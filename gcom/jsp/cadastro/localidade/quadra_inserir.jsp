<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ page import="gcom.util.ConstantesSistema"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan"%>

<%@ page import="gcom.cadastro.localidade.QuadraFace"%>

<%@ page import="gcom.cadastro.sistemaparametro.SistemaParametro"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>

<%@ include file="/jsp/util/titulo.jsp"%>

<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">

<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>

<SCRIPT LANGUAGE="JavaScript">
<!--

function limpar(tipo){

	var form = document.forms[0];

	switch (tipo){
        case 1:
		   form.localidadeID.value = "";
		   form.localidadeNome.value = "";
		   form.setorComercialID.value = "";
		   form.setorComercialCD.value = "";
		   form.setorComercialNome.value = "";
		   
		   form.codigoRota.value = "";
		   form.rotaMensagem.value = "";
		   form.rotaID.value = "";

		   form.quadraNM.value = "";
		   //Coloca o foco no objeto selecionado
		   form.localidadeID.focus();
		   break;
		case 2:
		   form.setorComercialID.value = "";
		   form.setorComercialCD.value = "";
		   form.setorComercialNome.value = "";
		   
		   form.codigoRota.value = "";
		   form.rotaMensagem.value = "";
		   form.rotaID.value = "";
		   form.bairroDescricao.value = "";
   		   form.bairroID.value = "";
		   
		   form.quadraNM.value = "";
		   //Coloca o foco no objeto selecionado
		   form.setorComercialCD.focus();
		   break;
		case 4:
		   form.distritoOperacionalID.value = "";
		   form.distritoOperacionalDescricao.value = "";
           form.distritoOperacionalDescricao.style.color = "#000000";

		   //Coloca o foco no objeto selecionado
		   form.distritoOperacionalID.focus();
		   break;
		case 5:
		   form.setorCensitarioID.value = "";
		   form.setorCensitarioDescricao.value = "";
		   form.setorCensitarioDescricao.style.color = "#000000";

		   //Coloca o foco no objeto selecionado
		   form.setorCensitarioID.focus();
		   break;
		case 7:
		   form.setorComercialID.value = "";
		   form.setorComercialCD.value = "";
		   form.setorComercialNome.value = "";
		   form.localidadeNome.value = "";
		   form.quadraNM.value = "";
		   
		   form.codigoRota.value = "";
		   form.rotaMensagem.value = "";
		   form.rotaID.value = "";
		   form.bairroDescricao.value = "";
   		   form.bairroID.value = "";
		   		   
		   break;
		case 8:
		   form.setorComercialID.value = "";
		   form.setorComercialNome.value = "";
		   form.quadraNM.value = "";
		   
		   form.codigoRota.value = "";
		   form.rotaMensagem.value = "";
		   form.rotaID.value = "";
		   form.indicadorRelacionamentoQuadraBairro.value = "2";
		   		   
		   break;
		case 9 :
		   form.rotaMensagem.value = "";
		   form.rotaID.value = "";
		   form.codigoRota.value = "";
		   form.bairroDescricao.value = "";
   		   form.bairroID.value = "";
		   //Coloca o foco no objeto selecionado
		   form.codigoRota.focus();
		case 10:
		   form.distritoOperacionalDescricao.value = "";
           form.distritoOperacionalDescricao.style.color = "#000000";

		   //Coloca o foco no objeto selecionado
		   form.distritoOperacionalID.focus();
		   break;
		case 11:
		   form.setorCensitarioDescricao.value = "";
		   form.setorCensitarioDescricao.style.color = "#000000";
		   
		   //Coloca o foco no objeto selecionado
		   form.setorCensitarioID.focus();
		   break;   
		case 12 :
   			form.rotaMensagem.value = "";
   			form.rotaID.value = "";
		   //Coloca o foco no objeto selecionado
		   form.codigoRota.focus();   
		   break;
		   
		case 13 :
   			form.bairroDescricao.value = "";
   			form.bairroID.value = "";
		    form.bairroID.focus();   
		   	break;
		   	
	   default:
          break;
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
			abrirPopup(url + "?" + "tipo=" + tipo + "&" + objeto + "=" + codigoObjeto + "&caminhoRetornoTelaPesquisaBairro=" + "/exibirInserirQuadra.do", altura, largura);
		}
	}
}

function abrirPopupQuadra(url, tipo, objeto, codigoObjeto, altura, largura, msg){

	var form = document.forms[0];
		if (form.localidadeID.value.length < 1 || isNaN(form.localidadeID.value)){
			alert('Informe Localidade.');
		}
		else if (form.setorComercialCD.value.length < 1 || isNaN(form.setorComercialCD.value)){
			alert('Informe Setor Comercial.');
		}
		else{
			abrirPopup(url + "&codigoSetorComercial=" + form.setorComercialCD.value  + "&idLocalidade=" + form.localidadeID.value , altura, largura);
		}
	
}


function recuperarDadosQuatroParametros(idRegistro, descricaoRegistro, codigoRegistro, tipoConsulta) {

	var form = document.forms[0];

	if (tipoConsulta == 'setorComercialOrigem') {
      form.setorComercialCD.value = codigoRegistro;
      form.setorComercialID.value = idRegistro;
	  form.setorComercialNome.value = descricaoRegistro;
	  form.setorComercialNome.style.color = "#000000";
	  
      form.action = 'exibirInserirQuadraAction.do?objetoConsulta=2';
	  submeterFormPadrao(form);
	}

}


function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {

	var form = document.forms[0];

	if (tipoConsulta == 'localidade') {
      form.localidadeID.value = codigoRegistro;
	  form.localidadeNome.value = descricaoRegistro;
	  form.localidadeNome.style.color = "#000000";
	}
	
	if (tipoConsulta == 'ibgeSetorCensitario') {
      form.setorCensitarioID.value = codigoRegistro;
	  form.setorCensitarioDescricao.value = descricaoRegistro;
	  form.setorCensitarioDescricao.style.color = "#000000";
	}
	
	if (tipoConsulta == 'distrito') {
      form.distritoOperacionalID.value = codigoRegistro;
	  form.distritoOperacionalDescricao.value = descricaoRegistro;
	  form.distritoOperacionalDescricao.style.color = "#000000";
	}
	
	if (tipoConsulta == 'bairro') {
   		form.bairroID.value = codigoRegistro;
 	 	form.bairroDescricao.value = descricaoRegistro;
 	    form.bairroDescricao.style.color = "#000000";
	}
}

function habilitacaoDistritoOperacional(indicadorRedeAguaAux){
	var form = document.forms[0];
		if (indicadorRedeAguaAux[0].checked){
			limpar(4);
			form.distritoOperacionalID.disabled = true;
			form.distritoOperacionalID.value = ""; 
		}else{
			form.distritoOperacionalID.disabled = false;
		}
   }
   
   
function habilitacaoSistemaEsgotoBacia(indicadorRedeEsgotoAux){
	
	var form = document.forms[0];
	if (indicadorRedeEsgotoAux[0].checked){
		form.sistemaEsgotoID.disabled = true;
		form.sistemaEsgotoID.value = "-1"; 
		form.baciaID.disabled = true;
		form.baciaID.value = "-1"; 
	}else{
		form.sistemaEsgotoID.disabled = false;
		form.baciaID.disabled = false;
	}
}

//Verifica o valor do objeto radio em tempo de execução
function verificarMarcacao(valor, tipoIndicador){
	if(tipoIndicador == "Agua"){
		document.getElementById("indicadorRedeAguaHTML").value = valor;
	}
	else{
		document.getElementById("indicadorRedeEsgotoHTML").value = valor;
	}
}

function atualizarDadosIndicador(formulario){
	var objIndicadorRedeAgua = returnObject(formulario, "indicadorRedeAguaAux");
	var objIndicadorRedeEsgoto = returnObject(formulario, "indicadorRedeEsgotoAux");
	var valorIndicadorAgua = objIndicadorRedeAgua.value;
	var valorIndicadorEsgoto = objIndicadorRedeEsgoto.value;
	
	for(x=0; x < formulario.elements.length; x++){
		if(formulario.elements[x].type == "radio"){
			if(formulario.elements[x].name == "indicadorRedeEsgotoAux"){
				if (formulario.elements[x].checked){
					valorIndicadorEsgoto = formulario.elements[x].value;
				}
			}
			else{
				if (formulario.elements[x].checked){
					valorIndicadorAgua = formulario.elements[x].value;
				}
			}
		}
	}

	document.getElementById("indicadorRedeAguaHTML").value = valorIndicadorAgua;
	document.getElementById("indicadorRedeEsgotoHTML").value = valorIndicadorEsgoto;
}

function validarIndicadorRedeAguaAux(){

    var form = document.forms[0];
    
    var indice;
    var array = new Array(form.indicadorRedeAguaAux.length);
    var selecionado = "";
    var formulario = document.forms[0]; 
    for(indice = 0; indice < form.elements.length; indice++){
	   if (form.elements[indice].type == 'radio' && form.elements[indice].checked == true && formulario.elements[indice].name == "indicadorRedeAguaAux") {
	      selecionado = form.elements[indice].value;
	      indice = form.elements.length;
	   }
    }    
	if(selecionado == ''){
		alert('Informe Rede de Água.');
		indicadorRedeAguaAux.focus();
	}	
}

function validarIndicadorRedeEsgotoAux(){

    var form = document.forms[0];
    
    var indice;
    var array = new Array(form.indicadorRedeEsgotoAux.length);
    var selecionado = "";
    var formulario = document.forms[0]; 
    for(indice = 0; indice < form.elements.length; indice++){
	   if (form.elements[indice].type == 'radio' && form.elements[indice].checked == true && formulario.elements[indice].name == "indicadorRedeEsgotoAux") {
	      selecionado = form.elements[indice].value;
	      indice = form.elements.length;
	   }
    }    
	if(selecionado == ''){
		alert('Informe Rede de Esgoto.');
		indicadorRedeEsgotoAux.focus();
	}	
}

function receberRota(codigoRota,destino) {
 	  var form = document.forms[0];
	  form.rotaID.value = codigoRota;		
	  form.action = 'exibirInserirQuadraAction.do?objetoConsulta=8';
	  form.submit();
}

//-->
</SCRIPT>

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>

<html:javascript staticJavascript="true" formName="InserirQuadraActionForm" />

<logic:equal name="permissaoAdicionarQuadraFace" value="2">

<!-- CASO A EMPRESA NÃO TRABALHE COM FACE DE QUADRA -->

<SCRIPT LANGUAGE="JavaScript">
<!--
function validarForm(formulario){
	
	var form = document.forms[0];
	
	if (validateInserirQuadraActionForm(formulario)){
	
		var objLocalidadeID = returnObject(formulario, "localidadeID");
		var objSetorComercialCD = returnObject(formulario, "setorComercialCD");
		var objSetorComercialID = returnObject(formulario, "setorComercialID");
		var objQuadraNM = returnObject(formulario, "quadraNM");
		var objSistemaEsgotoID = returnObject(formulario, "sistemaEsgotoID");
		var objBaciaID = returnObject(formulario, "baciaID");
		var objDistritoOperacionalID = returnObject(formulario, "distritoOperacionalID");
		var objSetorCensitarioID = returnObject(formulario, "setorCensitarioID");
		var objRotaID = returnObject(formulario, "rotaID");
		var objPerfilQuadra = returnObject(formulario, "perfilQuadra");
		var objBairroID = returnObject(formulario, "bairroID");
	
		var objIndicadorAguaHTML = document.getElementById("indicadorRedeAguaHTML");
		var objIndicadorEsgotoHTML = document.getElementById("indicadorRedeEsgotoHTML");
		
		if(objLocalidadeID.value.length < 1){
			alert("Informe Localidade.");
			objLocalidadeID.focus();
		}
		else if(objLocalidadeID.value.length > 0 && !testarCampoValorZero(objLocalidadeID, "Localidade")){
			objLocalidadeID.focus();
		}
		if(objSetorComercialCD.value.length < 1){
			alert("Informe Setor Comercial.");
			objSetorComercialCD.focus();
		}
		else if(objSetorComercialCD.value.length > 0 && !testarCampoValorZero(objSetorComercialCD, "Setor Comercial")){
			objSetorComercialCD.focus();
		}
		if(objQuadraNM.value.length < 1){
			alert("Informe Quadra.");
			objQuadraNM.focus();
		}
		else if(objQuadraNM.value.length > 0 && !testarCampoValorZero(objQuadraNM, "Quadra")){
			objQuadraNM.focus();
		}
		if(objPerfilQuadra.value == -1){
			alert("Informe Perfil da Quadra.");
			objPerfilQuadra.focus();
		}
		
		validarIndicadorRedeAguaAux();
		validarIndicadorRedeEsgotoAux();
		//com rede ou parcial
		if(document.forms[0].indicadorRedeEsgotoAux[1].checked || document.forms[0].indicadorRedeEsgotoAux[2].checked){
			if(objSistemaEsgotoID.options[objSistemaEsgotoID.options.selectedIndex].value == "-1"){
				alert("Informe Sistema de Esgoto.");
				objSistemaEsgotoID.focus();
			}
			
			else if (objBaciaID.length == "1"){
				alert("Sistema de Esgoto selecionado não contém Bacia.");
				objSistemaEsgotoID.focus();
			}
			else if (objBaciaID.options[objBaciaID.options.selectedIndex].value == "-1") {
				alert("Informe Bacia.");
				objSistemaEsgotoID.focus();
			}
			//com rede ou parcial
			else if(document.forms[0].indicadorRedeAguaAux[1].checked || document.forms[0].indicadorRedeAguaAux[2].checked){
				if (objDistritoOperacionalID.value.length < 1) {
					alert("Informe Distrito Operacional.");
					objDistritoOperacionalID.focus();
				}
				else if(objDistritoOperacionalID.value.length > 0 && !testarCampoValorZero(objDistritoOperacionalID, "Distrito Operacional")){
					objDistritoOperacionalID.focus();
				}
				else if(objSetorCensitarioID.value.length > 0 && !testarCampoValorZero(objSetorCensitarioID, "Setor Censitário")){
					objSetorCensitarioID.focus();
				}
				else if(objRotaID.value.length < 1){
					alert("Informe Rota.");
					objRotaID.focus();
				}
				else if(form.indicadorRelacionamentoQuadraBairro.value == "1" && form.bairroID.value == ""){
					
					alert("Informe Bairro.");
					objBairroID.focus();
				}
				else if (validateInserirQuadraActionForm(formulario)){
					botaoAvancarTelaEspera('/gsan/inserirQuadraAction.do');
				}
			}
			else if(objDistritoOperacionalID.value.length > 0 && !testarCampoValorZero(objDistritoOperacionalID, "Distrito Operacional")){
				objDistritoOperacionalID.focus();
			}
			else if(objSetorCensitarioID.value.length > 0 && !testarCampoValorZero(objSetorCensitarioID, "Setor Censitário")){
				objSetorCensitarioID.focus();
			}
			else if(objRotaID.value.length < 1){
				alert("Informe Rota.");
				objRotaID.focus();
			}
			else if(form.indicadorRelacionamentoQuadraBairro.value == "1" && form.bairroID.value == ""){
				
				alert("Informe Bairro.");
				objBairroID.focus();
			}
			else if (validateInserirQuadraActionForm(formulario)){
				botaoAvancarTelaEspera('/gsan/inserirQuadraAction.do');
			}
		}
		//com rede ou parcial
		else if(document.forms[0].indicadorRedeAguaAux[1].checked || document.forms[0].indicadorRedeAguaAux[2].checked){
			if (objDistritoOperacionalID.value.length < 1) {
				alert("Informe Distrito Operacional.");
				objDistritoOperacionalID.focus();
			}
			else if(objDistritoOperacionalID.value.length > 0 && !testarCampoValorZero(objDistritoOperacionalID, "Distrito Operacional")){
				objDistritoOperacionalID.focus();
			}
			else if(objSetorCensitarioID.value.length > 0 && !testarCampoValorZero(objSetorCensitarioID, "Setor Censitário")){
				objSetorCensitarioID.focus();
			}
			else if(objRotaID.value.length < 1){
				alert("Informe Rota.");
				objRotaID.focus();
			}
			else if(form.indicadorRelacionamentoQuadraBairro.value == "1" && form.bairroID.value == ""){
				
				alert("Informe Bairro.");
				objBairroID.focus();
			}
			else if (validateInserirQuadraActionForm(formulario)){
				botaoAvancarTelaEspera('/gsan/inserirQuadraAction.do');
			}
		}
		else if(objDistritoOperacionalID.value.length > 0 && !testarCampoValorZero(objDistritoOperacionalID, "Distrito Operacional")){
			objDistritoOperacionalID.focus();
		}
		else if(objSetorCensitarioID.value.length > 0 && !testarCampoValorZero(objSetorCensitarioID, "Setor Censitário")){
			objSetorCensitarioID.focus();
		}
		else if(objRotaID.value.length < 1){
			alert("Informe Rota.");
			objRotaID.focus();
		}
		else if(objRotaID.value.length < 1){
			alert("Informe Rota.");
			objRotaID.focus();
		}
		else if(form.indicadorRelacionamentoQuadraBairro.value == "1" && form.bairroID.value == ""){
			
			alert("Informe Bairro.");
			objBairroID.focus();
		}
		else if (validateInserirQuadraActionForm(formulario)){
			botaoAvancarTelaEspera('/gsan/inserirQuadraAction.do');
		}
	}
}

var bCancel = false; 

function validateInserirQuadraActionForm(form) {                                                                   
	
	if (bCancel) 
    	return true; 
    else 
    	return validateCaracterEspecial(form) && validateRequired(form) && validateLong(form) && validateInteiroZeroPositivo(form); 
} 

function caracteresespeciais () { 
     this.aa = new Array("localidadeID", "Localidade possui caracteres especiais.", new Function ("varName", " return this[varName];"));
     this.ab = new Array("setorComercialCD", "Setor Comercial possui caracteres especiais.", new Function ("varName", " return this[varName];"));
     this.ac = new Array("quadraNM", "Quadra possui caracteres especiais.", new Function ("varName", " return this[varName];"));
     this.ad = new Array("rotaID", "Rota possui caracteres especiais.", new Function ("varName", " return this[varName];"));
     this.ae = new Array("distritoOperacionalID", "Distrito Operacional possui caracteres especiais.", new Function ("varName", " return this[varName];"));
     this.af = new Array("setorCensitarioID", "Setor Censitário possui caracteres especiais.", new Function ("varName", " return this[varName];"));
} 

function required () { 
     this.aa = new Array("localidadeID", "Informe Localidade.", new Function ("varName", " return this[varName];"));
     this.ab = new Array("setorComercialCD", "Informe Setor Comercial.", new Function ("varName", " return this[varName];"));
     this.ac = new Array("quadraNM", "Informe Quadra.", new Function ("varName", " return this[varName];"));
     this.ad = new Array("rotaID", "Informe Rota.", new Function ("varName", " return this[varName];"));
     this.ae = new Array("perfilQuadra", "Informe Perfil da Quadra.", new Function ("varName", " return this[varName];"));
} 

function IntegerValidations () { 
     this.aa = new Array("localidadeID", "Localidade deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
     this.ab = new Array("setorComercialCD", "Setor Comercial deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
     this.ac = new Array("quadraNM", "Quadra deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
     this.ad = new Array("distritoOperacionalID", "Distrito Operacional deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
     this.ae = new Array("setorCensitarioID", "Setor Censitário deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
} 

function InteiroZeroPositivoValidations () { 
     this.aa = new Array("rotaID", "Rota deve somente conter números positivos ou zero.", new Function ("varName", " return this[varName];"));
}

//-->
</SCRIPT>

<!-- FIM - CASO A EMPRESA NÃO TRABALHE COM FACE DE QUADRA -->
</logic:equal>


<logic:equal name="permissaoAdicionarQuadraFace" value="1">

<!-- CASO A EMPRESA TRABALHE COM FACE DE QUADRA -->

<SCRIPT LANGUAGE="JavaScript">
<!--
	function validarForm(formulario){
		
		if (validateInserirQuadraActionForm(formulario)){
		
			var permissaoAdicionarQuadraFace = document.getElementById("permissaoAdicionarQuadraFace");
			var quadraFaceInformada = document.getElementById("quadraFaceInformada");
			
			if(permissaoAdicionarQuadraFace.value == "1" && quadraFaceInformada.value == "2"){
				alert("Informe Face(s) da Quadra.");
			}
			else{
				botaoAvancarTelaEspera('/gsan/inserirQuadraAction.do');
			}
		}
	}
	
	var bCancel = false; 

	function validateInserirQuadraActionForm(form) {                                                                   
	
		if (bCancel) 
    		return true; 
    	else 
    		return validateCaracterEspecial(form) && validateRequired(form) && validateLong(form) && validateInteiroZeroPositivo(form); 
	} 

	function caracteresespeciais () { 
     this.aa = new Array("localidadeID", "Localidade possui caracteres especiais.", new Function ("varName", " return this[varName];"));
     this.ab = new Array("setorComercialCD", "Setor Comercial possui caracteres especiais.", new Function ("varName", " return this[varName];"));
     this.ac = new Array("quadraNM", "Quadra possui caracteres especiais.", new Function ("varName", " return this[varName];"));
     this.ad = new Array("rotaID", "Rota possui caracteres especiais.", new Function ("varName", " return this[varName];"));
     this.af = new Array("setorCensitarioID", "Setor Censitário possui caracteres especiais.", new Function ("varName", " return this[varName];"));
	} 

	function required () { 
     this.aa = new Array("localidadeID", "Informe Localidade.", new Function ("varName", " return this[varName];"));
     this.ab = new Array("setorComercialCD", "Informe Setor Comercial.", new Function ("varName", " return this[varName];"));
     this.ac = new Array("quadraNM", "Informe Quadra.", new Function ("varName", " return this[varName];"));
     this.ad = new Array("rotaID", "Informe Rota.", new Function ("varName", " return this[varName];"));
     this.ae = new Array("perfilQuadra", "Informe Perfil da Quadra.", new Function ("varName", " return this[varName];"));
	} 

	function IntegerValidations () { 
     this.aa = new Array("localidadeID", "Localidade deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
     this.ab = new Array("setorComercialCD", "Setor Comercial deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
     this.ac = new Array("quadraNM", "Quadra deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
     this.ae = new Array("setorCensitarioID", "Setor Censitário deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
	} 

	function InteiroZeroPositivoValidations () { 
     this.aa = new Array("rotaID", "Rota deve somente conter números positivos ou zero.", new Function ("varName", " return this[varName];"));
	}
	
	function removerQuadraFace(numeroQuadraFace){
 	
 		var form = document.forms[0];
 		
 		if (confirm("Confirma remoção?")){
			
			form.action = "/gsan/exibirInserirQuadraAction.do?numeroQuadraFace=" + numeroQuadraFace;		
			submeterFormPadrao(form);
		}
	}
 
//-->
</SCRIPT>

<!-- FIM - CASO A EMPRESA TRABALHE COM FACE DE QUADRA -->
</logic:equal>

</head>

<logic:equal name="permissaoAdicionarQuadraFace" value="2">
	
	<!-- CASO A EMPRESA NÃO TRABALHE COM FACE DE QUADRA -->
	<body leftmargin="5" topmargin="5" onload="atualizarDadosIndicador(document.forms[0]); setarFoco('${requestScope.nomeCampo}');">
	<!-- FIM- CASO A EMPRESA NÃO TRABALHE COM FACE DE QUADRA -->
	
</logic:equal>

<logic:equal name="permissaoAdicionarQuadraFace" value="1">
	
	<!-- CASO A EMPRESA TRABALHE COM FACE DE QUADRA -->
	<body leftmargin="5" topmargin="5" onload="setarFoco('${requestScope.nomeCampo}');">
	<!-- FIM - CASO A EMPRESA TRABALHE COM FACE DE QUADRA -->
	
</logic:equal>

<input type="hidden" id="permissaoAdicionarQuadraFace" name="permissaoAdicionarQuadraFace" 
value="${requestScope.permissaoAdicionarQuadraFace}">

<div id="formDiv">

<html:form action="/exibirInserirQuadraAction" method="post">

	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>
	
	<html:hidden property="indicadorRelacionamentoQuadraBairro" />
	
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
					<td class="parabg">Inserir Quadra</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>

			<table width="100%" border="0">
				<tr>
					<td>Para adicionar a(s) quadra(s), informe os dados abaixo:</td>
					<logic:present scope="application" name="urlHelp">
						<td align="right"><a href="javascript:abrirPopupHelp('${applicationScope.urlHelp}cadastroLocalizacaoQuadraInserir', 500, 700);"><span style="font-weight: bold"><font color="#3165CE">Ajuda</font></span></a></td>									
					</logic:present>
					<logic:notPresent scope="application" name="urlHelp">
						<td align="right"><span style="font-weight: bold"><font color=#696969><u>Ajuda</u></font></span></td>									
					</logic:notPresent>
				</tr>
			</table>

			<table width="100%" border="0">
				<tr>
					<td colspan="2">
					
						<table width="100%" align="center" bgcolor="#90c7fc" border="0">
						<tr>
							<td><strong>Localização</strong></td>
						</tr>
						<tr bgcolor="#cbe5fe">
							<td width="100%" align="center">

							<table width="100%" border="0">
								
								<tr>
									<td width="21%"><strong>Localidade:<font color="#FF0000">*</font></strong></td>
									<td><html:text property="localidadeID" size="5" maxlength="3"
										tabindex="1"
										onkeypress="limpar(7);validaEnterComMensagem(event, 'exibirInserirQuadraAction.do?objetoConsulta=1', 'localidadeID', 'Localidade');" 
										onkeyup="somente_numero_zero_a_nove(this);" />
				
									<a
										href="javascript:abrirPopup('exibirPesquisarLocalidadeAction.do', 250, 495);">
									<img width="23" height="21" border="0"
										src="<bean:message key="caminho.imagens"/>pesquisa.gif"
										title="Pesquisar Localidade" /></a> 
									
									<logic:present name="corLocalidade">
				
										<logic:equal name="corLocalidade" value="exception">
											<html:text property="localidadeNome" size="45" readonly="true"
												style="background-color:#EFEFEF; border:0; color: #ff0000" />
										</logic:equal>
				
										<logic:notEqual name="corLocalidade" value="exception">
											<html:text property="localidadeNome" size="45" readonly="true"
												style="background-color:#EFEFEF; border:0; color: #000000" />
										</logic:notEqual>
				
									</logic:present> 
									
									<logic:notPresent name="corLocalidade">
				
										<logic:empty name="InserirQuadraActionForm"
											property="localidadeID">
											<html:text property="localidadeNome" size="45" value=""
												readonly="true"
												style="background-color:#EFEFEF; border:0; color: #ff0000" />
										</logic:empty>
										<logic:notEmpty name="InserirQuadraActionForm"
											property="localidadeID">
											<html:text property="localidadeNome" size="45" readonly="true"
												style="background-color:#EFEFEF; border:0; color: #000000" />
										</logic:notEmpty>
				
				
									</logic:notPresent> <a href="javascript:limpar(1);"> <img
										src="<bean:message key="caminho.imagens"/>limparcampo.gif"
										border="0" title="Apagar Localidade" /> </a></td>
								</tr>
								
								<tr>
									<td><strong>Setor Comercial:<font color="#FF0000">*</font></strong></td>
									<td><html:text property="setorComercialCD" size="5" maxlength="3"
										tabindex="2"
										onkeypress="limpar(8); validaEnterDependenciaComMensagem(event, 'exibirInserirQuadraAction.do?objetoConsulta=2', document.forms[0].setorComercialCD, document.forms[0].localidadeID.value, 'Localidade','Setor Comercial');" 
										onkeyup="somente_numero_zero_a_nove(this);" />
				
									<a
										href="javascript:chamarPopup('exibirPesquisarSetorComercialAction.do', 'setorComercialOrigem', 'idLocalidade', document.forms[0].localidadeID.value, 275, 480, 'Informe a Localidade');">
									<img width="23" height="21" border="0"
										src="<bean:message key="caminho.imagens"/>pesquisa.gif"
										title="Pesquisar Setor Comercial" /></a> <logic:present
										name="corSetorComercial">
				
										<logic:equal name="corSetorComercial" value="exception">
											<html:text property="setorComercialNome" size="45"
												readonly="true"
												style="background-color:#EFEFEF; border:0; color: #ff0000" />
										</logic:equal>
				
										<logic:notEqual name="corSetorComercial" value="exception">
											<html:text property="setorComercialNome" size="45"
												readonly="true"
												style="background-color:#EFEFEF; border:0; color: #000000" />
										</logic:notEqual>
				
									</logic:present> <logic:notPresent name="corSetorComercial">
				
										<logic:empty name="InserirQuadraActionForm"
											property="setorComercialCD">
											<html:text property="setorComercialNome" value="" size="45"
												readonly="true"
												style="background-color:#EFEFEF; border:0; color: #ff0000" />
										</logic:empty>
										<logic:notEmpty name="InserirQuadraActionForm"
											property="setorComercialCD">
											<html:text property="setorComercialNome" size="45"
												readonly="true"
												style="background-color:#EFEFEF; border:0; color: #000000" />
										</logic:notEmpty>
				
									</logic:notPresent> <html:hidden property="setorComercialID" /> <a
										href="javascript:limpar(2);"> <img
										src="<bean:message key="caminho.imagens"/>limparcampo.gif"
										border="0" title="Apagar Setor Comercial" /> </a></td>
								</tr>
								
								<tr>
									<td><strong>Quadra:<font color="#FF0000">*</font></strong></td>
									<td><html:text property="quadraNM" size="5" tabindex="3"
										maxlength="<%=""+SistemaParametro.NUMERO_DIGITOS_QUADRA%>" 
										onkeyup="somente_numero_zero_a_nove(this);" />&nbsp;<a
										href="javascript:abrirPopupQuadra('exibirPesquisarQuadraAction.do?consulta=sim');"><img
										width="23" height="21" border="0"
										src="<bean:message key="caminho.imagens"/>pesquisa_verde.gif"
										title="Pesquisar Quadra" /></a></td>
								</tr>
								
								<tr>
									<td><strong>Rota:<font color="#FF0000">*</font></strong></td>
									<td>
									<html:hidden property="rotaID" />
									<html:text property="codigoRota" size="5" maxlength="4"
										tabindex="18"
										onkeypress="validaEnterDependenciaComMensagemAceitaZERO(event, 'exibirInserirQuadraAction.do?objetoConsulta=8', document.forms[0].codigoRota, document.forms[0].setorComercialCD.value, 'Setor Comercial','Rota');"
										onkeyup="limpar(12);somente_numero_zero_a_nove(this);" /> <a
										href="javascript:abrirPopup('exibirPesquisarInformarRotaLeituraAction.do?limparForm=sim&idLocalidade='+document.forms[0].localidadeID.value+'&codigoSetorComercial='+document.forms[0].setorComercialCD.value+'&destino=', 250, 495);">
									<img width="23" height="21" border="0"
										src="<bean:message key="caminho.imagens"/>pesquisa.gif"
										title="Pesquisar Rota" /></a> <logic:present
										name="corRotaMensagem">
				
										<logic:equal name="corRotaMensagem" value="exception">
											<html:text property="rotaMensagem" size="45" readonly="true"
												style="background-color:#EFEFEF; border:0; color: #ff0000" />
										</logic:equal>
				
										<logic:notEqual name="corRotaMensagem" value="exception">
											<html:text property="rotaMensagem" size="45" readonly="true"
												style="background-color:#EFEFEF; border:0; color: #000000" />
										</logic:notEqual>
				
									</logic:present> <logic:notPresent name="corRotaMensagem">
				
										<logic:empty name="InserirQuadraActionForm" property="rotaID">
											<html:text property="rotaMensagem" size="45" readonly="true"
												style="background-color:#EFEFEF; border:0; color: #ff0000" />
										</logic:empty>
										<logic:notEmpty name="InserirQuadraActionForm" property="rotaID">
											<html:text property="rotaMensagem" size="45" readonly="true"
												style="background-color:#EFEFEF; border:0; color: #000000" />
										</logic:notEmpty>
				
									</logic:notPresent> <a href="javascript:limpar(9);"> <img
										src="<bean:message key="caminho.imagens"/>limparcampo.gif"
										border="0" title="Apagar Rota" /> </a></td>
								</tr>
								
								<html:hidden property="municipioID" />
								
								<tr>
									<td><strong>Bairro:</strong></td>
									<td>
										<html:text  property="bairroID" 
													maxlength="4" 
													tabindex="19"
													size="5"
													onkeyup="somente_numero_zero_a_nove(this);"
													onkeypress="validaEnterDependenciaComMensagem(event, 'exibirInserirQuadraAction.do?objetoConsulta=9', document.forms[0].bairroID, document.forms[0].municipioID.value, 'Setor Comercial', 'Bairro');" />
													
											<a href="javascript: abrirPopup('exibirPesquisarBairroAction.do?idMunicipio='+document.forms[0].municipioID.value, 400, 800);">
												<img width="23" 
													 height="21" 
													 border="0" 
													 src="<bean:message key="caminho.imagens"/>pesquisa.gif" 
													 title="Pesquisar Bairro" /></a> 
										
										<logic:present name="corBairro">
											<logic:equal name="corBairro" value="exception">
												<html:text property="bairroDescricao" size="30" readonly="true"
													style="background-color:#EFEFEF; border:0; color: #ff0000" />
											</logic:equal>
					
											<logic:notEqual name="corBairro" value="exception">
												<html:text property="bairroDescricao" size="45" readonly="true"
													style="background-color:#EFEFEF; border:0; color: #000000" />
											</logic:notEqual>
										</logic:present> 
										
										<logic:notPresent name="corBairro">
											<logic:empty name="InserirQuadraActionForm" property="bairroID">
												<html:text property="bairroDescricao" size="30" 
														   value="" 
														   readonly="true" 
														   style="background-color:#EFEFEF; border:0; color: #ff0000" />
											</logic:empty>
											
											<logic:notEmpty name="InserirQuadraActionForm" property="bairroID">
												<html:text property="bairroDescricao" size="30" readonly="true"
													style="background-color:#EFEFEF; border:0; color: #000000" />
											</logic:notEmpty>
										</logic:notPresent>
										
										<a href="javascript:limpar(13);">
											<img src="<bean:message key="caminho.imagens"/>limparcampo.gif" border="0" title="Apagar" /> 
										</a> 
									</td>
								</tr>
								
							</table>

							</td>
						</tr>
					</table>
					
					</td>
				</tr>
				
				<tr>
					<td colspan="2">
					
						<table width="100%" align="center" bgcolor="#90c7fc" border="0">
						<tr>
							<td><strong>Características</strong></td>
						</tr>
						<tr bgcolor="#cbe5fe">
							<td width="100%" align="center">

							<table width="100%" border="0">
								<tr>
									<td width="21%"><strong>Incrementar Lote:</strong></td>
									<td><html:radio property="indicadorIncrementoLote" value="1" tabindex="8" /><strong>Sim</strong>
										<html:radio property="indicadorIncrementoLote" value="2" tabindex="9" /><strong>N&atilde;o</strong></td>
								</tr>
								
								<tr>
									<td><strong>Tipo da Área:</strong></td>
									<td><html:select property="areaTipoID" style="width: 200px;"
										tabindex="4">
										<html:option
											value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
										<html:options collection="colecaoAreaTipo"
											labelProperty="descricao" property="id" />
									</html:select></td>
								</tr>
				
								<tr>
									<td><strong>Perfil da Quadra:<font color="#FF0000">*</font></strong></td>
									<td><html:select property="perfilQuadra" style="width: 200px;"
										tabindex="5">
										<html:option
											value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
										<html:options collection="colecaoPerfilQuadra"
											labelProperty="descricao" property="id" />
									</html:select></td>
								</tr>
								
								<logic:equal name="permissaoAdicionarQuadraFace" value="2">
								
								<!-- CASO A EMPRESA NÃO TRABALHE COM FACE DE QUADRA -->
								
									<INPUT TYPE="hidden" NAME="indicadorRedeAguaHTML" ID="indicadorRedeAguaHTML">

									<INPUT TYPE="hidden" NAME="indicadorRedeEsgotoHTML" ID="indicadorRedeEsgotoHTML">
								
									<tr>
										<td colspan="2" height="5"></td>
									</tr>
									<tr>
										<td><strong>Rede de Água:</strong></td>
										<td>
											<html:radio property="indicadorRedeAguaAux" value="1" tabindex="6"
											onclick="verificarMarcacao(1, 'Agua');habilitacaoDistritoOperacional(document.forms[0].indicadorRedeAguaAux);" /><strong>
											Sem Rede de Água 
											
											<html:radio property="indicadorRedeAguaAux" value="2" tabindex="6"
											onclick="verificarMarcacao(2, 'Agua');habilitacaoDistritoOperacional(document.forms[0].indicadorRedeAguaAux);" />
											Com Rede de Água 
											
											<html:radio property="indicadorRedeAguaAux" value="3" tabindex="7"
											onclick="verificarMarcacao(3, 'Agua');habilitacaoDistritoOperacional(document.forms[0].indicadorRedeAguaAux);" />
											Rede de Água Parcial</strong>
										</td>
									</tr>
									<tr>
										<td><strong>Rede de Esgoto:</strong></td>
										<td>
											<html:radio property="indicadorRedeEsgotoAux" value="1" tabindex="8"
											onclick="verificarMarcacao(1, 'Esgoto');habilitacaoSistemaEsgotoBacia(document.forms[0].indicadorRedeEsgotoAux);" /><strong>
											Sem Rede de Esgoto 
											
											<html:radio property="indicadorRedeEsgotoAux" value="2" tabindex="9"
											onclick="verificarMarcacao(2, 'Esgoto');habilitacaoSistemaEsgotoBacia(document.forms[0].indicadorRedeEsgotoAux);" />
											Com Rede de Esgoto 
											
											<html:radio property="indicadorRedeEsgotoAux" value="3" tabindex="10"
											onclick="verificarMarcacao(3, 'Esgoto');habilitacaoSistemaEsgotoBacia(document.forms[0].indicadorRedeEsgotoAux);" />
											Rede de Esgoto Parcial</strong>
										</td>
									</tr>
									
									<tr>
										<td><strong>Sistema Esgoto:<font color="#FF0000">*</font></strong></td>
										<td>
											<html:select property="sistemaEsgotoID" style="width: 200px;" tabindex="13"
											onchange="redirecionarSubmit('exibirInserirQuadraAction.do?objetoConsulta=7');">
											
												<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
												<html:options collection="colecaoSistemaEsgoto" labelProperty="descricaoAbreviada" property="id" />
											
											</html:select>
										</td>
									</tr>
									<tr>
										<td><strong>Bacia:<font color="#FF0000">*</font></strong></td>
										<td>
											<html:select property="baciaID" style="width: 200px;" tabindex="14">
											
												<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
												
												<logic:present name="colecaoBacia">
													<html:options collection="colecaoBacia" labelProperty="descricao" property="id" />
												</logic:present>
												
											</html:select>
										</td>
									</tr>
									<tr>
										<td><strong>Distrito Operacional:<font color="#FF0000">*</font></strong></td>
										<td>
											<html:text property="distritoOperacionalID" size="5"
											maxlength="3" tabindex="15"
											onkeypress="validaEnterComMensagem(event, 'exibirInserirQuadraAction.do?objetoConsulta=5', 'distritoOperacionalID','Distrito Operacional');"
											onkeyup="limpar(10);somente_numero_zero_a_nove(this);" /> <a
											href="javascript:abrirPopup('exibirPesquisarDistritoOperacionalAction.do?limparForm=sim',275,480);">
											<img width="23" height="21" border="0"
											src="<bean:message key="caminho.imagens"/>pesquisa.gif"
											title="Pesquisar Distrito Operacional" /></a> 
											
											<logic:present name="corDistritoOperacional">
					
												<logic:equal name="corDistritoOperacional" value="exception">
													<html:text property="distritoOperacionalDescricao" size="45"
														readonly="true"
														style="background-color:#EFEFEF; border:0; color: #ff0000" />
												</logic:equal>
						
												<logic:notEqual name="corDistritoOperacional" value="exception">
													<html:text property="distritoOperacionalDescricao" size="45"
														readonly="true"
														style="background-color:#EFEFEF; border:0; color: #000000" />
												</logic:notEqual>
					
											</logic:present> 
											
											<logic:notPresent name="corDistritoOperacional">
					
												<logic:empty name="InserirQuadraActionForm"
													property="distritoOperacionalID">
													<html:text property="distritoOperacionalDescricao"
														value="" size="45" readonly="true"
														style="background-color:#EFEFEF; border:0; color: #ff0000" />
												</logic:empty>
												<logic:notEmpty name="InserirQuadraActionForm"
													property="distritoOperacionalID">
													<html:text property="distritoOperacionalDescricao" size="45"
														readonly="true"
														style="background-color:#EFEFEF; border:0; color: #000000" />
												</logic:notEmpty>
					
											</logic:notPresent> 
											
											<a href="javascript:limpar(4);"><img
											src="<bean:message key="caminho.imagens"/>limparcampo.gif"
											border="0" title="Apagar Distrito Operacional" /></a>
										
										</td>
									</tr>
									
								<!-- FINAL - CASO A EMPRESA NÃO TRABALHE COM FACE DE QUADRA -->
								
								</logic:equal>
								
							</table>

							</td>
						</tr>
					</table>
					
					</td>
				</tr>
				
				<tr>
					<td colspan="2">
					
						<table width="100%" align="center" bgcolor="#90c7fc" border="0">
						<tr>
							<td><strong>IBGE</strong></td>
						</tr>
						<tr bgcolor="#cbe5fe">
							<td width="100%" align="center">

							<table width="100%" border="0">
								
								<tr>
									<td width="21%"><strong>Setor Censitário:</strong></td>
									<td><html:text property="setorCensitarioID" size="5" maxlength="3"
										tabindex="16" onkeyup="limpar(11);"
										onkeypress="validaEnterComMensagem(event, 'exibirInserirQuadraAction.do?objetoConsulta=6', 'setorCensitarioID','Setor Censitário');" 
										onkeyup="somente_numero_zero_a_nove(this);" />
				
									<a
										href="javascript:abrirPopup('exibirPesquisarTabelaAuxiliarAction.do?tela=ibgeSetorCensitario',275,480);">
									<img width="23" height="21" border="0"
										src="<bean:message key="caminho.imagens"/>pesquisa.gif"
										title="Pesquisar Setor Censitário" /></a> <logic:present
										name="corSetorCensitario">
				
										<logic:equal name="corSetorCensitario" value="exception">
											<html:text property="setorCensitarioDescricao" size="45"
												readonly="true"
												style="background-color:#EFEFEF; border:0; color: #ff0000" />
										</logic:equal>
				
										<logic:notEqual name="corSetorCensitario" value="exception">
											<html:text property="setorCensitarioDescricao" size="45"
												readonly="true"
												style="background-color:#EFEFEF; border:0; color: #000000" />
										</logic:notEqual>
				
									</logic:present> <logic:notPresent name="corSetorCensitario">
				
										<logic:empty name="InserirQuadraActionForm"
											property="setorCensitarioID">
											<html:text property="setorCensitarioDescricao" size="45"
												readonly="true"
												style="background-color:#EFEFEF; border:0; color: #ff0000" />
										</logic:empty>
										<logic:notEmpty name="InserirQuadraActionForm"
											property="setorCensitarioID">
											<html:text property="setorCensitarioDescricao" size="45"
												readonly="true"
												style="background-color:#EFEFEF; border:0; color: #000000" />
										</logic:notEmpty>
				
									</logic:notPresent> <a href="javascript:limpar(5);"> <img
										src="<bean:message key="caminho.imagens"/>limparcampo.gif"
										border="0" title="Apagar Setor Censitário" /> </a></td>
								</tr>
				
				
								<tr>
									<td><strong>ZEIS:</strong></td>
									<td><html:select property="zeisID" style="width: 200px;"
										tabindex="17">
										<html:option
											value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
										<html:options collection="colecaoZeis"
											labelProperty="descricaoAbreviada" property="id" />
									</html:select></td>
								</tr>
								
							</table>

							</td>
						</tr>
					</table>
					
					</td>
				</tr>
				
				<logic:equal name="permissaoAdicionarQuadraFace" value="1">
				
				<%int cont = 0;%>

				<tr>
					<td colspan="2" height="5"></td>
				</tr>
				<tr>
					<td colspan="2">
							
						<table width="100%" border="0">
						<tr>
							<td height="17" colspan="3"><strong>Face(s) da quadra:<font color="#FF0000">*</font></strong></td>
							<td align="right"><input type="button" class="bottonRightCol"
								value="Adicionar" tabindex="11" style="width: 80px"
								onclick="javascript:abrirPopup('exibirAdicionarQuadraFaceAction.do?acao=inserir&telaRetorno=exibirInserirQuadraAction', 295, 450);"></td>
						</tr>

						<logic:present name="colecaoQuadraFace">
						
						<logic:notEmpty name="colecaoQuadraFace">
						
						<input type="hidden" id="quadraFaceInformada" name="quadraFaceInformada" value="1">
						
						<tr>
							<td colspan="4">

							<table width="100%" cellpadding="0" cellspacing="0">
							<tr>
								<td>

									<table width="100%" bgcolor="#99CCFF">
									<tr bgcolor="#99CCFF">

										<td align="center" width="10%"><strong>Remover</strong></td>
										<td width="10%">
											<div align="center"><strong>Número</strong></div>
										</td>
										<td width="10%">
											<div align="center"><strong>Água</strong></div>
										</td>
										<td width="10%">
											<div align="center"><strong>Esgoto</strong></div>
										</td>
										<td width="30%">
											<div align="center"><strong>Distrito Opereracional</strong></div>
										</td>
										<td width="30%">
											<div align="center"><strong>Bacia</strong></div>
										</td>

									</tr>
									</table>

								</td>
							</tr>

							<tr>
								<td>

									<div style="width: 100%; height: 100; overflow: auto;">
									<%cont = 0;%>
									
									<table width="100%" align="center" bgcolor="#99CCFF">

										<logic:iterate name="colecaoQuadraFace" id="quadraFace" type="QuadraFace">
										
											<%cont = cont + 1;
											if (cont % 2 == 0) {%>
											<tr bgcolor="#cbe5fe">
											<%} else {%>
											<tr bgcolor="#FFFFFF">
											<%}%>

												<td align="center" width="10%" valign="middle">
													<a href="javascript:removerQuadraFace('<%="" + quadraFace.getNumeroQuadraFace().intValue()%>')">
														<img src="<bean:message key='caminho.imagens'/>Error.gif" border="0" >
													</a>
												</td>
												<td width="10%" align="center">
												
												<a href="javascript:abrirPopup('exibirAdicionarQuadraFaceAction.do?acao=atualizar&telaRetorno=exibirInserirQuadraAction&numeroQuadraFace=<%="" + quadraFace.getNumeroQuadraFace().intValue()%>',400, 630);">
												<bean:write name="quadraFace" property="numeroQuadraFace" /></a></td>
												<td width="10%" align="center"><bean:write name="quadraFace" property="descricaoRedeAgua" /></td>
												<td width="10%" align="center"><bean:write name="quadraFace" property="descricaoRedeEsgoto" /></td>
												<td width="30%">
													
													<logic:present name="quadraFace" property="distritoOperacional">
														<bean:write name="quadraFace" property="distritoOperacional.descricaoAbreviada" />
													</logic:present>
														
													<logic:notPresent name="quadraFace" property="distritoOperacional">
														&nbsp;
													</logic:notPresent>
													
												</td>
													
												<td width="30%">
													
													<logic:present name="quadraFace" property="bacia">
														<bean:write name="quadraFace" property="bacia.descricao" />
													</logic:present>
														
													<logic:notPresent name="quadraFace" property="bacia">
														&nbsp;
													</logic:notPresent>
													
												</td>
													
											</tr>

										</logic:iterate>
									</table>
									</div>
								</td>
							</tr>
							</table>
							
							</td>
						</tr>
						
						</logic:notEmpty>
						
						<logic:empty name="colecaoQuadraFace">
						
							<input type="hidden" id="quadraFaceInformada" name="quadraFaceInformada" value="2">
							
						</logic:empty>
						
						</logic:present>
						
						<logic:notPresent name="colecaoQuadraFace">
						
							<input type="hidden" id="quadraFaceInformada" name="quadraFaceInformada" value="2">
							
						</logic:notPresent>
						
						</table>
												
					</td>
				</tr>
				
				</logic:equal>
				
			</table>
				
			<table width="100%" border="0">
			<tr>
				<td width="20%" height="10"></td>
				<td><font color="#FF0000">*</font>&nbsp;Campos obrigat&oacute;rios</td>
			</tr>
			</table>

			<table width="100%" border="0">
			<tr>
				<td><input name="Button" type="button" class="bottonRightCol"
					value="Desfazer" align="left"
					onclick="window.location.href='<html:rewrite page="/exibirInserirQuadraAction.do?desfazer=S"/>'">
				<input name="Button" type="button" class="bottonRightCol"
					value="Cancelar" align="left"
					onClick="javascript:window.location.href='/gsan/telaPrincipal.do'"></td>
				<td align="right"><gsan:controleAcessoBotao name="botaoConcluir"
					value="Inserir" tabindex="19"
					onclick="javascript:validarForm(document.forms[0]);"
					url="inserirQuadraAction.do" /></td>
			</tr>
			</table>

			<p>&nbsp;</p>

			</td>
		</tr>
	</table>

	<%@ include file="/jsp/util/rodape.jsp"%>

</html:form>
</div>

<%@ include file="/jsp/util/telaespera.jsp"%>


<logic:equal name="permissaoAdicionarQuadraFace" value="2">

<!-- CASO A EMPRESA NÃO TRABALHE COM FACE DE QUADRA -->

	<script language="JavaScript">
	
		<!--
		habilitacaoDistritoOperacional(document.forms[0].indicadorRedeAguaAux);
		habilitacaoSistemaEsgotoBacia(document.forms[0].indicadorRedeEsgotoAux);
		//-->

	</script>
	
<!-- FIM - CASO A EMPRESA NÃO TRABALHE COM FACE DE QUADRA -->

</logic:equal>

<script language="JavaScript">
	
	document.forms[0].botaoConcluir.onclick = function() { validarForm(document.forms[0]); }

</script>
	
</body>
</html:html>




