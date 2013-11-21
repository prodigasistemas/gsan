<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN">

<html>
	<head>
		<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<script type="text/javascript">
			$(document).ready(function(){
				var matricula = $('#matricula').html();
				var length = matricula.length;
				$('#matricula').html(matricula.substr(0, length - 1) + '.' + matricula.substr(length - 1, 1)); 
			});
		</script>
	</head>
	<body>
		<div id="barra-servicos">
			<h2>Serviços</h2>
		    <h3>Bem-vindo(a) ${ExibirServicosPortalCompesaActionForm.nomeUsuario}</h3>
		    <h4><label style="font-size: 13px;">Matrícula: </label><span id="matricula">${ExibirServicosPortalCompesaActionForm.matricula}</span></h4>
		    <a href="exibirServicosPortalCompesaAction.do?menu=sim" title="Sair"><img src="/gsan/imagens/portal/general/btn-sair.png" alt="Sair" /></a>
		</div>
		<!-- Botão download Adobe Reader - Start -->
		<a href="http://get.adobe.com/br/reader/" title="Faça o download do Adobe Reader" class="adobe-reader" target="_blank"><img src="/gsan/imagens/portal/general/adobe-reader.gif" alt="Download do Adobe Reader" /></a>
		<!-- Botão download Adobe Reader - End -->
		<logic:present scope="request" name="voltarServicos">
			<div id="seg-via-conta" class="serv-int" style="margin: 0px;">
				<a href="exibirServicosPortalCompesaAction.do?method=voltarServico" title="Voltar e selecionar outro serviço" class="btn-voltar-servicos">
			    	<img src="/gsan/imagens/portal/general/btn-voltar-servicos.gif" alt="Voltar e selecionar outro serviço" />
			    </a>
			</div>
		</logic:present>
	</body>
</html>