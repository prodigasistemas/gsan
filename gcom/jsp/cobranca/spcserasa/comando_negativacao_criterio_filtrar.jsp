<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<%@ page import="gcom.util.Util" %>
<%@ page import="gcom.util.ConstantesSistema" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
<head>
<%@ include file="/jsp/util/titulo.jsp"%>

<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">

<html:javascript staticJavascript="false"  formName="FiltrarComandoNegativacaoTipoCriterioActionForm" />

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js" ></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js" ></script>
<script>
<!--
function chamarPopup(url, tipo, objeto, codigoObjeto, altura, largura, msg,objetoRelacionado, campo){
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

function habilitarPesquisaCliente(form) {
	if (form.codigoCliente.readOnly == false) {
		form.tipoPesquisa.value = 'cliente';
		chamarPopup('exibirPesquisarClienteAction.do', 'cliente', null, null, 275, 480, '',form.codigoCliente.value);
	}	
}

function habilitarPesquisaLocalidadeFinal(form) {
	if (form.codigoLocalidadeFinal.readOnly == false) {
		form.tipoPesquisa.value = 'localidadeFinal';
		chamarPopup('exibirPesquisarLocalidadeAction.do', 'localidade', null, null, 275, 480, '',form.codigoLocalidadeFinal.value);
	}	
}

function habilitarPesquisaLocalidadeInicial(form) {
	if (form.codigoLocalidadeInicial.readOnly == false) {
		form.tipoPesquisa.value = 'localidadeInicial';
		chamarPopup('exibirPesquisarLocalidadeAction.do', 'localidade', null, null, 275, 480, '',form.codigoLocalidadeInicial.value);
	}	
}

function habilitarPesquisaSetorComercialInicial(form) {
	if (form.codigoSetorComercialInicial.disabled == false){
		if (form.codigoSetorComercialInicial.readOnly == false) {
			form.tipoPesquisa.value = 'setorComercialInicial';
			chamarPopup('exibirPesquisarSetorComercialAction.do', 'setorComercial', null, null, 275, 480, '',form.codigoSetorComercialInicial.value);
		}
	}

}

function habilitarPesquisaSetorComercialFinal(form) {
	if (form.codigoSetorComercialFinal.disabled == false){
		if (form.codigoSetorComercialFinal.readOnly == false) {
			form.tipoPesquisa.value = 'setorComercialFinal';
			chamarPopup('exibirPesquisarSetorComercialAction.do', 'setorComercial', null, null, 275, 480, '',form.codigoSetorComercialFinal.value);
		}
	}	
}

function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
	var form = document.forms[0];
	if (tipoConsulta == 'cliente') {
    	form.codigoCliente.value = codigoRegistro;
       	form.action = 'exibirFiltrarComandoNegativacaoTipoCriterioAction.do';
        form.submit(); 
    }
    if (tipoConsulta == 'localidade') {
		if(form.tipoPesquisa.value == 'localidadeInicial'){
      		form.codigoLocalidadeInicial.value = codigoRegistro;
	  		form.descricaoLocalidadeInicial.value = descricaoRegistro;
	  		form.descricaoLocalidadeInicial.style.color = "#000000";
	  		if(form.codigoLocalidadeFinal.value == ""){
      		  form.codigoLocalidadeFinal.value = codigoRegistro;
	  		  form.descricaoLocalidadeFinal.value = descricaoRegistro;
	  		  form.descricaoLocalidadeFinal.style.color = "#000000";
	  	    }				
	    }else{
      	  	form.codigoLocalidadeFinal.value = codigoRegistro;
	  	  	form.descricaoLocalidadeFinal.value = descricaoRegistro;
	  	  	form.descricaoLocalidadeFinal.style.color = "#000000";	
	    }
	    verificarSetorComercial();
    }
    if (tipoConsulta == 'setorComercial') {
    	if(form.tipoPesquisa.value == 'setorComercialInicial'){
    		form.codigoSetorComercialInicial.value = codigoRegistro;
	  		//form.descricaoSetorComercialInicial.value = descricaoRegistro;
	  		//form.descricaoSetorComercialInicial.style.color = "#000000";
	  		
	  		form.action = 'exibirFiltrarComandoNegativacaoTipoCriterioAction.do';
        	form.submit(); 
	  		if(form.codigoSetorComercialFinal.value == ""){
      		  form.codigoSetorComercialFinal.value = codigoRegistro;
	  		  //form.descricaoSetorComercialFinal.value = descricaoRegistro;
	  		  //form.descricaoSetorComercialFinal.style.color = "#000000";
	  		  form.action = 'exibirFiltrarComandoNegativacaoTipoCriterioAction.do';
        	  form.submit(); 
	  	    }				
	    }else{
      	  	form.codigoSetorComercialFinal.value = codigoRegistro;
	 // 	  	form.descricaoSetorComercialFinal.value = descricaoRegistro;
	 // 	  	form.descricaoSetorComercialFinal.style.color = "#000000";	
	 		form.action = 'exibirFiltrarComandoNegativacaoTipoCriterioAction.do';
        	form.submit(); 
	    }
    }
}

function limparForm(tipo){
    var form = document.forms[0];
 	if(tipo == 'cliente')
    {
		var ObjCodigoCliente = returnObject(form,"codigoCliente");
		var ObjNomeCliente = returnObject(form,"nomeCliente");
		ObjCodigoCliente.value = "";
		ObjNomeCliente.value = "";
		form.nomeCliente.value = "";
		verificarCliente();
	}
	if(tipo == 'localidadeInicial')
    {
		form.codigoLocalidadeInicial.value = "";
		form.descricaoLocalidadeInicial.value = "";
		verificarSetorComercial();

	}
	if(tipo == 'localidadeFinal')
    {
		form.codigoLocalidadeFinal.value = "";
		form.descricaoLocalidadeFinal.value = "";
		verificarSetorComercial();
	}
	if(tipo == 'setorComercialInicial')
    {
		form.codigoSetorComercialInicial.value = "";
		form.descricaoSetorComercialInicial.value = "";
	}
	if(tipo == 'setorComercialFinal')
    {
		form.codigoSetorComercialFinal.value = "";
		form.descricaoSetorComercialFinal.value = "";
	}
}

function validaEnterCliente(tecla, caminhoActionReload, nomeCampo) {
	var form = document.forms[0];
	validaEnterComMensagem(tecla, caminhoActionReload, nomeCampo, "Código do Cliente");
}

function validaEnterLocalidadeInicial(tecla, caminhoActionReload, nomeCampo) {
	var form = document.forms[0];
	validaEnterComMensagem(tecla, caminhoActionReload, nomeCampo, "Código da Localidade Inicial");
}

function validaEnterLocalidadeFinal(tecla, caminhoActionReload, nomeCampo) {
	var form = document.forms[0];
	validaEnterComMensagem(tecla, caminhoActionReload, nomeCampo, "Código da Localidade Final");
}

function validaEnterSetorComercialInicial(tecla, caminhoActionReload, nomeCampo) {
	var form = document.forms[0];
	validaEnterComMensagem(tecla, caminhoActionReload, nomeCampo, "Código do Setor Comercial Inicial");
}

function validaEnterSetorComercialFinal(tecla, caminhoActionReload, nomeCampo) {
	var form = document.forms[0];
	validaEnterComMensagem(tecla, caminhoActionReload, nomeCampo, "Código do Setor Comercial Final");
}

function validaForm(){
   	var form = document.FiltrarComandoNegativacaoActionForm;
	form.submit();
}

function testeHabilitarCampos(){
	var form = document.forms[0];
	form.codigoSetorComercialInicial.disabled = true;
	form.codigoSetorComercialFinal.disabled = true;

	if (retiraEspacos(form.codigoLocalidadeInicial.value).length > 0){
		if (form.codigoLocalidadeInicial.value == form.codigoLocalidadeFinal.value){
			form.codigoSetorComercialInicial.disabled = false;
			form.codigoSetorComercialFinal.disabled = false;
		}
	}
	apagarCamposSetorComercial();
}

function apagarCamposSetorComercial(){
	var form = document.forms[0];
	if (form.codigoSetorComercialInicial.disabled){
		form.codigoSetorComercialInicial.value = "";
		form.descricaoSetorComercialInicial.value = "";
		
		form.codigoSetorComercialFinal.value = "";
		form.descricaoSetorComercialFinal.value = "";
	}
}

function retiraEspacos(string) {
    var i = 0;
    var final = '';
    while (i < string.length) {
        if (string.charAt(i) == ' ') {
            final += string.substr(0, i);
            string = string.substr(i+1, string.length - (i+1));
            i = 0;
        }
        else {
            i++;
        }
    }
    return final + string;
}




function desabilitarLocalizacaoGeografica(){
	var form = document.forms[0];	
	
		form.idGerenciaRegional.selectedIndex = -1 ;
		form.idGerenciaRegional.disabled = true ;	
		
		form.idUnidadeNegocio.selectedIndex = -1 ;
		form.idUnidadeNegocio.disabled = true ;	
		
		form.idEloPolo.selectedIndex = -1 ;
		form.idEloPolo.disabled = true ;
	
		form.codigoLocalidadeInicial.value = "" ;
		form.codigoLocalidadeInicial.disabled = true;			
		
		form.codigoLocalidadeFinal.value = "" ;	
		form.codigoLocalidadeFinal.disabled = true;
		
		form.codigoSetorComercialInicial.value = "";	
		form.codigoSetorComercialInicial.disabled = true;	
		
		form.codigoSetorComercialFinal.value = "" ;		
		form.codigoSetorComercialFinal.disabled = true;

}


function habilitarLocalizacaoGeografica(){
	var form = document.forms[0];	
		form.idGerenciaRegional.disabled = false ;		
	    form.idUnidadeNegocio.disabled = false ;	    
		form.idEloPolo.disabled = false ;		
		form.codigoLocalidadeInicial.disabled = false;				
		form.codigoLocalidadeFinal.disabled = false;
		form.codigoSetorComercialInicial.disabled = false;				
		form.codigoSetorComercialFinal.disabled = false;
}


function verificarCliente(){
	var form = document.forms[0];	

	if(form.codigoCliente.value != ""){
		
		form.idGrupoCobranca.selectedIndex = 0;	
	  	form.idGrupoCobranca.disabled = true; 
	  	desabilitarLocalizacaoGeografica();	
	}else{
		
	  	form.idGrupoCobranca.disabled = false; 
	    habilitarLocalizacaoGeografica();
	}    
}



function verificarGrupoCobranca(){
	var form = document.forms[0];
	   
	if(form.idGrupoCobranca.value != -1 && form.idGrupoCobranca.value != ""){		
		
	  form.idGerenciaRegional.disabled = true;
	  form.idUnidadeNegocio.disabled = true;
	  form.idEloPolo.disabled = true;
	  
	  form.codigoLocalidadeInicial.value = "" ;
	  form.codigoLocalidadeInicial.disabled = true;	
	  
	  form.codigoLocalidadeFinal.value = "" ;
	  form.codigoLocalidadeFinal.disabled = true;	
	  
	  verificarSetorComercial();		  
		            
	}else{
		
	  form.idGerenciaRegional.disabled = false;
	  form.idUnidadeNegocio.disabled = false;
	  form.idEloPolo.disabled = false;
	  
	  form.codigoLocalidadeInicial.value = "" ;
	  form.codigoLocalidadeInicial.disabled = false;	
	  
	  form.codigoLocalidadeFinal.value = "" ;
	  form.codigoLocalidadeFinal.disabled = false;	
	  
	  verificarSetorComercial();	  
	 
	}    
}




function desabilitarGrupoCobranca(){
	var form = document.forms[0];	
		
	if(form.idGerenciaRegional.value != -1 ||
		form.idUnidadeNegocio.value != -1 ||
		form.idEloPolo.value != -1 ||
		form.codigoLocalidadeInicial.value != "" ||
		form.codigoSetorComercialInicial.value != "" ||
		form.codigoLocalidadeFinal.value != "" ||
		form.codigoSetorComercialFinal.value != "" ){
	
	  	form.idGrupoCobranca.disabled = true; 
	}
	
	if( form.idGerenciaRegional.value == -1  &&
		form.idUnidadeNegocio.value == -1  &&
		form.idEloPolo.value == -1  &&
		form.codigoLocalidadeInicial.value == '' &&
		form.codigoSetorComercialInicial.value == '' &&
		form.codigoLocalidadeFinal.value == '' &&
		form.codigoSetorComercialFinal.value == ''){

	    form.idGrupoCobranca.disabled = false; 
	}
	
	
	
	if(form.codigoCliente.value != ""){
	   
	   verificarCliente();
	}
	

}

function verificarSetorComercial(){
	var form = document.forms[0];	
	
	
   	if(retiraEspacos(form.codigoLocalidadeInicial.value) != ""){
	  if(retiraEspacos(form.codigoLocalidadeInicial.value) == retiraEspacos(form.codigoLocalidadeFinal.value)){
	    form.codigoSetorComercialInicial.disabled = false;
	    form.codigoSetorComercialFinal.disabled = false;  
	    desabilitarGrupoCobranca(); 
		}else{
		limparSetorComercialInicial();
		limparSetorComercialFinal();
	    form.codigoSetorComercialInicial.disabled = true;
	    form.codigoSetorComercialFinal.disabled = true;			  
	  }
	}else{
		limparSetorComercialInicial();
		limparSetorComercialFinal();		
	    form.codigoSetorComercialInicial.disabled = true;
	    form.codigoSetorComercialFinal.disabled = true;			  
	}
}

function limparSetorComercialInicial() {
	var form = document.forms[0];		
    form.codigoSetorComercialInicial.value = '';
	form.descricaoSetorComercialInicial.value = '';		
	desabilitarGrupoCobranca();
}	

function limparSetorComercialFinal() {
	var form = document.forms[0];		
    form.codigoSetorComercialFinal.value = '';
	form.descricaoSetorComercialFinal.value = '';	
	desabilitarGrupoCobranca();	
}

function validaMesAno(mesAno){	
	if(mesAno.value != ""){
		return verificaAnoMesMensagemPersonalizada(mesAno,"Período de Referência do Débito Inválido");
	}else{
		return true;
	}
}
function validarPeriodoVencimento(data){
	if (data.value.length > 0){
		return verificaDataMensagemPersonalizada(data, "Período de Vencimentodo Débito inválido.");				
	}else{
		return true;		
	}
}

function verificaLocalidadeInicial(){
	var form = document.forms[0];		
	if (retiraEspacos(form.codigoLocalidadeInicial.value) == "" || retiraEspacos(form.codigoLocalidadeInicial.value) == null){
		form.descricaoLocalidadeInicial.value = "";
		form.codigoLocalidadeFinal.value = "";
		form.descricaoLocalidadeFinal.value = "";
	}
}

function verificaSetorComercialInicial(){
	var form = document.forms[0];		
	if (retiraEspacos(form.codigoSetorComercialInicial.value) == "" || retiraEspacos(form.codigoSetorComercialInicial.value) == null){
		form.descricaoSetorComercialInicial.value = "";
		form.codigoSetorComercialFinal.value = "";
		form.descricaoSetorComercialFinal.value = "";
	}
}

function verificaGeracaoComandoDataInicial(){
alert(form.geracaoComandoDataInicial.value);
	var form = document.forms[0];		
	if (retiraEspacos(form.geracaoComandoDataInicial.value) == "" || retiraEspacos(form.geracaoComandoDataInicial.value) == null){
		form.geracaoComandoDataInicialFinal.value = "";
	}
}

function verificaExecucaoComandoDataInicial(){
	var form = document.forms[0];		
	if (retiraEspacos(form.execucaoComandoDataInicial.value) == "" || retiraEspacos(form.execucaoComandoDataInicial.value) == null){
		form.execucaoComandoDataInicialFinal.value = "";
	}
}

function verificaReferenciaDebitoDataInicial(){
	var form = document.forms[0];		
	if (retiraEspacos(form.referenciaDebitoDataInicial.value) == "" || retiraEspacos(form.referenciaDebitoDataInicial.value) == null){
		form.referenciaDebitoDataFinal.value = "";
	}
}

function verificacVencimentoDebitoDataInicial(){
	var form = document.forms[0];		
	if (retiraEspacos(form.vencimentoDebitoDataInicial.value) == "" || retiraEspacos(form.vencimentoDebitoDataInicial.value) == null){
		form.vencimentoDebitoDataFinal.value = "";
	}
}

function filtrar(){
	var form = document.forms[0];
	form.submit();		
}

function verificaCompatibilidadeLocalidadeInicial(){
	var form = document.forms[0];
	if (form.localidadeInicialIncompativel.value == "true"){
		alert('A Localidade do Setor Comercial selecionado não compatível com a Localidade já escolhida');
		limparSetorComercialInicial();
	}
}




-->
</script>
</head>

<body leftmargin="5" topmargin="5" onload="setarFoco('${requestScope.nomeCampo}');verificarCliente();verificarSetorComercial();">
<div id="formDiv">
<html:form
	action="filtrarComandoNegativacaoTipoCriterioAction"    
	name="FiltrarComandoNegativacaoTipoCriterioActionForm"
  	type="gcom.gui.cobranca.spcserasa.FiltrarComandoNegativacaoTipoCriterioActionForm"
  	method="post">

<%@ include file="/jsp/util/cabecalho.jsp"%>
<%@ include file="/jsp/util/menu.jsp" %>

<input type="hidden" name="tipoPesquisa"/>
<input type="hidden" name="tipoLocalidade" />
<input type="hidden" name="tipoSetorComercial" />
<input type="hidden" name="tipoInicialFinal" />
<html:hidden property="localidadeInicialIncompativel"/>

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
		<td width="613" valign="top" class="centercoltext">
			<!--Início Tabela Reference a Páginação da Tela de Processo-->

              <table>
                <tr> 
                  <td></td>
                </tr>
              </table>
              <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
                <tr> 
                  <td width="11"><img border="0" src="<bean:message key='caminho.imagens'/>parahead_left.gif"/></td>
                  <td class="parabg">Filtrar Comandos de Negativa&ccedil;&atilde;o - Por Crit&eacute;rio</td>

                  <td width="11" valign="top"><img border="0" src="<bean:message key='caminho.imagens'/>parahead_right.gif"/></td>
                </tr>
              </table> 
              <!--Fim Tabela Reference a Páginação da Tela de Processo-->
              <p>&nbsp;</p>
              <table width="100%" border="0" dwcopytype="CopyTableRow">
                <tr>
                  <td colspan="3">Para filtrar o(s) comando(s) de negativa&ccedil;&atilde;o,  informe os dados abaixo:</td>

                </tr>
                <tr>
                  <td><strong>Negativador<span class="style5">:</span></strong></td>
                  <td colspan="2"><strong>
                    <logic:present name="colecaoNegativador">  
                   	   <html:select property="idNegativador" tabindex="7">
						   <selected><html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option> </selected>
							<logic:present name="colecaoNegativador">
								<html:options collection="colecaoNegativador" labelProperty="cliente.nome" property="id"/>
							</logic:present>
						</html:select>
                	</logic:present>  
                  <b> </b> <b> </b> </strong></td>
                </tr>
                <tr>
                  <td colspan="3"><hr>                  </td>

                </tr>
                <tr>
                  <td><strong>T&iacute;tulo:</strong></td>
                  <td colspan="2"><p>
                    <html:textarea property="titulo" cols="50" rows="2"></html:textarea>
                  </p>
                  <p>
                  	<html:radio property="tipoPesquisaTitulo" value="1" disabled="false" />
					Iniciando pelo texto
                  	<html:radio property="tipoPesquisaTitulo" value="2" disabled="false" />
					Contendo o texto </p></td>
                </tr>
                <tr> 
                  <td width="242"><strong>Comando Simulado:</strong></td>
                  <td colspan="2"><strong>					
					<html:radio property="comandoSimulado" value="1" disabled="false" />
                    <strong> Sim
					<html:radio property="comandoSimulado" value="2" disabled="false" />
                    <strong>N&atilde;o
                    <html:radio property="comandoSimulado" value="3" disabled="false" />
                    <strong> Todos </strong>
                    </strong></strong></strong></td>
                </tr>
                <tr>
                  <td colspan="3"><hr>                  </td>

                </tr>
                <tr>
                  <td><strong>Cliente:</strong></td>
                  <td colspan="2"><strong><b><span class="style4">
                    <html:text property="codigoCliente" maxlength="9" size="9" onkeyup="return validaEnterCliente(event, 'exibirFiltrarComandoNegativacaoTipoCriterioAction.do', 'codigoCliente'); " onkeypress="return isCampoNumerico(event);" />
					<a href="javascript:habilitarPesquisaCliente(document.forms[0]);" alt="Pesquisar Cliente">
					<img width="23" height="21" src="<bean:message key='caminho.imagens'/>pesquisa.gif" border="0" /></a>
					<logic:present name="corCliente">
						<logic:equal name="corCliente" value="exception">
							<html:text property="nomeCliente" size="38"	readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:equal>
						<logic:notEqual name="corCliente" value="exception">
							<html:text property="nomeCliente" size="38"	readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEqual>
					</logic:present> 
					<logic:notPresent name="corCliente">
						<logic:empty name="FiltrarComandoNegativacaoTipoCriterioActionForm"	property="codigoCliente">
							<html:text property="nomeCliente" size="38" value="" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:empty>
						<logic:notEmpty name="FiltrarComandoNegativacaoTipoCriterioActionForm" property="codigoCliente">
							<html:text property="nomeCliente" size="38"	readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEmpty>
					</logic:notPresent>
					<a href="javascript:limparForm('cliente');"> 
						<img border="0" src="<bean:message key='caminho.imagens'/>limparcampo.gif" style="cursor: hand;" /> 
					</a>
                </tr>
                <tr>
                  <td><STRONG>Tipo de Rela&ccedil;&atilde;o:</STRONG></td>
                  <td colspan="2"><strong>
                    <logic:present name="colecaoTipoRelacao">  
                    	<html:select property="idTipoRelacao" tabindex="7" disabled="false">
							 <selected><html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option> </selected>
							<logic:present name="colecaoTipoRelacao">
								<html:options collection="colecaoTipoRelacao" labelProperty="descricao" property="id"/>
							</logic:present>
						</html:select>
                	</logic:present>  

                    <b> </b> <b> </b> <b> </b> <b> </b> </strong></td>

                </tr>
                <tr>
                  <td colspan="3"><hr>                  </td>
                </tr>
                <tr>
                  <td><strong> Grupo de Cobran&ccedil;a:<span class="style5"></span></strong></td>
                  <td colspan="2"><strong>
					<logic:present name="colecaoGrupoCobranca">  
                    	<html:select property="idGrupoCobranca" tabindex="7" disabled="false" multiple="true" size="3" onchange="javascript:verificarGrupoCobranca();">
							 <selected><html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option> </selected>
							<logic:present name="colecaoGrupoCobranca">					
								<html:options collection="colecaoGrupoCobranca" labelProperty="descricao" property="id"/>
							</logic:present>
						</html:select>
                	</logic:present> 
                  </strong></td>
                </tr>
                <tr>
                  <td colspan="3"><hr>                  </td>
                </tr>
                <tr>
                  <td><strong>Ger&ecirc;ncia Regional:</strong></td>

                  <td colspan="2"><strong>
                    <logic:present name="colecaoGerenciaRegional">  
                    	<html:select property="idGerenciaRegional" tabindex="7" disabled="false" onchange="javascript:desabilitarGrupoCobranca();" multiple="true" size="3">
							 <selected><html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option> </selected>
							<logic:present name="colecaoGerenciaRegional">
								<html:options collection="colecaoGerenciaRegional" labelProperty="nome" property="id"/>
							</logic:present>
						</html:select>
                	</logic:present> 
                  </strong></td>
                </tr>
                <tr>
                  <td><strong>Unidade Neg&oacute;cio<span class="style5">:</span></strong></td>
                  <td colspan="2"><strong>
                    <logic:present name="colecaoUnidadeNegocio">  
                    	<html:select property="idUnidadeNegocio" tabindex="7" disabled="false" onchange="desabilitarGrupoCobranca();" multiple="true" size="3">
							 <selected><html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option> </selected>
							<logic:present name="colecaoUnidadeNegocio">
								<html:options collection="colecaoUnidadeNegocio" labelProperty="nome" property="id"/>
							</logic:present>
						</html:select>
                	</logic:present> 
                  </strong></td>
                </tr>
                <tr>
                  <td><strong>Localidade Pólo:<span class="style5">:</span></strong></td>
                  <td colspan="2"><strong>
                    <logic:present name="colecaoEloPolo">  
                    	<html:select property="idEloPolo" tabindex="7" disabled="false" onchange="desabilitarGrupoCobranca();" multiple="true" size="3">
							 <selected><html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option> </selected>
							<logic:present name="colecaoEloPolo">
								<html:options collection="colecaoEloPolo" labelProperty="descricao" property="id"/>
							</logic:present>
						</html:select>
                	</logic:present>
                  </strong></td>
                </tr>
                <tr>

                  <td colspan="3"><hr>                  </td>
                </tr>
                <tr>
                  <td><STRONG>Localidade Inicial:</STRONG></td>
                  <td colspan="2"><strong><b><span class="style4">
                    <html:text property="codigoLocalidadeInicial" maxlength="3" size="4" 
                    	onkeypress="javascript:validaEnterLocalidadeInicial(event, 'exibirFiltrarComandoNegativacaoTipoCriterioAction.do', 'codigoLocalidadeInicial'); return isCampoNumerico(event);" 
                    	onkeyup="replicarCampo(document.forms[0].codigoLocalidadeFinal,this);verificaLocalidadeInicial();desabilitarGrupoCobranca(); verificarSetorComercial();"/>
					<a href="javascript:habilitarPesquisaLocalidadeInicial(document.forms[0]);" alt="Pesquisar localidade Inicial">
					<img width="23" height="21" src="<bean:message key='caminho.imagens'/>pesquisa.gif" border="0" /></a>
					<logic:present name="corLocalidadeInicial">
						<logic:equal name="corLocalidadeInicial" value="exception">
							<html:text property="descricaoLocalidadeInicial" size="40"	readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:equal>
						<logic:notEqual name="corLocalidadeInicial" value="exception">
							<html:text property="descricaoLocalidadeInicial" size="40"	readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEqual>
					</logic:present> 
					<logic:notPresent name="corLocalidadeInicial">
						<logic:empty name="FiltrarComandoNegativacaoTipoCriterioActionForm"	property="codigoLocalidadeInicial">
							<html:text property="descricaoLocalidadeInicial" size="40" value="" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:empty>
						<logic:notEmpty name="FiltrarComandoNegativacaoTipoCriterioActionForm" property="codigoLocalidadeInicial">
							<html:text property="descricaoLocalidadeInicial" size="40"	readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEmpty>
					</logic:notPresent>
					<a href="javascript:limparForm('localidadeInicial');"> 
						<img border="0" src="<bean:message key='caminho.imagens'/>limparcampo.gif" style="cursor: hand;" /> 
					</a>
				  </td>
                </tr>
                <tr>
                  <td><STRONG>Setor Comercial Inicial:</STRONG></td>
                  <td colspan="2"><strong><b><span class="style4">
				  	<html:text property="codigoSetorComercialInicial" maxlength="3" size="4" onkeypress="javascript:validaEnterSetorComercialInicial(event, 'exibirFiltrarComandoNegativacaoTipoCriterioAction.do', 'codigoSetorComercialInicial'); return isCampoNumerico(event);" 
				  		onkeyup="replicarCampo(document.forms[0].codigoSetorComercialFinal,this);desabilitarGrupoCobranca();"/>
					<a href="javascript:habilitarPesquisaSetorComercialInicial(document.forms[0]);" alt="Pesquisar Setor Comercial Inicial">
					<img width="23" height="21" src="<bean:message key='caminho.imagens'/>pesquisa.gif" border="0" /></a>
					<logic:present name="corSetorComercialInicial">
						<logic:equal name="corSetorComercialInicial" value="exception">
							<html:text property="descricaoSetorComercialInicial" size="40"	readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:equal>
						<logic:notEqual name="corSetorComercialInicial" value="exception">
							<html:text property="descricaoSetorComercialInicial" size="40"	readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEqual>
					</logic:present> 
					<logic:notPresent name="corSetorComercialInicial">
						<logic:empty name="FiltrarComandoNegativacaoTipoCriterioActionForm"	property="codigoSetorComercialInicial">
							<html:text property="descricaoSetorComercialInicial" size="40" value="" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:empty>
						<logic:notEmpty name="FiltrarComandoNegativacaoTipoCriterioActionForm" property="codigoSetorComercialInicial">
							<html:text property="descricaoSetorComercialInicial" size="40"	readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEmpty>
					</logic:notPresent>
					<a href="javascript:limparForm('setorComercialInicial');"> 
						<img border="0" src="<bean:message key='caminho.imagens'/>limparcampo.gif" style="cursor: hand;" /> 
					</a>
				  </td>
                </tr>
                <tr>
                	<td><STRONG>Localidade Final:</STRONG></td>
                  	<td colspan="2"><strong><b><span class="style4">
						<html:text property="codigoLocalidadeFinal" maxlength="3" size="4" 
						onkeypress="javascript:validaEnterLocalidadeFinal(event, 'exibirFiltrarComandoNegativacaoTipoCriterioAction.do', 'codigoLocalidadeFinal'); return isCampoNumerico(event);"
						onkeyup="verificarSetorComercial();desabilitarGrupoCobranca();" />
					<a href="javascript:habilitarPesquisaLocalidadeFinal(document.forms[0]);" alt="Pesquisar localidade Final">
					<img width="23" height="21" src="<bean:message key='caminho.imagens'/>pesquisa.gif" border="0" /></a>
					<logic:present name="corLocalidadeFinal">
						<logic:equal name="corLocalidadeFinal" value="exception">
							<html:text property="descricaoLocalidadeFinal" size="40"	readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:equal>
						<logic:notEqual name="corLocalidadeFinal" value="exception">
							<html:text property="descricaoLocalidadeFinal" size="40"	readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEqual>
					</logic:present> 
					<logic:notPresent name="corLocalidadeFinal">
						<logic:empty name="FiltrarComandoNegativacaoTipoCriterioActionForm"	property="codigoLocalidadeFinal">
							<html:text property="descricaoLocalidadeFinal" size="40" value="" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:empty>
						<logic:notEmpty name="FiltrarComandoNegativacaoTipoCriterioActionForm" property="codigoLocalidadeFinal">
							<html:text property="descricaoLocalidadeFinal" size="40"	readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEmpty>
					</logic:notPresent>
					<a href="javascript:limparForm('localidadeFinal');"> 
						<img border="0" src="<bean:message key='caminho.imagens'/>limparcampo.gif" style="cursor: hand;" /> 
					</a>                    
                 	</td>
                </tr>
                
                <tr>
                  <td><STRONG>Setor Comercial Final:</STRONG></td>
                  <td colspan="2"><strong><b><span class="style4">
						<html:text property="codigoSetorComercialFinal" maxlength="3" size="4" onkeypress="javascript:validaEnterSetorComercialFinal(event, 'exibirFiltrarComandoNegativacaoTipoCriterioAction.do', 'codigoSetorComercialFinal'); return isCampoNumerico(event);" 
							onkeyup="desabilitarGrupoCobranca();"/>
					<a href="javascript:habilitarPesquisaSetorComercialFinal(document.forms[0]);" alt="Pesquisar Setor Comercial Final">
					<img width="23" height="21" src="<bean:message key='caminho.imagens'/>pesquisa.gif" border="0" /></a>
					<logic:present name="corSetorComercialFinal">
						<logic:equal name="corSetorComercialFinal" value="exception">
							<html:text property="descricaoSetorComercialFinal" size="40"	readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:equal>
						<logic:notEqual name="corSetorComercialFinal" value="exception">
							<html:text property="descricaoSetorComercialFinal" size="40"	readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEqual>
					</logic:present> 
					<logic:notPresent name="corSetorComercialFinal">
						<logic:empty name="FiltrarComandoNegativacaoTipoCriterioActionForm"	property="codigoSetorComercialFinal">
							<html:text property="descricaoSetorComercialFinal" size="40" value="" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:empty>
						<logic:notEmpty name="FiltrarComandoNegativacaoTipoCriterioActionForm" property="codigoSetorComercialFinal">
							<html:text property="descricaoSetorComercialFinal" size="40"	readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEmpty>
					</logic:notPresent>
					<a href="javascript:limparForm('setorComercialFinal');"> 
						<img border="0" src="<bean:message key='caminho.imagens'/>limparcampo.gif" style="cursor: hand;" /> 
					</a>                  
                  
                  </td>
                </tr>
                <tr>
                  <td colspan="3"><hr>                  </td>
                </tr>
                <tr>
                  <td><strong>Per&iacute;odo de Gera&ccedil;&atilde;o do Comando<span class="style5">:</span></strong></td>

                  <td colspan="2">
                  	<html:text property="geracaoComandoDataInicial" size="10"
						maxlength="10" tabindex="2"
						onkeyup="mascaraData(this, event);"
						onkeypress="return isCampoNumerico(event);" 
						onblur="validarPeriodoVencimento(document.forms[0].geracaoComandoDataInicial);"/>
					<a	href="javascript:abrirCalendarioReplicando('FiltrarComandoNegativacaoTipoCriterioActionForm', 'geracaoComandoDataInicial', 'geracaoComandoDataFinal');" >
					<img border="0"
						src="<bean:message key="caminho.imagens"/>calendario.gif"
						width="20" border="0" align="absmiddle" alt="Exibir Calendário"/></a>
					a <html:text property="geracaoComandoDataFinal" size="10"
						maxlength="10" tabindex="3" 
						onkeyup="mascaraData(this, event);" 
						onblur="validarPeriodoVencimento(document.forms[0].geracaoComandoDataFinal);"
						onkeypress="return isCampoNumerico(event);"/>
						 
					<a	href="javascript:abrirCalendario('FiltrarComandoNegativacaoTipoCriterioActionForm', 'geracaoComandoDataFinal')">
					<img border="0"
						src="<bean:message key="caminho.imagens"/>calendario.gif"
						width="20" border="0" align="absmiddle" alt="Exibir Calendário" /></a>
					dd/mm/aaaa
                  </td>

                </tr>
                <tr>
                  <td><strong>Per&iacute;odo de Execu&ccedil;&atilde;o do Comando:</strong></td>
                  <td colspan="2">
                  	<html:text property="execucaoComandoDataInicial" size="10"
						maxlength="10" tabindex="2"
						onkeyup="mascaraData(this, event);"
						onkeypress="return isCampoNumerico(event);" 
						onblur="validarPeriodoVencimento(document.forms[0].execucaoComandoDataInicial);"/>
					<a	href="javascript:abrirCalendarioReplicando('FiltrarComandoNegativacaoTipoCriterioActionForm', 'execucaoComandoDataInicial', 'execucaoComandoDataFinal');" >
					<img border="0"
						src="<bean:message key="caminho.imagens"/>calendario.gif"
						width="20" border="0" align="absmiddle" alt="Exibir Calendário"/></a>
					a <html:text property="execucaoComandoDataFinal" size="10"
						maxlength="10" tabindex="3" 
						onkeyup="mascaraData(this, event); "
						onkeypress="return isCampoNumerico(event);" 
						onblur="validarPeriodoVencimento(document.forms[0].execucaoComandoDataFinal);"/> 
					<a	href="javascript:abrirCalendario('FiltrarComandoNegativacaoTipoCriterioActionForm', 'execucaoComandoDataFinal')">
					<img border="0"
						src="<bean:message key="caminho.imagens"/>calendario.gif"
						width="20" border="0" align="absmiddle" alt="Exibir Calendário" /></a>
					dd/mm/aaaa
                  </td>
                </tr>
                <tr>
                  <td><strong>Per&iacute;odo de Refer&ecirc;ncia do D&eacute;bito:</strong></td>

                  <td colspan="2">
                  	<html:text property="referenciaDebitoDataInicial" 
					size="8" maxlength="7" 
					onkeyup="mascaraAnoMes(this, event);" 
					onblur="validaMesAno(document.forms[0].referenciaDebitoDataInicial);"
					onkeypress="return isCampoNumerico(event);"/>a 
					<html:text property="referenciaDebitoDataFinal" 
					size="8" maxlength="7" onkeyup="mascaraAnoMes(this, event);" 
					onblur="validaMesAno(document.forms[0].referenciaDebitoDataFinal);"
					onkeypress="return isCampoNumerico(event);"/> mm/aaaa
				  </td>
				</td>
                </tr>

                <tr>
                  <td><strong>Per&iacute;odo de Vencimento do D&eacute;bito:</strong></td>
                  <td colspan="2">
                  	<html:text property="vencimentoDebitoDataInicial" size="10"
						maxlength="10" tabindex="2"
						onkeyup="mascaraData(this, event); "
						onkeypress="return isCampoNumerico(event);" 
						onblur="validarPeriodoVencimento(document.forms[0].vencimentoDebitoDataInicial);"/>
					<a	href="javascript:abrirCalendarioReplicando('FiltrarComandoNegativacaoTipoCriterioActionForm', 'vencimentoDebitoDataInicial', 'vencimentoDebitoDataFinal');" >
					<img border="0"
						src="<bean:message key="caminho.imagens"/>calendario.gif"
						width="20" border="0" align="absmiddle" alt="Exibir Calendário"/></a>
					a <html:text property="vencimentoDebitoDataFinal" size="10"
						maxlength="10" tabindex="3" onkeyup="mascaraData(this, event); "
						onkeypress="return isCampoNumerico(event);" 
						onblur="validarPeriodoVencimento(document.forms[0].vencimentoDebitoDataFinal);"/> 
					<a	href="javascript:abrirCalendario('FiltrarComandoNegativacaoTipoCriterioActionForm', 'vencimentoDebitoDataFinal')">
					<img border="0"
						src="<bean:message key="caminho.imagens"/>calendario.gif"
						width="20" border="0" align="absmiddle" alt="Exibir Calendário" /></a>
					dd/mm/aaaa
                  </td>
                </tr>
                <tr>
                  <td colspan="3"><hr>                  </td>
                </tr>

                <tr>
                  <td><strong>Intervalo de Valor do D&eacute;bito:</strong></td>
                  <td colspan="2">
                  	<html:text property="valorDebitoInicial" 
						size="13" maxlength="13" onkeyup="formataValorMonetario(this, 13); "
						onkeypress="return isCampoNumerico(event);"/>a 
					<html:text property="valorDebitoFinal" 
						size="13" maxlength="13" onkeyup="formataValorMonetario(this, 13); "
						onkeypress="return isCampoNumerico(event);"/>
				  </td>

                </tr>
                <tr>
                  <td><strong>Intervalo de N&uacute;mero de Contas:</strong></td>
                  <td colspan="2">
                  	<html:text property="numeroContasInicial" 
						size="10" maxlength="4" onkeypress="return isCampoNumerico(event);" />a 
					<html:text property="numeroContasFinal"
					size="10" maxlength="4" onkeypress="return isCampoNumerico(event);" />
				  </td>
                </tr>
                <tr>
                  <td colspan="3"><hr>                  </td>
                </tr>
                <tr>
                  <td><strong>Recebeu Carta de Parcelamento em Atraso:</strong></td>
                  <td colspan="2"><strong>
					<html:radio property="cartaParcelamentoAtraso" value="1" disabled="false" />
                    <strong><strong>Sim</strong>
					<html:radio property="cartaParcelamentoAtraso" value="2" disabled="false" />
                    <strong>N&atilde;o</strong></strong></strong></td>
                </tr>
                <tr>
                  <td colspan="3"><hr>                  </td>

                </tr>
                <tr>
                  <td><strong>Situa&ccedil;&atilde;o do Comando:</strong></td>
                  <td colspan="2"><strong>
					<html:radio property="situacaoComando" value="3" disabled="false" />
                    <strong><strong>Todos</strong>
					<html:radio property="situacaoComando" value="1" disabled="false" />
					<strong><strong>Executados</strong></strong>
					<html:radio property="situacaoComando" value="2" disabled="false" />
                    <strong>N&atilde;o <strong><strong><strong>Executados</strong></strong></strong></strong></strong></strong></td>
                </tr>
                <tr> 
                  <td colspan="3"><hr> </td>
                </tr>

                <tr>
                  <td><strong>Exige ao Menos uma Conta em Nome do Cliente Negativado:</strong></td>
                  <td colspan="2"><strong>
					<html:radio property="indicadorContaNomeCliente" value="1" disabled="false" />
					<strong><strong>Sim</strong></strong>
					<html:radio property="indicadorContaNomeCliente" value="2" disabled="false" />
					<strong><strong>Não</strong></strong>
					<html:radio property="indicadorContaNomeCliente" value="3" disabled="false" />
                    <strong><strong>Todos</strong></strong>
                    </strong>
                  </td> 
                </tr>
                <tr> 
                  <td colspan="3"><hr> </td>
                </tr>
                <tr> 
                  <td height="17" colspan="3"><strong><font color="#FF0000"> </font></strong></td>
                </tr>
                <tr> 
                  <td height="17" colspan="2"><strong><font color="#FF0000"> </font></strong><strong><font color="#FF0000"> 
                    </font></strong><strong><font color="#FF0000"> 
                  	<input name="Submit22"
						class="bottonRightCol" value="Limpar" type="button"
						onclick="window.location.href='/gsan/exibirFiltrarComandoNegativacaoTipoCriterioAction.do?menu=sim';">
                    </font></strong></td>
                  <td align="right"><div align="right"><img src="<bean:message key='caminho.imagens'/>voltar.gif" width="15" height="24">
                    <input name="Button32222" type="button" class="bottonRightCol" value="Voltar" onClick="javascript:window.location.href='/gsan/exibirFiltrarComandoNegativacaoAction.do'"/>
                    <input name="Button322" type="button" class="bottonRightCol" value="Filtrar" onClick="return filtrar();" />
                  </div></td>
                  </tr>
              </table>
			
            
          <p>&nbsp;</p></tr>
		<tr>
		  <td colspan="3">
		</td>
	</tr>
</table>

<%@ include file="/jsp/util/rodape.jsp"%>
</div>
</body>
</html:form>

<%@ include file="/jsp/util/telaespera.jsp"%>

<script>

</script>



</html:html>