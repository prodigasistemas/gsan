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
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="PesquisarOrdemServicoActionForm" dynamicJavascript="false" />
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
				<td class="parabg">Pesquisa de Ordem de Servi&ccedil;o</td>
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
								<td width="8%" ><div align="center"><strong>Ordem de Servi&ccedil;o</strong></div></td>
				                <td width="28%"><div align="center"><strong>Tipo de Servi&ccedil;o</strong></div></td>
						        <td width="9%" ><div align="center"><strong>N&uacute;mero do RA</strong></div></td>
				                <td width="9%" ><div align="center"><strong>Im&oacute;vel</strong></div></td>
				                <td width="14%" ><div align="center"><strong>Sit.</strong></div></td>
			                  	<td width="5%"><div align="center"><strong>Data de Gera&ccedil;&atilde;o</strong></div></td>
			                  	<td width="13%"><div align="center"><strong>Data de Encerramento</strong></div></td>
				                <td width="14%"><div align="center"><strong>Unidade Atual</strong></div></td>
							</tr>
							<tr>
								<%--Esquema de paginação--%>
								<pg:pager isOffset="true" index="half-full" maxIndexPages="10"
										  export="currentPageNumber=pageNumber;pageOffset"
							             maxPageItems="10" items="${sessionScope.totalRegistros}">
									<pg:param name="q" />
									<pg:param name="pg"/>
									<logic:present name="colecaoOSHelper">
									<%--Esquema de paginação--%>
									<c:set var="count" value="0"/>
									<logic:iterate name="colecaoOSHelper" id="helper">
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
												<td bordercolor="#90c7fc">
						                        	<div align="center">
						                        		
						                        		<logic:present name="caminhoRetornoResultadoPesquisaOS">
						                        			
						                        			<a  title="Consultar Dados da Ordem de Serviço" href="javascript:window.location.href='<html:rewrite page="/exibirConsultarDadosOrdemServicoPopupAction.do?numeroOS=${helper.ordemServico.id}"/>&caminhoTelaPesquisaRetorno=${sessionScope.caminhoRetornoResultadoPesquisaOS}';">
																<bean:write name="helper" property="ordemServico.id" />
															</a>
														
						                        		</logic:present>
						                        		
						                        		<logic:notPresent name="caminhoRetornoResultadoPesquisaOS">
						                        			
						                        			<a  title="Consultar Dados da Ordem de Serviço" href="javascript:window.location.href='<html:rewrite page="/exibirConsultarDadosOrdemServicoPopupAction.do?numeroOS=${helper.ordemServico.id}"/>';">
																<bean:write name="helper" property="ordemServico.id" />
															</a>
															
						                        		</logic:notPresent>
						                        		
													</div>
												</td>

												<td bordercolor="#90c7fc">
						                        	<div align="center">
													<logic:notEmpty name="helper" property="ordemServico.servicoTipo">

														<logic:notEmpty name="caminhoRetornoTelaPesquisaOrdemServico">
															<a href="javascript:enviarDadosParametros('<bean:write name="caminhoRetornoTelaPesquisaOrdemServico"/>','<bean:write name="helper" property="ordemServico.id"/>', '<bean:write name="helper" property="ordemServico.servicoTipo.descricao"/>', 'ordemServico');">
																<bean:write name="helper" property="ordemServico.servicoTipo.descricao"/>
															</a>
														</logic:notEmpty>
							
														<logic:empty name="caminhoRetornoTelaPesquisaOrdemServico">
															<a href="javascript:enviarDados('<bean:write name="helper" property="ordemServico.id"/>', '<bean:write name="helper" property="ordemServico.servicoTipo.descricao"/>', 'ordemServico');">
																<bean:write name="helper" property="ordemServico.servicoTipo.descricao"/> 
															</a>
														</logic:empty>

													</logic:notEmpty>
													</div>	
												</td>

												<td bordercolor="#90c7fc">
						                        	<div align="center">
														<logic:notEmpty name="helper" property="ordemServico.registroAtendimento">
						                        		
						                        			<logic:present name="caminhoRetornoResultadoPesquisaOS">
								                        	
								                        		<a  title="Consultar Dados do Registro de Atendimento" href="javascript:window.location.href='<html:rewrite page="/exibirConsultarRegistroAtendimentoPopupAction.do?numeroRA=${helper.ordemServico.registroAtendimento.id}"/>&caminhoTelaPesquisaRetorno=${sessionScope.caminhoRetornoResultadoPesquisaOS}';">
																	<bean:write name="helper" property="ordemServico.registroAtendimento.id" /> 
																</a>
															
															</logic:present>
															
															<logic:notPresent name="caminhoRetornoResultadoPesquisaOS">
															
																<a  title="Consultar Dados do Registro de Atendimento" href="javascript:window.location.href='<html:rewrite page="/exibirConsultarRegistroAtendimentoPopupAction.do?numeroRA=${helper.ordemServico.registroAtendimento.id}"/>';">
																	<bean:write name="helper" property="ordemServico.registroAtendimento.id" /> 
																</a>
																
															</logic:notPresent>
															
														</logic:notEmpty>
													</div>	
												</td>

												<td bordercolor="#90c7fc">
						                        	<div align="center">
														<logic:notEmpty name="helper" property="imovel">
															<bean:write name="helper" property="imovel.id"/> 
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
														<logic:notEmpty name="helper" property="ordemServico.dataGeracao">
															<bean:write name="helper" property="ordemServico.dataGeracao"  format="dd/MM/yyyy" /> 
														</logic:notEmpty>
													</div>	
												</td>
												<td bordercolor="#90c7fc">
						                        	<div align="center">
														<logic:notEmpty name="helper" property="ordemServico.dataEncerramento">
															<bean:write name="helper" property="ordemServico.dataEncerramento" format="dd/MM/yyyy" /> 
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
									</logic:present>
							</table>
			
							</td>
						</tr>

		</table>
		<table width="100%" border="0">
			<tr>
				<td>
					<div align="center">
						<strong><%@ include
						file="/jsp/util/indice_pager_novo.jsp"%>
						</strong>
					</div>
				</td>
			</tr>
			<tr>
				<td height="24"><input type="button" class="bottonRightCol"
					value="Voltar Pesquisa"
					onclick="window.location.href='exibirPesquisarOrdemServicoAction.do?objetoConsultaOs=0';"/></td>
			</tr>
		</table>
		</pg:pager></td>
	</tr>

</table>

</body>

</html:html>
