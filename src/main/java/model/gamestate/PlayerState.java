package model.gamestate;

public class PlayerState {

    private int playerPosX = 5;
    private int playerPosY = 5;

    private int playerHealth = 100;
    private final int playerHealthMax = 100;

    private int playerStrength = 10;
    private int agility = 10;

    // TODO добавить поле текущего оружия

    // статистика по игроку
    private int treasure = 0;
    private int levelOfDungeon = 0;
    private int countOfKills = 0;
    private int countOfFood = 0;
    private int countOfDrinks = 0;
    private int countOfBooks = 0;
    private int countOfMissedStrikes = 0;
    private int countOfHit = 0;
    private int countOfSteps = 0;


    // конструктор
    public PlayerState() {
    }

    //геттеры
    public int getPlayerPosX() {
        return playerPosX;
    }

    public int getPlayerPosY() {
        return playerPosY;
    }
    // TODO > добавить по силе и здоровью

    public int getPlayerHealth() {
        return playerHealth;
    }

    public int getPlayerHealthMax() {
        return playerHealth;
    }

    public int getPlayerStrength() {
        return playerStrength;
    }

    public int getAgility() {
        return agility;
    }

    //сеттеры
    public void setPlayerPosX(int playerPosX) {
        this.playerPosX = playerPosX;
    }
    public void setPlayerPosY(int playerPosY) {
        this.playerPosY = playerPosY;
    }

    // управление
    public void playerMoveUp() {this.playerPosY--;}
    public void playerMoveDown() {this.playerPosY++;}
    public void playerMoveRight() {this.playerPosX++;}
    public void playerMoveLeft() {this.playerPosY--;}

}
