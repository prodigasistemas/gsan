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
	<meta name="viewport" content="height=device-height, initial-scale=1.0">
	
	<title>Cosanpa - Loja Virtual</title>
	
	<link href="<bean:message key="caminho.portal.css"/>portal.css" rel="stylesheet">
</head>

<body onload="setarFoco('${requestScope.matricula}');">
	<%@ include file="/jsp/portal/cabecalho.jsp"%>
	
	<div class="page-wrap">
		<div class="container pagina">
			<div class="container container-breadcrumb">
				<ul class="breadcrumb">
					<li class="breadcrumb-item"><a href="portal.do">Página Inicial</a></li>
					<li class="breadcrumb-item active">Acesso</li>
				</ul>
			</div>
	
			<div class="pagina-titulo">
				<h2>Acesso à Loja Virtual</h2>
			</div>
	
			<div class="container container-acesso">
				
				<html:form action="/acessarPortalAction.do"
						   method="post"
						   name="AcessarPortalActionForm"
						   type="gcom.gui.portal.AcessarPortalActionForm">
	
					<div class="form-group row">
						<label for="matricula">Matrícula do Imóvel:</label>
						<input name="matricula" id="matricula" type="number" maxlength="7" class="form-control" placeholder="Informe a matrícula" >
						<span class="form-alert form-alert-bottom"><html:errors property="matricula" /></span>
					</div>
					
					<logic:equal name="validarCpfCnpj" value="true" scope="session">
						<div class="form-group row">
							<label for="cpfCnpj">CPF/CNPJ do Solicitante:</label>
							<input name="cpfCnpj" id="cpfCnpj" type="number" maxlength="14" class="form-control" placeholder="Informe o CPF ou CNPJ">
							<span class="form-alert form-alert-bottom"><html:errors property="cpfCnpj" /></span>
						</div>
					</logic:equal>
	
					<div class="form-group row">
						<button type="submit" class="btn btn-primary">Acessar</button>
					</div>
				</html:form>
			</div>
		</div>
	</div>

	<%@ include file="/jsp/portal/rodape.jsp"%>

	<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
	<script language="JavaScript" src="<bean:message key="caminho.portal.js"/>jquery-3.2.1.min.js"></script>
	<script language="JavaScript" src="<bean:message key="caminho.portal.js"/>jquery-ui.min.js"></script>
	<script language="JavaScript" src="<bean:message key="caminho.portal.js"/>popper.js"></script>
	<script language="JavaScript" src="<bean:message key="caminho.portal.js"/>bootstrap.min.js"></script>
</body>
</html:html>