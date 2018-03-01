<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN">

<html:html>
	<head>
		<title>Compesa | Serviços</title>
		<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
		<script language="JavaScript" src="<bean:message key="caminho.portal.js"/>jquery-1.4.2.min.js"></script>
		<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
		<link rel="stylesheet" href="<bean:message key="caminho.portal.css"/>style.css" type="text/css">
		<link rel="stylesheet" href="<bean:message key="caminho.portal.css"/>jquery.theme.css" type="text/css">
		<script language="JavaScript" src="<bean:message key="caminho.portal.js"/>jquery.blockUI.js"></script>

		<script type="text/javascript">
			$(document).ready(function(){
			
				}
			
				$('.btn-emitir').click(function(){
					var msg = '';
					if($.trim($('[name=matriculaImovel]').val()).length == 0){
						msg += 'Informe Imóvel. \n';
					}
					if($.trim($('[name=ano] option:selected').val()) <= 0){
						msg += 'Informar o ano de solicitação da declaração. \n';
					}
					
					if(msg.length > 0){
						$('#selecionarAno h3').html(msg);
						
						$.blockUI({
							message : $('#selecionarAno'),
							theme : true,
							title : 'Aviso',
							onBlock : function() {
								$('.ui-widget-overlay').removeClass('ui-widget-overlay');
							}
						});
					} else {
						$('form').submit();
					}
				});
				
				$('#btnPesquisar').click(function(){
					$('form')
						.attr('action', 'portal.do?method=declaracaoAnual')
						.submit();
				});
				
				$('.confirm').click(function(){
					$.unblockUI();
					$('#cpfCnpjSolicitante').focus().select();
				});
			});
		</script>
		
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
					
					$('.confirm').click(function() {
						$.unblockUI();
					});
				});
			</script>
		</logic:present>
	</head>
	
	<body>
		
		<div id="container">
	    	<%@ include file="/jsp/portal/cabecalho.jsp"%>

			<!-- Content - Start -->
			<div id="content">
			
				<%@ include file="/jsp/portal/cabecalhoImovel.jsp"%>
				
				<div class="serv-int" id="seg-via-declaracao">
	            	<h3>Emitir 2ª via de declaração anual de quitação de débitos<span>&nbsp;</span></h3>

	            	<html:form action="/emitir2viaDeclaracaoAnualQuitacaoDebitosAction.do" name="Emitir2viaDeclaracaoAnualQuitacaoDebitosActionForm"
	type="gcom.gui.faturamento.Emitir2viaDeclaracaoAnualQuitacaoDebitosActionForm" method="post">
						<html:text property="matriculaImovel" style="display:none;" value="${ExibirPortalActionForm.matricula}" />
	                	<fieldset>
	                    	<legend>Selecione o Ano de Refer&ecirc;ncia:</legend>
	                    	
	                        <html:select property="ano">
								<html:option value="-1">&nbsp;</html:option>
								<logic:present name="colecaoAnosImovel" scope="request">
									<html:options collection="colecaoAnosImovel"
										labelProperty="descricao" 
										property="descricao" />
								</logic:present>
							</html:select>
							
                      		<input type="submit" class="btn-emitir" value="" />
	                    </fieldset>
					</html:form>
	            </div>
	        </div>
			
			<%@ include file="/jsp/portal/rodape.jsp"%>
		</div><!-- Content - End -->
	</body>
	
	<div id="selecionarAno" style="display:none; cursor: default"> 
        <h3 style="text-align:center; padding-top:10px; padding-bottom: 10px;"></h3> 
        <a href="javascript:void(0);" class="ui-corner-all button confirm">OK</a> 
	</div>
	
	<logic:present name="exception" scope="request">
		<div id="exception" style="display:none; cursor: default;"> 
	        <h3 style="text-align:center; padding-top:10px; padding-bottom: 10px;">
	        	<bean:write name="exception" scope="request" />
	        </h3> 
			<a href="javascript:void(0);" class="ui-corner-all button confirm">OK</a>
		</div>
	</logic:present>
	
</html:html>