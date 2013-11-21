<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan" %>

<%@page import="gcom.atendimentopublico.registroatendimento.RaDadosAgenciaReguladora"%>
<%@page import="gcom.util.ConstantesSistema"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">


<html:html>

<head>

<%@ include file="/jsp/util/titulo.jsp"%>

<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">

<script language="JavaScript" src="<bean:message key="caminho.js"/>/validacao/ManutencaoRegistro.js"></script>

<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>


</head>

<body leftmargin="5" topmargin="5">

<html:form  action="/informarRaDadosAgenciaReguladoraAction.do" 
		name="ManutencaoRegistroActionForm" type="gcom.gui.ManutencaoRegistroActionForm">

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
		<td width="600" valign="top" class="centercoltext">
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
					<td class="parabg">Listar os Registros de Atendimento acompanhados pela Agencia Reguladora</td>
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
						<strong>Registros de Atendimento encontrados:</strong>
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
								<td width="12%" align="center">
									<strong>Número do RA</strong>
								</td>
								<td width="5%" align="center">
									<strong>Sit.</strong>
								</td>
								<td width="20%" align="center">
									<strong>Data Reclamação</strong>
								</td>
								<td width="17%" align="center">
									<strong>Data Prevista Original</strong>
								</td>
								<td width="16%" align="center">
									<strong>Data Prevista Atual</strong>
								</td>
								<td width="30%" align="center">
									<strong>Descrição do Motivo</strong>
								</td>
								</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td>
						<table width="100%" bgcolor="#99CCFF">
						<%--Esquema de paginação--%>
						<pg:pager isOffset="true" index="half-full" maxIndexPages="10" export="currentPageNumber=pageNumber;pageOffset"				
							maxPageItems="10" items="${sessionScope.totalRegistros}">
							<pg:param name="pg"/>
							<pg:param name="q"/>
							<logic:present name="colecaoRaDadosAgenciaReguladora">
								<%int cont = 0;%>
								<logic:iterate name="colecaoRaDadosAgenciaReguladora" 
											   id="raDadosAgenciaReguladora" type="RaDadosAgenciaReguladora">
									<pg:item>
										<%cont = cont + 1;
										if (cont % 2 == 0) {%>
										<tr bgcolor="#cbe5fe">
										<%} else {%>
										<tr bgcolor="#FFFFFF">
										<%}%>
											<td width="12%" align="center">
												<a href="/gsan/exibirConsultarRaDadosAgenciaReguladoraAction.do?idRa=<%=""+ raDadosAgenciaReguladora.getRegistroAtendimento().getId()%>&Listar=ok">
												<bean:write name="raDadosAgenciaReguladora" property="registroAtendimento.id"/></a>
											</td>
											<td width="5%" align="center">
												<logic:equal name="raDadosAgenciaReguladora" 
													value="<%=ConstantesSistema.SITUACAO_RA_AGENCIA_PENDENTE.toString()%>" 
													property="codigoSituacaoArpe">Pen.</logic:equal> 
												<logic:equal name="raDadosAgenciaReguladora" 
													value="<%=ConstantesSistema.SITUACAO_RA_AGENCIA_ENCERRADO.toString()%>" 
													property="codigoSituacaoArpe">Enc.</logic:equal> 
												<logic:equal name="raDadosAgenciaReguladora" 
													value="<%=ConstantesSistema.SITUACAO_RA_AGENCIA_BLOQUEADO.toString()%>" 
													property="codigoSituacaoArpe">Blq.</logic:equal>
											</td>	 
											<td width="20%" align="center">
												<bean:write name="raDadosAgenciaReguladora" property="dataReclamacao" formatKey="date.format"/>
											</td>
											<td width="17%" align="center">
												<bean:write name="raDadosAgenciaReguladora" property="dataPrevisaoOriginal" formatKey="date.format"/>
											</td>
											<td width="16%" align="center">
												<bean:write name="raDadosAgenciaReguladora" property="dataPrevisaoAtual" formatKey="date.format"/>
											</td>
											<td width="30%" align="center">
												<bean:write name="raDadosAgenciaReguladora" property="agenciaReguladoraMotReclamacao.descricao"/>
											</td>
										</tr>
									</pg:item>
								</logic:iterate>
							</logic:present>
						<%-- Fim do esquema de paginação --%>
						</table>
						<table width="100%">
							<tr>
								<td>
									<input name="button" type="button" class="bottonRightCol" tabindex="1" value="Voltar Filtro"
										onclick="window.location.href='<html:rewrite page="/exibirFiltrarRaDadosAgenciaReguladoraAction.do"/>'">
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

 <%@ include file="/jsp/util/rodape.jsp"%> 

</body>
</html:form>
</html:html>