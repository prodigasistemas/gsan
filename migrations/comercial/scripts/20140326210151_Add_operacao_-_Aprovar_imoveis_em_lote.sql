-- // Add operacao - Aprovar imoveis em lote
-- Migration SQL that makes the change goes here.

INSERT INTO seguranca.funcionalidade(
        fncd_id, modu_id, fncd_dsfuncionalidade, fncd_tmultimaalteracao, 
        fncd_dscaminhomenu, fncd_dscaminhourl, fncd_icpontoentrada, fncd_dsabreviado, 
        fncd_nnordemmenu, fncd_icnovajanela, fncd_icolap)
VALUES (16007,1,'Aprovar imoveis em lote',now(),
'Gsan/','aprovarImoveisEmLoteAction.do',2,'AprImLot',1,2,2);


insert into seguranca.operacao
(oper_id, fncd_id, oper_dsoperacao, oper_dsabreviado, oper_dscaminhourl,  optp_id)
values (nextval('seguranca.seq_operacao'), 16007, 'Aprovar imoveis em lote', 'AprImLot', '' ,12);


insert into seguranca.grupo_func_operacao
values (1, currval('seguranca.seq_operacao'), 16007)

-- //@UNDO
-- SQL to undo the change goes here.

delete from seguranca.grupo_func_operacao
where oper_id = currval('seguranca.seq_operacao')

delete from seguranca.operacao
where fncd_id = 16007
and oper_dsoperacao like 'Aprovar imoveis em lote';

and fncd_id = 16007

delete from seguranca.funcionalidade
where fncd_id = 16007

