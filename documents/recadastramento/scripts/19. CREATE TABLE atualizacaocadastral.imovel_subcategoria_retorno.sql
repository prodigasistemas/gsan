-- Table: atualizacaocadastral.imovel_subcategoria_retorno
 
-- DROP TABLE atualizacaocadastral.imovel_subcategoria_retorno;
 
CREATE TABLE atualizacaocadastral.imovel_subcategoria_retorno
(
  imov_id integer NOT NULL,
  scat_id integer NOT NULL,
  isre_qteconomia smallint,
  imre_id integer,
  isre_tmultimaalteracao timestamp without time zone,
  CONSTRAINT imovel_subcategoria_retorno_pkey PRIMARY KEY (imov_id, scat_id),
  CONSTRAINT fk1_imovel_subcategoria_retorno FOREIGN KEY (imov_id)
      REFERENCES cadastro.imovel (imov_id) MATCH SIMPLE
      ON UPDATE RESTRICT ON DELETE RESTRICT,
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
 