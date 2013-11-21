<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>

<%@ page
	import="gcom.atendimentopublico.registroatendimento.LocalOcorrencia"%>
<%@ page import="gcom.atendimentopublico.registroatendimento.RegistroAtendimentoConta"%>
<%@ page import="gcom.atendimentopublico.registroatendimento.RegistroAtendimentoPagamentoDuplicidade"%>
<%@ page import="gcom.faturamento.conta.Conta" %>
<%@ page import="java.math.BigDecimal,gcom.util.Util" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>

<%@ include file="/jsp/util/telaespera.jsp"%>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="AtualizarRegistroAtendimentoActionForm"
	dynamicJavascript="false" />

<SCRIPT LANGUAGE="JavaScript">

<!--
window.onmousemove = verificarCamposHistoryBack;
	var bCancel = false; 

    function validateAtualizarRegistroAtendimentoActionForm(form) {                                                                   
        if (bCancel) 
      return true; 
        else 
       return validateCaracterEspecial(form) && validateInteger(form) && validateInteiroZeroPositivo(form) && validarCamposRequeridos(form); 
   } 

    function caracteresespeciais () { 
     this.ag = new Array("idImovel", "Imóvel possui caracteres especiais.", new Function ("varName", " return this[varName];"));
     this.ah = new Array("pontoReferencia", "Imóvel possui caracteres especiais.", new Function ("varName", " return this[varName];"));
     this.ai = new Array("idMunicipio", "Município possui caracteres especiais.", new Function ("varName", " return this[varName];"));
     this.aj = new Array("cdBairro", "Bairro possui caracteres especiais.", new Function ("varName", " return this[varName];"));
     this.ak = new Array("idLocalidade", "Localidade possui caracteres especiais.", new Function ("varName", " return this[varName];"));
     this.al = new Array("cdSetorComercial", "Setor Comercial possui caracteres especiais.", new Function ("varName", " return this[varName];"));
     this.am = new Array("nnQuadra", "Quadra possui caracteres especiais.", new Function ("varName", " return this[varName];"));
     this.ap = new Array("descricaoLocalOcorrencia", "Parecer possui caracteres especiais.", new Function ("varName", " return this[varName];"));
    } 

    function IntegerValidations () { 
     this.ab = new Array("idImovel", "Imóvel deve ser numérico(a).", new Function ("varName", " return this[varName];"));
     this.ac = new Array("idMunicipio", "Município deve ser numérico(a).", new Function ("varName", " return this[varName];"));
     this.ad = new Array("idLocalidade", "Localidade deve ser numérico(a).", new Function ("varName", " return this[varName];"));
     this.ae = new Array("cdSetorComercial", "Setor Comercial deve ser numérico(a).", new Function ("varName", " return this[varName];"));
     this.af = new Array("nnQuadra", "Quadra deve ser numérico(a).", new Function ("varName", " return this[varName];"));
    }
    
    
    function InteiroZeroPositivoValidations () { 
     this.aa = new Array("cdBairro", "Bairro deve somente conter números positivos ou zero.", new Function ("varName", " return this[varName];"));
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
    
    
    function obterIndicadorRuaLocalOcorrencia(){
    
    }
    
    
    function obterIndicadorCalcadaLocalOcorrencia(){
    
    }
    
	function limpar(situacao){
	
		var form = document.forms[0];
	
		switch (situacao){
	      case 1:
			   redirecionarSubmit("atualizarRegistroAtendimentoWizardAction.do?action=exibirAtualizarRegistroAtendimentoDadosLocalOcorrenciaAction&limparImovel=OK");
			   break;
		  case 2:
		   	   form.cdBairro.value = "";
		       form.descricaoBairro.value = "";
		       form.idBairro.value = "";
		       form.descricaoMunicipio.value = "";
		       
		       if (form.idBairroArea.options.length > 1){
		       		redirecionarSubmit("atualizarRegistroAtendimentoWizardAction.do?action=exibirAtualizarRegistroAtendimentoDadosLocalOcorrenciaAction&removerColecaoBairroArea=OK&campoFoco=idMunicipio");
		       }
		       
		       break;
		  case 3:
		       form.idMunicipio.value = "";
		       form.descricaoMunicipio.value = "";
		       form.cdBairro.value = "";
		       form.idBairro.value = "";
		       form.descricaoBairro.value = "";
 			   
 			   if (form.idBairroArea.options.length > 1){
		       		redirecionarSubmit("atualizarRegistroAtendimentoWizardAction.do?action=exibirAtualizarRegistroAtendimentoDadosLocalOcorrenciaAction&removerColecaoBairroArea=OK&campoFoco=idMunicipio");
		       }
		       
		       //Coloca o foco no objeto selecionado
		       form.idMunicipio.focus();
		       break;
		  case 4:
		   	   form.cdBairro.value = "";
		       form.idBairro.value = "";
		       form.descricaoBairro.value = "";
			   
			   if (form.idBairroArea.options.length > 1){
		       		redirecionarSubmit("atualizarRegistroAtendimentoWizardAction.do?action=exibirAtualizarRegistroAtendimentoDadosLocalOcorrenciaAction&removerColecaoBairroArea=OK&campoFoco=cdBairro");
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
		       form.idLocalidade.value = "";
		  	   form.descricaoLocalidade.value = "";
		       form.cdSetorComercial.value = "";
		       form.idSetorComercial.value = "";
		       form.descricaoSetorComercial.value = "";
		       form.idQuadra.value = "";
		       form.nnQuadra.value = "";
		       form.descricaoMunicipioOcorrencia.value = "";
		       
		       redirecionarSubmit("atualizarRegistroAtendimentoWizardAction.do?action=exibirAtualizarRegistroAtendimentoDadosLocalOcorrenciaAction&limparMunicipioOcorrencia=OK");
		       
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
		   	   form.idBairro.value = "";
		       form.descricaoBairro.value = "";
			   
			   if (form.idBairroArea.options.length > 1){
		       		redirecionarSubmit("atualizarRegistroAtendimentoWizardAction.do?action=exibirAtualizarRegistroAtendimentoDadosLocalOcorrenciaAction&removerColecaoBairroArea=OK&campoFoco=cdBairro");
		       }
		       
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
	  		
	  		redirecionarSubmit("atualizarRegistroAtendimentoWizardAction.do?action=exibirAtualizarRegistroAtendimentoDadosLocalOcorrenciaAction&pesquisarBairroArea=OK");
		}
		if (tipoConsulta == 'conta') {
      		form.idConta.value = descricaoRegistro;
	  		form.descConta.value = descricaoRegistro;
   			form.idContaPesquisada.value = idRegistro;
	  		
      		redirecionarSubmit("atualizarRegistroAtendimentoWizardAction.do?action=exibirAtualizarRegistroAtendimentoDadosLocalOcorrenciaAction&pesquisarConta=OK");
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
	  		redirecionarSubmit("atualizarRegistroAtendimentoWizardAction.do?action=exibirAtualizarRegistroAtendimentoDadosLocalOcorrenciaAction&pesquisarLocalidade=OK");
	  		
	  		limpar(6);
		}
		
		if (tipoConsulta == 'imovel') {
      		form.idImovel.value = codigoRegistro;

      		redirecionarSubmit("atualizarRegistroAtendimentoWizardAction.do?action=exibirAtualizarRegistroAtendimentoDadosLocalOcorrenciaAction&pesquisarImovel=OK");
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
					abrirPopup(url + "?" + "tipo=" + tipo + "&" + objeto + "=" + codigoObjeto + "&caminhoRetornoTelaPesquisaBairro=" + "/exibirAtualizarQuadra.do", altura, largura);
				}
			}	
		}
	}
	
	
	function verificarCompatibilidadeDivisaoEsgoto(){
		
		var form = document.forms[0];
		
		if (form.idDivisaoEsgoto.value > 0){
			redirecionarSubmit('atualizarRegistroAtendimentoWizardAction.do?action=exibirAtualizarRegistroAtendimentoDadosLocalOcorrenciaAction&verificarCompatibilidade=OK');
		}
		else{
			habilitarDesabilitarDescricaoLocalOcorrencia();
		}
	}
	
	//Remover Endereço
	function remover(){
		redirecionarSubmit('atualizarRegistroAtendimentoWizardAction.do?action=exibirAtualizarRegistroAtendimentoDadosLocalOcorrenciaAction&removerEndereco=OK');
	}
	
	function dadosLocalOcorrenciaInformados(){
		
		var retorno = false;
		var form = document.forms[0];
		
		for(indice = 0; indice < form.elements.length; indice++){
			
			if ((form.elements[indice].type == "text" ||  
				 form.elements[indice].type == "textarea") 
				 && form.elements[indice].name != "descricaoLocalOcorrencia" 
				 && form.elements[indice].name != "idUnidadeAtual"
				 && form.elements[indice].name != "descricaoUnidadeAtual"
				 && form.elements[indice].value.length > 0) {
				retorno = true;
				break;
			}
			else if (form.elements[indice].type == "select-one" && form.elements[indice].value > 0 && form.elements[indice].name != "ultimoacesso"){
				retorno = true;
				break;
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
				 && form.elements[indice].name != "idUnidadeAtual"
				 && form.elements[indice].name != "descricaoUnidadeAtual"
				 && form.elements[indice].style.border == ""
				 && campoReferencia.value.length > 0) {
				form.elements[indice].readOnly = true;
			}
			else if ((form.elements[indice].type == "text" ||  
				 form.elements[indice].type == "textarea") 
				 && form.elements[indice].name != "descricaoLocalOcorrencia"
				 && form.elements[indice].name != "idUnidadeAtual"
				 && form.elements[indice].name != "descricaoUnidadeAtual"
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
		
		//Lupas
		if (campoReferencia.value.length > 0){
			return false;
		}
		else{
			return true;
		}
	}
	//[SB0005] - Habilita/Desabilita Dados da Identificação do Local de Ocorrência e dados Solicitante
	
	function verificarCamposOnLoad(){
	 var form = document.forms[0];
	 
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
	       form.idDivisaoEsgoto.disabled= false;
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
	    
	 
	}
	
	function carregarIndicadorLocalOcorrencia(campo){
	var localOcorrencia = campo.options[campo.selectedIndex];
	var form = document.forms[0];
	form.indRuaLocalOcorrencia.value = localOcorrencia.id;
	form.indCalcadaLocalOcorrencia.value = localOcorrencia.nome;
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
	
	function limparDescricao(campoID,numeroLimpar){
	 if(!campoID.readOnly){
	  if(numeroLimpar != ''){
	   limpar(numeroLimpar);
	  }
	 }
	}
	
	function verificarCamposHistoryBack(){
	 var form = document.forms[0];
	 
	 
	 
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
	  //form.idDivisaoEsgoto.disabled = true;
	  
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
	     redirecionarSubmit('atualizarRegistroAtendimentoWizardAction.do?destino=2&action=exibirAtualizarRegistroAtendimentoDadosLocalOcorrenciaAction');		
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
			form.action = 'atualizarRegistroAtendimentoWizardAction.do?action=exibirAtualizarRegistroAtendimentoDadosLocalOcorrenciaAction&adicionarConta=OK';
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
	     		return validaEnterString(tecla, 'atualizarRegistroAtendimentoWizardAction.do?action=exibirAtualizarRegistroAtendimentoDadosLocalOcorrenciaAction&pesquisarConta=OK', 'idConta');
	     	}
		} else {
			return true;
		}
		
	}
	
	function removerConta(obj){
		var form = document.forms[0];
		if (confirm('Confirma Remoção?')) {
			form.action = 'atualizarRegistroAtendimentoWizardAction.do?action=exibirAtualizarRegistroAtendimentoDadosLocalOcorrenciaAction&removerConta='+obj;
			form.submit();
		}
	}
	
	
	function removerPagamento(obj){
		var form = document.forms[0];
		if (confirm('Confirma Remoção?')) {
			form.action = 'atualizarRegistroAtendimentoWizardAction.do?action=exibirAtualizarRegistroAtendimentoDadosLocalOcorrenciaAction&removerPagamento='+obj;
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
	<body leftmargin="5" topmargin="5"
		onload="setarFoco('${requestScope.nomeCampo}');verificarCamposOnLoad(); limitTextArea(document.forms[0].descricaoLocalOcorrencia, 200, document.getElementById('utilizadoLocalOcorrencia'), document.getElementById('limiteLocalOcorrencia'));">
</logic:notPresent>

<logic:present name="msgAlert">

	<logic:present name="msgAlert2">
		<body leftmargin="5" topmargin="5"
			onload="setarFoco('${requestScope.nomeCampo}'); alert('${requestScope.msgAlert}'); alert('${requestScope.msgAlert2}');verificarCamposOnLoad(); limitTextArea(document.forms[0].descricaoLocalOcorrencia, 200, document.getElementById('utilizadoLocalOcorrencia'), document.getElementById('limiteLocalOcorrencia'));">
	</logic:present>

	<logic:notPresent name="msgAlert2">
		<body leftmargin="5" topmargin="5"
			onload="setarFoco('${requestScope.nomeCampo}'); alert('${requestScope.msgAlert}');verificarCamposOnLoad(); limitTextArea(document.forms[0].descricaoLocalOcorrencia, 200, document.getElementById('utilizadoLocalOcorrencia'), document.getElementById('limiteLocalOcorrencia'));">
	</logic:notPresent>

</logic:present>

<div id="formDiv">
<html:form action="/atualizarRegistroAtendimentoWizardAction"
	method="post" >

	<!-- Parâmetro responsável pela formulação da tela de opção -->
	<INPUT TYPE="hidden" name="telaOpcao" value="OK">

	<html:hidden property="imovelObrigatorio" />
	<html:hidden property="pavimentoRuaObrigatorio" />
	<html:hidden property="pavimentoCalcadaObrigatorio" />
	<html:hidden property="tipoSolicitacao"/>

	<html:hidden property="indRuaLocalOcorrencia" />
	<html:hidden property="indCalcadaLocalOcorrencia" />
	<input type="hidden" name="solicitacaoTipoRelativoFaltaAgua" value="${sessionScope.solicitacaoTipoRelativoFaltaAgua}"/>
	<input type="hidden" name="solicitacaoTipoRelativoAreaEsgoto" value="${sessionScope.solicitacaoTipoRelativoAreaEsgoto}"/>

	<html:hidden property="idContaPesquisada" />
	
	<jsp:include
		page="/jsp/util/wizard/navegacao_abas_wizard_valida_avancar_ra.jsp?numeroPagina=2" />


	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>

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
					<td class="parabg">Atualizar Registro de Atendimento</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>

			<table width="100%" border="0">
				<tr>
					<td colspan="2">Para atualizar o registro de atendimento, informe
					os dados do local da ocorrência:</td>
					<logic:present scope="application" name="urlHelp">
						<td align="right"><a href="javascript:abrirPopupHelp('${applicationScope.urlHelp}atendimentoRegistroAtualizarAbaLocalOcorrencia', 500, 700);"><span style="font-weight: bold"><font color="#3165CE">Ajuda</font></span></a></td>									
					</logic:present>
					<logic:notPresent scope="application" name="urlHelp">
						<td align="right"><span style="font-weight: bold"><font color=#696969><u>Ajuda</u></font></span></td>									
					</logic:notPresent>
				</tr>
				<tr>
					<td HEIGHT="25" WIDTH="100"><strong>Imóvel:</strong></td>
					<td colspan="2">
					
						<html:text property="idImovel" size="10" maxlength="9"
							tabindex="1"
							onkeypress="validaEnterComMensagem(event, 'atualizarRegistroAtendimentoWizardAction.do?action=exibirAtualizarRegistroAtendimentoDadosLocalOcorrenciaAction&pesquisarImovel=OK', 'idImovel', 'Matrícula do Imóvel');return isCampoNumerico(event);"
							onkeyup="habilitarDesabilitarDescricaoLocalOcorrencia();document.forms[0].inscricaoImovel.value = '';" />
						<a href="javascript:if(habilitarDesabilitarDadosLocalOcorrencia(document.forms[0].descricaoLocalOcorrencia)){abrirPopup('exibirPesquisarImovelAction.do', 490, 800);}">
							<img src="<bean:message key='caminho.imagens'/>pesquisa.gif"
								width="23" height="21" alt="Pesquisar" border="0" title="Pesquisar Imóvel">
						</a>

						<logic:present name="corImovel">

							<logic:equal name="corImovel" value="exception">
								<html:text property="inscricaoImovel" size="22" readonly="true"
									style="background-color:#EFEFEF; border:0; color: #ff0000" />
							</logic:equal>

							<logic:notEqual name="corImovel" value="exception">
								<html:text property="inscricaoImovel" size="22" readonly="true"
									style="background-color:#EFEFEF; border:0; color: #000000" />
							</logic:notEqual>

						</logic:present>

						<logic:notPresent name="corImovel">

							<logic:empty name="AtualizarRegistroAtendimentoActionForm"
								property="idImovel">
								<html:text property="inscricaoImovel" value="" size="22"
									readonly="true"
									style="background-color:#EFEFEF; border:0; color: #ff0000" />
							</logic:empty>
							<logic:notEmpty name="AtualizarRegistroAtendimentoActionForm"
								property="idImovel">
								<html:text property="inscricaoImovel" size="22" readonly="true"
									style="background-color:#EFEFEF; border:0; color: #000000" />
							</logic:notEmpty>


						</logic:notPresent>


						<a href="javascript:if(document.forms[0].idImovel.value != '' && habilitarDesabilitarDadosLocalOcorrencia(document.forms[0].descricaoLocalOcorrencia)){limpar(1);}">
							<img src="<bean:message key='caminho.imagens'/>limparcampo.gif"
								alt="Apagar" border="0">
						</a>

						<input type="button" class="bottonRightCol" 
								value="RAs Pendentes"
								tabindex="2" 
								id="botaoConsultarRAPendente" align="right"
								onclick="consultarRAsPendentes();">					
					</td>
				</tr>
				<tr>
			         <td colspan="3" HEIGHT="10" align="right">
			         	<input type="button" class="bottonRightCol" value="Consultar D&eacute;bitos" id="botaoConsultarDebitos" align="right" onclick="consultarDebitos();">	
			         </td>
			     </tr>
				<tr>
					<td colspan="3" HEIGHT="10">
						<hr>
					</td>
				</tr>
				<tr>
					<td HEIGHT="25" WIDTH="100">
						<strong>Conta:</strong>
					</td>					
					<td colspan="2">
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
									size="40" maxlength="40" />
							</logic:notPresent>
							
							<logic:present name="contaEncontrada" scope="session">
								<html:text property="descConta" readonly="true"
									style="background-color:#EFEFEF; border:0; color: #000000; text-align:left;"
									size="40" maxlength="40" />
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
					    &nbsp;&nbsp;&nbsp;
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
					<td  colspan="3" height="50" valign="top">
					<table width="100%" cellpadding="0" cellspacing="0">
						<tr bordercolor="#000000">
							<td bgcolor="#90c7fc" align="center" width="15%">
							<div align="center"><strong>Remover</strong></div>
							</td>
							<td bgcolor="#90c7fc" width="85%"><strong>Conta</strong></td>
						</tr>
						<logic:present name="colecaoRAContasAtualizar">
							<tr>
								<td colspan="3">
		
								<div style="width: 100%; height: 100%; overflow: auto;">
								<table width="100%" bgcolor="#99CCFF">
									
									<logic:iterate name="colecaoRAContasAtualizar"
										id="registroAtendimentoConta" type="RegistroAtendimentoConta">
									<tr>																	
										<bean:define name="registroAtendimentoConta" property="conta" id="conta"/>
																	
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
												<div align="center"><font color="#333333">
													<logic:present name="conta" scope="session">
														<img width="14"
															height="14" border="0"
															src="<bean:message key="caminho.imagens"/>Error.gif"
														 	onclick="removerConta('${count2}')" />
												 	</logic:present>
												 	
													<logic:notPresent name="conta" scope="session">
														<img width="14"
															height="14" border="0"
															src="<bean:message key="caminho.imagens"/>Error.gif" />
													</logic:notPresent>
												</font></div>
											</td>
											
											<td width="85%">
												<bean:write name="conta"
													property="formatarAnoMesParaMesAno" />
											</td>
									</tr>
									</logic:iterate>
									
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
				<td colspan="5">
					<table width="100%" cellpadding="0" cellspacing="0">
						<tr bordercolor="#79bbfd">
							<td colspan="4" align="center" bgcolor="#79bbfd">
								<strong>Pagamentos em Duplicidade</strong>
							</td>
						</tr>
						<tr bordercolor="#000000">
							<td bgcolor="#90c7fc" width="15%" align="center" rowspan="2"><strong>Remover</strong></td>
							<td bgcolor="#90c7fc" width="25%" align="center" rowspan="2"><strong>Mês/Ano</strong></td>
							<td bgcolor="#90c7fc" width="30%" align="center" rowspan="2"><strong>Data do Pag.</strong></td>
							<td bgcolor="#90c7fc" width="30%" align="center" rowspan="2"><strong>Valor do Pag.</strong></td>
						</tr>
					</table>
				</td>
			</tr>
			
			<tr>
				<td colspan="5">
					<table width="100%" bgcolor="#99CCFF">
						<%	BigDecimal valorTotalPagamentoDuplicidade = null;	%>
						<logic:iterate name="colecaoPagamentosDuplicidade"	id="registroAtendimentoPagamentoDuplicidade" type="RegistroAtendimentoPagamentoDuplicidade">
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

								if(registroAtendimentoPagamentoDuplicidade.getValorPagamento() != null){
	                       			valorTotalPagamentoDuplicidade = 
	                       				valorTotalPagamentoDuplicidade.add(registroAtendimentoPagamentoDuplicidade.getValorPagamento()); 
								}	%>
                       		
                       		
	                    	<td width="15%" align="center">
	                        	<div align="center">
	                        		<img src="<bean:message key='caminho.imagens'/>Error.gif" width="14" height="14" 
								 		 style="cursor:hand;" 
								 		 name="imagem"	
								 		 onclick="removerPagamento('<%=registroAtendimentoPagamentoDuplicidade.getComp_id().getPagamentoId()%>');"
								 		 alt="Remover">
								</div>
							</td>
							
	                        <td width="15%" align="center">
                       			<%=registroAtendimentoPagamentoDuplicidade.getFormatarAnoMesPagamentoParaMesAno()%>
	                        </td>
	                        
							<td width="30%" align="center">
								<%=Util.formatarData(registroAtendimentoPagamentoDuplicidade.getDataPagamento())%>
							</td>
							
                        	<td width="30%" align="right">
								<%=Util.formatarMoedaReal(registroAtendimentoPagamentoDuplicidade.getValorPagamento())%>
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
					<td colspan="3" HEIGHT="10">
						<hr>
					</td>
				</tr>
				
				<tr>
					<td colspan="3">

					<table width="100%" border="0">
						<tr>
							<td><strong>Endereço da Ocorrência:</strong></td>
							<td align="right"><logic:present name="colecaoEnderecos">

								<logic:empty name="colecaoEnderecos">
									<input type="button" class="bottonRightCol" value="Adicionar"
										tabindex="3" id="botaoEndereco"
										onclick="javascript:if(habilitarDesabilitarDadosLocalOcorrencia(document.forms[0].descricaoLocalOcorrencia)){abrirPopup('exibirInserirEnderecoAction.do?tipoPesquisaEndereco=registroAtendimento&mostrarPerimetro=sim&operacao=3', 570, 700);}">
								</logic:empty>
								<logic:notEmpty name="colecaoEnderecos">
									<input type="button" class="bottonRightCol" value="Adicionar"
										tabindex="3" id="botaoEndereco" disabled>
								</logic:notEmpty>

							</logic:present> <logic:notPresent name="colecaoEnderecos">

								<logic:present name="habilitarAlteracaoEndereco">
									<logic:equal name="habilitarAlteracaoEndereco" value="SIM">
										<input type="button" class="bottonRightCol" value="Adicionar"
											tabindex="3" id="botaoEndereco"
											onclick="javascript:if(habilitarDesabilitarDadosLocalOcorrencia(document.forms[0].descricaoLocalOcorrencia)){abrirPopupComSubmit('atualizarRegistroAtendimentoWizardAction.do?action=exibirAtualizarRegistroAtendimentoDadosLocalOcorrenciaAction&tipoPesquisaEndereco=registroAtendimento&operacao=3', 570, 700, 'Endereco');}">
									</logic:equal>
									<logic:notEqual name="habilitarAlteracaoEndereco" value="SIM">
										<input type="button" class="bottonRightCol" value="Adicionar"
											tabindex="3" id="botaoEndereco" disabled>
									</logic:notEqual>
								</logic:present>

								<logic:notPresent name="habilitarAlteracaoEndereco">
									<input type="button" class="bottonRightCol" value="Adicionar"
										tabindex="3" id="botaoEndereco"
										onclick="javascript:if(habilitarDesabilitarDadosLocalOcorrencia(document.forms[0].descricaoLocalOcorrencia)){abrirPopupComSubmit('atualizarRegistroAtendimentoWizardAction.do?action=exibirAtualizarRegistroAtendimentoDadosLocalOcorrenciaAction&tipoPesquisaEndereco=registroAtendimento&operacao=3', 570, 700, 'Endereco');}">
								</logic:notPresent>

							</logic:notPresent></td>
						</tr>
					</table>
					</td>
				</tr>
				<tr>
					<td colspan="3" height="50" valign="top">
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

									<%String cor = "#cbe5fe";%>

									<logic:iterate name="colecaoEnderecos" id="endereco">

										<%if (cor.equalsIgnoreCase("#cbe5fe")) {
								cor = "#FFFFFF";%>
										<tr bgcolor="#FFFFFF" height="18">
											<%} else {
								cor = "#cbe5fe";%>
										<tr bgcolor="#cbe5fe" height="18">
											<%}%>

											<td width="10%" align="center"><logic:equal
												name="habilitarAlteracaoEndereco" value="SIM">
												<a
													href="javascript:if(confirm('Confirma remoção?')){remover();}"
													alt="Remover"><img
													src="<bean:message key='caminho.imagens'/>Error.gif"
													width="14" height="14" border="0"></a>
											</logic:equal> <logic:notEqual
												name="habilitarAlteracaoEndereco" value="SIM">
												<logic:present name="enderecoPertenceImovel">
											         <img src="<bean:message key='caminho.imagens'/>Error.gif" width="14" height="14" border="0">
										        </logic:present>
												<logic:notPresent name="enderecoPertenceImovel">
											     <a href="javascript:if(confirm('Confirma remoção?')){remover();}" alt="Remover"><img src="<bean:message key='caminho.imagens'/>Error.gif" width="14" height="14" border="0"></a>
										        </logic:notPresent>
											</logic:notEqual></td>

											<td><logic:equal name="habilitarAlteracaoEndereco"
												value="SIM">
												<a
													href="javascript:abrirPopup('exibirInserirEnderecoAction.do?exibirEndereco=OK&tipoPesquisaEndereco=registroAtendimento&operacao=3&mostrarPerimetro=sim', 570, 700)"><bean:write
													name="endereco" property="enderecoFormatado" /></a>
											</logic:equal> <logic:notEqual
												name="habilitarAlteracaoEndereco" value="SIM">
												<bean:write name="endereco" property="enderecoFormatado" />
											</logic:notEqual></td>
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
						<td colspan="2">
							<html:text property="descricaoMunicipioOcorrencia" size="30"
								maxlength="30" tabindex="4" readonly="true" 
								style="background-color:#EFEFEF; border:0; color: #000000"/>
						</td>
					</tr>
				</logic:equal>
				<tr>
					<td><strong>Referência:</strong></td>
					<td colspan="2"><html:text property="pontoReferencia" size="50"
						maxlength="60" tabindex="4"
						onkeyup="habilitarDesabilitarDescricaoLocalOcorrencia();" /></td>
				</tr>
<!-- ----------------------------------Coordenadas Gis-------------------------------------------- -->				
  			<tr>
					<td height="24" colspan="3">
					<hr>
					</td>
				</tr>
				<tr>
					<td height="24"><strong>Coordenada Norte:</strong></td>
					<td>
						<html:text maxlength="16" 
							property="nnCoordenadaNorte"
							style="text-align: right;"
							onkeyup="javaScript:formataValorGIS(this,16);" 
							onkeypress="return isCampoNumerico(event);"/>
					</td>
				</tr>
				<tr>
					<td height="24"><strong>Coordenada Leste:</strong></td>
					<td>
						<html:text maxlength="16" 
								property="nnCoordenadaLeste"
								style="text-align: right;"
								onkeyup="javaScript:formataValorGIS(this,16);" 
								onkeypress="return isCampoNumerico(event);"/>
					</td>
					 <td>
						<input type="button" class="bottonRightCol" value="AcquaGIS" tabindex="3"  id="botaoGis" align="left" onclick="respostaGis();">
					</td> 
				</tr>
				<tr>
					<td height="24" colspan="3">
					<hr>
					</td>
				</tr>
<!-- ------------------------------------------------------------------------------------------- -->
				<logic:equal name="solicitacaoTipoRelativoFaltaAgua" value="SIM">
					<tr>
						<td><strong>Município:</strong></td>
						<td colspan="2"><logic:present name="desabilitarMunicipioBairro">

							<html:text property="idMunicipio" 
									size="5" 
									maxlength="4"
									tabindex="5" 
									readonly="true" 
									onkeypress="return isCampoNumerico(event);"/>

							<img width="23" height="21" border="0"
								src="<bean:message key="caminho.imagens"/>pesquisa.gif"
								style="cursor: pointer;cursor:hand;"
								title="Pesquisar Município" />

							<html:text property="descricaoMunicipio" size="45"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />


							<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
								border="0" title="Apagar" style="cursor: pointer;cursor:hand;"/>

						</logic:present> <logic:notPresent
							name="desabilitarMunicipioBairro">

							<html:text property="idMunicipio" 
									size="5" 
									maxlength="4"
									tabindex="5"
									onkeypress="validaEnterComMensagem(event, 'atualizarRegistroAtendimentoWizardAction.do?action=exibirAtualizarRegistroAtendimentoDadosLocalOcorrenciaAction&pesquisarMunicipio=OK', 'idMunicipio', 'Município');return isCampoNumerico(event);"
									onkeyup="habilitarDesabilitarDescricaoLocalOcorrencia();limparDescricao(document.forms[0].idMunicipio,2);" />

							<a
								href="javascript:if(habilitarDesabilitarDadosLocalOcorrencia(document.forms[0].descricaoLocalOcorrencia)){abrirPopup('exibirPesquisarMunicipioAction.do', 250, 495);}">
							<img width="23" height="21" border="0"
								src="<bean:message key="caminho.imagens"/>pesquisa.gif"
								title="Pesquisar Município" style="cursor: pointer;cursor:hand;" /></a>

							<logic:present name="corMunicipio">

								<logic:equal name="corMunicipio" value="exception">
									<html:text property="descricaoMunicipio" size="45"
										readonly="true"
										style="background-color:#EFEFEF; border:0; color: #ff0000" />
								</logic:equal>

								<logic:notEqual name="corMunicipio" value="exception">
									<html:text property="descricaoMunicipio" size="45"
										readonly="true"
										style="background-color:#EFEFEF; border:0; color: #000000" />
								</logic:notEqual>

							</logic:present>

							<logic:notPresent name="corMunicipio">

								<logic:empty name="AtualizarRegistroAtendimentoActionForm"
									property="idMunicipio">
									<html:text property="descricaoMunicipio" size="45" value=""
										readonly="true"
										style="background-color:#EFEFEF; border:0; color: #ff0000" />
								</logic:empty>
								<logic:notEmpty name="AtualizarRegistroAtendimentoActionForm"
									property="idMunicipio">
									<html:text property="descricaoMunicipio" size="45"
										readonly="true"
										style="background-color:#EFEFEF; border:0; color: #000000" />
								</logic:notEmpty>

							</logic:notPresent>
							<a
								href="javascript:if(habilitarDesabilitarDadosLocalOcorrencia(document.forms[0].descricaoLocalOcorrencia)){limpar(3);}">
							<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
								border="0" title="Apagar" style="cursor: pointer;cursor:hand;"/> </a>

						</logic:notPresent></td>
					</tr>
				</logic:equal>

				<logic:notEqual name="solicitacaoTipoRelativoFaltaAgua" value="SIM">
					<tr>
						<td><strong>Município:</strong></td>
						<td colspan="2">
							<html:text property="idMunicipio" size="5"
								maxlength="4" 
								tabindex="5" 
								readonly="true" /> 
								<img width="23"
									height="21" border="0"
									src="<bean:message key="caminho.imagens"/>pesquisa.gif"
									title="Pesquisar Município" /> 
									<html:text property="descricaoMunicipio" size="45" readonly="true"
										style="background-color:#EFEFEF; border:0; color: #000000" /> <img
										src="<bean:message key="caminho.imagens"/>limparcampo.gif"
										border="0" title="Apagar" /></td>
					</tr>
				</logic:notEqual>



				<logic:equal name="solicitacaoTipoRelativoFaltaAgua" value="SIM">
					<tr>
						<td><strong>Bairro:</strong></td>
						<td colspan="2">
							<logic:present name="desabilitarMunicipioBairro">

							<html:text property="cdBairro" 
									size="5" 
									maxlength="4"
									tabindex="6" 
									readonly="true" 
									onkeypress="return isCampoNumerico(event);"/>

							<img width="23" height="21" border="0"
								src="<bean:message key="caminho.imagens"/>pesquisa.gif"
								title="Pesquisar Bairro" style="cursor: pointer;cursor:hand;"/>

							<html:text property="descricaoBairro" size="45" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />

							<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
								border="0" title="Apagar" style="cursor: pointer;cursor:hand;"/>

						</logic:present> <logic:notPresent
							name="desabilitarMunicipioBairro">

							<html:text property="cdBairro" size="5" maxlength="4"
								tabindex="6"
								onkeypress="validaEnterDependencia(event, 'atualizarRegistroAtendimentoWizardAction.do?action=exibirAtualizarRegistroAtendimentoDadosLocalOcorrenciaAction&pesquisarBairro=OK', document.forms[0].cdBairro, document.forms[0].idMunicipio.value, 'Município');return isCampoNumerico(event);"
								onkeyup="habilitarDesabilitarDescricaoLocalOcorrencia();limparDescricao(document.forms[0].cdBairro,10);" />

							<a
								href="javascript:chamarPopup('exibirPesquisarBairroAction.do', 'bairro', 'idMunicipio', document.forms[0].idMunicipio.value, 275, 480, 'Informe o Município.');">
							<img width="23" height="21" border="0"
								src="<bean:message key="caminho.imagens"/>pesquisa.gif"
								title="Pesquisar Bairro" style="cursor: pointer;cursor:hand;"/></a>

							<logic:present name="corBairro">

								<logic:equal name="corBairro" value="exception">
									<html:text property="descricaoBairro" size="45" readonly="true"
										style="background-color:#EFEFEF; border:0; color: #ff0000" />
								</logic:equal>

								<logic:notEqual name="corBairro" value="exception">
									<html:text property="descricaoBairro" size="45" readonly="true"
										style="background-color:#EFEFEF; border:0; color: #000000" />
								</logic:notEqual>

							</logic:present>

							<logic:notPresent name="corBairro">

								<logic:empty name="AtualizarRegistroAtendimentoActionForm"
									property="cdBairro">
									<html:text property="descricaoBairro" size="45" value=""
										readonly="true"
										style="background-color:#EFEFEF; border:0; color: #ff0000" />
								</logic:empty>
								<logic:notEmpty name="AtualizarRegistroAtendimentoActionForm"
									property="cdBairro">
									<html:text property="descricaoBairro" size="45" readonly="true"
										style="background-color:#EFEFEF; border:0; color: #000000" />
								</logic:notEmpty>

							</logic:notPresent>

							<html:hidden property="idBairro" />
							<a
								href="javascript:if(habilitarDesabilitarDadosLocalOcorrencia(document.forms[0].descricaoLocalOcorrencia)){limpar(4);}">
							<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
								border="0" title="Apagar" style="cursor: pointer;cursor:hand;"/> </a>

						</logic:notPresent></td>
					</tr>
				</logic:equal>

				<logic:notEqual name="solicitacaoTipoRelativoFaltaAgua" value="SIM">
					<tr>
						<td><strong>Bairro:</strong></td>
						<td colspan="2"><html:text property="cdBairro" size="5"
							maxlength="4" tabindex="6" readonly="true" /> <img width="23"
							height="21" border="0"
							src="<bean:message key="caminho.imagens"/>pesquisa.gif"
							title="Pesquisar Bairro" /> <html:text
							property="descricaoBairro" size="45" readonly="true"
							style="background-color:#EFEFEF; border:0; color: #000000" /> <img
							src="<bean:message key="caminho.imagens"/>limparcampo.gif"
							border="0" title="Apagar" /></td>
					</tr>
				</logic:notEqual>



				<logic:equal name="solicitacaoTipoRelativoFaltaAgua" value="SIM">
					<tr>
						<td><strong>Área do Bairro:</strong></td>
						<td colspan="2"><html:select property="idBairroArea"
							style="width: 200px;" tabindex="7"
							onchange="habilitarDesabilitarDescricaoLocalOcorrencia();">
							<html:option value="">&nbsp;</html:option>
							<logic:present name="colecaoBairroArea">
								<html:options collection="colecaoBairroArea"
									labelProperty="nome" property="id" />
							</logic:present>
						</html:select></td>
					</tr>
				</logic:equal>

				<logic:notEqual name="solicitacaoTipoRelativoFaltaAgua" value="SIM">
					<tr>
						<td><strong>Área do Bairro:</strong></td>
						<td colspan="2"><html:select property="idBairroArea"
							style="width: 200px;" tabindex="7" disabled="true">
							<html:option value="">&nbsp;</html:option>
							<logic:present name="colecaoBairroArea">
								<html:options collection="colecaoBairroArea"
									labelProperty="nome" property="id" />
							</logic:present>
						</html:select></td>
					</tr>
				</logic:notEqual>


				<tr>
					<td colspan="3" height="20"></td>
				</tr>


				<tr>
					<td><strong>Localidade:</strong></td>
					<td colspan="2"><html:text maxlength="3" property="idLocalidade"
						size="4" tabindex="8"
						onkeypress="validaEnterComMensagem(event, 'atualizarRegistroAtendimentoWizardAction.do?action=exibirAtualizarRegistroAtendimentoDadosLocalOcorrenciaAction&pesquisarLocalidade=OK', 'idLocalidade', 'Localidade');return isCampoNumerico(event);"
						onkeyup="habilitarDesabilitarDescricaoLocalOcorrencia();limparDescricao(document.forms[0].idLocalidade,'',5);" /> <a
						href="javascript:if(habilitarDesabilitarDadosLocalOcorrencia(document.forms[0].descricaoLocalOcorrencia)){abrirPopup('exibirPesquisarLocalidadeAction.do?tipo=imovelLocalidade', 400, 800); limpar(5);}">
					<img border="0"
						src="<bean:message key="caminho.imagens"/>pesquisa.gif" border="0" /></a>


					<logic:present name="corLocalidade">

						<logic:equal name="corLocalidade" value="exception">
							<html:text property="descricaoLocalidade" size="45"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:equal>

						<logic:notEqual name="corLocalidade" value="exception">
							<html:text property="descricaoLocalidade" size="45"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEqual>

					</logic:present> <logic:notPresent name="corLocalidade">

						<logic:empty name="AtualizarRegistroAtendimentoActionForm"
							property="idLocalidade">
							<html:text property="descricaoLocalidade" size="45" value=""
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:empty>
						<logic:notEmpty name="AtualizarRegistroAtendimentoActionForm"
							property="idLocalidade">
							<html:text property="descricaoLocalidade" size="45"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEmpty>

					</logic:notPresent> <a
						href="javascript:if(habilitarDesabilitarDadosLocalOcorrencia(document.forms[0].descricaoLocalOcorrencia)){limpar(8);}">
					<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar" /></a></td>
				</tr>

				<tr>
					<td><strong>Setor Comercial:</strong></td>
					<td colspan="2"><html:text maxlength="3"
						property="cdSetorComercial" size="4" tabindex="9"
						onkeypress="validaEnterDependencia(event, 'atualizarRegistroAtendimentoWizardAction.do?action=exibirAtualizarRegistroAtendimentoDadosLocalOcorrenciaAction&pesquisarSetorComercial=OK', this, document.forms[0].idLocalidade.value, 'Localidade');return isCampoNumerico(event);"
						onkeyup="habilitarDesabilitarDescricaoLocalOcorrencia();limparDescricao(document.forms[0].cdBairro,6);" />
					<a
						href="javascript:if(habilitarDesabilitarDadosLocalOcorrencia(document.forms[0].descricaoLocalOcorrencia)){abrirPopupDependencia('exibirPesquisarSetorComercialAction.do?idLocalidade='+document.forms[0].idLocalidade.value+'&tipo=SetorComercial',document.forms[0].idLocalidade.value,'Localidade', 400, 800);limpar(6);}">
					<img border="0"
						src="<bean:message key="caminho.imagens"/>pesquisa.gif" border="0" /></a>


					<logic:present name="corSetorComercial">

						<logic:equal name="corSetorComercial" value="exception">
							<html:text property="descricaoSetorComercial" size="45"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:equal>

						<logic:notEqual name="corSetorComercial" value="exception">
							<html:text property="descricaoSetorComercial" size="45"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEqual>

					</logic:present> <logic:notPresent name="corSetorComercial">

						<logic:empty name="AtualizarRegistroAtendimentoActionForm"
							property="cdSetorComercial">
							<html:text property="descricaoSetorComercial" size="45" value=""
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:empty>
						<logic:notEmpty name="AtualizarRegistroAtendimentoActionForm"
							property="cdSetorComercial">
							<html:text property="descricaoSetorComercial" size="45"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEmpty>

					</logic:notPresent> <html:hidden property="idSetorComercial" /> <a
						href="javascript:if(habilitarDesabilitarDadosLocalOcorrencia(document.forms[0].descricaoLocalOcorrencia)){limpar(9);}">
					<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar" /></a></td>
				</tr>
				<tr>
					<td><strong>Quadra:</strong></td>
					<td colspan="2"><html:text maxlength="4" property="nnQuadra"
						size="4" tabindex="10"
						onkeypress="validaEnterDependencia(event, 'atualizarRegistroAtendimentoWizardAction.do?action=exibirAtualizarRegistroAtendimentoDadosLocalOcorrenciaAction&pesquisarQuadra=OK', this, document.forms[0].idSetorComercial.value, 'Setor Comercial');return isCampoNumerico"
						onkeyup="habilitarDesabilitarDescricaoLocalOcorrencia();limparDescricao(document.forms[0].cdBairro,7);" />

					<html:hidden property="idQuadra" /> <logic:present name="msgQuadra"
						scope="request">
						<span style="color:#ff0000" id="msgQuadra"><bean:write
							scope="request" name="msgQuadra" /></span>
					</logic:present></td>
				</tr>


				<logic:equal name="solicitacaoTipoRelativoAreaEsgoto" value="SIM">
					<tr>
						<td><strong>Divisão de Esgoto:</strong></td>
						<td colspan="2"><html:select property="idDivisaoEsgoto"
							style="width: 200px;" tabindex="11"
							onchange="verificarCompatibilidadeDivisaoEsgoto();">
							<html:option value="">&nbsp;</html:option>
							<logic:present name="colecaoDivisaoEsgoto">
								<html:options collection="colecaoDivisaoEsgoto"
									labelProperty="descricao" property="id" />
							</logic:present>
						</html:select></td>
					</tr>
				</logic:equal>

				<logic:notEqual name="solicitacaoTipoRelativoAreaEsgoto" value="SIM">
					<tr>
						<td><strong>Divisão de Esgoto:</strong></td>
						<td colspan="2"><html:select property="idDivisaoEsgoto"
							style="width: 200px;" tabindex="11" disabled="true">
							<html:option value="">&nbsp;</html:option>
							<logic:present name="colecaoDivisaoEsgoto">
								<html:options collection="colecaoDivisaoEsgoto"
									labelProperty="descricao" property="id" />
							</logic:present>
						</html:select></td>
					</tr>
				</logic:notEqual>


				<tr>
					<td><strong>Unidade Atual:</strong></td>
					<td><html:text property="idUnidadeAtual" size="10" readonly="true"
						style="background-color:#EFEFEF; border:0; color: #000000" />
					&nbsp; <html:text property="descricaoUnidadeAtual" size="40"
						readonly="true"
						style="background-color:#EFEFEF; border:0; color: #000000" /></td>
				</tr>


				<tr>
					<td colspan="3" height="20"></td>
				</tr>


				<tr>
					<td><strong>Local da Ocorrência:</strong></td>
					<td colspan="2"><html:select property="idLocalOcorrencia"
						style="width: 200px;" tabindex="13"
						onchange="habilitarDesabilitarDescricaoLocalOcorrencia();carregarIndicadorLocalOcorrencia(this);">
						<html:option value="">&nbsp;</html:option>
						<logic:present name="colecaoLocalOcorrencia">
							<logic:iterate id="localOcorrencia" name="colecaoLocalOcorrencia"
								type="LocalOcorrencia">
								<option value="<%=""+localOcorrencia.getId() %>"
									id="<%=""+localOcorrencia.getIndicadorRua() %>"
									name="<%=""+localOcorrencia.getIndicadorCalcada() %>"><%=""
										+ localOcorrencia.getDescricao()%></option>
							</logic:iterate>
						</logic:present>
					</html:select></td>
				</tr>

				<tr>
					<td><strong>Pavimento da Rua:</strong></td>
					<td colspan="2"><html:select property="idPavimentoRua"
						style="width: 200px;" tabindex="14"
						onchange="habilitarDesabilitarDescricaoLocalOcorrencia();">
						<html:option value="">&nbsp;</html:option>
						<logic:present name="colecaoPavimentoRua">
							<html:options collection="colecaoPavimentoRua"
								labelProperty="descricao" property="id" />
						</logic:present>
					</html:select></td>
				</tr>

				<tr>
					<td><strong>Pavimento da Calçada:</strong></td>
					<td colspan="2"><html:select property="idPavimentoCalcada"
						style="width: 200px;" tabindex="15"
						onchange="habilitarDesabilitarDescricaoLocalOcorrencia();">
						<html:option value="">&nbsp;</html:option>
						<logic:present name="colecaoPavimentoCalcada">
							<html:options collection="colecaoPavimentoCalcada"
								labelProperty="descricao" property="id" />
						</logic:present>
					</html:select></td>
				</tr>


				<tr>
					<td colspan="3" height="20"></td>
				</tr>

				<logic:present name="desabilitarDescricaoLocalOcorrencia">
					<tr>
						<td HEIGHT="30"><strong>Descrição:</strong></td>
						<td><html:textarea property="descricaoLocalOcorrencia" cols="40"
							rows="2" readonly="true" onkeyup="limitTextArea(this, 200, document.getElementById('utilizadoLocalOcorrencia'), document.getElementById('limiteLocalOcorrencia'));"/><br>
							<strong><span id="utilizadoLocalOcorrencia">0</span>/<span id="limiteLocalOcorrencia">200</span></strong></td>
					</tr>
				</logic:present>

				<logic:notPresent name="desabilitarDescricaoLocalOcorrencia">
					<tr>
						<td HEIGHT="30"><strong>Descrição:</strong></td>
						<td><logic:notEmpty name="AtualizarRegistroAtendimentoActionForm"
							property="idImovel">
							<logic:notEmpty name="AtualizarRegistroAtendimentoActionForm"
								property="descricaoLocalOcorrencia">
								<html:textarea property="descricaoLocalOcorrencia" cols="40"
									rows="2" onkeyup="limitTextArea(this, 200, document.getElementById('utilizadoLocalOcorrencia'), document.getElementById('limiteLocalOcorrencia'));"/><br>
								<strong><span id="utilizadoLocalOcorrencia">0</span>/<span id="limiteLocalOcorrencia">200</span></strong>
							</logic:notEmpty>
							<logic:empty name="AtualizarRegistroAtendimentoActionForm"
								property="descricaoLocalOcorrencia">
								<html:textarea property="descricaoLocalOcorrencia" cols="40"
									rows="2" readonly="true"
									onkeyup="habilitarDesabilitarDadosLocalOcorrencia(this); limitTextArea(this, 200, document.getElementById('utilizadoLocalOcorrencia'), document.getElementById('limiteLocalOcorrencia'));" /><br>
								<strong><span id="utilizadoLocalOcorrencia">0</span>/<span id="limiteLocalOcorrencia">200</span></strong>
							</logic:empty>
						</logic:notEmpty> <logic:empty
							name="AtualizarRegistroAtendimentoActionForm" property="idImovel">
							<html:textarea property="descricaoLocalOcorrencia" cols="40"
								rows="2"
								onkeyup="habilitarDesabilitarDadosLocalOcorrencia(this); limitTextArea(this, 200, document.getElementById('utilizadoLocalOcorrencia'), document.getElementById('limiteLocalOcorrencia'));" /><br>
							<strong><span id="utilizadoLocalOcorrencia">0</span>/<span id="limiteLocalOcorrencia">200</span></strong>
						</logic:empty></td>
					</tr>
				</logic:notPresent>

				<tr>
					<td colspan="3" height="10"></td>
				</tr>
				<tr>
					<td colspan="3">
					<div align="right"><jsp:include
						page="/jsp/util/wizard/navegacao_botoes_wizard_valida_avancar_ra.jsp?numeroPagina=2" />
					</div>
					</td>
				</tr>
			</table>
			<p>&nbsp;</p>
			</td>
		</tr>
	</table>

	<%@ include file="/jsp/util/rodape.jsp"%>
<script>
convertePonto(document.forms[0].nnCoordenadaNorte);
convertePonto(document.forms[0].nnCoordenadaLeste);
</script>
</html:form>
</div>

</html:html>
