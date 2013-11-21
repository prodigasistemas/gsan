<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<%@ page import="gcom.cadastro.imovel.Categoria"%>
<%@ page import="gcom.cadastro.imovel.Subcategoria" %>
<%@ page import="gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao"%>
<%@ page import="gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao"%>
<%@ page import="gcom.faturamento.debito.DebitoCobrado" isELIgnored="false"%>
<%@ page import="gcom.util.Util"%>
<%@ page import="gcom.util.ConstantesSistema" %>
<%@ page import="gcom.cadastro.sistemaparametro.SistemaParametro" %>
<%@ page import="gcom.gui.GcomAction" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
<head>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js"></script>

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="InserirContaActionForm" />


<%-- Colocado por Raphael Rossiter em 14/03/2007
	 Objetivo: Manipulação dos objetos que serão exibidos no formulário de acordo com a empresa --%>
								
<%-- ================================================================================= --%>
							
<logic:notEqual name="indicadorTarifaCategoria" value="<%= "" + SistemaParametro.INDICADOR_TARIFA_SUBCATEGORIA %>">

<script language="JavaScript">
<!--


 function removerCategoria(idCategoria){
 	var form = document.forms[0];
 	if (validarCamposDinamicos(form)){
		form.action = "/gsan/removerSelecaoCategoriaAction.do?idCategoria=" + idCategoria + "&mapeamento=exibirInserirConta";
		if (confirm("Confirma remoção?")){
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

 
//-->
</script>

</logic:notEqual>
							
<%-- ================================================================================= --%>
<%-- ================================================================================= --%>



<%-- Colocado por Raphael Rossiter em 14/03/2007
	 Objetivo: Manipulação dos objetos que serão exibidos no formulário de acordo com a empresa --%>
								
<%-- ================================================================================= --%>
							
<logic:equal name="indicadorTarifaCategoria" value="<%= "" + SistemaParametro.INDICADOR_TARIFA_SUBCATEGORIA %>">

<script language="JavaScript">
<!--


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


function removerSubcategoria(idSubcategoria){
	var form = document.forms[0];
 	
 	if (validarCamposDinamicos(form)){
		form.action = "/gsan/removerSelecaoCategoriaAction.do?idSubcategoria=" + idSubcategoria + "&mapeamento=exibirInserirConta";
		if (confirm("Confirma remoção?")){
				form.submit();
		}
	}
 }

//-->
</script>

</logic:equal>
							
<%-- ================================================================================= --%>
<%-- ================================================================================= --%>



<script language="JavaScript">
<!--

function habilitacaoCamposAgua(listBoxAgua, consumoFaturadoAgua){
 	
 	var ATIVO = document.getElementById("ATIVO").value;
 	var indicadorFaturamento = listBoxAgua.options[listBoxAgua.options.selectedIndex].id;
 	
 	if (indicadorFaturamento.length > 0 && indicadorFaturamento != ATIVO){
 		consumoFaturadoAgua.value = "";
 		consumoFaturadoAgua.disabled = true;
 	}
 	else{
 		consumoFaturadoAgua.disabled = false;
 	}
}

/**
*
* inserir = false // Somente calcular os valores da conta
* inserir = true // calcular os valores da conta e inserir a mesma no BD
*
**/
function validarForm(form, inserir){

	if (validateInserirContaActionForm(form)){
	
		if (inserir){
			urlRedirect = "/gsan/inserirContaAction.do";
		}
		else{
			urlRedirect = "/gsan/calcularValoresContaAction.do";
		}
	
		var msgDataVencimento = "Data de Vencimento anterior à data corrente.";
		var msgDataVencimento60 = "Data de Vencimento posterior a data corrente mais 60 dias.";
		var DATA_ATUAL = document.getElementById("DATA_ATUAL").value;
		var DATA_ATUAL_60 = document.getElementById("DATA_ATUAL_60").value;
		var ANO_LIMITE = document.getElementById("ANO_LIMITE").value;
		
		var campo = "";
 		
 		var listBoxAgua = form.situacaoAguaConta;
		var listBoxEsgoto = form.situacaoEsgotoConta;
		
		var ATIVO = document.getElementById("ATIVO").value;
 		var indicadorFaturamentoAgua = listBoxAgua.options[listBoxAgua.options.selectedIndex].id;
 		var indicadorFaturamentoEsgoto = listBoxEsgoto.options[listBoxEsgoto.options.selectedIndex].id;
		
		if ((form.mesAnoConta.value.substring(3, 7) * 1) < (ANO_LIMITE * 1)){
			alert("Ano de Referência não deve ser menor que " + ANO_LIMITE + ".");
			form.mesAnoConta.focus();
			return false;
		}
		else if ((form.vencimentoConta.value.substring(6, 10) * 1) < (ANO_LIMITE * 1)){
			alert("Ano da Conta não deve ser menor que " + ANO_LIMITE + ".");
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
				else if (form.consumoAgua.value.length > 0 && form.consumoEsgoto.value.length > 0 &&
						 (form.consumoAgua.value * 1) > (form.consumoEsgoto.value * 1)){
					
					alert("Consumo de Esgoto não deve ser menor que Consumo de Água.");
					form.consumoEsgoto.value = form.consumoAgua.value;
					form.consumoEsgoto.focus();
					return false;
				}
				else {
					if (validarCamposDinamicos(form)){
						converteVirgula(form.percentualEsgoto);
						if (!inserir){
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
				else if (form.consumoAgua.value.length > 0 && form.consumoEsgoto.value.length > 0 &&
						 (form.consumoAgua.value * 1) > (form.consumoEsgoto.value * 1)){
						alert("Consumo de Esgoto não deve ser menor que Consumo de Água.");
					
					form.consumoEsgoto.value = form.consumoAgua.value;
					form.consumoEsgoto.focus();
					return false;
				}
				else {
					if (validarCamposDinamicos(form)){
						converteVirgula(form.percentualEsgoto);
						if (!inserir){
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
		else if (form.consumoAgua.value.length > 0 && form.consumoEsgoto.value.length > 0 &&
				(form.consumoAgua.value * 1) > (form.consumoEsgoto.value * 1)){
			
			alert("Consumo de Esgoto não deve ser menor que Consumo de Água.");
			form.consumoEsgoto.value = form.consumoAgua.value;
			form.consumoEsgoto.focus();
			return false;
		}
		else {
			if (validarCamposDinamicos(form)){
				converteVirgula(form.percentualEsgoto);
	    		if (!inserir){
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


function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {

    var form = document.forms[0];

    if (tipoConsulta == 'imovel') {
      //limparForm();
      form.idImovel.value = codigoRegistro;
      form.submit();
    }
 }

function exibirDadosImovel(form){

	idImovel = form.idImovel.value;
	inscricaoImovel = form.inscricaoImovel.value;
	
	if (idImovel.length > 0 && inscricaoImovel.length < 1){
		
		alert("Dados do Imóvel não pesquisados. Deseja pesquisar agora?")
		validaEnterSemEvento('exibirInserirContaAction.do', 'idImovel');
		
	}
}

function habilitacaoCamposEsgoto(listBoxEsgoto, consumoFaturadoEsgoto, percentualEsgoto){
 
 	var ATIVO = document.getElementById("ATIVO").value;
 	var indicadorFaturamento = listBoxEsgoto.options[listBoxEsgoto.options.selectedIndex].id;
 	
 	if (indicadorFaturamento.length > 0 && indicadorFaturamento != ATIVO){
 		consumoFaturadoEsgoto.value = "";
 		consumoFaturadoEsgoto.disabled = true;
 		percentualEsgoto.value = "";
 		percentualEsgoto.disabled = true;
 	}
 	else{
 		consumoFaturadoEsgoto.disabled = false;
 		percentualEsgoto.disabled = false;
 	}
}
 
 
 function removerDebitoCobrado(debitoCobradoUltimaAlteracao){
 	var form = document.forms[0];
 	if (validarCamposDinamicos(form)){
		form.action = "/gsan/removerSelecaoDebitoCobradoAction.do?debitoCobradoUltimaAlteracao=" + debitoCobradoUltimaAlteracao + "&mapeamento=exibirInserirConta";
		if (confirm("Confirma remoção?")){
			
			form.submit();
		}
	}
 }
 
 function limparForm(){
 	window.location.href="exibirInserirContaAction.do?menu=sim";
 }
  
  
  function validarInsercao(form){
	  if (validarForm(document.forms[0], true) != false){
	  	 botaoAvancarTelaEspera('/gsan/inserirContaAction.do');
	 }
  }
  
 //-->
</script>

<logic:present name="colecaoCategoria">
	<script language="JavaScript">
	<!--
	function concatenarParametro(){
	 	abrirPopup("exibirAdicionarDebitoCobradoContaAction.do?imovel=" + document.forms[0].idImovel.value, 395, 450);
	 }
	 
	//-->
	</script>
</logic:present>


<logic:present name="colecaoSubcategoria">
	<script language="JavaScript">
	<!--
	function concatenarParametro(){
	 	abrirPopup("exibirAdicionarDebitoCobradoContaAction.do?imovel=" + document.forms[0].idImovel.value, 395, 450);
	 }
	 
	//-->
	</script>
</logic:present>

</head>

<body leftmargin="5" topmargin="5"
	onload="setarFoco('${requestScope.nomeCampo}');">

<div id="formDiv"><html:form action="/exibirInserirContaAction" method="post">

	<INPUT TYPE="hidden" ID="ATIVO" value="<%=ConstantesSistema.INDICADOR_USO_ATIVO%>"/>
	<input type="hidden" name="cancelarValidacao" value="true" />
	<INPUT TYPE="hidden" ID="DATA_ATUAL" value="${requestScope.dataAtual}" />
	<INPUT TYPE="hidden" ID="DATA_ATUAL_60" value="${requestScope.dataAtual60}" />
	<INPUT TYPE="hidden" ID="ANO_LIMITE" value="${requestScope.anoLimite}" />


	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>

	<table width="770" border="0" cellspacing="5" cellpadding="0">
		<tr>
			<td width="140" valign="top" class="leftcoltext">
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
					<td class="parabg">Inserir Conta</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>

			<!-- ============================================================================================================================== -->

			<table width="100%" border="0">
				<tr>
					<td>Para inserir a conta, informe os dados abaixo:</td>
					<logic:present scope="application" name="urlHelp">
						<td align="right"><a href="javascript:abrirPopupHelp('${applicationScope.urlHelp}faturamentoContaInserir', 500, 700);"><span style="font-weight: bold"><font color="#3165CE">Ajuda</font></span></a></td>									
					</logic:present>
					<logic:notPresent scope="application" name="urlHelp">
						<td align="right"><span style="font-weight: bold"><font color=#696969><u>Ajuda</u></font></span></td>									
					</logic:notPresent>
			    </tr>
			</table>	
			<table width="100%" border="0">	
				<tr>
					<td colspan="4">
						<table width="100%" align="center" border="0">
							<tr>
								<td height="10" width="145"><strong>Matrícula do Imóvel:<font
									color="#FF0000">*</font></strong></td>
								<td><html:text property="idImovel" maxlength="9" size="9"
				onkeypress="validaEnterComMensagem(event, 'exibirInserirContaAction.do', 'idImovel', 'Matrícula do Imóvel');
				 return isCampoNumerico(event); "/>
								<a
									href="javascript:abrirPopup('exibirPesquisarImovelAction.do', 400, 800);"
									tabindex="1"> <img width="23" height="21"
									src="<bean:message key='caminho.imagens'/>pesquisa.gif"
									border="0" title="Pesquisar Imóvel" /></a> <logic:present name="corInscricao">

									<logic:equal name="corInscricao" value="exception">
										<html:text property="inscricaoImovel" size="30" readonly="true"
										style="background-color:#EFEFEF; border:0; color: #ff0000" />
									</logic:equal>

									<logic:notEqual name="corInscricao" value="exception">
										<html:text property="inscricaoImovel" size="30" readonly="true"
											style="background-color:#EFEFEF; border:0; color: #000000" />
									</logic:notEqual>

								</logic:present> <logic:notPresent name="corInscricao">
	
								<logic:empty name="InserirContaActionForm" property="idImovel">
									<html:text property="inscricaoImovel" size="30" readonly="true"
										style="background-color:#EFEFEF; border:0; color: #ff0000" />
								</logic:empty>
								<logic:notEmpty name="InserirContaActionForm"
									property="idImovel">
									<html:text property="inscricaoImovel" size="30" readonly="true"
										style="background-color:#EFEFEF; border:0; color: #000000" />
								</logic:notEmpty>
								</logic:notPresent> <a href="javascript:limparForm();"
									tabindex="1"> <img border="0"
									src="<bean:message key='caminho.imagens'/>limparcampo.gif"
									style="cursor: hand;" title="Apagar Imóvel"/> </a></td>
							</tr>
						</table>
						<table width="100%" align="center" bgcolor="#99CCFF" border="0">
						<tr>
							<td align="center"><strong>Dados do Imóvel</strong></td>
						</tr>
						<tr bgcolor="#cbe5fe">
							<td width="100%" align="center">

							<table width="100%" border="0">
								<tr>
									<td height="10"><strong>Nome do Cliente Usuário:</strong></td>
									<td><html:text property="nomeClienteUsuario" size="45"
										readonly="true" style="background-color:#EFEFEF; border:0" />
									</td>
								</tr>
								<tr>
									<td height="10"><strong>Situação de Água:</strong></td>
									<td><html:text property="situacaoAguaImovel" size="45"
										readonly="true" style="background-color:#EFEFEF; border:0" />
									</td>
								</tr>
								<tr>
									<td height="10"><strong>Situação de Esgoto:</strong></td>
									<td><html:text property="situacaoEsgotoImovel" size="45"
										readonly="true" style="background-color:#EFEFEF; border:0" />
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
							<td align="center"><strong>Dados da Conta</strong></td>
						</tr>
						<tr bgcolor="#cbe5fe">
							<td width="100%" align="center">

							<table width="100%" border="0">
								<tr>
									<td height="10" width="145"><strong>Mês e Ano da Conta:<font
										color="#FF0000">*</font></strong></td>
									<td><html:text property="mesAnoConta" size="8" maxlength="7"
										tabindex="2" onkeyup="mascaraAnoMes(this, event);"
										onblur="exibirDadosImovel(document.forms[0])"
										onkeypress="return isCampoNumerico(event);" />
										mm/aaaa
									</td>
									
								</tr>
								<tr>
									<td height="10"><strong>Motivo da Inclusão:<font
										color="#FF0000">*</font></strong></td>
									<td><html:select property="motivoInclusaoID"
										style="width: 340px;" tabindex="3">
										<html:option value="<%= ""+ConstantesSistema.NUMERO_NAO_INFORMADO %>">&nbsp;</html:option>
										<logic:present name="colecaoMotivoInclusaoConta">
											<html:options collection="colecaoMotivoInclusaoConta"
												labelProperty="descricaoMotivoInclusaoConta" property="id" />
										</logic:present>
									</html:select></td>
								</tr>
								<tr>
									<td height="10"><strong>Data de Vencimento:<font
										color="#FF0000">*</font></strong></td>
									<td><html:text property="vencimentoConta" size="11"
										maxlength="10" tabindex="4"
										onkeyup="mascaraData(this, event);" 
										onkeypress="javascript:return isCampoNumerico(event);"  /> 
										<a href="javascript:abrirCalendario('InserirContaActionForm', 'vencimentoConta')">
									<img border="0"
										src="<bean:message key='caminho.imagens'/>calendario.gif"
										width="20" border="0" align="absmiddle" alt="Exibir Calendário" /></a>

									dd/mm/aaaa</td>
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
							<td align="center"><strong>Dados de Água</strong></td>
						</tr>
						<tr bgcolor="#cbe5fe">
							<td width="100%" align="center">

							<table width="100%" border="0">
								<tr>
									<td height="10" width="140"><strong>Situação de Água:<font
										color="#FF0000">*</font></strong></td>
									<td><html:select property="situacaoAguaConta" style="width: 190px;" tabindex="5"
										onchange="habilitacaoCamposAgua(this, document.forms[0].consumoAgua);">
										
										<logic:present name="colecaoSituacaoLigacaoAgua">
										
											<option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</option>
															    	
											<logic:iterate name="colecaoSituacaoLigacaoAgua" id="ligacaoAguaSituacao" type="LigacaoAguaSituacao">
													      				
												<logic:equal name="InserirContaActionForm" property="situacaoAguaConta" value="<%="" + ligacaoAguaSituacao.getId() %>">
													<option SELECTED value="<%="" + ligacaoAguaSituacao.getId() %>" id="<%="" + ligacaoAguaSituacao.getIndicadorFaturamentoSituacao() %>" name="<%="" + ligacaoAguaSituacao.getConsumoMinimoFaturamento() %>"><%="" + ligacaoAguaSituacao.getDescricao() %></option>
												</logic:equal>
													      				
												<logic:notEqual name="InserirContaActionForm" property="situacaoAguaConta" value="<%="" + ligacaoAguaSituacao.getId() %>">
													<option value="<%="" + ligacaoAguaSituacao.getId() %>" id="<%="" + ligacaoAguaSituacao.getIndicadorFaturamentoSituacao() %>" name="<%="" + ligacaoAguaSituacao.getConsumoMinimoFaturamento() %>"><%="" + ligacaoAguaSituacao.getDescricao() %></option>
												</logic:notEqual>
													      			
											</logic:iterate>
											
										</logic:present>
										
									</html:select></td>
									<td colspan="2"></td>
								</tr>
								<tr>
									<td height="10"><strong>Consumo de Água:</strong></td>
									<td>
									<html:text property="consumoAgua" size="10" maxlength="6"
										tabindex="6" style="text-align: right;" 
										 onkeypress="javascript:return isCampoNumerico(event);" />
									</td>
									<td><strong>Valor de Água:</strong></td>
									<td><html:text property="valorAgua" size="18" readonly="true"
										style="background-color:#EFEFEF; border:0; text-align: right;" />
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
							<td align="center"><strong>Dados de Esgoto</strong></td>
						</tr>
						<tr bgcolor="#cbe5fe">
							<td width="100%" align="center">

							<table width="100%" border="0">
								<tr>
									<td height="10" width="140"><strong>Situação de Esgoto:<font
										color="#FF0000">*</font></strong></td>
									<td><html:select property="situacaoEsgotoConta" style="width: 190px;" tabindex="7"
										onchange="habilitacaoCamposEsgoto(this, document.forms[0].consumoEsgoto, document.forms[0].percentualEsgoto);">
										
										<logic:present name="colecaoSituacaoLigacaoEsgoto">
										
											<option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</option>
															    	
											<logic:iterate name="colecaoSituacaoLigacaoEsgoto" id="ligacaoEsgotoSituacao" type="LigacaoEsgotoSituacao">
													      				
												<logic:equal name="InserirContaActionForm" property="situacaoEsgotoConta" value="<%="" + ligacaoEsgotoSituacao.getId() %>">
													<option SELECTED value="<%="" + ligacaoEsgotoSituacao.getId() %>" id="<%="" + ligacaoEsgotoSituacao.getIndicadorFaturamentoSituacao() %>" name="<%="" + ligacaoEsgotoSituacao.getVolumeMinimoFaturamento() %>"><%="" + ligacaoEsgotoSituacao.getDescricao() %></option>
												</logic:equal>
													      			
												<logic:notEqual name="InserirContaActionForm" property="situacaoEsgotoConta" value="<%="" + ligacaoEsgotoSituacao.getId() %>">
													<option value="<%="" + ligacaoEsgotoSituacao.getId() %>" id="<%="" + ligacaoEsgotoSituacao.getIndicadorFaturamentoSituacao() %>" name="<%="" + ligacaoEsgotoSituacao.getVolumeMinimoFaturamento() %>"><%="" + ligacaoEsgotoSituacao.getDescricao() %></option>
												</logic:notEqual>
													      				
											</logic:iterate>
											
										</logic:present>
										
									</html:select></td>
									<td colspan="2"></td>
								</tr>
								<tr>
									<td height="10"><strong>Consumo de Esgoto:</strong></td>
									<td><html:text property="consumoEsgoto" size="10" maxlength="6"
										tabindex="8" style="text-align: right;" 
										 onkeypress="javascript:return isCampoNumerico(event);" /></td>
									<td><strong>Valor de Esgoto:</strong></td>
									<td><html:text property="valorEsgoto" size="18" readonly="true"
										style="background-color:#EFEFEF; border:0; text-align: right;" />
									</td>
								</tr>
								<tr>
									<td height="10"><strong>Percentual de Esgoto:</strong></td>
									<td><input name="percentualEsgoto" type="text" size="10" maxlength="6" 
									value="${requestScope.percentualEsgoto}" 
									onKeyup="formataValorMonetario(this, 5);" style="text-align: right;"
									 onkeypress="javascript:return isCampoNumerico(event);">
									</td>
									<td colspan="2"></td>
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

							<td height="10" width="160"><strong>Valor dos Débitos:</strong></td>
							<td><html:text property="valorDebito" size="21" readonly="true"
								style="background-color:#EFEFEF; border:0; text-align: right;" />
							</td>
							<td colspan="2" width="380"></td>
						</tr>
						<tr>
							<td height="10"><strong>Valor Total da Conta:</strong></td>
							<td><html:text property="valorTotal" size="21" readonly="true"
								style="background-color:#EFEFEF; border:0; text-align: right;" />
							</td>
						</tr>
					</table>
					</td>
				</tr>
				<tr>
					<td height="10" width="145">&nbsp;</td>
					<td colspan="3"><strong><font color="#FF0000">*</font></strong>
					Campos obrigat&oacute;rios</td>
				</tr>
				
				
				<%-- Colocado por Raphael Rossiter em 11/04/2007
					Objetivo: Manipulação dos objetos que serão exibidos no formulário de acordo com a empresa --%>
								
				<%-- ================================================================================= --%>
				
				<%int cont = 0;%>

				<logic:present name="colecaoCategoria">
				
					<tr>
						<td colspan="4" height="5"></td>
					</tr>
					<tr>
						<td colspan="4">
							
							<table width="100%" border="0">
							<tr>
								<td height="17" colspan="3"><strong>Categorias e Economias:</strong></td>
								<td align="right"><input type="button" class="bottonRightCol"
									value="Adicionar" tabindex="11" style="width: 80px"
									onclick="javascript:abrirPopup('exibirAdicionarCategoriaContaAction.do', 295, 450);"></td>
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
														<a href="javascript:removerCategoria(<%="" + categoria.getId().intValue()%>)">
															<img src="<bean:message key='caminho.imagens'/>Error.gif" border="0" >
														</a>
													</td>
													<td width="60%"><bean:write name="categoria"
														property="descricao" /></td>
													<td width="30%">
													<div align="center">
													<INPUT TYPE="text"
														NAME="categoria<%="" + categoria.getId().intValue()%>"
														size="6" id="categoria" maxlength="4"
														value="<%="" + categoria.getQuantidadeEconomiasCategoria()%>"
														style="text-align: right;" 
														onkeypress="javascript:return isCampoNumerico(event);"  />
													</div>
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
										<logic:present name="colecaoCategoria">
											<input type="button" class="bottonRightCol" value="Adicionar"
												tabindex="11" style="width: 80px" onclick="concatenarParametro();">
										</logic:present>
										<logic:notPresent name="colecaoCategoria">
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
																<input type="text"  name="debitoCobrado<%="" + GcomAction.obterTimestampIdObjeto(debitoCobrado) %>"
																	size="14" maxlength="14" id="debito" value="<%="" + Util.formatarMoedaReal(debitoCobrado.getValorPrestacao())%>"
																	style="text-align: right;" onkeyup="formataValorMonetario(this, 11)" />
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
							
				
				
				
				<%-- Colocado por Raphael Rossiter em 11/04/2007
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
																<input type="text"  name="debitoCobrado<%="" + GcomAction.obterTimestampIdObjeto(debitoCobrado) %>"
																	size="14" maxlength="14" id="debito" value="<%="" + Util.formatarMoedaReal(debitoCobrado.getValorPrestacao())%>"
																	style="text-align: right;" onkeyup="formataValorMonetario(this, 11)" />
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
				
				
				
				<tr>
					<td colspan="4">&nbsp;</td>
				</tr>
				<tr>
					<td colspan="3"><input name="Button" type="button" class="bottonRightCol"
						value="Desfazer" align="left"
						onclick="window.location.href='<html:rewrite page="/exibirInserirContaAction.do?menu=sim"/>'">
					<input name="Button" type="button" class="bottonRightCol"
						value="Cancelar" align="left"
						onclick="window.location.href='/gsan/telaPrincipal.do'"></td>
					<td align="right">
						<input name="retificar" id="botaoInserir" type="button" 
						class="bottonRightCol" style="width: 80px" value="Inserir"
						onclick="validarForm(document.forms[0], true);" />
					</td>
				</tr>
			</table>
			<p>&nbsp;</p>
			</td>
		</tr>
	</table>

	<%@ include file="/jsp/util/rodape.jsp"%>

	<SCRIPT LANGUAGE="JavaScript">
	<!--
	
	habilitacaoCamposAgua(document.forms[0].situacaoAguaConta, document.forms[0].consumoAgua);
	
	habilitacaoCamposEsgoto(document.forms[0].situacaoEsgotoConta, document.forms[0].consumoEsgoto, document.forms[0].percentualEsgoto);
	 
	//-->
</SCRIPT>

</html:form>
</div>

<%@ include file="/jsp/util/telaespera.jsp"%>
<script>
document.getElementById('botaoInserir').onclick = function() {validarInsercao(document.forms[0]);}
</script>


</body>
</html:html>



