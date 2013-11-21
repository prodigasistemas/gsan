<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan"%>

<%@ page import="gcom.util.ConstantesSistema"%>
<%@ page import="gcom.cobranca.MotivoNaoAceitacaoEncerramentoOS" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>

<%@ include file="/jsp/util/titulo.jsp"%>

<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">

<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>

<SCRIPT LANGUAGE="JavaScript">
	function facilitador(objeto) {
		if (objeto.id == "0" || objeto.id == undefined) {
			objeto.id = "1";
			marcarTodos();
		} else {
			objeto.id = "0";
			desmarcarTodos();
		}
	}

	// Verifica se há item selecionado
	function verficarSelecao(objeto) {

		if (CheckboxNaoVazio(objeto)) {
			if (confirm("Confirma remoção?")) {
				document.forms[0].action = "/gsan/removerMotivoNaoAceitacaoEncerramentoOSAction.do"
				document.forms[0].submit();
			}
		}
	}
</SCRIPT>


</head>

<body leftmargin="5" topmargin="5">
	<html:form action="/exibirManterMotivoNaoAceitacaoEncerramentoOSAction" method="post">

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

				<td valign="top" class="centercoltext">

					<p>&nbsp;</p>

					<table width="100%" border="0" align="center" cellpadding="0"
						cellspacing="0">
						<tr>
							<td width="11">
								<img border="0" src="<bean:message key="caminho.imagens"/>parahead_left.gif" />
							</td>
							<td class="parabg">
								Manter Motivos de Não Aceitação de Encerramento de O.S.
							</td>
							<td width="11">
								<img border="0"	src="<bean:message key="caminho.imagens"/>parahead_right.gif" />
							</td>
						</tr>
					</table>



					<table width="590" border="0" align="center" cellpadding="0"
						cellspacing="0">
						<tr>
							<td colspan="4" height="23"><font color="#000000" style="font-size: 10px" 
								face="Verdana, Arial, Helvetica, sans-serif"> 
								<strong>Motivos Cadastrados:</strong> </font>
							</td>
							<td align="right">
							</td>
						</tr>
					</table>
					
					<table width="590" border="0" align="center" cellpadding="0" cellspacing="0">
						<tr>
							<td bgcolor="#000000" height="2"></td>
						</tr>
						<tr>
							<td>
								<table width="590" bgcolor="#99CCFF">
									<tr bgcolor="#99CCFF">
										<td align="center" width="10%">
											<A HREF="javascript:facilitador(this);" id="0"><strong>Todos</strong></A>
										</td>
										<td align="center" width="10%">
											<FONT COLOR="#000000"><strong>Código</strong></FONT>
										</td>
										<td align="center" width="40%">
											<FONT COLOR="#000000"><strong>Descrição</strong></FONT>
										</td>
										<td align="center" width="15%">
											<FONT COLOR="#000000"><strong>Quantidade 1</strong></FONT>
										</td>
										<td align="center" width="15%">
											<FONT COLOR="#000000"><strong>Quantidade 2</strong></FONT>
										</td>
										<td align="center" width="10%">
											<FONT COLOR="#000000"><strong>Percentual</strong></FONT>
										</td>
									</tr>
								</table>
							</td>
						</tr>
						
						<tr>
							<td>
								<table width="590" bgcolor="#99CCFF">
									<%String cor = "#cbe5fe";%>

									<%--Esquema de paginação--%>
									<pg:pager isOffset="true" index="half-full" maxIndexPages="10" export="currentPageNumber=pageNumber;pageOffset" 
										maxPageItems="10" items="${sessionScope.totalRegistros}">
										
										<pg:param name="pg" />
										<pg:param name="q" />

										<logic:iterate name="colecaoMotivos" id="motivoEncerramento" type="MotivoNaoAceitacaoEncerramentoOS">
											<pg:item>
												<%if (cor.equalsIgnoreCase("#cbe5fe")) {
													cor = "#FFFFFF";
												%>
												<tr bgcolor="#FFFFFF" height="18">
													<%} else {
														cor = "#cbe5fe";
													%>
												
												<tr bgcolor="#cbe5fe" height="18">
													<%
														}
													%>

													<td align="center" width="10%">
														<html:checkbox property="motivoNaoAceitacaoEncerramentoOSSelectID" 
															value="<%="" + motivoEncerramento.getId()%>" />
													</td>
													
													<td width="10%">
														<bean:write name="motivoEncerramento" property="id" />
													</td>
													
													<td width="40%">
														<html:link href="/gsan/exibirAtualizarMotivoNaoAceitacaoEncerramentoOSAction.do?primeiraVez=Ok"
															paramId="idRegistroAtualizar" paramProperty="id" paramName="motivoEncerramento"
															title="<%="" + motivoEncerramento.getDescricaoMotivoNaoAceitacaoEncerramentoOS() %>">
															<bean:write name="motivoEncerramento" 
																property="descricaoMotivoNaoAceitacaoEncerramentoOS" />
														</html:link>
													</td>

													<td width="15%">
														<bean:write name="motivoEncerramento" 
															property="multiplicadorValorServicoDescontarCorteSupressao" />
													</td>
													
													<td width="15%">
														<bean:write name="motivoEncerramento"
															property="multiplicadorValorServicoDescontarNaoExecutados" />
													</td>
													
													<td width="10%">
														<bean:write name="motivoEncerramento" property="percentualMultaAplicar" />
													</td>
												</tr>
											</pg:item>
										</logic:iterate>
								</table>
							</td>
						</tr>
						<tr bordercolor="#90c7fc">
							<td>
								<table width="100%">
							        <tr>
							        	<td>
							        		<input name="botaoRemover" type="button"
												class="bottonRightCol" value="Remover" tabindex="5"
												onClick="javascript:verficarSelecao(motivoNaoAceitacaoEncerramentoOSSelectID)"  />
										</td>
							        </tr>
						        </table>
							</td>
						</tr>
					</table>


					<table width="100%" border="0">

						<tr>
							<td>
								<div align="center">
									<strong> <%@ include
											file="/jsp/util/indice_pager_novo.jsp"%></strong>
								</div>
							</td>

							</pg:pager>
							<%-- Fim do esquema de paginação --%>
						</tr>

					</table>
				</td>
			</tr>
		</table>

		<%@ include file="/jsp/util/rodape.jsp"%>

	</html:form>
</body>
</html:html>