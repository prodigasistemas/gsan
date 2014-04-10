INSERT INTO seguranca.tabela(
            tabe_id, tabe_nmtabela, tabe_dstabela)
    VALUES (663,'cadastro.cliente_Telefone_atlz_cad', 'Telefone');


INSERT INTO seguranca.tabela_coluna(
	    tbco_id, tabe_id, tbco_tmultimaalteracao, tbco_nmcoluna, tbco_dscoluna, 
	    tbco_icprimarykey, tbco_nmabreviado, atrb_id)
	VALUES ((select max(tbco_id) + 1 from seguranca.tabela_coluna), 663, now(), 'clac_id', 'Id Cliente', 
		2, null, null);

INSERT INTO seguranca.tabela_coluna(
	    tbco_id, tabe_id, tbco_tmultimaalteracao, tbco_nmcoluna, tbco_dscoluna, 
	    tbco_icprimarykey, tbco_nmabreviado, atrb_id)
	VALUES ((select max(tbco_id) + 1 from seguranca.tabela_coluna), 663, now(), 'cfac_cdddd', 'Codigo ddd', 
		2, null, null);

INSERT INTO seguranca.tabela_coluna(
	    tbco_id, tabe_id, tbco_tmultimaalteracao, tbco_nmcoluna, tbco_dscoluna, 
	    tbco_icprimarykey, tbco_nmabreviado, atrb_id)
	VALUES ((select max(tbco_id) + 1 from seguranca.tabela_coluna), 663, now(), 'cfac_nnfone', 'Numero Telefone', 
		2, null, null);

INSERT INTO seguranca.tabela_coluna(
	    tbco_id, tabe_id, tbco_tmultimaalteracao, tbco_nmcoluna, tbco_dscoluna, 
	    tbco_icprimarykey, tbco_nmabreviado, atrb_id)
	VALUES ((select max(tbco_id) + 1 from seguranca.tabela_coluna), 663, now(), 'cfac_nnfoneramal', 'Ramal Telefone', 
		2, null, null);

INSERT INTO seguranca.tabela_coluna(
	    tbco_id, tabe_id, tbco_tmultimaalteracao, tbco_nmcoluna, tbco_dscoluna, 
	    tbco_icprimarykey, tbco_nmabreviado, atrb_id)
	VALUES ((select max(tbco_id) + 1 from seguranca.tabela_coluna), 663, now(), 'fnet_id', 'Telefone Tipo', 
		2, null, null);

INSERT INTO seguranca.tabela_coluna(
	    tbco_id, tabe_id, tbco_tmultimaalteracao, tbco_nmcoluna, tbco_dscoluna, 
	    tbco_icprimarykey, tbco_nmabreviado, atrb_id)
	VALUES ((select max(tbco_id) + 1 from seguranca.tabela_coluna), 663, now(), 'cfac_icfonepadrao', 'Indicador Telefone Padrao', 
		2, null, null);




