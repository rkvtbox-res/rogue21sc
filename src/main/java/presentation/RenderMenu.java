package presentation;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import model.MainMenuState;

import java.io.IOException;

public class RenderMenu {

    public static void render(Screen screen, MainMenuState menu) throws IOException {
        screen.clear();
        TextGraphics graphics = screen.newTextGraphics();

        graphics.putString(2, 0, "DEBUG selected=" + menu.getSelectedMenu());

        graphics.putString(2, 1, menu.getGameTitle());

        String[] items = menu.getMenuItems();
        for (int i = 0; i < items.length; i++) {
            boolean selected = i == menu.getSelectedMenu();
            if (selected) {
                graphics.setForegroundColor(TextColor.ANSI.YELLOW);
                graphics.putString(4, 3 + i, "> " + items[i]);
                graphics.setForegroundColor(TextColor.ANSI.DEFAULT);
            } else {
                graphics.putString(4, 3 + i, "  " + items[i]);
            }
        }

        screen.refresh();
    }
}