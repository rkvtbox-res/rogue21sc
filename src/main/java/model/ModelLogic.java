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

        // читаем события нажатия клавиш
        bus.subscribe(Events.KeyPressed.class, e -> keyPressed(e.value()));

        // читаем события выбора пунктов меню
        bus.subscribe(Events.StartNewGame.class, e -> startNewGame());
    }

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

}
