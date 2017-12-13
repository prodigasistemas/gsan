<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

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
	<%-- 	<%@ include file="/jsp/portal/acesso-barra.jsp"%> --%>

	<html:form action="/segunda-via-conta.do" name="EmitirSegundaViaContaActionForm" type="gcom.gui.portal.EmitirSegundaViaContaActionForm" method="post">

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
					<p>
						Informe apenas os números da <b>Matrícula</b>, que se encontra na parte superior da sua conta de água:
					</p>

					<div class="container">
						<logic:notEmpty name="erro-segunda-via" scope="request">
							<div class="row">
								<div class="alert alert-danger">
									<html:errors property="erro-segunda-via" />
								</div>
							</div>
						</logic:notEmpty>
						<div class="row">
							<html:text property="matricula" size="9" maxlength="9" onkeypress="return isCampoNumerico(event);" styleClass="form-control col-sm-2" />
							<input type="submit" value="Consultar" class="btn btn-primary btn-segunda-via">
						</div>
						<br>
						<br>
					</div>

					<h3>Dados do Imóvel:</h3>
					
					<table class="table table-bordered text-center">
						<thead>
							<tr>
								<th style="width: 25%"><span>Matrícula: </span>${EmitirSegundaViaContaActionForm.matricula}</th>
								<th style="width: 75%"><span>Endereço: </span>${EmitirSegundaViaContaActionForm.endereco}</th>
							</tr>
						</thead>
					</table>
					
					<br>
					
					<h3>Contas em Aberto:</h3>

					<table class="table table-bordered text-center">
						<thead>
							<tr>
								<th style="width: 50%"><span>Quantidade de Contas: </span>${EmitirSegundaViaContaActionForm.quantidadeContas}</th>
								<th style="width: 50%"><span>Total: </span>R$ ${EmitirSegundaViaContaActionForm.valorTotalContas}</th>
							</tr>
						</thead>
					</table>

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
							<logic:notEmpty name="contas" scope="request">
								<logic:iterate name="contas" id="helper" type="ContaValoresHelper">
									<tr>
										<td><bean:write name="helper" property="formatarAnoMesParaMesAno" /></td>
										<td><bean:write name="helper" property="vencimentoConta" /></td>
										<td><bean:write name="helper" property="valorTotalConta" formatKey="money.format" /></td>
										<td>
											<a href="gerarRelatorio2ViaContaAction.do?cobrarTaxaEmissaoConta=N&idConta=<%="" + helper.getConta().getId()%>" title="Imprimir">
												<i class="fa fa-print"></i>
											</a>
										</td>
									</tr>
								</logic:iterate>
							</logic:notEmpty>

							<logic:empty name="contas" scope="request">
								<tr>
									<td colspan="4">Nenhuma fatura em aberto</td>
								</tr>
							</logic:empty>
						</tbody>
					</table>
					<br> <span><b>OBS:</b> A baixa da conta, após o pagamento, será efetuada em até 2 (dois) dias úteis, após compensação.</span>
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
</body>
</html:html>