<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan"%>

<%@ page import="gcom.util.ConstantesSistema"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false"
	formName="AtualizarLigacaoEsgotoSituacaoActionForm" />

<SCRIPT LANGUAGE="JavaScript">
<!--

function validarForm(formulario){
	if(validateAtualizarLigacaoEsgotoSituacaoActionForm(formulario)){
		if(validaTodosRadioButton(formulario)){
			submeterFormPadrao(formulario);
		}
	}	
}
  	
function validaRadioButton(nomeCampo,mensagem){
		
	var alerta = "";
	if(!nomeCampo[0].checked && !nomeCampo[1].checked){
		alerta = "Informe " + mensagem +".";
	}
		return alerta;
   	}
   
function validaTodosRadioButton(){
	var form = document.forms[0];
	var mensagem = "";
		
	if(validaRadioButton(form.indicadorFaturamentoSituacao,"Indicador de Faturamento.") != ""){
		mensagem = mensagem + validaRadioButton(form.indicadorFaturamentoSituacao,"Indicador de Faturamento.")+"\n";
	}
	if(validaRadioButton(form.indicadorExistenciaLigacao,"Indicador de Existência de Ligação.") != ""){
		mensagem = mensagem + validaRadioButton(form.indicadorExistenciaLigacao,"Indicador de Existência de Ligação.")+"\n";
	}
	if(validaRadioButton(form.indicadorExistenciaRede,"Indicador de Existência de Rede.") != ""){
		mensagem = mensagem + validaRadioButton(form.indicadorExistenciaRede,"Indicador de Existência de Rede.")+"\n";
	}
	if(mensagem != ""){
		alert(mensagem);
		return false;
	}else{
		return true;
	}
} 

//-->
</SCRIPT>

</head>

<body leftmargin="5" topmargin="5">

<html:form action="/atualizarLigacaoEsgotoSituacaoAction.do" method="post">

	<INPUT TYPE="hidden" name="removerLigacaoEsgotoSituacao">
	<INPUT TYPE="hidden" name="limparCampos" id="limparCampos">


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

			<td width="625" valign="top" class="centercoltext">

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
					<td class="parabg">Atualizar Situa&ccedil;&atilde;o de Liga&ccedil;&atilde;o de Esgoto</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>

			<table width="100%" border="0">
				<tr>
					<td colspan="2">Para Atualizar uma liga&ccedil;&atilde;o de Esgoto, informe os
					dados abaixo:</td>
				<tr>
					<td><strong>C&oacute;digo:</strong></td>
					<td><html:hidden property="id" /> <bean:write
						name="AtualizarLigacaoEsgotoSituacaoActionForm" property="id" /></td>
				</tr>

				<tr>
					<td><strong>Descri&ccedil;&atilde;o: <font color="#FF0000">*</font></strong></td>
					<td colspan="2"><span class="style2"> <html:text
						property="descricao" size="20" maxlength="20" /> </span></td>
				</tr>
				<tr>
					<td><strong>Descri&ccedil;&atilde;o Abreviada: <font color="#FF0000">*</font></strong></td>
					<td colspan="2"><span class="style2"> <html:text
						property="descricaoAbreviada" size="3" maxlength="3" /> </span></td>
				</tr>
								<tr>
					<td><strong>Consumo M&iacute;nimo: <font color="#FF0000">*</font></strong></td>
					<td colspan="2"><span class="style2"> <html:text
						property="consumoMinimoFaturamento" size="10" maxlength="10" /> </span></td>
				</tr>

				<tr>
					<td><strong>Indicador de Faturamento: <font
						color="#FF0000">*</font></strong></td>
					<td><strong> <html:radio property="indicadorFaturamentoSituacao"
						value="1" /> <strong>Sim <html:radio
						property="indicadorFaturamentoSituacao" value="2" /> N&atilde;o</strong>
					</strong></td>
				</tr>
				
				<tr>
					<td><strong>Indicador de Exist&ecirc;ncia de Rede: <font
						color="#FF0000">*</font></strong></td>
					<td><strong> <html:radio property="indicadorExistenciaRede"
						value="1" /> <strong>Sim <html:radio
						property="indicadorExistenciaRede" value="2" /> N&atilde;o</strong>
					</strong></td>
				</tr>
				
				<tr>
					<td><strong>Indicador de Exist&ecirc;ncia de Liga&ccedil;&atilde;o: <font
						color="#FF0000">*</font></strong></td>
					<td><strong> <html:radio property="indicadorExistenciaLigacao"
						value="1" /> <strong>Sim <html:radio
						property="indicadorExistenciaLigacao" value="2" /> N&atilde;o</strong>
					</strong></td>
				</tr>

				<tr>
					<td><strong>Indicador de uso: <font	color="#FF0000">*</font></strong></td>
					<td><html:radio property="indicadorUso" value="1" tabindex="5" /><strong>Ativo
					<html:radio property="indicadorUso" value="2" tabindex="6" />Inativo</strong>
					</td>
				</tr>

				<tr>
					<td><strong> <font color="#FF0000"></font></strong></td>
					<td align="right">
					<div align="left"><strong><font color="#FF0000">*</font></strong>
					Campos obrigat&oacute;rios</div>
					</td>
				</tr>

				<tr>
					<td width="40%" align="left"><input type="button"
						name="ButtonCancelar" class="bottonRightCol" value="Voltar"
						onClick="window.history.go(-1)"> <input type="button"
						name="ButtonReset" class="bottonRightCol" value="Desfazer"
						onClick="(document.forms[0]).reset()"> <input type="button"
						name="ButtonCancelar" class="bottonRightCol" value="Cancelar"
						onClick="javascript:window.location.href='/gsan/telaPrincipal.do'">
					</td>
					<td align="right"><input type="button"
						onClick="javascript:validarForm(document.forms[0]);"
						name="botaoAtualizar" class="bottonRightCol" value="Atualizar"></td>
				</tr>
			</table>
			</td>
		</tr>
	</table>

	<%@ include file="/jsp/util/rodape.jsp"%>

</html:form>
</body>
</html:html>

