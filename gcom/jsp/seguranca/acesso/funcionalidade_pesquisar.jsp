<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<%@page import="gcom.util.ConstantesSistema"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>

<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="PesquisarFuncionalidadeActionForm" />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript">

function validarForm(form){

	if(testarCampoValorZero(document.PesquisarFuncionalidadeActionForm.codigo, 'Código') && 
	testarCampoValorZero(document.PesquisarFuncionalidadeActionForm.descricao, 'Descrição') && 
	testarCampoValorZero(document.PesquisarFuncionalidadeActionForm.modulo, 'Módulo')) {

		if(validatePesquisarFuncionalidadeActionForm(form)){
    		submeterFormPadrao(form);
		}
	}

function limparForm(form) {
	
		form.codigo.value = "";
		form.descricao.value = "";
		form.modulo.value = "-1"; 
		form.indicadorPontoEntrada.value = "";
		
	
	}




}

</script>


</head>

<body leftmargin="5" topmargin="5" onload="resizePageSemLink(605, 365);" >
<html:form action="/pesquisarFuncionalidadeAction" method="post"
	onsubmit="return validatePesquisarFuncionalidadeActionForm(this);">
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
					<td class="parabg">Pesquisar Funcionalidade</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>
					<td colspan="2">Preencha os campos para pesquisar uma
					funcionalidade:</td>
				</tr>

				<tr>
					<td><strong>Código:</strong></td>
					<td><strong> <html:text property="codigo" size="10" maxlength="9" />
					</strong></td>
				</tr>

				<tr>
					<td width="162"><strong>Descri&ccedil;&atilde;o:</strong></td>

					<td><strong> <html:text property="descricao" size="60"
						maxlength="60" /> </strong></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td><html:radio property="tipoPesquisa" tabindex="5"
						value="<%=ConstantesSistema.TIPO_PESQUISA_INICIAL.toString()%>" />
					Iniciando pelo texto <html:radio property="tipoPesquisa"
						tabindex="6"
						value="<%=ConstantesSistema.TIPO_PESQUISA_COMPLETA.toString()%>" />
					Contendo o texto</td>
					<td>&nbsp;</td>
				</tr>
				

				<tr>
					<td><strong>Módulo:</strong></td>
					<td><html:select property="modulo">
						<html:option value="-1">&nbsp;</html:option>
						<html:options collection="colecaoModulo"
							labelProperty="descricaoModulo" property="id" />
					</html:select> <font size="1">&nbsp; </font></td>
				</tr>

				<tr>
					<td><strong>Ponto de Entrada:</strong></td>
					<td><strong> <html:radio property="indicadorPontoEntrada" value="1" />
					<strong>Sim <html:radio property="indicadorPontoEntrada" value="2" />
					N&atilde;o</strong> </strong></td>

				</tr>

				<tr>
					<td colspan="2">
					
						<logic:present	name="caminhoRetornoTelaPesquisaFuncionalidade">
						<INPUT TYPE="button" class="bottonRightCol" value="Voltar"
						onclick="redirecionarSubmit('${caminhoRetornoTelaPesquisaFuncionalidade}.do')" />
						</logic:present>
						<logic:notPresent	name="caminhoRetornoTelaPesquisaFuncionalidade">
						<INPUT TYPE="button" class="bottonRightCol" value="Fechar"
						onclick="window.close();" />
						</logic:notPresent>
						
						<input name="Button" type="button"
						class="bottonRightCol" value="Limpar" align="left"
						onclick="window.location.href='/gsan/exibirPesquisarFuncionalidadeAction.do?menu=sim'"></td>
						
					<td align="right" height="24"><input type="button" name="Button" class="bottonRightCol"
						value="Pesquisar" onClick="javascript:validarForm(document.forms[0])" /></td>
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

