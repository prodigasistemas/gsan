<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>

<%@ page import="gcom.util.ConstantesSistema"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>

<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false"
	formName="InserirArrecadacaoFormaActionForm" />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>

<script language="JavaScript">


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

function remover(objeto){
	if (CheckboxNaoVazio(objeto)){
		if (confirm ("Confirma remoção?")) {
			document.forms[0].action = "removerArrecadacaoFormaAction.do"
			document.forms[0].submit();
		 }
	}
}

</script>
</head>

<body leftmargin="5" topmargin="5">
<html:form action="/removerArrecadacaoFormaAction"
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
					<td class="parabg">Manter Forma de Arrecada&ccedil;&atilde;o</td>
					<td width="11" valign="top"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<table width="100%" cellpadding="0" cellspacing="0" border="0">
				<tr>
					<td colspan="11"><font color="#000000" style="font-size:10px"
						face="Verdana, Arial, Helvetica, sans-serif"> <strong>Forma(s) de
					Arrecada&ccedil;&atilde;o encontrada(s):</strong> </font></td>
				</tr>
			</table>
			<table width="100%" cellpadding="0" cellspacing="0">
				<tr>
					<td colspan="4" bgcolor="#000000" height="2"></td>
				</tr>
				<tr>
					<td>
					<table width="100%" bgcolor="#99CCFF" border="0">
						<tr bordercolor="#000000">
							<td bgcolor="#90c7fc" width="78">
							<div align="center" ><strong><a
										href="javascript:facilitador(this);">Todos</a></strong></div>
							<td bgcolor="#90c7fc">
							<div align="center"><strong>Identificador</strong></div>
							<td bgcolor="#90c7fc" width="135">
							<div align="center"><strong>C&oacute;digo</strong></td>
							<td bgcolor="#90c7fc" width="236">
							<div align="center"><strong>Descri&ccedil;&atilde;o</strong></td>
						</tr>
					</table>
					</td>
				</tr>
				<tr>
					<td>
					<table width="100%" bgcolor="#99CCFF">
						<pg:pager isOffset="true" index="half-full" maxIndexPages="10"
							export="currentPageNumber=pageNumber;pageOffset"
							maxPageItems="10" items="${sessionScope.totalRegistros}">
							<pg:param name="pg" />
							<pg:param name="q" />
							<logic:present name="colecaoArrecadacaoForma">
								<%int cont = 0;%>
								<logic:iterate name="colecaoArrecadacaoForma" id="ArrecadacaoForma"
									scope="request">
									<pg:item>
										<%cont = cont + 1;
			if (cont % 2 == 0) {%>
										<tr bgcolor="#cbe5fe">
											<%} else {%>
										<tr bgcolor="#FFFFFF">
											<%}%>
											<td width="7%">
											<div align="center"><input type="checkbox"
												name="idRegistrosRemocao" value="${ArrecadacaoForma.id}"></div>
											</td>
											<td width="12%" align="center">${ArrecadacaoForma.id}</td>
											<td width="12%" align="center">${ArrecadacaoForma.codigoArrecadacaoForma}</td>
											<td width="21%" align="center"><html:link
												page="/exibirAtualizarArrecadacaoFormaAction.do"
												paramName="ArrecadacaoForma" paramProperty="id"
												paramId="idRegistroAtualizacao">${ArrecadacaoForma.descricao}
													</html:link></td>

											<html:link href="/gsan/exibirManterArrecadacaoFormaAction.do"
												paramId="codigo" paramProperty="id" paramName="ArrecadacaoForma">


											</html:link>

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
					<td width="233">
						<font color="#FF0000"> 
							<input name="Button"
							type="button" class="bottonRightCol" value="Remover" align="left"
							onclick="remover(document.ManutencaoRegistroActionForm.idRegistrosRemocao);" />
							<input name="button" type="button" class="bottonRightCol"
							tabindex="2" value="Voltar Filtro"
							onclick="window.location.href='<html:rewrite page="/exibirFiltrarArrecadacaoFormaAction.do"/>'">
						</font>
					</td>
                    <td align="right" valign="top">
						<a href="javascript:toggleBox('demodiv',1);">
                        	<img align="right" border="0" src="<bean:message key='caminho.imagens'/>print.gif"  title="Imprimir Formas de Arrecadação"/>
						</a>
					</td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
		<jsp:include page="/jsp/relatorio/escolher_tipo_relatorio.jsp?relatorio=gerarRelatorioArrecadacaoFormaManterAction.do"/>
	<%@ include file="/jsp/util/rodape.jsp"%>
</html:form>
</body>
</html:html>
