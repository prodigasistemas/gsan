<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>

<%@ page isELIgnored="false"
	import="java.util.Collection,gcom.util.ConstantesSistema"%>
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

<body leftmargin="5" topmargin="5" onload="javascript:window.focus();resizePageSemLink(790, 410);">
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
				<td class="parabg">Pesquisa de <bean:write name="dados"
					property="titulo" scope="session" /></td>
				<td width="11"><img border="0"
					src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
			</tr>
		</table>
		<p>&nbsp;</p>
		<table width="100%" border="0" bgcolor="#90c7fc">
			<tr bgcolor="#90c7fc">
				<td width="15%" align="center"><strong>C&oacute;digo</strong></td>
				<td width="42%" align="center"><strong>Descrição</strong></td>
				<td width="42%" align="center"><strong>Descrição Abreviada</strong></td>
			</tr>
			<tr>
				<%--Esquema de paginação--%>
				<pg:pager isOffset="true" index="half-full" maxIndexPages="10"
					export="currentPageNumber=pageNumber;pageOffset"				
					maxPageItems="10" items="${sessionScope.totalRegistros}">
					<pg:param name="pg" />
					<pg:param name="q" />
					<%int cont = 0;%>
					<logic:iterate name="colecaoTabelaAuxiliarAbreviada"
						id="tabelaAuxiliarAbreviada" scope="session">
						<pg:item>
							<%cont = cont + 1;
							if (cont % 2 == 0) {%>
							<tr bgcolor="#cbe5fe">
							<%} else {%>
							<tr bgcolor="#FFFFFF">
							<%}%>
								<td align="center">
									<bean:write name="tabelaAuxiliarAbreviada" property="id" />
								</td>
								<c:if test="${empty requestScope.tipoPesquisa}" var="testeTipoPesquisa">
								<td align="center">
									<a href="javascript:enviarDadosParametros('${requestScope.caminhoRetornoTelaPesquisa}','<bean:write name="tabelaAuxiliarAbreviada" property="id"/>', '<bean:write name="tabelaAuxiliarAbreviada" property="descricao"/>', '${sessionScope.dados.nomeParametroFuncionalidade}');">
										<bean:write name="tabelaAuxiliarAbreviada" property="descricao" /></a>
								</td>
								</c:if>
								<c:if test="${!testeTipoPesquisa}">
									
									<c:if test="${testeTipoPesquisa == 'retornoPopup'}">
										<td align="center">
											<a href="javascript:enviarDados('<bean:write name="tabelaAuxiliarAbreviada" property="id"/>', '<bean:write name="tabelaAuxiliarAbreviada" property="descricao"/>', '${sessionScope.dados.nomeParametroFuncionalidade}');">
										<bean:write name="tabelaAuxiliarAbreviada" property="descricao" /></a>
										</td>
									</c:if>
									
									<c:if test="${testeTipoPesquisa != 'retornoPopup'}">
										<td align="center">
											<a href="javascript:window.location.href='${requestScope.caminhoRetorno}.do?id=${tabelaAuxiliarAbreviada.id}&descricao=${tabelaAuxiliarAbreviada.descricao}'">
												<bean:write name="tabelaAuxiliarAbreviada" property="descricao" /></a>
										</td>
									</c:if>
								</c:if>
								<td align="center">
									<bean:write name="tabelaAuxiliarAbreviada" property="descricaoAbreviada" />
								</td>
							</tr>
						</pg:item>
					</logic:iterate>
				</tr>
			</table>	
		<table width="100%" border="0">
			<tr>
				<td height="24">
					<input type="button" class="bottonRightCol" value="Voltar Pesquisa"
					onclick="window.history.back();"/>
					<%-- window.location.href='<html:rewrite page="/exibirPesquisarTabelaAuxiliarAbreviadaAction.do?tela=${sessionScope.dados.nomeParametroFuncionalidade}&tipoPesquisa=${requestScope.tipoPesquisa}"/>'" />--%>
				</td>
			</tr>
		</table>
		<table width="100%" border="0" align="center">
			<tr>
				<td align="center">
					<strong><%@ include file="/jsp/util/indice_pager_novo.jsp"%></strong>
				</td>
			</tr>
		</table>
		</pg:pager> 
		<%-- Fim do esquema de paginação --%>
		</td>
	</tr>
</table>

</body>
</html:html>
