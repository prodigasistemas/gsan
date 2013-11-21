<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>

<%@ page import="gcom.util.ConstantesSistema"%>

<html:html>
<head>
<title>GSAN - Sistema Integrado de Gest&atilde;o de Servi&ccedil;os de Saneamento</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="PesquisarActionForm"	dynamicJavascript="false" />
<script language="JavaScript">
// A função redirecionarSubmitAtualizar é definida dentro do cliente_inserir
function enviarIdParaInserir(idCliente) {
	opener.redirecionarSubmitAtualizar(idCliente);
	self.close();
}

//FUNCAO COM MAIS PARAMETROS. NECESSARIO NA BUSCA DO FILTRO CLIENTE EM INSERIR/MANTER CONTRATO PARCELAMENTO POR CLIENTE.
function enviarDadosConsultaContrParcel(codigoRegistro, descricaoRegistro, cnpj, tipoConsulta) {
	   opener.recuperarDadosPopupClienteContrParcel(codigoRegistro, descricaoRegistro, cnpj, tipoConsulta);
	   self.close();
	}
	

function getURLParameter( name )
{
  name = name.replace(/[\[]/,"\\\[").replace(/[\]]/,"\\\]");
  var regexS = "[\\?&]"+name+"=([^&#]*)";
  var regex = new RegExp( regexS );
  var results = regex.exec( window.location.href );
  if( results == null )
    return "";
  else
    return results[1];
}
</script>
</head>
<body leftmargin="5" topmargin="5"
	onload="javascript:resizePageSemLink(810, 405)">

<table width="770" border="0" cellspacing="5" cellpadding="0">
	<tr>
		<td width="770" valign="top" class="centercoltext">
		<table height="100%">
			<tr>
				<td></td>
			</tr>
		</table>
		<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
			<tr>
				<td width="11">
					<img border="0" src="<bean:message key="caminho.imagens"/>parahead_left.gif" />
				</td>
				<td class="parabg">Pesquisa de Cliente</td>
				<td width="11">
					<img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif" />
				</td>
			</tr>
		</table>
		<p>&nbsp;</p>
		<table width="100%" align="center" bgcolor="#99CCFF">
			<tr align="left" height="23" bgcolor="#90c7fc">
				<td width="8%" align="center">
					<strong>C&oacute;digo</strong>
				</td>
				<td width="29%" align="center">
					<strong>Nome</strong>
				</td>
				<td width="26%" align="center">
					<strong>Tipo Cliente</strong>
				</td>
				<td width="15%" align="center">
					<strong>CPF</strong>
				</td>
				<td width="22%" align="center">
					<strong>CNPJ</strong>
				</td>
			</tr>
				<%--Esquema de paginação--%>
				<pg:pager isOffset="true" index="half-full" maxIndexPages="10"
					export="currentPageNumber=pageNumber;pageOffset"				
					maxPageItems="10" items="${sessionScope.totalRegistros}">
				<pg:param name="pg" />
				<pg:param name="q" />
				<%int cont = 0;%>
				<logic:present name="colecaoCliente">
				<logic:iterate name="colecaoCliente" id="cliente">
					<pg:item>
						<%cont = cont + 1;
						if (cont % 2 == 0) {%>
						<tr bgcolor="#cbe5fe">
						<%} else {%>
						<tr bgcolor="#FFFFFF">
						<%}%>
							<td align="center">
								<logic:notEqual name="cliente" property="indicadorUso" value="<%=ConstantesSistema.INDICADOR_USO_DESATIVO.toString()%>">            
									<bean:write name="cliente" property="id" />
					            </logic:notEqual>
					            <logic:equal name="cliente" property="indicadorUso" value="<%=ConstantesSistema.INDICADOR_USO_DESATIVO.toString()%>">            
									<font color="#CC0000"> 
										<bean:write name="cliente" property="id" />
									</font>
					            </logic:equal>
							</td>
							<td>
								<logic:present name="consultaCliente">
									<a href="javascript:enviarIdParaInserir(${cliente.id})">
										<bean:write name="cliente" property="nome" /></a>
								</logic:present>
				
								<logic:notPresent name="consultaCliente">									
									<logic:present name="caminhoRetornoTelaPesquisaCliente" scope="session" >
										<logic:notEqual name="cliente" property="indicadorUso" value="<%=ConstantesSistema.INDICADOR_USO_DESATIVO.toString()%>">            
											<a href="javascript:enviarDadosParametros('<bean:write name="caminhoRetornoTelaPesquisaCliente" scope="session"  />','<bean:write name="cliente" property="id"/>', 
												'<bean:write name="cliente" property="nome"/>', 'cliente');">
												<bean:write name="cliente" property="nome" /> 
											</a>
							            </logic:notEqual>
							            <logic:equal name="cliente" property="indicadorUso" value="<%=ConstantesSistema.INDICADOR_USO_DESATIVO.toString()%>">            
											<a href="javascript:enviarDadosParametros('<bean:write name="caminhoRetornoTelaPesquisaCliente" scope="session"  />','<bean:write name="cliente" property="id"/>', 
												'<bean:write name="cliente" property="nome"/>', 'cliente');">
												<font color="#CC0000"> 
													<bean:write name="cliente" property="nome" /> 
												</font>
											</a>
							            </logic:equal>
									</logic:present> 
									<logic:notPresent name="caminhoRetornoTelaPesquisaCliente" scope="session" >
										<c:choose>
											<c:when test="${tipoConsulta != null}">
												<logic:notEqual name="cliente" property="indicadorUso" value="<%=ConstantesSistema.INDICADOR_USO_DESATIVO.toString()%>">            
													<a href="javascript:enviarDadosConsultaContrParcel('<bean:write name="cliente" property="id"/>', '<bean:write name="cliente" property="nome"/>', '<c:out value="${cliente.cnpjFormatado}"></c:out>', '<c:out value="${tipoConsulta}"></c:out>');">
														<bean:write name="cliente" property="nome" /> 
													</a>
									            </logic:notEqual>
									            <logic:equal name="cliente" property="indicadorUso" value="<%=ConstantesSistema.INDICADOR_USO_DESATIVO.toString()%>">            
													<a href="javascript:enviarDadosConsultaContrParcel('<bean:write name="cliente" property="id"/>', '<bean:write name="cliente" property="nome"/>', '<c:out value="${cliente.cnpjFormatado}"></c:out>', '<c:out value="${tipoConsulta}"></c:out>');">
														<font color="#CC0000"> 
															<bean:write name="cliente" property="nome" /> 
														</font>
													</a>
									            </logic:equal>
											</c:when>
											<c:otherwise>
												<logic:notEqual name="cliente" property="indicadorUso" value="<%=ConstantesSistema.INDICADOR_USO_DESATIVO.toString()%>">            
													<a href="javascript:enviarDados('<bean:write name="cliente" property="id"/>', '<bean:write name="cliente" property="nome"/>', 'cliente');">
														<bean:write name="cliente" property="nome" /> 
													</a>
									            </logic:notEqual>
									            <logic:equal name="cliente" property="indicadorUso" value="<%=ConstantesSistema.INDICADOR_USO_DESATIVO.toString()%>">            
													<a href="javascript:enviarDados('<bean:write name="cliente" property="id"/>', '<bean:write name="cliente" property="nome"/>', 'cliente');">
														<font color="#CC0000"> 
															<bean:write name="cliente" property="nome" /> 
														</font>
													</a>
									            </logic:equal>
											</c:otherwise>
										</c:choose>
									</logic:notPresent>
								</logic:notPresent>
							</td>
							<td>
								<logic:notEqual name="cliente" property="indicadorUso" value="<%=ConstantesSistema.INDICADOR_USO_DESATIVO.toString()%>">            
									<bean:write name="cliente" property="clienteTipo.descricao" />
					            </logic:notEqual>
					            <logic:equal name="cliente" property="indicadorUso" value="<%=ConstantesSistema.INDICADOR_USO_DESATIVO.toString()%>">            
									<font color="#CC0000"> 
										<bean:write name="cliente" property="clienteTipo.descricao" />
									</font>
					            </logic:equal>
							</td>
							<td align="right">
								<logic:notEmpty name="cliente" property="cpf">
									<logic:notEqual name="cliente" property="indicadorUso" value="<%=ConstantesSistema.INDICADOR_USO_DESATIVO.toString()%>">            
										<bean:write name="cliente" property="cpfFormatado" />
						            </logic:notEqual>
						            <logic:equal name="cliente" property="indicadorUso" value="<%=ConstantesSistema.INDICADOR_USO_DESATIVO.toString()%>">            
										<font color="#CC0000"> 
											<bean:write name="cliente" property="cpfFormatado" />
										</font>
						            </logic:equal>
								</logic:notEmpty>
								<logic:empty name="cliente" property="cpf">
									&nbsp;
					            </logic:empty>
					        </td>
							<td align="right">
								<logic:notEmpty name="cliente" property="cnpj">
									<logic:notEqual name="cliente" property="indicadorUso" value="<%=ConstantesSistema.INDICADOR_USO_DESATIVO.toString()%>">            
										<bean:write name="cliente" property="cnpjFormatado" />
						            </logic:notEqual>
						            <logic:equal name="cliente" property="indicadorUso" value="<%=ConstantesSistema.INDICADOR_USO_DESATIVO.toString()%>">            
										<font color="#CC0000"> 
											<bean:write name="cliente" property="cnpjFormatado" />
										</font>
						            </logic:equal>
								</logic:notEmpty>
								<logic:empty name="cliente" property="cnpj">
							    	&nbsp;
					            </logic:empty>
					        </td>
						</tr>
					</pg:item>
				</logic:iterate>
			</logic:present>
		</table>
		<table width="100%" border="0">
			<tr>
				<td align="center">
					<strong><%@ include file="/jsp/util/indice_pager_novo.jsp"%></strong>
				</td>
			</tr>
			<tr>
				<td height="24">
					<input type="button" class="bottonRightCol" value="Voltar Pesquisa"
					onclick="window.location.href='<html:rewrite page="/exibirPesquisarClienteAction.do?voltarPesquisa=1"/>'" />
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