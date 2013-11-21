<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<%@ page import="gcom.faturamento.FaturamentoSituacaoTipo"%>

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
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>

</head>

<body leftmargin="5" topmargin="5" onload="resizePageSemLink(750, 570);">


<html:form
	action="/exibirConsultarSituacaoEspecialCobrancaPopupAction"
	type="gcom.gui.faturamento.ConsultarSituacaoEspecialCobrancaPopupActionForm"
	name="ConsultarSituacaoEspecialCobrancaPopupActionForm" method="post">


	<table width="700" border="0" cellspacing="5" cellpadding="0">
		<tr>
			<td width="100%" valign="top" class="centercoltext">

			<table height="100%">
				<tr>
					<td></td>
				</tr>
			</table>

			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_left.gif" /></td>
					<td class="parabg">Consultar Situação Especial de Cobrança</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			
			<p>&nbsp;</p>
			
			<p>&nbsp;</p>
			
			<table width="100%" border="0">
			<tr>
				<td width="35%"><strong>Tipo da Sit. Especial de Cob.:</strong></td>
				<td colspan="3">
					<html:text property="tipo" size="60" maxlength="50" disabled="true" style="background-color:#EFEFEF; border:0; font-color: #000000"/>
				</td>
			</tr>
			<tr>
				<td width="35%"><strong>Motivo da Sit. Especial de Cob.:</strong></td>
				<td colspan="3">
					<html:text property="motivo" size="60" maxlength="50" disabled="true" style="background-color:#EFEFEF; border:0; font-color: #000000"/>
				</td>
			</tr>
			<tr>
				<td width="35%"><strong>Refer&ecirc;ncia do Cob. Inicial:</strong></td>
				<td colspan="3"><html:text property="mesAnoReferenciaCobrancaInicial"
					size="7" maxlength="7" disabled="true" style="background-color:#EFEFEF; border:0; font-color: #000000"/>
				</td>
			</tr>
			<tr>
				<td width="35%"><strong>Refer&ecirc;ncia do Cob. Final:</strong></td>
				<td colspan="3"><html:text property="mesAnoReferenciaCobrancaFinal" size="7"
					maxlength="7" disabled="true" style="background-color:#EFEFEF; border:0; font-color: #000000"/>
				</td>
			</tr>
			<tr>
				<td width="35%"><strong>Refer&ecirc;ncia Retirada:</strong></td>
				<td colspan="3"><html:text property="mesAnoReferenciaRetirada" size="7"
					maxlength="7" disabled="true" style="background-color:#EFEFEF; border:0; font-color: #000000"/>
				</td>
			</tr>
			<tr>
      			<td width="35%"><strong>Observação Informada:</strong></td>
        		<td colspan="3">
					<html:textarea property="observacaoInforma" cols="40" rows="4" disabled="true" style="background-color:#EFEFEF; border:0; font-color: #000000"/><br>
					<strong><span id="utilizado">0</span>/<span id="limite">100</span></strong>
				</td>
      		</tr>
			<tr>
      			<td width="35%"><strong>Observação Retirada:</strong></td>
        		<td colspan="3">
					<html:textarea property="observacaoRetira" cols="40" rows="4" disabled="true" style="background-color:#EFEFEF; border:0; font-color: #000000"/><br>
					<strong><span id="utilizado">0</span>/<span id="limite">100</span></strong>
				</td>
      		</tr>
			</table>
			
			<table width="100%" border="0">
			<tr>
				<td align="left">
					
				</td>
			</tr>
			</table>

			<p>&nbsp;</p>
			
			</td>
		</tr>


	</table>

</html:form>

</body>
</html:html>
