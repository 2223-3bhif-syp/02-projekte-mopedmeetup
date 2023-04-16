package at.htl.meetup.entities;


import org.apache.derby.client.am.DateTime;

import java.util.List;

public class Meetup {
    private Long id;
    private User creator;
    private Location location;
    private String description;

    private DateTime meetupDate;

    public Meetup() {
    }

    public Meetup(User creator, Location location, String description, DateTime meetupDate) {
        this.creator = creator;
        this.location = location;
        this.description = description;
        this.meetupDate = meetupDate;
    }
    public Meetup(Long id, User creator, Location location, String description, DateTime meetupDate) {
        this.id = id;
        this.creator = creator;
        this.location = location;
        this.description = description;
        this.meetupDate = meetupDate;
    }

    public DateTime getMeetupDate() {
        return meetupDate;
    }

    public void setMeetupDate(DateTime meetupDate) {
        this.meetupDate = meetupDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
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
