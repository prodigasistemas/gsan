<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

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
	formName="InserirAlteracaoTipoActionForm" />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>

<script language="JavaScript">

	function validarForm(){
		var form = document.forms[0];
		if (validateInserirAlteracaoTipoActionForm(form)){
      		submeterFormPadrao(form);
		}
    }

	

</script>

</head>

<body leftmargin="5" topmargin="5" onload="setarFoco('descricao');">
<html:form action="/inserirAlteracaoTipoAction.do"
	name="InserirAlteracaoTipoActionForm"
	type="gcom.gui.seguranca.acesso.InserirAlteracaoTipoActionForm"
	method="post"
	onsubmit="return validateInserirAlteracaoTipoActionForm(this);">

	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>

	<table width="770" border="0" cellspacing="5" cellpadding="0">
		<tr>
			<td width="115" valign="top" class="leftcoltext">
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


			<td valign="top" class="centercoltext">
			<table>
				<tr>
					<td></td>
				</tr>
			</table>

			<!--Início Tabela Reference a Páginação da Tela de Processo-->
			<table>
				<tr>
					<td></td>
				</tr>
			</table>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img border="0" src="imagens/parahead_left.gif" />
					</td>
					<td class="parabg">Inserir Tipo de Altera&ccedil;&atilde;o</td>
					<td width="11" valign="top"><img border="0"
						src="imagens/parahead_right.gif" /></td>
				</tr>
			</table>
			<!--Fim Tabela Reference a Páginação da Tela de Processo-->

			<table width="100%" border="0">
				<tr>
					<td colspan="2">Para inserir o tipo de altera&ccedil;&atilde;o, informe a
					descrição abaixo:</td>
				</tr>
				<tr>
					<td height="30"><strong>Descri&ccedil;&atilde;o: <font
						color="#FF0000">*</font></strong></td>
					<td colspan="2"><html:text property="descricao" maxlength="30"
						size="30"/><br>
					</td>
				</tr>
				<tr>
					<td><strong>Descri&ccedil;&atilde;o Abreviada: </strong></td>
					<td><strong> <html:text property="descricaoAbreviada" size="5"
						maxlength="5"/> </strong></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td align="left"><font color="#FF0000">*</font> Campo Obrigatório</td>
				</tr>
				<tr>
					<td colspan="2"><input name="Button" type="button"
						class="bottonRightCol" value="Desfazer" align="left"
						onclick="window.location.href='<html:rewrite page="/exibirInserirAlteracaoTipoAction.do?menu=sim"/>'"> <input name="Button"
						type="button" class="bottonRightCol" value="Cancelar" align="left"
						onclick="window.location.href='/gsan/telaPrincipal.do'"></td>
					<td width="53" height="24" align="right"><input type="button"
						name="Button2" class="bottonRightCol" value="Inserir"
						onClick="javascript:validarForm();" /></td>
					<td width="12">&nbsp;</td>
				</tr>
			</table>
			<p>&nbsp;</p>
		</tr>
	</table>
	<tr>
		<td colspan="3"><%@ include file="/jsp/util/rodape.jsp"%>
	</tr>

</html:form>

</body>
</html:html>
