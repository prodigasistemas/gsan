-- Table: atualizacaocadastral.cliente_retorno

-- DROP TABLE atualizacaocadastral.cliente_retorno;

CREATE TABLE atualizacaocadastral.cliente_retorno
(
  clir_id integer NOT NULL, -- Id do cliente
  clir_nmcliente character varying(50), -- Nome do cliente
  clir_nncpf character varying(11), -- Numero do CPF do cliente
  clir_nnrg character varying(13), -- Numero do RG do cliente
  clir_nncnpj character varying(14), -- Numero do CNPJ do cliente
  clir_dsemail character varying(40), -- EMAIL do cliente
  clir_tmultimaalteracao timestamp without time zone NOT NULL DEFAULT now(), -- Timestamp
  psex_id integer, -- Id do sexo do cliente
  unfe_id integer, -- Id da unidade da federacao do orgao emissor
  cltp_id integer NOT NULL, -- Id do tipo de cliente
  clie_id integer,
  CONSTRAINT cliente_retorno_pkey PRIMARY KEY (clir_id),
  CONSTRAINT fk1_cliente FOREIGN KEY (cltp_id)
      REFERENCES cadastro.cliente_tipo (cltp_id) MATCH SIMPLE
      ON UPDATE RESTRICT ON DELETE RESTRICT,
  CONSTRAINT fk2_cliente FOREIGN KEY (unfe_id)
      REFERENCES cadastro.unidade_federacao (unfe_id) MATCH SIMPLE
      ON UPDATE RESTRICT ON DELETE RESTRICT,
  CONSTRAINT fk3_cliente FOREIGN KEY (psex_id)
      REFERENCES cadastro.pessoa_sexo (psex_id) MATCH SIMPLE
      ON UPDATE RESTRICT ON DELETE RESTRICT
)
WITH (
  OIDS=FALSE
);
ALTER TABLE atualizacaocadastral.cliente_retorno
  OWNER TO gsan_admin;
GRANT ALL ON TABLE atualizacaocadastral.cliente_retorno TO gsan_admin;
GRANT SELECT, UPDATE, INSERT, DELETE ON TABLE atualizacaocadastral.cliente_retorno TO pg_aplic;
GRANT SELECT ON TABLE atualizacaocadastral.cliente_retorno TO pg_users;
COMMENT ON TABLE atualizacaocadastral.cliente_retorno
  IS 'Qualquer pessoa de natureza fisica ou juridica, que tenha ou possa vir a ter alguma relacao com um ou mais imovel';
COMMENT ON COLUMN atualizacaocadastral.cliente_retorno.clir_id IS 'Id do cliente';
COMMENT ON COLUMN atualizacaocadastral.cliente_retorno.clir_nmcliente IS 'Nome do cliente';
COMMENT ON COLUMN atualizacaocadastral.cliente_retorno.clir_nncpf IS 'Numero do CPF do cliente';
COMMENT ON COLUMN atualizacaocadastral.cliente_retorno.clir_nnrg IS 'Numero do RG do cliente';
COMMENT ON COLUMN atualizacaocadastral.cliente_retorno.clir_nncnpj IS 'Numero do CNPJ do cliente';
COMMENT ON COLUMN atualizacaocadastral.cliente_retorno.clir_dsemail IS 'EMAIL do cliente';
COMMENT ON COLUMN atualizacaocadastral.cliente_retorno.clir_tmultimaalteracao IS 'Timestamp';
COMMENT ON COLUMN atualizacaocadastral.cliente_retorno.psex_id IS 'Id do sexo do cliente	';
COMMENT ON COLUMN atualizacaocadastral.cliente_retorno.unfe_id IS 'Id da unidade da federacao do orgao emissor';
COMMENT ON COLUMN atualizacaocadastral.cliente_retorno.cltp_id IS ' Id do tipo de cliente';


-- Index: atualizacaocadastral.xfk1_cliente

-- DROP INDEX atualizacaocadastral.xfk1_cliente;

CREATE INDEX xfk1_cliente
  ON atualizacaocadastral.cliente_retorno
  USING btree
  (cltp_id)
TABLESPACE indices;

-- Index: atualizacaocadastral.xfk2_cliente

-- DROP INDEX atualizacaocadastral.xfk2_cliente;

CREATE INDEX xfk2_cliente
  ON atualizacaocadastral.cliente_retorno
  USING btree
  (unfe_id)
TABLESPACE indices;

-- Index: atualizacaocadastral.xfk3_cliente

-- DROP INDEX atualizacaocadastral.xfk3_cliente;

CREATE INDEX xfk3_cliente
  ON atualizacaocadastral.cliente_retorno
  USING btree
  (psex_id)
TABLESPACE indices;

