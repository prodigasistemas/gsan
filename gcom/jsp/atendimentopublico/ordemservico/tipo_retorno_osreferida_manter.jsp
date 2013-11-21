<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>

<head>

<title>GSAN - Sistema Integrado de Gest&atilde;o de Servi&ccedil;os de Saneamento</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>/validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script>
<!--
function facilitador(objeto){
	if (objeto.id == "0" || objeto.id == undefined){
		objeto.id = "1";
		marcarTodos();
	}
	else{
		objeto.id = "0";
		desmarcarTodos();
	}
}
-->
</script>
</head>

<body leftmargin="5" topmargin="5">

<html:form action="/removerTipoRetornoOrdemServicoReferidaAction"
	name="ManutencaoRegistroActionForm"
	type="gcom.gui.ManutencaoRegistroActionForm" method="post"
	onsubmit="return CheckboxNaoVazio(document.ManutencaoRegistroActionForm.idRegistrosRemocao) && confirm('Confirma remoção?')">

	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>

	<table width="770" border="0" cellspacing="5" cellpadding="0">
		<tr>
			<td width="130" valign="top" class="leftcoltext">
			<div align="center">
			<p align="left">&nbsp;</p>
			<p align="left">&nbsp;</p>
			<p align="left">&nbsp;</p>

			<%@ include file="/jsp/util/informacoes_usuario.jsp"%>

			<p align="left">&nbsp;</p>
			<p align="left">&nbsp;</p>
			<p align="left">&nbsp;</p>
			<p align="left">&nbsp;</p>
			<p align="left">&nbsp;</p>
			<p align="left">&nbsp;</p>
			<p align="left">&nbsp;</p>
			<p align="left">&nbsp;</p>
			<p align="left">&nbsp;</p>
			<p align="left">&nbsp;</p>
			<p align="left">&nbsp;</p>
			<p align="left">&nbsp;</p>

			<%@ include file="/jsp/util/mensagens.jsp"%>

			<p align="left">&nbsp;</p>
			<p align="left">&nbsp;</p>
			<p align="left">&nbsp;</p>
			<p align="left">&nbsp;</p>
			<p align="left">&nbsp;</p>
			<p align="left">&nbsp;</p>
			<p align="left">&nbsp;</p>
			</div>
			</td>
			<td width="625" valign="top" bgcolor="#003399" class="centercoltext">
			<table height="100%">

				<tr>
					<td></td>
				</tr>
			</table>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">

				<tr>
					<td width="11"><img src="imagens/parahead_left.gif"
						editor="Webstyle4"
						moduleid="Album Photos (Project)\toptab_page2_parahead_left.xws"
						border="0" /></td>
					<td class="parabg">Manter Tipo de Retorno da OS_Referida</td>

					<td width="11"><img src="imagens/parahead_right.gif"
						editor="Webstyle4"
						moduleid="Album Photos (Project)\toptab_page2_parahead_right.xws"
						border="0" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>

			<table width="100%" align="center" cellpadding="0" cellspacing="0">
				<!--corpo da segunda tabela-->
				<tr>
					<td colspan="5" height="23"><font color="#000000"
						style="font-size:10px"
						face="Verdana, Arial, Helvetica, sans-serif"><strong>Tipo(s) de
					Retorno(s) Cadastrados:</strong></font></td>
				</tr>
				<tr>
					<!--<td colspan="4" bgcolor="#3399FF"> -->
					<td colspan="5" bgcolor="#000000" height="2" valign="baseline"></td>
				</tr>
				<tr>
					<td>
					<table width="100%" align="center" bgcolor="#90c7fc" border="0"
						cellpadding="0" cellspacing="0">
						<tr bgcolor="#cbe5fe">
							<td width="100%" align="center">
							<table width="100%" bgcolor="#99CCFF">
								<tr bordercolor="#000000" bgcolor="#90c7fc">
									<td bgcolor="#90c7fc">
									<div align="center"><strong><a
										href="javascript:facilitador(this);">Todos</a></strong></div>
									</td>
									<td bgcolor="#90c7fc">
									<div align="center"><strong> Tipo de Retorno</strong></div>
									</td>

									<td bgcolor="#90c7fc">
									<div align="center"><strong>Descri&ccedil;&atilde;o</strong></div>
									</td>
									<td bgcolor="#90c7fc">
									<div align="center"><strong>Abreviatura</strong></div>
									</td>
									<td bgcolor="#90c7fc">
									<div align="center"><strong>Referência do Tipo de Serviço</strong></div>
									</td>
									<td bgcolor="#90c7fc">
									<div align="center"><strong>Indicador de Deferimento</strong></div>
									</td>

								</tr>
								<pg:pager isOffset="true" index="half-full" maxIndexPages="10"
									export="currentPageNumber=pageNumber;pageOffset"
									maxPageItems="10" items="${sessionScope.totalRegistros}">
									<pg:param name="pg" />
									<pg:param name="q" />
									<logic:present name="colecaoTipoRetornoOsReferida">
										<%int cont = 0;%>
										<logic:iterate name="colecaoTipoRetornoOsReferida"
											id="osReferidaRetornoTipo" scope="request">
											<pg:item>
												<%cont = cont + 1;
			if (cont % 2 == 0) {%>
												<tr bgcolor="#cbe5fe">
													<%} else {%>
												<tr bgcolor="#FFFFFF">
													<%}%>
													<td width="7%">
													<div align="center"><input type="checkbox"
														name="idRegistrosRemocao"
														value="${osReferidaRetornoTipo.id}"></div>
													</td>

													<td width="12%" align="center"><html:link
														page="/exibirAtualizarTipoRetornoOrdemServicoReferidaAction.do"
														paramName="osReferidaRetornoTipo" paramProperty="id"
														paramId="idRegistroAtualizacao">${osReferidaRetornoTipo.id}
													</html:link></td>
													<td width="27%" align="center">${osReferidaRetornoTipo.descricao}</td>
													<td width="15%">
													<div align="center">
													${osReferidaRetornoTipo.descricaoAbreviada}</div>
													</td>

													<td width="20%">
													<div align="center">${osReferidaRetornoTipo.servicoTipoReferencia.descricao}</div>
													</td>

													<td width="20%">
													<div align="center"><c:if
														test="${osReferidaRetornoTipo.indicadorDeferimento eq 1}">
														<div align="center">DEFERIMENTO</div>
													</c:if> <c:if
														test="${osReferidaRetornoTipo.indicadorDeferimento eq 2}">
														<div align="center">INDEFERIMENTO</div>
													</c:if></div>


													<html:link
														href="/gsan/exibirManterTipoRetornoOrdemServicoReferidaAction.do"
														paramId="osReferidaRetornoTipoID" paramProperty="id"
														paramName="osReferidaRetornoTipo">


													</html:link></td>

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
							<td colspan="2">
							<div align="center"><strong><%@ include
								file="/jsp/util/indice_pager_novo.jsp"%></strong></div>
							</td>
						</tr>
						</pg:pager>
						<tr>
							<td colspan="2"><font color="#FF0000"> <html:submit
								property="buttonRemover" styleClass="bottonRightCol"
								value="Remover" /></font> <input type="button"
								name="ButtonReset" class="bottonRightCol" value="Voltar Filtro"
								onClick="javascript:window.location.href='/gsan/exibirFiltrarTipoRetornoOrdemServicoReferidaAction.do?paginacao=sim'">
							</td>
						</tr>
					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
	<%@ include file="/jsp/util/rodape.jsp"%>
</html:form>
</body>
</html:html>
