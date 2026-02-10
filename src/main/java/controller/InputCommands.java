package controller;

import jcurses.system.InputChar;
import jcurses.system.Toolkit;

public class InputCommands {

    public static ControlKeys readInput() {

        InputChar input = Toolkit.readCharacter();


        switch (input.getCharacter()) {
            case 'w' -> {
                return ControlKeys.MOVE_UP;
            }
            case 's', 'S' -> {
                return ControlKeys.MOVE_DOWN;
            }
            case 'a', 'A' -> {
                return ControlKeys.MOVE_LEFT;
            }
            case 'd', 'D' -> {
                return ControlKeys.MOVE_RIGHT;
            }

            case 'i', 'I' -> {
                return ControlKeys.INVENTORY;
            }

            case 'q' -> {
                return ControlKeys.QUIT;
            }


            default -> {
                return ControlKeys.NONE;
            }

        }


    }
}

