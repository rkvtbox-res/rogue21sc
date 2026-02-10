package domain;

import java.util.Random;

public class Room {
    private int height;
    private int width;
    private int x, y;

    public Room(int h, int w) {
        height = h;
        width = w;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }
          public char[][] printMap(int h, int w){
                char[][] massiv = new char[h][w];
                for(int i =0;  i< h; i++){
                    for(int j = 0; j < w; j++){
                        massiv[i][j] = '#';
                    }
                }
                return massiv;
          }
          public void room(char[][] massiv, int h, int w, int x, int y){
                for(int i = x; i < x+ h; i++){
                    for(int j = y; j< y + w; j++){
                        if(i >= 0 && i< massiv.length && j >=0 && j < massiv[0].length){
                            massiv[i][j] = '.';
                        }
                    }
                    }
                }
                public void randomRoom(char[][] massiv){
                    Random random = new Random();

                    int h = 3+random.nextInt(4);
                    int w = 3+ random.nextInt(4);
                    x = random.nextInt(massiv.length- w)  ;
                    y = random.nextInt(massiv.length - h) ;
                room(massiv,h, w, x, y);
                }
    public Boolean roompriverka(char[][] massiv, int h, int w, int x, int y){
        for(int i = x-1; i < x+ h+1; i++){
            for(int j = y-1; j< y + w+1; j++){
                if(i >= 0 && i< massiv.length && j >=0 && j < massiv.length){
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

            int roomsAdded = 0;
            int attempts = 0;

            while (roomsAdded < 9 && attempts < 100) {
                int x = random.nextInt(75);
                int y = random.nextInt(25);
                int w = 4 + random.nextInt(6);
                int h = 3 + random.nextInt(4);

                if (roompriverka(map, h, w, x, y)) {
                    room(map, h, w, x, y);
                    roomsAdded++;
                }
                attempts++;
            }

            return map;
        }
          }






