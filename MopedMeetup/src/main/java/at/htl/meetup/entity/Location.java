package at.htl.meetup.entity;

public class Location {
    private Long id;

    private String street;
    private String city;
    private int zip;
    private String name;

    public Location() {

    }

    public Location(String name, String city, String street, int zip) {
        this.street = street;
        this.city = city;
        this.zip = zip;
        this.name = name;
    }
    public Location(Long id, String name, String city, String street, int zip) {
        this.id = id;
        this.street = street;
        this.city = city;
        this.zip = zip;
        this.name = name;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getZip() {
        return zip;
    }

    public void setZip(int zip) {
        this.zip = zip;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
