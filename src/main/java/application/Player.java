package application;

import java.util.Date;

public class Player {
    private final String playerId;
    private Club clubId;
    private final String fName;
    private final String lName;
    private final Date birthDate;
    private String nationality;
    private Date contractValidity;
    private String nominalPosition;

    public Player(String playerId, Club clubId, String fName, String lName, Date birthDate, String nationality, Date contractValidity, String nominalPosition) {
        this.playerId = playerId;
        this.clubId = clubId;
        this.fName = fName;
        this.lName = lName;
        this.birthDate = birthDate;
        this.nationality = nationality;
        this.contractValidity = contractValidity;
        this.nominalPosition = nominalPosition;
    }

    public String getPlayerId() {
        return playerId;
    }

}
