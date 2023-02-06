import java.util.List;

public class Meetup {
    int meetupID;
    User creator;
    List<User> participants;
    Location location;
    String description;

    public Meetup(User creator, Location location, String description) {
        this.creator = creator;
        this.location = location;
        this.description = description;
    }
}
