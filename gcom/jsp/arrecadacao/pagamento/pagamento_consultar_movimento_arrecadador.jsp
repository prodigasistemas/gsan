<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page import="gcom.util.ConstantesSistema" isELIgnored="false"%>
<%@page import="gcom.arrecadacao.pagamento.Pagamento"%>
<%@page import="gcom.arrecadacao.pagamento.PagamentoHistorico"%>
<%@page import="gcom.util.Util" isELIgnored="false"%>

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
	src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript">
<!--

	
-->
</script>

<style>
.fontePequena {
font-size: 11px;
face: Verdana, Arial, Helvetica, sans-serif;
}

</style>

</head>

<body leftmargin="5" topmargin="5">

<html:form action="/exibirConsultarPagamentoAction" method="post">

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
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_left.gif" /></td>
					<td class="parabg">Consultar Pagamentos do Movimento de Arrecadador</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<table width="100%" border="0" style="fonteReduzida">
				<tr>
					<td>
					<table width="100%" border="0">
						<tr>
							<td><strong>Arrecadador:</strong></td>
							<td><html:text property="idArrecadador" readonly="true"
								style="background-color:#EFEFEF; border:0" size="3"
								maxlength="3" />&nbsp; <html:text
								property="descricaoArrecadador" readonly="true"
								style="background-color:#EFEFEF; border:0;" size="40"
								maxlength="40" /></td>
						</tr>
						<tr>
							<td><strong>Tipo de Remessa:</strong></td>
							<td colspan="3"><html:text property="codigoRemessa"
								readonly="true" style="background-color:#EFEFEF; border:0;"
								size="10" maxlength="10" /></td>
						</tr>
						<tr>
							<td><strong>Identificação do serviço:</strong></td>
							<td colspan="3"><html:text property="identificacaoServico"
								readonly="true" style="background-color:#EFEFEF; border:0;"
								size="20" maxlength="20" /></td>
						</tr>
						<tr>
							<td><strong>Número Sequencial(NSA):</strong></td>
							<td colspan="3"><html:text property="numeroSequencial"
								readonly="true" style="background-color:#EFEFEF; border:0;"
								size="3" maxlength="3" /></td>
						</tr>
						<tr>
							<td><strong>Data de Geração:</strong></td>
							<td colspan="3"><html:text property="dataGeracao" readonly="true"
								style="background-color:#EFEFEF; border:0;" size="10"
								maxlength="10" /></td>
						</tr>

						<tr>
							<td><strong>Total de Registros:</strong></td>
							<td colspan="3"><html:text property="numeroRegistrosMovimento"
								readonly="true" style="background-color:#EFEFEF; border:0;"
								size="10" maxlength="10" /></td>
						</tr>
						<tr>
							<td><strong>Valor Total do Movimento:</strong></td>
							<td colspan="3"><html:text property="valorTotalMovimento"
								readonly="true" style="background-color:#EFEFEF; border:0;"
								size="10" maxlength="10" /></td>
						</tr>
						<tr>
							<td><strong>Data do Processamento:</strong></td>
							<td colspan="3"><html:text property="dataProcessamento"
								readonly="true" style="background-color:#EFEFEF; border:0;"
								size="10" maxlength="10" /></td>
						</tr>
						<tr>
							<td><strong>Hora do Processamento:</strong></td>
							<td colspan="3"><html:text property="horaProcessamento"
								readonly="true" style="background-color:#EFEFEF; border:0;"
								size="8" maxlength="8" /></td>
						</tr>
					</table>
					</td>
				</tr>




				<tr>
					<td colspan="4">
					<table width="100%" align="center" bgcolor="#99CCFF" border="0"
						cellpadding="0" cellspacing="0" class="fontePequena">

						<tr bgcolor="#79bbfd" height="20">
							<td align="center"><strong>Pagamentos das Contas</strong></td>
						</tr>

						<tr bgcolor="#cbe5fe">
							<td width="100%" align="center">
							<table width="100%" bgcolor="#90c7fc" class="fontePequena">
								<tr bordercolor="#000000">


									<td width="10%" align="center" rowspan="2"><strong>Imóvel</strong>
									</td>
									<td width="10%" align="center" rowspan="2"><strong>Mês/Ano
									Conta</strong></td>
									<td width="13%" align="center" rowspan="2"><strong>Valor da
									Conta</strong></td>
									<td width="13%" align="center" rowspan="2"><strong>Valor do
									Pag.</strong></td>
									<td width="13%" align="center" rowspan="2"><strong>Data do Pag.</strong>
									</td>
									<td width="28%" align="center" colspan="2"><strong>Situação</strong>
									</td>
								</tr>
								<tr>
									<td width="14%" bgcolor="#cbe5fe" align="center"><strong>Anterior</strong>
									</td>
									<td width="14%" bgcolor="#cbe5fe" align="center"><strong>Atual</strong>
									</td>
								</tr>
								<%int cont = 1;%>

								<%if ((Integer) session.getAttribute("qtdePagContas") <= ConstantesSistema.NUMERO_MAXIMO_REGISTROS_CONSULTA_PAGAMENTO) {%>

								<logic:present name="colecaoPagamentosMovimentoArrecadadorConta"
									scope="request">

									<logic:iterate
										name="colecaoPagamentosMovimentoArrecadadorConta"
										id="pagamento" type="Pagamento">
										<%cont = cont + 1;
			if (cont % 2 == 0) {%>
										<tr bgcolor="#FFFFFF">
											<%} else {

			%>
										<tr bgcolor="#cbe5fe">
											<%}%>
											<td width="10%" align="center"><logic:notEmpty
												name="pagamento" property="imovel">
													${pagamento.imovel.id}&nbsp;
												</logic:notEmpty></td>
											<td width="12%" align="center">
												<logic:notEmpty name="pagamento" property="contaGeral">
													<logic:notEmpty name="pagamento" property="contaGeral.conta.id">
														<logic:notEmpty name="pagamento" property="contaGeral.conta.referencia">
															<a
																href="javascript:abrirPopup('exibirConsultarContaAction.do?tipoConsulta=conta&contaID=<%="" + pagamento.getContaGeral().getConta().getId() %>' , 600, 800);">${pagamento.contaGeral.conta.formatarAnoMesParaMesAno}</a>
														</logic:notEmpty>
													</logic:notEmpty>
												</logic:notEmpty> 
												
												<logic:empty name="pagamento" property="contaGeral">
													${pagamento.formatarAnoMesPagamentoParaMesAno}
												</logic:empty>
											</td>
											<td width="12%" align="right"><logic:notEmpty
												name="pagamento" property="contaGeral">
												<logic:notEmpty name="pagamento" property="contaGeral.conta.valorTotal">
													<bean:write name="pagamento" property="contaGeral.conta.valorTotal"
														formatKey="money.format" />&nbsp;
													</logic:notEmpty>
											</logic:notEmpty></td>
											<td width="12%" align="right"><bean:write name="pagamento"
												property="valorPagamento" formatKey="money.format" />&nbsp;
											</td>
											<td width="12%" align="center">
												<a 
													href="javascript:abrirPopup('exibirConsultarPagamentoPopupAction.do?idPagamento=${pagamento.id}' , 210, 510);">
												<bean:write name="pagamento" property="dataPagamento" formatKey="date.format" />
												</a>
											&nbsp;</td>
											<td width="14%">
											${pagamento.pagamentoSituacaoAnterior.descricaoAbreviada}&nbsp;
											</td>
											<td width="14%">
											${pagamento.pagamentoSituacaoAtual.descricaoAbreviada}&nbsp;
											</td>
										</tr>
									</logic:iterate>
								</logic:present>

								<!--  Historico -->

								<logic:present
									name="colecaoPagamentosHistoricoMovimentoArrecadadorConta"
									scope="request">

									<logic:iterate
										name="colecaoPagamentosMovimentoArrecadadorConta"
										id="pagamentoHistorico" type="PagamentoHistorico">
										<%cont = cont + 1;
			if (cont % 2 == 0) {%>
										<tr bgcolor="#FFFFFF">
											<%} else {

			%>
										<tr bgcolor="#cbe5fe">
											<%}%>
											<td width="10%" align="center"><logic:notEmpty
												name="pagamentoHistorico" property="imovel">
												<font color="#ff0000"> ${pagamentoHistorico.imovel.id} </font>
													&nbsp;
												</logic:notEmpty></td>
											<td width="12%" align="center"><%--<logic:notEmpty
														name="pagamentoHistorico" property="conta">
														<a
															href="javascript:abrirPopup('exibirConsultarContaAction.do?tipoConsulta=conta&contaID=<%="" + pagamentoHistorico.getConta().getId() %>' , 600, 800);"><font color="#ff0000" >${pagamentoHistorico.conta.formatarAnoMesParaMesAno}</font></a>&nbsp;
												</logic:notEmpty>--%> <logic:present
												name="pagamentoHistorico"
												property="anoMesReferenciaArrecadacao">
												<font color="#ff0000">${pagamentoHistorico.formatarAnoMesParaMesAnoArrecadacao}</font>
											</logic:present></td>
											<td width="12%" align="right"><logic:notEmpty
												name="pagamentoHistorico" property="contaGeral">
												<logic:notEmpty
												name="pagamentoHistorico" property="contaGeral.contaHistorico">
												<logic:notEmpty name="pagamentoHistorico"
													property="contaGeral.contaHistorico.valorTotal">
													<font color="#ff0000"> <bean:write
														name="pagamentoHistorico" property="contaGeral.contaHistorico.valorTotal"
														formatKey="money.format" /> </font>	
																&nbsp;
													</logic:notEmpty></logic:notEmpty>
											</logic:notEmpty></td>
											<td width="12%" align="right"><font color="#ff0000"> <bean:write
												name="pagamentoHistorico" property="valorPagamento"
												formatKey="money.format" /> </font> &nbsp;</td>

											<td width="12%" align="center"> 
												<a style="color: #FF0000;"
														href="javascript:abrirPopup('exibirConsultarPagamentoPopupAction.do?idPagamentoHistorico=${pagamentoHistorico.id}' , 210, 510);">
												<bean:write name="pagamentoHistorico" property="dataPagamento" formatKey="date.format" />
												</a>
											&nbsp;</td>
												
											<td width="14%"><font color="#ff0000">
											${pagamentoHistorico.pagamentoSituacaoAnterior.descricaoAbreviada}
											</font> &nbsp;</td>
											<td width="14%"><font color="#ff0000">
											${pagamentoHistorico.pagamentoSituacaoAtual.descricaoAbreviada}
											</font> &nbsp;</td>
										</tr>
									</logic:iterate>
								</logic:present>
								<%} else {%>
								<tr>
									<td height="190" colspan="7">
										<div style="width: 100%; height: 100%; overflow: auto;">
											<table width="100%">
										
										
																		<logic:present name="colecaoPagamentosMovimentoArrecadadorConta"
									scope="request">

									<logic:iterate
										name="colecaoPagamentosMovimentoArrecadadorConta"
										id="pagamento" type="Pagamento">
										<%cont = cont + 1;
			if (cont % 2 == 0) {%>
										<tr bgcolor="#FFFFFF">
											<%} else {

			%>
										<tr bgcolor="#cbe5fe">
											<%}%>
											<td width="10%" align="center"><logic:notEmpty
												name="pagamento" property="imovel">
													${pagamento.imovel.id}&nbsp;
												</logic:notEmpty></td>
											<td width="10%" align="center">
												<logic:notEmpty name="pagamento" property="contaGeral">
													<logic:notEmpty name="pagamento" property="contaGeral.conta.id">
														<logic:notEmpty name="pagamento" property="contaGeral.conta.referencia">
															<a
																href="javascript:abrirPopup('exibirConsultarContaAction.do?tipoConsulta=conta&contaID=<%="" + pagamento.getContaGeral().getConta().getId() %>' , 600, 800);">${pagamento.contaGeral.conta.formatarAnoMesParaMesAno}</a>
														</logic:notEmpty>
													</logic:notEmpty>
												</logic:notEmpty> 
												
												<logic:empty name="pagamento" property="contaGeral">
													${pagamento.formatarAnoMesPagamentoParaMesAno}
												</logic:empty>
											</td>
											<td width="15%" align="right"><logic:notEmpty
												name="pagamento" property="contaGeral">
												<logic:notEmpty name="pagamento" property="contaGeral.conta.valorTotal">
													<bean:write name="pagamento" property="contaGeral.conta.valorTotal"
														formatKey="money.format" />&nbsp;
													</logic:notEmpty>
											</logic:notEmpty></td>
											<td width="13%" align="right"><bean:write name="pagamento"
												property="valorPagamento" formatKey="money.format" />&nbsp;
											</td>
											
											<td width="13%" align="center">
												<a 
														href="javascript:abrirPopup('exibirConsultarPagamentoPopupAction.do?idPagamento=${pagamento.id}' , 210, 510);">
												<bean:write name="pagamento" property="dataPagamento" formatKey="date.format" />
												</a>
											&nbsp;</td>
												
											<td width="15.5%">
											${pagamento.pagamentoSituacaoAnterior.descricaoAbreviada}&nbsp;
											</td>
											<td width="11.5%">
											${pagamento.pagamentoSituacaoAtual.descricaoAbreviada}&nbsp;
											</td>
										</tr>
									</logic:iterate>
								</logic:present>

								<!--  Historico -->

								<logic:present
									name="colecaoPagamentosHistoricoMovimentoArrecadadorConta"
									scope="request">

									<logic:iterate
										name="colecaoPagamentosMovimentoArrecadadorConta"
										id="pagamentoHistorico" type="PagamentoHistorico">
										<%cont = cont + 1;
			if (cont % 2 == 0) {%>
										<tr bgcolor="#FFFFFF">
											<%} else {

			%>
										<tr bgcolor="#cbe5fe">
											<%}%>
											<td width="10%" align="center"><logic:notEmpty
												name="pagamentoHistorico" property="imovel">
												<font color="#ff0000"> ${pagamentoHistorico.imovel.id} </font>
													&nbsp;
												</logic:notEmpty></td>
											<td width="10%" align="center"><%--<logic:notEmpty
														name="pagamentoHistorico" property="conta">
														<a
															href="javascript:abrirPopup('exibirConsultarContaAction.do?tipoConsulta=conta&contaID=<%="" + pagamentoHistorico.getConta().getId() %>' , 600, 800);"><font color="#ff0000" >${pagamentoHistorico.conta.formatarAnoMesParaMesAno}</font></a>&nbsp;
												</logic:notEmpty>--%> <logic:present
												name="pagamentoHistorico"
												property="anoMesReferenciaArrecadacao">
												<font color="#ff0000">${pagamentoHistorico.formatarAnoMesParaMesAnoArrecadacao}</font>
											</logic:present></td>
											<td width="15%" align="right">
											<logic:notEmpty
												name="pagamentoHistorico" property="contaGeral">
												<logic:notEmpty
												name="pagamentoHistorico" property="contaGeral.contaHistorico">
												<logic:notEmpty name="pagamentoHistorico"
													property="contaGeral.contaHistorico.valorTotal">
													<font color="#ff0000"> 
													<bean:write
														name="pagamentoHistorico" property="contaGeral.contaHistorico..valorTotal"
														formatKey="money.format" /> </font>	
																&nbsp;
													</logic:notEmpty>
													</logic:notEmpty>
											</logic:notEmpty></td>
											<td width="13%" align="right"><font color="#ff0000"> <bean:write
												name="pagamentoHistorico" property="valorPagamento"
												formatKey="money.format" /> </font> &nbsp;</td>

											<td width="13%" align="center">
												<a style="color: #FF0000;"
														href="javascript:abrirPopup('exibirConsultarPagamentoPopupAction.do?idPagamentoHistorico=${pagamentoHistorico.id}' , 210, 510);">
												<bean:write name="pagamentoHistorico" property="dataPagamento" formatKey="date.format" />
												</a>
											&nbsp;</td>
												
											<td width="15.5%"><font color="#ff0000">
											${pagamentoHistorico.pagamentoSituacaoAnterior.descricaoAbreviada}
											</font> &nbsp;</td>
											<td width="11.5%"><font color="#ff0000">
											${pagamentoHistorico.pagamentoSituacaoAtual.descricaoAbreviada}
											</font> &nbsp;</td>
										</tr>
									</logic:iterate>
								</logic:present>
										
										
											</table>
										</div>
									</td>
								</tr>

								<%}%>
								

							</table>
							</td>
						</tr>
					</table>
					</td>
				</tr>
				
				<%-- alterado por pedro alexandre dia 27/02/2007 --%>
				<tr>
				  <td colspan="4">
				    <table width="80%" >
                      <tr>
                        <td> <div align="left"><strong>Total Atual</strong></div></td>
                        
                        <td width="9%"><div align="right"><strong>Qtd -</strong></div></td>
                        <td width="10%"><div align="right"><bean:write name="qtdePagContasAtual" format="###,###"/></div></td>

                        <td width="38%"><div align="right"><strong>Valor do Pagamento -</strong></div></td>
                        <td width="22%"><div align="right"><bean:write name="vlPagContasAtual" formatKey="money.format"/></div></td>
                      </tr>
                      <tr> 
                        <td><div align="left"><strong>Total Hist&oacute;rico</strong></div></td>
                        
                        <td><div align="right"><strong>Qtd -</strong></div></td>
                        <td><div align="right"><bean:write name="qtdePagContasHistorico" format="###,###"/></div></td>
                        
                        <td><div align="right"><strong>Valor do Pagamento -</strong></div></td>
                        <td><div align="right"><bean:write name="vlPagContasHistorico" formatKey="money.format"/></div></td>
                      </tr>
                    </table>
                  </td>
                </tr>
                <tr> 
                  <td><hr> </td>
              	</tr>
				<%-- fim alteração ***************************** --%>



				<tr>
					<td colspan="4">
					<table width="100%" align="center" bgcolor="#99CCFF" border="0"
						cellpadding="0" cellspacing="0" class="fontePequena">

						<tr bgcolor="#79bbfd" height="20">
							<td align="center"><strong>Pagamentos das Guias de Pagamento</strong>
							</td>
						</tr>

						<tr bgcolor="#cbe5fe">
							<td width="100%" align="center">
							<table width="100%" bgcolor="#90c7fc" class="fontePequena">
								<tr bordercolor="#000000">
									<td width="9%" align="center" rowspan="2"><strong>Imóvel</strong></td>
									<td width="9%" align="center" rowspan="2"><strong>Cliente</strong></td>
									<td width="15%" align="center" rowspan="2"><strong>Tipo do
									Débito</strong></td>
									<td width="10%" align="center" rowspan="2"><strong>Valor da
									Guia de Pagto.</strong></td>
									<td width="10%" align="center" rowspan="2"><strong>Valor do
									Pag.</strong></td>
									<td width="9%" align="center" rowspan="2"><strong>Data do Pag.</strong></td>
									<td width=30% " align="center" colspan="2"><strong>Situação</strong></td>
								</tr>
								<tr>
									<td width="15%" bgcolor="#cbe5fe" align="center"><strong>Anterior</strong>
									</td>
									<td width="15%" bgcolor="#cbe5fe" align="center"><strong>Atual</strong>
									</td>
								</tr>
								<%int contad = 1;%>
								<%if ((Integer) session.getAttribute("qtdePagGuiaPagamento") <= ConstantesSistema.NUMERO_MAXIMO_REGISTROS_CONSULTA_PAGAMENTO) {%>

								<logic:present
									name="colecaoPagamentosMovimentoArrecadadorGuiaPagamento"
									scope="request">

									<logic:iterate
										name="colecaoPagamentosMovimentoArrecadadorGuiaPagamento"
										id="pagamento" type="Pagamento">
										<%contad = contad + 1;
			if (contad % 2 == 0) {%>
										<tr bgcolor="#FFFFFF">
											<%} else {%>
										<tr bgcolor="#cbe5fe">
											<%}%>
											<td width="9%" align="center"><logic:notEmpty
												name="pagamento" property="imovel">
													${pagamento.imovel.id}&nbsp;
												</logic:notEmpty></td>
											<td width="9%" align="center"><logic:notEmpty
												name="pagamento" property="cliente">
													${pagamento.cliente.id}&nbsp;
												</logic:notEmpty></td>
											<td width="15%" align="center"><logic:notEmpty
												name="pagamento" property="guiaPagamento">
												<logic:notEmpty name="pagamento"
													property="guiaPagamento.debitoTipo">
													<a
														href="javascript:abrirPopup('exibirConsultarGuiaPagamentoAction.do?guiaPagamentoId=<%="" + pagamento.getGuiaPagamento().getId() %>')">
														${pagamento.guiaPagamento.debitoTipo.descricao}</a>&nbsp;
																	</logic:notEmpty>
											</logic:notEmpty><logic:empty name="pagamento"
												property="guiaPagamento">${pagamento.debitoTipo.descricao}</logic:empty>
											</td>
											<td width="10%" align="right">
											${pagamento.valorPagamento}&nbsp;</td>
											<td width="10%" align="right"><logic:notEmpty
												name="pagamento" property="guiaPagamento">
												<logic:notEmpty name="pagamento"
													property="guiaPagamento.valorDebito">
													<bean:write name="pagamento"
														property="guiaPagamento.valorDebito"
														formatKey="money.format" />&nbsp;
													</logic:notEmpty>
											</logic:notEmpty></td>

											<td width="9%" align="center">
												<a 
													href="javascript:abrirPopup('exibirConsultarPagamentoPopupAction.do?idPagamento=${pagamento.id}' , 210, 510);">
												<bean:write name="pagamento" property="dataPagamento" formatKey="date.format" />
												</a>
											&nbsp;</td>
												
											<td width="15%">
											${pagamento.pagamentoSituacaoAnterior.descricaoAbreviada}&nbsp;
											</td>
											<td width="15%">
											${pagamento.pagamentoSituacaoAtual.descricaoAbreviada}&nbsp;
											</td>
										</tr>
									</logic:iterate>
								</logic:present>

								<!-- Historico -->

								<logic:present
									name="colecaoPagamentosHistoricoMovimentoArrecadadorGuiaPagamento"
									scope="request">

									<logic:iterate
										name="colecaoPagamentosHistoricoMovimentoArrecadadorGuiaPagamento"
										id="pagamentoHistorico" type="PagamentoHistorico">
										<%contad = contad + 1;
			if (contad % 2 == 0) {%>
										<tr bgcolor="#FFFFFF">
											<%} else {%>
										<tr bgcolor="#cbe5fe">
											<%}%>
											<td width="9%" align="center"><logic:notEmpty
												name="pagamentoHistorico" property="imovel">
												<font color="#ff0000"> ${pagamentoHistorico.imovel.id} </font>
													&nbsp;
												</logic:notEmpty></td>
											<td width="9%" align="center"><logic:notEmpty
												name="pagamentoHistorico" property="cliente">
												<font color="#ff0000"> ${pagamentoHistorico.cliente.id} </font>
													&nbsp;
												</logic:notEmpty></td>
											<td width="15%" align="center"><logic:notEmpty
												name="pagamentoHistorico" property="guiaPagamento">
												<logic:notEmpty name="pagamentoHistorico"
													property="guiaPagamento.debitoTipo">
													<a
														href="javascript:abrirPopup('exibirConsultarGuiaPagamentoAction.do?guiaPagamentoId=<%="" + pagamentoHistorico.getGuiaPagamentoGeral().getId() %>')"><font
														color="#ff0000">${pagamentoHistorico.guiaPagamento.debitoTipo.descricao}</font></a>&nbsp;
																	</logic:notEmpty>
											</logic:notEmpty><logic:empty name="pagamentoHistorico"
												property="guiaPagamento">${pagamentoHistorico.debitoTipo.descricao}</logic:empty>
											</td>
											<td width="10%" align="right"><font color="#ff0000">
											${pagamentoHistorico.valorPagamento} </font> &nbsp;</td>
											<td width="10%" align="right"><logic:notEmpty
												name="pagamentoHistorico" property="guiaPagamento">
												<logic:notEmpty name="pagamentoHistorico"
													property="guiaPagamento.valorDebito">
													<font color="#ff0000"> <bean:write
														name="pagamentoHistorico"
														property="guiaPagamento.valorDebito"
														formatKey="money.format" /> </font>	
																&nbsp;
													</logic:notEmpty>
											</logic:notEmpty></td>
											
											<td width="9%" align="center">
												<a style="color: #FF0000;"
													href="javascript:abrirPopup('exibirConsultarPagamentoPopupAction.do?idPagamentoHistorico=${pagamentoHistorico.id}' , 210, 510);">
												<bean:write name="pagamentoHistorico" property="dataPagamento" formatKey="date.format" />  
												</a>
											&nbsp;</td>
												
											<td width="15%"><font color="#ff0000">
											${pagamentoHistorico.pagamentoSituacaoAnterior.descricaoAbreviada}
											</font> &nbsp;</td>
											<td width="15%"><font color="#ff0000">
											${pagamentoHistorico.pagamentoSituacaoAtual.descricaoAbreviada}
											</font> &nbsp;</td>
										</tr>
									</logic:iterate>
								</logic:present>


								<%} else {%>
									<tr>
										<td height="190" colspan="8">
										<div style="width: 100%; height: 100%; overflow: auto;">
										<table width="100%">



								<logic:present
									name="colecaoPagamentosMovimentoArrecadadorGuiaPagamento"
									scope="request">

									<logic:iterate
										name="colecaoPagamentosMovimentoArrecadadorGuiaPagamento"
										id="pagamento" type="Pagamento">
										<%contad = contad + 1;
			if (contad % 2 == 0) {%>
										<tr bgcolor="#FFFFFF">
											<%} else {%>
										<tr bgcolor="#cbe5fe">
											<%}%>
											<td width="9%" align="center"><logic:notEmpty
												name="pagamento" property="imovel">
													${pagamento.imovel.id}&nbsp;
												</logic:notEmpty></td>
											<td width="9%" align="center"><logic:notEmpty
												name="pagamento" property="cliente">
													${pagamento.cliente.id}&nbsp;
												</logic:notEmpty></td>
											<td width="15%" align="center"><logic:notEmpty
												name="pagamento" property="guiaPagamento">
												<logic:notEmpty name="pagamento"
													property="guiaPagamento.debitoTipo">
													<a
														href="javascript:abrirPopup('exibirConsultarGuiaPagamentoAction.do?guiaPagamentoId=<%="" + pagamento.getGuiaPagamento().getId() %>')">
														${pagamento.guiaPagamento.debitoTipo.descricao}</a>&nbsp;
																	</logic:notEmpty>
											</logic:notEmpty><logic:empty name="pagamento"
												property="guiaPagamento">${pagamento.debitoTipo.descricao}</logic:empty>
											</td>
											<td width="10%" align="right">
											${pagamento.valorPagamento}&nbsp;</td>
											<td width="10%" align="right"><logic:notEmpty
												name="pagamento" property="guiaPagamento">
												<logic:notEmpty name="pagamento"
													property="guiaPagamento.valorDebito">
													<bean:write name="pagamento"
														property="guiaPagamento.valorDebito"
														formatKey="money.format" />&nbsp;
													</logic:notEmpty>
											</logic:notEmpty></td>

											<td width="9%" align="center">
												<a 
														href="javascript:abrirPopup('exibirConsultarPagamentoPopupAction.do?idPagamento=${pagamento.id}' , 210, 510);">
												<bean:write name="pagamento"  property="dataPagamento" formatKey="date.format" />
												</a>
											&nbsp;</td>
												
											<td width="16.5%">
											${pagamento.pagamentoSituacaoAnterior.descricaoAbreviada}&nbsp;
											</td>
											<td width="13.5%">
											${pagamento.pagamentoSituacaoAtual.descricaoAbreviada}&nbsp;
											</td>
										</tr>
									</logic:iterate>
								</logic:present>

								<!-- Historico -->

								<logic:present
									name="colecaoPagamentosHistoricoMovimentoArrecadadorGuiaPagamento"
									scope="request">

									<logic:iterate
										name="colecaoPagamentosHistoricoMovimentoArrecadadorGuiaPagamento"
										id="pagamentoHistorico" type="PagamentoHistorico">
										<%contad = contad + 1;
			if (contad % 2 == 0) {%>
										<tr bgcolor="#FFFFFF">
											<%} else {%>
										<tr bgcolor="#cbe5fe">
											<%}%>
											<td width="9%" align="center"><logic:notEmpty
												name="pagamentoHistorico" property="imovel">
												<font color="#ff0000"> ${pagamentoHistorico.imovel.id} </font>
													&nbsp;
												</logic:notEmpty></td>
											<td width="9%" align="center"><logic:notEmpty
												name="pagamentoHistorico" property="cliente">
												<font color="#ff0000"> ${pagamentoHistorico.cliente.id} </font>
													&nbsp;
												</logic:notEmpty></td>
											<td width="15%" align="center"><logic:notEmpty
												name="pagamentoHistorico" property="guiaPagamento">
												<logic:notEmpty name="pagamentoHistorico"
													property="guiaPagamento.debitoTipo">
													<a
														href="javascript:abrirPopup('exibirConsultarGuiaPagamentoAction.do?guiaPagamentoId=<%="" + pagamentoHistorico.getGuiaPagamentoGeral().getId() %>')"><font
														color="#ff0000">${pagamentoHistorico.guiaPagamento.debitoTipo.descricao}</font></a>&nbsp;
																	</logic:notEmpty>
											</logic:notEmpty><logic:empty name="pagamentoHistorico"
												property="guiaPagamento">${pagamentoHistorico.debitoTipo.descricao}</logic:empty>
											</td>
											<td width="10%" align="right"><font color="#ff0000">
											${pagamentoHistorico.valorPagamento} </font> &nbsp;</td>
											<td width="10%" align="right"><logic:notEmpty
												name="pagamentoHistorico" property="guiaPagamento">
												<logic:notEmpty name="pagamentoHistorico"
													property="guiaPagamento.valorDebito">
													<font color="#ff0000"> <bean:write
														name="pagamentoHistorico"
														property="guiaPagamento.valorDebito"
														formatKey="money.format" /> </font>	
																&nbsp;
													</logic:notEmpty>
											</logic:notEmpty></td>

											<td width="9%" align="center"> 
												<a style="color: #FF0000;"
													href="javascript:abrirPopup('exibirConsultarPagamentoPopupAction.do?idPagamentoHistorico=${pagamentoHistorico.id}' , 210, 510);">
												<bean:write name="pagamentoHistorico" property="dataPagamento" formatKey="date.format" />  
												</a>
											&nbsp;</td>
												
											<td width="16.5%"><font color="#ff0000">
											${pagamentoHistorico.pagamentoSituacaoAnterior.descricaoAbreviada}
											</font> &nbsp;</td>
											<td width="13.5%"><font color="#ff0000">
											${pagamentoHistorico.pagamentoSituacaoAtual.descricaoAbreviada}
											</font> &nbsp;</td>
										</tr>
									</logic:iterate>
								</logic:present>




										</table>
										</div>
										</td>
									</tr>

								<%}%>										



							</table>
							</td>
						</tr>
					</table>
					</td>
				</tr>
				
				<%-- alterado por pedro alexandre dia 27/02/2007 --%>
				<tr>
				  <td colspan="4">
				    <table width="80%" >
                      <tr>
                        <td> <div align="left"><strong>Total Atual</strong></div></td>
                        
                        <td width="9%"><div align="right"><strong>Qtd -</strong></div></td>
                        <td width="10%"><div align="right"><bean:write name="qtdePagGuiaPagamentoAtual" format="###,###"/></div></td>

                        <td width="38%"><div align="right"><strong>Valor do Pagamento -</strong></div></td>
                        <td width="22%"><div align="right"><bean:write name="vlPagGuiaPagamentoAtual" formatKey="money.format"/></div></td>
                      </tr>
                      <tr> 
                        <td><div align="left"><strong>Total Hist&oacute;rico</strong></div></td>
                        
                        <td><div align="right"><strong>Qtd -</strong></div></td>
                        <td><div align="right"><bean:write name="qtdePagGuiaPagamentoHistorico" format="###,###"/></div></td>
                        
                        <td><div align="right"><strong>Valor do Pagamento -</strong></div></td>
                        <td><div align="right"><bean:write name="vlPagGuiaPagamentoHistorico" formatKey="money.format"/></div></td>
                      </tr>
                    </table>
                  </td>
                </tr>
                <tr> 
                  <td><hr> </td>
              	</tr>
				<%-- fim alteração ***************************** --%>



				<tr>
					<td colspan="4">
					<table width="100%" align="center" bgcolor="#99CCFF" border="0"
						cellpadding="0" cellspacing="0" class="fontePequena">

						<tr bgcolor="#79bbfd" height="20">
							<td align="center"><strong>Pagamentos dos Débitos a Cobrar</strong>
							</td>
						</tr>

						<tr bgcolor="#cbe5fe">
							<td width="100%" align="center">
							<table width="100%" bgcolor="#90c7fc" class="fontePequena">
								<tr bordercolor="#000000">

									<td width="9%" bgcolor="#90c7fc" align="center" rowspan="2"><strong>Imóvel</strong>
									</td>
									<td width="16%" align="center" rowspan="2"><strong>Tipo do
									Débito</strong></td>
									<td width="12%" align="center" rowspan="2"><strong>Valor a Ser
									Cobrado</strong></td>
									<td width="12%" align="center" rowspan="2"><strong>Valor do
									Pag.</strong></td>
									<td width="12%" align="center" rowspan="2"><strong>Data do Pag.</strong>
									</td>
									<td width="30%" align="center" colspan="2"><strong>Situação</strong>
									</td>
								</tr>
								<tr>
									<td width="15%" bgcolor="#cbe5fe" align="center"><strong>Anterior</strong></td>
									<td width="15%" bgcolor="#cbe5fe" align="center"><strong>Atual</strong></td>
								</tr>
								<%int contador = 1;%>
								<%if ((Integer) session.getAttribute("qtdePagDebitoACobrar") <= ConstantesSistema.NUMERO_MAXIMO_REGISTROS_CONSULTA_PAGAMENTO) {%>
								<%--Esquema de paginação--%>

								<logic:present
									name="colecaoPagamentosMovimentoArrecadadorDebitoACobrar"
									scope="request">

									<logic:iterate
										name="colecaoPagamentosMovimentoArrecadadorDebitoACobrar"
										id="pagamento" type="Pagamento">
										<%contador = contador + 1;
			if (contador % 2 == 0) {%>
										<tr bgcolor="#FFFFFF">
											<%} else {

			%>
										<tr bgcolor="#cbe5fe">
											<%}%>
											<td width="9%" align="center"><logic:notEmpty
												name="pagamento" property="imovel">
													${pagamento.imovel.id}&nbsp;
												</logic:notEmpty></td>

											<td width="16%" align="center"><logic:notEmpty
												name="pagamento" property="debitoACobrarGeral">
												<logic:notEmpty name="pagamento"
													property="debitoACobrarGeral.debitoACobrar.debitoTipo.descricao">
													<a
														href="javascript:abrirPopup('exibirConsultarDebitoACobrarAction.do?imovelID=<bean:write name="pagamento" property="debitoACobrarGeral.debitoACobrar.imovel.id" />&debitoID=<bean:write name="pagamento" property="debitoACobrarGeral.id" />', 560,660);">${pagamento.debitoACobrarGeral.debitoACobrar.debitoTipo.descricao}&nbsp;</a>
												</logic:notEmpty>
											</logic:notEmpty><logic:empty name="pagamento"
												property="debitoACobrarGeral">${pagamento.debitoTipo.descricao} </logic:empty></td>

											<td width="12%" align="right"><logic:notEmpty
												name="pagamento" property="debitoACobrarGeral">
												<bean:write name="pagamento"
													property="debitoACobrarGeral.debitoACobrar.valorTotalComBonus"
													formatKey="money.format" />&nbsp;
												</logic:notEmpty></td>
											<td width="12%" align="right"><bean:write name="pagamento"
												property="valorPagamento" formatKey="money.format" />&nbsp;
											</td>

											<td width="12%" align="center">
												<a 
														href="javascript:abrirPopup('exibirConsultarPagamentoPopupAction.do?idPagamento=${pagamento.id}' , 210, 510);">
												<bean:write name="pagamento" property="dataPagamento" formatKey="date.format" />
												</a>
											&nbsp;</td>
												
											<td width="15%">
											${pagamento.pagamentoSituacaoAnterior.descricaoAbreviada}&nbsp;
											</td>
											<td width="15%">
											${pagamento.pagamentoSituacaoAtual.descricaoAbreviada}&nbsp;
											</td>
										</tr>
									</logic:iterate>
								</logic:present>


								<!-- Historico -->

								<logic:present
									name="colecaoPagamentosHistoricoMovimentoArrecadadorDebitoACobrar"
									scope="request">
									<logic:iterate
										name="colecaoPagamentosHistoricoMovimentoArrecadadorDebitoACobrar"
										id="pagamentoHistorico" type="PagamentoHistorico">
										<%contador = contador + 1;
			if (contador % 2 == 0) {%>
										<tr bgcolor="#FFFFFF">
											<%} else {

			%>
										<tr bgcolor="#cbe5fe">
											<%}%>
											<td width="9%" align="center"><logic:notEmpty
												name="pagamentoHistorico" property="imovel">
												<font color="#ff0000"> ${pagamentoHistorico.imovel.id} </font>
													&nbsp;
												</logic:notEmpty></td>

											<td width="16%" align="center"><logic:notEmpty
												name="pagamentoHistorico" property="debitoACobrarGeral">
												<logic:notEmpty name="pagamentoHistorico"
													property="debitoACobrarGeral.debitoACobrar.debitoTipo.descricao">
													<a
														href="javascript:abrirPopup('exibirConsultarDebitoACobrarAction.do?imovelID=<bean:write name="pagamentoHistorico" property="debitoACobrarGeral.debitoACobrar.imovel.id" />&debitoID=<bean:write name="pagamentoHistorico" property="debitoACobrarGeral.id" />', 560,660);"><font
														color="#ff0000">${pagamento.debitoACobrarGeral.debitoACobrar.debitoTipo.descricao}</font>&nbsp;</a>
												</logic:notEmpty>
											</logic:notEmpty><logic:empty name="pagamentoHistorico"
												property="debitoACobrarGeral">${pagamentoHistorico.debitoTipo.descricao} </logic:empty></td>

											<td width="12%" align="right"><logic:notEmpty
												name="pagamentoHistorico" property="debitoACobrarGeral">

												<font color="#ff0000"> <bean:write
													name="pagamentoHistorico"
													property="debitoACobrarGeral.debitoACobrar.valorTotalComBonus"
													formatKey="money.format" /> </font>	
															&nbsp;
												</logic:notEmpty></td>
											<td width="12%" align="right"><font color="#ff0000"> <bean:write
												name="pagamentoHistorico" property="valorPagamento"
												formatKey="money.format" /> </font> &nbsp;</td>

											<td width="12%" align="center">
												<a style="color: #FF0000;"
														href="javascript:abrirPopup('exibirConsultarPagamentoPopupAction.do?idPagamentoHistorico=${pagamentoHistorico.id}' , 210, 510);">
												<bean:write name="pagamentoHistorico" property="dataPagamento" formatKey="date.format" />  
												</a>
											&nbsp;</td>
												
											<td width="15%"><font color="#ff0000">
											${pagamentoHistorico.pagamentoSituacaoAnterior.descricaoAbreviada}
											</font> &nbsp;</td>
											<td width="15%"><font color="#ff0000">
											${pagamentoHistorico.pagamentoSituacaoAtual.descricaoAbreviada}
											</font> &nbsp;</td>
										</tr>
									</logic:iterate>
								</logic:present>

								<%} else {%>
									<tr>
										<td height="190" colspan="7">
										<div style="width: 100%; height: 100%; overflow: auto;">
										<table width="100%">


	<logic:present
									name="colecaoPagamentosMovimentoArrecadadorDebitoACobrar"
									scope="request">

									<logic:iterate
										name="colecaoPagamentosMovimentoArrecadadorDebitoACobrar"
										id="pagamento" type="Pagamento">
										<%contador = contador + 1;
			if (contador % 2 == 0) {%>
										<tr bgcolor="#FFFFFF">
											<%} else {

			%>
										<tr bgcolor="#cbe5fe">
											<%}%>
											<td width="9%" align="center"><logic:notEmpty
												name="pagamento" property="imovel">
													${pagamento.imovel.id}&nbsp;
												</logic:notEmpty></td>

											<td width="16%" align="center"><logic:notEmpty
												name="pagamento" property="debitoACobrarGeral">
												<logic:notEmpty name="pagamento"
													property="debitoACobrarGeral.debitoACobrar.debitoTipo.descricao">
													<a
														href="javascript:abrirPopup('exibirConsultarDebitoACobrarAction.do?imovelID=<bean:write name="pagamento" property="debitoACobrarGeral.debitoACobrar.imovel.id" />&debitoID=<bean:write name="pagamento" property="debitoACobrarGeral.id" />', 560,660);">${pagamento.debitoACobrarGeral.debitoACobrar.debitoTipo.descricao}&nbsp;</a>
												</logic:notEmpty>
											</logic:notEmpty><logic:empty name="pagamento"
												property="debitoACobrarGeral">${pagamento.debitoTipo.descricao} </logic:empty></td>

											<td width="12%" align="right"><logic:notEmpty
												name="pagamento" property="debitoACobrarGeral">
												<bean:write name="pagamento"
													property="debitoACobrarGeral.debitoACobrar.valorTotalComBonus"
													formatKey="money.format" />&nbsp;
												</logic:notEmpty></td>
											<td width="12%" align="right"><bean:write name="pagamento"
												property="valorPagamento" formatKey="money.format" />&nbsp;
											</td>

											<td width="13.5%" align="center">
												<a 
														href="javascript:abrirPopup('exibirConsultarPagamentoPopupAction.do?idPagamento=${pagamento.id}' , 210, 510);">
												<bean:write name="pagamento" property="dataPagamento" formatKey="date.format" />
												</a>
											&nbsp;</td>
												
											<td width="16%">
											${pagamento.pagamentoSituacaoAnterior.descricaoAbreviada}&nbsp;
											</td>
											<td width="12.5%">
											${pagamento.pagamentoSituacaoAtual.descricaoAbreviada}&nbsp;
											</td>
										</tr>
									</logic:iterate>
								</logic:present>


								<!-- Historico -->

								<logic:present
									name="colecaoPagamentosHistoricoMovimentoArrecadadorDebitoACobrar"
									scope="request">
									<logic:iterate
										name="colecaoPagamentosHistoricoMovimentoArrecadadorDebitoACobrar"
										id="pagamentoHistorico" type="PagamentoHistorico">
										<%contador = contador + 1;
			if (contador % 2 == 0) {%>
										<tr bgcolor="#FFFFFF">
											<%} else {

			%>
										<tr bgcolor="#cbe5fe">
											<%}%>
											<td width="9%" align="center"><logic:notEmpty
												name="pagamentoHistorico" property="imovel">
												<font color="#ff0000"> ${pagamentoHistorico.imovel.id} </font>
													&nbsp;
												</logic:notEmpty></td>

											<td width="16%" align="center"><logic:notEmpty
												name="pagamentoHistorico" property="debitoACobrarGeral">
												<logic:notEmpty name="pagamentoHistorico"
													property="debitoACobrarGeral.debitoACobrar.debitoTipo.descricao">
													<a
														href="javascript:abrirPopup('exibirConsultarDebitoACobrarAction.do?imovelID=<bean:write name="pagamentoHistorico" property="debitoACobrarGeral.debitoACobrar.imovel.id" />&debitoID=<bean:write name="pagamentoHistorico" property="debitoACobrarGeral.id" />', 560,660);"><font
														color="#ff0000">${pagamento.debitoACobrarGeral.debitoACobrar.debitoTipo.descricao}</font>&nbsp;</a>
												</logic:notEmpty>
											</logic:notEmpty><logic:empty name="pagamentoHistorico"
												property="debitoACobrarGeral">${pagamentoHistorico.debitoTipo.descricao} </logic:empty></td>

											<td width="12%" align="right"><logic:notEmpty
												name="pagamentoHistorico" property="debitoACobrarGeral">

												<font color="#ff0000"> <bean:write
													name="pagamentoHistorico"
													property="debitoACobrarGeral.debitoACobrar.valorTotalComBonus"
													formatKey="money.format" /> </font>	
															&nbsp;
												</logic:notEmpty></td>
											<td width="12%" align="right"><font color="#ff0000"> <bean:write
												name="pagamentoHistorico" property="valorPagamento"
												formatKey="money.format" /> </font> &nbsp;</td>

											<td width="13.5%" align="center">
												<a style="color: #FF0000;"
														href="javascript:abrirPopup('exibirConsultarPagamentoPopupAction.do?idPagamentoHistorico=${pagamentoHistorico.id}' , 210, 510);">
												 <bean:write name="pagamentoHistorico" property="dataPagamento" formatKey="date.format" />  
												</a> 
											&nbsp;</td>
												
											<td width="16%"><font color="#ff0000">
											${pagamentoHistorico.pagamentoSituacaoAnterior.descricaoAbreviada}
											</font> &nbsp;</td>
											<td width="15.5%"><font color="#ff0000">
											${pagamentoHistorico.pagamentoSituacaoAtual.descricaoAbreviada}
											</font> &nbsp;</td>
										</tr>
									</logic:iterate>
								</logic:present>


										</table>
										</div>
										</td>
									</tr>

								<%}%>	


							</table>
							</td>
						</tr>
					</table>
					</td>
				</tr>
				
				<%-- alterado por pedro alexandre dia 27/02/2007 --%>
				<tr>
				  <td colspan="4">
				    <table width="80%" >
                      <tr>
                        <td> <div align="left"><strong>Total Atual</strong></div></td>
                        
                        <td width="9%"><div align="right"><strong>Qtd -</strong></div></td>
                        <td width="10%"><div align="right"><bean:write name="qtdePagDebitoACobrarAtual" format="###,###"/></div></td>

                        <td width="38%"><div align="right"><strong>Valor do Pagamento -</strong></div></td>
                        <td width="22%"><div align="right"><bean:write name="vlPagDebitoACobrarAtual" formatKey="money.format"/></div></td>
                      </tr>
                      <tr> 
                        <td><div align="left"><strong>Total Hist&oacute;rico</strong></div></td>
                        
                        <td><div align="right"><strong>Qtd -</strong></div></td>
                        <td><div align="right"><bean:write name="qtdePagDebitoACobrarHistorico" format="###,###"/></div></td>
                        
                        <td><div align="right"><strong>Valor do Pagamento -</strong></div></td>
                        <td><div align="right"><bean:write name="vlPagDebitoACobrarHistorico" formatKey="money.format"/></div></td>
                      </tr>
                    </table>
                  </td>
                </tr>
                <tr> 
                  <td><hr> </td>
              	</tr>
				<%-- fim alteração ***************************** --%>
				
				<tr>
					<td><input type="button" name="ButtonCancelar"
						class="bottonRightCol" value="Cancelar"
						onClick="javascript:window.location.href='/gsan/telaPrincipal.do'">
					<input type="button" name="ButtonReset" class="bottonRightCol"
						value="Voltar Filtro" onClick="javascript:history.back();"></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
	<%@ include file="/jsp/util/rodape.jsp"%>
</body>
</html:form>
</html:html>
