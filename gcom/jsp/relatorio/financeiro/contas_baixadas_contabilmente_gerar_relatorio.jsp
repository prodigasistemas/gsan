<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>

<%@ page import="gcom.util.ConstantesSistema"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="GerarRelatorioContasBaixadasContabilmenteActionForm" />
<script>

function replicaDados(campoOrigem, campoDestino){
	campoDestino.value = campoOrigem.value;
}

function validarForm(){
	var form = document.forms[0];		
	if(validateGerarRelatorioContasBaixadasContabilmenteActionForm(form)){
		form.action = 'gerarRelatorioContasBaixadasContabilmenteAction.do';
		submeterFormPadrao(form);
	}
}



</script>


</head>

<body leftmargin="5" topmargin="5">
<html:form action="/gerarRelatorioContasBaixadasContabilmenteAction.do"
	name="GerarRelatorioContasBaixadasContabilmenteActionForm"
	type="gcom.gui.relatorio.financeiro.GerarRelatorioContasBaixadasContabilmenteActionForm"
	method="post">

	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>

	<table width="770" border="0" cellpadding="0" cellspacing="5">
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
			<table height="100%">
				<tr>
					<td></td>
				</tr>
			</table>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img
						src="<bean:message key="caminho.imagens"/>parahead_left.gif"
						border="0" /></td>
					<td class="parabg">Relatório das Contas Baixadas Contabilmente</td>
					<td width="11" valign="top"><img
						src="<bean:message key="caminho.imagens"/>parahead_right.gif"
						border="0" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>
					<td colspan="3">Para gerar o relatório das contas baixadas contabilmente, informe os dados abaixo:</td>
				</tr>
								
				<tr>
					<td><strong>Tipo de Relatório:<font	color="#FF0000">*</font></strong></td>
					<td align="left" width="15%">
						<html:radio property="tipo" value="<%=""+ConstantesSistema.ANALITICO%>" 
						onclick = "tipoFormato[0].checked=true; tipoFormato[1].disabled=true; tipoFormato[0].disabled=true;"/> <strong>Analítico </strong>
					</td>
					<td align="left">
						<html:radio property="tipo" value="<%=""+ConstantesSistema.SINTETICO%>" 
						onclick = "tipoFormato[1].disabled=false; tipoFormato[0].disabled=false; tipoFormato[0].checked=false;"/> <strong>Sintético </strong>
					</td>
				</tr>
								
				<tr>
					<td><strong>Periodicidade:<font	color="#FF0000">*</font></strong></td>
					<td align="left" width="15%">
						<html:radio property="periodicidade" value="<%=""+ConstantesSistema.MENSAL%>"
						onclick = "referenciaInicial.disabled=false;"/> <strong>Mensal </strong>
					</td>
					<td align="left">
						<html:radio property="periodicidade" value="<%=""+ConstantesSistema.ACUMULADO%>"
						onclick = "referenciaInicial.disabled=true;"/> <strong>Acumulado </strong>
					</td>
				</tr>
				
				<tr>
						<td><strong>Tipo:<font	color="#FF0000">*</font></strong></td>
						<td align="left" width="15%">
							<html:radio  property="tipoFormato" value="TXT"/> <strong>TXT </strong> 
						</td>
						<td align="left">
							<html:radio property="tipoFormato" value="PDF"/> <strong>Relatório </strong>
						</td>
					
				</tr>
								
				<tr>
					<td width="30%"><strong>Referência das Faturas:</strong></td>
					<td colspan="2">
						<strong>
						<html:text maxlength="7" property="referenciaInicial" size="7"
							onkeyup="mascaraAnoMes(this, event); replicaDados(document.forms[0].referenciaInicial, document.forms[0].referenciaFinal);" /> 
						<strong> a</strong>
						<html:text maxlength="7" property="referenciaFinal" size="7" onkeyup="mascaraAnoMes(this, event);" />
						</strong> (mm/aaaa)
					</td>
				</tr>
	
				<tr>
					<td>&nbsp;</td>
					<td colspan="2" align="left"><font color="#FF0000">*</font> Campo Obrigat&oacute;rio</td>
				</tr>
				<tr>
					<td>
					<p>&nbsp;</p>
					</td>
					<td colspan="2"></td>
				</tr>
			</table>
			<table>
				<tr>
					<td width="100%">
					<table width="30%" border="0" align="right" cellpadding="0"
						cellspacing="2">
						<tr>
							<td width="61">&nbsp;</td>
							<td width="416">&nbsp;</td>
							<td width="12"></td>
							<td width="61">		
							
					
								<input type="button" class="bottonRightCol"
								value="Gerar Relat&oacute;rio"
								onclick="validarForm()"
								>
								
							
							<%--
							onclick="toggleBox('demodiv',1);"
							
							
							<a
									href="javascript:toggleBox('demodiv',1);/*javascript:abrirPopupRelatorio('/gsan/gerarRelatorioResumoArrecadacaoAction.do');*/"
									style="text-decoration:none">
									
									<input type="button" class="bottonRightCol"
									value="Gerar Relat&oacute;rio"
									>
									
								 </a>
							 --%>
												
								
							</td>
							<td width="82">&nbsp;</td>
						</tr>
					</table>
					</td>
				</tr>
			</table>
			<p>&nbsp;</p>
			</td>
		</tr>
	</table>
	<jsp:include page="/jsp/relatorio/escolher_tipo_relatorio.jsp?relatorio=gerarRelatorioContasBaixadasContabilmenteAction.do"/> 
	<%@ include file="/jsp/util/rodape.jsp"%>
</html:form>
</body>
</html:html>
