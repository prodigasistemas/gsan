<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<%@ page import="gcom.cadastro.imovel.Imovel"%>
<%@ page import="gcom.cobranca.bean.ContaValoresHelper"%>

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
	
	<%@ include file="/jsp/portal/acesso-barra.jsp"%>

	<div class="page-wrap">
		<div class="container pagina">
			<div class="container container-breadcrumb">
				<ul class="breadcrumb">
					<li class="breadcrumb-item"><a href="portal.do">Página Inicial</a></li>
					<li class="breadcrumb-item active">2ª Via de Conta</li>
				</ul>
			</div>

			<div class="pagina-titulo">
				<h2>Emissão de 2ª Via de Conta</h2>
			</div>

			<div class="pagina-conteudo">
				<logic:notPresent name="portalImoveis" scope="session">
					<html:form action="/segunda-via-conta.do?acao=pesquisar" name="EmitirSegundaViaContaActionForm" type="gcom.gui.portal.EmitirSegundaViaContaActionForm" method="post">
						<p>
							Informe apenas os números da <b>Matrícula do Imóvel</b>, que se encontra na parte superior da sua conta de água:
						</p>
	
						<div class="container">
							<logic:notEmpty name="portal-erro-segunda-via" scope="request">
								<div class="row">
									<div class="alert alert-danger">
										<html:errors property="portal-erro-segunda-via" />
									</div>
								</div>
							</logic:notEmpty>
							<div class="row">
								<html:text property="matricula" size="9" maxlength="9" onkeypress="return isCampoNumerico(event);" styleClass="form-control col-sm-2" />
								<input type="submit" value="Consultar" class="btn btn-primary btn-consulta">
							</div>
							<br>
							<br>
						</div>
					</html:form>
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
											<a href="segunda-via-conta.do?acao=selecionar&imovel=<%="" + imovel.getId()%>" title="Selecionar">
												<i class="fa fa-search"></i>
											</a>
										</td>
									</tr>
								</logic:iterate>
							</tbody>
						</table>
					</logic:notEmpty>
				</logic:present>

				<br>

				<logic:present name="EmitirSegundaViaContaActionForm" property="matriculaSelecionada">		
					<logic:empty name="portalCpfOuCnpjCliente" scope="session">	
					
					<h3>Dados do Imóvel</h3>
					<table class="table table-bordered text-center">
						<thead>
							<tr>
								<th style="width: 25%"><span>Matrícula: </span>${EmitirSegundaViaContaActionForm.matriculaSelecionada}</th>
								<th style="width: 75%"><span>Endereço: </span>${EmitirSegundaViaContaActionForm.endereco}</th>
							</tr>
						</thead>
					</table>
					
					<h3>Contas em Aberto</h3>
					<table class="table table-bordered text-center">
						<thead>
							<tr>
								<th style="width: 50%"><span>Quantidade de Contas: </span>${EmitirSegundaViaContaActionForm.quantidadeContas}</th>
								<th style="width: 50%"><span>Total: </span>R$ <FONT COLOR="#ffffff">$</FONT> ${EmitirSegundaViaContaActionForm.valorTotalContas} <FONT COLOR="#ffffff">#</FONT></th>
							</tr>
						</thead>
					</table>
	                </logic:empty>
	                
	                <logic:notEmpty name="portalCpfOuCnpjCliente" scope="session">
	                <h3>Contas em Aberto</h3>
					<table class="table table-bordered text-center">
						<thead>
							<tr>
							    <th style="width: 25%"><span>Matrícula: </span>${EmitirSegundaViaContaActionForm.matriculaSelecionada}</th>
								<th style="width: 50%"><span>Quantidade de Contas: </span>${EmitirSegundaViaContaActionForm.quantidadeContas}</th>
								<th style="width: 25%"><span>Total: </span>R$ ${EmitirSegundaViaContaActionForm.valorTotalContas}</th>
							</tr>
						</thead>
					</table>
	                </logic:notEmpty>
					<table class="table table-striped table-sm table-bordered text-center">
						<thead>
							<tr>
								<th>Mês/Ano</th>
								<th>Vencimento</th>
								<th>Valor (R$)</th>
								<th>Imprimir</th>
							</tr>
						</thead>
						<tbody>
							<logic:notEmpty name="portalContas" scope="request">
								<logic:iterate name="portalContas" id="helper" type="ContaValoresHelper">
									<tr>
										<td><bean:write name="helper" property="formatarAnoMesParaMesAno" /></td>
										<td><bean:write name="helper" property="vencimentoConta" /></td>
										<td> <FONT COLOR="#ffffff">$</FONT><bean:write name="helper" property="valorTotalContaFormatado" /><FONT COLOR="#ffffff">#</FONT></td>
										<td>
											<a href="gerarRelatorio2ViaContaAction.do?cobrarTaxaEmissaoConta=N&idConta=<%="" + helper.getConta().getId()%>" title="Imprimir">
												<i class="fa fa-print"></i>
											</a>
										</td>
									</tr>
								</logic:iterate>
							</logic:notEmpty>
	
							<logic:empty name="portalContas" scope="request">
								<tr>
									<td colspan="4">Nenhuma fatura em aberto</td>
								</tr>
							</logic:empty>
						</tbody>
					</table>
					
					<span><b>OBS:</b> A baixa da conta, após o pagamento, será efetuada em até 2 (dois) dias úteis, após compensação.</span>
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