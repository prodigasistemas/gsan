<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@page import="gcom.util.ConstantesSistema" isELIgnored="false"%>
<html:html>

<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">

<html:javascript staticJavascript="false"
	formName="FiltrarAnormalidadeLeituraActionForm"
	dynamicJavascript="true" />

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript">

	function validaForm(form){

		if(testarCampoValorZero(document.FiltrarAnormalidadeLeituraActionForm.descricao, 'Descrição') &&
		testarCampoValorZero(document.FiltrarAnormalidadeLeituraActionForm.tipoServico, 'Tipo de Serviço')) {

			if(validateFiltrarAnormalidadeLeituraActionForm(form)){

    			submeterFormPadrao(form);
			}
		}
	}
	
	function limparForm(form) {
		form.descricao.value = "";
	    form.indicadorRelativoHidrometro.value = "";
	    form.indicadorImovelSemHidrometro.value = "";
		form.usoRestritoSistema.value = "";
	    form.perdaTarifaSocial.value = "";
	    form.osAutomatico.value = "";
		form.tipoServico.value = "";
	    form.consumoLeituraNaoInformado.value = "";
	    form.consumoLeituraInformado.value = "";
		form.leituraLeituraNaoturaInformado.value = "";
	    form.leituraLeituraInformado.value = "";
		
	}

    function verificarChecado(valor){
		form = document.forms[0];
		if(valor == "1"){
		 	form.indicadorAtualizar.checked = true;
		 }else{
		 	form.indicadorAtualizar.checked = false;
		}
	}

	function desabiltaCombo(){
		var form = document.forms[0];
		if(form.osAutomatico[1].checked){
			form.tipoServico.value="-1";
			form.tipoServico.disabled = true;
		}else{ 
			if (form.osAutomatico[0].checked){
				form.tipoServico.value="-1";
				form.tipoServico.disabled = false;

			}
		}
	} 

</script>


</head>

<body leftmargin="5" topmargin="5"
	onload="verificarChecado('${sessionScope.indicadorAtualizar}');">
<html:form action="/filtrarAnormalidadeLeituraAction"
	name="FiltrarAnormalidadeLeituraActionForm"
	type="gcom.gui.micromedicao.leitura.FiltrarAnormalidadeLeituraActionForm"
	method="post"
	onsubmit="return validateFiltrarAnormalidadeLeituraActionForm(this);">

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
			<td width="615" valign="top" class="centercoltext">
			<table height="100%">
				<tr>
					<td></td>
				</tr>
			</table>


			<!--Início Tabela Reference a Páginação da Tela de Processo-->
			<table>
				<tr>
					<td></td>

				</tr>
			</table>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img border="0" src="imagens/parahead_left.gif" /></td>
					<td class="parabg">Filtrar Anormalidade de Leitura</td>
					<td width="11" valign="top"><img border="0"
						src="imagens/parahead_right.gif" /></td>
				</tr>

			</table>
			<!--Fim Tabela Reference a Páginação da Tela de Processo-->
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>
					<td height="10" colspan="2">Para filtrar uma Anormalidade de
					Leitura, informe os dados abaixo:</td>
					<td width="80" align="right"><html:checkbox property="indicadorAtualizar" value="1" /><strong>Atualizar</strong></td>
				<tr>
					<td><strong>Descri&ccedil;&atilde;o:</strong></td>
					<td colspan="2"><span class="style2"> <html:text
						property="descricao" size="25" maxlength="25" /> </span></td>
				</tr>
				
				<tr>
					<td>&nbsp;</td>
					<td><html:radio property="tipoPesquisa"	tabindex="5"
						value="<%=ConstantesSistema.TIPO_PESQUISA_INICIAL.toString()%>" />
						Iniciando pelo texto
						<html:radio	property="tipoPesquisa" tabindex="6"
						value="<%=ConstantesSistema.TIPO_PESQUISA_COMPLETA.toString()%>" />
						Contendo o texto
					</td>
				</tr>				
				
				<!-- Abreviatura -->

				<tr>
					<td><strong>Anormalidade Relativa a Hidrômetro:</strong></td>
					<td><strong> <html:radio property="indicadorRelativoHidrometro"
						value="1" /> <strong>Sim <html:radio
						property="indicadorRelativoHidrometro" value="2" /> N&atilde;o</strong>
					</strong></td>

				</tr>
				<tr>
					<td><strong>Anormalidade Aceita para Ligação sem Hidrômetro:</strong></td>
					<td><strong> <html:radio property="indicadorImovelSemHidrometro"
						value="1" /> <strong>Sim <html:radio
						property="indicadorImovelSemHidrometro" value="2" /> N&atilde;o</strong>
					</strong></td>

				</tr>
				<tr>
					<td><strong>Anormalidade de Uso Restrito do Sistema:</strong></td>
					<td><strong> <html:radio property="usoRestritoSistema" value="1" />
					<strong>Sim <html:radio property="usoRestritoSistema" value="2" />
					N&atilde;o</strong> </strong></td>

				</tr>
				<tr>
					<td><strong>Anormalidade Acarreta Perda Tarifa Social:</strong></td>
					<td><strong> <html:radio property="perdaTarifaSocial" value="1" />
					<strong>Sim <html:radio property="perdaTarifaSocial" value="2" />
					N&atilde;o</strong> </strong></td>

				</tr>
				<tr>
					<td><strong>Anormalidade Emite OS Automática:</strong></td>
					<td><strong> <html:radio property="osAutomatico" value="1"
						onchange="javascript:desabiltaCombo();" /> <strong>Sim <html:radio
						property="osAutomatico" value="2"
						onchange="javascript:desabiltaCombo();" /> N&atilde;o</strong> </strong></td>

				</tr>

				<!-- Tipo de Serviço -->

				<tr>
					<td><strong>Tipo de Serviço:</strong></td>
					<td colspan="2" align="left"><html:select property="tipoServico" >
						<html:option value="-1">&nbsp;</html:option>
						<html:options collection="colecaoTipoServico"
							labelProperty="descricao" property="id" />
					</html:select></td>
				</tr>

				<tr>
					<td><strong>Anormalidade referente ao IS:</strong></td>
					<td><strong> <html:radio property="indicadorImpressaoSimultanea" value="1" />
					<strong>Sim <html:radio property="indicadorImpressaoSimultanea" value="2" />
					N&atilde;o</strong> </strong></td>

				</tr>
				<!--Consumo a Ser Cobrado (anormalidade informada e leitura não informada)-->

				<tr>
					<td><strong>Consumo a Ser Cobrado (anormalidade informada e leitura
					não informada):</strong></td>
					<td colspan="2" align="left"><html:select
						property="consumoLeituraNaoInformado">
						<html:option value="-1">&nbsp;</html:option>
						<html:options collection="colecaoLeituraAnormalidadeConsumo"
							labelProperty="descricaoConsumoACobrar" property="id" />
					</html:select></td>
				</tr>

				<tr>
					<td><strong>Consumo a Ser Cobrado (anormalidade informada e leitura
					informada):</strong></td>
					<td colspan="2" align="left"><html:select
						property="consumoLeituraInformado">
						<html:option value="-1">&nbsp;</html:option>
						<html:options collection="colecaoLeituraAnormalidadeConsumo"
							labelProperty="descricaoConsumoACobrar" property="id" />
					</html:select></td>
				</tr>

				<tr>
					<td><strong>Leitura para faturamento (anormalidade informada e
					leitura não informada):</strong></td>
					<td colspan="2" align="left"><html:select
						property="leituraLeituraNaoturaInformado">
						<html:option value="-1">&nbsp;</html:option>
						<html:options collection="colecaoLeituraAnormalidadeLeitura"
							labelProperty="descricaoFaturamento" property="id" />
					</html:select></td>
				</tr>

				<tr>
					<td><strong>Leitura para faturamento (anormalidade informada e
					leitura informada):</strong></td>
					<td colspan="2" align="left"><html:select
						property="leituraLeituraInformado">
						<html:option value="-1">&nbsp;</html:option>
						<html:options collection="colecaoLeituraAnormalidadeLeitura"
							labelProperty="descricaoFaturamento" property="id" />
					</html:select></td>
				</tr>
				
				<tr>
					<td><strong>Indicador de uso:</strong></td>
					<td><html:radio property="indicadorUso" tabindex="2" value="1"/><strong>Ativo</strong>
						<html:radio	property="indicadorUso" tabindex="3" value="2"/><strong>Inativo</strong>
						<html:radio	property="indicadorUso" tabindex="4" value=""/><strong>Todos</strong>
					</td>
				</tr>		
								
				<tr>
					<td><strong> <font color="#FF0000"> <input type="button"
						name="Submit22" class="bottonRightCol" value="Limpar"
						onClick="javascript:window.location.href='/gsan/exibirFiltrarAnormalidadeLeituraAction.do?menu=sim'"><!-- <input type="button"
								name="Submit23" class="bottonRightCol" value="Cancelar"
								onClick="javascript:window.location.href='/gsan/telaPrincipal.do'"> -->
					</font> </strong></td>
					<td colspan="2" align="right"><input type="button" name="Submit2"
						class="bottonRightCol" value="Filtrar"
						onclick="validaForm(document.forms[0]);"></td>
				</tr>
			</table>
			<p>&nbsp;</p>
		</tr>
		<!-- Rodapé -->
		<%@ include file="/jsp/util/rodape.jsp"%>
	</table>
	<p>&nbsp;</p>

	<tr>

	</tr>

</html:form>
</body>
</html:html>

