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

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>

<html:javascript staticJavascript="false"  formName="FiltrarOrdemProcessoRepavimentacaoActionForm"/>

</head>

<html:form action="/exibirImprimirRelacaoOrdemServicoRepavimentacaoAction" 
	method="post"
	name="FiltrarOrdemProcessoRepavimentacaoActionForm"
	type="gcom.gui.atendimentopublico.ordemservico.FiltrarOrdemProcessoRepavimentacaoActionForm">
	
<body leftmargin="0" topmargin="0" onload="resizePageSemLink(600, 400);">	

	<table width="300" height="200" border="0" cellpadding="0" >

  	<tr>
    	<td valign="top" class="centercoltext"> 
      	
      	<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
        	<tr>
          		<td width="11">
          			<img border="0" src="<bean:message key="caminho.imagens"/>parahead_left.gif" />
          		</td>
          		<td class="parabg">Imprimir Relação das Ordens em Processo de Repavimentação</td>
				<td width="11">
					<img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif" />
				</td>
        	</tr>
      	</table>
      	
      	<p>&nbsp;</p>

      	<table width="100%" border="0">
        	<tr>
          		<td colspan="3">Para imprimir a relação das ordens em processo de repavimentação,informe os dados abaixo:</td>
    		</tr>
      	</table>
    
      	<table width="100%" border="0">
        	<tr>

				<td align="left">
		    		 <strong>Relatorio: </strong>
		  		</td>
			  		
		  		<logic:equal name="FiltrarOrdemProcessoRepavimentacaoActionForm" property="situacaoRetorno" value="4">
		  			<td align="left">
						<select name="escolhaRelatorio">
		                     <option value="1">Completo</option>
						</select>
					</td>
		  		</logic:equal>
			  		
		  		<logic:notEqual name="FiltrarOrdemProcessoRepavimentacaoActionForm" property="situacaoRetorno" value="4">
		  			<td align="left">
						<select name="escolhaRelatorio">
		                     <option value="1">Completo</option>
		                     <option value="2">Divergente</option>
		                     <option value="3">Convergente</option>		                     						  
						</select>
					</td>
		  		</logic:notEqual>
      		</tr>
      		
      		<tr>
				<td colspan="2" align="left">
		    		 <strong>Apenas Os´s com observação de retorno? </strong>
		  		</td>
        		
        		<td>
          			<strong> 
            		<html:radio property="indicadorOsObservacaoRetorno" value="1">SIM</html:radio>
            		<html:radio property="indicadorOsObservacaoRetorno" value="2">NÃO</html:radio>
            		</strong>
            	</td>
            	
      		</tr>
        	
        	<tr>
		    	<td colspan="3">
       				<input type="button" 
       					class="bottonRightCol" 
       					value="Fechar" 
       					onclick="window.close();"/>
        		</td>
				
				<td align="right">
	                <input name="ButtonImprimirRelacao" 
	                	class="bottonRightCol" 
	                	onClick="javascript:toggleBox('demodiv',1);" 
	                	value="Imprimir Relação" 
	                	type="button">	
				</td>
        	</tr>
      	</table>
   		</td>
	</tr>
	</table>
	
	<jsp:include page="/jsp/relatorio/escolher_tipo_relatorio.jsp?relatorio=gerarRelacaoServicosAcompanhamentoRepavimentacaoAction.do&left=100&top=130" />
	
</body>
</html:form>
</html:html>