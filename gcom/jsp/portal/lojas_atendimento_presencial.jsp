<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<%@ page import="gcom.util.ConstantesSistema"%>
<%@ page import="gcom.gui.portal.LojaAtendimentoCompesaHelper"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN">

<html:html>
	<head>
		<title>Compesa</title>
		<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
		<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
		<script language="JavaScript" src="<bean:message key="caminho.portal.js"/>jquery-1.4.2.min.js"></script>
		<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
		<link rel="stylesheet" href="<bean:message key="caminho.portal.css"/>internal.css" type="text/css">
		<link rel="stylesheet" href="<bean:message key="caminho.portal.css"/>jquery.theme.css" type="text/css">
		<script language="JavaScript" src="<bean:message key="caminho.portal.js"/>jquery.blockUI.js"></script>
		
		<script type="text/javascript">
			$(document).ready(function(){
			
				$('#lista-informacoes li, #lista-informacoes li.link').hover(function(){
					$('.ativo').removeClass('ativo');
					$(this).find('a').addClass('ativo').css('color', '#FFF');
				}, function(){
					$('.ativo').removeClass('ativo').css('color', '#008FD6');;
				});
				
			
				$('[name=municipio]').change(function(){
					if(parseInt($(this).find('option:selected').val()) > 0) {
						$('form')
							.attr('action', window.location.href)
							.submit();
					}
				});
				
				$('#regiaoMetropolitana, #interior').hide();
				
				$('[name=municipio]').change(function(){
					if(parseInt($('[name=municipio]:visible option:selected').val()) > 0) {
						$('form')
							.attr('action', window.location.href)
							.submit();
					}
				});
				
				var local = $('#local').val();
				$('#interior, #regiaoMetropolitana').hide();
				if (local != '' && local !== undefined) {
					
					$('#' + local).show();
					$('.' + local).addClass('ativo');
					
					$('.' + local).parent().removeClass('link').addClass('localSelecionado');
					var texto = $('.' + local).html();
					$('.' + local).parent().html(texto)

					if(local == 'interior'){
						$('#interior select').removeAttr('disabled');
						$('#regiaoMetropolitana select').attr('disabled', true);
						
					} else if(local == 'regiaoMetropolitana'){
						$('#regiaoMetropolitana select').removeAttr('disabled');
						$('#interior select').attr('disabled', true);
					}
				}
				
				$('.lista-loja').hover(function(){
					var content = $(this).find('.detalhe').html();
					$('#content-detalhe').html(content);
				}, function(){
					$('#content-detalhe').html('');
				});
				
				if(parseInt($('[name=municipio] option:selected').val()) > 0){
					$('.lista-loja a:first').focus()
				}
				
				showHideDetails();
				
			});
			
			function showHideDetails(){
				if(parseInt($('[name=municipio]:visible option:selected').val()) > 0){
					$('#details').show();
				} else {
					$('#details').hide();
				}
			}
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
					
					$('#voltar').click(function(){
						$.unblockUI();
					});
				});
			</script>
		</logic:present>
				
		<link rel="stylesheet" href="<bean:message key="caminho.portal.css"/>style.css" type="text/css">
		<link rel="stylesheet" href="<bean:message key="caminho.portal.css"/>internal.css" type="text/css">

		<style type="text/css">
			.select-bg {
				display: inline-block;
				height: 30px;
				padding: 6px 0 0 4px;
				width: 100%;
			}
			
			.select-bg select {
				width: 375px; 
			}
			
			label {
				color: rgb(0, 143, 214);
				cursor: pointer;
				float: left;
				margin-left: 3px;
			}
			
			li {
				display: inline-block;
			}
			
			h3 {
				margin-bottom: 20px;
			}
			
			#lista-informacoes {
				float: left;
				padding: 10px 0 0 0px;
				width: 95%;
			}
			
			#lista-informacoes li {
				background: url("/gsan/imagens/portal/general/arrow-link.gif") no-repeat;
				float: left;
				height: 39px;
				margin-bottom: 5px;
				position: relative;
				width: 370px;
			}
			
			#lista-informacoes li.localSelecionado {
				background: url("/gsan/imagens/portal/general/arrow-link-hover.gif") no-repeat;
			}
			
			#lista-informacoes li.localSelecionado span {
				color: #FFF;
				font-size: 15px;
				font-weight: bold;
				height: 100%;
				position: absolute;
				width: 100%;
				margin-left: 50px;
				margin-top: 9px;
			}
			
			em {
			  color: rgb(0, 143, 214);
			  font-style: normal;
			  font-weight: 700;
			  padding-right: 5px;
			}
			
			.box {
				background-color: #EBEBEB;
				height: auto;
				width: auto; 
				margin-left:0;
				padding: 15px;
				-moz-border-radius: 5px; 
				-webkit-border-radius: 5px; 
				border-radius: 5px; 
				-moz-background-clip: padding; 
				-webkit-background-clip: padding-box; 
				background-clip: padding-box; 
			}
			
			#content-detalhe {
				width: 100%;
			}
			
			#content-detalhe p {
				padding: 6px 0 0 10px;
				width: 95%;
			}
			
			#content-detalhe p#loja {
				text-align: center;
				text-transform: uppercase;
			}
			
			.img-loja {
				padding: 0px !important; 
				margin-top: 5px; 
				border: 2px solid #008FD6;
			}
		</style>
	</head>
	<body>
		<div id="container">
	    	<%@ include file="/jsp/portal/cabecalho.jsp"%>
	        
	        <!-- Content - Start -->
	        <div id="content">
	        	
				<div class="serv-int" style="width:880px;">
					<logic:present name="local" scope="request">
			        	<input type="hidden" id="local" value="<bean:write name="local" scope="request"/>" />
		        	</logic:present>
		        
		        	<div style="width:100%;margin-bottom:35px;">
						<!-- Botão download Adobe Reader - Start -->
						<a href="http://get.adobe.com/br/reader/" title="Faça o download do Adobe Reader" class="adobe-reader" target="_blank"><img src="/gsan/imagens/portal/general/adobe-reader.gif" alt="Download do Adobe Reader" /></a>
						
						<a class="btn-voltar" title="Voltar" href="exibirServicosPortalCompesaAction.do">
							<img alt="Voltar" src="/gsan/imagens/portal/general/btn-voltar.gif">
						</a>
					</div>
				
					<h3>Lojas de Atendimento Personalizado<span>&nbsp;</span></h3>
					<br /><br /><br />
					<p class="paragrafo">
						<em>Atendimento ao cliente</em>
					</p>
					<p>&nbsp;</p>
					<p class="paragrafo">
						A Compesa está IMPLANTANDO uma fase no seu sistema de atendimento ao cliente com o objetivo de atender cada vez melhor,
						garantindo a eficiência, conforto e agilidade no atendimento das solicitações. Todos os setores envolvidos no atendimento
						foram preparados para garantir mais qualidade nas solicitações registradas e enviadas aos seus respectivos locais de execução.
					</p>
					<p>&nbsp;</p>
					<p class="paragrafo">
						O atendimento personalizado consiste no conjunto de lojas, atendimentos das Coordenações Regionais na Região
						Metropolitana do Recife (RMR) e os demais no interior do Estado.
					</p>
					
					<br /><br />
					<p class="paragrafo">
						<em>Regi&atilde;o Metropolitana do Recife e Regionais</em>
					</p>
					<p>&nbsp;</p>
					<p class="paragrafo">
						Algumas Lojas de Atendimento na região metropolitana contam com uma linha telefônica interligada exclusivamente com o Teleatendimento
						(0800 081 0195), onde o cliente poderá fazer consultas, reclamações, sugestões e solicitação de serviços.
					</p>
					<ul style="width:100%;margin-top:25px;">
						<li style="width:50%;float:left;">
							<div class="box">
								<b>Horário de atendimento das lojas e Coordenação Regional:</b>
								<br /><br />
								De Segunda a Sexta-feira: 8 às 17 horas (ininterrupto) <br />e aos Sábados: 8 às 12 horas
							</div>
						</li>
						<li style="width:50%;float:left">
							<div class="box">
								<b>Horário de atendimento do Expresso Cidadão:</b>
								<br /><br />
								De Segunda a Sexta-feira: 8 às 20 horas (ininterrupto) <br />e aos Sábados: 8 às 14 horas
							</div>
						</li>
					</ul>
				
					<p>&nbsp;</p>
					<p>&nbsp;</p>
					<p class="paragrafo">
						<em>Lojas de Atendimento</em>
					</p>
					<p>&nbsp;</p>
	        		<ul id="lista-informacoes">
		        		<li class="link">
		            		<a href="exibirLojasAtendimentoPresencialPortalCompesaAction.do?local=regiaoMetropolitana" class="regiaoMetropolitana">
								<span>Região metropolitana do Recife</span>
							</a>
		                </li>
		        		<li class="link">
		            		<a href="exibirLojasAtendimentoPresencialPortalCompesaAction.do?local=interior" class="interior">
								<span>Interior do estado</span>
							</a>
		                </li>
		        	</ul>
		        	
		        	<div id="solicitacao-serv">
		        	
			        	<html:form action="/exibirLojasAtendimentoPresencialPortalCompesaAction.do"
								name="ExibirLojasAtendimentoPresencialPortalCompesaActionForm"
								type="gcom.gui.portal.ExibirLojasAtendimentoPresencialPortalCompesaActionForm" method="post" >
								
							<div id="filtros">
			        			
				        		<div style="width:50%; float:left; display:none;" id="regiaoMetropolitana">
				        			<label>Selecione o munic&iacute;pio:</label>
					        		<div class="select-bg">
					        			<html:select property="municipio" disabled="true">
						        			<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
											<logic:present name="colecaoMunicipiosRegiaoMetropolitana" scope="request">
												<html:options collection="colecaoMunicipiosRegiaoMetropolitana" labelProperty="nome" property="id" />
											</logic:present>
										</html:select>
				        			</div>
				        		</div>
				        		<div style="width:50%; float:left; display:none;" id="interior">
				        			<label>Selecione o munic&iacute;pio:</label>
					        		<div class="select-bg">
					        			<html:select property="municipio" disabled="true">
						        			<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
											<logic:present name="colecaoMunicipiosInterior" scope="request">
												<html:options collection="colecaoMunicipiosInterior" labelProperty="nome" property="id" />
											</logic:present>
										</html:select>
				        			</div>
				        		</div>
			        		</div>
			        	</html:form>
			        	<div id="details">
		        			<div id="lojas" style="width: 370px; float: left; margin-left: 2px; margin-top: 16px; min-height:370px;">
		        				<ul id="lista-informacoes">
		        					<logic:present name="colecaoLojas">
			        					<logic:iterate name="colecaoLojas" id="loja" scope="request" type="LojaAtendimentoCompesaHelper">
				        					<li class="lista-loja">
						                        <logic:notEmpty name="loja" property="nomeLoja">
					        						<a href="javascript:void(0);">
														<span><bean:write name="loja" property="nomeLoja" /></span>
													</a>
												</logic:notEmpty>
												<div class="detalhe" style="display:none;">
							                        <logic:notEmpty name="loja" property="nomeLoja">
								                        <p id="loja">
								                        	<b><bean:write name="loja" property="nomeLoja" /></b>
														</p>
													</logic:notEmpty>
							                        <logic:notEmpty name="loja" property="endereco">
								                        <p id="endereco">
								                        	<b>Endereço:</b><br />
								                        	<bean:write name="loja" property="endereco" />
														</p>
													</logic:notEmpty>
							                        <logic:notEmpty name="loja" property="pontoReferencia">
								                        <p id="portoReferencia">
								                        	<b>Ponto de Referência:</b><br />
								                        	<bean:write name="loja" property="pontoReferencia" />
														</p>
													</logic:notEmpty>
							                        <logic:notEmpty name="loja" property="foneFax">
								                        <p id="fone">
								                        	<b>Fone / Fax:</b><br />
								                        	<bean:write name="loja" property="foneFax" />
														</p>
													</logic:notEmpty>
							                        <logic:notEmpty name="loja" property="email">
														<p id="email">
								                        	<b>E-mail:</b><br />
								                        	<bean:write name="loja" property="email" />
														</p>
													</logic:notEmpty>
													<logic:notEmpty name="loja" property="imagem">
														<p>
															<img class="img-loja" src='<bean:write name="loja" property="imagem" />' alt="Imagem da Loja" width="280" height="220"/>
														</p>
													</logic:notEmpty>
							                        <span id="bottom">&nbsp;</span>
							                    </div>
				        					</li>
										</logic:iterate>
		        					</logic:present>
		        				</ul>
		        			</div>
		        			<div class="detshow" style="background: url(/gsan/imagens/portal/general/info-servico-top.gif) no-repeat 0 0 #f5f6f6; width: 470px; float: left; margin-left: 2px; margin-top: 16px; min-height:370px;">
		        				<div id="content-detalhe"></div> 
	        				</div>
        					<span class="detshow" style="background: url(/gsan/imagens/portal/general/info-servico-bottom.gif) no-repeat scroll 0 0 transparent; float: left; height: 10px; width: 470px; margin-left:375px; ">&nbsp;</span>
		        		</div>
		        	</div>
	        	</div>
	       </div>
	       
	       <logic:present name="exception" scope="request">
				<div id="exception" style="display:none; cursor: default;"> 
			        <p style="text-align:justify; padding-top:10px; padding-bottom: 10px;">
			        	<bean:write name="exception" scope="request" />
			        </p> 
					<a href="javascript:void(0);" class="ui-corner-all button confirm" id="voltar">OK</a>
				</div>
			</logic:present>
	       
			<%@ include file="/jsp/portal/rodape.jsp"%>
	  	</div><!-- Container - End -->       
	</body>
</html:html>