<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>

<html:html>
<head>
<title>GSAN - Sistema Integrado de Gest&atilde;o de Servi&ccedil;os de Saneamento</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="PesquisarRegistroAtendimentoActionForm" dynamicJavascript="false" />
</head>

<body leftmargin="5" topmargin="5" onload="resizePageSemLink(790, 410);">
<table width="100%" border="0" cellspacing="5" cellpadding="0">
	<tr>
		<td width="100%" valign="top" class="centercoltext">
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
				<td class="parabg">Pesquisa de Registro de Atendimento</td>
                <td width="11" valign="top"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif"/></td>
              </tr>
            </table>
            <!-- Início do Corpo -->
            <p>&nbsp;</p>
            <table width="100%" cellpadding="0" cellspacing="0" border="0">
				<tr>
					<td colspan="11">
						<font color="#000000" style="font-size:10px" face="Verdana, Arial, Helvetica, sans-serif">
						<strong></strong> </font></td>
				</tr>
				<tr>
					<td colspan="11">&nbsp;</td>
				</tr>
				<tr>
					<td colspan="11">
						<table width="100%" bgcolor="#99CCFF">
			 				<tr bordercolor="#90c7fc" bgcolor="#90c7fc"> 
								<td width="9%" ><div align="center"><strong>N&uacute;mero do RA</strong></div></td>
				                <td width="35%"><div align="center"><strong>Especifica&ccedil;&atilde;o</strong></div></td>
						        <td width="9%" ><div align="center"><strong>Data Atendimento</strong></div></td>
				                <td width="9%" ><div align="center"><strong>Data Encerramento</strong></div></td>
				                <td width="3%" ><div align="center"><strong>Sit.</strong></div></td>
				                <td width="35%"><div align="center"><strong>Unidade Atual</strong></div></td>
							</tr>
							<pg:pager isOffset="true" maxIndexPages="10" export="currentPageNumber=pageNumber;pageOffset" 
									  index="half-full" maxPageItems="10" items="${sessionScope.totalRegistros}">
								<pg:param name="q" />
								<pg:param name="pg" />
								<%--Esquema de paginação--%>
								<c:set var="count" value="0"/>
								<logic:iterate name="colecaoRAHelper" id="helper">
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

										<logic:notEmpty name="caminhoRetornoTelaPesquisaRegistroAtendimento">
											<td bordercolor="#90c7fc">
					                        	<div align="center">
													<a title="Selecionar Registro de Atendimento" 
														href="javascript:enviarDadosParametros('<bean:write name="caminhoRetornoTelaPesquisaRegistroAtendimento"/>','<bean:write name="helper" property="registroAtendimento.id"/>', '<bean:write name="helper" property="registroAtendimento.solicitacaoTipoEspecificacao.descricao" />', 'registroAtendimento');">
														<bean:write name="helper" property="registroAtendimento.id" /></a>
												</div>
											</td>
										</logic:notEmpty>
			
										<logic:empty name="caminhoRetornoTelaPesquisaRegistroAtendimento">
											<td bordercolor="#90c7fc">
					                        	<div align="center">
														<a href="javascript:enviarDados('<bean:write name="helper" property="registroAtendimento.id"/>', '<bean:write name="helper" property="registroAtendimento.solicitacaoTipoEspecificacao.descricao"/>', 'registroAtendimento');">
															<bean:write name="helper" property="registroAtendimento.id" /></a>
												</div>
											</td>
										</logic:empty>

										<td bordercolor="#90c7fc">
				                        	<div align="center">
												<bean:write name="helper" property="registroAtendimento.solicitacaoTipoEspecificacao.descricao" /> 
											</div>	
										</td>
										<td bordercolor="#90c7fc">
				                        	<div align="center">
												<logic:notEmpty name="helper" property="registroAtendimento.registroAtendimento">
													<bean:write name="helper" property="registroAtendimento.registroAtendimento"  format="dd/MM/yyyy" /> 
												</logic:notEmpty>
											</div>	
										</td>
										<td bordercolor="#90c7fc">
				                        	<div align="center">
												<logic:notEmpty name="helper" property="registroAtendimento.dataEncerramento">
													<bean:write name="helper" property="registroAtendimento.dataEncerramento" format="dd/MM/yyyy" /> 
												</logic:notEmpty>
											</div>	
										</td>
										<td bordercolor="#90c7fc">
				                        	<div align="center">
												<logic:notEmpty name="helper" property="situacao">
													<bean:write name="helper" property="situacao" /> 
												</logic:notEmpty>
											</div>	
										</td>
										<td bordercolor="#90c7fc">
				                        	<div align="center">
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
												<input name="button" type="button" class="bottonRightCol" value="Voltar Pesquisa" onclick="window.location.href='<html:rewrite page="/exibirPesquisarRegistroAtendimentoAction.do"/>'" align="left"></td>
											<td valign="top">
												<div align="right"><a href="javascript:abrirPopupRelatorio('');">
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

</body>

</html:html>
