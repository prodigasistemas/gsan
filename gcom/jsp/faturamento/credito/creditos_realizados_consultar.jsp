
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<%@ page import="gcom.util.Util"%>
<%@ page import="gcom.faturamento.conta.Conta" isELIgnored="false"%>
<%@ page import="gcom.faturamento.conta.ContaHistorico"
	isELIgnored="false"%>
<%@ page import="gcom.faturamento.credito.CreditoRealizado"
	isELIgnored="false"%>
<%@ page import="gcom.faturamento.credito.CreditoRealizadoHistorico"
	isELIgnored="false"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
<head>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">

<script language="JavaScript">
<!--
function fechar(){
  window.close();
}
-->
</script>

</head>

<body leftmargin="5" topmargin="5">

<table width="770" border="0" cellspacing="5" cellpadding="0">
	<tr>
		<td width="750" valign="top" class="centercoltext">
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
				<td class="parabg">Consultar Créditos Realizados em Uma Conta</td>
				<td width="11"><img border="0"
					src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
			</tr>
		</table>
		<p>&nbsp;</p>
		<table width="100%" border="0">
			<tr>
				<td align="right"><a
					href="javascript: abrirPopupHelp('/gsan/help/help.jsp?mapIDHelpSet=imovelAutorizacaoDoacaoMensalManter', 500, 700);"><span
					style="font-weight: bold"><font color="#3165CE">Ajuda</font></span></a></td>
			</tr>
		</table>
		<%-- INICIO CONTA --%> <logic:present name="conta" scope="session">
			<table width="100%" border="0">
				<tr>
					<td height="25"><strong>Matr&iacute;cula do Im&oacute;vel:</strong></td>
					<td width="120"><html:text name="conta" property="imovel.id"
						size="12" maxlength="10" readonly="true"
						style="background-color:#EFEFEF; border:0" /></td>
					<td><strong>M&ecirc;s e Ano da Conta:</strong></td>
					<td><input type="text" name="referencia" size="10"
						value=<%="" +Util.formatarMesAnoReferencia(((Conta)session.getAttribute("conta")).getReferencia())%>
						readonly="true" style="background-color:#EFEFEF; border:0" /></td>
				</tr>
				<tr>
					<td width="183" height="25"><strong>Situa&ccedil;&atilde;o da
					Conta:</strong></td>
					<td colspan="3"><html:text name="conta"
						property="debitoCreditoSituacaoAtual.descricaoDebitoCreditoSituacao"
						size="30" readonly="true"
						style="background-color:#EFEFEF; border:0" /></td>
				</tr>
				<tr>
					<td><strong>Situa&ccedil;&atilde;o da Lig. de &Aacute;gua:</strong></td>
					<td><logic:present name="conta" property="ligacaoAguaSituacao">
						<html:text name="conta" property="ligacaoAguaSituacao.descricao"
							size="20" readonly="true"
							style="background-color:#EFEFEF; border:0" />
					</logic:present> <logic:notPresent name="conta"
						property="ligacaoAguaSituacao">
						<input type="text" name="ligacaoAguaSituacao" size="20"
							readonly="true" style="background-color:#EFEFEF; border:0" />
					</logic:notPresent></td>
					<td width="170"><strong>Situa&ccedil;&atilde;o da Lig. de Esgoto:</strong></td>
					<td width="120"><logic:present name="conta"
						property="ligacaoEsgotoSituacao">
						<html:text name="conta" property="ligacaoEsgotoSituacao.descricao"
							size="20" readonly="true"
							style="background-color:#EFEFEF; border:0" />
					</logic:present> <logic:notPresent name="conta"
						property="ligacaoEsgotoSituacao">
						<input type="text" name="ligacaoEsgotoSituacao" size="20"
							readonly="true" style="background-color:#EFEFEF; border:0" />
					</logic:notPresent></td>
				</tr>
			</table>
		</logic:present> <%-- FIM CONTA --%> <%-- INICIO CONTA HISTÓRICO --%>
		<logic:present name="contaHistorico" scope="session">
			<table width="100%" border="0">
				<tr>
					<td height="25"><strong>Matr&iacute;cula do Im&oacute;vel:</strong></td>
					<td width="120"><html:text name="contaHistorico"
						property="imovel.id" size="12" maxlength="10" readonly="true"
						style="background-color:#EFEFEF; border:0" /></td>

					<td><strong>M&ecirc;s e Ano da Conta:</strong></td>
					<td><input type="text" name="referencia" size="10"
						value=<%="" +Util.formatarMesAnoReferencia(((ContaHistorico)session.getAttribute("contaHistorico")).getAnoMesReferenciaConta())%>
						readonly="true" style="background-color:#EFEFEF; border:0" /></td>
				</tr>

				<tr>
					<td width="183" height="25"><strong>Situa&ccedil;&atilde;o da
					Conta:</strong></td>
					<td colspan="3"><html:text name="contaHistorico"
						property="debitoCreditoSituacaoAtual.descricaoDebitoCreditoSituacao"
						size="30" readonly="true"
						style="background-color:#EFEFEF; border:0" /></td>
				</tr>
				<tr>
					<td><strong>Situa&ccedil;&atilde;o da Lig. de &Aacute;gua:</strong></td>
					<td><logic:present name="contaHistorico"
						property="ligacaoAguaSituacao">
						<html:text name="contaHistorico"
							property="ligacaoAguaSituacao.descricao" size="20"
							readonly="true" style="background-color:#EFEFEF; border:0" />
					</logic:present> <logic:notPresent name="contaHistorico"
						property="ligacaoAguaSituacao">
						<input type="text" name="ligacaoAguaSituacao" size="20"
							readonly="true" style="background-color:#EFEFEF; border:0" />
					</logic:notPresent></td>
					<td width="170"><strong>Situa&ccedil;&atilde;o da Lig. de Esgoto:</strong></td>
					<td width="120"><logic:present name="contaHistorico"
						property="ligacaoEsgotoSituacao">
						<html:text name="contaHistorico"
							property="ligacaoEsgotoSituacao.descricao" size="20"
							readonly="true" style="background-color:#EFEFEF; border:0" />
					</logic:present> <logic:notPresent name="contaHistorico"
						property="ligacaoEsgotoSituacao">
						<input type="text" name="ligacaoEsgotoSituacao" size="20"
							readonly="true" style="background-color:#EFEFEF; border:0" />
					</logic:notPresent></td>
				</tr>
			</table>
		</logic:present> <%-- FIM CONTA HISTÓRICO--%> <!--============================ CREDITOS REALIZADOS ============================ -->
		<hr>
		<p><strong>Créditos Realizados:</strong></p>
		<table width="100%" border="0" bgcolor="#99C7FC">
			<tr bordercolor="#FFFFFF" bgcolor="#99C7FC">
				<td width="55%">
				<div align="left"><strong>Tipo do Crédito</strong></div>
				</td>
				<td width="10%">
				<div align="center"><strong>M&ecirc;s/Ano Refer&ecirc;ncia</strong></div>
				</td>
				<td width="10%">
				<div align="center"><strong>M&ecirc;s/Ano Cobran&ccedil;a</strong></div>
				</td>
				<td width="8%">
				<div align="center"><strong>Parcela</strong></div>
				</td>
				<td width="17%">
				<div align="center"><strong>Valor do Crédito</strong></div>
				</td>
			</tr>
			<%-- INICIO CREDITO REALIZADO --%>
			<logic:present name="colecaoContaCreditosRealizados">
				<%int cont = 0;%>
				<logic:iterate name="colecaoContaCreditosRealizados"
					id="creditoRealizado" type="CreditoRealizado">

					<%cont = cont + 1;
						if (cont % 2 == 0) {%>
					<tr bgcolor="#cbe5fe">
						<%} else {

						%>
					<tr bgcolor="#FFFFFF">
						<%}%>

						<td>
						<div align="left"><bean:write name="creditoRealizado"
							property="creditoTipo.descricao" /></div>
						</td>

						<td>
						<div align="center"><logic:notEmpty name="creditoRealizado"
							property="anoMesReferenciaCredito">
							<span style="color: #000000;"><%=""
											+ Util
													.formatarMesAnoReferencia(creditoRealizado
															.getAnoMesReferenciaCredito()
															.intValue())%></span>
						</logic:notEmpty> <logic:empty name="creditoRealizado"
							property="anoMesReferenciaCredito">
			    &nbsp;
			  </logic:empty></div>
						</td>

						<td>
						<div align="center"><logic:notEmpty name="creditoRealizado"
							property="anoMesCobrancaCredito">
							<span style="color: #000000;"><%=""
											+ Util
													.formatarMesAnoReferencia(creditoRealizado
															.getAnoMesCobrancaCredito()
															.intValue())%></span>
						</logic:notEmpty> <logic:empty name="creditoRealizado"
							property="anoMesCobrancaCredito">
			  &nbsp;
			</logic:empty></div>
						</td>

						<td>
						<div align="center"><bean:write name="creditoRealizado"
							property="numeroPrestacaoCredito" /> / <bean:write
							name="creditoRealizado" property="numeroTotalParcelasMenosBonus" /></div>
						</td>

						<td>
						<div align="right"><bean:write name="creditoRealizado"
							property="valorCredito" formatKey="money.format" /></div>
						</td>

					</tr>
				</logic:iterate>
			</logic:present>
			<%-- FIM CREDITO REALIZADO --%>


			<%-- INICIO CREDITO REALIZADO HISTORICO --%>
			<logic:present name="colecaoContaCreditosRealizadosHistorico">
				<%int cont1 = 0;%>
				<logic:iterate name="colecaoContaCreditosRealizadosHistorico"
					id="creditoRealizadoHistorico" type="CreditoRealizadoHistorico">

					<%cont1 = cont1 + 1;
						if (cont1 % 2 == 0) {%>
					<tr bgcolor="#FFFFFF">
						<%} else {

						%>
					<tr bgcolor="#cbe5fe">
						<%}%>

						<td>
						<div align="left"><bean:write name="creditoRealizadoHistorico"
							property="creditoTipo.descricao" /></div>
						</td>

						<td>
						<div align="center"><logic:notEmpty
							name="creditoRealizadoHistorico"
							property="anoMesReferenciaCredito">
							<span style="color: #000000;"><%=""
											+ Util
													.formatarMesAnoReferencia(creditoRealizadoHistorico
															.getAnoMesReferenciaCredito()
															.intValue())%></span>
						</logic:notEmpty> <logic:empty name="creditoRealizadoHistorico"
							property="anoMesReferenciaCredito">
			    &nbsp;
			  </logic:empty></div>
						</td>

						<td>
						<div align="center"><logic:notEmpty
							name="creditoRealizadoHistorico" property="anoMesCobrancaCredito">
							<span style="color: #000000;"><%=""
											+ Util
													.formatarMesAnoReferencia(creditoRealizadoHistorico
															.getAnoMesCobrancaCredito()
															.intValue())%></span>
						</logic:notEmpty> <logic:empty name="creditoRealizadoHistorico"
							property="anoMesCobrancaCredito">
			  &nbsp;
			</logic:empty></div>
						</td>

						<td>
						<div align="center"><bean:write name="creditoRealizadoHistorico"
							property="numeroPrestacaoCredito" /> / <bean:write
							name="creditoRealizadoHistorico" property="numeroTotalParcelasMenosBonus" /></div>
						</td>

						<td>
						<div align="right"><logic:notEmpty
							name="creditoRealizadoHistorico" property="valorCredito">
							<bean:write name="creditoRealizadoHistorico"
								property="valorCredito" formatKey="money.format" />
						</logic:notEmpty> <logic:notEmpty name="creditoRealizadoHistorico"
							property="valorCredito">	
			    &nbsp;
			  </logic:notEmpty></div>
						</td>

					</tr>
				</logic:iterate>
			</logic:present>
			<%-- FIM CREDITO REALIZADO HISTORICO --%>
		</table>
		<!--========================================================================== -->
		<table width="100%" border="0">
			<tr>
				<td height="24"><logic:present
					name="caminhoRetornoTelaConsultaCreditoRealizado">
					<input type="button" class="bottonRightCol" value="Voltar"
						style="width: 70px;" onclick="javascript:history.back();" />
				</logic:present>
				<td align="right"><input name="Button" type="button"
					class="bottonRightCol" value="Fechar"
					onClick="javascript:fechar();"></td>
			</tr>
		</table>
		<p>&nbsp;</p>
		</td>
	</tr>
</table>
</body>
</html:html>
