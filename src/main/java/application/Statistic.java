package application;

public class Statistic {
    private final String statId;
    private final Match matchID;
    private final int gameweek;
    private final Player playerId;
    private final Club rivalId;
    private int minutes;
    private int goals;
    private int assists;
    private byte cleanSheets;
    private byte ownGoals;
    private byte yellowCards;
    private byte redCards;

    public Statistic(String statId, Match matchID, int gameweek, Player playerId, Club rivalId, int minutes, int goals, int assists,
                     byte cleanSheets, byte ownGoals, byte yellowCards, byte redCards) {
        this.statId = statId;
        this.matchID = matchID;
        this.gameweek = gameweek;
        this.playerId = playerId;
        this.rivalId = rivalId;
        this.minutes = minutes;
        this.goals = goals;
        this.assists = assists;
        this.cleanSheets = cleanSheets;
        this.ownGoals = ownGoals;
        this.yellowCards = yellowCards;
        this.redCards = redCards;
    }
}
