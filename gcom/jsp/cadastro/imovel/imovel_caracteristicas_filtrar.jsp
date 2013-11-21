
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
<script language="JavaScript">

 var bCancel = false;

function validarForm(form){

  if(validatePesquisarActionForm(form)){
    form.submit();
  }
}

</script>

<script>
<!-- Begin 

     var bCancel = false; 

    function validateImovelOutrosCriteriosActionForm(form) {                                                                   
        if (bCancel) 
      return true; 
        else 
       return validateCaracterEspecial(form) && validateInteger(form)
       && validarQuantidadeEconomias()
	   && validarNumeroPontos()
	   && validarNumeroMoradores()
       && validarAreaConstruida();
   } 

    function caracteresespeciais () { 
     this.aa = new Array("quantidadeEconomiasInicial", "Intervalo de Quantidade de Economias Inicial deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
     this.ab = new Array("quantidadeEconomiasFinal", "Intervalo de Quantidade de Economias Final deve somente conter números positivos.", new Function ("varName", " return this[varName];"));

     this.ac = new Array("numeroPontosInicial", "Intervalo de Número de Pontos Inicial deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
     this.ad = new Array("numeroPontosFinal", "Intervalo de Número de Pontos Final deve somente conter números positivos.", new Function ("varName", " return this[varName];"));

     this.ae = new Array("numeroMoradoresInicial", "Intervalo de Número de Moradores Inicial deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
     this.af = new Array("numeroMoradoresFinal", "Intervalo de Número de Moradores Final deve somente conter números positivos.", new Function ("varName", " return this[varName];"));

     this.ag = new Array("areaConstruidaInicial", "Intervalo de Área Construída Inicial deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
     this.ah = new Array("areaConstruidaFinal", "Intervalo de Área Construída Final deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
    } 

    function IntegerValidations () { 
     this.ai = new Array("quantidadeEconomiasInicial", "Intervalo de Quantidade de Economias Inicial deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
     this.aj = new Array("quantidadeEconomiasFinal", "Intervalo de Quantidade de Economias Final deve somente conter números positivos.", new Function ("varName", " return this[varName];"));

     this.al = new Array("numeroPontosInicial", "Intervalo de Número de Pontos Inicial deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
     this.am = new Array("numeroPontosFinal", "Intervalo de Número de Pontos Final deve somente conter números positivos.", new Function ("varName", " return this[varName];"));

     this.an = new Array("numeroMoradoresInicial", "Intervalo de Número de Moradores Inicial deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
     this.ao = new Array("numeroMoradoresFinal", "Intervalo de Número de Moradores Final deve somente conter números positivos.", new Function ("varName", " return this[varName];"));

     this.ap = new Array("areaConstruidaInicial", "Intervalo de Área Construída Inicial deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
     this.aq = new Array("areaConstruidaFinal", "Intervalo de Área Construída Final deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
    } 
-->    
</script>

<script language="JavaScript">
function carregaQuantidadeEconomias(){
	var form = document.ImovelOutrosCriteriosActionForm;
	
	form.quantidadeEconomiasFinal.value = form.quantidadeEconomiasInicial.value;
}

function validarQuantidadeEconomias(){
	var form = document.ImovelOutrosCriteriosActionForm;
	
	if(form.quantidadeEconomiasInicial.value > form.quantidadeEconomiasFinal.value){
		alert("Intervalo de Quantidade de Economias Inicial maior que o Intervalo de Quantidade de Economias Final.");
		form.quantidadeEconomiasFinal.value="";
		form.quantidadeEconomiasFinal.focus();
		return false;
	}
	return true;
}

function carregaNumeroPontos(){
	var form = document.ImovelOutrosCriteriosActionForm;
	
	form.numeroPontosFinal.value = form.numeroPontosInicial.value;
}

function validarNumeroPontos(){
	var form = document.ImovelOutrosCriteriosActionForm;
	if(form.numeroPontosInicial.value*1 > form.numeroPontosFinal.value*1){
		alert("Intervalo de Número de Pontos Inicial maior que o Intervalo de Número de Pontos Final.");
		form.numeroPontosFinal.value="";
		form.numeroPontosFinal.focus();
		return false;
	}
	return true;
}

function carregaNumeroMoradores(){
	var form = document.ImovelOutrosCriteriosActionForm;
	
	form.numeroMoradoresFinal.value = form.numeroMoradoresInicial.value;
}

function validarNumeroMoradores(){
	var form = document.ImovelOutrosCriteriosActionForm;
	
	if(form.numeroMoradoresInicial.value*1 > form.numeroMoradoresFinal.value*1){
		alert("Intervalo de Número de Moradores Inicial maior que o Intervalo de Número de Moradores Final.");
		form.numeroMoradoresFinal.value="";
		form.numeroMoradoresFinal.focus();
		return false;
	}
	return true;
}

function carregaAreaConstruida(){
	var form = document.ImovelOutrosCriteriosActionForm;
	
	form.areaConstruidaFinal.value = form.areaConstruidaInicial.value;
}

function validarAreaConstruida(){
	var form = document.ImovelOutrosCriteriosActionForm;
	
	var inicial = parseFloat(form.areaConstruidaInicial.value);
	var final = parseFloat(form.areaConstruidaFinal.value);
	
	if(inicial > final){
		alert("Intervalo de Área Construída Inicial maior que o Intervalo de Área Construída Final.");
		form.areaConstruidaFinal.value="";
		form.areaConstruidaFinal.focus();
		return false;
	}
	return true;
}

function validarAreaConstruidaCombo(){

	var form = document.ImovelOutrosCriteriosActionForm;
	
	if(form.areaConstruidaFaixa.value != '-1'){
		form.areaConstruidaInicial.disabled = true;
		form.areaConstruidaFinal.disabled = true;
	}else{
		form.areaConstruidaInicial.disabled = false;
		form.areaConstruidaFinal.disabled = false;
	}
	
}

</script>

<script language="JavaScript">
function controlaCamposAreaConstruida(){
	var form = document.ImovelOutrosCriteriosActionForm;
	
	if(form.areaConstruidaInicial.value != ""
		|| form.areaConstruidaFinal.value != ""){
		form.areaConstruidaFaixa.disabled = true;
	}else{
		form.areaConstruidaFaixa.disabled = false;
	}
}

function habilitaOuDesabilitaTipoPoco(){
	var form = document.forms[0];

	if ( form.indicadorHabilitaOuDesabilitaTipoPoco.value == "HABILITA" ) {

		form.tipoPocoList.disabled = false;
	} else {

		form.tipoPocoList.disabled = true;
	}		
}

</script>


</head>

<body leftmargin="5" topmargin="5" onload="javascript:habilitaOuDesabilitaTipoPoco();">
<div id="formDiv">
<html:form action="/filtrarImovelOutrosCriteriosWizardAction"
	name="ImovelOutrosCriteriosActionForm"
	type="gcom.gui.cadastro.imovel.ImovelOutrosCriteriosActionForm"
	method="post"
	onsubmit="return validateImovelOutrosCriteriosActionForm(this);">

	<jsp:include
		page="/jsp/util/wizard/navegacao_abas_wizard_valida_avancar_valida_voltar.jsp?numeroPagina=4" />


	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>
	
	<html:hidden property="indicadorHabilitaOuDesabilitaTipoPoco" />

	<table width="770" border="0" cellspacing="1" cellpadding="0">
		<input type="hidden" name="numeroPagina" value="4" />
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
			<td width="617" valign="top" class="centercoltext">
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
					<td colspan="4">Para filtrar o(s) im&oacute;vel(is) pelas
					caracter&iacute;sticas gerais, informe os dados abaixo:</td>
				</tr>
				<tr>
					<td width="243"><strong>Perfil do Im&oacute;vel:</strong></td>
					<td width="354" colspan="3" align="right">
					<div align="left"><strong><html:select property="perfilImovel">
						<html:option value="-1">&nbsp;</html:option>
						<html:options collection="collectionImovelPerfil"
							labelProperty="descricao" property="id" />
					</html:select></strong></div>
					</td>
				</tr>
				<tr>
					<td colspan="4"></td>
				<tr>
				<tr>
					<td><strong>Categoria:</strong></td>
					<td colspan="3" align="right">
					<div align="left"><strong> <html:select property="categoriaImovel"
						onchange="javascript:document.ImovelOutrosCriteriosActionForm.subcategoria.value='-1';pesquisaColecaoReload('filtrarImovelOutrosCriteriosWizardAction.do?action=exibirCaracteristicasImovel','categoriaImovel');">
						<html:option value="-1">&nbsp;</html:option>
						<html:options collection="collectionImovelCategoria"
							labelProperty="descricao" property="id" />
					</html:select></strong></div>
					</td>
				</tr>
				<tr>
					<td colspan="4"></td>
				<tr>
				<tr>
					<td><strong>Subcategoria:</strong></td>
					<td colspan="3" align="right">
					<div align="left"><strong> <html:select property="subcategoria"
						onchange="javascript:pesquisaColecaoReload('filtrarImovelOutrosCriteriosWizardAction.do?action=exibirCaracteristicasImovel','subcategoria');">
						<html:option value="-1">&nbsp;</html:option>
						<html:options collection="collectionImovelSubcategoria"
							labelProperty="descricao" property="id" />
					</html:select></strong></div>
					</td>
				</tr>
				<tr>
					<td colspan="4">
					<hr>
					</td>
				<tr>
				<tr>
					<td><strong>Intervalo de Quantidade de Economias:</strong></td>
					<td colspan="3" align="right">
					<div align="left"><strong> <html:text
						property="quantidadeEconomiasInicial" size="4"
						onkeypress="return isCampoNumerico(event);"
						onkeyup="javascript:carregaQuantidadeEconomias();" maxlength="4" />
					a <html:text property="quantidadeEconomiasFinal" size="4"
						maxlength="4" onkeypress="return isCampoNumerico(event);"/> </strong></div>

					</td>
				</tr>
				<tr>
					<td colspan="4"></td>
				<tr>
				<tr>
					<td><strong>Intervalo de N&uacute;mero de Pontos:</strong></td>
					<td colspan="3" align="right">
					<div align="left"><strong><logic:present
						name="parametroGerarRelatorio">
						<logic:equal name="parametroGerarRelatorio"
							value="RelatorioCadastroConsumidoresInscricao">
							<html:text property="numeroPontosInicial" size="4"
								onkeypress="return isCampoNumerico(event);"
								onkeyup="javascript:carregaNumeroPontos();" maxlength="4"
								disabled="true" /> a <html:text property="numeroPontosFinal"
								size="4" maxlength="4" disabled="true" 
								onkeypress="return isCampoNumerico(event);"/>
						</logic:equal>
						<logic:notEqual name="parametroGerarRelatorio"
							value="RelatorioCadastroConsumidoresInscricao">
							<logic:equal name="parametroGerarRelatorio"
								value="RelatorioImoveisEndereco">
								<html:text property="numeroPontosInicial" size="4"
									onkeypress="return isCampoNumerico(event);"
									onkeyup="javascript:carregaNumeroPontos();" maxlength="4"
									disabled="true" /> a <html:text property="numeroPontosFinal"
									size="4" maxlength="4" disabled="true" 
									onkeypress="return isCampoNumerico(event);"/>
							</logic:equal>
							<logic:notEqual name="parametroGerarRelatorio"
								value="RelatorioImoveisEndereco">
								<html:text property="numeroPontosInicial" size="4"
									onkeypress="return isCampoNumerico(event);"
									onkeyup="javascript:carregaNumeroPontos();" maxlength="4" /> a <html:text
									property="numeroPontosFinal" size="4" maxlength="4"
									onkeypress="return isCampoNumerico(event);" />
							</logic:notEqual>
						</logic:notEqual>
					</logic:present> <logic:notPresent name="parametroGerarRelatorio">
						<html:text property="numeroPontosInicial" size="4"
							onkeypress="return isCampoNumerico(event);"
							onkeyup="javascript:carregaNumeroPontos();" maxlength="4" /> a <html:text
							property="numeroPontosFinal" size="4" maxlength="4" 
							onkeypress="return isCampoNumerico(event);"/>
					</logic:notPresent></strong></div>
					</td>
				</tr>
				<tr>
					<td><strong>Intervalo de N&uacute;mero de Moradores:</strong></td>
					<td colspan="3" align="right">
					<div align="left"><strong> <logic:present
						name="parametroGerarRelatorio">
						<logic:equal name="parametroGerarRelatorio"
							value="RelatorioCadastroConsumidoresInscricao">
							<html:text property="numeroMoradoresInicial" size="4"
								onkeypress="return isCampoNumerico(event);"
								onkeyup="javascript:carregaNumeroMoradores()" maxlength="4"
								disabled="true" /> a <html:text property="numeroMoradoresFinal"
								onkeypress="return isCampoNumerico(event);"
								size="4" maxlength="4" disabled="true" />
						</logic:equal>
						<logic:notEqual name="parametroGerarRelatorio"
							value="RelatorioCadastroConsumidoresInscricao">
							<logic:equal name="parametroGerarRelatorio"
								value="RelatorioImoveisEndereco">
								<html:text property="numeroMoradoresInicial" size="4"
									onkeypress="return isCampoNumerico(event);"
									onkeyup="javascript:carregaNumeroMoradores()" maxlength="4"
									disabled="true" /> a <html:text
									property="numeroMoradoresFinal" size="4" maxlength="4"
									onkeypress="return isCampoNumerico(event);"
									disabled="true" />
							</logic:equal>
							<logic:notEqual name="parametroGerarRelatorio"
								value="RelatorioImoveisEndereco">
								<html:text property="numeroMoradoresInicial" size="4"
									onkeypress="return isCampoNumerico(event);"
									onkeyup="javascript:carregaNumeroMoradores()" maxlength="4" /> a <html:text
									property="numeroMoradoresFinal" size="4" maxlength="4" 
									onkeypress="return isCampoNumerico(event);"/>
							</logic:notEqual>
						</logic:notEqual>
					</logic:present> <logic:notPresent name="parametroGerarRelatorio">
						<html:text property="numeroMoradoresInicial" size="4"
							onkeypress="return isCampoNumerico(event);"
							onkeyup="javascript:carregaNumeroMoradores()" maxlength="4" /> a <html:text
							property="numeroMoradoresFinal" size="4" maxlength="4" 
							onkeypress="return isCampoNumerico(event);"/>
					</logic:notPresent></strong></div>
					</td>
				</tr>
				<tr>
					<td colspan="4">
					<hr>
					</td>
				<tr>
				<tr>
					<td><strong>Intervalo de &Aacute;rea Constru&iacute;da:</strong></td>
					<td colspan="3" align="right">
					<div align="left"><strong> <logic:present
						name="parametroGerarRelatorio">
						<logic:equal name="parametroGerarRelatorio"
							value="RelatorioCadastroConsumidoresInscricao">
							<html:text maxlength="10" size="10" style="text-align: right;"
								onkeypress="return isCampoNumerico(event);"
								property="areaConstruidaInicial"
								onkeyup="javascript:formataValorMonetario(this, 8);carregaAreaConstruida();controlaCamposAreaConstruida();"
								disabled="false" /> a 
						<html:text property="areaConstruidaFinal" maxlength="10" size="10"
								onkeypress="return isCampoNumerico(event);"
								style="text-align: right;"
								onkeyup="formataValorMonetario(this, 8);controlaCamposAreaConstruida();"
								disabled="false" />
							<html:select property="areaConstruidaFaixa" disabled="false" />
						</logic:equal>
						<logic:notEqual name="parametroGerarRelatorio"
							value="RelatorioCadastroConsumidoresInscricao">
							<logic:equal name="parametroGerarRelatorio"
								value="RelatorioImoveisEndereco">
								<html:text maxlength="10" size="10" style="text-align: right;"
									property="areaConstruidaInicial"
									onkeypress="return isCampoNumerico(event);"
									onkeyup="javascript:formataValorMonetario(this, 8);carregaAreaConstruida();controlaCamposAreaConstruida();"
									disabled="false" /> a 
						<html:text property="areaConstruidaFinal" maxlength="10" size="10"
									style="text-align: right;"
									onkeypress="return isCampoNumerico(event);"
									onkeyup="formataValorMonetario(this, 8);controlaCamposAreaConstruida();"
									disabled="false" />
								<html:select property="areaConstruidaFaixa" disabled="false" />
							</logic:equal>
							<logic:notEqual name="parametroGerarRelatorio"
								value="RelatorioImoveisEndereco">
								<html:text maxlength="10" size="10" style="text-align: right;"
									property="areaConstruidaInicial"
									onkeypress="return isCampoNumerico(event);"
									onkeyup="javascript:formataValorMonetario(this, 8);carregaAreaConstruida();controlaCamposAreaConstruida();" /> a 
						<html:text property="areaConstruidaFinal" maxlength="10" size="10"
									style="text-align: right;"
									onkeypress="return isCampoNumerico(event);"
									onkeyup="formataValorMonetario(this, 8);controlaCamposAreaConstruida();" />
								<html:select onchange="javascript:validarAreaConstruidaCombo();"
									property="areaConstruidaFaixa">
									<html:option value="-1">&nbsp;</html:option>
									<html:options collection="collectionAreaConstuidaFaixa"
										labelProperty="faixaCompleta" property="id" />
								</html:select>
							</logic:notEqual>
						</logic:notEqual>
					</logic:present> <logic:notPresent name="parametroGerarRelatorio">
						<html:text maxlength="10" size="10" style="text-align: right;"
							property="areaConstruidaInicial"
							onkeypress="return isCampoNumerico(event);"
							onkeyup="javascript:formataValorMonetario(this, 8);carregaAreaConstruida();controlaCamposAreaConstruida();" /> a 
						<html:text property="areaConstruidaFinal" maxlength="10" size="10"
							style="text-align: right;"
							onkeyup="formataValorMonetario(this, 8);controlaCamposAreaConstruida();" />
						<html:select onchange="javascript:validarAreaConstruidaCombo();"
							property="areaConstruidaFaixa">
							<html:option value="-1">&nbsp;</html:option>
							<html:options collection="collectionAreaConstuidaFaixa"
								labelProperty="faixaCompleta" property="id" />
						</html:select>
					</logic:notPresent></strong></div>
					</td>
				</tr>
				<tr>
					<td><strong>Tipo de Po&ccedil;o:</strong></td>
					<td colspan="3" align="right">
					<div align="left"><strong><logic:present
						name="parametroGerarRelatorio">
						<logic:equal name="parametroGerarRelatorio"
							value="RelatorioCadastroConsumidoresInscricao">
							<html:select property="tipoPocoList" 
										 style="width: 230px;" 
									 	 multiple="mutiple" 
									 	 size="6" >
								<html:option value="-1">&nbsp;</html:option>
								<html:options collection="collectionTipoPoco"
									labelProperty="descricao" property="id"/>
							</html:select>	
						</logic:equal>
						<logic:notEqual name="parametroGerarRelatorio"
							value="RelatorioCadastroConsumidoresInscricao">
							<html:select property="tipoPoco">
								<html:option value="-1">&nbsp;</html:option>
								<html:options collection="collectionTipoPoco"
									labelProperty="descricao" property="id" />
							</html:select>
						</logic:notEqual>
					</logic:present> <logic:notPresent name="parametroGerarRelatorio">
						<html:select property="tipoPoco">
							<html:option value="-1">&nbsp;</html:option>
							<html:options collection="collectionTipoPoco"
								labelProperty="descricao" property="id" />
						</html:select>
					</logic:notPresent></strong></div>
					</td>
				</tr>
				<tr>
				<tr>
					<td>&nbsp;</td>
					<td colspan="3" align="right">&nbsp;</td>
					
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td colspan="3" align="right">
					<div align="left"><strong> </strong></div>
					</td>
				</tr>

				<tr>

					<td colspan="4">
					<div align="right"><jsp:include
						page="/jsp/util/wizard/navegacao_botoes_wizard_valida_avancar_valida_voltar.jsp?numeroPagina=4" />
					</div>
					</td>
				</tr>
			</table>
			<td>&nbsp;</td>
			<td colspan="3" align="right">&nbsp;</td>
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
