-- Sequence: atualizacaocadastral.seq_cliente_endereco_retorno

-- DROP SEQUENCE atualizacaocadastral.seq_cliente_endereco_retorno;

CREATE SEQUENCE atualizacaocadastral.seq_cliente_endereco_retorno
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE atualizacaocadastral.seq_cliente_endereco_retorno
  OWNER TO gsan_admin;
GRANT ALL ON TABLE atualizacaocadastral.seq_cliente_endereco_retorno TO gsan_admin;
GRANT SELECT, UPDATE ON TABLE atualizacaocadastral.seq_cliente_endereco_retorno TO pg_aplic;
GRANT SELECT ON TABLE atualizacaocadastral.seq_cliente_endereco_retorno TO pg_users;


 
-- Table: atualizacaocadastral.cliente_endereco_retorno
-- DROP TABLE atualizacaocadastral.cliente_endereco_retorno;
 
CREATE TABLE atualizacaocadastral.cliente_endereco_retorno
(
  cler_id integer NOT NULL, -- Id do cliente_endereco (Sequencial)
  cler_nnimovel character(5), -- Numero do Imovel
  cler_dscomplementoendereco character varying(25), -- Complemento do Endereco
  cler_tmultimaalteracao timestamp without time zone NOT NULL DEFAULT now(), -- timestamp da inclusao/ultima alteracao
  cler_nmmunicipio character varying(15),
  cler_nmbairro character varying(20),
  cler_dslogradouro character varying(40),
  cler_cdcep character(8),
  edtp_id integer, -- Id do Tipo de Endereco
  clie_id integer, -- Id do Cliente
  clir_id integer,
  CONSTRAINT cliente_endereco_retorno_pkey PRIMARY KEY (cler_id),
  CONSTRAINT fk2_cliente_endereco_retorno FOREIGN KEY (edtp_id)
      REFERENCES cadastro.endereco_tipo (edtp_id) MATCH SIMPLE
      ON UPDATE RESTRICT ON DELETE RESTRICT
  
)
WITH (
  OIDS=FALSE
);
ALTER TABLE atualizacaocadastral.cliente_endereco_retorno
  OWNER TO gsan_admin;
GRANT ALL ON TABLE atualizacaocadastral.cliente_endereco_retorno TO gsan_admin;
GRANT SELECT, UPDATE, INSERT, DELETE ON TABLE atualizacaocadastral.cliente_endereco_retorno TO pg_aplic;
GRANT SELECT ON TABLE atualizacaocadastral.cliente_endereco_retorno TO pg_users;
COMMENT ON TABLE atualizacaocadastral.cliente_endereco_retorno
  IS 'Relacao de todos os clientes com seus respectivos enderecos e complementos';
COMMENT ON COLUMN atualizacaocadastral.cliente_endereco_retorno.cler_id IS 'Id do cliente_endereco (Sequencial)';
COMMENT ON COLUMN atualizacaocadastral.cliente_endereco_retorno.cler_nnimovel IS 'Numero do Imovel';
COMMENT ON COLUMN atualizacaocadastral.cliente_endereco_retorno.cler_dscomplementoendereco IS 'Complemento do Endereco';
COMMENT ON COLUMN atualizacaocadastral.cliente_endereco_retorno.cler_tmultimaalteracao IS 'timestamp da inclusao/ultima alteracao';
COMMENT ON COLUMN atualizacaocadastral.cliente_endereco_retorno.edtp_id IS 'Id do Tipo de Endereco';
COMMENT ON COLUMN atualizacaocadastral.cliente_endereco_retorno.clie_id IS 'Id do Cliente';
 
 
-- Index: atualizacaocadastral.xfk1_cliente_endereco_retorno
 
-- DROP INDEX atualizacaocadastral.xfk1_cliente_endereco_retorno;
 
CREATE INDEX xfk1_cliente_endereco_retorno
  ON atualizacaocadastral.cliente_endereco_retorno
  USING btree
  (clie_id)
TABLESPACE indices;
 
-- Index: atualizacaocadastral.xfk2_cliente_endereco_retorno
 
-- DROP INDEX atualizacaocadastral.xfk2_cliente_endereco_retorno;
 
CREATE INDEX xfk2_cliente_endereco_retorno
  ON atualizacaocadastral.cliente_endereco_retorno
  USING btree
  (edtp_id)
TABLESPACE indices;
 
 
