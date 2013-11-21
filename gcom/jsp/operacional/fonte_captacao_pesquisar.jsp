<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ page import="gcom.util.ConstantesSistema"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>

<title>GSAN - Sistema Integrado de Gest&atilde;o de Servi&ccedil;os de Saneamento</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false" formName="PesquisarActionForm"
	dynamicJavascript="false" />

<script>

 var bCancel = false;

    function validatePesquisarActionForm(form) {
        if (bCancel)
      return true;
        else
       return validateLong(form) && validateCaracterEspecial(form) && 
       	testarCampoValorZero(document.forms[0].codigoFonteCaptacao, 'Código');
   }

    function caracteresespeciais () {
     this.ab = new Array("descricaoFonteCaptacao", "Descrição possui caracteres especiais.", new Function ("varName", " return this[varName];"));
     this.ac = new Array("descricaoAbreviadaFonteCaptacao", "Descrição Abreviada possui caracteres especiais.", new Function ("varName", " return this[varName];"));
    }

    function IntegerValidations () {
     this.aa = new Array("codigoFonteCaptacao", "Código Fonte de Captação deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
    }
    
    function limparForm(){
		var form = document.forms[0];
		
		form.codigoFonteCaptacao.value = "";
		form.descricaoFonteCaptacao.value = "";
		form.descricaoAbreviadaFonteCaptacao.value = "";
	}
	
	 function validarForm(form){
		form.submit();
    }
</script>

</head>

<body leftmargin="0" topmargin="0"
	onload="resizePageSemLink(480, 350);setarFoco('${requestScope.nomeCampo}');">

<html:form action="/pesquisarFonteCaptacaoAction" method="post"
	onsubmit="return validatePesquisarActionForm(this)">

	<table width="452" border="0" cellpadding="0">
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
					<td class="parabg">Pesquisar Fonte de Captação</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>
					<td colspan="4">Preencha os campos para pesquisar uma Fonte de Captação:</td>
					<logic:present scope="application" name="urlHelp">
						<td align="right"><a href="javascript:abrirPopupHelp('${applicationScope.urlHelp}cadastroFonteCaptacaoPesquisar', 500, 700);"><span style="font-weight: bold"><font color="#3165CE">Ajuda</font></span></a></td>									
		  			</logic:present>
		  			<logic:notPresent scope="application" name="urlHelp">
						<td align="right"><span style="font-weight: bold"><font color=#696969><u>Ajuda</u></font></span></td>									
	     			</logic:notPresent>    
				</tr>
			</table>

			<table width="100%" border="0">
				<tr>
					<td width="14%"><strong>Código:</strong></td>
					<td width="86%" colspan="3"><html:text maxlength="3"
						property="codigoFonteCaptacao" size="3" tabindex="1" /></td>
				</tr>
				<tr>
					<td height="0"><strong>Descrição:</strong></td>
					<td colspan="3"><html:text maxlength="30" property="descricaoFonteCaptacao"
						size="30" tabindex="2" /></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td colspan="3"><html:radio property="tipoPesquisaDescricao"
						value="<%=ConstantesSistema.TIPO_PESQUISA_INICIAL.toString()%>" />
					Iniciando pelo texto&nbsp;<html:radio
						property="tipoPesquisaDescricao" tabindex="5"
						value="<%=ConstantesSistema.TIPO_PESQUISA_COMPLETA.toString()%>" />
					Contendo o texto</td>
				</tr>
				<tr>
					<td height="0"><strong>Descrição Abreviada:</strong></td>
					<td colspan="3"><html:text maxlength="30" property="descricaoAbreviadaFonteCaptacao"
						size="30" tabindex="2" /></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td colspan="3">&nbsp;</td>
				</tr>

				<tr>
					<td colspan="4">
					<table width="100%" border="0">
						<tr>
							<td colspan="3"><INPUT TYPE="button" name="limpar"
								class="bottonRightCol" value="Limpar"
								onclick="document.forms[0].reset();limparForm();" /> 
								<logic:present name="caminhoRetornoTelaPesquisaFonteCaptacao" scope="session">
								<logic:notPresent name="Popup">
									<INPUT TYPE="button" name="voltar" class="bottonRightCol"
										value="Voltar"
										onclick="redirecionarSubmit('${caminhoRetornoTelaPesquisaFonteCaptacao}.do')" />
								</logic:notPresent>
								<logic:present name="Popup"></logic:present>
							</logic:present></td>
							<td align="right"><html:submit styleClass="bottonRightCol"
								value="Pesquisar" /></td>
						</tr>
					</table>
					<p>&nbsp;</p>
					</td>
				</tr>

			</table>
			</td>
		</tr>
	</table>
</html:form>
</body>
</html:html>
