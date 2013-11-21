<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>


<%@page import="gcom.cadastro.tarifasocial.TarifaSocialCartaoTipo" isELIgnored="false"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//NEN">
<html:html>

<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js"></script>

<html:javascript staticJavascript="false"
	formName="AtualizarDadosTarifaSocialActionForm"
	dynamicJavascript="true" />

<script language="JavaScript">
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

	function desabilitarBotaoConcluir(voltar) {
		if (voltar == null || voltar == '') {
			document.forms[0].Concluir.disabled = true;
		}
	}

	function habilitarBotaoConcluir() {
		document.forms[0].Concluir.disabled = false;   
	}


	function habilitarBotaoAvancar() {
		document.forms[0].Avancar.disabled = false;   
	}

	function avancar() {
	
		var form = document.forms[0];
		
		if (validateAtualizarDadosTarifaSocialActionForm(form)) {
			
			var arrayDados = form.tipoCartao.options[form.tipoCartao.selectedIndex].id.split(",");
			
			if (form.tipoCartao.value != "-1") {
			
				indicadorValidade = trim(arrayDados[1]);
				numeroMesesAdesao = trim(arrayDados[0]);
			
			}
			
			if (form.numeroCartaoProgramaSocial.value.length < 1 && form.tipoCartao.value == "-1" && form.valorRendaFamiliar.value.length < 1) {
				alert('Renda Familiar deve ser informada quando os dados do cartão do programa social não forem informados');
			} else if (form.numeroCartaoProgramaSocial.value.length > 0 && form.tipoCartao.value == "-1") {
				alert('Informe Tipo do Cartão do Programa Social');
			} else if (form.numeroCartaoProgramaSocial.value.length < 1 && form.tipoCartao.value != "-1") {
				alert('Informe Número do Cartão do Programa Social');
			} else if (form.tipoCartao.value != "-1" && form.dataValidadeCartao.value.length < 1 && indicadorValidade == "1") { 
				alert('Informe Data de Validade');
			} else if (form.tipoCartao.value != "-1" && form.dataValidadeCartao.value.length > 0 && indicadorValidade == "2") { 
				alert('Data de Validade não deve ser informada');
			} else if (form.tipoCartao.value != "-1" && (form.numeroMesesAdesao.value.length < 1 && numeroMesesAdesao != "null")  || 
				(form.numeroMesesAdesao.value.length > 0 && (numeroMesesAdesao != "null" && (form.numeroMesesAdesao.value * 1) > (numeroMesesAdesao * 1)))) {
				
				alert('Número Parcelas deve ser informado e ser menor ou igual a ' + numeroMesesAdesao);
			}  else if (form.tipoCartao.value != "-1" && form.numeroMesesAdesao.value.length > 0 && numeroMesesAdesao == "null") {
				alert('Número Parcelas não deve ser informado');
			} else if (form.numeroContratoCelpe.value.length < 1 && form.consumoCelpe.value.length > 0) {
				alert('Informe Número do Contrato da Companhia de Eletricidade');
			} else if (form.consumoCelpe.value.length < 1 && form.numeroContratoCelpe.value.length > 0) {
				alert('Informe Consumo Médio da Companhia de Eletricidade');
			} else {
				form.action = 'atualizarDadosTarifaSocialAction.do';
				submeterFormPadrao(form);
			}
		}
	}

	function concluir() {
		var form = document.forms[0];
	
		if (validateAtualizarDadosTarifaSocialActionForm(form)) {
		
			var arrayDados = form.tipoCartao.options[form.tipoCartao.selectedIndex].id.split(",");
			
			if (form.tipoCartao.value != "-1") {
			
				indicadorValidade = trim(arrayDados[1]);
				numeroMesesAdesao = trim(arrayDados[0]);
			}
			
			if (form.numeroCartaoProgramaSocial.value.length < 1 && form.tipoCartao.value == "-1" && form.valorRendaFamiliar.value.length < 1) {
				alert('Renda Familiar deve ser informada quando os dados do cartão do programa social não forem informados');
			} else if (form.numeroCartaoProgramaSocial.value.length > 0 && form.tipoCartao.value == "-1") {
				alert('Informe Tipo do Cartão do Programa Social');
			} else if (form.numeroCartaoProgramaSocial.value.length < 1 && form.tipoCartao.value != "-1") {
				alert('Informe Número do Cartão do Programa Social');
			} else if (form.tipoCartao.value != "-1" && form.dataValidadeCartao.value.length < 1 &&
					   indicadorValidade == "1") { 
				alert('Informe Data de Validade');
			} else if (form.tipoCartao.value != "-1" && form.dataValidadeCartao.value.length > 0 &&
					   indicadorValidade == "2") { 
				alert('Data de Validade não deve ser informada');
			} else if (form.tipoCartao.value != "-1" && (form.numeroMesesAdesao.value.length < 1 && numeroMesesAdesao != "null") 
			|| (form.numeroMesesAdesao.value.length > 0 && (numeroMesesAdesao != "null" && (form.numeroMesesAdesao.value * 1) > (numeroMesesAdesao * 1)))) {
				alert('Número Parcelas deve ser informado e ser menor ou igual a ' + numeroMesesAdesao);
			} else if (form.tipoCartao.value != "-1" && form.numeroMesesAdesao.value.length > 0 && numeroMesesAdesao == "null") {
				alert('Número Parcelas não deve ser informado');
			} else if (form.numeroContratoCelpe.value.length < 1 && form.consumoCelpe.value.length > 0) {
				alert('Informe Número do Contrato da Companhia de Eletricidade');
			} else if (form.consumoCelpe.value.length < 1 && form.numeroContratoCelpe.value.length > 0) {
				alert('Informe Consumo Médio da Companhia de Eletricidade');
			} else {
				form.action = 'atualizarDadosTarifaSocialAction.do?concluir=sim';
				submeterFormPadrao(form);
			}
		}
		
	}

	function fechar() {
		chamarReloadSemParametro();
		window.close();
	}

</script>

</head>

<logic:present name="fechar" scope="request">
	<body leftmargin="0" topmargin="0" onload="javascript:fechar();">
</logic:present>

<logic:notPresent name="fechar" scope="request">
	<body leftmargin="0" topmargin="0"
		onload="javascript:resizePageSemLink(700, 650);desabilitaCampoAreaConstruida();desabilitarBotaoConcluir('${requestScope.voltar}');">
</logic:notPresent>

<html:form action="/exibirAtualizarDadosTarifaSocialAction"
	method="post"
	onsubmit="return validateAtualizarDadosTarifaSocialActionForm(this);"
	focus="numeroCartaoProgramaSocial">

	<html:hidden property="id" name="AtualizarDadosTarifaSocialActionForm" />
	<html:hidden property="acao" />
	<input type="hidden" name="idImovelEconomia" />

	<!-- Objetos colocados para armazenar os valores de: cartão que possuí validade e o número máximo de meses para adesão com zero -->
	<!-- Constantes -->
	<INPUT TYPE="hidden" ID="INDICADOR_EXISTENCIA_VALIDADE_SIM" value="<%= "" + TarifaSocialCartaoTipo.INDICADOR_EXISTENCIA_VALIDADE_SIM%>">
	<INPUT TYPE="hidden" ID="NUMERO_MAXIMO_MESES_ADESAO_ZERO" value="<%= "" + TarifaSocialCartaoTipo.NUMERO_MAXIMO_MESES_ADESAO_ZERO%>">

	<!-- Objeto que armazenará o valor o indice do listBox -->
	<INPUT TYPE="hidden" ID="indexListBox" value="${requestScope.tarifaSocialDadoEconomia.tarifaSocialCartaoTipo.id}">

	<table width="100%" border="0" cellpadding="0" cellspacing="5">
		<tr>
			<td width="100%" valign="top" class="centercoltext">
			
			<table height="100%">
				<tr>
					<td></td>
				</tr>
			</table>
			
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11">
						<img border="0" src="<bean:message key="caminho.imagens"/>parahead_left.gif" />
					</td>
					<td class="parabg">Atualizar Dados da Tarifa Social</td>
					<td width="11">
						<img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif" />
					</td>
				</tr>
			</table>
			
			<table width="100%" border="0">
				<tr>
					<td width="100%">
					<table width="100%" border="0">
						
						<tr>
							<td><strong>Nome do Cliente Usuário:</strong></td>
							<td>
							<span class="style1"> 
								<html:text style="background-color:#EFEFEF; border:0;" size="15"
									disabled="true" 
									property="clienteNome" 
									size="45" 
									maxlength="45" />
							</span>
							</td>
						</tr>
						
						<tr>
							<td><strong>Complemento do Endereço:</strong></td>
							<td>
								<span class="style1"> 
								<html:text style="background-color:#EFEFEF; border:0;" size="15"
									disabled="true" 
									property="complementoEndereco" 
									size="25"
									maxlength="25" /> 
								</span>
							</td>
						</tr>
						
						<tr>
							<td colspan="3">
							<hr>
							</td>
						</tr>
						
						<tr>
							<td colspan="3">Para atualizar os dados da tarifa social, informe
							os dados abaixo:</td>
						</tr>
						
						<tr>
							<td colspan="3">
								<font color="#000000"> 
								<strong>Cart&atilde;o do Programa Social:</strong>
								</font>
							</td>
						</tr>
						
						<tr>
							<td>
								<div align="left">
									<strong> N&uacute;mero<font color="#FF0000">*<font color="#000000">:</font></font></strong></div>
							</td>
							
							<td>
								<html:text onkeypress="javascript:habilitarBotaoConcluir();habilitarBotaoAvancar();"
									property="numeroCartaoProgramaSocial" 
									maxlength="11" 
									size="11" />
							</td>
						</tr>
						
						<tr>
							<td>
								<div align="left"><strong>Tipo<font color="#FF0000">*<font
								color="#000000">:</font></font></strong></div>
							</td>
							
							<td>
								<font color="#FF0000"> 
								<html:select
									onchange="javascript:habilitarBotaoConcluir();habilitarBotaoAvancar();"
									property="tipoCartao">
									
									<option value="-1">&nbsp;</option>

									<logic:iterate id="cartaoTipo"
										name="colecaoTarifaSocialCartaoTipo"
										type="TarifaSocialCartaoTipo">

										<logic:equal name="AtualizarDadosTarifaSocialActionForm"
											property="tipoCartao"
											value="<%= cartaoTipo.getId().toString() %>">
											<option value="<%= cartaoTipo.getId().toString() %>" selected
												id="<%=""+ cartaoTipo.getNumeroMesesAdesao() %>, <%=""+ cartaoTipo.getIndicadorValidade() %>"><%="" + cartaoTipo.getDescricao()%></option>
										</logic:equal>
	
										<logic:notEqual name="AtualizarDadosTarifaSocialActionForm"
											property="tipoCartao"
											value="<%= cartaoTipo.getId().toString() %>">
											<option value="<%= cartaoTipo.getId().toString() %>"
												id="<%=""+ cartaoTipo.getNumeroMesesAdesao() %>, <%=""+ cartaoTipo.getIndicadorValidade() %>"><%="" + cartaoTipo.getDescricao()%></option>
										</logic:notEqual>

									</logic:iterate>
								</html:select> 
								</font>
							</td>
							
							<td><font color="#FF0000">&nbsp; </font></td>
						</tr>
						
						<tr>
							<td>
								<div align="left">
								<strong>Data de Validade:<font color="#FF0000">*</font></strong>
								</div>
							</td>
							
							<td>
								<html:text onkeypress="javascript:habilitarBotaoConcluir();habilitarBotaoAvancar();"
									property="dataValidadeCartao" 
									maxlength="10" 
									size="10"
									onkeyup="mascaraData(this, event);" /> 
								
								<a href="javascript:abrirCalendario('AtualizarDadosTarifaSocialActionForm', 'dataValidadeCartao')">
									<img border="0" 
										src="<bean:message key='caminho.imagens'/>calendario.gif" 
										width="19" 
										height="19" 
										border="0" 
										alt="Exibir Calendário" 
										tabindex="4"/></a>										
							</td>
							
							<td>&nbsp;</td>
						</tr>
						
						<tr>
							<td>
								<div align="left">
									<strong>N&uacute;mero Parcelas:<font
									color="#FF0000">*</font></strong>
								</div>
							</td>
							
							<td>
								<html:text onkeypress="javascript:habilitarBotaoConcluir();habilitarBotaoAvancar();"
									property="numeroMesesAdesao" 
									maxlength="2" 
									size="3" /></td>
							<td >&nbsp;</td>
						</tr>
						
						<tr>
							<td colspan="3">
							<hr>
							</td>
						</tr>
						
						<tr>
							<td><strong>Número Moradores:</strong></td>
							<td>
								<html:text property="numeroMoradores" 
									size="2"
									maxlength="2" 
									onkeypress="javascript:habilitarBotaoConcluir();habilitarBotaoAvancar();" />
							</td>
						</tr>
						
						<tr>
							<td colspan="3">
							<hr>
							</td>
						</tr>
						
						<tr>
							<td><font color="#000000"><strong>Dados
							da Companhia de Eletricidade:</strong></font></td>
						</tr>
						
						<tr>
							<td><strong>N&uacute;mero do Contrato:</strong></td>
							<td><html:text
								onkeypress="javascript:habilitarBotaoConcluir();habilitarBotaoAvancar();"
								property="numeroContratoCelpe" 
								maxlength="10" 
								size="15" /></td>
							<td>&nbsp;</td>
						</tr>
						
						<tr>
							<td><strong>Consumo M&eacute;dio:</strong></td>
							<td><html:text
								onkeypress="javascript:habilitarBotaoConcluir();habilitarBotaoAvancar();"
								property="consumoCelpe" maxlength="5" size="10" /> <strong>kWh
							</strong></td>
							<td>&nbsp;</td>
						</tr>
						
						<tr>
							<td colspan="3">
							<hr>
							</td>
						</tr>
						<tr>
							<td><strong>N&uacute;mero do IPTU:</strong></td>
							<td>
								<html:text
								onkeypress="javascript:habilitarBotaoConcluir();habilitarBotaoAvancar();"
								property="numeroIPTU" 
								maxlength="19" 
								size="20" /></td>
							<td>&nbsp;</td>
						</tr>
						<tr>
							<td><strong>&Aacute;rea Constru&iacute;da:</strong></td>
							<td>
								<html:text property="areaConstruida" 
									onkeypress="javascript:habilitarBotaoConcluir();habilitarBotaoAvancar();"
									style="text-align: right;"
									onkeyup="javaScript:formataValorMonetario(this, 8);desabilitaCampoAreaConstruida();"
									maxlength="9" 
									size="9" />&nbsp;m<sup>2</sup>
								
								<html:select property="areaConstruidaFaixa"
									onchange="javascript:habilitarBotaoConcluir();desabilitaCampoAreaConstruida();">
								
								<html:option value="-1">&nbsp;</html:option>
								<html:options collection="colecaoAreaConstruidaFaixa"
									labelProperty="faixaCompleta" 
									property="id" />
							</html:select></td>
							<td>&nbsp;</td>
						</tr>
						
						<tr>
							<td colspan="3">
							<hr>
							</td>
						</tr>
						
						<tr>
							<td>
								<strong>Renda Familiar<font color="#000000">:</font><font
								color="#FF0000">*</font></strong>
							</td>
							
							<td>(Caso n&atilde;o possua informa&ccedil;&otilde;es
							sobre Cart&atilde;o de Programa Social)</td>
						</tr>
						
						<tr>
							<td><strong>Valor:</strong></td>
							<td>
								<html:text property="valorRendaFamiliar"
									onkeypress="javascript:habilitarBotaoConcluir();habilitarBotaoAvancar();"
									onkeyup="javaScript:formataValorMonetario(this, 8);"
									style="text-align: right;" 
									maxlength="8" 
									size="8" />
							</td>
							<td>&nbsp;</td>
						</tr>
						
						<tr>
							<td><strong>Tipo:</strong></td>
							<td>
								<html:select property="rendaTipo"
									onchange="javascript:habilitarBotaoConcluir();habilitarBotaoAvancar();">
									<html:option value="-1">&nbsp;</html:option>
									<html:options collection="colecaoRendaTipo"
										labelProperty="descricao" 
										property="id" />
								</html:select>
							</td>
							<td>&nbsp;</td>
						</tr>
						
						<tr>
							<td colspan="3">
							<hr>
							</td>
						</tr>
						
						<tr>
							<td><strong>Motivo de Revisão:</strong></td>
							<td>
								<html:select onchange="javascript:habilitarBotaoConcluir();habilitarBotaoAvancar();"
									property="motivoRevisao">
								
									<html:option value="-1">&nbsp;</html:option>
									<html:options collection="colecaoMotivoRevisao"
										labelProperty="descricao" 
										property="id" />
								</html:select>
							</td>
							<td>&nbsp;</td>
						</tr>
						
						
						<logic:present name="exibirDataComparecimentoCarta" scope="session">
						<tr>
							<td>
								<div align="left">
								<strong>Data de Comparecimento da Carta:</strong>
								</div>
							</td>
							
							<td>
								<html:text property="dataComparecimentoCarta" 
									onkeypress="javascript:habilitarBotaoConcluir();habilitarBotaoAvancar();"
									maxlength="10" 
									size="10"
									onkeyup="mascaraData(this, event);" />
									
								<a href="javascript:abrirCalendario('AtualizarDadosTarifaSocialActionForm', 'dataComparecimentoCarta')">
									<img border="0" 
										src="<bean:message key='caminho.imagens'/>calendario.gif" 
										width="19" 
										height="19" 
										border="0" 
										alt="Exibir Calendário" 
										tabindex="4"/></a>									
							</td>
							
							<td>&nbsp;</td>
						</tr>
						</logic:present>
						
						<logic:notPresent name="exibirDataComparecimentoCarta" scope="session">
							<html:hidden property="dataComparecimentoCarta" />
						</logic:notPresent>
						
					</table>
					</td>
				</tr>
			</table>
			
			<p>&nbsp;</p>
			
			<table width="100%" border="0">
				<tr>
					<td>&nbsp;</td>
					<td>
					<div align="right">
						
						<input name="Avancar" 
							type="button"
							class="bottonRightCol" 
							value="Avançar"
							onClick="javascript:avancar();" /> 
						
						<input name="Concluir"
							type="button" 
							class="bottonRightCol" 
							value="Concluir"
							onClick="javascript:concluir();" /> 
						
						<input name="F" 
							type="button"
							class="bottonRightCol" 
							value="Fechar"
							onClick="javascript:window.close();" />
							
					</div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
</html:form>
</body>
</html:html>

