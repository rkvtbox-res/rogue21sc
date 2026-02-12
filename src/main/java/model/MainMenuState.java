package model;

import java.io.PrintWriter;

public class MainMenuState {
    private final String gameTitle = "RogueGame 21Sc Project";
    private final String[] menuItemsNew = {"New Game", "Load game", "Save Game", "Hall of Fame", "Quit"};
    //private final String[] menuItemPaused = {"Resume Game", "New Game", "Save Game", "Hall of Fame", "Quit" }
    private int selectedMenu = 0;

    public String getGameTitle() {
        return gameTitle;
    }

    public String[] getMenuItems() {
        return menuItemsNew;
    }

    public int getSelectedMenu() {
        return selectedMenu;
    }

    public void menuMoveUp() {
        selectedMenu = (selectedMenu - 1 + menuItemsNew.length) % menuItemsNew.length;
    }

    public void menuMoveDown() {
        selectedMenu = (selectedMenu + 1 + menuItemsNew.length) % menuItemsNew.length;
    }

}
