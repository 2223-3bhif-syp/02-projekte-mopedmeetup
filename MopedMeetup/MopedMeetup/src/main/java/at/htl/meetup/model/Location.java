package at.htl.meetup.model;

public class Location {
    private int locationID;
    private String coordinates;

    public Location() {
    }

    public Location(String coordinates) {
        this.coordinates = coordinates;
    }

    public int getLocationID() {
        return locationID;
    }

    public void setLocationID(int locationID) {
        this.locationID = locationID;
    }

    public String getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(String coordinates) {
        this.coordinates = coordinates;
    }
}
