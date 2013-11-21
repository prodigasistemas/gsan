<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<head>
<html:html>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
</head>

<body leftmargin="5" topmargin="5" onload="resizePageSemLink(790, 385);">

<table width="770" border="0" cellspacing="5" cellpadding="0">
	<tr>
		<td width="765" valign="top" class="centercoltext">
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
				<td class="parabg">Status Geração Relatório por Usuário</td>
				<td width="11"><img border="0"
					src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>

			</tr>
		</table>

		<p>&nbsp;</p>
		<p><strong>${requestScope.nomeProcesso}</strong></p>
		<table width="100%" border="0">
			<tr>
				<td height="31">
				<table width="100%" bgcolor="#99CCFF">
					<!--header da tabela interna -->
					<tr bordercolor="#FFFFFF" bgcolor="#99CCFF">

						<td width="53%">
						<div align="center" class="style9"><strong>Usu&aacute;rio
						Solicitante</strong></div>
						</td>
						<td width="16%">
						<div align="center"><strong>Data Solicitação</strong></div>
						</td>
						<td width="16%">
						<div align="center"><strong>Divis&atilde;o</strong></div>
						</td>
						<td width="16%">
						<div align="center" class="style9"><strong>Status</strong></div>
						</td>
						<td width="16%" bgcolor="#99CCFF">
						<p align="center"><strong>Dispon&iacute;vel para </strong></p>
						<p align="center"><strong>Download At&eacute;</strong></p>
						</td>
					</tr>
					<%int cont = 0;

				%>
					<logic:iterate name="colecaoRelatoriosDadosUsuario" id="dados">

						<%cont = cont + 1;
					if (cont % 2 == 0) {%>
						<tr bgcolor="#cbe5fe" bordercolor="#FFFFFF">
							<%} else {%>
						<tr bgcolor="#FFFFFF" bordercolor="#FFFFFF">
							<%}%>



							<td height="17" >${dados[0]}</td>
							<td>${dados[6]}</td>
							<td >
							<div align="center">${dados[1]}</div>
							</td>
							<td ><c:if test="${empty dados[2] or 
							                   ((dados[2] ne 2))}"
								var="semData">
								<div align="center">${dados[5]}<br>
								</div>
							</c:if> <c:if test="${!semData}">
								<a
									href="/gsan/exibirRelatorioBatchAction.do?idFuncionalidadeIniciada=${dados[4]}">
								<div align="center">${dados[5]}<br>
								</div>
								</a>
							</c:if></td>
							<td>
							<div align="center">${dados[3]}</div>
							</td>

						</tr>

					</logic:iterate>
				</table>
				</td>
			</tr>
		</table>
		<p>&nbsp;</p>

		</td>
	</tr>
</table>
</body>
</html:html>
