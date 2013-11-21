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
	formName="AtualizarConsumoAnormalidadeActionForm" />

<SCRIPT LANGUAGE="JavaScript">
<!--

function validarForm(formulario){
if(validateAtualizarConsumoAnormalidadeActionForm(formulario)){
submeterFormPadrao(formulario);
		}
  	}

//-->
</SCRIPT>

</head>

<body leftmargin="5" topmargin="5">

<html:form action="/atualizarConsumoAnormalidadeAction.do" method="post">

	<INPUT TYPE="hidden" name="removerConsumoAnormalidade">
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
					<td class="parabg">Atualizar Anormalidade de Consumo</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>

			<table width="100%" border="0">
				<tr>
					<td colspan="2">Para Atualizar uma Anormalidade de Consumo, informe
					os dados abaixo:</td>
				<tr>


					<td width="524"><strong>C&oacute;digo:</strong></td>


					<td width="446"><html:hidden property="id" /> <bean:write
						name="AtualizarConsumoAnormalidadeActionForm" property="id" /></td>
				</tr>

				<tr>


					<td width="524"><strong>Descri&ccedil;&atilde;o: <font
						color="#FF0000">*</font></strong></td>


					<td colspan="2" width="446"><span class="style2"> <html:text
						property="descricao" size="30" maxlength="25" /> </span></td>
				</tr>
				<tr>


					<td width="524"><strong>Descri&ccedil;&atilde;o Abreviada: </strong></td>


					<td colspan="2" width="446"><span class="style2"> <html:text
						property="descricaoAbreviada" size="5" maxlength="5" /> </span></td>
				</tr>
				<tr>


					<td width="524"><strong>Mensagem da Conta: </strong></td>


					<td width="446"><html:textarea property="mensagemConta" cols="40"
						rows="4"
						onkeyup="limitTextArea(document.forms[0].mensagemConta, 100, document.getElementById('utilizado'), document.getElementById('limite'));" /><br>
					<strong><span id="utilizado">0</span>/<span id="limite">100</span></strong>
					</td>
				</tr>
				
				<tr>
					<td><strong>Indicador de Permissão Especial para Revisar: </strong></td>
					<td>
						<strong> 
						<html:radio property="indicadorRevisaoComPermissaoEspecial" value="1" /> Sim 
						<html:radio property="indicadorRevisaoComPermissaoEspecial" value="2" /> N&atilde;o 
						</strong>
					</td>
				</tr>
				
				<tr>
					<td width="524"><strong>Indicador de uso: <font color="#FF0000">*</font></strong></td>
					<td width="446"><html:radio property="indicadorUso" value="1"
						tabindex="5" /><strong>Ativo <html:radio property="indicadorUso"
						value="2" tabindex="6" />Inativo</strong>
					</td>
				</tr>

				<tr>


					<td width="524"><strong> <font color="#FF0000"></font></strong></td>


					<td align="right" width="446">
					<div align="left"><strong><font color="#FF0000">*</font></strong>
					Campos obrigat&oacute;rios</div>
					</td>

				</tr>

				<tr>


					<td width="524" align="left"><input type="button"
						name="ButtonCancelar" class="bottonRightCol" value="Voltar"
						onClick="window.history.go(-1)"> <input type="button"
						name="ButtonReset" class="bottonRightCol" value="Desfazer"
						onClick="(document.forms[0]).reset()"> <input type="button"
						name="ButtonCancelar" class="bottonRightCol" value="Cancelar"
						onClick="javascript:window.location.href='/gsan/telaPrincipal.do'">
					</td>


					<td align="right" width="446"><input type="button"
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

