package model;

import java.io.PrintWriter;

public class MainMenuState {
    private final ModelState modelState;

    private final String gameTitle = "RogueGame 21Sc Project";
    private final String playerName = "";

    private final String[] menuItemsNew = {"New Game", "Load game", "Hall of Fame", "Quit"};
    private final String[] menuItemsPaused = {"Resume Game", "New Game", "Hall of Fame", "Quit" };
    private int selectedMenu = 0;

    public MainMenuState(ModelState modelState) {
        this.modelState = modelState;

    }

    public String getGameTitle() {
        return gameTitle;
    }

    public String[] getMenuItems() {
        if (modelState.getState() == ModelState.State.MENU) {
            return menuItemsNew;
        } else {
            return menuItemsPaused;
        }
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
