<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan" %>
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
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script>
<!--
function facilitador(objeto){
	if (objeto.value == "0"){
		objeto.value = "1";
		marcarTodos();
	}
	else{
		objeto.value = "0";
		desmarcarTodos();
	}
}

function validarForm(form){
	if (CheckboxNaoVazio(form.idRegistrosRemocao)){
		if (confirm ("Confirma remoção?")) {
			form.action = "/gsan/removerBairroAction.do";
			form.submit();
		 }
	}
}
-->
</script>

</head>

<body leftmargin="5" topmargin="5">

<html:form action="/removerBairroAction"
	name="ManutencaoRegistroActionForm"
	type="gcom.gui.ManutencaoRegistroActionForm" method="post"
	onsubmit="return CheckboxNaoVazio(document.ManutencaoRegistroActionForm.idRegistrosRemocao) && confirm('Confirma remoção?')">

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
			<td valign="top" bgcolor="#003399" class="centercoltext">
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
					<td class="parabg">Manter Bairro</td>

					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
				<tr>
					<td height="5" colspan="3"></td>
				</tr>
			</table>


			<table width="100%" cellpadding="0" cellspacing="0">
				<tr>
					<td colspan="5" height="23"><font color="#000000"
						style="font-size:10px"
						face="Verdana, Arial, Helvetica, sans-serif"><strong>Bairros
					Encontrados:</strong></font>
					</td>
					<logic:present scope="application" name="urlHelp">
						<td align="right"><a href="javascript:abrirPopupHelp('${applicationScope.urlHelp}cadastroEnderecoBairroManter', 500, 700);"><span style="font-weight: bold"><font color="#3165CE">Ajuda</font></span></a></td>									
					</logic:present>
					<logic:notPresent scope="application" name="urlHelp">
						<td align="right"><span style="font-weight: bold"><font color=#696969><u>Ajuda</u></font></span></td>									
					</logic:notPresent>
					</tr>
				</table>
			
	  			<table width="100%" cellpadding="0" cellspacing="0">
				<tr>
					<!--<td colspan="4" bgcolor="#3399FF"> -->
					<td colspan="5" bgcolor="#000000" height="2"></td>
				</tr>

				<tr bordercolor="#000000">

					<td width="7%" bgcolor="#90c7fc">
					<div align="center"><strong><a href="javascript:facilitador(this);">Todos</a></strong></div>
					</td>
					<td width="12%" align="center" bgcolor="#90c7fc"><strong>C&oacute;d.Bairro</strong></td>
					<td width="34%" align="center" bgcolor="#90c7fc"><strong>Nome do
					Bairro</strong></td>
					<td width="34%" align="center" bgcolor="#90c7fc"><strong>Munic&iacute;pio</strong></td>
					<td width="12%" align="center" bgcolor="#90c7fc"><strong>C&oacute;d.
					Pref.</strong></td>
				</tr>
				<tr>
					<td colspan="5">
					<table width="100%" bgcolor="#99CCFF">


						<%--Esquema de paginação--%>
						<pg:pager isOffset="true" index="half-full" maxIndexPages="10"
							export="currentPageNumber=pageNumber;pageOffset"
							maxPageItems="10" items="${sessionScope.totalRegistros}">
							<pg:param name="q" />
							<logic:present name="bairros">
								<%int cont = 0;%>
								<logic:iterate name="bairros" id="bairro">
									<pg:item>
										<%cont = cont + 1;
			if (cont % 2 == 0) {%>
										<tr bgcolor="#cbe5fe">
											<%} else {

			%>
										<tr bgcolor="#FFFFFF">
											<%}%>
											<logic:equal name="bairro" property="indicadorUso"
												value="<%=ConstantesSistema.INDICADOR_USO_DESATIVO.toString()%>">
												<td width="7%">
												<div align="center"><input type="checkbox"
													name="idRegistrosRemocao"
													value="<bean:write name="bairro" property="id"/>" /></div>
												</td>
												<td width="12%" align="center"><font color="#CC0000">
												<div align="center"><bean:write name="bairro"
													property="codigo" /></div>
												</font></td>
												<td width="34%"><a
													href="exibirAtualizarBairroAction.do?manter=sim&idRegistroAtualizacao=<bean:write
													name="bairro" property="id" />">
												<font color="#CC0000"> <bean:write name="bairro"
													property="nome" /> </font> </a></td>
												<td width="34%"><font color="#CC0000"> <bean:write
													name="bairro" property="municipio.nome" /> </font></td>
												<td width="12%"><font color="#CC0000">
												<div align="center"><bean:write name="bairro"
													property="codigoBairroPrefeitura" /></div>
												</font></td>
										</tr>
										</logic:equal>
										<logic:notEqual name="bairro" property="indicadorUso"
											value="<%=ConstantesSistema.INDICADOR_USO_DESATIVO.toString()%>">
											<td width="7%">
											<div align="center"><input type="checkbox"
												name="idRegistrosRemocao"
												value="<bean:write name="bairro" property="id"/>" /></div>
											</td>
											<td width="12%">
											<div align="center"><bean:write name="bairro"
												property="codigo" /></div>
											</td>
											<td width="34%"><a
												href="exibirAtualizarBairroAction.do?manter=sim&idRegistroAtualizacao=<bean:write
													name="bairro" property="id" />">
											<bean:write name="bairro" property="nome" /> </a></td>
											<td width="34%"><bean:write name="bairro"
												property="municipio.nome" /></td>
											<td width="12%">
											<div align="center"><bean:write name="bairro"
												property="codigoBairroPrefeitura" /></div>
											</td>

										</logic:notEqual>
									</pg:item>
								</logic:iterate>
							</logic:present>
					</table>
					<table width="100%" border="0">
						<tr>
							<td valign="top">
							<gsan:controleAcessoBotao name="Button" value="Remover"
							  onclick="javascript:validarForm(document.forms[0]);" url="removerBairroAction.do"/>
					<!--
							<html:submit styleClass="bottonRightCol"
								value="Remover" property="Button" /> --> <input name="button"
								type="button" class="bottonRightCol" value="Voltar Filtro"
								onclick="window.location.href='/gsan/exibirFiltrarBairroAction.do'"
								align="left" style="width: 80px;"></td>
							<td valign="top">
							<div align="right"><a href="javascript:toggleBox('demodiv',1);">
							<img border="0"
								src="<bean:message key="caminho.imagens"/>print.gif"
								title="Imprimir Bairros" /> </a></div>
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
	</table>
	<jsp:include
		page="/jsp/relatorio/escolher_tipo_relatorio.jsp?relatorio=gerarRelatorioBairroManterAction.do" />
	<%@ include file="/jsp/util/rodape.jsp"%>
</body>
</html:form>
</html:html>
