CREATE TABLE mm_USER (
                        USER_ID           INT NOT NULL CONSTRAINT USER_PK PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
                        FIRST_NAME        VARCHAR(15),
                        LAST_NAME         VARCHAR(25),
                        EMAIL             VARCHAR(30)
);


CREATE TABLE mm_LOCATION (
                          LOCATION_ID       INT NOT NULL CONSTRAINT LOCATION_PK PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
                          ADRESS       VARCHAR(15),
                          NAME              VARCHAR(20)
);

CREATE TABLE mm_MEETUP (
                           MEETUP_ID         INT NOT NULL CONSTRAINT MEETUP_PK PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
                           DESCRIPTION       VARCHAR(200),
                           CREATOR_ID        INT CONSTRAINT MEETUP_USER_FK REFERENCES mm_USER(USER_ID),
                           Location_ID       INT CONSTRAINT MEETUP_LOCATION_FK REFERENCES mm_LOCATION(LOCATION_ID)
);

CREATE TABLE mm_USER_MEETUP_MAPPING(
           LOCATION_ID       INT NOT NULL CONSTRAINT MM_USER_MEETUP_MAPPING_PK PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
           USER_ID         INT CONSTRAINT USER_FK REFERENCES mm_USER(USER_ID),
           MEETUP_ID       INT CONSTRAINT MEETUP_FK REFERENCES mm_LOCATION(LOCATION_ID)
);