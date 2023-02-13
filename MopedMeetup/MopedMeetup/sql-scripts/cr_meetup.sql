CREATE TABLE "USER" (
                        USER_ID           INT NOT NULL CONSTRAINT USER_PK PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
                        FIRST_NAME        VARCHAR(15),
                        LAST_NAME         VARCHAR(25),
                        EMAIL             VARCHAR(30)
);

CREATE TABLE MEETUP (
                        MEETUP_ID         INT NOT NULL CONSTRAINT MEETUP_PK PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
                        DESCRIPTION       VARCHAR(200),
                        CREATOR_ID        INT CONSTRAINT MEETUP_USER_FK REFERENCES "USER"(USER_ID),
                        Location_ID       INT CONSTRAINT MEETUP_LOCATION_FK REFERENCES LOCATION(LOCATION_ID)
);

CREATE TABLE LOCATION (
                          LOCATION_ID       INT NOT NULL CONSTRAINT LOCATION_PK PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
                          COORDINATES       VARCHAR(15)
);

--CREATE TABLE USER_MEETUP_MAPPING