package application;

import dao.ClubDAOImplementation;
import dao.CoachDAOImplementation;
import dao.MatchDAOImplementation;
import dao.PlayerDAOImplementation;

public class Club {
    private final String clubId;
    private final String name;
    private final String locality;
    private final String voivodeship;
    private String stadiumName;
    private int stadiumCapacity;
    private static final ClubDAOImplementation clubDAO = new ClubDAOImplementation();
    private static final PlayerDAOImplementation playerDAO = new PlayerDAOImplementation();
    private static final MatchDAOImplementation matchDAO = new MatchDAOImplementation();
    private static final CoachDAOImplementation coachDAO = new CoachDAOImplementation();


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

    public String getName() {
        return name;
    }

    public String getLocality() {
        return locality;
    }

    public String getVoivodeship() {
        return voivodeship;
    }

    public String getStadiumName() {
        return stadiumName;
    }

    public int getStadiumCapacity() {
        return stadiumCapacity;
    }

}
