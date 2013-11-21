<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
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
<%@ page
	import="gcom.gerencial.bean.ResumoCobrancaAcaoSituacaoAcaoDetalhesHelper"%>
<SCRIPT LANGUAGE="JavaScript">
<!--

function fechar(){
		window.close();
}
</SCRIPT>
</head>
<body leftmargin="5" topmargin="5">
<html:form action="/exibirConsultarResumoAcaoCobrancaPopupAction.do"
	name="ResumoAcaoCobrancaActionForm"
	type="gcom.gui.gerencial.cobranca.ResumoAcaoCobrancaActionForm"
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
					<%
						String tipoDetalhe = (String) session.getAttribute("tipoDetalhe");
						if (tipoDetalhe.equalsIgnoreCase("F") || tipoDetalhe.equalsIgnoreCase("eF")){
					%>
						<td class="parabg">Consultar Situação Encontrada</td>
						<td bgcolor="#79bbfd" align="center"><strong> </strong></td>							
					<%							
						} else if (tipoDetalhe.equalsIgnoreCase("F") || tipoDetalhe.equalsIgnoreCase("eF")){
					%>
						<td class="parabg">Consultar Motivo da Baixa da Ordem</td>							
					<%	
						} else {
					%>
						<td class="parabg">Consultar Tipo de Corte da Ordem</td>
					<%	
						}
					%>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<table>
				<tr>
					<td></td>
				</tr>
				<tr>
					<td><strong>Ação de Cobrança:</strong></td>
					<td>${requestScope.cobrancaAcao}</td>
				</tr>
				<tr>
					<td><strong>Situação da Ação:</strong></td>
					<td>${requestScope.cobrancaAcaoSituacao}</td>
				</tr>
			</table>

			<table bgcolor="#90c7fc" border="0">
				<tr>
						<%
						if (tipoDetalhe.equalsIgnoreCase("F") || tipoDetalhe.equalsIgnoreCase("eF")){
						%>
						<td bgcolor="#79bbfd" align="center"><strong>Situação Encontrada</strong></td>
						<%
						} else if (tipoDetalhe.equalsIgnoreCase("E") || tipoDetalhe.equalsIgnoreCase("eE")){
						%>
						<td bgcolor="#79bbfd" align="center"><strong>Motivo da Baixa da Ordem</strong></td>
						<%
						} else {
						%>
						<td bgcolor="#79bbfd" align="center"><strong>Tipo de Corte da Ordem</strong></td>
						<%
						}
						%>
					<td bgcolor="#79bbfd" align="center"><strong> Quantidade</strong></td>
					<td bgcolor="#79bbfd" align="center"><strong> Percentual </strong></td>
					<td bgcolor="#79bbfd" align="center"><strong> Valor</strong></td>
					<td bgcolor="#79bbfd" align="center"><strong> Percentual</strong></td>
				</tr>
				<logic:notEmpty name="colecaoResumoCobrancaAcaoSituacaoAcaoDetalhes">


					<%String cor = "#FFFFFF";%>

					<logic:iterate name="colecaoResumoCobrancaAcaoSituacaoAcaoDetalhes"
						id="resumoCobrancaAcaoSituacaoAcaoDetalhe">

						<tr>
							<%if (cor.equalsIgnoreCase("#FFFFFF")) {
								cor = "#cbe5fe";
							} else {
								cor = "#FFFFFF";
							}
							%>
							<td width="17%" bgcolor="<%=cor%>" align="center"><bean:write
								name="resumoCobrancaAcaoSituacaoAcaoDetalhe" property="descricao" /></td>							
							<td width="17%" align="center" bgcolor="<%=cor%>"><bean:write
								name="resumoCobrancaAcaoSituacaoAcaoDetalhe"
								property="quantidadeDocumento" formatKey="number.format" /></td>
							<td width="17%" align="right" bgcolor="<%=cor%>"><%=((ResumoCobrancaAcaoSituacaoAcaoDetalhesHelper) 
									resumoCobrancaAcaoSituacaoAcaoDetalhe)
									.getPercentualQuantidade(""	+ request.getAttribute("quantidadeTotal"))%></td>
							<td width="17%" align="right" bgcolor="<%=cor%>"><bean:write
								name="resumoCobrancaAcaoSituacaoAcaoDetalhe"
								property="valorDocumento" formatKey="money.format" /></td>
							<td width="15%" align="right" bgcolor="<%=cor%>"><%=((ResumoCobrancaAcaoSituacaoAcaoDetalhesHelper)
									resumoCobrancaAcaoSituacaoAcaoDetalhe)
									.getPercentualValor("" + request.getAttribute("valorTotal"))%></td>
						</tr>
					</logic:iterate>
				</logic:notEmpty>
				<tr>
					<td bgcolor="#cbe5fe" width="34%" align="center"><strong>TOTAL</strong></td>
					<td bgcolor="#cbe5fe" width="17%" align="center"><strong>${requestScope.quantidadeTotal}</strong></td>
					<td bgcolor="#cbe5fe" width="17%" align="right"><strong>100,00</strong></td>
					<td bgcolor="#cbe5fe" width="17%" align="right"><strong>${requestScope.valorTotalFormatado}</strong></td>
					<td bgcolor="#cbe5fe" width="15%" align="right"><strong>100,00</strong></td>
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
			</table>
			</td>
		</tr>
	</table>
</html:form>
<body>
</html:html>
