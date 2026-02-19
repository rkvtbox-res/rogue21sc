package presentation;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import model.ModelState;

import java.io.IOException;

public class RenderHallOfFame {
    public static void render(Screen screen, RenderState rs) throws IOException {
        screen.clear();
        TextGraphics graphics = screen.newTextGraphics();

        graphics.putString(rs.getWindowWidth()/2 - rs.gameName.length()/2, rs.gameNameLine, rs.gameName);
        String welcomePrompt = "Hall of Fame";
        graphics.putString(rs.getWindowWidth()/2 - welcomePrompt.length()/2, rs.welcomePromptLine, welcomePrompt);



        for (int y = rs.borderWindowFirstLine; y <= rs.borderWindowLastLine(); y++) {
            if (y == rs.borderWindowFirstLine) {
                for (int x = 1; x < rs.getWindowWidth(); x++) {
                    if (x == 1) graphics.putString(x, y, "╔");
                    else if (x == rs.getWindowWidth() - 1) graphics.putString(x, y, "╗");
                    else graphics.putString(x, y, "═");
                }
            } else if (y == rs.borderWindowLastLine()) {
                for (int x = 1; x < rs.getWindowWidth(); x++) {
                    if (x == 1) graphics.putString(x, y, "╚");
                    else if (x == rs.getWindowWidth() - 1) graphics.putString(x, y, "╝");
                    else graphics.putString(x, y, "═");
                }
            } else {
                graphics.putString(1, y, "║");
                graphics.putString(rs.getWindowWidth() - 1, y, "║");
            }
        }



        screen.refresh();
    }

}
