<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>

<html>
  <head>

	<%@ include file="/jsp/util/titulo.jsp"%>

	<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js" ></script>

	<SCRIPT TYPE="text/javascript" LANGUAGE="JavaScript"><!--
		document.write(Calendar(opener.month,opener.year));
	//--></SCRIPT>

  </head>
  <BODY BGCOLOR="#90c7fc" TOPMARGIN=2 LEFTMARGIN=5>
  </body>

</html>
