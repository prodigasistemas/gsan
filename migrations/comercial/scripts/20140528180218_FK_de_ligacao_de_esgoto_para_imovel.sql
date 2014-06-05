-- // FK de ligacao agua para imovel
-- Migration SQL that makes the change goes here.
alter table atendimentopublico.ligacao_esgoto
  add column imov_id integer;

alter table atendimentopublico.ligacao_esgoto
  add constraint fk_esgoto_imovel foreign key (imov_id)
  references cadastro.imovel (imov_id) match SIMPLE
  on update restrict on delete restrict;

update atendimentopublico.ligacao_esgoto
  set imov_id = lesg_id;

alter table atendimentopublico.ligacao_esgoto
  alter column imov_id set not null;
  
COMMENT ON COLUMN atendimentopublico.ligacao_esgoto.imov_id IS 'Imovel relacionado a ligacao de esgoto';  

-- //@UNDO
-- SQL to undo the change goes here.
alter table atendimentopublico.ligacao_esgoto
  drop column imov_id;
