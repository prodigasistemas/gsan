-- Table: cadastro.imagem_atlz_cadastral

-- DROP TABLE cadastro.imagem_atlz_cadastral;

CREATE TABLE cadastro.imagem_atlz_cadastral
(
  igac_id integer NOT NULL,
  imov_id integer NOT NULL, -- Id do imovel
  igac_imagem bytea NOT NULL, -- Imagem
  igac_tmultimaalteracao timestamp without time zone NOT NULL DEFAULT now(), -- Ultima alteracao realizada
  CONSTRAINT imagem_atlz_cadastral_pkey PRIMARY KEY (igac_id)
);

ALTER TABLE cadastro.imagem_atlz_cadastral OWNER TO gsan_admin;
GRANT SELECT, UPDATE ON TABLE cadastro.imagem_atlz_cadastral TO gsan_admin;
GRANT SELECT, UPDATE ON TABLE cadastro.imagem_atlz_cadastral TO pg_aplic;
GRANT SELECT ON TABLE cadastro.imagem_atlz_cadastral TO pg_users;

-- SEQUENCE
CREATE SEQUENCE cadastro.sequence_imagem_atlz_cadastral
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 1
 CACHE 1;
ALTER TABLE cadastro.sequence_imagem_atlz_cadastral OWNER TO gsan_admin;
GRANT SELECT, UPDATE ON TABLE cadastro.sequence_imagem_atlz_cadastral TO gsan_admin;
GRANT SELECT, UPDATE ON TABLE cadastro.sequence_imagem_atlz_cadastral TO pg_aplic;
GRANT SELECT ON TABLE cadastro.sequence_imagem_atlz_cadastral TO pg_users;
