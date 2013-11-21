<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<%@ page import="gcom.util.email.ServicosEmail" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>

<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
</head>

<body leftmargin="5" topmargin="5" background="#90c7fc">

<table width="770" border="0" cellspacing="5" cellpadding="0">
  <tr>
	<td>
	  <font size="1">
	  <%
		Exception exception = (Exception) session.getAttribute("excecaoPaginaErro");
                if (exception != null) {
                	out.print(ServicosEmail.processarExceptionParaEnvio(exception));
		}
	  %>
	  </font>
	</td>
  </tr>
</table>

</body>

</html:html>
