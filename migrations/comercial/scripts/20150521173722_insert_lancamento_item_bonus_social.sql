-- // insert lancamento item bonus social
-- Migration SQL that makes the change goes here.

INSERT INTO financeiro.lancamento_item(lcit_id, lcit_dsitemlancamento, lcit_dsabreviado, lcit_icitemcontabil, lcit_tmultimaalteracao)
VALUES (87, 'BONUS SOCIAL', 'BS', 2, now());


-- //@UNDO
-- SQL to undo the change goes here.

delete from arrecadacao.arrec_contb_parametros where lcit_id = 87;
delete from arrecadacao.resumo_arrecadacao     where lcit_id = 87;
delete from faturamento.fatur_contb_parametros where lcit_id = 87;
delete from financeiro.resumo_faturamento      where lcit_id = 87;
DELETE FROM financeiro.lancamento_item         WHERE lcit_id = 87;