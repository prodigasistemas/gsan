-- // insert cliente tipo nao informado pessoa juridica
-- Migration SQL that makes the change goes here.

INSERT INTO cadastro.cliente_tipo(cltp_id, cltp_dsclientetipo, cltp_icpessoafisicajuridica, cltp_icuso, cltp_tmultimaalteracao, epod_id)
VALUES (nextval('cadastro.seq_cliente_tipo'), 'NAO INFORMADO', 2, 1, now(), 1);

-- //@UNDO
-- SQL to undo the change goes here.

DELETE FROM cadastro.cliente_tipo WHERE cltp_dsclientetipo = 'NAO INFORMADO' AND cltp_icpessoafisicajuridica = 2;
