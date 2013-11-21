<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<head>
<html:html>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>

<script>
function enviarParaRelatorio(selecao){
	if (selecao.selectedIndex > 0) {
		window.open('http://'+ document.forms[0].ipServidor.value +'/mondrian/testpage.jsp?query=mondrian&parametro='+document.forms[0].tipoRelatorio.value+selecao.value, '_blank' );
		selecao.selectedIndex = 0;
	}
}

</script>	
	
	
</head>

<body leftmargin="5" topmargin="5">
<form action="">

<input type="hidden" name="tipoRelatorio" value="<%=request.getAttribute("tipoRelatorio")%>" /> 
<input type="hidden" name="ipServidor" value="<%=request.getAttribute("ipServidor")%>" /> 
	
	<%@ include	file="/jsp/util/cabecalho.jsp"%> 
	<%@ include file="/jsp/util/menu.jsp"%>

<table width="770" border="0" cellspacing="5" cellpadding="0">
	<tr>
		<td width="115" valign="top" class="leftcoltext">

		<div align="center">
		<p align="left">&nbsp;</p>
		<p align="left">&nbsp;</p>
		<p align="left">&nbsp;</p>

		<%@ include file="/jsp/util/informacoes_usuario.jsp"%>
		<p align="left">&nbsp;</p>
		<p align="left">&nbsp;</p>
		<p align="left">&nbsp;</p>
		<p align="left">&nbsp;</p>
		<p align="left">&nbsp;</p>
		<p align="left">&nbsp;</p>
		<p align="left">&nbsp;</p>
		<p align="left">&nbsp;</p>
		<p align="left">&nbsp;</p>
		<p align="left">&nbsp;</p>
		<p align="left">&nbsp;</p>
		<p align="left">&nbsp;</p>

		<%@ include file="/jsp/util/mensagens.jsp"%>

		<p align="left">&nbsp;</p>
		<p align="left">&nbsp;</p>
		<p align="left">&nbsp;</p>
		<p align="left">&nbsp;</p>
		<p align="left">&nbsp;</p>
		<p align="left">&nbsp;</p>
		<p align="left">&nbsp;</p>

		</div>
		</td>


		<td valign="top" class="centercoltext">

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
				<td class="parabg">Gerencial OLAP</td>
				<td width="11"><img border="0"
					src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>

			</tr>
		</table>

		<p>&nbsp;</p>
		<table bordercolor="#000000" width="100%" cellspacing="0">
			<tr>
				<td colspan="2">Escolha um ano para a exibição do relatório <b><%=request.getParameter("tipoRelatorio")%></b>:
				<td align="right">&nbsp;</td>
			</tr>
		</table>

		<table width="100%" border="0">
			<tr>
				<td width="183"><strong>Ano:</strong></td>
				<td width="432"><select onchange="javascript:enviarParaRelatorio(this);">
					<option value="" selected="selected">&nbsp;</option>
					<option value="2007">2007</option>
					<option value="2008">2008</option>
					<option value="2009">2009</option>
					<option value="2010">2010</option>
					<option value="2011">2011</option>
					<option value="2012">2012</option>
					<option value="2013">2013</option>
				</select>
		</table>

		<p>&nbsp;</p>
		</td>
	</tr>
</table>


<%@ include file="/jsp/util/rodape.jsp"%></form>
</body>
</html:html>
