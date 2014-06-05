-- // FK de ligacao agua para imovel
-- Migration SQL that makes the change goes here.
alter table atendimentopublico.ligacao_agua
  add column imov_id integer;

alter table atendimentopublico.ligacao_agua
  add constraint fk45_imovel foreign key (imov_id)
  references cadastro.imovel (imov_id) match SIMPLE
  on update restrict on delete restrict;

update atendimentopublico.ligacao_agua
  set imov_id = lagu_id;

alter table atendimentopublico.ligacao_agua
  alter column imov_id set not null;
  
COMMENT ON COLUMN atendimentopublico.ligacao_agua.imov_id IS 'Imovel relacionado a ligacao de agua';  

-- //@UNDO
-- SQL to undo the change goes here.
alter table atendimentopublico.ligacao_agua
  drop column imov_id;
