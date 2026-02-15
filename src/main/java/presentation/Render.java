package presentation;

import com.googlecode.lanterna.screen.Screen;
import eventbus.EventBus;
import eventbus.Events;
import model.gamestate.GameState;
import model.MainMenuState;
import model.ModelState;

import java.io.IOException;


public class Render {
    private final EventBus bus;
    private final Screen screen;
    private final MainMenuState menuState;
    private final ModelState modelState;
    private final GameState gameState;
    private final RenderState renderState;






    public Render (EventBus bus, Screen screen, MainMenuState menuState, ModelState modelState, GameState gameState) {
        this.bus = bus;
        this.screen = screen;
        this.menuState = menuState;
        this.modelState = modelState;
        this.gameState = gameState;
        this.renderState = new RenderState(this.screen);


        bus.subscribe(Events.RenderRefresh.class, e -> {
            try {
                render();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
    }



    public void render() throws IOException {
        switch (modelState.getState()) {
            case ENTER_NAME -> RenderTitle.render(screen, renderState, modelState.getUserName());
            case MENU -> RenderMenu.render(screen, menuState, renderState, modelState.getUserName());
            case GAME -> RenderGame.render(screen, gameState, renderState, modelState.getUserName());
            case GAME_PAUSE_MENU -> RenderPause.render(screen, menuState, gameState, renderState, modelState.getUserName());
            case QUIT -> {} // ничего не рисуем
        }
    }


}