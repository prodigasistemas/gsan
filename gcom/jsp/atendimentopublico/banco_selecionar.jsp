<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>
<%@ page import="gcom.cobranca.bean.ContaValoresHelper"%>
<%@ page import="gcom.faturamento.conta.Conta"%>
<%@ page import="gcom.util.ConstantesSistema"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

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
	formName="EmitirSegundaViaContaInternetActionForm"
	dynamicJavascript="true" />



<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script>
<!--
var bCancel = false;

function limparMatricula(form){
	form.matricula.value = '';
}

function validarForm(form){

	if(testarCampoValorZero(document.EmitirSegundaViaContaInternetActionForm.matricula, 'Matrícula')) {

		if(validateEmitirSegundaViaContaInternetActionForm(form)){
    		
    		form.submit();
		}
	}
}
-->
</script>
</head>

<body leftmargin="5" topmargin="5" onload="resizePageSemLink(785, 350);">

<html:form action="/emitirSegundaViaContaInternetAction" method="post"
	onsubmit="return validateEmitirSegundaViaContaInternetActionForm(this);">

	<table width="770" border="0" cellspacing="5" cellpadding="0">
		<tr>
			<td width="655" valign="top" class="centercoltext">
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
					<td class="parabg"><font size="2"><strong>Pagamento Online</strong></font></td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>

			<table width="100%" height="34" border="0" align="center"
				cellpadding="0" cellspacing="0">


				<tr>
					<td colspan="5"><br>
					<br>
					<strong>Selecione o Banco de sua preferência abaixo:</strong></td>
				</tr>

				<tr>
					<td>&nbsp;</td>
				</tr>

			</table>

			<table width="100%" height="34" border="0" align="center"
				cellpadding="0" cellspacing="0">
				<tr>

					<td width="20%" align="center">
					
						<logic:equal name="fichaCompensacao" value="1">
						
							<a href="javascript:
										window.open('http://www.bb.com.br','mywindow','width=700,height=700,toolbar=yes,
										location=yes,directories=yes,status=yes,menubar=yes,scrollbars=yes,copyhistory=yes,
										resizable=yes');window.close();">
							<img align="center" border="0"
								src="<bean:message key="caminho.imagens"/>logobrasil.png"
								title="Banco do Brasil" /></a>
								
						</logic:equal>
	
						<logic:notEqual name="fichaCompensacao" value="1">
							
							<a href="javascript:abrirPopup('enviarDadosBancosAction.do?banco=BancoBrasil', 800, 600);">
							<img align="center" border="0"
								src="<bean:message key="caminho.imagens"/>logobrasil.png"
								title="Banco do Brasil" /></a>
							
						</logic:notEqual>
					</td>

					<td width="20%" align="center"><a
						href="javascript:
						window.open('http://www.caixa.gov.br','mywindow','width=700,height=700,toolbar=yes,
						location=yes,directories=yes,status=yes,menubar=yes,scrollbars=yes,copyhistory=yes,
						resizable=yes');window.close();"><!-- <a
						href="javascript:abrirPopup('enviarDadosBancosAction.do?banco=Caixa', 400, 800);"> --><img
						align="center" border="0"
						src="<bean:message key="caminho.imagens"/>logocaixa.png"
						title="Caixa Econômica Federal" /></a></td>

					<td width="20%" align="center"><a
						href="javascript:
						window.open('http://www.bradesco.com.br','mywindow','width=700,height=700,toolbar=yes,
						location=yes,directories=yes,status=yes,menubar=yes,scrollbars=yes,copyhistory=yes,
						resizable=yes');window.close();"><img
						align="center" border="0"
						src="<bean:message key="caminho.imagens"/>logobradesco.png"
						title="Bradesco"></a></td>

					<!--  <td width="20%" align="center"><!-- <a
						href="javascript:abrirPopup('enviarDadosBancosAction.do?banco=Itau', 400, 800);"><a
						href="javascript:
						window.open('http://www.itau.com.br','mywindow','width=700,height=700,toolbar=yes,
						location=yes,directories=yes,status=yes,menubar=yes,scrollbars=yes,copyhistory=yes,
						resizable=yes');window.close();"><img
						align="center" border="0"
						src="<bean:message key="caminho.imagens"/>logoitau.png"
						title="Banco Itaú" /></a></td>-->

					<td width="20%" align="center"><a
						href="javascript:
						window.open('http://www.santander.com.br','mywindow','width=700,height=700,toolbar=yes,
						location=yes,directories=yes,status=yes,menubar=yes,scrollbars=yes,copyhistory=yes,
						resizable=yes');window.close();"><!-- <a
						href="javascript:abrirPopup('enviarDadosBancosAction.do?banco=BancoReal', 400, 800);"> --><img
						align="center" border="0"
						src="<bean:message key="caminho.imagens"/>logo_santander.gif"
						title="Santander" /></a></td>

				</tr>

				<tr>
					<td>&nbsp;</td>

				</tr>


				<tr>
					<td><input name="Button" type="button" class="bottonRightCol"
						value="Fechar" align="left" onclick="javascript:window.close();"></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
				</tr>
			</table>
	</table>
</html:form>
<%@ include file="/jsp/util/rodape.jsp"%>
</body>
</html:html>
