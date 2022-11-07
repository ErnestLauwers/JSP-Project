-- EEN teamgenoot voert deze code uit

-- vervang naam van schema door je eigen groepsnaam
-- groep102 staat voor groep1-1

-- vervang local_r012345 door je eigen r-nummer

-- vergeet je teamgenoot geen grants te geven, cfr grants aan lectoren

CREATE SCHEMA groep2b5;

GRANT ALL ON SCHEMA groep2b5 TO local_r0848777;

CREATE SEQUENCE groep2b5.user_id_seq;

GRANT ALL ON SEQUENCE groep2b5.user_id_seq TO local_r0848777;

CREATE TABLE groep2b5.users
(   userid integer NOT NULL DEFAULT nextval('groep2b5.user_id_seq'::regclass),
    email character varying COLLATE pg_catalog."default" NOT NULL,
    password character varying COLLATE pg_catalog."default" NOT NULL,
    firstname character varying COLLATE pg_catalog."default" NOT NULL,
    lastname character varying COLLATE pg_catalog."default" NOT NULL,
    team character varying COLLATE pg_catalog."default" NOT NULL,
    role character varying COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT user_pkey PRIMARY KEY (userid)
);

GRANT ALL ON TABLE groep2b5.users TO local_r0848777;

INSERT INTO groep2b5.users ("email","password","firstname","lastname","team","role") values ('director@ucll.be', 't', 'Andrew', 'Johnson', 'ALPHA', 'DIRECTOR');
INSERT INTO groep2b5.users ("email","password","firstname","lastname","team","role") values ('bart.smith@gmail.com', 's', 'Bart', 'Smith', 'GAMMA', 'TEAMLEADER');
INSERT INTO groep2b5.users ("email","password","firstname","lastname","team","role") values ('sarah.jones@gmail.com', 'u', 'Sarah', 'Jones', 'EPSILON', 'EMPLOYEE');


-- grant aan lectoren
GRANT ALL ON SCHEMA groep2b5 TO local_u0015529;
GRANT ALL ON SEQUENCE groep2b5.user_id_seq TO local_u0015529;
GRANT ALL ON TABLE groep2b5.users TO local_u0015529;

GRANT ALL ON SCHEMA groep2b5 TO local_u0034562;
GRANT ALL ON SEQUENCE groep2b5.user_id_seq TO local_u0034562;
GRANT ALL ON TABLE groep2b5.users TO local_u0034562;

-- grant aan teamgenoot
GRANT ALL ON SCHEMA groep2b5 TO local_r0903727;
GRANT ALL ON SEQUENCE groep2b5.user_id_seq TO local_r0903727;
GRANT ALL ON TABLE groep2b5.users TO local_r0903727;