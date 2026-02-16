package org.example;

import domain.Room;
import domain.Character;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
    private static char[][] dungeon;

    private static Character player;
    private static ArrayList<Room> rooms;
    private static int lastX = -1;
    private static int lastY = -1;
    public static void main(String[] args) throws IOException, InterruptedException {
        Room roomGenerator = new Room(0, 0, 0, 0);
        dungeon = roomGenerator.sborka();
        rooms = roomGenerator.getRooms();

        firstposition(dungeon, rooms);


        while(true){
            Ypravlen();
            otrisovka();
            Thread.sleep(1000);
        }
    }


    public static void Ypravlen() throws IOException {
        if (System.in.available() > 0) {
            char key = (char) System.in.read();
            if (key == '\n' || key == '\r') {
                return;
            }
            int oldX = player.getcharacterX();
            int oldY = player.getcharacterY();
            switch (key) {
                case 'w': {
                    player.movecharacter(-1, 0, dungeon);
                    break;
                }
                case 's': {
                    player.movecharacter(+1, 0, dungeon);
                    break;
                }
                case 'a': {
                    player.movecharacter(0, -1, dungeon);
                    break;
                }
                case 'd': {
                    player.movecharacter(0, +1, dungeon);
                    break;
                }
            }
        }}



    public static void otrisovka() {
        // При первом запуске рисуем всю карту
        if (lastX == -1) {
            fullRender();
            lastX = player.getcharacterX();
            lastY = player.getcharacterY();
            return;
        }

        // При движении обновляем только игрока
        updatePlayerPosition();
    }

    public static void fullRender() {
        StringBuilder sb = new StringBuilder("\033[H");
        for (int i = 0; i < dungeon.length; i++) {
            for (int j = 0; j < dungeon[0].length; j++) {
                sb.append(i == player.getcharacterX() && j == player.getcharacterY() ? '@' : dungeon[i][j]);
            }
            sb.append('\n');
        }
        System.out.print(sb.toString());
    }

    public static void updatePlayerPosition() {
        if (lastX != player.getcharacterX() || lastY != player.getcharacterY()) {
            // Стираем старого
            if (lastX >= 0) {
                System.out.print("\033[" + (lastX + 1) + ";" + (lastY + 1) + "H");
                System.out.print(dungeon[lastX][lastY]);
            }

            // Рисуем нового
            System.out.print("\033[" + (player.getcharacterX() + 1) + ";" + (player.getcharacterY() + 1) + "H");
            System.out.print('@');
            System.out.print("\033[" + (dungeon.length + 1) + ";1H");

            lastX = player.getcharacterX();
            lastY = player.getcharacterY();
        }
    }

    public static void firstposition(char[][] map, ArrayList<Room> maps) throws IOException {
        if (maps != null && !maps.isEmpty()) {
            Room firstroom = maps.get(0);
            int x = firstroom.getX() + firstroom.getHeight() / 2;
            int y = firstroom.getY() + firstroom.getWidth() / 2;
            player = new Character(x, y);
        }

    }
}