<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ page import="gcom.util.ConstantesSistema"%>

<html:html>
<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>

<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false"
	formName="AdicionarDebitoCobradoContaActionForm" />

<SCRIPT LANGUAGE="JavaScript">
<!--

function validarForm(form){
	
	if (validateAdicionarDebitoCobradoContaActionForm(form)){
	
		var ANO_LIMITE = document.getElementById("ANO_LIMITE").value;
		
		if (form.mesAnoDebito.value.length > 0 &&
				((form.mesAnoDebito.value.substring(3, 7) * 1) < (ANO_LIMITE * 1))){
			alert("Ano do débito não deve ser menor que " + ANO_LIMITE);
			form.mesAnoDebito.focus();
			return false;
		}
		
		if (form.mesAnoCobranca.value.length > 0 && 
				((form.mesAnoCobranca.value.substring(3, 7) * 1) < (ANO_LIMITE * 1))){
			alert("Ano da cobrança não deve ser menor que " + ANO_LIMITE);
			form.mesAnoCobranca.focus();
			return false;
		}
		
		if (testarCampoValorZero(form.valorDebito, "Valor do Débito")){
			submeterFormPadrao(form);
		}
	}
}

function obterValorDebito() {
	var form = document.forms[0];
	form.action = 'exibirAdicionarDebitoCobradoContaAction.do?imovel=' + form.imovelID.value;
	submeterFormPadrao(form);
}

//-->
</SCRIPT>

</head>

<logic:present name="reloadPage">

	<logic:equal name="reloadPageURL" value="INSERIRCONTA">
		<body leftmargin="0" topmargin="0"
			onload="window.focus(); resizePageSemLink(480, 280); chamarSubmitComUrl('exibirInserirContaAction.do?reloadPage=1'); self.close();">
	</logic:equal>

	<logic:notEqual name="reloadPageURL" value="INSERIRCONTA">
		<body leftmargin="0" topmargin="0"
			onload="window.focus(); resizePageSemLink(480, 280); chamarSubmitComUrl('exibirRetificarContaAction.do?reloadPage=1'); self.close();">
	</logic:notEqual>

</logic:present>

<logic:notPresent name="reloadPage">
	<body leftmargin="0" topmargin="0"
		onload="window.focus(); resizePageSemLink(480, 280);">
</logic:notPresent>


<INPUT TYPE="hidden" ID="ANO_LIMITE" value="${requestScope.anoLimite}" />

<html:form action="/adicionarDebitoCobradoContaAction" method="post">

	<html:hidden property="imovelID" />

	<table width="452" border="0" cellpadding="0" cellspacing="5">
		<tr>
			<td width="442" valign="top" class="centercoltext">
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
						editor="Webstyle4"
						moduleid="Album Photos (Project)\toptab_page2_parahead_left.xws"
						border="0" /></td>
					<td class="parabg">Adicionar Débito na Conta</td>
					<td width="11"><img
						src="<bean:message key="caminho.imagens"/>parahead_right.gif"
						editor="Webstyle4"
						moduleid="Album Photos (Project)\toptab_page2_parahead_right.xws"
						border="0" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>

			<table width="100%" border="0">
				<tr>
					<td colspan="4">Preencha os campos abaixo para inserir um débito na
					conta:</td>
					<logic:present scope="application" name="urlHelp">
						<td align="right"><a href="javascript:abrirPopupHelp('${applicationScope.urlHelp}faturamentoContaDebitoAdicionar', 500, 700);"><span style="font-weight: bold"><font color="#3165CE">Ajuda</font></span></a></td>									
					</logic:present>
					<logic:notPresent scope="application" name="urlHelp">
						<td align="right"><span style="font-weight: bold"><font color=#696969><u>Ajuda</u></font></span></td>									
					</logic:notPresent>
				</tr>
			</table>
			<table width="100%" border="0">
				<tr>
					<td width="100" height="20"><strong>Tipo de Débito:<font
						color="#FF0000">*</font></strong></td>
					<td colspan="3"><html:select property="debitoTipoID" onchange="obterValorDebito();">
						<html:option
							value="<%= ""+ConstantesSistema.NUMERO_NAO_INFORMADO %>">&nbsp;</html:option>
						<logic:present name="colecaoAdicionarDebitoTipo">
							<html:options collection="colecaoAdicionarDebitoTipo"
								labelProperty="descricao" property="id" />
						</logic:present>
					</html:select></td>
				</tr>
				<tr>
					<td><strong>Mês e Ano do Débito:</strong></td>
					<td colspan="3">
					<html:text property="mesAnoDebito" size="8"
						maxlength="7" tabindex="2" onkeyup="mascaraAnoMes(this, event);" 
						 onkeypress="javascript:return isCampoNumerico(event);" />
					&nbsp;mm/aaaa</td>
				</tr>
				<tr>
					<td><strong>Mês e Ano da Cobrança:</strong></td>
					<td colspan="3">
					<html:text property="mesAnoCobranca" size="8"
						maxlength="7" tabindex="3" onkeyup="mascaraAnoMes(this, event);" 
						 onkeypress="javascript:return isCampoNumerico(event);"/>
					&nbsp;mm/aaaa</td>
				</tr>

				<tr>
					<td><strong>Valor do Débito:<font color="#FF0000">*</font></strong></td>
					<td colspan="3"><logic:equal
						name="alterarValorSugeridoEmTipoDebito" value="true">
						<html:text property="valorDebito" size="15" tabindex="4"
							style="text-align: right;"
							onkeyup="formataValorMonetario(this, 11)" 
							 onkeypress="javascript:return isCampoNumerico(event);"/>
					</logic:equal> <logic:notEqual
						name="alterarValorSugeridoEmTipoDebito" value="true">
						<html:text property="valorDebito" size="15" tabindex="4"
							readonly="true"
							style="text-align:right;background-color:#EFEFEF; border:0; color: #000000" />
					</logic:notEqual></td>
				</tr>
				<tr>
					<td height="30" colspan="4">
					<div align="right"><input type="button"
						onclick="validarForm(document.forms[0]);" class="bottonRightCol"
						value="Inserir" style="width: 70px;">&nbsp; <input type="button"
						onclick="window.close();" class="bottonRightCol" value="Fechar"
						style="width: 70px;"></div>
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



