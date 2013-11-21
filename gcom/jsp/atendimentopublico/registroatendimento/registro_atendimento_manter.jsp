<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
<head>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
	
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js">
		
</script>

<script language="JavaScript">

function mudaCorUrgencia(){
	css_color_change('1','red');					
}

</script>

</head>
<body leftmargin="5" topmargin="5" onload="javaScript:mudaCorUrgencia();">

<html:form action="/gerarRelatorioFiltrarRegistroAtendimentoAction" method="post" >

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
            <table>
              <tr><td></td></tr>
            </table>
            <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
              <tr>
                <td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_left.gif"/></td>
                <td class="parabg">Manter Registro de Atendimento</td>
                <td width="11" valign="top"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif"/></td>
              </tr>
            </table>
            <!-- Início do Corpo -->
            <p>&nbsp;</p>
            <table width="100%" cellpadding="0" cellspacing="0" border="0" >
				<tr>
					<td colspan="11">
						<font color="#000000" style="font-size:10px" face="Verdana, Arial, Helvetica, sans-serif">
						<strong>Total de Registros de Atendimento encontrados: ${sessionScope.totalRegistros}</strong> </font></td>
					<logic:present scope="application" name="urlHelp">
						<td align="right"><a href="javascript:abrirPopupHelp('${applicationScope.urlHelp}atendimentoRegistroManter', 500, 700);"><span style="font-weight: bold"><font color="#3165CE">Ajuda</font></span></a></td>									
					</logic:present>
					<logic:notPresent scope="application" name="urlHelp">
						<td align="right"><span style="font-weight: bold"><font color=#696969><u>Ajuda</u></font></span></td>									
					</logic:notPresent>
							
				</tr>
				<tr>
					<td colspan="11">&nbsp;</td>
				</tr>
				<tr>
					<td colspan="12">
						<table width="100%" bgcolor="#99CCFF">
			 				<tr bordercolor="#90c7fc" bgcolor="#90c7fc"> 
								<td width="9%" ><div align="center"><strong>N&uacute;mero do RA</strong></div></td>
			                    <td width="38%"><div align="center"><strong>Especifica&ccedil;&atilde;o</strong></div></td>
            			        <td width="9%" ><div align="center"><strong>Data Atendimento</strong></div></td>
			                    <td width="9%" ><div align="center"><strong>Data Encerramento</strong></div></td>
			                    <td width="3%" ><div align="center"><strong>Sit.</strong></div></td>
			                    <td width="7%" ><div align="center"><strong>Perfil do Imóvel</strong></div></td>
			                    <td width="25%"><div align="center"><strong>Unidade Atual</strong></div></td>
			                </tr>
			                <tr>
							<pg:pager isOffset="true" maxIndexPages="10" export="currentPageNumber=pageNumber;pageOffset" 
									  index="half-full" maxPageItems="10" items="${sessionScope.totalRegistros}">
								<pg:param name="q" />
								<pg:param name="pg" />
								<%--Esquema de paginação--%>
								<c:set var="count" value="0"/>
								<logic:iterate name="colecaoRAHelper" id="helper" >									
									<pg:item>
			                      		<c:set var="count" value="${count+1}"/>
				                        <c:choose>
		                        			<c:when test="${count%2 == '1'}">
		                        				<tr bgcolor="#FFFFFF">
		                        			</c:when>
		                        			<c:otherwise>
		                        				<tr bgcolor="#cbe5fe">
		                        			</c:otherwise>
		                        		</c:choose>					
		                        								
										<td bordercolor="#90c7fc" >
				                        	<div align="center" >
				                        		<a href="javascript:window.location.href='<html:rewrite page="/exibirConsultarRegistroAtendimentoAction.do?numeroRA=${helper.registroAtendimento.id}"/>';" 
				                        		   title="Consultar Registro de Atendimento" onmouseover="window.status='Consultar Registro de Atendimento'; return true"
				                        		   onmouseout="window.status=''; return true" class="${helper.indicadorUrgencia}">
													<bean:write name="helper" property="registroAtendimento.id" /> 
												</a>
											</div>
										</td>
										
										<logic:equal name="helper" property="indicadorUrgencia" value="1">
											<logic:present name="helper" property="hint1">
												<td bordercolor="#90c7fc" class="${helper.indicadorUrgencia}" valign="center" 
												onmouseover="this.T_BGCOLOR='whitesmoke';this.T_RIGHT=true;return escape('<bean:write name="helper" property="hint1"/>');">
													<div>
														<bean:write name="helper" property="registroAtendimento.solicitacaoTipoEspecificacao.descricao" /> 
													</div>	
												</td>
											</logic:present>
											
											<logic:notPresent name="helper" property="hint1">
											<td bordercolor="#90c7fc" class="${helper.indicadorUrgencia}">
												<div>
													<bean:write name="helper" property="registroAtendimento.solicitacaoTipoEspecificacao.descricao" /> 
												</div>	
						                        		
											</td>
											</logic:notPresent>	
										</logic:equal>
										
										<logic:equal name="helper" property="indicadorUrgencia" value="2">
											<td bordercolor="#90c7fc" class="${helper.indicadorUrgencia}">
												<div>
													<bean:write name="helper" property="registroAtendimento.solicitacaoTipoEspecificacao.descricao" /> 
												</div>	
						                        		
											</td>
										</logic:equal>
											
								      		
								      		
								      		
								      		
										<td bordercolor="#90c7fc" class="${helper.indicadorUrgencia}">
				                        	<div align="center">
												<logic:notEmpty name="helper" property="registroAtendimento.registroAtendimento">
													<bean:write name="helper" property="registroAtendimento.registroAtendimento"  format="dd/MM/yyyy" /> 
												</logic:notEmpty>
											</div>	
										</td>
										<td bordercolor="#90c7fc" class="${helper.indicadorUrgencia}">
				                        	<div align="center">
												<logic:notEmpty name="helper" property="registroAtendimento.dataEncerramento">
													<bean:write name="helper" property="registroAtendimento.dataEncerramento" format="dd/MM/yyyy" /> 
												</logic:notEmpty>
											</div>	
										</td>
										<td bordercolor="#90c7fc" class="${helper.indicadorUrgencia}">
				                        	<div align="center">
												<logic:notEmpty name="helper" property="situacao">
													<bean:write name="helper" property="situacao" /> 
												</logic:notEmpty>
											</div>	
										</td>
										<td bordercolor="#90c7fc" class="${helper.indicadorUrgencia}">
				                        	<div>
												<logic:notEmpty name="helper" property="perfilImovel">
													<bean:write name="helper" property="perfilImovel.descricao" />
												</logic:notEmpty>
											</div>	
										</td>
										<td bordercolor="#90c7fc" class="${helper.indicadorUrgencia}">
				                        	<div>
												<logic:notEmpty name="helper" property="unidadeAtual">
													<bean:write name="helper" property="unidadeAtual.descricao" />
												</logic:notEmpty>
											</div>	
										</td>
									
									</tr>
								</pg:item>								
							</logic:iterate>
						</table>
						<%-- Fim do esquema de paginação --%>
						<table align="center">
							<tr align="center">
								<td align="center">
									<div align="center">
										<strong><%@ include	file="/jsp/util/indice_pager_novo.jsp"%>
										</strong>
									</div>
								</td>
							</tr>
						</table>
						</pg:pager>
					</td>
				</tr>
                <tr>
					<td colspan="5">
						<table width="100%" border="0">
							<tr>
								<td colspan="5">
									<table width="100%" border="0">
										<tr>
											<td valign="top">
												<input name="button" type="button" class="bottonRightCol" value="Voltar Filtro" onclick="window.location.href='<html:rewrite page="/exibirFiltrarRegistroAtendimentoAction.do"/>'" align="left" style="width: 80px;"></td>
											<td valign="top">
												<div align="right"><a href="javascript:toggleBox('demodiv',1);">
												<img border="0"	src="<bean:message key="caminho.imagens"/>print.gif" title="Imprimir Registros de Atendimento" /> </a></div>
											</td>
										</tr>
									</table>
								</td>
							</tr>
						</table>
					</td>
				</tr>
 			</table>
			<!-- Fim do Corpo -->
		</td>
	</tr>
</table>		
<p>&nbsp;</p>
<jsp:include page="/jsp/relatorio/escolher_tipo_relatorio.jsp?relatorio=gerarRelatorioFiltrarRegistroAtendimentoAction.do" />
<%@ include file="/jsp/util/rodape.jsp"%>

</body>
<%@ include file="/jsp/util/tooltip.jsp"%>
</html:form>
</html:html>