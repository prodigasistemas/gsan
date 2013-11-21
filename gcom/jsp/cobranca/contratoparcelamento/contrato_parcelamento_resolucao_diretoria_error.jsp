<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>

<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">

</head>

<body leftmargin="5" topmargin="5">
<%@ include file="/jsp/util/cabecalho.jsp"%>

<html:form action="/inserirResolucaoDiretoriaContratoParcelamentoAction.do"
		name="InserirResolucaoDiretoriaContratoParcelamentoActionForm"
		type="gcom.gui.cobranca.contratoparcelamento.InserirResolucaoDiretoriaContratoParcelamentoActionForm"
		method="post">

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
          		<td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_left.gif"/></td>
          		<td class="parabg"> Alerta</td>
          		<td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif"/></td>
        	</tr>
      	</table>
      	
      	<table width="100%" border="0">
        	<tr>
          		<td width="6%" align="left">
              		<img src="<bean:message key="caminho.imagens"/>atencao.gif"/>
         	 	</td>
		       	
		       	<td width="93%" align="center">
		        	<div align="left">
		                	<font>
		                    	<strong> <span class="font12"> <c:out value="${atencao}"></c:out><br /> </span> </strong>
		                  	</font>
			  		</div>
		     	</td>
         		
         		<td width="0" align="center"></td>
        	</tr>
        	
        	<tr>
          		<td colspan="3">&nbsp;</td>
        	</tr>
        	
        	<tr>

          			<td width="6%" align="left">
          				<logic:notPresent name="naoExibirBotaoVoltarTelaAtencao">
            				<strong><a href="#" onclick="<bean:write name="voltar"/>">Voltar</a></strong>
	         	 		</logic:notPresent>
          			</td>

          
          		<td width="93%" 
          			align="right">
  		      		
  		      		<html:link forward="telaPrincipal"><strong>Menu Principal</strong></html:link>
  	      		</td>
        	</tr>
      	</table>
      	<p>&nbsp;</p>
      	<p>&nbsp;</p>
    	</td>
  	
  	</tr>
	</table>
	
	</html:form>
	
	<%@ include file="/jsp/util/rodape.jsp"%>
	
	
</body>
</html:html>