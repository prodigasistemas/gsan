<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan"%>

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
	src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>

<script language="JavaScript">
function limparUnidadeOrganizacional() {
	var form = document.GerarRelatorioRelacaoParcelamentoActionForm;
	
	form.idUnidadeOrganizacional.value = "";
	form.descricaoUnidadeOrganizacional.value = "";	
}

function limparElo() {
	var form = document.GerarRelatorioRelacaoParcelamentoActionForm;
	
	form.idEloPolo.value = "";
	form.descricaoElo.value = "";	
}
function limparLocalidade() {
	var form = document.GerarRelatorioRelacaoParcelamentoActionForm;
	form.idLocalidade.value = "";
	form.descricaoLocalidade.value = "";	
	habilitarSetorQuadra();
}
function limparSetorComercial() {
	var form = document.GerarRelatorioRelacaoParcelamentoActionForm;
	form.idSetorComercial.value = "";
	form.descricaoSetorComercial.value = "";	
}
function limparUsuarioResponsavel() {
	var form = document.GerarRelatorioRelacaoParcelamentoActionForm;
	form.idUsuarioResponsavel.value = "";
	form.descricaoUsuarioResponsavel.value = "";	
}

function limparUsuarioConfirmacao() {
	var form = document.GerarRelatorioRelacaoParcelamentoActionForm;

	form.idUsuarioConfirmacao.value = "";
	form.descricaoUsuarioConfirmacao.value = "";	
}

function replicaDados(campoOrigem, campoDestino){
	campoDestino.value = campoOrigem.value;
}function verificarMotivoDesfazimento(){
  var form = document.forms[0];  
  form.idsMotivoDesfazimento.selectedIndex = 0;
  form.action = 'exibirGerarRelatorioRelacaoParcelamentoAction.do?idSituacaoParcelamento='+form.idSituacaoParcelamento.value;
  form.submit(); 
}

function replicaDataParcelamento() {
  var form = document.forms[0];
  form.dataParcelamentoFinal.value = form.dataParcelamentoInicial.value;
}

function replicaDataConfirmacao() {

	  var form = document.forms[0];
	  form.dataConfirmacaoFinal.value = form.dataConfirmacaoInicial.value;
}

function replicaDataConfirmacaoOperadora() {

	  var form = document.forms[0];
	  form.dataConfirmacaoOperadoraFinal.value = form.dataConfirmacaoOperadoraInicial.value;
}
function habilitarSetorQuadra(){

 	var form = document.forms[0];
 	
 	if(form.idLocalidade.value != ""){
      form.idSetorComercial.disabled = false;
      form.idQuadra.disabled = false;
	} else{
      form.idSetorComercial.disabled = true;
      form.idQuadra.disabled = true;
      limparSetorComercial();
      form.idQuadra.value = "";
	}
}
function chamarPopup(url, tipo, objeto, codigoObjeto, altura, largura, msg, fieldName){

	var campo = document.forms['GerarRelatorioRelacaoParcelamentoActionForm'].elements[fieldName]
		                                      		
	if(campo.disabled == false) {
	
		if (objeto == null || codigoObjeto == null){
			abrirPopup(url + "?" + "tipo=" + tipo, altura, largura);
		} else {
			if (codigoObjeto.length < 1 || isNaN(codigoObjeto)){
				alert(msg);
			} else {
				abrirPopup(url + "?" + "tipo=" + tipo + "&" + objeto + "=" + codigoObjeto + "&caminhoRetornoTelaPesquisa=" + tipo, altura, largura);
			}
		}
	
	}
}

function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
	var form = document.GerarRelatorioRelacaoParcelamentoActionForm;
	
	if (tipoConsulta == 'localidade') {
      form.idLocalidade.value = codigoRegistro;
	  form.descricaoLocalidade.value = descricaoRegistro;
	  form.descricaoLocalidade.style.color = "#000000";
	  habilitarSetorQuadra();
	  form.idMunicipio.focus();
 	}
 	else if (tipoConsulta == 'setorComercial') {
      form.idSetorComercial.value = codigoRegistro;
      form.descricaoSetorComercial.value = descricaoRegistro;
      form.descricaoSetorComercial.style.color = "#000000";
      form.idQuadra.focus();
    }
    else if (tipoConsulta == 'elo') {
      form.idEloPolo.value = codigoRegistro;
      form.descricaoElo.value = descricaoRegistro;
      form.descricaoElo.style.color = "#000000";
    }        	else if (tipoConsulta == 'usuario') {
	  if(form.idUsuarioResponsavel.disabled == false){			
	      form.idUsuarioResponsavel.value = codigoRegistro;
	      form.descricaoUsuarioResponsavel.value = descricaoRegistro;
	      form.descricaoUsuarioResponsavel.style.color = "#000000";
	  }
	  if(form.idUsuarioConfirmacao.disabled == false){			
		  form.idUsuarioConfirmacao.value = codigoRegistro;
		  form.descricaoUsuarioConfirmacao.value = descricaoRegistro;
		  form.descricaoUsuarioConfirmacao.style.color = "#000000";
	  }
    }
    else if(tipoConsulta == 'unidadeOrganizacional'){
    	form.idUnidadeOrganizacional.value = codigoRegistro;
		form.descricaoUnidadeOrganizacional.value = descricaoRegistro;
		form.descricaoUnidadeOrganizacional.style.color = "#000000";
		
    } 	
}
function mudarStatusCamposRelatorio(){

  var form = document.forms[0];  
  if ( form.idVisaoRelatorio.value == '1' ){  
    form.idGerenciaRegional.disabled = false;
	form.idUnidadeNegocio.disabled = false;
	form.idEloPolo.disabled = true;
	form.idLocalidade.disabled = false;
	form.idSetorComercial.disabled = true
	form.idQuadra.disabled = true;
	form.idSituacaoParcelamento.disabled = false;
    if ( form.idSituacaoParcelamento[form.idSituacaoParcelamento.selectedIndex].value == 'DESFEITO' ){
		form.idsMotivoDesfazimento.disabled = false;
    }    
	form.valorDebitoInicial.disabled = false;
	form.valorDebitoFinal.disabled = false;
	form.idUsuarioResponsavel.disabled = true;  
  } else {
    form.idGerenciaRegional.disabled = false;
	form.idUnidadeNegocio.disabled = false;
	form.idEloPolo.disabled = false;
	form.idUsuarioResponsavel.disabled = false;	  
	form.idLocalidade.disabled = true;
	form.idSetorComercial.disabled = true;
	form.idQuadra.disabled = true;
	form.idSituacaoParcelamento.disabled = true;
	form.idsMotivoDesfazimento.disabled = true;
	form.valorDebitoInicial.disabled = true;
	form.valorDebitoFinal.disabled = true;
  }
}
function mudarStatusCamposRelatorio(){
  var form = document.forms[0];
  
  if ( form.idVisaoRelatorio.value == '1' ){  
    form.idGerenciaRegional.disabled = false;
	form.idUnidadeNegocio.disabled = false;
	form.idEloPolo.disabled = true;
	form.idLocalidade.disabled = false;
	form.idSetorComercial.disabled = true;
	form.idQuadra.disabled = true;
	form.idSituacaoParcelamento.disabled = false;	
    if ( form.idSituacaoParcelamento[form.idSituacaoParcelamento.selectedIndex].value == 'DESFEITO' ){
		form.idsMotivoDesfazimento.disabled = false;
    }   
	form.valorDebitoInicial.disabled = false;
	form.valorDebitoFinal.disabled = false;
	form.idUsuarioResponsavel.disabled = true; 
	form.dataConfirmacaoInicial.disabled = true;
	form.dataConfirmacaoFinal.disabled = true;
	form.idUsuarioConfirmacao.disabled = true;
	form.indicadorConfirmacaoOperadora.value ="";
	form.indicadorConfirmacaoOperadora.disabled = true;
	form.dataConfirmacaoOperadoraInicial.disabled = true;
	form.dataConfirmacaoOperadoraFinal.disabled = true;
	document.getElementById('radio1').checked=false;
	document.getElementById('radio2').checked=false;
	document.getElementById('radio1').disabled=true;
	document.getElementById('radio2').disabled=true; 
  } else if ( form.idVisaoRelatorio.value == '2' ){
    form.idGerenciaRegional.disabled = false;
	form.idUnidadeNegocio.disabled = false;
	form.idEloPolo.disabled = false;
	form.idUsuarioResponsavel.disabled = false;
	form.idLocalidade.disabled = false;
	form.idSetorComercial.disabled = true;
	form.idQuadra.disabled = true;
	form.idSituacaoParcelamento.disabled = true;
	form.idsMotivoDesfazimento.disabled = true;
	form.valorDebitoInicial.disabled = true;
	form.valorDebitoFinal.disabled = true;
	form.dataConfirmacaoInicial.disabled = true;
	form.dataConfirmacaoFinal.disabled = true;
	form.idUsuarioConfirmacao.disabled = true;
	form.indicadorConfirmacaoOperadora.value ="";
	form.indicadorConfirmacaoOperadora.disabled = true;
	form.dataConfirmacaoOperadoraInicial.disabled = true;
	form.dataConfirmacaoOperadoraFinal.disabled = true;
	document.getElementById('radio1').checked=false;
	document.getElementById('radio2').checked=false;
	document.getElementById('radio1').disabled=true;
	document.getElementById('radio2').disabled=true;
  }else if ( form.idVisaoRelatorio.value == '3' ){
	form.idGerenciaRegional.disabled = false;
	form.idUnidadeNegocio.disabled = false;
	form.idEloPolo.disabled = false;
	form.idLocalidade.disabled = false;
	form.idSetorComercial.disabled = false;
	form.idQuadra.disabled = false;
	form.idSituacaoParcelamento.value = '1';	
	form.idSituacaoParcelamento.disabled = true;
	form.idsMotivoDesfazimento.disabled = true;
	form.valorDebitoInicial.disabled = true;
	form.valorDebitoFinal.disabled = true;
	form.idUsuarioResponsavel.disabled = true;
	form.perfilImovel.disabled = true;
	form.municipiosAssociados.disabled = true;
	form.dataParcelamentoInicial.disabled = false;
	form.dataParcelamentoFinal.disabled = false;
	form.dataConfirmacaoInicial.disabled = false;
	form.dataConfirmacaoFinal.disabled = false;
	form.idUsuarioConfirmacao.disabled = false;
	form.indicadorConfirmacaoOperadora.disabled = false;
	form.dataConfirmacaoOperadoraInicial.disabled = false;
	form.dataConfirmacaoOperadoraFinal.disabled = false;

	document.getElementById('radio1').disabled=false;
	document.getElementById('radio2').disabled=false;

	if(document.getElementById('radio1').checked==true){
		validarExibicaoDataConfirmacaoOperadora(document.getElementById('radio1').value);
	}else if(document.getElementById('radio2').checked==true){
		validarExibicaoDataConfirmacaoOperadora(document.getElementById('radio2').value);
	}else{
		document.getElementById('radio2').checked = true;
		validarExibicaoDataConfirmacaoOperadora(document.getElementById('radio2').value);
	}
  }}
function limparCampos(){
  var form = document.forms[0];
  form.idGerenciaRegional.selectedIndex = 0;
  form.idUnidadeNegocio.selectedIndex = 0;  
  
  form.idLocalidade.value = "";
  form.descricaoLocalidade.value = "";
  form.idSetorComercial.value = "";
  form.descricaoSetorComercial.value = "";
  form.idQuadra.value = ""; 
  form.dataConfirmacaoInicial.value = "";
  form.dataConfirmacaoFinal.value = "";
  form.idUsuarioConfirmacao.value = "";
  form.descricaoUsuarioConfirmacao.value = "";
  form.dataConfirmacaoOperadoraInicial.value = "";
  form.dataConfirmacaoOperadoraFinal.value = "";
  form.idUnidadeOrganizacional.value = "";
  form.descricaoUnidadeOrganizacional.value = "";
  if ( form.idVisaoRelatorio.value == '1' ){  
    form.idGerenciaRegional.selectedIndex =0;
	form.idUnidadeNegocio.value = "";
	form.idEloPolo.value = "";
	form.descricaoElo.value = "";	
	form.idSituacaoParcelamento.value = "";
	form.dataParcelamentoInicial.value = "";
	form.dataParcelamentoFinal.value = "";
	form.valorDebitoInicial.value = "";
	form.valorDebitoFinal.value = "";
	form.idUsuarioResponsavel.value = "";
	form.descricaoUsuarioResponsavel.value = "";	
  } else {
	form.idSituacaoParcelamento.selectedIndex = 0;
	form.dataParcelamentoInicial.value = "";
	form.dataParcelamentoFinal.value = "";
	form.idsMotivoDesfazimento.selectedIndex = 0;
	form.valorDebitoInicial.value = "";
	form.valorDebitoFinal.value = "";  
  }
}  

function limparForm(){
  var form = document.forms[0];
  
  form.idVisaoRelatorio.selectedIndex = 0;
  limparCampos();  
  mudarStatusCamposRelatorio();
}

function validarExibicaoDataConfirmacaoOperadora(opcao){
	var form = document.forms[0];
	
	if(opcao == '1'){
		form.dataConfirmacaoOperadoraInicial.disabled = false;
		form.dataConfirmacaoOperadoraFinal.disabled = false;
	}else if(opcao == '2'){
		form.dataConfirmacaoOperadoraInicial.value = "";
		form.dataConfirmacaoOperadoraFinal.value = "";
		form.dataConfirmacaoOperadoraInicial.disabled = true;
		form.dataConfirmacaoOperadoraFinal.disabled = true;
	}
}

function abrirCalendarioVerificandoBloqueio(formName, fieldName) {
	
	var campo = document.forms[formName].elements[fieldName]
	                                      		
	if(campo.disabled == false) {
	
	    nomeForm = formName;
	    nomeCampo = fieldName;
		centerpopup('./jsp/util/calendario.jsp','calendario',225,268);

	}

}

function abrirPopupDependenciaVerificandoBloqueio(url, idDependencia, nomeMSG, altura, largura, fieldName){

	var campo = document.forms['GerarRelatorioRelacaoParcelamentoActionForm'].elements[fieldName]
		                                      		
	if(campo.disabled == false) {
	
		if (idDependencia.length < 1 || isNaN(idDependencia) || ((idDependencia * 1) == 0)){
			alert("Informe " + nomeMSG);
		}
		else{
			abrirPopup(url , altura, largura);
		}
	}
}

function chamarPopup(url, tipo,altura, largura, objetoRelacionado,nomeDependencia,valorDependencia){
	if(objetoRelacionado.disabled != true){
		abrirPopup(url + "?" + "tipo=" + tipo + "&" + nomeDependencia + "=" + valorDependencia, altura, largura);
	}
			
}

</script>
	
</head>

<html:javascript staticJavascript="false"
	formName="GerarRelatorioRelacaoParcelamentoActionForm" />

<body leftmargin="5" topmargin="5"
	onload="window.focus();javascript:habilitarSetorQuadra('${requestScope.nomeCampo}');mudarStatusCamposRelatorio();">

<div id="formDiv">
	<html:form action="/gerarRelatorioRelacaoParcelamentoAction.do"
		name="GerarRelatorioRelacaoParcelamentoActionForm"
		type="gcom.gui.cobranca.parcelamento.GerarRelatorioRelacaoParcelamentoActionForm"
		method="post">
	
		
		<%@ include file="/jsp/util/cabecalho.jsp"%>
		<%@ include file="/jsp/util/menu.jsp" %>
		<table width="770" border="0" cellspacing="5" cellpadding="0">
		  <tr>
		    <td width="130" valign="top" class="leftcoltext">
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
		      </div></td>
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
					<td class="parabg">Filtrar Rela&ccedil;&atilde;o de Parcelamentos</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
	
			<!--Fim Tabela Reference a Páginação da Tela de Processo-->
	
			<p>&nbsp;</p>
	
			<table width="100%" border="0">
				<tr>
					<td colspan="2">Para filtrar o conjunto de parcelamentos, informe
					os dados abaixo:</td>
				</tr>
				
				<tr>
					<td width="25%"><strong>Visão do Relatório:</strong></td>
					<td><html:select property="idVisaoRelatorio"
						onchange="limparCampos();mudarStatusCamposRelatorio();">
						<logic:equal property="idVisaoRelatorio" name="GerarRelatorioRelacaoParcelamentoActionForm" value="1">
						  <option value="1" selected="selected">MARKETING ATIVO</option>
						  <option value="2">ANALÍTICO</option>
						  <option value="3">CARTÃO DE CRÉDITO</option>							  
						</logic:equal>
						
						<logic:equal property="idVisaoRelatorio" name="GerarRelatorioRelacaoParcelamentoActionForm" value="2">
						  <option value="1">MARKETING ATIVO</option>
						  <option value="2" selected="selected">ANALÍTICO</option>
						  <option value="3">CARTÃO DE CRÉDITO</option>
						</logic:equal>
						
						<logic:equal property="idVisaoRelatorio" name="GerarRelatorioRelacaoParcelamentoActionForm" value="3">
						  <option value="1">MARKETING ATIVO</option>
						  <option value="2">ANALÍTICO</option>
						  <option value="3" selected="selected">CARTÃO DE CRÉDITO</option>
						</logic:equal>
						
						<logic:empty property="idVisaoRelatorio" name="GerarRelatorioRelacaoParcelamentoActionForm">
						  <option value="1" selected="selected">MARKETING ATIVO</option>
						  <option value="2">ANALÍTICO</option>
						  <option value="3">CARTÃO DE CRÉDITO</option>						  
						</logic:empty>
					</html:select>
					</td>
				</tr>
				
				<tr>
					<td><strong>Gerência Regional:</strong></td>
					<td><html:select property="idGerenciaRegional"
						onchange="controleGerencia(this.value);">
						<logic:notEmpty name="gerenciasRegionais">
							<option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</option>
							<html:options collection="gerenciasRegionais"
								labelProperty="nome" property="id" />
						</logic:notEmpty>
					</html:select>
					</td>
				</tr>
				
				<tr>
					<td><strong>Unidade Organizacional: </strong></td>
					<td><strong> 
						<html:text property="idUnidadeOrganizacional" 
								   size="5"
								   maxlength="5"
								   onkeyup="javascript:validaEnterComMensagem(event, 'exibirGerarRelatorioRelacaoParcelamentoAction.do', 'idUnidadeOrganizacional', 'Unidade Organizacional');"
								   onkeypress="javascript:validaEnterComMensagem(event, 'exibirGerarRelatorioRelacaoParcelamentoAction.do', 'idUnidadeOrganizacional', 'Unidade Organizacional'); return isCampoNumerico(event);" />
						<a  
							href="javascript:chamarPopup('exibirPesquisarUnidadeOrganizacionalAction.do', 'unidadeEmpresa', 495, 300, '',document.forms[0].idUnidadeOrganizacional,'','');">
							<img border="0" title="Pesquisar Unidade Organizacional" 
								 src="imagens/pesquisa.gif" height="21" width="23"></a>
					
						<logic:present name="unidadeOrganizacionalInexistente" scope="request">
							<html:text property="descricaoUnidadeOrganizacional" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000"
								size="40" maxlength="40" />
								
						</logic:present> 
						<logic:notPresent name="unidadeOrganizacionalInexistente"
							scope="request">
							<html:text property="descricaoUnidadeOrganizacional" readonly="true"
								style="background-color:#EFEFEF; border:0" size="40"
								maxlength="40" />
								
						</logic:notPresent> 
						<a href="javascript:limparUnidadeOrganizacional();"> <img
						   src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						   border="0" title="Apagar" /> </a> </strong>
					</td>
				</tr>
	
				<tr>
					<td><strong>Unidade de Negócio:</strong></td>
					<td><html:select property="idUnidadeNegocio" tabindex="2"
						onchange="controleUnidadeNegocio(this.value);">
						<logic:notEmpty name="colecaoUnidadeNegocio">
							<option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</option>
							<html:options collection="colecaoUnidadeNegocio"
								labelProperty="nome" property="id" />
						</logic:notEmpty>
					</html:select>
					</td>
				</tr>
	
				<tr>
					<td><strong>Localidade Pólo:</strong></td>
					<td><strong> <html:text property="idEloPolo" disabled="true"
						size="5" maxlength="3"
						onkeyup="return validaEnterComMensagem(event, 'exibirFiltrarRelacaoParcelamentoAction.do', 'idEloPolo', 'Elo Pólo');"
						onchange="controleElo(this.value);" onkeypress="return isCampoNumerico(event);" /> <a
						href="javascript:chamarPopup('exibirPesquisarEloAction.do', 'elo', null, null, 400, 800, '', 'idEloPolo');">
					<img border="0" title="Pesquisar Localidade Pólo" src="imagens/pesquisa.gif" height="21" width="23"></a> 
					
					<logic:present name="eloInexistente" scope="request">
						<html:text property="descricaoElo" readonly="true"
							style="background-color:#EFEFEF; border:0; color: #ff0000"
							size="40" maxlength="40" />
					</logic:present> <logic:notPresent name="eloInexistente"
						scope="request">
						<html:text property="descricaoElo" readonly="true"
							style="background-color:#EFEFEF; border:0" size="40"
							maxlength="40" />
					</logic:notPresent> <a href="javascript:limparElo();"> <img
						src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar" /> </a> </strong>
					</td>
				</tr>
				
				<tr>
					<td><strong>Localidade: </strong></td>
					<td><strong> <html:text property="idLocalidade" size="5"
						maxlength="3" onfocus="javascript:habilitarSetorQuadra();"
						onkeyup="javascript:habilitarSetorQuadra();"
						onkeypress="validaEnterComMensagem(event, 'exibirGerarRelatorioRelacaoParcelamentoAction.do', 'idLocalidade', 'Localidade'); return isCampoNumerico(event);" />
					<a
						href="javascript:chamarPopup('exibirPesquisarLocalidadeAction.do?indicadorUsoTodos=1', 'localidade', null, null, 275, 480, '','idLocalidade');">
					<img border="0" title="Pesquisar Localidade" src="imagens/pesquisa.gif" height="21" width="23"></a>
					
					<logic:present name="localidadeInexistente" scope="request">
						<html:text property="descricaoLocalidade" readonly="true"
							style="background-color:#EFEFEF; border:0; color: #ff0000"
							size="40" maxlength="40" />
							
					</logic:present> <logic:notPresent name="localidadeInexistente"
						scope="request">
						<html:text property="descricaoLocalidade" readonly="true"
							style="background-color:#EFEFEF; border:0" size="40"
							maxlength="40" />
							
					</logic:notPresent> <a href="javascript:limparLocalidade();"> <img
						src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar" /> </a> </strong>
					</td>
				</tr>
				
				<tr> 
                	<td><strong>Município:</strong></td>
                	<td colspan="6">
               			<strong>
						<html:select property="municipiosAssociados" style="width: 350px; height:100px;" 	multiple="true">
							<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
							<logic:present name="colecaoMunicipioAssociado" scope="request">
								<html:options collection="colecaoMunicipioAssociado" labelProperty="nome" property="id" />
							</logic:present>
						</html:select>                
                 		</strong>
                 	 </td>
              	</tr> 
				
				<tr>
					<td><strong>Setor Comercial:</strong></td>
					<td><strong> <html:text property="idSetorComercial" size="5"
						maxlength="4" disabled="true"
						onkeyup="return validaEnterComMensagem(event, 'exibirGerarRelatorioRelacaoParcelamentoAction.do', 'idSetorComercial', 'Setor Comercial');"
						onchange="controleSetorComercial(this.value);" onkeypress="return isCampoNumerico(event);" /> <a
						href="javascript:abrirPopupDependenciaVerificandoBloqueio('exibirPesquisarSetorComercialAction.do?idLocalidade='+document.forms[0].idLocalidade.value+'&tipo=SetorComercial',document.forms[0].idLocalidade.value,'Localidade', 400, 800, 'idSetorComercial');">
	
					<img border="0" title="Pesquisar Setor Comercial" src="imagens/pesquisa.gif" height="21" width="23"></a>
					<logic:present name="setorComercialInexistente" scope="request">
						<html:text property="descricaoSetorComercial" readonly="true"
							style="background-color:#EFEFEF; border:0; color: #ff0000"
							size="40" maxlength="40" />
							
					</logic:present> <logic:notPresent
						name="setorComercialInexistente" scope="request">
						<html:text property="descricaoSetorComercial" readonly="true"
							style="background-color:#EFEFEF; border:0" size="40"
							maxlength="40" />
					</logic:notPresent> <a href="javascript:limparSetorComercial();">
	
					<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar" /> </a> </strong>
					</td>
				</tr>
	
				<tr>
					<td><strong>Quadra:</strong></td>
					<td><html:text property="idQuadra" style="width: 65px;" size="4"
						maxlength="4" disabled="true" onkeypress="return isCampoNumerico(event);"/>
					</td>
				</tr>
				
				<tr> 
                	<td><strong>Perfil do Im&oacute;vel:</strong></td>
                	
                	<td colspan="6">
               			<strong>
						<html:select property="perfilImovel" 
							style="width: 350px; height:100px;" 
							multiple="true">
							
							<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
							
							<logic:present name="colecaoPerfilImovel" scope="request">
								<html:options collection="colecaoPerfilImovel" 
									labelProperty="descricao" 
									property="id" />
							</logic:present>
						</html:select>                
                 		</strong>
                 	 </td>
              	</tr> 
				<tr>
					<td><strong>Situação:</strong></td>
					<td><html:select property="idSituacaoParcelamento"
						onchange="javascript:verificarMotivoDesfazimento();">
						<logic:notEmpty name="colecaoSituacaoParcelamento">
							<html:options collection="colecaoSituacaoParcelamento"
								labelProperty="descricao" property="id" />
						</logic:notEmpty>
					</html:select>
					</td>
				</tr>
	
	
	
				<tr>
					<td><strong>Período de Parcelamento:</strong></td>
					<td><strong> <html:text property="dataParcelamentoInicial"
						size="10" maxlength="10" onkeypress="return isCampoNumerico(event);"
						onkeyup="mascaraData(this,event);replicaDataParcelamento();" />
					<a
						href="javascript:abrirCalendarioVerificandoBloqueio('GerarRelatorioRelacaoParcelamentoActionForm', 'dataParcelamentoInicial')">
					<img border="0"
						src="<bean:message key="caminho.imagens"/>calendario.gif"
						width="20" border="0" align="middle" alt="Exibir Calendário" /></a>
					a </strong> <html:text property="dataParcelamentoFinal" size="10"
						maxlength="10" onkeyup="mascaraData(this,event)" onkeypress="return isCampoNumerico(event);" /> <a
						href="javascript:abrirCalendarioVerificandoBloqueio('GerarRelatorioRelacaoParcelamentoActionForm', 'dataParcelamentoFinal')">
					<img border="0"
						src="<bean:message key="caminho.imagens"/>calendario.gif"
						width="20" border="0" align="middle" alt="Exibir Calendário" /></a>
					(dd/mm/aaaa)
					</td>
				</tr>
	
				<logic:present name="Desfeito" scope="request">
					<tr>
						<td><strong>Motivo de Desfazimento:</strong></td>
						<td><html:select property="idsMotivoDesfazimento"
							style="width: 350px; height:100px;" multiple="true">
							<logic:notEmpty name="colecaoMotivoDesfazimento">
								<html:option
									value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
								<html:options collection="colecaoMotivoDesfazimento"
									labelProperty="descricaoParcelamentoMotivoDesfazer"
									property="id" />
							</logic:notEmpty>
						</html:select>
						</td>
					</tr>
				</logic:present>
				
				<logic:notPresent name="Desfeito" scope="request">
					<tr>
						<td><strong>Motivo de Desfazimento:</strong></td>
						<td><html:select property="idsMotivoDesfazimento"
							disabled="true">
							<logic:notEmpty name="colecaoMotivoDesfazimento">
								<html:option
									value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
								<html:options collection="colecaoMotivoDesfazimento"
									labelProperty="descricaoParcelamentoMotivoDesfazer"
									property="id" />
							</logic:notEmpty>
						</html:select>
						</td>
					</tr>
				</logic:notPresent>
				<tr>
					<td><strong>Valor do Débito Atualizado:</strong></td>
					<td>
						<strong> 
							<html:text onkeyup="formataValorMonetario(this, 10)" 
									   onkeypress="formataValorMonetario(this, 10); return isCampoNumerico(event);" 
									   property="valorDebitoInicial" size="10" maxlength="10" /> 
							a 
							<html:text onkeyup="formataValorMonetario(this, 10)" 
									   onkeypress="formataValorMonetario(this, 10);return isCampoNumerico(event);" 
									   property="valorDebitoFinal" size="10" maxlength="10" /> 
						</strong>
					</td>
				</tr>
	
				<tr>
					<td><strong>Usuário Responsável:</strong></td>
					<td><strong> <html:text property="idUsuarioResponsavel" size="5"
						maxlength="3" disabled="true" onkeypress="return isCampoNumerico(event);"
						onkeyup="return validaEnterComMensagem(event, 'exibirGerarRelatorioRelacaoParcelamentoAction.do?pesquisarUsuarioResponsavel=sim', 'idUsuarioResponsavel', 'Usuário Responsável');" />
					<a                         
						href="javascript:chamarPopup('exibirUsuarioPesquisar.do', 'usuarioResponsavel', null, null, 400, 800, '','idUsuarioResponsavel');">
					<img border="0" title="Pesquisar Usuário Responsável" src="imagens/pesquisa.gif" height="21" width="23" /></a>
					
					<logic:present name="usuarioResponsavelInexistente"
						scope="request">
						<html:text property="descricaoUsuarioResponsavel"
							readonly="true"
							style="background-color:#EFEFEF; border:0; color: #ff0000"
							size="40" maxlength="40" />
					</logic:present> <logic:notPresent
						name="usuarioResponsavelInexistente" scope="request">
						<html:text property="descricaoUsuarioResponsavel"
							readonly="true" style="background-color:#EFEFEF; border:0"
							size="40" maxlength="40" />
					</logic:notPresent> <a
						href="javascript:limparUsuarioResponsavel();"> <img
						src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar" /> </a> </strong>
					</td>
				</tr>
				
				<tr>
					<td colspan="2" class="style1">
					<hr>
					</td>
				</tr>
				
				<tr>	
					<td colspan="2" align="center"><strong>Dados do Pagamento Cartão de Crédito:</strong></td>							
				</tr>
				
				<tr>
					<td>&nbsp;</td>
					<td colspan="2" align="right">&nbsp;</td>
				</tr>
				
				<tr>
					<td><strong>Período de Confirmação:</strong></td>
					<td><strong> <html:text property="dataConfirmacaoInicial"
						size="10" maxlength="10" onkeypress="return isCampoNumerico(event);"
						onkeyup="mascaraData(this,event);replicaDataConfirmacao();" />
					<a
						href="javascript:abrirCalendarioVerificandoBloqueio('GerarRelatorioRelacaoParcelamentoActionForm', 'dataConfirmacaoInicial')">
					<img border="0"
						src="<bean:message key="caminho.imagens"/>calendario.gif"
						width="20" border="0" align="middle" alt="Exibir Calendário" /></a>
					a </strong> <html:text property="dataConfirmacaoFinal" size="10"
						maxlength="10" onkeyup="mascaraData(this,event)" onkeypress="return isCampoNumerico(event);" /> <a
						href="javascript:abrirCalendarioVerificandoBloqueio('GerarRelatorioRelacaoParcelamentoActionForm', 'dataConfirmacaoFinal')">
					<img border="0"
						src="<bean:message key="caminho.imagens"/>calendario.gif"
						width="20" border="0" align="middle" alt="Exibir Calendário" /></a>
					(dd/mm/aaaa)
					</td>
				</tr>
				
				<tr>
					<td><strong>Usuário da Confirmação:</strong></td>
					<td><strong> <html:text property="idUsuarioConfirmacao" size="5"
						maxlength="3" onkeypress="return isCampoNumerico(event);"
						onkeyup="return validaEnterComMensagem(event, 'exibirGerarRelatorioRelacaoParcelamentoAction.do?pesquisarUsuarioConfirmacao=sim', 'idUsuarioConfirmacao', 'Usuário Confirmação');" />
					<a
						href="javascript:chamarPopup('exibirUsuarioPesquisar.do', 'usuarioConfirmacao', null, null, 400, 800, '','idUsuarioConfirmacao');">
					<img border="0" title="Pesquisar Usuário Responsável" src="imagens/pesquisa.gif" height="21" width="23" /></a>
	
					<logic:present name="usuarioConfirmacaoInexistente"
						scope="request">
						<html:text property="descricaoUsuarioConfirmacao"
							readonly="true"
							style="background-color:#EFEFEF; border:0; color: #ff0000"
							size="40" maxlength="40" />
					</logic:present> <logic:notPresent
						name="usuarioConfirmacaoInexistente" scope="request">
						<html:text property="descricaoUsuarioConfirmacao"
							readonly="true" style="background-color:#EFEFEF; border:0"
							size="40" maxlength="40" />
					</logic:notPresent> <a
						href="javascript:limparUsuarioConfirmacao();"> <img
						src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar" /> </a> </strong>
					</td>
				</tr>
				
				<tr>
					<td><strong> Confirmado pela operadora?</strong></td>
					<td colspan="2" align="right">
						<div align="left">
							<label> 
						 	<input type="radio" onclick="javascript:document.forms[0].indicadorConfirmacaoOperadora.value = 1;validarExibicaoDataConfirmacaoOperadora(1);"
							 onclick="validarExibicaoDataConfirmacaoOperadora(this)" name="indicadorConfirmacaoOperadora" id="radio1" value="1"/>
							Sim 
							</label> 
							<label> 
							<input type="radio" onclick="javascript:document.forms[0].indicadorConfirmacaoOperadora.value = 2;validarExibicaoDataConfirmacaoOperadora(2);"
							 name="indicadorConfirmacaoOperadora" id="radio2" value="2"/>
							Não 
							</label>
						</div>
					</td>		
				</tr>
				
				<tr>
					<td><strong>Período de Confirmação da Operadora:</strong></td>
					<td><strong> <html:text property="dataConfirmacaoOperadoraInicial"
						size="10" maxlength="10" onkeypress="return isCampoNumerico(event);"
						onkeyup="mascaraData(this,event);replicaDataConfirmacaoOperadora();" />
					<a
						href="javascript:abrirCalendarioVerificandoBloqueio('GerarRelatorioRelacaoParcelamentoActionForm', 'dataConfirmacaoOperadoraInicial')">
					<img border="0"
						src="<bean:message key="caminho.imagens"/>calendario.gif"
						width="20" border="0" align="middle" alt="Exibir Calendário" /></a>
					a </strong> <html:text property="dataConfirmacaoOperadoraFinal" size="10"
						maxlength="10" onkeyup="mascaraData(this,event)" onkeypress="return isCampoNumerico(event);" /> <a
						href="javascript:abrirCalendarioVerificandoBloqueio('GerarRelatorioRelacaoParcelamentoActionForm', 'dataConfirmacaoOperadoraFinal')">
					<img border="0"
						src="<bean:message key="caminho.imagens"/>calendario.gif"
						width="20" border="0" align="middle" alt="Exibir Calendário" /></a>
					(dd/mm/aaaa)</td>
				</tr>

				<tr>
					<td>&nbsp;</td>
					<td colspan="2" align="right">
					<div align="left"><strong> </strong></div>
					</td>
				</tr>
	
				<tr>
					<td>
					<div align="left"><input type="button" name="limpar"
					class="bottonRightCol" value="Limpar" onclick="javascript:limparForm();"></div>
					</td>
					
					<td>
					<div align="right"><input type="button" name="botaoConcluir"
					class="bottonRightCol" value="Filtrar" onclick="javascript:botaoAvancarTelaEspera('/gsan/gerarRelatorioRelacaoParcelamentoAction.do');"></div>
					</td>
				</tr>
			</table>
	
			<p>&nbsp;</p>
			</td>
		</tr>
	
		<%@ include file="/jsp/util/rodape.jsp"%>
	</table>
	
	<script language="JavaScript">  	  	  mudarStatusCamposRelatorio();
	</script>
	
	</html:form>
</div>

<%@ include file="/jsp/util/telaespera.jsp"%>
</body>
</html:html>
