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

<body leftmargin="5" topmargin="5"
	onload="javascript:resizePageSemLink(806, 415)">
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
				<td class="parabg">Pesquisa de Im&oacute;vel</td>
				<td width="11"><img border="0"
					src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
			</tr>
		</table>
		<p>&nbsp;</p>
		<table width="100%" border="0" bgcolor="#90c7fc">
			<tr align="left" bgcolor="#90c7fc">
				<td width="9%" align="center"><strong>Matr&iacute;cula</strong></td>
				<td width="30%" align="center"><strong>Cliente</strong></td>
				<td width="60%" align="center"><strong>Endere&ccedil;o</strong></td>
			</tr>
			<%--Esquema de paginação--%>
			<pg:pager isOffset="true" index="half-full" maxIndexPages="10"
				export="currentPageNumber=pageNumber;pageOffset"
				maxPageItems="10" items="${sessionScope.totalRegistros}">
				
				
				<pg:param name="q" />

					<%int cont = 0;%>
			        <logic:iterate name="colecaoClientesImoveis" id="clienteImovel">
					<pg:item>
							<%cont = cont + 1;
							if (cont % 2 == 0) {%>
								<tr bgcolor="#cbe5fe">								
							<%} else {	%>
								<tr bgcolor="#FFFFFF">
							<%}%>
				
							<td width="9%" align="center"><bean:write
								name="clienteImovel" property="imovel.id" /></td>

							<logic:notEmpty name="caminhoRetornoTelaPesquisaImovel">
								<td><a
									href="javascript:enviarDadosParametros('<bean:write name="caminhoRetornoTelaPesquisaImovel"/>', '<bean:write name="clienteImovel" property="imovel.id"/>', '<bean:write name="clienteImovel" property="imovel.inscricaoFormatada"/>', 'imovel');">
								<bean:write name="clienteImovel" property="cliente.nome" /></a>
								</td>
							</logic:notEmpty>
							<logic:empty name="caminhoRetornoTelaPesquisaImovel">
								<logic:notEmpty name="tipo" scope="session">
									<td width="30%"><a
										href="javascript:enviarDados('<bean:write name="clienteImovel" property="imovel.id"/>', '<bean:write name="clienteImovel" property="imovel.inscricaoFormatada"/>', '<bean:write name="tipo"/>');">
									<bean:write name="clienteImovel" property="cliente.nome" /> </a>
									</td>
								</logic:notEmpty>
								<logic:empty name="tipo" scope="session">
									<td width="30%"><a
										href="javascript:enviarDados('<bean:write name="clienteImovel" property="imovel.id"/>', '<bean:write name="clienteImovel" property="imovel.inscricaoFormatada"/>', 'imovel');">
									<bean:write name="clienteImovel" property="cliente.nome" /> </a>
									</td>
								</logic:empty>
							</logic:empty>
							<td width="60%"><bean:write
								name="clienteImovel" property="imovel.enderecoFormatado" /></td>
						</tr>
					</pg:item>
				</logic:iterate>
		</table>
		<table width="100%" border="0">
			<tr>
				<td>
				<div align="center"><strong><%@ include
					file="/jsp/util/indice_pager_novo.jsp"%></strong></div>
				</td>
				</pg:pager>
				<%-- Fim do esquema de paginação --%>
			</tr>
			<tr>
				<td height="24"><input type="button" class="bottonRightCol"
					value="Voltar Pesquisa"
					onclick="window.location.href='<html:rewrite page="/exibirPesquisarImovelAction.do?novaPesquisa=1&objetoConsulta=1"/>'" /></td>
			</tr>
		</table>
</table>
</body>
</html:html>
