<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">

<%@ page import="gcom.util.ConstantesSistema"%>
<%@ include file="/jsp/util/telaespera.jsp"%>

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>

<html:javascript staticJavascript="false"  formName="GerarRelatorioImoveisFaturasAtrasoActionForm"/>

<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js" ></script>

<script language="JavaScript">
function habilitarPesquisaCliente() {
	var form = document.forms[0];
	if (!form.codigoCliente.disabled && !form.codigoCliente.readOnly) {
		chamarPopup('exibirPesquisarClienteAction.do', 'cliente', null, null, 275, 480, '',form.codigoCliente.value);
	}	
}

function habilitarPesquisaClienteSuperior() {
	var form = document.forms[0];
	if (!form.codigoClienteSuperior.disabled && !form.codigoClienteSuperior.readOnly ) {
		chamarPopup('exibirPesquisarResponsavelSuperiorAction.do?pesquisaSuperior=sim', 'clienteSuperior', null, null, 275, 480, '',form.codigoClienteSuperior.value);
	}	
}

function desabilitarClienteOuClienteSuperior(){
	var form = document.forms[0];

	for(var i = 0; i<form.criterioFiltro.length; i++){
		if(form.criterioFiltro[i].checked){
			if(form.criterioFiltro[i].value == "2"){
				if(form.codigoClienteSuperior.value.length > 0) {
					desabilitarClienteResponsavelTipoRelacao();
					
			    } else if(form.codigoCliente.value.length > 0) {
			    	desabilitarClienteSuperior();
				}
				break;
			}
		}
	}	
}

function desabilitarClienteResponsavelTipoRelacao(){
	var form = document.forms[0];

	form.nomeCliente.value = "";
	form.codigoCliente.value = "";
	simularCampoTextoDisabled(form.codigoCliente);

	form.tipoRelacao.value = <%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>;
	form.tipoRelacao.disabled = true;
	
	form.responsavel[1].checked = true;

	form.responsavel[0].disabled = true;
	form.responsavel[1].disabled = true;
	form.responsavel[2].disabled = true;
}

function desabilitarClienteSuperior(){
	var form = document.forms[0];

    form.nomeClienteSuperior.value = "";

	form.codigoClienteSuperior.value = "";
	simularCampoTextoDisabled(form.codigoClienteSuperior);
}

function validaEnterCliente(tecla, caminhoActionReload, nomeCampo,msg) {
	var form = document.forms[0];
	
	validaEnterComMensagem(tecla, caminhoActionReload, nomeCampo, msg);
	
	if(form.codigoClienteSuperior.value.length > 0) {
		desabilitarClienteResponsavelTipoRelacao();
		
    } else if(form.codigoCliente.value.length > 0) {
    	desabilitarClienteSuperior();
    	
		form.tipoRelacao.value = "<%=ConstantesSistema.NUMERO_NAO_INFORMADO%>";
		form.tipoRelacao.disabled = false;

		form.responsavel[1].checked = true;

    	form.responsavel[0].disabled = false;
    	form.responsavel[1].disabled = false;
    	form.responsavel[2].disabled = false;

	}else if(!form.codigoClienteSuperior.readOnly || !form.codigoCliente.readOnly){
		habilitarCamposCliente();
	}
}

function limparCamposClienteAtravesBorracha(tipo){
    var form = document.forms[0];

    if(tipo == 'cliente' && !form.codigoCliente.readOnly && !form.codigoCliente.disabled){
    	form.codigoCliente.value = "";
    	form.nomeCliente.value = "";
		habilitarCamposCliente();
	}
	if(tipo == 'clienteSuperior' && !form.codigoClienteSuperior.readOnly && !form.codigoClienteSuperior.disabled){
    	form.codigoClienteSuperior.value = "";
    	form.nomeClienteSuperior.value = "";    	
		habilitarCamposCliente();
	}
}

	function desabilitarCamposDeAcordoCriterioFiltro(isExecutarReload){
		var form = document.forms[0];
		
		for(var i = 0; i<form.criterioFiltro.length; i++){
			if(form.criterioFiltro[i].checked){
				if(form.criterioFiltro[i].value == "1"){
					desabilitarCamposCliente(isExecutarReload);
				}else if(form.criterioFiltro[i].value == "2"){
					desabilitarCamposLocalizacaoGeografica(isExecutarReload);
				}
				break;
			}
		}
	}

	function habilitarCamposCliente(){
		var form = document.forms[0];

		reverterSimularCampoTextoDisabled(form.codigoClienteSuperior);
		reverterSimularCampoTextoDisabled(form.codigoCliente);

		if(form.codigoCliente.value.length <= 0){
			form.tipoRelacao.value = "<%=ConstantesSistema.NUMERO_NAO_INFORMADO%>";
			form.tipoRelacao.disabled = true;

			form.responsavel[1].checked = true;

	    	form.responsavel[0].disabled = true;
	    	form.responsavel[1].disabled = true;
	    	form.responsavel[2].disabled = true;
		}
	}
		
	function desabilitarCamposCliente(isExecutarReload){
		limparCamposCliente();

		if(isExecutarReload){
			reloadForm();
		}

		desabilitarClienteSuperior();
		desabilitarClienteResponsavelTipoRelacao();
		habilitarCamposLocalizacaoGeografica();		
	}

	function habilitarCamposLocalizacaoGeografica(){
		
		var form = document.forms[0];
		
		form.localidadeInicial.disabled = false;
		form.nomeLocalidadeInicial.disabled = false;
		form.localidadeFinal.disabled = false;
		form.nomeLocalidadeFinal.disabled = false;
		
		form.setorComercialInicial.disabled = false;
		form.nomeSetorComercialInicial.disabled = false;
		form.setorComercialFinal.disabled = false;
		form.nomeSetorComercialFinal.disabled = false;

		form.rotaInicial.disabled = false;
		form.rotaFinal.disabled = false;

		form.sequencialRotaInicial.disabled = false;
		form.sequencialRotaFinal.disabled = false;
		
  		form.gerenciaRegional.disabled = false;
  		form.unidadeNegocio.disabled = false;

  		form.gerenciaRegional.focus();
	}
	
	function desabilitarCamposLocalizacaoGeografica(isExecutarReload){

		limparCamposLocalizacaGeografica();
		if(isExecutarReload){
			reloadForm();
		}
		
		var form = document.forms[0];
		
		form.localidadeInicial.disabled = true;
		form.nomeLocalidadeInicial.disabled = true;
		form.localidadeFinal.disabled = true;
		form.nomeLocalidadeFinal.disabled = true;
		
		form.setorComercialInicial.disabled = true;
		form.nomeSetorComercialInicial.disabled = true;
		form.setorComercialFinal.disabled = true;
		form.nomeSetorComercialFinal.disabled = true;

		form.rotaInicial.disabled = true;
		form.rotaFinal.disabled = true;

		form.sequencialRotaInicial.disabled = true;
		form.sequencialRotaFinal.disabled = true;
		
  		form.gerenciaRegional.disabled = true;
  		form.unidadeNegocio.disabled = true;

  		habilitarCamposCliente();
		form.codigoClienteSuperior.focus();
  		
	}

	function validarForm(){
		
		var form = document.forms[0];
		if(validateGerarRelatorioImoveisFaturasAtrasoActionForm(form) && 
				validarCliente() && validarCampos() && validarRota() /*&& validarLocalizacaoGeografica()*/){

			toggleBox('divAux',1);			
			toggleBox('demodiv',1);
		}
	}

	function validarCliente(){		
		var form = document.forms[0];

		if(form.criterioFiltro[1].checked){
			var clienteSuperior = form.codigoClienteSuperior.value;
			var cliente = form.codigoCliente.value;

			if(clienteSuperior == '' && cliente == ''){	
				alert('Informe o Cliente.');
				return false;
			}
		}

		return true;
	}
	
	function validarLocalizacaoGeografica(){		
		var form = document.forms[0];

		if(form.criterioFiltro[0].checked){
			var localidadeInicial = form.localidadeInicial.value;

			if(localidadeInicial == '' ){	
				alert('Informe a Localidade.');
				return false;
			}
		}

		return true;
	}
	
	function validarCampos(){
		
		var form = document.forms[0];
		
		msg = verificarAtributosInicialFinal(form.localidadeInicial,form.localidadeFinal,"Localidade");
		if( msg != ""){
			alert(msg);
			return false;
		}else{
			msg = verificarAtributosInicialFinal(form.setorComercialInicial,form.setorComercialFinal,"Setor Comercial");
			if( msg != ""){
				alert(msg);
				return false;
			}else{
				msg = verificarAtributosInicialFinal(form.rotaInicial,form.rotaFinal,"Rota");
				if( msg != ""){
					alert(msg);
					return false;
				}else{
					msg = verificarAtributosInicialFinal(form.sequencialRotaInicial,form.sequencialRotaFinal,"Sequencial da Rota");
					if( msg != ""){
						alert(msg);
						return false;
					}else{
						msg = verificarAtributosInicialFinal(form.quantidadeFaturasAtrasoInicial,form.quantidadeFaturasAtrasoFinal,"Quantidade de Faturas");
						if( msg != ""){
							alert(msg);
							return false;
						}else{
							msg = verificarReferenciaInicialFinal(form.referenciaFaturasAtrasoInicial,form.referenciaFaturasAtrasoFinal);
							if( msg != ""){
								alert(msg);
								return false;
							}else{
								msg = verificarAtributosInicialFinal(form.valorFaturasAtrasoInicial,form.valorFaturasAtrasoFinal,"Valor de Faturas");
								if( msg != ""){
									alert(msg);
									return false;
								}			
							}
						}
					}
				}
			}
		}
	
		return true;
	}

	function verificarReferenciaInicialFinal(campoInicio, campoFim){
		
		var msg = "";

		if (campoInicio.value.length > 0 && campoInicio.value.length < 1){
			msg = "Informe Referência de Faturas Final";
		} else if (campoInicio.value.length < 1 && campoInicio.value.length > 0) {
			msg = "Informe Referência de Faturas Inicial";
		} else {
			if ( comparaMesAno(campoInicio.value,'>',campoFim.value) ){
				msg = "Referência das Faturas Final deve ser maior ou igual a Referência das Faturas Inicial.";
			}
		}
		
		return msg;
	}
	
	function validarRota() {
		var form = document.forms[0];
		
		if (form.rotaInicial.value != null && form.rotaInicial.value != "") {
			if ((form.localidadeInicial.value == null || form.localidadeInicial.value == "") || (form.setorComercialInicial.value == null || form.setorComercialInicial.value == "")) {
				alert("Para informar a Rota é necessário informar a Localidade e o Setor Comercial");
				return false;
			}
		}
		
		return true;
	}

	function chamarPopupSetor(url, tipo, objeto, codigoObjeto, altura, largura, msg,campo){
		if(!campo.disabled){
	  		if (objeto == null || codigoObjeto == null){
	     		if(tipo == "" ){
	      			abrirPopup(url,altura, largura);
	     		}else{
		  			abrirPopup(url + "?" + "tipo=" + tipo, altura, largura);
		 		}
	 		}else{
				if (codigoObjeto.length < 1 || isNaN(codigoObjeto)){
					alert(msg);
				}else{
					abrirPopup(url + "?" + "tipo=" + tipo + "&" + objeto + "=" + codigoObjeto, altura, largura);
				}
			}
  		}
	}	
	
	function chamarPopup(url, tipo, objeto, codigoObjeto, altura, largura, msg,campo){
		if(!campo.disabled && !campo.radonly){
	  		if (objeto == null || codigoObjeto == null){
	     		if(tipo == "" ){
	      			abrirPopup(url,altura, largura);
	     		}else{
		  			abrirPopup(url + "?" + "tipo=" + tipo, altura, largura);
		 		}
	 		}else{
				if (codigoObjeto.length < 1 || isNaN(codigoObjeto)){
					alert(msg);
				}else{
					abrirPopup(url + "?" + "tipo=" + tipo + "&" + objeto + "=" + codigoObjeto + "&caminhoRetornoTelaPesquisa=" + tipo, altura, largura);
				}
			}
 		}
	}

  	function limparCamposLocalizacaGeografica(){

  		var form = document.forms[0];
  		
		form.localidadeInicial.value = "";
		form.nomeLocalidadeInicial.value = "";
		form.localidadeFinal.value = "";
		form.nomeLocalidadeFinal.value = "";
		
		form.setorComercialInicial.value = "";
		form.nomeSetorComercialInicial.value = "";
		form.setorComercialFinal.value = "";
		form.nomeSetorComercialFinal.value = "";

		form.rotaInicial.value = "";
		form.rotaFinal.value = "";

		form.sequencialRotaInicial.value = "";
		form.sequencialRotaFinal.value = "";
		
  		form.gerenciaRegional.value = "-1";
  		form.unidadeNegocio.value = "-1";  		
  	}

  	function limparCamposCliente(){
  		var form = document.forms[0];

		form.codigoClienteSuperior.value = "";
		form.nomeClienteSuperior.value = "";

		form.codigoCliente.value = "";
		form.nomeCliente.value = "";

		form.tipoRelacao.value = "<%=ConstantesSistema.NUMERO_NAO_INFORMADO%>";

		form.responsavel.value = "1";  		
  	}
  	
  	function limpar(){

  		limparCamposLocalizacaGeografica();
  		limparCamposCliente();
  		
  		var form = document.forms[0];
  				  		
  		form.esferaPoder.value = "-1";
  		form.categorias.value = "-1";
  		form.situacaoLigacaoAgua.value = "-1";
  		form.situacaoCobranca.value = "-1";
  		form.perfilImovel = "-1";
  		
  		form.quantidadeFaturasAtrasoInicial.value = "";
  		form.quantidadeFaturasAtrasoFinal.value = "";
		
		form.referenciaFaturasAtrasoInicial.value = "";
		form.referenciaFaturasAtrasoFinal.value = "";
  		
  		form.valorFaturasAtrasoInicial.value = "";
		form.valorFaturasAtrasoFinal.value = "";
  		

	  	form.action='exibirGerarRelatorioImoveisFaturasAtrasoAction.do?menu=sim';
		form.submit();
  	}
  	
  	function marcarHidrometro(){
  		var formulario = document.forms[0];
  		formulario.hidrometro[2].checked = true;
  	}
  	
  	function replicarLocalidade(){
		var formulario = document.forms[0]; 
		
		formulario.localidadeFinal.value = formulario.localidadeInicial.value;
		formulario.setorComercialInicial.focus;
	}
	
	function replicarSetorComercial(){
		var formulario = document.forms[0]; 
		formulario.setorComercialFinal.value = formulario.setorComercialInicial.value;
		formulario.rotaInicial.focus;
	} 
	
	function replicarRota(){
		var formulario = document.forms[0]; 
		formulario.rotaFinal.value = formulario.rotaInicial.value;
		formulario.sequencialRotaInicial.focus;
	} 
  	
	function replicarSequencialRota(){
		var formulario = document.forms[0];
		formulario.sequencialRotaFinal.value = formulario.sequencialRotaInicial.value;
	} 
	
	function limparOrigem(tipo){
		var form = document.forms[0];
		
		switch(tipo){
		
		case 1: //De localidade pra baixo

			form.nomeLocalidadeInicial.value = "";
			form.localidadeFinal.value = "";
			form.nomeLocalidadeFinal.value = "";
			form.setorComercialInicial.value = "";
		    form.setorComercialFinal.value = "";
		  	form.rotaInicial.value = "";
		   	form.sequencialRotaInicial.value = "";
		    
		case 2: //De setor para baixo

		   form.nomeSetorComercialInicial.value = "";
		   form.setorComercialFinal.value = "";
		   form.nomeSetorComercialFinal.value = "";
		   form.rotaFinal.value = "";
		   form.sequencialRotaFinal.value = "";
		    
			
		}
	}
	
	function limparBorrachaOrigem(tipo){
		var form = document.forms[0];
		
		switch(tipo){
			case 1: //De localidara pra baixo

				form.localidadeInicial.value = "";
				form.nomeLocalidadeInicial.value = "";
				form.localidadeFinal.value = "";
				form.nomeLocalidadeFinal.value = "";
				break;
			case 2: //De setor para baixo
		     	
		     	form.setorComercialInicial.value = "";
		     	form.nomeSetorComercialInicial.value = "";
		     	form.setorComercialFinal.value = "";
		     	form.nomeSetorComercialFinal.value = "";
		}
	}
	
	function limparBorrachaDestino(tipo){
		var form = document.forms[0];

		switch(tipo){
			case 1: //De localidade pra baixo
				form.localidadeFinal.value = "";
				form.nomeLocalidadeFinal.value = "";
				form.setorComercialFinal.value = "";
				
			case 2: //De setor para baixo		   
		   		form.setorComercialFinal.value = ""; 
		   		form.nomeSetorComercialFinal.value = "";		   
		}
	}
	
	
	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {

		var form = document.forms[0];
		form.action='exibirGerarRelatorioImoveisFaturasAtrasoAction.do';

		
		if (tipoConsulta == 'localidadeOrigem') {
      		
      		form.localidadeInicial.value = codigoRegistro;
	  		form.nomeLocalidadeInicial.value = descricaoRegistro;
	  		
	  		form.localidadeFinal.value = codigoRegistro;
      		form.nomeLocalidadeFinal.value = descricaoRegistro;
      		
	  		form.nomeLocalidadeInicial.style.color = "#000000";
	  		form.nomeLocalidadeFinal.style.color = "#000000";
	  		
	  		form.setorComercialInicial.focus();

		    form.submit();		  		
		}

		if (tipoConsulta == 'localidadeDestino') {
		
      		form.localidadeFinal.value = codigoRegistro;
      		form.nomeLocalidadeFinal.value = descricaoRegistro;
	  		form.nomeLocalidadeFinal.style.color = "#000000";

	  		form.setorComercialFinal.focus();

		    form.submit();		  		
		}

		if (tipoConsulta == 'cliente'){

			desabilitarClienteSuperior();
			
			form.codigoCliente.style.color = "#000000";
	  		form.nomeCliente.style.color = "#000000";
			
			form.codigoCliente.value = codigoRegistro;
			form.nomeCliente.value = descricaoRegistro;

			form.tipoRelacao.focus();
		}

		if (tipoConsulta == 'responsavelSuperior') {

			desabilitarClienteResponsavelTipoRelacao();
			
	  		form.codigoClienteSuperior.style.color = "#000000";
	  		form.nomeClienteSuperior.style.color = "#000000";

	  		form.codigoClienteSuperior.value = codigoRegistro;
			form.nomeClienteSuperior.value = descricaoRegistro;

			form.esferaPoder.focus();
		}			
	}
	
	
	function recuperarDadosQuatroParametros(idRegistro, descricaoRegistro, codigoRegistro, tipoConsulta) {

		var form = document.forms[0];

		if (tipoConsulta == 'setorComercialOrigem') {
		  	form.setorComercialInicial.value = codigoRegistro;
		  	form.nomeSetorComercialInicial.value = descricaoRegistro;
		  	form.nomeSetorComercialInicial.style.color = "#000000"; 
		  	
		  	form.setorComercialFinal.value = codigoRegistro;
		  	form.nomeSetorComercialFinal.value = descricaoRegistro;
		  	form.nomeSetorComercialFinal.style.color = "#000000";
		  	
		}

		if (tipoConsulta == 'setorComercialDestino') {
		  	form.setorComercialFinal.value = codigoRegistro;
		  	form.nomeSetorComercialFinal.value = descricaoRegistro;
		  	form.nomeSetorComercialFinal.style.color = "#000000";
		}
		
		form.action='exibirGerarRelatorioImoveisFaturasAtrasoAction.do';	
	    form.submit();
	}
	function desabilitaIntervaloDiferente(){

		var form = document.forms[0];	
		    if(form.localidadeInicial.value != form.localidadeFinal.value){	        
		        form.setorComercialInicial.disabled = true;		
			 	form.setorComercialFinal.disabled = true;
			 	form.rotaInicial.disabled = true;
			 	form.rotaFinal.disabled = true;
			 	form.sequencialRotaInicial.disabled = true;
			 	form.sequencialRotaFinal.disabled = true;
			 	form.setorComercialInicial.value = "";		
			 	form.setorComercialFinal.value = "";
	 			form.nomeSetorComercialInicial.value = "";		
			 	form.nomeSetorComercialFinal.value = "";
			 	form.rotaInicial.value = "";
			 	form.rotaFinal.value = "";
			 	form.sequencialRotaInicial.value = "";
			 	form.sequencialRotaFinal.value= "";
			 	
			 	
			  }else{
			 	form.setorComercialInicial.disabled = false;		
			 	form.setorComercialFinal.disabled = false;
			 	form.rotaInicial.disabled = false;
			 	form.rotaFinal.disabled = false;
			 	form.sequencialRotaInicial.disabled = false;
			 	form.sequencialRotaFinal.disabled = false;
			 }		
	}		
	function desabilitaIntervaloDiferenteSetor(){
		var form = document.forms[0];	
		    if(form.setorComercialInicial.value != form.setorComercialFinal.value || form.setorComercialInicial.disabled == true){	        	        
			 	form.rotaInicial.disabled = true;
			 	form.rotaFinal.disabled = true;
			 	form.sequencialRotaInicial.disabled = true;
			 	form.sequencialRotaFinal.disabled = true;
		 		form.rotaInicial.value = "";
			 	form.rotaFinal.value = "";
			 	form.sequencialRotaInicial.value = "";
			 	form.sequencialRotaFinal.value= "";	
			  }else{
			 	form.rotaInicial.disabled = false;
			 	form.rotaFinal.disabled = false;
			 	form.sequencialRotaInicial.disabled = false;
			 	form.sequencialRotaFinal.disabled = false;
			 }		
	}
function limparDestino(tipo){
	var form = document.forms[0];
	
	switch(tipo){
		case 1: //De localidade pra baixo
			if(form.localidadeInicial.value != form.localidadeFinal.value){
				form.nomeLocalidadeFinal.value = "";
				form.codigoSetorComercialInicial.value = "";
				form.setorComercialInicial.value = "";
				form.codigoSetorComercialFinal.value = "";
				form.setorComercialFinal.value = "";
		    }
		case 2: //De setor para baixo
			if(form.setorComercialInicial.value != form.setorComercialFinal.value){
			   form.nomeSetorComercialFinal.value = "";
			}
	}
}		

function reloadForm(){
	var form = document.forms[0];
		form.action='/gsan/exibirGerarRelatorioImoveisFaturasAtrasoAction.do';
		form.submit();
}

</script>

</head>

<body leftmargin="5" topmargin="5" onload="javascript:setarFoco('${requestScope.mesAnoFaturamento}');
	desabilitaIntervaloDiferente();desabilitaIntervaloDiferenteSetor();desabilitarCamposDeAcordoCriterioFiltro(false);desabilitarClienteOuClienteSuperior();marcarHidrometro(); toggleBox('divAux',0);">

<div id="formDiv">
<html:form action="/gerarRelatorioImoveisFaturasAtrasoAction.do"
	name="GerarRelatorioImoveisFaturasAtrasoActionForm"
	type="gcom.gui.relatorio.cadastro.GerarRelatorioImoveisFaturasAtrasoActionForm"
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
			<td width="600" valign="top" class="centercoltext">
		        <table height="100%">
			        <tr>
			          <td></td>
			        </tr>
		      	</table>

			<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
				<tr>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_left.gif" /></td>
					<td class="parabg">Gerar Relat&oacute;rio  de Im&oacute;veis com Faturas em Aberto</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<table width="100%" border="0">
				

				<tr>
					<td colspan="2">Para gerar o relat&oacute;rio, informe os dados abaixo:</td>
				</tr>

				<tr>
					<td width="30%">
						<strong>Filtrar por:</strong>
					</td>
					<td width="70%">
						<html:radio property="criterioFiltro" value="1" onchange="desabilitarCamposDeAcordoCriterioFiltro(true);"> Localização Geográfica</html:radio> 
						<html:radio property="criterioFiltro"  value="2" onchange="desabilitarCamposDeAcordoCriterioFiltro(true);"> Cliente</html:radio>
					</td>
				</tr>

				<tr>
					<td colspan="2">
					<hr/>
					</td>
				</tr>

				<tr>
					<td>
						<strong>Ger&ecirc;ncia Regional:</strong>
					</td>

					<td>
						<strong> 
								<html:select property="gerenciaRegional" style="width: 230px;" onchange="reloadForm();">
									<html:option
										value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;
									</html:option>
									<logic:present property="colecaoGerenciasRegionais" name="GerarRelatorioImoveisFaturasAtrasoActionForm">
										<html:optionsCollection property="colecaoGerenciasRegionais" name="GerarRelatorioImoveisFaturasAtrasoActionForm"
											label="nome" value="id" />
									</logic:present>
								</html:select>
						</strong>
					</td>
				</tr>

				<tr>
					<td>
						<strong>Unidade de Neg&oacute;cio:</strong>
					</td>

					<td>
						<strong> 
						<html:select property="unidadeNegocio" style="width: 230px;">
							<html:option
								value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;
							</html:option>
					
							<logic:present property="colecaoUnidadesNegocios" name="GerarRelatorioImoveisFaturasAtrasoActionForm">
								<html:optionsCollection property="colecaoUnidadesNegocios" name="GerarRelatorioImoveisFaturasAtrasoActionForm"
									label="nome" value="id" />
							</logic:present>
						</html:select> 														
						</strong>
					</td>
				</tr>				
              	
				<tr>
					<td><strong>Localidade Inicial:</strong></td>
					
					<td>
						
						<html:text maxlength="3" 
							tabindex="1"
							property="localidadeInicial" 
							size="3"
							onblur="javascript:replicarLocalidade();desabilitaIntervaloDiferente();" 
							onkeyup="javascript:limparOrigem(1);desabilitaIntervaloDiferente();"
							onkeypress="javascript:limparOrigem(1);validaEnterComMensagem(event, 'exibirGerarRelatorioImoveisFaturasAtrasoAction.do?objetoConsulta=1','localidadeInicial','Localidade Inicial');return isCampoNumerico(event);"/>
							
						<a href="javascript:chamarPopup('exibirPesquisarLocalidadeAction.do', 'origem', null, null, 275, 480, '',document.forms[0].localidadeInicial);limparOrigem(1);">
							<img width="23" 
								height="21" 
								border="0" 
								style="cursor:hand;"
								src="<bean:message key="caminho.imagens"/>pesquisa.gif"
								title="Pesquisar Localidade" /></a>
								

						<logic:present name="localidadeInicialEncontrada" scope="request">
							<html:text property="nomeLocalidadeInicial" 
								size="30"
								maxlength="30" 
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:present> 

						<logic:notPresent name="localidadeInicialEncontrada" scope="request">
							<html:text property="nomeLocalidadeInicial" 
								size="30"
								maxlength="30" 
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: red" />
						</logic:notPresent>

						
						<a href="javascript:limparBorrachaOrigem(1);"> 
							<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
								border="0" 
								title="Apagar" /></a>
					</td>
				</tr>
				
				<tr>
					<td><strong>Setor Comercial Inicial:</strong></td>
					
					<td>
						
						<html:text maxlength="3" 
							tabindex="1"
							property="setorComercialInicial" 
							size="3"
							onblur="javascript:replicarSetorComercial();desabilitaIntervaloDiferenteSetor();"
							onkeyup="limparOrigem(2);desabilitaIntervaloDiferenteSetor();"
							onkeypress="javascript:limparOrigem(2);validaEnterComMensagem(event, 'exibirGerarRelatorioImoveisFaturasAtrasoAction.do?objetoConsulta=2','setorComercialInicial','Setor Comercial Inicial');return isCampoNumerico(event);"/>
							
						<a href="javascript:chamarPopupSetor('exibirPesquisarSetorComercialAction.do', 'setorComercialOrigem', 'idLocalidade', document.forms[0].localidadeInicial.value , 275, 480, 'Informe Localidade Inicial',document.forms[0].setorComercialInicial);
						         limparOrigem(2);">
							<img width="23" 
								height="21" 
								border="0" 
								style="cursor:hand;"
								src="<bean:message key="caminho.imagens"/>pesquisa.gif"
								title="Pesquisar Setor Comercial" /></a>
								

						<logic:present name="setorComercialInicialEncontrado" scope="request">
							<html:text property="nomeSetorComercialInicial" 
								size="30"
								maxlength="30" 
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:present> 

						<logic:notPresent name="setorComercialInicialEncontrado" scope="request">
							<html:text property="nomeSetorComercialInicial" 
								size="30"
								maxlength="30" 
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: red" />
						</logic:notPresent>
						
						<a href="javascript:limparBorrachaOrigem(2);"> 
							<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
								border="0" 
								title="Apagar" /></a>
					</td>
				</tr>
				
				<tr>
					<td><strong>Rota Inicial:</strong></td>
					
					<td>
						
						<html:text maxlength="4" 
							tabindex="1"
							property="rotaInicial"
							onkeypress="return isCampoNumerico(event);"
							onblur="javascript:replicarRota();"
							size="4"/>
					</td>
				</tr>

				<tr>
					<td><strong>Sequencial Inicial da Rota:</strong></td>
					
					<td>
						
						<html:text maxlength="4" 
							tabindex="1"
							property="sequencialRotaInicial"
							onkeypress="return isCampoNumerico(event);"
							onblur="javascript:replicarSequencialRota();"
							size="4"/>
					</td>
				</tr>
				
				
				<tr>
					<td><strong>Localidade Final:</strong></td>
					
					<td>
						
						<html:text maxlength="3" 
							tabindex="1"
							property="localidadeFinal" 
							size="3"
							onblur="javascript:desabilitaIntervaloDiferente();"
							onkeyup="javascript:desabilitaIntervaloDiferente();"
							onkeypress="validaEnterComMensagem(event, 'exibirGerarRelatorioImoveisFaturasAtrasoAction.do?objetoConsulta=3','localidadeFinal','Localidade Final');return isCampoNumerico(event);"/>
							
						<a href="javascript:chamarPopup('exibirPesquisarLocalidadeAction.do', 'destino', null, null, 275, 480, '',document.forms[0].localidadeFinal);limparDestino(1);">
							<img width="23" 
								height="21" 
								border="0" 
								style="cursor:hand;"
								src="<bean:message key="caminho.imagens"/>pesquisa.gif"
								title="Pesquisar Localidade" /></a>
								 

						<logic:present name="localidadeFinalEncontrada" scope="request">
							<html:text property="nomeLocalidadeFinal" 
								size="30"
								maxlength="30" 
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:present> 

						<logic:notPresent name="localidadeFinalEncontrada" scope="request">
							<html:text property="nomeLocalidadeFinal" 
								size="30"
								maxlength="30" 
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: red" />
						</logic:notPresent>

						
						<a href="javascript:limparBorrachaDestino(1);"> 
							<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
								border="0" 
								title="Apagar" /></a>
					</td>
				</tr>
				
				<tr>
					<td><strong>Setor Comercial Final:</strong></td>
					
					<td>
						
						<html:text maxlength="3" property="setorComercialFinal"
							size="3"
							tabindex="8"
							onkeypress="limparDestino(2);validaEnterComMensagem(event, 'exibirGerarRelatorioImoveisFaturasAtrasoAction.do?objetoConsulta=4','setorComercialFinal','Setor Comercial Final');return isCampoNumerico(event);"		
							onkeyup="desabilitaIntervaloDiferenteSetor();"
							onblur="desabilitaIntervaloDiferenteSetor();"/>
								
						<a href="javascript:chamarPopupSetor('exibirPesquisarSetorComercialAction.do', 'setorComercialDestino', 'idLocalidade', document.forms[0].localidadeFinal.value, 275, 480, 'Informe Localidade Final',document.forms[0].setorComercialFinal);
							        limparDestino(2);">
							<img width="23" 
								height="21" 
								border="0" 
								src="<bean:message key="caminho.imagens"/>pesquisa.gif"
								title="Pesquisar Setor Comercial" /></a>
								

						<logic:present name="setorComercialFinalEncontrado" scope="request">
							<html:text property="nomeSetorComercialFinal" 
								size="30"
								maxlength="30" 
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:present> 

						<logic:notPresent name="setorComercialFinalEncontrado" scope="request">
							<html:text property="nomeSetorComercialFinal" 
								size="30"
								maxlength="30" 
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: red" />
						</logic:notPresent>
						
						<a href="javascript:limparBorrachaDestino(2);"> 
							<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
								border="0" 
								title="Apagar" /></a>
					</td>
				</tr>
				
				<tr>
					<td><strong>Rota Final:</strong></td>
					
					<td>
						
						<html:text maxlength="4" 
							tabindex="1"
							property="rotaFinal" 
							onkeypress="return isCampoNumerico(event);"
							size="4"/>
					</td>
				</tr>

				<tr>
					<td><strong>Sequencial Final da Rota:</strong></td>
					
					<td>
						
						<html:text maxlength="4" 
							tabindex="1"
							property="sequencialRotaFinal"
							onkeypress="return isCampoNumerico(event); "
							size="4"/>
					</td>
				</tr>
				
				<tr>
					<td colspan="2">
					<hr/>
					</td>
				</tr>

				<tr>
					<td width="30%"><strong>Código do Cliente Superior:</strong></td>
					<td colspan="2">
						<html:text property="codigoClienteSuperior" maxlength="9" size="9" onkeypress="return isCampoNumerico(event);" onkeyup="return validaEnterCliente(event, 'exibirGerarRelatorioImoveisFaturasAtrasoAction.do?objetoConsulta=5', 'codigoClienteSuperior','Código do Cliente Superior'); " />
						<a href="javascript:habilitarPesquisaClienteSuperior();" alt="Pesquisar Cliente Superior">
							<img width="23" height="21" src="<bean:message key='caminho.imagens'/>pesquisa.gif" border="0" />
						</a>

						<logic:present name="codigoClienteSuperiorEncontrado" scope="request">
								<html:text property="nomeClienteSuperior" size="38"	readonly="true"
									style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:present> 

						<logic:notPresent name="codigoClienteSuperiorEncontrado" scope="request">
								<html:text property="nomeClienteSuperior" size="38"	readonly="true"
									style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:notPresent>
						
						<a href="javascript:limparCamposClienteAtravesBorracha('clienteSuperior');"> 
							<img border="0" src="<bean:message key='caminho.imagens'/>limparcampo.gif" style="cursor: hand;" /> 
						</a>
					</td>
				</tr>
				<tr>
					<td width="30%"><strong>Código do Cliente:</strong></td>
					<td colspan="3">
						<html:text property="codigoCliente" maxlength="9" size="9" onkeypress="return isCampoNumerico(event);" onkeyup="return validaEnterCliente(event, 'exibirGerarRelatorioImoveisFaturasAtrasoAction.do?objetoConsulta=6', 'codigoCliente','Código do Cliente'); " />
						<a href="javascript:habilitarPesquisaCliente(document.forms[0]);" alt="Pesquisar Cliente">
							<img width="23" height="21" src="<bean:message key='caminho.imagens'/>pesquisa.gif" border="0" />
						</a>
						
						<logic:present name="codigoClienteEncontrado" scope="request">
								<html:text property="nomeCliente" size="38"	readonly="true"
									style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:present> 

						<logic:notPresent name="codigoClienteEncontrado" scope="request">
								<html:text property="nomeCliente" size="38"	readonly="true"
									style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:notPresent>
						
						<a href="javascript:limparCamposClienteAtravesBorracha('cliente');"> 
							<img border="0" src="<bean:message key='caminho.imagens'/>limparcampo.gif" style="cursor: hand;" /> 
						</a>
					</td>
				</tr>
				<tr id="linhatipoRelacao">
					<td>
						<strong>Tipo da Relação:</strong>
					</td>
					
					<td id="colunatipoRelacao">
							<strong>
								<html:select property="tipoRelacao" style="width: 230px;">
									<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
									
									<logic:present name="GerarRelatorioImoveisFaturasAtrasoActionForm" property="colecaoTiposRelacoes">
										<html:optionsCollection name="GerarRelatorioImoveisFaturasAtrasoActionForm" property="colecaoTiposRelacoes" label="descricao" value="id" />
									</logic:present>
								</html:select>
							</strong>
					</td>
				</tr>
			  <tr> 
                <td><strong>Responsável:</strong></td>
                <td colspan="6">
					<span class="style2">
						<strong> 
		                	<label>
			                  <logic:present name="semPermissao" scope="session">
				                  <html:radio property="responsavel" value="0" disabled="true"/>
				              </logic:present>
				              <logic:notPresent name="semPermissao" scope="session">
								  <html:radio property="responsavel" value="0"/>
							  </logic:notPresent>
			 				  Indicado na Conta
							</label>

		                  	<label> 
							  <html:radio property="responsavel" value="1"/>
			 				  Atual do Imóvel
							</label>

		                  	<label>
			                  <logic:present name="semPermissao" scope="session">
			                  		<html:radio property="responsavel" value="2" disabled="true" /> 
			                  </logic:present>
			                  <logic:notPresent name="semPermissao" scope="session">
							  		<html:radio property="responsavel" value="2"/>
							  </logic:notPresent>
			 				  Todos
							</label> 				  
                  		</strong>
					</span>
				</td>
              </tr>
				<tr>
					<td colspan="2">
					<hr/>
					</td>
				</tr>

				<tr>
					<td>
						<strong>Esfera de Poder:</strong>
					</td>

					<td>
						<strong> 
						<html:select property="esferaPoder" 
							style="width: 230px;height=100px;" 
							multiple="true">
							
							<html:option
								value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;
							</html:option>
					
							<logic:present property="colecaoEsferasPoder" name="GerarRelatorioImoveisFaturasAtrasoActionForm">
								<html:optionsCollection property="colecaoEsferasPoder"
									label="descricao" value="id" />
							</logic:present>
						</html:select> 														
						</strong>
					</td>
				</tr>
				
              	<tr> 
                	<td>
                		<strong>Situa&ccedil;&atilde;o da Liga&ccedil;&atilde;o de &Aacute;gua</strong>
                    </td>
                	
                	<td colspan="6">
               			<strong>
						<html:select property="situacaoLigacaoAgua" 
							style="width: 350px; height:100px;" 
							multiple="true">
							
							<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
							
							<logic:present property="colecaoSituacoesLigacaoAgua" name="GerarRelatorioImoveisFaturasAtrasoActionForm">
								<html:optionsCollection property="colecaoSituacoesLigacaoAgua" 
									label="descricao" value="id" />
							</logic:present>
						</html:select>                
                 		</strong>
                 	 </td>
              	</tr>				
              	<tr> 
                	<td>
                		<strong>Categorias</strong>                		
                    </td>
                	
                	<td colspan="6">
               			<strong>
						<html:select property="categorias" 
							style="width: 350px; height:100px;" 
							multiple="true">
							
							<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
							
							<logic:present property="colecaoCategorias" name="GerarRelatorioImoveisFaturasAtrasoActionForm">
								<html:optionsCollection property="colecaoCategorias" 
									label="descricao" value="id" />
							</logic:present>
						</html:select>                
                 		</strong>
                 	 </td>
                 </tr>
                 
                 <tr>		 
                 	 <td>
                		<strong>Perfil do Imóvel</strong>                		
                    </td>
                	
                	<td colspan="6">
               			<strong>
						<html:select property="perfilImovel" 
							style="width: 350px; height:100px;" 
							multiple="true">
							
							<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
							
							<logic:present property="colecaoPerfisImovel" name="GerarRelatorioImoveisFaturasAtrasoActionForm">
								<html:optionsCollection property="colecaoPerfisImovel" 
									label="descricao" value="id" />
							</logic:present>
						</html:select>                
                 		</strong>
                 	 </td>
              	</tr>    				
              	<tr> 
                	<td>
                		<strong>Situação de Cobrança</strong>                		
                    </td>
                	
                	<td colspan="6">
               			<strong>
						<html:select property="situacaoCobranca">
							
							<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
							
							<logic:present name="GerarRelatorioImoveisFaturasAtrasoActionForm" property="colecaoSituacoesCobranca">
								<html:optionsCollection name="GerarRelatorioImoveisFaturasAtrasoActionForm" property="colecaoSituacoesCobranca" label="descricao" value="id"/>
							</logic:present>
						</html:select>                
                 		</strong>
                 	 </td>
              	</tr>    				
				<tr>
					<td><strong>Qtd. de Faturas :</strong><font color="#FF0000">*</font></td>					
					<td colsan="3">						
						<html:text maxlength="5"
							tabindex="1"
							property="quantidadeFaturasAtrasoInicial" 
							size="3" 
							onkeypress="return isCampoNumerico(event);" 
							onkeyup="javascript:replicarCampo( document.forms[0].quantidadeFaturasAtrasoFinal, document.forms[0].quantidadeFaturasAtrasoInicial );"/>
						<strong>a</strong>
						<html:text maxlength="5"
							tabindex="1"
							onkeypress="return isCampoNumerico(event);" 
							property="quantidadeFaturasAtrasoFinal" 
							size="3"/>
					</td>
				</tr>
				<tr>
					<td><strong>Refer&ecirc;ncia das Faturas :</strong></td>					
					<td colsan="3">						
						<html:text maxlength="7"
							tabindex="1"
							property="referenciaFaturasAtrasoInicial"
							size="7"
							onkeypress="return isCampoNumerico(event);" 
							onkeyup="javascript:replicarCampo( document.forms[0].referenciaFaturasAtrasoFinal, document.forms[0].referenciaFaturasAtrasoInicial );mascaraAnoMes(this, event);"/>
						<strong>a</strong>
						<html:text maxlength="7"
							tabindex="1"
							property="referenciaFaturasAtrasoFinal" 
							size="7"
							onkeypress="return isCampoNumerico(event);"
							onkeyup="mascaraAnoMes(this, event);"/>(mm/aaaa)
					</td>
				</tr>
				<tr>
					<td><strong>Valor de Faturas :</strong></td>						
					<td colsan="3">						
						<html:text maxlength="7"
							tabindex="1"
							property="valorFaturasAtrasoInicial" 
							size="8"
							onkeypress="return isCampoNumerico(event);" 
							onkeyup="javascript:replicarCampo( document.forms[0].valorFaturasAtrasoFinal, document.forms[0].valorFaturasAtrasoInicial );"/>
						<strong>a</strong>
						<html:text maxlength="7"
							tabindex="1"
							onkeypress="return isCampoNumerico(event);" 
							property="valorFaturasAtrasoFinal" 
							size="8"/>
					</td>
				</tr>
				<tr>
					<td><strong>Tipo :</strong></td>						
					<td colsan="3">						
						<html:radio
							tabindex="1"
							property="tipo" value="A"/>Agrupadas
							&nbsp;
						<html:radio
							tabindex="1"
							property="tipo" value="D"/>Descritas

					</td>
				</tr>
				<tr> 
	            	<td><strong>Indicador de Hidrômetro:</strong></td>
	                <td colspan="5">
						<html:radio tabindex="1" property="hidrometro" value="1"/>Com Hidrômetro
					    &nbsp;
					    <html:radio tabindex="1" property="hidrometro" value="2"/>Sem Hidrômetro
						&nbsp;
						<html:radio tabindex="1" property="hidrometro" value="0"/>Todos
					</td>
              	</tr>
				<tr>
					<td height="24" >
			          	<input type="button" 
			          		class="bottonRightCol" 
			          		value="Limpar" 
			          		onclick="javascript:limpar();"/>
					</td>
				
					<td align="right">
						<input type="button" 
							name="Button" 
							class="bottonRightCol" 
							value="Gerar" 
							onClick="javascript:validarForm();" />
					</td>
				</tr>
			</table>
			<p>&nbsp;</p>
			</td>
		</tr>
	</table>
	
	<div id="divAux" style="position:absolute; left:110px; top:500px; width:200px; height:70px;">
	  <jsp:include page="/jsp/relatorio/escolher_tipo_relatorio_tela_espera.jsp?relatorio=gerarRelatorioImoveisFaturasAtrasoAction.do"/>
	</div>

	<%@ include file="/jsp/util/rodape.jsp"%>	

</html:form>
</div>
</body>
</html:html>
