<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>

<%@ page import="gcom.cobranca.bean.ContaValoresHelper"
	isELIgnored="false"%>
<%@ page import="gcom.cobranca.bean.GuiaPagamentoValoresHelper"
	isELIgnored="false"%>
<%@ page import="gcom.util.ConstantesSistema" isELIgnored="false"%>
<%@ page import="java.util.Collection" isELIgnored="false"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
<head>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>

<script language="JavaScript">
<!--
// Faz as validações do formulário
function validateEfetuarParcelamentoDebitosActionForm(){
	return true;
}

function enviar(){
	redirecionarSubmit('efetuarParcelamentoDebitosWizardAction.do?action=exibirEfetuarParcelamentoDebitosProcesso2Action&calcula=1');
}

function limparRadioButtonTodos(){
	redirecionarSubmit('efetuarParcelamentoDebitosWizardAction.do?action=exibirEfetuarParcelamentoDebitosProcesso2Action&limpaCombo=1');
}

function limpaRadioButton(nomeCampo){
	var form = document.forms[0];
  	if(nomeCampo.checked && nomeCampo.id != "0"){
   		nomeCampo.checked = false;
		nomeCampo.id = "0";
	}else{
		nomeCampo.id = "1";
		for (i=0; i < form.elements.length; i++){
			if( form.elements[i].type == "radio" && form.elements[i].name == nomeCampo.name 
					&& form.elements[i].checked == false ){
				form.elements[i].id = 0;
			}
		}
	}
}

-->
</script>
</head>

<body leftmargin="5" topmargin="5">
<div id="formDiv">
<html:form action="/efetuarParcelamentoDebitosWizardAction"
	name="EfetuarParcelamentoDebitosActionForm"
	type="gcom.gui.cobranca.EfetuarParcelamentoDebitosActionForm"
	method="post">

	<jsp:include
		page="/jsp/util/wizard/navegacao_abas_wizard_tela_espera.jsp?numeroPagina=2" />

	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>

	<input type="hidden" name="numeroPagina" value="2" />
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
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_left.gif" /></td>
					<td class="parabg">Efetuar Parcelamento de Débitos</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<!-- Início do Corpo - Roberta Costa -->
			<p>&nbsp;</p>

			<table width="100%" border="0">
				<tr>
					<td colspan="2">Para efetuar o parcelamento de d&eacute;bitos
					informe o im&oacute;vel:</td>
				</tr>
				<tr>
				<td width="30%" >&nbsp;</td>
		        <logic:present scope="application" name="urlHelp">
						<td align="right"><a href="javascript:abrirPopupHelp('${applicationScope.urlHelp}cobrancaParcelamentoEfetuar-AbaDebitos', 500, 700);"><span style="font-weight: bold"><font color="#3165CE">Ajuda</font></span></a></td>									
				</logic:present>
				<logic:notPresent scope="application" name="urlHelp">
						<td align="right"><span style="font-weight: bold"><font color=#696969><u>Ajuda</u></font></span></td>									
				</logic:notPresent>
				</tr>
				<tr>
					<td colspan="2">
					<table border="0">
						<tr>
							<td><strong>Matr&iacute;cula do Im&oacute;vel:</strong></td>
							<td><html:text property="matriculaImovel" size="10"
								readonly="true" style="background-color:#EFEFEF; border:0" /></td>
						</tr>
					</table>
					</td>
				</tr>
				<tr>
					<td colspan="4">
					<table border="0" bgcolor="#99CCFF" width="100%">
						<tr bgcolor="#99CCFF">
							<td colspan="2" align="center"><strong>Dados do Imóvel</strong></td>
						</tr>
						<tr>
							<td>
							<table border="0" bgcolor="#cbe5fe" width="100%">
								<tr>
									<td width="32%"><strong>Inscri&ccedil;&atilde;o do
									Im&oacute;vel:</strong></td>
									<td><html:text property="inscricaoImovel" size="25"
										readonly="true" style="background-color:#EFEFEF; border:0" />
									</td>
								</tr>
								<tr>
									<td><strong>Cliente Usu&aacute;rio:</strong></td>
									<td><html:text property="nomeCliente" size="45" readonly="true"
										style="background-color:#EFEFEF; border:0" /></td>
								</tr>
								<tr>
									<td><strong>CPF ou CNPJ:</strong></td>
									<td><html:text property="cpfCnpj" size="45" readonly="true"
										style="background-color:#EFEFEF; border:0" /></td>
								</tr>
								<tr>
									<td><strong>Situa&ccedil;&atilde;o da Liga&ccedil;&atilde;o de
									&Aacute;gua:</strong></td>
									<td><html:text property="situacaoAgua" size="45"
										readonly="true" style="background-color:#EFEFEF; border:0" />
									</td>
								</tr>
								<tr>
									<td><strong>Situa&ccedil;&atilde;o da Liga&ccedil;&atilde;o de
									Esgoto:</strong></td>
									<td><html:text property="situacaoEsgoto" size="45"
										readonly="true" style="background-color:#EFEFEF; border:0" />
									</td>
								</tr>
								<tr> 
									<td><strong>Perfil do Imóvel:</strong></td>
									<td>
										<html:text property="descricaoPerfilImovel" size="45"
											readonly="true" style="background-color:#EFEFEF; border:0" />
									</td>
								</tr>
							</table>
							</td>
						</tr>
					</table>
					</td>
				</tr>
				<tr>
					<td colspan="4"><br>
						<table width="100%" align="center" bgcolor="#99CCFF" border="0">
							<tr>
								<td align="center">
									<strong>Endere&ccedil;o do Im&oacute;vel</strong>
								</td>
							</tr>
							<tr bgcolor="#cbe5fe">
								<td align="center" bgcolor="#FFFFFF" height="20">
									<span id="endereco">
										<logic:present name="EfetuarParcelamentoDebitosActionForm" property="endereco">
											<bean:write name="EfetuarParcelamentoDebitosActionForm" 
												property="endereco"/>
										</logic:present>
									</span>	
									<logic:notPresent name="EfetuarParcelamentoDebitosActionForm" property="endereco">
									&nbsp;
									</logic:notPresent>													
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td colspan="4" height="23"><br>
					<font color="#000000"> <strong>Valor dos D&eacute;bitos do
					Im&oacute;vel:</strong> </font></td>
				</tr>
				<tr>
					<td colspan="4">
					<table width="100%" bgcolor="#99CCFF">
						<tr bgcolor="#90c7fc">
							<td align="center" width="30%"><strong> Contas</strong></td>
							<td align="center" width="30%"><strong>Guias de Pagamento</strong>
							</td>
							<td align="center" width="40%"><strong>Acr&eacute;scimos
							Impontualidade</strong></td>
						</tr>
						<tr bgcolor="#cbe5fe">
							<td align="right" height="20" bgcolor="#FFFFFF">
								<bean:write
								name="EfetuarParcelamentoDebitosActionForm"
								property="valorTotalContaValores" />
							</td>
							<td align="right" bgcolor="#FFFFFF">
								<bean:write
								name="EfetuarParcelamentoDebitosActionForm"
								property="valorGuiasPagamento" />
							</td>
							<td align="right" bgcolor="#FFFFFF">
							<logic:notEqual
								name="EfetuarParcelamentoDebitosActionForm" property="valorAcrescimosImpontualidade"
								value="0,00">
								<a href="javascript:abrirPopup('exibirValorAtualizacaoConsultarPopupAction.do?multa=<bean:write name="EfetuarParcelamentoDebitosActionForm" property="valorMulta" />&juros=<bean:write name="EfetuarParcelamentoDebitosActionForm" property="valorJurosMora" />&atualizacao=<bean:write name="EfetuarParcelamentoDebitosActionForm" property="valorAtualizacaoMonetaria" />', 300, 650);">
								<bean:write name="EfetuarParcelamentoDebitosActionForm"
									property="valorAcrescimosImpontualidade" formatKey="money.format" />
								</a>
							</logic:notEqual>
							<logic:equal name="EfetuarParcelamentoDebitosActionForm"
								property="valorAcrescimosImpontualidade" value="0,00">
								<bean:write name="EfetuarParcelamentoDebitosActionForm"
									property="valorAcrescimosImpontualidade" formatKey="money.format" />
							</logic:equal>
							</td>
						</tr>
					</table>
					<br>
					<table width="100%" bgcolor="#99CCFF">
						<tr bgcolor="#90c7fc">
							<td align="center" colspan="2"><strong>D&eacute;bitos a Cobrar</strong>
							</td>
							<td align="center" rowspan="2"><strong>Cr&eacute;ditos a Realizar</strong>
							</td>
							<td align="center" rowspan="2"><strong>D&eacute;bito Total
							Atualizado</strong></td>
						</tr>
						<tr bgcolor="#90c7fc">
							<td align="center" bgcolor="#cbe5fe" width="20%"><strong>Servi&ccedil;o</strong></td>
							<td align="center" bgcolor="#cbe5fe"><strong>Parcelamento</strong></td>
						</tr>
						<tr bgcolor="cbe5fe">
							<td align="right" height="20" bgcolor="#FFFFFF">
								<logic:notEqual name="EfetuarParcelamentoDebitosActionForm" 
									property="valorDebitoACobrarServico" value="0,00">
									<a href="javascript:abrirPopup('exibirConsultarDebitoACobrarAction.do?imovelID=
										<bean:define name="EfetuarParcelamentoDebitosActionForm" property="matriculaImovel" id="imovel" />
											<bean:write name="EfetuarParcelamentoDebitosActionForm" property="matriculaImovel" />&parcelamentoID=', 600, 800);">
										<bean:write name="EfetuarParcelamentoDebitosActionForm" property="valorDebitoACobrarServico"  formatKey="money.format"/>
									</a>
								</logic:notEqual>
								<logic:equal name="EfetuarParcelamentoDebitosActionForm" 
									property="valorDebitoACobrarServico" value="0,00">
									<bean:write	name="EfetuarParcelamentoDebitosActionForm" property="valorDebitoACobrarServico" formatKey="money.format" />
								</logic:equal>
							</td>
							<td align="right" bgcolor="#FFFFFF">
								<logic:notEqual name="EfetuarParcelamentoDebitosActionForm" 
									property="valorDebitoACobrarParcelamento" value="0,00">
									<a href="javascript:abrirPopup('exibirConsultarDebitoACobrarAction.do?imovelID=
										<bean:define name="EfetuarParcelamentoDebitosActionForm" property="matriculaImovel" id="imovel" />
											<bean:write name="EfetuarParcelamentoDebitosActionForm" property="matriculaImovel" />&parcelamentoID=', 600, 800);">
										<bean:write name="EfetuarParcelamentoDebitosActionForm" property="valorDebitoACobrarParcelamento"  formatKey="money.format"/>
									</a>
								</logic:notEqual>
								<logic:equal name="EfetuarParcelamentoDebitosActionForm" 
									property="valorDebitoACobrarParcelamento" value="0,00">
									<bean:write	name="EfetuarParcelamentoDebitosActionForm" property="valorDebitoACobrarParcelamento" formatKey="money.format" />
								</logic:equal>
							</td>
							<td align="right" bgcolor="#FFFFFF">
								<logic:notEqual name="EfetuarParcelamentoDebitosActionForm" 
									property="valorCreditoARealizar" value="0,00">
									<a href="javascript:abrirPopup('exibirConsultarCreditoARealizarAction.do?imovelID=<bean:define name="EfetuarParcelamentoDebitosActionForm" property="matriculaImovel" id="imovel" />
										<bean:write name="EfetuarParcelamentoDebitosActionForm" property="matriculaImovel" />&parcelamentoID=', 600, 800);">
											<bean:write name="EfetuarParcelamentoDebitosActionForm" property="valorCreditoARealizar" formatKey="money.format"/>
									</a>
								</logic:notEqual>
								<logic:equal name="EfetuarParcelamentoDebitosActionForm" 
									property="valorCreditoARealizar" value="0,00">
									<bean:write	name="EfetuarParcelamentoDebitosActionForm" property="valorCreditoARealizar" formatKey="money.format" />
								</logic:equal>
							</td>
							<td align="right" bgcolor="#FFFFFF"><bean:write
								name="EfetuarParcelamentoDebitosActionForm"
								property="valorDebitoTotalAtualizado" />
							</td>
						</tr>
					</table>
					</td>
				</tr>
				<tr>
					<td><br>
					</td>
				</tr>
				<tr>
					<td colspan="4" height="23"><strong>Contas em D&eacute;bito:</strong></td>
				</tr>
				<%int cont = 0;%>
				<tr>
					<td colspan="4">
					<table width="100%" bgcolor="#99CCFF">
						<logic:empty name="colecaoContaValores" scope="session">
							<tr bgcolor="#90c7fc">
								<td align="center"><strong>EP</strong></td>
								<td align="center"><strong>NB</strong></td>
								<td align="center"><strong>M&ecirc;s/Ano</strong></td>
								<td align="center"><strong>Vencimento</strong></td>
								<td align="center"><strong>Valor da Conta</strong></td>
								<td align="center"><strong>Acréscimos por Impontualidade</strong>
								</td>
							</tr>
						</logic:empty>
						<logic:notEmpty name="colecaoContaValores" scope="session">
							<%if (((Collection) session.getAttribute("colecaoContaValores")).size() 
									<= ConstantesSistema.NUMERO_MAXIMO_REGISTROS_CONTAS_DEBITO) {%>
							<tr bgcolor="#90c7fc">
								<td align="center"><strong>EP</strong></td>
								<td align="center"><strong>NB</strong></td>
								<td align="center"><strong>M&ecirc;s/Ano</strong></td>
								<td align="center"><strong>Vencimento</strong></td>
								<td align="center"><strong>Valor da Conta</strong></td>
								<td align="center"><strong>Acréscimos por Impontualidade</strong>
								</td>
							</tr>
							
							<logic:iterate name="colecaoContaValores"
								type="ContaValoresHelper" id="contaValores">
								
								<c:if test="${contaValores.revisao eq 2}">
								
								<%cont = cont + 1;
								if (cont % 2 == 0) {%>
								<tr bgcolor="#cbe5fe">
								<%} else {%>
								<tr bgcolor="#FFFFFF">
								<%}%>
									<td align="center" height="20">
										<c:if test="${contaValores.indicadorContasDebito eq 1}" var="igual1">
											<input type="radio"
												name="indicadorContasDebito<bean:write name="contaValores" property="conta.id"/>"
												value="1" checked="true" onclick="limpaRadioButton(this);" id = "1">
										</c:if> 
										<c:if test="${!igual1}">
											<input type="radio"
												name="indicadorContasDebito<bean:write name="contaValores" property="conta.id"/>"
												value="1" onclick="limpaRadioButton(this);" id = "0">
										</c:if>
									</td>
									<td align="center" height="20">
										<c:if test="${contaValores.indicadorContasDebito eq 2}" var="igual2">
											<input type="radio"
												name="indicadorContasDebito<bean:write name="contaValores" property="conta.id"/>"
												value="2" checked="true" onclick="limpaRadioButton(this);" id = "1">
										</c:if> 
										<c:if test="${!igual2}">
											<input type="radio"
												name="indicadorContasDebito<bean:write name="contaValores" property="conta.id"/>"
												value="2" onclick="limpaRadioButton(this);" id = "0">
										</c:if>
									</td>
									<td width="10%" align="center">
									<logic:notEmpty name="contaValores" property="conta">
										<logic:notEmpty name="contaValores" property="conta.dataRevisao">
								          <a href="javascript:abrirPopup('exibirConsultarContaAction.do?contaID=<bean:define name="contaValores" property="conta" id="conta" /><bean:write name="conta" property="id" />&tipoConsulta=conta', 600, 800);">
									           <font color="#ff0000"><bean:write name="contaValores" property="conta.formatarAnoMesParaMesAno"/></font>
								          </a>
								        </logic:notEmpty>  
										<logic:empty name="contaValores" property="conta.dataRevisao">
								          <a href="javascript:abrirPopup('exibirConsultarContaAction.do?contaID=<bean:define name="contaValores" property="conta" id="conta" /><bean:write name="conta" property="id" />&tipoConsulta=conta', 600, 800);">
									           <bean:write name="contaValores" property="conta.formatarAnoMesParaMesAno" />
										</logic:empty>
							         </logic:notEmpty>
							         <logic:empty name="contaValores" property="conta">
							              <bean:write name="contaValores"
									        property="conta.formatarAnoMesParaMesAno" />
						             </logic:empty> 
									</td>
									<td width="12%" align="center">
										<bean:write name="contaValores"
											property="conta.dataVencimentoConta" formatKey="date.format" />
									</td>
									<td width="30%" align="right"><bean:write name="contaValores" property="conta.valorTotal"
										formatKey="money.format" /></td>
									<td width="30%" align="right">
										<bean:write name="contaValores"
											property="valorTotalContaValoresParcelamento" formatKey="money.format" />
									</td>
								</tr>
								
								</c:if>
							</logic:iterate>
							
							<%} else {%>
							
							<tr bgcolor="#90c7fc">
								<td align="center" width="8%"><strong>EP</strong></td>
								<td align="center" width="7%"><strong>NB</strong></td>
								<td align="center" width="10%"><strong>M&ecirc;s/Ano</strong></td>
								<td align="center" width="14%"><strong>Vencimento</strong></td>
								<td align="center" width="29%"><strong>Valor da Conta</strong></td>
								<td align="center"><strong>&nbsp;Acréscimos por Impontualidade</strong>
								</td>
							</tr>
							<tr>
								<td height="100" colspan="6">
								<div style="width: 100%; height: 100%; overflow: auto;">
								<table width="100%">
									
									<logic:iterate name="colecaoContaValores"
										type="ContaValoresHelper" id="contaValores">
										
										<c:if test="${contaValores.revisao eq 2}">
										
										<%cont = cont + 1;
										if (cont % 2 == 0) {%>
										<tr bgcolor="#cbe5fe">
										<%} else {%>
										<tr bgcolor="#FFFFFF">
										<%}%>
											<td align="center" height="20" width="8%">
												<c:if test="${contaValores.indicadorContasDebito eq 1}" var="igual1">
												<input type="radio"
													name="indicadorContasDebito<bean:write name="contaValores" property="conta.id"/>"
													value="1" checked="true" onclick="limpaRadioButton(this);" id="1">
												</c:if> 
												<c:if test="${!igual1}">
												<input type="radio"
													name="indicadorContasDebito<bean:write name="contaValores" property="conta.id"/>"
													value="1" onclick="limpaRadioButton(this);"id="0">
												</c:if>
											</td>
											<td align="center" height="20" width="7%">
												<c:if test="${contaValores.indicadorContasDebito eq 2}"	var="igual2">
													<input type="radio"
														name="indicadorContasDebito<bean:write name="contaValores" property="conta.id"/>"
														value="2" checked="true" onclick="limpaRadioButton(this);" id ="1">
												</c:if> 
												<c:if test="${!igual2}">
													<input type="radio"
														name="indicadorContasDebito<bean:write name="contaValores" property="conta.id"/>"
														value="2" onclick="limpaRadioButton(this);" id="0">
												</c:if>
											</td>
											<td width="10%" align="center">
											<logic:notEmpty name="contaValores" property="conta">
									          <a href="javascript:abrirPopup('exibirConsultarContaAction.do?contaID=<bean:define name="contaValores" property="conta" id="conta" /><bean:write name="conta" property="id" />&tipoConsulta=conta', 600, 800);">
									           <bean:write name="contaValores" property="conta.formatarAnoMesParaMesAno" />
									          </a>
									         </logic:notEmpty>
									         <logic:empty name="contaValores" property="conta">
								              <bean:write name="contaValores"
										        property="conta.formatarAnoMesParaMesAno" />
								             </logic:empty> 
											</td>
											<td width="15%" align="center"><bean:write
												name="contaValores" property="conta.dataVencimentoConta"
												formatKey="date.format" /></td>
											<td width="30%" align="right">
											<bean:write name="contaValores" property="conta.valorTotal"
												formatKey="money.format" /></td>
											<td align="right"><bean:write name="contaValores"
												property="valorTotalContaValores" formatKey="money.format" />
											</td>
										</tr>
										
										</c:if>
										
									</logic:iterate>
								</table>
								</div>
								</td>
							</tr>
							<%}%>
						</logic:notEmpty>
					</table>
					</td>
				</tr>
				<tr>
					<td width="25%">
						<input type="button" name="" value="Limpar EP/NB" class="bottonRightCol" onClick="limparRadioButtonTodos()" />
					</td>
					<td colspan="3" height="23">
						<font color="#000000" style="font-size:10px" face="Verdana, Arial, Helvetica, sans-serif"> 
							<strong>EP-Entrada de Parcelamento; NB-Conta Paga e Ainda N&atilde;o Baixada</strong>
						</font><br>
						<font color="#ff0000" style="font-size:10px" face="Verdana, Arial, Helvetica, sans-serif"> 
							Contas em Revisão
						</font>
						
					</td>
				</tr>
				<tr>
					<td colspan="4" align="right"><input type="button" name=""
						value="Calcular" class="bottonRightCol" onClick="enviar();" /></td>
				</tr>
				<tr>
					<td colspan="4" height="23"><strong>Guias de Pagamento em
					D&eacute;bito:</strong></td>
				</tr>
				<% cont = 0;%>
				<tr>
					<td colspan="4">
					<table width="100%" bgcolor="#99CCFF">
						<logic:empty name="colecaoGuiaPagamentoValores" scope="session">
							<tr bgcolor="#90c7fc">
								<td align="center" width="30%"><strong>Tipo do D&eacute;bito</strong></td>
								<td align="center"><strong>Prest.</strong></td>
								<td align="center"><strong>Data de Emissão</strong></td>
								<td align="center"><strong>Data de Vencimento</strong></td>
								<td align="center"><strong>Valor</strong></td>
								<td align="center"><strong>Acrésc. por Impont.</strong></td>
							</tr>
						</logic:empty>
						<logic:notEmpty name="colecaoGuiaPagamentoValores" scope="session">
							<%if (((Collection) session.getAttribute("colecaoGuiaPagamentoValores")).size() 
									<= ConstantesSistema.NUMERO_MAXIMO_REGISTROS_GUIA_PAGAMENTO) {%>
							<tr bgcolor="#90c7fc">
								<td align="center" width="28%"><strong>Tipo do D&eacute;bito</strong></td>
								<td align="center"><strong>Prest.</strong></td>
								<td align="center"><strong>Data de Emissão</strong></td>
								<td align="center"><strong>Data de Vencimento</strong></td>
								<td align="center"><strong>Valor</strong></td>
								<td align="center"><strong>Acrésc. por Impont.</strong></td>
							</tr>
							<logic:iterate name="colecaoGuiaPagamentoValores"
								type="GuiaPagamentoValoresHelper" id="guiaPagamentoValores">
								<%cont = cont + 1;
								if (cont % 2 == 0) {%>
								<tr bgcolor="#cbe5fe">
								<%} else {%>
								<tr bgcolor="#FFFFFF">
								<%}%>
									<td width="28%" align="center" height="20">
										<a href="javascript:abrirPopup('exibirConsultarGuiaPagamentoAction.do?guiaPagamentoId=<bean:define name="guiaPagamentoValores" property="guiaPagamento" id="guiaPagamento" />
											<bean:write name="guiaPagamento" property="id" />', 600, 800);"><span style="color: #000000;"><bean:write name="guiaPagamentoValores" property="guiaPagamento.debitoTipo.descricao" /></span></a></td>
									<td width="10%" align="center"><bean:write name="guiaPagamentoValores" property="guiaPagamento.prestacaoFormatada" /></td>		
									<td width="12%" align="center"><bean:write name="guiaPagamentoValores" property="guiaPagamento.dataEmissao" formatKey="date.format" /></td>
									<td width="12%" align="center"><bean:write name="guiaPagamentoValores" property="guiaPagamento.dataVencimento"	formatKey="date.format" /></td>
									<td width="23%" align="right"><bean:write name="guiaPagamentoValores" property="guiaPagamento.valorDebito" formatKey="money.format" /></td>
									<td width="15%" align="right"><bean:write name="guiaPagamentoValores" property="valorAcrescimosImpontualidade" formatKey="money.format" /></td>
								</tr>
							</logic:iterate>
							<%} else {%>
							<tr bgcolor="#90c7fc">
								<td align="center" width="27%"><strong>Tipo do D&eacute;bito</strong></td>
								<td align="center" width="10%"><strong>Prest.</strong></td>
								<td align="center" width="11%"><strong>Data de Emissão</strong></td>
								<td align="center" width="13%"><strong>Data de Vencimento</strong></td>
								<td align="center" width="21%"><strong>Valor</strong></td>
								<td align="center"><strong>Acrésc. por Impont.</strong>
								</td>
							</tr>
							<tr>
								<td height="100" colspan="6">
								<div style="width: 100%; height: 100%; overflow: auto;">
								<table width="100%">
									<logic:iterate name="colecaoGuiaPagamentoValores"
										type="GuiaPagamentoValoresHelper" id="guiaPagamentoValores">
											<%cont = cont + 1;
											if (cont % 2 == 0) {%>
											<tr bgcolor="#cbe5fe">
											<%} else {%>
											<tr bgcolor="#FFFFFF">
											<%}%>
											<td width="28%" align="center" height="20">
											   <a href="javascript:abrirPopup('exibirConsultarGuiaPagamentoAction.do?guiaPagamentoId=<bean:define name="guiaPagamentoValores" property="guiaPagamento" id="guiaPagamento" /><bean:write name="guiaPagamento" property="id" />', 600, 800);">
												<span style="color: #000000;"> <bean:write	name="guiaPagamentoValores" property="guiaPagamento.debitoTipo.descricao" /> </span></a></td>
											<td width="10%" align="center"><bean:write name="guiaPagamentoValores" property="guiaPagamento.prestacaoFormatada" /></td>		
											<td width="12%" align="center"><bean:write name="guiaPagamentoValores" property="guiaPagamento.dataEmissao" formatKey="date.format" /></td>
											<td width="13%" align="center"><bean:write name="guiaPagamentoValores" property="guiaPagamento.dataVencimento" formatKey="date.format" /></td>
											<td width="22%" align="right"><bean:write name="guiaPagamentoValores" property="guiaPagamento.valorDebito" formatKey="money.format" /></td>
											<td width="15%" align="right"><bean:write name="guiaPagamentoValores" property="valorAcrescimosImpontualidade" formatKey="money.format" /></td>
										</tr>
									</logic:iterate>
								</table>
								</div>
								</td>
							</tr>
							<%}%>
						</logic:notEmpty>
					</table>
					</td>
				</tr>
				<tr>
					<td colspan="4"><jsp:include
						page="/jsp/util/wizard/navegacao_botoes_wizard_valida_avancar_valida_voltar_tela_espera.jsp?numeroPagina=2" />
					</td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<!-- Fim do Corpo - Roberta Costa --></td>
		</tr>
	</table>

	<%@ include file="/jsp/util/rodape.jsp"%>

</html:form>
</div>
<%@ include file="/jsp/util/telaespera.jsp"%>

</body>
</html:html>
