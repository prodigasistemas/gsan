<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<%@ page import="gcom.util.ConstantesSistema" %>


<html:html>
<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js" ></script>

<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>

<html:javascript staticJavascript="false"  formName="AtualizarRegistroAtendimentoObservacaoArquivoAnexoActionForm" dynamicJavascript="true"/>

<SCRIPT LANGUAGE="JavaScript">
<!--



function validarForm(form){

	if (validateAtualizarRegistroAtendimentoObservacaoArquivoAnexoActionForm(form)){
		
		form.action = "exibirAtualizarRegistroAtendimentoObservacaoArquivoAnexoAction.do?acaoAtualizar=OK";
		submeterFormPadrao(form);
	}
}

//-->
</SCRIPT>

</head>

<logic:present name="reloadPage">
	<body leftmargin="0" topmargin="0" onload="chamarSubmitComUrl('${sessionScope.telaRetorno}'); window.close();">
</logic:present>

<logic:notPresent name="reloadPage">
	<body leftmargin="0" topmargin="0" onload="resizePageSemLink(620, 350); setarFoco('${requestScope.nomeCampo}');
	limitTextArea(document.forms[0].observacaoAnexoAtualizacao, 200, document.getElementById('utilizado'), document.getElementById('limite'));">
</logic:notPresent>


<html:form action="/exibirAtualizarRegistroAtendimentoObservacaoArquivoAnexoAction" method="post">

<table width="600" border="0" cellpadding="0" cellspacing="5">
  <tr>
    <td width="590" valign="top" class="centercoltext"> 
      <table height="100%">
        <tr>
          <td></td>
        </tr>
      </table>
      <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td width="11"><img src="<bean:message key="caminho.imagens"/>parahead_left.gif" editor="Webstyle4" moduleid="Album Photos (Project)\toptab_page2_parahead_left.xws" border="0" /></td>
          <td class="parabg">Atualizar Observação</td>
          <td width="11"><img src="<bean:message key="caminho.imagens"/>parahead_right.gif" editor="Webstyle4" moduleid="Album Photos (Project)\toptab_page2_parahead_right.xws" border="0" /></td>
        </tr>
      </table> 
      <p>&nbsp;</p>

      <table width="100%" border="0">
        <tr>
          <td colspan="4">Informe os dados abaixo para atualizar a observação:</td>
          <td align="right"></td>
        </tr>
        </table>
      <table width="100%" border="0">
		<tr>
			<td colspan="2" height="5"></td>
		</tr>
		<tr>
			<td HEIGHT="30"><strong>Observação:</strong></td>
			<td>
				
				<html:textarea property="observacaoAnexoAtualizacao" rows="3" cols="50" 
				onkeyup="limitTextArea(document.forms[0].observacaoAnexoAtualizacao, 200, document.getElementById('utilizado'), document.getElementById('limite'));"/><br>
				<strong><span id="utilizado">0</span>/<span id="limite">200</span></strong>
				
			</td>
		</tr>
		
        <tr>
          <td colspan="4" height="5"></td>
        </tr>
        <tr>
          <td height="30" colspan="4"> 
          	<div align="right">
              
              <input type="button" tabindex="2" onclick="validarForm(document.forms[0]);" class="bottonRightCol" value="Atualizar">&nbsp;
              
              <input type="button" tabindex="12" onclick="window.close();" class="bottonRightCol" value="Fechar" style="width: 70px;">
			</div>
		  </td>
        </tr>
	  </table>
      
      <p>&nbsp;</p>
      </td>
  </tr>
</table>

</html:form>

<script language="JavaScript">
	
		<!--
		
		//-->

	</script>

</body>
</html:html>
