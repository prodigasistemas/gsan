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
					<li class="breadcrumb-item active">Ouvidoria</li>
				</ul>
			</div>
			
			<div class="pagina-titulo">
				<h2>Ouvidoria</h2>
			</div>
			
			<div class="pagina-conteudo">
				<h3>O que é?</h3>
			
				<p>É o canal de relacionamento da empresa com a sociedade para atender as sugestões, reclamações, elogios e denúncias de usuários referentes
					aos serviços de abastecimento de água e de esgotamento sanitário prestados pela Companhia. E se estende aos empregados da Companhia.</p>
	
				<p>A Ouvidoria é acionada quando o cidadão não se sente totalmente atendido em seus direitos, por outros canais disponíveis às suas
					reivindicações.</p>
			</div>
			
			<div class="pagina-conteudo">
				<h3>Missão</h3>
	
				<p>Assegurar o direito de manifestação, garantir o direito à informação e sugerir medidas de aprimoramento com a busca de soluções para os
					problemas apontados.</p>
			</div>
			
			<div class="pagina-conteudo">
				<h3>Como acessar</h3>
	
				<p>O acesso será garantido através dos links abaixo, onde é disponibilizado um formulário próprio para o relato do usuário.</p>
				
				<div class="links-ouvidoria text-center">
					<a href="http://ouvidoria.cosanpa.pa.gov.br/OuvidoriaCliente/formulario.jsf" target="_blank">Clique aqui para fazer Reclamações</a>
					<br>
					<a href="http://ouvidoria.cosanpa.pa.gov.br/OuvidoriaCliente/formularioalt.jsf" target="_blank">Clique aqui para fazer Sugestões, Elogios ou Denúncias</a>
				</div>
				
				<p><b>OBS.:</b> Para ter acesso à Ouvidoria, em caso de reclamações, é necessário primeiro o acesso ao atendimento para gerar um número de protocolo.</p>
				
			</div>
		</div>
	</div>
	
	<%@ include file="/jsp/portal/rodape.jsp"%>
</body>
</html:html>