public class Team {
    private String name;
    private int matchCounter;
    private int winCounter;
    private int drawCounter;
    private int lostCounter;
    private int goalsScored;
    private int goalsConceded;
    private int points;
    private String form = "";

    public Team(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getMatchCounter() {
        return matchCounter;
    }

    public int getWinCounter() {
        return winCounter;
    }

    public int getDrawCounter() {
        return drawCounter;
    }

    public int getLostCounter() {
        return lostCounter;
    }

    public int getGoalsScored() {
        return goalsScored;
    }

    public int getGoalsConceded() {
        return goalsConceded;
    }

    public int getPoints() {
        return points;
    }

    public String getForm() {
        return form;
    }

    public void addPoints(int howMany) {
        points += howMany;
    }

    public void addGame() {
        matchCounter++;
    }

    public void addGoalScored(int goals) {
        goalsScored += goals;
    }

    public void addGoalConceded(int goals) {
        goalsConceded += goals;
    }

    public void addWin() {
        winCounter++;
    }

    public void addDraw() {
        drawCounter++;
    }

    public void addLost() {
        lostCounter++;
    }

    public void addForm(String text) {
        form = text + " " +form;
    }
}
