CREATE TABLE THEME
 (
   THEME_ID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT  ,
   THEME_LIB TEXT
 );
 CREATE TABLE QUESTION
 (
   QUESTION_ID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT  ,
   THEME_ID INTEGER,
   QUESTION_LIB TEXT,
   FOREIGN KEY (THEME_ID) REFERENCES THEME (THEME_ID) 
 );
CREATE TABLE REPONSE
 (
   QUESTION_ID INTEGER NOT NULL  ,
   REP_ID INTEGER NOT NULL  ,
   REP_LIB TEXT  ,
   REP_TRUE INTEGER ,
   REP_INFO TEXT
   , PRIMARY KEY (QUESTION_ID,REP_ID) 
   ,FOREIGN KEY (QUESTION_ID)  REFERENCES QUESTION (QUESTION_ID)
 );
CREATE TABLE QCM
 (
   QCM_ID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
   QCM_DATE TEXT NULL  ,
   QCM_SCORE INTEGER NULL  
 );
CREATE TABLE POSER
 (
   QUESTION_ID INTEGER NOT NULL  ,
   QCM_ID INTEGER NOT NULL  
   , PRIMARY KEY (QUESTION_ID,QCM_ID)
   , FOREIGN KEY (QUESTION_ID)  REFERENCES QUESTION (QUESTION_ID) 
   ,  FOREIGN KEY (QCM_ID) REFERENCES QCM (QCM_ID) 
 );
CREATE TABLE CHOISIR
 (
   QCM_ID INTEGER NOT NULL  ,
   QUESTION_ID INTEGER NOT NULL  ,
   REP_ID INTEGER NOT NULL  
   , PRIMARY KEY (QCM_ID,QUESTION_ID,REP_ID) 
   , FOREIGN KEY (QCM_ID) REFERENCES QCM (QCM_ID)
   , FOREIGN KEY (QUESTION_ID,REP_ID) REFERENCES REPONSE (QUESTION_ID,REP_ID)
 );

DELETE FROM THEME;
DELETE FROM QUESTION;
DELETE FROM REPONSE;


INSERT INTO THEME(THEME_LIB) VALUES ('Énergies');
INSERT INTO THEME(THEME_LIB) VALUES ('Biodivertsité');
INSERT INTO THEME(THEME_LIB) VALUES ('Transports');
INSERT INTO THEME(THEME_LIB) VALUES ('Écologie');
INSERT INTO THEME(THEME_LIB) VALUES ('Climats');

/*
INSERT INTO QUESTION (QUESTION_LIB,THEME_ID) VALUES ( '?', remplacer par theme_id);
# X est le numéro de la question
INSERT INTO REPONSE (QUESTION_ID,REP_ID,REP_LIB, REP_TRUE, REP_INFO) VALUES (X, 1, '', 1,'');
INSERT INTO REPONSE (QUESTION_ID,REP_ID,REP_LIB, REP_TRUE) VALUES (X, 2, '', 0);
INSERT INTO REPONSE (QUESTION_ID,REP_ID,REP_LIB, REP_TRUE) VALUES (X, 3, '', 0);
INSERT INTO REPONSE (QUESTION_ID,REP_ID,REP_LIB, REP_TRUE) VALUES (X, 4, '', 0);

INSERT INTO THEME(THEME_LIB) VALUES ('Economie Verte');
INSERT INTO THEME(THEME_LIB) VALUES ('Gouvernance');
INSERT INTO THEME(THEME_LIB) VALUES ('Biodiversité');
INSERT INTO THEME(THEME_LIB) VALUES ('Développement Humain');
INSERT INTO THEME(THEME_LIB) VALUES ('Eau et Assainissements');
INSERT INTO THEME(THEME_LIB) VALUES ('Energie');
INSERT INTO THEME(THEME_LIB) VALUES ('Financements Innovants');
INSERT INTO THEME(THEME_LIB) VALUES ('Territoires Durables');
INSERT INTO THEME(THEME_LIB) VALUES ('Résilience');
INSERT INTO THEME(THEME_LIB) VALUES ('Sécurité Alimentaire');
*/

INSERT INTO QUESTION (QUESTION_LIB,THEME_ID) VALUES ( 'Qu''est ce que le gaz de schiste ?',1);
INSERT INTO QUESTION (QUESTION_LIB,THEME_ID) VALUES ( 'Selon les Nations unies, la surface mondiale déforestée chaque année équivaut à la taille :',2);
INSERT INTO QUESTION (QUESTION_LIB,THEME_ID) VALUES ( 'Selon l''International Displacement Monitoring Centre (IDMC), combien de personnes dans le monde ont abandonné leur domicile en 2012 du fait de catastrophes climatiques extrêmes (inondations, ouragans, etc.) ?',5);
INSERT INTO QUESTION (QUESTION_LIB,THEME_ID) VALUES ( 'La notion de « capacité de charge » est un concept clé du développement durable. Atteindre et dépasser cette capacité de charge signifierait que :',1);
INSERT INTO QUESTION (QUESTION_LIB,THEME_ID) VALUES ( 'En 2012, quelle était la part des énergies renouvelables dans la consommation finale d''énergie en France ?',1);
INSERT INTO QUESTION (QUESTION_LIB,THEME_ID) VALUES ( 'En 1987 « la commission mondiale sur l’environnement et le développement » qui a définie le concept de développement durable était présidée par :',1);
INSERT INTO QUESTION (QUESTION_LIB,THEME_ID) VALUES ( 'Au sommet de la terre à Rio en 1992 a été adopté un programme pour agir, qui en français a été appelé :',1);
INSERT INTO QUESTION (QUESTION_LIB,THEME_ID) VALUES ( 'Les Etats-Unis représentent quel pourcentage des émissions mondiales de gaz à effet de serre :',1);
INSERT INTO QUESTION (QUESTION_LIB,THEME_ID) VALUES ( 'Dans chaque ministère il est prévu d’avoir un fonctionnaire chargé du développement durable, sa dénomination est :',1);
INSERT INTO QUESTION (QUESTION_LIB,THEME_ID) VALUES ( 'Le label « HQE » signifie :',1);
INSERT INTO QUESTION (QUESTION_LIB,THEME_ID) VALUES ( 'Depuis 1850, la surface forestière du territoire français :',1);
INSERT INTO QUESTION (QUESTION_LIB,THEME_ID) VALUES ( 'Le nombre de français consommant régulièrement des produits bio est en constante augmentation. Quel est le pourcentage des français qui déclaraient en acheter régulièrement en 2012 ?',1);
INSERT INTO QUESTION (QUESTION_LIB,THEME_ID) VALUES ( 'Depuis la Loi Grenelle 2 de 2012, les entreprises françaises de plus de 500 salariés :',1);
INSERT INTO QUESTION (QUESTION_LIB,THEME_ID) VALUES ( 'Quelle est la température moyenne à la surface de la Terre ?',1);
INSERT INTO QUESTION (QUESTION_LIB,THEME_ID) VALUES ( 'Comment appelle-t-on les émissions de gaz à effet de serre dues à l’activité humaine ?',1);
INSERT INTO QUESTION (QUESTION_LIB,THEME_ID) VALUES ( 'Qu’est-ce-qu’un puits de carbone ?',1);
INSERT INTO QUESTION (QUESTION_LIB,THEME_ID) VALUES ( 'Pour quelle part le gaz carbonique contribue-t-il à l’effet de serre causé par les activités humaines ?',1);
INSERT INTO QUESTION (QUESTION_LIB,THEME_ID) VALUES ( 'En France, quelle est la moyenne de CO2 émis en 2013 par habitant et par an ?',1);
INSERT INTO QUESTION (QUESTION_LIB,THEME_ID) VALUES ( 'Qu’est-ce-que le GIEC ?',1);
INSERT INTO QUESTION (QUESTION_LIB,THEME_ID) VALUES ( 'Dans une maison, quel est le poste le plus gourmand en électricité ?',1);
INSERT INTO QUESTION (QUESTION_LIB,THEME_ID) VALUES ( 'Selon la FAO (Organisation des Nations unies pour l''alimentation et l''agriculture), quelle part de la nourriture produite dans le monde était-elle perdue ou jetée avant d''avoir été consommée en 2011 ?',4);
INSERT INTO QUESTION (QUESTION_LIB,THEME_ID) VALUES ( 'Il existe plus de 300 000 espèces végétales connues sur Terre. Les composés extraits de ces espèces constituent la base de',2);
INSERT INTO QUESTION (QUESTION_LIB,THEME_ID) VALUES ( 'En 1997, le protocole de Kyoto a permis',5);
INSERT INTO QUESTION (QUESTION_LIB,THEME_ID) VALUES ( 'Quelle est la durée de vie moyenne du protoxyde d’azote (N2O) dans l’atmosphère ?',2);
INSERT INTO QUESTION (QUESTION_LIB,THEME_ID) VALUES ( 'Quelle est la part d’émission mondiale de gaz à effet de serre des pays industrialisés ?',5);
INSERT INTO QUESTION (QUESTION_LIB,THEME_ID) VALUES ( 'Quel est l’objectif 2020 de réduction d’émissions de gaz à effet de serre fixé pour l’Union européenne dans le cadre du Protocole de Kyoto ?',5);
INSERT INTO QUESTION (QUESTION_LIB,THEME_ID) VALUES ( 'Afin de stabiliser le réchauffement climatique, de combien les pays industrialisés doivent-ils diviser leurs émissions de gaz à effet de serre d’ici 2050 ?',5);
INSERT INTO QUESTION (QUESTION_LIB,THEME_ID) VALUES ( 'Comment s’appelle le dispositif de prévention de l’Union européenne pour lutter contre le réchauffement climatique ?',5);
INSERT INTO QUESTION (QUESTION_LIB,THEME_ID) VALUES ( 'Quel est l’équivalent de la consommation électrique des appareils qui restent en veille chez vous ?',1);
INSERT INTO QUESTION (QUESTION_LIB,THEME_ID) VALUES ( 'Quelle est la quantité moyenne de gaz carbonique émis par chaque passager d’un vol aller-retour Paris-New York ?',3);

INSERT INTO REPONSE (QUESTION_ID,REP_ID,REP_LIB, REP_TRUE) VALUES (1, 1, 'Un gaz dangeureux', 0);
INSERT INTO REPONSE (QUESTION_ID,REP_ID,REP_LIB, REP_TRUE,REP_INFO) VALUES (1, 2, 'Un gaz naturel riche en matières organiques', 1,'gaz emprisonné dans la roche elle-même. Pour l’en extraire, il faut opérer une fracturation de cette roche, obtenue par injection d’eau sous pression, mélangée à quelques additifs.');
INSERT INTO REPONSE (QUESTION_ID,REP_ID,REP_LIB, REP_TRUE) VALUES (1, 3, 'Un gaz à effet de serre', 0);
INSERT INTO REPONSE (QUESTION_ID,REP_ID,REP_LIB, REP_TRUE) VALUES (1, 4, 'Un gaz rare', 0);

INSERT INTO REPONSE (QUESTION_ID,REP_ID,REP_LIB, REP_TRUE) VALUES (2, 1, 'De la Jamaïque', 0);
INSERT INTO REPONSE (QUESTION_ID,REP_ID,REP_LIB, REP_TRUE,REP_INFO) VALUES (2, 2, 'De la Grèce', 1,'La déforestation s’est produite au rythme de 130 000 km2 (13 millions d’hectares) par an en moyenne de 1990 à 2005 (= la Grèce).');
INSERT INTO REPONSE (QUESTION_ID,REP_ID,REP_LIB, REP_TRUE) VALUES (2, 3, 'Du Brésil', 0);
INSERT INTO REPONSE (QUESTION_ID,REP_ID,REP_LIB, REP_TRUE) VALUES (2, 4, 'De la France', 0);

INSERT INTO REPONSE (QUESTION_ID,REP_ID,REP_LIB, REP_TRUE) VALUES (3, 1, '320 000', 0);
INSERT INTO REPONSE (QUESTION_ID,REP_ID,REP_LIB, REP_TRUE) VALUES (3, 2, '3,2 millions ', 0);
INSERT INTO REPONSE (QUESTION_ID,REP_ID,REP_LIB, REP_TRUE,REP_INFO) VALUES (3, 3, '32 millions ', 1,'En 2012, 32,4 millions de personnes de 82 pays differents ont été contraintes au déplacement. L''Asie a été la plus affectée (22,2 millions de déplacés), devant l''Afrique (8,2 millions) et le continent américain (1,8 million). En 2011,ce chiffre atteignait 16,4 millions.');
INSERT INTO REPONSE (QUESTION_ID,REP_ID,REP_LIB, REP_TRUE) VALUES (3, 4, '32 milliards ', 0);

INSERT INTO REPONSE (QUESTION_ID,REP_ID,REP_LIB, REP_TRUE) VALUES (4, 1, 'Les villes vont manquer de place pour construire de nouveaux logements', 0);
INSERT INTO REPONSE (QUESTION_ID,REP_ID,REP_LIB, REP_TRUE) VALUES (4, 2, 'Croissance démographique oblige, il y aura plus de personnes que de ressources alimentaires', 0);
INSERT INTO REPONSE (QUESTION_ID,REP_ID,REP_LIB, REP_TRUE,REP_INFO) VALUES (4, 3, 'La demande de ressources naturelles va dépasser l''offre', 1,'Site sur le sujet: http://www.developpement-durable.gouv.fr/');
INSERT INTO REPONSE (QUESTION_ID,REP_ID,REP_LIB, REP_TRUE) VALUES (4, 4, 'Les réseaux de transport peuvent s''étendre pour supporter plus de trafic', 0);

INSERT INTO REPONSE (QUESTION_ID,REP_ID,REP_LIB, REP_TRUE) VALUES (5, 1, 'Moins de 3 %', 0);
INSERT INTO REPONSE (QUESTION_ID,REP_ID,REP_LIB, REP_TRUE,REP_INFO) VALUES (5, 2, 'Environ 13 % ', 1,'Répartitions des énergies renouvelables : Pétrole 43%, gaz 23%, nucléaire 17%, charbon 4%.');
INSERT INTO REPONSE (QUESTION_ID,REP_ID,REP_LIB, REP_TRUE) VALUES (5, 3, 'Plus de 25% ', 0);
INSERT INTO REPONSE (QUESTION_ID,REP_ID,REP_LIB, REP_TRUE) VALUES (5, 4, 'Environ 6 % ', 0);

INSERT INTO REPONSE (QUESTION_ID,REP_ID,REP_LIB, REP_TRUE) VALUES (6, 1, 'Monsieur Brodhag', 0);
INSERT INTO REPONSE (QUESTION_ID,REP_ID,REP_LIB, REP_TRUE,REP_INFO) VALUES (6, 2, 'Madame Gro Harlem Brundtland', 1,'La notion de développement durable a été construite par la Commission mondiale sur l’environnement et le développement, créée en 1983 par l’ONU et présidée par Gro Harlem Brundtland jusqu’en 1987. Source : http://www.cnrs.fr/cw/dossiers/dosclim1/biblio/pigb17/01_CREIDD.htm');
INSERT INTO REPONSE (QUESTION_ID,REP_ID,REP_LIB, REP_TRUE) VALUES (6, 3, 'Madame Vaira Vire-Fretberga', 0);
INSERT INTO REPONSE (QUESTION_ID,REP_ID,REP_LIB, REP_TRUE) VALUES (6, 4, 'Monsieur Toomas Hendrik Ilves', 0);

INSERT INTO REPONSE (QUESTION_ID,REP_ID,REP_LIB, REP_TRUE) VALUES (7, 1, 'Action 21', 0);
INSERT INTO REPONSE (QUESTION_ID,REP_ID,REP_LIB, REP_TRUE,REP_INFO) VALUES (7, 2, 'Agenda 21', 1,'La notion de développement durable a été construite par la Commission mondiale sur l’environnement et le développement, créée en 1983 par l’ONU et présidée par Gro Harlem Brundtland jusqu’en 1987. Source : http://www.cnrs.fr/cw/dossiers/dosclim1/biblio/pigb17/01_CREIDD.htm');
INSERT INTO REPONSE (QUESTION_ID,REP_ID,REP_LIB, REP_TRUE) VALUES (7, 3, 'Opération 21', 0);
INSERT INTO REPONSE (QUESTION_ID,REP_ID,REP_LIB, REP_TRUE) VALUES (7, 4, 'Mission 21', 0);

INSERT INTO REPONSE (QUESTION_ID,REP_ID,REP_LIB, REP_TRUE) VALUES (8, 1, '10 %', 0);
INSERT INTO REPONSE (QUESTION_ID,REP_ID,REP_LIB, REP_TRUE,REP_INFO) VALUES (8, 2, '20%', 1,'La Chine émet presque autant de gaz à effet de serre que les Etats-Unis. Plus de détails : http://www.developpement-durable.gouv.fr/IMG/pdf/Rep_-_Chiffres_cles_du_climat.pdf');
INSERT INTO REPONSE (QUESTION_ID,REP_ID,REP_LIB, REP_TRUE) VALUES (8, 3, '30%', 0);
INSERT INTO REPONSE (QUESTION_ID,REP_ID,REP_LIB, REP_TRUE) VALUES (8, 4, '40%', 0);

INSERT INTO REPONSE (QUESTION_ID,REP_ID,REP_LIB, REP_TRUE) VALUES (9, 1, 'Contrôleur au développement durable', 0);
INSERT INTO REPONSE (QUESTION_ID,REP_ID,REP_LIB, REP_TRUE,REP_INFO) VALUES (9, 2, 'Haut fonctionnaire au développement durable', 1,'Source : http://www.assemblee-nationale.fr/12/evenements/salon-des-maires/france_defi/comite.asp');
INSERT INTO REPONSE (QUESTION_ID,REP_ID,REP_LIB, REP_TRUE) VALUES (9, 3, 'Inspecteur général du développement durable', 0);
INSERT INTO REPONSE (QUESTION_ID,REP_ID,REP_LIB, REP_TRUE) VALUES (9, 4, 'Commissaire du développement durable', 0);

INSERT INTO REPONSE (QUESTION_ID,REP_ID,REP_LIB, REP_TRUE) VALUES (10, 1, 'Haute Qualification Ecologique', 0);
INSERT INTO REPONSE (QUESTION_ID,REP_ID,REP_LIB, REP_TRUE) VALUES (10, 2, 'Haute Qualité Ecologique', 0);
INSERT INTO REPONSE (QUESTION_ID,REP_ID,REP_LIB, REP_TRUE) VALUES (10, 3, 'Haute Qualification Environnementale', 0);
INSERT INTO REPONSE (QUESTION_ID,REP_ID,REP_LIB, REP_TRUE,REP_INFO) VALUES (10, 4, 'Haute Qualité Environnementale', 1,'La HQE, haute qualité environnementale, créée dans les années 90, consiste à effectuer une démarche dans le pur respect de l''environnement. Elle concerne le secteur du bâtiment et s''applique aux domaines des opérations de construction et de réhabilitation. Source : http://qualite.comprendrechoisir.com/comprendre/hqe');

INSERT INTO REPONSE (QUESTION_ID,REP_ID,REP_LIB, REP_TRUE) VALUES (11, 1, 'A été divisée par quatre', 0);
INSERT INTO REPONSE (QUESTION_ID,REP_ID,REP_LIB, REP_TRUE) VALUES (11, 2, 'A été divisée par deux', 0);
INSERT INTO REPONSE (QUESTION_ID,REP_ID,REP_LIB, REP_TRUE) VALUES (11, 3, 'Est restée constante', 0);
INSERT INTO REPONSE (QUESTION_ID,REP_ID,REP_LIB, REP_TRUE,REP_INFO) VALUES (11, 4, 'A été multipliée par deux', 1,'http://www.developpement-durable.gouv.fr/La-foret-francaise-en-chiffres.html');

INSERT INTO REPONSE (QUESTION_ID,REP_ID,REP_LIB, REP_TRUE) VALUES (12, 1, '8%', 0);
INSERT INTO REPONSE (QUESTION_ID,REP_ID,REP_LIB, REP_TRUE) VALUES (12, 2, '63%', 0);
INSERT INTO REPONSE (QUESTION_ID,REP_ID,REP_LIB, REP_TRUE) VALUES (12, 3, '88%', 0);
INSERT INTO REPONSE (QUESTION_ID,REP_ID,REP_LIB, REP_TRUE,REP_INFO) VALUES (12, 4, '38%', 1,'Aujourd''hui, près de 4 foyers sur 10 (38%) ont régulièrement acheté bio en 2012. Source: http://www.actu-environnement.com/ae/news/agence-bio-conmmations-bio-france-2012-production-conversions-baisse-17736.php4');

INSERT INTO REPONSE (QUESTION_ID,REP_ID,REP_LIB, REP_TRUE) VALUES (13, 1, 'N''ont plus à se soucier de leur impact environnemental', 0);
INSERT INTO REPONSE (QUESTION_ID,REP_ID,REP_LIB, REP_TRUE) VALUES (13, 2, 'Imposent à leurs fournisseurs une charte éthique garantissant le respect des droits fondamentaux', 0);
INSERT INTO REPONSE (QUESTION_ID,REP_ID,REP_LIB, REP_TRUE) VALUES (13, 3, 'Evaluent leur impact environnemental via la réalisation d''un bilan carbone annuel', 0);
INSERT INTO REPONSE (QUESTION_ID,REP_ID,REP_LIB, REP_TRUE,REP_INFO) VALUES (13, 4, 'Produisent des informations sur la manière dont elles prennent en compte les conséquences sociales et environnementales de leur activité', 1,'Plus d''infos: http://www.developpement-durable.gouv.fr/IMG/pdf/Grenelle_Loi-2.pdf');

INSERT INTO REPONSE (QUESTION_ID,REP_ID,REP_LIB, REP_TRUE) VALUES (14, 1, '12°C', 0);
INSERT INTO REPONSE (QUESTION_ID,REP_ID,REP_LIB, REP_TRUE) VALUES (14, 2, '22°C', 0);
INSERT INTO REPONSE (QUESTION_ID,REP_ID,REP_LIB, REP_TRUE) VALUES (14, 3, '18°C', 0);
INSERT INTO REPONSE (QUESTION_ID,REP_ID,REP_LIB, REP_TRUE,REP_INFO) VALUES (14, 4, '15°C', 1,'Source: http://www.cnrs.fr/cw/dossiers/dosclim1/sysfacte/effetserre/index.htm#tempmoy');

INSERT INTO REPONSE (QUESTION_ID,REP_ID,REP_LIB, REP_TRUE) VALUES (15, 1, 'Emissions anthropomorphiques', 0);
INSERT INTO REPONSE (QUESTION_ID,REP_ID,REP_LIB, REP_TRUE) VALUES (15, 2, 'Emissions anthropologiques', 0);
INSERT INTO REPONSE (QUESTION_ID,REP_ID,REP_LIB, REP_TRUE) VALUES (15, 3, 'Emissions anthropomorphes', 0);
INSERT INTO REPONSE (QUESTION_ID,REP_ID,REP_LIB, REP_TRUE,REP_INFO) VALUES (15, 4, 'Emissions anthropiques', 1,'http://www.actu-environnement.com/ae/dictionnaire_environnement/definition/anthropique.php4');

INSERT INTO REPONSE (QUESTION_ID,REP_ID,REP_LIB, REP_TRUE) VALUES (16, 1, 'Un système qui absorbe du carbone', 0);
INSERT INTO REPONSE (QUESTION_ID,REP_ID,REP_LIB, REP_TRUE) VALUES (16, 2, 'Une source d’eau naturellement riche en carbone', 0);
INSERT INTO REPONSE (QUESTION_ID,REP_ID,REP_LIB, REP_TRUE) VALUES (16, 3, 'Un site minier d’extraction de carbone brut', 0);
INSERT INTO REPONSE (QUESTION_ID,REP_ID,REP_LIB, REP_TRUE,REP_INFO) VALUES (16, 4, 'Un système qui absorbe naturellement le gaz carbonique (CO2)', 1,'http://www.actu-environnement.com/ae/dictionnaire_environnement/definition/puits_de_carbone.php4');

INSERT INTO REPONSE (QUESTION_ID,REP_ID,REP_LIB, REP_TRUE) VALUES (17, 1, '5%', 0);
INSERT INTO REPONSE (QUESTION_ID,REP_ID,REP_LIB, REP_TRUE) VALUES (17, 2, '20%', 0);
INSERT INTO REPONSE (QUESTION_ID,REP_ID,REP_LIB, REP_TRUE) VALUES (17, 3, '40%', 0);
INSERT INTO REPONSE (QUESTION_ID,REP_ID,REP_LIB, REP_TRUE,REP_INFO) VALUES (17, 4, '60%', 1,'Le gaz carbonique d''origine humaine est responsable d''un peu plus de 55% de l''effet de serre. Source: http://www.manicore.com/documentation/serre/gaz.html');

INSERT INTO REPONSE (QUESTION_ID,REP_ID,REP_LIB, REP_TRUE) VALUES (18, 1, '500 kg', 0);
INSERT INTO REPONSE (QUESTION_ID,REP_ID,REP_LIB, REP_TRUE) VALUES (18, 2, '1,2 t', 0);
INSERT INTO REPONSE (QUESTION_ID,REP_ID,REP_LIB, REP_TRUE) VALUES (18, 3, '5.8 t', 0);
INSERT INTO REPONSE (QUESTION_ID,REP_ID,REP_LIB, REP_TRUE,REP_INFO) VALUES (18, 4, '8.1 t', 1,'Source: http://www.planetoscope.com/co2/140-emissions-de-co2-par-habitant-en-france.html');

INSERT INTO REPONSE (QUESTION_ID,REP_ID,REP_LIB, REP_TRUE) VALUES (19, 1, 'Un gaz à effet de serre non naturel', 0);
INSERT INTO REPONSE (QUESTION_ID,REP_ID,REP_LIB, REP_TRUE) VALUES (19, 2, 'Une unité de mesure pour évaluer l’impact de l’homme sur le réchauffement climatique', 0);
INSERT INTO REPONSE (QUESTION_ID,REP_ID,REP_LIB, REP_TRUE) VALUES (19, 3, 'Un gaz à effet de serre naturel', 0);
INSERT INTO REPONSE (QUESTION_ID,REP_ID,REP_LIB, REP_TRUE,REP_INFO) VALUES (19, 4, 'Un groupe d’experts intergouvernemental pour étudier les changements climatiques', 1,'Source: http://www.developpement-durable.gouv.fr/Presentation-du-GIEC.html');

INSERT INTO REPONSE (QUESTION_ID,REP_ID,REP_LIB, REP_TRUE) VALUES (20, 1, 'L''audiovisuel', 0);
INSERT INTO REPONSE (QUESTION_ID,REP_ID,REP_LIB, REP_TRUE) VALUES (20, 2, 'Le lavage', 0);
INSERT INTO REPONSE (QUESTION_ID,REP_ID,REP_LIB, REP_TRUE) VALUES (20, 3, 'L''éclairage', 0);
INSERT INTO REPONSE (QUESTION_ID,REP_ID,REP_LIB, REP_TRUE,REP_INFO) VALUES (20, 4, 'La production de froid', 1,'Source: http://pionniersduclimat.lacub.fr/les-energies-et-leau/appareils-electriques-quels-sont-les-plus-energivores-quels-sont-les-plus-performants/');

INSERT INTO REPONSE (QUESTION_ID,REP_ID,REP_LIB, REP_TRUE) VALUES (21, 1, 'Moins de 10 %', 0);
INSERT INTO REPONSE (QUESTION_ID,REP_ID,REP_LIB, REP_TRUE,REP_INFO) VALUES (21, 2, 'Un tiers', 1,'Article sur le gaspillage alimentaire: http://www.planetoscope.com/agriculture-alimentation/1556-le-gaspillage-alimentaire-dans-le-monde.html');
INSERT INTO REPONSE (QUESTION_ID,REP_ID,REP_LIB, REP_TRUE) VALUES (21, 3, '50%', 0);
INSERT INTO REPONSE (QUESTION_ID,REP_ID,REP_LIB, REP_TRUE) VALUES (21, 4, 'Plus de 70 %', 0);

INSERT INTO REPONSE (QUESTION_ID,REP_ID,REP_LIB, REP_TRUE) VALUES (22, 1,'10 % des médicaments de prescription moderne', 0);
INSERT INTO REPONSE (QUESTION_ID,REP_ID,REP_LIB, REP_TRUE) VALUES (22, 2,'30 % des médicaments de prescription moderne', 0);
INSERT INTO REPONSE (QUESTION_ID,REP_ID,REP_LIB, REP_TRUE,REP_INFO) VALUES (22, 3,'50 % des médicaments de prescription moderne', 1,'Complément sur les substances d''origine végétale: http://ansm.sante.fr/Mediatheque/Publications/Pharmacopee-francaise-Substances-d-origine-vegetale');
INSERT INTO REPONSE (QUESTION_ID,REP_ID,REP_LIB, REP_TRUE) VALUES (22, 4,'70 % des médicaments de prescription moderne', 0);

INSERT INTO REPONSE (QUESTION_ID,REP_ID,REP_LIB, REP_TRUE) VALUES (23, 1, 'D''alerter la communauté internationale sur le non respect des conditions de travail définies par l''Organisation Internationale du Travail', 0);
INSERT INTO REPONSE (QUESTION_ID,REP_ID,REP_LIB, REP_TRUE) VALUES (23, 2, 'De définir des régles de bonne conduite en matière de gouvernance d''entreprise', 0);
INSERT INTO REPONSE (QUESTION_ID,REP_ID,REP_LIB, REP_TRUE,REP_INFO) VALUES (23, 3, 'De fixer des objectifs de réduction ou de limitation des émissions de gaz à effet de serre', 1,'Résumé du protocol de Kyoto :http://unfccc.int/portal_francophone/essential_background/feeling_the_heat/items/3294.php');
INSERT INTO REPONSE (QUESTION_ID,REP_ID,REP_LIB, REP_TRUE) VALUES (23, 4, 'De fixer des objectifs de réduction budgetaire', 0);

INSERT INTO REPONSE (QUESTION_ID,REP_ID,REP_LIB, REP_TRUE) VALUES (24, 1, '12 ans', 0);
INSERT INTO REPONSE (QUESTION_ID,REP_ID,REP_LIB, REP_TRUE) VALUES (24, 2, '50 ans', 0);
INSERT INTO REPONSE (QUESTION_ID,REP_ID,REP_LIB, REP_TRUE) VALUES (24, 3, '95 ans', 0);
INSERT INTO REPONSE (QUESTION_ID,REP_ID,REP_LIB, REP_TRUE,REP_INFO) VALUES (24, 4, '120 ans', 1,'C’est le gaz à effet de serre qui a la plus longue durée de vie. Le CO2 a une durée de vie moyenne de 100 ans');

INSERT INTO REPONSE (QUESTION_ID,REP_ID,REP_LIB, REP_TRUE) VALUES (25, 1, '20%', 0);
INSERT INTO REPONSE (QUESTION_ID,REP_ID,REP_LIB, REP_TRUE) VALUES (25, 2, '30%', 0);
INSERT INTO REPONSE (QUESTION_ID,REP_ID,REP_LIB, REP_TRUE) VALUES (25, 3, '40%', 0);
INSERT INTO REPONSE (QUESTION_ID,REP_ID,REP_LIB, REP_TRUE,REP_INFO) VALUES (25, 4, '50%', 1,'Toutefois, le taux d’émission de gaz à effet de serre des pays en voie de développement pourrait excéder, dans 10 ans, celui des pays industrialisés si aucune aide ne leur est apportée.');

INSERT INTO REPONSE (QUESTION_ID,REP_ID,REP_LIB, REP_TRUE) VALUES (26, 1, '- 20% part rapport à 1990', 0);
INSERT INTO REPONSE (QUESTION_ID,REP_ID,REP_LIB, REP_TRUE,REP_INFO) VALUES (26, 2, '- 8% part rapport à 1990', 1,'Résumé du protocol de Kyoto :http://unfccc.int/portal_francophone/essential_background/feeling_the_heat/items/3294.php');
INSERT INTO REPONSE (QUESTION_ID,REP_ID,REP_LIB, REP_TRUE) VALUES (26, 3, '- 8% par rapport à 1950', 0);
INSERT INTO REPONSE (QUESTION_ID,REP_ID,REP_LIB, REP_TRUE) VALUES (26, 4, '- 20% part rapport à 1950', 0);

INSERT INTO REPONSE (QUESTION_ID,REP_ID,REP_LIB, REP_TRUE) VALUES (27, 1, 'Par 2', 0);
INSERT INTO REPONSE (QUESTION_ID,REP_ID,REP_LIB, REP_TRUE,REP_INFO) VALUES (27, 2, 'Par 4', 1,'En divisant par 4 leurs émissions, cet effort permettra non pas d’enrayer complètement le réchauffement climatique mais de contenir l’élévation de la température moyenne de la terre à 2°, seuil au-dessus duquel des bouleversements catastrophiques surviendront.');
INSERT INTO REPONSE (QUESTION_ID,REP_ID,REP_LIB, REP_TRUE) VALUES (27, 3, 'Par 6', 0);
INSERT INTO REPONSE (QUESTION_ID,REP_ID,REP_LIB, REP_TRUE) VALUES (27, 4, 'Par 8', 0);

INSERT INTO REPONSE (QUESTION_ID,REP_ID,REP_LIB, REP_TRUE,REP_INFO) VALUES (28, 1, 'Le Paquet Energie Climat', 1,'Aadopté en 2008, il définit des objectifs à plus long terme que le protocole de Kyoto. L''Union europénne s’engage ainsi à réduire ses émissions de gaz à effet de serre de 20% d''ici 2020.');
INSERT INTO REPONSE (QUESTION_ID,REP_ID,REP_LIB, REP_TRUE) VALUES (28, 2, 'Le Protocole de Vienne', 0);
INSERT INTO REPONSE (QUESTION_ID,REP_ID,REP_LIB, REP_TRUE) VALUES (28, 3, 'Le Traité de Rome', 0);
INSERT INTO REPONSE (QUESTION_ID,REP_ID,REP_LIB, REP_TRUE) VALUES (28, 4, 'L''accord pour le climat', 0);

INSERT INTO REPONSE (QUESTION_ID,REP_ID,REP_LIB, REP_TRUE) VALUES (29, 1, 'Un jour d''éclairage de l''aéroport d''Orly', 0);
INSERT INTO REPONSE (QUESTION_ID,REP_ID,REP_LIB, REP_TRUE) VALUES (29, 2, 'Un jour d''éclairage public de Paris', 0);
INSERT INTO REPONSE (QUESTION_ID,REP_ID,REP_LIB, REP_TRUE,REP_INFO) VALUES (29, 3, 'Un jour d''éclairage public de toute la France', 1,'. Méfiez-vous donc de ces petites lumières rouges, pourtant anodines, sur vos magnétoscopes, chaînes HI-FI et ordinateurs ! Même en veille, vos appareils restent sous tension et consomment pratiquement autant d’électricité que s’ils étaient en marche.');
INSERT INTO REPONSE (QUESTION_ID,REP_ID,REP_LIB, REP_TRUE) VALUES (29, 4, 'Un jour d''éclairage public du continent européen', 0);

INSERT INTO REPONSE (QUESTION_ID,REP_ID,REP_LIB, REP_TRUE) VALUES (30, 1, '500 kg', 0);
INSERT INTO REPONSE (QUESTION_ID,REP_ID,REP_LIB, REP_TRUE,REP_INFO) VALUES (30, 2, '1,2 tonnes', 1,'1,2 tonnes de gaz carbonique, soit plus d’un quart des émissions moyennes d’un français en une année');
INSERT INTO REPONSE (QUESTION_ID,REP_ID,REP_LIB, REP_TRUE) VALUES (30, 3, '3,5 tonnes', 0);
INSERT INTO REPONSE (QUESTION_ID,REP_ID,REP_LIB, REP_TRUE) VALUES (30, 4, '6,6 tonnes', 0);
