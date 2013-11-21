<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>

<%@ page import="gcom.util.Pagina,gcom.util.ConstantesSistema"%>
<%@ page import="gcom.cadastro.endereco.Logradouro"%>
<%@ page import="java.util.Collection"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key='caminho.css'/>EstilosCompesa.css"
	type="text/css">

<script language="JavaScript"
	src="<bean:message key='caminho.js'/>util.js"></script>

<script language="JavaScript">
function enviarIdParaInserir(idLogradouro) {
	opener.redirecionarSubmitAtualizar(idLogradouro);
	self.close();
}
</script>

</head>

<body leftmargin="0" topmargin="0" onload="resizePageSemLink(760, 400);">
<table width="710" border="0" cellpadding="0" cellspacing="5">
	<tr>
		<td width="710" valign="top" class="centercoltext">
		<table height="100%">
			<tr>
				<td></td>
			</tr>
		</table>
		<table width="710" border="0" align="center" cellpadding="0"
			cellspacing="0">
			<tr>
				<td width="11"><img
					src="<bean:message key='caminho.imagens'/>parahead_left.gif"
					editor="Webstyle4"
					moduleid="Album Photos (Project)\toptab_page2_parahead_left.xws"
					border="0" /></td>
				<td class="parabg">Pesquisar Logradouro</td>
				<td width="11"><img
					src="<bean:message key='caminho.imagens'/>parahead_right.gif"
					editor="Webstyle4"
					moduleid="Album Photos (Project)\toptab_page2_parahead_right.xws"
					border="0" /></td>
			</tr>
		</table>
		<p>&nbsp;</p>
		<table width="710" cellpadding="0" cellspacing="0">
			<tr>
				<td>
				<table width="710" bgcolor="#99CCFF">
					<tr bgcolor="#99CCFF">
						<td width="310" align="center"><strong>Nome Logradouro</strong></td>
						<td width="310" align="center"><strong>Nome Popular</strong></td>
						<td width="200" align="center"><strong>Município</strong></td>

					</tr>
					<%--Esquema de paginação--%>
					<pg:pager isOffset="true" index="half-full" maxIndexPages="10"
						export="currentPageNumber=pageNumber;pageOffset" maxPageItems="10"
						items="${sessionScope.totalRegistros}">
						<pg:param name="q" />

						<%int cont = 0;%>
						<logic:iterate id="logradouro" name="logradouros">
							<pg:item>
								<%cont = cont + 1;
			if (cont % 2 == 0) {%>
								<tr bgcolor="#cbe5fe">
									<%} else {

			%>
								<tr bgcolor="#FFFFFF">
									<%}%>
									<td width="330"><logic:notEmpty
										name="caminhoRetornoTelaPesquisaLogradouro">
										<logic:notEqual name="logradouro" property="indicadorUso"
											value="<%=ConstantesSistema.INDICADOR_USO_DESATIVO.toString()%>">
											<logic:present name="consultaLogradouro">
												<a href="javascript:enviarIdParaInserir(${logradouro.id})">${logradouro.descricaoFormatada}&nbsp;
												</a>
											</logic:present>
											<logic:notPresent name="consultaLogradouro">
												<a
													href="javascript:enviarDadosParametros('<bean:write name="caminhoRetornoTelaPesquisaLogradouro"/>', '<bean:write name="logradouro" property="id"/>', '<bean:write name="logradouro" property="descricaoFormatada"/>', 'logradouro');">
												${logradouro.descricaoFormatada}&nbsp;</a>
											</logic:notPresent>
										</logic:notEqual>
										<logic:equal name="logradouro" property="indicadorUso"
											value="<%=ConstantesSistema.INDICADOR_USO_DESATIVO.toString()%>">
											<logic:present name="consultaLogradouro">
												<a href="javascript:enviarIdParaInserir(${logradouro.id})">
												<font color="#CC0000">
												${logradouro.descricaoFormatada}&nbsp; </font></a>
											</logic:present>
											<logic:notPresent name="consultaLogradouro">
												<a
													href="javascript:enviarDadosParametros('<bean:write name="caminhoRetornoTelaPesquisaLogradouro"/>', '<bean:write name="logradouro" property="id"/>', '<bean:write name="logradouro" property="descricaoFormatada"/>', 'logradouro');">
												<font color="#CC0000">
												${logradouro.descricaoFormatada}&nbsp; </font> </a>
											</logic:notPresent>
										</logic:equal>
									</logic:notEmpty> <logic:empty
										name="caminhoRetornoTelaPesquisaLogradouro">
										<logic:notEqual name="logradouro" property="indicadorUso"
											value="<%=ConstantesSistema.INDICADOR_USO_DESATIVO.toString()%>">
											<logic:present name="consultaLogradouro">
												<a href="javascript:enviarIdParaInserir(${logradouro.id})">${logradouro.descricaoFormatada}&nbsp;
												</a>
											</logic:present>
											<logic:notPresent name="consultaLogradouro">
												<a
													href="javascript:enviarDados('<bean:write name="logradouro" property="id"/>', '<bean:write name="logradouro" property="descricaoFormatada"/>', 'logradouro');">
												${logradouro.descricaoFormatada}&nbsp;</a>
											</logic:notPresent>
										</logic:notEqual>
										<logic:equal name="logradouro" property="indicadorUso"
											value="<%=ConstantesSistema.INDICADOR_USO_DESATIVO.toString()%>">
											<logic:present name="consultaLogradouro">
												<a href="javascript:enviarIdParaInserir(${logradouro.id})">
												<font color="#CC0000">
												${logradouro.descricaoFormatada}&nbsp; </font></a>
											</logic:present>
											<logic:notPresent name="consultaLogradouro">
												<a
													href="javascript:enviarDados('<bean:write name="logradouro" property="id"/>', '<bean:write name="logradouro" property="descricaoFormatada"/>', 'logradouro');">
												<font color="#CC0000">
												${logradouro.descricaoFormatada}&nbsp; </font> </a>
											</logic:notPresent>
										</logic:equal>
									</logic:empty></td>
									<td width="140"><logic:equal name="tipoReultado"
										value="logradouro">
										<logic:notEqual name="logradouro" property="indicadorUso"
											value="<%=ConstantesSistema.INDICADOR_USO_DESATIVO.toString()%>">            
																${logradouro.nomePopular}&nbsp;
												            </logic:notEqual>
										<logic:equal name="logradouro" property="indicadorUso"
											value="<%=ConstantesSistema.INDICADOR_USO_DESATIVO.toString()%>">
											<font color="#CC0000"> ${logradouro.nomePopular}&nbsp; </font>
										</logic:equal>
									</logic:equal> &nbsp;</td>
									<td width="140"><logic:equal name="tipoReultado"
										value="logradouro">
										<logic:present name="logradouro" property="municipio">
											<logic:notEmpty name="logradouro" property="municipio">
												<logic:notEqual name="logradouro" property="indicadorUso"
													value="<%=ConstantesSistema.INDICADOR_USO_DESATIVO.toString()%>">            
																${logradouro.municipio.nome}&nbsp;
												            </logic:notEqual>
												<logic:equal name="logradouro" property="indicadorUso"
													value="<%=ConstantesSistema.INDICADOR_USO_DESATIVO.toString()%>">
													<font color="#CC0000"> ${logradouro.municipio.nome}&nbsp; </font>
												</logic:equal>
											</logic:notEmpty>

											<logic:empty name="logradouro" property="municipio">
															&nbsp;
														</logic:empty>
										</logic:present>
									</logic:equal> &nbsp;</td>
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
			<tr>
				<td><INPUT type="button" class="bottonRightCol"
					value="Voltar Pesquisa" tabindex="1"
					onclick="window.location.href='/gsan/exibirPesquisarLogradouroAction.do?objetoConsulta=1'">
				</td>
			</tr>
		</table>

		</td>
	</tr>

</table>
</body>

</html:html>

