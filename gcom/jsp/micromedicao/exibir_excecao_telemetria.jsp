<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>

<head>

<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">




</head>

<body leftmargin="5" topmargin="5" bgcolor="black">

<table width="100%" border="0">

	<tr>
		<td colspan="2" align="right">
		
		<table cellpadding="1" cellspacing="1">
			<tr>
				<td><font size="2" color="#7FFF00"><bean:write name="excecao" scope="request" filter="false"/></font><br>
				</td>
			</tr>
		</table>
		
		</td>
	</tr>
	<tr>
		<td colspan="2">&nbsp;</td>
	</tr>
</table>

</body>

</html:html>

