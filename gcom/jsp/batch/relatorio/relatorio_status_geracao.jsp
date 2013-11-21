<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan"%>

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

<body leftmargin="5" topmargin="5">

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
				<td class="parabg">Status Geração Relatório</td>
				<td width="11"><img border="0"
					src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>

			</tr>
		</table>

		<p>&nbsp;</p>

		<p><strong><strong> Data: <bean:write name="dataCorrente"
			formatKey="date.format" /> - Hora: <bean:write name="dataCorrente"
			formatKey="hour.format" /> </strong></p>
		<p>&nbsp;</p>
		<table width="100%" border="0">
			<tr>
				<td height="31">
				<table width="100%" bgcolor="#99CCFF">
					<!--header da tabela interna -->
					<tr bordercolor="#FFFFFF" bgcolor="#99CCFF">
						<td width="53%">
						<div align="center" class="style9"><strong>Nome do
						Relat&oacute;rio </strong></div>
						</td>

						<td width="16%">
						<div align="center" class="style9"><strong>Quantidade
						Dispon&iacute;vel </strong></div>
						</td>
						<td width="17%">
						<div align="center">
						<p><strong>Quantidade </strong></p>
						<p><strong>Em Processamento </strong></p>
						</div>
						</td>
					</tr>
					<%int cont = 0;

				%>
					<logic:iterate name="colecaoRelatoriosDados" type="Object[]"
						id="dadoRelatorio">

						<%cont = cont + 1;
					if (cont % 2 == 0) {%>
						<tr bgcolor="#cbe5fe" bordercolor="#FFFFFF">
							<%} else {%>
						<tr bgcolor="#FFFFFF" bordercolor="#FFFFFF">
							<%}%>


							<td><a
								href="javascript:abrirPopup('exibirStatusGeracaoUsuarioAction.do?idProcesso=${dadoRelatorio[3]}', 500, 700);">${dadoRelatorio[0]}</a></td>
							<td height="17">
							<div align="center">${dadoRelatorio[1]}<br>
							</div>
							</td>
							<td>
							<div align="center">${dadoRelatorio[2]}</div>
							</td>
						</tr>
					</logic:iterate>
				</table>
				</td>
			</tr>

		</table>
		<p>&nbsp;</p>
		<p>&nbsp;</p>

		</td>
	</tr>
</table>


<%@ include file="/jsp/util/rodape.jsp"%>



</body>
</html:html>
