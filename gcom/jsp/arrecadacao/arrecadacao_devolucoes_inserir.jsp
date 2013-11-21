<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%@ page import="gcom.util.Util"%>
<%@page import="gcom.util.ConstantesSistema" isELIgnored="false"%>
<%@ page import="gcom.arrecadacao.GuiaDevolucao" isELIgnored="false"%>
<html:html>

<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="InserirDevolucoesActionForm" />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript">
<!--
function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
    var form = document.forms[0];
    if (tipoConsulta == 'cliente') {
        form.codigoCliente.value = codigoRegistro;
        form.codigoClienteClone.value = codigoRegistro;
        form.action = 'exibirInserirDevolucoesAction.do';
        form.submit();
     }
     if(tipoConsulta == 'imovel'){
      form.codigoImovel.value = codigoRegistro;
      form.codigoImovelClone.value = codigoRegistro;
      form.inscricaoImovel.value = descricaoRegistro;
      form.action = 'exibirInserirDevolucoesAction.do';
      form.submit();
    }
    if (tipoConsulta == 'localidade') {
      form.localidade.value = codigoRegistro;
      form.localidadeClone.value = codigoRegistro;
      form.localidadeDescricao.value = descricaoRegistro;
      form.action = 'exibirInserirDevolucoesAction.do';
      form.submit();
	}
	if (tipoConsulta == 'tipoDebito') { 	
 	  form.tipoDebito.value = codigoRegistro;
 	  form.tipoDebitoClone.value = codigoRegistro;
 	  form.descricaoTipoDebito.value = descricaoRegistro; 
      form.action = 'exibirInserirDevolucoesAction.do';
      form.submit();
 	}
 	if (tipoConsulta == 'guiaDevolucao') {
 	  form.guiaDevolucao.value = codigoRegistro;
 	  form.guiaDevolucaoDescricao.value = descricaoRegistro; 
      form.action = 'exibirInserirDevolucoesAction.do';
      form.submit();
 	}
}
function recuperarDadosCincoParametros(codigoRegistro, descricaoRegistro1, descricaoRegistro2, descricaoRegistro3, tipoConsulta) { 
	var form = document.InserirDevolucoesActionForm;
 	if (tipoConsulta == 'avisoBancario') {
		form.codigoAgenteArrecadador.value = descricaoRegistro1;
		form.dataLancamentoAviso.value = descricaoRegistro2;
		form.numeroSequencialAviso.value = descricaoRegistro3;
		form.avisoBancario.value = codigoRegistro;
		form.guiaDevolucao.focus();
	}
}
function limparForm(tipo){
    var form = document.InserirDevolucoesActionForm;
    if(tipo == 'guiaDevolucao')
    {
		if (form.guiaDevolucao.disabled == false) {
			form.localidade.disabled = false;
			form.codigoImovel.disabled = false;
			form.codigoCliente.disabled = false;
			form.referenciaDevolucao.disabled = false;
			form.tipoDebito.disabled = false;
			form.guiaDevolucao.disabled = false;
			form.valorDevolucao.disabled = false;
			var ObjGuiaDevolucao = returnObject(form,"guiaDevolucao");
			var ObjGuiaDevolucaoDescricao = returnObject(form,"guiaDevolucaoDescricao");
			var ObjCodigoCliente = returnObject(form,"codigoCliente");
			var ObjCodigoClienteClone = returnObject(form,"codigoClienteClone");
			var ObjCodigoImovel = returnObject(form,"codigoImovel");
			var ObjCodigoImovelClone = returnObject(form,"codigoImovelClone");
			var ObjNomeCliente = returnObject(form,"nomeCliente");
			var ObjInscricaoImovel = returnObject(form,"inscricaoImovel");
			var ObjLocalidade = returnObject(form,"localidade");
			var ObjLocalidadeClone = returnObject(form,"localidadeClone");
			var ObjLocalidadeDescricao = returnObject(form,"localidadeDescricao");
			var ObjReferenciaDevolucao = returnObject(form,"referenciaDevolucao");
			var ObjReferenciaDevolucaoClone = returnObject(form,"referenciaDevolucaoClone");
			var ObjValorDevolucao = returnObject(form,"valorDevolucao");
			var ObjTipoDebito = returnObject(form,"tipoDebito");
			var ObjTipoDebitoClone = returnObject(form,"tipoDebitoClone");
			var ObjDescricaoTipoDebito = returnObject(form,"descricaoTipoDebito");
			ObjGuiaDevolucao.value = "";
			ObjGuiaDevolucaoDescricao.value = "";
			ObjCodigoImovel.value = "";
			ObjCodigoImovelClone.value = "";
			ObjLocalidade.value = "";
			ObjLocalidadeClone.value = "";
			ObjLocalidadeDescricao.value = "";		
			ObjInscricaoImovel.value = "";
			ObjCodigoCliente.value = "";
			ObjCodigoClienteClone.value = "";
			ObjNomeCliente.value = "";
			ObjReferenciaDevolucao.value = "";
			ObjReferenciaDevolucaoClone.value = "";
			ObjValorDevolucao.value = "";
			ObjTipoDebito.value = "";
			ObjTipoDebitoClone.value = "";
			ObjDescricaoTipoDebito.value = "";
		}
	}
	if(tipo == 'imovel')
    {
    	if (form.codigoImovel.disabled == false) {
			form.codigoCliente.disabled = false;
	    	form.guiaDevolucao.disabled = false;
	    	//form.localidade.disabled = false;
			var ObjCodigoImovel = returnObject(form,"codigoImovel");
			var ObjCodigoImovelClone = returnObject(form,"codigoImovelClone");
			var ObjInscricaoImovel = returnObject(form,"inscricaoImovel");
			/*var ObjLocalidade = returnObject(form,"localidade");
			var ObjLocalidadeClone = returnObject(form,"localidadeClone");
			var ObjLocalidadeDescricao = returnObject(form,"localidadeDescricao");*/
			ObjCodigoImovel.value = "";
			ObjCodigoImovelClone.value = "";		
			ObjInscricaoImovel.value = "";
			/*ObjLocalidade.value = "";
			ObjLocalidadeClone.value = "";
			ObjLocalidadeDescricao.value = "";*/
		}
	}
	if(tipo == 'cliente')
    {
    	if (form.codigoCliente.disabled == false) {
			form.codigoImovel.disabled = false;
	    	form.guiaDevolucao.disabled = false;
			var ObjCodigoCliente = returnObject(form,"codigoCliente");
			var ObjCodigoClienteClone = returnObject(form,"codigoClienteClone");
			var ObjNomeCliente = returnObject(form,"nomeCliente");
			ObjCodigoCliente.value = "";
			ObjCodigoClienteClone.value = "";
			ObjNomeCliente.value = "";		
		}
	}
	if(tipo == 'localidade')
    {
        if (form.localidade.disabled == false) {
	        form.guiaDevolucao.disabled = false;
	        form.localidade.disabled = false;
			var ObjLocalidade = returnObject(form,"localidade");
			var ObjLocalidadeClone = returnObject(form,"localidadeClone");
			var ObjLocalidadeDescricao = returnObject(form,"localidadeDescricao");
			ObjLocalidade.value = "";
			ObjLocalidadeClone.value = "";
			ObjLocalidadeDescricao.value = "";
		}
	}
}
function limparAvisoBancario() {
	var form = document.InserirDevolucoesActionForm;
		form.codigoAgenteArrecadador.value = "";
		form.dataLancamentoAviso.value = "";
		form.numeroSequencialAviso.value = "";
		form.avisoBancario.value = "";
}
function limparTipoDebito(){
	var form = document.forms[0];
 	if (form.tipoDebito.disabled == false) {
 		form.tipoDebito.value = "";
 		form.tipoDebitoClone.value = "";
	 	form.descricaoTipoDebito.value = ""; 		
	}
}
 
function validaEnterCliente(tecla, caminhoActionReload, nomeCampo) {
	var form = document.InserirDevolucoesActionForm;
	
	validaEnterComMensagem(tecla, caminhoActionReload, nomeCampo, "Código do Cliente");
	
	if(form.codigoCliente.value.length > 0) {
		form.codigoImovel.disabled = true;
		if(form.guiaDevolucao.value.length == 0 || form.guiaDevolucao.value.length < 0) {
			form.guiaDevolucao.disabled = true;
		}
    } else {
		form.codigoImovel.disabled = false;
		form.guiaDevolucao.disabled = false;
		form.codigoCliente.value = "";
		form.codigoClienteClone.value = "";
        form.nomeCliente.value = "";
        form.guiaDevolucao.value = "";
        form.guiaDevolucaoDescricao.value = "";
	}
}
function validaEnterImovel(tecla, caminhoActionReload, nomeCampo) {
	var form = document.InserirDevolucoesActionForm;

	validaEnterComMensagem(tecla, caminhoActionReload, nomeCampo, "Matrícula do Imóvel");

	if(form.codigoImovel.value.length > 0) {
		form.codigoCliente.disabled = true;
		//form.localidade.disabled = true;
		if(form.guiaDevolucao.value.length == 0 || form.guiaDevolucao.value.length < 0) {
			form.guiaDevolucao.disabled = true;
		}
	} else {
		form.codigoCliente.disabled = false;
		//form.localidade.disabled = false;
		form.guiaDevolucao.disabled = false;
		form.codigoImovel.value = "";
		form.codigoImovelClone.value = "";
        form.inscricaoImovel.value = "";
        form.nomeCliente.value = "";
        form.guiaDevolucao.value = "";
        form.guiaDevolucaoDescricao.value = "";
	}
}
 
function validaEnterGuiaDevolucao(tecla, caminhoActionReload, nomeCampo) {
	var form = document.forms[0];
	
	validaEnterComMensagem(tecla, caminhoActionReload, nomeCampo, "Guia de Devolução");
	
	form.codigoCliente.value = "";
	form.nomeCliente.value = "";
	form.codigoClienteClone.value = "";
	form.codigoImovel.value = "";
	form.codigoImovelClone.value = "";
    form.inscricaoImovel.value = "";
    form.localidade.value = "";
    form.localidadeClone.value = "";
    form.localidadeDescricao.value = "";
    form.referenciaDevolucao.value = "";
    form.referenciaDevolucaoClone.value = "";
    form.valorDevolucao.value = "";
    form.tipoDebito.value = "";
    form.tipoDebitoClone.value = "";
    form.descricaoTipoDebito.value = "";
	if(form.guiaDevolucao.value.length > 0) {
		form.localidade.disabled = true;
		form.codigoImovel.disabled = true;
		form.codigoCliente.disabled = true;
		form.referenciaDevolucao.disabled = true;
		form.tipoDebito.disabled = true;
	} else {
    	form.localidade.disabled = false;
		if(form.codigoCliente.value == "")
		{
			form.codigoImovel.disabled = false;
		}
		form.codigoCliente.disabled = false;
		form.referenciaDevolucao.disabled = false;
		form.tipoDebito.disabled = false;
		form.valorDevolucao.disabled = false;
		form.guiaDevolucao.value = "";
        form.guiaDevolucaoDescricao.value = "";
	}
}

function validaEnterLocalidade(tecla, caminhoActionReload, nomeCampo) {
	var form = document.forms[0];
	
	validaEnterComMensagem(tecla, caminhoActionReload, nomeCampo, "Localidade");
	
	if(form.localidade.value.length > 0) {
		if(form.guiaDevolucao.value.length == 0 || form.guiaDevolucao.value.length < 0) {
			form.guiaDevolucao.disabled = true;
		}
    } else {
		form.guiaDevolucao.disabled = false;
		form.localidade.value = "";
		form.localidadeClone.value = "";
        form.localidadeDescricao.value = "";
	}
}
function validaEnterReferenciaDevolucao(tecla, caminhoActionReload, nomeCampo) {
	var form = document.forms[0];
	form.referenciaDevolucaoClone.value = form.referenciaDevolucao.value;
	
	validaEnterComMensagem(tecla, caminhoActionReload, nomeCampo, "Referência da Devolução");
	
	if(form.referenciaDevolucao.value.length > 0) {
		if(form.guiaDevolucao.value.length == 0 || form.guiaDevolucao.value.length < 0) {
			form.guiaDevolucao.disabled = true;
		}
    } else {
		form.guiaDevolucao.disabled = false;
		form.referenciaDevolucao.value = "";
		form.referenciaDevolucaoClone.value = "";
	}
}
function controleLocalidade(form){
	if(form.guiaDevolucao.value.length == 0 || form.guiaDevolucao.value.length < 0) {
			form.guiaDevolucao.disabled = true;
	}
}
function controleReferenciaDevolucao(form){
	if(form.guiaDevolucao.value.length == 0 || form.guiaDevolucao.value.length < 0) {
			form.guiaDevolucao.disabled = true;
	}
}
function controleImovel(){
	var form = document.forms[0];
	if(form.codigoImovel.value.length > 0){
		form.codigoCliente.value = "";	
		form.nomeCliente.value = "";
		form.codigoCliente.disabled = true;
		//form.localidade.disabled = true;
		if(form.guiaDevolucao.value.length == 0 || form.guiaDevolucao.value.length < 0) {
			form.guiaDevolucao.value = "";
			form.guiaDevolucaoDescricao.value = "";
			form.guiaDevolucao.disabled = true;
		}
	}
	else
	{
		form.codigoCliente.disabled = false;
		//form.localidade.disabled = false;
	}
}

function controleCliente(){
	var form = document.forms[0];
	if(form.codigoCliente.value.length > 0){
		form.codigoImovel.value = "";	
		form.inscricaoImovel.value = "";
		form.codigoImovel.disabled = true;
		if(form.guiaDevolucao.value.length == 0 || form.guiaDevolucao.value.length < 0) {
			form.guiaDevolucao.value = "";
			form.guiaDevolucaoDescricao.value = "";
			form.guiaDevolucao.disabled = true;
		}
	}
	else
	{
		form.codigoImovel.disabled = false;
	}
}
function controleGuiaDevolucao(){
	var form = document.forms[0];
	if(form.guiaDevolucao.value.length > 0){
		if(form.localidade.value.length > 0) {
			form.localidade.disabled = true;
		}
		form.codigoImovel.disabled = true;
		form.codigoCliente.disabled = true;
		form.referenciaDevolucao.disabled = true;
		form.tipoDebito.disabled = true;
	}else{
		if(form.codigoImovel.value.length < 0)
		{
			form.localidade.disabled = false;
		}
		else if(form.codigoCliente.value.length < 0)
		{
			form.codigoImovel.disabled = false;
		}
		if(form.codigoCliente.value.length > 0)
		{
			form.codigoCliente.disabled = false;
		}
		form.referenciaDevolucao.disabled = false;
		form.tipoDebito.disabled = false;
		form.valorDevolucao.disabled = false;	
	}
}

function chamarPopup(url, tipo, objeto, codigoObjeto, altura, largura, msg,objetoRelacionado){
	if(objetoRelacionado.disabled != true){
		if (objeto == null || codigoObjeto == null){
			abrirPopup(url + "?" + "tipo=" + tipo, altura, largura);
		}
		else{
			if (codigoObjeto.length < 1 || isNaN(codigoObjeto)){
				alert(msg);
			}
			else{
				abrirPopup(url + "?" + "tipo=" + tipo + "&" + objeto + "=" + codigoObjeto, altura, largura);
			}
		}
	}
}

function habilitarPesquisaGuiaDevolucao(form) {
	if (form.guiaDevolucao.disabled == false) {
		abrirPopup('exibirPesquisarGuiaDevolucaoAction.do?tipo=guiaDevolucao', 'guiaDevolucao');
	}	
}
function habilitarPesquisaLocalidade(form) {
	if (form.localidade.disabled == false) {
		chamarPopup('exibirPesquisarLocalidadeAction.do?tipo=imovelLocalidade', 'localidade', null, null, 275, 480, '',form.localidade.value);
	}	
}	
function habilitarPesquisaImovel(form) {
	if (form.codigoImovel.disabled == false) {
		chamarPopup('exibirPesquisarImovelAction.do', 'imovel', null, null, 275, 480, '',form.codigoImovel.value);
	}	
}	
function habilitarPesquisaCliente(form) {
	if (form.codigoCliente.disabled == false) {
		chamarPopup('exibirPesquisarClienteAction.do', 'cliente', null, null, 275, 480, '',form.codigoCliente.value);
	}	
}	
function habilitarPesquisaTipoDebito(form) {
	if (form.tipoDebito.disabled == false) {
		chamarPopup('exibirPesquisarTipoDebitoAction.do', 'tipoDebito', null, null, 550, 760, '',form.tipoDebito.value);
	}	
}	
function validarForm(form){
	var DATA_ATUAL = document.getElementById("DATA_ATUAL").value;
	var REFERENCIA_D = form.referenciaDevolucao.value.substring(3,7) + form.referenciaDevolucao.value.substring(0,2);
	var REFERENCIA_A = DATA_ATUAL.substring(6,10) + DATA_ATUAL.substring(3,5);
	var MES_ANO_C = DATA_ATUAL.substring(3,5) + DATA_ATUAL.substring(5,6) + DATA_ATUAL.substring(6,10);
	var ValorDevolucaoInteiro = obterNumerosSemVirgulaEPonto(form.valorDevolucao.value);
	var ValorGuiaDevolucaoInteiro = obterNumerosSemVirgulaEPonto(form.valorGuiaDevolucao.value);
	if(validateInserirDevolucoesActionForm(form)){
		if(form.codigoCliente.value.length > 0 && form.localidade.value.length < 1){
			alert("O preenchimento do campo Localidade é obrigatório caso Código do Cliente seja informado.");
			valorDevolucao.focus();
		}
		else if (!verificaAnoMesMensagemPersonalizada(form.referenciaDevolucao,"Mês/Ano de Referência da Devolução Inválido.")){
			form.referenciaDevolucao.value = "";
			return false;
		}
		else if (REFERENCIA_A < REFERENCIA_D){
		    alert("Mês/Ano de Referência da Devolução posterior ao Mês e Ano corrente " + MES_ANO_C + ".");
		    return false;
		}
		else if (comparaData(form.dataDevolucao.value, ">", DATA_ATUAL )){
			alert("Data da Devolução posterior à Data corrente " + DATA_ATUAL + ".");
			return false;
		}
		else if(form.codigoCliente.value.length > 0 && form.codigoImovel.value.length > 0){		
			alert("Informe Matrícula do Imóvel ou Código do Cliente.");
			return false;
		}
		else if(form.codigoCliente.value.length < 1 && form.codigoImovel.value.length < 1 && form.guiaDevolucao.value.length > 0){		
			alert("Informe Matrícula do Imóvel ou Código do Cliente.");
			return false;
		}
		else if(form.codigoCliente.value.length < 1 && form.codigoImovel.value.length < 1 && form.guiaDevolucao.value.length < 1){		
			alert("Informe Guia de Devolução, ou os dados da Guia de Devolução (Localidade, Matrícula do Imóvel ou Código do Cliente, Referência da Devolução e Tipo de Débito).");
			return false;
		}
		else
		{
			redirecionarSubmit('/gsan/inserirDevolucoesAction.do');
		}
	}
}
//-->
</script>
</head>
<body leftmargin="5" topmargin="5" onload="javascript:setarFoco('${requestScope.nomeCampo}');">
<html:form action="/exibirInserirDevolucoesAction" method="post"
	type="gcom.gui.arrecadacao.InserirDevolucoesActionForm"
	onsubmit="return validateInserirDevolucoesActionForm(this);">
	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>

	<table width="770" border="0" cellspacing="5" cellpadding="0">
		<tr>
			<td width="135" valign="top" class="leftcoltext">
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

			<td width="625" valign="top" class="centercoltext">
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
					<td class="parabg">Inserir Devolução</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>

			<!-- Início do Corpo - Fernanda Paiva  21/02/2006  -->

			<table width="100%" border="0">
				<tr>
					<td>Para inserir a devolução, informe os dados abaixo:</td>
					<td colspan="3">&nbsp;</td>
				</tr>
			</table>

			<table width="100%" border="0">
				<tr>
					<td height="10"><strong>Aviso Banc&aacute;rio:<font color="#FF0000">*</font></strong>
					</td>
					<td width="403"><html:text property="codigoAgenteArrecadador"
						size="3" maxlength="3" readonly="true"
						style="background-color:#EFEFEF; border:0; color: #000000" /> <html:text
						property="dataLancamentoAviso" size="10" maxlength="10"
						readonly="true"
						style="background-color:#EFEFEF; border:0; color: #000000" /> <html:text
						property="numeroSequencialAviso" size="3" maxlength="3"
						readonly="true"
						style="background-color:#EFEFEF; border:0; color: #000000" /> <html:hidden
						property="avisoBancario" /> 
						<a href="javascript:abrirPopup('exibirPesquisarAvisoBancarioAction.do?menu=sim', 475, 765);">
							<img width="23" height="21"	src="<bean:message key='caminho.imagens'/>pesquisa.gif" border="0" title="Pesquisar Aviso Bancário"/></a>
						<a href="javascript:limparAvisoBancario();"> <img
						src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Limpar Aviso Bancário" /></a></td>
				</tr>
				<tr>
					<td height="24" colspan="2">
					<hr>
					</td>
				</tr>
				<tr>
					<td height="10"><strong>Guia de Devolu&ccedil;&atilde;o:</strong></td>
					<td width="403">
						<html:text tabindex="1" property="guiaDevolucao" maxlength="9" size="9" onkeyup="validaEnterGuiaDevolucao(event, 'exibirInserirDevolucoesAction.do', 'guiaDevolucao');" />
						<a href="javascript:habilitarPesquisaGuiaDevolucao(document.forms[0]);">
							<img width="23" height="21"	src="<bean:message key='caminho.imagens'/>pesquisa.gif" border="0" title="Pesquisar Guia de Devolução" /></a>
						<logic:present name="corGuia"> 
							<logic:notEqual name="corGuia" value="exception">
								<html:text property="guiaDevolucaoDescricao" size="37"	readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" />
							</logic:notEqual>
							<logic:equal name="corGuia" value="exception">
								<html:text property="guiaDevolucaoDescricao" size="37"	readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000" />
							</logic:equal>
						</logic:present> 
						<logic:notPresent name="corGuia">
							<logic:empty name="InserirDevolucoesActionForm"	property="guiaDevolucao">
								<html:text property="guiaDevolucaoDescricao" size="37" value="" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" />
							</logic:empty>
							<logic:notEmpty name="InserirDevolucoesActionForm" property="guiaDevolucao">
								<html:text property="guiaDevolucaoDescricao" size="37"	readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" />
							</logic:notEmpty>
						</logic:notPresent>
						<a href="javascript:limparForm('guiaDevolucao');"> 
							<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"	border="0" align="absmiddle" title="Limpar Guia de Devolução" />
						</a>
					</td>
				</tr>
				<tr>
					<td height="24" colspan="2">
					<hr>
					</td>
				</tr>
				<tr>
					<td height="10"><strong>Localidade:</strong></td>
					<td width="81%" height="24">
						<html:text maxlength="3" tabindex="2" 
							property="localidade" size="3"
							onkeyup="validaEnterLocalidade(event, 'exibirInserirDevolucoesAction.do', 'localidade');" />
						<a href="javascript:habilitarPesquisaLocalidade(document.forms[0]);" >
							<img width="23" height="21"	src="<bean:message key='caminho.imagens'/>pesquisa.gif" border="0" title="Pesquisar Localidade" /></a>	
						<logic:present name="corLocalidade">
							<logic:equal name="corLocalidade" value="exception">
								<html:text property="localidadeDescricao" size="45"	readonly="true"
									style="background-color:#EFEFEF; border:0; color: #ff0000" />
							</logic:equal>
							<logic:notEqual name="corLocalidade" value="exception">
								<html:text property="localidadeDescricao" size="45"	readonly="true"
									style="background-color:#EFEFEF; border:0; color: #000000" />
							</logic:notEqual>
						</logic:present> 
						<logic:notPresent name="corLocalidade">
							<logic:empty name="InserirDevolucoesActionForm"	property="localidade">
								<html:text property="localidadeDescricao" size="45" value="" readonly="true"
									style="background-color:#EFEFEF; border:0; color: #000000" />
							</logic:empty>
							<logic:notEmpty name="InserirDevolucoesActionForm" property="localidade">
								<html:text property="localidadeDescricao" size="45"	readonly="true"
									style="background-color:#EFEFEF; border:0; color: #000000" />
							</logic:notEmpty>
						</logic:notPresent>
						<a href="javascript:limparForm('localidade');"> 
							<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"	border="0" align="absmiddle" title="Limpar Localidade" />
						</a>
					</td>
				</tr>
				<tr>
					<td height="10"><strong>Matrícula do Imóvel:</strong></td>
					<td width="403"><html:text property="codigoImovel" maxlength="9"
						size="9" tabindex="3" 
						onkeyup="validaEnterImovel(event, 'exibirInserirDevolucoesAction.do', 'codigoImovel');" />
					<a href="javascript:habilitarPesquisaImovel(document.forms[0]);" >
							<img width="23" height="21"	src="<bean:message key='caminho.imagens'/>pesquisa.gif" border="0" title="Pesquisar Matrícula do Imóvel" /></a>	
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
						<logic:empty name="InserirDevolucoesActionForm"	property="codigoImovel">
							<html:text property="inscricaoImovel" size="25" value="" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:empty>
						<logic:notEmpty name="InserirDevolucoesActionForm" property="codigoImovel">
							<html:text property="inscricaoImovel" size="25"	readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEmpty>
					</logic:notPresent>
					<a href="javascript:limparForm('imovel');"> 
						<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"	border="0" align="absmiddle" title="Limpar Matrícula do Imóvel" />
					</a>
					</td>
				</tr>
				<tr>
					<td height="10"><strong>Código do Cliente:</strong></td>
					<td width="80%"><html:text tabindex="4" property="codigoCliente" maxlength="9"
						size="9"
						onkeyup="return validaEnterCliente(event, 'exibirInserirDevolucoesAction.do', 'codigoCliente'); " />
					<a href="javascript:habilitarPesquisaCliente(document.forms[0]);" >
							<img width="23" height="21"	src="<bean:message key='caminho.imagens'/>pesquisa.gif" border="0" title="Pesquisar Código do Cliente" /></a>	
					<logic:present name="corCliente">
						<logic:equal name="corCliente" value="exception">
							<html:text property="nomeCliente" size="40"	readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:equal>
						<logic:notEqual name="corCliente" value="exception">
							<html:text property="nomeCliente" size="40"	readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEqual>
					</logic:present> 
					<logic:notPresent name="corCliente">
						<logic:empty name="InserirDevolucoesActionForm"	property="codigoCliente">
							<html:text property="nomeCliente" size="40" value="" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:empty>
						<logic:notEmpty name="InserirDevolucoesActionForm" property="codigoCliente">
							<html:text property="nomeCliente" size="40"	readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEmpty>
					</logic:notPresent>
					<a href="javascript:limparForm('cliente');"> 
						<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"	border="0" align="absmiddle" title="Limpar Código do Cliente" />
					</a>
					</td>
				</tr>
				<tr>
					<td height="10"><strong>Referência da Devolução:</strong></td>
					<td width="403"><INPUT TYPE="hidden" ID="DATA_ATUAL"
						value="${requestScope.dataAtual}" /> <html:text tabindex="5" 
						property="referenciaDevolucao" maxlength="7" size="7"
						onkeyup="mascaraAnoMes(this, event); return validaEnterReferenciaDevolucao(event, 'exibirInserirDevolucoesAction.do', 'referenciaDevolucao'); " /> mm/aaaa
					</td>
				</tr>
				<tr>
				  <td><strong>Tipo de Débito:</strong></td>
				  <td width="82%" height="24">
				  
				    <html:text maxlength="9" tabindex="6" property="tipoDebito" size="9" onkeypress="validaEnterComMensagem(event, 'exibirInserirDevolucoesAction.do','tipoDebito','Tipo do Débito');"/> 
				    <a href="javascript:habilitarPesquisaTipoDebito(document.forms[0]);" >
						<img width="23" height="21"	src="<bean:message key='caminho.imagens'/>pesquisa.gif" border="0" title="Pesquisar Tipo de Débito" /></a>	
					<logic:present name="corTipoDebito">
						<logic:equal name="corTipoDebito" value="exception">
							<html:text property="descricaoTipoDebito" size="40"	readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:equal>
						<logic:notEqual name="corTipoDebito" value="exception">
							<html:text property="descricaoTipoDebito" size="40"	readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEqual>
					</logic:present> 
					<logic:notPresent name="corTipoDebito">
						<logic:empty name="InserirDevolucoesActionForm"	property="tipoDebito">
							<html:text property="descricaoTipoDebito" size="40" value="" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:empty>
						<logic:notEmpty name="InserirDevolucoesActionForm" property="tipoDebito">
							<html:text property="descricaoTipoDebito" size="40"	readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEmpty>
					</logic:notPresent>
					<a href="javascript:limparTipoDebito();"> 
						<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"	border="0" align="absmiddle" title="Limpar Tipo de Débito" />
					</a>
				  </td>
				</tr>
       			<tr>
					<td height="24" colspan="2">
					<hr>
					</td>
				</tr>
				<tr>
					<td><strong>Valor da Devolução: <font color="#FF0000">*</font></strong></td>
					<td width="403"><html:hidden property="valorGuiaDevolucao" /> <html:text
						tabindex="7" property="valorDevolucao" maxlength="13" size="13" onkeyup="formataValorMonetario(this, 11)" style="text-align: right;"/>
					</td>
				</tr>
				<tr>
					<td height="10"><strong>Data da Devolu&ccedil;&atilde;o:<font
						color="#FF0000">*</font></strong></td>
					<td width="403"><html:text tabindex="8" property="dataDevolucao" maxlength="10"
						size="10" onkeyup="mascaraData(this, event);" /> <a
						href="javascript:abrirCalendario('InserirDevolucoesActionForm', 'dataDevolucao')">
					<img border="0"
						src="<bean:message key="caminho.imagens"/>calendario.gif"
						width="20" border="0" align="absmiddle" alt="Exibir Calendário" /></a>&nbsp;dd/mm/aaaa
					</td>
				</tr>
				<tr>
					<td colspan="3" height="10"></td>
				</tr>
				<tr>
					<td height="10">&nbsp;</td>
					<td><strong><font color="#FF0000">*</font></strong> Campos
					obrigat&oacute;rios</td>
					<td>&nbsp;</td>
				</tr>
			</table>
			<table border="0" width="100%">
				<tr>
					<td colspan="2">
					<html:hidden property="localidadeClone" />
					<html:hidden property="codigoImovelClone" />
					<html:hidden property="codigoClienteClone" />					
					<html:hidden property="referenciaDevolucaoClone" />					
					<html:hidden property="tipoDebitoClone" />
					<input tabindex="9" name="Button" type="button" class="bottonRightCol"
						value="Desfazer" align="left"
						onclick="window.location.href='<html:rewrite page="/exibirInserirDevolucoesAction.do?menu=sim"/>'">
					<input tabindex="10" name="Button" type="button" class="bottonRightCol" value="Cancelar" align="left"
						onclick="window.location.href='<html:rewrite page="/"/>'"></td>
					<td colspan="2" align="right">
					 <gsan:controleAcessoBotao name="Button" value="Inserir" onclick="javascript:validarForm(document.forms[0]);" url="inserirDevolucoesAction.do"/>
						<%-- <input tabindex="11" type="button" name="Button" class="bottonRightCol" value="Inserir" onClick="javascript:validarForm(document.forms[0]);" /> --%>
					</td>
				</tr>

				<!-- Fim do Corpo - Fernanda Paiva 21/02/2006  -->

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

	<logic:notEqual name="InserirDevolucoesActionForm"
		property="localidade" value="">
		<script language="JavaScript">
	<!--
		localidade(document.forms[0]);
	-->
	</script>
	</logic:notEqual>
	<logic:notEqual name="InserirDevolucoesActionForm"
		property="referenciaDevolucao" value="">
		<script language="JavaScript">
	<!--
		referenciaDevolucao(document.forms[0]);
	-->
	</script>
	</logic:notEqual>

	<script language="JavaScript">
	<!--
		controleGuiaDevolucao();
	-->
	</script>
	
</html:form>
</body>
</html:html>
