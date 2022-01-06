--inserari tipuri de joc
INSERT INTO tip_joc VALUES('Shooter',TIP_JOC_ID_TIP_SEQ.nextval);
INSERT INTO tip_joc VALUES('Battle Royale',TIP_JOC_ID_TIP_SEQ.nextval);
INSERT INTO tip_joc VALUES('MMORPG',TIP_JOC_ID_TIP_SEQ.nextval);
INSERT INTO tip_joc VALUES('Strategy',TIP_JOC_ID_TIP_SEQ.nextval);
INSERT INTO tip_joc VALUES('Sports',TIP_JOC_ID_TIP_SEQ.nextval);
INSERT INTO tip_joc VALUES('Fighting',TIP_JOC_ID_TIP_SEQ.nextval);
INSERT INTO tip_joc VALUES('Cards',TIP_JOC_ID_TIP_SEQ.nextval);
--inserari servicii de inregistrari live
INSERT INTO serviciu_streaming VALUES('Twitch',SERVICIU_STREAMING_ID_SERVICIU.nextval);
INSERT INTO serviciu_streaming VALUES('Facebook Gaming',SERVICIU_STREAMING_ID_SERVICIU.nextval);
INSERT INTO serviciu_streaming VALUES('Steam',SERVICIU_STREAMING_ID_SERVICIU.nextval);
INSERT INTO serviciu_streaming VALUES('PlayStation Now',SERVICIU_STREAMING_ID_SERVICIU.nextval);
INSERT INTO serviciu_streaming VALUES('Zynga',SERVICIU_STREAMING_ID_SERVICIU.nextval);
--inserari jocuri in sesiuni de gaming
INSERT INTO sesiune_gaming VALUES('League of Legends',SESIUNE_GAMING_ID_SESIUNE_SEQ.nextval,1,4);
INSERT INTO sesiune_gaming VALUES('FIFA',SESIUNE_GAMING_ID_SESIUNE_SEQ.nextval,4,5);
INSERT INTO sesiune_gaming VALUES('PUBG',SESIUNE_GAMING_ID_SESIUNE_SEQ.nextval,3,2);
INSERT INTO sesiune_gaming VALUES('Mortal Kombat',SESIUNE_GAMING_ID_SESIUNE_SEQ.nextval,2,6);
INSERT INTO sesiune_gaming VALUES('Counter-Strike',SESIUNE_GAMING_ID_SESIUNE_SEQ.nextval,3,1);
INSERT INTO sesiune_gaming VALUES('World of Warcraft',SESIUNE_GAMING_ID_SESIUNE_SEQ.nextval,1,3);
INSERT INTO sesiune_gaming VALUES('ZyngaPoker',SESIUNE_GAMING_ID_SESIUNE_SEQ.nextval,5,7);
--inseram clienti
INSERT INTO jucator VALUES('fara card',0,null);

INSERT INTO jucator VALUES('Fane',1782324569,'Botosani Destroyers');
INSERT INTO detalii_jucator VALUES(1782324569,'faneiliescu@yahoo.com','03-JUN-2000','M');
INSERT INTO jucator VALUES('Emi',1986422137,'Iasi Destroyers');
INSERT INTO detalii_jucator VALUES(1986422137,'emilut@gmail.com','02-FEB-2000','M');
INSERT INTO jucator VALUES('Alex',1430825739,null);
INSERT INTO detalii_jucator VALUES(1430825739,null,'04-APR-2000', null);
INSERT INTO jucator VALUES('Razvan',1883267545,null);
INSERT INTO detalii_jucator VALUES(1883267545,'razvan_andrei@gmail.com','29-OCT-1999', 'M');
INSERT INTO jucator VALUES('Ioana',2177809543,'Hammers');
INSERT INTO detalii_jucator VALUES(2177809543,'hellothere@yahoo.com','10-DEC-2001', 'F');
INSERT INTO jucator VALUES('Maria',2566980713,null);
INSERT INTO detalii_jucator VALUES(2566980713,'mm2001@outlook.com','15-NOV-2001', 'F');
INSERT INTO jucator VALUES('George',1779064352,null);
INSERT INTO detalii_jucator VALUES(1779064352,'george1996@msn.com','15-NOV-1995', 'M');
INSERT INTO jucator VALUES('Cosmin',1396407528,'FCSB');
INSERT INTO detalii_jucator VALUES(1396407528,'potop@conti.com','18-AUG-1980', 'M');
INSERT INTO jucator VALUES('Leo',1640193750,'FCBT');
INSERT INTO detalii_jucator VALUES(1640193750,'leo1990@superbet.ro','22-JUN-1990', 'M');

--achitari
INSERT INTO achitare(nr_card,id_sesiune,data_achitarii,pret,data_finalizare) VALUES(0,3,SYSDATE,5,SYSDATE+5/120);
INSERT INTO achitare(nr_card,id_sesiune,data_achitarii,pret,data_finalizare) VALUES(1782324569,5,SYSDATE,10,SYSDATE+10/120);
INSERT INTO achitare(nr_card,id_sesiune,data_achitarii,pret,data_finalizare) VALUES(1986422137,5,SYSDATE,10,SYSDATE+10/120);
INSERT INTO achitare(nr_card,id_sesiune,data_achitarii,pret,data_finalizare) VALUES(1430825739,6,SYSDATE,5,SYSDATE+5/120);
INSERT INTO achitare(nr_card,id_sesiune,data_achitarii,pret,data_finalizare) VALUES(1883267545,7,SYSDATE,5,SYSDATE+5/120);
INSERT INTO achitare(nr_card,id_sesiune,data_achitarii,pret,data_finalizare) VALUES(2177809543,1,SYSDATE,5,SYSDATE+5/120);
INSERT INTO achitare(nr_card,id_sesiune,data_achitarii,pret,data_finalizare) VALUES(2566980713,4,SYSDATE,10,SYSDATE+10/120);
INSERT INTO achitare(nr_card,id_sesiune,data_achitarii,pret,data_finalizare) VALUES(1779064352,4,SYSDATE,10,SYSDATE+10/120);
INSERT INTO achitare(nr_card,id_sesiune,data_achitarii,pret,data_finalizare) VALUES(1396407528,2,SYSDATE,10,SYSDATE+10/120);
INSERT INTO achitare(nr_card,id_sesiune,data_achitarii,pret,data_finalizare) VALUES(1640193750,2,SYSDATE,10,SYSDATE+10/120);