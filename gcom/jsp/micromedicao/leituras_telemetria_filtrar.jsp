<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<%@page import="gcom.util.ConstantesSistema"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>

<head>

<%@ include file="/jsp/util/titulo.jsp"%>

<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
	
<html:javascript staticJavascript="false" formName="FiltrarLeiturasTemeletriaForm" />
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript">

function validarForm(){
	var form = document.forms[0];
	if(validateFiltrarLeiturasTemeletriaForm(form)){
		submeterFormPadrao(form);
	}
}
	

</script>

</head>

<body leftmargin="5" topmargin="5">

<html:form action="/filtrarLeiturasTelemetriaAction"
	name="FiltrarLeiturasTelemetriaForm"
	type="gcom.gui.micromedicao.FiltrarLeiturasTelemetriaForm"
	method="post">

	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>

		<table width="770" border="0" cellspacing="5" cellpadding="0">
			<tr>
				<td width="115" valign="top" class="leftcoltext">
				<div align="center">
	
				<p align="left">&nbsp;</p>
				<p align="left">&nbsp;</p>
				<p align="left">&nbsp;</p>
	
				<%@ include file="/jsp/util/informacoes_usuario.jsp"%>
	
				<p align="left">&nbsp;</p>
				<p align="left">&nbsp;</p>
				<p align="left">&nbsp;</p>
				<p align="left">&nbsp;</p>
				<p align="left">&nbsp;</p>
				<p align="left">&nbsp;</p>
				<p align="left">&nbsp;</p>
				<p align="left">&nbsp;</p>
				<p align="left">&nbsp;</p>
				<p align="left">&nbsp;</p>
	
				<%@ include file="/jsp/util/mensagens.jsp"%>
	
				<p align="left">&nbsp;</p>
				<p align="left">&nbsp;</p>
				<p align="left">&nbsp;</p>
				<p align="left">&nbsp;</p>
				<p align="left">&nbsp;</p>
				<p align="left">&nbsp;</p>
				<p align="left">&nbsp;</p>
				</div>
				</td>
	
				<td valign="top" class="centercoltext">
				<table>
					<tr>
						<td></td>
					</tr>
				</table>
	
			<!--Início Tabela Reference a Páginação da Tela de Processo-->
				<table>
					<tr>
						<td></td>
					</tr>
				</table>
				<table width="100%" border="0" align="center" cellpadding="0"
					cellspacing="0">
					<tr>
						<td width="11"><img border="0" src="imagens/parahead_left.gif" />
						</td>
						<td class="parabg">Consultar Leituras Transmitidas Via Telemetria</td>
						<td width="11" valign="top"><img border="0"
							src="imagens/parahead_right.gif" /></td>
					</tr>
				</table>
			<!--Fim Tabela Reference a Páginação da Tela de Processo-->
				<table width="100%" border="0">
					<tr>
						<td colspan="3">Para selecionar leituras transmitidas via telemetria,
						informe os dados abaixo:</td>
					</tr>					
					<tr>
						<td colspan="3">
						<hr>
						</td>
					</tr>					
					<tr>
						<td><strong>Leituras:<font color="#FF0000">*</font></strong></td>
						<td  colspan="2">
							<html:radio property="situacaoLeitura"
								value="1"  />
							Processadas
							<html:radio property="situacaoLeitura"
								value="2" />
							Não Processadas
							<html:radio property="situacaoLeitura"
								value="3" />
							Ambos
						</td>
					</tr>
					<tr>
						<td colspan="3">
						<hr>
						</td>
					</tr>
					<tr> 
		                  <td><strong>Período do Envio Movimento:</strong></td>
	                  <td colspan="2"> 
	                  	<html:text name="FiltrarLeiturasTelemetriaForm" 
	                  	 	onkeyup="mascaraData(this, event);replicarCampo(document.forms[0].periodoEnvioFinal,this);"
	                  	 	property="periodoEnvioInicial" size="10" maxlength="10"
	                  	 	onkeypress="javascript:return isCampoNumerico(event);"/> 
						<a href="javascript:abrirCalendarioReplicando('FiltrarLeiturasTelemetriaForm', 'periodoEnvioInicial', 'periodoEnvioFinal')">
							<img border="0" src="<bean:message key='caminho.imagens'/>calendario.gif" alt="Exibir Calendário" /></a>
	                   	<strong>a</strong> 
	                    <html:text name="FiltrarLeiturasTelemetriaForm" 
	                    	onkeyup="mascaraData(this, event);" property="periodoEnvioFinal" size="10" maxlength="10" 
	                    	onkeypress="javascript:return isCampoNumerico(event);"/> 
						<a href="javascript:abrirCalendario('FiltrarLeiturasTelemetriaForm', 'periodoEnvioFinal')">
							<img border="0" src="<bean:message key='caminho.imagens'/>calendario.gif" alt="Exibir Calendário" /></a>
						<strong>dd/mm/aaaa</strong></td>
					</tr>	
					<tr>
						<td colspan="3">
						<hr>
						</td>
					</tr>
					<tr> 
		                  <td><strong>Período de Leitura:</strong></td>
	                  <td colspan="2">  
	                  	<html:text name="FiltrarLeiturasTelemetriaForm" 
	                  	 	onkeyup="mascaraData(this, event);replicarCampo(document.forms[0].periodoLeituraFinal,this);"
	                  	    property="periodoLeituraInicial" size="10" maxlength="10"
	                  	 	onkeypress="javascript:return isCampoNumerico(event);"/> 
						<a href="javascript:abrirCalendarioReplicando('FiltrarLeiturasTelemetriaForm', 'periodoLeituraInicial', 'periodoLeituraFinal')">
							<img border="0" src="<bean:message key='caminho.imagens'/>calendario.gif" alt="Exibir Calendário" /></a>
	                   	<strong>a</strong> 
	                    <html:text name="FiltrarLeiturasTelemetriaForm" 
	                    	onkeyup="mascaraData(this, event);" property="periodoLeituraFinal" size="10" maxlength="10" 
	                    	onkeypress="javascript:return isCampoNumerico(event);"/> 
						<a href="javascript:abrirCalendario('FiltrarLeiturasTelemetriaForm', 'periodoLeituraFinal')">
							<img border="0" src="<bean:message key='caminho.imagens'/>calendario.gif" alt="Exibir Calendário" /></a>
						<strong>dd/mm/aaaa</strong></td>
					</tr>
					<tr>
						<td colspan="3">
						<hr>
						</td>
					</tr>
					<tr>
						<td><p>&nbsp;</p></td>
						<td align="right">
							<div align="right"><strong><font color="#FF0000">*</font></strong>
								Campos obrigat&oacute;rios</div>
						</td>
					</tr>
							
					<tr>
						<td colspan="2"><input name="Button" type="button"
							class="bottonRightCol" value="Limpar" align="left"
							onclick="window.location.href='<html:rewrite page="/exibirFiltrarLeiturasTelemetriaAction.do?menu=sim"/>'"> 
							<input name="Button"
							type="button" class="bottonRightCol" value="Cancelar" align="left"
							onclick="window.location.href='/gsan/telaPrincipal.do'">
						</td>
						<td width="53" height="24" align="right"><input type="button"
							name="Button2" class="bottonRightCol" value="Filtrar"
							onClick="javascript:validarForm();" /></td>
					</tr>
				</table>
			<p>&nbsp;</p>
			<p>&nbsp;</p>
			<p>&nbsp;</p>
			<p>&nbsp;</p>
			<p>&nbsp;</p>
			<p>&nbsp;</p>
			<p>&nbsp;</p>
			<p>&nbsp;</p>

		</tr>
	</table>
	<%@ include file="/jsp/util/rodape.jsp"%>
</html:form>
</body>
</html:html>