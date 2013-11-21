
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>

<head>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<SCRIPT LANGUAGE="JavaScript">
<!--

function MM_reloadPage(init) {  //reloads the window if Nav4 resized
  if (init==true) with (navigator) {if ((appName=="Netscape")&&(parseInt(appVersion)==4)) {
    document.MM_pgW=innerWidth; document.MM_pgH=innerHeight; onresize=MM_reloadPage; }}
  else if (innerWidth!=document.MM_pgW || innerHeight!=document.MM_pgH) location.reload();
}

function fechar(){
	chamarReloadSemParametro();
	window.close();
}
function excluir(){
	var form = document.forms[0];
	if (confirm('Confirma alteração?')) {
		form.submit();
	}
}

MM_reloadPage(true);
//-->
</script>
</head>
<logic:present name="fecharPopup" scope="request">
	<body leftmargin="0" topmargin="0" onload="fechar();">
</logic:present>
<logic:notPresent name="fecharPopup" scope="request">
	<body leftmargin="0" topmargin="0">
</logic:notPresent>

<html:form action="/inserirMotivoRevisaoTarifaSocialAction"
	name="InserirMotivoRevisaoTarifaSocialActionForm"
	type="gcom.gui.cadastro.tarifasocial.InserirMotivoRevisaoTarifaSocialActionForm"
	method="post">
<table width="380" border="0" cellpadding="0" cellspacing="5">
  <tr> 
    <td width="375" valign="top" class="centercoltext"> <table height="100%">
        <tr> 
          <td></td>
        </tr>
      </table>
      <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr> 
          <td width="11"><img src="imagens/parahead_left.gif" editor="Webstyle4" moduleid="Album Photos (Project)\toptab_page2_parahead_left.xws" border="0" /></td>
          <td class="parabg">Colocar Motivo Revisão Tarifa Social</td>
          <td width="11"><img src="imagens/parahead_right.gif" editor="Webstyle4" moduleid="Album Photos (Project)\toptab_page2_parahead_right.xws" border="0" /></td>
        </tr>
      </table>
      <table width="100%" border="0">
        <tr> 
          <td height="115"> 
            <table width="100%" border="0" dwcopytype="CopyTableRow">
              <tr>
                <td colspan="3">Para colocar um motivo de revisão no imóvel da tarira social, 
                  informe os dados abaixo:</td>
              </tr>
              <tr>
                <td height="17" colspan="3">&nbsp;</td>
              </tr>
              <tr> 
                <td width="28%" height="25"><strong>Motivo Revisão:</strong></td>
                <td width="32%">
                	<html:select property="idMotivoRevisao">
							<html:option value="-1">&nbsp;</html:option>
							<html:options collection="colecaoTarifaSocialRevisaoMotivo"
								labelProperty="descricao" property="id" />
						</html:select>
                  </td>
              </tr>
              <tr>
                <td height="17" colspan="3">&nbsp;</td>
              </tr>
              <tr>
                <td height="17" colspan="3"><div align="right"><font color="#000000">&nbsp; 
                    <input name="Button2" type="button" class="bottonRightCol" value="Fechar" onClick="javascript:window.close();">
                    <input name="Button" type="button" class="bottonRightCol" value="Confirmar" onClick="javascript:excluir();">
                    </font></div></td>
              </tr>
            </table>
            </td>
        </tr>
      </table>
      </td>
  </tr>
</table>
</html:form>
</body>
</html>
