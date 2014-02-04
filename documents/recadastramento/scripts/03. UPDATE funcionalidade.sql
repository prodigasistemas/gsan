DELETE FROM seguranca.grupo_func_operacao
WHERE fncd_id = 1210;

INSERT INTO seguranca.grupo_func_operacao(grup_id, oper_id, fncd_id, gfop_tmultimaalteracao)
VALUES (1, 1504, 1211, now());

UPDATE seguranca.operacao
SET oper_dscaminhourl='filtrarAlteracaoAtualizacaoCadastralAction.do', fncd_id = 1211
WHERE oper_id = 1504;

UPDATE seguranca.funcionalidade
SET fncd_icpontoentrada = 2, fncd_dscaminhourl = null
WHERE fncd_id = 1210;

UPDATE seguranca.funcionalidade
SET fncd_icpontoentrada=2
WHERE fncd_id = 1120;

UPDATE seguranca.funcionalidade
SET fncd_dscaminhomenu = 'Gsan/Cadastro/Atualizacao Cadastral'
WHERE fncd_id IN (1088,1120,1152,1194,1207,1210,1211,1566);

UPDATE seguranca.funcionalidade
SET fncd_nnordemmenu=13000000
WHERE fncd_id = 1088;

UPDATE seguranca.funcionalidade
SET fncd_nnordemmenu=13000009
WHERE fncd_id = 1120;

UPDATE seguranca.funcionalidade
SET fncd_nnordemmenu=13000001, fncd_dsfuncionalidade = 'Gerar Arquivo Texto'
WHERE fncd_id = 1152;

UPDATE seguranca.funcionalidade
SET fncd_nnordemmenu=13000002
WHERE fncd_id = 1194;

UPDATE seguranca.funcionalidade
SET fncd_nnordemmenu=13000003, fncd_dsfuncionalidade = 'Carregar Arquivo Retorno'
WHERE fncd_id = 1207;

UPDATE seguranca.funcionalidade
SET fncd_nnordemmenu=13000010
WHERE fncd_id = 1210;

UPDATE seguranca.funcionalidade
SET fncd_nnordemmenu=13000004, fncd_dsfuncionalidade = 'Consultar Movimento'
WHERE fncd_id = 1211;

UPDATE seguranca.funcionalidade
SET fncd_nnordemmenu=13000011
WHERE fncd_id = 1566;

UPDATE seguranca.funcionalidade_categoria
SET fncg_dsfuncionalidadecategoria='Atualizacao Cadastral', fncg_nnordem=140
WHERE fncg_id = 121;




