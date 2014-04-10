-- Sequence: atualizacaocadastral.seq_cliente_imovel_retorno
 
 DROP SEQUENCE atualizacaocadastral.seq_cliente_imovel_retorno;
 
CREATE SEQUENCE atualizacaocadastral.seq_cliente_imovel_retorno
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE atualizacaocadastral.seq_cliente_imovel_retorno
  OWNER TO gsan_admin;
GRANT ALL ON TABLE atualizacaocadastral.seq_cliente_imovel_retorno TO gsan_admin;
GRANT SELECT, UPDATE ON TABLE atualizacaocadastral.seq_cliente_imovel_retorno TO pg_aplic;
GRANT SELECT ON TABLE atualizacaocadastral.seq_cliente_imovel_retorno TO pg_users;
 
 
-- Table: atualizacaocadastral.cliente_imovel_retorno
 
 DROP TABLE atualizacaocadastral.cliente_imovel_retorno;
 
CREATE TABLE atualizacaocadastral.cliente_imovel_retorno
(
  cire_id integer NOT NULL, -- Id do cliente_imovel (sequencial)
  clir_tmultimaalteracao timestamp without time zone NOT NULL DEFAULT now(), -- Timestamp
  imov_id integer, -- Id do Imovel
  clie_id integer, -- Id do Cliente
  crtp_id integer, -- Id do Tipo de Relacao do Cliente com o Imovel
  imre_id integer,
  clir_id integer,
  CONSTRAINT cliente_imovel_retorno_pkey PRIMARY KEY (cire_id),
  CONSTRAINT fk3_cliente_imovel_retorno FOREIGN KEY (crtp_id)
      REFERENCES cadastro.cliente_relacao_tipo (crtp_id) MATCH SIMPLE
      ON UPDATE RESTRICT ON DELETE RESTRICT
)
WITH (
  OIDS=FALSE
);
ALTER TABLE atualizacaocadastral.cliente_imovel_retorno
  OWNER TO gsan_admin;
GRANT ALL ON TABLE atualizacaocadastral.cliente_imovel_retorno TO gsan_admin;
GRANT SELECT, UPDATE, INSERT, DELETE ON TABLE atualizacaocadastral.cliente_imovel_retorno TO pg_aplic;
GRANT SELECT ON TABLE atualizacaocadastral.cliente_imovel_retorno TO pg_users;
COMMENT ON TABLE atualizacaocadastral.cliente_imovel_retorno
  IS 'Relacao que um cliente possa ter com um ou mais imovel, na condicao de usuario, responsavel ou proprietario.';
COMMENT ON COLUMN atualizacaocadastral.cliente_imovel_retorno.cire_id IS 'Id do cliente_imovel (sequencial)';
COMMENT ON COLUMN atualizacaocadastral.cliente_imovel_retorno.clir_tmultimaalteracao IS 'Timestamp';
COMMENT ON COLUMN atualizacaocadastral.cliente_imovel_retorno.imov_id IS 'Id do Imovel';
COMMENT ON COLUMN atualizacaocadastral.cliente_imovel_retorno.clie_id IS 'Id do Cliente';
COMMENT ON COLUMN atualizacaocadastral.cliente_imovel_retorno.crtp_id IS 'Id do Tipo de Relacao do Cliente com o Imovel';
 
 
-- Index: atualizacaocadastral.xak1_cliente_imovel_retorno
 
-- DROP INDEX atualizacaocadastral.xak1_cliente_imovel_retorno;
 
CREATE UNIQUE INDEX xak1_cliente_imovel_retorno
  ON atualizacaocadastral.cliente_imovel_retorno
  USING btree
  (cire_id, imov_id, crtp_id)
TABLESPACE indices;
 
-- Index: atualizacaocadastral.xfk1_cliente_imovel
 
-- DROP INDEX atualizacaocadastral.xfk1_cliente_imovel;
 
CREATE INDEX xfk1_cliente_imovel
  ON atualizacaocadastral.cliente_imovel_retorno
  USING btree
  (clie_id)
TABLESPACE indices;
 
-- Index: atualizacaocadastral.xfk2_cliente_imovel_retorno
 
-- DROP INDEX atualizacaocadastral.xfk2_cliente_imovel_retorno;
 
CREATE INDEX xfk2_cliente_imovel_retorno
  ON atualizacaocadastral.cliente_imovel_retorno
  USING btree
  (imov_id)
TABLESPACE indices;
 
-- Index: atualizacaocadastral.xfk3_cliente_imovel_retorno
 
-- DROP INDEX atualizacaocadastral.xfk3_cliente_imovel_retorno;
 
CREATE INDEX xfk3_cliente_imovel_retorno
  ON atualizacaocadastral.cliente_imovel_retorno
  USING btree
  (crtp_id)
TABLESPACE indices;