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

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="LembrarSenhaActionForm" />

<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js" ></script>

<script language="JavaScript">
	function validarForm(form) {
	  var LEMBRETE_SENHA = document.getElementById("LEMBRETE_SENHA").value;
	  
	  if(validateLembrarSenhaActionForm(form) && confirm('Lembrete de senha: ' + LEMBRETE_SENHA +'\n' +'Confirmar envio de e-mail com nova senha?')){
	  	form.submit();
	  	//o setTimeout funciona como um delay ('função javascript',milisegundos)
	  	//setTimeout('self.close()',1250);
	  }
	}
</script>
</head>

<body leftmargin="5" topmargin="5" onLoad="document.LembrarSenhaActionForm.login.focus();">

<html:form action="/lembrarSenhaAction"
	name="LembrarSenhaActionForm"
	type="gcom.gui.seguranca.acesso.LembrarSenhaActionForm"
	method="post">

<input type="hidden" id="LEMBRETE_SENHA" value="<%=request.getAttribute("lembreteSenha")%>"/>	
	
	<table width="530" border="0" cellspacing="5" cellpadding="0">
	  <tr>
		<td width="452" valign="top" class="centercoltext">
		  <table height="100%">
			<tr>
			  <td></td>
			</tr>
		  </table>
		  <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
			<tr>
			  <td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_left.gif" /></td>
			  <td class="parabg">Lembrar Senha</td>
			  <td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
			</tr>
		  </table>
		  <p>&nbsp;</p>
		  <table width="100%" border="0">
			<tr>
			  <td colspan="4">Preencha os campos abaixo para lembrar a senha:</td>
			</tr>
        	
        	<tr> 
              <td width="30%"><strong>Login:<font color="#FF0000">*</font></strong></td>
              <td colspan="3"> 
                <input type="text" name="login" value="<%=request.getAttribute("login")%>" size="10" maxlength="10" style="text-transform: none;"/>
              </td>
            </tr>
            
            <tr> 
              <td><strong>Data de Nascimento:<font color="#FF0000">*</font></strong></td>
              <td colspan="3">
			    <html:text name="LembrarSenhaActionForm" property="dataNascimento" maxlength="10" size="10" onkeypress="javascript:mascaraData(this,event);"/>
			    <img border="0"  src="<bean:message key='caminho.imagens'/>calendario.gif" onclick="javascript:abrirCalendario('LembrarSenhaActionForm', 'dataNascimento')"	width="20" border="0" align="middle" alt="Exibir Calendário" />
 		      </td>
            </tr>
            
            <tr> 
              <td><strong>CPF:<font color="#FF0000">*</font></strong></td>
              <td colspan="3"> 
                <html:text name="LembrarSenhaActionForm" property="cpf" size="11" maxlength="11"/>
              </td>
            </tr>
            <tr> 
              <td>&nbsp;</td>
              <td colspan="3"><font color="#FF0000">*</font>Campo Obrigat&oacute;rio</td>
            </tr>
            <tr> 
              <td colspan="4"> 
                <div align="right"> 
                  <input name="Button" type="button" class="bottonRightCol" value="Lembrar" onClick="validarForm(document.forms[0]);">
                  <input name="Button2" type="button" class="bottonRightCol" value="Fechar" onClick="javascript:window.close();">
                </div>
              </td>
            </tr>
		  </table>
		  <p>&nbsp;</p>
		  <p>&nbsp;</p>
		</td>
	  </tr>
	</table>
</html:form>
</html:html>
