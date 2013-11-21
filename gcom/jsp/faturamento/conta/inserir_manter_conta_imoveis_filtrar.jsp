<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@page import="gcom.util.ConstantesSistema"%>
<head>
<html:html>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false"
	formName="FiltrarImovelContaActionForm" />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript">
<!-- Begin

function recuperarDadosQuatroParametros(idRegistro, descricaoRegistro, codigoRegistro, tipoConsulta) {

	var form = document.FiltrarImovelContaActionForm;

	if (tipoConsulta == 'setorComercialOrigem') {
      form.setorComercialOrigemCD.value = codigoRegistro;
      form.setorComercialOrigemID.value = idRegistro;
	  form.nomeSetorComercialOrigem.value = descricaoRegistro;
	  if(form.setorComercialDestinoCD.value == "")
	  {
	  	form.setorComercialDestinoCD.value = codigoRegistro;
	  }
	  if(form.nomeSetorComercialDestino.value == "")
	  {
	  	form.nomeSetorComercialDestino.value = descricaoRegistro;
	  	form.nomeSetorComercialDestino.style.color = "#000000";	  
	  }
	  
	  bloquearLSQLS();
	  form.nomeSetorComercialOrigem.style.color = "#000000";
	  form.quadraOrigemNM.focus();
	}

	if (tipoConsulta == 'setorComercialDestino') {
      form.setorComercialDestinoCD.value = codigoRegistro;
      form.setorComercialDestinoID.value = idRegistro;
	  form.nomeSetorComercialDestino.value = descricaoRegistro;
	  form.nomeSetorComercialDestino.style.color = "#000000"; 
	  if(form.setorComercialOrigemCD.value == "")
	  {
	  	form.setorComercialOrigemCD.value = codigoRegistro;
	  }
	  if(form.nomeSetorComercialOrigem.value == "")
	  {
	  	form.nomeSetorComercialOrigem.value = descricaoRegistro;
	  	form.nomeSetorComercialOrigem.style.color = "#000000";	  
	  }
	  
	  bloquearLSQLS();
	  form.quadraDestinoNM.focus();
	}

}
function replicaDados(campoOrigem, campoDestino)
{
	campoDestino.value = campoOrigem.value;
}

function replicaLote(campoOrigem, campoDestino)
{
	campoDestino.value = campoOrigem.value;
}
function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {

	var form = document.FiltrarImovelContaActionForm;

	if (tipoConsulta == 'localidadeOrigem') {
      form.localidadeOrigemID.value = codigoRegistro;
	  form.nomeLocalidadeOrigem.value = descricaoRegistro;
	  if(form.localidadeDestinoID.value == "")
	  {
	  	form.localidadeDestinoID.value = codigoRegistro;
	  }
	  if(form.nomeLocalidadeDestino.value == "")
	  {
	  	form.nomeLocalidadeDestino.value = descricaoRegistro;
	  }
	  
	  bloquearLSQLS();
	  form.nomeLocalidadeOrigem.style.color = "#000000";
	  form.setorComercialOrigemCD.focus();
	}

	if (tipoConsulta == 'localidade') {
      form.localidadeDestinoID.value = codigoRegistro;
      form.nomeLocalidadeDestino.value = descricaoRegistro;
	  form.nomeLocalidadeDestino.style.color = "#000000";	  
	  if(form.localidadeOrigemID.value == "")
	  {
	  	form.localidadeOrigemID.value = codigoRegistro;
	  }
	  if(form.nomeLocalidadeOrigem.value == "")
	  {
	  	form.nomeLocalidadeOrigem.value = descricaoRegistro;
	  }
	  
	  bloquearLSQLS();
	  form.setorComercialDestinoCD.focus();
	}
	
	if (tipoConsulta == 'cliente') {
		 if (form.tipoPesquisa.value != null && form.tipoPesquisa.value == 'clienteSuperior') {
	 		form.codigoClienteSuperior.value = codigoRegistro;
	        form.codigoClienteSuperiorClone.value = codigoRegistro;
    	    form.action = 'exibirFiltrarImovelInserirManterContaAction.do';
        	form.submit();
	 	} else{
			limparPesquisaCliente();
		    form.codigoCliente.value = codigoRegistro;
		    form.nomeCliente.value = descricaoRegistro;
		    form.nomeCliente.style.color = "#000000";
		    bloquearLSQLS();
        }
    }

}
function limparLocalidade(tipo) {
    var form = document.FiltrarImovelContaActionForm;
   	switch (tipo){
   		case 1:
	   		if (!form.localidadeOrigemID.readOnly) {
			    form.localidadeDestinoID.readOnly = false;
			}
	    	form.localidadeDestinoID.value = "";
		    form.nomeLocalidadeDestino.value = "";
	    	form.setorComercialDestinoCD.value = "";
		    form.setorComercialDestinoID.value = "";
		    form.nomeSetorComercialDestino.value = "";
		    form.quadraDestinoNM.value = "";
		    form.quadraDestinoID.value = "";
	    	form.loteDestino.value = "";
		    form.localidadeOrigemID.value = "";
		    form.nomeLocalidadeOrigem.value = "";
		    form.setorComercialOrigemCD.value = "";
		    form.setorComercialOrigemID.value = "";
		    form.nomeSetorComercialOrigem.value = "";
		    form.quadraOrigemNM.value = "";
		    form.quadraOrigemID.value = "";
		    form.loteOrigem.value = "";
  	    break;   
		case 2:
   			form.localidadeDestinoID.value = "";
		    form.nomeLocalidadeDestino.value = "";
		    form.setorComercialDestinoCD.value = "";
		    form.setorComercialDestinoID.value = "";
		    form.nomeSetorComercialDestino.value = "";
		    form.quadraDestinoNM.value = "";
		    form.quadraDestinoID.value = "";
		    form.loteDestino.value = "";
  	    break;   
   }
}

function limpar(tipo){
	var form = document.FiltrarImovelContaActionForm;
   
	switch (tipo){
	//savio
	
     case 1:
		   form.idImovel.value = "";
		   form.inscricaoImovel.value = "";
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
		   //savio
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
		   form.loteDestino.value = "";
		   form.subLoteDestino.value = "";
		   form.loteOrigem.value = "";
		   form.subLoteOrigem.value = "";
		   
		   form.codigoRotaOrigem.value = "";
		   form.codigoRotaDestino.value = "";
		   form.sequencialRotaOrigem.value = "";
		   form.sequencialRotaDestino.value = "";
	
		   form.idFaturamentoGrupo.value = "";
		   
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
		   form.subLoteOrigem.value = "";
		   //Coloca o foco no objeto selecionado
		   
		   limpar(13);
		   form.localidadeOrigemID.focus();
		   break;
		case 8:
		   form.quadraOrigemNM.value = "";
		   form.quadraOrigemID.value = "";

		   form.loteOrigem.value = "";
		   form.subLoteOrigem.value = "";
		   
		   limpar(14);

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
		   form.subLoteDestino.value = "";
		
		   //Coloca o foco no objeto selecionado
		   form.localidadeDestinoID.focus();
		   break;
		 case 10:
		   form.quadraDestinoNM.value = "";
		   form.quadraDestinoID.value = "";

		   
		   form.loteDestino.value = "";
		   form.subLoteDestino.value = "";

		   //Coloca o foco no objeto selecionado
		   form.setorComercialDestinoCD.focus();
		   break;
		   
		 case 11:
		   
		   form.codigoCliente.value = "";
		   
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
		   form.loteDestino.value = "";
		   form.subLoteDestino.value = "";
		   form.loteOrigem.value = "";
		   form.subLoteOrigem.value = "";
		   
		   form.codigoRotaOrigem.value = "";
		   form.codigoRotaDestino.value = "";
		   form.sequencialRotaOrigem.value = "";
		   form.sequencialRotaDestino.value = "";
		   
		   break;
		   
		case 12:
		   form.loteOrigem.value = "";
		   form.subLoteOrigem.value = "";

		   //Coloca o foco no objeto selecionado
		   form.quadraOrigemNM.focus();
		   break; 
		   
		case 13:
		   form.setorComercialDestinoCD.value = "";
		   form.setorComercialDestinoID.value = "";
		   form.nomeSetorComercialDestino.value = "";
		   form.quadraDestinoNM.value = "";
		   form.quadraDestinoID.value = "";

		   		   
		   form.loteDestino.value = "";
		   form.subLoteDestino.value = "";
		
		   break;
		   
		case 14:
		   form.quadraDestinoNM.value = "";
		   form.quadraDestinoID.value = "";

		   
		   form.loteDestino.value = "";
		   form.subLoteDestino.value = "";

		   break;
		   
		case 15:
			form.sequencialRotaOrigem.value = "";
			
			limpar(16)
			form.codigoRotaOrigem.focus();
			
			break;
			
		case 16:
			form.sequencialRotaDestino.value = "";
			
			break;
			
		case 17:
			form.sequencialRotaDestino.value = "";
			
			form.codigoRotaDestino.focus();
			
			break;
			
		case 18:
		   form.codigoCliente.value = "";
		   form.idFaturamentoGrupo.value = "";
		   
		   break;
	   default:
          break;
	}
}

function habilitarPesquisaCliente(form) {
	if (form.codigoCliente.readOnly == false) {
		chamarPopup('exibirPesquisarClienteAction.do', 'cliente', null, null, 275, 480, '',form.codigoCliente.value);
	}	
}

function chamarPopup(url, tipo, objeto, codigoObjeto, altura, largura, msg,objetoRelacionado){

	if (objeto == null || codigoObjeto == null){
		abrirPopup(url + "?" + "tipo=" + tipo, altura, largura);
	}
	else{
		if (codigoObjeto.length < 1 || isNaN(codigoObjeto)){
			alert(msg);
		}
		else{
			abrirPopup(url + "?" + "tipo=" + tipo + "&" + objeto + "=" + codigoObjeto , altura, largura);
		}
	}
}

  function limparPesquisaCliente() {
    var form = document.forms[0];

      form.codigoCliente.value = "";
      form.nomeCliente.value = "";
      form.tipoRelacao.value = -1;
      bloquearLSQLS();
  }
  
  
function validarForm(form){

	var msg = "";
 	var foco = 0;
 	
 	if(form.banco.value != '' && form.banco.value != '-1'){
 		form.submit(); 	
 	}else{
		msg = msg + "\Informe Banco de Débito Automático";
		if(form.codigoCliente.value == "" && form.codigoClienteSuperior.value == ""){
	  	  
		  // Campos relacionados a rota
		  var rotaInicial = form.codigoRotaOrigem;
		  var seqRotaInicial = form.sequencialRotaOrigem;
		  var rotaFinal = form.codigoRotaDestino;
		  var seqRotaFinal = form.sequencialRotaDestino;
		  
		  var grupoFaturamento = form.idFaturamentoGrupo; 
		  
		  if(form.localidadeOrigemID.value != "" && form.localidadeDestinoID.value != ""){
		    
		    var obrigatorio = validarInscricao(form);
		    
		    if (!obrigatorio){
		    
		    	if(form.codigoRotaOrigem.value != "" || form.codigoRotaDestino.value != "" ||
		    	   form.sequencialRotaOrigem.value != "" || form.sequencialRotaDestino.value != ""){
		    		
		    		
		    		
		    		if (form.codigoRotaOrigem.value == ""){
		  				msg = msg + "\Informe Rota inicial";	
		  				foco = 1;
		  				obrigatorio = true;
		  			}
		  	
		  			if (form.codigoRotaDestino.value == ""){
		  				msg = msg + "\n Informe Rota final";
		  				
		  				if (foco == 0){
		  					foco = 2;
		  				}	
		  				
		  				obrigatorio = true;
		  			}
		    		
		    		if (obrigatorio){
		    			
		    			alert(msg);
		  		
				  		switch (foco) { 
			    			case 1: 
			       				form.codigoRotaOrigem.focus();
			       				break; 
			    			case 2: 
			       				form.codigoRotaDestino.focus();
			       				break; 
			    			case 3: 
			       				form.setorComercialOrigemCD.focus();
			       				break; 
			    			default: 
			       				form.setorComercialDestinoCD.focus();
			       				break;
						}
		    		}
		    		else if (!obrigatorio && (form.sequencialRotaOrigem.value != "" || form.sequencialRotaDestino.value != "")){
		    		
		    			obrigatorio = validarSeqRota(form);
		    			
		    			if (!obrigatorio && validateFiltrarImovelContaActionForm(form)){	
			  				form.submit();
		    			}
		    		}
		    		else if(!obrigatorio && validateFiltrarImovelContaActionForm(form)){
		    			form.submit();
		    		}
		    	}
		    	else if (validateFiltrarImovelContaActionForm(form)){
		    		form.submit();
		    	}
		    }
		    
		  }
		  else if (grupoFaturamento.value != "" && validateFiltrarImovelContaActionForm(form)){
		  	form.submit();
		  }
		  else if(form.codigoRotaOrigem.value != "" && form.codigoRotaDestino.value != ""){
		  	
		  	var msg = "";
		  	var foco = 0;
		  	var obrigatorio = false;
		  	
		  	if (form.localidadeOrigemID.value == ""){
		  		msg = "Informe Localidade da inscrição inicial";	
		  		foco = 1;
		  		obrigatorio = true;
		  	}
		  	
		  	if (form.localidadeDestinoID.value == ""){
		  		msg = msg + "\nInforme Localidade da inscrição final";	
		  		
		  		if (foco == 0){
		  			foco = 2;
		  		}
		  		obrigatorio = true;
		  	}
		  	
		  	if (form.setorComercialOrigemCD.value == ""){
		  		msg = msg +  "\nInforme Setor Comercial da inscrição inicial";	
		  		
		  		if (foco == 0){
		  			foco = 3;
		  		}
		  		obrigatorio = true;
		  	}
		  	
		  	if (form.setorComercialDestinoCD.value == ""){
		  		msg = msg + "\nInforme Setor Comercial da inscrição final";	
		  		
		  		if (foco == 0){
		  			foco = 4;
		  		}
		  		obrigatorio = true;
		  	}
		  	
		  	if (!obrigatorio){
		  		obrigatorio = validarSeqRota();
		  	}
		  	else{
		  		alert(msg);
		  		
		  		switch (foco) { 
	    			case 1: 
	       				form.localidadeOrigemID.focus();
	       				break; 
	    			case 2: 
	       				form.localidadeDestinoID.focus();
	       				break; 
	    			case 3: 
	       				form.setorComercialOrigemCD.focus();
	       				break; 
	    			default: 
	       				form.setorComercialDestinoCD.focus();
	       				break;
				}
		  	}
		  	
		  	if (!obrigatorio && validateFiltrarImovelContaActionForm(form)){	
			  form.submit();
		    }
		    
		  }
		  else{
		   alert(" Informe Código do Cliente ou \n Informe Localidade da inscrição inicial e\n Informe Localidade da inscrição final ou\n Informe Grupo de Faturamento ou\n Informe Rota Inicial e\n Informe Rota Final"); 
		  }
		}else{
		  	// Confirma a validação do formulário
			  if (validateFiltrarImovelContaActionForm(form)){
				form.submit();
		  	  }
		  
		}
	}
}

function validarSeqRota(form){

	var seqRotaInicial = form.sequencialRotaOrigem;
	var seqRotaFinal = form.sequencialRotaDestino;	

	var obrigatorio = false;
	  	
	if (seqRotaInicial.value != ""){
		obrigatorio = campoObrigatorio(seqRotaFinal, seqRotaInicial, "Informe Seq. da Rota final");
	}
	  	
	if (seqRotaFinal.value != ""){
		obrigatorio = campoObrigatorio(seqRotaInicial, seqRotaFinal, "Informe Seq. da Rota inicial");
	}
	
	return obrigatorio;
}

function validarRota(){

	var rotaInicial = form.codigoRotaOrigem;
	var rotaFinal = form.codigoRotaDestino;

	var obrigatorio = false;
	  	
	if (rotaInicial.value != ""){
		obrigatorio = campoObrigatorio(rotaFinal, rotaInicial, "Informe Rota final");
	}
	
	if (rotaFinal.value != ""){
		obrigatorio = campoObrigatorio(rotaInicial, rotaFinal, "Informe Rota inicial");
	}
	  	
	return obrigatorio;
}

function validarInscricao(form){

	// Campos relacionados a inscrição de origem
   	var localidadeOrigem = form.localidadeOrigemID;
   	var setorComercialOrigem = form.setorComercialOrigemCD;
   	var quadraOrigem = form.quadraOrigemNM;
   	var loteOrigem = form.loteOrigem;

   	// Campos relacionados a inscrição de destino
  	var localidadeDestino = form.localidadeDestinoID;
   	var setorComercialDestino = form.setorComercialDestinoCD;
   	var quadraDestino = form.quadraDestinoNM;
   	var loteDestino = form.loteDestino;
	  
	
	var obrigatorio = true;

	obrigatorio = campoObrigatorio(setorComercialOrigem, quadraOrigem, "Informe Setor Comercial da inscrição inicial");
	if (!obrigatorio){
		obrigatorio = campoObrigatorio(quadraOrigem, loteOrigem, "Informe Quadra da inscrição inicial");
	}

	//Destino
	if (!obrigatorio){
		obrigatorio = campoObrigatorio(setorComercialDestino, quadraDestino, "Informe Setor Comercial da inscrição final");
		if (!obrigatorio){
			obrigatorio = campoObrigatorio(quadraDestino, loteDestino, "Informe Quadra da inscrição final");
		}
	}

	//Origem - Destino
	if (!obrigatorio){
		obrigatorio = campoObrigatorio(setorComercialDestino, setorComercialOrigem, "Informe Setor Comercial da inscrição final");
		if (!obrigatorio){
			obrigatorio = campoObrigatorio(quadraDestino, quadraOrigem, "Informe Quadra da inscrição final");
			if (!obrigatorio){
				obrigatorio = campoObrigatorio(loteDestino, loteOrigem, "Informe Lote da inscrição final");
			}
		}
	}
	
	//Destino - Origem
	if (!obrigatorio){
		obrigatorio = campoObrigatorio(setorComercialOrigem, setorComercialDestino, "Informe Setor Comercial da inscrição inicial");
		if (!obrigatorio){
			obrigatorio = campoObrigatorio(quadraOrigem, quadraDestino, "Informe Quadra da inscrição inicial");
			if (!obrigatorio){
				obrigatorio = campoObrigatorio(loteOrigem, loteDestino, "Informe Lote da inscrição inicial");
			}
		}
	}
	
	return obrigatorio;
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

function bloquearLSQLS(){
	var form = document.FiltrarImovelContaActionForm;
	if(form.codigoCliente.value != "" ){
		
		limpar(3);
		
		form.codigoClienteSuperior.readOnly = true;
				
		form.localidadeOrigemID.readOnly = true;
		form.setorComercialOrigemCD.readOnly = true;
		form.quadraOrigemNM.readOnly = true;
		form.loteOrigem.readOnly = true;
		form.subLoteOrigem.readOnly = true;
		
		form.localidadeDestinoID.readOnly = true;
		form.setorComercialDestinoCD.readOnly = true;
		form.quadraDestinoNM.readOnly = true;
		form.loteDestino.readOnly = true;
		form.subLoteDestino.readOnly = true;
		
		form.codigoRotaOrigem.readOnly = true;
		form.codigoRotaDestino.readOnly = true;
		form.sequencialRotaOrigem.readOnly = true;
		form.sequencialRotaDestino.readOnly = true;
		
		form.idFaturamentoGrupo.readOnly = true;
		form.esferaPoder.disabled = true;
				
	}
	else if (form.idFaturamentoGrupo.value != ""){
	
		limpar(11);
		
		form.codigoClienteSuperior.readOnly = true;
				
		form.codigoCliente.readOnly = true;
		
		form.tipoRelacao.options[0].selected = true;
		form.tipoRelacao.disabled = true;
		
		form.localidadeOrigemID.readOnly = true;
		form.setorComercialOrigemCD.readOnly = true;
		form.quadraOrigemNM.readOnly = true;
		form.loteOrigem.readOnly = true;
		form.subLoteOrigem.readOnly = true;
		
		form.localidadeDestinoID.readOnly = true;
		form.setorComercialDestinoCD.readOnly = true;
		form.quadraDestinoNM.readOnly = true;
		form.loteDestino.readOnly = true;
		form.subLoteDestino.readOnly = true;
		
		form.codigoRotaOrigem.readOnly = true;
		form.codigoRotaDestino.readOnly = true;
		form.sequencialRotaOrigem.readOnly = true;
		form.sequencialRotaDestino.readOnly = true;
		form.esferaPoder.disabled = true;
	}
	else if (form.codigoRotaOrigem.value != "" || form.codigoRotaDestino.value != "" ||
			form.sequencialRotaOrigem.value != "" || form.sequencialRotaDestino.value != ""){
	
		limpar(18);
		
		form.codigoClienteSuperior.readOnly = true;
		
		form.codigoCliente.readOnly = true;
		
		form.tipoRelacao.options[0].selected = true;
		form.tipoRelacao.disabled = true;
		
		form.idFaturamentoGrupo.readOnly = true;
	}else if(form.codigoClienteSuperior.value != ""){
		limpar(3);
		
		form.codigoCliente.readOnly = true;		
		form.tipoRelacao.options[0].selected = true;
		form.tipoRelacao.disabled = true;
		
		form.localidadeOrigemID.readOnly = true;
		form.setorComercialOrigemCD.readOnly = true;
		form.quadraOrigemNM.readOnly = true;
		form.loteOrigem.readOnly = true;
		form.subLoteOrigem.readOnly = true;
		
		form.localidadeDestinoID.readOnly = true;
		form.setorComercialDestinoCD.readOnly = true;
		form.quadraDestinoNM.readOnly = true;
		form.loteDestino.readOnly = true;
		form.subLoteDestino.readOnly = true;
		
		form.codigoRotaOrigem.readOnly = true;
		form.codigoRotaDestino.readOnly = true;
		form.sequencialRotaOrigem.readOnly = true;
		form.sequencialRotaDestino.readOnly = true;
		
		form.idFaturamentoGrupo.readOnly = true;	
		form.esferaPoder.disabled = true;
	}
	else{
	
		form.codigoClienteSuperior.readOnly = false;
		form.codigoCliente.readOnly = false;
		form.tipoRelacao.options[0].selected = false;
		form.tipoRelacao.disabled = false;
		
		form.localidadeOrigemID.readOnly = false;
		form.setorComercialOrigemCD.readOnly = false;
		form.quadraOrigemNM.readOnly = false;
		form.loteOrigem.readOnly = false;
		form.subLoteOrigem.readOnly = false;
		
		form.localidadeDestinoID.readOnly = false;
		form.setorComercialDestinoCD.readOnly = false;
		form.quadraDestinoNM.readOnly = false;
		form.loteDestino.readOnly = false;
		form.subLoteDestino.readOnly = false;
		
		form.codigoRotaOrigem.readOnly = false;
		form.codigoRotaDestino.readOnly = false;
		form.sequencialRotaOrigem.readOnly = false;
		form.sequencialRotaDestino.readOnly = false;
		
		form.idFaturamentoGrupo.readOnly = false;	
		form.esferaPoder.disabled = false;
	}
}

  function bloquearLocalidadeDestino(){
    var form = document.FiltrarImovelContaActionForm;
      if(form.localidadeOrigemID.value.length > 0){
        form.localidadeDestinoID.readOnly = true;
      }else{
        form.localidadeDestinoID.readOnly = false;
      }
  }
  
  function verificarBotao(){
    var form = document.FiltrarImovelContaActionForm;
  	if(form.quadraOrigemNM.value != "" && form.quadraDestinoNM.value != ""){
  		form.quadra.disabled = true;
  	}else if(form.localidadeOrigemID.value != "" && form.localidadeOrigemID.value != "" &&
  		form.setorComercialOrigemCD.value != "" && form.setorComercialDestinoCD.value != "" && 
  		form.setorComercialOrigemCD.value == form.setorComercialDestinoCD.value){
  		form.quadra.disabled = false;
  	}else{
  		form.quadra.disabled = true;
  	}
  }
  
  function chmarPopupQuadra() {
  	var form = document.FiltrarImovelContaActionForm;
     if(form.localidadeOrigemID.value == "" && form.localidadeDestinoID.value == ""){
		alert(" Informe Localidade ");      
     }else if(form.setorComercialOrigemCD.value == "" && form.setorComercialDestinoCD.value == ""){
     	alert(" Informe SetorComercial "); 
     }else{
	    abrirPopup('exibirSelecionarQuadraImovelInserirManterContaAction.do?idLocalidade='+form.localidadeOrigemID.value+'&codigoSetorComercial='+form.setorComercialOrigemCD.value, 275, 480);	
	}
  }
  
	function habilitarPesquisaClienteSuperior(form) {
		if (form.codigoClienteSuperior.readOnly == false) {
			//form.tipoPesquisa.value = 'clienteSuperior';
			chamarPopup('exibirPesquisarClienteAction.do', 'clienteSuperior', null, null, 275, 480, '',form.codigoClienteSuperior.value);
		}
	}  
    
    function limparPesquisaClienteSuperior() {
    var form = document.forms[0];

      form.codigoClienteSuperior.value = "";
      form.nomeClienteSuperior.value = "";
      bloquearLSQLS();
  }    
  
  function habilitaCamposBanco(){
  	var form = document.forms[0];
  	  if(form.banco.value == '-1'){
  		form.codigoClienteSuperior.readOnly = false;
		form.codigoCliente.readOnly = false;
		form.tipoRelacao.options[0].selected = false;
		form.tipoRelacao.disabled = false;
		
		form.localidadeOrigemID.readOnly = false;
		form.setorComercialOrigemCD.readOnly = false;
		form.quadraOrigemNM.readOnly = false;
		form.loteOrigem.readOnly = false;
		form.subLoteOrigem.readOnly = false;
		
		form.localidadeDestinoID.readOnly = false;
		form.setorComercialDestinoCD.readOnly = false;
		form.quadraDestinoNM.readOnly = false;
		form.loteDestino.readOnly = false;
		form.subLoteDestino.readOnly = false;
		
		form.codigoRotaOrigem.readOnly = false;
		form.codigoRotaDestino.readOnly = false;
		form.sequencialRotaOrigem.readOnly = false;
		form.sequencialRotaDestino.readOnly = false;
		
		form.idFaturamentoGrupo.readOnly = false;
		
	}else{
		form.codigoClienteSuperior.readOnly = true;
		form.codigoCliente.readOnly = true;
		form.tipoRelacao.options[0].selected = true;
		form.tipoRelacao.disabled = true;
		
		form.localidadeOrigemID.readOnly = true;
		form.setorComercialOrigemCD.readOnly = true;
		form.quadraOrigemNM.readOnly = true;
		form.loteOrigem.readOnly = true;
		form.subLoteOrigem.readOnly = true;
		
		form.localidadeDestinoID.readOnly = true;
		form.setorComercialDestinoCD.readOnly = true;
		form.quadraDestinoNM.readOnly = true;
		form.loteDestino.readOnly = true;
		form.subLoteDestino.readOnly = true;
		
		form.codigoRotaOrigem.readOnly = true;
		form.codigoRotaDestino.readOnly = true;
		form.sequencialRotaOrigem.readOnly = true;
		form.sequencialRotaDestino.readOnly = true;
		
		form.idFaturamentoGrupo.readOnly = true;
		
		form.codigoClienteSuperior.value = "";
	    form.nomeClienteSuperior.value = "";
	    form.codigoCliente.value = "";
        form.nomeCliente.value = "";
        form.tipoRelacao.value = -1;
		form.codigoCliente.value = "";
		   
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
		   form.loteDestino.value = "";
		   form.subLoteDestino.value = "";
		   form.loteOrigem.value = "";
		   form.subLoteOrigem.value = "";
		   
		   form.codigoRotaOrigem.value = "";
		   form.codigoRotaDestino.value = "";
		   form.sequencialRotaOrigem.value = "";
		   form.sequencialRotaDestino.value = "";
	}
  }
-->
</script>
</head>

<body leftmargin="5" topmargin="5"
	onload="bloquearLSQLS();verificarBotao();">
<html:form action="/filtrarImovelInserirManterContaAction"
	name="FiltrarImovelContaActionForm"
	type="gcom.gui.faturamento.conta.FiltrarImovelContaActionForm"
	method="post"
	onsubmit="return validateFiltrarImovelContaActionForm(this);"
	focus="codigoClienteSuperior">

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
			<p>&nbsp;</p>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img border="0" src="imagens/parahead_left.gif" /></td>
					<td class="parabg">Filtrar Imóveis para Inserir ou Manter Conta</td>
					<td width="11" valign="top"><img border="0"
						src="imagens/parahead_right.gif" /></td>
				</tr>
			</table>
			<!--Fim Tabela Reference a Páginação da Tela de Processo-->
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>
					<td width="30%"><strong>Código do Cliente Superior:</strong></td>
					<td colspan="3">
					<html:text property="codigoClienteSuperior"
						maxlength="9" 
						size="9"
						onkeypress="validaEnterComMensagem(event, 'exibirFiltrarImovelInserirManterContaAction.do', 'codigoClienteSuperior', 'Código do Cliente Superior');return isCampoNumerico(event); "
						onkeyup="bloquearLSQLS();" /> 
							<a href="javascript:habilitarPesquisaClienteSuperior(document.forms[0]);"
								alt="Pesquisar Cliente Superior"> <img width="23" height="21"
								src="<bean:message key='caminho.imagens'/>pesquisa.gif" border="0" title="Pesquisar Cliente Superior"/></a>
					<logic:present name="corClienteSuperior">
						<logic:equal name="corClienteSuperior" value="exception">
							<html:text property="nomeClienteSuperior" 
									   size="38"
								       readonly="true"
								       style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:equal>
						<logic:notEqual name="corClienteSuperior" value="exception">
							<html:text property="nomeClienteSuperior" 
								       size="38"
									   readonly="true"
									   style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEqual>
					</logic:present> 
					<logic:notPresent name="corClienteSuperior">
						<logic:empty name="FiltrarImovelContaActionForm" property="codigoClienteSuperior">
							<html:text property="nomeClienteSuperior" 
								       size="38" 
								       value=""
									   readonly="true"
									   style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:empty>
						<logic:notEmpty name="FiltrarImovelContaActionForm" property="codigoClienteSuperior">
							<html:text property="nomeClienteSuperior" 
									   size="38"
									   readonly="true"
									   style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEmpty>
					</logic:notPresent> 
						<a href="javascript:limparPesquisaClienteSuperior();"> 
							<img border="0" src="<bean:message key='caminho.imagens'/>limparcampo.gif"
								 style="cursor:pointer; cursor:hand;" title="Apagar" /> </a>
					</td>
				</tr>
				<tr>
					<td width="125"><strong>Código do Cliente: </strong></td>
					<td colspan="3">
					<html:text  property="codigoCliente" 
								maxlength="9"
								size="9"
								onkeypress="validaEnterComMensagem(event, 'exibirFiltrarImovelInserirManterContaAction.do','codigoCliente','Cliente');return isCampoNumerico(event);"
								onkeyup="bloquearLSQLS();" /> 
									<a href="javascript:abrirPopup('exibirPesquisarClienteAction.do?indicadorUsoTodos=1&limparForm=OK', 400, 800);"
									   alt="Pesquisar Cliente"> 
									   <img width="23" height="21" 
									   		src="<bean:message key='caminho.imagens'/>pesquisa.gif" 
									   		border="0" title="Pesquisar Cliente"/>
									</a>
					<logic:present name="corCliente">
						<logic:equal name="corCliente" value="exception">
							<html:text property="nomeCliente" size="38" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:equal>
						<logic:notEqual name="corCliente" value="exception">
							<html:text property="nomeCliente" size="38" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEqual>
					</logic:present> <logic:notPresent name="corCliente">
						<logic:empty name="FiltrarImovelContaActionForm"
							property="codigoCliente">
							<html:text property="nomeCliente" size="38" value=""
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:empty>
						<logic:notEmpty name="FiltrarImovelContaActionForm"
							property="codigoCliente">
							<html:text property="nomeCliente" size="38" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEmpty>
					</logic:notPresent> 
					<a href="javascript:limparPesquisaCliente();">
						<img border="0"
							src="<bean:message key='caminho.imagens'/>limparcampo.gif"
							style="cursor:pointer;cursor:hand;" title="Apagar"/> 
					</a>
				</td>
				</tr>
				<tr>
					<td><strong>Tipo da Relação:</strong></td>
					<td colspan="3" align="right">
					<div align="left"><strong> <html:select property="tipoRelacao">
						<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
							<logic:notEmpty name="collectionClienteRelacaoTipo">
								<html:options collection="collectionClienteRelacaoTipo"
											  labelProperty="descricao" 
											  property="id" />
							</logic:notEmpty>
						</html:select></strong>
					</div>
					</td>
				</tr>
				<tr>
					<td colspan="4">
					<hr>
					</td>
				</tr>
				<tr>
					<td height="22" colspan="4">Informe os dados da inscrição inicial:</td>
				</tr>
				<tr>
					<td><strong>Localidade:</strong></td>
					<td colspan="3">
						<html:text maxlength="3"
							property="localidadeOrigemID" size="3"
							onkeypress="limpar(7);validaEnterComMensagem(event, 'exibirFiltrarImovelInserirManterContaAction.do?objetoConsulta=1&inscricaoTipo=origem','localidadeOrigemID','Código da localidade de origem');return isCampoNumerico(event);"
							onkeyup="replicaDados(document.forms[0].localidadeOrigemID, document.forms[0].localidadeDestinoID);verificarBotao();"
							tabindex="1" /> 
							<a href="javascript:chamarPopup('exibirPesquisarLocalidadeAction.do?indicadorUsoTodos=1', 'origem', null, null, 275, 480, '');"><img
							   src="<bean:message key="caminho.imagens"/>pesquisa.gif" border="0"
							   width="23" height="21" title="Pesquisar Localidade">
							</a> 
						<logic:present name="corLocalidadeOrigem">

							<logic:equal name="corLocalidadeOrigem" value="exception">
								<html:text property="nomeLocalidadeOrigem" size="45"
									readonly="true"
									style="background-color:#EFEFEF; border:0; color: #ff0000" />
							</logic:equal>
	
							<logic:notEqual name="corLocalidadeOrigem" value="exception">
								<html:text property="nomeLocalidadeOrigem" size="45"
									readonly="true"
									style="background-color:#EFEFEF; border:0; color: #000000" />
							</logic:notEqual>

						</logic:present>
						<logic:notPresent name="corLocalidadeOrigem">

						<logic:empty name="FiltrarImovelContaActionForm"
							property="localidadeOrigemID">
							<html:text property="nomeLocalidadeOrigem" value="" size="45"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:empty>
						<logic:notEmpty name="FiltrarImovelContaActionForm"
							property="localidadeOrigemID">
							<html:text property="nomeLocalidadeOrigem" size="45"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: 	#000000" />
						</logic:notEmpty>
					</logic:notPresent> 
						<a href="javascript:limparLocalidade(1);verificarBotao();"> 
							<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
								 border="0" title="Apagar" /> 
						</a>
				</td>
				</tr>
				<tr>
					<td><strong>Setor Comercial :</strong></td>
					<td colspan="3">
						<html:text  maxlength="3"
									property="setorComercialOrigemCD" 
									size="3"
									onkeypress="limpar(8); validaEnterDependenciaComMensagem(event, 'exibirFiltrarImovelInserirManterContaAction.do?objetoConsulta=2&inscricaoTipo=origem', document.forms[0].setorComercialOrigemCD, document.forms[0].localidadeOrigemID.value, 'localidade da inscrição inicial', 'Setor comercial inicial');return isCampoNumerico(event);"
									onkeyup="replicaDados(document.forms[0].setorComercialOrigemCD, document.forms[0].setorComercialDestinoCD);verificarBotao();"
									tabindex="2" /> 
							<a href="javascript:chamarPopup('exibirPesquisarSetorComercialAction.do', 'setorComercialOrigem', 'idLocalidade', document.FiltrarImovelContaActionForm.localidadeOrigemID.value, 275, 480, 'Informe a Localidade da inscrição inicial');">
								<img src="<bean:message key="caminho.imagens"/>pesquisa.gif" border="0"
									 width="23" height="21" title="Pesquisar Setor Comercial">
							</a> 
						<logic:present name="corSetorComercialOrigem">

							<logic:equal name="corSetorComercialOrigem" value="exception">
								<html:text property="nomeSetorComercialOrigem" size="45"
									readonly="true"
									style="background-color:#EFEFEF; border:0; color: #ff0000" />
							</logic:equal>
	
							<logic:notEqual name="corSetorComercialOrigem" value="exception">
								<html:text property="nomeSetorComercialOrigem" size="45"
									readonly="true"
									style="background-color:#EFEFEF; border:0; color: #000000" />
							</logic:notEqual>

						</logic:present> 
						<logic:notPresent name="corSetorComercialOrigem">

						<logic:empty name="FiltrarImovelContaActionForm"
							property="setorComercialOrigemCD">
							<html:text property="nomeSetorComercialOrigem" value="" size="45"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:empty>
						<logic:notEmpty name="FiltrarImovelContaActionForm"
							property="setorComercialOrigemCD">
							<html:text property="nomeSetorComercialOrigem" size="45"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEmpty>

					</logic:notPresent> <a
						href="javascript:limpar(2);verificarBotao();"> <img
						src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar" /> </a> <html:hidden
						property="setorComercialOrigemID" /></td>
				</tr>
				<tr>
				<tr>
					<td><strong>Quadra:</strong></td>
					<td colspan="2">
						<html:text maxlength="4" 
								   property="quadraOrigemNM"
								   size="4" 
								   tabindex="3" 
								   onkeypress="return isCampoNumerico(event);limpar(12);"
								   onkeyup="replicaDados(document.forms[0].quadraOrigemNM, document.forms[0].quadraDestinoNM);verificarBotao();" />
						<html:hidden property="quadraOrigemID" />
					</td>
					<td align="right" class="style1">
						<input name="quadra"
							   class="bottonRightCol" 
							   value="Quadra" 
							   type="button"
							   onclick="chmarPopupQuadra();" 
							   onkeyup="bloqueraBotaoQuadra();">
			&nbsp; </td>
				</tr>
				<tr>
					<td><strong>Lote:</strong></td>
					<td colspan="3">
					<html:text maxlength="4" 
						       property="loteOrigem"
						       size="4" 
						       tabindex="4"
						       onkeypress="return isCampoNumerico(event);"
							   onkeyup="replicaDados(document.forms[0].loteOrigem, document.forms[0].loteDestino);" />
					</td>
				</tr>
				<tr>
					<td height="24"><strong>Sublote:</strong></td>
					<td colspan="3">
					<html:text maxlength="3" 
							   property="subLoteOrigem"
							   size="4" 
							   tabindex="5"
							   onkeypress="return isCampoNumerico(event);"
							   onkeyup="replicaDados(document.forms[0].subLoteOrigem, document.forms[0].subLoteDestino);" />
					</td>
				</tr>
				<tr>
					<td colspan="4">
					<hr>
					</td>
				</tr>
				<tr>
					<td height="22" colspan="4">Informe os dados da inscrição final:</td>
				</tr>
				<tr>
					<td><strong>Localidade:</strong></td>
					<td colspan="3" height="24">
					<html:text  maxlength="3"
								property="localidadeDestinoID" 
								size="3" 
								tabindex="6"
								onkeypress="return isCampoNumerico(event);limpar(13);" /> 
								<a><img src="<bean:message key="caminho.imagens"/>pesquisa.gif" 
									    border="0"
										width="23" 
										height="21" 
										title="Pesquisar Localidade"
										style="cursor:pointer;cursor:hand;">
								</a> 
					<logic:present name="corLocalidadeDestino">

						<logic:equal name="corLocalidadeDestino" value="exception">
							<html:text property="nomeLocalidadeDestino" size="45"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:equal>

						<logic:notEqual name="corLocalidadeDestino" value="exception">
							<html:text property="nomeLocalidadeDestino" size="45"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEqual>

					</logic:present> 
					<logic:notPresent name="corLocalidadeDestino">

						<logic:empty name="FiltrarImovelContaActionForm"
							property="localidadeDestinoID">
							<html:text property="nomeLocalidadeDestino" value="" size="45"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:empty>
						<logic:notEmpty name="FiltrarImovelContaActionForm"
							property="localidadeDestinoID">
							<html:text property="nomeLocalidadeDestino" size="45"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: 	#000000" />
						</logic:notEmpty>
					</logic:notPresent> 
					<a> <img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
							 border="0" 
							 title="Apagar" /> 
					</a>
					</td>
				</tr>
				<tr>
					<td><strong>Setor Comercial :</strong></td>
					<td colspan="3">
					<html:text  maxlength="3"
								property="setorComercialDestinoCD" size="3"
								onkeyup="verificarBotao();"
								onkeypress="limpar(10); validaEnterDependenciaComMensagem(event, 'exibirFiltrarImovelInserirManterContaAction.do?objetoConsulta=2&inscricaoTipo=destino', document.forms[0].setorComercialDestinoCD, document.forms[0].localidadeDestinoID.value, 'localidade da inscrição final', 'Setor comercial final');return isCampoNumerico(event);"
								tabindex="7" /> 
						<a href="javascript:chamarPopup('exibirPesquisarSetorComercialAction.do', 'setorComercialDestino', 'idLocalidade', document.FiltrarImovelContaActionForm.localidadeDestinoID.value, 275, 480, 'Informe a Localidade da inscrição final');"><img
						   src="<bean:message key="caminho.imagens"/>pesquisa.gif" 
						   border="0"
						   width="23" 
						   height="21" 
						   title="Pesquisar Setor Comercial">
						</a> 
						<logic:present name="corSetorComercialDestino">

							<logic:equal name="corSetorComercialDestino" value="exception">
								<html:text property="nomeSetorComercialDestino" size="45"
									readonly="true"
									style="background-color:#EFEFEF; border:0; color: #ff0000" />
							</logic:equal>
	
							<logic:notEqual name="corSetorComercialDestino" value="exception">
								<html:text property="nomeSetorComercialDestino" size="45"
									readonly="true"
									style="background-color:#EFEFEF; border:0; color: #000000" />
							</logic:notEqual>

						</logic:present> 
						<logic:notPresent name="corSetorComercialDestino">
	
							<logic:empty name="FiltrarImovelContaActionForm"
								property="setorComercialDestinoCD">
								<html:text property="nomeSetorComercialDestino" value=""
									size="45" readonly="true"
									style="background-color:#EFEFEF; border:0; color: #000000" />
							</logic:empty>
							<logic:notEmpty name="FiltrarImovelContaActionForm"
								property="setorComercialDestinoCD">
								<html:text property="nomeSetorComercialDestino" size="45"
									readonly="true"
									style="background-color:#EFEFEF; border:0; color: #000000" />
							</logic:notEmpty>
	
						</logic:notPresent> 
							<a href="javascript:limpar(4);verificarBotao();"> 
								<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
									 border="0" title="Apagar" /> 
							</a> <html:hidden property="setorComercialDestinoID" />
					</td>
				</tr>
				<tr>
					<td><strong>Quadra:</strong></td>
					<td colspan="3">
					<html:text maxlength="4" 
							   property="quadraDestinoNM"
							   size="4" 
							   tabindex="8" 
							   onkeypress="return isCampoNumerico(event);"/> 
						<html:hidden property="quadraDestinoID" onkeyup="verificarBotao();" />
					</td>
				</tr>
				<tr>
					<td><strong>Lote:</strong></td>
					<td colspan="3">
						<html:text maxlength="4" 
								   property="loteDestino"
								   size="4" 
								   tabindex="9" 
								   onkeypress="return isCampoNumerico(event);"/>
					</td>
				</tr>
				<tr>
					<td height="24"><strong>Sublote:</strong></td>
					<td colspan="3">
						<html:text maxlength="3" 
								   property="subLoteDestino"
								   size="4" 
								   tabindex="10" 
								   onkeypress="return isCampoNumerico(event);"/></td>
				</tr>


				<!-- GRUPO FATURAMENTO -->

				<tr>
					<td colspan="4" HEIGHT="15"></td>
				</tr>
				<tr>
					<td colspan="4">

					<table width="100%" align="center" bgcolor="#99CCFF" border="0">
						<tr>
							<td><strong>Informe os dados do Grupo de Faturamento:</strong></td>
						</tr>
						<tr bgcolor="#cbe5fe">
							<td width="100%" align="center">

							<table width="100%" border="0">
								<tr>
									<td height="10" width="120"><strong>Grupo Faturamento:</strong></td>
									<td><html:text maxlength="3" 
												   property="idFaturamentoGrupo"
												   size="5" 
												   tabindex="11" 
												   onkeypress="return isCampoNumerico(event);"
												   onkeyup="bloquearLSQLS();" />
									</td>
								</tr>
							</table>

							</td>
						</tr>
					</table>

					</td>
				</tr>

				<!-- FIM GRUPO FATURAMENTO -->


				<!-- ROTA INICIAL -->

				<tr>
					<td colspan="4" HEIGHT="15"></td>
				</tr>
				<tr>
					<td colspan="4">

					<table width="100%" align="center" bgcolor="#99CCFF" border="0">
						<tr>
							<td><strong>Informe os dados da Rota Inicial:</strong></td>
						</tr>
						<tr bgcolor="#cbe5fe">
							<td width="100%" align="center">

							<table width="100%" border="0">
								<tr>
									<td height="10" width="120"><strong>Rota:</strong></td>
									<td><html:text  maxlength="4" 
 											        property="codigoRotaOrigem"
													size="5" tabindex="12"
													onkeyup="replicaDados(document.forms[0].codigoRotaOrigem, document.forms[0].codigoRotaDestino);bloquearLSQLS();"
													onkeypress="return isCampoNumerico(event);limpar(15);" />
									</td>
								</tr>

								<tr>
									<td height="10"><strong>Seq. da Rota:</strong></td>
									<td><html:text  maxlength="6" 
													property="sequencialRotaOrigem"
													size="7" 
													tabindex="13"
													onkeyup="replicaDados(document.forms[0].sequencialRotaOrigem, document.forms[0].sequencialRotaDestino);bloquearLSQLS();" 
													onkeypress="return isCampoNumerico(event);"/>
									</td>
								</tr>

							</table>

							</td>
						</tr>
					</table>

					</td>
				</tr>

				<!-- FIM ROTA INICIAL -->

				<tr>
					<td colspan="4" HEIGHT="5"></td>
				</tr>

				<!-- ROTA FINAL -->

				<tr>
					<td colspan="4">

					<table width="100%" align="center" bgcolor="#99CCFF" border="0">
						<tr>
							<td><strong>Informe os dados da Rota Final:</strong></td>
						</tr>
						<tr bgcolor="#cbe5fe">
							<td width="100%" align="center">

							<table width="100%" border="0">
								<tr>
									<td height="10" width="120"><strong>Rota:</strong></td>
									<td><html:text  maxlength="4" 
													property="codigoRotaDestino"
													size="5" 
													tabindex="14" 
													onkeypress="return isCampoNumerico(event);limpar(17);"
													onkeyup="bloquearLSQLS();" />
									</td>
								</tr>

								<tr>
									<td height="10"><strong>Seq. da Rota:</strong></td>
									<td><html:text  maxlength="6" 
													property="sequencialRotaDestino"
													size="7" 
													tabindex="15" 
													onkeyup="bloquearLSQLS();" 
													onkeypress="return isCampoNumerico(event);"/>
									</td>
								</tr>

							</table>

							</td>
						</tr>
					</table>

					</td>
				</tr>

				<!-- FIM ROTA FINAL -->

				<tr>
					<td><strong>Banco de Débito Automático:</strong></td>
					<td colspan="3"><logic:equal name="habilitaBanco" value="true">
						<html:select property="banco" style="width: 350px; height:100px;"
							multiple="true" onchange="javascript:habilitaCamposBanco();">
							<html:option
								value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
							<logic:present name="colecaoBancos" scope="session">
								<html:options collection="colecaoBancos"
									labelProperty="descricao" property="id" />
							</logic:present>
						</html:select>
					</logic:equal> <logic:equal name="habilitaBanco" value="false">
						<html:select property="banco" style="width: 350px; height:100px;"
							multiple="true" disabled="true">
							<html:option
								value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
							<logic:present name="colecaoBancos" scope="session">
								<html:options collection="colecaoBancos"
									labelProperty="descricao" property="id" />
							</logic:present>
						</html:select>
					</logic:equal></td>
				</tr>

				<tr>
					<td><strong>Esfera de Poder:</strong></td>
					<td colspan="3">
						<html:select property="esferaPoder" style="width: 350px; height:100px;"
							multiple="true" >
							<html:option
								value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
							<logic:present name="colecaoEsferasPoder" scope="session">
								<html:options collection="colecaoEsferasPoder"
									labelProperty="descricao" property="id" />
							</logic:present>
						</html:select>
					</td>
				</tr>
				
				<tr>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td colspan="3" class="style1">
						<input name="Submit22"
							   class="bottonRightCol" 
							   value="Limpar" 
							   tabindex="16" 
							   type="button"
							   onclick="window.location.href='/gsan/exibirFiltrarImovelInserirManterContaAction.do?menu=sim';">
					&nbsp; 
						<input type="button" 
							   name="Button" 
							   class="bottonRightCol"
							   value="Cancelar" 
							   tabindex="17"
							   onClick="javascript:window.location.href='/gsan/telaPrincipal.do'"
							   style="width: 80px" />
					</td>
					<td align="right">
						<gsan:controleAcessoBotao name="Button" value="Consultar" 
								tabindex="18" onclick="javascript:validarForm(document.forms[0]);"
								url="consultarDebitoAction.do" />
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
