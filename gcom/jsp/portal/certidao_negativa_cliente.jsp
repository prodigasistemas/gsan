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

<body>
	<%@ include file="/jsp/portal/cabecalho.jsp"%>

	<%@ include file="/jsp/portal/acesso-barra.jsp"%>

	<div class="page-wrap">
		<div class="container pagina">
			<div class="container container-breadcrumb">
				<ul class="breadcrumb">
					<li class="breadcrumb-item"><a href="portal.do">Página Inicial</a></li>
					<li class="breadcrumb-item active">Certidão Negativa de Cliente</li>
				</ul>
			</div>

			<div class="pagina-titulo">
				<h2>Certidão Negativa de Cliente</h2>
			</div>

			<div class="pagina-conteudo">
				<p>Informe o <b>Código do Cliente</b>:</p>
				
				<div class="container">
					<html:form action="/gerarCertidaoNegativaClientePortalAction.do"
						name="GerarCertidaoNegativaClienteActionForm"
						type="gcom.gui.atendimentopublico.GerarCertidaoNegativaClienteActionForm" 
						method="post">
			
						<div class="row">
							<input name="idCliente" id="idCliente" type="number" maxlength="7" class="form-control col-sm-3" placeholder="Informe o código do cliente">
							<span class="form-alert form-alert-right col-sm-3"><html:errors property="idCliente" /></span>
						</div>
						<br>
						<div class="row">
							<input type="submit" value="Gerar" class="btn btn-primary">
						</div>
					</html:form>
				</div>
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
