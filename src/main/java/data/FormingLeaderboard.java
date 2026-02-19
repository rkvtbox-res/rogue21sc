package data;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public final class FormingLeaderboard {

    private static final String FILE_PATH = "./leaderboard.json";

    private FormingLeaderboard() {} // запрещаем создание объекта

    public static List<Leader> getLeaderBoard() throws IOException {
        return fileRead();
    }

    private static List<Leader> fileRead() throws IOException {

        Path path = Path.of(FILE_PATH);
        File fileIn = path.toFile();

        // если файла нет — создаём пустой массив
        if (!fileIn.exists()) {
            Files.writeString(path, "[]");
        }

        String json = Files.readString(path).trim();
        List<Leader> leaderBoard = new ArrayList<>();

        if (json.isEmpty() || json.equals("[]")) {
            return leaderBoard;
        }

        // убираем []
        json = json.substring(1, json.length() - 1);

        // убираем пробелы и переносы
        json = json.replaceAll("\\s+", "");

        // делим на объекты
        String[] objects = json.split("\\},\\{");

        for (String object : objects) {

            object = object
                    .replace("{", "")
                    .replace("}", "")
                    .replace("\"", "");

            String[] fields = object.split(",");

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

            for (String field : fields) {

                int idx = field.indexOf(":");
                if (idx == -1) continue;

                String key = field.substring(0, idx);
                String value = field.substring(idx + 1);

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

            leaderBoard.add(new Leader(
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
            ));
        }

        return leaderBoard;
    }


    public static void saveLeaderBoard(List<Leader> leaderboard) throws IOException {

        // оставить максимум 10 (список уже отсортирован)
        int limit = Math.min(10, leaderboard.size());
        List<Leader> top10 = leaderboard.subList(0, limit);

        StringBuilder json = new StringBuilder();
        json.append("[\n");

        for (int i = 0; i < top10.size(); i++) {
            json.append(top10.get(i).toString());

            if (i < top10.size() - 1) {
                json.append(",");
            }
            json.append("\n");
        }

        json.append("]");

        Files.writeString(Path.of(FILE_PATH), json.toString());
    }
}
