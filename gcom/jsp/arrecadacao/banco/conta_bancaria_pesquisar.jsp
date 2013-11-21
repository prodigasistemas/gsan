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
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="ContaBancariaPesquisarActionForm" dynamicJavascript="false" />

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript">
<!-- Begin

var bCancel = false;

function validateContaBancariaPesquisarActionForm(form) {
     if (bCancel)
       return true;
     else
      return validateCaracterEspecial(form);
}

function caracteresespeciais () { 
 	this.aa = new Array("numeroConta", "Número da Conta possui caracteres especiais.", new Function ("varName", " return this[varName];"));
}

function validarForm(form){
	if(validateContaBancariaPesquisarActionForm(form)){
	    form.submit();
	}
}
function limparForm(){
	var form = document.forms[0];

	form.numeroConta.value="";
    form.idAgencia.value = "-1";
    form.idBanco.value = "-1";
	form.numeroConta.focus();
}

function selecionarAgencias(){
      var form = document.ContaBancariaPesquisarActionForm;
      if(validateContaBancariaPesquisarActionForm(form)){
	    form.action = 'contaBancariaPesquisarAction.do';
        form.submit();
	  }
}
-->
</script>

</head>

<body leftmargin="5" topmargin="5" onload="resizePageSemLink(490, 310);setarFoco('numeroConta');">

<html:form action="/retornarContaBancariaPesquisarAction"
	name="ContaBancariaPesquisarActionForm"
	type="gcom.gui.arrecadacao.banco.ContaBancariaPesquisarActionForm"
	method="post"
	onsubmit="return ContaBancariaPesquisarActionForm(this)">

	<table width="452" border="0" cellspacing="5" cellpadding="0">
		<tr>



			<td width="100%" valign="top" bgcolor="#003399" class="centercoltext">
			<table height="100%">

				<tr>
					<td></td>
				</tr>
			</table>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">

				<tr>
					<td width="11"><img src="imagens/parahead_left.gif" border="0" /></td>
					<td class="parabg">Pesquisar Conta Bancária</td>

					<td width="11"><img src="imagens/parahead_right.gif" border="0" /></td>
				</tr>
			</table>
			<table width="100%" border="0">
				<tr>
					<td>
					<!--header da tabela interna -->


					<table width="100%" border="0">
						<tr>
							<td colspan="4">Preencha os campos para pesquisar uma conta
							banc&aacute;ria:</td>
						</tr>
						<tr>
							<td width="22%"><strong>Banco:<strong><font color="#FF0000"></font></strong></strong></td>
							<td width="78%" colspan="3">
							
							<logic:notPresent name="ContaBancariaPesquisarActionForm" property="idBancoRecebido">
							<html:select property="idBanco" size="1" style="width:300" onchange="selecionarAgencias();">
								<html:option value="-1">&nbsp;</html:option>
								<html:options collection="collectionBanco" labelProperty="descricaoAbreviada" property="id" />
							</html:select>
							</logic:notPresent>
							
							<logic:present name="ContaBancariaPesquisarActionForm" property="idBancoRecebido">
							<html:select property="idBanco" size="1" style="width:300" disabled="true">
								<html:option value="-1">&nbsp;</html:option>
								<html:options collection="collectionBanco" labelProperty="descricaoAbreviada" property="id" />
							</html:select>
							</logic:present>
							
							</td>
						</tr>
						<tr>
							<td height="0"><strong>Ag&ecirc;ncia:</strong></td>
							<td colspan="3"><strong> <logic:notPresent
								name="collectionAgencia">
								<html:select property="idAgencia" size="1" style="width:300">
									<html:option value="-1">&nbsp;</html:option>
								</html:select>
							</logic:notPresent> <logic:present name="collectionAgencia" scope="request">
								<html:select property="idAgencia" size="1" style="width:300">
									<option value="">&nbsp;</option>
									<logic:iterate name="collectionAgencia" id="collectionAgencia">
									<option value="<bean:write name="collectionAgencia" property="id"/>">
										<bean:write name="collectionAgencia" property="codigoAgencia"/> - <bean:write name="collectionAgencia" property="nomeAgencia"/>
									</option>
									</logic:iterate>
								</html:select>
							</logic:present> </strong></td>
						</tr>
						<tr>
						<tr>
							<td height="0"><strong>N&uacute;mero da Conta:</strong></td>
							<td colspan="3"><strong> <html:text property="numeroConta"
								size="20" maxlength="20" /> </strong></td>
						</tr>
						<tr>
							<td>&nbsp;</td>
							<td colspan="3">&nbsp;</td>
						</tr>
					</table>
					<table border="0" width="100%">
						<tr>
							<td colspan="3">
								<INPUT TYPE="button" class="bottonRightCol" value="Limpar" onclick="document.forms[0].reset();limparForm();"/>
		          					&nbsp;&nbsp;
					          	<logic:present name="caminhoRetornoTelaPesquisaContaBancaria">
		          					<INPUT TYPE="button" class="bottonRightCol" value="Voltar" onclick="redirecionarSubmit('${caminhoRetornoTelaPesquisaContaBancaria}.do')"/>
					          	</logic:present>
							</td>
							<%-- <td height="24">
								<html:submit styleClass="bottonRightCol" value="Pesquisar" /></td>--%>
							<td align="right"><input type="button" name="Button" class="bottonRightCol"	tabindex="5" value="Pesquisar" onClick="javascript:validarForm(document.forms[0])" /></td>
						</tr>
					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
</body>
</html:form>
</html:html>
