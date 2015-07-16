<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<%@ page import="gcom.util.Util"%>
<%@ page import="gcom.cobranca.bean.ObterDebitoImovelOuClienteHelper"%>
<%@ page import="gcom.cobranca.bean.ContaValoresHelper"
	isELIgnored="false"%>
<%@ page import="gcom.faturamento.debito.DebitoACobrar"%>
<%@ page import="gcom.faturamento.credito.CreditoARealizar"%>
<%@ page import="gcom.cobranca.bean.GuiaPagamentoValoresHelper"%>
<%@ page import="gcom.util.ConstantesSistema" isELIgnored="false"%>
<%@ page import="java.util.Collection" isELIgnored="false"%>
<%@ page import="gcom.faturamento.conta.Conta"%>
<%@ page import="gcom.arrecadacao.pagamento.Pagamento"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<head>
<html:html>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false"
	formName="ConsultarDebitoClienteActionForm" dynamicJavascript="true" />

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript">

function voltar(){
    var form = document.forms[0];
  	form.action = 'exibirConsultarDebitoAction.do?voltar=sim';
    form.submit();		
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

//Ativa todos os elementos do tipo checkbox do formulario existente
function marcarTodosExtrato(nome){

	for (var i=0;i < document.forms[0].elements.length;i++){
		var elemento = document.forms[0].elements[i];
		if (elemento.type == "checkbox" && elemento.name == nome){
			
			elemento.checked = true;
		}
	}
}

//Desativa todos os elementos do tipo checkbox do formulario existente
function desmarcarTodosExtrato(nome) {

	for (var i=0;i < document.forms[0].elements.length;i++){
		var elemento = document.forms[0].elements[i];
		if (elemento.type == "checkbox" && elemento.name == nome){
			
			if (elemento.checked == true){
				elemento.checked = false;
			}
		}
	}
}

function contasSelecionadas2(form,urlTransferencia){
	
    retorno = false;

	for(indice = 0; indice < form.elements.length; indice++){
		
		if (form.elements[indice].type == "checkbox" && form.elements[indice].checked == true) {
			retorno = true;
			break;
		}		
	}
	
	if (!retorno){
		alert('Não existem contas selecionadas para emissão de extrato.'); 
	} else {	
		form.action = urlTransferencia;
		form.submit();
	}
}

function debitosCreditosSelecionados(form,urlTransferencia){
	
    retorno = false;

	for(indice = 0; indice < form.elements.length; indice++){
		
		if (form.elements[indice].type == "checkbox" && form.elements[indice].checked == true) {
			retorno = true;
			break;
		}
	}
	
	if (!retorno){
		alert('Não existem débitos/créditos selecionados para emissão de extrato.'); 
	}
	else {
		form.action = urlTransferencia;
		form.submit();
	}
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
	
function validaCheckCredito(){
	var form = document.forms[0];  	
	
	var idCreditos = form.idsCredito.value;
	myString = new String(idCreditos);
	splitString = myString.split(",");
	
	for (i=0; i< splitString.length; i++) {
		chave  = splitString[i];
		for(indice = 0; indice < form.elements.length; indice++){
			if (form.elements[indice].type == "checkbox" && 
				form.elements[indice].name == 'credito' && 
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
 
</script>
</head>

<body leftmargin="5" topmargin="5" onload="validaCheck();">
<html:form action="/exibirConsultarDebitoClienteAction"
	name="ConsultarDebitoClienteActionForm" method="post"
	type="gcom.gui.cobranca.ConsultarDebitoClienteActionForm">

	<input type="hidden" name="checkConta" value="0">
	<input type="hidden" name="checkCredito" value="0">
	<input type="hidden" name="checkDebito" value="0">
	<input type="hidden" name="checkGuia" value="0">

	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>

	<table width="770" border="0" cellspacing="5" cellpadding="0">
		<tr>
			<td width="115" valign="top" class="leftcoltext">
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

			<td valign="top" class="centercoltext">
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
					<td class="parabg">Consultar Débitos do Cliente</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<table height="100%">
				<tr>
					<td></td>
				</tr>
			</table>
			<table width="100%" border="0">
				<tr>
					<td colspan="4">
					<table width="100%" align="center" bgcolor="#90c7fc" border="0">
						<tr>
							<td colspan="2" align="center"><strong>Dados do Cliente</strong></td>
						</tr>
						<tr bgcolor="#cbe5fe">
							<td align="left">
							<table border="0">
								<tr>
									<td width="50%" align="right">
									<div align="left" class="style9"><strong>C&oacute;digo do
									Cliente:</strong></div>
									</td>
									<td width="50%" align="left">
									<logic:present name="ConsultarDebitoClienteActionForm" property="codigoCliente">
									<html:text
										name="ConsultarDebitoClienteActionForm"
										property="codigoCliente" size="9" readonly="true"
										style="background-color:#EFEFEF; border:0; font-size:9px" />
									</logic:present>
									<logic:notPresent name="ConsultarDebitoClienteActionForm" property="codigoCliente">
										<html:text
										name="ConsultarDebitoClienteActionForm"
										property="codigoClienteSuperior" size="9" readonly="true"
										style="background-color:#EFEFEF; border:0; font-size:9px" />
									</logic:notPresent>
									
										</td>
										
								</tr>
								<tr>
									<td width="50%">
									<div align="left" class="style9"><strong>Tipo de Cliente:</strong>
									</div>
									</td>
									<td align="left" width="50%"><html:text
										name="ConsultarDebitoClienteActionForm" property="tipoRelacao"
										size="30" readonly="true"
										style="background-color:#EFEFEF; border:0; font-size:9px" /></td>
								</tr>
								<tr>
									<td width="50%">
									<div align="left" class="style9"><strong>Per&iacute;odo de
									Referência do D&eacute;bito:</strong></div>
									</td>
									<td align="left" width="50%">
									<div align="left"><html:text
										name="ConsultarDebitoClienteActionForm"
										property="referenciaInicial" size="7" readonly="true"
										style="background-color:#EFEFEF; border:0; font-size:9px" />
									<strong> a</strong> <html:text
										name="ConsultarDebitoClienteActionForm"
										property="referenciaFinal" size="7" readonly="true"
										style="background-color:#EFEFEF; border:0; font-size:9px" />
									</div>
									</td>
								</tr>
								<tr>
									<td width="50%">
									<div align="left" class="style9"><strong>Per&iacute;odo de
									Vencimento do D&eacute;bito:</strong></div>
									</td>
									<td align="left" width="50%">
									<div align="left"><html:text
										name="ConsultarDebitoClienteActionForm"
										property="dataVencimentoInicial" size="10" readonly="true"
										style="background-color:#EFEFEF; border:0; font-size:9px" />
									<strong> a</strong> <html:text
										name="ConsultarDebitoClienteActionForm"
										property="dataVencimentoFinal" size="10" readonly="true"
										style="background-color:#EFEFEF; border:0; font-size:9px" />
									</div>
									</td>
								</tr>
								<tr>
									<td width="50%">
									<div align="left" class="style9"><strong>Nome do Cliente:</strong>
									</div>
									</td>
									<td align="left" width="50%">
									<logic:present name="ConsultarDebitoClienteActionForm" property="nomeCliente">
									<html:text
										name="ConsultarDebitoClienteActionForm" property="nomeCliente"
										size="50" readonly="true"
										style="background-color:#EFEFEF; border:0; font-size:9px" />
									</logic:present>
									<logic:notPresent name="ConsultarDebitoClienteActionForm" property="nomeCliente">
										<html:text
										name="ConsultarDebitoClienteActionForm" property="nomeClienteSuperior"
										size="50" readonly="true"
										style="background-color:#EFEFEF; border:0; font-size:9px" />
									</logic:notPresent>
									</td>
								</tr>
								<tr>
									<td width="50%">
									<div align="left" class="style9"><strong>CPF/CNPJ:</strong></div>
									</td>
									<td align="left" width="50%"><logic:notEqual
										name="ConsultarDebitoClienteActionForm" property="cpfCnpj"
										value="null">
										<html:text name="ConsultarDebitoClienteActionForm"
											property="cpfCnpj" size="25" readonly="true"
											style="background-color:#EFEFEF; border:0; font-size:9px" />
									</logic:notEqual> <logic:equal
										name="ConsultarDebitoClienteActionForm" property="cpfCnpj"
										value="null">
										<input name="ConsultarDebitoClienteActionForm" value=""
											size="25" readonly="true"
											style="background-color:#EFEFEF; border:0; font-size:9px" />
									</logic:equal></td>
								</tr>
								<tr>
									<td width="50%">
									<div align="left" class="style9"><strong>Profiss&atilde;o:</strong>
									</div>
									</td>
									<td align="left" width="50%"><logic:present
										name="ConsultarDebitoClienteActionForm" property="profissao">
										<html:text name="ConsultarDebitoClienteActionForm"
											property="profissao" size="25" readonly="true"
											style="background-color:#EFEFEF; border:0; font-size:9px" />&nbsp;
												</logic:present> <logic:notPresent
										name="ConsultarDebitoClienteActionForm" property="profissao">
										<input name="ConsultarDebitoClienteActionForm" value=""
											size="25" readonly="true"
											style="background-color:#EFEFEF; border:0; font-size:9px" />&nbsp;
												</logic:notPresent></td>
								</tr>
								<tr>
									<td width="50%">
									<div align="left" class="style9"><strong>Ramo de Atividade:</strong>
									</div>
									</td>
									<td align="left" width="50%"><logic:present
										name="ConsultarDebitoClienteActionForm"
										property="ramoAtividade">
										<html:text name="ConsultarDebitoClienteActionForm"
											property="ramoAtividade" size="25" readonly="true"
											style="background-color:#EFEFEF; border:0; font-size:9px" />&nbsp;
												</logic:present> <logic:notPresent
										name="ConsultarDebitoClienteActionForm"
										property="ramoAtividade">
										<input name="ConsultarDebitoClienteActionForm" value=""
											size="25" readonly="true"
											style="background-color:#EFEFEF; border:0; font-size:9px" />&nbsp;
												</logic:notPresent></td>
								</tr>
								<tr>
									<td width="50%">
									<div align="left" class="style9"><strong>Telefone para Contato:</strong>
									</div>
									</td>
									<td width="50%"><html:text
										name="ConsultarDebitoClienteActionForm" property="clienteFone"
										size="15" readonly="true"
										style="background-color:#EFEFEF; border:0; font-size:9px" /></td>
								</tr>
							</table>
							</td>
						</tr>
					</table>
					</td>
				</tr>
				<tr>
					<td colspan="4">
					<table width="100%" align="center" bgcolor="#90c7fc" border="0">
						<tr>
							<td align="center">
							<div align="center" class="style9"><strong>Endere&ccedil;o </strong>
							</div>
							</td>
						</tr>
						<tr bgcolor="#ffffff">
							<td align="center">
							<div align="center" class="style9"><bean:write
								name="ConsultarDebitoClienteActionForm"
								property="enderecoCliente" /> &nbsp;</div>
							</td>
						</tr>
					</table>
					</td>
				</tr>
				<tr>
					<td colspan="5">
					<table width="100%" align="center" bgcolor="#90c7fc" border="0">
						<%String cor = "#cbe5fe";%>
						<tr bordercolor="#90c7fc">
							<td colspan="12" bgcolor="#79bbfd" align="center"><strong>Contas</strong></td>
						</tr>
						<logic:notEmpty name="colecaoContaValores">
							<%if (((Collection) session.getAttribute("colecaoContaValores"))
					.size() <= ConstantesSistema.NUMERO_MAXIMO_REGISTROS_CONTAS_DEBITO) {%>
							<tr bordercolor="#000000">
							
								<td width="5%" bgcolor="#90c7fc" align="center">
								<div class="style9">
								<font color="#000000" style="font-size:9px"
									face="Verdana, Arial, Helvetica, sans-serif">
									<strong><a href="javascript:facilitador(document.forms[0].checkConta,'contasSelecionadas');" id="0">Todos</a></strong>
								</font>
								</div>
								</td>
								
								<td width="10%" bgcolor="#90c7fc" align="center">
								<div class="style9"><font color="#000000" style="font-size:9px"
									face="Verdana, Arial, Helvetica, sans-serif"> <strong>Matrícula</strong>
								</font></div>
								</td>
								<td width="10%" bgcolor="#90c7fc" align="center">
								<div class="style9"><font color="#000000" style="font-size:9px"
									face="Verdana, Arial, Helvetica, sans-serif"> <strong>M&ecirc;s/Ano</strong>
								</font></div>
								</td>
								<td width="9%" bgcolor="#90c7fc" align="center">
								<div class="style9"><font color="#000000" style="font-size:9px"
									face="Verdana, Arial, Helvetica, sans-serif"> <strong>Vencimento</strong>
								</font></div>
								</td>
								<td width="9%" bgcolor="#90c7fc" align="center">
								<div class="style9"><font color="#000000" style="font-size:9px"
									face="Verdana, Arial, Helvetica, sans-serif"> <strong>Valor de
								&Aacute;gua </strong> </font></div>
								</td>
								<td width="9%" bgcolor="#90c7fc" align="center">
								<div class="style9"><font color="#000000" style="font-size:9px"
									face="Verdana, Arial, Helvetica, sans-serif"> <strong>Valor de
								Esgoto</strong> </font></div>
								</td>
								<td width="8%" bgcolor="#90c7fc" align="center">
								<div class="style9"><font color="#000000" style="font-size:9px"
									face="Verdana, Arial, Helvetica, sans-serif"> <strong>Valor dos
								D&eacute;bitos</strong> </font></div>
								</td>
								<td width="8%" bgcolor="#90c7fc" align="center">
								<div class="style9"><font color="#000000" style="font-size:9px"
									face="Verdana, Arial, Helvetica, sans-serif"> <strong>Valor dos
								Creditos</strong> </font></div>
								</td>

								<td width="8%" bgcolor="#90c7fc" align="center">
								<div class="style9"><font color="#000000" style="font-size:9px"
									face="Verdana, Arial, Helvetica, sans-serif"> <strong>Valor dos
								Impostos</strong> </font></div>
								</td>

								<td width="8%" bgcolor="#90c7fc" align="center">
								<div class="style9"><font color="#000000" style="font-size:9px"
									face="Verdana, Arial, Helvetica, sans-serif"> <strong>Valor da
								Conta</strong> </font></div>
								</td>
								
								<td width="8%" bgcolor="#90c7fc" align="center">
								<div class="style9"><font color="#000000" style="font-size:9px"
									face="Verdana, Arial, Helvetica, sans-serif"> <strong>Acr&eacute;sc.
								Impont.</strong><strong></strong> </font></div>
								</td>
								<td width="8%" bgcolor="#90c7fc" align="center">
								<div class="style9"><font color="#000000" style="font-size:9px"
									face="Verdana, Arial, Helvetica, sans-serif"> <strong>Sit.</strong>
								</font></div>
								</td>
							</tr>
							<logic:present name="colecaoContaValores">
								<logic:iterate name="colecaoContaValores"
									id="contaValoresHelper" type="ContaValoresHelper">
									<%if (cor.equalsIgnoreCase("#cbe5fe")) {
					cor = "#FFFFFF";%>
									<tr bgcolor="#FFFFFF">
										<%} else {
					cor = "#cbe5fe";%>
									<tr bgcolor="#cbe5fe">
										<%}%>
										
										<td align="center" width="5%">
										<html:multibox property="contasSelecionadas">
											<bean:define name="contaValoresHelper" property="conta" id="conta" />
											<bean:write name="conta" property="id" />
										</html:multibox>
										</td>
										
										<td>
										<div class="style9" align="center"><bean:define
											name="contaValoresHelper" property="conta" id="conta" /> <font
											color="#000000" style="font-size:9px"
											face="Verdana, Arial, Helvetica, sans-serif"> <logic:notEmpty
											name="contaValoresHelper" property="conta">
											<logic:equal name="conta" property="contaMotivoRevisao"
												value="">
												<bean:write name="conta" property="imovel.id" />
											</logic:equal>
											<logic:notEqual name="conta" property="contaMotivoRevisao"
												value="">
												<font color="#CC0000"> <bean:write name="conta"
													property="imovel.id" /> </font>
											</logic:notEqual>
										</logic:notEmpty> </font></div>
										</td>
										<td align="left"><logic:notEmpty name="contaValoresHelper"
											property="conta">
											<div class="style9" align="center"><font color="#000000"
												style="font-size:9px"
												face="Verdana, Arial, Helvetica, sans-serif"> <a
												href="javascript:abrirPopup('exibirConsultarContaAction.do?contaID=<bean:define name="contaValoresHelper" property="conta" id="conta" /><bean:write name="conta" property="id" />&tipoConsulta=conta', 600, 800);">
											<bean:define name="contaValoresHelper" property="conta"
												id="conta" /> <logic:equal name="conta"
												property="contaMotivoRevisao" value="">
												<bean:write name="conta" property="formatarAnoMesParaMesAno" />
											</logic:equal> <logic:notEqual name="conta"
												property="contaMotivoRevisao" value="">
												<font color="#CC0000"> <bean:write name="conta"
													property="formatarAnoMesParaMesAno" /> </font>
											</logic:notEqual> </a> </font></div>
										</logic:notEmpty></td>
										<td align="left"><logic:notEmpty name="contaValoresHelper"
											property="conta">
											<div align="center"><font color="#000000"
												style="font-size:9px"
												face="Verdana, Arial, Helvetica, sans-serif"> <bean:define
												name="contaValoresHelper" property="conta" id="conta" /> <logic:equal
												name="conta" property="contaMotivoRevisao" value="">
												<bean:write name="conta" property="dataVencimentoConta"
													formatKey="date.format" />
											</logic:equal> <logic:notEqual name="conta"
												property="contaMotivoRevisao" value="">
												<font color="#CC0000"> <bean:write name="conta"
													property="dataVencimentoConta" formatKey="date.format" />
												</font>
											</logic:notEqual> </font></div>
										</logic:notEmpty></td>
										<td align="right"><logic:notEmpty name="contaValoresHelper"
											property="conta">
											<div align="right"><font color="#000000"
												style="font-size:9px"
												face="Verdana, Arial, Helvetica, sans-serif"> <bean:define
												name="contaValoresHelper" property="conta" id="conta" /> <logic:equal
												name="conta" property="contaMotivoRevisao" value="">
												<bean:write name="conta" property="valorAgua"
													formatKey="money.format" />
											</logic:equal> <logic:notEqual name="conta"
												property="contaMotivoRevisao" value="">
												<font color="#CC0000"> <bean:write name="conta"
													property="valorAgua" formatKey="money.format" /> </font>
											</logic:notEqual> </font></div>
										</logic:notEmpty></td>
										<td align="rigth">
										<div align="right"><font color="#000000" style="font-size:9px"
											face="Verdana, Arial, Helvetica, sans-serif"> <logic:notEmpty
											name="contaValoresHelper" property="conta">
											<bean:define name="contaValoresHelper" property="conta"
												id="conta" />
											<logic:equal name="conta" property="contaMotivoRevisao"
												value="">
												<bean:write name="conta" property="valorEsgoto"
													formatKey="money.format" />
											</logic:equal>
											<logic:notEqual name="conta" property="contaMotivoRevisao"
												value="">
												<font color="#CC0000"> <bean:write name="conta"
													property="valorEsgoto" formatKey="money.format" /> </font>
											</logic:notEqual>
										</logic:notEmpty> </font></div>
										</td>
										<td align="right"><logic:notEmpty name="contaValoresHelper"
											property="conta">
											<div align="right"><font color="#000000"
												style="font-size:9px"
												face="Verdana, Arial, Helvetica, sans-serif"> <logic:notEqual
												name="contaValoresHelper" property="conta.debitos" value="0">
												<a
													href="javascript:abrirPopup('exibirConsultarDebitoCobradoAction.do?contaID=<bean:define name="contaValoresHelper" property="conta" id="conta" /><bean:write name="conta" property="id" />&tipoConsulta=conta', 600, 800);">
												<logic:equal name="conta" property="contaMotivoRevisao"
													value="">
													<bean:write name="contaValoresHelper"
														property="conta.debitos" formatKey="money.format" />
												</logic:equal> <logic:notEqual name="conta"
													property="contaMotivoRevisao" value="">
													<font color="#CC0000"> <bean:write
														name="contaValoresHelper" property="conta.debitos"
														formatKey="money.format" /> </font>
												</logic:notEqual> </a>
											</logic:notEqual> <logic:equal name="contaValoresHelper"
												property="conta.debitos" value="0">
												<logic:equal name="conta" property="contaMotivoRevisao"
													value="">
													<bean:write name="contaValoresHelper"
														property="conta.debitos" formatKey="money.format" />
												</logic:equal>
												<logic:notEqual name="conta" property="contaMotivoRevisao"
													value="">
													<font color="#CC0000"> <bean:write
														name="contaValoresHelper" property="conta.debitos"
														formatKey="money.format" /> </font>
												</logic:notEqual>
											</logic:equal> </font></div>
										</logic:notEmpty></td>
										<td align="right"><logic:notEmpty name="contaValoresHelper"
											property="conta">
											<div align="right"><font color="#000000"
												style="font-size:9px"
												face="Verdana, Arial, Helvetica, sans-serif"> <logic:notEqual
												name="contaValoresHelper" property="conta.valorCreditos"
												value="0">
												<a
													href="javascript:abrirPopup('exibirConsultarCreditoRealizadoAction.do?contaID=<bean:define name="contaValoresHelper"	property="conta" id="conta" /><bean:write name="conta" property="id" />&tipoConsulta=conta', 600, 800);">
												<logic:equal name="conta" property="contaMotivoRevisao"
													value="">
													<bean:write name="contaValoresHelper"
														property="conta.valorCreditos" formatKey="money.format" />
												</logic:equal> <logic:notEqual name="conta"
													property="contaMotivoRevisao" value="">
													<font color="#CC0000"> <bean:write
														name="contaValoresHelper" property="conta.valorCreditos"
														formatKey="money.format" /> </font>
												</logic:notEqual> </a>
											</logic:notEqual> <logic:equal name="contaValoresHelper"
												property="conta.valorCreditos" value="0">
												<logic:equal name="conta" property="contaMotivoRevisao"
													value="">
													<bean:write name="contaValoresHelper"
														property="conta.valorCreditos" formatKey="money.format" />
												</logic:equal>
												<logic:notEqual name="conta" property="contaMotivoRevisao"
													value="">
													<font color="#CC0000"> <bean:write
														name="contaValoresHelper" property="conta.valorCreditos"
														formatKey="money.format" /> </font>
												</logic:notEqual>
											</logic:equal> </font></div>
										</logic:notEmpty></td>




										<td align="right"><logic:notEmpty name="contaValoresHelper"
											property="conta">
											<div align="right"><font color="#000000"
												style="font-size:9px"
												face="Verdana, Arial, Helvetica, sans-serif"> <bean:define
												name="contaValoresHelper" property="conta" id="conta" /> <logic:equal
												name="conta" property="contaMotivoRevisao" value="">
												<bean:write name="conta" property="valorImposto"
													formatKey="money.format" />
											</logic:equal> <logic:notEqual name="conta"
												property="contaMotivoRevisao" value="">
												<font color="#CC0000"> <bean:write name="conta"
													property="valorImposto" formatKey="money.format" /> </font>
											</logic:notEqual> </font></div>
										</logic:notEmpty></td>





										<td align="right"><logic:notEmpty name="contaValoresHelper"
											property="conta">
											<div align="right"><font color="#000000"
												style="font-size:9px"
												face="Verdana, Arial, Helvetica, sans-serif"> <bean:define
												name="contaValoresHelper" property="conta" id="conta" /> <logic:equal
												name="conta" property="contaMotivoRevisao" value="">
												<bean:write name="conta" property="valorTotal"
													formatKey="money.format" />
											</logic:equal> <logic:notEqual name="conta"
												property="contaMotivoRevisao" value="">
												<font color="#CC0000"> <bean:write name="conta"
													property="valorTotal" formatKey="money.format" /> </font>
											</logic:notEqual> </font></div>
										</logic:notEmpty></td>
										<td align="right"><logic:notEmpty name="contaValoresHelper"
											property="conta">
											<div align="right" class="style9"><font color="#000000"
												style="font-size:9px"
												face="Verdana, Arial, Helvetica, sans-serif"> <logic:notEqual
												name="contaValoresHelper" property="valorTotalContaValores"
												value="0">
												<a
													href="javascript:abrirPopup('exibirValorAtualizacaoConsultarPopupAction.do?multa=<bean:write name="contaValoresHelper" property="valorMulta" />&juros=<bean:write name="contaValoresHelper" property="valorJurosMora" />&atualizacao=<bean:write name="contaValoresHelper" property="valorAtualizacaoMonetaria" />', 300, 650);">
												<logic:equal name="conta" property="contaMotivoRevisao"
													value="">
													<bean:write name="contaValoresHelper"
														property="valorTotalContaValores" formatKey="money.format" />
												</logic:equal> <logic:notEqual name="conta"
													property="contaMotivoRevisao" value="">
													<font color="#CC0000"> <bean:write
														name="contaValoresHelper"
														property="valorTotalContaValores" formatKey="money.format" />
													</font>
												</logic:notEqual> </a>
											</logic:notEqual> <logic:equal name="contaValoresHelper"
												property="valorTotalContaValores" value="0">
												<logic:equal name="conta" property="contaMotivoRevisao"
													value="">
													<bean:write name="contaValoresHelper"
														property="valorTotalContaValores" formatKey="money.format" />
												</logic:equal>
												<logic:notEqual name="conta" property="contaMotivoRevisao"
													value="">
													<font color="#CC0000"> <bean:write
														name="contaValoresHelper"
														property="valorTotalContaValores" formatKey="money.format" />
													</font>
												</logic:notEqual>
											</logic:equal> </font></div>
										</logic:notEmpty></td>
										<td align="left"><logic:notEmpty name="contaValoresHelper"
											property="conta">
											<div align="left"><font color="#000000" style="font-size:9px"
												face="Verdana, Arial, Helvetica, sans-serif"> <bean:define
												name="contaValoresHelper" property="conta" id="conta" /> <bean:define
												name="conta" property="debitoCreditoSituacaoAtual"
												id="debitoCreditoSituacaoAtual" /> <logic:equal
												name="conta" property="contaMotivoRevisao" value="">
												<bean:write name="debitoCreditoSituacaoAtual"
													property="descricaoAbreviada" />
											</logic:equal> <logic:notEqual name="conta"
												property="contaMotivoRevisao" value="">
												<font color="#CC0000"> <bean:write
													name="debitoCreditoSituacaoAtual"
													property="descricaoAbreviada" /> </font>
											</logic:notEqual> </font></div>
										</logic:notEmpty></td>
									</tr>
								</logic:iterate>
								<logic:notEmpty name="colecaoContaValores">
									<%if (cor.equalsIgnoreCase("#cbe5fe")) {
					cor = "#FFFFFF";%>
									<tr bgcolor="#FFFFFF">
										<%} else {
					cor = "#cbe5fe";%>
									<tr bgcolor="#cbe5fe">
										<%}%>
										<td bgcolor="#90c7fc" align="left">
										<div class="style9" align="center"><font color="#000000"
											style="font-size:9px"
											face="Verdana, Arial, Helvetica, sans-serif"> <strong>Total</strong>
										</font></div>
										</td>
										<td align="left">&nbsp;</td>
										<td align="left">&nbsp;</td>
										<td align="left">&nbsp;</td>
										<td align="right">
										<div align="right"><font color="#000000" style="font-size:9px"
											face="Verdana, Arial, Helvetica, sans-serif"> <%=session.getAttribute("valorAgua")%>
										</font></div>
										</td>
										<td align="rigth">
										<div align="right"><font color="#000000" style="font-size:9px"
											face="Verdana, Arial, Helvetica, sans-serif"> <%=session.getAttribute("valorEsgoto")%>
										</font></div>
										</td>
										<td align="right">
										<div align="right"><font color="#000000" style="font-size:9px"
											face="Verdana, Arial, Helvetica, sans-serif"> <%=session.getAttribute("valorDebito")%>
										</font></div>
										</td>
										<td align="right">
										<div align="right"><font color="#000000" style="font-size:9px"
											face="Verdana, Arial, Helvetica, sans-serif"> <%=session.getAttribute("valorCredito")%>
										</font></div>
										</td>

										<td align="right">
										<div align="right"><font color="#000000" style="font-size:9px"
											face="Verdana, Arial, Helvetica, sans-serif"> <%=session.getAttribute("valorImposto")%>
										</font></div>
										</td>

										<td align="right">
										<div align="right"><font color="#000000" style="font-size:9px"
											face="Verdana, Arial, Helvetica, sans-serif"> <%=session.getAttribute("valorConta")%>
										</font></div>
										</td>
										<td align="right">
										<div align="right" class="style9"><font color="#000000"
											style="font-size:9px"
											face="Verdana, Arial, Helvetica, sans-serif"> <%=session.getAttribute("valorAcrescimo")%>
										</font></div>
										</td>
										<td align="left">
										<div align="left"><font color="#000000" style="font-size:9px"
											face="Verdana, Arial, Helvetica, sans-serif"> </font></div>
										</td>
									</tr>
								</logic:notEmpty>
							</logic:present>
							<%} else {%>
							<tr bordercolor="#000000">
							
								<td width="5%" bgcolor="#90c7fc" align="center">
								<div class="style9">
								<font color="#000000" style="font-size:9px"
									face="Verdana, Arial, Helvetica, sans-serif">
									<strong><a href="javascript:facilitador(document.forms[0].checkConta,'contasSelecionadas');" id="0">Todos</a></strong>
								</font>
								</div>
								</td>
							
								<td width="10%" bgcolor="#90c7fc" align="center">
								<div class="style9"><font color="#000000" style="font-size:9px"
									face="Verdana, Arial, Helvetica, sans-serif"> <strong>Matrícula</strong>
								</font></div>
								</td>
								<td width="10%" bgcolor="#90c7fc">
								<div align="center" class="style9"><font color="#000000"
									style="font-size:9px"
									face="Verdana, Arial, Helvetica, sans-serif"> <strong>M&ecirc;s/Ano</strong>
								</font></div>
								</td>
								<td width="9%" bgcolor="#90c7fc">
								<div align="center" class="style9"><font color="#000000"
									style="font-size:9px"
									face="Verdana, Arial, Helvetica, sans-serif"> <strong>Vencimento</strong>
								</font></div>
								</td>
								<td width="9%" bgcolor="#90c7fc">
								<div align="center" class="style9"><font color="#000000"
									style="font-size:9px"
									face="Verdana, Arial, Helvetica, sans-serif"> <strong>Valor de
								&Aacute;gua </strong> </font></div>
								</td>
								<td width="9%" bgcolor="#90c7fc">
								<div align="center" class="style9"><font color="#000000"
									style="font-size:9px"
									face="Verdana, Arial, Helvetica, sans-serif"> <strong>Valor de
								Esgoto</strong> </font></div>
								</td>
								<td width="8%" bgcolor="#90c7fc">
								<div align="center" class="style9"><font color="#000000"
									style="font-size:9px"
									face="Verdana, Arial, Helvetica, sans-serif"> <strong>Valor dos
								<br>
								D&eacute;bitos</strong> </font></div>
								</td>
								<td width="8%" bgcolor="#90c7fc">
								<div align="center" class="style9"><font color="#000000"
									style="font-size:9px"
									face="Verdana, Arial, Helvetica, sans-serif"> <strong>Valor dos
								Creditos</strong> </font></div>
								</td>

								<td width="8%" bgcolor="#90c7fc">
								<div align="center" class="style9"><font color="#000000"
									style="font-size:9px"
									face="Verdana, Arial, Helvetica, sans-serif"> <strong>Valor dos
								Impostos</strong> </font></div>
								</td>

								<td width="8%" bgcolor="#90c7fc">
								<div align="center" class="style9"><font color="#000000"
									style="font-size:9px"
									face="Verdana, Arial, Helvetica, sans-serif"> <strong>Valor da
								Conta</strong> </font></div>
								</td>
								<td width="8%" bgcolor="#90c7fc">
								<div align="center" class="style9"><font color="#000000"
									style="font-size:9px"
									face="Verdana, Arial, Helvetica, sans-serif"> <strong>Acr&eacute;sc.
								Impont.</strong><strong></strong> </font></div>
								</td>
								<td width="8%" bgcolor="#90c7fc">
								<div align="center" class="style9"><font color="#000000"
									style="font-size:9px"
									face="Verdana, Arial, Helvetica, sans-serif"> <strong>Sit.</strong>
								</font></div>
								</td>
							</tr>
							<tr>
								<td height="100" colspan="12">
								<div style="width: 100%; height: 100%; overflow: auto;">
								<table width="100%">
									<logic:present name="colecaoContaValores">
										<logic:iterate name="colecaoContaValores"
											id="contaValoresHelper" type="ContaValoresHelper">
											<%if (cor.equalsIgnoreCase("#cbe5fe")) {
					cor = "#FFFFFF";%>
											<tr bgcolor="#FFFFFF">
												<%} else {
					cor = "#cbe5fe";%>
											<tr bgcolor="#cbe5fe">
												<%}%>
												
												<td align="center" width="5%">
												<html:multibox property="contasSelecionadas">
													<bean:define name="contaValoresHelper" property="conta" id="conta" />
													<bean:write name="conta" property="id" />
												</html:multibox>
												</td>
												
												<td width="10%">
												<div align="left" class="style9"><bean:define
													name="contaValoresHelper" property="conta" id="conta" /> <font
													color="#000000" style="font-size:9px"
													face="Verdana, Arial, Helvetica, sans-serif"> <logic:notEmpty
													name="contaValoresHelper" property="conta">
													<logic:equal name="conta" property="contaMotivoRevisao"
														value="">
														<bean:write name="contaValoresHelper"
															property="conta.imovel.id" />
													</logic:equal>
													<logic:notEqual name="conta" property="contaMotivoRevisao"
														value="">
														<font color="#CC0000"> <bean:write
															name="contaValoresHelper" property="conta.imovel.id" />
														</font>
													</logic:notEqual>
												</logic:notEmpty> </font></div>
												</td>
												<td width="9%" align="left">
												<div align="left" class="style9"><font color="#000000"
													style="font-size:9px"
													face="Verdana, Arial, Helvetica, sans-serif"> <logic:notEmpty
													name="contaValoresHelper" property="conta">
													<bean:define name="contaValoresHelper" property="conta"
														id="conta" />
													<logic:equal name="conta" property="contaMotivoRevisao"
														value="">
														<a
															href="javascript:abrirPopup('exibirConsultarContaAction.do?contaID=<bean:define name="contaValoresHelper" property="conta" id="conta" /><bean:write name="conta" property="id" />&tipoConsulta=conta', 600, 800);"><bean:write
															name="conta" property="formatarAnoMesParaMesAno" /></a>
													</logic:equal>
													<logic:notEqual name="conta" property="contaMotivoRevisao"
														value="">
														<font color="#CC0000"> <a
															href="javascript:abrirPopup('exibirConsultarContaAction.do?contaID=<bean:define name="contaValoresHelper" property="conta" id="conta" /><bean:write name="conta" property="id" />&tipoConsulta=conta', 600, 800);"><bean:write
															name="conta" property="formatarAnoMesParaMesAno" /></a>
														</font>
													</logic:notEqual>
												</logic:notEmpty> </font></div>
												</td>
												<td width="12%" align="left">
												<div align="left" class="style9"><font color="#000000"
													style="font-size:9px"
													face="Verdana, Arial, Helvetica, sans-serif"> <logic:notEmpty
													name="contaValoresHelper" property="conta">
													<bean:define name="contaValoresHelper" property="conta"
														id="conta" />
													<logic:equal name="conta" property="contaMotivoRevisao"
														value="">
														<bean:write name="conta" property="dataVencimentoConta"
															formatKey="date.format" />
													</logic:equal>
													<logic:notEqual name="conta" property="contaMotivoRevisao"
														value="">
														<font color="#CC0000"> <bean:write name="conta"
															property="dataVencimentoConta" formatKey="date.format" />
														</font>
													</logic:notEqual>
												</logic:notEmpty> </font></div>
												</td>
												<td width="8%" align="right">
												<div align="right" class="style9"><font color="#000000"
													style="font-size:9px"
													face="Verdana, Arial, Helvetica, sans-serif"> <logic:notEmpty
													name="contaValoresHelper" property="conta">
													<bean:define name="contaValoresHelper" property="conta"
														id="conta" />
													<logic:equal name="conta" property="contaMotivoRevisao"
														value="">
														<bean:write name="conta" property="valorAgua"
															formatKey="money.format" />
													</logic:equal>
													<logic:notEqual name="conta" property="contaMotivoRevisao"
														value="">
														<font color="#CC0000"> <bean:write name="conta"
															property="valorAgua" formatKey="money.format" /> </font>
													</logic:notEqual>
												</logic:notEmpty> </font></div>
												</td>
												<td width="8%" align="right">
												<div align="right" class="style9"><font color="#000000"
													style="font-size:9px"
													face="Verdana, Arial, Helvetica, sans-serif"> <logic:notEmpty
													name="contaValoresHelper" property="conta">
													<bean:define name="contaValoresHelper" property="conta"
														id="conta" />
													<logic:equal name="conta" property="contaMotivoRevisao"
														value="">
														<bean:write name="conta" property="valorEsgoto"
															formatKey="money.format" />
													</logic:equal>
													<logic:notEqual name="conta" property="contaMotivoRevisao"
														value="">
														<font color="#CC0000"> <bean:write name="conta"
															property="valorEsgoto" formatKey="money.format" /> </font>
													</logic:notEqual>
												</logic:notEmpty> </font></div>
												</td>
												<td width="8%" align="right">
												<div align="right" class="style9"><font color="#000000"
													style="font-size:9px"
													face="Verdana, Arial, Helvetica, sans-serif"> <logic:notEmpty
													name="contaValoresHelper" property="conta">
													<logic:notEqual name="contaValoresHelper"
														property="conta.debitos" value="0">
														<a
															href="javascript:abrirPopup('exibirConsultarDebitoCobradoAction.do?contaID=<bean:define name="contaValoresHelper" property="conta" id="conta" /><bean:write name="conta" property="id" />&tipoConsulta=conta', 600, 800);">
														<logic:equal name="conta" property="contaMotivoRevisao"
															value="">
															<bean:write name="contaValoresHelper"
																property="conta.debitos" formatKey="money.format" />
														</logic:equal> <logic:notEqual name="conta"
															property="contaMotivoRevisao" value="">
															<font color="#CC0000"> <bean:write
																name="contaValoresHelper" property="conta.debitos"
																formatKey="money.format" /> </font>
														</logic:notEqual> </a>
													</logic:notEqual>
													<logic:equal name="contaValoresHelper"
														property="conta.debitos" value="0">
														<logic:equal name="conta" property="contaMotivoRevisao"
															value="">
															<bean:write name="contaValoresHelper"
																property="conta.debitos" formatKey="money.format" />
														</logic:equal>
														<logic:notEqual name="conta" property="contaMotivoRevisao"
															value="">
															<font color="#CC0000"> <bean:write
																name="contaValoresHelper" property="conta.debitos"
																formatKey="money.format" /> </font>
														</logic:notEqual>
													</logic:equal>
												</logic:notEmpty> </font></div>
												</td>
												<td width="10%" align="right">
												<div align="right" class="style9"><font color="#000000"
													style="font-size:9px"
													face="Verdana, Arial, Helvetica, sans-serif"> <logic:notEmpty
													name="contaValoresHelper" property="conta">
													<logic:notEqual name="contaValoresHelper"
														property="conta.valorCreditos" value="0">
														<a
															href="javascript:abrirPopup('exibirConsultarCreditoRealizadoAction.do?contaID=<bean:define name="contaValoresHelper"	property="conta" id="conta" /><bean:write name="conta" property="id" />&tipoConsulta=conta', 600, 800);">
														<logic:equal name="conta" property="contaMotivoRevisao"
															value="">
															<bean:write name="contaValoresHelper"
																property="conta.valorCreditos" formatKey="money.format" />
														</logic:equal> <logic:notEqual name="conta"
															property="contaMotivoRevisao" value="">
															<font color="#CC0000"> <bean:write
																name="contaValoresHelper" property="conta.valorCreditos"
																formatKey="money.format" /> </font>
														</logic:notEqual> </a>
													</logic:notEqual>
													<logic:equal name="contaValoresHelper"
														property="conta.valorCreditos" value="0">
														<logic:equal name="conta" property="contaMotivoRevisao"
															value="">
															<bean:write name="contaValoresHelper"
																property="conta.valorCreditos" formatKey="money.format" />
														</logic:equal>
														<logic:notEqual name="conta" property="contaMotivoRevisao"
															value="">
															<font color="#CC0000"> <bean:write
																name="contaValoresHelper" property="conta.valorCreditos"
																formatKey="money.format" /> </font>
														</logic:notEqual>
													</logic:equal>
												</logic:notEmpty> </font></div>
												</td>


												<td width="10%" align="right">
												<div align="right" class="style9"><font color="#000000"
													style="font-size:9px"
													face="Verdana, Arial, Helvetica, sans-serif"> <logic:notEmpty
													name="contaValoresHelper" property="conta">
													<bean:define name="contaValoresHelper" property="conta"
														id="conta" />
													<logic:equal name="conta" property="contaMotivoRevisao"
														value="">
														<bean:write name="conta" property="valorImposto"
															formatKey="money.format" />
													</logic:equal>
													<logic:notEqual name="conta" property="contaMotivoRevisao"
														value="">
														<font color="#CC0000"> <bean:write name="conta"
															property="valorImposto" formatKey="money.format" /> </font>
													</logic:notEqual>
												</logic:notEmpty> </font></div>
												</td>



												<td width="8%" align="right">
												<div align="right" class="style9"><font color="#000000"
													style="font-size:9px"
													face="Verdana, Arial, Helvetica, sans-serif"> <logic:notEmpty
													name="contaValoresHelper" property="conta">
													<bean:define name="contaValoresHelper" property="conta"
														id="conta" />
													<logic:equal name="conta" property="contaMotivoRevisao"
														value="">
														<bean:write name="conta" property="valorTotal"
															formatKey="money.format" />
													</logic:equal>
													<logic:notEqual name="conta" property="contaMotivoRevisao"
														value="">
														<font color="#CC0000"> <bean:write name="conta"
															property="valorTotal" formatKey="money.format" /> </font>
													</logic:notEqual>
												</logic:notEmpty> </font></div>
												</td>
												<td width="9%" align="right">
												<div align="right" class="style9"><font color="#000000"
													style="font-size:9px"
													face="Verdana, Arial, Helvetica, sans-serif"> <logic:notEqual
													name="contaValoresHelper" property="valorTotalContaValores"
													value="0">
													<a
														href="javascript:abrirPopup('exibirValorAtualizacaoConsultarPopupAction.do?multa=<bean:write name="contaValoresHelper" property="valorMulta" />&juros=<bean:write name="contaValoresHelper" property="valorJurosMora" />&atualizacao=<bean:write name="contaValoresHelper" property="valorAtualizacaoMonetaria" />', 300, 650);">
													<logic:equal name="conta" property="contaMotivoRevisao"
														value="">
														<bean:write name="contaValoresHelper"
															property="valorTotalContaValores"
															formatKey="money.format" />
													</logic:equal> <logic:notEqual name="conta"
														property="contaMotivoRevisao" value="">
														<font color="#CC0000"> <bean:write
															name="contaValoresHelper"
															property="valorTotalContaValores"
															formatKey="money.format" /> </font>
													</logic:notEqual> </a>
												</logic:notEqual> <logic:equal name="contaValoresHelper"
													property="valorTotalContaValores" value="0">
													<logic:equal name="conta" property="contaMotivoRevisao"
														value="">
														<bean:write name="contaValoresHelper"
															property="valorTotalContaValores"
															formatKey="money.format" />
													</logic:equal>
													<logic:notEqual name="conta" property="contaMotivoRevisao"
														value="">
														<font color="#CC0000"> <bean:write
															name="contaValoresHelper"
															property="valorTotalContaValores"
															formatKey="money.format" /> </font>
													</logic:notEqual>
												</logic:equal> </font></div>
												</td>
												<td width="8%" align="left">
												<div align="left" class="style9"><font color="#000000"
													style="font-size:9px"
													face="Verdana, Arial, Helvetica, sans-serif"> <logic:notEmpty
													name="contaValoresHelper" property="conta">
													<bean:define name="contaValoresHelper" property="conta"
														id="conta" />
													<bean:define name="conta"
														property="debitoCreditoSituacaoAtual"
														id="debitoCreditoSituacaoAtual" />
													<logic:equal name="conta" property="contaMotivoRevisao"
														value="">
														<bean:write name="debitoCreditoSituacaoAtual"
															property="descricaoAbreviada" />
													</logic:equal>
													<logic:notEqual name="conta" property="contaMotivoRevisao"
														value="">
														<font color="#CC0000"> <bean:write
															name="debitoCreditoSituacaoAtual"
															property="descricaoAbreviada" /> </font>
													</logic:notEqual>
												</logic:notEmpty> </font></div>
												</td>
											</tr>
										</logic:iterate>
										<logic:notEmpty name="colecaoContaValores">
											<%if (cor.equalsIgnoreCase("#cbe5fe")) {
					cor = "#FFFFFF";%>
											<tr bgcolor="#FFFFFF">
												<%} else {
					cor = "#cbe5fe";%>
											<tr bgcolor="#cbe5fe">
												<%}%>
												<td bgcolor="#90c7fc" align="center">
												<div class="style9" align="center"><font color="#000000"
													style="font-size:9px"
													face="Verdana, Arial, Helvetica, sans-serif"> <strong>Total</strong>
												</font></div>
												</td>
												<td align="left">&nbsp;</td>
												<td align="left">&nbsp;</td>
												<td align="left">&nbsp;</td>
												<td align="right">
												<div align="right"><font color="#000000"
													style="font-size:9px"
													face="Verdana, Arial, Helvetica, sans-serif"> <%=session.getAttribute("valorAgua")%>
												</font></div>
												</td>
												<td align="rigth">
												<div align="right"><font color="#000000"
													style="font-size:9px"
													face="Verdana, Arial, Helvetica, sans-serif"> <%=session.getAttribute("valorEsgoto")%>
												</font></div>
												</td>
												<td align="right">
												<div align="right"><font color="#000000"
													style="font-size:9px"
													face="Verdana, Arial, Helvetica, sans-serif"> <%=session.getAttribute("valorDebito")%>
												</font></div>
												</td>
												<td align="right">
												<div align="right"><font color="#000000"
													style="font-size:9px"
													face="Verdana, Arial, Helvetica, sans-serif"> <%=session.getAttribute("valorCredito")%>
												</font></div>
												</td>



												<td align="right">
												<div align="right"><font color="#000000"
													style="font-size:9px"
													face="Verdana, Arial, Helvetica, sans-serif"> <%=session.getAttribute("valorImposto")%>
												</font></div>
												</td>





												<td align="right">
												<div align="right"><font color="#000000"
													style="font-size:9px"
													face="Verdana, Arial, Helvetica, sans-serif"> <%=session.getAttribute("valorConta")%>
												</font></div>
												</td>
												<td align="right">
												<div align="right" class="style9"><font color="#000000"
													style="font-size:9px"
													face="Verdana, Arial, Helvetica, sans-serif"> <%=session.getAttribute("valorAcrescimo")%>
												</font></div>
												</td>
												<td align="left">
												<div align="left"><font color="#000000"
													style="font-size:9px"
													face="Verdana, Arial, Helvetica, sans-serif"> </font></div>
												</td>
											</tr>
										</logic:notEmpty>
									</logic:present>
								</table>
								</div>
								</td>
							</tr>
							<%}

			%>
						</logic:notEmpty>
					</table>
					</td>
				</tr>
				
				<tr>
					<td colspan="4">
					<table width="100%" align="center" bgcolor="#90c7fc" border="0">
						<%cor = "#cbe5fe";%>
						<tr bordercolor="#79bbfd">
							<td colspan="10" align="center" bgcolor="#79bbfd">
							<strong>Contas Inconformes</strong>
							</td>
						</tr>
						<logic:notEmpty name="colecaoPagamentosImovelContaInconformes" scope="session">
							<tr bordercolor="#000000">
								<td bgcolor="#90c7fc" width="14%" align="center" rowspan="2">
									<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif">
										<strong>M&ecirc;s/Ano Conta</strong>
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
							<tr>
								<td height="auto" colspan="10">
									<div style="width: 100%; max-height: 100px; overflow: auto;">
										<table width="100%">
											<%int cont1 = 1;%>
												<logic:iterate name="colecaoPagamentosImovelContaInconformes"
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
																<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif">
																	<logic:notEmpty name="pagamento" property="contaGeral.conta.id">
																		<logic:notEmpty name="pagamento"
																			property="contaGeral.conta.referencia">
																			<a href="javascript:abrirPopup('exibirConsultarContaAction.do?tipoConsulta=conta&contaID=<%="" + pagamento.getContaGeral().getId() %>' , 600, 800);">${pagamento.contaGeral.conta.formatarAnoMesParaMesAno}</a>
																		</logic:notEmpty>
																	</logic:notEmpty>
																</font>
															</logic:notEmpty>
															
															<logic:notEmpty name="pagamento" property="contaGeral.contaHistorico">
																<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif">
																	<logic:notEmpty name="pagamento" property="contaGeral.contaHistorico.id">
																		<logic:notEmpty name="pagamento"
																			property="contaGeral.contaHistorico.anoMesReferenciaConta">
																			<a href="javascript:abrirPopup('exibirConsultarContaAction.do?tipoConsulta=conta&contaID=<%="" + pagamento.getContaGeral().getId() %>' , 600, 800);">${pagamento.contaGeral.contaHistorico.formatarAnoMesParaMesAno}</a>
																		</logic:notEmpty>
																	</logic:notEmpty>
																</font>
															</logic:notEmpty>
															
														</logic:notEmpty> 
														<logic:empty name="pagamento" property="contaGeral">
															<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif">
																${pagamento.formatarAnoMesPagamentoParaMesAno}
															</font>
														</logic:empty></td>
														<td width="19%" align="right">
															<logic:notEmpty	name="pagamento" property="contaGeral">
																<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif">
																	<logic:notEmpty name="pagamento" property="contaGeral.conta">
																		<logic:notEmpty name="pagamento" property="contaGeral.conta.valorTotal">
																			<bean:write name="pagamento" property="contaGeral.conta.valorTotal"
																				formatKey="money.format" />&nbsp;
																		</logic:notEmpty>
																	</logic:notEmpty>
																</font>
																<logic:notEmpty name="pagamento" property="contaGeral.contaHistorico">
																	<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif">
																		<logic:notEmpty name="pagamento" property="contaGeral.contaHistorico.valorTotal">
																			<bean:write name="pagamento" property="contaGeral.contaHistorico.valorTotal"
																				formatKey="money.format" />&nbsp;
																		</logic:notEmpty>
																	</font>
																</logic:notEmpty>
															</logic:notEmpty>
														</td>
														<td width="19%" align="right">
															<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif">
																<bean:write name="pagamento" property="valorPagamento" formatKey="money.format" />&nbsp;
															</font>
														</td>
														
														<td width="16%" align="center">
															<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif">
																<a href="javascript:abrirPopup('exibirConsultarPagamentoPopupAction.do?idPagamento=${pagamento.id}' , 210, 510);">
																<bean:write name="pagamento" property="dataPagamento" formatKey="date.format" /></a>&nbsp;
															</font>
														</td>
													
														<td width="16%">
															<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif">	
																${pagamento.pagamentoSituacaoAnterior.descricaoAbreviada}&nbsp;
															</font>
														</td>
														<td width="16%">
															<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif">
																${pagamento.pagamentoSituacaoAtual.descricaoAbreviada}&nbsp;
															</font>
														</td>
													</tr>
												</logic:iterate>
										</table>
									</div>
								</td>
							</tr>
						</logic:notEmpty>
					</table>
					</td>
				</tr>				
				
				
				
				
				<tr>
					<logic:empty name="colecaoContaValores">
						<td colspan="4" align="right"><input type="button" name=""
							value="Imprimir Extrato de Débito Conta(s)"
							class="bottonRightCol" disabled="true" /></td>
					</logic:empty>
					<logic:notEmpty name="colecaoContaValores">
						<td colspan="4" align="right"><input type="button" name=""
							value="Imprimir Extrato de Débito Conta(s)"
							class="bottonRightCol"
							onclick="javascript:contasSelecionadas2(document.forms[0],'/gsan/emissaoExtratoConsultarDebitoClienteAction.do?tipo=conta');" />
<!--							onclick="window.location.href='<html:rewrite page="/gerarRelatorioExtratoDebitoClienteAction.do?tipo=conta"/>'" />-->
						</td>
					</logic:notEmpty>
				</tr>
				
				
				
				<tr>
					<td colspan="7">
					<table width="100%" align="center" bgcolor="#90c7fc" border="0">
						<tr bordercolor="#000000">
							<td colspan="7" align="center" bgcolor="#79bbfd"><strong>D&eacute;bitos	A Cobrar</strong></td>
						</tr>
						<tr bordercolor="#000000">
						
							<td width="5%" bgcolor="#90c7fc" align="center">
							<div class="style9">
							<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif">
								<strong><a href="javascript:facilitador(document.forms[0].checkDebito,'debitosSelecionados');" id="0">Todos</a></strong>
							</font>
							</div>
							</td>
						 	
							<td width="10%" bgcolor="#90c7fc">
							<div align="center" class="style9"><font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> <strong>Matrícula</strong>	</font></div>
							</td>
							<td width="40%" bgcolor="#90c7fc">
							<div align="center" class="style9"><font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> <strong>Tipo do D&eacute;bito</strong> </font></div>
							</td>
							<td width="10%" bgcolor="#90c7fc">
							<div align="center" class="style9"><font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> <strong>M&ecirc;s/Ano Refer&ecirc;ncia</strong> </font></div>
							</td>
							<td width="10%" bgcolor="#90c7fc">
							<div align="center" class="style9"><font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> <strong>M&ecirc;s/Ano Cobran&ccedil;a</strong> </font></div>
							</td>
							<td width="10%" bgcolor="#90c7fc">
							<div align="center" class="style9"><font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> <strong>Parcelas a cobrar</strong> </font></div>
							</td>
							<td width="15%" bgcolor="#90c7fc" height="20">
							<div align="center" class="style9"><font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> <strong>Valor a cobrar</strong> </font></div>
							</td>
						</tr>
						<%cor = "#cbe5fe";%>
						<tr>
							<td colspan="10">
							<div style="width: 100%; height: 100%; overflow: auto;">
							<table width="100%">
								<logic:present name="colecaoDebitoACobrar">
									<logic:iterate name="colecaoDebitoACobrar" id="debitoACobrar" type="DebitoACobrar">
										<%if (cor.equalsIgnoreCase("#cbe5fe")) {
										cor = "#FFFFFF";%>
										<tr bgcolor="#FFFFFF">
											<%} else {
										cor = "#cbe5fe";%>
										<tr bgcolor="#cbe5fe">
											<%}%>
											
											<td align="center" width="5%">
											<html:multibox property="debitosSelecionados">
												<bean:write name="debitoACobrar" property="id" />
											</html:multibox>
											</td>
											
											<td width="10%">
											<div align="left" class="style9"><font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> <bean:write name="debitoACobrar" property="imovel.id" /> </font></div>
											</td>
											<td width="43%">
											<div align="left" class="style9"><font color="#000000" style="font-size:9px"
												face="Verdana, Arial, Helvetica, sans-serif"> <logic:notEmpty
												name="debitoACobrar" property="imovel">
												<a
													href="javascript:abrirPopup('exibirConsultarDebitoACobrarAction.do?imovelID=<bean:define name="debitoACobrar" property="imovel" id="imovel" /><bean:write name="imovel" property="id" />&debitoID=<bean:write name="debitoACobrar" property="id" />', 570, 720);">
												<bean:define name="debitoACobrar" property="debitoTipo"
													id="debitoTipo" /> <logic:notEmpty name="debitoTipo"
													property="descricao">
													<bean:write name="debitoTipo" property="descricao" />
												</logic:notEmpty> </a>
											</logic:notEmpty> </font></div>
											</td>
											<td width="10%">
											<div align="center" class="style9"><font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
											<logic:notEmpty	name="debitoACobrar" property="anoMesReferenciaDebito">
												<%=Util.formatarAnoMesParaMesAno(((DebitoACobrar) debitoACobrar).getAnoMesReferenciaDebito().toString())%>
											</logic:notEmpty> </font></div>
											&nbsp;</td>
											<td width="10%">
												<div align="center" class="style9"><font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
												<logic:present	name="debitoACobrar" property="anoMesCobrancaDebito">
													<logic:notEqual name="debitoACobrar" property="anoMesCobrancaDebito" value="0">
														<bean:write name="debitoACobrar" property="formatarAnoMesCobrancaDebito" />
													</logic:notEqual>
												</logic:present>&nbsp; </font></div>
											</td>
											<td width="10%">
											<div align="center" class="style9"><font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
											<logic:notEmpty	name="debitoACobrar" property="parcelasRestanteComBonus">
												<bean:write name="debitoACobrar" property="parcelasRestanteComBonus" />
											</logic:notEmpty> </font></div>
											</td>
											<td width="17%">
											<div align="right" class="style9"><font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
												<logic:notEmpty name="debitoACobrar" property="valorTotalComBonus">
													<bean:write name="debitoACobrar" property="valorTotalComBonus" formatKey="money.format" />
												</logic:notEmpty> </font></div>
											</td>
										</tr>
									</logic:iterate>
									<logic:notEmpty name="colecaoDebitoACobrar">
										<%if (cor.equalsIgnoreCase("#cbe5fe")) {
										cor = "#FFFFFF";%>
										<tr bgcolor="#FFFFFF">
											<%} else {
										cor = "#cbe5fe";%>
										<tr bgcolor="#cbe5fe">
											<%}%>
											<td bgcolor="#90c7fc">
											<div align="center" class="style9"><font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> <strong>Total</strong> </font></div>
											</td>
											<td>&nbsp;</td>
											<td>&nbsp;</td>
											<td>&nbsp;</td>
											<td>&nbsp;</td>
											<td>&nbsp;</td>
											<td>
											<div align="right" class="style9"><font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> <%=session.getAttribute("valorDebitoACobrar")%> </font></div>
											</td>
										</tr>
									</logic:notEmpty>
								</logic:present>
							</table>
							</div>
							</td>
						</tr>
					</table>
					</td>
				</tr>
				
				
				
				<tr>
					<td colspan="7">
					<table width="100%" align="center" bgcolor="#90c7fc" border="0">
						<tr bordercolor="#79bbfd">
							<td colspan="7" bgcolor="#79bbfd" align="center"><strong>Cr&eacute;ditos
							A Realizar</strong></td>
						</tr>
						<tr bordercolor="#000000">
						
							<td width="5%" bgcolor="#90c7fc" align="center">
							<div class="style9">
							<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif">
								<strong><a href="javascript:facilitador(document.forms[0].checkCredito,'creditosSelecionados');" id="0">Todos</a></strong>
							</font>
							</div>
							</td>
						
							<td width="10%" bgcolor="#90c7fc" height="20">
							<div align="center" class="style9"><font color="#000000"
								style="font-size:9px"
								face="Verdana, Arial, Helvetica, sans-serif"> <strong>Matrícula</strong>
							</font></div>
							</td>
							<td width="40%" bgcolor="#90c7fc" height="20">
							<div align="center" class="style9"><font color="#000000"
								style="font-size:9px"
								face="Verdana, Arial, Helvetica, sans-serif"> <strong>Tipo do
							Cr&eacute;dito</strong> </font></div>
							</td>
							<td width="10%" bgcolor="#90c7fc">
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
							creditar</strong> </font></div>
							</td>
							<td width="15%" bgcolor="#90c7fc" height="20">
							<div align="center" class="style9"><font color="#000000"
								style="font-size:9px"
								face="Verdana, Arial, Helvetica, sans-serif"> <strong>Valor a
							creditar</strong> </font></div>
							</td>
						</tr>
						<%cor = "#cbe5fe";%>
						<logic:present name="colecaoCreditoARealizar">
							<logic:iterate name="colecaoCreditoARealizar"
								id="creditoARealizar" type="CreditoARealizar">
								<%if (cor.equalsIgnoreCase("#cbe5fe")) {
				cor = "#FFFFFF";%>
								<tr bgcolor="#FFFFFF">
									<%} else {
				cor = "#cbe5fe";%>
								<tr bgcolor="#cbe5fe">
									<%}%>
									
									<td align="center" width="5%">
									<html:multibox property="creditosSelecionados">
										<bean:write name="creditoARealizar" property="id" />
									</html:multibox>
									</td>
									
									<td>
									<div align="left" class="style9"><font color="#000000"
										style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"> <bean:write
										name="creditoARealizar" property="imovel.id" /> </font></div>
									</td>
									<td><logic:notEmpty name="creditoARealizar"
										property="creditoTipo">
										<div align="left" class="style9"><font color="#000000"
											style="font-size:9px"
											face="Verdana, Arial, Helvetica, sans-serif"> <a
											href="javascript:abrirPopup('exibirConsultarCreditoARealizarAction.do?imovelID=<bean:define name="creditoARealizar" property="imovel" id="imovel" /><bean:write name="imovel" property="id" />&creditoID=<bean:write name="creditoARealizar" property="id" />', 570, 720);">
										<bean:define name="creditoARealizar" property="creditoTipo"
											id="creditoTipo" /> <logic:notEmpty name="creditoTipo"
											property="descricao">
											<bean:write name="creditoTipo" property="descricao" />
										</logic:notEmpty> </a> </font></div>
									</logic:notEmpty></td>
									<td>
									<div align="center" class="style9"><font color="#000000"
										style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"> <logic:notEmpty
										name="creditoARealizar" property="anoMesReferenciaCredito">
										<bean:write name="creditoARealizar"
											property="formatarAnoMesReferenciaCredito" />
									</logic:notEmpty> </font></div>
									</td>
									<td>
									<div align="center" class="style9"><font color="#000000"
										style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"> <logic:notEmpty
										name="creditoARealizar" property="anoMesCobrancaCredito">
										<bean:write name="creditoARealizar"
											property="formatarAnoMesCobrancaCredito" />
									</logic:notEmpty> </font></div>
									</td>
									<td>
									<div align="center" class="style9"><font color="#000000"
										style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"> <logic:notEmpty
										name="creditoARealizar" property="parcelasRestanteComBonus">
										<bean:write name="creditoARealizar"
											property="parcelasRestanteComBonus" />
									</logic:notEmpty> </font></div>
									</td>
									<td>
									<div align="right" class="style9"><font color="#000000"
										style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"> <logic:notEmpty
										name="creditoARealizar" property="valorTotalComBonus">
										<bean:write name="creditoARealizar" property="valorTotalComBonus"
											formatKey="money.format" />
									</logic:notEmpty> </font></div>
									</td>
								</tr>
							</logic:iterate>
							<logic:notEmpty name="colecaoCreditoARealizar">
								<%if (cor.equalsIgnoreCase("#cbe5fe")) {
				cor = "#FFFFFF";%>
								<tr bgcolor="#FFFFFF">
									<%} else {
				cor = "#cbe5fe";%>
								<tr bgcolor="#cbe5fe">
									<%}%>
									<td bgcolor="#90c7fc">
									<div align="center" class="style9"><font color="#000000"
										style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"> <strong>Total</strong>
									</font></div>
									</td>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
									<td>
									<div align="right" class="style9"><font color="#000000"
										style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"> <%=session.getAttribute("valorCreditoARealizar")%>
									</font></div>
									</td>
								</tr>
							</logic:notEmpty>
						</logic:present>
					</table>
					</td>
				</tr>
				<tr>
					<td colspan="5">
					<table width="100%" align="center" bgcolor="#90c7fc" border="0">
						<tr bordercolor="#79bbfd">
							<td colspan="5" align="center" bgcolor="#79bbfd"><strong>Guias de
							Pagamento</strong></td>
						</tr>
						<tr bordercolor="#000000">
						
							<td width="5%" bgcolor="#90c7fc" align="center">
							<div class="style9">
							<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif">
								<strong><a href="javascript:facilitador(document.forms[0].checkGuia,'guiasSelecionadas');" id="0">Todos</a></strong>
							</font>
							</div>
							</td>
						
							<td width="45%" bgcolor="#90c7fc">
							<div align="center" class="style9"><font color="#000000"
								style="font-size:9px"
								face="Verdana, Arial, Helvetica, sans-serif"> <strong>Tipo do
							D&eacute;bito</strong> </font></div>
							</td>
							<td width="15%" bgcolor="#90c7fc">
							<div align="center" class="style9"><font color="#000000"
								style="font-size:9px"
								face="Verdana, Arial, Helvetica, sans-serif"> <strong>Data de
							Emiss&atilde;o</strong> </font></div>
							</td>
							<td width="15%" bgcolor="#90c7fc">
							<div align="center" class="style9"><font color="#000000"
								style="font-size:9px"
								face="Verdana, Arial, Helvetica, sans-serif"> <strong>Data de
							Vencimento</strong> </font></div>
							</td>
							<td width="20%" bgcolor="#90c7fc">
							<div align="center" class="style9"><font color="#000000"
								style="font-size:9px"
								face="Verdana, Arial, Helvetica, sans-serif"> <strong>Valor da
							Guia de Pagamento</strong> </font></div>
							</td>
						</tr>
						<%cor = "#cbe5fe";%>
						<logic:present name="colecaoGuiaPagamentoValores">
							<logic:iterate name="colecaoGuiaPagamentoValores"
								id="guiaPagamentoValoresHelper" type="GuiaPagamentoValoresHelper">
								<%if (cor.equalsIgnoreCase("#cbe5fe")) {
				cor = "#FFFFFF";%>
								<tr bgcolor="#FFFFFF">
									<%} else {
				cor = "#cbe5fe";%>
								<tr bgcolor="#cbe5fe">
									<%}%>
									
									<td align="center" width="5%">
									<html:multibox property="guiasSelecionadas">
										<bean:define name="guiaPagamentoValoresHelper" property="guiaPagamento" id="guiaPagamento" />
										<bean:write name="guiaPagamento" property="id" />
									</html:multibox>
									</td>
									
									<td>
									<div align="left" class="style9"><font color="#000000"
										style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"> <a
										href="javascript:abrirPopup('exibirConsultarGuiaPagamentoAction.do?guiaPagamentoId=<bean:define name="guiaPagamentoValoresHelper" property="guiaPagamento" id="guiaPagamento" /><bean:write name="guiaPagamento" property="id" />', 600, 800);">
									<bean:define name="guiaPagamento" property="debitoTipo"
										id="debitoTipo" /> <logic:notEmpty name="debitoTipo"
										property="descricao">
										<bean:write name="debitoTipo" property="descricao" />
									</logic:notEmpty> </a> </font></div>
									</td>
									<td>
									<div align="right" class="style9"><font color="#000000"
										style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"> <logic:notEmpty
										name="guiaPagamentoValoresHelper" property="guiaPagamento">
										<bean:define name="guiaPagamentoValoresHelper"
											property="guiaPagamento" id="guiaPagamento" />
										<bean:write name="guiaPagamento" property="dataEmissao"
											formatKey="date.format" />
									</logic:notEmpty> </font></div>
									</td>
									<td>
									<div align="right" class="style9"><font color="#000000"
										style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"> <logic:notEmpty
										name="guiaPagamentoValoresHelper" property="guiaPagamento">
										<bean:define name="guiaPagamentoValoresHelper"
											property="guiaPagamento" id="guiaPagamento" />
										<bean:write name="guiaPagamento" property="dataVencimento"
											formatKey="date.format" />
									</logic:notEmpty> </font></div>
									</td>
									<td>
									<div align="right" class="style9"><font color="#000000"
										style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"> <logic:notEmpty
										name="guiaPagamentoValoresHelper" property="guiaPagamento">
										<bean:define name="guiaPagamentoValoresHelper"
											property="guiaPagamento" id="guiaPagamento" />
										<bean:write name="guiaPagamento" property="valorDebito"
											formatKey="money.format" />
									</logic:notEmpty> </font></div>
									</td>
								</tr>
							</logic:iterate>
							<logic:notEmpty name="colecaoGuiaPagamentoValores">
								<%if (cor.equalsIgnoreCase("#cbe5fe")) {
				cor = "#FFFFFF";%>
								<tr bgcolor="#FFFFFF">
									<%} else {
				cor = "#cbe5fe";%>
								<tr bgcolor="#cbe5fe">
									<%}%>
									<td bgcolor="#90c7fc">
									<div align="center" class="style9"><font color="#000000"
										style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"> <strong>Total</strong>
									</font></div>
									</td>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
									<td>
									<div align="right" class="style9"><font color="#000000"
										style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"> <%=session.getAttribute("valorGuiaPagamento")%>
									</font></div>
									</td>
								</tr>
							</logic:notEmpty>
						</logic:present>
					</table>
					</td>
				</tr>
				<tr>
					<td colspan="4">&nbsp;</td>
				</tr>
				<tr>
					<td colspan="4">
					<table width="100%" align="center" bgcolor="#90c7fc" border="0">
						<tr bordercolor="#000000">
							<td width="50%" bgcolor="#90c7fc">
							<div align="center" class="style9"><font color="#000000"
								style="font-size:9px"
								face="Verdana, Arial, Helvetica, sans-serif"> <strong>Valor
							Total dos Débitos</strong> </font></div>
							</td>
							<td width="50%" bgcolor="#90c7fc">
							<div align="center" class="style9"><font color="#000000"
								style="font-size:9px"
								face="Verdana, Arial, Helvetica, sans-serif"> <strong>Valor
							Total dos Débitos Atualizado</strong> </font></div>
							</td>
						</tr>
						<tr bgcolor="#FFFFFF">
							<td>
							<div align="right" class="style9"><font color="#000000"
								style="font-size:9px"
								face="Verdana, Arial, Helvetica, sans-serif"> <%=session.getAttribute("valorTotalSemAcrescimo")%>
							</font></div>
							</td>
							<td>
							<div align="right" class="style9"><font color="#000000"
								style="font-size:9px"
								face="Verdana, Arial, Helvetica, sans-serif"> <%=session.getAttribute("valorTotalComAcrescimo")%>
							</font></div>
							</td>
						</tr>
					</table>
					</td>
				</tr>

				<tr>
					<td align="right"><select name="opcaoRelatorio">
						<option
							value="gerarRelatorioDebitosClienteConsultarAction.do?pesquisaCliente=sim&relatorioEndereco=sim">Relatório
						de Débitos com Endereço</option>
						<option
							value="gerarRelatorioDebitosClienteConsultarAction.do?pesquisaCliente=sim">Relatório
						de Débitos</option>
						<option
							value="gerarRelatorioDebitosResumidoClienteConsultarAction.do">Relatório
						de Débitos Resumido</option>
					</select>&nbsp;</td>
					<td align="right">
					<div align="right"><a
						href="javascript:toggleBoxCaminho('demodiv',1, document.forms[0].opcaoRelatorio.value);">
					<img border="0"
						src="<bean:message key="caminho.imagens"/>print.gif"
						title="Imprimir Débitos" /> </a></div>
					</td>
				</tr>
				<tr>
					<logic:empty name="colecaoContaValores">
						<td colspan="4" align="right"><input type="button" name=""
							value="Imprimir Extrato de Débito Total" class="bottonRightCol"
							disabled="true" /></td>
					</logic:empty>
					<logic:notEmpty name="colecaoContaValores">
						<td colspan="4" align="right"><input type="button" name=""
							value="Imprimir Extrato de Débito Total" class="bottonRightCol"
							onclick="javascript:debitosCreditosSelecionados(document.forms[0],'/gsan/emissaoExtratoConsultarDebitoClienteAction.do?tipo=total');" />
						</td>
					</logic:notEmpty>
				</tr>

				<tr>
					<td height="17" colspan="4">&nbsp;</td>
				</tr>
				<tr>
					<td align="left" colspan="4"><input name="Button" type="button"
						class="bottonRightCol" value="Voltar"
						onClick="javascript:voltar();">&nbsp; <input name="adicionar"
						class="bottonRightCol" value="Cancelar" type="button"
						onClick="javascript:window.location.href='/gsan/telaPrincipal.do'">
					</td>
				</tr>
				<tr>
					<td height="17" colspan="4">&nbsp;</td>
				</tr>
			</table>
			<p>&nbsp;</p>
			</td>
		</tr>
	</table>
	<jsp:include
		page="/jsp/relatorio/escolher_tipo_relatorio.jsp?relatorio=" />
	<%@ include file="/jsp/util/rodape.jsp"%>
</html:form>
</body>
</html:html>
