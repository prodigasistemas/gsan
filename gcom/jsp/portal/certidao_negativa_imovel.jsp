<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<%@ page import="gcom.gui.portal.LojaAtendimentoHelper"%>
<%@ page import="gcom.cadastro.imovel.Imovel"%>

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
	
 	<logic:present name="nomeUsuario">
		<%@ include file="/jsp/portal/acesso-barra.jsp"%>
	</logic:present>
	
	<div class="page-wrap">
		<div class="container pagina">
			<div class="container container-breadcrumb">
				<ul class="breadcrumb">
					<li class="breadcrumb-item"><a href="portal.do">Página Inicial</a></li>
					<li class="breadcrumb-item active">Certidão Negativa de Imóvel</li>
				</ul>
			</div>
	
			<div class="pagina-titulo">
				<h2>Certidão Negativa de Imóvel</h2>
			</div>
	
			<div class="pagina-conteudo">
			<logic:notPresent name="portalImoveis" scope="session">
				<p>
					Informe apenas os números da <b>Matrícula do Imóvel</b>, que se encontra na parte superior da sua conta de água:
				</p>
				
				<div class="container">
					<html:form action="/certidao-negativa-imovel.do?acao=gerar" name="GerarCertidaoNegativaActionForm"
						type="gcom.gui.atendimentopublico.GerarCertidaoNegativaActionForm" method="post">
						
						<logic:notEmpty name="erro-certidao-imovel" scope="request">
							<div class="row">
								<div class="alert alert-danger">
									<html:errors property="erro-certidao-imovel" />
								</div>
							</div>
						</logic:notEmpty>
						<div class="row">
							<html:text property="idImovel" size="9" maxlength="9" onkeypress="return isCampoNumerico(event);" styleClass="form-control col-sm-2" />
							<input type="submit" value="Gerar" class="btn btn-primary btn-consulta">
						</div>
					</html:form>
				</div>
				</logic:notPresent>
				<logic:present name="portalImoveis" scope="session">
					<logic:notEmpty name="portalImoveis" scope="session">
						<h3>Selecionar Imóvel</h3>
						
						<table class="table table-striped table-sm table-bordered text-center">
							<thead>
								<tr>
									<th class="col-2">Matrícula</th>
									<th class="col-9">Endereço</th>
									<th class="col-1">Selecionar</th>
								</tr>
							</thead>
							<tbody>
								<logic:iterate name="portalImoveis" id="imovel" type="Imovel">
									<tr>             
										<td><bean:write name="imovel" property="id" /></td>
										<td><bean:write name="imovel" property="enderecoFormatado" /></td>
										<td>
											<a href="certidao-negativa-imovel.do?acao=selecionar&imovel=<%="" + imovel.getId()%>" title="Selecionar">
												<i class="fa fa-search"></i>
											</a>
										</td>
									</tr>
								</logic:iterate>
							</tbody>
						</table>
					</logic:notEmpty>
				</logic:present>
				
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
