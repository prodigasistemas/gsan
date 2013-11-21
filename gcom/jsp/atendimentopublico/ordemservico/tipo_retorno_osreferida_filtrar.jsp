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

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false"
	formName="FiltrarTipoRetornoOrdemServicoReferidaActionForm"
	dynamicJavascript="true" />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript">

function validaForm(form){

	if(testarCampoValorZero(document.FiltrarTipoRetornoOrdemServicoReferidaActionForm.codigoTipoRetorno, 'Código do Tipo de Retorno') &&
	testarCampoValorZero(document.FiltrarTipoRetornoOrdemServicoReferidaActionForm.descricao, 'Descrição') && 
	testarCampoValorZero(document.FiltrarTipoRetornoOrdemServicoReferidaActionForm.abreviatura, 'Abreviatura')) {

		if(validateFiltrarTipoRetornoOrdemServicoReferidaActionForm(form)){

    		submeterFormPadrao(form);
		}
	}

function limparForm(form) {
		form.codigoTipoRetorno.value = "";
		form.descricao.value = "";
		form.abreviatura.value = "";
		form.servicoTipoReferencia.value = "";
	    form.deferimento.value = "";
	    form.trocaServico.value = "";  
	    form.situacao.value = "";
	    form.atendimentoMotivoEncerramento.value = "";  
	    form.indicadorUso.value = "";  	  	    
		
		
	
	}
}

function verificarValorAtualizar() {
	
		var form = document.forms[0];
	
		if (form.atualizar.checked == true) {
			form.atualizar.value = '1';
		} else {
			form.atualizar.value = '';
		}
		
	}

function checkAtualizar(valor) {
	
		var form = document.forms[0];
		
		if (valor == '1') {
			form.atualizar.checked = true;
		} else {
			form.atualizar.checked = false;
		}
		
	}


</script>


</head>

<body leftmargin="5" topmargin="5"
	onload="javascript:setarFoco('${requestScope.nomeCampo}');checkAtualizar('${sessionScope.atualizar}');verificarValorAtualizar();">

<html:form action="/filtrarTipoRetornoOrdemServicoReferidaAction"
	method="post" name="FiltrarTipoRetornoOrdemServicoReferidaActionForm"
	type="gcom.gui.atendimentopublico.ordemservico.FiltrarTipoRetornoOrdemServicoReferidaAction">

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
					<td class="parabg">Filtrar Tipo de Retorno da OS_Referida</td>
					<td width="11" valign="top"><img border="0"
						src="imagens/parahead_right.gif" /></td>
				</tr>

			</table>
			<!--Fim Tabela Reference a Páginação da Tela de Processo-->
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>
					<td colspan="3">Para filtrar o tipo de retorno da
					OS_Referida, informe os dados abaixo:</td>
					<td><html:checkbox property="atualizar" value="1" onclick="javascript:verificarValorAtualizar()"/><strong>Atualizar</strong></td>
				<tr>
					<td width="150"><strong>Código do Tipo de Retorno:<font
						color="#FF0000"></font></strong></td>

					<td colspan="2"><strong> <html:text property="codigoTipoRetorno" size="4"
						maxlength="4" /> </strong></td>
				</tr>
				<tr>
					<td width="150"><strong>Descri&ccedil;&atilde;o:<font
						color="#FF0000"></font></strong></td>

					<td colspan="2"><strong> <html:text property="descricao" size="50"
						maxlength="50" /> </strong></td>
				</tr>
				<tr>
					<td width="150"><strong>Abreviatura:<font color="#FF0000"></font></strong></td>
					<td colspan="2"><strong> <html:text property="abreviatura" size="5"
						maxlength="5" /> </strong></td>
				</tr>
				<tr>
					<td width="150"><strong>Referência do Tipo de Serviço<font color="#000000"></font>:</strong></td>
					<td colspan="2"><html:select property="servicoTipoReferencia">

						<html:option
							value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
						<html:options collection="colecaoServicoTipoReferencia"
							labelProperty="descricao" property="id" />
					</html:select></td>
				</tr>
				<tr>
					<td width="150"><strong>Indicador de Deferimento:</strong></td>
					<td colspan="2"><strong> <html:radio property="deferimento"
						value="1" /> <strong>Deferido&nbsp;&nbsp;<html:radio
						property="deferimento" value="2" /> Indeferido&nbsp;<html:radio
						property="deferimento" value="" /> Todos</strong> </strong></td>
				</tr>
				<tr>
					<td width="150"><strong>Indicador de Troca de Serviço:</strong></td>
					<td colspan="2"><strong> <html:radio property="trocaServico"
						value="1" /> <strong>Sim&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<html:radio property="trocaServico" value="2" />
					Não&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<html:radio
						property="trocaServico" value="" /> Todos</strong> </strong></td>
				</tr>
				<tr>
					<td width="150"><strong>Código da Situação:</strong></td>
					<td colspan="2"><strong> <html:radio property="situacao" value="1" />
					<strong>Pendente<html:radio property="situacao" value="2" />
					Encerrada<html:radio property="situacao" value="" /> Todos</strong>
					</strong></td>
				</tr>
				<tr>
					<td width="150"><strong>Motivo de Encerramento do Atendimento<font
						color="#000000"></font>:</strong></td>
					<td colspan="2"><html:select property="atendimentoMotivoEncerramento">

						<html:option
							value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
						<html:options collection="colecaoAtendimentoMotivoEncerramento"
							labelProperty="descricao" property="id" />
					</html:select></td>
				</tr>
				<tr>
					<td width="150"><strong>Indicador de Uso:</strong></td>
					<td colspan="2"><strong> <html:radio property="indicadorUso"
						value="1" /> <strong>Ativos&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <html:radio
						property="indicadorUso" value="2" />
					Inativos&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<html:radio
						property="indicadorUso" value="" /> Todos</strong> </strong></td>
				</tr>
				<tr>
					<td><strong> <font color="#FF0000"></font> </strong></td>
					<td colspan="2" align="right">
					<div align="left"><strong> <font color="#FF0000">*</font> </strong>
					Campos obrigat&oacute;rios</div>
					</td>
				</tr>
				<tr>
					<td><strong> <font color="#FF0000"> <input type="button"
						name="Submit22" class="bottonRightCol" value="Limpar"
						onClick="javascript:window.location.href='/gsan/exibirFiltrarTipoRetornoOrdemServicoReferidaAction.do?menu=sim'"><!-- <input type="button"
								name="Submit23" class="bottonRightCol" value="Cancelar"
								onClick="javascript:window.location.href='/gsan/telaPrincipal.do'"> -->
					</font> </strong></td>
					<td colspan="3" align="right"><input type="button" name="Submit2"
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

