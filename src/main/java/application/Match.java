package application;

import java.util.Date;

public class Match {
    private final int gameweek;
    private final String matchId;
    private final Date date;
    private final Club hostID;
    private final Club guestID;
    private final int goalsHost;
    private final int goalsGuest;
    private final Referee referee;
    private int attendance;

    public Match(int gameweek, String matchId, Date date, Club hostID, Club guestID, int goalsHost, int goalsGuest, Referee referee, int attendance) {
        this.gameweek = gameweek;
        this.matchId = matchId;
        this.date = date;
        this.hostID = hostID;
        this.guestID = guestID;
        this.goalsHost = goalsHost;
        this.goalsGuest = goalsGuest;
        this.referee = referee;
        this.attendance = attendance;
    }

    public String getMatchId() {
        return matchId;
    }

    public int getGameweek() {
        return gameweek;
    }

    public Date getDate() {
        return date;
    }

    public Club getHostID() {
        return hostID;
    }

    public Club getGuestID() {
        return guestID;
    }

    public int getGoalsHost() {
        return goalsHost;
    }

    public int getGoalsGuest() {
        return goalsGuest;
    }

    public Referee getReferee() {
        return referee;
    }

    public int getAttendance() {
        return attendance;
    }
}
