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
<script language="JavaScript">
<!--
function fechar(){
  window.close();
}
-->
</script>

</head>

<body leftmargin="5" topmargin="5" onload="resizePageSemLink(600, 500);">

<html:form action="/exibirConsultarFaturaItemHistoricoPopupAction"
	method="post">


<table width="590" border="0" cellspacing="5" cellpadding="0">
	<tr>
		<td width="590" valign="top" class="centercoltext">
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
				<td class="parabg">Histórico de Alterações da Fatura</td>
				<td width="11"><img border="0"
					src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
			</tr>
		</table>

		<p>&nbsp;</p>
		
		<table width="100%" align="center" bgcolor="#90c7fc" border="0"
						cellpadding="0" cellspacing="0">
						<tr bgcolor="#cbe5fe">
							<td width="100%" align="center">
							<div style="width: 100%; height: 100%; overflow: auto;">
				<tr>
					<td colspan="2">
						<table width="100%">
							<tr>
								<td width="15%" align="center"><b>Imóvel</b></td>
								<td width="15%" align="center"><b>Referência</b></td>
								<td width="15%" align="center"><b>Valor</b></td>
								<td width="15%" align="center"><b>Operação</b></td>
								<td width="15%" align="center"><b>Data da Operação</b></td>
								<td width="25%" align="center"><b>Responsável pela Operação</b></td>
							</tr>
						</table>
					</td>
				</tr>
				 <tr>
			        <td colspan="2">
				        <div style="width: 100%; height: 100%; overflow: auto;">
						<table width="100%">
			        		<logic:present name="colecaoFaturaItemHistorico">
				        		<%int cont = 0;%>
								<logic:iterate name="colecaoFaturaItemHistorico" id="faturaItemHistorico"
									scope="session">
									<%cont = cont + 1;
									if (cont % 2 == 0) {%>
									<tr bgcolor="#cbe5fe">
										<%} else {%>
									<tr bgcolor="#FFFFFF">
										<%}%>
									<td width="15%" align="center">${faturaItemHistorico.imovel.id}</td>
									<td width="15%" align="center">
										<logic:present name="faturaItemHistorico" property="contaGeral.conta">
											<div align="center">
												<bean:write name="faturaItemHistorico" property="contaGeral.conta.formatarAnoMesParaMesAno"/>
											</div>
										</logic:present>
										<logic:present name="faturaItemHistorico" property="contaGeral.contaHistorico">
											<div align="center">
												<bean:write name="faturaItemHistorico" property="contaGeral.contaHistorico.formatarAnoMesParaMesAno"/>
											</div>
										</logic:present>
									</td>
									<td width="15%" align="right">
										<bean:write name="faturaItemHistorico" property="valorConta" formatKey="money.format"/>
									</td>
									<td width="15%" align="center">
										<logic:equal name="faturaItemHistorico" property="indicadorOperacao" value="1">
											Inclusão
										</logic:equal>
										<logic:equal name="faturaItemHistorico" property="indicadorOperacao" value="2">
											Exclusão
										</logic:equal>
									</td>
									<td width="15%" align="center">
										<bean:write name="faturaItemHistorico" property="ultimaAlteracao" formatKey="date.format"/>
									</td>
									<td width="25%">${faturaItemHistorico.usuario.nomeUsuario}</td>
							</tr>
						</logic:iterate>
						</logic:present>
						</table>
						</div>
			        </td>
		        </tr>
				
				<tr>
				<td colspan="2" align="right"><input name="Button" type="button"
					class="bottonRightCol" value="Fechar"
					onClick="javascript:fechar();"></td>
				</tr>
				
			</table>	

		</td>
	</tr>

</table>
</html:form>
</body>
</html:html>
