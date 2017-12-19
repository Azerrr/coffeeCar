insert into Utilisateur (identifiant, motDePasse, role) values ('admin', 'admin', 1);
insert into Utilisateur (identifiant, motDePasse, role) values ('user', 'azerty', 2);
insert into Utilisateur (identifiant, motDePasse, role) values ('user2', 'azerty', 2);
insert into Utilisateur (identifiant, motDePasse, role) values ('eric', 'azerty', 2);
insert into Utilisateur (identifiant, motDePasse, role) values ('remi', 'azerty', 2);
insert into Utilisateur (identifiant, motDePasse, role) values ('maxime', 'azerty', 2);
insert into Utilisateur (identifiant, motDePasse, role) values ('guillaume', 'azerty', 2);

insert into Ville (ville) values ('Bourges');
insert into Ville (ville) values ('Blois');
insert into Ville (ville) values ('Orleans');
insert into Ville (ville) values ('Chartres');
insert into Ville (ville) values ('Paris');
insert into Ville (ville) values ('Lille');
insert into Ville (ville) values ('Bruxelles');
insert into Ville (ville) values ('Perpignant');
insert into Ville (ville) values ('Lyon');
insert into Ville (ville) values ('Nice');
insert into Ville (ville) values ('Nancy');
insert into Ville (ville) values ('Amiens');
insert into Ville (ville) values ('Rennes');

insert into Vehicule (type) values ('Urbaine');
insert into Vehicule (type) values ('Compacte');
insert into Vehicule (type) values ('SUV');
insert into Vehicule (type) values ('Break');
insert into Vehicule (type) values ('Routiere');
insert into Vehicule (type) values ('Fourgonnette');

insert into Etape (id,etape,tarif) values (1,'Blois',4);
insert into Etape (id,etape,tarif) values (2,'Orleans',5);
insert into Etape (id,etape,tarif) values (3,'Chartres',6);
insert into Etape (id,etape,tarif) values (4,'Paris',7);
insert into Etape (id,etape,tarif) values (5,'Lille',9);
insert into Etape (id,etape,tarif) values (6,'Bruxelles',11);
insert into Etape (id,etape,tarif) values (7,'Perpignant',12);
insert into Etape (id,etape,tarif) values (8,'Lyon',15);
insert into Etape (id,etape,tarif) values (9,'Nice',20);
insert into Etape (id,etape,tarif) values (10,'Nancy',65);
insert into Etape (id,etape,tarif) values (11,'Amiens',17);
insert into Etape (id,etape,tarif) values (12,'Rennes',96);

insert into Trajets (id,Date, heure, modele, nbPlaces, typeVehicule, villeDepart, conducteur_identifiant) values (1,'2017-12-06', '08:00', '106', 4, 'Urbaine', 'Bourges','remi');
insert into Trajets (id,Date, heure, modele, nbPlaces, typeVehicule, villeDepart, conducteur_identifiant) values (2,'2017-12-06', '09:00', '206', 4, 'Urbaine', 'Bourges','eric');
insert into Trajets (id,Date, heure, modele, nbPlaces, typeVehicule, villeDepart, conducteur_identifiant) values (3,'2017-12-06', '11:00', 'twingo', 4, 'Urbaine', 'Bourges','maxime');
insert into Trajets (id,Date, heure, modele, nbPlaces, typeVehicule, villeDepart, conducteur_identifiant) values (4,'2017-12-06', '05:00', 'velo', 4, 'Urbaine', 'Bourges','guillaume');


insert into TRAJETS_ETAPE (TRAJETS_ID, ETAPES_ID) values (1,1);
insert into TRAJETS_ETAPE (TRAJETS_ID, ETAPES_ID) values (1,2);
insert into TRAJETS_ETAPE (TRAJETS_ID, ETAPES_ID) values (1,3);

insert into TRAJETS_ETAPE (TRAJETS_ID, ETAPES_ID) values (2,4);
insert into TRAJETS_ETAPE (TRAJETS_ID, ETAPES_ID) values (2,5);
insert into TRAJETS_ETAPE (TRAJETS_ID, ETAPES_ID) values (2,6);

insert into TRAJETS_ETAPE (TRAJETS_ID, ETAPES_ID) values (3,7);
insert into TRAJETS_ETAPE (TRAJETS_ID, ETAPES_ID) values (3,8);
insert into TRAJETS_ETAPE (TRAJETS_ID, ETAPES_ID) values (3,9);

insert into TRAJETS_ETAPE (TRAJETS_ID, ETAPES_ID) values (4,10);
insert into TRAJETS_ETAPE (TRAJETS_ID, ETAPES_ID) values (4,11);
insert into TRAJETS_ETAPE (TRAJETS_ID, ETAPES_ID) values (4,12);








