<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<%@ page import="gcom.util.ConstantesSistema"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<head>
<html:html>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="InserirProcessoActionForm" />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript">
function enviarForm() {
	var form = document.InserirProcessoActionForm;

	if (form.idProcessoTipo.selectedIndex == 0) {
		alert('Selecione um Tipo de Processo.');
		return false;
	} else {
		form.submit();
	}
	
}

</script>
</head>

<body leftmargin="5" topmargin="5"
	onload="javascript:setarFoco('${requestScope.nomeCampo}');">
<html:form action="/inserirProcessoAction.do"
	name="InserirProcessoActionForm"
	type="gcom.gui.batch.InserirProcessoActionForm" method="post">

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
					<td class="parabg">Iniciar Processo</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>

				</tr>
			</table>

			<p>&nbsp;</p>
			<table width="100%" border="0" cellpadding="0" cellspacing="3">
				<tr>

					<td colspan="3">Para iniciar o processo, informe o tipo:</td>

				</tr>
				<tr>

					<td width="26%"><strong>Tipo do Processo:<font color="#FF0000">*</font></strong></td>


					<td width="74%" colspan="2"><html:select property="idProcessoTipo"
						tabindex="2">
						<html:option
							value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
						<html:options collection="colecaoProcessoTipo"
							labelProperty="descricao" property="id" />
					</html:select></td>

				</tr>
				<tr>

					<td colspan="3">&nbsp;</td>
				</tr>
				<tr>
					<td height="17" colspan="3"><strong><font color="#FF0000"> </font></strong><strong><font
						color="#FF0000"> </font></strong><strong><font color="#FF0000"> </font></strong></td>
				</tr>

				<tr>
					<td height="17" colspan="3"><strong><font color="#FF0000"> </font></strong><strong><font
						color="#FF0000"> </font></strong><strong><font color="#FF0000"> </font></strong></td>

				</tr>
				<tr>
					<td height="17" colspan="3"><strong><font color="#FF0000"> </font></strong><strong><font
						color="#FF0000"> </font></strong><strong><font color="#FF0000"> </font></strong></td>

				</tr>
				<tr>
					<td colspan="3" align="right">
					<table border="0" width="100%">

						<tr>
							<td align="right" width="60%">&nbsp;</td>
							<td align="right" width="5%"><input name="avancar" type="button"
								class="bottonRightCol" value="Avançar"
								onClick="javascript:enviarForm();" /></td>

							<td align="right" width="2%"><img
								src="<bean:message key="caminho.imagens"/>avancar.gif"
								width="15" border="0" onclick="javascript:enviarForm();" /></td>
							<td align="right" width="33%">&nbsp;</td>

						</tr>
					</table>
					</td>
				</tr>

				<tr>
					<td colspan="3" height="1px" bgcolor="#000000"></td>
				</tr>
				<%--
				<tr>
					<td colspan="2"><font color="#ff0000"> <input name="Submit22"
						class="bottonRightCol" value="Desfazer" type="button"
						onclick="window.location.href='/gsan/exibirInserirComandoAcaoCobrancaAction.do?menu=sim';">

					<input name="Submit23" class="bottonRightCol" value="Cancelar"
						type="button"
						onclick="window.location.href='/gsan/telaPrincipal.do'"> </font></td>
				</tr>
	
--%>
				<tr>
					<td colspan="2"><input type="button" name="Button"
						class="bottonRightCol" value="Desfazer"
						onClick="javascript:document.InserirProcessoActionForm.idProcessoTipo.selectedIndex=0;"
						style="width: 80px" /> &nbsp; <input type="button" name="Button"
						class="bottonRightCol" value="Cancelar"
						onClick="javascript:window.location.href='/gsan/telaPrincipal.do'"
						style="width: 80px" /></td>
				</tr>

			</table>
			<p>&nbsp;</p>

			</td>
		</tr>
	</table>


	<%@ include file="/jsp/util/rodape.jsp"%>


</html:form>
</body>
</html:html>
