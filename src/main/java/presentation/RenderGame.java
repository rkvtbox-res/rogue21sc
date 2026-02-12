package presentation;


import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import model.gamestate.GameState;

import java.io.IOException;

public class RenderGame {

   public static void render (Screen screen, GameState gameState) throws IOException {
       screen.clear();

       TextGraphics graphics = screen.newTextGraphics();

       graphics.putString(2, 1, gameState.getMapState().getDebugInfo());

       screen.refresh();
   }
}
