package model;

import controller.ControllerCommands;
import data.FormingLeaderboard;
import eventbus.EventBus;
import eventbus.Events;

import java.io.IOException;

public class ModelLogic {
    private final EventBus bus;
    private ModelState modelState; // тут храним глобальное состояние игры


    // Конструктор + подписываемся на события
    public ModelLogic(EventBus bus) {
        this.bus = bus;
        initClass();

        // читаем события нажатия клавиш - команды меню и игры
        bus.subscribe(Events.KeyPressed.class, e -> keyPressedNew(e.value()));

        // читаем события выбора пунктов меню
        bus.subscribe(Events.StartNewGame.class, e -> startNewGame());
        bus.subscribe(Events.QuitRequest.class, e -> quitRequest());

        // читаем события ввода имени
        bus.subscribe(Events.EnteredNameAddChar.class, e -> addCharToUserName(e.userName()));
        bus.subscribe(Events.EnteredNameBackspace.class, e -> removeCharInUserName());
        bus.subscribe(Events.EnteredNameSubmit.class, e -> submitUserName());

        // меню паузы
        bus.subscribe(Events.Pause.class, e -> gamePaused());

    }
    // Инициализируем подклассы состояний
    private void initClass() {
        this.modelState = new ModelState();
    }

    // геттеры
    public ModelState getModelState() {
        return modelState;
    }


    // основная логика управления
    private void keyPressedNew(ControllerCommands key) {
        if (key == null) return;

        if (modelState.getState() == ModelState.State.MENU || modelState.getState() == ModelState.State.GAME_PAUSE_MENU) {
            switch (key) {
                case MOVE_UP -> {
                    modelState.menuMoveUp();
                    bus.post(new Events.RenderRefresh());
                }
                case MOVE_DOWN -> {
                    modelState.menuMoveDown();
                    bus.post(new Events.RenderRefresh());
                }
                case ENTER -> {
                    menuSelection();
                }
            }
        } else if (modelState.getState() == ModelState.State.HALL_OF_FAME) {
            if (key == ControllerCommands.ENTER) {
                modelState.setState(ModelState.State.MENU);
                bus.post(new Events.RenderRefresh());
            }
        }
    }


    // логика выбора меню - по выбору постим соответствующее событие
    private void menuSelection() {
        if (modelState.getState() == ModelState.State.MENU) {
            switch (modelState.getSelectedMenu()) {
                case 0 -> {
                    bus.post(new Events.StartNewGame());
                    modelState.setState(ModelState.State.GAME);
                    bus.post(new Events.RenderRefresh());
                }
                case 1 -> bus.post(new Events.LoadGame());
                case 2 -> {
                    loadLeaderBoard();
                }
                case 3 -> bus.post(new Events.QuitRequest());
            }
        } else if (modelState.getState() == ModelState.State.GAME_PAUSE_MENU) {
            switch (modelState.getSelectedMenu()) {
                case 0 -> {
                    modelState.setState(ModelState.State.GAME);
                    bus.post(new Events.RenderRefresh());
                }
                case 1 -> {
                    bus.post(new Events.StartNewGame());
                    modelState.setState(ModelState.State.GAME);
                    bus.post(new Events.RenderRefresh());
                }
                case 2 -> bus.post(new Events.RenderRefresh());
                case 3 -> bus.post(new Events.QuitRequest());
            }
        }
    }

    private void startNewGame() {
        //bus.post(new Events.RenderRefresh());
    }

    private void addCharToUserName(char ch) {
        if (modelState.getState() != ModelState.State.ENTER_NAME) return;
        modelState.addCharToUserName(ch);
        bus.post(new Events.RenderRefresh());

    }

    private void removeCharInUserName() {
        if (modelState.getState() != ModelState.State.ENTER_NAME) return;
        modelState.removeCharFromUserName();
        bus.post(new Events.RenderRefresh());

    }

    private void submitUserName() {
        modelState.setState(ModelState.State.MENU);
        bus.post(new Events.RenderRefresh());

    }

    private void quitRequest() {
        modelState.setState(ModelState.State.QUIT);
        bus.post(new Events.RenderRefresh());

    }

    private void gamePaused() {
        modelState.setState(ModelState.State.GAME_PAUSE_MENU);
        bus.post(new Events.RenderRefresh());
    }

    private void loadLeaderBoard() {
        try {
            modelState.setLeaderboard(FormingLeaderboard.getLeaderBoard());
            modelState.setState(ModelState.State.HALL_OF_FAME);
            bus.post(new Events.RenderRefresh());
        } catch (IOException e) {
            // мягко: вернуть в меню + можно сохранить сообщение в state
            modelState.setState(ModelState.State.MENU);
            // modelState.setLastError("Cannot read leaderboard");
            bus.post(new Events.RenderRefresh());
        }
    }


}
