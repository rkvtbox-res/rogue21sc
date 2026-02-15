import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;

import controller.Controller;
import eventbus.EventBus;
import eventbus.Events;
import model.*;
import model.gamestate.GameState;
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

            // СЛОЙ МОДЕЛИ
            ModelState modelState = new ModelState();
            MainMenuState mainMenuState = new MainMenuState(modelState);
            new ModelLogic(bus, modelState, mainMenuState);

            GameState gameState = new GameState();
            new GameLogic(gameState, bus);

            // СЛОЙ ВВОДА
            Controller controller = new Controller(bus, screen, modelState);

            new Render(bus, screen, mainMenuState, modelState, gameState);
            // Отрисовываем меню - можно заменить на заставку
            bus.post(new Events.RenderRefresh());


            // СЛОЙ ВЫВОДА


            boolean running = true;
            while (running) {
                controller.controller(); // контроллер читает ввод и постит события

                if (modelState.getState() == ModelState.State.QUIT) {
                    running = false;
                }

                Thread.sleep(10);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            screen.stopScreen();

        }

    }
}
