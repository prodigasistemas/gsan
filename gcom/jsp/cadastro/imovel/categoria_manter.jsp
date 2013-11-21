<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan"%>
<%@ page import="gcom.util.ConstantesSistema,java.util.Collection"%>

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
<script>
<!--
function facilitador(objeto){
	if (objeto.value == "0"){
		objeto.value = "1";
		marcarTodos();
	}else{
		objeto.value = "0";
		desmarcarTodos();
	}
}
function verficarSelecao(objeto, tipoObjeto){
	var indice;
	var array = new Array(objeto.length);
	var selecionado = "";
	var formulario = document.forms[0]; 

	for(indice = 0; indice < formulario.elements.length; indice++){
		if (formulario.elements[indice].type == tipoObjeto && formulario.elements[indice].checked == true) {
			selecionado = formulario.elements[indice].value;
			break;
		}
 	}

	if (selecionado.length < 1) {
		alert('Nenhum registro selecionado para remoção.');
	}else {
		if (confirm ("Confirma remoção?")) {
			redirecionarSubmit("/gsan/removerCategoriaAction.do");
		}
	}
}
-->
</script>
</head>
<body leftmargin="5" topmargin="5">
<html:form action="/removerCategoriaAction"
	name="ManutencaoRegistroActionForm"
	type="gcom.gui.ManutencaoRegistroActionForm" method="post">
	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>
	<table width="770" border="0" cellspacing="5" cellpadding="0">
		<tr>
			<td width="115" valign="top" class="leftcoltext">
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
			<td valign="top" class="centercoltext">
			<table>
				<tr>
					<td></td>
				</tr>
			</table>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_left.gif" /></td>
					<td class="parabg">Manter Categoria</td>
					<td width="11" valign="top"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<!-- Início do Corpo - Roberta Costa -->
			<p>&nbsp;</p>

			<table width="100%" cellpadding="0" cellspacing="0">
				<tr>
					<td colspan="5" height="23"><font color="#000000"
						style="font-size:10px"
						face="Verdana, Arial, Helvetica, sans-serif"> <strong>Categorias
					Encontradas:</strong> </font></td>
					<logic:present scope="application" name="urlHelp">
						<td align="right"><a href="javascript:abrirPopupHelp('${applicationScope.urlHelp}cadastroCategoriaManter', 500, 700);"><span style="font-weight: bold"><font color="#3165CE">Ajuda</font></span></a></td>									
					</logic:present>
					<logic:notPresent scope="application" name="urlHelp">
						<td align="right"><span style="font-weight: bold"><font color=#696969><u>Ajuda</u></font></span></td>									
					</logic:notPresent>
				</tr>
				</table>				
				<table width="100%" cellpadding="0" cellspacing="0">
				<tr>
					<td colspan="5" bgcolor="#000000" height="2"></td>
				</tr>
				<tr bordercolor="#000000" height="20">
					<td width="7%" bgcolor="#90c7fc" align="center"><strong><a
						href="javascript:facilitador(this);">Todos</a></strong></td>
					<td width="12%" bgcolor="#90c7fc" align="center"><strong>&nbsp;C&oacute;digo</strong></td>
					<td width="30%" bgcolor="#90c7fc" align="center"><strong>&nbsp;Descrição</strong></td>
					<td width="30%" bgcolor="#90c7fc" align="center"><strong>&nbsp;Descrição
					Abreviada</strong></td>
					<td width="20%" bgcolor="#90c7fc" align="center"><strong>&nbsp;Consumo
					Minímo</strong></td>
				</tr>

				<%--Esquema de paginação--%>

				<%String cor = "#FFFFFF";%>

				<tr>
					<td colspan="5">
					<table width="100%" bgcolor="#99CCFF">
						<pg:pager isOffset="true" index="half-full" maxIndexPages="10"
							export="currentPageNumber=pageNumber;pageOffset"
							maxPageItems="10" items="${sessionScope.totalRegistros}">
							<pg:param name="pg" />
							<pg:param name="q" />

							<logic:present name="categorias">
								<logic:iterate name="categorias" id="categoria">
									<pg:item>

										<%if (cor.equalsIgnoreCase("#FFFFFF")) {
				cor = "#cbe5fe";%>
										<tr bgcolor="#FFFFFF">
											<%} else {
				cor = "#FFFFFF";%>
										<tr bgcolor="#cbe5fe">
											<%}%>
											<logic:equal name="categoria" property="indicadorUso"
												value="<%=ConstantesSistema.INDICADOR_USO_DESATIVO.toString()%>">
												<td width="7%" align="center"><input type="checkbox"
													name="idRegistrosRemocao"
													value="<bean:write name="categoria" property="id"/>" /></td>
												<td width="12%" align="center"><font color="#CC0000"> <bean:write
													name="categoria" property="id" /> </font></td>
												<td width="30%"><html:link
													page="/exibirAtualizarCategoriaAction.do"
													paramName="categoria" paramProperty="id"
													paramId="idRegistroAtualizacao">
													<font color="#CC0000"> <bean:write name="categoria"
														property="descricao" /> </font>
												</html:link></td>
												<td width="30%"><font color="#CC0000"> <bean:write
													name="categoria" property="descricaoAbreviada" /> </font></td>
												<td width="20%" align="center"><font color="#CC0000"> <bean:write
													name="categoria" property="consumoMinimo" /> </font></td>
											</logic:equal>
											<logic:notEqual name="categoria" property="indicadorUso"
												value="<%=ConstantesSistema.INDICADOR_USO_DESATIVO.toString()%>">
												<td width="7%">
												<div align="center"><input type="checkbox"
													name="idRegistrosRemocao"
													value="<bean:write name="categoria" property="id"/>" /></div>
												</td>
												<td width="12%" align="center"><bean:write name="categoria"
													property="id" /></td>
												<td width="30%"><html:link
													page="/exibirAtualizarCategoriaAction.do"
													paramName="categoria" paramProperty="id"
													paramId="idRegistroAtualizacao">
													<bean:write name="categoria" property="descricao" />
												</html:link></td>
												<td width="30%"><bean:write name="categoria"
													property="descricaoAbreviada" /></td>
												<td width="20%" align="center"><bean:write name="categoria"
													property="consumoMinimo" /></td>
											</logic:notEqual>
										</tr>
									</pg:item>
								</logic:iterate>
							</logic:present>
					</table>
					</td>
				</tr>
				<tr>
					<td colspan="5">
					<table width="100%" border="0">
						<tr>
						<tr>
							<td><!-- <input type="button" class="bottonRightCol" value="Remover"
								name="removerCategoria"
								onclick="verficarSelecao(idRegistrosRemocao, 'checkbox');" /> -->
								<gsan:controleAcessoBotao name="Button" value="Remover" onclick="verficarSelecao(idRegistrosRemocao, 'checkbox');" url="removerLocalidadeAction.do" align="left"/> 
								<input
								name="button" type="button" class="bottonRightCol"
								value="Voltar Filtro"
								onclick="window.location.href='<html:rewrite page="/exibirManterCategoriaAction.do"/>'">
							</td>
							<td align="right" valign="top">
							<div align="right"><a href="javascript:toggleBox('demodiv',1);">
							<img border="0"
								src="<bean:message key="caminho.imagens"/>print.gif"
								title="Imprimir Categorias" /> </a></div>
							</td>
						</tr>
					</table>
					</td>
				</tr>
			</table>
			<table width="100%" border="0">
				<tr>
					<td align="center"><strong><%@ include
						file="/jsp/util/indice_pager_novo.jsp"%></strong></td>
					</pg:pager>
				</tr>
			</table>
			<%-- Fim do esquema de paginação --%>

			<p>&nbsp;</p>
			<!-- Fim do Corpo - Roberta Costa --></td>
		</tr>
	</table>
	<jsp:include
		page="/jsp/relatorio/escolher_tipo_relatorio.jsp?relatorio=gerarRelatorioCategoriaManterAction.do" />
	<%@ include file="/jsp/util/rodape.jsp"%>
</html:form>
</body>
</html:html>
