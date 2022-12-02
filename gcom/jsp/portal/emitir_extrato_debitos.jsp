<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<%@ page import="gcom.cobranca.bean.ContaValoresHelper"%>
<%@ page import="gcom.cobranca.bean.GuiaPagamentoValoresHelper"%>
<%@ page import="gcom.arrecadacao.pagamento.Pagamento"%>
<%@ page import="gcom.faturamento.debito.DebitoACobrar"%>
<%@ page import="gcom.cobranca.bean.DebitoCreditoParcelamentoHelper"%>
<%@ page import="gcom.util.Util"%>
<%@ page import="java.math.BigDecimal"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN">

<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="viewport" content="height=device-height, initial-scale=1.0">

<title>Cosanpa - Loja Virtual</title>

<link href="<bean:message key="caminho.portal.css"/>portal.css" rel="stylesheet">


<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js" ></script>

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>

<html:javascript staticJavascript="false"  formName="EmitirExtratoDebitosActionForm"/>

<SCRIPT LANGUAGE="JavaScript">

	function validarForm(form){
		if (validateDebitoCreditoDadosSelecaoExtratoActionForm(form)){
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
			else if (form.elements[indice].type == "checkbox" && form.elements[indice].checked == false &&
					form.elements[indice].name == 'parcelamento'){
				
				var campoAntecipacao = eval('parc' + form.elements[indice].value);
				
				if (campoAntecipacao.value.length > 0){
					retorno = true;
					break;
				}
			}
			
		}
		
		if (!retorno){
			//alert('Não existem débitos/créditos selecionados para emissão do extrato de débitos.'); 
				alert('Não existem débitos/créditos selecionados.'); 
		}
		else if (validarCamposDinamicos(form)){
		
			var idsConta = obterValorCheckboxMarcadoPorNome("conta");
			var idsContaPreterita = obterValorCheckboxMarcadoPorNome("contaPreterita");
			var idsDebito = obterValorCheckboxMarcadoPorNome("debito");
			var idsCredito = obterValorCheckboxMarcadoPorNome("credito");
			var idsGuiaPagamento = obterValorCheckboxMarcadoPorNome("guiaPagamento");
			var idsParcelamento = obterValorCheckboxMarcadoPorNome("parcelamento");
			var concatenador = false;
			
			if (idsConta != null && idsConta.length > 0){
				urlTransferencia = urlTransferencia + "conta=" + idsConta;
				concatenador = true;
				
			}

			if (idsContaPreterita != null && idsContaPreterita.length > 0){
				if(idsConta != null && idsConta.length > 0){
					urlTransferencia = urlTransferencia + "," + idsContaPreterita;
					concatenador = true;
				}else{
					urlTransferencia = urlTransferencia + "contaPreterita=" + idsContaPreterita;
					concatenador = true;
					}
			}
			
			if (idsDebito != null && idsDebito.length > 0){
				if (concatenador){
					urlTransferencia = urlTransferencia + "&debito=" + idsDebito;
				}
				else{
					urlTransferencia = urlTransferencia + "debito=" + idsDebito;
					concatenador = true;
				}
			}
			if (idsCredito != null && idsCredito.length > 0){
				if (concatenador){
					urlTransferencia = urlTransferencia + "&credito=" + idsCredito;
				}
				else{
					urlTransferencia = urlTransferencia + "credito=" + idsCredito;
					concatenador = true;
				}
			}
			if (idsGuiaPagamento != null && idsGuiaPagamento.length > 0){
				if (concatenador){
					urlTransferencia = urlTransferencia + "&guiaPagamento=" + idsGuiaPagamento;
				}
				else{
					urlTransferencia = urlTransferencia + "guiaPagamento=" + idsGuiaPagamento;
					concatenador = true;
				}
			}
			if (idsParcelamento != null && idsParcelamento > 0){
				if (concatenador){
					urlTransferencia = urlTransferencia + "&parcelamento=" + idsParcelamento;
				}
				else{
					urlTransferencia = urlTransferencia + "parcelamento=" + idsParcelamento;
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
		form.action = "/gsan/extrato-debitos.do?menu=sim";
		submeterFormPadrao(form);
	}
  	
  	function limparImovel() {

    	var form = document.forms[0];

    	form.idImovel.value = "";
		form.inscricaoImovel.value = "";
		form.inscricaoImovel.style.color = "#000000";
		form.nomeClienteUsuarioImovel.value = "";
		form.descricaoLigacaoAguaSituacaoImovel.value = "";
		form.descricaoLigacaoEsgotoSituacaoImovel.value = "";
				
		//form.idImovel.focus();
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
	    validaCheckDebito();
	    validaCheckGuia();
	    validaCheckParcelamento();
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
  	
  	function validaCheckDebito(){
    	var form = document.forms[0];  	
  	
  		var idDebitos = form.idsDebito.value;
		myString = new String(idDebitos);
		splitString = myString.split(",");
		
		for (i=0; i< splitString.length; i++) {
			chave  = splitString[i];
			for(indice = 0; indice < form.elements.length; indice++){
				if (form.elements[indice].type == "checkbox" && 
					form.elements[indice].name == 'debito' && 
					form.elements[indice].value.trim() == chave.trim()) {

					form.elements[indice].checked = true;
				}
			}
		}
  	}
  	
  	function validaCheckGuia(){
    	var form = document.forms[0];  	
  	
  		var idGuias = form.idsGuia.value;
		myString = new String(idGuias);
		splitString = myString.split(",");
		
		for (i=0; i< splitString.length; i++) {
			chave  = splitString[i];
			for(indice = 0; indice < form.elements.length; indice++){
				if (form.elements[indice].type == "checkbox" && 
					form.elements[indice].name == 'guiaPagamento' && 
					form.elements[indice].value.trim() == chave.trim()) {

					form.elements[indice].checked = true;
				}
			}
		}
  	}
  	
  	function validaCheckParcelamento(){
    	var form = document.forms[0];  	
  	
  		var idParcelamentos = form.idsParcelamento.value;
		myString = new String(idParcelamentos);
		splitString = myString.split(",");
		
		for (i=0; i< splitString.length; i++) {
			chave  = splitString[i];
			for(indice = 0; indice < form.elements.length; indice++){
				if (form.elements[indice].type == "checkbox" && 
					form.elements[indice].name == 'parcelamento' && 
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
		var objetoTotalDebitoSelecionado = document.getElementById("totalDebitoSelecionado");
		objetoTotalDebitoSelecionado.innerHTML = "0,00";
		
		var objetoTotalDebitoAtualizadoSelecionado = document.getElementById("totalDebitoAtualizadoSelecionado");
		objetoTotalDebitoAtualizadoSelecionado.innerHTML = "0,00";
		
		for (var i=0;i < document.forms[0].elements.length;i++){
			var elemento = document.forms[0].elements[i];
			if (elemento.type == "checkbox" && elemento.name == nome){
				
				elemento.checked = true;
				controleCampoAntecipacaoParcela(elemento);
				//totalizarDebito(elemento);
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
					controleCampoAntecipacaoParcela(elemento);
					totalizarDebito(elemento);
				}
			}
		}
	}

	
	function controleCampoAntecipacaoParcela(objeto){
	
		if (objeto.checked == true){
			
			if (objeto.name == 'parcelamento'){
				
				var campoAntecipacao = eval('parc' + objeto.value);
				campoAntecipacao.value = "";
				campoAntecipacao.readOnly = true;
			}
		}
		else{
			
			if (objeto.name == 'parcelamento'){
				
				var campoAntecipacao = eval('parc' + objeto.value);
				campoAntecipacao.value = "";
				campoAntecipacao.readOnly = false;
			}
		}
	}
	
	
	function validarCamposDinamicos(form){
 
 		var camposValidos = true;
 
 		for (i=0; i < form.elements.length; i++) {
	    
	    	if (form.elements[i].type == "checkbox" && form.elements[i].checked == false){
    		
				switch (form.elements[i].name){
			
				case "parcelamento":
					
					var campoAntecipacaoParcelamento = eval('parc' + form.elements[i].value);
					
					if (isNaN(campoAntecipacaoParcelamento.value) || campoAntecipacaoParcelamento.value.indexOf('.') != -1){
						alert("Quantidade de Parcelas para antecipação deve conter apenas valores inteiros.");
						campoAntecipacaoParcelamento.focus();
						camposValidos = false;
					}
					else if (!testarCampoValorZero(campoAntecipacaoParcelamento, "Quantidade de Parcelas para antecipação")){
						campoAntecipacaoParcelamento.focus();
						camposValidos = false;
					}
					
				
					break;
					
				default:
					break;
			}	
    	}
    	
    	if (!camposValidos){
    		break;
    	}
    }
    
    return camposValidos;
}	


function calcularDesconto(){
	var form = document.forms[0];
	
	debitosCreditosSelecionados(form,'/gsan/exibirDebitoCreditoDadosSelecaoExtratoAction.do?','&calcularDesconto=sim&reloadPage=1');

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
	var objetoTotalDebitoSelecionado = document.getElementById("totalDebitoSelecionado");

	var objetoTotalDebitoAtualizadoSelecionado = document.getElementById("totalDebitoAtualizadoSelecionado");
	
	//VALOR TOTAL
	var valorTotalSelecionado = objetoTotalDebitoSelecionado.innerHTML;
	valorTotalSelecionado = valorTotalSelecionado.replace(".","");
	valorTotalSelecionado = valorTotalSelecionado.replace(",",".");

	//VALOR TOTAL ACUMULADO
	var valorTotalAtualizadoSelecionado = objetoTotalDebitoAtualizadoSelecionado.innerHTML;
	valorTotalAtualizadoSelecionado = valorTotalAtualizadoSelecionado.replace(".","");
	valorTotalAtualizadoSelecionado = valorTotalAtualizadoSelecionado.replace(",",".");
	
	//VALOR SELECIONADO
	var valorSelecionado = 0;
	var valorAcrescimosSelecionado = 0;

	if(objeto.name == 'conta' || objeto.name == 'contaPreterita'){
		myString = new String(objeto.alt);
		splitString = myString.split(";");
		valorSelecionado  = splitString[0];
		valorAcrescimosSelecionado  = splitString[1];
		
		valorAcrescimosSelecionado = valorAcrescimosSelecionado.replace(".","");
		valorAcrescimosSelecionado = valorAcrescimosSelecionado.replace(",",".");
		
	}else{
		valorSelecionado = objeto.alt;
	}
	
	valorSelecionado = valorSelecionado.replace(".","");
	valorSelecionado = valorSelecionado.replace(",",".");

	if ((objeto.name.substr(0,16) == "valorAntecipacao") || (objeto.checked == true)){
		valorTotalSelecionado = (valorTotalSelecionado * 1) + (valorSelecionado * 1);
		valorTotalAtualizadoSelecionado = (valorTotalAtualizadoSelecionado * 1) + (valorSelecionado * 1) + (valorAcrescimosSelecionado * 1);
	}
	else{
		valorTotalSelecionado = (valorTotalSelecionado * 1) - (valorSelecionado * 1);
		valorTotalAtualizadoSelecionado = (valorTotalAtualizadoSelecionado * 1) - (valorSelecionado * 1) - (valorAcrescimosSelecionado * 1);
	}
	
	objetoTotalDebitoSelecionado.innerHTML = formatarValorMonetario(valorTotalSelecionado);
	if(valorTotalAtualizadoSelecionado >= 0){
		objetoTotalDebitoAtualizadoSelecionado.innerHTML = formatarValorMonetario(valorTotalAtualizadoSelecionado);
	}else{
		objetoTotalDebitoAtualizadoSelecionado.innerHTML = "0,00";
	}
}


</SCRIPT>

</head>

<body onload="setarFoco('${requestScope.idImovel}');validaCheck();">
	<%@ include file="/jsp/portal/cabecalho.jsp"%>
	<%@ include file="/jsp/util/mensagens.jsp"%>
	<%-- 	<%@ include file="/jsp/portal/acesso-barra.jsp"%> --%>

<html:form action="/extrato-debitos.do" name="EmitirExtratoDebitosActionForm" type="gcom.gui.portal.EmitirExtratoDebitosActionForm" method="post">
<html:hidden property="idsConta"/>
<html:hidden property="idsDebito"/>
<html:hidden property="idsCredito"/>
<html:hidden property="idsGuia"/>
<html:hidden property="idsParcelamento"/>

<input type="hidden" name="checkConta" value="0">
<input type="hidden" name="checkContaPreterita" value="0">
<input type="hidden" name="checkCredito" value="0">
<input type="hidden" name="checkDebito" value="0">
<input type="hidden" name="checkGuia" value="0">
<input type="hidden" name="checkParcelamento" value="0">

<input type="hidden" name="valorConta" value="0">

		<div class="page-wrap">
			<div class="container pagina">
				<div class="container container-breadcrumb">
					<ul class="breadcrumb">
						<li class="breadcrumb-item"><a href="portal.do">Página Inicial</a></li>
						<li class="breadcrumb-item active">Extrato de Debitos</li>
					</ul>
				</div>

				<div class="pagina-titulo">
					<h2>Extrato de Debitos</h2>
				</div>

				<div class="pagina-conteudo">
					<p>
						Informe apenas os números da <b>Matrícula</b>, que se encontra na parte superior da sua conta de água:
					</p>

					<div class="container">
						<logic:notEmpty name="erro-segunda-via" scope="request">
							<div class="row">
								<div class="alert alert-danger">
									<html:errors property="erro-segunda-via" />
								</div>
							</div>
						</logic:notEmpty>
						<div class="row">
							<html:text property="idImovel" size="9" maxlength="9" onkeypress="return isCampoNumerico(event);" styleClass="form-control col-sm-2" />
							<input type="submit" value="Consultar" class="btn btn-primary btn-consulta">
						</div>
						<br>
						<br>
					</div>

					<h3>Dados do Imóvel:</h3>
					
					<table class="table table-bordered text-center">
						<thead>
							<tr>
								<th style="width: 25%"><span>Matrícula: </span>${EmitirExtratoDebitosActionForm.idImovel}</th>
								<th style="width: 75%"><span>Cliente: </span>${EmitirExtratoDebitosActionForm.nomeClienteUsuarioImovel}</th>
							</tr>
						</thead>
					</table>
					
					<br>
					
					<h3>Contas em Aberto:</h3>
					<%-- <%BigDecimal valorTotalConta = new BigDecimal("0.00");%>
					<%BigDecimal valorTotalAcrescimo = new BigDecimal("0.00");%>
					
					<%BigDecimal valorTotalDebitosFinal = valorTotalConta.add(valorTotalDebito);%>
					<%valorTotalDebitosFinal = valorTotalDebitosFinal.add(valorTotalContaPreteritos);%>
					<%valorTotalDebitosFinal = valorTotalDebitosFinal.add(valorTotalGuiaPagamento);%>
					<%valorTotalDebitosFinal = valorTotalDebitosFinal.add(valorTotalParcelamento);%>
					<%BigDecimal valorTotalDebitosAtualizado = valorTotalDebitosFinal.add(valorTotalAcrescimo);%> --%>
					<table class="table table-bordered text-center">
						<thead>
							<tr>
								<th style="width: 50%"><span>Quantidade de Contas: </span>${EmitirExtratoDebitosActionForm.quantidadeContas}</th>
								<th style="width: 50%"><span>Total: </span>R$ ${EmitirExtratoDebitosActionForm.valorTotalContas}</th>
							</tr>
						</thead>
					</table>
					
					<br/>
					<!-- Contas -->
					<h3>Contas:</h3>
					<table class="table table-striped table-sm table-bordered text-center">
						<thead>
							<tr>
							    <td>
								    <%BigDecimal valorTotalConta = new BigDecimal("0.00");%>
						            <%BigDecimal valorTotalAcrescimo = new BigDecimal("0.00");%>
								    <strong><a href="javascript:facilitador(document.forms[0].checkConta,'conta');" id="0">Todos</a></strong>
						        </td>
								<th>Mês/Ano</th>
								<th>Vencimento</th>
								<th>Valor (R$)</th>
								<th>Acrés. Impont.</th>
								<th>Situação</th>
							</tr>
						</thead>
						<tbody>
							<logic:notEmpty name="contas" scope="request">
								<logic:iterate name="contas" id="helper"
									type="ContaValoresHelper">
									<%valorTotalConta = valorTotalConta.add(helper.getValorTotalConta()); %>
						            <%valorTotalAcrescimo = valorTotalAcrescimo.add(helper.getValorTotalContaValoresParcelamento()); %>
									<tr>
										<!--Checkbox   -->
										<td align="center" width="5%">
												<INPUT TYPE="checkbox"
													NAME="conta" value="<%="" + helper.getConta().getId().intValue()%>"
													alt="<%="" + Util.formatarMoedaReal(helper.getValorTotalConta()).trim()%>
													<%=";" + Util.formatarMoedaReal(helper.getValorTotalContaValoresParcelamento()).trim()%>"
													onclick="totalizarDebito(this);"
												>
										</td>
										
										<!-- Mês/Ano -->
										<td>
											<bean:write name="helper"
												property="formatarAnoMesParaMesAno"
											/>
										</td>
										
										<!-- Vencimento -->
										<td>
											<bean:write name="helper" property="vencimentoConta" />
										</td>
										
										<!-- Valor -->
										<td>
											<bean:write name="helper"
												property="valorTotalContaFormatado" />
										</td>
										
										<!-- Acrés. Impont. -->
										<td width="20%">
											<div align="center">

												<logic:equal name="helper"
													property="valorTotalContaValoresParcelamento" value="0.00">
													<span style="color: #000000;"> <%="" + Util.formatarMoedaReal(helper.getValorTotalContaValoresParcelamento()).trim()%>
													</span>
												</logic:equal>

												<logic:notEqual name="helper"
													property="valorTotalContaValoresParcelamento" value="0.00">
													<a
														title="<%="Multa: " + Util.formatarMoedaReal(helper.getValorMulta()).trim() + " Juros de Mora: "
															+ Util.formatarMoedaReal(helper.getValorJurosMora()).trim() + " Atualização Monetária: "
															+ Util.formatarMoedaReal(helper.getValorAtualizacaoMonetaria()).trim()%>">

														<%="" + Util.formatarMoedaReal(helper.getValorTotalContaValoresParcelamento()).trim()%>
													</a>
												</logic:notEqual>

											</div>
										</td>
										
										<!-- Situação -->
										<td width="20%">
											<div align="center">
													<font color="#000000"> <bean:write name="helper"
															property="conta.debitoCreditoSituacaoAtual.descricaoDebitoCreditoSituacao" />
													</font>
											</div>
										</td>
									</tr>
								</logic:iterate>
								
								<!-- Total    Valor:    Acrés. Impont. -->
								<tr>			
									<td width="5%" height="20"></td>
									<td width="15%">
										<div align="center"><strong>Total:</strong></div>
									</td>
									<td width="20%"></td>
									<td width="20%">
										<div align="right"><strong>
											<%="" + Util.formatarMoedaReal(valorTotalConta).trim()%>
										</strong></div>
									</td>
									<td width="20%">
										<div align="right"><strong>
											<%="" + Util.formatarMoedaReal(valorTotalAcrescimo).trim()%>
										</strong></div>
									</td>
									<td width="20%"></td>
			
								</tr>
							</logic:notEmpty>

							<logic:empty name="contas" scope="request">
								<tr>
									<td colspan="6">Nenhuma fatura em aberto</td>
								</tr>
							</logic:empty>
						</tbody>
						<input TYPE="hidden" NAME="valorTotalConta" ID="valorTotalConta" value="<%= "" + valorTotalConta %>"/>
						<input type="hidden" name="valorTotalAcrescimo" ID="valorTotalAcrescimo" value="<%="" + valorTotalAcrescimo %>">
					</table>
					
					<br/>
					<!-- Contas Inconformes -->
					<h3>Contas Inconformes:</h3>
					<table class="table table-striped table-sm table-bordered text-center">
						<thead>
							<tr>
								<th>Mês/Ano Conta</th>
								<th>Valor da Conta</th>
								<th>Valor do Pag.</th>
								<th>Data do Pag.</th>
								<th>Situação Anterior</th>
								<th>Situação Atual</th>
							</tr>
						</thead>
						<tbody>
							<logic:notEmpty name="colecaoPagamentosImovelContaInconformes" scope="request">
								<logic:iterate name="colecaoPagamentosInconformesAtuais" id="pagamento" type="Pagamento">
									<tr>
				
										<!-- Mês/Ano Conta -->
										<td>
											<logic:notEmpty
												name="pagamento" property="contaGeral">
												<logic:notEmpty name="pagamento" property="contaGeral.conta">
														<logic:notEmpty name="pagamento" property="contaGeral.conta.id">
															<logic:notEmpty name="pagamento"
																property="contaGeral.conta.referencia">
																<a href="javascript:abrirPopup('exibirConsultarContaAction.do?tipoConsulta=conta&contaID=<%="" + pagamento.getContaGeral().getId() %>' , 600, 800);">${pagamento.contaGeral.conta.formatarAnoMesParaMesAno}</a>
															</logic:notEmpty>
														</logic:notEmpty>
												</logic:notEmpty>
												
												<logic:notEmpty name="pagamento" property="contaGeral.contaHistorico">
														<logic:notEmpty name="pagamento" property="contaGeral.contaHistorico.id">
															<logic:notEmpty name="pagamento"
																property="contaGeral.contaHistorico.anoMesReferenciaConta">
																<a href="javascript:abrirPopup('exibirConsultarContaAction.do?tipoConsulta=conta&contaID=<%="" + pagamento.getContaGeral().getId() %>' , 600, 800);">${pagamento.contaGeral.contaHistorico.formatarAnoMesParaMesAno}</a>
															</logic:notEmpty>
														</logic:notEmpty>
												</logic:notEmpty>
												
											</logic:notEmpty>
											<logic:empty name="pagamento" property="contaGeral">
													${pagamento.formatarAnoMesPagamentoParaMesAno}
											</logic:empty>
										</td>
										
										<!-- Valor da Conta -->
										<td>
											<logic:notEmpty	name="pagamento" property="contaGeral">
													<logic:notEmpty name="pagamento" property="contaGeral.conta">
														<logic:notEmpty name="pagamento" property="contaGeral.conta.valorTotalDaConta">
															<bean:write name="pagamento" property="contaGeral.conta.valorTotalDaConta" format="#,##0.00" locale="currentLocale"/>
														</logic:notEmpty>
													</logic:notEmpty>
													<logic:notEmpty name="pagamento" property="contaGeral.contaHistorico">
															<logic:notEmpty name="pagamento" property="contaGeral.contaHistorico.valorTotalDaConta">
																<bean:write name="pagamento" property="contaGeral.contaHistorico.valorTotalDaConta"	format="#,##0.00" locale="currentLocale"/>
															</logic:notEmpty>
													</logic:notEmpty>
											</logic:notEmpty>
										</td>
										
										<!-- Valor do Pag. -->
										<td>
											<bean:write name="pagamento" property="valorPagamento" format="#,##0.00" locale="currentLocale" />
										</td>
										
										<!-- Data do Pag. -->
										<td>
											<a href="javascript:abrirPopup('exibirConsultarPagamentoPopupAction.do?idPagamento=${pagamento.id}' , 210, 510);">
											<bean:write name="pagamento" property="dataPagamento" formatKey="date.format" /></a>
										</td>
										
										<!-- Situação Anterior -->
										<td>
											${pagamento.pagamentoSituacaoAnterior.descricaoAbreviada}
										</td>
										
										<!-- Situação Atual -->
										<td>
											${pagamento.pagamentoSituacaoAtual.descricaoAbreviada}&nbsp;
										</td>
										
									</tr>
								</logic:iterate>
								
								<logic:present name="colecaoPagamentosInconformesPreteritos">
									<logic:notEmpty name="colecaoPagamentosInconformesPreteritos" scope="request">
										<tr>
											<td>
												<div>
													<strong>Pagamentos Inconformes de Clientes Anteriores</strong>
												</div>
											</td>
										</tr>
									</logic:notEmpty>
									
									<logic:iterate name="colecaoPagamentosInconformesPreteritos"
														id="pagamento" type="Pagamento">
										<td>
											<logic:notEmpty
												name="pagamento" property="contaGeral">
												<logic:notEmpty name="pagamento" property="contaGeral.conta">
														<logic:notEmpty name="pagamento" property="contaGeral.conta.id">
															<logic:notEmpty name="pagamento"
																property="contaGeral.conta.referencia">
																<a href="javascript:abrirPopup('exibirConsultarContaAction.do?tipoConsulta=conta&contaID=<%="" + pagamento.getContaGeral().getId() %>' , 600, 800);">${pagamento.contaGeral.conta.formatarAnoMesParaMesAno}</a>
															</logic:notEmpty>
														</logic:notEmpty>
												</logic:notEmpty>
												
												<logic:notEmpty name="pagamento" property="contaGeral.contaHistorico">
														<logic:notEmpty name="pagamento" property="contaGeral.contaHistorico.id">
															<logic:notEmpty name="pagamento"
																property="contaGeral.contaHistorico.anoMesReferenciaConta">
																<a href="javascript:abrirPopup('exibirConsultarContaAction.do?tipoConsulta=conta&contaID=<%="" + pagamento.getContaGeral().getId() %>' , 600, 800);">${pagamento.contaGeral.contaHistorico.formatarAnoMesParaMesAno}</a>
															</logic:notEmpty>
														</logic:notEmpty>
												</logic:notEmpty>
												
											</logic:notEmpty>
											<logic:empty name="pagamento" property="contaGeral">
													${pagamento.formatarAnoMesPagamentoParaMesAno}
											</logic:empty> 
										</td>
										<td>
											<logic:notEmpty	name="pagamento" property="contaGeral">
													<logic:notEmpty name="pagamento" property="contaGeral.conta">
														<logic:notEmpty name="pagamento" property="contaGeral.conta.valorTotalDaConta">
															<bean:write name="pagamento" property="contaGeral.conta.valorTotalDaConta"
																format="#,##0.00" locale="currentLocale" />
														</logic:notEmpty>
													</logic:notEmpty>
												<logic:notEmpty name="pagamento" property="contaGeral.contaHistorico">
														<logic:notEmpty name="pagamento" property="contaGeral.contaHistorico.valorTotalDaConta">
															<bean:write name="pagamento" property="contaGeral.contaHistorico.valorTotalDaConta"
																format="#,##0.00" locale="currentLocale" />
														</logic:notEmpty>
												</logic:notEmpty>
											</logic:notEmpty>
										</td>
										
										<td>
											<bean:write name="pagamento" property="valorPagamento" format="#,##0.00" locale="currentLocale" />
										</td>
										
										<td>
											<a href="javascript:abrirPopup('exibirConsultarPagamentoPopupAction.do?idPagamento=${pagamento.id}' , 210, 510);">
											<bean:write name="pagamento" property="dataPagamento" formatKey="date.format" /></a>
										</td>
										
										<td>
												${pagamento.pagamentoSituacaoAnterior.descricaoAbreviada}
										</td>
										<td>
												${pagamento.pagamentoSituacaoAtual.descricaoAbreviada}
										</td>
									</logic:iterate>
								</logic:present>
								
							</logic:notEmpty>
							
							<logic:empty name="colecaoPagamentosImovelContaInconformes" scope="request">
								<tr>
									<td colspan="6">Nenhuma fatura em aberto</td>
								</tr>
							</logic:empty>
							
						</tbody>
					</table>
					
					<br/>
					<!-- Débitos Pretéritos -->
					<h3>Débitos Pretéritos:</h3>
					<table class="table table-striped table-sm table-bordered text-center">
						<thead>
							<tr>
							    <td>
							    	<strong><a href="javascript:facilitador(document.forms[0].checkContaPreterita,'contaPreterita');" id="0">Todos</a></strong>
						        </td>
								<th>Mês/Ano</th>
								<th>Vencimento</th>
								<th>Valor</th>
								<th>Acrés. Impont.</th>
								<th>Situação</th>
							</tr>
						</thead>
						<tbody>
							<%BigDecimal valorTotalContaPreteritos = new BigDecimal("0.00");%>
							<%BigDecimal valorTotalAcrescimoPreterito = new BigDecimal("0.00");%>
							<logic:present name="colecaoContaPreteritos">
								<logic:notEmpty name="colecaoContaPreteritos" scope="request">
									<logic:iterate name="colecaoContaPreteritos" id="conta" type="ContaValoresHelper">
										<tr>
											<%valorTotalContaPreteritos = valorTotalContaPreteritos.add(conta.getValorTotalConta()); %>
											<%valorTotalAcrescimoPreterito = valorTotalAcrescimoPreterito.add(conta.getValorTotalContaValoresParcelamento()); %>
											
											<!--  Checkbox -->
											<td>
												<INPUT TYPE="checkbox" NAME="contaPreterita" value="<%="" + conta.getConta().getId().intValue() %>" 
												alt="<%="" + Util.formatarMoedaReal(conta.getValorTotalConta()).trim()%>
												<%=";" + Util.formatarMoedaReal(conta.getValorTotalContaValoresParcelamento()).trim()%>"
												onclick="totalizarDebito(this);"> 
											</td>
											
											<logic:empty name="conta" property="conta.contaMotivoRevisao">
												<!-- Mês/Ano  -->
												<td>
													<font color="#000000"> 
														<a href="javascript:abrirPopup('exibirConsultarContaAction.do?contaID=<%="" + conta.getConta().getId() %>&tipoConsulta=conta', 600, 800);">
														<%=""+ Util.formatarMesAnoReferencia(conta.getConta().getReferencia())%> </a>
													</font>	
												</td>
												
												<!-- Data de Vencimento -->
												<td>
													<div align="center">
														<logic:present name="conta" property="conta.dataVencimentoConta">
															<span style="color: #000000;">
																<%="" + Util.formatarData(conta.getConta().getDataVencimentoConta())%>
															</span>
														</logic:present> 
														<logic:notPresent name="conta" property="conta.dataVencimentoConta">
															&nbsp;
														</logic:notPresent>	
													</div>
												</td>
												
												<!-- Valor -->
												<td>
													<div align="right">
														<span style="color: #000000;">
															<%="" + Util.formatarMoedaReal(new BigDecimal(conta.getConta().getValorTotalConta())).trim()%>
														</span>
													</div>
												</td>
												
												<!-- Acrés. Impont. -->
												<td>
													<div>
														
														<logic:equal name="conta" property="valorTotalContaValoresParcelamento" value="0.00">
														<span style="color: #000000;">
															<%="" + Util.formatarMoedaReal(conta.getValorTotalContaValoresParcelamento()).trim()%>
														</span>
														</logic:equal>
														
														<logic:notEqual name="conta" property="valorTotalContaValoresParcelamento" value="0.00">
														<a title="<%="Multa: " + Util.formatarMoedaReal(conta.getValorMulta()).trim() + 
														" Juros de Mora: " + Util.formatarMoedaReal(conta.getValorJurosMora()).trim() +
														" Atualização Monetária: " + Util.formatarMoedaReal(conta.getValorAtualizacaoMonetaria()).trim()%>">
															
															<%="" + Util.formatarMoedaReal(conta.getValorTotalContaValoresParcelamento()).trim()%>
														</a>
														</logic:notEqual>
														
													</div>
												</td>
												
												<!--  Situação -->
												<td>
													<div align="center">
																			
														<logic:present name="conta" property="conta.debitoCreditoSituacaoAtual">
															<font color="#000000"> 
																<bean:write name="conta" property="conta.debitoCreditoSituacaoAtual.descricaoDebitoCreditoSituacao" />
															</font>
														</logic:present> 
																			
														<logic:notPresent name="conta" property="conta.debitoCreditoSituacaoAtual">
															&nbsp;
														</logic:notPresent>
																			
													</div>
												</td>
											</logic:empty>
											
											<logic:notEmpty name="conta" property="conta.contaMotivoRevisao">
												<!-- Mês/Ano  -->
												<td>
													<font color="#ff0000"> 
														<a href="javascript:abrirPopup('exibirConsultarContaAction.do?contaID=<%="" + conta.getConta().getId() %>&tipoConsulta=conta', 600, 800);">
														<font color="#ff0000"><%=""+ Util.formatarMesAnoReferencia(conta.getConta().getReferencia())%> </font></a>
													</font>
												</td>
												
												<!-- Data de Vencimento -->
												<td>
													<div>
														<logic:present name="conta" property="conta.dataVencimentoConta">
															<span style="color: #ff0000;">
																<%="" + Util.formatarData(conta.getConta().getDataVencimentoConta())%>
															</span>
														</logic:present> 
														<logic:notPresent name="conta" property="conta.dataVencimentoConta">
															&nbsp;
														</logic:notPresent>	
													</div>
												</td>
												
												<!-- Valor -->
												<td>
													<div align="right">
														<span style="color: #ff0000;">
															<%="" + Util.formatarMoedaReal(new BigDecimal(conta.getConta().getValorTotalConta())).trim()%>
														</span>
													</div>
												</td>
												
												<!-- Acrés. Impont. -->
												<td>
													<div align="right">
														
														<logic:equal name="conta" property="valorTotalContaValoresParcelamento" value="0.00">
														<span style="color: #ff0000;">
															<%="" + Util.formatarMoedaReal(conta.getValorTotalContaValoresParcelamento()).trim()%>
														</span>
														</logic:equal>
														
														<logic:notEqual name="conta" property="valorTotalContaValoresParcelamento" value="0.00">
														<a title="<%="Multa: " + Util.formatarMoedaReal(conta.getValorMulta()).trim() + 
														" Juros de Mora: " + Util.formatarMoedaReal(conta.getValorJurosMora()).trim() +
														" Atualização Monetária: " + Util.formatarMoedaReal(conta.getValorAtualizacaoMonetaria()).trim()%>">
															<span style="color: #ff0000;">
																<%="" + Util.formatarMoedaReal(conta.getValorTotalContaValoresParcelamento()).trim()%>
															</span>
														</a>
														</logic:notEqual>
														
													</div>
												</td>
												
												<!--  Situação -->
												<td>
													<div>
																			
														<logic:present name="conta" property="conta.debitoCreditoSituacaoAtual">
															<font color="#ff0000"> 
																<bean:write name="conta" property="conta.debitoCreditoSituacaoAtual.descricaoDebitoCreditoSituacao" />
															</font>
														</logic:present> 
																			
														<logic:notPresent name="conta" property="conta.debitoCreditoSituacaoAtual">
															&nbsp;
														</logic:notPresent>
																			
													</div>
												</td>
												
											</logic:notEmpty>
										</tr>
									</logic:iterate>
									
									<tr>
										<td></td>
										<td>
											<div>
												<strong>Total:</strong>
											</div>
										</td>
										<td></td>
										<td>
											<div >
												<strong>
													<%="" + Util.formatarMoedaReal(valorTotalContaPreteritos).trim()%>
												</strong>
											</div>
										</td>
										<td>
											<div>
												<strong>
													<%="" + Util.formatarMoedaReal(valorTotalAcrescimoPreterito).trim()%>
												</strong>
											</div>
										</td>
										<td></td>
									</tr>
								</logic:notEmpty>
							</logic:present>
							
						</tbody>
						<input TYPE="hidden" NAME="valorTotalContaPreterita" ID="valorTotalContaPreterita" value="<%= "" + valorTotalContaPreteritos %>"/>
						<input type="hidden" name="valorTotalAcrescimoPreterito" ID="valorTotalContaPreterita" value="<%="" + valorTotalAcrescimoPreterito %>">
					</table>
					
					<br/>
					<!-- Débitos A Cobrar -->
					<h3>Débitos A Cobrar:</h3>
					<table class="table table-striped table-sm table-bordered text-center">
						<thead>
							<tr>
							    <td>
							    	<strong><a href="javascript:facilitador(document.forms[0].checkDebito,'debito');"id="0">Todos</a></strong>
						        </td>
								<th>Tipo do Débito</th>
								<th>Mês/Ano Ref.</th>
								<th>Mês/Ano Cobr.</th>
								<th>Prestação</th>
								<th>VL. Restante</th>
							</tr>
						</thead>
						<tbody>
							<%BigDecimal valorTotalDebito = new BigDecimal("0.00");%>
							<logic:present name="colecaoDebitoACobrar">
								<logic:notEmpty name="colecaoDebitoACobrar" scope="request">
											<logic:iterate name="colecaoDebitoACobrar" id="debitoACobrar" type="DebitoACobrar">
												<%valorTotalDebito = valorTotalDebito.add(debitoACobrar.getValorTotalComBonus()); %>
												<tr>
													<!-- Checkbox -->
													<td>
														<INPUT TYPE="checkbox" NAME="debito"
														value="<%="" + debitoACobrar.getId().intValue() %>"
														alt="<%="" + Util.formatarMoedaReal(debitoACobrar.getValorTotalComBonus()).trim()%>"
														onclick="totalizarDebito(this);"
														>
													</td>
													
													<!-- Tipo de Débito -->
													<td>
														<a href="javascript:abrirPopup('exibirConsultarDebitoACobrarAction.do?imovelID=<%="" + debitoACobrar.getImovel().getId().intValue() %>&debitoID=<%="" + debitoACobrar.getId().intValue() %>', 570, 720);">
														<bean:write name="debitoACobrar" property="debitoTipo.descricao" /></a>
													</td>
													
													<!-- Mês/Ano Ref. -->
													<td>
														<div>
														
															<logic:present name="debitoACobrar" property="anoMesReferenciaDebito">
																<span style="color: #000000;">
																	<%=""+ Util.formatarMesAnoReferencia(debitoACobrar.getAnoMesReferenciaDebito())%>
																</span>
															</logic:present> 
															
															<logic:notPresent name="debitoACobrar" property="anoMesReferenciaDebito">
																&nbsp;
															</logic:notPresent>	
														
														</div>
													</td>
													
													<!--  Mês/Ano Cobr. -->
													<td>
														<div>
															
															<logic:present name="debitoACobrar" property="anoMesCobrancaDebito">
																<span style="color: #000000;">
																	<%=""+ Util.formatarMesAnoReferencia(debitoACobrar.getAnoMesCobrancaDebito())%>
																</span>
															</logic:present> 
															
															<logic:notPresent name="debitoACobrar" property="anoMesCobrancaDebito">
																&nbsp;
															</logic:notPresent>	
														
														</div>
													</td>
													
													<!-- Prestação -->
													<td>
														<div>
															
															<bean:write name="debitoACobrar" property="numeroPrestacaoCobradas" /> /
															<bean:write name="debitoACobrar" property="numeroPrestacaoDebitoMenosBonus" />
															
														</div>
													</td>
													
													<!-- VL. Restante -->
													<td>
														<div>
																				
															<%="" + Util.formatarMoedaReal(debitoACobrar.getValorTotalComBonus()).trim()%>
																				
														</div>
													</td>
												</tr>
											</logic:iterate>
											
											<tr>
												<td></td>
												<td>
													<div><strong>Total:</strong></div>
												</td>
												<td></td>
												<td></td>
												<td></td>
												<td>
													<div>
														<strong>				
														<%="" + Util.formatarMoedaReal(valorTotalDebito).trim()%>
														</strong>				
													</div>
												</td>
												<td></td>
						
											</tr>

								</logic:notEmpty>
							</logic:present>
							
						</tbody>
						<input type="hidden" name="valorTotalDebito" ID="valorTotalDebito" value="<%="" + valorTotalDebito %>">
					</table>
					
					<br/>
					<!-- Guias de Pagamento -->
					<h3>Guias de Pagamento:</h3>
					<table class="table table-striped table-sm table-bordered text-center">
						<thead>
							<tr>
							    <td>
							   	
							    <strong><a href="javascript:facilitador(document.forms[0].checkGuia,'guiaPagamento');"id="0">Todos</a></strong>
						        </td>
								<th>Tipo do Débito</th>
								<th>Prestação</th>
								<th>Emissão</th>
								<th>Vencimento</th>
								<th>Valor</th>
								<%BigDecimal valorTotalGuiaPagamento = new BigDecimal("0.00");%>
							</tr>
						</thead>
						<tbody>
							
							<logic:notEmpty name="colecaoGuiaPagamento" scope="request">
								<logic:iterate name="colecaoGuiaPagamento" id="guiaPagamentoValoresHelper" type="GuiaPagamentoValoresHelper">
									<%valorTotalGuiaPagamento = valorTotalGuiaPagamento.add(guiaPagamentoValoresHelper.getGuiaPagamento().getValorDebito()); %>
									<tr>
										
										<!--Checkbox   -->
										<td align="center" width="5%">
											<INPUT TYPE="checkbox" NAME="guiaPagamento"
											value="<%="" + guiaPagamentoValoresHelper.getGuiaPagamento().getId().intValue() %>"											
											alt="<%="" + Util.formatarMoedaReal(guiaPagamentoValoresHelper.getGuiaPagamento().getValorDebito()).trim()%>"
											onclick="totalizarDebito(this);">
										</td>
										
										<!-- Tipo do Débito -->
										<td>				
											<a href="javascript:abrirPopup('exibirConsultarGuiaPagamentoAction.do?guiaPagamentoId=<%="" + guiaPagamentoValoresHelper.getGuiaPagamento().getId().intValue() %>', 600, 800);">
											<bean:write name="guiaPagamentoValoresHelper" property="guiaPagamento.debitoTipo.descricao" /></a>
										</td>
										
										<!-- Prestação -->
										<td align="center">
									
											<%="" + guiaPagamentoValoresHelper.getGuiaPagamento().getPrestacaoFormatada()%>
											
										</td>
										
										<!-- Emissão -->
										<td align="center">
								
											<logic:present name="guiaPagamentoValoresHelper" property="guiaPagamento.dataEmissao">
												<span style="color: #000000;">
													<%="" + Util.formatarData(guiaPagamentoValoresHelper.getGuiaPagamento().getDataEmissao())%>
												</span>
											</logic:present> 
											
											<logic:notPresent name="guiaPagamentoValoresHelper" property="guiaPagamento.dataEmissao">
												&nbsp;
											</logic:notPresent>	
										
										</td>
										
										<!-- Vencimento -->
										<td align="center">
									
											<logic:present name="guiaPagamentoValoresHelper" property="guiaPagamento.dataVencimento">
												<span style="color: #000000;">
													<%="" + Util.formatarData(guiaPagamentoValoresHelper.getGuiaPagamento().getDataVencimento())%>
												</span>
											</logic:present> 
											
											<logic:notPresent name="guiaPagamentoValoresHelper" property="guiaPagamento.dataVencimento">
												&nbsp;
											</logic:notPresent>	
										
										</td>
										
										<!-- Valor -->
										<td align="right">
									
											<%="" + Util.formatarMoedaReal(guiaPagamentoValoresHelper.getGuiaPagamento().getValorDebito()).trim()%>
											
										</td>
									</tr>
								</logic:iterate>
								
								<tr>
									<td width="5%" height="20"></td>
									<td width="35%">
										<div align="center"><strong>Total:</strong></div>
									</td>
									<td width="15%"></td>
									<td width="15%"></td>
									<td width="15%"></td>
									<td width="15%">
										<div align="right">
											<strong>				
											<%="" + Util.formatarMoedaReal(valorTotalGuiaPagamento).trim()%>
											</strong>				
										</div>
									</td>
								</tr>
								
							</logic:notEmpty>
							
							<logic:empty name="colecaoGuiaPagamento" scope="request">
								<tr>
									<td colspan="6">Nenhuma fatura em aberto</td>
								</tr>
							</logic:empty>
							
						</tbody>
						<input type="hidden" name="valorTotalGuiaPagamento" ID="valorTotalGuiaPagamento" value="<%="" + valorTotalGuiaPagamento %>">
					</table>
					
					<br/>
					<!-- Parcelamento -->
					<h3>Parcelamento:</h3>
					<table class="table table-striped table-sm table-bordered text-center">
						<thead>
							<tr>
							    <td>
							    	<strong><a href="javascript:facilitador(document.forms[0].checkParcelamento,'parcelamento');"id="0">Todos</a></strong>
						        </td>
								<th>Data</th>
								<th>Prestação</th>
								<th>VL Restante</th>
								<th>Antecipação</th>
							</tr>
						</thead>
						<tbody>
							<%BigDecimal valorTotalParcelamento = new BigDecimal("0.00");%>
							<logic:present name="colecaoDebitoCreditoParcelamento">
								<logic:notEmpty name="colecaoDebitoCreditoParcelamento" scope="request">
									<logic:iterate name="colecaoDebitoCreditoParcelamento" id="debitoCredito" type="DebitoCreditoParcelamentoHelper">
										<%valorTotalParcelamento = valorTotalParcelamento.add(debitoCredito.getValorTotal()); %>
										<tr>
											<!-- Checkbox -->
											<td>
												<INPUT TYPE="checkbox" NAME="parcelamento"
												value="<%="" + debitoCredito.getParcelamento().getId().intValue() %>"
												alt="<%="" + Util.formatarMoedaReal(debitoCredito.getValorTotal()).trim()%>"
												onclick="controleCampoAntecipacaoParcela(this);totalizarDebito(this);">
											</td>
											
											<!-- Data -->
											<td>
												<div>
													<a href="javascript:abrirPopup('exibirConsultarParcelamentoDebitoPopupAction.do?
													codigoImovel=<bean:define name="debitoCredito" property="parcelamento.imovel" id="imovel" />
													<bean:write name="imovel" property="id" />
													&codigoParcelamento=<bean:write name="debitoCredito" property="parcelamento.id" />', 400, 800);">
													<bean:write name="debitoCredito" property="parcelamento.ultimaAlteracao" formatKey="date.format" /></a>
												</div>
											
											</td>
											
											<!-- Prestacao -->
											<td>
												<div>
													
													<%="" + debitoCredito.getPrestacaoFormatada()%>
													
												</div>
											</td>
											
											<!--  VL Restante	 -->
											<td>
												<div>
													<span>
														<%="" + Util.formatarMoedaReal(debitoCredito.getValorTotal()).trim()%>
													</span>
												</div>
											</td>
											
											<!-- Antecipação -->
											<td>
												<div>
													
													<%if (debitoCredito.isAntecipacaoParcela()){%>
														
														<input type="text"  name="parc<%="" + debitoCredito.getParcelamento().getId().intValue() %>" 
														size="2" maxlength="2" id="parc<%="" + debitoCredito.getParcelamento().getId().intValue() %>"/>
														
													<%} else{%>
														
														<input type="text"  name="parc<%="" + debitoCredito.getParcelamento().getId().intValue() %>" 
														size="2" maxlength="2" id="parc<%="" + debitoCredito.getParcelamento().getId().intValue() %>"
														readonly="true"/>
													
													<%}%>
													
													
												</div>
											</td>
											
										</tr>
									</logic:iterate>
									
									<tr >
										<td ></td>
										<td >
											<div align="center"><strong>Total:</strong></div>
										</td>
										<td></td>
										<td>
											<div><strong>
												<%="" + Util.formatarMoedaReal(valorTotalParcelamento).trim()%>
											</strong></div>
										</td>
										<td></td>
									</tr>
								</logic:notEmpty>
							</logic:present>
						</tbody>
						<input type="hidden" name="valorTotalParcelamento" ID="valorTotalParcelamento" value="<%="" + valorTotalParcelamento %>">
					</table>
					
									
					<%BigDecimal valorTotalDebitosFinal = valorTotalConta; %>
					
					<% valorTotalDebitosFinal = valorTotalDebitosFinal.add(valorTotalGuiaPagamento); %>
					<%valorTotalDebitosFinal = valorTotalDebitosFinal.add(valorTotalContaPreteritos); %>
					<%valorTotalDebitosFinal = valorTotalDebitosFinal.add(valorTotalParcelamento); %>
					
					
					<%BigDecimal valorTotalDebitosAtualizado = valorTotalDebitosFinal.add(valorTotalAcrescimo); %>
					<!-- Total dos Débitos -->
					<table class="table table-bordered text-left">
						<thead>
							<tr>
								<td HEIGHT="20"><strong>Total dos Débitos:</strong></td>
								<td><div align="right"><strong><%="" + Util.formatarMoedaReal(valorTotalDebitosFinal).trim()%> </strong></div></td>
							</tr>
						</thead>
					</table>
					
					<!-- Total dos Débitos Atualizados -->
					<table class="table table-bordered text-left">
						<thead>
							<tr>
								<td HEIGHT="20"><strong>Total dos Débitos Atualizados:</strong></td>
								<td><div align="right"><strong><%="" + Util.formatarMoedaReal(valorTotalDebitosAtualizado).trim()%></strong></div></td>
							</tr>
						</thead>
					</table>
					
					<!-- Total de Débitos Selecionados -->
					<table class="table table-bordered text-left">
						<thead>
							<tr>
								<td HEIGHT="20"><strong>Total de Débitos Selecionados:</strong></td>
								<td><div align="right" style="font-size: 12"><strong><span id="totalDebitoSelecionado">0,00</span></strong></div></td>
							</tr>
						</thead>
					</table>
					
					<!-- Total de Débitos Acumulados Selecionados -->
					<table class="table table-bordered text-left">
						<thead>
							<tr>
								<td HEIGHT="20"><strong>Total de Débitos Acumulados Selecionados:</strong></td>
								<td><div align="right" style="font-size: 12"><strong><span id="totalDebitoAtualizadoSelecionado">0,00</span></strong></div></td>
							</tr>
						</thead>
					</table>
					
					<td align="right">
						<input type="button" name="" value="Imprimir" class="bottonRightCol" 
						onclick="javascript:debitosCreditosSelecionados(document.forms[0],'/gsan/emissaoExtratoDebitoAction.do?','&extratoDebito=1');"
						url="emissaoRelatorioExtratoDebitoAction.do?extratoDebito=1"/>
					</td>
						<br> <span><b>OBS:</b> A baixa da conta, após o pagamento, será efetuada em até 2 (dois) dias úteis, após compensação.</span>
				</div>
			</div>
		</div>
	</html:form>

	<%@ include file="/jsp/portal/rodape.jsp"%>

	<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
	<script language="JavaScript" src="<bean:message key="caminho.portal.js"/>jquery-3.2.1.min.js"></script>
	<script language="JavaScript" src="<bean:message key="caminho.portal.js"/>jquery-ui.min.js"></script>
	<script language="JavaScript" src="<bean:message key="caminho.portal.js"/>popper.js"></script>
	<script language="JavaScript" src="<bean:message key="caminho.portal.js"/>bootstrap.min.js"></script>
</body>
</html:html>