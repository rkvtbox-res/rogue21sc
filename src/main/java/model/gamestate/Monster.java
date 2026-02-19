package model.gamestate;


public class Monster {
    private enum monsterKind{
        ZOMBIE,
        VAMPIRE,
        GHOST,
        OGR,
        SNAKE_MAGICIAN;
    }


    private int health;
    private int strength;
    private int hostility;

    private int level; //коэффицент сложности

    private int posX;
    private int posY;




}
