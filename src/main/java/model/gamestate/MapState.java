package model.gamestate;

import model.generator.Cell;
import model.generator.LevelGenerator;

import java.util.Random;

public class MapState {
    private Cell[][] map;

    private int dungeonLevel;
    private Random random;

    public MapState(Random random) {
        this.random = random;
        LevelGenerator generator = new LevelGenerator();

        // генерируем уровень
        LevelGenerator.LevelResult result = generator.generate(random);

        // сохраняем карту
        this.map = result.map;
    }


    public int getDungeonLevel() {
        return dungeonLevel;
    }



    public Cell[][] getMap() {
        return map;
    }
}

