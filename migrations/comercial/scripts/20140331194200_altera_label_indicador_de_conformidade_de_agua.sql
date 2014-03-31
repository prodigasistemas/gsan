-- // altera label indicador de conformidade de agua
-- Migration SQL that makes the change goes here.
update operacao.indicador
set indc_nome = 'Índice de Conformidade da Qualidade ÁGUA',
indc_form = 'Nº Amostras AG em Conformidade / TT  Amostras  AG Analisadas *100'
where indc_id = 206;


-- //@UNDO
-- SQL to undo the change goes here.
update operacao.indicador
set indc_nome = 'Índice de Conformidade da Qualidade ÁGUA RMB',
indc_form = 'Nº Amostras AG em Conformidade RMB/ TT  Amostras  AG Analisadas  RMB*100'
where indc_id = 206;


