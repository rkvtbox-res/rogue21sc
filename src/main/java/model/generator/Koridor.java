package model.generator;

import java.util.ArrayList;

public class Koridor {

    public void koridor(ArrayList<Room> rooms, Cell[][] map) {
        if (rooms.size() < 2) return;

        ArrayList<Room> connected = new ArrayList<>();
        ArrayList<Room> unconnected = new ArrayList<>();

        unconnected.addAll(rooms);
        connected.add(unconnected.remove(0));

        while (!unconnected.isEmpty()) {
            Room bestFrom = null;
            Room bestTo = null;
            int bestDistance = Integer.MAX_VALUE;

            // Ищем ближайшую пару: одна комната из connected и одна из unconnected
            for (Room a : connected) {
                for (Room b : unconnected) {
                    int ax = a.getX() + a.getHeight() / 2; // X по карте = row (как у тебя в Room)
                    int ay = a.getY() + a.getWidth() / 2;  // Y по карте = col

                    int bx = b.getX() + b.getHeight() / 2;
                    int by = b.getY() + b.getWidth() / 2;

                    int distance = Math.abs(ax - bx) + Math.abs(ay - by);

                    if (distance < bestDistance) {
                        bestDistance = distance;
                        bestFrom = a;
                        bestTo = b;
                    }
                }
            }

            if (bestFrom != null && bestTo != null) {
                // Пробуем соединить комнаты
                if (connectTwoRooms(bestFrom, bestTo, rooms, map)) {
                    connected.add(bestTo);
                    unconnected.remove(bestTo);
                } else {
                    // если не удалось — крутим список (как у тебя было)
                    unconnected.remove(bestTo);
                    unconnected.add(bestTo);

                    if (unconnected.size() == rooms.size() - 1) {
                        connected.add(bestTo);
                        unconnected.remove(bestTo);
                    }
                }
            }
        }
    }

    private boolean connectTwoRooms(Room a, Room b, ArrayList<Room> rooms, Cell[][] map) {

        // Вычисляем точки “выхода” из комнат примерно по центру стены
        int x1, y1, x2, y2;

        // x — строка (row), y — колонка (col)
        if (a.getX() < b.getX()) {
            x1 = a.getX() + a.getHeight(); // снизу комнаты A (под стеной)
            x2 = b.getX() - 1;             // сверху комнаты B (над стеной)
        } else {
            x1 = a.getX() - 1;             // сверху комнаты A
            x2 = b.getX() + b.getHeight(); // снизу комнаты B
        }

        y1 = a.getY() + a.getWidth() / 2;
        y2 = b.getY() + b.getWidth() / 2;

        // Вариант 1: сначала горизонтально по y (по колонкам), потом вертикально по x (по строкам)
        if (tryVariant1(a, b, rooms, map, x1, y1, x2, y2)) return true;

        // Вариант 2: сначала вертикально, потом горизонтально
        if (tryVariant2(a, b, rooms, map, x1, y1, x2, y2)) return true;

        return false;
    }

    private boolean tryVariant1(Room a, Room b, ArrayList<Room> rooms, Cell[][] map,
                                int x1, int y1, int x2, int y2) {

        // 1) проверяем линию по y на уровне x1
        for (int y = Math.min(y1, y2); y <= Math.max(y1, y2); y++) {
            if (!isCellFreeForCorridor(map, rooms, a, b, x1, y)) {
                return false;
            }
        }

        // 2) проверяем линию по x на уровне y2
        for (int x = Math.min(x1, x2); x <= Math.max(x1, x2); x++) {
            if (!isCellFreeForCorridor(map, rooms, a, b, x, y2)) {
                return false;
            }
        }

        // 3) рисуем
        for (int y = Math.min(y1, y2); y <= Math.max(y1, y2); y++) {
            drawCorridorCell(map, x1, y);
        }
        for (int x = Math.min(x1, x2); x <= Math.max(x1, x2); x++) {
            drawCorridorCell(map, x, y2);
        }

        return true;
    }

    private boolean tryVariant2(Room a, Room b, ArrayList<Room> rooms, Cell[][] map,
                                int x1, int y1, int x2, int y2) {

        // 1) проверяем линию по x на уровне y1
        for (int x = Math.min(x1, x2); x <= Math.max(x1, x2); x++) {
            if (!isCellFreeForCorridor(map, rooms, a, b, x, y1)) {
                return false;
            }
        }

        // 2) проверяем линию по y на уровне x2
        for (int y = Math.min(y1, y2); y <= Math.max(y1, y2); y++) {
            if (!isCellFreeForCorridor(map, rooms, a, b, x2, y)) {
                return false;
            }
        }

        // 3) рисуем
        for (int x = Math.min(x1, x2); x <= Math.max(x1, x2); x++) {
            drawCorridorCell(map, x, y1);
        }
        for (int y = Math.min(y1, y2); y <= Math.max(y1, y2); y++) {
            drawCorridorCell(map, x2, y);
        }

        return true;
    }

    /**
     * Проверяем можно ли провести коридор через клетку (x,y).
     * ВАЖНО: map[row][col] => map[x][y] у тебя это row/col, но в массиве именно [x][y] если x=row.
     * У тебя в генераторе map[i][j] где i=row, j=col. Значит map[x][y] корректно,
     * при условии что x=row, y=col (как у тебя).
     */
    private boolean isCellFreeForCorridor(Cell[][] map, ArrayList<Room> rooms,
                                          Room currentA, Room currentB,
                                          int x, int y) {

        // границы массива
        if (x < 0 || x >= map.length || y < 0 || y >= map[0].length) {
            return false;
        }

        // не рисуем по рамке карты
        if (x == 0 || x == map.length - 1 || y == 0 || y == map[0].length - 1) {
            return false;
        }

        Cell cell = map[x][y];

        // Пустота или стена — можно “пробить” коридор
        if (cell == Cell.EMPTY || cell == Cell.WALL) {
            return true;
        }

        // Если попали в пол комнаты (FLOOR) или существующий коридор — обычно нельзя,
        // НО если это внутри одной из соединяемых комнат — можно (чтобы войти в комнату)
        if (cell == Cell.FLOOR || cell == Cell.CORRIDOR) {
            if (isCellInRoom(x, y, currentA) || isCellInRoom(x, y, currentB)) {
                return true;
            }
            // если это внутри другой комнаты — запрещаем
            for (Room r : rooms) {
                if (r != currentA && r != currentB) {
                    if (isCellInRoom(x, y, r)) return false;
                }
            }
            return false;
        }

        // остальное (двери, лестницы) пока считаем препятствием
        return false;
    }

    // Проверяем принадлежность точки комнате (включая стены вокруг комнаты)
    private boolean isCellInRoom(int x, int y, Room room) {
        return x >= room.getX() - 1 && x <= room.getX() + room.getHeight()
                && y >= room.getY() - 1 && y <= room.getY() + room.getWidth();
    }

    // Рисуем коридор: заменяем EMPTY/WALL на CORRIDOR, но не ломаем рамку и не перетираем FLOOR
    private void drawCorridorCell(Cell[][] map, int x, int y) {
        if (x <= 0 || x >= map.length - 1 || y <= 0 || y >= map[0].length - 1) return;

        Cell cell = map[x][y];

        if (cell == Cell.EMPTY || cell == Cell.WALL) {
            map[x][y] = Cell.CORRIDOR;
        }
    }
}
