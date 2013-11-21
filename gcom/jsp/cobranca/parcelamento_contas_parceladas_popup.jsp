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
<html:form action="/exibirParcelamentoContasParceladasPopupAction.do"
	name="ParcelamentoDebitoActionForm"
	type="gcom.gui.cobranca.ParcelamentoDebitoActionForm" method="post">
	<table width="570" border="0" cellpadding="0" cellspacing="5">
		<tr>
			<td width="560" valign="top" class="centercoltext">
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
					<td class="parabg">Contas que foram parceladas:</td>
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
					<td width="25%" bordercolor="#000000" bgcolor="#90c7fc">
					<div align="center"><strong> M&ecirc;s/Ano</strong></div>
					</td>
					<td width="30%" bordercolor="#000000" bgcolor="#90c7fc">
					<div align="center"><strong>Vencimento</strong></div>
					</td>
					<td width="45%" bordercolor="#000000" bgcolor="#90c7fc">
					<div align="center"><strong>Valor</strong></div>
					</td>
				</tr>
				<logic:present name="colecaoParcelamentoItem">
					<logic:iterate name="colecaoParcelamentoItem" id="parcelamentoItem">
						<tr bordercolor="#90c7fc">
							<logic:notEmpty name="parcelamentoItem" property="contaGeral">
							
  							    <bean:define name="parcelamentoItem" property="contaGeral" id="contaGeral" /> 
								<logic:notEmpty name="contaGeral" property="conta">
								
										<bean:define name="contaGeral" property="conta" id="conta" /> 
										<logic:notEmpty name="conta" property="formatarAnoMesParaMesAno">
											<td align="center" bgcolor="#cbe5fe">
												<bean:write name="conta" property="formatarAnoMesParaMesAno" />
											</td>
										</logic:notEmpty>
										<logic:notEmpty name="conta" property="dataVencimentoConta">
											<td align="center" bgcolor="#cbe5fe">
												<bean:write name="conta" property="dataVencimentoConta" formatKey="date.format"/>
											</td>	
										</logic:notEmpty>
										<logic:notEmpty name="conta" property="valorTotal">
											<td align="right" bgcolor="#cbe5fe">
												<bean:write name="conta" property="valorTotal" formatKey="money.format"/>
											</td>
										</logic:notEmpty>
										
								</logic:notEmpty>
								
								<logic:notEmpty name="contaGeral" property="contaHistorico">
								
										<bean:define name="contaGeral" property="contaHistorico" id="conta" /> 
										<logic:notEmpty name="conta" property="formatarAnoMesParaMesAno">
											<td align="center" bgcolor="#cbe5fe">
												<bean:write name="conta" property="formatarAnoMesParaMesAno" />
											</td>
										</logic:notEmpty>
										<logic:notEmpty name="conta" property="dataVencimentoConta">
											<td align="center" bgcolor="#cbe5fe">
												<bean:write name="conta" property="dataVencimentoConta" formatKey="date.format"/>
											</td>	
										</logic:notEmpty>
										<logic:notEmpty name="conta" property="valorTotal">
											<td align="right" bgcolor="#cbe5fe">
												<bean:write name="conta" property="valorTotal" formatKey="money.format"/>
											</td>
										</logic:notEmpty>
										
								</logic:notEmpty>
								
								
								
								
							</logic:notEmpty>
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
				<p>&nbsp;</p>
			</table>
			</td>
		</tr>
	</table>
</html:form>
<body>
</html:html>
