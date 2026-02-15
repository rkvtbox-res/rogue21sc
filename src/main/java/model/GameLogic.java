package model;

import controller.ControllerCommands;
import eventbus.EventBus;
import eventbus.Events;
import model.gamestate.GameState;

import java.util.Map;

public class GameLogic {
    private final EventBus bus;
    private GameState gameState;



    public GameLogic(EventBus bus) {
        this.bus = bus;
        initClass();

        bus.subscribe(Events.KeyPressed.class, e -> playerMove(e.value()));
    }

    private void initClass() {
        this.gameState = new GameState();
    }

    public GameState getGameState() {
        return gameState;
    }

    private void playerMove(ControllerCommands command) {
        switch (command) {
            case ControllerCommands.MOVE_UP -> gameState.getPlayerState().playerMoveUp();
            case ControllerCommands.MOVE_DOWN -> gameState.getPlayerState().playerMoveDown();
            case ControllerCommands.MOVE_RIGHT -> gameState.getPlayerState().playerMoveRight();
            case ControllerCommands.MOVE_LEFT -> gameState.getPlayerState().playerMoveLeft();
        }
        bus.post(new Events.RenderRefresh());
    }


}
