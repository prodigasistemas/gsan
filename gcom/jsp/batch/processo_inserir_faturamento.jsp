<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>

<%@ page import="gcom.faturamento.*,gcom.util.Util"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<head>
<html:html>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript">
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

function verificarSubmit(objeto) {
	if (CheckboxNaoVazioMensagemGenerico('Selecione as atividades de faturamento para execução.', objeto)){
		//document.forms[0].submit();
		submitForm(document.forms[0]);
	} else {
		return false;
	}
}


</script>
</head>

<body leftmargin="5" topmargin="5"
	onload="javascript:setarFoco('${requestScope.nomeCampo}');">
<div id="formDiv">
<form action="/gsan/inserirProcessoFaturamentoComandadoAction.do"
	method="post"><input type="hidden" name="caminhoReload"
	value="/gsan/exibirInserirProcessoFaturamentoComandadoAction.do" /> <%@ include
	file="/jsp/util/cabecalho.jsp"%> <%@ include file="/jsp/util/menu.jsp"%>

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
				<td class="parabg">Iniciar Processo de Faturamento</td>
				<td width="11"><img border="0"
					src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>

			</tr>
		</table>

		<p>&nbsp;</p>
		<table width="100%" border="0">
			<tr>
				<td><strong> Atividades do cronograma de faturamento comandadas para
				execução:</strong></td>
			</tr>
		</table>
		<table width="100%" border="0" bgcolor="#99CCFF">
			<tr bgcolor="79BBFD">

				<td align="center" width="5%" rowspan="2"><a
					href="javascript:facilitador(this);"><strong>Todos</strong></A></td>

				<td align="center" width="10%" rowspan="2"><strong>Grupo</strong></td>
				<td align="center" width="10%" rowspan="2"><strong>Mês/Ano</strong></td>
				<td align="center" width="40%" rowspan="2"><strong>Atividade</strong></td>
				<td align="center" width="15%" rowspan="2"><strong>Data Prevista</strong></td>
				<td align="center" width="20%" colspan="2"><strong>Comando</strong></td>

			</tr>

			<tr>
				<td align="center" width="50%" bgcolor="#cbe5fe"><FONT
					COLOR="#000000"><strong>Data</strong></FONT></td>
				<td align="center" width="50%" bgcolor="#cbe5fe"><FONT
					COLOR="#000000"><strong>Hora</strong></FONT></td>
			</tr>
			<%--Esquema de paginação--%>
			<pg:pager isOffset="true" index="half-full" maxIndexPages="10"
				export="currentPageNumber=pageNumber;pageOffset" maxPageItems="10"
				items="${sessionScope.totalRegistros}">
				<pg:param name="pg" />
				<pg:param name="q" />

				<%int cont = 0;%>
				<logic:iterate name="colecaoFaturamentoAtividadeCronograma"
					id="faturamentoAtividadeCronograma"
					type="gcom.faturamento.FaturamentoAtividadeCronograma">
					<pg:item>

						<%cont = cont + 1;
							if (cont % 2 == 0) {%>
						<tr bgcolor="#cbe5fe">
							<%} else {%>
						<tr bgcolor="#FFFFFF">
							<%}%>


							<td align="center" width="5%"><input type="checkbox"
								name="idFaturamentoAtividadeCronograma"
								value="${faturamentoAtividadeCronograma.id}"></td>

							<td align="center" width="10%">${faturamentoAtividadeCronograma.faturamentoGrupoCronogramaMensal.faturamentoGrupo.id}</td>
							<td align="center" width="10%"><%=Util
													.formatarAnoMesParaMesAno(faturamentoAtividadeCronograma
															.getFaturamentoGrupoCronogramaMensal()
															.getAnoMesReferencia())%></td>
							<td align="center" width="40%">${faturamentoAtividadeCronograma.faturamentoAtividade.descricao}</td>
							<td align="center" width="15%"><bean:write
								name="faturamentoAtividadeCronograma" property="dataPrevista"
								formatKey="date.format" /></td>
							<td align="center" width="20%"><bean:write
								name="faturamentoAtividadeCronograma" property="comando"
								formatKey="date.format" /></td>
							<td align="center" width="20%"><bean:write
								name="faturamentoAtividadeCronograma" property="comando"
								formatKey="hour.format" /></td>
						</tr>
					</pg:item>
				</logic:iterate>
		</table>
		<table width="100%">
			<tr>
				<td width="50%"><input type="button"
					onclick="window.location.href='/gsan/telaPrincipal.do'"
					class="bottonRightCol" value="Cancelar" style="width: 70px;"></td>
				<td align="right"><input type="button"
					onclick="verificarSubmit(document.forms[0].idFaturamentoAtividadeCronograma);"
					class="bottonRightCol" value="Iniciar" style="width: 70px;"></td>

			</tr>
		</table>
		<table width="100%" border="0">
			<tr>
				<td align="center"><strong><%@ include
					file="/jsp/util/indice_pager_novo_parametro_url.jsp"%></strong></td>
			</tr>
		</table>
		</pg:pager></td>
	</tr>
</table>


<%@ include file="/jsp/util/rodape.jsp"%></form>
</div>

</body>

<%@ include file="/jsp/util/telaespera.jsp"%>

</html:html>
