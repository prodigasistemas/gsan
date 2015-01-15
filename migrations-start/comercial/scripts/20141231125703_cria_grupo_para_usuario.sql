-- // cria grupo para usuario
-- Migration SQL that makes the change goes here.

insert into seguranca.grupo(grup_id
, grup_dsgrupo
, grup_dsabreviado
, grup_icuso
)
values(1
, 'ADMINISTRADOR'
, 'ADM'
, 1
);

-- //@UNDO
-- SQL to undo the change goes here.

delete from seguranca.grupo;