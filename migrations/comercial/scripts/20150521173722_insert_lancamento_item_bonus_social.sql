-- // insert lancamento item bonus social
-- Migration SQL that makes the change goes here.

INSERT INTO financeiro.lancamento_item(lcit_id, lcit_dsitemlancamento, lcit_dsabreviado, lcit_icitemcontabil, lcit_tmultimaalteracao)
VALUES (87, 'BONUS SOCIAL', 'BS', 2, now());


-- //@UNDO
-- SQL to undo the change goes here.

DELETE FROM financeiro.lancamento_item WHERE lcit_id = 87;
