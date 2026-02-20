package presentation;


import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import model.gamestate.GameState;
import model.generator.Cell;

import java.io.IOException;

public class RenderGame {

    public static void render(Screen screen, GameState gameState, RenderState rs, String userName) throws IOException {
        screen.clear();
        TextGraphics graphics = screen.newTextGraphics();

        //Заголовок и статы
        String title = "Rogue is " + userName;
        graphics.putString(rs.getWindowWidth() / 2 - title.length() / 2, rs.gameNameLine, title);

        // здоровье ловкость сила оружие
        graphics.putString(rs.getWindowWidth() / 2 - 10 - 5, rs.welcomePromptLine, "Agility");
        graphics.putString(rs.getWindowWidth() / 2 - 10 - 5, rs.welcomePromptLine + 1, String.valueOf(gameState.getPlayerState().getAgility()));


        graphics.putString(rs.getWindowWidth() / 2 + 10, rs.welcomePromptLine, "Strength");
        graphics.putString(rs.getWindowWidth() / 2 + 10, rs.welcomePromptLine + 1, String.valueOf(gameState.getPlayerState().getPlayerStrength()));


        graphics.putString(rs.getWindowWidth() / 2 - 36, rs.welcomePromptLine, "Health");
        graphics.putString(rs.getWindowWidth() / 2 - 36, rs.welcomePromptLine + 1, String.valueOf(gameState.getPlayerState().getPlayerHealth()) + " / " + String.valueOf(gameState.getPlayerState().getPlayerHealthMax()));


        graphics.putString(rs.getWindowWidth() / 2 + 30, rs.welcomePromptLine, "Weapon");
        graphics.putString(rs.getWindowWidth() / 2 + 30, rs.welcomePromptLine + 1, "Sword");

        // главное окно с картой
        // TODO сюда добавляем отрисовку карты, монстров и прочее
        for (int y = rs.borderWindowFirstLine; y <= rs.borderWindowLastLine(); y++) {
            if (y == rs.borderWindowFirstLine) {
                for (int x = 1; x < rs.getWindowWidth(); x++) {
                    if (x == 1) graphics.putString(x, y, "╔");
                    else if (x == rs.getWindowWidth() - 1) graphics.putString(x, y, "╗");
                    else graphics.putString(x, y, "═");
                }
            } else if (y == rs.borderWindowLastLine()) {
                for (int x = 1; x < rs.getWindowWidth(); x++) {
                    if (x == 1) graphics.putString(x, y, "╚");
                    else if (x == rs.getWindowWidth() - 1) graphics.putString(x, y, "╝");
                    else graphics.putString(x, y, "═");
                }
            } else {
                graphics.putString(1, y, "║");
                graphics.putString(rs.getWindowWidth() - 1, y, "║");
            }
        }

        // окно лога
        // TODO сюда дублируем текстом происходящее
        for (int y = rs.borderLogFirstLine(); y <= rs.borderLogLastLine(); y++) {
            if (y == rs.borderLogFirstLine()) {
                for (int x = 1; x < rs.getWindowWidth(); x++) {
                    if (x == 1) graphics.putString(x, y, "╔");
                    else if (x == rs.getWindowWidth() - 1) graphics.putString(x, y, "╗");
                    else graphics.putString(x, y, "═");
                }
            } else if (y == rs.borderLogLastLine()) {
                for (int x = 1; x < rs.getWindowWidth(); x++) {
                    if (x == 1) graphics.putString(x, y, "╚");
                    else if (x == rs.getWindowWidth() - 1) graphics.putString(x, y, "╝");
                    else graphics.putString(x, y, "═");
                }
            } else {
                graphics.putString(1, y, "║");
                graphics.putString(rs.getWindowWidth() - 1, y, "║");
            }
        }


        // карта

        Cell[][] map = gameState.getMapState().getMap();

        // Внутренние размеры окна карты (без рамки)
        int viewW = rs.getWindowWidth() - 4;
        int viewH = rs.borderWindowLastLine() - rs.borderWindowFirstLine - 1;

        // Где начинается отрисовка внутри рамки
        int screenStartX = 2;
        int screenStartY = rs.borderWindowFirstLine + 1;

        // Позиция игрока В КАРТЕ
        int playerX = gameState.getPlayerState().getPlayerPosX(); // колонка
        int playerY = gameState.getPlayerState().getPlayerPosY(); // строка

        // Центр экрана (куда “прибит” игрок)
        int centerX = viewW / 2;
        int centerY = viewH / 2;

        // Камера (левый верхний угол viewport на карте)
        int camX = playerX - centerX;
        int camY = playerY - centerY;

        // Ограничиваем камеру, чтобы не выйти за границы карты
        camX = clamp(camX, 0, map[0].length - viewW);
        camY = clamp(camY, 0, map.length - viewH);

        // 1) рисуем видимый кусок карты
        for (int sy = 0; sy < viewH; sy++) {
            for (int sx = 0; sx < viewW; sx++) {

                int mapY = camY + sy;
                int mapX = camX + sx;

                // защита если карта меньше окна
                if (mapY < 0 || mapY >= map.length || mapX < 0 || mapX >= map[0].length) {
                    graphics.putString(screenStartX + sx, screenStartY + sy, " ");
                    continue;
                }

                char ch = cellToChar(map[mapY][mapX]);
                graphics.putString(screenStartX + sx, screenStartY + sy, String.valueOf(ch));
            }
        }

        // 2) рисуем игрока поверх карты В ЦЕНТРЕ (или почти в центре у краёв)
        int playerScreenX = screenStartX + (playerX - camX);
        int playerScreenY = screenStartY + (playerY - camY);

        graphics.putString(playerScreenX, playerScreenY, rs.playerSymbol);

        screen.refresh();
    }

    private static char cellToChar(Cell cell) {
        return switch (cell) {
            case WALL -> '■';
            case FLOOR -> ' ';
            case CORRIDOR -> '□';
            case EMPTY -> '⋅';
            case DOOR -> '+';
            case STAIR_NEXT_LEVEL -> '>';
        };
    }

    private static int clamp(int v, int min, int max) {
        if (max < min) return min; // если карта меньше окна
        return Math.max(min, Math.min(max, v));
    }
}
