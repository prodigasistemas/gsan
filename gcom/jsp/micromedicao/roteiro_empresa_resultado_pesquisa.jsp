<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>
<html:html>

<head>
<title>GSAN - Sistema Integrado de Gest&atilde;o de Servi&ccedil;os de Saneamento</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript">
function enviarIdParaInserir(idRoteiroEmpresa,destino) {
	opener.receberRoteiroEmpresa(idRoteiroEmpresa,destino);
	self.close();
}
</script>
</head>

<body leftmargin="0" topmargin="0" onload="resizePageSemLink(660, 430);">
<table width="630" border="0" cellspacing="5" cellpadding="0">
	<tr>
		<td width="630" valign="top" class="centercoltext">
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
				<td class="parabg">Pesquisa de Roteiro Empresa</td>
				<td width="11"><img border="0"
					src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
			</tr>
		</table>
		<p>&nbsp;</p>
		<table width="100%" border="0" bgcolor="#90c7fc">
			<tr align="left">
				<td width="13%" align="center"><strong>Empresa</strong></td>
				<td width="30%" align="center"><strong>Grupo Faturamento</strong></td>
				<td width="30%" align="center"><strong>Localidade</strong></td>				
				<td width="17%" align="center"><strong>Nome Leiturista</strong></td>
			</tr>
			<%--Esquema de paginação--%>
			<pg:pager isOffset="true" index="half-full" maxIndexPages="10"
				export="currentPageNumber=pageNumber;pageOffset" maxPageItems="10"
				items="${sessionScope.totalRegistros}">
				<pg:param name="q" />

				<%int cont = 0;
					String destino = (String) session.getAttribute("destino");
				
				%>


				<logic:iterate name="colecaoRoteiroEmpresa" id="roteiroEmpresa">
					<pg:item>
						<%cont = cont + 1;
			if (cont % 2 == 0) {%>
						<tr bgcolor="#cbe5fe">
							<%} else {

			%>
						<tr bgcolor="#FFFFFF">
							<%}%>

							<td width="13%" align="center">
							<div align="center"><a
								href="javascript:enviarIdParaInserir('<bean:write name="roteiroEmpresa" 
								property="codigo"/>','<%=destino%>');">
								<bean:write name="roteiroEmpresa"
									property="codigo" /></a></div>
							</td>
							<td width="20%" align="center">
								<logic:present
								name="rota"
								property="faturamentoGrupo">
								<bean:define name="rota"
									property="faturamentoGrupo" id="faturamentoGrupo" />
									
								<bean:write name="faturamentoGrupo"
									property="descricaoAbreviada" />
									
								</logic:present>								
							</td>
							<td width="30%" align="center">
								<logic:present name="roteiroEmpresa" property="setorComercial">
								<bean:define name="roteiroEmpresa" property="setorComercial" id="setorComercial" />
									<logic:present name="setorComercial" property="localidade">
										<bean:define name="setorComercial"
											property="localidade" id="localidade" />
										<bean:write name="localidade"
											property="descricao" />
									</logic:present>							
								</logic:present>							
							</td>
							<td width="17%" align="center">
								<logic:present name="roteiroEmpresa" property="leiturista">
									<bean:define name="roteiroEmpresa" property="leiturista" id="leiturista" />
									<logic:present name="leiturista" property="funcionario">
										<bean:define name="leiturista"
											property="funcionario" id="funcionario" />
										<bean:write name="funcionario" property="nome" />
									</logic:present>							
									<logic:notPresent name="leiturista" property="funcionario">
										<logic:present name="leiturista" property="cliente">
											<bean:define name="leiturista" property="cliente" id="cliente" />
											<bean:write name="cliente" property="nome" />
										</logic:present>
									</logic:notPresent>							
								</logic:present>														
							</td>
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
		</table>
		<table width="100%" border="0">
			<tr>
				<td height="24"><input type="button" class="bottonRightCol"
					value="Fechar" onclick="javascript:window.close();" /></td>
			</tr>
		</table>
		</td>
	</tr>
</table>
</body>
</html:html>
