<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN">

<html:html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
	<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta name="viewport"content="height=device-height, initial-scale=1.0">

	<title>Cosanpa - Loja Virtual</title>

	<link href="<bean:message key="caminho.portal.css"/>portal.css" rel="stylesheet">
</head>

<body>
	<nav class="navbar">
		<div class="container justify-content-md-center">
			<a class="navbar-brand" href="portal.do">
				<img src="<bean:message key="caminho.portal.imagens"/>cosanpa/logo.png">
			</a>
		</div>
	</nav>

	<script language="JavaScript" src="<bean:message key="caminho.portal.js"/>jquery-3.2.1.min.js"></script>
	<script language="JavaScript" src="<bean:message key="caminho.portal.js"/>popper.js"></script>
	<script language="JavaScript" src="<bean:message key="caminho.portal.js"/>bootstrap.min.js"></script>
</body>
</html:html>
