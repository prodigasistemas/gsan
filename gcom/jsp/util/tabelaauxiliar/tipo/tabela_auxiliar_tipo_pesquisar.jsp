<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>

<title>Compesa - SGQA</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"  href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">

</head>

<html:form
  action="/pesquisarDistritoAction"
  method="post"
>

<body leftmargin="5" topmargin="5">
<table width="635" border="0" cellspacing="5" cellpadding="0">
  <tr>
    <td width="625" valign="top" class="centercoltext"> <table height="100%">
        <tr>
          <td></td>
        </tr>
      </table>
      <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_left.gif"/></td>
          <td class="parabg">Pesquisa de Distrito</td>
          <td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif"/></td>
        </tr>
      </table>
      <p>&nbsp;</p>
      <table width="100%" border="0">
        <tr>
          <td colspan="2">Preencha os campos para pesquisar um distrito:</td>
        </tr>
        <tr>
          <td ><strong>C&oacute;digo:</strong></td>
          <td>
            <html:text property="id"/>
	    <br><font color="red"><html:errors property="id"/></font>
	  </td>
        </tr>
        <tr>
          <td><strong>Descri&ccedil;&atilde;o:</strong></td>
           <td>
            <html:text property="descricao" maxlength="60" size="60"/>
	    <br><font color="red"><html:errors property="descricao"/></font>
	  </td>
        </tr>
        <tr>
          <td height="24"><strong>Descri&ccedil;&atilde;o do Sistema:</strong></td>

          <td> <html:select property="sistema">
              <html:option value="-1" >&nbsp;</html:option>
              <html:options collection="sistemas" property="id" labelProperty="descricao"/>
              </html:select>
         </td>
        </tr>
        <tr>
          <td height="24">&nbsp;</td>
          <td>&nbsp;</td>
        </tr>
        <tr>
          <td><html:submit styleClass="bottonRightCol" value="Pesquisar"/></td>
          <td>&nbsp;</td>
        </tr>
      </table>
      <p>&nbsp;</p></td>
  </tr>
</table>
</body>

</html:form>
</html:html>
