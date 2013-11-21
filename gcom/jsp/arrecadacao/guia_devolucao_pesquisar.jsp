<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@page import="gcom.util.ConstantesSistema" isELIgnored="false"%>

<html:html>
<head>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="PesquisarGuiaDevolucaoActionForm" dynamicJavascript="false" />

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>

<script language="JavaScript">
<!-- Begin
var bCancel = false; 

function validatePesquisarGuiaDevolucaoActionForm(form) {                                                                   
	if (bCancel) 
		return true; 
	else 
		return validateCaracterEspecial(form) 
				&& validateLong(form) 
				&& validateDate(form); 
} 

function DateValidations () { 
	this.aa = new Array("dataEmissaoGuiaInicio", "Data Inicial do Período de Emissão das Guias inválida.", new Function ("varName", "this.datePattern='dd/MM/yyyy';  return this[varName];"));
	this.ab = new Array("dataEmissaoGuiaFim", "Data Final do Período de Emissão das Guias inválida.", new Function ("varName", "this.datePattern='dd/MM/yyyy';  return this[varName];"));
	this.ac = new Array("dataValidadeGuiaInicio", "Data Inicial do Período de Validade das Guias inválida.", new Function ("varName", "this.datePattern='dd/MM/yyyy';  return this[varName];"));
	this.ad = new Array("dataValidadeGuiaFim", "Data Final do Período de Validade das Guias inválida.", new Function ("varName", "this.datePattern='dd/MM/yyyy';  return this[varName];"));
} 

function caracteresespeciais () { 
 	this.aa = new Array("codigoImovel", "Matrícula do Imóvel possui caracteres especiais.", new Function ("varName", " return this[varName];"));
 	this.ab = new Array("codigoCliente", "Código do Cliente possui caracteres especiais.", new Function ("varName", " return this[varName];"));
 	this.ac = new Array("dataEmissaoGuiaInicio", "Data Inicial do Período de Emissão das Guias possui caracteres especiais.", new Function ("varName", " return this[varName];"));
 	this.ad = new Array("dataEmissaoGuiaFim", "Data Final do Período de Emissão das Guias possui caracteres especiais.", new Function ("varName", " return this[varName];"));
 	this.ae = new Array("dataValidadeGuiaInicio", "Data Inicial do Período de Validade das Guias possui caracteres especiais.", new Function ("varName", " return this[varName];"));
 	this.af = new Array("dataValidadeGuiaFim", "Data Final do Período de Validade das Guias possui caracteres especiais.", new Function ("varName", " return this[varName];"));
} 

function IntegerValidations () { 
	this.aa = new Array("codigoImovel", "Matrícula do Imóvel deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
	this.ab = new Array("codigoCliente", "Código do Cliente deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
} 
window.onmousemove = resizePageSemLink(735, 545);

function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
    var form = document.forms[0];
    if (tipoConsulta == 'cliente') {
        form.codigoCliente.value = codigoRegistro;
        form.action = 'exibirPesquisarGuiaDevolucaoAction.do';
        form.submit();
     }
     if(tipoConsulta == 'imovel'){
      form.codigoImovel.value = codigoRegistro;
      form.inscricaoImovel.value = descricaoRegistro;
      form.action = 'exibirPesquisarGuiaDevolucaoAction.do';
      form.submit();
    }
}
function limparForm(tipo){
    var form = document.PesquisarGuiaDevolucaoActionForm;
	if(tipo == 'imovel')
    {
    	form.codigoCliente.disabled = false;
    	var ObjCodigoImovel = returnObject(form,"codigoImovel");
		var ObjInscricaoImovel = returnObject(form,"inscricaoImovel");
		ObjCodigoImovel.value = "";
		ObjInscricaoImovel.value = "";
	}
	if(tipo == 'cliente')
    {
    	form.codigoImovel.disabled = false;
    	var ObjCodigoCliente = returnObject(form,"codigoCliente");
		var ObjNomeCliente = returnObject(form,"nomeCliente");
		ObjCodigoCliente.value = "";
		ObjNomeCliente.value = "";		
	}
}
function validaEnterCliente(tecla, caminhoActionReload, nomeCampo) {
	var form = document.forms[0];
	
	validaEnterComMensagem(tecla, caminhoActionReload, nomeCampo, "Código do Cliente");
	
	if(form.codigoCliente.value.length > 0) {
		form.codigoImovel.disabled = true;
    } else {
		form.codigoImovel.disabled = false;
		form.codigoCliente.value = "";
        form.nomeCliente.value = "";
	}
}
function validaEnterImovel(tecla, caminhoActionReload, nomeCampo) {
	var form = document.PesquisarGuiaDevolucaoActionForm;
	
	validaEnterComMensagem(tecla, caminhoActionReload, nomeCampo, "Matrícula do Imóvel");
	
	if(form.codigoImovel.value.length > 0) {
		form.codigoCliente.disabled = true;
	} else {
		form.codigoCliente.disabled = false;
		form.codigoImovel.value = "";
        form.inscricaoImovel.value = "";
	}
}
function controleImovel(form){
	form.codigoCliente.disabled = true;
}

function controleCliente(form){
	form.codigoImovel.disabled = true;
}
function replicarCampo(fim,inicio) {
	fim.value = inicio.value;
}
function limparFormulario(){
	var form = document.forms[0];

	form.codigoImovel.value="";
    form.codigoCliente.value = "";
    form.dataEmissaoGuiaInicio.value = "";
    form.dataEmissaoGuiaFim.value = "";
    form.dataValidadeGuiaInicio.value = "";
    form.dataValidadeGuiaFim.value = "";
    form.inscricaoImovel.value = "";
    form.nomeCliente.value = "";
    form.situacaoGuia.value = "";
    form.tipoCredito.value = "";
    form.tipoDocumento.value = "";
    form.codigoCliente.disabled = false;
    form.codigoImovel.disabled = false;
	form.codigoImovel.focus();
}
function validarForm(form){
	if (validatePesquisarGuiaDevolucaoActionForm(form))
	{
		if (form.codigoImovel.value == "" && form.codigoCliente.value == "")
		{
			alert("Informe Matrícula do Imóvel ou Código do Cliente.");
		}
		else if (comparaData(form.dataEmissaoGuiaInicio.value, ">", form.dataEmissaoGuiaFim.value ))
		{
			alert ("Data Final do Período de Emissão das Guias é anterior à Data Inicial do Período de Emissão das Guias.");
		}
		else if (comparaData(form.dataValidadeGuiaInicio.value, ">", form.dataValidadeGuiaFim.value ))
		{
			alert ("Data Final do Período de Validade das Guias é anterior à Data Inicial do Período de Validade das Guias.");
		}
		else
		{
			redirecionarSubmit('/gsan/pesquisarGuiaDevolucaoAction.do');
		}
	}
}
function habilitarPesquisaImovel(form) {
	if (form.codigoImovel.disabled == false) {
		redirecionarSubmit('exibirPesquisarImovelAction.do?caminhoRetornoTelaPesquisaImovel=exibirPesquisarGuiaDevolucaoAction', 400, 800);
	}	
}
function habilitarPesquisaCliente(form) {
	if (form.codigoCliente.disabled == false) {
		redirecionarSubmit('exibirPesquisarClienteAction.do?caminhoRetornoTelaPesquisaCliente=exibirPesquisarGuiaDevolucaoAction', 400, 800);
	}	
}	
//-->
</script>
</head>
<body leftmargin="0" topmargin="0" onload="javascript:resizePageSemLink(745, 570);setarFoco('${requestScope.nomeCampo}');">
<html:form action="/exibirPesquisarGuiaDevolucaoAction" method="post"
	type="gcom.gui.arrecadacao.PesquisarGuiaDevolucaoActionForm"
	onsubmit="return validatePesquisarGuiaDevolucaoActionForm(this);">
	<table width="720" border="0" cellspacing="5" cellpadding="0">
		<tr>
			<td width="707" valign="top" class="centercoltext">
			<table height="100%">

				<tr>
					<td></td>
				</tr>
			</table>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img src="imagens/parahead_left.gif"
						editor="Webstyle4"
						moduleid="Album Photos (Project)\toptab_page2_parahead_left.xws"
						border="0" /></td>
					<td class="parabg">Pesquisar Guias de Devolu&ccedil;&atilde;o do
					Im&oacute;vel ou do Cliente</td>
					<td width="11"><img src="imagens/parahead_right.gif"
						editor="Webstyle4"
						moduleid="Album Photos (Project)\toptab_page2_parahead_right.xws"
						border="0" /></td>

				</tr>
			</table>
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>
					<td colspan="4">Preencha os campos para pesquisar guias de
					pagamento do imóvel ou do cliente:</td>
				</tr>
				<tr>
					<td height="10"><strong>Matrícula do Imóvel:</strong></td>
					<td colspan="3"><html:text property="codigoImovel" maxlength="9"
						size="9"
						onkeyup="validaEnterImovel(event, 'exibirPesquisarGuiaDevolucaoAction.do', 'codigoImovel');" />
					<a href="javascript:habilitarPesquisaImovel(document.forms[0]);" >
					<%-- //href="javascript:redirecionarSubmit('exibirPesquisarImovelAction.do?caminhoRetornoTelaPesquisaImovel=exibirPesquisarGuiaDevolucaoAction', 400, 800);" --%>
					<img width="23" height="21"
						src="<bean:message key='caminho.imagens'/>pesquisa.gif" border="0" title="Matrícula do Imóvel"/></a>
					<logic:present name="codigoImovelNaoEncontrado">
						<logic:equal name="codigoImovelNaoEncontrado" value="exception">
							<html:text property="inscricaoImovel" size="40" maxlength="30"
								readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:equal>
						<logic:notEqual name="codigoImovelNaoEncontrado" value="exception">
							<html:text property="inscricaoImovel" size="40" maxlength="30"
								readonly="true"	style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEqual>
					</logic:present> 
					<logic:notPresent name="codigoImovelNaoEncontrado">
						<logic:empty name="PesquisarGuiaDevolucaoActionForm" property="codigoImovel">
							<html:text property="inscricaoImovel" value="" size="40" maxlength="30" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:empty>
						<logic:notEmpty name="PesquisarGuiaDevolucaoActionForm"	property="codigoImovel">
							<html:text property="inscricaoImovel" size="40" maxlength="30"
								readonly="true"	style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEmpty>
					</logic:notPresent>
					<a href="javascript:limparForm('imovel');">
					<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Limpar Matrícula do Imóvel" /></a>
					</td>
				</tr>
				<tr>
					<td height="10"><strong>Código do Cliente:</strong></td>
					<td colspan="3"><html:text property="codigoCliente" maxlength="9"
						size="9"
						onkeyup="return validaEnterCliente(event, 'exibirPesquisarGuiaDevolucaoAction.do', 'codigoCliente'); " />
					<a href="javascript:habilitarPesquisaCliente(document.forms[0]);" >
					<%-- <a	href="javascript:redirecionarSubmit('exibirPesquisarClienteAction.do?caminhoRetornoTelaPesquisaCliente=exibirPesquisarGuiaDevolucaoAction', 400, 800);"> --%>
					<img width="23" height="21"
						src="<bean:message key='caminho.imagens'/>pesquisa.gif" border="0" title="Código do Cliente"/></a>
					<logic:present
						name="idClienteNaoEncontrado">
						<logic:equal name="idClienteNaoEncontrado" value="exception">
							<html:text property="nomeCliente" size="40" maxlength="30"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:equal>
						<logic:notEqual name="idClienteNaoEncontrado" value="exception">
							<html:text property="nomeCliente" size="40" maxlength="30"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEqual>
					</logic:present> <logic:notPresent name="idClienteNaoEncontrado">
						<logic:empty name="PesquisarGuiaDevolucaoActionForm"
							property="codigoCliente">
							<html:text property="nomeCliente" value="" size="40"
								maxlength="30" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:empty>
						<logic:notEmpty name="PesquisarGuiaDevolucaoActionForm"
							property="codigoCliente">
							<html:text property="nomeCliente" size="40" maxlength="30"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEmpty>

					</logic:notPresent>
					<a
						href="javascript:limparForm('cliente');">
					<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Limpar Código do Cliente" /></a></td>
				</tr>
				<tr>
					<td height="0"><strong>Período de Emissão das Guias:</strong></td>

					<td colspan="3"><strong> <html:text
						property="dataEmissaoGuiaInicio" size="10" maxlength="10"
						onkeyup="javascript:mascaraData(this,event); replicarCampo(document.forms[0].dataEmissaoGuiaFim,this);"  onfocus="replicarCampo(document.forms[0].dataEmissaoGuiaFim,this);" /> <a
						href="javascript:abrirCalendario('PesquisarGuiaDevolucaoActionForm', 'dataEmissaoGuiaInicio')">
					<img border="0"
						src="<bean:message key="caminho.imagens"/>calendario.gif"
						width="20" border="0" align="absmiddle" alt="Exibir Calendário" /></a>
					a</strong> <html:text property="dataEmissaoGuiaFim" size="10"
						maxlength="10" onkeyup="mascaraData(this, event);" /> <a
						href="javascript:abrirCalendario('PesquisarGuiaDevolucaoActionForm', 'dataEmissaoGuiaFim')">
					<img border="0"
						src="<bean:message key="caminho.imagens"/>calendario.gif"
						width="20" border="0" align="absmiddle" alt="Exibir Calendário" /></a>
					dd/mm/aaaa</td>
				</tr>
				<tr>
					<td height="0"><strong>Período de Validade das Guias:</strong></td>

					<td colspan="3"><strong> <html:text
						property="dataValidadeGuiaInicio" size="10" maxlength="10"
						onkeyup="javascript:mascaraData(this,event); replicarCampo(document.forms[0].dataValidadeGuiaFim,this);"  onfocus="replicarCampo(document.forms[0].dataValidadeGuiaFim,this);" /> <a
						href="javascript:abrirCalendario('PesquisarGuiaDevolucaoActionForm', 'dataValidadeGuiaInicio')">
					<img border="0"
						src="<bean:message key="caminho.imagens"/>calendario.gif"
						width="20" border="0" align="absmiddle" alt="Exibir Calendário" /></a>
					a</strong> <html:text property="dataValidadeGuiaFim" size="10"
						maxlength="10" onkeyup="mascaraData(this, event);" /> <a
						href="javascript:abrirCalendario('PesquisarGuiaDevolucaoActionForm', 'dataValidadeGuiaFim')">
					<img border="0"
						src="<bean:message key="caminho.imagens"/>calendario.gif"
						width="20" border="0" align="absmiddle" alt="Exibir Calendário" /></a>
					dd/mm/aaaa</td>
				</tr>
				<tr>
					<td class="style1"><strong>Situação da Guia:</strong></td>
					<td colspan="4" class="style1"><strong> <html:select
						property="situacaoGuia" style="width: 230px;" multiple="mutiple"
						size="4">
						<logic:present name="collectionSituacaoGuia">
							<html:option
								value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
							<html:options collection="collectionSituacaoGuia"
								labelProperty="descricaoDebitoCreditoSituacao" property="id" />
						</logic:present>
					</html:select> </strong></td>
				</tr>
				<tr>
					<td class="style1"><strong>Tipo de Crédito:</strong></td>
					<td colspan="4" class="style1"><strong> <html:select
						property="tipoCredito" style="width: 230px;" multiple="mutiple"
						size="4">
						<logic:present name="collectionTipoCredito">
							<html:option
								value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
							<html:options collection="collectionTipoCredito"
								labelProperty="descricao" property="id" />
						</logic:present>
					</html:select> </strong></td>
				</tr>
				<tr>
					<td class="style1"><strong>Tipo do Documento:</strong></td>
					<td colspan="4" class="style1"><strong> <html:select
						property="tipoDocumento" style="width: 230px;" multiple="mutiple"
						size="4">
						<logic:present name="collectionTipoDocumento">
							<html:option
								value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
							<html:options collection="collectionTipoDocumento"
								labelProperty="descricaoDocumentoTipo" property="id" />
						</logic:present>
					</html:select> </strong></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td colspan="3">&nbsp;</td>
				</tr>
				<tr>
					<td>
			          	<INPUT TYPE="button" class="bottonRightCol" value="Limpar" onclick="document.forms[0].reset();limparFormulario();"/>
							&nbsp;&nbsp;
			          	<logic:present name="caminhoRetornoTelaPesquisaGuiaDevolucao">
			          		<INPUT TYPE="button" class="bottonRightCol" value="Voltar" onclick="redirecionarSubmit('${caminhoRetornoTelaPesquisaGuiaDevolucao}.do')"/>
			          	</logic:present>
			         </td>
			         <td align="right">
				        <input type="button" name="Button" class="bottonRightCol" value="Pesquisar" onClick="javascript:validarForm(document.forms[0]);" />
					 </td>
				</tr>
			</table>
			<p>&nbsp;</p>
			</td>

		</tr>
	</table>
	<logic:notEqual name="PesquisarGuiaDevolucaoActionForm"
		property="codigoImovel" value="">
		<script language="JavaScript">
	<!--
		controleImovel(document.forms[0]);
	-->
	</script>
	</logic:notEqual>

	<logic:notEqual name="PesquisarGuiaDevolucaoActionForm"
		property="codigoCliente" value="">
		<script language="JavaScript">
	<!--
		controleCliente(document.forms[0]);
	-->
	</script>
	</logic:notEqual>
</html:form>
</body>
</html:html>
