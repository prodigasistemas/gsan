-- // altera url do menu operacional
-- Migration SQL that makes the change goes here.

update seguranca.funcionalidade
set fncd_dscaminhourl = 'acessarOperacional'
where fncd_dsfuncionalidade = 'Operacional';

-- //@UNDO
-- SQL to undo the change goes here.

update seguranca.funcionalidade
set fncd_dscaminhourl = '/GoperaWeb/?usuario='
where fncd_dsfuncionalidade = 'Operacional';

