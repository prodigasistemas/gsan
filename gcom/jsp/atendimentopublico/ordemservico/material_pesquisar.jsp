<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>

<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="PesquisarMaterialActionForm" />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript">

function validarForm(form){

	if(testarCampoValorZero(document.PesquisarMaterialActionForm.descricao, 'Descrição') && 
	testarCampoValorZero(document.PesquisarMaterialActionForm.descricaoAbreviada, 'Descricao Abreviada') && 
	testarCampoValorZero(document.PesquisarMaterialActionForm.unidadeMaterial, 'Unidade do Material')) {

		if(validatePesquisarMaterialActionForm(form)){
    		submeterFormPadrao(form);
		}
	}
}

function limparForm(form) {
	
		form.descricaoAbreviada.value = "";
		form.descricao.value = "";
		form.unidadeMaterial.value = "-1"; 
		
	
	}



</script>


</head>

<body leftmargin="5" topmargin="5" onload="resizePageSemLink(600, 390);">
<html:form action="/pesquisarMaterialAction" method="post"
	onsubmit="return validatePesquisarMaterialActionForm(this);">
	<table width="452" border="0" cellspacing="5" cellpadding="0">
		<tr>
			<td width="452" valign="top" class="centercoltext">
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
					<td class="parabg">Pesquisar Material</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>
					<td colspan="2">Preencha o campo para pesquisar um material:</td>
				</tr>


				<tr>
					<td width="162"><strong>Descri&ccedil;&atilde;o:</strong></td>

					<td><strong> <html:text property="descricao" size="60"
						maxlength="60" /> </strong></td>
				</tr>

				<tr>
					<td><strong>Descrição Abreviada:</strong></td>
					<td><strong> <html:text property="descricaoAbreviada" size="8"
						maxlength="8" /> </strong></td>
				</tr>


				<tr>
					<td><strong>Unidade do Material:</strong></td>
					<td><html:select property="unidadeMaterial">
						<html:option value="-1">&nbsp;</html:option>
						<html:options collection="colecaoUnidadeMaterial"
							labelProperty="descricao" property="id" />
					</html:select> <font size="1">&nbsp; </font></td>
				</tr>



				<tr>
					<td colspan="2">
					<input name="Button" type="button" class="bottonRightCol"
						value="Limpar" align="left"
						onclick="limparForm(document.forms[0]);">
					<logic:present name="caminhoRetornoTelaPesquisaMaterial">
	          			<INPUT TYPE="button" class="bottonRightCol" value="Voltar" onclick="redirecionarSubmit('${caminhoRetornoTelaPesquisaMaterial}.do')"/>
	          		</logic:present>
					</td>
					<td align="right" height="24"><input type="button" name="Button"
						class="bottonRightCol" value="Pesquisar"
						onClick="javascript:validarForm(document.forms[0])" /></td>
					<td>&nbsp;</td>
				</tr>

			</table>
			</td>
		</tr>



	</table>
	<p>&nbsp;</p>


</html:form>
</body>
</html:html>

