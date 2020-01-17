-- groupe
INSERT INTO groupe VALUES (NULL,'groupe 1');
INSERT INTO groupe VALUES (NULL,'groupe 2');
INSERT INTO groupe VALUES (NULL,'groupe 3');

-- activity
INSERT INTO activity VALUES (NULL,'Body Gym',1);
INSERT INTO activity VALUES (NULL,'Pilate',1);
INSERT INTO activity VALUES (NULL,'Zumba',1);
INSERT INTO activity VALUES (NULL,'Fitness',1);
INSERT INTO activity VALUES (NULL,'Krav Maga',2);
INSERT INTO activity VALUES (NULL,'Boxe Anglaise',2);
INSERT INTO activity VALUES (NULL,'Boxe Française',2);
INSERT INTO activity VALUES (NULL,'Yoga 1',3);
INSERT INTO activity VALUES (NULL,'Yoga 2',3);
INSERT INTO activity VALUES (NULL,'Yoga 3',3);

-- session
INSERT INTO session VALUES (NULL,30,'Lundi','10:00:00',1);
INSERT INTO session VALUES (NULL,30,'Mardi','10:00:00',1);
INSERT INTO session VALUES (NULL,30,'Lundi','14:00:00',2);
INSERT INTO session VALUES (NULL,30,'Mardi','14:00:00',2);
INSERT INTO session VALUES (NULL,30,'Mercredi','10:00:00',3);
INSERT INTO session VALUES (NULL,30,'Jeudi','10:00:00',3);
INSERT INTO session VALUES (NULL,30,'Mercredi','14:00:00',4);
INSERT INTO session VALUES (NULL,30,'Jeudi','14:00:00',4);
INSERT INTO session VALUES (NULL,30,'Lundi','16:00:00',5);
INSERT INTO session VALUES (NULL,30,'Mardi','16:00:00',5);
INSERT INTO session VALUES (NULL,30,'Mercredi','16:00:00',6);
INSERT INTO session VALUES (NULL,30,'Jeudi','16:00:00',6);
INSERT INTO session VALUES (NULL,30,'Vendredi','10:00:00',7);
INSERT INTO session VALUES (NULL,30,'Vendredi','14:00:00',7);
INSERT INTO session VALUES (NULL,30,'Vendredi','16:00:00',8);
INSERT INTO session VALUES (NULL,30,'Samedi','10:00:00',9);
INSERT INTO session VALUES (NULL,30,'Samedi','14:00:00',10);

-- user

INSERT INTO user VALUES (NULL,'1990-12-12','admin@sporteasy.com','admin',1,1,'admin','$2a$10$rfOhbPdQnlqYxGHwwWUgbOLHk6eyvr8u/uYe4kh72fQlZZ808BE5u');
INSERT INTO user VALUES (NULL,'1990-12-12','toto@toto.com','Toto',1,0,'Wolf','$2a$10$Tb1tcRAYd9Q7R17tMhkB8eVo6xFm9pdWGPHsuu1k2zwWsNL3iY8Aa');
INSERT INTO user VALUES (NULL,'1980-10-12','tata@tata.com','Tata',0,0,'Wolf','$2a$10$oKmwnS3B9COqkuT1o.MO4e8g2McWsxOeuAXUZihpmcnrZuFhvqiZG');
INSERT INTO user VALUES (NULL,'1985-09-12','titi@titi.com','Titi',1,0,'Wolf','$2a$10$NLlSgogNR.mGOIrldLTBc.ETlhg19FGjh5YVOG8FBvzow7VGRVcPC');

-- inscription
INSERT INTO inscription VALUES (NULL,NULL,'2019-12-20 10:00:00',1,2,2);
INSERT INTO inscription VALUES (NULL,NULL,'2019-12-20 10:00:00',1,3,2);
INSERT INTO inscription VALUES (NULL,NULL,'2019-12-20 10:00:00',1,12,2);
INSERT INTO inscription VALUES (NULL,NULL,'2019-12-20 10:00:00',1,2,3);
INSERT INTO inscription VALUES (NULL,NULL,'2019-12-20 10:00:00',1,3,3);
INSERT INTO inscription VALUES (NULL,NULL,'2019-12-20 10:00:00',1,12,3);
INSERT INTO inscription VALUES (NULL,NULL,'2019-12-20 10:00:00',1,2,4);
INSERT INTO inscription VALUES (NULL,NULL,'2019-12-20 10:00:00',1,3,4);
INSERT INTO inscription VALUES (NULL,NULL,'2019-12-20 10:00:00',1,12,4);