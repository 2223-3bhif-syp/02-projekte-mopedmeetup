package at.htl.meetup.entity;

public class Participants {
    private Long id;
    private User user;
    private Meetup meetup;

    public Participants(User user, Meetup meetup){
        this.user = user;
        this.meetup = meetup;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
