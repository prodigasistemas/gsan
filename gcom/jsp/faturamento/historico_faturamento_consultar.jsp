<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<%@ page import="gcom.util.Util"%>
<%@ page import="gcom.faturamento.conta.Conta" isELIgnored="false"%>
<%@ page import="gcom.faturamento.conta.ContaHistorico" isELIgnored="false"%>
<%@ page import="gcom.faturamento.debito.DebitoACobrar" isELIgnored="false"%>
<%@ page import="gcom.faturamento.debito.DebitoACobrarHistorico" isELIgnored="false"%>
<%@ page import="gcom.faturamento.credito.CreditoARealizar" isELIgnored="false"%>
<%@ page import="gcom.faturamento.credito.CreditoARealizarHistorico" isELIgnored="false"%>
<%@ page import="gcom.arrecadacao.pagamento.GuiaPagamentoHistorico" isELIgnored="false"%>
<%@ page import="gcom.arrecadacao.pagamento.GuiaPagamento" isELIgnored="false"%>
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
	src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="ConsultarHistoricoFaturamentoActionForm" />

<script language="JavaScript">
<!--

function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {

    var form = document.forms[0];

    if (tipoConsulta == 'imovel') {
      limparForm();      
      form.idImovel.value = codigoRegistro;
      form.action = 'exibirConsultarHistoricoFaturamentoAction.do'
      form.submit();
    }
 }
 
 function limparImovel() {
      window.location.href="exibirConsultarHistoricoFaturamentoAction.do?menu=sim";
}
 function limparForm(){
 	var form = document.forms[0];
 	
 	for (var i=0;i < form.elements.length;i++){
		var elemento = form.elements[i];
		if (elemento.type == "text"){
			elemento.value = "";
		}
	}
	
	redirecionarSubmit("exibirConsultarHistoricoFaturamentoAction.do?limparForm=OK");
 }
 
//-->
</SCRIPT>

</head>

<body leftmargin="5" topmargin="5"
	onload="javascript:setarFoco('idImovel')">

<html:form action="/exibirConsultarHistoricoFaturamentoAction"
	method="post">

	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>

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
					<td class="parabg">Consultar Histórico de Faturamento</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>

			<table width="100%" border="0">
				<tr>
					<td>Para consultar o histórico de faturamento, informe o imóvel:</td>
					<logic:present scope="application" name="urlHelp">
						<td align="right"><a href="javascript:abrirPopupHelp('${applicationScope.urlHelp}faturamentoHistoricoConsultar', 500, 700);"><span style="font-weight: bold"><font color="#3165CE">Ajuda</font></span></a></td>									
					</logic:present>
					<logic:notPresent scope="application" name="urlHelp">
						<td align="right"><span style="font-weight: bold"><font color=#696969><u>Ajuda</u></font></span></td>									
					</logic:notPresent>
				</tr>
			</table>

			<table width="100%" border="0">
				<tr>
					<td height="10" width="30%"><strong>Matrícula do Imóvel:</strong></td>
					<td width="82%" height="24"><html:text property="idImovel"
						maxlength="9" size="9"
						onkeypress="javascript:validaEnterComMensagem(event, 'exibirConsultarHistoricoFaturamentoAction.do', 'idImovel', 'Matrícula do Imóvel');" />
					<a
						href="javascript:abrirPopup('exibirPesquisarImovelAction.do', 400, 800);">
					<img border="0" src="imagens/pesquisa.gif" height="21" width="23"></a>
					<logic:present name="idImovelNaoEncontrado">
						<logic:equal name="idImovelNaoEncontrado" value="exception">
							<html:text property="descricaoImovel" size="25" maxlength="25"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:equal>
						<logic:notEqual name="idImovelNaoEncontrado" value="exception">
							<html:text property="descricaoImovel" size="25" maxlength="25"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEqual>
					</logic:present> <logic:notPresent name="idImovelNaoEncontrado">
						<logic:empty name="ConsultarHistoricoFaturamentoActionForm"
							property="idImovel">
							<html:text property="descricaoImovel" value="" size="25"
								maxlength="25" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:empty>
						<logic:notEmpty name="ConsultarHistoricoFaturamentoActionForm"
							property="idImovel">
							<html:text property="descricaoImovel" size="25" maxlength="25"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEmpty>
					</logic:notPresent> <a href="javascript:limparImovel();"> <img
						border="0" src="imagens/limparcampo.gif" height="21" width="23"> </a>
					</td>
				</tr>

				<tr>
					<td colspan="3">
					<table width="100%" align="center" bgcolor="#99C7FC" border="0">
						<tr>
							<td><strong>Dados do Imóvel</strong></td>
						</tr>
						<tr bgcolor="#cbe5fe">
							<td width="100%" align="center">
							<table width="100%" border="0">
								<tr>
									<td height="10"><strong>Nome do Cliente Usuário:</strong></td>
									<td><html:text property="nomeClienteUsuario" size="45"
										readonly="true" style="background-color:#EFEFEF; border:0" />
									</td>
								</tr>
								<tr>
									<td height="10"><strong>Situação de Água:</strong></td>
									<td><html:text property="situacaoAguaImovel" size="45"
										readonly="true" style="background-color:#EFEFEF; border:0" />
									</td>
								</tr>
								<tr>
									<td height="10"><strong>Situação de Esgoto:</strong></td>
									<td><html:text property="situacaoEsgotoImovel" size="45"
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
					<td colspan="3" height="10"></td>
				</tr>
				<tr>
					<td height="10">&nbsp;</td>
					<td><strong><font color="#FF0000">*</font></strong> Campos
					obrigat&oacute;rios</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td colspan="3" height="5"></td>
				</tr>



				<tr>
					<td colspan="3">
					<table width="100%" border="0">
						<tr>
							<td colspan="4">
							<table width="100%" border="0" bgcolor="#99C7FC">
								<tr bgcolor="#79bbfd">
									<td bgcolor="#79bbfd" height="20" colspan="8"><strong>Histórico das Contas do
									Imóvel</strong></td>
								</tr>
								<%if ((Integer)request.getAttribute("tamanhoColecaoContas") <= ConstantesSistema.NUMERO_MAXIMO_REGISTROS_CONTAS_DEBITO) {%>
								<tr bordercolor="#FFFFFF" bgcolor="#99C7FC">
									<td width="5%" rowspan="2" bordercolor="#000000"
										bgcolor="#90c7fc">
									<div align="center"><font color="#000000" style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"><strong>M&ecirc;s/Ano</strong></font></div>
									</td>

									<td width="15%" rowspan="2" bordercolor="#000000"
										bgcolor="#90c7fc">
									<div align="center"><font color="#000000" style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"><strong>Vencimento</strong></font></div>
									</td>

									<td width="70%" colspan="5" bordercolor="#000000"
										bgcolor="#90c7fc">
									<div align="center"><font color="#000000" style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"><strong>VALORES
									DA CONTA</strong></font></div>
									</td>

									<td width="8%" rowspan="2" bordercolor="#000000"
										bgcolor="#90c7fc">
									<div align="center"><font color="#000000" style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"><strong>Situa&ccedil;&atilde;o</strong></font></div>
									</td>


								</tr>
								<tr bordercolor="#FFFFFF" bgcolor="#cbe5fe">

									<td width="10%" bordercolor="#cbe5fe" bgcolor="#cbe5fe">
									<div align="center"><font color="#000000" style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"><strong>&Aacute;gua</strong></font></div>
									</td>

									<td width="10%" bordercolor="#cbe5fe" bgcolor="#cbe5fe">
									<div align="center"></div>
									<div align="center"><font color="#000000" style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"><strong>Esgoto</strong></font></div>
									</td>
									<td width="20%" bordercolor="#cbe5fe" bgcolor="#cbe5fe">
									<div align="center"><font color="#000000" style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"><strong>D&eacute;bitos</strong></font></div>
									</td>
									<td width="10%" bordercolor="#cbe5fe" bgcolor="#cbe5fe">
									<div align="center"><font color="#000000" style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"><strong>Cr&eacute;ditos</strong></font></div>
									</td>
									<td width="20%" bordercolor="#cbe5fe" bgcolor="#cbe5fe">
									<div align="center"><font color="#000000" style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"><strong>TOTAL</strong></font></div>
									</td>
								</tr>
								<%}else{ %>
								<tr bordercolor="#FFFFFF" bgcolor="#99C7FC">


									<td width="5%" rowspan="2" bordercolor="#000000"
										bgcolor="#90c7fc">
									<div align="center"><font color="#000000" style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"><strong>M&ecirc;s/Ano</strong></font></div>
									</td>

									<td width="14%" rowspan="2" bordercolor="#000000"
										bgcolor="#90c7fc">
									<div align="center"><font color="#000000" style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"><strong>Vencimento</strong></font></div>
									</td>

									<td width="66%" colspan="5" bordercolor="#000000"
										bgcolor="#90c7fc">
									<div align="center"><font color="#000000" style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"><strong>VALORES
									DA CONTA</strong></font></div>
									</td>

									<td width="10%" rowspan="2" bordercolor="#000000"
										bgcolor="#90c7fc">
									<div align="center"><font color="#000000" style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"><strong>Situa&ccedil;&atilde;o</strong></font></div>
									</td>


								</tr>
								<tr bordercolor="#FFFFFF" bgcolor="#cbe5fe">

									<td width="10%" bordercolor="#cbe5fe" bgcolor="#cbe5fe">
									<div align="center"><font color="#000000" style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"><strong>&Aacute;gua</strong></font></div>
									</td>

									<td width="10%" bordercolor="#cbe5fe" bgcolor="#cbe5fe">
									<div align="center"></div>
									<div align="center"><font color="#000000" style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"><strong>Esgoto</strong></font></div>
									</td>
									<td width="19%" bordercolor="#cbe5fe" bgcolor="#cbe5fe">
									<div align="center"><font color="#000000" style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"><strong>D&eacute;bitos</strong></font></div>
									</td>
									<td width="9%" bordercolor="#cbe5fe" bgcolor="#cbe5fe">
									<div align="center"><font color="#000000" style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"><strong>Cr&eacute;ditos</strong></font></div>
									</td>
									<td width="19%" bordercolor="#cbe5fe" bgcolor="#cbe5fe">
									<div align="center"><font color="#000000" style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"><strong>TOTAL</strong></font></div>
									</td>
								</tr>
								<%} %>
							</table>

							<%if ((Integer)request.getAttribute("tamanhoColecaoContas") > ConstantesSistema.NUMERO_MAXIMO_REGISTROS_CONTAS_DEBITO) {%>
							<div style="width: 100%; height: 100; overflow: auto;">
							<%}%>
							<table width="100%" bgcolor="#99C7FC">

								<!-- Esquema pra exibir as contas =========================== -->
								<logic:present name="colecaoContaImovel">

									<%int cont = 0;%>
									<logic:iterate name="colecaoContaImovel" id="conta"
										type="Conta">

										<%cont = cont + 1;
							if (cont % 2 == 0) {%>
										<tr bgcolor="#cbe5fe">
											<%} else {

							%>
										<tr bgcolor="#FFFFFF">
											<%}%>
											<td width="5%"><font color="#000000" style="font-size:9px"
												face="Verdana, Arial, Helvetica, sans-serif"> <a
												href="javascript:abrirPopup('exibirConsultarContaAction.do?tipoConsulta=conta&contaID=<%="" + conta.getId()%>' , 600, 800);"
												title="<%="" + Util.formatarMesAnoReferencia(conta.getReferencia())%>"><span
												style="color: #000000;"><%=""
									+ Util.formatarMesAnoReferencia(conta
											.getReferencia())%></span></a>
											</font></td>

											<td width="15%">
											<div align="center"><logic:present name="conta"
												property="dataVencimentoConta">
												<font color="#000000" style="font-size:9px"
													face="Verdana, Arial, Helvetica, sans-serif"> <span
													style="color: #000000;"><%=""
										+ Util.formatarData(conta
												.getDataVencimentoConta())%></span>
												</font>
											</logic:present> <logic:notPresent name="conta"
												property="dataVencimentoConta">
												<font color="#000000" style="font-size:9px"
													face="Verdana, Arial, Helvetica, sans-serif"> &nbsp; </font>
											</logic:notPresent></div>
											</td>

											<td width="10%">
											<div align="right"><font color="#000000"
												style="font-size:9px"
												face="Verdana, Arial, Helvetica, sans-serif"> <bean:write
												name="conta" property="valorAgua" formatKey="money.format" />
											</font></div>
											</td>

											<td width="10%">
											<div align="right"><font color="#000000"
												style="font-size:9px"
												face="Verdana, Arial, Helvetica, sans-serif"> <bean:write
												name="conta" property="valorEsgoto" formatKey="money.format" />
											</font></div>
											</td>

											<td width="20%">
											<div align="right"><logic:equal name="conta"
												property="debitos" value="0">
												<font color="#000000" style="font-size:9px"
													face="Verdana, Arial, Helvetica, sans-serif"> <bean:write
													name="conta" property="debitos" formatKey="money.format" />
												</font>
											</logic:equal> <logic:notEqual name="conta"
												property="debitos" value="0">
												<a
													href="javascript:abrirPopup('exibirConsultarDebitoCobradoAction.do?tipoConsulta=conta&contaID=<%="" + conta.getId()%>' , 500, 800);"
													title="<%="" + Util.formatarMesAnoReferencia(conta.getReferencia())%>">
												<font color="#000000" style="font-size:9px"
													face="Verdana, Arial, Helvetica, sans-serif"> <bean:write
													name="conta" property="debitos" formatKey="money.format" />
												</font> </a>
											</logic:notEqual></div>
											</td>

											<td width="10%">
											<div align="right"><logic:present name="conta"
												property="valorCreditos">
												<logic:equal name="conta" property="valorCreditos" value="0">
													<font color="#000000" style="font-size:9px"
														face="Verdana, Arial, Helvetica, sans-serif"> <bean:write
														name="conta" property="valorCreditos"
														formatKey="money.format" /> </font>
												</logic:equal>
												<logic:notEqual name="conta" property="valorCreditos"
													value="0">
													<a
														href="javascript:abrirPopup('exibirConsultarCreditoRealizadoAction.do?tipoConsulta=conta&contaID=<%="" + conta.getId()%>' , 500, 800);"
														title="<%="" + Util.formatarMesAnoReferencia(conta.getReferencia())%>">
													<font color="#000000" style="font-size:9px"
														face="Verdana, Arial, Helvetica, sans-serif"> <bean:write
														name="conta" property="valorCreditos"
														formatKey="money.format" /> </font> </a>
												</logic:notEqual>
											</logic:present> <logic:notPresent name="conta"
												property="valorCreditos">
												<font color="#000000" style="font-size:9px"
													face="Verdana, Arial, Helvetica, sans-serif"> &nbsp; </font>
											</logic:notPresent></div>
											</td>

											<td width="20%">
											<div align="right"><font color="#000000"
												style="font-size:9px"
												face="Verdana, Arial, Helvetica, sans-serif"> <span
												style="color: #000000;"><%=""
									+ Util.formatarMoedaReal(conta
											.getValorTotal())%></span>
											</font></div>
											</td>

											<td width="8%">
											<div align="left"><logic:present name="conta"
												property="debitoCreditoSituacaoAtual">
												<font color="#000000" style="font-size:9px"
													face="Verdana, Arial, Helvetica, sans-serif"> <bean:write
													name="conta"
													property="debitoCreditoSituacaoAtual.descricaoAbreviada" />
												</font>
											</logic:present> <logic:notPresent name="conta"
												property="debitoCreditoSituacaoAtual">
												<font color="#000000" style="font-size:9px"
													face="Verdana, Arial, Helvetica, sans-serif"> &nbsp; </font>
											</logic:notPresent></div>
											</td>

										</tr>
									</logic:iterate>
								</logic:present>
								<!-- Fim do Esquema pra exibir as contas =========================== -->



								<!-- Esquema pra exibir as contas do histórico =========================== -->
								<logic:present name="colecaoContaHistoricoImovel">

									<tr>
										<td colspan="8">
											<div align="center">
												<font color="#000000" 
													style="font-size:9px"
													face="Verdana, Arial, Helvetica, sans-serif">
												<strong>-----------------Histórico-----------------</strong>
												</font>
											</div>
										</td>
									</tr>

									<%int cont1 = 0;%>
									<logic:iterate name="colecaoContaHistoricoImovel"
										id="contaHistorico" type="ContaHistorico">

										<%cont1 = cont1 + 1;
							if (cont1 % 2 == 0) {%>
										<tr bgcolor="#cbe5fe">
											<%} else {

							%>
										<tr bgcolor="#FFFFFF">
											<%}%>
											<td width="5%"><font color="#000000" style="font-size:9px"
												face="Verdana, Arial, Helvetica, sans-serif"> <a
												href="javascript:abrirPopup('exibirConsultarContaAction.do?tipoConsulta=contaHistorico&contaID=<%="" + contaHistorico.getId()%>' , 600, 800);"
												title="<%="" + Util.formatarMesAnoReferencia(contaHistorico.getAnoMesReferenciaConta())%>"><span
												style="color: #000000;"><%=""
											+ Util
													.formatarMesAnoReferencia(contaHistorico
															.getAnoMesReferenciaConta())%></span></a>
											</font></td>

											<td width="15%">
											<div align="center"><logic:present name="contaHistorico"
												property="dataVencimentoConta">
												<font color="#000000" style="font-size:9px"
													face="Verdana, Arial, Helvetica, sans-serif"> <span
													style="color: #000000;"><%=""
										+ Util.formatarData(contaHistorico
												.getDataVencimentoConta())%></span>
												</font>
											</logic:present> <logic:notPresent name="contaHistorico"
												property="dataVencimentoConta">
												<font color="#000000" style="font-size:9px"
													face="Verdana, Arial, Helvetica, sans-serif"> &nbsp; </font>
											</logic:notPresent></div>
											</td>

											<td width="10%">
											<div align="right"><font color="#000000"
												style="font-size:9px"
												face="Verdana, Arial, Helvetica, sans-serif"> <bean:write
												name="contaHistorico" property="valorAgua"
												formatKey="money.format" /> </font></div>
											</td>

											<td width="10%">
											<div align="right"><font color="#000000"
												style="font-size:9px"
												face="Verdana, Arial, Helvetica, sans-serif"> <bean:write
												name="contaHistorico" property="valorEsgoto"
												formatKey="money.format" /> </font></div>
											</td>

											<td width="20%">
											<div align="right"><logic:equal name="contaHistorico"
												property="valorDebitos" value="0">
												<font color="#000000" style="font-size:9px"
													face="Verdana, Arial, Helvetica, sans-serif"> <bean:write
													name="contaHistorico" property="valorDebitos"
													formatKey="money.format" /> </font>
											</logic:equal> <logic:notEqual name="contaHistorico"
												property="valorDebitos" value="0">
												<a
													href="javascript:abrirPopup('exibirConsultarDebitoCobradoAction.do?tipoConsulta=contaHistorico&contaID=<%="" + contaHistorico.getId()%>' , 500, 800);"
													title="<%="" + Util.formatarMesAnoReferencia(contaHistorico.getAnoMesReferenciaConta())%>">
												<font color="#000000" style="font-size:9px"
													face="Verdana, Arial, Helvetica, sans-serif"> <bean:write
													name="contaHistorico" property="valorDebitos"
													formatKey="money.format" /> </font> </a>
											</logic:notEqual></div>
											</td>

											<td width="10%">
											<div align="right"><logic:present name="contaHistorico"
												property="valorCreditos">
												<logic:equal name="contaHistorico" property="valorCreditos"
													value="0">
													<font color="#000000" style="font-size:9px"
														face="Verdana, Arial, Helvetica, sans-serif"> <bean:write
														name="contaHistorico" property="valorCreditos"
														formatKey="money.format" /> </font>
												</logic:equal>
												<logic:notEqual name="contaHistorico"
													property="valorCreditos" value="0">
													<a
														href="javascript:abrirPopup('exibirConsultarCreditoRealizadoAction.do?tipoConsulta=contaHistorico&contaID=<%="" + contaHistorico.getId()%>' , 500, 800);"
														title="<%="" + Util.formatarMesAnoReferencia(contaHistorico.getAnoMesReferenciaConta())%>">
													<font color="#000000" style="font-size:9px"
														face="Verdana, Arial, Helvetica, sans-serif"> <bean:write
														name="contaHistorico" property="valorCreditos"
														formatKey="money.format" /> </font> </a>
												</logic:notEqual>
											</logic:present> <logic:notPresent name="contaHistorico"
												property="valorCreditos">
												<font color="#000000" style="font-size:9px"
													face="Verdana, Arial, Helvetica, sans-serif"> &nbsp; </font>
											</logic:notPresent></div>
											</td>

											<td width="20%">
											<div align="right"><font color="#000000"
												style="font-size:9px"
												face="Verdana, Arial, Helvetica, sans-serif"> <span
												style="color: #000000;"><%=""
									+ Util.formatarMoedaReal(contaHistorico
											.getValorTotal())%></span>
											</font></div>
											</td>

											<td width="8%">
											<div align="left"><logic:present name="contaHistorico"
												property="debitoCreditoSituacaoAtual">
												<font color="#000000" style="font-size:9px"
													face="Verdana, Arial, Helvetica, sans-serif"> <bean:write
													name="contaHistorico"
													property="debitoCreditoSituacaoAtual.descricaoAbreviada" />
												</font>
											</logic:present> <logic:notPresent name="contaHistorico"
												property="debitoCreditoSituacaoAtual">
												<font color="#000000" style="font-size:9px"
													face="Verdana, Arial, Helvetica, sans-serif"> &nbsp; </font>
											</logic:notPresent></div>
											</td>

										</tr>
									</logic:iterate>
								</logic:present>
								<!-- Fim do Esquema pra exibir as contas do histórico =========================== -->

							</table>
							<%if ((Integer)request.getAttribute("tamanhoColecaoContas") > ConstantesSistema.NUMERO_MAXIMO_REGISTROS_CONTAS_DEBITO) {%>
							</div>
							<%}%>
							</td>
						</tr>
						
						
						
						
						
						<tr>
							<td colspan="3">&nbsp;</td>
						</tr>
								
						
						
						
						
						
						
						
						<!-- inicio da tabela Debito A Cobrar -->
						
						<tr>
							<td colspan="4">
							<table width="100%" border="0" bgcolor="#99C7FC">
								<tr bgcolor="#79bbfd">
									<td bgcolor="#79bbfd" height="20" colspan="8">
									<strong>Histórico dos Débitos A Cobrar do Imóvel</strong></td>
								</tr>
								
								<%if ((Integer)request.getAttribute("tamanhoColecaoDebitos") <= ConstantesSistema.NUMERO_MAXIMO_REGISTROS_CONTAS_DEBITO) {%>
								
								<tr bordercolor="#FFFFFF" bgcolor="#99C7FC">
									<td width="30%" rowspan="2" bordercolor="#000000"
										bgcolor="#90c7fc">
									<div align="center"><font color="#000000" style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"><strong>Tipo do Débito</strong></font></div>
									</td>

									<td width="10%" rowspan="2" bordercolor="#000000"
										bgcolor="#90c7fc">
									<div align="center"><font color="#000000" style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"><strong>Mês/Ano Referência</strong></font></div>
									</td>

									<td width="10%" rowspan="2" bordercolor="#000000"
										bgcolor="#90c7fc">
									<div align="center"><font color="#000000" style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"><strong>Mês/Ano Cobrança</strong></font></div>
									</td>
									
									<td width="27%" colspan="3" bordercolor="#000000"
										bgcolor="#90c7fc">
									<div align="center"><font color="#000000" style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"><strong>Parcelas </strong></font></div>
									</td>
									
									
									<td width="15%" rowspan="2" bordercolor="#000000"
										bgcolor="#90c7fc">
									<div align="center"><font color="#000000" style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"><strong>Valor Débito</strong></font></div>
									</td>

									<td width="8%" rowspan="2" bordercolor="#000000"
										bgcolor="#90c7fc">
									<div align="center"><font color="#000000" style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"><strong>Situa&ccedil;&atilde;o</strong></font></div>
									</td>



								</tr>
								<tr bordercolor="#FFFFFF" bgcolor="#cbe5fe">

									<td width="9%" bordercolor="#cbe5fe" bgcolor="#cbe5fe">
									<div align="center"><font color="#000000" style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"><strong>Cobradas</strong></font></div>
									</td>

									<td width="9%" bordercolor="#cbe5fe" bgcolor="#cbe5fe">
									<div align="center"></div>
									<div align="center"><font color="#000000" style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"><strong>Total</strong></font></div>
									</td>
									<td width="9%" bordercolor="#cbe5fe" bgcolor="#cbe5fe">
									<div align="center"><font color="#000000" style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"><strong>Bônus</strong></font></div>
									</td>
								</tr>
								<%}else{ %>
								<tr bordercolor="#FFFFFF" bgcolor="#99C7FC">


									<td width="29%" rowspan="2" bordercolor="#000000"
										bgcolor="#90c7fc">
									<div align="center"><font color="#000000" style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"><strong>Tipo do Débito</strong></font></div>
									</td>

									<td width="10%" rowspan="2" bordercolor="#000000"
										bgcolor="#90c7fc">
									<div align="center"><font color="#000000" style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"><strong>Mês/Ano Referência</strong></font></div>
									</td>

									<td width="10%" rowspan="2" bordercolor="#000000"
										bgcolor="#90c7fc">
									<div align="center"><font color="#000000" style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"><strong>Mês/Ano Cobrança</strong></font></div>
									</td>
									
									<td width="26%" colspan="3" bordercolor="#000000"
										bgcolor="#90c7fc">
									<div align="center"><font color="#000000" style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"><strong>Parcelas </strong></font></div>
									</td>
									
									
									<td width="15%" rowspan="2" bordercolor="#000000"
										bgcolor="#90c7fc">
									<div align="center"><font color="#000000" style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"><strong>Valor Débito</strong></font></div>
									</td>

									<td width="10%" rowspan="2" bordercolor="#000000"
										bgcolor="#90c7fc">
									<div align="center"><font color="#000000" style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"><strong>Situa&ccedil;&atilde;o</strong></font></div>
									</td>

								</tr>
								<tr bordercolor="#FFFFFF" bgcolor="#cbe5fe">

									<td width="9%" bordercolor="#cbe5fe" bgcolor="#cbe5fe">
									<div align="center"><font color="#000000" style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"><strong>Cobradas</strong></font></div>
									</td>

									<td width="8%" bordercolor="#cbe5fe" bgcolor="#cbe5fe">
									<div align="center"></div>
									<div align="center"><font color="#000000" style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"><strong>Total</strong></font></div>
									</td>
									<td width="9%" bordercolor="#cbe5fe" bgcolor="#cbe5fe">
									<div align="center"><font color="#000000" style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"><strong>Bônus</strong></font></div>
									</td>
								</tr>
								<%} %>
							</table>

							<%if ((Integer)request.getAttribute("tamanhoColecaoDebitos") > ConstantesSistema.NUMERO_MAXIMO_REGISTROS_CONTAS_DEBITO) {%>
							<div style="width: 100%; height: 65; overflow: auto;">
							<%} %>
							<table width="100%" bgcolor="#99C7FC">

								<!-- Esquema pra exibir os debitos =========================== -->
								<logic:present name="colecaoDebitoACobrarImovel">

									<%int cont2 = 0;%>
									<logic:iterate name="colecaoDebitoACobrarImovel" id="debitoacobrar"	type="DebitoACobrar">

										<%cont2 = cont2 + 1;
									if (cont2 % 2 == 0) {%>
										<tr bgcolor="#cbe5fe">
											<%} else {

									%>
										<tr bgcolor="#FFFFFF">
											<%}%>
											<td width="30%"><font color="#000000" style="font-size:9px"
												face="Verdana, Arial, Helvetica, sans-serif"> <a
											href="javascript:abrirPopup('exibirConsultarDebitoACobrarAction.do?imovelID=<bean:define name="debitoacobrar" property="imovel" id="imovel" /><bean:write name="imovel" property="id" />&debitoID=<bean:write name="debitoacobrar" property="id" />', 570, 720);">
											<bean:define name="debitoacobrar" property="debitoTipo"	id="debitoTipo" /> 
											<logic:notEmpty name="debitoTipo" property="descricao">
												<bean:write name="debitoTipo" property="descricao" />
											</logic:notEmpty> </a>
											</font></td>

											<td width="10%">
											<div align="center">
												<logic:present name="debitoacobrar" property="anoMesReferenciaDebito">
													<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
														<span style="color: #000000;"><%=""	+ Util.formatarAnoMesParaMesAno(debitoacobrar.getAnoMesReferenciaDebito())%></span>
													</font>
												</logic:present> 
												<logic:notPresent name="debitoacobrar" property="anoMesReferenciaDebito">
													<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> &nbsp; </font>
												</logic:notPresent>
											</div>
											</td>
											
											<td width="10%">
											<div align="center">
												<logic:present name="debitoacobrar" property="anoMesCobrancaDebito">
													<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
														<span style="color: #000000;"><%=""	+ Util.formatarAnoMesParaMesAno(debitoacobrar.getAnoMesCobrancaDebito())%></span>
													</font>
												</logic:present> 
												<logic:notPresent name="debitoacobrar" property="anoMesCobrancaDebito">
													<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> &nbsp; </font>
												</logic:notPresent>
											</div>
											</td>

											<td width="9%">
											<div align="center">
												<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
													<bean:write	name="debitoacobrar" property="numeroPrestacaoCobradas" />
												</font>
											</div>
											</td>

											<td width="9%">
											<div align="center">
												<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
													<bean:write	name="debitoacobrar" property="numeroPrestacaoDebito" />
												</font>
											</div>
											</td>
											
											<td width="9%">
											<div align="center">
												<logic:present name="debitoacobrar" property="numeroParcelaBonus">
													<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
														<bean:write	name="debitoacobrar" property="numeroParcelaBonus" />
													</font>
												</logic:present> 
												<logic:notPresent name="debitoacobrar" property="numeroParcelaBonus">
													<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> &nbsp; </font>
												</logic:notPresent>
											</div>
											</td>

											<td width="15%">
											<div align="right">
												<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
													<bean:write	name="debitoacobrar" property="valorDebito" formatKey="money.format" />
												</font>
											</div>
											</td>

											<td width="8%">
											<div align="left">
												<logic:present name="debitoacobrar"	property="debitoCreditoSituacaoAtual">
													<font color="#000000" style="font-size:9px"	face="Verdana, Arial, Helvetica, sans-serif"> 
														<bean:write name="debitoacobrar" property="debitoCreditoSituacaoAtual.descricaoAbreviada" />
													</font>
												</logic:present> 
												<logic:notPresent name="debitoacobrar" property="debitoCreditoSituacaoAtual">
													<font color="#000000" style="font-size:9px"	face="Verdana, Arial, Helvetica, sans-serif"> &nbsp; </font>
												</logic:notPresent>
											</div>
											</td>

										</tr>
									</logic:iterate>
								</logic:present>
								<!-- Fim do Esquema pra exibir os debitos =========================== -->



								<!-- Esquema pra exibir os debitos do histórico =========================== -->
								<logic:present name="colecaoDebitoACobrarHistoricoImovel">

									<%int cont3 = 0;%>
									<logic:iterate name="colecaoDebitoACobrarHistoricoImovel" id="debitoacobrarhistorico" type="DebitoACobrarHistorico">

										<%cont3 = cont3 + 1;
							if (cont3 % 2 == 0) {%>
										<tr bgcolor="#cbe5fe">
											<%} else {

							%>
										<tr bgcolor="#FFFFFF">
											<%}%>
											<td width="30%"><font color="#000000" style="font-size:9px"
												face="Verdana, Arial, Helvetica, sans-serif"> <a
											href="javascript:abrirPopup('exibirConsultarDebitoACobrarAction.do?imovelID=<bean:define name="debitoacobrarhistorico" property="imovel" id="imovel" /><bean:write name="imovel" property="id" />&debitoHistoricoID=<bean:write name="debitoacobrarhistorico" property="id" />', 570, 720);">
											<bean:define name="debitoacobrarhistorico" property="debitoTipo" id="debitoTipo" /> 
											<logic:notEmpty name="debitoTipo" property="descricao">
												<bean:write name="debitoTipo" property="descricao" />
											</logic:notEmpty> </a>
											</font></td>

											<td width="10%">
											<div align="center">
												<logic:present name="debitoacobrarhistorico" property="anoMesReferenciaDebito">
													<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
														<span style="color: #000000;"><%=""	+ Util.formatarAnoMesParaMesAno(debitoacobrarhistorico.getAnoMesReferenciaDebito())%></span>
													</font>
												</logic:present> 
												<logic:notPresent name="debitoacobrarhistorico" property="anoMesReferenciaDebito">
													<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> &nbsp; </font>
												</logic:notPresent>
											</div>
											</td>
											
											<td width="10%">
											<div align="center">
												<logic:present name="debitoacobrarhistorico" property="anoMesCobrancaDebito">
													<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
														<span style="color: #000000;"><%=""	+ Util.formatarAnoMesParaMesAno(debitoacobrarhistorico.getAnoMesCobrancaDebito())%></span>
													</font>
												</logic:present> 
												<logic:notPresent name="debitoacobrarhistorico" property="anoMesCobrancaDebito">
													<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> &nbsp; </font>
												</logic:notPresent>
											</div>
											</td>

											<td width="9%">
											<div align="center">
												<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
													<bean:write	name="debitoacobrarhistorico" property="prestacaoCobradas" />
												</font>
											</div>
											</td>

											<td width="9%">
											<div align="center">
												<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
													<bean:write	name="debitoacobrarhistorico" property="prestacaoDebito" />
												</font>
											</div>
											</td>
											
											<td width="9%">
											<div align="center">
												<logic:present name="debitoacobrarhistorico" property="numeroParcelaBonus">
													<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
														<bean:write	name="debitoacobrarhistorico" property="numeroParcelaBonus" />
													</font>
												</logic:present> 
												<logic:notPresent name="debitoacobrarhistorico" property="numeroParcelaBonus">
													<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> &nbsp; </font>
												</logic:notPresent>
											</div>
											</td>

											<td width="15%">
											<div align="right">
												<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
													<bean:write	name="debitoacobrarhistorico" property="valorDebito" formatKey="money.format" />
												</font>
											</div>
											</td>

											<td width="8%">
											<div align="left">
											<bean:define name="debitoacobrarhistorico" property="debitoCreditoSituacaoAtual" id="debitoCreditoSituacaoAtual"/>
												<logic:present name="debitoCreditoSituacaoAtual" property="descricaoAbreviada">
											
													<font color="#000000" style="font-size:9px"	face="Verdana, Arial, Helvetica, sans-serif"> 
														<bean:write name="debitoacobrarhistorico" property="debitoCreditoSituacaoAtual.descricaoAbreviada" />
													</font>
												</logic:present> 
												<logic:notPresent name="debitoCreditoSituacaoAtual" property="descricaoAbreviada">
													<font color="#000000" style="font-size:9px"	face="Verdana, Arial, Helvetica, sans-serif"> &nbsp; </font>
												</logic:notPresent>
											</div>
											</td>

										</tr>
									</logic:iterate>
								</logic:present>
								<!-- Fim do Esquema pra exibir os debitos do histórico =========================== -->

							</table>
							<%if ((Integer)request.getAttribute("tamanhoColecaoDebitos") > ConstantesSistema.NUMERO_MAXIMO_REGISTROS_CONTAS_DEBITO) {%>
							</div>
							<%} %>
							</td>
						</tr>
						
						
						<!-- Fim da tabela Debito A Cobrar -->
						
						
						
						<tr>
						<td colspan="3">&nbsp;</td>
						</tr>
					
					
					
						<!-- inicio da tabela Credito A Realizar -->
						
						<tr>
							<td colspan="4">
							<table width="100%" border="0" bgcolor="#99C7FC">
								<tr bgcolor="#79bbfd">
									<td bgcolor="#79bbfd" height="20" colspan="8">
									<strong>Histórico dos Créditos A Realizar do Imóvel</strong></td>
								</tr>
								
								<%if ((Integer)request.getAttribute("tamanhoColecaoCreditos") <= ConstantesSistema.NUMERO_MAXIMO_REGISTROS_CONTAS_DEBITO) {%>
									 
								<tr bordercolor="#FFFFFF" bgcolor="#99C7FC">
									<td width="30%" rowspan="2" bordercolor="#000000"
										bgcolor="#90c7fc">
									<div align="center"><font color="#000000" style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"><strong>Tipo do Crédito</strong></font></div>
									</td>

									<td width="10%" rowspan="2" bordercolor="#000000"
										bgcolor="#90c7fc">
									<div align="center"><font color="#000000" style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"><strong>Mês/Ano Referência</strong></font></div>
									</td>

									<td width="10%" rowspan="2" bordercolor="#000000"
										bgcolor="#90c7fc">
									<div align="center"><font color="#000000" style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"><strong>Mês/Ano Cobrança</strong></font></div>
									</td>
									
									<td width="27%" colspan="3" bordercolor="#000000"
										bgcolor="#90c7fc">
									<div align="center"><font color="#000000" style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"><strong>Parcelas </strong></font></div>
									</td>
									
									
									<td width="15%" rowspan="2" bordercolor="#000000"
										bgcolor="#90c7fc">
									<div align="center"><font color="#000000" style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"><strong>Valor Crédito</strong></font></div>
									</td>

									<td width="8%" rowspan="2" bordercolor="#000000"
										bgcolor="#90c7fc">
									<div align="center"><font color="#000000" style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"><strong>Situa&ccedil;&atilde;o</strong></font></div>
									</td>



								</tr>
								<tr bordercolor="#FFFFFF" bgcolor="#cbe5fe">

									<td width="9%" bordercolor="#cbe5fe" bgcolor="#cbe5fe">
									<div align="center"><font color="#000000" style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"><strong>Cobradas</strong></font></div>
									</td>

									<td width="9%" bordercolor="#cbe5fe" bgcolor="#cbe5fe">
									<div align="center"></div>
									<div align="center"><font color="#000000" style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"><strong>Total</strong></font></div>
									</td>
									<td width="9%" bordercolor="#cbe5fe" bgcolor="#cbe5fe">
									<div align="center"><font color="#000000" style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"><strong>Bônus</strong></font></div>
									</td>
								</tr>
								<%}else{ %>
								<tr bordercolor="#FFFFFF" bgcolor="#99C7FC">


									<td width="29%" rowspan="2" bordercolor="#000000"
										bgcolor="#90c7fc">
									<div align="center"><font color="#000000" style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"><strong>Tipo do Crédito</strong></font></div>
									</td>

									<td width="10%" rowspan="2" bordercolor="#000000"
										bgcolor="#90c7fc">
									<div align="center"><font color="#000000" style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"><strong>Mês/Ano Referência</strong></font></div>
									</td>

									<td width="10%" rowspan="2" bordercolor="#000000"
										bgcolor="#90c7fc">
									<div align="center"><font color="#000000" style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"><strong>Mês/Ano Cobrança</strong></font></div>
									</td>
									
									<td width="26%" colspan="3" bordercolor="#000000"
										bgcolor="#90c7fc">
									<div align="center"><font color="#000000" style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"><strong>Parcelas </strong></font></div>
									</td>
									
									
									<td width="15%" rowspan="2" bordercolor="#000000"
										bgcolor="#90c7fc">
									<div align="center"><font color="#000000" style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"><strong>Valor Crédito</strong></font></div>
									</td>

									<td width="10%" rowspan="2" bordercolor="#000000"
										bgcolor="#90c7fc">
									<div align="center"><font color="#000000" style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"><strong>Situa&ccedil;&atilde;o</strong></font></div>
									</td>

								</tr>
								<tr bordercolor="#FFFFFF" bgcolor="#cbe5fe">

									<td width="9%" bordercolor="#cbe5fe" bgcolor="#cbe5fe">
									<div align="center"><font color="#000000" style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"><strong>Cobradas</strong></font></div>
									</td>

									<td width="8%" bordercolor="#cbe5fe" bgcolor="#cbe5fe">
									<div align="center"></div>
									<div align="center"><font color="#000000" style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"><strong>Total</strong></font></div>
									</td>
									<td width="9%" bordercolor="#cbe5fe" bgcolor="#cbe5fe">
									<div align="center"><font color="#000000" style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"><strong>Bônus</strong></font></div>
									</td>
								</tr>
								<%} %>
							</table>

							<%if ((Integer)request.getAttribute("tamanhoColecaoCreditos") > ConstantesSistema.NUMERO_MAXIMO_REGISTROS_CONTAS_DEBITO) {%>
							<div style="width: 100%; height: 65; overflow: auto;">
							<%}%>
							<table width="100%" bgcolor="#99C7FC">

								<!-- Esquema pra exibir os creditos =========================== -->
								<logic:present name="colecaoCreditoARealizarImovel">

									<%int cont5 = 0;%>
									<logic:iterate name="colecaoCreditoARealizarImovel" id="creditoarealizar" type="CreditoARealizar">

										<%cont5 = cont5 + 1;
									if (cont5 % 2 == 0) {%>
										<tr bgcolor="#cbe5fe">
											<%} else {

									%>
										<tr bgcolor="#FFFFFF">
											<%}%>
											<td width="30%"><font color="#000000" style="font-size:9px"
												face="Verdana, Arial, Helvetica, sans-serif"> <a
											href="javascript:abrirPopup('exibirConsultarCreditoARealizarAction.do?imovelID=<bean:define name="creditoarealizar" property="imovel" id="imovel" /><bean:write name="imovel" property="id" />&creditoID=<bean:write name="creditoarealizar" property="id" />', 570, 720);">
										<bean:define name="creditoarealizar" property="creditoTipo"
											id="creditoTipo" /> <logic:notEmpty name="creditoTipo"
											property="descricao">
											<bean:write name="creditoTipo" property="descricao" />
										</logic:notEmpty> </a>
											</font></td>

											<td width="10%">
											<div align="center">
												<logic:present name="creditoarealizar" property="anoMesReferenciaCredito">
													<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
														<span style="color: #000000;"><%=""	+ Util.formatarAnoMesParaMesAno(creditoarealizar.getAnoMesReferenciaCredito())%></span>
													</font>
												</logic:present> 
												<logic:notPresent name="creditoarealizar" property="anoMesReferenciaCredito">
													<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> &nbsp; </font>
												</logic:notPresent>
											</div>
											</td>
											
											<td width="10%">
											<div align="center">
												<logic:present name="creditoarealizar" property="anoMesCobrancaCredito">
													<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
														<span style="color: #000000;"><%=""	+ Util.formatarAnoMesParaMesAno(creditoarealizar.getAnoMesCobrancaCredito())%></span>
													</font>
												</logic:present> 
												<logic:notPresent name="creditoarealizar" property="anoMesCobrancaCredito">
													<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> &nbsp; </font>
												</logic:notPresent>
											</div>
											</td>

											<td width="9%">
											<div align="center">
												<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
													<bean:write	name="creditoarealizar" property="numeroPrestacaoRealizada" />
												</font>
											</div>
											</td>

											<td width="9%">
											<div align="center">
												<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
													<bean:write	name="creditoarealizar" property="numeroPrestacaoCredito" />
												</font>
											</div>
											</td>
											
											<td width="9%">
											<div align="center">
												<logic:present name="creditoarealizar" property="numeroParcelaBonus">
													<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
														<bean:write	name="creditoarealizar" property="numeroParcelaBonus" />
													</font>
												</logic:present> 
												<logic:notPresent name="creditoarealizar" property="numeroParcelaBonus">
													<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> &nbsp; </font>
												</logic:notPresent>
											</div>
											</td>

											<td width="15%">
											<div align="right">
												<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
													<bean:write	name="creditoarealizar" property="valorCredito" formatKey="money.format" />
												</font>
											</div>
											</td>

											<td width="8%">
											<div align="left">
												<logic:present name="creditoarealizar"	property="debitoCreditoSituacaoAtual">
													<font color="#000000" style="font-size:9px"	face="Verdana, Arial, Helvetica, sans-serif"> 
														<bean:write name="creditoarealizar" property="debitoCreditoSituacaoAtual.descricaoAbreviada" />
													</font>
												</logic:present> 
												<logic:notPresent name="creditoarealizar" property="debitoCreditoSituacaoAtual">
													<font color="#000000" style="font-size:9px"	face="Verdana, Arial, Helvetica, sans-serif"> &nbsp; </font>
												</logic:notPresent>
											</div>
											</td>

										</tr>
									</logic:iterate>
								</logic:present>
								<!-- Fim do Esquema pra exibir os creditos =========================== -->



								<!-- Esquema pra exibir os creditos do histórico =========================== -->
								<logic:present name="colecaoCreditoARealizarHistoricoImovel">

									<%int cont6 = 0;%>
									<logic:iterate name="colecaoCreditoARealizarHistoricoImovel" id="creditoarealizarhistorico" type="CreditoARealizarHistorico">

										<%cont6 = cont6 + 1;
							if (cont6 % 2 == 0) {%>
										<tr bgcolor="#cbe5fe">
											<%} else {

							%>
										<tr bgcolor="#FFFFFF">
											<%}%>
											<td width="30%"><font color="#000000" style="font-size:9px"
												face="Verdana, Arial, Helvetica, sans-serif"> <a
											href="javascript:abrirPopup('exibirConsultarCreditoARealizarAction.do?imovelID=<bean:define name="creditoarealizarhistorico" property="imovel" id="imovel" /><bean:write name="imovel" property="id" />&creditoHistoricoID=<bean:write name="creditoarealizarhistorico" property="id" />', 570, 720);">
										<bean:define name="creditoarealizarhistorico" property="creditoTipo"
											id="creditoTipo" /> <logic:notEmpty name="creditoTipo"
											property="descricao">
											<bean:write name="creditoTipo" property="descricao" />
										</logic:notEmpty> </a>
											</font></td>

											<td width="10%">
											<div align="center">
												<logic:present name="creditoarealizarhistorico" property="anoMesReferenciaCredito">
													<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
														<span style="color: #000000;"><%=""	+ Util.formatarAnoMesParaMesAno(creditoarealizarhistorico.getAnoMesReferenciaCredito())%></span>
													</font>
												</logic:present> 
												<logic:notPresent name="creditoarealizarhistorico" property="anoMesReferenciaCredito">
													<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> &nbsp; </font>
												</logic:notPresent>
											</div>
											</td>
											
											<td width="10%">
											<div align="center">
												<logic:present name="creditoarealizarhistorico" property="anoMesCobrancaCredito">
													<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
														<span style="color: #000000;"><%=""	+ Util.formatarAnoMesParaMesAno(creditoarealizarhistorico.getAnoMesCobrancaCredito())%></span>
													</font>
												</logic:present> 
												<logic:notPresent name="creditoarealizarhistorico" property="anoMesCobrancaCredito">
													<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> &nbsp; </font>
												</logic:notPresent>
											</div>
											</td>

											<td width="9%">
											<div align="center">
												<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
													<bean:write	name="creditoarealizarhistorico" property="prestacaoRealizadas" />
												</font>
											</div>
											</td>

											<td width="9%">
											<div align="center">
												<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
													<bean:write	name="creditoarealizarhistorico" property="prestacaoCredito" />
												</font>
											</div>
											</td>
											
											<td width="9%">
											<div align="center">
												<logic:present name="creditoarealizarhistorico" property="numeroParcelaBonus">
													<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
														<bean:write	name="creditoarealizarhistorico" property="numeroParcelaBonus" />
													</font>
												</logic:present> 
												<logic:notPresent name="creditoarealizarhistorico" property="numeroParcelaBonus">
													<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> &nbsp; </font>
												</logic:notPresent>
											</div>
											</td>

											<td width="15%">
											<div align="right">
												<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
													<bean:write	name="creditoarealizarhistorico" property="valorCredito" formatKey="money.format" />
												</font>
											</div>
											</td>

											<td width="8%">
											<div align="left">
												<logic:present name="creditoarealizarhistorico"	property="debitoCreditoSituacaoAtual">
													<font color="#000000" style="font-size:9px"	face="Verdana, Arial, Helvetica, sans-serif"> 
														<bean:write name="creditoarealizarhistorico" property="debitoCreditoSituacaoAtual.descricaoAbreviada" />
													</font>
												</logic:present> 
												<logic:notPresent name="creditoarealizarhistorico" property="debitoCreditoSituacaoAtual">
													<font color="#000000" style="font-size:9px"	face="Verdana, Arial, Helvetica, sans-serif"> &nbsp; </font>
												</logic:notPresent>
											</div>
											</td>

										</tr>
									</logic:iterate>
								</logic:present>
								<!-- Fim do Esquema pra exibir os creditos do histórico =========================== -->

							</table>
							<%if ((Integer)request.getAttribute("tamanhoColecaoCreditos") > ConstantesSistema.NUMERO_MAXIMO_REGISTROS_CONTAS_DEBITO) {%>
							</div>
							<%}%>
							</td>
						</tr>
						
						
						<!-- Fim da tabela Credito A Realizar -->					
					
					
					
					
						<tr>
							<td colspan="3">&nbsp;</td>
						</tr>
						
					
					
					
						<!-- inicio da tabela Guia de Pagamento -->
						
						<tr>
							<td colspan="4">
							<table width="100%" border="0" bgcolor="#99C7FC">
								<tr bgcolor="#79bbfd">
									<td bgcolor="#79bbfd" height="20" colspan="8">
									<strong>Histórico das Guias de Pagamento do Imóvel</strong></td>
								</tr>
								
								<%if ((Integer)request.getAttribute("tamanhoColecaoGuias") <= ConstantesSistema.NUMERO_MAXIMO_REGISTROS_CONTAS_DEBITO) {%>
								
								<tr bordercolor="#FFFFFF" bgcolor="#99C7FC">
									<td width="32%" bordercolor="#000000" bgcolor="#90c7fc">
									<div align="center"><font color="#000000" style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"><strong>Tipo do Débito</strong></font></div>
									</td>

									<td width="10%" bordercolor="#000000" bgcolor="#90c7fc">
									<div align="center"><font color="#000000" style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"><strong>Número da Prestação</strong></font></div>
									</td>

									<td width="15%" bordercolor="#000000" bgcolor="#90c7fc">
									<div align="center"><font color="#000000" style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"><strong>Número da Prestação Total</strong></font></div>
									</td>
									
									<td width="14%" bordercolor="#000000" bgcolor="#90c7fc">
									<div align="center"><font color="#000000" style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"><strong>Emissão </strong></font></div>
									</td>
									
									<td width="14%" bordercolor="#000000" bgcolor="#90c7fc">
									<div align="center"><font color="#000000" style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"><strong>Vencimento </strong></font></div>
									</td>
									
									<td width="15%" bordercolor="#000000" bgcolor="#90c7fc">
									<div align="center"><font color="#000000" style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"><strong>Valor da Guia</strong></font></div>
									</td>

								<%}else{ %>
								<tr bordercolor="#FFFFFF" bgcolor="#99C7FC">

									<td width="31%" bordercolor="#000000" bgcolor="#90c7fc">
									<div align="center"><font color="#000000" style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"><strong>Tipo do Débito</strong></font></div>
									</td>

									<td width="11%" bordercolor="#000000" bgcolor="#90c7fc">
									<div align="center"><font color="#000000" style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"><strong>Número da Prestação</strong></font></div>
									</td>

									<td width="14%" bordercolor="#000000" bgcolor="#90c7fc">
									<div align="center"><font color="#000000" style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"><strong>Número da Prestação Total</strong></font></div>
									</td>
									
									<td width="14%" bordercolor="#000000" bgcolor="#90c7fc">
									<div align="center"><font color="#000000" style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"><strong>Emissão </strong></font></div>
									</td>
									
									<td width="13%" bordercolor="#000000" bgcolor="#90c7fc">
									<div align="center"><font color="#000000" style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"><strong>Vencimento </strong></font></div>
									</td>
									
									<td width="19%" bordercolor="#000000" bgcolor="#90c7fc">
									<div align="center"><font color="#000000" style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"><strong>Valor da Guia</strong></font></div>
									</td>
									
								</tr>
								
								<%} %>
							</table>

							<%if ((Integer)request.getAttribute("tamanhoColecaoGuias") > ConstantesSistema.NUMERO_MAXIMO_REGISTROS_CONTAS_DEBITO) {%>
							<div style="width: 100%; height: 65; overflow: auto;">
							<%} %>
							<table width="100%" bgcolor="#99C7FC">

								<!-- Esquema pra exibir as guias =========================== -->
								<logic:present name="colecaoGuiaPagamentoImovel">

									<%int cont7 = 0;%>
									<logic:iterate name="colecaoGuiaPagamentoImovel" id="guiapagamento" type="GuiaPagamento">

										<%cont7 = cont7 + 1;
									if (cont7 % 2 == 0) {%>
										<tr bgcolor="#cbe5fe">
											<%} else {

									%>
										<tr bgcolor="#FFFFFF">
											<%}%>
											<td width="32%"><font color="#000000" style="font-size:9px"
												face="Verdana, Arial, Helvetica, sans-serif"> <a
												href="javascript:abrirPopup('exibirConsultarGuiaPagamentoAction.do?guiaPagamentoId=<bean:write name="guiapagamento" property="id" />', 600, 800);"><bean:define name="guiapagamento" property="debitoTipo"	id="debitoTipo" /> 
												<logic:notEmpty name="debitoTipo" property="descricao"><bean:write name="debitoTipo" property="descricao" /></logic:notEmpty> </a> 
											</font></td>
											

											<td width="10%">
											<div align="center">
												<logic:present name="guiapagamento" property="numeroPrestacaoDebito">
													<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
														<bean:write	name="guiapagamento" property="numeroPrestacaoDebito" />
													</font>
												</logic:present> 
												<logic:notPresent name="guiapagamento" property="numeroPrestacaoDebito">
													<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> &nbsp; </font>
												</logic:notPresent>
											</div>
											</td>
											
											<td width="15%">
											<div align="center">
												<logic:present name="guiapagamento" property="numeroPrestacaoTotal">
													<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
														<bean:write	name="guiapagamento" property="numeroPrestacaoTotal" />
													</font>
												</logic:present> 
												<logic:notPresent name="guiapagamento" property="numeroPrestacaoTotal">
													<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> &nbsp; </font>
												</logic:notPresent>
											</div>
											</td>

											<td width="14%">
											<div align="center">
												<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
													<bean:write name="guiapagamento" property="dataEmissao" formatKey="date.format" />
												</font>
											</div>
											</td>

											<td width="14%">
											<div align="center">
												<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
													<bean:write name="guiapagamento" property="dataVencimento" formatKey="date.format" />
												</font>
											</div>
											</td>

											<td width="15%">
											<div align="right">
												<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
													<bean:write name="guiapagamento" property="valorDebito"	formatKey="money.format" />
												</font>
											</div>
											</td>


										</tr>
									</logic:iterate>
								</logic:present>
								<!-- Fim do Esquema pra exibir as guias =========================== -->



								<!-- Esquema pra exibir as guias do histórico =========================== -->
								<logic:present name="colecaoGuiaPagamentoHistoricoImovel">

									<%int cont8 = 0;%>
									<logic:iterate name="colecaoGuiaPagamentoHistoricoImovel" id="guiaPagamentohistorico" type="GuiaPagamentoHistorico">

										<%cont8 = cont8 + 1;
							if (cont8 % 2 == 0) {%>
										<tr bgcolor="#cbe5fe">
											<%} else {

							%>
										<tr bgcolor="#FFFFFF">
											<%}%>
										<td width="32%"><font color="#000000" style="font-size:9px"
												face="Verdana, Arial, Helvetica, sans-serif"> <a
												href="javascript:abrirPopup('exibirConsultarGuiaPagamentoAction.do?guiaPagamentoHistoricoId=<bean:write name="guiaPagamentohistorico" property="id" />', 600, 800);"><bean:define name="guiaPagamentohistorico" property="debitoTipo"	id="debitoTipo" /> 
												<logic:notEmpty name="debitoTipo" property="descricao"><bean:write name="debitoTipo" property="descricao" /></logic:notEmpty> </a> 
											</font></td>
											

											<td width="10%">
											<div align="center">
												<logic:present name="guiaPagamentohistorico" property="numeroPrestacaoDebito">
													<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
														<bean:write	name="guiaPagamentohistorico" property="numeroPrestacaoDebito" />
													</font>
												</logic:present> 
												<logic:notPresent name="guiaPagamentohistorico" property="numeroPrestacaoDebito">
													<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> &nbsp; </font>
												</logic:notPresent>
											</div>
											</td>
											
											<td width="15%">
											<div align="center">
												<logic:present name="guiaPagamentohistorico" property="numeroPrestacaoTotal">
													<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
														<bean:write	name="guiaPagamentohistorico" property="numeroPrestacaoTotal" />
													</font>
												</logic:present> 
												<logic:notPresent name="guiaPagamentohistorico" property="numeroPrestacaoTotal">
													<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> &nbsp; </font>
												</logic:notPresent>
											</div>
											</td>

											<td width="14%">
											<div align="center">
												<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
													<bean:write name="guiaPagamentohistorico" property="dataEmissao" formatKey="date.format" />
												</font>
											</div>
											</td>

											<td width="14%">
											<div align="center">
												<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
													<bean:write name="guiaPagamentohistorico" property="dataVencimento" formatKey="date.format" />
												</font>
											</div>
											</td>

											<td width="15%">
											<div align="right">
												<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
													<bean:write name="guiaPagamentohistorico" property="valorDebito"	formatKey="money.format" />
												</font>
											</div>
											</td>

										</tr>
									</logic:iterate>
								</logic:present>
								<!-- Fim do Esquema pra exibir as guias do histórico =========================== -->

							</table>
							<%if ((Integer)request.getAttribute("tamanhoColecaoGuias") > ConstantesSistema.NUMERO_MAXIMO_REGISTROS_CONTAS_DEBITO) {%>
							</div>
							<%} %>
							</td>
						</tr>
						
						
						<!-- Fim da tabela Guia de Pagamento -->		
					
					
					
					
					
					
						
						<tr>
							<td colspan="4">
								<input type="button" name="Desfazer" class="bottonRightCol" value="Desfazer" 
								onClick="javascript:window.location.href='/gsan/exibirConsultarHistoricoFaturamentoAction.do?menu=sim'"
								style="width: 80px" tabindex="9"/>
								&nbsp; 
								<input type="button" name="Cancelar" class="bottonRightCol" value="Cancelar" 
								onClick="javascript:window.location.href='/gsan/telaPrincipal.do'" style="width: 80px" tabindex="10"/>
							</td>
						</tr>
					</table>
					</td>
				</tr>
			</table>
			<p>&nbsp;</p>
			</td>
		</tr>
	</table>

	<%@ include file="/jsp/util/rodape.jsp"%>

</html:form>

</body>
</html:html>
