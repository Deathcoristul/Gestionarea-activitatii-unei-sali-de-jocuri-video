--afisare jucatori si detalii
SELECT nume,TO_CHAR(jucator.nr_card),nume_echipa,data_nasterii,gen,email FROM jucator, detalii_jucator 
WHERE jucator.nr_card=detalii_jucator.nr_card;
--afisare sesiuni cu tipuri de joc
SELECT id_sesiune,denumire_joc "Nume Joc",tp.id_tip "ID Tip",denumire_tip "Tipul Jocului"
FROM sesiune_gaming sg,tip_joc tp
WHERE sg.id_tip=tp.id_tip;
--afisare achitari
SELECT nr_card,id_sesiune,TO_CHAR(data_achitarii,'DD-MON-YY HH24:MM') "Data si ora achitarii",nr_bon,pret, TO_CHAR(data_finalizare,'DD-MON-YY HH24:MM') "Data si ora finalizarii" 
FROM achitare;
--afisare achitari, cu nume jucatori
SELECT nume, TO_CHAR(data_achitarii,'DD-MON-YY HH24:MM') "Data si ora achitarii",nr_bon,pret, TO_CHAR(data_finalizare,'DD-MON-YY HH24:MM') "Data si ora finalizarii"
FROM jucator,achitare WHERE jucator.nr_card=achitare.nr_card;
--informatii despre timpul jucat
SELECT  id_sesiune,nr_card,TRUNC(minutes_ / 60) || ':' || TRUNC(MOD(minutes_,  60))  "Timp de joaca"
FROM (SELECT  (sysdate - data_achitarii) * 24 * 60 AS minutes_,nr_card,id_sesiune
FROM  achitare);
--informatii despre timpul jucat, cu numele jucatorului si cu numele jocului
SELECT  nume,denumire_joc,TRUNC(minutes_ / 60) || ':' || TRUNC(MOD(minutes_,  60))  "Timp de joaca"
FROM (SELECT  (sysdate - data_achitarii) * 24 * 60 AS minutes_,nume,denumire_joc
FROM  achitare,jucator,sesiune_gaming WHERE jucator.nr_card=achitare.nr_card and sesiune_gaming.id_sesiune=achitare.id_sesiune);
--stergem jucatori care au depasit timpul
DELETE FROM achitare WHERE SYSDATE> data_finalizare;
--ce se intampla daca inseram clienti cu e-mail gresit?
INSERT INTO jucator VALUES('VASILE',44,null);
INSERT INTO detalii_jucator VALUES(44,'vasile.com','03-AUG-1988','M');--check constraint email_ck violated
--de gen diferit?
INSERT INTO detalii_jucator VALUES(44,'vasile@sport.com','03-AUG-1988','G');--check constraint gen_ck violated
DELETE FROM jucator WHERE nume='VASILE';
--cu cifre in nume?
INSERT INTO jucator VALUES('d23aa',49,null);--check constraint nume_ck violated
--cu o singura litera?
INSERT INTO jucator VALUES('s',49,null);--check constraint nume_ck violated
--acelasi ID in tipuri de joc
INSERT INTO tip_joc VALUES('Nush',1);--unique constraint violated
--daca introducem o data cu un e-mail deja introdus in baza de date??
INSERT INTO detalii_jucator VALUES(44,'emilut@gmail.com','03-AUG-1988','M');--unique constraint violated
--daca introducem un tip de joc fara denumire
INSERT INTO tip_joc(id_tip) VALUES(36);--cannot insert NULL into