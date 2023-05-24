module mopedMeetup {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.apache.derby.client;
    requires org.apache.derby.commons;
    requires org.apache.derby.tools;
    requires org.mybatis;

    opens at.htl.meetup.view to javafx.fxml;
    exports at.htl.meetup.view;
    exports at.htl.meetup.controller;
    exports at.htl.meetup.entity;
}