<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>

<title>GSAN - Sistema Integrado de Gest&atilde;o de Servi&ccedil;os de Saneamento</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false"
	formName="ImovelOutrosCriteriosActionForm" dynamicJavascript="false" />

<script>
<!-- Begin 

     var bCancel = false; 

    function validateImovelOutrosCriteriosActionForm(form) {                                                                   
 
       if(form.intervaloMediaMinimaHidrometroInicio.value != null){
		   var indice;
		   var array = new Array(form.indicadorMedicao.length);
		   var selecionado = "";
		   var formulario = document.forms[0]; 
		   for(indice = 0; indice < form.elements.length; indice++){
		 	  if (form.elements[indice].type == 'radio' && form.elements[indice].checked == true) {
			    	selecionado = form.elements[indice].value;
			    	indice = form.elements.length;
			  }
		   }         		
       
			if(form.tipoMedicao.value == "-1" && selecionado == "comMedicao" ){
				alert("O Tipo de Medição deve ser informado. ");
    			return false;
			}	
  		}
     
        if (bCancel) 
      return true; 
        else 
       return validateCaracterEspecial(form) && validateInteger(form) && validateDecimal(form)
       && controleFaixaEsgoto()
       && controleFaixaAgua()
       && controleMediaMinimaHidrometro()
       && controleMediaMinimaImovel();
   } 

    function caracteresespeciais () { 
     this.aa = new Array("consumoMinimoInicial", "Intervalo de Consumo Mínimo Fixado de Água Inicial deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
     this.ab = new Array("consumoMinimoFinal", "Intervalo de Consumo Mínimo Fixado de Água Final deve somente conter números positivos.", new Function ("varName", " return this[varName];"));

     this.ac = new Array("intervaloPercentualEsgotoInicial", "Intervalo de Percentual de Esgoto Inicial deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
     this.ad = new Array("intervaloPercentualEsgotoFinal", "Intervalo de Percentual de Esgoto Final deve somente conter números positivos.", new Function ("varName", " return this[varName];"));

     this.ae = new Array("consumoMinimoFixadoEsgotoInicial", "Intervalo de Consumo Mínimo Fixado de Esgoto Inicial deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
     this.af = new Array("consumoMinimoFixadoEsgotoFinal", "Intervalo de Consumo Mínimo Fixado de Esgoto Final deve somente conter números positivos.", new Function ("varName", " return this[varName];"));

     this.ag = new Array("intervaloMediaMinimaImovelInicio", "Intervalo de Média Mínima do Imóvel Inicial deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
     this.ah = new Array("intervaloMediaMinimaImovelFinal", "Intervalo de Média Mínima do Imóvel Final deve somente conter números positivos.", new Function ("varName", " return this[varName];"));

     this.ai = new Array("intervaloMediaMinimaHidrometroInicio", "Intervalo de Média Mínima do Hidrômetro Inicial deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
     this.aj = new Array("intervaloMediaMinimaHidrometroFinal", "Intervalo de Média Mínima do Hidrômetro Final deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
    } 

    function IntegerValidations () { 
     this.aa = new Array("consumoMinimoInicial", "Intervalo de Consumo Mínimo Fixado de Água Inicial deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
     this.ab = new Array("consumoMinimoFinal", "Intervalo de Consumo Mínimo Fixado de Água Final deve somente conter números positivos.", new Function ("varName", " return this[varName];"));

     this.ae = new Array("consumoMinimoFixadoEsgotoInicial", "Intervalo de Consumo Mínimo Fixado de Esgoto Inicial deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
     this.af = new Array("consumoMinimoFixadoEsgotoFinal", "Intervalo de Consumo Mínimo Fixado de Esgoto Final deve somente conter números positivos.", new Function ("varName", " return this[varName];"));

     this.ag = new Array("intervaloMediaMinimaImovelInicio", "Intervalo de Média Mínima do Imóvel Inicial deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
     this.ah = new Array("intervaloMediaMinimaImovelFinal", "Intervalo de Média Mínima do Imóvel Final deve somente conter números positivos.", new Function ("varName", " return this[varName];"));

     this.ai = new Array("intervaloMediaMinimaHidrometroInicio", "Intervalo de Média Mínima do Hidrômetro Inicial deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
     this.aj = new Array("intervaloMediaMinimaHidrometroFinal", "Intervalo de Média Mínima do Hidrômetro Final deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
    } 

    function FloatValidations () {
     this.ac = new Array("intervaloPercentualEsgotoInicial", "Intervalo de Percentual de Esgoto Inicial deve somente conter números decimais positivos.", new Function ("varName", " return this[varName];"));
     this.ad = new Array("intervaloPercentualEsgotoFinal", "Intervalo de Percentual de Esgoto Final deve somente conter números decimais positivos.", new Function ("varName", " return this[varName];"));
    } 


-->    
</script>

<script language="JavaScript">
 <!-- Begin 
function validarForm(form){

  if(validatePesquisarActionForm(form)){
    form.submit();
  }
}

//esgoto
function controlaTextEsgoto(tipoRelatorio){
	var form = document.ImovelOutrosCriteriosActionForm;
	
	if(form.situacaoLigacaoEsgoto.value != "3"
		&& form.situacaoLigacaoEsgoto.value != "5" ){
		form.intervaloPercentualEsgotoInicial.value = "";
		form.intervaloPercentualEsgotoInicial.disabled = true;
		form.intervaloPercentualEsgotoFinal.value = "";
		form.intervaloPercentualEsgotoFinal.disabled = true;
		//---
		form.consumoMinimoFixadoEsgotoInicial.value = "";
		form.consumoMinimoFixadoEsgotoInicial.disabled = true;
		form.consumoMinimoFixadoEsgotoFinal.value = "";
		form.consumoMinimoFixadoEsgotoFinal.disabled = true;
	} else if (tipoRelatorio == "RelatorioCadastroConsumidoresInscricao") {
		form.consumoMinimoFixadoEsgotoInicial.disabled = false;
		form.consumoMinimoFixadoEsgotoFinal.disabled = false;
	} else if (tipoRelatorio == "RelatorioImoveisEndereco") {
	
	} else {
		form.intervaloPercentualEsgotoInicial.disabled = false;
		form.intervaloPercentualEsgotoFinal.disabled = false;
		form.consumoMinimoFixadoEsgotoInicial.disabled = false;
		form.consumoMinimoFixadoEsgotoFinal.disabled = false;
	}
}

function controleFaixaEsgoto(){
	var form = document.ImovelOutrosCriteriosActionForm;
	
	if(form.intervaloPercentualEsgotoInicial.value > form.intervaloPercentualEsgotoFinal.value){
		alert("Intervalo de Percentual de Esgoto Inicial maior que o Intervalo de Percentual de Esgoto Final.");
		form.intervaloPercentualEsgotoFinal.value = "";
		form.intervaloPercentualEsgotoFinal.focus();
		return false;
	}
	if(form.consumoMinimoFixadoEsgotoInicial.value > form.consumoMinimoFixadoEsgotoFinal.value){
		alert("Intervalo de Consumo Mínimo Fixado de Esgoto Inicial maior que o Intervalo de Consumo Mínimo Fixado de Esgoto Final.");
		form.consumoMinimoFixadoEsgotoFinal.value = "";
		form.consumoMinimoFixadoEsgotoFinal.focus();
		return false;
	}
	
	return true;
}


function carregaPercentualEsgotoFinal(){
	var form = document.ImovelOutrosCriteriosActionForm;
	
	form.intervaloPercentualEsgotoFinal.value = form.intervaloPercentualEsgotoInicial.value;
}

function carregaFixadoEsgotoFinal(){
	var form = document.ImovelOutrosCriteriosActionForm;
	
	form.consumoMinimoFixadoEsgotoFinal.value = form.consumoMinimoFixadoEsgotoInicial.value;
}

-->
</script>
<script language="JavaScript">
<!-- Begin
function controlaTextAgua(tipoRelatorio){
	var form = document.ImovelOutrosCriteriosActionForm;
	
	if(form.situacaoAgua.value != "3"
		&& form.situacaoAgua.value != "5" ){
		form.consumoMinimoInicial.value = "";
		form.consumoMinimoFinal.value = "";
		form.consumoMinimoInicial.disabled = true;
		form.consumoMinimoFinal.disabled = true;
	} else if (tipoRelatorio != "RelatorioCadastroConsumidoresInscricao" && tipoRelatorio != "RelatorioImoveisEndereco") {
		form.consumoMinimoInicial.disabled = false;
		form.consumoMinimoFinal.disabled = false;
	}
}

function controleFaixaAgua(){
	var form = document.ImovelOutrosCriteriosActionForm;
	
	if(form.consumoMinimoInicial.value > form.consumoMinimoFinal.value){
		alert("Intervalo de Consumo Mínimo Fixado de Água Inicial maior que o Intervalo de Consumo Mínimo Fixado de Água Final.");
		form.consumoMinimoFinal.value="";
		form.consumoMinimoFinal.focus();
		return false;
	}
	return true;
}

function carregaConsumoMinimoFinal(){
	var form = document.ImovelOutrosCriteriosActionForm;
	form.consumoMinimoFinal.value = form.consumoMinimoInicial.value; 
}
-->
</script>

<script language="JavaScript">
function controlaTextMedicao(tipoRelatorio){
	var form = document.ImovelOutrosCriteriosActionForm;
	
	if(form.tipoMedicao.value != "1"
		&& form.tipoMedicao.value != "2" ){
		form.intervaloMediaMinimaImovelInicio.value = "";
		form.intervaloMediaMinimaImovelFinal.value = "";
		form.intervaloMediaMinimaImovelInicio.disabled = true;
		form.intervaloMediaMinimaImovelFinal.disabled = true;
		
		form.intervaloMediaMinimaHidrometroInicio.value = "";
		form.intervaloMediaMinimaHidrometroFinal.value = "";
		form.intervaloMediaMinimaHidrometroInicio.disabled = true;
		form.intervaloMediaMinimaHidrometroFinal.disabled = true;
	} else if (tipoRelatorio != "RelatorioCadastroConsumidoresInscricao" && tipoRelatorio != "RelatorioImoveisEndereco") {
		form.intervaloMediaMinimaImovelInicio.disabled = false;
		form.intervaloMediaMinimaImovelFinal.disabled = false;
		
		form.intervaloMediaMinimaHidrometroInicio.disabled = false;
		form.intervaloMediaMinimaHidrometroFinal.disabled = false;
	}
}

function carregaMediaMinimaHidrometro(){
	var form = document.ImovelOutrosCriteriosActionForm;
	
	form.intervaloMediaMinimaHidrometroFinal.value = form.intervaloMediaMinimaHidrometroInicio.value;


}

function carregaMediaMinimaImovel(){
	var form = document.ImovelOutrosCriteriosActionForm;

	form.intervaloMediaMinimaImovelFinal.value = form.intervaloMediaMinimaImovelInicio.value;
}

function controleMediaMinimaHidrometro(){
	var form = document.ImovelOutrosCriteriosActionForm;
	
	if(form.intervaloMediaMinimaHidrometroInicio.value > form.intervaloMediaMinimaHidrometroFinal.value){
		alert("Intervalo de Média Mínima do Imóvel Inicial maior que o Intervalo de Média Mínima do Imóvel Final.");
		form.consumoMinimoFinal.value="";
		form.consumoMinimoFinal.focus();
		return false;
	}
	return true;
}

function controleMediaMinimaImovel(){
	var form = document.ImovelOutrosCriteriosActionForm;
	
	if(form.intervaloMediaMinimaImovelInicio.value > form.intervaloMediaMinimaImovelFinal.value){
		alert("Intervalo de Média Mínima do Hidrômetro Inicial maior que o Intervalo de Média Mínima do Hidrômetro Final.");
		form.consumoMinimoFinal.value="";
		form.consumoMinimoFinal.focus();
		return false;
	}
	return true;
}


function validarIndicadorMedicao(tipoRelatorio){

    var form = document.forms[0];
    
   var indice;
   var array = new Array(form.indicadorMedicao.length);
   var selecionado = "";
   var formulario = document.forms[0]; 
   for(indice = 0; indice < form.elements.length; indice++){
 	  if (form.elements[indice].type == 'radio' && form.elements[indice].checked == true) {
	    	selecionado = form.elements[indice].value;
	    	indice = form.elements.length;
	  }
   }    

	if(selecionado == ""){
		form.tipoMedicao.disabled = true;
		form.intervaloMediaMinimaImovelInicio.value = "";
		form.intervaloMediaMinimaImovelFinal.value = "";
		form.intervaloMediaMinimaImovelInicio.disabled = true;
		form.intervaloMediaMinimaImovelFinal.disabled = true;
		
		form.intervaloMediaMinimaHidrometroInicio.value = "";
		form.intervaloMediaMinimaHidrometroFinal.value = "";
		form.intervaloMediaMinimaHidrometroInicio.disabled = true;
		form.intervaloMediaMinimaHidrometroFinal.disabled = true;
	}else if(selecionado == "comMedicao"){
		
		if (tipoRelatorio != "RelatorioCadastroConsumidoresInscricao" && tipoRelatorio != "RelatorioImoveisEndereco") {
			form.intervaloMediaMinimaImovelInicio.disabled = false;
			form.intervaloMediaMinimaImovelFinal.disabled = false;
			form.intervaloMediaMinimaHidrometroInicio.disabled = false;
			form.intervaloMediaMinimaHidrometroFinal.disabled = false;
			form.tipoMedicao.disabled = false;		
		} else {
			form.tipoMedicao.disabled = false;
		}
		
	}else if(selecionado == "semMedicao"){
		form.tipoMedicao.disabled = true;
		form.intervaloMediaMinimaImovelInicio.value = "";
		form.intervaloMediaMinimaImovelFinal.value = "";
		form.intervaloMediaMinimaImovelInicio.disabled = true;
		form.intervaloMediaMinimaImovelFinal.disabled = true;
		
		form.intervaloMediaMinimaHidrometroInicio.value = "";
		form.intervaloMediaMinimaHidrometroFinal.value = "";
		form.intervaloMediaMinimaHidrometroInicio.disabled = true;
		form.intervaloMediaMinimaHidrometroFinal.disabled = true;
	}

}

	function habilitaOuDesabilitaTipoPoco(campo) {
		var form = document.forms[0];

		if (parseInt(form.situacaoLigacaoEsgoto.value) == parseInt(3)
				 || parseInt(form.situacaoAgua.value) == parseInt(3)) {
			form.indicadorHabilitaOuDesabilitaTipoPoco.value = 'HABILITA';
		}	else {
			form.indicadorHabilitaOuDesabilitaTipoPoco.value = 'DESABILITA';
		}
		
	}

</script>

</head>

<body leftmargin="5" topmargin="5"
	onload="controlaTextAgua('${sessionScope.parametroGerarRelatorio}');controlaTextEsgoto('${sessionScope.parametroGerarRelatorio}');controlaTextMedicao('${sessionScope.parametroGerarRelatorio}');">
<div id="formDiv">
<html:form action="/filtrarImovelOutrosCriteriosWizardAction"
	name="ImovelOutrosCriteriosActionForm" method="post"
	type="gcom.gui.cadastro.imovel.ImovelOutrosCriteriosActionForm"
	onsubmit="return validateImovelOutrosCriteriosActionForm(this);">

	<jsp:include
		page="/jsp/util/wizard/navegacao_abas_wizard_valida_avancar_valida_voltar.jsp?numeroPagina=3" />


	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>
	
	<html:hidden property="indicadorHabilitaOuDesabilitaTipoPoco" />

	<table width="770" border="0" cellspacing="5" cellpadding="0">
		<input type="hidden" name="numeroPagina" value="3" />
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
			<td width="685" valign="top" class="centercoltext">
			<table height="100%">
				<tr>
					<td></td>
				</tr>
			</table>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><html:img src="imagens/parahead_left.gif" border="0" /></td>
					<td class="parabg">Filtrar Imóvel</td>
					<td width="11" valign="top"><html:img
						src="imagens/parahead_right.gif" border="0" /></td>
				</tr>
			</table>
			<!--Fim Tabela Reference a Páginação da Tela de Processo-->
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>
					<td colspan="4">Para filtrar o(s) im&oacute;vel(is) pela
					situa&ccedil;&atilde;o de &aacute;gua, de esgoto, pelo consumo,
					informe os dados abaixo:</td>
				</tr>
				<tr>
					<td width="284"><strong>Situa&ccedil;&atilde;o da
					Liga&ccedil;&atilde;o de &Aacute;gua:</strong></td>
					<td width="313" colspan="3" align="right">
					<div align="left">
						<strong> 
							<html:select property="situacaoAgua"
										 onchange="controlaTextAgua('${sessionScope.parametroGerarRelatorio}');habilitaOuDesabilitaTipoPoco(this);">
							<html:option value="-1">&nbsp;</html:option>
							<html:options   collection="collectionsLigacaoAguaSituacao"
											labelProperty="descricao" property="id" />
							</html:select> 
						</strong>
					</div>
					</td>
				</tr>
				<tr>
					<td><strong>Intervalo de Consumo M&iacute;nimo Fixado de
					&Aacute;gua:</strong></td>
					<td colspan="3" align="right">
					<div align="left"><strong> <logic:present
						name="parametroGerarRelatorio">
						<logic:equal name="parametroGerarRelatorio"
							value="RelatorioCadastroConsumidoresInscricao">
							<html:text property="consumoMinimoInicial" size="6" maxlength="6"
								onkeypress="return isCampoNumerico(event);"
								onkeyup="javascript:carregaConsumoMinimoFinal();"
								disabled="true" /> a 
					<html:text property="consumoMinimoFinal" size="6" maxlength="6"
								onblur="controleFaixaAgua();" disabled="true" 
								onkeypress="return isCampoNumerico(event);"/>
						</logic:equal>
						<logic:notEqual name="parametroGerarRelatorio"
							value="RelatorioCadastroConsumidoresInscricao">
							<logic:equal name="parametroGerarRelatorio"
								value="RelatorioImoveisEndereco">
								<html:text property="consumoMinimoInicial" size="6"
									onkeypress="return isCampoNumerico(event);"
									maxlength="6" onkeyup="javascript:carregaConsumoMinimoFinal();"
									disabled="true" /> a 
					<html:text property="consumoMinimoFinal" size="6" maxlength="6"
									onkeypress="return isCampoNumerico(event);"
									onblur="controleFaixaAgua();" disabled="true" />
							</logic:equal>
							<logic:notEqual name="parametroGerarRelatorio"
								value="RelatorioImoveisEndereco">
								<html:text property="consumoMinimoInicial" size="6"
									onkeypress="return isCampoNumerico(event);"
									maxlength="6" onkeyup="javascript:carregaConsumoMinimoFinal();" /> a 
					<html:text property="consumoMinimoFinal" size="6" maxlength="6"
									onkeypress="return isCampoNumerico(event);"
									onblur="controleFaixaAgua();" />
							</logic:notEqual>
						</logic:notEqual>
					</logic:present> <logic:notPresent name="parametroGerarRelatorio">
						<html:text property="consumoMinimoInicial" size="6" maxlength="6"
							onkeypress="return isCampoNumerico(event);"
							onkeyup="javascript:carregaConsumoMinimoFinal();" /> a 
					<html:text property="consumoMinimoFinal" size="6" maxlength="6"
							onkeypress="return isCampoNumerico(event);"
							onblur="controleFaixaAgua();" />
					</logic:notPresent></strong></div>
					</td>
				</tr>
				<tr>
					<td colspan="4">
					<hr>
					</td>
				</tr>
				<tr>
					<td><strong>Situa&ccedil;&atilde;o da Liga&ccedil;&atilde;o de Esgoto:</strong></td>
					<td colspan="3" align="right">
						<div align="left">
							<strong> 
								<html:select property="situacaoLigacaoEsgoto"
											 onchange="controlaTextEsgoto('${sessionScope.parametroGerarRelatorio}');habilitaOuDesabilitaTipoPoco(this);">
								<html:option value="-1">&nbsp;</html:option>
								<html:options   collection="collectionLigacaoEsgotoSituacao"
												labelProperty="descricao" property="id" />
								</html:select> 
							</strong>
						</div>
					</td>
				</tr>
				<tr>
					<td><strong> Intervalo de Percentual de Esgoto:</strong></td>
					<td colspan="3" align="right">
					<div align="left"><strong><logic:present
						name="parametroGerarRelatorio">
						<logic:equal name="parametroGerarRelatorio"
							value="RelatorioCadastroConsumidoresInscricao">
							<html:text property="intervaloPercentualEsgotoInicial" size="7"
								maxlength="7"
								onkeyup="javascript:formataValorMonetario(this, 7);carregaPercentualEsgotoFinal();"
								disabled="true" /> a 
					<html:text property="intervaloPercentualEsgotoFinal"
								onblur="controleFaixaEsgoto();" size="7"
								onkeyup="javascript:formataValorMonetario(this, 7);"
								maxlength="7" disabled="true" />
						</logic:equal>
						<logic:notEqual name="parametroGerarRelatorio"
							value="RelatorioCadastroConsumidoresInscricao">
							<logic:equal name="parametroGerarRelatorio"
								value="RelatorioImoveisEndereco">
								<html:text property="intervaloPercentualEsgotoInicial" size="7"
									maxlength="7"
									onkeyup="javascript:formataValorMonetario(this, 7);carregaPercentualEsgotoFinal();"
									disabled="true" /> a 
					<html:text property="intervaloPercentualEsgotoFinal"
									onblur="controleFaixaEsgoto();" size="7"
									onkeyup="javascript:formataValorMonetario(this, 7);"
									maxlength="7" disabled="true" />
							</logic:equal>
							<logic:notEqual name="parametroGerarRelatorio"
								value="RelatorioImoveisEndereco">
								<html:text property="intervaloPercentualEsgotoInicial" size="7"
									maxlength="7"
									onkeyup="javascript:formataValorMonetario(this, 7);carregaPercentualEsgotoFinal();" /> a 
					<html:text property="intervaloPercentualEsgotoFinal"
									onblur="controleFaixaEsgoto();" size="7"
									onkeyup="javascript:formataValorMonetario(this, 7);"
									maxlength="7" />
							</logic:notEqual>
						</logic:notEqual>
					</logic:present> <logic:notPresent name="parametroGerarRelatorio">
						<html:text property="intervaloPercentualEsgotoInicial" size="7"
							maxlength="7"
							onkeyup="javascript:formataValorMonetario(this, 7);carregaPercentualEsgotoFinal();" /> a 
					<html:text property="intervaloPercentualEsgotoFinal"
							onblur="controleFaixaEsgoto();" size="7"
							onkeyup="javascript:formataValorMonetario(this, 7);"
							maxlength="7" />
					</logic:notPresent></strong></div>
					</td>
				</tr>
				<tr>
					<td><strong>Intervalo de Consumo M&iacute;nimo Fixado de Esgoto:</strong></td>
					<td colspan="3" align="right">
					<div align="left"><strong> <logic:present
						name="parametroGerarRelatorio">
						<logic:equal name="parametroGerarRelatorio"
							value="RelatorioImoveisEndereco">
							<html:text property="consumoMinimoFixadoEsgotoInicial" size="6"
								onkeypress="return isCampoNumerico(event);"
								onkeyup="javascript:carregaFixadoEsgotoFinal();" maxlength="6"
								disabled="true" />
					a <html:text property="consumoMinimoFixadoEsgotoFinal" size="6"
								onkeypress="return isCampoNumerico(event);"
								onblur="controleFaixaEsgoto();" maxlength="6" disabled="true" />
						</logic:equal>
						<logic:notEqual name="parametroGerarRelatorio"
							value="RelatorioImoveisEndereco">
							<html:text property="consumoMinimoFixadoEsgotoInicial" size="6"
								onkeypress="return isCampoNumerico(event);"
								onkeyup="javascript:carregaFixadoEsgotoFinal();" maxlength="6" />
					a <html:text property="consumoMinimoFixadoEsgotoFinal" size="6"
								onkeypress="return isCampoNumerico(event);"
								onblur="controleFaixaEsgoto();" maxlength="6" />
						</logic:notEqual>
					</logic:present> <logic:notPresent name="parametroGerarRelatorio">
						<html:text property="consumoMinimoFixadoEsgotoInicial" size="6"
							onkeypress="return isCampoNumerico(event);"
							onkeyup="javascript:carregaFixadoEsgotoFinal();" maxlength="6" />
					a <html:text property="consumoMinimoFixadoEsgotoFinal" size="6"
							onkeypress="return isCampoNumerico(event);"
							onblur="controleFaixaEsgoto();" maxlength="6" />
					</logic:notPresent> </strong></div>
					</td>
				</tr>
				<tr>
					<td colspan="4">
					<hr>
					</td>
				</tr>
				<tr>
					<td><strong>Indicador de Medi&ccedil;&atilde;o:</strong></td>
					<td colspan="3" align="right">
					<div align="left"><strong> <html:radio property="indicadorMedicao"
						onclick="validarIndicadorMedicao('${sessionScope.parametroGerarRelatorio}');"
						value="semMedicao" />Sem Medi&ccedil;&atilde;o <html:radio
						onclick="validarIndicadorMedicao('${sessionScope.parametroGerarRelatorio}');"
						property="indicadorMedicao" value="comMedicao" /> Com
					Medi&ccedil;&atilde;o<html:radio
						onclick="validarIndicadorMedicao('${sessionScope.parametroGerarRelatorio}');"
						property="indicadorMedicao" value="" /> Todos</strong></div>
					</td>
				</tr>
				<tr>
					<td><strong>Tipo de Medi&ccedil;&atilde;o:</strong></td>
					<td colspan="3" align="right">
					<div align="left"><strong><html:select property="tipoMedicao"
						onchange="controlaTextMedicao('${sessionScope.parametroGerarRelatorio}');">
						<html:option value="-1">&nbsp;</html:option>
						<html:options collection="collectionFiltroMedicaoTipo"
							property="id" labelProperty="descricao" />
					</html:select></strong></div>
					</td>
				</tr>
				<tr>
					<td><strong> Intervalo de M&eacute;dia M&iacute;nima do
					Im&oacute;vel:</strong></td>
					<td colspan="3" align="right">
					<div align="left"><strong><logic:present
						name="parametroGerarRelatorio">
						<logic:equal name="parametroGerarRelatorio"
							value="RelatorioCadastroConsumidoresInscricao">
							<html:text property="intervaloMediaMinimaImovelInicio"
								onkeypress="return isCampoNumerico(event);"
								onkeyup="carregaMediaMinimaImovel();" size="6" maxlength="6"
								disabled="true" />
					a <html:text property="intervaloMediaMinimaImovelFinal" size="6"
								onkeypress="return isCampoNumerico(event);"
								onblur="controleMediaMinimaImovel();" maxlength="6"
								disabled="true" />
						</logic:equal>
						<logic:notEqual name="parametroGerarRelatorio"
							value="RelatorioCadastroConsumidoresInscricao">
							<logic:equal name="parametroGerarRelatorio"
								value="RelatorioImoveisEndereco">
								<html:text property="intervaloMediaMinimaImovelInicio"
									onkeypress="return isCampoNumerico(event);"
									onkeyup="carregaMediaMinimaImovel();" size="6" maxlength="6"
									disabled="true" />
					a <html:text property="intervaloMediaMinimaImovelFinal" size="6"
									onkeypress="return isCampoNumerico(event);"
									onblur="controleMediaMinimaImovel();" maxlength="6"
									disabled="true" />
							</logic:equal>
							<logic:notEqual name="parametroGerarRelatorio"
								value="RelatorioImoveisEndereco">
								<html:text property="intervaloMediaMinimaImovelInicio"
									onkeyup="carregaMediaMinimaImovel();" size="6" maxlength="6" 
									onkeypress="return isCampoNumerico(event);"/>
					a <html:text property="intervaloMediaMinimaImovelFinal" size="6"
									onblur="controleMediaMinimaImovel();" maxlength="6" 
									onkeypress="return isCampoNumerico(event);"/>
							</logic:notEqual>
						</logic:notEqual>
					</logic:present> <logic:notPresent name="parametroGerarRelatorio">
						<html:text property="intervaloMediaMinimaImovelInicio"
							onkeyup="carregaMediaMinimaImovel();" size="6" maxlength="6"
							onkeypress="return isCampoNumerico(event);" />
					a <html:text property="intervaloMediaMinimaImovelFinal" size="6"
							onkeypress="return isCampoNumerico(event);"
							onblur="controleMediaMinimaImovel();" maxlength="6" />
					</logic:notPresent> </strong></div>
					</td>
				</tr>
				<tr>
					<td><strong> Intervalo de M&eacute;dia M&iacute;nima do
					Hidr&ocirc;metro:</strong></td>
					<td colspan="3" align="right">
					<div align="left"><strong> <logic:present
						name="parametroGerarRelatorio">
						<logic:equal name="parametroGerarRelatorio"
							value="RelatorioCadastroConsumidoresInscricao">
							<html:text property="intervaloMediaMinimaHidrometroInicio"
								onkeypress="return isCampoNumerico(event);"
								onkeyup="carregaMediaMinimaHidrometro();" size="6" maxlength="6"
								disabled="true" /> a <html:text
								property="intervaloMediaMinimaHidrometroFinal" size="6"
								onkeypress="return isCampoNumerico(event);"
								onblur="controleMediaMinimaHidrometro();" maxlength="6"
								disabled="true" />
						</logic:equal>
						<logic:notEqual name="parametroGerarRelatorio"
							value="RelatorioCadastroConsumidoresInscricao">
							<logic:equal name="parametroGerarRelatorio"
								value="RelatorioImoveisEndereco">
								<html:text property="intervaloMediaMinimaHidrometroInicio"
									onkeypress="return isCampoNumerico(event);"
									onkeyup="carregaMediaMinimaHidrometro();" size="6"
									maxlength="6" disabled="true" /> a <html:text
									property="intervaloMediaMinimaHidrometroFinal" size="6"
									onkeypress="return isCampoNumerico(event);"
									onblur="controleMediaMinimaHidrometro();" maxlength="6"
									disabled="true" />
							</logic:equal>
							<logic:notEqual name="parametroGerarRelatorio"
								value="RelatorioImoveisEndereco">
								<html:text property="intervaloMediaMinimaHidrometroInicio"
									onkeyup="carregaMediaMinimaHidrometro();" size="6"
									onkeypress="return isCampoNumerico(event);"
									maxlength="6" /> a <html:text
									property="intervaloMediaMinimaHidrometroFinal" size="6"
									onkeypress="return isCampoNumerico(event);"
									onblur="controleMediaMinimaHidrometro();" maxlength="6" />
							</logic:notEqual>
						</logic:notEqual>
					</logic:present> <logic:notPresent name="parametroGerarRelatorio">
						<html:text property="intervaloMediaMinimaHidrometroInicio"
							onkeyup="carregaMediaMinimaHidrometro();" 
							onkeypress="return isCampoNumerico(event);"
							size="6" maxlength="6" /> a <html:text
							property="intervaloMediaMinimaHidrometroFinal" size="6"
							onblur="controleMediaMinimaHidrometro();" maxlength="6" 
							onkeypress="return isCampoNumerico(event);"/>
					</logic:notPresent> </strong></div>
					</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td colspan="3" align="right">&nbsp;</td>
				</tr>
				<tr>
					<td colspan="4"><jsp:include
						page="/jsp/util/wizard/navegacao_botoes_wizard_valida_avancar_valida_voltar.jsp?numeroPagina=3" />
					</td>
				</tr>

			</table>
			<p>&nbsp;</p>
		</tr>
	</table>

	<%@ include file="/jsp/util/rodape.jsp"%>

</html:form>
</div>
</body>

<%@ include file="/jsp/util/telaespera.jsp"%>

<script>
document.getElementById('botaoConcluir').onclick = function() { botaoAvancarTelaEspera('/gsan/filtrarImovelOutrosCriteriosWizardAction.do?concluir=true&action=validarImovelOutrosCriterios'); }
</script>

</html:html>
