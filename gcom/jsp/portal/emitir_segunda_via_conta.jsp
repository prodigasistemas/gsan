<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ page import="gcom.cobranca.bean.ContaValoresHelper"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN">

<html:html>
<head>
	<title>Compesa | Serviços</title>
	<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
	<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
	<script language="JavaScript" src="<bean:message key="caminho.portal.js"/>jquery-1.4.2.min.js"></script>
	<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
	<link rel="stylesheet" href="<bean:message key="caminho.portal.css"/>style.css" type="text/css" />
	<link rel="stylesheet" href="<bean:message key="caminho.portal.css"/>jquery.theme.css" type="text/css">
		<!-- [if lt IE 9]>
			<style type="text/css">
				#form-matricula input.campo-text {height:28px!important; padding-top:5px!important}
			</style>
		<![endif]-->
		<script language="JavaScript" src="<bean:message key="caminho.portal.js"/>jquery.blockUI.js"></script>

	<script type="text/javascript">
		$(document).ready(function(){

			$('.confirm').click(function(){
				$.unblockUI();
			});
			
			$('#btnPesquisar').click(function(){
				$('form')
						.attr('action', 'exibirServicosPortalCompesaAction.do?method=declaracaoAnual')
						.submit();
			});
		});
	</script>	
	
	<logic:present name="erroSistema" scope="request">
		<script type="text/javascript">
			$(document).ready(function(){
				$.blockUI({
					message : $('#erroSistema'),
					theme : true,
					title : 'Erro.',
					onBlock : function() {
						$('.ui-widget-overlay').removeClass('ui-widget-overlay');
					}
				});
				
				$('.ok').click(function(){
					$.unblockUI();
					return false;
				});
			});
		</script>
	</logic:present>
	
	<logic:present name="exception" scope="request">
			<script type="text/javascript">
				$(document).ready(function(){
					$.blockUI({
						message : $('#exception'),
						theme : true,
						title : 'Aviso',
						onBlock : function() {
							$('.ui-widget-overlay').removeClass('ui-widget-overlay');
						}
					});
				});
			</script>
		</logic:present>
	
	<script type="text/javascript">
		$(document).ready(function(){
			$('table').each(function(){
				$(this).children('tbody').children('tr:last').addClass('last-tr');
			});
		});
	</script>
</head>

<body onload="setarFoco('${requestScope.nomeCampo}');">
	<div id="container">
		<%@ include file="/jsp/portal/cabecalho.jsp"%>

		<!-- Content - Start -->
		<div id="content">
		
			<%@ include file="/jsp/portal/cabecalhoImovel.jsp"%>
			<div id="seg-via-conta" class="serv-int">
				<html:form 	action="/emitirSegundaViaContaAction.do?method=emitirSegundaVia" method="post" style="width:100%;">
					<html:hidden property="matricula" value="${ExibirServicosPortalCompesaActionForm.matricula}" />
					<html:hidden property="nomeUsuario" value="${ExibirServicosPortalCompesaActionForm.nomeUsuario}" />

					<h3>
						Emissão da 2ª via de conta<span style="width:10px; padding:0;">&nbsp;</span>
					</h3>

					<ul id="desc-debito">
						<li>
							<em>Valor das Faturas em Aberto: R$</em>
							${EmitirSegundaViaContaActionForm.valorDebito} <span style="width:2px;float:none;padding:0;">|</span>
						</li>
						<li>
							<em>Data:</em> ${EmitirSegundaViaContaActionForm.data} <span style="width:2px;float:none;padding:0;">|</span>
						</li>
						<li>
							<em>Valor do Débito a Cobrar: R$</em>
							${EmitirSegundaViaContaActionForm.valorDebitoCobrado}
						</li>
					</ul>
					<span style="display:none;">
						
					</span>
					<table summary="Tabela de contas" style="font-size:12px;">
						<thead>
							<tr>
								<th width="205">Mês / Ano da Fatura</th>
								<th width="125">Valor (R$)</th>
								<th width="117">Imprimir</th>
								<th width="">Pagar agora</th>
							</tr>
						</thead>
						<tbody>
							<%! int i = 2;%>
							<logic:iterate name="colecaoContasValores" id="contaValoresHelper" type="ContaValoresHelper">
								<tr class="tr-<%=(i%2) + 1%>">
									<td><bean:write name="contaValoresHelper" property="formatarAnoMesParaMesAno" /></td>
									<td>R$<bean:write name="contaValoresHelper" property="valorTotalConta" formatKey="money.format" /></td>
									<td>
										<a href="javascript:abrirPopup('gerarRelatorio2ViaContaAction.do?cobrarTaxaEmissaoConta=N&idConta='+<%="" + contaValoresHelper.getConta().getId()%>, 400, 800);" 
											title="Imprimir Fatura">
											<img src="imagens/portal/icons/print.gif" alt="Imprimir Fatura" />
										</a>
									</td>	
									
									<td>
										<div>
											<a href="#" title="Selecionar banco">Selecionar banco</a>
											<ul class="box-banco">
												<li><a href="javascript:abrirPopup('enviarDadosBancosAction.do?banco=BancoBrasil&idConta=<%="" + contaValoresHelper.getConta().getId()%>', 800, 600);" 
														title="Banco do Brasil">
														<img src="imagens/portal/general/banco-brasil.gif" alt="Banco do Brasil" />
													</a>
												</li>
												<li><a href="javascript:window.open('http://www.bancoreal.com.br','mywindow','width=700,height=700,toolbar=yes,
														location=yes,directories=yes,status=yes,menubar=yes,scrollbars=yes,copyhistory=yes,
														resizable=yes');window.close();" 
														title="Banco Santander">
														<img src="imagens/portal/general/banco-santander.gif" alt="Banco Santander" />
													</a>
												</li>
												<li><a href="javascript:
														window.open('http://www.bradesco.com.br','mywindow','width=700,height=700,toolbar=yes,
														location=yes,directories=yes,status=yes,menubar=yes,scrollbars=yes,copyhistory=yes,
														resizable=yes');window.close();" 
														title="Bradesco">
														<img src="imagens/portal/general/banco-bradesco.gif" alt="Bradesco" />
												</a>
												</li>
												<li><a href="javascript:window.open('http://www.caixa.gov.br','mywindow','width=700,height=700,toolbar=yes,
														location=yes,directories=yes,status=yes,menubar=yes,scrollbars=yes,copyhistory=yes,
														resizable=yes');window.close();"" 
														title="Caixa Econômica Federal">
														<img src="imagens/portal/general/banco-caixa.gif" alt="Caixa Econômica Federal" />
													</a>
												</li>
												<li class="close"><img
													src="imagens/portal/icons/close.gif" alt="" />
												</li>
											</ul>
										</div>
									</td>
								</tr>
								<% i++;%>
							</logic:iterate>
						</tbody>
					</table>
					<p class="obs">Obs: A baixa da conta, após o pagamento, será efetuada em até 2(dois) dias úteis, após compensação.</p>
				</html:form>
			</div>
		</div><!-- Content - End -->
	<%@ include file="/jsp/portal/rodape.jsp"%>
	</div><!-- Container - End -->
	<div id="erroSistema" style="display:none; cursor: default"> 
        <h3 style="text-align:center; padding-top:10px; padding-bottom: 10px;">Erro do sistema.</h3> 
        <input type="button" style="float:right;" class="ok" value="Ok" /> 
	</div>
	
	<logic:present name="exception" scope="request">
		<div id="exception" style="display:none; cursor: default;"> 
	        <h3 style="text-align:center; padding-top:10px; padding-bottom: 10px;">
	        	<bean:write name="exception" scope="request" />
	        </h3> 
			<a href="javascript:void(0);" class="ui-corner-all button confirm">OK</a>
		</div>
	</logic:present>
</body>
</html:html>