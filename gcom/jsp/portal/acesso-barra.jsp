<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN">

<html:html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
	<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta name="viewport" content="height=device-height, initial-scale=1.0">
</head>

<body>
	<logic:present name="matricula" scope="session">
		<div class="container barra-acesso">
			<div class="row">
				<div class="col-sm-4 nome">
					<p>Bem vindo(a) <b><bean:write name="nomeUsuario" scope="session" /></b></p>
				</div>
				<div class="col-sm-4 matricula">
					<p>Matrícula: <b><bean:write name="matricula" scope="session" /></b></p>
				</div>
				<div class="col-sm-4 sair">
					<a href="portal.do?sair=true"><i class="fa fa-sign-out"></i> Sair</a>
				</div>
			</div>
		</div>
	</logic:present>
</body>
</html:html>