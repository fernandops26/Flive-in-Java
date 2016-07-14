prompt PL/SQL Developer import file
prompt Created on martes, 5 de julio de 2016 by ferna
set feedback off
set define off
prompt Disabling triggers for CATEGORIA...
alter table CATEGORIA disable all triggers;
prompt Disabling triggers for DEPARTAMENTO...
alter table DEPARTAMENTO disable all triggers;
prompt Disabling triggers for PERFIL...
alter table PERFIL disable all triggers;
prompt Disabling triggers for ALBUM...
alter table ALBUM disable all triggers;
prompt Disabling triggers for PUBLICACION...
alter table PUBLICACION disable all triggers;
prompt Disabling triggers for GUSTAR...
alter table GUSTAR disable all triggers;
prompt Disabling triggers for TIPONOTIFICACION...
alter table TIPONOTIFICACION disable all triggers;
prompt Disabling triggers for NOTIFICACION...
alter table NOTIFICACION disable all triggers;
prompt Disabling triggers for SEGUIDOR...
alter table SEGUIDOR disable all triggers;
prompt Disabling triggers for TAGALBUM...
alter table TAGALBUM disable all triggers;
prompt Disabling triggers for TAGPUBLICACION...
alter table TAGPUBLICACION disable all triggers;
prompt Disabling triggers for USUARIO...
alter table USUARIO disable all triggers;
prompt Disabling foreign key constraints for PERFIL...
alter table PERFIL disable constraint FK_CODDEPARTAMENTO;
prompt Disabling foreign key constraints for ALBUM...
alter table ALBUM disable constraint FK_CODCATEGORIA;
alter table ALBUM disable constraint FK_CODPERFIL2;
prompt Disabling foreign key constraints for PUBLICACION...
alter table PUBLICACION disable constraint FK_CODALBUM;
prompt Disabling foreign key constraints for GUSTAR...
alter table GUSTAR disable constraint FK_CODPERFIL5;
alter table GUSTAR disable constraint FL_CODPUBLICACION;
prompt Disabling foreign key constraints for NOTIFICACION...
alter table NOTIFICACION disable constraint FK_CODPERFIL_ORIGEN;
alter table NOTIFICACION disable constraint FK_CODPERFIL4;
alter table NOTIFICACION disable constraint FK_CODTIPONOTIFICACION;
prompt Disabling foreign key constraints for SEGUIDOR...
alter table SEGUIDOR disable constraint FK_CODPERFIL_SEGUIDO;
alter table SEGUIDOR disable constraint FK_CODPERFIL_SEGUIDOR;
prompt Disabling foreign key constraints for TAGALBUM...
alter table TAGALBUM disable constraint FK_CODALBUM2;
prompt Disabling foreign key constraints for TAGPUBLICACION...
alter table TAGPUBLICACION disable constraint FK_CODPUBLICACION;
prompt Disabling foreign key constraints for USUARIO...
alter table USUARIO disable constraint FK_CODPERFIL;
prompt Deleting USUARIO...
delete from USUARIO;
commit;
prompt Deleting TAGPUBLICACION...
delete from TAGPUBLICACION;
commit;
prompt Deleting TAGALBUM...
delete from TAGALBUM;
commit;
prompt Deleting SEGUIDOR...
delete from SEGUIDOR;
commit;
prompt Deleting NOTIFICACION...
delete from NOTIFICACION;
commit;
prompt Deleting TIPONOTIFICACION...
delete from TIPONOTIFICACION;
commit;
prompt Deleting GUSTAR...
delete from GUSTAR;
commit;
prompt Deleting PUBLICACION...
delete from PUBLICACION;
commit;
prompt Deleting ALBUM...
delete from ALBUM;
commit;
prompt Deleting PERFIL...
delete from PERFIL;
commit;
prompt Deleting DEPARTAMENTO...
delete from DEPARTAMENTO;
commit;
prompt Deleting CATEGORIA...
delete from CATEGORIA;
commit;
prompt Loading CATEGORIA...
insert into CATEGORIA (CODCATEGORIA, NOMBRECATE)
values (41, 'Flive');
insert into CATEGORIA (CODCATEGORIA, NOMBRECATE)
values (1, 'Regalos');
insert into CATEGORIA (CODCATEGORIA, NOMBRECATE)
values (2, 'Videos');
insert into CATEGORIA (CODCATEGORIA, NOMBRECATE)
values (3, 'Actividades al aire libre');
insert into CATEGORIA (CODCATEGORIA, NOMBRECATE)
values (4, 'Arquitectura');
insert into CATEGORIA (CODCATEGORIA, NOMBRECATE)
values (5, 'Animales');
insert into CATEGORIA (CODCATEGORIA, NOMBRECATE)
values (6, 'Arte');
insert into CATEGORIA (CODCATEGORIA, NOMBRECATE)
values (7, 'Bodas');
insert into CATEGORIA (CODCATEGORIA, NOMBRECATE)
values (8, 'Brincolaje y manualidades');
insert into CATEGORIA (CODCATEGORIA, NOMBRECATE)
values (9, 'Carteles e ilustraciones');
insert into CATEGORIA (CODCATEGORIA, NOMBRECATE)
values (10, 'Ciencia y naturaleza');
insert into CATEGORIA (CODCATEGORIA, NOMBRECATE)
values (11, 'Citas célebres');
insert into CATEGORIA (CODCATEGORIA, NOMBRECATE)
values (12, 'Coches y motocicletas');
insert into CATEGORIA (CODCATEGORIA, NOMBRECATE)
values (13, 'Comida y bebida');
insert into CATEGORIA (CODCATEGORIA, NOMBRECATE)
values (14, 'Decoración del hogar');
insert into CATEGORIA (CODCATEGORIA, NOMBRECATE)
values (15, 'Deportes');
insert into CATEGORIA (CODCATEGORIA, NOMBRECATE)
values (16, 'Diseño');
insert into CATEGORIA (CODCATEGORIA, NOMBRECATE)
values (17, 'Educación');
insert into CATEGORIA (CODCATEGORIA, NOMBRECATE)
values (18, 'Famosos');
insert into CATEGORIA (CODCATEGORIA, NOMBRECATE)
values (19, 'Festivos y acontecimientos');
insert into CATEGORIA (CODCATEGORIA, NOMBRECATE)
values (20, 'Fotografía');
insert into CATEGORIA (CODCATEGORIA, NOMBRECATE)
values (21, 'Historia');
insert into CATEGORIA (CODCATEGORIA, NOMBRECATE)
values (22, 'Humor');
insert into CATEGORIA (CODCATEGORIA, NOMBRECATE)
values (23, 'Jardinería');
insert into CATEGORIA (CODCATEGORIA, NOMBRECATE)
values (24, 'Libros, música y cine');
insert into CATEGORIA (CODCATEGORIA, NOMBRECATE)
values (25, 'Moda masculina');
insert into CATEGORIA (CODCATEGORIA, NOMBRECATE)
values (26, 'Moda para mujeres');
insert into CATEGORIA (CODCATEGORIA, NOMBRECATE)
values (27, 'Mundo geek');
insert into CATEGORIA (CODCATEGORIA, NOMBRECATE)
values (28, 'Niños y padres');
insert into CATEGORIA (CODCATEGORIA, NOMBRECATE)
values (29, 'Peluquería y belleza');
insert into CATEGORIA (CODCATEGORIA, NOMBRECATE)
values (30, 'Productos');
insert into CATEGORIA (CODCATEGORIA, NOMBRECATE)
values (31, 'Salud y ejercicio');
insert into CATEGORIA (CODCATEGORIA, NOMBRECATE)
values (32, 'Tatuajes');
insert into CATEGORIA (CODCATEGORIA, NOMBRECATE)
values (33, 'Tecnología');
insert into CATEGORIA (CODCATEGORIA, NOMBRECATE)
values (34, 'Viajes');
commit;
prompt 35 records loaded
prompt Loading DEPARTAMENTO...
insert into DEPARTAMENTO (CODDEPARTAMENTO, NOMBREDEP)
values (5, 'AMAZONAS');
insert into DEPARTAMENTO (CODDEPARTAMENTO, NOMBREDEP)
values (6, 'ANCASH');
insert into DEPARTAMENTO (CODDEPARTAMENTO, NOMBREDEP)
values (7, 'APURIMAC');
insert into DEPARTAMENTO (CODDEPARTAMENTO, NOMBREDEP)
values (8, 'AYACUCHO');
insert into DEPARTAMENTO (CODDEPARTAMENTO, NOMBREDEP)
values (9, 'CAJAMARCA');
insert into DEPARTAMENTO (CODDEPARTAMENTO, NOMBREDEP)
values (10, 'CALLAO');
insert into DEPARTAMENTO (CODDEPARTAMENTO, NOMBREDEP)
values (11, 'CUSCO');
insert into DEPARTAMENTO (CODDEPARTAMENTO, NOMBREDEP)
values (12, 'HUANCAVELICA');
insert into DEPARTAMENTO (CODDEPARTAMENTO, NOMBREDEP)
values (13, 'HUANUCO');
insert into DEPARTAMENTO (CODDEPARTAMENTO, NOMBREDEP)
values (14, 'ICA');
insert into DEPARTAMENTO (CODDEPARTAMENTO, NOMBREDEP)
values (15, 'JUNIN');
insert into DEPARTAMENTO (CODDEPARTAMENTO, NOMBREDEP)
values (16, 'LA LIBERTAD');
insert into DEPARTAMENTO (CODDEPARTAMENTO, NOMBREDEP)
values (17, 'LAMBAYEQUE');
insert into DEPARTAMENTO (CODDEPARTAMENTO, NOMBREDEP)
values (18, 'LORETO');
insert into DEPARTAMENTO (CODDEPARTAMENTO, NOMBREDEP)
values (19, 'LIMA');
insert into DEPARTAMENTO (CODDEPARTAMENTO, NOMBREDEP)
values (20, 'MADRE DE DIOS');
insert into DEPARTAMENTO (CODDEPARTAMENTO, NOMBREDEP)
values (21, 'MOQUEGUA');
insert into DEPARTAMENTO (CODDEPARTAMENTO, NOMBREDEP)
values (22, 'PASCO');
insert into DEPARTAMENTO (CODDEPARTAMENTO, NOMBREDEP)
values (23, 'PUNO');
insert into DEPARTAMENTO (CODDEPARTAMENTO, NOMBREDEP)
values (24, 'TACNA');
insert into DEPARTAMENTO (CODDEPARTAMENTO, NOMBREDEP)
values (25, 'TUMBES');
insert into DEPARTAMENTO (CODDEPARTAMENTO, NOMBREDEP)
values (26, 'UCAYALI');
insert into DEPARTAMENTO (CODDEPARTAMENTO, NOMBREDEP)
values (1, 'AREQUIPA');
insert into DEPARTAMENTO (CODDEPARTAMENTO, NOMBREDEP)
values (2, 'SAN MARTÍN');
insert into DEPARTAMENTO (CODDEPARTAMENTO, NOMBREDEP)
values (3, 'CAJAMARCA');
insert into DEPARTAMENTO (CODDEPARTAMENTO, NOMBREDEP)
values (4, 'PIURA');
commit;
prompt 26 records loaded
prompt Loading PERFIL...
insert into PERFIL (CODPERFIL, NOMBRES, APELLIDOS, IMAGEN, F_CREACION, CODDEPARTAMENTO, DESCRIPCIONPER)
values (1, 'Jorge Fernando', 'Palacios Sánchez', '1.jpg', to_date('16-05-2016 08:07:55', 'dd-mm-yyyy hh24:mi:ss'), 4, 'Es es una pequeña decripcion de mi !!!');
insert into PERFIL (CODPERFIL, NOMBRES, APELLIDOS, IMAGEN, F_CREACION, CODDEPARTAMENTO, DESCRIPCIONPER)
values (25, 'Dereck', 'Palacios Jimenez', 'none.jpg', to_date('28-06-2016 05:50:05', 'dd-mm-yyyy hh24:mi:ss'), 4, null);
commit;
prompt 2 records loaded
prompt Loading ALBUM...
insert into ALBUM (CODALBUM, NOMBRE, DESCRIPCION, F_CREACION, CODPERFIL, CODCATEGORIA, NUM_PUBLIC)
values (28, 'OTRO ALBUM MAS', 'NINUN', to_date('22-06-2016 00:42:20', 'dd-mm-yyyy hh24:mi:ss'), 1, 5, 0);
insert into ALBUM (CODALBUM, NOMBRE, DESCRIPCION, F_CREACION, CODPERFIL, CODCATEGORIA, NUM_PUBLIC)
values (23, 'otro24', 'nuevo', to_date('20-06-2016 16:14:18', 'dd-mm-yyyy hh24:mi:ss'), 1, 17, 1);
insert into ALBUM (CODALBUM, NOMBRE, DESCRIPCION, F_CREACION, CODPERFIL, CODCATEGORIA, NUM_PUBLIC)
values (15, 'Este es mi album con algun nombre', 'una descripcion c', to_date('20-06-2016 15:50:30', 'dd-mm-yyyy hh24:mi:ss'), 1, 20, 4);
insert into ALBUM (CODALBUM, NOMBRE, DESCRIPCION, F_CREACION, CODPERFIL, CODCATEGORIA, NUM_PUBLIC)
values (42, 'Fotografias antiguas', 'Este es un Album para las fotografias antiguas que me impresionan', to_date('28-06-2016 19:50:42', 'dd-mm-yyyy hh24:mi:ss'), 1, 21, 1);
insert into ALBUM (CODALBUM, NOMBRE, DESCRIPCION, F_CREACION, CODPERFIL, CODCATEGORIA, NUM_PUBLIC)
values (41, 'Flive', 'Este este es tu primer Album, lo adquiriste cuando te registraste en Flive', to_date('28-06-2016 05:50:05', 'dd-mm-yyyy hh24:mi:ss'), 25, 41, 2);
commit;
prompt 5 records loaded
prompt Loading PUBLICACION...
insert into PUBLICACION (CODPUBLICACION, TITULO, IMAGEN, F_CREACION, N_LIKES, CODALBUM, TAGS)
values (21, 'Publicacion de prueba ', '1-845502.jpg', to_date('24-06-2016 21:56:57', 'dd-mm-yyyy hh24:mi:ss'), 1, 23, 'silicon valley richart gilfoid gared dinesh backman ');
insert into PUBLICACION (CODPUBLICACION, TITULO, IMAGEN, F_CREACION, N_LIKES, CODALBUM, TAGS)
values (24, 'ultima', '1-159190.jpg', to_date('26-06-2016 19:01:52', 'dd-mm-yyyy hh24:mi:ss'), 0, 15, 'ultima copa america 2016 ');
insert into PUBLICACION (CODPUBLICACION, TITULO, IMAGEN, F_CREACION, N_LIKES, CODALBUM, TAGS)
values (25, 'Publciacion de dereck', '25-971079.png', to_date('28-06-2016 06:59:25', 'dd-mm-yyyy hh24:mi:ss'), 1, 41, 'George Peppa Dereck ');
insert into PUBLICACION (CODPUBLICACION, TITULO, IMAGEN, F_CREACION, N_LIKES, CODALBUM, TAGS)
values (7, 'Esta una nueva publicacion del dia martes', '1-67247.jpg', to_date('21-06-2016 08:04:19', 'dd-mm-yyyy hh24:mi:ss'), 1, 15, 'Estudia voto consciente ');
insert into PUBLICACION (CODPUBLICACION, TITULO, IMAGEN, F_CREACION, N_LIKES, CODALBUM, TAGS)
values (27, 'Foto antigua', '1-462279.jpg', to_date('28-06-2016 19:55:12', 'dd-mm-yyyy hh24:mi:ss'), 2, 42, 'paita antigua ');
insert into PUBLICACION (CODPUBLICACION, TITULO, IMAGEN, F_CREACION, N_LIKES, CODALBUM, TAGS)
values (6, 'Esta una nueva publicacion ', '1-264583.png', to_date('20-06-2016 22:44:12', 'dd-mm-yyyy hh24:mi:ss'), 0, 15, 'boda album fotos nuevos ');
insert into PUBLICACION (CODPUBLICACION, TITULO, IMAGEN, F_CREACION, N_LIKES, CODALBUM, TAGS)
values (23, 'PPOPO', '1-190228.jpg', to_date('25-06-2016 22:35:13', 'dd-mm-yyyy hh24:mi:ss'), 1, 15, 'tag TEG TIG ');
insert into PUBLICACION (CODPUBLICACION, TITULO, IMAGEN, F_CREACION, N_LIKES, CODALBUM, TAGS)
values (26, 'letra  "G" amarilla', '25-937.jpg', to_date('28-06-2016 19:32:05', 'dd-mm-yyyy hh24:mi:ss'), 1, 41, 'letra G amarilla ');
commit;
prompt 8 records loaded
prompt Loading GUSTAR...
insert into GUSTAR (CODGUSTAR, CODPERFIL, CODPUBLICACION)
values (63, 1, 25);
insert into GUSTAR (CODGUSTAR, CODPERFIL, CODPUBLICACION)
values (47, 1, 7);
insert into GUSTAR (CODGUSTAR, CODPERFIL, CODPUBLICACION)
values (67, 1, 26);
insert into GUSTAR (CODGUSTAR, CODPERFIL, CODPUBLICACION)
values (68, 25, 27);
insert into GUSTAR (CODGUSTAR, CODPERFIL, CODPUBLICACION)
values (48, 1, 27);
insert into GUSTAR (CODGUSTAR, CODPERFIL, CODPUBLICACION)
values (32, 1, 23);
insert into GUSTAR (CODGUSTAR, CODPERFIL, CODPUBLICACION)
values (46, 1, 21);
commit;
prompt 7 records loaded
prompt Loading TIPONOTIFICACION...
insert into TIPONOTIFICACION (CODTIPONOTIFICACION, NOMBRE)
values (1, 'Like');
insert into TIPONOTIFICACION (CODTIPONOTIFICACION, NOMBRE)
values (2, 'Seguidor');
commit;
prompt 2 records loaded
prompt Loading NOTIFICACION...
insert into NOTIFICACION (CODNOTIFICACION, CODPUBLICACION, LEIDO, CODPERFIL, CODPERFIL_ORIGEN, CODTIPONOTIFICACION, FECHA)
values (1, 26, 0, null, 1, 2, to_date('05-07-2016 19:02:20', 'dd-mm-yyyy hh24:mi:ss'));
insert into NOTIFICACION (CODNOTIFICACION, CODPUBLICACION, LEIDO, CODPERFIL, CODPERFIL_ORIGEN, CODTIPONOTIFICACION, FECHA)
values (2, 26, 0, null, 1, 2, to_date('05-07-2016 19:06:36', 'dd-mm-yyyy hh24:mi:ss'));
insert into NOTIFICACION (CODNOTIFICACION, CODPUBLICACION, LEIDO, CODPERFIL, CODPERFIL_ORIGEN, CODTIPONOTIFICACION, FECHA)
values (3, 27, 0, null, 25, 2, to_date('05-07-2016 19:07:06', 'dd-mm-yyyy hh24:mi:ss'));
commit;
prompt 3 records loaded
prompt Loading SEGUIDOR...
insert into SEGUIDOR (CODRELACIONSEGUIDOR, CODPERFIL_SEGUIDO, CODPERFIL_SEGUIDOR)
values (7, 25, 1);
insert into SEGUIDOR (CODRELACIONSEGUIDOR, CODPERFIL_SEGUIDO, CODPERFIL_SEGUIDOR)
values (6, 1, 25);
commit;
prompt 2 records loaded
prompt Loading TAGALBUM...
prompt Table is empty
prompt Loading TAGPUBLICACION...
prompt Table is empty
prompt Loading USUARIO...
insert into USUARIO (CODUSUARIO, EMAIL, PASSWORD, CODPERFIL)
values (1, 'f.499@hotmail.com', '81dc9bdb52d04dc20036dbd8313ed055', 1);
insert into USUARIO (CODUSUARIO, EMAIL, PASSWORD, CODPERFIL)
values (25, 'dereck_n@hotmail.com', '84d2004bf28a2095230e8e14993d398d', 25);
commit;
prompt 2 records loaded
prompt Enabling foreign key constraints for PERFIL...
alter table PERFIL enable constraint FK_CODDEPARTAMENTO;
prompt Enabling foreign key constraints for ALBUM...
alter table ALBUM enable constraint FK_CODCATEGORIA;
alter table ALBUM enable constraint FK_CODPERFIL2;
prompt Enabling foreign key constraints for PUBLICACION...
alter table PUBLICACION enable constraint FK_CODALBUM;
prompt Enabling foreign key constraints for GUSTAR...
alter table GUSTAR enable constraint FK_CODPERFIL5;
alter table GUSTAR enable constraint FL_CODPUBLICACION;
prompt Enabling foreign key constraints for NOTIFICACION...
alter table NOTIFICACION enable constraint FK_CODPERFIL_ORIGEN;
alter table NOTIFICACION enable constraint FK_CODPERFIL4;
alter table NOTIFICACION enable constraint FK_CODTIPONOTIFICACION;
prompt Enabling foreign key constraints for SEGUIDOR...
alter table SEGUIDOR enable constraint FK_CODPERFIL_SEGUIDO;
alter table SEGUIDOR enable constraint FK_CODPERFIL_SEGUIDOR;
prompt Enabling foreign key constraints for TAGALBUM...
alter table TAGALBUM enable constraint FK_CODALBUM2;
prompt Enabling foreign key constraints for TAGPUBLICACION...
alter table TAGPUBLICACION enable constraint FK_CODPUBLICACION;
prompt Enabling foreign key constraints for USUARIO...
alter table USUARIO enable constraint FK_CODPERFIL;
prompt Enabling triggers for CATEGORIA...
alter table CATEGORIA enable all triggers;
prompt Enabling triggers for DEPARTAMENTO...
alter table DEPARTAMENTO enable all triggers;
prompt Enabling triggers for PERFIL...
alter table PERFIL enable all triggers;
prompt Enabling triggers for ALBUM...
alter table ALBUM enable all triggers;
prompt Enabling triggers for PUBLICACION...
alter table PUBLICACION enable all triggers;
prompt Enabling triggers for GUSTAR...
alter table GUSTAR enable all triggers;
prompt Enabling triggers for TIPONOTIFICACION...
alter table TIPONOTIFICACION enable all triggers;
prompt Enabling triggers for NOTIFICACION...
alter table NOTIFICACION enable all triggers;
prompt Enabling triggers for SEGUIDOR...
alter table SEGUIDOR enable all triggers;
prompt Enabling triggers for TAGALBUM...
alter table TAGALBUM enable all triggers;
prompt Enabling triggers for TAGPUBLICACION...
alter table TAGPUBLICACION enable all triggers;
prompt Enabling triggers for USUARIO...
alter table USUARIO enable all triggers;
set feedback on
set define on
prompt Done.
