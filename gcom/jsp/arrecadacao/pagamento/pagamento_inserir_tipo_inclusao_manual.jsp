<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<%@ page import="gcom.cobranca.DocumentoTipo" %>
<%@ page import="gcom.gui.GcomAction" %>
<%@ page import="gcom.arrecadacao.pagamento.Pagamento" %>
<%@ page import="gcom.util.Util" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>

<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="PagamentoActionForm" dynamicJavascript="false" />
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js" ></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js" ></script>
<script type="text/javascript" language="Javascript1.1">
<!--
	//window.onmousemove = habilitacaoCampos;

    var bCancel = false;

   function validatePagamentoActionForm(form){
   		
   		var pagamentoInformado = document.getElementById("pagamentoInformado");
   		
   		if (pagamentoInformado == null){
   			form.idTipoDocumento.focus();
   			alert("Informe Pagamento");
   		}
   		else{
   			submeterFormPadrao(form);
   		}
   }
   
   function validarForm(form) {
        if (bCancel)
      return true;
        else
       return validateLong(form) && validateRequired(form) && validaTipoDocumento() && validateDecimal(form)
       && verificarImovel()&& verificarReferenciaConta() && verificarGuiaPagamento() && verificarDebitoACobrar() ;
   }

    function required () {
     this.aa = new Array("idTipoDocumento", "Informe Tipo de Documento.", new Function ("varName", " return this[varName];"));
     this.ab = new Array("valorPagamento", "Informe Valor do Pagamento.", new Function ("varName", " return this[varName];"));
    }
    
    function FloatValidations () {
       this.an = new Array("valorPagamento", "Valor do Pagamento deve somente conter números decimais positivos.",                 new Function ("varName", " return this[varName];"));
    } 
        
     function IntegerValidations () { 
       this.aa = new Array("idLocalidade", "Localidade deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
       this.ab = new Array("idImovel", "Matrícula do Imóvel deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
       this.ac = new Array("idCliente", "Código do Cliente deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
       this.ad = new Array("idGuiaPagamento", "Guia de Pagamento deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
       this.ae = new Array("idDebitoACobrar", "Débito a Cobrar deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
       this.af = new Array("idTipoDebito", "Tipo de Débito deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
    } 

    
-->
</script>

<script type="text/javascript" language="Javascript1.1">
<!--
//Vivianne Sousa - 15/08/06

	function validaRequiredGuiaPagamento () {
		var form = document.forms[0];
		var msg = '';

		 if(form.idLocalidade.value == null || form.idLocalidade.value == "") {
			msg = 'Informe Localidade.\n';
		}
		
		if((form.idImovel.value == null || form.idImovel.value == "")
		&& (form.idCliente.value == null || form.idCliente.value == "" )) {
			msg = msg + 'Informe Matrícula do Imóvel ou Código do Cliente.\n';
		}
		
		if((form.idGuiaPagamento.value == null || form.idGuiaPagamento.value == "")
		&& (form.idTipoDebito.value == null || form.idTipoDebito.value == "" )) {
			msg = msg + 'Informe Guia de Pagamento ou Tipo de Débito.';
		}
		
		if( msg != '' ){
			alert(msg);
			return false;
		}else{
			return true;
		}
	}
	
	
	function validaRequiredDebitoACobrar () {
		var form = document.forms[0];
		var msg = '';

		 if(form.idImovel.value == null || form.idImovel.value == "") {
			msg = 'Informe Matrícula do Imóvel.\n';
		}
		
		if((form.idDebitoACobrar.value == null || form.idDebitoACobrar.value == "")
		&& (form.idTipoDebito.value == null || form.idTipoDebito.value == "" )) {
			msg = msg + 'Informe Débito a Cobrar ou Tipo de Débito.';
		}
		
		if( msg != '' ){
			alert(msg);
			return false;
		}else{
			return true;
		}
	}
	
	
	function validaRequiredConta () {
		var form = document.forms[0];
		var msg = '';

		 if(form.idImovel.value == null || form.idImovel.value == "") {
			msg = 'Informe Matrícula do Imóvel.\n';
		}
		
		if(form.referenciaConta.value == null || form.referenciaConta.value == "") {
			msg = msg + 'Informe Referência da Conta.';
		}
		
		if( msg != '' ){
			alert(msg);
			return false;
		}else{
			return true;
		}
	}
	
	function validaTipoDocumento(){
		var form = document.forms[0];
		
		if(form.idTipoDocumento.value == <%=DocumentoTipo.GUIA_PAGAMENTO%>){    
			return validaRequiredGuiaPagamento();
		    		
		}else if(form.idTipoDocumento.value == <%=DocumentoTipo.DEBITO_A_COBRAR%>){
			return validaRequiredDebitoACobrar();
			
		}else if(form.idTipoDocumento.value == <%=DocumentoTipo.CONTA%>){
			return validaRequiredConta();
			
		}
	}
	
	
	function desabilitarPesquisaCliente(form){
		form.descricaoImovel.value = "";
		if(form.idTipoDocumento.value == <%=DocumentoTipo.GUIA_PAGAMENTO%>){    
		    limparCliente();
		    if(trim(form.idImovel.value) == ""){
		        form.idCliente.disabled = false; 
		    }else{
		        form.idCliente.disabled = true; 
		    }
		 }   
	    //limpa todos os campos das pesquisas que dependem de Matricula do imovel
        limparReferenciaConta();
		limparGuiaPagamento();
		limparDebitoACobrar();
 	}

	function desabilitarPesquisaImovel(form){
		form.nomeCliente.value= "";
    	limparImovel();
	    if(trim(form.idCliente.value) == ""){
	       form.idImovel.disabled = false; 
	    }else{
	        form.idImovel.disabled = true; 
	    }
	    //limpa todos os campos das pesquisas que dependem de Código do Cliente
  		limparGuiaPagamento();
	}	
	
	function validaDependenciaImovel(campo){
		var form = document.forms[0];
	    if(form.idImovel.value == null || form.idImovel.value == "") {
     	   alert('Informe Matrícula do Imóvel.');
     	   campo.value = '';
	 	   form.idImovel.focus();
        }
	}
	
	function validaDependenciaImovelCliente(campo){
		var form = document.forms[0];
	
	    if((form.idImovel.value == null || form.idImovel.value == "")
	    && (form.idCliente.value == null || form.idCliente.value == "")) {
     	   alert('Informe Matrícula do Imóvel ou Código do Cliente.');
     	   campo.value = '';
        }
	}
	
-->
</script>

<script type="text/javascript" language="Javascript1.1">
<!--
//window.onmousemove = habilitacaoCampos;

function recuperarDadosCincoParametros(codigoRegistro, descricaoRegistro1, descricaoRegistro2, descricaoRegistro3, tipoConsulta) { 
	var form = document.forms[0];
	
 	if (tipoConsulta == 'debitoACobrar') {
 	    form.idDebitoACobrar.value = codigoRegistro;
 	    form.valorDebitoACobrar.value = descricaoRegistro1;
		form.descricaoDebitoACobrar.value = descricaoRegistro3;
		form.descricaoDebitoACobrar.style.color = "#000000";
		//[FS0018] Verificar valor do débito a cobrar
		form.valorPagamento.value = descricaoRegistro1;
		limparTipoDebito();
		desabilitarPesquisaTipoDebito(form);
	}
	
	if (tipoConsulta == 'guiaPagamento') {
 	    form.idGuiaPagamento.value = codigoRegistro;
		form.valorGuiaPagamento.value = descricaoRegistro1;
		form.descricaoGuiaPagamento.value = descricaoRegistro3;
		form.descricaoGuiaPagamento.style.color = "#000000";
		//[FS0015] Verificar valor da guia de pagamento
		form.valorPagamento.value = descricaoRegistro1;
		limparTipoDebito();
		desabilitarPesquisaTipoDebito(form);
	}
}

function recuperarDadosQuatroParametros(codigoRegistro, descricaoRegistro1, descricaoRegistro2, tipoConsulta) { 
	var form = document.forms[0];
	
 	if (tipoConsulta == 'conta') {
 	    form.referenciaConta.value = descricaoRegistro1;
		form.descricaoReferenciaConta.value = descricaoRegistro2;
		form.valorPagamento.value = descricaoRegistro2;
		//form.descricaoReferenciaConta.style.color = "#000000";
	}
}

function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {

    var form = document.forms[0];

    if (tipoConsulta == 'avisoBancario') {
      form.idAvisoBancario.value = codigoRegistro;
      form.descricaoAvisoBancario.value = descricaoRegistro;
      //form.descricaoAvisoBancario.style.color = "#000000";
    }
    
    if (tipoConsulta == 'localidade') { 	
 	  form.idLocalidade.value = codigoRegistro;
 	  form.descricaoLocalidade.value = descricaoRegistro; 	
 	  //form.descricaoLocalidade.style.color = "#000000";	
 	}
 	
 	if (tipoConsulta == 'imovel') { 	
 	  form.idImovel.value = codigoRegistro;
 	  form.descricaoImovel.value = descricaoRegistro; 
 	  //form.descricaoImovel.style.color = "#000000";
 	}
 	
 	if (tipoConsulta == 'cliente') { 	
 	  form.idCliente.value = codigoRegistro;
 	  form.nomeCliente.value = descricaoRegistro; 		
 	  //form.nomeCliente.style.color = "#000000";
 	}
 	
 	if (tipoConsulta == 'tipoDebito') { 	
 	  form.idTipoDebito.value = codigoRegistro;
 	  form.descricaoTipoDebito.value = descricaoRegistro; 
 	  //form.descricaoTipoDebito.style.color = "#000000";
 	  form.idDebitoACobrar.disabled = true;
 	  form.idGuiaPagamento.disabled = true;
 	}
 }
 
 
function limparReferenciaConta(){
 	var form = document.forms[0];
 	
 	form.referenciaConta.value = "";
 	form.descricaoReferenciaConta.value = ""; 		
 }
 
function limparLocalidade(){
 	var form = document.forms[0];
 	
 	form.idLocalidade.value = "";
 	form.descricaoLocalidade.value = ""; 		
 }

function limparImovel(){
 	var form = document.forms[0];
 	
 	form.idImovel.value = "";
 	form.descricaoImovel.value = ""; 	
 }

function limparCliente(){
 	var form = document.forms[0];
 	
    form.idCliente.value = "";
 	form.nomeCliente.value = ""; 		
 }

 function limparTipoDebito(){
 	var form = document.forms[0];
 	
 	form.idTipoDebito.value = "";
 	form.descricaoTipoDebito.value = ""; 	
 	
 	if(form.idTipoDocumento.value == <%=DocumentoTipo.GUIA_PAGAMENTO%>){    
        form.idGuiaPagamento.disabled = false; 
    }else if(form.idTipoDocumento.value == <%=DocumentoTipo.DEBITO_A_COBRAR%>){
        form.idDebitoACobrar.disabled = false; 
    }
 }
 
 function limparGuiaPagamento(){
 	var form = document.forms[0];
 	
 	form.idGuiaPagamento.value = "";
 	form.descricaoGuiaPagamento.value = ""; 		
 	form.valorGuiaPagamento.value = "";
 	
 	form.idTipoDebito.disabled = false;
 }
 
 function limparDebitoACobrar(){
 	var form = document.forms[0];
 	
 	form.idDebitoACobrar.value = "";
 	form.descricaoDebitoACobrar.value = ""; 		
 	form.valorDebitoACobrar.value = "";
 	
 	form.idTipoDebito.disabled = false;
 }
 
 //[SB0004- Habilitar/Desabilitar Campos]
 function habilitacaoCampos(){

	var form = document.forms[0];
	 
 	var CONTA = document.getElementById("DOCUMENTO_TIPO_CONTA").value;
 	var GUIA_PAGAMENTO = document.getElementById("DOCUMENTO_TIPO_GUIA_PAGAMENTO").value;
 	var DEBITO_A_COBRAR = document.getElementById("DOCUMENTO_TIPO_DEBITO_A_COBRAR").value;
 	
 	var tipoDocumentoSelected = form.idTipoDocumento.value;
 	
 	if (tipoDocumentoSelected == CONTA ){
 		
 		form.idLocalidade.disabled = true;
 		form.idImovel.disabled = false;
 		
 		form.idCliente.value = "";
 		form.idCliente.disabled = true;
 		form.nomeCliente.value = "";
 		
 		form.referenciaConta.disabled = false;
 		
 		form.idGuiaPagamento.value = "";
 		form.descricaoGuiaPagamento.value = "";
 		form.valorGuiaPagamento.value = "";
 		form.idGuiaPagamento.disabled = true;
 		form.valorGuiaPagamento.disabled = true;
 		
 		form.idDebitoACobrar.value = "";
 		form.descricaoDebitoACobrar.value = "";
 		form.valorDebitoACobrar.value = "";
 		form.idDebitoACobrar.disabled = true;
 		form.valorDebitoACobrar.disabled = true;
 		
 		form.idTipoDebito.value = "";
 		form.descricaoTipoDebito.value = "";
 		form.idTipoDebito.disabled = true;
 		
 		//form.idImovel.focus();
 		
 	}
 	else if(tipoDocumentoSelected == GUIA_PAGAMENTO){
	 	
 		form.idLocalidade.disabled = false;

 		if(form.idImovel.value != "" && form.idCliente.value == ""){
 		    form.idCliente.disabled = true;
 		}else if(form.idImovel.value == "" && form.idCliente.value != ""){
 		    form.idImovel.disabled = true;
 		}else{
	 		form.idCliente.disabled = false;
	 		form.idImovel.disabled = false;
 		}
 		
 		//form.idImovel.disabled = false;
 		
 		//form.idCliente.disabled = false;
 		
 		form.referenciaConta.value = "";
 		form.descricaoReferenciaConta.value = "";
 		form.referenciaConta.disabled = true;
 		
 		form.idGuiaPagamento.disabled = false;
 		
 		form.idDebitoACobrar.value = "";
 		form.descricaoDebitoACobrar.value = "";
 		form.valorDebitoACobrar.value = "";
 		form.idDebitoACobrar.disabled = true;
 		form.valorDebitoACobrar.disabled = true;
 		
 		form.idTipoDebito.disabled = false;
 		
 		if(form.idGuiaPagamento.value != ""){
 		  if(form.idTipoDebito.value == ""){
 		    form.idTipoDebito.disabled = true;
 		  }
 		}else{
		  if(form.idTipoDebito.value != ""){
 		    form.idGuiaPagamento.disabled = true;
 		  }
 		}

 	} 
 	else if(tipoDocumentoSelected == DEBITO_A_COBRAR){
 	
 		form.idLocalidade.disabled = true;
 		
 		form.idImovel.disabled = false;
 		
 		form.idCliente.value = "";
 		form.idCliente.disabled = true;
 		form.nomeCliente.value = "";
 		
 		form.referenciaConta.value = "";
 		form.descricaoReferenciaConta.value = "";
 		form.referenciaConta.disabled = true;
 		
 		form.idGuiaPagamento.value = "";
 		form.descricaoGuiaPagamento.value = "";
 		form.valorGuiaPagamento.value = "";
 		form.idGuiaPagamento.disabled = true;
 		form.valorGuiaPagamento.disabled = true;
 		
 		form.idDebitoACobrar.disabled = false;
 		
 		form.idTipoDebito.disabled = false;
 		
 		
 		if(form.idDebitoACobrar.value != ""){
 		  if(form.idTipoDebito.value == ""){
 		    form.idTipoDebito.disabled = true;
 		  }
 		}else{
		  if(form.idTipoDebito.value != ""){
 		    form.idDebitoACobrar.disabled = true;
 		  }
 		}
 		
 		//form.idImovel.focus();
 	}
 	else {
 	
 		form.idLocalidade.value = "";
 		form.idLocalidade.disabled = true;
 		form.descricaoLocalidade.value ="";
 		
 		form.idImovel.value = "";
 		form.descricaoImovel.value = ""; 
 		form.idImovel.disabled = true;
 		
 		form.idCliente.value = "";
 		form.idCliente.disabled = true;
 		form.nomeCliente.value = "";
 		
 		form.referenciaConta.value = "";
 		form.descricaoReferenciaConta.value = "";
 		form.referenciaConta.disabled = true;
 		
 		form.idGuiaPagamento.value = "";
 		form.descricaoGuiaPagamento.value = "";
 		form.valorGuiaPagamento.value = "";
 		form.idGuiaPagamento.disabled = true;
 		form.valorGuiaPagamento.disabled = true;
 		
 		form.idDebitoACobrar.value = "";
 		form.descricaoDebitoACobrar.value = "";
 		form.valorDebitoACobrar.value = "";
 		form.idDebitoACobrar.disabled = true;
 		form.valorDebitoACobrar.disabled = true;
 		
 		form.idTipoDebito.value = "";
 		form.descricaoTipoDebito.value = "";
 		form.idTipoDebito.disabled = true;
 		
 		form.valorPagamento.value = "";
 	}
 }
 
 function habilitarPesquisaLocalidade(listBoxDocumentoTipo){
 
   if(listBoxDocumentoTipo.value == <%=DocumentoTipo.GUIA_PAGAMENTO%>){
     abrirPopup('exibirPesquisarLocalidadeAction.do', 400, 800);
   }
 }
 
 function habilitarPesquisaConta(idImovel){
 
   var form = document.forms[0];
   
   if(form.idTipoDocumento.value == <%=DocumentoTipo.CONTA%>){
     if(idImovel.value == ""){
       alert('Informe Matrícula do Imóvel.')
     }else{
     abrirPopup('exibirPesquisarContaAction.do?idImovel='+idImovel.value +'&limparForm=1'+'&situacaoConta=nri' , 530, 790);
     }
   }//else{
    // return false;
  // }
 }
 
 function habilitarPesquisaGuiaPagamento(form){
 
   if(form.idTipoDocumento.value == <%=DocumentoTipo.GUIA_PAGAMENTO%>){
   
   if (form.idTipoDebito.value == "") {
   
     if(form.idImovel.value != ""){
       abrirPopup('exibirPesquisarGuiaPagamentoAction.do?idImovel='+form.idImovel.value + '&limpaForm=1', 500, 775);
     }else if(form.idCliente.value != ""){
       abrirPopup('exibirPesquisarGuiaPagamentoAction.do?idCliente='+form.idCliente.value +'&limpaForm=1' , 500, 775);
     }else{
       alert('Informe Matrícula do Imóvel ou Código do Cliente.')
     }
   }// else{
     // return false;
  }  
 }
 
 function habilitarPesquisaDebitoACobrar(form){
 
   if(form.idTipoDocumento.value == <%=DocumentoTipo.DEBITO_A_COBRAR%>){
   
     if(form.idImovel.value == ""){
       alert('Informe Matrícula do Imóvel.') 
     }else{
       abrirPopup('exibirPesquisarDebitoACobrarAction.do?idImovel='+form.idImovel.value , 500, 800);
     }
   }//else{
    // return false;
   //}  
 }
 
  function habilitarPesquisaTipoDebito(form){
 
   if(form.idTipoDocumento.value == <%=DocumentoTipo.DEBITO_A_COBRAR%> || form.idTipoDocumento.value == <%=DocumentoTipo.GUIA_PAGAMENTO%>){
     if(form.idTipoDebito.disabled == false){
       abrirPopup('exibirPesquisarTipoDebitoAction.do', 580, 800);
     }
   }//else{
    // return false;
   //}  
 }
 
 //[FS0021]
 function desabilitarPesquisaTipoDebito(form){
    limparTipoDebito();

    if(form.idTipoDocumento.value == <%=DocumentoTipo.GUIA_PAGAMENTO%>){    
      if(trim(form.idGuiaPagamento.value) == ""){
        form.idTipoDebito.disabled = false; 
      }else{
        form.idTipoDebito.disabled = true; 
      }
    }else if(form.idTipoDocumento.value == <%=DocumentoTipo.DEBITO_A_COBRAR%>){
      if(trim(form.idDebitoACobrar.value) == ""){
        form.idTipoDebito.disabled = false; 
      }else{
        form.idTipoDebito.disabled = true; 
      }    
    }
 }

 function desabilitarPesquisaGuiaPagamento(form){

    limparGuiaPagamento();
    if(form.idTipoDocumento.value == <%=DocumentoTipo.GUIA_PAGAMENTO%>){    
      if(trim(form.idTipoDebito.value) == ""){
        form.idGuiaPagamento.disabled = false; 
      }else{
        form.idGuiaPagamento.disabled = true; 
      }    
    }
 }

function desabilitarPesquisaDebitoACobrar(form){
    limparDebitoACobrar();
    
    if(form.idTipoDocumento.value == <%=DocumentoTipo.DEBITO_A_COBRAR%>){        
      if(trim(form.idTipoDebito.value) == ""){
        form.idDebitoACobrar.disabled = false; 
      }else{
        form.idDebitoACobrar.disabled = true; 
      }    
    }
 }
 
 function habilitarPesquisaImovel(listBoxDocumentoTipo){
 
   if(listBoxDocumentoTipo.value == <%=DocumentoTipo.GUIA_PAGAMENTO%> || listBoxDocumentoTipo.value == <%=DocumentoTipo.DEBITO_A_COBRAR%> || listBoxDocumentoTipo.value == <%=DocumentoTipo.CONTA%>){
     abrirPopup('exibirPesquisarImovelAction.do', 400, 800);
   }//else{
   //  return false;
   //}
 }
 
 function habilitarPesquisaCliente(listBoxDocumentoTipo){
   var form = document.forms[0];
    
   if(listBoxDocumentoTipo.value == <%=DocumentoTipo.GUIA_PAGAMENTO%>){
     if(form.idImovel.value != null && form.idImovel.value != "") {
     	alert('Informe Matrícula do Imóvel ou Código do Cliente.');
     }else{
       abrirPopup('exibirPesquisarClienteAction.do', 400, 800);
     }
   }//else{
    // return false;
   //}
 }
 
 function verificarReferenciaConta(){
 
	  var form = document.forms[0];
	  
	  if(form.referenciaConta.disabled == false){
	  		  	
	  	if(form.referenciaConta.value == ""){
	    	alert('Informe Referência da Conta.');
	    	return false;
	  	} else{
	  	  if(verificaAnoMes(form.referenciaConta)){
	  	    return true;
	  	  }else{
	  	    return false;
	  	  }
	  	}
	  }else{
	    return true;
	  }
  }    
    
  
function verificarGuiaPagamento(){
 
  var form = document.forms[0];
	  
  if(form.idTipoDocumento.value == <%=DocumentoTipo.GUIA_PAGAMENTO%>){
    if(form.idLocalidade.value == ""){
	  alert('Informe Localidade.');  
	  return false;
	}else{
	  return true;
	}
  }else{
    if(form.idGuiaPagamento.disabled == false){
      if(form.idGuiaPagamento.value == ""){
	    alert('Informe Guia de Pagamento ou Tipo de Débito.');
	    return false;
	  } else{
	    return true;
	  }
    }else{
	  return true;   
    }
  }  
}    
  
  
  
   function verificarDebitoACobrar(){
 
	  var form = document.forms[0];
	  
	  if(form.idDebitoACobrar.disabled == false){
	  		  	
	  	if(form.idDebitoACobrar.value == ""){
	    	alert('Informe Débito a Cobrar ou Tipo de Débito.');
	    	return false;
	  	} else{
	  	  return true;
	  	}
	  }else{
	    return true;
	  }
  }    
  
  
-->
</script>
<script type="text/javascript" language="Javascript1.1">
<!-- 

function verificarPreenchimentoImovelCliente(){
  var form = document.forms[0];

  if(form.idCliente.value != "" && form.idImovel.value != ""){
	alert('Informe Matrícula do Imóvel ou Código do Cliente.');
   	return false;
  }else if(form.idCliente.value == "" && form.idImovel.value == ""){
	alert('Informe Matrícula do Imóvel ou Código do Cliente.');
	return false;
  }else{
    return true;
  }
}
    
function verificarImovel(){

  var form = document.forms[0];

  if(form.idTipoDocumento.value == <%=DocumentoTipo.GUIA_PAGAMENTO%>){ 
    return verificarPreenchimentoImovelCliente();
   } 
   
   if(form.idTipoDocumento.value == <%=DocumentoTipo.DEBITO_A_COBRAR%>){
   
      if(form.idImovel.disabled == false){
      
        if(trim(form.idImovel.value) == ""){
          alert('Informe Matrícula do Imóvel.');
          return false;
        }else{
          return true;
        }
      }
      else{
        return true;
      }
    }

    if(form.idTipoDocumento.value == <%=DocumentoTipo.CONTA%>){
    
      if(form.idImovel.disabled == false){
      
        if(trim(form.idImovel.value) == ""){
          alert('Informe Matrícula do Imóvel.');
          return false;
        }else{
          return true;
        }
      }else{
        return true;
      }
    }
    
 }
 
 function adicionarPagamento(form){
 
 	if (validarForm(form)){
 		form.action = "/gsan/inserirPagamentosWizardAction.do?informarPagamento=OK&clicouAdicionar=OK&destino=2&action=exibirInserirPagamentosTipoInclusaoManualAction";
 		submeterFormPadrao(form);
 	}	
 }
 
 function removerPagamento(pagamento){
 	
 	if (confirm ("Confirma remoção?")) {
		form = document.forms[0];
 		form.action = "/gsan/inserirPagamentosWizardAction.do?removerPagamento=" + pagamento + "&destino=2&action=exibirInserirPagamentosTipoInclusaoManualAction";
 		submeterFormPadrao(form);
	}
 }
 
 function solicitarConfirmacao(msg, pagamento){
 	
 	if (msg != ''){
 		if (!confirm (msg)) {
			form = document.forms[0];
 			form.action = "/gsan/inserirPagamentosWizardAction.do?removerPagamento=" + pagamento + "&destino=2&action=exibirInserirPagamentosTipoInclusaoManualAction";
 			submeterFormPadrao(form);
 		}
 	}
 }
 
 -->

</script>
</head>

<body leftmargin="5" topmargin="5" onload="habilitacaoCampos(); solicitarConfirmacao('${requestScope.msg}', '${requestScope.pagamento}')">

<html:form
    action="/inserirPagamentosWizardAction"
    method="post"
>

<input type="hidden" id="DOCUMENTO_TIPO_CONTA" value="<%=DocumentoTipo.CONTA%>"/>
<input type="hidden" id="DOCUMENTO_TIPO_GUIA_PAGAMENTO" value="<%=DocumentoTipo.GUIA_PAGAMENTO%>"/>
<input type="hidden" id="DOCUMENTO_TIPO_DEBITO_A_COBRAR" value="<%=DocumentoTipo.DEBITO_A_COBRAR%>"/>

<jsp:include page="/jsp/util/wizard/navegacao_abas_wizard_valida_avancar.jsp?numeroPagina=2"/>
<%@ include file="/jsp/util/cabecalho.jsp"%>
<%@ include file="/jsp/util/menu.jsp" %>
<input type="hidden" name="numeroPagina" value="2"/>

<table width="770" border="0" cellspacing="5" cellpadding="0">
  <tr>
    <td width="145" valign="top" class="leftcoltext">
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
          <td class="parabg">Inserir Pagamentos</td>
          <td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif"/></td>
        </tr>
      </table>
      <p>&nbsp;</p>
      <table width="100%" border="0" dwcopytype="CopyTableRow">
       	<tr>
          <td width="18%">
            <strong>Aviso Bancário:</strong>
          </td>
          <td colspan="3">
            <strong> 
			  <html:text property="codigoAgenteArrecadador" size="3" maxlength="3" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" />
			  <html:text property="dataLancamentoAviso" size="10" maxlength="10" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" />
			  <html:text property="numeroSequencialAviso" size="3" maxlength="3" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" />
			</strong>
		  </td>
        </tr>
        <tr>
          <td>
            <strong>Data do Pagamento:</strong>
          </td>
          <td>
            <html:text property="dataPagamento" size="10" maxlength="10" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>
          </td>
        </tr>
        <tr>
          <td>
            <strong>Forma de Arrecadação:</strong>
          </td>
          <td>
			<html:text property="descricaoFormaArrecadacao" size="69" maxlength="65" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>
          </td>
        </tr>
        <tr> 
          <td colspan="4"> Para inserir o pagamento, informe os dados abaixo:</td>
        </tr>
        
        <tr>
          <td height="18" colspan="4"> <hr> </td>
        </tr>
        
        <tr>
          <td>
            <strong>Tipo do Documento:<font color="#FF0000">*</font></strong>
          </td>
          <td>
            <html:select property="idTipoDocumento" onchange="habilitacaoCampos();">
              <html:option value=""></html:option> 
			  <html:options collection="colecaoDocumentoTipo" labelProperty="descricaoDocumentoTipo" property="id"/>
            </html:select>
          </td>
        </tr>
        
        <tr> 
          <td height="18" colspan="4"> <hr> </td>
        </tr>
        
        <tr>
		  <td width="18%"><strong>Localidade:</strong></td>
		  <td width="403">
		  
		    <html:text name="PagamentoActionForm" maxlength="3" tabindex="1" property="idLocalidade" size="3" 
		    onkeypress="validaEnter(event, '/gsan/inserirPagamentosWizardAction.do?destino=2&action=exibirInserirPagamentosTipoInclusaoManualAction','idLocalidade');"
		    onkeyup="document.forms[0].descricaoLocalidade.value=''"/> 
			
			<a href="javascript:habilitarPesquisaLocalidade(document.forms[0].idTipoDocumento);">
					<img width="23" height="21" border="0"
						src="<bean:message key="caminho.imagens"/>pesquisa.gif"
						title="Pesquisar Localidade" /></a>
			
		    <logic:present name="idLocalidadeNaoEncontrada">
		  	  <logic:equal name="idLocalidadeNaoEncontrada" value="exception">
			    <html:text property="descricaoLocalidade" size="40" maxlength="30" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000" />
			  </logic:equal>
			  <logic:notEqual name="idLocalidadeNaoEncontrada" value="exception">
			    <html:text property="descricaoLocalidade" size="40" maxlength="30" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" />
			  </logic:notEqual>
			</logic:present> 
			<logic:notPresent name="idLocalidadeNaoEncontrada">
			  <logic:empty name="PagamentoActionForm" property="idLocalidade">
		 	    <html:text property="descricaoLocalidade" value="" size="40" maxlength="30" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000" />
			  </logic:empty>
			  <logic:notEmpty name="PagamentoActionForm" property="idLocalidade">
 			    <html:text property="descricaoLocalidade" size="40" maxlength="30" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" />
			  </logic:notEmpty>
			</logic:notPresent> 
			
						<a href="javascript:limparLocalidade();">
				<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
				border="0" title="Apagar" /> </a>
			
		  </td>
		</tr>
        
        <tr>
		  <td height="10" width="160"><strong>Matrícula do Imóvel:</strong></td>
		  <td width="82%" height="24">
		  
		    <html:text maxlength="9" tabindex="1" property="idImovel" size="9" 
		    onkeyup="javascript:desabilitarPesquisaCliente(document.forms[0]);"
		    onkeypress="validaEnter(event, '/gsan/inserirPagamentosWizardAction.do?destino=2&action=exibirInserirPagamentosTipoInclusaoManualAction','idImovel');"/> 
		    
		    	<a href="javascript:habilitarPesquisaImovel(document.forms[0].idTipoDocumento);">
					<img width="23" height="21" border="0"
						src="<bean:message key="caminho.imagens"/>pesquisa.gif"
						title="Pesquisar Matrícula do Imóvel" /></a>
			
		    <logic:present name="idImovelNaoEncontrado">
		  	  <logic:equal name="idImovelNaoEncontrado" value="exception">
			    <html:text property="descricaoImovel" size="50" maxlength="50" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000" />
			  </logic:equal>
			  <logic:notEqual name="idImovelNaoEncontrado" value="exception">
			    <html:text property="descricaoImovel" size="50" maxlength="50" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" />
			  </logic:notEqual>
			</logic:present> 
			<logic:notPresent name="idImovelNaoEncontrado">
			  <logic:empty name="PagamentoActionForm" property="idImovel">
		 	    <html:text property="descricaoImovel" value="" size="50" maxlength="50" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000" />
			  </logic:empty>
			  <logic:notEmpty name="PagamentoActionForm" property="idImovel">
 			    <html:text property="descricaoImovel" size="50" maxlength="50" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" />
			  </logic:notEmpty>
			</logic:notPresent> 
			<a href="javascript:limparImovel();desabilitarPesquisaCliente(document.forms[0]);">
				<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
				border="0" title="Apagar" /> </a>
		  </td>
		  
		</tr>
        
        <tr>
		  <td width="18%"><strong>Código do Cliente:</strong></td>
		  <td width="82%">
		    <html:text maxlength="9" property="idCliente" tabindex="6" size="10" 
		     onkeyup="javascript:desabilitarPesquisaImovel(document.forms[0]);"
		    onkeypress="return validaEnter(event, '/gsan/inserirPagamentosWizardAction.do?destino=2&action=exibirInserirPagamentosTipoInclusaoManualAction', 'idCliente');" />
 			
		    	<a href="javascript:habilitarPesquisaCliente(document.forms[0].idTipoDocumento);">
					<img width="23" height="21" border="0"
						src="<bean:message key="caminho.imagens"/>pesquisa.gif"
						title="Pesquisar Código do Cliente" /></a> 					    	
 			
 			<logic:present name="idClienteNaoEncontrado">
			  <logic:equal name="idClienteNaoEncontrado" value="exception">
			    <html:text property="nomeCliente" size="50" maxlength="50" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000" />
			  </logic:equal>
			  <logic:notEqual name="idClienteNaoEncontrado" value="exception">
			    <html:text property="nomeCliente" size="50" maxlength="50" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" />
			  </logic:notEqual>
			</logic:present> 
			<logic:notPresent name="idClienteNaoEncontrado">
			  <logic:empty name="PagamentoActionForm" property="idCliente">
				<html:text property="nomeCliente" value="" size="50" maxlength="50" readonly="true"	style="background-color:#EFEFEF; border:0; color: #ff0000" />
			  </logic:empty>
			  <logic:notEmpty name="PagamentoActionForm" property="idCliente">
				<html:text property="nomeCliente" size="50" maxlength="50" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" />
			  </logic:notEmpty>
			</logic:notPresent> 
			    <a href="javascript:limparCliente();desabilitarPesquisaImovel(document.forms[0]);">
				<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
				border="0" title="Apagar" /> </a>
		  </td>
		</tr>
        <tr> 
          <td height="18" colspan="4"> <hr> </td>
        </tr>
        
        <tr>
		  <td><strong>Referência da Conta:</strong></td>
		  <td>
		    <strong> 
		      <html:text property="referenciaConta" size="7" maxlength="7" 
		      onkeyup="javascript:validaDependenciaImovel(this);document.forms[0].descricaoReferenciaConta.value=''"
		      onkeypress="javascript:mascaraAnoMes(this,event);return validaEnterString(event, '/gsan/inserirPagamentosWizardAction.do?destino=2&action=exibirInserirPagamentosTipoInclusaoManualAction', 'referenciaConta');"/>
		    </strong>mm/aaaa 
		    
		    	<a href="javascript:habilitarPesquisaConta(document.forms[0].idImovel);">
					<img width="23" height="21" border="0"
						src="<bean:message key="caminho.imagens"/>pesquisa.gif"
						title="Pesquisar Referência da Conta" /></a> 	
		    
		    <logic:present name="referenciaContaNaoEncontrada">
		  	  <logic:equal name="referenciaContaNaoEncontrada" value="exception">
			    <html:text property="descricaoReferenciaConta" size="40" maxlength="30" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000" />
			  </logic:equal>
			  <logic:notEqual name="referenciaContaNaoEncontrada" value="exception">
			    <html:text property="descricaoReferenciaConta" size="40" maxlength="30" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" />
			  </logic:notEqual>
			</logic:present> 
			<logic:notPresent name="referenciaContaNaoEncontrada" >
			  <logic:empty name="PagamentoActionForm" property="referenciaConta">
		 	    <html:text property="descricaoReferenciaConta" value="" size="40" maxlength="30" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000" />
			  </logic:empty>
			  <logic:notEmpty name="PagamentoActionForm" property="referenciaConta">
 			    <html:text property="descricaoReferenciaConta" size="40" maxlength="30" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" />
			  </logic:notEmpty>
			</logic:notPresent> 
    		    <a href="javascript:limparReferenciaConta();">
				<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
				border="0" title="Apagar" /> </a>
		  </td>
		</tr>
        
        <tr>
		  <td><strong>Guia de Pagamento:</strong></td>
		  <td width="82%" height="24">
		  
		    <html:text maxlength="9" tabindex="1"	property="idGuiaPagamento" size="9" 
		    onkeyup="javascript:validaDependenciaImovelCliente(this);
		    desabilitarPesquisaTipoDebito(document.forms[0]);
		    document.forms[0].descricaoGuiaPagamento.value='';
		    document.forms[0].valorGuiaPagamento.value=''" 
		    onkeypress="validaEnter(event, '/gsan/inserirPagamentosWizardAction.do?destino=2&action=exibirInserirPagamentosTipoInclusaoManualAction','idGuiaPagamento');"/> 
		    
		    	<a href="javascript:habilitarPesquisaGuiaPagamento(document.forms[0]);">
					<img width="23" height="21" border="0"
						src="<bean:message key="caminho.imagens"/>pesquisa.gif"
						title="Pesquisar Guia de Pagamento" /></a> 			    
		    
		    <logic:present name="idGuiaPagamentoNaoEncontrado">
		  	  <logic:equal name="idGuiaPagamentoNaoEncontrado" value="exception">
			    <html:text property="descricaoGuiaPagamento" size="30" maxlength="30" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000" />
			  </logic:equal>
			  <logic:notEqual name="idGuiaPagamentoNaoEncontrado" value="exception">
			    <html:text property="descricaoGuiaPagamento" size="30" maxlength="30" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" />
			  </logic:notEqual>
			</logic:present> 
			<logic:notPresent name="idGuiaPagamentoNaoEncontrado">
			  <logic:empty name="PagamentoActionForm" property="idGuiaPagamento">
		 	    <html:text property="descricaoGuiaPagamento" value="" size="30" maxlength="30" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000" />
			  </logic:empty>
			  <logic:notEmpty name="PagamentoActionForm" property="idGuiaPagamento">
 			    <html:text property="descricaoGuiaPagamento" size="30" maxlength="30" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" />
			  </logic:notEmpty>
			</logic:notPresent> 
			
			<html:text maxlength="14" tabindex="1"	property="valorGuiaPagamento" size="14" maxlength="14" disabled="true" /> 
		    
		    		    		    <a href="javascript:limparGuiaPagamento();">
				<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
				border="0" title="Apagar" /> </a>
		    
		  </td>
		</tr>
        
        <tr>
		  <td><strong> Débito a Cobrar:</strong></td>
		  <td width="82%" height="24">
		  
		    <html:text maxlength="9" tabindex="1" property="idDebitoACobrar" size="9" 
		    onkeyup="javascript:validaDependenciaImovel(this);desabilitarPesquisaTipoDebito(document.forms[0]);
		    document.forms[0].descricaoDebitoACobrar.value='';
		    document.forms[0].valorDebitoACobrar.value=''" 
		    onchange="javascript:desabilitarPesquisaTipoDebito(document.forms[0]);" onkeypress="validaEnter(event, '/gsan/inserirPagamentosWizardAction.do?destino=2&action=exibirInserirPagamentosTipoInclusaoManualAction','idDebitoACobrar');"/> 
		    
		    	<a href="javascript:habilitarPesquisaDebitoACobrar(document.forms[0]);">
					<img width="23" height="21" border="0"
						src="<bean:message key="caminho.imagens"/>pesquisa.gif"
						title="Pesquisar Débito a Cobrar" /></a> 				    
		    
		    <logic:present name="idDebitoACobrarNaoEncontrado">
		  	  <logic:equal name="idDebitoACobrarNaoEncontrado" value="exception">
			    <html:text property="descricaoDebitoACobrar" size="30" maxlength="30" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000" />
			  </logic:equal>
			  <logic:notEqual name="idDebitoACobrarNaoEncontrado" value="exception">
			    <html:text property="descricaoDebitoACobrar" size="30" maxlength="30" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" />
			  </logic:notEqual>
			</logic:present> 
			<logic:notPresent name="idDebitoACobrarNaoEncontrado">
			  <logic:empty name="PagamentoActionForm" property="idDebitoACobrar">
		 	    <html:text property="descricaoDebitoACobrar" value="" size="30" maxlength="30" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000"/>
			  </logic:empty>
			  <logic:notEmpty name="PagamentoActionForm" property="idDebitoACobrar">
 			    <html:text property="descricaoDebitoACobrar" size="30" maxlength="30" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" />
			  </logic:notEmpty>
			</logic:notPresent> 
			
			<html:text maxlength="9" tabindex="1" property="valorDebitoACobrar" size="14" maxlength="14" disabled="true"/> 
		    
		    		    <a href="javascript:limparDebitoACobrar();">
				<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
				border="0" title="Apagar" /> </a>
		    
		  </td>
		</tr>
        
        <tr>
		  <td><strong>Tipo de Débito:</strong></td>
		  <td width="82%" height="24">
		  
		    <html:text maxlength="9" tabindex="1" property="idTipoDebito" size="9" 
		    onkeyup="javascript:desabilitarPesquisaGuiaPagamento(document.forms[0]);
		    desabilitarPesquisaDebitoACobrar(document.forms[0]);
		    document.forms[0].descricaoTipoDebito.value=''" 
		    onchange="javascript:desabilitarPesquisaGuiaPagamento(document.forms[0]);desabilitarPesquisaDebitoACobrar(document.forms[0]);" 
		    onkeypress="validaEnter(event, '/gsan/inserirPagamentosWizardAction.do?destino=2&action=exibirInserirPagamentosTipoInclusaoManualAction','idTipoDebito');"/> 
		    
		    	<a href="javascript:habilitarPesquisaTipoDebito(document.forms[0]);">
					<img width="23" height="21" border="0"
						src="<bean:message key="caminho.imagens"/>pesquisa.gif"
						title="Pesquisar Tipo de Débito" /></a> 		    
		    
		    <logic:present name="idTipoDebitoNaoEncontrado">
		  	  <logic:equal name="idTipoDebitoNaoEncontrado" value="exception">
			    <html:text property="descricaoTipoDebito" size="50" maxlength="50" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000" />
			  </logic:equal>
			  <logic:notEqual name="idTipoDebitoNaoEncontrado" value="exception">
			    <html:text property="descricaoTipoDebito" size="50" maxlength="50" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" />
			  </logic:notEqual>
			</logic:present> 
			<logic:notPresent name="idTipoDebitoNaoEncontrado">
			  <logic:empty name="PagamentoActionForm" property="idTipoDebito">
		 	    <html:text property="descricaoTipoDebito" value="" size="50" maxlength="50" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000" />
			  </logic:empty>
			  <logic:notEmpty name="PagamentoActionForm" property="idTipoDebito">
 			    <html:text property="descricaoTipoDebito" size="50" maxlength="50" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" />
			  </logic:notEmpty>
			</logic:notPresent> 
		    
		    <a href="javascript:limparTipoDebito();">
				<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
				border="0" title="Apagar" /> </a>
		    
		  </td>
		</tr>
        
        <tr> 
          <td height="18" colspan="4"> <hr> </td>
        </tr>
        
        <tr> 
          <td><strong>Valor do Pagamento:<font color="#FF0000">*</font></strong></td>
          <td> 
            <strong> 
              <html:text property="valorPagamento" size="14" maxlength="14" 
              onkeyup="formataValorMonetario(this, 14)"style="text-align:right;"/>
            </strong> 
          </td>
        </tr>
        
        <tr>
          <td>
            <strong>  </strong>
          </td>
          <td>
            <strong> <font color="#FF0000">*</font> </strong> Campo obrigat&oacute;rio
          </td>
        </tr>
        
        <%-- Colocado por Raphael Rossiter em 24/09/2007
			Objetivo: Inserir mais de um Pagamento por vez
								
		<%-- ================================================================================= --%>
				
		<%int cont = 0;%>
				
		<tr>
			<td height="5" colspan="2"></td>
		</tr>
		<tr>
			<td colspan="2">
							
				<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
				<tr>
					<td height="17"><strong>Pagamentos:</strong></td>
					<td align="right"><input type="button" class="bottonRightCol"
					value="Adicionar" tabindex="11" style="width: 80px"
					onclick="adicionarPagamento(document.forms[0]);"></td>
				</tr>
				
				<tr>
					<td height="5" colspan="2"></td>
				</tr>
				
				<tr>
					<td colspan="2">

						<table width="100%" cellpadding="0" cellspacing="0">
						<tr>
							<td>

								<table width="100%" bgcolor="#99CCFF">
								<tr bgcolor="#99CCFF">
	
									<td align="center" width="10%"><strong>Remover</strong></td>
									<td width="30%">
										<div align="center"><strong>Tipo Doc.</strong></div>
									</td>
									<td width="12%">
										<div align="center"><strong>Imovel</strong></div>
									</td>
									<td width="12%">
										<div align="center"><strong>Cliente</strong></div>
									</td>
									<td width="12%">
										<div align="center"><strong>Ref. Conta</strong></div>
									</td>
									<td width="12%">
										<div align="center"><strong>Tipo Deb.</strong></div>
									</td>
									<td width="12%">
										<div align="center"><strong>Vl. Pag.</strong></div>
									</td>
	
								</tr>
								</table>

							</td>
						</tr>

						<tr>
							<td>

								<div style="width: 100%; height: 100; overflow: auto;">
										

								<table width="100%" align="center" bgcolor="#99CCFF">
								
								<logic:present name="colecaoPagamento">
								
									<INPUT TYPE="hidden" ID="pagamentoInformado" VALUE="OK">

									<logic:iterate name="colecaoPagamento" id="pagamento"
									type="Pagamento">

										<%cont = cont + 1;
										if (cont % 2 == 0) {%>
										<tr bgcolor="#cbe5fe">
											<%} else {%>
										<tr bgcolor="#FFFFFF">
											<%}%>

										<td align="center" width="10%" valign="middle">
											<a href="javascript:removerPagamento(<%="" + GcomAction.obterTimestampIdObjeto(pagamento) %>)">
												<img src="<bean:message key='caminho.imagens'/>Error.gif" border="0" >
											</a>
										</td>
										
										<td width="30%"><bean:write name="pagamento"
										property="documentoTipo.descricaoAbreviado" /></td>
										
										<logic:present name="pagamento" property="imovel">
											
											<td width="12%" align="center"><bean:write name="pagamento"
											property="imovel.id" /></td>
										
										</logic:present>
										
										<logic:notPresent name="pagamento" property="imovel">
											
											<td width="12%"></td>
										
										</logic:notPresent>
										
										<logic:present name="pagamento" property="cliente">
											
											<td width="12%" align="center"><bean:write name="pagamento"
											property="cliente.id" /></td>
										
										</logic:present>
										
										<logic:notPresent name="pagamento" property="cliente">
											
											<td width="12%"></td>
										
										</logic:notPresent>
										
										<logic:present name="pagamento" property="anoMesReferenciaPagamento">
											
											<td width="12%" align="center"><%= "" + Util.formatarAnoMesParaMesAno(pagamento.getAnoMesReferenciaPagamento()) %></td>
										
										</logic:present>
										
										<logic:notPresent name="pagamento" property="anoMesReferenciaPagamento">
											
											<td width="12%"></td>
										
										</logic:notPresent>
										
										<logic:present name="pagamento" property="debitoTipo">
											
											<td width="12%" align="center"><bean:write name="pagamento"
											property="debitoTipo.id" /></td>
										
										</logic:present>
										
										<logic:notPresent name="pagamento" property="debitoTipo">
											
											<td width="12%"></td>
										
										</logic:notPresent>
										
										<td width="12%" align="right"><bean:write name="pagamento"
										property="valorPagamento" formatKey="money.format"/></td>
											
										</tr>


									</logic:iterate>
									
								</logic:present>
									
								</table>
								</div>
						
							</td>
						</tr>
						</table>
					
					</td>
				</tr>
        
        		</table>						
							
			</td>
		</tr>
        
        
        <%-- Colocado por Raphael Rossiter em 24/09/2007
			Objetivo: Inserir mais de um Pagamento por vez - FIM
								
		<%-- ================================================================================= --%>
        
        
        <tr>
          <td colspan="2"><div align="right"><jsp:include page="/jsp/util/wizard/navegacao_botoes_wizard_valida_avancar.jsp?numeroPagina=2"/></div></td>
        </tr>
      </table>
      <p>&nbsp;</p>
    </td>
  </tr>

</table>

<%@ include file="/jsp/util/rodape.jsp"%>

<script language="JavaScript">
<!--
habilitacaoCampos();
//-->
</script>
</body>
</html:form>
</html:html>
