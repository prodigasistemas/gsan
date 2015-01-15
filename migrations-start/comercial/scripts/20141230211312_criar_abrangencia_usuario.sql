-- // criar abrangencia usuario
-- Migration SQL that makes the change goes here.

insert into  seguranca.usuario_abrangencia(usab_id
, usab_idsuperior
, usab_dsusuarioabrangencia
, usab_dsabreviado
, usab_icuso)

values (1
, 1
, 'ESTADO'
, 'EST   '
, 1);

-- //@UNDO
-- SQL to undo the change goes here.


DELETE FROM seguranca.usuario_abrangencia;