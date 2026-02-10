import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;

import controller.KeyboardController;
import debug.DebugKeyLogger;
import eventbus.EventBus;

import java.io.IOException;


public class Main {
    public static void main(String[] args) throws IOException {

        //инит лантерны под текущий терминал и создает буфер Screen
        Screen screen = new DefaultTerminalFactory().createScreen();

        // инит буфера
        screen.startScreen();

        try {
            // создаем шину
            EventBus bus = new EventBus();

            // создаем контроллер клавиатуры через переменную для обращения
            KeyboardController controller = new KeyboardController(bus, screen);

            // создаем объект дебаггера для подписки
            new DebugKeyLogger(bus, screen);

            boolean running = true;

            while (running) {
                controller.readKey(); // контроллер читает ввод и постит события


                Thread.sleep(10);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            screen.stopScreen();

        }

    }
}
