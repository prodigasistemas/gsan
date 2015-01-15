-- // create table receitas a faturar resumo
-- Migration SQL that makes the change goes here.
CREATE TABLE faturamento.receitas_a_faturar_resumo
(
  rafr_id integer NOT NULL,
  rafr_amreferencia integer NOT NULL,
  ftgr_id integer NOT NULL,
  rafr_dtleituraanterior date NOT NULL,
  rafr_dtleituraatual date NOT NULL,
  rafr_diferencadias integer NOT NULL,
  rafr_diasnaofaturados integer NOT NULL,
  rafr_vlagua numeric(13,2),
  rafr_vlaguadiario numeric(13,2),
  rafr_vlaguaafaturar numeric(13,2),
  rafr_vlesgoto numeric(13,2),
  rafr_vlesgotodiario numeric(13,2),
  rafr_vlesgotoafaturar numeric(13,2),
  rafr_tmultimaalteracao timestamp without time zone NOT NULL DEFAULT now(),
  CONSTRAINT receitas_a_faturar_resumo_pkey PRIMARY KEY (rafr_id)  
)
WITH (
  OIDS=FALSE
);
ALTER TABLE faturamento.receitas_a_faturar_resumo
  OWNER TO gsan_admin;
GRANT ALL ON TABLE faturamento.receitas_a_faturar_resumo TO gsan_admin;
GRANT SELECT, UPDATE, INSERT, DELETE ON TABLE faturamento.receitas_a_faturar_resumo TO pg_aplic;
GRANT SELECT ON TABLE faturamento.receitas_a_faturar_resumo TO pg_users;


CREATE SEQUENCE faturamento.seq_receitas_a_faturar_resumo
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE faturamento.seq_receitas_a_faturar_resumo
  OWNER TO gsan_admin;
GRANT SELECT, UPDATE ON TABLE faturamento.seq_receitas_a_faturar_resumo TO gsan_admin;
GRANT SELECT, UPDATE ON TABLE faturamento.seq_receitas_a_faturar_resumo TO pg_aplic;
GRANT SELECT ON TABLE faturamento.seq_receitas_a_faturar_resumo TO pg_users;



-- //@UNDO
-- SQL to undo the change goes here.
DROP TABLE faturamento.receitas_a_faturar_resumo;
DROP SEQUENCE faturamento.seq_receitas_a_faturar_resumo;

