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

        bus.subscribe(Events.KeyPressed.class, e -> keyPressed(e.value()));
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
            }
        }

    }

}
