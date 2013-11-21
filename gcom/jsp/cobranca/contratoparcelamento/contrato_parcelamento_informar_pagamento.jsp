<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>

<%@page import="gcom.cobranca.contratoparcelamento.InformarPagamentoContratoParcelamentoPorClienteHelper"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>

<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false"
	formName="ConsultarComandosContasCobrancaEmpresaActionForm" />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>

<SCRIPT LANGUAGE="JavaScript">

	function limparPesquisaArrecadador(){
		var form = document.forms[0];
		
		form.idArrecadador.value = "";
		form.nomeArrecadador.value = "";
	}
	
	function limparCliente(){
		var form = document.forms[0];
		
		form.action = 'exibirInformarPagamentoContratoParcelamentoPorClienteAction.do?limpar=sim';
    	submeterFormPadrao(form);
	}
	
	function bloqueiaDados(){
	
		var form = document.forms[0];
		
		if(form.numeroContrato.value != null && form.numeroContrato.value != ''){
				
			form.nomeCliente.value = "";
			form.idCliente.value = "";
			form.idCliente.style.color = "#000000";
			form.idCliente.readOnly = true;
			form.idCliente.style.backgroundColor = '#EFEFEF';
			
		} 
		
		if(form.idCliente.value != null && form.idCliente.value != ''){
				
			form.numeroContrato.value = "";
			form.numeroContrato.style.color = "#000000";
			form.numeroContrato.readOnly = true;
			form.numeroContrato.style.backgroundColor = '#EFEFEF';
			
		} 
		
		if ((form.numeroContrato.value == null || form.numeroContrato.value == '')
			&& (form.idCliente.value == null || form.idCliente.value == '')) {
		
			form.idCliente.style.color = "#000000";
			form.idCliente.readOnly = false;
			form.idCliente.style.backgroundColor = '';
			form.numeroContrato.style.color = "#000000";
			form.numeroContrato.readOnly = false;
			form.numeroContrato.style.backgroundColor = '';
			
		}
		
		if (selecionouContrato()) {
			
			form.idArrecadador.style.color = "#000000";
			form.idArrecadador.readOnly = false;
			form.idArrecadador.style.backgroundColor = '';
			
			form.dataPagamento.style.color = "#000000";
			form.dataPagamento.readOnly = false;
			form.dataPagamento.style.backgroundColor = '';
			
		} else {
				
			form.idArrecadador.value = "";
			form.idArrecadador.style.color = "#000000";
			form.idArrecadador.readOnly = true;
			form.idArrecadador.style.backgroundColor = '#EFEFEF';
			form.dataPagamento.value = "";
			form.dataPagamento.style.color = "#000000";
			form.dataPagamento.readOnly = true;
			form.dataPagamento.style.backgroundColor = '#EFEFEF';
			
		}
	
	}
	
	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
	    var form = document.forms[0];
	
	    if (tipoConsulta == 'contratoParcelamento') {
	    	form.numeroContrato.value = descricaoRegistro;
			bloqueiaDados();
	    } else if (tipoConsulta == 'cliente') {
    		form.idCliente.value = codigoRegistro;
			form.nomeCliente.value = descricaoRegistro;
			form.nomeCliente.style.color = "#000000";
			bloqueiaDados();
    	} else if (tipoConsulta == 'arrecadador') {
	        form.idArrecadador.value = codigoRegistro;
	        form.nomeArrecadador.value = descricaoRegistro;
	        form.nomeArrecadador.style.color = "#000000";
	    }

	 }
	 
	 function limparLista() {
	 	var form = document.forms[0];
	 	
	 	if (form.totalSelecionado.value != null && form.totalSelecionado.value != "") {
			form.action = 'exibirInformarPagamentoContratoParcelamentoPorClienteAction.do?limparContratos=sim';
			submeterFormPadrao(form);
		}
	 }
	 
	 function selecionaDadosContrato() {
	 	var form = document.forms[0];
	 	
	 	if (selecionouContrato()) {
			form.action = 'exibirInformarPagamentoContratoParcelamentoPorClienteAction.do?selecionarDadosContrato=sim&idContrato='+contratoSelecionado();
			submeterFormPadrao(form);
		}
	 }
	
	function selecionouContrato(){

		var form = document.forms[0];
	    var selecionados = form.elements['idRegistro'];
		var retorno = false;
		
		if (selecionados == undefined) {
		
			retorno = false;
			
		} else if (selecionados.value != null && selecionados.value != '') {
			
			retorno = true;
			
		} else {
		
			for (i=0; i< selecionados.length; i++) {
				if(selecionados[i].checked){
					retorno = true;
				}
			}
			
		}

		return retorno;
	}
	
	function contratoSelecionado(){

		var form = document.forms[0];
	    var selecionados = form.elements['idRegistro'];
		var retorno = false;
		
		if (selecionados.value != null && selecionados.value != '') {
			
			return selecionados.value;
			
		} else {
			for (i=0; i< selecionados.length; i++) {
				if(selecionados[i].checked){
					return selecionados[i].value;
				}
			}
		}
	}
	
	function informarPagamento(){
		
		var form = document.forms[0];
		
		if (!selecionouContrato()) {
			alert('Selecione um contrato');
		} else if (form.idArrecadador.value == null || form.idArrecadador.value == '' ){
			alert('Informe o Arrecadador');
		} else if (validaData(form.dataPagamento)){
			form.action = 'informarPagamentoContratoParcelamentoPorClienteAction.do';
			submeterFormPadrao(form);
		}
		
	}
	
</script>

</head>

<body leftmargin="5" topmargin="5" onload="bloqueiaDados();">

<html:form action="/informarPagamentoContratoParcelamentoPorClienteAction"
	name="InformarPagamentoContratoParcelamentoPorClienteActionForm"
	type="gcom.gui.cobranca.contratoparcelamento.InformarPagamentoContratoParcelamentoPorClienteActionForm"
	method="post">

	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>


	<table width="770" border="0" cellspacing="5" cellpadding="0">
		<tr>
			<td width="150" valign="top" class="leftcoltext">
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
			<td width="625" valign="top" bgcolor="#003399" class="centercoltext">
			<table height="100%">

				<tr>
					<td></td>
				</tr>
			</table>
			
			<html:hidden property="totalSelecionado"/>
			
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img border="0" src="imagens/parahead_left.gif" /></td>
					<td class="parabg">Informar Pagamento Contrato de Parcelamento por Cliente</td>
					<td width="11" valign="top"><img border="0"
						src="imagens/parahead_right.gif" /></td>
				</tr>
			</table>
			<!--Fim Tabela Reference a Páginação da Tela de Processo-->
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>
					<td colspan="2">Para informar o pagamento de Contrato de Parcelamento por Cliente, informe os dados abaixo:</td>
				</tr>
				
		        <tr>
		          <td width="26%"><strong>Número do Contrato:<font color="#ff0000"></font></strong></td>
		          <td width="74%">
					<html:text maxlength="15" property="numeroContrato" size="15" onchange="javascript:limparLista();"
						onkeyup="validaEnterString(event, 'exibirInformarPagamentoContratoParcelamentoPorClienteAction.do', 'numeroContrato'); bloqueiaDados();" />
					 <span class="style2"><a href="javascript:abrirPopup('exibirContratoParcelamentoPesquisar.do?indicadorUsoTodos=1&limparForm=OK&tipoConsulta=contrato&indicadorPesquisaApenasContEncerrados=2', 500, 700);">
		          		<img src="imagens/pesquisa.gif" alt="Pesquisar Contrato Parcelamento" width="23" height="21" border="0" /></a>
		          	 </span>
		          </td>
		          <td></td>
		        </tr>
				<tr>
					<td width="30%"><strong>Cliente:</strong></td>
					<td><html:text maxlength="9" property="idCliente" size="9"
						tabindex="2" onchange="javascript:bloqueiaDados();limparLista();"
						onkeyup="validaEnterComMensagem(event, 'exibirInformarPagamentoContratoParcelamentoPorClienteAction.do', 'idCliente', 'Cliente'); limparClienteTecla();bloqueiaDados();" />
					<a
						href="javascript:abrirPopup('exibirPesquisarClienteAction.do?limpaForm=S', 495, 300);"><img
						src="<bean:message key='caminho.imagens'/>pesquisa.gif" width="23"
						height="21" alt="Pesquisar" border="0"></a> <logic:present
						name="clienteInexistente" scope="request">
						<html:text property="nomeCliente" size="40" maxlength="40"
							readonly="true"
							style="border: 0pt none ; background-color:#EFEFEF; color: #ff0000" />
					</logic:present> <logic:notPresent name="clienteInexistente"
						scope="request">
						<html:text property="nomeCliente" size="40" maxlength="40"
							readonly="true"
							style="background-color:#EFEFEF; border:0; color: #000000" />
					</logic:notPresent> <a href="javascript:limparCliente();"> <img
						border="0" src="imagens/limparcampo.gif" height="21" width="23"> </a>
					</td>
				</tr>
				<tr>
					<td colspan="3">
					<hr>
					</td>
				</tr>

				<table width="100%" >
				
			        <tr bgcolor="#99CCFF" align="center">
			         	<td colspan="3" bgcolor="#79bbfd">
			         		<div align="center">
			         			<span class="style2"><b>Contratos Pendentes</b></span><strong></strong>
			        		</div>
			       		</td>
			        </tr>
					<tr>
						<td colspan="4" height="1"> </td>
					</tr>
					<tr>
						<td colspan="3">
						<table width="100%"  border="0">
							<tr bordercolor="#000000">
								<td width="5%" bgcolor="#90c7fc" align="center" rowspan="2"><strong></strong></td>
								<td width="10%" bordercolor="#000000" bgcolor="#90c7fc"	align="center" rowspan="2">
									<div align="center"><strong>Contrato</strong></div>
								</td>
								<td width="13%" bgcolor="#90c7fc" align="center" rowspan="2"><strong>Valor Parcelado</strong></td>
								<td width="10%" bgcolor="#90c7fc" align="center" rowspan="2"><strong>No. Parcelas</strong></td>
								<td width="13%" bgcolor="#90c7fc" align="center" rowspan="2"><strong>Valor Pago</strong></td>
								<td width="14%" bgcolor="#90c7fc" align="center" rowspan="2"><strong>No. Parcelas Pagas</strong></td>
								
				
						</table>
						</td>
					</tr>
					<tr>
						<td colspan="3">
						<table width="100%" bgcolor="#99CCFF">
							<pg:pager isOffset="true" index="half-full" maxIndexPages="10"
								export="currentPageNumber=pageNumber;pageOffset"
								maxPageItems="10" items="${sessionScope.totalRegistros}">
								<pg:param name="pg" />
								<pg:param name="q" />
								<logic:present
									name="colecaoInformarPagamentoContratoParcelamentoPorClienteHelper">
									<%int cont = 0;%>
									<logic:iterate
										name="colecaoInformarPagamentoContratoParcelamentoPorClienteHelper"
										id="informarPagamentoContratoParcelamentoPorClienteHelper"
										type="InformarPagamentoContratoParcelamentoPorClienteHelper"
										scope="session">
										<pg:item>
											<%cont = cont + 1;
									if (cont % 2 == 0) {%>
											<tr bgcolor="#cbe5fe">
												<%} else {%>
											</tr>
											<tr bgcolor="#FFFFFF">
												<%}%>
												<td width="5%">
													<div align="center"><html:radio property="idRegistro"
														value="${informarPagamentoContratoParcelamentoPorClienteHelper.idContrato}"
														onchange="selecionaDadosContrato();" />
													</div>
												</td>

												<td align="center" width="10%">
													<bean:write name="informarPagamentoContratoParcelamentoPorClienteHelper" property="numeroContrato" />
												</td>
		
												<td align="center" width="13%">
													<bean:write name="informarPagamentoContratoParcelamentoPorClienteHelper" property="valorParcelado"  /> 
												</td>
	
												<td align="center" width="10%">
													<bean:write name="informarPagamentoContratoParcelamentoPorClienteHelper" property="numeroParcelas"  /> 
												</td>
	
												<td align="center" width="13%">
													<bean:write name="informarPagamentoContratoParcelamentoPorClienteHelper" property="valorPago"  /> 
												</td>
												
												<td align="center" width="14%">
													<bean:write name="informarPagamentoContratoParcelamentoPorClienteHelper" property="numeroParcelasPagas" /> 
												</td>

											</tr>
										</pg:item>
									</logic:iterate>
								</logic:present>
						</table>
						</td>
					</tr>
					<tr>
						<td colspan="3">
						<hr>
						</td>
					</tr>
					<tr bgcolor="#99CCFF" align="center">
			         	<td colspan="3" bgcolor="#79bbfd">
			         		<div align="center">
			         			<span class="style2"><b>Dados do Pagamento</b></span><strong></strong>
			        		</div>
			       		</td>
			        </tr>
			        <tr>
						<td><strong>Arrecadador:<font color="#FF0000">*</font></strong></td>
						<td colspan="2"><html:text maxlength="3" property="idArrecadador" size="4"
								onkeypress="validaEnterComMensagem(event, 'exibirInformarPagamentoContratoParcelamentoPorClienteAction.do', 'idArrecadador','Arrecadador');" /> 
							<logic:present name="confirmarPagamento">
							<a
								href="javascript:abrirPopup('exibirPesquisarArrecadadorAction.do');"><img
								src="<bean:message key='caminho.imagens'/>pesquisa.gif" width="23"
								height="21" alt="Pesquisar Arrecadador" border="0"></a>
							</logic:present>
							<logic:notPresent name="confirmarPagamento">
									<img width="23" height="21" border="0"
										src="<bean:message key="caminho.imagens"/>pesquisa.gif"
										title="Pesquisar Arrecadador" /> 
							</logic:notPresent>
							
							<logic:present name="idArrecadadorNaoEncontrado">
								<logic:equal name="idArrecadadorNaoEncontrado" value="exception">
									<html:text property="nomeArrecadador" size="40" maxlength="30"
										readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000"/>
								</logic:equal>
								<logic:notEqual name="idArrecadadorNaoEncontrado" value="exception">
									<html:text property="nomeArrecadador" size="40" maxlength="30"
										readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>
								</logic:notEqual>
							</logic:present> 
							<logic:notPresent name="idArrecadadorNaoEncontrado">
								<html:text property="nomeArrecadador" size="40" maxlength="30"
									readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000"/>
							</logic:notPresent>
							<logic:present name="confirmarPagamento">
								<a href="javascript:limparPesquisaArrecadador(document.forms[0]);"> <img
								   src="<bean:message key="caminho.imagens"/>limparcampo.gif"
								   border="0" title="Apagar" /></a></logic:present>
							<logic:notPresent name="confirmarPagamento">
								<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
								   border="0" title="Apagar" />
							</logic:notPresent>
						</td>
		
					</tr>
					<tr>
						<td width="26%">
							<b>Parcela:</b>
						</td>
						<td colspan="2" width="74%">
							<html:text property="numeroParcela" size="40" maxlength="40"
								readonly="true" style="background-color:#EFEFEF;" />
						</td>
					</tr>	
					<tr>
						<td width="26%">
							<b>Valor:</b>
						</td>
						<td colspan="2" width="74%">
							<html:text property="valorParcelado" size="40" maxlength="40"
								readonly="true" style="background-color:#EFEFEF;" />
						</td>
					</tr>	
					<tr>
						<td><strong>Data do Pagamento:<font color="#FF0000">*</font></strong></td>
						<td colspan="2"><strong> <html:text maxlength="10" property="dataPagamento"
							size="10" tabindex="8" onkeyup="mascaraData(this, event);" />
						</strong> <a href="javascript:abrirCalendario('InformarPagamentoContratoParcelamentoPorClienteActionForm', 'dataPagamento');">
						<img border="0"
							src="<bean:message key="caminho.imagens"/>calendario.gif"
							width="20" border="0" align="absmiddle" alt="Exibir Calendário" /></a>(dd/mm/aaaa)</td>
					</tr>
				</table>
				<table width="100%" border="0">
					<tr>
						<td colspan="2">
						<div align="center"><strong><%@ include
							file="/jsp/util/indice_pager_novo.jsp"%></strong></div>
						</td>
					</tr>
					</pg:pager>

					<tr>
						<td colspan="2">&nbsp;</td>
					</tr>

					<tr>
						<td><strong> <font color="#FF0000"></font></strong></td>
						<td align="right">
						<div align="left"><strong><font color="#FF0000">*</font></strong>
						Campos obrigat&oacute;rios</div>
						</td>
					</tr>

					<tr>
						<td>
							<input name="Button" type="button" class="bottonRightCol"
								value="Desfazer" align="left"
								onclick="window.location.href='<html:rewrite page="/exibirInformarPagamentoContratoParcelamentoPorClienteAction.do?menu=sim"/>'">
							<input name="Button" type="button" class="bottonRightCol"
								value="Cancelar" align="left"
								onclick="javascript:window.location.href='/gsan/telaPrincipal.do'">
						</td>
						<td align="right">
							<logic:present name="confirmarPagamento" scope="session">
								<input type="button" name="confirmar"
									class="bottonRightCol" value="Confirmar Pagamento"
									onClick="javascript:informarPagamento();" />
							</logic:present>
							
							<logic:notPresent name="confirmarPagamento" scope="session">
								<input type="button" name=""confirmar"" disabled="disabled"
									class="bottonRightCol" value="Confirmar Pagamento"
									onClick="" />
							</logic:notPresent>
						</td>
					</tr>

				</table>

			</table>

			<%@ include file="/jsp/util/rodape.jsp"%> </html:form>
</body>
</html:html>
