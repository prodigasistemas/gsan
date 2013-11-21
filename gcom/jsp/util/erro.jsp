<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%@page isErrorPage="true" isELIgnored="false"
	import="gcom.gui.ActionServletException, gcom.util.FachadaException, java.io.*"%>


<html:html>


<head>

<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>

EstilosCompesa.css"
	type="text/css">

<script type="text/javascript">
<!--
function toggleBox(szDivID, iState) // 1 visible, 0 hidden
{
    if(document.layers)	   //NN4+
    {
       document.layers[szDivID].visibility = iState ? "show" : "hide";
    }
    else if(document.getElementById)	  //gecko(NN6) + IE 5+
    {
        var obj = document.getElementById(szDivID);
        obj.style.visibility = iState ? "visible" : "hidden";
    }
    else if(document.all)	// IE 4
    {
        document.all[szDivID].style.visibility = iState ? "visible" : "hidden";
    }
}
// -->
</script>

<script language="JavaScript">

function verificarVoltar(){

	var botaoBack = document.getElementById('botaoBack');
	var inicioHistorico;
	//caso não possa voltar para nenhum lugar
	  //IE	 
	  if (document.all) {
		inicioHistorico = 0;
	  //Netscape	
      } else {
		inicioHistorico = 1;
      }
	if(window.history.length > inicioHistorico) {
		<%if (exception instanceof ActionServletException) {
			ActionServletException actionServletException = (ActionServletException) exception;
		
			if (actionServletException.getUrlBotaoVoltar() != null && 
				!actionServletException.getUrlBotaoVoltar().equalsIgnoreCase("")){	%>
				
				botaoBack.innerHTML = '<br><input name="botaoVoltar" class="bottonRightCol" value="Voltar" onclick="urlVoltar()" type="button">';
    	<%	}
    		else{	%>
    		
    			botaoBack.innerHTML = '<br><input name="botaoVoltar" class="bottonRightCol" value="Voltar" onclick="javascript:history.back();" type="button">';
    	<%	}		
    	}else{	
    		if (exception instanceof FachadaException) {
    			FachadaException fachadaException = (FachadaException) exception;
    			
    			if (fachadaException.getUrlBotaoVoltar() != null && 
    					!fachadaException.getUrlBotaoVoltar().equalsIgnoreCase("")){	%>
    					botaoBack.innerHTML = '<br><input name="botaoVoltar" class="bottonRightCol" value="Voltar" onclick="urlVoltar()" type="button">';
    	    	<%	}
    	    		else{	%>
    	    			botaoBack.innerHTML = '<br><input name="botaoVoltar" class="bottonRightCol" value="Voltar" onclick="javascript:history.back();" type="button">';
    	    	<%	}
    			
    		}else{
    	%>
    		botaoBack.innerHTML = '<br><input name="botaoVoltar" class="bottonRightCol" value="Voltar" onclick="javascript:history.back();" type="button">';
    	<%	}
    	
    	}%>
    	
		return true; 
	} else {
		botaoBack.innerHTML = '<br><input name="botaoFechar" class="bottonRightCol" value="Fechar" onclick="javascript:window.close();" type="button">';
 		return false;
	}
}

function urlVoltar(){

	window.location.href = document.getElementById("url").value;
	
}

</script>


</head>

<body leftmargin="5" topmargin="5" onload="verificarVoltar();toggleBox('demodiv',0);">

<%if (exception instanceof ActionServletException) {
	ActionServletException actionServletException = (ActionServletException) exception; 
	
	if (actionServletException.getUrlBotaoVoltar() != null && 
		!actionServletException.getUrlBotaoVoltar().equalsIgnoreCase("")){%>
		
		<input type="hidden" id="url" value="<%="" + actionServletException.getUrlBotaoVoltar()%>">
	
	<%}%>
<%}else if(exception instanceof FachadaException){
	FachadaException fachadaException = (FachadaException) exception; 
	
	if (fachadaException.getUrlBotaoVoltar() != null && 
		!fachadaException.getUrlBotaoVoltar().equalsIgnoreCase("")){%>
		
		<input type="hidden" id="url" value="<%="" + fachadaException.getUrlBotaoVoltar()%>">
	<%}
	

  }
 
	%>	
		

<table width="100%" border="0" cellspacing="5" cellpadding="0">
	<tr>
		<td width="100%" valign="top" class="centercoltext">
		<table height="100%">
			<tr>
				<td></td>
			</tr>
		</table>
		<table width="100%" border="0" align="center" cellpadding="0"
			cellspacing="0">
			<tr>
				<td width="17"><img border="0"
					src="<bean:message key="caminho.imagens"/>parahead_left.gif" /></td>
				<% if (exception.getMessage().startsWith(padraoErro)) {%>
				<td width="100%" class="parabg">Erro</td>
				<% } else if (exception.getMessage().startsWith(padraoAtencao)){%>
				<td width="100%" class="parabg">Atenção</td>
				<% } %>

				<td width="3%" class="parabg">&nbsp;</td>
				<td width="4%"><img border="0"
					src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
			</tr>
		</table>
		<table width="100%" border="0">
			<tr>
				<td width="6%" align="left"><%! String padraoErro = "erro";
	      String padraoAtencao = "atencao";%> <%-- Parte que seta o ícone de erro ou de atenção de acordo com a chave da mensagem de erro--%>
				<% if (exception.getMessage().startsWith(padraoErro)) {%> <img
					src="<bean:message key="caminho.imagens"/>erro.gif" /> <%} else if (exception.getMessage().
							startsWith(padraoAtencao)) {%>
				<img src="<bean:message key="caminho.imagens"/>alerta.gif" /> <% } %>
				</td>

				<td><span style="font-size:12px;font-weight: bold;"> <%--Tenta recuperar um parâmetro da exceção para complementar a mensagem de erro--%>
				<% 
				int tamanhoColecao = 0;	
				if (exception instanceof ActionServletException) {
					ActionServletException actionServletException = (ActionServletException) exception;
					tamanhoColecao = actionServletException.getParametroMensagem().size();

			if (tamanhoColecao > 0) {
			%> <gsan:i18n key="${pageContext.errorData.throwable.message}"
					arg0="<%=(tamanhoColecao - 1) >= 0 ? actionServletException.getParametroMensagem(0) : "" %>"
					arg1="<%=(tamanhoColecao - 1) >= 1 ? actionServletException.getParametroMensagem(1) : "" %>"
					arg2="<%=(tamanhoColecao - 1) >= 2 ? actionServletException.getParametroMensagem(2) : "" %>"
					arg3="<%=(tamanhoColecao - 1) >= 3 ? actionServletException.getParametroMensagem(3) : "" %>"
					arg4="<%=(tamanhoColecao - 1) >= 4 ? actionServletException.getParametroMensagem(4) : "" %>"
					 /><br>
				<% } else {%> 
					<gsan:i18n key="${pageContext.errorData.throwable.message}" /><br>
				<% } %> <% } else if (exception instanceof FachadaException){
		    	FachadaException fachadaException = (FachadaException) exception;
		    	tamanhoColecao = fachadaException.getParametroMensagem().size();
				if (tamanhoColecao > 0) {
                    %> <gsan:i18n key="${pageContext.errorData.throwable.message}"
					arg0="<%=(tamanhoColecao - 1) >= 0 ? fachadaException.getParametroMensagem(0) : ""%>"
					arg1="<%=(tamanhoColecao - 1) >= 1 ? fachadaException.getParametroMensagem(1) : ""%>"
					arg2="<%=(tamanhoColecao - 1) >= 2 ? fachadaException.getParametroMensagem(2) : ""%>"
					arg3="<%=(tamanhoColecao - 1) >= 3 ? fachadaException.getParametroMensagem(3) : ""%>"
					arg4="<%=(tamanhoColecao - 1) >= 4 ? fachadaException.getParametroMensagem(4) : ""%>"
					 /><br>

				<% } else { %> 
					<gsan:i18n key="${pageContext.errorData.throwable.message}" /><br>
				<% }
		       } %> </span></td>
			</tr>
			<tr>
				<td colspan="2">
				<div id="botaoBack"></div>
				</td>
			</tr>
			<div ID="demodiv"></div>
			<% if (exception.getMessage().startsWith(padraoErro)){ %>
			<tr>
				<td colspan="2" align="right"><font
					onclick="toggleBox('demodiv',0);"
					onmouseover="toggleBox('demodiv',1);"><strong>Visualizar Log
				Servidor</strong></font>
				<div ID="demodiv">
				<table
					style="position: absolute;left: 10px;top: 180px;width: 133px;height: 70px;border: 1px solid #000000;background-color: #cbe5fe;z-index: 2;overflow: auto;">
					<tr>
						<td><b><%= exception.getMessage() %></b><br>
						<p><b>With the following stack trace:</b>
						<pre><b><% 
						  ByteArrayOutputStream baos = new ByteArrayOutputStream();
						  exception.printStackTrace(new PrintStream(baos));
						  out.print(baos); %>
						</b></pre> <%-- 
						<c:forEach
							items="${pageContext.errorData.throwable.stackTrace}" var="st">
						${st}
						</c:forEach>
						--%></td>
					</tr>
				</table>
				</div>
				</td>
			</tr>
			<% } %>
			<tr>
				<td colspan="2">&nbsp;</td>
			</tr>
		</table>
		<p>&nbsp;</p>
		<p>&nbsp;</p>
		</td>
	</tr>
</table>

</body>

</html:html>

