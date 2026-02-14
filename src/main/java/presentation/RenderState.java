package presentation;

import com.googlecode.lanterna.screen.Screen;

public class RenderState {
    private final Screen screen;

    // базовая информация
    public final String gameName = "Rogue";
    public final String welcomePrompt = " welcome to adventure!";

    // рамки
    public final String windowBorderStright = "═";
    public final String windowBorderCornerUpLeft = "╔";
    public final String windowBorderCornerUpRight = "╗";
    public final String windowBorderCornerDownLeft = "╚";
    public final String windowBorderCornerDownRight = "╝";
    public final String windowBorder = "║";

    //Позиция меню
    public final int gameNameLine = 1;
    public final int welcomePromptLine = 2;
    public final int borderWindowFirstLine = 4;



    public RenderState(Screen screen) {
        this.screen= screen;
    }

    // высота терминала в строках
    public int getWindowHeight() {
        return screen.getTerminalSize().getRows();
    }

    // ширина терминала
    public int getWindowWidth() {
        return screen.getTerminalSize().getColumns();
    }

    public int borderWindowLastLine () {
        return getWindowHeight() - 11;
    }

    public int borderLogFirstLine() {
        return getWindowHeight();
    }

    public int borderLogLastLine () {
        return getWindowHeight() - 10;
    }

}
