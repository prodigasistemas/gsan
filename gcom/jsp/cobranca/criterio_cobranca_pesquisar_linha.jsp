<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>

<script language="JavaScript">

</script>
</head>
<!-- onload="resizePageSemLink(810,700);" -->
<body leftmargin="0" topmargin="0" onload="resizePageSemLink(790,450);">
<html:form action="/pesquisarCriterioCobrancaAction"
	name="PesquisarCriterioCobrancaLinhaActionForm"
	type="gcom.gui.cobranca.PesquisarCriterioCobrancaLinhaActionForm"
	method="post">

	<table width="770" border="0" cellspacing="5" cellpadding="0">
		<tr>

			<td width="770" valign="top" class="centercoltext">
			<table height="100%">

				<tr>
					<td></td>
				</tr>
			</table>
			<table align="center" border="0" cellpadding="0" cellspacing="0"
				width="100%">
				<tr>
					<td width="11"><img border="0"
						src="/gsan/imagens/parahead_left.gif" /></td>
					<td class="parabg">Linhas do Critério de Cobrança</td>

					<td width="11"><img border="0"
						src="/gsan/imagens/parahead_right.gif" /></td>
				</tr>
			</table>
			<table height="100%">

				<tr>
					<td></td>
				</tr>
			</table>
			<table width="100%">
				<tr>
					<td colspan="2">
					<table width="100%" bgcolor="#90c7fc">
						<tr bgcolor="#90c7fc">
							<td colspan="9" bgcolor="#90c7fc"><strong>Linhas do Critério de
							Cobrança: <html:text property="criterioDescricao" size="40"
								maxlength="40" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000;" />
							</strong></td>
						</tr>
						<tr bgcolor="#90c7fc">
							<td bgcolor="#90c7fc" width="7%">
							<div align="center"></div>
							<div align="center"><strong>Perfil do Imóvel </strong></div>
							</td>
							<td bgcolor="#90c7fc" width="8%">
							<div align="center"><strong>Categoria</strong></div>
							</td>
							<td bgcolor="#90c7fc" width="6%">
							<div align="center"><strong>Valor Mínimo do Débito</strong></div>
							</td>
							<td bgcolor="#90c7fc" width="6%">
							<div align="center"><strong>Qtde. Mínima de Contas</strong></div>
							</td>
							<td bgcolor="#90c7fc" width="7%">
							<div align="center"><strong>Valor Máximo do Débito</strong></div>
							</td>
							<td bgcolor="#90c7fc" width="7%">
							<div align="center"><strong>Qtde. Máxima de Contas</strong></div>
							</td>
							<td bgcolor="#90c7fc" width="20%">
							<div align="center"><strong>Valor Mínimo do Débito para Clientes
							com Débito Automático</strong></div>
							</td>
							<td bgcolor="#90c7fc" width="18%">
							<div align="center"><strong>Qtde. Mínima de Contas para Clientes
							com Débito Automático</strong></div>
							</td>
							<td bgcolor="#90c7fc" width="21%">
							<div align="center"><strong>Valor Mínimo da Conta do Mês</strong></div>
							</td>
						</tr>
						<%String cor = "#cbe5fe";%>
						<logic:present name="colecaoCobCritLinha" scope="session">
							<logic:iterate name="colecaoCobCritLinha" id="linha">
								<%	if (cor.equalsIgnoreCase("#cbe5fe")){	
								cor = "#FFFFFF";%>
								<tr bgcolor="#FFFFFF" height="18">	
							<%} else{	
								cor = "#cbe5fe";%>
								<tr bgcolor="#cbe5fe" height="18">		
							<%}%>

									<td>
									<div align="center"><bean:write name="linha"
										property="imovelPerfil.descricao" /></div>
									</td>
									<td>
									<div align="center"><bean:write name="linha"
										property="categoria.descricaoAbreviada" /></div>
									</td>
									<td>
									<div align="center"><bean:write name="linha"
										property="valorMinimoDebito" formatKey="money.format" /></div>
									</td>
									<td>
									<div align="center"><bean:write name="linha"
										property="quantidadeMinimaContas" formatKey="number.format" /></div>
									</td>
									<td>
									<div align="center"><bean:write name="linha"
										property="valorMaximoDebito" formatKey="money.format" /></div>
									</td>
									<td>
									<div align="center"><bean:write name="linha"
										property="quantidadeMaximaContas" formatKey="number.format" /></div>
									</td>
									<td>
									<div align="center"><bean:write name="linha"
										property="valorMinimoDebitoDebitoAutomatico"
										formatKey="money.format" /></div>
									</td>
									<td>
									<div align="center"><bean:write name="linha"
										property="quantidadeMinimaContasDebitoAutomatico"
										formatKey="number.format" /></div>
									</td>
									<td>
									<div align="center"><bean:write name="linha"
										property="valorMinimoContaMes" formatKey="money.format" /></div>
									</td>
								</tr>
							</logic:iterate>
						</logic:present>
					</table>
					</td>
				</tr>
			</table>
			<br>
			<table width="100%" border="0">
				<tr>
					<td>
					<div align="left"><input type="button" name="butao" value="Voltar"
						class="bottonRightCol" onclick="javascript:history.back();"></div>

					</td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
</html:form>
</body>
</html:html>
