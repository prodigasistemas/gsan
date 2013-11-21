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

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false"
	formName="AtualizarPrioridadeTipoServicoActionForm"
	dynamicJavascript="true" />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript">

function validaForm(form){

	if(testarCampoValorZero(document.AtualizarPrioridadeTipoServicoActionForm.codigo, 'Código da Prioridade') &&
	testarCampoValorZero(document.AtualizarPrioridadeTipoServicoActionForm.descricao, 'Descrição') && 
		testarCampoValorZero(document.AtualizarPrioridadeTipoServicoActionForm.abreviatura, 'Horas para Execução do Serviço') && 
		testarCampoValorZero(document.AtualizarPrioridadeTipoServicoActionForm.qtdHorasInicio, 'Horas para Execução do Serviço') &&
	testarCampoValorZero(document.AtualizarPrioridadeTipoServicoActionForm.qtdHorasFim, 'Horas para Finalização do Serviço')) {

		if(validateAtualizarPrioridadeTipoServicoActionForm(form)){

    		submeterFormPadrao(form);
		}
	}

function limparForm(form) {
		form.codigo.value = "";
		form.descricao.value = "";
		form.abreviatura.value = "";
		form.qtdHorasInicio.value = "";
	    form.qtdHorasFim.value = "";
		
		
	
	}
}

	function reload() {
		var form = document.AtualizarPrioridadeTipoServicoActionForm;
		form.action = "/gsan/exibirAtualizarPrioridadeTipoServicoAction.do";
		form.submit();
	}  
	
 

</script>


</head>

<body leftmargin="5" topmargin="5">

<html:form action="/atualizarPrioridadeTipoServicoAction" method="post"
	name="AtualizarPrioridadeTipoServicoActionForm"
	type="gcom.gui.atendimentopublico.ordemservico.AtualizarPrioridadeTipoServicoAction">

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
					<td class="parabg">Atualizar Prioridade do Tipo de Serviço</td>
					<td width="11" valign="top"><img border="0"
						src="imagens/parahead_right.gif" /></td>
				</tr>

			</table>
			<!--Fim Tabela Reference a Páginação da Tela de Processo-->
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>

					<td colspan="2">Para atualizar a Prioridade do Tipo de Serviço,
					informe os dados abaixo:</td>
				</tr>



				<tr>
					<td width="162"><strong>Código da Prioridade:<font color="#FF0000"></font></strong></td>

					<td><strong> <html:text property="codigo" size="4" maxlength="4"
						readonly="true"
						style="background-color:#EFEFEF; border:0; color: #000000;" /> </strong></td>
				</tr>
				<tr>
					<td width="162"><strong>Descri&ccedil;&atilde;o da Prioridade:<font
						color="#000000"><font color="#FF0000">*</font></strong></td>

					<td><strong> <html:text property="descricao" size="50"
						maxlength="50" /> </strong></td>
				</tr>
				<tr>
					<td><strong>Abreviatura da Prioridade:<font color="#FF0000"></font></strong></td>
					<td><strong> <html:text property="abreviatura" size="5"
						maxlength="5" /> </strong></td>
				</tr>
				<tr>
					<td width="162"><strong>Horas para Execução do Serviço:<font
						color="#000000"><font color="#FF0000">*</font></strong></td>

					<td><strong> <html:text property="qtdHorasInicio" size="2"
						maxlength="2" /> </strong></td>
				</tr>
				<tr>
					<td width="162"><strong>Horas para Finalização do Serviço:<font
						color="#000000"><font color="#FF0000">*</font></strong></td>

					<td><strong> <html:text property="qtdHorasFim" size="2"
						maxlength="2" /> </strong></td>
				</tr>
				
				<tr>
					<td><strong>Indicador de uso:<font color="#FF0000">*</font></strong></td>
						<td><html:radio property="indicadorUso" value="1" tabindex="5"/>Ativo
							<html:radio property="indicadorUso" value="2" tabindex="6"/>Inativo
						</td>
				</tr>		
				
				<tr>
				    <td></td>
					<td>
					<div align="left" ><font color="#FF0000">*</font> Campos obrigatórios</div>
					</td>
				</tr>						

				<tr>
					<td colspan="2"><font color="#FF0000"><logic:present name="manter"
						scope="session">
						<input type="button" name="ButtonReset" class="bottonRightCol"
							value="Voltar"
							onClick="javascript:window.location.href='/gsan/exibirManterPrioridadeTipoServicoAction.do'">
					</logic:present><logic:notPresent name="manter" scope="session">
						<input type="button" name="ButtonReset" class="bottonRightCol"
							value="Voltar"
							onClick="javascript:window.location.href='/gsan/exibirFiltrarPrioridadeTipoServicoAction.do'">
					</logic:notPresent> <input type="button" name="ButtonReset"
						class="bottonRightCol" value="Desfazer"
						onClick="window.location.href='<html:rewrite page="/exibirAtualizarPrioridadeTipoServicoAction.do?desfazer=sim"/>'">
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

