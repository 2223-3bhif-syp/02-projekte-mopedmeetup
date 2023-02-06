package at.htl.meetup.model;


import java.util.List;

public class User {
    private int userID;
    private String firstName;
    private String lastName;
    private int age;
    private String email;
    private List<Meetup> meetups;

    public User(String firstName, String lastName, int age, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.email = email;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Meetup> getMeetups() {
        return meetups;
    }

    public void setMeetups(List<Meetup> meetups) {
        this.meetups = meetups;
    }
}
