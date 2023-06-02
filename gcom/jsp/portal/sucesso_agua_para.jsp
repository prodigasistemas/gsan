<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<%@ page import="gcom.util.Util"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN">

<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="viewport" content="height=device-height, initial-scale=1.0">

<title>Cosanpa - Loja Virtual</title>

<link href="<bean:message key="caminho.portal.css"/>portal.css" rel="stylesheet">


<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js" ></script>

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>

<html:javascript staticJavascript="false"/>

</head>

<body>
	<%@ include file="/jsp/portal/cabecalho.jsp"%>
	<%@ include file="/jsp/util/mensagens.jsp"%>
	<%-- 	<%@ include file="/jsp/portal/acesso-barra.jsp"%> --%>

<html:form action="/sucesso-agua-para.do" method="post">

		<div class="page-wrap">
			<div class="container pagina">
				<div class="container container-breadcrumb">
					<ul class="breadcrumb">
						<li class="breadcrumb-item"><a href="portal.do">Página Inicial</a></li>
						<li class="breadcrumb-item active">Cadastro água Pará</li>
					</ul>
				</div>

				<div class="pagina-titulo">
					<h2>Conclusão do cadastro água Pará</h2>
				</div>

				<div class="pagina-conteudo">
					<p>
						<b>Dados cadastrados com sucesso!</b>
					</p>			
					<br> <span><b>OBS:</b> Os dados serão analisados.</span>
				</div>
			</div>
		</div>
	</html:form>

	<%@ include file="/jsp/portal/rodape.jsp"%>

	<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
	<script language="JavaScript" src="<bean:message key="caminho.portal.js"/>jquery-3.2.1.min.js"></script>
	<script language="JavaScript" src="<bean:message key="caminho.portal.js"/>jquery-ui.min.js"></script>
	<script language="JavaScript" src="<bean:message key="caminho.portal.js"/>popper.js"></script>
	<script language="JavaScript" src="<bean:message key="caminho.portal.js"/>bootstrap.min.js"></script>
	<SCRIPT LANGUAGE="JavaScript">

</SCRIPT>
</body>
</html:html>