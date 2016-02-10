<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>

<%@ page import="gcom.cadastro.imovel.Categoria" %>
<%@ page import="gcom.cadastro.imovel.Subcategoria" %>
<%@ page import="gcom.faturamento.debito.DebitoCobrado" isELIgnored="false"%>
<%@ page import="gcom.faturamento.credito.CreditoRealizado" %>
<%@ page import="gcom.faturamento.conta.Conta"%>
<%@ page import="gcom.util.Util" %>
<%@ page import="gcom.util.ConstantesSistema" %>
<%@ page import="gcom.cadastro.sistemaparametro.SistemaParametro" %>
<%@ page import="gcom.financeiro.FinanciamentoTipo" %>
<%@ page import="gcom.faturamento.consumotarifa.ConsumoTarifa" %>

<%@ page import="gcom.cadastro.cliente.ClienteConta" %>
<%@ page import="gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao" %>
<%@ page import="gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao" %>
<%@ page import="gcom.gui.GcomAction" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">


<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js" ></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js" ></script>

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="RetificarContaActionForm"/>


<%-- Colocado por Raphael Rossiter em 16/04/2007
	 Objetivo: Manipulação dos objetos que serão exibidos no formulário de acordo com a empresa --%>
								
<%-- ================================================================================= --%>
							
<logic:notEqual name="indicadorTarifaCategoria" value="<%= "" + SistemaParametro.INDICADOR_TARIFA_SUBCATEGORIA %>">

<SCRIPT LANGUAGE="JavaScript">

function removerCategoria(idCategoria){
 	var form = document.forms[0];
 	
 	if (validarCamposDinamicos(form)){
 		form.action = "/gsan/removerSelecaoCategoriaActionRetificarConta.do?idCategoria=" + idCategoria + "&mapeamento=exibirRetificarConta";
 		if (confirm("Confirma remoção?")){
 			form.situacaoAguaConta.disabled = false;
			form.situacaoEsgotoConta.disabled = false;
			form.submit();
		}
	}
 }
 
 
 function validarCamposDinamicos(form){
 
 	var camposValidos = true;
 
 	for (i=0; i < form.elements.length; i++) {
    
    	if (form.elements[i].type == "text" && form.elements[i].id.length > 1){
    		
			switch (form.elements[i].id){
			
				case "categoria":
				
					if (form.elements[i].value.length < 1){
						alert("Informe Quantidade de Economias.");
						form.elements[i].focus();
						camposValidos = false;
					}
					else if (isNaN(form.elements[i].value) || form.elements[i].value.indexOf('.') != -1){
						alert("Quantidade de Economias deve conter apenas valores inteiros.");
						form.elements[i].focus();
						camposValidos = false;
					}
					else if (!testarCampoValorZero(form.elements[i], "Quantidade de Categorias")){
						form.elements[i].focus();
						camposValidos = false;
					}
					
				
					break;
					
				case "debito":
				
					var value = form.elements[i].value;
					value = value.replace(/\./g, '');
					value = value.replace(/,/g, '.');
				
					if (value.length < 1){
						alert("Informe Valor do Débito.");
						form.elements[i].focus();
						camposValidos = false;
					}
					else if (isNaN(value) || value < 0){
						alert("Valor do Débito deve somente conter números positivos.");
						form.elements[i].focus();
						camposValidos = false;
					}
					else if (!testarCampoValorZeroDecimal(form.elements[i], "Valor do Débito")){
						form.elements[i].focus();
						camposValidos = false;
					}
					
					break;
					
				case "credito":
				
					var value = form.elements[i].value;
					value = value.replace(/\./g, '');
					value = value.replace(/,/g, '.');
				
					if (value.length < 1){
						alert("Informe Valor do Crédito.");
						form.elements[i].focus();
						camposValidos = false;
					}
					else if (isNaN(value) || value < 0){
						alert("Valor do Crédito deve somente conter numéros positivos.");
						form.elements[i].focus();
						camposValidos = false;
					}
					else if (!testarCampoValorZeroDecimal(form.elements[i], "Valor do Crédito")){
						form.elements[i].focus();
						camposValidos = false;
					}
			
					break;
					
				default:
					break;
			}	
    	}
    	
    	
    	if (!camposValidos){
    		break;
    	}
    }
    
    return camposValidos;
}
 
  function validarRetificacao(form){
	  if (validarForm(document.forms[0], true) != false){
	  	 botaoAvancarTelaEspera('/gsan/retificarContaAction.do');
	 }
  }
  
 

</SCRIPT>

</logic:notEqual>
							
<%-- ================================================================================= --%>
<%-- ================================================================================= --%>



<%-- Colocado por Raphael Rossiter em 16/04/2007
	 Objetivo: Manipulação dos objetos que serão exibidos no formulário de acordo com a empresa --%>
								
<%-- ================================================================================= --%>
							
<logic:equal name="indicadorTarifaCategoria" value="<%= "" + SistemaParametro.INDICADOR_TARIFA_SUBCATEGORIA %>">

<SCRIPT LANGUAGE="JavaScript">

function removerSubcategoria(idSubcategoria){
	var form = document.forms[0];
 	
 	if (validarCamposDinamicos(form)){
		form.action = "/gsan/removerSelecaoCategoriaActionRetificarConta.do?idSubcategoria=" + idSubcategoria + "&mapeamento=exibirRetificarConta";
		if (confirm("Confirma remoção?")){
			form.situacaoAguaConta.disabled = false;
			form.situacaoEsgotoConta.disabled = false;
			form.submit();
		}
	}
 }
 
 function validarCamposDinamicos(form){
 
 	var camposValidos = true;
 
 	for (i=0; i < form.elements.length; i++) {
    
    	if (form.elements[i].type == "text" && form.elements[i].id.length > 1){
    		
			switch (form.elements[i].id){
			
				case "subcategoria":
				
					if (form.elements[i].value.length < 1){
						alert("Informe Quantidade de Economias.");
						form.elements[i].focus();
						camposValidos = false;
					}
					else if (isNaN(form.elements[i].value) || form.elements[i].value.indexOf('.') != -1){
						alert("Quantidade de Economias deve conter apenas valores inteiros.");
						form.elements[i].focus();
						camposValidos = false;
					}
					else if (!testarCampoValorZero(form.elements[i], "Quantidade de Subcategorias")){
						form.elements[i].focus();
						camposValidos = false;
					}
					
				
					break;
					
				case "debito":
				
					var value = form.elements[i].value;
					value = value.replace(/\./g, '');
					value = value.replace(/,/g, '.');
				
					if (value.length < 1){
						alert("Informe Valor do Débito.");
						form.elements[i].focus();
						camposValidos = false;
					}
					else if (isNaN(value) || value < 0){
						alert("Valor do Débito deve somente conter números positivos.");
						form.elements[i].focus();
						camposValidos = false;
					}
					else if (!testarCampoValorZeroDecimal(form.elements[i], "Valor do Débito")){
						form.elements[i].focus();
						camposValidos = false;
					}
					
					break;
					
				case "credito":
				
					var value = form.elements[i].value;
					value = value.replace(/\./g, '');
					value = value.replace(/,/g, '.');
				
					if (value.length < 1){
						alert("Informe Valor do Crédito.");
						form.elements[i].focus();
						camposValidos = false;
					}
					else if (isNaN(value) || value < 0){
						alert("Valor do Crédito deve somente conter numéros positivos.");
						form.elements[i].focus();
						camposValidos = false;
					}
					else if (!testarCampoValorZeroDecimal(form.elements[i], "Valor do Crédito")){
						form.elements[i].focus();
						camposValidos = false;
					}
			
					break;
					
				default:
					break;
			}	
    	}
    	
    	
    	if (!camposValidos){
    		break;
    	}
    }
    
    return camposValidos;
}

  function validarRetificacao(form){
	  if (validarForm(document.forms[0], true) != false){
	  	 botaoAvancarTelaEspera('/gsan/retificarContaAction.do');
	 }
  }

</SCRIPT>

</logic:equal>
							
<%-- ================================================================================= --%>
<%-- ================================================================================= --%>


<SCRIPT LANGUAGE="JavaScript"><!--



/**
*
* inserir = false // Somente calcular os valores da conta
* inserir = true // calcular os valores da conta e inserir a mesma no BD
*
**/
function validarForm(form, retificar){

	if (validateRetificarContaActionForm(form)){
	
		if (retificar){
			urlRedirect = "/gsan/retificarContaAction.do";
		}
		else{
			urlRedirect = "/gsan/calcularValoresRetificarContaAction.do";
		}
		
		var msgDataVencimento = "Data de Vencimento anterior à data corrente.";
		var msgDataVencimento60 = "Data de Vencimento posterior a data corrente mais 60 dias.";
		var msgDataVencimentoUltimoDiaMes = "Data de Vencimento foi alterada para o último dia do mês corrente.Confirma?";
		var msgRecalcularConsumoEsgoto = "Deseja recalcular o consumo de esgoto aplicando o percentual de coleta?";
		var DATA_ATUAL = document.getElementById("DATA_ATUAL").value;
		var DATA_ATUAL_60 = document.getElementById("DATA_ATUAL_60").value;
		var ANO_LIMITE = document.getElementById("ANO_LIMITE").value;
		var ID_CONDOMINIO = document.getElementById("ID_CONDOMINIO").value;
		var CONSUMO_AGUA_ANTES = document.getElementById("CONSUMO_AGUA_ANTES").value;
		var ULTIMA_DATA_MES = document.getElementById("ULTIMA_DATA_MES").value;
		var INDICADOR_CALCULA_VENCIMENTO = document.getElementById("INDICADOR_CALCULA_VENCIMENTO").value;
		var PERMISSAO_NAO_VERIFICAR_CONSUMO_ESGOTO = document.getElementById("PERMISSAO_NAO_VERIFICAR_CONSUMO_ESGOTO").value;
		
		var listBoxAgua = form.situacaoAguaConta;
		var listBoxEsgoto = form.situacaoEsgotoConta;
		
		var ATIVO = document.getElementById("ATIVO").value;
 		var indicadorFaturamentoAgua = listBoxAgua.options[listBoxAgua.options.selectedIndex].id;
 		var indicadorFaturamentoEsgoto = listBoxEsgoto.options[listBoxEsgoto.options.selectedIndex].id;
 		
 		var percentualColeta = form.percentualColeta;
 		
 	
 	   if(INDICADOR_CALCULA_VENCIMENTO != 2){ 
 	     if(form.indicadorDataAlterada == "sim"){  	  
 	  	   if (comparaData(form.vencimentoConta.value, ">", ULTIMA_DATA_MES )){					
 				if (confirm(msgDataVencimentoUltimoDiaMes)){ 
 				    form.vencimentoConta.value = ULTIMA_DATA_MES;	 			   
					form.vencimentoConta.focus();	
				}else{	
					//*******************************************
					// CRC3390
					// Por: Ivan Sergio
					//*******************************************
					//return false;				
					//form.vencimentoConta.focus();
					urlRedirect = "/gsan/calcularValoresRetificarContaAction.do"
					//*******************************************
				}	
			}
		  }
		}

		if ((form.vencimentoConta.value.substring(6, 10) * 1) < (ANO_LIMITE * 1)){
			alert("Ano do vencimento da conta não deve ser menor que " + ANO_LIMITE + ".");
			form.vencimentoConta.focus();
			return false;
		}
		else if( form.existeColecao.value == 1){
			valido = false;
			alert("Informe Categorias e Economias.");
			return false;
		}else if (comparaData(form.vencimentoConta.value, "<", DATA_ATUAL )){
			if (confirm(msgDataVencimento)){
				
				if ((indicadorFaturamentoAgua.length > 0 && indicadorFaturamentoAgua == ATIVO)
				 	&& form.consumoAgua.value.length < 1){
					
					alert("O preenchimento do campo Consumo de Água é obrigatório.");
					form.consumoAgua.focus();
					return false;
				}
				else if (form.consumoAgua.value.length > 0 && form.consumoAgua.value != 0 && 
					form.consumoAgua.value <= listBoxAgua.options[listBoxAgua.selectedIndex].name){
				
					alert("Consumo informado menor que consumo mínimo para situação da ligação de água, valor tem que ser maior que " + listBoxAgua.options[listBoxAgua.selectedIndex].name);
					form.consumoAgua.focus();
					return false;
				}
				else if ((indicadorFaturamentoEsgoto.length > 0 && indicadorFaturamentoEsgoto == ATIVO) 
					&& form.consumoEsgoto.value.length < 1){
					
					alert("O preenchimento do campo Consumo de Esgoto é obrigatório.");
					form.consumoEsgoto.focus();
					return false;
				}
				else if (form.consumoEsgoto.value.length > 0 && form.consumoEsgoto.value != 0 && 
					form.consumoEsgoto.value <= listBoxEsgoto.options[listBoxEsgoto.selectedIndex].name){
				
					alert("Volume informado menor que volume mínimo para situação da ligação de esgoto, valor tem que ser maior que " + listBoxesgoto.name);
					form.consumoEsgoto.focus();
					return false;
				}
				else if ((indicadorFaturamentoEsgoto.length > 0 && indicadorFaturamentoEsgoto == ATIVO) 
					&& form.percentualEsgoto.value.length < 1){
				
					alert("O preenchimento do campo Percentual de Esgoto é obrigatório.");
					form.percentualEsgoto.focus();
					return false;
				}
				else if (obterQuantidadeInteiro(form.percentualEsgoto.value) > 3 ||
						 obterQuantidadeFracao(form.percentualEsgoto.value) > 2){
						
					alert("Percentual de Esgoto inválido.");
					form.percentualEsgoto.focus();
					return false;
				}
				
				else if(form.consumoEsgoto.value == "" && 
					form.percentualColeta.value == "" && indicadorFaturamentoEsgoto == ATIVO){
					
					alert("O preenchimento do campo Consumo de Esgoto é Obrigatório.");
					form.consumoEsgoto.focus();
					return false;
				}
				
				else if(form.consumoEsgoto.value == "" && indicadorFaturamentoEsgoto == ATIVO){
					
					alert("O preenchimento do campo Percentual de Coleta é Obrigatório.");
					form.percentualColeta.focus();
					return false;
				}
				
				else if ((form.percentualColeta.value * 1) != 100 &&
						form.consumoEsgoto.value.length > 0 &&
						form.consumoEsgoto.value > 0 &&
						form.consumoAgua.value.length > 0 && 
						form.consumoAgua.value != CONSUMO_AGUA_ANTES &&
						form.consumoAgua.value != form.consumoAguaAlterado.value){
		
					 form.consumoAguaAlterado.value = form.consumoAgua.value;
									
		 				if (confirm(msgRecalcularConsumoEsgoto)){ 
		 				   
		 				    form.consumoEsgoto.value = Math.round(form.consumoAgua.value * (parseInt(form.percentualColeta.value)/100));
							form.consumoEsgoto.focus();
							
							if(form.consumoEsgoto.value == "" && 
								form.percentualColeta.value == "" && indicadorFaturamentoEsgoto == ATIVO){
					
								alert("O preenchimento do campo Consumo de Esgoto é Obrigatório.");
								form.consumoEsgoto.focus();
								return false;
							}
							
							if(form.consumoEsgoto.value == "" && indicadorFaturamentoEsgoto == ATIVO){
					
								alert("O preenchimento do campo Percentual de Coleta é Obrigatório.");
								form.percentualColeta.focus();
								return false;
							}
							
							if (form.consumoAgua.value.length > 0 && form.consumoEsgoto.value.length > 0 &&
							 (Math.round(form.consumoAgua.value * (parseInt(form.percentualColeta.value)/100))) > (form.consumoEsgoto.value * 1) && 
							 (ID_CONDOMINIO == null || ID_CONDOMINIO == '')
							 && PERMISSAO_NAO_VERIFICAR_CONSUMO_ESGOTO == 2
							 ){
						
								alert("Consumo de Esgoto não deve ser menor que o Consumo de Água");
								form.consumoEsgoto.value = Math.round(form.consumoAgua.value * (parseInt(form.percentualColeta.value)/100));
								
								form.consumoEsgoto.focus();
								return false;
							}
							else if( form.existeColecao.value == 1 ){
								alert("Informe Categorias e Economias.");
								return false;
							}else {
								if (validarCamposDinamicos(form)) {
									//converteVirgula(form.percentualEsgoto);
									
									if (!retificar){
										form.situacaoAguaConta.disabled = false;
										form.situacaoEsgotoConta.disabled = false;
										redirecionarSubmit(urlRedirect);
									}
								}else{
									return false;
								}	
							}	
							
						}else{	
							return false;				
						}	
				}
				else if (form.consumoAgua.value.length > 0 && form.consumoEsgoto.value.length > 0 &&
						 (Math.round(form.consumoAgua.value * (parseInt(form.percentualColeta.value)/100))) > (form.consumoEsgoto.value * 1) && 
						 (ID_CONDOMINIO == null || ID_CONDOMINIO == '')
						  && PERMISSAO_NAO_VERIFICAR_CONSUMO_ESGOTO == 2
						 ){
					
					alert("Consumo de Esgoto não deve ser menor que o Consumo de Água");
					form.consumoEsgoto.value = Math.round(form.consumoAgua.value * (parseInt(form.percentualColeta.value)/100));
					
					form.consumoEsgoto.focus();
					return false;
				}
				else if( form.existeColecao.value == 1 ){
					alert("Informe Categorias e Economias.");
					return false;
				}else {
					if (validarCamposDinamicos(form)) {
						//converteVirgula(form.percentualEsgoto);
						
						if (!retificar){
							form.situacaoAguaConta.disabled = false;
							form.situacaoEsgotoConta.disabled = false;
							redirecionarSubmit(urlRedirect);
						}
					}else{
						return false;
					}	
				}	
				
			}else{
			return false;
			}
		}
		else if (comparaData(form.vencimentoConta.value, ">", DATA_ATUAL_60 )){
			if (confirm(msgDataVencimento60)){
				
				if ((indicadorFaturamentoAgua.length > 0 && indicadorFaturamentoAgua == ATIVO)
				 	&& form.consumoAgua.value.length < 1){
					
					alert("O preenchimento do campo Consumo de Água é obrigatório.");
					form.consumoAgua.focus();
					return false;
				}
				else if (form.consumoAgua.value.length > 0 && form.consumoAgua.value != 0 && 
					form.consumoAgua.value <= listBoxAgua.options[listBoxAgua.selectedIndex].name){
				
					alert("Consumo informado menor que consumo mínimo para situação da ligação de água, valor tem que ser maior que " + listBoxAgua.options[listBoxAgua.selectedIndex].name);
					form.consumoAgua.focus();
					return false;
				}
				else if ((indicadorFaturamentoEsgoto.length > 0 && indicadorFaturamentoEsgoto == ATIVO) 
					&& form.consumoEsgoto.value.length < 1){
					
					alert("O preenchimento do campo Consumo de Esgoto é obrigatório.");
					form.consumoEsgoto.focus();
					return false;
				}
				else if (form.consumoEsgoto.value.length > 0 && form.consumoEsgoto.value != 0 && 
					form.consumoEsgoto.value <= listBoxEsgoto.options[listBoxEsgoto.selectedIndex].name){
				
					alert("Volume informado menor que volume mínimo para situação da ligação de esgoto, valor tem que ser maior que " + listBoxesgoto.name);
					form.consumoEsgoto.focus();
					return false;
				}
				else if ((indicadorFaturamentoEsgoto.length > 0 && indicadorFaturamentoEsgoto == ATIVO) 
					&& form.percentualEsgoto.value.length < 1){
				
					alert("O preenchimento do campo Percentual de Esgoto é obrigatório.");
					form.percentualEsgoto.focus();
					return false;
				}
				else if (obterQuantidadeInteiro(form.percentualEsgoto.value) > 3 ||
						 obterQuantidadeFracao(form.percentualEsgoto.value) > 2){
					
					alert("Percentual de Esgoto inválido.");
					form.percentualEsgoto.focus();
					return false;
				}
				
				else if(form.consumoEsgoto.value == "" && 
					form.percentualColeta.value == "" && indicadorFaturamentoEsgoto == ATIVO){
					
					alert("O preenchimento do campo Volume de Esgoto é Obrigatório.");
					form.consumoEsgoto.focus();
					return false;
				}
				
				else if(form.consumoEsgoto.value == "" && indicadorFaturamentoEsgoto == ATIVO){
					
					alert("O preenchimento do campo Percentual de Coleta é Obrigatório.");
					form.percentualColeta.focus();
					return false;
				}
				
				else if ((form.percentualColeta.value * 1) != 100 &&
						form.consumoEsgoto.value.length > 0 &&
						form.consumoEsgoto.value > 0 &&
						form.consumoAgua.value.length > 0 && 
						form.consumoAgua.value != CONSUMO_AGUA_ANTES &&
						form.consumoAgua.value != form.consumoAguaAlterado.value){				
						
						 form.consumoAguaAlterado.value = form.consumoAgua.value;
						
		 				if (confirm(msgRecalcularConsumoEsgoto)){ 
		 				    
		 				    form.consumoEsgoto.value = Math.round(form.consumoAgua.value * (parseInt(form.percentualColeta.value)/100));
							form.consumoEsgoto.focus();
							
							if(form.consumoEsgoto.value == "" && 
								form.percentualColeta.value == "" && indicadorFaturamentoEsgoto == ATIVO){
					
								alert("O preenchimento do campo Volume de Esgoto é Obrigatório.");
								form.consumoEsgoto.focus();
								return false;
							}
							
							if(form.consumoEsgoto.value == "" && indicadorFaturamentoEsgoto == ATIVO){
					
								alert("O preenchimento do campo Percentual de Coleta é Obrigatório.");
								form.percentualColeta.focus();
								return false;
							}
							
							if (form.consumoAgua.value.length > 0 && form.consumoEsgoto.value.length > 0 &&
							 (Math.round(form.consumoAgua.value * (parseInt(form.percentualColeta.value)/100))) > (form.consumoEsgoto.value * 1) && 
							 (ID_CONDOMINIO == null || ID_CONDOMINIO == '')
							  && PERMISSAO_NAO_VERIFICAR_CONSUMO_ESGOTO == 2
							 ){
						
								alert("Volume de Esgoto não deve ser menor que o Consumo de Água");
								form.consumoEsgoto.value = Math.round(form.consumoAgua.value * (parseInt(form.percentualColeta.value)/100));
								
								form.consumoEsgoto.focus();
								return false;
							}
							else if( form.existeColecao.value == 1 ){
								alert("Informe Categorias e Economias.");
								return false;
							}else {
								if (validarCamposDinamicos(form)) {
									//converteVirgula(form.percentualEsgoto);
									
									if (!retificar){
										form.situacaoAguaConta.disabled = false;
										form.situacaoEsgotoConta.disabled = false;
										redirecionarSubmit(urlRedirect);
									}
								}else{
									return false;
								}	
							}	
							
		 				    
						}else{	
							return false;				
							
						}	
					
		
				}
				else if (form.consumoAgua.value.length > 0 && form.consumoEsgoto.value.length > 0 &&
						 (Math.round(form.consumoAgua.value * (parseInt(form.percentualColeta.value)/100))) > (form.consumoEsgoto.value * 1) && 
						 (ID_CONDOMINIO == null || ID_CONDOMINIO == '')
						  && PERMISSAO_NAO_VERIFICAR_CONSUMO_ESGOTO == 2
						 ){

					alert("Volume de Esgoto não deve ser menor que Consumo de Água.");
					form.consumoEsgoto.value = Math.round(form.consumoAgua.value * (parseInt(form.percentualColeta.value)/100));
					form.consumoEsgoto.focus();
					return false;
				}
				else if( form.existeColecao.value == 1 ){
					alert("Informe Categorias e Economias.");
					return false;
				}else {
					if (validarCamposDinamicos(form)){
						//converteVirgula(form.percentualEsgoto);
						
						if (!retificar){
						form.situacaoAguaConta.disabled = false;
						form.situacaoEsgotoConta.disabled = false;
						redirecionarSubmit(urlRedirect);
						}
					}else{
						return false;
					}	
				}
				
			}else{
			return false;
			}
		}
		else if ((indicadorFaturamentoAgua.length > 0 && indicadorFaturamentoAgua == ATIVO)
			&& form.consumoAgua.value.length < 1){
					
			alert("O preenchimento do campo Consumo de Água é obrigatório.");
			form.consumoAgua.focus();
			return false;
		}
		else if (form.consumoAgua.value.length > 0 && form.consumoAgua.value != 0 && 
				form.consumoAgua.value <= listBoxAgua.options[listBoxAgua.selectedIndex].name){
				
			alert("Consumo informado menor que consumo mínimo para situação da ligação de água, valor tem que ser maior que " + listBoxAgua.options[listBoxAgua.selectedIndex].name);
			form.consumoAgua.focus();
			return false;
		}
		else if ((indicadorFaturamentoEsgoto.length > 0 && indicadorFaturamentoEsgoto == ATIVO) 
			&& form.consumoEsgoto.value.length < 1){
					
			alert("Informe Volume de Esgoto ou Percentual de Coleta.");
			if(form.percentualColeta.value == ''){
				form.percentualColeta.focus();
			}else{
				form.consumoEsgoto.focus();
			}
			return false;
		}
		else if (form.consumoEsgoto.value.length > 0 && form.consumoEsgoto.value != 0 && 
				form.consumoEsgoto.value <= listBoxEsgoto.options[listBoxEsgoto.selectedIndex].name){
				
			alert("Volume informado menor que volume mínimo para situação da ligação de esgoto, valor tem que ser maior que " + listBoxesgoto.name);
			form.consumoEsgoto.focus();
			return false;
		}
		else if ((indicadorFaturamentoEsgoto.length > 0 && indicadorFaturamentoEsgoto == ATIVO) 
			&& form.percentualEsgoto.value.length < 1){
				
			alert("O preenchimento do campo Percentual de Esgoto é obrigatório.");
			form.percentualEsgoto.focus();
			return false;
		}
		else if (obterQuantidadeInteiro(form.percentualEsgoto.value) > 3 ||
				 obterQuantidadeFracao(form.percentualEsgoto.value) > 2){
				
			alert("Percentual de Esgoto inválido.");
			form.percentualEsgoto.focus();
			return false;
		}
		
		else if(form.consumoEsgoto.value == "" && 
			form.percentualColeta.value == "" && indicadorFaturamentoEsgoto == ATIVO){
					
			alert("O preenchimento do campo Volume de Esgoto é Obrigatório.");
			form.consumoEsgoto.focus();
			return false;
		}
		
		else if(form.consumoEsgoto.value == "" && indicadorFaturamentoEsgoto == ATIVO){
					
			alert("O preenchimento do campo Percentual de Coleta é Obrigatório.");
			form.percentualColeta.focus();
			return false;
		}
		
		else if ((form.percentualColeta.value * 1) != 100 &&
						form.consumoEsgoto.value.length > 0 &&
						form.consumoEsgoto.value > 0 &&
						form.consumoAgua.value.length > 0 && 
						form.consumoAgua.value != CONSUMO_AGUA_ANTES &&
						form.consumoAgua.value != form.consumoAguaAlterado.value){
				
				form.consumoAguaAlterado.value = form.consumoAgua.value;
				 
 				if (confirm(msgRecalcularConsumoEsgoto)){ 
 				   
 				    form.consumoEsgoto.value = parseInt(Math.round(form.consumoAgua.value * (parseInt(form.percentualColeta.value)/100)));
					form.consumoEsgoto.focus();
					
					if(form.consumoEsgoto.value == "" && 
						form.percentualColeta.value == "" && indicadorFaturamentoEsgoto == ATIVO){
					
						alert("O preenchimento do campo Volume de Esgoto é Obrigatório.");
						form.consumoEsgoto.focus();
						return false;
					}
					
					if(form.consumoEsgoto.value == "" && indicadorFaturamentoEsgoto == ATIVO){
					
						alert("O preenchimento do campo Percentual de Coleta é Obrigatório.");
						form.percentualColeta.focus();
						return false;
					}
					
					if (form.consumoAgua.value.length > 0 && form.consumoEsgoto.value.length > 0 &&
					 (Math.round(form.consumoAgua.value * (parseInt(form.percentualColeta.value)/100))) > (form.consumoEsgoto.value * 1) && 
					 (ID_CONDOMINIO == null || ID_CONDOMINIO == '')
					  && PERMISSAO_NAO_VERIFICAR_CONSUMO_ESGOTO == 2
					 ){
				
						alert("Volume de Esgoto não deve ser menor que o Consumo de Água");
						form.consumoEsgoto.value = Math.round(form.consumoAgua.value * (parseInt(form.percentualColeta.value)/100));
						
						form.consumoEsgoto.focus();
						return false;
					}
					else if( form.existeColecao.value == 1 ){
						alert("Informe Categorias e Economias.");
						return false;
					}else {
						if (validarCamposDinamicos(form)) {
							//converteVirgula(form.percentualEsgoto);
							
							if (!retificar){
								form.situacaoAguaConta.disabled = false;
								form.situacaoEsgotoConta.disabled = false;
								redirecionarSubmit(urlRedirect);
							}
						}else{
							return false;
						}	
					}	

				}else{	
					return false;				
				}	
		}
		else if (form.consumoAgua.value.length > 0 && form.consumoEsgoto.value.length > 0 &&
				(Math.round(form.consumoAgua.value * (parseInt(form.percentualColeta.value)/100))) > (form.consumoEsgoto.value * 1) && 
				(ID_CONDOMINIO == null || ID_CONDOMINIO == '')
				 && PERMISSAO_NAO_VERIFICAR_CONSUMO_ESGOTO == 2){
			
			alert("Volume de Esgoto não deve ser menor que Consumo de Água.");
			form.consumoEsgoto.value = Math.round(form.consumoAgua.value * (parseInt(form.percentualColeta.value)/100));
			form.consumoEsgoto.focus();
			return false;
		}
		else {
			if (validarCamposDinamicos(form)){
					
				if (!retificar){	
					form.situacaoAguaConta.disabled = false;
					form.situacaoEsgotoConta.disabled = false;
					redirecionarSubmit(urlRedirect);
				}
			}else{
				return false;
			}
		}	
	}else{
		return false;
	}
}


function consumoAguaRequerido(form){
 	var retorno = false;
 	
 	var situacaoAguaConta = returnObject(form, "situacaoAguaConta");
 	var consumoAguaConta = returnObject(form, "consumoAgua");
 	
 	var ATIVO = document.getElementById("ATIVO").value;
 	var indicadorFaturamento = situacaoAguaConta.options[situacaoAguaConta.options.selectedIndex].id;
 		
	if ((indicadorFaturamento.length > 0 && indicadorFaturamentoAgua == ATIVO) &&
		 consumoAguaConta.value.length < 1){
		retorno = true;
	}
	
	return retorno;
 }
 
 
 function desabilitaCampoAgua(form){
 	
 	var situacaoAguaConta = returnObject(form, "situacaoAguaConta");
 	var consumoAguaConta = returnObject(form, "consumoAgua");
 	
 	var ATIVO = document.getElementById("ATIVO").value;
 	var indicadorFaturamento = situacaoAguaConta.options[situacaoAguaConta.options.selectedIndex].id;
 	
 	if (indicadorFaturamento.length > 0 && indicadorFaturamento != ATIVO){
 		consumoAguaConta.value = "";
		consumoAguaConta.disabled = true;
 	}
 	else{
 		consumoAguaConta.disabled = false;
 	}
 }
 
 function removerDebitoCobrado(debitoCobradoUltimaAlteracao){
 	var form = document.forms[0];
 	
 	form.action = "/gsan/removerSelecaoDebitoCobradoActionRetificarConta.do?debitoCobradoUltimaAlteracao=" + debitoCobradoUltimaAlteracao + "&mapeamento=exibirRetificarConta";
 	
 	if (confirm("Confirma remoção?")){
 		form.situacaoAguaConta.disabled = false;
		form.situacaoEsgotoConta.disabled = false;
 	
		form.submit();
	}
 }
 
 
 function removerCreditoRealizado(creditoRealizadoUltimaAlteracao){
 	var form = document.forms[0];
 	
 	form.action = "/gsan/removerSelecaoCreditoRealizadoAction.do?creditoRealizadoUltimaAlteracao=" + creditoRealizadoUltimaAlteracao;
 	
	if (confirm("Confirma remoção?")){
		form.situacaoAguaConta.disabled = false;
		form.situacaoEsgotoConta.disabled = false;
		form.submit();
	}
 }
 
 
 function consumoEsgotoRequerido(form){
 	var retorno = false;
 	
 	var situacaoEsgotoConta = returnObject(form, "situacaoEsgotoConta");
 	var consumoEsgotoConta = returnObject(form, "consumoEsgoto");
 	
 	var ATIVO = document.getElementById("ATIVO").value;
 	var indicadorFaturamento = situacaoEsgotoConta.options[situacaoEsgotoConta.options.selectedIndex].id;
 		
	if ((indicadorFaturamento.length > 0 && indicadorFaturamentoAgua == ATIVO) &&
		 consumoEsgotoConta.value.length < 1){
		retorno = true;
	}
	
	return retorno;
 }
 
 
 function percentualEsgotoRequerido(form){
 	var retorno = false;
 	
 	var situacaoEsgotoConta = returnObject(form, "situacaoEsgotoConta");
 	var percentualEsgoto = returnObject(form, "percentualEsgoto");
 	
 	var ATIVO = document.getElementById("ATIVO").value;
 	var indicadorFaturamento = situacaoEsgotoConta.options[situacaoEsgotoConta.options.selectedIndex].id;
 		
	if ((indicadorFaturamento.length > 0 && indicadorFaturamentoAgua == ATIVO) &&
		 percentualEsgoto.value.length < 1){
		retorno = true;
	}
	
	return retorno;
 }
 
 
 function desabilitaCampoEsgoto(form){
 	
 	var situacaoEsgotoConta = returnObject(form, "situacaoEsgotoConta");
 	var consumoFaturadoEsgoto = returnObject(form, "consumoEsgoto");
 	var percentualEsgoto = returnObject(form, "percentualEsgoto");
 	var consumoFaturadoPoco = returnObject(form, "consumoFaturadoPoco");
 	
 	var ATIVO = document.getElementById("ATIVO").value;
 	var indicadorFaturamento = situacaoEsgotoConta.options[situacaoEsgotoConta.options.selectedIndex].id;
 	
 	if (indicadorFaturamento.length > 0 && indicadorFaturamento != ATIVO){
 		
 		consumoFaturadoEsgoto.value = "";
 		consumoFaturadoEsgoto.disabled = true;
 		
 		percentualEsgoto.value = "";
 		percentualEsgoto.disabled = true;
 		
 		consumoFaturadoPoco.value = "";
 		consumoFaturadoPoco.disabled = true;
 	}
 	else{
 		consumoFaturadoEsgoto.disabled = false;
 		percentualEsgoto.disabled = false;
 		consumoFaturadoPoco.disabled = false;
 	}
 }
 

function checkDataVencimento(){
	var form = document.forms[0];	
	form.indicadorDataAlterada = "sim";
}



function voltarTelaManter(){
	window.location.href= "exibirManterContaAction.do?idImovelRequest=" + document.forms[0].idImovel.value;
}

function habilitacaoConsumoEsgotoEPercentualColeta(){
 	var form = document.forms[0];

 	var listBoxEsgoto = document.forms[0].situacaoEsgotoConta;
	var consumoFaturadoEsgoto = document.forms[0].consumoEsgoto;
	var percentualColeta = document.forms[0].percentualColeta;
	var consumoAgua = document.forms[0].consumoAgua;
	var consumoPoco = document.forms[0].consumoFaturadoPoco;
	
	var consumo = 0;
 
 	var ATIVO = document.getElementById("ATIVO").value;
 	var indicadorFaturamento = listBoxEsgoto.options[listBoxEsgoto.options.selectedIndex].id;

 	if (indicadorFaturamento.length > 0 ){
 	 	if(indicadorFaturamento == ATIVO){
	 	
	 	 	if(percentualColeta.value != "" ){
		 	
	 			if(consumoAgua.value != ""){

					consumo = consumo + parseInt(consumoAgua.value);
	 			}
	 			
	 			if(consumoPoco.value != ""){
					
					consumo = consumo + parseInt(consumoPoco.value);
	 			}
	 			
	 			if(consumoAgua.value != "" || consumoPoco.value != ""){
	 				
	 				var percentual = percentualColeta.value;
	 				
					percentual = percentual.replace(/\./g, '');
					percentual = percentual.replace(/,/g, '.');
	 				
	 				//consumoFaturadoEsgoto.value = Math.round(consumo * (percentual/100));
	 				
	 			}
	 			
	 			if(consumoFaturadoEsgoto.value != ''){ 
	 				form.consumoFaturadoPoco.readOnly = true;
	 				form.percentualColeta.readOnly = true;
	 			}else{ 
		 			consumoFaturadoEsgoto.readOnly = true;
		 			form.percentualColeta.readOnly = true;
		 			
		 			if(form.consumoFaturadoPoco.value != '' || form.percentualColeta.value != ''){ 
	 					consumoFaturadoEsgoto.readOnly = true;
	 				}
		 		}
	 			
	 		}else if (consumoFaturadoEsgoto.value != ""){ 
				form.percentualColeta.readOnly = true;
				form.consumoFaturadoPoco.readOnly = true;
			}
	 	}else{
	 		
			form.percentualColeta.readOnly = true;
			consumoPoco.readOnly = true;
	 	}
	 	
	 	if(form.leituraAnteriorPoco.value == null || form.leituraAnteriorPoco.value == ""){
	 		form.leituraAnteriorPoco.readOnly = true;
	 	}
	 	
	 	if(form.leituraAtualPoco.value == null || form.leituraAtualPoco.value == ""){
	 		form.leituraAtualPoco.readOnly = true;
	 	}
	 	
 	}
}
// habilitacaoConsumoEsgoto(document.forms[0].situacaoEsgotoConta, document.forms[0].consumoEsgoto, this, document.forms[0].consumoAgua, document.forms[0].consumoFaturadoPoco);"
function habilitacaoConsumoEsgoto(listBoxEsgoto, consumoFaturadoEsgoto, percentualColeta, consumoAgua, consumoPoco){
 
 	var ATIVO = document.getElementById("ATIVO").value;
 	var indicadorFaturamento = listBoxEsgoto.options[listBoxEsgoto.options.selectedIndex].id;
 	var consumo = 0;

 	if (indicadorFaturamento.length > 0 && indicadorFaturamento == ATIVO){
 		
 		if(percentualColeta.value != ""){
	 	
 			if(consumoAgua.value != ""){
 			
				consumo = consumo + parseInt(consumoAgua.value);
 			}
 			
 			if(consumoPoco.value != ""){
 			
				consumo = consumo + parseInt(consumoPoco.value);
 			}
 			
 			if(consumoAgua.value != "" || consumoPoco.value != ""){
 				var percentual = percentualColeta.value;
				percentual = percentual.replace(/\./g, '');
				percentual = percentual.replace(/,/g, '.');
				consumoFaturadoEsgoto.value = Math.round(consumo * (percentual/100));
 			}
 			
 			if(((percentualColeta.value != '' && percentualColeta.readOnly == false) || (consumoPoco.value != '' && consumoPoco.readOnly == false)) && consumoFaturadoEsgoto.value !=''){
				consumoFaturadoEsgoto.value = "";
 				consumoFaturadoEsgoto.readOnly = true;
 			}
 			
 		}else{
	 		
	 		if (consumoFaturadoEsgoto.value != ""){
		 		percentualColeta.value = "";
				percentualColeta.readOnly = true;
				
			}
			if((consumoPoco.value != '' && consumoPoco.readOnly == false) || (percentualColeta.value != '' && percentualColeta.readOnly == false)){
				consumoFaturadoEsgoto.value = "";
 				consumoFaturadoEsgoto.readOnly = true;
 			}else{
 				consumoFaturadoEsgoto.readOnly = false;
 			}
 		}

 	}else{
 		if((consumoPoco.value != '' && consumoPoco.readOnly == false) || (percentualColeta.value != '' && percentualColeta.readOnly == false)){
			consumoFaturadoEsgoto.value = "";
 			consumoFaturadoEsgoto.readOnly = true;
 		}else{
 			consumoFaturadoEsgoto.readOnly = false;
 		}
 	}
 	
 	if(consumoPoco.value == '' && percentualColeta.value == ''){
 		consumoFaturadoEsgoto.readOnly = false;
 	}
 	
}

function habilitacaoConsumoPoco(listBoxEsgoto, consumoFaturadoEsgoto, consumoPoco){
 
 	var ATIVO = document.getElementById("ATIVO").value;
 	var indicadorFaturamento = listBoxEsgoto.options[listBoxEsgoto.options.selectedIndex].id;
 	
 	if (indicadorFaturamento.length > 0 && indicadorFaturamento == ATIVO){
 		if(consumoFaturadoEsgoto.value != ""){
			consumoPoco.value = "";
 			consumoPoco.readOnly = true;
 		}else{
 			consumoPoco.readOnly = false;
 		}

 	}else{
 		if(consumoFaturadoEsgoto.value != ""){
 			consumoPoco.value = "";
 			consumoPoco.readOnly = true;
 		}else{

 			consumoPoco.readOnly = false;
 		}
 	}
}

function habilitacaoPercentualColeta(listBoxEsgoto, consumoFaturadoEsgoto, percentualColeta){
 
 	var ATIVO = document.getElementById("ATIVO").value;
 	var indicadorFaturamento = listBoxEsgoto.options[listBoxEsgoto.options.selectedIndex].id;
 	
 	if (indicadorFaturamento.length > 0 && indicadorFaturamento == ATIVO){
 		
 		if(consumoFaturadoEsgoto.value != ""){
 			percentualColeta.readOnly = true;
 		}else{
 			percentualColeta.readOnly = false;
 		}

 	}else{
 		percentualColeta.readOnly = true;
 	}
}

function habilitaConsumoPoco(){
	var form = document.forms[0];
	
	if(form.consumoFaturadoPoco.value != '' && form.percentualColeta.value != ''){
		habilitacaoConsumoEsgoto(form.situacaoEsgotoConta, form.consumoEsgoto, form.percentualColeta, form.consumoAgua, form.consumoFaturadoPoco);
	}
	
	if((form.consumoFaturadoPoco.value != '' && form.consumoFaturadoPoco.readOnly == false) || (form.percentualColeta.value != '' && form.percentualColeta.readOnly == false)){
		
		form.consumoEsgoto.readOnly = true;
	}else{
		form.consumoEsgoto.readOnly = false;
	}
	
}

function desabilita(){
	var form = document.forms[0];
	if(form.consumoEsgoto.value == ''){
		
		form.consumoFaturadoPoco.readOnly = false;
		form.percentualColeta.readOnly = false;
	}
}

function desabilita2(){
	var form = document.forms[0];
	
	if(form.consumoEsgoto.value != ''){
		
		form.consumoEsgoto.readOnly = false;
	}else{
		if(form.consumoFaturadoPoco.value != '' || form.percentualColeta.value != ''){
			form.consumoEsgoto.readOnly = true;
		}
	}
}
 
  function reload(){
	var form = document.forms[0];
	form.situacaoAguaConta.disabled = false;
	form.situacaoEsgotoConta.disabled = false;
	form.action = "/gsan/exibirRetificarContaAction.do?reloadPage=1&selecionouMotivo=1";
	form.submit();
 }

 function habilitacaoCamposTela(){
 
	var HABILITA_SITUACAO_AGUA = document.getElementById("HABILITA_SITUACAO_AGUA").value;
	var HABILITA_CONSUMO_AGUA = document.getElementById("HABILITA_CONSUMO_AGUA").value;
	var HABILITA_LEITURA_ANTERIOR = document.getElementById("HABILITA_LEITURA_ANTERIOR").value;
	var HABILITA_LEITURA_ATUAL = document.getElementById("HABILITA_LEITURA_ATUAL").value;
	var HABILITA_SITUACAO_ESGOTO = document.getElementById("HABILITA_SITUACAO_ESGOTO").value;
	var HABILITA_CONSUMO_ESGOTO = document.getElementById("HABILITA_CONSUMO_ESGOTO").value;
	var HABILITA_PERCENTUAL_ESGOTO = document.getElementById("HABILITA_PERCENTUAL_ESGOTO").value;
	var HABILITA_PERCENTUAL_COLETA = document.getElementById("HABILITA_PERCENTUAL_COLETA").value;
	var HABILITA_LEITURA_ANTERIOR_POCO = document.getElementById("HABILITA_LEITURA_ANTERIOR_POCO").value;
	var HABILITA_LEITURA_ATUAL_POCO = document.getElementById("HABILITA_LEITURA_ATUAL_POCO").value;
	var HABILITA_VOLUMA_POCO = document.getElementById("HABILITA_VOLUMA_POCO").value;
	
	var form = document.forms[0];

 	if(HABILITA_SITUACAO_AGUA == "2"){
		form.situacaoAguaConta.disabled = true;
	}

	if(HABILITA_SITUACAO_ESGOTO == "2"){
		form.situacaoEsgotoConta.disabled = true;
	}
	
	if(HABILITA_CONSUMO_AGUA == "2"){
		form.consumoAgua.readOnly = true;
	}
	
	if(HABILITA_LEITURA_ANTERIOR == "2"){
		form.leituraAnteriorAgua.readOnly = true;
	}

	if(HABILITA_LEITURA_ATUAL == "2"){
		form.leituraAtualAgua.readOnly = true;
	}
	
	if(HABILITA_CONSUMO_ESGOTO == "2"){
		form.consumoEsgoto.readOnly = true;
	}
	
	if(HABILITA_PERCENTUAL_ESGOTO == "2"){
		form.percentualEsgoto.readOnly = true;
	}
	
	if(HABILITA_PERCENTUAL_COLETA == "2"){
		form.percentualColeta.readOnly = true;
	}
	
	if(HABILITA_LEITURA_ANTERIOR_POCO == "2"){
		form.leituraAnteriorPoco.readOnly = true;
	}
	
	if(HABILITA_LEITURA_ATUAL_POCO == "2"){
		form.leituraAtualPoco.readOnly = true;
	}
	
	if(HABILITA_VOLUMA_POCO == "2"){
		form.consumoFaturadoPoco.readOnly = true;
	}
	
}
 
//
--></SCRIPT>


<logic:present name="colecaoCategoria">

	<SCRIPT LANGUAGE="JavaScript">
	<!--
	function concatenarParametro(){
	var form = document.forms[0];
	
		form.situacaoAguaConta.disabled = false;
		form.situacaoEsgotoConta.disabled = false;
	 	abrirPopup("exibirAdicionarDebitoCobradoContaAction.do?imovel=" + document.forms[0].idImovel.value, 395, 450);
	 }
	 
	 function concatenarParametroCredito(){
	 var form = document.forms[0];
	 
	 	form.situacaoAguaConta.disabled = false;
		form.situacaoEsgotoConta.disabled = false;
	 	abrirPopup("exibirAdicionarCreditoRealizadoContaAction.do?imovel=" + document.forms[0].idImovel.value, 395, 450);
	 }
	 
	//-->
	</SCRIPT>
	
</logic:present>

<logic:present name="colecaoSubcategoria">

	<SCRIPT LANGUAGE="JavaScript">
	<!--
	function concatenarParametro(){
	var form = document.forms[0];
	
		form.situacaoAguaConta.disabled = false;
		form.situacaoEsgotoConta.disabled = false;
	 	abrirPopup("exibirAdicionarDebitoCobradoContaAction.do?imovel=" + document.forms[0].idImovel.value, 395, 450);
	 }
	 
	 function concatenarParametroCredito(){
	 	var form = document.forms[0];
	
		form.situacaoAguaConta.disabled = false;
		form.situacaoEsgotoConta.disabled = false;
	 	abrirPopup("exibirAdicionarCreditoRealizadoContaAction.do?imovel=" + document.forms[0].idImovel.value, 395, 450);
	 }
	 
	//-->
	</SCRIPT>
	
</logic:present>

</head>

<body leftmargin="5" topmargin="5" onload="habilitacaoConsumoEsgotoEPercentualColeta();habilitacaoCamposTela();">

<div id="formDiv"><html:form action="/exibirRetificarContaAction"
	method="post" >


<INPUT TYPE="hidden" ID="ATIVO" value="<%=ConstantesSistema.INDICADOR_USO_ATIVO%>"/>

<INPUT TYPE="hidden" ID="DATA_ATUAL" value="${requestScope.dataAtual}"/>
<INPUT TYPE="hidden" ID="DATA_ATUAL_60" value="${requestScope.dataAtual60}"/>
<INPUT TYPE="hidden" ID="ANO_LIMITE" value="${requestScope.anoLimite}"/>
<INPUT TYPE="hidden" ID="ID_CONDOMINIO" value="${requestScope.idImovelCondominio}"/>

<INPUT TYPE="hidden" ID="ULTIMA_DATA_MES" value="${requestScope.ultimaDataMes}"/>
<INPUT TYPE="hidden" ID="INDICADOR_CALCULA_VENCIMENTO" value="${requestScope.indicadorCalculaVencimento}"/>
<input type="hidden" name="cancelarValidacao" value="true" />
<INPUT TYPE="hidden" ID="CONSUMO_AGUA_ANTES" value="${sessionScope.consumoAguaAntes}"/>
<INPUT TYPE="hidden" ID="PERMISSAO_NAO_VERIFICAR_CONSUMO_ESGOTO" value="${requestScope.temPermissaoNaoVerificarConsumoEsgoto}"/>

<INPUT TYPE="hidden" ID="HABILITA_SITUACAO_AGUA" value="${requestScope.habilitaSituacaoAgua}"/>
<INPUT TYPE="hidden" ID="HABILITA_CONSUMO_AGUA" value="${requestScope.habilitaConsumoAgua}"/>
<INPUT TYPE="hidden" ID="HABILITA_LEITURA_ANTERIOR" value="${requestScope.habilitaLeituraAnterior}"/>
<INPUT TYPE="hidden" ID="HABILITA_LEITURA_ATUAL" value="${requestScope.habilitaLeituraAtual}"/>
<INPUT TYPE="hidden" ID="HABILITA_SITUACAO_ESGOTO" value="${requestScope.habilitaSituacaoEsgoto}"/>
<INPUT TYPE="hidden" ID="HABILITA_CONSUMO_ESGOTO" value="${requestScope.habilitaConsumoEsgoto}"/>
<INPUT TYPE="hidden" ID="HABILITA_PERCENTUAL_ESGOTO" value="${requestScope.habilitaPercentualEsgoto}"/>
<INPUT TYPE="hidden" ID="HABILITA_PERCENTUAL_COLETA" value="${requestScope.habilitaPercentualColeta}"/>
<INPUT TYPE="hidden" ID="HABILITA_VOLUMA_POCO" value="${requestScope.habilitaVolumePoco}"/>
<INPUT TYPE="hidden" ID="HABILITA_LEITURA_ANTERIOR_POCO" value="${requestScope.habilitaLeituraAnteriorPoco}"/>
<INPUT TYPE="hidden" ID="HABILITA_LEITURA_ATUAL_POCO" value="${requestScope.habilitaLeituraAtualPoco}"/>

<INPUT TYPE="hidden" ID="HABILITA_LISTA_CATEGORIAS" value="${requestScope.habilitaListaCategoriasEQuantidadesEconomias}"/>
<INPUT TYPE="hidden" ID="HABILITA_LISTA_DEBITOS" value="${requestScope.habilitaListaDebitos}"/>
<INPUT TYPE="hidden" ID="HABILITA_LISTA_CREDITOS" value="${requestScope.habilitaListaCreditos}"/>

<html:hidden property="consumoAguaAlterado" />


<%@ include file="/jsp/util/cabecalho.jsp"%>
<%@ include file="/jsp/util/menu.jsp" %>

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

	<td width="600" valign="top" class="centercoltext">

        <table height="100%">
        <tr>
          <td></td>
        </tr>
      	</table>

      <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_left.gif"/></td>
          <td class="parabg">Retificar Conta</td>
          <td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif"/></td>
        </tr>
      </table>
      <p>&nbsp;</p>
      
	<table width="100%" border="0">
	<tr>
		<logic:present scope="application" name="urlHelp">
			<td align="right"><a href="javascript:abrirPopupHelp('${applicationScope.urlHelp}faturamentoContaRetificar', 500, 700);"><span style="font-weight: bold"><font color="#3165CE">Ajuda</font></span></a></td>									
		</logic:present>
		<logic:notPresent scope="application" name="urlHelp">
			<td align="right"><span style="font-weight: bold"><font color=#696969><u>Ajuda</u></font></span></td>									
		</logic:notPresent>
	</tr>
	

      <tr> 
          <td colspan="4"></td>
      </tr>
      <tr> 
          <td colspan="4">
				
				<table width="100%" align="center" bgcolor="#99CCFF" border="0">
				<tr>
					<td><strong>Dados do Imóvel</strong></td>
				</tr>
				<tr bgcolor="#cbe5fe">
					<td width="100%" align="center">
			
						<table width="100%" border="0">
						<tr> 
							<td height="10" width="145"><strong>Matrícula do Imóvel:</strong></td>
				          	<td>
				          		<html:text property="idImovel" size="9" readonly="true" style="background-color:#EFEFEF; border:0"/>
				          		<html:text property="inscricaoImovel" size="32" readonly="true" style="background-color:#EFEFEF; border:0"/>
						  	</td>
						</tr>
						<tr> 
							<td height="10"><strong>Nome do Cliente Usuário:</strong></td>
							<td>
								<html:text property="nomeClienteUsuario" size="45" readonly="true" style="background-color:#EFEFEF; border:0"/>
							</td>
						</tr>
						<tr> 
							<td height="10"><strong>Situação de Água:</strong></td>
							<td>
								<html:text property="situacaoAguaImovel" size="45" readonly="true" style="background-color:#EFEFEF; border:0"/>
							</td>
						</tr>
						<tr> 
							<td height="10"><strong>Situação de Esgoto:</strong></td>
							<td>
								<html:text property="situacaoEsgotoImovel" size="45" readonly="true" style="background-color:#EFEFEF; border:0"/>
							</td>
						</tr>
						</table>

					</td>
				</tr>
				</table>

		  </td>
      </tr>
      <tr>
      	<td colspan="4" height="10"></td>
      </tr>
      
      <tr> 
          <td colspan="4">
				
				<table width="100%" align="center" bgcolor="#99CCFF" border="0">
				<tr>
					<td><strong>Dados da Conta:</strong></td>
				</tr>
				<tr bgcolor="#cbe5fe">
					<td width="100%" align="center">
			
						<table width="100%" border="0">
						<tr> 
							<td width="145"><strong>Mês e Ano da Conta:</strong></td>
          					<td><html:text property="mesAnoConta" size="8" readonly="true" style="background-color:#EFEFEF; border:0"/></td>
						</tr>
						<tr> 
				          	<td height="10"><strong>Motivo da Retificação:<font color="#FF0000">*</font></strong></td>
				          	<td>
								<html:select property="motivoRetificacaoID" style="width: 400px;" tabindex="3" onchange="reload();">
									<html:option value="<%= ""+ConstantesSistema.NUMERO_NAO_INFORMADO %>">&nbsp;</html:option>
									<logic:present name="colecaoMotivoRetificacaoConta">
										<html:options collection="colecaoMotivoRetificacaoConta" labelProperty="descricao" property="id" />
									</logic:present>
								</html:select>
						  	</td>
				      	</tr>	
				      	
						<!-- Alterado por Hugo Leonardo, 18/078/2010  -->
						<logic:equal name="indicadorCalculaVencimento" value="<%= "" + ConstantesSistema.INDICADOR_USO_ATIVO %>" scope="session">
				      		<tr> 
					          	<td height="10"><strong>Data de Vencimento:<font color="#FF0000">*</font></strong></td>
					          	<html:hidden property="vencimentoContaNaBase" />
					          	<td>
									<html:text property="vencimentoConta" 
											   size="11" 
											   maxlength="10" 
											   tabindex="4" 
											   onkeyup="mascaraData(this, event);" 
											   style="background-color:#EFEFEF; border:0"
											   onclick="javascript:checkDataVencimento();"
											   readonly="true" />
							  	</td>
			      			</tr>
				      	</logic:equal>
				      	
				      	<logic:notEqual name="indicadorCalculaVencimento" value="<%= "" + ConstantesSistema.INDICADOR_USO_ATIVO %>" scope="session">
				      		<tr> 
					          	<td height="10"><strong>Data de Vencimento:<font color="#FF0000">*</font></strong></td>
					          	<html:hidden property="vencimentoContaNaBase" />
					          	<td>
                    				<html:text property="vencimentoConta" 
                    						   size="11" 
                    						   maxlength="10" 
                    						   tabindex="4" 
                    						   onkeyup="mascaraData(this, event);" 
                    						   onclick="javascript:checkDataVencimento();"/>
									<a href="javascript:abrirCalendario('RetificarContaActionForm', 'vencimentoConta');checkDataVencimento();">
				                 		<img border="0" src="<bean:message key='caminho.imagens'/>calendario.gif" width="20" border="0" align="absmiddle" alt="Exibir Calendário"/></a> dd/mm/aaaa
							  	</td>
			      			</tr>
				      	</logic:notEqual>
			      		
				      	
				      	<!-- Adicionado por Bruno Barros, 25/07/2008  -->
				     
						<tr> 
				          	<td height="10"><strong>Tarifa de Consumo:<font color="#FF0000">*</font></strong></td>
							<td>
								<logic:equal name="temPermissaoAlterarTarifaConsumo"
									value="true">				      								
									<html:select property="idConsumoTarifa" style="width: 190px;" tabindex="5">
										<logic:present name="colecaoConsumoTarifa">										
												<option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</option>															    	
												<logic:iterate name="colecaoConsumoTarifa" id="consumoTarifa" type="ConsumoTarifa">													      				
													<logic:equal name="RetificarContaActionForm" property="idConsumoTarifa" value="<%="" + consumoTarifa.getId() %>">
														<option SELECTED value="<%="" + consumoTarifa.getId() %>"><%="" + consumoTarifa.getDescricao() %></option>
													</logic:equal>
														      				
													<logic:notEqual name="RetificarContaActionForm" property="idConsumoTarifa" value="<%="" + consumoTarifa.getId() %>">
														<option value="<%="" + consumoTarifa.getId() %>"><%="" + consumoTarifa.getDescricao() %></option>
													</logic:notEqual>													      			
												</logic:iterate>											
										</logic:present>									
									</html:select>
								</logic:equal>
								<logic:notEqual name="temPermissaoAlterarTarifaConsumo"
									value="true">
									<html:select property="idConsumoTarifa" style="width: 190px;" tabindex="5" disabled="true">
										<logic:present name="colecaoConsumoTarifa">										
												<option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</option>															    	
												<logic:iterate name="colecaoConsumoTarifa" id="consumoTarifa" type="ConsumoTarifa">													      				
													<logic:equal name="RetificarContaActionForm" property="idConsumoTarifa" value="<%="" + consumoTarifa.getId() %>">
														<option SELECTED value="<%="" + consumoTarifa.getId() %>"><%="" + consumoTarifa.getDescricao() %></option>
													</logic:equal>
														      				
													<logic:notEqual name="RetificarContaActionForm" property="idConsumoTarifa" value="<%="" + consumoTarifa.getId() %>">
														<option value="<%="" + consumoTarifa.getId() %>"><%="" + consumoTarifa.getDescricao() %></option>
													</logic:notEqual>													      			
												</logic:iterate>											
										</logic:present>	
									</html:select>																			
								</logic:notEqual>								
							</td>				      	
						</tr>					      	
						<!-- Fim adicionado por Bruno Barros -->
						
			
				</table>

		  </td>
      </tr>
      <tr>
      	<td colspan="4" height="10"></td>
      </tr>
      <tr> 
          <td colspan="4">
				
				<table width="100%" align="center" bgcolor="#99CCFF">
				<tr>
					<td><strong>Dados de Água:</strong></td>
				</tr>
				<tr bgcolor="#cbe5fe">
					<td width="100%" align="center">
			
						<table width="100%" border="0">
						<tr> 
							<td height="10" width="140"><strong>Situação de Água:</strong></td>
							<td>
									<html:select property="situacaoAguaConta" style="width: 190px;" tabindex="5" onchange="desabilitaCampoAgua(document.forms[0]);" >
									
										<logic:present name="colecaoSituacaoLigacaoAgua">
											
												<option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</option>
																    	
												<logic:iterate name="colecaoSituacaoLigacaoAgua" id="ligacaoAguaSituacao" type="LigacaoAguaSituacao">
														      				
													<logic:equal name="RetificarContaActionForm" property="situacaoAguaConta" value="<%="" + ligacaoAguaSituacao.getId() %>">
														<option SELECTED value="<%="" + ligacaoAguaSituacao.getId() %>" id="<%="" + ligacaoAguaSituacao.getIndicadorFaturamentoSituacao() %>" name="<%="" + ligacaoAguaSituacao.getConsumoMinimoFaturamento() %>"><%="" + ligacaoAguaSituacao.getDescricao() %></option>
													</logic:equal>
														      				
													<logic:notEqual name="RetificarContaActionForm" property="situacaoAguaConta" value="<%="" + ligacaoAguaSituacao.getId() %>">
														<option value="<%="" + ligacaoAguaSituacao.getId() %>" id="<%="" + ligacaoAguaSituacao.getIndicadorFaturamentoSituacao() %>" name="<%="" + ligacaoAguaSituacao.getConsumoMinimoFaturamento() %>"><%="" + ligacaoAguaSituacao.getDescricao() %></option>
													</logic:notEqual>
														      			
												</logic:iterate>
												
										</logic:present>
										
									</html:select>	
							</td>
							<td colspan="2"></td>
						</tr>
						<tr> 
							<td height="10"><strong>Consumo de Água:</strong></td>
							<td>
								<html:text property="consumoAgua" size="10" maxlength="6" tabindex="6" style="text-align: right;" onkeypress="javascript:return isCampoNumerico(event);"/>
							</td>
							<td><strong>Valor de Água:</strong></td>
							<td>
								<html:text property="valorAgua" size="18" readonly="true" style="background-color:#EFEFEF; border:0; text-align: right;"/>
							</td>
						</tr>
						
						<tr>
							<td height="10"><strong>Leitura Anterior:</strong></td>
							<td>
							<html:text property="leituraAnteriorAgua" size="10" maxlength="6"
								tabindex="6" style="text-align: right;" 
								 onkeypress="javascript:return isCampoNumerico(event);" />
							</td>
							
						</tr>
						<tr>
							<td height="10"><strong>Leitura Atual:</strong></td>
							<td>
							<html:text property="leituraAtualAgua" size="10" maxlength="6"
								tabindex="6" style="text-align: right;" 
								 onkeypress="javascript:return isCampoNumerico(event);" />
							</td>
							
						</tr>
						
						</table>

					</td>
				</tr>
				</table>

		  </td>
      </tr>
      <tr>
      	<td colspan="4" height="10"></td>
      </tr>
	  <tr> 
          <td colspan="4">
				
				<table width="100%" align="center" bgcolor="#99CCFF">
				<tr>
					<td><strong>Dados de Esgoto:</strong></td>
				</tr>
				<tr bgcolor="#cbe5fe">
					<td width="100%" align="center">
			
						<table width="100%" border="0">
						<tr> 
							<td height="10" width="140"><strong>Situação de Esgoto:</strong></td>
							<td>
								<html:select property="situacaoEsgotoConta" 
											 style="width: 190px;" 
											 tabindex="7" 
											 onchange="desabilitaCampoEsgoto(document.forms[0]); habilitacaoConsumoPoco(document.forms[0].situacaoEsgotoConta, this, document.forms[0].consumoFaturadoPoco);">
									
									<logic:present name="colecaoSituacaoLigacaoEsgoto">
										
											<option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</option>
															    	
											<logic:iterate name="colecaoSituacaoLigacaoEsgoto" id="ligacaoEsgotoSituacao" type="LigacaoEsgotoSituacao">
													      				
												<logic:equal name="RetificarContaActionForm" property="situacaoEsgotoConta" value="<%="" + ligacaoEsgotoSituacao.getId() %>">
													<option SELECTED value="<%="" + ligacaoEsgotoSituacao.getId() %>" id="<%="" + ligacaoEsgotoSituacao.getIndicadorFaturamentoSituacao() %>" name="<%="" + ligacaoEsgotoSituacao.getVolumeMinimoFaturamento() %>"><%="" + ligacaoEsgotoSituacao.getDescricao() %></option>
												</logic:equal>
													      			
												<logic:notEqual name="RetificarContaActionForm" property="situacaoEsgotoConta" value="<%="" + ligacaoEsgotoSituacao.getId() %>">
													<option value="<%="" + ligacaoEsgotoSituacao.getId() %>" id="<%="" + ligacaoEsgotoSituacao.getIndicadorFaturamentoSituacao() %>" name="<%="" + ligacaoEsgotoSituacao.getVolumeMinimoFaturamento() %>"><%="" + ligacaoEsgotoSituacao.getDescricao() %></option>
												</logic:notEqual>
													      				
											</logic:iterate>
											
									</logic:present>
									
								</html:select>
							</td>
							<td colspan="2"></td>
						</tr>
						
						<tr> 
							<td height="10"><strong>Volume de Esgoto:</strong></td>
							<td>
								<html:text property="consumoEsgoto" 
										   size="10" 
										   maxlength="6" 
										   tabindex="8" 
										   style="text-align: right;" 
										   onkeyup = "desabilita();"
										   onchange ="habilitacaoConsumoPoco(document.forms[0].situacaoEsgotoConta, this, document.forms[0].consumoFaturadoPoco); habilitacaoPercentualColeta(document.forms[0].situacaoEsgotoConta, this, document.forms[0].percentualColeta);"
										   onkeypress="javascript:return isCampoNumerico(event);"/>
							</td>
							<td><strong>Valor de Esgoto:</strong></td>
							<td>
								<html:text property="valorEsgoto" size="18" readonly="true" style="background-color:#EFEFEF; border:0; text-align: right;"/>
							</td>
						</tr>
						
						<tr> 
							<td height="10"><strong>Percentual de Esgoto:</strong></td>
							<td>
								<html:text property="percentualEsgoto" 
										   size="10" 
										   maxlength="6" 
										   tabindex="9" 
										   onkeyup="formataValorMonetario(this, 5);" style="text-align: right;"/>%
							</td>
							<td colspan="2"></td>
						</tr>
						
						<tr> 
							<td height="10"><strong>Percentual de Coleta:</strong></td>
							<td>
								<input type="text" 
									name="percentualColeta" 
									value="<bean:write name="RetificarContaActionForm" property="percentualColeta"/>" 
									size="10" 
									maxlength="6" 
									tabindex="11" 
									style="text-align: right;" 
									onKeyup="formataValorMonetario(this, 5); habilitacaoConsumoEsgoto(document.forms[0].situacaoEsgotoConta, document.forms[0].consumoEsgoto, this, document.forms[0].consumoAgua, document.forms[0].consumoFaturadoPoco);"
									/>%
							</td>
						</tr>
						
						<tr> 
					      	<td height="10"><strong>Volume do Poço:</strong></td>
							<td>
								<html:text property="consumoFaturadoPoco" 
										   size="10" 
										   maxlength="6" 
										   tabindex="10" 
										   style="text-align: right;" 
										   onkeypress="javascript:return isCampoNumerico(event);"
										   onkeyup="javascript:habilitaConsumoPoco();"/>
							</td>
						</tr>
						
						<tr>
							<td height="10"><strong>Leitura Anterior:</strong></td>
							<td>
							<html:text property="leituraAnteriorPoco" size="10" maxlength="6"
								tabindex="6" style="text-align: right;" 
								 onkeypress="javascript:return isCampoNumerico(event);" />
							</td>
							
						</tr>
						<tr>
							<td height="10"><strong>Leitura Atual:</strong></td>
							<td>
							<html:text property="leituraAtualPoco" size="10" maxlength="6"
								tabindex="6" style="text-align: right;" 
								 onkeypress="javascript:return isCampoNumerico(event);" />
							</td>
							
						</tr>
						
						</table>

					</td>
				</tr>
				</table>

		  </td>
      </tr>
      <tr>
      	<td colspan="4" height="5"></td>
      </tr>
      <tr>
      	<td colspan="4">
      		<table width="100%" border="0">
			<tr> 
				
				<td height="10" width="140"><strong>Valor dos Débitos:</strong></td>
		        <td width="160">
					<html:text property="valorDebito" size="21" readonly="true" style="background-color:#EFEFEF; border:0; text-align: right;"/>
		        </td>
		        <td colspan="2">
		    		<table width="100%" border="0">
					<tr> 
						<td height="10" width="140"><strong>Valor dos Créditos:</strong></td>
		        		<td>
							<html:text property="valorCredito" size="21" readonly="true" style="background-color:#EFEFEF; border:0; text-align: right;"/>
		        		</td>
		        	</tr>
		        	</table>
		        </td>
			</tr>
			<tr>
				<td height="10"><strong>Valor Rateio Agua:</strong></td>
		        <td>
					<html:text property="valorRateioAgua" size="21" readonly="true" style="background-color:#EFEFEF; border:0; text-align: right;"/>
		        </td>
		        <td colspan="2" align="right"></td>
			</tr>
			<tr>
				<td height="10"><strong>Valor Rateio Esgoto:</strong></td>
		        <td>
					<html:text property="valorRateioEsgoto" size="21" readonly="true" style="background-color:#EFEFEF; border:0; text-align: right;"/>
		        </td>
		        <td colspan="2" align="right"></td>
			</tr>
			<tr>
				<td height="10"><strong>Valor Total da Conta:</strong></td>
		        <td>
					<html:text property="valorTotal" size="21" readonly="true" style="background-color:#EFEFEF; border:0; text-align: right;"/>
		        </td>
		        <td colspan="2" align="right"></td>
			</tr>
			</table>
      	</td>
      </tr>
      <tr> 
          <td height="10" width="100"></td>
          <td colspan="3"><strong><font color="#FF0000">*</font></strong> 
                    Campos obrigat&oacute;rios
		  </td>
      </tr>
      
      <%-- Colocado por Raphael Rossiter em 17/04/2007
			Objetivo: Manipulação dos objetos que serão exibidos no formulário de acordo com a empresa --%>
								
	  <%-- ================================================================================= --%>
				
				<%int cont = 0; String cor = null;%>

				<logic:present name="colecaoCategoria">
				
					<tr>
						<td colspan="4" height="5"></td>
					</tr>
					<tr>
						<td colspan="4">
							
							<table width="100%" border="0">
							<tr>
								<td height="17" colspan="3"><strong>Categorias e Economias:</strong></td>
								<td align="right">
									<logic:equal name="habilitaListaCategoriasEQuantidadesEconomias" value="2">
										<input type="button" class="bottonRightCol"
										value="Adicionar" tabindex="11" style="width: 80px"
										disabled="true">				
									</logic:equal>
									<logic:notEqual name="habilitaListaCategoriasEQuantidadesEconomias" value="2">
										<input type="button" class="bottonRightCol"
										value="Adicionar" tabindex="11" style="width: 80px"
										onclick="javascript:abrirPopup('exibirAdicionarCategoriaContaAction.do', 295, 450);">
									</logic:notEqual>
								</td>
							</tr>

							<tr>
								<td colspan="4">

								<table width="100%" cellpadding="0" cellspacing="0">
									<tr>
										<td>

										<table width="100%" bgcolor="#99CCFF">
											<tr bgcolor="#99CCFF">

												<td align="center" width="10%"><strong>Remover</strong></td>
												<td width="60%">
												<div align="center"><strong>Categoria</strong></div>
												</td>
												<td width="30%">
												<div align="center"><strong>Quantidade de Economias</strong></div>
												</td>

											</tr>
										</table>

										</td>
									</tr>

									<tr>
										<td>

										<div style="width: 100%; height: 100; overflow: auto;">
										<%cont = 0;
										if(session.getAttribute("existeColecao") != null){
				   						%>
											<input type="hidden" name="existeColecao" value="1">
										<%}
				   						else{
										%>
											<input type="hidden" name="existeColecao" value="0">
										<%}%>

										<table width="100%" align="center" bgcolor="#99CCFF">

											<logic:iterate name="colecaoCategoria" id="categoria"
												type="Categoria">



												<%cont = cont + 1;
										if (cont % 2 == 0) {%>
										<tr bgcolor="#cbe5fe">
											<%} else {%>
										<tr bgcolor="#FFFFFF">
											<%}%>

													<td align="center" width="10%" valign="middle">
														<logic:notEqual name="habilitaListaCategoriasEQuantidadesEconomias" value="2">
															<a href="javascript:removerCategoria(<%="" + categoria.getId().intValue()%>)" >
																<img src="<bean:message key='caminho.imagens'/>Error.gif" border="0" >
															</a>
														</logic:notEqual>
													</td>

													<td width="60%"><bean:write name="categoria"
														property="descricao" /></td>
													<td width="30%">
													
													<logic:equal name="habilitaListaCategoriasEQuantidadesEconomias" value="2">
														<div align="center"><INPUT TYPE="text"
															NAME="categoria<%="" + categoria.getId().intValue()%>"
															size="6" id="categoria" maxlength="4"
															value="<%="" + categoria.getQuantidadeEconomiasCategoria()%>"
															style="text-align: right;" readOnly="true" /></div>
													</logic:equal>
													<logic:notEqual name="habilitaListaCategoriasEQuantidadesEconomias" value="2">
														<div align="center"><INPUT TYPE="text"
															NAME="categoria<%="" + categoria.getId().intValue()%>"
															size="6" id="categoria" maxlength="4"
															value="<%="" + categoria.getQuantidadeEconomiasCategoria()%>"
															style="text-align: right;" onkeypress="javascript:return isCampoNumerico(event);" /></div>
													</logic:notEqual>
													
													
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
								<tr>
									<td colspan="4" align="right">
										<input type="button" class="bottonRightCol" value="Calcular" tabindex="10"
											onclick="validarForm(document.forms[0], false);" style="width: 80px">
									</td>
								</tr>
							</table>
										
							
						</td>
					</tr>
					<tr>
						<td colspan="4" height="5"></td>
					</tr>
					<tr>
						<td colspan="4">
							<table width="100%" border="0">
								<tr>
									<td height="17" colspan="3"><strong>Débitos Cobrados:</strong></td>
									<td align="right">
									
										<logic:equal name="habilitaListaDebitos" value="2">
											<input type="button" class="bottonRightCol" value="Adicionar"
												tabindex="11" style="width: 80px" disabled="true"">
										</logic:equal>
										<logic:notEqual name="habilitaListaDebitos" value="2">
											<logic:present name="colecaoCategoria">
												<input type="button" class="bottonRightCol" value="Adicionar"
													tabindex="11" style="width: 80px" onclick="concatenarParametro();">
											</logic:present>
											<logic:notPresent name="colecaoCategoria">
												<input type="button" class="bottonRightCol" value="Adicionar"
													tabindex="11" style="width: 80px" onclick="alert('É necessário informar o imóvel');" />
											</logic:notPresent>
										</logic:notEqual>
										
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<table width="100%" cellpadding="0" cellspacing="0">
											<tr>
												<td>
													<table width="100%" bgcolor="#99CCFF">
														<tr bgcolor="#99CCFF">
															<td align="center" width="10%"><strong>Remover</strong></td>
															<td align="center" width="30%"><strong>Tipo do Débito</strong></td>
															<td align="center" width="20%"><strong>Mês/Ano do Débito</strong></td>
															<td align="center" width="20%"><strong>Mês/Ano da Cobrança</strong></td>
															<td align="center" width="20%"><strong>Valor do Débito</strong></td>
														</tr>
													</table>
												</td>
											</tr>	
											<logic:present name="colecaoDebitoCobrado">
											<tr>
												<td>
													<div style="width: 100%; height: 100; overflow: auto;">
													<table width="100%" align="center" bgcolor="#99CCFF">
													<logic:iterate name="colecaoDebitoCobrado"
														id="debitoCobrado" type="DebitoCobrado">
														<%cont = cont + 1;
														if (cont % 2 == 0) {%>
														<tr bgcolor="#cbe5fe">
														<%} else {%>
														<tr bgcolor="#FFFFFF">
														<%}%>
															<td align="center" width="10%" valign="middle">
																<logic:notEqual name="habilitaListaDebitos" value="2">
																	<a href="javascript:removerDebitoCobrado('<%="" + GcomAction.obterTimestampIdObjeto(debitoCobrado)%>')">
																	<img src="<bean:message key='caminho.imagens'/>Error.gif" border="0"></a>
																</logic:notEqual>
															</td>
															<td width="30%">
																<bean:write name="debitoCobrado" property="debitoTipo.descricao" />
															</td>
															<td align="center" width="20%">
																<logic:present name="debitoCobrado" property="anoMesReferenciaDebito">
																	<%=""+ Util.formatarMesAnoReferencia(debitoCobrado
																		.getAnoMesReferenciaDebito())%>
																</logic:present>
																<logic:notPresent name="debitoCobrado" property="anoMesReferenciaDebito">
																	&nbsp;
																</logic:notPresent>
															</td>
															<td align="center" width="20%">
																<logic:present name="debitoCobrado" property="anoMesCobrancaDebito">
																	<%=""+ Util.formatarMesAnoReferencia(debitoCobrado
																		.getAnoMesCobrancaDebito())%>
																</logic:present>
																<logic:notPresent name="debitoCobrado" property="anoMesCobrancaDebito">
																	&nbsp;
																</logic:notPresent>
															</td>
															<td align="center" width="20%">

														<logic:present name="debitoCobrado" property="debitoTipo.financiamentoTipo">
															<logic:equal name="debitoCobrado" property="debitoTipo.financiamentoTipo.id" value="<%= "" + FinanciamentoTipo.SERVICO_NORMAL %>">
															
																<logic:equal name="habilitaListaDebitos" value="2">
																		<input type="text"  name="debitoCobrado<%="" + GcomAction.obterTimestampIdObjeto(debitoCobrado) %>"
																		size="14" maxlength="14" id="debito" value="<%="" + Util.formatarMoedaReal(debitoCobrado.getValorPrestacao())%>"
																		style="text-align: right;" onkeyup="formataValorMonetario(this, 11)" readOnly="true"/>	
																</logic:equal>
																<logic:notEqual name="habilitaListaDebitos" value="2">
																		<input type="text"  name="debitoCobrado<%="" + GcomAction.obterTimestampIdObjeto(debitoCobrado) %>"
																		size="14" maxlength="14" id="debito" value="<%="" + Util.formatarMoedaReal(debitoCobrado.getValorPrestacao())%>"
																		style="text-align: right;" onkeyup="formataValorMonetario(this, 11)" />
																</logic:notEqual>
															
															</logic:equal>

															<logic:notEqual name="debitoCobrado" property="debitoTipo.financiamentoTipo.id" value="<%= "" + FinanciamentoTipo.SERVICO_NORMAL %>">
																<input type="text"  name="debitoCobrado<%="" + GcomAction.obterTimestampIdObjeto(debitoCobrado) %>"
																	size="14" maxlength="14" id="debito" value="<%="" + Util.formatarMoedaReal(debitoCobrado.getValorPrestacao())%>"
																	style="text-align: right;" onkeyup="formataValorMonetario(this, 11)" readOnly="true" />
															</logic:notEqual>
														</logic:present>
																
															</td>
														</tr>
													</logic:iterate>
													</table>
													</div>
												</td>
											</tr>
											</logic:present>
										</table>
									</td>
								</tr>
							</table>
						</td>
					</tr>
					<tr>
			      		<td colspan="4" height="5"></td>
			       	</tr>
			       	<tr>
			      		<td colspan="4">
			      	
			      		<table width="100%" border="0">
				  		<tr> 
			          		<td height="17" colspan="3"><strong>Créditos Realizados:</strong></td>
			          		<td align="right">
			          		
			          			<logic:equal name="habilitaListaCreditos" value="2">
									<input type="button" class="bottonRightCol" value="Adicionar"
										tabindex="11" style="width: 80px" disabled="true"">
								</logic:equal>
								<logic:notEqual name="habilitaListaCreditos" value="2">
									<logic:present name="colecaoCategoria">
					          			<input type="button" class="bottonRightCol" value="Adicionar" tabindex="11" style="width: 80px" onclick="concatenarParametroCredito();">
					      			</logic:present>
					      			
					      			<logic:notPresent name="colecaoCategoria">
					          			<input type="button" class="bottonRightCol" value="Adicionar" tabindex="11" style="width: 80px" onclick="alert('É necessário informar o imóvel');">
					      			</logic:notPresent>
								</logic:notEqual>
			          		
			      			</td>
			      		</tr>
			      		
			      		
			      		<tr> 
			          		<td colspan="4">
					  
								<table width="100%" cellpadding="0" cellspacing="0">
								<tr> 
			                		<td> 
								
										<table width="100%" bgcolor="#99CCFF">
			                    		<tr bgcolor="#99CCFF"> 
			
											<td align="center" width="10%"><strong>Remover</strong></td>
											<td width="30%"><div align="center"><strong>Tipo do Crédito</strong></div></td>
											<td width="15%"><div align="center"><strong>Mês/Ano do Crédito</strong></div></td>
											<td width="15%"><div align="center"><strong>Mês/Ano da Cobrança</strong></div></td>
											<td width="20%"><div align="center"><strong>Valor do Crédito</strong></div></td>
								
										</tr>
			                    		</table>
								
									</td>
			            		</tr>
			            		
			            		<logic:present name="colecaoCreditoRealizado">
			            
			            		<tr> 
									<td> 
								
										<div style="width: 100%; height: 100; overflow: auto;">
										
										<%cor = "#cbe5fe";%>
										
										<table width="100%" align="center" bgcolor="#99CCFF">	
			
											<logic:iterate name="colecaoCreditoRealizado" id="creditoRealizado" type="CreditoRealizado">
			                            
												
												<%	if (cor.equalsIgnoreCase("#cbe5fe")){
													cor = "#FFFFFF";%>
													<tr bgcolor="#FFFFFF">
												<%} else{
													cor = "#cbe5fe";%>
													<tr bgcolor="#cbe5fe">
												<%}%>
												 
													<td align="center" width="10%" valign="middle">
														<logic:notEqual name="habilitaListaCreditos" value="2">
															<a href="javascript:removerCreditoRealizado('<%="" + GcomAction.obterTimestampIdObjeto(creditoRealizado)%>')">
																<img src="<bean:message key='caminho.imagens'/>Error.gif" border="0" >
															</a>
														</logic:notEqual>
													</td>
													<td width="30%">
													
														<bean:write name="creditoRealizado" property="creditoTipo.descricao"/>
														
													</td>
													<td width="20%">
													<div align="center">
			
														<logic:present name="creditoRealizado" property="anoMesReferenciaCredito">	
															<%=""+ Util.formatarMesAnoReferencia(creditoRealizado.getAnoMesReferenciaCredito())%>
														</logic:present>
													
														<logic:notPresent name="creditoRealizado" property="anoMesReferenciaCredito">
															&nbsp;
														</logic:notPresent>
													
													</div>
													</td>
													<td width="20%">
													<div align="center">
													
														<logic:present name="creditoRealizado" property="anoMesCobrancaCredito">
															<%=""+ Util.formatarMesAnoReferencia(creditoRealizado.getAnoMesCobrancaCredito())%>
														</logic:present>
													
														<logic:notPresent name="creditoRealizado" property="anoMesCobrancaCredito">
															&nbsp;
														</logic:notPresent>
														
													</div>
													</td>
													<td width="20%">
													<div align="center">
														<logic:equal name="habilitaListaCreditos" value="2">
															<INPUT TYPE="text" NAME="creditoRealizado<%="" + GcomAction.obterTimestampIdObjeto(creditoRealizado)%>" size="14" maxlength="14" value="<%="" + Util.formatarMoedaReal(creditoRealizado.getValorCredito())%>" id="credito" style="text-align: right;" onkeyup="formataValorMonetario(this, 11)" readOnly="true"/>
														</logic:equal>
														<logic:notEqual name="habilitaListaCreditos" value="2">
															<INPUT TYPE="text" NAME="creditoRealizado<%="" + GcomAction.obterTimestampIdObjeto(creditoRealizado)%>" size="14" maxlength="14" value="<%="" + Util.formatarMoedaReal(creditoRealizado.getValorCredito())%>" id="credito" style="text-align: right;" onkeyup="formataValorMonetario(this, 11)"/>
														</logic:notEqual>
													
													
														
														
													</div>
													</td>
												</tr>
												
			
											</logic:iterate>
											</table>
										</div>
									</td>
			            		</tr>
			            		
			            		</logic:present>
			            		
			            		</table>
							</td>
						</tr>
						
						</table>
							
			      		</td>
			      	</tr>
				
				</logic:present>
				
				<%-- ================================================================================= --%>
				<%-- ================================================================================= --%>
				
				
				
				<%-- Colocado por Raphael Rossiter em 17/04/2007
					Objetivo: Manipulação dos objetos que serão exibidos no formulário de acordo com a empresa --%>
								
				<%-- ================================================================================= --%>
				
				<logic:present name="colecaoSubcategoria">
				
					<tr>
						<td colspan="4" height="5"></td>
					</tr>
					<tr>
						<td colspan="4">

							
							<table width="100%" border="0">
							<tr> 
								<td class="style1"><strong>Subcategorias e Economias:</strong><strong><font color="#FF0000">*</font></strong></td>
								<td colspan="2" class="style1"><div align="right"> 
									<input type="button" class="bottonRightCol" value="Adicionar" tabindex="9" style="width: 80px" 
										onclick="javascript:abrirPopup('exibirAdicionarCategoriaContaAction.do', 295, 450);"></div>
								</td>
							</tr>
							<tr> 
								<td colspan="4">
									<table width="100%" cellpadding="0" cellspacing="0">
										
										<tr> 
											<td> 
												
												<div style="width: 100%; height=150; overflow: auto;">
					   						    <%
					   						    cont = 0;
					   						    if(session.getAttribute("existeColecao") != null){
					   						    %>
													<input type="hidden" name="existeColecao" value="1">
												<%}
					   						    else{
												%>
													<input type="hidden" name="existeColecao" value="0">
												<%}%>
				                                
				                                <logic:present name="colecaoSubcategoria">
				                                
				                                <% Integer idCategoria = null; %>
		        		                        
		        		                        <table width="100%" align="center" bgcolor="#99CCFF">
											    
											    <logic:iterate name="colecaoSubcategoria" id="subcategoria" type="Subcategoria">
											    
											    	<% if (idCategoria == null || 
											    		   idCategoria.intValue() != subcategoria.getCategoria().getId().intValue()){ %>
											    	
											    		<tr bgcolor="#79bbfd"> 
															<td colspan="3"><strong><bean:write name="subcategoria" property="categoria.descricao"/></strong></td>
														</tr>
											    	
											    		<tr bgcolor="#99CCFF"> 
															<td align="center" width="10%"><strong>Remover</strong></td>
															<td align="center" width="60%"><strong>Subcategoria</strong></td>
															<td align="center" width="30%"><strong>Quantidade de Economias</strong></td>
														</tr>
														
														<% idCategoria = subcategoria.getCategoria().getId(); 
															cont = 0; %>
													
													<%} %>
												
													<%cont = cont + 1;
													if (cont % 2 == 0) {%>
													<tr bgcolor="#cbe5fe">
													<%} else {%>
													<tr bgcolor="#FFFFFF">
													<%}%>
													    <td align="center" width="10%" valign="middle">
														   <a href="javascript:removerSubcategoria(<%="" + subcategoria.getId().intValue()%>)">
															  <img src="<bean:message key='caminho.imagens'/>Error.gif" border="0" >
														   </a>
														</td>
														<td width="60%">
															<bean:write name="subcategoria" property="descricao"/>
														</td>
														<td width="30%" align="center">
													      <input type="text" name="subcategoria<%="" + subcategoria.getId().intValue()%>" size="6" maxlength="4" id="subcategoria" value="<%="" + subcategoria.getQuantidadeEconomias() %>" style="text-align: right;"/>
														</td>
													 </tr>
												
												</logic:iterate>
												
												</table>
												
												</logic:present>
												
												</div>
											</td>
										</tr>
									</table>
								</td>
							</tr>
							<tr>
								<td colspan="4" align="right">
									<input type="button" class="bottonRightCol" value="Calcular" tabindex="10"
										onclick="validarForm(document.forms[0], false);" style="width: 80px">
								</td>
							</tr>
							</table>
							
						</td>
					</tr>
					<tr>
						<td colspan="4" height="5"></td>
					</tr>
					<tr>
						<td colspan="4">
							<table width="100%" border="0">
								<tr>
									<td height="17" colspan="3"><strong>Débitos Cobrados:</strong></td>
									<td align="right">
										
										<logic:present name="colecaoSubcategoria">
											<input type="button" class="bottonRightCol" value="Adicionar"
												tabindex="11" style="width: 80px" onclick="concatenarParametro();">
										</logic:present>
										<logic:notPresent name="colecaoSubcategoria">
											<input type="button" class="bottonRightCol" value="Adicionar"
												tabindex="11" style="width: 80px" onclick="alert('É necessário informar o imóvel');" />
										</logic:notPresent>
									
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<table width="100%" cellpadding="0" cellspacing="0">
											<tr>
												<td>
													<table width="100%" bgcolor="#99CCFF">
														<tr bgcolor="#99CCFF">
															<td align="center" width="10%"><strong>Remover</strong></td>
															<td align="center" width="30%"><strong>Tipo do Débito</strong></td>
															<td align="center" width="20%"><strong>Mês/Ano do Débito</strong></td>
															<td align="center" width="20%"><strong>Mês/Ano da Cobrança</strong></td>
															<td align="center" width="20%"><strong>Valor do Débito</strong></td>
														</tr>
													</table>
												</td>
											</tr>
											
											<logic:present name="colecaoDebitoCobrado">
											<tr>
												<td>
													<div style="width: 100%; height: 100; overflow: auto;"><%cont = 0;%>
													<table width="100%" align="center" bgcolor="#99CCFF">
													<logic:iterate name="colecaoDebitoCobrado"
														id="debitoCobrado" type="DebitoCobrado">
														<%cont = cont + 1;
														if (cont % 2 == 0) {%>
														<tr bgcolor="#cbe5fe">
														<%} else {%>
														<tr bgcolor="#FFFFFF">
														<%}%>
															<td align="center" width="10%" valign="middle">
																<a href="javascript:removerDebitoCobrado('<%="" + GcomAction.obterTimestampIdObjeto(debitoCobrado)%>')">
																<img src="<bean:message key='caminho.imagens'/>Error.gif" border="0"></a>
															</td>
															<td width="30%">
																<bean:write name="debitoCobrado" property="debitoTipo.descricao" />
															</td>
															<td align="center" width="20%">
																<logic:present name="debitoCobrado" property="anoMesReferenciaDebito">
																	<%=""+ Util.formatarMesAnoReferencia(debitoCobrado
																		.getAnoMesReferenciaDebito())%>
																</logic:present>
																<logic:notPresent name="debitoCobrado" property="anoMesReferenciaDebito">
																	&nbsp;
																</logic:notPresent>
															</td>
															<td align="center" width="20%">
																<logic:present name="debitoCobrado" property="anoMesCobrancaDebito">
																	<%=""+ Util.formatarMesAnoReferencia(debitoCobrado
																		.getAnoMesCobrancaDebito())%>
																</logic:present>
																<logic:notPresent name="debitoCobrado" property="anoMesCobrancaDebito">
																	&nbsp;
																</logic:notPresent>
															</td>
															<td align="center" width="20%">
															<logic:equal name="alterarValorSugeridoEmTipoDebito" value="true">
																<input type="text"  name="debitoCobrado<%="" + GcomAction.obterTimestampIdObjeto(debitoCobrado) %>"
																	size="14" maxlength="14" id="debito" value="<%=Util.formatarMoedaReal(debitoCobrado.getValorPrestacao())%>"
																	style="text-align: right;" onkeyup="formataValorMonetario(this, 11)" />
															</logic:equal>
															<logic:notEqual name="alterarValorSugeridoEmTipoDebito" value="true">
																	<input type="text"  name="debitoCobrado<%="" + GcomAction.obterTimestampIdObjeto(debitoCobrado) %>"
																	size="14" maxlength="14" id="debito" value="<%=Util.formatarMoedaReal(debitoCobrado.getValorPrestacao())%>"
																	readonly="true"	style="text-align:right;background-color:#EFEFEF; border:0; color: #000000" />
															</logic:notEqual>
															</td>
														</tr>
													</logic:iterate>
													</table>
													</div>
												</td>
											</tr>
											</logic:present>
										</table>
									</td>
								</tr>
							</table>
						</td>
					</tr>
					<tr>
			      		<td colspan="4" height="5"></td>
			       	</tr>
			       	<tr>
			      		<td colspan="4">
			      	
			      		<table width="100%" border="0">
				  		<tr> 
			          		<td height="17" colspan="3"><strong>Créditos Realizados:</strong></td>
			          		<td align="right">
			          		
			          		<logic:present name="colecaoSubcategoria">
			          			<input type="button" class="bottonRightCol" value="Adicionar" tabindex="11" style="width: 80px" onclick="concatenarParametroCredito();">
			      			</logic:present>
			      			
			      			<logic:notPresent name="colecaoSubcategoria">
			          			<input type="button" class="bottonRightCol" value="Adicionar" tabindex="11" style="width: 80px" onclick="alert('É necessário informar o imóvel');">
			      			</logic:notPresent>
			      			</td>
			      		</tr>
			      		
			      		
			      		<tr> 
			          		<td colspan="4">
					  
								<table width="100%" cellpadding="0" cellspacing="0">
								<tr> 
			                		<td> 
								
										<table width="100%" bgcolor="#99CCFF">
			                    		<tr bgcolor="#99CCFF"> 
			
											<td align="center" width="10%"><strong>Remover</strong></td>
											<td width="30%"><div align="center"><strong>Tipo do Crédito</strong></div></td>
											<td width="15%"><div align="center"><strong>Mês/Ano do Crédito</strong></div></td>
											<td width="15%"><div align="center"><strong>Mês/Ano da Cobrança</strong></div></td>
											<td width="20%"><div align="center"><strong>Valor do Crédito</strong></div></td>
								
										</tr>
			                    		</table>
								
									</td>
			            		</tr>
			            		
			            		<logic:present name="colecaoCreditoRealizado">
			            
			            		<tr> 
									<td> 
								
										<div style="width: 100%; height: 100; overflow: auto;">
										
										<%cor = "#cbe5fe";%>
										
										<table width="100%" align="center" bgcolor="#99CCFF">	
			
											<logic:iterate name="colecaoCreditoRealizado" id="creditoRealizado" type="CreditoRealizado">
			                            
												
												<%	if (cor.equalsIgnoreCase("#cbe5fe")){
													cor = "#FFFFFF";%>
													<tr bgcolor="#FFFFFF">
												<%} else{
													cor = "#cbe5fe";%>
													<tr bgcolor="#cbe5fe">
												<%}%>
												 
													<td align="center" width="10%" valign="middle">
														<a href="javascript:removerCreditoRealizado('<%="" + GcomAction.obterTimestampIdObjeto(creditoRealizado)%>')">
															<img src="<bean:message key='caminho.imagens'/>Error.gif" border="0" >
														</a>
													</td>
													<td width="30%">
													
														<bean:write name="creditoRealizado" property="creditoTipo.descricao"/>
														
													</td>
													<td width="20%">
													<div align="center">
			
														<logic:present name="creditoRealizado" property="anoMesReferenciaCredito">	
															<%=""+ Util.formatarMesAnoReferencia(creditoRealizado.getAnoMesReferenciaCredito())%>
														</logic:present>
													
														<logic:notPresent name="creditoRealizado" property="anoMesReferenciaCredito">
															&nbsp;
														</logic:notPresent>
													
													</div>
													</td>
													<td width="20%">
													<div align="center">
													
														<logic:present name="creditoRealizado" property="anoMesCobrancaCredito">
															<%=""+ Util.formatarMesAnoReferencia(creditoRealizado.getAnoMesCobrancaCredito())%>
														</logic:present>
													
														<logic:notPresent name="creditoRealizado" property="anoMesCobrancaCredito">
															&nbsp;
														</logic:notPresent>
														
													</div>
													</td>
													<td width="20%">
													<div align="center">
			
														<INPUT TYPE="text" NAME="creditoRealizado<%="" + GcomAction.obterTimestampIdObjeto(creditoRealizado)%>" size="14" maxlength="14" value="<%="" + Util.formatarMoedaReal(creditoRealizado.getValorCredito())%>" id="credito" style="text-align: right;" onkeyup="formataValorMonetario(this, 11)"/>
														
													</div>
													</td>
												</tr>
												
			
											</logic:iterate>
											</table>
										</div>
									</td>
			            		</tr>
			            		
			            		</logic:present>
			            		
			            		</table>
							</td>
						</tr>
						
						</table>
							
			      		</td>
			      	</tr>
				
				</logic:present>
				
				<%-- ================================================================================= --%>
				<%-- ================================================================================= --%>
				
				
      
   		</table>
   </table>
   		<table width="100%" border="0">
      	<tr>
      		<td colspan="3" height="5">
				<input name="Button" type="button" class="bottonRightCol" value="Desfazer" align="left"
				onclick="window.location.href='<html:rewrite page="/exibirRetificarContaAction.do?contaID=${sessionScope.contaID}"/>'">
			
				<input name="Button" type="button" class="bottonRightCol" value="Cancelar" align="left"
				onclick="window.location.href='<html:rewrite page="/telaPrincipal.do"/>'">
			
				<input type="button" class="bottonRightCol" value="Voltar" style="width: 80px" onclick="voltarTelaManter();">
			</td>
      		<td align="right" height="5">
      			<input name="retificar" id="botaoRetificar" type="button" class="bottonRightCol" style="width: 80px" value="Retificar"
				onclick="validarForm(document.forms[0], true);" />
      		</td>
      		
      	</tr>   
      	</table>
   
   		<p>&nbsp;</p>
	
	</td>
  </tr>
</table>

<%@ include file="/jsp/util/rodape.jsp"%>
</html:form>


<SCRIPT LANGUAGE="JavaScript">
<!--

	desabilitaCampoAgua(document.forms[0]);
	desabilitaCampoEsgoto(document.forms[0]);

//-->
</SCRIPT>


<%@ include file="/jsp/util/telaespera.jsp"%>
<script>
document.getElementById('botaoRetificar').onclick = function() { validarRetificacao(document.forms[0]); }
</script>


</body>
</html:html>

