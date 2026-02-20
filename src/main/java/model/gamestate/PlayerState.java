package model.gamestate;

import model.generator.Cell;

import java.util.Random;

public class PlayerState {

    private int playerPosX = 5;
    private int playerPosY = 5;
    private MapState mapState;
    private int playerHealth = 100;
    private final int playerHealthMax = 100;

    private int playerStrength = 10;
    private int agility = 10;

    // TODO добавить поле текущего оружия

    private int treasure = 0;
    private int countOfKills = 0;
    private int countOfFood = 0;
    private int countOfDrinks = 0;
    private int countOfBooks = 0;
    private int countOfMissedStrikes = 0;
    private int countOfHit = 0;
    private int countOfSteps = 0;


    // конструктор
    public PlayerState(MapState mapState, Random random) {
        this.mapState = mapState;
        // находим старт. позицию
        int[] startPos = findStartPosition();
        this.playerPosX = startPos[0];
        this.playerPosY = startPos[1];
    }
    private int[] findStartPosition() {
        Cell[][] map = mapState.getMap();
        for (int y = 0; y < map.length; y++) {
            for (int x = 0; x < map[y].length; x++) {
                if (map[y][x].isWalkable()) {
                    return new int[]{x, y};
                }
            }
        }
        return new int[]{5, 5}; // запасной вариант
    }
    //геттеры
    public int getPlayerPosX() {
        return playerPosX;
    }

    public int getPlayerPosY() {
        return playerPosY;
    }

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

    public int getTreasure() {
        return treasure;
    }

    public int getCountOfKills() {
        return countOfKills;
    }

    public int getCountOfFood() {
        return countOfFood;
    }

    public int getCountOfDrinks() {
        return countOfDrinks;
    }

    public int getCountOfBooks() {
        return countOfBooks;
    }

    public int getCountOfMissedStrikes() {
        return countOfMissedStrikes;
    }

    public int getCountOfHit() {
        return countOfHit;
    }

    public int getCountOfSteps() {
        return countOfSteps;
    }


    //сеттеры
    public void setPlayerPosX(int playerPosX) {
        this.playerPosX = playerPosX;
    }

    public void setPlayerPosY(int playerPosY) {
        this.playerPosY = playerPosY;
    }

    // управление
    public void playerMoveUp(){int newY = playerPosY - 1;
        if (canMoveTo(playerPosX, newY)) {
        playerPosY = newY;
        countOfSteps++;
    }
}


    public void playerMoveDown(){ int newY = playerPosY + 1;
        if (canMoveTo(playerPosX, newY)) {
            playerPosY = newY;
            countOfSteps++;
        }
    }

    public void playerMoveRight() {
        int newX = playerPosX + 1;
        if (canMoveTo(newX, playerPosY)) {
            playerPosX = newX;
            countOfSteps++;
        }
    }
    public void playerMoveLeft() {
        int newX = playerPosX - 1;
        if (canMoveTo(newX, playerPosY)) {
            playerPosX = newX;
            countOfSteps++;
        }
    }
    private boolean canMoveTo(int x, int y) {
        Cell[][] map = mapState.getMap();

        if (y < 0 || y >= map.length || x < 0 || x >= map[0].length) {
            return false;
        }
        return map[y][x].isWalkable();
    }

}
