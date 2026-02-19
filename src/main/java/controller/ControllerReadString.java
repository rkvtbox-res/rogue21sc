package controller;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;


import java.io.IOException;

public class ControllerReadString {

    public static KeyStroke readUserName(Screen screen) throws IOException {
        KeyStroke input = screen.pollInput();
        if (input == null) {
            return null;
        }

        return input;
    }


}
