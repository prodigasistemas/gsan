<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"><head>

<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>

<html:html>



EstilosCompesa.css" type="text/css">

</head>

<body leftmargin="5" topmargin="5">
<table width="100%" border="0" cellspacing="5" cellpadding="0">
  <tr>
    <td width="806" valign="top" class="centercoltext">
      <table height="100%">
        <tr>
          <td></td>
        </tr>
      </table>
      <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td width="14"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_left.gif"/></td>
          <td width="703" class="parabg">Erro</td>
          <td width="17" class="parabg"><html:link href="javascript:window.close();"><img border="0" src="<bean:message key="caminho.imagens"/>close.gif" alt="Fechar"/></html:link></td>
          <td width="14"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif"/></td>
        </tr>
      </table>
      <table width="100%" border="0">
        <tr>
          <td colspan="2">
            <img src="<bean:message key="caminho.imagens"/>erro.gif"/>
            <logic:messagesPresent>
              <html:messages id="atencao">
                <font>
                  <strong>
                    <bean:write name="atencao"/><br>
                  </strong>
                </font>
              </html:messages>
            </logic:messagesPresent>
      	  </td>
        </tr>
		<tr>
          <td colspan="2"><a href="javascript:history.back()"><strong>Voltar</strong></a></td>
        </tr>
        <tr>
          <td colspan="2">&nbsp;</td>
        </tr>
      </table>
      <p>&nbsp;</p>
      <p>&nbsp; </p>
    </td>
  </tr>
</table>

</body>

</html:html>
