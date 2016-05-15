----------------------------------------------
-- Export file for user USER_FLIVE          --
-- Created by ferna on 14/05/2016, 18:55:57 --
----------------------------------------------

spool backupFlive.log

prompt
prompt Creating table DEPARTAMENTO
prompt ===========================
prompt
create table USER_FLIVE.DEPARTAMENTO
(
  CODDEPARTAMENTO NUMBER(3) not null,
  NOMBREDEP       VARCHAR2(20) not null
)
tablespace TB_FLIVE
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
alter table USER_FLIVE.DEPARTAMENTO
  add constraint PK_CODDEPARTAMENTO primary key (CODDEPARTAMENTO)
  using index 
  tablespace TB_FLIVE
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table PROVINCIA
prompt ========================
prompt
create table USER_FLIVE.PROVINCIA
(
  CODPROVINCIA    NUMBER(3) not null,
  NOMBREPROV      VARCHAR2(20) not null,
  CODDEPARTAMENTO NUMBER(3) not null
)
tablespace TB_FLIVE
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
alter table USER_FLIVE.PROVINCIA
  add constraint PK_CODPROVINCIA primary key (CODPROVINCIA)
  using index 
  tablespace TB_FLIVE
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
alter table USER_FLIVE.PROVINCIA
  add constraint FK_CODDEPARTAMENTO foreign key (CODDEPARTAMENTO)
  references USER_FLIVE.DEPARTAMENTO (CODDEPARTAMENTO);

prompt
prompt Creating table PERFIL
prompt =====================
prompt
create table USER_FLIVE.PERFIL
(
  CODPERFIL    NUMBER(5) not null,
  NOMBRES      VARCHAR2(30) not null,
  APELLIDOS    VARCHAR2(30) not null,
  DNI          CHAR(8) not null,
  CELULAR      NUMBER(9),
  IMAGEN       VARCHAR2(40) not null,
  F_CREACION   DATE not null,
  CODPROVINCIA NUMBER(3) not null
)
tablespace TB_FLIVE
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
alter table USER_FLIVE.PERFIL
  add constraint PK_CODPERFIL primary key (CODPERFIL)
  using index 
  tablespace TB_FLIVE
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
alter table USER_FLIVE.PERFIL
  add constraint U_DNI unique (DNI)
  using index 
  tablespace TB_FLIVE
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
alter table USER_FLIVE.PERFIL
  add constraint FK_CODPROVINCIA foreign key (CODPROVINCIA)
  references USER_FLIVE.PROVINCIA (CODPROVINCIA);

prompt
prompt Creating table ALBUM
prompt ====================
prompt
create table USER_FLIVE.ALBUM
(
  CODALBUM    NUMBER(10) not null,
  NOMBRE      VARCHAR2(50) not null,
  DESCRIPCION VARCHAR2(200),
  F_CREACION  DATE not null,
  CODPERFIL   NUMBER(5) not null
)
tablespace TB_FLIVE
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
alter table USER_FLIVE.ALBUM
  add constraint PK_CODALBUM primary key (CODALBUM)
  using index 
  tablespace TB_FLIVE
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
alter table USER_FLIVE.ALBUM
  add constraint FK_CODPERFIL2 foreign key (CODPERFIL)
  references USER_FLIVE.PERFIL (CODPERFIL);

prompt
prompt Creating table PUBLICACION
prompt ==========================
prompt
create table USER_FLIVE.PUBLICACION
(
  CODPUBLICACION NUMBER(10) not null,
  TITULO         VARCHAR2(50) not null,
  IMAGEN         VARCHAR2(50) not null,
  F_CREACION     DATE not null,
  N_LIKES        NUMBER(5) not null,
  CODALBUM       NUMBER(10) not null
)
tablespace TB_FLIVE
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
alter table USER_FLIVE.PUBLICACION
  add constraint PK_CODPUBLICACION primary key (CODPUBLICACION)
  using index 
  tablespace TB_FLIVE
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
alter table USER_FLIVE.PUBLICACION
  add constraint FK_CODALBUM foreign key (CODALBUM)
  references USER_FLIVE.ALBUM (CODALBUM);

prompt
prompt Creating table COMENTARIO
prompt =========================
prompt
create table USER_FLIVE.COMENTARIO
(
  CODCOMENTARIO  NUMBER(10) not null,
  F_CREACION     DATE not null,
  TEXTO          VARCHAR2(200) not null,
  CODPUBLICACION NUMBER(10) not null,
  CODPERFIL      NUMBER(5) not null
)
tablespace TB_FLIVE
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
alter table USER_FLIVE.COMENTARIO
  add constraint PK_CODCOMENTARIO primary key (CODCOMENTARIO)
  using index 
  tablespace TB_FLIVE
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
alter table USER_FLIVE.COMENTARIO
  add constraint FK_CODPERFIL3 foreign key (CODPERFIL)
  references USER_FLIVE.PERFIL (CODPERFIL);
alter table USER_FLIVE.COMENTARIO
  add constraint FK_CODPUBLICACION3 foreign key (CODPUBLICACION)
  references USER_FLIVE.PUBLICACION (CODPUBLICACION);

prompt
prompt Creating table GUSTAR
prompt =====================
prompt
create table USER_FLIVE.GUSTAR
(
  CODGUSTAR      NUMBER(10) not null,
  CODPERFIL      NUMBER(5) not null,
  CODPUBLICACION NUMBER(10) not null
)
tablespace TB_FLIVE
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
alter table USER_FLIVE.GUSTAR
  add constraint PK_CODGUSTAR primary key (CODGUSTAR)
  using index 
  tablespace TB_FLIVE
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
alter table USER_FLIVE.GUSTAR
  add constraint FK_CODPERFIL5 foreign key (CODPERFIL)
  references USER_FLIVE.PERFIL (CODPERFIL);
alter table USER_FLIVE.GUSTAR
  add constraint FL_CODPUBLICACION foreign key (CODPUBLICACION)
  references USER_FLIVE.PUBLICACION (CODPUBLICACION);

prompt
prompt Creating table TIPONOTIFICACION
prompt ===============================
prompt
create table USER_FLIVE.TIPONOTIFICACION
(
  CODTIPONOTIFICACION NUMBER(1) not null,
  NOMBRE              VARCHAR2(30) not null
)
tablespace TB_FLIVE
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
alter table USER_FLIVE.TIPONOTIFICACION
  add constraint PK_CODTIPONOTIFICACION primary key (CODTIPONOTIFICACION)
  using index 
  tablespace TB_FLIVE
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table NOTIFICACION
prompt ===========================
prompt
create table USER_FLIVE.NOTIFICACION
(
  CODNOTIFICACION     NUMBER(10) not null,
  CONTENIDO           VARCHAR2(100) not null,
  LEIDO               BINARY_DOUBLE not null,
  CODPERFIL           NUMBER(5) not null,
  CODPERFIL_ORIGEN    NUMBER(5) not null,
  CODTIPONOTIFICACION NUMBER(1) not null
)
tablespace TB_FLIVE
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
alter table USER_FLIVE.NOTIFICACION
  add constraint PK_CODNOTIFICACION primary key (CODNOTIFICACION)
  using index 
  tablespace TB_FLIVE
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
alter table USER_FLIVE.NOTIFICACION
  add constraint FK_CODPERFIL_ORIGEN foreign key (CODPERFIL_ORIGEN)
  references USER_FLIVE.PERFIL (CODPERFIL);
alter table USER_FLIVE.NOTIFICACION
  add constraint FK_CODPERFIL4 foreign key (CODPERFIL)
  references USER_FLIVE.PERFIL (CODPERFIL);
alter table USER_FLIVE.NOTIFICACION
  add constraint FK_CODTIPONOTIFICACION foreign key (CODTIPONOTIFICACION)
  references USER_FLIVE.TIPONOTIFICACION (CODTIPONOTIFICACION);

prompt
prompt Creating table SEGUIDOR
prompt =======================
prompt
create table USER_FLIVE.SEGUIDOR
(
  CODRELACIONSEGUIDOR NUMBER(10) not null,
  CODPERFIL_SEGUIDO   NUMBER(5) not null,
  CODPERFIL_SEGUIDOR  NUMBER(5) not null
)
tablespace TB_FLIVE
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
alter table USER_FLIVE.SEGUIDOR
  add constraint PK_CODRELACIONSEGUIDOR primary key (CODRELACIONSEGUIDOR)
  using index 
  tablespace TB_FLIVE
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
alter table USER_FLIVE.SEGUIDOR
  add constraint FK_CODPERFIL_SEGUIDO foreign key (CODPERFIL_SEGUIDO)
  references USER_FLIVE.PERFIL (CODPERFIL);
alter table USER_FLIVE.SEGUIDOR
  add constraint FK_CODPERFIL_SEGUIDOR foreign key (CODPERFIL_SEGUIDOR)
  references USER_FLIVE.PERFIL (CODPERFIL);

prompt
prompt Creating table TAGALBUM
prompt =======================
prompt
create table USER_FLIVE.TAGALBUM
(
  CODTAGALBUM NUMBER(5) not null,
  NOMBRE      VARCHAR2(20) not null,
  CODALBUM    NUMBER(10) not null
)
tablespace TB_FLIVE
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
alter table USER_FLIVE.TAGALBUM
  add constraint PK_CODTAGALBUM primary key (CODTAGALBUM)
  using index 
  tablespace TB_FLIVE
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
alter table USER_FLIVE.TAGALBUM
  add constraint FK_CODALBUM2 foreign key (CODALBUM)
  references USER_FLIVE.ALBUM (CODALBUM);

prompt
prompt Creating table TAGPUBLICACION
prompt =============================
prompt
create table USER_FLIVE.TAGPUBLICACION
(
  CODTAGPUBLICACION NUMBER(5) not null,
  NOMBRE            VARCHAR2(20) not null,
  CODPUBLICACION    NUMBER(10) not null
)
tablespace TB_FLIVE
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
alter table USER_FLIVE.TAGPUBLICACION
  add constraint PK_CODTAGPUBLICACION primary key (CODTAGPUBLICACION)
  using index 
  tablespace TB_FLIVE
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
alter table USER_FLIVE.TAGPUBLICACION
  add constraint FK_CODPUBLICACION foreign key (CODPUBLICACION)
  references USER_FLIVE.PUBLICACION (CODPUBLICACION);

prompt
prompt Creating table USUARIO
prompt ======================
prompt
create table USER_FLIVE.USUARIO
(
  CODUSUARIO NUMBER(5) not null,
  EMAIL      VARCHAR2(30) not null,
  PASSWORD   VARCHAR2(15) not null,
  CODPERFIL  NUMBER(5) not null
)
tablespace TB_FLIVE
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
alter table USER_FLIVE.USUARIO
  add constraint PK_CODUSUARIO primary key (CODUSUARIO)
  using index 
  tablespace TB_FLIVE
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
alter table USER_FLIVE.USUARIO
  add constraint U_EMAIL unique (EMAIL)
  using index 
  tablespace TB_FLIVE
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
alter table USER_FLIVE.USUARIO
  add constraint FK_CODPERFIL foreign key (CODPERFIL)
  references USER_FLIVE.PERFIL (CODPERFIL);


spool off
