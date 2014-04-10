-- Sequence: atualizacaocadastral.sequence_imovel_ramo_atividade_retorno
 
-- DROP SEQUENCE atualizacaocadastral.sequence_imovel_ramo_atividade_retorno;
 
CREATE SEQUENCE atualizacaocadastral.sequence_imovel_ramo_atividade_retorno
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE atualizacaocadastral.sequence_imovel_ramo_atividade_retorno
  OWNER TO gsan_admin;
GRANT ALL ON TABLE atualizacaocadastral.sequence_imovel_ramo_atividade_retorno TO gsan_admin;
GRANT SELECT, UPDATE ON TABLE atualizacaocadastral.sequence_imovel_ramo_atividade_retorno TO pg_aplic;
GRANT SELECT ON TABLE atualizacaocadastral.sequence_imovel_ramo_atividade_retorno TO pg_users;

-- Table: atualizacaocadastral.imovel_ramo_atividade_retorno
 
-- DROP TABLE atualizacaocadastral.imovel_ramo_atividade_retorno;
 
CREATE TABLE atualizacaocadastral.imovel_ramo_atividade_retorno
(
  irar_id integer NOT NULL, -- Id do imovel ramo atividade retorno (sequencial)
  imov_id integer, -- Id do Imovel
  ratv_id integer NOT NULL, -- Id do ramo de atividade
  imre_id integer,
  irar_tmultimaalteracao timestamp without time zone NOT NULL DEFAULT now(), -- Timestamp da ultima alteracao
  CONSTRAINT imovel_ramo_atividade_retorno_pkey PRIMARY KEY (irar_id),
  CONSTRAINT fk2_imovel_ramo_atividade_retorno FOREIGN KEY (ratv_id)
      REFERENCES cadastro.ramo_atividade (ratv_id) MATCH SIMPLE
      ON UPDATE RESTRICT ON DELETE RESTRICT
)
WITH (
  OIDS=FALSE
);
ALTER TABLE atualizacaocadastral.imovel_ramo_atividade_retorno
  OWNER TO gsan_admin;
GRANT ALL ON TABLE atualizacaocadastral.imovel_ramo_atividade_retorno TO gsan_admin;
GRANT SELECT, UPDATE, INSERT, DELETE ON TABLE atualizacaocadastral.imovel_ramo_atividade_retorno TO pg_aplic;
GRANT SELECT ON TABLE atualizacaocadastral.imovel_ramo_atividade_retorno TO pg_users;
COMMENT ON COLUMN atualizacaocadastral.imovel_ramo_atividade_retorno.irar_id IS 'Id do imovel ramo atividade retorno (sequencial)';
COMMENT ON COLUMN atualizacaocadastral.imovel_ramo_atividade_retorno.imov_id IS 'Id do Imovel';
COMMENT ON COLUMN atualizacaocadastral.imovel_ramo_atividade_retorno.ratv_id IS 'Id do ramo de atividade';
COMMENT ON COLUMN atualizacaocadastral.imovel_ramo_atividade_retorno.irar_tmultimaalteracao IS 'Timestamp da ultima alteracao';