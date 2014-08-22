<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="gcom.arrecadacao.pagamento.Pagamento"%>
<%@ page import="gcom.util.Util" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<%@ include file="/jsp/util/titulo.jsp"%>
		<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
		
		<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
		<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js"></script>
		<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
		
		<script language="JavaScript">
		
			function refaturar(form) {
				form.action = 'refaturarPagamentosNaoClassificadosAction.do?devolver=0';
		  		form.submit();
			}
			
			function devolver(form) {
				form.action = 'classificarPagamentosAction.do?devolver=1';
		  		form.submit();
			}
		</script>
		
	</head>

	<body>
		<html:form action="/classificarPagamentosAction" method="post" name="PagamentosAClassificarActionForm"
			type="gcom.gui.arrecadacao.PagamentosAClassificarActionForm" method="post">
		
			<%@ include file="/jsp/util/cabecalho.jsp"%>
			<%@ include file="/jsp/util/menu.jsp"%>
		
			<table id="principal" width="770" border="0" cellspacing="5" cellpadding="0">
				<tr>
					<td width="130" valign="top" class="leftcoltext">
						<div id="mensagens" align="center">
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
					
					<td width="602" valign="top" class="centercoltext">
				
						<table id="vazia" height="100%">
							<tr> 
								<td></td>
							</tr>
						</table>
						
						<table id="cabecalho" width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
							<tr>
								<td width="11">
									<img border="0"src="<bean:message key="caminho.imagens"/>parahead_left.gif" />
								</td>
			
								<logic:notPresent name="acao" scope="session">
									<td class="parabg">Classificar Pagamentos</td>
								</logic:notPresent>
			
								<td width="11">
									<img border="0"	src="<bean:message key="caminho.imagens"/>parahead_right.gif" />
								</td>
							</tr>
						</table>
						
						<table id="descricaoPesquisa" width="100%" cellpadding="0" cellspacing="0">
							<tr>
								<td height="23">
									<font style="font-size: 10px;"color="#000000" face="Verdana, Arial, Helvetica, sans-serif"> 
										<strong>Situação pesquisada:</strong> 
										<bean:write name="PagamentosAClassificarActionForm" property="situacaoPagamento"/>
									</font>
								</td>
								
							</tr>
							
							<tr>
								<td height="23">
									<font style="font-size: 10px;"color="#000000" face="Verdana, Arial, Helvetica, sans-serif"> 
										<strong>Pagamentos encontrados:</strong> 
										<bean:write name="totalRegistros" /> 
									</font>
								</td>
								
							</tr>
						</table>
						
						<table id="resultadoConsulta" width="100%" cellpadding="0" cellspacing="0">
							<tr>
								<td colspan="7" bgcolor="#000000" height="2"></td>
							</tr>
							
							<tr>
								<td>
									<div id="divColecao" style="height:300px;overflow:auto;">
									<table width="100%" bgcolor="#90c7fc">
										<tr>
											<td width="7%">
												<div align="center">
													<strong><a href="javascript:facilitador(this);" id="0">Todos</a></strong>
												</div>
											</td>
											<td align="center" width="8%" bgcolor="#90c7fc"><strong>Imóvel</strong></td>
											<td align="center" width="8%" bgcolor="#90c7fc"><strong>Data pagamentos</strong></td>
											<td align="center" width="11%" bgcolor="#90c7fc"><strong>Valor</strong></td>
											<td align="center" width="8%" bgcolor="#90c7fc"><strong>Referência</strong></td>
											
											<logic:equal name="PagamentosAClassificarActionForm" property="exibirMotivoCancelamento" value="true">
												<td align="center" width="8%" bgcolor="#90c7fc"><strong>Motivo cancelamento</strong></td>
											</logic:equal>

													<logic:present name="colecaoPagamentosAClassificar">
														<% String cor = "#cbe5fe";%>
														<logic:iterate name="colecaoPagamentosAClassificar" id="pagamento" type="Pagamento"> 
															<%	if (cor.equalsIgnoreCase("#cbe5fe")){	
																cor = "#FFFFFF";%>
																<tr bgcolor="#FFFFFF" height="18">	
															<%} else{	
																cor = "#cbe5fe";%>
																<tr bgcolor="#cbe5fe" height="18">		
															<%}%>
												
																	<td width="7%">
																		<div align="center">
																			<input type="checkbox" name="idRegistrosClassificacao" value="<bean:write name="pagamento" property="id"/>"/>
																		</div>
																	</td>
																	<td width="11%">
																		<div align="center">
																			<bean:write name="pagamento" property="imovel.id" />
																		</div>
																	</td>
																	<td width="11%">
																		<div align="center">
																			<bean:write name="pagamento" property="dataPagamento" formatKey="date.format"/>
																		</div>
																	</td>
																	<td width="11%">
																		<div align="center">
																			R$<bean:write name="pagamento" property="valorPagamento" formatKey="money.format"/>
																		</div>
																	</td>
																	<td width="11%">
																		<div align="center">
																			<%= pagamento.getFormatarAnoMesParaMesAno() %>
																		</div>
																	</td>
																
																	<logic:equal name="PagamentosAClassificarActionForm" property="exibirMotivoCancelamento" value="true">
																	<td width="11%">
																		<div align="center">
																			<logic:notEmpty name="pagamento" property="contaGeral.conta">
																				<bean:write name="pagamento" property="contaGeral.conta.contaMotivoCancelamento.descricaoMotivoCancelamentoConta" />
																			</logic:notEmpty>
																			<logic:notEmpty name="pagamento" property="contaGeral.contaHistorico">
																				<bean:write name="pagamento" property="contaGeral.contaHistorico.contaMotivoCancelamento.descricaoMotivoCancelamentoConta" />
																			</logic:notEmpty>
																		</div>
																	</td>
																</logic:equal>
																</tr>
														</logic:iterate>
													</logic:present>
										</tr>
									</table>
									</div>
									
									<table id="tabelaBotoes" width="100%">
											<tr>
												<td>
													<gsan:controleAcessoBotao name="Button" value="Classificar"
														 		onclick="javascript:verficarSelecao(document.ClassificarPagamentosActionForm.idRegistrosClassificacao);" 
														 		url="classificarPagamentosAction.do"/>
													
													<input name="button" type="button" class="bottonRightCol" value="Voltar Filtro"
																onclick="window.location.href='<html:rewrite page="/exibirFiltrarPagamentosAClassificarAction.do?desfazer=N"/>'"
																align="left" style="width: 80px;"/>
													
													<input style="width: 70px" type="button" name="Button2" class="bottonRightCol"
																value="Devolver" onclick="devolver(document.forms[0]);" url="classificarPagamentosAction.do" 
																tabindex="12" id="desabilita"/>
															
													<logic:equal name="PagamentosAClassificarActionForm" property="exibirMotivoCancelamento" value="true">	
														<input style="width: 70px" type="button" name="Button2" class="bottonRightCol"
																value="Refaturar" onclick="refaturar(document.forms[0]);" url="classificarPagamentosAction.do" 
																tabindex="13" id="desabilita">
																
																
													</logic:equal>
												</td>
												
												<td align="right">      
													<div align="right">
														<a href="javascript:toggleBox('demodiv',1);">
															<img border="0" src="<bean:message key="caminho.imagens"/>print.gif" title="Imprimir Rotas" /> 
														</a>
													</div>
												</td>
											</tr>
										</table>
									
								</td>
							</tr>
					
						</table>
					</td>
			
				</tr>
			</table>
		
		
		</html:form>
	</body>

</html>
