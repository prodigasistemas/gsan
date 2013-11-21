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
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false"
	formName="ImovelCurvaAbcDebitosActionForm" dynamicJavascript="false" />

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>cadastro/imovel/imovel_outros_criterios.js"></script>

<script>
<!-- Begin 
	var bCancel = false; 
	
    function validateImovelCurvaAbcDebitosActionForm(form) {
    	var retorno = false;
    	
        if (bCancel) 
      		return true; 
        else 
			retorno = validateRequired(form) && validateCaracterEspecial(form) && validateDecimal(form) && validateLong(form);
		
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
		}else {
			return retorno;
		}
		//**************************************************************
   	}
   	
   	function caracteresespeciais () {
		this.aa = new Array("intervaloQuantidadeDocumentosInicial", "Intervalo de Quantidade de Documentos Inicial deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
		this.ab = new Array("intervaloQuantidadeDocumentosFinal", "Intervalo de Quantidade de Documentos Final deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
    }
    
	function required () { 
		this.aa = new Array("valorMinimoDebito", "Informe o Valor Mínimo do Débito.", new Function ("varName", " return this[varName];"));
	} 
    
	function FloatValidations () {
		this.aa = new Array("valorMinimoDebito", "Valor Mínimo do Débito deve somente conter números decimais positivos.",new Function ("varName", " return this[varName];"));
	} 
	
	function IntegerValidations () {
		this.aa = new Array("intervaloQuantidadeDocumentosInicial", "Intervalo de Quantidade de Documentos Inicial deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
		this.ab = new Array("intervaloQuantidadeDocumentosFinal", "Intervalo de Quantidade de Documentos Final deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
    }
	
	function carregaIntervaloQuantidadeDocumentosFinal(){
		var form = document.ImovelCurvaAbcDebitosActionForm;
		form.intervaloQuantidadeDocumentosFinal.value = form.intervaloQuantidadeDocumentosInicial.value;
	}
	
	function controleIntervaloQuantidadeDocumentos(){
		var form = document.ImovelCurvaAbcDebitosActionForm;

		if(parseInt(form.intervaloQuantidadeDocumentosInicial.value) > 
		   parseInt(form.intervaloQuantidadeDocumentosFinal.value)) {

			alert("Intervalo de Quantidade de Documentos Inicial é maior que o Intervalo Final.");
			//form.intervaloQuantidadeDocumentosFinal.value = "";
			form.intervaloQuantidadeDocumentosFinal.focus();
			return false;
		}
		
		return true;
	}

	function limparForm(){
		var form = document.forms[0];
	}
 
-->    
</script>

</head>
<body leftmargin="5" topmargin="5" onload="//setarFoco('${requestScope.nomeCampo}');">

<div id="formDiv">
<html:form action="/filtrarImovelCurvaAbcDebitosWizardAction"
	type="gcom.gui.cadastro.imovel.ImovelCurvaAbcDebitosActionForm"
	onsubmit="validateImovelCurvaAbcDebitosActionForm(this);"
	name="ImovelCurvaAbcDebitosActionForm" method="post">

	<jsp:include page="/jsp/util/wizard/navegacao_abas_wizard_valida_avancar_valida_voltar.jsp?numeroPagina=5" />

	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>

	<table width="770" border="0" cellspacing="5" cellpadding="0">
		<input type="hidden" name="numeroPagina" value="1" />
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
						<td class="parabg">Filtrar Im&oacute;vel</td>
						<td width="11"><img border="0"
							src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
					</tr>
				</table>
				<p>&nbsp;</p>
			
				<table width="100%" border="0">
					<tr>
						<td colspan="2">Para filtrar o(s) im&oacute;vel(is) de acordo com o d&eacute;bito existente,
										informe os dados abaixo:
						</td>
					</tr>
					<tr>
						<td width="220"><strong>Valor m&iacute;nimo do d&eacute;bito:&nbsp;<font color="#FF0000">*</font></strong></td>
						<td align="left">
							<html:text property="valorMinimoDebito" size="12" maxlength="12"
								onkeyup="formataValorMonetario(this, 12)" onkeypress="return isCampoNumerico(event);" style="text-align:right;" />
						</td>
					</tr>
					<tr><td colspan="2"><hr></td></tr>
					<tr><td colspan="2"><strong>Intervalo de Quantidade de Documentos</strong></td></tr>
					<tr>
						<td width="220"><strong>Inicial:</strong></td>
						<td align="left">
							<html:text property="intervaloQuantidadeDocumentosInicial" size="5" maxlength="4"
								onkeyup="carregaIntervaloQuantidadeDocumentosFinal();" disabled="true"></html:text>
						</td>
					</tr>
					<tr>
						<td width="220"><strong>Final:</strong></td>
						<td align="left">
							<html:text property="intervaloQuantidadeDocumentosFinal" size="5" maxlength="4"
								onblur="controleIntervaloQuantidadeDocumentos();" disabled="true"></html:text>
						</td>
					</tr>
					<tr><td colspan="2"><hr></td></tr>
					<tr>
						<td width="220"><strong>Considerar Pagamentos:</strong></td>
						<td align="left">
							<table width="40%" border="0">
								<tr>
									<logic:present name="indicadorPagamentosNaoClassificados">
										<td><html:radio value="1" property="indicadorPagamentosNaoClassificados"></html:radio>&nbsp;Sim</td>
										<td><html:radio value="2" property="indicadorPagamentosNaoClassificados"></html:radio>&nbsp;N&atilde;o</td>
									</logic:present>
									<logic:notPresent name="indicadorPagamentosNaoClassificados">
										<td><input type="radio" name="indicadorPagamentosNaoClassificados" value="1" checked="checked">&nbsp;Sim</td>
										<td><input type="radio" name="indicadorPagamentosNaoClassificados" value="2">&nbsp;N&atilde;o</td>
									</logic:notPresent>
								</tr>
							</table>
						</td>
					</tr>
					
					<tr>
						<td colspan="2" valign="bottom" height="100"><font color="#FF0000">*</font> Campo obrigatório.</td>
					</tr>
					<tr>
						<td colspan="2">
							<div align="right">
								<jsp:include page="/jsp/util/wizard/navegacao_botoes_wizard_valida_avancar_valida_voltar.jsp?numeroPagina=5" />
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

<logic:present name="msgValidacao">
	<script>
		alert('${requestScope.msgValidacao}');
	</script>
</logic:present>

</body>

<%@ include file="/jsp/util/telaespera.jsp"%>

</html:html>