----------------------------------------------
-- Export file for user USER_FLIVE          --
-- Created by ferna on 05/07/2016, 19:24:54 --
----------------------------------------------

spool FliveBackup-FernandoPalacios.log

prompt
prompt Creating table CATEGORIA
prompt ========================
prompt
create table CATEGORIA
(
  CODCATEGORIA NUMBER(5) not null,
  NOMBRECATE   VARCHAR2(100) not null
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
alter table CATEGORIA
  add constraint PK_CODCATEGORIA primary key (CODCATEGORIA)
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
prompt Creating table DEPARTAMENTO
prompt ===========================
prompt
create table DEPARTAMENTO
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
alter table DEPARTAMENTO
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
prompt Creating table PERFIL
prompt =====================
prompt
create table PERFIL
(
  CODPERFIL       NUMBER(5) not null,
  NOMBRES         VARCHAR2(30) not null,
  APELLIDOS       VARCHAR2(30) not null,
  IMAGEN          VARCHAR2(40) not null,
  F_CREACION      DATE not null,
  CODDEPARTAMENTO NUMBER(3),
  DESCRIPCIONPER  VARCHAR2(150)
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
alter table PERFIL
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
alter table PERFIL
  add constraint FK_CODDEPARTAMENTO foreign key (CODDEPARTAMENTO)
  references DEPARTAMENTO (CODDEPARTAMENTO);

prompt
prompt Creating table ALBUM
prompt ====================
prompt
create table ALBUM
(
  CODALBUM     NUMBER(10) not null,
  NOMBRE       VARCHAR2(50) not null,
  DESCRIPCION  VARCHAR2(200),
  F_CREACION   DATE not null,
  CODPERFIL    NUMBER(5) not null,
  CODCATEGORIA NUMBER(5) not null,
  NUM_PUBLIC   NUMBER(5) not null
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
alter table ALBUM
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
alter table ALBUM
  add constraint FK_CODCATEGORIA foreign key (CODCATEGORIA)
  references CATEGORIA (CODCATEGORIA);
alter table ALBUM
  add constraint FK_CODPERFIL2 foreign key (CODPERFIL)
  references PERFIL (CODPERFIL);

prompt
prompt Creating table PUBLICACION
prompt ==========================
prompt
create table PUBLICACION
(
  CODPUBLICACION NUMBER(10) not null,
  TITULO         VARCHAR2(50) not null,
  IMAGEN         VARCHAR2(50) not null,
  F_CREACION     DATE not null,
  N_LIKES        NUMBER(5) not null,
  CODALBUM       NUMBER(10) not null,
  TAGS           VARCHAR2(250) not null
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
alter table PUBLICACION
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
alter table PUBLICACION
  add constraint FK_CODALBUM foreign key (CODALBUM)
  references ALBUM (CODALBUM) on delete cascade;

prompt
prompt Creating table GUSTAR
prompt =====================
prompt
create table GUSTAR
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
alter table GUSTAR
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
alter table GUSTAR
  add constraint FK_CODPERFIL5 foreign key (CODPERFIL)
  references PERFIL (CODPERFIL);
alter table GUSTAR
  add constraint FL_CODPUBLICACION foreign key (CODPUBLICACION)
  references PUBLICACION (CODPUBLICACION);

prompt
prompt Creating table TIPONOTIFICACION
prompt ===============================
prompt
create table TIPONOTIFICACION
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
alter table TIPONOTIFICACION
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
create table NOTIFICACION
(
  CODNOTIFICACION     NUMBER(10) not null,
  CODPUBLICACION      NUMBER(10),
  LEIDO               NUMBER(1) not null,
  CODPERFIL           NUMBER(5),
  CODPERFIL_ORIGEN    NUMBER(5) not null,
  CODTIPONOTIFICACION NUMBER(1) not null,
  FECHA               DATE not null
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
alter table NOTIFICACION
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
alter table NOTIFICACION
  add constraint FK_CODPERFIL_ORIGEN foreign key (CODPERFIL_ORIGEN)
  references PERFIL (CODPERFIL);
alter table NOTIFICACION
  add constraint FK_CODPERFIL4 foreign key (CODPERFIL)
  references PERFIL (CODPERFIL);
alter table NOTIFICACION
  add constraint FK_CODTIPONOTIFICACION foreign key (CODTIPONOTIFICACION)
  references TIPONOTIFICACION (CODTIPONOTIFICACION);

prompt
prompt Creating table SEGUIDOR
prompt =======================
prompt
create table SEGUIDOR
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
alter table SEGUIDOR
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
alter table SEGUIDOR
  add constraint FK_CODPERFIL_SEGUIDO foreign key (CODPERFIL_SEGUIDO)
  references PERFIL (CODPERFIL);
alter table SEGUIDOR
  add constraint FK_CODPERFIL_SEGUIDOR foreign key (CODPERFIL_SEGUIDOR)
  references PERFIL (CODPERFIL);

prompt
prompt Creating table TAGALBUM
prompt =======================
prompt
create table TAGALBUM
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
alter table TAGALBUM
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
alter table TAGALBUM
  add constraint FK_CODALBUM2 foreign key (CODALBUM)
  references ALBUM (CODALBUM);

prompt
prompt Creating table TAGPUBLICACION
prompt =============================
prompt
create table TAGPUBLICACION
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
alter table TAGPUBLICACION
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
alter table TAGPUBLICACION
  add constraint FK_CODPUBLICACION foreign key (CODPUBLICACION)
  references PUBLICACION (CODPUBLICACION);

prompt
prompt Creating table USUARIO
prompt ======================
prompt
create table USUARIO
(
  CODUSUARIO NUMBER(5) not null,
  EMAIL      VARCHAR2(30) not null,
  PASSWORD   VARCHAR2(50) not null,
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
alter table USUARIO
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
alter table USUARIO
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
alter table USUARIO
  add constraint FK_CODPERFIL foreign key (CODPERFIL)
  references PERFIL (CODPERFIL);

prompt
prompt Creating sequence SEC_ALBUM
prompt ===========================
prompt
create sequence SEC_ALBUM
minvalue 1
maxvalue 9999999999
start with 61
increment by 1
cache 20;

prompt
prompt Creating sequence SEC_CATEGORIA
prompt ===============================
prompt
create sequence SEC_CATEGORIA
minvalue 1
maxvalue 99999
start with 61
increment by 1
cache 20;

prompt
prompt Creating sequence SEC_COMENTARIO
prompt ================================
prompt
create sequence SEC_COMENTARIO
minvalue 1
maxvalue 9999999999
start with 1
increment by 1
cache 20;

prompt
prompt Creating sequence SEC_DEPARTAMENTO
prompt ==================================
prompt
create sequence SEC_DEPARTAMENTO
minvalue 1
maxvalue 999
start with 41
increment by 1
cache 20;

prompt
prompt Creating sequence SEC_GUSTAR
prompt ============================
prompt
create sequence SEC_GUSTAR
minvalue 1
maxvalue 9999999999
start with 81
increment by 1
cache 20;

prompt
prompt Creating sequence SEC_NOTIFICACION
prompt ==================================
prompt
create sequence SEC_NOTIFICACION
minvalue 1
maxvalue 9999999999
start with 21
increment by 1
cache 20;

prompt
prompt Creating sequence SEC_PERFIL
prompt ============================
prompt
create sequence SEC_PERFIL
minvalue 1
maxvalue 99999
start with 41
increment by 1
cache 20;

prompt
prompt Creating sequence SEC_PROVINCIA
prompt ===============================
prompt
create sequence SEC_PROVINCIA
minvalue 1
maxvalue 999
start with 1
increment by 1
cache 20;

prompt
prompt Creating sequence SEC_PUBLICACION
prompt =================================
prompt
create sequence SEC_PUBLICACION
minvalue 1
maxvalue 9999999999
start with 41
increment by 1
cache 20;

prompt
prompt Creating sequence SEC_SEGUIDOR
prompt ==============================
prompt
create sequence SEC_SEGUIDOR
minvalue 1
maxvalue 9999999999
start with 21
increment by 1
cache 20;

prompt
prompt Creating sequence SEC_TAGALBUM
prompt ==============================
prompt
create sequence SEC_TAGALBUM
minvalue 1
maxvalue 99999
start with 1
increment by 1
cache 20;

prompt
prompt Creating sequence SEC_TAGPUBLICACION
prompt ====================================
prompt
create sequence SEC_TAGPUBLICACION
minvalue 1
maxvalue 99999
start with 1
increment by 1
cache 20;

prompt
prompt Creating sequence SEC_TIPONOTIFICACION
prompt ======================================
prompt
create sequence SEC_TIPONOTIFICACION
minvalue 1
maxvalue 9
start with 10
increment by 1
cache 20;

prompt
prompt Creating sequence SEC_USUARIO
prompt =============================
prompt
create sequence SEC_USUARIO
minvalue 1
maxvalue 99999
start with 41
increment by 1
cache 20;

prompt
prompt Creating package PAQ_ALBUM
prompt ==========================
prompt
create or replace package PAQ_ALBUM is

TYPE vCursor IS REF CURSOR;

procedure sp_buscarAlbumUsuario
  (vCodPerfil IN perfil.codperfil%type,
  Listado OUT vCursor);
  
  procedure sp_buscarAlbumDetalladoUsuario
  (vCodPerfil IN perfil.codperfil%type,
  Listado OUT vCursor);
  
 
  procedure sp_buscarAlbDetPorCodigoAlbum
  (vCodAlbum IN album.codalbum%type,
  Listado OUT vCursor);
  
  
end PAQ_ALBUM;
/

prompt
prompt Creating package PAQ_CATEG
prompt ==========================
prompt
create or replace package PAQ_CATEG is

TYPE vCursor IS REF CURSOR;

procedure sp_listarCategorias
  (Listado OUT vCursor);

end PAQ_CATEG;
/

prompt
prompt Creating package PAQ_DEPART
prompt ===========================
prompt
create or replace package paq_depart is
TYPE vCursor IS REF CURSOR;

procedure sp_listarDepartamentos
  (Listado OUT vCursor);

end paq_depart;
/

prompt
prompt Creating package PAQ_GUSTAR
prompt ===========================
prompt
create or replace package PAQ_GUSTAR is

type vCursor is ref Cursor;
 
procedure sp_buscarLikesPorPerfil(
  vCodPerfil IN perfil.codperfil%type,
  Listado OUT vCursor);

end PAQ_GUSTAR;
/

prompt
prompt Creating package PAQ_PERFIL
prompt ===========================
prompt
create or replace package PAQ_PERFIL is

  type vCursor is ref cursor;
  
  procedure sp_buscarPerfilPorCodigo(vCodPerfil IN perfil.codperfil%type,
    Listado OUT vCursor);
    
  procedure sp_buscarTodosPerfiles(Listado OUT vCursor);

end PAQ_PERFIL;
/

prompt
prompt Creating package PAQ_PUBLIC
prompt ===========================
prompt
create or replace package PAQ_PUBLIC is

  TYPE vCursor is ref Cursor;
  
  procedure sp_buscarPublicacionesDeAlbum
  (vCodAlbum IN album.codalbum%type,
  Listado OUT vCursor);
  
  procedure sp_buscarPublicacionesDePerfil(
    vCodPerfil IN perfil.codperfil%type,
    Listado OUT vCursor);
    
    
  procedure sp_buscarTodasPublicaciones(
    Listado OUT vCursor);
    
    
    procedure sp_buscarPublicPorCodigo(vCodPublicacion OUT publicacion.codpublicacion%type,Listado OUT vCursor);
end PAQ_PUBLIC;
/

prompt
prompt Creating package PAQ_SEGUI
prompt ==========================
prompt
create or replace package PAQ_SEGUI is

  TYPE vCursor is ref Cursor;
  
  procedure sp_verificarRelacionSeguidor
  (vCodSeguido IN seguidor.codperfil_seguido%type,
  vCodSeguidor IN seguidor.codperfil_seguidor%type,
  Listado OUT vCursor);
  
  
  procedure sp_buscarSeguidoresDePerfil(
    vCodPerfilSeguido IN seguidor.codperfil_seguido%type,
    Listado OUT vCursor);
    
  procedure sp_buscarAQuienSigueUnPerfil(
    vCodPerfilSeguidor IN seguidor.codperfil_seguidor%type,
    Listado OUT vCursor);
  
end PAQ_SEGUI;
/

prompt
prompt Creating package PAQ_USER
prompt =========================
prompt
create or replace package paq_user is

TYPE vCursor IS REF CURSOR;

procedure sp_buscarUsuario
  (vEmail IN usuario.email%type,
  vPassword IN usuario.password%type,
  vCodUsuario OUT usuario.codusuario%type
  );
  
procedure sp_loguearUsuario
  (vEmail IN usuario.email%type,
  vPassword IN usuario.password%type,
  Listado OUT vCursor);


procedure sp_buscarPerfilUsuario
  (vCodUsuario IN usuario.codusuario%type,
  Listado OUT vCursor);
  
  end paq_user;
/

prompt
prompt Creating procedure SP_ACTUALIZARALBUM
prompt =====================================
prompt
create or replace procedure sp_actualizarAlbum
(vCodAlbum IN album.codalbum%type,
 vNombre IN album.nombre%type,
 vDescripcion IN album.descripcion%type,
 vCodCategoria IN album.codcategoria%type) is
begin
  update album
     set nombre = vNombre,
         descripcion = vDescripcion,
         codcategoria = vCodCategoria
   where codalbum = vCodAlbum;
  
end sp_actualizarAlbum;
/

prompt
prompt Creating procedure SP_ACTUALIZARDATOSPERFIL
prompt ===========================================
prompt
create or replace procedure sp_actualizarDatosPerfil
    (vCodPerfil IN perfil.codperfil%type,
    vNombres IN perfil.nombres%type,
    vApellidos IN perfil.apellidos%type,
    vCodDepartamento IN perfil.coddepartamento%type,
    vDescripcionPer IN perfil.descripcionper%type)
    is
    begin
      update perfil
            set nombres = vNombres,
                apellidos = vApellidos,
                coddepartamento = vCoddepartamento,
                descripcionper=vDescripcionPer
          where codperfil = vCodperfil;
         
  end sp_actualizarDatosPerfil;
/

prompt
prompt Creating procedure SP_ACTUALIZARIMAGENPERFIL
prompt ============================================
prompt
create or replace procedure sp_actualizarImagenPerfil
    (vCodPerfil IN perfil.codperfil%type,
    vImagen IN perfil.imagen%type)
    is
    begin
    update Perfil set imagen=vImagen where codPerfil=vCodPerfil;
  end sp_actualizarImagenPerfil;
/

prompt
prompt Creating procedure SP_DEJARDESEGUIR
prompt ===================================
prompt
create or replace procedure sp_dejarDeSeguir
    (vCodPerfilSeguido IN seguidor.codperfil_seguido%type,
    vCodPerfilSeguidor IN seguidor.codperfil_seguidor%type)
    is
    begin
      delete seguidor
       where codPerfil_seguido=vCodPerfilSeguido and codPerfil_seguidor=vCodPerfilSeguidor;
  end sp_dejarDeSeguir;
/

prompt
prompt Creating procedure SP_ELIMINARALBUM
prompt ===================================
prompt
create or replace procedure sp_eliminarAlbum
    (vCodAlbum album.codalbum%type)
    is
    begin
      delete album where codalbum = vCodAlbum;
  end sp_eliminarAlbum;
/

prompt
prompt Creating procedure SP_ELIMINARLIKEPUBLICACION
prompt =============================================
prompt
create or replace procedure sp_eliminarLikePublicacion
    (vCodPerfil IN perfil.codperfil%type,
    vCodPublicacion IN publicacion.codpublicacion%type)
    is
    begin
     update publicacion
        set 
            n_likes = n_likes - 1
      where codpublicacion = vCodPublicacion;
      
      delete gustar
       where codperfil=vCodPerfil and codpublicacion=vCodPublicacion;
      
  end sp_eliminarLikePublicacion;
/

prompt
prompt Creating procedure SP_NOTIFICACIONLIKE
prompt ======================================
prompt
create or replace procedure sp_notificacionLike
    (vCodPerfilOrigen IN perfil.codperfil%type,
    vCodPublicacion IN publicacion.codpublicacion%type,
    vLeido IN notificacion.leido%type)
    is
    fecha_hoy Date;
    begin
    select SYSDATE into fecha_hoy from dual;
     insert into notificacion
       ( codpublicacion, leido, codperfil_origen, codtiponotificacion, fecha)
     values
       ( vCodPublicacion,vLeido, vCodperfilOrigen,2, fecha_hoy);
     
  end sp_notificacionLike;
/

prompt
prompt Creating procedure SP_NOTIFICACIONSEGUIDOR
prompt ==========================================
prompt
create or replace procedure sp_notificacionSeguidor
    (vCodPerfilOrigen IN perfil.codperfil%type,
    vCodPerfil IN perfil.codperfil%type,
    leido IN notificacion.leido%type)
    is
    fecha_hoy Date;
    begin
    select SYSDATE into fecha_hoy from dual;
     insert into notificacion
       (codperfil,leido, codperfil_origen, codtiponotificacion, fecha)
     values
       (vCodPerfil,0, vCodperfilOrigen, 2, fecha_hoy);
     
  end sp_notificacionSeguidor;
/

prompt
prompt Creating procedure SP_NUEVAPUBLICACION
prompt ======================================
prompt
create or replace procedure sp_nuevaPublicacion
    (vTitulo IN publicacion.titulo%type,
    vImagen IN publicacion.imagen%type,
    vCodAlbum IN publicacion.codpublicacion%type,
    vTags IN publicacion.tags%type)
    is
    fecha_hoy Date;
    n_pub number;
    begin
     select sysdate into fecha_hoy from dual;
     select num_public into n_pub from Album where codalbum=vCodAlbum;
     
     insert into publicacion
       (titulo, imagen, f_creacion, n_likes, codalbum, tags)
     values
       (vTitulo, vImagen, fecha_hoy, 0, vCodAlbum, vTags);
       
       update Album set num_public =n_pub+1 where codalbum =vCodAlbum;
    commit;
  end sp_nuevaPublicacion;
/

prompt
prompt Creating procedure SP_NUEVOALBUM
prompt ================================
prompt
create or replace procedure sp_nuevoAlbum
(vNombre IN album.nombre%type,
vDescripcion IN album.descripcion%type,
vCodPerfil IN album.codperfil%type,
vCodCategoria IN album.codcategoria%type) is
 fecha_hoy Date;
begin
  select SYSDATE into fecha_hoy from dual;
  insert into album
    ( nombre, descripcion, f_creacion, codperfil, codcategoria, num_public)
  values
    (vNombre, vDescripcion, fecha_hoy, vCodPerfil, vCodCategoria, 0);
  
end sp_nuevoAlbum;
/

prompt
prompt Creating procedure SP_NUEVOLIKEPUBLICACION
prompt ==========================================
prompt
create or replace procedure sp_nuevoLikePublicacion
    (vCodPerfil IN perfil.codperfil%type,
    vCodPublicacion IN publicacion.codpublicacion%type)
    is
    begin
     update publicacion
        set 
            n_likes = n_likes + 1
      where codpublicacion = vCodPublicacion;
      
      insert into gustar
        ( codperfil, codpublicacion)
      values
        (vCodPerfil, vCodPublicacion);
  end sp_nuevoLikePublicacion;
/

prompt
prompt Creating procedure SP_NUEVOSEGUIDOR
prompt ===================================
prompt
create or replace procedure sp_nuevoSeguidor
    (vCodSeguido IN seguidor.codperfil_seguido%type,
    vCodSeguidor IN seguidor.codperfil_seguidor%type)
    is
    begin
     insert into seguidor
       (codperfil_seguido, codperfil_seguidor)
     values
       (vCodSeguido, vCodSeguidor);
     
  end sp_nuevoSeguidor;
/

prompt
prompt Creating procedure SP_REGISTROUSUARIO
prompt =====================================
prompt
create or replace procedure sp_registroUsuario
    (vEmail IN usuario.email%type,
    vPassword IN usuario.password%type,
    vNombrePer IN perfil.nombres%type,
    vApellidoPer IN perfil.apellidos%type)
    is
    fecha_hoy Date;
    codPerfilRegistrado perfil.codperfil%type;
    begin
     select SYSDATE into fecha_hoy from dual;
     insert into Perfil(nombres,apellidos,imagen,f_creacion) 
     values (vNombrePer,vApellidoPer,'none.jpg',fecha_hoy) 
     RETURNING codperfil into codPerfilRegistrado;
    
    insert into Usuario(email,Password,codperfil) 
    values (vEmail,vPassword,codPerfilRegistrado);
    commit;
  end sp_registroUsuario;
/

prompt
prompt Creating package body PAQ_ALBUM
prompt ===============================
prompt
create or replace package body PAQ_ALBUM is

  procedure sp_buscarAlbumUsuario(
    vCodPerfil IN perfil.codperfil%type,
    Listado OUT vCursor)
    is 
    begin
      OPEN Listado FOR
      select a.codalbum, a.nombre from perfil p inner join album a on (p.codperfil=a.codperfil) where p.codperfil=vCodPerfil;
      end sp_buscarAlbumUsuario;
      
  procedure sp_buscarAlbumDetalladoUsuario(
    vCodPerfil IN perfil.codperfil%type,
    Listado OUT vCursor)
    is 
    begin
      OPEN Listado FOR
      select a.codalbum, a.nombre,a.descripcion,a.f_creacion,a.num_public,c.codcategoria,c.nombrecate,p.codperfil,p.nombres from perfil p inner join album a on (p.codperfil=a.codperfil) inner join categoria c on (a.codcategoria=c.codcategoria) where p.codperfil=vCodPerfil;
      end sp_buscarAlbumDetalladoUsuario;
      
      
  procedure sp_buscarAlbDetPorCodigoAlbum(
    vCodAlbum IN album.codalbum%type,
    Listado OUT vCursor)
    is 
    begin
      OPEN Listado FOR
      select a.codalbum, a.nombre,a.descripcion,a.f_creacion,a.num_public,c.codcategoria,c.nombrecate,p.codperfil, p.nombres
       from perfil p inner join album a on (p.codperfil=a.codperfil) inner join categoria c on (a.codcategoria=c.codcategoria) 
       where a.codalbum=vCodAlbum;
      end sp_buscarAlbDetPorCodigoAlbum;
end PAQ_ALBUM;
/

prompt
prompt Creating package body PAQ_CATEG
prompt ===============================
prompt
create or replace package body PAQ_CATEG is

procedure sp_listarCategorias
  (Listado OUT vCursor) is begin
  OPEN Listado FOR 
  select codcategoria, nombrecate from categoria;
  end sp_listarCategorias;
end PAQ_CATEG;
/

prompt
prompt Creating package body PAQ_DEPART
prompt ================================
prompt
create or replace package body paq_depart is
procedure sp_listarDepartamentos
  (Listado OUT vCursor) is begin
  OPEN Listado FOR
  select coddepartamento, nombredep from departamento;
  end sp_listarDepartamentos;
end paq_depart;
/

prompt
prompt Creating package body PAQ_GUSTAR
prompt ================================
prompt
create or replace package body PAQ_GUSTAR is

 procedure sp_buscarLikesPorPerfil
   (vCodPerfil IN perfil.codperfil%type,
   Listado OUT vCursor) 
   is begin
   OPEN Listado FOR 
   select p.codperfil,g.codgustar,pub.codpublicacion 
   from perfil p inner join gustar g on (p.codperfil=g.codperfil) 
   inner join publicacion pub on (pub.codpublicacion=g.codpublicacion)
   where p.codperfil=vCodPerfil;
   
   end sp_buscarLikesPorPerfil;
end PAQ_GUSTAR;
/

prompt
prompt Creating package body PAQ_PERFIL
prompt ================================
prompt
create or replace package body PAQ_PERFIL is


procedure sp_buscarPerfilPorCodigo(vCodPerfil IN perfil.codperfil%type,
    Listado OUT vCursor) is begin
    OPEN Listado for
    select p.codperfil, p.nombres, p.apellidos,p.imagen,p.f_creacion,p.descripcionper,dep.coddepartamento,dep.nombredep
     from Perfil p  left join departamento dep on (p.coddepartamento=dep.coddepartamento)  
     where p.codperfil=vCodPerfil;
    end sp_buscarPerfilPorCodigo;
    
procedure sp_buscarTodosPerfiles(Listado OUT vCursor) is begin
    OPEN Listado for
    select p.codperfil, p.nombres, p.apellidos,p.imagen,p.f_creacion,p.descripcionper,dep.coddepartamento,dep.nombredep
     from Perfil p  left join departamento dep on (p.coddepartamento=dep.coddepartamento);
    end sp_buscarTodosPerfiles;
    

end PAQ_PERFIL;
/

prompt
prompt Creating package body PAQ_PUBLIC
prompt ================================
prompt
create or replace package body PAQ_PUBLIC is
 procedure sp_buscarPublicacionesDeAlbum
  (vCodAlbum IN album.codalbum%type,
  Listado OUT vCursor) is begin
  OPEN Listado FOR
  select pub.codpublicacion, pub.titulo, pub.imagen, pub.f_creacion, pub.n_likes, alb.codalbum, pub.tags,alb.nombre as nombreAlbum,per.codperfil,per.nombres as nombresPer
  from publicacion pub inner join Album alb on (pub.codalbum=alb.codalbum) inner join Perfil per on (alb.codperfil=per.codperfil) where pub.codalbum=vCodAlbum;
  end sp_buscarPublicacionesDeAlbum;
  
   procedure sp_buscarPublicacionesDePerfil(
    vCodPerfil IN perfil.codperfil%type,
    Listado OUT vCursor) is begin
    OPEN Listado FOR
    select pub.codpublicacion, pub.titulo,pub.imagen,pub.f_creacion,pub.n_likes,pub.tags,al.codalbum,al.nombre,p.codperfil
    from publicacion pub  inner join album al on (pub.codalbum=al.codalbum) inner join perfil p on (p.codperfil=al.codperfil)
    where p.codperfil=vCodPerfil order by pub.f_creacion desc;
    
    end sp_buscarPublicacionesDePerfil;
    
    
   procedure sp_buscarTodasPublicaciones(
     Listado OUT Vcursor) is begin
     OPEN Listado FOR
     select pub.codpublicacion, pub.titulo,pub.imagen,pub.f_creacion,pub.n_likes,pub.tags,al.codalbum,al.nombre,al.codcategoria,ca.nombrecate,p.codperfil,p.nombres,p.apellidos,p.imagen as imagenPer
    from publicacion pub  inner join album al on (pub.codalbum=al.codalbum) inner join categoria ca on (al.codcategoria=ca.codcategoria) inner join perfil p on (p.codperfil=al.codperfil)
     order by pub.f_creacion desc;
    end sp_buscarTodasPublicaciones;
    
    
     procedure sp_buscarPublicPorCodigo(vCodPublicacion OUT publicacion.codpublicacion%type,Listado OUT vCursor) is begin
       OPEN Listado FOR 
       select pub.codpublicacion, titulo, imagen, f_creacion, n_likes, al.codalbum, tags, p.codperfil, p.nombres,p.apellidos from publicacion pub
        inner join album al on (pub.codalbum=al.codalbum) inner join perfil p on (p.codperfil=al.codperfil) where codpublicacion=vCodPublicacion;
       end sp_buscarPublicPorCodigo;
    
end PAQ_PUBLIC;
/

prompt
prompt Creating package body PAQ_SEGUI
prompt ===============================
prompt
create or replace package body PAQ_SEGUI is

 procedure sp_verificarRelacionSeguidor
  (vCodSeguido IN seguidor.codperfil_seguido%type,
  vCodSeguidor IN seguidor.codperfil_seguidor%type,
  Listado OUT vCursor) is begin
  OPEN Listado FOR
  select seg.codrelacionseguidor, p1.codperfil as codPerfilS, p1.nombres as nombresS,p1.apellidos as apellidosS , 
  p2.codperfil  as codPerfilSR, p2.nombres as nombresSR , p2.apellidos as apellidosSR from seguidor seg
   inner join perfil p1 on (p1.codperfil=seg.codperfil_seguido) 
   inner join perfil p2 on (seg.codperfil_seguidor=p2.codperfil) 
   where seg.codperfil_seguido=vCodSeguido and seg.codperfil_seguidor=vCodSeguidor;
  end sp_verificarRelacionSeguidor;
  
  
  procedure sp_buscarSeguidoresDePerfil(
    vCodPerfilSeguido IN seguidor.codperfil_seguido%type,
    Listado OUT vCursor) is begin
    OPEN Listado FOR 
    select seg.codrelacionseguidor, p1.codperfil as codPerfilS, p1.nombres as nombresS,p1.apellidos as apellidosS,
    p1.imagen as imagenS, dep1.nombredep as nombredepS,
    p2.codperfil  as codPerfilSR, p2.nombres as nombresSR , p2.apellidos as apellidosSR,
    p2.imagen as imagenSR ,dep2.nombredep as nombredepSR from seguidor seg
   inner join perfil p1 on (p1.codperfil=seg.codperfil_seguido) left join departamento dep1 on (p1.coddepartamento=dep1.coddepartamento)
   inner join perfil p2 on (seg.codperfil_seguidor=p2.codperfil) left join departamento dep2 on (p2.coddepartamento=dep2.coddepartamento)
   where seg.codperfil_seguido=vCodPerfilSeguido;
    end sp_buscarSeguidoresDePerfil;
    
  procedure sp_buscarAQuienSigueUnPerfil(
    vCodPerfilSeguidor IN seguidor.codperfil_seguidor%type,
    Listado OUT vCursor) is begin
    OPEN Listado FOR 
    select seg.codrelacionseguidor, p1.codperfil as codPerfilS, p1.nombres as nombresS,p1.apellidos as apellidosS,
    p1.imagen as imagenS, dep1.nombredep as nombredepS,
    p2.codperfil  as codPerfilSR, p2.nombres as nombresSR , p2.apellidos as apellidosSR,
    p2.imagen as imagenSR ,dep2.nombredep as nombredepSR from seguidor seg
   inner join perfil p1 on (p1.codperfil=seg.codperfil_seguido) left join departamento dep1 on (p1.coddepartamento=dep1.coddepartamento)
   inner join perfil p2 on (seg.codperfil_seguidor=p2.codperfil) left join departamento dep2 on (p2.coddepartamento=dep2.coddepartamento)
   where seg.codperfil_seguidor=vCodPerfilSeguidor;
    end sp_buscarAQuienSigueUnPerfil;
    
    
end PAQ_SEGUI;
/

prompt
prompt Creating package body PAQ_USER
prompt ==============================
prompt
create or replace package body paq_user is


  
  --Busqueda de usuario
  procedure sp_buscarUsuario
    (vEmail IN usuario.email%type,
    vPassword IN usuario.password%type,
    vCodUsuario OUT usuario.codusuario%type)
    is begin
       select codUsuario into vCodUsuario from Usuario where vEmail= email and vPassword =password;
  end sp_buscarUsuario;
  
  --Logueo de usuario
  procedure sp_loguearUsuario
  (vEmail IN usuario.email%type,
  vPassword IN usuario.password%type,
  Listado OUT vCursor)
  is begin
     OPEN Listado FOR
     select u.codusuario, u.email,p.codperfil,p.nombres,p.apellidos, p.imagen from Usuario u inner join Perfil p ON (u.codperfil=p.codperfil) where vEmail=u.email and vPassword=u.password;
  end sp_loguearUsuario;

    --BuscarPerfil
   procedure sp_buscarPerfilUsuario
  (vCodUsuario IN usuario.codusuario%type,
  Listado OUT vCursor)
  is begin
  OPEN Listado FOR
       select u.codusuario,u.email, p.codperfil, nombres, apellidos, imagen, f_creacion,descripcionper, d.coddepartamento ,d.nombredep from usuario u inner join perfil p on(u.codperfil=p.codperfil) left join departamento d on (p.coddepartamento=d.coddepartamento)where vCodUsuario= u.codusuario;
  end sp_buscarPerfilUsuario;
  
  end paq_user;
/

prompt
prompt Creating trigger TG_ALBUM
prompt =========================
prompt
create or replace trigger tg_album
  before insert on album
  for each row
declare
  -- local variables here
begin
  select sec_album.nextval into :new.codalbum from dual;
end tg_album;
/

prompt
prompt Creating trigger TG_CATEGORIA
prompt =============================
prompt
create or replace trigger tg_categoria
  before insert on categoria
  for each row
declare
  -- local variables here
begin
  select sec_categoria.nextval into :new.codcategoria from dual;
end tg_categoria;
/

prompt
prompt Creating trigger TG_DEPARTAMENTO
prompt ================================
prompt
create or replace trigger tg_departamento
  before insert on departamento
  for each row
declare
  -- local variables here
begin
  select sec_departamento.nextval into :new.coddepartamento from dual;
end tg_departamento;
/

prompt
prompt Creating trigger TG_GUSTAR
prompt ==========================
prompt
create or replace trigger tg_gustar
  before insert on gustar
  for each row
declare
  -- local variables here
begin
  select sec_gustar.nextval into :new.codgustar from dual;
end tg_gustar;
/

prompt
prompt Creating trigger TG_NOTIFICACION
prompt ================================
prompt
create or replace trigger tg_notificacion
  before insert on notificacion
  for each row
declare
  -- local variables here
begin
  select sec_notificacion.nextval into :new.codnotificacion from dual;
end tg_notificacion;
/

prompt
prompt Creating trigger TG_PEFIL
prompt =========================
prompt
create or replace trigger tg_pefil
  before insert on perfil
  for each row
declare
  -- local variables here
begin
  select sec_perfil.nextval into :new.codperfil from dual;
end tg_perfil;
/

prompt
prompt Creating trigger TG_PUBLICACION
prompt ===============================
prompt
create or replace trigger tg_publicacion
  before insert on publicacion
  for each row
declare
  -- local variables here
begin
  select sec_publicacion.nextval into :new.codpublicacion from dual;
end tg_publicacion;
/

prompt
prompt Creating trigger TG_SEGUIDOR
prompt ============================
prompt
create or replace trigger tg_seguidor
  before insert on seguidor
  for each row
declare
  -- local variables here
begin
  select sec_seguidor.nextval into :new.codrelacionseguidor from dual;
end tg_seguidor;
/

prompt
prompt Creating trigger TG_TAGALBUM
prompt ============================
prompt
create or replace trigger tg_tagalbum
  before insert on tagalbum
  for each row
declare
  -- local variables here
begin
  select sec_tagalbum.nextval into :new.codtagalbum from dual;
end tg_taalbum;
/

prompt
prompt Creating trigger TG_TAGPUBLICACION
prompt ==================================
prompt
create or replace trigger tg_tagpublicacion
  before insert on tagpublicacion
  for each row
declare
  -- local variables here
begin
  select sec_tagpublicacion.nextval into :new.codtagpublicacion from dual;
end tg_tagpublicacion;
/

prompt
prompt Creating trigger TG_TIPONOTIFICACION
prompt ====================================
prompt
create or replace trigger tg_tiponotificacion
  before insert on tiponotificacion
  for each row
declare
  -- local variables here
begin
  select sec_tiponotificacion.nextval into :new.codtiponotificacion from dual;
end tg_tiponotificacion;
/

prompt
prompt Creating trigger TG_USUARIO
prompt ===========================
prompt
create or replace trigger tg_usuario
  before insert on usuario
  for each row
declare
  -- local variables here
begin
  select sec_usuario.nextval into :new.codusuario from dual;
end tg_usuario;
/


spool off
