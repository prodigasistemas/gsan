-- // insert funcionalidade receitas a faturar
-- Migration SQL that makes the change goes here.
INSERT INTO seguranca.funcionalidade(
            fncd_id, modu_id,
            fncd_dsfuncionalidade, fncd_tmultimaalteracao,
            fncd_dscaminhomenu, fncd_dscaminhourl,
            fncd_icpontoentrada, fncd_dsabreviado, 
            fncd_nnordemmenu, fncd_icnovajanela,
            fncd_icolap, fncg_id)
    VALUES (16025, 9,
	    'Gerar Resumo de Receitas a Faturar', now(), 
            null, null,
            2, 'GeReReAF',
            1, 2,
            2, 9);


INSERT INTO batch.unidade_processamento(
            unpr_id, unpr_dsunidadeprocessamento, unpr_tmultimaalteracao, 
            unpr_icuso, unpr_dsabreviada)
    VALUES (21, 'GRUPO FATURAMENTO', now(), 1, 'GF');


INSERT INTO batch.processo_funcionalidade(
            prfn_id, proc_id,
            prfn_tmultimaalteracao, unpr_id,
            prfn_nnsequencialexecucao, fncd_id,
            prfn_icuso, prfn_dsorientacao)
    VALUES (nextval('batch.seq_processo_funcionalidade'), 50,
	    now(), 21, 10, 16025, 1, null);

-- //@UNDO
-- SQL to undo the change goes here.
DELETE FROM batch.unidade_iniciada
WHERE fuin_id IN (SELECT fuin_id
				  FROM batch.funcionalidade_iniciada
				  WHERE prfn_id IN (SELECT prfn_id FROM batch.processo_funcionalidade WHERE fncd_id = 16025));

DELETE FROM batch.funcionalidade_iniciada
WHERE prfn_id IN (SELECT prfn_id FROM batch.processo_funcionalidade WHERE fncd_id = 16025);

DELETE FROM batch.processo_funcionalidade WHERE fncd_id = 16025;

DELETE FROM seguranca.funcionalidade WHERE fncd_id = 16025;

DELETE FROM batch.unidade_processamento WHERE unpr_id = 21;

