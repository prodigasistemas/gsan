
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<%@ page import="gcom.util.Util"%>
<%@ page
	import="gcom.faturamento.credito.CreditoARealizar,gcom.cobranca.parcelamento.ParcelamentoItem,gcom.faturamento.credito.CreditoARealizarHistorico"
	isELIgnored="false"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
<head>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
<SCRIPT LANGUAGE="JavaScript">
<!--

function fechar(){
		window.close();
}

//-->
</SCRIPT>
</head>

<body leftmargin="5" topmargin="5">

<table width="700" border="0" cellspacing="5" cellpadding="0">
	<tr>
		<td width="625" valign="top" class="centercoltext">
		<table width="100%" border="0" align="center" cellpadding="0"
			cellspacing="0">
			<tr>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td width="11"><img border="0"
					src="<bean:message key="caminho.imagens"/>parahead_left.gif" /></td>
				<td class="parabg">Consultar Créditos A Realizar</td>
				<td width="11"><img border="0"
					src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
			</tr>
		</table>
		<p>&nbsp;</p>
		<table width="100%" border="0">
			<tr>
				<td width="20%"><strong>Código do Imóvel:</strong></td>
				<td width="80%" colspan="2" align="left"><html:text
					name="imovelConsultar" property="id" size="12" maxlength="10"
					readonly="true" style="background-color:#EFEFEF; border:0" /></td>
			</tr>
			<tr>
				<td colspan="3">
				<table width="100%" align="center" bgcolor="#99CCFF" border="0">
					<tr>
						<td align="center"><strong>Endere&ccedil;o </strong></td>
					</tr>
					<tr bgcolor="#FFFFFF">
						<td>
						<div align="center">${requestScope.enderecoFormatado} &nbsp;</div>
						</td>
					</tr>
				</table>
				</td>
			</tr>
			<tr>
				<td colspan="3">
				<hr>
				</td>
			</tr>
			
			<!-- CREDITO A REALIZAR -->
			<logic:present name="colecaoCreditoARealizar">
				<tr>
					<td colspan="3" height="300">
					<div style="width: 100%; height: 330; overflow: auto;">
					<table width="80%" border="0">
						<logic:iterate name="colecaoCreditoARealizar"
							id="creditoARealizar" type="CreditoARealizar">
							<tr>
								<td><strong>Tipo do Crédito:<font color="#FF0000"> </font></strong></td>
								<td colspan="2" align="right">
								<div align="left"><logic:notEmpty name="creditoARealizar"
									property="creditoTipo">
									<html:text name="creditoARealizar"
										property="creditoTipo.descricao" size="30" maxlength="30"
										readonly="true" style="background-color:#EFEFEF; border:0" />
								</logic:notEmpty></div>
								</td>
							</tr>
							<tr>
								<td><strong>Situa&ccedil;&atilde;o do Crédito a Realizar:</strong></td>
								<td colspan="2" align="right">
								<div align="left"><logic:notEmpty name="creditoARealizar"
									property="debitoCreditoSituacaoAtual">
									<html:text name="creditoARealizar"
										property="debitoCreditoSituacaoAtual.descricaoDebitoCreditoSituacao"
										size="30" maxlength="30" readonly="true"
										style="background-color:#EFEFEF; border:0" />
								</logic:notEmpty></div>
								</td>
							</tr>
							<tr>
								<td width="183" height="25"><strong>Usuário:</strong></td>
								<td colspan="3">
									<logic:present name="creditoARealizar" property="usuario">
										<html:text name="creditoARealizar" property="usuario.nomeUsuario" size="30" readonly="true" style="background-color:#EFEFEF; border:0" />
									</logic:present>		
									<logic:notPresent name="creditoARealizar" property="usuario">
										<input type="text" name="usuario" size="30" readonly="true" style="background-color:#EFEFEF; border:0" />
									</logic:notPresent>
								</td>
							</tr>
							<tr>
								<td><strong>Data e Hora de Gera&ccedil;&atilde;o do Crédito:</strong></td>
								<td colspan="2" align="right">
								<div align="left"><logic:notEmpty name="creditoARealizar"
									property="geracaoCredito">
									<input type="text" size="10" maxlength="10" readonly="true"
										style="background-color:#EFEFEF; border:0"
										value="<bean:write name="creditoARealizar"  property="geracaoCredito" formatKey="date.format" />">
									&nbsp; <input type="text" size="8" maxlength="8"
										readonly="true" style="background-color:#EFEFEF; border:0"
										value="<bean:write name="creditoARealizar"  property="geracaoCredito" formatKey="hour.format" />">
								</logic:notEmpty>
								</td>
							</tr>
							<tr>
								<td><strong>M&ecirc;s e Ano de Refer&ecirc;ncia do Crédito:</strong></td>
								<td colspan="2" align="right">
								<div align="left"><logic:notEmpty name="creditoARealizar"
									property="anoMesReferenciaCredito">
									<input type="text" size="7" maxlength="7" readonly="true"
										style="background-color:#EFEFEF; border:0"
										value="<%="" + Util.formatarMesAnoReferencia(creditoARealizar.getAnoMesReferenciaCredito())%>">
								</logic:notEmpty>
								</td>
							</tr>
							<tr>
								<td><strong>M&ecirc;s e Ano da Cobran&ccedil;a do Crédito:</strong></td>
								<td colspan="2" align="right">
								<div align="left"><logic:notEmpty name="creditoARealizar"
									property="anoMesCobrancaCredito">
									<input type="text" size="7" maxlength="7" readonly="true"
										style="background-color:#EFEFEF; border:0"
										value="<%="" + Util.formatarMesAnoReferencia(creditoARealizar.getAnoMesCobrancaCredito())%>">
								</logic:notEmpty>
								</td>
							</tr>
							<tr>
								<td><strong>N&uacute;mero de Presta&ccedil;&otilde;es
								Creditadas:<strong></strong></td>
								<td colspan="2" align="right">
								<div align="left"><logic:notEmpty name="creditoARealizar"
									property="numeroPrestacaoRealizada">
									<html:text name="creditoARealizar"
										property="numeroPrestacaoRealizada" size="7" maxlength="7"
										readonly="true"
										style="background-color:#EFEFEF; border:0; text-align: right;" />
								</logic:notEmpty></div>
								</td>
							</tr>
							<tr>
								<td><strong>N&uacute;mero Total de Presta&ccedil;&otilde;es:<strong><font
									color="#FF0000"> </font></strong></strong></td>
								<td colspan="2" align="right">
								<div align="left"><logic:notEmpty name="creditoARealizar"
									property="numeroPrestacaoCreditoMenosBonus">
									<html:text name="creditoARealizar"
										property="numeroPrestacaoCreditoMenosBonus" size="7" maxlength="7"
										readonly="true"
										style="background-color:#EFEFEF; border:0; text-align: right;" />
								</logic:notEmpty></div>
								</td>
							</tr>
							<tr>
								<td><strong>Valor Total do Crédito:<strong></strong></strong></td>
								<td colspan="2" align="right">
								<div align="left"><logic:notEmpty name="creditoARealizar"
									property="valorCredito">
									<input type="text" size="17" maxlength="17" readonly="true"
										style="background-color:#EFEFEF; border:0; text-align: right;"
										value="<%="" + Util.formatarMoedaReal(creditoARealizar.getValorCredito())%>">
								</logic:notEmpty></div>
								</td>
							</tr>
							<tr>
								<td><strong>Valor Restante a Ser Creditado:<strong></strong></strong></td>
								<td colspan="2" align="right">
								<div align="left"><logic:notEmpty name="creditoARealizar"
									property="valorTotalComBonus">
									<input type="text" size="17" maxlength="17" readonly="true"
										style="background-color:#EFEFEF; border:0; text-align: right;"
										value="<%="" + Util.formatarMoedaReal(creditoARealizar.getValorTotalComBonus())%>">
								</logic:notEmpty></div>
								</td>
							</tr>
							<tr>
								<td><strong>Registro de Atendimento:<strong><font
									color="#FF0000"> </font></strong><font color="#FF0000"> </font></strong></td>
								<td colspan="2" align="left"><logic:notEmpty
									name="creditoARealizar" property="registroAtendimento">
									<html:text name="creditoARealizar"
										property="registroAtendimento.id" size="9" readonly="true"
										style="background-color:#EFEFEF; border:0; text-align: right;" />
								</logic:notEmpty>&nbsp;</td>
							</tr>
							<tr>
								<td><strong>Ordem de Servi&ccedil;o:<strong><font
									color="#FF0000"> </font></strong><font color="#FF0000"> </font></strong></td>
								<td colspan="2" align="left"><logic:notEmpty
									name="creditoARealizar" property="ordemServico">
									<html:text name="creditoARealizar" property="ordemServico.id"
										size="9" readonly="true"
										style="background-color:#EFEFEF; border:0; text-align: right;" />
								</logic:notEmpty>&nbsp;</td>
							</tr>
							<tr>
								<td><strong>Origem do Crédito:<font color="#FF0000"></font></strong></td>
								<td colspan="2" align="right">
								<div align="left"><logic:notEmpty name="creditoARealizar"
									property="creditoOrigem">
									<html:text name="creditoARealizar"
										property="creditoOrigem.descricaoCreditoOrigem" size="45"
										maxlength="45" readonly="true"
										style="background-color:#EFEFEF; border:0" />
								</logic:notEmpty></div>
								</td>
							</tr>
							
							<tr>
								<td><strong>Matrícula do Imóvel Origem:</strong></td>
								<td colspan="2">
									
									<logic:present name="creditoARealizar" property="origem">
										<html:text name="creditoARealizar" property="origem.creditoARealizar.imovel.id"
										size="12" maxlength="10" readonly="true"
										style="background-color:#EFEFEF; border:0" />
									</logic:present>
									
									<logic:notPresent name="creditoARealizar" property="origem">
										<input type="text" name="imovelOrigem" size="12" maxlength="10"
										readonly="true" style="background-color:#EFEFEF; border:0" />
									</logic:notPresent>
									
								</td>
							</tr>
					</table>
					<table width="100%" border="0">
						<tr>
							<td colspan="3">
							<hr>
							</td>
						</tr>
						</logic:iterate>
					</table>
					</div>
					</td>
				</tr>
			</logic:present>
			<!-- final de CREDITO A REALIZAR -->
			
			
			
			<!-- CREDITO A REALIZAR HISTORICO -->
			<logic:present name="colecaoCreditoARealizarHistorico">
				<tr>
					<td colspan="3" height="300">
					<div style="width: 100%; height: 330; overflow: auto;">
					<table width="80%" border="0">
						<logic:iterate name="colecaoCreditoARealizarHistorico" id="creditoARealizarHistorico" type="CreditoARealizarHistorico">
							<tr>
								<td><strong>Tipo do Crédito:<font color="#FF0000"> </font></strong></td>
								<td colspan="2" align="right">
								<div align="left"><logic:notEmpty name="creditoARealizarHistorico"
									property="creditoTipo">
									<html:text name="creditoARealizarHistorico"
										property="creditoTipo.descricao" size="30" maxlength="30"
										readonly="true" style="background-color:#EFEFEF; border:0" />
								</logic:notEmpty></div>
								</td>
							</tr>
							<tr>
								<td><strong>Situa&ccedil;&atilde;o do Crédito a Realizar:</strong></td>
								<td colspan="2" align="right">
								<div align="left"><logic:notEmpty name="creditoARealizarHistorico"
									property="debitoCreditoSituacaoAtual">
									<html:text name="creditoARealizarHistorico"
										property="debitoCreditoSituacaoAtual.descricaoDebitoCreditoSituacao"
										size="30" maxlength="30" readonly="true"
										style="background-color:#EFEFEF; border:0" />
								</logic:notEmpty></div>
								</td>
							</tr>
							<tr>
								<td width="183" height="25"><strong>Usuário:</strong></td>
								<td colspan="3">
									<logic:present name="creditoARealizarHistorico" property="usuario">
										<html:text name="creditoARealizarHistorico" property="usuario.nomeUsuario" size="30" readonly="true" style="background-color:#EFEFEF; border:0" />
									</logic:present>		
									<logic:notPresent name="creditoARealizarHistorico" property="usuario">
										<input type="text" name="usuario" size="30" readonly="true" style="background-color:#EFEFEF; border:0" />
									</logic:notPresent>
								</td>
							</tr>
							<tr>
								<td><strong>Data e Hora de Gera&ccedil;&atilde;o do Crédito:</strong></td>
								<td colspan="2" align="right">
								<div align="left"><logic:notEmpty name="creditoARealizarHistorico"
									property="geracaoCreditoARealizar">
									<input type="text" size="10" maxlength="10" readonly="true"
										style="background-color:#EFEFEF; border:0"
										value="<bean:write name="creditoARealizarHistorico"  property="geracaoCreditoARealizar" formatKey="date.format" />">
									&nbsp; <input type="text" size="8" maxlength="8"
										readonly="true" style="background-color:#EFEFEF; border:0"
										value="<bean:write name="creditoARealizarHistorico"  property="geracaoCreditoARealizar" formatKey="hour.format" />">
								</logic:notEmpty>
								</td>
							</tr>
							<tr>
								<td><strong>M&ecirc;s e Ano de Refer&ecirc;ncia do Crédito:</strong></td>
								<td colspan="2" align="right">
								<div align="left"><logic:notEmpty name="creditoARealizarHistorico"
									property="anoMesReferenciaCredito">
									<input type="text" size="7" maxlength="7" readonly="true"
										style="background-color:#EFEFEF; border:0"
										value="<%="" + Util.formatarMesAnoReferencia(creditoARealizarHistorico.getAnoMesReferenciaCredito())%>">
								</logic:notEmpty>
								</td>
							</tr>
							<tr>
								<td><strong>M&ecirc;s e Ano da Cobran&ccedil;a do Crédito:</strong></td>
								<td colspan="2" align="right">
								<div align="left"><logic:notEmpty name="creditoARealizarHistorico"
									property="anoMesCobrancaCredito">
									<input type="text" size="7" maxlength="7" readonly="true"
										style="background-color:#EFEFEF; border:0"
										value="<%="" + Util.formatarMesAnoReferencia(creditoARealizarHistorico.getAnoMesCobrancaCredito())%>">
								</logic:notEmpty>
								</td>
							</tr>
							<tr>
								<td><strong>N&uacute;mero de Presta&ccedil;&otilde;es
								Creditadas:<strong></strong></td>
								<td colspan="2" align="right">
								<div align="left"><logic:notEmpty name="creditoARealizarHistorico"
									property="prestacaoRealizadas">
									<html:text name="creditoARealizarHistorico"
										property="prestacaoRealizadas" size="7" maxlength="7"
										readonly="true"
										style="background-color:#EFEFEF; border:0; text-align: right;" />
								</logic:notEmpty></div>
								</td>
							</tr>
							<tr>
								<td><strong>N&uacute;mero Total de Presta&ccedil;&otilde;es:<strong><font
									color="#FF0000"> </font></strong></strong></td>
								<td colspan="2" align="right">
								<div align="left"><logic:notEmpty name="creditoARealizarHistorico"
									property="numeroPrestacaoCreditoMenosBonus">
									<html:text name="creditoARealizarHistorico"
										property="numeroPrestacaoCreditoMenosBonus" size="7" maxlength="7"
										readonly="true"
										style="background-color:#EFEFEF; border:0; text-align: right;" />
								</logic:notEmpty></div>
								</td>
							</tr>
							<tr>
								<td><strong>Valor Total do Crédito:<strong></strong></strong></td>
								<td colspan="2" align="right">
								<div align="left"><logic:notEmpty name="creditoARealizarHistorico"
									property="valorCredito">
									<input type="text" size="17" maxlength="17" readonly="true"
										style="background-color:#EFEFEF; border:0; text-align: right;"
										value="<%="" + Util.formatarMoedaReal(creditoARealizarHistorico.getValorCredito())%>">
								</logic:notEmpty></div>
								</td>
							</tr>
							<tr>
								<td><strong>Valor Restante a Ser Creditado:<strong></strong></strong></td>
								<td colspan="2" align="right">
								<div align="left"><logic:notEmpty name="creditoARealizarHistorico"
									property="valorTotalComBonus">
									<input type="text" size="17" maxlength="17" readonly="true"
										style="background-color:#EFEFEF; border:0; text-align: right;"
										value="<%="" + Util.formatarMoedaReal(creditoARealizarHistorico.getValorTotalComBonus())%>">
								</logic:notEmpty></div>
								</td>
							</tr>
							<tr>
								<td><strong>Registro de Atendimento:<strong><font
									color="#FF0000"> </font></strong><font color="#FF0000"> </font></strong></td>
								<td colspan="2" align="left"><logic:notEmpty
									name="creditoARealizarHistorico" property="registroAtendimento">
									<html:text name="creditoARealizarHistorico"
										property="registroAtendimento.id" size="9" readonly="true"
										style="background-color:#EFEFEF; border:0; text-align: right;" />
								</logic:notEmpty>&nbsp;</td>
							</tr>
							<tr>
								<td><strong>Ordem de Servi&ccedil;o:<strong><font
									color="#FF0000"> </font></strong><font color="#FF0000"> </font></strong></td>
								<td colspan="2" align="left"><logic:notEmpty
									name="creditoARealizarHistorico" property="ordemServico">
									<html:text name="creditoARealizarHistorico" property="ordemServico.id"
										size="9" readonly="true"
										style="background-color:#EFEFEF; border:0; text-align: right;" />
								</logic:notEmpty>&nbsp;</td>
							</tr>
							<tr>
								<td><strong>Origem do Crédito:<font color="#FF0000"></font></strong></td>
								<td colspan="2" align="right">
								<div align="left"><logic:notEmpty name="creditoARealizarHistorico"
									property="creditoOrigem">
									<html:text name="creditoARealizarHistorico"
										property="creditoOrigem.descricaoCreditoOrigem" size="45"
										maxlength="45" readonly="true"
										style="background-color:#EFEFEF; border:0" />
								</logic:notEmpty></div>
								</td>
							</tr>
							
							<tr>
								<td><strong>Matrícula do Imóvel Origem:</strong></td>
								<td colspan="2">
									
									<logic:present name="creditoARealizarHistorico" property="origem">
										<html:text name="creditoARealizarHistorico" property="origem.creditoARealizarHistorico.imovel.id"
										size="12" maxlength="10" readonly="true"
										style="background-color:#EFEFEF; border:0" />
									</logic:present>
									
									<logic:notPresent name="creditoARealizarHistorico" property="origem">
										<input type="text" name="imovelOrigem" size="12" maxlength="10"
										readonly="true" style="background-color:#EFEFEF; border:0" />
									</logic:notPresent>
									
								</td>
							</tr>
					</table>
					<table width="100%" border="0">
						<tr>
							<td colspan="3">
							<hr>
							</td>
						</tr>
						</logic:iterate>
					</table>
					</div>
					</td>
				</tr>
			</logic:present>
			<!-- final de CREDITO A REALIZAR HISTORICO -->
			
			<logic:present name="colecaoParcelamentoItem">
				<tr>
					<td colspan="3" height="300">
					<div style="width: 100%; height: 300; overflow: auto;">
					<table width="80%" border="0">
						<logic:iterate name="colecaoParcelamentoItem"
							id="parcelamentoItem" type="ParcelamentoItem">
							<tr>
								<td><strong>Tipo do Crédito:<font color="#FF0000"> </font></strong></td>
								<td colspan="2" align="right">
								<div align="left"><logic:notEmpty name="parcelamentoItem"
									property="creditoARealizarGeral">
									<logic:notEmpty name="parcelamentoItem"
										property="creditoARealizarGeral.creditoARealizar.creditoTipo">
										<html:text name="parcelamentoItem"
											property="creditoARealizarGeral.creditoARealizar.creditoTipo.descricao"
											size="30" maxlength="30" readonly="true"
											style="background-color:#EFEFEF; border:0" />
									</logic:notEmpty>
								</logic:notEmpty></div>
								</td>
							</tr>
							<tr>
								<td><strong>Situa&ccedil;&atilde;o do Crédito a Realizar:</strong></td>
								<td colspan="2" align="right">
								<div align="left"><logic:notEmpty name="parcelamentoItem"
									property="creditoARealizarGeral">
									<logic:notEmpty name="parcelamentoItem"
										property="creditoARealizarGeral.creditoARealizar.debitoCreditoSituacaoAtual">
										<html:text name="parcelamentoItem"
											property="creditoARealizarGeral.creditoARealizar.debitoCreditoSituacaoAtual.descricaoDebitoCreditoSituacao"
											size="30" maxlength="30" readonly="true"
											style="background-color:#EFEFEF; border:0" />
									</logic:notEmpty>
								</logic:notEmpty></div>
								</td>
							</tr>
							<tr>
								<td width="183" height="25"><strong>Usuário:</strong></td>
								<td colspan="3">
									<logic:present name="parcelamentoItem" property="creditoARealizarGeral.creditoARealizar.usuario">
										<html:text name="parcelamentoItem" property="creditoARealizarGeral.creditoARealizar.usuario.nomeUsuario" 
												   size="30" readonly="true" style="background-color:#EFEFEF; border:0" />
									</logic:present>		
									<logic:notPresent name="parcelamentoItem" property="creditoARealizarGeral.creditoARealizar.usuario">
										<input type="text" name="usuario" size="30" readonly="true" style="background-color:#EFEFEF; border:0" />
									</logic:notPresent>
								</td>
							</tr>
							<tr>
								<td><strong>Data e Hora de Gera&ccedil;&atilde;o do Crédito:</strong></td>
								<td colspan="2" align="right">
								<div align="left"><logic:notEmpty name="parcelamentoItem"
									property="creditoARealizarGeral">
									<logic:notEmpty name="parcelamentoItem"
										property="creditoARealizarGeral.creditoARealizar.geracaoCredito">
										<input type="text" size="10" maxlength="10" readonly="true"
											style="background-color:#EFEFEF; border:0"
											value="<bean:write name="parcelamentoItem"  property="creditoARealizarGeral.creditoARealizar.geracaoCredito" formatKey="date.format" />">
										&nbsp; <input type="text" size="8" maxlength="8"
											readonly="true" style="background-color:#EFEFEF; border:0"
											value="<bean:write name="parcelamentoItem"  property="creditoARealizarGeral.creditoARealizar.geracaoCredito" formatKey="hour.format" />">
									</logic:notEmpty>
								</logic:notEmpty>
								</td>
							</tr>
							<tr>
								<td><strong>M&ecirc;s e Ano de Refer&ecirc;ncia do Crédito:</strong></td>
								<td colspan="2" align="right">
								<div align="left"><logic:notEmpty name="parcelamentoItem"
									property="creditoARealizarGeral">
									<logic:notEmpty name="parcelamentoItem"
										property="creditoARealizarGeral.creditoARealizar.anoMesReferenciaCredito">
										<input type="text" size="7" maxlength="7" readonly="true"
											style="background-color:#EFEFEF; border:0"
											value="<%="" + Util.formatarMesAnoReferencia(parcelamentoItem.getCreditoARealizarGeral().getCreditoARealizar().getAnoMesReferenciaCredito())%>">
									</logic:notEmpty>
								</logic:notEmpty>
								</td>
							</tr>
							<tr>
								<td><strong>M&ecirc;s e Ano da Cobran&ccedil;a do Crédito:</strong></td>
								<td colspan="2" align="right">
								<div align="left"><logic:notEmpty name="parcelamentoItem"
									property="creditoARealizarGeral">
									<logic:notEmpty name="parcelamentoItem"
										property="creditoARealizarGeral.creditoARealizar.anoMesCobrancaCredito">
										<input type="text" size="7" maxlength="7" readonly="true"
											style="background-color:#EFEFEF; border:0"
											value="<%="" + Util.formatarMesAnoReferencia(parcelamentoItem.getCreditoARealizarGeral().getCreditoARealizar().getAnoMesCobrancaCredito())%>">
									</logic:notEmpty>
								</logic:notEmpty>
								</td>
							</tr>
							<tr>
								<td><strong>N&uacute;mero de Presta&ccedil;&otilde;es
								Creditadas:<strong></strong></td>
								<td colspan="2" align="right">
								<div align="left"><logic:notEmpty name="parcelamentoItem"
									property="creditoARealizarGeral">
									<logic:notEmpty name="parcelamentoItem"
										property="creditoARealizarGeral.creditoARealizar.numeroPrestacaoRealizada">
										<html:text name="parcelamentoItem"
											property="creditoARealizarGeral.creditoARealizar.numeroPrestacaoRealizada"
											size="7" maxlength="7" readonly="true"
											style="background-color:#EFEFEF; border:0; text-align: right;" />
									</logic:notEmpty>
								</logic:notEmpty></div>
								</td>
							</tr>
							<tr>
								<td><strong>N&uacute;mero Total de Presta&ccedil;&otilde;es:<strong><font
									color="#FF0000"> </font></strong></strong></td>
								<td colspan="2" align="right">
								<div align="left"><logic:notEmpty name="parcelamentoItem"
									property="creditoARealizarGeral">
									<logic:notEmpty name="parcelamentoItem"
										property="creditoARealizarGeral.creditoARealizar.numeroPrestacaoCreditoMenosBonus">
										<html:text name="parcelamentoItem"
											property="creditoARealizarGeral.creditoARealizar.numeroPrestacaoCreditoMenosBonus"
											size="7" maxlength="7" readonly="true"
											style="background-color:#EFEFEF; border:0; text-align: right;" />
									</logic:notEmpty>
								</logic:notEmpty></div>
								</td>
							</tr>
							<tr>
								<td><strong>Valor Total do Crédito:<strong></strong></strong></td>
								<td colspan="2" align="right">
								<div align="left"><logic:notEmpty name="parcelamentoItem"
									property="creditoARealizarGeral">
									<logic:notEmpty name="parcelamentoItem"
										property="creditoARealizarGeral.creditoARealizar.valorCredito">
										<input type="text" size="17" maxlength="17" readonly="true"
											style="background-color:#EFEFEF; border:0; text-align: right;"
											value="<%="" + Util.formatarMoedaReal(parcelamentoItem.getCreditoARealizarGeral().getCreditoARealizar().getValorCredito())%>">
									</logic:notEmpty>
								</logic:notEmpty></div>
								</td>
							</tr>
							<tr>
								<td><strong>Valor Restante a Ser Creditado:<strong></strong></strong></td>
								<td colspan="2" align="right">
								<div align="left"><logic:notEmpty name="parcelamentoItem"
									property="creditoARealizarGeral">
									<logic:notEmpty name="parcelamentoItem"
										property="creditoARealizarGeral.creditoARealizar.valorTotalComBonus">
										<input type="text" size="17" maxlength="17" readonly="true"
											style="background-color:#EFEFEF; border:0; text-align: right;"
											value="<%="" + Util.formatarMoedaReal(parcelamentoItem.getCreditoARealizarGeral().getCreditoARealizar().getValorTotalComBonus())%>">
									</logic:notEmpty>
								</logic:notEmpty></div>
								</td>
							</tr>
							<tr>
								<td><strong>Registro de Atendimento:<strong><font
									color="#FF0000"> </font></strong><font color="#FF0000"> </font></strong></td>
								<td colspan="2" align="left"><logic:notEmpty
									name="parcelamentoItem" property="creditoARealizarGeral">
									<logic:notEmpty name="parcelamentoItem"
										property="creditoARealizarGeral.creditoARealizar.registroAtendimento">
										<html:text name="parcelamentoItem"
											property="creditoARealizarGeral.creditoARealizar.registroAtendimento.id"
											size="9" readonly="true"
											style="background-color:#EFEFEF; border:0; text-align: right;" />
									</logic:notEmpty>
								</logic:notEmpty> &nbsp;</td>
							</tr>
							<tr>
								<td><strong>Ordem de Servi&ccedil;o:<strong><font
									color="#FF0000"> </font></strong><font color="#FF0000"> </font></strong></td>
								<td colspan="2" align="left"><logic:notEmpty
									name="parcelamentoItem" property="creditoARealizarGeral">
									<logic:notEmpty name="parcelamentoItem"
										property="creditoARealizarGeral.creditoARealizar.ordemServico">
										<html:text name="parcelamentoItem"
											property="creditoARealizarGeral.creditoARealizar.ordemServico.id"
											size="9" readonly="true"
											style="background-color:#EFEFEF; border:0; text-align: right;" />
									</logic:notEmpty>
								</logic:notEmpty> &nbsp;</td>
							</tr>
							<tr>
								<td><strong>Origem do Crédito:<font color="#FF0000"></font></strong></td>
								<td colspan="2" align="right">
								<div align="left"><logic:notEmpty name="parcelamentoItem"
									property="creditoARealizarGeral">
									<logic:notEmpty name="parcelamentoItem"
										property="creditoARealizarGeral.creditoARealizar.creditoOrigem">
										<html:text name="parcelamentoItem"
											property="creditoARealizarGeral.creditoARealizar.creditoOrigem.descricaoCreditoOrigem"
											size="45" maxlength="45" readonly="true"
											style="background-color:#EFEFEF; border:0" />
									</logic:notEmpty>
								</logic:notEmpty></div>
								</td>
							</tr>
							
							<tr>
								<td><strong>Matrícula do Imóvel Origem:</strong></td>
								<td colspan="2">
									
									<logic:present name="parcelamentoItem" property="creditoARealizarGeral.creditoARealizar.origem">
										<html:text name="parcelamentoItem" property="creditoARealizarGeral.creditoARealizar.origem.creditoARealizar.imovel.id"
										size="12" maxlength="10" readonly="true"
										style="background-color:#EFEFEF; border:0" />
									</logic:present>
									
									<logic:notPresent name="parcelamentoItem" property="creditoARealizarGeral.creditoARealizar.origem">
										<input type="text" name="imovelOrigem" size="12" maxlength="10"
										readonly="true" style="background-color:#EFEFEF; border:0" />
									</logic:notPresent>
									
								</td>
							</tr>
					</table>
					<table width="100%" border="0">
						<tr>
							<td colspan="3">
							<hr>
							</td>
						</tr>
						</logic:iterate>
					</table>
					</div>
					</td>
				</tr>
			</logic:present>
			<tr>
				<td colspan="3">&nbsp;</td>
			</tr>
			<table width="100%" border="0">

				<tr>
					<td height="24"><logic:present
						name="caminhoRetornoTelaConsultaCreditoARealizar">
						<input type="button" class="bottonRightCol" value="Voltar"
							style="width: 70px;" onclick="javascript:history.back();" />
					</logic:present>
					<td align="right"><input name="Button" type="button"
						class="bottonRightCol" value="Fechar"
						onClick="javascript:fechar();"></td>
				</tr>
			</table>
		</table>
		<p>&nbsp;</p>
		</td>
	</tr>
</table>
</body>
</html:html>
