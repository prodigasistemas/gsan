-- Sequence: atualizacaocadastral.seq_imagem_retorno

-- DROP SEQUENCE atualizacaocadastral.seq_imagem_retorno;

CREATE SEQUENCE atualizacaocadastral.seq_imagem_retorno
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE atualizacaocadastral.seq_imagem_retorno
  OWNER TO gsan_admin;
GRANT ALL ON TABLE atualizacaocadastral.seq_imagem_retorno TO gsan_admin;
GRANT SELECT, UPDATE, INSERT, DELETE ON TABLE atualizacaocadastral.seq_imagem_retorno TO pg_aplic;
GRANT SELECT ON TABLE atualizacaocadastral.seq_imagem_retorno TO pg_users;



-- Table: atualizacaocadastral.imagem_retorno

-- DROP TABLE atualizacaocadastral.imagem_retorno;

CREATE TABLE atualizacaocadastral.imagem_retorno
(
  imgr_id integer NOT NULL,
  imre_id integer NOT NULL,
  imov_id integer,
  imgr_nomeimagem character varying(50) NOT NULL,
  imgr_pathimagem character varying(250) NOT NULL,
  imgr_tmultimaalteracao timestamp without time zone NOT NULL DEFAULT now(),
  CONSTRAINT imagem_retorno_pkey PRIMARY KEY (imgr_id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE atualizacaocadastral.imagem_retorno
  OWNER TO gsan_admin;
GRANT ALL ON TABLE atualizacaocadastral.imagem_retorno TO gsan_admin;
GRANT SELECT, UPDATE, INSERT, DELETE ON TABLE atualizacaocadastral.imagem_retorno TO pg_aplic;
GRANT SELECT ON TABLE atualizacaocadastral.imagem_retorno TO pg_users;

