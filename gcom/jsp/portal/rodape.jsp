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
	
	<link href="<bean:message key="caminho.portal.css"/>portal.css" rel="stylesheet">
	<link href="<bean:message key="caminho.portal.css"/>rodape.css" rel="stylesheet">
</head>

<body>
	<footer>
		<div class="container">
			<div class="row">
				<div class="col-sm-4">
					<h2>Companhia de Saneamento do Pará</h2>
					<h3><b>Endereço:</b> Av. Magalhães Barata, 1201 - São Brás. CEP: 66060-901 - Belém - Pará</h3>
					<h3><b>Telefone:</b> (91) 3202-8400 / <b>Fax:</b> (91) 3236-2199</h3>
				</div>
				<div class="col-sm-3">
					<h5>A Empresa</h5>
					<ul>
						<li><a href="http://www.cosanpa.pa.gov.br/index.php/a-empresa/2013-06-20-08-51-26" target="_blank">Histórico</a></li>
						<li><a href="http://www.cosanpa.pa.gov.br/index.php/acesso-a-informacao/2015-11-12-19-08-37" target="_blank">Institucional</a></li>
						<li><a href="http://www.cosanpa.pa.gov.br/index.php/a-empresa/diretoria"target="_blank">Diretoria</a></li>
					</ul>
				</div>
				<div class="col-sm-3">
					<h5>Acesso à Informações</h5>
					<ul>
						<li><a href="http://www.cosanpa.pa.gov.br/index.php/acesso-a-informacao/sobre-a-lai"target="_blank">Sobre a LAI</a></li>
						<li><a href="http://www.cosanpa.pa.gov.br/index.php/editais-pregoes/editais-pregoes"target="_blank">Editais e Pregões</a></li>
						<li><a href="http://www.cosanpa.pa.gov.br/index.php/a-empresa/downloaddocumentos"target="_blank">Downloads</a></li>
					</ul>
				</div>
				<div class="col-sm-2">
					<div class="social-networks">
						<a href="https://twitter.com/cosanpaoficial" class="twitter" target="_blank"><i class="fa fa-twitter"></i></a>
						<a href="https://www.facebook.com/cosanpa.pa" class="facebook" target="_blank"><i class="fa fa-facebook-official"></i></a>
					</div>
				</div>
			</div>
		</div>
		<div class="footer-copyright">
			<p>© 2017 - COSANPA. Todos os direitos reservados.</p>
		</div>
	</footer>
</body>
</html:html>