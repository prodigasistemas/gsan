<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>

<%@ page import="gcom.cobranca.bean.ParcelamentoCartaoCreditoHelper"%>
<%@ page import="gcom.cobranca.parcelamento.Parcelamento"%>
<%@ page import="gcom.util.ConstantesSistema"%>

<%@ page import="gcom.util.Util"%>
<%@ page import="java.math.BigDecimal"%>
<%@ page import="gcom.cobranca.bean.ContaValoresHelper"%>
<%@ page import="gcom.faturamento.debito.DebitoACobrar"%>
<%@ page import="gcom.cobranca.bean.GuiaPagamentoValoresHelper"%>
<%@ page import="gcom.cobranca.bean.DebitoCreditoParcelamentoHelper"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">

<html:javascript staticJavascript="false" formName="ParcelamentoCartaoConfirmarForm" />

<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>

<script language="JavaScript">

function totalizarDebitosSelecionados(){
	
	form = document.forms[0];
	
	validaCheck();
	
	for(indice = 0; indice < form.elements.length; indice++){
		
		if ((form.elements[indice].type == "checkbox" && form.elements[indice].checked == true) ||
			(form.elements[indice].type == "hidden" && form.elements[indice].name.substr(0,16) == "valorAntecipacao" &&
			form.elements[indice].value != "0,00")) {
			
			totalizarDebito(form.elements[indice]);
		}
	}
}

function totalizarDebito(objeto){
	
	var objetoTotalDebitoSelecionado = document.getElementById("totalDebitoSelecionado");
	
	//VALOR TOTAL
	var valorTotalSelecionado = objetoTotalDebitoSelecionado.innerHTML;
	valorTotalSelecionado = valorTotalSelecionado.replace(".","");
	valorTotalSelecionado = valorTotalSelecionado.replace(",",".");
	
	//VALOR SELECIONADO
	var valorSelecionado = objeto.alt;
	valorSelecionado = valorSelecionado.replace(".","");
	valorSelecionado = valorSelecionado.replace(",",".");
	
	if ((objeto.name.substr(0,16) == "valorAntecipacao") || (objeto.checked == true)){
		valorTotalSelecionado = (valorTotalSelecionado * 1) + (valorSelecionado * 1);
	}
	else{
		valorTotalSelecionado = (valorTotalSelecionado * 1) - (valorSelecionado * 1); 
	}
	
	objetoTotalDebitoSelecionado.innerHTML = formatarValorMonetario(valorTotalSelecionado);
}

function validarForm(){

	 var form = document.forms[0];	
     
     var msg = "";
     var retorno = true;
      
	 var modalidadeCartao = obterValorRadioMarcadoPorNome('modalidadeCartao');
	 var matricula = form.matriculaImovel.value;
	 
	 if (modalidadeCartao.length < 1){
	 	msg = "Informe Modalidade \n";
	 	retorno = false;
	 }
	 
	 if (matricula.length < 1){
	 	msg = msg + "Informe Matrícula do Imóvel \n";
	 	retorno = false;
	 }
	 
	 if (retorno){
	 	
	 	var MODALIDADE_CREDITO = document.getElementById('modalidadeCredito').value;
	 	
	 	if (modalidadeCartao == MODALIDADE_CREDITO){
	 		validarCartaoCredito();
	 	}
	 	else if (validarCartaoDebito()){
	 		
	 		var objetoTotalDebitoSelecionado = document.getElementById("totalDebitoSelecionado");
	
			//VALOR TOTAL
			var valorTotalSelecionado = objetoTotalDebitoSelecionado.innerHTML;
			valorTotalSelecionado = valorTotalSelecionado.replace(".","");
			valorTotalSelecionado = valorTotalSelecionado.replace(",",".");
			
		
			botaoAvancarTelaEspera('/gsan/parcelamentoCartaoCreditoConfirmarAction.do?valorTotal=' + valorTotalSelecionado);
			
	 	}
	 	
	 }
	 else{
	 	alert(msg);
	 }
}

function validarCartaoCredito(){
	
	form = document.forms[0];

	if (form.idParcelamento == null || form.idParcelamento == undefined){
		alert("Selecione um Parcelamento.");
	}
	else {
		form.action = "parcelamentoCartaoCreditoConfirmarAction.do";
 		form.submit();	
	}
}

function validarCartaoDebito(){
	
	retorno = false;
	
	for(indice = 0; indice < form.elements.length; indice++){
			
		if (form.elements[indice].type == "checkbox" && form.elements[indice].checked == true) {
			retorno = true;
			break;
		}
		else if (form.elements[indice].type == "checkbox" && form.elements[indice].checked == false &&
				form.elements[indice].name == 'parcelamentoDebito'){
				
			var campoAntecipacao = eval('parc' + form.elements[indice].value);
			
			if (campoAntecipacao.value.length > 0){
				retorno = true;
				break;
			}
		}	
	}
		
	if (!retorno){
		alert('Selecione um Débito.'); 
	}
	else if (!validarCamposDinamicos(form)){
		
		retorno = false;
	}
	
	return retorno;
}

function adicionarTransacaoCartaoDebito(){
	
	if (validarCartaoDebito()){
		
		abrirPopupComSubmit('exibirAdicionarPagamentoCartaoCredito.do?inicio=sim',450,532,'Adicionar');
	}
}

function modificarModalidade(){
	
	form = document.forms[0];
	
	var matricula = form.matriculaImovel.value;
	
	if (matricula.length > 0){
		form.action = "exibirParcelamentoCartaoCreditoConfirmarAction.do?novoImovel=OK";
 		form.submit();	
	}
}

function extendeTabela(tabela,display){
	var form = document.forms[0];

	if(display){
 		eval('layerHide'+tabela).style.display = 'none';
 		eval('layerShow'+tabela).style.display = 'block';
	}else{
		eval('layerHide'+tabela).style.display = 'block';
 		eval('layerShow'+tabela).style.display = 'none';
	}
}

function validarImovel(tecla){

	 var form = document.forms[0];
	 
	 var modalidadeCartao = obterValorRadioMarcadoPorNome('modalidadeCartao');
	 var modalidadeCartaoAuxiliar = document.getElementById('modalidadeCartaoAuxiliar');
	 modalidadeCartaoAuxiliar.value = modalidadeCartao;
	 
	 validaEnterDependenciaComMensagem(tecla,'exibirParcelamentoCartaoCreditoConfirmarAction.do?novoImovel=OK', 
	 form.matriculaImovel, modalidadeCartaoAuxiliar.value, 'Modalidade', 'Matrícula do Imóvel');
}


function chamarPopup(url, tipo, objeto, codigoObjeto, altura, largura, msg,objetoRelacionado){
	
	if(objetoRelacionado.disabled != true){
		
		if (objeto == null || codigoObjeto == null){
			abrirPopup(url + "?" + "tipo=" + tipo, altura, largura);
		} 
		else if (codigoObjeto.length < 1 || isNaN(codigoObjeto)){
			alert(msg);
		} 
		else{
			abrirPopup(url + "?" + "tipo=" + tipo + "&" + objeto + "=" + codigoObjeto + "&caminhoRetornoTelaPesquisa=" + tipo, altura, largura);
		}
	}
}

function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
	var form = document.forms[0];
			
	if (tipoConsulta == 'imovel') {
	  form.matriculaImovel.value = codigoRegistro;
      form.inscricaoImovel.value = descricaoRegistro;
      form.inscricaoImovel.style.color = '#000000';	      
	}
	 
	if (tipoConsulta == 'cliente') {
      limparPesquisaCliente();
      form.idCliente.value = codigoRegistro;
      form.nomeCliente.value = descricaoRegistro;
      form.nomeCliente.style.color = "#000000";
    }
}

function limparFormImovel(){
	
	var form = document.forms[0];
	
	form.matriculaImovel.value = "";
	form.inscricaoImovel.value = "";
	form.enderecoImovel.value = "";
	
	desfazer();
}

function verficarSelecao(){
	var form = document.forms[0];

	for(indice = 0; indice < form.elements.length; indice++){
		if (form.elements[indice].type == "radio" && form.elements[indice].checked == true) {
			form.action='exibirParcelamentoCartaoCreditoConfirmarAction.do?numeroParcelamento='+form.elements[indice].value;
			form.submit();
		}
	}
}

function selecionarParcelamento(ParcelamemtoSelecionado){

	var form = document.forms[0];
	for(indice = 0; indice < form.elements.length; indice++){
		if (form.elements[indice].type == "radio" && form.elements[indice].value == ParcelamemtoSelecionado ) {
			form.elements[indice].checked = true;
		}
	}

}
function limparPesquisaCliente() {
    var form = document.forms[0];

      form.idCliente.value = "";
      form.nomeCliente.value = "";
}

function validarRadio(){

 	var form = document.forms[0];

		if (RadioNaoVazioMensagem("Parcelamento", "")){
			verficarSelecao();
		}
}

function desfazerImovel(tecla,nomeCampoForm) {

	var form = document.forms[0];
	var objetoCampo = eval("form." + nomeCampoForm);

	if (document.all) {
		var codigo = event.keyCode;
    }
	else {
       var codigo = tecla.which;
    }

	//|| (codigo>=48 && codigo<=90)
    
	if (codigo == 8 ) {
		var form = document.forms[0];
	 	form.action='exibirParcelamentoCartaoCreditoConfirmarAction.do?menu=sim&limpar=sim';
	 	form.submit();

	}
	
}

function desfazer(){
 	
 	var form = document.forms[0];
 	form.action='exibirParcelamentoCartaoCreditoConfirmarAction.do?menu=sim&limpar=sim';
 	form.submit();
 	
}

function setarFocus(){
	var form = document.forms[0];
	
	if(form.validadeCartaoInvalida.value=='true'){
		form.validadeCartao.value=="";
	}
	
	for(indice = 0; indice < form.elements.length; indice++){
			
		if (form.elements[indice].type == "radio" && form.elements[indice].name == "idParcelamento") {
			if(form.elements[indice].value==form.idParcelamento.value){
				form.elements[indice].checked=true;	
			}
		}
	}
	
	if(form.matriculaImovel.value==""){
		form.matriculaImovel.focus();
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

	function validaCheck(){
     	validaCheckConta();
	    validaCheckDebito();
	    validaCheckGuia();
	    validaCheckParcelamento();
  	}
  	
  	
  	function validaCheckConta(){
    	var form = document.forms[0];  	
  	
  		if (form.idsConta.value.length > 0){
  			
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
  	}
  	
  	function validaCheckDebito(){
    	var form = document.forms[0];  	
  	
  		if (form.idsDebito.value.length > 0){
  			
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
  	}
  	
  	function validaCheckGuia(){
    	var form = document.forms[0];  	
  	
  		if (form.idsGuia.value.length > 0){
  			
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
  	}
  	
  	function validaCheckParcelamento(){
    	var form = document.forms[0];  	
  	
  		if (form.idsParcelamentoDebito.value.length > 0){
  			
  			var idParcelamentos = form.idsParcelamentoDebito.value;
			myString = new String(idParcelamentos);
			splitString = myString.split(",");
			
			for (i=0; i< splitString.length; i++) {
				chave  = splitString[i];
				for(indice = 0; indice < form.elements.length; indice++){
					if (form.elements[indice].type == "checkbox" && 
						form.elements[indice].name == 'parcelamentoDebito' && 
						form.elements[indice].value.trim() == chave.trim()) {
	
						form.elements[indice].checked = true;
					}
				}
			}
  		}
  	}
  	
  	function controleCampoAntecipacaoParcela(objeto){
	
		if (objeto.checked == true){
			
			if (objeto.name == 'parcelamentoDebito'){
				
				var campoAntecipacao = eval('parc' + objeto.value);
				campoAntecipacao.value = "";
				campoAntecipacao.readOnly = true;
			}
		}
		else{
			
			if (objeto.name == 'parcelamentoDebito'){
				
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
			
				case "parcelamentoDebito":
					
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

function limparValorAntecipacao(idParcelamento){

	var objetoValorAntecipacao = eval('valorAntecipacao' + idParcelamento);
	
	if (objetoValorAntecipacao.value != "0,00"){
		
		var objetoTotalDebitoSelecionado = document.getElementById("totalDebitoSelecionado");
	
		//VALOR TOTAL
		var valorTotalSelecionado = objetoTotalDebitoSelecionado.innerHTML;
		valorTotalSelecionado = valorTotalSelecionado.replace(".","");
		valorTotalSelecionado = valorTotalSelecionado.replace(",",".");
		
		//VALOR SELECIONADO
		var valorSelecionado = objetoValorAntecipacao.value;
		valorSelecionado = valorSelecionado.replace(".","");
		valorSelecionado = valorSelecionado.replace(",",".");
		
		valorTotalSelecionado = (valorTotalSelecionado * 1) - (valorSelecionado * 1); 
		
		objetoValorAntecipacao.value = "0,00";
		objetoTotalDebitoSelecionado.innerHTML = formatarValorMonetario(valorTotalSelecionado);
	}
}

function validarQuantidadeAntecipacaoParcelas(idParcelamento){
	
	retorno = true;
	
	var campoAntecipacao = eval('parc' + idParcelamento);
	
	if (campoAntecipacao.value.length <= 0){
		
		alert('Informe Quantidade de Parcelas para Antecipação.'); 
		retorno = false;
	}
	else if (!validarCamposDinamicos(form)){
		
		retorno = false;
	}
	
	if (retorno){
		
		form.action = "exibirParcelamentoCartaoCreditoConfirmarAction.do";
 		form.submit();	
	}
}
	  	
</script>
</head>

<body leftmargin="5" topmargin="5" onload="setarFocus();selecionarParcelamento('${sessionScope.ParcelamemtoSelecionado}');">
<div id="formDiv">
<html:form action="/exibirParcelamentoCartaoCreditoConfirmarAction.do"
	name="ParcelamentoCartaoConfirmarForm"
	type="gcom.gui.cobranca.ParcelamentoCartaoConfirmarForm" method="post">

	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>

	<html:hidden property="validadeCartaoInvalida" />
	
	<!-- COLOCADO APENAS PARA AUXILIAR NA VALIDAÇÃO DA FUNÇÃO "validarImovel" -->
	<INPUT TYPE=HIDDEN NAME="modalidadeCartaoAuxiliar" ID="modalidadeCartaoAuxiliar"/>
	<INPUT TYPE=HIDDEN NAME="modalidadeCredito" ID="modalidadeCredito" value="<%=""+ConstantesSistema.MODALIDADE_CARTAO_CREDITO%>"/>
	
	<!-- COLOCADO PARA VALIDAR AS INFORMAÇÕES PARA PAGAMENTO COM CARTÃO DE DÉBITO -->
	<input type="hidden" name="checkConta" value="0">
	<input type="hidden" name="checkCredito" value="0">
	<input type="hidden" name="checkDebito" value="0">
	<input type="hidden" name="checkGuia" value="0">
	<input type="hidden" name="checkParcelamento" value="0">
	
	<!-- COLOCADO PARA GUARDAR OS VALORES MARCADOS ATRAVÉS DOS CHECKBOXS -->
	<input type="hidden" name="idsConta" value="${requestScope.idsConta}">
	<input type="hidden" name="idsDebito" value="${requestScope.idsDebito}">
	<input type="hidden" name="idsGuia" value="${requestScope.idsGuia}">
	<input type="hidden" name="idsParcelamentoDebito" value="${requestScope.idsParcelamentoDebito}">
	
	
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
						<td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_left.gif" /></td>
						<td class="parabg">Confirmar Pagamento Cartão de Crédito/Débito</td>
						<td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
					</tr>
				</table>

				<p>&nbsp;</p>

				<table width="100%" border="0">
					<tr>
						<td>Para confirmar o pagamento de débitos por meio de cartão de crédito/débito informe os dados abaixo:</td>
					</tr>
				</table>

				<table width="100%" border="0">
				<tr>
					<td><strong><span class="style2">Modalidade</span>:<font color="#FF0000">*</font></strong></td>
					<td colspan="2">
						<html:radio property="modalidadeCartao" value="<%=""+ConstantesSistema.MODALIDADE_CARTAO_CREDITO%>" onclick="modificarModalidade();"/><strong>Crédito
						<html:radio property="modalidadeCartao" value="<%=""+ConstantesSistema.MODALIDADE_CARTAO_DEBITO%>" onclick="modificarModalidade();"/>Débito</strong>
					</td>
				</tr>
				<tr>
					<td><strong><span class="style2">Matr&iacute;cula do Im&oacute;vel</span>:<font color="#FF0000">*</font></strong></td>
					<td colspan="2">
						<html:text property="matriculaImovel" size="9" maxlength="9"
						onkeypress="javascript:desfazerImovel(event,'matriculaImovel');
						validarImovel(event);return isCampoNumerico(event);"/>
						
					<span class="style2"><strong> 
						
						<a href="javascript:chamarPopup('exibirPesquisarImovelAction.do', 'imovel', null, null, 
						570, 800, '',document.forms[0].matriculaImovel);">
						<img src="<bean:message key='caminho.imagens'/>pesquisa.gif"
						width="23" height="21" name="imagem" alt="Pesquisar" border="0"></a>
					
						<logic:present name="inscricaoImovelEncontrada" scope="session">
						
							<html:text property="inscricaoImovel" readonly="true"
							style="background-color:#EFEFEF; border:0; color: #000000"
							size="40" maxlength="40" />
						
						</logic:present> 
						
						<logic:notPresent name="inscricaoImovelEncontrada" scope="session">
						
							<html:text property="inscricaoImovel" readonly="true"
							style="background-color:#EFEFEF; border:0; color: #ff0000"
							size="40" maxlength="40" />
					
						</logic:notPresent> 
						
						<a href="javascript:desfazer();"> <img border="0" title="Apagar"
						src="<bean:message key='caminho.imagens'/>limparcampo.gif" /></a>
					
					</strong></span>
					
					</td>
				</tr>
				
				<!-- ENDEREÇO DO IMÓVEL -->
				<logic:present name="enderecoFormatado" scope="session">
					
					<tr>
						<td colspan="3">
						<table width="100%" align="center" bgcolor="#90c7fc" border="0">
							<tr>
								<td align="center"><strong>Endereço do Imóvel:</strong></td>
							</tr>
							<tr bgcolor="#FFFFFF">
								<td width="100%" align="center">
								<table width="100%" border="0">
									<tr>
										<td width="100%">
										<div align="center"><span id="endereco"> <logic:notEmpty
											name="ParcelamentoCartaoConfirmarForm"
											property="enderecoImovel">
											<bean:write name="ParcelamentoCartaoConfirmarForm"
												property="enderecoImovel" />
										</logic:notEmpty> </span></div>
										</td>
									</tr>
								</table>
								</td>
							</tr>
						</table>
						</td>
					</tr>
					
				</logic:present>
				<!-- FIM ENDEREÇO DO IMÓVEL -->

				
				<!-- PARCELAMENTO ATIVOS (PAGAMENTO COM CARTÃO DE CRÉDITO) -->
				<logic:present name="cartaoCredito" scope="session">
					
					<tr>
						<td colspan="3">
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td colspan="3" height="23"><strong>Relação de Parcelamentos
								Ativos:</strong></td>
							</tr>
							<tr>
								<td>
								<table width="100%" bgcolor="#90c7fc">
									<tr bgcolor="#90c7fc">
										<td width="10%" bgcolor="#90c7fc">
										<div align="center"><strong>&nbsp;</strong></div>
										</td>
										<td width="20%" bgcolor="#90c7fc">
										<div align="center"><strong>Referência</strong></div>
										</td>
										<td width="70%" bgcolor="#90c7fc">
										<div align="center"><strong>Usuário</strong></div>
										</td>
									</tr>

									<%String cor = "##cbe5fe";%>
									<logic:notEmpty name="colecaoParcelamentos" scope="session">
										<logic:present name="colecaoParcelamentos" scope="session">
											<logic:iterate name="colecaoParcelamentos" id="parcelamento"
												type="Parcelamento">
												<%if (cor.equalsIgnoreCase("#FFFFFF")) {
													cor = "#cbe5fe";%>
												<tr bgcolor="#cbe5fe">
													<%} else {
													cor = "#FFFFFF";%>
												<tr bgcolor="#FFFFFF">
													<%}%>
													<td width="10%">
													<div align="center"><input type="radio"
														name="idParcelamento"
														value="<bean:write name="parcelamento" property="id"/>" /></div>
													</td>
													<td width="20%" align="center"><a
														href="javascript:abrirPopup('exibirConsultarParcelamentoDebitoPopupAction.do?codigoImovel=
																<bean:write name="ParcelamentoCartaoConfirmarForm" property="matriculaImovel"/>
																&codigoParcelamento=
																<bean:write name="parcelamento" property="id" />'
																, 400, 800);">
													<bean:write name="parcelamento" property="parcelamento"
														formatKey="date.format" /> </a></td>
													<td width="70%"><logic:present name="parcelamento"
														property="usuario">
														<bean:write name="parcelamento"
															property="usuario.nomeUsuario" />
													</logic:present></td>
												</tr>
											</logic:iterate>
										</logic:present>
									</logic:notEmpty>
								</table>
								</td>
							</tr>

						</table>
						</td>
					</tr>
					<tr>
						<td colspan="3" align="right">
							<input type="button" name="selecionar" class="bottonRightCol" value="Selecionar"
							onclick="javascript:validarRadio();">
						</td>
					</tr>
					
				</logic:present>
				<!-- FIM PARCELAMENTO ATIVOS (PAGAMENTO COM CARTÃO DE CRÉDITO) -->
				
				
				<!-- PAGAMENTO COM CARTÃO DE CRÉDITO -->
				<logic:present name="ParcelamemtoSelecionado" scope="session">

					<tr>
						<td colspan="3">
						<table width="100%" align="center" bgcolor="#90c7fc" border="0">
							<tr bordercolor="#79bbfd">
								<td bgcolor="#79bbfd" align="center"><strong>Valores para Cartão
								de Crédito:</strong></td>
							</tr>
						</table>
						<table width="100%" align="center" bgcolor="#90c7fc" border="0">
							<tr bordercolor="#90c7fc">
								<td align="center" width="25%" bgcolor="#90c7fc"><strong>Quantidade
								Parcelas</strong></td>
								<td align="center" width="25%" bgcolor="#90c7fc"><strong>Valor
								Parcelas</strong></td>
								<td align="center" width="25%" bgcolor="#90c7fc"><strong>Valor
								Total Parcelado</strong></td>
								<td align="center" width="25%" bgcolor="#90c7fc"><strong>Data
								do Parcelamento</strong></td>
							</tr>
							<tr bordercolor="#90c7fc">
								<td align="center" width="25%" bgcolor="#FFFFFF"><span
									id="numeroPrestacoes"> <logic:notEmpty
									name="ParcelamentoCartaoConfirmarForm"
									property="numeroPrestacoes">
									<bean:write name="ParcelamentoCartaoConfirmarForm"
										property="numeroPrestacoes" />
								</logic:notEmpty> <logic:empty
									name="ParcelamentoCartaoConfirmarForm"
									property="numeroPrestacoes">
									0
								</logic:empty> </span></td>
								<td align="center" width="25%" bgcolor="#FFFFFF"><span
									id="valorPrestacao"> <logic:notEmpty
									name="ParcelamentoCartaoConfirmarForm"
									property="valorPrestacao">
									<bean:write name="ParcelamentoCartaoConfirmarForm"
										property="valorPrestacao" formatKey="money.format" />
								</logic:notEmpty> <logic:empty
									name="ParcelamentoCartaoConfirmarForm"
									property="valorPrestacao">
									0
								</logic:empty> </span></td>
								<td align="center" width="25%" bgcolor="#FFFFFF"><span
									id="valorTotal"> <logic:notEmpty
									name="ParcelamentoCartaoConfirmarForm" property="valorTotal">
									<bean:write name="ParcelamentoCartaoConfirmarForm"
										property="valorTotal" formatKey="money.format" />
								</logic:notEmpty> <logic:empty
									name="ParcelamentoCartaoConfirmarForm" property="valorTotal">
									0
								</logic:empty> </span></td>
								<td align="center" width="25%" bgcolor="#FFFFFF"><span
									id="parcelamento"> <logic:notEmpty
									name="ParcelamentoCartaoConfirmarForm" property="parcelamento">
									<bean:write name="ParcelamentoCartaoConfirmarForm"
										property="parcelamento" />
								</logic:notEmpty> <logic:empty
									name="ParcelamentoCartaoConfirmarForm" property="parcelamento">
									0
								</logic:empty> </span></td>
							</tr>
						</table>
						</td>
					</tr>

					<tr>
						<td colspan="3" height="5"></td>
					</tr>


					<tr>
						<td colspan="3">
						<table width="100%" border="0">
							<tr>
								<td height="17" colspan="3"><strong>Pagamentos confirmados para Cartão de Crédito:</strong></td>
								<td align="right"><input type="button" class="bottonRightCol"
									value="Adicionar" tabindex="11" style="width: 80px"
									onclick="abrirPopup('exibirAdicionarPagamentoCartaoCredito.do?inicio=sim',450,532);">
								</td>
							</tr>
							<tr>
								<td colspan="4">
								<table width="100%" cellpadding="0" cellspacing="0">
									<tr>
										<td>
										<table width="100%" bgcolor="#99CCFF">
											<tr bgcolor="#99CCFF">
												<td align="center" width="10%"><strong>Remover</strong></td>
												<td align="center" width="60%%"><strong>Número do Cartão</strong></td>
												<td align="center" width="30%"><strong>Valor da Transação</strong></td>
											</tr>
						<logic:present name="colecaoTransacao" scope="session">
								<%int cont = 0;%>
							<logic:iterate id="helper" 
								name="colecaoTransacao" type="ParcelamentoCartaoCreditoHelper">
								<%cont = cont + 1;
								if (cont % 2 == 0) {%>
								<tr bgcolor="#FFFFFF">
								<%} else {
							
								%>
								<tr bgcolor="#cbe5fe">
								<%}%>
									<td width="10%">
									<div align="center"><font color="#333333"> <img width="14"
										height="14" border="0"
										src="<bean:message key="caminho.imagens"/>Error.gif"
										onclick="javascript:if(confirm('Confirma remoção?')){redirecionarSubmitSemUpperCase('removerPagamentoCartaoCreditoAction.do?tempoInclusao=<bean:write name="helper" property="tempoInclusao"/>');}" />
									</font></div>
									</td>
									<td>
									<div align="center"><bean:write name="helper"
										property="numeroCartao" /></div>
									</td>
									<td>
									<div align="center"><bean:write name="helper"
										property="valorTransacao" /></div>
									</td>
								</tr>
							</logic:iterate>

						</logic:present>
											
										</table>
										</td>
									</tr>
								</table>
								</td>
							</tr>
						</table>
						</td>
					</tr>
					
				</logic:present>
				<!-- FIM PAGAMENTO COM CARTÃO DE CRÉDITO -->
				
			</table>
			
			<!-- PAGAMENTO COM CARTÃO DE DÉBITO -->
			<logic:present name="cartaoDebito" scope="session">
			
				<table width="100%" border="0">
				<tr>
					<td>
					
						<table width="100%" cellpadding="0" cellspacing="0">
						<tr>
							<td height="23"><strong>Extrato de Débitos:</strong></td>
						</tr>
						<tr>
							<td>
							
								<div id="layerHideConta" style="display:block">
								
									<table width="100%" cellpadding="0" cellspacing="0">
									<tr bgcolor="#79bbfd">
										<td colspan="6" height="20"><span class="style2"> <a
										href="javascript:extendeTabela('Conta',true);" /><b>Contas</b></a></span></td>
									</tr>
									</table>
									
								</div>
								
								<div id="layerShowConta" style="display:none">
									
									<table width="100%" cellpadding="0" cellspacing="0">
									<tr bgcolor="#79bbfd">
										<td colspan="6" height="20"><span class="style2"> <a
										href="javascript:extendeTabela('Conta',false);" /><b>Contas</b></a></span></td>
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
									
									<%BigDecimal valorTotalConta = new BigDecimal("0.00");%>
									<%BigDecimal valorTotalAcrescimo = new BigDecimal("0.00");%>
									<%BigDecimal valorConta = new BigDecimal("0.00");%>
			
									<logic:present name="colecaoConta">
			
									<logic:notEmpty name="colecaoConta">
										
										<table width="100%" cellpadding="0" cellspacing="0">
										<tr>
											<td>
																	
												<% String corConta = "#cbe5fe";%>
							
												<div style="width: 100%; height: 100; overflow: auto;">
																	
												<table width="100%" align="center" bgcolor="#90c7fc">
																	
												<logic:iterate name="colecaoConta" id="conta" type="ContaValoresHelper">
							
													<%valorTotalConta = valorTotalConta.add(conta.getValorTotalConta()); %>
													<%valorTotalAcrescimo = valorTotalAcrescimo.add(conta.getValorTotalContaValoresParcelamento()); %>
													
													
													
													<%	if (corConta.equalsIgnoreCase("#cbe5fe")){
														corConta = "#FFFFFF";%>
														<tr bgcolor="#FFFFFF">
													<%} else{
														corConta = "#cbe5fe";%>
														<tr bgcolor="#cbe5fe">
													<%}%>
																			
																			
													<td align="center" width="5%">
														<html:checkbox property="conta" value="<%="" + conta.getConta().getId().toString().trim() %>" 
														alt="<%="" + Util.formatarMoedaReal(conta.getValorTotalConta()).trim()%>"
														onclick="totalizarDebito(this);"/>
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
											
												<%if (corConta.equalsIgnoreCase("#cbe5fe")){
													corConta = "#FFFFFF";%>
													<tr bgcolor="#FFFFFF">
												<%} else{
													corConta = "#cbe5fe";%>
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
										</table>
					
									</logic:notEmpty>
								
									</logic:present>
															
								</div>						
							
							</td>
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
								
								<div id="layerHideDebito" style="display:block">
								
									<table width="100%" cellpadding="0" cellspacing="0">
									<tr bgcolor="#79bbfd">
										<td colspan="6" height="20"><span class="style2"> <a
										href="javascript:extendeTabela('Debito',true);" /><b>Débitos</b></a></span></td>
									</tr>
									</table>
									
								</div>			
								
								<div id="layerShowDebito" style="display:none">
									
									<table width="100%" cellpadding="0" cellspacing="0">
									<tr bgcolor="#79bbfd">
										<td colspan="6" height="20"><span class="style2"> <a
										href="javascript:extendeTabela('Debito',false);" /><b>Débitos</b></a></span></td>
									</tr>
									<tr bgcolor="#90c7fc">
										<td width="5%" height="20">
											<strong><a href="javascript:facilitador(document.forms[0].checkDebito,'debito');"id="0">Todos</a></strong>
										</td>
										<td width="30%">
											<div align="center"><strong>Tipo do Débito</strong></div>
										</td>
										<td width="20%">
											<div align="center"><strong>Mês/Ano Ref.</strong></div>
										</td>
										<td width="20%">
											<div align="center"><strong>Mês/Ano Cobr.</strong></div>
										</td>
										<td width="5%">
											<div align="center"><strong>Parc.</strong></div>
										</td>
										<td width="20%">
											<div align="center"><strong>Vl. Restante</strong></div>
										</td>
									</tr>
									</table>
						
									<%BigDecimal valorTotalDebito = new BigDecimal("0.00");%>
									
									<logic:present name="colecaoDebitoACobrar">
								
									<logic:notEmpty name="colecaoDebitoACobrar">
					
										<table width="100%" cellpadding="0" cellspacing="0">
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
													
														<html:checkbox property="debito" value="<%="" + debitoACobrar.getId().intValue() %>" 
														alt="<%="" + Util.formatarMoedaReal(debitoACobrar.getValorTotalComBonus()).trim()%>"
														onclick="totalizarDebito(this);"></html:checkbox>
													
													</td>
													<td width="30%" align="left">
														<a href="javascript:abrirPopup('exibirConsultarDebitoACobrarAction.do?imovelID=<%="" + debitoACobrar.getImovel().getId().intValue() %>&debitoID=<%="" + debitoACobrar.getId().intValue() %>', 570, 720);">
														<bean:write name="debitoACobrar" property="debitoTipo.descricao" /></a>
													</td>
													<td width="20%">
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
													<td width="20%">
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
													<td width="5%">
														<div align="center">
														
															<bean:write name="debitoACobrar" property="numeroPrestacaoCobradas" /> /
															<bean:write name="debitoACobrar" property="numeroPrestacaoDebitoMenosBonus" />
														
														</div>
													</td>
													<td width="20%">
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
												<td width="30%">
													<div align="center"><strong>Total:</strong></div>
												</td>
												<td width="20%"></td>
												<td width="20%"></td>
												<td width="5%"></td>
												<td width="20%">
													<div align="right">
													<strong>				
														<%="" + Util.formatarMoedaReal(valorTotalDebito).trim()%>
													</strong>				
													</div>
												</td>
								
													</tr>
																		
													</table>
																		
													</div>
													
												</td>
											</tr>
										</table>
										
										</logic:notEmpty>
										
										</logic:present>
										
								</div>
							</td>
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
							
								<div id="layerHideGuia" style="display:block">
								
									<table width="100%" cellpadding="0" cellspacing="0">
									<tr bgcolor="#79bbfd">
										<td colspan="6" height="20"><span class="style2"> <a
										href="javascript:extendeTabela('Guia',true);" /><b>Guias de Pagamento</b></a></span></td>
									</tr>
									</table>
									
								</div>
								
								<div id="layerShowGuia" style="display:none">
									
									<table width="100%" cellpadding="0" cellspacing="0">
									<tr bgcolor="#79bbfd">
										<td colspan="6" height="20"><span class="style2"> <a
										href="javascript:extendeTabela('Guia',false);" /><b>Guias de Pagamento</b></a></span></td>
									</tr>
									<tr bgcolor="#90c7fc">
				
										<td width="5%" height="20">
											<strong><a href="javascript:facilitador(document.forms[0].checkGuia,'guiaPagamento');"id="0">Todos</a></strong>
										</td>
										<td width="33%">
											<div align="center"><strong>Tipo do Débito</strong></div>
										</td>
										<td width="12%">
											<div align="center"><strong>Prestação</strong></div>
										</td>
										<td width="15%">
											<div align="center"><strong>Emissão</strong></div>
										</td>
										<td width="15%">
											<div align="center"><strong>Vencimento</strong></div>
										</td>
										<td width="20%">
											<div align="center"><strong>Valor</strong></div>
										</td>
				
									</tr>
									</table>
									
									
									<%BigDecimal valorTotalGuiaPagamento = new BigDecimal("0.00");%>
						
									<logic:present name="colecaoGuiaPagamento">
									
									<logic:notEmpty name="colecaoGuiaPagamento">
									
									<table width="100%" cellpadding="0" cellspacing="0">
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
												
													<html:checkbox property="guiaPagamento" value="<%="" + guiaPagamentoValoresHelper.getGuiaPagamento().getId().intValue() %>" 
													alt="<%="" + Util.formatarMoedaReal(guiaPagamentoValoresHelper.getGuiaPagamento().getValorDebito()).trim()%>"
													onclick="totalizarDebito(this);"></html:checkbox>
												
												</td>
												<td width="35%" align="left">
													<a href="javascript:abrirPopup('exibirConsultarGuiaPagamentoAction.do?guiaPagamentoId=<%="" + guiaPagamentoValoresHelper.getGuiaPagamento().getId().intValue() %>', 600, 800);">
													<bean:write name="guiaPagamentoValoresHelper" property="guiaPagamento.debitoTipo.descricao" /></a>
												</td>
												<td width="10%">
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
												<td width="20%">
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
												<td width="10%"></td>
												<td width="15%"></td>
												<td width="15%"></td>
												<td width="20%">
													<div align="right">
														<strong>				
														<%="" + Util.formatarMoedaReal(valorTotalGuiaPagamento).trim()%>
														</strong>				
													</div>
												</td>
						
											</tr>
																
											</table>
																
											</div>
											
										</td>
									</tr>
									</table>
									
									</logic:notEmpty>
									
									</logic:present>
									
								</div>
							</td>
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
							
								<div id="layerHideParcelamento" style="display:block">
								
									<table width="100%" cellpadding="0" cellspacing="0">
																	
										<tr bgcolor="#79bbfd">
											<td colspan="5" height="20"><span class="style2"> <a
										href="javascript:extendeTabela('Parcelamento',true);" /><b>Parcelamento</b></a></span></td>
										</tr>
									</table>
									
								</div>
								
								<div id="layerShowParcelamento" style="display:none">
								
									<table width="100%" cellpadding="0" cellspacing="0">
									<tr bgcolor="#79bbfd">
											<td colspan="5" height="20"><span class="style2"> <a
										href="javascript:extendeTabela('Parcelamento', false);" /><b>Parcelamento</b></a></span></td>
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
			
									<%BigDecimal valorTotalParcelamento = new BigDecimal("0.00");%>
			
									<logic:present name="colecaoDebitoCreditoParcelamento">
									
									<logic:notEmpty name="colecaoDebitoCreditoParcelamento">
						
									<table width="100%" cellpadding="0" cellspacing="0">
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
													<html:checkbox property="parcelamentoDebito" value="<%="" + debitoCredito.getParcelamento().getId().intValue() %>" 
													alt="<%="" + Util.formatarMoedaReal(debitoCredito.getValorTotal()).trim()%>"
													onclick="controleCampoAntecipacaoParcela(this);totalizarDebito(this);"/>
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
															
															<logic:present name="debitoCredito" property="quantidadeAntecipacaoParcelas">
																
																<input type="text"  name="parc<%="" + debitoCredito.getParcelamento().getId().intValue() %>" 
																size="2" maxlength="2" id="parc<%="" + debitoCredito.getParcelamento().getId().intValue() %>"
																onkeyup="limparValorAntecipacao('<%="" + debitoCredito.getParcelamento().getId().intValue() %>');"
																value="<%="" + debitoCredito.getQuantidadeAntecipacaoParcelas().intValue() %>"/>
															
															</logic:present>
															
															<logic:notPresent name="debitoCredito" property="quantidadeAntecipacaoParcelas">
																
																<input type="text"  name="parc<%="" + debitoCredito.getParcelamento().getId().intValue() %>" 
																size="2" maxlength="2" id="parc<%="" + debitoCredito.getParcelamento().getId().intValue() %>"
																onkeyup="limparValorAntecipacao('<%="" + debitoCredito.getParcelamento().getId().intValue() %>');"/>
															
															</logic:notPresent>
															
															
															
														<%} else{%>
															
															<logic:present name="debitoCredito" property="quantidadeAntecipacaoParcelas">
															
																<input type="text"  name="parc<%="" + debitoCredito.getParcelamento().getId().intValue() %>" 
																size="2" maxlength="2" id="parc<%="" + debitoCredito.getParcelamento().getId().intValue() %>"
																onkeyup="limparValorAntecipacao('<%="" + debitoCredito.getParcelamento().getId().intValue() %>');"
																value="<%="" + debitoCredito.getQuantidadeAntecipacaoParcelas().intValue() %>"
																readonly="true"/>
																
															</logic:present>
															
															<logic:notPresent name="debitoCredito" property="quantidadeAntecipacaoParcelas">
															
																<input type="text"  name="parc<%="" + debitoCredito.getParcelamento().getId().intValue() %>" 
																size="2" maxlength="2" id="parc<%="" + debitoCredito.getParcelamento().getId().intValue() %>"
																onkeyup="limparValorAntecipacao('<%="" + debitoCredito.getParcelamento().getId().intValue() %>');"
																readonly="true"/>
																
															</logic:notPresent>
														
														<%}%>
														
														<a href="javascript:validarQuantidadeAntecipacaoParcelas('<%="" + debitoCredito.getParcelamento().getId().intValue() %>');">
														<img src="<bean:message key='caminho.imagens'/>calculadora_pequena.jpg"
														alt="Calcular" border="0"></a>
														
														<input type="hidden" name="valorAntecipacao<%="" + debitoCredito.getParcelamento().getId().intValue() %>"
														id="valorAntecipacao<%="" + debitoCredito.getParcelamento().getId().intValue() %>" 
														value="<%="" + Util.formatarMoedaReal(debitoCredito.getValorAntecipacaoParcela()).trim()%>"
														alt="<%="" + Util.formatarMoedaReal(debitoCredito.getValorAntecipacaoParcela()).trim()%>">
														
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
										
									</td>
								</tr>
								</table>
								
								</logic:notEmpty>
								
								</logic:present>
						
						
								</div>
							</td>
						</tr>
						</table>
						
						
						<table width="100%" cellpadding="0" cellspacing="0">
						<tr>
							<td HEIGHT="5"></td>
						</tr>
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
					<%valorTotalDebitosFinal = valorTotalDebitosFinal.add(valorTotalGuiaPagamento); %>
					<%valorTotalDebitosFinal = valorTotalDebitosFinal.add(valorTotalParcelamento); %>
					<%//valorTotalDebitosFinal = valorTotalDebitosFinal.add(valorTotalAcrescimo); %>
				
				<table width="100%" border="0">
				<tr>
					<td HEIGHT="15"><strong>Total dos Débitos Atualizados:</strong></td>
					<td><div align="right" style="font-size: 15"><strong>R$ <%="" + Util.formatarMoedaReal(valorTotalDebitosFinal).trim()%></strong></div></td>
				</tr>
				<tr>
					<td HEIGHT="20"><strong>Total dos Débitos para pagamento com cartão de débito:</strong></td>
					<td><div align="right" style="font-size: 15"><strong>R$ <span id="totalDebitoSelecionado">0,00</span></strong></div></td>
				</tr>
				</table>
				
				
				<SCRIPT LANGUAGE="JavaScript">
				<!--
					totalizarDebitosSelecionados();
				//-->
				</SCRIPT>
				
				
				<table width="100%" border="0">
				<tr>
					<td colspan="3" height="5"></td>
				</tr>


				<tr>
					<td colspan="3">
					<table width="100%" border="0">
						<tr>
							<td height="17" colspan="3"><strong>Pagamentos confirmados para Cartão de Débito:</strong></td>
							<td align="right"><input type="button" class="bottonRightCol"
								value="Adicionar" tabindex="11" style="width: 80px"
								onclick="adicionarTransacaoCartaoDebito();">
							</td>
						</tr>
						<tr>
							<td colspan="4">
							<table width="100%" cellpadding="0" cellspacing="0">
								<tr>
									<td>
									<table width="100%" bgcolor="#99CCFF">
										<tr bgcolor="#99CCFF">
											<td align="center" width="10%"><strong>Remover</strong></td>
											<td align="center" width="60%%"><strong>Número do Cartão</strong></td>
											<td align="center" width="30%"><strong>Valor da Transação</strong></td>
										</tr>
						<logic:present name="colecaoTransacao" scope="session">
								<%int contPagamento = 0;%>
							<logic:iterate id="helper" 
								name="colecaoTransacao" type="ParcelamentoCartaoCreditoHelper">
								<%contPagamento = contPagamento + 1;
								if (contPagamento % 2 == 0) {%>
								<tr bgcolor="#FFFFFF">
								<%} else {
							
								%>
								<tr bgcolor="#cbe5fe">
								<%}%>
									<td width="10%">
									<div align="center"><font color="#333333"> <img width="14"
										height="14" border="0"
										src="<bean:message key="caminho.imagens"/>Error.gif"
										onclick="javascript:if(confirm('Confirma remoção?')){redirecionarSubmitSemUpperCase('removerPagamentoCartaoCreditoAction.do?tempoInclusao=<bean:write name="helper" property="tempoInclusao"/>');}" />
									</font></div>
									</td>
									<td>
									<div align="center"><bean:write name="helper"
										property="numeroCartao" /></div>
									</td>
									<td>
									<div align="center"><bean:write name="helper"
										property="valorTransacao" /></div>
									</td>
								</tr>
							</logic:iterate>

						</logic:present>
											
									</table>
									</td>
								</tr>
							</table>
							</td>
						</tr>
					</table>
					</td>
				</tr>
				</table>
				
			</logic:present>
			<!-- FIM PAGAMENTO COM CARTÃO DE DÉBITO -->

			<table width="100%" border="0" cellspacing="0" cellpadding ="0">
			<tr VALIGN=BOTTOM>
				<td height="24" colspan="2"><hr></td>
			</tr>
			<tr>
				<td>
					<input name="button" type="button" class="bottonRightCol"
					value="Desfazer" onclick="desfazer();"> &nbsp;&nbsp; 
					
					<input type="button" name="ButtonCancelar" class="bottonRightCol"
					value="Cancelar" onClick="javascript:document.forms[0].target='';window.location.href='/gsan/telaPrincipal.do'">
				</td>
				<td align="right"><input name="confirmar" type="button"
					class="bottonRightCol" value="Confirmar" onclick="validarForm();"></td>
			</tr>
			</table>
			
			<p>&nbsp;</p>
			
			</td>
		</tr>
	</table>
	<%@ include file="/jsp/util/rodape.jsp"%>
</body>
</html:form>
</div>

<%@ include file="/jsp/util/telaespera.jsp"%>
<script>
document.getElementById('botaoConcluir').onclick = function() { botaoAvancarTelaEspera('/gsan/parcelamentoCartaoCreditoConfirmarAction.do'); }
</script>
</html:html>
