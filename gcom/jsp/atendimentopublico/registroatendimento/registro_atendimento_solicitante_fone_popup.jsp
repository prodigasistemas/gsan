<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<%@ page import="gcom.cadastro.cliente.FoneTipo"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
<head>
<title>GSAN - Sistema Integrado de Gest&atilde;o de Servi&ccedil;os de Saneamento</title>

<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="AdicionarSolicitanteRegistroAtendimentoActionForm"	dynamicJavascript="false" />

<script language="JavaScript">
var bCancel = false; 

    function validateAdicionarSolicitanteRegistroAtendimentoActionForm(form) {                                                                   
        if (bCancel) 
      return true; 
        else 
       return validateCaracterEspecial(form) && validateInteger(form) && validateRequired(form); 
   	} 

    function caracteresespeciais () { 
     this.aa = new Array("dddTelefone", "DDD possui caracteres especiais.", new Function ("varName", " return this[varName];"));
     this.ab = new Array("numeroTelefone", "Número do Telefone possui caracteres especiais.", new Function ("varName", " return this[varName];"));
     this.ac = new Array("ramal", "Ramal possui caracteres especiais.", new Function ("varName", " return this[varName];"));
    } 

    function IntegerValidations () { 
     this.aa = new Array("numeroTelefone", "Número do Telefone deve ser numérico(a).", new Function ("varName", " return this[varName];"));
     this.ab = new Array("dddTelefone", "DDD deve ser numérico(a).", new Function ("varName", " return this[varName];"));
    }
    function required () { 
     this.aa = new Array("idTipoTelefone", "Informe Tipo de Telefone.", new Function ("varName", "this.datePattern='dd/MM/yyyy';  return this[varName];"));
     this.ab = new Array("dddTelefone", "Informe DDD.", new Function ("varName", " return this[varName];"));
     this.ac = new Array("numeroTelefone", "Informe Número do Telefone.", new Function ("varName", " return this[varName];"));
     
    } 
    
   function validarForm(form){
	if(validateAdicionarSolicitanteRegistroAtendimentoActionForm(form)){
		form.submit();
	}
}

function limparForm(){
	var form = document.AdicionarSolicitanteRegistroAtendimentoActionForm;
	    
	form.idTipoTelefone.value = "";
	form.dddTelefone.value = "";
	form.numeroTelefone.value = "";
	form.ramal.value = "";
}
	
	</script>
</head>

<logic:present name="telaRetorno" scope="request">
<body leftmargin="5" topmargin="5"
	onload="chamarReload('${requestScope.telaRetorno}'); window.close();">
</logic:present>

<logic:notPresent name="telaRetorno" scope="request">

	<logic:present name="telaRetornoPopUp" scope="request">
	<body leftmargin="5" topmargin="5"
		onload="redirecionarSubmit('${requestScope.telaRetornoPopUp}');">
	</logic:present>
	
	<logic:notPresent name="telaRetornoPopUp" scope="request">
		<body leftmargin="5" topmargin="5"
			onload="javascript:resizePageSemLink(600, 330);javascript:setarFoco('${requestScope.nomeCampo}');">
	</logic:notPresent>

	
</logic:notPresent>



<html:form action="/adicionarSolicitanteFoneAction" method="post">
	<table width="560" border="0" cellspacing="5" cellpadding="0">
		<tr>
			<td width="550" valign="top" class="centercoltext">
			<table height="100%">
				<tr>
					<td></td>
				</tr>
			</table>
			<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
				<tr>
					<td width="11">
						<img border="0" src="<bean:message key="caminho.imagens"/>parahead_left.gif" />
					</td>
					<td class="parabg">Fones do Solicitante</td>
					<td width="11">
						<img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif" />
					</td>
				</tr>
			</table>
			<table width="100%" border="0">
				<tr>
					<td colspan="2" height="28">Para adicionar um fone, informe os dados abaixo:</td>
				</tr>
				<tr>
					<td width="40%" height="28"><strong>Tipo Telefone:<font color="#FF0000">*</font></strong></td>
					<td width="60%">
						<html:select property="idTipoTelefone" tabindex="1">
							<html:option value="">&nbsp;</html:option>
							<logic:present name="colecaoFoneTipo">
					           <logic:iterate id="foneTipo" name="colecaoFoneTipo" type="FoneTipo">
						         <option value="<%=""+foneTipo.getId()%>;<%=""+foneTipo.getDescricao()%>"><%=""+foneTipo.getDescricao()%></option>
					           </logic:iterate>
				            </logic:present>
						</html:select>	
					</td>
				</tr>
				<tr>

					<td height="24">
						<strong>DDD:<font color="#FF0000">*</font></strong>
					</td>
					<td>
						<input type="text" name="dddTelefone" maxlength="2" size="2" tabindex="3">
					</td>
				</tr>
				<tr>

					<td height="24">
						<strong>N&uacute;mero do Telefone:<font color="#FF0000">*</font></strong>
					</td>
					<td>
						<input type="text" name="numeroTelefone" maxlength="9" size="9" tabindex="4">
					</td>
				</tr>

				<tr>
					<td height="24"><strong>Ramal:</strong></td>
					<td>
						<input type="text" name="ramal" maxlength="4" size="4" tabindex="5">
					</td>
				</tr>
				<tr>
					<td></td>

					<td>
						<strong><font color="#FF0000"> * </font></strong> Campos obrigat&oacute;rios
					</td>
				</tr>
				<tr>
					<td height="24">
						<logic:present name="caminhoRetornoTelaAdicionarFonePopUp">
							<input type="button" class="bottonRightCol" value="Voltar" style="width: 70px;" onclick="redirecionarSubmit('exibirAdicionarSolicitanteRegistroAtendimentoAction.do')"/>&nbsp;
						</logic:present>
						<input type="button" class="bottonRightCol" value="Limpar" style="width: 70px;" onclick="javascript:limparForm();"/>&nbsp;	
					</td>
					<td align="right">
						<input type="button" name="Button" class="bottonRightCol" tabindex="10" value="Adicionar"
							onClick="javascript:validarForm(document.forms[0])" />
					</td>
				</tr>
			</table>
			<p>&nbsp;</p>
			</td>
		</tr>
	</table>
</html:form>
</body>
</html:html>
