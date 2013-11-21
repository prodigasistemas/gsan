<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>

<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false"  formName="EfetuarAlteracaoSenhaPorMatriculaActionForm" />
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>

<script language="JavaScript">
	function validarForm(form) {
	  if(validateEfetuarAlteracaoSenhaPorMatriculaActionForm(form)){
	  	form.submit();
	  }
	}
	
	
	
	function limparLoginTecla(){
		document.forms[0].nomeUsuario.value = "";
		document.forms[0].dataNascimento.value = "";
	}
</script>

</head>

<body leftmargin="5" topmargin="5"	onLoad="document.EfetuarAlteracaoSenhaPorMatriculaActionForm.login.focus();"">

<html:form action="/efetuarAlteracaoSenhaPorMatriculaAction.do" 
           method="post" 
           name="EfetuarAlteracaoSenhaPorMatriculaActionForm" 
           type="gcom.gui.seguranca.acesso.EfetuarAlteracaoSenhaPorMatriculaActionForm">
	
	
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
			<td class="parabg">Alterar Senha por Login</td>
			<td width="11" valign="top"><img border="0"	src="imagens/parahead_right.gif" /></td>
		  </tr>
		</table>
		
		<!--Fim Tabela Reference a Páginação da Tela de Processo-->
		
		<p>&nbsp;</p>
		<table width="100%" border="0">
		  <tr>
			<td colspan="2">Para alterar a senha atual para a senha padrão, informe os dados abaixo:</td>
		  </tr>
		  
		  <tr> 
            <td><strong>Login:<font color="#FF0000">*</font></strong></td>
            <td> 
            <html:text maxlength="11"
						property="login" size="11" tabindex="1" style="text-transform: none;" 
						onkeypress="javascript:return pesquisaEnterSemUpperCase(event, 'exibirEfetuarAlteracaoSenhaPorMatriculaAction.do', 'login');" 
						onkeyup="javascript:limparLoginTecla()"
						/>
              <logic:present name="corLabel">
              <html:text property="nomeUsuario" size="50" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000"/>
              </logic:present>
              <logic:notPresent name="corLabel">
              <html:text property="nomeUsuario" size="50" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>
              </logic:notPresent>
            </td>
          </tr>
          <tr> 
            <td><strong>Data de Nascimento:</strong></td>
            <td>
			  <html:text property="dataNascimento" size="10" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>
 		    </td>
          </tr>
          <tr> 
            <td>&nbsp;</td>
            <td colspan="2"><font color="#FF0000">*</font>Campo Obrigat&oacute;rio</td>
          </tr>	
		  <tr> 
            <td colspan="4"> 
              <div align="right"> 
                <input name="Button" type="button" class="bottonRightCol" value="Alterar" onclick="validarForm(document.forms[0]);">
              </div>
            </td>
          </tr>
	  </table>
	  
	  <p>&nbsp;</p>
	  </td>
	</tr>
  </table>

  <%@ include file="/jsp/util/rodape.jsp"%>
  
</html:form>
</body>
</html:html>

