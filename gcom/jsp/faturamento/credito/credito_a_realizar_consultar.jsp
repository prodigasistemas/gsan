
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<%@ page import="gcom.util.Util"%>
<%@ page
	import="gcom.faturamento.credito.CreditoARealizar,gcom.cobranca.parcelamento.ParcelamentoItem,gcom.faturamento.credito.CreditoARealizarGeral,gcom.faturamento.credito.CreditoARealizarHistorico"
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
				<td class="parabg">Consultar Cr�ditos A Realizar</td>
				<td width="11"><img border="0"
					src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
			</tr>
		</table>
		<p>&nbsp;</p>
		<table width="100%" border="0">
			<tr>
				<td width="20%"><strong>C�digo do Im�vel:</strong></td>
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
								<td><strong>Tipo do Cr�dito:<font color="#FF0000"> </font></strong></td>
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
								<td><strong>Situa&ccedil;&atilde;o do Cr�dito a Realizar:</strong></td>
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
								<td width="183" height="25"><strong>Usu�rio:</strong></td>
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
								<td><strong>Data e Hora de Gera&ccedil;&atilde;o do Cr�dito:</strong></td>
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
								<td><strong>M&ecirc;s e Ano de Refer&ecirc;ncia do Cr�dito:</strong></td>
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
								<td><strong>M&ecirc;s e Ano da Cobran&ccedil;a do Cr�dito:</strong></td>
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
								<td><strong>Valor Total do Cr�dito:<strong></strong></strong></td>
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
								<td><strong>Origem do Cr�dito:<font color="#FF0000"></font></strong></td>
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
								<td><strong>Matr�cula do Im�vel Origem:</strong></td>
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
								<td><strong>Tipo do Cr�dito:<font color="#FF0000"> </font></strong></td>
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
								<td><strong>Situa&ccedil;&atilde;o do Cr�dito a Realizar:</strong></td>
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
								<td width="183" height="25"><strong>Usu�rio:</strong></td>
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
								<td><strong>Data e Hora de Gera&ccedil;&atilde;o do Cr�dito:</strong></td>
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
								<td><strong>M&ecirc;s e Ano de Refer&ecirc;ncia do Cr�dito:</strong></td>
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
								<td><strong>M&ecirc;s e Ano da Cobran&ccedil;a do Cr�dito:</strong></td>
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
								<td><strong>Valor Total do Cr�dito:<strong></strong></strong></td>
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
								<td><strong>Origem do Cr�dito:<font color="#FF0000"></font></strong></td>
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
								<td><strong>Matr�cula do Im�vel Origem:</strong></td>
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
						<logic:iterate name="colecaoParcelamentoItem" id="parcelamentoItem" type="ParcelamentoItem">
						
							<bean:define name="parcelamentoItem" property="creditoARealizarGeral" id="creditoARealizarGeral" /> 
							<tr>
								<td><strong>Tipo do Cr�dito:<font color="#FF0000"> </font></strong></td>
								<td colspan="2" align="right">
								<div align="left">
								
								<logic:notEmpty name="parcelamentoItem" property="creditoARealizarGeral">
								<logic:present name="creditoARealizarGeral" property="creditoARealizar">
											<bean:define name="creditoARealizarGeral" property="creditoARealizar" id="creditoARealizar" /> 
											<bean:define name="creditoARealizar" property="creditoTipo" id="creditoTipo" /> 
											<bean:write name="creditoTipo" property="descricao" />
								</logic:present>
								<logic:notPresent name="creditoARealizarGeral" property="creditoARealizar">
											<bean:define name="creditoARealizarGeral" property="creditoARealizarHistorico" id="creditoARealizarHistorico" /> 
											<bean:define name="creditoARealizarHistorico" property="creditoTipo" id="creditoTipo" /> 
											<bean:write name="creditoTipo" property="descricao" />
								</logic:notPresent>					
			
								</logic:notEmpty></div>
								</td>
							</tr>
							<tr>
								<td><strong>Situa&ccedil;&atilde;o do Cr�dito a Realizar:</strong></td>
								<td colspan="2" align="right">
								<div align="left">
								<logic:notEmpty name="parcelamentoItem" property="creditoARealizarGeral">
								<logic:present name="creditoARealizarGeral" property="creditoARealizar">
											<bean:define name="creditoARealizarGeral" property="creditoARealizar" id="creditoARealizar" /> 
											<bean:define name="creditoARealizar" property="debitoCreditoSituacaoAtual" id="debitoCreditoSituacaoAtual" /> 
											<bean:write name="debitoCreditoSituacaoAtual" property="descricaoDebitoCreditoSituacao" />
								</logic:present>
								<logic:notPresent name="creditoARealizarGeral" property="creditoARealizar">
											<bean:define name="creditoARealizarGeral" property="creditoARealizarHistorico" id="creditoARealizarHistorico" /> 
											<bean:define name="creditoARealizarHistorico" property="debitoCreditoSituacaoAtual" id="debitoCreditoSituacaoAtual" /> 
											<bean:write name="debitoCreditoSituacaoAtual" property="descricaoDebitoCreditoSituacao" />
								</logic:notPresent>					
															
								</logic:notEmpty></div>
								</td>
							</tr>
							
							
							<tr>							
							  <td width="183" height="25"><strong>Usu�rio:</strong></td>
							  <td colspan="3">
							   	<logic:present name="creditoARealizarGeral" property="creditoARealizar">
							   		 	<bean:define name="creditoARealizarGeral" property="creditoARealizar" id="creditoARealizar" /> 
									<logic:present name="creditoARealizar" property="usuario">
									 	<bean:define name="creditoARealizar" property="usuario" id="usuario" /> 
								 			<bean:write name="usuario" property="nomeUsuario"/>
							        </logic:present>
   
							    	<logic:notPresent name="creditoARealizar" property="usuario">
							        	<input type="text" name="usuario" size="30" readonly="true" style="background-color:#EFEFEF; border:0" />
							     	</logic:notPresent>
							     </logic:present>   
							     
							    <logic:notPresent name="creditoARealizarGeral" property="creditoARealizar">
							  			<bean:define name="creditoARealizarGeral" property="creditoARealizarHistorico" id="creditoARealizarHistorico" /> 
							   		<logic:present name="creditoARealizarHistorico" property="usuario">
							   			<bean:define name="creditoARealizarHistorico" property="usuario" id="usuario" /> 
							    		<bean:write name="usuario" property="nomeUsuario" />
							     	</logic:present>    
							      	<logic:notPresent name="creditoARealizarHistorico" property="usuario">
							        		<input type="text" name="usuario" size="30" readonly="true" style="background-color:#EFEFEF; border:0" />
							     	</logic:notPresent>
							    </logic:notPresent>
							  </td>
							</tr>
							<tr>
							  <td><strong>Data e Hora de Gera&ccedil;&atilde;o do Cr�dito:</strong></td>
							  <td colspan="2" align="right">
							  <div align="left">
							  
				   		       <logic:present name="creditoARealizarGeral" property="creditoARealizar">
							   		<logic:present name="creditoARealizar" property="geracaoCredito">
							   			<bean:define name="creditoARealizar" property="geracaoCredito" id="geracaoCredito" />  		
											<input type="text" size="10" maxlength="10" readonly="true" style="background-color:#EFEFEF; border:0" value="<bean:write name="creditoARealizar" property="geracaoCredito" 
							         			 formatKey="date.format" />"> &nbsp; 
							         	    <input type="text" size="8" maxlength="8"readonly="true" style="background-color:#EFEFEF; border:0" value="<bean:write name="creditoARealizar" property="geracaoCredito" formatKey="hour.format" />">
									</logic:present>
								</logic:present>
							    
							    <logic:notPresent name="creditoARealizarGeral" property="creditoARealizar">
									<logic:present name="creditoARealizarHistorico" property="geracaoCreditoARealizar">
										<bean:define name="creditoARealizarHistorico" property="geracaoCreditoARealizar" id="geracaoCreditoARealizar" />  
											<input type="text" size="10" maxlength="10" readonly="true" style="background-color:#EFEFEF; border:0" value="<bean:write name="creditoARealizarHistorico" property="geracaoCreditoARealizar" 
							         			 formatKey="date.format" />"> &nbsp; 
							         	    <input type="text" size="8" maxlength="8"readonly="true" style="background-color:#EFEFEF; border:0" value="<bean:write name="creditoARealizarHistorico" property="geracaoCreditoARealizar" formatKey="hour.format" />">
									</logic:present>
								</logic:notPresent>

							  </td>
							</tr>
							<tr>
							  <td><strong>M&ecirc;s e Ano de Refer&ecirc;ncia do Cr�dito:</strong></td>
							  <td colspan="2" align="right">
							  <div align="left">
							  
							     <logic:present name="creditoARealizarGeral" property="creditoARealizar">
									<logic:present name="creditoARealizar" property="anoMesReferenciaCredito">
										<bean:define name="creditoARealizar" property="anoMesReferenciaCredito" id="anoMesReferenciaCredito" /> 
											 <input type="text" size="7" maxlength="7" readonly="true" style="background-color:#EFEFEF; border:0"
							           			 value="<%="" + Util.formatarMesAnoReferencia(parcelamentoItem.getCreditoARealizarGeral().getCreditoARealizar().getAnoMesReferenciaCredito())%>">
									</logic:present>
								</logic:present>
							  	
							  	 <logic:notPresent name="creditoARealizarGeral" property="creditoARealizar">
									<logic:present name="creditoARealizarHistorico" property="anoMesReferenciaCredito">
										<bean:define name="creditoARealizarHistorico" property="anoMesReferenciaCredito" id="anoMesReferenciaCredito" />  
										  <input type="text" size="7" maxlength="7" readonly="true" style="background-color:#EFEFEF; border:0"
							           			 value="<%="" + Util.formatarMesAnoReferencia(parcelamentoItem.getCreditoARealizarGeral().getCreditoARealizarHistorico().getAnoMesReferenciaCredito())%>">
									</logic:present>
								</logic:notPresent>
							 </td>
							</tr>
							<tr>
							  <td><strong>M&ecirc;s e Ano da Cobran&ccedil;a do Cr�dito:</strong></td>
							  <td colspan="2" align="right">
							  <div align="left">
							  
							   <logic:present name="creditoARealizarGeral" property="creditoARealizar">
							   		<logic:present name="creditoARealizar" property="anoMesCobrancaCredito">
										<bean:define name="creditoARealizar" property="anoMesCobrancaCredito" id="anoMesCobrancaCredito" />  
											<input type="text" size="7" maxlength="7" readonly="true" style="background-color:#EFEFEF; border:0"
							           			 value="<%="" + Util.formatarMesAnoReferencia(parcelamentoItem.getCreditoARealizarGeral().getCreditoARealizar().getAnoMesCobrancaCredito())%>">
									</logic:present>
								</logic:present>
							  	
							  	 <logic:notPresent name="creditoARealizarGeral" property="creditoARealizar">
									<logic:present name="creditoARealizarHistorico" property="anoMesCobrancaCredito">
										<bean:define name="creditoARealizarHistorico" property="anoMesCobrancaCredito" id="anoMesCobrancaCredito" />  
											<input type="text" size="7" maxlength="7" readonly="true" style="background-color:#EFEFEF; border:0"
							           			 value="<%="" + Util.formatarMesAnoReferencia(parcelamentoItem.getCreditoARealizarGeral().getCreditoARealizarHistorico().getAnoMesCobrancaCredito())%>">
									</logic:present>
								</logic:notPresent>
							  </td>
							</tr>
							<tr>
							
							
							  <td><strong>N&uacute;mero de Presta&ccedil;&otilde;es Creditadas:<strong></strong></td>
							  <td colspan="2" align="right">
							    <div align="left">
							    
							    <logic:present name="creditoARealizarGeral" property="creditoARealizar">
										<logic:present name="creditoARealizar" property="numeroPrestacaoRealizada">
											<bean:define name="creditoARealizar" property="numeroPrestacaoRealizada" id="numeroPrestacaoRealizada" />  
											 <html:text name="creditoARealizar" property="numeroPrestacaoRealizada" size="7" maxlength="7" readonly="true"
							             		 style="background-color:#EFEFEF; border:0; text-align: right;" />
									</logic:present>
								</logic:present>
								<logic:notPresent name="creditoARealizarGeral" property="creditoARealizar">
									<logic:present name="creditoARealizarHistorico" property="numeroPrestacaoRealizada">
											<bean:define name="creditoARealizarHistorico" property="numeroPrestacaoRealizada" id="numeroPrestacaoRealizada" />  
											 <html:text name="creditoARealizarHistorico" property="numeroPrestacaoRealizada" size="7" maxlength="7" readonly="true"
							             		 style="background-color:#EFEFEF; border:0; text-align: right;" />
									</logic:present>
								</logic:notPresent>
							   
							    </div>
							  </td>
							</tr>
							<tr>
							  <td><strong>N&uacute;mero Total de Presta&ccedil;&otilde;es:<strong><font
							    color="#FF0000"> </font></strong></strong></td>
							  <td colspan="2" align="right">
							  <div align="left">
							  
							   <logic:present name="creditoARealizarGeral" property="creditoARealizar">
									<logic:present name="creditoARealizar" property="numeroPrestacaoCreditoMenosBonus">
										<bean:define name="creditoARealizar" property="numeroPrestacaoCreditoMenosBonus" id="numeroPrestacaoCreditoMenosBonus" />  
											  <html:text name="creditoARealizar" property="numeroPrestacaoCreditoMenosBonus" size="7" maxlength="7" readonly="true"
							             			 style="background-color:#EFEFEF; border:0; text-align: right;" />
								 		</logic:present>
								 </logic:present>
								 	<logic:notPresent name="creditoARealizarGeral" property="creditoARealizar">
								  		<logic:present name="creditoARealizarHistorico" property="numeroPrestacaoCreditoMenosBonus">
											<bean:define name="creditoARealizarHistorico" property="numeroPrestacaoCreditoMenosBonus" id="numeroPrestacaoCreditoMenosBonus" />  
											 <html:text name="creditoARealizarHistorico" property="numeroPrestacaoCreditoMenosBonus" size="7" maxlength="7" readonly="true"
							             		 style="background-color:#EFEFEF; border:0; text-align: right;" />
									</logic:present>
								</logic:notPresent>
				
							  </div>
							  </td>
							</tr>
							<tr>
							  <td><strong>Valor Total do Cr�dito:<strong></strong></strong></td>
							  <td colspan="2" align="right">
							    <div align="left">
							    <logic:present name="creditoARealizarGeral" property="creditoARealizar">
									<logic:present name="creditoARealizar" property="valorCredito">
										<bean:define name="creditoARealizar" property="valorCredito" id="valorCredito" />  
											   <input type="text" size="17" maxlength="17" readonly="true" style="background-color:#EFEFEF; border:0; text-align: right;"
							              		value="<%="" + Util.formatarMoedaReal(parcelamentoItem.getCreditoARealizarGeral().getCreditoARealizar().getValorCredito())%>">
								 		</logic:present>
								 </logic:present>
								 	<logic:notPresent name="creditoARealizarGeral" property="creditoARealizar">
								 		<logic:present name="creditoARealizarHistorico" property="valorCredito">
								 			<bean:define name="creditoARealizarHistorico" property="valorCredito" id="valorCredito" />  
											 <input type="text" size="17" maxlength="17" readonly="true" style="background-color:#EFEFEF; border:0; text-align: right;"
							             		 value="<%="" + Util.formatarMoedaReal(parcelamentoItem.getCreditoARealizarGeral().getCreditoARealizarHistorico().getValorCredito())%>">
									</logic:present>
								</logic:notPresent>
							   
							    </div>
							  </td>
							</tr>
							<tr>
							  <td><strong>Valor Restante a Ser Creditado:<strong></strong></strong></td>
							  <td colspan="2" align="right">
							    <div align="left">
							    
							    <logic:present name="creditoARealizarGeral" property="creditoARealizar">
									<logic:present name="creditoARealizar" property="valorTotalComBonus">
										<bean:define name="creditoARealizar" property="valorTotalComBonus" id="valorTotalComBonus" />  
											              <input type="text" size="17" maxlength="17" readonly="true"style="background-color:#EFEFEF; border:0; text-align: right;"
							              value="<%="" + Util.formatarMoedaReal(parcelamentoItem.getCreditoARealizarGeral().getCreditoARealizar().getValorTotalComBonus())%>">
								 		</logic:present>
								 </logic:present>
								 	<logic:notPresent name="creditoARealizarGeral" property="creditoARealizar">
										<logic:present name="creditoARealizarHistorico" property="valorTotalComBonus">
											<bean:define name="creditoARealizarHistorico" property="valorTotalComBonus" id="valorTotalComBonus" />  
											     <input type="text" size="17" maxlength="17" readonly="true" style="background-color:#EFEFEF; border:0; text-align: right;"
							              value="<%="" + Util.formatarMoedaReal(parcelamentoItem.getCreditoARealizarGeral().getCreditoARealizarHistorico().getValorTotalComBonus())%>">
									</logic:present>
								</logic:notPresent>
						
							    </div>
							  </td>
							</tr>
							<tr>
							  <td><strong>Registro de Atendimento:<strong><font
							    color="#FF0000"> </font></strong><font color="#FF0000"> </font></strong></td>
							  <td colspan="2" align="left">
							  
							  	    <logic:present name="creditoARealizarGeral" property="creditoARealizar">
										<logic:present name="creditoARealizar" property="registroAtendimento">
											<bean:define name="creditoARealizar" property="registroAtendimento" id="registroAtendimento" />  
											<bean:define name="registroAtendimento" property="id" id="id" /> 
											             <html:text name="registroAtendimento"	property="id" size="9" readonly="true" style="background-color:#EFEFEF; border:0; text-align: right;" />
								 		</logic:present>
								 	</logic:present>
								 	<logic:notPresent name="creditoARealizarGeral" property="creditoARealizar">
										<logic:present name="creditoARealizarHistorico" property="registroAtendimento">
											<bean:define name="creditoARealizarHistorico" property="registroAtendimento" id="registroAtendimento" />  
											<bean:define name="registroAtendimento" property="id" id="id" /> 
											     <html:text name="registroAtendimento"	property="id" size="9" readonly="true" style="background-color:#EFEFEF; border:0; text-align: right;" />
										</logic:present>
									</logic:notPresent>
				
							</tr>
							<tr>
							  <td><strong>Ordem de Servi&ccedil;o:<strong><font
							    color="#FF0000"> </font></strong><font color="#FF0000"> </font></strong></td>
							  <td colspan="2" align="left">
							  
							    	<logic:present name="creditoARealizarGeral" property="creditoARealizar">
										<logic:present name="creditoARealizar" property="ordemServico">
											<bean:define name="creditoARealizar" property="ordemServico" id="ordemServico" />  
											<bean:define name="ordemServico" property="id" id="id" /> 
											             <html:text name="ordemServico"	property="id" size="9" readonly="true" style="background-color:#EFEFEF; border:0; text-align: right;" />
								 		</logic:present>
								 	</logic:present>
								 	<logic:notPresent name="creditoARealizarGeral" property="creditoARealizar">
										<logic:present name="creditoARealizarHistorico" property="ordemServico">
											<bean:define name="creditoARealizarHistorico" property="ordemServico" id="ordemServico" />  
											<bean:define name="ordemServico" property="id" id="id" /> 
											     <html:text name="ordemServico"	property="id" size="9" readonly="true" style="background-color:#EFEFEF; border:0; text-align: right;" />
										</logic:present>
									</logic:notPresent>
							  
							</tr>
							<tr>
							  <td><strong>Origem do Cr�dito:<font color="#FF0000"></font></strong></td>
							  <td colspan="2" align="right">
							  <div align="left">
							  
							  	   <logic:present name="creditoARealizarGeral" property="creditoARealizar">
										<logic:present name="creditoARealizar" property="creditoOrigem">
											 <bean:define name="creditoARealizar" property="creditoOrigem" id="creditoOrigem" />
												 <html:text name="creditoOrigem"	property="descricaoCreditoOrigem" size="45" maxlength="45" readonly="true" style="background-color:#EFEFEF; border:0" />
								 		</logic:present>
									</logic:present>
								 	<logic:notPresent name="creditoARealizarGeral" property="creditoARealizar">
										<logic:present name="creditoARealizarHistorico" property="creditoOrigem">
											<bean:define name="creditoARealizarHistorico" property="creditoOrigem" id="creditoOrigem" />  
												<html:text name="creditoOrigem"	property="descricaoCreditoOrigem" size="45" maxlength="45" readonly="true" style="background-color:#EFEFEF; border:0" />
										</logic:present>
									</logic:notPresent>
							 </td>
							</tr>
							<tr>
							  <td><strong>Matrcula do Im�vel Origem:</strong></td>
							  <td colspan="2">
							  
							   	    <logic:present name="creditoARealizarGeral" property="creditoARealizar">
										<logic:present name="creditoARealizar" property="origem">
											<bean:define name="creditoARealizar" property="origem" id="origem" />  
											<bean:define name="origem" property="creditoARealizar" id="creditoARealizar" /> 
											<bean:define name="creditoARealizar" property="imovel" id="imovel" /> 
											<bean:define name="imovel" property="id" id="id" /> 
											              <html:text name="imovel" property="id" size="12" maxlength="10" readonly="true" style="background-color:#EFEFEF; border:0" />
								 		</logic:present>
								 		<logic:notPresent name="creditoARealizar" property="origem">
								 						   <input type="text" name="imovelOrigem" size="12" maxlength="10" readonly="true" style="background-color:#EFEFEF; border:0" />
								 		</logic:notPresent>
									 </logic:present>
							  	
								 	<logic:notPresent name="creditoARealizarGeral" property="creditoARealizar">
										<logic:present name="creditoARealizarHistorico" property="origem">
											<bean:define name="creditoARealizarHistorico" property="origem" id="origem" /> 
											<bean:define name="origem" property="creditoARealizarHistorico" id="creditoARealizarHistorico" /> 
											<bean:define name="creditoARealizarHistorico" property="imovel" id="imovel" /> 
												<html:text name="imovel" property="id" size="12" maxlength="10" readonly="true" style="background-color:#EFEFEF; border:0" />
										</logic:present>
										<logic:notPresent name="creditoARealizar" property="origem">
								 						   <input type="text" name="imovelOrigem" size="12" maxlength="10" readonly="true" style="background-color:#EFEFEF; border:0" />
								 		</logic:notPresent>
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
