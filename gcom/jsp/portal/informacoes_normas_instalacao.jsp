<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN">

<html:html>
	<head>
		<title>Compesa</title>
		<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
		<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
		<script language="JavaScript" src="<bean:message key="caminho.portal.js"/>jquery-1.4.2.min.js"></script>
		<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
		<link rel="stylesheet" href="<bean:message key="caminho.portal.css"/>style.css" type="text/css">
		<link rel="stylesheet" href="<bean:message key="caminho.portal.css"/>internal.css" type="text/css">
	<style type="text/css">
						
			.lista ul li {
				list-style: url("/gsan/imagens/portal/general/marcador.gif");
				margin: 0 0 0 15px;
				list-style-position: outside;
				padding: 5px;
			}
			
			.lista li {
				list-style: decimal;
				list-style-position: inside;
				font-weight: bold;
				color: #008FD6;
				margin: 0 0 0 0px;
				padding: 0px;
							
			}
			
			.lista-condicoes li #lista ul li {
				list-style: upper-alpha inside none;				
				list-style-position: inside;
				margin: 0 0 0 30px;
				padding: 0 0px;
			}
			
			em {
    			color: #008FD6;
    			font-style: normal;
   				font-weight: 700;
    			padding-right: 5px;
			}
			font span{
   				color: #008FD6;
    			float: none;
   				margin: 0;
    			padding-bottom: 10px;
    			text-indent: 0;
				font-style:italic;
    			float: right;
    		}
    		.paragrafo {line-height: 30px;}
    		
    		#lista-criterios ul li
    		{
    			list-style-type: upper-alpha;
    			margin: 0 0 0 47px;
				padding:0 0px;				
    		}
			#atualizacao{
    			line-height:2.3em;
    			padding:0 15px; 
    			position:relative;    			
    			float:left; 
    			font-size:11px;
    			height:33px;
    			width: 315px;  	
    		}
    		.imagem{border: 5px solid #c9c9c9; margin-right: 15px;}
    		.pontilhada{border-bottom: 1px dashed #c9c9c9;}  
    		span{
    			font-weight: normal;
    			color: #2f2f2f;
    		} 					
		</style>
	</head>
	
	<body>
		<div id="container">
	    	<%@ include file="/jsp/portal/cabecalho.jsp"%>
	        
	        <!-- Content - Start -->
		   	<div id="content">
		        <%@ include file="/jsp/portal/cabecalhoInformacoes.jsp"%>
		        <html:form action="/exibirInformacoesPortalAction" type="gcom.gui.portal.ExibirInformacoesPortalActionForm">		        		
		        	<div id="pagina" class="serv-int" style="width:880px;">
		        		<p>&nbsp;</p>						
	        			<h3>
							Normas de Instalação e Individualização Predial<span>&nbsp;</span>
						</h3>						
						<br />
						<br />
						<div id="medicaoIndividualizada" class="lista pontilhada">
							<p class="paragrafo"><em><b>Medição Individualizada</b></em></p>
							<br />
							
							<img src="/gsan/imagens/portal/general/medicao_individializada.gif" class="imagem" style="float: left"/>
							<p style="text-align: justify">
								<span>A Medição Individualizada de Água em Edifícios / Condomínios consiste na  instalação de um hidrômetro em cada apartamento, de maneira que seja possível medir o seu consumo individual.</span>
							</p>
							<br />
							<p style="text-align: justify">
								<span>Implantado pela COMPESA há 16 anos, em 1994, os resultados são satisfatórios, especialmente no que se refere à:</span>
							</p>
							<br />
							<ul style="margin-left: 210px;">
								<li><span>Redução do consumo de água nos edifícios;</span></li>
								<li><span>Diminuição do desperdício e da inadimplência e</span></li>
								<li><span>Aumento da satisfação dos clientes.</span></li>
							</ul>
							
							<p style="text-align: justify">
								<span>Neste modelo de Medição de Consumo Individualizado, desenvolvido e implantado pela Gerência de Micromedição da COMPESA, o hidrômetro deixa de ser um mero medidor divisório para ser um medidor de consumo real.</span>
							</p>
							<br />
							<br />
							<p style="text-align: justify">
								<span>A conta no final do mês é uma lógica justa: quem gastar mais vai pagar mais. O medidor que abastece o prédio é mantido para apurar o consumo total do edifício.</span>
								 <span>A conta de água e esgoto passa a ser estabelecida através do consumo individual de cada apartamento.</span>
							</p>
							<br />
							<br />
							<p style="text-align: justify">
								<span>No decorrer destes 16 anos, mais de 64.880 hidrômetros foram instalados em 3.594 edifícios / condomínios.</span>
							</p>
							<br />
							<br />
							<p style="text-align: justify">
								<span>Pernambuco é o Estado brasileiro pioneiro a adotar em larga escala o processo de quantificação de conta de água através da medição individualizada por apartamento.</span>
							</p>
							<br />
							<br />
							<br />
							<div id="links" style="width: 525px">
								<logic:present name="arquivoLeiNormaMedicao" scope="request">
									<a href="exibirInformacoesPortalAction.do?modo=verLeiIndividualizacao" target="_blank"><img src="/gsan/imagens/portal/general/botao_pdf.gif" style="float: left"/></a>
									<em><bean:write property="descrissaoLeiIndividualizacao" name="ExibirInformacoesPortalActionForm" /></em>
									<br />
									<br />
									<br />
								</logic:present>
								
								<logic:present name="arquivoNormaCO" scope="request">
									<a href="exibirInformacoesPortalAction.do?modo=verNormaCO" target="_blank"><img src="/gsan/imagens/portal/general/botao_pdf.gif" style="float: left"/></a>
									<em><bean:write property="descrissaoNormaCO" name="ExibirInformacoesPortalActionForm" /></em>
									<br />
									<br />
									<br />
								</logic:present>
								
								<logic:present name="arquivoNormaCM" scope="request">
									<a href="exibirInformacoesPortalAction.do?modo=verNormaCM" target="_blank"><img src="/gsan/imagens/portal/general/botao_pdf.gif" style="float: left"/></a>
									<em><bean:write property="descrissaoNormaCM" name="ExibirInformacoesPortalActionForm" /></em>
								</logic:present>
							</div>
							<br />
							<br />
							<br />
							<br />
							<font><span style="font-size: 11px;">Fonte:COMPESA/GMI/JAN/2011</span></font>
							<br />
							<br />
							<div id="atualizacao" style="background-image: url(/gsan/imagens/portal/general/ultima_atualizacao.png);background-repeat: no-repeat;">
									<span style="position: absolute; padding-top: 7px;"> Última atualização (segunda, 17 de Janeiro de 2011)</span>
							</div>
							<br />
							<br />
							<br />
						</div><!-- Medicao Individualizada -->
						
						<div id="instalacaoMicromedidor" class="lista pontilhada">
							<br />
							<br />
							<em>Instalação Micromedidor</em>
							<br />
							<br />
							<br />
							
							<img src="/gsan/imagens/portal/general/instalacao_micromedidor.gif" class="imagem" style="float: left"/>
							<p style="text-align: justify">
								<span>Para efetivar a Medição Individualizada em Edifícios/Condomínios antigos e novos de acordo com a Norma em vigor na Compesa, é preciso preencher Termo de compromisso (modelo fornecido pela Compesa, na Loja de Atendimento ao Cliente)</span> 
								<span>ao qual deverá ser anexada a Ata da reunião de condomínio, onde deve constar na referida Ata a aceitação integral das condições para implantação da medição individualizada, aprovada pela maioria dos moradores presentes na reunião, ou seja, 50% (cinqüenta por cento) + 1 (um) do total de condôminos.</span>
							</p>
							<br />
							<ul style="margin-left: 225px;">
								<li><span>O condomínio deve estar adimplente e o responsável (Síndico ou seu representante legal) deverá apresentar, na Loja de Atendimento ao Cliente, toda documentação para análise.</span></li>
								<li><span>Edifício/Condomínio Novo ? é a edificação cujo respectivo projeto de instalação hidráulica foi protocolado para análise no órgão competente de cada Município, após o dia 22/06/2004, data de início da vigência da Lei Estadual 12.609;</span></li>
								<li><span>Edifício/Condomínio Antigo ? é a edificação cujo respectivo projeto de instalação hidráulica foi protocolado para análise no órgão competente de cada Município, antes do dia 22/06/2004, data de início da vigência da Lei Estadual 12.609;</span> </li>
							</ul>
							<br />
							<p style="text-align: justify">
								<span>Destacamos que os custos das modificações a serem realizadas nas instalações hidráulicas dos edifícios/condomínios antigos e novos são de responsabilidade do condomínio. 
								Os custos das adaptações variam em função das características das instalações hidráulicas de cada prédio, podendo ser facilitado para os condomínios que disponham do Projeto hidráulico original. 
								Algumas condições técnicas precisam ser obedecidas para que seja possível fazer a individualização: o apartamento deve ser alimentado por um único ponto onde será instalado o medidor; 
								é vedada a utilização de válvulas de descargas ou similar (por problemas técnicos); e não pode haver interligação das tubulações de água entre os diversos apartamentos do edifício.</span>
							</p>
							<br />
							<br />
							<br />
							<br />
							<!-- <font><span style="font-size: 11px;">Fonte:COMPESA/DAC/JUN/2007</span></font> -->
							<br />
							<br />
							<div id="atualizacao" style="background-image: url(/gsan/imagens/portal/general/ultima_atualizacao.png);background-repeat: no-repeat;">
									<span style="position: absolute; padding-top: 7px;"> Última atualização (quarta, 24 de Novembro de 2010)</span>
							</div>
							<br />
							<br />
							<br />
						</div><!-- Instalacao Micromedidor -->
						
						<div id="vantagensMicromedidor" class="pontilhada">
							<br />
							<br />
							<em>Vantagens do Micromedidor</em>
							<br />
							<br />
							<br />
							
							<ul id="listapontosvista" class="lista"><!-- listapontosvista - Inicio -->
								<li><b><em>Do ponto de vista do consumidor</em></b>
									<br />
									<br />
									<p><span>As principais vantagens da medição individualizada de água nos apartamentos de edifícios multifamiliares, são:</span></p>
									<ul id="listavantconsumidor">
										<li><span>Pagamento proporcional ao consumo, ou seja, um apartamento que só tenha um consumidor não pagará em forma semelhante ao que possua 6, 8 ou 10 pessoas;</span></li>
										<li><span>O cliente não pagará pelo desperdício dos outros;</span></li>
										<li><span>Um cliente bom pagador jamais terá a sua água cortada pela irresponsabilidade dos maus pagadores;</span></li>
										<li><span>Redução do valor do pagamento da conta de água, em alguns casos de até 25%;</span></li>
										<li><span>Redução do consumo do edifício em até 30%;</span></li>
										<li><span>Possibilidade de localizar vazamentos internos nos apartamentos, que, às vezes, levam meses e até anos para serem identificados;</span></li>
										<li><span>Maior satisfação do cliente, já que ele passa a controlar diretamente a sua conta de água.</span></li>
									</ul>
									<br />
									<br />
								</li>
								<li><b><em>Do ponto de vista da concessionária</em></b>
									<br />
									<br />
									<span>Os principais benefícios das empresas concessionárias de água, são:</span>
									
									<ul>
										<li><span>Redução do índice de inadimplência, pois somente é cortada a água dos maus pagadores, e na prática esses passam a ser bons pagadores;</span></li>
										<li><span>Redução do consumo de água, podendo atingir, em média, 25%;</span></li>
										<li><span>Redução do número de reclamações de consumo, refletindo-se numa melhor imagem perante a população;</span></li>
										<li><span>Aumento do faturamento em torno de 20% devido ao efeito da tarifa progressiva.</span></li>
									</ul>
									<br />
									<br />
								</li>
								
								<img src="/gsan/imagens/portal/general/vantagens_micromedidor.gif" class="imagem" style="float: left"/>
								<li><b><em>Do ponto de vista dos construtores</em></b>
									<br />
									<br />
									<span>Para os construtores, o principal benefício comentado, é:</span>
									
									<ul style="list-style-position: outside; margin-left: 223px;">
										<li><span>Maior facilidade de venda dos apartamentos com medição individualizada de água.</span></li>
										<li><span>Menor custo na obra, no que se refere às instalações hidráulicas.</span></li>
									</ul>
									<br />
									<br />
									<br />
									
								</li>
								<li><b><em>Aspecto Legal</em></b>
									<br />
									<br />
									<span>Lei Estadual 12.609 de 22/06/2004 do Governo do Estado de Pernambuco.</span>
								</li>
							</ul><!-- listapontosvista - Fim -->
							

							<br />
							<br />
							<br />
							<font><span style="font-size: 11px;">Fonte:COMPESA/GMI/JAN/2011</span></font>
							<br />
							<br />
							<div id="atualizacao" style="background-image: url(/gsan/imagens/portal/general/ultima_atualizacao.png);background-repeat: no-repeat;">
									<span style="position: absolute; padding-top: 7px;"> Última atualização (segunda, 17 de Janeiro de 2011)</span>
							</div>
							<br />
							<br />
							<br />
						</div><!-- Vantagens Micromedidor -->
							        
		       		</div><!-- Pagina - End -->
		       		</html:form>
	       	</div><!-- content -->
	       	
			<%@ include file="/jsp/portal/rodape.jsp"%>
			
	  	</div><!-- Container - End -->       
	</body>
</html:html>