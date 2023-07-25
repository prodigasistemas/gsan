<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<!DOCTYPE html>

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
		<div class="container pagina pagina-sucesso">
			<i class="fa fa-check-circle fa-10x"></i>
			<h3>Solicitação de cadastro confirmada</h3> 
			<p>
				Obrigado por realizar o cadastro no portal de atendimento da COSANPA. 
				Iremos analisar sua solicitação e em breve você receberá um email com as 
				instruções de acesso à plataforma.
			</p>
		</div>
	</div>

	<%@ include file="/jsp/portal/rodape.jsp"%>

</body>
</html:html>