<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ page import="gcom.util.ConstantesSistema"%>

<%@page import="gcom.gerencial.bean.ResumoFaturamentoSimulacaoDetalheHelper"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<link rel="stylesheet"href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">

<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js"></script>

<script language="JavaScript">


</script>

</head>

<body leftmargin="0" topmargin="0"
		onload="window.focus();resizePageSemLink(710,535);">

<html:form action="/exibirResumoAnaliseFaturamentoDetalheAction" 
	name="ResumoAnaliseFaturamentoActionForm"
	type="gcom.gui.gerencial.faturamento.ResumoAnaliseFaturamentoActionForm"
	method="post">
	
	<table width="702" border="0" cellspacing="5" cellpadding="0">
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
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_left.gif" /></td>
					<td class="parabg">Consultar Análise do Faturamento Detalhe - <bean:write name="tipo" scope="session"/></td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>
					<td>
					<table width="100%" align="center" bgcolor="#90c7fc">
						<tr bgcolor="#79bbfd">
							<td width="75%" bgcolor="#90c7fc">
							<div align="center"><strong>Descrição</strong></div>
							</td>
						
							<td width="25%" bgcolor="#90c7fc">
							<div align="center"><strong>Valor</strong></div>
							</td>
						</tr>
							<logic:present name="colecaoDetalhes">
								<%int cont = 0;%>
								<logic:iterate name="colecaoDetalhes" id="detalhe" type="ResumoFaturamentoSimulacaoDetalheHelper">					
										<%cont = cont + 1; 
										if (cont % 2 == 0) {%>
										<tr bgcolor="#cbe5fe">
											<%} else {%>
										<tr bgcolor="#FFFFFF">
											<%}%>									
											<td width="75%" align="left">
											<bean:write name="detalhe" property="descricao"/>
											</td>
											<td width="25%" align="right">
											<bean:write name="detalhe" property="valorCredito"/>
											</td>
										</tr>	
								</logic:iterate>
							</logic:present>
					</table>
					</td>
				</tr>
				<tr>
					<td align="right"><input type="button"
						onclick="window.close()" class="bottonRightCol" value="Fechar"
						style="width: 70px;"></td>
					<td>&nbsp;</td>
				</tr>
			</table>
			<p>&nbsp;</p>
			</td>
		</tr>
	</table>
	
</html:form>

</body>

</html:html>
