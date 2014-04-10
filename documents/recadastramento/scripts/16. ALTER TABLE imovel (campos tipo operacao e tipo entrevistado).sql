ALTER TABLE cadastro.imovel_atlz_cadastral RENAME COLUMN imac_nmentrevistado TO imac_tipoentrevistado;

UPDATE seguranca.tabela_coluna
   SET tbco_nmcoluna='imac_tipoentrevistado', tbco_dscoluna = 'Entrevistado'
 WHERE tabe_id = 661 AND tbco_nmcoluna='imac_nmentrevistado';

ALTER TABLE cadastro.imovel_atlz_cadastral ADD COLUMN imac_tipooperacao INTEGER;
ALTER TABLE atualizacaocadastral.imovel_retorno RENAME COLUMN imac_nmentrevistado TO imac_tipoentrevistado;
ALTER TABLE atualizacaocadastral.imovel_retorno ADD COLUMN imac_tipooperacao INTEGER;