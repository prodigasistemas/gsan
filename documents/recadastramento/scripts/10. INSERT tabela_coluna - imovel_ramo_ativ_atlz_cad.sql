INSERT INTO seguranca.tabela(
            tabe_id, tabe_nmtabela, tabe_dstabela)
    VALUES (665,'cadastro.imovel_ramo_ativ_atlz_cad', 'Ramo Atividade');

INSERT INTO seguranca.tabela_coluna(
            tbco_id, tabe_id, tbco_tmultimaalteracao, tbco_nmcoluna, tbco_dscoluna, 
            tbco_icprimarykey, tbco_nmabreviado, atrb_id)
    VALUES ((select max(tbco_id) + 1 from seguranca.tabela_coluna), 665, now(), 'ratv_id', 'Ramo Atividade', 
            2, 'ratv_id', null);
