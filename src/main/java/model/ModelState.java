package model;

public class ModelState {

    // константы состояний игры
    public enum State {
        MENU,           // стартовое меню
        ENTER_NAME,     // режим ввода имени игрока
        GAME,           // игра
        PAUSE_MENU,     // меню во время игры, игра на паузе
        QUIT }          // выход из игры

    private final StringBuilder userName = new StringBuilder();


    // текущее состояние
    private State state = State.ENTER_NAME;

    // геттер
    public State getState() { return state; }

    // сеттер
    public void setState(State state) { this.state = state; }

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

