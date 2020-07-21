CREATE TABLE public.user
(
    id SERIAL PRIMARY KEY NOT NULL,
    mail TEXT NOT NULL,
    password TEXT NOT NULL,
    password_salt TEXT NOT NULL
);
CREATE UNIQUE INDEX "user_id_uindex" ON public.user (id);
CREATE UNIQUE INDEX "user_mail_uindex" ON public.user (mail);
CREATE UNIQUE INDEX "user_uuid_uindex" ON public.user (uuid);

CREATE TABLE public.sessions
(
    id SERIAL NOT NULL,
    token TEXT NOT NULL,
    PRIMARY KEY (id, token)
);


insert into "user" (id,mail,password,password_salt) values (1,'test@example.de','passwordTest', '123asd');
insert into "user" (id,mail,password,password_salt) values (2,'test2@example.de','passwordTest', '123asd');
insert into "user" (id,mail,password,password_salt) values (3,'test3@example.de','passwordTest', '123asd');
insert into "user" (id,mail,password,password_salt) values (4,'test4@example.de','passwordTest', '123asd');
insert into "user" (id,mail,password,password_salt) values (5,'test5@example.de','passwordTest', '123asd');
insert into "user" (id,mail,password,password_salt) values (6,'test6@example.de','passwordTest', '123asd');
insert into "user" (id,mail,password,password_salt) values (7,'test7@example.de','passwordTest', '123asd');
insert into "user" (id,mail,password,password_salt) values (8,'test8@example.de','passwordTest', '123asd');
insert into "user" (id,mail,password,password_salt) values (9,'test9@example.de','passwordTest', '123asd');
insert into "user" (id,mail,password,password_salt) values (10,'test10@example.de','passwordTest', '123asd');
insert into "user" (id,mail,password,password_salt) values (11,'test11@example.de','passwordTest', '123asd');
insert into "user" (id,mail,password,password_salt) values (12,'test12@example.de','passwordTest', '123asd');
