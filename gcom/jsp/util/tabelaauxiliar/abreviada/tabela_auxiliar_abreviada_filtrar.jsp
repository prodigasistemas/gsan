<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/menus-taglib.tld" prefix="menu"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%@ page import="gcom.util.Util"%>
<%@ page import="gcom.util.ConstantesSistema"%>

<html:html>
<head>

<title><bean:write name="nomeSistema" scope="session" /></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
</head>


<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<script language="JavaScript">
	function limpar(){
		var form = document.forms[0];
		form.id.value = '';
		form.descricao.value = '';
		form.descricaoAbreviada.value = '';
	}

	function validarForm() {
		var form = document.forms[0];
		if(validateInteger(form) && validateCaracterEspecial(form)){
			submeterFormPadrao(form);
		}
	}
	
	function IntegerValidations () {
		this.aa = new Array("id", "O campo Código deve conter apenas números.", new Function ("varName", " return this[varName];"));
	}
	
	function caracteresespeciais () {
		this.aa = new Array("descricao", "Descrição possui caracteres especiais.", new Function ("varName", " return this[varName];"));
		this.ab = new Array("descricaoAbreviada", "Descrição Abreviada possui caracteres especiais.", new Function ("varName", " return this[varName];"));
	}
	
</script>


<body leftmargin="5" topmargin="5">
<html:form
	action="/filtrarTabelaAuxiliarAbreviadaAction?tela=${requestScope.tela}"
	method="post">

	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>

	<table width="770" border="0" cellspacing="5" cellpadding="0">
		<tr>
			<td width="150" valign="top" class="leftcoltext">
			<div align="center"><%@ include
				file="/jsp/util/informacoes_usuario.jsp"%></div>
			</td>
			<td width="625" valign="top" class="centercoltext">
			<table>
				<tr>
					<td></td>
				</tr>
			</table>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img
						src="<bean:message key="caminho.imagens"/>parahead_left.gif"
						border="0" /></td>
					<td class="parabg">Filtrar <bean:write name="titulo"
						scope="session" /></td>

					<td width="11"><img
						src="<bean:message key="caminho.imagens"/>parahead_right.gif"
						border="0" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>
					<td width="80%">Preencha os campos para pesquisar um(a) <%=((String) session.getAttribute("titulo")).toLowerCase()%>:</td>
					<td width="20%" align="right"><input type="checkbox"
						name="atualizar" value="1" checked /><strong>Atualizar</strong></td>
				</tr>
			</table>
			<table width="100%" border="0">
				<tr>
					<td><strong>C&oacute;digo:</strong></td>
					<td><html:text property="id" maxlength="3" size="3" onkeyup="somente_numero(this);" /><font
						size="1"> &nbsp;(Somente números)</font> <br>
					<font color="red"><html:errors property="id" /></font></td>
				</tr>
				<tr>
					<td width="19%"><strong><bean:write name="descricao"
						scope="session" />:</strong></td>
					<td width="81%"><input type"text" name="descricao"
						maxlength="<bean:write name="tamMaxCampoDescricao" scope="session"/>"
						size="<bean:write name="tamMaxCampoDescricao" scope="session"/>" />
					<br>
					<font color="red"><html:errors property="descricao" /></font></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td><input type="radio"
						onclick="javascript:document.forms[0].acertar.value = 1;" checked="checked"
						name="tipoPesquisa" value="1">Iniciando pelo texto <input type="radio"
						onclick="javascript:document.forms[0].acertar.value = 2;"
						name="tipoPesquisa" value="2">  Contendo o texto</td>
						
						
				</tr>
				<tr>
					<td width="19%"><strong><bean:write name="descricaoAbreviada"
						scope="session" />:</strong></td>
					<td width="81%"><input type"text" name="descricaoAbreviada"
						maxlength="<bean:write name="tamMaxCampoDescricaoAbreviada" scope="session"/>"
						size="<bean:write name="tamMaxCampoDescricaoAbreviada" scope="session"/>" />
					<br>
					<font color="red"><html:errors property="descricaoAbreviada" /></font>
					</td>
				</tr>
				<tr>
					<td><strong>Indicador de uso:</strong></td>
					<td><html:radio property="indicadorUso" tabindex="2" value="1" /><strong>Ativo</strong>
					<html:radio property="indicadorUso" tabindex="3" value="2" /><strong>Inativo</strong>
					<html:radio property="indicadorUso" tabindex="4" value="" /><strong>Todos</strong>
					</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td height="0"><input name="Button" type="button"
						class="bottonRightCol" value="Limpar" align="left"
						onclick="javascript:limpar();"></td>
					<td align="right"><input name="Button2" type="button"
						class="bottonRightCol" value="Filtrar" align="right"
						onClick="javascript:validarForm();" /></td>

				</tr>

				<tr>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
				</tr>

			</table>
			<p>&nbsp;</p>
	</table>

	<%@ include file="/jsp/util/rodape.jsp"%>
</body>
</html:form>
</html:html>
