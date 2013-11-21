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
	formName="FiltrarPrioridadeTipoServicoActionForm"
	dynamicJavascript="true" />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript">

function validaForm(form){

	if(testarCampoValorZero(document.FiltrarPrioridadeTipoServicoActionForm.codigo, 'Código da Prioridade') &&
	testarCampoValorZero(document.FiltrarPrioridadeTipoServicoActionForm.descricao, 'Descrição') && 
	testarCampoValorZero(document.FiltrarPrioridadeTipoServicoActionForm.abreviatura, 'Abreviatura')) {

		if(validateFiltrarPrioridadeTipoServicoActionForm(form)){

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

    function verificarChecado(valor){
		form = document.forms[0];
		if(valor == "1"){
		 	form.indicadorAtualizar.checked = true;
		 }else{
		 	form.indicadorAtualizar.checked = false;
		}
	}
	

</script>


</head>

<body leftmargin="5" topmargin="5" onload="verificarChecado('${indicadorAtualizar}');setaFocus();">

<html:form action="/filtrarPrioridadeTipoServicoAction" method="post"
	name="FiltrarPrioridadeTipoServicoActionForm"
	type="gcom.gui.atendimentopublico.ordemservico.FiltrarPrioridadeTipoServicoAction">

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
					<td class="parabg">Filtrar Prioridade do Tipo de Serviço</td>
					<td width="11" valign="top"><img border="0"
						src="imagens/parahead_right.gif" /></td>
				</tr>

			</table>
			<!--Fim Tabela Reference a Páginação da Tela de Processo-->
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>
					<td height="10" colspan="2" width="80%">Para filtrar Prioridade do Tipo de
					Serviço informe os dados abaixo:</td>
					<td width="20%" align="right"><html:checkbox property="indicadorAtualizar" value="1" /><strong>Atualizar</strong></td>
				</tr>
				</table>
				<table width="100%" border="0">	
				<tr>
					<td width="162"><strong>Código da prioridade:<font color="#FF0000"></font></strong></td>

					<td><strong> <html:text property="codigo" size="4" maxlength="4" />
					</strong></td>
				</tr>
				<tr>
					<td width="162"><strong>Descri&ccedil;&atilde;o da prioridade:<font
						color="#FF0000"></font></strong></td>

					<td><strong> <html:text property="descricao" size="30"
						maxlength="30" /> </strong></td>
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
				<tr>
					<td><strong>Abreviatura da prioridade:<font color="#FF0000"></font></strong></td>
					<td><strong> <html:text property="abreviatura" size="5"
						maxlength="5" /> </strong></td>
				</tr>

				<tr>
					<td><strong>Horas para Execução do Serviço:<font color="#FF0000"></font></strong></td>
					<td><strong> <html:text property="qtdHorasInicio" size="2"
						maxlength="2" /> </strong></td>
				</tr>

				<tr>
					<td><strong>Horas para Finalização do Serviço:<font color="#FF0000"></font></strong></td>
					<td><strong> <html:text property="qtdHorasFim" size="2"
						maxlength="2" /> </strong></td>
				</tr>
				<tr>
					<td><strong> <font color="#FF0000"> <input type="button"
						name="Submit22" class="bottonRightCol" value="Limpar"
						onClick="javascript:window.location.href='/gsan/exibirFiltrarPrioridadeTipoServicoAction.do?menu=sim'"><!-- <input type="button"
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

