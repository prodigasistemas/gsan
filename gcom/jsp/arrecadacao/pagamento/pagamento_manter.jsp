<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan"%>

<%@page import="gcom.util.ConstantesSistema,java.util.Collection"%>

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
			document.forms[0].action = "/gsan/removerPagamentoAction.do"
			document.forms[0].submit();
		 }
	}
}
//-->
</script>
</head>

<body leftmargin="5" topmargin="5">

<html:form action="/removerPagamentoAction"
	name="ManterPagamentoActionForm"
	type="gcom.gui.arrecadacao.pagamento.ManterPagamentoActionForm"
	method="post">

	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>

	<table width="770" border="0" cellspacing="5" cellpadding="0">
		<tr>
			<td width="150" valign="top" class="leftcoltext">
			<div align="center" style="width: 110px;">
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

			<td width="635" valign="top" class="centercoltext">

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
					<td class="parabg">Manter Pagamentos</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
				<tr>
					<td height="5" colspan="3"></td>
				</tr>
			</table>

			<table width="100%" cellpadding="0" cellspacing="0">
				<tr>
					<td colspan="11" height="23"><font color="#000000"
						style="font-size:10px"
						face="Verdana, Arial, Helvetica, sans-serif"> <strong> Pagamentos
					Encontrados: </strong> </font></td>
				</tr>

				<tr>
					<td colspan="11" bgcolor="#000000" height="2"></td>
				</tr>

				<tr>
					<td colspan="11">
					<table width="100%" bgcolor="#99CCFF">
						<tr bordercolor="#FFFFFF" bgcolor="#99CCFF">
							<td class="style1" align="center" rowspan="2"><strong> <font
								color="#000000" style="font-size:9px"
								face="Verdana, Arial, Helvetica, sans-serif"> <a
								onclick="this.focus();" id="0"
								href="javascript:facilitador(this);">Todos</a> </font> </strong>
							</td>

							<td rowspan="2" bordercolor="#000000" bgcolor="#90c7fc">
							<div align="center"><font color="#000000" style="font-size:9px"
								face="Verdana, Arial, Helvetica, sans-serif"> <strong>Imóvel</strong>
							</font></div>
							</td>

							<td rowspan="2" bordercolor="#000000" bgcolor="#90c7fc">
							<div align="center"><font color="#000000" style="font-size:9px"
								face="Verdana, Arial, Helvetica, sans-serif"> <strong>Cliente</strong>
							</font></div>
							</td>

							<td colspan="3" bordercolor="#000000" bgcolor="#90c7fc">
							<div align="center"><font color="#000000" style="font-size:9px"
								face="Verdana, Arial, Helvetica, sans-serif"> <strong>Aviso
							Bancário</strong> </font></div>
							</td>

							<td rowspan="2" bordercolor="#000000" bgcolor="#90c7fc">
							<div align="center"><font color="#000000" style="font-size:9px"
								face="Verdana, Arial, Helvetica, sans-serif"> <strong>Tipo do
							Documento</strong> </font></div>
							</td>

							<td rowspan="2" bordercolor="#000000" bgcolor="#90c7fc">
							<div align="center"><font color="#000000" style="font-size:9px"
								face="Verdana, Arial, Helvetica, sans-serif"> <strong>Mês/Ano
							Referência do Pagamento</strong> </font></div>
							</td>

							<td rowspan="2" bordercolor="#000000" bgcolor="#90c7fc">
							<div align="center"><font color="#000000" style="font-size:9px"
								face="Verdana, Arial, Helvetica, sans-serif"> <strong>Valor do
							Pagamento</strong> </font></div>
							</td>

							<td rowspan="2" bordercolor="#000000" bgcolor="#90c7fc">
							<div align="center"><font color="#000000" style="font-size:9px"
								face="Verdana, Arial, Helvetica, sans-serif"> <strong>Data do
							Pagamento</strong> </font></div>
							</td>


							<td colspan="9" bordercolor="#000000" bgcolor="#90c7fc">
							<div align="center"><font color="#000000" style="font-size:9px"
								face="Verdana, Arial, Helvetica, sans-serif"> <strong>Situação</strong>
							</font></div>
							</td>
						</tr>

						<tr bordercolor="#FFFFFF" bgcolor="#cbe5fe">
							<td bgcolor="#90c7fc">
							<div align="center"><font color="#000000" style="font-size:9px"
								face="Verdana, Arial, Helvetica, sans-serif"> <strong>Agente</strong>
							</font></div>
							</td>

							<td bgcolor="#90c7fc">
							<div align="center"><font color="#000000" style="font-size:9px"
								face="Verdana, Arial, Helvetica, sans-serif"> <strong>Data
							Lançamento</strong> </font></div>
							</td>

							<td bgcolor="#90c7fc">
							<div align="center"><font color="#000000" style="font-size:9px"
								face="Verdana, Arial, Helvetica, sans-serif"> <strong>Seq.</strong>
							</font></div>
							</td>

							<td bgcolor="#90c7fc">
							<div align="center"><font color="#000000" style="font-size:9px"
								face="Verdana, Arial, Helvetica, sans-serif"> <strong>Anterior</strong>
							</font></div>
							</td>

							<td bgcolor="#90c7fc">
							<div align="center"><font color="#000000" style="font-size:9px"
								face="Verdana, Arial, Helvetica, sans-serif"> <strong>Atual</strong>
							</font></div>
							</td>
						</tr>

						<%--Esquema de paginação--%>
						<pg:pager isOffset="true" index="half-full" maxIndexPages="10"
							export="currentPageNumber=pageNumber;pageOffset"
							maxPageItems="10" items="${sessionScope.totalRegistros}">
							<pg:param name="pg" />
							<pg:param name="q" />

							<logic:present name="colecaoPagamentos">
								<%int cont = 1;%>
								<logic:iterate name="colecaoPagamentos" id="pagamento">
									<pg:item>
										<%cont = cont + 1;
			if (cont % 2 == 0) {%>
										<tr bgcolor="#FFFFFF">
											<%} else {

			%>
										<tr bgcolor="#cbe5fe">
											<%}%>

											<td>
											<div align="center"><input type="checkbox"
												name="idRegistrosRemocao"
												value="<bean:write name="pagamento" property="id"/>" /></div>
											</td>

											<td>
											<div align="center"><logic:notPresent name="pagamento"
												property="imovel">
												<font color="#000000" style="font-size:9px"
													face="Verdana, Arial, Helvetica, sans-serif"> &nbsp; </font>
											</logic:notPresent> <logic:present name="pagamento"
												property="imovel">
												<font color="#000000" style="font-size:9px"
													face="Verdana, Arial, Helvetica, sans-serif"> <bean:write
													name="pagamento" property="imovel.id" /> </font>
											</logic:present></div>
											</td>

											<td>
											<div align="center"><logic:notPresent name="pagamento"
												property="cliente">
												<font color="#000000" style="font-size:9px"
													face="Verdana, Arial, Helvetica, sans-serif"> &nbsp; </font>
											</logic:notPresent> <logic:present name="pagamento"
												property="cliente">
												<font color="#000000" style="font-size:9px"
													face="Verdana, Arial, Helvetica, sans-serif"> <bean:write
													name="pagamento" property="cliente.id" /> </font>
											</logic:present></div>
											</td>

											<td align="center"><font color="#000000"
												style="font-size:9px"
												face="Verdana, Arial, Helvetica, sans-serif"> <bean:write
												name="pagamento"
												property="avisoBancario.arrecadador.codigoAgente" /> </font>
											</td>

											<td align="center"><font color="#000000"
												style="font-size:9px"
												face="Verdana, Arial, Helvetica, sans-serif"> <bean:write
												name="pagamento" property="avisoBancario.dataLancamento"
												formatKey="date.format" /> </font></td>

											<td align="center"><font color="#000000"
												style="font-size:9px"
												face="Verdana, Arial, Helvetica, sans-serif"> <bean:write
												name="pagamento" property="avisoBancario.numeroSequencial" />
											</font></td>

											<td><html:link page="/exibirAtualizarPagamentosAction.do"
												paramName="pagamento" paramProperty="id"
												paramId="idRegistroAtualizacao">

												<font color="#000000" style="font-size:9px"
													face="Verdana, Arial, Helvetica, sans-serif"> <bean:write
													name="pagamento"
													property="documentoTipo.descricaoDocumentoTipo" /> </font>

											</html:link></td>

											<td align="center"><logic:present
												name="pagamento" property="anoMesReferenciaPagamento">
												<font color="#000000">${pagamento.formatarAnoMesPagamentoParaMesAno}</font>
											</logic:present></td>

											<td>

											<div align="right"><font color="#000000"
												style="font-size:9px"
												face="Verdana, Arial, Helvetica, sans-serif"> <bean:write
												name="pagamento" property="valorPagamento"
												formatKey="money.format" /> </font></div>
											</td>

											<td align="center"><font color="#000000"
												style="font-size:9px"
												face="Verdana, Arial, Helvetica, sans-serif"> <bean:write
												name="pagamento" property="dataPagamento"
												formatKey="date.format" /> </font></td>

											<td>

											<div align="center"><logic:notPresent name="pagamento"
												property="pagamentoSituacaoAnterior">
												<font color="#000000" style="font-size:9px"
													face="Verdana, Arial, Helvetica, sans-serif"> &nbsp; </font>
											</logic:notPresent> <logic:present name="pagamento"
												property="pagamentoSituacaoAnterior">
												<font color="#000000" style="font-size:9px"
													face="Verdana, Arial, Helvetica, sans-serif"> <bean:write
													name="pagamento"
													property="pagamentoSituacaoAnterior.descricaoAbreviada" />
												</font>
											</logic:present></div>
											</td>

											<td>

											<div align="center"><logic:notPresent name="pagamento"
												property="pagamentoSituacaoAtual">
												<font color="#000000" style="font-size:9px"
													face="Verdana, Arial, Helvetica, sans-serif"> &nbsp; </font>
											</logic:notPresent> <logic:present name="pagamento"
												property="pagamentoSituacaoAtual">
												<font color="#000000" style="font-size:9px"
													face="Verdana, Arial, Helvetica, sans-serif"> <bean:write
													name="pagamento"
													property="pagamentoSituacaoAtual.descricaoAbreviada" /> </font>
											</logic:present></div>
											</td>

										</tr>

									</pg:item>
								</logic:iterate>
							</logic:present>
					</table>

					<table width="100%">
						<tr>
							<td><gsan:controleAcessoBotao name="Button" value="Remover"
								onclick="javascript:verficarSelecao(idRegistrosRemocao);"
								url="removerPagamentoAction.do" align="left" /> <%-- <input name="Button" type="button" class="bottonRightCol" value="Remover" onclick="verficarSelecao(idRegistrosRemocao)" align="left" style="width: 70px;"> --%>
							<input name="button" type="button" class="bottonRightCol"
								value="Voltar Filtro"
								onclick="window.location.href='<html:rewrite page="/exibirFiltrarPagamentoAction.do?tela=manterPagamento"/>'"
								align="left" style="width: 80px;"></td>

							<td align="right">
							<div align="right"><a href="javascript:toggleBox('demodiv',1);">
							<img border="0"
								src="<bean:message key="caminho.imagens"/>print.gif"
								title="Imprimir Pagamentos" /> </a></div>
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
			</td>
		</tr>
	</table>
	<jsp:include
		page="/jsp/relatorio/escolher_tipo_relatorio.jsp?relatorio=gerarRelatorioPagamentoAction.do" />
	<%@ include file="/jsp/util/rodape.jsp"%>
</body>

</html:form>
</html:html>
