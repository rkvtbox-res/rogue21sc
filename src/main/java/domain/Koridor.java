package domain;
import java.util.ArrayList;

public class Koridor {
    public void koridor(ArrayList<Room> maps, char[][] map) {
        if (maps.size() < 2) return;
        ArrayList<Room> connected = new ArrayList<>();
        ArrayList<Room> unconnected = new ArrayList<>();

        unconnected.addAll(maps);
        connected.add(unconnected.remove(0));


        while (!unconnected.isEmpty()) {
            Room bestFrom = null;
            Room bestTo = null;
            int bestDistance = Integer.MAX_VALUE;

            for (Room a : connected) {
                for (Room b : unconnected) {
                    int centerAx = a.getX() + a.getHeight() / 2;
                    int centerAy = a.getY() + a.getWidth() / 2;
                    int centerBx = b.getX() + b.getHeight() / 2;
                    int centerBy = b.getY() + b.getWidth() / 2;

                    int distance = Math.abs(centerAx - centerBx) + Math.abs(centerAy - centerBy);

                    if (distance < bestDistance) {
                        bestDistance = distance;
                        bestFrom = a;
                        bestTo = b;
                    }
                }
            }

            if (bestFrom != null && bestTo != null) {
                // Пробуем соединить комнаты
                if (connectTwoRooms(bestFrom, bestTo, maps, map)) {
                    connected.add(bestTo);
                    unconnected.remove(bestTo);
                } else {
                    unconnected.remove(bestTo);
                    unconnected.add(bestTo);

                    if (unconnected.size() == maps.size() - 1) {
                        connected.add(bestTo);
                        unconnected.remove(bestTo);
                    }
                }
            }
        }
    }
    private boolean connectTwoRooms(Room a, Room b, ArrayList<Room> maps, char[][] map) {
        int[][] var = {
                {1, 2},
                {2, 1},
                {1, -2},
                {-2, 1}
        };

        for (int[] vars : var) {
            int x1, y1, x2, y2;

            if (a.getX() < b.getX()) {
                x1 = a.getX() + a.getHeight();
                x2 = b.getX() - 1;
            } else {
                x1 = a.getX() - 1;
                x2 = b.getX() + b.getHeight();
            }
            y1 = a.getY() + a.getWidth() / 2;
            y2 = b.getY() + b.getWidth() / 2;

            boolean variant1Valid = true;
            for (int y = Math.min(y1, y2); y <= Math.max(y1, y2); y++) {
                if (!isCellFreeForCorridor(map, maps, a, b, x1, y)) {
                    variant1Valid = false;
                    break;
                }
            }
            if (variant1Valid) {
                for (int x = Math.min(x1, x2); x <= Math.max(x1, x2); x++) {
                    if (!isCellFreeForCorridor(map, maps, a, b, x, y2)) {
                        variant1Valid = false;
                        break;
                    }
                }
            }
            if (variant1Valid) {
                for (int y = Math.min(y1, y2); y <= Math.max(y1, y2); y++) {
                    drawCorridorCell(map, x1, y);
                }
                for (int x = Math.min(x1, x2); x <= Math.max(x1, x2); x++) {
                    drawCorridorCell(map, x, y2);
                }
                return true;
            }
            boolean variant2Valid = true;

            for (int x = Math.min(x1, x2); x <= Math.max(x1, x2); x++) {
                if (!isCellFreeForCorridor(map, maps, a, b, x, y1)) {
                    variant2Valid = false;
                    break;
                }
            }
            if (variant2Valid) {
                for (int y = Math.min(y1, y2); y <= Math.max(y1, y2); y++) {
                    if (!isCellFreeForCorridor(map, maps, a, b, x2, y)) {
                        variant2Valid = false;
                        break;
                    }
                }
            }
            if (variant2Valid) {
                for (int x = Math.min(x1, x2); x <= Math.max(x1, x2); x++) {
                    drawCorridorCell(map, x, y1);
                }
                for (int y = Math.min(y1, y2); y <= Math.max(y1, y2); y++) {
                    drawCorridorCell(map, x2, y);
                }
                return true;
            }
        }

        return false;
    }
    private boolean isCellFreeForCorridor(char[][] map, ArrayList<Room> maps, Room currentA, Room currentB, int x, int y) {

        if (x < 0 || x >= map.length || y < 0 || y >= map[0].length) {
            return false;
        }
        if (x == 0 || x == map.length - 1 || y == 0 || y == map[0].length - 1) {
            return false;
        }
        if (map[x][y] == ' ' || map[x][y] == '*') {
            return true;
        }
        if (map[x][y] == '.') {
            if (isCellInRoom(x, y, currentA) || isCellInRoom(x, y, currentB)) {
                return true;
            }
            for (Room room : maps) {
                if (room != currentA && room != currentB) {
                    if (isCellInRoom(x, y, room)) {
                        return false;
                    }
                }
            }
            return false;
        }

        return false;
    }
    private boolean isCellInRoom(int x, int y, Room room) {
        // Включаем стены в проверку
        return x >= room.getX() - 1 && x <= room.getX() + room.getHeight() &&
                y >= room.getY() - 1 && y <= room.getY() + room.getWidth();
    }
    private void drawCorridorCell(char[][] map, int x, int y) {
        if (x >= 0 && x < map.length && y >= 0 && y < map[0].length) {

            if (x == 0 || x == map.length - 1 || y == 0 || y == map[0].length - 1) {
                return;
            }
            if (map[x][y] == ' ' || map[x][y] == '*') {
                map[x][y] = '.';
            }
        }
    }


    }