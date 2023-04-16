CREATE TABLE mm_USER (
                        U_ID           INT NOT NULL CONSTRAINT U_PK PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
                        U_FIRST_NAME        VARCHAR(15),
                        U_LAST_NAME         VARCHAR(25),
                        U_EMAIL             VARCHAR(30),
                        U_DATE_OF_BIRTH     DATE
);

CREATE TABLE mm_LOCATION (
                             L_ID       INT NOT NULL CONSTRAINT L_PK PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
                             L_STREET            VARCHAR(50),
                             L_CITY              VARCHAR(20),
                             L_ZIP              INT,
                             L_NAME              VARCHAR(20)
);

CREATE TABLE mm_MEETUP (
                           M_ID         INT NOT NULL CONSTRAINT M_PK PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
                           M_DESCRIPTION       VARCHAR(200),
                           M_MEETUP_DATE       DATE,
                           M_U_ID        INT CONSTRAINT M_U_FK REFERENCES mm_USER(U_ID),
                           M_L_ID       INT CONSTRAINT M_L_FK REFERENCES mm_LOCATION(L_ID)
);

CREATE TABLE mm_PARTICIPANTS(
           P_ID             INT NOT NULL CONSTRAINT P_PK PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
           P_U_ID           INT CONSTRAINT U_FK REFERENCES mm_USER(U_ID),
           P_M_ID           INT CONSTRAINT M_FK REFERENCES mm_MEETUP(M_ID)
);