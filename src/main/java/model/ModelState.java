package model;

import data.Leader;

import java.util.List;

public class ModelState {
    // ------------------------------------------------ константы состояний игры (глобальные состояния)
    public enum State {
        MENU,           // стартовое меню
        ENTER_NAME,     // режим ввода имени игрока
        GAME,           // игра
        HALL_OF_FAME,   // таблица рекордов
        GAME_INVENTORY,
        GAME_PAUSE_MENU,     // меню во время игры, игра на паузе
        QUIT }          // выход из игры

    // Тут собираем имя пользователя
    private final StringBuilder userName = new StringBuilder();


    // ------------------------------------------------ работа с меню - разный набор в зависимости от состояния
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

    // ---------------------------------------------- Таблица рекордов
    private List<Leader> leaderBoard;

    // гетеры
    public int getSelectedMenu() {
        return selectedMenu;
    }

    public void menuMoveUp() {
        selectedMenu = (selectedMenu - 1 + menuItemsNew.length) % menuItemsNew.length;
    }

    public void menuMoveDown() {
        selectedMenu = (selectedMenu + 1 + menuItemsNew.length) % menuItemsNew.length;
    }

    // ------------------------------------------------ состояние игры (стартовое - ввод имени)
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

    public void setLeaderboard(List<Leader> leaderBoard) {
        this.leaderBoard = leaderBoard;
    }

    public List<Leader> getLeaderBoard() {
        return leaderBoard;
    }
}

