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
<%@ page
	import="gcom.gerencial.bean.CobrancaAcaoDebitoHelper,gcom.cobranca.ResumoCobrancaAcao"%>
<SCRIPT LANGUAGE="JavaScript">
<!--

function fechar(){
		window.close();
}
</SCRIPT>
</head>
<body leftmargin="5" topmargin="5">
<html:form action="/exibirConsultarResumoAcaoCobrancaSituacaoPagoPopupAction.do"
	name="ResumoAcaoCobrancaActionForm"
	type="gcom.gui.gerencial.cobranca.ResumoAcaoCobrancaActionForm"
	method="post">
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
					<td class="parabg">Consultar Resumo das Ações de Cobrança</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<table>
				<tr>
					<td></td>
				</tr>
				<tr>
					<td><strong>Ação de Cobrança:</strong></td>
					<td>${requestScope.cobrancaAcao}</td>
				</tr>
				<tr>
					<td><strong>Situação da Ação:</strong></td>
					<td>${requestScope.cobrancaAcaoSituacao}</td>
				</tr>
				<logic:notEmpty name="idCobrancaAcaoDebito">
					<tr>
						<td><strong>Situação do Débito:</strong></td>
						<td>${requestScope.cobrancaAcaoDebito}</td>
					</tr>
				</logic:notEmpty>
			</table>

			<table bgcolor="#90c7fc" border="0">
				<tr>
					<td bgcolor="#79bbfd" align="center"><strong> Periodo de Referência</strong></td>
					<td bgcolor="#79bbfd" align="center"><strong> Quantidade Documentos</strong></td>
					<td bgcolor="#79bbfd" align="center"><strong> Percentual </strong></td>
					<td bgcolor="#79bbfd" align="center"><strong> Valor Documentos</strong></td>
					<td bgcolor="#79bbfd" align="center"><strong> Percentual</strong></td>
				</tr>
				<logic:notEmpty name="cobrancaAcaoDebitoHelperParaPago">



					<logic:iterate name="cobrancaAcaoDebitoHelperParaPago"
						id="cobrancaAcaoDebitoHelper">

						<tr>
                            <logic:equal name="cobrancaAcaoDebitoHelper" property="indicadorAntesApos" value="<%=""+ResumoCobrancaAcao.INDICADOR_ANTES %>">
 							  <td width="17%" bgcolor="#FFFFFF">ANTES</td>
 							  <td width="17%" align="right" bgcolor="#FFFFFF"><bean:write
									name="cobrancaAcaoDebitoHelper"
									property="quantidadeDocumento" formatKey="number.format" /></td>
							  <td width="17%" align="right" bgcolor="#FFFFFF"><%=((CobrancaAcaoDebitoHelper) cobrancaAcaoDebitoHelper)
															.getPercentualQuantidade(""
																	+ request
																			.getAttribute("quantidadeTotal"))%></td>
							  <td width="17%" align="right" bgcolor="#FFFFFF"><bean:write
									name="cobrancaAcaoDebitoHelper"
									property="valorDocumento" formatKey="money.format" /></td>
							  <td width="15%" align="right" bgcolor="#FFFFFF"><%=((CobrancaAcaoDebitoHelper) cobrancaAcaoDebitoHelper)
															.getPercentualValor(""
																	+ request
																			.getAttribute("valorTotal"))%></td>	
							</logic:equal>
							<logic:equal name="cobrancaAcaoDebitoHelper" property="indicadorAntesApos" value="<%=""+ResumoCobrancaAcao.INDICADOR_APOS %>">
							 <td width="17%" bgcolor="#cbe5fe" >DEPOIS</td>
							 <td width="17%" align="right" bgcolor="#cbe5fe"><bean:write
									name="cobrancaAcaoDebitoHelper"
									property="quantidadeDocumento" formatKey="number.format" /></td>
							  <td width="17%" align="right" bgcolor="#cbe5fe"><%=((CobrancaAcaoDebitoHelper) cobrancaAcaoDebitoHelper)
															.getPercentualQuantidade(""
																	+ request
																			.getAttribute("quantidadeTotal"))%></td>
							  <td width="17%" align="right" bgcolor="#cbe5fe"><bean:write
									name="cobrancaAcaoDebitoHelper"
									property="valorDocumento" formatKey="money.format" /></td>
							  <td width="15%" align="right" bgcolor="#cbe5fe"><%=((CobrancaAcaoDebitoHelper) cobrancaAcaoDebitoHelper)
															.getPercentualValor(""
																	+ request
																			.getAttribute("valorTotal"))%></td>	
							</logic:equal>
										
					  </tr>
					</logic:iterate>
					<tr>
					 <td bgcolor="#FFFFFF" width="34%"><strong>TOTAL</strong></td>
					 <td bgcolor="#FFFFFF" width="17%" align="right"><strong>${requestScope.quantidadeTotal}</strong></td>
					 <td bgcolor="#FFFFFF" width="17%" align="right"><strong>100,00</strong></td>
					 <td bgcolor="#FFFFFF" width="17%" align="right"><strong>${requestScope.valorTotalFormatado}</strong></td>
					 <td bgcolor="#FFFFFF" width="15%" align="right"><strong>100,00</strong></td>
				    </tr>
				</logic:notEmpty>
				
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
</html:form>
<body>
</html:html>
