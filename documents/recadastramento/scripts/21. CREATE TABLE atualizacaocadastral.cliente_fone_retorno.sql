-- Sequence: atualizacaocadastral.sequence_cliente_fone_retorno
 
-- DROP SEQUENCE atualizacaocadastral.sequence_cliente_fone_retorno;
 
CREATE SEQUENCE atualizacaocadastral.sequence_cliente_fone_retorno
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE atualizacaocadastral.sequence_cliente_fone_retorno
  OWNER TO gsan_admin;
GRANT ALL ON TABLE atualizacaocadastral.sequence_cliente_fone_retorno TO gsan_admin;
GRANT SELECT, UPDATE ON TABLE atualizacaocadastral.sequence_cliente_fone_retorno TO pg_aplic;
GRANT SELECT ON TABLE atualizacaocadastral.sequence_cliente_fone_retorno TO pg_users;
 
 
 
-- Table: atualizacaocadastral.cliente_fone_retorno
 
-- DROP TABLE atualizacaocadastral.cliente_fone_retorno;
 
CREATE TABLE atualizacaocadastral.cliente_fone_retorno
(
  clfr_id integer NOT NULL, -- Id do cliente_fone (Sequencial)
  clfr_cdddd character(2), -- Codigo do DDD do Telefone
  clfr_nnfone character varying(9), -- Numero do Telefone
  clfr_tmultimaalteracao timestamp without time zone NOT NULL DEFAULT now(), -- Timestamp
  fnet_id integer NOT NULL, -- Id do Tipo de Telefone
  clie_id integer, -- Id do Cliente
  clir_id integer,
  CONSTRAINT cliente_fone_retorno_pkey PRIMARY KEY (clfr_id),
  CONSTRAINT fk2_cliente_fone FOREIGN KEY (fnet_id)
      REFERENCES cadastro.fone_tipo (fnet_id) MATCH SIMPLE
      ON UPDATE RESTRICT ON DELETE RESTRICT
)
WITH (
  OIDS=FALSE
);
ALTER TABLE atualizacaocadastral.cliente_fone_retorno
  OWNER TO gsan_admin;
GRANT ALL ON TABLE atualizacaocadastral.cliente_fone_retorno TO gsan_admin;
GRANT SELECT, UPDATE, INSERT, DELETE ON TABLE atualizacaocadastral.cliente_fone_retorno TO pg_aplic;
GRANT SELECT ON TABLE atualizacaocadastral.cliente_fone_retorno TO pg_users;
COMMENT ON TABLE atualizacaocadastral.cliente_fone_retorno
  IS 'Relacao de todos os cliente com seus respectivos telefones dentro dos tipos permitidos';
COMMENT ON COLUMN atualizacaocadastral.cliente_fone_retorno.clfr_id IS 'Id do cliente_fone (Sequencial)';
COMMENT ON COLUMN atualizacaocadastral.cliente_fone_retorno.clfr_cdddd IS 'Codigo do DDD do Telefone';
COMMENT ON COLUMN atualizacaocadastral.cliente_fone_retorno.clfr_nnfone IS 'Numero do Telefone';
COMMENT ON COLUMN atualizacaocadastral.cliente_fone_retorno.clfr_tmultimaalteracao IS 'Timestamp';
COMMENT ON COLUMN atualizacaocadastral.cliente_fone_retorno.fnet_id IS 'Id do Tipo de Telefone';
COMMENT ON COLUMN atualizacaocadastral.cliente_fone_retorno.clie_id IS 'Id do Cliente';
 
 
-- Index: atualizacaocadastral.xfk1_cliente_fone_retorno
 
-- DROP INDEX atualizacaocadastral.xfk1_cliente_fone_retorno;
 
CREATE INDEX xfk1_cliente_fone_retorno
  ON atualizacaocadastral.cliente_fone_retorno
  USING btree
  (clie_id)
TABLESPACE indices;
 
-- Index: atualizacaocadastral.xfk2_cliente_fone_retorno
 
-- DROP INDEX atualizacaocadastral.xfk2_cliente_fone_retorno;
 
CREATE INDEX xfk2_cliente_fone_retorno
  ON atualizacaocadastral.cliente_fone_retorno
  USING btree
  (fnet_id)
TABLESPACE indices;