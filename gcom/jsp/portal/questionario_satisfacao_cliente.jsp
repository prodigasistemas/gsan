<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN">

<html:html>
	<head>
		<title>Compesa</title>
		<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
		<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
		<link rel="stylesheet" href="<bean:message key="caminho.portal.css"/>style.css" type="text/css">
		<script language="JavaScript" src="<bean:message key="caminho.portal.js"/>jquery-1.4.2.min.js"></script>
		<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
		
		<link rel="stylesheet" href="<bean:message key="caminho.css"/>jquery.realperson.css" type="text/css">
		<script type="text/javascript" src="<bean:message key="caminho.js"/>jquery.realperson.js"></script>
		
		<script type="text/javascript">
		function getSelectedRadio(buttonGroup) {
		   if (buttonGroup[0]) { 
		      for (var i=0; i<buttonGroup.length; i++) {
		         if (buttonGroup[i].checked) {
		            return i
		         }
		      }
		   } else {
		      if (buttonGroup.checked) { return 0; }
		   }
		   return -1;
		} 
			function enviarQuestionario(){
				var form = document.forms[0];

				var validar = true;

				var comentarioBemAtendido = form.comentarioBemAtendido.value;
				var comentarioSolicitacaoResolvida = form.comentarioSolicitacaoResolvida.value;
				var comentarioContatoEquipeCampo = form.comentarioContatoEquipeCampo.value;
				var comentarioGeral = form.comentarioGeral.value;

				if(getSelectedRadio(form.indicadorBemAtendido) == -1){
					validar = false;
					alert("Selcione uma opção para a primeira pergunta!");
					
				}else if(getSelectedRadio(form.indicadorSolicitacaoResolvida) == -1){
					validar = false;
					alert("Selcione uma opção para a segunda pergunta!");
				}else if(getSelectedRadio(form.indicadorContatoEquipeCampo) == -1){
					validar = false;
					alert("Selcione uma opção para a terceira pergunta!");
				}else if(getSelectedRadio(form.nota) == -1){
					validar = false;
					alert("Informe qual nota você daria ao atendimento!");
				}else if(comentarioBemAtendido.length > 400 ){
					validar = false;
					alert("Diminua a quantidade de caracters do comentário da primeira pergunta!");
				}else if(comentarioSolicitacaoResolvida.length > 400 ){
					validar = false;
					alert("Diminua a quantidade de caracters do comentário da segunda pergunta!");
				}else if(comentarioContatoEquipeCampo.length > 400 ){
					validar = false;
					alert("Diminua a quantidade de caracters do comentário da terceira pergunta!");
				}else if(comentarioGeral.length > 400 ){
					validar = false;
					alert("Diminua a quantidade de caracters do último comentário!");
				}

				

				var hash = 5381; 
		        var valorBrowser = form.defaultReal.value; 
		        valorBrowser = valorBrowser.toUpperCase();

		        for(var i = 0; i < valorBrowser.length; i++) { 
			        var hash = ((hash << 5) + hash);
		        	hash = hash + valorBrowser.charCodeAt(i);
		        } 
				
		       if(validar && hash != form.defaultRealHash.value){
		    	   validar = false;
				   alert("Caracteres informodas inválidos!");
			   }

			   if(validar == true){
			    	form.submit();
			   }				


			}

			function sairQuestionario(){
				var w = window.open();
				w.close();
			}
		
		</script>
		 <script language="JavaScript">
		 $(document).ready(function(){
				$('#defaultReal').realperson({regenerate: 'Mudar Imagem'}); 
		  });
		</script>
	</head>
	
	<body>
	<div id="container">
	<html:form action="/questionarioSatisfacaoAction.do"
		name="QuestionarioSatisfacaoActionForm" 
		type="gcom.gui.portal.QuestionarioSatisfacaoActionForm"
		method="post">
		
	    	<%@ include file="/jsp/portal/cabecalho_questionario_satisfacao.jsp"%>
	    	
	         <div id="barra-servicos" style="margin-bottom: 20px;">
					<h2>Serviços</h2>
				    <h4 style="color: #006BBB; right: 250px; font-size: 16px; font-weight: bold;">Pesquisa de Satisfação do Cliente</h4>
				    <a href="javascript:;" onclick="sairQuestionario()" title="Sair"><img src="/gsan/imagens/portal/general/btn-sair.png" alt="Sair" /></a>
			</div>
	        <c:choose>
	        	<c:when test="${ operacao == 'exibirQuestionario' }">
	        		<div style="clear: both; margin: 0 0 0 38px; border: 1px solid #DEDEDE; border-radius: 5px; -moz-border-radius: 7x; width: 865px;">
	        			<table>
	        				<tr>
	        					<td>
	        						<b>Solicitante:</b>
	        					</td>
	        					<td>
	        						 <c:out value="${nomeSolicitante }"></c:out>
	        					</td>
	        				</tr>
	        				<tr>
	        					<td>
	        						<b>Local do Serviço:</b>
	        					</td>
	        					<td>
	        						<c:out value="${localServico }"></c:out>
	        					</td>
	        				</tr>
	        				<tr>
	        					<td>
	        						<b>Tipo de Serviço:</b>
	        					</td>
	        					<td>
	        						<c:out value="${tipoServico }"></c:out>
	        					</td>
	        				</tr>
	        				<tr>
	        					<td>
	        						<b>Data de Conclusão:</b>
	        					</td>
	        					<td>
	        						<c:out value="${dataConclusao }"></c:out>
	        					</td>
	        				</tr>
	        			</table>
	        		</div>
	        		<input type="hidden" name="idRegistroAtendimentoEncerrado" value="${idRegistroAtendimentoEncerrado}" />
			        <br />
	        		<div style="clear: both; padding-left: 40px; padding-right: 40px;" align="left">
				        <b>Você foi bem atendido quando entrou em contato conosco para relatar o problema?</b>
				        <br />
				        <br />
				        <div style="float: left;">
				        	<input type="radio" name="indicadorBemAtendido" value="1" /> <b>Sim</b>
				        	<br />
				        	<input type="radio" name="indicadorBemAtendido" value="2" /> <b>Não</b>
				        </div>
				        <div style="margin-left: 100px;">
				        	Comentário<br />
				        	<textarea rows="10" cols="50" name="comentarioBemAtendido"
				        	onkeyup="limitTextArea(document.forms[0].comentarioBemAtendido, 400, document.getElementById('comentarioBemAtendidoUtilizado'), document.getElementById('comentarioBemAtendidoLimite'));" ></textarea>
				        	<br />
				        	<span id="comentarioBemAtendidoUtilizado" style="display: none"></span>
				        	Restam <span id="comentarioBemAtendidoLimite">400</span> caracteres
				        </div>
				        
				        <br />
				        <br />
				        
				        <b>Sua solicitação foi resolvida por completo?</b>
				        <br />
				        <br />
				        <div style="float: left;">
				        	<input type="radio" name="indicadorSolicitacaoResolvida" value="1" /> <b>Sim</b>
				        	<br />
				        	<input type="radio" name="indicadorSolicitacaoResolvida" value="2" /> <b>Não</b>
				        </div>
				        <div style="margin-left: 100px;">
				        	Comentário<br />
				        	<textarea rows="10" cols="50" name="comentarioSolicitacaoResolvida"
				        	onkeyup="limitTextArea(document.forms[0].comentarioSolicitacaoResolvida, 400, document.getElementById('comentarioSolicitacaoResolvidaUtilizado'), document.getElementById('comentarioSolicitacaoResolvidaLimite'));"></textarea>
							<br />
				        	<span id="comentarioSolicitacaoResolvidaUtilizado" style="display: none"></span>
				        	Restam <span id="comentarioSolicitacaoResolvidaLimite">400</span> caracteres
				        </div>
				        
				        <br />
				        <br />
				        
				        <b>Caso tenha entrado em contato com a equipe de campo, foi bem atendido?</b>
				        <br />
				        <br />
				        <div style="float: left;">
				        	<input type="radio" name="indicadorContatoEquipeCampo" value="1" /> <b>Sim</b>
				        	<br />
				        	<input type="radio" name="indicadorContatoEquipeCampo" value="2" /> <b>Não</b>
				        	<br />
				        	<input type="radio" name="indicadorContatoEquipeCampo" value="3" /> <b>Não Houve</b>
				        		<br />
				        		 <b style="margin-left: 20px;">Contato</b>
				        </div>
				        <div style="margin-left: 100px;">
				        	Comentário<br />
				        	<textarea rows="10" cols="50" name="comentarioContatoEquipeCampo"
				        	onkeyup="limitTextArea(document.forms[0].comentarioContatoEquipeCampo, 400, document.getElementById('comentarioContatoEquipeCampoUtilizado'), document.getElementById('comentarioContatoEquipeCampoLimite'));"></textarea>
							<br />
				        	<span id="comentarioContatoEquipeCampoUtilizado" style="display: none"></span>
				        	Restam <span id="comentarioContatoEquipeCampoLimite">400</span> caracteres
				        </div>
				        
				        <br />
				        <br />
				        <b>Qual nota você daria quanto a qualidade do serviço executado?</b>
				        <br />
				        <br />
				        <table>
			      		    <tr>
				        		<td>
						        	<input type="radio" name="nota" value="1" /> 
				        		</td>
				        		<td>
						        	<input type="radio" name="nota" value="2" /> 
				        		</td>
				        		<td>
						        	<input type="radio" name="nota" value="3" /> 
				        		</td>
				        		<td>
						        	<input type="radio" name="nota" value="4" /> 
				        		</td>
				        		<td>
						        	<input type="radio" name="nota" value="5" /> 
				        		</td>
				        		<td>
						        	<input type="radio" name="nota" value="6" /> 
				        		</td>
				        		<td>
						        	<input type="radio" name="nota" value="7" /> 
				        		</td>
				        		<td>
						        	<input type="radio" name="nota" value="8" /> 
				        		</td>
				        		<td>
						        	<input type="radio" name="nota" value="9" /> 
				        		</td>
				        		<td>
						        	<input type="radio" name="nota" value="10" /> 
				        		</td>
				        	</tr>
				        	<tr>
				        		<td>
				        			<b>1</b>
				        		</td>
				        		<td>
				        			<b>2</b>
				        		</td>
				        		<td>
				        			<b>3</b>
				        		</td>
				        		<td>
				        			<b>4</b>
				        		</td>
				        		<td>
				        			<b>5</b>
				        		</td>
				        		<td>
				        			<b>6</b>
				        		</td>
				        		<td>
				        			<b>7</b>
				        		</td>
				        		<td>
				        			<b>8</b>
				        		</td>
				        		<td>
				        			<b>9</b>
				        		</td>
				        		<td>
				        			<b>10</b>
				        		</td>
				           </tr>
				        </table>
				       
				       	<br />
				        <br />
				        
				        <b>Caso tenha algum comentário que julgue importante relatar, utilize o espaço abaixo?</b>
				        <br />
				        <br />
				        <div>
				        	Comentário<br />
				        	<textarea rows="10" cols="65" name="comentarioGeral"
				        	onkeyup="limitTextArea(document.forms[0].comentarioGeral, 400, document.getElementById('comentarioGeralUtilizado'), document.getElementById('comentarioGeralLimite'));"></textarea>
							<br />
				        	<span id="comentarioGeralUtilizado" style="display: none"></span>
				        	Restam <span id="comentarioGeralLimite">400</span> caracteres
				        </div>
				        <br />
				        <br />
				        <b>Informe os caracteres da imagem abaixo no espaço ao lado.</b>	
				        <br />	
				        <br />	
				        	<div align="left" style="width: 400px;">
					       		 <input id="defaultReal" name="defaultReal" />
				        	</div>
				        <br />
				        <br />
				        <a href="#" onclick="enviarQuestionario();" title="Enviar"><img src="<bean:message key="caminho.portal.imagens"/>general/btn-enviar.png" alt="Enviar" /></a>
			       </div>
	        	</c:when>
	        	<c:when test="${ operacao == 'RAnaoEncontrada' }">
	        		<br />
			        <br />
	        		<div align="center" style="clear: both; padding: 20px;">
	        			<img src="<bean:message key="caminho.imagens"/>alerta.gif" alt="Enviar" align="middle" />
		        		<b style="color: #006BBB;"> 
		        			Registro de Atendimento não encontrado ou ainda não encerrado.
		        		</b>
	        		</div>
	        	</c:when>
	        	<c:when test="${ operacao == 'sucesso' }">
	        		<br />
			        <br />
	        		<div align="center" style="clear: both; padding: 20px;">
	        			<b style="color: #006BBB;">
		        			Pesquisa de Satisfação registrada com sucesso. A COMPESA agradece a sua colaboração para melhor atendê-lo
		        		</b>
	        		</div>
	        	</c:when>
	        	<c:otherwise>
	        		<br />
			        <br />
	        		<div align="center" style="clear: both; padding: 20px;">
	        			<img src="<bean:message key="caminho.imagens"/>alerta.gif" alt="Enviar" align="middle" />
		        		<b style="color: #006BBB;">
		        			Você já respondeu esta pesquisa. A COMPESA agradece sua participação.
		        		</b>
	        		</div>
	        	</c:otherwise>
	        </c:choose>
	  </html:form>  
    </div>
	</body>
</html:html>