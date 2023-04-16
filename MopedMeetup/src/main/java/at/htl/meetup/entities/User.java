package at.htl.meetup.entities;


import java.time.LocalDateTime;
import java.util.List;

public class User {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private LocalDateTime birthday;

    public User(String firstName, String lastName, String email, LocalDateTime birthday) {
        setFirstName(firstName);
        setLastName(lastName);
        setEmail(email);
        setBirthday(birthday);
    }
    public User(Long id, String firstName, String lastName, String email, LocalDateTime birthday) {
        setId(id);
        setFirstName(firstName);
        setLastName(lastName);
        setEmail(email);
        setBirthday(birthday);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

     public int getAge() {
         LocalDateTime age = LocalDateTime.now().minusYears(birthday.getYear()).minusMonths(birthday.getMonthValue()).minusDays(birthday.getDayOfMonth());
         return age.getYear();
     }

    public LocalDateTime getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDateTime birthday) {
        this.birthday = birthday;
    }
}
