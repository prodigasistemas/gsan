<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>

<%@ include file="/jsp/util/telaespera.jsp"%>

<%@ page import="gcom.util.ConstantesSistema"%>
<%@ page import="java.math.BigDecimal"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>

<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false" formName="InformarContasEmCobrancaActionForm" />
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js"></script>

<SCRIPT LANGUAGE="JavaScript">

	function limparEmpresa() {
		var form = document.forms[0];
		form.idEmpresa.value = "";
		form.nomeEmpresa.value = "";	
	}
	
	function limparEmpresaTecla() {
		var form = document.forms[0];
		form.nomeEmpresa.value = "";	
	}
	
	function limparImovel() {
		var form = document.forms[0];
		
		form.idImovel.value = "";
		form.inscricaoImovel.value = "";	
		
		bloqueiaDados(); 
	}
	
	function limparImovelTecla() {
		var form = document.forms[0];
		form.inscricaoImovel.value = "";	
	}
	
	function limparCliente() {
		var form = document.forms[0];
		
		form.idCliente.value = "";
		form.nomeCliente.value = "";	
		
		bloqueiaDados(); 
	}
	
	function limparClienteTecla() {
		var form = document.forms[0];
		form.nomeCliente.value = "";	
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
		form.codigoSetorComercialOrigem.value = "";
		form.descricaoSetorComercialOrigem.value = "";
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
	
	function limparServicoTipo() {
		var form = document.forms[0];
		form.idServicoTipo.value = "";
		form.descricaoServicoTipo.value = "";
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
	
	function pesquisarEmpresa() {
		var form = document.forms[0];

		if (form.idEmpresa.disabled == false)  {
			abrirPopup('exibirPesquisarEmpresaAction.do?limpaForm=S', 495, 300);
		}
	}
	
	function pesquisarQuantidadeContas() {
		var form = document.forms[0];
		
		if(validarLocalidade() && validarSetorComercial() && validarEmpresa() && validarQuantidadeDiasVencimento() && validarQuantidadeDeContas() && validarValorContas()){
		
			form.action = 'exibirInformarContasEmCobrancaAction.do?pesquisarQtdContas=sim';
	    	
	    	botaoAvancarTelaEspera('exibirInformarContasEmCobrancaAction.do?pesquisarQtdContas=sim');
	    }
	}
	
	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {

    	var form = document.forms[0];
   
	    if (tipoConsulta == 'empresa') {
			form.idEmpresa.value = codigoRegistro;
			form.nomeEmpresa.value = descricaoRegistro;
			form.nomeEmpresa.style.color = "#000000";
			bloqueiaDados();
    	} else if (tipoConsulta == 'imovel') {
    		form.idImovel.value = codigoRegistro;
			form.inscricaoImovel.value = descricaoRegistro;
			form.inscricaoImovel.style.color = "#000000";
			bloqueiaDados();
    	} else if (tipoConsulta == 'cliente') {
    		form.idCliente.value = codigoRegistro;
			form.nomeCliente.value = descricaoRegistro;
			form.nomeCliente.style.color = "#000000";
			bloqueiaDados();
    	} else if (tipoConsulta == 'localidade') {
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
    	} else if (tipoConsulta == 'tipoServico') {
			form.idServicoTipo.value = codigoRegistro;
			form.descricaoServicoTipo.value = descricaoRegistro;
			form.descricaoServicoTipo.style.color = "#000000";
    	}
    
    }
    
	function gerarDadosCobranca(){
		var form = document.forms[0];
		form.action = 'informarContasEmCobrancaAction.do';
	    submeterFormPadrao(form);
	}
	
	function bloqueiaDados(){
	
		var form = document.forms[0];
		
		if (form.idImovel.value != null && form.idImovel.value != ''){
		
			form.idLocalidadeOrigem.value = "";
			form.idLocalidadeOrigem.disabled = true;
			form.idLocalidadeOrigem.value = "";
			form.idLocalidadeOrigem.disabled = true;
			form.idCliente.value = "";
			form.idCliente.disabled = true;
			form.idLocalidadeDestino.value = "";
			form.idLocalidadeDestino.disabled = true;
			form.codigoSetorComercialOrigem.value = "";
			form.codigoSetorComercialOrigem.disabled = true;
			form.codigoSetorComercialDestino.value = "";
			form.codigoSetorComercialDestino.disabled = true;
			form.idsCategoria.value = "-1";
			form.idsCategoria.selectedIndex = 0;
			form.idsCategoria.disabled = true;
			form.idsImovelPerfil.value = "-1";
			form.idsImovelPerfil.selectedIndex = 0;
			form.idsImovelPerfil.disabled = true;
			form.idsUnidadeNegocio.value = "-1";
			form.idsUnidadeNegocio.selectedIndex = 0;
			form.idsUnidadeNegocio.disabled = true;
			form.idsGerenciaRegional.value = "-1";
			form.idsGerenciaRegional.selectedIndex = 0;
			form.idsGerenciaRegional.disabled = true;
			form.codigoQuadraInicial.value = "";
			form.codigoQuadraInicial.disabled = true;
			form.codigoQuadraFinal.value = "";
			form.codigoQuadraFinal.disabled = true;
			form.idsLigacaoAguaSituacao.value = "-1";
			form.idsLigacaoAguaSituacao.selectedIndex = 0;
			form.idsLigacaoAguaSituacao.disabled = true;
			
		}else if(form.idCliente.value != null && form.idCliente.value != ''){
		
			form.idImovel.value = "";
			form.idImovel.disabled = true;
			form.idLocalidadeOrigem.value = "";
			form.idLocalidadeOrigem.disabled = true;
			form.idLocalidadeDestino.value = "";
			form.idLocalidadeDestino.disabled = true;
			form.codigoSetorComercialOrigem.value = "";
			form.codigoSetorComercialOrigem.disabled = true;
			form.codigoSetorComercialDestino.value = "";
			form.codigoSetorComercialDestino.disabled = true;
			form.idsCategoria.value = "-1";
			form.idsCategoria.selectedIndex = 0;
			form.idsCategoria.disabled = true;
			form.idsImovelPerfil.value = "-1";
			form.idsImovelPerfil.selectedIndex = 0;
			form.idsImovelPerfil.disabled = true;
			form.idsUnidadeNegocio.value = "-1";
			form.idsUnidadeNegocio.selectedIndex = 0;
			form.idsUnidadeNegocio.disabled = true;
			form.idsGerenciaRegional.value = "-1";
			form.idsGerenciaRegional.selectedIndex = 0;
			form.idsGerenciaRegional.disabled = true;
			form.codigoQuadraInicial.value = "";
			form.codigoQuadraInicial.disabled = true;
			form.codigoQuadraFinal.value = "";
			form.codigoQuadraFinal.disabled = true;
			form.idsLigacaoAguaSituacao.value = "-1";
			form.idsLigacaoAguaSituacao.selectedIndex = 0;
			form.idsLigacaoAguaSituacao.disabled = true;
			
		} else if (form.idsCategoria.value != null && form.idsCategoria.value != '' && form.idsCategoria.value != '-1') {
		
			form.idImovel.value = "";
			form.idImovel.disabled = true;
			form.idCliente.value = "";
			form.idCliente.disabled = true;
			
		} else if(form.idsUnidadeNegocio.value != null && form.idsUnidadeNegocio.value != ''  && form.idsUnidadeNegocio.value != '-1'){
		
			form.idImovel.value = "";
			form.idImovel.disabled = true;
			form.idLocalidadeOrigem.value = "";
			form.idLocalidadeOrigem.disabled = true;
			form.idLocalidadeDestino.value = "";
			form.idLocalidadeDestino.disabled = true;
			form.idCliente.value = "";
			form.idCliente.disabled = true;
			form.codigoSetorComercialOrigem.value = "";
			form.codigoSetorComercialOrigem.disabled = true;
			form.codigoSetorComercialDestino.value = "";
			form.codigoSetorComercialDestino.disabled = true;
			form.codigoQuadraInicial.value = "";
			form.codigoQuadraInicial.disabled = true;
			form.codigoQuadraFinal.value = "";
			form.codigoQuadraFinal.disabled = true;
			
		} else if(form.idsGerenciaRegional.value != null && form.idsGerenciaRegional.value != ''  && form.idsGerenciaRegional.value != '-1'){
		
			form.idImovel.value = "";
			form.idImovel.disabled = true;
			form.idLocalidadeOrigem.value = "";
			form.idLocalidadeOrigem.disabled = true;
			form.idLocalidadeDestino.value = "";
			form.idLocalidadeDestino.disabled = true;
			form.idCliente.value = "";
			form.idCliente.disabled = true;
			form.codigoSetorComercialOrigem.value = "";
			form.codigoSetorComercialOrigem.disabled = true;
			form.codigoSetorComercialDestino.value = "";
			form.codigoSetorComercialDestino.disabled = true;
			form.codigoQuadraInicial.value = "";
			form.codigoQuadraInicial.disabled = true;
			form.codigoQuadraFinal.value = "";
			form.codigoQuadraFinal.disabled = true;
			
		} else if(form.idLocalidadeOrigem.value != null && form.idLocalidadeOrigem.value != ''){
		
			form.idImovel.value = "";
			form.idImovel.disabled = true;
			form.idCliente.value = "";
			form.idCliente.disabled = true;
			form.idsUnidadeNegocio.value = "-1";
			form.idsUnidadeNegocio.disabled = true;
			form.idsUnidadeNegocio.selectedIndex = 0;
			form.idsCategoria.value = "-1";
			form.idsCategoria.selectedIndex = 0;
			form.idsGerenciaRegional.value = "-1";
			form.idsGerenciaRegional.disabled = true;
			form.idsGerenciaRegional.selectedIndex = 0;
			
		} else if(form.idsLigacaoAguaSituacao.value != null 
			&& form.idsLigacaoAguaSituacao.value != ''  
			&& form.idsLigacaoAguaSituacao.value != '-1'){
		
			form.idImovel.value = "";
			form.idImovel.disabled = true;
			form.idCliente.value = "";
			form.idCliente.disabled = true;
			
		}  else {
		
			form.idsCategoria.disabled = false;
			form.idsUnidadeNegocio.disabled = false;
			form.idsGerenciaRegional.disabled = false;
			form.idImovel.disabled = false;
			form.idCliente.disabled = false;
			form.idLocalidadeOrigem.disabled = false;
			form.idLocalidadeDestino.disabled = false;
			form.codigoSetorComercialOrigem.disabled = false;
			form.codigoSetorComercialDestino.disabled = false;
			form.codigoQuadraInicial.disabled = false;
			form.codigoQuadraFinal.disabled = false;
			form.idsImovelPerfil.disabled = false;
			form.idsLigacaoAguaSituacao.disabled = false;
			
		}
	
	
	}
	
	function validarLocalidade(){
		var form = document.forms[0];
		
		if((form.idsUnidadeNegocio.value == null || form.idsUnidadeNegocio.value == '' || form.idsUnidadeNegocio.value == '-1') 
			&& (form.idsGerenciaRegional.value == null || form.idsGerenciaRegional.value == '' || form.idsGerenciaRegional.value == '-1') 
			&& (form.idImovel.value == null || form.idImovel.value == '')
			&& (form.idCliente.value == null || form.idCliente.value == '')){
			
			if(form.idLocalidadeOrigem.value != null && form.idLocalidadeOrigem.value != '' 
				&& (form.idLocalidadeDestino.value == null || form.idLocalidadeDestino.value == '')){
					
				alert('Informe Localidade Final.');	
				return false;	
					
			}else if ((form.idLocalidadeDestino.value != null || form.idLocalidadeDestino.value != '')
						&& (form.idLocalidadeOrigem.value == null || form.idLocalidadeOrigem.value == '')){		
				
				alert('Informe Localidade Inicial.');	
				return false;
				
			}else{
				return true;
			}
			
		}else{
				return true;
			}
	}
	
	function validarSetorComercial(){
		var form = document.forms[0];
		
		if((form.idsUnidadeNegocio.value == null || form.idsUnidadeNegocio.value == '' || form.idsUnidadeNegocio.value == '-1') 
			&& (form.idImovel.value == null || form.idImovel.value == '')
			&& (form.idCliente.value == null || form.idCliente.value == '')){
		
			if(form.codigoSetorComercialOrigem.value != null && form.codigoSetorComercialOrigem.value != '' 
					&& (form.codigoSetorComercialDestino.value == null || form.codigoSetorComercialDestino.value == '')){
					
				alert('Informe Setor Comercial Final.');
				return false;		
					
			}else if (form.codigoSetorComercialOrigem.value != null && form.codigoSetorComercialOrigem.value != '' 
					&& (form.codigoSetorComercialDestino.value == null || form.codigoSetorComercialDestino.value == '')){		
				
				alert('Informe Setor Comercial Inicial.');	
				return false;
				
			}else{
				return true;
			}
		}else{
				return true;
			}
	}

	function validarQuantidadeDiasVencimento(){
		var form = document.forms[0];

		if((form.idImovel.value == null || form.idImovel.value == '') && (form.idCliente.value == null || form.idCliente.value == '')){
			
			if(form.quantidadeDiasVencimento.value == null || form.quantidadeDiasVencimento.value == ''){
					
				alert('Informe a Quantidade de Dias de Vencimento.');	
				return false;	
					
			} else {
				return true;
			}
			
		}else{
				return true;
		}
	}

	function validarQuantidadeDeContas(){
		var form = document.forms[0];
		
		if((form.idImovel.value == null || form.idImovel.value == '') && (form.idCliente.value == null || form.idCliente.value == '')){

			if((form.quantidadeContasInicial.value == null && form.quantidadeContasInicial.value != '')
				|| (form.quantidadeContasFinal.value == null || form.quantidadeContasFinal.value == '')){
					
				alert('Informe a Quantidade Inicial e Final de Contas.');
				return false;		
					
			}else{
				return true;
			}
		}else{
				return true;
			}
	}
	
	function validarValorContas(){
		var form = document.forms[0];
		
		if((form.idImovel.value == null || form.idImovel.value == '') && (form.idCliente.value == null || form.idCliente.value == '')){
		
			if((form.valorMinimo.value == null && form.valorMinimo.value != '')
				|| (form.valorMaximo.value == null || form.valorMaximo.value == '')){
					
				alert('Informe o Valor Mínimo e Máximo da Conta.');
				return false;		
					
			}else{
				return true;
			}
		}else{
				return true;
			}
	}
	function desabilitaEmpresa(){
		var form = document.forms[0];
		
		form.qtdContas.value = '';
		form.qtdClientes.value = '';
		form.valorTotalDivida.value = '';
		form.idEmpresa.value = '';
		form.idEmpresa.disabled = true;
	}
	
	function liberaEmpresa(){
		var form = document.forms[0];
		
		if(form.liberaEmpresa.value == 'sim'){
			
			form.idEmpresa.disabled = false;
		
		}else{
			form.idEmpresa.disabled = true;
		
		}
	
	}
	
	function verificarBloqueioEmpresa() {
	
		var form = document.forms[0];
	
		if (form.qtdContas.value != null && form.qtdContas.value != '' && form.qtdContas.value != '0') {
			form.idEmpresa.disabled = false;
		} else {
			form.idEmpresa.value = '';
			form.idEmpresa.disabled = true;
		}
	}
	
	function liberaGerarDadosCobranca(){
	
		var form = document.forms[0];
		
		if (form.qtdContas.value != null && form.qtdContas.value != '' && form.qtdContas.value != '0') {
			form.dataInicioCiclo.value = "";
			form.dataInicioCiclo.disabled = false;
			form.quantidadeDiasCiclo.value = "";
			form.quantidadeDiasCiclo.disabled = false;
			form.gerarDadosCobranca.disabled = false;
		}else{
			form.dataInicioCiclo.disabled = true;
			form.quantidadeDiasCiclo.disabled = true;
			form.gerarDadosCobranca.disabled = true;
		}
	
	}
	
	function validarInformacaoSetorComercial(){
		var form =  document.forms[0];
		
		if(form.codigoSetorComercialOrigem.value !=null && form.codigoSetorComercialOrigem.value != ''
				&& form.idLocalidadeOrigem.value != form.idLocalidadeDestino.value){
			alert('A Localidade Final não pode ser diferente da Localidade Inicial');
			return false;
		
		}else{
		
		return true;
		}
	
	}
	
	
	function validaForm() {
		var form =  document.forms[0];
		var submeterForm = true;
		
		if(form.idEmpresa.value != null && form.idEmpresa.value != '') {
			if (form.colecaoInformada.value != null && form.colecaoInformada.value != '') {
				if (form.dataInicioCiclo.value == null || form.dataInicioCiclo.value == ''){
						submeterForm = false;
						alert('Informe a Data Início do Ciclo');
				} else if (form.quantidadeDiasCiclo.value == null || form.quantidadeDiasCiclo.value == '') {
						submeterForm = false;
						alert('Informe a Quantidade de Dias do Ciclo');
				} else if (form.idServicoTipo.value == null || form.idServicoTipo.value == '') {
						submeterForm = false;
						alert('Informe o Tipo de Serviço');
				} else if (!verificaData(form.dataInicioCiclo)){
					submeterForm = false;
				} else if (comparaDataComDataAtual(form.dataInicioCiclo.value, "<")) {
					submeterForm = false;
					alert('Data Início do Ciclo deve ser maior ou igual a data corrente.');
				} else if (!validarCampoNumericoComMensagem(form.quantidadeDiasCiclo, "Quantidade de Dias do Ciclo")) {
					submeterForm = false;
				}
			} else if (form.dataInicioCiclo.value != null && form.dataInicioCiclo.value != '') {
				if (!verificaData(form.dataInicioCiclo)){
					submeterForm = false;
				} else if (comparaDataComDataAtual(form.dataInicioCiclo.value, "<")) {
					submeterForm = false;
					alert('Data Início do Ciclo deve ser maior ou igual a data corrente.');
				} else if (form.quantidadeDiasCiclo.value != null && form.quantidadeDiasCiclo.value != ''
						&& validarCampoNumericoComMensagem(form.quantidadeDiasCiclo, "Quantidade de Dias do Ciclo")) {
					submeterForm = true;
				}
			}
			
		}else{
			submeterForm = false;
			alert('Informe Empresa');
		}	
		
		if (submeterForm) {
			if (validateInformarContasEmCobrancaActionForm(form) && validarInformacaoSetorComercial()){
					form.action = 'informarContasEmCobrancaAction.do';
					submeterFormPadrao(form);
			}
		}
	}
	
	function validarEmpresa(){
		var form = document.forms[0];
		
		if(form.idEmpresa.value == null || form.idEmpresa.value == ''){
				
			alert('Informe a Empresa.');
			return false;		
				
		}
		
		return true;
		
	}
	
	function limparTotalizacao(){
		var form = document.forms[0];
		
		if (form.totalSelecionado.value != null && form.totalSelecionado.value != "") {
			form.action = 'exibirInformarContasEmCobrancaAction.do?limparTotalizacao=SIM';
			submeterFormPadrao(form);
		}
	}
	
</script>

</head>

<body leftmargin="5" topmargin="5" onload="javascript:setarFoco('${requestScope.nomeCampo}');bloqueiaDados();liberaGerarDadosCobranca();">

<div id="formDiv">
<html:form action="/exibirInformarContasEmCobrancaAction"
	name="InformarContasEmCobrancaActionForm"
	type="gcom.gui.cobranca.InformarContasEmCobrancaActionForm"
	method="post">

	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>
	
	<table width="770" border="0" cellspacing="5" cellpadding="0">
		<tr>
			<td width="150" valign="top" class="leftcoltext">
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
			<td width="625" valign="top" bgcolor="#003399" class="centercoltext">
			<table height="100%">

				<tr>
					<td></td>
				</tr>
			</table>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img border="0" src="imagens/parahead_left.gif" /></td>
					<td class="parabg">Informar Contas em Cobrança por Empresa</td>
					<td width="11" valign="top"><img border="0"
						src="imagens/parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<html:hidden property="colecaoInformada"/>
			<html:hidden property="totalSelecionado"/>
			
			<input type="hidden" name="tipoPesquisa" />
			<table width="100%" border="0">
				<tr>
					<td colspan="2">Para gerar o comando das contas em cobrança por empresa, informe os dados abaixo:</td>
				</tr>
				
				<tr><td colspan="2"></td></tr>
				
				<tr>
					<td width="30%"><strong>Cobrança por Telemarketing:</strong></td>
					<td colspan="6">
						<span class="style2">
							<strong>
								<label><html:radio property="indicadorCobrancaTelemarketing" value="1"/>Sim</label>
 				  				<label><html:radio property="indicadorCobrancaTelemarketing" value="2"/>Não</label>
                  			</strong>
                  		</span>
					</td>
				</tr>
				
				<tr><td colspan="2"><hr></td></tr>
				
				<tr>
					<td width="30%"><strong>Imóvel:</strong></td>
					<td><html:text maxlength="9" property="idImovel" size="9"
						tabindex="1" onchange="javascript:limparTotalizacao();"
						onkeyup="validaEnterComMensagem(event, 'exibirInformarContasEmCobrancaAction.do', 'idImovel', 'Imóvel'); limparImovelTecla();bloqueiaDados();" />
					<a
						href="javascript:abrirPopup('exibirPesquisarImovelAction.do?limpaForm=S', 495, 300);"><img
						src="<bean:message key='caminho.imagens'/>pesquisa.gif" width="23"
						height="21" alt="Pesquisar" border="0"></a> <logic:present
						name="imovelInexistente" scope="request">
						<html:text property="inscricaoImovel" size="37" maxlength="37"
							readonly="true"
							style="border: 0pt none ; background-color:#EFEFEF; color: #ff0000" />
					</logic:present> <logic:notPresent name="imovelInexistente"
						scope="request">
						<html:text property="inscricaoImovel" size="37" maxlength="37"
							readonly="true"
							style="background-color:#EFEFEF; border:0; color: #000000" />
					</logic:notPresent> <a href="javascript:limparImovel();limparTotalizacao();"> <img
						border="0" src="imagens/limparcampo.gif" height="21" width="23"> </a>
					</td>
				</tr>
				<tr>
					<td width="30%"><strong>Cliente:</strong></td>
					<td><html:text maxlength="9" property="idCliente" size="9"
						tabindex="2" onchange="javascript:limparTotalizacao();"
						onkeyup="validaEnterComMensagem(event, 'exibirInformarContasEmCobrancaAction.do', 'idCliente', 'Cliente'); limparClienteTecla();bloqueiaDados();" />
					<a
						href="javascript:abrirPopup('exibirPesquisarClienteAction.do?limpaForm=S', 495, 300);"><img
						src="<bean:message key='caminho.imagens'/>pesquisa.gif" width="23"
						height="21" alt="Pesquisar" border="0"></a> <logic:present
						name="clienteInexistente" scope="request">
						<html:text property="nomeCliente" size="37" maxlength="37"
							readonly="true"
							style="border: 0pt none ; background-color:#EFEFEF; color: #ff0000" />
					</logic:present> <logic:notPresent name="clienteInexistente"
						scope="request">
						<html:text property="nomeCliente" size="37" maxlength="37"
							readonly="true"
							style="background-color:#EFEFEF; border:0; color: #000000" />
					</logic:notPresent> <a href="javascript:limparCliente();limparTotalizacao();"> <img
						border="0" src="imagens/limparcampo.gif" height="21" width="23"> </a>
					</td>
				</tr>
				<tr>
					<td colspan="2">
					<hr>
					</td>
				</tr>
				<tr>
					<td width="30%"><strong>Categoria:</strong></td>
					<td><html:select property="idsCategoria" tabindex="3" onchange="javascript:bloqueiaDados();limparTotalizacao();" multiple="mutiple" size="4">
						<logic:notEmpty name="colecaoCategoria">
							<option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</option>
							<html:options collection="colecaoCategoria"
								labelProperty="descricao" property="id" />
						</logic:notEmpty>
					</html:select></td>
				</tr>
				<tr>
					<td colspan="2">
					<hr>
					</td>
				</tr>
				<tr>
					<td width="30%"><strong>Perfil do Imóvel:</strong></td>
					<td><html:select property="idsImovelPerfil" tabindex="4" multiple="mutiple" size="4" onchange="javascript:limparTotalizacao();">
						<logic:notEmpty name="colecaoImovelPerfil">
							<option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</option>
							<html:options collection="colecaoImovelPerfil"
								labelProperty="descricao" property="id" />
						</logic:notEmpty>
					</html:select></td>
				</tr>
				<tr>
					<td width="30%"><strong>Gerência Regional:</strong></td>
					<td><html:select property="idsGerenciaRegional" tabindex="5" multiple="mutiple" size="4" onchange="javascript:bloqueiaDados();limparTotalizacao();">
						<logic:notEmpty name="colecaoGerenciaRegional">
							<option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</option>
							<html:options collection="colecaoGerenciaRegional"
								labelProperty="nome" property="id" />
						</logic:notEmpty>
					</html:select></td>
				</tr>
				<tr>
					<td width="30%"><strong>Unidade Negócio:</strong></td>
					<td><html:select property="idsUnidadeNegocio" tabindex="6" multiple="mutiple" size="4" onchange="javascript:bloqueiaDados();limparTotalizacao();">
						<logic:notEmpty name="colecaoUnidadeNegocio">
							<option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</option>
							<html:options collection="colecaoUnidadeNegocio"
								labelProperty="nome" property="id" />
						</logic:notEmpty>
					</html:select></td>
				</tr>
				<tr>
					<td width="30%"><strong>Situação da Ligação de Água:</strong></td>
					<td><html:select property="idsLigacaoAguaSituacao" 
						tabindex="7" multiple="mutiple" size="4" 
						onchange="javascript:bloqueiaDados();limparTotalizacao();">
						<logic:notEmpty name="colecaoLigacaoAguaSituacao">
							<option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</option>
							<html:options collection="colecaoLigacaoAguaSituacao"
								labelProperty="descricao" property="id" />
						</logic:notEmpty>
					</html:select></td>
				</tr>
				<tr>
					<td colspan="2">
					<hr>
					</td>
				</tr>
				<tr>
					<td><strong>Localidade Inicial:</strong></td>
					<td><html:text maxlength="3" property="idLocalidadeOrigem" size="3"
						onkeypress="validaEnterComMensagem(event, 'exibirInformarContasEmCobrancaAction.do?tipoPesquisa=localidadeOrigem', 'idLocalidadeOrigem' ,'Localidade Inicial');"
						tabindex="8" onkeyup="javascript:replicarCampo(form.idLocalidadeDestino, form.idLocalidadeOrigem);limparLocalidadeOrigemTecla();bloqueiaDados();" 
						onchange="javascript:limparTotalizacao();" />
					<a href="javascript:pesquisarLocalidadeOrigem();"> <img width="23"
						height="21" border="0"
						src="<bean:message key="caminho.imagens"/>pesquisa.gif"
						title="Pesquisar Localidade" /></a> <logic:present
						name="localidadeOrigemInexistente" scope="request">
						<html:text property="nomeLocalidadeOrigem" size="40"
							maxlength="40" readonly="true"
							style="border: 0pt none ; background-color:#EFEFEF; color: #ff0000" />
					</logic:present> <logic:notPresent
						name="localidadeOrigemInexistente" scope="request">
						<html:text property="nomeLocalidadeOrigem" size="40"
							maxlength="40" readonly="true"
							style="background-color:#EFEFEF; border:0; color: #000000" />
					</logic:notPresent> <a href="javascript:limparLocalidadeOrigem();limparTotalizacao();">
					<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar Localidade" /></a></td>
				</tr>
				<tr>
					<td><strong>Setor Comercial Inicial:</strong></td>
					<td><html:text maxlength="3" property="codigoSetorComercialOrigem" size="3"
						onkeypress="validaEnterDependenciaComMensagem(event, 'exibirInformarContasEmCobrancaAction.do?tipoPesquisa=setorComercialOrigem', document.forms[0].codigoSetorComercialOrigem, document.forms[0].idLocalidadeOrigem.value, 'Localidade Inicial', 'Setor Comercial Inicial');"
						tabindex="9" onkeyup="javascript:replicarCampo(form.codigoSetorComercialDestino, form.codigoSetorComercialOrigem);limparSetorComercialOrigemTecla();"
						onchange="javascript:limparTotalizacao();" />
					<a href="javascript:pesquisarSetorComercialOrigem();"> <img
						width="23" height="21" border="0"
						src="<bean:message key="caminho.imagens"/>pesquisa.gif"
						title="Pesquisar Setor Comercial" /></a> <logic:present
						name="setorComercialOrigemInexistente" scope="request">
						<html:text property="descricaoSetorComercialOrigem" size="40"
							maxlength="40" readonly="true"
							style="border: 0pt none ; background-color:#EFEFEF; color: #ff0000" />
					</logic:present> <logic:notPresent
						name="setorComercialOrigemInexistente" scope="request">
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
					<td><html:text maxlength="4" property="codigoQuadraInicial" size="3"
						onkeypress="validaEnterDependenciaComMensagem(event, 'exibirInformarContasEmCobrancaAction.do?tipoPesquisa=quadraInicial', document.forms[0].codigoQuadraInicial, document.forms[0].codigoSetorComercialOrigem.value, 'Setor Comercial Inicial','Quadra Inicial');"
						tabindex="10"
						onkeyup="javascript:replicarCampo(form.codigoQuadraFinal, form.codigoQuadraInicial);limparQuadraInicialTecla();" 
						onchange="javascript:limparTotalizacao();" />
						<a href="javascript:pesquisarQuadraInicial();"> <img
							width="23" height="21" border="0"
							src="<bean:message key="caminho.imagens"/>pesquisa.gif"
							title="Pesquisar Quadra" /></a>
						<logic:present name="quadraInicialInexistente" scope="request">
						<html:text property="descricaoQuadraInicial" size="40"
							maxlength="40" readonly="true"
							style="border: 0pt none ; background-color:#EFEFEF; color: #ff0000" />
						</logic:present>
						<logic:notPresent name="quadraInicialInexistente" scope="request">
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
					<td><html:text maxlength="3" property="idLocalidadeDestino"
						size="3"
						onkeypress="validaEnterComMensagem(event, 'exibirInformarContasEmCobrancaAction.do?tipoPesquisa=localidadeDestino', 'idLocalidadeDestino' ,'Localidade Final');bloqueiaDados();"
						tabindex="11" onkeyup="limparLocalidadeDestinoTecla();" 
						onchange="javascript:limparTotalizacao();" /> <a
						href="javascript:pesquisarLocalidadeDestino();"> <img width="23"
						height="21" border="0"
						src="<bean:message key="caminho.imagens"/>pesquisa.gif"
						title="Pesquisar Localidade" /></a> <logic:present
						name="localidadeDestinoInexistente" scope="request">
						<html:text property="nomeLocalidadeDestino" size="40"
							maxlength="40" readonly="true"
							style="border: 0pt none ; background-color:#EFEFEF; color: #ff0000" />
					</logic:present> <logic:notPresent
						name="localidadeDestinoInexistente" scope="request">
						<html:text property="nomeLocalidadeDestino" size="40"
							maxlength="40" readonly="true"
							style="background-color:#EFEFEF; border:0; color: #000000" />
					</logic:notPresent> <a href="javascript:limparLocalidadeDestino();limparTotalizacao();">
					<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar Localidade" /></a></td>
				</tr>
				<tr>
					<td><strong>Setor Comercial Final:</strong></td>
					<td><html:text maxlength="3" property="codigoSetorComercialDestino"
						size="3"
						onkeypress="validaEnterDependenciaComMensagem(event, 'exibirInformarContasEmCobrancaAction.do?tipoPesquisa=setorComercialDestino', document.forms[0].codigoSetorComercialDestino, document.forms[0].idLocalidadeDestino.value, 'Localidade Final', 'Setor Comercial Final');"
						onchange="javascript:limparTotalizacao();" 
						tabindex="12" onkeyup="limparSetorComercialDestinoTecla();" /> <a
						href="javascript:pesquisarSetorComercialDestino();"> <img
						width="23" height="21" border="0"
						src="<bean:message key="caminho.imagens"/>pesquisa.gif"
						title="Pesquisar Setor Comercial" /></a> <logic:present
						name="setorComercialDestinoInexistente" scope="request">
						<html:text property="descricaoSetorComercialDestino" size="40"
							maxlength="40" readonly="true"
							style="border: 0pt none ; background-color:#EFEFEF; color: #ff0000" />
					</logic:present> <logic:notPresent
						name="setorComercialDestinoInexistente" scope="request">
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
					<td><html:text maxlength="4" property="codigoQuadraFinal" size="3"
						onkeypress="validaEnterDependenciaComMensagem(event, 'exibirInformarContasEmCobrancaAction.do?tipoPesquisa=quadraFinal', document.forms[0].codigoQuadraFinal, document.forms[0].codigoSetorComercialDestino.value, 'Setor Comercial Final','Quadra Final');"
						tabindex="13" onkeyup="javascript:limparQuadraFinalTecla();" 
						onchange="javascript:limparTotalizacao();" />
						<a href="javascript:pesquisarQuadraFinal();"> <img
							width="23" height="21" border="0"
							src="<bean:message key="caminho.imagens"/>pesquisa.gif"
							title="Pesquisar Quadra" /></a>
						<logic:present name="quadraFinalInexistente" scope="request">
						<html:text property="descricaoQuadraFinal" size="40"
							maxlength="40" readonly="true"
							style="border: 0pt none ; background-color:#EFEFEF; color: #ff0000" />
						</logic:present>
						<logic:notPresent name="quadraFinalInexistente" scope="request">
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
					<td colspan="2">
					<hr>
					</td>
				</tr>
				<tr>
					<td width="30%"><strong>Empresa:<font color="#FF0000">*</font></strong></td>
					<td><html:text maxlength="9" property="idEmpresa" size="10"
						tabindex="14"
						onkeyup="validaEnterComMensagem(event, 'exibirInformarContasEmCobrancaAction.do', 'idEmpresa', 'Empresa'); limparEmpresaTecla();" 
						onchange="javascript:limparTotalizacao();" />
					<a href="javascript:pesquisarEmpresa();"><img
						src="<bean:message key='caminho.imagens'/>pesquisa.gif" width="23"
						height="21" alt="Pesquisar" border="0"></a> <logic:present
						name="empresaInexistente" scope="request">
						<html:text property="nomeEmpresa" size="37" maxlength="37"
							readonly="true"
							style="border: 0pt none ; background-color:#EFEFEF; color: #ff0000" />
					</logic:present> <logic:notPresent name="empresaInexistente"
						scope="request">
						<html:text property="nomeEmpresa" size="37" maxlength="37"
							readonly="true"
							style="background-color:#EFEFEF; border:0; color: #000000" />
					</logic:notPresent> <a href="javascript:limparEmpresa();limparTotalizacao();"> <img
						border="0" src="imagens/limparcampo.gif" height="21" width="23"> </a>
					</td>
				</tr>
				<tr>
					<td colspan="2">
					<hr>
					</td>
				</tr>
				<tr>
					<td><strong>Período Refer. das Contas:</strong></td>
					<td><strong> <html:text maxlength="7" property="referenciaInicial"
						size="7" tabindex="15"
						onkeyup="mascaraAnoMes(this, event); replicarCampo(document.forms[0].referenciaFinal, document.forms[0].referenciaInicial);"
						onchange="javascript:limparTotalizacao();" />
					<strong> a</strong> <html:text maxlength="7"
						property="referenciaFinal" size="7" tabindex="16"
						onkeyup="mascaraAnoMes(this, event);"
						onchange="javascript:limparTotalizacao();" /> </strong> (mm/aaaa)</td>
				</tr>
				<tr>
					<td><strong>Período de Vencimento das Contas:</strong></td>
					<td><strong> <html:text maxlength="10"
						property="dataVencimentoInicial" size="10" tabindex="17"
						onkeyup="mascaraData(this, event);  replicarCampo(document.forms[0].dataVencimentoFinal, document.forms[0].dataVencimentoInicial);"
						onchange="javascript:limparTotalizacao();" />
					<a
						href="javascript:abrirCalendarioReplicando('InformarContasEmCobrancaActionForm', 'dataVencimentoInicial', 'dataVencimentoFinal');">
					<img border="0"
						src="<bean:message key="caminho.imagens"/>calendario.gif"
						width="20" border="0" align="absmiddle" alt="Exibir Calendário" /></a>
					a</strong> <html:text maxlength="10" property="dataVencimentoFinal"
						tabindex="18" size="10" onkeyup="mascaraData(this, event);" 
						onchange="javascript:limparTotalizacao();" /> <a
						href="javascript:abrirCalendario('InformarContasEmCobrancaActionForm', 'dataVencimentoFinal')">
					<img border="0"
						src="<bean:message key="caminho.imagens"/>calendario.gif"
						width="20" border="0" align="absmiddle" alt="Exibir Calendário" /></a>
					(dd/mm/aaaa)</td>
				</tr>
				<tr>
					<td><strong>Valor da Conta:<font color="#FF0000">*</font></strong></td>
					<td>
						<strong> 
							<html:text property="valorMinimo" size="14" maxlength="14" tabindex="19"
								onkeyup="formataValorMonetario(this, 14); replicarCampo(document.forms[0].valorMaximo, document.forms[0].valorMinimo);"
								onchange="javascript:limparTotalizacao();" style="text-align:right;" /> a 
							<html:text property="valorMaximo" size="14" maxlength="14" tabindex="20" onkeyup="formataValorMonetario(this, 14);" 
								onchange="javascript:limparTotalizacao();" style="text-align:right;" /> 
						</strong>
					</td>
				</tr>
				<tr>
					<td><strong>Quantidade de Contas:<font color="#FF0000">*</font></strong></td>
					<td>
						<strong> 
							<html:text property="quantidadeContasInicial" size="14" maxlength="9" tabindex="21"
								onkeyup="somente_numero(this);replicarCampo(document.forms[0].quantidadeContasFinal, document.forms[0].quantidadeContasInicial);"
								onchange="javascript:limparTotalizacao();" style="text-align:right;" /> a 
							<html:text property="quantidadeContasFinal" size="14" maxlength="9" tabindex="22" onkeyup="somente_numero(this);"
								onchange="javascript:limparTotalizacao();" style="text-align:right;" /> 
						</strong>
					</td>
				</tr>
				<tr>
					<td><strong>Quantidade de Dias de Vencimento:<font color="#FF0000">*</font></strong></td>
					<td>
						<strong> 
							<html:text property="quantidadeDiasVencimento" size="14" maxlength="10" tabindex="23" onkeyup="somente_numero(this);"
								onchange="javascript:limparTotalizacao();" style="text-align:right;" />
				</tr>
				
				<tr><td><strong> </strong></td><td><strong> </strong></td></tr>
				
				<tr>
					<td><strong>Quantidade Máxima de Clientes no Comando:</strong></td>
					<td><strong> <html:text property="quantidadeMaximaClientes" size="14"
						maxlength="10" tabindex="24"
						onkeyup="somente_numero(this);"
						onchange="javascript:limparTotalizacao();" 
						style="text-align:right;" />
				</tr>
				
				<tr><td><strong> </strong></td><td><strong> </strong></td></tr>
				
				<tr>
					<td width="30%"><strong>Incluir débitos pretéritos:</strong></td>
					<td colspan="6">
						<span class="style2">
							<strong>
								<label><html:radio property="indicadorGerarComDebitoPreterito" value="1"/>Sim</label>
 				  				<label><html:radio property="indicadorGerarComDebitoPreterito" value="2"/>Não</label>
                  			</strong>
                  		</span>
					</td>
				</tr>
				
				<tr>
					<td colspan="2" align="right"><input type="button"
						name="selecionar" class="bottonRightCol" value="Selecionar"
						onClick="javascript:pesquisarQuantidadeContas();" /></td>
				</tr>
				<tr>
					<td colspan="2">
					<hr>
					</td>
				</tr>
			</table>
				<logic:present name="colecaoQuantidadeContas" scope="session">
			<table width="100%" >
					<tr>
						<td bgcolor="#90c7fc" bordercolor="#90c7fc"></td>
						<td bgcolor="#90c7fc" bordercolor="#90c7fc" align="center" colspan="<bean:write name='tamanho' scope='session'/>"><strong>Quantidade de Faturas em Aberto:</strong></td>
						
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

				</table>
				</logic:present>
				<table width="100%" border="0">
				
				<logic:notPresent name="colecaoQuantidadeContas" scope="session">
					<tr>
						<td><strong>Quantidade de Contas:</strong></td>
						<td><html:text property="qtdContas" size="10" maxlength="10"
							readonly="true"
							style="background-color:#EFEFEF; border:0; color: #000000" /></td>
					</tr>
					<tr>
						<td><strong>Quantidade de Clientes:</strong></td>
						<td><html:text property="qtdClientes" size="10" maxlength="10"
							readonly="true"
							style="background-color:#EFEFEF; border:0; color: #000000" /></td>
					</tr>
					<tr>
						<td><strong>Valor Total da Dívida:</strong></td>
						<td><html:text property="valorTotalDivida" size="15" maxlength="15"
							readonly="true"
							style="background-color:#EFEFEF; border:0; color: #000000" /></td>
					</tr>
				</logic:notPresent>
				
				<tr>
					<td colspan="2">
					<hr>
					</td>
				</tr>
				<logic:present name="habilitaCamposCiclo">
					<tr>
						<td><strong>Data Início do Ciclo:</strong></td>
						<td><strong> <html:text maxlength="10" property="dataInicioCiclo"
							size="10" tabindex="25" onkeyup="mascaraData(this, event);" />
						</strong> <a
							href="javascript:abrirCalendario('InformarContasEmCobrancaActionForm', 'dataInicioCiclo');" >
						<img border="0"
							src="<bean:message key="caminho.imagens"/>calendario.gif"
							width="20" border="0" align="absmiddle" alt="Exibir Calendário" /></a></td>
					</tr>
					<tr>
						<td><strong>Quantidade de Dias do Ciclo:</strong></td>
						<td><html:text property="quantidadeDiasCiclo" size="10" maxlength="10"/></td>
					</tr>
				</logic:present>
				<logic:notPresent name="habilitaCamposCiclo" scope="session">
					<tr>
						<td><strong>Data Início do Ciclo:</strong></td>
						<td><strong> <html:text maxlength="10" property="dataInicioCiclo"
							size="10" tabindex="26" onkeyup="mascaraData(this, event);" disabled="disabled"/>
						</strong> <a
							href="javascript:abrirCalendario('InformarContasEmCobrancaActionForm', 'dataInicioCiclo');">
						<img border="0"
							src="<bean:message key="caminho.imagens"/>calendario.gif"
							width="20" border="0" align="absmiddle" alt="Exibir Calendário" /></a>(dd/mm/aaaa)</td>
					</tr>
					<tr>
						<td><strong>Quantidade de Dias do Ciclo:</strong></td>
						<td><html:text property="quantidadeDiasCiclo" size="10" maxlength="10" disabled="disabled"/></td>
					</tr>
				</logic:notPresent>
				<tr>
					<td colspan="2">
					<hr>
					</td>
				</tr>
				<tr> 
		            <td>
		                <strong>Tipo de Servi&ccedil;o:<font color="#FF0000"></font></strong>
       				</td>
       				<td colspan="3">
       					<strong>

         					<html:text property="idServicoTipo" size="10" maxlength="4" 
									onkeyup="somente_numero(this);validaEnterComMensagem(event, 'exibirInformarContasEmCobrancaAction.do?tipoPesquisa=servicoTipo', 'idServicoTipo' ,'Tipo de Serviço');" />

                      		<a href="javascript:abrirPopup('exibirPesquisarTipoServicoAction.do', 300, 620);">

							<img width="23" 
								height="21" 
								border="0"
								src="<bean:message key="caminho.imagens"/>pesquisa.gif"
								title="Pesquisar Tipo de Serviço" /></a> 

							<logic:present name="idServicoTipoEncontrada" scope="request">
								
								<html:text property="descricaoServicoTipo" 
									size="37"
									maxlength="37" 
									readonly="true"
									style="background-color:#EFEFEF; border:0; color: #000000" />
							</logic:present> 
					
							<logic:notPresent name="idServicoTipoEncontrada" scope="request">
								
								<html:text property="descricaoServicoTipo" 
									size="37"
									maxlength="37" 
									readonly="true"
									style="background-color:#EFEFEF; border:0; color: red" />
									
							</logic:notPresent>


               				<a href="javascript:limparServicoTipo();">
               					<img src="imagens/limparcampo.gif" 
               						 width="23" 
               						 height="21"
               						 border="0"
        			 				 title="Apagar"></a>
		                        					
		                 </strong>
		            </td>
		        </tr>
				<tr>
					<td colspan="2">
					&nbsp;
					</td>
				</tr>
				<tr>
					<td colspan="2" align="right"><input type="button"
						name="gerarDadosCobranca" class="bottonRightCol"
						value="Gerar Dados Cobrança"
						onClick="javascript:validaForm();" /></td>
				</tr>
				<tr>
					<td><strong> <font color="#FF0000"></font></strong></td>
					<td align="right">
					<div align="left"><strong><font color="#FF0000">*</font></strong>
					Campos obrigat&oacute;rios</div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
	</table>

	<%@ include file="/jsp/util/rodape.jsp"%>

</html:form>
</div>
</body>
</html:html>
