package debug;

import controller.ControlKeys;
import eventbus.EventBus;
import eventbus.Events;
import jcurses.system.CharColor;
import jcurses.system.Toolkit;

public class DebugKeyLogger {

    public DebugKeyLogger(EventBus bus) {
        bus.subscribe(Events.KeyPressed.class, this::onKey);
    }

    private void onKey(Events.KeyPressed e) {
        ControlKeys key = e.value();

        if (key == ControlKeys.MOVE_UP) {
            CharColor textColor = new CharColor(CharColor.BLACK, CharColor.WHITE);
            Toolkit.printString("[DEBUG] MOVE_UP pressed", 0, 0, textColor);
        }
    }
}
