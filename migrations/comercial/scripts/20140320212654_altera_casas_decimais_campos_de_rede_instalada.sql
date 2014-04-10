-- // altera casas decimais campos de rede instalada
-- Migration SQL that makes the change goes here.
update operacao.rede_instalada set rdin_cadastrada = rdin_cadastrada + 0.00;

update operacao.rede_instalada set rdin_existente = rdin_existente + 0.00;

-- //@UNDO
-- SQL to undo the change goes here.

-- Sem necessidade de UNDO, pois apenas esta adicionando zero ao campo
