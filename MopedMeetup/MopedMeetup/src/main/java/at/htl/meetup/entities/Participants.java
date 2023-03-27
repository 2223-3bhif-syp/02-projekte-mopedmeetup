package at.htl.meetup.entities;

public class Participants {
    private int id;
    private User user;
    private Meetup meetup;

    public Participants(User user, Meetup meetup){
        this.user = user;
        this.meetup = meetup;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Meetup getMeetup() {
        return meetup;
    }

    public void setMeetup(Meetup meetup) {
        this.meetup = meetup;
    }
}
