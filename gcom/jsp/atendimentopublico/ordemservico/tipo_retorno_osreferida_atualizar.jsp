<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>




<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@page import="gcom.util.ConstantesSistema" isELIgnored="false"%>
<html:html>

<head>

<title>GSAN - Sistema Integrado de Gest&atilde;o de Servi&ccedil;os de Saneamento</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false" 
	formName="AtualizarTipoRetornoOrdemServicoReferidaActionForm"
	dynamicJavascript="true" />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript">

function validaForm(form){

	if(testarCampoValorZero(document.AtualizarTipoRetornoOrdemServicoReferidaActionForm.codigoTipoRetorno, 'Código do Tipo de Retorno') &&
	testarCampoValorZero(document.AtualizarTipoRetornoOrdemServicoReferidaActionForm.descricao, 'Descrição') && 
	testarCampoValorZero(document.AtualizarTipoRetornoOrdemServicoReferidaActionForm.abreviatura, 'Abreviatura')) {

		if(validateAtualizarTipoRetornoOrdemServicoReferidaActionForm(form)){

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

	function reload() {
		var form = document.InserirTipoRetornoOrdemServicoReferidaActionForm;
		form.action = "/gsan/exibirAtualizarTipoRetornoOrdemServicoReferidaAction.do";
		form.submit();
	}  
	
	function pesquisarTipoOSReferencia() {
		alert("entro1");
		var form = AtualizarTipoRetornoOrdemServicoReferidaActionForm;
		if (form.servicoTipoReferencia.selectedIndex != 0) {
			alert("entro3");		
			reload();
		} else {
			alert("entro2");
			form.trocaServico[0].checked = false;
			form.trocaServico[1].checked = false;			
			form.trocaServico[0].disabled = false;
			form.trocaServico[1].disabled = false;			
			form.situacao[0].checked = false;
			form.situacao[1].checked = false;			
			form.situacao[0].disabled = false;
			form.situacao[1].disabled = false;			
			form.atendimentoMotivoEncerramento.disabled = false;
			form.atendimentoMotivoEncerramento.selectedIndex = 0;			
		}
	} 

</script>


</head>

<body leftmargin="5" topmargin="5">

<html:form action="/atualizarTipoRetornoOrdemServicoReferidaAction"
	method="post" name="AtualizarTipoRetornoOrdemServicoReferidaActionForm"
	type="gcom.gui.atendimentopublico.ordemservico.AtualizarTipoRetornoOrdemServicoReferidaAction">

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
					<td class="parabg">Atualizar Tipo Retorno da OS_Referida</td>
					<td width="11" valign="top"><img border="0"
						src="imagens/parahead_right.gif" /></td>
				</tr>

			</table>
			<!--Fim Tabela Reference a Páginação da Tela de Processo-->
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>

					<td colspan="2">Para atualizar o tipo de retorno da OS_Referida,
					informe os dados abaixo:</td>
				</tr>



				<tr>
					<td width="162"><strong>Código do Tipo de Retorno:<font
						color="#FF0000"></font></strong></td>

					<td><strong> <html:text property="codigoTipoRetorno" size="4"
						maxlength="4" readonly="true"
						style="background-color:#EFEFEF; border:0; color: #000000;" /> </strong></td>
				</tr>
				<tr>
					<td width="162"><strong>Descri&ccedil;&atilde;o:<font
						color="#000000"><font color="#FF0000">*</font></strong></td>

					<td><strong> <html:text property="descricao" size="50"
						maxlength="50" /> </strong></td>
				</tr>
				<tr>
					<td><strong>Abreviatura:<font color="#FF0000"></font></strong></td>
					<td><strong> <html:text property="abreviatura" size="5"
						maxlength="5" /> </strong></td>
				</tr>
				<tr>
					<td><strong>Referência do Tipo de Serviço:<font color="#000000"><font
						color="#FF0000">*</font></strong></td>
					<td><html:select property="servicoTipoReferencia" onchange="javascript:pesquisarTipoOSReferencia();">

						<html:option
							value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
						<html:options collection="colecaoServicoTipoReferencia"
							labelProperty="descricao" property="id" />
					</html:select></td>
				</tr>
				<tr>
					<td width="40%"><strong>Indicador de Deferimento:<font
						color="#FF0000">*</font></strong></td>
					<td width="60%"><strong> <html:radio property="deferimento"
						value="1" /> <strong>Deferido&nbsp;&nbsp;<html:radio
						property="deferimento" value="2" /> Indeferido&nbsp;</strong> </strong></td>
				</tr>
				<tr>
					<td width="40%"><strong>Indicador de Troca de Serviço:<font
						color="#FF0000">*</font></strong></td>
					<td width="60%"><strong> <html:radio property="trocaServico"
						value="1" /> <strong>Sim&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<html:radio property="trocaServico" value="2" /> Não</strong> </strong></td>
				</tr>
				<tr>
					<td width="40%"><strong>Código da Situação:</strong></td>
					<td width="60%"><strong> <html:radio property="situacao" value="1" />
					<strong>Pendente<html:radio property="situacao" value="2" />
					Encerrada<html:radio property="situacao" value="" />Nulo</strong>
					</strong></td>
				</tr>
				<tr>
					<td><strong>Motivo de Encerramento do Atendimento<font
						color="#000000"></font>:</strong></td>
					<td><html:select property="atendimentoMotivoEncerramento">

						<html:option
							value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
						<html:options collection="colecaoAtendimentoMotivoEncerramento"
							labelProperty="descricao" property="id" />
					</html:select></td>
				</tr>
				<tr>
					<td width="40%"><strong>Indicador de Uso:<font color="#FF0000">*</font></strong></td>
					<td width="60%"><strong> <html:radio property="indicadorUso"
						value="1" /> <strong>Ativos&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <html:radio
						property="indicadorUso" value="2" /> Inativos</strong> </strong></td>
				</tr>
				<tr>
					<td><strong> <font color="#FF0000"></font> </strong></td>
					<td width="330" align="right">
					<div align="left"><strong> <font color="#FF0000">*</font> </strong>
					Campos obrigat&oacute;rios</div>
					</td>
				</tr>
				<tr>
					<td colspan="2"><font color="#FF0000"><logic:present name="filtrar"
						scope="session">
						<input type="button" name="ButtonReset" class="bottonRightCol"
							value="Voltar"
							onClick="javascript:window.location.href='/gsan/exibirFiltrarTipoRetornoOrdemServicoReferidaAction.do'">
					</logic:present><logic:notPresent name="filtrar" scope="session">
						<input type="button" name="ButtonReset" class="bottonRightCol"
							value="Voltar"
							onClick="javascript:window.location.href='/gsan/filtrarTipoRetornoOrdemServicoReferidaAction.do'">
					</logic:notPresent> <input type="button" name="ButtonReset"
						class="bottonRightCol" value="Desfazer"
						onClick="window.location.href='<html:rewrite page="/exibirAtualizarTipoRetornoOrdemServicoReferidaAction.do?desfazer=S&idRegistroAtualizacaoValor=${requestScope.idRegistroAtualizacao}"/>'">
					<input type="button" name="ButtonCancelar" class="bottonRightCol"
						value="Cancelar"
						onClick="javascript:window.location.href='/gsan/telaPrincipal.do'">
					</font></td>
					<td align="right"><input type="button" name="Button"
						class="bottonRightCol" value="Atualizar"
						onClick="javascript:validaForm(document.forms[0]);" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			</td>
			<!-- Rodapé -->
			<%@ include file="/jsp/util/rodape.jsp"%>
	</table>


</html:form>
</body>
</html:html>

