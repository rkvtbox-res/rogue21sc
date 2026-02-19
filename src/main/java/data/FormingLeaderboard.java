package data;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FormingLeaderboard {

    private final List<Leader> leaderBoard = new ArrayList<>();

    private final String filePathOfLeaderboard = "./";
    private final String fileNameOfLeaderboard = "leaderboard.json";

    public List<Leader> getLeaderBoard() throws IOException {
        fileRead();
        return leaderBoard;
    }

    private void fileRead() throws IOException {

        File fileIn = new File(filePathOfLeaderboard + fileNameOfLeaderboard);

        // если файла нет, создаем
        if (!fileIn.exists()) {
            Files.writeString(Path.of(filePathOfLeaderboard + fileNameOfLeaderboard), "[]");
        }

        // читаем весь файл в одну строку
        String firstToString = Files.readString(Path.of(filePathOfLeaderboard + fileNameOfLeaderboard)).trim();
        if (firstToString.isEmpty() || firstToString.equals("[]")) {
            leaderBoard.clear();
            return;
        }


        // убираем [], пробелы и переносы строки
        firstToString = firstToString.substring(1, firstToString.length() - 1);
        firstToString = firstToString.replaceAll("\\s+", "");

        // разделяем на элементы
        String[] secondToArray = firstToString.split("\\},\\{");

        // очищаем старые данные, чтобы не было дубликатов
        leaderBoard.clear();


        for (int i = 0; i < secondToArray.length; i++) {
            // работаем с одним элементом
            // убираем кавычки и брейсы
            secondToArray[i] = secondToArray[i].replaceAll("\\{", "").replaceAll("\\}", "").replaceAll("\"", "");

            String[] thirdToElements = secondToArray[i].split("\\,");


            String playerName = "Unknown";
            int treasure = 0;
            int levelOfDungeon = 0;
            int countOfKills = 0;
            int countOfFood = 0;
            int countOfDrinks = 0;
            int countOfBooks = 0;
            int countOfMissedStrikes = 0;
            int countOfHit = 0;
            int countOfSteps = 0;
            boolean currentAttempt = false;

            for (int j = 0; j < thirdToElements.length; j++) {
                // работаем с одной записью
                int idx = thirdToElements[j].indexOf(":");
                String key = thirdToElements[j].substring(0, idx);
                String value = thirdToElements[j].substring(idx + 1);

                switch (key) {
                    case "playerName" -> playerName = value;
                    case "treasure" -> treasure = Integer.parseInt(value);
                    case "levelOfDungeon" -> levelOfDungeon = Integer.parseInt(value);
                    case "countOfKills" -> countOfKills = Integer.parseInt(value);
                    case "countOfFood" -> countOfFood = Integer.parseInt(value);
                    case "countOfDrinks" -> countOfDrinks = Integer.parseInt(value);
                    case "countOfBooks" -> countOfBooks = Integer.parseInt(value);
                    case "countOfMissedStrikes" -> countOfMissedStrikes = Integer.parseInt(value);
                    case "countOfHit" -> countOfHit = Integer.parseInt(value);
                    case "countOfSteps" -> countOfSteps = Integer.parseInt(value);
                    case "currentAttempt" -> currentAttempt = Boolean.parseBoolean(value);
                }


            }

            Leader leader = new Leader(
                    playerName,
                    treasure,
                    levelOfDungeon,
                    countOfKills,
                    countOfFood,
                    countOfDrinks,
                    countOfBooks,
                    countOfMissedStrikes,
                    countOfHit,
                    countOfSteps,
                    currentAttempt
            );

            leaderBoard.add(leader);



        }

    }


}