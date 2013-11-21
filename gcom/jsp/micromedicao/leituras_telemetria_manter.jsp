<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>


<%@page import="gcom.micromedicao.TelemetriaMovReg"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">


<%@page import="gcom.micromedicao.TelemetriaMovReg;"%><html:html>

<head>

<%@ include file="/jsp/util/titulo.jsp"%>

<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
	
<html:javascript staticJavascript="false" formName="ManutencaoRegistroActionForm" />
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript">
function facilitador(objeto){

	if (objeto.id == "0" || objeto.id == undefined){
		objeto.id = "1";
		marcarTodos();
	}
	else{
		objeto.id = "0";
		desmarcarTodos();
	}
}

</script>

</head>

<body leftmargin="5" topmargin="5">

<html:form action="/manterLeiturasTelemetriaAction"
	name="ManutencaoRegistroActionForm"
	type="gcom.gui.ManutencaoRegistroActionForm"
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
				<table>
					<tr>
						<td></td>
					</tr>
				</table>
				<!--Início Tabela Reference a Páginação da Tela de Processo-->
				<table>
					<tr>
						<td></td>
					</tr>
				</table>
				<table width="100%" border="0" align="center" cellpadding="0"
					cellspacing="0">
					<tr>
						<td width="11"><img border="0" src="imagens/parahead_left.gif" />
						</td>
						<td class="parabg">Consultar Leituras Transmitidas Via Telemetria</td>
						<td width="11" valign="top"><img border="0"
							src="imagens/parahead_right.gif" /></td>
					</tr>
				</table>
				<!--Fim Tabela Reference a Páginação da Tela de Processo-->
				<table width="100%" border="0">				
					<tr>
						<td colspan="6" bgcolor="#000000" height="2"></td>
					</tr>
					<tr>
						<td>
							<table width="100%" bgcolor="#99CCFF">
								<!--header da tabela interna -->
								<tr bgcolor="#99CCFF">
									<td width="5%" align="center">
										<strong>
										<a onclick="this.focus();" id="0" href="javascript:facilitador(this);">Todos</a>
										</strong>
									</td>
									<td width="20%" align="center">
										<strong>Inscrição</strong>
									</td>
									<td width="15%" align="center">
										<strong>Matrícula</strong>
									</td>
									<td width="20%" align="center">
										<strong>Data/Hora Consumo</strong>
									</td>
									<td width="15%" align="center">
										<strong>Consumo</strong>
									</td>
									<td width="15%" align="center">
										<strong>Hidrômetro</strong>
									</td>
									<td width="5%" align="center">
										<strong>Erro</strong>
									</td>
								</tr>
							</table>
						</td>
					</tr>
					<tr>
						<td>
						<table width="100%" bgcolor="#99CCFF">
							<%--Esquema de paginação--%>
						<pg:pager isOffset="true" index="half-full" maxIndexPages="10"
							export="currentPageNumber=pageNumber;pageOffset"				
							maxPageItems="10" items="${sessionScope.totalRegistros}">
							<pg:param name="pg"/>
							<pg:param name="q"/>
							<logic:present name="colecao">
								<%int cont = 0;%>
								<logic:iterate name="colecao" id="telemetriaMovReg" type="TelemetriaMovReg">
									<pg:item>
										<%cont = cont + 1;
										if (cont % 2 == 0) {%>
										<tr bgcolor="#cbe5fe">
										<%} else {%>
										<tr bgcolor="#FFFFFF">
										<%}%>
											<td width="5%" align="center">
											<logic:equal name="telemetriaMovReg" property="indicadorProcessado" value="1">
												<input type="checkbox" disabled="disabled" name="idRegistrosAutorizar" value="<bean:write name="telemetriaMovReg" property="id"/>"/>
											</logic:equal>
											<logic:equal name="telemetriaMovReg" property="indicadorProcessado" value="2">
												<input type="checkbox" name="idRegistrosAutorizar" value="<bean:write name="telemetriaMovReg" property="id"/>"/>
											</logic:equal>
											</td>
											<td width="20%" align="center">
												<bean:write name="telemetriaMovReg" property="inscricao"/>
											</td>
											<td width="20%" align="center">
												<bean:write name="telemetriaMovReg" property="imovel.id"/>
											</td>
											<td width="20%" align="center">
												<bean:write name="telemetriaMovReg" property="dataLeitura" format="dd/MM/yyyy HH:mm:ss"/>
											</td>
											<td width="15%" align="center">
												<bean:write name="telemetriaMovReg" property="leitura"/>
											</td>
											<td width="15%" align="center">
												<bean:write name="telemetriaMovReg" property="numeroHidrometro"/>
											</td>
											<td width="5%" align="center">
												<logic:equal name="telemetriaMovReg" property="indicadorProcessado" value="2">
													<div align="center">
													<input type="button" name="Log" value="Log" 
													class="bottonRightCol" 
													onclick="javascript:window.open('exibirExcecaoTelemetriaIniciadaAction.do?idTelemetria=<bean:write name="telemetriaMovReg" property="id"/>','mywindow','width=700,height=700,toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=yes,copyhistory=no,resizable=yes');"/>
													</div>
												</logic:equal>
												<logic:notEqual name="telemetriaMovReg" property="indicadorProcessado" value="2">
													<div align="center">&nbsp;</div>
												</logic:notEqual>
											</td>						
										</tr>
									</pg:item>
								</logic:iterate>
							</logic:present>
						<%-- Fim do esquema de paginação --%>
						</table>
						<table width="100%" border="0">
							<tr>
								<td align="center">
									<strong><%@ include file="/jsp/util/indice_pager_novo.jsp"%></strong>
								</td>
							</tr>
						</table>
						</pg:pager>
						</td>				
					</tr>
					<tr>
						<td>
						<table width="100%">
							<tr>
								<td align="left" valign="top">							
									<logic:present scope="session" name="existeLeiturasNaoProcessadas">						
										<logic:equal name="existeLeiturasNaoProcessadas" value="true">
                                			<input type="button"
												name="buttonFiltro" class="bottonRightCol" value="Reprocessar"
												onClick="submeterFormPadrao(document.forms[0]);">
										</logic:equal>
										<logic:equal name="existeLeiturasNaoProcessadas" value="false">
                                			<input type="button" disabled="disabled"
												name="buttonFiltro" class="bottonRightCol" value="Reprocessar"
												onClick="submeterFormPadrao(document.forms[0]);">
										</logic:equal>
									</logic:present>
									<logic:notPresent scope="session" name="existeLeiturasNaoProcessadas">
                                		<input type="button"
											name="buttonFiltro" class="bottonRightCol" value="Reprocessar"
											onClick="submeterFormPadrao(document.forms[0]);">
									</logic:notPresent>
									<input type="button"
										name="buttonFiltro" class="bottonRightCol" value="Voltar Filtro"
										onClick="javascript:window.location.href='/gsan/exibirFiltrarLeiturasTelemetriaAction.do?menu=sim'">
								</td>		
								<td align="right" valign="top">
                                	<a href="javascript:toggleBox('demodiv',1);">
                                    <img align="right" border="0" src="<bean:message key='caminho.imagens'/>print.gif"  title="Imprimir"/>
                                    </a>
                                </td>
							</tr>
						</table>
						</td>
					</tr>	
			</table>
			</td>
		</tr>
	</table>
	<jsp:include page="/jsp/relatorio/escolher_tipo_relatorio.jsp?relatorio=gerarRelatorioLeiturasTelemetriaAction.do"/>
	<%@ include file="/jsp/util/rodape.jsp"%>
</html:form>
</body>
</html:html>