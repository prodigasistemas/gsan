<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@page import="gcom.util.ConstantesSistema" isELIgnored="false"%>
<html:html>
<head>

<title>GSAN - Sistema Integrado de Gest&atilde;o de Servi&ccedil;os de
Saneamento</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false"
	formName="PesquisarGerenciaRegionalActionForm" />
<script>
	
	function validarForm(form){
		if (testarCampoValorZero(document.PesquisarGerenciaRegionalActionForm.id, 'Código')){
			if(validatePesquisarGerenciaRegionalActionForm(form)){
	    		form.submit();
			}
		}

	}
	
	function limparForm(){
	var form = document.forms[0];
	
	form.id.value = '';
	form.nome.value = '';
	form.nomeAbreviado.value = '';
	
	
}

</script>

</head>

<body leftmargin="5" topmargin="5"
	onload="resizePageSemLink(600, 365);setarFoco('${requestScope.nomeCampo}');">

<html:form action="/retornarGerenciaRegionalPesquisar"
	name="PesquisarGerenciaRegionalActionForm"
	type="gcom.gui.cadastro.localidade.PesquisarGerenciaRegionalActionForm"
	method="post"
	onsubmit="return validatePesquisarGerenciaRegionalActionForm(this);">

	<table width="550" border="0" cellspacing="5" cellpadding="0">
		<tr>
			<td width="520" valign="top" class="centercoltext">
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
					<td class="parabg">Pesquisar Gerência Regional</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>

			<p>&nbsp;</p>

			<table width="100%" border="0">
				<tr>
					<td colspan="2">Preencha os campos para pesquisar uma gerência
					regional:</td>
				</tr>
			</table>

			<table width="100%" border="0">
				<tr>
					<td><strong>Código:</strong></td>
					<td><html:text maxlength="9" property="id" size="9" tabindex="1" />
					</td>
				</tr>

				<tr>
					<td><strong>Nome:</strong></td>
					<td><html:text maxlength="40" property="nome" size="40"
						tabindex="2" /></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td><html:radio property="tipoPesquisa" tabindex="3"
						value="<%=ConstantesSistema.TIPO_PESQUISA_INICIAL.toString()%>" />
					Iniciando pelo texto <html:radio property="tipoPesquisa"
						tabindex="4"
						value="<%=ConstantesSistema.TIPO_PESQUISA_COMPLETA.toString()%>" />
					Contendo o texto</td>
				</tr>

				<tr>
					<td><strong>Nome Abreviado:</strong></td>
					<td><html:text maxlength="40" property="nomeAbreviado" size="40"
						tabindex="5" /></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td colspan="3">&nbsp;</td>
				</tr>
				<tr>
					<td><logic:present name="caminhoRetornoTelaPesquisaFuncionario">
						<input type="button" name="Button1" class="bottonRightCol"
							value="Voltar" onclick="history.back();">
					</logic:present> <input name="Button2" type="button"
						class="bottonRightCol" value="Limpar" align="left"
						onclick="javascript:limparForm();"/>
					</td>
					<td align="right"><input type="button" name="Button3"
						class="bottonRightCol" value="Pesquisar" tabindex="4"
						onClick="validarForm(document.PesquisarGerenciaRegionalActionForm)" />
					</td>
				</tr>
			</table>

			<p>&nbsp;</p>
			<p>&nbsp;</p>
	</table>
</html:form>
</body>
</html:html>
