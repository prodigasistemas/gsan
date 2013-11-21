
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<%@ page import="gcom.util.Util"%>
<%@ page import="gcom.faturamento.conta.Conta" isELIgnored="false"%>
<%@ page import="gcom.faturamento.conta.ContaHistorico"
	isELIgnored="false"%>
<%@ page import="gcom.faturamento.debito.DebitoCobrado"
	isELIgnored="false"%>
<%@ page import="gcom.faturamento.debito.DebitoCobradoHistorico"
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
<html:form action="/exibirConsultarImovelAction.do"
		name="ConsultarImovelActionForm"
		type="gcom.gui.cadastro.imovel.ConsultarImovelActionForm"
		method="post">

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
				<td class="parabg">Consultar Débitos Cobrados de Uma Conta</td>
				<td width="11"><img border="0"
					src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
			</tr>
		</table>
		<p>&nbsp;</p>
		<table width="100%" border="0">
			<tr>
				<td align="right"><a
					href="javascript: abrirPopupHelp('/gsan/help/help.jsp?mapIDHelpSet=faturamentoDebitoCobradoConsultar', 500, 700);"><span
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
		</logic:present> <%-- FIM CONTA HISTÓRICO--%> <!--============================ DEBITOS COBRADOS ============================ -->
		<hr>
		<table width="100%" border="0">
			<tr>
				<td>
				<p><strong>Débitos Cobrados:</strong></p>
				<table width="100%" border="0" bgcolor="#99C7FC">
					<tr bordercolor="#FFFFFF" bgcolor="#99C7FC">
						<td width="30%">
						<div align="left"><strong>Tipo do D&eacute;bito</strong></div>
						</td>
						<td width="8%">
						<div align="center"><strong>M&ecirc;s/Ano Refer&ecirc;ncia</strong></div>
						</td>
						<td width="8%">
						<div align="center"><strong>M&ecirc;s/Ano Cobran&ccedil;a</strong></div>
						</td>
						<td width="8%">
						<div align="center"><strong>Parcela</strong></div>
						</td>
						<td width="10%">
						<div align="center"><strong>Valor da Parcela</strong></div>
						</td>
					</tr>
				</table>
				<div style="width: 100%; height: 100; overflow: auto;">

				<table width="100%" border="0" bgcolor="#99C7FC">


					<%-- INICIO DEBITO COBRADO --%>
					<logic:present name="colecaoContaDebitosCobrados">
						<%int cont = 0;%>
						<logic:iterate name="colecaoContaDebitosCobrados"
							id="debitoCobrado" type="DebitoCobrado">
							<%cont = cont + 1;
						if (cont % 2 == 0) {%>
							<tr bgcolor="#cbe5fe">
								<%} else {%>
							<tr bgcolor="#FFFFFF">
								<%}%>

								<td width="30%">
								<div align="left"><bean:write name="debitoCobrado"
									property="debitoTipo.descricao" /></div>
								</td>

								<td width="8%">
								<div align="center"><logic:notEmpty name="debitoCobrado"
									property="anoMesReferenciaDebito">
									<span style="color: #000000;"><%=""
											+ Util
													.formatarMesAnoReferencia(debitoCobrado
															.getAnoMesReferenciaDebito())%></span>
								</logic:notEmpty> <logic:empty name="debitoCobrado"
									property="anoMesReferenciaDebito">
			    		&nbsp;
			  		  </logic:empty></div>
								</td>

								<td width="8%">
								<div align="center"><logic:notEmpty name="debitoCobrado"
									property="anoMesCobrancaDebito">
									<span style="color: #000000;"><%=""
											+ Util
													.formatarMesAnoReferencia(debitoCobrado
															.getAnoMesCobrancaDebito())%></span>
								</logic:notEmpty> <logic:empty name="debitoCobrado"
									property="anoMesCobrancaDebito">
			    	  &nbsp;
			  		</logic:empty></div>
								</td>

								<td width="8%">
								<div align="center"><bean:write name="debitoCobrado"
									property="numeroPrestacaoDebito" /> / <bean:write
									name="debitoCobrado" property="numeroTotalParcelasMenosBonus" /></div>
								</td>

								<td width="10%">
								<div align="right"><bean:write name="debitoCobrado"
									property="valorPrestacao" formatKey="money.format" /></div>
								</td>
							</tr>
						</logic:iterate>
					</logic:present>
					<%-- FIM DEBITO COBRADO --%>


					<%-- INICIO DEBITO COBRADO HISTORICO --%>
					<logic:present name="colecaoContaDebitosCobradosHistorico">
						<%int cont1 = 0;%>
						<logic:iterate name="colecaoContaDebitosCobradosHistorico"
							id="debitoCobradoHistorico" type="DebitoCobradoHistorico">
							<%cont1 = cont1 + 1;
						if (cont1 % 2 == 0) {%>
							<tr bgcolor="#FFFFFF">
								<%} else {%>
							<tr bgcolor="#cbe5fe">
								<%}%>

								<td width="30%">
								<div align="left"><bean:write name="debitoCobradoHistorico"
									property="debitoTipo.descricao" /></div>
								</td>

								<td width="8%">
								<div align="center"><logic:notEmpty
									name="debitoCobradoHistorico" property="anoMesReferenciaDebito">
									<span style="color: #000000;"><%=""
											+ Util
													.formatarMesAnoReferencia(debitoCobradoHistorico
															.getAnoMesReferenciaDebito())%></span>
								</logic:notEmpty> <logic:empty name="debitoCobradoHistorico"
									property="anoMesReferenciaDebito">
			    		&nbsp;
			  		  </logic:empty></div>
								</td>

								<td width="8%">
								<div align="center"><logic:notEmpty
									name="debitoCobradoHistorico" property="anoMesCobrancaDebito">
									<span style="color: #000000;"><%=""
											+ Util
													.formatarMesAnoReferencia(debitoCobradoHistorico
															.getAnoMesCobrancaDebito())%></span>
								</logic:notEmpty> <logic:empty name="debitoCobradoHistorico"
									property="anoMesCobrancaDebito">
			    	  &nbsp;
			  		</logic:empty></div>
								</td>

								<td width="8%">
								<div align="center"><bean:write name="debitoCobradoHistorico"
									property="numeroPrestacaoDebito" /> / <bean:write
									name="debitoCobradoHistorico" property="numeroTotalParcelasMenosBonus" /></div>
								</td>

								<td width="10%">
								<div align="right"><bean:write name="debitoCobradoHistorico"
									property="valorPrestacao" formatKey="money.format" /></div>
								</td>
							</tr>
						</logic:iterate>
					</logic:present>
					<%-- INICIO DEBITO COBRADO HISTORICO --%>





				</table>
				</div>
				</td>
			</tr>
			<tr>
				<td align="left" width="100%">
					  <div align="right">
						   <a href="javascript:toggleBox('demodiv',1);">
								<img border="0" src="<bean:message key="caminho.imagens"/>print.gif"
									title="Imprimir Valor dos Débitos" /> 
							</a>
					  </div>
				</td>
			</tr>
			<tr>&nbsp;</tr>

			<table width="100%" border="0">

				<tr>
					<td height="24"><logic:present
						name="caminhoRetornoTelaConsultaDebitos">
						<input type="button" class="bottonRightCol" value="Voltar"
							style="width: 70px;" onclick="javascript:history.back();" />
					</logic:present>
					
					<td align="right"><input name="Button" type="button"
						class="bottonRightCol" value="Fechar"
						onClick="javascript:fechar();">
					</td>
				</tr>
			</table>
		</table>
		<!--========================================================================== -->

		<p>&nbsp;</p>
		</td>
	</tr>
</table>
<jsp:include page="/jsp/relatorio/escolher_tipo_relatorio.jsp?relatorio=gerarRelatorioDebitoCobradoContaAction.do"/>
<%@ include file="/jsp/util/tooltip.jsp" %>	
</html:form>
</body>
</html:html>
