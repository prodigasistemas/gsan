<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<%@ page import="gcom.util.Pagina, gcom.util.ConstantesSistema" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>

<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false"  formName="EfetuarAlteracaoSenhaSimplificadaActionForm" />
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js" ></script>



<script language="JavaScript">
function validarForm(form,indicadorSenhaForte) {
	  var novaSenha = form.novaSenha.value;

	  if(indicadorSenhaForte==1){
		  if(novaSenha.length > 5 && novaSenha.length < 9){
			  if(validateEfetuarAlteracaoSenhaSimplificadaActionForm(form)){
			  	form.submit();
			  }
		  } else{
		     alert('Senha tem que possuir de 6 a 8 caracteres.');
		     form.novaSenha.focus();
		  }   
	  }else{
		  if(novaSenha.length > 3 && novaSenha.length < 7){
			  	if(validateEfetuarAlteracaoSenhaSimplificadaActionForm(form)){
			  		form.submit();
			  	}
		  }else{
		     	alert('Senha tem que possuir de 4 a 6 caracteres.');
		     	form.novaSenha.focus();
		  }
	  }
}
	
</script>

</head>

<body leftmargin="5" topmargin="5"	onLoad="document.EfetuarAlteracaoSenhaSimplificadaActionForm.senha.focus();"">

<html:form action="/efetuarAlteracaoSenhaSimplificadaAction.do" 
           method="post" 
           name="EfetuarAlteracaoSenhaSimplificadaActionForm" 
           type="gcom.gui.seguranca.acesso.EfetuarAlteracaoSenhaSimplificadaActionForm">
	
	
  <%@ include file="/jsp/util/cabecalho.jsp"%>
  <%@ include file="/jsp/util/menu.jsp"%>

  <table width="770" border="0" cellspacing="5" cellpadding="0">
	<tr>
	  <td width="140" valign="top" class="leftcoltext">
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
		<!--Início Tabela Reference a Páginação da Tela de Processo-->
		<table>
		  <tr>
			<td></td>
		  </tr>
		</table>
		
		<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
		  <tr>
			<td width="11"><img border="0" src="imagens/parahead_left.gif" /></td>
			<td class="parabg">Alterar Senha</td>
			<td width="11" valign="top"><img border="0"	src="imagens/parahead_right.gif" /></td>
		  </tr>
		</table>
		
		<!--Fim Tabela Reference a Páginação da Tela de Processo-->
		
		<p>&nbsp;</p>
		<table width="100%" border="0">
		  <tr>
			<td colspan="3">Para alterar a senha, informe os dados abaixo:</td>
		  </tr>
		   <%	if (getServletContext().getAttribute("indicadorSenhaForte").equals(ConstantesSistema.SIM.toString())){%>
		  <tr> 
            <td><strong>Senha:<font color="#FF0000">*</font></strong></td>
            <td colspan="3"> 
              <html:password name="EfetuarAlteracaoSenhaSimplificadaActionForm" property="senha" size="10" maxlength="8" style="text-transform: none;" />
            </td>
          </tr>
		  
		  <tr> 
            <td><strong>Lembrete Senha:<font color="#FF0000">*</font></strong></td>
            <td colspan="3" width="65%"> 
              <input type="text" name="lembreteSenha" value="<%=request.getAttribute("lembreteSenha")%>" size="30" maxlength="30" style="text-transform: none;"/>
            </td>
          </tr>

		  <tr> 
            <td><strong>Nova Senha:<font color="#FF0000">*</font></strong></td>
            <td colspan="3"> 
              <html:password name="EfetuarAlteracaoSenhaSimplificadaActionForm" property="novaSenha" size="10" maxlength="8" style="text-transform: none;" />
              <img border="0" width="25" height="25"
					src="<bean:message key="caminho.imagens"/>informacao.gif"
					onmouseover="this.T_BGCOLOR='whitesmoke';this.T_RIGHT=true;return escape('<%=request.getAttribute("mensagem")%>'); "/>	
            </td>         

          <tr> 
            <td><strong>Confirmação da Nova Senha:<font color="#FF0000">*</font></strong></td>
            <td colspan="3"> 
              <html:password name="EfetuarAlteracaoSenhaSimplificadaActionForm" property="confirmacaoNovaSenha" size="10" maxlength="8" style="text-transform: none;" />
            </td>
          </tr>
          <tr>
          	<td>
          		&nbsp;
          	</td>
          </tr>
          <tr> 
           	<td colspan="5">
         		
	       	</td>
           </tr>
          <%}else{ %>
          <tr> 
            <td><strong>Senha:<font color="#FF0000">*</font></strong></td>
            <td colspan="3"> 
              <html:password name="EfetuarAlteracaoSenhaSimplificadaActionForm" property="senha" size="6" maxlength="6" style="text-transform: none;" />
            </td>
          </tr>
		  
		  <tr> 
            <td><strong>Lembrete Senha:<font color="#FF0000">*</font></strong></td>
            <td colspan="3"> 
              <input type="text" name="lembreteSenha" value="<%=request.getAttribute("lembreteSenha")%>" size="30" maxlength="30" style="text-transform: none;"/>
            </td>
          </tr>

		  <tr> 
            <td><strong>Nova Senha:<font color="#FF0000">*</font></strong></td>
            <td colspan="3"> 
              <html:password name="EfetuarAlteracaoSenhaSimplificadaActionForm" property="novaSenha" size="6" maxlength="6" style="text-transform: none;" />
            </td>
          </tr>
          
          <tr> 
            <td><strong>Confirmação da Nova Senha:<font color="#FF0000">*</font></strong></td>
            <td colspan="3"> 
              <html:password name="EfetuarAlteracaoSenhaSimplificadaActionForm" property="confirmacaoNovaSenha" size="6" maxlength="6" style="text-transform: none;" />
            </td>
          </tr>
        
          <%}%>

          <tr> 
            <td>&nbsp;</td>
            <td colspan="3" ><font color="#FF0000">*</font>Campo Obrigat&oacute;rio</td>
          </tr>	
          <tr>
          	<td align="left">
          	 <input type="button"
						name="ButtonCancelar" class="bottonRightCol" value="Cancelar"
						onClick="javascript:window.location.href='/gsan/telaPrincipal.do'">  
			</td>
			<td colspan="3" align="right">				
                <input name="Button" type="button" class="bottonRightCol" value="Alterar" onclick="validarForm(document.forms[0],<%=getServletContext().getAttribute("indicadorSenhaForte")%>);">
            </td>
          </tr>
	  </table>
	  
	  <p>&nbsp;</p>
 	  <p>&nbsp;</p>
	  <p>&nbsp;</p>
 	  <p>&nbsp;</p>
	  <p>&nbsp;</p>
 	  <p>&nbsp;</p>
 	  <p>&nbsp;</p>
	  <p>&nbsp;</p>
 	  <p>&nbsp;</p>
	  <p>&nbsp;</p>
 	  <p>&nbsp;</p>
	  <p>&nbsp;</p>
 	  <p>&nbsp;</p>
	  <p>&nbsp;</p>
 	  <p>&nbsp;</p>
 	  <p>&nbsp;</p>
	  <p>&nbsp;</p>				
	  </td>
	</tr>
  </table>
  	
  <%@ include file="/jsp/util/rodape.jsp"%>
  <%@ include file="/jsp/util/tooltip.jsp" %>	
  
</html:form>
</body>
</html:html>

