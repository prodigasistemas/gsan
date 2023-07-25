<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<%@ page import="gcom.gui.portal.LojaAtendimentoHelper"%>

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
	
 	<logic:present name="nomeUsuario">
		<%@ include file="/jsp/portal/acesso-barra.jsp"%>
	</logic:present>
	
	<div class="page-wrap">
		<div class="container pagina">
			<div class="container container-breadcrumb">
				<ul class="breadcrumb">
					<li class="breadcrumb-item"><a href="portal.do">Página Inicial</a></li>
					<li class="breadcrumb-item active">Lojas de Atendimento</li>
				</ul>
			</div>
			
			<div class="pagina-titulo">
				<h2>Lojas de Atendimento</h2>
			</div>
			
			<div class="pagina-conteudo">
				<h3>Atendimento ao Cliente</h3>
	
				<p class="paragrafo-unico">A COSANPA disponibiliza atendimento presencial personalizado, composto por um conjunto de Lojas e Unidades de Atendimento na Região
					Metropolitana de Belém e de Escritórios Locais no interior do Estado do Pará.</p>
			</div>
	
			<div class="pagina-conteudo">
				<h3>Região Metropolitana e Interior</h3>
	
				<p>Algumas lojas de atendimento na região metropolitana contam com uma linha telefônica interligada exclusivamente com o Call Center - <b>0800 0195
				 195</b>, onde o cliente poderá fazer consultas, reclamações, sugestões e solicitação de serviços.</p>
	
<!-- 				<div class="row"> -->
<!-- 					<div class="col-sm-6"> -->
<!-- 						<div class="pagina-cartao"> -->
<!-- 							<h4>Horário de Atendimento na região Metropolitana:</h4> -->
<!-- 							<p>De Segunda a Sexta das 08:00 às 17:00 e aos Sábados das 08:00 às 14:00.</p> -->
<!-- 						</div> -->
<!-- 					</div> -->
<!-- 					<div class="col-sm-6"> -->
<!-- 						<div class="pagina-cartao"> -->
<!-- 							<h4>Horário de Atendimento no Interior:</h4> -->
<!-- 							<p>De Segunda a Sexta das 08:00 às 17:00 e aos Sábados das 08:00 às 14:00.</p> -->
<!-- 						</div> -->
<!-- 					</div> -->
<!-- 				</div> -->
			</div>
			
			<logic:present name="localidades" scope="request">
				<div class="pagina-conteudo">
					<h3>Lojas e Unidades de Atendimento</h3>
					
					<html:form action="/lojas-de-atendimento.do" name="ExibirLojasAtendimentoActionForm" type="gcom.gui.portal.ExibirLojasAtendimentoActionForm" method="post">
						<html:select property="localidade" styleClass="form-control col-md-3 localidades">
							<html:option value="-1">Selecione uma Localidade</html:option>
							<html:options collection="localidades" property="descricao" />
						</html:select>

						<logic:present name="localidades" scope="request">
							<logic:present name="lojas" scope="request">
								<table class="table table-striped table-sm table-bordered text-center">
									<thead>
										<tr>
											<th style="width: 20%;">Localidade</th>
											<th style="width: 65%;">Endereço</th>
											<th style="width: 15%;">Horário</th>
										</tr>
									</thead>
									<tbody>
										<logic:iterate name="lojas" id="loja" scope="request" type="LojaAtendimentoHelper">
											<tr>
												<td><bean:write name="loja" property="localidade" /></td>
												<td><bean:write name="loja" property="endereco" /></td>
												<td><bean:write name="loja" property="horario" /></td>
											</tr>
										</logic:iterate>
									</tbody>
								</table>
							</logic:present>
						</logic:present>
						
					</html:form>
				</div>
			</logic:present>
		</div>
	</div>

	<%@ include file="/jsp/portal/rodape.jsp"%>
	
	<script language="JavaScript" src="<bean:message key="caminho.portal.js"/>jquery-3.2.1.min.js"></script>
	<script language="JavaScript" src="<bean:message key="caminho.portal.js"/>jquery-ui.min.js"></script>
	<script language="JavaScript" src="<bean:message key="caminho.portal.js"/>popper.js"></script>
	<script language="JavaScript" src="<bean:message key="caminho.portal.js"/>bootstrap.min.js"></script>
	
	<script language="JavaScript">
		$(document).ready(function(){
			$('[name=localidade]').change(function() {
				$('form').attr('action', window.location.href).submit();
			});
		});
	</script>
</body>

</html:html>