<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ page import="gcom.util.ConstantesSistema" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>

<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="PesquisarAvisoDeducoesActionForm"
	dynamicJavascript="false" />

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript">
<!--

	function enviar(){
		if (validatePesquisarAvisoDeducoesActionForm(document.forms[0])) {
   			opener.recuperarDadosDeducao(document.forms[0].tipoDeducaoInclusao.value, document.forms[0].valorDeducaoInclusao.value);
   			self.close();
   		}
	}

	var bCancel = false;

    function validatePesquisarAvisoDeducoesActionForm(form) {                                                                   
			
      if (bCancel) {
      	return true; 
      } else {
      	return validateRequired(form) && validateCaracterEspecial(form) && validateDecimal(form);
      }
   } 	

    function caracteresespeciais () { 
     this.aa = new Array("valorDeducaoInclusao", "Valor da Dedução possui caracteres especiais.", new Function ("varName", " return this[varName];"));
    }
    
    function FloatValidations () {
     this.aa = new Array("valorDeducaoInclusao", "Valor da Dedução deve somente conter números decimais positivos.", new Function ("varName", " return this[varName];"));
    }
    
    function required () {
	 this.aa = new Array("tipoDeducaoInclusao", "Informe Tipo da Dedução.", new Function ("varName", " return this[varName];"));
	 this.ab = new Array("valorDeducaoInclusao", "Informe Valor da Dedução.", new Function ("varName", " return this[varName];"));
    }

-->
</script>

</head>
<body>

<logic:present name="reload" scope="request">
	<body leftmargin="5" topmargin="5"
		onload="chamarReloadSemParametro();window.close();" onload="resizePageSemLink(790, 410);">
</logic:present>

<logic:notPresent name="reload" scope="request">
	<body leftmargin="5" topmargin="5" onload="resizePageSemLink(588, 250);">
</logic:notPresent>

<html:form action="/exibirAdicionarAvisoDeducoesAction"
	name="PesquisarAvisoDeducoesActionForm"
	type="gcom.gui.arrecadacao.banco.PesquisarAvisoDeducoesActionForm"
	method="post" onsubmit="validatePesquisarAvisoDeducoesActionForm(this)">

	<table width="550" border="0" cellspacing="5" cellpadding="0">
		<tr>



			<td width="625" valign="top" bgcolor="#003399" class="centercoltext">
			<table height="100%">

				<tr>
					<td></td>
				</tr>
			</table>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">

				<tr>
					<td width="11"><img src="imagens/parahead_left.gif" border="0" /></td>
					<td class="parabg">Adicionar Aviso Deduções</td>

					<td width="11"><img src="imagens/parahead_right.gif" border="0" /></td>
				</tr>
			</table>
			<table width="100%" border="0">
				<tr>
					<td>
					<table width="100%" border="0">
						<tr>
							<td colspan="4">Preencha os campos para inserir uma
							dedu&ccedil;&atilde;o no aviso banc&aacute;rio:</td>
						</tr>
						<tr>
							<td width="28%" height="24"><strong>Tipo da
							Dedu&ccedil;&atilde;o:<font color="#FF0000">*</font></strong></td>
							<td width="72%" colspan="3"><html:select
								property="tipoDeducaoInclusao"
								name="PesquisarAvisoDeducoesActionForm">
								<html:option value="<%= ""+ConstantesSistema.NUMERO_NAO_INFORMADO %>">&nbsp;</html:option>
								<html:options collection="collectionDeducaoTipo" property="id"
									labelProperty="descricaoAbreviado" />
							</html:select></td>
						</tr>
						<tr>
							<td width="28%" height="24"><strong>Valor da
							Dedu&ccedil;&atilde;o:<font color="#FF0000">*</font></strong></td>
							<td colspan="3"><html:text property="valorDeducaoInclusao" onkeyup="formataValorMonetario(this, 14)" style="text-align:right;"
								size="14" maxlength="14" /></td>
						</tr>
						<tr>
							<td height="24">&nbsp;</td>
							<td colspan="3"><font color="#FF0000">*</font> Campo
							Obrigat&oacute;rio</td>
						</tr>
						<tr>
							<td height="27" colspan="4">
							<div align="right"><input name="Button" type="button"
								class="bottonRightCol" onclick="javascript:enviar();"
								value="Inserir"> <input name="Button2" type="button"
								class="bottonRightCol" value="Fechar"
								onClick="javascript:window.close();"></div>
							</td>
						</tr>
					</table>
					<p>&nbsp;</p>
					</td>
				</tr>
			</table>
			</td>
		</tr>
	</table>

	</body>

</html:form>
</html:html>
