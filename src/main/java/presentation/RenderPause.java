package presentation;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import model.ModelState;
import model.gamestate.GameState;

import java.io.IOException;

public class RenderPause {
    public static void render(Screen screen, GameState gameState, RenderState rs, ModelState modelState) throws IOException {
        screen.clear();
        TextGraphics graphics = screen.newTextGraphics();

        String title = rs.getPromptPauseTitle();

        graphics.putString(rs.getWindowWidth() / 2 - title.length() / 2, rs.gameNameLine, title);
        // здоровье ловкость сила оружие
        graphics.putString(rs.getWindowWidth() / 2 - 10 - 5, rs.welcomePromptLine, "Agility");
        graphics.putString(rs.getWindowWidth() / 2 - 10 - 5, rs.welcomePromptLine+1, String.valueOf(gameState.getPlayerState().getAgility()));


        graphics.putString(rs.getWindowWidth() / 2 + 10, rs.welcomePromptLine, "Strength");
        graphics.putString(rs.getWindowWidth() / 2 + 10, rs.welcomePromptLine+1, String.valueOf(gameState.getPlayerState().getPlayerStrength()));


        graphics.putString(rs.getWindowWidth() / 2 - 36, rs.welcomePromptLine, "Health");
        graphics.putString(rs.getWindowWidth() / 2 - 36, rs.welcomePromptLine+1, String.valueOf(gameState.getPlayerState().getPlayerHealth()) + " / " + String.valueOf(gameState.getPlayerState().getPlayerHealthMax()));


        graphics.putString(rs.getWindowWidth() / 2 + 30, rs.welcomePromptLine, "Weapon");
        graphics.putString(rs.getWindowWidth() / 2 + 30, rs.welcomePromptLine+1, "Sword");


        // главное окно с картой
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

        String[] items = modelState.getMenuItems();
        for (int i = 0; i < items.length; i++) {
            boolean selected = i == modelState.getSelectedMenu();
            if (selected) {
                graphics.setForegroundColor(TextColor.ANSI.YELLOW);
                graphics.putString(rs.getWindowWidth()/2 - 5, rs.borderWindowFirstLine + 3 + i, "> " + items[i]);
                graphics.setForegroundColor(TextColor.ANSI.DEFAULT);
            } else {
                graphics.putString(rs.getWindowWidth()/2 - 5, rs.borderWindowFirstLine + 3 + i, "  " + items[i]);
            }
        }


        screen.refresh();
    }
}
