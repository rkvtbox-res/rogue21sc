package model;

public class ModelState {
    // ------------------------------------------------ константы состояний игры (глобальные состояния)
    public enum State {
        MENU,           // стартовое меню
        ENTER_NAME,     // режим ввода имени игрока
        GAME,           // игра
        GAME_INVENTORY,
        GAME_PAUSE_MENU,     // меню во время игры, игра на паузе
        QUIT }          // выход из игры

    private final StringBuilder userName = new StringBuilder();
    private final String gameTitle = "RogueGame 21Sc Project";

    // ------------------------------------------------ работа с меню
    private final String[] menuItemsNew = {"New Game", "Load game", "Hall of Fame", "Quit"};
    private final String[] menuItemsPaused = {"Resume Game", "New Game", "Hall of Fame", "Quit" };
    private int selectedMenu = 0; // индекс текущего меню

    public String[] getMenuItems() {
        if (state == ModelState.State.MENU) {
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

    // ------------------------------------------------ текущее состояние игры
    private State state = State.ENTER_NAME;

    public State getState() { return state; }
    public void setState(State state) { this.state = state; }

    // --------------------------------------------------------- работа с именем
    public void addCharToUserName(char ch) {
        userName.append(ch);
    }
    public void removeCharFromUserName() {
        if (userName.length() > 0) {
            userName.deleteCharAt(userName.length() - 1);
        }
    }

    public String getUserName () {
        return userName.toString();
    }


}

