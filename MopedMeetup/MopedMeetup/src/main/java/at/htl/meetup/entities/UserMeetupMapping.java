package at.htl.meetup.entities;

public class UserMeetupMapping {
    private int UserId;
    private int MeetupId;

    public UserMeetupMapping(){

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
