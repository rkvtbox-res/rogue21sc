package presentation;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import model.ModelState;

import java.io.IOException;

public class RenderMenu {

    public static void render(Screen screen, RenderState rs, ModelState modelState) throws IOException {
        screen.clear();
        TextGraphics graphics = screen.newTextGraphics();

        graphics.putString(rs.getWindowWidth()/2 - rs.gameName.length()/2, rs.gameNameLine, rs.gameName);
        String welcomePrompt = modelState.getUserName() + rs.welcomePrompt;
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