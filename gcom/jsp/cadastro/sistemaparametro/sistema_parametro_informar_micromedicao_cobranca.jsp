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

<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">

<html:javascript formName="InformarSistemaParametrosActionForm" dynamicJavascript="false" staticJavascript="true" />

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script>

	var bCancel = false;

	function validateInformarSistemaParametrosActionForm(form) {
		if (bCancel){
			return true;
		} else {
			return  validateRequired(form) && 
				validateInteger(form);
		}
		
		//validateCaracterEspecial(form) 
		//&& validateRequired(form)
		//&& validateInteger(form);
	}

	function IntegerValidations () {
		this.aa = new Array("incrementoMaximoConsumo", "Incremento Máximo de Consumo por economia em Rateio deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
		//this.ab = new Array("decrementoMaximoConsumo", "Decremento Máximo de Consumo por economia em Rateio deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
		this.ac = new Array("diasVencimentoCobranca", "Número de Dias entre o Vencimento e o Início da Cobrança deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
		this.ad = new Array("numeroDiasValidadeExtrato", "Número de Dias de Validade do Extrato de Débito deve somente conter números positivos.", new Function ("varName", " return this[varName];"));		
		this.ae = new Array("numeroDiasValidadeExtratoPermissaoEspecial", "Número de Dias de Validade do Extrato de Débito para quem possui Permissão Especial deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
		this.af = new Array("numeroDiasVencimentoEntradaParcelamento", "Número de Dias para o Vencimento da Guia de pagamento de Entrada de Parcelamento.", new Function ("varName", " return this[varName];"));	
		this.af = new Array("numeroDiasEncerrarOsFiscalizacaoDecursoPrazo", "Número de dias úteis para que a OS de Fiscalização Seja Encerrada por Decurso de Prazo deve conter apenas números positivos.", new Function ("varName", " return this[varName];"));	
	}

/*function caracteresespeciais () {
	this.aa = new Array("nomeEstado", "Nome do Estado possui caracteres especiais.", new Function ("varName", " return this[varName];"));
	this.ab = new Array("nomeEmpresa", "Nome da Empresa possui caracteres especiais.", new Function ("varName", " return this[varName];"));
	this.ac = new Array("abreviaturaEmpresa", "Abreviatura da Empresa possui caracteres especiais.", new Function ("varName", " return this[varName];"));
	this.ad = new Array("cnpj", "CNPJ possui caracteres especiais.", new Function ("varName", " return this[varName];"));
}*/

	function required () {
		this.aa = new Array("incrementoMaximoConsumo", "Informe Incremento Máximo de Consumo por economia em Rateio.", new Function ("varName", " return this[varName];"));
		this.ab = new Array("decrementoMaximoConsumo", "Informe Decremento Máximo de Consumo por economia em Rateio.", new Function ("varName", " return this[varName];"));
		this.ac = new Array("diasVencimentoCobranca", "Informe Número de Dias entre o Vencimento e o Início da Cobrança.", new Function ("varName", " return this[varName];"));
		this.ad = new Array("numeroDiasValidadeExtrato", "Informe Número de Dias de Validade do Extrato de Débito.", new Function ("varName", " return this[varName];"));		
	}


</script>
</head>

<body leftmargin="5" topmargin="5"
	onload="setarFoco('${requestScope.nomeCampo}');">

<html:form action="/informarParametrosSistemaWizardAction" method="post"
	onsubmit="return validateInformarSistemaParametrosActionForm(this);">

	<jsp:include
		page="/jsp/util/wizard/navegacao_abas_wizard_valida_avancar.jsp?numeroPagina=4" />

	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>

	<input type="hidden" name="numeroPagina" value="4" />
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
					<td colspan="2" align="center"><strong>Parâmetros para
					Micromedição:</strong></td>
				</tr>

				<tr>
					<td>&nbsp;</td>
				</tr>
				
				<tr>
					<td width="40%" align="left">
						<strong>Menor Capacidade de Hidrômetro para ser Grande Usuário:</strong>
					</td>
					<td>
						<html:select property="codigoMenorCapacidade">
						<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
						<html:options collection="colecaoHidrometroCapacidade"
							labelProperty="descricao" 
							property="id" />
						</html:select>
					</td>
				</tr>
				
				<tr>
					<td width="40%">
						<strong>Indicador de Geração de Faixa Falsa:</strong>
					</td>
					<td><strong> 
						<html:radio property="indicadorGeracaoFaixaFalsa" value="1" /> Sim 
						<html:radio property="indicadorGeracaoFaixaFalsa" value="2" /> N&atilde;o 
						<html:radio property="indicadorGeracaoFaixaFalsa" value="3" /> De acordo com a Rota
						</strong>
					</td>
				</tr>

				<tr>
					<td width="40%">
						<strong>Indicador do Percentual para Geração de Faixa Falsa:</strong>
					</td>
					<td>
						<strong> 
						<html:radio property="indicadorPercentualGeracaoFaixaFalsa" value="1" /> Percentual Parâmetro 
						<html:radio property="indicadorPercentualGeracaoFaixaFalsa" value="2" /> Percentual da Rota 
						</strong>
					</td>
				</tr>

				<tr>
					<td width="40%" align="left">
						<strong> Percentual de Geração de Faixa Falsa:</strong>
					</td>
					<td>
						<html:text property="percentualGeracaoFaixaFalsa"
							size="5" 
							maxlength="5"
							onkeyup="javascript:formataValorMonetario(this, 5);" /> 
					</td>
				</tr>

				<tr>
					<td width="40%">
						<strong>Indicador de Geração de Fiscalização de Leitura :</strong>
					</td>
					<td>
						<strong> 
						<html:radio property="indicadorGeracaoFiscalizacaoLeitura" value="1" /> Sim 
						<html:radio property="indicadorGeracaoFiscalizacaoLeitura" value="2" /> Não 
						<html:radio property="indicadorGeracaoFiscalizacaoLeitura" value="3" /> De acordo com a Rota 
						</strong>
					</td>

				</tr>

				<tr>
					<td width="40%">
						<strong>Indicador do Percentual Geração de Fiscalização de Leitura :</strong>
					</td>
					<td>
						<strong> 
						<html:radio property="indicadorPercentualGeracaoFiscalizacaoLeitura" value="1" /> Percentual Parâmetro 
						<html:radio property="indicadorPercentualGeracaoFiscalizacaoLeitura" value="2" /> Percentual da Rota 
						</strong>
					</td>
				</tr>

				<tr>
					<td width="40%" align="left">
						<strong>Percentual de Geração de Fiscalização de Leitura:</strong>
					</td>
					<td>
						<html:text property="percentualGeracaoFiscalizacaoLeitura" 
							size="5"
							maxlength="5" 
							onkeyup="javascript:formataValorMonetario(this, 5);" />
					</td>
				</tr>

				<tr>
					<td width="40%" align="left">
						<strong>Incremento Máximo de Consumo por economia em Rateio:</strong>
						<font color="#FF0000">*</font>
					</td>
					<td width="87%">
						<html:text property="incrementoMaximoConsumo"
							size="9" 
							maxlength="9"
							onkeyup="javascript:verificaNumeroInteiro(this);" />  
					</td>
				</tr>

				<tr>
					<td width="40%" align="left">
						<strong>Decremento Máximo de Consumo por economia em Rateio: </strong>
						<font color="#FF0000">*</font>
					</td>
					<td>
						<html:text property="decrementoMaximoConsumo"
							size="9" 
							maxlength="9"
							onkeyup="javascript:verificaNumeroInteiro(this);" /> 
					</td>
				</tr>

				<tr>
					<td width="40%" align="left">
						<strong>Percentual de Tolerância para o Rateio do Consumo: </strong>
					</td>
					<td width="87%">
						<html:text property="percentualToleranciaRateioConsumo" 
							size="5"
							maxlength="5" 
							onkeyup="javascript:formataValorDecimalUmaCasa(this, 3);" />
					</td>
				</tr>

				<tr>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td colspan="2" align="center"><strong>Parâmetros para Cobrança:</strong></td>

				</tr>
				<tr>
					<td>&nbsp;</td>
				</tr>

				<tr>
					<td width="40%" align="left">
						<strong>Número de Dias entre o Vencimento e o Início da Cobrança:</strong>
						<font color="#FF0000">*</font>
					</td>
					<td>
						<html:text maxlength="2" 
							property="diasVencimentoCobranca"
							size="2" 
							onkeyup="javascript:verificaNumeroInteiro(this);" />
					</td>
				</tr>
				
				<tr>
					<td width="40%" align="left">
						<strong>Número Máximo de Meses de Sanções:</strong>
					</td>
					<td>
						<html:text maxlength="2" 
							property="numeroMaximoMesesSancoes"
							size="2" 
							onkeyup="javascript:verificaNumeroInteiro(this);" />
					</td>
				</tr>
				
				<tr>
					<td width="40%" align="left">
						<strong>Valor da Segunda Via:</strong>
						<font color="#FF0000">*</font>
					</td>
					<td>
						<html:text maxlength="13" 
							property="valorSegundaVia" 
							size="13" onkeyup="javascript:formataValorMonetario(this, 13);"/>
					</td>
				</tr>	
				
				<tr>
					<td width="40%">
						<strong>Indicador de Cobrança da Taxa de Extrato :</strong>
					</td>
					<td>
						<strong> 
						<html:radio property="indicadorCobrarTaxaExtrato" value="1" /> Sim 
						<html:radio property="indicadorCobrarTaxaExtrato" value="2" /> N&atilde;o 
						</strong>
					</td>
				</tr>

				<tr>
					<td width="40%" align="left">
						<strong>Código da Periodicidade da Negativaç&atilde;o:</strong>
					</td>
					<td>
						<html:text maxlength="2" 
							property="codigoPeriodicidadeNegativacao"
							size="2" 
							onkeyup="javascript:verificaNumeroInteiro(this);" />
					</td>
				</tr>

				<tr>
					<td width="40%" align="left">
						<strong>Número de Dias para Calculo de Adicionais de Impontualidade:</strong>
					</td>
					<td>
						<html:text maxlength="2" 
							property="numeroDiasCalculoAcrescimos"
							size="2" 
							onkeyup="javascript:verificaNumeroInteiro(this);" />
					</td>
				</tr>
				
				
				<tr>
					<td width="40%" align="left">
						<strong>Número de Dias de Validade do Extrato de Débito:</strong>
					</td>
					<td>
						<html:text maxlength="2" 
							property="numeroDiasValidadeExtrato"
							size="2" 
							onkeyup="javascript:verificaNumeroInteiro(this);" />
					</td>
				</tr>
				
				<tr>
					<td width="40%" align="left">
						<strong>Número de Dias de Validade do Extrato de Débito para quem possui Permissão Especial:</strong>
					</td>
					<td>
						<html:text maxlength="2" 
							property="numeroDiasValidadeExtratoPermissaoEspecial"
							size="2" 
							onkeyup="javascript:verificaNumeroInteiro(this);" />
					</td>
				</tr>
				
				<tr>
					<td width="40%" align="left">
						<strong>Número de Dias para o Vencimento da Guia de pagamento de Entrada de Parcelamento:</strong>
						<font color="#FF0000">*</font>
					</td>
					<td>
						<html:text maxlength="2" 
							property="numeroDiasVencimentoEntradaParcelamento"
							size="2" 
							onkeyup="javascript:verificaNumeroInteiro(this);" />
					</td>
				</tr>
				
				<tr>
					<td width="40%" align="left">
						<strong>Resolução de Diretoria para Cálculo de Descontos para pagamento à vista :</strong>
					</td>
					<td>
						<html:select property="idResolucaoDiretoria">
						<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
							<html:options collection="colecaoResolucaoDiretoria"
							labelProperty="numeroResolucaoDiretoria" 
							property="id" />
						</html:select>
					</td>
				</tr>
				
				
				<tr>
					<td width="40%">
						<strong>Indicador Parcelamento Confirmado :</strong>
					</td>
					<td>
						<strong> 
							<html:radio property="indicadorParcelamentoConfirmado" value="1" /> Sim 
							<html:radio property="indicadorParcelamentoConfirmado" value="2" /> N&atilde;o 
						</strong>
					</td>
				</tr>
				<tr>
					<td width="40%" align="left">
						<strong>Número de dias úteis para que a OS de Fiscalização Seja Encerrada por Decurso de Prazo:</strong>
					</td>
					<td>
						<html:text maxlength="3" 
							property="numeroDiasEncerrarOsFiscalizacaoDecursoPrazo"
							size="3" 
							onkeyup="javascript:verificaNumeroInteiro(this);"
							onchange="javascript:verificaNumeroInteiroComAlerta(this, 'Número de dias úteis para que a OS de Fiscalização seja encerrada por Decurso de Prazo');" />
					</td>
				</tr>
			<tr>
			<td colspan="3"><hr></td>
			</tr>
				<tr>
					<td width="40%">
						<strong>Cálculo juros parcelamento pela tabela price :</strong>
					</td>
					<td>
						<strong> 
							<html:radio property="indicadorTabelaPrice" value="1" /> Sim 
							<html:radio property="indicadorTabelaPrice" value="2" /> N&atilde;o 
						</strong>
					</td>
				</tr>
				
			<tr>
			<td colspan="3"><hr></td>
			</tr>
				<tr>
					<td width="40%">
						<strong>Retirar Contas Vinculadas a Contrato de Parcelamento da Composição do Débito do Imóvel ou do Cliente?</strong>
						<font color="#FF0000">*</font>
					</td>
					<td>
						<strong>
							<html:radio property="indicadorBloqueioContasContratoParcelDebitos" value="1" /> Sim
							<html:radio property="indicadorBloqueioContasContratoParcelDebitos" value="2" /> N&atilde;o
						</strong>
					</td>
				</tr>
				
				<tr>
					<td width="40%">
						<strong>Retirar Guias Vinculadas a Contrato de Parcelamento da Composição do Débito do Imóvel ou do Cliente?</strong>
						<font color="#FF0000">*</font>
					</td>
					<td>
						<strong> 
							<html:radio property="indicadorBloqueioGuiasOuAcresContratoParcelDebito" value="1" /> Sim
							<html:radio property="indicadorBloqueioGuiasOuAcresContratoParcelDebito" value="2" /> N&atilde;o
						</strong>
					</td>
				</tr>
				<tr>
					<td width="40%">
						<strong>Retirar os Débitos a Cobrar Vinculados ao Contrato de Parcelamento da Composição do Débito do Imóvel ou do Cliente?</strong>
						<font color="#FF0000">*</font>
					</td>
					<td>
						<strong> 
							<html:radio property="indicadorBloqueioDebitoACobrarContratoParcelDebito" value="1" /> Sim
							<html:radio property="indicadorBloqueioDebitoACobrarContratoParcelDebito" value="2" /> N&atilde;o
						</strong>
					</td>
				</tr>
				
				<tr>
					<td width="40%">
						<strong>Bloquear Contas Vinculadas a Contrato de Parcelamento na tela de Manter Conta?</strong>
						<font color="#FF0000">*</font>
					</td>
					<td>
						<strong> 
							<html:radio property="indicadorBloqueioContasContratoParcelManterConta" value="1" /> Sim
							<html:radio property="indicadorBloqueioContasContratoParcelManterConta" value="2" /> N&atilde;o
						</strong>
					</td>
				</tr>
				
				<tr>
					<td width="40%">
						<strong>Bloquear Guias de Juros ou de Acréscimos por Impontualidade Vinculadas a Contrato de Parcelamento na tela de Manter Guia?</strong>
						<font color="#FF0000">*</font>
					</td>
					<td>
						<strong>
							<html:radio property="indicadorBloqueioGuiasOuAcresContratoParcelManterConta" value="1" /> Sim
							<html:radio property="indicadorBloqueioGuiasOuAcresContratoParcelManterConta" value="2" /> N&atilde;o
						</strong>
					</td>
				</tr>
				<tr>
					<td width="40%">
						<strong>Bloquear os Débitos a Cobrar Vinculados ao Contrato de Parcelamento na tela de Manter Débitos a Cobrar?</strong>
						<font color="#FF0000">*</font>
					</td>
					<td>
						<strong>
							<html:radio property="indicadorBloqueioDebitoACobrarContratoParcelManterDebito" value="1" /> Sim
							<html:radio property="indicadorBloqueioDebitoACobrarContratoParcelManterDebito" value="2" /> N&atilde;o
						</strong>
					</td>
				</tr>
					<tr>
						<td colspan="3"><hr></td>
					</tr>
				<tr>
					<td width="40%" align="left">
						<strong>Número Máximo de Parcelas para os Contratos de Parcelamento por Cliente:</strong>
					</td>
					<td>
						<html:text maxlength="3" 
							property="numeroMaximoParcelasContratosParcelamento"
							size="3" 
							onkeyup="javascript:verificaNumeroInteiro(this);" />
					</td>
				</tr>
				<p>&nbsp;</p>
				<tr>
					<td></td>
					<td>
						<font color="#FF0000">*</font>Campo obrigat&oacute;rio
					</td>
				</tr>
				<tr>
					<td colspan="2">
					<div align="right"><jsp:include
						page="/jsp/util/wizard/navegacao_botoes_wizard_valida_avancar.jsp?numeroPagina=4" /></div>
					</td>
				</tr>
			</table>
		
			</td>
		</tr>
	</table>

	<%@ include file="/jsp/util/rodape.jsp"%>
</body>
</html:form>
</html:html>
