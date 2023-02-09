package at.htl.meetup.model;


import java.util.List;

public class Meetup {
    private int meetupID;
    private User creator;
    private List<User> participants;
    private Location location;
    private String description;

    public Meetup() {
    }

    public Meetup(User creator, Location location, String description) {
        this.creator = creator;
        this.location = location;
        this.description = description;
    }

    public int getMeetupID() {
        return meetupID;
    }

    public void setMeetupID(int meetupID) {
        this.meetupID = meetupID;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public List<User> getParticipants() {
        return participants;
    }

    public void setParticipants(List<User> participants) {
        this.participants = participants;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
