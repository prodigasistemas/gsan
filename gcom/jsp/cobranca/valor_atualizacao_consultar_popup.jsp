
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<%@ page import="gcom.util.Util"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
<head>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
<script language="JavaScript">
<!--
function fechar(){
	window.close();
}
//-->
</script>
</head>
<body leftmargin="0" topmargin="0">
<html:form action="/exibirValorAtualizacaoConsultarPopupAction"
	name="ValorAtualizacaoConsultarActionForm"
	type="gcom.gui.cobranca.ValorAtualizacaoConsultarActionForm"
	method="post">

	<table width="600" border="0" cellspacing="5" cellpadding="0">
		<tr>
			<td width="625" valign="top" class="centercoltext">
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
					<td class="parabg">Consultar Acr&eacute;scimos Por Impontualidade</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" />
					</td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>
					<td width="45%"><strong>Valor da Multa:</strong></td>
					<td colspan="2"><input type="text" size="17" maxlength="17"
						readonly="true"
						style="background-color:#EFEFEF; border:0; text-align: right;"
						value="<bean:write name="ValorAtualizacaoConsultarActionForm"  property="multa" formatKey="money.format" />">
					</td>
				</tr>
				<tr>
					<td><strong>Valor dos Juros de Mora:</strong></td>
					<td colspan="2"><input type="text" size="17" maxlength="17"
						readonly="true"
						style="background-color:#EFEFEF; border:0; text-align: right;"
						value="<bean:write name="ValorAtualizacaoConsultarActionForm"  property="juros" formatKey="money.format" />">
					</td>
				</tr>
				<tr>
					<td><strong><bean:write name="label" scope="request" /></strong></td>
					<td colspan="2"><input type="text" size="17" maxlength="17"
						readonly="true"
						style="background-color:#EFEFEF; border:0; text-align: right;"
						value="<bean:write name="ValorAtualizacaoConsultarActionForm"  property="atualizacao" formatKey="money.format" />">
					</td>
				</tr>
				<tr>
					<td colspan="3">&nbsp;</td>
				</tr>
				<table width="100%" border="0">
					
					<tr>
						<td align="left" width="100%">
							  <div align="right">
								   <a href="javascript:toggleBox('demodiv',1);">
										<img border="0" src="<bean:message key="caminho.imagens"/>print.gif"
											title="Imprimir Acréscimos Por Impontualidade" /> 
									</a>
							  </div>
						</td>
					</tr>
					
					<tr>
						<td height="24"><logic:present
							name="caminhoRetornoTelaConsultaDebitos">
							<input type="button" class="bottonRightCol" value="Voltar"
								style="width: 70px;" onclick="javascript:history.back();" />
						</logic:present>
						<td align="right"><input name="Button" type="button"
							class="bottonRightCol" value="Fechar"
							onClick="javascript:fechar();"></td>

					</tr>
				</table>
			</table>
			<p>&nbsp;</p>
			</td>
		</tr>
	</table>

	<jsp:include page="/jsp/relatorio/escolher_tipo_relatorio.jsp?relatorio=gerarRelatorioAcrescimoPorImpontualidadeAction.do"/>
	<%@ include file="/jsp/util/tooltip.jsp" %>	
	</html:form>
</body>
</html:html>
