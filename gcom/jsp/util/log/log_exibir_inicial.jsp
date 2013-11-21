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


<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>

<script language="JavaScript">

	function exibirLog() {
		var form = document.ExibirLogActionForm;
		submeterFormPadrao(form);
	}
	
</script>


<body leftmargin="5" topmargin="5">

<html:form action="/exibirLogTelaFinalAction.do"
	name="ExibirLogActionForm"
	type="gcom.gui.util.log.ExibirLogActionForm"
	method="post">

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
		          		<td class="parabg">Exibição do LOG-GSAN</td>
		          		<td width="11">
		          			<img border="0" 
		          				src="<bean:message key="caminho.imagens"/>parahead_right.gif"/>
		          		</td>
		        	</tr>
		      	</table>
			   	
			   	<table width="100%" border="0">
			        
			        <tr>
							<td width="60">
								<div align="left"><strong>Log´s disponíveis:</strong></div>
							</td>
							
							<td width="175">
								<html:select property="arquivo" style="width: 230px;">
									
									<html:options collection="colecaoArquivos" 
										labelProperty="label" 
										property="value" />
								</html:select>
							</td>							
			        </tr>
			        
			        <tr>
			          	<td width="60" align="left">
							<input name="Button" 
								type="button" 
								class="bottonRightCol"
								value="Menu Principal" 
								align="left"
								onclick="window.location.href='/gsan/telaPrincipal.do'">
			          	</td>
			          
			          	<td align="right">
	  		      			<input name="Button" 
								type="button"
								class="bottonRightCol" 
								value="Exibir" 
								onclick="exibirLog();">
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