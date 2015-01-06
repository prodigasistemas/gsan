-- // insere sistema parametros
-- Migration SQL that makes the change goes here.

insert into cadastro.sistema_parametros (parm_id
, parm_dsmensagemsistema
, parm_amreferenciafaturamento
, parm_amreferenciaarrecadacao
, parm_nmestado
, parm_nmempresa
, parm_nmabreviadoempresa
, parm_nnconsumograndeusuario
, parm_vlminimoemissaoconta
, parm_qteconomiasgrandeusuario
, parm_nndiasvenctocobranca
, parm_nnmaximodiasaltdadosos
, parm_idultimoramanual
, parm_dstitulopagina
, parm_ipservidorsmtp
, parm_dsemailresponsavel
, parm_ipservidormodulogerencial
, parm_nmcontrolador
, parm_nnmaximofavorito
) 
values (1
, 'BEM VINDOS AO GSAN, SISTEMA COMERCIAL DA COSANPA.'
, 201410
, 201411
, 'PARA'
, 'COMPANHIA DE SANEAMENTO DO PARA'
, 'COSANPA'
, 200
, 5.00
, 20
, 20
, 30
, 4214
, 'GSAN - COSANPA'
, '127.0.0.1'
, 'johndoe@gmail.com'
, '127.0.0.1:8080'
, 'COSANPA'
, 10
);

-- //@UNDO
-- SQL to undo the change goes here.

delete from cadastro.sistema_parametros;
