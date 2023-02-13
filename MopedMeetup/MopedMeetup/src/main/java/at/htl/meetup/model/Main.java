package at.htl.meetup.model;

import at.htl.meetup.database.SqlRunner;

public class Main {
    public static void main(String[] args) {
        SqlRunner.dropAndCreateTablesWithExampleData();
    }
}
