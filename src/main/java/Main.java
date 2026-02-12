import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;

import controller.Controller;
import eventbus.EventBus;
import eventbus.Events;
import model.MainMenuState;
import model.ModelLogic;
import model.ModelState;
import presentation.Render;

import java.io.IOException;


public class Main {
    public static void main(String[] args) throws IOException {

        //инит лантерны под текущий терминал и создает буфер Screen
        Screen screen = new DefaultTerminalFactory().createScreen();

        // инит буфера
        screen.startScreen();
        screen.setCursorPosition(null);


        try {
            // СЛОЙ ШИНЫ
            EventBus bus = new EventBus();

            // СЛОЙ ВВОДА
            Controller controller = new Controller(bus, screen);

            // СЛОЙ МОДЕЛИ

            ModelState modelState = new ModelState();
            MainMenuState mainMenuState = new MainMenuState();
            new ModelLogic(bus, modelState, mainMenuState);

            new Render(bus, screen, mainMenuState, modelState);
            // Отрисовываем меню - можно заменить на заставку
            bus.post(new Events.RenderRefresh());


            // СЛОЙ ВЫВОДА


            boolean running = true;
            while (running) {
                controller.readKey(); // контроллер читает ввод и постит события
                // нужно отслеживать глобальное состояние игры

                Thread.sleep(10);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            screen.stopScreen();

        }

    }
}
