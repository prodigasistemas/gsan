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
	<%@ include file="/jsp/portal/cabecalho.jsp"%>
	
<%-- 	<%@ include file="/jsp/portal/acesso-barra.jsp"%> --%>

	<logic:notPresent name="matricula" scope="session">
		<section class="header text-center">
			<div class="container">
				<h1>Bem vindo  Loja Virtual</h1>
			</div>
		</section>
	</logic:notPresent>
	
	<div class="page-wrap">
		<div class="container">
			<div class="row">
				<div class="col-md-3">
					<a href="#">
						<div class="panel-modulo">
							<a href="segunda-via-conta.do?action=resetar">
								<div class="panel-link">
									<i class="fa fa-barcode fa-3x" aria-hidden="true"></i>
									<h3>2 Via de Conta</h3>
								</div>
							</a>
						</div>
					</a>
				</div>
				
				<div class="col-md-3">
					<a href="#">
						<div class="panel-modulo">
							<a href="certidao-negativa-imovel.do">
								<div class="panel-link">
									<i class="fa fa-home fa-3x" aria-hidden="true"></i>
									<h3>certidão Negativa de Imóvel</h3>
								</div>
							</a>
						</div>
					</a>
				</div>
				
				<div class="col-md-3">
					<a href="#">
						<div class="panel-modulo">
							<a href="certidao-negativa-cliente.do">
								<div class="panel-link">
									<i class="fa fa-user fa-3x" aria-hidden="true"></i>
									<h3>certidão Negativa de Cliente</h3>
								</div>
							</a>
						</div>
					</a>
				</div>
	
	<!-- 			<div class="col-md-3"> -->
	<!-- 				<div class="panel-modulo"> -->
	<!-- 					<a href="#"> -->
	<!-- 						<div class="panel-link"> -->
	<!-- 							<i class="fa fa-handshake-o fa-3x" aria-hidden="true"></i> -->
	<!-- 							<h3>Negociao de Dbitos</h3> -->
	<!-- 						</div> -->
	<!-- 					</a> -->
	<!-- 				</div> -->
	<!-- 			</div> -->
	
	<!-- 			<div class="col-md-3"> -->
	<!-- 				<div class="panel-modulo"> -->
	<!-- 					<a href="#"> -->
	<!-- 						<div class="panel-link"> -->
	<!-- 							<i class="fa fa-envelope fa-3x" aria-hidden="true"></i> -->
	<!-- 							<h3>Fatura por Email</h3> -->
	<!-- 						</div> -->
	<!-- 					</a> -->
	<!-- 				</div> -->
	<!-- 			</div> -->
	
	<!-- 			<div class="col-md-3"> -->
	<!-- 				<div class="panel-modulo"> -->
	<!-- 					<a href="#"> -->
	<!-- 						<div class="panel-link"> -->
	<!-- 							<i class="fa fa-check-square fa-3x" aria-hidden="true"></i> -->
	<!-- 							<h3>Declarao Anual de Quitao de Dbitos</h3> -->
	<!-- 						</div> -->
	<!-- 					</a> -->
	<!-- 				</div> -->
	<!-- 			</div> -->
				
	<!-- 			<div class="col-md-3"> -->
	<!-- 				<div class="panel-modulo"> -->
	<!-- 					<a href="#"> -->
	<!-- 						<div class="panel-link"> -->
	<!-- 							<i class="fa fa-list fa-3x" aria-hidden="true"></i> -->
	<!-- 							<h3>Estrutura Tarifria</h3> -->
	<!-- 						</div> -->
	<!-- 					</a> -->
	<!-- 				</div> -->
	<!-- 			</div> -->
				
	<!-- 			<div class="col-md-3"> -->
	<!-- 				<div class="panel-modulo"> -->
	<!-- 					<a href="#"> -->
	<!-- 						<div class="panel-link"> -->
	<!-- 							<i class="fa fa-group fa-3x" aria-hidden="true"></i> -->
	<!-- 							<h3>Tarifa Social</h3> -->
	<!-- 						</div> -->
	<!-- 					</a> -->
	<!-- 				</div> -->
	<!-- 			</div> -->
	
	<!-- 			<div class="col-md-3"> -->
	<!-- 				<div class="panel-modulo"> -->
	<!-- 					<a href="#"> -->
	<!-- 						<div class="panel-link"> -->
	<!-- 							<i class="fa fa-wrench fa-3x" aria-hidden="true"></i> -->
	<!-- 							<h3>Normas de Instalao</h3> -->
	<!-- 						</div> -->
	<!-- 					</a> -->
	<!-- 				</div> -->
	<!-- 			</div> -->
				
				<div class="col-md-3">
					<div class="panel-modulo">
						<a href="lojas-de-atendimento.do">
							<div class="panel-link">
								<i class="fa fa-map-marker fa-3x" aria-hidden="true"></i>
								<h3>Lojas de Atendimento</h3>
							</div>
						</a>
					</div>
				</div>
	
				<div class="col-md-3">
					<div class="panel-modulo">
						<a href="canais-de-atendimento.do?pagina=callcenter">
							<div class="panel-link">
								<i class="fa fa-phone fa-3x" aria-hidden="true"></i>
								<h3>Call Center</h3>
							</div>
						</a>
					</div>
				</div>
	
				<div class="col-md-3">
					<div class="panel-modulo">
						<a href="canais-de-atendimento.do?pagina=ouvidoria">
							<div class="panel-link">
								<i class="fa fa-comments fa-3x" aria-hidden="true"></i>
								<h3>Ouvidoria</h3>
							</div>
						</a>
					</div>
				</div>
				<div class="col-md-3">
					<a href="#">
						<div class="panel-modulo">
							<a href="extrato-debitos.do?action=resetar">
								<div class="panel-link">
									<i class="fa fa-money fa-3x" aria-hidden="true"></i>
									<h3>Extrato de Debitos</h3>
								</div>
							</a>
						</div>
					</a>
				</div>
				<div class="col-md-3">
					<a href="#">
						<div class="panel-modulo">
							<a href="agua-para.do?action=resetar">
								<div class="panel-link">
									<img src="<bean:message key="caminho.portal.imagens"/>general/agua-para-logo.png" width="120">
									<h3>Recadastro Água Pará</h3>
									<br>
									<h4>19/06/2023 a 30/06/2023</h4>
								</div>
							</a>
						</div>
					</a>
				</div>
				<div class="col-md-3">
					<a href="#">
						<div class="panel-modulo">
							<a href="cadastro-login-cliente.do">
								<div class="panel-link">
									<i class="fa fa-address-book fa-3x" aria-hidden="true"></i>
									<h3>Cadastro do Cliente</h3>

								</div>
							</a>
						</div>
					</a>
				</div>				
			</div>
		</div>
	</div>

	<%@ include file="/jsp/portal/rodape.jsp"%>

	<script language="JavaScript" src="<bean:message key="caminho.portal.js"/>jquery-3.2.1.min.js"></script>
	<script language="JavaScript" src="<bean:message key="caminho.portal.js"/>popper.js"></script>
	<script language="JavaScript" src="<bean:message key="caminho.portal.js"/>bootstrap.min.js"></script>
</body>
</html:html>
