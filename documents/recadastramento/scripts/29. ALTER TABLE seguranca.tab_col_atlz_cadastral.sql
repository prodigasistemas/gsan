--Rollback
--alter table seguranca.tab_col_atlz_cadastral drop column usur_id;

alter table seguranca.tab_col_atlz_cadastral alter column tcac_dtvalidacao type timestamp;

alter table seguranca.tab_col_atlz_cadastral
  add column usur_id INTEGER
  CONSTRAINT fk3_tabela_coluna_atualizacao_cadastral REFERENCES seguranca.usuario (usur_id)
  MATCH SIMPLE ON UPDATE RESTRICT ON DELETE RESTRICT;

COMMENT ON COLUMN seguranca.tab_col_atlz_cadastral.usur_id IS 'Id do usuario analista';
