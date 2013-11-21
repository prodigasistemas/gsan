<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%@page import="gcom.arrecadacao.Devolucao"%>
<%@page import="gcom.arrecadacao.DevolucaoHistorico"%>
<%@page import="gcom.cadastro.cliente.ClienteImovel"%>
<%@page import="gcom.util.Util" isELIgnored="false"%>
<%@ page import="gcom.util.ConstantesSistema" isELIgnored="false"%>

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
<html:form action="/exibirConsultarDevolucaoAction" method="post">

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
					<td class="parabg">Consultar Devoluções do Imóvel</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<table width="100%" border="0" class="fontePequena">
				<tr>
					<td width="30%"><strong>Matrícula do Imóvel:</strong></td>
					<td>
						<html:text property="idImovel" readonly="true" style="background-color:#EFEFEF; border:0;"
							size="10" maxlength="10"/>
					</td>
				</tr>
			
			
				<tr>
					<td width="180"><strong>Inscrição:</strong></td>
					<td colspan="3" align="left">
					<div align="left"><html:text property="inscricao" readonly="true"
						style="background-color:#EFEFEF; border:0; font-size:9px;"
						size="30" maxlength="30" /></div>
					</td>
				</tr>
				<tr>
					<td width="180"><strong>Situação da Ligação de Água:</strong></td>

					<td colspan="3" align="left"><html:text
						property="situacaoLigacaoAgua" readonly="true"
						style="background-color:#EFEFEF; border:0;font-size:9px;"
						size="30" maxlength="30" /></td>
				</tr>
				<tr>
					<td width="180"><strong>Situação da Ligação de Esgoto:</strong></td>

					<td align="right">
					<div align="left"><html:text property="situacaoLigacaoEsgoto"
						readonly="true"
						style="background-color:#EFEFEF; border:0 ;font-size:9px;"
						size="30" maxlength="30" /></div>
					</td>
				</tr>
				<tr>
					<td colspan="4">
					<table width="100%" bgcolor="#99CCFF" class="fontePequena">
						<tr bgcolor="#90c7fc" height="18">
								<td align="center"><strong>Endere&ccedil;o</strong></td>
						</tr>
						<tr>
							<logic:present name="imovel" scope="session">
								<td bgcolor="#FFFFFF">
								<div align="center"><span id="endereco"> <bean:write
									name="imovel" property="enderecoFormatado" scope="session" />
								</span></div>
								</td>
							</logic:present>
							<logic:notPresent name="imovel" scope="session">
								<td bgcolor="#FFFFFF">&nbsp;</td>
							</logic:notPresent>
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
							<td align="center">
								<strong>Clientes</strong>
							</td>
						</tr>
						
						<tr bgcolor="#cbe5fe">
							<td width="100%" align="center">
							<table width="100%" bgcolor="#90c7fc" class="fontePequena">
								<tr bordercolor="#000000">
									<td width="40%" bgcolor="#90c7fc" align="center"><strong>Nome do Cliente</strong></td>
									<td width="20%" bgcolor="#90c7fc" align="center"><strong>Tipo da Relação</strong></td>
									<td width="20%" bgcolor="#90c7fc" align="center"><strong>CPF</strong></td>
									<td width="20%" bgcolor="#90c7fc" align="center"><strong>CNPJ</strong></td>
								</tr>
								
								<logic:present name="colecaoClientes" scope="request">
									<%int c = 1;%>
									<logic:iterate name="colecaoClientes" id="clienteImovel"
										type="ClienteImovel">
										<%c = c + 1;
										if (c % 2 == 0) {%>
										<tr bgcolor="#FFFFFF">
											<%} else {

											%>
										<tr bgcolor="#cbe5fe">
											<%}%>
											
											
											<logic:present name="clienteImovel" property="cliente.nome" >
												<td width="40%" >${clienteImovel.cliente.nome}&nbsp;</td>
											</logic:present>
											<logic:notPresent name="clienteImovel" property="cliente.nome" >
												<td width="40%" >&nbsp;</td>
											</logic:notPresent>
											
											<logic:present name="clienteImovel" property="clienteRelacaoTipo" >
												<td width="20%" >${clienteImovel.clienteRelacaoTipo.descricao}&nbsp;</td>
											</logic:present>
											<logic:notPresent name="clienteImovel" property="clienteRelacaoTipo" >
												<td width="20%" >&nbsp;</td>
											</logic:notPresent>
											
											<logic:present name="clienteImovel" property="cliente.cpfFormatado" >
												<td width="20%" align="right">${clienteImovel.cliente.cpfFormatado}&nbsp;</td>
											</logic:present>
											<logic:notPresent name="clienteImovel" property="cliente.cpfFormatado" >
												<td width="20%" align="right">&nbsp;</td>
											</logic:notPresent>
											
											<logic:present name="clienteImovel" property="cliente.cnpjFormatado" >
												<td width="20%" align="right">${clienteImovel.cliente.cnpjFormatado}&nbsp;</td>
											</logic:present>
											<logic:notPresent name="clienteImovel" property="cliente.cnpjFormatado" >
												<td width="20%" align="right">&nbsp;</td>
											</logic:notPresent>

										</tr>
									</logic:iterate>
								</logic:present>
							</table>
							</td>
						</tr>
					</table>
				<tr>
					<td colspan="4" height="10"></td>
				</tr>
				<tr>
					<td colspan="4">
					<table width="100%" align="center" bgcolor="#99CCFF" border="0"
						cellpadding="0" cellspacing="0" class="fontePequena">
						<tr bgcolor="#79bbfd" height="20">
							<td align="center">
								<strong>Devoluções das Contas</strong>
							</td>
						</tr>
						<%int cont = 1;%>
						<tr bgcolor="#cbe5fe">
							<td width="100%" align="center">
							<table width="100%" bgcolor="#90c7fc" class="fontePequena">
								<tr bordercolor="#000000">
									<td width="14%" bgcolor="#90c7fc" align="center" rowspan="2"><strong>Mês/Ano</strong></td>
									<td width="19%" bgcolor="#90c7fc" align="center" rowspan="2"><strong>Valor da Conta</strong></td>
									<td width="19%" bgcolor="#90c7fc" align="center" rowspan="2"><strong>Valor da Devol.</strong></td>
									<td width="16%" bgcolor="#90c7fc" align="center" rowspan="2"><strong>Data da Devol.</strong></td>
									<td width="32%" bgcolor="#90c7fc" align="center" colspan="2"><strong>Situação</strong></td>
								</tr>
								<tr>
									<td width="16%" bgcolor="#cbe5fe" align="center"><strong>Anterior</strong></td>
									<td width="16%" bgcolor="#cbe5fe" align="center"><strong>Atual</strong></td>
								</tr>
								<%if ((Integer) session.getAttribute("qtdeDevContas")
									<= ConstantesSistema.NUMERO_MAXIMO_REGISTROS_CONSULTA_DEVOLUCAO) {%>
				
								
								<!-- Devolução -->
								<logic:present name="colecaoDevolucaoImovelConta"
									scope="request">
									
									<logic:iterate name="colecaoDevolucaoImovelConta"
										id="devolucao" type="Devolucao">
										<%cont = cont + 1;
											if (cont % 2 == 0) {%>
										<tr bgcolor="#FFFFFF">
											<%} else {

											%>
										<tr bgcolor="#cbe5fe">
											<%}%>
											
											<%-- jah tava comentado 										
											<td width="14%" align="center">
												<a href="javascript:abrirPopup('exibirConsultarContaAction.do?tipoConsulta=conta&contaID=<%="" + devolucao.getGuiaDevolucao().getConta().getId() %>' , 600, 800);">${devolucao.guiaDevolucao.conta.formatarAnoMesParaMesAno}&nbsp;</a>
											</td>
											--%>									
											<logic:present name="devolucao" property="guiaDevolucao">
												<logic:present name="devolucao" property="guiaDevolucao.conta" >
													<td width="14%" align="center">
														<a href="javascript:abrirPopup('exibirConsultarContaAction.do?tipoConsulta=conta&contaID=<%="" + devolucao.getGuiaDevolucao().getConta().getId() %>' , 600, 800);">${devolucao.guiaDevolucao.conta.formatarAnoMesParaMesAno}&nbsp;</a>
													</td>
												</logic:present>
												<logic:notPresent name="devolucao" property="guiaDevolucao.conta" >
														<td width="14%" align="center">${devolucao.formatarAnoMesDevolucaoParaMesAno}&nbsp;</td>
												</logic:notPresent>
											</logic:present>			
											<logic:notPresent name="devolucao" property="guiaDevolucao" >
													<td width="14%" align="center">${devolucao.formatarAnoMesDevolucaoParaMesAno}&nbsp;</td>
											</logic:notPresent>												
									
									
											<logic:present name="devolucao" property="guiaDevolucao">
												<logic:present name="devolucao" property="guiaDevolucao.conta" >
													<logic:present name="devolucao" property="guiaDevolucao.conta.valorTotal" >
														<td width="19%" align="right">${devolucao.guiaDevolucao.conta.valorTotal}&nbsp;</td>
													</logic:present>
													<logic:notPresent name="devolucao" property="guiaDevolucao.conta.valorTotal" >
														<td width="19%" align="center">&nbsp;</td>
													</logic:notPresent>
												</logic:present>
												<logic:notPresent name="devolucao" property="guiaDevolucao.conta" >
													<td width="19%" align="center">&nbsp;</td>
												</logic:notPresent>
											</logic:present>
											<logic:notPresent name="devolucao" property="guiaDevolucao" >
												<td width="19%" align="center">&nbsp;</td>
											</logic:notPresent>
											
												
											<logic:present name="devolucao" property="valorDevolucao" >
												<td width="19%" align="right">${devolucao.valorDevolucao}&nbsp;</td>
											</logic:present>
											<logic:notPresent name="devolucao" property="valorDevolucao" >
												<td width="19%" align="center">&nbsp;</td>
											</logic:notPresent>
											
											<logic:present name="devolucao" property="dataDevolucao" >
												<td width="16%" align="center"><bean:write name="devolucao" property="dataDevolucao" formatKey="date.format" />&nbsp;</td>
											</logic:present>
											<logic:notPresent name="devolucao" property="dataDevolucao" >
												<td width="16%" align="center">&nbsp;</td>
											</logic:notPresent>
											
											<logic:present name="devolucao" property="devolucaoSituacaoAnterior" >
												<logic:present name="devolucao" property="devolucaoSituacaoAnterior.descricaoAbreviado" >
													<td width="16%">${devolucao.devolucaoSituacaoAnterior.descricaoAbreviado}&nbsp;</td>
												</logic:present>
												<logic:notPresent name="devolucao" property="devolucaoSituacaoAnterior.descricaoAbreviado" >
													<td width="16%" align="center">&nbsp;</td>
												</logic:notPresent>
											</logic:present>
											<logic:notPresent name="devolucao" property="devolucaoSituacaoAnterior" >
												<td width="16%" align="center">&nbsp;</td>
											</logic:notPresent>
											
											<logic:present name="devolucao" property="devolucaoSituacaoAtual" >
												<logic:present name="devolucao" property="devolucaoSituacaoAtual.descricaoAbreviado" >
													<td width="16%">${devolucao.devolucaoSituacaoAtual.descricaoAbreviado}&nbsp;</td>
												</logic:present>
												<logic:notPresent name="devolucao" property="devolucaoSituacaoAtual.descricaoAbreviado" >
													<td width="16%" align="center">&nbsp;</td>
												</logic:notPresent>
											</logic:present>
											<logic:notPresent name="devolucao" property="devolucaoSituacaoAtual" >
												<td width="16%" align="center">&nbsp;</td>
											</logic:notPresent>
											
										</tr>
									</logic:iterate>
								</logic:present>
								
								
								<!-- Devolução Histórico -->
								
								<logic:present name="colecaoDevolucaoHistoricoImovelConta"
									scope="request">
									
									<logic:iterate name="colecaoDevolucaoHistoricoImovelConta"
										id="devolucaoHistorico" type="DevolucaoHistorico">
										<%cont = cont + 1;
											if (cont % 2 == 0) {%>
										<tr bgcolor="#FFFFFF">
											<%} else {

											%>
										<tr bgcolor="#cbe5fe">
											<%}%>
											
											<%-- jah tava comentado 										
											<td width="14%" align="center">
												<a href="javascript:abrirPopup('exibirConsultarContaAction.do?tipoConsulta=conta&contaID=<%="" + devolucao.getGuiaDevolucao().getConta().getId() %>' , 600, 800);">${devolucao.guiaDevolucao.conta.formatarAnoMesParaMesAno}&nbsp;</a>
											</td>
											--%>									
											<td width="14%" align="center"><!--
												<logic:present name="devolucaoHistorico" property="anoMesReferenciaArrecadacao">
														<font color="#ff0000" >${devolucaoHistorico.formatarAnoMesParaMesAnoArrecadacao}</font>
												</logic:present>
											 &nbsp;
											 -->
  												<logic:present name="devolucaoHistorico" property="anoMesReferenciaDevolucao">  												
														<font color="#ff0000" >${devolucaoHistorico.formatarAnoMesParaMesAnoDevolucao}</font>
												</logic:present>
											</td>										
											<logic:present name="devolucaoHistorico" property="guiaDevolucao">
												<logic:present name="devolucaoHistorico" property="guiaDevolucao.conta" >
													<logic:present name="devolucaoHistorico" property="guiaDevolucao.conta.valorTotal" >
															<td width="19%" align="right"><font color="#ff0000" >${devolucaoHistorico.guiaDevolucao.conta.valorTotal}</font>&nbsp;</td>
													</logic:present>
													<logic:notPresent name="devolucaoHistorico" property="guiaDevolucao.conta.valorTotal" >
														<td width="19%" align="center">&nbsp;</td>
													</logic:notPresent>
												</logic:present>
												<logic:notPresent name="devolucaoHistorico" property="guiaDevolucao.conta" >
													<td width="19%" align="center">&nbsp;</td>
												</logic:notPresent>
											</logic:present>
											<logic:notPresent name="devolucaoHistorico" property="guiaDevolucao" >
												<td width="19%" align="center">&nbsp;</td>
											</logic:notPresent>
											
											<logic:present name="devolucaoHistorico" property="valorDevolucao" >
												<td width="19%" align="right">
												<font color="#ff0000" >${devolucaoHistorico.valorDevolucao}</font>
												&nbsp;</td>
											</logic:present>
											<logic:notPresent name="devolucaoHistorico" property="valorDevolucao" >
												<td width="19%" align="center">&nbsp;</td>
											</logic:notPresent>
											
											<logic:present name="devolucaoHistorico" property="dataDevolucao" >
													<td width="16%" align="center"><font color="#ff0000" ><bean:write name="devolucaoHistorico" property="dataDevolucao" formatKey="date.format" /></font>&nbsp;</td>
											</logic:present>
											<logic:notPresent name="devolucaoHistorico" property="dataDevolucao" >
												<td width="16%" align="center">&nbsp;</td>
											</logic:notPresent>
											
											<logic:present name="devolucaoHistorico" property="devolucaoSituacaoAnterior" >
												<logic:present name="devolucaoHistorico" property="devolucaoSituacaoAnterior.descricaoAbreviado" >
														<td width="16%"><font color="#ff0000" >${devolucaoHistorico.devolucaoSituacaoAnterior.descricaoAbreviado}</font>&nbsp;</td>
												</logic:present>
												<logic:notPresent name="devolucaoHistorico" property="devolucaoSituacaoAnterior.descricaoAbreviado" >
													<td width="16%" align="center">&nbsp;</td>
												</logic:notPresent>
											</logic:present>
											<logic:notPresent name="devolucaoHistorico" property="devolucaoSituacaoAnterior" >
												<td width="16%" align="center">&nbsp;</td>
											</logic:notPresent>
											
											<logic:present name="devolucaoHistorico" property="devolucaoSituacaoAtual" >
												<logic:present name="devolucaoHistorico" property="devolucaoSituacaoAtual.descricaoAbreviado" >
													<td width="16%"><font color="#ff0000" >${devolucaoHistorico.devolucaoSituacaoAtual.descricaoAbreviado}</font>&nbsp;</td>
												</logic:present>
												<logic:notPresent name="devolucaoHistorico" property="devolucaoSituacaoAtual.descricaoAbreviado" >
													<td width="16%" align="center">&nbsp;</td>
												</logic:notPresent>
											</logic:present>
											<logic:notPresent name="devolucaoHistorico" property="devolucaoSituacaoAtual" >
												<td width="16%" align="center">&nbsp;</td>
											</logic:notPresent>
										</tr>
									</logic:iterate>
								</logic:present>
								
								<%} else {%>
									<tr>
										<td height="190" colspan="6">
											<div style="width: 100%; height: 100%; overflow: auto;">
												<table width="100%">
								
													<!-- Devolução -->
													<logic:present name="colecaoDevolucaoImovelConta"
														scope="request">
														
														<logic:iterate name="colecaoDevolucaoImovelConta"
															id="devolucao" type="Devolucao">
															<%cont = cont + 1;
																if (cont % 2 == 0) {%>
															<tr bgcolor="#FFFFFF">
																<%} else {
					
																%>
															<tr bgcolor="#cbe5fe">
																<%}%>
																
																<%-- jah tava comentado 										
																<td width="14%" align="center">
																	<a href="javascript:abrirPopup('exibirConsultarContaAction.do?tipoConsulta=conta&contaID=<%="" + devolucao.getGuiaDevolucao().getConta().getId() %>' , 600, 800);">${devolucao.guiaDevolucao.conta.formatarAnoMesParaMesAno}&nbsp;</a>
																</td>
																--%>									
																<logic:present name="devolucao" property="guiaDevolucao">
																	<logic:present name="devolucao" property="guiaDevolucao.conta" >
																		<td width="14%" align="center">
																			<a href="javascript:abrirPopup('exibirConsultarContaAction.do?tipoConsulta=conta&contaID=<%="" + devolucao.getGuiaDevolucao().getConta().getId() %>' , 600, 800);">${devolucao.guiaDevolucao.conta.formatarAnoMesParaMesAno}&nbsp;</a>
																		</td>
																	</logic:present>
																	<logic:notPresent name="devolucao" property="guiaDevolucao.conta" >
																			<td width="14%" align="center">${devolucao.formatarAnoMesDevolucaoParaMesAno}&nbsp;</td>
																	</logic:notPresent>
																</logic:present>			
																<logic:notPresent name="devolucao" property="guiaDevolucao" >
																		<td width="14%" align="center">${devolucao.formatarAnoMesDevolucaoParaMesAno}&nbsp;</td>
																</logic:notPresent>												
														
														
																<logic:present name="devolucao" property="guiaDevolucao">
																	<logic:present name="devolucao" property="guiaDevolucao.conta" >
																		<logic:present name="devolucao" property="guiaDevolucao.conta.valorTotal" >
																			<td width="19%" align="right">${devolucao.guiaDevolucao.conta.valorTotal}&nbsp;</td>
																		</logic:present>
																		<logic:notPresent name="devolucao" property="guiaDevolucao.conta.valorTotal" >
																			<td width="19%" align="center">&nbsp;</td>
																		</logic:notPresent>
																	</logic:present>
																	<logic:notPresent name="devolucao" property="guiaDevolucao.conta" >
																		<td width="19%" align="center">&nbsp;</td>
																	</logic:notPresent>
																</logic:present>
																<logic:notPresent name="devolucao" property="guiaDevolucao" >
																	<td width="19%" align="center">&nbsp;</td>
																</logic:notPresent>
																
																	
																<logic:present name="devolucao" property="valorDevolucao" >
																	<td width="20%" align="right">${devolucao.valorDevolucao}&nbsp;</td>
																</logic:present>
																<logic:notPresent name="devolucao" property="valorDevolucao" >
																	<td width="20%" align="center">&nbsp;</td>
																</logic:notPresent>
																
																<logic:present name="devolucao" property="dataDevolucao" >
																	<td width="16%" align="center"><bean:write name="devolucao" property="dataDevolucao" formatKey="date.format" />&nbsp;</td>
																</logic:present>
															<logic:notPresent name="devolucao" property="dataDevolucao" >
																<td width="16%" align="center">&nbsp;</td>
															</logic:notPresent>
															
															<logic:present name="devolucao" property="devolucaoSituacaoAnterior" >
																<logic:present name="devolucao" property="devolucaoSituacaoAnterior.descricaoAbreviado" >
																	<td width="16.5%">${devolucao.devolucaoSituacaoAnterior.descricaoAbreviado}&nbsp;</td>
																</logic:present>
																<logic:notPresent name="devolucao" property="devolucaoSituacaoAnterior.descricaoAbreviado" >
																	<td width="16.5%" align="center">&nbsp;</td>
																</logic:notPresent>
															</logic:present>
															<logic:notPresent name="devolucao" property="devolucaoSituacaoAnterior" >
																<td width="16.5%" align="center">&nbsp;</td>
															</logic:notPresent>
															
															<logic:present name="devolucao" property="devolucaoSituacaoAtual" >
																<logic:present name="devolucao" property="devolucaoSituacaoAtual.descricaoAbreviado" >
																	<td width="13.5%">${devolucao.devolucaoSituacaoAtual.descricaoAbreviado}&nbsp;</td>
																</logic:present>
																<logic:notPresent name="devolucao" property="devolucaoSituacaoAtual.descricaoAbreviado" >
																	<td width="13.5%" align="center">&nbsp;</td>
																</logic:notPresent>
															</logic:present>
															<logic:notPresent name="devolucao" property="devolucaoSituacaoAtual" >
																<td width="13.5%" align="center">&nbsp;</td>
															</logic:notPresent>
															
														</tr>
													</logic:iterate>
												</logic:present>
												
												
												<!-- Devolução Histórico -->
												
												<logic:present name="colecaoDevolucaoHistoricoImovelConta"
													scope="request">
													
													<logic:iterate name="colecaoDevolucaoHistoricoImovelConta"
														id="devolucaoHistorico" type="DevolucaoHistorico">
														<%cont = cont + 1;
															if (cont % 2 == 0) {%>
														<tr bgcolor="#FFFFFF">
															<%} else {
				
															%>
														<tr bgcolor="#cbe5fe">
															<%}%>
															
															<%-- jah tava comentado 										
															<td width="14%" align="center">
																<a href="javascript:abrirPopup('exibirConsultarContaAction.do?tipoConsulta=conta&contaID=<%="" + devolucao.getGuiaDevolucao().getConta().getId() %>' , 600, 800);">${devolucao.guiaDevolucao.conta.formatarAnoMesParaMesAno}&nbsp;</a>
															</td>
															--%>									
															<td width="14%" align="center">
				  												<logic:present name="devolucaoHistorico" property="anoMesReferenciaDevolucao">  												
																		<font color="#ff0000" >${devolucaoHistorico.formatarAnoMesParaMesAnoDevolucao}</font>
																</logic:present>
														 &nbsp;
														 </td>										
														<logic:present name="devolucaoHistorico" property="guiaDevolucao">
															<logic:present name="devolucaoHistorico" property="guiaDevolucao.conta" >
																<logic:present name="devolucaoHistorico" property="guiaDevolucao.conta.valorTotal" >
																		<td width="19%" align="right"><font color="#ff0000" >${devolucaoHistorico.guiaDevolucao.conta.valorTotal}</font>&nbsp;</td>
																</logic:present>
																<logic:notPresent name="devolucaoHistorico" property="guiaDevolucao.conta.valorTotal" >
																	<td width="19%" align="center">&nbsp;</td>
																</logic:notPresent>
															</logic:present>
															<logic:notPresent name="devolucaoHistorico" property="guiaDevolucao.conta" >
																<td width="19%" align="center">&nbsp;</td>
															</logic:notPresent>
														</logic:present>
														<logic:notPresent name="devolucaoHistorico" property="guiaDevolucao" >
															<td width="19%" align="center">&nbsp;</td>
														</logic:notPresent>
														
														<logic:present name="devolucaoHistorico" property="valorDevolucao" >
															<td width="20%" align="right">
															<font color="#ff0000" >${devolucaoHistorico.valorDevolucao}</font>
															&nbsp;</td>
														</logic:present>
														<logic:notPresent name="devolucaoHistorico" property="valorDevolucao" >
															<td width="20%" align="center">&nbsp;</td>
														</logic:notPresent>
														
														<logic:present name="devolucaoHistorico" property="dataDevolucao" >
																<td width="16%" align="center"><font color="#ff0000" ><bean:write name="devolucaoHistorico" property="dataDevolucao" formatKey="date.format" /></font>&nbsp;</td>
														</logic:present>
														<logic:notPresent name="devolucaoHistorico" property="dataDevolucao" >
															<td width="16%" align="center">&nbsp;</td>
														</logic:notPresent>
														
														<logic:present name="devolucaoHistorico" property="devolucaoSituacaoAnterior" >
															<logic:present name="devolucaoHistorico" property="devolucaoSituacaoAnterior.descricaoAbreviado" >
																	<td width="16.5%"><font color="#ff0000" >${devolucaoHistorico.devolucaoSituacaoAnterior.descricaoAbreviado}</font>&nbsp;</td>
															</logic:present>
															<logic:notPresent name="devolucaoHistorico" property="devolucaoSituacaoAnterior.descricaoAbreviado" >
																<td width="16.5%" align="center">&nbsp;</td>
															</logic:notPresent>
														</logic:present>
														<logic:notPresent name="devolucaoHistorico" property="devolucaoSituacaoAnterior" >
															<td width="16.5%" align="center">&nbsp;</td>
														</logic:notPresent>
														
														<logic:present name="devolucaoHistorico" property="devolucaoSituacaoAtual" >
															<logic:present name="devolucaoHistorico" property="devolucaoSituacaoAtual.descricaoAbreviado" >
																<td width="13.5%"><font color="#ff0000" >${devolucaoHistorico.devolucaoSituacaoAtual.descricaoAbreviado}</font>&nbsp;</td>
															</logic:present>
															<logic:notPresent name="devolucaoHistorico" property="devolucaoSituacaoAtual.descricaoAbreviado" >
																<td width="13.5%" align="center">&nbsp;</td>
															</logic:notPresent>
														</logic:present>
														<logic:notPresent name="devolucaoHistorico" property="devolucaoSituacaoAtual" >
															<td width="13.5%" align="center">&nbsp;</td>
														</logic:notPresent>
														
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
				
					<tr>
						<td colspan="4" height="10"></td>
					</tr>
					<tr>
						<td colspan="4">
						<table width="100%" align="center" bgcolor="#99CCFF" border="0"
						cellpadding="0" cellspacing="0" class="fontePequena">
						<tr bgcolor="#79bbfd" height="20">
							<td align="center">
								<strong>Devoluções das Guias de Pagamento</strong>
							</td>
						</tr>
						<%int contad = 1;%>
						<tr bgcolor="#cbe5fe">
							<td width="100%" align="center">

							<table width="100%" bgcolor="#90c7fc" class="fontePequena">
								<tr bordercolor="#000000">
									<td width="12%" bgcolor="#90c7fc" align="center" rowspan="2"><strong>Cliente</strong></td>
									<td width="15%" bgcolor="#90c7fc" align="center" rowspan="2"><strong>Tipo do Débito</strong></td>
									<td width="15%" bgcolor="#90c7fc" align="center" rowspan="2"><strong>Valor da Guia de Pagto.</strong></td>
									<td width="15%" bgcolor="#90c7fc" align="center" rowspan="2"><strong>Valor da Devol.</strong></td>
									<td width="11%" bgcolor="#90c7fc" align="center" rowspan="2"><strong>Data da Devol.</strong></td>
									<td width="32%" bgcolor="#90c7fc" align="center" colspan="2"><strong>Situação</strong></td>
								</tr>
								<tr>
									<td width="16%" bgcolor="#cbe5fe" align="center"><strong>Anterior</strong></td>
									<td width="16%" bgcolor="#cbe5fe" align="center"><strong>Atual</strong></td>
								</tr>
								
								<%if ((Integer) session.getAttribute("qtdeDevGuiaPagamento")
									<= ConstantesSistema.NUMERO_MAXIMO_REGISTROS_CONSULTA_DEVOLUCAO) {%>
				
								<!-- Devoluções -->
								<logic:present name="colecaoDevolucaoImovelGuiaPagamento"
									scope="request">
									
									<logic:iterate name="colecaoDevolucaoImovelGuiaPagamento"
										id="devolucao" type="Devolucao">
										<%contad = contad + 1;
											if (contad % 2 == 0) {%>
										<tr bgcolor="#FFFFFF">
											<%} else {

											%>
										<tr bgcolor="#cbe5fe">
											<%}%>
											<td width="12%" align="center">${devolucao.cliente.id}&nbsp;</td>
											<td width="15%" align="center">
												<logic:present name="devolucao" property="guiaDevolucao">
													<logic:present name="devolucao" property="guiaDevolucao.guiaPagamento">
													   <logic:present name="devolucao" property="guiaDevolucao.guiaPagamento.debitoTipo">
															<a href="javascript:abrirPopup('exibirConsultarGuiaPagamentoAction.do?guiaPagamentoId=<%="" + devolucao.getGuiaDevolucao().getGuiaPagamento().getId() %>')">${devolucao.guiaDevolucao.guiaPagamento.debitoTipo.descricao}</a>	
														</logic:present>
													</logic:present>
												</logic:present>
												&nbsp;
											</td>
											<td width="15%" align="right">
												<logic:present name="devolucao" property="guiaDevolucao">
													<logic:present name="devolucao" property="guiaDevolucao.guiaPagamento">
														${devolucao.guiaDevolucao.guiaPagamento.valorDebito}
													</logic:present>
												</logic:present>
												&nbsp;
											</td>
											<td width="15%" align="right">
												${devolucao.valorDevolucao}&nbsp;
											</td>
											<td width="11%" align="center">
												<bean:write name="devolucao" property="dataDevolucao" formatKey="date.format" />&nbsp;
											</td>
											<td width="16%">
												<logic:present name="devolucao" property="devolucaoSituacaoAnterior">
													${devolucao.devolucaoSituacaoAnterior.descricaoAbreviado}
												</logic:present>
												&nbsp;	
											</td>
											<td width="16%">
												<logic:present name="devolucao" property="devolucaoSituacaoAtual">
													${devolucao.devolucaoSituacaoAtual.descricaoAbreviado}
												</logic:present>
												&nbsp;
											</td>
										</tr>
									</logic:iterate>
								</logic:present>
								
								<!-- Devoluções Histórico-->
								<logic:present name="colecaoDevolucaoHistoricoImovelGuiaPagamento"
									scope="request">
									
									<logic:iterate name="colecaoDevolucaoHistoricoImovelGuiaPagamento"
										id="devolucaoHistorico" type="DevolucaoHistorico">
										<%contad = contad + 1;
											if (contad % 2 == 0) {%>
										<tr bgcolor="#FFFFFF">
											<%} else {

											%>
										<tr bgcolor="#cbe5fe">
											<%}%>
											<td width="12%" align="center"><font color="#ff0000" >${devolucaoHistorico.cliente.id}</font>&nbsp;</td>
											<td width="15%" align="center">
												<logic:present name="devolucaoHistorico" property="guiaDevolucao">
													<logic:present name="devolucaoHistorico" property="guiaDevolucao.guiaPagamento">
													   <logic:present name="devolucaoHistorico" property="guiaDevolucao.guiaPagamento.debitoTipo">
															 <a href="javascript:abrirPopup('exibirConsultarGuiaPagamentoAction.do?guiaPagamentoId=<%="" + devolucaoHistorico.getGuiaDevolucao().getGuiaPagamento().getId() %>')"><font color="#ff0000" >${devolucaoHistorico.guiaDevolucao.guiaPagamento.debitoTipo.descricao}</font></a>	
														</logic:present>
													</logic:present>
												</logic:present>
												&nbsp;
											</td>
											<td width="15%" align="right">
												<logic:present name="devolucaoHistorico" property="guiaDevolucao">
													<logic:present name="devolucaoHistorico" property="guiaDevolucao.guiaPagamento">
														<font color="#ff0000" >${devolucaoHistorico.guiaDevolucao.guiaPagamento.valorDebito}</font>
													</logic:present>
												</logic:present>
												&nbsp;
											</td>
											<td width="15%" align="right">
												<font color="#ff0000" >${devolucaoHistorico.valorDevolucao}</font>&nbsp;
											</td>
											<td width="11%" align="center">
												<font color="#ff0000" ><bean:write name="devolucaoHistorico" property="dataDevolucao" formatKey="date.format" /></font>&nbsp;
											</td>
											<td width="16%">
												<logic:present name="devolucaoHistorico" property="devolucaoSituacaoAnterior">
													<font color="#ff0000" >${devolucaoHistorico.devolucaoSituacaoAnterior.descricaoAbreviado}</font>
												</logic:present>
												&nbsp;	
											</td>
											<td width="16%">
												<logic:present name="devolucaoHistorico" property="devolucaoSituacaoAtual">
													<font color="#ff0000" >${devolucaoHistorico.devolucaoSituacaoAtual.descricaoAbreviado}</font>
												</logic:present>
												&nbsp;
											</td>
										</tr>
									</logic:iterate>
								</logic:present>
								
								<%} else {%>
									<tr>
										<td height="190" colspan="7">
											<div style="width: 100%; height: 100%; overflow: auto;">
												<table width="100%">
								
													<!-- Devoluções -->
													<logic:present name="colecaoDevolucaoImovelGuiaPagamento"
														scope="request">
														
														<logic:iterate name="colecaoDevolucaoImovelGuiaPagamento"
															id="devolucao" type="Devolucao">
															<%contad = contad + 1;
																if (contad % 2 == 0) {%>
															<tr bgcolor="#FFFFFF">
																<%} else {
					
																%>
															<tr bgcolor="#cbe5fe">
																<%}%>
																<td width="12%" align="center">${devolucao.cliente.id}&nbsp;</td>
																<td width="15%" align="center">
																	<logic:present name="devolucao" property="guiaDevolucao">
																		<logic:present name="devolucao" property="guiaDevolucao.guiaPagamento">
																		   <logic:present name="devolucao" property="guiaDevolucao.guiaPagamento.debitoTipo">
																				<a href="javascript:abrirPopup('exibirConsultarGuiaPagamentoAction.do?guiaPagamentoId=<%="" + devolucao.getGuiaDevolucao().getGuiaPagamento().getId() %>')">${devolucao.guiaDevolucao.guiaPagamento.debitoTipo.descricao}</a>	
																			</logic:present>
																		</logic:present>
																	</logic:present>
																	&nbsp;
																</td>
																<td width="15%" align="right">
																	<logic:present name="devolucao" property="guiaDevolucao">
																		<logic:present name="devolucao" property="guiaDevolucao.guiaPagamento">
																			${devolucao.guiaDevolucao.guiaPagamento.valorDebito}
																		</logic:present>
																	</logic:present>
																	&nbsp;
																</td>
																<td width="15%" align="right">
																	${devolucao.valorDevolucao}&nbsp;
																</td>
																<td width="12.9%" align="center">
																	<bean:write name="devolucao" property="dataDevolucao" formatKey="date.format" />&nbsp;
																</td>
																<td width="16.6%">
																	<logic:present name="devolucao" property="devolucaoSituacaoAnterior">
																		${devolucao.devolucaoSituacaoAnterior.descricaoAbreviado}
																	</logic:present>
																	&nbsp;	
																</td>
																<td width="13.5%">
																	<logic:present name="devolucao" property="devolucaoSituacaoAtual">
																		${devolucao.devolucaoSituacaoAtual.descricaoAbreviado}
																	</logic:present>
																	&nbsp;
																</td>
															</tr>
														</logic:iterate>
													</logic:present>
													
													<!-- Devoluções Histórico-->
													<logic:present name="colecaoDevolucaoHistoricoImovelGuiaPagamento"
														scope="request">
														
														<logic:iterate name="colecaoDevolucaoHistoricoImovelGuiaPagamento"
															id="devolucaoHistorico" type="DevolucaoHistorico">
															<%contad = contad + 1;
																if (contad % 2 == 0) {%>
															<tr bgcolor="#FFFFFF">
																<%} else {
					
																%>
															<tr bgcolor="#cbe5fe">
																<%}%>
																<td width="12%" align="center"><font color="#ff0000" >${devolucaoHistorico.cliente.id}</font>&nbsp;</td>
																<td width="15%" align="center">
																	<logic:present name="devolucaoHistorico" property="guiaDevolucao">
																		<logic:present name="devolucaoHistorico" property="guiaDevolucao.guiaPagamento">
																		   <logic:present name="devolucaoHistorico" property="guiaDevolucao.guiaPagamento.debitoTipo">
																				<a href="javascript:abrirPopup('exibirConsultarGuiaPagamentoAction.do?guiaPagamentoId=<%="" + devolucaoHistorico.getGuiaDevolucao().getGuiaPagamento().getId() %>')"><font color="#ff0000" >${devolucaoHistorico.guiaDevolucao.guiaPagamento.debitoTipo.descricao}</font></a>
																			</logic:present>
																		</logic:present>
																	</logic:present>
																	&nbsp;
																</td>
																<td width="15%" align="right">
																	<logic:present name="devolucaoHistorico" property="guiaDevolucao">
																		<logic:present name="devolucaoHistorico" property="guiaDevolucao.guiaPagamento">
																			<font color="#ff0000" >${devolucaoHistorico.guiaDevolucao.guiaPagamento.valorDebito}</font>
																		</logic:present>
																	</logic:present>
																	&nbsp;
																</td>
																<td width="15%" align="right">
																	<font color="#ff0000" >${devolucaoHistorico.valorDevolucao}</font>&nbsp;
																</td>
																<td width="12.9%" align="center">
																	<font color="#ff0000" ><bean:write name="devolucaoHistorico" property="dataDevolucao" formatKey="date.format" /></font>&nbsp;
																</td>
																<td width="16.6%">
																	<logic:present name="devolucaoHistorico" property="devolucaoSituacaoAnterior">
																		<font color="#ff0000" >${devolucaoHistorico.devolucaoSituacaoAnterior.descricaoAbreviado}</font>
																	</logic:present>
																	&nbsp;	
																</td>
																<td width="13.5%">
																	<logic:present name="devolucaoHistorico" property="devolucaoSituacaoAtual">
																		<font color="#ff0000" >${devolucaoHistorico.devolucaoSituacaoAtual.descricaoAbreviado}</font>
																	</logic:present>
																	&nbsp;
																</td>
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
					
				<tr>
					<td colspan="4" height="10"></td>
				</tr>
				<tr>
					<td colspan="4">
					<table width="100%" align="center" bgcolor="#99CCFF" border="0"
						cellpadding="0" cellspacing="0" class="fontePequena">
						<tr bgcolor="#79bbfd" height="20">
							<td align="center">
								<strong>Devoluções dos Débitos a Cobrar</strong>
							</td>
						</tr>
						<%int contador = 1;%>
						<tr bgcolor="#cbe5fe">
							<td width="100%" align="center">
							<table width="100%" bgcolor="#90c7fc" class="fontePequena">
								<tr bordercolor="#000000">
									<td width="19%" bgcolor="#90c7fc" align="center" rowspan="2"><strong>Tipo do Débito</strong></td>
									<td width="17%" bgcolor="#90c7fc" align="center" rowspan="2"><strong>Valor a Ser Cobrado</strong></td>
									<td width="17%" bgcolor="#90c7fc" align="center" rowspan="2"><strong>Valor da Devol.</strong></td>
									<td width="15%" bgcolor="#90c7fc" align="center" rowspan="2"><strong>Data da Devol.</strong></td>
									<td width="32%" bgcolor="#90c7fc" align="center" colspan="2"><strong>Situação</strong></td>
								</tr>
								<tr>
									<td width="16%" bgcolor="#cbe5fe" align="center"><strong>Anterior</strong></td>
									<td width="16%" bgcolor="#cbe5fe" align="center"><strong>Atual</strong></td>
								</tr>

								<%--Esquema de paginação--%>
								<%if ((Integer) session.getAttribute("qtdeDevDebitoACobrar")
									<= ConstantesSistema.NUMERO_MAXIMO_REGISTROS_CONSULTA_DEVOLUCAO) {%>
				
								
								<!-- Devoluções -->
 								<logic:present name="colecaoDevolucaoImovelDebitoACobrar"
									scope="request">
									
									<logic:iterate name="colecaoDevolucaoImovelDebitoACobrar"
										id="devolucao" type="Devolucao">
										<%contador = contador + 1;
											if (contador % 2 == 0) {%>
										<tr bgcolor="#FFFFFF">
											<%} else {

											%>
										<tr bgcolor="#cbe5fe">
											<%}%>
											<td width="19%" align="center">
											<a href="javascript:abrirPopup('exibirConsultarDebitoACobrarAction.do?imovelID=<bean:write name="devolucao" property="guiaDevolucao.debitoACobrarGeral.debitoACobrar.imovel.id" />&debitoID=<bean:write name="devolucao" property="guiaDevolucao.debitoACobrarGeral.id" />', 560, 660);">${devolucao.guiaDevolucao.debitoACobrarGeral.debitoACobrar.debitoTipo.descricao}
											&nbsp;</a>
											</td>
											<td width="17%" align="right">${devolucao.guiaDevolucao.debitoACobrarGeral.debitoACobrar.valorTotalComBonus}&nbsp;</td>
											<td width="17%" align="right">${devolucao.valorDevolucao}&nbsp;</td>
											<td width="15%" align="center"><bean:write name="devolucao" property="dataDevolucao" formatKey="date.format" />&nbsp;</td>
											<td width="16%">${devolucao.devolucaoSituacaoAnterior.descricaoAbreviado}&nbsp;</td>
											<td width="16%">${devolucao.devolucaoSituacaoAtual.descricaoAbreviado}&nbsp;</td>
										</tr>
									</logic:iterate>
								</logic:present>
								
								<!-- Devoluções Histórico-->
 								<logic:present name="colecaoDevolucaoHistoricoImovelDebitoACobrar"
									scope="request">
									
									<logic:iterate name="colecaoDevolucaoHistoricoImovelDebitoACobrar"
										id="devolucaoHistorico" type="DevolucaoHistorico">
										<%contador = contador + 1;
											if (contador % 2 == 0) {%>
										<tr bgcolor="#FFFFFF">
											<%} else {

											%>
										<tr bgcolor="#cbe5fe">
											<%}%>
											<td width="19%" align="center">
											 <a href="javascript:abrirPopup('exibirConsultarDebitoACobrarAction.do?imovelID=<bean:write name="devolucaoHistorico" property="guiaDevolucao.debitoACobrarGeral.debitoACobrar.imovel.id" />&debitoID=<bean:write name="devolucaoHistorico" property="guiaDevolucao.debitoACobrarGeral.id" />', 560, 660);"><font color="#ff0000" >${devolucaoHistorico.guiaDevolucao.debitoACobrarGeral.debitoACobrar.debitoTipo.descricao}</font>&nbsp;</a></td>
											<td width="17%" align="right"><font color="#ff0000" >${devolucaoHistorico.guiaDevolucao.debitoACobrarGeral.debitoACobrar.valorTotalComBonus}</font>&nbsp;</td>
											<td width="17%" align="right"><font color="#ff0000" >${devolucaoHistorico.valorRestanteCobrado}</font>&nbsp;</td>
											<td width="15%" align="center"><font color="#ff0000" ><bean:write name="devolucaoHistorico" property="dataDevolucao" formatKey="date.format" /></font>&nbsp;</td>
											<td width="16%"><font color="#ff0000" >${devolucaoHistorico.devolucaoSituacaoAnterior.descricaoAbreviado}</font>&nbsp;</td>
											<td width="16%"><font color="#ff0000" >${devolucaoHistorico.devolucaoSituacaoAtual.descricaoAbreviado}</font>&nbsp;</td>
										</tr>
									</logic:iterate>
								</logic:present>
								
								
								<%} else {%>
									<tr>
										<td height="190" colspan="6">
											<div style="width: 100%; height: 100%; overflow: auto;">
												<table width="100%">
								
													<!-- Devoluções -->
					 								<logic:present name="colecaoDevolucaoImovelDebitoACobrar"
														scope="request">
														
														<logic:iterate name="colecaoDevolucaoImovelDebitoACobrar"
															id="devolucao" type="Devolucao">
															<%contador = contador + 1;
																if (contador % 2 == 0) {%>
															<tr bgcolor="#FFFFFF">
																<%} else {
					
																%>
															<tr bgcolor="#cbe5fe">
																<%}%>
																<td width="19%" align="center">
																<a href="javascript:abrirPopup('exibirConsultarDebitoACobrarAction.do?imovelID=<bean:write name="devolucao" property="guiaDevolucao.debitoACobrarGeral.debitoACobrar.imovel.id" />&debitoID=<bean:write name="devolucao" property="guiaDevolucao.debitoACobrarGeral.id" />', 560, 660);">${devolucao.guiaDevolucao.debitoACobrarGeral.debitoACobrar.debitoTipo.descricao}
																&nbsp;</a>
																</td>
																<td width="17%" align="right">${devolucao.guiaDevolucao.debitoACobrarGeral.debitoACobrar.valorTotalComBonus}&nbsp;</td>
																<td width="17%" align="right">${devolucao.valorDevolucao}&nbsp;</td>
																<td width="16.8%" align="center"><bean:write name="devolucao" property="dataDevolucao" formatKey="date.format" />&nbsp;</td>
																<td width="16.7%">${devolucao.devolucaoSituacaoAnterior.descricaoAbreviado}&nbsp;</td>
																<td width="13.5%">${devolucao.devolucaoSituacaoAtual.descricaoAbreviado}&nbsp;</td>
															</tr>
														</logic:iterate>
													</logic:present>
													
													<!-- Devoluções Histórico-->
					 								<logic:present name="colecaoDevolucaoHistoricoImovelDebitoACobrar"
														scope="request">
														
														<logic:iterate name="colecaoDevolucaoHistoricoImovelDebitoACobrar"
															id="devolucaoHistorico" type="DevolucaoHistorico">
															<%contador = contador + 1;
																if (contador % 2 == 0) {%>
															<tr bgcolor="#FFFFFF">
																<%} else {
					
																%>
															<tr bgcolor="#cbe5fe">
																<%}%>
																<td width="19%" align="center">
																 <a href="javascript:abrirPopup('exibirConsultarDebitoACobrarAction.do?imovelID=<bean:write name="devolucaoHistorico" property="guiaDevolucao.debitoACobrarGeral.debitoACobrar.imovel.id" />&debitoID=<bean:write name="devolucaoHistorico" property="guiaDevolucao.debitoACobrarGeral.id" />', 560, 660);"><font color="#ff0000" >${devolucaoHistorico.guiaDevolucao.debitoACobrarGeral.debitoACobrar.debitoTipo.descricao}&nbsp;</font></a></td>
																<td width="17%" align="right"><font color="#ff0000" >${devolucaoHistorico.guiaDevolucao.debitoACobrarGeral.debitoACobrar.valorTotalComBonus}</font>&nbsp;</td>
																<td width="17%" align="right"><font color="#ff0000" >${devolucaoHistorico.valorRestanteCobrado}</font>&nbsp;</td>
																<td width="16.8%" align="center"><font color="#ff0000" ><bean:write name="devolucaoHistorico" property="dataDevolucao" formatKey="date.format" /></font>&nbsp;</td>
																<td width="16.7%"><font color="#ff0000" >${devolucaoHistorico.devolucaoSituacaoAnterior.descricaoAbreviado}</font>&nbsp;</td>
																<td width="13.5%"><font color="#ff0000" >${devolucaoHistorico.devolucaoSituacaoAtual.descricaoAbreviado}</font>&nbsp;</td>
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
					
					
				<tr>
					<td colspan="4" height="10"></td>
				</tr>
				
				
				<tr>
					<td colspan="4">
					<table width="100%" align="center" bgcolor="#99CCFF" border="0"
						cellpadding="0" cellspacing="0" class="fontePequena">
						<tr bgcolor="#79bbfd" height="20">
							<td align="center">
								<strong>Devoluções de Valores</strong>
							</td>
						</tr>	
						<%int contado = 1;%>					
						<tr bgcolor="#cbe5fe">
							<td width="100%" align="center">

							<table width="100%" bgcolor="#90c7fc" class="fontePequena">
								<tr bordercolor="#000000">
									<td width="15%" bgcolor="#90c7fc" align="center" rowspan="2"><strong>Cliente</strong></td>
									<td width="20%" bgcolor="#90c7fc" align="center" rowspan="2"><strong>Tipo do Débito</strong></td>
									<td width="11%" bgcolor="#90c7fc" align="center" rowspan="2"><strong>Valor da Guia de Devol.</strong></td>
									<td width="11%" bgcolor="#90c7fc" align="center" rowspan="2"><strong>Valor da Devol.</strong></td>
									<td width="11%" bgcolor="#90c7fc" align="center" rowspan="2"><strong>Data da Devol.</strong></td>
									<td width="32%" bgcolor="#90c7fc" align="center" colspan="2"><strong>Situação</strong></td>
								</tr>
								<tr>
									<td width="16%" bgcolor="#cbe5fe" align="center"><strong>Anterior</strong></td>
									<td width="16%" bgcolor="#cbe5fe" align="center"><strong>Atual</strong></td>
								</tr>
								<%if ((Integer) session.getAttribute("qtdeDevDevolucaoValores")
									<= ConstantesSistema.NUMERO_MAXIMO_REGISTROS_CONSULTA_DEVOLUCAO) {%>
				
								<!-- Devoluções -->
								<logic:present name="colecaoDevolucaoImovelDevolucaoValor"
									scope="request">
									
									<logic:iterate name="colecaoDevolucaoImovelDevolucaoValor"
										id="devolucao" type="Devolucao">
										<%contado = contado + 1;
											if (contado % 2 == 0) {%>
										<tr bgcolor="#FFFFFF">
											<%} else {

											%>
										<tr bgcolor="#cbe5fe">

											<%}%>
											<td width="15%" align="center">${devolucao.cliente.id}&nbsp;</td>
											<td width="20%">${devolucao.debitoTipo.descricao}&nbsp;</td>
											<td width="11%" align="right">${devolucao.guiaDevolucao.valorDevolucao}&nbsp;</td>
											<td width="11%" align="right">${devolucao.valorDevolucao}&nbsp;</td>
											<td width="11%" align="center"><bean:write name="devolucao" property="dataDevolucao" formatKey="date.format" />&nbsp;</td>
											<td width="16%">${devolucao.devolucaoSituacaoAnterior.descricaoAbreviado}&nbsp;</td>
											<td width="16%">${devolucao.devolucaoSituacaoAtual.descricaoAbreviado}&nbsp;</td>
										</tr>
									</logic:iterate>
								</logic:present>
								
								<!-- Devoluções Histórico-->
								<logic:present name="colecaoDevolucaoHistoricoImovelDevolucaoValor"
									scope="request">
									
									<logic:iterate name="colecaoDevolucaoHistoricoImovelDevolucaoValor"
										id="devolucaoHistorico" type="DevolucaoHistorico">
										<%contado = contado + 1;
											if (contado % 2 == 0) {%>
										<tr bgcolor="#FFFFFF">
											<%} else {

											%>
										<tr bgcolor="#cbe5fe">

											<%}%>
											<td width="15%" align="center"><font color="#ff0000" >${devolucaoHistorico.cliente.id}</font>&nbsp;</td>
											<td width="20%"><font color="#ff0000" >${devolucaoHistorico.debitoTipo.descricao}</font>&nbsp;</td>
											<td width="11%" align="right"><font color="#ff0000" >${devolucaoHistorico.guiaDevolucao.valorDevolucao}</font>&nbsp;</td>
											<td width="11%" align="right"><font color="#ff0000" >${devolucaoHistorico.valorDevolucao}</font>&nbsp;</td>
											<td width="11%" align="center"><font color="#ff0000" ><bean:write name="devolucaoHistorico" property="dataDevolucao" formatKey="date.format" /></font>&nbsp;</td>
											<td width="16%"><font color="#ff0000" >${devolucaoHistorico.devolucaoSituacaoAnterior.descricaoAbreviado}</font>&nbsp;</td>
											<td width="16%"><font color="#ff0000" >${devolucaoHistorico.devolucaoSituacaoAtual.descricaoAbreviado}</font>&nbsp;</td>
										</tr>
									</logic:iterate>
								</logic:present>
								
								
								<%} else {%>
									<tr>
										<td height="190" colspan="7">
											<div style="width: 100%; height: 100%; overflow: auto;">
												<table width="100%">
								
													<!-- Devoluções -->
													<logic:present name="colecaoDevolucaoImovelDevolucaoValor"
														scope="request">
														
														<logic:iterate name="colecaoDevolucaoImovelDevolucaoValor"
															id="devolucao" type="Devolucao">
															<%contado = contado + 1;
																if (contado % 2 == 0) {%>
															<tr bgcolor="#FFFFFF">
																<%} else {
					
																%>
															<tr bgcolor="#cbe5fe">
					
																<%}%>
																<td width="15%" align="center">${devolucao.cliente.id}&nbsp;</td>
																<td width="20%">${devolucao.debitoTipo.descricao}&nbsp;</td>
																<td width="11%" align="right">${devolucao.guiaDevolucao.valorDevolucao}&nbsp;</td>
																<td width="11%" align="right">${devolucao.valorDevolucao}&nbsp;</td>
																<td width="12.8%" align="center"><bean:write name="devolucao" property="dataDevolucao" formatKey="date.format" />&nbsp;</td>
																<td width="16.7%">${devolucao.devolucaoSituacaoAnterior.descricaoAbreviado}&nbsp;</td>
																<td width="13.5%">${devolucao.devolucaoSituacaoAtual.descricaoAbreviado}&nbsp;</td>
															</tr>
														</logic:iterate>
													</logic:present>
													
													<!-- Devoluções Histórico-->
													<logic:present name="colecaoDevolucaoHistoricoImovelDevolucaoValor"
														scope="request">
														
														<logic:iterate name="colecaoDevolucaoHistoricoImovelDevolucaoValor"
															id="devolucaoHistorico" type="DevolucaoHistorico">
															<%contado = contado + 1;
																if (contado % 2 == 0) {%>
															<tr bgcolor="#FFFFFF">
																<%} else {
					
																%>
															<tr bgcolor="#cbe5fe">
					
																<%}%>
																<td width="15%" align="center"><font color="#ff0000" >${devolucaoHistorico.cliente.id}</font>&nbsp;</td>
																<td width="20%"><font color="#ff0000" >${devolucaoHistorico.debitoTipo.descricao}</font>&nbsp;</td>
																<td width="11%" align="right"><font color="#ff0000" >${devolucaoHistorico.guiaDevolucao.valorDevolucao}</font>&nbsp;</td>
																<td width="11%" align="right"><font color="#ff0000" >${devolucaoHistorico.valorDevolucao}</font>&nbsp;</td>
																<td width="12.8%" align="center"><font color="#ff0000" ><bean:write name="devolucaoHistorico" property="dataDevolucao" formatKey="date.format" /></font>&nbsp;</td>
																<td width="16.7%"><font color="#ff0000" >${devolucaoHistorico.devolucaoSituacaoAnterior.descricaoAbreviado}</font>&nbsp;</td>
																<td width="13.5%"><font color="#ff0000" >${devolucaoHistorico.devolucaoSituacaoAtual.descricaoAbreviado}</font>&nbsp;</td>
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
				<tr>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td><font color="#FF0000"> <input type="button"
						name="ButtonCancelar" class="bottonRightCol" value="Cancelar"
						onClick="javascript:window.location.href='/gsan/telaPrincipal.do'">
					<input type="button" name="ButtonReset" class="bottonRightCol"
						value="Voltar Filtro" onclick="javascript:history.back();"> </font>
					</td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
	<%@ include file="/jsp/util/rodape.jsp"%>
</body>
</html:form>
</html:html>
