ALTER TABLE cadastro.imovel ALTER COLUMN imov_nniptu TYPE character varying(31);

ALTER TABLE cadastro.imovel_economia ALTER COLUMN imec_nniptu TYPE character varying(31);

ALTER TABLE cadastro.imovel_atlz_cadastral ALTER COLUMN imac_nniptu TYPE character varying(31);

ALTER TABLE atualizacaocadastral.imovel_retorno ALTER COLUMN imac_nniptu TYPE character varying(31);
