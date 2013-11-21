<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>

<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan"%>
<%@ page
	import="gcom.util.Pagina,gcom.util.ConstantesSistema,java.util.Collection"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
<head>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js">
</script>
<script>
<!--
function facilitador(objeto){
	if (objeto.value == "0" || objeto.value == undefined){
		objeto.value = "1";
		marcarTodos();
	}
	else{
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
			redirecionarSubmit("/gsan/removerSubcategoriaAction.do");
		}
		}
   }
-->
</script>
</head>
<body leftmargin="5" topmargin="5">
<html:form action="/removerSubcategoriaAction"
	name="ManutencaoRegistroActionForm"
	type="gcom.gui.ManutencaoRegistroActionForm" method="post">
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
			
			 <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
		        <tr>
		          <td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_left.gif"/></td>
		          <td class="parabg">Manter Subcategoria</td>
		          <td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif"/></td>
		        </tr>
		      </table>
			

			<!-- Início do Corpo -->
			<p>&nbsp;</p>
			<table width="100%" cellpadding="0" cellspacing="0">
				<tr>
					<td height="23"><font color="#000000"
						style="font-size:10px"
						face="Verdana, Arial, Helvetica, sans-serif"> <strong>Subcategorias
					Encontradas:</strong> </font></td>
					<logic:present scope="application" name="urlHelp">
						<td align="right"><a href="javascript:abrirPopupHelp('${applicationScope.urlHelp}cadastroSubcategoriaManter', 500, 700);"><span style="font-weight: bold"><font color="#3165CE">Ajuda</font></span></a></td>									
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
				<tr bordercolor="#000000">
					<td width="7%" bgcolor="#90c7fc" align="center"><strong><a
						onclick="this.focus();" id="0"
						href="javascript:facilitador(this);">Todos</a></strong></td>
					<td width="12%" align="center" bgcolor="#90c7fc"><strong>C&oacute;digo</strong></td>
					<td width="34%" align="center" bgcolor="#90c7fc"><strong>Descrição</strong></td>
					<td width="34%" align="center" bgcolor="#90c7fc"><strong>Categoria</strong></td>
				</tr>
				<%--Esquema de paginação--%>

				<%int cont = 0;%>

				<tr>
					<td colspan="5">
					<table width="100%" bgcolor="#99CCFF">
						<pg:pager isOffset="true" index="half-full" maxIndexPages="10"
							export="currentPageNumber=pageNumber;pageOffset"
							maxPageItems="10" items="${sessionScope.totalRegistros}">
							<pg:param name="pg" />
							<pg:param name="q" />
							<logic:present name="subcategorias">
								<logic:iterate name="subcategorias" id="subcategoria">
									<pg:item>

										<%cont = cont + 1;
			if (cont % 2 == 0) {%>
										<tr bgcolor="#cbe5fe">
											<%} else {%>
										<tr bgcolor="#FFFFFF">
											<%}%>
											<logic:equal name="subcategoria" property="indicadorUso"
												value="<%=ConstantesSistema.INDICADOR_USO_DESATIVO.toString()%>">
												<td width="7%">
												<div align="center"><input type="checkbox"
													name="idRegistrosRemocao"
													value="<bean:write name="subcategoria" property="id"/>" />
												</div>
												</td>
												<td align="center" width="12%"><font color="#CC0000"> <bean:write
													name="subcategoria" property="codigo" /> </font></td>
												<td align="left" width="34%"><a
													href="exibirAtualizarSubcategoriaAction.do?manter=sim&idRegistroAtualizacao=<bean:write
														name="subcategoria" property="id" />">
												<font color="#CC0000"> <bean:write name="subcategoria"
													property="descricao" /> </font> </a></td>
												<td align="left" width="34%"><font color="#CC0000"> <bean:write
													name="subcategoria" property="categoria.descricao" /> </font>
												</td>
											</logic:equal>
											<logic:notEqual name="subcategoria" property="indicadorUso"
												value="<%=ConstantesSistema.INDICADOR_USO_DESATIVO.toString()%>">
												<td width="7%">
												<div align="center"><input type="checkbox"
													name="idRegistrosRemocao"
													value="<bean:write name="subcategoria" property="id"/>" />
												</div>
												</td>
												<td align="center" width="12%"><bean:write
													name="subcategoria" property="codigo" /></td>
												<td width="34%" align="left"><a
													href="exibirAtualizarSubcategoriaAction.do?manter=sim&idRegistroAtualizacao=<bean:write
														name="subcategoria" property="id" />">
												<bean:write name="subcategoria" property="descricao" /> </a>
												</td>
												<td width="34%" align="left"><bean:write name="subcategoria"
													property="categoria.descricao" /></td>
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
							<td valign="top">
							<gsan:controleAcessoBotao name="Button" value="Remover" onclick="verficarSelecao(idRegistrosRemocao, 'checkbox');" url="removerSubcategoriaAction.do" align="left"/><!-- 
							<input name="Remover" type="button"
								class="bottonRightCol" value="Remover"
								onclick="verficarSelecao(idRegistrosRemocao, 'checkbox')"> 
							-->	<%-- 
								<logic:present scope="session" name="filtrar_manter">
										<input name="Submit222" class="bottonRightCol"
										value="Voltar Filtro" type="button"
										onclick="window.location.href='<html:rewrite page="/exibirFiltrarSubcategoriaAction.do"/>'"
										onclick="javascript:history.back();"
										>
								</logic:present> 
								<logic:notPresent scope="session" name="filtrar_manter">--%>
										<input name="button" type="button" class="bottonRightCol"
										value="Voltar Filtro"
										onclick="window.location.href='<html:rewrite page="/exibirManterSubcategoriaAction.do"/>'"
										align="left" style="width: 80px;">
								<%--</logic:notPresent>--%>
							</td>
							<td valign="top">
							<div align="right"><a href="javascript:toggleBox('demodiv',1);">
							<img border="0"
								src="<bean:message key="caminho.imagens"/>print.gif"
								title="Imprimir Subcategorias" /> </a></div>
							</td>
						</tr>
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
					<%-- Fim do esquema de paginação --%>
				</tr>
			</table>
		
			<!-- Fim do Corpo - Roberta Costa --></td>
		</tr>
	</table>
	<jsp:include
		page="/jsp/relatorio/escolher_tipo_relatorio.jsp?relatorio=gerarRelatorioSubcategoriaManterAction.do" />
	<%@ include file="/jsp/util/rodape.jsp"%>
</html:form>
</body>
</html:html>
