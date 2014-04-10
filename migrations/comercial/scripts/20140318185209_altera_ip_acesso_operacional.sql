-- // altera_ip_acesso_operacional
-- Migration SQL that makes the change goes here.
update cadastro.sistema_parametros
set parm_ipservidormodulooperacional = '200.178.173.135:8084'


-- //@UNDO
-- SQL to undo the change goes here.
update cadastro.sistema_parametros
set parm_ipservidormodulooperacional = '10.20.100.24:8084'
