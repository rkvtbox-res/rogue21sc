package presentation;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import model.gamestate.GameState;

import java.io.IOException;

public class RenderTitle {
    private static final String promptGameName = "Rogue";
    private static final String promptGreetings = "Welcome to the world of adventure, wanderer.";
    private static final String inputNamePrompt = "Write your name: ";


    public static void render(Screen screen, RenderState renderState, String userName) throws IOException {
        screen.clear();

        TextGraphics graphics = screen.newTextGraphics();

        graphics.putString(renderState.getWindowWidth() / 2 - promptGameName.length() / 2, (renderState.getWindowHeight() / 2 - 5), promptGameName);
        graphics.putString(renderState.getWindowWidth() / 2 - promptGreetings.length() / 2, (renderState.getWindowHeight() / 2 - 1), promptGreetings);
        graphics.putString(renderState.getWindowWidth() / 2 - inputNamePrompt.length() / 2, (renderState.getWindowHeight() / 2), inputNamePrompt);
        graphics.putString(renderState.getWindowWidth() / 2 + inputNamePrompt.length() / 2 + 1, (renderState.getWindowHeight() / 2), userName);



        screen.refresh();
    }
}


