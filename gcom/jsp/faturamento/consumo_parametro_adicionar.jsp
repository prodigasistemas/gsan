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

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false"
	formName="AdicionarConsumoParametroActionForm" />

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>



<script language="JavaScript">
	function validarFormAdicionar(form) {
		if(testarCampoValorZero(document.AdicionarConsumoParametroActionForm.numeroParametro, 'Parâmetro máximo')			&& testarCampoValorZero(document.AdicionarConsumoParametroActionForm.numeroConsumo, 'Número do Consumo')) {
						if(validateAdicionarConsumoParametroActionForm(form)){
				form.action = 'adicionarConsumoParametroAction.do?adicionar=sim';
	        	form.submit()			}		}	}
	
	function validarFormAtualizar(form) {
		if(testarCampoValorZero(document.AdicionarConsumoParametroActionForm.numeroParametro, 'Parâmetro máximo')
			&& testarCampoValorZero(document.AdicionarConsumoParametroActionForm.numeroConsumo, 'Número do Consumo')) {

			if(validateAdicionarConsumoParametroActionForm(form)){
				form.action = 'adicionarConsumoParametroAction.do?atualizar=sim';
	        	form.submit()
			}
		}
	}
		function chamarReload(){
		chamarSubmitComUrlSemUpperCase('/gsan/exibirInformarConsumoParametroAction.do');	}
</script>

</head>

<body>


<logic:present name="reload" scope="request">
	<body leftmargin="5" topmargin="5"
		onload="resizePageSemLink(400, 400);chamarReload();window.close();">
</logic:present>

<logic:notPresent name="reload">
	<body leftmargin="5" topmargin="5"
		onload="resizePageSemLink(600, 350);javascript:setarFoco('${requestScope.nomeCampo}');">
</logic:notPresent>


<html:form action="/adicionarConsumoParametroAction"
	name="AdicionarConsumoParametroActionForm"
	type="gcom.gui.faturamento.AdicionarConsumoParametroActionForm"
	method="post">
	<table width="530" border="0" cellspacing="5" cellpadding="0">
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
					<td class="parabg">Adicionar Consumo e Parâmetro</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>
					<td colspan="4">Preencha os campos para inserir/atualizar Consumo e
					Parâmetro:</td>
				</tr>
				<tr>
					<td>
					<p>&nbsp;</p>
					</td>
				</tr>

				<tr>
					<td><strong>Mês/Ano:<font color="#FF0000">*</font></strong></td>
					<td colspan="2"><html:text property="mesAnoReferencia" size="7"
						maxlength="7" style="background-color:#EFEFEF; border:0;"
						readonly="true" />
				</tr>
				<tr>
					<td><strong>Categoria:<font color="#FF0000">*</font></strong></td>
					<td colspan="2"><html:text property="idCategoria" size="15"
						maxlength="15" style="background-color:#EFEFEF; border:0;"
						readonly="true" />
				</tr>
				<tr>
					<td><strong>Subcategoria:<font color="#FF0000">*</font></strong></td>
					<td colspan="2"><html:text property="idSubCategoria" size="45"
						maxlength="40" style="background-color:#EFEFEF; border:0;"
						readonly="true" />
				</tr>

				<tr>
					<td><strong>Parâmetro máximo:<font color="#FF0000">*</font></strong></td>
					<td colspan="2"><html:text property="numeroParametro" size="8"
						onkeyup="formataValorMonetario(this, 7)" maxlength="7"
						tabindex="1" />
				</tr>
				<tr>
					<td><strong>Número do Consumo:<font color="#FF0000">*</font></strong></td>
					<td colspan="2"><html:text property="numeroConsumo" size="8"
						maxlength="7" tabindex="2" />
				</tr>

				<tr>
					<td><strong> <font color="#FF0000"></font></strong></td>
					<td align="right">
					<div align="left"><strong><font color="#FF0000">*</font></strong>
					Campos obrigat&oacute;rios</div>
					</td>
				</tr>


				<tr>
					<td height="0">&nbsp;</td>
					<td colspan="3">&nbsp;</td>
				</tr>

				<tr>
					<td height="24" colspan="4">
					<div align="right"><logic:present name="adicionar" scope="request">

						<input type="button" name="Button" class="bottonRightCol"
							value="Informar" tabindex="3"
							onClick="validarFormAdicionar(document.forms[0]);" />
					</logic:present> <logic:notPresent name="adicionar" scope="request">
						<input type="button" name="Button" class="bottonRightCol"
							value="Informar" tabindex="3"
							onClick="validarFormAtualizar(document.forms[0]);" />
					</logic:notPresent> <input type="button" name="Button"
						class="bottonRightCol" value="Fechar" tabindex="4"
						onClick="window.close()" /></div>
					</td>
					<td colspan="3">&nbsp;</td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<p>&nbsp;</p>
	</table>
</html:form>
</html:html>
