<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%@ page import="gcom.util.ConstantesSistema"%>

<html:html>
<head>

<%@ include file="/jsp/util/titulo.jsp"%>

<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>

<html:javascript staticJavascript="false" formName="ImovelEmissaoOrdensSeletivasActionForm" dynamicJavascript="false" />

<script language="JavaScript" src="<bean:message key="caminho.js"/>cadastro/imovel/imovel_emissao_ordens_seletivas.js"></script>

<script>
	var bCancel = false; 
	
    function validateImovelEmissaoOrdensSeletivasActionForm(form) {
    
    	if ( form.usuarioSemPermissaoGerarOS.value == '2' ) {
			form.sugestao[1].disabled = true;
		}
       
        if (bCancel) {
      		return true; 
        } 
        else {
			
			if (validateInteger(form)) {

				if ( (form.sugestao[0].checked) && !form.tipoRelatorio[0].checked && !form.tipoRelatorio[1].checked) {
					alert('Informe tipo relatório.');
					return false;
				}
				
				if ( (form.sugestao[1].checked) && (form.firma.selectedIndex == 0) ) {
					alert('Informe a Firma.');
					form.firma.focus();
					return false;
				}
				
				if ( parseInt(form.localidadeFinal.value) < parseInt(form.localidadeInicial.value) ) {
					alert('A Localidade Final deve ser Maior ou Igual a Inicial.');
					form.localidadeFinal.focus();
					form.nomeLocalidadeFinal.value = '';
					return false;
				}
				else {
					
					if ( parseInt(form.codigoSetorComercialFinal.value) < parseInt(form.codigoSetorComercialInicial.value) ) {
						alert('O Código do Setor Comercial Final deve ser Maior ou Igual ao Inicial.');
						form.codigoSetorComercialFinal.focus();
						form.nomeSetorComercialFinal.value = '';
						return false;
					}
					else {
						
						if ( parseInt(form.quadraFinal.value) < parseInt(form.quadraInicial.value) ) {
							alert('A Quadra Final deve ser Maior ou Igual a Inicial.');
							form.quadraFinal.focus();
							return false;
						}
						else{
							
							if ( parseInt(form.rotaFinal.value) < parseInt(form.rotaInicial.value) ) {
								alert('A Rota Final deve ser Maior ou Igual a Inicial.');
								form.rotaFinal.focus();
								return false;
							}
							else{
								
								if ( parseInt(form.rotaSequenciaFinal.value) < parseInt(form.rotaSequenciaInicial.value) ) {
									alert('O Sequencial Final deve ser Maior ou Igual a Inicial.');
									form.form.rotaSequenciaFinal.focus();
									return false;
								}
							}
						}
					}
				}
				
				form.nomeFirma.value = form.firma.options[form.firma.selectedIndex].text;
				
				return true;
	       	}
	       	else {
	       		return false;
	       	}
		}
   	} 

    function IntegerValidations () { 
	     this.aa = new Array("quantidadeMaxima", "Quantidade Máxima deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
	     this.ab = new Array("elo", "Elo deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
	     this.ac = new Array("logradouro", "Logradouro deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
	     this.ad = new Array("localidadeInicial", "Localidade Inicial deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
	     this.ae = new Array("localidadeFinal", "Localidade Final deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
	     this.af = new Array("codigoSetorComercialInicial", "Código do Setor Comercial Inicial deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
	     this.ag = new Array("codigoSetorComercialFinal", "Código do Setor Comercial Final deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
	     this.ah = new Array("quadraInicial", "Quadra Inicial deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
	     this.ai = new Array("quadraFinal", "Quadra Final deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
	     this.aj = new Array("rotaInicial", "Rota Inicial deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
	     this.al = new Array("rotaSequenciaInicial", "Sequencial Inicial deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
	     this.am = new Array("rotaFinal", "Rota Final deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
	     this.an = new Array("rotaSequenciaFinal", "Sequencial Final deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
    }
    
    
    function habilitaDesabilitaAbaHidrometro() {
		var form = document.forms[0];
		
		if (form.tipoOrdem[0].checked) {
			document.getElementById('2').style.display = 'none';
		}
		
		if (form.tipoOrdem[1].checked || form.tipoOrdem[2].checked || form.tipoOrdem[3].checked) {
			document.getElementById('2').style.display = '';
		}
	}
    
	function habilitaDesabilitaAbaHidrometroTeste() {
		var form = document.forms[0];

		
		if (form.tipoOrdem.value == 304 || form.tipoOrdem.value == -1) {
			document.getElementById('2').style.display = 'none';
		}else{
			document.getElementById('2').style.display = '';
		}
	}
	
	function duplicarReferenciaCobranca(){
		var formulario = document.forms[0]; 
		formulario.referenciaCobrancaFinal.value = formulario.referenciaCobrancaInicial.value;
	}
	
	function habilitaDesabilitaFirma() {
		var form = document.forms[0];
		
		if ( form.usuarioSemPermissaoGerarOS.value == '2' ) {
			form.sugestao[1].disabled = true;
			form.sugestao[1].checked = false;
			form.sugestao[0].checked = true;			
		}
		

		if (form.sugestao[0].checked) { 
			form.firma.disabled = true;
			form.firma.selectedIndex = 0;
			form.tipoRelatorio[0].disabled = false;
			if(form.tipoRelatorio[0].checked==false &&form.tipoRelatorio[1].checked==false){
				form.tipoRelatorio[0].checked = true;
				form.tipoRelatorio.value = 1;
			}
			form.tipoRelatorio[1].disabled = false;
		}
		
		if (form.sugestao[1].checked) {
			form.firma.disabled = false;
			form.tipoRelatorio[0].disabled = true;
			form.tipoRelatorio[1].disabled = true;
			form.tipoRelatorio[0].checked = false;
			form.tipoRelatorio[1].checked = false;
		}
	}
	
	function habilitaDesabilitaInscricao() {
		
		var form = document.forms[0];
		
		if (form.gerenciaRegional.value != "-1" || form.unidadeNegocio.value != "-1" || form.elo.value.length > 0 ||
			form.logradouro.value.length > 0) {
			
			if (form.gerenciaRegional.value != "-1"){
				
				form.unidadeNegocio.value = "-1"
				form.unidadeNegocio.disabled = true;
					
				form.elo.value = "";
				form.nomeElo.value = "";
				form.elo.disabled = true;
				
				form.logradouro.value = "";
				form.descricaoLogradouro.value = "";
				form.logradouro.disabled = true;
			}
			else if (form.unidadeNegocio.value != "-1"){
					
				form.gerenciaRegional.value = "-1"
				form.gerenciaRegional.disabled = true;
					
				form.elo.value = "";
				form.nomeElo.value = "";
				form.elo.disabled = true;
				
				form.logradouro.value = "";
				form.descricaoLogradouro.value = "";
				form.logradouro.disabled = true;
			}
			else if (form.elo.value.length > 0){
					
				form.gerenciaRegional.value = "-1"
				form.gerenciaRegional.disabled = true;
					
				form.unidadeNegocio.value = "-1"
				form.unidadeNegocio.disabled = true;
				
				form.logradouro.value = "";
				form.descricaoLogradouro.value = "";
				form.logradouro.disabled = true;
			}
			else if (form.logradouro.value.length > 0){
					
				form.gerenciaRegional.value = "-1"
				form.gerenciaRegional.disabled = true;
					
				form.unidadeNegocio.value = "-1"
				form.unidadeNegocio.disabled = true;
				
				form.elo.value = "";
				form.nomeElo.value = "";
				form.elo.disabled = true;
			}
			
			form.localidadeInicial.value = "";
			form.nomeLocalidadeInicial.value = "";
			form.localidadeFinal.value = "";
			form.nomeLocalidadeFinal.value = "";
			
			form.setorComercialInicial.value = "";
			form.setorComercialFinal.value = "";
			form.codigoSetorComercialInicial.value = "";
			form.codigoSetorComercialFinal.value = "";
			form.nomeSetorComercialInicial.value = "";
			form.nomeSetorComercialFinal.value = "";
			
			form.quadraInicial.value = "";
			form.quadraFinal.value = "";
			form.idQuadraInicial.value = "";
			form.idQuadraFinal.value = "";
			
			form.rotaInicial.value = "";
			form.rotaSequenciaInicial.value = "";
			form.rotaFinal.value = "";
			form.rotaSequenciaFinal.value = "";
			
			form.localidadeInicial.disabled = true;
			form.localidadeFinal.disabled = true;
			form.codigoSetorComercialInicial.disabled = true;
			form.codigoSetorComercialFinal.disabled = true;
			form.quadraInicial.disabled = true;
			form.quadraFinal.disabled = true;
			form.rotaInicial.disabled = true;
			form.rotaSequenciaInicial.disabled = true;
			form.rotaFinal.disabled = true;
			form.rotaSequenciaFinal.disabled = true;
			
		}
		else {
			
			form.gerenciaRegional.disabled = false;
			form.unidadeNegocio.disabled = false;
			form.elo.disabled = false;
			form.logradouro.disabled = false;
				
			form.localidadeInicial.disabled = false;
			form.localidadeFinal.disabled = false;
			
			controleSetorComercial();
			
			controleQuadra();
		}
	}
	
	function controleSetorComercial() {
		
		var form = document.forms[0];
			
		if (form.localidadeInicial.value.length > 0) {
		
			form.codigoSetorComercialInicial.disabled = false;
			
			if(form.localidadeFinal.value.length > 0){
			
				form.codigoSetorComercialFinal.disabled = false;
				
				if(form.localidadeInicial.value != form.localidadeFinal.value){
				
					form.codigoSetorComercialInicial.disabled = true;
					form.codigoSetorComercialFinal.disabled = true;
						
					form.codigoSetorComercialInicial.value = "";
					form.codigoSetorComercialFinal.value = "";
						
					form.setorComercialInicial.value = "";
					form.setorComercialFinal.value = "";
						
					form.nomeSetorComercialInicial.value = "";
					form.nomeSetorComercialFinal.value = "";
						
					document.getElementById("btPesqSetorComercialInicial").disabled = true;
					document.getElementById("btPesqSetorComercialFinal").disabled = true;
				
					controleQuadra();
		   		}
		   		else{
		   			
		   			form.codigoSetorComercialInicial.disabled = false;
					form.codigoSetorComercialFinal.disabled = false;
					
					document.getElementById("btPesqSetorComercialInicial").disabled = false;
					document.getElementById("btPesqSetorComercialFinal").disabled = false;
				
					controleQuadra();
		   		}
			}
			
		}
		else {
			
			form.codigoSetorComercialInicial.disabled = true;
			form.codigoSetorComercialFinal.disabled = true;
				
			form.codigoSetorComercialInicial.value = "";
			form.codigoSetorComercialFinal.value = "";
				
			form.setorComercialInicial.value = "";
			form.setorComercialFinal.value = "";
				
			form.nomeSetorComercialInicial.value = "";
			form.nomeSetorComercialFinal.value = "";
				
			document.getElementById("btPesqSetorComercialInicial").disabled = true;
			document.getElementById("btPesqSetorComercialFinal").disabled = true;
				
			controleQuadra();
		}
	}
	
	function controleQuadra() {
		var form = document.forms[0];
		
		if (form.codigoSetorComercialInicial.value.length > 0) {
		
			if(form.codigoSetorComercialFinal.value.length > 0){
			   
			   	if (form.codigoSetorComercialInicial.value != form.codigoSetorComercialFinal.value){
			   		
			   		form.quadraInicial.value = "";
					form.quadraFinal.value = "";
					form.idQuadraInicial.value = "";
					form.idQuadraFinal.value = "";
					form.rotaInicial.value = "";
					form.rotaSequenciaInicial.value = "";
					form.rotaFinal.value = "";
					form.rotaSequenciaFinal.value = "";
				
					form.quadraInicial.disabled = true;
					form.quadraFinal.disabled = true;
					form.rotaInicial.disabled = true;
					form.rotaSequenciaInicial.disabled = true;
					form.rotaFinal.disabled = true;
					form.rotaSequenciaFinal.disabled = true;
			   	}
			   	else{
			   		
			   		form.quadraInicial.disabled = false;
					form.quadraFinal.disabled = false;
					form.rotaInicial.disabled = false;
					form.rotaSequenciaInicial.disabled = false;
					form.rotaFinal.disabled = false;
					form.rotaSequenciaFinal.disabled = false;
			   	}
			}
		}
		else {
			
			form.quadraInicial.value = "";
			form.quadraFinal.value = "";
			form.idQuadraInicial.value = "";
			form.idQuadraFinal.value = "";
			form.rotaInicial.value = "";
			form.rotaSequenciaInicial.value = "";
			form.rotaFinal.value = "";
			form.rotaSequenciaFinal.value = "";
			
			form.quadraInicial.disabled = true;
			form.quadraFinal.disabled = true;
			form.rotaInicial.disabled = true;
			form.rotaSequenciaInicial.disabled = true;
			form.rotaFinal.disabled = true;
			form.rotaSequenciaFinal.disabled = true;
			
		}
	}
	
	

function limparBorrachaOrigem(tipo){
	
	var form = document.forms[0];
	
	switch(tipo){
		case 1: //De localidara pra baixo
		    
		    if(!form.localidadeInicial.disabled){
				
				form.localidadeInicial.value = "";
				form.nomeLocalidadeInicial.value = "";
				form.localidadeFinal.value = "";
				form.nomeLocalidadeFinal.value = "";
	
				controleSetorComercial();
			}
			
			break;
			
		case 2: //De setor para baixo
		    
		    if(!form.codigoSetorComercialInicial.disabled){
				
				form.codigoSetorComercialInicial.value = "";
				form.setorComercialInicial.value = "";
				form.nomeSetorComercialInicial.value = "";
				
				form.codigoSetorComercialFinal.value = "";
				form.setorComercialFinal.value = "";		   
				form.nomeSetorComercialFinal.value = "";
				
				controleQuadra();
		    }
		    
		    break;
	}
}

/*
Limpa os campos da faixa final pelo click na borracha(conforme parametro passado)
*/
function limparBorrachaDestino(tipo){
	var form = document.ImovelEmissaoOrdensSeletivasActionForm;

	switch(tipo){
		case 1: //De localidade pra baixo
			
			if(!form.localidadeFinal.disabled){
				
				form.localidadeFinal.value = "";
				form.nomeLocalidadeFinal.value = "";
				
				form.setorComercialFinal.value = ""; 
		   		form.nomeSetorComercialFinal.value = "";		   
   		   		form.codigoSetorComercialFinal.value = "";
   		   		
   		   		form.quadraFinal.value = "";
				form.idQuadraFinal.value = "";
				form.rotaFinal.value = "";
				form.rotaSequenciaFinal.value = "";
				
				form.codigoSetorComercialFinal.disabled = true;
				form.quadraFinal.disabled = true;
				form.rotaFinal.disabled = true;
				form.rotaSequenciaFinal.disabled = true;
				
				form.localidadeFinal.focus();
			}
			
			break;
			
		case 2: //De setor para baixo		 
		
			if(!form.codigoSetorComercialInicial.disabled){
				
				form.setorComercialFinal.value = ""; 
		   		form.nomeSetorComercialFinal.value = "";		   
   		   		form.codigoSetorComercialFinal.value = "";
   		   		
   		   		form.quadraFinal.value = "";
				form.idQuadraFinal.value = "";
				form.rotaFinal.value = "";
				form.rotaSequenciaFinal.value = "";
				
				form.quadraFinal.disabled = true;
				form.rotaFinal.disabled = true;
				form.rotaSequenciaFinal.disabled = true;
				
				form.codigoSetorComercialFinal.focus();
			}
			
   		   	break;
   		   	
   		default:
   			break
     }
}

/*
recupera os dados vindos pelo metodo enviarDadosQuatroParametros 
*/

function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
	var form = document.ImovelEmissaoOrdensSeletivasActionForm;
	
	if (tipoConsulta == 'localidadeOrigem') {
      form.localidadeInicial.value = codigoRegistro;
	  form.nomeLocalidadeInicial.value = descricaoRegistro;
	  form.nomeLocalidadeInicial.style.color = "#000000";
	  
	  form.localidadeFinal.value = codigoRegistro;
      form.nomeLocalidadeFinal.value = descricaoRegistro;
      form.nomeLocalidadeFinal.style.color = "#000000";
      form.codigoSetorComercialInicial.focus();
      
      controleSetorComercial();
	}

	if (tipoConsulta == 'localidadeDestino') {
      form.localidadeFinal.value = codigoRegistro;
      form.nomeLocalidadeFinal.value = descricaoRegistro;
   	  form.nomeLocalidadeFinal.style.color = "#000000";
   	  form.codigoSetorComercialFinal.focus();
   	  
   	  controleSetorComercial();
	}
	
	if (tipoConsulta == 'elo') {
		form.elo.value = codigoRegistro;
		form.nomeElo.value = descricaoRegistro;
		form.nomeElo.style.color = "#000000";
		form.elo.focus();
		
		habilitaDesabilitaInscricao();
	}
	
	if (tipoConsulta == 'logradouro') {
      	form.logradouro.value = codigoRegistro;
      	form.descricaoLogradouro.value = descricaoRegistro;
      	form.descricaoLogradouro.style.color = "#000000";
      	form.logradouro.focus();
      	
      	habilitaDesabilitaInscricao();
    }
    
    if (tipoConsulta == 'imovel'){
    	form.idImovel.value = codigoRegistro;
    	form.inscricaoImovel.value = descricaoRegistro;
    	form.inscricaoImovel.style.color = "#000000";
    	
    	habilitarDesabilitarDemaisCampos();
    }
	
}

/*
recupera os dados vindos pelo metodo enviarDadosQuatroParametros 
*/

function recuperarDadosQuatroParametros(idRegistro, descricaoRegistro, codigoRegistro, tipoConsulta) {
	var form = document.ImovelEmissaoOrdensSeletivasActionForm;

	if (tipoConsulta == 'setorComercialOrigem') {
      form.codigoSetorComercialInicial.value = codigoRegistro;
      form.setorComercialInicial.value = idRegistro;
	  form.nomeSetorComercialInicial.value = descricaoRegistro;
	  form.nomeSetorComercialInicial.style.color = "#000000";
	  
	  form.codigoSetorComercialFinal.value = codigoRegistro;
      form.setorComercialFinal.value = idRegistro;
	  form.nomeSetorComercialFinal.value = descricaoRegistro;
	  form.nomeSetorComercialFinal.style.color = "#000000";
	  
	  controleQuadra();
	}

	if (tipoConsulta == 'setorComercialDestino') {
      form.codigoSetorComercialFinal.value = codigoRegistro;
      form.setorComercialFinal.value = idRegistro;
	  form.nomeSetorComercialFinal.value = descricaoRegistro;
	  form.nomeSetorComercialFinal.style.color = "#000000"; 
	  
	  controleQuadra();
	}
}

	//Caso tenha informado o imovel, bloquear demais campos
	function habilitarDesabilitarDemaisCampos(){
		var form = document.ImovelEmissaoOrdensSeletivasActionForm;
		if (form.inscricaoImovel.value == ''){
			form.quantidadeMaxima.disabled = false;
			form.gerenciaRegional.disabled = false;
			form.unidadeNegocio.disabled = false;
			form.elo.disabled = false;
			form.localidadeInicial.disabled = false;
			form.localidadeFinal.disabled = false;
		}else{
			//bloqueia campos
			//form.firma.disabled = true;
			form.quantidadeMaxima.disabled = true;
			form.gerenciaRegional.disabled = true;
			form.unidadeNegocio.disabled = true;
			form.elo.disabled = true;
			form.nomeElo.disabled = true;
			form.logradouro.disabled = true;
			form.descricaoLogradouro.disabled = true;
			form.localidadeInicial.disabled = true;
			form.nomeLocalidadeInicial.disabled = true;
			form.localidadeFinal.disabled = true;
			form.nomeLocalidadeFinal.disabled = true;
			form.codigoSetorComercialInicial.disabled = true;
			form.nomeSetorComercialInicial.disabled = true;
			form.codigoSetorComercialFinal.disabled = true;
			form.nomeSetorComercialFinal.disabled = true;
			form.quadraInicial.disabled = true;
			form.quadraFinal.disabled = true;
			form.rotaInicial.disabled = true;
			form.rotaFinal.disabled = true;
			form.rotaSequenciaInicial.disabled = true;
			form.rotaSequenciaFinal.disabled = true;
			//limpa campos
			//form.firma.value = "-1";
			form.unidadeNegocio.value = "-1";
			form.gerenciaRegional.value = "-1";
			form.quantidadeMaxima.value = '';
			form.elo.value = '';
			form.logradouro.value = '';
			form.descricaoLogradouro.value = '';
			form.localidadeInicial.value = '';
			form.nomeLocalidadeInicial.value = '';
			form.localidadeFinal.value = '';
			form.nomeLocalidadeFinal.value = '';
			form.codigoSetorComercialInicial.value = '';
			form.nomeSetorComercialInicial.value = '';
			form.codigoSetorComercialFinal.value = '';
			form.nomeSetorComercialFinal.value = '';
			form.quadraInicial.value = '';
			form.quadraFinal.value = '';
			form.rotaInicial.value = '';
			form.rotaFinal.value = '';
			form.rotaSequenciaInicial.value = '';
			form.rotaSequenciaFinal.value = '';
		}
	}
	
	function limparImovel(){
		var form = document.ImovelEmissaoOrdensSeletivasActionForm;
		form.inscricaoImovel.value ='';
		form.idImovel.value = '';
		form.inscricaoImovel.style.color = "#000000";
	}
	

</script>

</head>
<body leftmargin="5" topmargin="5" onload="//setarFoco('${requestScope.nomeCampo}');">


<html:form action="/filtrarImovelEmissaoOrdensSeletivasWizardAction"
	type="gcom.gui.atendimentopublico.ordemservico.ImovelEmissaoOrdensSeletivasActionForm"
	onsubmit="return validateImovelEmissaoOrdensSeletivasActionForm(this);"
	name="ImovelEmissaoOrdensSeletivasActionForm" method="post">

	<jsp:include page="/jsp/util/wizard/navegacao_abas_wizard_valida_avancar_valida_voltar.jsp?numeroPagina=1" />

	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>

	<table width="770" border="0" cellspacing="5" cellpadding="0">
		<input type="hidden" name="numeroPagina" value="1" />
		<html:hidden property="nomeFirma"/>
		<html:hidden property="usuarioSemPermissaoGerarOS"/>
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
				
				<!--
				******************************************************************
				** Início do Formulário ******************************************
				******************************************************************
				-->
				
				<table width="100%" border="0" align="center" cellpadding="0"
					cellspacing="0">
					<tr>
						<td width="11"><img border="0"
							src="<bean:message key="caminho.imagens"/>parahead_left.gif" /></td>
						<td class="parabg">Filtrar Imóvel</td>
						<td width="11"><img border="0"
							src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
					</tr>
				</table>
				<p>&nbsp;</p>
			
				<table width="100%" border="0">
				<tr>
					<td colspan="2">Para filtrar o(s) im&oacute;vel(is), informe os dados abaixo:</td>
				</tr>
					
				 <tr>
					<td width="22%"><strong>Tipo da Ordem:</strong></td>
					<td align="left">
						<table width="100%" border="0">
							<tr>
								<logic:present name="tipoOrdem">
									<td>
									    <html:radio value="INSTALACAO" property="tipoOrdem" onclick="habilitaDesabilitaAbaHidrometro();"></html:radio>&nbsp;Instala&ccedil;&atilde;o Hidr&ocirc;metro
									    <html:radio value="SUBSTITUICAO" property="tipoOrdem" onclick="habilitaDesabilitaAbaHidrometro();"></html:radio>&nbsp;Substitui&ccedil;&atilde;o Hidr&ocirc;metro
									    <html:radio value="REMOCAO" property="tipoOrdem" onclick="habilitaDesabilitaAbaHidrometro();"></html:radio>Remoção Hidr&ocirc;metro
									    <html:radio value="INSPECAO" property="tipoOrdem" onclick="habilitaDesabilitaAbaHidrometro();"></html:radio>Inspeção de Anormalidade
									</td>
								</logic:present>
								<logic:notPresent name="tipoOrdem">
									<td>
									    <input type="radio" name="tipoOrdem" value="INSTALACAO" checked="checked" onclick="habilitaDesabilitaAbaHidrometro();">Instala&ccedil;&atilde;o Hidr&ocirc;metro
									    <input type="radio" name="tipoOrdem" value="SUBSTITUICAO" onclick="habilitaDesabilitaAbaHidrometro();">Substitui&ccedil;&atilde;o Hidr&ocirc;metro
									    <input type="radio" name="tipoOrdem" value="REMOCAO" onclick="habilitaDesabilitaAbaHidrometro();">Remoção Hidr&ocirc;metro
									    <input type="radio" name="tipoOrdem" value="INSPECAO" onclick="habilitaDesabilitaAbaHidrometro();">Inspeção de Anormalidade
									</td>
								</logic:notPresent>
							</tr>
						</table>
					</td>
				</tr>
				<!-- 	
				<tr>
					<td width="22%"><strong>Tipo da Ordem:<font color="#FF0000">*</font></strong></td>
					<td>
						<html:select property="tipoOrdem" style="width: 240px;" onclick="habilitaDesabilitaAbaHidrometro();">
							<html:option value="-1">&nbsp;</html:option>
						</html:select>
						
					</td>
				</tr>
				-->	
					
					
				<tr>
					<td><strong>Tipo de Emissão:</strong></td>
					<td align="left">
						<table width="100%" border="0">
							<tr>
								<td>
									
									<logic:present name="sugestao">
									
										<html:radio value="1" property="sugestao" onclick="habilitaDesabilitaFirma();"></html:radio>&nbsp;Relatório
									
										<logic:equal name="usuarioPermissaoGerar" value="true">
											<html:radio value="2" property="sugestao" onclick="habilitaDesabilitaFirma();"></html:radio>&nbsp;Ordem de Serviço
										</logic:equal>
										
										<logic:equal name="usuarioPermissaoGerar" value="false">
											<html:radio value="2" property="sugestao" onclick="habilitaDesabilitaFirma();" disabled="true"></html:radio>&nbsp;Ordem de Serviço
										</logic:equal>
									
									</logic:present>
								
									<logic:notPresent name="sugestao">
										
										<input type="radio" name="sugestao" value="1" onclick="habilitaDesabilitaFirma();" checked="checked">&nbsp;Relatório
									
										<logic:equal name="usuarioPermissaoGerar" value="true">
											<html:radio value="2" property="sugestao" onclick="habilitaDesabilitaFirma();"></html:radio>&nbsp;Ordem de Serviço
										</logic:equal>
									
										<logic:equal name="usuarioPermissaoGerar" value="false">
											<html:radio value="2" property="sugestao" onclick="habilitaDesabilitaFirma();" disabled="true"></html:radio>&nbsp;Ordem de Serviço
										</logic:equal>
									
									</logic:notPresent>
								
								</td>
							</tr>
						</table>
					</td>
				</tr>
				
				<tr id="tipoRelatorio">
					<td>
						&nbsp;
					</td>
					<td align="left">
						<table width="100%" border="0">
							<tr>
								<td>			
										<logic:equal name="usuarioPermissaoGerar" value="true">
											<html:radio value="1" property="tipoRelatorio"></html:radio>&nbsp;Sintético
											<html:radio value="2" property="tipoRelatorio"></html:radio>&nbsp;Analítico
										</logic:equal>
										
										<logic:equal name="usuarioPermissaoGerar" value="false">
											<html:radio value="1" property="tipoRelatorio"  disabled="true"></html:radio>&nbsp;Sintético
											<html:radio value="2" property="tipoRelatorio"  disabled="true"></html:radio>&nbsp;Analítico
										</logic:equal>		
								</td>
							</tr>
						</table>
					</td>
				</tr>
					
				<tr>
					<td ><strong>Descrição Comando:</strong></td>
					<td align="left"><html:text property="descricaoComando" size="50" maxlength="50" ></html:text></td>
				</tr>	
					
				<tr>
					<td><strong>Firma:</strong></td>
					<td>
						<logic:present name="sugestao">
						
							<logic:equal name="sugestao" value="1">
								<html:select property="firma" disabled="true">
									<html:option value="-1">&nbsp;</html:option>
									<html:options collection="colecaoFirma" property="id" labelProperty="descricao" />
								</html:select>
							</logic:equal>
							
							<logic:equal name="sugestao" value="2">
								<html:select property="firma">
									<html:option value="-1">&nbsp;</html:option>
									<html:options collection="colecaoFirma" property="id" labelProperty="descricao" />
								</html:select>
							</logic:equal>
						
						</logic:present>
									
						<logic:notPresent name="sugestao">
						
							<html:select property="firma">
								<html:option value="-1">&nbsp;</html:option>
								<html:options collection="colecaoFirma" property="id" labelProperty="descricao" />
							</html:select>
						
						</logic:notPresent>
						
					</td>
				</tr>

				<tr>
					<td ><strong>Quantidade Máxima:</strong></td>
					<td align="left"><html:text property="quantidadeMaxima" size="5" maxlength="4" 
					onkeypress="return isCampoNumerico(event);"></html:text></td>
				</tr>
				
			<tr>
		      	<td><strong>Matr&iacute;cula do  Im&oacute;vel:</strong></td>
		        <td align = "left">
					<html:text property="idImovel" size="10" maxlength="9" tabindex="1" 
						onkeypress="validaEnterComMensagem(event, 'filtrarImovelEmissaoOrdensSeletivasWizardAction.do?action=exibirFiltrarImovelEmissaoOrdensSeletivasParametros&pesquisarImovel=OK', 'idImovel', 'Matrícula do Imóvel');return isCampoNumerico(event);"/>
					<a href="javascript:abrirPopup('exibirPesquisarImovelAction.do', 490, 800);">
					<img src="<bean:message key='caminho.imagens'/>pesquisa.gif" width="23" height="21" alt="Pesquisar" title="Pesquisar" border="0"></a>
		
					<logic:present name="corImovel">
		
						<logic:equal name="corImovel" value="exception">
							<html:text property="inscricaoImovel" size="22" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000"/>
						</logic:equal>
		
						<logic:notEqual name="corImovel" value="exception">
							<html:text property="inscricaoImovel" size="22" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>
						</logic:notEqual>
		
					</logic:present>
		
					<logic:notPresent name="corImovel">
		
						<logic:empty name="ImovelEmissaoOrdensSeletivasActionForm" property="idImovel">
							<html:text property="inscricaoImovel" value="" size="22" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000"/>
						</logic:empty>
						<logic:notEmpty name="ImovelEmissaoOrdensSeletivasActionForm" property="idImovel">
							<html:text property="inscricaoImovel" size="22" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>
						</logic:notEmpty>
						
		
					</logic:notPresent>
		        	
		        	<a href="javascript:limparImovel();habilitarDesabilitarDemaisCampos();">
		        	<img src="<bean:message key='caminho.imagens'/>limparcampo.gif" alt="Apagar" title="Apagar" border="0"></a>
		
		       	</td>
	  		</tr>
				
				<tr>
					<td><strong>Gerência Regional:</strong></td>
					<td>
						
						<html:select property="gerenciaRegional" onchange="habilitaDesabilitaInscricao();" 
						style="width: 240px;">
							<html:option value="-1">&nbsp;</html:option>
							<html:options collection="colecaoGerenciaRegional" property="id" labelProperty="nome" />
						</html:select>
						
					</td>
				</tr>
				
				<tr>
					<td><strong>Unidade Negócio:</strong></td>
					<td>
						
						<html:select property="unidadeNegocio" onchange="habilitaDesabilitaInscricao();"
						style="width: 240px;">
							<html:option value="-1">&nbsp;</html:option>
							<html:options collection="colecaoUnidadeNegocio" property="id" labelProperty="nome" />
						</html:select>
						
					</td>
				</tr>
					
				<tr>
					<td><strong>Localidade Pólo:</strong></td>
					<td>
						<html:text tabindex="7" maxlength="3" property="elo" size="5"
						onkeypress="habilitaDesabilitaInscricao(); validaEnter(event, 'filtrarImovelEmissaoOrdensSeletivasWizardAction.do?action=exibirFiltrarImovelEmissaoOrdensSeletivasParametros&objetoConsulta=4&inscricaoTipo=origem', 'elo');return isCampoNumerico(event);"/>
						
						<a href="javascript:chamarPopup('exibirPesquisarEloAction.do', 'origem', null, null, 275, 480, '');" id="btPesqElo">
						<img width="23" height="21" border="0" src="<bean:message key="caminho.imagens"/>pesquisa.gif"
						title="Pesquisar" /></a>
						
						<logic:present name="corEloOrigem">
							<logic:equal name="corEloOrigem" value="exception">
								<html:text property="nomeElo" size="35" readonly="true"
									style="background-color:#EFEFEF; border:0; color: #ff0000" />
							</logic:equal>
		
							<logic:notEqual name="corEloOrigem" value="exception">
								<html:text property="nomeElo" size="35" readonly="true"
									style="background-color:#EFEFEF; border:0; color: #000000" />
							</logic:notEqual>
						</logic:present>
							
						<logic:notPresent name="corEloOrigem">
							<logic:empty name="ImovelEmissaoOrdensSeletivasActionForm"
								property="elo">
								<html:text property="nomeElo" value="" size="35" readonly="true"
									style="background-color:#EFEFEF; border:0; color: #ff0000" />
							</logic:empty>

							<logic:notEmpty name="ImovelEmissaoOrdensSeletivasActionForm"
								property="elo">
								<html:text property="nomeElo" size="35" readonly="true"
									style="background-color:#EFEFEF; border:0; color: 	#000000" />
							</logic:notEmpty>
						</logic:notPresent>
							
						<a href="javascript:limparBorrachaElo(); habilitaDesabilitaInscricao();">
						<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar" /></a>
					</td>
				</tr>
				
				<tr>
					<td><strong>Logradouro:</strong></td>
					<td><html:text property="logradouro" maxlength="9" size="9" 
						onkeypress="javascript:habilitaDesabilitaInscricao();validaEnter(event, 'filtrarImovelEmissaoOrdensSeletivasWizardAction.do?action=exibirFiltrarImovelEmissaoOrdensSeletivasParametros&objetoConsulta=5&inscricaoTipo=origem', 'logradouro');return isCampoNumerico(event);"/>
					
						<a href="javascript:abrirPopup('exibirPesquisarLogradouroAction.do?indicadorUsoTodos=1&primeriaVez=1', 400, 800);"><img
						src="/gsan/imagens/pesquisa.gif" width="23"
						height="21" border="0" style="cursor:hand;" alt="Pesquisar" title="Pesquisar"></a>  
						
						<logic:present name="corLogradouro">
							<logic:equal name="corLogradouro" value="exception">
								<html:text property="descricaoLogradouro" size="40" readonly="true"
									style="background-color:#EFEFEF; border:0; color: #ff0000" />
							</logic:equal>
		
							<logic:notEqual name="corLogradouro" value="exception">
								<html:text property="descricaoLogradouro" size="40" readonly="true"
									style="background-color:#EFEFEF; border:0; color: #000000" />
							</logic:notEqual>
						</logic:present>
							
						<logic:notPresent name="corLogradouro">
							<logic:empty name="ImovelEmissaoOrdensSeletivasActionForm"
								property="elo">
								<html:text property="descricaoLogradouro" value="" size="40" readonly="true"
									style="background-color:#EFEFEF; border:0; color: #ff0000" />
							</logic:empty>

							<logic:notEmpty name="ImovelEmissaoOrdensSeletivasActionForm"
								property="elo">
								<html:text property="descricaoLogradouro" size="40" readonly="true"
									style="background-color:#EFEFEF; border:0; color: 	#000000" />
							</logic:notEmpty>
						</logic:notPresent>
						
						
					 	<a href="javascript:limparBorrachaLogradouro(); habilitaDesabilitaInscricao();">
						<img src="/gsan/imagens/limparcampo.gif"
						border="0" title="Apagar" /></a>
					</td>
				</tr>
				
				</table>
					
					
				<table width="100%" border="0">
					<tr>
						<td colspan="2">
					
							<table width="100%" align="center" bgcolor="#90c7fc" border="0">
							<tr>
								<td><strong>Inscrição Inicial</strong></td>
							</tr>
							<tr bgcolor="#cbe5fe">
								<td width="100%" align="center">
								
									<table width="100%" border="0">
								
									<tr>
										<td width="21%"><strong>Localidade:</strong></td>
										<td>
											<html:text tabindex="8" maxlength="3" property="localidadeInicial" size="5"
											onkeypress="duplicarLocalidade(); controleSetorComercial(); validaEnter(event, 'filtrarImovelEmissaoOrdensSeletivasWizardAction.do?action=exibirFiltrarImovelEmissaoOrdensSeletivasParametros&objetoConsulta=1&inscricaoTipo=origem', 'localidadeInicial');return isCampoNumerico(event);"
											onclick="javascript:validarLocalidade();" onblur="duplicarLocalidade();"/>
											
											<a href="javascript:chamarPopup('exibirPesquisarLocalidadeAction.do', 'origem', null, null, 275, 480, ''); controleSetorComercial();" id="btPesqLocalidadeInicial">
											<img width="23" height="21" border="0" src="<bean:message key="caminho.imagens"/>pesquisa.gif"
											title="Pesquisar" /></a>
											
											<logic:present name="corLocalidadeOrigem">
												<logic:equal name="corLocalidadeOrigem" value="exception">
													<html:text property="nomeLocalidadeInicial" size="35" readonly="true"
														style="background-color:#EFEFEF; border:0; color: #ff0000" />
												</logic:equal>
						
												<logic:notEqual name="corLocalidadeOrigem" value="exception">
													<html:text property="nomeLocalidadeInicial" size="35" readonly="true"
														style="background-color:#EFEFEF; border:0; color: #000000" />
												</logic:notEqual>
											</logic:present>
											
											<logic:notPresent name="corLocalidadeOrigem">
												<logic:empty name="ImovelEmissaoOrdensSeletivasActionForm"
													property="localidadeInicial">
													<html:text property="nomeLocalidadeInicial" value="" size="35" readonly="true"
														style="background-color:#EFEFEF; border:0; color: #ff0000" />
												</logic:empty>
				
												<logic:notEmpty name="ImovelEmissaoOrdensSeletivasActionForm"
													property="localidadeInicial">
													<html:text property="nomeLocalidadeInicial" size="35" readonly="true"
														style="background-color:#EFEFEF; border:0; color: 	#000000" />
												</logic:notEmpty>
											</logic:notPresent>
											
											<a href="javascript:limparBorrachaOrigem(1);">
											<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
											border="0" title="Apagar" /></a>
										</td>
									</tr>
									<tr>
										<td><strong>Setor Comercial:</strong></td>
										<td>
											<html:text tabindex="9" maxlength="3"
											property="codigoSetorComercialInicial" size="5"
											onkeypress="duplicarSetorComercial(); controleQuadra(); validaEnterDependencia(event, 'filtrarImovelEmissaoOrdensSeletivasWizardAction.do?action=exibirFiltrarImovelEmissaoOrdensSeletivasParametros&objetoConsulta=2&inscricaoTipo=origem', document.forms[0].codigoSetorComercialInicial, document.forms[0].localidadeInicial.value, 'Localidade Inicial.');return isCampoNumerico(event);"
											onblur="duplicarSetorComercial(); controleQuadra();"/>
											
											<a href="javascript:chamarPopup('exibirPesquisarSetorComercialAction.do', 'setorComercialOrigem','idLocalidade', document.ImovelEmissaoOrdensSeletivasActionForm.localidadeInicial.value, 275, 480, 'Informe Localidade Inicial.'); controleQuadra();" id="btPesqSetorComercialInicial">
											<img width="23" height="21" border="0" src="<bean:message key="caminho.imagens"/>pesquisa.gif" title="Pesquisar" /></a>
											
											<logic:present name="corSetorComercialOrigem">
												<logic:equal name="corSetorComercialOrigem" value="exception">
													<html:text property="nomeSetorComercialInicial" size="35" readonly="true"
														style="background-color:#EFEFEF; border:0; color: #ff0000" />
												</logic:equal>
					
												<logic:notEqual name="corSetorComercialOrigem" value="exception">
													<html:text property="nomeSetorComercialInicial" size="35" readonly="true"
														style="background-color:#EFEFEF; border:0; color: #000000" />
												</logic:notEqual>
					
											</logic:present>
											
											<logic:notPresent name="corSetorComercialOrigem">
												<logic:empty name="ImovelEmissaoOrdensSeletivasActionForm" property="codigoSetorComercialInicial">
													<html:text property="nomeSetorComercialInicial" value="" size="35" readonly="true"
														style="background-color:#EFEFEF; border:0; color: #ff0000" />
												</logic:empty>
												<logic:notEmpty name="ImovelEmissaoOrdensSeletivasActionForm" property="codigoSetorComercialInicial">
													<html:text property="nomeSetorComercialInicial" size="35" readonly="true"
														style="background-color:#EFEFEF; border:0; color: #000000" />
												</logic:notEmpty>
											</logic:notPresent>
											<a href="javascript:limparBorrachaOrigem(2);">
												<img src="<bean:message key="caminho.imagens"/>limparcampo.gif" border="0" title="Apagar" />
											</a>
											<html:hidden property="setorComercialInicial"/>
										</td>
									</tr>
									<tr>
										<td><strong>Quadra:</strong></td>
										<td colspan="3">
										
											<html:text tabindex="10" maxlength="4" property="quadraInicial" size="5"
											onkeypress="validaEnterDependencia(event, 'filtrarImovelEmissaoOrdensSeletivasWizardAction.do?action=exibirFiltrarImovelEmissaoOrdensSeletivasParametros&objetoConsulta=3&inscricaoTipo=origem', document.forms[0].quadraInicial, document.forms[0].codigoSetorComercialInicial.value, 'Setor Comercial Inicial.');return isCampoNumerico(event);"
											onblur="duplicarQuadra();"/> 
											
											<logic:present name="corQuadraOrigem" scope="request">
												<span style="color:#ff0000" id="msgQuadraInicial">
													<bean:write scope="request" name="msgQuadraInicial" />
												</span>
											</logic:present> 
											
											<logic:notPresent name="corQuadraOrigem" scope="request"></logic:notPresent> 
											
											<html:hidden property="idQuadraInicial" />
										
										</td>
									</tr>
									
									<tr>
										<td><strong>Rota inicial:</strong></td>
										<td align="left"><html:text property="rotaInicial" size="5" maxlength="4" disabled="true" 
											onkeypress="return isCampoNumerico(event);"></html:text>
										seq.:
										<html:text property="rotaSequenciaInicial" size="7" maxlength="6" disabled="true" 
											onkeypress="return isCampoNumerico(event);"></html:text></td>
									</tr>
									</table>
									
								</td>
							</tr>
							</table>
						
						</td>
					</tr>
				</table>		
				
				
				<table width="100%" border="0">
					<tr>
						<td colspan="2">
					
							<table width="100%" align="center" bgcolor="#90c7fc" border="0">
							<tr>
								<td><strong>Inscrição Final</strong></td>
							</tr>
							<tr bgcolor="#cbe5fe">
								<td width="100%" align="center">
								
									<table width="100%" border="0">
									<tr>
										<td width="21%"><strong>Localidade:</strong></td>
										<td>
											<html:text maxlength="3" property="localidadeFinal" size="5"
											onkeypress="controleSetorComercial(); limparDestino(1); validaEnter(event,'filtrarImovelEmissaoOrdensSeletivasWizardAction.do?action=exibirFiltrarImovelEmissaoOrdensSeletivasParametros&objetoConsulta=1&inscricaoTipo=destino', 'localidadeFinal');return isCampoNumerico(event);"
											tabindex="11"/>
											<a href="javascript:chamarPopup('exibirPesquisarLocalidadeAction.do', 'destino', null, null, 275, 480, ''); limparDestino(1);" id="btPesqLocalidadeFinal">
												<img width="23" height="21" border="0" src="<bean:message key="caminho.imagens"/>pesquisa.gif" title="Pesquisar" /></a>
											<logic:present name="corLocalidadeDestino">
												<logic:equal name="corLocalidadeDestino" value="exception">
													<html:text property="nomeLocalidadeFinal" size="35" readonly="true"
														style="background-color:#EFEFEF; border:0; color: #ff0000" />
												</logic:equal>
						
												<logic:notEqual name="corLocalidadeDestino" value="exception">
													<html:text property="nomeLocalidadeFinal" size="35" readonly="true"
														style="background-color:#EFEFEF; border:0; color: #000000" />
												</logic:notEqual>
											</logic:present>
											
											<logic:notPresent name="corLocalidadeDestino">
												<logic:empty name="ImovelEmissaoOrdensSeletivasActionForm" property="localidadeFinal">
													<html:text property="nomeLocalidadeFinal" value="" size="35" readonly="true"
														style="background-color:#EFEFEF; border:0; color: #ff0000" />
												</logic:empty>
												<logic:notEmpty name="ImovelEmissaoOrdensSeletivasActionForm" property="localidadeFinal">
													<html:text property="nomeLocalidadeFinal" size="35" readonly="true"
														style="background-color:#EFEFEF; border:0; color: 	#000000" />
												</logic:notEmpty>
											</logic:notPresent>
											
											<a href="javascript:limparBorrachaDestino(1);">
												<img src="<bean:message key="caminho.imagens"/>limparcampo.gif" border="0" title="Apagar" />
											</a>
										</td>
									</tr>
									<tr>
										<td><strong>Setor Comercial :</strong></td>
										<td>
											<html:text maxlength="3" property="codigoSetorComercialFinal" size="5"
												onkeyup="limparDestino(2);"
												onkeypress="controleQuadra(); validaEnterDependencia(event, 'filtrarImovelEmissaoOrdensSeletivasWizardAction.do?action=exibirFiltrarImovelEmissaoOrdensSeletivasParametros&objetoConsulta=2&inscricaoTipo=destino', document.forms[0].codigoSetorComercialFinal, document.forms[0].localidadeFinal.value, 'Localidade Final.');return isCampoNumerico(event);"
												tabindex="12" />
											<a href="javascript:chamarPopup('exibirPesquisarSetorComercialAction.do', 'setorComercialDestino', 'idLocalidade', document.ImovelEmissaoOrdensSeletivasActionForm.localidadeFinal.value, 275, 480, 'Informe Localidade Final.'); limparDestino(2);" id="btPesqSetorComercialFinal">
												<img width="23" height="21" border="0" src="<bean:message key="caminho.imagens"/>pesquisa.gif"
													title="Pesquisar" /></a>
											
											<logic:present name="corSetorComercialDestino">
												<logic:equal name="corSetorComercialDestino" value="exception">
													<html:text property="nomeSetorComercialFinal" size="35" readonly="true"
														style="background-color:#EFEFEF; border:0; color: #ff0000" />
												</logic:equal>
				
												<logic:notEqual name="corSetorComercialDestino" value="exception">
													<html:text property="nomeSetorComercialFinal" size="35" readonly="true"
														style="background-color:#EFEFEF; border:0; color: #000000" />
												</logic:notEqual>
											</logic:present>
											
											<logic:notPresent name="corSetorComercialDestino">
												<logic:empty name="ImovelEmissaoOrdensSeletivasActionForm" property="codigoSetorComercialFinal">
													<html:text property="nomeSetorComercialFinal" value="" size="35" readonly="true"
														style="background-color:#EFEFEF; border:0; color: #ff0000" />
												</logic:empty>
												<logic:notEmpty name="ImovelEmissaoOrdensSeletivasActionForm" property="codigoSetorComercialFinal">
													<html:text property="nomeSetorComercialFinal" size="35" readonly="true"
														style="background-color:#EFEFEF; border:0; color: #000000" />
												</logic:notEmpty>
											</logic:notPresent>
											
											<a href="javascript:limparBorrachaDestino(2);">
												<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
													border="0" title="Apagar" /></a>
													
											<html:hidden property="setorComercialFinal" />
										</td>
									</tr>
									<tr>
										<td><strong>Quadra:</strong></td>
										<td><html:text maxlength="4" property="quadraFinal" size="5"
											onkeypress="limparDestino(3); validaEnterDependencia(event, 'filtrarImovelEmissaoOrdensSeletivasWizardAction.do?action=exibirFiltrarImovelEmissaoOrdensSeletivasParametros&objetoConsulta=3&inscricaoTipo=destino', document.forms[0].quadraFinal, document.forms[0].codigoSetorComercialFinal.value, 'Setor Comercial Final.');return isCampoNumerico(event);"
											tabindex="13"/> 
											
											<logic:present name="corQuadraDestino" scope="request">
												<span style="color:#ff0000" id="msgQuadraFinal">
													<bean:write scope="request" name="msgQuadraFinal" />
												</span>
											</logic:present> 
											
											<logic:notPresent name="corQuadraDestino" scope="request"></logic:notPresent> 
											
											<html:hidden property="idQuadraFinal" />
										</td>
									</tr>
									
									<tr>
										<td><strong>Rota Final:</strong></td>
										<td align="left"><html:text property="rotaFinal" size="5" maxlength="4" disabled="true" 
											onkeypress="return isCampoNumerico(event);"></html:text>
										seq.:
										<html:text property="rotaSequenciaFinal" size="7" maxlength="6" disabled="true" 
											onkeypress="return isCampoNumerico(event);"></html:text></td>
									</tr>
									</table>
								
								</td>
							</tr>
							</table>
						</td>
					</tr>
				</table>
				
				<table width="100%" border="0">	
					<tr>
						<td colspan="4">
							<div align="right">
								<jsp:include page="/jsp/util/wizard/navegacao_botoes_wizard_valida_avancar_valida_voltar.jsp?numeroPagina=1" />
							</div>
						</td>
					</tr>
				</table>
				
				<p>&nbsp;</p>
			</td>
		</tr>
	</table>

	<%@ include file="/jsp/util/rodape.jsp"%>

</html:form>

<logic:present name="tipoOrdem">
	<logic:equal name="tipoOrdem" value="INSTALACAO">
		<script>document.getElementById('2').style.display = 'none';</script>
	</logic:equal>
	<logic:notEqual name="tipoOrdem" value="INSTALACAO">
		<script>document.getElementById('2').style.display = '';</script>
	</logic:notEqual>
</logic:present>
<logic:notPresent name="tipoOrdem">
	<script>document.getElementById('2').style.display = 'none';</script>
</logic:notPresent>

<script>
	habilitaDesabilitaAbaHidrometro();
	habilitaDesabilitaFirma();
	habilitaDesabilitaInscricao();
	controleSetorComercial();
	controleQuadra();
	habilitarDesabilitarDemaisCampos();
</script>

</body>
</html:html>