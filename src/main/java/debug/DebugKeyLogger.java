package debug;

import eventbus.EventBus;
import eventbus.Events;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import controller.ControlKeys;

import java.io.IOException;

public class DebugKeyLogger {
    private final Screen screen;


    public DebugKeyLogger(EventBus bus, Screen screen) {
        this.screen = screen;
        bus.subscribe(Events.KeyPressed.class, this::onKey);
    }

    private void onKey(Events.KeyPressed e) {
        TextGraphics textGraphics = screen.newTextGraphics();

        ControlKeys key = e.value();

        if (key == ControlKeys.MOVE_UP) {
            textGraphics.putString(3,3, "Pressed MOVE_UP");
            try {
                screen.refresh();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

        }
    }
}
