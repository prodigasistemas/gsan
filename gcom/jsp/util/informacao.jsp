<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<head>

<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">

<html:html>
</head>

<body leftmargin="5" topmargin="5">
<table width="100%" border="0" cellspacing="5" cellpadding="0">
	<tr>
		<td width="806" valign="top" class="centercoltext">
		<table height="100%">
			<tr>
				<td></td>
			</tr>
		</table>
		<table width="100%" border="0" align="center" cellpadding="0"
			cellspacing="0">
			<tr>
          <td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_left.gif" /></td>
          <td class="parabg">
          <logic:present name="tituloInformacao" >
           <bean:write name="tituloInformacao"  />
           </logic:present>
           <logic:notPresent name="tituloInformacao">
           Informação
           </logic:notPresent>
           </td>
        <td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
			</tr>
		</table>
		<table width="100%" border="0" cellpadding="0" cellspacing="0">
			<tr>
				<td colspan="2" width="3%">
				<!-- <img src="<bean:message key="caminho.imagens"/>sucesso2.gif" /> -->
				</td>
				
				<td colspan="2" style="padding-top: 15px" >
				<div align="left">
				<span style="font-size:12px;font-weight: bold;"> 
					<logic:present
						name="descricaoInformacao">
						<bean:write filter="false" name="descricaoInformacao" />
					</logic:present> 
				</span>
				</div>
				</td>
			</tr>
			
			<tr>
				<td colspan="4" style="padding-top: 25px">
				<input name="Button2" type="button" class="bottonRightCol" value="Fechar"
						onClick="javascript:window.close();"></td>
			</tr>
			<tr>
				<td colspan="4">&nbsp;</td>
			</tr>
		</table>
		<p>&nbsp;</p>
		<p>&nbsp;</p>
		</td>
	</tr>
</table>

</body>

</html:html>
