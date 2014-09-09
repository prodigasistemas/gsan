-- // Deletando Constraints cobranca_doc_item_hist
-- Migration SQL that makes the change goes here.
ALTER TABLE cobranca.cobranca_doc_item_hist DROP CONSTRAINT fk1_cobranca_documento_item_historico;
ALTER TABLE cobranca.cobranca_doc_item_hist DROP CONSTRAINT fk2_cobranca_documento_item_historico;
ALTER TABLE cobranca.cobranca_doc_item_hist DROP CONSTRAINT fk3_cobranca_documento_item_historico;
ALTER TABLE cobranca.cobranca_doc_item_hist DROP CONSTRAINT fk4_cobranca_documento_item_historico;
ALTER TABLE cobranca.cobranca_doc_item_hist DROP CONSTRAINT fk5_cobranca_documento_item_historico;
ALTER TABLE cobranca.cobranca_doc_item_hist DROP CONSTRAINT fk6_cobranca_documento_item_historico;
ALTER TABLE cobranca.cobranca_doc_item_hist DROP CONSTRAINT fk7_cobranca_documento_item_historico;


-- //@UNDO
-- SQL to undo the change goes here.
ALTER TABLE cobranca.cobranca_doc_item_hist ADD CONSTRAINT fk1_cobranca_documento_item_historico FOREIGN KEY (dotp_id)
      REFERENCES cobranca.documento_tipo (dotp_id) MATCH SIMPLE
      ON UPDATE RESTRICT ON DELETE RESTRICT;
ALTER TABLE cobranca.cobranca_doc_item_hist ADD CONSTRAINT fk2_cobranca_documento_item_historico FOREIGN KEY (cnta_id)
      REFERENCES faturamento.conta_geral (cnta_id) MATCH SIMPLE
      ON UPDATE RESTRICT ON DELETE RESTRICT;
ALTER TABLE cobranca.cobranca_doc_item_hist ADD CONSTRAINT fk3_cobranca_documento_item_historico FOREIGN KEY (dbac_id)
      REFERENCES faturamento.debito_a_cobrar_geral (dbac_id) MATCH SIMPLE
      ON UPDATE RESTRICT ON DELETE RESTRICT;
ALTER TABLE cobranca.cobranca_doc_item_hist ADD CONSTRAINT fk4_cobranca_documento_item_historico FOREIGN KEY (gpag_id)
      REFERENCES faturamento.guia_pagamento_geral (gpag_id) MATCH SIMPLE
      ON UPDATE RESTRICT ON DELETE RESTRICT;
ALTER TABLE cobranca.cobranca_doc_item_hist ADD CONSTRAINT fk5_cobranca_documento_item_historico FOREIGN KEY (cbdo_id)
      REFERENCES cobranca.cobranca_documento (cbdo_id) MATCH SIMPLE
      ON UPDATE RESTRICT ON DELETE RESTRICT;
ALTER TABLE cobranca.cobranca_doc_item_hist ADD CONSTRAINT fk6_cobranca_documento_item_historico FOREIGN KEY (cdst_id)
      REFERENCES cobranca.cobranca_debito_situacao (cdst_id) MATCH SIMPLE
      ON UPDATE RESTRICT ON DELETE RESTRICT;
ALTER TABLE cobranca.cobranca_doc_item_hist ADD CONSTRAINT fk7_cobranca_documento_item_historico FOREIGN KEY (crar_id)
      REFERENCES faturamento.credito_a_realizar_geral (crar_id) MATCH SIMPLE
      ON UPDATE RESTRICT ON DELETE RESTRICT;

