-- // insert nova funcionalidade qtd impressao termica
-- Migration SQL that makes the change goes here.

CREATE SEQUENCE faturamento.seq_conta_impressao_termica_qtde
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE faturamento.seq_conta_impressao_termica_qtde
  OWNER TO gsan_admin;
GRANT SELECT, UPDATE ON TABLE faturamento.seq_conta_impressao_termica_qtde TO gsan_admin;
GRANT SELECT, UPDATE ON TABLE faturamento.seq_conta_impressao_termica_qtde TO pg_aplic;
GRANT SELECT ON TABLE faturamento.seq_conta_impressao_termica_qtde TO pg_users;




CREATE TABLE faturamento.conta_impressao_termica_qtde
(
  citq_id integer NOT NULL,
  citq_idGrupoFaturamento integer NOT NULL,
  citq_referencia integer NOT NULL,
  citq_idLocalidade integer NOT NULL,
  citq_descricaoLocalidade character varying(50),
  citq_qtdeContas integer NOT NULL,
  citq_datageracao timestamp without time zone NOT NULL DEFAULT now(),
  CONSTRAINT conta_impressao_termica_qtde_pkey PRIMARY KEY (citq_id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE faturamento.conta_impressao_termica_qtde OWNER TO gsan_admin;
GRANT ALL ON TABLE faturamento.conta_impressao_termica_qtde TO gsan_admin;
GRANT SELECT, UPDATE, INSERT, DELETE ON TABLE faturamento.conta_impressao_termica_qtde TO pg_aplic;
GRANT SELECT ON TABLE faturamento.conta_impressao_termica_qtde TO pg_users;



INSERT INTO seguranca.funcionalidade(
            fncd_id, modu_id, fncd_dsfuncionalidade, fncd_tmultimaalteracao, 
            fncd_dscaminhomenu, fncd_dscaminhourl, fncd_icpontoentrada, fncd_dsabreviado, 
            fncd_nnordemmenu, fncd_icnovajanela, fncd_icolap, fncg_id)
    VALUES (nextval('seguranca.seq_funcionalidade'), 7,'Consultar Quantidade de Contas Impressao Termica', now(), 
    		'Gsan/Faturamento/Conta', 'exibirFiltrarQtdeContaImpressaoTermicaAction.do', 1, 'qtCntImTe',0, 2, 2, 33);


INSERT INTO seguranca.operacao(
            oper_id, fncd_id, oper_dsoperacao, oper_dsabreviado, oper_dscaminhourl, 
            oper_tmultimaalteracao, oper_idoperacaopesquisa, tbco_id, optp_id, 
            tbco_idargumento, oper_icregistratransacao)
    VALUES (nextval('seguranca.seq_operacao'), currval('seguranca.seq_funcionalidade'), 'Consultar Quantidade de Contas Impressao Termica', 'qtCntImTe', 'consultarQtdeContaImpressaoTermicaAction.do', now(), null, null, 6, null, 2);

    
    
INSERT INTO seguranca.grupo_func_operacao(
            grup_id, oper_id, fncd_id, gfop_tmultimaalteracao)
    VALUES (1, currval('seguranca.seq_operacao'), currval('seguranca.seq_funcionalidade'), now());


-- //@UNDO
-- SQL to undo the change goes here.

DROP SEQUENCE faturamento.seq_conta_impressao_termica_qtde;
DROP TABLE faturamento.conta_impressao_termica_qtde;
DELETE FROM seguranca.grupo_func_operacao WHERE fncd_id in (
  SELECT fncd_id FROM seguranca.funcionalidade WHERE fncd_dscaminhourl = 'exibirFiltrarQtdeContaImpressaoTermicaAction.do'
);
DELETE FROM seguranca.operacao WHERE oper_dscaminhourl = 'consultarQtdeContaImpressaoTermicaAction.do';
DELETE FROM seguranca.funcionalidade WHERE fncd_dscaminhourl = 'exibirFiltrarQtdeContaImpressaoTermicaAction.do';

