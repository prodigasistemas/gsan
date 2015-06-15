<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>
	<%@ include file="/jsp/util/titulo.jsp"%>
	<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
	<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
	<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
	<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
	<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js"></script>
	<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
	
	<html:javascript staticJavascript="false"  formName="GerarRelatorioResumoCreditosAvisosBancariosActionForm" />
	<script>

		function gerar(){
		  	var form = document.forms[0];
		  	form.submit();
		}
	
	</script>
</head>

<body leftmargin="5" topmargin="5">
<html:form action="/gerarRelatorioResumoCreditosAvisosBancariosAction.do"
	name="GerarRelatorioResumoCreditosAvisosBancariosActionForm"
	type="gcom.gui.relatorio.arrecadacao.GerarRelatorioResumoCreditosAvisosBancariosActionForm"
	method="post">

	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>

	<table width="770" border="0" cellpadding="0" cellspacing="5">
		<tr>
			<td width="115" valign="top" class="leftcoltext">
				<div align="center">
					<p align="left">&nbsp;</p> <p align="left">&nbsp;</p> <p align="left">&nbsp;</p>
					<%@ include file="/jsp/util/informacoes_usuario.jsp"%>
					<p align="left">&nbsp;</p> <p align="left">&nbsp;</p> <p align="left">&nbsp;</p>
					<p align="left">&nbsp;</p> <p align="left">&nbsp;</p> <p align="left">&nbsp;</p>
					<p align="left">&nbsp;</p> <p align="left">&nbsp;</p> <p align="left">&nbsp;</p>
					<p align="left">&nbsp;</p> <p align="left">&nbsp;</p> <p align="left">&nbsp;</p>
					<%@ include file="/jsp/util/mensagens.jsp"%>
					<p align="left">&nbsp;</p> <p align="left">&nbsp;</p> <p align="left">&nbsp;</p>
					<p align="left">&nbsp;</p> <p align="left">&nbsp;</p> <p align="left">&nbsp;</p>
					<p align="left">&nbsp;</p>
				</div>
			</td>
			
			<td valign="top" class="centercoltext">
				<table height="100%">
					<tr>
						<td></td>
					</tr>
				</table>
				<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
					<tr>
						<td width="11"><img src="<bean:message key="caminho.imagens"/>parahead_left.gif" border="0" /></td>
						<td class="parabg">Relat&oacute;rio de Resumo de Cr&eacute;ditos dos Avisos Banc&aacute;rios</td>
						<td width="11" valign="top"><img src="<bean:message key="caminho.imagens"/>parahead_right.gif" border="0" /></td>
					</tr>
				</table>
			
				<p>&nbsp;</p>

				<table width="100%" border="0">
					<tr>
						<td colspan="3">Informe a data desejada para gerar o relatório:</td>
					</tr>
					
					<tr>
						<tr><td> <p>&nbsp;</p></td><td colspan="2"></td></tr>
						<td width="30%"><strong>Data do Cr&eacute;dito Previsto:<font color="#FF0000">*</font></strong></td>
						<td width="70%">
							<html:text onkeyup="mascaraData(this, event);somente_numero(this);"
								property="dataConsulta" 
								size="10" 
								maxlength="10"
								onkeypress="javascript:return isCampoNumerico(event);" /> 
							<a href="javascript:abrirCalendario('GerarRelatorioResumoCreditosAvisosBancariosActionForm', 'dataConsulta')">
								<img border="0" 
									width="16" 
									height="15"
									src="<bean:message key="caminho.imagens"/>calendario.gif"
									width="20" 
									border="0" 
									align="absmiddle" 
									alt="Exibir Calendário" /></a> (dd/mm/aaaa)
						</td>
					</tr>
					
					<tr><td> <p>&nbsp;</p></td><td colspan="2"></td></tr>
					
					<tr>
						<td><div align="left"></div></td>
						<td>
							<div align="right"><input type="Button" value="Gerar" onclick="javascript:gerar();" class="bottonRightCol" /></div>
						</td>
					</tr>
				</table>  
			
			</td>
		</tr>
	</table>
	
	<%@ include file="/jsp/util/rodape.jsp"%>

</html:form>
</body>
</html:html>
