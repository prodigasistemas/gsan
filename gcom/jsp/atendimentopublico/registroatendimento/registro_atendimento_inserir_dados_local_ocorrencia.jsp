<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>

<%@ page import="gcom.util.ConstantesSistema" %>
<%@ page import="gcom.faturamento.conta.Conta" %>
<%@ page import="gcom.arrecadacao.pagamento.Pagamento"%>
<%@ page import="gcom.atendimentopublico.registroatendimento.LocalOcorrencia" %>
<%@ page import="java.math.BigDecimal,gcom.util.Util" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">

<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js" ></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js" ></script>

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="InserirRegistroAtendimentoActionForm" dynamicJavascript="false" />

<SCRIPT LANGUAGE="JavaScript">
<!--
window.onmousemove = verificarCamposHistoryBack;
	var bCancel = false; 

    function validateInserirRegistroAtendimentoActionForm(form) {                                                                   
        if (bCancel) 
      return true; 
        else 
       return validateCaracterEspecial(form) && validateInteger(form) && validateInteiroZeroPositivo(form) && validarCamposRequeridos(form)
       			&& validarEnderecoPreenchido(form); 
   } 
   
   function caracteresespeciais () { 
     this.ah = new Array("idImovel", "Imóvel possui caracteres especiais.", new Function ("varName", " return this[varName];"));
     this.ai = new Array("pontoReferencia", "Imóvel possui caracteres especiais.", new Function ("varName", " return this[varName];"));
     this.aj = new Array("idMunicipio", "Município possui caracteres especiais.", new Function ("varName", " return this[varName];"));
     this.ak = new Array("cdBairro", "Bairro possui caracteres especiais.", new Function ("varName", " return this[varName];"));
     this.al = new Array("idLocalidade", "Localidade possui caracteres especiais.", new Function ("varName", " return this[varName];"));
     this.am = new Array("cdSetorComercial", "Setor Comercial possui caracteres especiais.", new Function ("varName", " return this[varName];"));
     this.an = new Array("nnQuadra", "Quadra possui caracteres especiais.", new Function ("varName", " return this[varName];"));
     this.ao = new Array("idUnidadeDestino", "Unidade Destino possui caracteres especiais.", new Function ("varName", " return this[varName];"));
     this.ap = new Array("parecerUnidadeDestino", "Parecer possui caracteres especiais.", new Function ("varName", " return this[varName];"));
     this.aq = new Array("descricaoLocalOcorrencia", "Descrição possui caracteres especiais.", new Function ("varName", " return this[varName];"));
    }

	function IntegerValidations () { 
		this.ac = new Array("idImovel", "Imóvel deve ser numérico(a).", new Function ("varName", " return this[varName];"));
		this.ad = new Array("idMunicipio", "Município deve ser numérico(a).", new Function ("varName", " return this[varName];"));
		this.ae = new Array("idLocalidade", "Localidade deve ser numérico(a).", new Function ("varName", " return this[varName];"));
		this.af = new Array("cdSetorComercial", "Setor Comercial deve ser numérico(a).", new Function ("varName", " return this[varName];"));
		this.ag = new Array("idUnidadeDestino", "Unidade Destino deve ser numérico(a).", new Function ("varName", " return this[varName];"));
    }
    
    
    function InteiroZeroPositivoValidations () { 
     this.aa = new Array("cdBairro", "Bairro deve somente conter números positivos ou zero.", new Function ("varName", " return this[varName];"));
     this.ab = new Array("nnQuadra", "Quadra deve somente conter números positivos ou zero.", new Function ("varName", " return this[varName];"));
    }
    
    
    function validarCamposRequeridos(form){
    
    	var retorno = false;
    	var msgAlert = "";
    	 
    	if (dadosLocalOcorrenciaInformados() || form.descricaoLocalOcorrencia.value.length > 0){
    	
    		if (form.descricaoLocalOcorrencia.value.length > 0){
    			alert("O Registro de Atendimento ficará bloqueado até que seja informado o endereço da ocorrência");
    			retorno = true;
    		}
    		else{
    			
    			var imovelObrigatorio = form.imovelObrigatorio.value;
    			var idImovel = form.idImovel.value;
    			var pavimentoRuaObrigatorio = form.pavimentoRuaObrigatorio.value;
    			var pavimentoCalcadaObrigatorio = form.pavimentoCalcadaObrigatorio.value;
    			var localOcorrencia = form.idLocalOcorrencia.options[form.idLocalOcorrencia.selectedIndex];
    			
    			var botaoEndereco = document.getElementById("botaoEndereco");
    			var enderecoInformado = document.getElementById("enderecoInformado");
    			
    			if (imovelObrigatorio == "SIM" && idImovel == ''){
    				msgAlert = "Informe Imóvel \n";			
    			}
    			
    			if (form.idBairroArea.disabled && !botaoEndereco.disabled && enderecoInformado == null){
    				msgAlert = "Informe Endereço \n";
    			}
    			
    			if (!form.idMunicipio.readOnly && form.idMunicipio.value.length < 1){
    				msgAlert = msgAlert + "Informe Município \n";			
    			}
    			
    			if (!form.cdBairro.readOnly && form.cdBairro.value.length < 1){
    				msgAlert = msgAlert + "Informe Bairro \n";			
    			}
    			
    			if (!form.idBairroArea.disabled && form.idBairroArea.value < 0){
    				msgAlert = msgAlert + "Informe Área do Bairro \n";			
    			}
    			
    			if (!form.idLocalidade.readOnly && form.idLocalidade.value.length < 1){
    				msgAlert = msgAlert + "Informe Localidade \n";			
    			}
    			
    			if (!form.nnQuadra.readOnly && form.nnQuadra.value.length > 0 && form.cdSetorComercial.value.length < 1){
    				msgAlert = msgAlert + "Informe Setor Comercial \n";			
    			}
    			
    			if (!form.idDivisaoEsgoto.disabled && form.idDivisaoEsgoto.value < 0){
    				msgAlert = msgAlert + "Informe Divisão de Esgoto \n";			
    			}
    			
    			if (!form.parecerUnidadeDestino.readOnly && form.parecerUnidadeDestino.value.length < 1 &&
    				form.idUnidadeDestino.value.length > 0){
    				msgAlert = msgAlert + "Informe Parecer \n";			
    			}
    			
    			if (!form.idPavimentoRua.disabled && form.idPavimentoRua.value < 0 && 
    				(pavimentoRuaObrigatorio == "SIM" || localOcorrencia.id == "1")){
    				msgAlert = msgAlert + "Informe Pavimento da Rua \n";			
    			}
    			
    			if (!form.idPavimentoCalcada.disabled && form.idPavimentoCalcada.value < 0 && 
    				(pavimentoCalcadaObrigatorio == "SIM" || localOcorrencia.name == "1")){
    				msgAlert = msgAlert + "Informe Pavimento da Calçada \n";			
    			}
    			
    			if (msgAlert.length < 1){
    				retorno = true;
    			}
    			else{
    				alert(msgAlert);
    			}
    		}
    	}
    	else{
    		form.idImovel.focus();
    		alert("Informe os  Dados da Identificação do Local da Ocorrência ou a Descrição do Local da Ocorrência");
    	}
    	
    	return retorno;
    }
    
    function validarEnderecoPreenchido(form) {
    	var retorno = false;
		
    	if (!form.idImovel.readOnly) {
    		if (form.enderecoPreenchido.value != "OK") {
	    		form.idImovel.focus();
    			alert("Informe os  Dados da Identificação do Local da Ocorrência ou a Descrição do Local da Ocorrência");
    		}else {
    			retorno = true;
    		}
    	}else {
    		retorno = true;
    	}
		
		return retorno;
    }
    
    
    function obterIndicadorRuaLocalOcorrencia(){
    
    }
    
    
    function obterIndicadorCalcadaLocalOcorrencia(){
    
    }
    
	function limpar(situacao){
	
		var form = document.forms[0];
	
		switch (situacao){
	      case 1:
			   
			   redirecionarSubmit("inserirRegistroAtendimentoWizardAction.do?action=exibirInserirRegistroAtendimentoDadosLocalOcorrenciaAction&limparImovel=OK");
			   
			   break;
		  case 2:
		   	   form.cdBairro.value = "";
		       form.descricaoBairro.value = "";
		       form.idBairro.value = "";
		       form.descricaoMunicipio.value = "";
		       
		       if (form.idBairroArea.options.length > 1){
		       		redirecionarSubmit("inserirRegistroAtendimentoWizardAction.do?action=exibirInserirRegistroAtendimentoDadosLocalOcorrenciaAction&removerColecaoBairroArea=OK&campoFoco=idMunicipio");
		       }
		       
		       break;
		  case 3:
		       form.idMunicipio.value = "";
		       form.descricaoMunicipio.value = "";
		       form.cdBairro.value = "";
		       form.idBairro.value = "";
		       form.descricaoBairro.value = "";
 			   
 			   if (form.idBairroArea.options.length > 1){
		       		redirecionarSubmit("inserirRegistroAtendimentoWizardAction.do?action=exibirInserirRegistroAtendimentoDadosLocalOcorrenciaAction&removerColecaoBairroArea=OK&campoFoco=idMunicipio");
		       }
		       
		       //Coloca o foco no objeto selecionado
		       form.idMunicipio.focus();
		       break;
		  case 4:
		   	   form.cdBairro.value = "";
		       form.idBairro.value = "";
		       form.descricaoBairro.value = "";
			   
			   if (form.idBairroArea.options.length > 1){
		       		redirecionarSubmit("inserirRegistroAtendimentoWizardAction.do?action=exibirInserirRegistroAtendimentoDadosLocalOcorrenciaAction&removerColecaoBairroArea=OK&campoFoco=cdBairro");
		       }
		       
		       //Coloca o foco no objeto selecionado
		       form.cdBairro.focus();
		       break;
		       
		  case 5:
		  	   form.descricaoLocalidade.value = "";
		       form.cdSetorComercial.value = "";
		       form.idSetorComercial.value = "";
		       form.descricaoSetorComercial.value = "";
		       form.idQuadra.value = "";
		       form.nnQuadra.value = "";
		       
		       var msgQuadra = document.getElementById("msgQuadra");
	
			   if (msgQuadra != null){
					msgQuadra.innerHTML = "";
			   }
			   
			   //Coloca o foco no objeto selecionado
		       form.idLocalidade.focus();
		       
		       break;
		   
		   case 6:
		  	   form.idSetorComercial.value = "";
		       form.descricaoSetorComercial.value = "";
		       form.idQuadra.value = "";
		       form.nnQuadra.value = "";
		       
		       var msgQuadra = document.getElementById("msgQuadra");
	
			   if (msgQuadra != null){
					msgQuadra.innerHTML = "";
			   }
			   
			   //Coloca o foco no objeto selecionado
		       form.cdSetorComercial.focus();
		       
		       break;
		       
		    case 7:
		  	   form.idQuadra.value = "";
		  	   var msgQuadra = document.getElementById("msgQuadra");
	
			   if (msgQuadra != null){
					msgQuadra.innerHTML = "";
			   }
			   
			   break;
		       
		    case 8:
		       redirecionarSubmit("inserirRegistroAtendimentoWizardAction.do?action=exibirInserirRegistroAtendimentoDadosLocalOcorrenciaAction&limparLocalidade=OK");
		       
		       var msgQuadra = document.getElementById("msgQuadra");
	
			   if (msgQuadra != null){
					msgQuadra.innerHTML = "";
			   }
			   
			   //Coloca o foco no objeto selecionado
		       form.idLocalidade.focus();
		       
		       break;
		   
		   case 9:
		  	   form.cdSetorComercial.value = "";
		       form.idSetorComercial.value = "";
		       form.descricaoSetorComercial.value = "";
		       form.idQuadra.value = "";
		       form.nnQuadra.value = "";
		       
		       var msgQuadra = document.getElementById("msgQuadra");
	
			   if (msgQuadra != null){
					msgQuadra.innerHTML = "";
			   }
			   
			   //Coloca o foco no objeto selecionado
		       form.cdSetorComercial.focus();
		       
		       break;
		       
		   case 10:
		  	   form.idUnidadeDestino.value = "";
		       form.descricaoUnidadeDestino.value = "";
		       form.parecerUnidadeDestino.value = "";
		       
			   //Coloca o foco no objeto selecionado
		       form.idUnidadeDestino.focus();
		       
		       verificarCamposOnLoad();
		       
		       break;
		       
		   case 11:
		   	   form.idBairro.value = "";
		       form.descricaoBairro.value = "";
			   
			   if (form.idBairroArea.options.length > 1){
		       		redirecionarSubmit("inserirRegistroAtendimentoWizardAction.do?action=exibirInserirRegistroAtendimentoDadosLocalOcorrenciaAction&removerColecaoBairroArea=OK&campoFoco=cdBairro");
		       }
		       
		       break;
		       
		   case 12:
		  	   form.descricaoUnidadeDestino.value = "";
		       form.parecerUnidadeDestino.value = "";
		       
			   break;
		   
		   case 13:
		  	   form.parecerUnidadeDestino.value = "";
		       
			   //Coloca o foco no objeto selecionado
		       form.parecerUnidadeDestino.focus();
		       
		       break;
		   default:
	          break;
		}
	}
	
	
	function recuperarDadosQuatroParametros(idRegistro, descricaoRegistro, codigoRegistro, tipoConsulta) {

		var form = document.forms[0];

		if (tipoConsulta == 'bairro') {
      		form.cdBairro.value = codigoRegistro;
	  		form.idBairro.value = idRegistro;
	  		form.descricaoBairro.value = descricaoRegistro;
	  		form.descricaoBairro.style.color = "#000000";
	  		
	  		redirecionarSubmit("inserirRegistroAtendimentoWizardAction.do?action=exibirInserirRegistroAtendimentoDadosLocalOcorrenciaAction&pesquisarBairroArea=OK");
		}
	}


	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {

		var form = document.forms[0];

		if (tipoConsulta == 'municipio') {
      		form.idMunicipio.value = codigoRegistro;
	  		form.descricaoMunicipio.value = descricaoRegistro;
	  		form.descricaoMunicipio.style.color = "#000000";
	  		
	  		limpar(4);
		}
	
		if (tipoConsulta == 'localidade') {
      		form.idLocalidade.value = codigoRegistro;
	  		form.descricaoLocalidade.value = descricaoRegistro;
	  		form.descricaoLocalidade.style.color = "#000000";
	  		redirecionarSubmit("inserirRegistroAtendimentoWizardAction.do?action=exibirInserirRegistroAtendimentoDadosLocalOcorrenciaAction&pesquisarLocalidade=OK");
	  		
	  		limpar(6);
		}
		
		if (tipoConsulta == 'unidadeOrganizacional') {
      		form.idUnidadeDestino.value = codigoRegistro;
      		form.descricaoUnidadeDestino.value = descricaoRegistro;
      		form.descricaoUnidadeDestino.style.color = "#000000";
      
      		redirecionarSubmit("inserirRegistroAtendimentoWizardAction.do?action=exibirInserirRegistroAtendimentoDadosLocalOcorrenciaAction&pesquisarUnidadeDestino=OK");
    	}
    	if (tipoConsulta == 'imovel') {
      		form.idImovel.value = codigoRegistro;
      
      		redirecionarSubmit("inserirRegistroAtendimentoWizardAction.do?action=exibirInserirRegistroAtendimentoDadosLocalOcorrenciaAction&pesquisarImovel=OK");
    	}
		if (tipoConsulta == 'setorComercial') {
      		form.cdSetorComercial.value = codigoRegistro;
	  		form.descricaoSetorComercial.value = descricaoRegistro;
	  		form.descricaoSetorComercial.style.color = "#000000";
		}    
		if (tipoConsulta == 'conta') {
      		form.idConta.value = codigoRegistro;
	  		form.descConta.value = descricaoRegistro;
	  		
      		redirecionarSubmit("inserirRegistroAtendimentoWizardAction.do?action=exibirInserirRegistroAtendimentoDadosLocalOcorrenciaAction&pesquisarConta=OK");
		}    	
	}
	
	
	function chamarPopup(url, tipo, objeto, codigoObjeto, altura, largura, msg){

		if (habilitarDesabilitarDadosLocalOcorrencia(document.forms[0].descricaoLocalOcorrencia)){
	
			if (objeto == null || codigoObjeto == null){
				abrirPopup(url + "?" + "tipo=" + tipo, altura, largura);
			}
			else{
				if (codigoObjeto.length < 1 || isNaN(codigoObjeto)){
					alert(msg);
				}	
				else{
					abrirPopup(url + "?" + "tipo=" + tipo + "&" + objeto + "=" + codigoObjeto + "&caminhoRetornoTelaPesquisaBairro=" + "/exibirInserirQuadra.do", altura, largura);
				}
			}	
		}
	}
	
	
	function verificarCompatibilidadeDivisaoEsgoto(){
		
		var form = document.forms[0];
		
		if (form.idDivisaoEsgoto.value > 0){
			redirecionarSubmit('inserirRegistroAtendimentoWizardAction.do?action=exibirInserirRegistroAtendimentoDadosLocalOcorrenciaAction&verificarCompatibilidade=OK');
		}
		else{
			habilitarDesabilitarDescricaoLocalOcorrencia();
		}
	}
	
	//Remover Endereço
	function remover(){
		redirecionarSubmit('inserirRegistroAtendimentoWizardAction.do?action=exibirInserirRegistroAtendimentoDadosLocalOcorrenciaAction&removerEndereco=OK');
	}
	
	function dadosLocalOcorrenciaInformados(){
		
		var retorno = false;
		var form = document.forms[0];
		
		for(indice = 0; indice < form.elements.length; indice++){
			
			if ((form.elements[indice].type == "text" ||  
				 form.elements[indice].type == "textarea") 
				 && form.elements[indice].name != "descricaoLocalOcorrencia"
				 && form.elements[indice].value.length > 0
				 && form.elements[indice].name != "nnCoordenadaNorte"
				 && form.elements[indice].name != "nnCoordenadaLeste") {
				
				retorno = true;
				break;
			}
			else if (form.elements[indice].type == "select-one" && form.elements[indice].value > 0 && 
					form.elements[indice].name != "ultimoacesso"){
				retorno = true;
				break;
			}
		}
		
		var enderecoInformado = document.getElementById("enderecoInformado");
		
		if (!retorno){
			if (enderecoInformado != null){
				retorno = true;
			}
		}
		
		return retorno;	
	}
	
	function habilitarDesabilitarDescricaoLocalOcorrencia(){
	
		var form = document.forms[0];
		
		if (dadosLocalOcorrenciaInformados()){
			form.descricaoLocalOcorrencia.readOnly = true;
		}
		else{
			form.descricaoLocalOcorrencia.readOnly = false;
		}
	}
	
	function habilitarDesabilitarDadosLocalOcorrencia(campoReferencia){
	
		var form = document.forms[0];
		
		for(indice = 0; indice < form.elements.length; indice++){
			
			if ((form.elements[indice].type == "text" ||  
				 form.elements[indice].type == "textarea") 
				 && form.elements[indice].name != "descricaoLocalOcorrencia"
				 && form.elements[indice].style.border == ""
				 && campoReferencia.value.length > 0) {
				
				form.elements[indice].readOnly = true;
			}
			else if ((form.elements[indice].type == "text" ||  
				 form.elements[indice].type == "textarea") 
				 && form.elements[indice].name != "descricaoLocalOcorrencia"
				 && form.elements[indice].style.border == ""
				 && campoReferencia.value.length < 1) {
				
				form.elements[indice].readOnly = false; 
			}
			else if (form.elements[indice].type == "select-one" && campoReferencia.value.length > 0 && form.elements[indice].name != "ultimoacesso"){
				
				form.elements[indice].disabled = true;
			}
			else if (form.elements[indice].type == "select-one" && campoReferencia.value.length < 1){
				
				form.elements[indice].disabled = false;
			}
		}
		
		//=================================================================
		if(form.descricaoLocalOcorrencia.value == ''){ 
			 if(form.solicitacaoTipoRelativoFaltaAgua.value == 'SIM'){
			     form.idMunicipio.readOnly = false;
			     form.cdBairro.readOnly = false;
			     form.idBairroArea.disabled = false;
			 }else{
				 form.idMunicipio.readOnly = true;
			     form.cdBairro.readOnly = true;
			     form.idBairroArea.disabled = true;
			 }
			 
			 if(form.solicitacaoTipoRelativoAreaEsgoto.value == 'SIM'){
		       form.idDivisaoEsgoto.disabled = false;
		     }else{
			   form.idDivisaoEsgoto.disabled = true;
		    }
		 }
	 	
	 	if(form.idImovel.value != ''){
	  		form.idLocalidade.readOnly = true;
	  		form.cdSetorComercial.readOnly = true;
	  		form.nnQuadra.readOnly = true;
	  		//form.idDivisaoEsgoto.disabled = true;
	  		form.idMunicipio.readOnly = true;
	        form.cdBairro.readOnly = true;
	  
	  		if(form.idPavimentoCalcada.value != ''){
	    		if(form.imovelObrigatorio.value =='SIM'){
	     			form.idPavimentoCalcada.disabled = true;
	    		}else{
	    			form.idPavimentoCalcada.disabled = false;
	    		}
	   		}else{
	    		form.idPavimentoCalcada.disabled = false;
	   		}
	   		
	   		if(form.idPavimentoRua.value != ''){
	    		if(form.imovelObrigatorio.value =='SIM'){
	     			form.idPavimentoRua.disabled = true;
	    		}else{
	    			form.idPavimentoRua.disabled = false;
	    		}
	   		}else{
	   			form.idPavimentoRua.disabled = false;
	   		}
	 	}else{
	  		if(form.descricaoLocalOcorrencia.value == ''){
	   		   form.idLocalidade.readOnly = false;
			   form.cdSetorComercial.readOnly = false;
			   form.nnQuadra.readOnly = false;
			   form.idPavimentoCalcada.disabled = false;
			   form.idPavimentoRua.disabled = false;
	  		}
	 	}
		//=============================================================
		
		//Lupas
		if (campoReferencia.value.length > 0){
			return false;
		}
		else{
			return true;
		}
	}
	
	
	function carregarIndicadorLocalOcorrencia(campo){
		var localOcorrencia = campo.options[campo.selectedIndex];
		var form = document.forms[0];
		form.indRuaLocalOcorrencia.value = localOcorrencia.id;
		form.indCalcadaLocalOcorrencia.value = localOcorrencia.nome;
	}
	
	
	//[SB0005] - Habilita/Desabilita Dados da Identificação do Local de Ocorrência e dados Solicitante
	
	function verificarCamposOnLoad(){
		var form = document.forms[0];
		
	 	var enderecoInformado = document.getElementById("enderecoInformado");
	     var informarCep = form.informarCep.value;

	    if (informarCep == "SIM"){
    		abrirPopupComSubmit('inserirRegistroAtendimentoWizardAction.do?action=exibirInserirRegistroAtendimentoDadosLocalOcorrenciaAction&tipoPesquisaEndereco=registroAtendimento&operacao=1&pesquisarLogradouro=OK&gis=OK',570,700,'endereco');		
    					
   		}
	 	
	 	
	 
	 habilitarDesabilitarDescricaoLocalOcorrencia();
	 habilitarDesabilitarDadosLocalOcorrencia(form.descricaoLocalOcorrencia);	 

		 if(form.descricaoLocalOcorrencia.value == ''){ 
			 if(form.solicitacaoTipoRelativoFaltaAgua.value == 'SIM'){
			     form.idMunicipio.readOnly = false;
			     form.cdBairro.readOnly = false;
			     form.idBairroArea.disabled = false;
			 }else{
				 form.idMunicipio.readOnly = true;
			     form.cdBairro.readOnly = true;
			     form.idBairroArea.disabled = true;
			 }
			 
			 if(form.solicitacaoTipoRelativoAreaEsgoto.value == 'SIM'){
		       	form.idDivisaoEsgoto.disabled = false;
		     }else{
		     	form.idDivisaoEsgoto.disabled = true;
		    }
		 }
	 	
	 	if(form.idImovel.value != ''){
	  		form.idLocalidade.readOnly = true;
	  		form.cdSetorComercial.readOnly = true;
	  		form.nnQuadra.readOnly = true;
	  		//form.idDivisaoEsgoto.disabled = true;
	  		form.idMunicipio.readOnly = true;
	        form.cdBairro.readOnly = true;
	  
	  		if(form.idPavimentoCalcada.value != ''){
	    		if(form.imovelObrigatorio.value =='SIM'){
	     			form.idPavimentoCalcada.disabled = true;
	    		}else{
	    			form.idPavimentoCalcada.disabled = false;
	    		}
	   		}else{
	    		form.idPavimentoCalcada.disabled = false;
	   		}
	   		
	   		if(form.idPavimentoRua.value != ''){
	    		if(form.imovelObrigatorio.value =='SIM'){
	     			form.idPavimentoRua.disabled = true;
	    		}else{
	    			form.idPavimentoRua.disabled = false;
	    		}
	   		}else{
	   			form.idPavimentoRua.disabled = false;
	   		}
	 	}else{
	  		if(form.descricaoLocalOcorrencia.value == ''){
	   		   form.idLocalidade.readOnly = false;
			   form.cdSetorComercial.readOnly = false;
			   form.nnQuadra.readOnly = false;
			   form.idPavimentoCalcada.disabled = false;
			   form.idPavimentoRua.disabled = false;
	  		}
	 	}
	 	
		if (enderecoInformado != null) {
			form.idMunicipio.readOnly = true;
			form.cdBairro.readOnly = true;
		}
		
	}
	
	
	function consultarRAsPendentes(){
		
		var form = document.forms[0];
		var idImovel = form.idImovel.value;
		
		if (!isNaN(idImovel) && idImovel.length > 0 && idImovel.indexOf(',') == -1 &&
			idImovel.indexOf('.') == -1 && ((idImovel * 1) > 0)){
			
			abrirPopup("/gsan/exibirConsultarRegistroAtendimentoPendentesImovelAction.do?idImovel=" + 
			idImovel + "&situacao=1", 400, 690);
		}
	}
	
	function consultarDebitos() {
		
		var form = document.forms[0];
		var idImovel = form.idImovel.value;
		
		if (!isNaN(idImovel) && idImovel.length > 0 && idImovel.indexOf(',') == -1 &&
			idImovel.indexOf('.') == -1 && ((idImovel * 1) > 0)){
			
			abrirPopup('consultarDebitoAction.do?ehPopup=true&codigoImovel='+idImovel, 550, 735);
		}		
	}
	
	function verificarCamposHistoryBack(){
		var form = document.forms[0];
		
//		alert(form.descricaoLocalOcorrencia);
//		alert(form.descricaoLocalOcorrencia.value);
	 	if(form.descricaoLocalOcorrencia.value == ''){ 
			if(form.solicitacaoTipoRelativoFaltaAgua.value == 'SIM'){
		    	form.idBairroArea.disabled = false;
		 	}else{
				form.idBairroArea.disabled = true;
		 	}
		 
		 	if(form.solicitacaoTipoRelativoAreaEsgoto.value == 'SIM'){
	       		form.idDivisaoEsgoto.disabled = false;
	     	}else{
		   		form.idDivisaoEsgoto.disabled = true;
	    	}
	 	}
	 
		if(form.idImovel.value != ''){
	  
	  		if(form.idPavimentoCalcada.value != ''){
	    		if(form.imovelObrigatorio.value =='SIM'){
	     			form.idPavimentoCalcada.disabled = true;
	    		}else{
	    			form.idPavimentoCalcada.disabled = false;
	    		}
	   		}else{
	    		form.idPavimentoCalcada.disabled = false;
	   		}
	   		
	   		if(form.idPavimentoRua.value != ''){
	    		if(form.imovelObrigatorio.value =='SIM'){
	     			form.idPavimentoRua.disabled = true;
	    		}else{
	    			form.idPavimentoRua.disabled = false;
	    		}
	   		}else{
	   			form.idPavimentoRua.disabled = false;
	   		}
	 	}else{
	  		if(form.descricaoLocalOcorrencia.value == ''){
	   			form.idPavimentoCalcada.disabled = false;
	   			form.idPavimentoRua.disabled = false;
	  		}
	 	}
	}
	
	//Integraçã com o GIS
	function respostaGis(){		  
   		redirecionarSubmit('inserirRegistroAtendimentoWizardAction.do?action=exibirInserirRegistroAtendimentoDadosLocalOcorrenciaAction');	  
   		  		
	}
	
	function setLocalOcorrencia(localOcorrencia) {
		var form = document.forms[0];
		
		form.idLocalOcorrencia.value = localOcorrencia;
	}
	
	
   function pesquisarConta(idImovel){
   		var form = document.forms[0];

     	if (idImovel.value == "") {
       		alert('Informe o imóvel para pesquisar as contas.');
     	} else if (verificaAnoMes(form.idConta)) {
     		abrirPopup('exibirPesquisarContaAction.do?idImovel='+idImovel.value , 400, 800);
     	}
 	}
 	
	function limparConta() {
	
		var form = document.forms[0];
		
		form.idConta.value = "";
		form.descConta.value = "";
	
	}
	
    function recuperarDadosQuatroParametros(codigoRegistro, descricaoRegistro1, descricaoRegistro2, tipoConsulta) {
        
    	var form = document.forms[0];
        
    	if (tipoConsulta == 'conta') {
      		form.idConta.value = descricaoRegistro1;
	  		form.descConta.value = descricaoRegistro1;
   			form.idContaPesquisada.value = codigoRegistro;
	  		
      		redirecionarSubmit("inserirRegistroAtendimentoWizardAction.do?action=exibirInserirRegistroAtendimentoDadosLocalOcorrenciaAction&pesquisarConta=OK");
		}  
    }

	function limparContaTecla() {
		var form = document.forms[0];
		form.idConta.value = "";
		form.descConta.value = "";
	}

	function adicionarConta() {
	
		var form = document.forms[0];
		
		if (form.idConta.value == '') {
			alert('Informe a Conta.');
		} else if (verificaAnoMes(form.idConta)) {
			form.action = 'inserirRegistroAtendimentoWizardAction.do?action=exibirInserirRegistroAtendimentoDadosLocalOcorrenciaAction&adicionarConta=OK';
			form.submit();
		}
	
	}
	
	function validaContaImovel(tecla) {
		var form = document.forms[0];
		
		if (document.all) {
			var codigo = event.keyCode;
	    }
		else {
	       var codigo = tecla.which;
	    }
	
		if (codigo == 13) {
			if(form.idImovel.value == ""){
				form.idConta.value = "";
				form.descConta.value = "";
	       		alert('Informe o imóvel para pesquisar as contas.');
	       		return true;
	     	} else if (verificaAnoMes(form.idConta)) {
	     		return validaEnterString(tecla, 'inserirRegistroAtendimentoWizardAction.do?action=exibirInserirRegistroAtendimentoDadosLocalOcorrenciaAction&pesquisarConta=OK', 'idConta');
	     	}
		} else {
			return true;
		}
		
	}
	
	function removerConta(obj){
		var form = document.forms[0];
		if (confirm('Confirma Remoção?')) {
			form.action = 'inserirRegistroAtendimentoWizardAction.do?action=exibirInserirRegistroAtendimentoDadosLocalOcorrenciaAction&removerConta='+obj;
			form.submit();
		}
	}
	
	function removerPagamento(obj){
		var form = document.forms[0];
		if (confirm('Confirma Remoção?')) {
			form.action = 'inserirRegistroAtendimentoWizardAction.do?action=exibirInserirRegistroAtendimentoDadosLocalOcorrenciaAction&removerPagamento='+obj;
			form.submit();
		}
	}
	
	
	function limparIdContaPesquisada() {
		var form = document.forms[0];
	   	form.idContaPesquisada.value = "";
	}
//-->
</SCRIPT>


</head>

<logic:notPresent name="msgAlert">
	<body leftmargin="5" 
		topmargin="5" 
		onload="setarFoco('${requestScope.nomeCampo}'); setLocalOcorrencia('${requestScope.localOcorrencia}'); verificarCamposOnLoad(); limitTextArea(document.forms[0].descricaoLocalOcorrencia, 200, document.getElementById('utilizadoLocalOcorrencia'), document.getElementById('limiteLocalOcorrencia')); limitTextArea(document.forms[0].parecerUnidadeDestino, 400, document.getElementById('utilizadoParecer'), document.getElementById('limiteParecer'));">
</logic:notPresent>

<logic:present name="msgAlert">
	
	<logic:present name="msgAlert2">
		<body leftmargin="5" topmargin="5" onload="setarFoco('${requestScope.nomeCampo}'); setLocalOcorrencia('${requestScope.localOcorrencia}'); alert('${requestScope.msgAlert}'); alert('${requestScope.msgAlert2}'); verificarCamposOnLoad('${requestScope.localOcorrencia}'); limitTextArea(document.forms[0].descricaoLocalOcorrencia, 200, document.getElementById('utilizadoLocalOcorrencia'), document.getElementById('limiteLocalOcorrencia')); limitTextArea(document.forms[0].parecerUnidadeDestino, 400, document.getElementById('utilizadoParecer'), document.getElementById('limiteParecer'));">
	</logic:present>
	
	<logic:notPresent name="msgAlert2">
		<body leftmargin="5" topmargin="5" onload="setarFoco('${requestScope.nomeCampo}'); setLocalOcorrencia('${requestScope.localOcorrencia}'); alert('${requestScope.msgAlert}'); verificarCamposOnLoad(); limitTextArea(document.forms[0].descricaoLocalOcorrencia, 200, document.getElementById('utilizadoLocalOcorrencia'), document.getElementById('limiteLocalOcorrencia')); limitTextArea(document.forms[0].parecerUnidadeDestino, 400, document.getElementById('utilizadoParecer'), document.getElementById('limiteParecer'));">
	</logic:notPresent>
	
</logic:present>

<!-- Disponibiliza os dados do cliente usuário do imóvel na Aba Nº 03 -->
<div id="formDiv">
<html:form action="/inserirRegistroAtendimentoWizardAction" method="post">

<!-- Parâmetro responsável pela formulação da tela de opção -->
<INPUT TYPE="hidden" name="telaOpcao" value="OK">

<html:hidden property="imovelObrigatorio"/>
<html:hidden property="pavimentoRuaObrigatorio"/>
<html:hidden property="pavimentoCalcadaObrigatorio"/>
<html:hidden property="tipoSolicitacao"/>

<html:hidden property="indRuaLocalOcorrencia"/>
<html:hidden property="indCalcadaLocalOcorrencia"/>
<html:hidden property="informarCep"/>

<html:hidden property="idContaPesquisada" />

<input type="hidden" name="enderecoPreenchido" value="${requestScope.enderecoPreenchido}"/>



<input type="hidden" name="solicitacaoTipoRelativoFaltaAgua" value="${sessionScope.solicitacaoTipoRelativoFaltaAgua}"/>
	<input type="hidden" name="solicitacaoTipoRelativoAreaEsgoto" value="${sessionScope.solicitacaoTipoRelativoAreaEsgoto}"/>

<jsp:include page="/jsp/util/wizard/navegacao_abas_wizard_valida_avancar_ra.jsp?numeroPagina=2"/>


<logic:notPresent scope="session" name="origemGIS">
	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp" %>
</logic:notPresent>


<table width="770" border="0" cellspacing="5" cellpadding="0">
  <tr>
	<logic:notPresent scope="session" name="origemGIS">
	  		<td width="140" valign="top" class="leftcoltext">
	</logic:notPresent>
	<logic:present scope="session" name="origemGIS">
		<td width="140" valign="top" class="leftcoltext" style="display:none;">
	</logic:present>
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

      <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_left.gif"/></td>
          <td class="parabg">Inserir Registro de Atendimento</td>
          <td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif"/></td>
        </tr>
      </table>
      <p>&nbsp;</p>

      <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
      	<td colspan="4" HEIGHT="40" align="center">
      		<span style="font-size: 18px;font-weight: bold;">
      		Nº Protocolo: ${sessionScope.protocoloAtendimento}</span></td>
      </tr>
      
      <tr>
      	<td colspan="3">Para inserir o registro de atendimento, informe os dados do local da ocorrência:</td>
		<logic:present scope="application" name="urlHelp">
								<td align="right"><a href="javascript:abrirPopupHelp('${applicationScope.urlHelp}atendimentoRegistroInserirAbaLocalOcorrencia', 500, 700);"><span style="font-weight: bold"><font color="#3165CE">Ajuda</font></span></a></td>									
		</logic:present>
		<logic:notPresent scope="application" name="urlHelp">
								<td align="right" ><span style="font-weight: bold"><font color=#696969><u>Ajuda</u></font></span></td>									
		</logic:notPresent>           
      </tr>
      
      <tr>
      	<td HEIGHT="30"><strong>Imóvel:&nbsp;&nbsp;</strong></td>
        <td>
			<html:text property="idImovel" size="9" maxlength="9" tabindex="1" onkeypress="validaEnterComMensagem(event, 'inserirRegistroAtendimentoWizardAction.do?action=exibirInserirRegistroAtendimentoDadosLocalOcorrenciaAction&pesquisarImovel=OK', 'idImovel', 'Matrícula do Imóvel');return isCampoNumerico(event);" onkeyup="habilitarDesabilitarDescricaoLocalOcorrencia();document.forms[0].inscricaoImovel.value='';"/>
			<a href="javascript:if(habilitarDesabilitarDadosLocalOcorrencia(document.forms[0].descricaoLocalOcorrencia)){abrirPopup('exibirPesquisarImovelAction.do', 490, 800);}">
			<img src="<bean:message key='caminho.imagens'/>pesquisa.gif" width="23" height="21" alt="Pesquisar" border="0" title="Pesquisar Imóvel"></a>

			<logic:present name="corImovel">

				<logic:equal name="corImovel" value="exception">
					<html:text property="inscricaoImovel" size="20" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000"/>
				</logic:equal>

				<logic:notEqual name="corImovel" value="exception">
					<html:text property="inscricaoImovel" size="20" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>
				</logic:notEqual>

			</logic:present>

			<logic:notPresent name="corImovel">

				<logic:empty name="InserirRegistroAtendimentoActionForm" property="idImovel">
					<html:text property="inscricaoImovel" value="" size="20" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000"/>
				</logic:empty>
				<logic:notEmpty name="InserirRegistroAtendimentoActionForm" property="idImovel">
					<html:text property="inscricaoImovel" size="20" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>
				</logic:notEmpty>
				

			</logic:notPresent>
        	
        	<a href="javascript:if(habilitarDesabilitarDadosLocalOcorrencia(document.forms[0].descricaoLocalOcorrencia)){limpar(1);}">
        	<img src="<bean:message key='caminho.imagens'/>limparcampo.gif" alt="Apagar" border="0" title="Apagar"></a>
        	
       	</td>
       	<td>
       		<input type="button" class="bottonRightCol" value="RAs Pendentes" tabindex="2"  id="botaoConsultarRAPendente" align="right" onclick="consultarRAsPendentes();" style="width: 100px;">	
       	</td>
       	<td>
       		<input type="button" class="bottonRightCol" value="Consultar D&eacute;bitos" id="botaoConsultarDebitos" align="right" onclick="consultarDebitos();" style="width: 109px;">
       	</td>
	  </tr>
	  </table>
     
     <table width="100%" border="0">
    	 <tr>
			<td width="15%">
				<strong>Conta:</strong>
			</td>					
			<td colspan="3">
				<logic:present name="conta" scope="session">
				
					<html:text property="idConta" style="text-transform: none;"
						size="8" maxlength="8" tabindex="6"
						onkeypress="limparIdContaPesquisada();mascaraAnoMes(this,event);validaContaImovel(event);" />
			
					<a href="javascript:pesquisarConta(document.forms[0].idImovel);">
						<img width="23" height="21" border="0"
							src="<bean:message key="caminho.imagens"/>pesquisa.gif"
							title="Pesquisar Conta" /></a> 
					
					<logic:notPresent name="contaEncontrada" scope="session">
						<html:text property="descConta" readonly="true"
							style="background-color:#EFEFEF; border:0; color: #ff0000"
							size="45" maxlength="40" />
					</logic:notPresent>
					
					<logic:present name="contaEncontrada" scope="session">
						<html:text property="descConta" readonly="true"
							style="background-color:#EFEFEF; border:0; color: #000000; text-align:left;"
							size="45" maxlength="40" />
					</logic:present>
									
					<a href="javascript:limparConta();">
						<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
							border="0" title="Limpar Conta" /> 
					</a>
				</logic:present>
				
				<logic:notPresent name="conta" scope="session">
				
					<html:text property="idConta" size="8"
						maxlength="8" tabindex="6" readonly="true" disabled="true"/>
			
					<img width="23" height="21" border="0"
						src="<bean:message key="caminho.imagens"/>pesquisa.gif"
						title="Pesquisar Conta" />
					
					<html:text property="descConta" readonly="true"
						style="background-color:#EFEFEF; border:0; color: #ff0000"
						size="40" maxlength="40" />
						
					<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Limpar Conta" /> 
				</logic:notPresent>
			</td> 
			<td>&nbsp;&nbsp;&nbsp;</td>
			<td>
			
				<logic:present name="conta" scope="session">
					<input type="button" name="Submit24"
						class="bottonRightCol" value="Adicionar"
						onClick="javascript:adicionarConta();"
						tabindex="7">
				</logic:present>
					
				<logic:notPresent name="conta" scope="session">
					<input type="button" name="Submit24"
						class="bottonRightCol" value="Adicionar"
						onClick="javascript:adicionarConta();"
						tabindex="7" disabled="true">
				</logic:notPresent>
			</td>
		</tr>




		<tr>
			<td colspan="6">
			<table width="100%" cellpadding="0" cellspacing="0">
				<tr bordercolor="#000000">
					<td bgcolor="#90c7fc" align="center" width="15%">
					<div align="center"><strong>Remover</strong></div>
					</td>
					<td bgcolor="#90c7fc" width="85%"><strong>Conta</strong></td>
				</tr>
				<logic:present name="colecaoConta">
					<tr>
						<td colspan="3">

						<div style="width: 100%; height: 100%; overflow: auto;">
						<table width="100%" bgcolor="#99CCFF">
							<tr>
							<logic:iterate name="colecaoConta"
								id="conta" type="Conta">
								<c:set var="count2" value="${count2+1}" />
								<c:choose>
									<c:when test="${count2%2 == '1'}">
										<tr bgcolor="#FFFFFF">
									</c:when>
									<c:otherwise>
										<tr bgcolor="#cbe5fe">
									</c:otherwise>
								</c:choose>
								<td width="15%">
									<div align="center"><font color="#333333"> <img width="14"
										height="14" border="0"
										src="<bean:message key="caminho.imagens"/>Error.gif"
									 	onclick="removerConta('${count2}')" />
									</font></div>
								</td>
								<td width="85%">
									<bean:write name="conta"
										property="formatarAnoMesParaMesAno" />
								</td>
							</logic:iterate>
							</tr>

							<tr bgcolor="#FFFFFF">
								<td width="15%">
									<div align="center" ></div>
								</td>
								<td width="80%" height="17">
								</td>
							</tr>
						</table>
						</div>
						</td>
					</tr>
				</logic:present>
			</table>
			</td>
		</tr>
   
		<logic:present name="colecaoPagamentosDuplicidade" scope="session">
			<tr>
				<td colspan="6">
					<table width="100%" cellpadding="0" cellspacing="0">
						<tr bordercolor="#79bbfd">
							<td colspan="4" align="center" bgcolor="#79bbfd">
								<strong>Pagamentos em Duplicidade</strong>
							</td>
						</tr>
						<tr bordercolor="#000000">
							<td bgcolor="#90c7fc" width="15%" align="center" rowspan="2"><strong>Remover</strong></td>
							<td bgcolor="#90c7fc" width="25%" align="center" rowspan="2"><strong>Mês/Ano Pag.</strong></td>
							<td bgcolor="#90c7fc" width="30%" align="center" rowspan="2"><strong>Data do Pag.</strong></td>
							<td bgcolor="#90c7fc" width="30%" align="center" rowspan="2"><strong>Valor do Pag.</strong></td>
						</tr>
					</table>
				</td>
			</tr>
			
			<tr>
				<td colspan="6">
					<table width="100%" bgcolor="#99CCFF">
						<%	BigDecimal valorTotalPagamentoDuplicidade = null;	%>

						<logic:iterate name="colecaoPagamentosDuplicidade"	id="pagamento" type="Pagamento">
               		  		<c:set var="count" value="${count+1}"/>
                       		<c:choose>
                       			<c:when test="${count%2 == '1'}">
                       				<tr bgcolor="#FFFFFF">
                       			</c:when>
                       			<c:otherwise>
                       				<tr bgcolor="#cbe5fe">
                       			</c:otherwise>
                       		</c:choose>
                       		
                       		<%	if(valorTotalPagamentoDuplicidade == null){
                         			valorTotalPagamentoDuplicidade = new BigDecimal(0.0);                       			
                       			}

								if(pagamento.getValorPagamento() != null){
	                       			valorTotalPagamentoDuplicidade = 
	                       				valorTotalPagamentoDuplicidade.add(pagamento.getValorPagamento()); 
								}	%>

                       		
	                    	<td width="15%" align="center">
	                        	<div align="center">
	                        		<img src="<bean:message key='caminho.imagens'/>Error.gif" width="14" height="14" 
								 		 style="cursor:hand;" 
								 		 name="imagem"	
								 		 onclick="removerPagamento('<bean:write name="pagamento"property="id"/>');"
								 		 alt="Remover">
								</div>
							</td>
							
	                        <td width="25%" align="center">
                       			${pagamento.formatarAnoMesPagamentoParaMesAno}
	                        </td>
	                        
							<td width="30%" align="center">
								<bean:write name="pagamento" 
									property="dataPagamento" 
									formatKey="date.format" />
							</td>
							
                        	<td width="30%" align="right">
                        		<bean:write name="pagamento" 
                        			property="valorPagamento" 
                        			formatKey="money.format" />
							</td>
	                        
						</logic:iterate>

						<% if (valorTotalPagamentoDuplicidade != null) {	%>
                			<tr>
	                    		<td colspan="3">
		                    		<div align="left">
		                    		<strong>Valor Total:</strong>
		                    		</div>
	                    		</td>
        		        		<td>
				                    <div align="right">
		   		              		<strong><%= Util.formatarMoedaReal(valorTotalPagamentoDuplicidade) %></strong>
				                    </div>
                  				</td>
                			</tr>
						<%	}	%>
						
					</table>
				</td>
			</tr>		
		
		</logic:present>   
   
      <tr>
         <td colspan="6" WIDTH="140">

			<table width="100%" border="0">
	      	<tr>
	      		<td><strong>Endereço da Ocorrência:</strong></td>
	      		<td align="right">
	      		
	      			<logic:present name="colecaoEnderecos">
		 
						<logic:empty name="colecaoEnderecos">
						      <input type="button" class="bottonRightCol" value="Adicionar" tabindex="3" id="botaoEndereco" onclick="javascript:if(habilitarDesabilitarDadosLocalOcorrencia(document.forms[0].descricaoLocalOcorrencia)){abrirPopupComSubmit('inserirRegistroAtendimentoWizardAction.do?action=exibirInserirRegistroAtendimentoDadosLocalOcorrenciaAction&tipoPesquisaEndereco=registroAtendimento&operacao=1', 570, 700, 'Endereco');}">
						      <input type="button" class="bottonRightCol" value="AcquaGIS" tabindex="3"  id="botaoGis" align="left" onclick="respostaGis();">
						</logic:empty>
						<logic:notEmpty name="colecaoEnderecos">
							<input type="button" class="bottonRightCol" value="Adicionar" tabindex="3" id="botaoEndereco" disabled>
						    <input type="button" class="bottonRightCol" value="AcquaGIS" tabindex="3" id="botaoGis" align="left" disabled>
						</logic:notEmpty>
				 
				 	</logic:present>
		
				 	<logic:notPresent name="colecaoEnderecos">
					
						<logic:present name="habilitarAlteracaoEndereco">
							<logic:equal name="habilitarAlteracaoEndereco" value="SIM">
								<input type="button" class="bottonRightCol" value="Adicionar" tabindex="3"  id="botaoEndereco" onclick="javascript:if(habilitarDesabilitarDadosLocalOcorrencia(document.forms[0].descricaoLocalOcorrencia)){abrirPopupComSubmit('inserirRegistroAtendimentoWizardAction.do?action=exibirInserirRegistroAtendimentoDadosLocalOcorrenciaAction&tipoPesquisaEndereco=registroAtendimento&operacao=1', 570, 700, 'Endereco');}">
								 <input type="button" class="bottonRightCol" value="AcquaGIS" tabindex="3"  id="botaoGis" align="left" onclick="respostaGis();">
						 	</logic:equal>
						 	<logic:notEqual name="habilitarAlteracaoEndereco" value="SIM">
						 		<input type="button" class="bottonRightCol" value="Adicionar" tabindex="3" id="botaoEndereco" disabled>
						 		<input type="button" class="bottonRightCol" value="AcquaGIS" tabindex="3" id="botaoGis" align="left" disabled>
						 	</logic:notEqual>
						</logic:present>
					
						<logic:notPresent name="habilitarAlteracaoEndereco">
							<input type="button" class="bottonRightCol" value="Adicionar" tabindex="3"  id="botaoEndereco" onclick="javascript:if(habilitarDesabilitarDadosLocalOcorrencia(document.forms[0].descricaoLocalOcorrencia)){abrirPopupComSubmit('inserirRegistroAtendimentoWizardAction.do?action=exibirInserirRegistroAtendimentoDadosLocalOcorrenciaAction&tipoPesquisaEndereco=registroAtendimento&operacao=1', 570, 700, 'Endereco');}">
							<input type="button" class="bottonRightCol" value="AcquaGIS" tabindex="3"  id="botaoGis" align="left" onclick="respostaGis();">
						</logic:notPresent>
				 	
				 	</logic:notPresent>
	      		
	      		</td>
	      	</tr>
		 	</table>
		 </td>
     </tr>
     <tr>
         <td colspan="6" height="50" valign="top">
			<table width="100%" cellpadding="0" cellspacing="0">
			<tr>
				<td>
					<table width="100%" border="0" bgcolor="#90c7fc">
					<tr bgcolor="#90c7fc" height="18">
						<td width="10%" align="center"><strong>Remover</strong></td>
						<td align="center"><strong>Endereço da Ocorrência</strong></td>
					</tr>
					</table>
				</td>
			</tr>

			<logic:present name="colecaoEnderecos">
			
			<input type="hidden" id="enderecoInformado">

			<tr>
				<td>
					<table width="100%" align="center" bgcolor="#99CCFF">
						<!--corpo da segunda tabela-->

						<% String cor = "#cbe5fe";%>

						<logic:iterate name="colecaoEnderecos" id="endereco">
						
							<%	if (cor.equalsIgnoreCase("#cbe5fe")){	
								cor = "#FFFFFF";%>
								<tr bgcolor="#FFFFFF" height="18">	
							<%} else{	
								cor = "#cbe5fe";%>
								<tr bgcolor="#cbe5fe" height="18">		
							<%}%>
						
								<td width="10%" align="center">
									<logic:equal name="habilitarAlteracaoEndereco" value="SIM">
										<a href="javascript:if(confirm('Confirma remoção?')){remover();}" alt="Remover"><img src="<bean:message key='caminho.imagens'/>Error.gif" width="14" height="14" border="0"></a>
									</logic:equal>
									
									<logic:notEqual name="habilitarAlteracaoEndereco" value="SIM">
										
										<logic:present name="enderecoPertenceImovel">
											<img src="<bean:message key='caminho.imagens'/>Error.gif" width="14" height="14" border="0">
										</logic:present>
										
										<logic:notPresent name="enderecoPertenceImovel">
											<a href="javascript:if(confirm('Confirma remoção?')){remover();}" alt="Remover"><img src="<bean:message key='caminho.imagens'/>Error.gif" width="14" height="14" border="0"></a>
										</logic:notPresent>
										
									</logic:notEqual>
								</td>
								
								<td>
									
									<logic:equal name="habilitarAlteracaoEndereco" value="SIM">
										<a href="javascript:abrirPopup('exibirInserirEnderecoAction.do?exibirEndereco=OK&tipoPesquisaEndereco=registroAtendimento&operacao=1&mostrarPerimetro=sim&origem=acquaGIS', 570, 700)"><bean:write name="endereco" property="enderecoFormatado"/></a>
									</logic:equal>
									
									<logic:notEqual name="habilitarAlteracaoEndereco" value="SIM">
										<bean:write name="endereco" property="enderecoFormatado"/> 
									</logic:notEqual>
								</td>
							</tr>
						</logic:iterate>

					</table>
			  	</td>
			</tr>

			</logic:present>

			</table>
	   </td>
   	  </tr>
  	  <logic:equal name="desabilitaMunicipioLocalidade" value="OK" scope="request">
   	  	<tr>
			<td>
				<strong>Município:</strong>
			</td>
			<td colspan="3">
				<html:text property="descricaoMunicipioOcorrencia" size="30"
					maxlength="60" tabindex="4" readonly="true" 
					style="background-color:#EFEFEF; border:0; color: #000000"/>
			</td>
		  </tr>
   	  </logic:equal>
   	  <tr>
   	  	<td><strong>Ponto de Referência:</strong></td>
        <td colspan="3">
			<html:text property="pontoReferencia" size="50" maxlength="60" tabindex="5" onkeyup="habilitarDesabilitarDescricaoLocalOcorrencia();"/>
		</td>
	  </tr>
	  
	  
	  <!-- ----------------------------------Coordenadas Gis-------------------------------------------- -->				
  	  <tr>
		<td height="24" colspan="4"><hr></td>
	  </tr>
	  <tr>
		<td height="24"><strong>Coordenada Norte:</strong></td>
		<td colspan="3">
			<html:text maxlength="16" property="nnCoordenadaNorte"
			style="text-align: right;" onkeyup="javaScript:formataValorGIS(this,16);" />
		</td>
	  </tr>
	  <tr>
		<td height="24"><strong>Coordenada Leste:</strong></td>
		<td colspan="3">
			<html:text maxlength="16" property="nnCoordenadaLeste"
			style="text-align: right;" onkeyup="javaScript:formataValorGIS(this,16);" />
		</td>

	  </tr>
	  <tr>
		<td height="24" colspan="4"><hr></td>
	  </tr>
	<!-- ------------------------------------------------------------------------------------------- -->
	  
	  <logic:equal name="solicitacaoTipoRelativoFaltaAgua" value="SIM">
	  <tr>
        <td><strong>Município:</strong></td>
        <td colspan="3">
        
        	<logic:present name="desabilitarMunicipioBairro">
        	
        		<html:text property="idMunicipio" size="5" maxlength="4" tabindex="6" readonly="true"/>
			
				<img width="23" height="21" border="0"
				src="<bean:message key="caminho.imagens"/>pesquisa.gif"
				title="Pesquisar Município" />
			
				<html:text property="descricaoMunicipio" size="42" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>
				
				
				<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
				border="0" title="Apagar" />
				
        	</logic:present>
        	
        	<logic:notPresent name="desabilitarMunicipioBairro">
			
				<html:text property="idMunicipio" size="5" maxlength="4" tabindex="7" onkeypress="limpar(2);validaEnterComMensagem(event, 'inserirRegistroAtendimentoWizardAction.do?action=exibirInserirRegistroAtendimentoDadosLocalOcorrenciaAction&pesquisarMunicipio=OK', 'idMunicipio', 'Município');return isCampoNumerico(event);" onkeyup="habilitarDesabilitarDescricaoLocalOcorrencia();"/>
				
				<a href="javascript:if(habilitarDesabilitarDadosLocalOcorrencia(document.forms[0].descricaoLocalOcorrencia)){abrirPopup('exibirPesquisarMunicipioAction.do', 250, 495);}">
					<img width="23" height="21" border="0"
					src="<bean:message key="caminho.imagens"/>pesquisa.gif"
					title="Pesquisar Município" /></a>
				
				<logic:present name="corMunicipio">
	
					<logic:equal name="corMunicipio" value="exception">
						<html:text property="descricaoMunicipio" size="42" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000"/>
					</logic:equal>
	
					<logic:notEqual name="corMunicipio" value="exception">
						<html:text property="descricaoMunicipio" size="42" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>
					</logic:notEqual>
	
				</logic:present>
	
				<logic:notPresent name="corMunicipio">
	
					<logic:empty name="InserirRegistroAtendimentoActionForm" property="idMunicipio">
						<html:text property="descricaoMunicipio" size="42" value="" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000"/>
					</logic:empty>
					<logic:notEmpty name="InserirRegistroAtendimentoActionForm" property="idMunicipio">
						<html:text property="descricaoMunicipio" size="42" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>
					</logic:notEmpty>
					
				</logic:notPresent>
				<a	href="javascript:if(habilitarDesabilitarDadosLocalOcorrencia(document.forms[0].descricaoLocalOcorrencia)){limpar(3);}">
					<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
					border="0" title="Apagar" /> </a>
					
			</logic:notPresent>
			
		</td>
      </tr>
      </logic:equal>
      
      <logic:notEqual name="solicitacaoTipoRelativoFaltaAgua" value="SIM">
	  <tr>
        <td><strong>Município:</strong></td>
        <td colspan="3">
			<html:text property="idMunicipio" size="5" maxlength="4" tabindex="5" readonly="true"/>
			
				<img width="23" height="21" border="0"
				src="<bean:message key="caminho.imagens"/>pesquisa.gif"
				title="Pesquisar Município" />
			
				<html:text property="descricaoMunicipio" size="42" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>
				
				
				<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
				border="0" title="Apagar" /> 
			</td>
      </tr>
      </logic:notEqual>
      
      
      
      <logic:equal name="solicitacaoTipoRelativoFaltaAgua" value="SIM">
      <tr>
		<td><strong>Bairro:</strong></td>
		<td colspan="3">
			
			<logic:present name="desabilitarMunicipioBairro">
			
				<html:text property="cdBairro" size="5" maxlength="4" tabindex="6" readonly="true"/>
			
				<img width="23" height="21" border="0"
				src="<bean:message key="caminho.imagens"/>pesquisa.gif"
				title="Pesquisar Bairro" />
			
				<html:text property="descricaoBairro" size="42" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>
				
				<html:hidden property="idBairro"/>
				<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
				border="0" title="Apagar" />
				
			</logic:present>
			
			<logic:notPresent name="desabilitarMunicipioBairro">
			
				<html:text property="cdBairro" size="5" maxlength="4" tabindex="6" onkeypress="validaEnterDependencia(event, 'inserirRegistroAtendimentoWizardAction.do?action=exibirInserirRegistroAtendimentoDadosLocalOcorrenciaAction&pesquisarBairro=OK', document.forms[0].cdBairro, document.forms[0].idMunicipio.value, 'Município');return isCampoNumerico(event);" onkeyup="limpar(11); habilitarDesabilitarDescricaoLocalOcorrencia();"/>
				
				<a	href="javascript:chamarPopup('exibirPesquisarBairroAction.do', 'bairro', 'idMunicipio', document.forms[0].idMunicipio.value, 275, 480, 'Informe o Município.');">
					<img width="23" height="21" border="0"
					src="<bean:message key="caminho.imagens"/>pesquisa.gif"
					title="Pesquisar Bairro" /></a>
				
				<logic:present name="corBairro">
	
					<logic:equal name="corBairro" value="exception">
						<html:text property="descricaoBairro" size="42" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000"/>
					</logic:equal>
	
					<logic:notEqual name="corBairro" value="exception">
						<html:text property="descricaoBairro" size="42" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>
					</logic:notEqual>
	
				</logic:present>
	
				<logic:notPresent name="corBairro">
	
					<logic:empty name="InserirRegistroAtendimentoActionForm" property="cdBairro">
						<html:text property="descricaoBairro" size="42" value="" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000"/>
					</logic:empty>
					<logic:notEmpty name="InserirRegistroAtendimentoActionForm" property="cdBairro">
						<html:text property="descricaoBairro" size="42" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>
					</logic:notEmpty>
					
				</logic:notPresent>
	
				<html:hidden property="idBairro"/>
				<a	href="javascript:if(habilitarDesabilitarDadosLocalOcorrencia(document.forms[0].descricaoLocalOcorrencia)){limpar(4);}">
					<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
					border="0" title="Apagar" /> </a>
					
			</logic:notPresent>
			
		</td>
	  </tr>
	  </logic:equal>
	    
	  <logic:notEqual name="solicitacaoTipoRelativoFaltaAgua" value="SIM">
	  <tr>
		<td><strong>Bairro:</strong></td>
		<td colspan="3">
				<html:text property="cdBairro" size="5" maxlength="4" tabindex="6" readonly="true"/>
			
				<img width="23" height="21" border="0"
				src="<bean:message key="caminho.imagens"/>pesquisa.gif"
				title="Pesquisar Bairro" />
			
				<html:text property="descricaoBairro" size="42" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>
				
				<html:hidden property="idBairro"/>
				<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
				border="0" title="Apagar" /> 
		</td>
	  </tr>
	  </logic:notEqual>
	    
	    
	    
	    <logic:equal name="solicitacaoTipoRelativoFaltaAgua" value="SIM">
	    <tr>
        <td><strong>Área do Bairro:</strong></td>
        <td colspan="3">
			<html:select property="idBairroArea" style="width: 200px;" tabindex="7" onchange="habilitarDesabilitarDescricaoLocalOcorrencia();">
				<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
				<logic:present name="colecaoBairroArea">
					<html:options collection="colecaoBairroArea" labelProperty="nome" property="id"/>
				</logic:present>
			</html:select>
		</td>
      	</tr>
      	</logic:equal>
      	
      	<logic:notEqual name="solicitacaoTipoRelativoFaltaAgua" value="SIM">
   	    <tr>
        <td><strong>Área do Bairro:</strong></td>
        <td colspan="3">
			<html:select property="idBairroArea" style="width: 200px;" tabindex="7" disabled="true">
				<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
			</html:select>
		</td>
      	</tr>
   	    </logic:notEqual>
   	    
   	    
   	    <tr>
      		<td colspan="4" height="20"></td>
      	</tr>
      	
   	    
   	    <tr>
		   <td><strong>Localidade:</strong></td>
           <td colspan="3">
           		
           		<logic:present name="desabilitarLcalidadeSetorQuadra">
	           		<html:text maxlength="3" property="idLocalidade" size="4"  tabindex="8" readonly="true"/>
	            	<img border="0" src="<bean:message key="caminho.imagens"/>pesquisa.gif" border="0" title="Pesquisar Localidade"/>
				</logic:present>
				
				<logic:notPresent name="desabilitarLcalidadeSetorQuadra">
	           		<html:text maxlength="3" property="idLocalidade" size="4"  tabindex="8" 
	            	onkeypress="limpar(5), validaEnterComMensagem(event, 'inserirRegistroAtendimentoWizardAction.do?action=exibirInserirRegistroAtendimentoDadosLocalOcorrenciaAction&pesquisarLocalidade=OK', 'idLocalidade', 'Localidade');return isCampoNumerico(event);" onkeyup="habilitarDesabilitarDescricaoLocalOcorrencia();"/>
	            	<a href="javascript:if(habilitarDesabilitarDadosLocalOcorrencia(document.forms[0].descricaoLocalOcorrencia)){abrirPopup('exibirPesquisarLocalidadeAction.do?tipo=imovelLocalidade', 400, 800); limpar(5);}">
	                <img border="0" src="<bean:message key="caminho.imagens"/>pesquisa.gif" border="0" title="Pesquisar Localidade"/></a>
				</logic:notPresent>

   		      	<logic:present name="corLocalidade">

					<logic:equal name="corLocalidade" value="exception">
						<html:text property="descricaoLocalidade" size="45" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000"/>
					</logic:equal>
	
					<logic:notEqual name="corLocalidade" value="exception">
						<html:text property="descricaoLocalidade" size="45" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>
					</logic:notEqual>

				</logic:present>

				<logic:notPresent name="corLocalidade">

					<logic:empty name="InserirRegistroAtendimentoActionForm" property="idLocalidade">
						<html:text property="descricaoLocalidade" size="45" value="" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000"/>
					</logic:empty>
					<logic:notEmpty name="InserirRegistroAtendimentoActionForm" property="idLocalidade">
						<html:text property="descricaoLocalidade" size="45" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>
					</logic:notEmpty>
				
				</logic:notPresent>
			
				<logic:present name="desabilitarLcalidadeSetorQuadra">
					<img
					src="<bean:message key="caminho.imagens"/>limparcampo.gif"
					border="0" title="Apagar" />
				</logic:present>
				
				<logic:notPresent name="desabilitarLcalidadeSetorQuadra">
					<a href="javascript:if(habilitarDesabilitarDadosLocalOcorrencia(document.forms[0].descricaoLocalOcorrencia)){limpar(8);}"> <img
					src="<bean:message key="caminho.imagens"/>limparcampo.gif"
					border="0" title="Apagar" /></a>
				</logic:notPresent>
				                   
			</td>
        </tr>
                
        <tr>
            <td><strong>Setor Comercial:</strong></td>
            <td colspan="3">
            
            	<logic:present name="desabilitarLcalidadeSetorQuadra">
            		<html:text maxlength="3" property="cdSetorComercial" size="4"  
                	tabindex="9" readonly="true"/>
                	<img border="0" src="<bean:message key="caminho.imagens"/>pesquisa.gif" border="0" title="Pesquisar Setor Comercial"/>
   		      	</logic:present>
   		      	
   		      	<logic:notPresent name="desabilitarLcalidadeSetorQuadra">
            		<html:text maxlength="3" property="cdSetorComercial" size="4"  
                	tabindex="9" onkeypress="validaEnterDependencia(event, 'inserirRegistroAtendimentoWizardAction.do?action=exibirInserirRegistroAtendimentoDadosLocalOcorrenciaAction&pesquisarSetorComercial=OK', this, document.forms[0].idLocalidade.value, 'Localidade');return isCampoNumerico(event);" onkeyup="limpar(6); habilitarDesabilitarDescricaoLocalOcorrencia();"/>
                	<a href="javascript:if(habilitarDesabilitarDadosLocalOcorrencia(document.forms[0].descricaoLocalOcorrencia)){abrirPopupDependencia('exibirPesquisarSetorComercialAction.do?idLocalidade='+document.forms[0].idLocalidade.value+'&tipo=SetorComercial',document.forms[0].idLocalidade.value,'Localidade', 400, 800);limpar(6);}">
					<img border="0" src="<bean:message key="caminho.imagens"/>pesquisa.gif" border="0" title="Pesquisar Setor Comercial"/></a>
   		      	</logic:notPresent>
   		      
   		      	<logic:present name="corSetorComercial">

					<logic:equal name="corSetorComercial" value="exception">
						<html:text property="descricaoSetorComercial" size="45" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000"/>
					</logic:equal>
	
					<logic:notEqual name="corSetorComercial" value="exception">
						<html:text property="descricaoSetorComercial" size="45" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>
					</logic:notEqual>

				</logic:present>

				<logic:notPresent name="corSetorComercial">

					<logic:empty name="InserirRegistroAtendimentoActionForm" property="cdSetorComercial">
						<html:text property="descricaoSetorComercial" size="45" value="" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000"/>
					</logic:empty>
					<logic:notEmpty name="InserirRegistroAtendimentoActionForm" property="cdSetorComercial">
						<html:text property="descricaoSetorComercial" size="45" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>
					</logic:notEmpty>
				
				</logic:notPresent>
				
				<html:hidden property="idSetorComercial"/>
				
				<logic:present name="desabilitarLcalidadeSetorQuadra">
					<img
					src="<bean:message key="caminho.imagens"/>limparcampo.gif"
					border="0" title="Apagar" />
            	</logic:present>
            	
            	<logic:notPresent name="desabilitarLcalidadeSetorQuadra">
					<a href="javascript:if(habilitarDesabilitarDadosLocalOcorrencia(document.forms[0].descricaoLocalOcorrencia)){limpar(9);}"> <img
					src="<bean:message key="caminho.imagens"/>limparcampo.gif"
					border="0" title="Apagar" /></a>
            	</logic:notPresent>
            	
            </td>
        </tr>
        <tr>
            <td><strong>Quadra:</strong></td>
            <td colspan="3">
            
            	<logic:present name="desabilitarLcalidadeSetorQuadra">
            		<html:text maxlength="4" property="nnQuadra" size="4"  tabindex="10" readonly="true"/>
				</logic:present>
				
				<logic:notPresent name="desabilitarLcalidadeSetorQuadra">
            		<html:text maxlength="4" property="nnQuadra" size="4"  tabindex="10"
                	onkeypress="validaEnterDependencia(event, 'inserirRegistroAtendimentoWizardAction.do?action=exibirInserirRegistroAtendimentoDadosLocalOcorrenciaAction&pesquisarQuadra=OK', this, document.forms[0].idSetorComercial.value, 'Setor Comercial');return isCampoNumerico(event);" onkeyup="limpar(7); habilitarDesabilitarDescricaoLocalOcorrencia();"/>
				</logic:notPresent>
				
				<html:hidden property="idQuadra"/>
				
				<logic:present name="msgQuadra" scope="request">
					<span style="color:#ff0000" id="msgQuadra"><bean:write scope="request" name="msgQuadra"/></span>
                </logic:present>

            </td>
        </tr>
        
        
        <logic:equal name="solicitacaoTipoRelativoAreaEsgoto" value="SIM">
        <tr>
        <td><strong>Divisão de Esgoto: </strong></td>
        <td colspan="3">
			
			<logic:present name="desabilitarDivisaoEsgoto">
			
				<html:select property="idDivisaoEsgoto" style="width: 200px;" tabindex="11" disabled="true">
					<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
					<logic:present name="colecaoDivisaoEsgoto">
						<html:options collection="colecaoDivisaoEsgoto" labelProperty="descricao" property="id"/>
					</logic:present>
				</html:select>
			
			</logic:present>
			
			<logic:notPresent name="desabilitarDivisaoEsgoto">
			
				<html:select property="idDivisaoEsgoto" style="width: 200px;" tabindex="11" onchange="verificarCompatibilidadeDivisaoEsgoto();">
					<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
					<logic:present name="colecaoDivisaoEsgoto">
						<html:options collection="colecaoDivisaoEsgoto" labelProperty="descricao" property="id"/>
					</logic:present>
				</html:select>
			
			</logic:notPresent>
			
		</td>
      	</tr>
      	</logic:equal>
      	
      	<logic:notEqual name="solicitacaoTipoRelativoAreaEsgoto" value="SIM">
      	<tr>
        <td><strong>Divisão de Esgoto:</strong></td>
        <td colspan="3">
			<html:select property="idDivisaoEsgoto" style="width: 200px;" tabindex="11" disabled="true">
				<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
				<logic:present name="colecaoDivisaoEsgoto">
					<html:options collection="colecaoDivisaoEsgoto" labelProperty="descricao" property="id"/>
				</logic:present>
			</html:select>
		</td>
      	</tr>
      	</logic:notEqual>
      	
      	
      	<tr>
		<td><strong>Unidade Destino:</strong></td>
		<td colspan="3">
			
			<logic:present name="desabilitarUnidadeDestino">
				<html:text property="idUnidadeDestino" size="5" maxlength="4" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>
			
				<img width="23" height="21" border="0"
				src="<bean:message key="caminho.imagens"/>pesquisa.gif"
				title="Pesquisar Unidade Destino" />
			
				<html:text property="descricaoUnidadeDestino" size="45" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>
				
				<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
				border="0" title="Apagar" />
				
			</logic:present>
			
			<logic:notPresent name="desabilitarUnidadeDestino">
				<html:text property="idUnidadeDestino" size="5" maxlength="4" tabindex="12" onkeypress="validaEnterComMensagem(event, 'inserirRegistroAtendimentoWizardAction.do?action=exibirInserirRegistroAtendimentoDadosLocalOcorrenciaAction&pesquisarUnidadeDestino=OK', 'idUnidadeDestino', 'Unidade Destino');return isCampoNumerico(event);" onkeyup="limpar(12); habilitarDesabilitarDescricaoLocalOcorrencia();"/>
				
				<a	href="javascript:if(habilitarDesabilitarDadosLocalOcorrencia(document.forms[0].descricaoLocalOcorrencia)){abrirPopup('exibirPesquisarUnidadeOrganizacionalAction.do', 410, 790);}">
					<img width="23" height="21" border="0"
					src="<bean:message key="caminho.imagens"/>pesquisa.gif"
					title="Pesquisar Unidade Destino" /></a>
				
				<logic:present name="corUnidadeDestino">
	
					<logic:equal name="corUnidadeDestino" value="exception">
						<html:text property="descricaoUnidadeDestino" size="45" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000"/>
					</logic:equal>
	
					<logic:notEqual name="corUnidadeDestino" value="exception">
						<html:text property="descricaoUnidadeDestino" size="45" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>
					</logic:notEqual>
	
				</logic:present>
	
				<logic:notPresent name="corUnidadeDestino">
	
					<logic:empty name="InserirRegistroAtendimentoActionForm" property="idUnidadeDestino">
						<html:text property="descricaoUnidadeDestino" size="45" value="" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000"/>
					</logic:empty>
					
					<logic:notEmpty name="InserirRegistroAtendimentoActionForm" property="idUnidadeDestino">
						<html:text property="descricaoUnidadeDestino" size="45" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>
					</logic:notEmpty>
					
				</logic:notPresent>
	
				<a	href="javascript:if(habilitarDesabilitarDadosLocalOcorrencia(document.forms[0].descricaoLocalOcorrencia)){limpar(10);}">
					<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
					border="0" title="Apagar" /> </a>
					
			</logic:notPresent>
			
			</td>
	  	</tr>
	  	
	  	<tr>
      		<td HEIGHT="30"><strong>Parecer Tramitação:</strong></td>
        	<td colspan="3">
				<html:textarea property="parecerUnidadeDestino" 
					cols="40" 
					rows="2" 
					onkeyup="habilitarDesabilitarDescricaoLocalOcorrencia(); limitTextArea(this, 400, document.getElementById('utilizadoParecer'), document.getElementById('limiteParecer'));"/><br>
				
				<strong><span id="utilizadoParecer">0</span>/<span id="limiteParecer">400</span></strong>
			</td>
      	</tr>
      	
      	
      	<tr>
      		<td colspan="4" height="20"></td>
      	</tr>
      	
      	
      	<tr>
        <td><strong>Local da Ocorrência:</strong></td>
        <td colspan="3">
			<html:select property="idLocalOcorrencia" style="width: 200px;" tabindex="13" onchange="habilitarDesabilitarDescricaoLocalOcorrencia();carregarIndicadorLocalOcorrencia(this);">
				<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
				<logic:present name="colecaoLocalOcorrencia">
					<logic:iterate id="localOcorrencia" name="colecaoLocalOcorrencia" type="LocalOcorrencia">
						<option value="<%=""+localOcorrencia.getId() %>" 
						id="<%=""+localOcorrencia.getIndicadorRua() %>"
						name="<%=""+localOcorrencia.getIndicadorCalcada() %>"><%=""+localOcorrencia.getDescricao() %></option>
					</logic:iterate>
				</logic:present>
			</html:select>
		</td>
      	</tr>
      	
      	<tr>
        <td><strong>Pavimento da Rua:</strong></td>
        <td colspan="3">
        
        	<logic:present name="desabilitarPavimentoRua">
				<html:select property="idPavimentoRua" style="width: 200px;" tabindex="14" disabled="true">
					<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
					<logic:present name="colecaoPavimentoRua">
						<html:options collection="colecaoPavimentoRua" labelProperty="descricao" property="id"/>
					</logic:present>
				</html:select>
			</logic:present>
			
			<logic:notPresent name="desabilitarPavimentoRua">
				<html:select property="idPavimentoRua" style="width: 200px;" tabindex="14" onchange="habilitarDesabilitarDescricaoLocalOcorrencia();">
					<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
					<logic:present name="colecaoPavimentoRua">
						<html:options collection="colecaoPavimentoRua" labelProperty="descricao" property="id"/>
					</logic:present>
				</html:select>
			</logic:notPresent>
			
		</td>
      	</tr>
      	
      	<tr>
        <td><strong>Pavimento da Calçada:</strong></td>
        <td colspan="3">
			
			<logic:present name="desabilitarPavimentoCalcada">
				<html:select property="idPavimentoCalcada" style="width: 200px;" tabindex="15" disabled="true">
					<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
					<logic:present name="colecaoPavimentoCalcada">
						<html:options collection="colecaoPavimentoCalcada" labelProperty="descricao" property="id"/>
					</logic:present>
				</html:select>
			</logic:present>
			
			<logic:notPresent name="desabilitarPavimentoCalcada">
				<html:select property="idPavimentoCalcada" style="width: 200px;" tabindex="15" onchange="habilitarDesabilitarDescricaoLocalOcorrencia();">
					<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
					<logic:present name="colecaoPavimentoCalcada">
						<html:options collection="colecaoPavimentoCalcada" labelProperty="descricao" property="id"/>
					</logic:present>
				</html:select>
			</logic:notPresent>
			
		</td>
      	</tr>
      	
      	
      	<tr>
      		<td colspan="4" height="20"></td>
      	</tr>
      	
      	<logic:present name="desabilitarDescricaoLocalOcorrencia">
	      	<tr>
	      		<td HEIGHT="30"><strong>Descrição do Local da Ocorrência:</strong></td>
	        	<td colspan="3">
					<html:textarea property="descricaoLocalOcorrencia" 
						cols="40" 
						rows="2" 
						readonly="true" 
						onkeyup="limitTextArea(this, 200, document.getElementById('utilizadoLocalOcorrencia'), document.getElementById('limiteLocalOcorrencia'));"/><br>
					<strong>
						<span id="utilizadoLocalOcorrencia">0</span>/<span id="limiteLocalOcorrencia">200</span>
					</strong>
				</td>
	      	</tr>
      	</logic:present>
      	
      	<logic:notPresent name="desabilitarDescricaoLocalOcorrencia">
	      	<tr>
	      		<td HEIGHT="30"><strong>Descrição Local Ocorrência:</strong></td>
	        	<td colspan="3">
					<html:textarea property="descricaoLocalOcorrencia" 
						cols="40" 
						rows="2" 
						onkeyup="habilitarDesabilitarDadosLocalOcorrencia(this); limitTextArea(this, 200, document.getElementById('utilizadoLocalOcorrencia'), document.getElementById('limiteLocalOcorrencia'));"/><br>
					
					<strong>
						<span id="utilizadoLocalOcorrencia">0</span>/<span id="limiteLocalOcorrencia">200</span>
					</strong>
				</td>
	      	</tr>
      	</logic:notPresent>
      	
        <tr>
      		<td colspan="4" height="10"></td>
      	</tr>
      	<tr>
        	<td colspan="4">
			<div align="right">
				<jsp:include page="/jsp/util/wizard/navegacao_botoes_wizard_valida_avancar_ra.jsp?numeroPagina=2"/>
			</div>
		</td>
      </tr>
      </table>
      <p>&nbsp;</p>
	</td>
  </tr>
</table>

<logic:notPresent scope="session" name="origemGIS">
	<%@ include file="/jsp/util/rodape.jsp"%>
</logic:notPresent>
</html:form>
</div>
<%@ include file="/jsp/util/telaespera.jsp"%>

<script>
document.getElementById('botaoConcluir').onclick = function() { botaoAvancarTelaEspera('/gsan/inserirRegistroAtendimentoWizardAction.do?concluir=true&action=inserirRegistroAtendimentoDadosLocalOcorrenciaAction'); }
</script>
<logic:present scope="session" name="origemGIS">
	<script>
		document.getElementById("Layer1").style.top = "5";
	</script>
</logic:present>

</html:html>
