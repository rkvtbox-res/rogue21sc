package controller;

import eventbus.EventBus;
import eventbus.Events;

import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;


public class Controller {
    private final EventBus bus;
    private final Screen screen;

    public Controller(EventBus bus, Screen screen) {
        this.bus = bus;
        this.screen = screen;
    }

    public void readKey() throws IOException {
        ControllerCommands key = ControllerReadCommands.readInput(screen);

        //if (key == null) return;

        bus.post(new Events.KeyPressed(key));
    }
}
