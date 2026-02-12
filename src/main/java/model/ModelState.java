package model;

public class ModelState {

    // константы состояний игры
    public enum State {
        MENU,           // стартовое меню
        GAME,           // игра
        PAUSE_MENU,     // меню во время игры, игра на паузе
        QUIT }          // выход из игры

    // текущее состояние
    private State state = State.MENU;

    // геттер
    public State getState() { return state; }

    // сеттер
    public void setState(State state) { this.state = state; }
}