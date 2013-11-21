<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan" %>

<%@ page import="gcom.util.Util" %>
<%@ page import="gcom.cobranca.parcelamento.ParcelamentoQuantidadeReparcelamentoHelper" %>
<%@ page import="gcom.cobranca.parcelamento.ParcelamentoDescontoAntiguidade" %>
<%@ page import="gcom.cobranca.parcelamento.ParcelamentoDescontoInatividade" %>
<%@ page import="gcom.cobranca.parcelamento.ParcDesctoInativVista" %>
<%@ page import="gcom.util.ConstantesSistema" isELIgnored="false"%>
<%@ page import="java.util.Collection" isELIgnored="false"%>

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
	<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="ParcelamentoPerfilActionForm" />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript"><!--


	 function caracteresespeciais () { 
	     this.aa = new Array("qtdeMaximaReparcelamento", "Reparcelamentos Consecutivos possui caracteres especiais.", new Function ("varName", " return this[varName];"));
	     this.ab = new Array("quantidadeMinimaMesesDebito", "Qtde. Mínima Meses de Débito p/ Desconto possui caracteres especiais.", new Function ("varName", " return this[varName];"));
	     this.ac = new Array("quantidadeMaximaMesesInatividade", "Qtde. Máxima Meses de Inatividade da Lig. de Água possui caracteres especiais.", new Function ("varName", " return this[varName];"));
	     this.ad = new Array("quantidadeMaximaMesesInatividadeAVista", "Qtde. Máxima Meses de Inatividade da Lig. de Água possui caracteres especiais.", new Function ("varName", " return this[varName];"));
	  }
	
	 function InteiroZeroPositivoValidations () {
	     this.aa = new Array("qtdeMaximaReparcelamento", " Reparcelamentos Consecutivos deve somente conter números positivos ou zero.", new Function ("varName", " return this[varName];"));
	     this.ab = new Array("quantidadeMinimaMesesDebito", "Qtde. Mínima Meses de Débito p/ Desconto deve somente conter números positivos ou zero.", new Function ("varName", " return this[varName];"));
	     this.ac = new Array("quantidadeMaximaMesesInatividade", "Qtde. Máxima Meses de Inatividade da Lig. de Água deve somente conter números positivos ou zero.", new Function ("varName", " return this[varName];"));
	     this.ad = new Array("quantidadeMaximaMesesInatividadeAVista", "Qtde. Máxima Meses de Inatividade da Lig. de Água deve somente conter números positivos ou zero.", new Function ("varName", " return this[varName];"));
	 }
	
	
	 var bCancel = false; 
	 function validaCaracterEspeciaisInteger(form) {                                                                   
				
	      if (bCancel) {
	      	return true; 
	      } else {
	       	return  validateCaracterEspecial(form) && validateInteiroZeroPositivo(form);
	       	}
   	  
   } 

	function validaRequiredAdicionarReparcelamento () {
		var form = document.forms[0];
		var msg = '';
		if( form.qtdeMaximaReparcelamento.value  == '' ) {
			msg = 'Informe Qtde. Máxima Reparcelamentos Consecutivos.\n';
		}
		if( msg != '' ){
			alert(msg);
			return false;
		}else if (validaCaracterEspeciaisInteger(form)){
			return true;
		}
	}

	function adicionarReparcelamento (form){

		if (validaRequiredAdicionarReparcelamento()){
			if(isNaN(form.qtdeMaximaReparcelamento.value)){	
	 			alert('Qtde. Máxima Reparcelamentos Consecutivos possui caracteres especiais.');
	 			form.qtdeMaximaReparcelamento.focus();
			}else {
				abrirPopupComSubmitBotao(form,'','');
			}
		}
	}
	
	function validaRequiredAdicionarParcelamentoDescontoAntiguidade () {
		var form = document.forms[0];
		var msg = '';
		
		if( form.quantidadeMinimaMesesDebito.value  == '' 
			|| form.percentualDescontoSemRestabelecimentoAntiguidade.value  == ''
			|| form.percentualDescontoComRestabelecimentoAntiguidade.value  == ''	
			|| form.percentualDescontoAtivo.value == '') {
		
			msg = msg + 'O preenchimento dos campos Qtde. Mínima Meses de Débito p/ Desconto, Percentual de Desconto Sem Restabelecimento, Percentual de Desconto Com Restabelecimento, Percentual de Desconto Ativo é obrigatório, caso algum deles seja informado.';
		
		}
		
		// if( form.quantidadeMinimaMesesDebito.value  == '' ) {
		//	msg = msg + 'Informe Qtde. Mínima Meses de Débito p/ Desconto.\n';
		//}
		//if( form.percentualDescontoSemRestabelecimentoAntiguidade.value  == '' ) {
		//	msg = msg + 'Informe Percentual de Desconto Sem Restabelecimento.\n';
		//}
		//if( form.percentualDescontoComRestabelecimentoAntiguidade.value  == '' ) {
		//	msg = msg + 'Informe Percentual de Desconto Com Restabelecimento.\n';
		//}
		//if( form.percentualDescontoAtivo.value == '' ) {
		//	msg = msg + 'Informe Percentual de Desconto Ativo.';
		//}
		
		if( msg != '' ){
			alert(msg);
			return false;
		}else if (validaCaracterEspeciaisInteger(form)){
			return true;
		}
	}
	
	function validaCampoZeroAdicionarParcelamentoDescontoAntiguidade () {
		var form = document.forms[0];
		var msg = '';
		
		if( !testarValorZero(form.quantidadeMinimaMesesDebito)) {
			msg = msg + 'Qtde. Mínima Meses de Débito p/ Desconto deve somente conter números positivos.\n';
		}
		if( !testarValorZero(form.percentualDescontoSemRestabelecimentoAntiguidade)) {
			msg = msg + 'Percentual de Desconto Sem Restabelecimento deve somente conter números decimais positivos.\n';
		}
		if( !testarValorZero(form.percentualDescontoComRestabelecimentoAntiguidade)) {
			msg = msg + 'Percentual de Desconto Com Restabelecimento deve somente conter números decimais positivos.\n';
		}
		if( !testarValorZero(form.percentualDescontoAtivo)) {
			msg = msg + 'Percentual de Desconto Ativo deve somente conter números decimais positivos.';
		}
		if( msg != '' ){
			alert(msg);
			return false;
		}else{
			return true;
		}
	}
	
	function adicionarParcelamentoDescontoAntiguidade (form){
    var PERCENTUAL_MAXIMO_ABATIMENTO = document.getElementById("PERCENTUAL_MAXIMO_ABATIMENTO").value;
   
    if (validaRequiredAdicionarParcelamentoDescontoAntiguidade()){
		if(isNaN(form.quantidadeMinimaMesesDebito.value)){	
 			alert('Qtde. Mínima Meses de Débito p/ Desconto possui caracteres especiais.'); 
 			form.quantidadeMinimaMesesDebito.focus();	
 		}else if (validaCampoZeroAdicionarParcelamentoDescontoAntiguidade()){
				
	 			//if(parseFloat(form.percentualDescontoSemRestabelecimentoAntiguidade.value.replace(",", ".")) > parseFloat(PERCENTUAL_MAXIMO_ABATIMENTO.replace(",", "."))){
				//	alert('Percentual de Desconto Sem Restabelecimento é superior ao Percentual Máximo de Desconto de ' + PERCENTUAL_MAXIMO_ABATIMENTO +  ' permitido para Financiamento' );
				//	form.percentualDescontoSemRestabelecimentoAntiguidade.focus();
				//}else if(parseFloat(form.percentualDescontoComRestabelecimentoAntiguidade.value.replace(",", ".")) > parseFloat(PERCENTUAL_MAXIMO_ABATIMENTO.replace(",", "."))){
				//	alert('Percentual de Desconto Com Restabelecimento é superior ao Percentual Máximo de Desconto de ' + PERCENTUAL_MAXIMO_ABATIMENTO +  ' permitido para Financiamento' );
				//	form.percentualDescontoComRestabelecimentoAntiguidade.focus();
				//}else if(parseFloat(form.percentualDescontoAtivo.value.replace(",", ".")) > parseFloat(PERCENTUAL_MAXIMO_ABATIMENTO.replace(",", "."))){
				//	alert('Percentual de Desconto Ativo é superior ao Percentual Máximo de Desconto de ' + PERCENTUAL_MAXIMO_ABATIMENTO +  ' permitido para Financiamento' );
				//	form.percentualDescontoAtivo.focus();
				//}else{ 		
					document.forms[0].target='';
					form.action = "exibirInserirPerfilParcelamentoAction.do?adicionarParcelamentoDescontoAntiguidade=S";
			   		submeterFormPadrao(form);
		   		//}
						
 		}		   		
    }
   }
	
	
	//Testa se o campo foi digitado somente com zeros
	function testarValorZero(valor) {
		var retorno = true;
		var conteudo = valor.value.replace(",","");
		var conteudo = conteudo.replace(".","");
		
		if (trim(valor.value).length > 0){
			if (isNaN(valor.value)) {
				for (x= 0; x < conteudo.length; x++){
					if (conteudo.substr(x, 1) != "0"){
						retorno = true;
						break;
					}
					else{
						retorno = false;	
					}
				}
			}
			else {
				var intValorCampo = valor.value * 1;
				if (intValorCampo == 0) {
	        		retorno =  false;
				}
			}
		}
		return retorno;
	}
		
	function reloadPage(){
	  document.forms[0].reload();
	}


	function validarIndicadorParcelarSancoesMaisDeUmaConta(){

	    var form = document.forms[0];
	    
	    var indice;
	    var array = new Array(form.indicadorParcelarSancoesMaisDeUmaConta.length);
	    var selecionado = "";
	    var formulario = document.forms[0]; 
	    for(indice = 0; indice < form.elements.length; indice++){
		   if (form.elements[indice].type == 'radio' && form.elements[indice].checked == true && formulario.elements[indice].name == "indicadorParcelarSancoesMaisDeUmaConta") {
		      selecionado = form.elements[indice].value;
		      indice = form.elements.length;
		   }
	    }    
		if(selecionado == ''){
			alert('Informe Não parcelar com sanções em mais de uma conta.');
			indicadorParcelarSancoesMaisDeUmaConta.focus();
		}else{
			return true;
		}	
	}


	function validarIndicadorParcelarChequeDevolvido(){

	    var form = document.forms[0];
	    
	    var indice;
	    var array = new Array(form.indicadorParcelarChequeDevolvido.length);
	    var selecionado = "";
	    var formulario = document.forms[0]; 
	    for(indice = 0; indice < form.elements.length; indice++){
		   if (form.elements[indice].type == 'radio' && form.elements[indice].checked == true && formulario.elements[indice].name == "indicadorParcelarChequeDevolvido") {
		      selecionado = form.elements[indice].value;
		      indice = form.elements.length;
		   }
	    }    
		if(selecionado == ''){
			alert('Informe Não parcelar com situação de cobrança.');
			indicadorParcelarChequeDevolvido.focus();
		}else{
			return true;
		}	
	}

	function validarForm(form){
		
		if(validateParcelamentoPerfilActionForm(form)){
		
			if ( validarIndicadorParcelarChequeDevolvido() &&
			validarIndicadorRetroativoTarifaSocial() && validarIndicadorAlertaParcelaMinima() &&
			validarIndicadorEntradaMinima() && validarIndicadorCapacidadeHidrometro() 
			&& validarIndicadorParcelarSancoesMaisDeUmaConta()){
				
				if (<%=session.getAttribute("collectionParcelamentoQuantidadeReparcelamentoHelperVazia")%> == "1"){
					alert('Informe Reparcelamento Consecutivo.');
				}else{
					document.forms[0].target='';
					submeterFormPadrao(form);
				}
			
			}
		}
	}
   
   
   function validaRequiredAdicionarParcelamentoDescontoInatividade () {
		var form = document.forms[0];
		var msg = '';
		
		if( form.quantidadeMaximaMesesInatividade.value  == '' 
			|| form.percentualDescontoSemRestabelecimentoInatividade.value  == ''
			|| form.percentualDescontoComRestabelecimentoInatividade.value  == '') {
		
			msg = msg + 'O preenchimento dos campos Qtde. Máxima Meses de Inatividade da Lig. de Água, Percentual de Desconto Sem Restabelecimento, Percentual de Desconto Com Restabelecimento é obrigatório, caso algum deles seja informado.';
		
		}
		
		
		//if( form.quantidadeMaximaMesesInatividade.value  == '' ) {
		//	msg = msg + 'Informe Qtde. Máxima Meses de Inatividade da Lig. de Água.\n';
		//}
		//if( form.percentualDescontoSemRestabelecimentoInatividade.value  == '' ) {
		//	msg = msg + 'Informe Percentual de Desconto Sem Restabelecimento.\n';
		//}
		//if( form.percentualDescontoComRestabelecimentoInatividade.value  == '' ) {
		//	msg = msg + 'Informe Percentual de Desconto Com Restabelecimento.';
		//}

		if( msg != '' ){
			alert(msg);
			return false;
		}else if (validaCaracterEspeciaisInteger(form)){
			return true;
		}
	}
	
	function validaCampoZeroAdicionarParcelamentoDescontoInatividade () {
		var form = document.forms[0];
		var msg = '';
		if( !testarValorZero(form.quantidadeMaximaMesesInatividade)) {
			msg = msg + 'Qtde. Máxima Meses de Inatividade da Lig. de Água deve somente conter números positivos.\n';
		}
		if( !testarValorZero(form.percentualDescontoSemRestabelecimentoInatividade)) {
			msg = msg + 'Percentual de Desconto Sem Restabelecimento deve somente conter números decimais positivos.\n';
		}
		if( !testarValorZero(form.percentualDescontoComRestabelecimentoInatividade)) {
			msg = msg + 'Percentual de Desconto Com Restabelecimento deve somente conter números decimais positivos.';
		}
		if( msg != '' ){
			alert(msg);
			return false;
		}else{
			return true;
		}
	}
	
   
    function adicionarParcelamentoDescontoInatividade (form){
    var PERCENTUAL_MAXIMO_ABATIMENTO = document.getElementById("PERCENTUAL_MAXIMO_ABATIMENTO").value;
   
	    if (validaRequiredAdicionarParcelamentoDescontoInatividade()){
			
			if(isNaN(form.quantidadeMaximaMesesInatividade.value)){	
	 			alert('Qtde. Máxima Meses de Inatividade da Lig. de Água possui caracteres especiais.'); 
	 			form.quantidadeMaximaMesesInatividade.focus();	
	 		
	 		}else if (validaCampoZeroAdicionarParcelamentoDescontoInatividade()){
			    //if(parseFloat(form.percentualDescontoSemRestabelecimentoInatividade.value.replace(",", ".")) > parseFloat(PERCENTUAL_MAXIMO_ABATIMENTO.replace(",", "."))){
				//	alert('Percentual de Desconto Sem Restabelecimento é superior ao Percentual Máximo de Desconto de ' + PERCENTUAL_MAXIMO_ABATIMENTO +  ' permitido para Financiamento' );
				//	form.percentualDescontoSemRestabelecimentoInatividade.focus();
				//}else if(parseFloat(form.percentualDescontoComRestabelecimentoInatividade.value.replace(",", ".")) > parseFloat(PERCENTUAL_MAXIMO_ABATIMENTO.replace(",", "."))){
				//	alert('Percentual de Desconto Com Restabelecimento é superior ao Percentual Máximo de Desconto de ' + PERCENTUAL_MAXIMO_ABATIMENTO +  ' permitido para Financiamento' );
				//	form.percentualDescontoComRestabelecimentoInatividade.focus();
				//}else{ 		
					document.forms[0].target='';
				    form.action = "exibirInserirPerfilParcelamentoAction.do?adicionarParcelamentoDescontoInatividade=S";
				    submeterFormPadrao(form);
		   		//}
			}
			
	   	}
    } 
	function verificaPercentualMaximoAbatimento(percentualDesconto){
	var PERCENTUAL_MAXIMO_ABATIMENTO = document.getElementById("PERCENTUAL_MAXIMO_ABATIMENTO").value;
	
		if(percentualDesconto.value!= "" && PERCENTUAL_MAXIMO_ABATIMENTO!= ""){

			if (testarCampoValorZero(percentualDesconto, ' Percentual de Desconto')){
				 if(parseFloat(percentualDesconto.value.replace(",", ".")) > parseFloat(PERCENTUAL_MAXIMO_ABATIMENTO.replace(",", "."))){
					alert('Percentual de Desconto é superior ao Percentual Máximo de Desconto ' + PERCENTUAL_MAXIMO_ABATIMENTO +  ' permitido para Financiamento');
					percentualDesconto.focus();
	   			 }
			}			
		}
	}
   
	function abrirPopupComSubmitLink(form,altura, largura,qtdeMaxReparcelamento){
		var height = window.screen.height - 160;
		var width = window.screen.width;
		var top = (height - altura)/2;
		var left = (width - largura)/2;
				
		window.open('', 'Pesquisar','top=' + top + ',left='+ left +',location=no,screenY=0,screenX=0,menubar=no,status=no,toolbar=no,scrollbars=yes,resizable=no,width=650,height=670');
		form.target='Pesquisar';
		form.action = 'exibirInserirPrestacoesParcelamentoPerfilAction.do?primeiraVez=S&qtdeMaximaReparcelamento='+ qtdeMaxReparcelamento ;
		submeterFormPadrao(form);
	}
	
	function abrirPopupComSubmitBotao(form,altura,largura){
		var height = window.screen.height - 160;
		var width = window.screen.width;
		var top = (height - altura)/2;
		var left = (width - largura)/2;
		window.open('', 'Pesquisar','top=' + top + ',left='+ left +',location=no,screenY=0,screenX=0,menubar=no,status=no,toolbar=no,scrollbars=yes,resizable=no,width=' + largura + ',height=' + altura);
		form.target='Pesquisar';
		form.action = 'exibirInserirPrestacoesParcelamentoPerfilAction.do?primeiraVez=S&adicionarReparcelamento=S' ;
		submeterFormPadrao(form);
	}
	
	function teste(){
		window.location.href='/gsan/exibirInserirPerfilParcelamentoAction.do'
	}
   
   	function replicarCampo(fim,inicio) {
    	fim.value = inicio.value;
	}


	function validarIndicadorRetroativoTarifaSocial(){

	    var form = document.forms[0];
	    
	    var indice;
	    var array = new Array(form.indicadorRetroativoTarifaSocial.length);
	    var selecionado = "";
	    var formulario = document.forms[0]; 
	    for(indice = 0; indice < form.elements.length; indice++){
		   if (form.elements[indice].type == 'radio' && form.elements[indice].checked == true && formulario.elements[indice].name == "indicadorRetroativoTarifaSocial") {
		      selecionado = form.elements[indice].value;
		      indice = form.elements.length;
		   }
	    }    
		if(selecionado == ''){
			alert('Informe Indicador de retroativo de tarifa social.');
			indicadorRetroativoTarifaSocial.focus();
		}else{
			return true;
		}	
	}
	
	function validarIndicadorAlertaParcelaMinima(){

	    var form = document.forms[0];
	    
	    var indice;
	    var array = new Array(form.indicadorAlertaParcelaMinima.length);
	    var selecionado = "";
	    var formulario = document.forms[0]; 
	    for(indice = 0; indice < form.elements.length; indice++){
		   if (form.elements[indice].type == 'radio' && form.elements[indice].checked == true && formulario.elements[indice].name == "indicadorAlertaParcelaMinima") {
		      selecionado = form.elements[indice].value;
		      indice = form.elements.length;
		   }
	    }    
		if(selecionado == ''){
			alert('Informe Indicador de alerta de parcela mínima.');
			indicadorAlertaParcelaMinima.focus();
		}else{
			return true;
		}	
	}
	
		function validarIndicadorEntradaMinima(){

	    var form = document.forms[0];
	    
	    var indice;
	    var array = new Array(form.indicadorEntradaMinima.length);
	    var selecionado = "";
	    var formulario = document.forms[0]; 
	    for(indice = 0; indice < form.elements.length; indice++){
		   if (form.elements[indice].type == 'radio' && form.elements[indice].checked == true && formulario.elements[indice].name == "indicadorEntradaMinima") {
		      selecionado = form.elements[indice].value;
		      indice = form.elements.length;
		   }
	    }    
		if(selecionado == ''){
			alert('Informe Indicador de entrada mínima.');
			indicadorEntradaMinima.focus();
		}else{
			return true;
		}	
	}
	
	function validarIndicadorCapacidadeHidrometro(){

	    var form = document.forms[0];
	    
	    var indice;
	    var array = new Array(form.capacidadeHidrometro.length);
	    var selecionado = "";
	    var formulario = document.forms[0]; 
	    for(indice = 0; indice < form.elements.length; indice++){
		   if (form.elements[indice].type == 'radio' && form.elements[indice].checked == true && formulario.elements[indice].name == "capacidadeHidrometro") {
		      selecionado = form.elements[indice].value;
		      indice = form.elements.length;
		   }
	    }    
		if(selecionado == ''){
			alert('Informe Indicador pesquisa capacidade do hidrometro.');
			capacidadeHidrometro.focus();
		}else{
			return true;
		}	
	}
	
	function adicionarParcelamentoDescontoInatividadeAVista (form){
    var PERCENTUAL_MAXIMO_ABATIMENTO = document.getElementById("PERCENTUAL_MAXIMO_ABATIMENTO").value;
   
	    if (validaRequiredAdicionarParcelamentoDescontoInatividadeAVista()){
			
			if(isNaN(form.quantidadeMaximaMesesInatividadeAVista.value)){	
	 			alert('Qtde. Máxima Meses de Inatividade da Lig. de Água possui caracteres especiais.'); 
	 			form.quantidadeMaximaMesesInatividadeAVista.focus();	
	 		
	 		}else if (validaCampoZeroAdicionarParcelamentoDescontoInatividadeAVista()){
				document.forms[0].target='';
			    form.action = "exibirInserirPerfilParcelamentoAction.do?adicionarParcelamentoDescontoInatividadeAVista=S";
			    submeterFormPadrao(form);
			}
			
	   	}
    } 
    
     function validaRequiredAdicionarParcelamentoDescontoInatividadeAVista () {
		var form = document.forms[0];
		var msg = '';
		
		if( form.quantidadeMaximaMesesInatividadeAVista.value  == '' 
			|| form.percentualDescontoSemRestabelecimentoInatividadeAVista.value  == ''
			|| form.percentualDescontoComRestabelecimentoInatividadeAVista.value  == '') {
		
			msg = msg + 'O preenchimento dos campos Qtde. Máxima Meses de Inatividade da Lig. de Água, Percentual de Desconto Sem Restabelecimento, Percentual de Desconto Com Restabelecimento é obrigatório, caso algum deles seja informado.';
		
		}

		if( msg != '' ){
			alert(msg);
			return false;
		}else if (validaCaracterEspeciaisInteger(form)){
			return true;
		}
	}
	
	function validaCampoZeroAdicionarParcelamentoDescontoInatividadeAVista () {
		var form = document.forms[0];
		var msg = '';
		if( !testarValorZero(form.quantidadeMaximaMesesInatividadeAVista)) {
			msg = msg + 'Qtde. Máxima Meses de Inatividade da Lig. de Água deve somente conter números positivos.\n';
		}
		if( !testarValorZero(form.percentualDescontoSemRestabelecimentoInatividadeAVista)) {
			msg = msg + 'Percentual de Desconto Sem Restabelecimento deve somente conter números decimais positivos.\n';
		}
		if( !testarValorZero(form.percentualDescontoComRestabelecimentoInatividadeAVista)) {
			msg = msg + 'Percentual de Desconto Com Restabelecimento deve somente conter números decimais positivos.';
		}
		if( msg != '' ){
			alert(msg);
			return false;
		}else{
			return true;
		}
	}
	
--></script>
</head>
<body leftmargin="0" topmargin="0" onload="setarFoco('${requestScope.nomeCampo}');">

<html:form action="/inserirPerfilParcelamentoAction"
	name="ParcelamentoPerfilActionForm"
	type="gcom.gui.cobranca.parcelamento.ParcelamentoPerfilActionForm"
	method="post"
	onsubmit="return validateParcelamentoPerfilActionForm(this);">

<input type="hidden" id="PERCENTUAL_MAXIMO_ABATIMENTO" value="${requestScope.percentualMaximoAbatimento}"/>

<%@ include file="/jsp/util/cabecalho.jsp"%>
<%@ include file="/jsp/util/menu.jsp" %>

<table width="770" border="0" cellspacing="5" cellpadding="0">
	<tr>
		<td width="120" valign="top" class="leftcoltext">
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
		<td valign="top" class="centercoltext">
            <table>
              <tr><td></td></tr>
              
            </table>
            <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
              <tr>
                <td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_left.gif"/></td>
                <td class="parabg">Inserir Perfil de Parcelamento</td>
                <td width="11" valign="top"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif"/></td>
              </tr>
            </table>


			<p>&nbsp;</p>
			<table width="100%" border="0">

				<tr> 
                	<td colspan="2">Para adicionar o perfil de parcelamento, informe os dados abaixo:</td>
	            </tr>
				
             	<tr>
					<td width="50%"><strong>Número da RD:<font color="#FF0000">*</font></strong></td>
					<td><html:select property="resolucaoDiretoria" tabindex="1">
						<html:option value="-1">&nbsp;</html:option>
						<html:options collection="collectionResolucaoDiretoria"	labelProperty="numeroResolucaoDiretoria" property="id" />
					</html:select> <font size="1">&nbsp; </font></td>
				</tr>
				
              	<tr>
					<td><strong>Tipo da Situação do Imóvel:<font color="#FF0000">*</font></strong></td>
					<td><html:select property="imovelSituacaoTipo" tabindex="2">
						<html:option value="-1">&nbsp;</html:option>
						<html:options collection="collectionImovelSituacaoTipo"	labelProperty="descricaoImovelSituacaoTipo" property="id" />
					</html:select> <font size="1">&nbsp; </font></td>
				</tr>
					
					
              	<tr>
					<td><strong>Perfil do Imóvel:</strong></td>
					<td><html:select property="imovelPerfil" tabindex="3">
						<html:option value="-1">&nbsp;</html:option>
						<html:options collection="collectionImovelPerfil" labelProperty="descricao" property="id" />
					</html:select> <font size="1">&nbsp; </font></td>
				</tr>
				
				
				<tr>
					<td width="50%"><strong>Subcategoria:</strong></td>
					<td><html:select property="subcategoria" tabindex="4">
						<html:option value="-1">&nbsp;</html:option>
						<html:options collection="collectionSubcategoria" labelProperty="descricao" property="id" />
					</html:select> <font size="1">&nbsp; </font></td>
				</tr>	
				
				<tr>
					<td><strong>Categoria:</strong></td>
					<td><html:select property="categoria" tabindex="5">
						<html:option value="-1">&nbsp;</html:option>
						<html:options collection="collectionCategoria" labelProperty="descricao" property="id" />
					</html:select> <font size="1">&nbsp; </font></td>
				</tr>					
 	
              	<tr> 
                	<td><strong> Percentual de Desconto sobre Multa:</strong><font color="#FF0000">*</font></td>
                	<td>
                		<html:text property="percentualDescontoAcrescimoMulta" size="6" maxlength="6" 
                		tabindex="6" onkeyup="formataValorMonetario(this, 6)" style="text-align:right;" />
                  	</td>
              	</tr>
              	
              	<tr> 
                	<td><strong> Percentual de Desconto sobre Juros Mora:</strong><font color="#FF0000">*</font></td>
                	<td>
                		<html:text property="percentualDescontoAcrescimoJurosMora" size="6" maxlength="6" 
                		tabindex="6" onkeyup="formataValorMonetario(this, 6)" style="text-align:right;" />
                  	</td>
              	</tr>
              	
              	<tr> 
                	<td><strong> Percentual de Desconto sobre Atualização Monetária:</strong><font color="#FF0000">*</font></td>
                	<td>
                		<html:text property="percentualDescontoAcrescimoAtualizacaoMonetaria" size="6" maxlength="6" 
                		tabindex="6" onkeyup="formataValorMonetario(this, 6)" style="text-align:right;" />
                  	</td>
              	</tr>
              	
              	<tr> 
                	<td><strong>Percentual de Desconto sobre os Acréscimos por Impontualidade para pagamento à vista:</strong><font color="#FF0000">*</font></td>
                	<td>
                		<html:text property="percentualDescontoAcrescimoPagamentoAVista" size="6" maxlength="6" 
                		tabindex="7" onkeyup="formataValorMonetario(this, 6)" style="text-align:right;" />
                  	</td>
              	</tr>	
              		
              	<tr> 
                	<td><strong> Percentual da Tarifa Mínima para Cálculo do Valor Mínimo da Prestação:<font color="#FF0000">*</font></strong></td>
                	<td>
                		<html:text property="percentualTarifaMinimaPrestacao" size="6" maxlength="6" 
                		tabindex="8" onkeyup="formataValorMonetario(this, 6)" style="text-align:right;" />
                  	</td>
              	</tr>
              	
			   <tr>
					<td><strong> Percentual de desconto tarifa social:</strong></td>
                 	<td>
               		<html:text property="percentualDescontoTarifaSocial" size="6" maxlength="6" 
               		tabindex="9" onkeyup="formataValorMonetario(this, 6)" style="text-align:right;" />
                 	</td>
				</tr>
				
				<tr>
					<td><strong> Percentual de desconto de sanção:</strong></td>
                 	<td>
               		<html:text property="percentualDescontoSancao" size="6" maxlength="6" 
               		tabindex="10" onkeyup="formataValorMonetario(this, 6)" style="text-align:right;" />
                 	</td>
				</tr>
				
				<tr>
	         		<td><strong> Consumo por economia:</strong></td>
	               	<td>
               		<html:text property="numeroConsumoEconomia" size="6" maxlength="6" tabindex="11" onkeypress="return isCampoNumerico(event);"/>
                 	</td>
              	</tr>
				
				<tr>
					<td><strong> Quantidade mínima da fatura:</strong></td>
                 	<td>
	                 	<html:text property="parcelaQuantidadeMinimaFatura" size="6" maxlength="6" tabindex="12" onkeypress="return isCampoNumerico(event);"/>
                 	</td>
				</tr>
				
				<tr>
					<td><strong> Quantidade de economias:</strong></td>
                 	<td>
               		<html:text property="quantidadeEconomias" size="6" maxlength="6" tabindex="13" onkeypress="return isCampoNumerico(event);"/>
                 	</td>
				</tr>
				<tr>
					<td><strong> Quantidade Máxima de Reparcelamento:</strong></td>
                 	<td>
               		<html:text property="quantidadeMaximaReparcelamento" size="6" maxlength="6" tabindex="15" onkeypress="return isCampoNumerico(event);"/>
                 	</td>
				</tr>
				<tr>
					<td><strong> Área construída:</strong></td>
                 	<td>
               		<html:text property="numeroAreaConstruida" size="9" maxlength="9" 
               		tabindex="14" onkeyup="formataValorMonetario(this, 9)" style="text-align:right;" />
                 	</td>
				</tr>
				
					
				<tr>
					<td><strong>Limites de Datas:</strong></td>
					<td><strong> 
						<html:text maxlength="7" property="anoMesReferenciaLimiteInferior" size="7" tabindex="16"
							onkeyup="mascaraAnoMes(this, event); 
							replicarCampo(document.forms[0].anoMesReferenciaLimiteSuperior,this);" onkeypress="return isCampoNumerico(event);"/> <strong> a</strong>
						<html:text maxlength="7" property="anoMesReferenciaLimiteSuperior"
							size="7" tabindex="19" onkeyup="mascaraAnoMes(this, event);" onkeypress="return isCampoNumerico(event);"/> </strong>
					mm/aaaa</td>
			   </tr>
			   
			   <tr>
					<td><strong>Data limite para o desconto no pagamento à vista:</strong></td>
					
					<td><strong> 
						<html:text
						property="dataLimiteDescontoPagamentoAVista" size="11" maxlength="10"
						tabindex="17"
						onkeyup="mascaraData(this, event);" onkeypress="return isCampoNumerico(event);"/> <a
						href="javascript:abrirCalendario('ParcelamentoPerfilActionForm', 'dataLimiteDescontoPagamentoAVista');">
					<img border="0"
						src="<bean:message key='caminho.imagens'/>calendario.gif"
						width="16" height="15" border="0" alt="Exibir Calendário"
						tabindex="4" /></a> 
					</strong> 
					</td>
					
					
					
			   </tr>
				
				
				<tr> 
					<td><strong>Não parcelar com situação de cobrança: <font color="#FF0000">*</font></strong></td>
					<td>
						<strong>
							<html:radio property="indicadorParcelarChequeDevolvido" value="1" tabindex="17"/>Sim
							<html:radio property="indicadorParcelarChequeDevolvido" value="2" tabindex="18"/>N&atilde;o
						</strong>
					</td>
				</tr>
				
				<tr> 
					<td><strong>Indicador de retroativo de tarifa social: <font color="#FF0000">*</font></strong></td>
					<td colspan="3">
						<strong>
							<html:radio property="indicadorRetroativoTarifaSocial" value="1" tabindex="19"/>Sim
							<html:radio property="indicadorRetroativoTarifaSocial" value="2" tabindex="20"/>N&atilde;o
						</strong>
					</td>
				</tr>
				
				<tr> 
					<td><strong>Indicador de alerta de parcela mínima: <font color="#FF0000">*</font></strong></td>
					<td colspan="3">
						<strong>
							<html:radio property="indicadorAlertaParcelaMinima" value="1" tabindex="21"/>Sim
							<html:radio property="indicadorAlertaParcelaMinima" value="2" tabindex="22"/>N&atilde;o
						</strong>
					</td>
				</tr>
				
				<tr> 
					<td><strong>Indicador de entrada mínima: <font color="#FF0000">*</font></strong></td>
					<td colspan="3">
						<strong>
							<html:radio property="indicadorEntradaMinima" value="1" tabindex="23"/>Sim
							<html:radio property="indicadorEntradaMinima" value="2" tabindex="24"/>N&atilde;o
						</strong>
					</td>
				</tr>
				
				<tr> 
					<td><strong>Indicador pesquisa capacidade do hidrometro: <font color="#FF0000">*</font></strong></td>
					<td colspan="3">
						<strong>
							<html:radio property="capacidadeHidrometro" value="1" tabindex="25"/>Sim
							<html:radio property="capacidadeHidrometro" value="2" tabindex="26"/>N&atilde;o
						</strong>
					</td>
				</tr>
              	
              	<tr bgcolor="#49A3FC">
					<td colspan="2" align="center"><strong> Única Fatura</strong></td>
				</tr>
              	
	
      			<tr>
	         		<td><strong> Consumo mínimo por economia:</strong></td>
	               	<td>
               		<html:text property="consumoMinimo" size="6" maxlength="6" tabindex="25" style="text-align:right;" onkeypress="return isCampoNumerico(event);" />
                 	</td>
              	</tr>
	
				<tr>
					<td><strong> Percentual de variação consumo médio:</strong></td>
                 	<td>
               		<html:text property="percentualVariacaoConsumoMedio" size="6" maxlength="6" 
               		tabindex="26" onkeyup="formataValorMonetario(this, 6)" style="text-align:right;" />
                 	</td>
	
				</tr>
	
              	<tr> 
					<td><strong>Não parcelar com sanções em mais de uma conta: <font color="#FF0000">*</font></strong></td>
					<td colspan="3">
						<strong>
							<html:radio property="indicadorParcelarSancoesMaisDeUmaConta" value="1" tabindex="27"/>Sim
							<html:radio property="indicadorParcelarSancoesMaisDeUmaConta" value="2" tabindex="28"/>N&atilde;o
						</strong>
					</td>
				</tr>
			               	
				<%-- início da tabela de Quantidade Máxima de Reparcelamentos Consecutivos --%>
				<tr>
					<td>
						<p>&nbsp;</p>
					</td>
				</tr>
				
				<tr bgcolor="#49A3FC">
					<td colspan="2" align="center"><strong>Reparcelamentos Consecutivos</strong></td>
				</tr>
									
				<tr> 
                	<td><strong> Reparcelamentos Consecutivos:<font color="#FF0000">*</font></strong></td>
                	<td>
                		<html:text property="qtdeMaximaReparcelamento" size="3" maxlength="3" tabindex="29" onkeypress="return isCampoNumerico(event);"/>
                  	</td>
              	</tr>
              	
              	<tr> 
                	<td><strong> Percentual de Entrada Sugerida:</strong></td>
                	<td>
                		<html:text property="percentualEntradaSugerida" size="6" maxlength="6" 
                		tabindex="30" style="text-align:right;" onkeyup="formataValorMonetario(this, 6);"/>
                  	</td>
              	</tr>
              	
	 			<%--  
	            <tr> 
                	<td><strong> Valor Mínimo da Prestação:<font color="#FF0000">*</font></strong></td>
                	<td>
                		<html:text property="valorMinimoPrestacao" size="16" maxlength="16" 
                		tabindex="7" onkeyup="formataValorMonetario(this, 16)"
                		style="text-align:right;"/>
                  	</td>
              	</tr>
				--%>

	 
             	<tr>
					<td>
				 	 <strong> Reparcelamentos Consecutivos Informado(s) </strong>
                   </td>
				    <td align="right">
					  <input name="Button" type="button" class="bottonRightCol" value="Adicionar" align="right" 
					  onclick="adicionarReparcelamento(document.forms[0])" />
				    </td>
             	</tr>
             	
             	
             	
             	
             	<%int cont = 0;%>
				<tr>
					<td colspan="4">
					<table width="100%" border="0" bgcolor="90c7fc">
						<logic:empty name="collectionParcelamentoQuantidadeReparcelamentoHelper" scope="session">
							<tr bgcolor="#90c7fc">
								<td align="center" width="10%"><strong>Remover</strong></td>
								<td align="center" width="20%"><strong>Reparcelamentos Consecutivos</strong></td>
								<td align="center" width="20%"><strong>Percentual de Entrada Sugerida</strong></td>
								<td align="center" width="50%"><strong>Informações do Parcelamento por Quantidade de Reparcelamentos</strong></td>
							</tr>
							<tr>
								<td align="center" width="10%" bgcolor="#ffffff">&nbsp;</td>
								<td align="center" width="20%" bgcolor="#ffffff">&nbsp;</td>
								<td align="center" width="20%" bgcolor="#ffffff">&nbsp;</td>
								<td align="center" width="50%" bgcolor="#ffffff">&nbsp;</td>
							</tr>
						</logic:empty>
						<logic:notEmpty name="collectionParcelamentoQuantidadeReparcelamentoHelper" scope="session">
							<%if (((Collection) session.getAttribute("collectionParcelamentoQuantidadeReparcelamentoHelper")).size() 
									<= ConstantesSistema.NUMERO_MAXIMO_REGISTROS_PERFIL_PARCELAMENTO) {%>
							<tr bgcolor="#90c7fc">
								<td align="center" width="10%"><strong>Remover</strong></td>
								<td align="center" width="20%"><strong>Reparcelamentos Consecutivos</strong></td>
								<td align="center" width="20%"><strong>Percentual de Entrada Sugerida</strong></td>
								<td align="center" width="50%"><strong>Informações do Parcelamento por Quantidade de Reparcelamentos</strong></td>
							</tr>
								<logic:iterate name="collectionParcelamentoQuantidadeReparcelamentoHelper" 
								id="teste" type="ParcelamentoQuantidadeReparcelamentoHelper">
								<%cont = cont + 1;
								if (cont % 2 == 0) {%>
								<tr bgcolor="#cbe5fe">
								<%} else {%>
								<tr bgcolor="#FFFFFF">
								<%}%>
								
									<td width="10%">
							            <div align="center"><font color="#333333"> <img width="14"
							             height="14" border="0"
							             src="<bean:message key="caminho.imagens"/>Error.gif"
								             onclick="javascript:document.forms[0].target='';if(confirm('Confirma remoção?')){redirecionarSubmit('removerParcelamentoQuantidadeReparcelamentoHelperActionInserir.do?qtdeMaxReparcelamento=<bean:write name="teste" property="quantidadeMaximaReparcelamento"/>');}" />
							            </font></div>
							       </td>	
							       
							        <td width="20%">
										<div align="center"><logic:notPresent name="acao" scope="session">
											<a
											href="javascript:abrirPopupComSubmitLink(document.forms[0],'','',<bean:write name="teste" 
											property="quantidadeMaximaReparcelamento" />);"
											><bean:write name="teste" property="quantidadeMaximaReparcelamento" /></a>&nbsp;
											</logic:notPresent></div>
									</td>

									<td width="20%">
										<div align="right"><bean:write name="teste" property="percentualEntradaSugerida" formatKey="money.format"/> &nbsp;</div>
									</td>

									<td width="40%">
										<div><bean:write name="teste" property="informacaoParcelamentoQtdeReparcelamento" /> &nbsp;</div>
									</td>
	
								</tr>
							</logic:iterate>
							<%} else {%>
								<tr bgcolor="#90c7fc">
									<td align="center" width="10%"><strong>Remover</strong></td>
									<td align="center" width="20%"><strong>Reparcelamentos Consecutivos</strong></td>
									<td align="center" width="20%"><strong>Percentual de Entrada Sugerida</strong></td>
									<td align="center" width="50%"><strong>Informações do Parcelamento por Quantidade de Reparcelamentos</strong></td>
								</tr>
								<tr>
									<td height="100" colspan="6">
									<div style="width: 100%; height: 100%; overflow: auto;">
									<table width="100%">
										<logic:iterate name="collectionParcelamentoQuantidadeReparcelamentoHelper" 
										id="teste" type="ParcelamentoQuantidadeReparcelamentoHelper">
											<%cont = cont + 1;
											if (cont % 2 == 0) {%>
											<tr bgcolor="#cbe5fe">
											<%} else {%>
											<tr bgcolor="#FFFFFF">
											<%}%>
												
												<td width="10%">
										            <div align="center"><font color="#333333"> <img width="14"
										             height="14" border="0"
										             src="<bean:message key="caminho.imagens"/>Error.gif"
		 								             onclick="javascript:document.forms[0].target='';if(confirm('Confirma remoção?')){redirecionarSubmit('removerParcelamentoQuantidadeReparcelamentoHelperActionInserir.do?qtdeMaxReparcelamento=<bean:write name="teste" property="quantidadeMaximaReparcelamento"/>');}" />
										            </font></div>
										       </td>	
										       
										        <td width="15%">
													<div align="center"><logic:notPresent name="acao" scope="session">
														<a
														href="javascript:abrirPopupComSubmitLink(document.forms[0],'','',<bean:write name="teste" 
														property="quantidadeMaximaReparcelamento" />);"
														><bean:write name="teste" property="quantidadeMaximaReparcelamento" /></a>&nbsp;
														</logic:notPresent></div>
												</td>
			
												<td width="15%">
													<div align="right"><bean:write name="teste" property="percentualEntradaSugerida" formatKey="money.format"/> &nbsp;</div>
												</td>
	
			
												<td width="40%">
													<div><bean:write name="teste" property="informacaoParcelamentoQtdeReparcelamento" /> &nbsp;</div>
												</td>
	
											
											</tr>
										</logic:iterate>
									</table>
									</div>
									</td>
								</tr>
							<%}%>
						</logic:notEmpty>
					</table>
					</td>
				</tr>
            			
           	<%-- final da tabela de Quantidade Máxima de Reparcelamentos Consecutivos --%>
		              	
									
				<%-- início da tabela de Descontos por Antiguidade --%>
				<tr>
						<td>
							<p>&nbsp;</p>
					</td>
				</tr>

				<tr bgcolor="#49A3FC">
					<td colspan="2" align="center"><strong>Desconto(s) por Antiguidade</strong></td>
				</tr>
				
				<tr> 
	                <td><strong>Qtde. Mínima Meses de Débito p/ Desconto:</strong></td>
                	<td>
                		<html:text property="quantidadeMinimaMesesDebito" size="4" maxlength="4" tabindex="30" onkeypress="return isCampoNumerico(event);" />
                  	</td>
              	</tr>
              	
              	<tr> 
                	<td><strong> Percentual de Desconto Sem Restabelecimento:</strong></td>
                	<td>
                		<html:text property="percentualDescontoSemRestabelecimentoAntiguidade" size="6" 
                		maxlength="6" tabindex="31" onkeyup="formataValorMonetario(this, 6)"
                		style="text-align:right;"/>
                  	</td>
              	</tr>

              	<tr> 
                	<td><strong> Percentual de Desconto Com Restabelecimento:</strong></td>
                	<td>
                		<html:text property="percentualDescontoComRestabelecimentoAntiguidade" size="6" 
                		maxlength="6" tabindex="32" onkeyup="formataValorMonetario(this, 6)"
                		style="text-align:right;"/>
                  	</td>
              	</tr>
              	
              	<tr> 
                	<td><strong> Percentual de Desconto Ativo: </strong></td>
                	<td>
                		<html:text property="percentualDescontoAtivo" size="6" 
                		maxlength="6" tabindex="33" onkeyup="formataValorMonetario(this, 6)"
                		style="text-align:right;"/>
                  	</td>
              	</tr>
              	
              	<tr> 
                	<td><strong> Motivo de Revisão: </strong></td>
                	<td>
                		<html:select property="idContaMotivoRevisao" tabindex="34">
						<html:option value="-1">&nbsp;</html:option>
						<html:options collection="collectionContaMotivoRevisao"	labelProperty="descricaoMotivoRevisaoConta" property="id" />
						</html:select>
                  	</td>
              	</tr>

              	<tr>
					<td>
					  <strong> Desconto(s) por Antiguidade Informado(s) </strong>
                    </td>
					    <td align="right">
						  <input name="Button" type="button" class="bottonRightCol" value="Adicionar" align="right" 
						  onclick="adicionarParcelamentoDescontoAntiguidade(document.forms[0])" />
					    </td>
              	</tr>
              	
              	
              	<%int cont4 = 0;%>
				<tr>
					<td colspan="4">
					<table width="100%" border="0" bgcolor="90c7fc">
						
						<logic:empty name="collectionParcelamentoDescontoAntiguidade" scope="session">
							<tr bordercolor="#000000" bgcolor="#90c7fc">
								<td rowspan="2" align="center" width="10%"><strong>Remover</strong></td>
								<td rowspan="2" width="20%" align="center"><strong>Qtde. Mínima Meses de Débito</strong></td>
								<td colspan="3 "align="center"><strong>Percentual de Desconto</strong></td>
								<td rowspan="2" "align="center"><strong>Motivo Rev.</strong></td>
							</tr>
							<tr  bgcolor="#cbe5fe">
								<td width="20%" align="center"><strong>Sem Restabelecimento</strong></td>
								<td width="20%" align="center"><strong>Com Restabelecimento</strong></td>
								<td width="15%" align="center"><strong>Ativo</strong></td>
							</tr>
							<tr>
								<td align="center" width="10%" bgcolor="#ffffff">&nbsp;</td>
								<td align="center" width="20%" bgcolor="#ffffff">&nbsp;</td>
								<td align="center" width="20%" bgcolor="#ffffff">&nbsp;</td>
								<td align="center" width="20%" bgcolor="#ffffff">&nbsp;</td>
								<td align="center" width="15%" bgcolor="#ffffff">&nbsp;</td>
								<td align="center" width="15%" bgcolor="#ffffff">&nbsp;</td>
							</tr>
						</logic:empty>
						
						
						<logic:notEmpty name="collectionParcelamentoDescontoAntiguidade" scope="session">
						
							<%if (((Collection) session.getAttribute("collectionParcelamentoDescontoAntiguidade")).size() 
									<= ConstantesSistema.NUMERO_MAXIMO_REGISTROS_PERFIL_PARCELAMENTO) {%>
								<tr bordercolor="#000000" bgcolor="#90c7fc">
									<td rowspan="2" align="center" width="10%"><strong>Remover</strong></td>
									<td rowspan="2" width="20%" align="center"><strong>Qtde. Mínima Meses de Débito</strong></td>
									<td colspan="3"align="center"><strong>Percentual de Desconto</strong></td>
									<td rowspan="2" "align="center"><strong>Motivo Rev.</strong></td>
								</tr>
								<tr  bgcolor="#cbe5fe">
									<td width="20%" align="center"><strong>Sem Restabelecimento</strong></td>
									<td width="20%" align="center"><strong>Com Restabelecimento</strong></td>
									<td width="15%" align="center"><strong>Ativo</strong></td>
								</tr>
								
								<logic:iterate name="collectionParcelamentoDescontoAntiguidade" 
									id="parcelamentoDescontoAntiguidade" type="ParcelamentoDescontoAntiguidade">
									<%cont4 = cont4 + 1;
									if (cont4 % 2 == 0) {%>
									<tr bgcolor="#cbe5fe">
									<%} else {%>
									<tr bgcolor="#FFFFFF">
									<%}%>
									
										<td width="10%">
								            <div align="center"><font color="#333333"> <img width="14"
								             height="14" border="0"
								             src="<bean:message key="caminho.imagens"/>Error.gif"
									             onclick="javascript:document.forms[0].target='';if(confirm('Confirma remoção?')){redirecionarSubmit('removerParcelamentoDescontoAntiguidadeActionInserir.do?quantidadeMinimaMesesDeb=<bean:write name="parcelamentoDescontoAntiguidade" property="quantidadeMinimaMesesDebito"/>');}" />
								            </font></div>
								       </td>	
								       
								       <td width="20%" align="center">
											<div>${parcelamentoDescontoAntiguidade.quantidadeMinimaMesesDebito} &nbsp;</div>
										</td>
								       
										<td width="20%" align="center">
											<input type="text" style="text-align: right;font-size: xx-small;" 
											size="6" maxlength="6" align="center"
											name="vlSemRestAntiguidade<bean:write name="parcelamentoDescontoAntiguidade" property="ultimaAlteracao.time"/>" 
											value="<%="" + Util.formatarMoedaReal(parcelamentoDescontoAntiguidade.getPercentualDescontoSemRestabelecimento())%>" 
											onkeyup="formataValorMonetario(this, 16)" 
											style="text-align:right;"
											/>
										</td>
	
										<td width="20%" align="center">
											<input type="text" style="text-align: right;font-size: xx-small;" 
											size="6" maxlength="6" align="center"
											name="vlComRestAntiguidade<bean:write name="parcelamentoDescontoAntiguidade" property="ultimaAlteracao.time"/>" 
											value="<%="" + Util.formatarMoedaReal(parcelamentoDescontoAntiguidade.getPercentualDescontoComRestabelecimento())%>" 
											onkeyup="formataValorMonetario(this, 16)"
											style="text-align:right;"
										/>
										
										<td width="15%" align="center">
											<input type="text" style="text-align: right;font-size: xx-small;" 
											size="6" maxlength="6" align="center"
											name="vlDescontoAtivo<bean:write name="parcelamentoDescontoAntiguidade" property="ultimaAlteracao.time"/>" 
											value="<%="" + Util.formatarMoedaReal(parcelamentoDescontoAntiguidade.getPercentualDescontoAtivo())%>" 
											onkeyup="formataValorMonetario(this, 16)"
											style="text-align:right;"
											/>	
											
										</td>
										
										<td width="15%" align="center">
											<div>${parcelamentoDescontoAntiguidade.contaMotivoRevisao.descricaoMotivoRevisaoConta} &nbsp;</div>
										</td>
									</tr>
								</logic:iterate>
							
							<%} else {%>
							
								<tr bordercolor="#000000" bgcolor="#90c7fc">
									<td rowspan="2" align="center" width="10%"><strong>Remover</strong></td>
									<td rowspan="2" width="20%" align="center"><strong>Qtde. Mínima Meses de Débito</strong></td>
									<td colspan="3"align="center"><strong>Percentual de Desconto</strong></td>
									<td rowspan="2" "align="center"><strong>Motivo Rev.</strong></td>
								</tr>
								<tr  bgcolor="#cbe5fe">
									<td width="20%" align="center"><strong>Sem Restabelecimento</strong></td>
									<td width="20%" align="center"><strong>Com Restabelecimento</strong></td>
									<td width="15%" align="center"><strong>Ativo</strong></td>
								</tr>
								<tr>
									<td height="100" colspan="6">
									<div style="width: 100%; height: 100%; overflow: auto;">
									<table width="100%">
										<logic:iterate name="collectionParcelamentoDescontoAntiguidade" 
	   								     id="parcelamentoDescontoAntiguidade" type="ParcelamentoDescontoAntiguidade">
											<%cont4 = cont4 + 1;
											if (cont4 % 2 == 0) {%>
											<tr bgcolor="#cbe5fe">
											<%} else {%>
											<tr bgcolor="#FFFFFF">
											<%}%>
												
												<td width="10%">
										            <div align="center"><font color="#333333"> <img width="14"
										             height="14" border="0"
										             src="<bean:message key="caminho.imagens"/>Error.gif"
		 								             onclick="javascript:document.forms[0].target='';if(confirm('Confirma remoção?')){redirecionarSubmit('removerParcelamentoDescontoAntiguidadeActionInserir.do?quantidadeMinimaMesesDeb=<bean:write name="parcelamentoDescontoAntiguidade" property="quantidadeMinimaMesesDebito"/>');}" />
										            </font></div>
										       </td>	
										       
										       <td width="20%" align="center">
													<div>${parcelamentoDescontoAntiguidade.quantidadeMinimaMesesDebito} &nbsp;</div>
												</td>
										       
												<td width="20%" align="center">
													<input type="text" style="text-align: right;font-size: xx-small;" 
													size="6" maxlength="6" align="center"
													name="vlSemRestAntiguidade<bean:write name="parcelamentoDescontoAntiguidade" property="ultimaAlteracao.time"/>" 
													value="<%="" + Util.formatarMoedaReal(parcelamentoDescontoAntiguidade.getPercentualDescontoSemRestabelecimento())%>" 
													onkeyup="formataValorMonetario(this, 16)" 
													style="text-align:right;"
													/>
												</td>
		
												<td width="20%" align="center">
													<input type="text" style="text-align: right;font-size: xx-small;" 
													size="6" maxlength="6" align="center"
													name="vlComRestAntiguidade<bean:write name="parcelamentoDescontoAntiguidade" property="ultimaAlteracao.time"/>" 
													value="<%="" + Util.formatarMoedaReal(parcelamentoDescontoAntiguidade.getPercentualDescontoComRestabelecimento())%>" 
													onkeyup="formataValorMonetario(this, 16)"
													style="text-align:right;"
												/>
												
												<td width="14%" align="center">
													<input type="text" style="text-align: right;font-size: xx-small;" 
													size="6" maxlength="6" align="center"
													name="vlDescontoAtivo<bean:write name="parcelamentoDescontoAntiguidade" property="ultimaAlteracao.time"/>" 
													value="<%="" + Util.formatarMoedaReal(parcelamentoDescontoAntiguidade.getPercentualDescontoAtivo())%>" 
													onkeyup="formataValorMonetario(this, 16)"
													style="text-align:right;"
													/>	
													
												</td>
												
												<td width="15%" align="center">
													<div>${parcelamentoDescontoAntiguidade.contaMotivoRevisao.descricaoMotivoRevisaoConta} &nbsp;</div>
												</td>
											</tr>
										</logic:iterate>
									</table>
									</div>
									</td>
								</tr>
								<%}%>
						</logic:notEmpty>
					</table>
					</td>
				</tr>
              	
              	<%-- final da tabela de Descontos por Antiguidade --%>	
              	
								
				<%-- início da tabela de Descontos por Inatividade --%>
              	<tr>
					<td>
						<p>&nbsp;</p>
					</td>
				</tr>
				
				<tr bgcolor="#49A3FC">
					<td colspan="2" align="center"><strong>Desconto(s) por Inatividade</strong></td>
				</tr>

				<tr> 
	              	<td><strong> Qtde. Máxima Meses de Inatividade da Lig. de Água:</strong></td>
	              	<td>
	              		<html:text property="quantidadeMaximaMesesInatividade" size="4" maxlength="4" 
	              		tabindex="35" onkeypress="return isCampoNumerico(event);"/>
	                	</td>
	            	</tr>
	            	
	            	<tr> 
	              	<td><strong> Percentual de Desconto Sem Restabelecimento:</strong></td>
	              	<td>
	              		<html:text property="percentualDescontoSemRestabelecimentoInatividade" size="6" 
	              		maxlength="6" tabindex="36" onkeyup="formataValorMonetario(this, 6)"
	              		style="text-align:right;"/>
	                	</td>
	            	</tr>
	
	            	<tr> 
	              	<td><strong> Percentual de Desconto Com Restabelecimento:</strong></td>
	              	<td>
	              		<html:text property="percentualDescontoComRestabelecimentoInatividade" size="6" 
	              		maxlength="6" tabindex="37" onkeyup="formataValorMonetario(this, 6)"
	              		style="text-align:right;"/>
	                	</td>
	            	</tr>
	
	            	<tr>
				<td>
				  <strong> Desconto(s) por Inatividade Informado(s) </strong>
	                  </td>
				    <td align="right">
					  <input name="Button" type="button" class="bottonRightCol" value="Adicionar" align="right" 
					  onclick="adicionarParcelamentoDescontoInatividade(document.forms[0])"  />
				    </td>
	           </tr>
	            	
	            	
	            	
	            	
	           	<%int cont3 = 0;%>
				<tr>
					<td colspan="4">
					<table width="100%" border="0" bgcolor="90c7fc">
						<logic:empty name="collectionParcelamentoDescontoInatividade" scope="session">
							<tr bgcolor="#90c7fc">
								<td rowspan="2" align="center" width="10%"><strong>Remover</strong></td>
								<td rowspan="2" width="40%" align="center"><strong> Qtde. Máxima Meses da Lig. de Água</strong></td>
								<td colspan="2" align="center"><strong>Percentual de Desconto</strong></td>
							</tr>
							<tr bgcolor="#cbe5fe">
								<td width="25%" align="center"><strong>Sem Restabelecimento</strong></td>
								<td width="25%" align="center"><strong>Com Restabelecimento</strong></td>
							</tr>
							<tr>
								<td align="center" width="10%" bgcolor="#ffffff">&nbsp;</td>
								<td align="center" width="40%" bgcolor="#ffffff">&nbsp;</td>
								<td align="center" width="25%" bgcolor="#ffffff">&nbsp;</td>
								<td align="center" width="25%" bgcolor="#ffffff">&nbsp;</td>
							</tr>
						</logic:empty>
						
						<logic:notEmpty name="collectionParcelamentoDescontoInatividade" scope="session">
							
							<%if (((Collection) session.getAttribute("collectionParcelamentoDescontoInatividade")).size() 
									<= ConstantesSistema.NUMERO_MAXIMO_REGISTROS_PERFIL_PARCELAMENTO) {%>
								<tr bgcolor="#90c7fc">
									<td rowspan="2" align="center" width="10%"><strong>Remover</strong></td>
									<td rowspan="2" width="40%" align="center"><strong>Qtde. Máxima Meses da Lig. de Água</strong></td>
									<td colspan="2" align="center"><strong>Percentual de Desconto</strong></td>
								</tr>
								<tr bgcolor="#cbe5fe">
									<td width="25%" align="center"><strong>Sem Restabelecimento</strong></td>
									<td width="25%" align="center"><strong>Com Restabelecimento</strong></td>
								</tr>
							
						
								<logic:iterate name="collectionParcelamentoDescontoInatividade" 
									id="parcelamentoDescontoInatividade"
									type="ParcelamentoDescontoInatividade">
									<%cont3 = cont3 + 1;
									if (cont3 % 2 == 0) {%>
									<tr bgcolor="#cbe5fe">
									<%} else {%>
									<tr bgcolor="#FFFFFF">
									<%}%>
									
										<td width="10%">
								            <div align="center"><font color="#333333"> <img width="14"
								             height="14" border="0"
								             src="<bean:message key="caminho.imagens"/>Error.gif"
									             onclick="javascript:document.forms[0].target='';if(confirm('Confirma remoção?')){redirecionarSubmit('removerParcelamentoDescontoInatividadeActionInserir.do?quantidadeMaximaMesesInat=<bean:write name="parcelamentoDescontoInatividade" property="quantidadeMaximaMesesInatividade"/>');}" />
								            </font></div>
								       </td>	
								       <td width="40%" align="center">
											<div>${parcelamentoDescontoInatividade.quantidadeMaximaMesesInatividade} &nbsp;</div>
										</td>
										<td width="25%" align="center">
											<input type="text" style="text-align: right;font-size: xx-small;" 
											size="6" maxlength="6" align="center"
											name="vlSemRestInatividade<bean:write name="parcelamentoDescontoInatividade" property="ultimaAlteracao.time"/>" 
											value="<%="" + Util.formatarMoedaReal(parcelamentoDescontoInatividade.getPercentualDescontoSemRestabelecimento())%>" 
											onkeyup="formataValorMonetario(this, 6)" 
											style="text-align:right;"
											/>
										</td>
										<td width="25%" align="center">
											<input type="text" style="text-align: right;font-size: xx-small;" 
											size="6" maxlength="6" align="center"
											name="vlComRestInatividade<bean:write name="parcelamentoDescontoInatividade" property="ultimaAlteracao.time"/>" 
											value="<%="" + Util.formatarMoedaReal(parcelamentoDescontoInatividade.getPercentualDescontoComRestabelecimento())%>" 
											onkeyup="formataValorMonetario(this, 6)" 
											style="text-align:right;"
											/>
										</td>
									</tr>
								</logic:iterate>
								
							<%} else {%>
							
								<tr bgcolor="#90c7fc">
									<td rowspan="2" align="center" width="10%"><strong>Remover</strong></td>
									<td rowspan="2" width="39%" align="center"><strong> Qtde. Máxima Meses da Lig. de Água</strong></td>
									<td colspan="2" align="center"><strong>Percentual de Desconto</strong></td>
								</tr>
								<tr bgcolor="#cbe5fe">
									<td width="24%" align="center"><strong>Sem Restabelecimento</strong></td>
									<td width="27%" align="center"><strong>Com Restabelecimento</strong></td>
								</tr>
						
								<tr>
									<td height="100" colspan="6">
									<div style="width: 100%; height: 100%; overflow: auto;">
									<table width="100%">
										<logic:iterate name="collectionParcelamentoDescontoInatividade" 
											id="parcelamentoDescontoInatividade"
											type="ParcelamentoDescontoInatividade">
											<%cont3 = cont3 + 1;
											if (cont3 % 2 == 0) {%>
											<tr bgcolor="#cbe5fe">
											<%} else {%>
											<tr bgcolor="#FFFFFF">
											<%}%>
												<td width="10%">
										            <div align="center"><font color="#333333"> <img width="14"
										             height="14" border="0"
										             src="<bean:message key="caminho.imagens"/>Error.gif"
											             onclick="javascript:document.forms[0].target='';if(confirm('Confirma remoção?')){redirecionarSubmit('removerParcelamentoDescontoInatividadeActionInserir.do?quantidadeMaximaMesesInat=<bean:write name="parcelamentoDescontoInatividade" property="quantidadeMaximaMesesInatividade"/>');}" />
										            </font></div>
										       </td>	
										       <td width="40%" align="center">
													<div>${parcelamentoDescontoInatividade.quantidadeMaximaMesesInatividade} &nbsp;</div>
												</td>
												<td width="25%" align="center">
													<input type="text" style="text-align: right;font-size: xx-small;" 
													size="6" maxlength="6" align="center"
													name="vlSemRestInatividade<bean:write name="parcelamentoDescontoInatividade" property="ultimaAlteracao.time"/>" 
													value="<%="" + Util.formatarMoedaReal(parcelamentoDescontoInatividade.getPercentualDescontoSemRestabelecimento())%>" 
													onkeyup="formataValorMonetario(this, 6)" 
													style="text-align:right;"
													/>
												</td>
												<td width="25%" align="center">
													<input type="text" style="text-align: right;font-size: xx-small;" 
													size="6" maxlength="6" align="center"
													name="vlComRestInatividade<bean:write name="parcelamentoDescontoInatividade" property="ultimaAlteracao.time"/>" 
													value="<%="" + Util.formatarMoedaReal(parcelamentoDescontoInatividade.getPercentualDescontoComRestabelecimento())%>" 
													onkeyup="formataValorMonetario(this, 6)" 
													style="text-align:right;"
													/>
												</td>
											</tr>
										</logic:iterate>
									</table>
									</div>
									</td>
								</tr>
							<%}%>
						</logic:notEmpty>
					</table>
					</td>
				</tr>
	          		
	           	<%-- final da tabela de Descontos por Inatividade --%>			
              				
              						
				<%-- início da tabela de Descontos por Inatividade à Vista--%>
              	<tr>
					<td>
						<p>&nbsp;</p>
					</td>
				</tr>
				
				<tr bgcolor="#49A3FC">
					<td colspan="2" align="center"><strong>Desconto(s) por Inatividade à Vista </strong></td>
				</tr>

				<tr> 
	              	<td><strong> Qtde. Máxima Meses de Inatividade da Lig. de Água:</strong></td>
	              	<td>
	              		<html:text property="quantidadeMaximaMesesInatividadeAVista" size="4" maxlength="4" 
	              		tabindex="38" onkeypress="return isCampoNumerico(event);"/>
	                	</td>
	            	</tr>
	            	
	            	<tr> 
	              	<td><strong> Percentual de Desconto Sem Restabelecimento:</strong></td>
	              	<td>
	              		<html:text property="percentualDescontoSemRestabelecimentoInatividadeAVista" size="6" 
	              		maxlength="6" tabindex="39" onkeyup="formataValorMonetario(this, 6)"
	              		style="text-align:right;"/>
	                	</td>
	            	</tr>
	
	            	<tr> 
	              	<td><strong> Percentual de Desconto Com Restabelecimento:</strong></td>
	              	<td>
	              		<html:text property="percentualDescontoComRestabelecimentoInatividadeAVista" size="6" 
	              		maxlength="6" tabindex="40" onkeyup="formataValorMonetario(this, 6)"
	              		style="text-align:right;"/>
	                	</td>
	            	</tr>
	
	            	<tr>
				<td>
				  <strong> Desconto(s) por Inatividade Informado(s) </strong>
	                  </td>
				    <td align="right">
					  <input name="Button" type="button" class="bottonRightCol" value="Adicionar" align="right" 
					  onclick="adicionarParcelamentoDescontoInatividadeAVista(document.forms[0])"  />
				    </td>
	           </tr>
	            	
	            	
	            	
	            	
	           	<%int cont5 = 0;%>
				<tr>
					<td colspan="4">
					<table width="100%" border="0" bgcolor="90c7fc">
						<logic:empty name="collectionParcelamentoDescontoInatividadeAVista" scope="session">
							<tr bgcolor="#90c7fc">
								<td rowspan="2" align="center" width="10%"><strong>Remover</strong></td>
								<td rowspan="2" width="40%" align="center"><strong> Qtde. Máxima Meses da Lig. de Água</strong></td>
								<td colspan="2" align="center"><strong>Percentual de Desconto</strong></td>
							</tr>
							<tr bgcolor="#cbe5fe">
								<td width="25%" align="center"><strong>Sem Restabelecimento</strong></td>
								<td width="25%" align="center"><strong>Com Restabelecimento</strong></td>
							</tr>
							<tr>
								<td align="center" width="10%" bgcolor="#ffffff">&nbsp;</td>
								<td align="center" width="40%" bgcolor="#ffffff">&nbsp;</td>
								<td align="center" width="25%" bgcolor="#ffffff">&nbsp;</td>
								<td align="center" width="25%" bgcolor="#ffffff">&nbsp;</td>
							</tr>
						</logic:empty>
						
						<logic:notEmpty name="collectionParcelamentoDescontoInatividadeAVista" scope="session">
							
							<%if (((Collection) session.getAttribute("collectionParcelamentoDescontoInatividadeAVista")).size() 
									<= ConstantesSistema.NUMERO_MAXIMO_REGISTROS_PERFIL_PARCELAMENTO) {%>
								<tr bgcolor="#90c7fc">
									<td rowspan="2" align="center" width="10%"><strong>Remover</strong></td>
									<td rowspan="2" width="40%" align="center"><strong>Qtde. Máxima Meses da Lig. de Água</strong></td>
									<td colspan="2" align="center"><strong>Percentual de Desconto</strong></td>
								</tr>
								<tr bgcolor="#cbe5fe">
									<td width="25%" align="center"><strong>Sem Restabelecimento</strong></td>
									<td width="25%" align="center"><strong>Com Restabelecimento</strong></td>
								</tr>
							
						
								<logic:iterate name="collectionParcelamentoDescontoInatividadeAVista" 
									id="parcelamentoDescontoInatividade"
									type="ParcDesctoInativVista">
									<%cont5 = cont5 + 1;
									if (cont5 % 2 == 0) {%>
									<tr bgcolor="#cbe5fe">
									<%} else {%>
									<tr bgcolor="#FFFFFF">
									<%}%>
									
										<td width="10%">
								            <div align="center"><font color="#333333"> <img width="14"
								             height="14" border="0"
								             src="<bean:message key="caminho.imagens"/>Error.gif"
									             onclick="javascript:document.forms[0].target='';if(confirm('Confirma remoção?')){redirecionarSubmit('removerParcelamentoDescontoInatividadeAVistaActionInserir.do?quantidadeMaximaMesesInat=<bean:write name="parcelamentoDescontoInatividade" property="quantidadeMaximaMesesInatividade"/>');}" />
								            </font></div>
								       </td>	
								       <td width="40%" align="center">
											<div>${parcelamentoDescontoInatividade.quantidadeMaximaMesesInatividade} &nbsp;</div>
										</td>
										<td width="25%" align="center">
											<input type="text" style="text-align: right;font-size: xx-small;" 
											size="6" maxlength="6" align="center"
											name="vlSemRestInatividadeAVista<bean:write name="parcelamentoDescontoInatividade" property="ultimaAlteracao.time"/>" 
											value="<%="" + Util.formatarMoedaReal(parcelamentoDescontoInatividade.getPercentualDescontoSemRestabelecimento())%>" 
											onkeyup="formataValorMonetario(this, 6)" 
											style="text-align:right;"
											/>
										</td>
										<td width="25%" align="center">
											<input type="text" style="text-align: right;font-size: xx-small;" 
											size="6" maxlength="6" align="center"
											name="vlComRestInatividadeAVista<bean:write name="parcelamentoDescontoInatividade" property="ultimaAlteracao.time"/>" 
											value="<%="" + Util.formatarMoedaReal(parcelamentoDescontoInatividade.getPercentualDescontoComRestabelecimento())%>" 
											onkeyup="formataValorMonetario(this, 6)" 
											style="text-align:right;"
											/>
										</td>
									</tr>
								</logic:iterate>
								
							<%} else {%>
							
								<tr bgcolor="#90c7fc">
									<td rowspan="2" align="center" width="10%"><strong>Remover</strong></td>
									<td rowspan="2" width="39%" align="center"><strong> Qtde. Máxima Meses da Lig. de Água</strong></td>
									<td colspan="2" align="center"><strong>Percentual de Desconto</strong></td>
								</tr>
								<tr bgcolor="#cbe5fe">
									<td width="24%" align="center"><strong>Sem Restabelecimento</strong></td>
									<td width="27%" align="center"><strong>Com Restabelecimento</strong></td>
								</tr>
						
								<tr>
									<td height="100" colspan="6">
									<div style="width: 100%; height: 100%; overflow: auto;">
									<table width="100%">
										<logic:iterate name="collectionParcelamentoDescontoInatividadeAVista" 
											id="parcelamentoDescontoInatividade"
											type="ParcDesctoInativVista">
											<%cont5 = cont5 + 1;
											if (cont5 % 2 == 0) {%>
											<tr bgcolor="#cbe5fe">
											<%} else {%>
											<tr bgcolor="#FFFFFF">
											<%}%>
												<td width="10%">
										            <div align="center"><font color="#333333"> <img width="14"
										             height="14" border="0"
										             src="<bean:message key="caminho.imagens"/>Error.gif"
											             onclick="javascript:document.forms[0].target='';if(confirm('Confirma remoção?')){redirecionarSubmit('removerParcelamentoDescontoInatividadeAVistaActionInserir.do?quantidadeMaximaMesesInat=<bean:write name="parcelamentoDescontoInatividade" property="quantidadeMaximaMesesInatividade"/>');}" />
										            </font></div>
										       </td>	
										       <td width="40%" align="center">
													<div>${parcelamentoDescontoInatividade.quantidadeMaximaMesesInatividade} &nbsp;</div>
												</td>
												<td width="25%" align="center">
													<input type="text" style="text-align: right;font-size: xx-small;" 
													size="6" maxlength="6" align="center"
													name="vlSemRestInatividadeAVista<bean:write name="parcelamentoDescontoInatividade" property="ultimaAlteracao.time"/>" 
													value="<%="" + Util.formatarMoedaReal(parcelamentoDescontoInatividade.getPercentualDescontoSemRestabelecimento())%>" 
													onkeyup="formataValorMonetario(this, 6)" 
													style="text-align:right;"
													/>
												</td>
												<td width="25%" align="center">
													<input type="text" style="text-align: right;font-size: xx-small;" 
													size="6" maxlength="6" align="center"
													name="vlComRestInatividadeAVista<bean:write name="parcelamentoDescontoInatividade" property="ultimaAlteracao.time"/>" 
													value="<%="" + Util.formatarMoedaReal(parcelamentoDescontoInatividade.getPercentualDescontoComRestabelecimento())%>" 
													onkeyup="formataValorMonetario(this, 6)" 
													style="text-align:right;"
													/>
												</td>
											</tr>
										</logic:iterate>
									</table>
									</div>
									</td>
								</tr>
							<%}%>
						</logic:notEmpty>
					</table>
					</td>
				</tr>
	          		
	           	<%-- final da tabela de Descontos por Inatividade à Vista--%>			
              				
              							
              									
	
              <tr> 
                <td><strong> <font color="#FF0000"></font></strong></td>
                <td align="right"> <div align="left"><strong><font color="#FF0000">*</font></strong> 
                    Campos obrigat&oacute;rios</div></td>
              </tr>                 		
			


				<tr>
					<td colspan="2">
						<table width="100%">
						<tr>
							<td>
								<input name="Button" type="button" class="bottonRightCol" value="Desfazer" align="left" onclick="window.location.href='<html:rewrite page="/exibirInserirPerfilParcelamentoAction.do?desfazer=S"/>'" >
		                    	<input name="Button" type="button" class="bottonRightCol" value="Cancelar" align="left" onClick="javascript:window.location.href='/gsan/telaPrincipal.do'">				
		                    	</td>
		                    <td align="right">
		                      <gsan:controleAcessoBotao name="Button" value="Inserir" onclick="validarForm(document.ParcelamentoPerfilActionForm)" url="inserirPerfilParcelamentoAction.do" align="right"/>
							  <%-- <input name="Button" type="button" class="bottonRightCol" value="Inserir" align="right" onClick="validarForm(document.ParcelamentoPerfilActionForm)" >--%>
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
<%@ include file="/jsp/util/rodape.jsp"%>
</html:form>
</body>
</html:html>
