<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/strujava.lang.String error = (java.lang.String) pageContext.getAttribute("error");c"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan"%>

<%@page import="gcom.cadastro.imovel.Contrato"%>

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

<script language="JavaScript">
<!-- Begin

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
			document.forms[0].action = "/gsan/removerContratoDemandaAction.do"
			document.forms[0].submit();
		 }
	}
 }

-->
</script>

</head>

<body leftmargin="5" topmargin="5">

<html:form action="/removerContratoDemandaAction"
	name="ManutencaoRegistroActionForm"
	type="gcom.gui.ManutencaoRegistroActionForm" method="post"
	onsubmit="return CheckboxNaoVazio(document.ManutencaoRegistroActionForm.idRegistrosRemocao) && confirm('Confirma remoção?')">

	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ inclujava.lang.Object grupo = (java.lang.Object) pageContext.getAttribute("grupo");pacing="5" cellpadding="0">
		<tr>
			<td width="150" valign="top" class="leftcoltext">
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
					<td width="11"><img border="0" src="imagens/parahead_left.gif" /></td>
					<td class="parabg">Manter Contrato de Demanda</td>
					<td width="11" valign="top"><img border="0"
						src="imagens/parahead_right.gif" /></td>
				</tr>
			</table>
			<!--Fim Tabela Reference a Páginação da Tela de Processo-->
			<p>&nbsp;</p>
			<table width="100%" align="center" cellpadding="0" cellspacing="0">
				<!--corpo da segunda tabela-->
				<tr>
					<td colspan="5" height="23"><font color="#000000"
						style="font-size:10px"
						face="Verdana, Arial, Helvetica, sans-serif"><strong>Contratos de
					Demanda Encontradas:</strong></font></td>
				</tr>
				<tr>
					<!--<td colspan="4" bgcolor="#3399FF"> -->
					<td colspan="5" bgcolor="#000000" height="2" valign="baseline"></td>
				</tr>
				<tr>
					<td>
					<table width="100%" align="center" bgcolor="#90c7fc">
						<tr bgcolor="#79bbfd">
							<td width="7%" bgcolor="#90c7fc">
							<div align="center"><strong><a
								href="javascript:facilitador(this);">Todos</a></strong></div>
							</td>
							<td width="24%" bgcolor="#90c7fc">
							<div align="center"><strong>Número Contrato</strong></div>
							</td>
							<td width="19%" bgcolor="#90c7fc">
							<div align="center"><strong>Imóvel</strong></div>
							</td>
							<td width="25%" bgcolor="#90c7fc">
							<div align="center"><strong>Data Início Contrato</strong></div>
							</td>
							<td width="25%" bgcolor="#90c7fc">
							<div align="center"><strong>Data Fim Contrato</strong></div>
							</td>
						</tr>
						<%--Esquema de paginação--%>
						java.lang.Integer pageOffset = (java.lang.Integer) pageContext.getAttribute("pageOffset");
							<pg:param name="pg" />
							<pg:param name="q" />
							<logic:present name="colecaoContratoDemanda">
								<%
									int cont = 0;
								%>
								Contrato contratoDemanda = (Contrato) pageContext.getAttribute("contratoDemanda");
									<pg:item>
										<%cont = cont + 1;
			if (cont % 2 == 0) {%>
										<tr bgcolor="#cbe5fe">
											<%} else {

			%>
										<tr bgcolor="#FFFFFF">
											<%}%>
											<td width="7%">
											<div align="center"><input type="checkbox"
												name="idRegistrosRemocao"
												value="<bean:write name="contratoDemanda" property="id"/>" /></div>
											</td>
											<td width="24%" align="center"><html:link
												href="/gsan/exibirAtualizarContratoDemandaAction.do"
												paramId="contratoDemandaID" paramProperty="id"
												paramName="contratoDemanda"
												title="<%="" + contratoDemanda.getId()%>">
												<bean:write name="contratoDemanda" property="numeroContrato" />
											</html:link></td>
											<td width="19%" align="center">${contratoDemanda.imovel.id}</td>
											<td width="25%" align="center"><logic:notEmpty
												name="contratoDemanda" property="dataContratoInicio">
												<bean:write name="contratoDemanda"
													property="dataContratoInicio" formatKey="date.format" />
											</logic:notEmpty>&nbsp;</td>
											<td width="25%" align="center"><logic:notEmpty
												name="contratoDemanda" property="dataContratoFim">
												<bean:write name="contratoDemanda"
													property="dataContratoFim" formatKey="date.format" />
											</logic:notEmpty>&nbsp;</td>
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
					<td width="233"><font color="#FF0000"> <gsan:controleAcessoBotao
						name="buttonRemover" value="Remover"
						onclick="javascript:verficarSelecao(idRegistrosRemocao);"
						url="removerContratoDemandaAction.do" /> </font> <font
						color="#FF0000"> <input type="button" name="buttonFiltro"
						class="bottonRightCol" value="Voltar Filtro"
						onClick="javascript:window.location.href='/gsan/exibirFiltrarContratoDemandaAction.do?paginacao=sim'">
					</font></td>
				</tr>
			</table>
			</td>
		</tr>
	</table>

	<%@ include file="/jsp/util/rodape.jsp"%>
</html:form>
</body>
</html:html>
