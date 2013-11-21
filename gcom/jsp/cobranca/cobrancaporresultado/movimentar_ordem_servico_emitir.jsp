<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>

<%@ page import="gcom.util.ConstantesSistema" %>
<%@ page import="java.math.BigDecimal"%>
<%@page import="gcom.gui.cobranca.cobrancaporresultado.OrdemServicoGeradaEmpresaCobrancaHelper" %>
<%@ page import="java.util.Collection" isELIgnored="false"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>

<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="InserirGrupoActionForm" dynamicJavascript="false" />
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js" ></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js" ></script>

<script>
	function facilitador(objeto){
	
		if (objeto.id == "0" || objeto.id == undefined){
			objeto.id = "1";
			marcarTodos();
		}
		else{
			objeto.id = "0";
			desmarcarTodos();
		}
	}

	function selecionarDiv(tipo){
		var form = document.forms[0];
		if(tipo==1){
			form.tipoDivEscolhida.value = 1;
			document.getElementById('tipoVisitaCobranca').style.display = 'block';
			document.getElementById('tipoEmpresaContratada').style.display = 'none';
			document.getElementById('tipoAtravesRA').style.display = 'none';
		}else if(tipo==2){
			form.tipoDivEscolhida.value = 2;
			document.getElementById('tipoVisitaCobranca').style.display = 'none';
			document.getElementById('tipoEmpresaContratada').style.display = 'block';
			document.getElementById('tipoAtravesRA').style.display = 'none';
		}else if(tipo==3){
			form.tipoDivEscolhida.value = 3;
			document.getElementById('tipoVisitaCobranca').style.display = 'none';
			document.getElementById('tipoEmpresaContratada').style.display = 'none';
			document.getElementById('tipoAtravesRA').style.display = 'block';
		}
	}

	function verificaSelecao(){
		var form = document.forms[0];
		if(form.tipoDivEscolhida.value==1){
			document.getElementById('tipoVisitaCobranca').style.display = 'block';
			document.getElementById('tipoEmpresaContratada').style.display = 'none';
			document.getElementById('tipoAtravesRA').style.display = 'none';
		}else if(form.tipoDivEscolhida.value==2){
			document.getElementById('tipoVisitaCobranca').style.display = 'none';
			document.getElementById('tipoEmpresaContratada').style.display = 'block';
			document.getElementById('tipoAtravesRA').style.display = 'none';
		}else if(form.tipoDivEscolhida.value==3){
			document.getElementById('tipoVisitaCobranca').style.display = 'none';
			document.getElementById('tipoEmpresaContratada').style.display = 'none';
			document.getElementById('tipoAtravesRA').style.display = 'block';
		}
	}
	
	
	function limparLocalidadeOrigem() {
		var form = document.forms[0];
		form.idLocalidadeOrigem.value = "";
		form.nomeLocalidadeOrigem.value = "";
		form.idLocalidadeDestino.value = "";
		form.nomeLocalidadeDestino.value = "";
		limparSetorComercialOrigem();
		bloqueiaDados();
		
	}
	
	function limparLocalidadeOrigemTecla() {
		var form = document.forms[0];
		form.nomeLocalidadeOrigem.value = "";
		form.nomeLocalidadeDestino.value = "";
	}
	
	function limparLocalidadeDestino() {
		var form = document.forms[0];
		form.idLocalidadeDestino.value = "";
		form.nomeLocalidadeDestino.value = "";
	}
	
	function limparLocalidadeDestinoTecla() {
		var form = document.forms[0];
		form.nomeLocalidadeDestino.value = "";
	}
	
	function limparSetorComercialOrigem() {
		var form = document.forms[0];
		form.idSetorComercialOrigem.value = "";
		form.codigoSetorComercialOrigem.value = "";
		form.descricaoSetorComercialOrigem.value = "";
		
		form.idSetorComercialDestino.value = "";
		form.codigoSetorComercialDestino.value = "";
		form.descricaoSetorComercialDestino.value = "";
		limparQuadraInicial();
	}
	
	function limparSetorComercialOrigemTecla() {
		var form = document.forms[0];
		form.descricaoSetorComercialOrigem.value = "";
		form.descricaoSetorComercialDestino.value = "";
	}
	
	function limparSetorComercialDestino() {
		var form = document.forms[0];
		form.idSetorComercialDestino.value = "";
		form.codigoSetorComercialDestino.value = "";
		form.descricaoSetorComercialDestino.value = "";
	}
	
	function limparSetorComercialDestinoTecla() {
		var form = document.forms[0];
		form.descricaoSetorComercialDestino.value = "";
	}
	
	function limparQuadraInicial(){
		var form = document.forms[0];
		form.codigoQuadraInicial.value = "";
		form.descricaoQuadraInicial.value = "";
		form.codigoQuadraFinal.value = "";
		form.descricaoQuadraFinal.value = "";
	}
	
	function limparQuadraInicialTecla() {
		var form = document.forms[0];
		form.descricaoQuadraInicial.value = "";
	}
	
	function limparQuadraFinal(){
		var form = document.forms[0];
		form.codigoQuadraFinal.value = "";
		form.descricaoQuadraFinal.value = "";
	}
	
	function limparQuadraFinalTecla() {
		var form = document.forms[0];
		form.descricaoQuadraFinal.value = "";
	}
	
	function pesquisarLocalidadeOrigem() {
		var form = document.forms[0];

		if (form.idLocalidadeOrigem.disabled == false)  {
			form.tipoPesquisa.value = 'origem';
			abrirPopup('exibirPesquisarLocalidadeAction.do', 275, 480);
		}
	}
	
	function pesquisarLocalidadeDestino() {
		var form = document.forms[0];

		if (form.idLocalidadeDestino.disabled == false)  {
			form.tipoPesquisa.value = 'destino';
			abrirPopup('exibirPesquisarLocalidadeAction.do', 275, 480);
		}
	}
	
	function pesquisarSetorComercialOrigem() {
		var form = document.forms[0];

		if (form.codigoSetorComercialOrigem.disabled == false) {
			form.tipoPesquisa.value = 'origem';
			abrirPopupDependencia('exibirPesquisarSetorComercialAction.do', form.idLocalidadeOrigem.value, 'Localidade Inicial', 275, 480);
		}
	}
	
	function pesquisarSetorComercialDestino() {
		var form = document.forms[0];
		
		if (form.codigoSetorComercialDestino.disabled == false) {
			form.tipoPesquisa.value = 'destino';
			abrirPopupDependencia('exibirPesquisarSetorComercialAction.do', form.idLocalidadeOrigem.value, 'Localidade Inicial', 275, 480);
		}
	}
	
	function pesquisarQuadraInicial() {
		var form = document.forms[0];

		if (form.codigoQuadraInicial.disabled == false) {
			form.tipoPesquisa.value = 'origem';
			abrirPopupDependencia('exibirPesquisarQuadraAction.do', form.codigoSetorComercialOrigem.value, 'Setor Comercial Inicial', 275, 480);
		}
	}
	
	function pesquisarQuadraFinal() {
		var form = document.forms[0];

		if (form.codigoQuadraFinal.disabled == false) {
			form.tipoPesquisa.value = 'destino';
			abrirPopupDependencia('exibirPesquisarQuadraAction.do', form.codigoSetorComercialOrigem.value, 'Setor Comercial Inicial', 275, 480);
		}
	}
	
	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {

    	var form = document.forms[0];
   
	    if (tipoConsulta == 'localidade') {
    		if (form.tipoPesquisa.value == 'origem') {
    			form.idLocalidadeOrigem.value = codigoRegistro;
				form.nomeLocalidadeOrigem.value = descricaoRegistro;
				form.nomeLocalidadeOrigem.style.color = "#000000";
				form.idLocalidadeDestino.value = codigoRegistro;
				form.nomeLocalidadeDestino.value = descricaoRegistro;
				form.nomeLocalidadeDestino.style.color = "#000000";
				bloqueiaDados();
			} else {
				form.idLocalidadeDestino.value = codigoRegistro;
				form.nomeLocalidadeDestino.value = descricaoRegistro;
				form.nomeLocalidadeDestino.style.color = "#000000";
			}
    	} else if (tipoConsulta == 'setorComercial') {
    		if (form.tipoPesquisa.value == 'origem') {
    			form.codigoSetorComercialOrigem.value = codigoRegistro;
				form.descricaoSetorComercialOrigem.value = descricaoRegistro;
				form.descricaoSetorComercialOrigem.style.color = "#000000";
				form.codigoSetorComercialDestino.value = codigoRegistro;
				form.descricaoSetorComercialDestino.value = descricaoRegistro;
				form.descricaoSetorComercialDestino.style.color = "#000000";
				bloqueiaDados();
			} else {
				form.codigoSetorComercialDestino.value = codigoRegistro;
				form.descricaoSetorComercialDestino.value = descricaoRegistro;
				form.descricaoSetorComercialDestino.style.color = "#000000";
			}
    	} else if (tipoConsulta == 'quadra') {
    		if (form.tipoPesquisa.value == 'origem') {
    			form.codigoQuadraInicial.value = codigoRegistro;
				form.descricaoQuadraInicial.value = descricaoRegistro;
				form.descricaoQuadraInicial.style.color = "#000000";
				form.codigoQuadraFinal.value = codigoRegistro;
				form.descricaoQuadraFinal.value = descricaoRegistro;
				form.descricaoQuadraFinal.style.color = "#000000";
				bloqueiaDados();
			} else {
				form.codigoQuadraFinal.value = codigoRegistro;
				form.descricaoQuadraFinal.value = descricaoRegistro;
				form.descricaoQuadraFinal.style.color = "#000000";
			}
    	}
    
    }
    
	
	function bloqueiaDados(){
	
		var form = document.forms[0];
		
		if((form.numeroOSInicial.value != null && form.numeroOSInicial.value != '')
			|| (form.numeroOSFinal.value != null && form.numeroOSFinal.value != '')){
			
			form.idsCategoria.value = "-1";
			form.idsCategoria.selectedIndex = 0;
			form.idsCategoria.style.color = "#000000";
			form.idsCategoria.disabled = true;
			form.idsCategoria.readOnly = true;
			form.idsCategoria.style.backgroundColor = '#EFEFEF';
			
			form.idsImovelPerfil.value = "-1";
			form.idsImovelPerfil.selectedIndex = 0;
			form.idsImovelPerfil.style.color = "#000000";
			form.idsImovelPerfil.disabled = true;
			form.idsImovelPerfil.readOnly = true;
			form.idsImovelPerfil.style.backgroundColor = '#EFEFEF';
			
			form.idsUnidadeNegocio.value = "-1";
			form.idsUnidadeNegocio.selectedIndex = 0;
			form.idsUnidadeNegocio.style.color = "#000000";
			form.idsUnidadeNegocio.disabled = true;
			form.idsUnidadeNegocio.readOnly = true;
			form.idsUnidadeNegocio.style.backgroundColor = '#EFEFEF';
			
			form.idsGerenciaRegional.value = "-1";
			form.idsGerenciaRegional.selectedIndex = 0;
			form.idsGerenciaRegional.style.color = "#000000";
			form.idsGerenciaRegional.disabled = true;
			form.idsGerenciaRegional.readOnly = true;
			form.idsGerenciaRegional.style.backgroundColor = '#EFEFEF';
		
			form.idLocalidadeOrigem.value = "";
			form.idLocalidadeOrigem.style.color = "#000000";
			form.idLocalidadeOrigem.readOnly = true;
			form.idLocalidadeOrigem.style.backgroundColor = '#EFEFEF';
			
			form.idLocalidadeDestino.value = "";
			form.idLocalidadeDestino.style.color = "#000000";
			form.idLocalidadeDestino.readOnly = true;
			form.idLocalidadeDestino.style.backgroundColor = '#EFEFEF';
			
			form.codigoSetorComercialOrigem.value = "";
			form.codigoSetorComercialOrigem.style.color = "#000000";
			form.codigoSetorComercialOrigem.readOnly = true;
			form.codigoSetorComercialOrigem.style.backgroundColor = '#EFEFEF';
			
			form.codigoSetorComercialDestino.value = "";
			form.codigoSetorComercialDestino.style.color = "#000000";
			form.codigoSetorComercialDestino.readOnly = true;
			form.codigoSetorComercialDestino.style.backgroundColor = '#EFEFEF';
			
			form.codigoQuadraInicial.value = "";
			form.codigoQuadraInicial.style.color = "#000000";
			form.codigoQuadraInicial.readOnly = true;
			form.codigoQuadraInicial.style.backgroundColor = '#EFEFEF';
			
			form.codigoQuadraFinal.value = "";
			form.codigoQuadraFinal.style.color = "#000000";
			form.codigoQuadraFinal.readOnly = true;
			form.codigoQuadraFinal.style.backgroundColor = '#EFEFEF';
			
		} 
		
		if((form.idsCategoria.value != null && form.idsCategoria.value != ''  && form.idsCategoria.value != '-1')
			|| (form.idsImovelPerfil.value != null && form.idsImovelPerfil.value != ''  && form.idsImovelPerfil.value != '-1')){
		
			form.numeroOSInicial.value = "";
			form.numeroOSInicial.style.color = "#000000";
			form.numeroOSInicial.readOnly = true;
			form.numeroOSInicial.style.backgroundColor = '#EFEFEF';
			
			form.numeroOSFinal.value = "";
			form.numeroOSFinal.disabled = true;
			form.numeroOSFinal.style.color = "#000000";
			form.numeroOSFinal.readOnly = true;
			form.numeroOSFinal.style.backgroundColor = '#EFEFEF';
			
		}  
		
		if((form.idsUnidadeNegocio.value != null && form.idsUnidadeNegocio.value != ''  && form.idsUnidadeNegocio.value != '-1')
				|| (form.idsGerenciaRegional.value != null && form.idsGerenciaRegional.value != ''  && form.idsGerenciaRegional.value != '-1')){
		
			form.idLocalidadeOrigem.value = "";
			form.idLocalidadeOrigem.style.color = "#000000";
			form.idLocalidadeOrigem.readOnly = true;
			form.idLocalidadeOrigem.style.backgroundColor = '#EFEFEF';
			
			form.idLocalidadeDestino.value = "";
			form.idLocalidadeDestino.style.color = "#000000";
			form.idLocalidadeDestino.readOnly = true;
			form.idLocalidadeDestino.style.backgroundColor = '#EFEFEF';
			
			form.codigoSetorComercialOrigem.value = "";
			form.codigoSetorComercialOrigem.style.color = "#000000";
			form.codigoSetorComercialOrigem.readOnly = true;
			form.codigoSetorComercialOrigem.style.backgroundColor = '#EFEFEF';
			
			form.codigoSetorComercialDestino.value = "";
			form.codigoSetorComercialDestino.style.color = "#000000";
			form.codigoSetorComercialDestino.readOnly = true;
			form.codigoSetorComercialDestino.style.backgroundColor = '#EFEFEF';
			
			form.codigoQuadraInicial.value = "";
			form.codigoQuadraInicial.style.color = "#000000";
			form.codigoQuadraInicial.readOnly = true;
			form.codigoQuadraInicial.style.backgroundColor = '#EFEFEF';
			
			form.codigoQuadraFinal.value = "";
			form.codigoQuadraFinal.style.color = "#000000";
			form.codigoQuadraFinal.readOnly = true;
			form.codigoQuadraFinal.style.backgroundColor = '#EFEFEF';
			
			form.numeroOSInicial.value = "";
			form.numeroOSInicial.style.color = "#000000";
			form.numeroOSInicial.readOnly = true;
			form.numeroOSInicial.style.backgroundColor = '#EFEFEF';
			
			form.numeroOSFinal.value = "";
			form.numeroOSFinal.style.color = "#000000";
			form.numeroOSFinal.readOnly = true;
			form.numeroOSFinal.style.backgroundColor = '#EFEFEF';
			
		} 
		
		if(form.idLocalidadeOrigem.value != null && form.idLocalidadeOrigem.value != ''){
		
			form.idsUnidadeNegocio.value = "-1";
			form.idsUnidadeNegocio.selectedIndex = 0;
			form.idsUnidadeNegocio.style.color = "#000000";
			form.idsUnidadeNegocio.disabled = true;
			form.idsUnidadeNegocio.readOnly = true;
			form.idsUnidadeNegocio.style.backgroundColor = '#EFEFEF';
			
			form.idsGerenciaRegional.value = "-1";
			form.idsGerenciaRegional.selectedIndex = 0;
			form.idsGerenciaRegional.style.color = "#000000";
			form.idsGerenciaRegional.disabled = true;
			form.idsGerenciaRegional.readOnly = true;
			form.idsGerenciaRegional.style.backgroundColor = '#EFEFEF';
			
			form.numeroOSInicial.value = "";
			form.numeroOSInicial.style.color = "#000000";
			form.numeroOSInicial.readOnly = true;
			form.numeroOSInicial.style.backgroundColor = '#EFEFEF';
			
			form.numeroOSFinal.value = "";
			form.numeroOSFinal.style.color = "#000000";
			form.numeroOSFinal.readOnly = true;
			form.numeroOSFinal.style.backgroundColor = '#EFEFEF';
			
		} 
		
		if ((form.numeroOSInicial.value == null || form.numeroOSInicial.value == '')
			&& (form.numeroOSFinal.value == null || form.numeroOSFinal.value == '')
			&& (form.idsCategoria.value == null || form.idsCategoria.value == '' || form.idsCategoria.value == '-1')
			&& (form.idsImovelPerfil.value == null || form.idsImovelPerfil.value == '' || form.idsImovelPerfil.value == '-1')
			&& (form.idsUnidadeNegocio.value == null || form.idsUnidadeNegocio.value == '' || form.idsUnidadeNegocio.value == '-1')
			&& (form.idsGerenciaRegional.value == null || form.idsGerenciaRegional.value == '' || form.idsGerenciaRegional.value == '-1')
			&& (form.idLocalidadeOrigem.value == null || form.idLocalidadeOrigem.value == '')) {
		
			form.idsCategoria.style.color = "#000000";
			form.idsCategoria.disabled = false;
			form.idsCategoria.readOnly = false;
			form.idsCategoria.style.backgroundColor = '';
			form.idsImovelPerfil.style.color = "#000000";
			form.idsImovelPerfil.disabled = false;
			form.idsImovelPerfil.readOnly = false;
			form.idsImovelPerfil.style.backgroundColor = '';
			
			form.idsUnidadeNegocio.style.color = "#000000";
			form.idsUnidadeNegocio.disabled = false;
			form.idsUnidadeNegocio.readOnly = false;
			form.idsUnidadeNegocio.style.backgroundColor = '';
			form.idsGerenciaRegional.style.color = "#000000";
			form.idsGerenciaRegional.disabled = false;
			form.idsGerenciaRegional.readOnly = false;
			form.idsGerenciaRegional.style.backgroundColor = '';
			
			form.idLocalidadeOrigem.style.color = "#000000";
			form.idLocalidadeOrigem.readOnly = false;
			form.idLocalidadeOrigem.style.backgroundColor = '';
			form.idLocalidadeDestino.style.color = "#000000";
			form.idLocalidadeDestino.readOnly = false;
			form.idLocalidadeDestino.style.backgroundColor = '';
			
			form.codigoSetorComercialOrigem.style.color = "#000000";
			form.codigoSetorComercialOrigem.readOnly = false;
			form.codigoSetorComercialOrigem.style.backgroundColor = '';
			form.codigoSetorComercialDestino.style.color = "#000000";
			form.codigoSetorComercialDestino.readOnly = false;
			form.codigoSetorComercialDestino.style.backgroundColor = '';
			
			form.codigoQuadraInicial.style.color = "#000000";
			form.codigoQuadraInicial.readOnly = false;
			form.codigoQuadraInicial.style.backgroundColor = '';
			form.codigoQuadraFinal.style.color = "#000000";
			form.codigoQuadraFinal.readOnly = false;
			form.codigoQuadraFinal.style.backgroundColor = '';
			
			form.numeroOSInicial.style.color = "#000000";
			form.numeroOSInicial.readOnly = false;
			form.numeroOSInicial.style.backgroundColor = '';
			form.numeroOSFinal.style.color = "#000000";
			form.numeroOSFinal.readOnly = false;
			form.numeroOSFinal.style.backgroundColor = '';
			
		}
	
	}
	
	
	function limparTotalizacao(){
		var form = document.forms[0];
		
		if (form.totalSelecionado.value != null && form.totalSelecionado.value != "") {
			form.action = 'movimentarOrdemServicoWizardAction.do?action=exibirMovimentarOrdemServicoEmitirOSAction&limparTotalizacao=SIM';
			submeterFormPadrao(form);
		}
	}
	
	
	function pesquisarQuantidadeContas() {
		var form = document.forms[0];
		
		if(validarLocalidade() && validarSetorComercial()){
		
			form.action = 'movimentarOrdemServicoWizardAction.do?action=exibirMovimentarOrdemServicoEmitirOSAction&pesquisarQtdContas=sim';
	    	submeterFormPadrao(form);
	    }
	}
	
	function validarLocalidade(){
		var form = document.forms[0];
		
		if(form.idLocalidadeOrigem.value != null && form.idLocalidadeOrigem.value != '' 
			&& (form.idLocalidadeDestino.value == null || form.idLocalidadeDestino.value == '')){
				
			alert('Informe Localidade Final.');	
			return false;	
				
		}
		
		return true;
	}
	
	function validarSetorComercial(){
		var form = document.forms[0];
		
		if(form.codigoSetorComercialOrigem.value != null && form.codigoSetorComercialOrigem.value != '' 
				&& (form.codigoSetorComercialDestino.value == null || form.codigoSetorComercialDestino.value == '')){
				
			alert('Informe Setor Comercial Final.');
			return false;		
				
		}
		return true;
	}
	
	function validateMovimentarOrdemServicoActionForm(form) {
		var form =  document.forms[0];
		
		return true;
    }
    
    function pesquisarOSEmpresaCobranca() {
    
    	var form =  document.forms[0];
    	
    	form.action = 'movimentarOrdemServicoWizardAction.do?action=exibirMovimentarOrdemServicoEmitirOSAction&pesquisarOSEmpresaCobranca=sim';
    	form.submit();
    	
    }
    
    function pesquisarOSRA(){
    
    	var form =  document.forms[0];
    	
    	form.action = 'movimentarOrdemServicoWizardAction.do?action=exibirMovimentarOrdemServicoEmitirOSAction&pesquisarOSRA=sim';
    	form.submit();
    	
    }
    
</script>

</head>

<body onload="verificaSelecao();bloqueiaDados();">

<html:form
    action="/movimentarOrdemServicoWizardAction"
    method="post"
    onsubmit="return validateMovimentarOrdemServicoActionForm(this);"
>

<jsp:include page="/jsp/util/wizard/navegacao_abas_wizard_valida_avancar.jsp?numeroPagina=1"/>

<%@ include file="/jsp/util/cabecalho.jsp"%>
<%@ include file="/jsp/util/menu.jsp" %>

<table width="770" border="0" cellspacing="5" cellpadding="0">

<input type="hidden" name="numeroPagina" value="1"/>

<html:hidden property="tipoDivEscolhida"/>
<html:hidden property="tipoPesquisa"/>
<html:hidden property="colecaoInformada"/>
<html:hidden property="totalSelecionado"/>
<html:hidden property="idSetorComercialOrigem"/>
<html:hidden property="idSetorComercialDestino"/>

  <tr>
    <td width="123" valign="top" class="leftcoltext">
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
          <td class="parabg">Movimentar Ordem de Serviço - Emitir OS</td>
          <td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif"/></td>
        </tr>
      </table>
    
      <p>&nbsp;</p>
    
      <table border="0" width="100%">
        <tr>
          <td colspan="3">Para emitir OS para comandos de contas em cobrança por empresa, informe os dados abaixo:</td>
        </tr>
        <tr>
          <td width="26%"><strong>Comando de Conta de Cobrança:<font color="#ff0000"></font></strong></td>
          <td width="74%">
			<html:text maxlength="40" property="idComandoContaCobranca" size="20" readonly="true"
						style="background-color:#EFEFEF; border:0; font-color: #000000" />
          </td>
          <td></td>
        </tr>
        
        <tr bgcolor="#99CCFF" align="center">
         	<td colspan="3" bgcolor="#99CCFF">
         		<div align="center">
         			<span class="style2"><a href="javascript:selecionarDiv('1');"><b>O.S. Gerada Tipo Visita para Cobrança</b> </a></span><strong></strong>
        		</div>
       		</td>
        </tr>
         <tr>
			<td colspan="3">
		       <div id="tipoVisitaCobranca" style="display:none;">
			       <table border="0">
			        <tr> 
			          <td width="26%"><strong>Número de OS Inicial:<font color="#ff0000"></font></strong></td>
			          <td width="74%" colspan="2">
						<html:text maxlength="10" property="numeroOSInicial" size="10" 
									onchange="javascript:bloqueiaDados();limparTotalizacao();" />
			          </td>
			        </tr>
			        <tr>
			          <td width="26%"><strong>Número de OS Final:<font color="#ff0000"></font></strong></td>
			          <td width="74%" colspan="2">
						<html:text maxlength="10" property="numeroOSFinal" size="10" 
									onchange="javascript:bloqueiaDados();limparTotalizacao();" />
			          </td>
			        </tr>
			      
					<tr>
						<td colspan="3" width="100%">
						<hr>
						</td>
					</tr>
					<tr>
						<td width="30%"><strong>Categoria:</strong></td>
						<td colspan="2"><html:select property="idsCategoria" tabindex="3" multiple="mutiple" size="3" onchange="javascript:bloqueiaDados();limparTotalizacao();">
							<logic:present name="colecaoCategoria">
								<logic:notEmpty name="colecaoCategoria">
									<option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</option>
									<html:options collection="colecaoCategoria"
										labelProperty="descricao" property="id" />
								</logic:notEmpty>
							</logic:present>
						</html:select></td>
					</tr>
					<tr>
						<td width="30%"><strong>Perfil do Imóvel:</strong></td>
						<td colspan="2"><html:select property="idsImovelPerfil" tabindex="3" multiple="mutiple" size="3" onchange="javascript:bloqueiaDados();limparTotalizacao();">
							<logic:notEmpty name="colecaoImovelPerfil">
								<option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</option>
								<html:options collection="colecaoImovelPerfil"
									labelProperty="descricao" property="id" />
							</logic:notEmpty>
						</html:select></td>
					</tr>
					<tr>
						<td width="30%"><strong>Gerência Regional:</strong></td>
						<td colspan="2"><html:select property="idsGerenciaRegional" tabindex="3" multiple="mutiple" size="4" onchange="javascript:bloqueiaDados();limparTotalizacao();" >
							<logic:notEmpty name="colecaoGerenciaRegional">
								<option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</option>
								<html:options collection="colecaoGerenciaRegional"
									labelProperty="nome" property="id" />
							</logic:notEmpty>
						</html:select></td>
					</tr>
					<tr>
						<td width="30%"><strong>Unidade Negócio:</strong></td>
						<td colspan="2"><html:select property="idsUnidadeNegocio" tabindex="3" multiple="mutiple" size="4" onchange="javascript:bloqueiaDados();limparTotalizacao();" >
							<logic:notEmpty name="colecaoUnidadeNegocio">
								<option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</option>
								<html:options collection="colecaoUnidadeNegocio"
									labelProperty="nome" property="id" />
							</logic:notEmpty>
						</html:select></td>
					</tr>
					<tr>
						<td colspan="3" width="100%">
						<hr>
						</td>
					</tr>
					<tr>
						<td><strong>Localidade Inicial:</strong></td>
						<td colspan="2"><html:text maxlength="3" property="idLocalidadeOrigem" size="3"
							onkeypress="validaEnterComMensagem(event, 'movimentarOrdemServicoWizardAction.do?action=exibirMovimentarOrdemServicoEmitirOSAction&tipoPesquisa=localidadeOrigem', 'idLocalidadeOrigem' ,'Localidade Inicial');"
							tabindex="4" onkeyup="javascript:replicarCampo(form.idLocalidadeDestino, form.idLocalidadeOrigem);limparLocalidadeOrigemTecla();bloqueiaDados();" 
							onchange="javascript:limparTotalizacao();" />
						<a href="javascript:pesquisarLocalidadeOrigem();"> <img width="23"
							height="21" border="0"
							src="<bean:message key="caminho.imagens"/>pesquisa.gif"
							title="Pesquisar Localidade" /></a> <logic:present
							name="localidadeOrigemInexistente" scope="session">
							<html:text property="nomeLocalidadeOrigem" size="40"
								maxlength="40" readonly="true"
								style="border: 0pt none ; background-color:#EFEFEF; color: #ff0000" />
						</logic:present> <logic:notPresent
							name="localidadeOrigemInexistente" scope="session">
							<html:text property="nomeLocalidadeOrigem" size="40"
								maxlength="40" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notPresent> <a href="javascript:limparLocalidadeOrigem();limparTotalizacao();">
						<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
							border="0" title="Apagar Localidade" /></a></td>
					</tr>
					<tr>
						<td><strong>Setor Comercial Inicial:</strong></td>
						<td colspan="2"><html:text maxlength="3" property="codigoSetorComercialOrigem" size="3"
							onkeypress="validaEnterDependenciaComMensagem(event, 'movimentarOrdemServicoWizardAction.do?action=exibirMovimentarOrdemServicoEmitirOSAction&tipoPesquisa=setorComercialOrigem', document.forms[0].codigoSetorComercialOrigem, document.forms[0].idLocalidadeOrigem.value, 'Localidade Inicial', 'Setor Comercial Inicial');"
							tabindex="5" onkeyup="javascript:replicarCampo(form.codigoSetorComercialDestino, form.codigoSetorComercialOrigem);limparSetorComercialOrigemTecla();"
							onchange="javascript:limparTotalizacao();" />
						<a href="javascript:pesquisarSetorComercialOrigem();"> <img
							width="23" height="21" border="0"
							src="<bean:message key="caminho.imagens"/>pesquisa.gif"
							title="Pesquisar Setor Comercial" /></a> <logic:present
							name="setorComercialOrigemInexistente" scope="session">
							<html:text property="descricaoSetorComercialOrigem" size="40"
								maxlength="40" readonly="true"
								style="border: 0pt none ; background-color:#EFEFEF; color: #ff0000" />
						</logic:present> <logic:notPresent
							name="setorComercialOrigemInexistente" scope="session">
							<html:text property="descricaoSetorComercialOrigem" size="40"
								maxlength="40" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notPresent> <a
							href="javascript:limparSetorComercialOrigem();limparTotalizacao();"> <img
							src="<bean:message key="caminho.imagens"/>limparcampo.gif"
							border="0" title="Apagar Setor Comercial" /></a></td>
					</tr>
					
					<tr>
						<td><strong>Quadra Inicial:</strong></td>
						<td colspan="2"><html:text maxlength="4" property="codigoQuadraInicial" size="3"
							onkeypress="validaEnterDependenciaComMensagem(event, 'movimentarOrdemServicoWizardAction.do?action=exibirMovimentarOrdemServicoEmitirOSAction&tipoPesquisa=quadraInicial', document.forms[0].codigoQuadraInicial, document.forms[0].codigoSetorComercialOrigem.value, 'Setor Comercial Inicial','Quadra Inicial');"
							tabindex="8"
							onkeyup="javascript:replicarCampo(form.codigoQuadraFinal, form.codigoQuadraInicial);limparQuadraInicialTecla();" 
							onchange="javascript:limparTotalizacao();" />
							<a href="javascript:pesquisarQuadraInicial();"> <img
								width="23" height="21" border="0"
								src="<bean:message key="caminho.imagens"/>pesquisa.gif"
								title="Pesquisar Quadra" /></a>
							<logic:present name="quadraInicialInexistente" scope="session">
							<html:text property="descricaoQuadraInicial" size="40"
								maxlength="40" readonly="true"
								style="border: 0pt none ; background-color:#EFEFEF; color: #ff0000" />
							</logic:present>
							<logic:notPresent name="quadraInicialInexistente" scope="session">
								<html:text property="descricaoQuadraInicial" size="40"
									maxlength="40" readonly="true"
									style="background-color:#EFEFEF; border:0; color: #000000" />
							</logic:notPresent> <a
								href="javascript:limparQuadraInicial();limparTotalizacao();"> <img
								src="<bean:message key="caminho.imagens"/>limparcampo.gif"
								border="0" title="Apagar Quadra Inicial" /></a>
						</td>
					</tr>
					
					<tr>
						<td><strong>Localidade Final:</strong></td>
						<td colspan="2"><html:text maxlength="3" property="idLocalidadeDestino"
							size="3"
							onkeypress="validaEnterComMensagem(event, 'movimentarOrdemServicoWizardAction.do?action=exibirMovimentarOrdemServicoEmitirOSAction&tipoPesquisa=localidadeDestino', 'idLocalidadeDestino' ,'Localidade Final');bloqueiaDados();"
							tabindex="6" onkeyup="limparLocalidadeDestinoTecla();" 
							onchange="javascript:limparTotalizacao();" /> <a
							href="javascript:pesquisarLocalidadeDestino();"> <img width="23"
							height="21" border="0"
							src="<bean:message key="caminho.imagens"/>pesquisa.gif"
							title="Pesquisar Localidade" /></a> <logic:present
							name="localidadeDestinoInexistente" scope="session">
							<html:text property="nomeLocalidadeDestino" size="40"
								maxlength="40" readonly="true"
								style="border: 0pt none ; background-color:#EFEFEF; color: #ff0000" />
						</logic:present> <logic:notPresent
							name="localidadeDestinoInexistente" scope="session">
							<html:text property="nomeLocalidadeDestino" size="40"
								maxlength="40" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notPresent> <a href="javascript:limparLocalidadeDestino();limparTotalizacao();">
						<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
							border="0" title="Apagar Localidade" /></a></td>
					</tr>
					<tr>
						<td><strong>Setor Comercial Final:</strong></td>
						<td colspan="2"><html:text maxlength="3" property="codigoSetorComercialDestino"
							size="3"
							onkeypress="validaEnterDependenciaComMensagem(event, 'movimentarOrdemServicoWizardAction.do?action=exibirMovimentarOrdemServicoEmitirOSAction&tipoPesquisa=setorComercialDestino', document.forms[0].codigoSetorComercialDestino, document.forms[0].idLocalidadeDestino.value, 'Localidade Final', 'Setor Comercial Final');"
							onchange="javascript:limparTotalizacao();" 
							tabindex="7" onkeyup="limparSetorComercialDestinoTecla();" /> <a
							href="javascript:pesquisarSetorComercialDestino();"> <img
							width="23" height="21" border="0"
							src="<bean:message key="caminho.imagens"/>pesquisa.gif"
							title="Pesquisar Setor Comercial" /></a> <logic:present
							name="setorComercialDestinoInexistente" scope="session">
							<html:text property="descricaoSetorComercialDestino" size="40"
								maxlength="40" readonly="true"
								style="border: 0pt none ; background-color:#EFEFEF; color: #ff0000" />
						</logic:present> <logic:notPresent
							name="setorComercialDestinoInexistente" scope="session">
							<html:text property="descricaoSetorComercialDestino" size="40"
								maxlength="40" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notPresent> <a
							href="javascript:limparSetorComercialDestino();limparTotalizacao();"> <img
							src="<bean:message key="caminho.imagens"/>limparcampo.gif"
							border="0" title="Apagar Setor Comercial" /></a></td>
					</tr>
					
					<tr>
						<td><strong>Quadra Final:</strong></td>
						<td colspan="2"><html:text maxlength="4" property="codigoQuadraFinal" size="3"
							onkeypress="validaEnterDependenciaComMensagem(event, 'movimentarOrdemServicoWizardAction.do?action=exibirMovimentarOrdemServicoEmitirOSAction&tipoPesquisa=quadraFinal', document.forms[0].codigoQuadraFinal, document.forms[0].codigoSetorComercialDestino.value, 'Setor Comercial Final','Quadra Final');"
							tabindex="8" onkeyup="javascript:limparQuadraFinalTecla();" 
							onchange="javascript:limparTotalizacao();" />
							<a href="javascript:pesquisarQuadraFinal();"> <img
								width="23" height="21" border="0"
								src="<bean:message key="caminho.imagens"/>pesquisa.gif"
								title="Pesquisar Quadra" /></a>
							<logic:present name="quadraFinalInexistente" scope="session">
							<html:text property="descricaoQuadraFinal" size="40"
								maxlength="40" readonly="true"
								style="border: 0pt none ; background-color:#EFEFEF; color: #ff0000" />
							</logic:present>
							<logic:notPresent name="quadraFinalInexistente" scope="session">
								<html:text property="descricaoQuadraFinal" size="40"
									maxlength="40" readonly="true"
									style="background-color:#EFEFEF; border:0; color: #000000" />
							</logic:notPresent> <a
								href="javascript:limparQuadraFinal();limparTotalizacao();"> <img
								src="<bean:message key="caminho.imagens"/>limparcampo.gif"
								border="0" title="Apagar Quadra Final" /></a>
						</td>
					</tr>
					
					<tr>
						<td colspan="3" width="100%">
							<hr>
						</td>
					</tr>
        		</table>
        		<table border="0">
					<tr> 
			          <td width="30%" align="left"><strong>Intervalo de Valor de Conta:<font color="#ff0000"></font></strong>
			          </td>
			          <td width="45%" align="center">&nbsp;&nbsp;<strong>
			          		<html:text property="valorMinimo" size="14"
								maxlength="14" tabindex="12"
								onkeyup="formataValorMonetario(this, 14); replicarCampo(document.forms[0].valorMaximo, document.forms[0].valorMinimo);"
								onchange="javascript:limparTotalizacao();" 
								style="text-align:right;" /> a 
							<html:text property="valorMaximo"
								size="14" maxlength="14" tabindex="13"
								onkeyup="formataValorMonetario(this, 14);"
								onchange="javascript:limparTotalizacao();" 
								style="text-align:right;" /> </strong>
							
			          </td>
			          <td width="25%" align="right"><input type="button"
						name="pesquisar" class="bottonRightCol" value="Pesquisar"
						onClick="javascript:pesquisarQuantidadeContas();" /></td>
			        </tr>
					<tr>
						<td colspan="3" width="100%">
							<hr>
						</td>
					</tr>
				</table>
					
        		<table border="0">
					<tr>
						<td colspan="3" align="left">
							<strong>Resultado da Pesquisa:</strong>
						</td>
					</tr>
					<tr>
						<td colspan="3">
						</td>
					</tr>
				</table>
        		<table border="0" width="100%" >
						<logic:present name="colecaoQuantidadeContas" scope="session">
							<tr>
								<td bgcolor="#90c7fc" bordercolor="#90c7fc"></td>
								<td bgcolor="#90c7fc" bordercolor="#90c7fc" align="center" 
									colspan="<bean:write name='tamanho' scope='session'/>">
									<strong>Quantidade de Faturas em Aberto:</strong></td>
								
							</tr>
							
							<tr>
								<td align="center" bgcolor="#99CCFF"><strong></strong></td>
								<logic:iterate name="colecaoFaixa" id="faixa"
									type="String">
										
										<td align="center"  bgcolor="#99CCFF">
											<strong><bean:write name="faixa" /></strong>
										</td>
										
								</logic:iterate>
							</tr>
							
							<tr>
								<td  align="center" bgcolor="#99CCFF"><strong>Quantidade de Contas:</strong></td>
								<logic:iterate name="colecaoQtdeContas" id="quantidadeContas"
									type="Integer">
									
										<td align="center" bgcolor="#FFFFFF">
											<strong><bean:write name="quantidadeContas" /></strong>
										</td>
									
								</logic:iterate>
							</tr>
							
							<tr>
								<td  align="center" bgcolor="#99CCFF"><strong>Quantidade de Clientes:</strong></td>
								<logic:iterate name="colecaoQtdeClientes" id="quantidadeClientes"
									type="Integer">
									
										<td align="center"  bgcolor="#cbe5fe">
											<strong><bean:write name="quantidadeClientes" /></strong>
										</td>
									
								</logic:iterate>
							</tr>
							
							<tr>
								<td  align="center" bgcolor="#99CCFF"><strong>Valor Total da Dívida:</strong></td>
								<logic:iterate name="colecaoValorTotalDivida" id="valorTotalDivida"
									type="BigDecimal">
									
										<td align="center"  bgcolor="#FFFFFF">
											<strong><bean:write name="valorTotalDivida" formatKey="money.format"/></strong>
										</td>
									
								</logic:iterate>
							</tr>
				
						</logic:present>
				
					<logic:notPresent name="colecaoQuantidadeContas" scope="session">
							<tr>
								<td width="28%"><strong>Quantidade de Contas:</strong></td>
								<td colspan="2" width="72%"><html:text property="qtdContas" size="10" maxlength="10"
									readonly="true"
									style="background-color:#EFEFEF; border:0; color: #000000" /></td>
							</tr>
							<tr>
								<td width="28%"><strong>Quantidade de Clientes:</strong></td>
								<td colspan="2" width="72%"><html:text property="qtdClientes" size="10" maxlength="10"
									readonly="true"
									style="background-color:#EFEFEF; border:0; color: #000000" /></td>
							</tr>
							<tr>
								<td width="28%"><strong>Valor Total da Dívida:</strong></td>
								<td colspan="2" width="72%"><html:text property="valorTotalDivida" size="15" maxlength="15"
									readonly="true" 
									style="background-color:#EFEFEF; border:0; color: #000000" /></td>
							</tr>
					</logic:notPresent>
					<tr>
						<td colspan="3">
							&nbsp;
						</td>
					</tr>
					<logic:present name="colecaoQuantidadeContas" scope="session">
						<tr>
							<td width="28%"><strong>Quantidade de Ordens de Serviço:</strong></td>
							<td colspan="<bean:write name='tamanho' scope='session'/>" width="72%" >
								<html:text property="qtdeTotalClientes" size="10" maxlength="10"
									readonly="true" 
									style="background-color:#EFEFEF; border:0; color: #000000" />
							</td>
						</tr>
					</logic:present>
					<logic:notPresent name="colecaoQuantidadeContas" scope="session">
						<tr>
							<td width="28%"><strong>Quantidade de Ordens de Serviço:</strong></td>
							<td colspan="2" width="72%" >
								<html:text property="qtdeTotalClientes" size="10" maxlength="10"
									readonly="true" 
									style="background-color:#EFEFEF; border:0; color: #000000" />
							</td>
						</tr>
					</logic:notPresent>
		        </table>
		       </div>
	        </td>
        </tr>
        <tr bgcolor="#99CCFF" align="center">
         	<td colspan="3" bgcolor="#99CCFF">
         		<div align="center">
         			<span class="style2"><a href="javascript:selecionarDiv('2');"><b>O.S. Gerada pela Empresa Contratada</b> </a></span><strong></strong>
        		</div>
       		</td>
        </tr>
        <tr>
			<td colspan="3">
		       <div id="tipoEmpresaContratada" style="display:none; width: 100%; ">
					<table border="0" width="100%">
					<tr>
						<td colspan="3">
							&nbsp;
						</td>
					</tr>
					<tr>
						<td width="30%"><strong>Tipo de Serviço:<font color="#ff0000"></font></strong></td>
						<td width="45%">
							<html:select property="idTipoServico" tabindex="3" >
								<logic:notEmpty name="colecaoServicoTipo">
									<option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</option>
									<html:options collection="colecaoServicoTipo"
										labelProperty="descricao" property="id" />
								</logic:notEmpty>
							</html:select>
						</td>
						<td width="25%" align="right"><input type="button"
							name="pesquisarOSEmpCob" class="bottonRightCol" value="Pesquisar"
							onClick="javascript:pesquisarOSEmpresaCobranca();" />
						</td>
					</tr>
					<tr>
						<td colspan="3" width="100%">
							<hr>
						</td>
					</tr>
					<tr>
						<td colspan="3">
							&nbsp;
						</td>
					</tr>
					<tr>
						<td colspan="3">
						<table width="100%" bgcolor="#99CCFF" border="0">
							<tr bordercolor="#000000">
								<td width="10%" bgcolor="#90c7fc" align="center"><strong><a
									href="javascript:facilitador(this);">Todos</a></strong></td>
								<td width="15%" bordercolor="#000000" bgcolor="#90c7fc" align="center">
									<div align="center"><strong>Número O.S.</strong></div>
								</td>
								<td width="25%" bgcolor="#90c7fc" align="center"><strong>Tipo Serviço</strong></td>
								<td width="25%" bgcolor="#90c7fc" align="center"><strong>Matrícula</strong></td>
								<td width="25%" bgcolor="#90c7fc" align="center"><strong>Cliente</strong></td>
				
						</table>
						</td>
					</tr>
					<tr>
						<td colspan="3"><%if (session.getAttribute("colecaoOSEmpresaCobranca") == null 
								|| session.getAttribute("colecaoOSEmpresaCobranca").equals("")
								|| ((Collection) session
										.getAttribute("colecaoOSEmpresaCobranca"))
										.size() <= ConstantesSistema.NUMERO_MAXIMO_REGISTROS_EMITIR_OS_COBRANCA_POR_RESULTADO) {%>
							<div style="width: 100%;">
							<%} else { %>
							<div style="width: 100%; height: 200; overflow: auto;">
							<%} %>
							<table width="100%" bgcolor="#99CCFF">
									<logic:present
										name="colecaoOSEmpresaCobranca">
										<%int cont = 0;%>
										<logic:iterate
											name="colecaoOSEmpresaCobranca"
											id="ordemServicoGeradaEmpresaCobrancaHelper"
											type="OrdemServicoGeradaEmpresaCobrancaHelper"
											scope="session">
												<%cont = cont + 1;
										if (cont % 2 == 0) {%>
												<tr bgcolor="#cbe5fe">
													<%} else {%>
												</tr>
												<tr bgcolor="#FFFFFF">
													<%}%>
													<td width="10%">
													<div align="center"><html:checkbox property="numerosOSEmpresaCobranca"
														value="${ordemServicoGeradaEmpresaCobrancaHelper.numeroOS}"  />
													</div>
													</td>
		
													<td align="center" width="15%"><bean:write name="ordemServicoGeradaEmpresaCobrancaHelper" property="numeroOS" /> </td>
		
													<td align="center" width="25%"><bean:write name="ordemServicoGeradaEmpresaCobrancaHelper" property="tipoServico"  /> </td>
		
													<td align="center" width="25%"><bean:write name="ordemServicoGeradaEmpresaCobrancaHelper" property="matriculaImovel"  /> </td>
		
													<td align="center" width="25%"><bean:write name="ordemServicoGeradaEmpresaCobrancaHelper" property="cliente"  /> </td>
		
												</tr>
										</logic:iterate>
									</logic:present>
									<logic:notPresent
										name="colecaoOSEmpresaCobranca">
										
									</logic:notPresent>
								</table>
							</div>
						</td>
					</tr>
							
					<tr>
						<td colspan="3" rowspan="3">
							&nbsp;
						</td>
					</tr>
					</table>
		       </div>
	        </td>
        </tr>
					
        <tr bgcolor="#99CCFF" align="center">
         	<td colspan="3" bgcolor="#99CCFF">
         		<div align="center">
         			<span class="style2"><a href="javascript:selecionarDiv('3');"><b>O.S. Gerada Através de uma R.A.</b> </a></span><strong></strong>
        		</div>
       		</td>
        </tr>
        <tr>
			<td colspan="3">
		       <div id="tipoAtravesRA" style="display:none; width: 100%">
			      <table border="0" width="100%">
					<tr>
						<td colspan="3">
							&nbsp;
						</td>
					</tr>
					<tr>
						<td width="30%"><strong>Tipo de Serviço:<font color="#ff0000"></font></strong></td>
						<td width="45%">
							<html:select property="idTipoServicoRA" tabindex="3" >
								<logic:notEmpty name="colecaoServicoTipo">
									<option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</option>
									<html:options collection="colecaoServicoTipo"
										labelProperty="descricao" property="id" />
								</logic:notEmpty>
							</html:select>
						</td>
						<td width="25%" align="right"><input type="button"
							name="pesquisarOSEmpCob" class="bottonRightCol" value="Pesquisar"
							onClick="javascript:pesquisarOSRA();" />
						</td>
					</tr>
					<tr>
						<td colspan="3" width="100%">
							<hr>
						</td>
					</tr>
					<tr>
						<td colspan="3">
							&nbsp;
						</td>
					</tr>
					<tr>
						<td colspan="3">
						<table width="100%" bgcolor="#99CCFF" border="0">
							<tr bordercolor="#000000">
								<td width="10%" bgcolor="#90c7fc" align="center"><strong><a
									href="javascript:facilitador(this);">Todos</a></strong></td>
								<td width="15%" bordercolor="#000000" bgcolor="#90c7fc" align="center">
									<div align="center"><strong>Número O.S.</strong></div>
								</td>
								<td width="25%" bgcolor="#90c7fc" align="center"><strong>Tipo Serviço</strong></td>
								<td width="25%" bgcolor="#90c7fc" align="center"><strong>Matrícula</strong></td>
								<td width="25%" bgcolor="#90c7fc" align="center"><strong>Cliente</strong></td>
				
						</table>
						</td>
					</tr>
					<tr>
						<td colspan="3"><%if (session.getAttribute("colecaoOSRegistroAtendimento") == null 
								|| session.getAttribute("colecaoOSRegistroAtendimento").equals("")
								|| ((Collection) session
										.getAttribute("colecaoOSRegistroAtendimento"))
										.size() <= ConstantesSistema.NUMERO_MAXIMO_REGISTROS_EMITIR_OS_COBRANCA_POR_RESULTADO) {%>
							<div style="width: 100%;">
							<%} else { %>
							<div style="width: 100%; height: 200; overflow: auto;">
							<%} %>
							<table width="100%" bgcolor="#99CCFF">
									<logic:present
										name="colecaoOSRegistroAtendimento">
										<%int cont1 = 0;%>
										<logic:iterate
											name="colecaoOSRegistroAtendimento"
											id="ordemServicoGeradaRAHelper"
											type="OrdemServicoGeradaEmpresaCobrancaHelper"
											scope="session">
												<%cont1 = cont1 + 1;
										if (cont1 % 2 == 0) {%>
												<tr bgcolor="#cbe5fe">
													<%} else {%>
												</tr>
												<tr bgcolor="#FFFFFF">
													<%}%>
													<td width="10%">
													<div align="center"><html:checkbox property="numerosOSRegistroAtendimento"
														value="${ordemServicoGeradaRAHelper.numeroOS}"  />
													</div>
													</td>
		
													<td align="center" width="15%"><bean:write name="ordemServicoGeradaRAHelper" property="numeroOS" /> </td>
		
													<td align="center" width="25%"><bean:write name="ordemServicoGeradaRAHelper" property="tipoServico"  /> </td>
		
													<td align="center" width="25%"><bean:write name="ordemServicoGeradaRAHelper" property="matriculaImovel"  /> </td>
		
													<td align="center" width="25%"><bean:write name="ordemServicoGeradaRAHelper" property="cliente"  /> </td>
		
												</tr>
										</logic:iterate>
									</logic:present>
									<logic:notPresent
										name="colecaoOSEmpresaCobranca">
										
									</logic:notPresent>
								</table>
							</div>
						</td>
					</tr>
							
					<tr>
						<td colspan="3" rowspan="3">
							&nbsp;
						</td>
					</tr>
					</table>
		       </div>
	        </td>
        </tr>
        
        <tr>
          <td colspan="3"><div align="right"><jsp:include page="/jsp/util/wizard/navegacao_botoes_wizard_valida_avancar.jsp?numeroPagina=1"/></div></td>
        </tr>
        
     </table>
   </td>
 </tr>
</table>

<%@ include file="/jsp/util/rodape.jsp"%>

</html:form>
</body>
</html:html>
