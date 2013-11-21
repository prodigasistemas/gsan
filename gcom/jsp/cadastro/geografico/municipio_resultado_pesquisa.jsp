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
	
<script language="JavaScript">
	function enviarIdParaInserir(idMunicipio) {
		opener.redirecionarSubmitAtualizar(idMunicipio);
		self.close();
	}
</script>	
	
</head>

<body leftmargin="5" topmargin="5" onload="resizePageSemLink(810, 420);">
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
				<td class="parabg">Pesquisa de Município</td>
				<td width="11"><img border="0"
					src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
			</tr>
		</table>
		<p>&nbsp;</p>
		<table width="100%" border="0" bgcolor="#99CCFF">
			<tr align="left" bgcolor="#90c7fc">
				<td width="13%" align="center"><strong>C&oacute;digo</strong></td>
				<td width="37%" align="center"><strong>Nome</strong></td>
				<td width="25%" align="center"><strong>Microrregi&atilde;o</strong></td>
				<td width="25%" align="center"><strong>Regi&atilde;o</strong></td>
			</tr>
			<%--Esquema de paginação--%>
				<pg:pager isOffset="true" index="half-full" maxIndexPages="10"
				export="currentPageNumber=pageNumber;pageOffset"
				maxPageItems="10" items="${sessionScope.totalRegistros}">
				<pg:param name="q" />

					<%int cont = 0;%>
						<logic:iterate name="colecaoMunicipios" id="municipio">
							<pg:item>

							<%cont = cont + 1;
							if (cont % 2 == 0) {%>
								<tr bgcolor="#cbe5fe">								
							<%} else {	%>
								<tr bgcolor="#FFFFFF">
							<%}%>

							<td align="center">
								<logic:notEqual name="municipio" property="indicadorUso" value="<%=ConstantesSistema.INDICADOR_USO_DESATIVO.toString()%>">
									<bean:write name="municipio" property="id" />
								</logic:notEqual>
								<logic:equal name="municipio" property="indicadorUso" value="<%=ConstantesSistema.INDICADOR_USO_DESATIVO.toString()%>">
									<font color="#CC0000">
										<bean:write name="municipio" property="id" />
									</font>
								</logic:equal>
							</td>
							<td ><logic:notEmpty
								name="caminhoRetornoTelaPesquisaMunicipio">
								<logic:notEqual name="caminhoRetornoTelaPesquisaMunicipio"
									value="exibirInserirClienteTelefoneAction">
									<logic:notEqual name="caminhoRetornoTelaPesquisaMunicipio"
										value="exibirAtualizarClienteTelefoneAction">
										<logic:notEqual name="municipio" property="indicadorUso" value="<%=ConstantesSistema.INDICADOR_USO_DESATIVO.toString()%>">            
											<a href="javascript:enviarDadosParametros('<bean:write name="caminhoRetornoTelaPesquisaMunicipio"/>', '<bean:write name="municipio" property="id"/>', '<bean:write name="municipio" property="nome"/>', 'municipio');">
												<bean:write name="municipio" property="nome" />
											</a>
							            </logic:notEqual>
							            <logic:equal name="municipio" property="indicadorUso" value="<%=ConstantesSistema.INDICADOR_USO_DESATIVO.toString()%>">            
											<a href="javascript:enviarDadosParametros('<bean:write name="caminhoRetornoTelaPesquisaMunicipio"/>', '<bean:write name="municipio" property="id"/>', '<bean:write name="municipio" property="nome"/>', 'municipio');">
												<font color="#CC0000"> 
													<bean:write name="municipio" property="nome" /> 
												</font>
											</a>
							            </logic:equal>
									</logic:notEqual>
								</logic:notEqual>

								<logic:equal name="caminhoRetornoTelaPesquisaMunicipio"
									value="exibirInserirClienteTelefoneAction">
										<logic:notEqual name="municipio" property="indicadorUso" value="<%=ConstantesSistema.INDICADOR_USO_DESATIVO.toString()%>">            
											<a href="javascript:enviarDadosMunicipioDdd('<bean:write name="municipio" property="id"/>','<bean:write name="municipio" property="nome"/>',<bean:write name="municipio" property="ddd"/>,'municipio')">
												<bean:write name="municipio" property="nome" /> 
											</a>
							            </logic:notEqual>
							            <logic:equal name="municipio" property="indicadorUso" value="<%=ConstantesSistema.INDICADOR_USO_DESATIVO.toString()%>">            
											<a  href="javascript:enviarDadosMunicipioDdd('<bean:write name="municipio" property="id"/>','<bean:write name="municipio" property="nome"/>',<bean:write name="municipio" property="ddd"/>,'municipio')">
												<font color="#CC0000"> 
													<bean:write name="municipio" property="nome" /> 
												</font>
											</a>
							            </logic:equal>
								</logic:equal>

								<logic:equal name="caminhoRetornoTelaPesquisaMunicipio"
									value="exibirAtualizarClienteTelefoneAction">
										<logic:notEqual name="municipio" property="indicadorUso" value="<%=ConstantesSistema.INDICADOR_USO_DESATIVO.toString()%>">            
											<a href="javascript:enviarDadosMunicipioDdd('<bean:write name="municipio" property="id"/>','<bean:write name="municipio" property="nome"/>',<bean:write name="municipio" property="ddd"/>,'municipio')">
												<bean:write name="municipio" property="nome" /> 
											</a>
							            </logic:notEqual>
							            <logic:equal name="municipio" property="indicadorUso" value="<%=ConstantesSistema.INDICADOR_USO_DESATIVO.toString()%>">            
											<a href="javascript:enviarDadosMunicipioDdd('<bean:write name="municipio" property="id"/>','<bean:write name="municipio" property="nome"/>',<bean:write name="municipio" property="ddd"/>,'municipio')">
												<font color="#CC0000"> 
													<bean:write name="municipio" property="nome" /> 
												</font>
											</a>
										</logic:equal>
								</logic:equal>



							</logic:notEmpty> 
							<logic:empty name="caminhoRetornoTelaPesquisaMunicipio">
								<logic:notEqual name="municipio" property="indicadorUso" value="<%=ConstantesSistema.INDICADOR_USO_DESATIVO.toString()%>">            
									<logic:present name="consulta">
										<a href="javascript:enviarIdParaInserir(${municipio.id})"> 
											<bean:write	name="municipio" property="nome" /> 
										</a>
									</logic:present>
				  
				  					<logic:notPresent name="consulta">
										<a href="javascript:enviarDados('<bean:write name="municipio" property="id"/>', '<bean:write name="municipio" property="nome"/>', 'municipio');">
											<bean:write name="municipio" property="nome"/>
										</a>
									</logic:notPresent>   
									
					            </logic:notEqual>
							    <logic:equal name="municipio" property="indicadorUso" value="<%=ConstantesSistema.INDICADOR_USO_DESATIVO.toString()%>">            
							    	<logic:present name="consulta">
										<a href="javscript:enviarIdParaInserir(${municipio.id})">
											<font color="#CC0000"> 
												 <bean:write name="municipio" property="nome" /> 
											</font>
										</a>
								  	</logic:present>
				  
								  	<logic:notPresent name="consulta">
										<a href="javascript:enviarDados('<bean:write name="municipio" property="id"/>', '<bean:write name="municipio" property="nome"/>', 'municipio');">
											<font color="#CC0000">
												<bean:write name="municipio" property="nome"/>
											</font>
										</a>
								  	</logic:notPresent>   
								  
							    </logic:equal>
							</logic:empty>
						</td>
						<td>
							<logic:notEqual name="municipio" property="indicadorUso" value="<%=ConstantesSistema.INDICADOR_USO_DESATIVO.toString()%>">            
								<bean:write name="municipio" property="microrregiao.nome" />
						    </logic:notEqual>
						    <logic:equal name="municipio" property="indicadorUso" value="<%=ConstantesSistema.INDICADOR_USO_DESATIVO.toString()%>">            
								<font color="#CC0000"> 
									<bean:write name="municipio" property="microrregiao.nome" />
								</font>
						    </logic:equal>
						</td>
						<td>
							<logic:notEqual name="municipio" property="indicadorUso" value="<%=ConstantesSistema.INDICADOR_USO_DESATIVO.toString()%>">            
								<bean:write name="municipio" property="microrregiao.regiao.nome" />
						    </logic:notEqual>
						    <logic:equal name="municipio" property="indicadorUso" value="<%=ConstantesSistema.INDICADOR_USO_DESATIVO.toString()%>">            
								<font color="#CC0000"> 
									<bean:write name="municipio" property="microrregiao.regiao.nome" />
								</font>
						    </logic:equal>
						</td>
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
				</table>
		<table width="100%" border="0">
			<tr>
				<td height="24"><input type="button" class="bottonRightCol"
					value="Voltar Pesquisa"
					onclick="window.location.href='<html:rewrite page="/exibirPesquisarMunicipioAction.do?objetoConsulta=1"/>'" /></td>
			</tr>
		</table>
	  </td>
	</tr>
</table>

</body>
</html:html>
