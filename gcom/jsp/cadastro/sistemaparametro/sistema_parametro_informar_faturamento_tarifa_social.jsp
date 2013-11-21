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

<html:javascript formName="InformarSistemaParametrosActionForm"
	dynamicJavascript="false" staticJavascript="true" />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>

<script>
	var bCancel = false;

	function validateInformarSistemaParametrosActionForm(form) {
		if (bCancel) {
			return true;
		} else {
			return  validateRequired(form) && validateInteger(form);
		}
	}

	function IntegerValidations () {
		this.aa = new Array("menorConsumo", "Menor Consumo para ser Grande Usuário deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
		this.ab = new Array("menorValor", "Menor Valor para Emissão de Contas deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
		this.ac = new Array("qtdeEconomias", "Qtde de Economias para ser Grande Usuário deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
		this.ad = new Array("mesesCalculoMedio", "Meses para Cálculo de Média de Consumo deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
		this.ae = new Array("diasMinimoVencimento", "Dias Mínimo para Calcular Vencimento deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
		
		this.ag = new Array("numeroMesesValidadeConta", "Número de Meses para validade da Conta deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
		this.ah = new Array("numeroMesesAlteracaoVencimento", "Número de Meses para alteração de um vencimento para outro deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
		this.ai = new Array("consumoMaximo", "Consumo de Energia Máxima para a Tarifa deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
		this.aj = new Array("areaMaxima", "Área Máxima do Imóvel para a Tarifa deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
		this.al = new Array("numeroDiasVariacaoConsumo", "Qtde de Dias de Variação de Consumo deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
		this.am = new Array("qtdeContasRetificadas", "Qtde de Contas Retificadas deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
		
		this.an = new Array("numeroVezesSuspendeLeitura", "Numero de vezes suspende leitura deve somente conter números positivos.", new Function ("varName", "return this[varName];"));
		this.ao = new Array("numeroMesesLeituraSuspensa", "Numero de meses de leitura suspensa deve somente conter números positivos.", new Function ("varName", "return this[varName];"));
		this.ap = new Array("numeroMesesReinicioSitEspFatu", "Numero de meses reinicio de situacao especial de faturamento deve somente conter números positivos.", new Function ("varName", "return this[varName];"));
	}

	function required () {
		this.aa = new Array("mesAnoReferencia", "Informe Mês e Ano de Referência.", new Function ("varName", " return this[varName];"));
		this.ab = new Array("menorConsumo", "Informe Menor Consumo para ser Grande Usuário.", new Function ("varName", " return this[varName];"));
		this.ac = new Array("menorValor", "Informe Menor Valor para Emissão de Contas.", new Function ("varName", " return this[varName];"));
		this.ad = new Array("qtdeEconomias", "Informe Qtde de Economias para ser Grande Usuário.", new Function ("varName", " return this[varName];"));
		this.ae = new Array("mesesCalculoMedio", "Informe Meses para Cálculo de Média de Consumo.", new Function ("varName", " return this[varName];"));
		this.af = new Array("mesAnoAtualizacaoTarifaria", "Informe Mês e Ano de Atualização Tarifária.", new Function ("varName", " return this[varName];"));
		this.ag = new Array("numeroMesesMaximoCalculoMedia", "Informe Número máximo de meses para calculo da media.", new Function ("varName", " return this[varName];"));
		this.ah = new Array("numeroMesesCalculoCorrecao", "Informe Número de meses para correção monetária.", new Function ("varName", " return this[varName];"));
		this.ai = new Array("numeroDiasVariacaoConsumo", "Informe Qtde de Dias de Variação de Consumo.", new Function ("varName", " return this[varName];"));
		this.ak = new Array("nnDiasPrazoRecursoAutoInfracao", "Informe Número de dias de prazo para entrada de Recurso do Auto de Infração.", new Function ("varName", " return this[varName];"));
    	this.al = new Array("qtdeContasRetificadas", "Informe Qtde de Contas Retificadas.", new Function ("varName", " return this[varName];"));
    	this.al = new Array("codigoTipoCalculoNaoMedido", "Informe o Código de Tipo de Cálculo de Não Medido.", new Function ("varName", " return this[varName];"));
		
		
		
	}

	function validarDias(valor){
		var form = document.forms[0];
		if(valor > 15){
			alert('O número mínimo de dias não pode ser superior a 15');
			limparDiasMinimoVencimento();
			limparDiasMinimoVencimentoCorreio();
		}
	}

	function validarMes(valor){
		if(valor > 12){
			alert('O número mínimo de meses não pode ser superior a 12');
			limparNumeroMesesValidadeConta();
			limparNumeroMesesAlteracaoVencimento();
		}
	}

	function limparDiasMinimoVencimento(){
		var form = document.forms[0];
		if(form.diasMinimoVencimento.value > 15){
			form.diasMinimoVencimento.value = '';
			form.diasMinimoVencimento.focus();
		}
	}

	function limparDiasMinimoVencimentoCorreio(){
		var form = document.forms[0];
		if(form.diasMinimoVencimentoCorreio.value > 15){
			form.diasMinimoVencimentoCorreio.value = '';
			form.diasMinimoVencimentoCorreio.focus();
		}
	}

	function limparNumeroMesesValidadeConta()	{
		var form = document.forms[0];
		if(form.numeroMesesValidadeConta.value > 12){
			form.numeroMesesValidadeConta.value = '';
			form.numeroMesesValidadeConta.focus();
		}
	}
	
	function limparNumeroMesesAlteracaoVencimento(){
		var form = document.forms[0];	
		if(form.numeroMesesAlteracaoVencimento.value > 12){
			form.numeroMesesAlteracaoVencimento.value = '';
			form.numeroMesesAlteracaoVencimento.focus();
		}
	}

</script>
</head>

<body leftmargin="5" topmargin="5"
	onload="javascript:setarFoco('${requestScope.nomeCampo}'); limitTextArea(document.forms[0].mensagemContaBraile, 500, document.getElementById('utilizado'), document.getElementById('limite'));">
<html:form action="/informarParametrosSistemaWizardAction" method="post"
	onsubmit="return validateInformarSistemaParametrosActionForm(this);">

	<jsp:include
		page="/jsp/util/wizard/navegacao_abas_wizard_valida_avancar.jsp?numeroPagina=2" />

	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>

	<input type="hidden" name="numeroPagina" value="2" />

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
					<td colspan="2" align="center"><strong>Parâmetros para Faturamento:</strong></td>
				</tr>

				<tr>
					<td>&nbsp;</td>
				</tr>

				<tr>
					<td width="40%" align="left"><strong>Mês e Ano de Referência:</strong>
					<font color="#FF0000">*</font></td>
					<td width="82%"><html:text property="mesAnoReferencia" size="7"
						maxlength="7" onkeyup="javascript:mascaraAnoMes(this,event);"
						disabled="true" /> <strong>mm/aaaa</strong></td>
				</tr>

				<tr>
					<td width="40%" align="left"><strong>Menor Consumo para ser Grande
					Usuário:</strong> <font color="#FF0000">*</font></td>
					<td><html:text maxlength="9" property="menorConsumo" size="9"
						onkeyup="javascript:verificaNumeroInteiro(this);" /></td>
				</tr>

				<tr>
					<td width="40%" align="left"><strong>Menor Valor para Emissão de
					Contas:</strong> <font color="#FF0000">*</font></td>
					<td><html:text maxlength="13" property="menorValor" size="13"
						onkeyup="javascript:formataValorMonetario(this,13);" /></td>
				</tr>

				<tr>
					<td width="40%" align="left"><strong>Valor para Emissão de Conta no
					Formato Ficha de Compensação:</strong></td>
					<td><html:text maxlength="13" property="valorContaFichaComp"
						size="13" onkeyup="javascript:formataValorMonetario(this,13);" />
					</td>
				</tr>

				<tr>
					<td width="40%" align="left"><strong> Qtde de Economias para ser
					Grande Usuário:</strong> <font color="#FF0000">*</font></td>
					<td width="87%"><html:text property="qtdeEconomias" size="9"
						maxlength="9" onkeyup="javascript:verificaNumeroInteiro(this);" />
					</td>
				</tr>

				<tr>
					<td width="40%" align="left"><strong> Meses para Cálculo de Média
					de Consumo:<font color="#FF0000">*</font></strong></td>
					<td width="87%"><html:text property="mesesCalculoMedio" size="2"
						maxlength="2" onkeyup="javascript:verificaNumeroInteiro(this);" />
					</td>
				</tr>

				<tr>
					<td width="40%" align="left"><strong>Dias Mínimo para Calcular
					Vencimento:</strong></td>
					<td><html:text maxlength="5" property="diasMinimoVencimento"
						size="2" onkeyup="javascript:verificaNumeroInteiro(this);"
						onblur="javascript:validarDias(document.forms[0].diasMinimoVencimento.value);" />
					</td>
				</tr>

				<tr>
					<td width="40%" align="left"><strong>Dias Mínimo para Calcular
					Vencimento se entrega pelos Correios:</strong></td>
					<td><html:text maxlength="2" property="diasMinimoVencimentoCorreio"
						size="2" onkeyup="javascript:verificaNumeroInteiro(this);"
						onblur="javascript:validarDias(document.forms[0].diasMinimoVencimentoCorreio.value);" />
					</td>
				</tr>

				<tr>
					<td width="40%" align="left"><strong>Dias para vencimento
					alternativo:</strong></td>
					<td><html:text property="diasVencimentoAlternativo" size="50"
						maxlength="83"
						onkeypress="return (isCampoNumerico(event) || event.keyCode == 59 || event.which == 59)" />
					</td>
				</tr>
				<tr>
					<td width="40%" align="left"><strong>Número de meses para validade
					da Conta:</strong></td>
					<td><html:text maxlength="2" property="numeroMesesValidadeConta"
						size="2" onkeyup="javascript:verificaNumeroInteiro(this);"
						onblur="validarMes(document.forms[0].numeroMesesValidadeConta.value);" />
					</td>
				</tr>

				<tr>
					<td width="40%" align="left"><strong>Número de meses para alteração
					de um vencimento para outro:</strong></td>
					<td><html:text maxlength="2"
						property="numeroMesesAlteracaoVencimento" size="2"
						onkeyup="javascript:verificaNumeroInteiro(this);"
						onblur="validarMes(document.forms[0].numeroMesesAlteracaoVencimento.value);" />
					</td>
				</tr>

				<tr>
					<td width="40%" align="left"><strong>Número máximo de meses para
					retroagir o calculo da media:</strong> <font color="#FF0000">*</font>
					</td>
					<td><html:text maxlength="2"
						property="numeroMesesMaximoCalculoMedia" size="2"
						onkeyup="javascript:verificaNumeroInteiro(this);"
						onblur="validarMes(document.forms[0].numeroMesesMaximoCalculoMedia.value);" />
					</td>
				</tr>

				<tr>
					<td width="40%" align="left"><strong>Número de meses para calcular
					correção monetária:</strong> <font color="#FF0000">*</font></td>
					<td><html:text maxlength="2" property="numeroMesesCalculoCorrecao"
						size="2" onkeyup="javascript:verificaNumeroInteiro(this);"
						onblur="validarMes(document.forms[0].numeroMesesCalculoCorrecao.value);" />
					</td>
				</tr>

				<tr>
					<td width="40%"><strong>Indicador Seguir Roteiro Empresa:</strong>
					</td>
					<td><strong> <html:radio property="indicadorRoteiroEmpresa"
						value="1" /> Sim <html:radio property="indicadorRoteiroEmpresa"
						value="2" /> N&atilde;o </strong></td>
				</tr>

				<tr>
					<td width="40%"><strong>Indicador Alteração do Vencimento Mais de
					uma Vez:</strong></td>
					<td><strong> <html:radio
						property="indicadorLimiteAlteracaoVencimento" value="1" /> Sim <html:radio
						property="indicadorLimiteAlteracaoVencimento" value="2" />
					N&atilde;o </strong></td>
				</tr>

				<tr>
					<td width="40%"><strong>Indicador Tipo de Tarifa de Consumo: </strong>
					</td>
					<td><strong> <html:radio property="indicadorTarifaCategoria"
						value="1" /> Por Categoria <html:radio
						property="indicadorTarifaCategoria" value="2" /> Por SubCategoria
					</strong></td>
				</tr>

				<tr>
					<td width="40%"><strong>Indicador Atualizaç&atilde;o Tarifária: </strong>
					</td>
					<td><strong> <html:radio property="indicadorAtualizacaoTarifaria"
						value="1" /> Sim <html:radio
						property="indicadorAtualizacaoTarifaria" value="2" /> N&atilde;o
					</strong></td>
				</tr>

				<tr>
					<td width="40%"><strong>Mês e Ano de Atualização Tarifária:</strong>
					<font color="#FF0000">*</font></td>
					<td><html:text property="mesAnoAtualizacaoTarifaria" size="7"
						maxlength="7" onkeyup="javascript:mascaraAnoMes(this,event);" />
					<strong>mm/aaaa</strong></td>
				</tr>

				<tr>
					<td width="40%"><strong>Indicador de Faturamento Antecipado: </strong>
					</td>
					<td><strong> <html:radio property="indicadorFaturamentoAntecipado"
						value="1" /> Sim <html:radio
						property="indicadorFaturamentoAntecipado" value="2" /> N&atilde;o
					</strong></td>
				</tr>

				<tr>
					<td>&nbsp;</td>

				</tr>
				<tr>
					<td width="40%"><strong>Retificar com um valor Menor: </strong></td>
					<td><strong> <html:radio property="indicadorRetificacaoValorMenor"
						value="1" /> Sim <html:radio
						property="indicadorRetificacaoValorMenor" value="2" /> N&atilde;o
					</strong></td>
				</tr>


				<tr>
					<td width="40%"><strong>Transferência com débito: </strong></td>
					<td><strong> <html:radio property="indicadorTransferenciaComDebito"
						value="1" /> Sim <html:radio
						property="indicadorTransferenciaComDebito" value="2" />
					N&atilde;o </strong></td>
				</tr>

				<tr>
					<td width="40%"><strong>Não Medido por Tarifa de Consumo: </strong>
					</td>
					<td><strong> <html:radio property="indicadorNaoMedidoTarifa"
						value="1" /> Sim <html:radio property="indicadorNaoMedidoTarifa"
						value="2" /> N&atilde;o </strong></td>
				</tr>

				<tr>
					<td width="40%" align="left"><strong> Qtde de contas retificadas</strong>
					<font color="#FF0000">*</font></td>
					<td width="87%"><html:text property="qtdeContasRetificadas"
						size="9" maxlength="9"
						onkeyup="javascript:verificaNumeroInteiro(this);" /></td>
				</tr>

				<tr>
					<td width="40%" align="left"><strong>Quantidade de dias de variação
					de consumo:</strong> <font color="#FF0000">*</font></td>
					<td><html:text maxlength="3" property="numeroDiasVariacaoConsumo"
						size="3" onkeyup="javascript:verificaNumeroInteiro(this);" /></td>
				</tr>

				<tr>
					<td width="40%" align="left"><strong>Percentual do Bônus Social:</strong>
					<font color="#FF0000">*</font></td>
					<td><html:text maxlength="6" property="percentualBonusSocial"
						size="6" onkeyup="javascript:formataValorMonetario(this, 5);" />
					</td>
				</tr>

				<tr>
					<td width="40%" align="left"><strong>Indicador de bloqueio de
					recalculo e reemissao de conta na impressao simultanea:</strong> <font
						color="#FF0000">*</font></td>
					<td><strong> <html:radio property="indicadorBloqueioContaMobile"
						value="1" /> Sim <html:radio
						property="indicadorBloqueioContaMobile" value="2" /> N&atilde;o </strong>
					</td>
				</tr>

				<tr>
					<td width="40%" align="left"><strong>Mensagem Pedido Conta BRAILE:<font
						color="#FF0000"></font></strong></td>

					<td colspan="3"><span class="style2"> <strong> <html:textarea
						property="mensagemContaBraile" cols="40" rows="4"
						onkeyup="limitTextArea(document.forms[0].mensagemContaBraile, 500, document.getElementById('utilizado'), document.getElementById('limite'));" /><br>

					<strong> <span id="utilizado">0</span>/<span id="limite">500</span>
					</strong> </strong> </span></td>
				</tr>

				<tr>
					<td><strong>Código de Tipo de Cálculo de Não Medido:<font
						color="#FF0000">*</font></strong></td>
					<td align="right" colspan="2">
					<div align="left"><span class="style2"> <html:select
						property="codigoTipoCalculoNaoMedido" tabindex="1">
						<html:option
							value="<%= ""+ConstantesSistema.NUMERO_NAO_INFORMADO %>">&nbsp;</html:option>
						<html:option value="1">AREA CONSTRUIDA</html:option>
						<html:option value="2">PONTOS DE UTILIZAÇÃO</html:option>
						<html:option value="3">NUMERO DE MORADORES</html:option>
					</html:select> </span></div>
					</td>
				</tr>

				<tr>
					<td width="40%" align="left"><strong>Número de meses para retificar
					uma conta:</strong></td>
					<td><html:text maxlength="4" property="numeroMesesRetificarConta"
						size="4" onkeyup="javascript:verificaNumeroInteiro(this);" /></td>
				</tr>

				<tr>
					<td width="40%"><strong>Está na Norma de Retificação da Conta: </strong>
					</td>
					<td><strong> <html:radio property="indicadorNormaRetificacao"
						value="1" /> Sim <html:radio property="indicadorNormaRetificacao"
						value="2" /> N&atilde;o </strong></td>
				</tr>

				<tr>
					<td colspan="2" align="center"><strong>Parâmetros para Tarifa
					Social:</strong></td>
				</tr>

				<tr>
					<td>&nbsp;</td>
				</tr>

				<tr>
					<td width="40%" align="left"><strong>Salário Mínimo:</strong></td>
					<td><html:text maxlength="9" property="salarioMinimo" size="9"
						onkeyup="javascript:formataValorMonetario(this, 9);" /></td>
				</tr>

				<tr>
					<td width="40%" align="left"><strong>Área Máxima do Imóvel para a
					Tarifa:</strong></td>
					<td><html:text maxlength="7" property="areaMaxima" size="7"
						onkeyup="javascript:verificaNumeroInteiro(this);" /></td>
				</tr>

				<tr>
					<td width="40%" align="left"><strong>Consumo de Energia Máxima para
					a Tarifa:</strong></td>
					<td><html:text maxlength="4" property="consumoMaximo" size="4"
						onkeyup="javascript:verificaNumeroInteiro(this);" /></td>
				</tr>

				<tr>
					<td width="40%" align="left"><strong>Número de dias de prazo para
					entrada de recurso do auto de infração:</strong> <font
						color="#FF0000">*</font></td>
					<td><html:text maxlength="4"
						property="nnDiasPrazoRecursoAutoInfracao" size="4"
						onkeyup="javascript:verificaNumeroInteiro(this);" /></td>
				</tr>
				
				<tr>
					<td width="40%" align="left">
						<strong>Número de vezes com consumo até 10m³ para suspender leitura:</strong>
					</td>
					
					<td><html:text property="numeroVezesSuspendeLeitura" maxlength="2"
						size="2" onkeyup="javascript:verificaNumeroInteiro(this);" />
					</td>
				</tr>

				<tr>
					<td width="40%" align="left">
						<strong>Número de meses para manter leitura suspensa:</strong>
					</td>
					
					<td><html:text property="numeroMesesLeituraSuspensa" maxlength="2"
						size="2" onkeyup="javascript:verificaNumeroInteiro(this);" />
					</td>
				</tr>

				<tr>
					<td width="40%" align="left">
						<strong>Intervalo de meses para considerar reincidência de consumo até 10m³:</strong>
					</td>
					
					<td><html:text property="numeroMesesReinicioSitEspFatu"
						maxlength="2" size="2"
						onkeyup="javascript:verificaNumeroInteiro(this);" /></td>
				</tr>

				<tr>
					<td></td>
					<td><strong><font color="#FF0000">*</font></strong>Campos
					obrigat&oacute;rio</td>
				</tr>

				<tr>
					<td colspan="2">
					<div align="right"><jsp:include
						page="/jsp/util/wizard/navegacao_botoes_wizard_valida_avancar.jsp?numeroPagina=2" /></div>
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
