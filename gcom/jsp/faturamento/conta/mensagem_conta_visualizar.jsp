<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>
<%@ page import="gcom.util.ConstantesSistema,java.util.Collection"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>

<script language="JavaScript">

</script>
</head>
<!--  -->
<body leftmargin="0" topmargin="0" onload="resizePageSemLink(500,328);">
<html:form 
	action="/exibirVisualizarMensagemContaAction"
	name="VisualizarMensagemContaActionForm"
	type="gcom.gui.faturamento.conta.VisualizarMensagemContaActionForm"
	method="post">

	<table border="0" cellspacing="5" cellpadding="0">
		<tr>
			<td valign="top" class="centercoltext">
			<table height="100%">

				<tr>
					<td></td>
				</tr>
			</table>
			<table align="center" border="0" cellpadding="0" cellspacing="0"
				width="100%">
				<tbody>
					<tr>
						<td width="11"><img border="0"
							src="<bean:message key="caminho.imagens"/>parahead_left.gif" /></td>
						<td class="parabg">Visualizar Mensagem da Conta</td>

						<td width="11"><img border="0"
							src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>

					</tr>
				</tbody>
			</table>
			<p>&nbsp;</p>
			<table width="100%" bgcolor="#99CCFF">
				<tbody>
					<tr align="left" bgcolor="#99CCFF">
						<td width="35%">
						<strong>Mensagem da Conta</strong>
						</td>
						<td width="65%">
						<strong> <html:textarea
							property="mensagemConta01" cols="40" rows="2" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000;"/></strong>
						</td>
					</tr>
					<tr>
						<td>
						<strong></strong>
						</td>
						<td>
						<strong><html:textarea
							property="mensagemConta02" cols="40" rows="2" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000;"/></strong>
						</td>
					</tr>
					<tr>
						<td rowspan="2" bgcolor="#99CCFF">
						<strong></strong>
						</td>
						<td rowspan="2" bgcolor="#99CCFF">
						<strong><html:textarea
							property="mensagemConta03" cols="40" rows="2" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000;"/></strong>
						</td>

					</tr>
				</tbody>
			</table>
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>
					<td>
					<div align="right"><input type="button" name="butao" value="Fechar"
						class="bottonRightCol" onclick="javascript:window.close();"></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
</html:form>
</body>
</html:html>
