<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<%@ page
	import="gcom.cobranca.parcelamento.ParcelamentoPagamentoCartaoCredito"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>

<html:javascript staticJavascript="false"  formName="ParcelamentoCartaoConfirmarForm"/>

<script language="JavaScript">

function concluirTransacao(){
	var form = document.forms[0];

	form.action='atualizarDadosCartaoCreditoDebitoAction.do?concluir=ok';
	form.submit();
}

function validarRadio(){

 	var form = document.forms[0];

		if (RadioNaoVazioMensagem("Parcelamento", "")){
			verficarSelecao();
		}
}


function verficarSelecao(){
	var form = document.forms[0];

	for(indice = 0; indice < form.elements.length; indice++){
		if (form.elements[indice].type == "radio" && form.elements[indice].checked == true) {
			form.action='consultarDadosParcelamentoCartaoCreditoAction.do?objetoConsulta=selecionar&numeroParcelamento='+form.elements[indice].value;
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
function validarForm() {
  var form = document.forms[0];
  if(validateParcelamentoCartaoConfirmarForm(form)){	     
	 submeterFormPadrao(form); 
  }  		  
}

function pesquisarClientePopup(){
	var form = document.forms[0];

	form.action='consultarDadosParcelamentoCartaoCreditoAction.do?objetoConsulta=pesquisarClientePopup';
	form.submit();
}

function limparCliente(){
	var form = document.forms[0];
	
	form.idCliente.value="";
	form.nomeCliente.value="";
}

</script>
</head>

<logic:notPresent name="fechaPopup" scope="request">
	<body onload="selecionarParcelamento('${sessionScope.ParcelamemtoSelecionado}');">
</logic:notPresent>

<logic:present name="fechaPopup" scope="request">
	<body onload="window.close();">
</logic:present>

<html:form action="/atualizarDadosCartaoCreditoDebitoAction.do?atualizar=ok"
	name="ParcelamentoCartaoConfirmarForm" scope="request"
	type="gcom.gui.cobranca.parcelamento.ParcelamentoCartaoConfirmarForm"
	method="post">
	<table width="760" border="0" cellspacing="5" cellpadding="0">
		<tr>
			<td width="760" valign="top" class="centercoltext">
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
					<td class="parabg">Consultar Dados do Parcelamento com Cartão de
					Crédito</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
				

			</table>
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td colspan="3" height="23"><strong>Relação de pagamento(s) com cartão de crédito ativo(s):</strong></td>
				</tr>
				<tr>
					<td>
					<table width="100%" bgcolor="#90c7fc">
						<tr bgcolor="#90c7fc">
							<td width="10%" bgcolor="#90c7fc">
							<div align="center"><strong>&nbsp;</strong></div>
							</td>
							<td width="70%" bgcolor="#90c7fc">
							<div align="center"><strong>Cliente</strong></div>
							</td>
							<td width="20%" bgcolor="#90c7fc">
							<div align="center"><strong>Valor</strong></div>
							</td>
						</tr>

						<%String cor = "##cbe5fe";%>
						<logic:notEmpty name="colecaoParcelamentos" scope="session">
							<logic:present name="colecaoParcelamentos" scope="session">
								<logic:iterate name="colecaoParcelamentos" id="parcelamento"
									type="ParcelamentoPagamentoCartaoCredito">
									<%if (cor.equalsIgnoreCase("#FFFFFF")) {
				cor = "#cbe5fe";%>
									<tr bgcolor="#cbe5fe">
										<%} else {
				cor = "#FFFFFF";%>
									<tr bgcolor="#FFFFFF">
										<%}%>
										<td width="10%">
										<div align="center"><input type="radio" name="idParcelamento"
											value="<bean:write name="parcelamento" property="id"/>" /></div>
										</td>
										<td width="70%" align="center"><bean:write name="parcelamento"
											property="cliente.nome" /></td>
										<td width="20"><bean:write name="parcelamento"
											property="valorParcelado" /></td>
									</tr>
								</logic:iterate>
							</logic:present>
						</logic:notEmpty>
					</table>
					</td>
				</tr>

			</table>
			<table width="100%">

				<tr>
					<logic:notPresent name="dados" scope="request">
						
						<td>
							<input name="ButtonFechar" type="button"
								class="bottonRightCol" value="Fechar"
								onclick="javascript:window.close();">
						</td>

					</logic:notPresent>
					
					<td align="right">
						
						<input type="button" name="selecionar"
						class="bottonRightCol" value="Selecionar"
						onclick="javascript:validarRadio();">
						
						<input type="button" name="concluir"
						class="bottonRightCol" value="Concluir"
						onclick="javascript:concluirTransacao();">
					</td>
				</tr>

			</table>
			
			<logic:present name="atualizado" scope="request">
				<table width="100%" border="0">
					<tr valign="middle">
						<td colspan="4" align="center">
							<img src="<bean:message key="caminho.imagens"/>sucesso2.gif" /><b> Dados Atualizados com Sucesso.</b>
						</td>
					</tr>
				</table>
			</logic:present>
			
			
			<logic:present name="dados" scope="request">
				
				<table width="100%" border="0">
					<tr>
						<td colspan="4">Dados do cartão de crédito confirmado:</td>
					</tr>
				</table>
				<table width="100%">
						<tr>
							<td width="30%"><strong>Cartão de Crédito:</strong></td>
							<td><html:text maxlength="25" readonly="true"
								style="background-color:#EFEFEF; border:0"
								property="cartaoCredito" size="30" tabindex="1" /></td>
						</tr>		
						
							
					<logic:present name="temPermissaoAlterarDadosCartao" scope="request">
						<tr>
							<td width="40%"><strong>Número do Documento:</strong></td>
							<td><html:text maxlength="11"
								property="documentoCartao" size="30" tabindex="1" /></td>
						</tr>
						<tr>
							<td width="30%"><strong>Número do Autorização:</strong></td>
							<td><html:text maxlength="11"
								property="autorizacaoCartao" size="30" tabindex="1" /></td>
						</tr>
						<tr>
							<td width="30%"><strong>Número do Cartão de Crédito:</strong></td>
							<td><html:text maxlength="25"
								property="numeroCartao" size="30" tabindex="4" /></td>
						</tr>
						<tr>
							<td width="30%"><strong>Número da Identificação da Transação
							(NSU):</strong></td>
							<td><html:text maxlength="11" 
								property="numeroIdentificacaoTransacao" size="30" tabindex="5" />
							</td>
						</tr>
						<tr>
							<td width="30%"><strong>Mês/Ano Validade do Cartão:</strong></td>
							<td><html:text maxlength="7"
								property="validadeCartao" size="10" tabindex="6" 
								onkeyup="mascaraAnoMes(this, event);" /></td>
						</tr>
						<tr>
							<td width="30%"><strong>Titular do Cartão de Crédito:</strong></td>
							<td><html:text maxlength="9" property="idCliente"
								size="10" tabindex="7" 
								onkeypress="return validaEnter(event, 'consultarDadosParcelamentoCartaoCreditoAction.do?objetoConsulta=cliente', 'idCliente');" />
								&nbsp;
							
								<a href="javascript:pesquisarClientePopup();">
								<img border="0"
									src="<bean:message key="caminho.imagens"/>pesquisa.gif"
									title="Pesquisar Cliente" /></a> <logic:present
									name="codigoClienteNaoEncontrado" scope="request">
									<input type="text" name="nomeCliente" size="40" readonly="true"
										style="background-color:#EFEFEF; border:0; color: #ff0000"
										value="<bean:message key="atencao.cliente.inexistente"/>" />
								</logic:present> <logic:notPresent
									name="codigoClienteNaoEncontrado" scope="request">
									<html:text property="nomeCliente" size="40" readonly="true"
										style="background-color:#EFEFEF; border:0; color: #000000" />
								</logic:notPresent> <a
									href="javascript:limparCliente();document.forms[0].idCliente.focus();">
								<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
									border="0" title="Apagar" /></a>
							</td>
						</tr>
					</logic:present>
					
					<logic:notPresent name="temPermissaoAlterarDadosCartao" scope="request">
						<tr>
							<td width="40%"><strong>Número do Documento:</strong></td>
							<td><html:text maxlength="25" readonly="true"
								style="background-color:#EFEFEF; border:0"
								property="documentoCartao" size="30" tabindex="1" /></td>
						</tr>
						<tr>
							<td width="30%"><strong>Número do Autorização:</strong></td>
							<td><html:text maxlength="25" readonly="true"
								style="background-color:#EFEFEF; border:0"
								property="autorizacaoCartao" size="30" tabindex="1" /></td>
						</tr>
						<tr>
							<td width="30%"><strong>Número do Cartão de Crédito:</strong></td>
							<td><html:text maxlength="25" readonly="true"
								style="background-color:#EFEFEF; border:0"
								property="numeroCartao" size="30" tabindex="1" /></td>
						</tr>
						<tr>
							<td width="30%"><strong>Número da Identificação da Transação
							(NSU):</strong></td>
							<td><html:text maxlength="25" readonly="true"
								style="background-color:#EFEFEF; border:0"
								property="numeroIdentificacaoTransacao" size="30" tabindex="1" />
							</td>
						</tr>
						<tr>
							<td width="30%"><strong>Mês/Ano Validade do Cartão:</strong></td>
							<td><html:text maxlength="25" readonly="true"
								style="background-color:#EFEFEF; border:0"
								property="validadeCartao" size="10" tabindex="1" /></td>
						</tr>
						<tr>
							<td width="30%"><strong>Titular do Cartão de Crédito:</strong></td>
							<td><html:text readonly="true"
								style="background-color:#EFEFEF; border:0" property="idCliente"
								size="10" tabindex="1" />&nbsp;
								<html:text property="nomeCliente" size="40" readonly="true"
										style="background-color:#EFEFEF; border:0; color: #000000" />
							</td>
						</tr>
					</logic:notPresent>
					<tr>
							<td width="30%"><strong>Usuário Confirmação:</strong></td>
							<td><html:text readonly="true"
								style="background-color:#EFEFEF; border:0"
								property="usuarioConfirmacao" size="50" tabindex="1" /></td>
						</tr>
					<tr>
						<td width="30%"><strong>Quantidade de Parcelas:</strong></td>
						<td><html:text readonly="true"
							style="background-color:#EFEFEF; border:0" property="qtdParcelas"
							size="50" tabindex="1" /></td>
					</tr>
					<tr>
						<td width="30%"><strong>Data Confirmação:</strong></td>
						<td><html:text maxlength="25" readonly="true"
							style="background-color:#EFEFEF; border:0"
							property="dataOperadora" size="10" tabindex="1" /></td>
					</tr>
					
					
					<logic:present name="temPermissaoAlterarDadosCartao" scope="request">
					
					<tr>
						<td width="30%"><strong>Valor da Transação:</strong></td>
						<td><html:text property="valorTransacao" size="20" onkeyup="formataValorMonetario(this, 14)" tabindex="1"/></td>
					</tr>
					
					</logic:present>
					
					<logic:notPresent name="temPermissaoAlterarDadosCartao" scope="request">
					
					<tr>
						<td width="30%"><strong>Valor da Transação:</strong></td>
						<td><html:text property="valorTransacao" size="20" readonly="true" tabindex="1"/></td>
					</tr>
					
					</logic:notPresent>
					
					
					<tr>
						<td>&nbsp;</td>
					</tr>
					<tr>

						<td>
							<div align="left"><input name="ButtonFechar" type="button"
							class="bottonRightCol" value="Fechar"
							onclick="javascript:window.close();"></div>
						</td>
						<td align="right">
							<input type="button" name="Button" class="bottonRightCol"
							value="Salvar" onclick="validarForm();" tabindex="13" />
						</td>
					</tr>
				</table>
				
				<SCRIPT>
					
					resizePageSemLink(790, 620);
					
				</SCRIPT>
			
			</logic:present>
			
			<p>&nbsp;</p>
			</td>
		</tr>
	</table>
</html:form>
</body>
</html:html>
