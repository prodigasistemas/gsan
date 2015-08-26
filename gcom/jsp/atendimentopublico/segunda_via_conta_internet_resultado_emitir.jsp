<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>
<%@ page import="gcom.cobranca.bean.ContaValoresHelper"%>

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
	formName="EmitirSegundaViaContaInternetActionForm"
	dynamicJavascript="true" />



<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script>
<!--
var bCancel = false;

function limparMatricula(form){
	form.matricula.value = '';
}

function validarForm(form){

	if(testarCampoValorZero(document.EmitirSegundaViaContaInternetActionForm.matricula, 'Matrícula')) {

		if(validateEmitirSegundaViaContaInternetActionForm(form)){
    		
    		form.submit();
		}
	}
}
-->
</script>
</head>

<body leftmargin="5" topmargin="5"
	onload="setarFoco('${requestScope.nomeCampo}');">

<html:form action="/emitirSegundaViaContaInternetAction" method="post"
	onsubmit="return validateEmitirSegundaViaContaInternetActionForm(this);">


	<%@ include file="/jsp/util/cabecalho.jsp"%>


	<table width="770" border="0" cellspacing="5" cellpadding="0">
		<tr>

			<td width="655" valign="top" class="centercoltext">
			
			<table height="100%">
				<tr>
					<td></td>
				</tr>
			</table>

			<table width="100%" 
				border="0" 
				align="center" 
				cellpadding="0"
				cellspacing="0">
				
				<tr>
					<td width="11">
						<img border="0"
							src="<bean:message key="caminho.imagens"/>parahead_left.gif" />
					</td>
					
					<td class="parabg">
						<font size="2">
							<logic:present name="ehEmail">
								<strong>Emissão da Conta - Atendimento Internet</strong>
							</logic:present>
							<logic:notPresent name="ehEmail">
								<strong>Emissão da 2ª Via de Conta - Atendimento Internet</strong>
							</logic:notPresent>
						</font>
					</td>
					
					
					<td width="11">
						<img border="0"
							src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			

			<table width="100%" 
				border="0" 
				align="center"
				cellpadding="0" 
				cellspacing="0">

				<tr>
					<td>
						<br>
						<strong>CONFIGURAÇÃO NECESSÁRIA DO NAVEGADOR/COMPUTADOR</strong>
					</td>
				</tr>
				
				<tr>
					<td>1- Internet Explorer 6.0 ou superior;</td>
				</tr>
				
				<tr>
					<td>2 - Mozilla Firefox 1.5 ou superior;</td>
				</tr>
				
				<tr>
					<td>3 - Acrobat Reader 6.0 ou superior;
					<br>
					<strong>PROCEDIMENTOS PARA IMPRESSÃO</strong></td>
				</tr>
				
				<tr>
					<td>1- Imprimir em folha de papel tamanho A4;</td>
				</tr>
				
				<tr>
					<td>2 - Imprimir apenas em impressoras Jato de Tinta ou Laser;</td>
				</tr>

				<tr>
					<td>3- Selecionar qualidade de impressão normal e nunca rascunho;</td>
				</tr>
				
				<tr>
					<td><br>
					<br>
					<strong>PAGAMENTO ON-LINE DE FATURAS EM ABERTO</strong></td>
				</tr>
				
				<tr>
					<td>Obs: A baixa da conta, após o pagamento, será efetuada em até
					2(dois) dias utéis, após compensação.</td>
				</tr>
				
			</table>
				
			<table width="100%" 
				border="0" 
				align="center"
				cellpadding="0" 
				cellspacing="0">
				
				<tr>
					<td>&nbsp;</td>
				</tr>
				
				<tr>
					<td>
						<strong>Matrícula: </strong>
						<html:text property="matricula" 
							size="9" 
							maxlength="9" 
							readonly="true" 
							style="background-color:#EFEFEF; border:0;"/>

					</td>
				</tr>
				
				<tr>
					<td>
						<strong>Nome do Cliente: </strong>
						<html:text property="nomeCliente" 
							size="40" 
							maxlength="60"  
							readonly="true" 
							style="background-color:#EFEFEF; border:0;"/>
					</td>
				</tr>
				
				<tr>
					<td align="left">
						<strong>Localidade Pólo: </strong>
						<html:text property="elo" 
							size="30" 
							maxlength="30" 
							readonly="true" 
							style="background-color:#EFEFEF; border:0;" />
					</td>
					
					<td align="left">
						<strong>Data do Débito&nbsp;&nbsp;&nbsp;:</strong>
						<html:text property="dataDebito" 
							size="10" 
							maxlength="10"  
							readonly="true" 
							style="background-color:#EFEFEF; border:0;"/>
					</td>
				</tr>
			
				
				<tr></tr>
			</table>
				
			<table width="100%" 
				border="0" 
				align="center"
				cellpadding="0" 
				cellspacing="0">
				
				<tr>
					<td colspan="4">
					<table width="100%" cellpadding="0" cellspacing="0">
						<tr>
							<td>
							<table width="100%" border="0" bgcolor="#90c7fc">
								<tr bgcolor="#90c7fc">
									<td width="12%">
										<div align="center"><strong>Mês/Ano da Fatura</strong></div>
									</td>
									<td width="15%">
										<div align="center"><strong>Valor(R$)</strong></div>
									</td>
									<td width="15%">
										<div align="center"><strong>Imprimir</strong></div>
									</td>
									<td width="15%">
										<div align="center"><strong>Pagamento</strong></div>
									</td>
								</tr>
							</table>
							</td>
						</tr>

						<tr>
							<td>
							<%	String cor = "#cbe5fe";	%>
								<div style="width: 100%; height: 300; overflow: auto;">
								
									<table width="100%" 
										align="center" 
										bgcolor="#90c7fc">
										
										<logic:iterate name="colecaoContasValores"
											id="contaValoresHelper" type="ContaValoresHelper">
									<%	if (cor.equalsIgnoreCase("#cbe5fe")) {
											cor = "#FFFFFF";	%>
										<tr bgcolor="#FFFFFF">
									<%	} else {
											cor = "#cbe5fe";%>
										<tr bgcolor="#cbe5fe">
									<%	}	%>
											
											<td width="12%">
												<div align="center">
													<bean:write name="contaValoresHelper"
														property="formatarAnoMesParaMesAno" />
												</div>
											</td>
											
											<td width="15%">
												<div align="center">
												<bean:write name="contaValoresHelper"
													property="valorTotalConta" 
													formatKey="money.format" />
												</div>
											</td>
											
											<td width="15%" align="center">
												<a href="javascript:abrirPopup('gerarRelatorio2ViaContaAction.do?cobrarTaxaEmissaoConta=N&idConta='+<%="" + contaValoresHelper.getConta().getId()%>, 400, 800);">
												<img align="center" border="0"
													src="<bean:message key="caminho.imagens"/>print.gif"
													title="Imprimir Fatura" /></a>
											</td>
											
											<td width="15%" align="center">
											<a href="javascript:abrirPopup('exibirSelecionarBancoAction.do?idConta='+<%="" + contaValoresHelper.getConta().getId()%>, 400, 800);" 
												title="Selecionar Banco"> Selecionar Banco </a>
											</td>
										</tr>
										</logic:iterate>
										
										<logic:present name="colecaoContaValoresPreteritos">
										
										<tr>
										 	 <td colspan="10">
												<div align="center">
											 	 <font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif">
												  <strong>Contas de Clientes Anteriores</strong>
												 </font>
												</div>
											 </td>
										</tr>
										
									<logic:iterate name="colecaoContaValoresPreteritos"
											id="contaValoresPreteritosHelper" type="ContaValoresHelper">
		
											
									<%	if (cor.equalsIgnoreCase("#cbe5fe")) {
											cor = "#FFFFFF";	%>
										<tr bgcolor="#FFFFFF">
									<%	} else {
											cor = "#cbe5fe";%>
										<tr bgcolor="#cbe5fe">
									<%	}	%>
											
											<td width="12%">
												<div align="center">
													<bean:write name="contaValoresPreteritosHelper"
														property="formatarAnoMesParaMesAno" />
												</div>
											</td>
											
											<td width="15%">
												<div align="center">
												<bean:write name="contaValoresPreteritosHelper"
													property="valorTotalConta" 
													formatKey="money.format" />
												</div>
											</td>
											
											<td width="15%" align="center">
												<a href="javascript:abrirPopup('gerarRelatorio2ViaContaAction.do?cobrarTaxaEmissaoConta=N&idConta='+<%="" + contaValoresPreteritosHelper.getConta().getId()%>, 400, 800);">
												<img align="center" border="0"
													src="<bean:message key="caminho.imagens"/>print.gif"
													title="Imprimir Fatura" /></a>
											</td>
											
											<td width="15%" align="center">
											<a href="javascript:abrirPopup('exibirSelecionarBancoAction.do?idConta='+<%="" + contaValoresPreteritosHelper.getConta().getId()%>, 400, 800);" 
												title="Selecionar Banco"> Selecionar Banco </a>
											</td>
										</tr>
										</logic:iterate>

										</logic:present>
										
										<logic:present name="totalContas">
									<%	if (cor.equalsIgnoreCase("#cbe5fe")) {
											cor = "#FFFFFF";	%>
										<tr bgcolor="#FFFFFF">
									<%	} else {
											cor = "#cbe5fe";	%>
										<tr bgcolor="#cbe5fe">
									<%	}	%>
											<td width="12%">
												<div align="center"><strong>TOTAL</strong></div>
											</td>
			
											<td width="15%">
												<div align="center">
													<bean:write name="totalContas"
													formatKey="money.format" /></div>
											</td>
											
											<td width="15%" align="center">&nbsp;</td>
											<td width="15%" align="center">&nbsp;</td>
										</tr>
										</logic:present>
									</table>
								</div>
							</td>
						</tr>
					</table>
					</td>
				</tr>

				<tr>
					<td>&nbsp;</td>
				</tr>
					
				<tr>
					<td>
						<input name="Button" 
							type="button" 
							class="bottonRightCol"
							value="Voltar" 
							align="left" 
							onclick="javascript:history.back();">
							<logic:present name="veioMenu" scope="session">
							
								<input type="button" 
									name="ButtonCancelar" 
									class="bottonRightCol"
									value="Cancelar"
									onClick="javascript:window.location.href='/gsan/telaPrincipal.do'">

							</logic:present>
					</td>
				</tr>
				
				<tr>
					<td>&nbsp;</td>
				</tr>
				
			</table>
			</td>
		</tr>
	</table>
</html:form> 
<%@ include file="/jsp/util/rodape.jsp"%>
</body>
</html:html>