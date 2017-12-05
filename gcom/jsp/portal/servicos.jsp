<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN">

<html:html>
<head>
	<title>Compesa | Serviços</title>
	<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
	<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
	<script language="JavaScript" src="<bean:message key="caminho.portal.js"/>jquery-1.4.2.min.js"></script>
	<script language="JavaScript" src="<bean:message key="caminho.portal.js"/>jquery.blockUI.js"></script>
	<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
	<link rel="stylesheet" href="<bean:message key="caminho.portal.css"/>style.css" type="text/css">
	<link rel="stylesheet" href="<bean:message key="caminho.portal.css"/>internal.css" type="text/css">
	<link rel="stylesheet" href="<bean:message key="caminho.portal.css"/>jquery.theme.css" type="text/css">
	
	<script type="text/javascript">
		$(document).ready(function(){
			$('.info-serv').hide();
			$('#lista-servicos li, #lista-informacoes li').hover(function(){
				$('.ativo').removeClass('ativo');
				$(this).find('.info-serv').fadeIn(50);
				$(this).find('a').addClass('ativo').css('color', '#FFF');
			}, function(){
				$('.ativo').removeClass('ativo').css('color', '#008FD6');;
				$(this).find('.info-serv').fadeOut(50);
			});
		
			$('.confirm').click(function(){
				$.unblockUI();
			});
		});
	</script>
	<logic:present name="imovelSemDebito" scope="request">
		<script type="text/javascript">
			$(document).ready(function(){
				$.blockUI({
					message : $('#imovelSemDebitos'),
					theme : true,
					title : 'Aviso'
				});
			});
		</script>
	</logic:present>
	
	<logic:present name="debitoParceladoMesCorrente" scope="request">
		<script type="text/javascript">
			$(document).ready(function(){
				$.blockUI({
					message : $('#debitoParceladoMesCorrente'),
					theme : true,
					title : 'Aviso'
				});
			});
		</script>
	</logic:present>
	
	<logic:present name="imovelSemQuitacaoAnual" scope="request">
			<script type="text/javascript">
			$(document).ready(function(){
				$.blockUI({
					message : $('#imovelSemQuitacaoAnual'),
					theme : true,
					title : 'Aviso'
				});
			});
		</script>
	</logic:present>
	
	<!-- Validações Inicias de efetuar parcelamento de débitos -->
	<logic:present name="imovelParcelamentoAtivo" scope="request">
		<script type="text/javascript">
			$(document).ready(function(){
				$.blockUI({
					message : $('#imovelParcelamentoAtivo'),
					theme : true,
					title : 'Aviso'
				});
			});
		</script>
	</logic:present>
	
	<logic:present name="imovelSemDebitos" scope="request">
		<script type="text/javascript">
			$(document).ready(function(){
				$.blockUI({
					message : $('#imovelSemDebitos'),
					theme : true,
					title : 'Aviso'
				});
			});
		</script>
	</logic:present>
	
	<logic:present name="imovelNaoPossuiPerfilParcelamento" scope="request">
		<script type="text/javascript">
			$(document).ready(function(){
				$.blockUI({
					message : $('#imovelNaoPossuiPerfilParcelamento'),
					theme : true,
					title : 'Aviso'
				});
			});
		</script>
	</logic:present>
	<!-- Fim das validações Inicias de efetuar parcelamento de débitos -->
</head>
	
<body>
	<div id="container"> 
	   <html:form action="/portal.do?method=servicos" method="post" 
			name="ExibirPortalActionForm" type="gcom.gui.portal.ExibirPortalActionForm" >
    	<%@ include file="/jsp/portal/cabecalho.jsp"%>
        
        <!-- Content - Start -->
         <div id="content">
         <%@ include file="/jsp/portal/cabecalhoImovel.jsp"%>
            
            <ul id="lista-informacoes">
                <li>
            		<a href="segunda-via-conta.do">
            			<span>2ª Via da conta</span>
           			</a>
                	<div class="info-serv" style="text-align:justify;">
                        <p>Este acesso permite solicitar a segunda via da sua conta, que poderá ser paga nos agentes recebedores da Compesa. Para acessar este serviço, clique no assunto &quot;2ª Via de conta&quot; e digite o número da sua matrícula, e aguarde o término da impressão  para pagamento, ou se preferir dirija-se a uma loja de atendimento, ou entre em contato com o call center pelo número 0800 081 0195 ou pelo link fale conosco.</p>
                        <p>A Compesa disponibiliza mais este serviço sem custo adicional.</p>
                        <span id="bottom">&nbsp;</span>
                        <img src="imagens/portal/general/seta-info-servicos.gif" alt="Seta" />
                    </div>
                </li>
                <li id="serv-2">
                	<a href="portal.do?method=declaracaoAnual">
                		<span style="font-size:14px;">Declaração anual de quitação de débito</span>
               		</a>
                	<div class="info-serv" style="text-align:justify;">
                        <p>Conforme determina o artigo 3º da lei federal 12.007 de 2009 a Compesa disponibiliza para você a declaração de quitação anual de débitos. Lembramos que para este acesso o cliente deverá estar em dia com suas contas referentes ao ano de 2010.</p>
                        <p>Para acessar este serviço, clique no assunto declaração anual de quitação de débito ou dirija-se a uma loja de atendimento, ou entre em contato com o call center pelo número 0800 081 0195 ou pelo link fale conosco ou pelo e-mail dac0800@compesa.com.br</p>
                        <span id="bottom">&nbsp;</span>
                        <img src="imagens/portal/general/seta-info-servicos.gif" alt="Seta" />
                    </div>
                </li>
                <li id="serv-3">
                	<a href="exibirInserirCadastroEmailClientePortalAction.do?ok=sim">
                		<span>Recebimento de fatura por e-mail</span>
               		</a>
                	<div class="info-serv" style="text-align:justify;">
                        <p>A Compesa disponibiliza para você a facilidade de receber suas faturas em seu e-mail, para acessar este serviço, clique no assunto recebimento de fatura por email faça seu cadastro e receba suas contas sem sair de casa, ou dirija-se a uma loja de atendimento, ou entre em contato com o call center pelo número 0800 081 0195 ou pelo link fale conosco ou pelo e-mail dac0800@compesa.com.br</p>
                        <p>A Compesa disponibiliza mais este serviço sem custo adicional.</p>
                        <span id="bottom">&nbsp;</span>
                        <img src="imagens/portal/general/seta-info-servicos.gif" alt="Seta" />
                    </div>
                </li>
                <li id="serv-4">
                	<a href="exibirInserirCadastroContaBrailePortalAction.do">
                		<span>Solicitar conta em braile</span>
               		</a>
                	<div class="info-serv" style="text-align:justify;">
                        <p>Em atendimento a Lei Estadual nº 14.262 de 05 de janeiro de 2011, que  assegura aos portadores de deficiência visual o direito de receber os boletos de pagamento de suas contas de água, energia elétrica e telefonia, confeccionados em braille, estamos disponibilizando esta funcionalidade para emissão deste serviço, o qual será solicitado sem custo adicional.</p>
                        <p>Para acessar clique em solicitar conta em braille em solicitar conta em braille ou dirija-se a uma loja de atendimento ou entre em contato com o call center, através  do  número 0800 081 0195.</p>
                        <span id="bottom">&nbsp;</span>
                        <img src="imagens/portal/general/seta-info-servicos.gif" alt="Seta" />
                    </div>
                </li>
                <li id="serv-5">
                	<a href="exibirInserirSolicitacaoServicosPortalAction.do?init=1">
                		<span>Outros serviços</span>
               		</a>
                	<div class="info-serv">
                        <p>Por este acesso, será possível solicitar alguns serviços. Faça sua opção.</p>
                        <span id="bottom">&nbsp;</span>
                        <img src="imagens/portal/general/seta-info-servicos.gif" alt="Seta" />
                   	</div>
                </li>
                <li id="serv-6">
                	<a href="exibirEfetuarParcelamentoDebitosPortalAction.do?paginaServicos=SIM">
                		<span>Negociação de débitos</span>
               		</a>
                	<div class="info-serv" style="text-align:justify;">
                        <p>Este acesso permite simular as condições de regularização de seu débito à vista ou a prazo. Ao final da negociação, será gerado documento pagável nos agentes arrecadadores da Compesa.
						</p>
                        <span id="bottom">&nbsp;</span>
                        <img src="imagens/portal/general/seta-info-servicos.gif" alt="Seta" />
                    </div>
                </li>
            </ul>
        </div>
        <!-- Content - End -->
        
       <%@ include file="/jsp/portal/rodape.jsp"%>
       </html:form>
    </div>
    
	<!-- Avisos -->
	
	<logic:present name="imovelSemDebito" scope="request">
		<div id="imovelSemDebitos" style="display:none;cursor:default;">
			<img alt="Aviso" src="imagens/portal/icons/warning.png" alt="Aviso" style="float: left; padding-right:10px; margin-top: 10px;">
	        <h3 style="text-align:center; padding-top:10px; padding-bottom: 10px;">Imóvel sem débitos.</h3> 
			<a href="javascript:void(0);" class="ui-corner-all button confirm">OK</a>
		</div>
	</logic:present>
	
	<logic:present name="imovelSemQuitacaoAnual" scope="request">
		<div id="imovelSemQuitacaoAnual" style="display:none;cursor:default;">
			<img alt="Aviso" src="imagens/portal/icons/warning.png" alt="Aviso" style="float: left; padding-right:10px; margin-top: 10px;">
	        <h3 style="text-align:justify; padding-top:10px; padding-bottom: 10px;">Imóvel sem declaração anual de quitação de débitos</h3>
	        <p>
	        	Em caso de dúvidas, procure uma loja de atendimento mais próxima, ou entre em contato com o call center pelo 0800 081 0195
	        </p>
	        <a href="javascript:void(0);" class="ui-corner-all button confirm">OK</a>
		</div>
	</logic:present>
	
	<!-- Validações Inicias de efetuar parcelamento de débitos -->
	<logic:present name="imovelParcelamentoAtivo" scope="request">
		<div id="imovelParcelamentoAtivo" style="display:none; cursor: default;">
			<img alt="Aviso" src="imagens/portal/icons/warning.png" alt="Aviso" style="float: left; padding-right:10px; margin-top: 10px;">
	        <h3 style="padding-top:10px; padding-bottom: 10px;">Imóvel já possui um parcelamento não quitado/cobrado. </h3>
	        <p>
	        	Em caso de dúvidas, procure uma loja de atendimento mais próxima, ou entre em contato com o call center pelo 0800 081 0195.
	        </p>
	        <a href="javascript:void(0);" class="ui-corner-all button confirm">OK</a>
		</div>
	</logic:present>
	
	<logic:present name="imovelSemDebitos" scope="request">
		<div id="imovelSemDebitos" style="display:none; cursor: default;">
			<img alt="Aviso" src="imagens/portal/icons/warning.png" alt="Aviso" style="float: left; padding-right:10px; margin-top: 10px;">
	        <h3 style="padding-top:10px; padding-bottom: 10px;">O Imóvel informado não possui débitos. </h3>
	        <p>
	        	Em caso de dúvidas, procure uma loja de atendimento mais próxima, ou entre em contato com o call center pelo 0800 081 0195.
	        </p>
	        <a href="javascript:void(0);" class="ui-corner-all button confirm">OK</a>
		</div>
	</logic:present>
	
	<logic:present name="debitoParceladoMesCorrente" scope="request">
		<div id="debitoParceladoMesCorrente" style="display:none; cursor: default;">
			<img alt="Aviso" src="imagens/portal/icons/warning.png" alt="Aviso" style="float: left; padding-right:10px; margin-top: 10px;">
	        <h3 style="padding-top:10px; padding-bottom: 10px;">O débito deste imóvel já foi parcelado no mês de faturamento corrente. </h3>
	        <p>
	        	Em caso de dúvidas, procure uma loja de atendimento mais próxima, ou entre em contato com o call center pelo 0800 081 0195.
	        </p>
	        <a href="javascript:void(0);" class="ui-corner-all button confirm">OK</a>
		</div>
	</logic:present>
	
	<logic:present name="imovelNaoPossuiPerfilParcelamento" scope="request">
		<div id="imovelNaoPossuiPerfilParcelamento" style="display:none; cursor: default;">
			<img alt="Aviso" src="imagens/portal/icons/warning.png" alt="Aviso" style="float: left; padding-right:10px; margin-top: 10px;">
	        <h3 style="padding-top:10px; padding-bottom: 10px;">Não existe perfil de parcelamento correspondente à situação do imóvel. </h3>
	        <p>
	        	Em caso de dúvidas, procure uma loja de atendimento mais próxima, ou entre em contato com o call center pelo 0800 081 0195.
	        </p>
	        <a href="javascript:void(0);" class="ui-corner-all button confirm">OK</a>
		</div>
	</logic:present>
	<!-- Fim das validações Inicias de efetuar parcelamento de débitos -->
</body>
</html:html>