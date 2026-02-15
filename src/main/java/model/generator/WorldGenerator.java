package model.generator;

import model.gamestate.GameState;

import java.util.Random;

// карта комната от 10х10 до 20х20
// ширина карты 80
// генерация карты > добавление на нее монстров > добавление сокровищ > оружия еды ...
//

public class WorldGenerator {
    private final GameState gameState;

    private final long seed;

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
        this.seed = new Random()
    }



    private roomGenerstor() {

    }
}
