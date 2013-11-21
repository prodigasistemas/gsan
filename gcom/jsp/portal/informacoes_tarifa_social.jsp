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
						
			.lista-condicoes ul li {
				list-style: url("/gsan/imagens/portal/general/marcador.gif");
				margin: 0 0 0 15px;
				list-style-position: outside;
				padding: 0px;
			}
			
			.lista-condicoes li {
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
				font-weight: bold;
				color: #000;
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
    		.paragrafo {
    			line-height: 30px;
    		}
    		
    		#lista-criterios ul li
    		{
    			font-weight: bold;
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
		        	<div id="tarifasocial" class="serv-int" style="width:880px;">	
	    	    			
							<p>&nbsp;</p>
						
	        				<h3>
								Tarifa social<span>&nbsp;</span>
							</h3>
						
							<br />
							<br />						
							<em>Cadastramento na tarifa social</em>
							<br />
							<br />	
							
							<p style="text-align:justify;">
								O governo do estado, conjuntamente com a Compesa, instituiu em novembro de 2003 a TARIFA SOCIAL com o objetivo de asistir as famílias de baixa renda.
								O cliente que se enquadrar e se cadastrar nos critérios e condições da Tarifa Social será beneficiado com um subsídio de mai de 78% sobre o valor da Tarifa Mínima de água que é de R$ 24,52(vinte e quatro reais e cinquenta e dois centavos), passando a pagar R$ 5,25(cinco reais e vinte e cinco centavos) a partir do Extrato de Decisão - ARPE - DOE - 19/06/2010.
							</p>
							
							<p>&nbsp;</p>						
							<em>Critérios:</em>
							<br />
							<br />	
							
							<p style="text-align:justify;">
								Terá direito ao benefício da Tarifa Social o cliente que seja morador de imóvel abastecido pela Compesa, cadastrado na categoria Residencial não medido ou medido que apresente nos últimos 6(seis) meses, para cada economia, consumo médio de água -  de até 10m³/mês(dez metros cúbicos mês) e consumo médio de energia elétrica - na categoria residencial monofásico - de até 80 kwh/mês(oitenta quilowatts hora mês) e que também se enquadre em um dos critério abaixo estabelecidos:
							</p>
							<br />
							<div id="lista-criterios" style="background-color: #e9e9e9;">
								<ul>
									<li>
										<p style="text-align: justify;" ><span>Seja Beneficiário de Programa de Proteção Social do Governo Federal, descritos a seguir: Bolsa Familia, Programa de Erradicação do Trabalho Infantil-PETI, Beneficio de Prestação Continuada(Amparo Assistencial ao idoso e ao Deficiente) e Seguro Desemprego.</span></p>
										<p style="text-align: justify;" class="paragrafo"><span>Cliente beneficiado com  Seguro Desemprego deverá estar recebendo o valor de 1(um) salario mínimo vigente, sendo o beneficio da Tarifa Social concedido pelo período máximo de 5 (cinco) meses.</span></p>
										<br />
									</li>
									<li>
										<p style="text-align: justify;" class="paragrafo"><span>Tenha Renda Familiar Mensal Comprovada de até 1 (um) salário mínimo vigente.</span></p>
										<p style="text-align: justify;" class="paragrafo"><span>Entende-se por Renda Familiar Mensal Comprovada o somatório dos rendimentos de todos os moradores do imóvel advindos de salários e vantagens (exeto Salário-Família), pensões, aposentadorias, beneficios e outros.</span></p>
										<br />
									</li>
									<li>
										<p style="text-align: justify;"><span>Tenha Renda Familiar Mensal Declarada de até 1 (um) salário mínimo vigente e seja morador de imóvel com área construida de até 60m² (sessenta metros quadrados).</span></p>
									</li>
								</ul>								
							</div>
							<br />
							<p style="text-align:justify;">
								Entende-se por Renda Familiar Mensal Declarada o somatório dos recebimentos de todos os moradores do imóvel advindos de rendimentos de autônomos, prestação ou vendas de bens e serviços, alugueis e outros.
							</p>
							
							<p>&nbsp;</p>						
							<em>Condições</em>
							<br>&nbsp;</br>
							
							<ul class="lista-condicoes">
								<li><b>Para Cadastramento</b>
									<br />
									<br />
									<div id="lista" style="background-color: #e9e9e9;">
										<!-- Substituido por lista de critérios -->
										<ul style="" >
											<li>
												<span>O imóvel deverá estar na situação "Ligado", "Cortado" ou "Suprimido" de Água;</span>
												<br />
												<br />
											</li>
											<li>
												<span>O cliente inadimplente que se enquadrar no critérios da Tarifa Social, terá direito ao beneficio desde que se comprometa a liquidar ou negociar o débito, mediante Carta Cobrança, que a Compesa enviará ao seu imóvel;</span>
												<br />
												<br />
											</li>
											<li>
												<span>O débito de fatura do período não prescricional será convertido retroativamente para o valor da Tarifa Social da época.</span>
												<br />
												<br />
											</li>
											<li>
												<span>As multas, juros e correções do débito convertido seram cancelados.</span>
												<br />
												<br />
											</li>
											<li>
												<span>O cliente deverá apresentar original e cópia do Cadastro de Pessoa Fisica - CF, Carteira de Identidade, conta da Compesa, conta de Energia Elétrica e demais documentos atualizados, conforme se enquadre.</span>
												<br />
											</li>
										</ul>
										
									</div>
									<br />
									<br />
									<p style="text-align: justify;" >
										<span>Caso o solicitante do benefício não seja proprietario do imóvel, será obrigatório anexar ao formulário de cadastramento cópia do CPF e Carteira de Identidade do proprietário;</span>
									</p>
									<br />
									<br />
									<p style="color: #808080;"><b>Sendo Beneficiário de Programa de Proteção Social do Governo Federal:</b></p>
									<br />
									<ul>
										<li><span>Cartão de Programa Social do Governo Federal.</span></li>
										<li><span>Comprovante de Pagamento do Benefício Social.</span></li>
									</ul>
									<br />
									<br />
									<p style="color: #808080;"><b>Tendo Renda Familiar Mensal Comprovada:</b></p>
									<br />
									<ul>
										<li><span>Recibo de Pagamento e Carteira profissional ou</span></li>
										<li><span>Contra-Cheque ou</span></li>
										<li><span>Demonstrativo de Pagamento.</span></li>
									</ul>
									<br />
									<br />
									<p style="color: #808080;"><b>Tendo Renda Familiar Mensal Declarada:</b></p>
									<br />
									<ul>
										<li><span>Imposto Predial e Territorial Urbano - IPTU ou</span></li>
										<li><span>Escritura com área construida do imóvel e</span></li>
										<li><span>Declarar Renda Familiar no Formulário "Tarifa Social-Cadastramento".</span></li>
									</ul>
									<br />
									<br />
								</li>
								
								<li><b>Para Implantação</b>
								<br />
								<br />
								
								<p style="text-align:justify;">
									<span>
										A implantação do cliente na Tarifa Social estará condicionada a análise e aprovação do cadastro pela Compesa.
										<br />
										<br />
										Os Clientes residentes em imóvel com mais de uma economia estarão condicionados à aprovação de todos os cadastros do referido imóvel.
									</span>
								</p>
								
								</li>
							</ul>
							
							
							
							<p align="left">&nbsp;</p>
							<p align="left">&nbsp;</p>
					
							<font><span style="font-size: 11px;">Fonte:COMPESA/DAC/01-10-10 (RD 11/2008)</span></font>
						
							<p align="left">&nbsp;</p>
							<p align="left">&nbsp;</p>
								
							<div id="atualizacao" style="background-image: url(/gsan/imagens/portal/general/ultima_atualizacao.png);background-repeat: no-repeat;">
									<span style="position: absolute; padding-top: 7px;"> Última atualização (segunda, 4 de Outubro de 2010)</span>
							</div>							
							             
		       	</div><!-- Content - End -->
	       </div>
	    	 <%@ include file="/jsp/portal/rodape.jsp"%>
	  	</div><!-- Container - End -->       
	</body>
</html:html>