<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>

<%@ page import="java.util.Collection,gcom.util.ConstantesSistema"%>
<html:html>
<head>
<title>GSAN - Sistema Integrado de Gest&atilde;o de Servi&ccedil;os de Saneamento</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="PesquisarActionForm"
	dynamicJavascript="false" />
</head>

<body leftmargin="5" topmargin="5" onload="resizePageSemLink(790, 410);">
<table width="750" border="0" cellspacing="5" cellpadding="0">
	<tr>
		<td width="750" valign="top" class="centercoltext">
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
				<td class="parabg">Pesquisa de Anormalidade de Leitura</td>

				<td width="11"><img border="0"
					src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
			</tr>
			<tr>
				<td height="5" colspan="3"></td>
			</tr>
		</table>


		<table width="100%" cellpadding="0" cellspacing="0">
			<tr>
				<td></td>
			</tr>
			<tr>
				<!--<td colspan="4" bgcolor="#3399FF"> -->
				<td colspan="6" bgcolor="#000000" height="2"></td>
			</tr>

			<tr bordercolor="#000000">
				<td width="9%" align="center" bgcolor="#90c7fc"><strong>Código</strong></td>
				<td width="35%" align="center" bgcolor="#90c7fc"><strong>Descrição
				da Anormalidade de Leitura</strong></td>
				<td width="14%" align="center" bgcolor="#90c7fc"><strong>Relativa a
				Hidrômetro</strong></td>
				<td width="14%" align="center" bgcolor="#90c7fc"><strong>Aceita p/
				Ligação sem Hidr.</strong></td>
				<td width="14%" align="center" bgcolor="#90c7fc"><strong>Uso
				Restrito do Sistema</strong></td>
				<td width="14%" align="center" bgcolor="#90c7fc"><strong>Perda da
				Tarifa Social</strong></td>
			</tr>
			<tr>
				<td colspan="6">
				<table width="100%" bgcolor="#99CCFF">



					<%--Esquema de paginação--%>
					<pg:pager maxIndexPages="10" export="currentPageNumber=pageNumber"
						index="center" maxPageItems="10">
						<pg:param name="pg" />
						<pg:param name="q" />
						<logic:present name="leituraAnormalidades">
							<%int cont = 0;%>
							<logic:iterate name="leituraAnormalidades"
								id="leituraAnomalidade">
								<pg:item>
									<%cont = cont + 1;
			if (cont % 2 == 0) {%>
									<tr bgcolor="#FFFFFF">
										<%} else {

			%>
									<tr bgcolor="#cbe5fe">
										<%}%>
										<td width="9%">
										<div align="center"><bean:write name="leituraAnomalidade"
											property="id" /></div>
										</td>
										<td width="35%"><a
											href="javascript:enviarDados('<bean:write name="leituraAnomalidade" property="id"/>', '<bean:write name="leituraAnomalidade" property="descricao"/>', 'leituraAnormalidade');">
										<bean:write name="leituraAnomalidade" property="descricao" />
										</a></td>
										<td width="14%" align="center"><logic:equal
											name="leituraAnomalidade"
											property="indicadorRelativoHidrometro" value="1">
											SIM
										</logic:equal> <logic:equal name="leituraAnomalidade"
											property="indicadorRelativoHidrometro" value="2">
											NÃO
										</logic:equal></td>
										<td width="14%" align="center"><logic:equal
											name="leituraAnomalidade"
											property="indicadorImovelSemHidrometro" value="1">
											SIM
											</logic:equal>
											<logic:equal
											name="leituraAnomalidade"
											property="indicadorImovelSemHidrometro" value="2">
											NÃO
											</logic:equal>
										</td>
										<td width="14%" align="center"><logic:equal
											name="leituraAnomalidade" property="indicadorSistema" value="1">
											SIM
											</logic:equal>
											<logic:equal
											name="leituraAnomalidade" property="indicadorSistema" value="2">
											NÃO
											</logic:equal>
										</td>
										<td width="14%" align="center"><logic:equal
											name="leituraAnomalidade"
											property="indicadorPerdaTarifaSocial" value="1">
											SIM
											</logic:equal>
											<logic:equal
											name="leituraAnomalidade"
											property="indicadorPerdaTarifaSocial" value="2">
											NÃO
											</logic:equal>
										</td>
									</tr>
								</pg:item>
							</logic:iterate>
						</logic:present>
				</table>

				</td>
			</tr>

		</table>
		<%if (((Collection) session.getAttribute("leituraAnormalidades"))
					.size() == ConstantesSistema.NUMERO_MAXIMO_REGISTROS_PESQUISA) {%>
		<%@ include file="/jsp/util/limite_filtro.jsp"%> <%}%>

		<table width="100%" border="0">
			<tr>
				<td><strong><%@ include file="/jsp/util/indice_pager.jsp"%></strong>
				</td>
			</tr>
			<tr>
				<td height="24"><input type="button" class="bottonRightCol"
					value="Nova Pesquisa"
					onclick="window.location.href='<html:rewrite page="/exibirPesquisarLeituraAnormalidadeAction.do"/>'" /></td>
			</tr>
		</table>
		</pg:pager></td>
	</tr>

</table>

</body>

</html:html>
