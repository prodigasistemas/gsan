<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>

<title>GSAN - Sistema Integrado de Gest&atilde;o de Servi&ccedil;os de Saneamento</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<link rel="stylesheet"	href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">

<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false" formName="ImovelCurvaAbcDebitosActionForm" dynamicJavascript="false" />

<script>
<!-- Begin 

     var bCancel = false; 

    function validateImovelCurvaAbcDebitosActionForm(form) {
    	var retorno = false;
    	
		if (bCancel) 
			return true; 
		else 
			retorno = validateCaracterEspecial(form) && validateInteger(form) && controleFaixaEsgoto();
				
		//**************************************************************
		// Autor: Ivan Sergio
		// Data: 08/05/2009
		// CRC1491
		// Verifica se o usuario clicou no Concluir para mostrar a tela
		// de Espera. Esta verificacao deve ser feita por conta do Wizard.
		//**************************************************************
		if (retorno == true) {
			var action = form.action;
			if (action.indexOf("concluir=true") > 0) {
				submitForm(form);
			} else {
				return retorno;
			}
		} else {
			return retorno;
		}
		//**************************************************************
   } 

    function caracteresespeciais () { 
		this.aa = new Array("intervaloConsumoMinimoFixadoEsgotoInicial", "Intervalo de Média Mínima do Imóvel Inicial deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
		this.ab = new Array("intervaloConsumoMinimoFixadoEsgotoFinal", "Intervalo de Média Mínima do Imóvel Final deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
		this.ac = new Array("intervaloMesesCortadoSuprimidoInicial", "Intervalo de Meses para Cortado ou Suprimido deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
		this.ad = new Array("intervaloMesesCortadoSuprimidoFinal", "Intervalo de Meses para Cortado ou Suprimido deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
    } 

    function IntegerValidations () { 
		this.aa = new Array("intervaloConsumoMinimoFixadoEsgotoInicial", "Intervalo de Média Mínima do Imóvel Inicial deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
		this.ab = new Array("intervaloConsumoMinimoFixadoEsgotoFinal", "Intervalo de Média Mínima do Imóvel Final deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
		this.ac = new Array("intervaloMesesCortadoSuprimidoInicial", "Intervalo de Meses para Cortado ou Suprimido deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
		this.ad = new Array("intervaloMesesCortadoSuprimidoFinal", "Intervalo de Meses para Cortado ou Suprimido deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
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
function controlaTextEsgoto(){
	var form = document.ImovelCurvaAbcDebitosActionForm;
	
	if(form.situacaoLigacaoEsgoto.value != "3"
		&& form.situacaoLigacaoEsgoto.value != "5" ){
		form.intervaloConsumoMinimoFixadoEsgotoInicial.value = "";
		form.intervaloConsumoMinimoFixadoEsgotoInicial.disabled = true;
		form.intervaloConsumoMinimoFixadoEsgotoFinal.value = "";
		form.intervaloConsumoMinimoFixadoEsgotoFinal.disabled = true;
	} else {
		form.intervaloPercentualEsgotoInicial.disabled = false;
		form.intervaloPercentualEsgotoFinal.disabled = false;
		form.consumoMinimoFixadoEsgotoInicial.disabled = false;
		form.consumoMinimoFixadoEsgotoFinal.disabled = false;
	}
}

function controleFaixaEsgoto(){
	var form = document.ImovelCurvaAbcDebitosActionForm;
	
	if(form.intervaloConsumoMinimoFixadoEsgotoInicial.value > form.intervaloConsumoMinimoFixadoEsgotoFinal.value){
		alert("Intervalo de Consumo Mínimo Fixado de Esgoto Inicial maior que o Intervalo de Consumo Mínimo Fixado de Esgoto Final.");
		form.intervaloConsumoMinimoFixadoEsgotoFinal.value = "";
		form.intervaloConsumoMinimoFixadoEsgotoFinal.focus();
		return false;
	}
	
	return true;
}

function carregaFixadoEsgotoFinal(){
	var form = document.ImovelCurvaAbcDebitosActionForm;
	form.intervaloConsumoMinimoFixadoEsgotoFinal.value = form.intervaloConsumoMinimoFixadoEsgotoInicial.value;
}

function carregaIntervaloMesesCortadoSuprimido(){
	var form = document.ImovelCurvaAbcDebitosActionForm;
	form.intervaloMesesCortadoSuprimidoFinal.value = form.intervaloMesesCortadoSuprimidoInicial.value;
}

function controleIntervaloMesesCortadoSuprimido(){
	var form = document.ImovelCurvaAbcDebitosActionForm;
	
	if(form.intervaloMesesCortadoSuprimidoInicial.value > form.intervaloMesesCortadoSuprimidoFinal.value){
		alert("Intervalo de Meses para Cortado ou Suprimido Inicial maior que o Intervalo de Meses para Cortado ou Suprimido Final.");
		form.intervaloMesesCortadoSuprimidoFinal.value = "";
		form.intervaloMesesCortadoSuprimidoFinal.focus();
		return false;
	}
	
	return true;
}

-->
</script>

<script language="JavaScript">
function controlaTextMedicao(){
	var form = document.ImovelCurvaAbcDebitosActionForm;
	
	if(form.idTipoMedicao.value != "1" && form.idTipoMedicao.value != "2" ){
		form.intervaloMediaMinimaImovelInicio.value = "";
		form.intervaloMediaMinimaImovelFinal.value = "";
		form.intervaloMediaMinimaImovelInicio.disabled = true;
		form.intervaloMediaMinimaImovelFinal.disabled = true;
		
		form.intervaloMediaMinimaHidrometroInicio.value = "";
		form.intervaloMediaMinimaHidrometroFinal.value = "";
		form.intervaloMediaMinimaHidrometroInicio.disabled = true;
		form.intervaloMediaMinimaHidrometroFinal.disabled = true;
	} else if (tipoRelatorio != "RelatorioCadastroConsumidoresInscricao") {
		form.intervaloMediaMinimaImovelInicio.disabled = false;
		form.intervaloMediaMinimaImovelFinal.disabled = false;
		
		form.intervaloMediaMinimaHidrometroInicio.disabled = false;
		form.intervaloMediaMinimaHidrometroFinal.disabled = false;
	}
}

function validarIndicadorMedicao(){
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
		form.idTipoMedicao.disabled = true;
		form.idTipoMedicao[0].selected = true;
		
	}else if(selecionado == "comMedicao"){
		form.idTipoMedicao.disabled = false;
	}else if(selecionado == "semMedicao"){
		form.idTipoMedicao.disabled = true;
		form.idTipoMedicao[0].selected = true;
	}

}

</script>

</head>

<body leftmargin="5" topmargin="5" onload="//controlaTextEsgoto(); controlaTextMedicao();">

<div id="formDiv">
<html:form action="/filtrarImovelCurvaAbcDebitosWizardAction" name="ImovelCurvaAbcDebitosActionForm" method="post"
	type="gcom.gui.cadastro.imovel.ImovelCurvaAbcDebitosActionForm"
	onsubmit="validateImovelCurvaAbcDebitosActionForm(this);">
	
	<jsp:include page="/jsp/util/wizard/navegacao_abas_wizard_valida_avancar_valida_voltar.jsp?numeroPagina=3" />
	
	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>

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
					situa&ccedil;&atilde;o de &aacute;gua, de esgoto, informe os dados abaixo:</td>
				</tr>
				<tr>
					<td width="234">
						<strong>Situa&ccedil;&atilde;o da Liga&ccedil;&atilde;o de &Aacute;gua:</strong>
					</td>
					<td width="363" colspan="3" align="right">
						<div align="left">
							<strong>
								<html:select property="situacaoLigacaoAgua" multiple="multiple" size="3" style="width: 230px;">
									<html:option value="-1">&nbsp;</html:option>
									<html:options collection="collectionsLigacaoAguaSituacao"
										labelProperty="descricao" property="id" />
								</html:select>
							</strong>
						</div>
					</td>
				</tr>
				
				<tr>
					<td><strong>Situa&ccedil;&atilde;o da Liga&ccedil;&atilde;o de Esgoto:</strong></td>
					<td colspan="3" align="right">
						<div align="left">
							<strong> 
								<html:select property="situacaoLigacaoEsgoto" multiple="multiple" size="3" style="width: 230px;">
									<html:option value="-1">&nbsp;</html:option>
									<html:options collection="collectionLigacaoEsgotoSituacao"
										labelProperty="descricao" property="id" />
								</html:select>
							</strong>
						</div>
					</td>
				</tr>
				
				<tr>
					<td><strong>Intervalo de Consumo M&iacute;nimo Fixado de Esgoto:</strong></td>
					<td colspan="3" align="right">
						<div align="left">
							<strong>
								<html:text property="intervaloConsumoMinimoFixadoEsgotoInicial" size="6"
									onkeyup="javascript:carregaFixadoEsgotoFinal();" onkeypress="return isCampoNumerico(event);" maxlength="6" />
								a
								<html:text property="intervaloConsumoMinimoFixadoEsgotoFinal" size="6"
									onblur="controleFaixaEsgoto();" onkeypress="return isCampoNumerico(event);" maxlength="6" />
							</strong>
						</div>
					</td>
				</tr>
				
				<tr><td colspan="2"><hr></td></tr>
				
				<tr><td colspan="2"><strong>Intervalo de Meses para Cortado ou Suprimido:</strong></td></tr>
				<tr>
					<td><strong>Inicial:</strong></td>
					<td>
						<html:text property="intervaloMesesCortadoSuprimidoInicial" size="3"
							onkeyup="javascript:carregaIntervaloMesesCortadoSuprimido();" onkeypress="return isCampoNumerico(event);" maxlength="2" />
					</td>
				</tr>
				<tr>
					<td><strong>Final:</strong></td>
					<td>
						<html:text property="intervaloMesesCortadoSuprimidoFinal" size="3"
							onblur="controleIntervaloMesesCortadoSuprimido();" onkeypress="return isCampoNumerico(event);" maxlength="2" />
					</td>
				</tr>
				
				<tr><td colspan="2"><hr></td></tr>
				
				<tr>
					<td><strong>Indicador de Medi&ccedil;&atilde;o:</strong></td>
					<td colspan="3" align="right">
						<div align="left">
							<strong>
								<html:radio property="indicadorMedicao"
									onclick="validarIndicadorMedicao();"
									value="semMedicao" />Sem Medi&ccedil;&atilde;o
								<html:radio
									onclick="validarIndicadorMedicao();"
									property="indicadorMedicao" value="comMedicao" /> Com Medi&ccedil;&atilde;o
								<html:radio
									onclick="validarIndicadorMedicao();"
									property="indicadorMedicao" value="" /> Todos
							</strong>
						</div>
					</td>
				</tr>
				<tr>
					<td><strong>Tipo de Medi&ccedil;&atilde;o:</strong></td>
					<td colspan="3" align="right">
						<div align="left">
							<strong>
								<html:select property="idTipoMedicao">
									<html:option value="-1">&nbsp;</html:option>
									<html:options collection="collectionFiltroMedicaoTipo" property="id"
										labelProperty="descricao" />
								</html:select>
							</strong>
						</div>
					</td>
				</tr>
				
				<tr>
					<td>&nbsp;</td>
					<td colspan="3" align="right">&nbsp;</td>
				</tr>
				<tr>
					<td colspan="4">
						<jsp:include page="/jsp/util/wizard/navegacao_botoes_wizard_valida_avancar_valida_voltar.jsp?numeroPagina=3" />
					</td>
				</tr>

			</table>
			<p>&nbsp;</p>
		</tr>
	</table>

	<%@ include file="/jsp/util/rodape.jsp"%>
</html:form>
</div>

<logic:present name="classificacao">
	<logic:equal name="classificacao" value="ESTADO">
		<script>document.getElementById('2').style.display = 'none';</script>
	</logic:equal>
	<logic:notEqual name="classificacao" value="ESTADO">
		<script>document.getElementById('2').style.display = '';</script>
	</logic:notEqual>
</logic:present>
<logic:notPresent name="classificacao">
	<script>document.getElementById('2').style.display = 'none';</script>
</logic:notPresent>

</body>

<%@ include file="/jsp/util/telaespera.jsp"%>

</html:html>