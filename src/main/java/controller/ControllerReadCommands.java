package controller;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;


public class ControllerReadCommands {

    public static ControllerCommands readInput(Screen screen) throws IOException {

        KeyStroke input = screen.pollInput();
        if (input == null) {
            return null;
        }


        if (input.getKeyType() != KeyType.Character) {
            return switch (input.getKeyType()) {
                case ArrowUp -> ControllerCommands.MOVE_UP;
                case ArrowDown -> ControllerCommands.MOVE_DOWN;
                case ArrowLeft -> ControllerCommands.MOVE_LEFT;
                case ArrowRight -> ControllerCommands.MOVE_RIGHT;
                case Enter -> ControllerCommands.ENTER;
                case Escape -> ControllerCommands.QUIT;
                default -> ControllerCommands.NONE;
            };
        }

        return switch (input.getCharacter()) {
            case 'w', 'W' -> ControllerCommands.MOVE_UP;
            case 's', 'S' -> ControllerCommands.MOVE_DOWN;
            case 'a', 'A' -> ControllerCommands.MOVE_LEFT;
            case 'd', 'D' -> ControllerCommands.MOVE_RIGHT;
            case 'i', 'I' -> ControllerCommands.INVENTORY;
            case 'e', 'E' -> ControllerCommands.READ;
            case 'h', 'H' -> ControllerCommands.WEAR_WEAPON;
            case 'j', 'J' -> ControllerCommands.EAT;
            case 'k', 'K' -> ControllerCommands.DRINK;
            case ' ' -> ControllerCommands.WAIT;

            default -> ControllerCommands.NONE;
        };

    }
}

