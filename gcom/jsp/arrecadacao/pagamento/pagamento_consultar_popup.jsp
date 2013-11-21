<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
<head>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript">
<!--
function fechar(){
  window.close();
}
-->
</script>

</head>

<body leftmargin="5" topmargin="5" onload="resizePageSemLink(510, 210);">

<html:form action="/exibirConsultarPagamentoPopupAction"
	method="post">


<table width="490" border="0" cellspacing="5" cellpadding="0">
	<tr>
		<td width="480" valign="top" class="centercoltext">
		<table height="100%">
			<tr>
				<td></td>
			</tr>
		</table>

		<table width="100%" border="0" align="center" cellpadding="0"
			cellspacing="0">
			<tr>
				<td width="11"><img border="0"
					src="<bean:message key="caminho.imagens"/>parahead_left.gif" /></td>
				<td class="parabg">Dados do Pagamento</td>
				<td width="11"><img border="0"
					src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
			</tr>
		</table>

		<p>&nbsp;</p>
		
			<table width="100%" border="0">
				
				 <tr>
			        <td><strong>Arrecadador:</strong></td>
			        <td>
				        <html:text property="codigoAgenteArrecadador" size="3" maxlength="3" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" />
			            <html:text property="nomeClienteArrecadador" size="45" maxlength="45" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" />
			        </td>
		        </tr>
		        
		        <tr>
			        <td><strong>Ultima Alteração:</strong></td>
			        <td>
				        <html:text property="ultimaAlteracaoPagamento" size="10" maxlength="10" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" />
			        </td>
		        </tr>
				
				<tr>
				<td colspan="2" align="right"><input name="Button" type="button"
					class="bottonRightCol" value="Fechar"
					onClick="javascript:fechar();"></td>
				</tr>
				
			</table>	

		</td>
	</tr>

</table>
</html:form>
</body>
</html:html>
