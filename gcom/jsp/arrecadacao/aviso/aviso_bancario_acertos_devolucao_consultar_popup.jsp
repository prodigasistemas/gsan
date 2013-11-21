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
					<td class="parabg">Consultar Acertos da Devolução</td>
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

				<tr bgcolor="#90c7fc">
					<td align="center" colspan="3" width="40%"><strong>Conta Bancária</strong></td>
					<td align="center" rowspan="2" width="17%"><strong>Tipo</strong></td>
					<td align="center" rowspan="2" width="17%"><strong>Data</strong></td>
					<td align="center" rowspan="2" width="26%"><strong>Valor</strong></td>
				</tr>
				<tr bgcolor="#90c7fc"> 
					<td align="center" bgcolor="cbe5fe" width="13%"><strong>Banco</strong></td>
					<td align="center" bgcolor="cbe5fe" width="13%"><strong>Agência</strong></td>
					<td align="center" bgcolor="cbe5fe" width="14%"><strong>Número</strong></td>
				</tr>


				<logic:present name="colecaoAcertosAvisoBancarioHelper">
					<% String cor = "#cbe5fe";%>
					<logic:iterate name="colecaoAcertosAvisoBancarioHelper" id="acertosAvisoBancarioHelper">
					
						<%	if (cor.equalsIgnoreCase("#cbe5fe")){	
							cor = "#FFFFFF";%>
							<tr bgcolor="#FFFFFF" height="18">	
						<%} else{	
							cor = "#cbe5fe";%>
							<tr bgcolor="#cbe5fe" height="18">		
						<%}%>
								<td width="13%" >
									<bean:write name="acertosAvisoBancarioHelper" property="idbanco" />
								</td>
								<td width="13%" >
									<bean:write name="acertosAvisoBancarioHelper" property="codigoAgencia" />
								</td>
								<td width="14%" >
									<bean:write name="acertosAvisoBancarioHelper" property="numeroConta" />
								</td>
								<td width="17%" >
									<bean:write name="acertosAvisoBancarioHelper" property="indicadorCreditoDebito" />
								</td>
								<td width="17%" >
									<bean:write name="acertosAvisoBancarioHelper" property="dataAcerto" />
								</td>
								<td width="26%" align="right">
									<bean:write name="acertosAvisoBancarioHelper" property="valorAcerto" />
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
