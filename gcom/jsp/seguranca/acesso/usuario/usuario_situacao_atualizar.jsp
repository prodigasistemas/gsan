<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
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
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="AtualizarSituacaoUsuarioActionForm" />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>

<script language="JavaScript">
<!-- Begin

	function validarForm(form){
				
		if(testarCampoValorZero(document.AtualizarSituacaoUsuarioActionForm.descricaoUsuarioSituacao, 'Descrição') 
		&& testarCampoValorZero(document.AtualizarSituacaoUsuarioActionForm.descricaoAbreviada, 'Descrição Abreviada')) { 		
		&&	testarCampoValorZero(document.AtualizarSituacaoUsuarioActionForm.indicadorUsoSistema, 'Indicador de Uso do Sistema')
		&&	testarCampoValorZero(document.AtualizarSituacaoUsuarioActionForm.indicadorUso, 'Indicador de Uso')
		&&	testarCampoValorZero(document.AtualizarSituacaoUsuarioActionForm.id, 'Codigo')
			if(validateAtualizarSituacaoUsuarioActionForm(form)){			
   				submeterFormPadrao(form);			
			}
		}	
	}

-->
</script>

</head>

<body leftmargin="5" topmargin="5"
	onload="javascript:setarFoco('descricaoUsuarioSituacao');">

<html:form action="/atualizarSituacaoUsuarioAction"
	name="AtualizarSituacaoUsuarioActionForm"
	type="gcom.gui.seguranca.acesso.usuario.AtualizarSituacaoUsuarioActionForm"
	method="post">

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
			<td width="625" valign="top" bgcolor="#003399" class="centercoltext">
			<table height="100%">

				<tr>
					<td></td>
				</tr>
			</table>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img border="0" src="imagens/parahead_left.gif" /></td>
					<td class="parabg">Atualizar Situa&ccedil;&atilde;o do
					Usu&aacute;rio</td>
					<td width="11" valign="top"><img border="0"
						src="imagens/parahead_right.gif" /></td>
				</tr>
			</table>
			<!--Fim Tabela Reference a Páginação da Tela de Processo-->
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>
					<td colspan="2">Para atualizar uma situa&ccedil;&atilde;o do
					usu&aacute;rio, informe os dados abaixo:</td>
				</tr>
				<tr>
					<td width="231"><strong>C&oacute;digo:</strong></td>

					<td width="380"><html:text property="id" size="4" maxlength="4" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000;" />
					</td>
				</tr>

				<tr>
					<td width="231"><strong>Descri&ccedil;&atilde;o:<font
						color="#FF0000">*</font></strong></td>
					<td width="380"><html:text property="descricaoUsuarioSituacao"
						size="30" maxlength="30" /></td>
				</tr>
				<tr>
					<td><strong>Descri&ccedil;&atilde;o Abreviada:<font color="#FF0000">*</font></strong></td>

					<td><html:text property="descricaoAbreviada" size="6" maxlength="6" /></td>
				</tr>
				<tr>
					<td><strong>Indicador de Uso Exclusivo do Sistema<font
						color="#000000">:</font><font color="#FF0000">*</font></strong></td>
					<td align="right">
					<div align="left"><html:radio property="indicadorUsoSistema"
						value="1" /> <strong>Sim <html:radio
						property="indicadorUsoSistema" value="2" /> Não </strong></div>
					</td>
				</tr>
				<tr>
					<td><strong>Indicador de Uso<font color="#000000">:</font><font
						color="#FF0000">*</font></strong></td>
					<td align="right">
					<div align="left"><html:radio property="indicadorUso" value="1" />
					<strong>Sim <html:radio property="indicadorUso" value="2" /> Não </strong></div>
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
					<td align="left"><input name="Button" type="reset" class="bottonRightCol"
						value="Desfazer" align="left"> <input name="Button" type="button"
						class="bottonRightCol" value="Cancelar" align="left"
						onclick="window.location.href='<html:rewrite page="/"/>'"></td>
					<td align="right" colspan="3"><input type="submit" name="Submit2"
						class="bottonRightCol" value="Atualizar"
						onClick="javascript:submeterFormPadrao(document.forms[0]);" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>
		</tr>
		<tr>

			<td colspan="3"></td>
		</tr>
	</table>

	<%@ include file="/jsp/util/rodape.jsp"%>

</html:form>
</body>
</html:html>
