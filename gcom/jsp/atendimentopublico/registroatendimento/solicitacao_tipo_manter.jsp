<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>

<%@page
	import="gcom.atendimentopublico.registroatendimento.SolicitacaoTipo"%>

<%@page import="gcom.util.ConstantesSistema"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>

<head>

<%@ include file="/jsp/util/titulo.jsp"%>
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

<html:form action="/removerAtualizarTipoSolicitacaoEspecificacaoAction"
	name="ManutencaoRegistroActionForm"
	type="gcom.gui.ManutencaoRegistroActionForm" method="post"
	onsubmit="return CheckboxNaoVazio(document.ManutencaoRegistroActionForm.idRegistrosRemocao) && confirm('Confirmar remoção?')">

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
			<td width="625" valign="top" class="centercoltext">
			<table height="100%">
				<tr>
					<td></td>
				</tr>
			</table>

			<table>
				<tr>
					<td></td>

				</tr>
			</table>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img border="0" src="imagens/parahead_left.gif" /></td>
					<td class="parabg">Manter Tipo de Solicitação com Especificações</td>
					<td width="11" valign="top"><img border="0"
						src="imagens/parahead_right.gif" /></td>

				</tr>
			</table>

			<table width="100%" cellpadding="0" cellspacing="0">
				<tr>
					<td colspan="7" height="23"><font style="font-size: 10px;"
						color="#000000" face="Verdana, Arial, Helvetica, sans-serif"> <strong>Tipos
					de Solicitação com Especificações Cadastradas:</strong> </font></td>
				</tr>
				<tr>
					<!--<td colspan="4" bgcolor="#3399FF"> -->
					<td colspan="7" bgcolor="#000000" height="2" valign="baseline"></td>
				</tr>
				<tr>
					<td>
					<table width="100%" align="center" bgcolor="#90c7fc" border="0"
						cellpadding="0" cellspacing="0">
						<tr bgcolor="#cbe5fe">
							<td width="100%" align="center">
							<table width="100%" bgcolor="#90c7fc">
								<tr bordercolor="#FFFFFF" bgcolor="#79BBFD">

									<td width="7%">
									<div align="center"><strong><a
										href="javascript:facilitador(this);" id="0">Todos</a></strong></div>
									</td>

									<td width="29%">
									<div align="center"><strong>Tipo da Solicitação</strong></div>
									</td>
									<td width="25%">
									<div align="center"><strong>Grupo de Solicitação</strong></div>
									</td>
									<td width="17%">
									<div align="center"><strong>Indicador Falta D'água</strong></div>
									</td>

									<td width="22%">
									<div align="center"><strong>Indicador de Tarifa Social</strong></div>
									</td>
								</tr>
								<pg:pager isOffset="true" index="half-full" maxIndexPages="10"
									export="currentPageNumber=pageNumber;pageOffset"
									maxPageItems="10" items="${sessionScope.totalRegistros}">
									<pg:param name="pg" />
									<pg:param name="q" />
									<%int cont = 0;%>
									<logic:iterate name="colecaoTipoSolicitacao"
										id="solicitacaoTipo" type="SolicitacaoTipo">
										<pg:item>
											<%cont = cont + 1;
			if (cont % 2 == 0) {%>
											<tr bgcolor="#FFFFFF">
												<%} else {%>
											<tr bgcolor="#cbe5fe">
												<%}%>
												<logic:equal name="solicitacaoTipo" property="indicadorUso"
													value="<%=ConstantesSistema.INDICADOR_USO_DESATIVO.toString()%>">
													<td width="7%">
													<div align="center"><input type="checkbox"
														name="idRegistrosRemocao"
														value="<bean:write name="solicitacaoTipo" property="id"/>"></div>
													</td>
													<td width="29%" align="center"><html:link
														href="/gsan/exibirAtualizarTipoSolicitacaoEspecificacaoAction.do?manter=sim"
														paramId="idTipoSolicitacao" paramProperty="id"
														paramName="solicitacaoTipo"
														title="<%="" + solicitacaoTipo.getDescricao()%>">
														<font color="#CC0000"><bean:write name="solicitacaoTipo" property="descricao" />
													</html:link></td>

													<td width="25%"><font color="#CC0000">
													<div align="center"><bean:write name="solicitacaoTipo"
														property="solicitacaoTipoGrupo.descricao" /></div>
													</font></td>


													<td width="17%" align="center"><font color="#CC0000"><logic:equal
														name="solicitacaoTipo" property="indicadorFaltaAgua"
														value="1">
											SIM
											</logic:equal> <logic:equal name="solicitacaoTipo"
														property="indicadorFaltaAgua" value="2">
											NÃO
											</logic:equal></font></td>

													<td width="22%" align="center"><font color="#CC0000"><logic:equal
														name="solicitacaoTipo" property="indicadorTarifaSocial"
														value="1">
											SIM
											</logic:equal> <logic:equal name="solicitacaoTipo"
														property="indicadorTarifaSocial" value="2">
											NÃO
											</logic:equal></font></td>
											</tr>
											</logic:equal>
											<logic:notEqual name="solicitacaoTipo"
											
												property="indicadorUso"
												value="<%=ConstantesSistema.INDICADOR_USO_DESATIVO.toString()%>">
												<td width="7%">
												<div align="center"><input type="checkbox"
													name="idRegistrosRemocao"
													value="<bean:write name="solicitacaoTipo" property="id"/>"></div>
												</td>
												<td width="29%" align="center"><html:link
													href="/gsan/exibirAtualizarTipoSolicitacaoEspecificacaoAction.do?manter=sim"
													paramId="idTipoSolicitacao" paramProperty="id"
													paramName="solicitacaoTipo"
													title="<%="" + solicitacaoTipo.getDescricao()%>">
													<bean:write name="solicitacaoTipo" property="descricao" />
												</html:link></td>

												<td width="25%">
												<div align="center"><bean:write name="solicitacaoTipo"
													property="solicitacaoTipoGrupo.descricao" /></div>
												</td>


												<td width="17%" align="center"><logic:equal
													name="solicitacaoTipo" property="indicadorFaltaAgua"
													value="1">
											SIM
											</logic:equal> <logic:equal name="solicitacaoTipo"
													property="indicadorFaltaAgua" value="2">
											NÃO
											</logic:equal></td>

												<td width="22%" align="center"><logic:equal
													name="solicitacaoTipo" property="indicadorTarifaSocial"
													value="1">
											SIM
											</logic:equal> <logic:equal name="solicitacaoTipo"
													property="indicadorTarifaSocial" value="2">
											NÃO
											</logic:equal></td>

											</logic:notEqual>
										</pg:item>
									</logic:iterate>
							</table>
							<table width="100%" border="0">
								<tr>
									<td align="center"><strong><%@ include
										file="/jsp/util/indice_pager_novo.jsp"%></strong></td>
								</tr>
								</pg:pager>
								<tr>
									<td><font color="#FF0000"> <html:submit
										property="buttonRemover" styleClass="bottonRightCol"
										value="Remover" /></font> <input name="button" type="button"
										class="bottonRightCol" value="Voltar Filtro"
										onclick="window.location.href='<html:rewrite page="/exibirFiltrarTipoSolicitacaoEspecificacaoAction.do?limpar=S"/>'"
										align="left" style="width: 80px;"></td>
								</tr>
							</table>
							</td>
						</tr>

					</table>
					<p>&nbsp;</p>
					</td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
	<%@ include file="/jsp/util/rodape.jsp"%>
</body>
</html:form>
</html:html>
