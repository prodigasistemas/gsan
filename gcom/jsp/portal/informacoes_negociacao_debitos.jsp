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
					
			.lista li {
				list-style: url("/gsan/imagens/portal/general/marcador.gif");
				margin: 0 0 0 15px;
				padding: 0px;
			}
			
			em {
    			color: #008FD6;
    			font-style: normal;
   				font-weight: 700;
    			padding-right: 5px;
			}
			font {
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
    		p{
    		text-align: justify;
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
    					
		</style>
	</head>
	
	<body>
		<div id="container">
	    	<%@ include file="/jsp/portal/cabecalho.jsp"%>
	        
	        <!-- Content - Start -->
		        <div id="content">
		        <%@ include file="/jsp/portal/cabecalhoInformacoes.jsp"%>
		        	<div id="negociadebitos" class="serv-int" style="width:880px;">	
	    	    			
							<p>&nbsp;</p>
						
	        				<h3>
								Negociação de Débitos<span>&nbsp;</span>
							</h3>
							<br />
							<br />
							<p>Parcelamento de débito</p>
							<p class="paragrafo"><em><b>Condomínio:</b></em></p>
							<p>
								O solicitante deve ser o síndico ou representante legal do condomínio e ter, no mínimo, 18 anos.
							</p>
							<p class="paragrafo">
								<em>Quais os documentos necessários?</em>
							</p>
							<ul class="lista">
								<li>RG, CPF e telefone de contato do síndico;</li>
								<li>Conta de serviços de água e/ou esgotos;</li>
								<li>Cópia do cartão do CNPJ do condomínio, se houver;</li>
								<li>Cópia da ata de eleição do síndico com registro em cartório ou o Contrato Social da Administradora;</li>
								<li>Quando solicitado por representante legal, apresentar procuração original (com firma reconhecida ou com a apresentação do RG (original) de quem a assinou).</li>
								<li>Para condomínios sem CNPJ ou sem síndicos preencher o termo autorização de parcelamento.</li>
							</ul>
							
							<p class="paragrafo">
								As cópias devem ser acompanhadas do original e serão retidas para arquivo da Compesa.
								No momento da solicitação ocorrerá o preenchimento e assinatura do Termo de Acordo para Parcelamento de Débito.								
							</p>
							<br />
							<p><em><b>Residencial</b></em></p>
							<p class="paragrafo">
							<p class="paragrafo"><em><b>Quais os documentos necessários?</b></em></p>
							
							<p><em><b>Se proprietário:</b></em> RG e CPF, telefone para contato.</p>
							<p>&nbsp;</p>
							<p><em><b>Se inquilino:</b></em> RG e CPF, telefone para contato, cópia do contrato de locação com firma reconhecida*
								Observação: a quantidade de parcelas dependerá do período do término do contrato de locação.
							</p>
							<p>&nbsp;</p>
							<p>
							<p><em><b>Se representante: </b></em> RG, CPF, e cópia dos documentos do imóvel, acrescidos de procuração com firma reconhecida (ou com a apresentação do RG original de quem a assinou).
							</p>
							<p>&nbsp;</p>
							<p><em><b>Se emancipado:</b></em> Apresentar um documento que comprove esta condição.
									Conta de serviços de água e/ou esgotos. No momento da solicitação ocorrerá o preenchimento e assinatura do Termo de Acordo para Parcelamento de Débito.
							</p>
							<br />
							<br />
							<p><em><b>Comercial e industrial</b></em></p>
							<p class="paragrafo">
							Cópia do Contrato Social e última alteração contratual;
							<ul class="lista">								
								<li>Cópia do Cartão de CNPJ;</li>
								<li>Representante com procuração com firma reconhecida*.</li>
							</ul>
								<br />
								
								Para os acordos de parcelamento formalizados por representante, serão considerados os dados constantes da procuração para elaboração do termo.
								No momento da solicitação ocorrerá o preenchimento e assinatura do Termo de Acordo para Parcelamento de Débito.
							</p>
							
							<p align="left">&nbsp;</p>
							<p align="left">&nbsp;</p>
					
							<font><span style="font-size: 11px;">Fonte:COMPESA/DAC/01-10-10 (RD 11/2008)</span></font>
						
							<p align="left">&nbsp;</p>
							<p align="left">&nbsp;</p>
							
							<br />
							<br />
							<br />
							<br />
							
							<div id="atualizacao" style="background-image: url(/gsan/imagens/portal/general/ultima_atualizacao.png);background-repeat: no-repeat;">
									<span style="position: absolute; padding-top: 7px;"> Última atualização ( quarta, 15 de junho de 2011 )</span>
							</div>					
							             
		       	</div><!-- negociadebitos - End -->
	       </div><!-- Content - End -->
	    	 <%@ include file="/jsp/portal/rodape.jsp"%>
	  	</div><!-- Container - End -->       
	</body>
</html:html>