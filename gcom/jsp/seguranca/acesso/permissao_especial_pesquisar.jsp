<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<%@page import="gcom.util.ConstantesSistema"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>

<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="PesquisarPermissaoEspecialActionForm" />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript">

function validarForm(form){

	if(testarCampoValorZero(document.PesquisarPermissaoEspecialActionForm.codigo, 'Código') && 
	testarCampoValorZero(document.PesquisarPermissaoEspecialActionForm.descricao, 'Descrição') && 
	testarCampoValorZero(document.PesquisarPermissaoEspecialActionForm.codigoOperacao, 'Operacao')) {

		if(validatePesquisarPermissaoEspecialActionForm(form)){
    		submeterFormPadrao(form);
		}
	}
}

function limparForm(form) {
	
	form.codigo.value = "";
	form.descricao.value = "";
	form.codigoOperacao.value = ""; 
	form.descricaoOperacao.value = "";	
}

//Recebe os dados do(s) popup(s)
function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {

  var form = document.PesquisarOperacaoActionForm;

   if (tipoConsulta == 'operacao') {
    form.idImovel.value = codigoRegistro;
    form.action = 'exibirPesquisarOperacaoAction.do'
    form.submit();
  }

}

</script>


</head>

<body leftmargin="5" topmargin="5" onload="resizePageSemLink(570, 350);" >
<html:form action="/pesquisarPermissaoEspecialAction" method="post"
	onsubmit="return validatePesquisarPermissaoEspecialActionForm(this);">
	<table width="452" border="0" cellspacing="5" cellpadding="0">
		<tr>
			<td width="452" valign="top" class="centercoltext">
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
					<td class="parabg">Pesquisar Permissão Especial</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>
					<td colspan="2">Preencha os campos para pesquisar uma
					permissão especial:</td>
				</tr>

				<tr>
					<td><strong>Código:</strong></td>
					<td><strong> <html:text property="codigo" size="10" maxlength="4" />
					</strong></td>
				</tr>

				<tr>
					<td width="162"><strong>Descri&ccedil;&atilde;o:</strong></td>

					<td><strong> <html:text property="descricao" size="60"
						maxlength="60" /> </strong></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td><html:radio property="tipoPesquisa" tabindex="5"
						value="<%=ConstantesSistema.TIPO_PESQUISA_INICIAL.toString()%>" />
					Iniciando pelo texto <html:radio property="tipoPesquisa"
						tabindex="6"
						value="<%=ConstantesSistema.TIPO_PESQUISA_COMPLETA.toString()%>" />
					Contendo o texto</td>
					<td>&nbsp;</td>
				</tr>
				

				<tr>
					<td><strong>Operação<font color="#000000">:</font></strong></td>
					<td><html:text property="codigoOperacao" size="4" maxlength="4"
						onkeypress="javascript:return validaEnter(event, 'exibirPesquisarOperacaoAction.do', 'codigoOperacao');" />
					<img width="23" height="21"
						src="<bean:message key="caminho.imagens"/>pesquisa.gif"
						style="cursor:hand;"
						onclick="abrirPopup('exibirPesquisarOperacaoAction.do', 400, 800);"
						alt="Pesquisar" /> <logic:present
						name="operacaoInexistente" scope="request">
						<html:text property="nomeOperacao" size="35"
							maxlength="35" readonly="true"
							style="background-color:#EFEFEF; border:0; color: #ff0000" />
					</logic:present> <logic:notPresent
						name="operacaoInexistente" scope="request">
						<html:text property="nomeOperacao" size="35"
							maxlength="35" readonly="true"
							style="background-color:#EFEFEF; border:0; color: #000000" />
					</logic:notPresent> <a href="javascript:limparFuncionalidade();">
					<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar" /></a></td>

				</tr>

				<tr>
					<td colspan="2">
					
						<logic:present	name="caminhoRetornoTelaPesquisaPermissaoEspecial">
						<INPUT TYPE="button" class="bottonRightCol" value="Voltar"
						onclick="redirecionarSubmit('${caminhoRetornoTelaPesquisaPermissaoEspecial}.do')" />
						</logic:present>
						<logic:notPresent	name="caminhoRetornoTelaPesquisaPermissaoEspecial">
						<INPUT TYPE="button" class="bottonRightCol" value="Fechar"
						onclick="window.close();" />
						</logic:notPresent>
						
						<input name="Button" type="button"
						class="bottonRightCol" value="Limpar" align="left"
						onclick="window.location.href='/gsan/exibirPesquisarPermissaoEspecialAction.do?menu=sim'"></td>
						
					<td align="right" height="24"><input type="button" name="Button" class="bottonRightCol"
						value="Pesquisar" onClick="javascript:validarForm(document.forms[0])" /></td>
					<td>&nbsp;</td>
				</tr>
				

			</table>
			</td>
		</tr>
		
		
	</table>
	<p>&nbsp;</p>
	
	
</html:form>
</body>
</html:html>