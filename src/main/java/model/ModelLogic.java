package model;

import controller.ControllerCommands;
import eventbus.EventBus;
import eventbus.Events;

public class ModelLogic {
    private final EventBus bus;
    private final ModelState modelState;
    private final MainMenuState mainMenuState;


    public ModelLogic (EventBus bus, ModelState modelState, MainMenuState mainMenuState) {
        this.bus = bus;
        this.modelState = modelState;
        this.mainMenuState = mainMenuState;


        // читаем события нажатия клавиш - команды меню и игры
        bus.subscribe(Events.KeyPressed.class, e -> keyPressed(e.value()));

        // читаем события выбора пунктов меню
        bus.subscribe(Events.StartNewGame.class, e -> startNewGame());
        bus.subscribe(Events.QuitRequest.class, e -> quitRequest());

        // читаем события ввода имени
        bus.subscribe(Events.EnteredNameAddChar.class, e -> addCharToUserName(e.userName()));
        bus.subscribe(Events.EnteredNameBackspace.class, e -> removeCharInUserName());
        bus.subscribe(Events.EnteredNameSubmit.class, e -> submitUserName());
    }

    // основная логика управления
    private void keyPressed (ControllerCommands key) {
        if (key == null) return;

        if (modelState.getState() == ModelState.State.MENU) {
            switch (key) {
                case MOVE_UP -> {
                    mainMenuState.menuMoveUp();
                    bus.post(new Events.RenderRefresh());
                }
                case MOVE_DOWN -> {
                    mainMenuState.menuMoveDown();
                    bus.post(new Events.RenderRefresh());
                }
                case ENTER -> {
                    menuSelection();
                }
            }
        }
    }


    // логика выбора меню - по выбору постим соответствующее событие
    private void menuSelection() {
        switch (mainMenuState.getSelectedMenu()) {
            case 0 -> {
                modelState.setState(ModelState.State.GAME);
                bus.post(new Events.StartNewGame());
                bus.post(new Events.RenderRefresh());
            }
            case 1 -> bus.post(new Events.LoadGame());
            case 2 -> bus.post(new Events.RenderRefresh());
            case 3 -> bus.post(new Events.QuitRequest());
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

    private void submitUserName () {
        modelState.setState(ModelState.State.MENU);
        bus.post(new Events.RenderRefresh());

    }

    private void quitRequest() {
        modelState.setState((ModelState.State.QUIT));
        bus.post(new Events.RenderRefresh());

    }

}
