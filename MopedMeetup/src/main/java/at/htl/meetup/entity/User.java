package at.htl.meetup.entity;


import java.time.LocalDateTime;

public class User {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private LocalDateTime dateOfBirth;

    public User(String firstName, String lastName, String password, String email, LocalDateTime dateOfBirth) {
        setFirstName(firstName);
        setLastName(lastName);
        setPassword(password);
        setEmail(email);
        setDateOfBirth(dateOfBirth);
    }
    public User(Long id, String firstName, String lastName, String password, String email, LocalDateTime dateOfBirth) {
        setId(id);
        setFirstName(firstName);
        setLastName(lastName);
        setPassword(password);
        setEmail(email);
        setDateOfBirth(dateOfBirth);
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

     public int getAge() {
         LocalDateTime age = LocalDateTime.now().minusYears(dateOfBirth.getYear()).minusMonths(dateOfBirth.getMonthValue()).minusDays(dateOfBirth.getDayOfMonth());
         return age.getYear();
     }

    public LocalDateTime getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDateTime dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                '}';
    }
}
