package model;

public class PlayerStage {
    private int playerPosX = 5;
    private int playerPosY = 5;

    private int playerHealth = 100;
    private int playerStrength = 10;

    //геттеры
    public int getPlayerPosX() {
        return playerPosX;
    }

    public int getPlayerPosY() {
        return playerPosY;
    }
    // TODO > добавить по силе и здоровью


    //сеттеры
    public void setPlayerPosX(int playerPosX) {
        this.playerPosX = playerPosX;
    }

    public void setPlayerPosY(int playerPosY) {
        this.playerPosY = playerPosY;
    }

}
