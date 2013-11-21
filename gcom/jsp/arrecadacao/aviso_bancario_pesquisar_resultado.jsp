<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>
<%@ page import="gcom.util.ConstantesSistema,java.util.Collection"%>
<%@ page import="gcom.arrecadacao.aviso.AvisoBancario"%>

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
	function ValidarForm(){
		var formRed = "/gsan/inserirCategoriaFaixaConsumoTarifaAction.do";
			redirecionarSubmit(formRed);
	}
</script>
</head>
<logic:equal name="testeInserir" value="false" scope="request">
	<body leftmargin="0" topmargin="0" onload="resizePageSemLink(810,595);">
</logic:equal>
<body onload="resizePageSemLink(810,595);">
<html:form action="/pesquisarAvisoBancarioAction"
	name="PesquisarAvisoBancarioActionForm"
	type="gcom.gui.arrecadacao.PesquisarAvisoBancarioActionForm"
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
				<td class="parabg">Pesquisar Aviso Bancário</td>

				<td width="11"><img border="0"
					src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>

				</tr>
				</tbody>
			</table>
			<p>&nbsp;</p>
			<table width="100%" bgcolor="#90c7fc">
				<tbody>
					<tr align="left" bgcolor="#90c7fc">
						<td rowspan="2" width="10%">
						<div align="center"><strong>Arrecadador</strong></div>
						</td>
						<td rowspan="2" width="14%">
						<div align="center"><strong>Nome</strong> <strong>do Arrecadador</strong></div>
						</td>

						<td rowspan="2" width="8%">
						<div align="center">
						<p><strong>Data do</strong></p>
						<p><strong>Lançamento</strong></p>
						</div>
						</td>
						<td rowspan="2" width="4%">
						<div align="center"><strong>Seq.</strong></div>
						</td>
						<td rowspan="2" width="8%">
						<div align="center"><strong>Tipo do Aviso</strong></div>
						</td>

						<td>
						<div align="center"><strong>Previsão do Crédito/Débito</strong></div>
						</td>
						<td colspan="2">
						<div align="center"><strong>Realização do Crédito/Débito</strong></div>
						</td>
					</tr>
					<tr align="left" bgcolor="#90c7fc">
						<td width="14%" bgcolor="#cbe5fe">
						<div align="center"><strong>Data Prevista</strong></div>
						</td>
						<td width="14%" bgcolor="#cbe5fe">
						<div align="center"><strong>Data Realizada</strong></div>
						</td>

						<td width="16%" bgcolor="#cbe5fe">
						<div align="center"><strong>Valor Realizado</strong></div>
						</td>
					</tr>
					<%String cor = "#cbe5fe";%>
					<pg:pager isOffset="true" index="half-full" maxIndexPages="10"
				export="currentPageNumber=pageNumber;pageOffset"
				maxPageItems="10" items="${sessionScope.totalRegistros}">
						<pg:param name="pg" />
						<pg:param name="q" />
						<logic:present name="colecaoAvisoBancario" scope="session">
							<logic:iterate name="colecaoAvisoBancario" id="aviso" type="AvisoBancario">
								<pg:item>
									<%if (cor.equalsIgnoreCase("#cbe5fe")) {
					cor = "#FFFFFF";%>
									<tr bgcolor="#FFFFFF">
									<%} else {
					cor = "#cbe5fe";%>
									<tr bgcolor="#cbe5fe">
										<%}%>
										<td>
										<div align="center"><bean:write name="aviso"
											property="arrecadador.codigoAgente" /></div>
										</td>
										<td>
										<div><a
											href="javascript:enviarDadosCincoParametros('<bean:write name="aviso" property="id"/>', '<bean:write name="aviso" property="arrecadador.codigoAgente"/>', '<bean:write name="aviso" property="dataLancamento" format="dd/MM/yyyy"/>', '<bean:write name="aviso" property="numeroSequencial"/>', 'avisoBancario');">
										<bean:write name="aviso"
											property="arrecadador.cliente.nome" />
										</a></div>
										</td>
										<td>
										<div align="center"><bean:write name="aviso"
											property="dataLancamento" format="dd/MM/yyyy" /></div>
										</td>
										<td>
										<div align="center"><bean:write name="aviso"
											property="numeroSequencial" /></div>
										</td>
										<td>
										<div align="center"><bean:write name="aviso"
											property="indicadorCreditoDebito" /></div>
										</td>
										<td>
										<div align="center"><bean:write name="aviso"
											property="dataPrevista" format="dd/MM/yyyy" /></div>
										</td>
										<%--<td>
										<div align="center"><bean:write name="aviso"
											property="valorPrevisto" /></div>
										</td>--%>
										<td>
										<div align="center"><bean:write name="aviso"
											property="dataRealizada" format="dd/MM/yyyy"/></div>
										</td>
										<td>
										<div align="right"><bean:write name="aviso"
											property="valorRealizado" formatKey="money.format" /></div>
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
				<td height="24"><input type="button" class="bottonRightCol"
					value="Voltar Pesquisa"
					onclick="window.location.href='<html:rewrite page="/exibirPesquisarAvisoBancarioAction.do"/>'" /></td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
</html:form>
</body>
</html:html>
