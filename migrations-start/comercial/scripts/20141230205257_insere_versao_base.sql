-- // insere versao base
-- Migration SQL that makes the change goes here.

insert into admindb.db_versao_base (dbvb_id
,  dbvb_nmempresa
, dbvb_nmdatabase 
, dbvb_dtversaobase
, dbvb_nmlogindba)
values
(1
, 'EMPRESA'
, 'comercial'
, '2014-12-30'
, 'admin')

-- //@UNDO
-- SQL to undo the change goes here.


delete from admindb.db_versao_base;