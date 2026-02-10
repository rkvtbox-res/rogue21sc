package org.example;

import domain.Room;

public class Main {
    public static void main(String[] args) {
        Room roomGenerator = new Room(0, 0);
        char[][] dungeon = roomGenerator.sborka();
        for(int y = 0; y < dungeon[0].length; y++) {
            for(int x = 0; x < dungeon.length; x++) {
                System.out.print(dungeon[x][y]);
            }
            System.out.println();
        }
        System.out.println(dungeon.length);
    }
}