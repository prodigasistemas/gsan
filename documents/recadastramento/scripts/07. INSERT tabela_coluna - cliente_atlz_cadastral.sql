INSERT INTO seguranca.tabela(
            tabe_id, tabe_nmtabela, tabe_dstabela)
    VALUES (662,'cadastro.cliente_atlz_cadastral', 'Cliente');


INSERT INTO seguranca.tabela_coluna(tbco_id, tabe_id, tbco_tmultimaalteracao, tbco_nmcoluna, tbco_dscoluna,
	    tbco_icprimarykey, tbco_nmabreviado, atrb_id)
    VALUES ((select max(tbco_id) + 1 from seguranca.tabela_coluna), 662, now(), 'clac_nmcliente', 'Nome Cliente',
	        2, 'clac_nncliente', null);

INSERT INTO seguranca.tabela_coluna(
            tbco_id, tabe_id, tbco_tmultimaalteracao, tbco_nmcoluna, tbco_dscoluna, 
            tbco_icprimarykey, tbco_nmabreviado, atrb_id)
    VALUES ((select max(tbco_id) + 1 from seguranca.tabela_coluna), 662, now(), 'crtp_id', 'Relacao Tipo', 
            2, 'crtp_id', null);

INSERT INTO seguranca.tabela_coluna(
            tbco_id, tabe_id, tbco_tmultimaalteracao, tbco_nmcoluna, tbco_dscoluna, 
            tbco_icprimarykey, tbco_nmabreviado, atrb_id)
    VALUES ((select max(tbco_id) + 1 from seguranca.tabela_coluna), 662, now(), 'cltp_id', 'Cliente Tipo', 
            2, 'cltp_id', null);

INSERT INTO seguranca.tabela_coluna(
            tbco_id, tabe_id, tbco_tmultimaalteracao, tbco_nmcoluna, tbco_dscoluna, 
            tbco_icprimarykey, tbco_nmabreviado, atrb_id)
    VALUES ((select max(tbco_id) + 1 from seguranca.tabela_coluna), 662, now(), 'clac_nncpfcnpj', 'Numero CPF/CNPJ', 
            2, 'clac_nmcpfcnpj', null);

INSERT INTO seguranca.tabela_coluna(
            tbco_id, tabe_id, tbco_tmultimaalteracao, tbco_nmcoluna, tbco_dscoluna, 
            tbco_icprimarykey, tbco_nmabreviado, atrb_id)
    VALUES ((select max(tbco_id) + 1 from seguranca.tabela_coluna), 662, now(), 'clac_nnrg', 'Numero RG', 
            2, 'clac_nnrg', null);

INSERT INTO seguranca.tabela_coluna(
            tbco_id, tabe_id, tbco_tmultimaalteracao, tbco_nmcoluna, tbco_dscoluna, 
            tbco_icprimarykey, tbco_nmabreviado, atrb_id)
    VALUES ((select max(tbco_id) + 1 from seguranca.tabela_coluna), 662, now(), 'clac_dsabreviadaoerg', 'Desc Orgao ExpedIdor RG', 
            2, 'clac_dsoergabreviada', null);

INSERT INTO seguranca.tabela_coluna(
            tbco_id, tabe_id, tbco_tmultimaalteracao, tbco_nmcoluna, tbco_dscoluna, 
            tbco_icprimarykey, tbco_nmabreviado, atrb_id)
    VALUES ((select max(tbco_id) + 1 from seguranca.tabela_coluna), 662, now(), 'clac_dsufsiglaoerg', 'Sigla UF RG', 
            2, 'clac_dsufsiglaoerg', null);

INSERT INTO seguranca.tabela_coluna(
            tbco_id, tabe_id, tbco_tmultimaalteracao, tbco_nmcoluna, tbco_dscoluna, 
            tbco_icprimarykey, tbco_nmabreviado, atrb_id)
    VALUES ((select max(tbco_id) + 1 from seguranca.tabela_coluna), 662, now(), 'clac_dtrgemissao', 'Data Emissao RG', 
            2, 'clac_dtrgemissao', null);

INSERT INTO seguranca.tabela_coluna(
            tbco_id, tabe_id, tbco_tmultimaalteracao, tbco_nmcoluna, tbco_dscoluna, 
            tbco_icprimarykey, tbco_nmabreviado, atrb_id)
    VALUES ((select max(tbco_id) + 1 from seguranca.tabela_coluna), 662, now(), 'clac_dtnascimento', 'Data de Nascimento', 
            2, 'clac_dtnascimento', null);

INSERT INTO seguranca.tabela_coluna(
            tbco_id, tabe_id, tbco_tmultimaalteracao, tbco_nmcoluna, tbco_dscoluna, 
            tbco_icprimarykey, tbco_nmabreviado, atrb_id)
    VALUES ((select max(tbco_id) + 1 from seguranca.tabela_coluna), 662, now(), 'prof_id', 'Profissao', 
            2, 'prof_id', null);

INSERT INTO seguranca.tabela_coluna(
            tbco_id, tabe_id, tbco_tmultimaalteracao, tbco_nmcoluna, tbco_dscoluna, 
            tbco_icprimarykey, tbco_nmabreviado, atrb_id)
    VALUES ((select max(tbco_id) + 1 from seguranca.tabela_coluna), 662, now(), 'ratv_id', 'Ramo de AtivIdade', 
            2, 'ratv_id', null);

INSERT INTO seguranca.tabela_coluna(
            tbco_id, tabe_id, tbco_tmultimaalteracao, tbco_nmcoluna, tbco_dscoluna, 
            tbco_icprimarykey, tbco_nmabreviado, atrb_id)
    VALUES ((select max(tbco_id) + 1 from seguranca.tabela_coluna), 662, now(), 'psex_id', 'Sexo', 
            2, 'psex_id', null);

INSERT INTO seguranca.tabela_coluna(
            tbco_id, tabe_id, tbco_tmultimaalteracao, tbco_nmcoluna, tbco_dscoluna, 
            tbco_icprimarykey, tbco_nmabreviado, atrb_id)
    VALUES ((select max(tbco_id) + 1 from seguranca.tabela_coluna), 662, now(), 'clac_dsemail', 'Email', 
            2, 'clac_dsemail', null);

INSERT INTO seguranca.tabela_coluna(
            tbco_id, tabe_id, tbco_tmultimaalteracao, tbco_nmcoluna, tbco_dscoluna, 
            tbco_icprimarykey, tbco_nmabreviado, atrb_id)
    VALUES ((select max(tbco_id) + 1 from seguranca.tabela_coluna), 662, now(), 'clac_nnmae', 'Nome da Mae', 
            2, 'clac_nnmae', null);

INSERT INTO seguranca.tabela_coluna(
            tbco_id, tabe_id, tbco_tmultimaalteracao, tbco_nmcoluna, tbco_dscoluna, 
            tbco_icprimarykey, tbco_nmabreviado, atrb_id)
    VALUES ((select max(tbco_id) + 1 from seguranca.tabela_coluna), 662, now(), 'edtp_id', 'Tipo de Endereco', 
            2, 'edtp_id', null);

INSERT INTO seguranca.tabela_coluna(
            tbco_id, tabe_id, tbco_tmultimaalteracao, tbco_nmcoluna, tbco_dscoluna, 
            tbco_icprimarykey, tbco_nmabreviado, atrb_id)
    VALUES ((select max(tbco_id) + 1 from seguranca.tabela_coluna), 662, now(), 'lgtp_id', 'Tipo de Logradouro', 
            2, 'lgtp_id', null);

INSERT INTO seguranca.tabela_coluna(
            tbco_id, tabe_id, tbco_tmultimaalteracao, tbco_nmcoluna, tbco_dscoluna, 
            tbco_icprimarykey, tbco_nmabreviado, atrb_id)
    VALUES ((select max(tbco_id) + 1 from seguranca.tabela_coluna), 662, now(), 'clac_dslogradourotipo', 'Desc Tipo Logradouro', 
            2, null, null);

INSERT INTO seguranca.tabela_coluna(
            tbco_id, tabe_id, tbco_tmultimaalteracao, tbco_nmcoluna, tbco_dscoluna, 
            tbco_icprimarykey, tbco_nmabreviado, atrb_id)
    VALUES ((select max(tbco_id) + 1 from seguranca.tabela_coluna), 662, now(), 'lgtt_id', 'IdLogradouro Titulo', 
            2, null, null);

INSERT INTO seguranca.tabela_coluna(
            tbco_id, tabe_id, tbco_tmultimaalteracao, tbco_nmcoluna, tbco_dscoluna, 
            tbco_icprimarykey, tbco_nmabreviado, atrb_id)
    VALUES ((select max(tbco_id) + 1 from seguranca.tabela_coluna), 662, now(), 'clac_dslogradourotitulo', 'Desc Titulo Logradouro', 
            2, null, null);

INSERT INTO seguranca.tabela_coluna(
            tbco_id, tabe_id, tbco_tmultimaalteracao, tbco_nmcoluna, tbco_dscoluna, 
            tbco_icprimarykey, tbco_nmabreviado, atrb_id)
    VALUES ((select max(tbco_id) + 1 from seguranca.tabela_coluna), 662, now(), 'logr_id', 'Id do Logradouro', 
            2, null, null);

INSERT INTO seguranca.tabela_coluna(
            tbco_id, tabe_id, tbco_tmultimaalteracao, tbco_nmcoluna, tbco_dscoluna, 
            tbco_icprimarykey, tbco_nmabreviado, atrb_id)
    VALUES ((select max(tbco_id) + 1 from seguranca.tabela_coluna), 662, now(), 'clac_dslogradouro', 'Desc Logradouro', 
            2, null, null);

INSERT INTO seguranca.tabela_coluna(
            tbco_id, tabe_id, tbco_tmultimaalteracao, tbco_nmcoluna, tbco_dscoluna, 
            tbco_icprimarykey, tbco_nmabreviado, atrb_id)
    VALUES ((select max(tbco_id) + 1 from seguranca.tabela_coluna), 662, now(), 'clac_cdcep', 'Codigo CEP', 
            2, null, null);

INSERT INTO seguranca.tabela_coluna(
            tbco_id, tabe_id, tbco_tmultimaalteracao, tbco_nmcoluna, tbco_dscoluna, 
            tbco_icprimarykey, tbco_nmabreviado, atrb_id)
    VALUES ((select max(tbco_id) + 1 from seguranca.tabela_coluna), 662, now(), 'clac_nmbairro', 'Nome do Bairro', 
            2, null, null);

INSERT INTO seguranca.tabela_coluna(
            tbco_id, tabe_id, tbco_tmultimaalteracao, tbco_nmcoluna, tbco_dscoluna, 
            tbco_icprimarykey, tbco_nmabreviado, atrb_id)
    VALUES ((select max(tbco_id) + 1 from seguranca.tabela_coluna), 662, now(), 'clac_nnimovel', 'Numero do Imovel', 
            2, null, null);

INSERT INTO seguranca.tabela_coluna(
            tbco_id, tabe_id, tbco_tmultimaalteracao, tbco_nmcoluna, tbco_dscoluna, 
            tbco_icprimarykey, tbco_nmabreviado, atrb_id)
    VALUES ((select max(tbco_id) + 1 from seguranca.tabela_coluna), 662, now(), 'clac_dscomplementoendereco', 'Complemento Endereco', 
            2, null, null);


INSERT INTO seguranca.tabela_coluna(
            tbco_id, tabe_id, tbco_tmultimaalteracao, tbco_nmcoluna, tbco_dscoluna, 
            tbco_icprimarykey, tbco_nmabreviado, atrb_id)
    VALUES ((select max(tbco_id) + 1 from seguranca.tabela_coluna), 662, now(), 'edrf_id', 'Endereco de Conferencia', 
            2, null, null);

INSERT INTO seguranca.tabela_coluna(
            tbco_id, tabe_id, tbco_tmultimaalteracao, tbco_nmcoluna, tbco_dscoluna, 
            tbco_icprimarykey, tbco_nmabreviado, atrb_id)
    VALUES ((select max(tbco_id) + 1 from seguranca.tabela_coluna), 662, now(), 'clac_nncnae', 'Codigo do CNAE', 
            2, null, null);
