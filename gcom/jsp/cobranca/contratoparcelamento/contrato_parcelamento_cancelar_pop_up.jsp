<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
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
	src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>

<html:javascript staticJavascript="false"
	formName="CancelarContratoParcelamentoClienteActionForm" />

<script language="JavaScript">
	function validar() {

		var form = document.forms[0];

		if (validateCancelarContratoParcelamentoClienteActionForm(form)) {

			form.submit();
		}
	}
</script>

</head>


<logic:present name="fecharPopupCancelarContrato" scope="session">
	<body leftmargin="0" topmargin="0"
		onload="chamarReload('manterContratoParcelamentoClienteAction.do?idClienteContrato=${idContrato}');window.close();">
</logic:present>
<logic:notPresent name="fecharPopupCancelarContrato" scope="session">
	<body leftmargin="0" topmargin="0"
		onload="window.focus();resizePageSemLink(720,380);">
</logic:notPresent>

<html:form action="/cancelarContratoParcelamentoClienteAction.do"
	name="CancelarContratoParcelamentoClienteActionForm"
	type="gcom.gui.cobranca.contratoparcelamento.CancelarContratoParcelamentoClienteActionForm"
	method="post">

	<table width="702" border="0" cellspacing="5" cellpadding="0">
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
					<td class="parabg">Cancelar Contrato de Parcelamento por
					Cliente</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>
					<td colspan="2"></td>
				</tr>
			</table>
			<table width="100%" border="0">
				<tr>
					<td width="24%"><strong>Número do Contrato:<font
						color="#FF0000">*</font></strong></td>

					<td><html:text maxlength="10" property="numeroContrato"
						size="15" readonly="true"
						style="background-color:#EFEFEF; border:0" tabindex="3" /></td>
				</tr>

					<tr>
						<td>
							<strong>Cliente:<font color="#FF0000">*</font></strong>
						</td>
						<td width="86%">
							<html:text maxlength="10" property="idCliente" size="9" readonly="true" style="background-color: rgb(239, 239, 239); border: 0pt none; float: left;" />
							&nbsp;&nbsp;
							<html:text maxlength="30" property="nomeCliente" size="45" readonly="true" style="background-color: rgb(239, 239, 239); border: 0pt none; float: left; margin-left: 8px;" />
							&nbsp;&nbsp;
							<html:text maxlength="14" property="cnpjCliente" size="15" readonly="true" style="background-color: rgb(239, 239, 239); border: 0pt none; float: left; position: absolute; margin-left: -9px;" />
							
						</td>
					</tr>
				<tr>
					<td><strong>Data Implantação Contrato:<font
						color="#FF0000">*</font></strong></td>
					<td><html:text property="dataImplantacaoContrato" size="12"
						tabindex="9" onkeyup="mascaraData(this, event)" maxlength="10"
						readonly="true" style="background-color:#EFEFEF; border:0" /> <img
						border="0"
						src="<bean:message key='caminho.imagens'/>calendario.gif"
						width="16" height="15" border="0" alt="Exibir Calendário"
						tabindex="4" /></td>
				</tr>

				<tr>
					<td colspan="4">
					<hr>
					</td>
				</tr>

				<tr>
					<td><strong>Data Cancelamento:<font color="#FF0000">*</font></strong></td>
					<td><html:text property="dataCancelamento" size="11"
						tabindex="9" onkeyup="mascaraData(this, event)" maxlength="10" />
					<a
						href="javascript:abrirCalendario('CancelarContratoParcelamentoClienteActionForm', 'dataCancelamento');">
					<img border="0"
						src="<bean:message key='caminho.imagens'/>calendario.gif"
						width="16" height="15" border="0" alt="Exibir Calendário"
						tabindex="4" /></a></td>
				</tr>


				<tr>
					<td><strong>Motivo Cancelamento:<font color="#FF0000">*</font></strong></td>
					<td><html:select property="idMotivoCancelamento">
						<html:option value="-1">&nbsp;</html:option>
						<html:options collection="colecaoParcelamentoMotivoDesfazer"
							labelProperty="descricaoParcelamentoMotivoDesfazer" property="id" />
					</html:select> <font size="1">&nbsp; </font></td>
				</tr>


				<tr>
					<td colspan="4">
					<hr>
					</td>
				</tr>
				<tr>
					<td height="24" colspan="3" width="80%"><input type="button"
						onclick="window.close()" class="bottonRightCol" value="Fechar"
						style="width: 70px;"></td>
					<td align="right"><input name="Cancelar" type="button"
						class="bottonRightCol" value="Cancelar Contrato" onclick="validar();"></td>
					<td>&nbsp;</td>
				</tr>
			</table>
			<p>&nbsp;</p>
			</td>
		</tr>
	</table>

</html:form>

</body>

</html:html>
