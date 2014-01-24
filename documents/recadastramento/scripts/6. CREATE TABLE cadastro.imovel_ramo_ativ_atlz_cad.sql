-- Table: cadastro.imovel_ramo_ativ_atlz_cad

CREATE TABLE cadastro.imovel_ramo_ativ_atlz_cad
(
 imra_id integer NOT NULL, -- Id
 imov_id integer NOT NULL, -- Id do Imovel
 ratv_id integer NOT NULL, -- Id do ramo de atividade
 imra_tmultimaalteracao timestamp without time zone NOT NULL DEFAULT now(), -- Timestamp da ultima alteracao
 CONSTRAINT imovel_ramo_ativ_atlz_cad_pkey PRIMARY KEY (imra_id),
 CONSTRAINT fk2_imovel_ramo_ativ_atlz_cad FOREIGN KEY (ratv_id)
     REFERENCES cadastro.ramo_atividade (ratv_id) MATCH SIMPLE
     ON UPDATE RESTRICT ON DELETE RESTRICT
)
WITH (
 OIDS=FALSE
);
ALTER TABLE cadastro.imovel_ramo_ativ_atlz_cad OWNER TO gsan_admin;
GRANT ALL ON TABLE cadastro.imovel_ramo_ativ_atlz_cad TO gsan_admin;
GRANT SELECT, UPDATE, INSERT, DELETE ON TABLE cadastro.imovel_ramo_ativ_atlz_cad TO pg_aplic;
GRANT SELECT ON TABLE cadastro.imovel_ramo_ativ_atlz_cad TO pg_users;
COMMENT ON COLUMN cadastro.imovel_ramo_ativ_atlz_cad.imov_id IS 'Id do Imovel';
COMMENT ON COLUMN cadastro.imovel_ramo_ativ_atlz_cad.ratv_id IS 'Id do ramo de atividade';
COMMENT ON COLUMN cadastro.imovel_ramo_ativ_atlz_cad.imra_tmultimaalteracao IS 'Timestamp da ultima alteracao';

-- SEQUENCE
CREATE SEQUENCE cadastro.sequence_imovel_ramo_ativ_atlz_cad
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 1
 CACHE 1;
ALTER TABLE cadastro.sequence_imovel_ramo_ativ_atlz_cad OWNER TO gsan_admin;
GRANT SELECT, UPDATE ON TABLE cadastro.sequence_imovel_ramo_ativ_atlz_cad TO gsan_admin;
GRANT SELECT, UPDATE ON TABLE cadastro.sequence_imovel_ramo_ativ_atlz_cad TO pg_aplic;
GRANT SELECT ON TABLE cadastro.sequence_imovel_ramo_ativ_atlz_cad TO pg_users;

-- INSERT seguranca.tabela
INSERT INTO seguranca.tabela(
           tabe_id, tabe_tmultimaalteracao, tabe_nmtabela, tabe_dstabela)
   VALUES (665, now(), 'cadastro.imovel_ramo_ativ_atlz_cad', 'Imovel Ramo-atividade atualizacao cadastral');
