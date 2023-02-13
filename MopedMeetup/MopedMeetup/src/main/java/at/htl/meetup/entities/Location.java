package at.htl.meetup.entities;

public class Location {
    private int locationID;
    private String adress;
    private String name;

    public Location() {

    }

    public Location(String adress) {
        this.adress = adress;
    }

    public int getLocationID() {
        return locationID;
    }

    public void setLocationID(int locationID) {
        this.locationID = locationID;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
