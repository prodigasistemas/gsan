<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>

<%@ page
	import="gcom.util.Pagina,gcom.util.ConstantesSistema,java.util.Collection"%>

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

</head>

<body leftmargin="5" topmargin="5">

<html:form action="/removerBairroAction"
	name="GerarMovimentoDebitoAutomaticoBancoActionForm"
	type="gcom.gui.arrecadacao.GerarMovimentoDebitoAutomaticoBancoActionForm" method="post"
	>

	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>
	<table width="770" border="0" cellspacing="5" cellpadding="0">
		<tr>

			<td width="120" valign="top" class="leftcoltext">
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
			<td width="600" align="top" bgcolor="#003399" class="centercoltext">
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
					<td class="parabg">Resumo dos Movimentos de Débito Automático Gerados</td>

					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<br>
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td colspan="4">
					<table width="100%">
					 <tr>
					  <td>
					  <strong>Data: <bean:write name="GerarMovimentoDebitoAutomaticoBancoActionForm" property="dataAtual" formatKey="date.format"/></strong>
					  </td>
					  <td>
					  &nbsp;
					  </td>
					  <td>
					   <strong>Hora: <bean:write name="GerarMovimentoDebitoAutomaticoBancoActionForm" property="dataAtual" formatKey="hour.format"/></strong>
					  
					  </td>
					  
					 </tr>
					</table>	
					<td colspan="4">&nbsp;
					</td>
				</tr>
				<tr>
					<td colspan="7">&nbsp;
					</td>
				</tr>
			</table>
			<table width="100%" border="0" bgcolor="#90c7fc">
				<tr bordercolor="#000000">
					<td width="17%" bgcolor="#90c7fc" colspan="2"><div align="center"><strong> Banco</strong></div></td>
					<td align="center" width="8%" bgcolor="#90c7fc" rowspan="2"><strong>NSA</strong></td>
					<td align="center" width="12%" bgcolor="#90c7fc" rowspan="2"><strong>Qtd. Registros</strong></td>
					<td align="center" width="15%" bgcolor="#90c7fc" rowspan="2"><strong>Valor a Debitar</strong></td>
					<td align="center" width="38%" bgcolor="#90c7fc" rowspan="2"><strong>E-mail</strong></td>
					<td align="center" width="10%" bgcolor="#90c7fc" rowspan="2"><strong>Sit. Envio</strong></td>
				</tr>
				<tr>
					<td width="6%" bgcolor="#cbe5fe" align="center"><strong>Cod.</strong></td>
					<td width="11%" bgcolor="#cbe5fe" align="center"><strong>Nome</strong></td>
				</tr>
			</table>
			<table width="100%" border="0" bgcolor="#cbe5fe" cellpadding="0" cellspacing="0">
				<tr>
					<td colspan="7" height="200">
						<div style="width: 100%; height: 100%; overflow: auto;">
							<table width="100%"  border="0" bgcolor="#99CCFF">
									<%int cont = 0;%>
									<logic:iterate name="colecaogerarMovimentoDebitoAutomatico"
										id="gerarMovimentoDebitoAutomaticoBancoHelper">
										<%cont = cont + 1;
							            if (cont % 2 == 0) {%>
										<tr bgcolor="#cbe5fe">
											<%} else {

							            %>
										<tr bgcolor="#FFFFFF">
											<%}%>
											<td width="6%">
											<div align="center"><bean:write name="gerarMovimentoDebitoAutomaticoBancoHelper"
												property="banco.id" /></div>
											</td>
											<td width="11%"><bean:write name="gerarMovimentoDebitoAutomaticoBancoHelper"
												property="banco.nomeAbreviado" /></td>
											<td width="8%" align="center"><bean:write name="gerarMovimentoDebitoAutomaticoBancoHelper"
												property="arrecadadorMovimento.numeroSequencialArquivo" /></td>
											<td width="11%" align="center"><bean:write name="gerarMovimentoDebitoAutomaticoBancoHelper"
												property="arrecadadorMovimento.numeroRegistrosMovimento" /></td>
											<td width="15%">
											<div align="right"><bean:write name="gerarMovimentoDebitoAutomaticoBancoHelper"
												property="arrecadadorMovimento.valorTotalMovimento" formatKey="money.format" />
											</div>
											</td>
												
											<td width="39%"><bean:write name="gerarMovimentoDebitoAutomaticoBancoHelper"
												property="descricaoEmail" /></td>
											<td width="10%"><bean:write name="gerarMovimentoDebitoAutomaticoBancoHelper"
												property="situacaoEnvioEmail" /></td>	
										</tr>										
											
									</logic:iterate>

								</table>
								</div>
					</td>
				</tr>
				</table>
				<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
				 <td colspan="7">
				      <table width="100%" border="0">
						<tr>
							<td valign="top">
								<input type="button" name="ButtonCancelar" class="bottonRightCol" value="Cancelar" onClick="javascript:window.location.href='/gsan/telaPrincipal.do'">
						        &nbsp;<%-- <input name="Button" type="button" class="bottonRightCol" value="Voltar" align="left"	onclick="window.location.href='<html:rewrite page="/exibirGerarMovimentoDebitoAutomaticoBancoAction.do"/>'">--%>
						        <input name="Submit222"
									class="bottonRightCol" value="Voltar" type="button"
									onclick="javascript:history.back();">
						    </td>
							<td valign="top">
							<div align="right"><a
								href="javascript:toggleBox('demodiv',1);">
							<img border="0"
								src="<bean:message key="caminho.imagens"/>print.gif"
								title="Imprimir Movimento de Débito Automático" /> </a></div>
							</td>
						</tr>
					  </table>
					</td>
				 </tr>

			</table>
	</table>
<jsp:include page="/jsp/relatorio/escolher_tipo_relatorio.jsp?relatorio=gerarRelatorioMovimentoDebitoAutomaticoBancoAction.do"/>
	<%@ include file="/jsp/util/rodape.jsp"%>
</body>
</html:form>
</html:html>
