package model.generator;

public class Room {
    private final int x;              // левая верхняя точка
    private final int y;              // левая нижняя точка
    private final int height;         // длина боковой стены
    private final int width;         // длина нижней стены

    public Room (int x, int y, int height, int width) {
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
    }

    public int getX() { return x; }
    public int getY() { return y; }
    public int getHeight() { return height; }
    public int getWidth() { return width; }

    // TODO геттер центра комнаты
}
