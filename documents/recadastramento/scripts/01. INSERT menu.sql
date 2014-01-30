--UPDATE micromedicao.leiturista
--   SET empr_id=1
-- WHERE 1=1;

 
INSERT INTO seguranca.grupo_func_operacao(
            grup_id, oper_id, fncd_id)
    VALUES (1, 1385, 1088);

INSERT INTO seguranca.grupo_func_operacao(
            grup_id, oper_id, fncd_id)
    VALUES (1, 1419, 1120);

INSERT INTO seguranca.grupo_func_operacao(
            grup_id, oper_id, fncd_id)
    VALUES (1, 1454, 1152);

INSERT INTO seguranca.grupo_func_operacao(
            grup_id, oper_id, fncd_id)
    VALUES (1, 1488, 1194);

INSERT INTO seguranca.grupo_func_operacao(
            grup_id, oper_id, fncd_id)
    VALUES (1, 1489, 1194);

INSERT INTO seguranca.grupo_func_operacao(
            grup_id, oper_id, fncd_id)
    VALUES (1, 1504, 1211);

INSERT INTO seguranca.grupo_func_operacao(
            grup_id, oper_id, fncd_id)
    VALUES (1, 1507, 1211);

INSERT INTO seguranca.grupo_func_operacao(
            grup_id, oper_id, fncd_id)
    VALUES (1, 1508, 1211);

INSERT INTO seguranca.grupo_func_operacao(
            grup_id, oper_id, fncd_id)
    VALUES (1, 1509, 1211);

INSERT INTO seguranca.grupo_func_operacao(
            grup_id, oper_id, fncd_id)
    VALUES (1, 1515, 1211);

INSERT INTO seguranca.operacao(
            oper_id, fncd_id, oper_dsoperacao, oper_dsabreviado, oper_dscaminhourl, 
             optp_id)
    VALUES (1502, 1207, 'Carregar Dados Atualizacao Cadastral', 'CaDaAtuCad', 'carregarDadosAtualizacaoCadastralAction.do',3);

INSERT INTO seguranca.grupo_func_operacao(
            grup_id, oper_id, fncd_id)
    VALUES (1, 1502, 1207);




--DELETE FROM seguranca.grupo_func_operacao
-- WHERE oper_id = 1504 AND fncd_id = 1210;

--DELETE FROM seguranca.operacao
-- WHERE oper_id = 1504 AND fncd_id = 1210;

--DELETE FROM seguranca.funcionalidade_depend
-- WHERE fncd_id = 1210;

--DELETE FROM seguranca.usuario_favoritos
-- WHERE fncd_id = 1210;
 
--DELETE FROM seguranca.funcionalidade
-- WHERE fncd_id = 1210;




UPDATE seguranca.funcionalidade
   SET fncd_dscaminhomenu='Gsan/Cadastro/Atualizacao Cadastral', fncd_icpontoentrada=1, 
      fncg_id=121
 WHERE fncd_id = 1088 or
fncd_id = 1120 or
fncd_id = 1152 or
fncd_id = 1194 or
fncd_id = 1211 or
fncd_id = 1207;


