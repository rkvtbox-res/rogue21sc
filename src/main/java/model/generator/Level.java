package model.generator;

import java.util.ArrayList;
import java.util.List;

public class Level {
    private final int width, height;
    private final Cell[][] level;
    private final List<Room> rooms = new ArrayList<>();

    public Level (int height, int width) {
        this.width = width;
        this.height = height;
        this.level = new Cell[height][width];
    }
}
