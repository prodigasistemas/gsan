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

ALTER TABLE atualizacaocadastral.imovel_retorno ADD loca_id integer;
ALTER TABLE atualizacaocadastral.imovel_retorno ADD imre_cdsetorcomercial integer;
ALTER TABLE atualizacaocadastral.imovel_retorno ADD imre_nnquadra integer;

/*
ALTER TABLE atualizacaocadastral.imovel_retorno RENAME COLUMN imac_nnimovel TO imre_nnimovel;
ALTER TABLE atualizacaocadastral.imovel_retorno RENAME COLUMN imac_dscomplementoendereco TO imre_dscomplementoendereco;
ALTER TABLE atualizacaocadastral.imovel_retorno RENAME COLUMN imac_nnpontosutilizacao TO imre_nnpontosutilizacao;
ALTER TABLE atualizacaocadastral.imovel_retorno RENAME COLUMN imac_nnmorador TO imre_nnmorador;
ALTER TABLE atualizacaocadastral.imovel_retorno RENAME COLUMN imac_nniptu TO imre_nniptu;
ALTER TABLE atualizacaocadastral.imovel_retorno RENAME COLUMN imac_nncoordenadax TO imre_nncoordenadax;
ALTER TABLE atualizacaocadastral.imovel_retorno RENAME COLUMN imac_nncoordenaday TO imre_nncoordenaday;
ALTER TABLE atualizacaocadastral.imovel_retorno RENAME COLUMN imac_tmultimaalteracao TO imre_tmultimaalteracao;
ALTER TABLE atualizacaocadastral.imovel_retorno RENAME COLUMN imac_nnhidrometro TO imac_nnhidrometro;
ALTER TABLE atualizacaocadastral.imovel_retorno RENAME COLUMN imac_nnmedidorenergia TO imre_nnmedidorenergia;
ALTER TABLE atualizacaocadastral.imovel_retorno RENAME COLUMN imac_dsoutrasinformacoes TO imre_dsoutrasinformacoes;
ALTER TABLE atualizacaocadastral.imovel_retorno RENAME COLUMN imac_tipoentrevistado TO imre_tipoentrevistado;
ALTER TABLE atualizacaocadastral.imovel_retorno RENAME COLUMN imac_tipooperacao TO imre_tipooperacao;
ALTER TABLE atualizacaocadastral.imovel_retorno RENAME COLUMN imac_nmmunicipio TO imre_nmmunicipio;
ALTER TABLE atualizacaocadastral.imovel_retorno RENAME COLUMN imac_dslogradouro TO imre_dslogradouro;
ALTER TABLE atualizacaocadastral.imovel_retorno RENAME COLUMN imac_nmbairro TO imre_nmbairro;
ALTER TABLE atualizacaocadastral.imovel_retorno RENAME COLUMN imac_cdcep TO imre_cdcep;
*/



