- Sequence: atualizacaocadastral.seq_cliente_imovel_retorno
 
-- DROP SEQUENCE atualizacaocadastral.seq_cliente_imovel_retorno;
 
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
 
 
- Table: atualizacaocadastral.cliente_imovel_retorno
 
-- DROP TABLE atualizacaocadastral.cliente_imovel_retorno;
 
CREATE TABLE atualizacaocadastral.cliente_imovel_retorno
(
  clir_id integer NOT NULL, -- Id do cliente_imovel (sequencial)
  clir_tmultimaalteracao timestamp without time zone NOT NULL DEFAULT now(), -- Timestamp
  clir_icnomeconta smallint NOT NULL, -- Indica se este e o Cliente cujo Nome deve sair na Conta do Imovel (1_Sim; 2_Nao). Um e somente um Cliente do Imovel deve ter o Indicador igual a 1_Sim.
  imov_id integer NOT NULL, -- Id do Imovel
  clie_id integer NOT NULL, -- Id do Cliente
  crtp_id integer, -- Id do Tipo de Relacao do Cliente com o Imovel
  CONSTRAINT cliente_imovel_retorno_pkey PRIMARY KEY (clir_id),
  CONSTRAINT fk1_cliente_imovel_retorno FOREIGN KEY (clie_id)
      REFERENCES cadastro.cliente (clie_id) MATCH SIMPLE
      ON UPDATE RESTRICT ON DELETE RESTRICT,
  CONSTRAINT fk2_cliente_imovel_retorno FOREIGN KEY (imov_id)
      REFERENCES cadastro.imovel (imov_id) MATCH SIMPLE
      ON UPDATE RESTRICT ON DELETE RESTRICT,
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
COMMENT ON COLUMN atualizacaocadastral.cliente_imovel_retorno.clir_id IS 'Id do cliente_imovel (sequencial)';
COMMENT ON COLUMN atualizacaocadastral.cliente_imovel_retorno.clir_tmultimaalteracao IS 'Timestamp';
COMMENT ON COLUMN atualizacaocadastral.cliente_imovel_retorno.clir_icnomeconta IS 'Indica se este e o Cliente cujo Nome deve sair na Conta do Imovel (1_Sim; 2_Nao). Um e somente um Cliente do Imovel deve ter o Indicador igual a 1_Sim.';
COMMENT ON COLUMN atualizacaocadastral.cliente_imovel_retorno.imov_id IS 'Id do Imovel';
COMMENT ON COLUMN atualizacaocadastral.cliente_imovel_retorno.clie_id IS 'Id do Cliente';
COMMENT ON COLUMN atualizacaocadastral.cliente_imovel_retorno.crtp_id IS 'Id do Tipo de Relacao do Cliente com o Imovel';
 
 
-- Index: atualizacaocadastral.xak1_cliente_imovel_retorno
 
-- DROP INDEX atualizacaocadastral.xak1_cliente_imovel_retorno;
 
CREATE UNIQUE INDEX xak1_cliente_imovel_retorno
  ON atualizacaocadastral.cliente_imovel_retorno
  USING btree
  (clie_id, imov_id, crtp_id)
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