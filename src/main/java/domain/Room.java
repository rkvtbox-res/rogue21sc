package domain;

import java.util.ArrayList;
import java.util.Random;

public class Room {
    private int height;
    private int width;
    private int x, y;
    private ArrayList<Room> rooms; // Добавить поле

    public ArrayList<Room> getRooms() {
        return rooms;
    }
    public Room(int h, int w, int xs, int ys) {
        height = h;
        width = w;
        x = xs;
        y = ys;

    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

          public char[][] printMap(int width, int height){

                char[][] massiv = new char[height][width];
                for(int i =0;  i< height; i++) {
                    for (int j = 0; j < width; j++) {
                        if (i == 0 || i == height - 1 || j == 0 || j == width - 1) {
                            massiv[i][j] = '*';
                        } else {
                            massiv[i][j] = ' ';
                        }
                    }
                }
                return massiv;
          }
          public void room(char[][] massiv, int h, int w, int x, int y) {
              for (int i = x; i < x + h; i++) {
                  for (int j = y; j < y + w; j++) {
                      if (i >= 0 && i < massiv.length && j >= 0 && j < massiv[0].length) {
                          massiv[i][j] = '.';
                      }
                  }
              }
              for (int i = x - 1; i <= x + h; i++) {
                  for (int j = y - 1; j <= y + w; j++) {
                      if (i >= 0 && i < massiv.length && j >= 0 && j < massiv[0].length)
                      if (i == x - 1 || i == x+ h || j == y - 1 || j == y + w)
                          massiv[i][j] = '*';
                      }
                  }

          }
                public void randomRoom(char[][] massiv){
                    Random random = new Random();

                    int h = 3+random.nextInt(4);
                    int w = 3+ random.nextInt(4);
                    x = random.nextInt(massiv.length- h - 3 )+ 3   ;
                    y = random.nextInt(massiv[0].length - w - 3)+ 3;
                room(massiv,h, w, x, y);
                }
    public Boolean roompriverka(char[][] massiv, int h, int w, int x, int y){
        for(int i = x-4; i < x+ h+4; i++){
            for(int j = y-4; j< y + w+4; j++){
                if(i >= 0 && i< massiv.length && j >=0 && j < massiv[0].length){
                  if(massiv[i][j] == '.'){
                      return false;
                  }
                }
            }
        }
        return true;
    }



        public char[][] sborka(){
        char[][] map = printMap(80, 30);
        Random random = new Random();
            ArrayList<Room> maps = new ArrayList<>();
            int roomsAdded = 0;
            int attempts = 0;

            while (roomsAdded < 9 && attempts < 100) {
                int w = 3 + random.nextInt(6);
                int h = 3 + random.nextInt(6);
                int x = random.nextInt(map.length - h - 3) + 3;
                int y = random.nextInt(map[0].length - w - 3) +3;

                if (roompriverka(map, h, w, x, y)) {
                    room(map, h, w, x, y);
                    roomsAdded++;
                    maps.add(new Room(h, w, x, y));
                }
                attempts++;
            }
            Koridor koridor = new Koridor();
        koridor.koridor(maps, map);
            this.rooms = maps;
            return map;
        }
          }






