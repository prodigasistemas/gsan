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
	src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false"
	formName="ImovelOutrosCriteriosActionForm" dynamicJavascript="false" />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript">
 var bCancel = false;

function validarForm(form){

  if(validatePesquisarActionForm(form)){
    form.submit();
  }
}
function validateImovelOutrosCriteriosActionForm(){
	return true;
}
</script>
</head>

<body leftmargin="5" topmargin="5">
<div id="formDiv">
<html:form action="/filtrarImovelOutrosCriteriosWizardAction"
	name="ImovelOutrosCriteriosActionForm"
	type="gcom.gui.cadastro.imovel.ImovelOutrosCriteriosActionForm"
	method="post"
	onsubmit="return validateImovelOutrosCriteriosActionForm(this);">

	<jsp:include
		page="/jsp/util/wizard/navegacao_abas_wizard_valida_avancar_valida_voltar.jsp?numeroPagina=5" />

	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>

	<table width="770" border="0" cellspacing="5" cellpadding="0">
		<input type="hidden" name="numeroPagina" value="5" />
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
			<td width="625" valign="top" class="centercoltext">
			<table height="100%">
				<tr>
					<td></td>
				</tr>
			</table>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img
						src="<bean:message key="caminho.imagens"/>parahead_left.gif"
						border="0"></td>
					<td class="parabg">Filtrar Imóvel</td>
					<td width="11" valign="top"><img
						src="<bean:message key="caminho.imagens"/>parahead_right.gif"
						border="0"></td>
				</tr>
			</table>
			<!--Fim Tabela Reference a Páginação da Tela de Processo-->
			<p>&nbsp;</p>



			<table width="100%" border="0">
				<tr>
					<td colspan="4">Para filtrar o(s) im&oacute;vel(is) pelos dados de
					faturamento e cobran&ccedil;a, informe os dados abaixo:</td>
				</tr>
				<tr>
					<td width="251"><strong> Tipo de Situa&ccedil;&atilde;o Especial de
					Faturamento:</strong></td>
					<td width="346" colspan="3" align="right">
					<div align="left"><a href="usuario_pesquisar.htm"> </a><strong> <logic:present
						name="parametroGerarRelatorio">
						<logic:equal name="parametroGerarRelatorio"
							value="RelatorioImoveisEndereco">
							<html:select property="tipoSituacaoEspecialFaturamento"
								disabled="true" />
						</logic:equal>
						<logic:notEqual name="parametroGerarRelatorio"
							value="RelatorioImoveisEndereco">
							<html:select property="tipoSituacaoEspecialFaturamento">
								<html:option value="-1">&nbsp;</html:option>
								<html:options collection="collectionFaturamentoSituacaoTipo"
									property="id" labelProperty="descricao" />
							</html:select>
						</logic:notEqual>
					</logic:present> <logic:notPresent name="parametroGerarRelatorio">
						<html:select property="tipoSituacaoEspecialFaturamento">
							<html:option value="-1">&nbsp;</html:option>
							<html:options collection="collectionFaturamentoSituacaoTipo"
								property="id" labelProperty="descricao" />
						</html:select>
					</logic:notPresent> </strong></div>
					</td>
				</tr>
				<tr>
					<td><strong> Tipo de Situa&ccedil;&atilde;o Especial de
					Cobran&ccedil;a:</strong></td>
					<td colspan="3" align="right">
					<div align="left"><a href="usuario_pesquisar.htm"> </a><strong> <logic:present
						name="parametroGerarRelatorio">
						<logic:equal name="parametroGerarRelatorio"
							value="RelatorioCadastroConsumidoresInscricao">
							<html:select property="tipoSituacaoEspecialCobranca"
								disabled="true" />
						</logic:equal>
						<logic:notEqual name="parametroGerarRelatorio"
							value="RelatorioCadastroConsumidoresInscricao">
							<logic:equal name="parametroGerarRelatorio"
								value="RelatorioImoveisEndereco">
								<html:select property="tipoSituacaoEspecialCobranca"
									disabled="true" />
							</logic:equal>
							<logic:notEqual name="parametroGerarRelatorio"
								value="RelatorioImoveisEndereco">
								<html:select property="tipoSituacaoEspecialCobranca">
									<html:option value="-1">&nbsp;</html:option>
									<html:options collection="collectionCobrancaSituacaoTipo"
										property="id" labelProperty="descricao" />
								</html:select>
							</logic:notEqual>
						</logic:notEqual>
					</logic:present> <logic:notPresent name="parametroGerarRelatorio">
						<html:select property="tipoSituacaoEspecialCobranca">
							<html:option value="-1">&nbsp;</html:option>
							<html:options collection="collectionCobrancaSituacaoTipo"
								property="id" labelProperty="descricao" />
						</html:select>
					</logic:notPresent> </strong></div>
					</td>
				</tr>
				<tr>
					<td><strong> Situa&ccedil;&atilde;o de Cobran&ccedil;a:</strong></td>
					<td colspan="3" align="right">
					<div align="left"><a href="usuario_pesquisar.htm"> </a><strong> <logic:present
						name="parametroGerarRelatorio">
						<logic:equal name="parametroGerarRelatorio"
							value="RelatorioCadastroConsumidoresInscricao">
							<html:select property="situacaoCobranca" disabled="true" />
						</logic:equal>
						<logic:notEqual name="parametroGerarRelatorio"
							value="RelatorioCadastroConsumidoresInscricao">
							<logic:equal name="parametroGerarRelatorio"
								value="RelatorioImoveisEndereco">
								<html:select property="situacaoCobranca" disabled="true" />
							</logic:equal>
							<logic:notEqual name="parametroGerarRelatorio"
								value="RelatorioImoveisEndereco">
								<html:select property="situacaoCobranca">
									<html:option value="-1">&nbsp;</html:option>
									<html:options collection="collectionCobrancaSituacao"
										property="id" labelProperty="descricao" />
								</html:select>
							</logic:notEqual>
						</logic:notEqual>
					</logic:present> <logic:notPresent name="parametroGerarRelatorio">
						<html:select property="situacaoCobranca">
							<html:option value="-1">&nbsp;</html:option>
							<html:options collection="collectionCobrancaSituacao"
								property="id" labelProperty="descricao" />
						</html:select>
					</logic:notPresent> </strong></div>
					</td>
				</tr>
				<tr>
					<td colspan="4">
					<hr>
					</td>
				<tr>
				<tr>
					<td><strong>Dia de Vencimento Alternativo?</strong></td>
					<td colspan="3" align="right">
					<div align="left"><a href="usuario_pesquisar.htm"> </a><strong> <label>
					<logic:present name="parametroGerarRelatorio">
						<logic:equal name="parametroGerarRelatorio"
							value="RelatorioCadastroConsumidoresInscricao">
							<html:radio property="diaVencimentoAlternativo" value="1"
								disabled="true" />
						</logic:equal>
						<logic:notEqual name="parametroGerarRelatorio"
							value="RelatorioCadastroConsumidoresInscricao">
							<logic:equal name="parametroGerarRelatorio"
								value="RelatorioImoveisEndereco">
								<html:radio property="diaVencimentoAlternativo" value="1"
									disabled="true" />
							</logic:equal>
							<logic:notEqual name="parametroGerarRelatorio"
								value="RelatorioImoveisEndereco">
								<html:radio property="diaVencimentoAlternativo" value="1" />
							</logic:notEqual>
						</logic:notEqual>
					</logic:present> <logic:notPresent name="parametroGerarRelatorio">
						<html:radio property="diaVencimentoAlternativo" value="1" />
					</logic:notPresent> Sim</label> <label> <logic:present
						name="parametroGerarRelatorio">
						<logic:equal name="parametroGerarRelatorio"
							value="RelatorioCadastroConsumidoresInscricao">
							<html:radio property="diaVencimentoAlternativo" value="2"
								disabled="true" />
						</logic:equal>
						<logic:notEqual name="parametroGerarRelatorio"
							value="RelatorioCadastroConsumidoresInscricao">
							<logic:equal name="parametroGerarRelatorio"
								value="RelatorioImoveisEndereco">
								<html:radio property="diaVencimentoAlternativo" value="2"
									disabled="true" />
							</logic:equal>
							<logic:notEqual name="parametroGerarRelatorio"
								value="RelatorioImoveisEndereco">
								<html:radio property="diaVencimentoAlternativo" value="2" />
							</logic:notEqual>
						</logic:notEqual>
					</logic:present> <logic:notPresent name="parametroGerarRelatorio">
						<html:radio property="diaVencimentoAlternativo" value="2" />
					</logic:notPresent> N&atilde;o</label> <html:radio
						property="diaVencimentoAlternativo" value="" /> Todos </strong></div>
					</td>
				</tr>
				<tr>
					<td colspan="4">
					<hr>
					</td>
				<tr>
				<tr>
					<td><strong>Anormalidade de Localidade Pólo:</strong></td>
					<td colspan="3" align="right">
					<div align="left"><strong><logic:present
						name="parametroGerarRelatorio">
						<logic:equal name="parametroGerarRelatorio"
							value="RelatorioCadastroConsumidoresInscricao">
							<html:select property="anormalidadeElo" disabled="true" />
						</logic:equal>
						<logic:notEqual name="parametroGerarRelatorio"
							value="RelatorioCadastroConsumidoresInscricao">
							<html:select property="anormalidadeElo">
								<html:option value="-1">&nbsp;</html:option>
								<html:options collection="collectionEloAnormalidade"
									property="id" labelProperty="descricao" />
							</html:select>
						</logic:notEqual>
					</logic:present> <logic:notPresent name="parametroGerarRelatorio">
						<html:select property="anormalidadeElo">
							<html:option value="-1">&nbsp;</html:option>
							<html:options collection="collectionEloAnormalidade"
								property="id" labelProperty="descricao" />
						</html:select>
					</logic:notPresent> </strong></div>
					</td>
				</tr>
				<tr>
					<td><strong>Ocorr&ecirc;ncia de Cadastro:</strong></td>
					<td colspan="3" align="right">
					<div align="left"><strong> <logic:present
						name="parametroGerarRelatorio">
						<logic:equal name="parametroGerarRelatorio"
							value="RelatorioCadastroConsumidoresInscricao">
							<html:select property="ocorrenciaCadastro" disabled="true" />
						</logic:equal>
						<logic:notEqual name="parametroGerarRelatorio"
							value="RelatorioCadastroConsumidoresInscricao">
							<html:select property="ocorrenciaCadastro">
								<html:option value="-1">&nbsp;</html:option>
								<html:options collection="collectionCadastroOcorrencia"
									property="id" labelProperty="descricao" />
							</html:select>
						</logic:notEqual>
					</logic:present> <logic:notPresent name="parametroGerarRelatorio">
						<html:select property="ocorrenciaCadastro">
							<html:option value="-1">&nbsp;</html:option>
							<html:options collection="collectionCadastroOcorrencia"
								property="id" labelProperty="descricao" />
						</html:select>
					</logic:notPresent> </strong></div>
					</td>
				</tr>
				<tr>
					<td><strong>Tarifa de Consumo:</strong></td>
					<td colspan="3" align="right">
					<div align="left"><strong> <logic:present
						name="parametroGerarRelatorio">
						<logic:equal name="parametroGerarRelatorio"
							value="RelatorioCadastroConsumidoresInscricao">
							<html:select property="tarifaConsumo" disabled="true" />
						</logic:equal>
						<logic:notEqual name="parametroGerarRelatorio"
							value="RelatorioCadastroConsumidoresInscricao">
							<html:select property="tarifaConsumo">
								<html:option value="-1">&nbsp;</html:option>
								<html:options collection="collectionConsumoTarifa" property="id"
									labelProperty="descricao" />
							</html:select>
						</logic:notEqual>
					</logic:present> <logic:notPresent name="parametroGerarRelatorio">
						<html:select property="tarifaConsumo">
							<html:option value="-1">&nbsp;</html:option>
							<html:options collection="collectionConsumoTarifa" property="id"
								labelProperty="descricao" />
						</html:select>
					</logic:notPresent> </strong></div>
					</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td colspan="3" align="right">&nbsp;</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td colspan="3" align="right"></td>
				</tr>
				<tr>
					<td colspan="4">
					<div align="right"><jsp:include
						page="/jsp/util/wizard/navegacao_botoes_wizard_valida_avancar_valida_voltar.jsp?numeroPagina=5" />
					</div>
					</td>
				</tr>
			</table>

			<p>&nbsp;</p>
		</tr>
	</table>

	<%@ include file="/jsp/util/rodape.jsp"%>
</html:form>
</div>
</body>

<%@ include file="/jsp/util/telaespera.jsp"%>

<script>
document.getElementById('botaoConcluir').onclick = function() { botaoAvancarTelaEspera('/gsan/filtrarImovelOutrosCriteriosWizardAction.do?concluir=true&action=validarImovelOutrosCriterios'); }
</script>

</html:html>
