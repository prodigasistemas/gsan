<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
<head>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">

<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js"></script>

</head>

<body leftmargin="5" topmargin="5" onload="resizePageSemLink(735, 550)">
<html:form action="/exibirConsultarRegistroAtendimentoOSAction.do"
	name="ConsultarRegistroAtendimentoOSActionForm"
	type="gcom.gui.atendimentopublico.registroatendimento.ConsultarRegistroAtendimentoOSActionForm"
	method="post">

	<table width="705" border="0" cellspacing="5" cellpadding="0">

		<tr>
			<td width="700" valign="top" class="centercoltext">

				<table height="100%">
					<tr>
						<td></td>
					</tr>
				</table>
	
				<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
					<tr>
						<td width="11">
							<img border="0" src="<bean:message key="caminho.imagens"/>parahead_left.gif" />
						</td>
						<td class="parabg">Consultar Ordens de Serviço do Registro de Atendimento</td>
						<td width="11">
							<img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif" />
						</td>
					</tr>
				</table>
			
				<table width="100%" border="0">
					<tr>
						<logic:present scope="application" name="urlHelp">
								<td align="right"><a href="javascript:abrirPopupHelp('${applicationScope.urlHelp}atendimentoRegistroConsultar', 500, 700);"><span style="font-weight: bold"><font color="#3165CE">Ajuda</font></span></a></td>									
						</logic:present>
						<logic:notPresent scope="application" name="urlHelp">
								<td align="right"><span style="font-weight: bold"><font color=#696969><u>Ajuda</u></font></span></td>									
						</logic:notPresent>
					</tr>
				</table>
				
				<!--Inicio da Tabela Dados Gerais do Registro de Atendimento -->
            	<table width="100%" border="0">
	            	<tr>
	                	<td height="31">
	                    	<table width="100%" border="0" align="center">
	                        	<tr bgcolor="#cbe5fe">
	                      			<td align="center">
	                      				<table width="100%" border="0" bgcolor="#99CCFF">
						    				<tr bgcolor="#99CCFF">
	                         					<td height="18" colspan="2">
	                         						<div align="center">
	                         							<span class="style2"><b>Dados do Registro de Atendimento </b></span>
	                         						</div>
	                         					</td>
	                        				</tr>
											<tr bgcolor="#cbe5fe">
												<td>
													<table border="0" width="100%">
						                            	<tr>
						                              		<td height="10" width="33%"><strong>N&uacute;mero do RA:</strong></td>
						                              		
						                              		<td> 
																<html:text property="numeroRA" readonly="true"
																	style="background-color:#EFEFEF; border:0;" size="9"
																	maxlength="9" />
																&nbsp;&nbsp;&nbsp;&nbsp;	
																<strong>Situa&ccedil;&atilde;o do RA:</strong>
																&nbsp;&nbsp;	
																<html:text property="situacaoRA" readonly="true"
																	style="background-color:#EFEFEF; border:0;" size="34"
																	maxlength="9" />
						                                  	</td>
						                            	</tr>
							                            <tr>
							                              	<td height="10" width="33%"><strong>Tipo do Solicita&ccedil;&atilde;o:</strong></td>
															<td>
																<html:text property="tipoSolicitacaoId" readonly="true"
																	style="background-color:#EFEFEF; border:0;" size="4"
																	maxlength="4" />
																<html:text property="tipoSolicitacao" readonly="true"
																	style="background-color:#EFEFEF; border:0;" size="40"
																	maxlength="40" />
															</td>
							                            </tr>
							                            <tr>
							                              	<td height="10" width="33%"><strong>Especifica&ccedil;&atilde;o:</strong></td>
															<td>
																<html:text property="especificacaoId" readonly="true"
																	style="background-color:#EFEFEF; border:0;" size="4"
																	maxlength="4" />
																<html:text property="especificacao" readonly="true"
																	style="background-color:#EFEFEF; border:0;" size="40"
																	maxlength="40" />
															</td>
							                            </tr>
							                            <tr> 
							                              	<td class="style3"><strong>Unidade Atual:</strong></td>
							                              	<td colspan="3">
																<html:text property="idUnidadeAtual" readonly="true"
																	style="background-color:#EFEFEF; border:0;" size="4"
																	maxlength="4" />
				
																<html:text property="unidadeAtual" readonly="true"
																	style="background-color:#EFEFEF; border:0;" size="40"
																	maxlength="40" />
															</td>
							                            </tr>
													</table>
												</td>
											</tr>
										</table>
									</td>
								</tr>	
               				</table>
						</td>
					</tr>
					<!-- Ordens de Serviço -->
	        		<tr> 
	          			<td colspan="1">
	          				<div align="right"><strong></strong></div>
	            			<div align="left"><strong></strong></div>
	            			<div align="center"></div>
	            		</td>
	        		</tr>
        			<tr> 
          				<td width="100%" colspan="1">
          					<table width="100%" border="0">
	        					<tr> 
	          						<td width="100%"><strong>Dados das Ordens de Servi&ccedil;o</strong></td>
	        					</tr>
		        				<tr> 
		          					<td><strong></strong>
		          						<div align="left"> 
		              						<table width="100%" align="center" bgcolor="#99CCFF">
		                						<!--corpo da segunda tabela-->
		                						<tr bordercolor="#FFFFFF" bgcolor="#79BBFD"> 
		                  							<td width="20%"><div align="center"><strong>N&uacute;mero da Ordem de Servi&ccedil;o</strong></div></td>
		                  							<td width="40%"><div align="center"><strong>Tipo de Servi&ccedil;o</strong></div></td>
		                  							<td width="10%"><div align="center"><strong>Data da Gera&ccedil;&atilde;o</strong></div></td>
		                  							<td width="30%"><div align="center"><strong>Situa&ccedil;&atilde;o</strong></div></td>
		                						</tr>
			                					<c:set var="count" value="0"/>
	  			                      		  	<logic:iterate id="os" name="ConsultarRegistroAtendimentoOSActionForm" property="colecaoOS" type="gcom.atendimentopublico.ordemservico.OrdemServico" scope="session">
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
								                        		<bean:write name="os" property="id"/>
								                        	</div>
								                        </td>
								                        <td bordercolor="#90c7fc">
								                        	<div align="left">
						                              			<a href="javascript:window.location.href='<html:rewrite page="/exibirConsultarDadosOrdemServicoPopupAction.do?numeroOS=${os.id}"/>';">
							                        				<bean:write name="os" property="servicoTipo.descricao"/>
								                        		</a>
								                        	</div>
								                        </td>
								                        <td bordercolor="#90c7fc">
								                        	<div align="center">								                        
						                        				<bean:write name="os" property="dataGeracao" format="dd/MM/yyyy"/>
								                        	</div>
								                        </td>
								                        <td bordercolor="#90c7fc">
								                        	<div align="left">
								                        		<c:choose>
								                        			<c:when test="${os.situacao == 1}">
								                        				PENDENTE
								                        			</c:when>
								                        			<c:when test="${os.situacao == 2}">
								                        				ENCERRADO
								                        			</c:when>
								                        			<c:when test="${os.situacao == 3}">
								                        				EXECU&Ccedil;&Atilde;O EM ANDAMENTO 
								                        			</c:when>
								                        			<c:when test="${os.situacao == 4}">
								                        				AGUARDANDO LIBERA&Ccedil;&Atilde;O PARA EXECU&Ccedil;&Atilde;O
								                        			</c:when>
								                        		</c:choose>
								                        	</div>
								                        </td>
								                    </tr>
						                      	</logic:iterate>	
		              						</table>
		            					</div>
		            				</td>
		        				</tr>
	        				</table>
	        			</td>
	        		</tr>							
	        	</table>
	        	<table  width="100%" border="0">
			  		<tr> 
          				<td>&nbsp;</td>
          				<td>
          					<div align="right">
          						<strong>
          							<font color="#FF0000">
        								<input type="submit" name="Submit223422" class="bottonRightCol" value="Fechar" onclick="javascript:window.close();">
        							</font>
        						</strong>
        					</div>
        				</td>
        			</tr>
	        	</table>
			</td>		
		</tr>
	</table>
	
	<!-- Fim do Corpo -->
</html:form>
</body>
</html:html>