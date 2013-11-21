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
			
			<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
				<tr>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_left.gif" /></td>
					<td class="parabg">Pesquisa de Usuário</td>
	
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
				
				<tr bordercolor="#000000">
				
					<td width="40%" align="center" bgcolor="#90c7fc">
						<strong>Nome do Usuário</strong>
					</td>
					<td width="35%" align="center" bgcolor="#90c7fc">
						<strong>Tipo do Usuário</strong>
					</td>
					<td width="25%" align="center" bgcolor="#90c7fc">
						<strong>Matrícula do Funcionário</strong>
					</td>
				</tr>
				
				<tr>
					<td colspan="6">
					
					<table width="100%" bgcolor="#99CCFF">

					<%--Esquema de paginação--%>
					<pg:pager isOffset="true" 
						index="half-full" 
						maxIndexPages="10"
						export="currentPageNumber=pageNumber;pageOffset" 
						maxPageItems="10"
						items="${sessionScope.totalRegistros}">
						
						<pg:param name="q" />
						
							<logic:present name="collectionUsuario">
							<%	int cont = 1;	%>
								<logic:iterate name="collectionUsuario" id="usuario">
									<pg:item>
									<%	cont = cont + 1;
										if (cont % 2 == 0) {	%>
											<tr bgcolor="#FFFFFF">
									<%	} else {	%>
											<tr bgcolor="#cbe5fe">
									<%	}	%>

										<logic:notEmpty name="caminhoRetornoTelaPesquisaUsuario">
										
											<td>											
												<logic:notEmpty name="mostrarLogin" scope="session">
													
													<logic:present name="popup">
														<a href="javascript:enviarDados( '<bean:write name="usuario" property="login"/>', '<bean:write name="usuario" property="nomeUsuario"/>', 'usuario');">
															<font style="text-transform: uppercase;"> 
																<bean:write name="usuario" property="nomeUsuario" />
															</font>
														</a>
													</logic:present>
													
													<logic:notPresent name="popup">
														<a href="javascript:enviarDadosParametros('<bean:write name="caminhoRetornoTelaPesquisaUsuario"/>', '<bean:write name="usuario" property="login"/>', '<bean:write name="usuario" property="nomeUsuario"/>', 'usuario');">
															<font style="text-transform: uppercase;"> 
																<bean:write name="usuario" property="nomeUsuario" />
															</font>
														</a>
													</logic:notPresent>
													
												</logic:notEmpty>
												<logic:empty name="mostrarLogin" scope="session">
													<logic:present name="popup">
														<a href="javascript:enviarDados('<bean:write name="usuario" property="id"/>', '<bean:write name="usuario" property="nomeUsuario"/>', 'usuario');">
															<font style="text-transform: uppercase;"> 
																<bean:write name="usuario" property="nomeUsuario" />
															</font>
														</a>
													</logic:present>	
													<logic:notPresent name="popup">
														<a href="javascript:enviarDadosParametros('<bean:write name="caminhoRetornoTelaPesquisaUsuario"/>', '<bean:write name="usuario" property="id"/>', '<bean:write name="usuario" property="nomeUsuario"/>', 'usuario');">
															<font style="text-transform: uppercase;"> 
																<bean:write name="usuario" property="nomeUsuario" />
															</font>
														</a>
													</logic:notPresent>
												</logic:empty>	
											</td>												
											
										</logic:notEmpty>
										
										<logic:empty name="caminhoRetornoTelaPesquisaUsuario">
											
											<td width="40%">											
												
												<logic:notEmpty name="mostrarLogin" scope="session">
													<a href="javascript:enviarDados('<bean:write name="usuario" property="login"/>', '<bean:write name="usuario" property="nomeUsuario"/>', 'usuario');">
														<font style="text-transform: uppercase;"> 
															<bean:write name="usuario" property="nomeUsuario" />
														</font>
													</a>
												</logic:notEmpty>
												
												<logic:empty name="mostrarLogin" scope="session">
													<a href="javascript:enviarDados('<bean:write name="usuario" property="id"/>', '<bean:write name="usuario" property="nomeUsuario"/>', 'usuario');">
														<font style="text-transform: uppercase;"> 
															<bean:write name="usuario" property="nomeUsuario" />
														</font>
													</a>
												</logic:empty>	
											</td>										
											
										</logic:empty>
										
										<td width="35%">
											<div>${usuario.usuarioTipo.descricao} &nbsp;</div>
										</td>
										
										<td width="25%" align="center">
											<div>${usuario.funcionario.id} &nbsp;</div>
										</td>
									</tr>
									</pg:item>
								</logic:iterate>
							</logic:present>
					</table>
				</td>
			</tr>

		</table>
		<table width="100%" border="0">
			<tr>
				<td>
				<div align="center"><strong><%@ include
					file="/jsp/util/indice_pager_novo.jsp"%></strong></div>
				</td>
				</pg:pager>
			</tr>
			<tr>
				<td height="24">
					<input type="button" 
						class="bottonRightCol"
						value="Voltar Pesquisa"
						onclick="window.location.href='<html:rewrite page="/exibirUsuarioPesquisar.do"/>'" />
				</td>
			</tr>
		</table>
		</td>
	</tr>
</table>
</body>
</html:html>
