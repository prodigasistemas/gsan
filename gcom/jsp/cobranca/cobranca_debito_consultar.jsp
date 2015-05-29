<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@page import="gcom.util.ConstantesSistema"%>
<head>
<html:html>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">

<%
	Boolean semPermissao = (Boolean) session.getAttribute("semPermissao");
%>

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="ConsultarDebitoActionForm" dynamicJavascript="false" />

<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js" ></script>
<script language="JavaScript"><!--
 Begin

var bCancel = false; 

function validateConsultarDebitoActionForm(form) {                                                                   
	if (bCancel) 
		return true; 
	else 
		return validateCaracterEspecial(form) && validateLong(form) && validateMesAno(form) && validateDate(form) ; 
} 

function caracteresespeciais () { 
	this.aa = new Array("codigoImovel", "Matrícula do Imóvel possui caracteres especiais.", new Function ("varName", " return this[varName];"));	
	this.ab = new Array("codigoCliente", "Código do Cliente possui caracteres especiais.", new Function ("varName", " return this[varName];"));	
	this.ac = new Array("referenciaInicial", "Período de Referência do Débito Inicial possui caracteres especiais.", new Function ("varName", " return this[varName];"));
	this.ad = new Array("referenciaFinal", "Período de Referência do Débito Final possui caracteres especiais.", new Function ("varName", " return this[varName];"));
	this.ae = new Array("dataVencimentoInicial", "Período de Vencimento do Débito Inicial possui caracteres especiais.", new Function ("varName", " return this[varName];"));
	this.af = new Array("dataVencimentoFinal", "Período de Vencimento do Débito Final possui caracteres especiais.", new Function ("varName", " return this[varName];"));	
} 

function IntegerValidations () { 
	this.aa = new Array("codigoImovel", "Matrícula do Imóvel deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
	this.ab = new Array("codigoCliente", "Código do Cliente deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
	this.ab = new Array("codigoClienteSuperior", "Código do Cliente Superior deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
} 

function MesAnoValidations () { 
	this.aa = new Array("referenciaInicial", "Período de Referência do Débito Inicial inválido.", new Function ("varName", "this.datePattern='dd/MM/yyyy';  return this[varName];"));
	this.ab = new Array("referenciaFinal", "Período de Referência do Débito Final inválido.", new Function ("varName", "this.datePattern='dd/MM/yyyy';  return this[varName];"));
}

function DateValidations () { 
	this.aa = new Array("dataVencimentoInicial", "Período de Vencimento do Débito Inicial inválido.", new Function ("varName", "this.datePattern='dd/MM/yyyy';  return this[varName];"));
	this.ab = new Array("dataVencimentoFinal", "Período de Vencimento do Débito Final inválido.", new Function ("varName", "this.datePattern='dd/MM/yyyy';  return this[varName];"));
} 
 

function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
    var form = document.forms[0];
	 if (tipoConsulta == 'cliente') {
	 		form.codigoCliente.value = codigoRegistro;
    	    form.codigoClienteClone.value = codigoRegistro;
        	form.action = 'exibirConsultarDebitoAction.do';
	        form.submit();
	 }else if (tipoConsulta == 'responsavelSuperior') {
	 		form.codigoClienteSuperior.value = codigoRegistro;
	        form.codigoClienteSuperiorClone.value = codigoRegistro;
    	    form.action = 'exibirConsultarDebitoAction.do';
        	form.submit();
     } else if(tipoConsulta == 'imovel'){
      form.codigoImovel.value = codigoRegistro;
      form.codigoImovelClone.value = codigoRegistro;
      form.inscricaoImovel.value = descricaoRegistro;
      form.action = 'exibirConsultarDebitoAction.do';
      form.submit();
    }
     
}

function limparForm(tipo){
    var form = document.ConsultarDebitoActionForm;
    if(tipo == 'imovel')
    {
    	form.codigoClienteSuperior.readOnly = false;
    	form.codigoCliente.readOnly = false;
    	form.tipoRelacao.disabled = false;
		var ObjCodigoImovel = returnObject(form,"codigoImovel");
		var ObjCodigoImovelClone = returnObject(form,"codigoImovelClone");
		var ObjInscricaoImovel = returnObject(form,"inscricaoImovel");
		ObjCodigoImovel.value = "";
		ObjCodigoImovelClone.value = "";		
		ObjInscricaoImovel.value = "";
	}
	if(tipo == 'cliente')
    {
    	form.codigoClienteSuperior.readOnly = false;
    	form.codigoImovel.readOnly = false;
		var ObjCodigoCliente = returnObject(form,"codigoCliente");
		var ObjCodigoClienteClone = returnObject(form,"codigoClienteClone");
		var ObjNomeCliente = returnObject(form,"nomeCliente");
		var ObjTipoRelacao = returnObject(form,"tipoRelacao");
		ObjCodigoCliente.value = "";
		ObjCodigoClienteClone.value = "";
		ObjNomeCliente.value = "";		
		ObjTipoRelacao.value = <%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>;
	}
	if(tipo == 'clienteSuperior')
    {
    	form.codigoImovel.readOnly = false;
    	form.codigoCliente.readOnly = false;
    	form.tipoRelacao.disabled = false;
		var ObjCodigoClienteSuperior = returnObject(form,"codigoClienteSuperior");
		var ObjCodigoClienteSuperiorClone = returnObject(form,"codigoClienteSuperiorClone");
		var ObjNomeClienteSuperior = returnObject(form,"nomeClienteSuperior");
		ObjCodigoClienteSuperior.value = "";
		ObjCodigoClienteSuperiorClone.value = "";
		ObjNomeClienteSuperior.value = "";
		
		<% if (semPermissao == null || !semPermissao.equals(new Boolean(true))) {	%>
			form.responsavel[1].disabled = false;
			form.responsavel[2].disabled = false;
		<% } %>		
	}
}

function habilitarPesquisaCliente(form) {
	if (form.codigoCliente.readOnly == false) {
		form.tipoPesquisa.value = 'cliente';
		chamarPopup('exibirPesquisarClienteAction.do', 'cliente', null, null, 275, 480, '',form.codigoCliente.value);
	}	
}

function habilitarPesquisaImovel(form) {
	if (form.codigoImovel.readOnly == false) {
		chamarPopup('exibirPesquisarImovelAction.do', 'imovel', null, null, 275, 480, '',form.codigoImovel.value);
	}	
}

function habilitarPesquisaClienteSuperior(form) {
	if (form.codigoClienteSuperior.readOnly == false) {
		form.tipoPesquisa.value = 'clienteSuperior';
		chamarPopup('exibirPesquisarResponsavelSuperiorAction.do?pesquisaSuperior=sim', 'clienteSuperior', null, null, 275, 480, '',form.codigoClienteSuperior.value);
	}	
}

function validaEnterClienteSuperior(tecla, caminhoActionReload, nomeCampo) {
	var form = document.ConsultarDebitoActionForm;
	
	validaEnterComMensagem(tecla, caminhoActionReload, nomeCampo, "Código do Cliente Superior");
	
	if(form.codigoClienteSuperior.value.length > 0) {
		form.codigoImovel.readOnly = true;
		form.codigoImovel.disabled = true;
    	form.codigoImovel.value = "";	
		form.codigoImovelClone.value = "";	
		form.inscricaoImovel.value = "";
		form.codigoCliente.readOnly = true;
		form.codigoCliente.disabled = true;
		form.tipoRelacao.disabled = true;
		form.codigoCliente.value = "";	
		form.codigoClienteClone.value = "";	
		form.nomeCliente.value = "";
		form.tipoRelacao.value = <%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>;
		form.responsavel[0].checked = true;
		form.responsavel[1].disabled = true;
		form.responsavel[2].disabled = true;
    } else {
		form.codigoImovel.readOnly = false;
		form.codigoImovel.disabled = false;
		form.codigoClienteSuperior.value = "";
		form.codigoClienteSuperiorClone.value = "";
        form.nomeClienteSuperior.value = "";
        form.nomeCliente.value = "";
        form.inscricaoImovel.value = "";
        form.codigoCliente.readOnly = false;
        form.codigoCliente.disabled = false;
		form.tipoRelacao.disabled = false;
		
		<% if (semPermissao == null || !semPermissao.equals(new Boolean(true))) {	%>
			form.responsavel[1].disabled = false;
			form.responsavel[2].disabled = false;
		<% } %>
	}
}

function validaEnterCliente(tecla, caminhoActionReload, nomeCampo) {
	var form = document.ConsultarDebitoActionForm;
	
	validaEnterComMensagem(tecla, caminhoActionReload, nomeCampo, "Código do Cliente");
	
	if(form.codigoCliente.value.length > 0) {
		form.codigoImovel.readOnly = true;
		form.codigoImovel.disabled = true;
    	form.codigoImovel.value = "";	
		form.codigoImovelClone.value = "";	
		form.inscricaoImovel.value = "";
		form.codigoClienteSuperior.readOnly = true;
		form.codigoClienteSuperior.disabled = true;
		form.codigoClienteSuperior.value = "";	
		form.codigoClienteSuperiorClone.value = "";	
		form.nomeClienteSuperior.value = "";
    } else {
		form.codigoImovel.readOnly = false;
		form.codigoImovel.disabled = false;
		form.codigoCliente.value = "";
		form.codigoClienteClone.value = "";
        form.nomeCliente.value = "";
        form.inscricaoImovel.value = "";
        form.nomeCliente.value = "";
        form.nomeClienteSuperior.value = "";
        form.codigoClienteSuperior.readOnly = false;
        form.codigoClienteSuperior.disabled = false;
	}
}
function validaEnterImovel(tecla, caminhoActionReload, nomeCampo) {
	var form = document.ConsultarDebitoActionForm;
	
	validaEnterComMensagem(tecla, caminhoActionReload, nomeCampo, "Matrícula do Imóvel");
	
	if(form.codigoImovel.value.length > 0) {
		form.codigoCliente.readOnly = true;
		form.codigoCliente.disabled = true;
		form.tipoRelacao.disabled = true;
		form.codigoCliente.value = "";	
		form.codigoClienteClone.value = "";	
		form.nomeCliente.value = "";
		form.tipoRelacao.value = <%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>;
		form.codigoClienteSuperior.readOnly = true;
		form.codigoClienteSuperior.disabled = true;
		form.codigoClienteSuperior.value = "";	
		form.codigoClienteSuperiorClone.value = "";	
		form.nomeClienteSuperior.value = "";
	} else {
		form.codigoCliente.readOnly = false;
		form.codigoCliente.disabled = false;
		form.tipoRelacao.disabled = false;
		form.codigoImovel.value = "";
		form.codigoImovelClone.value = "";
        form.inscricaoImovel.value = "";
        form.nomeCliente.value = "";
        form.codigoClienteSuperior.readOnly = false;
        form.codigoClienteSuperior.disabled = false;
	}
}
function controleImovel(){
	var form = document.ConsultarDebitoActionForm;
	if(form.codigoImovel.value.length > 0){
		form.codigoCliente.readOnly = true;
		form.tipoRelacao.disabled = true;
		form.codigoClienteSuperior.readOnly = true;
		form.responsavel[0].disabled = true;
		form.responsavel[1].disabled = true;
		form.responsavel[2].checked = true;
	}
	else
	{
		if (form.codigoCliente.value.length == 0 && form.codigoClienteSuperior.value.length == 0) {
			form.codigoCliente.readOnly = false;
			form.tipoRelacao.disabled = false;
			form.codigoClienteSuperior.readOnly = false;
		}
	}
}

function controleCliente(){
	var form = document.ConsultarDebitoActionForm;
	if(form.codigoCliente.value.length > 0){
		form.codigoImovel.readOnly = true;
		form.codigoClienteSuperior.readOnly = true;
		form.responsavel[0].checked = true;
	}
	else
	{
		if (form.codigoImovel.value.length == 0 && form.codigoClienteSuperior.value.length == 0) {
			form.codigoImovel.readOnly = false;
			form.codigoClienteSuperior.readOnly = false;
		}
	}
}

function controleClienteSuperior(){
	var form = document.ConsultarDebitoActionForm;
	if(form.codigoClienteSuperior.value.length > 0){
		form.codigoImovel.readOnly = true;
		form.codigoCliente.readOnly = true;
		form.tipoRelacao.disabled = true;
		
		//form.responsavel[1].checked = true;
		form.responsavel[1].disabled = false;
		form.responsavel[2].disabled = false;
		
	}
	else
	{
		if (form.codigoImovel.value.length == 0 && form.codigoCliente.value.length == 0) {
			form.codigoImovel.readOnly = false;
			form.codigoCliente.readOnly = false;
			form.tipoRelacao.disabled = false;
		}
		
		<% if (semPermissao == null || !semPermissao.equals(new Boolean(true))) {	%>
			form.responsavel[1].disabled = false;
			form.responsavel[2].disabled = false;
		<% } %>
	}
}


function chamarPopup(url, tipo, objeto, codigoObjeto, altura, largura, msg,objetoRelacionado){
	if(objetoRelacionado.readOnly != true){
		if (objeto == null || codigoObjeto == null){
			abrirPopup(url + "?" + "tipo=" + tipo, altura, largura);
		}
		else{
			if (codigoObjeto.length < 1 || isNaN(codigoObjeto)){
				alert(msg);
			}
			else{
				abrirPopup(url + "?" + "tipo=" + tipo + "&" + objeto + "=" + codigoObjeto + "&caminhoRetornoTelaPesquisa=" + tipo, altura, largura);
			}
		}
	}
}
 
function validarForm(form)
{
	var retorno = true;
	var objCodigoImovel 		 = returnObject(form, "codigoImovel");
	var objCodigoCliente 		 = returnObject(form, "codigoCliente");
	var objCodigoClienteSuperior = returnObject(form, "codigoClienteSuperior");
	var objReferenciaInicial 	 = returnObject(form, "referenciaInicial");
	var objReferenciaFinal 		 = returnObject(form, "referenciaFinal");
	var objDataVencimentoInicial = returnObject(form, "dataVencimentoInicial");
	var objDataVencimentoFinal   = returnObject(form, "dataVencimentoFinal");
	var objRefInicial 	 		 = returnObject(form, "refInicial");
	var objRefFinal 		     = returnObject(form, "refFinal");
	var objDtInicial 			 = returnObject(form, "dtInicial");
	var objDtFinal   			 = returnObject(form, "dtFinal");
	if (validateConsultarDebitoActionForm(form))
	{
		// Validações do caso de uso
		if ((trim(objCodigoImovel.value) != '' && trim(objCodigoCliente.value) != '' && trim(objCodigoClienteSuperior.value) != '')
			|| ((trim(objCodigoImovel.value) != '' && trim(objCodigoCliente.value) != '')) || (trim(objCodigoCliente.value) != '' && trim(objCodigoClienteSuperior.value)) 
			|| (trim(objCodigoImovel.value) != '' && trim(objCodigoClienteSuperior.value) != '')) {
			alert('Deve ser informado Matrícula do Imóvel ou Código do Cliente ou Código do Cliente Superior para consulta.');
			objCodigoImovel.value = "";
			objCodigoCliente.value = "";
			objCodigoImovel.focus();
			return false;
	    } else if (trim(objCodigoImovel.value) == '' && trim(objCodigoCliente.value) == '' && trim(objCodigoClienteSuperior.value) == ''){
   			alert('Informe Matrícula do Imóvel ou Código do Cliente ou Código do Cliente Superior.');
			objCodigoImovel.value = "";
			objCodigoCliente.value = "";
			objCodigoImovel.focus();
			return false;
	    }else if ((objReferenciaInicial.value != "" && objReferenciaFinal.value != "" ) && (comparaMesAno(objReferenciaInicial.value, ">", objReferenciaFinal.value))){
			alert("Período de Referência do Débito Inicial posterior ao Período de Referência do Débito Final.");
			return false;
		}else if ((objDataVencimentoInicial.value != "" && objDataVencimentoFinal.value != "" ) && (comparaData(objDataVencimentoInicial.value, ">", objDataVencimentoFinal.value))){
			alert("Período de Vencimento do Débito Inicial posterior ao Período de Vencimento do Débito Final.");
			return false;
		}
		if( retorno == true ){
			submeterFormPadrao(form);
		}	
	}
}

function habilitarResponsavel() {
	var form = document.ConsultarDebitoActionForm;
	if(form.codigoClienteSuperior.value.length > 0){
		form.responsavel[0].disabled = true;
		form.responsavel[2].disabled = true;
		form.responsavel[1].checked = true;
	}
}


--></script>
</head>

<body leftmargin="5" topmargin="5" onload="habilitarResponsavel()">
<html:form action="/consultarDebitoAction"   
	name="ConsultarDebitoActionForm"
  	type="gcom.gui.cobranca.ConsultarDebitoActionForm"
  	method="post"
  	onsubmit="return validateConsultarDebitoActionForm(this);" 
  	focus="codigoImovel">

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

			<td valign="top" class="centercoltext"><!--Início Tabela Reference a Páginação da Tela de Processo-->
			<p>&nbsp;</p>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img border="0" src="imagens/parahead_left.gif" /></td>
					<td class="parabg">Consultar D&eacute;bito</td>
					<td width="11" valign="top"><img border="0"
						src="imagens/parahead_right.gif" /></td>
				</tr>
			</table>
			<!--Fim Tabela Reference a Páginação da Tela de Processo-->
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>
					<td height="22" colspan="4">Para consultar d&eacute;bitos do
					cliente ou do im&oacute;vel, informe os dados abaixo:</td>
					<logic:present scope="application" name="urlHelp">
						<td align="right"><a href="javascript:abrirPopupHelp('${applicationScope.urlHelp}cobrancaDebitosConsultar', 500, 700);"><span style="font-weight: bold"><font color="#3165CE">Ajuda</font></span></a></td>									
					</logic:present>
					<logic:notPresent scope="application" name="urlHelp">
						<td align="right"><span style="font-weight: bold"><font color=#696969><u>Ajuda</u></font></span></td>									
					</logic:notPresent>
				</tr>
				<tr>
					<td width="30%" height="10"><strong>Matrícula do Imóvel:</strong></td>
					<td colspan="3">
						<html:text  property="codigoImovel" 
									maxlength="9" 
									size="9" 
									onkeyup="validaEnterImovel(event, 'exibirConsultarDebitoAction.do', 'codigoImovel');" 
									onkeypress="return isCampoNumerico(event);"/>
					<a href="javascript:habilitarPesquisaImovel(document.forms[0]);" alt="Pesquisar Imóvel">
						<img width="23" height="21"
							src="<bean:message key='caminho.imagens'/>pesquisa.gif" border="0" 
							title="Pesquisar imóvel"/></a>
							
						<logic:present name="corImovel">
							<logic:equal name="corImovel" value="exception">
								<html:text property="inscricaoImovel" size="25"	readonly="true"
									style="background-color:#EFEFEF; border:0; color: #ff0000" />
							</logic:equal>
							<logic:notEqual name="corImovel" value="exception">
								<html:text property="inscricaoImovel" size="25"	readonly="true"
									style="background-color:#EFEFEF; border:0; color: #000000" />
							</logic:notEqual>
						</logic:present> 
						<logic:notPresent name="corImovel">
							<logic:empty name="ConsultarDebitoActionForm"	property="codigoImovel">
								<html:text property="inscricaoImovel" size="25" value="" readonly="true"
									style="background-color:#EFEFEF; border:0; color: #000000" />
							</logic:empty>
							<logic:notEmpty name="ConsultarDebitoActionForm" property="codigoImovel">
								<html:text property="inscricaoImovel" size="25"	readonly="true"
									style="background-color:#EFEFEF; border:0; color: #000000" />
							</logic:notEmpty>
						</logic:notPresent>
						<a href="javascript:limparForm('imovel');"> 
							<img border="0" src="<bean:message key='caminho.imagens'/>limparcampo.gif" 
							style="cursor: hand;" 
							title="Apagar"/> 
						</a>	
					</td>
				</tr>
				<tr>
					<td colspan="5">
					<hr>
					</td>
				</tr>
				<tr>
					<td width="30%"><strong>Código do Cliente Superior:</strong></td>
					<td colspan="3">
						<html:text  property="codigoClienteSuperior" 
									maxlength="9" 
									size="9" 
									onkeyup="return validaEnterClienteSuperior(event, 'exibirConsultarDebitoAction.do', 'codigoClienteSuperior'); " 
									onkeypress="return isCampoNumerico(event);"/>
							<a href="javascript:habilitarPesquisaClienteSuperior(document.forms[0]);" alt="Pesquisar Cliente Superior">
								<img width="23" height="21" 
									 src="<bean:message key='caminho.imagens'/>pesquisa.gif" border="0" 
									 title="Pesquisar Cliente Superior"/></a>
						<logic:present name="corClienteSuperior">
							<logic:equal name="corClienteSuperior" value="exception">
								<html:text property="nomeClienteSuperior" size="30"	readonly="true"
									style="background-color:#EFEFEF; border:0; color: #ff0000" />
							</logic:equal>
							<logic:notEqual name="corClienteSuperior" value="exception">
								<html:text property="nomeClienteSuperior" size="30"	readonly="true"
									style="background-color:#EFEFEF; border:0; color: #000000" />
							</logic:notEqual>
						</logic:present> 
						<logic:notPresent name="corClienteSuperior">
							<logic:empty name="ConsultarDebitoActionForm" property="codigoClienteSuperior">
								<html:text property="nomeClienteSuperior" size="30" value="" readonly="true"
									style="background-color:#EFEFEF; border:0; color: #ff0000" />
							</logic:empty>
							<logic:notEmpty name="ConsultarDebitoActionForm" property="codigoClienteSuperior">
								<html:text property="nomeClienteSuperior" size="30"	readonly="true"
									style="background-color:#EFEFEF; border:0; color: #000000" />
							</logic:notEmpty>
						</logic:notPresent>
						<a href="javascript:limparForm('clienteSuperior');"> 
							<img border="0" 
								 src="<bean:message key='caminho.imagens'/>limparcampo.gif" 
								 title="Apagar"
								 style="cursor: hand;" /> 
						</a>
					</td>
				</tr>
				<tr>
					<td width="30%"><strong>Código do Cliente:</strong></td>
					<td colspan="3">
						<html:text  property="codigoCliente" 
									maxlength="9" 
									size="9" 
									onkeyup="return validaEnterCliente(event, 'exibirConsultarDebitoAction.do', 'codigoCliente'); " 
									onkeypress="return isCampoNumerico(event);" />
						<a  href="javascript:habilitarPesquisaCliente(document.forms[0]);" 
							alt="Pesquisar Cliente">
							<img width="23" 
								 height="21" 
								 src="<bean:message key='caminho.imagens'/>pesquisa.gif" border="0" 
								 title="Pesquisar Cliente"/></a>
						<logic:present name="corCliente">
							<logic:equal name="corCliente" value="exception">
								<html:text property="nomeCliente" size="30"	readonly="true"
									style="background-color:#EFEFEF; border:0; color: #ff0000" />
							</logic:equal>
							<logic:notEqual name="corCliente" value="exception">
								<html:text property="nomeCliente" size="30"	readonly="true"
									style="background-color:#EFEFEF; border:0; color: #000000" />
							</logic:notEqual>
						</logic:present> 
						<logic:notPresent name="corCliente">
							<logic:empty name="ConsultarDebitoActionForm"	property="codigoCliente">
								<html:text property="nomeCliente" size="30" value="" readonly="true"
									style="background-color:#EFEFEF; border:0; color: #ff0000" />
							</logic:empty>
							<logic:notEmpty name="ConsultarDebitoActionForm" property="codigoCliente">
								<html:text property="nomeCliente" size="30"	readonly="true"
									style="background-color:#EFEFEF; border:0; color: #000000" />
							</logic:notEmpty>
						</logic:notPresent>
						<a href="javascript:limparForm('cliente');"> 
							<img border="0" 
								 src="<bean:message key='caminho.imagens'/>limparcampo.gif" 
								 style="cursor: hand;" 
								 title="Apagar"/> 
						</a>
					</td>
				</tr>
				<tr>
					<td width="30%"><strong>Tipo da Relação:</strong></td>
					<td colspan="3" align="right">
					<div align="left"><strong> <html:select property="tipoRelacao">
					<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
						<logic:notEmpty name="collectionClienteRelacaoTipo">
						<html:options collection="collectionClienteRelacaoTipo"
							labelProperty="descricao" property="id" />
						</logic:notEmpty>
					</html:select></strong></div>
					</td>
				</tr>
			  <tr> 
                <td><strong>Responsável:</strong></td>
                <td colspan="6"><span class="style2"><strong> 
                  <label>
				  <html:radio property="responsavel" value="0" onclick="javascript: reload();"/>
 				  Indicado na Conta</label>
 				  
 				  <logic:present name="semPermissao" scope="session">
                  <html:radio property="responsavel" value="1" disabled="true" />
                  </logic:present>
                  <logic:notPresent name="semPermissao" scope="session">
				  <html:radio property="responsavel" value="1" onclick="javascript: reload();"/>
				  </logic:notPresent>
 				  Atual do Imóvel</label>
 				  
                  <label>
                  <logic:present name="semPermissao" scope="session">
                  <html:radio property="responsavel" value="2" disabled="true" /> 
                  </logic:present>
                  <logic:notPresent name="semPermissao" scope="session">
				  <html:radio property="responsavel" value="2" onclick="javascript: reload();"/>
				  </logic:notPresent>
 				  Todos</label> 				  
                  </strong></span>
				</td>
              </tr>
				<tr>
					<td colspan="5">
					<hr>
					</td>
				</tr>
				<tr>
					<td width="30%"><strong>Per&iacute;odo de Refer&ecirc;ncia do D&eacute;bito:</strong></td>
					<td colspan="3" align="right">
					<div align="left">
						<html:text  property="referenciaInicial" 
									maxlength="7" 
									size="7" 
									onkeyup="mascaraAnoMes(this, event);replicarCampo(document.forms[0].referenciaFinal,this);" 
									onkeypress="return isCampoNumerico(event);"/>
					<strong> a</strong> 
						<html:text  property="referenciaFinal" 
									maxlength="7" 
									size="7" 
									onkeyup="mascaraAnoMes(this, event);" 
									onkeypress="return isCampoNumerico(event);"/>
					mm/aaaa</div>
					<div align="left"><strong></strong></div>
					<div align="left"></div>
					</td>
				</tr>
				<tr>
					<td width="30%"><strong>Per&iacute;odo de Vencimento do D&eacute;bito:</strong></td>
					<td colspan="3" align="right">
					<div align="left">
					<html:text property="dataVencimentoInicial" size="10"
						maxlength="10" 
						onkeyup="mascaraData(this, event);replicarCampo(document.forms[0].dataVencimentoFinal,this);" 
						onkeypress="return isCampoNumerico(event);"/> 
							<a href="javascript:abrirCalendario('ConsultarDebitoActionForm', 'dataVencimentoInicial')">
					<img border="0"
						src="<bean:message key='caminho.imagens'/>calendario.gif"
						width="20" border="0" align="absmiddle" alt="Exibir Calendário" /></a> <strong> a</strong> 
					<html:text property="dataVencimentoFinal"
						size="10" maxlength="10"  
						onkeyup="mascaraData(this, event);" 
						onkeypress="return isCampoNumerico(event);"/> 
						<a href="javascript:abrirCalendario('ConsultarDebitoActionForm', 'dataVencimentoFinal')">
					<img border="0"
						src="<bean:message key='caminho.imagens'/>calendario.gif"
						width="20" border="0" align="absmiddle" alt="Exibir Calendário" /></a>&nbsp;dd/mm/aaaa
					</div>
					</td>
				</tr>
				<tr>
					<td colspan="5">&nbsp;</td>
				</tr>
				<tr>
					
				</tr>
				
				<tr>
				<td colspan="5">
				<table width="100%" border="0">
				<tr>
					<td  class="style1">
						<input name="Submit22"
						class="bottonRightCol" value="Limpar" type="button"
						onclick="window.location.href='/gsan/exibirConsultarDebitoAction.do?menu=sim';">
						&nbsp;
						<input type="button" name="Button" class="bottonRightCol" value="Cancelar" tabindex="6" onClick="javascript:window.location.href='/gsan/telaPrincipal.do'" style="width: 80px"/>
					</td>
					<td align="right">
						<html:hidden property="codigoImovelClone" />
						<html:hidden property="codigoClienteClone" />	
						<html:hidden property="codigoClienteSuperiorClone" />
						<input type="hidden" name="tipoPesquisa" />
						
						<gsan:controleAcessoBotao name="Button" value="Consultar" onclick="javascript:validarForm(document.forms[0]);" url="consultarDebitoAction.do"/>				
						<%-- <input type="button" name="Button" class="bottonRightCol" value="Consultar" onClick="validarForm(document.forms[0]);" /> --%>
					</td>
				</tr>
				</table>
				</td>
				</tr>
				
				
				
				<tr>
					<td colspan="5">&nbsp;</td>
				</tr>
			</table>
			<p>&nbsp;</p>

			</td>
		</tr>
	</table>

	<%@ include file="/jsp/util/rodape.jsp"%>
	<script language="JavaScript">
	<!--
		controleImovel();
	-->
	</script>

	<script language="JavaScript">
	<!--
		controleCliente();
	-->
	</script>
	
	<script language="JavaScript">
	<!--
		controleClienteSuperior();
	-->
	</script>


</html:form>
</body>
</html:html>
