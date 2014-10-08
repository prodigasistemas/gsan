-- // Add coluna classificacao recupracao credito tabela pagamento
-- Migration SQL that makes the change goes here.

alter table arrecadacao.pagamento add column pgmt_icclassrecupcredito smallint DEFAULT 2;

-- //@UNDO
-- SQL to undo the change goes here.

alter table arrecadacao.pagamento drop column pgmt_icclassrecupcredito;


