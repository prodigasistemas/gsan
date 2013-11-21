<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%@page import="gcom.arrecadacao.pagamento.Pagamento"%>
<%@page import="gcom.arrecadacao.pagamento.PagamentoHistorico"%>
<%@ page import="gcom.util.ConstantesSistema" isELIgnored="false"%>
<%@page import="gcom.cadastro.cliente.ClienteImovel"%>
<%@page import="gcom.util.Util" isELIgnored="false"%>

<html:html>

<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js"></script>

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
					<td class="parabg">Consultar Pagamentos do Imóvel</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			
			<p>&nbsp;</p>
			<table width="100%" border="0" style="fonteReduzida">
			  <tr>
				<td colspan="4">
				  <table width="100%" border="0">
					<tr>
					  <td width="35%"><strong>Matrícula do Imóvel:</strong></td>
					  <td><html:text property="idImovel" readonly="true" style="background-color:#EFEFEF; border:0;" size="10" maxlength="10" /></td>
					</tr>
					<tr>
					  <td width="35%"><strong>Inscrição:</strong></td>
					  <td><html:text property="inscricao" readonly="true" style="background-color:#EFEFEF; border:0;" size="30"	maxlength="30" /></td>
					</tr>
					<tr>
					  <td><strong>Situação da Ligação de Água:</strong></td>
							<td><html:text property="situacaoLigacaoAgua" readonly="true"
								style="background-color:#EFEFEF; border:0;" size="30"
								maxlength="30" /></td>
						</tr>
						
						<tr>
							<td><strong>Situação da Ligação de Esgoto:</strong></td>
							<td><html:text property="situacaoLigacaoEsgoto" readonly="true"
								style="background-color:#EFEFEF; border:0;" size="30"
								maxlength="30" /></td>
						</tr>

						<tr>
							<td colspan="4">
							<table width="100%" bgcolor="#99CCFF">
								<tr bgcolor="#90c7fc">
									<td align="center"><strong>Endere&ccedil;o</strong></td>
								</tr>
								<tr>
									<logic:present name="imovel" scope="session">
										<td bgcolor="#FFFFFF" align="center"><span id="endereco"> <bean:write
											name="imovel" property="enderecoFormatado" scope="session" />
										</span></td>
									</logic:present>
									<logic:notPresent name="imovel" scope="session">
										<td bgcolor="#FFFFFF">&nbsp;</td>
									</logic:notPresent>
								</tr>
							</table>
							</td>
						</tr>

					</table>
					</td>
				</tr>
				<tr>
					<td colspan="4" height="10"></td>
				</tr>


				<tr>
					<td colspan="4">

					<table width="100%" align="center" bgcolor="#90c7fc" border="0"
						cellpadding="0" cellspacing="0" class="fontePequena">

						<tr bgcolor="#79bbfd" height="20">
							<td align="center"><strong>Clientes</strong></td>
						</tr>
						<%int cont = 1;%>

						<tr bgcolor="#cbe5fe">
							<td width="100%" align="center">
							<table width="100%" bgcolor="#90c7fc" class="fontePequena">

								<tr bordercolor="#000000">
									<td width="40%" align="center" bgcolor="#90c7fc"><strong>Nome
									do Cliente</strong></td>
									<td width="20%" align="center" bgcolor="#90c7fc"><strong>Tipo
									da Relação</strong></td>
									<td width="20%" align="center" bgcolor="#90c7fc"><strong>CPF</strong></td>
									<td width="20%" align="center" bgcolor="#90c7fc"><strong>CNPJ</strong></td>
								</tr>

								<logic:present name="colecaoClienteImovel" scope="request">
									<logic:notEmpty name="colecaoClienteImovel" scope="request">
										<logic:iterate name="colecaoClienteImovel" id="clienteImovel"
											type="ClienteImovel">
											<%cont = cont + 1;
            if (cont % 2 == 0) {%>
											<tr bgcolor="#FFFFFF">
												<%} else {

            %>
											<tr bgcolor="#cbe5fe">
												<%}%>
												<td width="40%">${clienteImovel.cliente.nome}&nbsp;</td>

												<td width="20%"><logic:notEmpty name="clienteImovel"
													property="clienteRelacaoTipo">
														    		${clienteImovel.clienteRelacaoTipo.descricao}
															</logic:notEmpty> &nbsp;</td>
												<logic:present name="clienteImovel"
													property="cliente.cpfFormatado">
													<td width="20%" align="right">${clienteImovel.cliente.cpfFormatado}&nbsp;</td>
												</logic:present>
												<logic:notPresent name="clienteImovel"
													property="cliente.cpfFormatado">
													<td width="20%" align="right">&nbsp;</td>
												</logic:notPresent>

												<logic:present name="clienteImovel"
													property="cliente.cnpjFormatado">
													<td width="20%" align="right">${clienteImovel.cliente.cnpjFormatado}&nbsp;</td>
												</logic:present>
												<logic:notPresent name="clienteImovel"
													property="cliente.cnpjFormatado">
													<td width="20%" align="right">&nbsp;</td>
												</logic:notPresent>
											</tr>
										</logic:iterate>
									</logic:notEmpty>
								</logic:present>
							</table>
							</td>
						</tr>
					</table>
					</td>
				</tr>

				<tr>
					<td colspan="4" height="10"></td>
				</tr>

				<tr>
					<td colspan="4">
					<table width="100%" align="center" bgcolor="#99CCFF" border="0"
						cellpadding="0" cellspacing="0" class="fontePequena">

						<tr bgcolor="#79bbfd" height="20">
							<td align="center"><strong>Pagamentos das Contas</strong></td>
						</tr>
						<%int cont1 = 1;%>

						<tr bgcolor="#cbe5fe">
							<td width="100%" align="center">
							<table width="100%" bgcolor="#90c7fc" class="fontePequena">
								<tr bordercolor="#000000">

									<td bgcolor="#90c7fc" width="14%" align="center" rowspan="2"><strong>Mês/Ano
									Conta</strong></td>
									<td bgcolor="#90c7fc" width="19%" align="center" rowspan="2"><strong>Valor
									da Conta</strong></td>
									<td bgcolor="#90c7fc" width="19%" align="center" rowspan="2"><strong>Valor
									do Pag.</strong></td>
									<td bgcolor="#90c7fc" width="16%" align="center" rowspan="2"><strong>Data
									do Pag.</strong></td>
									<td bgcolor="#90c7fc" width="32%" align="center" colspan="2"><strong>Situação</strong>
									</td>
								</tr>
								<tr>
									<td width="16%" bgcolor="#cbe5fe" align="center"><strong>Anterior</strong></td>
									<td width="16%" bgcolor="#cbe5fe" align="center"><strong>Atual</strong></td>
								</tr>


								<%if ((Integer) session.getAttribute("qtdePagContas") <= ConstantesSistema.NUMERO_MAXIMO_REGISTROS_CONSULTA_PAGAMENTO) {%>

								<logic:present name="colecaoPagamentosImovelConta"
									scope="request">
									<logic:notEmpty name="colecaoPagamentosImovelConta"
										scope="request">
										<logic:iterate name="colecaoPagamentosImovelConta"
											id="pagamento" type="Pagamento">
											<%cont1 = cont1 + 1;
                if (cont1 % 2 == 0) {%>
											<tr bgcolor="#FFFFFF">
												<%} else {

                %>
											<tr bgcolor="#cbe5fe">
												<%}%>

												<td width="14%" align="center"><logic:notEmpty
													name="pagamento" property="contaGeral">
													<logic:notEmpty name="pagamento" property="contaGeral.conta">
														<logic:notEmpty name="pagamento" property="contaGeral.conta.id">
															<logic:notEmpty name="pagamento"
																property="contaGeral.conta.referencia">
																<a
																	href="javascript:abrirPopup('exibirConsultarContaAction.do?tipoConsulta=conta&contaID=<%="" + pagamento.getContaGeral().getId() %>' , 600, 800);">${pagamento.contaGeral.conta.formatarAnoMesParaMesAno}</a>
															</logic:notEmpty>
														</logic:notEmpty>
													</logic:notEmpty>
													
													<logic:notEmpty name="pagamento" property="contaGeral.contaHistorico">
														<logic:notEmpty name="pagamento" property="contaGeral.contaHistorico.id">
															<logic:notEmpty name="pagamento"
																property="contaGeral.contaHistorico.anoMesReferenciaConta">
																<a
																	href="javascript:abrirPopup('exibirConsultarContaAction.do?tipoConsulta=conta&contaID=<%="" + pagamento.getContaGeral().getId() %>' , 600, 800);">${pagamento.contaGeral.contaHistorico.formatarAnoMesParaMesAno}</a>
															</logic:notEmpty>
														</logic:notEmpty>
													</logic:notEmpty>
													
												</logic:notEmpty> 
												<logic:empty name="pagamento" property="contaGeral">
														${pagamento.formatarAnoMesPagamentoParaMesAno}
												</logic:empty></td>
												<td width="19%" align="right">
													<logic:notEmpty	name="pagamento" property="contaGeral">
														<logic:notEmpty name="pagamento" property="contaGeral.conta">
															<logic:notEmpty name="pagamento" property="contaGeral.conta.valorTotal">
																<bean:write name="pagamento" property="contaGeral.conta.valorTotal"
																	formatKey="money.format" />&nbsp;
															</logic:notEmpty>
														</logic:notEmpty>
														<logic:notEmpty name="pagamento" property="contaGeral.contaHistorico">
															<logic:notEmpty name="pagamento" property="contaGeral.contaHistorico.valorTotal">
																<bean:write name="pagamento" property="contaGeral.contaHistorico.valorTotal"
																	formatKey="money.format" />&nbsp;
															</logic:notEmpty>
														</logic:notEmpty>
													</logic:notEmpty>
												</td>
												<td width="19%" align="right"><bean:write name="pagamento"
													property="valorPagamento" formatKey="money.format" />&nbsp;
												</td>
												
												<td width="16%" align="center">
													<a
														href="javascript:abrirPopup('exibirConsultarPagamentoPopupAction.do?idPagamento=${pagamento.id}' , 210, 510);">
													<bean:write name="pagamento"
														property="dataPagamento" formatKey="date.format" /></a>&nbsp;</td>
												<td width="16%">
												
												${pagamento.pagamentoSituacaoAnterior.descricaoAbreviada}&nbsp;
												</td>
												<td width="16%">
												${pagamento.pagamentoSituacaoAtual.descricaoAbreviada}&nbsp;
												</td>
											</tr>
										</logic:iterate>
									</logic:notEmpty>
								</logic:present>



								<!-- Pagamento Historico -->

								<logic:present name="colecaoPagamentosHistoricoImovelConta"
									scope="request">
									<logic:notEmpty name="colecaoPagamentosHistoricoImovelConta"
										scope="request">
										<logic:iterate name="colecaoPagamentosHistoricoImovelConta"
											id="pagamentoHistorico" type="PagamentoHistorico">
											<%cont1 = cont1 + 1;
                if (cont1 % 2 == 0) {%>
											<tr bgcolor="#FFFFFF">
												<%} else {

                %>
											<tr bgcolor="#cbe5fe">
												<%}%>

												<td width="14%" align="center">
												 <logic:present
													name="pagamentoHistorico"
													property="anoMesReferenciaPagamento">
													<font color="#ff0000">${pagamentoHistorico.formatarAnoMesReferenciaPagamento}</font>
												</logic:present></td>
												<td width="19%" align="right"><logic:notEmpty
													name="pagamentoHistorico" property="contaGeral">
												<logic:notEmpty name="pagamentoHistorico"
														property="contaGeral.contaHistorico">
													<logic:notEmpty name="pagamentoHistorico"
														property="contaGeral.contaHistorico.valorTotal">
														<font color="#ff0000"> <bean:write
															name="pagamentoHistorico" property="contaGeral.contaHistorico.valorTotal"
															formatKey="money.format" /> </font>&nbsp;
													</logic:notEmpty>
												</logic:notEmpty>
												</logic:notEmpty>
												</td>
												<td width="19%" align="right"><font color="#ff0000"> <bean:write
													name="pagamentoHistorico" property="valorPagamento"
													formatKey="money.format" /> </font> &nbsp;</td>
												
												<td width="16%" align="center"><font color="#ff0000"> 
													<a style="color: #FF0000;"
													href="javascript:abrirPopup('exibirConsultarPagamentoPopupAction.do?idPagamentoHistorico=${pagamentoHistorico.id}' , 210, 510);">
													<span style="color: #FF0000;"><bean:write name="pagamentoHistorico" property="dataPagamento" formatKey="date.format" /></span> </a>
  											    </font> &nbsp;</td>
  											    
												<td width="16%"><font color="#ff0000">
													${pagamentoHistorico.pagamentoSituacaoAnterior.descricaoAbreviada}
												</font> &nbsp;</td>
												<td width="16%"><font color="#ff0000">
												${pagamentoHistorico.pagamentoSituacaoAtual.descricaoAbreviada}
												</font> &nbsp;</td>
											</tr>
										</logic:iterate>
									</logic:notEmpty>
								</logic:present>


								<%} else {%>
								<tr>
									<td height="190" colspan="6">
									<div style="width: 100%; height: 100%; overflow: auto;">
									<table width="100%">


										<logic:present name="colecaoPagamentosImovelConta"
											scope="request">
											<logic:notEmpty name="colecaoPagamentosImovelConta"
												scope="request">
												<logic:iterate name="colecaoPagamentosImovelConta"
													id="pagamento" type="Pagamento">
													<%cont1 = cont1 + 1;
                if (cont1 % 2 == 0) {%>
													<tr bgcolor="#FFFFFF">
														<%} else {

                %>
													<tr bgcolor="#cbe5fe">
														<%}%>

														<td width="14%" align="center">
														<logic:notEmpty	name="pagamento" property="contaGeral">
															<logic:notEmpty name="pagamento" property="contaGeral.conta">
																<logic:notEmpty name="pagamento" property="contaGeral.conta.id">
																	<logic:notEmpty name="pagamento" property="contaGeral.conta.referencia">
																		<a
																			href="javascript:abrirPopup('exibirConsultarContaAction.do?tipoConsulta=conta&contaID=<%="" + pagamento.getContaGeral().getConta().getId() %>' , 600, 800);">${pagamento.contaGeral.conta.formatarAnoMesParaMesAno}</a>
																	</logic:notEmpty>
																</logic:notEmpty>
															</logic:notEmpty>
															
															<logic:notEmpty name="pagamento" property="contaGeral.contaHistorico">
																<logic:notEmpty name="pagamento" property="contaGeral.contaHistorico.id">
																	<logic:notEmpty name="pagamento" property="contaGeral.contaHistorico.anoMesReferenciaConta">
																		<a
																			href="javascript:abrirPopup('exibirConsultarContaAction.do?tipoConsulta=conta&contaID=<%="" + pagamento.getContaGeral().getContaHistorico().getId() %>' , 600, 800);">${pagamento.contaGeral.contaHistorico.formatarAnoMesParaMesAno}</a>
																	</logic:notEmpty>
																</logic:notEmpty>
															</logic:notEmpty>
															
														</logic:notEmpty> 
														<logic:empty name="pagamento"
															property="contaGeral">
														${pagamento.formatarAnoMesPagamentoParaMesAno}
													</logic:empty></td>
														<td width="19%" align="right">
														<logic:notEmpty	name="pagamento" property="contaGeral">
															<logic:notEmpty name="pagamento" property="contaGeral.conta">
																<logic:notEmpty name="pagamento" property="contaGeral.conta.valorTotal">
																	<bean:write name="pagamento" property="contaGeral.conta.valorTotal"
																		formatKey="money.format" />&nbsp;
																</logic:notEmpty>
															</logic:notEmpty>
															
															<logic:notEmpty name="pagamento" property="contaGeral.contaHistorico">
																<logic:notEmpty name="pagamento" property="contaGeral.contaHistorico.valorTotal">
																	<bean:write name="pagamento" property="contaGeral.contaHistorico.valorTotal"
																		formatKey="money.format" />&nbsp;
																</logic:notEmpty>
															</logic:notEmpty>
															
														</logic:notEmpty></td>
														<td width="19%" align="right">
														<bean:write name="pagamento"
															property="valorPagamento" formatKey="money.format" /> &nbsp;
														</td>
														<td width="18%" align="center">
															<a
															href="javascript:abrirPopup('exibirConsultarPagamentoPopupAction.do?idPagamento=${pagamento.id}' , 210, 510);">										
																<bean:write
																	name="pagamento" property="dataPagamento"
																	formatKey="date.format" /></a>&nbsp;</td>
														<td width="16.5%">
															${pagamento.pagamentoSituacaoAnterior.descricaoAbreviada}&nbsp;
														</td>
														<td width="13.5%">
														${pagamento.pagamentoSituacaoAtual.descricaoAbreviada}&nbsp;
														</td>
													</tr>
												</logic:iterate>
											</logic:notEmpty>
										</logic:present>



										<!-- Pagamento Historico -->

										<logic:present name="colecaoPagamentosHistoricoImovelConta"
											scope="request">
											<logic:notEmpty name="colecaoPagamentosHistoricoImovelConta"
												scope="request">
												<logic:iterate name="colecaoPagamentosHistoricoImovelConta"
													id="pagamentoHistorico" type="PagamentoHistorico">
													<%cont1 = cont1 + 1;
                if (cont1 % 2 == 0) {%>
													<tr bgcolor="#FFFFFF">
														<%} else {

                %>
													<tr bgcolor="#cbe5fe">
														<%}%>

														<td width="14%" align="center"><logic:present
															name="pagamentoHistorico"
															property="anoMesReferenciaPagamento">
															<font color="#ff0000">${pagamentoHistorico.formatarAnoMesReferenciaPagamento}</font>
														</logic:present></td>
														<td width="19%" align="right">
														<logic:notEmpty	name="pagamentoHistorico" property="contaGeral">
															<logic:notEmpty
															name="pagamentoHistorico" property="contaGeral.contaHistorico">
															<logic:notEmpty name="pagamentoHistorico"
																property="contaGeral.contaHistorico.valorTotal">
																<font color="#ff0000"> <bean:write
																	name="pagamentoHistorico" property="contaGeral.contaHistorico.valorTotal"
																	formatKey="money.format" /> </font>&nbsp;
													</logic:notEmpty></logic:notEmpty>
														</logic:notEmpty></td>
														<td width="19%" align="right"><font color="#ff0000"> <bean:write
															name="pagamentoHistorico" property="valorPagamento"
															formatKey="money.format" /> </font> &nbsp;</td>
														
														<td width="18%" align="center"><font color="#ff0000"> 
															<a style="color: #FF0000;"
															href="javascript:abrirPopup('exibirConsultarPagamentoPopupAction.do?idPagamentoHistorico=${pagamentoHistorico.id}' , 210, 510);">											
															<span style="color: #FF0000;"><bean:write name="pagamentoHistorico" property="dataPagamento" formatKey="date.format" /> </span></a>
														</font>&nbsp;</td>
														
														<td width="16.5%"><font color="#ff0000">
														${pagamentoHistorico.pagamentoSituacaoAnterior.descricaoAbreviada}
														</font> &nbsp;</td>
														<td width="13.5%"><font color="#ff0000">
														${pagamentoHistorico.pagamentoSituacaoAtual.descricaoAbreviada}
														</font> &nbsp;</td>
													</tr>
												</logic:iterate>
												
				
												
											</logic:notEmpty>
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
						<%int contad = 1;%>

						<tr bgcolor="#cbe5fe">
							<td width="100%" align="center">
							<table width="100%" bgcolor="#90c7fc" class="fontePequena">
								<tr bordercolor="#000000">

									<td width="12%" align="center" rowspan="2"><strong>Cliente</strong>
									</td>
									<td width="15%" align="center" rowspan="2"><strong>Tipo do
									Débito</strong></td>
									<td width="15%" align="center" rowspan="2"><strong>Valor da
									Guia de Pagto.</strong></td>
									<td width="15%" align="center" rowspan="2"><strong>Valor do
									Pag.</strong></td>
									<td width="11%" align="center" rowspan="2"><strong>Data do Pag.</strong>
									</td>
									<td width=32% " align="center" colspan="2"><strong>Situação</strong>
									</td>
								</tr>
								<tr>
									<td width="16%" bgcolor="#cbe5fe" align="center"><strong>Anterior</strong></td>
									<td width="16%" bgcolor="#cbe5fe" align="center"><strong>Atual</strong></td>
								</tr>


								<%if ((Integer) session.getAttribute("qtdePagGuiaPagamento") <= ConstantesSistema.NUMERO_MAXIMO_REGISTROS_CONSULTA_PAGAMENTO) {%>

								<logic:present name="colecaoPagamentosImovelGuiaPagamento"
									scope="request">
									<logic:notEmpty name="colecaoPagamentosImovelGuiaPagamento"
										scope="request">
										<logic:iterate name="colecaoPagamentosImovelGuiaPagamento"
											id="pagamento" type="Pagamento">
											<%contad = contad + 1;
                if (contad % 2 == 0) {%>
											<tr bgcolor="#FFFFFF">
												<%} else {

                %>
											<tr bgcolor="#cbe5fe">
												<%}%>

												<td width="12%" align="center">
												${pagamento.cliente.id}&nbsp;</td>
												<td width="15%" align="center"><logic:notEmpty
													name="pagamento" property="guiaPagamento">
													<a
														href="javascript:abrirPopup('exibirConsultarGuiaPagamentoAction.do?guiaPagamentoId=<%="" + pagamento.getGuiaPagamento().getId() %>')">${pagamento.guiaPagamento.debitoTipo.descricao}</a>&nbsp;
														</logic:notEmpty><logic:empty name="pagamento"
													property="guiaPagamento">${pagamento.debitoTipo.descricao} </logic:empty></td>
												<td width="15%" align="right"><logic:notEmpty
													name="pagamento" property="guiaPagamento">
													<logic:notEmpty name="pagamento"
														property="guiaPagamento.valorDebito">
														<bean:write name="pagamento"
															property="guiaPagamento.valorDebito"
															formatKey="money.format" />&nbsp;
													</logic:notEmpty>
												</logic:notEmpty></td>
												<td width="15%" align="right"><bean:write name="pagamento"
													property="valorPagamento" formatKey="money.format" />&nbsp;
												</td>
												<td width="11%" align="center">
												
												<a
													href="javascript:abrirPopup('exibirConsultarPagamentoPopupAction.do?idPagamento=${pagamento.id}' , 210, 510);">
													<bean:write name="pagamento"
														property="dataPagamento" formatKey="date.format" /> </a> &nbsp;</td>
												<td width="16%">
												${pagamento.pagamentoSituacaoAnterior.descricaoAbreviada}&nbsp;
												</td>
												<td width="16%">
												${pagamento.pagamentoSituacaoAtual.descricaoAbreviada}&nbsp;
												</td>
											</tr>
										</logic:iterate>
									</logic:notEmpty>
								</logic:present>


								<!--  Historico -->

								<logic:present
									name="colecaoPagamentosHistoricoImovelGuiaPagamento"
									scope="request">
									<logic:notEmpty
										name="colecaoPagamentosHistoricoImovelGuiaPagamento"
										scope="request">
										<logic:iterate
											name="colecaoPagamentosHistoricoImovelGuiaPagamento"
											id="pagamentoHistorico" type="PagamentoHistorico">
											<%contad = contad + 1;
                if (contad % 2 == 0) {%>
											<tr bgcolor="#FFFFFF">
												<%} else {

                %>
											<tr bgcolor="#cbe5fe">
												<%}%>

												<td width="12%" align="center">
												${pagamentoHistorico.cliente.id}&nbsp;</td>
												<td width="15%" align="center"><logic:notEmpty
													name="pagamentoHistorico" property="guiaPagamento">
													<a
														href="javascript:abrirPopup('exibirConsultarGuiaPagamentoAction.do?guiaPagamentoId=<%="" + pagamentoHistorico.getGuiaPagamento().getId() %>')"><font
														color="#ff0000">${pagamentoHistorico.guiaPagamento.debitoTipo.descricao}</font></a>&nbsp;
														</logic:notEmpty><logic:empty name="pagamentoHistorico"
													property="guiaPagamento">${pagamentoHistorico.debitoTipo.descricao} </logic:empty></td>
												<td width="15%" align="right"><logic:notEmpty
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
												<td width="15%" align="right"><font color="#ff0000"> <bean:write
													name="pagamentoHistorico" property="valorPagamento"
													formatKey="money.format" /> </font> &nbsp;</td>
												
												<td width="11%" align="center"><font color="#ff0000"> 
												
													<a style="color: #FF0000;"
													href="javascript:abrirPopup('exibirConsultarPagamentoPopupAction.do?idPagamentoHistorico=${pagamentoHistorico.id}' , 210, 510);">
													<bean:write name="pagamentoHistorico" property="dataPagamento" formatKey="date.format" /></a> 
												</font> &nbsp; </td>
													
												<td width="16%"><font color="#ff0000">
												${pagamentoHistorico.pagamentoSituacaoAnterior.descricaoAbreviada}
												</font> &nbsp;</td>
												<td width="16%"><font color="#ff0000">
												${pagamentoHistorico.pagamentoSituacaoAtual.descricaoAbreviada}
												</font> &nbsp;</td>
											</tr>
										</logic:iterate>
									</logic:notEmpty>
								</logic:present>



								<%} else {

                %>

								<tr>
									<td height="190" colspan="7">
									<div style="width: 100%; height: 100%; overflow: auto;">
									<table width="100%">

										<logic:present name="colecaoPagamentosImovelGuiaPagamento"
											scope="request">
											<logic:notEmpty name="colecaoPagamentosImovelGuiaPagamento"
												scope="request">
												<logic:iterate name="colecaoPagamentosImovelGuiaPagamento"
													id="pagamento" type="Pagamento">
													<%contad = contad + 1;
                if (contad % 2 == 0) {%>
													<tr bgcolor="#FFFFFF">
														<%} else {

                %>
													<tr bgcolor="#cbe5fe">
														<%}%>

														<td width="12%" align="center">
														${pagamento.cliente.id}&nbsp;</td>
														<td width="15%" align="center"><logic:notEmpty
															name="pagamento" property="guiaPagamento">
															<a
																href="javascript:abrirPopup('exibirConsultarGuiaPagamentoAction.do?guiaPagamentoId=<%="" + pagamento.getGuiaPagamento().getId() %>')">${pagamento.guiaPagamento.debitoTipo.descricao}</a>&nbsp;
														</logic:notEmpty><logic:empty name="pagamento"
															property="guiaPagamento">${pagamento.debitoTipo.descricao} </logic:empty></td>
														<td width="15%" align="right"><logic:notEmpty
															name="pagamento" property="guiaPagamento">
															<logic:notEmpty name="pagamento"
																property="guiaPagamento.valorDebito">
																<bean:write name="pagamento"
																	property="guiaPagamento.valorDebito"
																	formatKey="money.format" />&nbsp;
													</logic:notEmpty>
														</logic:notEmpty></td>
														<td width="15%" align="right"><bean:write name="pagamento"
															property="valorPagamento" formatKey="money.format" />&nbsp;
														</td>
														<td width="13%" align="center">
												<a
													href="javascript:abrirPopup('exibirConsultarPagamentoPopupAction.do?idPagamento=${pagamento.id}' , 210, 510);">														
														<bean:write
															name="pagamento" property="dataPagamento"
															formatKey="date.format" /> </a> &nbsp;
															
															</td>
														<td width="16.5%">
														${pagamento.pagamentoSituacaoAnterior.descricaoAbreviada}&nbsp;
														</td>
														<td width="13.5%">
														${pagamento.pagamentoSituacaoAtual.descricaoAbreviada}&nbsp;
														</td>
													</tr>
												</logic:iterate>
											</logic:notEmpty>
										</logic:present>


										<!--  Historico -->

										<logic:present
											name="colecaoPagamentosHistoricoImovelGuiaPagamento"
											scope="request">
											<logic:notEmpty
												name="colecaoPagamentosHistoricoImovelGuiaPagamento"
												scope="request">
												<logic:iterate
													name="colecaoPagamentosHistoricoImovelGuiaPagamento"
													id="pagamentoHistorico" type="PagamentoHistorico">
													<%contad = contad + 1;
                if (contad % 2 == 0) {%>
													<tr bgcolor="#FFFFFF">
														<%} else {

                %>
													<tr bgcolor="#cbe5fe">
														<%}%>

														<td width="12%" align="center">
														${pagamentoHistorico.cliente.id}&nbsp;</td>
														<td width="15%" align="center"><logic:notEmpty
															name="pagamentoHistorico" property="guiaPagamento">
															<a
																href="javascript:abrirPopup('exibirConsultarGuiaPagamentoAction.do?guiaPagamentoId=<%="" + pagamentoHistorico.getGuiaPagamento().getId() %>')"><font
																color="#ff0000">${pagamentoHistorico.guiaPagamento.debitoTipo.descricao}</font></a>&nbsp;
														</logic:notEmpty><logic:empty name="pagamentoHistorico"
															property="guiaPagamento">${pagamentoHistorico.debitoTipo.descricao} </logic:empty></td>
														<td width="15%" align="right"><logic:notEmpty
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
														<td width="15%" align="right"><font color="#ff0000"> <bean:write
															name="pagamentoHistorico" property="valorPagamento"
															formatKey="money.format" /> </font> &nbsp;</td>
														
														<td width="13%" align="center"><font color="#ff0000"> 
															<a style="color: #FF0000;"
																href="javascript:abrirPopup('exibirConsultarPagamentoPopupAction.do?idPagamentoHistorico=${pagamentoHistorico.id}' , 210, 510);">														
																<bean:write name="pagamentoHistorico" property="dataPagamento" formatKey="date.format" /> 
															</a>	
														</font> &nbsp;</td>
														
														<td width="16.5%"><font color="#ff0000">
														${pagamentoHistorico.pagamentoSituacaoAnterior.descricaoAbreviada}
														</font> &nbsp;</td>
														<td width="13.5%"><font color="#ff0000">
														${pagamentoHistorico.pagamentoSituacaoAtual.descricaoAbreviada}
														</font> &nbsp;</td>
													</tr>
												</logic:iterate>
											</logic:notEmpty>
										</logic:present>

									</table>
									</div>
									</td>
								</tr>


								<%}

            %>

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
						<%int contador = 1;%>
						<tr bgcolor="#cbe5fe">
							<td width="100%" align="center">
							<table width="100%" bgcolor="#90c7fc" class="fontePequena">
								<tr bordercolor="#000000">
									<td width="19%" align="center" rowspan="2"><strong>Tipo do
									Débito</strong></td>
									<td width="17%" align="center" rowspan="2"><strong>Valor a Ser
									Cobrado</strong></td>
									<td width="17%" align="center" rowspan="2"><strong>Valor do
									Pag.</strong></td>
									<td width="15%" align="center" rowspan="2"><strong>Data do Pag.</strong>
									</td>
									<td width="32%" align="center" colspan="2"><strong>Situação</strong>
									</td>
								</tr>
								<tr>
									<td width="16%" bgcolor="#cbe5fe" align="center"><strong>Anterior</strong>
									</td>
									<td width="16%" bgcolor="#cbe5fe" align="center"><strong>Atual</strong>
									</td>
								</tr>

								<%--Esquema de paginação--%>


								<%if ((Integer) session.getAttribute("qtdePagDebitoACobrar") <= ConstantesSistema.NUMERO_MAXIMO_REGISTROS_CONSULTA_PAGAMENTO) {%>

								<logic:present name="colecaoPagamentosImovelDebitoACobrar"
									scope="request">
									<logic:iterate name="colecaoPagamentosImovelDebitoACobrar"
										id="pagamento" type="Pagamento">
										<%contador = contador + 1;
                if (contador % 2 == 0) {%>
										<tr bgcolor="#FFFFFF">
											<%} else {%>
										<tr bgcolor="#cbe5fe">
											<%}%>

											<td width="19%" align="center">
											<logic:notEmpty
												name="pagamento" property="debitoACobrarGeral">
												
													<logic:notEmpty
													name="pagamento" property="debitoACobrarGeral.debitoACobrar">
													<a
														href="javascript:abrirPopup('exibirConsultarDebitoACobrarAction.do?imovelID=<bean:write name="pagamento" property="debitoACobrarGeral.debitoACobrar.imovel.id" />&debitoID=<bean:write name="pagamento" property="debitoACobrarGeral.id" />', 560, 660);">
													${pagamento.debitoACobrarGeral.debitoACobrar.debitoTipo.descricao}&nbsp;</a>
													</logic:notEmpty>
													<logic:notEmpty
														name="pagamento" property="debitoACobrarGeral.debitoACobrarHistorico">
														<a
															href="javascript:abrirPopup('exibirConsultarDebitoACobrarAction.do?imovelID=<bean:write name="pagamento" property="debitoACobrarGeral.debitoACobrarHistorico.imovel.id" />&debitoID=<bean:write name="pagamento" property="debitoACobrarGeral.id" />', 560, 660);">
														${pagamento.debitoACobrarGeral.debitoACobrarHistorico.debitoTipo.descricao}&nbsp;</a>
													</logic:notEmpty>
											</logic:notEmpty>
											
											<logic:empty name="pagamento"
												property="debitoACobrarGeral">${pagamento.debitoTipo.descricao} 
											</logic:empty></td>
											
											<td width="17%" align="right">
											<logic:notEmpty
												name="pagamento" property="debitoACobrarGeral">
												<logic:notEmpty
												name="pagamento" property="debitoACobrarGeral.debitoACobrar">
													<logic:notEmpty name="pagamento"
														property="debitoACobrarGeral.debitoACobrar.valorTotalComBonus">
														<bean:write name="pagamento"
															property="debitoACobrarGeral.debitoACobrar.valorTotalComBonus"
															formatKey="money.format" />&nbsp;
													</logic:notEmpty>
												</logic:notEmpty>
												<logic:notEmpty
												name="pagamento" property="debitoACobrarGeral.debitoACobrarHistorico">
													<logic:notEmpty name="pagamento"
														property="debitoACobrarGeral.debitoACobrarHistorico.valorTotalComBonus">
														<bean:write name="pagamento"
															property="debitoACobrarGeral.debitoACobrarHistorico.valorTotalComBonus"
															formatKey="money.format" />&nbsp;
													</logic:notEmpty>
												</logic:notEmpty>
											</logic:notEmpty></td>
											<td width="17%" align="right"><bean:write name="pagamento"
												property="valorPagamento" formatKey="money.format" />&nbsp;
											</td>
											<td width="15%" align="center">
												<a
													href="javascript:abrirPopup('exibirConsultarPagamentoPopupAction.do?idPagamento=${pagamento.id}' , 210, 510);">											
											<bean:write name="pagamento"
												property="dataPagamento" formatKey="date.format" />
												</a>
												
												&nbsp;</td>
											<td width="16%">
											${pagamento.pagamentoSituacaoAnterior.descricaoAbreviada}&nbsp;
											</td>
											<td width="16%">
											${pagamento.pagamentoSituacaoAtual.descricaoAbreviada}&nbsp;
											</td>
										</tr>
									</logic:iterate>
								</logic:present>


								<!-- Historico -->

								<logic:present
									name="colecaoPagamentosHistoricoImovelDebitoACobrar"
									scope="request">
									<logic:iterate
										name="colecaoPagamentosHistoricoImovelDebitoACobrar"
										id="pagamentoHistorico" type="PagamentoHistorico">

										<%contador = contador + 1;
                if (contador % 2 == 0) {%>
										<tr bgcolor="#FFFFFF">
											<%} else {%>
										<tr bgcolor="#cbe5fe">
											<%}%>

											<td width="19%" align="center">
											<logic:notEmpty
												name="pagamentoHistorico" property="debitoACobrarGeral">
													<logic:notEmpty
													name="pagamentoHistorico" property="debitoACobrarGeral.debitoACobrarHistorico">
													<a
														href="javascript:abrirPopup('exibirConsultarDebitoACobrarAction.do?imovelID=<bean:write name="pagamentoHistorico" property="debitoACobrarGeral.debitoACobrarHistorico.imovel.id" />&debitoID=<bean:write name="pagamentoHistorico" property="debitoACobrarGeral.id" />', 560, 660);">
													<font color="#ff0000">
													${pagamentoHistorico.debitoACobrarGeral.debitoACobrarHistorico.debitoTipo.descricao} </font>
													&nbsp;</a>
													</logic:notEmpty>
											</logic:notEmpty>
											
											<logic:empty name="pagamentoHistorico"
												property="debitoACobrarGeral">${pagamentoHistorico.debitoTipo.descricao} 
											</logic:empty></td>
											
											<td width="17%" align="right">
											<logic:notEmpty
												name="pagamentoHistorico" property="debitoACobrarGeral">
												<logic:notEmpty
												name="pagamentoHistorico" property="debitoACobrarGeral.debitoACobrarHistorico">
													<logic:notEmpty name="pagamentoHistorico"
														property="debitoACobrarGeral.debitoACobrarHistorico.valorTotalComBonus">
														<font color="#ff0000"> <bean:write
															name="pagamentoHistorico"
															property="debitoACobrarGeral.debitoACobrarHistorico.valorTotalComBonus"
															formatKey="money.format" /> </font>		
																&nbsp;
													</logic:notEmpty>
												</logic:notEmpty>
											</logic:notEmpty></td>
											<td width="17%" align="right"><font color="#ff0000"> <bean:write
												name="pagamentoHistorico" property="valorPagamento"
												formatKey="money.format" /> </font> &nbsp;</td>
										
											<td width="15%" align="center"><font color="#ff0000"> 
											<a style="color: #FF0000;"
													href="javascript:abrirPopup('exibirConsultarPagamentoPopupAction.do?idPagamentoHistorico=${pagamentoHistorico.id}' , 210, 510);">
											<bean:write name="pagamentoHistorico" property="dataPagamento" formatKey="date.format" /> 
												</a>
											</font> &nbsp;</td>
												
											<td width="16%"><font color="#ff0000">
											${pagamentoHistorico.pagamentoSituacaoAnterior.descricaoAbreviada}
											</font> &nbsp;</td>
											<td width="16%"><font color="#ff0000">
											${pagamentoHistorico.pagamentoSituacaoAtual.descricaoAbreviada}
											</font> &nbsp;</td>
										</tr>
									</logic:iterate>
								</logic:present>

								<%} else {%>
								<tr>
									<td height="190" colspan="6">
									<div style="width: 100%; height: 100%; overflow: auto;">
									<table width="100%">

										<logic:present name="colecaoPagamentosImovelDebitoACobrar"
											scope="request">
											<logic:iterate name="colecaoPagamentosImovelDebitoACobrar"
												id="pagamento" type="Pagamento">
												<%contador = contador + 1;
                if (contador % 2 == 0) {%>
												<tr bgcolor="#FFFFFF">
													<%} else {%>
												<tr bgcolor="#cbe5fe">
													<%}%>

													<td width="19%" align="center">
													<logic:notEmpty
														name="pagamento" property="debitoACobrarGeral">
														
														<logic:notEmpty
														name="pagamento" property="debitoACobrarGeral.debitoACobrar">
														
														<a
															href="javascript:abrirPopup('exibirConsultarDebitoACobrarAction.do?imovelID=<bean:write name="pagamento" property="debitoACobrarGeral.debitoACobrar.imovel.id" />&debitoID=<bean:write name="pagamento" property="debitoACobrarGeral.id" />', 560, 660);">
														${pagamento.debitoACobrarGeral.debitoACobrar.debitoTipo.descricao}&nbsp;</a>
														</logic:notEmpty>
														<logic:notEmpty
														name="pagamento" property="debitoACobrarGeral.debitoACobrarHistorico">
														
														<a
															href="javascript:abrirPopup('exibirConsultarDebitoACobrarAction.do?imovelID=<bean:write name="pagamento" property="debitoACobrarGeral.debitoACobrarHistorico.imovel.id" />&debitoID=<bean:write name="pagamento" property="debitoACobrarGeral.id" />', 560, 660);">
														${pagamento.debitoACobrarGeral.debitoACobrarHistorico.debitoTipo.descricao}&nbsp;</a>
														</logic:notEmpty>
													</logic:notEmpty>
													
													
													<logic:empty name="pagamento"
														property="debitoACobrarGeral">${pagamento.debitoTipo.descricao} 
													</logic:empty></td>
													<td width="17%" align="right">
													<logic:notEmpty
														name="pagamento" property="debitoACobrarGeral">
														<logic:notEmpty
														name="pagamento" property="debitoACobrarGeral.debitoACobrar">
														
															<logic:notEmpty name="pagamento"
																property="debitoACobrarGeral.debitoACobrar.valorTotalComBonus">
																<bean:write name="pagamento"
																	property="debitoACobrarGeral.debitoACobrar.valorTotalComBonus"
																	formatKey="money.format" />&nbsp;
															</logic:notEmpty>
														</logic:notEmpty>
														<logic:notEmpty
														name="pagamento" property="debitoACobrarGeral.debitoACobrarHistorico">
														
															<logic:notEmpty name="pagamento"
																property="debitoACobrarGeral.debitoACobrarHistorico.valorTotalComBonus">
																<bean:write name="pagamento"
																	property="debitoACobrarGeral.debitoACobrarHistorico.valorTotalComBonus"
																	formatKey="money.format" />&nbsp;
															</logic:notEmpty>
														</logic:notEmpty>
														
													</logic:notEmpty></td>
													<td width="17%" align="right"><bean:write name="pagamento"
														property="valorPagamento" formatKey="money.format" />&nbsp;
													</td>
													<td width="17%" align="center">
												<a
													href="javascript:abrirPopup('exibirConsultarPagamentoPopupAction.do?idPagamento=${pagamento.id}' , 210, 510);">													
													<bean:write name="pagamento"
														property="dataPagamento" formatKey="date.format" />
													</a>														
														&nbsp;
													</td>
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
											name="colecaoPagamentosHistoricoImovelDebitoACobrar"
											scope="request">
											<logic:iterate
												name="colecaoPagamentosHistoricoImovelDebitoACobrar"
												id="pagamentoHistorico" type="PagamentoHistorico">

												<%contador = contador + 1;
                if (contador % 2 == 0) {%>
												<tr bgcolor="#FFFFFF">
													<%} else {%>
												<tr bgcolor="#cbe5fe">
													<%}%>

													<td width="19%" align="center">
													<logic:notEmpty
														name="pagamentoHistorico" property="debitoACobrarGeral">
														<logic:notEmpty
														name="pagamentoHistorico" property="debitoACobrarGeral.debitoACobrarHistorico">
														<a
															href="javascript:abrirPopup('exibirConsultarDebitoACobrarAction.do?imovelID=<bean:write name="pagamentoHistorico" property="debitoACobrarGeral.debitoACobrarHistorico.imovel.id" />&debitoID=<bean:write name="pagamentoHistorico" property="debitoACobrarGeral.id" />', 560, 660);">
														<font color="#ff0000">
														${pagamentoHistorico.debitoACobrarGeral.debitoACobrarHistorico.debitoTipo.descricao} </font>
														&nbsp;</a>
														</logic:notEmpty>
													</logic:notEmpty>
													
													
													<logic:empty name="pagamentoHistorico"
														property="debitoACobrarGeral">${pagamentoHistorico.debitoTipo.descricao} 
													</logic:empty></td>
													
													<td width="17%" align="right">
													
													<logic:notEmpty
														name="pagamentoHistorico" property="debitoACobrarGeral">
														<logic:notEmpty
															name="pagamentoHistorico" property="debitoACobrarGeral.debitoACobrarHistorico">
															<logic:notEmpty name="pagamentoHistorico"
																property="debitoACobrarGeral.debitoACobrarHistorico.valorTotalComBonus">
																<font color="#ff0000"> <bean:write
																	name="pagamentoHistorico"
																	property="debitoACobrarGeral.debitoACobrarHistorico.valorTotalComBonus"
																	formatKey="money.format" /> </font>		
																					&nbsp;
															</logic:notEmpty>
														</logic:notEmpty>
													</logic:notEmpty></td>
													<td width="17%" align="right"><font color="#ff0000"> <bean:write
														name="pagamentoHistorico" property="valorPagamento"
														formatKey="money.format" /> </font> &nbsp;</td>

													<td width="17%" align="center"><font color="#ff0000"> 
													<a style="color: #FF0000;"
														href="javascript:abrirPopup('exibirConsultarPagamentoPopupAction.do?idPagamentoHistorico=${pagamentoHistorico.id}' , 210, 510);">
														<bean:write	name="pagamentoHistorico" property="dataPagamento" formatKey="date.format" /> 
													</a>	
													</font> &nbsp;</td>
													
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
					<td colspan="2"><input type="button" name="ButtonCancelar"
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
