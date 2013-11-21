<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan"%>

<%@ page import="gcom.util.Util"%>
<%@ page import="java.math.BigDecimal"%>
<%@ page import="gcom.cobranca.bean.ContaValoresHelper"%>
<%@ page import="java.util.Collection" isELIgnored="false"%>
<%@ page import="gcom.faturamento.credito.CreditoARealizar"%>
<%@ page import="gcom.arrecadacao.pagamento.Pagamento"%>
<%@ page import="gcom.faturamento.conta.ContaGeral"%>
<%@ page import="gcom.faturamento.conta.Conta"%>
<%@ page import="gcom.faturamento.conta.ContaHistorico"%>
<%@ page import="gcom.faturamento.bean.CreditosHelper"%>


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
	src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>

<html:javascript staticJavascript="false"
	formName="FiltrarRegistroAtendimentoDevolucaoValoresActionForm" />

<SCRIPT LANGUAGE="JavaScript">
<!--

	function validarForm(form){
		if (validateFiltrarRegistroAtendimentoDevolucaoValoresActionForm(form)){
			submeterFormPadrao(form);
		}
	}
	
	function debitosCreditosSelecionados(form,urlTransferencia,complementoUrl){
	
	    retorno = false;
	
		for(indice = 0; indice < form.elements.length; indice++){
			
			if (form.elements[indice].type == "checkbox" && form.elements[indice].checked == true) {
				retorno = true;
				break;
			}
			
		}
		
		if (!retorno){
			alert('Não existem débitos/créditos selecionados.'); 
		}
		else{
		
			var idsConta = obterValorCheckboxMarcadoPorNome("conta");
			var idsPagamentos = obterValorCheckboxMarcadoPorNome("pagamento");
			
			var concatenador = false;
			if (idsConta != null && idsConta.length > 0){
				urlTransferencia = urlTransferencia + "conta=" + idsConta;
				concatenador = true;
				
			}
			if (idsPagamentos != null && idsPagamentos.length > 0){
				if (concatenador){
					urlTransferencia = urlTransferencia + "&pagamento=" + idsPagamentos;
				}
				else{
					urlTransferencia = urlTransferencia + "pagamento=" + idsPagamentos;
					concatenador = true;
				}
			}
			
			urlTransferencia = urlTransferencia + complementoUrl;
			form.action = urlTransferencia;
			
			submeterFormPadrao(form);
		}
	}
	
	
	
	
	function chamarPopup(url, tipo, objeto, codigoObjeto, altura, largura, msg,objetoRelacionado){
		if(objetoRelacionado.disabled != true){
			if (objeto == null || codigoObjeto == null){
				abrirPopup(url + "?" + "tipo=" + tipo, altura, largura);
			} else{
				if (codigoObjeto.length < 1 || isNaN(codigoObjeto)){
					alert(msg);
				} else{
					abrirPopup(url + "?" + "tipo=" + tipo + "&" + objeto + "=" + codigoObjeto + "&caminhoRetornoTelaPesquisa=" + tipo, altura, largura);
				}
			}
		}
	}

	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
	    var form = document.forms[0];
	    
	   	    
	    if (tipoConsulta == 'imovel') {

		    form.idImovel.value = codigoRegistro;
		    form.inscricaoImovel.value = descricaoRegistro;
		    form.inscricaoImovel.style.color = "#000000";
	    	reloadImovel();
	    }
	}
		
	function reloadImovel() {
		var form = document.forms[0];
		form.action = "/gsan/exibirEfetuarDevolucaoValoresPagosDuplicidadeAction.do?menu=sim";
		submeterFormPadrao(form);
	}
  	
  	function limparImovel() {

		reloadImovel();
  	}
  
  	function limpar(){
  		limparImovel();
  	} 	
  	
  	
	String.prototype.trim = function() {
    	return this.replace(/^\s*/, "").replace(/\s*$/, "");
	}
  	
  	function validaCheck(){
     	validaCheckConta();

  	}
  	
  	
  	function validaCheckConta(){
    	var form = document.forms[0];  	
  	
  		var idContas = form.idsConta.value;
		myString = new String(idContas);
		splitString = myString.split(",");
		
		for (i=0; i< splitString.length; i++) {
			chave  = splitString[i];
			for(indice = 0; indice < form.elements.length; indice++){
				if (form.elements[indice].type == "checkbox" && 
					form.elements[indice].name == 'conta' && 
					form.elements[indice].value.trim() == chave.trim()) {

					form.elements[indice].checked = true;
				}
			}
		}
  	}

	function facilitador(objeto,nome){

		if (objeto.value == "0" || objeto.id == undefined){
	
			objeto.value = "1";
			marcarTodosExtrato(nome);
				
		} else{
				
			objeto.value = "0";
			desmarcarTodosExtrato(nome);
		}
	}
		
	//Ativa todos os elementos do tipo checkbox do formul?rio existente
	function marcarTodosExtrato(nome){
		
		var objetoTotalPagamentoSelecionado = document.getElementById("totalPagamentoSelecionado");
		objetoTotalPagamentoSelecionado.innerHTML = "0,00";
		
		var objetoTotalCreditoAbatido = document.getElementById("totalCreditoAbatido");
		objetoTotalCreditoAbatido.innerHTML = "0,00";
		
		for (var i=0;i < document.forms[0].elements.length;i++){
			var elemento = document.forms[0].elements[i];
			if (elemento.type == "checkbox" && elemento.name == nome){
				
				elemento.checked = true;
				
			}
		}
		
		totalizarDebitosSelecionados();
	}
	
	//Desativa todos os elementos do tipo checkbox do formul?rio existente
	function desmarcarTodosExtrato(nome) {
			
		for (var i=0;i < document.forms[0].elements.length;i++){
			var elemento = document.forms[0].elements[i];
			if (elemento.type == "checkbox" && elemento.name == nome){
				
				if (elemento.checked == true){
					elemento.checked = false;
					totalizarDebito(elemento);
				}
			}
		}
	}

	


function calcular(){
	var form = document.forms[0];
	
	debitosCreditosSelecionados(form,'/gsan/exibirEfetuarDevolucaoValoresPagosDuplicidadeAction.do?','&calcular=sim&reloadPage=1');

}

function totalizarDebitosSelecionados(){
	
	form = document.forms[0];
	
	validaCheck();
	
	for(indice = 0; indice < form.elements.length; indice++){
		
		if ((form.elements[indice].type == "checkbox" && form.elements[indice].checked == true)) {
			
			totalizarDebito(form.elements[indice]);
		}
	}
}

function totalizarDebito(objeto){
	form = document.forms[0];
	var objetoTotalPagamentoSelecionado = document.getElementById("totalPagamentoSelecionado");
	var objetoTotalCreditoAbatido = document.getElementById("totalCreditoAbatido");
	
	//VALOR TOTAL PAGAMENTO
	var valorTotalSelecionado = objetoTotalPagamentoSelecionado.innerHTML;
	valorTotalSelecionado = valorTotalSelecionado.replace(".","");
	valorTotalSelecionado = valorTotalSelecionado.replace(",",".");

	//VALOR TOTAL CONTA 
	var valorTotalCreditoAbatido = objetoTotalCreditoAbatido.innerHTML;
	valorTotalCreditoAbatido = valorTotalCreditoAbatido.replace(".","");
	valorTotalCreditoAbatido = valorTotalCreditoAbatido.replace(",",".");

	//VALOR SELECIONADO
	var valorSelecionado =  objeto.alt;
	valorSelecionado = valorSelecionado.replace(".","");
	valorSelecionado = valorSelecionado.replace(",",".");
	
	if(objeto.name == 'conta'){
		if (objeto.checked == true){
			valorTotalCreditoAbatido = (valorTotalCreditoAbatido * 1) + (valorSelecionado * 1);
		}else{
			valorTotalCreditoAbatido = (valorTotalCreditoAbatido * 1) - (valorSelecionado * 1); 
		}
		
	}else if(objeto.name == 'pagamento'){
		if (objeto.checked == true){
			valorTotalSelecionado = (valorTotalSelecionado * 1) + (valorSelecionado * 1);
		}else{
			valorTotalSelecionado = (valorTotalSelecionado * 1) - (valorSelecionado * 1); 
		}
	}
	
	objetoTotalCreditoAbatido.innerHTML = formatarValorMonetario(valorTotalCreditoAbatido);
	objetoTotalPagamentoSelecionado.innerHTML = formatarValorMonetario(valorTotalSelecionado);

	form.totalCreditoAbatido.value = formatarValorMonetario(valorTotalCreditoAbatido);
	form.totalPagamentoSelecionado.value = formatarValorMonetario(valorTotalSelecionado);
}


	
	function transferir() {
		var form = document.forms[0];
		form.action = "/gsan/transferirDevolucaoValoresPagosDuplicidadeAction.do";
		submeterFormPadrao(form);
	}
  	

//-->
</SCRIPT>

</head>

<body leftmargin="5" topmargin="5"
	onload=";validaCheck();">

<html:form action="/transferirDevolucaoValoresPagosDuplicidadeAction"
	method="post">

	<html:hidden property="idsConta" />
	<input type="hidden" name="checkConta" value="0">
	<input type="hidden" name="valorConta" value="0">



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
					<td class="parabg">Devolução de Pagamentos em Duplicidade</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>

			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td><strong>Para retificação da conta, informe os dados abaixo:</strong></td>
				</tr>

			</table>

			<table width="100%" border="0">
				<tr>
					<td><strong>Número do RA:</strong></td>
					<td><html:text property="idRAConsulta" size="50" maxlength="50"
						readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" /></td>
				</tr>

				<tr>
					<td><strong>Imóvel:</strong></td>
					<td><html:text property="idImovelSelecionado" size="50"
						maxlength="50" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" /></td>
				</tr>

				<tr>
					<td><strong>Cliente:</strong></td>
					<td><html:text property="nomeClienteUsuarioImovelSelecionado"
						size="50" maxlength="50" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" /></td>
				</tr>
				
			</table>

			<table width="100%" cellpadding="0" cellspacing="0">
				<tr>
					<td HEIGHT="5"></td>
				</tr>
				<tr>
					<td bgcolor="#000000" height="1"></td>
				</tr>
			</table>
			<!-- Tabela de pagamentos em duplicidade --> 
			<table width="100%" border="0">
				<tr>
					<td>

					<table width="100%" cellpadding="0" cellspacing="0">
						<tr>
							<td><%BigDecimal valorTotalPagamento = new BigDecimal("0.00");%>
							<table width="100%" cellpadding="0" cellspacing="0">
								<tr bgcolor="#79bbfd">
									<td colspan="6" height="20"><strong>Pagamentos em Duplicidade</strong></td>
								</tr>
								<tr bgcolor="#90c7fc">

									<td width="5%" height="20"><strong>Marcar</strong></td>
									<td width="15%">
									<div align="center"><strong>Mês/Ano</strong></div>
									</td>
									<td width="20%">
									<div align="center"><strong>Vencimento</strong></div>
									</td>
									<td width="20%">
									<div align="center"><strong>Valor</strong></div>
									</td>
									<td width="20%">
									<div align="center"><strong>Data Pagamento</strong></div>
									</td>
									<td width="20%">
									<div align="center"><strong>Valor Pago</strong></div>
									</td>

								</tr>
							</table>

							</td>
						</tr>
					<logic:present name="colecaoPagamento">

							<logic:notEmpty name="colecaoPagamento">

								<tr>
									<td><%String cor = "#cbe5fe";%>
									<%if (((Collection) session.getAttribute("colecaoPagamento")).size() >= 4) {%>
									<div style="width: 100%; height: 100; overflow: auto;">
									<%}%>

									<table width="100%" align="center" bgcolor="#90c7fc">

										<logic:iterate name="colecaoPagamento" id="pagamento" type="Pagamento">

											<%valorTotalPagamento = valorTotalPagamento.add(pagamento.getValorPagamento());%>

											<%if (cor.equalsIgnoreCase("#cbe5fe")) {
											cor = "#FFFFFF";%>
											<tr bgcolor="#FFFFFF">
												<%} else {
											cor = "#cbe5fe";%>
											<tr bgcolor="#cbe5fe">
												<%}%>


												<td align="center" width="5%"><INPUT TYPE="checkbox"
													NAME="pagamento"
													value="<%="" + pagamento.getId().intValue() %>"
													alt="<%="" + Util.formatarMoedaReal(pagamento.getValorPagamento()).trim()%>"
													onclick="totalizarDebito(this);"></td>

												<logic:notEmpty name="pagamento" property="contaGeral.conta">

													<td width="15%" align="center"><font color="#000000"> <a
														href="javascript:abrirPopup('exibirConsultarContaAction.do?contaID=<%="" + pagamento.getContaGeral().getConta().getId() %>&tipoConsulta=conta', 600, 800);">
													<%="" + Util.formatarMesAnoReferencia(pagamento.getContaGeral().getConta().getReferencia())%>
													</a> </font></td>
													
													<td width="20%">
													<div align="center">
														<span style="color: #000000;"> 
														<%="" + Util.formatarData(pagamento.getContaGeral().getConta().getDataVencimentoConta())%></span></div>
													</td>
													
													<td width="20%">
													<div align="right"><span style="color: #000000;"> <%=""
													+ Util.formatarMoedaReal(new BigDecimal(pagamento.getContaGeral().getConta().getValorTotalConta())).trim()%>
													</span></div>
													</td>

												</logic:notEmpty>
												
												<logic:notEmpty name="pagamento" property="contaGeral.contaHistorico">

													<td width="15%" align="center"><font color="#000000"> <a
														href="javascript:abrirPopup('exibirConsultarContaAction.do?contaID=<%="" + pagamento.getContaGeral().getContaHistorico().getId() %>&tipoConsulta=contaHistorico', 600, 800);">
													<%="" + Util.formatarMesAnoReferencia(pagamento.getContaGeral().getContaHistorico().getAnoMesReferenciaConta())%>
													</a> </font></td>
													
													<td width="20%">
													<div align="center">
														<span style="color: #000000;"> 
														<%="" + Util.formatarData(pagamento.getContaGeral().getContaHistorico().getDataVencimentoConta())%></span></div>
													</td>
													
													<td width="20%">
													<div align="right"><span style="color: #000000;"> <%=""
													+ Util.formatarMoedaReal(pagamento.getContaGeral().getContaHistorico().getValorTotal()).trim()%>
													</span></div>
													</td>

												</logic:notEmpty>
										
												<td width="20%">
													<div align="right"><span style="color: #000000;"> <%=""
													+ Util.formatarData(pagamento.getDataPagamento()) %>
													</span></div>
												</td>
													
												<td width="20%">
													<div align="right"><span style="color: #000000;"> <%=""
													+ Util.formatarMoedaReal(pagamento.getValorPagamento()).trim()%>
													</span></div>
												</td>
											

											</tr>

										</logic:iterate>

										<%if (cor.equalsIgnoreCase("#cbe5fe")) {
										cor = "#FFFFFF";%>
										<tr bgcolor="#FFFFFF">
											<%} else {
										cor = "#cbe5fe";%>
										<tr bgcolor="#cbe5fe">
											<%}%>

											<td width="5%" height="20"></td>
											<td width="15%">
											<div align="center"><strong>Total:</strong></div>
											</td>
											<td width="20%"></td>
											<td width="20%"></td>
											<td width="20%">
											<div align="right"><strong> </strong></div>
											</td>
											<td width="20%"><div align="right"><strong> <%=""+ Util.formatarMoedaReal(valorTotalPagamento).trim()%></strong></div></td>

										</tr>

									</table>
								<%if (((Collection) session.getAttribute("colecaoPagamento")).size() >= 4) {%>
								</div>
								<%}%>
										

									</td>
								</tr>

							</logic:notEmpty>

						</logic:present>

					</table>

					</td>
				</tr>
			</table>
			<!-- Tabela de pagamentos em duplicidade -->
			
				<table width="100%" border="0">
				<tr>
					<td HEIGHT="20" align="right" ><strong>Total de Crédito:</strong></td>
					<td>
					<div align="right" style="font-size: 12"><strong><span
						id="totalPagamentoSelecionado">0,00</span></strong></div>
					</td>

				</tr>
			
			</table>
			

			<table width="100%" border="0">
				<tr>
					<td HEIGHT="10"></td>
				</tr>
			</table>

			<!-- Tabela de CONTAS a pagar-->
			<table width="100%" border="0">
				<tr>
					<td>

					<table width="100%" cellpadding="0" cellspacing="0">
						<tr>
							<td><%BigDecimal valorTotalConta = new BigDecimal("0.00");%>
							<table width="100%" cellpadding="0" cellspacing="0">
								<tr bgcolor="#79bbfd">
									<td colspan="5" height="20"><strong>Contas</strong></td>
								</tr>
								<tr bgcolor="#90c7fc">

									<td width="5%" height="20"><strong>Marcar</strong></td>
									<td width="20%">
									<div align="center"><strong>Mês/Ano</strong></div>
									</td>
									<td width="25%">
									<div align="center"><strong>Vencimento</strong></div>
									</td>
									<td width="25%">
									<div align="center"><strong>Valor</strong></div>
									</td>
									<td width="25%">
									<div align="center"><strong>Situação</strong></div>
									</td>

								</tr>
							</table>

							</td>
						</tr>

						<logic:present name="colecaoConta">

							<logic:notEmpty name="colecaoConta">

								<tr>
									<td><%String cor = "#cbe5fe";%>
									<%if (((Collection) session.getAttribute("colecaoConta")).size() >= 4) {%>
									<div style="width: 100%; height: 100; overflow: auto;">
									<%}%>
										<table width="100%" align="center" bgcolor="#90c7fc">

										<logic:iterate name="colecaoConta" id="conta" type="ContaValoresHelper">

											<%valorTotalConta = valorTotalConta.add(conta.getValorTotalConta());%>

											<%if (cor.equalsIgnoreCase("#cbe5fe")) {
											cor = "#FFFFFF";%>
											<tr bgcolor="#FFFFFF">
												<%} else {
											cor = "#cbe5fe";%>
											<tr bgcolor="#cbe5fe">
												<%}%>


												<td align="center" width="5%"><INPUT TYPE="checkbox" NAME="conta"
													value="<%="" + conta.getConta().getId().intValue() %>"
													alt="<%="" + Util.formatarMoedaReal(conta.getValorTotalConta()).trim()%>"
													onclick="totalizarDebito(this);"></td>

													<td width="20%" align="center"><font color="#000000"> <a
														href="javascript:abrirPopup('exibirConsultarContaAction.do?contaID=<%="" + conta.getConta().getId() %>&tipoConsulta=conta', 600, 800);">
													<%=""+ Util.formatarMesAnoReferencia(conta.getConta().getReferencia())%>
													</a> </font></td>
													
													<td width="25%">
													<div align="center">
													<logic:present name="conta"
														property="conta.dataVencimentoConta">
														<span style="color: #000000;"> <%="" + Util.formatarData(conta.getConta().getDataVencimentoConta())%></span>
													</logic:present> 
													<logic:notPresent name="conta" property="conta.dataVencimentoConta">
														&nbsp;
													</logic:notPresent></div>
													</td>
													
													<td width="25%">
													<div align="right"><span style="color: #000000;"> <%=""
													+ Util.formatarMoedaReal(new BigDecimal(conta.getConta().getValorTotalConta())).trim()%>
													</span></div>
													</td>

													<td width="25%">
													<div align="center">
													<logic:present name="conta" property="conta.debitoCreditoSituacaoAtual">
														<font color="#000000"> <bean:write name="conta" property="conta.debitoCreditoSituacaoAtual.descricaoDebitoCreditoSituacao" /></font>
													</logic:present> 
													<logic:notPresent name="conta" property="conta.debitoCreditoSituacaoAtual">
														&nbsp;
													</logic:notPresent></div>
													</td>

											</tr>

										</logic:iterate>

										<%if (cor.equalsIgnoreCase("#cbe5fe")) {
								cor = "#FFFFFF";%>
										<tr bgcolor="#FFFFFF">
											<%} else {
								cor = "#cbe5fe";%>
										<tr bgcolor="#cbe5fe">
											<%}%>

											<td width="5%" height="20"></td>
											<td width="20%">
											<div align="center"><strong>Total:</strong></div>
											</td>
											<td width="25%"></td>
											<td width="25%">
											<div align="right"><strong> <%=""+ Util.formatarMoedaReal(valorTotalConta).trim()%>
											</strong></div>
											</td>
											<td width="25%">
											<div align="right"><strong> </strong></div>
											</td>
										

										</tr>

									</table>
									<%if (((Collection) session.getAttribute("colecaoConta")).size() >= 4) {%>
									</div>
									<%}%>
								

									</td>
								</tr>

							</logic:notEmpty>

						</logic:present>

					</table>
					</td>
				</tr>
			</table>
			<!-- tabela de Contas a pagar -->
			<table width="100%" border="0">
				<tr>
					<td align="right" HEIGHT="20"><strong>Total do Crédito Abatido:</strong></td>
					<td>
					<div align="right" style="font-size: 12"><strong><span
						id="totalCreditoAbatido">0,00</span></strong></div>
					</td>
					<td align="right"><input type="button" name="" value="Calcular"
						class="bottonRightCol"
						onclick="javascript:calcular();"/></td>
				</tr>
			</table>
			<table width="100%" cellpadding="0" cellspacing="0">
				<tr>
					<td HEIGHT="5"></td>
				</tr>
				<tr>
					<td bgcolor="#000000" height="1"></td>
				</tr>
				<tr>
					<td HEIGHT="5"></td>
				</tr>
			</table>

			<table width="100%" border="0">
				<tr>
					<td><strong>Para retificação da conta, informe os dados abaixo:</strong></td>
				</tr>
			</table>
			<table width="100%" border="0">
				<tr>
					<td HEIGHT="10"></td>
				</tr>
			</table>

			<!-- tabela de Contas a ser retificada -->
			<table width="100%" border="0">
				<tr>
					<td>

					<table width="100%" cellpadding="0" cellspacing="0">
						<tr>
							<td><%BigDecimal valorTotalContaRetificada = new BigDecimal("0.00");%>
							<table width="100%" cellpadding="0" cellspacing="0">
								<tr bgcolor="#79bbfd">
									<td colspan="6" height="20"><strong>Conta a ser Retificada</strong></td>
								</tr>
								<tr bgcolor="#90c7fc">
		
									<td width="15%"><div align="center"><strong>Mês/Ano</strong></div></td>
									<td width="10%"><div align="center"><strong>Vencimento</strong></div></td>
									<td width="15%"><div align="center"><strong>Valor Original</strong></div></td>
									<td width="15%"><div align="center"><strong>Valor Crédito</strong></div></td>
									<td width="15%"><div align="center"><strong>Valor Atual</strong></div></td>
									<td width="30%"><div align="center"><strong>Situação</strong></div></td>

								</tr>
							</table>

							</td>
						</tr>

						<logic:present name="colecaoContaASerRetificada">

							<logic:notEmpty name="colecaoContaASerRetificada">

								<tr>
									<td><%String cor = "#cbe5fe";%>
									<%if (((Collection) session.getAttribute("colecaoContaASerRetificada")).size() >= 4) {%>
									<div style="width: 100%; height: 100; overflow: auto;">
									<%}%>
										<table width="100%" align="center" bgcolor="#90c7fc">

										<logic:iterate name="colecaoContaASerRetificada" id="conta" type="ContaValoresHelper">

											<%valorTotalContaRetificada = valorTotalContaRetificada.add(conta.getValorTotalConta());%>

											<%if (cor.equalsIgnoreCase("#cbe5fe")) {
											cor = "#FFFFFF";%>
											<tr bgcolor="#FFFFFF">
												<%} else {
											cor = "#cbe5fe";%>
											<tr bgcolor="#cbe5fe">
												<%}%>

												<td width="15%" align="center"><font color="#000000"> <a
													href="javascript:abrirPopup('exibirConsultarContaAction.do?contaID=<%="" + conta.getConta().getId() %>&tipoConsulta=conta', 600, 800);">
												<%=""+ Util.formatarMesAnoReferencia(conta.getConta().getReferencia())%>
												</a> </font></td>
												
												<td width="10%">
												<div align="center">
												<logic:present name="conta"
													property="conta.dataVencimentoConta">
													<span style="color: #000000;"> <%="" + Util.formatarData(conta.getConta().getDataVencimentoConta())%></span>
												</logic:present> 
												<logic:notPresent name="conta" property="conta.dataVencimentoConta">
													&nbsp;
												</logic:notPresent></div>
												</td>
												
												<td width="15%">
												<div align="right"><span style="color: #000000;"> <%=""
												+ Util.formatarMoedaReal(new BigDecimal(conta.getConta().getValorTotalConta())).trim()%>
												</span></div>
												</td>
												
												<td width="15%">
												<div align="right"><span style="color: #000000;"> <%=""
												+ Util.formatarMoedaReal(conta.getValorCreditoConta()).trim()%>
												</span></div>
												</td>
												
												<td width="15%">
												<div align="right"><span style="color: #000000;"> <%=""
												+ Util.formatarMoedaReal(conta.getValorAtualConta()).trim()%>
												</span></div>
												</td>

												<td width="30%">
												<div align="center">
												<logic:present name="conta" property="conta.debitoCreditoSituacaoAtual">
													<font color="#000000"> <bean:write name="conta" property="conta.debitoCreditoSituacaoAtual.descricaoDebitoCreditoSituacao" /></font>
												</logic:present> 
												<logic:notPresent name="conta" property="conta.debitoCreditoSituacaoAtual">
													&nbsp;
												</logic:notPresent></div>
												</td>

											</tr>

										</logic:iterate>

										<%if (cor.equalsIgnoreCase("#cbe5fe")) {
								cor = "#FFFFFF";%>
										<tr bgcolor="#FFFFFF">
											<%} else {
								cor = "#cbe5fe";%>
										<tr bgcolor="#cbe5fe">
											<%}%>

											<td width="15%">
											<div align="center"><strong>Total:</strong></div>
											</td>
											<td width="10%"></td>
											<td width="15%">
											<div align="right"><strong> <%=""+ Util.formatarMoedaReal(valorTotalContaRetificada).trim()%>
											</strong></div>
											</td>
											<td width="15%">
											<td width="15%">
											<td width="30%">
											<div align="right"><strong> </strong></div>
											</td>
										

										</tr>

									</table>
									<%if (((Collection) session.getAttribute("colecaoContaASerRetificada")).size() >= 4) {%>
									</div>
									<%}%>
								

									</td>
								</tr>

							</logic:notEmpty>

						</logic:present>

					</table>
					</td>
				</tr>
			</table>
			<!-- tabela de Contas a ser retificada -->

			<table width="100%" cellpadding="0" cellspacing="0">
				<tr>
					<td HEIGHT="5"></td>
				</tr>
			</table>

			<!-- tabela de credito a ser transferido -->
			<table width="100%" cellpadding="0" cellspacing="0">
			<tr>
				<td>
				
					<table width="100%" cellpadding="0" cellspacing="0">
					<tr bgcolor="#79bbfd">
						<td colspan="4" height="20"><strong>Crédito a ser Transferido</strong></td>
					</tr>
					<tr bgcolor="#90c7fc">
				
						<td width="25%"><div align="center"><strong>Tipo do Crédito</strong></div></td>
						<td width="45%"><div align="center"><strong>Origem</strong></div></td>
						<td width="15%"><div align="center"><strong>Valor</strong></div></td>
						<td width="15%"><div align="center"><strong>Referência</strong></div></td>

					</tr>
					</table>

				</td>
			</tr>
			
			
			<%BigDecimal valorTotalCreditoASerTransferido = new BigDecimal("0.00");%>
			
			<logic:present name="colecaoCreditoASerTransferido">
			
			<logic:notEmpty name="colecaoCreditoASerTransferido">

			<tr>
				<td>
										
					<% String cor2 = "#cbe5fe";%>
					<%if (((Collection) session.getAttribute("colecaoCreditoASerTransferido")).size() >= 4) {%>
					<div style="width: 100%; height: 100; overflow: auto;">
					<%}%>
					
					<table width="100%" align="center" bgcolor="#90c7fc">
										
					<logic:iterate name="colecaoCreditoASerTransferido" id="credito" type="CreditosHelper">

						<%valorTotalCreditoASerTransferido = valorTotalCreditoASerTransferido.add(credito.getValorCredito()); %>
						
						<%	if (cor2.equalsIgnoreCase("#cbe5fe")){
							cor2 = "#FFFFFF";%>
							<tr bgcolor="#FFFFFF">
						<%} else{
							cor2 = "#cbe5fe";%>
							<tr bgcolor="#cbe5fe">
						<%}%>

						<td width="25%" align="left">
							<bean:write name="credito" property="tipoCredito" />
						</td>
						
						<td width="45%" align="left">
							<bean:write name="credito" property="origemCredito" />
						</td>
	
						<td width="15%">
							<div align="right">
								<%="" + Util.formatarMoedaReal(credito.getValorCredito()).trim()%>
							</div>
						</td>
						<td width="15%">
						<div align="center">
						
							<logic:present name="credito" property="referenciaCredito">
								<span style="color: #000000;">
									<%=""+ Util.formatarMesAnoReferencia(credito.getReferenciaCredito())%>
								</span>
							</logic:present> 
							
							<logic:notPresent name="credito" property="referenciaCredito">
								&nbsp;
							</logic:notPresent>	
						
						</div>
						</td>
					</tr>
			
					</logic:iterate>
					
						<%if (cor2.equalsIgnoreCase("#cbe5fe")){
							cor2 = "#FFFFFF";%>
							<tr bgcolor="#FFFFFF">
						<%} else{
							cor2 = "#cbe5fe";%>
							<tr bgcolor="#cbe5fe">
						<%}%>

						<td width="25%">
							<div align="center"><strong>Total:</strong></div>
						</td>
						<td width="45%"></td>
									<td width="15%">
							<div align="right">
								<strong>				
								<%="" + Util.formatarMoedaReal(valorTotalCreditoASerTransferido).trim()%>
								</strong>				
							</div>
						</td>
						<td width="15%"></td>

					</tr>
										
					</table>
										
					<%if (((Collection) session.getAttribute("colecaoCreditoASerTransferido")).size() >= 4) {%>
					</div>
					<%}%>
				</td>
			</tr>

			</logic:notEmpty>
			
			</logic:present>

			</table>
			<!-- tabela de crédito a ser transferido -->
			
			<table width="100%" cellpadding="0" cellspacing="0">
				<tr>
					<td HEIGHT="5"></td>
				</tr>
			</table>

			<!-- tabela de credito a realizar -->
			<table width="100%" cellpadding="0" cellspacing="0">
			<tr>
				<td>
				
					<table width="100%" cellpadding="0" cellspacing="0">
					<tr bgcolor="#79bbfd">
						<td colspan="4" height="20"><strong>Crédito a Realizar</strong></td>
					</tr>
					<tr bgcolor="#90c7fc">
				
						<td width="25%">
							<div align="center"><strong>Tipo do Crédito</strong></div>
						</td>
						<td width="45%">
							<div align="center"><strong>Origem</strong></div>
						</td>
						<td width="15%">
							<div align="center"><strong>Valor</strong></div>
						</td>
						<td width="15%">
							<div align="center"><strong>Referência</strong></div>
						</td>

					</tr>
					</table>

				</td>
			</tr>
			
			
			<%BigDecimal valorTotalCredito = new BigDecimal("0.00");%>
			
			<logic:present name="colecaoCreditoARealizar">
			
			<logic:notEmpty name="colecaoCreditoARealizar">

			<tr>
				<td>
										
					<% String cor2 = "#cbe5fe";%>

					<%if (((Collection) session.getAttribute("colecaoCreditoARealizar")).size() >= 4) {%>
					<div style="width: 100%; height: 100; overflow: auto;">
					<%}%>
										
					<table width="100%" align="center" bgcolor="#90c7fc">
										
					<logic:iterate name="colecaoCreditoARealizar" id="credito" type="CreditosHelper">

						<%valorTotalCredito = valorTotalCredito.add(credito.getValorCredito()); %>
						
						<%	if (cor2.equalsIgnoreCase("#cbe5fe")){
							cor2 = "#FFFFFF";%>
							<tr bgcolor="#FFFFFF">
						<%} else{
							cor2 = "#cbe5fe";%>
							<tr bgcolor="#cbe5fe">
						<%}%>
												

						<td width="25%" align="left">
							<bean:write name="credito" property="tipoCredito" />
						</td>
						
						<td width="45%" align="left">
							<bean:write name="credito" property="origemCredito" />
						</td>
	
						<td width="15%">
							<div align="right">
								<%="" + Util.formatarMoedaReal(credito.getValorCredito()).trim()%>
							</div>
						</td>
						<td width="15%">
						<div align="center">
						
							<logic:present name="credito" property="referenciaCredito">
								<span style="color: #000000;">
									<%=""+ Util.formatarMesAnoReferencia(credito.getReferenciaCredito())%>
								</span>
							</logic:present> 
							
							<logic:notPresent name="credito" property="referenciaCredito">
								&nbsp;
							</logic:notPresent>	
						
						</div>
						</td>
					</tr>
			
					</logic:iterate>
					
						<%if (cor2.equalsIgnoreCase("#cbe5fe")){
							cor2 = "#FFFFFF";%>
							<tr bgcolor="#FFFFFF">
						<%} else{
							cor2 = "#cbe5fe";%>
							<tr bgcolor="#cbe5fe">
						<%}%>

						<td width="25%">
							<div align="center"><strong>Total:</strong></div>
						</td>
						<td width="45%"></td>
						<td width="15%">
							<div align="right">
								<strong>				
								<%="" + Util.formatarMoedaReal(valorTotalCredito).trim()%>
								</strong>				
							</div>
						</td>
						<td width="15%"></td>
					</tr>
										
					</table>
										
					<%if (((Collection) session.getAttribute("colecaoCreditoARealizar")).size() >= 4) {%>
					</div>
					<%}%>
					<input type="hidden" name="valorTotalCredito" ID="valorTotalCredito" value="<%="" + valorTotalCredito %>">
				</td>
			</tr>

			</logic:notEmpty>
			
			</logic:present>

			</table>
			<!-- tabela de crédito a realizar -->

			<SCRIPT LANGUAGE="JavaScript">
				<!--
					totalizarDebitosSelecionados();
				//-->
				</SCRIPT>

			<table width="100%" border="0">
				<tr>
					<td HEIGHT="10"></td>
				</tr>
			</table>


			<table width="100%" border="0">
				<tr>
					<td HEIGHT="10"></td>
				</tr>
			</table>


			<table width="100%" border="0">
				<tr>
					<td HEIGHT="10"></td>
				</tr>
			</table>

			<table width="100%" border="0">
				<tr>
					<td><input name="Button" type="button" class="bottonRightCol"
						value="Desfazer" align="left" onclick="javascript:limparImovel();">
					<input name="Button" type="button" class="bottonRightCol"
						value="Cancelar" align="left"
						onclick="javascript:window.location.href='/gsan/telaPrincipal.do'">
						<input name="Button" type="button" class="bottonRightCol"
						value="Voltar" align="left"
						onclick="javascript:window.location.href='/gsan/filtrarRegistroAtendimentoDevolucaoValoresAction.do'">
						
					</td>

					<td align="right">
					
						<logic:equal name="habilitarBotaoTransferir" value="1">
							<input type="button" name="" value="Transferir"
							class="bottonRightCol"
							onclick="javascript:transferir();"
							url="transferirDevolucaoValoresPagosDuplicidadeAction.do"
							 />
						</logic:equal>
						
						<logic:equal name="habilitarBotaoTransferir" value="2">
							<input type="button" name="" value="Transferir"
							class="bottonRightCol"
							onclick="javascript:transferir();"
							url="transferirDevolucaoValoresPagosDuplicidadeAction.do"
							disabled="true" />
						</logic:equal>
						
					</td>
				</tr>

			</table>

			<p>&nbsp;</p>

			</td>
		</tr>
	</table>

	<%@ include file="/jsp/util/rodape.jsp"%>

</html:form>


</body>
</html:html>
