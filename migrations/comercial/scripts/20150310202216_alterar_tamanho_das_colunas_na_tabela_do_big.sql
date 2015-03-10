-- // alterar tamanho das colunas na tabela do big
-- Migration SQL that makes the change goes here.

ALTER TABLE arrecadacao.boletim_informacoes_gerenciais ALTER COLUMN boig_vlmediofaturamento TYPE numeric(10,2);
ALTER TABLE arrecadacao.boletim_informacoes_gerenciais ALTER COLUMN boig_icrecebimentomedio TYPE numeric(10,2);
ALTER TABLE arrecadacao.boletim_informacoes_gerenciais ALTER COLUMN boig_qtdfatcomprometidos TYPE numeric(10,2);
ALTER TABLE arrecadacao.boletim_informacoes_gerenciais ALTER COLUMN boig_prazomedioatendimentoos TYPE numeric(10,2);

-- //@UNDO
-- SQL to undo the change goes here.
ALTER TABLE arrecadacao.boletim_informacoes_gerenciais ALTER COLUMN boig_vlmediofaturamento TYPE numeric(5,2);
ALTER TABLE arrecadacao.boletim_informacoes_gerenciais ALTER COLUMN boig_icrecebimentomedio TYPE numeric(5,2);
ALTER TABLE arrecadacao.boletim_informacoes_gerenciais ALTER COLUMN boig_qtdfatcomprometidos TYPE numeric(5,2);
ALTER TABLE arrecadacao.boletim_informacoes_gerenciais ALTER COLUMN boig_prazomedioatendimentoos TYPE numeric(5,2);

