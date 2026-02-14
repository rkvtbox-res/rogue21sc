package presentation;

import com.googlecode.lanterna.screen.Screen;

public class RenderState {
    private final Screen screen;

    public RenderState(Screen screen) {
        this.screen= screen;
    }

    // высота терминала в строках
    public int getWindowHeight() {
        return screen.getTerminalSize().getRows();
    }

    // ширина терминала
    public int getWindowWidth() {
        return  screen.getTerminalSize().getColumns();
    }

}
