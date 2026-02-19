package model.generator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LevelGenerator {

    private static final int GRID_X = 3;
    private static final int GRID_Y = 3;


    private final int minRoomSize = 30;
    private final int maxRoomSize = 40;

    // Отступ внутри сектора для расстояния между комнатами
    private final int outOfWallSpace = 2;

    private final int mapWidth;
    private final int mapHeight;

    //конструктор
    public LevelGenerator() {
                // Минимальный размер сектора:
        // maxRoomSize + (outOfWallSpace слева+справа) + 2 клетки на стены комнаты
        int sectorMinSize = maxRoomSize + outOfWallSpace * 2;

        this.mapWidth  = GRID_X * sectorMinSize + 5;
        this.mapHeight = (GRID_Y * sectorMinSize + 5) / 2;
    }

    public LevelResult generate(Random random) {
       

        Cell[][] map = createEmptyMap(mapWidth, mapHeight);

        List<Room> rooms = new ArrayList<>();

        // размеры сектора
        int sectorW = mapWidth / GRID_X;
        int sectorH = mapHeight  / GRID_Y;

        // gx — “ряд” сектора, gy — “колонка” сектора
        for (int gx = 0; gx < GRID_Y; gx++) {
            for (int gy = 0; gy < GRID_X; gy++) {

                int sectorTop    = 1 + gx * sectorH;
                int sectorLeft   = 1 + gy * sectorW;
                int sectorBottom = sectorTop + sectorH - 1;
                int sectorRight  = sectorLeft + sectorW - 1;

                int roomH = (minRoomSize + random.nextInt(maxRoomSize - minRoomSize + 1)) / 2;
                int roomW = minRoomSize + random.nextInt(maxRoomSize - minRoomSize + 1);


                int minX = sectorTop  + outOfWallSpace + 1;
                int maxX = sectorBottom - outOfWallSpace - roomH - 1;

                int minY = sectorLeft + outOfWallSpace + 1;
                int maxY = sectorRight - outOfWallSpace - roomW - 1;

                int x = minX + (maxX > minX ? random.nextInt(maxX - minX + 1) : 0);
                int y = minY + (maxY > minY ? random.nextInt(maxY - minY + 1) : 0);

                drawRoom(map, roomH, roomW, x, y);
                rooms.add(new Room(roomH, roomW, x, y));
            }
        }

                new Koridor().koridor(new ArrayList<>(rooms), map);
        return new LevelResult(map, rooms, random, mapWidth, mapHeight);
    }

    private Cell[][] createEmptyMap(int width, int height) {
        Cell[][] map = new Cell[height][width];

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                // рамка карты — стены, внутри — пустота
                map[i][j] = (i == 0 || i == height - 1 || j == 0 || j == width - 1)
                        ? Cell.WALL
                        : Cell.EMPTY;
            }
        }
        return map;
    }

    private void drawRoom(Cell[][] map, int h, int w, int x, int y) {
        // пол
        for (int i = x; i < x + h; i++) {
            for (int j = y; j < y + w; j++) {
                map[i][j] = Cell.FLOOR;
            }
        }
        // стены вокруг
        for (int i = x - 1; i <= x + h; i++) {
            for (int j = y - 1; j <= y + w; j++) {
                if (i == x - 1 || i == x + h || j == y - 1 || j == y + w) {
                    map[i][j] = Cell.WALL;
                }
            }
        }
    }

    public static class LevelResult {
        public final Cell[][] map;
        public final List<Room> rooms;
        public final Random random;
        public final int width;
        public final int height;

        public LevelResult(Cell[][] map, List<Room> rooms, Random random, int width, int height) {
            this.map = map;
            this.rooms = rooms;
            this.random = random;
            this.width = width;
            this.height = height;
        }
    }
}
