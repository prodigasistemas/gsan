<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page import="gcom.util.ConstantesSistema"%>
<head>
<html:html>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false" 
	formName="FiltrarComandosAcaoCobrancaEventualActionForm" />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript">
<!-- Begin
    function caracteresespeciais () {
	     this.aa = new Array("localidadeOrigemID", "Localidade Inicial deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
	     this.ab = new Array("localidadeDestinoID", "Localidade Final deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
	     this.ac = new Array("setorComercialOrigemCD", "Setor Comercial Inicial deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
	     this.ad = new Array("setorComercialDestinoCD", "Setor Comercial Final deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
	     this.ae = new Array("idCliente", "Cliente deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
	     this.af = new Array("intervaloQuantidadeDocumentosFinal", "Intervalor de Quantidade dos Documentos Final deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
	     this.ag = new Array("intervaloQuantidadeDocumentosInicial", "Intervalor de Quantidade dos Documentos Inicial deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
	     this.ah = new Array("intervaloQuantidadeItensDocumentosFinal", "Intervalo de Quantidade de Itens dos Documentos Final deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
	     this.ai = new Array("intervaloQuantidadeItensDocumentosInicial", "Intervalo de Quantidade de Itens dos Documentos Inicial deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
	     this.aj = new Array("intervaloValorDocumentosFinal", "Intervalo de Valor dos Documentos Final deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
	     this.al = new Array("intervaloValorDocumentosInicial", "Intervalo de Valor dos Documentos Inicial deve somente conter números positivos.", new Function ("varName", " return this[varName];"));     
	     this.am = new Array("criterioCobranca", "Critério de Cobrança deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
    }

    function IntegerValidations () {
	     this.am = new Array("localidadeOrigemID", "Localidade Inicial deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
	     this.an = new Array("localidadeDestinoID", "Localidade Final deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
	     this.ao = new Array("setorComercialOrigemCD", "Setor Comercial Inicial deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
	     this.ap = new Array("setorComercialDestinoCD", "Setor Comercial Final deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
	     this.aq = new Array("idCliente", "Cliente deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
	     this.ar = new Array("intervaloQuantidadeDocumentosFinal", "Intervalor de Quantidade dos Documentos Final deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
	     this.as = new Array("intervaloQuantidadeDocumentosInicial", "Intervalor de Quantidade dos Documentos Inicial deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
	     this.at = new Array("intervaloQuantidadeItensDocumentosFinal", "Intervalo de Quantidade de Itens dos Documentos Final deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
	     this.au = new Array("intervaloQuantidadeItensDocumentosInicial", "Intervalo de Quantidade de Itens dos Documentos Inicial deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
	     this.az = new Array("criterioCobranca", "Critério de Cobrança deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
    }




function filtrar(){
	var form = document.forms[0];
	
	if(verificaAnoMesMensagemPersonalizada(form.periodoReferenciaContasInicial,"Mês ou Ano da Referência das Contas Inicial inválido")
		&& verificaAnoMesMensagemPersonalizada(form.periodoReferenciaContasFinal,"Mês ou Ano da Referência das Contas Final inválido")
		&& verificaDataMensagemPersonalizada(form.periodoVencimentoContasInicial,"Data do Período de Vencimento das Contas Inicial inválido")
		&& verificaDataMensagemPersonalizada(form.periodoVencimentoContasFinal,"Data do Período de Vencimento das Contas Final inválido")
		&& verificaDataMensagemPersonalizada(form.periodoRealizacaoComandoInicial,"Data do Período de Realização do Comando Inicial inválido")
		&& verificaDataMensagemPersonalizada(form.periodoRealizacaoComandoFinal,"Data do Período de Realização do Comando Final inválido")
		&& verificaDataMensagemPersonalizada(form.periodoComandoInicial,"Data do Período do Comando Inicial inválido")
		&& verificaDataMensagemPersonalizada(form.periodoComandoFinal,"Data do Período do Comando Final inválido")
		&& testarCampoValorZero(document.FiltrarComandosAcaoCobrancaEventualActionForm.intervaloQuantidadeDocumentosFinal, 'Intervalor de Quantidade dos Documentos Final')
		&& testarCampoValorZero(document.FiltrarComandosAcaoCobrancaEventualActionForm.intervaloQuantidadeDocumentosInicial, 'Intervalor de Quantidade dos Documentos Inicial')
		&& testarCampoValorZero(document.FiltrarComandosAcaoCobrancaEventualActionForm.intervaloQuantidadeItensDocumentosFinal, 'Intervalo de Quantidade de Itens dos Documentos Final')
		&& testarCampoValorZero(document.FiltrarComandosAcaoCobrancaEventualActionForm.intervaloQuantidadeItensDocumentosInicial, 'Intervalo de Quantidade de Itens dos Documentos Inicial')
		&& testarCampoValorZeroDecimal(document.FiltrarComandosAcaoCobrancaEventualActionForm.intervaloValorDocumentosFinal, 'Intervalo de Valor dos Documentos Final')
		&& testarCampoValorZeroDecimal(document.FiltrarComandosAcaoCobrancaEventualActionForm.intervaloValorDocumentosInicial, 'Intervalo de Valor dos Documentos Inicial')
		&& testarCampoValorZero(document.FiltrarComandosAcaoCobrancaEventualActionForm.localidadeOrigemID, 'Localidade Inicial') 
	    && testarCampoValorZero(document.FiltrarComandosAcaoCobrancaEventualActionForm.localidadeDestinoID, 'Localidade Final') 
		&& testarCampoValorZero(document.FiltrarComandosAcaoCobrancaEventualActionForm.setorComercialOrigemCD, 'Setor Comercial Inicial') 
		&& testarCampoValorZero(document.FiltrarComandosAcaoCobrancaEventualActionForm.setorComercialDestinoCD, 'Setor Comercial Final') 
	 	&& testarCampoValorZero(document.FiltrarComandosAcaoCobrancaEventualActionForm.idCliente, 'Cliente') 
	 	&& testarCampoValorZero(document.FiltrarComandosAcaoCobrancaEventualActionForm.criterioCobranca, 'Critério de Cobrança') 	 	
	    && validateCaracterEspecial(form) 
	    && validateLong(form)
		&& validarLocalidadeDiferentes()	
		&& validarSetoresComerciaisDiferentes()
		&& validarQuadraDiferentes()
		&& validarRotasDiferentes()
		&& verificarSetoresComerciaisMenores()
		&& verificarLocalidadeMenores()
		&& validarInscricoes()){    

		if (comparaMesAno(document.FiltrarComandosAcaoCobrancaEventualActionForm.periodoReferenciaContasInicial.value, ">", 
			document.FiltrarComandosAcaoCobrancaEventualActionForm.periodoReferenciaContasFinal.value)){
			alert("Mês ou Ano da Referência das Contas Inicial posterior ao Mês ou Ano da Referência das Contas Final.");
			return false;
		}
		
		if (comparaData(document.FiltrarComandosAcaoCobrancaEventualActionForm.periodoVencimentoContasInicial.value, ">",
		 document.FiltrarComandosAcaoCobrancaEventualActionForm.periodoVencimentoContasFinal.value )){
			alert('Data do Período de Vencimento das Contas Final anterior à data do Vencimento das Contas Inicial');
			return false;
		}
		if (comparaData(document.FiltrarComandosAcaoCobrancaEventualActionForm.periodoRealizacaoComandoInicial.value, ">",
		 document.FiltrarComandosAcaoCobrancaEventualActionForm.periodoRealizacaoComandoFinal.value )){
			alert('Data do Período de Realização do Comando Final anterior à data do Período Realização do Comando Inicial');
			return false;
		}
		if (comparaData(document.FiltrarComandosAcaoCobrancaEventualActionForm.periodoComandoInicial.value, ">",
		 document.FiltrarComandosAcaoCobrancaEventualActionForm.periodoComandoFinal.value )){
			alert('Data do Período do Comando Final anterior à data do Período do Comando Inicial');
			return false;
		}

		if(document.FiltrarComandosAcaoCobrancaEventualActionForm.intervaloQuantidadeDocumentosFinal.value  < 
			document.FiltrarComandosAcaoCobrancaEventualActionForm.intervaloQuantidadeDocumentosInicial.value){
			alert('Quantidade dos Documentos Final menor que a Quantidade dos Documentos Inicial');
			return false;			
		}
		if(document.FiltrarComandosAcaoCobrancaEventualActionForm.intervaloQuantidadeItensDocumentosFinal.value <
		   document.FiltrarComandosAcaoCobrancaEventualActionForm.intervaloQuantidadeItensDocumentosInicial.value){
			alert('Quantidade dos Itens de Documentos Final menor que a Quantidade dos Itens de Documentos Inicial');
			return false;			
		}
		if(document.FiltrarComandosAcaoCobrancaEventualActionForm.intervaloValorDocumentosFinal.value <
		 document.FiltrarComandosAcaoCobrancaEventualActionForm.intervaloValorDocumentosInicial.value){
			alert('Valor dos Documentos Final menor que o Valor dos Documentos Inicial');
			return false;			
		 }


	    form.action =  '/gsan/filtrarComandosAcaoCobrancaEventualAction.do'
		form.submit();
	}
}

function duplicarRota(){
	var formulario = document.forms[0]; 
	formulario.rotaFinal.value = formulario.rotaInicial.value;
}  

function receberRota(codigoRota,destino) {
 	  var form = document.forms[0];
	if(destino == "inicial"){
		form.rotaInicial.value = codigoRota;		
		form.rotaFinal.value = codigoRota;				
	}else if(destino == "final"){
		form.rotaFinal.value = codigoRota;		
	}

}


function carregarRotaInicial(){

	var formulario = document.forms[0]; 
	
	if(formulario.setorComercialOrigemCD.value!= '' && formulario.setorComercialDestinoCD.value != ''){
		if(formulario.rotaInicial.options.length == 1){
	   		formulario.action =  '/gsan/exibirFiltrarComandosAcaoCobrancaEventualAction.do?rota=CARREGAR'
   			formulario.submit();				
		}
	}
	
}

function carregarRotaFinal(){

	var formulario = document.forms[0]; 
	
	if(formulario.setorComercialOrigemCD.value!= '' && formulario.setorComercialDestinoCD.value != ''){
		if(formulario.rotaFinal.options.length == 1){
	   		formulario.action =  '/gsan/exibirFiltrarComandosAcaoCobrancaEventualAction.do?rota=CARREGAR'
   			formulario.submit();				
		}
	}
	
}


function duplicarLocalidade(){
	var formulario = document.forms[0]; 
	formulario.localidadeDestinoID.value = formulario.localidadeOrigemID.value;
}  

function duplicarSetorComercial(){
	var formulario = document.forms[0]; 
	formulario.setorComercialDestinoCD.value = formulario.setorComercialOrigemCD.value;
	if(formulario.setorComercialOrigemCD.value!= '' && formulario.setorComercialDestinoCD.value != ''){
	    if(formulario.rotaInicial != undefined){
			formulario.rotaInicial.value = "";
			formulario.rotaFinal.value = "";
   	   		formulario.rotaInicial.readOnly = true;
   	   		formulario.rotaFinal.readOnly = true;
	    }
	}
}  

function duplicaPeriodoReferenciaContas(){
	var formulario = document.forms[0]; 
	formulario.periodoReferenciaContasFinal.value = formulario.periodoReferenciaContasInicial.value;
}  
  
function duplicaPeriodoPrevisaoComando(){
	var formulario = document.forms[0]; 
	formulario.periodoPrevisaoComandoFinal.value = formulario.periodoPrevisaoComandoInicial.value;
}  

function duplicaPeriodoVencimentoContas(){
	var formulario = document.forms[0]; 
	formulario.periodoVencimentoContasFinal.value = formulario.periodoVencimentoContasInicial.value;
}  

function duplicaPeriodoComando(){
	var formulario = document.forms[0]; 
	formulario.periodoComandoFinal.value = formulario.periodoComandoInicial.value;
}  
  
function duplicaPeriodoRealizacaoComando(){
	var formulario = document.forms[0]; 
	formulario.periodoRealizacaoComandoFinal.value = formulario.periodoRealizacaoComandoInicial.value;
}  
 
  
function duplicaIntervaloQuantidadeDocumentos(){
	var formulario = document.forms[0]; 
	formulario.intervaloQuantidadeDocumentosFinal.value = formulario.intervaloQuantidadeDocumentosInicial.value;
}  
  
function duplicaIntervaloValorDocumentos(){
	var formulario = document.forms[0]; 
	formulario.intervaloValorDocumentosFinal.value = formulario.intervaloValorDocumentosInicial.value;
}  

function duplicaIntervaloQuantidadeItensDocumentos(){
	var formulario = document.forms[0]; 
	formulario.intervaloQuantidadeItensDocumentosFinal.value = formulario.intervaloQuantidadeItensDocumentosInicial.value;
}  
  
function cancelar(){
		var formulario = document.forms[0]; 
   		formulario.action =  '/gsan/telaPrincipal.do'
   		formulario.submit();
}

function voltar(){
		var formulario = document.forms[0]; 
   		formulario.action =  '/gsan/exibirConsultarComandosAcaoCobrancaAction.do?carregando=SIM';
   		formulario.submit();
}


function limpar(){
		var formulario = document.forms[0]; 
		
		formulario.grupoCobranca.selectedIndex = -1;
		formulario.acaoCobranca.selectedIndex = -1;
		formulario.atividadeCobranca.selectedIndex = -1;	
		formulario.periodoReferenciaContasInicial.value = "";
		formulario.periodoReferenciaContasFinal.value = "";
		formulario.periodoVencimentoContasInicial.value = "";
		formulario.periodoVencimentoContasFinal.value = "";
		formulario.periodoComandoInicial.value = "";
		formulario.periodoComandoFinal.value = "";
		formulario.periodoRealizacaoComandoInicial.value = "";
		formulario.periodoRealizacaoComandoFinal.value = "";	
		formulario.intervaloQuantidadeDocumentosInicial.value = "";
		formulario.intervaloQuantidadeDocumentosFinal.value = "";
		formulario.intervaloValorDocumentosInicial.value = "";
		formulario.intervaloValorDocumentosFinal.value = "";
		formulario.intervaloQuantidadeItensDocumentosInicial.value = "";
		formulario.intervaloQuantidadeItensDocumentosFinal.value = "";

		formulario.gerenciaRegional.value = "";
		formulario.unidadeNegocio.value = "";		
		formulario.localidadeOrigemID.value = "";
		formulario.localidadeDestinoID.value = "";
		formulario.nomeLocalidadeOrigem.value = "";
		formulario.nomeLocalidadeDestino.value = "";
		formulario.setorComercialOrigemCD.value = "";
		formulario.setorComercialOrigemID.value = "";
		formulario.nomeSetorComercialOrigem.value = "";
		formulario.setorComercialDestinoCD.value = "";
		formulario.setorComercialDestinoID.value = "";
		formulario.nomeSetorComercialDestino.value = "";
		formulario.rotaInicial.value = "";
		formulario.rotaFinal.value = "";
		formulario.idCliente.value = "";
		formulario.nomeCliente.value = "";
		formulario.clienteRelacaoTipo.value = "";


		formulario.consumoMedioInicial.value = "";
		formulario.consumoMedioFinal.value = "";
		formulario.tipoConsumo.value = 1

		formulario.criterioCobranca.value = "";
		formulario.nomeCriterioCobranca.value = "";
		formulario.inscricaoTipo.value = "";
		
		formulario.indicadorCriterio[0].checked = true;
		formulario.situacaoComando[0].checked = true;

		validarExibicaoTipoCosumo();
}

function validarGerenciaRegionalParaBloquear(){

		var form = document.forms[0];
	
		var ok = 1;
		if(form.localidadeOrigemID.value != ""){
			ok = 2;
		}
		if(form.localidadeDestinoID.value != ""){
			ok = 2;
		}
		if(form.idCliente.value != ""){
			ok = 2;
		}
		if(form.unidadeNegocio.value != "-1"){
			ok = 2;
		}
		
		if(form.grupoCobranca.value != ""){
			ok = 2;
		}
		
		if(ok == 1){
			form.grupoCobranca.disabled = true;
		    form.unidadeNegocio.disabled = true;		    
			form.localidadeOrigemID.readOnly = true;
			form.localidadeDestinoID.readOnly = true;
			form.setorComercialOrigemCD.readOnly = true;
		    form.setorComercialDestinoCD.readOnly = true;
		    form.rotaInicial.readOnly = true;
    	    form.rotaFinal.readOnly = true;
        	form.nomeCliente.value = ""
        	form.idCliente.value = ""
        	form.idCliente.readOnly = true;
        	form.clienteRelacaoTipo.value = "";
        	form.clienteRelacaoTipo.disabled = true;
		}
	}


function validarUnidadeNegocioParaBloquear(){

	var form = document.forms[0];
	
		var ok = 1;
		if(form.localidadeOrigemID.value != ""){
			ok = 2;
		}
		if(form.localidadeDestinoID.value != ""){
			ok = 2;
		}
		if(form.idCliente.value != ""){
			ok = 2;
		}
		if(form.gerenciaRegional.value != "-1"){
			ok = 2;
		}
		if(form.grupoCobranca.value != ""){
			ok = 2;
		}
		if(ok == 1){
			form.grupoCobranca.disabled = true;
		    form.gerenciaRegional.disabled = true;		    
			form.localidadeOrigemID.readOnly = true;
			form.localidadeDestinoID.readOnly = true;
			form.setorComercialOrigemCD.readOnly = true;
		    form.setorComercialDestinoCD.readOnly = true;
		    form.rotaInicial.readOnly = true;
    	    form.rotaFinal.readOnly = true;
        	form.nomeCliente.value = ""
        	form.idCliente.value = ""
        	form.idCliente.readOnly = true;
        	form.clienteRelacaoTipo.value = "";
        	form.clienteRelacaoTipo.disabled = true;
		}
}


function validarIndicadorCriterio(){

    var form = document.forms[0];
    
   var indice;
   var array = new Array(form.indicadorCriterio.length);
   var selecionado = "";
   var formulario = document.forms[0]; 
   for(indice = 0; indice < form.elements.length; indice++){
 	  if (form.elements[indice].type == 'radio' && form.elements[indice].checked == true) {
	    	selecionado = form.elements[indice].value;
	    	indice = form.elements.length;
	  }
   }    

	if(selecionado == 'Todos'){
	   limparCriterioCobranca();
	   form.criterioCobranca.disabled = true;
	}else if(selecionado == 'Rota'){
	   limparCriterioCobranca();
	   form.criterioCobranca.disabled = true;
	}else if(selecionado == 'Comando'){
	   form.criterioCobranca.disabled = false;
	}

}



function verificarGerenciaRegional(){
    var formulario = document.forms[0];
    	
	if(formulario.localidadeOrigemID.value != '' || formulario.localidadeDestinoID.value != ''){
		formulario.gerenciaRegional.disabled = true;
	}else{
		formulario.gerenciaRegional.disabled = false;
	}
}  

function verificarUnidadeNegocio(){
    var formulario = document.forms[0];
    	
	if(formulario.localidadeOrigemID.value != '' || formulario.localidadeDestinoID.value != ''){
		formulario.unidadeNegocio.disabled = true;
	}else{
		formulario.unidadeNegocio.disabled = false;
	}
}  


function verificarGrupoCobranca(){
    var formulario = document.forms[0];
    
	if(formulario.localidadeOrigemID.value != '' || formulario.localidadeDestinoID.value != ''){
		formulario.grupoCobranca.disabled = true;
	}else{
		formulario.grupoCobranca.disabled = false;
	}
}  


function validarCliente(apagar){
    var form = document.forms[0];
    if (form.idCliente.readOnly == false){
	    if (form.idCliente.value != ''){
		      if(apagar ==1) form.clienteRelacaoTipo.value = "";	
    		  form.clienteRelacaoTipo.disabled = false;
	    }else{
		      form.clienteRelacaoTipo.disabled = true;
	    }
	}
}



function validarLocalidadeDiferentes(){
	var form = document.forms[0];

	if(form.localidadeOrigemID.value != '' && form.localidadeDestinoID.value == ''){
		alert("Informe a Localidade Final");
		form.localidadeDestinoID.focus();
		return false;
	}
	if(form.localidadeOrigemID.value == '' && form.localidadeDestinoID.value != ''){
		alert("Informe a Localidade Inicial");
		form.localidadeOrigemID.focus();
		return false;
	}
	return true;
	
}

function validarSetoresComerciaisDiferentes(){
	var form = document.forms[0];
	if(form.setorComercialOrigemCD.value!= '' && form.setorComercialDestinoCD.value == ''){
		alert("Informe o Setor Comercial Final");
		form.setorComercialDestinoCD.focus();
		return false;
	}
	if(form.setorComercialOrigemCD.value == '' && form.setorComercialDestinoCD.value != ''){
		alert("Informe o Setor Comercial Inicial");
		form.setorComercialDestinoCD.focus();
		return false;
	}
	return true;
}

function validarRotasDiferentes(){
	var form = document.forms[0];

    if(form.rotaInicial != undefined){
		if(form.rotaInicial.value!= '' && form.rotaFinal.value == ''){
			alert("Informe a Rota Final");
			form.rotaFinal.focus();
			return false;
		}
		if(form.rotaInicial.value == '' && form.rotaFinal.value != ''){
			alert("Informe a Rota Inicial");
			form.rotaInicial.focus();
			return false;
		}
	}
	return true;
}

function verificarSetoresComerciaisMenores(){

	var form = document.forms[0];
	if(form.setorComercialOrigemCD.value != '' && form.setorComercialDestinoCD.value != ''){
		if(form.setorComercialDestinoCD.value < form.setorComercialOrigemCD.value){
			alert("Setor Comercial Final menor que o Setor Comercial Inicial");	
			form.setorComercialDestinoCD.focus();
			return false;
		}else{
			return true;
		}
	}else{
		return true;
	}
}

function verificarLocalidadeMenores(){
	var form = document.forms[0];
	if(form.localidadeOrigemID.value != '' && form.localidadeDestinoID.value != ''){
		if(form.localidadeDestinoID.value < form.localidadeOrigemID.value){
			alert("Localidade Final menor que a Localidade Inicial");	
			form.localidadeDestinoID.focus();
			return false;
		}else{
			return true;
		}
	
	}else{
		return true;
	}
}

function desabilitarCobrancaGrupo(){
		var form = document.forms[0];
		form.grupoCobranca.disabled = true;
		form.grupoCobranca.value = "";
}


function validarGrupoCobranca(visulizar){

		var form = document.forms[0];
	
		var ok = true;
		if(form.gerenciaRegional.value != "-1"){
			ok = false;
		}
		if(form.unidadeNegocio.value != "-1"){
			ok = false;
		}
		if(form.localidadeOrigemID.value != ""){
			ok = false;
		}
		if(form.localidadeDestinoID.value != ""){
			ok = false;
		}

		if(ok == true){
			var grupoCobrancaSelecionado = false;
				for (i = 0; i < form.grupoCobranca.length; i++){
			   if((i != 0) && (form.grupoCobranca[i].selected)){
			   		grupoCobrancaSelecionado = true;
			   }
			}
			if(grupoCobrancaSelecionado == true){
				form.gerenciaRegional.disabled = true;
				form.unidadeNegocio.disabled = true;				
		    	form.localidadeOrigemID.readOnly = true;
				form.localidadeDestinoID.readOnly = true;
		        form.setorComercialOrigemCD.readOnly = true;
		        form.setorComercialDestinoCD.readOnly = true;
		        form.imagem.enabled = true;
				form.idCliente.readOnly = true;
				form.clienteRelacaoTipo.disabled = true;
			    if(form.rotaInicial != undefined){
						form.rotaInicial.value = "";
						form.rotaFinal.value = "";
		    	   		form.rotaInicial.readOnly = true;
		    	   		form.rotaFinal.readOnly = true;
			    }
			}else{
				form.gerenciaRegional.disabled = false;
				form.unidadeNegocio.disabled = false;				
		    	form.localidadeOrigemID.readOnly = false;
				form.localidadeDestinoID.readOnly = false;
		        form.setorComercialOrigemCD.readOnly = false;
		        form.setorComercialDestinoCD.readOnly = false;
		        form.imagem.enabled = false;
				form.idCliente.readOnly = false;
				form.clienteRelacaoTipo.disabled = true;
			    if(form.rotaInicial != undefined){
		    	   		form.rotaInicial.readOnly = false;
		    	   		form.rotaFinal.readOnly = false;
			    }	
			}
		}else{
			if(visulizar == 1){
//				alert("Gerência Regional/Localidade/Setor Comercial/Rota informados");
	//			form.grupoCobranca.value = "";
			}
		}
}

function validarGrupoCobrancaParaBloquear(){

	var form = document.forms[0];
	
		var ok = 1;
		if(form.localidadeOrigemID.value != ""){
			ok = 2;
		}
		if(form.localidadeDestinoID.value != ""){
			ok = 2;
		}
		if(ok == 2){
			form.grupoCobranca.disabled = true;
		    form.gerenciaRegional.disabled = true;
		    form.unidadeNegocio.disabled = true;		    
        	form.nomeCliente.value = ""
        	form.idCliente.value = ""
        	form.idCliente.readOnly = true;
        	form.clienteRelacaoTipo.value = "";
        	form.clienteRelacaoTipo.disabled = true;
		}else{
			form.grupoCobranca.disabled = false;
		    form.gerenciaRegional.disabled = false;
		    form.unidadeNegocio.disabled = false;		    
        	form.idCliente.readOnly = false;
        	form.clienteRelacaoTipo.value = "";
        	form.clienteRelacaoTipo.disabled = false;

		}
		
}

function validarLocalidade(){

	var form = document.forms[0];

	if(form.localidadeOrigemID.value != form.localidadeDestinoID.value){
	    form.setorComercialDestinoCD.value = "";
	    form.setorComercialDestinoID.value = "";		   
 	    form.nomeSetorComercialDestino.value = "";
		form.setorComercialOrigemCD.value = "";
		form.setorComercialOrigemID.value = "";
		form.nomeSetorComercialOrigem.value = "";
		alert("Para informar o Setor Comercial, a Localidade Inicial e Final precisam ser iguais");
	}
}


function limparDestino(tipo){


	var form = document.forms[0];

	switch(tipo){
		case 1: //De localidade pra baixo
			form.localidadeDestinoID.value = "";
			form.nomeLocalidadeDestino.value = "";		
			form.setorComercialOrigemCD.value = "";
			form.setorComercialOrigemID.value = "";
			form.nomeSetorComercialOrigem.value = "";
		    form.setorComercialDestinoCD.value = "";
	        form.setorComercialOrigemCD.readOnly = false;
	        form.setorComercialDestinoCD.readOnly = false;
	        form.imagem.enabled = false;
	   		form.rotaInicial.value = "";
       		form.rotaFinal.value = "";		   
		    
		case 2: //De setor para baixo
   	       form.setorComercialDestinoCD.value = "";
		   form.setorComercialDestinoID.value = ""; 
		   form.nomeSetorComercialDestino.value = "";		   
	   	   form.rotaInicial.value = "";
 	   	   form.rotaFinal.value = "";		   
	}
}


function limparOrigem(tipo){
	var form = document.forms[0];

	switch(tipo){
		case 1: //De localidara pra baixo
			form.localidadeOrigemID.value = "";
			form.nomeLocalidadeOrigem.value = "";
			form.grupoCobranca.disabled = false;			
			form.gerenciaRegional.disabled = false;
			form.unidadeNegocio.disabled = false;			
			form.localidadeDestinoID.value = "";
			form.nomeLocalidadeDestino.value = "";
	        form.setorComercialOrigemCD.readOnly = false;
	        form.setorComercialDestinoCD.readOnly = false;
	        form.imagem.enabled = false;
	   		form.rotaInicial.value = "";
       		form.rotaFinal.value = "";		   
   	   		form.rotaInicial.readOnly = false;
   	   		form.rotaFinal.readOnly = false;
		case 2: //De setor para baixo
		   form.setorComercialOrigemCD.value = "";
		   form.setorComercialOrigemID.value = "";
		   form.nomeSetorComercialOrigem.value = "";
		   form.setorComercialDestinoCD.value = "";
		   form.setorComercialDestinoID.value = "";		   
		   form.nomeSetorComercialDestino.value = "";
	   	   form.rotaInicial.value = "";
	   	   form.rotaFinal.value = "";		   
	}
}



function validarGerenciaRegional(visulizar){

	var form = document.forms[0];
	
		var ok = true;
		if(form.localidadeOrigemID.value != ""){
			ok = false;
		}
		if(form.localidadeDestinoID.value != ""){
			ok = false;
		}
		if(form.idCliente.value != ""){
			ok = false;
		}

		if(form.unidadeNegocio.value != "-1"){
			ok = false;
		}

		if(form.grupoCobranca.value != ""){
			ok = false;
		}
		
		if(ok == false){
		    	form.grupoCobranca.disabled = true;			
		    	form.unidadeNegocio.disabled = true;			
		    	form.localidadeOrigemID.readOnly = true;
				form.localidadeDestinoID.readOnly = true;
		        form.setorComercialOrigemCD.readOnly = true;
		        form.setorComercialDestinoCD.readOnly = true;
		        form.imagem.enabled = true;
				form.rotaInicial.value = "";
				form.rotaFinal.value = "";
    	   		form.rotaInicial.readOnly = true;
    	   		form.rotaFinal.readOnly = true;
			    form.idCliente.readOnly = true;
        		form.clienteRelacaoTipo.disabled = true;
		}else{
		    	form.grupoCobranca.disabled = false;						
		    	form.unidadeNegocio.disabled = false;				
		    	form.localidadeOrigemID.readOnly = false;
				form.localidadeDestinoID.readOnly = false;
		        form.setorComercialOrigemCD.readOnly = false;
		        form.setorComercialDestinoCD.readOnly = false;
		        form.imagem.enabled = false;
		   		form.rotaInicial.value = "";
   		   		form.rotaFinal.value = "";		   
    	   		form.rotaInicial.readOnly = false;
    	   		form.rotaFinal.readOnly = false;
			    form.idCliente.readOnly = false;
		}
}

function validarGerenciaRegionalSelecionado(){

	var form = document.forms[0];
	
	if(form.gerenciaRegional.value != "-1"){
		form.grupoCobranca.disabled = true;
    	form.localidadeOrigemID.readOnly = true;
		form.localidadeDestinoID.readOnly = true;
        form.setorComercialOrigemCD.readOnly = true;
        form.setorComercialDestinoCD.readOnly = true;
        form.unidadeNegocio.disabled = true;
        form.imagem.enabled = true;
   		form.rotaInicial.readOnly = true;
   		form.rotaFinal.readOnly = true;
        form.nomeCliente.value = ""
        form.idCliente.value = ""
        form.idCliente.readOnly = true;
        form.clienteRelacaoTipo.value = "";
        form.clienteRelacaoTipo.disabled = true;
	}else{
		form.grupoCobranca.disabled = false;
    	form.localidadeOrigemID.readOnly = false;
		form.localidadeDestinoID.readOnly = false;
        form.setorComercialOrigemCD.readOnly = false;
        form.setorComercialDestinoCD.readOnly = false;
        form.unidadeNegocio.disabled = false;        
        form.imagem.enabled = false;
   		form.rotaInicial.readOnly = false;
   		form.rotaFinal.readOnly = false;
        form.nomeCliente.value = ""
        form.idCliente.value = ""
        form.idCliente.readOnly = false;
        form.clienteRelacaoTipo.value = "";
        form.clienteRelacaoTipo.disabled = false;
	}
}


function validarUnidadeNegocio(visulizar){

	var form = document.forms[0];
	
		var ok = true;
		if(form.localidadeOrigemID.value != ""){
			ok = false;
		}
		if(form.localidadeDestinoID.value != ""){
			ok = false;
		}
		if(ok == true){
			if(form.unidadeNegocio.value != "-1"){
		    	form.localidadeOrigemID.readOnly = true;
				form.localidadeDestinoID.readOnly = true;
				form.gerenciaRegional.disabled = true;
		        form.setorComercialOrigemCD.readOnly = true;
		        form.setorComercialDestinoCD.readOnly = true;
		        form.imagem.enabled = true;
				form.rotaInicial.value = "";
				form.rotaFinal.value = "";
    	   		form.rotaInicial.readOnly = true;
    	   		form.rotaFinal.readOnly = true;
			    form.idCliente.readOnly = true;
			}else{
		    	form.localidadeOrigemID.readOnly = false;
				form.localidadeDestinoID.readOnly = false;
		        form.setorComercialOrigemCD.readOnly = false;
				form.gerenciaRegional.disabled = false;		        
		        form.setorComercialDestinoCD.readOnly = false;
		        form.imagem.enabled = false;
		   		form.rotaInicial.value = "";
   		   		form.rotaFinal.value = "";		   
    	   		form.rotaInicial.readOnly = false;
    	   		form.rotaFinal.readOnly = false;
			    form.idCliente.readOnly = false;
			}
		}else{
			if(visulizar == 1){
				alert("Localidade/Setor Comercial/Rota informados");
				form.unidadeRegional.value = "";
			}
		}
}


function limparBorrachaDestino(tipo){
	var form = document.forms[0];

	switch(tipo){
		case 1: //De localidade pra baixo
			form.localidadeDestinoID.value = "";
			form.nomeLocalidadeDestino.value = "";					
			form.setorComercialOrigemCD.value = "";
			form.setorComercialOrigemID.value = "";
			form.nomeSetorComercialOrigem.value = "";
    		form.setorComercialDestinoCD.value = "";
	        form.setorComercialOrigemCD.readOnly = false;
	        form.setorComercialDestinoCD.readOnly = false;
	        form.imagem.enabled = false;
	   		form.rotaInicial.value = "";
	   		form.rotaFinal.value = "";		   
   	   		form.rotaInicial.readOnly = false;
   	   		form.rotaFinal.readOnly = false;

		case 2: //De setor para baixo		   
		   form.setorComercialDestinoID.value = ""; 
		   form.nomeSetorComercialDestino.value = "";		   
   		   form.setorComercialDestinoCD.value = "";
		   form.rotaInicial.value = "";
   		   form.rotaFinal.value = "";
	}
}


function validarClienteParaBloquear(){

	var form = document.forms[0];
	
		if(form.idCliente.value != ""){
			form.grupoCobranca.disabled = true;
		    form.gerenciaRegional.disabled = true;
		    form.unidadeNegocio.disabled = true;		    
	    	form.localidadeOrigemID.readOnly = true;
			form.localidadeDestinoID.readOnly = true;
        	form.setorComercialOrigemCD.readOnly = true;
	        form.setorComercialDestinoCD.readOnly = true;
    	    form.imagem.enabled = true;
	    	if(form.rotaInicial != undefined){
    	   		form.rotaInicial.readOnly = true;
    	   		form.rotaFinal.readOnly = true;
		    }
		}
}


function habilitarRota(){
	var form = document.forms[0];

	if(form.setorComercialDestinoCD.value == form.setorComercialOrigemCD.value ||
	form.setorComercialDestinoCD.lenght > 0){
	   form.rotaInicial.value = "";
 	   form.rotaFinal.value = "";
	   form.rotaInicial.readOnly = false;
 	   form.rotaFinal.readOnly = false;	
	}else{
	   form.rotaInicial.value = "";
 	   form.rotaFinal.value = "";	
	   form.rotaInicial.readOnly = true;
   	   form.rotaFinal.readOnly = true;	

	}
}


function recuperarDadosQuatroParametros(idRegistro, descricaoRegistro, codigoRegistro, tipoConsulta) {

	var form = document.forms[0];

	if (tipoConsulta == 'setorComercialOrigem') {
      form.setorComercialOrigemCD.value = codigoRegistro;
      form.setorComercialOrigemID.value = idRegistro;
      form.action = 'exibirFiltrarComandosAcaoCobrancaEventualAction.do?objetoConsulta=2&inscricaoTipo=origem'
      form.submit();
      
	  form.nomeSetorComercialOrigem.value = descricaoRegistro;
	  form.nomeSetorComercialOrigem.style.color = "#000000";
	  if(form.setorComercialDestinoCD.value == ""){
	      form.setorComercialDestinoCD.value = codigoRegistro;
    	  form.setorComercialDestinoID.value = idRegistro;
		  form.nomeSetorComercialDestino.value = descricaoRegistro;
		  form.nomeSetorComercialDestino.style.color = "#000000"; 
	  }
	  
	}

	if (tipoConsulta == 'setorComercialDestino') {
	
      form.setorComercialDestinoCD.value = codigoRegistro;
   	  form.setorComercialDestinoID.value = idRegistro;
      form.action = 'exibirFiltrarComandosAcaoCobrancaEventualAction.do?objetoConsulta=2&inscricaoTipo=destino'
      form.submit();
	  form.nomeSetorComercialDestino.value = descricaoRegistro;
	  form.nomeSetorComercialDestino.style.color = "#000000"; 
	}

}


function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {

    var form = document.forms[0];
    if (tipoConsulta == 'cobrancaAcaoAtividadeComando') {
      form.action = 'eexibirFiltrarComandosAcaoCobrancaEventualAction.do?idCobrancaAcaoAtividadeComando='+codigoRegistro
      form.submit();
    }
    

   	if (tipoConsulta == 'criterioCobranca') {
	   form.criterioCobranca.value = codigoRegistro;
       form.nomeCriterioCobranca.value = descricaoRegistro;
       form.nomeCriterioCobranca.style.color = "#000000";
       
    }
    
	if (tipoConsulta == 'localidadeOrigem') {
      form.localidadeOrigemID.value = codigoRegistro;
	  form.nomeLocalidadeOrigem.value = descricaoRegistro;
      form.action = 'exibirFiltrarComandosAcaoCobrancaEventualAction.do?objetoConsulta=1&inscricaoTipo=origem'
      form.submit();
	  form.nomeLocalidadeOrigem.style.color = "#000000";
	  if(form.localidadeDestinoID.value == ""){
	      form.localidadeDestinoID.value = codigoRegistro;
		  form.nomeLocalidadeDestino.value = descricaoRegistro;
		  form.nomeLocalidadeDestino.style.color = "#000000";
	  }
	}

	if (tipoConsulta == 'localidade') {
    	form.localidadeDestinoID.value = codigoRegistro;
        form.action = 'exibirFiltrarComandosAcaoCobrancaEventualAction.do?objetoConsulta=1&inscricaoTipo=destino'
		form.submit();
    	form.nomeLocalidadeDestino.value = descricaoRegistro;
 		form.nomeLocalidadeDestino.style.color = "#000000";    		
 		if(form.localidadeDestinoID.value > form.localidadeOrigemID.value){
		        form.setorComercialOrigemCD.readOnly = true;
		        form.setorComercialDestinoCD.readOnly = true;
		        form.imagem.enabled = true;
			    if(form.rotaInicial != undefined){
						form.rotaInicial.value = "";
						form.rotaFinal.value = "";

		    	   		form.rotaInicial.readOnly = true;
		    	   		form.rotaFinal.readOnly = true;
			    }
 		
 		}else{
		        form.setorComercialOrigemCD.readOnly = false;
		        form.setorComercialDestinoCD.readOnly = false;
		        form.imagem.enabled = false;
			    if(form.rotaInicial != undefined){
		    	   		form.rotaInicial.readOnly = false;
		    	   		form.rotaFinal.readOnly = false;
			    }
		} 		
         		
	}
	
     if (tipoConsulta == 'cliente') {
      form.idCliente.value = codigoRegistro;
      form.nomeCliente.value = descricaoRegistro;
      form.nomeCliente.style.color = "#000000";
      form.clienteRelacaoTipo.disabled = false;
      
    }	

}

function validarHabilitarCampo(){

	var form = document.forms[0];
	
		var ok = 1;
		if(form.localidadeOrigemID.value != ""){
			ok = 2;
		}
		if(form.localidadeDestinoID.value != ""){
			ok = 2;
		}
		if(form.idCliente.value != ""){
			ok = 2;
		}
		if(form.gerenciaRegional.value != "-1"){
			ok = 2;
		}
		if(form.grupoCobranca.value != ""){
			ok = 2;
		}
		if(form.unidadeNegocio.value != "-1"){
			ok = 2;
		}
		
		if(ok == 1){
			form.grupoCobranca.disabled = false;
		    form.gerenciaRegional.disabled = false;		    
		    form.unidadeNegocio.disabled = false;		    
			form.localidadeOrigemID.readOnly = false;
			form.localidadeDestinoID.readOnly = false;
			form.setorComercialOrigemCD.readOnly = false;
		    form.setorComercialDestinoCD.readOnly = false;
		    form.rotaInicial.readOnly = false;
    	    form.rotaFinal.readOnly = false;
        	form.nomeCliente.value = ""
        	form.idCliente.value = ""
        	form.idCliente.readOnly = false;
        	form.clienteRelacaoTipo.value = "";
        	form.clienteRelacaoTipo.disabled = false;
		}
}


function validarLocalidades(){
    var form = document.forms[0];
    
	if(form.localidadeDestinoID.value > form.localidadeOrigemID.value){
        form.setorComercialOrigemCD.readOnly = true;
        form.setorComercialDestinoCD.readOnly = true;
        form.imagem.enabled = true;
	    if(form.rotaInicial != undefined){
				form.rotaInicial.value = "";
				form.rotaFinal.value = "";
    	   		form.rotaInicial.readOnly = true;
    	   		form.rotaFinal.readOnly = true;
	    }
	}else{
        form.setorComercialOrigemCD.readOnly = false;
        form.setorComercialDestinoCD.readOnly = false;
        form.imagem.enabled = false;
	    if(form.rotaInicial != undefined){
    	   		form.rotaInicial.readOnly = false;
    	   		form.rotaFinal.readOnly = false;
	    }
	}
}

function limparCliente(){
  	  var form = document.forms[0];
      form.nomeCliente.value = ""
      form.idCliente.value = ""
      form.clienteRelacaoTipo.value = "";
      form.clienteRelacaoTipo.disabled = true;
      
      form.grupoCobranca.disabled = false;
      form.localidadeOrigemID.readOnly = false;
      form.localidadeDestinoID.readOnly = false;
	  form.setorComercialOrigemCD.readOnly = false;
      form.setorComercialDestinoCD.readOnly = false;
      
      form.gerenciaRegional.disabled = false;
      form.unidadeNegocio.disabled = false;            
      form.rotaInicial.readOnly = false;
      form.rotaFinal.readOnly = false;      
 	  formulario.clienteRelacaoTipo.disabled = true;
      
}

function pesquisarLocalidadeFinal(){
	if(validarExistenciaLocalidadeInicial()){
		chamarPopup('exibirPesquisarLocalidadeAction.do', 'destino', null, null, 275, 480, '',document.forms[0].localidadeDestinoID);
	}
	
}

function pesquisarSetorComercialFinal(){
	if(validarExistenciaSetorComercialInicial()){
		chamarPopup('exibirPesquisarSetorComercialAction.do', 'setorComercialDestino', 'idLocalidade', document.forms[0].localidadeDestinoID.value, 275, 480, 'Informe a Localidade da inscrição de destino',document.forms[0].setorComercialDestinoCD);
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
				abrirPopup(url + "?" + "tipo=" + tipo + "&" + objeto + "=" + codigoObjeto + "&caminhoRetornoTelaPesquisa=" + tipo, altura, largura);
			}
		}
	}
}

function validarExistenciaLocalidadeInicial(){
	var form = document.forms[0]; 
	if(form.localidadeOrigemID.value == ""){
		form.localidadeDestinoID.value = "";
		alert("Informe a Localidade Inicial");
		form.localidadeOrigemID.focus();
		return false;
	}
	return true;
}

function validarExistenciaSetorComercialInicial(){
	var form = document.forms[0]; 
	if(form.setorComercialOrigemCD.value == ""){
		form.setorComercialDestinoCD.value = "";
		alert("Informe o Setor Comercial Inicial");
		form.setorComercialOrigemCD.focus();
		return false;
	}
	return true;
}


function habilitacaoCampoRota(){
	var form = document.forms[0];
	
	var setorComercialOrigem = trim(form.setorComercialOrigemCD.value);
	var setorComercialDestino = trim(form.setorComercialDestinoCD.value);
	
	if (setorComercialOrigem.length > 0 && setorComercialDestino.length > 0){
		
		if (setorComercialOrigem != setorComercialDestino){
			form.rotaInicial.value = "";
			form.rotaFinal.value = "";
			form.rotaInicial.readOnly = true;
			form.rotaFinal.readOnly = true;
		}else{
			form.rotaInicial.readOnly = false;
			form.rotaFinal.readOnly = false;
		}
		
	}
}
  
function validarLocalidadeGerencia(){
    var form = document.forms[0];
    
    if(form.localidadeOrigemID.readOnly == false){
	    if ( form.localidadeOrigemID.value != ''){
	    	  form.gerenciaRegional.disabled = true;
	      	  form.unidadeNegocio.disabled = true;
	    }else{
	    	  form.unidadeNegocio.disabled = false;
	    	  form.gerenciaRegional.disabled = false;    	  
	    }
	 }
}
  
function desabilitarCobrancaAcao(){
    var form = document.forms[0];

  if(form.localidadeOrigemID.readOnly == false){ 	
    if (form.gerenciaRegional.value != "-1"  | form.localidadeOrigemID.value != ''  ){
     /// form.cobrancaGrupo.value = "";	
      form.grupoCobranca.disabled = true;
    }else{
      form.grupoCobranca.disabled = false;
    }
  }
}
function validarLocalidarFinalMaiorInicial(){

    var form = document.forms[0];
	if((form.localidadeDestinoID.value*1) > (form.localidadeOrigemID.value*1)){
        form.setorComercialOrigemCD.readOnly = true;
        form.setorComercialDestinoCD.readOnly = true;
        form.setorComercialOrigemCD.value = "";
        form.setorComercialDestinoCD.value = "";
        form.rotaInicial.value = "";
 	  	form.rotaFinal.value = "";	
	   	form.rotaInicial.readOnly = true;
   	  	form.rotaFinal.readOnly = true;	
        form.imagem.enabled = true;
	}else{
        form.setorComercialOrigemCD.readOnly = false;
        form.setorComercialDestinoCD.readOnly = false;
        form.imagem.enabled = false;
	    form.rotaInicial.value = "";
 	    form.rotaFinal.value = "";
	    form.rotaInicial.readOnly = false;
 	    form.rotaFinal.readOnly = false;	
	 }		
    
}  

function validarSetoresComerciais(){
    var form = document.forms[0];
    
	if(form.setorComercialDestinoCD.value > form.setorComercialOrigemCD.value){
        form.imagem.enabled = true;
	    if(form.rotaInicial != undefined){
				form.rotaInicial.value = "";
				form.rotaFinal.value = "";

    	   		form.rotaInicial.readOnly = true;
    	   		form.rotaFinal.readOnly = true;
	    }
	
	}else{
        form.imagem.enabled = false;
	    if(form.rotaInicial != undefined){
    	   		form.rotaInicial.readOnly = false;
    	   		form.rotaFinal.readOnly = false;
	    }
	}
}

function mensagem(mensagem){
	if(mensagem.length > 0){
		alert(mensagem);
	}
}

function limparCriterioCobranca(){
   var form = document.forms[0];
   form.criterioCobranca.value = "";
   form.nomeCriterioCobranca.value = "";
}


function pesquisarRota(destino){
   var form = document.forms[0];
   var msg = '';
   if(form.localidadeOrigemID.value == ""){
	   msg = 'Informe Localidade Inicial.\n';
   }
   if(form.localidadeDestinoID.value == ""){
	   msg = msg+ 'Informe Localidade Final.\n';
   }
   if(form.setorComercialOrigemCD.value == ""){
	   msg = msg +'Informe Setor Comercial Inicial.\n';
   }
   if(form.setorComercialDestinoCD.value == ""){
	   msg = msg+ 'Informe Setor Comercial Final.\n';
   }

   if( msg != '' ){

   }else{
		var msgDois = '';
		
	   if(form.localidadeOrigemID.value != form.localidadeDestinoID.value){
		   msgDois = 'Localidades diferentes.\n';
	   }
	   if(form.setorComercialOrigemCD.value != form.setorComercialDestinoCD.value){
		   msgDois = msgDois + 'Setores Comeriais diferentes.\n';
	   }
	   if( msgDois != '' ){
		}else{
			abrirPopup('exibirPesquisarRotaAction.do?idLocalidade='+form.localidadeOrigemID.value+'&codigoSetorComercial='+form.setorComercialOrigemCD.value+'&destino='+destino, 250, 495);
		}
	}
}


function desabilitarLocalidadeCobrancaAcao(){
  var form = document.forms[0];
  
  if (form.idCliente.readOnly == false){  
	  if (form.idCliente.value != ''  ){
	
	      form.grupoCobranca.value = "";	
	      form.grupoCobranca.disabled = true;
	      form.localidadeOrigemID.readOnly = true;
	      form.localidadeDestinoID.readOnly = true;
		  form.setorComercialOrigemCD.readOnly = true;
	      form.setorComercialDestinoCD.readOnly = true;
	      form.gerenciaRegional.disabled = true;
	      form.rotaInicial.readOnly = true;
	      form.rotaFinal.readOnly = true;      
	      form.unidadeNegocio.disabled = true;      
	
	  }else{
	      form.grupoCobranca.disabled = false;
	      form.localidadeOrigemID.readOnly = false;
	      form.localidadeDestinoID.readOnly = false;
		  form.setorComercialOrigemCD.readOnly = false;
	      form.setorComercialDestinoCD.readOnly = false;
	      form.gerenciaRegional.disabled = false;
	      form.unidadeNegocio.disabled = false;            
	      form.rotaInicial.readOnly = false;
	      form.rotaFinal.readOnly = false;      
	  }
	}
}


function validarInscricoes(){
   var form = document.forms[0];
   var msg = '';
   if(form.setorComercialOrigemCD.value != "" && form.localidadeOrigemID.value == ""){
	   msg = 'Informe Localidade Inicial.\n';
   }
   if(form.setorComercialDestinoCD.value != "" && form.localidadeDestinoID.value == ""){
	   msg = msg+ 'Informe Localidade Final.\n';
   }
   if(form.rotaInicial.value != "" && form.setorComercialOrigemCD.value == ""){
	   msg = msg +'Informe Setor Comercial Inicial.\n';
   }
   if(form.rotaFinal.value != "" &&form.setorComercialDestinoCD.value == ""){
	   msg = msg+ 'Informe Setor Comercial Final.\n';
   }

   if( msg != '' ){
   		alert(msg);
		return false;
   }else{
		return true;
   }
}

function replicaDados(campoOrigem, campoDestino){
	campoDestino.value = campoOrigem.value;
}

function validarExibicaoTipoCosumo(){

	var form = document.forms[0];

	if(form.consumoMedioInicial.value!="" && form.consumoMedioFinal.value!=""){
		form.tipoConsumo.disabled = false;
	}else{
		form.tipoConsumo.value = '1';
		form.tipoConsumo.disabled = true;;
	}
	
}

	function validarQuadraDiferentes(){
		var form = document.forms[0];
		
		if(form.numeroQuadraInicial.value!= '' && form.numeroQuadraFinal.value == ''){
			alert("Informe Quadra Final.");
			form.numeroQuadraFinal.focus();
			return false;
		}
		if(form.numeroQuadraInicial.value == '' && form.numeroQuadraFinal.value != ''){
			alert("Informe Quadra Inicial.");
			form.numeroQuadraInicial.focus();
			return false;
		}
		
		if(form.numeroQuadraInicial.value != '' && form.setorComercialOrigemCD.value == ''){
			alert("Informe Setor Comercial Inicial.");
			form.setorComercialOrigemCD.focus();
			return false;
		}
	
		if(form.numeroQuadraFinal.value != '' && form.setorComercialDestinoCD.value == ''){
			alert("Informe Setor Comercial Final.");
			form.setorComercialDestinoCD.focus();
			return false;
		}
		
		return true;
	}

	
	function validarQuadra(nomeCampo, idDependencia, nomeDependencia){
      
		var form = document.forms[0];
		
		var objetoCampo = nomeCampo;
		var valorCampo = nomeCampo.value;
	
		if(idDependencia.length < 1 || isNaN(idDependencia) || ((idDependencia * 1) == 0) ||
		   idDependencia.indexOf(',') != -1 || idDependencia.indexOf('.') != -1 || ((idDependencia * 1) == 0)){
			
			alert('Informe ' + nomeDependencia);
			form.setorComercialOrigemCD.focus();
			
		}
	}


-->
</script>
</head>
<body leftmargin="5" topmargin="5"
	onload="javascript:mensagem('${requestScope.mensagem}');setarFoco('${requestScope.nomeCampo}');validarLocalidarFinalMaiorInicial();habilitarRota();validarExibicaoTipoCosumo();">

<form action="/exibirFiltrarComandosAcaoCobrancaEventualAction"
	name="FiltrarComandosAcaoCobrancaEventualActionForm" method="post">
	
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
		<table height="100%">
			<tr>
				<td></td>
			</tr>
		</table>
		<table width="100%" border="0" align="center" cellpadding="0"
			cellspacing="0">
			<tr>
				<td width="11"><img border="0" src="imagens/parahead_left.gif" /></td>
				<td class="parabg">Filtrar Comandos de A&ccedil;&atilde;o de
				Cobran&ccedil;a - Comandos Eventuais</td>
				<td width="11" valign="top"><img border="0"
					src="imagens/parahead_right.gif" /></td>
			</tr>
		</table>
		<!--Fim Tabela Reference a Páginação da Tela de Processo-->
		<p>&nbsp;</p>
		<table width="100%" border="0" dwcopytype="CopyTableRow">
			<tr>
				<td><strong>Per&iacute;odo de Emiss&atilde;o:</strong></td>
				<td colspan="3"><strong>
					<html:text  name="FiltrarComandosAcaoCobrancaEventualActionForm"
								property="dataEmissaoInicio"
								size="10"
								onkeyup="mascaraData(this, event); replicaDados(document.FiltrarComandosAcaoCobrancaEventualActionForm.dataEmissaoInicio, document.FiltrarComandosAcaoCobrancaEventualActionForm.dataEmissaoFim);somente_numero(this);"
								onkeypress="return isCampoNumerico(event);"
								maxlength="10" />
						<a href="javascript:abrirCalendario('FiltrarComandosAcaoCobrancaEventualActionForm', 'dataEmissaoInicio')">
						  	<img border="0"
								 src="<bean:message key='caminho.imagens'/>calendario.gif"
								 width="20" 
								 border="0" 
								 align="absmiddle" 
								 title="Exibir Calendário" />
						</a>
						a</strong>
					<html:text  name="FiltrarComandosAcaoCobrancaEventualActionForm"
								property="dataEmissaoFim" 
								size="10"
								maxlength="10" 
								onkeypress="return isCampoNumerico(event);"
								onkeyup="mascaraData(this, event);" />
						<a href="javascript:abrirCalendario('FiltrarComandosAcaoCobrancaEventualActionForm', 'dataEmissaoFim')">
							<img border="0"
							src="<bean:message key='caminho.imagens'/>calendario.gif"
							width="20" 
							border="0" 
							align="absmiddle" 
							title="Exibir Calendário" />
						</a>
						dd/mm/aaaa</td>
			</tr>
			
			<tr>
				<td><strong>T&iacute;tulo do Comando:</strong></td>
				<td colspan="4">
				<div style="width : 320px; overflow-x:scroll; overflow: -moz-scrollbars-horizontal;">
				<html:select property="idCobrancaAcaoAtividadeComando" tabindex="1"
					name="FiltrarComandosAcaoCobrancaEventualActionForm"
					multiple="mutiple" size="4">
					<logic:present name="colecaoCobrancaAcaoAtividadeComando">
						<html:option value="" />
						<html:options collection="colecaoCobrancaAcaoAtividadeComando"
							labelProperty="descricaoTitulo" property="id" />
					</logic:present>
				</html:select>
				</div></td>
			</tr>
			<tr>
				<td><strong>A&ccedil;&atilde;o de Cobran&ccedil;a:</strong></td>
				<td colspan="4"><html:select property="acaoCobranca" tabindex="1"
					name="FiltrarComandosAcaoCobrancaEventualActionForm"
					style="width: 320px;" multiple="mutiple" size="2">
					<logic:present name="colecaoAcaoCobranca">
						<html:option value="" />
						<html:options collection="colecaoAcaoCobranca"
							labelProperty="descricaoCobrancaAcao" property="id" />
					</logic:present>
				</html:select></td>
			</tr>
			<tr>
				<td height="17" colspan="1"><strong>Indicador de Critério:</strong></td>
				<td colspan="3"> 
				<strong> <html:radio property="indicadorCriterio" tabindex="2"
					onclick="validarIndicadorCriterio();"
					name="FiltrarComandosAcaoCobrancaEventualActionForm" value="Todos" />
				Todos </strong>
				<strong> <html:radio property="indicadorCriterio" tabindex="3"
					onclick="validarIndicadorCriterio();"
					name="FiltrarComandosAcaoCobrancaEventualActionForm" value="Rota" />
				Crit&eacute;rio da Rota </strong>
				<strong> <html:radio property="indicadorCriterio" tabindex="4"
					onclick="validarIndicadorCriterio();"
					name="FiltrarComandosAcaoCobrancaEventualActionForm"
					value="Comando" /> Crit&eacute;rio de Cobran&ccedil;a do Comando</strong></td>
			</tr>
			<tr>
				<td><strong>Crit&eacute;rio de Cobran&ccedil;a:</strong></td>
				<td width="81%" align="right" colspan="4">
				<div align="left">
					<html:text  maxlength="4"
								property="criterioCobranca" 
								size="4" 
								tabindex="5"
								name="FiltrarComandosAcaoCobrancaEventualActionForm"
								onkeypress="validaEnter(event, 'exibirFiltrarComandosAcaoCobrancaEventualAction.do','criterioCobranca');return isCampoNumerico(event);" />
						<img src="<bean:message key='caminho.imagens'/>pesquisa.gif"
							width="23" 
							height="21" 
							style="cursor:hand;cursor:pointer;" 
							name="imagem"
							onclick="chamarPopup('exibirPesquisarCriterioCobrancaAction.do?limpaForm=1', 'origem', null, null, 275, 480, '',document.forms[0].criterioCobranca);"
							title="Pesquisar Critério de Cobrança"> <strong> 
					<logic:present name="corCriterioCobranca">
						<logic:equal name="corCriterioCobranca" value="exception">
							<html:text  property="nomeCriterioCobranca" 
										size="40"
										maxlength="40" readonly="true"
										name="FiltrarComandosAcaoCobrancaEventualActionForm"
										style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:equal>

						<logic:notEqual name="corCriterioCobranca" value="exception">
							<html:text  property="nomeCriterioCobranca" 
										size="40"
										maxlength="40" 
										readonly="true"
										name="FiltrarComandosAcaoCobrancaEventualActionForm"
										style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEqual>

					</logic:present> 
					<logic:notPresent name="corCriterioCobranca">

						<logic:empty name="FiltrarComandosAcaoCobrancaEventualActionForm" property="criterioCobranca">
							<html:text  property="nomeCriterioCobranca" 
										value="" 
										size="40"
										maxlength="40" 
										readonly="true"
										name="FiltrarComandosAcaoCobrancaEventualActionForm"
										style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:empty>
						<logic:notEmpty name="FiltrarComandosAcaoCobrancaEventualActionForm"
							property="criterioCobranca">
							<html:text  property="nomeCriterioCobranca" 
										size="40"
										maxlength="40" 
										readonly="true"
										name="FiltrarComandosAcaoCobrancaEventualActionForm"
										style="background-color:#EFEFEF; border:0; color: 	#000000" />
						</logic:notEmpty>
				</logic:notPresent> 
					<img src="<bean:message key='caminho.imagens'/>limparcampo.gif"
						 title="Apagar" 
						 style="cursor:hand;cursor:pointer;"
						 onclick="limparCriterioCobranca()" 
						 name="imagem"></strong></div>
				</td>
			</tr>
			<tr>
				<td><strong>Atividade de Cobran&ccedil;a:</strong></td>
				<td colspan="4"><html:select property="atividadeCobranca"
					tabindex="6" name="FiltrarComandosAcaoCobrancaEventualActionForm"
					style="width: 230px;" multiple="mutiple" size="2">
					<logic:present name="colecaoAtividadeCobranca">
						<html:option value="" />
						<html:options collection="colecaoAtividadeCobranca"
							labelProperty="descricaoCobrancaAtividade" property="id" />
					</logic:present>
				</html:select></td>
			</tr>
			<tr>
				<td colspan="5">
				<hr>
				</td>
			</tr>
			<tr>
				<td><strong>Grupo de Cobran&ccedil;a:</strong></td>
				<td colspan="4"><html:select property="grupoCobranca" tabindex="7"
					onchange="validarGrupoCobranca(1);"
					name="FiltrarComandosAcaoCobrancaEventualActionForm"
					style="width: 230px;" multiple="mutiple" size="2">
					<logic:present name="colecaoGrupoCobranca">
						<html:option value="" />
						<html:options collection="colecaoGrupoCobranca"
							labelProperty="descricao" property="id" />
					</logic:present>
				</html:select></td>
			</tr>
			<tr>
				<td colspan="5">
				<hr>
				</td>
			</tr>
			<tr>
				<td colspan="5"><strong>Dados de Localiza&ccedil;&atilde;o
				Geogr&aacute;fica </strong></td>
			</tr>
			<tr>
				<td height="24"><strong>Ger&ecirc;ncia Regional:<strong></strong></strong></td>
				<td><div align="left">
					<html:select property="gerenciaRegional" style="width: 200px;"
					tabindex="8" name="FiltrarComandosAcaoCobrancaEventualActionForm"
					onchange="javaScript:validarGerenciaRegionalSelecionado();">
						<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
				         <logic:iterate name="colecaoGerenciaRegional" id="colecaoGerenciasRegionais">
							   <html:option value="${colecaoGerenciasRegionais.id}">
					           <bean:write name="colecaoGerenciasRegionais" property="nomeAbreviado"/> 
					           - <bean:write name="colecaoGerenciasRegionais" property="nome"/>
						      </html:option>
				         </logic:iterate>
			        </html:select>
			        </div>
			    </td>
			</tr>

			<tr>
				<td height="24"><strong>Unidade Negócio:<strong></strong></strong></td>
				<td><div align="left">
					<html:select property="unidadeNegocio" style="width: 200px;"
					tabindex="8" name="FiltrarComandosAcaoCobrancaEventualActionForm"
					onchange="javaScript:validarUnidadeNegocio(1);desabilitarCobrancaAcao();">
						<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
				         <logic:iterate name="colecaoUnidadeNegocio" id="colecaoUnidadeNegocios">
							   <html:option value="${colecaoUnidadeNegocios.id}">
					           <bean:write name="colecaoUnidadeNegocios" property="nomeAbreviado"/> 
					           - <bean:write name="colecaoUnidadeNegocios" property="nome"/>
					          </html:option>
				         </logic:iterate>
			        </html:select>
			        </div>
			    </td>
			</tr>

			<tr>
				<td colspan="5">
				<hr>
				</td>
			</tr>
			<html:hidden property="inscricaoTipo"
				name="FiltrarComandosAcaoCobrancaEventualActionForm" />
			<tr>
				<td ><strong>Localidade Inicial:</strong></td>
				<td align="right" colspan="4">
					<div align="left">
						<html:text  maxlength="3"
									property="localidadeOrigemID" 
									size="3" 
									tabindex="9"
									name="FiltrarComandosAcaoCobrancaEventualActionForm"
									onkeypress="limparDestino(1);
									validaEnterComMensagem(event, 'exibirFiltrarComandosAcaoCobrancaEventualAction.do?objetoConsulta=1&inscricaoTipo=origem',
								    'localidadeOrigemID', 'Localidade Inicial');return isCampoNumerico(event);"
									onkeyup="javaScript:desabilitarCobrancaAcao();validarLocalidadeGerencia();" 
									onblur="duplicarLocalidade();"/> 
					
							<img src="<bean:message key='caminho.imagens'/>pesquisa.gif" 
								 width="23"
								 height="21" style="cursor:hand;cursor:pointer;" 
								 name="imagem"
								 onclick="chamarPopup('exibirPesquisarLocalidadeAction.do', 'origem', null, null, 275, 480, '',document.forms[0].localidadeOrigemID);"
								 title="Pesquisar Localidade"> <strong> 
						<logic:present name="corLocalidadeOrigem">
							<logic:equal name="corLocalidadeOrigem" value="exception">
								<html:text property="nomeLocalidadeOrigem" size="40"
									maxlength="40" readonly="true"
									name="FiltrarComandosAcaoCobrancaEventualActionForm"
									style="background-color:#EFEFEF; border:0; color: #ff0000" />
							</logic:equal>
		
							<logic:notEqual name="corLocalidadeOrigem" value="exception">
								<html:text property="nomeLocalidadeOrigem" size="40"
									maxlength="40" readonly="true"
									name="FiltrarComandosAcaoCobrancaEventualActionForm"
									style="background-color:#EFEFEF; border:0; color: #000000" />
							</logic:notEqual>

					</logic:present> 
					<logic:notPresent name="corLocalidadeOrigem">

						<logic:empty name="FiltrarComandosAcaoCobrancaEventualActionForm"
							property="localidadeOrigemID">
							<html:text property="nomeLocalidadeOrigem" value="" size="40"
								maxlength="40" readonly="true"
								name="FiltrarComandosAcaoCobrancaEventualActionForm"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:empty>
						<logic:notEmpty
							name="FiltrarComandosAcaoCobrancaEventualActionForm"
							property="localidadeOrigemID">
							<html:text property="nomeLocalidadeOrigem" size="40"
								maxlength="40" readonly="true"
								name="FiltrarComandosAcaoCobrancaEventualActionForm"
								style="background-color:#EFEFEF; border:0; color: 	#000000" />
						</logic:notEmpty>
					</logic:notPresent> 
				
						<a  href="javascript:limparOrigem(1)"> 
							<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
								 border="0" 
								 title="Apagar" />
						</a>
				</strong></div>
				</td>

			</tr>

			<tr>
				<td><strong>Setor Comercial Inicial: </strong></td>
				<td align="right" colspan="4">
				<div align="left">
					<html:text maxlength="3" 
							   tabindex="10"
							   name="FiltrarComandosAcaoCobrancaEventualActionForm"
							   property="setorComercialOrigemCD" size="3"
							   onblur="javascript:duplicarSetorComercial();habilitarRota();"
							   onkeypress="limparDestino(2);
						       validaEnterDependenciaComMensagem(event, 'exibirFiltrarComandosAcaoCobrancaEventualAction.do?objetoConsulta=2&inscricaoTipo=origem&validarCriterio=naoAcao', document.forms[0].setorComercialOrigemCD, document.forms[0].localidadeOrigemID.value, 'Localidade Inicial', 'Setor Comercial Inicial');
						       return isCampoNumerico(event);"
							   onfocus="validarLocalidade();" /> 
						<img src="<bean:message key='caminho.imagens'/>pesquisa.gif" width="23"
							 height="21" 
							 style="cursor:hand;cursor:pointer;" 
							 name="imagem"
							 onclick="chamarPopup('exibirPesquisarSetorComercialAction.do', 
							 'setorComercialOrigem', 'idLocalidade', document.forms[0].localidadeOrigemID.value, 275, 480, 
							 'Informe a Localidade da inscrição de origem',document.forms[0].setorComercialOrigemCD);"
							 title="Pesquisar Setor Comercial">
				<logic:present name="corSetorComercialOrigem">
					<logic:equal name="corSetorComercialOrigem" value="exception">
						<html:text property="nomeSetorComercialOrigem" size="40"
							name="FiltrarComandosAcaoCobrancaEventualActionForm"
							maxlength="40" readonly="true"
							style="background-color:#EFEFEF; border:0; color: #ff0000" />
					</logic:equal>
					<logic:notEqual name="corSetorComercialOrigem" value="exception">
						<html:text property="nomeSetorComercialOrigem" size="40"
							name="FiltrarComandosAcaoCobrancaEventualActionForm"
							maxlength="40" readonly="true"
							style="background-color:#EFEFEF; border:0; color: #000000" />
					</logic:notEqual>
				</logic:present> <logic:notPresent name="corSetorComercialOrigem">
					<logic:empty name="FiltrarComandosAcaoCobrancaEventualActionForm"
						property="setorComercialOrigemCD">
						<html:text property="nomeSetorComercialOrigem" value="" size="40"
							maxlength="40" readonly="true"
							name="FiltrarComandosAcaoCobrancaEventualActionForm"
							style="background-color:#EFEFEF; border:0; color: #ff0000" />
					</logic:empty>
					<logic:notEmpty
						name="FiltrarComandosAcaoCobrancaEventualActionForm"
						property="setorComercialOrigemCD">
						<html:text property="nomeSetorComercialOrigem" size="40"
							maxlength="40" readonly="true"
							name="FiltrarComandosAcaoCobrancaEventualActionForm"
							style="background-color:#EFEFEF; border:0; color: #000000" />
					</logic:notEmpty>

				</logic:notPresent> 
					<a  href="javascript:limparOrigem(2);"> 
						<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
							 border="0" 
							 title="Apagar" />
					</a> <html:hidden property="setorComercialOrigemID" name="FiltrarComandosAcaoCobrancaEventualActionForm" />
				</div>
				</td>
			</tr>
			
			<tr>
				<td><strong>Quadra Inicial:</strong></td>
				<td align="left" colspan="4">
			  		<html:text maxlength="5"
			  			name="FiltrarComandosAcaoCobrancaEventualActionForm"
						property="numeroQuadraInicial" 
						size="5" 
						tabindex="13"
           				onblur="validarQuadra(this, document.forms[0].setorComercialOrigemCD.value, 'Setor Comercial Inicial');"
           				onkeypress="return isCampoNumerico(event);"  />
                  </td>					
			</tr>			
			
			
			<tr>
				<td><strong>Rota Inicial:<font color="#FF0000"> </font></strong></td>
				<td align="right" colspan="4">
				<div align="left">
						<html:text maxlength="4" 
								   tabindex="11"
								   property="rotaInicial" 
								   size="4"
								   onchange="duplicarRota()" 
								   onkeypress="return isCampoNumerico(event);"
								   name="FiltrarComandosAcaoCobrancaEventualActionForm"/> 
							<a href="javascript:pesquisarRota('inicial');">
								<img width="23" 
									 height="21" 
									 border="0"
 									 style="cursor:hand;cursor:pointer;"
 									 src="<bean:message key="caminho.imagens"/>pesquisa.gif"
									 title="Pesquisar Rota" />
							</a>						
				</div>
				<div align="left"><strong></strong></div>
				<div align="left"></div>
				</td>
			</tr>
			<tr>
				<td colspan="5">
				<hr>
				</td>
			</tr>
			<tr>
				<td colspan="5">
				<hr>
				</td>
			</tr>
			<tr>
				<td><strong>Localidade Final:<font color="#FF0000"> </font></strong></td>
				<td width="37%" align="left" colspan="4">
				<html:text  maxlength="3"
							property="localidadeDestinoID" 
							size="3" 
							tabindex="12"
							name="FiltrarComandosAcaoCobrancaEventualActionForm"
							onkeyup="javascript:validarExistenciaLocalidadeInicial();validarLocalidarFinalMaiorInicial();"
							onkeypress="validaEnterComMensagem(event, 'exibirFiltrarComandosAcaoCobrancaEventualAction.do?objetoConsulta=1&inscricaoTipo=destino&validarCriterio=naoAcao', 'localidadeDestinoID', 'Localidade Final');
							return isCampoNumerico(event);" />
					<img src="<bean:message key='caminho.imagens'/>pesquisa.gif"
						width="23" 
						height="21" 
						border="0" 
						style="cursor:hand;cursor:pointer;"
						name="imagem"
						onclick="pesquisarLocalidadeFinal();"
						alt="Pesquisar"> 
						
					<logic:present name="corLocalidadeDestino">

					<logic:equal name="corLocalidadeDestino" value="exception">
						<html:text property="nomeLocalidadeDestino" size="40"
							maxlength="40" readonly="true"
							name="FiltrarComandosAcaoCobrancaEventualActionForm"
							style="background-color:#EFEFEF; border:0; color: #ff0000" />
					</logic:equal>

					<logic:notEqual name="corLocalidadeDestino" value="exception">
						<html:text property="nomeLocalidadeDestino" size="40"
							maxlength="40" readonly="true"
							name="FiltrarComandosAcaoCobrancaEventualActionForm"
							style="background-color:#EFEFEF; border:0; color: #000000" />
					</logic:notEqual>

				</logic:present> <logic:notPresent name="corLocalidadeDestino">

					<logic:empty name="FiltrarComandosAcaoCobrancaEventualActionForm"
						property="localidadeDestinoID">
						<html:text property="nomeLocalidadeDestino" value="" size="40"
							maxlength="40" readonly="true"
							name="FiltrarComandosAcaoCobrancaEventualActionForm"
							style="background-color:#EFEFEF; border:0; color: #ff0000" />
					</logic:empty>
					<logic:notEmpty
						name="FiltrarComandosAcaoCobrancaEventualActionForm"
						property="localidadeDestinoID">
						<html:text property="nomeLocalidadeDestino" size="40"
							maxlength="40" readonly="true"
							name="FiltrarComandosAcaoCobrancaEventualActionForm"
							style="background-color:#EFEFEF; border:0; color: 	#000000" />
					</logic:notEmpty>
				</logic:notPresent> 
				<a href="javascript:limparBorrachaDestino(1);"> <img
				src="<bean:message key="caminho.imagens"/>limparcampo.gif"
				border="0" title="Apagar" /></a> 				
				</td>
			</tr>
			<tr>
				<td><strong>Setor Comercial Final: </strong></td>
				<td align="left" colspan="4">
					<html:text  maxlength="3"
								property="setorComercialDestinoCD" size="3" tabindex="13"
								name="FiltrarComandosAcaoCobrancaEventualActionForm"
								onkeyup="javascript:validarExistenciaSetorComercialInicial();habilitarRota();"
								onkeypress="validaEnterDependenciaComMensagem(event, 'exibirFiltrarComandosAcaoCobrancaEventualAction.do?objetoConsulta=2&inscricaoTipo=destino&validarCriterio=naoAcao', document.forms[0].setorComercialDestinoCD, document.forms[0].localidadeDestinoID.value, 'Localidade Final', 'Setor Comercial Final');return isCampoNumerico(event);"
								onfocus="validarLocalidade();" /> 
						<img src="<bean:message key='caminho.imagens'/>pesquisa.gif" 
							 width="23"
							 height="21" 
							 style="cursor:hand;cursor:pointer" 
							 name="imagem"
							 onclick="pesquisarSetorComercialFinal();"
							 title="Pesquisar Setor Comercial"> 
							 
					<logic:present name="corSetorComercialDestino">
						<logic:equal name="corSetorComercialDestino" value="exception">
							<html:text property="nomeSetorComercialDestino" size="40"
								maxlength="40" readonly="true"
								name="FiltrarComandosAcaoCobrancaEventualActionForm"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:equal>
						<logic:notEqual name="corSetorComercialDestino" value="exception">
							<html:text property="nomeSetorComercialDestino" size="40"
								maxlength="40" readonly="true"
								name="FiltrarComandosAcaoCobrancaEventualActionForm"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEqual>
					</logic:present> 
					
					<logic:notPresent name="corSetorComercialDestino">
						<logic:empty name="FiltrarComandosAcaoCobrancaEventualActionForm"
							property="setorComercialDestinoCD">
							<html:text property="nomeSetorComercialDestino" size="40"
								maxlength="40" readonly="true"
								name="FiltrarComandosAcaoCobrancaEventualActionForm"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:empty>
						<logic:notEmpty
							name="FiltrarComandosAcaoCobrancaEventualActionForm"
							property="setorComercialDestinoCD">
							<html:text property="nomeSetorComercialDestino" size="40"
								maxlength="40" readonly="true"
								name="FiltrarComandosAcaoCobrancaEventualActionForm"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEmpty>
					</logic:notPresent> 
					<html:hidden property="setorComercialDestinoID" name="FiltrarComandosAcaoCobrancaEventualActionForm" /> 
					<a href="javascript:limparBorrachaDestino(2);"> 
						<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
							 border="0" 
							 title="Apagar" />
					</a>
				</td>
			</tr>

			<tr>
				<td><strong>Quadra Final:</strong></td>
				<td align="left" colspan="4">
			  		<html:text maxlength="5"
			  			name="FiltrarComandosAcaoCobrancaEventualActionForm"
						property="numeroQuadraFinal" 
						size="5" 
						tabindex="13"
           				onblur="validarQuadra(this, document.forms[0].setorComercialDestinoCD.value, 'Setor Comercial Final');"
           				onkeypress="return isCampoNumerico(event);"  />
                  </td>					
			</tr>			
			
			<tr>
				<td><strong>Rota Final:<font color="#FF0000"> </font></strong></td>
				<td align="right" colspan="4">
				<div align="left"><strong>
						<html:text  maxlength="4" 
									tabindex="14"
									property="rotaFinal" 
									size="4"
									onkeypress="return isCampoNumerico(event);"
									name="FiltrarComandosAcaoCobrancaEventualActionForm"/> 
							<a href="javascript:pesquisarRota('final');">
								<img width="23" 
									 height="21" 
									 border="0"
									 style="cursor:hand;cursor:pointer;"
									 src="<bean:message key="caminho.imagens"/>pesquisa.gif"
									 title="Pesquisar Rota" />
							</a>						
				</strong></div>
				<div align="left"></div>
				</td>
			</tr>
			<tr>
				<td colspan="5">
				<hr>
				</td>
			</tr>
			<tr>
				<td><strong>Cliente:</strong></td>
				<td colspan="4"><strong> 
					<html:text  maxlength="9"
								property="idCliente" 
								size="9" 
								tabindex="15"
								name="FiltrarComandosAcaoCobrancaEventualActionForm"
								onkeyup="javaScript:validarCliente(1);desabilitarLocalidadeCobrancaAcao();"
								name="FiltrarComandosAcaoCobrancaEventualActionForm"
								onkeypress="javascript:validaEnterComMensagem(event, 'exibirFiltrarComandosAcaoCobrancaEventualAction.do?objetoConsulta=3&inscricaoTipo=destino&validarCriterio=naoAcao', 'idCliente', 'Cliente');return isCampoNumerico(event);" />
						<a href="javascript:abrirPopup('exibirPesquisarClienteAction.do', 400, 800);">
							<img border="0"
								 src="<bean:message key="caminho.imagens"/>pesquisa.gif"
								 title="Pesquisar Cliente" />
						</a> 
					<logic:present name="codigoClienteNaoEncontrado" scope="request">
						<input type="text" 
							   name="nomeCliente" 
							   size="40" 
							   readonly="true"
							   name="FiltrarComandosAcaoCobrancaEventualActionForm"
							   style="background-color:#EFEFEF; border:0; color: #ff0000"
							   value="CLIENTE INEXISTENTE" />
					</logic:present> 
					<logic:notPresent name="codigoClienteNaoEncontrado" scope="request">
						<html:text property="nomeCliente" 
								   size="40" 
								   readonly="true"
								   name="FiltrarComandosAcaoCobrancaEventualActionForm"
								   style="background-color:#EFEFEF; border:0; color: #000000" />
					</logic:notPresent> 
						<a href="javascript:limparCliente();"> 
							<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
								 border="0" 
								 title="Apagar" />
						</a> </strong></td>
			</tr>
			<tr>
				<td><strong>Tipo de Rela&ccedil;&atilde;o:</strong></td>
				<td colspan="4"><strong> <html:select tabindex="16"
					name="FiltrarComandosAcaoCobrancaEventualActionForm"
					property="clienteRelacaoTipo" disabled="true">
					<html:option value="">&nbsp;</html:option>
					<html:options name="request" collection="colecaoClienteRelacaoTipo"
						labelProperty="descricao" property="id" />
				</html:select> </strong></td>
			</tr>
			<tr>
				<td colspan="5">
				<hr>
				</td>
			</tr>
			<tr>
				<td height="17"><strong>Per&iacute;odo do Comando</strong><strong>:</strong></td>
				<td colspan="4"><strong> 
					<html:text maxlength="10" 
							   tabindex="17"
							   name="FiltrarComandosAcaoCobrancaEventualActionForm"
							   property="periodoComandoInicial" 
							   size="10"
							   onkeypress="return isCampoNumerico(event);"
							   onkeyup="javascript:mascaraData(this, event);duplicaPeriodoComando();" />
						<a href="javascript:abrirCalendarioReplicando('FiltrarComandosAcaoCobrancaEventualActionForm', 'periodoComandoInicial',
							'periodoComandoFinal')">
							<img border="0"
								src="<bean:message key='caminho.imagens'/>calendario.gif"
								width="20" border="0" align="middle" title="Exibir Calendário" />
						</a>
				<strong> a</strong> 
					<html:text  maxlength="10"
								name="FiltrarComandosAcaoCobrancaEventualActionForm" 
								tabindex="18"
								property="periodoComandoFinal" 
								size="10"
								onkeypress="return isCampoNumerico(event);"
								onkeyup="javascript:mascaraData(this, event);" /> 
						<a href="javascript:abrirCalendario('FiltrarComandosAcaoCobrancaEventualActionForm', 'periodoComandoFinal')">
							<img border="0"
								src="<bean:message key='caminho.imagens'/>calendario.gif"
								width="20" 
								border="0" 
								align="middle" 
								title="Exibir Calendário" />
						</a>
				</strong></td>
			</tr>
			<tr>
				<td height="17"><strong>Per&iacute;odo de Realiza&ccedil;&atilde;o
				do Comando</strong><strong>:</strong></td>
				<td colspan="4"><strong> 
					<html:text  maxlength="10" 
								tabindex="19"
								name="FiltrarComandosAcaoCobrancaEventualActionForm"
								property="periodoRealizacaoComandoInicial" 
								size="10"
								onkeypress="return isCampoNumerico(event);"
								onkeyup="javascript:mascaraData(this, event);duplicaPeriodoRealizacaoComando();" />
						<a href="javascript:abrirCalendarioReplicando('FiltrarComandosAcaoCobrancaEventualActionForm', 'periodoRealizacaoComandoInicial',
							'periodoRealizacaoComandoFinal')">
							<img border="0"
								 src="<bean:message key='caminho.imagens'/>calendario.gif"
								 width="20" border="0" align="middle" title="Exibir Calendário" />
						</a>
				<strong> a</strong> 
					<html:text  maxlength="10" tabindex="20"
								name="FiltrarComandosAcaoCobrancaEventualActionForm"
								property="periodoRealizacaoComandoFinal" size="10"
								onkeyup="javascript:mascaraData(this, event);"
								onkeypress="return isCampoNumerico(event);" /> 
						<a href="javascript:abrirCalendario('FiltrarComandosAcaoCobrancaEventualActionForm', 'periodoRealizacaoComandoFinal')">
							<img border="0"
								src="<bean:message key='caminho.imagens'/>calendario.gif"
								width="20" border="0" align="middle" title="Exibir Calendário" />
						</a>
				</strong></td>
			</tr>
			<tr>
				<td width="29%"><strong>Per&iacute;odo de Refer&ecirc;ncia das
				Contas:</strong></td>
				<td colspan="4"><strong><strong> 
					<html:text  maxlength="7" tabindex="21"
								name="FiltrarComandosAcaoCobrancaEventualActionForm"
								property="periodoReferenciaContasInicial" size="7"
								onkeypress="return isCampoNumerico(event);"
								onkeyup="javascript:mascaraAnoMes(this, event);duplicaPeriodoReferenciaContas();" />
				</strong> </strong>mm/aaaa<strong><strong><strong>a</strong> 
					<html:text  maxlength="7" 
								name="FiltrarComandosAcaoCobrancaEventualActionForm" 
								tabindex="22"
								property="periodoReferenciaContasFinal" 
								onkeypress="return isCampoNumerico(event);"
								size="7"
								onkeyup="javascript:mascaraAnoMes(this, event);" /> </strong> </strong>mm/aaaa<strong>
				</strong></td>
			</tr>
			<tr>
				<td height="17"><strong>Per&iacute;odo de Vencimento das Contas</strong><strong>:</strong></td>
				<td colspan="4"><strong> <html:text maxlength="10" tabindex="23"
					name="FiltrarComandosAcaoCobrancaEventualActionForm"
					property="periodoVencimentoContasInicial" size="10"
					onkeypress="return isCampoNumerico(event);"				
					onkeyup="javascript:mascaraData(this, event);duplicaPeriodoVencimentoContas();" />
				<a
					href="javascript:abrirCalendarioReplicando('FiltrarComandosAcaoCobrancaEventualActionForm', 'periodoVencimentoContasInicial',
					'periodoVencimentoContasFinal')">
				<img border="0"
					src="<bean:message key='caminho.imagens'/>calendario.gif"
					width="20" border="0" align="middle" alt="Exibir Calendário" /></a>
				<strong> a</strong> <html:text maxlength="10" tabindex="24"
					name="FiltrarComandosAcaoCobrancaEventualActionForm"
					property="periodoVencimentoContasFinal" size="10"
					onkeypress="return isCampoNumerico(event);"
					onkeyup="javascript:mascaraData(this, event);" /> <a
					href="javascript:abrirCalendario('FiltrarComandosAcaoCobrancaEventualActionForm', 'periodoVencimentoContasFinal')">
				<img border="0"
					src="<bean:message key='caminho.imagens'/>calendario.gif"
					width="20" border="0" align="middle" alt="Exibir Calendário" /></a>
				</strong></td>
			</tr>
			<tr>
				<td colspan="5">
				<hr>
				</td>
			</tr>
			<tr>
						<td><strong>Consumo Médio do Imóvel:</strong></td>
						<td colspan="3"><strong> <html:text maxlength="7"
							name="FiltrarComandosAcaoCobrancaEventualActionForm"
							property="consumoMedioInicial" size="7" tabindex="13"
							onkeyup="replicarCampo(document.forms[0].consumoMedioFinal,this);validarExibicaoTipoCosumo();"
							onkeypress="return isCampoNumerico(event);"/><strong> a</strong> <html:text
							name="FiltrarComandosAcaoCobrancaEventualActionForm" tabindex="14"
							maxlength="7" property="consumoMedioFinal" size="7"					
							onkeypress="validarExibicaoTipoCosumo();return isCampoNumerico(event);" /> </strong>
						</td>
			</tr>
			<tr>
						<td><strong>Tipo de Consumo:</strong></td>
						<td colspan="3"><strong> 
							<html:select tabindex="12"
								name="FiltrarComandosAcaoCobrancaEventualActionForm"
								property="tipoConsumo" disabled="true">
								<html:option value="1">MEDIDO</html:option>
								<html:option value="2">NÃO MEDIDO</html:option>
								<html:option value="3">AMBOS</html:option>
							</html:select> </strong>
						</td>
			</tr>			
			<tr>
				<td colspan="5">
				<hr>
				</td>
			</tr>
			<tr>
				<td height="17"><strong>Intervalo de Valor dos Documentos:</strong></td>
				<td colspan="4"><strong> <html:text maxlength="17" tabindex="25"
					name="FiltrarComandosAcaoCobrancaEventualActionForm" 
					style="text-align: right;"
					property="intervaloValorDocumentosInicial" size="17"
					onkeypress="return isCampoNumerico(event);"
					onkeyup="javascript:formataValorMonetario(this, 17);duplicaIntervaloValorDocumentos();" />
				a <html:text maxlength="17" style="text-align: right;"
					name="FiltrarComandosAcaoCobrancaEventualActionForm" tabindex="26"
					property="intervaloValorDocumentosFinal" size="17"
					onkeypress="return isCampoNumerico(event);"
					onkeyup="javascript:formataValorMonetario(this, 17)" /> </strong></td>
			</tr>
			<tr>
				<td height="17"><strong>Intervalo de Quantidade dos Documentos:</strong></td>
				<td colspan="4"><strong> <html:text maxlength="9"
					name="FiltrarComandosAcaoCobrancaEventualActionForm" tabindex="27"
					property="intervaloQuantidadeDocumentosInicial" size="9"
					onkeypress="return isCampoNumerico(event);"
					onkeyup="javascript:duplicaIntervaloQuantidadeDocumentos();" /> a
				<html:text maxlength="9"
					name="FiltrarComandosAcaoCobrancaEventualActionForm" tabindex="28"
					onkeypress="return isCampoNumerico(event);"
					property="intervaloQuantidadeDocumentosFinal" size="9" /> </strong></td>
			</tr>
			<tr>
				<td height="17"><strong>Intervalo de Quantidade de Itens dos
				Documentos:</strong></td>
				<td colspan="4"><strong> <html:text maxlength="9"
					name="FiltrarComandosAcaoCobrancaEventualActionForm" tabindex="29"
					property="intervaloQuantidadeItensDocumentosInicial" size="9"
					onkeypress="return isCampoNumerico(event);"
					onkeyup="javascript:duplicaIntervaloQuantidadeItensDocumentos();" />
				a <html:text maxlength="9"
					name="FiltrarComandosAcaoCobrancaEventualActionForm" tabindex="30"
					onkeypress="return isCampoNumerico(event);"
					property="intervaloQuantidadeItensDocumentosFinal" size="9" /> </strong></td>
			</tr>
			<tr>
				<td colspan="5">
				<hr>
				</td>
			</tr>
			<tr>
				<td height="17" colspan="1"><strong>Situa&ccedil;&atilde;o do Comando:</strong></td>
				<td colspan="3"><strong> <html:radio property="situacaoComando" tabindex="31"
					name="FiltrarComandosAcaoCobrancaEventualActionForm" value="Todos" /> Todos </strong>
				<strong> <html:radio property="situacaoComando" tabindex="32"
					name="FiltrarComandosAcaoCobrancaEventualActionForm" value="Realizados" />
				Realizados</strong>
				<strong> <html:radio property="situacaoComando" tabindex="33" 
					name="FiltrarComandosAcaoCobrancaEventualActionForm" value="NaoRealizados" />
				N&atilde;o Realizados</strong></td>
			</tr>
			<tr>
				<td colspan="5">
				<hr>
				</td>
			</tr>
			<tr>
				<td colspan="5">&nbsp;</td>
			</tr>
			<tr>
				<td colspan="3" align="left">
				<table border="0" align="left">
					<tr>
						<td align="left"><input name="Button32222" type="button"
							class="bottonRightCol" value="Limpar" tabindex="35"
							onClick="javascript:limpar()" /></td>
					</tr>
				</table>
				</td>
				<td>
				<table border="0" align="right">
					<tr>
						<td align="right"><img src="imagens/voltar.gif" width="15" 
							height="24" border="0" /></td>
						<td align="right"><input name="Button32222" type="button" tabindex="36"
							class="bottonRightCol" value="Voltar" tabindex="37"
							onClick="javascript:voltar();" /></td>
						<td align="right">
						  <gsan:controleAcessoBotao name="concluir" value="Filtrar" onclick="javascript:filtrar();" url="filtrarComandosAcaoCobrancaEventualAction.do" tabindex="38"/>
						</td>
					</tr>
				</table>
				</td>
			</tr>
		</table>

		<p>&nbsp;</p>
		</td>
	</tr>
</table>
</form>
<script language="JavaScript">
<!-- Begin
///	verificarGerenciaRegional();

/*	verificarUnidadeNegocio();
	verificarGrupoCobranca();
	validarLocalidades();
	validarSetoresComerciais();
	validarCliente(0);
	validarIndicadorCriterio();
	validarGrupoCobranca(1);
	validarGerenciaRegional(1);
	*/
	
	validarCliente(0);

	validarGrupoCobrancaParaBloquear();

	validarGerenciaRegionalParaBloquear();

	validarUnidadeNegocioParaBloquear();

	validarClienteParaBloquear();

	validarHabilitarCampo();
	
-->
</script>

<%@ include file="/jsp/util/rodape.jsp"%>
</body>
</html:html>
