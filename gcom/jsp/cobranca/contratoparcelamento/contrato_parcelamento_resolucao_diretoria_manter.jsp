<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan" %>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>

<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>/validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js">
</script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>

<script language="JavaScript">

function todos() {
	marcarTodos();
}

</script>
</head>



<body leftmargin="5" topmargin="5">

<%@ include file="/jsp/util/cabecalho.jsp"%>
<%@ include file="/jsp/util/menu.jsp"%>


<html:form action="/removerResolucaoDiretoriaContratoParcelamentoAction"
	name="ExcluirResolucaoDiretoriaContratoParcelamentoActionForm"
	type="gcom.gui.cobranca.contratoparcelamento.ExcluirResolucaoDiretoriaContratoParcelamentoActionForm"
	method="post"
	onsubmit="return CheckboxNaoVazio(document.forms[0].ids) && confirm('Confirma remoção?')">
					
					
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


		<td valign="top" class="centercoltext"><!--Início Tabela Reference a Páginação da Tela de Processo-->
		<table>
			<tr>
				<td></td>
			</tr>
		</table>
		<table width="100%" border="0" align="center" cellpadding="0"
			cellspacing="0">
			<tr>
				<td width="11"><img border="0" src="imagens/parahead_left.gif" /></td>
				<td class="parabg">Manter Resolu&ccedil;&atilde;o de Diretoria para
				Contrato de Parcelamento por Cliente</td>
				<td width="11" valign="top"><img border="0"
					src="imagens/parahead_right.gif" /></td>
			</tr>
		</table>
		<!--Fim Tabela Reference a Páginação da Tela de Processo-->
		<p>&nbsp;</p>
		<table width="100%" border="0">
			<tr>
				<td colspan="2" width="100%"><strong>Resolu&ccedil;&otilde;es de Diretoria
				Cadastradas:</strong></td>
			</tr>
			<tr>
				<td colspan="2"><strong></strong>
				<div align="left">
				<table width="100%" align="center" bgcolor="#99CCFF">
					<!--corpo da segunda tabela-->
					<tr bordercolor="#FFFFFF" bgcolor="#79BBFD">
						<td width="6%" rowspan="2">
						<div align="center"><strong><u style="cursor: pointer;" onclick="javascript: todos();">Todos</u></strong></div>
						</td>
						<td width="16%" rowspan="2">
						<div align="center"><strong>N&uacute;mero da RD</strong></div>
						</td>
						<td width="40%" rowspan="2">
						<div align="center"><strong>Assunto da RD</strong></div>
						</td>
						<td colspan="2">
						<div align="center"><strong>Per&iacute;odo </strong><strong>de
						Vig&ecirc;ncia da RD</strong></div>
						</td>
					</tr>
					<tr bordercolor="#FFFFFF" bgcolor="#79BBFD">
						<td width="21%">
						<div align="center"><strong>In&iacute;cio</strong></div>
						</td>
						<td width="17%">
						<div align="center"><strong>T&eacute;rmino</strong></div>
						</td>
					</tr>
					
					
					<%--Esquema de paginação--%>
						<pg:pager isOffset="true" index="half-full" maxIndexPages="10"
							export="currentPageNumber=pageNumber;pageOffset"
							maxPageItems="10" items="${sessionScope.totalRegistros}">
							<pg:param name="q" />
							
							
							<%int cont = 0;%>
							<logic:present name="collContratoParcelamentoRD">
								<logic:iterate name="collContratoParcelamentoRD" id="contratoParcelamentoRD">
									<pg:item>
										<%cont = cont + 1;
									if (cont % 2 == 0) {%>
										<tr bgcolor="#cbe5fe">
											<%} else {

									%>
										<tr bgcolor="#FFFFFF">
											<%}%>
											<td>
											<div align="center"><strong> <input type="checkbox"
												name="ids"
												value="<bean:write name="contratoParcelamentoRD" property="id"/>" /> </strong></div>
											</td>
											<td>
											<div align="center">
												<a href="/gsan/exibirAtualizarResolucaoDiretoriaContratoParcelamentoAction.do?numeroContratoParcelamentoRD=<c:out value="${contratoParcelamentoRD.numero}"></c:out>">
													<c:out value="${contratoParcelamentoRD.numero}"></c:out>
												</a>
											</div>
											</td>
											<td>
												<div align="center"><bean:write name="contratoParcelamentoRD"
													property="assunto" /></div>
											</td>
											<td>
												<div align="center"><bean:write name="contratoParcelamentoRD"
													property="dataVigenciaInicio" formatKey="date.format" /></div>
											</td>
											<td>
												<div align="center"><bean:write name="contratoParcelamentoRD"
													property="dataVigenciaFinal" formatKey="date.format" /></div>
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
					
							<tr>
								<td width="233">&nbsp;</td>
								<td align="right">
								<div align="right"><strong> </strong></div>
								<div align="left"><strong></strong></div>
								<div align="center"></div>
								</td>
							</tr>
							<tr>
								<td><strong><font color="#FF0000"> <input type="submit"
									name="Submit222" class="bottonRightCol" value="Remover"> </font></strong><strong><font
									color="#FF0000"> <input type="button" name="novoFiltro"
									class="bottonRightCol" value="Novo Filtro"
									onClick="javascript:window.location.href='/gsan/exibirFiltrarResolucaoDiretoriaContratoParcelamentoAction.do?menu=sim'">
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
			</tr>
			
			
			
		</table>
	</tr>
	
		<jsp:include page="/jsp/relatorio/escolher_tipo_relatorio.jsp?relatorio=gerarRelatorioResolucaoDiretoriaContratoParcelamentoAction.do"/> 
		<%@ include file="/jsp/util/rodape.jsp"%> 
		</html:form>
		
		</body>
</html:html>