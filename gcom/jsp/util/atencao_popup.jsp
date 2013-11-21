<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>

<head>

<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>

EstilosCompesa.css" type="text/css">

</head>

<body leftmargin="5" topmargin="5">
<table width="100%" border="0" cellspacing="5" cellpadding="0">
  <tr>
    <td width="834" valign="top" class="centercoltext">
      <table height="100%">
        <tr>
          <td></td>
        </tr>
      </table>
      <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td width="14"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_left.gif"/></td>
          <td width="724" class="parabg">Alerta</td>
          <td width="17" class="parabg"><html:link href="javascript:window.close();"><img border="0" src="<bean:message key="caminho.imagens"/>close.gif" alt="Fechar"/></html:link></td>
          <td width="14"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif"/></td>
        </tr>
      </table>
      <table width="100%" border="0">
       <tr>
         <td width="26%" align="center">
           <div align="right">
             <img src="<bean:message key="caminho.imagens"/>alerta.gif"/>
           </div>
         </td>
         <td width="74%" align="center">
           <div align="left">
             <logic:messagesPresent>
               <html:messages id="atencao">
                 <font>
                   <strong>
                     <bean:write name="atencao"/><br>
                   </strong>
                 </font>
               </html:messages>
             </logic:messagesPresent>
           </div>
         </td>
       </tr>
	   <tr>
	     <td colspan="2">&nbsp;</td>
	   </tr>
       <tr>
         <td colspan="2"><a href="javascript:history.back()"><strong>Voltar</strong></a></td>
       </tr>
     </table>
     <p>&nbsp;</p>
     <p>&nbsp; </p></td>
  </tr>

</table>

</body>

</html:html>
