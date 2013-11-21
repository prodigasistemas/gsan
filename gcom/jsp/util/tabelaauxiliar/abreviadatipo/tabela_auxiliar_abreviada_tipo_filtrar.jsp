<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/menus-taglib.tld" prefix="menu"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>

<head>

<title><bean:write name="nomeSistema" scope="session" /></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">

<%@ page import="gcom.util.ConstantesSistema"%>
</head>

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<script language="JavaScript">


	function validarForm() {
		var form = document.forms[0];
		if(validateInteger(form)){
			submeterFormPadrao(form);
		}
	}
	
	
	function IntegerValidations () {
		this.aa = new Array("id", "O campo Código deve conter apenas números.", new Function ("varName", " return this[varName];"));
	}
	
		function limpar(){
		var form = document.forms[0];
		form.id.value = '';
		form.descricao.value = '';
		form.descricaoAbreviada.value = '';
		form.tipo.value = '';
	}
		
	function verificarChecado(valor){
		form = document.forms[0];
		if(valor == "1"){
		 	form.indicadorAtualizar.checked = true;
		 }else{
		 	form.indicadorAtualizar.checked = false;
		}
	}
	
	alert(  )

	
</script>

<body leftmargin="5" topmargin="5">

<html:form
	action="/filtrarTabelaAuxiliarAbreviadaTipoAction?tela=${sessionScope.tela}"
	method="post">

	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>

	<table width="770" border="0" cellspacing="5" cellpadding="0">
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
			<td width="625" valign="top" class="centercoltext">
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_left.gif"
						editor="Webstyle4" /></td>
					<td class="parabg">Filtrar <bean:write name="titulo"
						scope="session" /></td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif"
						editor="Webstyle4" /></td>
				</tr>
				<p>&nbsp;</p>
				<table width="100%" border="0">
					<tr>
						<td colspan="2">Preencha os campos para pesquisar um(a) <%=((String) session.getAttribute("titulo")).toLowerCase()%>:</td>
						<td width="100" align="left" colspan="2"><input type="checkbox"
							name="atualizar" value="1" checked /><strong>Atualizar</strong></td>
					</tr>
					<tr>
						<td><strong>C&oacute;digo:</strong></td>
						<td><html:text property="id" maxlength="2" size="2" /><font
							size="1">&nbsp;(somente números)</font> <br>
						<font color="red"><html:errors property="id" /></font></td>
					</tr>
					<tr>
						<td><strong>Descri&ccedil;&atilde;o:</strong></td>
						<td><html:text property="descricao" maxlength="20" size="20" /> <br>
						<font color="red"><html:errors property="descricao" /></font></td>
					</tr>
				<tr>
					<td>&nbsp;</td>
					<td>					   										
					   <html:radio property="tipoPesquisa"	tabindex="3"
						value="<%=ConstantesSistema.TIPO_PESQUISA_INICIAL.toString()%>"/>
						Iniciando pelo texto
						<html:radio	property="tipoPesquisa" tabindex="4"
							value="<%=ConstantesSistema.TIPO_PESQUISA_COMPLETA.toString()%>"/>
						Contendo o texto
					</td>
				</tr>	
					<tr>
						<td width="19%"><strong><bean:write name="descricaoAbreviada"
							scope="session" />:</strong></td>
						<td width="65%"><input type"text" name="descricaoAbreviada"
							maxlength="6" size="6" />
						<br>
						<font color="red"><html:errors property="descricaoAbreviada" /></font>
						</td>
					</tr>
					<tr>
						<td height="24"><strong>Sistema de Abastecimento<span
							class="style1">: </span></strong></td>
						<td><html:select property="tipo" style="width: 230px;">
							<html:option
								value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;
							</html:option>
							<html:options collection="colecaoObject"
								labelProperty="descricao" property="id" />
						</html:select></td>
					</tr>
					<tr>
						<td><strong>Indicador de uso:</strong></td>
						<td><html:radio property="indicadorUso" tabindex="2" value="1" /><strong>Ativo</strong>
						<html:radio property="indicadorUso" tabindex="3" value="2" /><strong>Inativo</strong>
						<html:radio property="indicadorUso" tabindex="4" value="" /><strong>Todos</strong>
						</td>
						<td>&nbsp;</td>
					</tr>
					<tr>
						<td height="24">&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
					<tr>
						<td height="0"><input name="Button" type="button"
							class="bottonRightCol" value="Limpar" align="left"
							onclick="javascript:window.location.href='/gsan/exibirFiltrarTabelaAuxiliarAbreviadaTipoAction.do?tela=${sessionScope.tela}&menu=sim'"></td>
						<td>&nbsp;</td>
						<td height="0" widht="100" align="right"><input name="Button2" type="button"
						class="bottonRightCol" value="Filtrar" align="right"
						onClick="javascript:validarForm();"/></td>
						<td>
						<div align="right"></div>
						</td>
					</tr>
				</table>
			</table>
			</td>
		</tr>
	</table>

	<%@ include file="/jsp/util/rodape.jsp"%>
</html:form>
</body>
</html:html>
