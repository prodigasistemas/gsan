<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan" %>

<%@ page import="gcom.util.Util"%>
<%@ page import="java.math.BigDecimal"%>
<%@ page import="gcom.cobranca.bean.ContaValoresHelper"%>
<%@ page import="gcom.faturamento.debito.DebitoACobrar"%>
<%@ page import="gcom.faturamento.credito.CreditoARealizar"%>
<%@ page import="gcom.cobranca.bean.GuiaPagamentoValoresHelper"%>
<%@ page import="gcom.cobranca.bean.DebitoCreditoParcelamentoHelper"%>
<%@ page import="gcom.arrecadacao.pagamento.Pagamento"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">

<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js" ></script>

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>

<html:javascript staticJavascript="false"  formName="DebitoCreditoDadosSelecaoExtratoActionForm"/>

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
		form.action = "/gsan/exibirDebitoCreditoDadosSelecaoExtratoAction.do?menu=sim";
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
	    validaCheckCredito();
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
				totalizarDebito(elemento);
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
	objetoTotalDebitoAtualizadoSelecionado.innerHTML = formatarValorMonetario(valorTotalAtualizadoSelecionado);
}


</SCRIPT>

</head>

<body leftmargin="5" topmargin="5" onload="setarFoco('${requestScope.nomeCampo}');validaCheck();">

<html:form action="/exibirDebitoCreditoDadosSelecaoExtratoAction" method="post">

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



<%@ include file="/jsp/util/cabecalho.jsp"%>
<%@ include file="/jsp/util/menu.jsp" %>

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

      <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_left.gif"/></td>
          <td class="parabg">Extrato de Débitos</td>
          <td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif"/></td>
        </tr>
      </table>
      <p>&nbsp;</p>

      <table width="100%" border="0">
      <tr>
      	<td></td>
      </tr>
      </table>
						
	
	  <table width="100%" border="0">
		<tr>
			<td><strong>Imóvel:</strong></td>
			<td>
				<html:text property="idImovel" size="10" maxlength="10" tabindex="2"
				onkeypress="validaEnterComMensagem(event, 'exibirDebitoCreditoDadosSelecaoExtratoAction.do?pesquisarImovel=SIM', 'idImovel', 'Matrícula');return isCampoNumerico(event);" /> 
				<a href="javascript:chamarPopup('exibirPesquisarImovelAction.do', 'imovel', null, null, 490, 800, '', document.forms[0].idImovel);">
				<img src="<bean:message key='caminho.imagens'/>pesquisa.gif"
				 	 width="23" 
				 	 height="21" 
				 	 alt="Pesquisar" 
				 	 title="Pesquisar Imóvel"
				 	 border="0"></a> 

				<logic:present name="corImovel">
					<logic:equal name="corImovel" value="exception">
						<html:text property="inscricaoImovel" size="30" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000" />
					</logic:equal>

					<logic:notEqual name="corImovel" value="exception">
						<html:text property="inscricaoImovel" size="30" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" />
					</logic:notEqual>
				</logic:present> 

				<logic:notPresent name="corImovel">
					<logic:empty name="DebitoCreditoDadosSelecaoExtratoActionForm" property="idImovel">
						<html:text property="inscricaoImovel" value="" size="30" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000" />
					</logic:empty>
					
					<logic:notEmpty name="DebitoCreditoDadosSelecaoExtratoActionForm" property="idImovel">
						<html:text property="inscricaoImovel" size="30" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" />
					</logic:notEmpty>
				</logic:notPresent> 

				<a href="javascript:limparImovel();"> 
					<img src="<bean:message key='caminho.imagens'/>limparcampo.gif"
						alt="Apagar" 
						title="Apagar"
						border="0">
				</a>
			</td>
		</tr>
		
		<tr>
			<td><strong>Cliente:</strong></td>
			<td>
	   			<html:text property="nomeClienteUsuarioImovel" size="50" maxlength="50" readonly="true"
				style="background-color:#EFEFEF; border:0; color: #000000"/>
			</td>
		</tr>
		
		<tr>
			<td><strong>Situação da Ligação de Água:</strong></td>
			<td><html:text property="descricaoLigacaoAguaSituacaoImovel" size="20" maxlength="20" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/></td>
		</tr>
		
		<tr>
			<td><strong>Situação da Ligação de Esgoto:</strong></td>
			<td><html:text property="descricaoLigacaoEsgotoSituacaoImovel" size="20" maxlength="20" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/></td>
		</tr>
		
	</table>

	
	<table width="100%" border="0">
	<tr>
		<td HEIGHT="10"></td>
	</tr>
	</table>
		

	<table width="100%" border="0">
	<tr>
		<td>
		
			<table width="100%" cellpadding="0" cellspacing="0">
			<tr>
				<td>
					<%BigDecimal valorTotalConta = new BigDecimal("0.00");%>
					<%BigDecimal valorTotalAcrescimo = new BigDecimal("0.00");%>
					<table width="100%" cellpadding="0" cellspacing="0">
					<tr bgcolor="#79bbfd">
						<td colspan="6" height="20"><strong>Contas</strong></td>
					</tr>
					<tr bgcolor="#90c7fc">

						<td width="5%" height="20">
							<strong><a href="javascript:facilitador(document.forms[0].checkConta,'conta');" id="0">Todos</a></strong>
						</td>
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
							<div align="center"><strong>Acrés. Impont.</strong></div>
						</td>
						<td width="20%">
							<div align="center"><strong>Situação</strong></div>
						</td>

					</tr>
					</table>

				</td>
			</tr>
			
			


			<logic:present name="colecaoContaNormais">
			
			<logic:notEmpty name="colecaoContaNormais">

			<tr>
				<td>
										
					<% String cor = "#cbe5fe";%>

					<div style="width: 100%; height: 100; overflow: auto;">
										
					<table width="100%" align="center" bgcolor="#90c7fc">
										
					<logic:iterate name="colecaoContaNormais" id="conta" type="ContaValoresHelper">

						<%valorTotalConta = valorTotalConta.add(conta.getValorTotalConta()); %>
						<%valorTotalAcrescimo = valorTotalAcrescimo.add(conta.getValorTotalContaValoresParcelamento()); %>
						
						<%	if (cor.equalsIgnoreCase("#cbe5fe")){
							cor = "#FFFFFF";%>
							<tr bgcolor="#FFFFFF">
						<%} else{
							cor = "#cbe5fe";%>
							<tr bgcolor="#cbe5fe">
						<%}%>
											
												
						<td align="center" width="5%">
							<INPUT TYPE="checkbox" NAME="conta" value="<%="" + conta.getConta().getId().intValue() %>" 
							alt="<%="" + Util.formatarMoedaReal(conta.getValorTotalConta()).trim()%>
							<%=";" + Util.formatarMoedaReal(conta.getValorTotalContaValoresParcelamento()).trim()%>"
							onclick="totalizarDebito(this);"> 
						</td>
						
						<logic:empty name="conta" property="conta.contaMotivoRevisao">
						
							<td width="15%" align="center">
							<font color="#000000"> 
								<a href="javascript:abrirPopup('exibirConsultarContaAction.do?contaID=<%="" + conta.getConta().getId() %>&tipoConsulta=conta', 600, 800);">
								<%=""+ Util.formatarMesAnoReferencia(conta.getConta().getReferencia())%> </a>
							</font>	
							</td>
							<td width="20%">
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
							<td width="20%">
								<div align="right">
									<span style="color: #000000;">
										<%="" + Util.formatarMoedaReal(new BigDecimal(conta.getConta().getValorTotalConta())).trim()%>
									</span>
								</div>
								
							</td>
							<td width="20%">
								<div align="right">
									
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
							<td width="20%">
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
							<td width="15%" align="center">
								<font color="#ff0000"> 
									<a href="javascript:abrirPopup('exibirConsultarContaAction.do?contaID=<%="" + conta.getConta().getId() %>&tipoConsulta=conta', 600, 800);">
									<font color="#ff0000"><%=""+ Util.formatarMesAnoReferencia(conta.getConta().getReferencia())%> </font></a>
								</font>
							</td>
							<td width="20%">
								<div align="center">
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
							<td width="20%">
								<div align="right">
									<span style="color: #ff0000;">
										<%="" + Util.formatarMoedaReal(new BigDecimal(conta.getConta().getValorTotalConta())).trim()%>
									</span>
								</div>
							</td>
							<td width="20%">
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
							<td width="20%">
								<div align="center">
														
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
					
						<%if (cor.equalsIgnoreCase("#cbe5fe")){
							cor = "#FFFFFF";%>
							<tr bgcolor="#FFFFFF">
						<%} else{
							cor = "#cbe5fe";%>
							<tr bgcolor="#cbe5fe">
						<%}%>

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
										
					</table>
										
					</div>
					
				</td>
			</tr>

			</logic:notEmpty>
			
			</logic:present>
			
					
			<input TYPE="hidden" NAME="valorTotalConta" ID="valorTotalConta" value="<%= "" + valorTotalConta %>"/>
			<input type="hidden" name="valorTotalAcrescimo" ID="valorTotalAcrescimo" value="<%="" + valorTotalAcrescimo %>">
			
			        <tr>
					  <td colspan="1">&nbsp;</td>
				    </tr>
			
			</table>
			
			<table width="100%" align="center" bgcolor="#90c7fc" border="0">
						<% String cor = "#cbe5fe";%>
						<tr bordercolor="#79bbfd">
							<td colspan="10" bgcolor="#79bbfd">
							<strong>Contas Inconformes</strong>
							</td>
						</tr>
						
							<tr bordercolor="#000000">
								<td bgcolor="#90c7fc" width="14%" align="center" rowspan="2">
									<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif">
										<strong>Mês/Ano Conta</strong>
									</font>
								</td>
								<td bgcolor="#90c7fc" width="19%" align="center" rowspan="2">
									<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif">
										<strong>Valor da Conta</strong>
									</font>
								</td>
								<td bgcolor="#90c7fc" width="19%" align="center" rowspan="2">
									<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif">
										<strong>Valor do Pag.</strong>
									</font>
								</td>
								<td bgcolor="#90c7fc" width="16%" align="center" rowspan="2">
									<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif">
										<strong>Data do Pag.</strong>
									</font>
								</td>
								<td bgcolor="#90c7fc" width="32%" align="center" colspan="2">
									<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif">
										<strong>Situação</strong>
									</font>
								</td>
							</tr>
							<tr>
								<td width="16%" bgcolor="#cbe5fe" align="center">
									<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif">
										<strong>Anterior</strong>
									</font>
								</td>
								<td width="16%" bgcolor="#cbe5fe" align="center">
									<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif">
										<strong>Atual</strong>
									</font>
								</td>
							</tr>
						
						<logic:notEmpty name="colecaoPagamentosImovelContaInconformes" scope="session">							
							<tr>
								<td height="auto" colspan="10">
									<div style="width: 100%; max-height: 100px; overflow: auto;">
										<table width="100%">
											<%int cont1 = 1;%>
												<logic:iterate name="colecaoPagamentosInconformesAtuais"
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
														<td width="19%" align="right">
																<bean:write name="pagamento" property="valorPagamento" formatKey="money.format" />&nbsp;
														</td>
														
														<td width="16%" align="center">
																<a href="javascript:abrirPopup('exibirConsultarPagamentoPopupAction.do?idPagamento=${pagamento.id}' , 210, 510);">
																<bean:write name="pagamento" property="dataPagamento" formatKey="date.format" /></a>&nbsp;
														</td>
													
														<td width="16%">
																${pagamento.pagamentoSituacaoAnterior.descricaoAbreviada}&nbsp;
														</td>
														<td width="16%">
																${pagamento.pagamentoSituacaoAtual.descricaoAbreviada}&nbsp;
														</td>
													</tr>
												</logic:iterate>
												
												<logic:present name="colecaoPagamentosInconformesPreteritos">
													<logic:notEmpty name="colecaoPagamentosInconformesPreteritos" scope="session">
														<tr>
															<td colspan="10">
																<div align="center">
																		<strong>Pagamentos Inconformes de Clientes Anteriores</strong>
																</div>
															</td>
														</tr>
													</logic:notEmpty>
													
													<logic:iterate name="colecaoPagamentosInconformesPreteritos"
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
														<td width="19%" align="right">
																<bean:write name="pagamento" property="valorPagamento" formatKey="money.format" />&nbsp;
														</td>
														
														<td width="16%" align="center">
																<a href="javascript:abrirPopup('exibirConsultarPagamentoPopupAction.do?idPagamento=${pagamento.id}' , 210, 510);">
																<bean:write name="pagamento" property="dataPagamento" formatKey="date.format" /></a>&nbsp;
														</td>
													
														<td width="16%">
																${pagamento.pagamentoSituacaoAnterior.descricaoAbreviada}&nbsp;
														</td>
														<td width="16%">
																${pagamento.pagamentoSituacaoAtual.descricaoAbreviada}&nbsp;
														</td>
													</tr>
												</logic:iterate>
													
												</logic:present>
										</table>
									</div>
								</td>
							</tr>
						</logic:notEmpty>
					</table>
			<table width="100%" cellpadding="0" cellspacing="0">
			<tr>
				<td HEIGHT="5"></td>
			</tr>
			</table>
			
			<table width="100%" cellpadding="0" cellspacing="0">
			<tr>
				<td>
					<%BigDecimal valorTotalContaPreteritos = new BigDecimal("0.00");%>
					<%BigDecimal valorTotalAcrescimoPreterito = new BigDecimal("0.00");%>
					<table width="100%" cellpadding="0" cellspacing="0">
					<tr bgcolor="#79bbfd">
						<td colspan="6" height="20"><strong>D&eacute;bitos Pret&eacute;ritos</strong></td>
					</tr>
					<tr bgcolor="#90c7fc">

						<td width="5%" height="20">
							<strong><a href="javascript:facilitador(document.forms[0].checkContaPreterita,'contaPreterita');" id="0">Todos</a></strong>
						</td>
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
							<div align="center"><strong>Acrés. Impont.</strong></div>
						</td>
						<td width="20%">
							<div align="center"><strong>Situação</strong></div>
						</td>

					</tr>
					</table>

				</td>
			</tr>
			
			


			<logic:present name="colecaoContaPreteritos">
			
			<logic:notEmpty name="colecaoContaPreteritos">

			<tr>
				<td>
										
					<% cor = "#cbe5fe";%>

					<div style="width: 100%; height: 100; overflow: auto;">
										
					<table width="100%" align="center" bgcolor="#90c7fc">
										
					<logic:iterate name="colecaoContaPreteritos" id="conta" type="ContaValoresHelper">

						<%valorTotalContaPreteritos = valorTotalContaPreteritos.add(conta.getValorTotalConta()); %>
						<%valorTotalAcrescimoPreterito = valorTotalAcrescimoPreterito.add(conta.getValorTotalContaValoresParcelamento()); %>
						
						<%	if (cor.equalsIgnoreCase("#cbe5fe")){
							cor = "#FFFFFF";%>
							<tr bgcolor="#FFFFFF">
						<%} else{
							cor = "#cbe5fe";%>
							<tr bgcolor="#cbe5fe">
						<%}%>
											
												
						<td align="center" width="5%">
							<INPUT TYPE="checkbox" NAME="contaPreterita" value="<%="" + conta.getConta().getId().intValue() %>" 
							alt="<%="" + Util.formatarMoedaReal(conta.getValorTotalConta()).trim()%>
							<%=";" + Util.formatarMoedaReal(conta.getValorTotalContaValoresParcelamento()).trim()%>"
							onclick="totalizarDebito(this);"> 
						</td>
						
						<logic:empty name="conta" property="conta.contaMotivoRevisao">
						
							<td width="15%" align="center">
							<font color="#000000"> 
								<a href="javascript:abrirPopup('exibirConsultarContaAction.do?contaID=<%="" + conta.getConta().getId() %>&tipoConsulta=conta', 600, 800);">
								<%=""+ Util.formatarMesAnoReferencia(conta.getConta().getReferencia())%> </a>
							</font>	
							</td>
							<td width="20%">
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
							<td width="20%">
								<div align="right">
									<span style="color: #000000;">
										<%="" + Util.formatarMoedaReal(new BigDecimal(conta.getConta().getValorTotalConta())).trim()%>
									</span>
								</div>
								
							</td>
							<td width="20%">
								<div align="right">
									
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
							<td width="20%">
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
							<td width="15%" align="center">
								<font color="#ff0000"> 
									<a href="javascript:abrirPopup('exibirConsultarContaAction.do?contaID=<%="" + conta.getConta().getId() %>&tipoConsulta=conta', 600, 800);">
									<font color="#ff0000"><%=""+ Util.formatarMesAnoReferencia(conta.getConta().getReferencia())%> </font></a>
								</font>
							</td>
							<td width="20%">
								<div align="center">
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
							<td width="20%">
								<div align="right">
									<span style="color: #ff0000;">
										<%="" + Util.formatarMoedaReal(new BigDecimal(conta.getConta().getValorTotalConta())).trim()%>
									</span>
								</div>
							</td>
							<td width="20%">
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
							<td width="20%">
								<div align="center">
														
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
					
						<%if (cor.equalsIgnoreCase("#cbe5fe")){
							cor = "#FFFFFF";%>
							<tr bgcolor="#FFFFFF">
						<%} else{
							cor = "#cbe5fe";%>
							<tr bgcolor="#cbe5fe">
						<%}%>

						<td width="5%" height="20"></td>
						<td width="15%">
							<div align="center"><strong>Total:</strong></div>
						</td>
						<td width="20%"></td>
						<td width="20%">
							<div align="right"><strong>
								<%="" + Util.formatarMoedaReal(valorTotalContaPreteritos).trim()%>
							</strong></div>
						</td>
						<td width="20%">
							<div align="right"><strong>
								<%="" + Util.formatarMoedaReal(valorTotalAcrescimoPreterito).trim()%>
							</strong></div>
						</td>
						<td width="20%"></td>

					</tr>
										
					</table>
										
					</div>
					
				</td>
			</tr>

			</logic:notEmpty>
			
			</logic:present>
			
			<input TYPE="hidden" NAME="valorTotalContaPreterita" ID="valorTotalContaPreterita" value="<%= "" + valorTotalContaPreteritos %>"/>
			<input type="hidden" name="valorTotalAcrescimoPreterito" ID="valorTotalContaPreterita" value="<%="" + valorTotalAcrescimoPreterito %>">
			
			</table>
			
			
			<table width="100%" cellpadding="0" cellspacing="0">
			<tr>
				<td HEIGHT="5"></td>
			</tr>
			</table>	
				
			<table width="100%" cellpadding="0" cellspacing="0">
			<tr>
				<td>
				
					<table width="100%" cellpadding="0" cellspacing="0">
					<tr bgcolor="#79bbfd">
						<td colspan="7" height="20"><strong>Débitos A Cobrar</strong></td>
					</tr>
					<tr bgcolor="#90c7fc">

						<td width="5%" height="20">
							<strong><a href="javascript:facilitador(document.forms[0].checkDebito,'debito');"id="0">Todos</a></strong>
						</td>
						<td width="35%">
							<div align="center"><strong>Tipo do Débito</strong></div>
						</td>
						<td width="15%">
							<div align="center"><strong>Mês/Ano Ref.</strong></div>
						</td>
						<td width="15%">
							<div align="center"><strong>Mês/Ano Cobr.</strong></div>
						</td>
						<td width="15%">
							<div align="center"><strong>Prestação</strong></div>
						</td>
						<td width="15%">
							<div align="center"><strong>Vl. Restante</strong></div>
						</td>

					</tr>
					</table>

				</td>
			</tr>
			
			
			<%BigDecimal valorTotalDebito = new BigDecimal("0.00");%>
			
			<logic:present name="colecaoDebitoACobrar">
			
			<logic:notEmpty name="colecaoDebitoACobrar">

			<tr>
				<td>
										
					<% String cor1 = "#cbe5fe";%>

					<div style="width: 100%; height: 100; overflow: auto;">
										
					<table width="100%" align="center" bgcolor="#90c7fc">
										
					<logic:iterate name="colecaoDebitoACobrar" id="debitoACobrar" type="DebitoACobrar">

						<%valorTotalDebito = valorTotalDebito.add(debitoACobrar.getValorTotalComBonus()); %>
						
						<%	if (cor1.equalsIgnoreCase("#cbe5fe")){
							cor1 = "#FFFFFF";%>
							<tr bgcolor="#FFFFFF">
						<%} else{
							cor1 = "#cbe5fe";%>
							<tr bgcolor="#cbe5fe">
						<%}%>
												
												
						<td align="center" width="5%">
							<INPUT TYPE="checkbox" NAME="debito"
							value="<%="" + debitoACobrar.getId().intValue() %>"
							alt="<%="" + Util.formatarMoedaReal(debitoACobrar.getValorTotalComBonus()).trim()%>"
							onclick="totalizarDebito(this);"
							>
						</td>
						<td width="35%" align="left">
							<a href="javascript:abrirPopup('exibirConsultarDebitoACobrarAction.do?imovelID=<%="" + debitoACobrar.getImovel().getId().intValue() %>&debitoID=<%="" + debitoACobrar.getId().intValue() %>', 570, 720);">
							<bean:write name="debitoACobrar" property="debitoTipo.descricao" /></a>
						</td>
						<td width="15%">
							<div align="center">
							
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
						<td width="15%">
							<div align="center">
								
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
						<td width="15%">
							<div align="center">
								
								<bean:write name="debitoACobrar" property="numeroPrestacaoCobradas" /> /
								<bean:write name="debitoACobrar" property="numeroPrestacaoDebitoMenosBonus" />
								
							</div>
						</td>
						<td width="15%">
							<div align="right">
													
								<%="" + Util.formatarMoedaReal(debitoACobrar.getValorTotalComBonus()).trim()%>
													
							</div>
						</td>
					</tr>
			
					</logic:iterate>
					
						<%if (cor1.equalsIgnoreCase("#cbe5fe")){
							cor1 = "#FFFFFF";%>
							<tr bgcolor="#FFFFFF">
						<%} else{
							cor1 = "#cbe5fe";%>
							<tr bgcolor="#cbe5fe">
						<%}%>

						<td width="5%" height="20"></td>
						<td width="20%">
							<div align="center"><strong>Total:</strong></div>
						</td>
						<td width="15%"></td>
						<td width="15%"></td>
						<td width="15%"></td>
						<td width="15%">
							<div align="right">
								<strong>				
								<%="" + Util.formatarMoedaReal(valorTotalDebito).trim()%>
								</strong>				
							</div>
						</td>
						<td width="15%"></td>

					</tr>
										
					</table>
										
					</div>
					<input type="hidden" name="valorTotalDebito" ID="valorTotalDebito" value="<%="" + valorTotalDebito %>">
				</td>
			</tr>

			</logic:notEmpty>
			
			</logic:present>

			</table>
			
			<table width="100%" cellpadding="0" cellspacing="0">
			<tr>
				<td HEIGHT="5"></td>
			</tr>
			</table>
			
			<table width="100%" cellpadding="0" cellspacing="0">
			<tr>
				<td HEIGHT="5"></td>
			</tr>
			</table>
			
			
			<table width="100%" cellpadding="0" cellspacing="0">
			<tr>
				<td>
				
					<table width="100%" cellpadding="0" cellspacing="0">
					<tr bgcolor="#79bbfd">
						<td colspan="6" height="20"><strong>Guias de Pagamento</strong></td>
					</tr>
					<tr bgcolor="#90c7fc">

						<td width="5%" height="20">
							<strong><a href="javascript:facilitador(document.forms[0].checkGuia,'guiaPagamento');"id="0">Todos</a></strong>
						</td>
						<td width="35%">
							<div align="center"><strong>Tipo do Débito</strong></div>
						</td>
						<td width="15%">
							<div align="center"><strong>Prestação</strong></div>
						</td>
						<td width="15%">
							<div align="center"><strong>Emissão</strong></div>
						</td>
						<td width="15%">
							<div align="center"><strong>Vencimento</strong></div>
						</td>
						<td width="15%">
							<div align="center"><strong>Valor</strong></div>
						</td>

					</tr>
					</table>

				</td>
			</tr>
			
			<%BigDecimal valorTotalGuiaPagamento = new BigDecimal("0.00");%>
			
			<logic:present name="colecaoGuiaPagamento">
			
			<logic:notEmpty name="colecaoGuiaPagamento">

			<tr>
				<td>
										
					<% String cor3 = "#cbe5fe";%>

					<div style="width: 100%; height: 100; overflow: auto;">
										
					<table width="100%" align="center" bgcolor="#90c7fc">
										
					<logic:iterate name="colecaoGuiaPagamento" id="guiaPagamentoValoresHelper" type="GuiaPagamentoValoresHelper">

						<%valorTotalGuiaPagamento = valorTotalGuiaPagamento.add(guiaPagamentoValoresHelper.getGuiaPagamento().getValorDebito()); %>
						
						<%	if (cor3.equalsIgnoreCase("#cbe5fe")){
							cor3 = "#FFFFFF";%>
							<tr bgcolor="#FFFFFF">
						<%} else{
							cor3 = "#cbe5fe";%>
							<tr bgcolor="#cbe5fe">
						<%}%>
												
												
						<td align="center" width="5%">
							<INPUT TYPE="checkbox" NAME="guiaPagamento"
							value="<%="" + guiaPagamentoValoresHelper.getGuiaPagamento().getId().intValue() %>"
							alt="<%="" + Util.formatarMoedaReal(guiaPagamentoValoresHelper.getGuiaPagamento().getValorDebito()).trim()%>"
							onclick="totalizarDebito(this);">
						</td>
						<td width="35%" align="left">
							<a href="javascript:abrirPopup('exibirConsultarGuiaPagamentoAction.do?guiaPagamentoId=<%="" + guiaPagamentoValoresHelper.getGuiaPagamento().getId().intValue() %>', 600, 800);">
							<bean:write name="guiaPagamentoValoresHelper" property="guiaPagamento.debitoTipo.descricao" /></a>
						</td>
						<td width="15%">
							<div align="center">
								
								<%="" + guiaPagamentoValoresHelper.getGuiaPagamento().getPrestacaoFormatada()%>
								
							</div>
						</td>
						
						<td width="15%">
							<div align="center">
							
								<logic:present name="guiaPagamentoValoresHelper" property="guiaPagamento.dataEmissao">
									<span style="color: #000000;">
										<%="" + Util.formatarData(guiaPagamentoValoresHelper.getGuiaPagamento().getDataEmissao())%>
									</span>
								</logic:present> 
								
								<logic:notPresent name="guiaPagamentoValoresHelper" property="guiaPagamento.dataEmissao">
									&nbsp;
								</logic:notPresent>	
							
							</div>
						</td>
						<td width="15%">
							<div align="center">
								
								<logic:present name="guiaPagamentoValoresHelper" property="guiaPagamento.dataVencimento">
									<span style="color: #000000;">
										<%="" + Util.formatarData(guiaPagamentoValoresHelper.getGuiaPagamento().getDataVencimento())%>
									</span>
								</logic:present> 
								
								<logic:notPresent name="guiaPagamentoValoresHelper" property="guiaPagamento.dataVencimento">
									&nbsp;
								</logic:notPresent>	
							
							</div>
						</td>
						<td width="15%">
							<div align="right">
								
								<%="" + Util.formatarMoedaReal(guiaPagamentoValoresHelper.getGuiaPagamento().getValorDebito()).trim()%>
								
							</div>
						</td>
					</tr>
			
					</logic:iterate>
					
						<%if (cor3.equalsIgnoreCase("#cbe5fe")){
							cor3 = "#FFFFFF";%>
							<tr bgcolor="#FFFFFF">
						<%} else{
							cor3 = "#cbe5fe";%>
							<tr bgcolor="#cbe5fe">
						<%}%>

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
										
					</table>
										
					</div>
					<input type="hidden" name="valorTotalGuiaPagamento" ID="valorTotalGuiaPagamento" value="<%="" + valorTotalGuiaPagamento %>">
				</td>
			</tr>

			</logic:notEmpty>
			
			</logic:present>

			</table>
			
			
			
			<table width="100%" cellpadding="0" cellspacing="0">
			<tr>
				<td HEIGHT="5"></td>
			</tr>
			</table>
			
			
			
		<table width="100%" cellpadding="0" cellspacing="0">
			<tr>
				<td>
				
					<table width="100%" cellpadding="0" cellspacing="0">
					<tr bgcolor="#79bbfd">
						<td colspan="5" height="20"><strong>Parcelamento</strong></td>
					</tr>
					<tr bgcolor="#90c7fc">
						<td width="5%" height="20">
							<strong><a href="javascript:facilitador(document.forms[0].checkParcelamento,'parcelamento');"id="0">Todos</a></strong>
						</td>
						<td width="50%">
							<div align="center"><strong>Data</strong></div>
						</td>
						<td width="15%">
							<div align="center"><strong>Prestação</strong></div>
						</td>
						<td width="15%">
							<div align="center"><strong>Vl. Restante</strong></div>
						</td>
						<td width="15%">
							<div align="center"><strong>Antecipação</strong></div>
						</td>
					</table>

				</td>
			</tr>
			
			
			<%BigDecimal valorTotalParcelamento = new BigDecimal("0.00");%>

			<logic:present name="colecaoDebitoCreditoParcelamento">
			
			<logic:notEmpty name="colecaoDebitoCreditoParcelamento">

			<tr>
				<td>
										
					<% String cor0 = "#cbe5fe";%>

					<div style="width: 100%; height: 100; overflow: auto;">
										
					<table width="100%" align="center" bgcolor="#90c7fc">
										
					<logic:iterate name="colecaoDebitoCreditoParcelamento" id="debitoCredito" type="DebitoCreditoParcelamentoHelper">
						
						<%valorTotalParcelamento = valorTotalParcelamento.add(debitoCredito.getValorTotal()); %>
						
						<%	if (cor0.equalsIgnoreCase("#cbe5fe")){
							cor0 = "#FFFFFF";%>
							<tr bgcolor="#FFFFFF">
						<%} else{
							cor0 = "#cbe5fe";%>
							<tr bgcolor="#cbe5fe">
						<%}%>
												
												
						<td align="center" width="5%">
							<INPUT TYPE="checkbox" NAME="parcelamento"
							value="<%="" + debitoCredito.getParcelamento().getId().intValue() %>"
							alt="<%="" + Util.formatarMoedaReal(debitoCredito.getValorTotal()).trim()%>"
							onclick="controleCampoAntecipacaoParcela(this);totalizarDebito(this);">
						</td>
						
						<td width="50%" align="center">
						
							<div align="left">
								<a href="javascript:abrirPopup('exibirConsultarParcelamentoDebitoPopupAction.do?
								codigoImovel=<bean:define name="debitoCredito" property="parcelamento.imovel" id="imovel" />
								<bean:write name="imovel" property="id" />
								&codigoParcelamento=<bean:write name="debitoCredito" property="parcelamento.id" />', 400, 800);">
								<bean:write name="debitoCredito" property="parcelamento.ultimaAlteracao" formatKey="date.format" /></a>
							</div>
						
						</td>
						
						<td width="15%">
							<div align="center">
								
								<%="" + debitoCredito.getPrestacaoFormatada()%>
								
							</div>
						</td>

						<td width="15%">
							<div align="right">
								<span style="color: #000000;">
									<%="" + Util.formatarMoedaReal(debitoCredito.getValorTotal()).trim()%>
								</span>
							</div>
						</td>
						
						<td width="15%">
							<div align="center">
								
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
					
						<%if (cor0.equalsIgnoreCase("#cbe5fe")){
							cor0 = "#FFFFFF";%>
							<tr bgcolor="#FFFFFF">
						<%} else{
							cor0 = "#cbe5fe";%>
							<tr bgcolor="#cbe5fe">
						<%}%>

						<td width="5%" height="20"></td>
						<td width="50%">
							<div align="center"><strong>Total:</strong></div>
						</td>
						<td width="15%"></td>
						<td width="15%">
							<div align="right"><strong>
								<%="" + Util.formatarMoedaReal(valorTotalParcelamento).trim()%>
							</strong></div>
						</td>
						<td width="15%"></td>
						

					</tr>
										
					</table>
										
					</div>
					<input type="hidden" name="valorTotalParcelamento" ID="valorTotalParcelamento" value="<%="" + valorTotalParcelamento %>">
				</td>
			</tr>

			</logic:notEmpty>
			
			</logic:present>
			
			</table>
			
		</td>
		
	</tr>
	
	</table>
	
	<table width="100%" border="0">
	<tr>
		<td HEIGHT="10"></td>
	</tr>
	</table>
	
	<%BigDecimal valorTotalDebitosFinal = valorTotalConta.add(valorTotalDebito); %>
	<%valorTotalDebitosFinal = valorTotalDebitosFinal.add(valorTotalContaPreteritos); %>
	<%valorTotalDebitosFinal = valorTotalDebitosFinal.add(valorTotalGuiaPagamento); %>
	<%valorTotalDebitosFinal = valorTotalDebitosFinal.add(valorTotalParcelamento); %>
	
	
	<%BigDecimal valorTotalDebitosAtualizado = valorTotalDebitosFinal.add(valorTotalAcrescimo); %>
	<%valorTotalDebitosAtualizado = valorTotalDebitosAtualizado.add(valorTotalAcrescimoPreterito); %>
	
	<table width="100%" border="0">
	<tr>
		<td HEIGHT="20"><strong>Total dos Débitos:</strong></td>
		<td><div align="right"><strong><%="" + Util.formatarMoedaReal(valorTotalDebitosFinal).trim()%></strong></div></td>
	</tr>
	<tr>
		<td HEIGHT="20"><strong>Total dos Débitos Atualizados:</strong></td>
		<td><div align="right"><strong><%="" + Util.formatarMoedaReal(valorTotalDebitosAtualizado).trim()%></strong></div></td>
	</tr>

	<tr>
		<td HEIGHT="20"><strong>Total de Débitos Selecionados:</strong></td>
		<td><div align="right" style="font-size: 12"><strong><span id="totalDebitoSelecionado">0,00</span></strong></div></td>
		
	</tr>
	<tr>
		<td HEIGHT="20"><strong>Total de Débitos Acumulados Selecionados:</strong></td>
		<td><div align="right" style="font-size: 12"><strong><span id="totalDebitoAtualizadoSelecionado">0,00</span></strong></div></td>
		
	</tr>
	</table>
	
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
	 
	 <table>
		<tr>
			<td>
			
			<logic:present name="habilitaIncluirDebito" >
				<a href="javascript:abrirPopup('exibirInserirDebitoACobrarPopupAction.do?limparForm=1', 350, 610);">
				<strong><%="Incluir Débito"%> </strong></a>
			</logic:present>
			
			<logic:notPresent name="habilitaIncluirDebito" >
				<a>
				<strong><%="Incluir Débito"%> </strong></a>
			</logic:notPresent>
				
			</td>
		
		</tr>
	</table>
	 
	 	<table width="100%" border="0">
	<tr>
		<td HEIGHT="10"></td>
	</tr>
	</table>
	 
	 
	 <logic:notPresent name="botaoCalcularDesconto">
		 <table width="100%" border="0">
			<tr>
				<td width="30%"><strong>Acréscimos Impontualidade:</strong></td>
				<td><strong> <span class="style1"><strong> 
				<html:radio	property="indicadorIncluirAcrescimosImpontualidade" value="1" /> <strong>Incluir
				<html:radio property="indicadorIncluirAcrescimosImpontualidade" value="2" /> Não Incluir
				
				<logic:equal name="temPermissaoIncluirAcrescExtratoComDesconto" value="true">
					<html:radio property="indicadorIncluirAcrescimosImpontualidade" value="3" /> Incluir com Desconto
				</logic:equal>
				
				</strong></strong></span></strong></td>
			</tr>
		</table>
	 </logic:notPresent>
	 
	<logic:present name="botaoCalcularDesconto">
		<logic:notEqual name="botaoCalcularDesconto" value="true"> 
			 <table width="100%" border="0">
				<tr>
					<td width="30%"><strong>Acréscimos Impontualidade:</strong></td>
					<td><strong> <span class="style1"><strong> 
					<html:radio	property="indicadorIncluirAcrescimosImpontualidade" value="1" /> <strong>Incluir
					<html:radio property="indicadorIncluirAcrescimosImpontualidade" value="2" /> Não Incluir
					
					<logic:equal name="temPermissaoIncluirAcrescExtratoComDesconto" value="true">
						<html:radio property="indicadorIncluirAcrescimosImpontualidade" value="3" /> Incluir com Desconto
					</logic:equal>
					
					</strong></strong></span></strong></td>
				</tr>
			</table>
		</logic:notEqual>
	</logic:present>
	
	<logic:equal name="temPermissaoRetirarTaxaCobrancaDoExtratoDeDebitos" value="true">
		<table width="100%" border="0">
			<tr>
				<td width="30%"><strong>Cobrar Taxa de Cobrança:</strong></td>
				<td><strong> <span class="style1"><strong> 
				<html:radio	property="indicadorTaxaCobranca" value="1" /> <strong>Sim
				<html:radio property="indicadorTaxaCobranca" value="2" /> Não
				</strong></strong></span></strong></td>
			</tr>
		</table>
	</logic:equal>
	
	<logic:equal name="temPermissaoResolucaoDiretoria" value="true">
	<table width="100%" border="0">
		<tr> 
			<td><strong>Com Restabelecimento?</strong></td>
			<td>
				<strong>
					<html:radio property="indicadorRestabelecimento" value="1"/>Sim
					<html:radio property="indicadorRestabelecimento" value="2"/>N&atilde;o
				</strong>
			</td>
		</tr>
		</table>
	</logic:equal>
			
	<logic:equal name="botaoCalcularDesconto" value="true">
	<table width="100%" border="0">
		<tr>
			<td>
				<input name="Button" type="button" class="bottonRightCol"
				value="Calcular Desconto" align="left"
				onclick="javascript:calcularDesconto();">
			</td>
		</tr>	

		<tr>
			<td HEIGHT="20"><strong>Valor do Desconto para Pagamento à Vista:</strong></td>
			<td><div align="right"><strong><bean:write name="DebitoCreditoDadosSelecaoExtratoActionForm" 
									property="valorDescontoPagamentoAVista"/></strong></div></td>
		</tr>
		<tr>
			<td HEIGHT="20"><strong>Valor do Pagamento à Vista:</strong></td>
			<td><div align="right"><strong><bean:write name="DebitoCreditoDadosSelecaoExtratoActionForm" 
									property="valorPagamentoAVista"/></strong></div></td>
		</tr>		

		</table>
		
	</logic:equal>		
	
		
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
		<td>
			<input name="Button" type="button" class="bottonRightCol"
			value="Desfazer" align="left"
			onclick="javascript:limparImovel();">
			<input name="Button" type="button" class="bottonRightCol"
			value="Cancelar" align="left" onclick="javascript:window.location.href='/gsan/telaPrincipal.do'">
		</td>
	
		<td align="right">
			<input type="button" name="" value="Imprimir" class="bottonRightCol" 
			onclick="javascript:debitosCreditosSelecionados(document.forms[0],'/gsan/emissaoExtratoDebitoAction.do?','&extratoDebito=1');"
			url="emissaoRelatorioExtratoDebitoAction.do?extratoDebito=1"/>
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
