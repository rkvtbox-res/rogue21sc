package controller;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import eventbus.EventBus;
import eventbus.Events;

import com.googlecode.lanterna.screen.Screen;
import model.ModelState;


import javax.swing.*;
import java.io.IOException;


public class Controller {
    private final EventBus bus;
    private final Screen screen;
    private final ModelState modelState;

    public Controller(EventBus bus, Screen screen, ModelState modelState) {
        this.bus = bus;
        this.screen = screen;
        this.modelState = modelState;
    }

    public void controller() throws IOException {
        if (modelState.getState() == ModelState.State.ENTER_NAME) {
            readLine();
        } else {
            readKey();
        }
    }

    public void readKey() throws IOException {
        ControllerCommands key = ControllerReadCommands.readInput(screen);
        if (key == null || key == ControllerCommands.NONE) return;

        bus.post(new Events.KeyPressed(key));
    }

    public void readLine() throws IOException {
        // считали значение
        KeyStroke keyStroke = ControllerReadString.readUserName(screen);
        if (keyStroke == null) return;

        // преобразовали в тип клавиши
        KeyType type = keyStroke.getKeyType();

        switch (type) {
            case Character -> {
                char ch = keyStroke.getCharacter();
                bus.post(new Events.EnteredNameAddChar(ch));
            }
            case Backspace -> bus.post(new Events.EnteredNameBackspace());
            case Enter -> bus.post(new Events.EnteredNameSubmit());
            //TODO Нужена ли дефолтная ветка?


        }
    }
}


