<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<%@page import="gcom.util.ConstantesSistema" isELIgnored="false"%>
<%@page import="gcom.cadastro.tarifasocial.TarifaSocialCartaoTipo"%>


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
	formName="TarifaSocialCartaoActionForm" />

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>

<SCRIPT LANGUAGE="JavaScript">
<!--

function fechamento(codigo,valor) {
    opener.window.location.href='/gsan/inserirTarifaSocialWizardAction.do?action=exibirInserirTarifaSocialDadosUmaEconomiaAction&codigo='+codigo+'&valor='+valor+'&destino=2';
    self.close();
}


function validarForm(form){
	var objNumero = returnObject(form, "numero");
	var objTipoCartao = returnObject(form, "tipoCartao");
	
	if (document.getElementById("indexListBox").value.length > 0){
			
		for (index=0; index < objTipoCartao.options.length; index++){
	
			if (trim(objTipoCartao.options[index].value) == trim(document.getElementById("indexListBox").value)){
				var objIndicadorValidadeSim = objTipoCartao.options[index].name; 
				var objNumeroAdesao = objTipoCartao.options[index].id;
				break;	
			}
		}
	}
	else{
		var objIndicadorValidadeSim = ""; 
		var objNumeroAdesao = "";
	}
	
	var objDataValidade = returnObject(form, "dataValidade");
	var objNumeroParcelas = returnObject(form, "numeroParcelas");
	var objConsumoCelpe = returnObject(form, "numeroCelpe");

	/*Constantes
	------------------------------------------------------------------------ */
	// Constante para validar os campos do tipo ListBox
	var VALOR_PADRAO = objTipoCartao.options[0].value; 

	var INDICADOR_EXISTENCIA_VALIDADE_SIM = document.getElementById("INDICADOR_EXISTENCIA_VALIDADE_SIM").value;
	var NUMERO_MAXIMO_MESES_ADESAO_ZERO = document.getElementById("NUMERO_MAXIMO_MESES_ADESAO_ZERO").value;
	var DATA_ATUAL = document.getElementById("DATA_ATUAL").value;

	//----------------------------------------------------------------------

	var objValorRendaFamiliar = returnObject(form, "valorRendaFamiliar");
	var objTipoRenda = returnObject(form, "tipoRenda");

	//Verificação dos dados referentes ao cartão do programa social
	
	if (validateTarifaSocialCartaoActionForm(form)){
	
		if (objNumero.value.length > 0 || objTipoCartao.value != VALOR_PADRAO ||
			objDataValidade.value.length > 0 || objNumeroParcelas.value.length > 0){
		
			// Número é obrigatório
			if (objNumero.value.length < 1){
				objNumero.focus();
				alert("Informe Número do Cartão do Programa Social");
			}
			else if (!testarCampoValorZero(objNumero, "Número do Cartão do Programa Social ")){
				objNumero.focus();
			}
			else if (objTipoCartao.value == VALOR_PADRAO){
				objTipoCartao.focus();
				alert("Informe Tipo do Cartão do Programa Social");
			}
			else if (objDataValidade.value.length < 1 && objIndicadorValidadeSim == INDICADOR_EXISTENCIA_VALIDADE_SIM){
				objDataValidade.focus();
				alert("Informe Data de Validade");
			}
			else if (objDataValidade.value.length > 0 && objIndicadorValidadeSim != INDICADOR_EXISTENCIA_VALIDADE_SIM){
				objDataValidade.focus();
				alert("Data de Validade não deve ser informada");
			}
			else if (objDataValidade.value.length > 0 &&
					comparaData(objDataValidade.value, "<", DATA_ATUAL )){
				objDataValidade.focus();
				alert("Data de Validade deve ser superior à data corrente");
			}
			else if (objNumeroParcelas.value.length < 1 && objNumeroAdesao != "0"){
				objNumeroParcelas.focus();
				alert("Número Parcelas deve ser informado e ser menor ou igual a " + objNumeroAdesao);
			}
			else if (objNumeroParcelas.value.length > 0 && objNumeroAdesao == "0"){
				objNumeroParcelas.focus();
				alert("Número Parcelas não deve ser informado");
			}
			else if (!testarCampoValorZero(objNumeroParcelas, "Número Parcelas ")){
				objNumeroParcelas.focus();
			}
			else if (objNumeroParcelas.value.length > 0 && objNumeroAdesao != "0"
					&& (objNumeroParcelas.value * 1) > (objNumeroAdesao * 1)){
				objNumeroParcelas.focus();
				alert("Número Parcelas deve ser menor ou igual a " + objNumeroAdesao);
			}
			else if (!testarCampoValorZero(objConsumoCelpe, "Consumo Médio Companhia de Energia ")){
				objConsumoCelpe.focus();
			}
			else if (objValorRendaFamiliar.value.length > 0 || objTipoRenda.value != VALOR_PADRAO){
				//Verificação dos dados referentes a renda familiar		
				if (objValorRendaFamiliar.value.length < 1){
					objValorRendaFamiliar.focus();
					alert("Informe Valor da Renda Familiar");
				}	
				else if (!testarCampoValorZero(objValorRendaFamiliar, "Valor da Renda Familiar ")){
					objValorRendaFamiliar.focus();
				}
				else if (objTipoRenda.value == VALOR_PADRAO){
					objTipoRenda.focus();
					alert("Informe Tipo da Renda Familiar");
				}
				else {
				
					if (form.numeroContratoCelpe.value.length < 1 && form.numeroCelpe.value.length > 0) {
						alert('Informe Número do Contrato da Companhia de Eletricidade');
					}
		
					else if (form.numeroCelpe.value.length < 1 && form.numeroContratoCelpe.value.length > 0) {
						alert('Informe Consumo Médio da Companhia de Eletricidade');
					} 
				
					else {
						converteVirgula(objValorRendaFamiliar);
						submeterFormPadrao(form);
					}
				}
			}
			else {
			
				if (form.numeroContratoCelpe.value.length < 1 && form.numeroCelpe.value.length > 0) {
					alert('Informe Número do Contrato da Companhia de Eletricidade');
				}
		
				else if (form.numeroCelpe.value.length < 1 && form.numeroContratoCelpe.value.length > 0) {
					alert('Informe Consumo Médio da Companhia de Eletricidade');
				} 
				
				else {
					converteVirgula(objValorRendaFamiliar);
					submeterFormPadrao(form);
				}
			}
		}
		else{
			//Verificação dos dados referentes a renda familiar
			if (!testarCampoValorZero(objConsumoCelpe, "Consumo Médio Companhia de Energia ")){
				objConsumoCelpe.focus();
			}
			else if (objValorRendaFamiliar.value.length < 1){
				objValorRendaFamiliar.focus();
				alert("Renda Familiar deve ser informada quando os dados do cartão do programa social não forem informados");
			}
			else if (!testarCampoValorZero(objValorRendaFamiliar, "Valor da renda ")){
				objValorRendaFamiliar.focus();
			}
			else if (objTipoRenda.value == VALOR_PADRAO){
				objTipoRenda.focus();
				alert("Informe Tipo da Renda Familiar");
			}
			else {
			
				if (form.numeroContratoCelpe.value.length < 1 && form.numeroCelpe.value.length > 0) {
					alert('Informe Número do Contrato da Companhia de Eletricidade');
				}
		
				else if (form.numeroCelpe.value.length < 1 && form.numeroContratoCelpe.value.length > 0) {
					alert('Informe Consumo Médio da Companhia de Eletricidade');
				} 
				
				else {
					converteVirgula(objValorRendaFamiliar);
					submeterFormPadrao(form);
				}
			}
		}
	}
}



function armazenarIndex(listBox){
	document.getElementById("indexListBox").value = listBox.value;
}


function selectTipoCartao(listBox, valorSelect){
	if (valorSelect.length > 0 && valorSelect > 0){
		for (x=0; x < listBox.options.length; x++){
			if (listBox.options[x].value == valorSelect){
				listBox.options[x].selected = true;
				break;
			}
		}
	}
}

function desabilitaCampoAreaConstruida(){
    var form = document.forms[0];
	if (trim(form.areaConstruida.value) != ''){
		form.areaConstruidaFaixa.value = '-1';
		form.areaConstruidaFaixa.disabled = true;
	}else{
		form.areaConstruidaFaixa.disabled = false;
		if (form.areaConstruidaFaixa.value != '-1') {
			form.areaConstruida.value = "";
			form.areaConstruida.readOnly = true;
		} else {
			form.areaConstruida.readOnly = false;
		}

	}
    
}

//-->
</SCRIPT>

</head>

<logic:present name="operacaoConcluida">
	<body leftmargin="0" topmargin="0"
		onLoad="fechamento('${requestScope.codigo}','${requestScope.valor}'); setarFoco('${requestScope.nomeCampo}');">
</logic:present>

<logic:notPresent name="operacaoConcluida">
	<body leftmargin="0" topmargin="0"
		onload="selectTipoCartao(document.forms[0].tipoCartao, document.getElementById('indexListBox').value); setarFoco('${requestScope.nomeCampo}');convertePonto(document.forms[0].valorRendaFamiliar);desabilitaCampoAreaConstruida()">



	<html:form action="/inserirTarifaSocialDadosCartaoUmaEconomia"
		method="post">

		<!-- Objetos colocados para armazenar os valores de: cartão que possuí validade e o número máximo de meses para adesão com zero -->
		<!-- Constantes -->
		<INPUT TYPE="hidden" ID="DATA_ATUAL" value="${requestScope.dataAtual}" />
		<INPUT TYPE="hidden" ID="INDICADOR_EXISTENCIA_VALIDADE_SIM"
			value="<%= "" + TarifaSocialCartaoTipo.INDICADOR_EXISTENCIA_VALIDADE_SIM%>">
		<INPUT TYPE="hidden" ID="NUMERO_MAXIMO_MESES_ADESAO_ZERO"
			value="<%= "" + TarifaSocialCartaoTipo.NUMERO_MAXIMO_MESES_ADESAO_ZERO%>">

		<!-- Objeto que armazenará o valor o indice do listBox -->
		<INPUT TYPE="hidden" ID="indexListBox"
			value="${requestScope.tarifaSocialDadoEconomia.tarifaSocialCartaoTipo.id}">
		<input type="hidden" name="codigo"
			value="${requestScope.tarifaSocialDadoEconomia.id}" />


		<table width="452" border="0" cellpadding="0" cellspacing="5">
			<tr>
				<td width="442" valign="top" class="centercoltext">
				<table height="100%">
					<tr>
						<td></td>
					</tr>
				</table>
				<table width="100%" border="0" align="center" cellpadding="0"
					cellspacing="0">
					<tr>
						<td width="11"><img
							src="<bean:message key="caminho.imagens"/>parahead_left.gif"
							border="0" /></td>
						<td class="parabg">Inserir Dados da Tarifa Social</td>
						<td width="11"><img
							src="<bean:message key="caminho.imagens"/>parahead_right.gif"
							border="0" /></td>
					</tr>
				</table>
				<table width="100%" border="0">
					<tr>
						<td height="234">
						<table width="100%" border="0" dwcopytype="CopyTableRow">
							<tr>
								<td colspan="3">Para inserir os dados para tarifa social,
								forne&ccedil;a os dados abaixo:</td>
							</tr>
							<tr>
								<td height="17" colspan="3"><font color="#000000"> <strong>Cart&atilde;o
								do Programa Social</strong> </font></td>
							</tr>
							<tr>
								<td width="28%" height="24"><strong>N&uacute;mero:</strong></td>
								<td colspan="2"><html:text property="numero" maxlength="11"
									size="11"
									value="${requestScope.tarifaSocialDadoEconomia.numeroCartaoProgramaSocial}" /></td>
							</tr>

							<tr>
								<td height="25"><strong>Tipo:</strong></td>
								<td colspan="2"><html:select property="tipoCartao"
									value="${requestScope.tarifaSocialDadoEconomia.tarifaSocialCartaoTipo.id}"
									onchange="armazenarIndex(this);" style="width:200px;">
									<html:option
										value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
									<logic:iterate id="objTipoCartao"
										name="colecaoTarifaSocialCartaoTipo"
										type="TarifaSocialCartaoTipo">

										<logic:present name="objTipoCartao"
											property="numeroMesesAdesao">


											<OPTION VALUE="<%=objTipoCartao.getId()%>"
												name="<%=objTipoCartao.getIndicadorValidade()%>"
												id="<%=objTipoCartao.getNumeroMesesAdesao()%>"><%=objTipoCartao.getDescricao()%></OPTION>

										</logic:present>

										<logic:notPresent name="objTipoCartao"
											property="numeroMesesAdesao">


											<OPTION VALUE="<%=objTipoCartao.getId()%>"
												name="<%=objTipoCartao.getIndicadorValidade()%>" id="0"><%=objTipoCartao.getDescricao()%></OPTION>

										</logic:notPresent>

									</logic:iterate>

								</html:select></td>
							</tr>
							<tr>
								<td height="17"><strong>Data de Validade:</strong></td>
								<td colspan="2"><html:text property="dataValidade" size="11"
									maxlength="10"
									value="${requestScope.tarifaSocialDadoEconomia.dataValidadeCartaoFormatada}"
									onkeyup="mascaraData(this, event);" /> <a
									href="javascript:abrirCalendario('TarifaSocialCartaoActionForm', 'dataValidade')"><img
									border="0"
									src="<bean:message key='caminho.imagens'/>calendario.gif"
									width="20" border="0" align="middle" alt="Exibir Calendário" /></a>
								&nbsp;&nbsp;<span style="font-size: 10px">(dd/mm/aaaa)</span></td>
							</tr>
							<tr>
								<td height="25"><strong>N&uacute;mero Parcelas:</strong></td>
								<td colspan="2"><html:text property="numeroParcelas" size="2"
									maxlength="2"
									value="${requestScope.tarifaSocialDadoEconomia.numeroMesesAdesao}" /></td>
							</tr>
							<tr>
								<td colspan="3">
								<hr>
								</td>
							</tr>
							<tr>
								<td height="34"><strong>Número Moradores:</strong></td>
								<td colspan="2"><html:text property="numeroMoradores" size="2"
									maxlength="2" />
								</td>
							</tr>
							<tr>
								<td colspan="3">
								<hr>
								</td>
							</tr>
							<tr>
								<td height="20" colspan="3"><font color="#000000"><strong>Dados
								da Companhia de Eletricidade:</strong></font></td>
							</tr>
							<tr>
								<td height="28"><strong>N&uacute;mero do Contrato:</strong></td>
								<td colspan="2"><html:text property="numeroContratoCelpe"
									maxlength="10" size="15" /></td>
							</tr>
							<tr>
								<td height="24"><strong>Consumo M&eacute;dio:</strong></td>
								<td colspan="2"><html:text property="numeroCelpe" size="5"
									maxlength="5" value="${requestScope.tarifaSocialDadoEconomia.consumoCelpe}" /> <strong>kWh</strong>
								</td>
							</tr>
							<tr>
								<td height="24" colspan="3">
								<hr>
								</td>
							</tr>
							<tr>
								<td height="24"><strong>N&uacute;mero do IPTU:</strong></td>
								<td colspan="2"><html:text property="numeroIPTU" maxlength="19"
									size="20" /></td>
							</tr>
							<tr>
								<td height="29"><strong>&Aacute;rea Constru&iacute;da:</strong></td>
								<td colspan="2"><html:text style="text-align: right;"
									onkeyup="javaScript:formataValorMonetario(this, 8);desabilitaCampoAreaConstruida();"
									property="areaConstruida" maxlength="9" size="9" />&nbsp;m<sup>2</sup>
								<html:select
									onchange="javascript:desabilitaCampoAreaConstruida();"
									property="areaConstruidaFaixa">
									<html:option value="-1">&nbsp;</html:option>
									<html:options collection="colecaoAreaConstruidaFaixa"
										labelProperty="faixaCompleta" property="id" />
								</html:select></td>
						</table>
						<hr>
						</td>
					</tr>
					<tr>
						<td>
						<table width="100%" border="0" dwcopytype="CopyTableRow">
							<tr>
								<td width="24%"><strong>Renda Familiar</strong></td>
								<td width="74%">&nbsp;</td>
								<td width="2%">&nbsp;</td>
							</tr>
							<tr>
								<td height="25"><strong>Valor:</strong></td>
								<td><html:text property="valorRendaFamiliar" size="8"
									style="text-align: right;" maxlength="8"
									value="${requestScope.tarifaSocialDadoEconomia.valorRendaFamiliar}"
									onkeyup="javaScript:formataValorMonetario(this, 8);" /></td>
								<td>&nbsp;</td>
							</tr>
							<tr>
								<td height="25"><strong>Tipo:</strong></td>

								<td><html:select property="tipoRenda"
									value="${requestScope.tarifaSocialDadoEconomia.rendaTipo.id}"
									style="width:115px;">
									<html:option
										value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
									<html:options collection="colecaoRendaTipo"
										labelProperty="descricao" property="id" />
								</html:select></td>
								<td>&nbsp;</td>
							</tr>
							<logic:present name="colecaoTarifaSocialExclusaoMotivo"
								scope="request">
								<tr>
									<td colspan="2">
									<hr>
									</td>
								</tr>
								<tr>
									<td colspan="2"><strong>Cliente é usuário da Tarifa Social</strong></td>
								</tr>
								<tr>
									<td height="25"><strong>Matrícula do Imóvel:</strong></td>
									<td><html:text property="idImovel" size="10" readonly="true"
										style="background-color:#EFEFEF; border:0; color: #000000" /></td>
								</tr>
								<tr>
									<td height="25"><strong>Motivo Revisão:</strong></td>
									<td><html:text property="motivoRevisao" size="40"
										readonly="true"
										style="background-color:#EFEFEF; border:0; color: #000000" /></td>
								</tr>
								<tr>
									<td height="25"><strong>Motivo de Exclusão no Imóvel Anterior:</strong></td>
									<td><html:select property="motivoExclusao">
										<html:option
											value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
										<html:options collection="colecaoTarifaSocialExclusaoMotivo"
											labelProperty="descricao" property="id" />
									</html:select></td>
							</logic:present>
						</table>
						</td>
					</tr>
					<tr>
						<td height="24">
						<div align="right"><INPUT TYPE="button" Class="bottonRightCol"
							value="Inserir" onclick="validarForm(document.forms[0]);"
							style="width:70px;" /> <INPUT TYPE="button"
							Class="bottonRightCol" value="Fechar"
							onclick="javascript:window.close();" style="width:70px;" /></div>
						</td>
					</tr>
				</table>
				<p>&nbsp;</p>
				</td>
			</tr>
		</table>
	</html:form>

	</body>
</logic:notPresent>


</html:html>
