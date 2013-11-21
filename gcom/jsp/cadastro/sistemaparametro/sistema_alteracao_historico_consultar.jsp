<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan" %>

<%@page import="gcom.cadastro.sistemaparametro.SistemaAlteracaoHistorico"%>

<%@page import="gcom.util.ConstantesSistema"%>

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


</head>

<body leftmargin="5" topmargin="5">

<html:form action="/exibirConsultarSistemaAlteracaoHistoricoAction">

<%@ include file="/jsp/util/cabecalho.jsp"%>
<%@ include file="/jsp/util/menu.jsp"%>

<table width="770" border="0" cellspacing="5" cellpadding="0">
	<tr>
		<td width="130" valign="top" class="leftcoltext">
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
		<td width="625" valign="top" class="centercoltext">
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
					<td class="parabg">Consultar Histórico de Alterações do Sistema </td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
				<tr>
					<td height="5" colspan="3"></td>
				</tr>
			</table>
	
			<table width="100%" cellpadding="0" cellspacing="0">
				<tr>
					<td colspan="4" height="23">
						<font color="#000000" style="font-size:10px" face="Verdana, Arial, Helvetica, sans-serif">
						<strong>Histórico de alterações do sistema cadastradas:</strong>
						</font>
					</td>
				</tr>
	  		</table>
			
	  		<table width="100%" cellpadding="0" cellspacing="0">
				<tr>
					<td colspan="4" bgcolor="#000000" height="2"></td>
				</tr>
				<tr>
					<td>
						<table width="100%" bgcolor="#99CCFF">
							<!--header da tabela interna -->
							<tr bgcolor="#99CCFF">
								<td width="60%" align="center">
									<strong>Título da alteração</strong>
								</td>
								<td width="30%" align="center">
									<strong>Data de Alteração</strong>
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
							<logic:present name="colecaoSistemaAlteracaoHistorico">
								<%int cont = 0;%>
								<logic:iterate name="colecaoSistemaAlteracaoHistorico" id="sistemaAlteracaoHistorico" type="SistemaAlteracaoHistorico">
									<pg:item>
										<%cont = cont + 1;
										if (cont % 2 == 0) {%>
										<tr bgcolor="#cbe5fe">
										<%} else {%>
										<tr bgcolor="#FFFFFF">
										<%}%>
											<td width="60%" align="left">
												<a href="javascript:abrirPopup('/gsan/exibirSistemaHistAlteracaoDetalharPopupAction.do?idSistemaAlteracaoHistorico=${sistemaAlteracaoHistorico.id}',280, 537)"><bean:write name="sistemaAlteracaoHistorico" property="nome"/></a>
											</td>
											<td width="30%" align="center">
												<bean:write name="sistemaAlteracaoHistorico" property="data" formatKey="date.format"/>
											</td>
										</tr>
									</pg:item>
								</logic:iterate>
							</logic:present>
					<%-- Fim do esquema de paginação --%>
						</table>
						
						<table width="100%">
							<logic:present name="Filtrar" scope="request">
								<tr>
									<td width="40%" align="left">
										<input type="button"name="ButtonCancelar" class="bottonRightCol" value="Voltar Filtro" 
										onclick="javascript:window.location.href='/gsan/exibirFiltrarSistemaAlteracaoHistoricoAction.do'">
									</td>
								</tr>
							</logic:present>
								
							<logic:notPresent  name="Filtrar" scope="request">
								<tr>
									<td width="40%" align="left">
										<input type="button"name="ButtonCancelar" class="bottonRightCol" value="Voltar Filtro" 
										onClick="javascript:window.location.href='/gsan/telaPrincipal.do'">
									</td>
								</tr>
							</logic:notPresent>
						</table>
						
						<table width="100%">
							<tr>
								<td align="right" valign="top">
                                	<a href="javascript:toggleBox('demodiv',1);">
                                    	<img align="right" border="0" src="<bean:message key='caminho.imagens'/>print.gif"  title="Imprimir Materiais"/></a>
                                </td>
							</tr>
						</table>
						
						<table width="100%" border="0">
							<tr>
								<td align="center">
									<strong><%@ include file="/jsp/util/indice_pager_novo.jsp"%></strong>
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>		
		</pg:pager>
		<p>&nbsp;</p>
		</td>
	</tr>
</table>
<%--<jsp:include page="/jsp/relatorio/escolher_tipo_relatorio.jsp?relatorio=gerarRelatorioMaterialManterAction.do"/>--%>
 <%@ include file="/jsp/util/rodape.jsp"%> 
</html:form>

</body>

</html:html>