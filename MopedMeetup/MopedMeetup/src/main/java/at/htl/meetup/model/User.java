import java.util.List;

public class User {
    int userID;
    String firstName;
    String lastName;
    int age;
    String email;
    List<Meetup> Meetups;

    public User(String firstName, String lastName, int age, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.email = email;
    }
}
