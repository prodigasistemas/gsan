INSERT INTO seguranca.tabela(
            tabe_id, tabe_nmtabela, tabe_dstabela)
    VALUES (664,'cadastro.imovel_subcatg_atlz_cad', 'Subcategoria');


INSERT INTO seguranca.tabela_coluna(
            tbco_id, tabe_id, tbco_tmultimaalteracao, tbco_nmcoluna, tbco_dscoluna, 
            tbco_icprimarykey, tbco_nmabreviado, atrb_id)
    VALUES ((select max(tbco_id) + 1 from seguranca.tabela_coluna), 664, now(), 'scat_id', 'Id Subcategoria', 
            2, null, null);

INSERT INTO seguranca.tabela_coluna(
            tbco_id, tabe_id, tbco_tmultimaalteracao, tbco_nmcoluna, tbco_dscoluna, 
            tbco_icprimarykey, tbco_nmabreviado, atrb_id)
    VALUES ((select max(tbco_id) + 1 from seguranca.tabela_coluna), 664, now(), 'isac_dscategoria', 'Descricao Categoria', 
            2, null, null);

INSERT INTO seguranca.tabela_coluna(
            tbco_id, tabe_id, tbco_tmultimaalteracao, tbco_nmcoluna, tbco_dscoluna, 
            tbco_icprimarykey, tbco_nmabreviado, atrb_id)
    VALUES ((select max(tbco_id) + 1 from seguranca.tabela_coluna), 664, now(), 'catg_id', 'Id Categoria', 
            2, null, null);

INSERT INTO seguranca.tabela_coluna(
            tbco_id, tabe_id, tbco_tmultimaalteracao, tbco_nmcoluna, tbco_dscoluna, 
            tbco_icprimarykey, tbco_nmabreviado, atrb_id)
    VALUES ((select max(tbco_id) + 1 from seguranca.tabela_coluna), 664, now(), 'isac_dssubcategoria', 'Descricao Subcategoria', 
            2, null, null);

INSERT INTO seguranca.tabela_coluna(
            tbco_id, tabe_id, tbco_tmultimaalteracao, tbco_nmcoluna, tbco_dscoluna, 
            tbco_icprimarykey, tbco_nmabreviado, atrb_id)
    VALUES ((select max(tbco_id) + 1 from seguranca.tabela_coluna), 664, now(), 'isac_qteconomia', 'Quantidade de Economias', 
            2, null, null);





