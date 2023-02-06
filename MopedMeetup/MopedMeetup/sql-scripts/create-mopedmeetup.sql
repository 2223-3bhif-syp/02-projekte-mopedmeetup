CREATE TABLE USER (
                            USER_ID               INT NOT NULL CONSTRAINT INSTRUCTOR_PK PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
                            FIRST_NAME                  VARCHAR(15),
                            LAST_NAME                   VARCHAR(25),
                            EMAIL                       VARCHAR(20)
);

CREATE TABLE MEETUP (
                      MEETUP_ID               INT NOT NULL CONSTRAINT INSTRUCTOR_PK PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
                      Location_ID             VARCHAR(15),
                      DESCRIPTION             VARCHAR(25),
                      CREATOR_ID              INT NOT NULL,
                      FOREIGN KEY (CREATOR_ID) REFERENCES USER(USER_ID),
                      FOREIGN KEY (CHAT_ID) REFERENCES CHAT(CHAT_ID)
);

CREATE TABLE LOCATION (
                      LOCATION_ID                 INT NOT NULL CONSTRAINT INSTRUCTOR_PK PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
                      COORDINATES                  VARCHAR(15)
);