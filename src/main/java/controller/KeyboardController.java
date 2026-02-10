package controller;

import eventbus.EventBus;
import eventbus.Events;

public class KeyboardController {
    private final EventBus bus;

    public KeyboardController (EventBus bus) {
        this.bus = bus;
    }

    public void readKey() {
        ControlKeys key = InputCommands.readInput();
        
        if (key == ControlKeys.QUIT) {
            bus.post(new Events.Quit());
            return;
        }

        bus.post(new Events.KeyPressed(key));
    }

}
