package model;

import model.gamestate.GameState;

// карта комната от 10х10 до 20х20
// ширина карты 80

public class WorldGenerator {
    private final GameState gameState;

    private final int levelQuantity = 21;

    private final int levelMaxWidth = 100;
    private final int levelMaxHeight = 100;

    private final int roomsAtLevel = 9;

    private final int roomMinWidth = 10;
    private final int roomMaxWidth = 25;

    private final int roomMinHeight = 10;
    private final int roomMaxHeight = 25;


    public WorldGenerator(GameState gameState) {
        this.gameState = gameState;
    }



    //private roomGenerstor
}
