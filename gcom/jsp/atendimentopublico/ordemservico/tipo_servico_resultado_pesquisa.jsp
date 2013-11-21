<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>


<html:html>
<head>
<title>GSAN - Sistema Integrado de Gest&atilde;o de Servi&ccedil;os de Saneamento</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="PesquisarTipoServicoActionForm" dynamicJavascript="false" />
</head>
<body leftmargin="0" topmargin="0" onload="resizePageSemLink(800, 300);">

<html:form action="/pesquisarTipoServicoAction.do"
	name="PesquisarTipoServicoActionForm"
	type="gcom.gui.atendimentopublico.ordemservico.PesquisarTipoServicoActionForm"
	method="post">

<table width="100%" border="0" cellspacing="5" cellpadding="5">
	<tr>
		<td width="100%" valign="top" class="centercoltext">

		<table width="100%" border="0" align="center" cellpadding="0"
			cellspacing="0">
			<tr>
				<td width="11"><img border="0"
					src="<bean:message key="caminho.imagens"/>parahead_left.gif" /></td>
				<td class="parabg">Pesquisa de Tipo de Serviço</td>

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
				<td colspan="7"></td>
			</tr>
			
			<tr bordercolor="#000000">
				<td width="6%" align="center" bgcolor="#90c7fc"><strong>Código</strong></td>
				<td width="30%" align="center" bgcolor="#90c7fc"><strong>Descrição do Tipo de Serviço</strong></td>
				<td width="12%" align="center" bgcolor="#90c7fc"><strong>Prioridade do Tipo de Serviço</strong></td>
				<td width="15%" align="center" bgcolor="#90c7fc"><strong>Atualização do Comercial</strong></td>
				<%-- <td width="11%" align="center" bgcolor="#90c7fc"><strong>Indicadores Com Pavimentação</strong></td> --%>
				
				<td width="9%" align="center" bgcolor="#90c7fc"><strong>Pavimentação Rua</strong></td>
				<td width="9%" align="center" bgcolor="#90c7fc"><strong>Pavimentação Calçada</strong></td>
				
				<td width="11%" align="center" bgcolor="#90c7fc"><strong>Pode Ser Terceirizado</strong></td>
			</tr>
			<tr>
				<td colspan="7">
				<table width="100%" bgcolor="#99CCFF">
					<%--Esquema de paginaÃ§Ã£o--%>
					<pg:pager isOffset="true" index="half-full" maxIndexPages="10"
							  export="currentPageNumber=pageNumber;pageOffset"
				             maxPageItems="10" items="${sessionScope.totalRegistros}">
					<pg:param name="q" />
					<pg:param name="pg"/>
					<logic:present name="servicosTipo">
					<%int cont = 0;%>
			        <logic:iterate name="servicosTipo" id="tipoServico">
						<pg:item>
							<%cont = cont + 1;
							if (cont % 2 == 0) {%>
								<tr bgcolor="#cbe5fe">								
							<%} else {	%>
								<tr bgcolor="#FFFFFF">
							<%}%>
								<logic:notPresent name="tipo">
									<logic:notEmpty name="caminhoRetornoTelaPesquisaTipoServico">
										<td width="6%">
											<div align="center">
												<a href="javascript:enviarDadosParametros('<bean:write name="caminhoRetornoTelaPesquisaTipoServico"/>','<bean:write name="tipoServico" property="id"/>', '<bean:write name="tipoServico" property="descricao"/>', 'tipoServico');">
													<bean:write name="tipoServico" property="id" />
												</a>
											</div>
										</td>
									</logic:notEmpty>
		
									<logic:empty name="caminhoRetornoTelaPesquisaTipoServico">
											<td width="6%" align="center">
												<a href="javascript:enviarDados('<bean:write name="tipoServico" property="id"/>', '<bean:write name="tipoServico" property="descricao"/>', 'tipoServico');">
													<bean:write name="tipoServico" property="id" />
												</a>
											</td>
									</logic:empty>
								</logic:notPresent>
								
								<logic:present name="tipo">
									<logic:empty name="caminhoRetornoTelaPesquisaTipoServico">
											<td width="6%" align="center">
												<a href="javascript:enviarDados('<bean:write name="tipoServico" property="id"/>', '<bean:write name="tipoServico" property="descricao"/>', '${tipo}');">
													<bean:write name="tipoServico" property="id" />
												</a>
											</td>
									</logic:empty>
								</logic:present>
									
									<td width="30%">
										<bean:write name="tipoServico" property="descricao" />
									</td>

									<td width="12%">
										<bean:write name="tipoServico" property="servicoTipoPrioridade.descricao" />
									</td>

									<td width="15%">
										<div>
											<c:choose>
											<c:when test="${tipoServico.indicadorAtualizaComercial == '1'}">
								            	SIM - No Momento da Execução
								            </c:when>
								            <c:when test="${tipoServico.indicadorAtualizaComercial == '2'}">
								            	NÃO
								            </c:when>
								            <c:otherwise>
								            	SIM - No Momento da Posterior
								            </c:otherwise>
							            </c:choose>
							            </div>
									</td>

									<%-- <td width="11%">
										<c:choose>
											<c:when test="${tipoServico.indicadorPavimento == '1'}">
								            	SIM
								            </c:when>
								            <c:otherwise>
								            	NÃO
								            </c:otherwise>
							            </c:choose>
									</td> --%>
									
									<%-- Indicador de Pavimento de Rua --%>
									<td width="9%">
										<c:choose>
											<c:when test="${tipoServico.indicadorPavimentoRua == '1'}">
								            	SIM
								            </c:when>
								            <c:otherwise>
								            	NÃO
								            </c:otherwise>
							            </c:choose>
									</td>
									
									
									<%-- Indicador de Pavimento de Calçada --%>
									<td width="9%">
										<c:choose>
											<c:when test="${tipoServico.indicadorPavimentoCalcada == '1'}">
								            	SIM
								            </c:when>
								            <c:otherwise>
								            	NÃO
								            </c:otherwise>
							            </c:choose>
									</td>
									
									
									
									
									<td width="11%" >
										<c:choose>
											<c:when test="${tipoServico.indicadorTerceirizado == '1'}">
								            	SIM
								            </c:when>
								            <c:otherwise>
								            	NÃO
								            </c:otherwise>
							            </c:choose>
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
						<strong><%@ include	file="/jsp/util/indice_pager_novo.jsp"%>
						</strong>
					</div>
				</td>
			</tr>
			<tr>
				<td height="24"><input type="button" class="bottonRightCol"
					value="Voltar Pesquisa"
					onclick="window.location.href='/gsan/exibirPesquisarTipoServicoAction.do'"/></td>
			</tr>
		</table>
		</pg:pager></td>
	</tr>
</table>
</html:form>
</body>
</html:html>
