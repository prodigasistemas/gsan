<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ page import="java.math.BigDecimal,gcom.arrecadacao.ArrecadacaoDadosDiarios,gcom.util.Util" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>

<head>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>

<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
<SCRIPT LANGUAGE="JavaScript">
<!--

function fechar(){
		window.close();
}
</SCRIPT>
</head>
<body leftmargin="5" topmargin="5">
<html:form action="/exibirConsultarDadosDiariosLocalidadeAction.do"
	name="FiltrarDadosDiariosArrecadacaoActionForm"
	type="gcom.gui.arrecadacao.FiltrarDadosDiariosArrecadacaoActionForm"
	method="post">
	<table width="570" border="0" cellpadding="0" cellspacing="5">
		<tr>
			<td width="560" valign="top" class="centercoltext">
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
					<td class="parabg">Consultar Dados Diários da Arrecadação - Localidade</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
				</tr>
			</table>
			<table width="100%" border="0">
				<tr>
					<td height="10">
					<table width="100%" border="0">
						<%
						BigDecimal valorTotal = (BigDecimal)session.getAttribute("valorTotal");
						BigDecimal valorTotalElo = (BigDecimal)session.getAttribute("valorTotalElo");
						String referencia = (String)session.getAttribute("referencia");
						String nomeGerencia = (String)request.getAttribute("nomeGerencia");
						String nomeElo = (String)request.getAttribute("nomeElo");
						String idGerencia = (String)request.getAttribute("idGerencia");
						String idElo = (String)request.getAttribute("idElo");
						%>
						<tr bgcolor="#90c7fc">
							<td colspan="4">
								<div align="center"><strong>M&ecirc;s/Ano:</strong><strong>
								<%= Util.formatarAnoMesParaMesAno(referencia) %></strong></div>
							</td>
						</tr>
						<tr>
			                <td width="15%"><strong>Ger&ecirc;ncia:</strong></td>
			                <td width="35%" align="left"><strong> <%= nomeGerencia %></strong></td>
			                <td width="30%" align="right">
								<strong>Valor:</strong>
							</td>
							<td align="right" width="20%">
								<a href="javascript:abrirPopup('exibirConsultarDadosDiariosValoresDiariosAction.do?referencia=<%= referencia%>&idGerencia=<%= idGerencia%>', 475, 600);">
								<strong><%= Util.formatarMoedaReal(valorTotal) %></strong>
								</a>
							</td>
						</tr>
						<tr>
			                <td width="15%"><strong>Localidade Pólo:</strong></td>
			                <td width="35%" align="left"><strong> <%= nomeElo %></strong></td>
			                <td width="30%" align="right">
								<strong>Valor:</strong>
							</td>
							<td align="right" width="20%">
								<a href="javascript:abrirPopup('exibirConsultarDadosDiariosValoresDiariosAction.do?referencia=<%= referencia%>&idGerencia=<%= idGerencia%>&idElo=<%= idElo%>', 475, 600);">
									<strong><%= Util.formatarMoedaReal(valorTotalElo) %></strong>
								</a>
							</td>
						</tr>
					</table>
					<table width="100%" bgcolor="#99CCFF" dwcopytype="CopyTableRow">
						<!--header da tabela interna -->
						<tr bordercolor="#FFFFFF" bgcolor="#90c7fc">
							<td width="27%">
							<div align="center" class="style9"><strong>Localidade</strong></div>
							</td>
							<td width="20%">
							<div align="center" class="style9"><strong>Valor</strong></div>

							</td>
							<td width="20%">
							<div align="center"><strong>Percentual Localidade Pólo</strong></div>
							</td>
							<td width="23%">
							<div align="center"><strong>Percentual Gerência</strong></div>
							</td>
						</tr>
						<logic:present name="colecaoDadosDiarios">
						<logic:iterate name="colecaoDadosDiarios" id="arrecadacaoDadosDiarios">
						<tr bgcolor="#cbe5fe">
							<td height="17">
								<logic:present name="arrecadacaoDadosDiarios" property="localidade.localidade">
							      <div align="left">
							        <bean:write name="arrecadacaoDadosDiarios" property="localidade.localidade.descricao" />
								  </div>
								</logic:present>
							</td>
							<td>
								<logic:present name="arrecadacaoDadosDiarios" property="valorPagamentos">
							      <div align="right">
							      	<a href="javascript:abrirPopup('exibirConsultarDadosDiariosValoresDiariosAction.do?referencia=<bean:write name="arrecadacaoDadosDiarios" property="anoMesReferenciaArrecadacao" />&idGerencia=<bean:write name="arrecadacaoDadosDiarios" property="gerenciaRegional.id" />&idElo=<bean:write name="arrecadacaoDadosDiarios" property="localidade.localidade.id" />&idLocalidade=<bean:write name="arrecadacaoDadosDiarios" property="localidade.id" />', 475, 600);">
							        	<bean:write name="arrecadacaoDadosDiarios" property="valorPagamentos" formatKey="money.format"/>
							        </a>	
								  </div>
								</logic:present>
							</td>
							<% 
								BigDecimal percentualMultiplicacao = ((ArrecadacaoDadosDiarios)arrecadacaoDadosDiarios).getValorPagamentos().multiply(new BigDecimal("100.00"));

								BigDecimal percentual = percentualMultiplicacao.divide(
										valorTotal,2,BigDecimal.ROUND_HALF_UP);

								BigDecimal percentualMultiplicacaoElo = ((ArrecadacaoDadosDiarios)arrecadacaoDadosDiarios).getValorPagamentos().multiply(new BigDecimal("100.00"));

								BigDecimal percentualElo = percentualMultiplicacaoElo.divide(
										valorTotalElo,2,BigDecimal.ROUND_HALF_UP);

							%>								
							<td>
							<div align="right"><%= Util.formatarMoedaReal(percentualElo) %> %</div>
							</td>
							<td>
							<div align="right"><%= Util.formatarMoedaReal(percentual) %> %</div>
							</td>
						</tr>
					</logic:iterate>
					</logic:present>
					</table>
					<p></p>

					<p>&nbsp;</p>
					</td>
				</tr>
			</table>
			<table border="0" width="100%">
				<tr>
					<td colspan="3">&nbsp;</td>
				</tr>
				<tr>
					<td colspan="3" align="right"><input name="Button" type="button"
						class="bottonRightCol" value="Fechar"
						onClick="javascript:fechar();"></td>
				</tr>
				<p>&nbsp;</p>
			</table>
			</td>
		</tr>
	</table>
</html:form>
<body>
</html:html>