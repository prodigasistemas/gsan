<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<%@ page import="gcom.cadastro.cliente.ClienteImovel"%>

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

<body onload="setarFoco('${requestScope.matriculaInformada}');">
	<%@ include file="/jsp/portal/cabecalho.jsp"%>
	<%-- 	<%@ include file="/jsp/portal/acesso-barra.jsp"%> --%>

	<div class="page-wrap">
		<div class="container pagina">
			<div class="container container-breadcrumb">
				<ul class="breadcrumb">
					<li class="breadcrumb-item"><a href="portal.do">Página Inicial</a></li>
					<li class="breadcrumb-item active">Cadastro de Login de Cliente</li>
				</ul>
			</div>

			<div class="pagina-titulo">
				<h2>Cadastro de Login de Cliente</h2>
			</div>

			<div class="pagina-conteudo">
				
				<logic:notPresent name="portalSucesso" scope="request">
					<p>
						Informe apenas os números da <b>Matrícula do Imóvel</b>, que se encontra na parte superior da sua conta de água:
					</p>

					<html:form
						action="/cadastro-login-cliente.do?acao=pesquisar"
						name="CadastroLoginClienteActionForm"
						type="gcom.gui.portal.CadastroLoginClienteActionForm"
						method="post">
						<div class="container">
							<logic:present name="portal-err-cadastro-login-cliente"
								scope="request">
								<div class="row">
									<div class="alert alert-danger" role="alert">
										<p>
											<html:errors property="portal-err-cadastro-login-cliente" />
										</p>
									</div>
								</div>
							</logic:present>

							<div class="row">
								<html:text property="matriculaInformada" size="9" maxlength="9"
									onkeypress="return isCampoNumerico(event);"
									styleClass="form-control col-sm-2" />
								<input type="submit" value="Consultar"
									class="btn btn-primary btn-consulta">
							</div>
							<br> <br>
						</div>
					</html:form>
				</logic:notPresent>
				
				<logic:present name="portalSucesso" scope="request">
					<div class="container">
						<div class="alert alert-success" role="alert">
							<h4 class="alert-heading">Cadastro de Login efetuado com sucesso!</h4>
							<hr>
							<p class="mb-0">Você receberá um <strong>email com o link de confirmação</strong> da solicitação de cadastro.</p>
						</div>
					</div>
				</logic:present>

				<logic:present name="CadastroLoginClienteActionForm" property="matriculaSelecionada">
					<h3>Dados do Imóvel:</h3>
					
					<table class="table table-bordered text-center">
						<thead>
							<tr>
								<th style="width: 25%"><span>Matrícula: </span>${CadastroLoginClienteActionForm.matriculaSelecionada}</th>
								<th style="width: 75%"><span>Endereço: </span>${CadastroLoginClienteActionForm.enderecoImovel}</th>
							</tr>
						</thead>
					</table>
					
					<br>
					
					<h3>Formulário de Cadastro:</h3>
				
					<div class="container container-form">
						<html:form action="/cadastro-login-cliente.do?acao=cadastrar" name="CadastroLoginClienteActionForm" type="gcom.gui.portal.CadastroLoginClienteActionForm" method="post">
							<div class="form-row">
								<div class="form-group col-6">
									<label for="nome">Nome Completo</label>
									<html:text property="nome" styleClass="form-control" style="text-transform: uppercase" maxlength="50" />
									<logic:present name="portal-err-cadastro-login-cliente-form" scope="request">
										<small class="form-text text-danger">
											<html:errors property="err-nome" />
										</small>
									</logic:present>
									<logic:notPresent name="portal-err-cadastro-login-cliente-form" scope="request">
										<small class="form-text text-muted">Informe seu nome conforme documento de identificação</small>
									</logic:notPresent>
								</div>
								
								<div class="form-group col-3">
									<label for="cpfOuCnpj">CPF ou CNPJ</label>
									<html:text property="cpfOuCnpj" styleClass="form-control cpfOuCnpj"/>
									<logic:present name="portal-err-cadastro-login-cliente-form" scope="request">
										<small class="form-text text-danger">
											<html:errors property="err-cpf-cnpj" />
										</small>
									</logic:present>
								</div>
								
								<div class="form-group col-3">
									<label for="dataNascimento">Data de Nascimento</label>
									<html:text property="dataNascimento" styleClass="form-control" onkeypress="$(this).mask('00/00/0000')"/>
									<logic:present name="portal-" scope="request">
										<small class="form-text text-danger">
											<html:errors property="err-data-nascimento" />
										</small>
									</logic:present>
									<logic:notPresent name="portal-err-cadastro-login-cliente-form" scope="request">
										<small class="form-text text-muted">Exemplo: 01/01/1980</small>
									</logic:notPresent>
								</div>
							</div>
							
							<div class="form-row">
								<div class="form-group col-3">
									<label for="celular">Celular</label>
									<html:text property="celular" styleClass="form-control" onkeypress="$(this).mask('(00) 00000-0000')"/>
									<logic:present name="portal-err-cadastro-login-cliente-form" scope="request">
										<small class="form-text text-danger">
											<html:errors property="err-celular" />
										</small>
									</logic:present>
									<logic:notPresent name="portal-err-cadastro-login-cliente-form" scope="request">
										<small class="form-text text-muted">Exemplo: (91) 9XXXX-XXXX</small>
									</logic:notPresent>
								</div>
								
								<div class="form-group col-5">
									<label for="email">Email</label>
									<html:text property="email" styleClass="form-control" style="text-transform: lowercase" maxlength="40"/>
									<logic:present name="portal-err-cadastro-login-cliente-form" scope="request">
										<small class="form-text text-danger">
											<html:errors property="err-email" />
										</small>
									</logic:present>
								</div>
								
								<div class="form-group col-2">
									<label for="senha">Senha</label>
									<html:password property="senha" styleClass="form-control" redisplay="false" maxlength="8" onkeypress="return isCampoNumerico(event);"/>
									<logic:present name="portal-err-cadastro-login-cliente-form" scope="request">
										<small class="form-text text-danger">
											<html:errors property="err-senha" />
										</small>
									</logic:present>
									<logic:notPresent name="portal-err-cadastro-login-cliente-form" scope="request">
										<small class="form-text text-muted">Somente números</small>
									</logic:notPresent>
								</div>
								
								<div class="form-group col-2">
									<label for="confirmarSenha">Confirmar Senha</label>
									<html:password property="confirmarSenha" name="confirmarSenha" styleClass="form-control" redisplay="false" maxlength="8" onkeypress="return isCampoNumerico(event);"/>
									<logic:present name="portal-err-cadastro-login-cliente-form" scope="request">
										<small class="form-text text-danger">
											<html:errors property="err-confirmar-senha" />
										</small>
									</logic:present>
								</div>
							</div>
							
							<input type="submit" value="Cadastrar" class="btn btn-primary">
						</html:form>
					</div>
				</logic:present>
				
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