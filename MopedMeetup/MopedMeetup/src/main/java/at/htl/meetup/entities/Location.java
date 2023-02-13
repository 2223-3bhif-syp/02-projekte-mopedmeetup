package at.htl.meetup.entities;

public class Location {
    private int id;
    private String address;
    private String name;

    public Location() {

    }

    public Location(String address) {
        this.address = address;
    }

    public Location(int id, String address, String name) {
        this.id = id;
        this.address = address;
        this.name = name;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
