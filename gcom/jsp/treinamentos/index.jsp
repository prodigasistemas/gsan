<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN">

<html:html>

<style type="text/css">
[data-toggle="collapse"] .fa:before {  
  content: "\f13a";
}

[data-toggle="collapse"].collapsed .fa:before {
  content: "\f139";
}
</style>

<head>

<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="viewport" content="height=device-height, initial-scale=1.0">

<title>Cosanpa - Treinamentos</title>

<link href="<bean:message key="caminho.portal.css"/>portal.css" rel="stylesheet">
<link href="gcom/css/treinamentos/style.css" rel="stylesheet">


</head>

<body>
	<%@ include file="/jsp/portal/cabecalho.jsp"%>
	
		<div class="page-wrap">
		<div class="container pagina">
			<div class="container container-breadcrumb">
				<ul class="breadcrumb">
					<li class="breadcrumb-item"><a href="portal.do">Treinamentos</a></li>
				</ul>
			</div>
			
			<div class="pagina-titulo">
				<h2>V&iacutedeos</h2>
			</div>
			
			<div class="pagina-conteudo">
				<h3>Faturamento</h3>
	
				<p><a data-toggle="collapse" href="#collapseExample" aria-expanded="false" aria-controls="collapseExample"><i class="fa" aria-hidden="true"></i> Mudanca de Titularidade</a></p>
					<div class="collapse" id="collapseExample">
					  <div class="card card-body">
					  		<div class="embed-responsive embed-responsive-16by9">
					   			 <iframe src="http://vazamento.cosanpa.pa.gov.br/gsan/tutoriais/mudanca_titularidade.htm" allowfullscreen="true" border="none"></iframe>
					   		</div>
					  </div>
					</div>

			</div>

	
			<div class="pagina-conteudo">
				 <h3>Financeiro</h3>
	
				<p></p> 
	
			</div>

</body>
</html:html>