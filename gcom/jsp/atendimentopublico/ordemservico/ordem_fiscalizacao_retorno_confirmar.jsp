<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%@page isELIgnored="false"
	import="gcom.gui.ActionServletException,gcom.util.ControladorException"%>


<html:html>

<head>

<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">

</head>

<body leftmargin="5" topmargin="5">
<html:form action="/confirmarRetornoOSFiscalizacaoAction"
	onsubmit="return validateManterTarifaSocialActionForm(this);"
	name="ManterTarifaSocialActionForm"
	type="gcom.gui.cadastro.tarifasocial.ManterTarifaSocialActionForm"
	method="post">


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
		 <td width="17"><img border="0"	src="<bean:message key="caminho.imagens"/>parahead_left.gif" /></td>
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
		   <span style="font-size:12px;font-weight: bold;"> Deseja encerrar a Ordem de Serviço de número ${sessionScope.idOS}?
		   </span>
		 </td>
	   </tr>
	   <tr>
		 <td colspan="2" align="center">
		 

	
		     <input type="button"
		          class="bottonRightCol" name="confirmar" value="Sim"
				  onclick="javascript:window.location.href='confirmarRetornoOSFiscalizacaoAction.do?confirmado=sim'" />
				  &nbsp;&nbsp;&nbsp;&nbsp;
			  <input type="button"
		          class="bottonRightCol" name="cancelar" value="Não"
				  onclick="javascript:window.location.href='confirmarRetornoOSFiscalizacaoAction.do?confirmado=nao'" />
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
</html:form>
</body>

</html:html>

