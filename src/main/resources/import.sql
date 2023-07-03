insert into movie (id, date,title, url_images, year) values(nextval('hibernate_sequence'),'1987-10-6','Full metal jacket','{"fullmetaljacket.jpg","fullmetaljacket2.png"}', 1987);
insert into movie (id, date,title, url_images, year) values(nextval('hibernate_sequence'),'2008-2-22','Non e'' un paese per vecchi', '{"non_%C3%A8_un_paese_per_vecchi.jpg"}',2008);
insert into movie (id, date,title, url_images, year) values(nextval('hibernate_sequence'),'2016-12-16','The founder', '{"thefounder.jpg"}',2016);
insert into movie (id, date,title, url_images, year) values(nextval('hibernate_sequence'),'2001-12-6','Harry Potter e la pietra filosofale', '{"pietrafilosofale.jpg"}',2001);
insert into movie (id, date,title, url_images, year) values(nextval('hibernate_sequence'),'2017-7-13','Il pianeta delle scimmie', '{"pianetadellescimmie.jpg","pianetadellescimmie2.jpg","pianetadellescimmie3.jpg"}',2017);
insert into movie (id, date,title, url_images, year) values(nextval('hibernate_sequence'),'2015-2-25','Lo chiamavano Jeeg Robot', '{"jeegRobot.jpg","jeegRobot2.jpg"}',2015);
insert into movie (id, date,title, url_images, year) values(9999,'2019-9-26','Yesterday', '{"yesterday.jpg","yesterday2.jpg"}',2019);

insert into artist (id, death_date,name, surname, url_of_picture, date_of_birth) values(nextval('hibernate_sequence'), null,'Tim', 'Burton', 'timburton.jpg','1958-08-25');
insert into artist (id, death_date,name, surname, url_of_picture, date_of_birth) values(nextval('hibernate_sequence'), null,'Himesh', 'Patel', 'himeshpatel.jpg','1990-10-13');
insert into artist (id, death_date,name, surname, url_of_picture, date_of_birth) values(nextval('hibernate_sequence'),null,'Danny', 'Boyle', 'dannyboyle.jpg','1956-10-20');
insert into artist (id, death_date,name, surname, url_of_picture, date_of_birth) values(nextval('hibernate_sequence'), null,'Tim', 'Roth', 'timroth.jpg','1961-05-14');
insert into artist (id, death_date,name, surname, url_of_picture, date_of_birth) values(nextval('hibernate_sequence'), null,'Michael', 'Keaton', 'michaelkeaton.jpg','1951-09-05');
insert into artist (id, death_date,name, surname, url_of_picture, date_of_birth) values(nextval('hibernate_sequence'),'1999-3-7' ,'Stanley', 'Kubrick', 'kubrick.jpg','1928-07-26');
insert into artist (id, death_date,name, surname, url_of_picture, date_of_birth) values(nextval('hibernate_sequence'),'2018-4-15','Ronald Lee', 'Ermey', 'rLeeermeycrop.jpeg','1944-03-24');	
insert into artist (id, death_date,name, surname, url_of_picture, date_of_birth) values(nextval('hibernate_sequence'),'Claudio', 'Santamaria', 'claudiosantamaria.jpg','1974-07-22');
insert into artist (id, death_date,name, surname, url_of_picture, date_of_birth) values(nextval('hibernate_sequence'), null,'Gabriele', 'Mainetti', 'gabrielemainetti.jpg','1976-11-07');

insert into users (id,email,name,surname) values(9999,'mario@gmail.com','mario','rossi')
insert into credentials (id,password,role,username,user_id) values(9999,'$2a$10$DseXVaKFAl.dtm9cWiKNoOASEMIuu/.axSVJPx2WCrsjRRNW8dFLG','ADMIN','rossi',9999)
insert into review (id,score,text,title,movie_id,reviewer_id) values(nextval('hibernate_sequence'),3,'testo','titolo',9999,9999)