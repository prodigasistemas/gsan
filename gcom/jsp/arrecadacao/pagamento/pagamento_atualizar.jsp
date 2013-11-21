<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan" %>

<%@ page import="gcom.cobranca.DocumentoTipo" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>

<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="ManterPagamentoActionForm"/>
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js" ></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js" ></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js" ></script>
<script>
<!--
window.onmousemove = habilitacaoCampos;

function validarForm(form){
   form.idLocalidade.value = form.codigoLocalidade.value;
   form.idImovel.value = form.codigoImovel.value;
   form.idCliente.value = form.codigoCliente.value;
   form.idGuiaPagamento.value = form.codigoGuiaPagamento.value;
   form.idDebitoACobrar.value = form.codigoDebitoACobrar.value;
   form.idTipoDebito.value = form.codigoTipoDebito.value;
   
  if(validateManterPagamentoActionForm(form)&& validaTipoDocumento() && verificarReferenciaConta() && verificarGuiaPagamento() && verificarDebitoACobrar() && verificarImovel() && verificaDataPagamento(form)){
	redirecionarSubmit('/gsan/atualizarPagamentosAction.do');
  }
}
    
-->
</script>

<script type="text/javascript" language="Javascript1.1">
<!--
//Vivianne Sousa - 19/08/06

window.onmousemove = habilitacaoCampos;

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



<script>
<!--
window.onmousemove = habilitacaoCampos;

function verificaDataPagamento(form){
  var DATA_ATUAL = document.getElementById("DATA_ATUAL").value;
  
  if (comparaData(form.dataPagamento.value, ">", DATA_ATUAL)){
	alert('Data do pagamento posterior à data corrente ' + DATA_ATUAL);
  } else{
    return true;
  }
}

function recuperarDadosCincoParametros(codigoRegistro, descricaoRegistro1, descricaoRegistro2, descricaoRegistro3, tipoConsulta) { 
	var form = document.forms[0];
	
 	if (tipoConsulta == 'debitoACobrar') {
 	    form.idDebitoACobrar.value = codigoRegistro;
 	    form.codigoDebitoACobrar.value = codigoRegistro;
 	    form.valorDebitoACobrar.value = descricaoRegistro1;
		form.descricaoDebitoACobrar.value = descricaoRegistro3;
		//[FS0018] Verificar valor do débito a cobrar
		form.valorPagamento.value = descricaoRegistro1;
		limparTipoDebito();
		desabilitarPesquisaTipoDebito(form);
	}
	
	if (tipoConsulta == 'guiaPagamento') {
 	    form.idGuiaPagamento.value = codigoRegistro;
 	    form.codigoGuiaPagamento.value = codigoRegistro;
		form.valorGuiaPagamento.value = descricaoRegistro1;
		form.descricaoGuiaPagamento.value = descricaoRegistro3;
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
	}
}

function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {

    var form = document.forms[0];

    if (tipoConsulta == 'avisoBancario') {
      form.idAvisoBancario.value = codigoRegistro;
      form.descricaoAvisoBancario.value = descricaoRegistro;
    }
    
    if (tipoConsulta == 'localidade') { 	
 	  form.idLocalidade.value = codigoRegistro;
 	  form.codigoLocalidade.value = codigoRegistro;
 	  form.descricaoLocalidade.value = descricaoRegistro; 		
 	}
 	
 	if (tipoConsulta == 'imovel') { 	
 	  form.idImovel.value = codigoRegistro;
 	  form.codigoImovel.value = codigoRegistro;
 	  form.descricaoImovel.value = descricaoRegistro; 
 	}
 	
 	if (tipoConsulta == 'cliente') { 	
 	  form.idCliente.value = codigoRegistro;
 	  form.codigoCliente.value = codigoRegistro;
 	  form.nomeCliente.value = descricaoRegistro; 		
 	}
 	
 	if (tipoConsulta == 'tipoDebito') { 	
 	  form.idTipoDebito.value = codigoRegistro;
 	  form.codigoTipoDebito.value = codigoRegistro;
 	  form.descricaoTipoDebito.value = descricaoRegistro; 
 	  form.codigoDebitoACobrar.disabled = true;
 	  form.codigoGuiaPagamento.disabled = true;
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
 	form.codigoLocalidade.value = "";
 	form.descricaoLocalidade.value = ""; 		
 }

function limparImovel(){
 	var form = document.forms[0];
 	
 	form.idImovel.value = "";
 	form.codigoImovel.value = "";
 	form.descricaoImovel.value = ""; 	
 }

function limparCliente(){
 	var form = document.forms[0];
 	
    form.idCliente.value = "";
    form.codigoCliente.value = "";
 	form.nomeCliente.value = ""; 		
 }

 function limparTipoDebito(){
 	var form = document.forms[0];
 	
 	form.idTipoDebito.value = "";
 	form.codigoTipoDebito.value = "";
 	form.descricaoTipoDebito.value = ""; 	
 	
 	if(form.idTipoDocumento.value == <%=DocumentoTipo.GUIA_PAGAMENTO%>){    
        form.codigoGuiaPagamento.disabled = false; 
    }else if(form.idTipoDocumento.value == <%=DocumentoTipo.DEBITO_A_COBRAR%>){
        form.codigoDebitoACobrar.disabled = false; 
    }
 }
 
 function limparGuiaPagamento(){
 	var form = document.forms[0];
 	
 	form.idGuiaPagamento.value = "";
 	form.codigoGuiaPagamento.value = "";
 	form.descricaoGuiaPagamento.value = ""; 		
 	form.valorGuiaPagamento.value = "";
 	
 	form.codigoTipoDebito.disabled = false;
 }
 
 function limparDebitoACobrar(){
 	var form = document.forms[0];
 	
 	form.idDebitoACobrar.value = "";
 	form.codigoDebitoACobrar.value = "";
 	form.descricaoDebitoACobrar.value = ""; 		
 	form.valorDebitoACobrar.value = "";
 	
 	form.codigoTipoDebito.disabled = false;
 }
 
 //[SB0004- Habilitar/Desabilitar Campos]
 //function habilitacaoCampos(listBoxDocumentoTipo){
 
 function habilitacaoCampos(){
  
	var form = document.forms[0];
	 
 	var CONTA = document.getElementById("DOCUMENTO_TIPO_CONTA").value;
 	var GUIA_PAGAMENTO = document.getElementById("DOCUMENTO_TIPO_GUIA_PAGAMENTO").value;
 	var DEBITO_A_COBRAR = document.getElementById("DOCUMENTO_TIPO_DEBITO_A_COBRAR").value;
 	
 	//var tipoDocumentoSelected = listBoxDocumentoTipo.value;
 	
 	var tipoDocumentoSelected = form.idTipoDocumento.value;
 	
 	if (tipoDocumentoSelected == CONTA ){
 		
 		
 		form.codigoLocalidade.disabled = true;
 		form.idImovel.disabled = false;
 		
 		form.idCliente.value = "";
 		form.codigoCliente.value = "";
 		form.codigoCliente.disabled = true;
 		form.nomeCliente.value = "";
 		
 		form.referenciaConta.disabled = false;
 		
 		form.idGuiaPagamento.value = "";
 		form.codigoGuiaPagamento.value = "";
 		form.descricaoGuiaPagamento.value = "";
 		form.valorGuiaPagamento.value = "";
 		form.codigoGuiaPagamento.disabled = true;
 		form.valorGuiaPagamento.disabled = true;
 		
 		form.idDebitoACobrar.value = "";
 		form.codigoDebitoACobrar.value = "";
 		form.descricaoDebitoACobrar.value = "";
 		form.valorDebitoACobrar.value = "";
 		form.codigoDebitoACobrar.disabled = true;
 		form.valorDebitoACobrar.disabled = true;
 		
 		form.idTipoDebito.value = "";
 		form.codigoTipoDebito.value = "";
 		form.descricaoTipoDebito.value = "";
 		form.codigoTipoDebito.disabled = true;
 		
 		//form.codigoImovel.focus();
 		
 	}
 	else if(tipoDocumentoSelected == GUIA_PAGAMENTO){
	 	
 		form.codigoLocalidade.disabled = false;
 		
 		form.codigoImovel.disabled = false;
 		
 		form.codigoCliente.disabled = false;
 		
 		form.referenciaConta.value = "";
 		form.descricaoReferenciaConta.value = "";
 		form.referenciaConta.disabled = true;
 		
 		form.codigoGuiaPagamento.disabled = false;
 		
 		form.idDebitoACobrar.value = "";
 		form.codigoDebitoACobrar.value = "";
 		form.descricaoDebitoACobrar.value = "";
 		form.valorDebitoACobrar.value = "";
 		form.codigoDebitoACobrar.disabled = true;
 		form.valorDebitoACobrar.disabled = true;
 		
 		form.codigoTipoDebito.disabled = false;
 		
 		
 		if(form.codigoGuiaPagamento.value != ""){
 		  if(form.codigoTipoDebito.value == ""){
 		    form.codigoTipoDebito.disabled = true;
 		  }
 		}else{
		  if(form.codigoTipoDebito.value != ""){
 		    form.codigoGuiaPagamento.disabled = true;
 		  }
 		}
 		
 		
 	} 
 	else if(tipoDocumentoSelected == DEBITO_A_COBRAR){
 	
 		form.codigoLocalidade.disabled = true;
 		
 		form.codigoImovel.disabled = false;
 		
 		form.idCliente.value = "";
 		form.codigoCliente.value = "";
 		form.codigoCliente.disabled = true;
 		form.nomeCliente.value = "";
 		
 		form.referenciaConta.value = "";
 		form.descricaoReferenciaConta.value = "";
 		form.referenciaConta.disabled = true;
 		
 		form.idGuiaPagamento.value = "";
 		form.codigoGuiaPagamento.value = "";
 		form.descricaoGuiaPagamento.value = "";
 		form.valorGuiaPagamento.value = "";
 		form.codigoGuiaPagamento.disabled = true;
 		form.valorGuiaPagamento.disabled = true;
 		
 		form.codigoDebitoACobrar.disabled = false;
 		
 		form.codigoTipoDebito.disabled = false;
 		
 		if(form.codigoDebitoACobrar.value != ""){
 		  if(form.codigoTipoDebito.value == ""){
 		    form.codigoTipoDebito.disabled = true;
 		  }
 		}else{
		  if(form.codigoTipoDebito.value != ""){
 		    form.codigoDebitoACobrar.disabled = true;
 		  }
 		}
 		
 		
 		//form.codigoImovel.focus();
 	}
 	else {
 	
 		form.idLocalidade.value = "";
 		form.codigoLocalidade.value = "";
 		form.codigoLocalidade.disabled = true;
 		form.descricaoLocalidade.value ="";
 		
 		form.idImovel.value = "";
 		form.codigoImovel.value = "";
 		form.descricaoImovel.value = ""; 
 		form.codigoImovel.disabled = true;
 		
 		form.idCliente.value = "";
 		form.codigoCliente.value = "";
 		form.codigoCliente.disabled = true;
 		form.nomeCliente.value = "";
 		
 		form.referenciaConta.value = "";
 		form.descricaoReferenciaConta.value = "";
 		form.referenciaConta.disabled = true;
 		
 		form.idGuiaPagamento.value = "";
 		form.codigoGuiaPagamento.value = "";
 		form.descricaoGuiaPagamento.value = "";
 		form.valorGuiaPagamento.value = "";
 		form.codigoGuiaPagamento.disabled = true;
 		form.valorGuiaPagamento.disabled = true;
 		
 		form.idDebitoACobrar.value = "";
 		form.codigoDebitoACobrar.value = "";
 		form.descricaoDebitoACobrar.value = "";
 		form.valorDebitoACobrar.value = "";
 		form.codigoDebitoACobrar.disabled = true;
 		form.valorDebitoACobrar.disabled = true;
 		
 		form.idTipoDebito.value = "";
 		form.codigoTipoDebito.value = "";
 		form.descricaoTipoDebito.value = "";
 		form.codigoTipoDebito.disabled = true;
 	}
 }
 
 function habilitarPesquisaLocalidade(listBoxDocumentoTipo){
 
   if(listBoxDocumentoTipo.value == <%=DocumentoTipo.GUIA_PAGAMENTO%>){
     abrirPopup('exibirPesquisarLocalidadeAction.do', 400, 800);
   }else{
     return false;
   }
 }
 
 function habilitarPesquisaConta(idImovel){
 
   var form = document.forms[0];
   
   if(form.idTipoDocumento.value == <%=DocumentoTipo.CONTA%>){
     if(idImovel.value == ""){
       alert('Informe Matrícula do Imóvel.')
     }else{
     abrirPopup('exibirPesquisarContaAction.do?idImovel='+idImovel.value , 400, 800);
     }
   }else{
     return false;
   }
 }
 
 function habilitarPesquisaGuiaPagamento(form){
 
   if(form.idTipoDocumento.value == <%=DocumentoTipo.GUIA_PAGAMENTO%>){
   
   if (form.idTipoDebito.value == "") {
   
     if(form.idImovel.value != ""){
       abrirPopup('exibirPesquisarGuiaPagamentoAction.do?idImovel='+form.idImovel.value , 500, 775);
     }else if(form.idCliente.value != ""){
       abrirPopup('exibirPesquisarGuiaPagamentoAction.do?idCliente='+form.idCliente.value , 500, 775);
     }else{
       alert('Informe Matrícula do Imóvel ou Código do Cliente.')
     }
   } else{
      return false;
   }
   }  
 }
 
 function habilitarPesquisaDebitoACobrar(form){
 
   if(form.idTipoDocumento.value == <%=DocumentoTipo.DEBITO_A_COBRAR%>){
   
     if(form.idImovel.value == ""){
       alert('Informe Matrícula do Imóvel.')
     }else{
       abrirPopup('exibirPesquisarDebitoACobrarAction.do?idImovel='+form.idImovel.value , 500, 800);
     }
   }else{
     return false;
   }  
 }
 
  function habilitarPesquisaTipoDebito(form){
 
   if(form.idTipoDocumento.value == <%=DocumentoTipo.DEBITO_A_COBRAR%> || form.idTipoDocumento.value == <%=DocumentoTipo.GUIA_PAGAMENTO%>){
     abrirPopup('exibirPesquisarTipoDebitoAction.do', 500, 800);
   }else{
     return false;
   }  
 }
 
 //[FS0021]
 function desabilitarPesquisaTipoDebito(form){
    limparTipoDebito();

    if(form.idTipoDocumento.value == <%=DocumentoTipo.GUIA_PAGAMENTO%>){    
      if(trim(form.codigoGuiaPagamento.value) == ""){
        form.codigoTipoDebito.disabled = false; 
      }else{
        form.codigoTipoDebito.disabled = true; 
      }
    }else if(form.idTipoDocumento.value == <%=DocumentoTipo.DEBITO_A_COBRAR%>){
      if(trim(form.codigoDebitoACobrar.value) == ""){
        form.codigoTipoDebito.disabled = false; 
      }else{
        form.codigoTipoDebito.disabled = true; 
      }    
    }
 }

 function desabilitarPesquisaGuiaPagamento(form){

    limparGuiaPagamento();
    
    if(form.idTipoDocumento.value == <%=DocumentoTipo.GUIA_PAGAMENTO%>){    
      if(trim(form.codigoTipoDebito.value) == ""){
        form.codigoGuiaPagamento.disabled = false; 
      }else{
        form.codigoGuiaPagamento.disabled = true; 
      }    
    }
 }

function desabilitarPesquisaDebitoACobrar(form){
    limparDebitoACobrar();
    
    if(form.idTipoDocumento.value == <%=DocumentoTipo.DEBITO_A_COBRAR%>){        
      if(trim(form.codigoTipoDebito.value) == ""){
        form.codigoDebitoACobrar.disabled = false; 
      }else{
        form.codigoDebitoACobrar.disabled = true; 
      }    
    }
 }
 
 function habilitarPesquisaImovel(listBoxDocumentoTipo){
 
   if(listBoxDocumentoTipo.value == <%=DocumentoTipo.GUIA_PAGAMENTO%> || listBoxDocumentoTipo.value == <%=DocumentoTipo.DEBITO_A_COBRAR%> || listBoxDocumentoTipo.value == <%=DocumentoTipo.CONTA%>){
     abrirPopup('exibirPesquisarImovelAction.do', 400, 800);
   }else{
     return false;
   }
 }
 
 
 function habilitarPesquisaCliente(listBoxDocumentoTipo){
   var form = document.forms[0];
    
   if(listBoxDocumentoTipo.value == <%=DocumentoTipo.GUIA_PAGAMENTO%>){
     if(form.idImovel.value != null && form.idImovel.value != "") {
     	alert('Informe Matrícula do Imóvel ou Código do Cliente.');
     }else{
       abrirPopup('exibirPesquisarClienteAction.do', 400, 800);
     }
   }else{
     return false;
   }
 }
 
    
 function verificarReferenciaConta(){
 
	  var form = document.forms[0];
	  
	  if(form.referenciaConta.disabled == false){
	  		  	
	  	if(form.referenciaConta.value == ""){
	    	alert('Informe Referência da Conta.');
	    	return false;
	  	} else{
	  	  if(verificaAnoMesMensagemPersonalizada(form.referenciaConta,'Referência da Conta inválida.')){
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
    if(form.codigoLocalidade.value == ""){
	  alert('Informe Localidade.');  
	  return false;
	}else{
	  return true;
	}
  }else{
    if(form.codigoGuiaPagamento.disabled == false){
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
	  
	  if(form.codigoDebitoACobrar.disabled == false){
	  		  	
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
<script>
<!-- 

function verificarPreenchimentoImovelCliente(){
  var form = document.forms[0];

  if(form.codigoCliente.value != "" && form.codigoImovel.value != ""){
	alert('Informe Matrícula do Imóvel ou Código do Cliente.');
   	return false;
  }else if(form.codigoCliente.value == "" && form.codigoImovel.value == ""){
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
   
      if(form.codigoImovel.disabled == false){
      
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
    
      if(form.codigoImovel.disabled == false){
      
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
 -->

</script>
</head>

<body leftmargin="5" topmargin="5">

<html:form
    action="/atualizarPagamentosAction"
    method="post"
    onsubmit="return validateManterPagamentoActionForm(this);"
>


<input type="hidden" id="DOCUMENTO_TIPO_CONTA" value="<%=DocumentoTipo.CONTA%>"/>
<input type="hidden" id="DOCUMENTO_TIPO_GUIA_PAGAMENTO" value="<%=DocumentoTipo.GUIA_PAGAMENTO%>"/>
<input type="hidden" id="DOCUMENTO_TIPO_DEBITO_A_COBRAR" value="<%=DocumentoTipo.DEBITO_A_COBRAR%>"/>


<%@ include file="/jsp/util/cabecalho.jsp"%>
<%@ include file="/jsp/util/menu.jsp" %>
<input type="hidden" id="DATA_ATUAL" value="${requestScope.dataAtual}"/>

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
          <td class="parabg">Atualizar Pagamentos</td>
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
			<html:hidden property="idAvisoBancario"/>
		  </td>
        </tr>
        <tr>
          <td>
            <strong>Forma de Arrecadação:</strong>
          </td>
          <td>
			<html:text property="descricaoFormaArrecadacao" size="65" maxlength="65" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>
			<html:hidden property="idFormaArrecadacao"/>
          </td>
        </tr>
        <tr> 
          <td colspan="4"> Para atualizar o pagamento, informe os dados abaixo:</td>
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
		    <html:hidden name="ManterPagamentoActionForm" property="idLocalidade"/> 
		    <input type="text" 
		    	   maxlength="3" 
		    	   tabindex="1" 
		    	   name="codigoLocalidade"  
		    	   value="<bean:write name="ManterPagamentoActionForm" property="idLocalidade"/>" 
		    	   size="3" 
		    	   onkeyup="form.idLocalidade.value = form.codigoLocalidade.value;validaEnter(event, '/gsan/exibirAtualizarPagamentosAction.do?reloadPage=1','codigoLocalidade');" 
		    	   onkeypress="return isCampoNumerico(event);"/> 
		    
			<img width="23" 
				 height="21" 
				 border="0" 
				 src="<bean:message key="caminho.imagens"/>pesquisa.gif" title="Pesquisar Localidade" 
				 onclick="javascript:habilitarPesquisaLocalidade(document.forms[0].idTipoDocumento);" 
				 style="cursor: pointer;cursor:hand;"
				 />
			
		    <logic:present name="idLocalidadeNaoEncontrada">
		  	  <logic:equal name="idLocalidadeNaoEncontrada" value="exception">
			    <html:text property="descricaoLocalidade" size="40" maxlength="30" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000" />
			  </logic:equal>
			  <logic:notEqual name="idLocalidadeNaoEncontrada" value="exception">
			    <html:text property="descricaoLocalidade" size="40" maxlength="30" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" />
			  </logic:notEqual>
			</logic:present> 
			<logic:notPresent name="idLocalidadeNaoEncontrada">
			  <logic:empty name="ManterPagamentoActionForm" property="idLocalidade">
		 	    <html:text property="descricaoLocalidade" value="" size="40" maxlength="30" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000" />
			  </logic:empty>
			  <logic:notEmpty name="ManterPagamentoActionForm" property="idLocalidade">
 			    <html:text property="descricaoLocalidade" size="40" maxlength="30" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" />
			  </logic:notEmpty>
			</logic:notPresent> 
			
		    <img border="0" src="<bean:message key='caminho.imagens'/>limparcampo.gif" 
		    	onclick="limparLocalidade();" title="Apagar" style="cursor: pointer;cursor:hand;"/>
		  </td>
		</tr>
        
        <tr>
		  <td height="10" width="160"><strong>Matrícula do Imóvel:</strong></td>
		  <td width="82%" height="24">
		    <html:hidden name="ManterPagamentoActionForm" property="idImovel"/> 
		    <input type="text" 
		    	   maxlength="9" 
		    	   tabindex="1" 
	  		       value="<bean:write name="ManterPagamentoActionForm" property="idImovel"/>" 
	  		       name="codigoImovel" 
	  		       size="9" 
			       onkeypress="form.idImovel.value = form.codigoImovel.value;validaEnter(event, '/gsan/exibirAtualizarPagamentosAction.do?reloadPage=1','codigoImovel');return isCampoNumerico(event);"
        	       onkeyup="javascript:desabilitarPesquisaCliente(document.forms[0]);"/> 
		    
			<img width="23" 
				 height="21" 
				 src="<bean:message key='caminho.imagens'/>pesquisa.gif" 
				 border="0" 
				 onclick="javascript:habilitarPesquisaImovel(document.forms[0].idTipoDocumento);" 
				 style="cursor: pointer;cursor:hand;"
				 title="Pesquisar Imóvel"/>
			
		    <logic:present name="idImovelNaoEncontrado">
		  	  <logic:equal name="idImovelNaoEncontrado" value="exception">
			    <html:text property="descricaoImovel" size="50" maxlength="50" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000" />
			  </logic:equal>
			  <logic:notEqual name="idImovelNaoEncontrado" value="exception">
			    <html:text property="descricaoImovel" size="50" maxlength="50" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" />
			  </logic:notEqual>
			</logic:present> 
			<logic:notPresent name="idImovelNaoEncontrado">
			  <logic:empty name="ManterPagamentoActionForm" property="idImovel">
		 	    <html:text property="descricaoImovel" value="" size="50" maxlength="50" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000" />
			  </logic:empty>
			  <logic:notEmpty name="ManterPagamentoActionForm" property="idImovel">
 			    <html:text property="descricaoImovel" size="50" maxlength="50" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" />
			  </logic:notEmpty>
			</logic:notPresent> 
			
		    <img border="0" 
		    	 src="<bean:message key='caminho.imagens'/>limparcampo.gif" 
		    	 onclick="limparImovel();desabilitarPesquisaCliente(document.forms[0]);" 
		    	 style="cursor: pointer;cursor:hand;" 
		    	 title="Apagar"/>
		  </td>
		  
		</tr>
        
        <tr>
		  <td width="18%"><strong>Código do Cliente:</strong></td>
		  <td width="82%">
		    <html:hidden name="ManterPagamentoActionForm" property="idCliente"/> 
		    <input type="text" maxlength="9" name="codigoCliente" 
			       value="<bean:write name="ManterPagamentoActionForm" property="idCliente"/>" 
			       tabindex="6" 
			       size="10" 
			       onkeypress="form.idCliente.value = form.codigoCliente.value; validaEnter(event, '/gsan/exibirAtualizarPagamentosAction.do?reloadPage=1', 'codigoCliente');return isCampoNumerico(event);"
			       onkeyup="javascript:desabilitarPesquisaImovel(document.forms[0]);" />
 			
 			<img width="23" height="21" border="0" src="<bean:message key="caminho.imagens"/>pesquisa.gif" title="Pesquisar Cliente" onclick="javascript:habilitarPesquisaCliente(document.forms[0].idTipoDocumento);" style="cursor: pointer;cursor: hand;"/>
 			
 			<logic:present name="idClienteNaoEncontrado">
			  <logic:equal name="idClienteNaoEncontrado" value="exception">
			    <html:text property="nomeCliente" size="50" maxlength="50" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000" />
			  </logic:equal>
			  <logic:notEqual name="idClienteNaoEncontrado" value="exception">
			    <html:text property="nomeCliente" size="50" maxlength="50" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" />
			  </logic:notEqual>
			</logic:present> 
			<logic:notPresent name="idClienteNaoEncontrado">
			  <logic:empty name="ManterPagamentoActionForm" property="idCliente">
				<html:text property="nomeCliente" value="" size="50" maxlength="50" readonly="true"	style="background-color:#EFEFEF; border:0; color: #ff0000" />
			  </logic:empty>
			  <logic:notEmpty name="ManterPagamentoActionForm" property="idCliente">
				<html:text property="nomeCliente" size="50" maxlength="50" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" />
			  </logic:notEmpty>
			</logic:notPresent> 
			
			<img border="0" src="<bean:message key='caminho.imagens'/>limparcampo.gif" title="Apagar" onclick="limparCliente();desabilitarPesquisaImovel(document.forms[0]);" style="cursor: pointer;cursor: hand;"/>
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
		      onkeypress="javascript:mascaraAnoMes(this,event);validaEnterString(event, '/gsan/exibirAtualizarPagamentosAction.do?reloadPage=1', 'referenciaConta');return isCampoNumerico(event);"
		      />
		    </strong>mm/aaaa
			<img width="23" height="21" border="0" src="<bean:message key="caminho.imagens"/>pesquisa.gif" title="Pesquisar Conta" onclick="javascript:habilitarPesquisaConta(document.forms[0].idImovel);" style="cursor: pointer;cursor: hand;"/>
			
			
		    <logic:present name="referenciaContaNaoEncontrada">
		  	  
		  	  <logic:equal name="referenciaContaNaoEncontrada" value="exception">
			      <html:text property="descricaoReferenciaConta" 
			      			 size="40" 
			      			 maxlength="30" 
			      			 readonly="true" 
			      			 style="background-color:#EFEFEF; border:0; color: #ff0000" />
			  </logic:equal>
			  <logic:notEqual name="referenciaContaNaoEncontrada" value="exception">
			      <html:text property="descricaoReferenciaConta" 
			      			 size="40" 
			      			 maxlength="30" 
			      			 readonly="true" 
			      			 style="background-color:#EFEFEF; border:0; color: #000000" 
			      			 />
			  </logic:notEqual>
			  
			</logic:present> 
			
			<logic:notPresent name="referenciaContaNaoEncontrada" >
			  
			  <logic:empty name="ManterPagamentoActionForm" property="referenciaConta">
		 	    <html:text property="descricaoReferenciaConta" value="" size="40" maxlength="30" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000" />
			  </logic:empty>
			  <logic:notEmpty name="ManterPagamentoActionForm" property="referenciaConta">
 			    <html:text property="descricaoReferenciaConta" size="40" maxlength="30" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" />
			  </logic:notEmpty>
			  
			</logic:notPresent> 
			
		    <img border="0" src="<bean:message key='caminho.imagens'/>limparcampo.gif" title="Apagar" onclick="limparReferenciaConta();" style="cursor: hand;"/>
		  </td>
		</tr>
        
        <tr>
		  <td><strong>Guia de Pagamento:</strong></td>
		  <td width="82%" height="24">
		    <html:hidden name="ManterPagamentoActionForm" property="idGuiaPagamento"/> 
		    <input type="text" value="<bean:write name="ManterPagamentoActionForm" property="idGuiaPagamento"/>" 
		    	   maxlength="9" tabindex="1"	name="codigoGuiaPagamento" size="9" 
			       onkeyup="javascript:validaDependenciaImovelCliente(this);
				   desabilitarPesquisaTipoDebito(document.forms[0]);
				   document.forms[0].descricaoGuiaPagamento.value='';
				   document.forms[0].valorGuiaPagamento.value=''" 
				   onkeypress="form.idGuiaPagamento.value = form.codigoGuiaPagamento.value;validaEnter(event, '/gsan/exibirAtualizarPagamentosAction.do?reloadPage=1','codigoGuiaPagamento');return isCampoNumerico(event);"/> 
		    
			<img width="23" height="21" src="<bean:message key='caminho.imagens'/>pesquisa.gif" border="0" onclick="javascript:habilitarPesquisaGuiaPagamento(document.forms[0]);" title="Pesquisar Guia de Pagamento" style="cursor: pointer;cursor: hand;"/>
			
		    <logic:present name="idGuiaPagamentoNaoEncontrado">
		  	  <logic:equal name="idGuiaPagamentoNaoEncontrado" value="exception">
			    <html:text property="descricaoGuiaPagamento" size="30" maxlength="30" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000" />
			  </logic:equal>
			  <logic:notEqual name="idGuiaPagamentoNaoEncontrado" value="exception">
			    <html:text property="descricaoGuiaPagamento" size="30" maxlength="30" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" />
			  </logic:notEqual>
			</logic:present> 
			<logic:notPresent name="idGuiaPagamentoNaoEncontrado">
			  <logic:empty name="ManterPagamentoActionForm" property="idGuiaPagamento">
		 	    <html:text property="descricaoGuiaPagamento" value="" size="30" maxlength="30" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000" />
			  </logic:empty>
			  <logic:notEmpty name="ManterPagamentoActionForm" property="idGuiaPagamento">
 			    <html:text property="descricaoGuiaPagamento" size="30" maxlength="30" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" />
			  </logic:notEmpty>
			</logic:notPresent> 
			
			<html:text maxlength="14" tabindex="1"	property="valorGuiaPagamento" size="14" maxlength="14" disabled="true" /> 

		    <img border="0" src="<bean:message key='caminho.imagens'/>limparcampo.gif" title="Apagar" onclick="limparGuiaPagamento();" style="cursor: hand;"/>
		  </td>
		</tr>
        
        <tr>
		  <td><strong> Débito a Cobrar:</strong></td>
		  <td width="82%" height="24">
		    <html:hidden name="ManterPagamentoActionForm" property="idDebitoACobrar"/> 
			    <input type="text" value="<bean:write name="ManterPagamentoActionForm" property="idDebitoACobrar"/>" 
			    	   maxlength="9" tabindex="1" name="codigoDebitoACobrar" size="9" 
					   onkeyup="javascript:validaDependenciaImovel(this);desabilitarPesquisaTipoDebito(document.forms[0]);" 
					   onchange="javascript:desabilitarPesquisaTipoDebito(document.forms[0]);" 
			    	   onkeypress="form.idDebitoACobrar.value = form.codigoDebitoACobrar.value;validaEnter(event, '/gsan/exibirAtualizarPagamentosAction.do?reloadPage=1','codigoDebitoACobrar');return isCampoNumerico(event);"
   		  /> 
		    
			<img width="23" 
				 height="21" 
				 src="<bean:message key='caminho.imagens'/>pesquisa.gif" 
				 border="0" 
				 onclick="javascript:habilitarPesquisaDebitoACobrar(document.forms[0]);" style="cursor: pointer;cursor: hand;"/>
			
		    <logic:present name="idDebitoACobrarNaoEncontrado">
		  	  <logic:equal name="idDebitoACobrarNaoEncontrado" value="exception">
			    <html:text property="descricaoDebitoACobrar" 
			    		   size="30" 
			    		   maxlength="30" 
			    		   readonly="true" 
			    		   style="background-color:#EFEFEF; border:0; color: #ff0000" />
			  </logic:equal>
			  <logic:notEqual name="idDebitoACobrarNaoEncontrado" value="exception">
			    <html:text property="descricaoDebitoACobrar" 
			    		   size="30" 
			    		   maxlength="30" 
			    		   readonly="true" 
			    		   style="background-color:#EFEFEF; border:0; color: #000000" />
			  </logic:notEqual>
			</logic:present> 
			<logic:notPresent name="idDebitoACobrarNaoEncontrado">
			  <logic:empty name="ManterPagamentoActionForm" property="idDebitoACobrar">
		 	    <html:text property="descricaoDebitoACobrar" 
					 	   value="" 
					 	   size="30" 
					 	   maxlength="30" 
					 	   readonly="true" 
					 	   style="background-color:#EFEFEF; border:0; color: #ff0000"/>
			  </logic:empty>
			  <logic:notEmpty name="ManterPagamentoActionForm" property="idDebitoACobrar">
 			    <html:text property="descricaoDebitoACobrar" 
 			    		   size="30" 
 			    		   maxlength="30" 
 			    		   readonly="true" 
 			    		   style="background-color:#EFEFEF; border:0; color: #000000" />
			  </logic:notEmpty>
			</logic:notPresent> 
			
			<html:text maxlength="9" tabindex="1" property="valorDebitoACobrar" size="14" maxlength="14" disabled="true"/> 
			
		    <img border="0" src="<bean:message key='caminho.imagens'/>limparcampo.gif" onclick="limparDebitoACobrar();" title="Apagar" style="cursor: pointer;cursor: hand;"/>
		  </td>
		</tr>
        
        <tr>
		  <td><strong>Tipo de Débito:</strong></td>
		  <td width="82%" height="24">
		    <html:hidden name="ManterPagamentoActionForm" property="idTipoDebito"/> 
			    <input  type="text" 
			    		maxlength="9" 
			    		tabindex="1" 
			    		name="codigoTipoDebito" 
					    value="<bean:write name="ManterPagamentoActionForm" property="idTipoDebito"/>" 
					    size="9" 
					    onkeyup="javascript:desabilitarPesquisaGuiaPagamento(document.forms[0]);
						    desabilitarPesquisaDebitoACobrar(document.forms[0]);
						    document.forms[0].descricaoTipoDebito.value=''" 
					    onchange="javascript:desabilitarPesquisaGuiaPagamento(document.forms[0]);desabilitarPesquisaDebitoACobrar(document.forms[0]);" 
					    onkeypress="form.idTipoDebito.value = form.codigoTipoDebito.value;validaEnter(event, '/gsan/exibirAtualizarPagamentosAction.do?reloadPage=1','codigoTipoDebito');return isCampoNumerico(event);"
			 	/> 
		    
			<img width="23" 
				 height="21" 
				 src="<bean:message key='caminho.imagens'/>pesquisa.gif" 
				 border="0" 
				 onclick="javascript:habilitarPesquisaTipoDebito(document.forms[0]);" 
				 style="cursor: pointer;cursor: hand;" title="Pesquisar Tipo de Débito"/>
			
		    <logic:present name="idTipoDebitoNaoEncontrado">
		  	  <logic:equal name="idTipoDebitoNaoEncontrado" value="exception">
			    <html:text property="descricaoTipoDebito" size="50" maxlength="50" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000" />
			  </logic:equal>
			  <logic:notEqual name="idTipoDebitoNaoEncontrado" value="exception">
			    <html:text property="descricaoTipoDebito" size="50" maxlength="50" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" />
			  </logic:notEqual>
			</logic:present> 
			<logic:notPresent name="idTipoDebitoNaoEncontrado">
			  <logic:empty name="ManterPagamentoActionForm" property="idTipoDebito">
		 	    <html:text property="descricaoTipoDebito" value="" size="50" maxlength="50" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000" />
			  </logic:empty>
			  <logic:notEmpty name="ManterPagamentoActionForm" property="idTipoDebito">
 			    <html:text property="descricaoTipoDebito" size="50" maxlength="50" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" />
			  </logic:notEmpty>
			</logic:notPresent> 
			
		    <img border="0" src="<bean:message key='caminho.imagens' />limparcampo.gif" title="Apagar" onclick="limparTipoDebito();" style="cursor:pointer;cursor: hand;"/>
		  </td>
		</tr>
        
        <tr> 
          <td height="18" colspan="4"> <hr> </td>
        </tr>
        
        <tr>
          <td>
            <strong>Data do Pagamento:</strong>
          </td>
          <td>
            <html:text property="dataPagamento" 
            size="10" 
            maxlength="10" 
            onkeypress="javascript:mascaraData(this,event);return isCampoNumerico(event);"/>
            	<img border="0" 
            		 src="<bean:message key='caminho.imagens'/>calendario.gif" 
            		 onclick="javascript:abrirCalendario('ManterPagamentoActionForm', 'dataPagamento')"	
            		 width="20" 
            		 border="0" 
            		 align="absmiddle" 
            		 alt="Exibir Calendário" /> dd/mm/aaaa 
          </td>
        </tr>
        
        <tr> 
          <td><strong>Valor do Pagamento:<font color="#FF0000">*</font></strong></td>
          <td>
            <logic:equal name="habilitarValorPagamento" value="false" scope="session">
              <strong> 
                <html:text property="valorPagamento" 
                		   disabled="true" 
                		   size="14"
                		   maxlength="14" 
                		   style="text-align:right;"
                		   onkeypress="return isCampoNumerico(event);"/>
              </strong> 
            </logic:equal> 

            <logic:equal name="habilitarValorPagamento" value="true" scope="session">
              <strong> 
                <html:text property="valorPagamento" 
                		   size="14" 
                		   maxlength="14" 
		                   onkeyup="formataValorMonetario(this, 14)" 
		                   style="text-align:right;"
		                   onkeypress="formataValorMonetario(this, 14);return isCampoNumerico(event);"/>
              </strong> 
            </logic:equal> 
          </td>
        </tr>
        
        <tr>
          <td>
            <strong>Situação Atual do Pagamento:</strong>
          </td>
          <td>

            <logic:present name="colecaoSituacaoAtualPagamento" scope="session">
              <html:select property="idSituacaoAtualPagamento">
                <html:option value=""></html:option> 
			    <html:options collection="colecaoSituacaoAtualPagamento" labelProperty="descricao" property="id"/>
              </html:select>
            </logic:present>

            <logic:notPresent name="colecaoSituacaoAtualPagamento" scope="session">
              <html:select property="idSituacaoAtualPagamento" disabled="true">
                <html:option value=""></html:option> 
              </html:select>
            </logic:notPresent>
          </td>
        </tr>
        
        <tr>
	        <td><strong>Arrecadador:</strong></td>
	        <td>
		        <html:text property="codigoAgenteArrecadador" size="3" maxlength="3" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" />
	            <html:text property="nomeClienteArrecadador" size="40" maxlength="40" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" />
	        </td>
        </tr>
        
        <tr>
	        <td><strong>Ultima Alteração:</strong></td>
	        <td>
		        <html:text property="ultimaAlteracaoPagamento" size="10" maxlength="10" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" />
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
        
        <tr>
	        <td colspan="4">
		        <table width="100%">
			        <tr>
						<td>
							<input name="Button" 
								   type="button" 
								   class="bottonRightCol" 
								   value="Voltar" 
								   align="left"
								   onclick="window.location.href='<html:rewrite page="/exibirManterPagamentoAction.do"/>'">
						
						
						<input name="Button" type="button" class="bottonRightCol" value="Desfazer" align="left" onclick="window.location.href='<html:rewrite page="/exibirAtualizarPagamentosAction.do?desfazer=S&reloadPage=1"/>'">
						<input name="Button" type="button" class="bottonRightCol" value="Cancelar" align="left" onclick="window.location.href='/gsan/telaPrincipal.do'">
						</td>
						<td align="right">
						  <gsan:controleAcessoBotao name="Button" value="Atualizar" onclick="javascript:validarForm(document.forms[0]);" url="atualizarPagamentosAction.do" align="right"/>
						  <%-- <input name="Button" type="button" class="bottonRightCol" value="Atualizar" align="right" onClick="javascript:validarForm(document.forms[0]);"> --%>
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

<script language="JavaScript">
<!--
habilitacaoCampos();
//habilitacaoCampos(document.forms[0].idTipoDocumento);
//-->
</script>
</body>
</html:form>
</html:html>
