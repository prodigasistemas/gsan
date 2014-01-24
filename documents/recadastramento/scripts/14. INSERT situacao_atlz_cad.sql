INSERT INTO cadastro.situacao_atlz_cadastral(
            siac_id, siac_dssituacao, siac_icuso, siac_tmultimaalteracao)
    VALUES (1, 'BLOQUEADO', 1, NOW());


INSERT INTO cadastro.situacao_atlz_cadastral(
            siac_id, siac_dssituacao, siac_icuso, siac_tmultimaalteracao)
    VALUES (2, 'EM CAMPO', 1, NOW());


INSERT INTO cadastro.situacao_atlz_cadastral(
            siac_id, siac_dssituacao, siac_icuso, siac_tmultimaalteracao)
    VALUES (3, 'TRANSMITIDO', 1, now());


INSERT INTO cadastro.situacao_atlz_cadastral(
            siac_id, siac_dssituacao, siac_icuso, siac_tmultimaalteracao)
    VALUES (4, 'APROVADO', 1, NOW());


UPDATE cadastro.situacao_atlz_cadastral
   SET siac_dssituacao = 'DISPONIVEL', siac_icuso=1
 WHERE siac_id = 0;
