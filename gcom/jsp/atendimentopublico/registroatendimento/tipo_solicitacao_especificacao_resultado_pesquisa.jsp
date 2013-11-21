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
</head>

<body leftmargin="5" topmargin="5" onload="resizePageSemLink(783, 555);">
<table width="770" border="0" cellspacing="5" cellpadding="0">
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
				<td class="parabg">Pesquisar Tipo de Solicitação com Especificações</td>

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
				<td width="35%" align="center" bgcolor="#90c7fc"><strong>Descrição
				do Tipo da Solicitação</strong></td>
				<td width="35%" align="center" bgcolor="#90c7fc"><strong>Grupo do
				Tipo da Solicitação</strong></td>
				<td width="30%" align="center" bgcolor="#90c7fc"><strong>Indicador
				de Falta D'Água</strong></td>

			</tr>
			<tr>
				<td colspan="6">
				<table width="100%" bgcolor="#99CCFF">



					<%--Esquema de paginação--%>
					<pg:pager maxIndexPages="10" export="currentPageNumber=pageNumber"
						index="center" maxPageItems="10">
						<pg:param name="pg" />
						<pg:param name="q" />
						<logic:present name="colecaoSolicitacaoTipo">
							<%int cont = 0;%>
							<logic:iterate name="colecaoSolicitacaoTipo" id="solicitacaoTipo">
								<pg:item>
									<%cont = cont + 1;
			if (cont % 2 == 0) {%>
									<tr bgcolor="#cbe5fe">
										<%} else {

			%>
									<tr bgcolor="#FFFFFF">
										<%}%>
										<logic:notPresent name="tipo">
										<td width="35%" align="center">
											<a href="javascript:enviarDados('${solicitacaoTipo.id}', '${solicitacaoTipo.descricao}', 'solicitacaoTipo');">
												${solicitacaoTipo.descricao}&nbsp; 
											</a>
										</td>
										</logic:notPresent>
										
										<logic:present name="tipo">
											<td width="6%" align="center">
												<a href="javascript:enviarDados('<bean:write name="solicitacaoTipo" property="id"/>', '<bean:write name="solicitacaoTipo" property="descricao"/>', '${tipo}');">
													<bean:write name="solicitacaoTipo" property="id" />
												</a>
											</td>
										</logic:present>
										
										<td width="35%" align="center">${solicitacaoTipo.solicitacaoTipoGrupo.descricao}&nbsp;
										</td>
										<td width="30%" align="center"><logic:equal
											name="solicitacaoTipo" property="indicadorFaltaAgua"
											value="1">
											SIM
											</logic:equal> <logic:equal name="solicitacaoTipo"
											property="indicadorFaltaAgua" value="2">
											NÃO
											</logic:equal></td>

									</tr>
								</pg:item>
							</logic:iterate>
						</logic:present>
				</table>

				</td>
			</tr>

		</table>
		<%if (((Collection) session.getAttribute("colecaoSolicitacaoTipo"))
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
					onclick="window.location.href='/gsan/exibirPesquisarTipoSolicitacaoEspecificacoesAction.do?menu=sim'" /></td>
			</tr>
		</table>
		</pg:pager></td>
	</tr>

</table>

</body>

</html:html>
