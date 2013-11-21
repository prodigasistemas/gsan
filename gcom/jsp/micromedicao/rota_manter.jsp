<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan" %>
<%@ page import="java.util.Collection,gcom.util.ConstantesSistema"%>
<%@ page import="gcom.util.Util"%>
<%@ page import="gcom.seguranca.acesso.usuario.UsuarioAlteracao"%>
<%@ page import="gcom.seguranca.transacao.TabelaLinhaColunaAlteracao"%>
<%@ page import="gcom.seguranca.acesso.OperacaoEfetuada"%>
<%@ page import="java.util.Date"%>

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

function verficarSelecao(objeto){

	if (CheckboxNaoVazio(objeto)){
		if (confirm ("Confirma remoção?")) {
			document.forms[0].action = "/gsan/removerRotaAction.do"
			document.forms[0].submit();
		 }
	}
 }
-->
</script>


</head>

<body leftmargin="5" topmargin="5">

<html:form action="/removerRotaAction" method="post"
	name="ManutencaoRegistroActionForm"
	type="gcom.gui.ManutencaoRegistroActionForm" method="post"
>

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
			<td width="602" valign="top" class="centercoltext">
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

					<logic:notPresent name="acao" scope="session">
						<td class="parabg">Manter Rota</td>
					</logic:notPresent>

					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>

			<table width="100%" cellpadding="0" cellspacing="0">
				<tr>
					<td height="23"><font style="font-size: 10px;"
						color="#000000" face="Verdana, Arial, Helvetica, sans-serif"> <strong>Rotas
					encontradas:</strong> </font></td>
					<logic:present scope="application" name="urlHelp">
									<td align="right"><a href="javascript:abrirPopupHelp('${applicationScope.urlHelp}cadastroRotaManter', 500, 700);"><span style="font-weight: bold"><font color="#3165CE">Ajuda</font></span></a></td>									
					</logic:present>
					<logic:notPresent scope="application" name="urlHelp">
									<td align="right"><span style="font-weight: bold"><font color=#696969><u>Ajuda</u></font></span></td>									
					</logic:notPresent>
				</tr>
			</table>
			<table width="100%" cellpadding="0" cellspacing="0">
				<tr>
					<td colspan="7" bgcolor="#000000" height="2"></td>
				</tr>



				<tr>
					<td>
						<table width="100%" bgcolor="#90c7fc">

							<tr>
								<td width="7%">
								<div align="center"><strong><a
									href="javascript:facilitador(this);" id="0">Todos</a></strong></div>
								</td>
								<td align="center" width="8%" bgcolor="#90c7fc"><strong>Rota</strong></td>
								<td align="center" width="11%" bgcolor="#90c7fc"><strong>Localidade</strong></td>
								<td align="center" width="8%" bgcolor="#90c7fc"><strong>Setor</strong></td>
								<td align="center" width="22%" bgcolor="#90c7fc"><strong>Grupo de Faturamento</strong></td>
								<td align="center" width="22%" bgcolor="#90c7fc"><strong>Grupo de Cobrança</strong></td>
								<td align="center" width="22%" bgcolor="#90c7fc"><strong>Empresa de Leitura</strong></td>
							</tr>

							<%--Esquema de paginação--%>
							<pg:pager isOffset="true" index="half-full" maxIndexPages="10"
							export="currentPageNumber=pageNumber;pageOffset"
							maxPageItems="10" items="${sessionScope.totalRegistros}">
								<pg:param name="pg" />
								<pg:param name="q" />

							<logic:present name="collectionRota">
								<% String cor = "#cbe5fe";%>

								<logic:iterate name="collectionRota" id="rota">
								<pg:item>
									<%	if (cor.equalsIgnoreCase("#cbe5fe")){	
										cor = "#FFFFFF";%>
										<tr bgcolor="#FFFFFF" height="18">	
									<%} else{	
										cor = "#cbe5fe";%>
										<tr bgcolor="#cbe5fe" height="18">		
									<%}%>
								
											<td width="7%">
												<div align="center"><input type="checkbox"
													name="idRegistrosRemocao"
													value="<bean:write name="rota" property="id"/>"></div>
											</td>
											<td width="8%">
												<div align="center">
													<logic:notPresent name="acao"
														scope="session">
														<a
																href="/gsan/exibirAtualizarRotaAction.do?idRegistroAtualizacao=<bean:write name="rota" property="id"/>">
															<logic:notEqual name="rota" property="indicadorUso"
																value="<%=ConstantesSistema.INDICADOR_USO_DESATIVO.toString()%>">
																<bean:write name="rota" property="codigo" />
															</logic:notEqual> 
															<logic:equal name="rota" property="indicadorUso"
																value="<%=ConstantesSistema.INDICADOR_USO_DESATIVO.toString()%>">
																<font color="#CC0000"><bean:write name="rota" property="codigo" /></font>
															</logic:equal>
														</a>
														&nbsp;
													</logic:notPresent>
												</div>
											</td>
											<td width="11%">
												<div align="center">
													<logic:notEqual name="rota" property="indicadorUso"
															value="<%=ConstantesSistema.INDICADOR_USO_DESATIVO.toString()%>">
															${rota.setorComercial.localidade.id} &nbsp;
														</logic:notEqual> 
														<logic:equal name="rota" property="indicadorUso"
															value="<%=ConstantesSistema.INDICADOR_USO_DESATIVO.toString()%>">
															<font color="#CC0000">${rota.setorComercial.localidade.id} &nbsp;</font>
													</logic:equal>
												</div>
											</td>
											<td width="8%">
												<div align="center">
													<logic:notEqual name="rota" property="indicadorUso"
														value="<%=ConstantesSistema.INDICADOR_USO_DESATIVO.toString()%>">
														${rota.setorComercial.codigo} &nbsp;
													</logic:notEqual> 
													<logic:equal name="rota" property="indicadorUso"
														value="<%=ConstantesSistema.INDICADOR_USO_DESATIVO.toString()%>">
														<font color="#CC0000">${rota.setorComercial.codigo} &nbsp;</font>
													</logic:equal>
												</div>
											</td>
											<td width="22%">
												<div>
													<logic:notEqual name="rota" property="indicadorUso"
														value="<%=ConstantesSistema.INDICADOR_USO_DESATIVO.toString()%>">
														${rota.faturamentoGrupo.descricao} &nbsp;
													</logic:notEqual> 
													<logic:equal name="rota" property="indicadorUso"
														value="<%=ConstantesSistema.INDICADOR_USO_DESATIVO.toString()%>">
														<font color="#CC0000">${rota.faturamentoGrupo.descricao} &nbsp;</font>
													</logic:equal>
												</div>
											</td>
											<td width="22%">
												<div>
													<logic:notEqual name="rota" property="indicadorUso"
														value="<%=ConstantesSistema.INDICADOR_USO_DESATIVO.toString()%>">
														${rota.cobrancaGrupo.descricao} &nbsp;
													</logic:notEqual> 
													<logic:equal name="rota" property="indicadorUso"
														value="<%=ConstantesSistema.INDICADOR_USO_DESATIVO.toString()%>">
														<font color="#CC0000">${rota.cobrancaGrupo.descricao} &nbsp;</font>
													</logic:equal>
												</div>
											</td>
											<td width="22%">
												<div>
													<logic:notEqual name="rota" property="indicadorUso"
														value="<%=ConstantesSistema.INDICADOR_USO_DESATIVO.toString()%>">
														${rota.empresa.descricao} &nbsp;
													</logic:notEqual> 
													<logic:equal name="rota" property="indicadorUso"
														value="<%=ConstantesSistema.INDICADOR_USO_DESATIVO.toString()%>">
														<font color="#CC0000">${rota.empresa.descricao} &nbsp;</font>
													</logic:equal>
												</div>
											</td>
										</tr>
									</pg:item>
								</logic:iterate>
							</logic:present>
					</table>
					</td>
				</tr>
			</table>

			<table width="100%">
				<tr>

					<td><logic:notPresent name="acao" scope="session">
						<gsan:controleAcessoBotao name="Button" value="Remover"
							  onclick="javascript:verficarSelecao(document.ManutencaoRegistroActionForm.idRegistrosRemocao);" url="removerRotaAction.do"/>
<!--
						<input name="Button" type="button" class="bottonRightCol" value="Remover"
						 onclick="verficarSelecao(document.ManutencaoRegistroActionForm.idRegistrosRemocao)" align="left" style="width: 70px;"> -->
					</logic:notPresent> <input name="button" type="button"
						class="bottonRightCol" value="Voltar Filtro"
						onclick="window.location.href='<html:rewrite page="/exibirFiltrarRotaAction.do?desfazer=N"/>'"
						align="left" style="width: 80px;"></td>
					<td align="right">      
					<div align="right"><a
								href="javascript:toggleBox('demodiv',1);">
							<img border="0"
								src="<bean:message key="caminho.imagens"/>print.gif"
								title="Imprimir Rotas" /> </a></div>
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
					<%-- Fim do esquema de paginação --%>
				</tr>

			</table>
			</td>
		</tr>
	</table>
	<jsp:include page="/jsp/relatorio/escolher_tipo_relatorio.jsp?relatorio=gerarRelatorioRotaManterAction.do"/>
	<%@ include file="/jsp/util/rodape.jsp"%>
</body>
</html:form>
</html:html>
