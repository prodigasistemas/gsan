-- // criar usuario
-- Migration SQL that makes the change goes here.

insert into seguranca.usuario(usur_id
, utip_id
, usur_nmlogin
, usur_nmsenha
, usst_id
, usab_id
, empr_id
, usur_nmusuario
, usur_ictiporelatoriopadrao
, usur_icexibemensagem
)
values(1
, 1
, 'admin'
, 'eBABU3RLsww3GGqUYPL/0SUjE9o='
, 1
, 1
, 1
, 'Administrador'
, 1
, 1
);


-- //@UNDO
-- SQL to undo the change goes here.

delete from seguranca.usuario;
