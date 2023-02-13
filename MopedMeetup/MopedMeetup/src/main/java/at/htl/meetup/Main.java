package at.htl.meetup;

import at.htl.meetup.database.SqlRunner;

public class Main {
    public static void main(String[] args) {
        SqlRunner.dropAndCreateTablesWithExampleData();
    }
}
