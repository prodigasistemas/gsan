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
	formName="AtualizarDivisaoEsgotoActionForm" />

<SCRIPT LANGUAGE="JavaScript">
<!--

function validarForm(formulario){
 

	
	if(validateAtualizarDivisaoEsgotoActionForm(formulario)){
 		
 		submeterFormPadrao(formulario);
	}
	
 }

function limparForm() {
		var form = document.AtualizarDivisaoEsgotoActionForm;
		form.descricao.value = "";
	}
	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
		var form = document.forms[0];
		
		if (tipoConsulta == 'unidadeOrganizacional') {
     		form.unidadeOrganizacionalId.value = codigoRegistro;
	    	form.unidadeOrganizacionalDescricao.value = descricaoRegistro;
	    	form.unidadeOrganizacionalDescricao.style.color = '#000000';
		}	
	}
	function limparUnidade(){
		var form = document.forms[0];
 		form.unidadeOrganizacionalId.value = "";
     	form.unidadeOrganizacionalDescricao.value = "";
	}
//-->
</SCRIPT>

</head>

<body leftmargin="5" topmargin="5">

<html:form action="/atualizarDivisaoEsgotoAction.do" method="post">

	<INPUT TYPE="hidden" name="removerDivisaoEsgoto">
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
					<td class="parabg">Atualizar Divisão de Esgoto</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>

			<table width="100%" border="0">
				<tr>
					<td colspan="3">Para atualizar a divisão de esgoto informe os
					dados abaixo:</td>
					
				<tr>
					<td><strong>C&oacute;digo:</strong></td>
					<td><html:hidden property="id" />
					<bean:write	name="AtualizarDivisaoEsgotoActionForm" property="id" />
				</tr>	
				<tr>
					<td><strong>Descrição: <font color="#FF0000">*</font></strong></td>
					<td colspan="2"><span class="style2"> <html:text
						property="descricao" size="30" maxlength="30" /> </span></td>
				</tr>
				<tr>
					<td width="270"><strong>Unidade Organizacional: <font
						color="#FF0000">*</font></strong></td>
					<td colspan="3"><html:text property="unidadeOrganizacionalId" size="5"
						maxlength="5" tabindex="15"
						onkeypress="validaEnterComMensagem(event, 'exibirAtualizarDivisaoEsgotoAction.do?objetoConsulta=1', 'unidadeOrganizacionalId','Unidade Organizacional');"/> 
						<a
						href="javascript:abrirPopup('exibirPesquisarUnidadeOrganizacionalAction.do?menu=sim',275,480);">
					<img width="23" height="21" border="0"
						src="<bean:message key="caminho.imagens"/>pesquisa.gif"
						title="Pesquisar Distrito Operacional" /></a> <logic:present
						name="corUnidadeOrganizacional">

						<logic:equal name="corUnidadeOrganizacional" value="exception">
							<html:text property="unidadeOrganizacionalDescricao" size="42"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:equal>

						<logic:notEqual name="corUnidadeOrganizacional" value="exception">
							<html:text property="unidadeOrganizacionalDescricao" size="45"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEqual>

					</logic:present> <logic:notPresent name="corUnidadeOrganizacional">

						<logic:empty name="AtualizarDivisaoEsgotoActionForm"
							property="unidadeOrganizacionalId">
							<html:text property="unidadeOrganizacionalDescricao"
								value="" size="45" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:empty>
						<logic:notEmpty name="AtualizarDivisaoEsgotoActionForm"
							property="unidadeOrganizacionalId">
							<html:text property="unidadeOrganizacionalDescricao" size="45"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEmpty>

					</logic:notPresent> <a href="javascript:limparUnidade();"> <img
						src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar" /> </a></td>
				</tr>
				
				<tr>
					<td><strong>Indicador de Uso<font color="#FF0000">*</font></strong></td>
					<td colspan="2"><html:radio property="indicadorUso" tabindex="2" value="1"><strong>Ativo</strong></html:radio>
					<html:radio property="indicadorUso" tabindex="3" value="2" ><strong>Inativo</strong></html:radio>
				  </td>
					<td width="410">&nbsp;</td>
				</tr>
				

				<tr>
					<td><strong> <font color="#FF0000"></font></strong></td>
					<td width="241" align="right">
					<div align="left"><strong><font color="#FF0000">*</font></strong>
					Campos obrigat&oacute;rios</div>
				  </td>
				</tr>

				<tr>
					<td align="left" colspan="2"><input type="button"
						name="ButtonCancelar" class="bottonRightCol" value="Voltar"
						onClick="window.history.go(-1)"> <input type="button"
						name="ButtonReset" class="bottonRightCol" value="Desfazer"
						onClick="(document.forms[0]).reset()"> <input type="button"
						name="ButtonCancelar" class="bottonRightCol" value="Cancelar"
						onClick="javascript:window.location.href='/gsan/telaPrincipal.do'">
				  </td>
				  <td>&nbsp;</td>
					<td width="66" align="right"><input type="button"
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

