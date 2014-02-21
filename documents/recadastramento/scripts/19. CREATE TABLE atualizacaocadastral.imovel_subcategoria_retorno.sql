-- Sequence: atualizacaocadastral.sequence_imovel_subcategoria_retorno
 
-- DROP SEQUENCE atualizacaocadastral.sequence_imovel_subcategoria_retorno;
 
CREATE SEQUENCE atualizacaocadastral.sequence_imovel_subcategoria_retorno
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE atualizacaocadastral.sequence_imovel_subcategoria_retorno
  OWNER TO gsan_admin;
GRANT ALL ON TABLE atualizacaocadastral.sequence_imovel_subcategoria_retorno TO gsan_admin;
GRANT SELECT, UPDATE ON TABLE atualizacaocadastral.sequence_imovel_subcategoria_retorno TO pg_aplic;
GRANT SELECT ON TABLE atualizacaocadastral.sequence_imovel_subcategoria_retorno TO pg_users;

-- Table: atualizacaocadastral.imovel_subcategoria_retorno
 
-- DROP TABLE atualizacaocadastral.imovel_subcategoria_retorno;
 
CREATE TABLE atualizacaocadastral.imovel_subcategoria_retorno
(
  isre_id integer NOT NULL, -- Id do imovel subcategoria retorno (sequencial) 
  imov_id integer,
  scat_id integer NOT NULL,
  isre_qteconomia smallint,
  imre_id integer,
  isre_tmultimaalteracao timestamp without time zone,
  CONSTRAINT imovel_subcategoria_retorno_pkey PRIMARY KEY (isre_id),
  CONSTRAINT fk2_imovel_subcategoria_retorno FOREIGN KEY (scat_id)
      REFERENCES cadastro.subcategoria (scat_id) MATCH SIMPLE
      ON UPDATE RESTRICT ON DELETE RESTRICT
)
WITH (
  OIDS=FALSE
);
ALTER TABLE atualizacaocadastral.imovel_subcategoria_retorno
  OWNER TO gsan_admin;
GRANT ALL ON TABLE atualizacaocadastral.imovel_subcategoria_retorno TO gsan_admin;
GRANT SELECT, UPDATE, INSERT, DELETE ON TABLE atualizacaocadastral.imovel_subcategoria_retorno TO pg_aplic;
GRANT SELECT ON TABLE atualizacaocadastral.imovel_subcategoria_retorno TO pg_users;
COMMENT ON COLUMN atualizacaocadastral.imovel_subcategoria_retorno.isre_id IS 'Id do imovel subcategoria retorno (sequencial)';

 