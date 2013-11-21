<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%@page import="gcom.arrecadacao.pagamento.Pagamento"%>
<%@page import="gcom.arrecadacao.pagamento.PagamentoHistorico"%>
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
function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {

   	var form = document.forms[0];

    if (tipoConsulta == 'imovel') {
      form.idImovelPagamentos.value = codigoRegistro;
      form.matriculaImovelPagamentos.value = descricaoRegistro;
      form.matriculaImovelPagamentos.style.color = "#000000";
	  form.action = 'consultarImovelWizardAction.do?action=exibirConsultarImovelPagamentosAction&indicadorNovo=OK'
	  form.submit();
    }
    
}

function limparForm(){
   	var form = document.forms[0];
	form.action = 'consultarImovelWizardAction.do?action=exibirConsultarImovelPagamentosAction&limparForm=OK'
	form.submit();
}

function verificarExibicaoRelatorio() {
	var form = document.forms[0];
	
	if (form.idImovelPagamentos.value.length > 0 && form.matriculaImovelPagamentos.value.length > 0) {
		toggleBox('demodiv',1);
	} else {
		alert('Informe Imóvel');
	}
	
}


function habilitaMatricula() {
	var form = document.forms[0];
	
	if (form.idImovelPagamentos.value != null && form.matriculaImovelPagamentos.value != null && 
		form.matriculaImovelPagamentos.value != "" && form.matriculaImovelPagamentos.value != "IMÓVEL INEXISTENTE"){
	
		form.idImovelPagamentos.disabled = true;
	} else {
		form.idImovelPagamentos.disabled = false;
	}
}

function pesquisarImovel() {
	var form = document.forms[0];
 	
 	if (form.idImovelPagamentos.disabled ) {
 		alert("Para realizar uma pesquisa de imóvel é necessário apagar o imóvel atual.")
	} else {
		abrirPopup('exibirPesquisarImovelAction.do', 400, 800)
	}
}

	
-->
</script>

<style>
.fontePequena {
font-size: 11px;
face: Verdana, Arial, Helvetica, sans-serif;
}

</style>

</head>

<body leftmargin="5" topmargin="5" onload="javascript:habilitaMatricula();setarFoco('idImovelPagamentos')">

<html:form action="/exibirConsultarImovelAction.do"
	name="ConsultarImovelActionForm"
	type="gcom.gui.cadastro.imovel.ConsultarImovelActionForm" method="post"
	onsubmit="return validateConsultarImovelActionForm(this);">

	<jsp:include
		page="/jsp/util/wizard/navegacao_abas_wizard_consulta.jsp?numeroPagina=6" />

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
			<p>&nbsp;</p>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_left.gif" /></td>
					<td class="parabg">&nbsp;</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<table width="100%" border="0" style="fonteReduzida">
				<tr>
					<td colspan="4">
					<table width="100%" align="center" bgcolor="#99CCFF" border="0">
						<tr>
						    <td>
							    <table width="100%" align="center" bgcolor="#99CCFF" border="0">
								    <tr>
									    <td align="left" width="4%">
											<img border="0" width="25" height="25"
												src="<bean:message key="caminho.imagens"/>informacao.gif"
												onmouseover="this.T_BGCOLOR='whitesmoke';this.T_LEFT=true;return escape( '${ConsultarImovelActionForm.hint2}' ); "/>
									    </td>						    						
										<td align="center" width="96%"><strong>Dados do Imóvel<logic:present name="imovelExcluido" scope="request"><font color="#ff0000"> (Excluído)</font></logic:present></strong></td>
									</tr>
								</table>
							</td>
						</tr>
						<tr bgcolor="#cbe5fe">
							<td width="100%" align="center">
							<table width="100%" border="0">
								<tr>
									<td bordercolor="#000000" width="25%"><strong>Im&oacute;vel:<font
										color="#FF0000">*</font></strong></td>
									<td width="75%" colspan="3"><html:text
										property="idImovelPagamentos" maxlength="9" size="9"
										onkeypress="validaEnterComMensagem(event, 'consultarImovelWizardAction.do?action=exibirConsultarImovelPagamentosAction&indicadorNovo=OK','idImovelPagamentos','Im&oacute;vel');return isCampoNumerico(event);"
										/> 
									<a
										href="javascript:pesquisarImovel();">
									<img width="23" height="21"
										src="<bean:message key="caminho.imagens"/>pesquisa.gif"
										border="0" title="Pesquisar Imóvel"/></a> <logic:present
										name="idImovelPagamentosNaoEncontrado" scope="request">
										<html:text property="matriculaImovelPagamentos" size="40"
											readonly="true"
											style="background-color:#EFEFEF; border:0; color: #ff0000" 
											title="Localidade.Setor.Quadra.Lote.Sublote"/>

									</logic:present> <logic:notPresent
										name="idImovelPagamentosNaoEncontrado" scope="request">
										<logic:present name="valorMatriculaImovelPagamentos"
											scope="request">
											<html:text property="matriculaImovelPagamentos"
												size="40" readonly="true"
												style="background-color:#EFEFEF; border:0; color: #000000" 
												title="Localidade.Setor.Quadra.Lote.Sublote"/>
										</logic:present>
										<logic:notPresent name="valorMatriculaImovelPagamentos"
											scope="request">
											<html:text property="matriculaImovelPagamentos"
												size="40" readonly="true"
												style="background-color:#EFEFEF; border:0; color: #000000" 
												title="Localidade.Setor.Quadra.Lote.Sublote"/>
										</logic:notPresent>
									</logic:notPresent> <a href="javascript:limparForm();"> <img
										src="<bean:message key="caminho.imagens"/>limparcampo.gif"
										border="0" title="Apagar" /></a></td>

								</tr>
								<tr>
									<td height="10">
									<div class="style9"><strong>Situação de Água:</strong></div>
									</td>
									<td><html:text property="situacaoAguaPagamentos"
										readonly="true"
										style="background-color:#EFEFEF; border:0; color: #000000"
										size="15" maxlength="15" /></td>
									<td width="90"><strong>Situação de Esgoto:</strong></td>
									<td width="120"><html:text
										property="situacaoEsgotoPagamentos" readonly="true"
										style="background-color:#EFEFEF; border:0; color: #000000"
										size="15" maxlength="15" /></td>
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

									<td bgcolor="#90c7fc" width="12%" align="center" rowspan="2"><strong>Mês/Ano
									Conta</strong></td>
									<td bgcolor="#90c7fc" width="14%" align="center" rowspan="2"><strong>Valor
									da Conta</strong></td>
									<td bgcolor="#90c7fc" width="14%" align="center" rowspan="2"><strong>Valor
									do Pag.</strong></td>
									<td bgcolor="#90c7fc" width="14%" align="center" rowspan="2"><strong>Data
									do Pag.</strong></td>
									<td bgcolor="#90c7fc" width="14%" align="center" rowspan="2"><strong>Arrecadador</strong>
									</td>
									<td bgcolor="#90c7fc" width="32%" align="center" colspan="2"><strong>Situação</strong>
									</td>
								</tr>
								<tr>
									<td width="16%" bgcolor="#cbe5fe" align="center"><strong>Anterior</strong></td>
									<td width="16%" bgcolor="#cbe5fe" align="center"><strong>Atual</strong></td>
								</tr>
								<%if( (session.getAttribute("qtdePagContas") == null)) {%>
										<!--  PAra carregar a tabela vazia -->

								<%}else if( (session.getAttribute("qtdePagContas") != null) && ((Integer) session.getAttribute("qtdePagContas") <= ConstantesSistema.NUMERO_MAXIMO_REGISTROS_CONSULTA_PAGAMENTO)) {%>

								<!-- Pagamento com conta  -->
								<logic:present name="colecaoPagamentosImovelConta"
									scope="session">
									<logic:notEmpty name="colecaoPagamentosImovelConta"
										scope="session">
										<logic:iterate name="colecaoPagamentosImovelConta"
											id="pagamento" type="Pagamento">
											<%cont1 = cont1 + 1;
				if (cont1 % 2 == 0) {%>
											<tr bgcolor="#FFFFFF">
												<%} else {

				%>
											<tr bgcolor="#cbe5fe">
												<%}%>

												<td width="12%" align="center"> 
 												   <logic:notEmpty	name="pagamento" property="contaGeral">
													   <logic:notEmpty name="pagamento" property="contaGeral.conta.id">
														   <logic:notEmpty name="pagamento" property="contaGeral.conta.referencia">
															   <a href="javascript:abrirPopup('exibirConsultarContaAction.do?tipoConsulta=conta&contaSemCodigoBarras=1&contaID=<%="" + pagamento.getContaGeral().getId() %>' , 600, 800);">${pagamento.contaGeral.conta.formatarAnoMesParaMesAno}</a>
  														   </logic:notEmpty>
														   <logic:empty name="pagamento" property="contaGeral.conta.referencia">
																<font color="#ff0000">${pagamento.formatarAnoMesPagamentoParaMesAno}</font>															
														   </logic:empty>	
													   </logic:notEmpty>
												       <logic:empty name="pagamento" property="contaGeral.conta.id">
													         <font color="#ff0000">${pagamento.formatarAnoMesPagamentoParaMesAno}</font>
												       </logic:empty>														
												  </logic:notEmpty>
												  <logic:empty name="pagamento" property="contaGeral">
													<font color="#ff0000">${pagamento.formatarAnoMesPagamentoParaMesAno}</font>
												  </logic:empty>	
												
												</td>
												<td width="14%" align="right"><logic:notEmpty
													name="pagamento" property="contaGeral">
													<logic:notEmpty name="pagamento"
														property="contaGeral.conta.valorTotal">
														<bean:write name="pagamento" property="contaGeral.conta.valorTotal"
															formatKey="money.format" />&nbsp;
													</logic:notEmpty>
												</logic:notEmpty></td>
												<td width="14%" align="right"><bean:write name="pagamento"
													property="valorPagamento" formatKey="money.format" />&nbsp;
												</td>
												<td width="14%" align="center">
													<a href="javascript:abrirPopup('exibirConsultarDadosPagamentoAction.do?idPagamento=<%=""+ pagamento.getId()%>' ,800,600);">
														<bean:write name="pagamento" property="dataPagamento" formatKey="date.format" />
													</a>
												</td>
												<td width="14%" align="center">
												${pagamento.avisoBancario.arrecadador.cliente.nome}&nbsp;
												</td>
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

								<!--  Pagamento com conta em historico -->
								<logic:present name="colecaoPagamentosImovelContaHistorico"
									scope="session">
									<logic:notEmpty name="colecaoPagamentosImovelContaHistorico"
										scope="session">
										<logic:iterate name="colecaoPagamentosImovelContaHistorico"
											id="pagamento" type="Pagamento">
											<%cont1 = cont1 + 1;
				if (cont1 % 2 == 0) {%>
											<tr bgcolor="#FFFFFF">
												<%} else {

				%>
											<tr bgcolor="#cbe5fe">
												<%}%>

												<td width="12%" align="center">
												<logic:notEmpty name="pagamento" property="contaGeral.conta">
														<logic:notEmpty name="pagamento" property="contaGeral.conta.id">
															<logic:notEmpty name="pagamento" property="contaGeral.conta.anoMesReferenciaPagamento">
																<a href="javascript:abrirPopup('exibirConsultarContaAction.do?tipoConsulta=conta&contaID=<%="" + pagamento.getContaGeral().getContaHistorico().getId() %>' , 600, 800);"><font color="#ff0000" > ${pagamento.contaHistorico.formatarAnoMesParaMesAno}</font></a>
															</logic:notEmpty>
														</logic:notEmpty>
												</logic:notEmpty>
												 <logic:present
													name="pagamento"
													property="anoMesReferenciaPagamento">
													<font color="#ff0000">${pagamento.formatarAnoMesPagamentoParaMesAno}</font>
												</logic:present></td>
												<td width="14%" align="right"><logic:notEmpty
													name="pagamento" property="contaGeral">
													<logic:notEmpty
													name="pagamento" property="contaGeral.contaHistorico">
													<logic:notEmpty name="pagamento"
														property="contaGeral.contaHistorico.valorTotal">
														<font color="#ff0000"> <bean:write
															name="pagamento" property="contaGeral.contaHistorico.valorTotal"
															formatKey="money.format" /> </font>&nbsp;
													</logic:notEmpty>
													</logic:notEmpty>
												</logic:notEmpty></td>
												<td width="14%" align="right"><font color="#ff0000"> <bean:write
													name="pagamento" property="valorPagamento"
													formatKey="money.format" /> </font> &nbsp;</td>
												<td width="14%" align="center"><font color="#ff0000"> 
													<a style="color: #ff0000" href="javascript:abrirPopup('exibirConsultarDadosPagamentoAction.do?pagamento=OK&idPagamento=<%=""+ pagamento.getId()%>' ,800,600);">
														<bean:write name="pagamento" property="dataPagamento" formatKey="date.format" />
													</a>
												</td>
													
												<td width="14%" align="center">
													<font color="#ff0000">${pagamento.avisoBancario.arrecadador.cliente.nome}</font>&nbsp;
												</td>
												<td width="16%"><font color="#ff0000">
												${pagamento.pagamentoSituacaoAnterior.descricaoAbreviada}
												</font> &nbsp;</td>
												<td width="16%"><font color="#ff0000">
												${pagamento.pagamentoSituacaoAtual.descricaoAbreviada}
												</font> &nbsp;</td>
											</tr>
										</logic:iterate>
									</logic:notEmpty>
								</logic:present>


								<!-- Pagamento Historico com conta em historico -->

								<logic:present name="colecaoPagamentosHistoricoImovelConta"
									scope="session">
									<logic:notEmpty name="colecaoPagamentosHistoricoImovelConta"
										scope="session">
										<logic:iterate name="colecaoPagamentosHistoricoImovelConta"
											id="pagamentoHistorico" type="PagamentoHistorico">
											<%cont1 = cont1 + 1;
				if (cont1 % 2 == 0) {%>
											<tr bgcolor="#FFFFFF">
												<%} else {

				%>
											<tr bgcolor="#cbe5fe">
												<%}%>

												<td width="12%" align="center"><%--<logic:notEmpty
															name="pagamentoHistorico" property="conta">
															<logic:notEmpty name="pagamentoHistorico" property="conta.id">
																<logic:notEmpty name="pagamentoHistorico"
																	property="conta.referencia">
																	<a
																		href="javascript:abrirPopup('exibirConsultarContaAction.do?tipoConsulta=conta&contaID=<%="" + pagamentoHistorico.getConta().getId() %>' , 600, 800);"><font color="#ff0000" > ${pagamentoHistorico.conta.formatarAnoMesParaMesAno}</font></a>
																</logic:notEmpty>
															</logic:notEmpty>
														</logic:notEmpty>--%> <logic:present
													name="pagamentoHistorico"
													property="anoMesReferenciaPagamento">
													<font color="#ff0000">${pagamentoHistorico.formatarAnoMesReferenciaPagamento}</font>
												</logic:present></td>
												<td width="14%" align="right"><logic:notEmpty
													name="pagamentoHistorico" property="contaGeral">
													<logic:notEmpty
													name="pagamentoHistorico" property="contaGeral.contaHistorico">
													<logic:notEmpty name="pagamentoHistorico"
														property="contaGeral.contaHistorico.valorTotal">
														<font color="#ff0000"> <bean:write
															name="pagamentoHistorico" property="contaGeral.contaHistorico.valorTotal"
															formatKey="money.format" /> </font>&nbsp;
													</logic:notEmpty>
													</logic:notEmpty>
												</logic:notEmpty></td>
												<td width="14%" align="right"><font color="#ff0000"> <bean:write
													name="pagamentoHistorico" property="valorPagamento"
													formatKey="money.format" /> </font> &nbsp;</td>
												<td width="14%" align="center"><font color="#ff0000"> 
													<a style="color: #ff0000" href="javascript:abrirPopup('exibirConsultarDadosPagamentoAction.do?pagamentoHistorico=OK&idPagamento=<%=""+ pagamentoHistorico.getId()%>' ,800,600);">
														<bean:write name="pagamentoHistorico" property="dataPagamento" formatKey="date.format" />
													</a>
												</td>
													
												<td width="14%" align="center">
													<font color="#ff0000">${pagamentoHistorico.avisoBancario.arrecadador.cliente.nome}</font>&nbsp;
												</td>
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
								
								<%}else if( (session.getAttribute("qtdePagContas") != null) && ((Integer) session.getAttribute("qtdePagContas") > ConstantesSistema.NUMERO_MAXIMO_REGISTROS_CONSULTA_PAGAMENTO)) {%>
																<tr>
									<td height="190" colspan="7">
									<div style="width: 100%; height: 100%; overflow: auto;">
									<table width="100%">


										<logic:present name="colecaoPagamentosImovelConta"
											scope="session">
											<logic:notEmpty name="colecaoPagamentosImovelConta"
												scope="session">
												<logic:iterate name="colecaoPagamentosImovelConta"
													id="pagamento" type="Pagamento">
													<%cont1 = cont1 + 1;
				if (cont1 % 2 == 0) {%>
													<tr bgcolor="#FFFFFF">
														<%} else {

				%>
													<tr bgcolor="#cbe5fe">
														<%}%>

														<td width="12%" align="center"> <logic:notEmpty
															name="pagamento" property="contaGeral">
															<logic:notEmpty name="pagamento" property="contaGeral.conta.id">
																<logic:notEmpty name="pagamento"
																	property="contaGeral.conta.referencia">
																	<a
																		href="javascript:abrirPopup('exibirConsultarContaAction.do?tipoConsulta=conta&contaSemCodigoBarras=1&contaID=<%="" + pagamento.getContaGeral().getId() %>' , 600, 800);">${pagamento.contaGeral.conta.formatarAnoMesParaMesAno}</a>
																</logic:notEmpty>
														<logic:empty name="pagamento"
															property="contaGeral.conta.referencia">
																${pagamento.formatarAnoMesPagamentoParaMesAno}															
														</logic:empty>	
																
															</logic:notEmpty>
												<logic:empty
													name="pagamento" property="contaGeral.conta.id">
													${pagamento.formatarAnoMesPagamentoParaMesAno}
												</logic:empty>																
														</logic:notEmpty>
												<logic:empty
													name="pagamento" property="contaGeral">
													${pagamento.formatarAnoMesPagamentoParaMesAno}
												</logic:empty>	
														
														
														</td>
														<td width="13%" align="right"><logic:notEmpty
															name="pagamento" property="contaGeral">
															<logic:notEmpty name="pagamento"
																property="contaGeral.conta.valorTotal">
																<bean:write name="pagamento" property="contaGeral.conta.valorTotal"
																	formatKey="money.format" />&nbsp;
													</logic:notEmpty>
														</logic:notEmpty></td>
														<td width="16%" align="right"><bean:write name="pagamento"
															property="valorPagamento" formatKey="money.format" />&nbsp;
														</td>
														<td width="14%" align="center">
															<a href="javascript:abrirPopup('exibirConsultarDadosPagamentoAction.do?idPagamento=<%=""+ pagamento.getId()%>' ,800,600);">
																<bean:write name="pagamento" property="dataPagamento" formatKey="date.format" />
															</a>
														</td>
														<td width="15%" align="center">
														${pagamento.avisoBancario.arrecadador.cliente.nome}&nbsp;
														</td>
														<td width="17%">
														${pagamento.pagamentoSituacaoAnterior.descricaoAbreviada}&nbsp;
														</td>
														<td width="13.5%">
														${pagamento.pagamentoSituacaoAtual.descricaoAbreviada}&nbsp;
														</td>
													</tr>
												</logic:iterate>
											</logic:notEmpty>
										</logic:present>
										
										<!--  Pagamento com conta em historico  -->
										<logic:present name="colecaoPagamentosImovelContaHistorico"
											scope="session">
											<logic:notEmpty name="colecaoPagamentosImovelContaHistorico"
												scope="session">
												<logic:iterate name="colecaoPagamentosImovelContaHistorico"
													id="pagamento" type="Pagamento">
													<%cont1 = cont1 + 1;
				if (cont1 % 2 == 0) {%>
													<tr bgcolor="#FFFFFF">
														<%} else {

				%>
													<tr bgcolor="#cbe5fe">
														<%}%>

														<td width="12%" align="center"><logic:present
															name="pagamento"
															property="anoMesReferenciaPagamento">
															<font color="#ff0000">${pagamento.formatarAnoMesPagamentoParaMesAno}</font>
														</logic:present></td>
														<td width="13%" align="right"><logic:notEmpty
															name="pagamento" property="contaGeral">
															<logic:notEmpty
															name="pagamento" property="contaGeral.contaHistorico">
															<logic:notEmpty name="pagamento"
																property="contaGeral.contaHistorico.valorTotal">
																<font color="#ff0000"> <bean:write
																	name="pagamento" property="contaGeral.contaHistorico.valorTotal"
																	formatKey="money.format" /> </font>&nbsp;
													</logic:notEmpty>
													</logic:notEmpty>
														</logic:notEmpty></td>
														<td width="16%" align="right"><font color="#ff0000"> <bean:write
															name="pagamento" property="valorPagamento"
															formatKey="money.format" /> </font> &nbsp;</td>
														<td width="14%" align="center"> 
															<a style="color: #ff0000" href="javascript:abrirPopup('exibirConsultarDadosPagamentoAction.do?pagamento=OK&idPagamento=<%=""+ pagamento.getId()%>' ,800,600);">
																<bean:write name="pagamento" property="dataPagamento" formatKey="date.format" />
															</a>
														</td>
														<td width="15%" align="center">
														<font color="#ff0000">${pagamento.avisoBancario.arrecadador.cliente.nome}</font>&nbsp;
														</td>
														<td width="17%"><font color="#ff0000">
														${pagamento.pagamentoSituacaoAnterior.descricaoAbreviada}
														</font> &nbsp;</td>
														<td width="13.5%"><font color="#ff0000">
														${pagamento.pagamentoSituacaoAtual.descricaoAbreviada}
														</font> &nbsp;</td>
													</tr>
												</logic:iterate>
											</logic:notEmpty>
										</logic:present>


										<!-- Pagamento Historico com Conta Historico-->

										<logic:present name="colecaoPagamentosHistoricoImovelConta"
											scope="session">
											<logic:notEmpty name="colecaoPagamentosHistoricoImovelConta"
												scope="session">
												<logic:iterate name="colecaoPagamentosHistoricoImovelConta"
													id="pagamentoHistorico" type="PagamentoHistorico">
													<%cont1 = cont1 + 1;
				if (cont1 % 2 == 0) {%>
													<tr bgcolor="#FFFFFF">
														<%} else {

				%>
													<tr bgcolor="#cbe5fe">
														<%}%>

														<td width="12%" align="center"><logic:present
															name="pagamentoHistorico"
															property="anoMesReferenciaPagamento">
															<font color="#ff0000">${pagamentoHistorico.formatarAnoMesReferenciaPagamento}</font>
														</logic:present></td>
														<td width="13%" align="right"><logic:notEmpty
															name="pagamentoHistorico" property="contaGeral">
															<logic:notEmpty
															name="pagamentoHistorico" property="contaGeral.contaHistorico">
															<logic:notEmpty name="pagamentoHistorico"
																property="contaGeral.contaHistorico.valorTotal">
																<font color="#ff0000"> <bean:write
																	name="pagamentoHistorico" property="contaGeral.contaHistorico.valorTotal"
																	formatKey="money.format" /> </font>&nbsp;
													</logic:notEmpty>
													</logic:notEmpty>
														</logic:notEmpty></td>
														<td width="16%" align="right"><font color="#ff0000"> <bean:write
															name="pagamentoHistorico" property="valorPagamento"
															formatKey="money.format" /> </font> &nbsp;</td>
														<td width="14%" align="center"> 
															<a style="color: #ff0000" href="javascript:abrirPopup('exibirConsultarDadosPagamentoAction.do?pagamentoHistorico=OK&idPagamento=<%=""+ pagamentoHistorico.getId()%>' ,800,600);">
																<bean:write name="pagamentoHistorico" property="dataPagamento" formatKey="date.format" />
															</a>
														</td>
														<td width="15%" align="center">
														<font color="#ff0000">${pagamentoHistorico.avisoBancario.arrecadador.cliente.nome}</font>&nbsp;
														</td>
														<td width="17%"><font color="#ff0000">
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


				<tr>
					<td colspan="4" height="10"></td>
				</tr>






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
									<td width="11%" align="center" rowspan="2"><strong>Tipo do
									Débito</strong></td>
									<td width="11%" align="center" rowspan="2"><strong>Valor da
									Guia de Pagto.</strong></td>
									<td width="11%" align="center" rowspan="2"><strong>Valor do
									Pag.</strong></td>
									<td width="11%" align="center" rowspan="2"><strong>Data do Pag.</strong>
									</td>
									<td  width="14%" align="center" rowspan="2"><strong>Arrecadador</strong>
									</td>
									<td width=30% " align="center" colspan="2"><strong>Situação</strong>
									</td>
								</tr>
								<tr>
									<td width="15%" bgcolor="#cbe5fe" align="center"><strong>Anterior</strong></td>
									<td width="15%" bgcolor="#cbe5fe" align="center"><strong>Atual</strong></td>
								</tr>
								<%if((session.getAttribute("qtdePagGuiaPagamento") == null)) {%>
									<!-- Para carregar a tabela vazia -->

								<%}else if((session.getAttribute("qtdePagGuiaPagamento") != null) && ((Integer) session.getAttribute("qtdePagGuiaPagamento") <= ConstantesSistema.NUMERO_MAXIMO_REGISTROS_CONSULTA_PAGAMENTO)) {%>

								<logic:present name="colecaoPagamentosImovelGuiaPagamento"
									scope="session">
									<logic:notEmpty name="colecaoPagamentosImovelGuiaPagamento"
										scope="session">
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
												<td width="11%" align="center">
													<logic:present name="pagamento" property="guiaPagamento">
														<a 
															href="javascript:abrirPopup('exibirConsultarGuiaPagamentoAction.do?guiaPagamentoId=<%="" + pagamento.getGuiaPagamento().getId() %>')">${pagamento.debitoTipo.descricao}
														</a>&nbsp;
													</logic:present>
													<logic:notPresent name="pagamento" property="guiaPagamento">
														${pagamento.debitoTipo.descricao}													
													</logic:notPresent>			
														
												</td>
												<td width="11%" align="right">
													<logic:notEmpty name="pagamento" property="guiaPagamento">
														<logic:notEmpty name="pagamento" property="guiaPagamento.valorDebito">
															<bean:write name="pagamento" property="guiaPagamento.valorDebito" formatKey="money.format" />&nbsp;
														</logic:notEmpty>
													</logic:notEmpty>
												</td>
												<td width="11%" align="right">
													<bean:write name="pagamento" property="valorPagamento" formatKey="money.format" />&nbsp;
												</td>
												<td width="11%" align="center">
													<a  href="javascript:abrirPopup('exibirConsultarDadosPagamentoAction.do?idPagamento=<%=""+ pagamento.getId()%>' ,800,600);">
														<bean:write name="pagamento" property="dataPagamento" formatKey="date.format" />
													</a>
												</td>	
													
												<td width="14%" align="center">
													${pagamento.avisoBancario.arrecadador.cliente.nome}&nbsp;
												</td>
												<td width="15%">
													${pagamento.pagamentoSituacaoAnterior.descricaoAbreviada}&nbsp;
												</td>
												<td width="15%">
													${pagamento.pagamentoSituacaoAtual.descricaoAbreviada}&nbsp;
												</td>
											</tr>
										</logic:iterate>
									</logic:notEmpty>
								</logic:present>


								<!--  Historico -->

								<logic:present
									name="colecaoPagamentosHistoricoImovelGuiaPagamento"
									scope="session">
									<logic:notEmpty
										name="colecaoPagamentosHistoricoImovelGuiaPagamento"
										scope="session">
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
												${pagamento.cliente.id}&nbsp;</td>
												<td width="11%" align="center"><logic:notEmpty
													name="pagamentoHistorico" property="guiaPagamento">
													<a
														href="javascript:abrirPopup('exibirConsultarGuiaPagamentoAction.do?guiaPagamentoId=<%="" + pagamentoHistorico.getGuiaPagamento().getId() %>')"><font
														color="#ff0000">${pagamentoHistorico.debitoTipo.descricao}</font></a>&nbsp;
														</logic:notEmpty>
												<logic:notPresent
													name="pagamento" property="guiaPagamento">
												<font color="#ff0000">	${pagamentoHistorico.debitoTipo.descricao}</font>													
												</logic:notPresent>																	
														</td>
												<td width="11%" align="right"><logic:notEmpty
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
												<td width="11%" align="right"><font color="#ff0000"> <bean:write
													name="pagamentoHistorico" property="valorPagamento"
													formatKey="money.format" /> </font> &nbsp;</td>
												<td width="11%" align="center">
													<a style="color: #ff0000" href="javascript:abrirPopup('exibirConsultarDadosPagamentoAction.do?pagamentoHistorico=OK&idPagamento=<%=""+ pagamentoHistorico.getId()%>' ,800,600);">
														<bean:write name="pagamentoHistorico" property="dataPagamento" formatKey="date.format" />
													</a>&nbsp;
												</td>
												<td width="14%" align="center">
													<font color="#ff0000">${pagamentoHistorico.avisoBancario.arrecadador.cliente.nome}</font>&nbsp;
												</td>
												
												<td width="15%"><font color="#ff0000">
												${pagamentoHistorico.pagamentoSituacaoAnterior.descricaoAbreviada}
												</font> &nbsp;</td>
												<td width="15%"><font color="#ff0000">
												${pagamentoHistorico.pagamentoSituacaoAtual.descricaoAbreviada}
												</font> &nbsp;</td>
											</tr>
										</logic:iterate>
									</logic:notEmpty>
								</logic:present>



								<%}else if((session.getAttribute("qtdePagGuiaPagamento") != null) && ((Integer) session.getAttribute("qtdePagGuiaPagamento") > ConstantesSistema.NUMERO_MAXIMO_REGISTROS_CONSULTA_PAGAMENTO)) {%>


								<tr>
									<td height="190" colspan="7">
									<div style="width: 100%; height: 100%; overflow: auto;">
									<table width="100%">

										<logic:present name="colecaoPagamentosImovelGuiaPagamento"
											scope="session">
											<logic:notEmpty name="colecaoPagamentosImovelGuiaPagamento"
												scope="session">
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
														<td width="11%" align="center"><logic:present
															name="pagamento" property="guiaPagamento">
															<a
																href="javascript:abrirPopup('exibirConsultarGuiaPagamentoAction.do?guiaPagamentoId=<%="" + pagamento.getGuiaPagamento().getId() %>')">${pagamento.debitoTipo.descricao}</a>&nbsp;
														</logic:present>
																										<logic:notPresent
													name="pagamento" property="guiaPagamento">
													${pagamento.debitoTipo.descricao}													
												</logic:notPresent>			
														</td>
														<td width="11%" align="right"><logic:notEmpty
															name="pagamento" property="guiaPagamento">
															<logic:notEmpty name="pagamento"
																property="guiaPagamento.valorDebito">
																<bean:write name="pagamento"
																	property="guiaPagamento.valorDebito"
																	formatKey="money.format" />&nbsp;
													</logic:notEmpty>
														</logic:notEmpty></td>
														<td width="11%" align="right"><bean:write name="pagamento"
															property="valorPagamento" formatKey="money.format" />&nbsp;
														</td>
														<td width="11%" align="center">
															<a  href="javascript:abrirPopup('exibirConsultarDadosPagamentoAction.do?idPagamento=<%=""+ pagamento.getId()%>' ,800,600);">
																<bean:write name="pagamento" property="dataPagamento" formatKey="date.format" />
															</a>&nbsp;
														</td>
														<td width="14%" align="center">
															${pagamento.avisoBancario.arrecadador.cliente.nome}&nbsp;
														</td>	
														
														<td width="15.5%">
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
											scope="session">
											<logic:notEmpty
												name="colecaoPagamentosHistoricoImovelGuiaPagamento"
												scope="session">
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
														${pagamento.cliente.id}&nbsp;</td>
														<td width="11%" align="center"><logic:notEmpty
															name="pagamentoHistorico" property="guiaPagamento">
															<a
																href="javascript:abrirPopup('exibirConsultarGuiaPagamentoAction.do?guiaPagamentoId=<%="" + pagamentoHistorico.getGuiaPagamento().getId() %>')"><font
																color="#ff0000">${pagamentoHistorico.debitoTipo.descricao}</font></a>&nbsp;
														</logic:notEmpty>
																										<logic:notPresent
													name="pagamento" property="guiaPagamento">
													${pagamentoHistorico.debitoTipo.descricao}													
												</logic:notPresent>			
														</td>
														<td width="11%" align="right"><logic:notEmpty
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
														<td width="11%" align="right"><font color="#ff0000"> <bean:write
															name="pagamentoHistorico" property="valorPagamento"
															formatKey="money.format" /> </font> &nbsp;</td>
														<td width="11%" align="center">
															<a style="color: #ff0000" href="javascript:abrirPopup('exibirConsultarDadosPagamentoAction.do?pagamentoHistorico=OK&idPagamento=<%=""+ pagamentoHistorico.getId()%>' ,800,600);">
																<bean:write name="pagamentoHistorico" property="dataPagamento" formatKey="date.format" />
															</a>
														</td>
															
														<td width="14%" align="center">
															<font color="#ff0000">${pagamentoHistorico.avisoBancario.arrecadador.cliente.nome}</font>&nbsp;
														</td>
														<td width="15.5%"><font color="#ff0000">
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


				<tr>
					<td colspan="4" height="10"></td>
				</tr>


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
									<td width="18%" align="center" rowspan="2"><strong>Tipo do
									Débito</strong></td>
									<td width="16%" align="center" rowspan="2"><strong>Valor a Ser
									Cobrado</strong></td>
									<td width="14%" align="center" rowspan="2"><strong>Valor do
									Pag.</strong></td>
									<td width="14%" align="center" rowspan="2"><strong>Data do Pag.</strong>
									</td>
									<td  width="14%" align="center" rowspan="2"><strong>Arrecadador</strong>
									</td>
									<td width="24%" align="center" colspan="2"><strong>Situação</strong>
									</td>
								</tr>
								<tr>
									<td width="12%" bgcolor="#cbe5fe" align="center"><strong>Anterior</strong>
									</td>
									<td width="12%" bgcolor="#cbe5fe" align="center"><strong>Atual</strong>
									</td>
								</tr>

								<%--Esquema de paginação--%>
								<%if((session.getAttribute("qtdePagDebitoACobrar") == null)) {%>
									<!-- Para carregar a tabela vazia -->

								<%}else if((session.getAttribute("qtdePagDebitoACobrar") != null) && ((Integer) session.getAttribute("qtdePagDebitoACobrar") <= ConstantesSistema.NUMERO_MAXIMO_REGISTROS_CONSULTA_PAGAMENTO)) {%>

								<logic:present name="colecaoPagamentosImovelDebitoACobrar"
									scope="session">
									<logic:iterate name="colecaoPagamentosImovelDebitoACobrar"
										id="pagamento" type="Pagamento">
										<%contador = contador + 1;
				if (contador % 2 == 0) {%>
										<tr bgcolor="#FFFFFF">
											<%} else {%>
										<tr bgcolor="#cbe5fe">
											<%}%>

											<td width="18%" align="center">
											
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
											
											
											<logic:empty name="pagamento" property="debitoACobrarGeral">
												
													${pagamento.debitoACobrarGeral.debitoTipo.descricao}&nbsp;
												
												
											</logic:empty>		
											</td>
											<td width="16%" align="right">
												<logic:notEmpty name="pagamento" property="debitoACobrarGeral">
													<logic:notEmpty name="pagamento" property="debitoACobrarGeral.debitoACobrar">
														<logic:notEmpty name="pagamento" property="debitoACobrarGeral.debitoACobrar.valorDebito">
															<logic:notEmpty name="pagamento" property="debitoACobrarGeral.debitoACobrar.numeroPrestacaoDebitoMenosBonus">
																<logic:notEmpty name="pagamento" property="debitoACobrarGeral.debitoACobrar.numeroPrestacaoCobradas">
																	<bean:write name="pagamento" property="debitoACobrarGeral.debitoACobrar.valorTotalComBonus" formatKey="money.format" />&nbsp;
																</logic:notEmpty>
															</logic:notEmpty>
														</logic:notEmpty>
													</logic:notEmpty>
													
													
													<logic:notEmpty name="pagamento" property="debitoACobrarGeral.debitoACobrarHistorico">
														<logic:notEmpty name="pagamento" property="debitoACobrarGeral.debitoACobrarHistorico.valorDebito">
															<logic:notEmpty name="pagamento" property="debitoACobrarGeral.debitoACobrarHistorico.numeroPrestacaoDebitoMenosBonus">
																<logic:notEmpty name="pagamento" property="debitoACobrarGeral.debitoACobrarHistorico.prestacaoCobradas">
																	<bean:write name="pagamento" property="debitoACobrarGeral.debitoACobrarHistorico.valorTotalComBonus" formatKey="money.format" />&nbsp;
																</logic:notEmpty>
															</logic:notEmpty>
														</logic:notEmpty>
													</logic:notEmpty>
													
												</logic:notEmpty>
											</td>
											<td width="14%" align="right">
												<a  href="javascript:abrirPopup('exibirConsultarDadosPagamentoAction.do?idPagamento=<%=""+ pagamento.getId()%>' ,800,600);">
														<bean:write name="pagamento" property="valorPagamento" formatKey="money.format" />
												</a>&nbsp;
											</td>
											<td width="14%" align="center"><bean:write name="pagamento"
												property="dataPagamento" formatKey="date.format" />&nbsp;
											</td>
											<td width="14%" align="center">
												${pagamento.avisoBancario.arrecadador.cliente.nome}&nbsp;
											</td>
											<td width="12%">
											${pagamento.pagamentoSituacaoAnterior.descricaoAbreviada}&nbsp;
											</td>
											<td width="12%">
											${pagamento.pagamentoSituacaoAtual.descricaoAbreviada}&nbsp;
											</td>
										</tr>
									</logic:iterate>
								</logic:present>


								<!-- Historico -->

								<logic:present
									name="colecaoPagamentosHistoricoImovelDebitoACobrar"
									scope="session">
									<logic:iterate
										name="colecaoPagamentosHistoricoImovelDebitoACobrar"
										id="pagamentoHistorico" type="PagamentoHistorico">

										<%contador = contador + 1;
				if (contador % 2 == 0) {%>
										<tr bgcolor="#FFFFFF">
											<%} else {%>
										<tr bgcolor="#cbe5fe">
											<%}%>

											<td width="18%" align="center">
											
											
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
											
											
											 
											<logic:empty
															name="pagamentoHistorico" property="debitoACobrarGeral">

																<font color="#ff0000">${pagamentoHistorico.debitoTipo.descricao}&nbsp;</font>
											</logic:empty>		
											
											</td>
											<td width="16%" align="right">
												<logic:notEmpty name="pagamentoHistorico" property="debitoACobrarGeral">
													<logic:notEmpty name="pagamentoHistorico" property="debitoACobrarGeral.debitoACobrarHistorico">
															<logic:notEmpty name="pagamentoHistorico" property="debitoACobrarGeral.debitoACobrarHistorico.valorDebito">
																<logic:notEmpty name="pagamentoHistorico" property="debitoACobrarGeral.debitoACobrarHistorico.numeroPrestacaoDebitoMenosBonus">
																	<logic:notEmpty name="pagamentoHistorico" property="debitoACobrarGeral.debitoACobrarHistorico.prestacaoCobradas">
																		<font color="#ff0000"> 
																			<bean:write name="pagamentoHistorico" property="debitoACobrarGeral.debitoACobrarHistorico.valorTotalComBonus" formatKey="money.format" /> 
																		</font>		
																				&nbsp;
																	</logic:notEmpty>
																</logic:notEmpty>
															</logic:notEmpty>
													</logic:notEmpty>
												</logic:notEmpty></td>
											<td width="14%" align="right"><font color="#ff0000"> <bean:write
												name="pagamentoHistorico" property="valorPagamento"
												formatKey="money.format" /> </font> &nbsp;</td>
											<td width="14%" align="center">
												<a style="color: #ff0000" href="javascript:abrirPopup('exibirConsultarDadosPagamentoAction.do?pagamentoHistorico=OK&idPagamento=<%=""+ pagamentoHistorico.getId()%>' ,800,600);">
													<bean:write name="pagamentoHistorico" property="dataPagamento"	formatKey="date.format" />
												</a>
											</td>
											<td width="14%" align="center">
												<font color="#ff0000">${pagamentoHistorico.avisoBancario.arrecadador.cliente.nome}</font>&nbsp;
											</td>
											<td width="12%"><font color="#ff0000">
											${pagamentoHistorico.pagamentoSituacaoAnterior.descricaoAbreviada}
											</font> &nbsp;</td>
											<td width="12%"><font color="#ff0000">
											${pagamentoHistorico.pagamentoSituacaoAtual.descricaoAbreviada}
											</font> &nbsp;</td>
										</tr>
									</logic:iterate>
								</logic:present>

								<%}else if((session.getAttribute("qtdePagDebitoACobrar") != null) && ((Integer) session.getAttribute("qtdePagDebitoACobrar") > ConstantesSistema.NUMERO_MAXIMO_REGISTROS_CONSULTA_PAGAMENTO)) {%>
								<tr>
									<td height="190" colspan="6">
									<div style="width: 100%; height: 100%; overflow: auto;">
									<table width="100%">

										<logic:present name="colecaoPagamentosImovelDebitoACobrar"
											scope="session">
											<logic:iterate name="colecaoPagamentosImovelDebitoACobrar"
												id="pagamento" type="Pagamento">
												<%contador = contador + 1;
				if (contador % 2 == 0) {%>
												<tr bgcolor="#FFFFFF">
													<%} else {%>
												<tr bgcolor="#cbe5fe">
													<%}%>

													<td width="17%" align="center">
													<logic:notEmpty
															name="pagamento" property="debitoACobrarGeral">
													<a
														href="javascript:abrirPopup('exibirConsultarDebitoACobrarAction.do?imovelID=<bean:write name="pagamento" property="debitoACobrarGeral.debitoACobrar.imovel.id" />&debitoID=<bean:write name="pagamento" property="debitoACobrarGeral.id" />', 560, 660);">
													${pagamento.debitoACobrarGeral.debitoACobrar.debitoTipo.descricao}&nbsp;</a>
													</logic:notEmpty>
													
											<logic:empty
															name="pagamento" property="debitoACobrarGeral">
															${pagamento.debitoACobrarGeral.debitoTipo.descricao}&nbsp;
											</logic:empty>				
													
													</td>
													<td width="15%" align="right">
														<logic:notEmpty name="pagamento" property="debitoACobrarGeral">
															<logic:notEmpty name="pagamento" property="debitoACobrarGeral.debitoACobrar">
																<logic:notEmpty name="pagamento" property="debitoACobrarGeral.debitoACobrar.valorDebito">
																	<logic:notEmpty name="pagamento" property="debitoACobrarGeral.debitoACobrar.numeroPrestacaoDebitoMenosBonus">
																		<logic:notEmpty name="pagamento" property="debitoACobrarGeral.debitoACobrar.numeroPrestacaoCobradas">
																			<bean:write name="pagamento" property="debitoACobrarGeral.debitoACobrar.valorTotalComBonus" formatKey="money.format" />&nbsp;
																		</logic:notEmpty>
																	</logic:notEmpty>
																</logic:notEmpty>
															</logic:notEmpty>
															<logic:notEmpty name="pagamento" property="debitoACobrarGeral.debitoACobrarHistorico">
																<logic:notEmpty name="pagamento" property="debitoACobrarGeral.debitoACobrarHistorico.valorDebito">
																	<logic:notEmpty name="pagamento" property="debitoACobrarGeral.debitoACobrarHistorico.numeroPrestacaoDebitoMenosBonus">
																		<logic:notEmpty name="pagamento" property="debitoACobrarGeral.debitoACobrarHistorico.numeroPrestacaoCobradas">
																			<bean:write name="pagamento" property="debitoACobrarGeral.debitoACobrarHistorico.valorTotalComBonus" formatKey="money.format" />&nbsp;
																		</logic:notEmpty>
																	</logic:notEmpty>
																</logic:notEmpty>
															</logic:notEmpty>
														</logic:notEmpty>														
													</td>
													<td width="14%" align="right"><bean:write name="pagamento"
														property="valorPagamento" formatKey="money.format" />&nbsp;
													</td>
													<td width="14%" align="center">
														<a style="color: #ff0000" href="javascript:abrirPopup('exibirConsultarDadosPagamentoAction.do?idPagamento=<%=""+ pagamento.getId()%>' ,800,600);">
															<bean:write name="pagamento" property="dataPagamento"	formatKey="date.format" />
														</a>
													</td>
													<td width="14%" align="center">
														${pagamento.avisoBancario.arrecadador.cliente.nome}&nbsp;
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
											scope="session">
											<logic:iterate
												name="colecaoPagamentosHistoricoImovelDebitoACobrar"
												id="pagamentoHistorico" type="PagamentoHistorico">

												<%contador = contador + 1;
				if (contador % 2 == 0) {%>
												<tr bgcolor="#FFFFFF">
													<%} else {%>
												<tr bgcolor="#cbe5fe">
													<%}%>

													<td width="17%" align="center">
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
													
													<logic:empty
															name="pagamentoHistorico" property="debitoACobrarGeral">
															

															<font color="#ff0000">${pagamentoHistorico.debitoACobrarGeral.debitoTipo.descricao}&nbsp;</font>
													</logic:empty>		
													</td>
													<td width="15%" align="right">
														<logic:notEmpty name="pagamentoHistorico" property="debitoACobrarGeral">
															<logic:notEmpty name="pagamentoHistorico" property="debitoACobrarGeral.debitoACobrarHistorico">
																<logic:notEmpty name="pagamentoHistorico" property="debitoACobrarGeral.debitoACobrarHistorico.valorDebito">
																	<logic:notEmpty name="pagamentoHistorico" property="debitoACobrarGeral.debitoACobrarHistorico.numeroPrestacaoDebitoMenosBonus">
																		<logic:notEmpty name="pagamentoHistorico" property="debitoACobrarGeral.debitoACobrarHistorico.prestacaoCobradas">
																			<font color="#ff0000"> 
																			<bean:write name="pagamentoHistorico" property="debitoACobrarGeral.debitoACobrarHistorico.valorTotalComBonus" formatKey="money.format" /> </font>	&nbsp;
																		</logic:notEmpty>
																	</logic:notEmpty>
																</logic:notEmpty>
															</logic:notEmpty>
														</logic:notEmpty>
														
													</td>
													<td width="14%" align="right"><font color="#ff0000"> <bean:write
														name="pagamentoHistorico" property="valorPagamento"
														formatKey="money.format" /> </font> &nbsp;</td>
																											
													<td width="14%" align="center">
														<a style="color: #ff0000" href="javascript:abrirPopup('exibirConsultarDadosPagamentoAction.do?pagamentoHistorico=OK&idPagamento=<%=""+ pagamentoHistorico.getId()%>' ,800,600);">
															<bean:write name="pagamentoHistorico" property="dataPagamento"	formatKey="date.format" />
														</a>
													</td>
													<td width="14%" align="center">
														<font color="#ff0000">${pagamentoHistorico.avisoBancario.arrecadador.cliente.nome}</font>&nbsp;
													</td>
													<td width="12%"><font color="#ff0000">
													${pagamentoHistorico.pagamentoSituacaoAnterior.descricaoAbreviada}
													</font> &nbsp;</td>
													<td width="10.5%"><font color="#ff0000">
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
				<tr>
				<td align="right">
						  <div align="right">
						   <a href="javascript:verificarExibicaoRelatorio();">
							<img border="0"
								src="<bean:message key="caminho.imagens"/>print.gif"
								title="Imprimir Pagamentos" /> </a>
						  </div>
						</td>
					</tr>
					
					</table>
					<p>&nbsp;</p>
				<table width="100%" border="0">
					<tr>
						<td colspan="2">
						<div align="right"><jsp:include
							page="/jsp/util/wizard/navegacao_botoes_wizard_consulta.jsp?numeroPagina=6" /></div>
						</td>
					</tr>
				</table>
		</td>
	</tr>
</table>
<jsp:include
		page="/jsp/relatorio/escolher_tipo_relatorio.jsp?relatorio=gerarRelatorioPagamentoAction.do" />
<%@ include file="/jsp/util/rodape.jsp"%>
<%@ include file="/jsp/util/tooltip.jsp" %>
</body>
</html:form>
</html:html>
