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
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="PesquisarUnidadeSuperiorActionForm"
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
		<table width="100%" border="0" align="center" cellpadding="0"
			cellspacing="0">
			<tr>
				<td width="11"><img border="0"
					src="<bean:message key="caminho.imagens"/>parahead_left.gif" /></td>
				<td class="parabg">Pesquisa de Unidades Organizacionais</td>

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
				<td colspan="6"></td>
			</tr>

			<tr bordercolor="#000000">
				<td width="15%" align="center" bgcolor="#90c7fc"><strong>Tipo da Unidade</strong></td>
				<td width="10%" align="center" bgcolor="#90c7fc"><strong>N&iacute;vel Hier&aacute;rquico:</strong></td>
				<td width="30%" align="center" bgcolor="#90c7fc"><strong>Descri&ccedil;&atilde;o</strong></td>
				<td width="15%" align="center" bgcolor="#90c7fc"><strong>Abre RA?</strong></td>
				<td width="15%" align="center" bgcolor="#90c7fc"><strong>Aceita Tramite?</strong></td>				
			</tr>
			<tr>
				<td colspan="6">
				<table width="100%" bgcolor="#99CCFF">

					<%--Esquema de paginação--%>
					<pg:pager isOffset="true" index="half-full" maxIndexPages="10"
							export="currentPageNumber=pageNumber;pageOffset"
							maxPageItems="10" items="${sessionScope.totalRegistros}">
							<pg:param name="pg" />
							<pg:param name="q" />
						<logic:present name="colecaoUnidadeOrganizacionalSuperior">
							<%int cont = 1;%>
							<logic:iterate name="colecaoUnidadeOrganizacionalSuperior"
								id="unidadeOrganizacional">
								<pg:item>
							<%	cont = cont + 1;
									if (cont % 2 == 0) {	%>
									<tr bgcolor="#FFFFFF">
								<%	} else {	%>
									<tr bgcolor="#cbe5fe">
								<%	}	%>
									


									<logic:notEmpty name="caminhoRetornoTelaPesquisa">
										<td width="15%">
											<div align="center">
												<a href="javascript:enviarDadosParametros('<bean:write name="caminhoRetornoTelaPesquisa"/>','<bean:write name="unidadeOrganizacional" property="id"/>', '<bean:write name="unidadeOrganizacional" property="descricao"/>', 'unidadeSuperior');">
													<bean:write name="unidadeOrganizacional" property="descricao" />
												</a>
											</div>
										</td>
									</logic:notEmpty>
		
									<logic:empty name="caminhoRetornoTelaPesquisa">
											<td width="30%">
												<a href="javascript:enviarDados('<bean:write name="unidadeOrganizacional" property="id"/>', '<bean:write name="unidadeOrganizacional" property="descricao"/>', 'unidadeSuperior');">
													<bean:write name="unidadeOrganizacional" property="descricao" />
												</a>
											</td>
									</logic:empty>

									<td width="10%">
										<div align="left">
											<bean:write name="unidadeOrganizacional" property="unidadeTipo.nivel"/>
										</div>
												
									</td>
									
									<td width="30%">
										<bean:write name="unidadeOrganizacional" property="descricao" />
									</td>
									
									<td width="15%">
									
										<logic:equal name="unidadeOrganizacional" property="indicadorAberturaRa"
											value="<%=ConstantesSistema.INDICADOR_USO_ATIVO.toString()%>">
											Sim
										</logic:equal>

										<logic:equal name="unidadeOrganizacional" property="indicadorAberturaRa"
											value="<%=ConstantesSistema.INDICADOR_USO_DESATIVO.toString()%>">
											N&atilde;o
										</logic:equal>

									</td>

									<td width="15%">

										<logic:equal name="unidadeOrganizacional" property="indicadorTramite"
											value="<%=ConstantesSistema.INDICADOR_USO_ATIVO.toString()%>">
											Sim
										</logic:equal>

										<logic:equal name="unidadeOrganizacional" property="indicadorTramite"
											value="<%=ConstantesSistema.INDICADOR_USO_DESATIVO.toString()%>">
											N&atilde;o
										</logic:equal>

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
					<div align="center">
						<strong><%@ include
						file="/jsp/util/indice_pager_novo.jsp"%>
						</strong>
					</div>
				</td>
			</tr>
			<tr>
				<td height="24"><input type="button" class="bottonRightCol"
					value="Voltar Pesquisa"
					onclick="window.location.href='/gsan/exibirPesquisarUnidadeSuperiorAction.do'"/></td>
			</tr>
		</table>
		</pg:pager></td>
	</tr>

</table>

</body>

</html:html>
