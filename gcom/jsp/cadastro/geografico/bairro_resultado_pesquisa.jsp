<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>

<%@ page import="gcom.util.ConstantesSistema"%>
<html:html>
<head>
<title>GSAN - Sistema Integrado de Gest&atilde;o de Servi&ccedil;os de Saneamento</title>

<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="PesquisarActionForm" dynamicJavascript="false" />
<script language="JavaScript">

function enviarIdParaInserir(idBairro) {
	opener.redirecionarSubmitAtualizar(idBairro);
	self.close();
}
</script>

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
				<td class="parabg">Pesquisa de Bairro</td>
				<td width="11"><img border="0"
					src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
			</tr>
		</table>

		<p>&nbsp;</p>

		<table width="100%" border="0" bgcolor="#90c7fc">
			<tr align="left">
				<td width="15%" align="center"><strong>C&oacute;digo</strong></td>
				<td width="60%" align="center"><strong>Nome</strong></td>
				<td width="30%" align="center"><strong>Município</strong></td>
			</tr>

			<tr align="left">
				<%--Esquema de paginação--%>
				<pg:pager isOffset="true" index="half-full" maxIndexPages="10"
					export="currentPageNumber=pageNumber;pageOffset" maxPageItems="10"
					items="${sessionScope.totalRegistros}">

					<pg:param name="q" />

					<%int cont = 0;%>

					<logic:iterate name="colecaoBairros" id="bairro">
						<pg:item>
						<%	cont = cont + 1;
							if (cont % 2 == 0) {%>
								<tr bgcolor="#cbe5fe">
						<%	} else {	%>
								<tr bgcolor="#FFFFFF">
						<%	}	%>
						
								<td align="center">
									
									<logic:notEqual name="bairro" property="indicadorUso"
										value="<%=ConstantesSistema.INDICADOR_USO_DESATIVO.toString()%>">

										<bean:write name="bairro" property="codigo" />
									
									</logic:notEqual> 
									
									<logic:equal name="bairro" property="indicadorUso"
										value="<%=ConstantesSistema.INDICADOR_USO_DESATIVO.toString()%>">
										
										<font color="#CC0000"> 
											<bean:write name="bairro" property="codigo" /> 
										</font>
									
									</logic:equal>
								</td>
								
								<td>
								  <logic:present name="caminhoRetornoTelaPesquisaBairro" scope="session">
								    <a href="javascript:enviarDadosParametros('<bean:write name="caminhoRetornoTelaPesquisaBairro"/>', '<bean:write name="bairro" property="codigo"/>', '<bean:write name="bairro" property="nome"/>', 'bairro');">
										<bean:write name="bairro" property="nome" />
									</a>
								  </logic:present>
								  <logic:notPresent name="caminhoRetornoTelaPesquisaBairro" scope="session">
									<logic:notPresent name="tipo" scope="session">
									
									
										<logic:equal name="tipo" value="bairro">
											
											<logic:notEqual name="bairro" property="indicadorUso"
												value="<%=ConstantesSistema.INDICADOR_USO_DESATIVO.toString()%>">
												
												<logic:present name="consulta">
													<a href="javascript:enviarIdParaInserir(${bairro.id})"> 
														<bean:write name="bairro" property="nome" /> 
													</a>
												</logic:present>
												
												<logic:notPresent name="consulta">
													<a href="javascript:enviarDadosQuatroParametros('<bean:write name="bairro" property="id"/>', '<bean:write name="bairro" property="nome"/>', '<bean:write name="bairro" property="codigo"/>','bairro');">
														<bean:write name="bairro" property="nome" /> 
													</a>
												</logic:notPresent>
												
											</logic:notEqual>
											
											<logic:equal name="bairro" property="indicadorUso"
												value="<%=ConstantesSistema.INDICADOR_USO_DESATIVO.toString()%>">
												
												<logic:present name="consulta">
													<a href="javascript:enviarIdParaInserir(${bairro.id})"> 
														<font color="#CC0000"> 
															<bean:write name="bairro" property="nome" />
														</font>
													</a>
												</logic:present>
												
												<logic:notPresent name="consulta">
													<a href="javascript:enviarDadosQuatroParametros('<bean:write name="bairro" property="id"/>', '<bean:write name="bairro" property="nome"/>', '<bean:write name="bairro" property="codigo"/>','bairro');">
														<font color="#CC0000"> 
															<bean:write name="bairro" property="nome" /> 
														</font> 
													</a>
												</logic:notPresent>
												
											</logic:equal>
										
										</logic:equal>
										
										<logic:notEqual name="tipo" value="bairro">
											
											<logic:notEqual name="bairro" property="indicadorUso"
												value="<%=ConstantesSistema.INDICADOR_USO_DESATIVO.toString()%>">
												
												<logic:present name="consulta">
													<a href="javascript:enviarIdParaInserir(${bairro.id})"> 
														<bean:write name="bairro" property="nome" />
													</a>
												</logic:present>
												<logic:notPresent name="consulta">
												<a href="javascript:enviarDados('<bean:write name="bairro" property="codigo"/>', '<bean:write name="bairro" property="nome"/>', 'bairro');">
													<bean:write name="bairro" property="nome" /> 
													 
												</a>
												</logic:notPresent>
											</logic:notEqual>
											
											<logic:equal name="bairro" property="indicadorUso"
												value="<%=ConstantesSistema.INDICADOR_USO_DESATIVO.toString()%>">
												
												<logic:present name="consulta">
													<a href="javascript:enviarIdParaInserir(${bairro.id})">
														<font color="#CC0000"> 
															<bean:write name="bairro" property="nome" />
														</font>
													</a>
												</logic:present>
												
												<logic:notPresent name="consulta">
													 <a href="javascript:enviarDados('<bean:write name="bairro" property="codigo"/>', '<bean:write name="bairro" property="nome"/>', 'bairro');">
													 <font color="#CC0000">
													 <bean:write name="bairro" property="nome" /> 
													 </font>
													 
												     </a>
												</logic:notPresent>
											</logic:equal>
											
										</logic:notEqual>
										
									</logic:notPresent> 
									
									<logic:present name="tipo" scope="session">
									     <logic:equal name="tipo" value="bairro">
											<logic:notPresent name="consulta">
													<a href="javascript:enviarDadosQuatroParametros('<bean:write name="bairro" property="id"/>', '<bean:write name="bairro" property="nome"/>', '<bean:write name="bairro" property="codigo"/>','bairro');">
														<bean:write name="bairro" property="nome" /> 
													</a>
											</logic:notPresent>
										 </logic:equal>	
										<logic:notEqual name="tipo" value="bairro">	
										
										<logic:notEqual name="bairro" property="indicadorUso"
											value="<%=ConstantesSistema.INDICADOR_USO_DESATIVO.toString()%>">
												
											<logic:present name="consulta">
												<a href="javascript:enviarIdParaInserir(${bairro.id})"> 
													<bean:write name="bairro" property="nome" />
												</a>
											</logic:present>
											<logic:notPresent name="consulta">
												
												<a href="javascript:enviarDados('<bean:write name="bairro" property="codigo"/>', '<bean:write name="bairro" property="nome"/>', 'bairro');">
													 <bean:write name="bairro" property="nome" /> 
													 
												</a>
											</logic:notPresent>
										</logic:notEqual>
											
										<logic:equal name="bairro" 
											property="indicadorUso"
											value="<%=ConstantesSistema.INDICADOR_USO_DESATIVO.toString()%>">
												
											<logic:present name="consulta">
												<a href="javascript:enviarIdParaInserir(${bairro.id})">
													<font color="#CC0000"> 
														<bean:write name="bairro" property="nome" />
													</font>
												</a>
											</logic:present>
												
											<logic:notPresent name="consulta">
											    
												<a href="javascript:enviarDados('<bean:write name="bairro" property="codigo"/>', '<bean:write name="bairro" property="nome"/>', 'bairro');">
													 <font color="#CC0000">
													 <bean:write name="bairro" property="nome" /> 
													 </font>
													 
												</a>
											</logic:notPresent>
										</logic:equal>
									   </logic:notEqual>
									</logic:present>
								  </logic:notPresent>	
								</td>
								
								<td>
									<logic:notEqual name="bairro" 
										property="indicadorUso"
										value="<%=ConstantesSistema.INDICADOR_USO_DESATIVO.toString()%>">
										
										<bean:write name="bairro" property="municipio.nome" />
									</logic:notEqual> 
									
									<logic:equal name="bairro"
										property="indicadorUso"
										value="<%=ConstantesSistema.INDICADOR_USO_DESATIVO.toString()%>">
										
										<font color="#CC0000"> 
											<bean:write name="bairro" property="municipio.nome" /> 
										</font>
									</logic:equal>
								</td>
							</tr>
						</pg:item>
					</logic:iterate>
			</tr>
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
					value="Voltar Pesquisa"
					onclick="window.location.href='<html:rewrite page="/exibirPesquisarBairroAction.do?novaPesquisa=OK&objetoConsulta=1"/>'" /></td>
			</tr>
		</table>
		
		</td>
	</tr>
</table>

</body>
</html:html>
