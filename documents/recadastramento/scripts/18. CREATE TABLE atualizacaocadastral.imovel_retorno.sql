-- Schema: atualizacaocadastral
  
GRANT ALL ON SCHEMA atualizacaocadastral TO gsan_admin;
GRANT USAGE ON SCHEMA atualizacaocadastral TO pg_aplic;
GRANT USAGE ON SCHEMA atualizacaocadastral TO pg_users;
 
-- Sequence: atualizacaocadastral.sequence_imovel_retorno
 
-- DROP SEQUENCE atualizacaocadastral.sequence_imovel_retorno;
 
CREATE SEQUENCE atualizacaocadastral.sequence_imovel_retorno
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE atualizacaocadastral.sequence_imovel_retorno
  OWNER TO gsan_admin;
GRANT ALL ON TABLE atualizacaocadastral.sequence_imovel_retorno TO gsan_admin;
GRANT SELECT, UPDATE ON TABLE atualizacaocadastral.sequence_imovel_retorno TO pg_aplic;
GRANT SELECT ON TABLE atualizacaocadastral.sequence_imovel_retorno TO pg_users;
 
-- Table: atualizacaocadastral.imovel_retorno
 
-- DROP TABLE atualizacaocadastral.imovel_retorno;
 
CREATE TABLE atualizacaocadastral.imovel_retorno
(
  imre_id integer NOT NULL,
  imac_nnimovel character(5) NOT NULL,
  imac_dscomplementoendereco character varying(25),
  imac_nnpontosutilizacao smallint,
  imac_nnmorador smallint,
  imac_nniptu character varying(31),
  imac_nncoordenadax character varying(20),
  imac_nncoordenaday character varying(20),
  imac_nnhidrometro character(10),
  imac_nnmedidorenergia character(10),
  imac_dsoutrasinformacoes character varying(200),
  imac_tipoentrevistado character varying(50),
  imac_tipooperacao integer,
  imac_tmultimaalteracao timestamp without time zone,
  last_id integer,
  ftab_id integer,
  hipr_id integer,
  imov_id integer

  CONSTRAINT imovel_retorno_pkey PRIMARY KEY (imre_id),
  CONSTRAINT fk1_imovel_retorno FOREIGN KEY (last_id)
      REFERENCES atendimentopublico.ligacao_agua_situacao (last_id) MATCH SIMPLE
      ON UPDATE RESTRICT ON DELETE RESTRICT,
  CONSTRAINT fk2_imovel_retorno FOREIGN KEY (ftab_id)
      REFERENCES cadastro.fonte_abastecimento (ftab_id) MATCH SIMPLE
      ON UPDATE RESTRICT ON DELETE RESTRICT,
  CONSTRAINT fk3_imovel_retorno FOREIGN KEY (hipr_id)
      REFERENCES micromedicao.hidrometro_protecao (hipr_id) MATCH SIMPLE
      ON UPDATE RESTRICT ON DELETE RESTRICT
)
WITH (
  OIDS=FALSE
);
ALTER TABLE atualizacaocadastral.imovel_retorno
  OWNER TO gsan_admin;
GRANT ALL ON TABLE atualizacaocadastral.imovel_retorno TO gsan_admin;
GRANT SELECT, UPDATE, INSERT, DELETE ON TABLE atualizacaocadastral.imovel_retorno TO pg_aplic;
GRANT SELECT ON TABLE atualizacaocadastral.imovel_retorno TO pg_users;
 
-- Index: atualizacaocadastral.fk1_imovel_retorno
 
-- DROP INDEX atualizacaocadastral.fk1_imovel_retorno;
 
CREATE INDEX fk1_imovel_retorno
  ON atualizacaocadastral.imovel_retorno
  USING btree
  (last_id)
TABLESPACE indices;

 
-- Index: atualizacaocadastral.fk2_imovel_retorno
 
-- DROP INDEX atualizacaocadastral.fk2_imovel_retorno;
 
CREATE INDEX fk2_imovel_retorno
  ON atualizacaocadastral.imovel_retorno
  USING btree
  (ftab_id)
TABLESPACE indices;


-- Index: atualizacaocadastral.fk3_imovel_retorno
 
-- DROP INDEX atualizacaocadastral.fk3_imovel_retorno;
 
CREATE INDEX fk3_imovel_retorno
  ON atualizacaocadastral.imovel_retorno
  USING btree
  (hipr_id)
TABLESPACE indices;



-- Index: atualizacaocadastral.fk4_imovel_retorno
 
-- DROP INDEX atualizacaocadastral.fk4_imovel_retorno;
 
CREATE INDEX fk4_imovel_retorno
  ON atualizacaocadastral.imovel_retorno
  USING btree
  (imov_id)
TABLESPACE indices;
