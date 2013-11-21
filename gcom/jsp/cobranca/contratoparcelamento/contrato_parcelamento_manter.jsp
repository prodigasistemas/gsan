<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan" %>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>


<%@page isELIgnored="false"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>
<meta http-equiv="content-type" content="text/html; charset=iso-8859-1" />

<%@ include file="/jsp/util/titulo.jsp"%>
<link rel="stylesheet"	href="<bean:message key="caminho.css"/>popup.css"	type="text/css">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js">
</script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>
	
	
</head>



<body leftmargin="5" topmargin="5" onkeypress="return verificaCliqueEnter(event,'<c:out value="${etapa}"></c:out>');" >
<html:form action="/atualizarContratoParcelamentoClienteAction.do"
	name="AtualizarContratoParcelamentoPorClienteActionForm" 
	type="gcom.gui.cobranca.contratoparcelamento.AtualizarContratoParcelamentoPorClienteActionForm"
	method="post">
	
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
			<td valign="top" class="centercoltext">
					<!--In&iacute;cio Tabela Reference a P&aacute;gina&ccedil;&atilde;o da Tela de Processo-->
		            <table>
		              <tr>
		                <td></td>
		              </tr>
		            </table>
		            <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
		              <tr>
		                <td width="11"><img border="0" src="imagens/parahead_left.gif"/></td>
		                <td class="parabg"> Manter Contrato de Parcelamento por Cliente<strong></strong></td>
		                <td width="11" valign="top"><img border="0" src="imagens/parahead_right.gif"/></td>
		              </tr>
		            </table>
		            <!--Fim Tabela Reference a P&aacute;gina&ccedil;&atilde;o da Tela de Processo-->
					 <table width="100%" border="0">
			              <tr> 
			                <td colspan="2"> 
				                <table>
				                	<tr>
										<td colspan="2"><strong>Contratos de Parcelamento
										Cadastrados:</strong></td>
									</tr>
									<tr>
										<td height="97" colspan="2"><strong></strong>
										<div align="left">
										<table width="100%" align="center" bgcolor="#99CCFF">
											<!--corpo da segunda tabela-->
											<tr bordercolor="#FFFFFF" bgcolor="#79BBFD">
												<td width="12%">
												<div align="center"><strong>N&uacute;mero do
												Contrato</strong></div>
												</td>
												<td width="11%">
												<div align="center"><strong> Contrato</strong> <strong>Anterior</strong></div>
												</td>
												<td width="31%">
												<div align="center"><strong>Cliente</strong></div>
												</td>
												<td>
												<div align="center"><strong>Data de
												Implanta&ccedil;&atilde;o</strong></div>
												<div align="center"></div>
												<div align="center"></div>
												</td>
												<td>
												<div align="center"><strong>Situa&ccedil;&atilde;o</strong>
												<strong>Pagamento</strong></div>
												</td>
												<td>
												<div align="center"><strong>Cancelado</strong></div>
												</td>
											</tr>
											<%--Esquema de paginação--%>
											<pg:pager isOffset="true" index="half-full" maxIndexPages="10"
												export="currentPageNumber=pageNumber;pageOffset"
												maxPageItems="10" items="${sessionScope.totalRegistros}">
												<pg:param name="q" />
												
												
												<%int cont = 0;%>
												<logic:present name="collectionContratoParcelamentoCliente">
													<logic:iterate name="collectionContratoParcelamentoCliente" id="contratoParcelamentoCliente">
														<pg:item>
															<%cont = cont + 1;
														if (cont % 2 == 0) {%>
															<tr bgcolor="#cbe5fe">
																<%} else {
					
														%>
															<tr bgcolor="#FFFFFF">
																<%}%>
																<td>
																	<div align="center">
																		<a href="/gsan/manterContratoParcelamentoClienteAction.do?idClienteContrato=<c:out value="${contratoParcelamentoCliente.id}"></c:out>">
																			<c:out value="${contratoParcelamentoCliente.contrato.numero}"></c:out>
																		</a>
																	</div>
																</td>
																<td>
																	<div align="center">
																		<c:out value="${contratoParcelamentoCliente.contrato.contratoAnterior.numero}"></c:out>
																	</div>
																</td>
																<td>
																	<div align="center">
																		<c:out value="${contratoParcelamentoCliente.cliente.nome}"></c:out>
																	</div>
																</td>
																<td>
																	<div align="center">
																		<c:out value="${contratoParcelamentoCliente.contrato.dataContratoFormatada}"></c:out>
																	</div>
																</td>
																<td>
																	<div align="center">
																		<c:if test="${contratoParcelamentoCliente.contrato.valorParcelamentoACobrar != null && contratoParcelamentoCliente.contrato.valorParcelamentoACobrar > 0}">
																			Pendente
																		</c:if>
																		<c:if test="${contratoParcelamentoCliente.contrato.valorParcelamentoACobrar != null && contratoParcelamentoCliente.contrato.valorParcelamentoACobrar == 0}">
																			Pago
																		</c:if>
																	</div>
																</td>
																<td>
																	<div align="center">
																		<c:if test="${contratoParcelamentoCliente.contrato.motivoDesfazer != null}">
																			Sim
																		</c:if>
																		<c:if test="${contratoParcelamentoCliente.contrato.motivoDesfazer == null}">
																			Não
																		</c:if>
																	</div>
																</td>
															</tr>
														</pg:item>
													</logic:iterate>
												</logic:present>
										<table width="100%" border="0">
											<tr>
												<td>
												<div align="center"><strong><%@ include
													file="/jsp/util/indice_pager_novo.jsp"%></strong></div>
												</td>
												</pg:pager>
												<%-- Fim do esquema de paginação --%>
											</tr>
										</table>
											
											
										</table>
								
										</div>
										</td>
									</tr>
									<tr>
										<td width="233">&nbsp;</td>
										<td align="right">
										<div align="right"><strong> </strong></div>
										<div align="left"><strong></strong></div>
										<div align="center"></div>
										</td>
									</tr>
									<tr>
										<td><strong><font color="#FF0000"> <input
											type="button" name="Submit223" class="bottonRightCol"
											value="Novo Filtro" onclick="javascript:window.location.href='/gsan/exibirFiltrarContratoParcelamentoClienteAction.do?menu=sim'"
											>
										</font></strong></td>
										<td align="right">
										<div align="right"><strong> </strong><img onclick="javascript: toggleBox('demodiv',1);" style="cursor: pointer;"
									src="imagens/print.gif" alt="" width="28"
									height="26"></div>
										<div align="left"><strong></strong></div>
										<div align="left"></div>
										</td>
									</tr>
									<tr>
										<td colspan="2">
										<div align="center"></div>
										</td>
									</tr>
				                </table>	
			                </td>
			              </tr>
			             
			            </table>
					
			</td>
			
	</table>
	<jsp:include page="/jsp/relatorio/escolher_tipo_relatorio.jsp?relatorio=gerarRelatorioManterContratoParcelamentoPorClienteAction.do"/>
	<%@ include file="/jsp/util/rodape.jsp"%>
	</html:form>
</body>
</html:html>

