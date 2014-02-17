--Rollback
--alter table atualizacaocadastral.imovel_retorno 
--drop column muni_id, 
--drop column imac_nnmunicipio, 
--drop column lgtp_id, 
--drop column logr_id, 
--drop column imac_dslogradouro,
--drop column imac_nmbairro,
--drop column imac_cdcep;

ALTER TABLE atualizacaocadastral.imovel_retorno
ADD COLUMN muni_id integer,
ADD COLUMN imac_nmmunicipio character varying(30),
ADD COLUMN lgtp_id integer,
ADD COLUMN logr_id integer,
ADD COLUMN imac_dslogradouro character varying(40),
ADD COLUMN imac_nmbairro character varying(30),
ADD COLUMN imac_cdcep integer;