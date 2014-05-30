-- // Add colunas tabelas retorno recadastramento
-- Migration SQL that makes the change goes here.
ALTER TABLE atualizacaocadastral.imovel_retorno ADD COLUMN lest_id INTEGER
	CONSTRAINT fk4_imovel_retorno REFERENCES atendimentopublico.ligacao_esgoto_situacao (lest_id)
	MATCH SIMPLE ON UPDATE RESTRICT ON DELETE RESTRICT;


ALTER TABLE atualizacaocadastral.imovel_retorno ADD COLUMN rlin_id INTEGER
	CONSTRAINT fk5_imovel_retorno REFERENCES atendimentopublico.ramal_local_instalacao (rlin_id)
	MATCH SIMPLE ON UPDATE RESTRICT ON DELETE RESTRICT;
	

ALTER TABLE atualizacaocadastral.imovel_retorno ADD COLUMN himc_id INTEGER
	CONSTRAINT fk6_imovel_retorno REFERENCES micromedicao.hidrometro_marca (himc_id)
	MATCH SIMPLE ON UPDATE RESTRICT ON DELETE RESTRICT;


ALTER TABLE atualizacaocadastral.imovel_retorno ADD COLUMN hicp_id INTEGER
	CONSTRAINT fk7_imovel_retorno REFERENCES micromedicao.hidrometro_capacidade (hicp_id)
	MATCH SIMPLE ON UPDATE RESTRICT ON DELETE RESTRICT;


ALTER TABLE atualizacaocadastral.cliente_endereco_retorno ADD COLUMN lgtp_id INTEGER
	CONSTRAINT fk3_cliente_endereco_retorno REFERENCES cadastro.logradouro_tipo (lgtp_id)
	MATCH SIMPLE ON UPDATE RESTRICT ON DELETE RESTRICT;




-- //@UNDO
-- SQL to undo the change goes here.
ALTER TABLE atualizacaocadastral.imovel_retorno DROP COLUMN lest_id;
ALTER TABLE atualizacaocadastral.imovel_retorno DROP CONSTRAINT fk4_imovel_retorno;
ALTER TABLE atualizacaocadastral.imovel_retorno DROP COLUMN rlin_id;
ALTER TABLE atualizacaocadastral.imovel_retorno DROP CONSTRAINT fk5_imovel_retorno;
ALTER TABLE atualizacaocadastral.imovel_retorno DROP COLUMN himc_id;
ALTER TABLE atualizacaocadastral.imovel_retorno DROP CONSTRAINT fk6_imovel_retorno;
ALTER TABLE atualizacaocadastral.imovel_retorno DROP COLUMN hicp_id;
ALTER TABLE atualizacaocadastral.imovel_retorno DROP CONSTRAINT fk7_imovel_retorno;
ALTER TABLE atualizacaocadastral.cliente_endereco_retorno DROP COLUMN lgtp_id;
ALTER TABLE atualizacaocadastral.cliente_endereco_retorno DROP CONSTRAINT fk3_cliente_endereco_retorno;