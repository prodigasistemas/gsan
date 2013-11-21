<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
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
		botaoBack.innerHTML = '<br><input name="botaoVoltar" class="bottonRightCol" value="Voltar" onclick="javascript:history.back();" type="button">';
    	return true;
	} else {
	    
		botaoBack.innerHTML = '<br><input name="botaoVoltar" class="bottonRightCol" value="Voltar" onclick="javascript:history.back();" type="button">';
 		return false;
	}
}

</script>


</head>

<body leftmargin="5" topmargin="5"
	onload="verificarVoltar();toggleBox('demodiv',0);">
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
				<td width="100%" class="parabg">Atenção</td>

				<td width="3%" class="parabg"><html:link
					href="javascript:window.close();">
					<img border="0"
						src="<bean:message key="caminho.imagens"/>close.gif" alt="Fechar" />
				</html:link></td>
				<td width="4%"><img border="0"
					src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
			</tr>
		</table>
		<table width="100%" border="0">
			<tr>
				<td width="6%" align="left"><img
					src="<bean:message key="caminho.imagens"/>alerta.gif" /></td>

				<td><span style="font-size:12px;font-weight: bold;"> <logic:messagesPresent>
					<%-- 	Os seguintes erros precisam ser corrigidos para finalizar o processo:--%>
					<table width="100%" border="0">
						<html:messages id="error">
							<tr>
								<td><strong><bean:write name="error" /><br>
								</strong></td>
							</tr>
						</html:messages>
					</table>
				</logic:messagesPresent></span></td>
			</tr>
			<tr>
				<td colspan="2">&nbsp;</td>
			</tr>
			<tr>
				<td colspan="2">
				<div id="botaoBack"></div>
				</td>
			</tr>
		</table>
		<p>&nbsp;</p>
		</td>
	</tr>
</table>

</body>

</html:html>



