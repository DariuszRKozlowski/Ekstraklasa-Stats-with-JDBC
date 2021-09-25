package application;

public class Club {
    private final String clubId;
    private final String name;
    private final String locality;
    private final String voivodeship;
    private String stadiumName;
    private int stadiumCapacity;

    public Club(String clubId, String name, String locality, String voivodeship, String stadiumName, int stadiumCapacity) {
        this.clubId = clubId;
        this.name = name;
        this.locality = locality;
        this.voivodeship = voivodeship;
        this.stadiumName = stadiumName;
        this.stadiumCapacity = stadiumCapacity;
    }

    public String getClubId() {
        return clubId;
    }

}
