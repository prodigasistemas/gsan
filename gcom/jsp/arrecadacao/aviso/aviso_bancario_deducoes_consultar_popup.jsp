<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>

<head>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>

<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
<SCRIPT LANGUAGE="JavaScript">
<!--

function fechar(){
		window.close();
}
</SCRIPT>
</head>
<body leftmargin="5" topmargin="5">
	<table width="510" border="0" cellpadding="0" cellspacing="5">
		<tr>
			<td width="500" valign="top" class="centercoltext">
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
					<td class="parabg">Consultar Deduções do Aviso Bancário</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
				<tr>
					<td>
						&nbsp;
					</td>
				</tr>
			</table>
			<table width="100%" bgcolor="#99CCFF" border="0">
				<tr bordercolor="#90c7fc">
					<td width="60%" bordercolor="#000000" bgcolor="#90c7fc">
					<div align="center"><strong>Tipo</strong></div>
					</td>
					<td width="40%" bordercolor="#000000" bgcolor="#90c7fc">
					<div align="center"><strong>Valor</strong></div>
					</td>
				</tr>
				<logic:present name="colecaoDeducoesHelper">
					<% String cor = "#cbe5fe";%>
					<logic:iterate name="colecaoDeducoesHelper" id="deducoesHelper">
					
						<%	if (cor.equalsIgnoreCase("#cbe5fe")){	
							cor = "#FFFFFF";%>
							<tr bgcolor="#FFFFFF" height="18">	
						<%} else{	
							cor = "#cbe5fe";%>
							<tr bgcolor="#cbe5fe" height="18">		
						<%}%>
								<td width="60%" >
									<bean:write name="deducoesHelper" property="tipo" />
								</td>
								<td width="40%" align="right">
									<bean:write name="deducoesHelper" property="valorDeducao" />
								</td>
							</tr>
					</logic:iterate>
				</logic:present>
				</table>
				<table border="0" width="100%"> 			
				<tr>
					<td colspan="3">&nbsp;</td>
				</tr>
				<tr>
					<td colspan="3" align="right"><input name="Button" type="button"
						class="bottonRightCol" value="Fechar"
						onClick="javascript:fechar();"></td>
				</tr>
			
			</table>
			</td>
		</tr>
	</table>

<body>
</html:html>
