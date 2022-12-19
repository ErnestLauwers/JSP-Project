CREATE SEQUENCE groep2b5.project_id_seq;

GRANT ALL ON SEQUENCE groep2b5.project_id_seq TO local_r0848777;

CREATE TABLE groep2b5.projects
(   projectid integer NOT NULL DEFAULT nextval('groep2b5.project_id_seq'::regclass),
    name character varying COLLATE pg_catalog."default" NOT NULL,
    team character varying COLLATE pg_catalog."default" NOT NULL,
    startdate timestamp without time zone NOT NULL,
    enddate timestamp without time zone,
    CONSTRAINT project_pkey PRIMARY KEY (projectid)
);

GRANT ALL ON TABLE groep2b5.projects TO local_r0848777;

INSERT INTO groep2b5.projects ("name", "team", "startdate", "enddate") values ('First', 'Alpha', '13/03/2023', '21/03/2024');
INSERT INTO groep2b5.projects ("name", "team", "startdate", "enddate") values ('Second', 'Beta', '15/03/2023', '18/05/2024');
INSERT INTO groep2b5.projects ("name", "team", "startdate", "enddate") values ('Third', 'Gamma', '17/09/2023', '17/10/2023');

-- grant aan lectoren
GRANT ALL ON SEQUENCE groep2b5.project_id_seq TO local_u0015529;
GRANT ALL ON TABLE groep2b5.projects TO local_u0015529;
GRANT ALL ON SEQUENCE groep2b5.project_id_seq TO local_u0034562;
GRANT ALL ON TABLE groep2b5.projects TO local_u0034562;

-- grant aan teamgenoot
GRANT ALL ON SEQUENCE groep2b5.project_id_seq TO local_r0903727;
GRANT ALL ON TABLE groep2b5.projects TO local_r0903727;