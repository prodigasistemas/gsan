<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>

<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">

</head>

<script language="JavaScript">
				
	function pesquisar() {
	    var form = document.forms[0];
	    form.verTudo.value = '';
	    form.submit();
	}

	function anterior(campo) {
	    var form = document.forms[0];
	    form.verTudo.value = '';
	    form.inicio.value = campo;
	    form.submit();				
	}

	function proximo(campo) {
	    var form = document.forms[0];
	    form.verTudo.value = '';
	    form.inicio.value = campo;
	    form.submit();				
	}
	
	function exibirTudo() {
	    var form = document.forms[0];
	    form.verTudo.value = 'true';
	    form.submit();				
	}
	
	function voltar(){
		
		var form = document.forms[0];
    	
    	form.action='exibirLogTelaInicialAction.do';
		form.submit();
	
	}


</script>

<body leftmargin="5" topmargin="5">

<html:form action="/exibirLogTelaFinalAction.do"
	name="ExibirLogActionForm"
	type="gcom.gui.util.log.ExibirLogActionForm"
	method="post">

	<input type="hidden" name="inicio" value="">
	<input type="hidden" name="verTudo" value="">
	
	
<%@ include file="/jsp/util/cabecalho.jsp"%>

	<table width="770" border="0" cellspacing="5" cellpadding="0">
  		<tr>
    		<td width="770" valign="top" class="centercoltext">
      			
      			<table height="100%">
			        <tr>
			          <td></td>
			        </tr>
      			</table>
      			
		      	<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
		        	<tr>
		          		<td width="11">
		          			<img border="0" 
		          				src="<bean:message key="caminho.imagens"/>parahead_left.gif"/>
		          		</td>
		          		<td class="parabg">Exibição do LOG - Pagina Atual <b><bean:write name="ExibirLogActionForm" property="paginaAtual"/> </b></td>
		          		<td width="11">
		          			<img border="0" 
		          				src="<bean:message key="caminho.imagens"/>parahead_right.gif"/>
		          		</td>
		        	</tr>
		      	</table>
			   	
			   	<table width="100%" border="0">
			        
			        <tr>
						<bean:write name="ExibirLogActionForm" 
							property="textoErro"
							filter="false"/>
			        </tr>
			        <tr>
			        	<td width="100" align="left">
				        	<logic:notEmpty name="ExibirLogActionForm" property="inicio">
					        	
								<input name="Button" 
									type="button"
									class="bottonRightCol" 
									value="Primeira" 
									onclick="anterior(0);">
									
								<input name="Button" 
									type="button"
									class="bottonRightCol" 
									value="Anterior" 
									onclick="anterior(<bean:write name="ExibirLogActionForm" property="anterior"/>);">			        
									
				        	</logic:notEmpty>

							<input name="Button" 
								type="button"
								class="bottonRightCol" 
								value="Proxima" 
								onclick="proximo(<bean:write name="ExibirLogActionForm" property="proximo"/>);">
							
							<input name="Button" 
								type="button"
								class="bottonRightCol" 
								value="Ultima" 
								onclick="proximo(<bean:write name="ExibirLogActionForm" property="ultima"/>);">												        
						</td>
			        </tr>

			        <tr>
			        	<td width="100" align="left">
							Pagina: <input type="text" 
								name="numeroPagina" 
								size="5" 
								maxlength="4">
							
							<input name="Button" 
								type="button"
								class="bottonRightCol" 
								value="Pesquisar" 
								onClick="javascript:pesquisar();">
			        	</td>
			        </tr>
			        
			        <tr>
			        	<td width="100" align="left">
							Pesquisar Por Palavra: <input type="text" 
								name="palavra" 
								size="35" 
								maxlength="30">
							
							<input name="Button" 
								type="button"
								class="bottonRightCol" 
								value="Pesquisar Por Palavra" 
								onClick="javascript:pesquisar();">
			        	</td>
			        </tr>
			        
			        
			        <tr>
			          	<td width="60" align="left">
							<input name="Button" 
								type="button"
								class="bottonRightCol" 
								value="Voltar" 
								onclick="voltar();">			          	
			          	</td>
			          
			          	<td align="right">
	  		      			<input name="Button" 
								type="button"
								class="bottonRightCol" 
								value="Exibir" 
								onclick="validaForm();">
			  	      	</td>
			        </tr>
				</table>
			   	
			   	<p>&nbsp;</p>
			    <p>&nbsp;</p>
			    
    		</td>
  		</tr>
	</table>

	<%@ include file="/jsp/util/rodape.jsp"%>
</html:form>
</body>
</html:html>