ALTER TABLE atualizacaocadastral.imovel_controle_atlz_cad  DROP CONSTRAINT fk1_imovel_controle_atlz_cad;

ALTER TABLE atualizacaocadastral.imovel_controle_atlz_cad ALTER COLUMN imov_id DROP NOT NULL;

ALTER TABLE atualizacaocadastral.imovel_controle_atlz_cad ADD COLUMN imre_id INTEGER CONSTRAINT fk1_imovel_controle_atlz_cad
REFERENCES atualizacaocadastral.imovel_retorno (imre_id) MATCH SIMPLE
ON UPDATE RESTRICT ON DELETE RESTRICT;

ALTER TABLE atualizacaocadastral.imovel_controle_atlz_cad ADD COLUMN icac_tmprocessamento TIMESTAMP without TIME ZONE;
