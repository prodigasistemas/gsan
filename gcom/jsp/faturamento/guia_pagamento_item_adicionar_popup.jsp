<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<%@ page import="gcom.util.Util"%>
<%@ page import="java.math.BigDecimal"%>
<%@ page import="gcom.faturamento.conta.Conta" isELIgnored="false"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>

<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false"  formName="AdicionarGuiaPagamentoItemActionForm" />
<script language="JavaScript">


<!-- Begin
function validarForm(form){
	
	if(validateAdicionarGuiaPagamentoItemActionForm(form)){
   		
   		var pagamentoParcial = document.getElementById("Pagamento_Parcial");
   		
   		if (pagamentoParcial == null){
   			form.submit();
   		}
   		else if (RadioNaoVazioMensagem("Conta", "")){
   			form.submit();
   		}
	}
}


function chamarSubmitComUrl(pagina){
	toUpperCase(opener.window.document.forms[0]);
	opener.window.document.forms[0].action = pagina; 
	opener.window.document.forms[0].submit();
}


function pesquisarTipoDebito(){
	var form = document.forms[0];
	if(form.idTipoDebito.disabled == false){
		redirecionarSubmit('exibirPesquisarTipoDebitoAction.do?limparForm=1&tipoFinanciamentoServico=SIM&caminhoRetornoTelaPesquisaTipoDebito=exibirAdicionarGuiaPagamentoItemPopupAction');	
	}
}

function limparTipoDebito(){
	
	var form = document.forms[0];
	
	if(form.idTipoDebito.disabled == false){
		form.idTipoDebito.value = "";
		form.descricaoTipoDebito.value = "";
		form.valorTotalServico.value = "";
		
		redirecionarSubmit('exibirAdicionarGuiaPagamentoItemPopupAction.do');
	}
}

function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
    var form = document.forms[0];

    if (tipoConsulta == 'tipoDebito') {
      form.idTipoDebito.value = codigoRegistro;
      form.descricaoTipoDebito.value = descricaoRegistro;
      form.descricaoTipoDebito.style.color = "#000000";
	  form.submit();
    }
}

function reloadOpener(caminhoRetornoOpener){
  opener.window.document.forms[0].action = caminhoRetornoOpener;
  opener.window.document.forms[0].submit();
}

-->
</script>

</head>


<logic:present name="reloadPage">
	<logic:notPresent name="desabilitaIdDebito" scope="request">
		<body leftmargin="5" topmargin="5" onload="resizePageSemLink(610, 350); reloadOpener('${caminhoRetornoOpener}');">
	</logic:notPresent>
	<logic:present name="desabilitaIdDebito" scope="request">
		<body leftmargin="5" topmargin="5" onload="resizePageSemLink(610, 350); reloadOpener('${caminhoRetornoOpener}');window.close();">
	</logic:present>
</logic:present>

<logic:notPresent name="reloadPage">
	<body leftmargin="5" topmargin="5" onload="resizePageSemLink(610, 350);">
</logic:notPresent>

<html:form action="/adicionarGuiaPagamentoItemPopupAction.do?desabilitaIdDebito=${desabilitaIdDebito}"
	name="AdicionarGuiaPagamentoItemActionForm"
	type="gcom.gui.faturamento.AdicionarGuiaPagamentoItemActionForm"
	method="post"
	onsubmit="return validateAdicionarGuiaPagamentoItemActionForm(this);">
	
	<table width="580" border="0" cellspacing="5" cellpadding="0">
		<tr>
			<td width="530" valign="top" class="centercoltext">
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
					<td class="parabg">Adicionar Item da Guia de Pagamento</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<table width="100%" border="0">
			<tr>
				<td colspan="4">Para adicionar um item na guia de pagamento, informe os dados abaixo:</td>
				<td align="right"></td>
			</tr>
			</table>
				
			<table width="100%" border="0">
			<tr>
				<td colspan="4"><p>&nbsp;</p></td>
			</tr>
				
			<tr>
				<td width="140"><strong>Tipo de Débito:</strong><font color="#FF0000">*</font></td>
				<td colspan="3" align="right">
				<div align="left">
						
					<logic:notPresent name="desabilitaIdDebito" scope="request">
					
							<html:text property="idTipoDebito" size="9" 
							onkeypress="validaEnter(event, 'exibirAdicionarGuiaPagamentoItemPopupAction.do?objetoConsulta=1', 'idTipoDebito');"
							maxlength="9" tabindex="4"  />
							<a href="javascript:pesquisarTipoDebito();"> <img width="23" height="21" border="0" src="<bean:message key="caminho.imagens"/>pesquisa.gif" title="Pesquisar Tipo de Débito" /></a> 
					
					</logic:notPresent>
						
					<logic:present name="desabilitaIdDebito" scope="request">
					
							<html:text property="idTipoDebito" size="9" 
							maxlength="9" tabindex="4"  readonly="true"/>
					
					</logic:present>
					
					<logic:present name="corDebitoTipo">
					
						<logic:equal name="corDebitoTipo" value="exception">
						
								<html:text property="descricaoTipoDebito" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000"
								size="40" maxlength="45" />
						
						</logic:equal>
					
						<logic:notEqual name="corDebitoTipo" value="exception">
					
								<html:text property="descricaoTipoDebito" size="40"
								maxlength="40" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						
						</logic:notEqual>
					
					</logic:present> 
						
					<logic:notPresent name="corDebitoTipo">
					
						<logic:empty name="AdicionarGuiaPagamentoItemActionForm" property="idTipoDebito">
					
							<html:text property="descricaoTipoDebito" size="40"
							maxlength="40" readonly="true"
							style="background-color:#EFEFEF; border:0; color: #ff0000" />
					
						</logic:empty>
						<logic:notEmpty name="AdicionarGuiaPagamentoItemActionForm" property="idTipoDebito">
					
							<html:text property="descricaoTipoDebito" size="40"
							maxlength="40" readonly="true"
							style="background-color:#EFEFEF; border:0; color: #000000" />
					
						</logic:notEmpty>
					
					</logic:notPresent> 
						
					<logic:notPresent name="desabilitaIdDebito" scope="request">
					
						<a href="javascript:limparTipoDebito();"> <img src="<bean:message key="caminho.imagens"/>limparcampo.gif" border="0" title="Apagar Tipo de Débito" /> </a>
					
					</logic:notPresent>
					
				</div>
				</td>
			</tr>
			
			<logic:equal name="alterarValorSugeridoEmTipoDebito"
									value="true">
				<tr>
					<td><strong>Valor Total do Servi&ccedil;o:<strong><font color="#FF0000">*</font></strong></strong></td>
					<td colspan="3" align="right">
						
						<div align="left">
							
							<html:text property="valorTotalServico" style="text-align: right;"
							onkeyup="formataValorMonetario(this, 11);"
							maxlength="14" size="14" tabindex="5" />
						
						</div>
					</td>
				</tr>
			
			</logic:equal>
			
			<logic:notEqual name="alterarValorSugeridoEmTipoDebito" value="true">
				
				<tr>
					<td><strong>Valor Total do Servi&ccedil;o:<strong><font color="#FF0000">*</font></strong></strong></td>
					<td colspan="3" align="right">
					
						<div align="left">
							
							<html:text property="valorTotalServico" 
							onkeyup="formataValorMonetario(this, 11);"
							readonly="true"  style="text-align:right;background-color:#EFEFEF; border:0; color: #000000"
							maxlength="14" size="14" tabindex="5" />
						
						</div>
					
					</td>
				</tr>
				
			</logic:notEqual>
				
            <tr> 
            	<td><strong> <font color="#FF0000"></font></strong></td>
                <td align="right" colspan="3"> <div align="left"><strong><font color="#FF0000">*</font></strong> 
                    Campos obrigat&oacute;rios</div></td>
            </tr>
            
            <tr> 
            	<td height="20"></td>
            </tr> 
			
			<!-- CONTAS PARA SELEÇÃO -->
			
			<logic:present name="colecaoContaParaPagamentoParcial">
			
			<tr>
				<td colspan="3">

				<table width="100%" border="0">
					<tr>
						<td colspan="4">
						<table width="100%" cellpadding="0" cellspacing="0">
							<tr>
								<td>
									<table width="100%" border="0">
									<tr>
										<td height="20">
											<span id="Pagamento_Parcial"></span>
											Selecione uma das contas para realização do pagamento parcial:
										</td>
									</tr>
									</table>
									
									<table width="100%" border="0" bgcolor="#90c7fc">
									<tr bgcolor="#90c7fc">

										<td width="5%"></td>
										<td width="10%">
											<div align="center"><strong>Refe.</strong></div>
										</td>
										<td width="13%">
											<div align="center"><strong>Venc.</strong></div>
										</td>
										<td width="10%">
											<div align="center"><strong>Valor</strong></div>
										</td>
										<td width="10%">
											<div align="center"><strong>Água</strong></div>
										</td>
										<td width="10%">
											<div align="center"><strong>Esgoto</strong></div>
										</td>
										<td width="13%">
											<div align="center"><strong>Validade</strong></div>
										</td>
										<td width="13%">
											<div align="center"><strong>Revisão</strong></div>
										</td>
										<td width="16%">
											<div align="center"><strong>Situação</strong></div>
										</td>

									</tr>
									</table>

								</td>
							</tr>

							<tr>
								<td>
										
									<% String cor = "#cbe5fe";%>

									<div style="width: 100%; height: 100; overflow: auto;">
										
									<table width="100%" align="center" bgcolor="#90c7fc">
										
									<logic:iterate name="colecaoContaParaPagamentoParcial" id="conta" type="Conta">

								
										<%	if (cor.equalsIgnoreCase("#cbe5fe")){
												cor = "#FFFFFF";%>
												<tr bgcolor="#FFFFFF">
										<%} else{
												cor = "#cbe5fe";%>
												<tr bgcolor="#cbe5fe">
										<%}%>
												
												<td align="center" width="5%" valign="middle">
														
													<INPUT TYPE="radio" NAME="conta" value="<%="" + conta.getId().intValue()%>">
														
												</td>
													
												<td width="10%" align="center">
															
													<%=""+ Util.formatarMesAnoReferencia(conta.getReferencia())%>													
														
												</td>
												<td width="13%">
													
													<div align="center">
													
														<logic:present name="conta" property="dataVencimentoConta">
															
															<span style="color: #000000;">
																
																<%="" + Util.formatarData(conta.getDataVencimentoConta())%>
																
															</span>
														
														</logic:present> 
														
														<logic:notPresent name="conta" property="dataVencimentoConta">
																&nbsp;
														</logic:notPresent>
														
													</div>
													
												</td>
												<td width="10%">
												
													<div align="right">
													
														<span style="color: #000000;">
														
															<%="" + Util.formatarMoedaReal(new BigDecimal(conta.getValorTotalConta())).trim()%>
														
														</span>
													
													</div>
												
												</td>
												<td width="10%">
													
													<div align="center">
													
														<logic:present name="conta" property="consumoAgua">
															
															<bean:write name="conta" property="consumoAgua" />
													
														</logic:present> 
														
														<logic:notPresent name="conta" property="consumoAgua">
															&nbsp;
														</logic:notPresent>
														
													</div>
												
												</td>
												
												<td width="10%">
												
													<div align="center">
													
														<logic:present name="conta" property="consumoEsgoto">
														
															<bean:write name="conta" property="consumoEsgoto" />
													
														</logic:present> 
														
														<logic:notPresent name="conta" property="consumoEsgoto">
															&nbsp;
														</logic:notPresent>
													
													</div>
												
												</td>
												<td width="13%">
													
													<div align="center">
													
														<logic:present name="conta" property="dataValidadeConta">
														
															<span style="color: #000000;">
															
																<%="" + Util.formatarData(conta.getDataValidadeConta())%>
																
															</span>
													
														</logic:present> 
														
														<logic:notPresent name="conta" property="dataValidadeConta">
															&nbsp;
														</logic:notPresent>
														
													</div>
												</td>
												<td width="13%">
													
													<div align="center">
													
														<logic:present name="conta" property="dataRevisao">
														
															<span style="color: #000000;">
															
																<%="" + Util.formatarData(conta.getDataRevisao())%>
																
															</span>
														</logic:present> 
														
														<logic:notPresent name="conta" property="dataRevisao">
															&nbsp;
														</logic:notPresent>
													
													</div>
												</td>
												<td width="16%">
													
													<div align="center">
													
														<logic:present name="conta" property="debitoCreditoSituacaoAtual">
															<bean:write name="conta" property="debitoCreditoSituacaoAtual.descricaoDebitoCreditoSituacao" />
														</logic:present> 
														
														<logic:notPresent name="conta" property="debitoCreditoSituacaoAtual">
															&nbsp;
														</logic:notPresent>
													
													</div>
													
												</td>
											</tr>
											

									</logic:iterate>
										
									</table>
										
									</div>
								</td>
							</tr>

							</table>
							</td>
						</tr>

					</table>

					</td>
				</tr>
			
			</logic:present>
			
			<!-- FIM CONTAS PARA SELEÇÃO -->
			
			<tr>
				<td>
					<input type="button" name="Button" class="bottonRightCol" value="Fechar" tabindex="4"
					onClick="window.close();" />	
                </td>
                <td  align="right">
   	                <input type="button" name="Button" class="bottonRightCol" value="Inserir" tabindex="3"
					onClick="validarForm(document.forms[0]);" />
                </td>
			</tr>
				
				
			</table>
			<p>&nbsp;</p>
			<p>&nbsp;</p>
	</table>
</html:form>
</body>

</html:html>
