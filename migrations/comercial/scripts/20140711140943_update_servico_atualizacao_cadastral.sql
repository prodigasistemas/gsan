-- // update servico atualizacao cadastral
-- Migration SQL that makes the change goes here.
  UPDATE atendimentopublico.servico_tipo
  SET svtp_dsservicotipo='ATUALIZACAO CADASTRAL GSAN', svtp_dsabreviado = '', svtp_vlservico = 0.00, svtp_icatualizacomercial = 2, svtp_icfiscalizacaosituacao = 2
  WHERE svtp_id = 1185;
  
  UPDATE atendimentopublico.ordem_servico SET svtp_id = 13 WHERE orse_id = 1816314;


-- //@UNDO
-- SQL to undo the change goes here.


