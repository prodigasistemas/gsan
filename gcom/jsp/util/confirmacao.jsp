<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%@page isELIgnored="false" import="gcom.gui.ActionServletException,gcom.util.ControladorException"%>

<html:html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js" ></script>
</head>

<body leftmargin="5" topmargin="5">
<div id="formDiv">
    <form method="post">
    <logic:present name="reexibirCritica">
    	<html:hidden name="cancelarValidacao" property="cancelarValidacao" value="${sessionScope.reexibirCritica}"/>
    </logic:present>
     <logic:notPresent name="reexibirCritica">
    	<html:hidden name="cancelarValidacao" property="cancelarValidacao" value="true"/>
    </logic:notPresent>
	<table width="100%" border="0" cellspacing="5" cellpadding="0">
 		<tr>
	   		<td width="100%" valign="top" class="centercoltext">
		 	
		 	<table height="100%">
		   		<tr>
			 		<td></td>
		   		</tr>
		 	</table>
		 	
		 	<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
				<tr>
					<td width="17">
						<img border="0"	src="<bean:message key="caminho.imagens"/>parahead_left.gif" /></td>
				 	<td width="100%" class="parabg">Confirmação</td>
				 	<td width="3%" class="parabg">
				   		<html:link href="javascript:window.close();">
					 		<img border="0" src="<bean:message key="caminho.imagens"/>close.gif" alt="Fechar" />
				   		</html:link>
				 	</td>
				 	<td width="4%">
				   		<img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif" />
				 	</td>
			   	</tr>
			</table>
			
			<table width="100%" border="0">
				<tr>
					<td width="6%" align="left">
				   		<img src="<bean:message key="caminho.imagens"/>alerta.gif" />
				 	</td>
				 	<td>
				   		<span style="font-size:12px;font-weight: bold;">

				   		<%	String tipoRelatorio = (String) request.getAttribute("tipoRelatorio");
				   			String[] parametros = (String[]) request.getAttribute("parametrosMensagem");
							
				   			if (parametros == null) {	%> 
								<bean:message key="${requestScope.chaveMensagem}" /><br>
						<%	} else {
								int tamanhoColecao = parametros.length;	%> 
								<bean:message key="${requestScope.chaveMensagem}"
							
								arg0="<%=(tamanhoColecao - 1) >= 0 ? parametros[0] : "" %>"
								arg1="<%=(tamanhoColecao - 1) >= 1 ? parametros[1] : "" %>"
								arg2="<%=(tamanhoColecao - 1) >= 2 ? parametros[2] : "" %>"
								arg3="<%=(tamanhoColecao - 1) >= 3 ? parametros[3] : "" %>"
								arg4="<%=(tamanhoColecao - 1) >= 4 ? parametros[4] : "" %>" /><br>
						<%	}	%>

				   		</span>
				 	</td>
			   	</tr>
	   			
	   			<tr>
		 			<td colspan="2" align="center">
						<c:if test="${empty requestScope.confirmacaoNormal}" var="confirmacaoNormal">
					   		<logic:present name="nomeBotao1">
					     		<input type="button"
					          		class="bottonRightCol" 
					          		name="confirmar" 
					          		value="${requestScope.nomeBotao1}"
							  		onclick="javascript:window.location.href='${sessionScope.statusWizard.caminhoActionPrincipalWizard}.do?action=${requestScope.caminhoConfirmacao}&destino=${param.destino}&confirmado=ok&confirmacaoDupla=${requestScope.confirmacaoDupla}'" />
							</logic:present>
							
							<logic:notPresent name="nomeBotao1">	  
					     		<input type="button"
					          		class="bottonRightCol" 
					          		name="confirmar" 
					          		value="Confirmar"
							 	 	onclick="javascript:window.location.href='${sessionScope.statusWizard.caminhoActionPrincipalWizard}.do?action=${requestScope.caminhoConfirmacao}&destino=${param.destino}&confirmado=ok&confirmacaoDupla=${requestScope.confirmacaoDupla}'" />
							</logic:notPresent>	
							
							<logic:present name="nomeBotao3">	  
					     		<input type="button"
					          		class="bottonRightCol" 
					          		name="confirmar" 
					          		value="${requestScope.nomeBotao3}"
							 	 	onclick="javascript:window.location.href='${sessionScope.statusWizard.caminhoActionPrincipalWizard}.do?action=${requestScope.caminhoConfirmacao}&destino=${param.destino}&confirmado=nao'" />
							</logic:present>	    
						</c:if>
						
						<c:if test="${!confirmacaoNormal}">
							<logic:present name="nomeBotao1">
						   		<input type="button"
						          	class="bottonRightCol" 
						          	name="confirmar" 
						          	value="${requestScope.nomeBotao1}"
								  	onclick="javascript:botaoAvancarTelaEspera('${requestScope.caminhoConfirmacao}?confirmado=ok&tipoRelatorio=<%=tipoRelatorio%>');" />
							</logic:present>		  
							
							<logic:notPresent name="nomeBotao1">
						   		<input type="button"
						          	class="bottonRightCol" 
						          	name="confirmar" 
						          	value="Confirmar"
								  	onclick="javascript:botaoAvancarTelaEspera('${requestScope.caminhoConfirmacao}?confirmado=ok&tipoRelatorio=<%=tipoRelatorio%>');" />
							</logic:notPresent>
							
							<logic:present name="nomeBotao3">
						   		<input type="button"
						          	class="bottonRightCol" 
						          	name="confirmar" 
						          	value="${requestScope.nomeBotao3}"
								  	onclick="javascript:botaoAvancarTelaEspera('${requestScope.caminhoConfirmacao}?confirmado=nao&tipoRelatorio=<%=tipoRelatorio%>');" />
							</logic:present>								  
						</c:if>		  
				  		&nbsp;&nbsp;&nbsp;&nbsp;
						
						<logic:present name="cancelamento">
							<logic:present name="nomeBotao2">
								<input type="button"
						          	class="bottonRightCol" 
						          	name="cancelar" 
						          	value="${requestScope.nomeBotao2}"
								  	onclick="javascript:botaoAvancarTelaEspera('${requestScope.caminhoConfirmacao}?confirmado=cancelar&tipoRelatorio=<%=tipoRelatorio%>');" />
							</logic:present>
			 				
			 				<logic:notPresent name="nomeBotao2">
								<input type="button"
						          	class="bottonRightCol" 
						          	name="cancelar" 
						          	value="Cancelar"
							  		onclick="javascript:botaoAvancarTelaEspera('${requestScope.caminhoConfirmacao}?confirmado=cancelar&tipoRelatorio=<%=tipoRelatorio%>');" />
				 			</logic:notPresent>
						</logic:present>
						<logic:notPresent name="nomeBotao3">
							<logic:notPresent name="cancelamento">		  
						     	<input type="button" 
						     		class="bottonRightCol"
								  	name="voltar"
								  	value="Voltar" 
								  	onclick="javascript:history.back()" />
					   		</logic:notPresent>
				   		</logic:notPresent>
		 			</td>
	   			</tr>
	   			
	   			<tr>
		 			<td colspan="2">&nbsp;</td>
	   			</tr>
	 		</table>
		 	
		 	<p>&nbsp;</p>
			<p>&nbsp;</p>
   			
   			</td>
  		</tr>
	</table>
	</form>
</div>	
	
<%@ include file="/jsp/util/telaespera.jsp"%>
	
</body>
</html:html>