CREATE SEQUENCE groep2b5.workorder_id_seq;

GRANT ALL ON SEQUENCE groep2b5.workorder_id_seq TO local_r0903727;

CREATE TABLE groep2b5.workorders
(   workorderid integer NOT NULL DEFAULT nextval('groep2b5.workorder_id_seq'::regclass),
    name character varying COLLATE pg_catalog."default" NOT NULL,
    team character varying COLLATE pg_catalog."default" NOT NULL,
    date timestamp without time zone NOT NULL,
    starttime time NOT NULL,
    endtime time NOT NULL,
    description character varying COLLATE pg_catalog."default" NOT NULL,
    userid int NOT NULL,
    CONSTRAINT workorder_pkey PRIMARY KEY (workorderid)
);

GRANT ALL ON TABLE groep2b5.workorders TO local_r0903727;

INSERT INTO groep2b5.workorders ("name","team","date","starttime","endtime","description", "userid") values ('Anna Peters', 'Alpha', '21/09/2022', '09:00:00', '10:00:00', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit', 3);
INSERT INTO groep2b5.workorders ("name","team","date","starttime","endtime","description", "userid") values ('Bert Christensen', 'Beta', '28/09/2022', '10:00:00', '17:00:00', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit', 4);
INSERT INTO groep2b5.workorders ("name","team","date","starttime","endtime","description", "userid") values ('Chris Johnson', 'Gamma', '12/10/2022', '09:00:00', '10:30:00', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit', 5);

-- grant aan lectoren
GRANT ALL ON SEQUENCE groep2b5.workorder_id_seq TO local_u0015529;
GRANT ALL ON TABLE groep2b5.workorders TO local_u0015529;

GRANT ALL ON SEQUENCE groep2b5.workorder_id_seq TO local_u0034562;
GRANT ALL ON TABLE groep2b5.workorders TO local_u0034562;

-- grant aan teamgenoot
GRANT ALL ON SEQUENCE groep2b5.workorder_id_seq TO local_r0848777;
GRANT ALL ON TABLE groep2b5.workorders TO local_r0848777;