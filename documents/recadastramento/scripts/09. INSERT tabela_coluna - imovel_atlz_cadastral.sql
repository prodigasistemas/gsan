ALTER TABLE seguranca.tab_atlz_cadastral ADD column tatc_complemento VARCHAR;


INSERT INTO seguranca.tabela(
            tabe_id, tabe_nmtabela, tabe_dstabela)
    VALUES (661,'cadastro.imovel_atlz_cadastral', 'Imovel');


﻿INSERT INTO seguranca.tabela_coluna(
            tbco_id, tabe_id, tbco_tmultimaalteracao, tbco_nmcoluna, tbco_dscoluna, 
            tbco_icprimarykey, tbco_nmabreviado, atrb_id)
    VALUES ((select max(tbco_id) + 1 from seguranca.tabela_coluna), 661, now(), 'loca_id', 'Id da Localidade', 
            2, 'loca_id', null);

INSERT INTO seguranca.tabela_coluna(
            tbco_id, tabe_id, tbco_tmultimaalteracao, tbco_nmcoluna, tbco_dscoluna, 
            tbco_icprimarykey, tbco_nmabreviado, atrb_id)
    VALUES ((select max(tbco_id) + 1 from seguranca.tabela_coluna), 661, now(), 'imac_cdsetorcomercial', 'Codigo Setor Comercial', 
            2, 'imac_cdsetorcomercial', null);

INSERT INTO seguranca.tabela_coluna(
            tbco_id, tabe_id, tbco_tmultimaalteracao, tbco_nmcoluna, tbco_dscoluna, 
            tbco_icprimarykey, tbco_nmabreviado, atrb_id)
    VALUES ((select max(tbco_id) + 1 from seguranca.tabela_coluna), 661, now(), 'imac_nnquadra', 'Numero Quadra', 
            2, 'imac_nnquadra', null);

INSERT INTO seguranca.tabela_coluna(
            tbco_id, tabe_id, tbco_tmultimaalteracao, tbco_nmcoluna, tbco_dscoluna, 
            tbco_icprimarykey, tbco_nmabreviado, atrb_id)
    VALUES ((select max(tbco_id) + 1 from seguranca.tabela_coluna), 661, now(), 'imac_nnlote', 'Numero Lote', 
            2, 'imac_nnlote', null);

INSERT INTO seguranca.tabela_coluna(
            tbco_id, tabe_id, tbco_tmultimaalteracao, tbco_nmcoluna, tbco_dscoluna, 
            tbco_icprimarykey, tbco_nmabreviado, atrb_id)
    VALUES ((select max(tbco_id) + 1 from seguranca.tabela_coluna), 661, now(), 'imac_nnsublote', 'Numero Sublote', 
            2, 'imac_nnsublote', null);

INSERT INTO seguranca.tabela_coluna(
            tbco_id, tabe_id, tbco_tmultimaalteracao, tbco_nmcoluna, tbco_dscoluna, 
            tbco_icprimarykey, tbco_nmabreviado, atrb_id)
    VALUES ((select max(tbco_id) + 1 from seguranca.tabela_coluna), 661, now(), 'imac_nnsequencialrota', 'Numero Sequencial Rota', 
            2, 'imac_nnsequencialrota', null);

INSERT INTO seguranca.tabela_coluna(
            tbco_id, tabe_id, tbco_tmultimaalteracao, tbco_nmcoluna, tbco_dscoluna, 
            tbco_icprimarykey, tbco_nmabreviado, atrb_id)
    VALUES ((select max(tbco_id) + 1 from seguranca.tabela_coluna), 661, now(), 'lgtp_id', 'Id do Tipo de Logradouro', 
            2, 'lgtp_id', null);

INSERT INTO seguranca.tabela_coluna(
            tbco_id, tabe_id, tbco_tmultimaalteracao, tbco_nmcoluna, tbco_dscoluna, 
            tbco_icprimarykey, tbco_nmabreviado, atrb_id)
    VALUES ((select max(tbco_id) + 1 from seguranca.tabela_coluna), 661, now(), 'imac_dslogradourotipo', 'Desc Tipo de Logradouro', 
            2, 'imac_dslogradourotipo', null);

INSERT INTO seguranca.tabela_coluna(
            tbco_id, tabe_id, tbco_tmultimaalteracao, tbco_nmcoluna, tbco_dscoluna, 
            tbco_icprimarykey, tbco_nmabreviado, atrb_id)
    VALUES ((select max(tbco_id) + 1 from seguranca.tabela_coluna), 661, now(), 'lgtt_id', 'Id do Logradouro Titulo', 
            2, 'lgtt_id', null);

INSERT INTO seguranca.tabela_coluna(
            tbco_id, tabe_id, tbco_tmultimaalteracao, tbco_nmcoluna, tbco_dscoluna, 
            tbco_icprimarykey, tbco_nmabreviado, atrb_id)
    VALUES ((select max(tbco_id) + 1 from seguranca.tabela_coluna), 661, now(), 'imac_dslogradourotitulo', 'Desc do Logradouro Titulo', 
            2, 'imac_dslogradourotitulo', null);

INSERT INTO seguranca.tabela_coluna(
            tbco_id, tabe_id, tbco_tmultimaalteracao, tbco_nmcoluna, tbco_dscoluna, 
            tbco_icprimarykey, tbco_nmabreviado, atrb_id)
    VALUES ((select max(tbco_id) + 1 from seguranca.tabela_coluna), 661, now(), 'logr_id', 'Id do Logradouro', 
            2, 'logr_id', null);

INSERT INTO seguranca.tabela_coluna(
            tbco_id, tabe_id, tbco_tmultimaalteracao, tbco_nmcoluna, tbco_dscoluna, 
            tbco_icprimarykey, tbco_nmabreviado, atrb_id)
    VALUES ((select max(tbco_id) + 1 from seguranca.tabela_coluna), 661, now(), 'imac_dslogradouro', 'Desc do Logradouro', 
            2, 'imac_dslogradouro', null);

INSERT INTO seguranca.tabela_coluna(
            tbco_id, tabe_id, tbco_tmultimaalteracao, tbco_nmcoluna, tbco_dscoluna, 
            tbco_icprimarykey, tbco_nmabreviado, atrb_id)
    VALUES ((select max(tbco_id) + 1 from seguranca.tabela_coluna), 661, now(), 'imac_nmbairro', 'Nome do Bairro', 
            2, 'imac_nmbairro', null);

INSERT INTO seguranca.tabela_coluna(
            tbco_id, tabe_id, tbco_tmultimaalteracao, tbco_nmcoluna, tbco_dscoluna, 
            tbco_icprimarykey, tbco_nmabreviado, atrb_id)
    VALUES ((select max(tbco_id) + 1 from seguranca.tabela_coluna), 661, now(), 'imac_cdcep', 'Codigo do Cep', 
            2, 'imac_cdcep', null);

INSERT INTO seguranca.tabela_coluna(
            tbco_id, tabe_id, tbco_tmultimaalteracao, tbco_nmcoluna, tbco_dscoluna, 
            tbco_icprimarykey, tbco_nmabreviado, atrb_id)
    VALUES ((select max(tbco_id) + 1 from seguranca.tabela_coluna), 661, now(), 'imac_nnimovel', 'Numero do Imovel', 
            2, 'imac_nnimovel', null);

INSERT INTO seguranca.tabela_coluna(
            tbco_id, tabe_id, tbco_tmultimaalteracao, tbco_nmcoluna, tbco_dscoluna, 
            tbco_icprimarykey, tbco_nmabreviado, atrb_id)
    VALUES ((select max(tbco_id) + 1 from seguranca.tabela_coluna), 661, now(), 'imac_dscomplementoendereco', 'Complemento Endereco', 
            2, 'imac_dscomplementoend', null);

INSERT INTO seguranca.tabela_coluna(
            tbco_id, tabe_id, tbco_tmultimaalteracao, tbco_nmcoluna, tbco_dscoluna, 
            tbco_icprimarykey, tbco_nmabreviado, atrb_id)
    VALUES ((select max(tbco_id) + 1 from seguranca.tabela_coluna), 661, now(), 'ftab_id', 'Fonte de Abastecimento', 
            2, 'ftab_id', null);

INSERT INTO seguranca.tabela_coluna(
            tbco_id, tabe_id, tbco_tmultimaalteracao, tbco_nmcoluna, tbco_dscoluna, 
            tbco_icprimarykey, tbco_nmabreviado, atrb_id)
    VALUES ((select max(tbco_id) + 1 from seguranca.tabela_coluna), 661, now(), 'last_id', 'Situacao Ligacao Agua', 
            2, 'last_id', null);

INSERT INTO seguranca.tabela_coluna(
            tbco_id, tabe_id, tbco_tmultimaalteracao, tbco_nmcoluna, tbco_dscoluna, 
            tbco_icprimarykey, tbco_nmabreviado, atrb_id)
    VALUES ((select max(tbco_id) + 1 from seguranca.tabela_coluna), 661, now(), 'lest_id', 'Situacao Ligacao Esgoto', 
            2, 'lest_id', null);

INSERT INTO seguranca.tabela_coluna(
            tbco_id, tabe_id, tbco_tmultimaalteracao, tbco_nmcoluna, tbco_dscoluna, 
            tbco_icprimarykey, tbco_nmabreviado, atrb_id)
    VALUES ((select max(tbco_id) + 1 from seguranca.tabela_coluna), 661, now(), 'imac_nnmorador', 'Numero de Moradores', 
            2, 'imac_nnmorador', null);

INSERT INTO seguranca.tabela_coluna(
            tbco_id, tabe_id, tbco_tmultimaalteracao, tbco_nmcoluna, tbco_dscoluna, 
            tbco_icprimarykey, tbco_nmabreviado, atrb_id)
    VALUES ((select max(tbco_id) + 1 from seguranca.tabela_coluna), 661, now(), 'imac_nniptu', 'Numero IPTU', 
            2, 'imac_nniptu', null);

INSERT INTO seguranca.tabela_coluna(
            tbco_id, tabe_id, tbco_tmultimaalteracao, tbco_nmcoluna, tbco_dscoluna, 
            tbco_icprimarykey, tbco_nmabreviado, atrb_id)
    VALUES ((select max(tbco_id) + 1 from seguranca.tabela_coluna), 661, now(), 'imac_nncoordenadax', 'Latidude', 
            2, 'imac_nncoordenadax', null);

INSERT INTO seguranca.tabela_coluna(
            tbco_id, tabe_id, tbco_tmultimaalteracao, tbco_nmcoluna, tbco_dscoluna, 
            tbco_icprimarykey, tbco_nmabreviado, atrb_id)
    VALUES ((select max(tbco_id) + 1 from seguranca.tabela_coluna), 661, now(), 'imac_nncoordenaday', 'Longitude', 
            2, 'imac_nncoordenadax', null);

INSERT INTO seguranca.tabela_coluna(
            tbco_id, tabe_id, tbco_tmultimaalteracao, tbco_nmcoluna, tbco_dscoluna, 
            tbco_icprimarykey, tbco_nmabreviado, atrb_id)
    VALUES ((select max(tbco_id) + 1 from seguranca.tabela_coluna), 661, now(), 'hicp_id', 'Capacidade Hidrometro', 
            2, 'hicp_id', null);

INSERT INTO seguranca.tabela_coluna(
            tbco_id, tabe_id, tbco_tmultimaalteracao, tbco_nmcoluna, tbco_dscoluna, 
            tbco_icprimarykey, tbco_nmabreviado, atrb_id)
    VALUES ((select max(tbco_id) + 1 from seguranca.tabela_coluna), 661, now(), 'himc_id', 'Marca Hidrometro', 
            2, 'himc_id', null);

INSERT INTO seguranca.tabela_coluna(
            tbco_id, tabe_id, tbco_tmultimaalteracao, tbco_nmcoluna, tbco_dscoluna, 
            tbco_icprimarykey, tbco_nmabreviado, atrb_id)
    VALUES ((select max(tbco_id) + 1 from seguranca.tabela_coluna), 661, now(), 'hili_id', 'Local Instalacao Hidrometro', 
            2, 'hili_id', null);

INSERT INTO seguranca.tabela_coluna(
            tbco_id, tabe_id, tbco_tmultimaalteracao, tbco_nmcoluna, tbco_dscoluna, 
            tbco_icprimarykey, tbco_nmabreviado, atrb_id)
    VALUES ((select max(tbco_id) + 1 from seguranca.tabela_coluna), 661, now(), 'imac_nnhidrometro', 'Numero do Hidrometro', 
            2, 'imac_nnhidrometro', null);


INSERT INTO seguranca.tabela_coluna(
            tbco_id, tabe_id, tbco_tmultimaalteracao, tbco_nmcoluna, tbco_dscoluna, 
            tbco_icprimarykey, tbco_nmabreviado, atrb_id)
    VALUES ((select max(tbco_id) + 1 from seguranca.tabela_coluna), 661, now(), 'imac_nnmedidorenergia', 'Numero Medidor de Energia', 
            2, 'imac_nnmedidorenergia', null);

INSERT INTO seguranca.tabela_coluna(
            tbco_id, tabe_id, tbco_tmultimaalteracao, tbco_nmcoluna, tbco_dscoluna, 
            tbco_icprimarykey, tbco_nmabreviado, atrb_id)
    VALUES ((select max(tbco_id) + 1 from seguranca.tabela_coluna), 661, now(), 'cocr_id', 'Ocorrencia de Cadastro', 
            2, 'cocr_id', null);

INSERT INTO seguranca.tabela_coluna(
            tbco_id, tabe_id, tbco_tmultimaalteracao, tbco_nmcoluna, tbco_dscoluna, 
            tbco_icprimarykey, tbco_nmabreviado, atrb_id)
    VALUES ((select max(tbco_id) + 1 from seguranca.tabela_coluna), 661, now(), 'imac_dsoutrasinformacoes', 'Outras Informacoes', 
            2, 'imac_dsoutrasinformacoes', null);

INSERT INTO seguranca.tabela_coluna(
            tbco_id, tabe_id, tbco_tmultimaalteracao, tbco_nmcoluna, tbco_dscoluna, 
            tbco_icprimarykey, tbco_nmabreviado, atrb_id)
    VALUES ((select max(tbco_id) + 1 from seguranca.tabela_coluna), 661, now(), 'imac_tipoentrevistado', 'Entrevistado', 
            2, 'imac_nsentrevistado', null);

INSERT INTO seguranca.tabela_coluna(
            tbco_id, tabe_id, tbco_tmultimaalteracao, tbco_nmcoluna, tbco_dscoluna, 
            tbco_icprimarykey, tbco_nmabreviado, atrb_id)
    VALUES ((select max(tbco_id) + 1 from seguranca.tabela_coluna), 661, now(), 'rlin_id', 'Local Instalacao de Ramal', 
            2, 'rlin_id', null);

INSERT INTO seguranca.tabela_coluna(
           tbco_id, tabe_id, tbco_tmultimaalteracao, tbco_nmcoluna, tbco_dscoluna, 
           tbco_icprimarykey, tbco_nmabreviado, atrb_id)
   VALUES ((SELECT max(tbco_id)+1 from seguranca.tabela_coluna),661, now(), 'imac_nnpontosutilizacao', 'Numero Pontos Utilizacao',
	   2, 'imac_nnpontosutilizacao', null);

INSERT INTO seguranca.tabela_coluna(
           tbco_id, tabe_id, tbco_tmultimaalteracao, tbco_nmcoluna, tbco_dscoluna, 
           tbco_icprimarykey, tbco_nmabreviado, atrb_id)
   VALUES ((SELECT max(tbco_id)+1 from seguranca.tabela_coluna),661, now(), 'hipr_id', 'Protecao Hidrometro',
	   2, 'hipr_id', null);





