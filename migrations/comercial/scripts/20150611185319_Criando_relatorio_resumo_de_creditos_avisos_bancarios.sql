-- // Criando relatorio resumo de creditos avisos bancarios
-- Migration SQL that makes the change goes here.

INSERT INTO seguranca.funcionalidade (fncd_id, modu_id, fncd_dsfuncionalidade, fncd_tmultimaalteracao, fncd_dscaminhomenu, fncd_dscaminhourl, fncd_icpontoentrada, fncd_dsabreviado,fncd_nnordemmenu,fncd_icnovajanela,fncd_icolap,fncg_id) 
VALUES (nextval('seguranca.seq_funcionalidade'),9,'Gerar Resumo Creditos Avisos Bancarios',now(),'/Gsan/Relatorios/Arrecadacao','gerarRelatorioResumoCreditosAvisosBancarios.do' ,1,'GeReCrAvBa',1,2,2,106);


INSERT INTO seguranca.operacao(oper_id, fncd_id, oper_dsoperacao, oper_dsabreviado, oper_dscaminhourl, oper_tmultimaalteracao, oper_idoperacaopesquisa,  tbco_id, optp_id, tbco_idargumento, oper_icregistratransacao)
VALUES ((nextval('seguranca.seq_operacao')), currval('seguranca.seq_operacao'), 'Gerar Resumo Creditos Avisos Bancarios', 'GeReCrAvBa', 'gerarRelatorioResumoCreditosAvisosBancariosAction.do',now(),null,null,14,null,2);

INSERT INTO seguranca.grupo_func_operacao VALUES (1, currval('seguranca.seq_operacao'), currval('seguranca.seq_funcionalidade'), now());

-- //@UNDO
-- SQL to undo the change goes here.

DELETE FROM seguranca.grupo_func_operacao 
  WHERE oper_id IN (SELECT oper_id FROM seguranca.operacao WHERE oper_dsoperacao LIKE 'Gerar Resumo Creditos Avisos Bancarios')
  AND fncd_id IN (SELECT fncd_id FROM seguranca.funcionalidade WHERE fncd_dsfuncionalidade LIKE 'Gerar Resumo Creditos Avisos Bancarios');

DELETE FROM seguranca.operacao WHERE oper_dsoperacao LIKE 'Gerar Resumo Creditos Avisos Bancarios';
DELETE FROM seguranca.funcionalidade WHERE fncd_dsfuncionalidade LIKE 'Gerar Resumo Creditos Avisos Bancarios';
