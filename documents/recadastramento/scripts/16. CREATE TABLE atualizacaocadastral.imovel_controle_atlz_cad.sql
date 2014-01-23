-- Table: atualizacaocadastral.imovel_controle_atlz_cad
 
-- DROP TABLE cadastro.imovel_controle_atlz_cad;
 
CREATE TABLE atualizacaocadastral.imovel_controle_atlz_cad
(
  icac_id integer NOT NULL,
  imov_id integer NOT NULL,
  icac_tmgeracao timestamp without time zone,
  icac_tmretorno timestamp without time zone,
  icac_tmaprovacao timestamp without time zone,
  siac_id integer NOT NULL,
  CONSTRAINT imovel_controle_atlz_cad_pk PRIMARY KEY (icac_id),
  CONSTRAINT fk1_imovel_controle_atlz_cad FOREIGN KEY (imov_id)
      REFERENCES cadastro.imovel (imov_id) MATCH SIMPLE
      ON UPDATE RESTRICT ON DELETE RESTRICT,
  CONSTRAINT fk2_imovel_controle_atlz_cad FOREIGN KEY (siac_id)
      REFERENCES cadastro.situacao_atlz_cadastral (siac_id) MATCH SIMPLE
      ON UPDATE RESTRICT ON DELETE RESTRICT
)
WITH (
  OIDS=FALSE
);
ALTER TABLE atualizacaocadastral.imovel_controle_atlz_cad
  OWNER TO gsan_admin;
GRANT ALL ON TABLE atualizacaocadastral.imovel_controle_atlz_cad TO gsan_admin;
GRANT SELECT, UPDATE, INSERT, DELETE ON TABLE atualizacaocadastral.imovel_controle_atlz_cad TO pg_aplic;
GRANT SELECT ON TABLE atualizacaocadastral.imovel_controle_atlz_cad TO pg_users;