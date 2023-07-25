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

<body onload="setarFoco('${requestScope.portalMatricula}');">
	<%@ include file="/jsp/portal/cabecalho.jsp"%>
	
	<div class="page-wrap">
		<div class="container pagina">	
			<div class="container container-breadcrumb">
			<div class="row">
				<ul class="col-sm-10 breadcrumb">
				    <li class="breadcrumb-item"><a href="portal.do">Página Inicial</a></li>
					<li class="breadcrumb-item active">Acesso</li>
				</ul>
				    <div class="col-sm-2 cadastro">
				    <a href="cadastro-login-cliente.do">Faça seu cadastro <i class="fa fa-sign-in"></i></a>
				    </div>
				   </div>
			</div>
			<div class="pagina-titulo">
				<h2>Acesso à Loja Virtual</h2>
			</div>
	
			<div class="container container-acesso">
				
				<html:form action="/acessar-portal.do?acao=login"
						   method="post"
						   name="AcessarPortalActionForm"
						   type="gcom.gui.portal.AcessarPortalActionForm">
						   
					<logic:present name="portal-err-login" scope="request">
						<div class="alert alert-danger" role="alert">
							<html:errors property="portal-err-login" />
						</div>
					</logic:present>
	
					<div class="form-group row">
						<label for="CpfCnpj">CPF ou CNPJ</label>
						<html:text property="cpfOuCnpj" styleClass="form-control cpfOuCnpj"/>
						<logic:present name="portal-err-login-form" scope="request">
							<small class="form-text text-danger">
								<html:errors property="err-cpf-cnpj" />
							</small>
						</logic:present>
					</div>
					
					<div class="form-group row">
						<label for="senha">Senha</label>
						<html:password property="senha" styleClass="form-control" redisplay="false" maxlength="8" onkeypress="return isCampoNumerico(event);"/>
						<logic:present name="portal-err-login-form" scope="request">
							<small class="form-text text-danger">
								<html:errors property="err-senha" />
							</small>
						</logic:present>
					</div>
	
					<div class="form-group row">
						<button type="submit" class="btn btn-primary">Acessar</button>
					</div>
				</html:form>
				
			</div>
		</div>
	</div>

	<%@ include file="/jsp/portal/rodape.jsp"%>

	<script src="<bean:message key="caminho.js"/>util.js"></script>
	<script src="<bean:message key="caminho.portal.js"/>jquery-3.2.1.min.js"></script>
	<script src="<bean:message key="caminho.portal.js"/>jquery-ui.min.js"></script>
	<script src="<bean:message key="caminho.portal.js"/>jquery.mask.js"></script>
	<script src="<bean:message key="caminho.portal.js"/>popper.js"></script>
	<script src="<bean:message key="caminho.portal.js"/>bootstrap.min.js"></script>
	
	<script>
		$('.cpfOuCnpj').mask('000.000.000-00', {
			onKeyPress : function(cpfcnpj, e, field, options) {
				const masks = ['000.000.000-000', '00.000.000/0000-00'];
				const mask = (cpfcnpj.length > 14) ? masks[1] : masks[0];
				$('.cpfOuCnpj').mask(mask, options);
			}
		});
	</script>
</body>
</html:html>