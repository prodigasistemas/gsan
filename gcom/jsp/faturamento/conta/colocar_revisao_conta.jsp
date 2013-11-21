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

<script language="JavaScript">
<!-- 
function validarForm(form){
	if (form.motivoRevisaoContaID.value == -1){
		alert("Informe Motivo da Revisão.");
	}
	else if (confirm("Confirma revisão?")){
		submeterFormPadrao(form);
	}
}

//-->
</SCRIPT>

</head>

<logic:present name="reloadPage" >
	<body leftmargin="0" topmargin="0" onload="chamarSubmitComUrl('exibirManterContaAction.do'); window.close();">
</logic:present>

<logic:notPresent name="reloadPage">
	<body leftmargin="0" topmargin="0" onload="resizePageSemLink(480, 280);">
</logic:notPresent>


<html:form action="/colocarRevisaoContaAction" method="post">

<html:hidden property="contaSelected"/>

<table width="452" border="0" cellpadding="0" cellspacing="5">
  <tr>
    <td width="442" valign="top" class="centercoltext"> 
      <table height="100%">
        <tr>
          <td></td>
        </tr>
      </table>
      <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td width="11"><img src="<bean:message key="caminho.imagens"/>parahead_left.gif" editor="Webstyle4" moduleid="Album Photos (Project)\toptab_page2_parahead_left.xws" border="0" /></td>
          <td class="parabg">Colocar Conta em Revisão</td>
          <td width="11"><img src="<bean:message key="caminho.imagens"/>parahead_right.gif" editor="Webstyle4" moduleid="Album Photos (Project)\toptab_page2_parahead_right.xws" border="0" /></td>
        </tr>
      </table> 
      <p>&nbsp;</p>

      <table width="100%" border="0">
        <tr>
          <td colspan="4">Para colocar a(s) conta(s) em revisão informe o motivo:</td>
          <logic:present scope="application" name="urlHelp">
				<td align="right"><a href="javascript:abrirPopupHelp('${applicationScope.urlHelp}faturamentoContaRevisaoColocar', 500, 700);"><span style="font-weight: bold"><font color="#3165CE">Ajuda</font></span></a></td>									
		  </logic:present>
		  <logic:notPresent scope="application" name="urlHelp">
				<td align="right"><span style="font-weight: bold"><font color=#696969><u>Ajuda</u></font></span></td>									
		  </logic:notPresent>
        </tr>
      </table>
        
      <table width="100%" border="0">
        <tr>
          <td width="150" height="20"><strong>Motivo da Revisão:<font color="#FF0000">*</font></strong></td>
          <td colspan="3">
          
          	<html:select property="motivoRevisaoContaID">
          		<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
				<logic:present name="colecaoMotivoRevisaoConta">
					<html:options collection="colecaoMotivoRevisaoConta" labelProperty="descricaoMotivoRevisaoConta" property="id"/>
				</logic:present>
			</html:select>
          
          </td>
        </tr>
        <tr>
          <td colspan="4" height="5"></td>
        </tr>
        <tr>
          <td height="30" colspan="4"> 
          	<div align="right">
              <input type="button" onclick="validarForm(document.forms[0]);" class="bottonRightCol" value="Concluir" style="width: 70px;">&nbsp;
              <input type="button" onclick="window.close();" class="bottonRightCol" value="Fechar" style="width: 70px;">
			</div>
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
