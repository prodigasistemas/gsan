-- // altera colunas de indicador permitir nulo
-- Migration SQL that makes the change goes here.
alter table operacao.indicador_mensal
  drop constraint indicador_mensal_pkey;
alter table operacao.indicador_mensal alter column greg_id drop not null;
alter table operacao.indicador_mensal alter column uneg_id drop not null;
alter table operacao.indicador_mensal alter column muni_id drop not null;
alter table operacao.indicador_mensal alter column loca_id drop not null;
alter table operacao.indicador_mensal alter column unop_tipo drop not null;
alter table operacao.indicador_mensal alter column unop_id drop not null;

ALTER TABLE operacao.indicador_mensal ADD COLUMN indm_id SERIAL;
UPDATE operacao.indicador_mensal 
  SET indm_id = nextval(pg_get_serial_sequence('operacao.indicador_mensal','indm_id'));
ALTER TABLE operacao.indicador_mensal 
  ADD CONSTRAINT indicador_mensal_pkey
    PRIMARY KEY (indm_id);

ALTER TABLE operacao.indicador_mensal
  OWNER TO postgres;
-- //@UNDO
-- SQL to undo the change goes here.

alter table operacao.indicador_mensal
  DROP CONSTRAINT indicador_mensal_pkey; 

alter table operacao.indicador_mensal
  DROP COLUMN indm_id; 

alter table operacao.indicador_mensal alter column greg_id set not null;
alter table operacao.indicador_mensal alter column uneg_id set not null;
alter table operacao.indicador_mensal alter column muni_id set not null;
alter table operacao.indicador_mensal alter column loca_id set not null;
alter table operacao.indicador_mensal alter column unop_tipo set not null;
alter table operacao.indicador_mensal alter column unop_id set not null;

alter table operacao.indicador_mensal
  ADD CONSTRAINT indicador_mensal_pkey 
    PRIMARY KEY (indm_data, indc_id, greg_id, uneg_id, muni_id, loca_id, unop_tipo, unop_id);

