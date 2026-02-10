
import controller.ControlKeys;
import controller.KeyboardController;
import debug.DebugKeyLogger;
import eventbus.EventBus;
import eventbus.Events;
import jcurses.system.Toolkit;

import controller.InputCommands;
import presentation.Present;

public class Main {
    public static void main(String[] args) {

        //инит курсов
        Toolkit.init();

        try {
            // создаем шину
            EventBus bus = new EventBus();

            // создаем контроллер клавиатуры
            KeyboardController controller = new KeyboardController(bus);

            new DebugKeyLogger(bus);

            boolean running = true;

            while (running) {
                controller.readKey(); // контроллер читает ввод и постит событи
            }
        } finally {
            Toolkit.shutdown();

        }

    }
}
