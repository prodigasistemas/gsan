<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>

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
	
	<script type="text/javascript">
	//FUNCAO COM MAIS PARAMETROS. NECESSARIO NA BUSCA DO FILTRO RESPONSAVEL SUPERIOR EM INSERIR/MANTER CONTRATO PARCELAMENTO POR CLIENTE.
	function enviarDadosConsultaContrParcel(codigoRegistro, descricaoRegistro, cnpj, tipoConsulta) {
		   opener.recuperarDadosPopupClienteContrParcel(codigoRegistro, descricaoRegistro, cnpj, tipoConsulta);
		   self.close();
		}
	
	</script>
	
</head>

<body leftmargin="5" topmargin="5"
	onload="javascript:resizePageSemLink(720, 405); ">

<table width="670" border="0" cellspacing="5" cellpadding="0">
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
				<td class="parabg">Pesquisar Respons&aacute;vel Superior</td>
				<td width="11"><img border="0"
					src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
			</tr>
		</table>
		<p>&nbsp;</p>
		<table width="100%" border="0" cellpadding="0" cellspacing="0">
			<tr align="left" bordercolor="#000000">
				<td width="69%" bgcolor="#90c7fc" align="center"><strong>Nome</strong></td>
				<td width="31%" bgcolor="#90c7fc" align="center"><strong>&nbsp;CNPJ</strong></td>
			</tr>
			<tr>
				<td colspan="2">
					<table width="100%" border="0" bgcolor="#99CCFF"><%--Esquema de paginação--%>
						<pg:pager isOffset="true" index="half-full" maxIndexPages="10"
								export="currentPageNumber=pageNumber;pageOffset"
								maxPageItems="10" items="${sessionScope.totalRegistros}">
						<pg:param name="q" />
						<% String cor = "#cbe5fe";%>
						<logic:iterate name="colecaoResponsavelSuperiores"
							id="responsavelSuperior">
							<pg:item>
							<% if (cor.equalsIgnoreCase("#cbe5fe")){   
			                                cor = "#FFFFFF";%>
							<tr align="left" bgcolor="#FFFFFF" height="18">
								<%} else{   
			                                cor = "#cbe5fe";%>
							<tr align="left" bgcolor="#cbe5fe" height="18">
								<%}%>
								<td width="70%" align="left">
								
									<c:choose>
											<c:when test="${tipoConsulta != null}">
												<logic:notEqual name="responsavelSuperior" property="indicadorUso" value="<%=ConstantesSistema.INDICADOR_USO_DESATIVO.toString()%>">            
													<a href="javascript:enviarDadosConsultaContrParcel('<bean:write name="responsavelSuperior" property="id"/>', '<bean:write name="responsavelSuperior" property="nome"/>', '<c:out value="${responsavelSuperior.cnpjFormatado}"></c:out>', '<c:out value="${tipoConsulta}"></c:out>');">
														<bean:write name="responsavelSuperior" property="nome" /> 
													</a>
									            </logic:notEqual>
									            <logic:equal name="responsavelSuperior" property="indicadorUso" value="<%=ConstantesSistema.INDICADOR_USO_DESATIVO.toString()%>">            
													<a href="javascript:enviarDadosConsultaContrParcel('<bean:write name="responsavelSuperior" property="id"/>', '<bean:write name="responsavelSuperior" property="nome"/>', '<c:out value="${responsavelSuperior.cnpjFormatado}"></c:out>', '<c:out value="${tipoConsulta}"></c:out>');">
														<font color="#CC0000"> 
															<bean:write name="responsavelSuperior" property="nome" /> 
														</font>
													</a>
									            </logic:equal>
											</c:when>
											<c:otherwise>
												<logic:notEqual name="responsavelSuperior" property="indicadorUso" value="<%=ConstantesSistema.INDICADOR_USO_DESATIVO.toString()%>">            
													<a href="javascript:enviarDados('<bean:write name="responsavelSuperior" property="id"/>','<bean:write name="responsavelSuperior" property="nome"/>','responsavelSuperior');">
														<bean:write name="responsavelSuperior" property="nome" /> 
													</a>
									            </logic:notEqual>
									            <logic:equal name="responsavelSuperior" property="indicadorUso" value="<%=ConstantesSistema.INDICADOR_USO_DESATIVO.toString()%>">            
													<a href="javascript:enviarDados('<bean:write name="responsavelSuperior" property="id"/>','<bean:write name="responsavelSuperior" property="nome"/>','responsavelSuperior');">
														<font color="#CC0000"> 
															<bean:write name="responsavelSuperior" property="nome" /> 
														</font>
													</a>
									            </logic:equal>
											</c:otherwise>
								</c:choose>
								
								</td>
								<td width="30%" align="right">
									<logic:notEqual name="responsavelSuperior" property="indicadorUso" value="<%=ConstantesSistema.INDICADOR_USO_DESATIVO.toString()%>">            
										<bean:write name="responsavelSuperior" property="cnpjFormatado" />
						            </logic:notEqual>
						            <logic:equal name="responsavelSuperior" property="indicadorUso" value="<%=ConstantesSistema.INDICADOR_USO_DESATIVO.toString()%>">            
										<font color="#CC0000"> 
											<bean:write name="responsavelSuperior" property="cnpjFormatado" />
										</font>
						            </logic:equal>
								</td>
							</tr>
							</pg:item>
						</logic:iterate>
					</table>
				</td>
			</tr>
		</table>
		<table width="100%" border="0">
			<tr>
				<td><div align="center"><strong><%@ include
						file="/jsp/util/indice_pager_novo.jsp"%></strong></div>
				</td>
			</tr>
			<tr>
				<td><input type="button" class="bottonRightCol"
					value="Voltar Pesquisa" onclick="javascript:history.back();"/></td>
			</tr>
		</table>
		</pg:pager> 
		<%-- Fim do esquema de paginação --%>
		</td>
	</tr>
</table>

</body>
</html:html>
