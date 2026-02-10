package controller;

import eventbus.EventBus;
import eventbus.Events;

import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;


public class KeyboardController {
    private final EventBus bus;
    private final Screen screen;

    public KeyboardController (EventBus bus, Screen screen) {
        this.bus = bus;
        this.screen = screen;
    }

    public void readKey() throws IOException {
        ControlKeys key = InputCommands.readInput(screen);

        if (key == null) return;
        bus.post(new Events.KeyPressed(key));

        if (key == ControlKeys.QUIT) {
            bus.post(new Events.Quit());
        }
    }

}
