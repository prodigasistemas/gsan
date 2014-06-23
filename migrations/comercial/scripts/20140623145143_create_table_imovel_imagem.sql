-- // create table imovel_imagem
-- Migration SQL that makes the change goes here.
CREATE SEQUENCE cadastro.seq_imovel_imagem
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE cadastro.seq_imovel_imagem
  OWNER TO gsan_admin;
GRANT ALL ON TABLE cadastro.seq_imovel_imagem TO gsan_admin;
GRANT SELECT, UPDATE, INSERT, DELETE ON TABLE cadastro.seq_imovel_imagem TO pg_aplic;
GRANT SELECT ON TABLE cadastro.seq_imovel_imagem TO pg_users;

CREATE TABLE cadastro.imovel_imagem
(
  imim_id integer NOT NULL,
  imov_id integer NOT NULL,
  imim_nmimagem character varying(50) NOT NULL,
  imim_caminhoimagem character varying(250) NOT NULL,
  imim_tmultimaalteracao timestamp without time zone NOT NULL DEFAULT now(),
  CONSTRAINT imovel_imagem_pkey PRIMARY KEY (imim_id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE cadastro.imovel_imagem
  OWNER TO gsan_admin;
GRANT ALL ON TABLE cadastro.imovel_imagem TO gsan_admin;
GRANT SELECT, UPDATE, INSERT, DELETE ON TABLE cadastro.imovel_imagem TO pg_aplic;
GRANT SELECT ON TABLE cadastro.imovel_imagem TO pg_users;


-- //@UNDO
-- SQL to undo the change goes here.
DROP SEQUENCE cadastro.seq_imovel_imagem;
DROP TABLE cadastro.imovel_imagem;


