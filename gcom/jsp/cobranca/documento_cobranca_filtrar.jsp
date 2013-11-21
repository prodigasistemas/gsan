<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan" %>

<%@ page import="gcom.util.ConstantesSistema"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<head>
<html:html>

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="FiltrarDocumentosCobrancaActionForm" />
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js"></script>
<SCRIPT LANGUAGE="JavaScript">

<!--

function limparMsgQuadraInexistente() {
	var msgQuadraOrigem = document.getElementById("msgQuadraOrigem");
	var msgQuadraDestino = document.getElementById("msgQuadraDestino");
	
	if (msgQuadraOrigem != null){
		msgQuadraOrigem.innerHTML = "";
	}
	if (msgQuadraDestino != null){
		msgQuadraDestino.innerHTML = "";
	}

}

function replicaDados(campoOrigem, campoDestino){
	campoDestino.value = campoOrigem.value;
}


function recuperarDadosQuatroParametros(idRegistro, descricaoRegistro, codigoRegistro, tipoConsulta) {

	var form = document.FiltrarDocumentosCobrancaActionForm;

	if (tipoConsulta == 'setorComercialOrigem') {
    	form.setorComercialOrigemCD.value = codigoRegistro;
      	form.setorComercialOrigemID.value = idRegistro;
	  	form.nomeSetorComercialOrigem.value = descricaoRegistro;
	  	form.nomeSetorComercialOrigem.style.color = "#000000";
	  	form.setorComercialDestinoCD.value = codigoRegistro;
      	form.setorComercialDestinoID.value = idRegistro;
	  	form.nomeSetorComercialDestino.value = descricaoRegistro;
	  	form.nomeSetorComercialDestino.style.color = "#000000"; 
	}

	if (tipoConsulta == 'setorComercialDestino') {
      	form.setorComercialDestinoCD.value = codigoRegistro;
      	form.setorComercialDestinoID.value = idRegistro;
	  	form.nomeSetorComercialDestino.value = descricaoRegistro;
	  	form.nomeSetorComercialDestino.style.color = "#000000"; 
	}

	if (tipoConsulta == 'quadraOrigem') {
      	form.quadraOrigemNM.value = codigoRegistro;
	  	form.quadraOrigemID.value = idRegistro;
	  	form.quadraDestinoNM.value = codigoRegistro;
	  	form.quadraDestinoID.value = idRegistro;
    }

	if (tipoConsulta == 'quadraDestino') {
      	form.quadraDestinoNM.value = codigoRegistro;
	  	form.quadraDestinoID.value = idRegistro;
	}

}

function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {

	var form = document.FiltrarDocumentosCobrancaActionForm;

	if (tipoConsulta == 'localidadeOrigem') {
   		form.localidadeOrigemID.value = codigoRegistro;
	  	form.nomeLocalidadeOrigem.value = descricaoRegistro;
	  	form.localidadeDestinoID.value = codigoRegistro;
      	form.nomeLocalidadeDestino.value = descricaoRegistro;
	  	form.nomeLocalidadeOrigem.style.color = "#000000";
	}

	if (tipoConsulta == 'localidade') {
      	form.localidadeDestinoID.value = codigoRegistro;
      	form.nomeLocalidadeDestino.value = descricaoRegistro;
	  	form.nomeLocalidadeDestino.style.color = "#000000";
	}


	if(tipoConsulta == 'imovel'){
        form.idImovel.value = codigoRegistro;
        
        document.FiltrarDocumentosCobrancaActionForm.action = '/gsan/exibirFiltrarDocumentosCobrancaAction.do?bloquear=todos';
        document.FiltrarDocumentosCobrancaActionForm.submit();
    }
	
	

}


function validarForm(form){
   	// Campos relacionados a inscrição de origem
	var localidadeOrigem = form.localidadeOrigemID;
	var setorComercialOrigem = form.setorComercialOrigemCD;
	var quadraOrigem = form.quadraOrigemNM;
	var loteOrigem = form.loteOrigem;
	var subloteOrigem = form.subloteOrigem;

	// Campos relacionados a inscrição de destino
	var localidadeDestino = form.localidadeDestinoID;
	var setorComercialDestino = form.setorComercialDestinoCD;
	var quadraDestino = form.quadraDestinoNM;
	var loteDestino = form.loteDestino;
	var subloteDestino = form.subloteDestino;

	var obrigatorio = false;

	//Origem
	if (localidadeOrigem.value.length < 1){
		alert("Informe Localidade Inicial");
		localidadeOrigem.focus();
		obrigatorio = true;
	}else if (!testarCampoValorZero(localidadeOrigem, "Localidade Inicial")){
		localidadeOrigem.focus();
		obrigatorio = true;
	}else if (setorComercialOrigem.value.length > 0 && 
			  !testarCampoValorZero(setorComercialOrigem, "Setor Comercial Inicial")){

		setorComercialOrigem.focus();
		obrigatorio = true;
	}else if (quadraOrigem.value.length > 0 && !testarCampoValorZero(quadraOrigem, "Quadra Inicial")){
		quadraOrigem.focus();
		obrigatorio = true;
	}else{
		obrigatorio = campoObrigatorio(setorComercialOrigem, quadraOrigem, "Informe Setor de Comercial Origem");
		if (!obrigatorio){
			obrigatorio = campoObrigatorio(quadraOrigem, loteOrigem, "Informe Quadra Inicial");
		}
		if (!obrigatorio){
			obrigatorio = campoObrigatorio(loteOrigem, subloteOrigem, "Informe Lote Inicial");
		}
	}

	//Destino
	if (!obrigatorio){
		if (localidadeDestino.value.length < 1){
			alert("Informe Localidade Final");
			localidadeDestino.focus();
			obrigatorio = true;
		}else if (!testarCampoValorZero(localidadeDestino, "Localidade Final")){
			localidadeDestino.focus();
			obrigatorio = true;
		}else if (setorComercialDestino.value.length > 0 && 
			  !testarCampoValorZero(setorComercialDestino, "Setor Comercial Final")){

			setorComercialDestino.focus();
			obrigatorio = true;
		}else if (quadraDestino.value.length > 0 && !testarCampoValorZero(quadraDestino, "Quadra Final")){
			quadraDestino.focus();
			obrigatorio = true;
		}else{
			obrigatorio = campoObrigatorio(setorComercialDestino, quadraDestino, "Informe Setor Comercial Destino");
			if (!obrigatorio){
				obrigatorio = campoObrigatorio(quadraDestino, loteDestino, "Informe Quadra Final");
			}
			if (!obrigatorio){
			obrigatorio = campoObrigatorio(loteDestino, subloteDestino, "Informe Lote Final");
		
		}
		}
	}
	

	//Origem - Destino
	if (!obrigatorio){
		obrigatorio = campoObrigatorio(setorComercialOrigem,setorComercialDestino,"Informe Setor Comercial Inicial");
		if (!obrigatorio){
			obrigatorio = campoObrigatorio(quadraOrigem,quadraDestino, "Informe Quadra Inicial");
			if (!obrigatorio){
				obrigatorio = campoObrigatorio(loteOrigem,loteDestino, "Informe Lote Inicial");
				if (!obrigatorio){
				obrigatorio = campoObrigatorio(subloteOrigem, subloteDestino, "Informe Sublote Inicial");
			}
			}
		}
	}
	//Destino - Origem
	if (!obrigatorio){
		obrigatorio = campoObrigatorio(setorComercialDestino, setorComercialOrigem, "Informe Setor Comercial Final");
		if (!obrigatorio){
			obrigatorio = campoObrigatorio(quadraDestino, quadraOrigem, "Informe Quadra Final");
			if (!obrigatorio){
				obrigatorio = campoObrigatorio(loteDestino, loteOrigem, "Informe Lote Final");
				if (!obrigatorio){
				obrigatorio = campoObrigatorio(subloteDestino, subloteOrigem, "Informe Sublote Final");
			}
			}
		}
	}
	
	// Confirma a validação do formulário
	if (!obrigatorio && validateFiltrarDocumentosCobrancaActionForm(form)){
		return true;
			
	}else{
		return false;
	}

}

function limpaLocal(){
	limparBorrachaOrigem(1, this);
	//bloquearM();
	
}

function campoObrigatorio(campoDependencia, dependente, msg){
	if (dependente.value.length < 1){
		return false
	}
	else if (campoDependencia.value.length < 1){
		alert(msg);
		campoDependencia.focus();
		return true
	}
}

function chamarPopup(url, tipo, objeto, codigoObjeto, altura, largura, msg){

	if (objeto == null || codigoObjeto == null){
		abrirPopup(url + "?" + "tipo=" + tipo, altura, largura);
	}
	else{
		if (codigoObjeto.length < 1 || isNaN(codigoObjeto)){
			alert(msg);
		}
		else{
			abrirPopup(url + "?" + "tipo=" + tipo + "&Popup=sim&" + objeto + "=" + codigoObjeto + "", altura, largura);
		}
	}
}

function validarLocalidade(){
	var form = document.FiltrarDocumentosCobrancaActionForm;
	if( form.localidadeOrigemID.value == form.localidadeDestinoID.value ){
		form.setorComercialOrigemID.readOnly = false;
		form.setorComercialDestinoID.readOnly = false;
	}
	else if( form.localidadeOrigemID.value != form.localidadeDestinoID.value ){
		form.setorComercialOrigemID.readOnly = true;
		form.setorComercialDestinoID.readOnly = true;
		form.setorComercialOrigemID.value = '';
		form.setorComercialDestinoID.value = '';
		form.quadraOrigemID.value = '';
		form.quadraDestinoID.value = '';
	}
	else if( form.setorComercialOrigemID.value != form.setorComercialDestinoID.value ){
			form.quadraOrigemID.readOnly = false;
			form.quadraDestinoID.readOnly = false;
	}
}

//tiago moreno
function limpar(tipo){

	var form = document.FiltrarDocumentosCobrancaActionForm;
   
	switch (tipo){
	//Tiago Moreno
     case 1:
     		
		   form.idImovel.value = "";
		   form.inscricaoImovel.value = "";
		   form.endereco.value = "";
		   //document.getElementById("endereco").innerHTML = "&nbsp;";
		   bloquearLSQLS();
		   break;   
	   case 2:
		   form.setorComercialOrigemCD.value = "";
		   form.setorComercialOrigemID.value = "";
		   form.nomeSetorComercialOrigem.value = "";
		   form.quadraOrigemNM.value = "";
		   form.quadraOrigemID.value = "";
		   
		   //Coloca o foco no objeto selecionado
		   form.setorComercialOrigemCD.focus();
		   break;
		   //Tiago Moreno
	   case 3:
		   form.localidadeDestinoID.value = "";
		   form.nomeLocalidadeDestino.value = "";
		   form.setorComercialDestinoCD.value = "";
		   form.setorComercialDestinoID.value = "";
		   form.nomeSetorComercialDestino.value = "";
		   form.quadraDestinoNM.value = "";
		   form.quadraDestinoID.value = "";
		   form.localidadeOrigemID.value = "";
		   form.nomeLocalidadeOrigem.value = "";
		   form.setorComercialOrigemCD.value = "";
		   form.setorComercialOrigemID.value = "";
		   form.nomeSetorComercialOrigem.value = "";
		   form.quadraOrigemNM.value = "";
		   form.quadraOrigemID.value = "";
		   

		   break;  
	   case 4:
		   form.setorComercialDestinoCD.value = "";
		   form.setorComercialDestinoID.value = "";
		   form.nomeSetorComercialDestino.value = "";
		   form.quadraDestinoNM.value = "";
		   form.quadraDestinoID.value = "";
		   
		   //Coloca o foco no objeto selecionado
		   form.setorComercialDestinoCD.focus();
		   break;
		case 5:
		   form.quadraOrigemNM.value = "";
		   form.quadraOrigemID.value = "";
		   
		   //Coloca o foco no objeto selecionado
		   form.quadraOrigemNM.focus();
		   break;
		case 6:
		   form.quadraDestinoNM.value = "";
		   form.quadraDestinoID.value = "";
		   
		   //Coloca o foco no objeto selecionado
		   form.quadraDestinoNM.focus();
		   break;
		case 7:
		   form.setorComercialOrigemCD.value = "";
		   form.setorComercialOrigemID.value = "";
		   form.nomeSetorComercialOrigem.value = "";
		   form.quadraOrigemNM.value = "";
		   form.quadraOrigemID.value = "";

		   form.loteOrigem.value = "";
		   form.subloteOrigem.value = "";
		   //Coloca o foco no objeto selecionado
		   form.localidadeOrigemID.focus();
		   break;
		case 8:
		   form.quadraOrigemNM.value = "";
		   form.quadraOrigemID.value = "";
		   
		   form.loteOrigem.value = "";
		   form.subloteOrigem.value = "";

		   //Coloca o foco no objeto selecionado
		   form.setorComercialOrigemCD.focus();
		   break;
		case 9:
		   form.setorComercialDestinoCD.value = "";
		   form.setorComercialDestinoID.value = "";
		   form.nomeSetorComercialDestino.value = "";
		   form.quadraDestinoNM.value = "";
		   form.quadraDestinoID.value = "";
		   
		   form.loteDestino.value = "";
		   form.subloteDestino.value = "";

		   //Coloca o foco no objeto selecionado
		   form.localidadeDestinoID.focus();
		   break;
		 case 10:
		   form.quadraDestinoNM.value = "";
		   form.quadraDestinoID.value = "";
		   
		   form.loteDestino.value = "";
		   form.subloteDestino.value = "";

		   //Coloca o foco no objeto selecionado
		   form.setorComercialDestinoCD.focus();
		   break;
	   case 11:
		   window.location.href = '/gsan/exibirFiltrarDocumentosCobrancaAction.do?menu=sim';
		   break;    
	   default:
          break;
	}
}

function jogarPopup(tipo){

	var form = document.FiltrarDocumentosCobrancaActionForm;
   
	switch (tipo){
	//Tiago Moreno
		case 1:
		  if (form.idGerenciaRegional.value == "" && form.localidadeOrigemID.value == ""){
		  	chamaPopupImovel();
		  }
		  break;
		case 2:
		  if (form.idGerenciaRegional.value == "" && form.idImovel.value == ""){
		  	chamarPopup('exibirPesquisarLocalidadeAction.do', 'origem', null, null, 275, 480, '');
		  }
		  break;
		case 3:
		  if (form.idGerenciaRegional.value == "" && form.idImovel.value == ""){
		  	chamarPopup('exibirPesquisarLocalidadeAction.do', 'destino', null, null, 275, 480, '');
		  }
		  break;
		case 4:
		  if (form.idGerenciaRegional.value == "" && form.idImovel.value == ""){
		  	chamarPopup('exibirPesquisarLocalidadeAction.do', 'origem', null, null, 275, 480, '');
		  }
		  break;
		case 5:
		  if (form.idGerenciaRegional.value == "" && form.idImovel.value == ""){
		  	chamarPopup('exibirPesquisarLocalidadeAction.do', 'origem', null, null, 275, 480, '');
		  }
		  break;
	}
}

function bloquearELSQ(){
	var form = document.FiltrarDocumentosCobrancaActionForm;

	if(form.idGerenciaRegional.value != "" ){
	
	//if(form.idGerenciaRegional.selectedIndex != 0 ){
	
		form.localidadeOrigemID.readOnly = true;
		form.localidadeOrigemID.value = "";

		form.setorComercialOrigemCD.readOnly = true;
		form.setorComercialOrigemCD.value = "";
		form.quadraOrigemNM.readOnly = true;
		form.quadraOrigemNM.value = "";
		form.loteOrigem.readOnly = true;
		form.loteOrigem.value = "";
		form.subloteOrigem.readOnly = true;
		form.subloteOrigem.value = "";
		form.idImovel.readOnly = true;
		form.idImovel.value = "";
		form.idUnidadeNegocio.disabled = true;
		form.idUnidadeNegocio.value = "";		
		
		form.localidadeDestinoID.readOnly = true;
		form.localidadeDestinoID.value = "";
		form.setorComercialDestinoCD.readOnly = true;
		form.setorComercialDestinoCD.value = "";
		form.quadraDestinoNM.readOnly = true;
		form.quadraDestinoNM.value = "";
		form.loteDestino.readOnly = true;
		form.loteDestino.value = "";
		form.subloteDestino.readOnly = true;
		form.subloteDestino.value = "";
		limpar(3);
	
		//return false;
	}else{
		form.localidadeOrigemID.readOnly = false;
		form.setorComercialOrigemCD.readOnly = false;
		form.quadraOrigemNM.readOnly = false;
		form.subloteOrigem.readOnly = false;
		form.idImovel.readOnly = false;
		form.localidadeDestinoID.readOnly = false;
		form.setorComercialDestinoCD.readOnly = false;
		form.quadraDestinoNM.readOnly = false;
		form.idUnidadeNegocio.disabled = false;				
 
	}
}

function bloquearESLSQ(){
	var form = document.FiltrarDocumentosCobrancaActionForm;

	if(form.idUnidadeNegocio.value != ""){
	
	//if(form.idGerenciaRegional.selectedIndex != 0 ){
	
		form.localidadeOrigemID.readOnly = true;
		form.localidadeOrigemID.value = "";

		form.setorComercialOrigemCD.readOnly = true;
		form.setorComercialOrigemCD.value = "";
		form.quadraOrigemNM.readOnly = true;
		form.quadraOrigemNM.value = "";
		form.loteOrigem.readOnly = true;
		form.loteOrigem.value = "";
		form.subloteOrigem.readOnly = true;
		form.subloteOrigem.value = "";
		form.idImovel.readOnly = true;
		form.idImovel.value = "";
		form.idGerenciaRegional.disabled = true;
		form.idGerenciaRegional.value = "";		
		
		form.localidadeDestinoID.readOnly = true;
		form.localidadeDestinoID.value = "";
		form.setorComercialDestinoCD.readOnly = true;
		form.setorComercialDestinoCD.value = "";
		form.quadraDestinoNM.readOnly = true;
		form.quadraDestinoNM.value = "";
		form.loteDestino.readOnly = true;
		form.loteDestino.value = "";
		form.subloteDestino.readOnly = true;
		form.subloteDestino.value = "";
		limpar(3);
	
		//return false;
	}else{
	    //alert(form.subloteDestino.readOnly);
		form.localidadeOrigemID.readOnly = false;
		form.setorComercialOrigemCD.readOnly = false;
		form.quadraOrigemNM.readOnly = false;
		form.subloteOrigem.readOnly = false;
		form.idImovel.readOnly = false;
		form.localidadeDestinoID.readOnly = false;
		form.setorComercialDestinoCD.readOnly = false;
		form.quadraDestinoNM.readOnly = false;
		form.idGerenciaRegional.disabled = false;				
 
	}
}



 function chamaPopupImovel(){
 	var form = document.InserirMensagemContaActionForm;
	abrirPopup('exibirPesquisarImovelAction.do');
 }



function bloquearLSQLS(){
	var form = document.FiltrarDocumentosCobrancaActionForm;

	if(form.idImovel.value != "" ){//se matricula imovel nao for vazio entao deshabilita todos os texts
	
		form.localidadeOrigemID.readOnly = true;
		form.localidadeOrigemID.value = "";
		form.setorComercialOrigemCD.readOnly = true;
		form.setorComercialOrigemCD.value = "";
		form.quadraOrigemNM.readOnly = true;
		form.quadraOrigemNM.value = "";
		form.loteOrigem.readOnly = true;
		form.loteOrigem.value = "";
		form.subloteOrigem.readOnly = true;
		form.subloteOrigem.value = "";
		form.idGerenciaRegional.disabled = true;
		form.idGerenciaRegional.value = "";
		form.idUnidadeNegocio.disabled = true;		
		form.idUnidadeNegocio.value = "";
		
		form.localidadeDestinoID.readOnly = true;
		form.localidadeDestinoID.value = "";
		form.setorComercialDestinoCD.readOnly = true;
		form.setorComercialDestinoCD.value = "";
		form.quadraDestinoNM.readOnly = true;
		form.quadraDestinoNM.value = "";
		form.loteDestino.readOnly = true;
		form.loteDestino.value = "";
		form.subloteDestino.readOnly = true;	
		form.subloteDestino.value = "";
		
		limpar(3);
	
		return false;
	}
	if(form.idImovel.value == ""){//se matricula imovel for vazio entao habilita todos os texts
		
		if(document.getElementById("endereco") != null){
			document.getElementById("endereco").innerHTML = '&nbsp;';
			form.endereco.value = "";
		}

		form.inscricaoImovel.value = "";
		form.localidadeOrigemID.readOnly = false;
		form.setorComercialOrigemCD.readOnly = false;
		form.quadraOrigemNM.readOnly = false;
		form.loteOrigem.readOnly = false;
		form.subloteOrigem.readOnly = false;
		form.idGerenciaRegional.disabled = false;
		form.idUnidadeNegocio.disabled = false;		
		
		form.localidadeDestinoID.readOnly = false;
		form.setorComercialDestinoCD.readOnly = false;
		form.quadraDestinoNM.readOnly = false;
		form.loteDestino.readOnly = false;
		form.subloteDestino.readOnly = false;
		form.idImovel.readeOnly = false;

		//verificarBotoes();
	}
}
function bloquearM(){
	var form = document.FiltrarDocumentosCobrancaActionForm;
	if(form.localidadeOrigemID.value != ""){
	 form.idImovel.readOnly = true;
	 form.idGerenciaRegional.disabled = true;
	 form.idUnidadeNegocio.disabled = true; 
	}
	if(form.localidadeOrigemID.value == ""){
	 form.idImovel.readOnly = false;
	 form.idGerenciaRegional.disabled = false;
	 form.idUnidadeNegocio.disabled = false; 	 
	  //verificarBotoes();		 
	}
}

function bloquearM2(){
	var form = document.FiltrarDocumentosCobrancaActionForm;
	if(form.localidadeOrigemID.value != ""){
	 form.idImovel.readOnly = true;
	 form.idGerenciaRegional.disabled = true;
	 form.idUnidadeNegocio.disabled = true;	 
	}
}


function limparDestino(tipo){
	var form = document.FiltrarDocumentosCobrancaActionForm;

	switch(tipo){
		case 1: //De localidade pra baixo
			//form.localidadeDestinoID.value = "";
			form.nomeLocalidadeDestino.value = "";		
			form.setorComercialOrigemCD.value = "";
			form.setorComercialOrigemID.value = "";
			form.nomeSetorComercialOrigem.value = "";
		    form.setorComercialDestinoCD.value = "";
		case 2: //De setor para baixo		   
		   form.setorComercialDestinoID.value = ""; 
		   form.nomeSetorComercialDestino.value = "";		   
		   form.quadraOrigemNM.value = "";
		   form.quadraOrigemID.value = "";
		   form.loteOrigem.value = "";
		   form.loteDestino.value = "";
		case 3://De quadra pra baixo
           form.loteDestino.value = "";
           form.loteOrigem.value = "";                        		
		   form.quadraDestinoNM.value = "";
		   form.quadraDestinoID.value = "";
        case 4:
		   form.loteDestino.value = "";		   

	}
}

function limparOrigem(tipo){
	var form = document.FiltrarDocumentosCobrancaActionForm;

		
	switch(tipo){
		case 1: //De localidara pra baixo
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
		case 3://De quadra pra baixo
		   form.quadraOrigemNM.value = "";
		   form.quadraOrigemID.value = "";
		   form.loteOrigem.value = "";

		   form.quadraDestinoNM.value = "";
		   form.quadraDestinoID.value = "";
		   form.loteDestino.value = "";
		   limparMsgQuadraInexistente();
	}
}

function limparBorrachaOrigem(tipo, event){
	var form = document.FiltrarDocumentosCobrancaActionForm;
   
		
	switch(tipo){
		case 1: //De localidade pra baixo
			form.localidadeOrigemID.value = "";
			form.nomeLocalidadeOrigem.value = "";
			//form.idImovel.readOnly = false;
			form.idImovel.disabled = false;
			form.idGerenciaRegional.disabled = false;
			form.idUnidadeNegocio.disabled = false;			
			form.localidadeDestinoID.value = "";
			form.nomeLocalidadeDestino.value = "";
			form.setorComercialOrigemCD.value = "";	
		    form.setorComercialOrigemID.value = "";
		    form.nomeSetorComercialOrigem.value = "";
		    form.setorComercialDestinoCD.value = "";
		    form.setorComercialDestinoID.value = "";		   
		    form.nomeSetorComercialDestino.value = "";
			form.quadraOrigemNM.value = "";
		    form.quadraOrigemID.value = "";
		    form.quadraDestinoNM.value = "";
		    form.quadraDestinoID.value = "";
			limparMsgQuadraInexistente();
			//verificarBotoes();
		case 2: //De setor para baixo
		   form.setorComercialOrigemCD.value = "";
		   form.setorComercialOrigemID.value = "";
		   form.nomeSetorComercialOrigem.value = "";
		   form.setorComercialDestinoCD.value = "";
		   form.setorComercialDestinoID.value = "";		   
		   form.nomeSetorComercialDestino.value = "";
		   form.quadraOrigemNM.value = "";
		   form.quadraOrigemID.value = "";
		   form.quadraDestinoNM.value = "";
		   form.quadraDestinoID.value = "";
		   limparMsgQuadraInexistente();
		case 3://De quadra pra baixo
		   form.quadraOrigemNM.value = "";
		   form.quadraOrigemID.value = "";
		   form.loteOrigem.value = "";
		   form.subloteOrigem.value = "";
		   form.quadraDestinoNM.value = "";
		   form.quadraDestinoID.value = "";
		   form.loteDestino.value = "";
		   form.subloteDestino.value = "";
		   limparMsgQuadraInexistente();
		case 4:
			if (event.keyCode != 13 && event.keyCode != 9){
				if (form.localidadeOrigemID.value == ""){
					form.idImovel.readOnly = false;
					form.idGerenciaRegional.disabled = false;
			    }
			}
	}
}



function limparBorrachaDestino(tipo){
	var form = document.FiltrarDocumentosCobrancaActionForm;

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
		   form.quadraOrigemNM.value = "";
		   form.quadraOrigemID.value = "";

		case 3://De quadra pra baixo
		   form.quadraDestinoNM.value = "";
		   form.quadraDestinoID.value = "";
	}
}

//Tiago Moreno
 function replicarLote(){
    var form = document.FiltrarDocumentosCobrancaActionForm;
	form.loteDestino.value = form.loteOrigem.value;
  }
 //Tiago Moreno 
  function replicarSublote(){
    var form = document.FiltrarDocumentosCobrancaActionForm;
	form.subloteDestino.value = form.subloteOrigem.value;
  }

//Tiago Moreno
function limpaCampoSeVazio(campo,funcao){
  if(campo.value == ""){
    eval(funcao);
 } 
}
//Tiago Moreno
function limparSubloteOrigem(){
var form = document.FiltrarDocumentosCobrancaActionForm;
 if(form.loteOrigem.value == ""){
  form.loteDestino.value = "";
  form.subloteDestino.value = "";
  form.subloteOrigem.value = "";  
 }
}

//Tiago Moreno 
function limparSubloteDestino(){
var form = document.FiltrarDocumentosCobrancaActionForm;
if(form.loteDestino.value == ""){
  form.subloteDestino.value = "";
  form.subloteOrigem.value = "";
  }
}


function desabilitaIntervaloDiferente(tipo){
	var form = document.SituacaoEspecialCobrancaActionForm;
	
	switch(tipo){
		case 1: //De localidade 
		    if(form.localidadeOrigemID.value != form.localidadeDestinoID.value){
		        limparBorrachaOrigem(2);
		        form.setorComercialOrigemCD.readOnly = true;
			 	form.quadraOrigemNM.readOnly = true;
		     	form.loteOrigem.readOnly = true;
             	form.subloteOrigem.readOnly = true;
			 	form.setorComercialDestinoCD.readOnly = true;
			 	form.quadraDestinoNM.readOnly = true;
		     	form.loteDestino.readOnly = true;
             	form.subloteDestino.readOnly = true;
             
             	
             	form.loteOrigem.value = "";
             	form.subloteOrigem.value = "";	
			 
			  }else{
			 	form.setorComercialOrigemCD.readOnly = false;
			 	form.quadraOrigemNM.readOnly = false;
		     	form.loteOrigem.readOnly = false;
             	form.subloteOrigem.readOnly = false;
			 	form.setorComercialDestinoCD.readOnly = false;
			 	form.quadraDestinoNM.readOnly = false;
		     	form.loteDestino.readOnly = false;
             	form.subloteDestino.readOnly = false;
			
			  }
			
			break;					
			   		   
		case 2: //De setor Comercial		   
		    if(form.setorComercialOrigemCD.value != form.setorComercialDestinoCD.value){
			  	form.quadraOrigemNM.readOnly = true;
		     	form.loteOrigem.readOnly = true;
             	form.subloteOrigem.readOnly = true;
			 	form.quadraDestinoNM.readOnly = true;
		     	form.loteDestino.readOnly = true;
             	form.subloteDestino.readOnly = true;
             
             	form.loteOrigem.value = "";
             	form.subloteOrigem.value = "";
             	form.quadraOrigemNM.value = "";	
             	form.quadraOrigemID.value = "";
             		
			 
			  }else{
			 	form.quadraOrigemNM.readOnly = false;
		     	form.loteOrigem.readOnly = false;
             	form.subloteOrigem.readOnly = false;
			 	form.quadraDestinoNM.readOnly = false;
		     	form.loteDestino.readOnly = false;
             	form.subloteDestino.readOnly = false;
			
			  }
			
			break;
           
		case 3://De quadra 
		     if(form.quadraOrigemNM.value != form.quadraDestinoNM.value){
			  	form.loteOrigem.readOnly = true;
             	form.subloteOrigem.readOnly = true;
			 	form.loteDestino.readOnly = true;
             	form.subloteDestino.readOnly = true;
             
             	
             	form.loteOrigem.value = "";
             	form.subloteOrigem.value = "";
             	form.loteDestino.value = "";
             	form.subloteDestino.value = "";	
			 
			  }else{
			 	form.loteOrigem.readOnly = false;
             	form.subloteOrigem.readOnly = false;
			 	form.loteDestino.readOnly = false;
             	form.subloteDestino.readOnly = false;
			
			  }
			
			break;
		case 4://De Lote
		     if(form.loteOrigem.value != form.loteDestino.value){
			  	form.subloteOrigem.readOnly = true;
			 	form.subloteDestino.readOnly = true;
             
             	form.subloteOrigem.value = "";
             	form.subloteDestino.value = "";	
			 
			  }else{
			 	form.subloteOrigem.readOnly = false;
			 	form.subloteDestino.readOnly = false;
			
			  }
			
			break;
		
		}
	  
}


function redirecionarPopup(caminho){
	redirecionarSubmit(caminho+'&caminhoRetornoTelaPesquisa=exibirFiltrarDocumentosCobrancaAction');
}

function validarAnoMesReferencia(){
	var form = document.FiltrarDocumentosCobrancaActionForm;
	var retorno = true;
	if (form.mesAnoReferencia.value != ""){
		if (!validaAnoMesSemAlert(form.mesAnoReferencia)){
			form.mesAnoReferencia.value = "";
			alert('Mês/Ano de referência inválido');
			retorno = false;
		}
	}
	
	return retorno;
}


//
--></SCRIPT>
<script>
<!-- Begin 

     var bCancel = false; 
 
   function validarFormSubmit(){
     var form = document.FiltrarDocumentosCobrancaActionForm;
     	validarAnoMesReferencia();
     	if (validateFiltrarDocumentosCobrancaActionForm(form) && validarAnoMesReferencia() && testarCampoValorZeroDecimal(form.valorInicial, 'Valor Inicial') && testarCampoValorZeroDecimal(form.valorFinal, 'Valor Final')){
     		form.submit();
     	}
	}
/*
    function caracteresespeciais () { 
     this.aa = new Array("localidadeOrigemID", "Localidade de Origem possui caracteres especiais.", new Function ("varName", " return this[varName];"));
     this.ab = new Array("setorComercialOrigemCD", "Setor Comercial de Origem possui caracteres especiais.", new Function ("varName", " return this[varName];"));
     this.ac = new Array("quadraOrigemNM", "Quadra de Origem possui caracteres especiais.", new Function ("varName", " return this[varName];"));
     this.ad = new Array("loteOrigem", "Lote de Origem possui caracteres especiais.", new Function ("varName", " return this[varName];"));
     this.ae = new Array("localidadeDestinoID", "Localidade de Destino possui caracteres especiais.", new Function ("varName", " return this[varName];"));
     this.af = new Array("setorComercialDestinoCD", "Setor Comercial de destino possui caracteres especiais.", new Function ("varName", " return this[varName];"));
     this.ag = new Array("quadraDestinoNM", "Quadra de Destino possui caracteres especiais.", new Function ("varName", " return this[varName];"));
     this.ah = new Array("loteDestino", "Lote de destino possui caracteres especiais.", new Function ("varName", " return this[varName];"));
    } 

    function IntegerValidations () { 
     this.aa = new Array("localidadeOrigemID", "Localidade de Origem deve somente conter numeros positivos.", new Function ("varName", " return this[varName];"));
     this.ab = new Array("setorComercialOrigemCD", "Setor Comercial de Origem deve somente conter numeros positivos.", new Function ("varName", " return this[varName];"));
     this.ac = new Array("quadraOrigemNM", "Quadra de Origem deve somente conter numeros positivos.", new Function ("varName", " return this[varName];"));
     this.ad = new Array("loteOrigem", "Lote de Origem deve somente conter numeros positivos.", new Function ("varName", " return this[varName];"));
     this.ae = new Array("localidadeDestinoID", "Localidade de Destino deve somente conter numeros positivos.", new Function ("varName", " return this[varName];"));
     this.af = new Array("setorComercialDestinoCD", "Setor Comercial de Destino deve somente conter numeros positivos.", new Function ("varName", " return this[varName];"));
     this.ag = new Array("quadraDestinoNM", "Quadra de Destino deve somente conter numeros positivos.", new Function ("varName", " return this[varName];"));
     this.ah = new Array("loteDestino", "Lote de Destino deve somente conter numeros positivos.", new Function ("varName", " return this[varName];"));
    } 
*/
-->    
</script>
</head>
<!-- abrirPopup('/gsan/exibirInserirReajusteConsumoTarifaAction.do?id_r=' + document.forms[0].idRegistrosRemocao, 350, 610); -->


	<logic:present name="ehPopup">
		<body leftmargin="5" topmargin="5" onload="resizePageSemLink(630, 710);bloquearM2();">
	</logic:present>

	<logic:notPresent name="ehPopup">
		<body leftmargin="5" topmargin="5" onload="bloquearM2();">
	</logic:notPresent>


<html:form action="/filtrarDocumentosCobrancaAction"
	name="FiltrarDocumentosCobrancaActionForm"
	type="gcom.gui.cobranca.FiltrarDocumentosCobrancaActionForm"
	onsubmit="return validateFiltrarDocumentosCobrancaActionForm(this);" method="post">

	<logic:notPresent name="ehPopup">
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
			<td valign="top" class="centercoltext">
			<table height="100%">
				<tr>
					<td></td>
				</tr>
			</table>
			<table>
				<tr>
					<td></td>
				</tr>
			</table>

			<TABLE cellSpacing=0 cellPadding=0 width="100%" align=center border=0>
				<TBODY>

					<TR>
						<TD width=11><IMG
							src="<bean:message key="caminho.imagens"/>parahead_left.gif"
							border=0></TD>
						<TD class=parabg>Consultar Documentos de Cobran&ccedil;a</TD>
						<TD width=11><IMG
							src="<bean:message key="caminho.imagens"/>parahead_right.gif"
							border=0></TD>
					</TR>
				</TBODY>
			</TABLE>
			
		</logic:notPresent>

		<logic:present name="ehPopup">
			<table width="600" border="0" cellspacing="5" cellpadding="0">
				<tr>	
					<td width="600" valign="top" class="centercoltext">
					<table height="100%">
						<tr>
							<td></td>
						</tr>
					</table>
					<TABLE cellSpacing=0 cellPadding=0 width="100%" align=center border=0>
						<TBODY>
							<TR>
								<TD width=11><IMG
									src="<bean:message key="caminho.imagens"/>parahead_left.gif"
									border=0></TD>
								<TD class=parabg>Consultar Documentos de Cobran&ccedil;a</TD>
								<TD width=11><IMG
									src="<bean:message key="caminho.imagens"/>parahead_right.gif"
									border=0></TD>
							</TR>
		
						</TBODY>
					</TABLE>
					<P>&nbsp;</P>

		</logic:present>

			<DIV align=right>
			<table bordercolor="#000000" width="100%" cellspacing="0" id="table1">

				<tr>
					<td colspan="3">Para filtrar documentos de cobran&ccedil;a no
					sistema, informe os dados abaixo</td>
					<logic:present scope="application" name="urlHelp">
						<td align="right"><a href="javascript:abrirPopupHelp('${applicationScope.urlHelp}cobrancaDocumentoPesquisar', 500, 700);"><span style="font-weight: bold"><font color="#3165CE">Ajuda</font></span></a></td>									
					</logic:present>
					<logic:notPresent scope="application" name="urlHelp">
						<td align="right"><span style="font-weight: bold"><font color=#696969><u>Ajuda</u></font></span></td>									
					</logic:notPresent>
					
				</tr>
				<tr>
					<td><p>&nbsp;</p></td>
				</tr>
				<tr>
					<td width="25%"><strong> Matricula:<font color="#FF0000"></font> </strong>
					</td>
					<td colspan="3">
						<logic:notEqual parameter="bloquear" value="matricula">
							
							<html:text maxlength="9" 
								property="idImovel" 
								size="9"
								onkeypress="validaEnterComMensagem(event, 'exibirFiltrarDocumentosCobrancaAction.do?bloquear=todos', 'idImovel', 'Matrícula do Imóvel')"
								onkeyup="bloquearLSQLS();somente_numero_zero_a_nove(this);" 
								tabindex="1" />
							
							<a href="javascript:jogarPopup(1);"> 
								<img width="23" 
									height="21" 
									border="0" 
									title="Pesquisar Imóvel"
									src="<bean:message key="caminho.imagens"/>pesquisa.gif"/></a>
							
							<logic:equal name="corImovel" value="exception">
								<html:text property="inscricaoImovel" 
									size="45" 
									readonly="true"
									style="background-color:#EFEFEF; border:0; color: #ff0000"
									disabled="true" />
							</logic:equal>

							<logic:notEqual name="corImovel" value="exception">
								<html:text property="inscricaoImovel" 
									size="45" 
									readonly="true"
									style="background-color:#EFEFEF; border:0; color: #000000"
									disabled="true" />
							</logic:notEqual>


							<a href="javascript:limpar(11);"> 
								<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
									border="0" 
									title="Apagar Imóvel" /></a>
						</logic:notEqual> 

						<logic:equal parameter="bloquear" value="matricula">
							<html:text maxlength="9" 
								property="idImovel" 
								size="9"
								disabled="true"
								onkeypress="validaEnter(event, 'exibirFiltrarDocumentosCobrancaAction.do?bloquear=todos', 'idImovel')"
								onkeyup="bloquearLSQLS();somente_numero_zero_a_nove(this);" />
	
							<img width="23" 
								height="21" 
								border="0"
								src="<bean:message key="caminho.imagens"/>pesquisa.gif"
								onclick="abrirPopup('exibirPesquisarImovelAction.do',500,800);" />
								
							<logic:equal name="corImovel" value="exception">
								<html:text property="inscricaoImovel" 
									size="45" 
									readonly="true"
									style="background-color:#EFEFEF; border:0; color: #ff0000"
									disabled="true" />
							</logic:equal>
	
							<logic:notEqual name="corImovel" value="exception">
								<html:text property="inscricaoImovel" 
									size="45" 
									readonly="true"
									style="background-color:#EFEFEF; border:0; color: #000000"
									disabled="true" />
							</logic:notEqual>
	
							<a href="javascript:limpar(11);"> 
								<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
									border="0" 
									title="Apagar Imóvel" /></a>
						</logic:equal>
					</td>
				</tr>
				<tr>
					<td><p>&nbsp;</p></td>
				</tr>
				<tr>
					<td colspan="4">
					<table width="98%" bgcolor="#99CCFF">
						<tr>
							<td>
							<table width="100%" border="0" cellpadding="1" cellspacing="0"
								bgcolor="#99CCFF" bordercolor="#99CCFF">
								<!--header da tabela interna -->
								<tr>
									<td align="center"><b> Endere&ccedil;o</b></td>
								</tr>
							</table>
							</td>

						</tr>
						<logic:present name="existe" scope="request">
							<tr bgcolor="#FFFFFF">
								<td>
									<div align="center">
										<span id="endereco">
											<bean:write name="FiltrarDocumentosCobrancaActionForm" property="endereco" />&nbsp;
										</span>
									</div>
									<html:hidden property="endereco" />
								</td>
							</tr>
						</logic:present>
					</table>
					</td>
				</tr>

				<tr>
					<td height="24" colspan="4" class="style1"><hr></td>
				</tr>
				<tr>
					<td><strong>Gerência Regional:<font color="#FF0000"></font></strong></td>
	
					<td>
					<html:select property="idGerenciaRegional"  onchange="bloquearELSQ();">
						<html:option value="">&nbsp;</html:option>
			            <html:options collection="colecaoGerenciasRegionais" labelProperty="nome" property="id" />
					</html:select>	
					</td>
				</tr>
				<tr>
					<td height="24"><strong>Unidade Negócio:<strong></strong></strong></td>
					<td width="60%" colspan="3">
					<div align="left"><strong> <html:select property="idUnidadeNegocio" onchange="bloquearESLSQ();">
						<html:option value="">&nbsp;</html:option>
						<html:options collection="colecaoUnidadeNegocio" labelProperty="nome" property="id" />
						
					</html:select></strong></div>
					</td>

				</tr>



				<tr>
					<td height="24" colspan="4" class="style1"><hr></td>
				</tr>

				<tr>
					<td colspan="4"></td>
				</tr>

				<tr>
					<td colspan="2">Informe os dados da inscri&ccedil;&atilde;o inicial:</td>
				</tr>

				<tr>
					<logic:equal scope="session" parameter="bloquear" value="todos">

						<td>
							<strong>Localidade:<font color="#FF0000"></font></strong>
						</td>
						<td><html:text maxlength="3" 
								property="localidadeOrigemID"
								size="5"
								onkeypress="validaEnter(event, 'exibirFiltrarDocumentosCobrancaAction.do?objetoConsulta=1&inscricaoTipo=origem&bloquear=matricula', 'localidadeOrigemID');"
								tabindex="2" 
								onclick="validarLocalidade()" 
								onblur="replicaDados(document.FiltrarDocumentosCobrancaActionForm.localidadeOrigemID, document.FiltrarDocumentosCobrancaActionForm.localidadeDestinoID);"
								onkeyup="limparBorrachaOrigem(4, this);bloquearM();limpaCampoSeVazio(document.forms[0].localidadeOrigemID,'limparBorrachaOrigem(1, this);');somente_numero_zero_a_nove(this);"
								disabled="true" /> 
								
							<logic:present name="ehPopup" scope="session">
								<a href="javascript:redirecionarPopup('exibirPesquisarLocalidadeAction.do?tipo=origem');">
									<img src="<bean:message key='caminho.imagens'/>pesquisa.gif"
										width="23" 
										height="21" 
										style="cursor:hand;"
										alt="Pesquisar" 
										border="0" 
										name="pesquisaLocalidade"></a>
							</logic:present>

							<logic:notPresent name="ehPopup" scope="session">

								<a href="javascript:jogarPopup(2);">
									<img src="<bean:message key='caminho.imagens'/>pesquisa.gif"
										width="23" 
										height="21" 
										style="cursor:hand;"
										alt="Pesquisar" 
										border="0" 
										name="pesquisaLocalidade"></a>
							</logic:notPresent>
							
							<logic:present name="corLocalidadeOrigem">

								<logic:equal name="corLocalidadeOrigem" value="exception">
									<html:text property="nomeLocalidadeOrigem" 
										size="45"
										readonly="true"
										style="background-color:#EFEFEF; border:0; color: #ff0000"
										disabled="true" />
								</logic:equal>

								<logic:notEqual name="corLocalidadeOrigem" value="exception">
									<html:text property="nomeLocalidadeOrigem" 
										size="45"
										readonly="true"
										style="background-color:#EFEFEF; border:0; color: #000000"
										disabled="true" />
								</logic:notEqual>

							</logic:present> 
							
							<logic:notPresent name="corLocalidadeOrigem">

								<logic:empty name="FiltrarDocumentosCobrancaActionForm" property="localidadeOrigemID">
									<html:text property="nomeLocalidadeOrigem" 
										value="" 
										size="45"
										readonly="true"
										style="background-color:#EFEFEF; border:0; color: #ff0000"
										disabled="true" />
								</logic:empty>
								
								<logic:notEmpty name="FiltrarDocumentosCobrancaActionForm" property="localidadeOrigemID">
									<html:text property="nomeLocalidadeOrigem" 
										size="45"
										readonly="true"
										style="background-color:#EFEFEF; border:0; color: 	#000000"
										disabled="true" />
								</logic:notEmpty>
							</logic:notPresent> 
							
							<a href="javascript:limpaLocal();">
								<img src="<bean:message key='caminho.imagens'/>limparcampo.gif"
									alt="Apagar" 
									border="0" 
									style="cursor:hand;"
									onclick=""
									title="Apagar Localidade"></a>
						</td>
				</tr>
				<tr>
					<td><strong>Setor Comercial :</strong></td>
					<td>
						<html:text maxlength="3" 
							property="setorComercialOrigemCD"
							size="5"
							onkeypress="validaEnterDependencia(event, 'exibirFiltrarDocumentosCobrancaAction.do?objetoConsulta=2&inscricaoTipo=origem&bloquear=matricula', document.forms[0].setorComercialOrigemCD, document.forms[0].localidadeOrigemID.value, 'localidade da inscrição de origem');"
							tabindex="3" 
							disabled="true" 
							onblur="replicaDados(document.FiltrarDocumentosCobrancaActionForm.setorComercialOrigemCD, document.FiltrarDocumentosCobrancaActionForm.setorComercialDestinoCD);"
							onkeyup="limparBorrachaOrigem(5, this);limpaCampoSeVazio(document.forms[0].setorComercialOrigemCD,'limparBorrachaOrigem(2, this);');somente_numero_zero_a_nove(this);" />
						
							<logic:present name="ehPopup">
								<a href="javascript:redirecionarPopup('exibirPesquisarSetorComercialAction.do?tipo=origem');">
									<img src="<bean:message key='caminho.imagens'/>pesquisa.gif"
										width="23" 
										height="21" 
										border="0" 
										style="cursor:hand;"></a>
							</logic:present>

							<logic:notPresent name="ehPopup">

								<a href="javascript:chamarPopup('exibirPesquisarSetorComercialAction.do', 'setorComercialOrigem', 'idLocalidade', document.FiltrarDocumentosCobrancaActionForm.localidadeOrigemID.value, 275, 480, 'Informe a Localidade da inscrição de origem');">
									<img src="<bean:message key='caminho.imagens'/>pesquisa.gif"
										width="23" 
										height="21" 
										border="0" 
										style="cursor:hand;"></a>
							</logic:notPresent>

						<logic:present name="corSetorComercialOrigem">

							<logic:equal name="corSetorComercialOrigem" value="exception">
								
								<html:text property="nomeSetorComercialOrigem" 
									size="45"
									readonly="true"
									style="background-color:#EFEFEF; border:0; color: #ff0000"
									disabled="true" />
							</logic:equal>

							<logic:notEqual name="corSetorComercialOrigem" value="exception">
								<html:text property="nomeSetorComercialOrigem" 
									size="45"
									readonly="true"
									style="background-color:#EFEFEF; border:0; color: #000000"
									disabled="true" />
							</logic:notEqual>

						</logic:present> 
						
						<logic:notPresent name="corSetorComercialOrigem">

							<logic:empty name="FiltrarDocumentosCobrancaActionForm" property="setorComercialOrigemCD">
								<html:text property="nomeSetorComercialOrigem" 
									value="" 
									size="45"
									readonly="true"
									style="background-color:#EFEFEF; border:0; color: #ff0000"
									disabled="true" />
							</logic:empty>
							
							<logic:notEmpty name="FiltrarDocumentosCobrancaActionForm" property="setorComercialOrigemCD">
								<html:text property="nomeSetorComercialOrigem" 
									size="45"
									readonly="true"
									style="background-color:#EFEFEF; border:0; color: #000000"
									disabled="true" />
							</logic:notEmpty>

						</logic:notPresent> 
						
						<a href="javascript:limparBorrachaOrigem(2, this);">
							<img src="<bean:message key='caminho.imagens'/>limparcampo.gif"
								alt="Apagar" 
								border="0" 
								style="cursor:hand;"
								onclick=""
								title="Apagar Setor Comercial"></a> 
						
						<html:hidden property="setorComercialOrigemID" />
					</td>
				</tr>
				<tr>
					<td width="183"><strong>Quadra:</strong></td>
					<td width="432">
						<html:text maxlength="4" 
							property="quadraOrigemNM"
							size="5"
							onkeypress="validaEnterDependencia(event, 'exibirFiltrarDocumentosCobrancaAction.do?objetoConsulta=3&inscricaoTipo=origem&bloquear=matricula', document.forms[0].quadraOrigemNM, document.forms[0].setorComercialOrigemCD.value, 'setor comercial da inscrição de origem');"
							tabindex="4" 
							disabled="true" 
							onblur="replicaDados(document.FiltrarDocumentosCobrancaActionForm.quadraOrigemNM, document.FiltrarDocumentosCobrancaActionForm.quadraDestinoNM);"
							onkeyup="limparBorrachaOrigem(6, this);limpaCampoSeVazio(document.FiltrarDocumentosCobrancaActionForm.quadraOrigemNM,'limparBorrachaOrigem(3, this);');somente_numero_zero_a_nove(this);" />
							
						<logic:present name="corQuadraOrigem">
							<logic:equal name="corQuadraOrigem" value="exception">
								<span style="color:#ff0000" id="msgQuadraOrigem">
								<bean:write name="FiltrarDocumentosCobrancaActionForm" property="quadraMensagemOrigem" />
								</span>
							</logic:equal>
						</logic:present> 
						<html:hidden property="quadraOrigemID" />
					</td>
				</tr>
				<tr>
					<td><html:hidden property="loteOrigem" /> <html:hidden
						property="subloteOrigem" /></td>
				</tr>
				<tr>
					<td colspan="4"></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td colspan="2">Informe os dados da inscri&ccedil;&atilde;o final:</td>
				</tr>
				<tr>
					<td><strong>Localidade:<font color="#FF0000"></font></strong></td>
					<td>
						<html:text maxlength="3" 
							property="localidadeDestinoID"
							size="5"
							onkeypress="validaEnter(event, 'exibirFiltrarDocumentosCobrancaAction.do?objetoConsulta=1&inscricaoTipo=destino&bloquear=matricula&bloquear=matricula', 'localidadeDestinoID');"
							tabindex="7" 
							disabled="true"
							onkeyup="limparBorrachaOrigem(7, this);limpaCampoSeVazio(document.FiltrarDocumentosCobrancaActionForm.localidadeDestinoID,'limparBorrachaDestino(1);');somente_numero_zero_a_nove(this);" />
						
						<logic:present name="ehPopup">
							<a href="javascript:redirecionarPopup('exibirPesquisarLocalidadeAction.do?tipo=destino');">
								<img src="<bean:message key='caminho.imagens'/>pesquisa.gif"
									width="23" height="21" 
									style="cursor:hand;"
									onclick="" border="0"
									alt="Pesquisar"></a> 
						</logic:present>

						<logic:notPresent name="ehPopup">
							<a href="javascript:jogarPopup(3);">
								<img src="<bean:message key='caminho.imagens'/>pesquisa.gif"
									width="23" height="21" 
									style="cursor:hand;"
									onclick="" border="0"
									alt="Pesquisar"></a> 
						</logic:notPresent>
						
						<logic:present name="corLocalidadeDestino">

							<logic:equal name="corLocalidadeDestino" value="exception">
								<html:text property="nomeLocalidadeDestino" 
									size="45"
									readonly="true"
									style="background-color:#EFEFEF; border:0; color: #ff0000"
									disabled="true" />
							</logic:equal>

							<logic:notEqual name="corLocalidadeDestino" value="exception">
								<html:text property="nomeLocalidadeDestino" 
									size="45"
									readonly="true"
									style="background-color:#EFEFEF; border:0; color: #000000"
									disabled="true" />
							</logic:notEqual>

						</logic:present> 
						
						<logic:notPresent name="corLocalidadeDestino">

							<logic:empty name="FiltrarDocumentosCobrancaActionForm" property="localidadeDestinoID">
								<html:text property="nomeLocalidadeDestino" 
									value="" 
									size="45"
									readonly="true"
									style="background-color:#EFEFEF; border:0; color: #ff0000"
									disabled="true" />
							</logic:empty>
							
							<logic:notEmpty name="FiltrarDocumentosCobrancaActionForm" property="localidadeDestinoID">
								<html:text property="nomeLocalidadeDestino" 
									size="45"
									readonly="true"
									style="background-color:#EFEFEF; border:0; color: 	#000000"
									disabled="true" />
							</logic:notEmpty>
						
						</logic:notPresent>
						
						<a href="javascript:limparBorrachaDestino(1);">
							<img src="<bean:message key='caminho.imagens'/>limparcampo.gif"
								alt="Apagar" 
								border="0" 
								style="cursor:hand;"
								onclick=""
								title="Apagar Localidade"></a>
					</td>
				</tr>
				<tr>
					<td><strong>Setor Comercial :</strong></td>
					<td>
						<html:text maxlength="3" 
							property="setorComercialDestinoCD"
							size="5"
							onkeypress="validaEnterDependencia(event, 'exibirFiltrarDocumentosCobrancaAction.do?objetoConsulta=2&inscricaoTipo=destino&bloquear=matricula', document.FiltrarDocumentosCobrancaActionForm.setorComercialDestinoCD, document.FiltrarDocumentosCobrancaActionForm.localidadeDestinoID.value, 'localidade da inscrição de destino');"
							tabindex="8" 
							disabled="true"
							onkeyup="limparBorrachaOrigem(8, this);limpaCampoSeVazio(document.FiltrarDocumentosCobrancaActionForm.setorComercialDestinoCD,'limparBorrachaDestino(2);');somente_numero_zero_a_nove(this);" />
						
						
						<logic:present name="ehPopup">
							<a href="javascript:redirecionarPopup('exibirPesquisarSetorComercialAction.do?tipo=destino');">
								<img src="<bean:message key='caminho.imagens'/>pesquisa.gif"
									width="23" 
									height="21" 
									style="cursor:hand;"
									border="0"
									alt="Pesquisar"></a> 
						</logic:present>

						<logic:notPresent name="ehPopup">
							<a href="javascript:chamarPopup('exibirPesquisarSetorComercialAction.do', 'setorComercialDestino', 'idLocalidade', document.FiltrarDocumentosCobrancaActionForm.localidadeDestinoID.value, 275, 480, 'Informe a Localidade da inscrição de destino');">
								<img src="<bean:message key='caminho.imagens'/>pesquisa.gif"
									width="23" 
									height="21" 
									style="cursor:hand;"
									border="0"
									alt="Pesquisar"></a> 
						</logic:notPresent>

						<logic:present name="corSetorComercialDestino">

							<logic:equal name="corSetorComercialDestino" value="exception">
								<html:text property="nomeSetorComercialDestino" 
									size="45"
									readonly="true"
									style="background-color:#EFEFEF; border:0; color: #ff0000"
									disabled="true" />
							</logic:equal>

							<logic:notEqual name="corSetorComercialDestino" value="exception">
								<html:text property="nomeSetorComercialDestino" 
									size="45"
									readonly="true"
									style="background-color:#EFEFEF; border:0; color: #000000"
									disabled="true" />
							</logic:notEqual>
						</logic:present> 

						<logic:notPresent name="corSetorComercialDestino">

							<logic:empty name="FiltrarDocumentosCobrancaActionForm" property="setorComercialDestinoCD">
								<html:text property="nomeSetorComercialDestino" 
									value=""
									size="45" 
									readonly="true"
									style="background-color:#EFEFEF; border:0; color: #ff0000"
									disabled="true" />
							</logic:empty>
							
							<logic:notEmpty name="FiltrarDocumentosCobrancaActionForm" property="setorComercialDestinoCD">
								<html:text property="nomeSetorComercialDestino" 
									size="45"
									readonly="true"
									style="background-color:#EFEFEF; border:0; color: #000000"
									disabled="true" />
							</logic:notEmpty>

						</logic:notPresent> 
						<a href="javascript:limparBorrachaDestino(2);">
							<img src="<bean:message key='caminho.imagens'/>limparcampo.gif"
								alt="Apagar" 
								border="0" 
								style="cursor:hand;"
								title="Apagar Setor Comercial"></a>
							
						<html:hidden property="setorComercialDestinoID" />
					</td>
				</tr>
				<tr>
					<td><strong>Quadra:</strong></td>
					<td>
						<html:text maxlength="4" 
							property="quadraDestinoNM" 
							size="5"
							onkeypress="validaEnterDependencia(event, 'exibirFiltrarDocumentosCobrancaAction.do?objetoConsulta=3&inscricaoTipo=destino&bloquear=matricula', document.FiltrarDocumentosCobrancaActionForm.quadraDestinoNM, document.FiltrarDocumentosCobrancaActionForm.setorComercialDestinoCD.value, 'setor comercial da inscrição de destino');"
							tabindex="9" 
							disabled="true"
							onkeyup="limparBorrachaOrigem(9, this);limpaCampoSeVazio(document.FiltrarDocumentosCobrancaActionForm.quadraDestinoNM,'limparBorrachaDestino(3);');somente_numero_zero_a_nove(this);" />
							
						<logic:equal name="corQuadraDestino" value="exception">
							<span style="color:#ff0000" id="msgQuadraDestino">
							<bean:write name="FiltrarDocumentosCobrancaActionForm" property="quadraMensagemDestino" /></span>
						</logic:equal> 
						<html:hidden property="quadraDestinoID" />
					</td>
				</tr>
				
				<tr>
					<td><html:hidden property="loteDestino" /> <html:hidden
						property="subloteDestino" /></td>
				</tr>
				</logic:equal>




				<logic:notEqual scope="session" parameter="bloquear" value="todos">
					<tr>
						<td><strong>Localidade:<font color="#FF0000"></font></strong></td>
						<td>
							<html:text maxlength="3" 
								property="localidadeOrigemID"
								size="5"
								onkeypress="validaEnter(event, 'exibirFiltrarDocumentosCobrancaAction.do?objetoConsulta=1&inscricaoTipo=origem&bloquear=matricula', 'localidadeOrigemID');"
								tabindex="2" 
								onclick="validarLocalidade()"  
								onblur="replicaDados(document.FiltrarDocumentosCobrancaActionForm.localidadeOrigemID, document.FiltrarDocumentosCobrancaActionForm.localidadeDestinoID);"
								onkeyup="limparBorrachaOrigem(4, this);bloquearM();limpaCampoSeVazio(document.FiltrarDocumentosCobrancaActionForm.localidadeOrigemID,'limparBorrachaOrigem(1, this);');somente_numero_zero_a_nove(this);" />
						
							<logic:present name="ehPopup" scope="session">
								<a href="javascript:redirecionarPopup('exibirPesquisarLocalidadeAction.do?tipo=origem');">
								<img src="<bean:message key='caminho.imagens'/>pesquisa.gif"
									width="23" 
									height="21" 
									style="cursor:hand;"
									onclick="" 
									border="0"
									alt="Pesquisar" 
									name="pesquisaLocalidade"></a> 

							</logic:present>
							
							<logic:notPresent name="ehPopup" scope="session">
								<a href="javascript:jogarPopup(2);">
									<img src="<bean:message key='caminho.imagens'/>pesquisa.gif"
										width="23" 
										height="21" 
										style="cursor:hand;"
										onclick="" 
										border="0"
										alt="Pesquisar" 
										name="pesquisaLocalidade"></a> 
							</logic:notPresent>

							<logic:present name="corLocalidadeOrigem">

								<logic:equal name="corLocalidadeOrigem" value="exception">
									<html:text property="nomeLocalidadeOrigem" 
										size="45"
										readonly="true"
										style="background-color:#EFEFEF; border:0; color: #ff0000" />
								</logic:equal>

								<logic:notEqual name="corLocalidadeOrigem" value="exception">
									<html:text property="nomeLocalidadeOrigem" 
										size="45"
										readonly="true"
										style="background-color:#EFEFEF; border:0; color: #000000" />
								</logic:notEqual>

							</logic:present> 
							
							<logic:notPresent name="corLocalidadeOrigem">

								<logic:empty name="FiltrarDocumentosCobrancaActionForm" property="localidadeOrigemID">
									<html:text property="nomeLocalidadeOrigem" 
										value="" 
										size="45"
										readonly="true"
										style="background-color:#EFEFEF; border:0; color: #ff0000" />
								</logic:empty>
								
								<logic:notEmpty name="FiltrarDocumentosCobrancaActionForm" property="localidadeOrigemID">
									<html:text property="nomeLocalidadeOrigem" 
										size="45"
										readonly="true"
										style="background-color:#EFEFEF; border:0; color: 	#000000" />
								</logic:notEmpty>

							</logic:notPresent>
								<a href="javascript:limparBorrachaOrigem(1, this);">
									<img src="<bean:message key='caminho.imagens'/>limparcampo.gif"
										alt="Apagar" 
										border="0" 
										style="cursor:hand;"
										onclick=""
										title="Apagar Localidade"></a>
						</td>
					</tr>
					
					<tr>
						<td><strong>Setor Comercial :</strong></td>
						<td>
							<html:text maxlength="3" property="setorComercialOrigemCD"
								size="5"
								onkeypress="validaEnterDependencia(event, 'exibirFiltrarDocumentosCobrancaAction.do?objetoConsulta=2&inscricaoTipo=origem&bloquear=matricula', document.FiltrarDocumentosCobrancaActionForm.setorComercialOrigemCD, document.FiltrarDocumentosCobrancaActionForm.localidadeOrigemID.value, 'localidade da inscrição de origem');"
								tabindex="3" onblur="replicaDados(document.FiltrarDocumentosCobrancaActionForm.setorComercialOrigemCD, document.FiltrarDocumentosCobrancaActionForm.setorComercialDestinoCD);"
								onkeyup="limparBorrachaOrigem(5, this);limpaCampoSeVazio(document.FiltrarDocumentosCobrancaActionForm.setorComercialOrigemCD,'limparBorrachaOrigem(2, this);');somente_numero_zero_a_nove(this);" />
						
							<logic:present name="ehPopup">
								<a href="javascript:redirecionarPopup('exibirPesquisarSetorComercialAction.do?tipo=destino');">
								<img src="<bean:message key='caminho.imagens'/>pesquisa.gif"
									width="23" 
									border="0" 
									height="21" 
									style="cursor:hand;"></a>
							</logic:present>
							
							<logic:notPresent name="ehPopup">		
								<a href="javascript:chamarPopup('exibirPesquisarSetorComercialAction.do', 'setorComercialOrigem', 'idLocalidade', document.FiltrarDocumentosCobrancaActionForm.localidadeOrigemID.value, 275, 480, 'Informe a Localidade da inscrição de origem');">
									<img src="<bean:message key='caminho.imagens'/>pesquisa.gif"
										width="23" 
										border="0" 
										height="21" 
										style="cursor:hand;"></a>
							</logic:notPresent>

							<logic:present name="corSetorComercialOrigem">
	
								<logic:equal name="corSetorComercialOrigem" value="exception">
									<html:text property="nomeSetorComercialOrigem" 
										size="45"
										readonly="true"
										style="background-color:#EFEFEF; border:0; color: #ff0000" />
								</logic:equal>
	
								<logic:notEqual name="corSetorComercialOrigem" value="exception">
									<html:text property="nomeSetorComercialOrigem" 
										size="45"
										readonly="true"
										style="background-color:#EFEFEF; border:0; color: #000000" />
								</logic:notEqual>
	
							</logic:present> 
							
							<logic:notPresent name="corSetorComercialOrigem">

								<logic:empty name="FiltrarDocumentosCobrancaActionForm" property="setorComercialOrigemCD">
									<html:text property="nomeSetorComercialOrigem" 
										value=""
										size="45" 
										readonly="true"
										style="background-color:#EFEFEF; border:0; color: #ff0000" />
								</logic:empty>
								
								<logic:notEmpty name="FiltrarDocumentosCobrancaActionForm" property="setorComercialOrigemCD">
									<html:text property="nomeSetorComercialOrigem" 
										size="45"
										readonly="true"
										style="background-color:#EFEFEF; border:0; color: #000000" />
								</logic:notEmpty>

							</logic:notPresent>
							<a href="javascript:limparBorrachaOrigem(2, this);"> 
								<img src="<bean:message key='caminho.imagens'/>limparcampo.gif"
									alt="Apagar" 
									style="cursor:hand;"
									onclick="" 
									border="0"
									title="Apagar Setor Comercial"></a> 
							<html:hidden property="setorComercialOrigemID" />
						</td>
					</tr>

					<tr>
						<td width="183"><strong>Quadra:</strong></td>
						<td width="432">
							<html:text maxlength="4" 
								property="quadraOrigemNM"
								size="5"  
								onkeypress="validaEnterDependencia(event, 'exibirFiltrarDocumentosCobrancaAction.do?objetoConsulta=3&inscricaoTipo=origem&bloquear=matricula', document.FiltrarDocumentosCobrancaActionForm.quadraOrigemNM, document.FiltrarDocumentosCobrancaActionForm.setorComercialOrigemCD.value, 'setor comercial da inscrição de origem');"
								tabindex="4" 
								onblur="replicaDados(document.FiltrarDocumentosCobrancaActionForm.quadraOrigemNM, document.FiltrarDocumentosCobrancaActionForm.quadraDestinoNM);"
								onkeyup="limparBorrachaOrigem(6, this);limpaCampoSeVazio(document.FiltrarDocumentosCobrancaActionForm.quadraOrigemNM,'limparBorrachaOrigem(3, this);');" />

							<logic:equal name="corQuadraOrigem" value="exception">
								<span style="color:#ff0000" id="msgQuadraOrigem">
								<bean:write name="FiltrarDocumentosCobrancaActionForm" property="quadraMensagemOrigem" /></span>
							</logic:equal> 
							<html:hidden property="quadraOrigemID" />
						</td>
					</tr>
					<tr>
						<td><html:hidden property="loteOrigem" /> <html:hidden
							property="subloteOrigem" /></td>
					</tr>
					<td colspan="4"></td>
					<tr>
					<tr>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
					<tr>
						<td colspan="2">Informe os dados da inscri&ccedil;&atilde;o final:</td>
					</tr>
					<tr>
						<td><strong>Localidade:<font color="#FF0000"></font></strong></td>
						<td><html:text maxlength="3" property="localidadeDestinoID"
							size="5"
							onkeypress="validaEnter(event, 'exibirFiltrarDocumentosCobrancaAction.do?objetoConsulta=1&inscricaoTipo=destino&bloquear=matricula&bloquear=matricula', 'localidadeDestinoID');"
							tabindex="7"
							onkeyup="limparBorrachaOrigem(7, this);limpaCampoSeVazio(document.FiltrarDocumentosCobrancaActionForm.localidadeDestinoID,'limparBorrachaDestino(1);');somente_numero_zero_a_nove(this);" />

							<logic:present name="ehPopup" scope="session">
								<a href="javascript:redirecionarPopup('exibirPesquisarLocalidadeAction.do?tipo=origem');">
									<img src="<bean:message key='caminho.imagens'/>pesquisa.gif"
										width="23" 
										height="21" 
										style="cursor:hand;"
										border="0"
										alt="Pesquisar"></a>
							</logic:present>

							<logic:notPresent name="ehPopup" scope="session">
								<a href="javascript:jogarPopup(3);">
									<img src="<bean:message key='caminho.imagens'/>pesquisa.gif"
										width="23" 
										height="21" 
										style="cursor:hand;"
										border="0"
										alt="Pesquisar"></a> 
							</logic:notPresent>

							<logic:present name="corLocalidadeDestino">

								<logic:equal name="corLocalidadeDestino" value="exception">
									<html:text property="nomeLocalidadeDestino" 
										size="45"
										readonly="true"
										style="background-color:#EFEFEF; border:0; color: #ff0000" />
								</logic:equal>

								<logic:notEqual name="corLocalidadeDestino" value="exception">
									<html:text property="nomeLocalidadeDestino" 
										size="45"
										readonly="true"
										style="background-color:#EFEFEF; border:0; color: #000000" />
								</logic:notEqual>

							</logic:present> 
							
							<logic:notPresent name="corLocalidadeDestino">

								<logic:empty name="FiltrarDocumentosCobrancaActionForm" property="localidadeDestinoID">
									<html:text property="nomeLocalidadeDestino" 
										size="45"
										readonly="true"
										style="background-color:#EFEFEF; border:0; color: #ff0000" />
								</logic:empty>
								
								<logic:notEmpty name="FiltrarDocumentosCobrancaActionForm" property="localidadeDestinoID">
									<html:text property="nomeLocalidadeDestino" 
										size="45"
										readonly="true"
										style="background-color:#EFEFEF; border:0; color: 	#000000" />
								</logic:notEmpty>
							</logic:notPresent>
							
							
							<a href="javascript:limparBorrachaDestino(1);"> 
								<img src="<bean:message key='caminho.imagens'/>limparcampo.gif"
									alt="Apagar" 
									border="0" 
									style="cursor:hand;"
									title="Apagar Localidade"></a>
						</td>
					</tr>
					<tr>
						<td><strong>Setor Comercial :</strong></td>
						<td>
							<html:text maxlength="3" 
								property="setorComercialDestinoCD"
								size="5"
								onkeypress="validaEnterDependencia(event, 'exibirFiltrarDocumentosCobrancaAction.do?objetoConsulta=2&inscricaoTipo=destino&bloquear=matricula', document.FiltrarDocumentosCobrancaActionForm.setorComercialDestinoCD, document.FiltrarDocumentosCobrancaActionForm.localidadeDestinoID.value, 'localidade da inscrição de destino');"
								tabindex="8"
								onkeyup="limparBorrachaOrigem(8, this);limpaCampoSeVazio(document.FiltrarDocumentosCobrancaActionForm.setorComercialDestinoCD,'limparBorrachaDestino(2);');somente_numero_zero_a_nove(this);" />
								
								<logic:present name="ehPopup">
									<a href="javascript:redirecionarPopup('exibirPesquisarSetorComercialAction.do?tipo=destino');">
									<img src="<bean:message key='caminho.imagens'/>pesquisa.gif"
										width="23" 
										height="21" 
										style="cursor:hand;"
										border="0"
										alt="Pesquisar"></a>
								</logic:present>

								<logic:notPresent name="ehPopup">
									<a href="javascript:chamarPopup('exibirPesquisarSetorComercialAction.do', 'setorComercialDestino', 'idLocalidade', document.FiltrarDocumentosCobrancaActionForm.localidadeDestinoID.value, 275, 480, 'Informe a Localidade da inscrição de destino');">
									<img src="<bean:message key='caminho.imagens'/>pesquisa.gif"
										width="23" 
										height="21" 
										style="cursor:hand;"
										border="0"
										alt="Pesquisar"></a>
								</logic:notPresent>

								<logic:present name="corSetorComercialDestino">

									<logic:equal name="corSetorComercialDestino" value="exception">
										<html:text property="nomeSetorComercialDestino" 
											size="45"
											readonly="true"
											style="background-color:#EFEFEF; border:0; color: #ff0000" />
									</logic:equal>

									<logic:notEqual name="corSetorComercialDestino" value="exception">
										<html:text property="nomeSetorComercialDestino" 
											size="45"
											readonly="true"
											style="background-color:#EFEFEF; border:0; color: #000000" />
									</logic:notEqual>

								</logic:present> 
								
								<logic:notPresent name="corSetorComercialDestino">

									<logic:empty name="FiltrarDocumentosCobrancaActionForm" property="setorComercialDestinoCD">
										<html:text property="nomeSetorComercialDestino" 
											value=""
											size="45" 
											readonly="true"
											style="background-color:#EFEFEF; border:0; color: #ff0000" />
									</logic:empty>
									
									<logic:notEmpty name="FiltrarDocumentosCobrancaActionForm" property="setorComercialDestinoCD">
										<html:text property="nomeSetorComercialDestino" 
											size="45"
											readonly="true"
											style="background-color:#EFEFEF; border:0; color: #000000" />
									</logic:notEmpty>

								</logic:notPresent> 
								
								<a href="javascript:limparBorrachaDestino(2);"> 
									<img src="<bean:message key='caminho.imagens'/>limparcampo.gif"
										alt="Apagar" 
										style="cursor:hand;" 
										border="0"
										title="Apagar Setor Comercial"></a> 
								<html:hidden property="setorComercialDestinoID" />
						</td>
					</tr>
					
					<tr>
						<td><strong>Quadra:</strong></td>
						<td><html:text maxlength="4" property="quadraDestinoNM" size="5"
							onkeypress="validaEnterDependencia(event, 'exibirFiltrarDocumentosCobrancaAction.do?objetoConsulta=3&inscricaoTipo=destino&bloquear=matricula', document.FiltrarDocumentosCobrancaActionForm.quadraDestinoNM, document.FiltrarDocumentosCobrancaActionForm.setorComercialDestinoCD.value, 'setor comercial da inscrição de destino');"
							tabindex="9"
							onkeyup="limparBorrachaOrigem(9, this);limpaCampoSeVazio(document.FiltrarDocumentosCobrancaActionForm.quadraDestinoNM,'limparBorrachaDestino(3);');somente_numero_zero_a_nove(this);" />

						<logic:equal name="corQuadraDestino" value="exception">
							<span style="color:#ff0000" id="msgQuadraDestino"><bean:write
								name="FiltrarDocumentosCobrancaActionForm"
								property="quadraMensagemDestino" /></span>
						</logic:equal> <html:hidden property="quadraDestinoID" /></td>
					</tr>
					<tr>
						<td><html:hidden property="loteDestino" /> <html:hidden
							property="subloteDestino" /></td>
					</tr>
				</logic:notEqual>

				<tr>
					<td height="24" colspan="4" class="style1"><hr></td>

				</tr>
				<tr>
					<td class="style1"><strong>Forma de Emiss&atilde;o:</strong></td>
					<td colspan="4" class="style1"><strong> <html:select
						property="documentoEmissaoForma" style="width: 230px;"
						multiple="mutiple" size="3">
						<logic:present name="colecaoDocumentoEmissaoForma">
							<html:option
								value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
							<html:options collection="colecaoDocumentoEmissaoForma"
								labelProperty="descricaoDocumentoEmissaoForma" property="id" />
						</logic:present>
					</html:select></strong></td>
				</tr>
				<tr>
					<td class="style1"><strong>A&ccedil;&atilde;o de Cobran&ccedil;a:</strong></td>
					<td colspan="4" class="style1"><strong> <html:select
						property="cobrancaAcao" style="width: 230px;" multiple="mutiple"
						size="3">
						<logic:present name="colecaoCobrancaAcao">
							<html:option
								value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
							<html:options collection="colecaoCobrancaAcao"
								labelProperty="descricaoCobrancaAcao" property="id" />
						</logic:present>
					</html:select></strong></td>
				</tr>
				<tr>
					<td class="style1"><strong>Situa&ccedil;&atilde;o da A&ccedil;&atilde;o:</strong></td>
					<td colspan="4" class="style1"><strong> <html:select
						property="idCobrancaAcaoSituacao" style="width: 230px;" multiple="mutiple"
						size="3">
						<logic:present name="colecaoCobrancaAcaoSituacao">
							<html:option
								value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
							<html:options collection="colecaoCobrancaAcaoSituacao"
								labelProperty="descricao" property="id" />
						</logic:present>
					</html:select></strong></td>
				</tr>
				<tr>
					<td class="style1"><strong>Situa&ccedil;&atilde;o do Débito:</strong></td>
					<td colspan="4" class="style1"><strong> <html:select
						property="idCobrancaDebitoSituacao" style="width: 230px;" multiple="mutiple"
						size="3">
						<logic:present name="colecaoCobrancaDebitoSituacao">
							<html:option
								value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
							<html:options collection="colecaoCobrancaDebitoSituacao"
								labelProperty="descricao" property="id" />
						</logic:present>
					</html:select></strong></td>
				</tr>
				<tr>
					<td height="24" colspan="4" class="style1"></td>
				</tr>
				<tr>
					<td class="style1"><strong>Ciclo:</strong></td>
					<td><html:text property="mesAnoReferencia" size="8" maxlength="7"
										onkeyup="mascaraAnoMes(this, event);somente_numero(this);" />&nbsp;mm/aaaa
					</td>
				</tr>
				<tr>
					<td class="style1"><strong>Per&iacute;odo de Data de
					Emiss&atilde;o:</strong></td>

					<td colspan="3"><strong> <html:text property="dataEmissaoInicio"
						size="10"
						onkeyup="mascaraData(this, event); replicaDados(document.FiltrarDocumentosCobrancaActionForm.dataEmissaoInicio, document.FiltrarDocumentosCobrancaActionForm.dataEmissaoFim);somente_numero(this);"
						maxlength="10" /> <a
						href="javascript:abrirCalendario('FiltrarDocumentosCobrancaActionForm', 'dataEmissaoInicio')">
					 <img border="0"
						src="<bean:message key="caminho.imagens"/>calendario.gif"
						width="20" border="0" align="absmiddle" alt="Exibir Calendário" /> </a>
					a</strong> <html:text property="dataEmissaoFim" size="10"
						maxlength="10" onkeyup="mascaraData(this, event);" /> <a
						href="javascript:abrirCalendario('FiltrarDocumentosCobrancaActionForm', 'dataEmissaoFim')">
					 <img border="0"
						src="<bean:message key="caminho.imagens"/>calendario.gif"
						width="20" border="0" align="absmiddle" alt="Exibir Calendário" /></a>
					dd/mm/aaaa</td>
				</tr>
				<tr>
					<td class="style1"><strong>Intervalo de Valor do Documento:</strong></td>

					<td colspan="3" class="style1"><strong> <html:text
						property="valorInicial" size="14" style="text-align:right;"
						onkeyup="formataValorMonetario(this, 14); replicaDados(document.FiltrarDocumentosCobrancaActionForm.valorInicial, document.FiltrarDocumentosCobrancaActionForm.valorFinal);"
						maxlength="14" /> a</strong> <strong> <html:text
						property="valorFinal" style="text-align:right;"
						onkeyup="formataValorMonetario(this, 14);" size="14"
						maxlength="14" /></strong></td>
				</tr>
				<tr>
					<td class="style1"><strong>Motivo da N&atilde;o Entrega do
					Documento:</strong></td>

					<td colspan="4" class="style1"><strong> <html:select
						property="motivoNaoEntregaDocumento" style="width: 230px;"
						multiple="mutiple" size="3">
						<logic:present name="colecaoMotivo">
							<html:option
								value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
							<html:options collection="colecaoMotivo"
								labelProperty="motivoNaoeEntregaDocumento" property="id" />
						</logic:present>
					</html:select></strong></td>
				</tr>
				<tr>
					<td class="style1"><strong>Perfil do Im&oacute;vel:</strong></td>
					<td colspan="4" class="style1"><strong> <html:select
						property="imovelPerfil" style="width: 230px;" multiple="mutiple"
						size="3">
						<logic:present name="colecaoImovelPerfil">
							<html:option
								value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
							<html:options collection="colecaoImovelPerfil"
								labelProperty="descricao" property="id" />
						</logic:present>
					</html:select></strong></td>
				</tr>
				<tr>
					<td class="style1"><strong>Categoria:</strong></td>
					<td colspan="4" class="style1"><strong> <html:select
						property="idCategoria" style="width: 230px;" multiple="mutiple"
						size="3">
						<logic:present name="colecaoCategorias">
							<html:option
								value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
							<html:options collection="colecaoCategorias"
								labelProperty="descricao" property="id" />
						</logic:present>
					</html:select></strong></td>
				</tr>
				<tr>
					<td class="style1"><strong>Firma:</strong></td>
					<td colspan="4" class="style1"><strong> <html:select
						property="idFirma" style="width: 230px;" multiple="mutiple"
						size="3">
						<logic:present name="colecaoEmpresas">
							<html:option
								value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
							<html:options collection="colecaoEmpresas"
								labelProperty="descricao" property="id" />
						</logic:present>
					</html:select></strong></td>
				</tr>

				<tr>
					<td>&nbsp;</td>

					<td colspan="3">&nbsp;</td>
				</tr>
				<tr>
					<td></td>

				</tr>
			</table>
			<table width="100%">
				<tr>
					<td colspan="3" align="left">
						<div align="left">
							<logic:notPresent name="ehPopup">
								<input type="button" 
									value="Limpar"
									name="Submit123" 
									class="bottonRightCol"
									onclick="javascript:window.location.href='/gsan/exibirFiltrarDocumentosCobrancaAction.do?menu=sim';">
							</logic:notPresent>
							
							<logic:present name="ehPopup">
								<input type="button" 
									value="Limpar"
									name="Submit123" 
									class="bottonRightCol"
									onclick="javascript:window.location.href='/gsan/exibirFiltrarDocumentosCobrancaAction.do?menu=sim&ehPopup=true';">
							</logic:present>
						</div>
					</td>

					<td colspan="3">
					<div align="right">
					  <gsan:controleAcessoBotao name="Submit23" value="Consultar" onclick="validarFormSubmit();" url="filtrarDocumentosCobrancaAction.do"/>

					</div>
					</td>
				</tr>
			</table>
			</DIV>
		</td>
		</tr>
		</table>

		<logic:notPresent name="ehPopup">
			<%@ include file="/jsp/util/rodape.jsp"%>
		</logic:notPresent>


</html:form>

<script language="JavaScript">
<!-- Begin
	bloquearLSQLS();
-->
</script>
</html:html>
