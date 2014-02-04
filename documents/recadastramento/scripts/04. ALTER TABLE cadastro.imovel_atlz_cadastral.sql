--UPDATE cadastro.imovel_atlz_cadastral
-- SET empr_id = 1
-- WHERE 1=1;

ALTER TABLE cadastro.imovel_atlz_cadastral ALTER COLUMN imac_dsoutrasinformacoes TYPE VARCHAR (200);

ALTER TABLE cadastro.imovel_atlz_cadastral ADD COLUMN rlin_id INTEGER;
ALTER TABLE cadastro.imovel_atlz_cadastral
	ADD CONSTRAINT fk26_imovel_atlz_cadastral FOREIGN KEY (rlin_id)
	REFERENCES atendimentopublico.ramal_local_instalacao (rlin_id) MATCH SIMPLE
	ON UPDATE RESTRICT ON DELETE RESTRICT;

ALTER TABLE cadastro.imovel_atlz_cadastral ALTER COLUMN imac_nncoordenadax TYPE VARCHAR (20);
ALTER TABLE cadastro.imovel_atlz_cadastral ALTER COLUMN imac_nncoordenaday TYPE VARCHAR (20);


ALTER TABLE cadastro.cliente_atlz_cadastral DROP COLUMN imac_id;
ALTER TABLE cadastro.imovel_subcatg_atlz_cad DROP COLUMN imac_id;
ALTER TABLE cadastro.imovel_atlz_cadastral DROP COLUMN imac_id;

ALTER TABLE cadastro.imovel_atlz_cadastral ADD CONSTRAINT imovel_atlz_cadastral_pkey PRIMARY KEY (imov_id);

ALTER TABLE cadastro.imovel_atlz_cadastral ALTER COLUMN imac_icatualizado DROP NOT NULL;
