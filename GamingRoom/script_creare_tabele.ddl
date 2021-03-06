-- Generated by Oracle SQL Developer Data Modeler 21.2.0.183.1957
--   at:        2021-12-28 12:53:24 EET
--   site:      Oracle Database 21c
--   type:      Oracle Database 21c



-- predefined type, no DDL - MDSYS.SDO_GEOMETRY

-- predefined type, no DDL - XMLTYPE

CREATE TABLE achitare (
    nr_card         NUMBER(12) NOT NULL,
    id_sesiune      NUMBER(10) NOT NULL,
    data_achitarii  DATE NOT NULL,
    nr_bon          NUMBER(30) NOT NULL,
    pret            NUMBER(8) NOT NULL,
    data_finalizare DATE NOT NULL
);

ALTER TABLE achitare
    ADD CONSTRAINT pret_ck CHECK ( pret IN ( 5, 10 ) );

ALTER TABLE achitare
    ADD CONSTRAINT finish_ck CHECK ( data_finalizare = data_achitarii + pret / 120 );

ALTER TABLE achitare
    ADD CONSTRAINT achitare_pk PRIMARY KEY ( nr_card,
                                             nr_bon,
                                             data_achitarii,
                                             id_sesiune );

CREATE TABLE detalii_jucator (
    nr_card       NUMBER(12) NOT NULL,
    email         VARCHAR2(30),
    data_nasterii DATE NOT NULL,
    gen           CHAR(1)
);

ALTER TABLE detalii_jucator
    ADD CONSTRAINT email_ck CHECK ( REGEXP_LIKE ( email,
                                                  '[a-z0-9._%-]+@[a-z0-9._%-]+\.[a-z]{2,4}' ) );

ALTER TABLE detalii_jucator
    ADD CONSTRAINT birthdate_ck CHECK ( data_nasterii BETWEEN TO_DATE('01.01.1950', 'DD.MM.YYYY') AND TO_DATE('01.01.2013', 'DD.MM.YYYY') );

ALTER TABLE detalii_jucator
    ADD CONSTRAINT gen_ck CHECK ( gen IN ( 'F', 'M' ) );

ALTER TABLE detalii_jucator ADD CONSTRAINT detalii_jucator_pk PRIMARY KEY ( nr_card );

ALTER TABLE detalii_jucator ADD CONSTRAINT detalii_jucator_email_un UNIQUE ( email );

CREATE TABLE jucator (
    nume        VARCHAR2(40) NOT NULL,
    nr_card     NUMBER(12) NOT NULL,
    nume_echipa VARCHAR2(40)
);

ALTER TABLE jucator
    ADD CONSTRAINT nume_ck CHECK ( instr(nume, '0') = 0
                                   AND instr(nume, '1') = 0
                                   AND instr(nume, '2') = 0
                                   AND instr(nume, '3') = 0
                                   AND instr(nume, '4') = 0
                                   AND instr(nume, '5') = 0
                                   AND instr(nume, '6') = 0
                                   AND instr(nume, '7') = 0
                                   AND instr(nume, '8') = 0
                                   AND instr(nume, '9') = 0
                                   AND length(nume) > 1 );

ALTER TABLE jucator ADD CONSTRAINT jucator_pk PRIMARY KEY ( nr_card );

CREATE TABLE sesiune_gaming (
    denumire_joc VARCHAR2(40) NOT NULL,
    id_sesiune   NUMBER(10) NOT NULL,
    id_tip       NUMBER(8) NOT NULL
);

ALTER TABLE sesiune_gaming ADD CONSTRAINT sesiune_gaming_pk PRIMARY KEY ( id_sesiune );

CREATE TABLE tip_joc (
    denumire_tip VARCHAR2(40) NOT NULL,
    id_tip       NUMBER(8) NOT NULL
);

ALTER TABLE tip_joc ADD CONSTRAINT tip_joc_pk PRIMARY KEY ( id_tip );

ALTER TABLE achitare
    ADD CONSTRAINT achitare_jucator_fk FOREIGN KEY ( nr_card )
        REFERENCES jucator ( nr_card );

ALTER TABLE achitare
    ADD CONSTRAINT achitare_sesiune_gaming_fk FOREIGN KEY ( id_sesiune )
        REFERENCES sesiune_gaming ( id_sesiune );

ALTER TABLE detalii_jucator
    ADD CONSTRAINT detalii_jucator_jucator_fk FOREIGN KEY ( nr_card )
        REFERENCES jucator ( nr_card );

ALTER TABLE sesiune_gaming
    ADD CONSTRAINT sesiune_gaming_tip_joc_fk FOREIGN KEY ( id_tip )
        REFERENCES tip_joc ( id_tip );

CREATE SEQUENCE achitare_nr_bon_seq START WITH 100 INCREMENT BY 100 NOCACHE ORDER;

CREATE OR REPLACE TRIGGER achitare_nr_bon_trg BEFORE
    INSERT ON achitare
    FOR EACH ROW
    WHEN ( new.nr_bon IS NULL )
BEGIN
    :new.nr_bon := achitare_nr_bon_seq.nextval;
END;
/

CREATE SEQUENCE sesiune_gaming_id_sesiune_seq START WITH 1 NOCACHE ORDER;

CREATE OR REPLACE TRIGGER sesiune_gaming_id_sesiune_trg BEFORE
    INSERT ON sesiune_gaming
    FOR EACH ROW
    WHEN ( new.id_sesiune IS NULL )
BEGIN
    :new.id_sesiune := sesiune_gaming_id_sesiune_seq.nextval;
END;
/

CREATE SEQUENCE tip_joc_id_tip_seq START WITH 1 NOCACHE ORDER;

CREATE OR REPLACE TRIGGER tip_joc_id_tip_trg BEFORE
    INSERT ON tip_joc
    FOR EACH ROW
    WHEN ( new.id_tip IS NULL )
BEGIN
    :new.id_tip := tip_joc_id_tip_seq.nextval;
END;
/



-- Oracle SQL Developer Data Modeler Summary Report: 
-- 
-- CREATE TABLE                             5
-- CREATE INDEX                             0
-- ALTER TABLE                             16
-- CREATE VIEW                              0
-- ALTER VIEW                               0
-- CREATE PACKAGE                           0
-- CREATE PACKAGE BODY                      0
-- CREATE PROCEDURE                         0
-- CREATE FUNCTION                          0
-- CREATE TRIGGER                           3
-- ALTER TRIGGER                            0
-- CREATE COLLECTION TYPE                   0
-- CREATE STRUCTURED TYPE                   0
-- CREATE STRUCTURED TYPE BODY              0
-- CREATE CLUSTER                           0
-- CREATE CONTEXT                           0
-- CREATE DATABASE                          0
-- CREATE DIMENSION                         0
-- CREATE DIRECTORY                         0
-- CREATE DISK GROUP                        0
-- CREATE ROLE                              0
-- CREATE ROLLBACK SEGMENT                  0
-- CREATE SEQUENCE                          3
-- CREATE MATERIALIZED VIEW                 0
-- CREATE MATERIALIZED VIEW LOG             0
-- CREATE SYNONYM                           0
-- CREATE TABLESPACE                        0
-- CREATE USER                              0
-- 
-- DROP TABLESPACE                          0
-- DROP DATABASE                            0
-- 
-- REDACTION POLICY                         0
-- 
-- ORDS DROP SCHEMA                         0
-- ORDS ENABLE SCHEMA                       0
-- ORDS ENABLE OBJECT                       0
-- 
-- ERRORS                                   0
-- WARNINGS                                 0
