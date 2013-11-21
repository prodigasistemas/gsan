<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>

<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan"%>


<%@page import="gcom.faturamento.debito.DebitoTipo"%>



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
			document.forms[0].action = "/gsan/removerTipoDebitoAction.do"
			document.forms[0].submit();
		 }
	}
 }
-->
</script>

</head>



<body leftmargin="5" topmargin="5">



<html:form action="/removerTipoDebitoAction"
	name="ManutencaoRegistroActionForm"
	type="gcom.gui.ManutencaoRegistroActionForm" method="post"
	onsubmit="return CheckboxNaoVazio(document.ManutencaoRegistroActionForm.idRegistrosRemocao) && confirm('Confirmar exclusao?')">



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

					<td class="parabg">Manter Tipo Débito</td>

					<td width="11" valign="top"><img border="0"
						src="imagens/parahead_right.gif" /></td>



				</tr>

			</table>



			<table width="100%" cellpadding="0" cellspacing="0">

				<tr>

					<td colspan="7" height="23"><font style="font-size: 10px;"
						color="#000000" face="Verdana, Arial, Helvetica, sans-serif"> <strong>Tipo(s)

					de Débito(s) Cadastrado(s):</strong> </font></td>

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



									<td width="12%">

									<div align="center"><strong>Código</strong></div>

									</td>

									<td width="29%">

									<div align="center"><strong>Descrição</strong></div>

									</td>

									<td width="15%">

									<div align="center"><strong>Valor Sugerido</strong></div>

									</td>

									<td width="20%">

									<div align="center"><strong>Débito Automático</strong></div>

									</td>

									<td width="20%">

									<div align="center"><strong>Débito Conta</strong></div>

									</td>



								</tr>

								<pg:pager isOffset="true" index="half-full" maxIndexPages="10"
									export="currentPageNumber=pageNumber;pageOffset"
									maxPageItems="10" items="${sessionScope.totalRegistros}">

									<pg:param name="pg" />

									<pg:param name="q" />

									<%int cont = 0;%>

									<logic:iterate name="colecaoDebitoTipo" id="debitoTipo"
										type="DebitoTipo">

										<pg:item>

											<%cont = cont + 1;

			if (cont % 2 == 0) {%>

											<tr bgcolor="#FFFFFF">

												<%} else {%>
											<tr bgcolor="#cbe5fe">

												<%}%>



												<td width="7%">

												<div align="center"><input type="checkbox"
													name="idRegistrosRemocao"
													value="<bean:write name="debitoTipo" property="id"/>"></div>

												</td>

												<td width="12%">

												<div align="center"><bean:write name="debitoTipo"
													property="id" /></div>

												</td>



												<td width="29%" align="center"><html:link
													href="/gsan/exibirAtualizarTipoDebitoAction.do?manter=sim"
													paramId="idDebitoTipo" paramProperty="id"
													paramName="debitoTipo"
													title="<%="" + debitoTipo.getDescricao()%>">

													<bean:write name="debitoTipo" property="descricao" />

												</html:link></td>


												<td width="15%">

												<div align="center"><bean:write name="debitoTipo"
													property="valorSugerido" formatKey="money.format" />
												</td>


												<td width="20%" align="center"><logic:equal
													name="debitoTipo" property="indicadorGeracaoAutomatica"
													value="1">
											SIM
											</logic:equal> <logic:equal name="debitoTipo"
													property="indicadorGeracaoAutomatica" value="2">
											NÃO
											</logic:equal></td>



											<td width="20%" align="center"><logic:equal
													name="debitoTipo" property="indicadorGeracaoConta"
													value="1">
											SIM
											</logic:equal> <logic:equal name="debitoTipo"
													property="indicadorGeracaoConta" value="2">
											NÃO
											</logic:equal></td>







											</tr>

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

									<td><font color="#FF0000"><gsan:controleAcessoBotao
										name="buttonRemover" value="Remover"
										onclick="javascript:verficarSelecao(idRegistrosRemocao);"
										url="removerTipoDebitoAction.do" /> </font> <input
										name="button" type="button" class="bottonRightCol"
										value="Voltar Filtro"
										onclick="window.location.href='<html:rewrite page="/exibirFiltrarTipoDebitoAction.do?limpar=S"/>'"
										align="left" style="width: 80px;"></td>
							
										<td valign="top">
											<div align="right"><a
											href="javascript:toggleBox('demodiv',1);">
											<img border="0"
											src="<bean:message key="caminho.imagens"/>print.gif"
											title="Imprimir Tipo de Debito" /> </a></div>
										</td>
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
	<jsp:include page="/jsp/relatorio/escolher_tipo_relatorio.jsp?relatorio=gerarRelatorioTipoDebitoManterAction.do"/>
	<%@ include file="/jsp/util/rodape.jsp"%>
</body>

</html:form>

</html:html>

