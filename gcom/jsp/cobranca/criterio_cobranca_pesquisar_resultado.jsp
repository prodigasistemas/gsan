<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>

<script language="JavaScript">

</script>
</head>
<!-- onload="resizePageSemLink(810,595);" -->
<body leftmargin="0" topmargin="0" onload="resizePageSemLink(810,615);">
<html:form action="/pesquisarCriterioCobrancaAction"
	name="PesquisarCriterioCobrancaForm"
	type="gcom.gui.cobranca.PesquisarCriterioCobrancaActionForm"
	method="post">

	<table width="770" border="0" cellspacing="5" cellpadding="0">
		<tr>
			<td width="770" valign="top" class="centercoltext">
			<table height="100%">

				<tr>
					<td></td>
				</tr>
			</table>
			<table align="center" border="0" cellpadding="0" cellspacing="0"
				width="100%">
				<tbody>
					<tr>
						<td width="11"><img border="0"
							src="<bean:message key="caminho.imagens"/>parahead_left.gif" /></td>
						<td class="parabg">Pesquisar Critério de Cobrança</td>

						<td width="11"><img border="0"
							src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>

					</tr>
				</tbody>
			</table>
			<p>&nbsp;</p>
			<table width="100%" bgcolor="#90c7fc">
				<tbody>
					<tr align="left" bgcolor="#90c7fc">
						<td rowspan="2" bgcolor="#90c7fc">
						<div align="center"><strong>Código Critério</strong></div>
						</td>
						<td rowspan="2" width="20%" bgcolor="#90c7fc">
						<div align="center"><strong>Descrição do Critério</strong></div>
						<div align="center"></div>
						<div align="center"></div>
						</td>
						<td rowspan="2" bgcolor="#90c7fc">
						<div align="center"><strong>Início de Vigência</strong></div>
						<div align="center"></div>
						<div align="center"></div>
						</td>

						<td rowspan="2" bgcolor="#90c7fc">
						<div align="center"><strong>Anos para Considerar Conta Antiga</strong></div>
						</td>
						<td colspan="6" bgcolor="#90c7fc">
						<div align="center"><strong>Indicadores de Seleção</strong></div>
						<div align="center"></div>
						<div align="center"></div>
						</td>
					</tr>
					<tr align="left" bgcolor="#90c7fc">
						<td>
						<div align="center"><strong>Situação Especial Cobrança</strong></div>
						</td>

						<td>
						<div align="center"><strong>Situação Cobrança</strong></div>
						</td>
						<td>
						<div align="center"><strong>Contas em Revisão</strong></div>
						</td>
						<td>
						<div align="center"><strong>Débito Apenas Conta Mês</strong></div>
						</td>
						<td>
						<div align="center"><strong>Inquilino Débito Apenas Conta Mês</strong></div>
						</td>
						<td>
						<div align="center"><strong>Débito só de Contas Antigas</strong></div>
						</td>
					</tr>
					<% String cor = "#cbe5fe";%>
					<pg:pager isOffset="true" index="half-full" maxIndexPages="10"
							export="currentPageNumber=pageNumber;pageOffset"
							maxPageItems="10" items="${sessionScope.totalRegistros}">
							<pg:param name="pg" />
							<pg:param name="q" />
						<logic:present name="colecaoCriterioCobranca" scope="session">
							<logic:iterate name="colecaoCriterioCobranca" id="criterio">
								<pg:item>
										<%	if (cor.equalsIgnoreCase("#cbe5fe")){	
											cor = "#FFFFFF";%>
											<tr bgcolor="#FFFFFF" height="18">	
										<%} else{	
											cor = "#cbe5fe";%>
											<tr bgcolor="#cbe5fe" height="18">		
										<%}%>
										<td>
										<div align="center">
										<logic:notPresent name="tipoPesquisa">
											<logic:notEmpty
												name="caminhoRetornoTelaPesquisaCriterioCobranca">
												<a href="javascript:enviarDadosParametros('<bean:write name="caminhoRetornoTelaPesquisaCriterioCobranca"/>', '<bean:write name="criterio" property="id"/>', '<bean:write name="criterio" property="descricaoCobrancaCriterio"/>', 'criterioCobranca');">
												<bean:write name="criterio" property="id" /></a>
											</logic:notEmpty>
											<logic:empty
												name="caminhoRetornoTelaPesquisaCriterioCobranca">
												<a href="javascript:enviarDados('<bean:write name="criterio" property="id"/>', '<bean:write name="criterio" property="descricaoCobrancaCriterio"/>', 'criterioCobranca');">
												<bean:write name="criterio" property="id" /></a>
											</logic:empty>
										</logic:notPresent>
										</div>
										</td>
										<td>
										<div align="center">
										 <a	href="exibirPesquisarCriterioCobrancaLinhaAction.do?criterio=<bean:write name="criterio" property="id"/>">
											<bean:write name="criterio" property="descricaoCobrancaCriterio" /></a>
										</div>
										</td>
										<td>
										<div align="center"><bean:write name="criterio"
											property="dataInicioVigencia" format="dd/MM/yyyy" /></div>
										</td>
										<td>
										<div align="center"><bean:write name="criterio"
											property="numeroContaAntiga" /></div>
										</td>
										<td>
										<div align="center"><logic:equal name="criterio"
											property="indicadorEmissaoImovelParalisacao" value="1">Sim</logic:equal>
										<logic:equal name="criterio"
											property="indicadorEmissaoImovelParalisacao" value="2">Não</logic:equal>
										</div>
										</td>
										<td bordercolor="#90c7fc">
										<div align="center"><logic:equal name="criterio"
											property="indicadorEmissaoImovelSituacaoCobranca" value="1">Sim</logic:equal>
										<logic:equal name="criterio"
											property="indicadorEmissaoImovelSituacaoCobranca" value="2">Não</logic:equal>
										</div>
										</td>
										<td>
										<div align="center"><logic:equal name="criterio"
											property="indicadorEmissaoContaRevisao" value="1">Sim</logic:equal>
										<logic:equal name="criterio"
											property="indicadorEmissaoContaRevisao" value="2">Não</logic:equal>
										</div>
										</td>
										<td>
										<div align="center"><logic:equal name="criterio"
											property="indicadorEmissaoDebitoContaMes" value="1">Sim</logic:equal>
										<logic:equal name="criterio"
											property="indicadorEmissaoDebitoContaMes" value="2">Não</logic:equal>
										</div>
										</td>
										<td>
										<div align="center"><logic:equal name="criterio"
											property="indicadorEmissaoInquilinoDebitoContaMes" value="1">Sim</logic:equal>
										<logic:equal name="criterio"
											property="indicadorEmissaoInquilinoDebitoContaMes" value="2">Não</logic:equal>
										</div>
										</td>
										<td>
										<div align="center"><logic:equal name="criterio"
											property="indicadorEmissaoDebitoContaAntiga" value="1">Sim</logic:equal>
										<logic:equal name="criterio"
											property="indicadorEmissaoDebitoContaAntiga" value="2">Não</logic:equal>
										</div>
										</td>
									</tr>
								</pg:item>
							</logic:iterate>
						</logic:present>
				</tbody>
			</table>
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>
					<td>
					<div align="center"><strong><%@ include
						file="/jsp/util/indice_pager_novo.jsp"%></strong></div>
					</td>
					</pg:pager>
				</tr>
				<tr>
					<td>
					<div align="left"><input type="button" name="butao"
						value="Voltar Pesquisa" class="bottonRightCol"
						onclick="javascript:window.location.href='exibirPesquisarCriterioCobrancaAction.do';"></div>

					</td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
</html:form>
</body>
</html:html>
