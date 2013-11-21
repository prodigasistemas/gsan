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
	var bCancel = false; 
	
    function validateImovelCurvaAbcDebitosActionForm(form) {
        if (bCancel) 
      		return true; 
        else 
			if ( (validateCaracterEspecial(form)) && (validateMesAno(form)) ) {
				if ( (form.referenciaCobrancaInicial.value == '') || (form.referenciaCobrancaInicial.length == 0) ) {
					alert('Informe a Referência da Cobrança Inicial.');
					form.referenciaCobrancaInicial.focus();
					return;
				}
				
				if ( (form.referenciaCobrancaFinal.value == '') || (form.referenciaCobrancaFinal.length == 0) ) {
					alert('Informe a Referência da Cobrança Final.');
					form.referenciaCobrancaFinal.focus();
					return;
				}
				
				var dataInicial = form.referenciaCobrancaInicial.value.replace("/", "");
				var dataFinal   = form.referenciaCobrancaFinal.value.replace("/", "");
				
				dataInicial = dataInicial.substr(2, 4) + dataInicial.substr(0, 2);
				dataFinal   = dataFinal.substr(2, 4) + dataFinal.substr(0, 2);
				
				if ( parseInt(dataInicial) > parseInt(dataFinal) ) {
					alert('A Referência Inicial da Cobrança deve ser Menor que a Final.');
					form.referenciaCobrancaInicial.focus();
					return;
				}
				
				//**************************************************************
				// Autor: Ivan Sergio
				// Data: 08/05/2009
				// CRC1491
				// Verifica se o usuario clicou no Concluir para mostrar a tela
				// de Espera. Esta verificacao deve ser feita por conta do Wizard.
				//**************************************************************
				var action = form.action;
				if (action.indexOf("concluir=true") > 0) {
					submitForm(form);
				} else {
					return true;
				}
				//**************************************************************
	       	}else {
	       		return;
	       	}
   	} 

    function caracteresespeciais () { 
	     this.aa = new Array("referenciaCobrancaInicial", "Referência Inicial da Cobrança deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
	     this.ab = new Array("referenciaCobrancaFinal", "Referência Final da Cobrança deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
    }
    
	function MesAnoValidations () { 
		this.aa = new Array("referenciaCobrancaInicial", "Referência Inicial da Cobrança(Mês/Ano) inválido. ", new Function ("varName", " return this[varName];"));
		this.ab = new Array("referenciaCobrancaFinal", "Referência Final da Cobrança(Mês/Ano) inválido. ", new Function ("varName", " return this[varName];"));
	}
	
	function limparForm(){
		var form = document.forms[0];
	}
 
	function validarBairro(){
	 	var form = document.forms[0];
 	 	if (form.idMunicipio.value.length < 1) {
			form.idBairro.value = "";
 			alert('Informe Município');
	 	}
	}
	
	function escondeAbaLocalidade() {
		document.getElementById('2').style.display = 'none';
	}
	
	function mostraAbaLocalidade() {
		document.getElementById('2').style.display = '';
	}
	
	function duplicarReferenciaCobranca(){
		var formulario = document.forms[0]; 
		formulario.referenciaCobrancaFinal.value = formulario.referenciaCobrancaInicial.value;
	}
   
</script>

</head>
<body leftmargin="5" topmargin="5" onload="//setarFoco('${requestScope.nomeCampo}');">

<div id="formDiv">
<html:form action="/filtrarImovelCurvaAbcDebitosWizardAction"
	type="gcom.gui.cadastro.imovel.ImovelCurvaAbcDebitosActionForm"
	onsubmit="validateImovelCurvaAbcDebitosActionForm(this);"
	name="ImovelCurvaAbcDebitosActionForm" method="post">

	<jsp:include page="/jsp/util/wizard/navegacao_abas_wizard_valida_avancar_valida_voltar.jsp?numeroPagina=1" />

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
						<td width="220"><strong>Classifica&ccedil;&atilde;o do Relat&oacute;rio:</strong></td>
						<td align="left">
							<table width="100%" border="0">
								<tr>
									<logic:present name="classificacao">
										<td width="20%"><html:radio value="ESTADO" property="classificacao" onclick="escondeAbaLocalidade();"></html:radio>&nbsp;</td>
										<td width="20%"><html:radio value="REGIONAL" property="classificacao" onclick="mostraAbaLocalidade();"></html:radio>&nbsp;</td>
										<td width="20%"><html:radio value="LOCAL" property="classificacao" onclick="mostraAbaLocalidade();"></html:radio>&nbsp;</td>
										<td width="20%"><html:radio value="SETORCOMERCIAL" property="classificacao" onclick="mostraAbaLocalidade();"></html:radio>&nbsp;</td>
										<td width="20%"><html:radio value="MUNICIPIO" property="classificacao" onclick="mostraAbaLocalidade();"></html:radio>&nbsp;</td>
									</logic:present>
									<logic:notPresent name="classificacao">
										<td width="20%"><input type="radio" name="classificacao" value="ESTADO" checked="checked" onclick="escondeAbaLocalidade();"></td>
										<td width="20%"><input type="radio" name="classificacao" value="REGIONAL" onclick="mostraAbaLocalidade();"></td>
										<td width="20%"><input type="radio" name="classificacao" value="LOCAL" onclick="mostraAbaLocalidade();"></td>
										<td width="20%"><input type="radio" name="classificacao" value="SETORCOMERCIAL" onclick="mostraAbaLocalidade();"></td>
										<td width="20%"><input type="radio" name="classificacao" value="MUNICIPIO" onclick="mostraAbaLocalidade();"></td>
									</logic:notPresent>
								</tr>
								
								<tr>
									<td>Estado</td>
									<td>Regional</td>
									<td>Local</td>
									<td>Setor Comercial</td>
									<td>Município</td>
								</tr>
							</table>
						</td>
					</tr>
					<tr><td colspan="2"><hr></td></tr>
					<tr>
						<td width="220"><strong>Refer&ecirc;cia da Cobran&ccedil;a Inicial:&nbsp;<font color="#FF0000">*</font></strong></td>
						<td align="left"><html:text property="referenciaCobrancaInicial" size="9" maxlength="7" onkeyup="mascaraAnoMes(this, event);" onkeypress="return isCampoNumerico(event);" onblur="javascript:duplicarReferenciaCobranca();"></html:text> <strong>MM/AAAA</strong></td>
					</tr>
					<tr>
						<td width="220"><strong>Refer&ecirc;cia da Cobran&ccedil;a Final:&nbsp;<font color="#FF0000">*</font></strong></td>
						<td align="left"><html:text property="referenciaCobrancaFinal" size="9" maxlength="7" onkeyup="mascaraAnoMes(this, event);" onkeypress="return isCampoNumerico(event);"></html:text> <strong>MM/AAAA</strong></td>
					</tr>
					<tr><td colspan="2"><hr></td></tr>
					<tr>
						<td width="220"><strong>Im&oacute;veis com medi&ccedil;&atilde;o individualizada:</strong></td>
						<td align="left">
							<table width="40%" border="0">
								<tr>
									<logic:present name="indicadorImovelMedicaoIndividualizada">
										<td><html:radio value="1" property="indicadorImovelMedicaoIndividualizada"></html:radio>&nbsp;Sim</td>
										<td><html:radio value="2" property="indicadorImovelMedicaoIndividualizada"></html:radio>&nbsp;N&atilde;o</td>
									</logic:present>
									<logic:notPresent name="indicadorImovelMedicaoIndividualizada">
										<td><input type="radio" name="indicadorImovelMedicaoIndividualizada" value="1">&nbsp;Sim</td>
										<td><input type="radio" name="indicadorImovelMedicaoIndividualizada" value="2" checked="checked">&nbsp;N&atilde;o</td>
									</logic:notPresent>
								</tr>
							</table>
						</td>
					</tr>
					<tr>
						<td width="220"><strong>Im&oacute;veis com paraliza&ccedil;&atilde;o de faturamento/cobran&ccedil;a:</strong></td>
						<td align="left">
							<table width="40%" border="0">
								<tr>
									<logic:present name="indicadorImovelParalizacaoFaturamentoCobranca">
										<td><html:radio value="1" property="indicadorImovelParalizacaoFaturamentoCobranca"></html:radio>&nbsp;Sim</td>
										<td><html:radio value="2" property="indicadorImovelParalizacaoFaturamentoCobranca"></html:radio>&nbsp;N&atilde;o</td>
									</logic:present>
									<logic:notPresent name="indicadorImovelParalizacaoFaturamentoCobranca">
										<td><input type="radio" name="indicadorImovelParalizacaoFaturamentoCobranca" value="1">&nbsp;Sim</td>
										<td><input type="radio" name="indicadorImovelParalizacaoFaturamentoCobranca" value="2" checked="checked">&nbsp;N&atilde;o</td>
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