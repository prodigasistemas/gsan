<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>

<%@ page import="java.util.Collection,gcom.util.ConstantesSistema"%>
<%@page
	import="gcom.atendimentopublico.ordemservico.ServicoTipoReferencia"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>

<title>GSAN - Sistema Integrado de Gest&atilde;o de Servi&ccedil;os de Saneamento</title>
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

</head>

<body leftmargin="5" topmargin="5" onload="resizePageSemLink(790,513);">

<html:form action="/pesquisarClienteAction" method="post"
	onsubmit="return validatePesquisarActionForm(this);">
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
					<td class="parabg">Pesquisa do Tipo Serviço Referência</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>
					<td width="100%">

					<table width="100%" align="center" bgcolor="#90c7fc" border="0"
						cellpadding="0" cellspacing="0">
						<tr bgcolor="#cbe5fe">
							<td width="100%" align="center">
							<table width="100%" bgcolor="#90c7fc">
								<tr bordercolor="#FFFFFF" bgcolor="#79BBFD">


									<td width="12%">
									<div align="center"><strong>Tipo Serviço de Referência</strong></div>
									</td>

									<td width="28%">
									<div align="left"><strong>Descrição</strong></div>
									</td>

									<td width="12%">
									<div align="center"><strong>Descrição Abreviada</strong></div>
									</td>

									<td width="20%">
									<div align="center"><strong>Indicador de existência da OS de
									Referência</strong></div>
									</td>

									<td width="28%">
									<div align="left"><strong>Tipo de Serviço</strong></div>
									</td>

								</tr>
								<pg:pager maxIndexPages="10"
									export="currentPageNumber=pageNumber" index="center"
									maxPageItems="10">
									<pg:param name="pg" />
									<pg:param name="q" />
									<%int cont = 0;%>
									<logic:iterate name="colecaoServicoTipoReferencia"
										id="servicoTipoReferencia" type="ServicoTipoReferencia">
										<pg:item>
											<%cont = cont + 1;
			if (cont % 2 == 0) {%>
											<tr bgcolor="#cbe5fe">
												<%} else {%>
											<tr bgcolor="#FFFFFF">
												<%}%>
												<logic:notEmpty
													name="caminhoRetornoTelaPesquisaTipoReferencia">
													<td width="12%">
													<div align="center">
													<bean:write name="servicoTipoReferencia" property="id" />
													</div>
													</td>
												</logic:notEmpty>

												<logic:empty name="caminhoRetornoTelaPesquisaTipoReferencia">
													<td width="12%" align="center">
													<bean:write name="servicoTipoReferencia" property="id" />
													</td>
												</logic:empty>


												<td width="28%" align="left"><logic:notEmpty
													name="caminhoRetornoTelaPesquisaTipoReferencia">
													<a
														href="javascript:enviarDadosParametros('<bean:write name="caminhoRetornoTelaPesquisaTipoReferencia"/>', '<bean:write name="servicoTipoReferencia" property="id"/>', '<bean:write name="servicoTipoReferencia" property="descricao"/>', 'servicoTipoReferencia');">
													<bean:write name="servicoTipoReferencia"
														property="descricao" /> </a>
												</logic:notEmpty> <logic:empty
													name="caminhoRetornoTelaPesquisaTipoReferencia">
													<a
														href="javascript:enviarDados( '<bean:write name="servicoTipoReferencia" property="id"/>', '<bean:write name="servicoTipoReferencia" property="descricao"/>','servicoTipoReferencia');">
													<bean:write name="servicoTipoReferencia"
														property="descricao" /></a>
												</logic:empty>
												
												
													<td width="12%">
														<div>
														<bean:write name="servicoTipoReferencia" property="descricaoAbreviada" />
														</div>
													</td>
											
												


												<td width="20%" align="center"><logic:equal
													name="servicoTipoReferencia"
													property="indicadorExistenciaOsReferencia" value="1">
											SIM
											</logic:equal> <logic:equal name="servicoTipoReferencia"
													property="indicadorExistenciaOsReferencia" value="2">
											NÃO
											</logic:equal></td>

												<td width="28%">
												<div align="left">${servicoTipoReferencia.servicoTipo.descricao}&nbsp;</div>
												</td>

											</tr>
										</pg:item>
									</logic:iterate>
							</table>
							<table width="100%">
								<tr>
									<td><input name="button" type="button" class="bottonRightCol"
										value="Voltar Pesquisa"
										onclick="window.location.href='<html:rewrite page="/exibirPesquisarTipoServicoReferenciaAction.do?limpar=S"/>'"
										align="left"></td>

								</tr>
							</table>
							<%if (((Collection) session
					.getAttribute("colecaoServicoTipoReferencia")).size() == ConstantesSistema.NUMERO_MAXIMO_REGISTROS_PESQUISA) {%>
							<%@ include file="/jsp/util/limite_pesquisa.jsp"%> <%}%>

							<table width="100%" border="0">
								<tr>
									<td><strong><%@ include file="/jsp/util/indice_pager.jsp"%></strong>
									</td>
								</tr>
							</table>
							</pg:pager>
					</table>
					<p>&nbsp;</p>
			</table>
	</table>
</body>
</html:form>
</html:html>
