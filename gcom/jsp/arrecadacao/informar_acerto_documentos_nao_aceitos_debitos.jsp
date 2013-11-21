<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>

<%@ page import="gcom.cobranca.DocumentoTipo" %>
<%@ page import="java.util.Collection" isELIgnored="false"%>
<%@ page import="gcom.faturamento.debito.DebitoACobrar"%>
<%@ page import="gcom.cobranca.bean.ContaValoresHelper"%>
<%@ page import="gcom.cobranca.bean.GuiaPagamentoValoresHelper"%>
<%@ page import="gcom.util.Util" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>

<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js" ></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js" ></script>
<script type="text/javascript" language="Javascript1.1">

    var bCancel = false;
	var todasContasMarcadas = false;
	var todasDebitosMarcados = false;
	var todasGuiasMarcadas = false;
	
	function selecionarTodasContas(){
		var form = document.forms[0];
		var checkBoxs = form.contasSelecao;
		
		if (checkBoxs != null && checkBoxs.length == null) {
			checkBoxs.checked = !todasContasMarcadas;
		} else {
	      	for (var i = 0; i < checkBoxs.length; i++) {
	      		
	      		checkBoxs[i].checked = !todasContasMarcadas;
	      		
	      	}
	      	
		}
      	
      	todasContasMarcadas = !todasContasMarcadas;
	}
	
	function selecionarTodosDebitos(){
		var form = document.forms[0];
		var checkBoxs = form.debitosACobrarSelecao;
		
		if (checkBoxs != null && checkBoxs.length == null) {
			checkBoxs.checked = !todasDebitosMarcados;
	    } else {
	      	for (var i = 0; i < checkBoxs.length; i++) {
	      		
	      		checkBoxs[i].checked = !todasDebitosMarcados;
	      		
	      	}
	    }
      	
      	todasDebitosMarcados = !todasDebitosMarcados;
	}
	
	function selecionarTodasGuias(){
		var form = document.forms[0];
		var checkBoxs = form.guiasSelecao;
		
		if (checkBoxs != null && checkBoxs.length == null) {
			checkBoxs.checked = !todasGuiasMarcadas;
	    } else {
	      	for (var i = 0; i < checkBoxs.length; i++) {
	      		
	      		checkBoxs[i].checked = !todasGuiasMarcadas;
	      		
	      	}
	    }
      	
      	todasGuiasMarcadas = !todasGuiasMarcadas;
	}
	
    function validateInformarAcertoDocumentosNaoAceitosActionForm(form){
		if (validarForm()){
  			submeterFormPadrao(form);
		}
    }
   
    function validarForm() {
		var form = document.forms[0];
		var msg = '';
       
        if (bCancel) {
     		 return true;
	    } else {
			if(form.idImovel.value == null
				|| form.idImovel.value == '') {
				msg = msg + 'Informe Imóvel.\n';
			}
			
			if( msg != '' ){
				alert(msg);
				return false;
			}else{
				return true;
			}
        }
    }


	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {

	    var form = document.forms[0];
	
	 	if (tipoConsulta == 'imovel') { 	
	 	  form.idImovel.value = codigoRegistro;
	 	  form.descricaoImovel.value = descricaoRegistro;
	 	  limparDadosAnteriores();
		}
	}
	 
	 
	function limparImovel(){
	 	var form = document.forms[0];
	 	
	 	form.idImovel.value = "";
	 	form.descricaoImovel.value = "";
	 	limparDadosAnteriores(); 	
	}
	
	function desabilitarBotaoSelecionar() {
		var form = document.forms[0];
		
		form.selecionarDebitosPagos.disabled = true;
	}
	function limparDadosAnteriores(){
		var form = document.forms[0];
		form.descricaoImovel.value = "";
		form.action ='informarAcertoDocumentosNaoAceitosWizardAction.do?destino=2&action=exibirInformarAcertoDocumentosNaoAceitosDebitosAction&limparDadosAnteriores=true'
		form.submit();
	}
	function verificaSelecaoImovel() {
		var form = document.forms[0];
		
		if(form.idImovel.value == null
			|| form.idImovel.value == '') {
			
			form.selecionarDebitosPagos.disabled = true;
			
		} else {
	
			form.selecionarDebitosPagos.disabled = false;
			
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
		
		var form = document.forms[0];
		
		//var objetoTotalDebitoSelecionado = document.getElementById("totalDebitoSelecionado");
		//objetoTotalDebitoSelecionado.innerHTML = "0,00";
		
		form.totalDebitoSelecionado.value = "0,00";
		
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
  	
  	function validaCheck(){
     	validaCheckConta();
	    validaCheckDebito();
	    validaCheckGuia();
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
					form.elements[indice].name == 'contasSelecao' && 
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
					form.elements[indice].name == 'debitosACobrarSelecao' && 
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
					form.elements[indice].name == 'guiasSelecao' && 
					form.elements[indice].value.trim() == chave.trim()) {

					form.elements[indice].checked = true;
				}
			}
		}
  	}

	function totalizarDebitosSelecionados(){
		
		form = document.forms[0];
		
		//validaCheck();
		
		for(indice = 0; indice < form.elements.length; indice++){
			
			if ((form.elements[indice].type == "checkbox" && form.elements[indice].checked == true)) {
				
				totalizarDebito(form.elements[indice]);
			}
		}
	}

	function totalizarDebito(objeto){
		var form = document.forms[0];
		
		//var objetoTotalDebitoSelecionado = document.getElementById("totalDebitoSelecionado");
		
		//VALOR TOTAL
		var valorTotalSelecionado = form.totalDebitoSelecionado.value; //objetoTotalDebitoSelecionado.innerHTML;
		valorTotalSelecionado = valorTotalSelecionado.replace(".","");
		valorTotalSelecionado = valorTotalSelecionado.replace(",",".");
	
		//VALOR SELECIONADO
		var valorSelecionado = 0;
		var valorAcrescimosSelecionado = 0;
		
		if(objeto.name == 'contasSelecao'){
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
		}
		else{
			valorTotalSelecionado = (valorTotalSelecionado * 1) - (valorSelecionado * 1); 
		}
		
		//objetoTotalDebitoSelecionado.innerHTML = formatarValorMonetario(valorTotalSelecionado);
		form.totalDebitoSelecionado.value = formatarValorMonetario(valorTotalSelecionado);
	}
	
	function chamarPopup(url, tipo, objeto, codigoObjeto, altura, largura, msg,objetoRelacionado){
		if(objetoRelacionado.readOnly != true){
			if (objeto == null || codigoObjeto == null){
				abrirPopup(url + "?" + "tipo=" + tipo, altura, largura);
			}else{
				if (codigoObjeto.length < 1 || isNaN(codigoObjeto)){
					alert(msg);
				}else{
					abrirPopup(url + "?" + "tipo=" + tipo + "&" + objeto + "=" + codigoObjeto, altura, largura);
				}
			}
		}
	}
	
	function filtrarPagamentos() {
		var form = document.forms[0];
		
		if(form.idImovel.value != null 
			&& form.idImovel.value != ''
			&& form.descricaoImovel.value != null 
			&& form.descricaoImovel.value != ''){
			abrirPopup('exibirSelecionarDebitosPagosPopupAction.do?abrirPopup=SIM&idImovel='+form.idImovel.value+'&descricaoImovel='+form.descricaoImovel.value, 430, 620);
		} else {
			alert('Informe Matrícula do Imóvel.');
		}
	}
</script>
</head>

<body leftmargin="5" topmargin="5" onload="verificaSelecaoImovel();" >

<html:form
    action="/informarAcertoDocumentosNaoAceitosWizardAction"
    method="post"
>

<jsp:include page="/jsp/util/wizard/navegacao_abas_wizard_valida_avancar.jsp?numeroPagina=2"/>
<%@ include file="/jsp/util/cabecalho.jsp"%>
<%@ include file="/jsp/util/menu.jsp" %>
<input type="hidden" name="numeroPagina" value="2"/>

<table width="770" border="0" cellspacing="5" cellpadding="0">
  <tr>
    <td width="145" valign="top" class="leftcoltext">
    
		<input type="hidden" name="checkConta" value="0">
		<input type="hidden" name="checkDebito" value="0">
		<input type="hidden" name="checkGuia" value="0">
		
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
          <td class="parabg">Informar Acerto Documentos Não Aceitos</td>
          <td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif"/></td>
        </tr>
      </table>
      <p>&nbsp;</p>
      <table width="100%" border="0" dwcopytype="CopyTableRow">
        <tr>
          <td colspan="2">
            Para transferir o valor do pagamento, informe os dados abaixo:
          </td>
        </tr>
        <tr>
		  <td width="160"><strong>Matrícula do Imóvel:<font color="#FF0000">*</font></strong></td>
		  <td width="82%" >
		  
		    <html:text maxlength="9" tabindex="1" property="idImovel" size="9" 
			    onkeyup="javascript:desabilitarPesquisaCliente(document.forms[0]);"
			    onblur="javascript:limparDadosAnteriores();"
			    onkeypress="validaEnter(event, '/gsan/informarAcertoDocumentosNaoAceitosWizardAction.do?destino=2&action=exibirInformarAcertoDocumentosNaoAceitosDebitosAction','idImovel');"/> 
		    
	    	<a href="javascript:chamarPopup('exibirPesquisarImovelAction.do', 'imovel', null, null, 275, 480, '',document.forms[0].idImovel);">
				<img width="23" height="21" border="0"
					src="<bean:message key="caminho.imagens"/>pesquisa.gif"
					title="Pesquisar Matrícula do Imóvel" /></a>
		
		    <logic:present name="idImovelNaoEncontrado">
		  	  <logic:equal name="idImovelNaoEncontrado" value="exception">
			    <html:text property="descricaoImovel" size="50" maxlength="50" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000" />
			  </logic:equal>
			  <logic:notEqual name="idImovelNaoEncontrado" value="exception">
			    <html:text property="descricaoImovel" size="50" maxlength="50" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" />
			  </logic:notEqual>
			</logic:present> 
			<logic:notPresent name="idImovelNaoEncontrado">
			  <logic:empty name="InformarAcertoDocumentosNaoAceitosActionForm" property="idImovel">
		 	    <html:text property="descricaoImovel" value="" size="50" maxlength="50" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000" />
			  </logic:empty>
			  <logic:notEmpty name="InformarAcertoDocumentosNaoAceitosActionForm" property="idImovel">
 			    <html:text property="descricaoImovel" size="50" maxlength="50" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" />
			  </logic:notEmpty>
			</logic:notPresent> 
			<a href="javascript:limparImovel();desabilitarBotaoSelecionar();">
				<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
				border="0" title="Apagar" /> </a>
		  </td>
		  
		</tr> 
		<tr>
			<td colspan="2" height="24">
				<hr>
			</td>
		</tr> 
	    <tr>
          <td colspan="2">
            Débitos Encontrados:
          </td>
        </tr>
		<tr>
		 	 <td colspan="2">
			 	 <table width="100%" border="0" dwcopytype="CopyTableRow">
				 	 <tr>
					 	 <td>
					  		<strong>Total do Pagamento:</strong>
			 	  		 </td>
					 	 <td>
						  	<html:text property="totalPagamento" 
						  		size="15" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000; text-align: right;" />
					  	 </td>
					  	 <td>
					  		<strong>Total dos Débitos Selecionados:</strong>
					  	 </td>
					  	 <td>
					  	 	<html:text property="totalDebitoSelecionado" 
						  		size="15" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000; text-align: right;" />
					  	
					  	</td>
				  	</tr>
			  	</table>
		  	</td>
	    </tr>
		<tr>
			<td colspan="2" height="12">
			</td>
		</tr> 
        <tr>
		  <td align="right" colspan="2">
		  	<input type="button"
				name="selecionarDebitosPagos" class="bottonRightCol"
				disabled="disabled"
				value="Selecionar Débitos Pagos"
				onClick="javascript:filtrarPagamentos();" />
		  </td>
          <td></td>
        </tr>
		<tr>
			<td colspan="2" height="12">
			</td>
		</tr> 
        <tr>
        	<td colspan="2">
				<table width="100%" align="center" bgcolor="#90c7fc" border="0">
					<%String cor = "#cbe5fe";%>
					<%cor = "#cbe5fe";%>
					<tr bordercolor="#79bbfd">
						<td colspan="6" align="center" bgcolor="#79bbfd">
						<strong>Contas</strong>
						</td>
					</tr>

					<logic:notEmpty name="colecaoContaValores" scope="session">

						<tr bordercolor="#000000">
						
							<td width="20%" bgcolor="#90c7fc">
								<div align="center"><strong><a href="javascript:facilitador(document.forms[0].checkConta,'contasSelecao');" id="0">Selecionar</a></strong></div>
							</td>
							
							<td width="15%" bgcolor="#90c7fc" align="center">
							<div class="style9" align="center"><font color="#000000" style="font-size:9px"
								face="Verdana, Arial, Helvetica, sans-serif"> <strong>M&ecirc;s/Ano</strong>
							</font></div>
							</td>
							<td width="20%" bgcolor="#90c7fc" align="center">
							<div class="style9" align="center"><font color="#000000" style="font-size:9px"
								face="Verdana, Arial, Helvetica, sans-serif"> <strong>Venc.</strong>
							</font></div>
							</td>
							
							<td width="15%" bgcolor="#90c7fc" align="center">
							<div class="style9" align="center"><font color="#000000" style="font-size:9px"
								face="Verdana, Arial, Helvetica, sans-serif"> <strong>Val.
							Conta</strong> </font></div>
							</td>
							<td width="15%" bgcolor="#90c7fc" align="center">
							<div class="style9" align="center"><font color="#000000" style="font-size:9px"
								face="Verdana, Arial, Helvetica, sans-serif"> <strong>Sit.</strong>
							</font></div>
							</td>
						</tr>
						<logic:present name="colecaoContaValores">
							<tr>
        						<td colspan="6">
       								<div style="height: 100%; max-height: 200px; overflow: auto;">
										<table width="100%" align="center" bgcolor="#90c7fc" border="0">
											<logic:iterate name="colecaoContaValores"
												id="contavaloreshelper">
												<%if (cor.equalsIgnoreCase("#cbe5fe")) {
													cor = "#FFFFFF";%>
												<tr bgcolor="#FFFFFF">
													<%} else {
													cor = "#cbe5fe";%>
												<tr bgcolor="#cbe5fe">
													<%}%>
													<bean:define name="contavaloreshelper" property="conta" id="conta" /> 
													
														 <td width="20%" align="center">
													 		<div align="center">
														 		<input 
														 			name="contasSelecao" 
														 			value="<c:out value="${conta.id}" ></c:out>" 
														 			type="checkbox" 
														 			alt="<%="" + Util.formatarMoedaReal(((ContaValoresHelper)contavaloreshelper).getValorTotalConta()).trim()%>
																		<%=";" + Util.formatarMoedaReal(((ContaValoresHelper)contavaloreshelper).getValorTotalContaValoresParcelamento()).trim()%>"
																	onclick="totalizarDebito(this);">
														 	</div>
					                                    </td>
				                                    
													<td align="center" width="15%">
														<logic:notEmpty name="contavaloreshelper" property="conta">
																										
															<logic:equal name="contavaloreshelper" property="indicadorDebitoPago" value="2">
																<div align="center">
																	<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
																		<a href="javascript:abrirPopup('exibirConsultarContaAction.do?contaID=<bean:define name="contavaloreshelper" property="conta" id="conta" /><bean:write name="conta" property="id" />&tipoConsulta=conta', 600, 800);">
																		<bean:write name="conta" property="formatarAnoMesParaMesAno" /></a> 
																	</font>
																</div>
															</logic:equal>
			
															<logic:equal name="contavaloreshelper" property="indicadorDebitoPago" value="1">
																<div align="center">
																	<font color="#ff0000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
																		<a href="javascript:abrirPopup('exibirConsultarContaAction.do?contaID=<bean:define name="contavaloreshelper" property="conta" id="conta" /><bean:write name="conta" property="id" />&tipoConsulta=conta', 600, 800);">
																		<font color="#ff0000"><bean:write name="conta" property="formatarAnoMesParaMesAno" /></font></a> 
																	</font>
																</div>
															</logic:equal>
															
														</logic:notEmpty>
													</td>
													<td align="center" width="20%">
														<logic:notEmpty name="contavaloreshelper" property="conta">
															<logic:equal name="contavaloreshelper" property="indicadorDebitoPago" value="2">
																<div align="center">
																	<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
																		<bean:define name="contavaloreshelper" property="conta" id="conta" /> 
																		<bean:write name="conta" property="dataVencimentoConta" formatKey="date.format.small" /> 
																	</font>
																</div>
															</logic:equal>
			
															<logic:equal name="contavaloreshelper" property="indicadorDebitoPago" value="1">
																<div align="center">
																	<font color="#ff0000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
																		<bean:define name="contavaloreshelper" property="conta" id="conta" /> 
																		<bean:write name="conta" property="dataVencimentoConta" formatKey="date.format.small"  /> 
																	</font>
																</div>
															</logic:equal>
														
														</logic:notEmpty>
													</td>
													
													<td align="right" width="15%">
														<logic:notEmpty name="contavaloreshelper" property="conta">
															
															<logic:equal name="contavaloreshelper" property="indicadorDebitoPago" value="2">
																<div align="right">
																	<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
																		<bean:define name="contavaloreshelper" property="conta" id="conta" /> 
																		<bean:write name="conta" property="valorTotal" formatKey="money.format" />
																	</font>
																</div>
															</logic:equal>
			
															<logic:equal name="contavaloreshelper" property="indicadorDebitoPago" value="1">
																<div align="right">
																	<font color="#ff0000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
																		<bean:define name="contavaloreshelper" property="conta" id="conta" /> 
																		<bean:write name="conta" property="valorTotal" formatKey="money.format" />
																	</font>
																</div>
															</logic:equal>
														</logic:notEmpty>
													</td>
													<td align="center" width="15%">
														<logic:notEmpty name="contavaloreshelper" property="conta">
															<logic:equal name="contavaloreshelper" property="indicadorDebitoPago" value="2">
																<div align="center" title="${contavaloreshelper.conta.debitoCreditoSituacaoAtual.descricaoDebitoCreditoSituacao}">
																	<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
																		<bean:define name="contavaloreshelper" property="conta" id="conta" /> 
																		<bean:define name="conta" property="debitoCreditoSituacaoAtual" id="debitoCreditoSituacaoAtual" /> 
																		<bean:write name="debitoCreditoSituacaoAtual" property="descricaoAbreviada" /> 
																	</font>
																</div>
															</logic:equal>
			
															
															<logic:equal name="contavaloreshelper" property="indicadorDebitoPago" value="1">
																<div align="center" title="${contavaloreshelper.conta.debitoCreditoSituacaoAtual.descricaoDebitoCreditoSituacao}" >
																	<font color="#ff0000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
																		<bean:define name="contavaloreshelper" property="conta" id="conta" /> 
																		<bean:define name="conta" property="debitoCreditoSituacaoAtual" id="debitoCreditoSituacaoAtual" /> 
																		<bean:write name="debitoCreditoSituacaoAtual" property="descricaoAbreviada" /> 
																	</font>
																</div>
															</logic:equal>
														</logic:notEmpty>
													</td>
												</tr>
											</logic:iterate>
										</table>
									</div>
								</td>
							</tr>
							
							<logic:notEmpty name="colecaoContaValores">
								<%if (cor.equalsIgnoreCase("#cbe5fe")) {
									cor = "#FFFFFF";%>
								<tr bgcolor="#FFFFFF">
									<%} else {
								cor = "#cbe5fe";%>
								<tr bgcolor="#cbe5fe">
									<%}%>
									<td bgcolor="#90c7fc" align="center">
									</td>
									<td bgcolor="#90c7fc" align="center">
									<div class="style9" align="center"><font color="#000000"
										style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"> <strong>Total</strong>
									</font></div>
									</td>
									<td align="left">
									
										<%=((Collection) session.getAttribute("colecaoContaValores")).size()%>
											&nbsp;
											doc(s)
									</td>
									<td align="right">
									<div align="right"><font color="#000000" style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"> <%=session.getAttribute("valorConta")%>
									</font></div>
									</td>
									<td align="left">
									<div align="left"><font color="#000000" style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"> </font></div>
									</td>
								</tr>
							</logic:notEmpty>
						</logic:present>
					</logic:notEmpty>
				</table>
        	</td>
        </tr>
        
		<tr>
			<td colspan="2" height="24">
			</td>
		</tr> 
		
		<tr>
        	<td colspan="2">
				<table width="100%" align="center" bgcolor="#90c7fc" border="0">
						<tr>
							<td colspan="4">
							<table width="100%" align="center" bgcolor="#90c7fc" border="0">
								<tr bordercolor="#79bbfd">
									<td colspan="6" bgcolor="#79bbfd" align="center"><strong>D&eacute;bitos
									A Cobrar</strong></td>
								</tr>
								<tr bordercolor="#000000">
									<td width="4%" bgcolor="#90c7fc">
										<div align="center"><strong>
											<a href="javascript:facilitador(document.forms[0].checkDebito,'debitosACobrarSelecao');" id="0">Selecionar</a></strong>
										</div>
									</td>
									<td width="42%" bgcolor="#90c7fc">
									<div align="center" class="style9"><font color="#000000"
										style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"> <strong>Tipo do
									D&eacute;bito</strong> </font></div>
									</td>
									<td width="13%" bgcolor="#90c7fc">
									<div align="center" class="style9"><font color="#000000"
										style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"> <strong>M&ecirc;s/Ano
									Refer&ecirc;ncia</strong> </font></div>
									</td>
									<td width="10%" bgcolor="#90c7fc">
									<div align="center" class="style9"><font color="#000000"
										style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"> <strong>M&ecirc;s/Ano
									Cobran&ccedil;a</strong> </font></div>
									</td>
									<td width="10%" bgcolor="#90c7fc">
									<div align="center" class="style9"><font color="#000000"
										style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"> <strong>Parcelas a
									cobrar</strong> </font></div>
									</td>
									<td width="17%" bgcolor="#90c7fc" height="20">
									<div align="center" class="style9"><font color="#000000"
										style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"> <strong>Valor a
									cobrar</strong> </font></div>
									</td>
								</tr>
								<%String cor1 = "#cbe5fe";%>
								<logic:present name="colecaoDebitoACobrar">
									<tr>
										<td colspan="6">
		       								<div style="height: 100%; max-height: 200px; overflow: auto;">
												<table width="100%" align="center" bgcolor="#90c7fc" border="0">
													<logic:iterate name="colecaoDebitoACobrar" id="debitoacobrarHelper">
														<bean:define name="debitoacobrarHelper"
															property="debitoACobrar" id="debitoacobrar" />
														<%if (cor1.equalsIgnoreCase("#cbe5fe")) {
															cor1 = "#FFFFFF";%>
														<tr bgcolor="#FFFFFF">
															<%} else {
															cor1 = "#cbe5fe";%>
														<tr bgcolor="#cbe5fe">
															<%}%>
														 	<td>
														 		<div align="center">
															 		<input name="debitosACobrarSelecao" 
														 				value="<c:out value="${debitoacobrar.id}" ></c:out>" 
														 				type="checkbox"
																		alt="<%="" + Util.formatarMoedaReal(((DebitoACobrar)debitoacobrar).getValorTotalComBonus()).trim()%>"
																		onclick="totalizarDebito(this);" >
															 	</div>
						                                    </td>
															<td>
																<logic:equal name="debitoacobrarHelper" property="indicadorDebitoPago" value="2">
																	<div align="left">
																		<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
																			<logic:notEmpty name="debitoacobrar" property="imovel">
																				<a href="javascript:abrirPopup('exibirConsultarDebitoACobrarAction.do?imovelID=<bean:define name="debitoacobrar" property="imovel" id="imovel" /><bean:write name="imovel" property="id" />&debitoID=<bean:write name="debitoacobrar" property="id" />', 570, 720);">
																					<bean:define name="debitoacobrar" property="debitoTipo"
																						id="debitoTipo" /> 
																					<logic:notEmpty name="debitoTipo" property="descricao">
																						<bean:write name="debitoTipo" property="descricao" />
																					</logic:notEmpty> 
																				</a>
																			</logic:notEmpty> 
																		</font>
																	</div>
																</logic:equal>
				
																<logic:equal name="debitoacobrarHelper" property="indicadorDebitoPago" value="1">
																	<div align="left">
																		<font color="#ff0000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
																			<logic:notEmpty name="debitoacobrar" property="imovel">
																				<a href="javascript:abrirPopup('exibirConsultarDebitoACobrarAction.do?imovelID=<bean:define name="debitoacobrar" property="imovel" id="imovel" /><bean:write name="imovel" property="id" />&debitoID=<bean:write name="debitoacobrar" property="id" />', 570, 720);">
																					<bean:define name="debitoacobrar" property="debitoTipo"
																						id="debitoTipo" /> 
																					<logic:notEmpty name="debitoTipo" property="descricao">
																						<bean:write name="debitoTipo" property="descricao" />
																					</logic:notEmpty> 
																				</a>
																			</logic:notEmpty> 
																		</font>
																	</div>
																</logic:equal>
															</td>
															<td>
																<logic:equal name="debitoacobrarHelper" property="indicadorDebitoPago" value="2">
																		<div align="center">
																			<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
																				<logic:notEmpty
																					name="debitoacobrar" property="anoMesReferenciaDebito">
																					<%= Util.formatarAnoMesParaMesAno(((DebitoACobrar)debitoacobrar).getAnoMesReferenciaDebito().toString()) %>
																				</logic:notEmpty>
																			</font>
																		</div>
																	</logic:equal>
					
																	<logic:equal name="debitoacobrarHelper" property="indicadorDebitoPago" value="1">
																		<div align="center">
																			<font color="#ff0000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
																				<logic:notEmpty
																					name="debitoacobrar" property="anoMesReferenciaDebito">
																					<%= Util.formatarAnoMesParaMesAno(((DebitoACobrar)debitoacobrar).getAnoMesReferenciaDebito().toString()) %>
																				</logic:notEmpty>
																			</font>
																		</div>
																	</logic:equal>
															</td>
															<td>
																<logic:equal name="debitoacobrarHelper" property="indicadorDebitoPago" value="2">
																	<div align="center">
																		<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
																			<logic:notEmpty
																				name="debitoacobrar" property="anoMesCobrancaDebito">
																				<%= Util.formatarAnoMesParaMesAno(((DebitoACobrar)debitoacobrar).getAnoMesCobrancaDebito().toString()) %>
																			</logic:notEmpty>
																		</font>
																	</div>
																</logic:equal>
				
																<logic:equal name="debitoacobrarHelper" property="indicadorDebitoPago" value="1">
																	<div align="center">
																		<font color="#ff0000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
																			<logic:notEmpty
																				name="debitoacobrar" property="anoMesCobrancaDebito">
																				<%= Util.formatarAnoMesParaMesAno(((DebitoACobrar)debitoacobrar).getAnoMesCobrancaDebito().toString()) %>
																			</logic:notEmpty>
																		</font>
																	</div>
																</logic:equal>
															</td>
															<td>
																<logic:equal name="debitoacobrarHelper" property="indicadorDebitoPago" value="2">
																	<div align="center">
																		<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
																			<logic:notEmpty
																				name="debitoacobrar" property="parcelasRestanteComBonus">
																				<bean:write name="debitoacobrar" property="parcelasRestanteComBonus" />
																			</logic:notEmpty>
																		</font>
																	</div>
																</logic:equal>
				
																<logic:equal name="debitoacobrarHelper" property="indicadorDebitoPago" value="1">
																	<div align="center">
																		<font color="#ff0000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
																			<logic:notEmpty
																				name="debitoacobrar" property="parcelasRestanteComBonus">
																				<bean:write name="debitoacobrar" property="parcelasRestanteComBonus" />
																			</logic:notEmpty>
																		</font>
																	</div>
																</logic:equal>
															</td>
															<td>
																<logic:equal name="debitoacobrarHelper" property="indicadorDebitoPago" value="2">
																	<div align="right">
																		<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
																			<logic:notEmpty
																				name="debitoacobrar" property="valorDebito">
																				<bean:write name="debitoacobrar" property="valorDebito"
																					formatKey="money.format" />
																			</logic:notEmpty>
																		</font>
																	</div>
																</logic:equal>
				
																<logic:equal name="debitoacobrarHelper" property="indicadorDebitoPago" value="1">
																	<div align="right">
																		<font color="#ff0000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
																			<logic:notEmpty
																				name="debitoacobrar" property="valorDebito">
																				<bean:write name="debitoacobrar" property="valorDebito"
																					formatKey="money.format" />
																			</logic:notEmpty>
																		</font>
																	</div>
																</logic:equal>
															</td>
														</tr>
													</logic:iterate>
												</table>
											</div>
										</td>
									</tr>
										<%if (cor1.equalsIgnoreCase("#cbe5fe")) {
										cor1 = "#FFFFFF";%>
										<tr bgcolor="#FFFFFF">
											<%} else {
										cor1 = "#cbe5fe";%>
										<tr bgcolor="#cbe5fe">
											<%}%>
										<td bgcolor="#90c7fc" colspan="2">
											<div align="center" class="style9"><font color="#000000"
												style="font-size:9px"
												face="Verdana, Arial, Helvetica, sans-serif"> <strong>Total</strong>
											</font></div>
										</td>
										<td>
											<%=((Collection) session.getAttribute("colecaoDebitoACobrar")).size() %>
												&nbsp;
												doc(s)
										</td>
										<td width="15%" ></td>
										<td width="15%" ></td>
										<td width="15%" >
											<div align="right" class="style9"><font color="#000000"
												style="font-size:9px"
												face="Verdana, Arial, Helvetica, sans-serif"> <%=session.getAttribute("valorDebitoACobrar")%>
											</font></div>
										</td>
				
									</tr>
								</logic:present>
							</table>
							</td>
						</tr>
				</table>
        	</td>
        </tr>
        
		<tr>
			<td colspan="2" height="24">
			</td>
		</tr> 
		
        <tr>
			<td colspan="2">
			<table width="100%" align="center" bgcolor="#90c7fc" border="0">
				<tr bordercolor="#79bbfd">
					<td colspan="6" bgcolor="#79bbfd" align="center"><strong>Guias de Pagamento</strong></td>
				</tr>
				<tr bordercolor="#000000">
				
					<td width="12%" bgcolor="#90c7fc">
						<div align="center">
							<strong><a href="javascript:facilitador(document.forms[0].checkGuia,'guiasSelecao');"id="0">Selecionar</a></strong>
						</div>
					</td>
					<td width="32%" bgcolor="#90c7fc">
						<div align="center" class="style9">
							<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif">
								 <strong>Tipo do D&eacute;bito</strong> 
							</font>
						</div>
					</td>
					<td width="13%" bgcolor="#90c7fc">
						<div align="center" class="style9">
							<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
								<strong>Prestação</strong> 
							</font>
						</div>
					</td>
					<td width="11%" bgcolor="#90c7fc">
						<div align="center" class="style9">
							<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
								<strong>Data de Emiss&atilde;o</strong> 
							</font>
						</div>
					</td>
					<td width="17%" bgcolor="#90c7fc">
						<div align="center" class="style9">
							<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
								<strong>Data de Vencimento</strong> 
							</font>
						</div>
					</td>
					<td width="23%" bgcolor="#90c7fc">
						<div align="center" class="style9">
							<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
								<strong>Valor da Guia de Pagamento</strong> 
							</font>
						</div>
					</td>
				</tr>
				<%cor = "#cbe5fe";%>
				<logic:present name="colecaoGuiaPagamentoValores">
					<tr>
						<td colspan="6">
							<div style="height: 100%; max-height: 200px; overflow: auto;">
								<table width="100%" align="center" bgcolor="#90c7fc" border="0">
									<logic:iterate name="colecaoGuiaPagamentoValores"
										id="guiapagamentohelper">
										<%if (cor.equalsIgnoreCase("#cbe5fe")) {
											cor = "#FFFFFF";
											%>
										<tr bgcolor="#FFFFFF">
											<%} else {
											cor = "#cbe5fe";%>
										<tr bgcolor="#cbe5fe">
											<%}%>
											<td width="12%" >
										 		<div align="center">
											 		<input name="guiasSelecao" 
											 			value="<c:out value="${guiapagamentohelper.guiaPagamento.id}" ></c:out>" 
											 			type="checkbox"
											 			alt="<%="" + Util.formatarMoedaReal(((GuiaPagamentoValoresHelper)guiapagamentohelper).getGuiaPagamento().getValorDebito()).trim()%>"
														onclick="totalizarDebito(this);" >
											 	</div>
		                                    </td>
											<td width="32%" >
												<logic:equal name="guiapagamentohelper" property="indicadorDebitoPago" value="2">
													<div align="left">
														<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
															<a href="javascript:abrirPopup('exibirConsultarGuiaPagamentoAction.do?guiaPagamentoId=<bean:define name="guiapagamentohelper" property="guiaPagamento" id="guiaPagamento" /><bean:write name="guiaPagamento" property="id" />', 600, 800);">
																<bean:define name="guiaPagamento" property="debitoTipo"	id="debitoTipo" /> 
																<logic:notEmpty name="debitoTipo" property="descricao">
																	<bean:write name="debitoTipo" property="descricao" />
																</logic:notEmpty> 
															</a>
														</font>
													</div>
												</logic:equal>

												<logic:equal name="guiapagamentohelper" property="indicadorDebitoPago" value="1">
													<div align="left">
														<font color="#ff0000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
															<a href="javascript:abrirPopup('exibirConsultarGuiaPagamentoAction.do?guiaPagamentoId=<bean:define name="guiapagamentohelper" property="guiaPagamento" id="guiaPagamento" /><bean:write name="guiaPagamento" property="id" />', 600, 800);">
																<bean:define name="guiaPagamento" property="debitoTipo"	id="debitoTipo" /> 
																<logic:notEmpty name="debitoTipo" property="descricao">
																	<bean:write name="debitoTipo" property="descricao" />
																</logic:notEmpty> 
															</a>
														</font>
													</div>
												</logic:equal>
											<td width="13%">
												<logic:equal name="guiapagamentohelper" property="indicadorDebitoPago" value="2">
													<div align="left">
														<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
															<logic:notEmpty
																name="guiapagamentohelper" property="guiaPagamento">
																<bean:define name="guiapagamentohelper"
																	property="guiaPagamento" id="guiaPagamento" />
																<bean:write name="guiaPagamento" property="prestacaoFormatada"/>
															</logic:notEmpty>
														</font>
													</div>
												</logic:equal>

												<logic:equal name="guiapagamentohelper" property="indicadorDebitoPago" value="1">
													<div align="left">
														<font color="#ff0000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
															<logic:notEmpty
																name="guiapagamentohelper" property="guiaPagamento">
																<bean:define name="guiapagamentohelper"
																	property="guiaPagamento" id="guiaPagamento" />
																<bean:write name="guiaPagamento" property="prestacaoFormatada"/>
															</logic:notEmpty>
														</font>
													</div>
												</logic:equal>
											</td>
											<td width="11%">
												<logic:equal name="guiapagamentohelper" property="indicadorDebitoPago" value="2">
													<div align="center">
														<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
															<logic:notEmpty	name="guiapagamentohelper" property="guiaPagamento"> 
																<bean:define name="guiapagamentohelper" property="guiaPagamento" id="guiaPagamento" />
																<bean:write name="guiaPagamento" property="dataEmissao"	formatKey="date.format" />
															</logic:notEmpty>
														</font>
													</div>
												</logic:equal>

												<logic:equal name="guiapagamentohelper" property="indicadorDebitoPago" value="1">
													<div align="center">
														<font color="#ff0000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
															<logic:notEmpty	name="guiapagamentohelper" property="guiaPagamento"> 
																<bean:define name="guiapagamentohelper" property="guiaPagamento" id="guiaPagamento" />
																<bean:write name="guiaPagamento" property="dataEmissao"	formatKey="date.format" />
															</logic:notEmpty>
														</font>
													</div>
												</logic:equal>
											</td>
											<td width="17%">
												<logic:equal name="guiapagamentohelper" property="indicadorDebitoPago" value="2">
													<div align="center">
														<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
															<logic:notEmpty
																name="guiapagamentohelper" property="guiaPagamento">
																<bean:define name="guiapagamentohelper"
																	property="guiaPagamento" id="guiaPagamento" />
																<bean:write name="guiaPagamento" property="dataVencimento"
																	formatKey="date.format" />
															</logic:notEmpty>
														</font>
													</div>
												</logic:equal>

												<logic:equal name="guiapagamentohelper" property="indicadorDebitoPago" value="1">
													<div align="center">
														<font color="#ff0000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
															<logic:notEmpty
																name="guiapagamentohelper" property="guiaPagamento">
																<bean:define name="guiapagamentohelper"
																	property="guiaPagamento" id="guiaPagamento" />
																<bean:write name="guiaPagamento" property="dataVencimento"
																	formatKey="date.format" />
															</logic:notEmpty>
														</font>
													</div>
												</logic:equal>
											</td>
											<td width="23%">
												<logic:equal name="guiapagamentohelper" property="indicadorDebitoPago" value="2">
													<div align="right">
														<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
															<logic:notEmpty
																name="guiapagamentohelper" property="guiaPagamento">
																<bean:define name="guiapagamentohelper"
																	property="guiaPagamento" id="guiaPagamento" />
																<bean:write name="guiaPagamento" property="valorDebito"
																	formatKey="money.format" />
															</logic:notEmpty>
														</font>
													</div>
												</logic:equal>

												<logic:equal name="guiapagamentohelper" property="indicadorDebitoPago" value="1">
													<div align="right">
														<font color="#ff0000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
															<logic:notEmpty
																name="guiapagamentohelper" property="guiaPagamento">
																<bean:define name="guiapagamentohelper"
																	property="guiaPagamento" id="guiaPagamento" />
																<bean:write name="guiaPagamento" property="valorDebito"
																	formatKey="money.format" />
															</logic:notEmpty>
														</font>
													</div>
												</logic:equal>
											</td>
										</tr>
									</logic:iterate>
								</table>
							</div>
						</td>
					</tr>
					<%if (cor.equalsIgnoreCase("#cbe5fe")) {
						cor = "#FFFFFF";%>
					<tr bgcolor="#FFFFFF">
						<%} else {
						cor = "#cbe5fe";%>
					<tr bgcolor="#cbe5fe">
						<%}%>
						<td bgcolor="#90c7fc" colspan="2">
							<div align="center" class="style9"><font color="#000000"
								style="font-size:9px"
								face="Verdana, Arial, Helvetica, sans-serif"> <strong>Total</strong>
							</font></div>
						</td>
						<td><%=((Collection) session.getAttribute("colecaoGuiaPagamentoValores")).size()%>
						&nbsp;
						doc(s)</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>
						<div align="right" class="style9"><font color="#000000"
							style="font-size:9px"
							face="Verdana, Arial, Helvetica, sans-serif"> <%=session.getAttribute("valorGuiaPagamento")%>
						</font></div>
						</td>
					</tr>
				</logic:present>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="2" height="12">
			</td>
		</tr>
		<tr align="right">
		 	 <td>
 	  		 </td>
		 	 <td align="right">
		  		<strong>Total dos Débitos:</strong>&nbsp;
			  	<html:text property="totalDebitos" 
			  		size="15" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000; text-align: right;" />
		  	 </td>
	    </tr>
        <tr>
          <td colspan="2"><div align="right"><jsp:include page="/jsp/util/wizard/navegacao_botoes_wizard_valida_avancar.jsp?numeroPagina=2"/></div></td>
        </tr>
      </table>
      <p>&nbsp;</p>
    </td>
  </tr>

</table>

<%@ include file="/jsp/util/rodape.jsp"%>

</body>
</html:form>
</html:html>
