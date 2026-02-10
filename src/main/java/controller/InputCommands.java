package controller;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;


public class InputCommands {

    public static ControlKeys readInput(Screen screen) throws IOException {

        KeyStroke input = screen.pollInput();
        if (input == null) {
            return null;
        }

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

