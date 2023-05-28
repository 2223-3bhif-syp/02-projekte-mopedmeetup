package at.htl.meetup.entity;


public class User {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Integer age;

    public User(String firstName, String lastName, String password, String email, Integer age) {
        setFirstName(firstName);
        setLastName(lastName);
        setPassword(password);
        setEmail(email);
        setAge(age);
    }
    public User(Long id, String firstName, String lastName, String password, String email, Integer age) {
        setId(id);
        setFirstName(firstName);
        setLastName(lastName);
        setPassword(password);
        setEmail(email);
        setAge(age);
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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", age=" + age +
                '}';
    }
}
