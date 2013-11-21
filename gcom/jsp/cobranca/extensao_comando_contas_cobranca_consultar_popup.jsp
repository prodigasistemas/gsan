<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

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
	src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>

</head>

<body leftmargin="5" topmargin="5" onload="resizePageSemLink(750, 570);">


<html:form action="/exibirConsultarContasComandoCobrancaPopupAction"
	type="gcom.gui.cobranca.GerarArquivoTextoContasCobrancaEmpresaActionForm"
	name="GerarArquivoTextoContasCobrancaEmpresaActionForm" method="post">


	<table width="700" border="0" cellspacing="5" cellpadding="0">
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
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_left.gif" /></td>
					<td class="parabg">Comando de Contas em Cobrança por Empresa</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>

			<p>&nbsp;</p>

			<p>&nbsp;</p>

			<table width="100%" border="0">
				<tr>
					<td width="35%"><strong>Empresa:</strong></td>
					<td colspan="3"><html:text property="nomeEmpresa" size="60"
						maxlength="50" readonly="true"
						style="background-color:#EFEFEF; border:0; font-color: #000000" />
					</td>
				</tr>
				<tr>
					<td width="35%"><strong>Data de Execução do Comando:</strong></td>
					<td colspan="3"><html:text property="dataExecucaoComando" size="12"
						maxlength="10" readonly="true"
						style="background-color:#EFEFEF; border:0; font-color: #000000" />
					</td>
				</tr>
				<tr>
					<td width="35%"><strong>Período de Referência das Contas:</strong></td>
					<td colspan="3"><html:text
						property="periodoReferenciaContasInicial" size="7" maxlength="7"
						readonly="true"
						style="background-color:#EFEFEF; border:0; font-color: #000000" />
					a <html:text property="periodoReferenciaContasFinal" size="7"
						maxlength="7" readonly="true"
						style="background-color:#EFEFEF; border:0; font-color: #000000" />
					</td>
				</tr>
				<tr>
					<td width="35%"><strong>Período de Vencimento das Contas:</strong></td>
					<td colspan="3"><html:text
						property="periodoVencimentoContasInicial" size="12" maxlength="10"
						readonly="true"
						style="background-color:#EFEFEF; border:0; font-color: #000000" />
					a <html:text property="periodoVencimentoContasFinal" size="12"
						maxlength="10" readonly="true"
						style="background-color:#EFEFEF; border:0; font-color: #000000" />
					</td>
				</tr>
				<tr>
					<td width="35%"><strong>Intervalo de Valor das Contas:</strong></td>
					<td colspan="3"><html:text property="intervaloValorContasInicial"
						size="12" maxlength="10" readonly="true"
						style="background-color:#EFEFEF; border:0; font-color: #000000; text-align:right" />
					a <html:text property="intervaloValorContasFinal" size="12"
						maxlength="10" readonly="true"
						style="background-color:#EFEFEF; border:0; font-color: #000000; text-align:right" />
					</td>
				</tr>
				<tr>
					<td width="35%"><strong>Imóvel:</strong></td>
					<td colspan="3"><html:text property="idImovel" size="12"
						maxlength="10" readonly="true"
						style="background-color:#EFEFEF; border:0; font-color: #000000" />
					</td>
				</tr>
				<tr>
					<td width="35%"><strong>Cliente:</strong></td>
					<td colspan="3"><html:text property="nomeCliente" size="60"
						maxlength="50" readonly="true"
						style="background-color:#EFEFEF; border:0; font-color: #000000" />
					</td>
				</tr>
				<tr>
					<td width="35%"><strong>Unidade de Negócio:</strong></td>
					<td colspan="3"><html:text property="idUnidadeNegocio" size="10"
						maxlength="10" readonly="true"
						style="background-color:#EFEFEF; border:0; font-color: #000000" />
					<html:text property="nomeUnidadeNegocio" size="50" maxlength="40"
						readonly="true"
						style="background-color:#EFEFEF; border:0; font-color: #000000" />
					</td>
				</tr>
				<tr>
					<td width="35%"><strong>Intervalo de Localização:</strong></td>
					<td colspan="3"><html:text property="intervaloLocalizacaoInicial"
						size="5" maxlength="4" readonly="true"
						style="background-color:#EFEFEF; border:0; font-color: #000000" />
					a <html:text property="intervaloLocalizacaoFinal" size="5"
						maxlength="4" readonly="true"
						style="background-color:#EFEFEF; border:0; font-color: #000000" />
					</td>
				</tr>
				<tr>
					<td width="35%"><strong>Intervalo de Setor Comercial:</strong></td>
					<td colspan="3"><html:text
						property="intervaloSetorComercialInicial" size="5" maxlength="4"
						readonly="true"
						style="background-color:#EFEFEF; border:0; font-color: #000000" />
					a <html:text property="intervaloSetorComercialFinal" size="5"
						maxlength="4" readonly="true"
						style="background-color:#EFEFEF; border:0; font-color: #000000" />
					</td>
				</tr>
				<tr>
					<td width="35%"><strong>Quantidade Total de Contas Selecionadas
					para Cobrança:</strong></td>
					<td colspan="3"><html:text property="qtdeTotalContasCobranca"
						size="8" maxlength="8" readonly="true"
						style="background-color:#EFEFEF; border:0; font-color: #000000" />
					</td>
				</tr>
				<tr>
					<td width="35%"><strong>Valor Total de Contas Selecionadas para
					Cobrança:</strong></td>
					<td colspan="3"><html:text property="valorTotalContasCobranca"
						size="12" maxlength="12" readonly="true"
						style="background-color:#EFEFEF; border:0; font-color: #000000; text-align:right" />
					</td>
				</tr>
				<tr>
					<td width="35%"><strong>Quantidade Total de Contas Selecionadas
					pelos Critérios de Comando:</strong></td>
					<td colspan="3"><html:text property="qtdeContasCriterioComando"
						size="8" maxlength="8" readonly="true"
						style="background-color:#EFEFEF; border:0; font-color: #000000" />
					</td>
				</tr>
				<tr>
					<td width="35%"><strong>Valor Total de Contas Selecionadas pelos
					Critérios de Comando:</strong></td>
					<td colspan="3"><html:text property="valorContasCriterioComando"
						size="12" maxlength="12" readonly="true"
						style="background-color:#EFEFEF; border:0; font-color: #000000; text-align:right" />
					</td>
				</tr>

				<tr>
					<td>&nbsp;</td>
					<td colspan="1" align="right"><input type="button" name="fechar"
						class="bottonRightCol" value="Fechar" onClick="window.close();" /></td>
				</tr>
			</table>

			<table width="100%" border="0">
				<tr>
					<td align="left"></td>
				</tr>
			</table>


			<p>&nbsp;</p>
	</table>

</html:form>

</body>
</html:html>
