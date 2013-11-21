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
	formName="InserirPrioridadeTipoServicoActionForm" />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript">

function validarForm(form){

	if((testarCampoValorZero(document.InserirPrioridadeTipoServicoActionForm.descricao, 'Descrição da Prioridade')) && 
	   testarCampoValorZero(document.InserirPrioridadeTipoServicoActionForm.abreviatura, 'Abreviatura da Prioridade')&&
	   testarCampoValorZero(document.InserirPrioridadeTipoServicoActionForm.qtdHorasInicio, 'Horas para Execução do Serviço')&&
	    testarCampoValorZero(document.InserirPrioridadeTipoServicoActionForm.qtdHorasFim, 'Abreviatura da Prioridade')
	  
	   ) {			
			if(validateInserirPrioridadeTipoServicoActionForm(form)){
    		submeterFormPadrao(form);
			
		
		}
	}
}


function limparForm(form) {
	
		form.descricao.value = "";
		form.abreviatura.value = "";
		form.indicadorExistenciaOsReferencia[0].checked = false;
		form.indicadorExistenciaOsReferencia[1].checked = false;
		form.situacaoOSAntes.value = "-1"; 
		form.situacaoOSApos.value = "-1";
		form.tipoServicao.value = "";
	}


	function limparNomeTipoServico() {
	var form = document.InserirPrioridadeTipoServicoActionForm;
		form.nomeTipoServico.value = "";
	}
	
</script>


</head>

<body leftmargin="5" topmargin="5"
	onload="javascript:setarFoco('${requestScope.nomeCampo}');">

<html:form action="/inserirPrioridadeTipoServicoAction" method="post"
	name="InserirPrioridadeTipoServicoActionForm"
	type="gcom.gui.atendimentopublico.ordemservico.InserirPrioridadeTipoServicoActionForm"
	onsubmit="return validateInserirPrioridadeTipoServicoActionForm(this);">
	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>

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
			<td width="625" valign="top" class="centercoltext">
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
					<td class="parabg">Inserir Prioridade do Tipo de Serviço</td>
					<td width="11" valign="top"><img border="0"
						src="imagens/parahead_right.gif" /></td>
				</tr>

			</table>
			<!--Fim Tabela Reference a Páginação da Tela de Processo-->
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>

					<td colspan="2">Para adicionar a prioridade do tipo de serviço,
					informe os dados abaixo:</td>
				</tr>
				<tr>
					<td width="162"><strong>Descri&ccedil;&atilde;o:<font
						color="#FF0000">*</font></strong></td>

					<td><strong> <html:text property="descricao" size="60"
						maxlength="50" /> </strong></td>
				</tr>
				<tr>
					<td><strong>Abreviatura:</strong></td>
					<td><strong> <html:text property="abreviatura" size="5"
						maxlength="5" /> </strong></td>
				</tr>
				<tr>
					<td><strong>Quantidade de Horas para início da execução:</strong></td>
					<td><strong> <html:text property="qtdHorasInicio" size="2"
						maxlength="2" /> </strong></td>
				</tr>
				<tr>
					<td><strong>Quantidade de Horas para fim da execução:</strong></td>
					<td><strong> <html:text property="qtdHorasFim" size="2"
						maxlength="2" /> </strong></td>
				</tr>



				<tr>
					<td height="19"><strong> <font color="#FF0000"></font></strong></td>
					<td align="right">
					<div align="left"><strong><font color="#FF0000">*</font></strong>
					Campos obrigat&oacute;rios</div>
					</td>
				</tr>
				<tr>
					<td colspan="2"><input name="Button" type="button"
						class="bottonRightCol" value="Desfazer" align="left"
						onclick="window.location.href='/gsan/exibirInserirPrioridadeTipoServicoAction.do?menu=sim'">
					<input name="Button" type="button" class="bottonRightCol"
						value="Cancelar" align="left"
						onclick="window.location.href='/gsan/telaPrincipal.do'"></td>
					<td align="right" height="24"><input type="button" name="Button"
						class="bottonRightCol" value="Inserir"
						onClick="javascript:validarForm(document.forms[0]);" /></td>
					<td>&nbsp;</td>
				</tr>
			</table>
			<p>&nbsp;</p>
		</tr>
	</table>
	<tr>
		<td colspan="3"><%@ include file="/jsp/util/rodape.jsp"%>
	</tr>
</html:form>
</body>
</html:html>

