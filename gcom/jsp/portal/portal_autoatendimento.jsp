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
		
		<!--<logic:present name="imovelInvalido" scope="request">
			<script type="text/javascript">
				$(document).ready(function(){
					$('#link-3 a').focus();
					$('#matricula').focus();
				});
			</script>
		</logic:present>-->
		
		<style type="text/css">
			.lista-canais li {
				list-style: url("/gsan/imagens/portal/general/marcador.gif");
				margin: 0 0 0 15px;
				padding: 0px;
			}
			
			li em {
    			color: #008FD6;
			}
			em {
    			font-style: normal;
   				font-weight: 700;
    			padding-right: 5px;
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
		        	<div id="teleatend" class="serv-int" style="width:880px;">	
	    	    			<a class="adobe-reader" target="_blank" title="Faça o download do Adobe Reader" href="http://get.adobe.com/br/reader/">
								<img alt="Download do Adobe Reader" src="/gsan/imagens/portal/general/adobe-reader.gif">
							</a>
							<a class="btn-voltar" title="Voltar" href="exibirServicosPortalCompesaAction.do">
								<img alt="Voltar" src="/gsan/imagens/portal/general/btn-voltar.gif">
							</a>
							<p>&nbsp;</p>
						
	        				<h3>
								Auto-atendimento<span>&nbsp;</span>
							</h3>
						
							<br>&nbsp;</br>	
							<p>&nbsp;</p>						
							Nos equipamentos do auto-atendimento o cliente pode obter os seguintes serviços:
							<br>&nbsp;</br>	
							<ul class="lista-canais">
								<li>Segunda via de conta;</li>
								<li>Extrato de débito;</li>
								<li>Dados do imóvel;</li>
								<li>Consulta a Registros de Serviços(RS);</li>
							</ul>
							<br />
							Estes serviços só estarão disponibilizados se o cliente estiver com seu cadastro atualizado, CPF e matrícula do imóvel.
							<p align="left">&nbsp;</p>
							<p align="left">&nbsp;</p>
							<p align="left">&nbsp;</p>
								
							<div id="atualizacao" style="background-image: url(/gsan/imagens/portal/general/ultima_atualizacao.png);background-repeat: no-repeat;">
									<span style="position: absolute; padding-top: 7px;"> Última atualização (segunda, 26 de Janeiro de 2009)</span>
							</div>
							
							<p align="left">&nbsp;</p>
							<p align="left">&nbsp;</p>
							<p align="left">&nbsp;</p>
							<p align="left">&nbsp;</p>
							<p align="left">&nbsp;</p>
							<p align="left">&nbsp;</p>
							<p align="left">&nbsp;</p>
							<p align="left">&nbsp;</p>
							<p align="left">&nbsp;</p>
							<p align="left">&nbsp;</p>
							<p align="left">&nbsp;</p>
							<p align="left">&nbsp;</p>
							<p align="left">&nbsp;</p>
							<p align="left">&nbsp;</p>
							<p align="left">&nbsp;</p>
							<p align="left">&nbsp;</p>             
		       	</div><!-- Content - End -->
	       </div>
	    	 <%@ include file="/jsp/portal/rodape.jsp"%>
	  	</div><!-- Container - End -->       
	</body>
</html:html>