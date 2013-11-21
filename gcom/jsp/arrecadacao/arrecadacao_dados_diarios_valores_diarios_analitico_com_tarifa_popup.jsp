<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.Collection"%>
<%@ page import="java.math.BigDecimal,gcom.arrecadacao.ArrecadacaoDadosDiariosAuxiliar,gcom.util.Util,java.util.Date,java.util.Calendar,java.util.GregorianCalendar" %>
<%@ page import="gcom.arrecadacao.DevolucaoDadosDiariosAuxiliar, gcom.arrecadacao.bean.FiltrarDadosDiariosArrecadacaoHelper, gcom.arrecadacao.bean.FormasArrecadacaoDadosDiariosHelper" %>

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
-->		
}
</SCRIPT>
</head>
<body leftmargin="5" topmargin="5">
<html:form action="/exibirConsultarDadosDiariosValoresDiariosAnaliticoComTarifaAction.do"
	name="FiltrarDadosDiariosArrecadacaoComTarifaActionForm"
	type="gcom.gui.arrecadacao.FiltrarDadosDiariosArrecadacaoComTarifaActionForm"
	method="post">
	<table width="670" border="0" cellpadding="0" cellspacing="5">
		<tr>
			<td width="670" valign="top" class="centercoltext">
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
					<td class="parabg">Consultar Dados Diários da Arrecadação - Por Formas de Arrecadação Com Tarifa</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
				</tr>
			</table>
			<table width="100%" border="0">
				<tr>
					<td height="10">
					<table width="100%" border="0">
						<%
						BigDecimal valorTotal = (BigDecimal)session.getAttribute("valorTotal");
						String referencia = (String)session.getAttribute("referencia");
						String dadosMesInformado = (String)request.getAttribute("dadosMesInformado");
						String dadosAtual = (String)request.getAttribute("dadosAtual");
						String nomeGerencia = (String)request.getAttribute("nomeGerencia");
						String descricaoLocalidade = (String)request.getAttribute("descricaoLocalidade");
						String descricaoElo = (String)request.getAttribute("descricaoElo");
						String nomeAgente = (String)request.getAttribute("nomeAgente");
						String nomeCategoria = (String)request.getAttribute("nomeCategoria");
						String nomePerfil = (String)request.getAttribute("nomePerfil");
						String nomeDocumento = (String)request.getAttribute("nomeDocumento");
						String nomeArrecadacaoForma = (String)request.getAttribute("nomeArrecadacaoForma");
						
						String nomeUnidadeNegocio = (String)request.getAttribute("nomeUnidadeNegocio");
						String mostraUnidadeGerencia = (String)request.getAttribute("mostraUnidadeGerencia");
						
						%>
					<tr bgcolor="#90c7fc">
							<td colspan="4">
								<table width="100%">
									<tr>
										<td><strong>Processamento Definitivo: 
											<%= dadosMesInformado %>
											</strong>
									    </td>
										<td>
											<div align="right"><strong>M&ecirc;s/Ano:</strong><strong>
											<%= Util.formatarAnoMesParaMesAno(referencia) %></strong></div>
										</td>
									</tr>
								</table>
							</td>
						</tr>
						<tr bgcolor="#90c7fc">
							<td colspan="4">
								<table width="100%">
									<tr>
										<td><strong>Último Processamento Atual: 
											<%= dadosAtual %>
											</strong>
									    </td>

									</tr>
								</table>
							</td>
						</tr>
						<% 
						if (nomeAgente != null)
						{
						%>
							<tr>
						        <td width="15%"><strong>Banco:</strong></td>
						    	<td width="85%" align="left"><strong> <%= nomeAgente %></strong></td>
					    	</tr>
						<%
						}
						%>
					</table>
					<table width="100%" border="0">
						<tr bgcolor="#cbe5fe">
							<td width="82%" align="right">
							<strong>Valor:</strong>
							</td>
							<td align="right" width="18%">
								<strong><%= Util.formatarMoedaReal(valorTotal) %></strong>
							</td>
						</tr>
					</table>
					<table width="100%" bgcolor="#99CCFF" dwcopytype="CopyTableRow">
						<!--header da tabela interna -->
						<tr bordercolor="#FFFFFF" bgcolor="#90c7fc">
							<td width="25%">
							<div align="center" class="style9">
							<font color="#000000" style="font-size:12px"
								face="Verdana, Arial, Helvetica, sans-serif">
								<strong>Data</strong></font></div>
							</td>
							<td width="25%">
							<div align="center" class="style9">
							<font color="#000000" style="font-size:12px"
								face="Verdana, Arial, Helvetica, sans-serif">
								<strong>Quant Doc</strong></font></div>
							</td>							
							<td width="25%">
							<div align="center" class="style9">
							<font color="#000000" style="font-size:12px"
								face="Verdana, Arial, Helvetica, sans-serif">
							<strong>Débitos</strong></font></div>
							</td>							
							<td width="25%">
							<div align="center" class="style9">
							<font color="#000000" style="font-size:12px"
								face="Verdana, Arial, Helvetica, sans-serif">
								<strong>Descontos</strong></font></div>
							</td>							
							<td width="25%">
							<div align="center" class="style9">
							<font color="#000000" style="font-size:12px"
								face="Verdana, Arial, Helvetica, sans-serif">
								<strong>Valor Arrecadado</strong></font></div>
							</td>							
							<td width="25%">
							<div align="center" class="style9">
							<font color="#000000" style="font-size:12px"
								face="Verdana, Arial, Helvetica, sans-serif">
								<strong>Valor Tarifa</strong></font></div>
							</td>							
							<td width="25%">
							<div align="center" class="style9">
							<font color="#000000" style="font-size:12px"
								face="Verdana, Arial, Helvetica, sans-serif">
								<strong>Arrecadação Líquida</strong></font></div>
							</td>							
							<td width="25%">
							<div align="center" class="style9">
							<font color="#000000" style="font-size:12px"
								face="Verdana, Arial, Helvetica, sans-serif">
								<strong>Data Prevista</strong></font></div>
							</td>							
							<td width="25%">
							<div align="center" class="style9">
							<font color="#000000" style="font-size:12px"
								face="Verdana, Arial, Helvetica, sans-serif">
								<strong>Valor até o Dia</strong></font></div>
							</td>							
						</tr>
						<% 
						BigDecimal valorDia = new BigDecimal("0.00");
						BigDecimal valorDiaFA = new BigDecimal("0.00");
						%>
						<logic:present name="colecaoDadosDiarios">
						<logic:iterate name="colecaoDadosDiarios" id="itemHelper" type="FiltrarDadosDiariosArrecadacaoHelper">
							<tr bgcolor="#FFFFFF">
					
							<%

							Date data = (Date) itemHelper.getItemAgrupado();
							String quantidadeDocumentos = Util.agruparNumeroEmMilhares(itemHelper.getQuantidadeDocumentos());
							BigDecimal valorArrecadadoBruto = itemHelper.getValorDebitos();
							BigDecimal valorDescontos = itemHelper.getValorDescontos();
							String valorTarifa = "";
							BigDecimal valorArrecadado = itemHelper.getValorArrecadacao();
							BigDecimal valorArrecadadoLiquido = itemHelper.getValorArrecadacaoLiquida();
							String dataPrevista = "";
							Collection<FormasArrecadacaoDadosDiariosHelper> colecaoFormasArrecadacao = itemHelper.getColecaoFormasArrecadacao();
							
							Calendar calendarioDataPagamento = new GregorianCalendar();
							calendarioDataPagamento.setTime(data);
							
							String anoMesDataPagamento = "";
							String corFont = "";
							if ((calendarioDataPagamento.get(Calendar.MONTH) + 1) < 10) {
								anoMesDataPagamento = calendarioDataPagamento.get(Calendar.YEAR)+ "0"+ (calendarioDataPagamento.get(Calendar.MONTH) + 1);
							} else {
								anoMesDataPagamento = calendarioDataPagamento.get(Calendar.YEAR)+ "" + (calendarioDataPagamento.get(Calendar.MONTH) + 1);
							}
							if(!anoMesDataPagamento.equals(referencia)){
								corFont = "#CC0000";
							} else {
								corFont = "#000000";
							}
							
							%>
							<td height="17" align="center">												 
								 <font color="<%=corFont%>" style="font-size:12px"> 
								 		<div align="right"><%= Util.formatarData(data) %></div>
								 </font>									
							</td>
							<td nowrap="nowrap">
								 <font color="<%=corFont%>" style="font-size:12px"> 
								 		<div align="right"><%= quantidadeDocumentos == null ? "" : quantidadeDocumentos + "" %></div>
								 </font>																
							</td>
							<td nowrap="nowrap">
								<font color="<%=corFont%>" style="font-size:12px"> 
									<div align="right"><%= Util.formatarMoedaReal(valorArrecadadoBruto) %></div>
								 </font>									
							</td>
							<td nowrap="nowrap">
								<font color="<%=corFont%>" style="font-size:12px"> 
									<div align="right"><%= Util.formatarMoedaReal(valorDescontos) %></div>
								 </font>									
							</td>
							<td nowrap="nowrap">
								<font color="<%=corFont%>" style="font-size:12px"> 
									<div align="right"><%= Util.formatarMoedaReal(valorArrecadado) %></div>
								 </font>									
							</td>							
							<td nowrap="nowrap">
								<font color="<%=corFont%>" style="font-size:12px"> 
									<div align="right"><%= valorTarifa %></div>
								 </font>									
							</td>
							<td nowrap="nowrap">
								<font color="<%=corFont%>" style="font-size:12px"> 
									<div align="right"><%= Util.formatarMoedaReal(valorArrecadadoLiquido) %></div>
								 </font>									
							</td>							
							<% 
								valorDia = itemHelper.getValorArrecadacaoLiquida().add(valorDia);
							%>								
							<td nowrap="nowrap">
								<font color="<%=corFont%>" style="font-size:12px"> 
									<div align="right"><%= dataPrevista %> </div>
								 </font>									
							</td>
							<td nowrap="nowrap">
								<font color="<%=corFont%>" style="font-size:12px"> 
									<div align="right"><%= Util.formatarMoedaReal(valorDia) %></div>
								 </font>									
							</td>
						</tr>
									<% 
										if(colecaoFormasArrecadacao != null) {%>
											<%Iterator colecaoIterator = colecaoFormasArrecadacao.iterator();
											while(colecaoIterator.hasNext()) { %>
												<% FormasArrecadacaoDadosDiariosHelper helper = (FormasArrecadacaoDadosDiariosHelper) colecaoIterator.next(); %>
										<tr bgcolor="#80bffd">
											<td height="17" align="left">
												<font color="<%=corFont%>" style="font-size:11px"> 
													<div align="right"><%= helper.getDescricaoArrecadador() %> </div>
								 				</font>
											</td>
											<td nowrap="nowrap">
												<font color="<%=corFont%>" style="font-size:11px"> 
													<div align="right"><%= helper.getQtdeDocumentos() %></div>
								 				</font>
											</td>
											<td nowrap="nowrap">
												<font color="<%=corFont%>" style="font-size:11px"> 
													<div align="right"><%= Util.formatarMoedaReal(helper.getDebitos()) %></div>
								 				</font>
											</td>
											<td nowrap="nowrap">
												<font color="<%=corFont%>" style="font-size:11px"> 
													<div align="right"><%= Util.formatarMoedaReal(helper.getDescontos()) %></div>
								 				</font>
											</td>
											<td nowrap="nowrap">
												<font color="<%=corFont%>" style="font-size:11px"> 
													<div align="right"><%= Util.formatarMoedaReal(helper.getValorArrecadado()) %></div>
								 				</font>
											</td>
											<td nowrap="nowrap">
												<font color="<%=corFont%>" style="font-size:11px"> 
													<div align="right"><%= Util.formatarMoedaReal(helper.getValorTarifa()) %></div>
								 				</font>
											</td>
											<td nowrap="nowrap">
												<font color="<%=corFont%>" style="font-size:11px"> 
													<div align="right"><%= Util.formatarMoedaReal(helper.getArrecadacaoLiquida()) %></div>
								 				</font>
											</td>
											<% 
												valorDiaFA = helper.getArrecadacaoLiquida().add(valorDiaFA);
											%>	
											<td nowrap="nowrap">
												<font color="<%=corFont%>" style="font-size:11px"> 
													<div align="right"><%=  Util.formatarData(helper.getDataPrevista()) %></div>
								 				</font>
											</td>
											<td nowrap="nowrap">
												<font color="<%=corFont%>" style="font-size:11px"> 
													<div align="right"><%= Util.formatarMoedaReal(valorDiaFA) %></div>
								 				</font>
											</td>
										</tr>
										<%
											}%>
										<%} %>
						
					</logic:iterate>
					</logic:present>
					</table>
					<p></p>

					<p>&nbsp;</p>
					</td>
				</tr>
			</table>
			<table border="0" width="100%">
				<tr>
					<td colspan="3">&nbsp;</td>
				</tr>
				<tr>
					<td colspan="2" align="left">
					<input name="ButtonCancelar" type="button"
						class="bottonRightCol" value="Voltar"
						onClick="javascript:window.history.go(-1);">
						</td>
					<td align="right">
					<input name="Button" type="button"
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
