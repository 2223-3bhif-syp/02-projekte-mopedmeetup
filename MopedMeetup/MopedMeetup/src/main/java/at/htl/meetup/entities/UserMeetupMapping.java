package at.htl.meetup.entities;

public class UserMeetupMapping {

    private int id;
    private int UserId;
    private int MeetupId;

    public UserMeetupMapping(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int userId) {
        UserId = userId;
    }

    public int getMeetupId() {
        return MeetupId;
    }

    public void setMeetupId(int meetupId) {
        MeetupId = meetupId;
    }
}
