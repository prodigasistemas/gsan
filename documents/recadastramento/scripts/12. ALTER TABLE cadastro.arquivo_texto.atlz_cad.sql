ALTER TABLE cadastro.arquivo_texto_atlz_cad DROP COLUMN txac_cdrota;

ALTER TABLE cadastro.arquivo_texto_atlz_cad ADD COLUMN rota_id INTEGER
	CONSTRAINT fk4_arquivo_texto_atualizacao_cadastral REFERENCES micromedicao.rota (rota_id)
	MATCH SIMPLE ON UPDATE RESTRICT ON DELETE RESTRICT;



--ALTER TABLE cadastro.arquivo_texto_atlz_cad DROP COLUMN sitl_id;
    
--ALTER TABLE cadastro.arquivo_texto_atlz_cad DROP CONSTRAINT fk4_arquivo_texto_atualizacao_cadastral;

--ALTER TABLE cadastro.arquivo_texto_atlz_cad ADD COLUMN sitl_id INTEGER NOT NULL
--	CONSTRAINT fk4_arquivo_texto_atualizacao_cadastral REFERENCES micromedicao.situacao_transm_leitura (sitl_id)
--	MATCH SIMPLE ON UPDATE RESTRICT ON DELETE RESTRICT;