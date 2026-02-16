package domain;

import java.util.ArrayList;
import java.io.IOException;


public class Character {
    private int maxHealth;//
    private int Health;//
    private int agiliti;//ловкость
    private int strength; //сила
    private String currentWeapon; //текущее оружие
    private int characterX;
    private int characterY;
    private char character = '@';


     public Character(int x, int y) throws IOException {
         characterX = x;
         characterY = y;
    }

    public int getcharacterX() { return characterX; }
    public int getcharacterY() { return characterY; }


 public void movecharacter(int x, int y, char[][] map){
        int nowX = x + characterX;
     int nowY = y + characterY;
       if(nowX < 0 || nowX >= map.length || nowY < 0 || nowY >= map[0].length){
           return;
       }
       if(map[nowX][nowY] == '.'){
           characterX = nowX;
           characterY = nowY;
       }



 }


}

