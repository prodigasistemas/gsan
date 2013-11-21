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
	this.aa = new Array("menorConsumo", "Menor Consumo para ser Grande Usuário deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
	this.ab = new Array("menorValor", "Menor Valor para Emissão de Contas deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
	this.ac = new Array("qtdeEconomias", "Qtde de Economias para ser Grande Usuário deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
	this.ad = new Array("mesesCalculoMedio", "Meses para Cálculo de Média de Consumo deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
	this.ae = new Array("diasMinimoVencimento", "Dias Mínimo para Calcular Vencimento deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
	this.af = new Array("diasMinimoVencimentoCorreio", "Dias Mínimo para Calcular Vencimento se entrega pelos Correios deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
	this.ag = new Array("numeroMesesValidadeConta", "Número de Meses para validade da Conta deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
	this.ah = new Array("numeroMesesAlteracaoVencimento", "Número de Meses para alteração de um vencimento para outro deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
	this.ai = new Array("consumoMaximo", "Consumo de Energia Máxima para a Tarifa deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
	

}



/*function caracteresespeciais () {
	this.aa = new Array("nomeEstado", "Nome do Estado possui caracteres especiais.", new Function ("varName", " return this[varName];"));
	this.ab = new Array("nomeEmpresa", "Nome da Empresa possui caracteres especiais.", new Function ("varName", " return this[varName];"));
	this.ac = new Array("abreviaturaEmpresa", "Abreviatura da Empresa possui caracteres especiais.", new Function ("varName", " return this[varName];"));
	this.ad = new Array("cnpj", "CNPJ possui caracteres especiais.", new Function ("varName", " return this[varName];"));
}*/

function required () {
	this.aa = new Array("mesAnoReferencia", "Informe Mês e Ano de Referência.", new Function ("varName", " return this[varName];"));
	this.ab = new Array("menorConsumo", "Informe Menor Consumo para ser Grande Usuário.", new Function ("varName", " return this[varName];"));
	this.ac = new Array("menorValor", "Informe Menor Valor para Emissão de Contas.", new Function ("varName", " return this[varName];"));
	this.ad = new Array("qtdeEconomias", "Informe Qtde de Economias para ser Grande Usuário.", new Function ("varName", " return this[varName];"));
	this.ae = new Array("mesesCalculoMedio", "Informe Meses para Cálculo de Média de Consumo.", new Function ("varName", " return this[varName];"));
}

function validarDias(valor){
	var form = document.forms[0];
	//if(form.diasMinimoVencimento.value > 15){
		//form.diasMinimoVencimento.value = '';
		//}
		//if(form.diasMinimoVencimento.value > 15){
		//form.diasMinimoVencimento.value = '';
		//}
	if(valor > 15){
	alert('O número mínimo de dias não pode ser superior a 15');
	limparDiasMinimoVencimento();
	limparDiasMinimoVencimentoCorreio();
			
	}

}

function validarMes(valor){
	if(valor > 12){
	alert('O número mínimo de meses não pode ser superior a 12');
	limparNumeroMesesValidadeConta();
	limparNumeroMesesAlteracaoVencimento();
	}

}

function limparDiasMinimoVencimento(){

	var form = document.forms[0];
		if(form.diasMinimoVencimento.value > 15){
		form.diasMinimoVencimento.value = '';
		form.diasMinimoVencimento.focus();
		}
}

function limparDiasMinimoVencimentoCorreio(){
	var form = document.forms[0];
		if(form.diasMinimoVencimentoCorreio.value > 15){
		form.diasMinimoVencimentoCorreio.value = '';
		form.diasMinimoVencimentoCorreio.focus();
		}
}

function limparNumeroMesesValidadeConta()	{
	var form = document.forms[0];
	
	if(form.numeroMesesValidadeConta.value > 12){
	form.numeroMesesValidadeConta.value = '';
	form.numeroMesesValidadeConta.focus();
	}
}
	
function limparNumeroMesesAlteracaoVencimento(){
	var form = document.forms[0];	
	if(form.numeroMesesAlteracaoVencimento.value > 12){
	form.numeroMesesAlteracaoVencimento.value = '';
	form.numeroMesesAlteracaoVencimento.focus();
	}
}
-->
</script>
</head>

<body leftmargin="5" topmargin="5"
	onload="javascript:setarFoco('${requestScope.nomeCampo}');">

<html:form action="/inserirParametrosSistemaWizardAction" method="post"
	onsubmit="return validateInserirParametrosSistemaActionForm(this);">

	<jsp:include
		page="/jsp/util/wizard/navegacao_abas_wizard_valida_avancar.jsp?numeroPagina=2" />

	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>

	<input type="hidden" name="numeroPagina" value="2" />
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
					<td colspan="2" align="center"><strong>Parâmetros para Faturamento:</strong></td>
				</tr>

				<tr>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td width="40%" align="left"><strong>Mês e Ano de Referência:</strong><font
						color="#FF0000">*</font></td>
					<td width="82%"><html:text property="mesAnoReferencia" size="7"
						maxlength="7" onkeyup="javascript:mascaraAnoMes(this,event);" />
					<strong>mm/aaaa</strong></td>

				</tr>
				<tr>
					<td width="40%" align="left"><strong>Menor Consumo para ser Grande
					Usuário:<font color="#FF0000">*</font></strong></td>
					<td><html:text maxlength="9" property="menorConsumo" size="9" /></td>
				</tr>
				<tr>
					<td width="40%" align="left"><strong>Menor Valor para Emissão de
					Contas:<font color="#FF0000">*</font></strong></td>
					<td><html:text maxlength="13" property="menorValor" size="13"
						onkeyup="javascript:formataValorMonetario(this,13);" /></td>
				</tr>
				<tr>
					<td width="40%" align="left"><strong> Qtde de Economias para ser
					Grande Usuário:<font color="#FF0000">*</font></strong></td>
					<td width="87%"><html:text property="qtdeEconomias" size="9"
						maxlength="9" /> <font size="1"> &nbsp; </font></td>
				</tr>

				<tr>
					<td width="40%" align="left"><strong> Meses para Cálculo de Média
					de Consumo:<font color="#FF0000">*</font></strong></td>
					<td width="87%"><html:text property="mesesCalculoMedio" size="2"
						maxlength="2" /> <font size="1"> &nbsp; </font></td>
				</tr>


				<tr>
					<td width="40%" align="left"><strong>Dias Mínimo para Calcular
					Vencimento:</strong></td>
					<td><html:text maxlength="5" property="diasMinimoVencimento"
						size="2"
						onblur="javascript:validarDias(document.forms[0].diasMinimoVencimento.value);" /></td>
				</tr>

				<tr>
					<td width="40%" align="left"><strong>Dias Mínimo para Calcular
					Vencimento se entrega pelos Correios:</strong></td>
					<td><html:text maxlength="2" property="diasMinimoVencimentoCorreio"
						size="2"
						onblur="javascript:validarDias(document.forms[0].diasMinimoVencimentoCorreio.value);" /></td>
				</tr>

				<tr>
					<td width="40%" align="left"><strong>Número de Meses para validade
					da Conta:</strong></td>
					<td><html:text maxlength="2" property="numeroMesesValidadeConta"
						size="2"
						onblur="validarMes(document.forms[0].numeroMesesValidadeConta.value);" /></td>
				</tr>
				<tr>
					<td width="40%" align="left"><strong>Número de Meses para alteração
					de um vencimento para outro:</strong></td>
					<td><html:text maxlength="2"
						property="numeroMesesAlteracaoVencimento" size="2"
						onblur="validarMes(document.forms[0].numeroMesesAlteracaoVencimento.value);" /></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td colspan="2" align="center"><strong>Parâmetros para Tarifa
					Social:</strong></td>

				</tr>
				<tr>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td width="40%" align="left"><strong>Salário Mínimo:</strong></td>
					<td><html:text maxlength="9" property="salarioMinimo" size="9"
						onkeyup="javascript:formataValorMonetario(this, 9);" /></td>
				</tr>
				<tr>
					<td width="40%" align="left"><strong>Área Máxima do Imóvel para a
					Tarifa:</strong></td>
					<td><html:text maxlength="7" property="areaMaxima" size="7"
						onkeyup="javascript:formataValorMonetario(this, 7);" /></td>
				</tr>
				<tr>
					<td width="40%" align="left"><strong>Consumo de Energia Máxima para
					a Tarifa:</strong></td>
					<td><html:text maxlength="4" property="consumoMaximo" size="4" /></td>
				</tr>
				<tr>
					<td><strong></strong></td>
					<td><strong><font color="#FF0000">*</font></strong>Campo
					obrigat&oacute;rio</td>
				</tr>
				<tr>
					<td colspan="2">
					<div align="right"><jsp:include
						page="/jsp/util/wizard/navegacao_botoes_wizard_valida_avancar.jsp?numeroPagina=2" /></div>
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
