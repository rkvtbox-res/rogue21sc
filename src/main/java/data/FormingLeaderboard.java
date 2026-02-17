package data;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FormingLeaderboard {

    //  читаем из файла, пишем в список лидеров - каждый элемент объект
    // читаем из списка, пишем в файл (перед этим добавляем новый элемент, сортируем
    // вывод показываем последние 10 лидеров
    // количество сокровищ, достигнутый уровень, количество побежденных противников, количество съеденной еды, количество выпитых эликсиров, количество прочитанных свитков, количество нанесенных и пропущенных ударов, количество пройденных клеток.

    // нужен класс проверяющий существование файла с таблицей - если нет создаем новый
    private final List<Leader> leaderBoard = new ArrayList<>();

    private final String filePathOfLeaderboard = "./";
    private final String fileNameOfLeaderboard = "leaderboard.json";

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
        firstToString = firstToString.substring(1, firstToString.length()-1);
        firstToString = firstToString.replaceAll("\\s+", "");

    // разделяем на элементы
        String[] secondToArray = firstToString.split("\\},\\{");

        for (int i= 0; i < secondToArray.length; i++) {
            // работаем с одним элементом
            // убираем кавычки и брейсы
            secondToArray[i] = secondToArray[i].replaceAll("\\{", "").replaceAll("\\}", "").replaceAll("\"", "");

            String[] thirdToElements = secondToArray[i].split("\\,");

            for (int j = 0; j < thirdToElements.length; j++) {
                // работаем с одной записью
                int idx = thirdToElements[j].indexOf(":");
                String key = thirdToElements[j].substring(0, idx);
                String value = thirdToElements[j].substring(idx + 1);

            }


        }




    }




}