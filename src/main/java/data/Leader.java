package data;

public class Leader {
    private String playerName;
    private int treasure;
    private int levelOfDungeon;
    private int countOfKills;
    private int countOfFood;
    private int countOfDrinks;
    private int countOfBooks;
    private int countOfMissedStrikes;
    private int countOfHit;
    private int countOfSteps;
    private boolean currentAttempt;

    public Leader(String playerName, int treasure, int levelOfDungeon, int countOfKills, int countOfFood,
                  int countOfDrinks, int countOfBooks, int countOfMissedStrikes, int countOfHit, int countOfSteps,
                  boolean currentAttempt) {
        this.playerName = playerName;
        this.treasure = treasure;
        this.levelOfDungeon = levelOfDungeon;
        this.countOfKills = countOfKills;
        this.countOfFood = countOfFood;
        this.countOfDrinks = countOfDrinks;
        this.countOfBooks = countOfBooks;
        this.countOfMissedStrikes = countOfMissedStrikes;
        this.countOfHit = countOfHit;
        this.countOfSteps = countOfSteps;
        this.currentAttempt = currentAttempt;

    }

    @Override
    public String toString() {
        return String.format("" +
                        "  {\n" +
                        "    \"playerName\": \"%s\",\n" +
                        "    \"treasure\": %d,\n" +
                        "    \"levelOfDungeon\": %d,\n" +
                        "    \"countOfKills\": %d,\n" +
                        "    \"countOfFood\": %d,\n" +
                        "    \"countOfDrinks\": %d,\n" +
                        "    \"countOfBooks\": %d,\n" +
                        "    \"countOfMissedStrikes\": %d,\n" +
                        "    \"countOfHit\": %d,\n" +
                        "    \"countOfSteps\": %d,\n" +
                        "    \"currentAttempt\": %b\n" +
                        "  }",
                playerName, treasure, levelOfDungeon, countOfKills, countOfFood, countOfDrinks,
                countOfBooks, countOfMissedStrikes, countOfHit, countOfSteps, currentAttempt);
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }
    public String getPlayerName() {
        return playerName;
    }
    public void setTreasure(int treasure) {
        this.treasure = treasure;
    }
    public int getTreasure() {
        return treasure;
    }

    public int getLevelOfDungeon() {
        return levelOfDungeon;
    }

    public void setLevelOfDungeon(int levelOfDungeon) {
        this.levelOfDungeon = levelOfDungeon;
    }

    public int getCountOfKills() {
        return countOfKills;
    }

    public void setCountOfKills(int countOfKills) {
        this.countOfKills = countOfKills;
    }

    public int getCountOfFood() {
        return countOfFood;
    }

    public void setCountOfFood(int countOfFood) {
        this.countOfFood = countOfFood;
    }

    public int getCountOfDrinks() {
        return countOfDrinks;
    }

    public void setCountOfDrinks(int countOfDrinks) {
        this.countOfDrinks = countOfDrinks;
    }

    public int getCountOfBooks() {
        return countOfBooks;
    }

    public void setCountOfBooks(int countOfBooks) {
        this.countOfBooks = countOfBooks;
    }

    public int getCountOfMissedStrikes() {
        return countOfMissedStrikes;
    }

    public void setCountOfMissedStrikes(int countOfMissedStrikes) {
        this.countOfMissedStrikes = countOfMissedStrikes;
    }

    public int getCountOfHit() {
        return countOfHit;
    }

    public void setCountOfHit(int countOfHit) {
        this.countOfHit = countOfHit;
    }

    public int getCountOfSteps() {
        return countOfSteps;
    }

    public void setCountOfSteps(int countOfSteps) {
        this.countOfSteps = countOfSteps;
    }

    public boolean isCurrentAttempt() {
        return currentAttempt;
    }

    public void setCurrentAttempt(boolean currentAttempt) {
        this.currentAttempt = currentAttempt;
    }
}
