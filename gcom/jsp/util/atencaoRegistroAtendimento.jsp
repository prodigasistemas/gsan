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
	src="<bean:message key="caminho.js"/>util.js"></script>

</head>

<body leftmargin="5" topmargin="5">

<%@ include file="/jsp/util/cabecalho.jsp"%>

<form action="" method="post">

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
				<td class="parabg">Alerta</td>
				<td width="11"><img border="0"
					src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
			</tr>
		</table>
		<table width="100%" border="0">
			<tr>
				<td width="6%" align="left"><img
					src="<bean:message key="caminho.imagens"/>atencao.gif" /></td>
				<td width="93%" align="center">
				<div align="left"><logic:present name="atencao">
					<strong> <bean:write name="atencao" /><br>
					</strong>
				</logic:present></div>
				</td>
				<td width="0" align="center"></td>
			</tr>
			<tr>
				<td colspan="3">&nbsp;</td>
			</tr>
		</table>
		<table width="100%" border="0">
			<tr>
				<td width="50%" align="left"><logic:present name="labelBotao">

					<logic:equal name="tipoChamada" value="popup">
						<INPUT TYPE="button" value="${requestScope.labelBotao}"
							onclick="abrirPopup('${requestScope.urlBotao}', 500, 500);"
							class="bottonRightCol" style="width: 200px">
					</logic:equal>

					<logic:notEqual name="tipoChamada" value="popup">
						<INPUT TYPE="button" value="${requestScope.labelBotao}"
							onclick="window.location.href='${requestScope.urlBotao}';"
							class="bottonRightCol" style="width: 200px">
					</logic:notEqual>
				</logic:present> <logic:present name="labelBotao2">
          	&nbsp;
          	<logic:equal name="tipoChamada2" value="popup">
						<INPUT TYPE="button" value="${requestScope.labelBotao2}"
							onclick="abrirPopup('${requestScope.urlBotao2}', 500, 500);"
							class="bottonRightCol" style="width: 220px">
					</logic:equal>

					<logic:notEqual name="tipoChamada2" value="popup">
						<INPUT TYPE="button" value="${requestScope.labelBotao2}"
							onclick="window.location.href='${requestScope.urlBotao2}';"
							class="bottonRightCol" style="width: 220px">
					</logic:notEqual>
				</logic:present></td>
				<td width="50%" align="right">
				<table>
					<logic:present name="proximaAba">
					<tr>
						<td width="40%" colspan="4" align="right">
						 <a href="javascript:botaoAvancarRA('${requestScope.voltarAba}');"><img
									src="imagens/voltar.gif" border="0" /></a></td>
						<td><INPUT TYPE="button" class="buttonAbaRodape" value="Voltar"
							onclick="javascript:botaoAvancarRA('${requestScope.voltarAba}');">
						</td>
						<td>
						<INPUT TYPE="button" class="buttonAbaRodape" value="Avançar"
									onclick="javascript:botaoAvancarRA('${requestScope.proximaAba}');">
						</td>
						<td><a
									href="javascript:botaoAvancarRA('${requestScope.proximaAba}');"><img
									src="imagens/avancar.gif" border="0" /></a>
								
						</td>
					</tr>
					</logic:present>
					
					<logic:present name="concluir">
					<tr>
						<td colspan="4" align="right">
						 <INPUT TYPE="button" class="buttonAbaRodape" value="Concluir"
							onclick="javascript:botaoAvancarRA('${requestScope.concluir}');">
						</td>
					</tr>
					</logic:present>
				</table>
				<p>&nbsp;</p>
				<p>&nbsp;</p>
				</td>
			</tr>
		</table>
		<%@ include file="/jsp/util/rodape.jsp"%></td>
	</tr>
</table>
</form>
</body>

</html:html>
