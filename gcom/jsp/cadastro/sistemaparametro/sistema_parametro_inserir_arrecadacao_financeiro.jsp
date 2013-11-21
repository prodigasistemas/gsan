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

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="InserirParametrosSistemaActionForm"
	dynamicJavascript="false" />

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script>
<!--
var bCancel = false;

function validateInserirParametrosSistemaActionForm(form) {
	if (bCancel)
		return true;
	else
		return  validateRequired(form)
		&& validateInteger(form);
		//validateCaracterEspecial(form) 
		//&& validateRequired(form)
		//&& validateInteger(form);
}
function IntegerValidations () {
	this.aa = new Array("codigoEmpresaFebraban", "Código da Empresa para FEBRABAN deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
	this.ab = new Array("numeroLayOut", "Número do Lau-out da FEBRABAN deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
	this.ac = new Array("indentificadorContaDevolucao", "Identificador da Conta Bancária para Devolução deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
	this.ad = new Array("maximoParcelas", "Máximo de Parcelas para um Financiamento deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
	this.ae = new Array("numeroMaximoParcelaCredito", "Número Máximo para Parcela de Crédito deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
}



/*function caracteresespeciais () {
	this.aa = new Array("nomeEstado", "Nome do Estado possui caracteres especiais.", new Function ("varName", " return this[varName];"));
	this.ab = new Array("nomeEmpresa", "Nome da Empresa possui caracteres especiais.", new Function ("varName", " return this[varName];"));
	this.ac = new Array("abreviaturaEmpresa", "Abreviatura da Empresa possui caracteres especiais.", new Function ("varName", " return this[varName];"));
	this.ad = new Array("cnpj", "CNPJ possui caracteres especiais.", new Function ("varName", " return this[varName];"));
}*/

function required () {
	this.aa = new Array("mesAnoReferenciaArrecadacao", "Informe Mês e Ano de Referência.", new Function ("varName", " return this[varName];"));
}


-->
</script>
</head>

<body leftmargin="5" topmargin="5"
	onload="javascript:setarFoco('${requestScope.nomeCampo}');">

<html:form action="/inserirParametrosSistemaWizardAction" method="post"
	onsubmit="return validateInserirParametrosSistemaActionForm(this);">

	<jsp:include
		page="/jsp/util/wizard/navegacao_abas_wizard_valida_avancar.jsp?numeroPagina=3" />

	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>

	<input type="hidden" name="numeroPagina" value="3" />
	<table width="770" border="0" cellspacing="5" cellpadding="0">
		<tr>
			<td width="150" valign="top" class="leftcoltext">
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
			<td width="655" valign="top" class="centercoltext">
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
					<td class="parabg">Inserir Parâmetros do Sistema</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<table width="100%" border="0" dwcopytype="CopyTableRow">
				<tr>
					<td>Para inserir parâmetros do sistema, informe os dados abaixo:
					<td align="right"><a
						href="javascript: abrirPopup('/gsan/help/help.jsp?mapIDHelpSet=clienteInserirAbaNomeTipo', 500, 700);"><span
						style="font-weight: bold"><font color="#3165CE">Ajuda</font></span></a></td>
				</tr>
			</table>

			<table width="100%" border="0">


				<tr>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td colspan="2" align="center"><strong>Parâmetros para Arrecadação:</strong></td>
				</tr>

				<tr>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td width="40%" align="left"><strong>Mês e Ano de Referência:</strong><font
						color="#FF0000">*</font></td>
					<td width="82%"><html:text property="mesAnoReferenciaArrecadacao"
						size="7" maxlength="7"
						onkeyup="javascript:mascaraAnoMes(this,event);" /><strong>mm/aaaa</strong></td>
				</tr>
				<tr>
					<td width="40%" align="left"><strong>Código da Empresa para
					FEBRABAN:</strong></td>
					<td><html:text maxlength="9" property="codigoEmpresaFebraban"
						size="9" /></td>
				</tr>
				<tr>
					<td width="40%" align="left"><strong>Número do Lay-out da FEBRABAN:</strong></td>
					<td><html:text maxlength="5" property="numeroLayOut" size="5" /></td>
				</tr>
				<tr>
					<td width="40%" align="left"><strong>Identificador da Conta
					Bancária para Devolução:<font color="#FF0000">*</font></strong></td>
					<td><html:select property="indentificadorContaDevolucao">
						<html:option
							value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
						<html:options collection="colecaoContaBancaria" labelProperty="id"
							property="id" />
					</html:select></td>

				</tr>
				<tr>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td colspan="2" align="center"><strong>Parâmetros para o
					Financeiro:</strong></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td width="40%" align="left"><strong> Percentual de Entrada Mínima
					para Financiamento:</strong></td>
					<td width="87%"><html:text property="percentualEntradaMinima"
						size="5" maxlength="5"
						onkeyup="javascript:formataValorMonetario(this, 5);" /> <font
						size="1"> &nbsp; </font></td>
				</tr>
				<tr>
					<td width="40%" align="left"><strong>Máximo de Parcelas para um
					Financiamento:</strong></td>
					<td><html:text maxlength="5" property="maximoParcelas" size="5" /></td>
				</tr>
				<tr>
					<td width="40%" align="left"><strong>Percentual Máximo para
					Abatimento de um Serviço:</strong></td>
					<td><html:text maxlength="5" property="percentualMaximoAbatimento"
						size="5" onkeyup="javascript:formataValorMonetario(this, 5);" /></td>
				</tr>
				<tr>
					<td width="40%" align="left"><strong>Percentual de Taxa de Juros
					para Financiamento:</strong></td>
					<td><html:text maxlength="5" property="percentualTaxaFinanciamento"
						size="5" onkeyup="javascript:formataValorMonetario(this, 5);" /></td>
				</tr>
				<tr>
					<td width="40%" align="left"><strong>Número Máximo para Parcela de
					Crédito:</strong></td>
					<td><html:text maxlength="2" property="numeroMaximoParcelaCredito"
						size="2" /></td>
				</tr>
				<tr>
					<td width="40%" align="left"><strong>Percentual da Média do Índice
					para Cálculo do Parcelamento:</strong></td>
					<td><html:text maxlength="9" property="percentualCalculoIndice"
						size="9" onkeyup="javascript:formataValorMonetario(this, 5);" /></td>
				</tr>
				<tr>
					<td><strong></strong></td>
					<td><strong><font color="#FF0000">*</font></strong>Campo
					obrigat&oacute;rio</td>
				</tr>
				<tr>
					<td colspan="2">
					<div align="right"><jsp:include
						page="/jsp/util/wizard/navegacao_botoes_wizard_valida_avancar.jsp?numeroPagina=3" /></div>
					</td>
				</tr>
			</table>
			<p>&nbsp;</p>
			</td>
		</tr>
	</table>
	<%@ include file="/jsp/util/rodape.jsp"%>
</body>
</html:form>
</html:html>
