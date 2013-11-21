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
	  if(validateLembrarSenhaActionForm(form) && confirm('Confirmar envio de e-mail com nova senha?')){
	  	form.submit();
	  }
	}
	
	function lembrarSenha(form){
	 if(validateLembrarSenhaActionForm(form)){
	  	redirecionarSubmit('exibirLembrarSenhaAction.do?login='+form.login.value+'&cpf='+form.cpf.value+'&dataNascimento='+form.dataNascimento.value);
	  }
	}
	
	function telaLogin(){
   	  redirecionarSubmit('/gsan');
	}
	
	function enviarEmail(form){
	 if(validateLembrarSenhaActionForm(form)){
	  	form.submit();
	  }
	}
</script>
</head>

<body leftmargin="5" topmargin="5" onLoad="document.LembrarSenhaActionForm.login.focus();">

<html:form action="/lembrarSenhaAction"
	name="LembrarSenhaActionForm"
	type="gcom.gui.seguranca.acesso.LembrarSenhaActionForm"
	method="post">

<%@ include file="/jsp/util/cabecalho.jsp"%>
<%--<%@ include file="/jsp/util/menu.jsp"%> --%>

<%--<input type="hidden" id="LEMBRETE_SENHA" value="<%=request.getAttribute("lembreteSenha")%>"/> --%>	
	
<table width="770" border="0" cellspacing="5" cellpadding="0">
  <tr>
	<td width="140" valign="top" class="leftcoltext">
	  <div align="center">
		
		<p align="left">&nbsp;</p>
		<p align="left">&nbsp;</p>
		<p align="left">&nbsp;</p>

		<%--<%@ include file="/jsp/util/informacoes_usuario.jsp"%> --%>

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

	    <%--<%@ include file="/jsp/util/mensagens.jsp"%> --%>

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
	  <!--Início Tabela Reference a Páginação da Tela de Processo-->
	  <table>
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
	
	  <!--Fim Tabela Reference a Páginação da Tela de Processo-->
		
	  <p>&nbsp;</p>
		
	  <table width="100%" border="0">
		<tr>
		  <td>Preencha os campos abaixo para lembrar a senha:</td>
		  <td align="right"><a href="javascript: abrirPopupHelp('/gsan/help/help.jsp?mapIDHelpSet=lembrarSenha', 500, 700);"><span style="font-weight: bold"><font color="#3165CE">Ajuda</font></span></a></td>								
		  
		</tr>
		</table>
	  <table width="100%" border="0">        	
        <logic:notPresent name="login" scope="request">	
	        <tr> 
	          <td width="30%"><strong>Login:<font color="#FF0000">*</font></strong></td>
	          <td colspan="3"> 
	            <html:text name="LembrarSenhaActionForm" property="login" size="11" maxlength="11" style="text-transform: none;"/>
	          </td>
	        </tr>
	            
	        <tr> 
	          <td><strong>Data de Nascimento:<font color="#FF0000">*</font></strong></td>
	          <td colspan="3">
				<html:text name="LembrarSenhaActionForm" property="dataNascimento" maxlength="10" size="10" onkeypress="javascript:mascaraData(this,event);"/>
				<img border="0" src="<bean:message key='caminho.imagens'/>calendario.gif" onclick="javascript:abrirCalendario('LembrarSenhaActionForm', 'dataNascimento');document.LembrarSenhaActionForm.cpf.focus();"	width="20" border="0" align="middle" alt="Exibir Calendário" />
	 		  </td>
	        </tr>
	            
	        <tr> 
	          <td><strong>CPF:<font color="#FF0000">*</font></strong></td>
	          <td colspan="3"> 
	            <html:text name="LembrarSenhaActionForm" property="cpf" size="11" maxlength="11"/>
	          </td>
	        </tr>
        </logic:notPresent>

        <logic:present name="login" scope="request">	
	        <tr> 
	          <td width="30%"><strong>Login:<font color="#FF0000">*</font></strong></td>
	          <td colspan="3"> 
	            <input type="text" name="login" value="<%=request.getAttribute("login")%>" size="11" maxlength="11" style="text-transform: none;"/>
	          </td>
	        </tr>
	            
	        <tr> 
	          <td><strong>Data de Nascimento:<font color="#FF0000">*</font></strong></td>
	          <td colspan="3">
				<input type="text" name="dataNascimento" value="<%=request.getAttribute("dataNascimento")%>" maxlength="10" size="10" onkeypress="javascript:mascaraData(this,event);"/>
				<img border="0" src="<bean:message key='caminho.imagens'/>calendario.gif" onclick="javascript:abrirCalendario('LembrarSenhaActionForm', 'dataNascimento');document.LembrarSenhaActionForm.cpf.focus();"	width="20" border="0" align="middle" alt="Exibir Calendário" />
	 		  </td>
	        </tr>
	            
	        <tr> 
	          <td><strong>CPF:<font color="#FF0000">*</font></strong></td>
	          <td colspan="3"> 
	            <input type="text" name="cpf" value="<%=request.getAttribute("cpf")%>" size="11" maxlength="11"/>
	          </td>
	        </tr>
        </logic:present>

        
        <tr> 
          <td>&nbsp;</td>
          <td colspan="3"><font color="#FF0000">*</font>Campo Obrigat&oacute;rio</td>
        </tr>
        
        <tr> 
          <td colspan="4"> 
            <div align="right"> 
              <input name="Button" type="button" class="bottonRightCol" value="Lembrar" onClick="javascript:lembrarSenha(document.forms[0]);">
              <input name="Button2" type="button" class="bottonRightCol" value="Login" onClick="javascript:telaLogin();">
            </div>
          </td>
        </tr>
        
        <tr> 
          <td>&nbsp;</td>
          <td>&nbsp;</td>
        </tr>
        
	  </table>
	  
	  <logic:present name="lembreteSenha" scope="request">
	    <table>
	        <tr> 
	          <td><strong>Lembrete da Senha:</strong></td>
	        </tr>
	 		<tr>
	 		  <td> 
	            <input type="text" name="lembreteSenha" value="<%=request.getAttribute("lembreteSenha")%>" size="30" maxlength="30" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>
	          </td>
	 		</tr>       
	 		
	 		<tr>
		       <td>
		         <a href="javascript:enviarEmail(document.forms[0]);">Enviar senha para e-mail.</a><br>
		         <font size="1">(lembre que o sistema vai gerar uma nova senha)</font>
		       </td>
		    </tr>
		</table>    
      </logic:present>
	
	  <p>&nbsp;</p>
	</td>  
  </tr>
</table>

<%@ include file="/jsp/util/rodape.jsp"%>
  
</html:form>
</body>
</html:html>

