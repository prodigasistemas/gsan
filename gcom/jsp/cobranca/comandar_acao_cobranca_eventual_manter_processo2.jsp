<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan" %>

<%@ page import="gcom.cobranca.CobrancaAtividade" %>

<%@ page import="gcom.util.ConstantesSistema"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<head>
<html:html>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false"  formName="ManterComandoAcaoCobrancaDetalhesActionForm" />

<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript">
<!-- Begin

    function caracteresespeciais () {
     this.ag = new Array("titulo", "Título possui caracteres especiais.", new Function ("varName", " return this[varName];"));
     this.aa = new Array("localidadeOrigemID", "Localidade Inicial deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
     this.ab = new Array("localidadeDestinoID", "Localidade Final deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
     this.ac = new Array("setorComercialOrigemCD", "Setor Comercial Inicial deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
     this.ad = new Array("setorComercialDestinoCD", "Setor Comercial Final deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
     this.ae = new Array("idCliente", "Cliente deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
    }

    function IntegerValidations () {
     this.ac = new Array("prazoExecucao", "Cliente deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
     this.ab = new Array("quantidadeMaximaDocumentos", "Quantidade máxima de documentos deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
     this.af = new Array("localidadeOrigemID", "Localidade Inicial deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
     this.ag = new Array("localidadeDestinoID", "Localidade Final deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
     this.ah = new Array("setorComercialOrigemCD", "Setor Comercial Inicial deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
     this.ai = new Array("setorComercialDestinoCD", "Setor Comercial Final deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
     this.aj = new Array("idCliente", "Cliente deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
    }
    
    function FloatValidations () {
	 	this.ap = new Array("valorLimiteObrigatoria", "Valor Limite para Emissão Obrigatória deve somente conter números decimais positivos.",new Function ("varName", " return this[varName];"));
	}

    function required () {
     this.ab = new Array("titulo", "Informe Título.", new Function ("varName", " return this[varName];"));
     this.ac = new Array("descricaoSolicitacao", "Informe Descrição Solicitação.", new Function ("varName", " return this[varName];"));     	
     this.al = new Array("cobrancaAcao", "Informe Ação de Cobrança.", new Function ("varName", " return this[varName];"));
     this.am = new Array("cobrancaAtividade", "Informa Atividade de Cobrança.", new Function ("varName", " return this[varName];"));
    } 

function validar(){

   var form = document.forms[0];
   var msg = '';
   if(form.cobrancaAcao.value == "-1"){
	   msg = 'Informe Ação de Cobrança.\n';
   }
   if(form.cobrancaAtividade.value == "-1"){
	   msg = msg + 'Informe Atividade de Cobrança.\n';
   }
	if( msg != '' ){
		alert(msg);
		return false;
	}else{
		return true;
	}
}

 function validarCliente(apagar){
    var form = document.forms[0];
    
    if (form.idCliente.readOnly == false && form.idCliente.disabled == false){
	    if (form.idCliente.value != ''){
	      if(apagar ==1) form.clienteRelacaoTipo.value = "";	
	      form.clienteRelacaoTipo.disabled = false;
	      form.codigoClienteSuperior.value = "";
      	  form.codigoClienteSuperior.disabled = true;
	    }else{
	      form.clienteRelacaoTipo.disabled = true;
	      form.codigoClienteSuperior.disabled = false;
	    }
	 }
  }


function desabilitarCobrancaAcao(){
  var form = document.forms[0];
  if(form.localidadeOrigemID.readOnly == false){ 	
	  if (form.localidadeOrigemID.value != ''  ){
	      form.cobrancaGrupo.value = "";	
	      form.cobrancaGrupo.disabled = true;
	      form.gerenciaRegional.disabled = true;
	      form.unidadeNegocio.disabled = true;      
	        form.nomeCliente.value = ""
	        form.idCliente.value = ""
	        form.idCliente.readOnly = true;
	        form.clienteRelacaoTipo.value = "";
	        form.clienteRelacaoTipo.disabled = true;      	
	      
	  }else{
	      form.cobrancaGrupo.disabled = false;
	      form.gerenciaRegional.disabled = false;
	      form.unidadeNegocio.disabled = false;      
	      form.idCliente.readOnly = false;
	      form.clienteRelacaoTipo.disabled = false;      
	  }
  }
}



function verificarCobrancaAtividade(){
///document.forms[0].cobrancaAtividade.options[document.forms[0].cobrancaAtividade.selectedIndex].id
    var form = document.forms[0];
	if(form.cobrancaAtividade.value == 1){
///		form.executarComando.disabled = true;	
	}
}


function validarHabilitarCampo(){

	var form = document.forms[0];
	
	if(form.dataRealizacao.value == ''){
	
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
		if(form.codigoClienteSuperior.value != ""){
		  ok = 2;
		}
		if(form.gerenciaRegional.value != "-1"){
			ok = 2;
		}
		if(form.cobrancaGrupo.value != "-1"){
			ok = 2;
		}
		if(form.unidadeNegocio.value != "-1"){
			ok = 2;
		}
		
		if(ok == 1){
			form.cobrancaGrupo.disabled = false;
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
}



function executarcomando(){
	
	var form = document.forms[0];
	
	if(validateRequired(form) && testarCampoValorZero(document.ManterComandoAcaoCobrancaDetalhesActionForm.localidadeOrigemID, 'Localidade Inicial') 
    && testarCampoValorZero(document.ManterComandoAcaoCobrancaDetalhesActionForm.localidadeDestinoID, 'Localidade Final') 
	&& testarCampoValorZero(document.ManterComandoAcaoCobrancaDetalhesActionForm.setorComercialOrigemCD, 'Setor Comercial Inicial') 
	&& testarCampoValorZero(document.ManterComandoAcaoCobrancaDetalhesActionForm.setorComercialDestinoCD, 'Setor Comercial Final') 
 	&& testarCampoValorZero(document.ManterComandoAcaoCobrancaDetalhesActionForm.idCliente, 'Cliente') 
    && validateCaracterEspecial(form) 
	&& validateLong(form) 
	&& verificaAnoMesMensagemPersonalizada(form.periodoInicialConta,"Mês/Ano Inicial do Período de Referência das Contas inválido")
	&& verificaAnoMesMensagemPersonalizada(form.periodoFinalConta,"Mês/Ano Final do Período de Referência das Contas inválido")
	&& verificaDataMensagemPersonalizada(form.periodoVencimentoContaInicial,"Data Inicial do Período de Vencimento das Contas inválida")
	&& verificaDataMensagemPersonalizada(form.periodoVencimentoContaFinal,"Data Final do Período de Vencimento das Contas inválida")
	&& validarLocalidadeDiferentes()
	&& validarSetoresComerciaisDiferentes()
	&& validarQuadraDiferentes()
	&& validarRotasDiferentes()
	&& verificarSetoresComerciaisMenores()
	&& verificarLocalidadeMenores()
	&& tratarExecutarComando()){	
	    form.action =  '/gsan/manterComandoAcaoCobrancaEventualExecutarComandoAction.do'
		form.submit();
	}
}

	function concluircomando(){
		var form = document.forms[0];
		
		if(validar() 
			&& testarCampoValorZero(document.ManterComandoAcaoCobrancaDetalhesActionForm.localidadeOrigemID, 'Localidade Inicial') 
		    && testarCampoValorZero(document.ManterComandoAcaoCobrancaDetalhesActionForm.localidadeDestinoID, 'Localidade Final') 
			&& testarCampoValorZero(document.ManterComandoAcaoCobrancaDetalhesActionForm.setorComercialOrigemCD, 'Setor Comercial Inicial') 
			&& testarCampoValorZero(document.ManterComandoAcaoCobrancaDetalhesActionForm.setorComercialDestinoCD, 'Setor Comercial Final') 
		 	&& testarCampoValorZero(document.ManterComandoAcaoCobrancaDetalhesActionForm.idCliente, 'Cliente') 
		    && validateCaracterEspecial(form)
			&& validateLong(form) 
			&& verificaAnoMesMensagemPersonalizada(form.periodoInicialConta,"Mês/Ano Inicial do Período de Referência das Contas inválido")
			&& verificaAnoMesMensagemPersonalizada(form.periodoFinalConta,"Mês/Ano Final do Período de Referência das Contas inválido")
			&& verificaDataMensagemPersonalizada(form.periodoVencimentoContaInicial,"Data Inicial do Período de Vencimento das Contas inválida")
			&& verificaDataMensagemPersonalizada(form.periodoVencimentoContaFinal,"Data Final do Período de Vencimento das Contas inválida")
			&& validarLocalidadeDiferentes()
			&& validarSetoresComerciaisDiferentes()
			&& validarQuadraDiferentes()
			&& validarRotasDiferentes()
			&& verificarSetoresComerciaisMenores()
			&& verificarLocalidadeMenores() 
			&& validarInscricoes() 
			&& verificarAtividadeEmitir()){
			
			form.action =  '/gsan/manterComandoAcaoCobrancaEventualConcluirAction.do'
		    form.submit(); 
		} 
	}

function tratarExecutarComando(){
	var form = document.forms[0];
	if(form.cobrancaAtividade.value == 3){
		alert("O Caso de [UC0000] Encerrar Atividade de Ação de Cobrança não foi implementado para esta iteração.")
		form.cobrancaAtividade.focus();
		return false;
	}
	return true;
}

function validarLocalidadeDiferentes(){
	var form = document.forms[0];
	
	if(form.localidadeOrigemID.value != '' && form.localidadeDestinoID.value == ''){
		alert("Informe Localidade Final.");
		form.localidadeDestinoID.focus();
		return false;
	}
	if(form.localidadeOrigemID.value == '' && form.localidadeDestinoID.value != ''){
		alert("Informe Localidade Inicial.");
		form.localidadeOrigemID.focus();
		return false;
	}
	return true;
	
}

function desabilitarLocalidadeCobrancaAcao(){
  var form = document.forms[0];
  if (form.idCliente.readOnly == false  || form.codigoClienteSuperior.readOnly == false){	
	 if (form.idCliente.value != '' || form.codigoClienteSuperior.value != '' ){
	      form.cobrancaGrupo.value = "";	
	      form.cobrancaGrupo.disabled = true;
	      form.localidadeOrigemID.readOnly = true;
	      form.localidadeDestinoID.readOnly = true;
		  form.setorComercialOrigemCD.readOnly = true;
	      form.setorComercialDestinoCD.readOnly = true;
	      form.gerenciaRegional.disabled = true;
	      form.unidadeNegocio.disabled = true;
	      form.rotaInicial.readOnly = true;
	      form.rotaFinal.readOnly = true;
	      
	  }else{
	      form.cobrancaGrupo.disabled = false;
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


function validarSetoresComerciaisDiferentes(){
	var form = document.forms[0];
	
	if(form.setorComercialOrigemCD.value!= '' && form.setorComercialDestinoCD.value == ''){
		alert("Informe Setor Comercial Final.");
		form.setorComercialDestinoCD.focus();
		return false;
	}
	if(form.setorComercialOrigemCD.value == '' && form.setorComercialDestinoCD.value != ''){
		alert("Informe Setor Comercial Inicial.");
		form.setorComercialDestinoCD.focus();
		return false;
	}
	return true;
}

function validarClienteParaBloquear(){

	var form = document.forms[0];
	if(form.dataRealizacao.value == ''){
		if(form.idCliente.value != ""){
			form.cobrancaGrupo.disabled = true;
		    form.gerenciaRegional.disabled = true;
		    form.unidadeNegocio.disabled = true;		    
	    	form.localidadeOrigemID.readOnly = true;
			form.localidadeDestinoID.readOnly = true;
	        form.setorComercialOrigemCD.readOnly = true;
	        form.setorComercialDestinoCD.readOnly = true;
	        form.imagem.enabled = true;
	   		form.rotaInicial.readOnly = true;
	   		form.rotaFinal.readOnly = true;
		}
	}
}


function validarGrupoCobrancaSelecionado(){

	var form = document.forms[0];
		
	if(form.cobrancaGrupo.value != "-1"){
		form.gerenciaRegional.disabled = true;
		form.unidadeNegocio.disabled = true;		
    	form.localidadeOrigemID.readOnly = true;
		form.localidadeDestinoID.readOnly = true;
        form.setorComercialOrigemCD.readOnly = true;
        form.setorComercialDestinoCD.readOnly = true;
        form.imagem.enabled = true;
   		form.rotaInicial.readOnly = true;
  		form.rotaFinal.readOnly = true;
        form.nomeCliente.value = ""
        form.idCliente.value = ""
        form.idCliente.readOnly = true;
        form.clienteRelacaoTipo.value = "";
        form.clienteRelacaoTipo.disabled = true;
	}else{
		form.gerenciaRegional.disabled = false;
		form.unidadeNegocio.disabled = false;		
    	form.localidadeOrigemID.readOnly = false;
		form.localidadeDestinoID.readOnly = false;
        form.setorComercialOrigemCD.readOnly = false;
        form.setorComercialDestinoCD.readOnly = false;
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

function validarGerenciaRegionalSelecionado(){

	var form = document.forms[0];
	
	if(form.gerenciaRegional.value != "-1"){
		form.cobrancaGrupo.disabled = true;
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
		form.cobrancaGrupo.disabled = false;
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


function validarUnidadeNegocioSelecionado(){

	var form = document.forms[0];
	
	if(form.unidadeNegocio.value != "-1"){
		form.cobrancaGrupo.disabled = true;
    	form.localidadeOrigemID.readOnly = true;
		form.localidadeDestinoID.readOnly = true;
        form.setorComercialOrigemCD.readOnly = true;
        form.setorComercialDestinoCD.readOnly = true;
        form.gerenciaRegional.disabled = true;        
        form.imagem.enabled = true;
   		form.rotaInicial.readOnly = true;
   		form.rotaFinal.readOnly = true;
        form.nomeCliente.value = ""
        form.idCliente.value = ""
        form.idCliente.readOnly = true;
        form.clienteRelacaoTipo.value = "";
        form.clienteRelacaoTipo.disabled = true;
	}else{
		form.cobrancaGrupo.disabled = false;
    	form.localidadeOrigemID.readOnly = false;
		form.localidadeDestinoID.readOnly = false;
        form.setorComercialOrigemCD.readOnly = false;
        form.setorComercialDestinoCD.readOnly = false;
        form.gerenciaRegional.disabled = false;        
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


function validarRotasDiferentes(){
	var form = document.forms[0];
	
    if(form.rotaInicial != undefined){
		if(form.rotaInicial.value!= '' && form.rotaFinal.value == ''){
			alert("Informe Rota Final.");
			form.rotaFinal.focus();
			return false;
		}
		if(form.rotaInicial.value == '' && form.rotaFinal.value != ''){
			alert("Informe Rota Inicial.");
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
			alert("Setor Comercial Final menor que Setor Comercial Inicial");	
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
			alert("Localidade Final menor que Localidade Inicial");	
			form.localidadeDestinoID.focus();
			return false;
		}else{
			return true;
		}
	
	}else{
		return true;
	}
}



function validarCriterios(valor){

	var form = document.forms[0];

	if(valor == 1){ //validar ação cobrança
		form.action =  '/gsan/exibirManterComandoAcaoCobrancaDetalhesAction.do?validarCriterio=SIM&validar=Acao'
    	form.submit();
    }else if(valor == 2){//validar cobrança atividade
		form.action =  '/gsan/exibirManterComandoAcaoCobrancaDetalhesAction.do?validarCriterio=SIM&validar=Atividade'
    	form.submit();
    	
    }
}

function verificar(){

	var form = document.forms[0];
	
	if(form.cobrancaAtividadeIndicadorExecucao.value != 1){
	///	form.executarComando.disabled = true;	
	}else if(form.cobrancaAtividadeIndicadorExecucao.value != 1){
		///form.executarComando.disabled = false;	
	}
}

function habilitar(valor){

	var form = document.forms[0];
	form.cobrancaAtividadeIndicadorExecucao.value = valor;	
	if(valor == 1){//indicador de rota
		form.concluir.disabled = false;
		form.Avancar.disabled = true;
		form.indicador[0].checked = true;
	}else if(valor == 2){//indicador de comando
		form.concluir.disabled  = true;
		form.Avancar.disabled = false;	
		form.indicador[1].checked = true;	
	}else if(valor == ""){
		form.concluir.disabled = false;
		form.Avancar.disabled = true;
		form.indicador[0].checked = true;
	}
}

function desabilitarCobrancaGrupo(){
	
	var form = document.forms[0];
	form.cobrancaGrupo.disabled = true;
	form.cobrancaGrupo.value = "";
}

function validarGrupoCobranca(visulizar){

	var form = document.forms[0];
	
///	if(form.cobrancaGrupo.value != ""){
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
			if(form.cobrancaGrupo.value != ""){
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
			}else{
				form.gerenciaRegional.disabled = false;
				form.unidadeNegocio.disabled = false;				
		    	form.localidadeOrigemID.readOnly = false;
				form.localidadeDestinoID.readOnly = false;
		        form.setorComercialOrigemCD.readOnly = false;
		        form.setorComercialDestinoCD.readOnly = false;
		        form.imagem.enabled = false;
			    if(form.rotaInicial != undefined){
		    	   		form.rotaInicial.readOnly = false;
		    	   		form.rotaFinal.readOnly = false;
			    }	
			}
		}else{
			if(visulizar == 1){
				alert("Gerência Regional/Localidade/Setor Comercial/Rota informados");
				form.cobrancaGrupo.value = "";
			}
		}
	///}	
}

function validarGrupoCobrancaParaBloquear(){

	var form = document.forms[0];
	
	if(form.dataRealizacao.value == ''){
	
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
		if(form.codigoClienteSuperior.value != ""){
		  ok = 2;
		}
		if(form.unidadeNegocio.value != "-1"){
			ok = 2;
		}
		if(form.gerenciaRegional.value != "-1"){
			ok = 2;
		}
		if(ok == 1){
///			form.cobrancaGrupo.disabled = true;
		    form.gerenciaRegional.disabled = true;
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
}

function validarGerenciaRegionalParaBloquear(){

	var form = document.forms[0];
	
if(form.dataRealizacao.value == ''){
	
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
		
		if(form.codigoClienteSuperior.value != ""){
		  ok = 2;
		}

		if(form.unidadeNegocio.value != "-1"){
			ok = 2;
		}

		if(form.cobrancaGrupo.value != "-1"){
			ok = 2;
		}
		
		if(ok == 1){
			form.cobrancaGrupo.disabled = true;
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
}

function validarUnidadeNegocioParaBloquear(){

	var form = document.forms[0];
	
	if(form.dataRealizacao.value == ''){
	
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
		
		if(form.codigoClienteSuperior.value != ""){
		  ok = 2;
		}
		
		if(form.gerenciaRegional.value != "-1"){
			ok = 2;
		}
		if(form.cobrancaGrupo.value != "-1"){
			ok = 2;
		}
		
		if(ok == 1){
			form.cobrancaGrupo.disabled = true;
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
}

function validarLocalidadeSetorRotaParaBloquear(){

	var form = document.forms[0];
	
	if(form.dataRealizacao.value == ''){
	 var ok = 1;
		if(form.localidadeOrigemID.value != ""){
			ok = 2;
		}
		if(form.localidadeDestinoID.value != ""){
			ok = 2;
		}
		
		if(ok == 2){
			form.cobrancaGrupo.disabled = true;
		    form.gerenciaRegional.disabled = true;
		    form.unidadeNegocio.disabled = true;		    
        	form.nomeCliente.value = ""
        	form.idCliente.value = ""
        	form.idCliente.readOnly = true;
        	form.clienteRelacaoTipo.value = "";
        	form.clienteRelacaoTipo.disabled = true;
		}
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

function receberRota(codigoRota,destino) {
 	  var form = document.forms[0];
	if(destino == "inicial"){
		form.rotaInicial.value = codigoRota;		
		form.rotaFinal.value = codigoRota;				
	}else if(destino == "final"){
		form.rotaFinal.value = codigoRota;		
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
		    desabilitarCobrancaAcao();
		case 2: //De setor para baixo
   	       form.setorComercialDestinoCD.value = "";
		   form.setorComercialDestinoID.value = ""; 
		   form.nomeSetorComercialDestino.value = "";		   
	   		form.rotaInicial.value = "";
   	   		form.rotaFinal.value = "";		   
	}
}

function limparOrigem(tipo,objetoReferencia){
	var form = document.forms[0];
	
	if(objetoReferencia.disabled == false){

	switch(tipo){
		case 1: //De localidara pra baixo
			
			form.cobrancaGrupo.disabled = false;
			form.gerenciaRegional.disabled = false;
			form.unidadeNegocio.disabled = false;			
	        form.idCliente.readOnly = false;
    	    form.clienteRelacaoTipo.disabled = true;			
			
			form.localidadeOrigemID.value = "";
			form.nomeLocalidadeOrigem.value = "";
			
			form.localidadeDestinoID.value = "";
			form.nomeLocalidadeDestino.value = "";
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
}

function limparBorrachaDestino(tipo,objetoReferencia){
	var form = document.forms[0];

   if(objetoReferencia.disabled == false){

	switch(tipo){
		case 1: //De localidade pra baixo
			form.localidadeDestinoID.value = "";
			form.nomeLocalidadeDestino.value = "";					
			form.setorComercialOrigemCD.value = "";
			form.setorComercialOrigemID.value = "";
			form.nomeSetorComercialOrigem.value = "";
    		form.setorComercialDestinoCD.value = "";

		case 2: //De setor para baixo		   
		   form.setorComercialDestinoID.value = ""; 
		   form.nomeSetorComercialDestino.value = "";		   
   		   form.setorComercialDestinoCD.value = "";
		   form.rotaInicial.value = "";
    	   form.rotaFinal.value = "";
	}
	}
}

function habilitarRota(){
	var form = document.forms[0];

	if(form.setorComercialDestinoCD.value == form.setorComercialOrigemCD.value){
	   form.rotaInicial.value = "";
 	   form.rotaFinal.value = "";
	   form.rotaInicial.readOnly = false;
 	   form.rotaFinal.readOnly = false;	
	}else{
	   form.rotaInicial.readOnly = true;
   	   form.rotaFinal.readOnly = true;	

	}
}


function recuperarDadosQuatroParametros(idRegistro, descricaoRegistro, codigoRegistro, tipoConsulta) {

	var form = document.forms[0];

	if (tipoConsulta == 'setorComercialOrigem') {
      form.setorComercialOrigemCD.value = codigoRegistro;
      form.setorComercialOrigemID.value = idRegistro;
      form.action = 'exibirManterComandoAcaoCobrancaDetalhesAction.do?objetoConsulta=2&inscricaoTipo=origem'
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
      form.action = 'exibirManterComandoAcaoCobrancaDetalhesAction.do?objetoConsulta=2&inscricaoTipo=destino'
   	  form.submit();

	  form.nomeSetorComercialDestino.value = descricaoRegistro;
	  form.nomeSetorComercialDestino.style.color = "#000000"; 
	}

}


function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {

    var form = document.forms[0];
    if (tipoConsulta == 'cobrancaAcaoAtividadeComando') {
      form.action = 'exibirManterComandoAcaoCobrancaDetalhesAction.do?idCobrancaAcaoAtividadeComando='+codigoRegistro+'&cobrancaAcaoAtividadeComandoPesquisado=true'
      form.submit();
    }

	if (tipoConsulta == 'localidadeOrigem') {
      form.localidadeOrigemID.value = codigoRegistro;
	  form.nomeLocalidadeOrigem.value = descricaoRegistro;
      form.action = 'exibirManterComandoAcaoCobrancaDetalhesAction.do?objetoConsulta=1&inscricaoTipo=origem'
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
        form.action = 'exibirManterComandoAcaoCobrancaDetalhesAction.do?objetoConsulta=1&inscricaoTipo=destino'
        form.submit();
    	form.nomeLocalidadeDestino.value = descricaoRegistro;
 		form.nomeLocalidadeDestino.style.color = "#000000";    		
	}
	
    if (tipoConsulta == 'cliente') {
	 	if (form.tipoPesquisa.value != null && form.tipoPesquisa.value == 'clienteSuperior') {
	 		form.codigoClienteSuperior.value = codigoRegistro;
      		form.nomeClienteSuperior.value = descricaoRegistro;
      		form.nomeClienteSuperior.style.color = "#000000";
      		validarClienteSuperior(1);	
      		desabilitarLocalidadeCobrancaAcao();
	 	} else {
	        form.idCliente.value = codigoRegistro;
      		form.nomeCliente.value = descricaoRegistro;
      		form.nomeCliente.style.color = "#000000";
     	 	form.clienteRelacaoTipo.disabled = false;
     	 	validarCliente(1);	
     	 	desabilitarLocalidadeCobrancaAcao()
        }
        
     }

}

function limparCliente(){
  	  var form = document.forms[0];
       form.nomeCliente.value = ""
       form.idCliente.value = ""
       form.clienteRelacaoTipo.value = "";
       form.clienteRelacaoTipo.disabled = true;
       form.nomeClienteSuperior.value = "";
       form.codigoClienteSuperior.disabled = false;
}

function avancar(){
	var form = document.forms[0]; 
		
	if(validateRequired(form) && testarCampoValorZero(document.ManterComandoAcaoCobrancaDetalhesActionForm.localidadeOrigemID, 'Localidade Inicial') 
	    && testarCampoValorZero(document.ManterComandoAcaoCobrancaDetalhesActionForm.localidadeDestinoID, 'Localidade Final') 
		&& testarCampoValorZero(document.ManterComandoAcaoCobrancaDetalhesActionForm.setorComercialOrigemCD, 'Setor Comercial Inicial') 
		&& testarCampoValorZero(document.ManterComandoAcaoCobrancaDetalhesActionForm.setorComercialDestinoCD, 'Setor Comercial Final') 
	 	&& testarCampoValorZero(document.ManterComandoAcaoCobrancaDetalhesActionForm.idCliente, 'Cliente') 
	    && validateCaracterEspecial(form) 
		&& validateLong(form) 
		&& verificaAnoMesMensagemPersonalizada(form.periodoInicialConta,"Mês/Ano Inicial do Período de Referência das Contas inválido")
		&& verificaAnoMesMensagemPersonalizada(form.periodoFinalConta,"Mês/Ano Final do Período de Referência das Contas inválido")
		&& verificaDataMensagemPersonalizada(form.periodoVencimentoContaInicial,"Data Inicial do Período de Vencimento das Contas inválida")
		&& verificaDataMensagemPersonalizada(form.periodoVencimentoContaFinal,"Data Final do Período de Vencimento das Contas inválida")
		&& validarLocalidadeDiferentes()
		&& validarSetoresComerciaisDiferentes()
		&& validarRotasDiferentes()
		&& verificarSetoresComerciaisMenores()
		&& verificarLocalidadeMenores()
		&& validarInscricoes()){	
			form.action =  '/gsan/exibirManterComandoAcaoCobrancaEventualCriterioComandoAction.do?idCobrancaAtividade='+form.cobrancaAtividade.value+'&idCobrancaAcao='+form.cobrancaAcao.value
   			form.submit();
   	}
}

function chamarPopup(url, tipo, objeto, codigoObjeto, altura, largura, msg,objetoRelacionado){


	if(objetoRelacionado.disabled != true && objetoRelacionado.readOnly != true){
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

function voltar(){
	var formulario = document.forms[0]; 
	formulario.action =  'exibirManterComandoAcaoCobrancaAction.do?menu=sim'
	formulario.submit();
}

function mensagem(mensagem){
	if(mensagem.length > 0){
		alert(mensagem);
	}
}

function validarExistenciaLocalidadeInicial(){
	var form = document.forms[0]; 
	if(form.localidadeOrigemID.value == ""){
		form.localidadeDestinoID.value = "";
		alert("Informe Localidade Inicial.");
		form.localidadeOrigemID.focus();
	}
}

function validarExistenciaSetorComercialInicial(){
	var form = document.forms[0]; 
	if(form.setorComercialOrigemCD.value == ""){
		form.setorComercialDestinoCD.value = "";
		alert("Informe Setor Comercial Inicial.");
		form.setorComercialOrigemCD.focus();
	}
}


function habilitacaoCampoRota(){

	var form = document.forms[0];
	
	var setorComercialOrigem = trim(form.setorComercialOrigemCD.value);
	var setorComercialDestino = trim(form.setorComercialDestinoCD.value);
	
	if (setorComercialOrigem.length > 0 && setorComercialDestino.length > 0){
		
		if (setorComercialOrigem != setorComercialDestino){
			form.rotaInicial.readOnly = true;
			form.rotaFinal.readOnly = true;
		}
		else{
			form.rotaInicial.readOnly = false;
			form.rotaFinal.readOnly = false;
		}
		
	}
}

function desabilitaIntervaloDiferente(tipo){
	var form = document.forms[0];
	
	switch(tipo){
		case 1: //De localidade 
		    if(form.localidadeOrigemID.value != form.localidadeDestinoID.value){
		        form.setorComercialOrigemCD.value = "";
			 	form.setorComercialDestinoCD.value = "";
		        form.setorComercialOrigemCD.readOnly = true;
			 	form.setorComercialDestinoCD.readOnly = true;
			    if(form.rotaInicial != undefined){
				  	form.rotaInicial.readOnly = true;
				 	form.rotaFinal.readOnly = true;
             	}
			 
			  }else{
		        form.setorComercialOrigemCD.readOnly = false;
			 	form.setorComercialDestinoCD.readOnly = false;
			    if(form.rotaInicial != undefined){
				  	form.rotaInicial.readOnly = false;
				 	form.rotaFinal.readOnly = false;
             	}
			  }
			break;					
		case 2: //De setor Comercial		   
		    if(form.setorComercialOrigemCD.value != form.setorComercialDestinoCD.value){
			    if(form.rotaInicial != undefined){
				  	form.rotaInicial.readOnly = true;
				 	form.rotaFinal.readOnly = true;
             	}
  			  }else{
				   if(form.rotaInicial != undefined){
					  	form.rotaInicial.readOnly = false;
			 			form.rotaFinal.readOnly = false;
			 		}
			  }
			break;
		case 3://De Rota 

		   if(form.rotaInicial != undefined){
		   
		     if(form.rotaInicial.value != form.rotaFinal.value){
			  	form.rotaInicial.readOnly = true;
			 	form.rotaFinal.readOnly = true;
             	form.rotaInicial.value = "";
             	form.rotaFinal.value = "";
			  }else{
			  	form.rotaInicial.readOnly = false;
			 	form.rotaFinal.readOnly = false;
			  }

			}
			break;
		}
}

function replicarSetorComercial(){
	var form = document.forms[0];
	if(form.setorComercialDestinoCD.value == ""){
		form.setorComercialDestinoCD.value = form.setorComercialOrigemCD.value;
	}	
}

function replicarLocalidade(){
	var form = document.forms[0];
	if(form.localidadeDestinoID.value == ""){
		form.localidadeDestinoID.value = form.localidadeOrigemID.value;	
	}
	
}

function replicarRota(){
	var form = document.forms[0];
	if(form.rotaFinal.value == ""){
		form.rotaFinal.value = form.rotaInicial.value;	
	}
	
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

function verificarAtividadeEmitir(){
var form = document.forms[0];
 if(form.cobrancaAtividade.value == form.idAtividadeCobrancaEmitir.value){
  if(form.prazoExecucao.value == ''){
   alert("Informe prazo de execução");
   return false;
  }else{
   return true;
  }
 }else{
  return true;
 }
}

function verificarCampoPrazoExecucao(){
var form = document.forms[0];
if(form.dataRealizacao.value == ''){
 if(form.cobrancaAtividade != null && form.cobrancaAtividade.value != form.idAtividadeCobrancaEmitir.value){
  form.prazoExecucao.value = '';
  form.prazoExecucao.disabled = true; 
 }else{
  form.prazoExecucao.disabled = false; 
 }
}
}

function  validarAbrirPoupPesquisarClienteSuperior() {
	var form = document.forms[0];
	if (form.codigoClienteSuperior.readOnly == false) {
		form.tipoPesquisa.value = 'clienteSuperior';
		chamarPopup('exibirPesquisarClienteAction.do?indicadorUsoTodos=1&limparForm=OK', 'clienteSuperior', null, null, 275, 480, '',form.codigoClienteSuperior.value);
	}	
}

function validarAbrirPoupPesquisarCliente(){
	var form = document.forms[0];	
							
	if(form.idCliente.disabled == false){
	    form.tipoPesquisa.value = 'cliente';
	    chamarPopup('exibirPesquisarClienteAction.do?indicadorUsoTodos=1&limparForm=OK', 'cliente', null, null, 275, 480, '',form.idCliente.value);
	}
	
}


function validarLimparClienteSuperior(){
	var form = document.forms[0];						
	if(form.codigoClienteSuperior.disabled == false){
		limparClienteSuperior();
	}
}

function limparClienteSuperior(){
  	  var form = document.forms[0];
      form.nomeClienteSuperior.value = ""
      form.codigoClienteSuperior.value = ""
      form.nomeCliente.value = "";
      form.idCliente.disabled = false;
}


function limparCliente(){
  	  var form = document.forms[0];
       form.nomeCliente.value = ""
       form.idCliente.value = ""
       form.clienteRelacaoTipo.value = "";
       form.clienteRelacaoTipo.disabled = true;
       form.nomeClienteSuperior.value = "";
       form.codigoClienteSuperior.disabled = false;
}

function validarClienteSuperior(apagar){
    	var form = document.forms[0];
    	if (form.codigoClienteSuperior.value != ''){
      		if(apagar == 1) form.clienteRelacaoTipo.value = "";	
      		form.clienteRelacaoTipo.disabled = true;
      		form.idCliente.disabled = true;
      		form.idCliente.value = "";
      		form.nomeCliente.value = "";
    	}else{
      		form.clienteRelacaoTipo.disabled = false;
      		form.idCliente.disabled = false;
   	 	}
  	}
  	
function validarLimparCliente(){
	var form = document.forms[0];						
	if(form.idCliente.disabled == false){
		limparCliente();
	}
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

<body leftmargin="5" topmargin="5" onload="validarExibicaoTipoCosumo();">
<html:form action="/exibirManterComandoAcaoCobrancaDetalhesAction.do"
	name="ManterComandoAcaoCobrancaDetalhesActionForm"
	type="gcom.gui.cobranca.ManterComandoAcaoCobrancaDetalhesActionForm"
	method="post"
	onsubmit="return validateExibirManterComandoAcaoCobrancaDetalhesActionForm(this);">

	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>
	
	<input type="hidden" name="idAtividadeCobrancaEmitir" value= "<%=CobrancaAtividade.EMITIR %>"/>
	<input type="hidden" name="tipoPesquisa" />
	
	<logic:empty name="ManterComandoAcaoCobrancaDetalhesActionForm" property="dataRealizacao">
		<input type="hidden" name="dataRealizacao"/>
	</logic:empty>

	<logic:notEmpty name="ManterComandoAcaoCobrancaDetalhesActionForm" property="dataRealizacao">
		<input type="hidden" 
			name="dataRealizacao" 
			value="<bean:write name="ManterComandoAcaoCobrancaDetalhesActionForm" property="dataRealizacao"/>" />
	</logic:notEmpty>

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
			<!--Início Tabela Reference a Páginação da Tela de Processo-->
			<table>
				<tr>
					<td></td>
				</tr>
			</table>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img border="0" src="imagens/parahead_left.gif" /></td>
					<td class="parabg">Manter Comando de A&ccedil;&atilde;o de
					Cobran&ccedil;a</td>
					<td width="11" valign="top"><img border="0"
						src="imagens/parahead_right.gif" /></td>
				</tr>
			</table>
			<!--Fim Tabela Reference a Páginação da Tela de Processo-->
			<p>&nbsp;</p>
			<table width="100%" border="0" dwcopytype="CopyTableRow">
				<tr>
					<td colspan="4">Para determinar a a&ccedil;&atilde;o de
					cobran&ccedil;a a ser comandada, informe os dados abaixo:</td>
				</tr>
				<tr>
					<td width="16%"><strong>Título:<font
						color="#FF0000">*</font></strong></td>
					<td colspan="3">
					 <logic:empty name="ManterComandoAcaoCobrancaDetalhesActionForm" property="dataRealizacao">	
					  <html:text property="titulo" size="60" maxlength="100" tabindex="1"/>
					 </logic:empty>
					 <logic:notEmpty name="ManterComandoAcaoCobrancaDetalhesActionForm" property="dataRealizacao">	
					  <html:text property="titulo" size="60" maxlength="100" tabindex="1" disabled="true"/>
					 </logic:notEmpty>
					</td>
				</tr>
				<tr>
					<td width="16%"><strong>Descrição da Solicitação:<font
						color="#FF0000">*</font></strong></td>
					<td colspan="3">
					 <logic:empty name="ManterComandoAcaoCobrancaDetalhesActionForm" property="dataRealizacao">	
					  <html:textarea property="descricaoSolicitacao" cols="50" rows="10" tabindex="2"/>
					 </logic:empty>
					 <logic:notEmpty name="ManterComandoAcaoCobrancaDetalhesActionForm" property="dataRealizacao">	
					  <html:textarea property="descricaoSolicitacao" cols="50" rows="10" tabindex="2" disabled="true"/>
					 </logic:notEmpty>
					</td>
				</tr>
				
				<tr>
					<td width="16%"><strong>A&ccedil;&atilde;o de Cobran&ccedil;a:<font
						color="#FF0000">*</font></strong></td>
					<td colspan="3">
					 <logic:empty name="ManterComandoAcaoCobrancaDetalhesActionForm" property="dataRealizacao">	
					 <html:select tabindex="3"
						name="ManterComandoAcaoCobrancaDetalhesActionForm"
						property="cobrancaAcao" onchange="validarCriterios(1);">
						<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
						<logic:present scope="session" name="colecaoCobrancaAcao">
							<html:options name="session" collection="colecaoCobrancaAcao"
								labelProperty="descricaoCobrancaAcao" property="id" />
						</logic:present>
					  </html:select>
					 </logic:empty>
					 <logic:notEmpty name="ManterComandoAcaoCobrancaDetalhesActionForm" property="dataRealizacao">	
					  <html:select tabindex="3"
						name="ManterComandoAcaoCobrancaDetalhesActionForm"
						property="cobrancaAcao" disabled="true">
						<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
						<logic:present scope="session" name="colecaoCobrancaAcao">
							<html:options name="session" collection="colecaoCobrancaAcao"
								labelProperty="descricaoCobrancaAcao" property="id" />
						</logic:present>
					  </html:select>
					 </logic:notEmpty>
					  
				    </td>
				</tr>
				<tr>
					<td><strong>Atividade de Cobran&ccedil;a:<font color="#FF0000">*</font></strong></td>
					<td colspan="3">
					<logic:empty name="ManterComandoAcaoCobrancaDetalhesActionForm" property="dataRealizacao">	
					  <html:select name="ManterComandoAcaoCobrancaDetalhesActionForm"
						tabindex="4"
						property="cobrancaAtividade" onchange="validarCriterios(2);">
						<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
						<logic:present scope="session" name="colecaoCobrancaAtividade">
							<html:options name="session"
								collection="colecaoCobrancaAtividade"
								labelProperty="descricaoCobrancaAtividade" property="id" />
						</logic:present>
					  </html:select>
					</logic:empty>
					<logic:notEmpty name="ManterComandoAcaoCobrancaDetalhesActionForm" property="dataRealizacao">	
					  <html:select name="ManterComandoAcaoCobrancaDetalhesActionForm"
						tabindex="4"
						property="cobrancaAtividade" disabled="true">
						<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
						<logic:present scope="session" name="colecaoCobrancaAtividade">
							<html:options name="session"
								collection="colecaoCobrancaAtividade"
								labelProperty="descricaoCobrancaAtividade" property="id" />
						</logic:present>
					</html:select>
					 </logic:notEmpty>
					 
					</td>
				</tr>
				<tr>
					<td width="16%"><strong>Prazo de Execução:</strong></td>
					<td colspan="3"><html:text property="prazoExecucao" tabindex="5" size="3" maxlength="3"/></td>
				</tr>
				
				<tr>
					<td width="16%"><strong>Quantidade Máxima de Documentos:</strong></td>
					<td colspan="3">
					 <logic:empty name="ManterComandoAcaoCobrancaDetalhesActionForm" property="dataRealizacao"> 
					  <html:text property="quantidadeMaximaDocumentos" tabindex="4" size="5" maxlength="5"/>
					 </logic:empty>
					 <logic:notEmpty name="ManterComandoAcaoCobrancaDetalhesActionForm" property="dataRealizacao">
					  <html:text property="quantidadeMaximaDocumentos" tabindex="4" size="5" maxlength="5" disabled="true"/>
					 </logic:notEmpty> 
				   </td>
					  
				</tr>
				<tr>
					<td width="16%"><strong>Valor Limite para Emissão Obrigatória:</strong></td>
					<td colspan="3">
						<logic:empty name="ManterComandoAcaoCobrancaDetalhesActionForm" property="dataRealizacao"> 
					  		<html:text property="valorLimiteObrigatoria" size="9"
							maxlength="9" tabindex="5" 
							onkeyup="formataValorMonetario(this, 9)" 
							style="text-align:right;" />
					 	</logic:empty>
					 	<logic:notEmpty name="ManterComandoAcaoCobrancaDetalhesActionForm" property="dataRealizacao">
					  		<html:text property="valorLimiteObrigatoria" size="8"
							maxlength="8" tabindex="5" 
							onkeyup="formataValorMonetario(this, 8)" 
							style="text-align:right;" />
					 	</logic:notEmpty> 
				   </td>
					  
				</tr>
				<tr>
					<td width="16%"><strong>Apenas para Imóveis com Débito:</strong></td>
					<td colspan="3">
					 <logic:empty name="ManterComandoAcaoCobrancaDetalhesActionForm" property="dataRealizacao"> 
					  <html:radio property="indicadorImoveisDebito" value="1" /> 
					                  <strong>Sim 
					                <html:radio	property="indicadorImoveisDebito" value="2" /> 
					                N&atilde;o</strong>
					 </logic:empty>
					 <logic:notEmpty name="ManterComandoAcaoCobrancaDetalhesActionForm" property="dataRealizacao">
					  <html:radio property="indicadorImoveisDebito" value="1" disabled="true"/> 
					                  <strong>Sim 
					                <html:radio	property="indicadorImoveisDebito" value="2" disabled="true"/> 
					                N&atilde;o</strong>
					 </logic:notEmpty> 
					  
					  
					</td>
				</tr>
				<tr>
					<td width="16%"><strong>Gerar Boletins de Cadastro:</strong></td>
					<td>
					  <logic:empty name="ManterComandoAcaoCobrancaDetalhesActionForm" property="dataRealizacao"> 
					  <html:radio property="indicadorGerarBoletimCadastro" value="1" /> 
					                  <strong>Sim 
					                <html:radio	property="indicadorGerarBoletimCadastro" value="2" /> 
					                N&atilde;o</strong>
					 </logic:empty>
					 <logic:notEmpty name="ManterComandoAcaoCobrancaDetalhesActionForm" property="dataRealizacao">
					  <html:radio property="indicadorGerarBoletimCadastro" value="1" disabled="true"/> 
					                  <strong>Sim 
					                <html:radio	property="indicadorGerarBoletimCadastro" value="2" disabled="true"/> 
					                N&atilde;o</strong>
					 </logic:notEmpty> 
					             
					 <html:hidden
						property="cobrancaAtividadeIndicadorExecucao" /></td>
					<td colspan="2">
					<div align="right">
					<logic:empty name="ManterComandoAcaoCobrancaDetalhesActionForm" property="dataRealizacao">  
					 <input name="Button32232" type="button"
						class="bottonRightCol" value="Pesquisar Comandos"
						onClick="javascript:abrirPopup('exibirPesquisarComandoAcaoCobrancaAction.do', 300, 750);" />
					</logic:empty>
					<logic:notEmpty name="ManterComandoAcaoCobrancaDetalhesActionForm" property="dataRealizacao">
					<input name="Button32232" type="button"
						class="bottonRightCol" value="Pesquisar Comandos"/>
					</logic:notEmpty>
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="4">
					<hr>
					</td>
				</tr>
				<tr>
					<td><strong>Grupo de Cobran&ccedil;a:</strong></td>
					<td colspan="3">
					 <logic:empty name="ManterComandoAcaoCobrancaDetalhesActionForm" property="dataRealizacao"> 
					  <html:select tabindex="6"
						name="ManterComandoAcaoCobrancaDetalhesActionForm"
						onchange="javascript:validarGrupoCobrancaSelecionado();"
						property="cobrancaGrupo">
						<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
						<logic:present scope="session" name="colecaoCobrancaGrupo">
							<html:options name="session" collection="colecaoCobrancaGrupo"
								labelProperty="descricao" property="id" />
						</logic:present>
					  </html:select>	
					 </logic:empty>
					 <logic:notEmpty name="ManterComandoAcaoCobrancaDetalhesActionForm" property="dataRealizacao">
					  <html:select tabindex="6"
						name="ManterComandoAcaoCobrancaDetalhesActionForm"
						property="cobrancaGrupo" disabled="true">
						<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
						<logic:present scope="session" name="colecaoCobrancaGrupo">
							<html:options name="session" collection="colecaoCobrancaGrupo"
								labelProperty="descricao" property="id" />
						</logic:present>
					  </html:select>
					 </logic:notEmpty> 
					 
					</td>
				</tr>
				<tr>
					<td colspan="4">
					<hr>
					</td>
				</tr>
				<tr>
					<td height="24"><strong>Ger&ecirc;ncia Regional:<strong></strong></strong></td>
					<td width="60%" colspan="3">
					<div align="left"><strong>
					
					<logic:empty name="ManterComandoAcaoCobrancaDetalhesActionForm" property="dataRealizacao"> 
					  <html:select tabindex="7"
						name="ManterComandoAcaoCobrancaDetalhesActionForm"
						property="gerenciaRegional">
						<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
						<logic:present scope="session" name="colecaoGerenciaRegional">
				         <logic:iterate name="colecaoGerenciaRegional" id="colecaoGerenciasRegionais">
  							  <html:option value="${colecaoGerenciasRegionais.id}">
					           <bean:write name="colecaoGerenciasRegionais" property="nomeAbreviado"/> 
					           - <bean:write name="colecaoGerenciasRegionais" property="nome"/>
					          </html:option>
				         </logic:iterate>
						</logic:present>
					  </html:select>
					</logic:empty>
					<logic:notEmpty name="ManterComandoAcaoCobrancaDetalhesActionForm" property="dataRealizacao">
					  <html:select tabindex="7"
						name="ManterComandoAcaoCobrancaDetalhesActionForm"
						property="gerenciaRegional" disabled="true">
						<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
						<logic:present scope="session" name="colecaoGerenciaRegional">
				         <logic:iterate name="colecaoGerenciaRegional" id="colecaoGerenciasRegionais">
  							  <html:option value="${colecaoGerenciasRegionais.id}">
					           <bean:write name="colecaoGerenciasRegionais" property="nomeAbreviado"/> 
					           - <bean:write name="colecaoGerenciasRegionais" property="nome"/>
					          </html:option>
				         </logic:iterate>
						</logic:present>
					  </html:select>			
					</logic:notEmpty>
					 
								
					</strong></div>
					</td>

				</tr>
				
				<tr>
					<td height="24"><strong>Unidade Negócio:<strong></strong></strong></td>
					<td width="60%" colspan="3">
					 <div align="left"><strong> 
						 <logic:empty name="ManterComandoAcaoCobrancaDetalhesActionForm" property="dataRealizacao"> 
						  <html:select tabindex="8"
							name="ManterComandoAcaoCobrancaDetalhesActionForm"
							onchange="javaScript:validarUnidadeNegocioSelecionado();"
							property="unidadeNegocio">
							<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
							<logic:present scope="session" name="colecaoUnidadeNegocio">
					         <logic:iterate name="colecaoUnidadeNegocio" id="colecaoUnidadeNegocios">
									<html:option value="${colecaoUnidadeNegocios.id}">
						           <bean:write name="colecaoUnidadeNegocios" property="nomeAbreviado"/> 
						           - <bean:write name="colecaoUnidadeNegocios" property="nome"/>
						          </html:option>
					         </logic:iterate>
							</logic:present>

						  </html:select>
						 </logic:empty>
						 <logic:notEmpty name="ManterComandoAcaoCobrancaDetalhesActionForm" property="dataRealizacao">
						  <html:select tabindex="8"
							name="ManterComandoAcaoCobrancaDetalhesActionForm"
							onchange="javaScript:validarUnidadeNegocioSelecionado();"
							property="unidadeNegocio" disabled="true">
							<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
							<logic:present scope="session" name="colecaoUnidadeNegocio">
					         <logic:iterate name="colecaoUnidadeNegocio" id="colecaoUnidadeNegocios">
									<html:option value="${colecaoUnidadeNegocios.id}">
						           <bean:write name="colecaoUnidadeNegocios" property="nomeAbreviado"/> 
						           - <bean:write name="colecaoUnidadeNegocios" property="nome"/>
						          </html:option>
					         </logic:iterate>
							</logic:present>
	
						  </html:select>
						 </logic:notEmpty>
					 </strong></div>
					</td>

				</tr>
				
				
				
				<html:hidden property="inscricaoTipo" />
				<tr>
					<td colspan="4">
					<hr>
					</td>
				</tr>

				<tr>

					<td height="51"><strong>Localidade Inicial:<font color="#FF0000"> </font></strong></td>
					<td width="31%" align="right" colspan="2">
					<div align="left">
					<logic:empty name="ManterComandoAcaoCobrancaDetalhesActionForm" property="dataRealizacao"> 
					  <html:text maxlength="3" property="localidadeOrigemID" size="3"
						onkeypress="limparDestino(1);validaEnterComMensagem(event, 'exibirManterComandoAcaoCobrancaDetalhesAction.do?objetoConsulta=1&inscricaoTipo=origem', 'localidadeOrigemID','Localidade Inicial');"
						tabindex="5" onkeyup="javaScript:desabilitarCobrancaAcao();"
						onblur="javascript:replicarLocalidade();" onchange="enviarDadosLocalidade()" />
					 </logic:empty>
					 <logic:notEmpty name="ManterComandoAcaoCobrancaDetalhesActionForm" property="dataRealizacao">
					  <html:text maxlength="3"
						property="localidadeOrigemID" size="3"
						tabindex="5" disabled="true"/>
					 </logic:notEmpty>
					 <img
						src="<bean:message key='caminho.imagens'/>pesquisa.gif" width="23"
						height="21" style="cursor:hand;" name="imagem"
						onclick="chamarPopup('exibirPesquisarLocalidadeAction.do', 'origem', null, null, 275, 480, '',document.forms[0].localidadeOrigemID);"
						alt="Pesquisar"> <strong> <logic:present name="corLocalidadeOrigem">
						<logic:equal name="corLocalidadeOrigem" value="exception">
							<html:text property="nomeLocalidadeOrigem" size="30"
								maxlength="30" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:equal>

						<logic:notEqual name="corLocalidadeOrigem" value="exception">
							<html:text property="nomeLocalidadeOrigem" size="30"
								maxlength="30" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEqual>

					</logic:present> <logic:notPresent name="corLocalidadeOrigem">

						<logic:empty name="ManterComandoAcaoCobrancaDetalhesActionForm"
							property="localidadeOrigemID">
							<html:text property="nomeLocalidadeOrigem" value="" size="30"
								maxlength="30" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:empty>
						<logic:notEmpty name="ManterComandoAcaoCobrancaDetalhesActionForm"
							property="localidadeOrigemID">
							<html:text property="nomeLocalidadeOrigem" size="30"
								maxlength="30" readonly="true"
								style="background-color:#EFEFEF; border:0; color: 	#000000" />
						</logic:notEmpty>
					</logic:notPresent> 
						<a href="javascript:limparOrigem(1,document.forms[0].localidadeOrigemID);"> <img name="imagem"
						src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar" /></a>  							
					</strong></div>
					</td>
				</tr>
				<tr>
					<td><strong>Setor Comercial Inicial: </strong></td>
					<td align="right" colspan="2">
					<div align="left">
					<logic:empty name="ManterComandoAcaoCobrancaDetalhesActionForm" property="dataRealizacao"> 
					  <html:text maxlength="3" tabindex="6"
						property="setorComercialOrigemCD" size="3"
						onkeypress="limparDestino(2);validaEnterDependenciaComMensagem(event, 'exibirManterComandoAcaoCobrancaDetalhesAction.do?objetoConsulta=2&inscricaoTipo=origem&validarCriterio=naoAcao', document.forms[0].setorComercialOrigemCD, document.forms[0].localidadeOrigemID.value, 'Localidade Inicial', 'Setor Comercial Inicial');"
						onblur="replicarSetorComercial();"
						onfocus="validarLocalidade();" />
					 </logic:empty>
					 <logic:notEmpty name="ManterComandoAcaoCobrancaDetalhesActionForm" property="dataRealizacao">
					  <html:text maxlength="3"
						property="setorComercialOrigemCD" size="3"
						disabled="true"/>
					 </logic:notEmpty>
					 <img
						src="<bean:message key='caminho.imagens'/>pesquisa.gif" width="23"
						height="21" style="cursor:hand;" name="imagem"
						onclick="chamarPopup('exibirPesquisarSetorComercialAction.do', 
						'setorComercialOrigem', 'idLocalidade', document.forms[0].localidadeOrigemID.value, 275, 480, 
						'Informe a Localidade da inscrição de origem',document.forms[0].setorComercialOrigemCD);">
						<html:hidden property="setorComercialOrigemID" />

					<logic:present name="corSetorComercialOrigem">
						<logic:equal name="corSetorComercialOrigem" value="exception">
							<html:text property="nomeSetorComercialOrigem" size="30"
								maxlength="30" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:equal>

						<logic:notEqual name="corSetorComercialOrigem" value="exception">
							<html:text property="nomeSetorComercialOrigem" size="30"
								maxlength="30" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEqual>
					</logic:present> <logic:notPresent name="corSetorComercialOrigem">
						<logic:empty name="ManterComandoAcaoCobrancaDetalhesActionForm"
							property="setorComercialOrigemCD">
							<html:text property="nomeSetorComercialOrigem" value="" size="30"
								maxlength="30" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:empty>
						<logic:notEmpty name="ManterComandoAcaoCobrancaDetalhesActionForm"
							property="setorComercialOrigemCD">
							<html:text property="nomeSetorComercialOrigem" size="30"
								maxlength="30" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEmpty>

					</logic:notPresent>
						<a href="javascript:limparOrigem(2,document.forms[0].setorComercialOrigemCD);"> <img name="imagem"
						src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar" /></a>  							
					</div>
					</td>
				</tr>
				
				
				<tr>
					<td><strong>Quadra Inicial:</strong></td>
					<td colspan="3">
						<logic:empty name="ManterComandoAcaoCobrancaDetalhesActionForm" property="dataRealizacao"> 
					  		<html:text maxlength="5"
								property="numeroQuadraInicial" 
								size="5" 
								tabindex="13"
                   				onblur="validarQuadra(this, document.forms[0].setorComercialOrigemCD.value, 'Setor Comercial Inicial');"
                   				onkeypress="return isCampoNumerico(event);"  />
					 	</logic:empty>
					 	
					 <logic:notEmpty name="ManterComandoAcaoCobrancaDetalhesActionForm" property="dataRealizacao">
					  		<html:text maxlength="5"
								property="numeroQuadraInicial" 
								size="5" 
								tabindex="13"
                   				disabled="true"  />					 
					 </logic:notEmpty>					 	
                   </td>					
				</tr>
				
				<tr>
					<td><strong>Rota Inicial:<font color="#FF0000"> </font></strong></td>
					<td align="right" colspan="2">
					<div align="left">
					<logic:empty name="ManterComandoAcaoCobrancaDetalhesActionForm" property="dataRealizacao"> 
					  <html:text maxlength="4" tabindex="7"
						property="rotaInicial" size="4"
						onblur="javascript:replicarRota();" 
						name="ManterComandoAcaoCobrancaDetalhesActionForm"/>
					 </logic:empty>
					 <logic:notEmpty name="ManterComandoAcaoCobrancaDetalhesActionForm" property="dataRealizacao">
					  <html:text maxlength="4" tabindex="7"
						property="rotaInicial" size="4"
						name="ManterComandoAcaoCobrancaDetalhesActionForm" disabled="true"/>
					 </logic:notEmpty>
					 <a href="javascript:pesquisarRota('inicial');">
					 <img width="23" height="21" border="0"
					 src="<bean:message key="caminho.imagens"/>pesquisa.gif"
					 title="Pesquisar Rota" /></a>						
					</div>
					</td>
				</tr>
				<tr>
					<td colspan="4">
					<hr>
					</td>
				</tr>

				<tr>
					<td><strong>Localidade Final:<font color="#FF0000"> </font></strong></td>
					<td width="37%" align="left" colspan="2">
					
					<logic:empty name="ManterComandoAcaoCobrancaDetalhesActionForm" property="dataRealizacao"> 
					  <html:text maxlength="3"
						property="localidadeDestinoID" size="3" tabindex="8"
						onkeyup="desabilitaIntervaloDiferente(1);"
						onkeypress="javascript:validaEnterComMensagem(event, 'exibirManterComandoAcaoCobrancaDetalhesAction.do?objetoConsulta=1&inscricaoTipo=destino', 'localidadeDestinoID','Localidade Final');" 
						/>
					 </logic:empty>
					 <logic:notEmpty name="ManterComandoAcaoCobrancaDetalhesActionForm" property="dataRealizacao">
					  <html:text maxlength="3"
						property="localidadeDestinoID" size="3" tabindex="8"
						disabled="true"/>
					 </logic:notEmpty>
					
					<img src="<bean:message key='caminho.imagens'/>pesquisa.gif"
						width="23" height="21" border="0" style="cursor:hand;"
						name="imagem"
						onclick="chamarPopup('exibirPesquisarLocalidadeAction.do', 'destino', null, null, 275, 480, '',document.forms[0].localidadeDestinoID);"
						alt="Pesquisar"><logic:present
						name="corLocalidadeDestino">

						<logic:equal name="corLocalidadeDestino" value="exception">
							<html:text property="nomeLocalidadeDestino" size="30"
								maxlength="30" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:equal>

						<logic:notEqual name="corLocalidadeDestino" value="exception">
							<html:text property="nomeLocalidadeDestino" size="30"
								maxlength="30" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEqual>

					</logic:present> <logic:notPresent name="corLocalidadeDestino">

						<logic:empty name="ManterComandoAcaoCobrancaDetalhesActionForm"
							property="localidadeDestinoID">
							<html:text property="nomeLocalidadeDestino" value="" size="30"
								maxlength="30" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:empty>
						<logic:notEmpty name="ManterComandoAcaoCobrancaDetalhesActionForm"
							property="localidadeDestinoID">
							<html:text property="nomeLocalidadeDestino" size="30"
								maxlength="30" readonly="true"
								style="background-color:#EFEFEF; border:0; color: 	#000000" />
						</logic:notEmpty>
					</logic:notPresent>
						<a href="javascript:limparBorrachaDestino(1,document.forms[0].localidadeDestinoID);"> <img name="imagem"
						src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar" /></a>  		
					</td>
				</tr>

				<tr>
					<td><strong>Setor Comercial Final: </strong></td>
					<td align="left" colspan="2">
					<logic:empty name="ManterComandoAcaoCobrancaDetalhesActionForm" property="dataRealizacao"> 
					  <html:text maxlength="3"
						property="setorComercialDestinoCD" size="3" tabindex="9"
						onkeyup="desabilitaIntervaloDiferente(2);habilitarRota();"
						onkeypress="validaEnterDependenciaComMensagem(event, 'exibirManterComandoAcaoCobrancaDetalhesAction.do?objetoConsulta=2&inscricaoTipo=destino&validarCriterio=naoAcao', document.forms[0].setorComercialDestinoCD, document.forms[0].localidadeDestinoID.value, 'Localidade Final', 'Setor Comercial Final');"
						onfocus="validarLocalidade();" />  
					 </logic:empty>
					 <logic:notEmpty name="ManterComandoAcaoCobrancaDetalhesActionForm" property="dataRealizacao">
					  <html:text maxlength="3"
						property="setorComercialDestinoCD" size="3" tabindex="9"
						disabled="true"/>
					 </logic:notEmpty>
					
					<img
						src="<bean:message key='caminho.imagens'/>pesquisa.gif" width="23"
						height="21" style="cursor:hand;" name="imagem"
						onclick="chamarPopup('exibirPesquisarSetorComercialAction.do', 'setorComercialDestino', 'idLocalidade', document.forms[0].localidadeDestinoID.value, 275, 480, 'Informe a Localidade da inscrição de destino',document.forms[0].setorComercialDestinoCD);"
						alt="Pesquisar"><logic:present
						name="corSetorComercialDestino">

						<logic:equal name="corSetorComercialDestino" value="exception">
							<html:text property="nomeSetorComercialDestino" size="30"
								maxlength="30" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:equal>

						<logic:notEqual name="corSetorComercialDestino" value="exception">
							<html:text property="nomeSetorComercialDestino" size="30"
								maxlength="30" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEqual>

					</logic:present> <logic:notPresent name="corSetorComercialDestino">

						<logic:empty name="ManterComandoAcaoCobrancaDetalhesActionForm"
							property="setorComercialDestinoCD">
							<html:text property="nomeSetorComercialDestino" size="30"
								maxlength="30" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:empty>
						<logic:notEmpty name="ManterComandoAcaoCobrancaDetalhesActionForm"
							property="setorComercialDestinoCD">
							<html:text property="nomeSetorComercialDestino" size="30"
								maxlength="30" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEmpty>

					</logic:notPresent> <html:hidden property="setorComercialDestinoID" />
						<a href="javascript:limparBorrachaDestino(2,document.forms[0].setorComercialDestinoCD);"> <img name="imagem"
						src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar" /></a>  		
					</td>
				</tr>

				<tr>
					<td><strong>Quadra Final:</strong></td>
					<td colspan="3">
						<logic:empty name="ManterComandoAcaoCobrancaDetalhesActionForm" property="dataRealizacao"> 
					  		<html:text maxlength="5"
								property="numeroQuadraFinal" 
								size="5" 
								tabindex="13"
                   				onblur="validarQuadra(this, document.forms[0].setorComercialDestinoCD.value, 'Setor Comercial Final');"
                   				onkeypress="return isCampoNumerico(event);"  />
					 	</logic:empty>
					 	
					 <logic:notEmpty name="ManterComandoAcaoCobrancaDetalhesActionForm" property="dataRealizacao">
					  		<html:text maxlength="5"
								property="numeroQuadraFinal" 
								size="5" 
								tabindex="13"
                   				disabled="true"  />					 
					 </logic:notEmpty>					 	
                   </td>					
				</tr>				
				
				<tr>
					<td><strong>Rota Final:<font color="#FF0000"> </font></strong></td>
					<td align="right" colspan="2">
						<div align="left">
						<logic:empty name="ManterComandoAcaoCobrancaDetalhesActionForm" property="dataRealizacao"> 
						  <html:text maxlength="4" tabindex="10"
						     property="rotaFinal" size="4"
						     name="ManterComandoAcaoCobrancaDetalhesActionForm"/> 
						</logic:empty>
						<logic:notEmpty name="ManterComandoAcaoCobrancaDetalhesActionForm" property="dataRealizacao">
						  <html:text maxlength="4" tabindex="10"
						   property="rotaFinal" size="4"
						   name="ManterComandoAcaoCobrancaDetalhesActionForm" disabled="true"/> 
						</logic:notEmpty>
						
						<a href="javascript:pesquisarRota('final');">
						<img width="23" height="21" border="0"
						src="<bean:message key="caminho.imagens"/>pesquisa.gif"
						title="Pesquisar Rota" /></a>	
						</div>					
					</td>
				</tr>
				<tr>
					<td colspan="4">
					<hr>
					</td>
				</tr>
				<tr>
					<td><strong>Cliente Superior:</strong></td>
					<td colspan="3"><strong>
					<logic:empty name="ManterComandoAcaoCobrancaDetalhesActionForm" property="dataRealizacao"> 
					  <html:text maxlength="9"
						property="codigoClienteSuperior" size="9" tabindex="11"
						onkeyup="javaScript:validarClienteSuperior(1);desabilitarLocalidadeCobrancaAcao();"
						onkeypress="javascript:validaEnter(event, 'exibirManterComandoAcaoCobrancaDetalhesAction.do?objetoConsulta=3&inscricaoTipo=destino', 'codigoClienteSuperior');" />
					 </logic:empty>
					 <logic:notEmpty name="ManterComandoAcaoCobrancaDetalhesActionForm" property="dataRealizacao">
					  <html:text maxlength="9"
						property="codigoClienteSuperior" size="9" tabindex="11"
						disabled="true"/>
					 </logic:notEmpty>
					<a href="javascript:validarAbrirPoupPesquisarClienteSuperior();">
					<img border="0"
						src="<bean:message key="caminho.imagens"/>pesquisa.gif"
						title="Pesquisar Cliente" /></a> <logic:present
						name="codigoClienteSuperiorNaoEncontrado" scope="request">
						<input type="text" name="nomeClienteSuperior" size="40" readonly="readonly"
							style="background-color:#EFEFEF; border:0; color: #ff0000"
							value="CLIENTE SUPERIOR INEXISTENTE" />
					</logic:present> <logic:notPresent
						name="codigoClienteSuperiorNaoEncontrado" scope="request">
						<html:text property="nomeClienteSuperior" size="40" readonly="true"
							style="background-color:#EFEFEF; border:0; color: #000000" />
					</logic:notPresent> <a href="javascript:validarLimparClienteSuperior();desabilitarLocalidadeCobrancaAcao()"> <img
						src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar" /></a> </strong></td>
				</tr>
				<tr>
					<td><strong>Cliente:</strong></td>
					<td colspan="3"><strong>
					<logic:empty name="ManterComandoAcaoCobrancaDetalhesActionForm" property="dataRealizacao"> 
					  <html:text maxlength="9"
						property="idCliente" size="9" tabindex="11"
						onkeyup="javaScript:validarCliente();desabilitarLocalidadeCobrancaAcao();"
						onkeypress="javascript:validaEnter(event, 'exibirManterComandoAcaoCobrancaDetalhesAction.do?objetoConsulta=3&inscricaoTipo=destino', 'idCliente');" />
					 </logic:empty>
					 <logic:notEmpty name="ManterComandoAcaoCobrancaDetalhesActionForm" property="dataRealizacao">
					  <html:text maxlength="9"
						property="idCliente" size="9" tabindex="11"
						disabled="true"/>
					 </logic:notEmpty>
					<a href="javascript:validarAbrirPoupPesquisarCliente();">
					<img border="0"
						src="<bean:message key="caminho.imagens"/>pesquisa.gif"
						title="Pesquisar Cliente" /></a> <logic:present
						name="codigoClienteNaoEncontrado" scope="request">
						<input type="text" name="nomeCliente" size="50" readonly="readonly"
							style="background-color:#EFEFEF; border:0; color: #ff0000"
							value="CLIENTE INEXISTENTE" />
					</logic:present> <logic:notPresent
						name="codigoClienteNaoEncontrado" scope="request">
						<html:text property="nomeCliente" size="50" readonly="true"
							style="background-color:#EFEFEF; border:0; color: #000000" />
					</logic:notPresent> <a href="javascript:validarLimparCliente();desabilitarLocalidadeCobrancaAcao();"> <img
						src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar" /></a> </strong></td>
				</tr>
				<tr>
					<td><strong>Tipo de Rela&ccedil;&atilde;o:</strong></td>
					<td colspan="3"><strong> 
					<logic:empty name="ManterComandoAcaoCobrancaDetalhesActionForm" property="dataRealizacao"> 
					  <html:select tabindex="12"
						name="ManterComandoAcaoCobrancaDetalhesActionForm"
						property="clienteRelacaoTipo">
						<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
						<html:options name="request"
							collection="colecaoClienteRelacaoTipo" labelProperty="descricao"
							property="id" />
					</html:select>
					 </logic:empty>
					 <logic:notEmpty name="ManterComandoAcaoCobrancaDetalhesActionForm" property="dataRealizacao">
					  <html:select tabindex="12"
						name="ManterComandoAcaoCobrancaDetalhesActionForm"
						property="clienteRelacaoTipo" disabled="true">
						<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
						<html:options name="request"
							collection="colecaoClienteRelacaoTipo" labelProperty="descricao"
							property="id" />
					</html:select>
					 </logic:notEmpty>
					 </strong></td>
				</tr>
				<tr>
					<td colspan="5">
					<hr>
					</td>
				</tr>
				<tr>
					<td><strong>Per&iacute;odo de Refer&ecirc;ncia das Contas</strong><strong>:</strong></td>
					<td colspan="3"><strong> 
					<logic:empty name="ManterComandoAcaoCobrancaDetalhesActionForm" property="dataRealizacao"> 
					  <html:text maxlength="7"
						property="periodoInicialConta" size="7" tabindex="13"
						onkeyup="mascaraAnoMes(this, event);" /><strong> a</strong> <html:text
						name="ManterComandoAcaoCobrancaDetalhesActionForm" maxlength="7"
						property="periodoFinalConta" size="7" tabindex="14"
						onkeyup="mascaraAnoMes(this, event);" />
					 </logic:empty>
					 <logic:notEmpty name="ManterComandoAcaoCobrancaDetalhesActionForm" property="dataRealizacao">
					 <html:text maxlength="7"
						property="periodoInicialConta" size="7" tabindex="13" disabled="true"/><strong> a</strong>
					 <html:text name="ManterComandoAcaoCobrancaDetalhesActionForm" maxlength="7"
						property="periodoFinalConta" size="7" tabindex="14"
						disabled="true"/>
					 </logic:notEmpty>
					 </strong></td>
				</tr>
				<tr>
					<td><strong>Per&iacute;odo de Vencimento das Contas:</strong></td>
					<td colspan="3"> 
					<logic:empty name="ManterComandoAcaoCobrancaDetalhesActionForm" property="dataRealizacao"> 
					  <html:text maxlength="10" tabindex="15"
						property="periodoVencimentoContaInicial" size="10"
						onkeyup="mascaraData(this, event);" />
					  <a href="javascript:abrirCalendario('ManterComandoAcaoCobrancaDetalhesActionForm', 'periodoVencimentoContaInicial')">
					  <img border="0"
						src="<bean:message key='caminho.imagens'/>calendario.gif"
						width="20" border="0" align="middle" alt="Exibir Calendário" /></a><strong>
					    a</strong> <html:text maxlength="10" tabindex="16"
						property="periodoVencimentoContaFinal" size="10"
						onkeyup="mascaraData(this, event);" /> <a
						href="javascript:abrirCalendario('ManterComandoAcaoCobrancaDetalhesActionForm', 'periodoVencimentoContaFinal')">
					    <img border="0"
						src="<bean:message key='caminho.imagens'/>calendario.gif"
						width="20" border="0" align="middle" alt="Exibir Calendário" /></a>
					 </logic:empty>
					 <logic:notEmpty name="ManterComandoAcaoCobrancaDetalhesActionForm" property="dataRealizacao">
					 <html:text maxlength="10" tabindex="15"
						property="periodoVencimentoContaInicial" size="10"
						onkeyup="mascaraData(this, event);" disabled="true"/>
						<strong>a</strong>
						<html:text maxlength="10" tabindex="16"
						property="periodoVencimentoContaFinal" size="10"
						onkeyup="mascaraData(this, event);" disabled="true"/> 
					 </logic:notEmpty>
				   </td>
				</tr>
				<tr>
					<td colspan="4">
					<hr>
					</td>
				</tr>
				<tr>
						<td><strong>Consumo Médio do Imóvel:</strong></td>
						<td colspan="3"><strong> <html:text maxlength="7"
							property="consumoMedioInicial" size="7" tabindex="13"
							onkeyup="replicarCampo(document.forms[0].consumoMedioFinal,this);validarExibicaoTipoCosumo();"
							onkeypress="return isCampoNumerico(event);"/><strong> a</strong> <html:text
							name="ManterComandoAcaoCobrancaDetalhesActionForm" tabindex="14"
							maxlength="7" property="consumoMedioFinal" size="7"					
							onkeypress="validarExibicaoTipoCosumo();return isCampoNumerico(event);" /> </strong>
						</td>
				</tr>
				<tr>
						<td><strong>Tipo de Consumo:</strong></td>
						<td colspan="3"><strong> 
							<html:select tabindex="12"
								name="ManterComandoAcaoCobrancaDetalhesActionForm"
								property="tipoConsumo" disabled="true">
								<html:option value="1">MEDIDO</html:option>
								<html:option value="2">NÃO MEDIDO</html:option>
								<html:option value="3">AMBOS</html:option>
							</html:select> </strong>
						</td>
				</tr>
				<logic:present scope="session" name="colecaoFiscalizacaoSituacao">					
					<tr>
						<td colspan="4">
						<hr>
						</td>
					</tr>
					<tr>
						<td><strong>Período de Fiscalização do Imóvel:</strong></td>
						<td colspan="3"><strong> <html:text maxlength="10" tabindex="15"
							property="periodoInicialFiscalizacao" size="10"
							onkeyup="mascaraData(this, event);"
							onkeypress="return isCampoNumerico(event);" /> <a
							href="javascript:abrirCalendario('ManterComandoAcaoCobrancaDetalhesActionForm', 'periodoInicialFiscalizacao')">
						<img border="0"
							src="<bean:message key='caminho.imagens'/>calendario.gif"
							width="20" border="0" align="middle" alt="Exibir Calendário" /></a><strong>
						a</strong> <html:text maxlength="10" tabindex="16"
							property="periodoFinalFiscalizacao" size="10"
							onkeyup="mascaraData(this, event);"
							onkeypress="return isCampoNumerico(event);" /> <a
							href="javascript:abrirCalendario('ManterComandoAcaoCobrancaDetalhesActionForm', 'periodoFinalFiscalizacao')">
						<img border="0"
							src="<bean:message key='caminho.imagens'/>calendario.gif"
							width="20" border="0" align="middle" alt="Exibir Calendário" /></a>
						</strong>dd/mm/aaaa</td>
					</tr>		
					<tr>
						<td width="16%"><strong>Situação de Fiscalização:</strong></td>
						<td colspan="3"><html:select property="situacaoFiscalizacao" style="height: 100px" multiple="true" tabindex="3">
							<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
							<logic:present scope="session" name="colecaoFiscalizacaoSituacao">
								<html:options name="session" collection="colecaoFiscalizacaoSituacao"
									labelProperty="descricaoFiscalizacaoSituacao" property="id" />
							</logic:present>
						</html:select></td>
					</tr>
				</logic:present>
				<tr>
					<td colspan="4">
					<hr>
					</td>
				</tr>
				<tr>
					<td><strong>Indicador do Crit&eacute;rio:<font color="#FF0000">*</font></strong></td>
					<td colspan="3">
					<logic:empty name="ManterComandoAcaoCobrancaDetalhesActionForm" property="dataRealizacao"> 
					  <html:radio property="indicador" tabindex="17"
						value="Rota" onclick="habilitar('1');" />  Usa
					Crit&eacute;rio da Rota 
						<html:radio property="indicador" value="Comando" tabindex="18"
							onclick="habilitar('2');" />
					 </logic:empty>
					 <logic:notEmpty name="ManterComandoAcaoCobrancaDetalhesActionForm" property="dataRealizacao">
					  <html:radio property="indicador" tabindex="17"
						value="Rota" disabled="true"/> Usa
					Crit&eacute;rio da Rota 
						<html:radio property="indicador" value="Comando" tabindex="18"
							disabled="true" />
					 </logic:notEmpty>
					
					
						<strong>Usa <strong><strong>Crit&eacute;rio</strong> do Comando</strong>
						</strong>
					</td>
				</tr>
				<tr>
					<td height="17"><strong></strong></td>
					<td colspan="3"><strong><font color="#FF0000">*</font></strong>
					Campos obrigat&oacute;rios</td>
				</tr>
				<tr>
					<td colspan="4">&nbsp;</td>
				</tr>
				<tr>
					<td colspan="4">&nbsp;</td>
				</tr>
				<tr>
					<td height="17" colspan="4">&nbsp;</td>
				</tr>
				<tr>
					<td colspan="2" align="left"><!--  <input name="executarComando"
						type="button" class="bottonRightCol" onClick="executarcomando();"
						value="Executar Comando" />
						-->
						</td>
					<td colspan="2" align="right">
					<table border="0">
						<tr>
							<td align="right"><img src="imagens/voltar.gif" width="15"
								height="24" border="0" /></td>
							<td align="right"><input name="Button32222" type="button"
								class="bottonRightCol" value="Voltar"
								onClick="javascript:voltar()" /></td>
							<td align="right"><input name="Avancar" type="button"
								class="bottonRightCol" disabled value="Avançar"
								onClick="avancar();" /></td>
							<td align="right"><img src="imagens/avancar.gif" width="15"
								height="24" border="0" /></td>
							<td align="right"></td>
						</tr>
					</table>
					</td>
				</tr>
				<tr>
					<td colspan="3" width="100%" height="1px" bgcolor="#000000"></td>
				</tr>				
				<tr>
					<td colspan="2"><strong> <font color="#ff0000"> <input name="Submit22"
						class="bottonRightCol" value="Desfazer" type="button"
						onClick="javascript:voltar();" >

					<input name="Submit23" class="bottonRightCol" value="Cancelar"
						type="button"
						onclick="window.location.href='/gsan/telaPrincipal.do'"> </font></strong></td>
					<td align="right">
					  <gsan:controleAcessoBotao name="concluir" value="Concluir" onclick="javascript:concluircomando();" url="manterComandoAcaoCobrancaEventualConcluirAction.do"/>
					  <%-- <input type="button" name="concluir" class="bottonRightCol" value="Concluir" onclick="javascript:concluircomando();" /> --%>
					</td>
				</tr>				
			</table>
			<p>&nbsp;</p>
			</td>
		</tr>
	</table>
	<%@ include file="/jsp/util/rodape.jsp"%>
</html:form>
</body>
<script language="JavaScript">
<!-- Begin

	habilitar(document.forms[0].cobrancaAtividadeIndicadorExecucao.value);
///	validarGrupoCobranca(0);
	validarCliente(0);
	
	validarClienteSuperior(1);
	desabilitarLocalidadeCobrancaAcao();

	validarGrupoCobrancaParaBloquear();

	validarGerenciaRegionalParaBloquear();

	validarUnidadeNegocioParaBloquear();
	
	verificarCampoPrazoExecucao();

///	habilitacaoCampoRota();
	validarClienteParaBloquear();

	validarLocalidadeSetorRotaParaBloquear();

	validarHabilitarCampo();
-->
</script>
</html:html>
