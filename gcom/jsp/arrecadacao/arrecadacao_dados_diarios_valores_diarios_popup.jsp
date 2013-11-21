<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ page import="java.math.BigDecimal,gcom.arrecadacao.ArrecadacaoDadosDiarios,gcom.util.Util,java.util.Date,java.util.Calendar,java.util.GregorianCalendar" %>
<%@ page import="gcom.arrecadacao.DevolucaoDadosDiarios, gcom.arrecadacao.bean.FiltrarDadosDiariosArrecadacaoHelper" %>

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

function verificarExibicaoRelatorio() {
	
	toggleBox('demodiv',1);
	
}
</SCRIPT>
</head>
<body leftmargin="5" topmargin="5">
<html:form action="/exibirConsultarDadosDiariosValoresDiariosAction.do"
	name="FiltrarDadosDiariosArrecadacaoActionForm"
	type="gcom.gui.arrecadacao.FiltrarDadosDiariosArrecadacaoActionForm"
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
					<td class="parabg">Consultar Dados Diários da Arrecadação - Valores
					Diários</td>
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
						String dadosMesInformado = (String)session.getAttribute("dadosMesInformado");
						String dadosAtual = (String)session.getAttribute("dadosAtual");
						String nomeGerencia = (String)session.getAttribute("nomeGerencia");
						String descricaoLocalidade = (String)session.getAttribute("descricaoLocalidade");
						String descricaoElo = (String)session.getAttribute("descricaoElo");
						String nomeAgente = (String)session.getAttribute("nomeAgente");
						String nomeCategoria = (String)session.getAttribute("nomeCategoria");
						String nomePerfil = (String)session.getAttribute("nomePerfil");
						String nomeDocumento = (String)session.getAttribute("nomeDocumento");
						String nomeArrecadacaoForma = (String)session.getAttribute("nomeArrecadacaoForma");
						
						String nomeUnidadeNegocio = (String)session.getAttribute("nomeUnidadeNegocio");
						String mostraUnidadeGerencia = (String)session.getAttribute("mostraUnidadeGerencia");
						String idArrecadador = (String)request.getAttribute("idArrecadadorPopup");
						
						String faturamentoCobradoEmConta = (String)session.getAttribute("faturamentoCobradoEmConta");
						
						String arrecadador = (String)session.getAttribute("arrecadador");
						
						String idArrecadacaoForma = (String)request.getAttribute("idArrecadacaoForma");
						
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
						<tr bgcolor="#90c7fc">
							<td colspan="4">
								<table width="100%">
									<tr>
										<td>
											<strong>Arrecadador:
												<logic:empty name="arrecadador">TODOS</logic:empty>
												<logic:notEmpty name="arrecadador"><%=arrecadador%></logic:notEmpty>
											</strong>
										</td>
									</tr>
								</table>
							</td>
						</tr>
	    	    		<tr bgcolor="#90c7fc">
	        	          	<td colspan=4>
								<table width="100%">
									<tr>
										<td>
				        	          		<strong>Faturamento Cobrado em Conta: 
			       	          	 					<%=faturamentoCobradoEmConta%>
			       	          				 </strong>
		       	          				 </td>
	       	          				 </tr>
       	          				 </table>
							</td>
						</tr>
						<% 
						if (nomeGerencia != null)
						{
						%>
							<tr>
				                <td width="15%"><strong>Ger&ecirc;ncia:</strong></td>
				                <td width="85%" align="left"><strong> <%= nomeGerencia %></strong></td>
			                </tr>
						<%
						}
						if (nomeUnidadeNegocio != null)
						{
						%>
							<tr>
					            <td width="25%"><strong>Unidade Negócio:</strong></td>
					            <td width="75%" align="left"><strong> <%= nomeUnidadeNegocio %></strong></td>
				            </tr>
						<%
						}
						
						
						if (descricaoElo != null)
						{
						%>
							<tr>
					            <td width="15%"><strong>Localidade Pólo</strong></td>
					            <td width="85%" align="left"><strong> <%= descricaoElo %></strong></td>
				            </tr>
						<%
						}
						if (descricaoLocalidade != null)
						{
						%>
						   <tr>
					          <td width="15%"><strong>Localidade:</strong></td>
					          <td width="85%" align="left"><strong> <%= descricaoLocalidade %></strong></td>
				           </tr>
						<%
						}
						if (nomeAgente != null)
						{
						%>
							<tr>
						        <td width="15%"><strong>Banco:</strong></td>
						    	<td width="85%" align="left"><strong> <%= nomeAgente %></strong></td>
					    	</tr>
						<%
						}
						if (nomeCategoria != null)
						{
						%>
							<tr>
						        <td width="15%"><strong>Categoria:</strong></td>
						    	<td width="85%" align="left"><strong> <%= nomeCategoria %></strong></td>
					    	</tr>
						<%
						}
						if (nomePerfil != null)
						{
						%>
							<tr>
						        <td width="15%"><strong>Perfil:</strong></td>
						    	<td width="85%" align="left"><strong> <%= nomePerfil %></strong></td>
					    	</tr>
						<%
						}
						if (nomeDocumento != null)
						{
						%>
							<tr>
						        <td width="25%"><strong>Tipo do Documento:</strong></td>
						    	<td width="75%" align="left"><strong> <%= nomeDocumento %></strong></td>
					    	</tr>
						<%
						}
						if (nomeArrecadacaoForma != null)
						{
						%>
							<tr>
						        <td width="25%"><strong>Forma de arrecadação:</strong></td>
						    	<td width="75%" align="left"><strong> <%= nomeArrecadacaoForma %></strong></td>
					    	</tr>
						<%
						}
						
						%>
					</table>
					<table width="100%" border="0">
					<% if(idArrecadacaoForma == null) {
							%>
						<tr bgcolor="#cbe5fe">
						<td align="right" width="100%">
								<strong>
									<a href="javascript:abrirPopup('exibirConsultarDadosDiariosValoresDiariosAnaliticoAction.do?referencia=<%= referencia%>&idArrecadadorPopup=${requestScope.idArrecadadorPopup}', 475, 800);">
										Por Forma de Arrecadação
									</a>
								</strong>
						</td>
						</tr>
					<%}
						%>
						</table>
						<table width="100%" border="0">
						<tr bgcolor="#cbe5fe">
							<td width="82%" align="right">
							<strong>Valor:</strong>
							</td>
							<td align="right" width="18%">
								<strong>
									<%= Util.formatarMoedaReal(valorTotal) %>
								</strong>
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
							<strong>Quant Pag</strong></font></div>
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
								<strong>Devoluções</strong></font></div>
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
								<strong>%</strong></font></div>
							</td>							
							<td width="25%">
							<div align="center" class="style9">
							<font color="#000000" style="font-size:12px"
								face="Verdana, Arial, Helvetica, sans-serif">
								<strong>Valor até o Dia</strong></font></div>
							</td>							
							<td width="25%">
							<div align="center" class="style9">
							<font color="#000000" style="font-size:12px"
								face="Verdana, Arial, Helvetica, sans-serif"><strong>%</strong></font></div>
							</td>														
						</tr>
						<% 
						BigDecimal valorDia = new BigDecimal("0.00");
						String cor = "#cbe5fe";
						%>
						<logic:present name="colecaoDadosDiarios" scope="session">
						<logic:iterate name="colecaoDadosDiarios" id="itemHelper" type="FiltrarDadosDiariosArrecadacaoHelper">
						<% if (cor.equalsIgnoreCase("#cbe5fe")) {
									cor = "#FFFFFF";%>
								<tr bgcolor="#FFFFFF">
								<%} else {
									cor = "#cbe5fe";%>
								<tr bgcolor="#cbe5fe">
								<%}%>
							<%
								 

							Date data = (Date) itemHelper.getItemAgrupado();
							String quantidadeDocumentos = Util.agruparNumeroEmMilhares(itemHelper.getQuantidadeDocumentos());
							String quantidadePagamentos = Util.agruparNumeroEmMilhares(itemHelper.getQuantidadePagamentos());
							BigDecimal valorArrecadadoBruto = itemHelper.getValorDebitos();
							BigDecimal valorDescontos = itemHelper.getValorDescontos();
							BigDecimal valorDevolucoes = itemHelper.getValorDevolucoes();
							BigDecimal valorArrecadado = itemHelper.getValorArrecadacao();
							BigDecimal valorArrecadadoLiquido = itemHelper.getValorArrecadacaoLiquida();
							
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
								 		<div align="right"><%= quantidadePagamentos == null ? "" : quantidadePagamentos + "" %></div>
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
									<div align="right"><%= Util.formatarMoedaReal(valorDevolucoes) %></div>
								 </font>									
							</td>
							<td nowrap="nowrap">
								<font color="<%=corFont%>" style="font-size:12px"> 
									<div align="right"><%= Util.formatarMoedaReal(valorArrecadadoLiquido) %></div>
								 </font>									
							</td>							
							<% 
								BigDecimal percentualMultiplicacao = itemHelper.getValorDebitos().multiply(new BigDecimal("100.00"));

								BigDecimal percentual = percentualMultiplicacao.divide(
										valorTotal,2,BigDecimal.ROUND_HALF_UP);

								valorDia = itemHelper.getValorArrecadacaoLiquida().add(valorDia);
								
								BigDecimal percentualMultiplicacaoDoDia = valorDia.multiply(new BigDecimal("100.00"));

								BigDecimal percentualDoDia = percentualMultiplicacaoDoDia.divide(
										valorTotal,2,BigDecimal.ROUND_HALF_UP);
							%>								
							<td nowrap="nowrap">
								<font color="<%=corFont%>" style="font-size:12px"> 
									<div align="right"><%= Util.formatarMoedaReal(percentual) %> %</div>
								 </font>									
							</td>
							<td nowrap="nowrap">
								<font color="<%=corFont%>" style="font-size:12px"> 
									<div align="right"><%= Util.formatarMoedaReal(valorDia) %></div>
								 </font>									
							</td>
							<td nowrap="nowrap">
								<font color="<%=corFont%>" style="font-size:12px"> 
									<div align="right"><%= Util.formatarMoedaReal(percentualDoDia) %> %</div>
								 </font>									
							</td>
						</tr>
					</logic:iterate>
					</logic:present>
					</table>
		            <table width="100%" border="0">
						<tr>
							<td align="right">
								  <div align="right">
								   <a href="javascript:verificarExibicaoRelatorio();">
									<img border="0"
										src="<bean:message key="caminho.imagens"/>print.gif"
										title="Imprimir" /> </a>
								  </div>
							</td>
						</tr>
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
	<jsp:include
		page="/jsp/relatorio/escolher_tipo_relatorio.jsp?relatorio=gerarRelatorioDadosDiariosValoresDiariosAction.do" />
</html:form>
<body>
</html:html>
