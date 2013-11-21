<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>

<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<SCRIPT LANGUAGE="JavaScript">
<!--

function concluir() {
	<logic:notEqual name="reexibir" value="false">
	if (window.opener && !window.opener.closed)	
    	window.opener.reexibir();
	</logic:notEqual>
	window.close();
}

//-->
</SCRIPT>

</head>

<body leftmargin="0" topmargin="0" onload="javascript:concluir();">
<form name="form" >

</form>
</body>
</html>

