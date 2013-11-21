<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<%@ page import="gcom.util.Util" isELIgnored="false"%>
<%@ page import="gcom.util.ConstantesSistema" isELIgnored="false"%>
<%@ page import="java.util.Collection" isELIgnored="false"%>
<%@ page import="gcom.cobranca.bean.TransferenciasDebitoHelper"
	isELIgnored="false"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<head>
<html:html>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript">
<!--

 
//-->
</script>
</head>

<body leftmargin="5" topmargin="5">
<form>

<%@ include file="/jsp/util/cabecalho.jsp"%>
<%@ include file="/jsp/util/menu.jsp"%>

<table width="770" border="0" cellspacing="5" cellpadding="0">
	<tr>
		<td width="115" valign="top" class="leftcoltext">

		<div align="center">
		<p align="left">&nbsp;</p>
		<p align="left">&nbsp;</p>
		<p align="left">&nbsp;</p>

		<%@ include file="/jsp/util/informacoes_usuario.jsp"%>
		<p align="left">&nbsp;</p>
		<p align="left">&nbsp;</p>
		<p align="left">&nbsp;</p>
		<p align="left">&nbsp;</p>
		<p align="left">&nbsp;</p>
		<p align="left">&nbsp;</p>
		<p align="left">&nbsp;</p>
		<p align="left">&nbsp;</p>
		<p align="left">&nbsp;</p>
		<p align="left">&nbsp;</p>
		<p align="left">&nbsp;</p>
		<p align="left">&nbsp;</p>

		<%@ include file="/jsp/util/mensagens.jsp"%>

		<p align="left">&nbsp;</p>
		<p align="left">&nbsp;</p>
		<p align="left">&nbsp;</p>
		<p align="left">&nbsp;</p>
		<p align="left">&nbsp;</p>
		<p align="left">&nbsp;</p>
		<p align="left">&nbsp;</p>

		</div>
		</td>

		<td valign="top" class="centercoltext"><!--Início Tabela Reference a Páginação da Tela de Processo-->
		<p>&nbsp;</p>
		<table width="100%" border="0" align="center" cellpadding="0"
			cellspacing="0">
			<tr>
				<td width="11"><img border="0" src="imagens/parahead_left.gif" /></td>
				<td class="parabg">Consultar Transferências</td>
				<td width="11" valign="top"><img border="0"
					src="imagens/parahead_right.gif" /></td>
			</tr>
		</table>
		<!--Fim Tabela Reference a Páginação da Tela de Processo-->
		<p>&nbsp;</p>
		<table width="100%" border="0">
			<tr>
				<td colspan="4">
				<table width="100%" align="center" bgcolor="#90c7fc" border="0"
					cellspacing="0">
					<%String cor = "#cbe5fe";%>
					<tr bordercolor="#79bbfd">
						<td colspan="5" align="center" bgcolor="#79bbfd"><strong>Contas</strong>
						</td>
					</tr>
					<logic:notEmpty name="colecaoContasTransferidas">
						<tr bordercolor="#000000">
							<td width="10%" bgcolor="#90c7fc" align="center">
							<div class="style9"><font color="#000000" style="font-size:9px"
								face="Verdana, Arial, Helvetica, sans-serif"> <strong>M&ecirc;s/Ano</strong>
							</font></div>
							</td>
							<td width="15%" bgcolor="#90c7fc" align="center">
							<div class="style9"><font color="#000000" style="font-size:9px"
								face="Verdana, Arial, Helvetica, sans-serif"> <strong>Imóvel
							Origem</strong> </font></div>
							</td>
							<td width="15%" bgcolor="#90c7fc" align="center">
							<div class="style9"><font color="#000000" style="font-size:9px"
								face="Verdana, Arial, Helvetica, sans-serif"> <strong>Imóvel
							Destino </strong> </font></div>
							</td>
							<td width="20%" bgcolor="#90c7fc" align="center">
							<div class="style9"><font color="#000000" style="font-size:9px"
								face="Verdana, Arial, Helvetica, sans-serif"> <strong>Data
							Transferência</strong> </font></div>
							</td>
							<td width="40%" bgcolor="#90c7fc" align="left">
							<div class="style9"><font color="#000000" style="font-size:9px"
								face="Verdana, Arial, Helvetica, sans-serif"> <strong>Usuário</strong>
							</font></div>
							</td>
						</tr>
						<logic:present name="colecaoContasTransferidas">
							<tr>
								<td colspan="5" width="100%"><%if (((Collection) session.getAttribute("colecaoContasTransferidas"))
					.size() >= ConstantesSistema.NUMERO_MAXIMO_REGISTROS_PARA_SCROLL) {%>
								<div style="width: 100%; height: 100; overflow: auto;"><%}

			%>
								<table width="100%" bgcolor="#90c7fc" border="0">
									<logic:iterate name="colecaoContasTransferidas"
										id="transferenciasDebitoHelper"
										type="TransferenciasDebitoHelper">

										<%if (cor.equalsIgnoreCase("#cbe5fe")) {
				cor = "#FFFFFF";%>
										<tr bgcolor="#FFFFFF">
											<%} else {
				cor = "#cbe5fe";%>
										<tr bgcolor="#cbe5fe">
											<%}%>
											<td width="10%" align="center"><bean:write
												name="transferenciasDebitoHelper"
												property="conta.referenciaFormatada" /></td>
											<td width="15%" align="center"><bean:write
												name="transferenciasDebitoHelper" property="imovelOrigem.id" /></td>
											<td width="15%" align="center"><bean:write
												name="transferenciasDebitoHelper"
												property="imovelDestino.id" /></td>
											<td width="20%" align="center"><bean:write
												name="transferenciasDebitoHelper"
												property="dataTransferencia" formatKey="date.format" /></td>
											<td width="40%" align="left"><logic:present
												name="transferenciasDebitoHelper" property="usuario">
												<bean:write name="transferenciasDebitoHelper"
													property="usuario.nomeUsuario" />
											</logic:present>&nbsp;</td>
										</tr>
									</logic:iterate>
								</table>
								</div>
								</td>
							</tr>
						</logic:present>
					</logic:notEmpty>
				</table>
				</td>
			</tr>
			<tr>
				<td colspan="4">&nbsp;</td>
			</tr>
			<tr>
				<td colspan="4">
				<table width="100%" align="center" bgcolor="#90c7fc" border="0"
					cellspacing="0">
					<%cor = "#cbe5fe";%>
					<tr bordercolor="#79bbfd">
						<td colspan="5" align="center" bgcolor="#79bbfd"><strong>Débitos a
						Cobrar</strong></td>
					</tr>
					<logic:notEmpty name="colecaoDebitosACobrarTransferidos">
						<tr bordercolor="#000000">
							<td width="25%" bgcolor="#90c7fc" align="left">
							<div class="style9"><font color="#000000" style="font-size:9px"
								face="Verdana, Arial, Helvetica, sans-serif"> <strong>Tipo de
							Débito</strong> </font></div>
							</td>
							<td width="15%" bgcolor="#90c7fc" align="center">
							<div class="style9"><font color="#000000" style="font-size:9px"
								face="Verdana, Arial, Helvetica, sans-serif"> <strong>Imóvel
							Origem</strong> </font></div>
							</td>
							<td width="15%" bgcolor="#90c7fc" align="center">
							<div class="style9"><font color="#000000" style="font-size:9px"
								face="Verdana, Arial, Helvetica, sans-serif"> <strong>Imóvel
							Destino </strong> </font></div>
							</td>
							<td width="20%" bgcolor="#90c7fc" align="center">
							<div class="style9"><font color="#000000" style="font-size:9px"
								face="Verdana, Arial, Helvetica, sans-serif"> <strong>Data
							Transferência</strong> </font></div>
							</td>
							<td width="25%" bgcolor="#90c7fc" align="left">
							<div class="style9"><font color="#000000" style="font-size:9px"
								face="Verdana, Arial, Helvetica, sans-serif"> <strong>Usuário</strong>
							</font></div>
							</td>
						</tr>
						<logic:present name="colecaoDebitosACobrarTransferidos">
							<tr>
								<td colspan="5" width="100%"><%if (((Collection) session
					.getAttribute("colecaoDebitosACobrarTransferidos")).size() >= ConstantesSistema.NUMERO_MAXIMO_REGISTROS_PARA_SCROLL) {%>
								<div style="width: 100%; height: 100; overflow: auto;"><%}

			%>
								<table width="100%" bgcolor="#90c7fc" border="0">
									<logic:iterate name="colecaoDebitosACobrarTransferidos"
										id="transferenciasDebitoHelper">
										<%if (cor.equalsIgnoreCase("#cbe5fe")) {
				cor = "#FFFFFF";%>
										<tr bgcolor="#FFFFFF">
											<%} else {
				cor = "#cbe5fe";%>
										<tr bgcolor="#cbe5fe">
											<%}%>
											<td width="25%" align="left"><bean:write
												name="transferenciasDebitoHelper"
												property="debitoACobrar.debitoTipo.descricao" /></td>
											<td width="15%" align="center"><bean:write
												name="transferenciasDebitoHelper" property="imovelOrigem.id" /></td>
											<td width="15%" align="center"><bean:write
												name="transferenciasDebitoHelper"
												property="imovelDestino.id" /></td>
											<td width="20%" align="center"><bean:write
												name="transferenciasDebitoHelper"
												property="dataTransferencia" formatKey="date.format" /></td>
											<td width="25%" align="left"><logic:present
												name="transferenciasDebitoHelper" property="usuario">
												<bean:write name="transferenciasDebitoHelper"
													property="usuario.nomeUsuario" />
											</logic:present>&nbsp;</td>
										</tr>
									</logic:iterate>
								</table>
								</div>
								</td>
							</tr>
						</logic:present>
					</logic:notEmpty>
				</table>
				</td>
			</tr>

			<tr>
				<td colspan="4">&nbsp;</td>
			</tr>
			<tr>
				<td colspan="4">
				<table width="100%" align="center" bgcolor="#90c7fc" border="0"
					cellspacing="0">
					<%cor = "#cbe5fe";%>
					<tr bordercolor="#79bbfd">
						<td colspan="5" align="center" bgcolor="#79bbfd"><strong>Créditos
						a Realizar</strong></td>
					</tr>
					<logic:notEmpty name="colecaoCreditosARealizarTransferidos">
						<tr bordercolor="#000000">
							<td width="25%" bgcolor="#90c7fc" align="left">
							<div class="style9"><font color="#000000" style="font-size:9px"
								face="Verdana, Arial, Helvetica, sans-serif"> <strong>Tipo de
							Crédito</strong> </font></div>
							</td>
							<td width="15%" bgcolor="#90c7fc" align="center">
							<div class="style9"><font color="#000000" style="font-size:9px"
								face="Verdana, Arial, Helvetica, sans-serif"> <strong>Imóvel
							Origem</strong> </font></div>
							</td>
							<td width="15%" bgcolor="#90c7fc" align="center">
							<div class="style9"><font color="#000000" style="font-size:9px"
								face="Verdana, Arial, Helvetica, sans-serif"> <strong>Imóvel
							Destino </strong> </font></div>
							</td>
							<td width="20%" bgcolor="#90c7fc" align="center">
							<div class="style9"><font color="#000000" style="font-size:9px"
								face="Verdana, Arial, Helvetica, sans-serif"> <strong>Data
							Transferência</strong> </font></div>
							</td>
							<td width="25%" bgcolor="#90c7fc" align="left">
							<div class="style9"><font color="#000000" style="font-size:9px"
								face="Verdana, Arial, Helvetica, sans-serif"> <strong>Usuário</strong>
							</font></div>
							</td>
						</tr>
						<logic:present name="colecaoCreditosARealizarTransferidos">
							<tr>
								<td colspan="5" width="100%" border="0"><%if (((Collection) session
					.getAttribute("colecaoCreditosARealizarTransferidos"))
					.size() >= ConstantesSistema.NUMERO_MAXIMO_REGISTROS_PARA_SCROLL) {%>
								<div style="width: 100%; height: 100; overflow: auto;"><%}

			%>
								<table width="100%" bgcolor="#90c7fc">
									<logic:iterate name="colecaoCreditosARealizarTransferidos"
										id="transferenciasDebitoHelper">
										<%if (cor.equalsIgnoreCase("#cbe5fe")) {
				cor = "#FFFFFF";%>
										<tr bgcolor="#FFFFFF">
											<%} else {
				cor = "#cbe5fe";%>
										<tr bgcolor="#cbe5fe">
											<%}%>
											<td width="25%" align="left"><bean:write
												name="transferenciasDebitoHelper"
												property="creditoARealizar.creditoTipo.descricao" /></td>
											<td width="15%" align="center"><bean:write
												name="transferenciasDebitoHelper" property="imovelOrigem.id" /></td>
											<td width="15%" align="center"><bean:write
												name="transferenciasDebitoHelper"
												property="imovelDestino.id" /></td>
											<td width="20%" align="center"><bean:write
												name="transferenciasDebitoHelper"
												property="dataTransferencia" formatKey="date.format" /></td>
											<td width="25%" align="left"><logic:present
												name="transferenciasDebitoHelper" property="usuario">
												<bean:write name="transferenciasDebitoHelper"
													property="usuario.nomeUsuario" />
											</logic:present>&nbsp;</td>
										</tr>
									</logic:iterate>
								</table>
								</div>
								</td>
							</tr>
						</logic:present>
					</logic:notEmpty>
				</table>
				</td>
			</tr>

			<tr>
				<td colspan="4">&nbsp;</td>
			</tr>

			<tr>
				<td colspan="4">
				<table width="100%" align="center" bgcolor="#90c7fc" border="0"
					cellspacing="0">
					<%cor = "#cbe5fe";%>
					<tr bordercolor="#79bbfd">
						<td colspan="5" align="center" bgcolor="#79bbfd"><strong>Guias de
						Pagamento</strong></td>
					</tr>
					<logic:notEmpty name="colecaoGuiasPagamentoTransferidas">
						<tr bordercolor="#000000">
							<td width="25%" bgcolor="#90c7fc" align="left">
							<div class="style9"><font color="#000000" style="font-size:9px"
								face="Verdana, Arial, Helvetica, sans-serif"> <strong>Tipo de
							Débito</strong> </font></div>
							</td>
							<td width="15%" bgcolor="#90c7fc" align="center">
							<div class="style9"><font color="#000000" style="font-size:9px"
								face="Verdana, Arial, Helvetica, sans-serif"> <strong>Imóvel
							Origem</strong> </font></div>
							</td>
							<td width="15%" bgcolor="#90c7fc" align="center">
							<div class="style9"><font color="#000000" style="font-size:9px"
								face="Verdana, Arial, Helvetica, sans-serif"> <strong>Imóvel
							Destino </strong> </font></div>
							</td>
							<td width="20%" bgcolor="#90c7fc" align="center">
							<div class="style9"><font color="#000000" style="font-size:9px"
								face="Verdana, Arial, Helvetica, sans-serif"> <strong>Data
							Transferência</strong> </font></div>
							</td>
							<td width="25%" bgcolor="#90c7fc" align="left">
							<div class="style9"><font color="#000000" style="font-size:9px"
								face="Verdana, Arial, Helvetica, sans-serif"> <strong>Usuário</strong>
							</font></div>
							</td>
						</tr>
						<logic:present name="colecaoGuiasPagamentoTransferidas">
							<tr>
								<td colspan="5" width="100%"><%if (((Collection) session
					.getAttribute("colecaoGuiasPagamentoTransferidas")).size() >= ConstantesSistema.NUMERO_MAXIMO_REGISTROS_PARA_SCROLL) {%>
								<div style="width: 100%; height: 100; overflow: auto;"><%}

			%>
								<table width="100%" bgcolor="#90c7fc" border="0">
									<logic:iterate name="colecaoGuiasPagamentoTransferidas"
										id="transferenciasDebitoHelper">
										<%if (cor.equalsIgnoreCase("#cbe5fe")) {
				cor = "#FFFFFF";%>
										<tr bgcolor="#FFFFFF">
											<%} else {
				cor = "#cbe5fe";%>
										<tr bgcolor="#cbe5fe">
											<%}%>
											<td width="25%" align="left"><bean:write
												name="transferenciasDebitoHelper"
												property="guiaPagamento.debitoTipo.descricao" /></td>
											<td width="15%" align="center"><bean:write
												name="transferenciasDebitoHelper" property="imovelOrigem.id" /></td>
											<td width="15%" align="center"><bean:write
												name="transferenciasDebitoHelper"
												property="imovelDestino.id" /></td>
											<td width="20%" align="center"><bean:write
												name="transferenciasDebitoHelper"
												property="dataTransferencia" formatKey="date.format" /></td>
											<td width="25%" align="left"><logic:present
												name="transferenciasDebitoHelper" property="usuario">
												<bean:write name="transferenciasDebitoHelper"
													property="usuario.nomeUsuario" />
											</logic:present>&nbsp;</td>
										</tr>
									</logic:iterate>
								</table>
								</div>
								</td>
							</tr>
						</logic:present>
					</logic:notEmpty>
				</table>
			<tr>
				<td colspan="4">&nbsp;</td>
			</tr>
			<tr>
				<td colspan="4">
				<table width="100%" border="0">
					<tr>
						<td colspan="2"><input type="button" name="button"
							class="bottonRightCol" value="Voltar"
							onClick="javascript:window.location.href='/gsan/exibirConsultarTransferenciasDebitoAction.do'">
						</td>
						<td colspan="2">
						<div align="right"><a href="javascript:toggleBox('demodiv',1);"> <img
							border="0" src="<bean:message key="caminho.imagens"/>print.gif"
							title="Imprimir Transferências" /> </a></div>
						</td>
					</tr>
				</table>
				</td>
			</tr>

		</table>
		</td>
	</tr>
</table>

<jsp:include
	page="/jsp/relatorio/escolher_tipo_relatorio.jsp?relatorio=gerarRelatorioTransferenciasConsultarAction.do" />
<%@ include file="/jsp/util/rodape.jsp"%>
<p>&nbsp;</p>
</form>
</body>
</html:html>
