package presentation;


import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import model.gamestate.GameState;

import java.io.IOException;

public class RenderGame {

    public static void render(Screen screen, GameState gameState, RenderState rs, String userName) throws IOException {
        screen.clear();
        TextGraphics graphics = screen.newTextGraphics();

        String title = "Rogue is " + userName;
        graphics.putString(rs.getWindowWidth() / 2 - title.length() / 2, rs.gameNameLine, title);
        // здоровье ловкость сила оружие
        graphics.putString(rs.getWindowWidth() / 2 - 10 - 5, rs.welcomePromptLine, "Agile");
        graphics.putString(rs.getWindowWidth() / 2 + 10, rs.welcomePromptLine, "Strength");
        graphics.putString(rs.getWindowWidth() / 2 - 36, rs.welcomePromptLine, "Health");
        graphics.putString(rs.getWindowWidth() / 2 + 30, rs.welcomePromptLine, "Weapon");


        // главное окно с катрой
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

        // окно лога
        for (int y = rs.borderLogFirstLine(); y <= rs.borderLogLastLine(); y++) {
            if (y == rs.borderLogFirstLine()) {
                for (int x = 1; x < rs.getWindowWidth(); x++) {
                    if (x == 1) graphics.putString(x, y, "╔");
                    else if (x == rs.getWindowWidth() - 1) graphics.putString(x, y, "╗");
                    else graphics.putString(x, y, "═");
                }
            } else if (y == rs.borderLogLastLine()) {
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
