<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<%@ page import="gcom.util.ConstantesSistema"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
<head>
<%@ include file="/jsp/util/titulo.jsp"%>

<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">

<html:javascript formName="InformarSistemaParametrosActionForm" dynamicJavascript="false" staticJavascript="true" />

<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script>

	var bCancel = false;

	function validateInformarSistemaParametrosActionForm(form) {
		if (bCancel){
			return true;
		}else{
			return  validateRequired(form) && 
			validateInteger(form);
		}
	}
	
	function IntegerValidations () {
		this.aa = new Array("codigoEmpresaFebraban", "Código da Empresa para FEBRABAN deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
		this.ab = new Array("numeroLayOut", "Número do Lau-out da FEBRABAN deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
		this.ac = new Array("maximoParcelas", "Máximo de Parcelas para um Financiamento deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
		this.ad = new Array("numeroMaximoParcelaCredito", "Número Máximo para Parcela de Crédito deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
	}

	function required () {
		this.aa = new Array("mesAnoReferenciaArrecadacao", "Informe Mês e Ano de Referência.", new Function ("varName", " return this[varName];"));
	}

</script>
</head>

<body leftmargin="5" topmargin="5"
	onload="javascript:setarFoco('${requestScope.nomeCampo}');">

<html:form action="/informarParametrosSistemaWizardAction" method="post" 
	onsubmit="return validateInformarSistemaParametrosActionForm(this);">

	<jsp:include page="/jsp/util/wizard/navegacao_abas_wizard_valida_avancar.jsp?numeroPagina=3" />

	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>

	<input type="hidden" name="numeroPagina" value="3" />
	<table width="770" border="0" cellspacing="5" cellpadding="0">
		<tr>
			<td width="150" valign="top" class="leftcoltext">
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
			<td width="655" valign="top" class="centercoltext">
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
					<td class="parabg">Informar Parâmetros do Sistema</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<table width="100%" border="0" dwcopytype="CopyTableRow">
				<tr>
					<td>Para informar parâmetros do sistema, informe os dados abaixo:
					<td align="right"><a
						href="javascript: abrirPopup('/gsan/help/help.jsp?mapIDHelpSet=clienteInserirAbaNomeTipo', 500, 700);"><span
						style="font-weight: bold"><font color="#3165CE">Ajuda</font></span></a></td>
				</tr>
			</table>

			<table width="100%" border="0">


				<tr>
					<td>&nbsp;</td>
				</tr>
				
				<tr>
					<td colspan="2" align="center"><strong>Parâmetros para Arrecadação:</strong></td>
				</tr>

				<tr>
					<td>&nbsp;</td>
				</tr>
				
				<tr>
					<td width="40%" align="left">
						<strong>Mês e Ano de Referência:</strong><font color="#FF0000">*</font>
					</td>
					<td width="82%">
						<html:text property="mesAnoReferenciaArrecadacao"
							size="7" 
							maxlength="7"
							onkeyup="javascript:mascaraAnoMes(this,event);" 
							onkeypress="return isCampoNumerico(event);" disabled="true"/><strong>mm/aaaa</strong>
					</td>
				</tr>
				
				<tr>
					<td width="40%" align="left">
						<strong>Código da Empresa para FEBRABAN:</strong>
					</td>
					<td>
						<html:text maxlength="4" 
							property="codigoEmpresaFebraban"
							size="4" 
							onkeyup="javascript:verificaNumeroInteiro(this);" />
					</td>
				</tr>
				
				<tr>
					<td width="40%" align="left">
						<strong>Número do Lay-out da FEBRABAN:</strong>
					</td>
					<td>
						<html:text maxlength="4" 
							property="numeroLayOut" 
							size="4"
							onkeyup="javascript:verificaNumeroInteiro(this);" />
					</td>
				</tr>
				
				<tr>
					<td width="40%" align="left">
						<strong>Identificador da Conta Bancária para Devolução:</strong>
					</td>
					<td>
						<html:select property="indentificadorContaDevolucao">
						<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
						<html:options collection="colecaoContaBancaria" 
							labelProperty="id"
							property="id" />
						</html:select>
					</td>
				</tr>
				
				<tr>
					<td width="40%" align="left">
						<strong>Número do módulo verificador:</strong>
					</td>
					<td>
						<html:text maxlength="2" 
							property="numeroModuloDigitoVerificador" 
							size="3"
							onkeypress="return isCampoNumerico(event);" />
					</td>
				</tr>
				
				<tr>
					<td width="40%" align="left">
						<strong>Número meses para pesquisa de imóveis com ramais suprimidos:</strong>
					</td>
					<td>
						<html:text maxlength="3" 
							property="numeroMesesPesquisaImoveisRamaisSuprimidos" 
							size="4"
							onkeypress="return isCampoNumerico(event);" />
					</td>
				</tr>

				<tr>
					<td width="40%" align="left">
						<strong>Número de anos para geração da declaração de quitação de débitos:</strong>
					</td>
					<td>
						<html:text maxlength="2" 
							property="numeroAnoQuitacao" 
							size="4"
							onkeypress="return isCampoNumerico(event);" />
					</td>
				</tr>
				
				<tr>
					<td width="40%" align="left">
						<strong>Quantidade de meses anteriores geração declaração de quitação de débitos:</strong>
					</td>
					<td>
						<html:text maxlength="2" 
							property="numeroMesesAnterioresParaDeclaracaoQuitacao" 
							size="4"
							onkeypress="return isCampoNumerico(event);" />
					</td>
				</tr>
				
				
				<tr>
						<td width="40%">
							<strong>Contas parceladas para declaração de quitação de débitos: </strong>
						</td>
						<td>
							<strong> 
							<html:radio property="indicadorContaParcelada" value="1" /> Sim 
							<html:radio property="indicadorContaParcelada" value="2" /> N&atilde;o
							</strong>
						</td>
				</tr>
				
				<tr>
						<td width="40%">
							<strong>Contas em cobrança judicial para declaração de quitação de débitos: </strong>
						</td>
						<td>
							<strong> 
							<html:radio property="indicadorCobrancaJudical" value="1" /> Sim 
							<html:radio property="indicadorCobrancaJudical" value="2" /> N&atilde;o
							</strong>
						</td>
				</tr>
				
				<tr>
						<td width="40%">
							<strong>Indicador do valor do movimento arrecadador: </strong><font color="#FF0000">*</font>
						</td>
						<td>
							<strong> 
							<html:radio property="indicadorValorMovimentoArrecadador" value="1" /> Sim 
							<html:radio property="indicadorValorMovimentoArrecadador" value="2" /> N&atilde;o
							</strong>
						</td>
				</tr>
				
				<tr>
					<td>&nbsp;</td>
				</tr>

				<tr>
					<td colspan="2" align="center">
						<strong>Parâmetros para o Financeiro:</strong>
					</td>
				</tr>

				<tr>
					<td>&nbsp;</td>
				</tr>
				
				<tr>
					<td width="40%" align="left">
						<strong> Percentual de Entrada Mínima para Financiamento:</strong>
					</td>
					
					<td width="87%">
						<html:text property="percentualEntradaMinima"
							size="5" 
							maxlength="5"
							onkeyup="javascript:formataValorMonetario(this, 5);" /> 
					</td>
				</tr>
				
				<tr>
					<td width="40%" align="left">
						<strong>Máximo de Parcelas para um Financiamento:</strong>
					</td>
					<td>
						<html:text maxlength="5" 
							property="maximoParcelas" 
							size="5"
							onkeyup="javascript:verificaNumeroInteiro(this);" />
					</td>
				</tr>
				
				<tr>
					<td width="40%" align="left">
						<strong>Percentual Máximo para Abatimento de um Serviço:</strong>
					</td>
					<td>
						<html:text maxlength="5" 
							property="percentualMaximoAbatimento"
							size="5" 
							onkeyup="javascript:formataValorMonetario(this, 5);" />
					</td>
				</tr>
				
				<tr>
					<td width="40%" align="left">
						<strong>Percentual de Taxa de Juros para Financiamento:</strong>
					</td>
					<td>
						<html:text maxlength="5" 
							property="percentualTaxaFinanciamento"
							size="5" 
							onkeyup="javascript:formataValorMonetario(this, 5);" />
					</td>
				</tr>
				
				<tr>
					<td width="40%" align="left">
						<strong>Número Máximo para Parcela de Crédito:</strong>
					</td>
					
					<td>
						<html:text maxlength="3" 
							property="numeroMaximoParcelaCredito"
							size="3" 
							onkeyup="javascript:verificaNumeroInteiro(this);" />
					</td>
				</tr>
				
				<tr>
					<td width="40%" align="left">
						<strong>Percentual da Média do Índice para Cálculo do Parcelamento:</strong>
					</td>
					<td>
						<html:text maxlength="5" 
							property="percentualCalculoIndice"
							size="5" 
							onkeyup="javascript:formataValorMonetario(this, 5);" /></td>
				</tr>
				
				<tr>
					<td width="40%" align="left">
						<strong>Código Relatório Dados Diários:</strong><font color="#FF0000">*</font>
					</td>
					<td align="right" colspan="2"><div align="left"><span class="style2">
				  				<html:select property="cdDadosDiarios" tabindex="1">
								<html:option value="<%= ""+ConstantesSistema.NUMERO_NAO_INFORMADO %>">&nbsp;</html:option>				  
								<html:option value="0">Nao Exibir Faturamento Cobrado em Conta</html:option>
								<html:option value="1">Exibir Faturamento Cobrado em Conta</html:option>
								</html:select>
				  			</span></div>
						</td>
				</tr>
				
				<tr>
					<td></td>
					<td><strong><font color="#FF0000">*</font></strong>Campo obrigat&oacute;rio</td>
				</tr>
				
				<tr>
					<td colspan="2">
					<div align="right"><jsp:include
						page="/jsp/util/wizard/navegacao_botoes_wizard_valida_avancar.jsp?numeroPagina=3" /></div>
					</td>
				</tr>
			</table>
			<p>&nbsp;</p>
			</td>
		</tr>
	</table>
	<%@ include file="/jsp/util/rodape.jsp"%>
</body>
</html:form>
</html:html>
