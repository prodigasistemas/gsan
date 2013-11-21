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

</head>

<body leftmargin="5" topmargin="5">
<logic:notPresent scope="session" name="gis">
	<%@ include file="/jsp/util/cabecalho.jsp"%>
</logic:notPresent>

<table width="770" border="0" cellspacing="5" cellpadding="0">
	<tr>
		<td width="770" valign="top" class="centercoltext">
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
				<td class="parabg">Espera</td>
				<td width="11"><img border="0"
					src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
			</tr>
		</table>
		<table width="100%" border="0" align="center">
			<tr>
				<td width="6%" align="left"><img
					src="<bean:message key="caminho.imagens"/>atencao.gif" /></td>
				<td width="48%" align="left" colspan="3">
					<table width="100%" border="0">
						<tr>
							<td><strong>Aguarde enquanto a funcionalidade está processando...</strong></td>
							<td align="right"><img src="<bean:message key="caminho.imagens"/>carregando.gif" border="0" /></td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		</td>
	</tr>
</table>
<logic:notPresent scope="session" name="gis">
	<%@ include file="/jsp/util/rodape.jsp"%>
</logic:notPresent>
</body>

</html:html>
