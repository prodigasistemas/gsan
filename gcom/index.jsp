<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<html:html>
<head>
<%
	session.setAttribute("nomeSistema","GSAN - Sistema Integrado de Gest&atilde;o de Servi&ccedil;os de Saneamento");
%>

</head>

<logic:present scope="application" name="tituloPagina">
	<logic:forward name="telaLogin"/>
	
	<body>
	</body>
	
</logic:present>

<logic:notPresent scope="application" name="tituloPagina">
	
	<body onload="window.location.href='/gsan/carregarParametrosAction.do'">
	</body>

</logic:notPresent>

</html:html>