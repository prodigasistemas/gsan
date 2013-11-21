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
<script language="JavaScript">
<!-- Begin -->
</script>

</head>
<body>
<html:form action="/atualizarTipoRateioPopupAction.do"
	name="InserirCriterioCobrancaLinhaConsultarPopupActionForm"
	type="gcom.gui.cobranca.InserirCriterioCobrancaLinhaConsultarPopupActionForm"
	method="post">

	<table width="764" border="0" cellpadding="0" cellspacing="5">
		<tr>
			<td width="754" valign="top" class="centercoltext">
			<table height="100%">
				<tr>
					<td></td>
				</tr>
			</table>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img src="imagens/parahead_left.gif"	border="0" /></td>
					<td class="parabg">Consultar<strong> </strong>Linhas do <font
						size="-1">Crit&eacute;rio</font></td>
					<td width="11"><img src="imagens/parahead_right.gif" border="0" /></td>
				</tr>
			</table>
			<table width="100%" border="0">
				<tr>
					<td height="234">
					<table width="98%" border="0" dwcopytype="CopyTableRow">
						<tr>
							<td colspan="2">&nbsp;</td>
						</tr>
						<tr>
							<td colspan="2">
							<table width="100%" border="0" bordercolor="#000000">
								<tr bordercolor="#90c7fc">
									<td colspan="9" bgcolor="#90c7fc"><strong>Linhas do
									Crit&eacute;rio da A&ccedil;&atilde;o de Cobran&ccedil;a: <html:text
										maxlength="30" property="descricaoCobrancaCriterio" size="40"
										readonly="true"
										style="background-color:#EFEFEF; border:0; color: #000000" />
									</strong></td>
								</tr>
								<tr bordercolor="#000000">
									<td width="7%" bgcolor="#90c7fc">
									<div align="center"></div>
									<div align="center"><strong>Perfil do Im&oacute;vel </strong></div>
									</td>
									<td width="8%" bgcolor="#90c7fc">
									<div align="center"><strong>Categoria</strong></div>
									</td>
									<td width="6%" bgcolor="#90c7fc">
									<div align="center"><strong>Valor M&iacute;nimo do
									D&eacute;bito</strong></div>
									</td>
									<td width="6%" bgcolor="#90c7fc">
									<div align="center"><strong>Qtde. M&iacute;nima de Contas</strong></div>
									</td>
									<td width="7%" bgcolor="#90c7fc">
									<div align="center"><strong>Valor M&aacute;ximo do
									D&eacute;bito</strong></div>
									</td>
									<td width="7%" bgcolor="#90c7fc">
									<div align="center"><strong>Qtde. M&aacute;xima de Contas</strong></div>
									</td>
									<td width="20%" bgcolor="#90c7fc">
									<div align="center"><strong>Valor M&iacute;nimo do
									D&eacute;bito para Clientes com D&eacute;bito Autom&aacute;tico</strong></div>
									</td>
									<td width="18%" bgcolor="#90c7fc">
									<div align="center"><strong>Qtde. M&iacute;nima de Contas para
									Clientes com D&eacute;bito Autom&aacute;tico</strong></div>
									</td>
									<td width="21%" bgcolor="#90c7fc">
									<div align="center"><strong>Valor M&iacute;nimo da Conta do
									M&ecirc;s</strong></div>
									</td>
								</tr>
								<%String cor = "#cbe5fe";%>
								<logic:present name="colecaoCobrancaCriterioLinha"
									scope="session">
									<logic:iterate name="colecaoCobrancaCriterioLinha"
										id="cobrancaCriterioLinha">
										<%if (cor.equalsIgnoreCase("#FFFFFF")) {
				cor = "#cbe5fe";%>
										<tr bgcolor="#cbe5fe">
											<%} else {
				cor = "#FFFFFF";%>
										<tr bgcolor="#FFFFFF">
											<%}%>
											<td>
											<div align="center"><bean:define name="cobrancaCriterioLinha"
												property="imovelPerfil" id="imovelPerfil" /> <bean:write
												name="imovelPerfil" property="descricao" /></div>
											</td>
											<td>
											<div align="center"><bean:define name="cobrancaCriterioLinha"
												property="categoria" id="categoria" /> <bean:write
												name="categoria" property="descricaoAbreviada" /></div>
											</td>
											<td>
											<div align="center"><bean:write name="cobrancaCriterioLinha"
												property="valorMinimoDebito" /></div>
											</td>
											<td>
											<div align="center"><bean:write name="cobrancaCriterioLinha"
												property="quantidadeMinimaContas" /></div>
											</td>
											<td>
											<div align="center"><bean:write name="cobrancaCriterioLinha"
												property="valorMaximoDebito" /></div>
											</td>
											<td>
											<div align="center"><bean:write name="cobrancaCriterioLinha"
												property="quantidadeMaximaContas" /></div>
											</td>
											<td>
											<div align="center"><bean:write name="cobrancaCriterioLinha"
												property="valorMinimoDebitoDebitoAutomatico" /></div>
											</td>
											<td>
											<div align="center"><bean:write name="cobrancaCriterioLinha"
												property="quantidadeMinimaContasDebitoAutomatico" /></div>
											</td>
											<td>
											<div align="center"><bean:write name="cobrancaCriterioLinha"
												property="valorMinimoContaMes" /></div>
											</td>
										</tr>
									</logic:iterate>
								</logic:present>
							</table>
							</td>
						</tr>
						<tr>
							<td width="371" height="26">&nbsp;</td>
							<td width="436" colspan="-2">
							<div align="right"><input type="button" name="Submit"
								value="Fechar" class="bottonRightCol"
								onclick="javascript:window.close();"></div>
							</td>
						</tr>
						
					</table>
					</td>
				</tr>
			</table>
			<p>&nbsp;</p>
			</td>
		</tr>
	</table>
	
	
</html:form>

</body>
</html:html>
