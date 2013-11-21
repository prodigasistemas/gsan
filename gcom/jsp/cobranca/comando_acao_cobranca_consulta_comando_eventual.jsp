<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page
	import="gcom.util.Util,gcom.cobranca.CobrancaGrupoCronogramaMes,gcom.cobranca.CobrancaAcaoAtividadeComando"%>
<head>
<html:html>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript">
<!-- Begin
  
-->
</script>
</head>
<html:form action="/gerarRelatorioFiltrarComandoAcaoCobranca" method="post" >
<body leftmargin="5" topmargin="5">
<form><%@ include file="/jsp/util/cabecalho.jsp"%> <%@ include
	file="/jsp/util/menu.jsp"%>

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
			<table>
                <tbody><tr> 
                  <td></td>
                </tr>
              </tbody></table>
              <table align="center" border="0" cellpadding="0" cellspacing="0" width="100%">
                <tbody><tr> 
                  <td width="11"><img src="imagens/parahead_left.gif" border="0"></td>

                  <td class="parabg">Consulta Comandos de Ação de 
                    Cobrança Eventuais</td>
                  <td valign="top" width="11"><img src="imagens/parahead_right.gif" border="0"></td>
                </tr>
              </tbody></table> 
			<table width="100%" border="0">
			<tr>
				<td colspan="2"><strong>Comandos de A&ccedil;&atilde;o de
				Cobran&ccedil;a do Eventuais:</strong></td>
			</tr>
			<tr>
				<td colspan="2">
				<div align="left">
				<table width="100%" align="center" bgcolor="#99CCFF">
					<!--corpo da segunda tabela-->
					<tr bordercolor="#FFFFFF" bgcolor="#79BBFD">
						<td width="16%" rowspan="2">
						<div align="center"><strong>A&ccedil;&atilde;o de Cobran&ccedil;a</strong></div>
						</td>
						<td width="14%" rowspan="2">
						<div align="center"><strong>Atividade de Cobran&ccedil;a</strong></div>
						</td>
						<td colspan="2">
						<div align="center"><strong>Comando</strong></div>
						<div align="center"></div>
						</td>
						<td width="15%" rowspan="2">
						<div align="center"><strong>Crit&eacute;rio Utilizado</strong></div>
						</td>
						<td width="36%" rowspan="2">
						<div align="center"><strong>Crit&eacute;rio</strong></div>
						</td>
					</tr>
					<tr bordercolor="#FFFFFF" bgcolor="#79BBFD">
						<td bordercolor="#000000" bgcolor="#90c7fc">
						<div align="center"><strong>Data </strong></div>
						</td>
						<td bordercolor="#000000" bgcolor="#90c7fc">
						<div align="center"><strong>Hora</strong></div>
						</td>
					</tr>

					<%String cor = "#FFFFFF";%>

					<!--Fim Tabela Reference a Páginação da Tela de Processo-->
					<%--Esquema de paginação--%>

					<pg:pager isOffset="true" index="half-full" maxIndexPages="10"
						export="currentPageNumber=pageNumber;pageOffset"				
						maxPageItems="10" items="${sessionScope.totalRegistros}">

						<pg:param name="pg" />
						<pg:param name="q" />
						<logic:present name="colecaoCobrancaAcaoAtividadeEventual"
							scope="session">
							<logic:iterate name="colecaoCobrancaAcaoAtividadeEventual"
								id="cobrancaAcaoAtividadeComando">
								<pg:item>
									<%if (cor.equalsIgnoreCase("#cbe5fe")) {
										cor = "##FFFFFF";%>
									<tr bgcolor="#cbe5fe">
										<%} else {
										cor = "#cbe5fe";%>
									<tr bgcolor="#FFFFFF">
										<%}%>
										<td bordercolor="#90c7fc">
										<div align="center"><a          
											href="javascript:abrirPopup('exibirComandosAcaoCobrancaEventualDadosComandoAction.do?idCobrancaAcaoAtividadeEventual='+<bean:write name="cobrancaAcaoAtividadeComando" property="id" />, 550, 750);">
										<logic:present name="cobrancaAcaoAtividadeComando"
											property="cobrancaAcao">
											<bean:define name="cobrancaAcaoAtividadeComando"
												property="cobrancaAcao" id="cobrancaAcao" />
											<bean:write name="cobrancaAcao"
												property="descricaoCobrancaAcao" />
										</logic:present></a></div>
										</td>
										<td bordercolor="#90c7fc">
										<div align="center"><logic:present
											name="cobrancaAcaoAtividadeComando"
											property="cobrancaAtividade">

											<bean:define name="cobrancaAcaoAtividadeComando"
												property="cobrancaAtividade" id="cobrancaAtividade" />

											<bean:write name="cobrancaAtividade"
												property="descricaoCobrancaAtividade" />

										</logic:present></div>
										</td>

										<td width="9%" bordercolor="#90c7fc">
										<div align="center"><bean:write
											name="cobrancaAcaoAtividadeComando" format="dd/MM/yyyy"
											property="comando" /></div>
										</td>

										<td width="10%" bordercolor="#90c7fc">
										<div align="center"><bean:write
											name="cobrancaAcaoAtividadeComando" format="HH:mm:ss"
											property="comando" /></div>
										</td>

										<td bordercolor="#90c7fc">
										<div align="center"><%if (((CobrancaAcaoAtividadeComando) cobrancaAcaoAtividadeComando)
												.getIndicadorCriterio() != null) {
											if (((CobrancaAcaoAtividadeComando) cobrancaAcaoAtividadeComando)
													.getIndicadorCriterio().shortValue() == (short) 1) {
							
											%>Rota <%} else {
							
											%>Comando<%}
										}
										%></div>
										</td>
										<td bordercolor="#90c7fc">
										<div align="left"><logic:present
											name="cobrancaAcaoAtividadeComando"
											property="cobrancaCriterio">

											<bean:define name="cobrancaAcaoAtividadeComando"
												property="cobrancaCriterio" id="cobrancaCriterio" />

											<bean:write name="cobrancaCriterio"
												property="descricaoCobrancaCriterio" />

										</logic:present></div>
										</td>
									</tr>
								</pg:item>

							</logic:iterate>
						</logic:present>
				</table>
				<table width="100%" border="0">
					<tr>
						<td><div align="center"><strong><%@ include	file="/jsp/util/indice_pager_novo.jsp"%></strong></div>
						</td>
					</tr>
				</table>
				</div>
				</td>
			</tr>
			<tr>
				<td colspan="2"><strong></strong>
				<div align="left"><strong><font color="#FF0000"> </font></strong></div>
				</td>
			</tr>
			<tr>
				<td><input name="button" type="button" class="bottonRightCol"
					value="Voltar Filtro"
					onclick="window.location.href='<html:rewrite page="/exibirFiltrarComandosAcaoCobrancaEventualAction.do?tipoComando=Eventual&carregando=SIM"/>'"
					align="left" style="width: 80px;"></td>
				<td valign="top">
					<div align="right">
					<a href="javascript:toggleBox('demodiv',1);">
					<img border="0"	src="<bean:message key="caminho.imagens"/>print.gif" title="Imprimir Comandos de Ação de Cobrança das Eventuais" /> </a></div>
				</td>
			</tr>
			</pg:pager>
		</table>
		<p>&nbsp;</p>
		</td>
	</tr>
</table>

<jsp:include page="/jsp/relatorio/escolher_tipo_relatorio.jsp?relatorio=gerarRelatorioFiltrarComandoAcaoCobranca.do" />
<%@ include file="/jsp/util/rodape.jsp"%></form>
</html:form>
</body>
</html:html>
