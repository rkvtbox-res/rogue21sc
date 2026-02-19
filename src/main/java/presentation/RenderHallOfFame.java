package presentation;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import data.Leader;
import model.ModelState;

import java.io.IOException;
import java.util.List;

public class RenderHallOfFame {
    public static void render(Screen screen, RenderState rs, ModelState modelState) throws IOException {
        screen.clear();
        TextGraphics graphics = screen.newTextGraphics();

        String welcomePrompt = "Hall of Fame";
        List<Leader> leaderBoard = modelState.getLeaderBoard();


        graphics.putString(rs.getWindowWidth() / 2 - rs.gameName.length() / 2, rs.gameNameLine, rs.gameName);
        graphics.putString(rs.getWindowWidth() / 2 - welcomePrompt.length() / 2, rs.welcomePromptLine, welcomePrompt);

        // Рисуем рамку
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

        //TODO добавить поля в таблицу лидеров

        String leaderTitle = String.format(
                "%-20s %-10s",
                "Player",
                "Treasure"
        );
        graphics.putString( 10,rs.borderWindowFirstLine + 5,  leaderTitle);

        // Выводим лидеров
        for (int i = 0; i < leaderBoard.size(); i++) {
            if (leaderBoard.get(i).isCurrentAttempt()) { // Пропускаем пустые строки (если есть)
                String currentAtt = "✪ ";
                String leader = String.format(
                        "%s %-20s %-10d",
                        currentAtt,
                        leaderBoard.get(i).getPlayerName(),
                        leaderBoard.get(i).getTreasure()
                );
                graphics.putString(7, rs.borderWindowFirstLine + 7 + i, leader);
            } else {
                String leader = String.format(
                        "%-20s %-10d",
                        leaderBoard.get(i).getPlayerName(),
                        leaderBoard.get(i).getTreasure()
                );
                graphics.putString(10, rs.borderWindowFirstLine + 7 + i, leader);
            }
        }




        screen.refresh();
    }

}
