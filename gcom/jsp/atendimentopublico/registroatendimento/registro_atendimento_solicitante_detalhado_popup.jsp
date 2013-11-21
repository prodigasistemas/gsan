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

<%@ page import="gcom.util.ConstantesSistema"%>

<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js"></script>

<script language="JavaScript">
	
	function consultarSolicitantePorRA() {
		var form = document.forms[0];
		form.action = 'exibirConsultarRegistroAtendimentoSolicitanteAction.do?numeroRA='+form.numeroRA.value;
		form.submit();
	}
	
</script>

</head>

<body leftmargin="5" topmargin="5">
<html:form action="/exibirConsultarRegistroAtendimentoSolicitanteDetalhadoAction.do"
	name="ConsultarRegistroAtendimentoSolicitanteActionForm"
	type="gcom.gui.atendimentopublico.registroatendimento.ConsultarRegistroAtendimentoSolicitanteActionForm"
	method="post">

	<html:hidden property="numeroRA" />

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
						<td class="parabg">Solicitante do Registro de Atendimento</td>
						<td width="11">
							<img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif" />
						</td>
					</tr>
				</table>
			
				<p>&nbsp;</p>
				
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
	                         							<span class="style2"><b>Dados do Solicitante</b></span>
	                         						</div>
	                         					</td>
	                        				</tr>
											<tr bgcolor="#cbe5fe">
												<td>
													<table border="0" width="100%">

			                            				<tr>
															<td class="style3"><strong>Nº Protocolo:</strong></td>
															<td colspan="3"><html:text property="protocoloAtendimento"
																readonly="true" style="background-color:#EFEFEF; border:0;"
																size="15" maxlength="14" /></td>
														</tr>
										
			                            				<tr>

			                              					<td height="10"><strong>Solicitante Principal:</strong></td>
						                              		<td width="10%"> 
																<html:radio property="indicadorPrincipal" value="<%=""+ConstantesSistema.SIM%>" disabled="true"/>
																<strong>Sim</strong>
																<html:radio property="indicadorPrincipal" value="<%=""+ConstantesSistema.NAO%>" disabled="true"/>
																<strong>N&atilde;o</strong>
						                                  	</td>
						                            	</tr>

						                            	<tr> 
						                              		<td class="style3"><strong>Nome do Solicitante:</strong></td>
						                              		<td colspan="3">
																<html:text property="nomeSolicitante" readonly="true"
																	style="background-color:#EFEFEF; border:0;" size="48"
																	maxlength="50" />
						                                	</td>
						                            	</tr>

						                            	<tr> 
						                              		<td height="10"><strong>Cliente Solicitante:</strong></td>
						                              		<td colspan="3"> 
																<html:text property="clienteSolicitante" readonly="true"
																	style="background-color:#EFEFEF; border:0;" size="48"
																	maxlength="50" />
						                                 	</td>
						                            	</tr>
						                            	
							                            <tr> 
							                              	<td class="style3"><strong>Unidade de Atendimento:</strong></td>
							                              	<td colspan="3">
																<html:text property="idUnidadeSolicitante" readonly="true"
																	style="background-color:#EFEFEF; border:0;" size="4"
																	maxlength="4" />
				
																<html:text property="unidadeSolicitante" readonly="true"
																	style="background-color:#EFEFEF; border:0;" size="40"
																	maxlength="40" />
															</td>
							                            </tr>

							                            <tr> 
							                              	<td class="style3"><strong>Funcion&aacute;rio Respons&aacute;vel:</strong></td>
							                              	<td colspan="3">
																<html:text property="idFuncionarioResponsavel" readonly="true"
																	style="background-color:#EFEFEF; border:0;" size="4"
																	maxlength="4" />
				
																<html:text property="funcionarioResponsavel" readonly="true"
																	style="background-color:#EFEFEF; border:0;" size="40"
																	maxlength="40" />
															</td>
							                            </tr>

									        			<tr>
									          				<td colspan="5" border="0">
							          							<hr noshade></hr>
									        				</td>
									        			</tr>		

						                            	<tr> 
						                              		<td height="10"><strong>Endere&ccedil;o do Solicitante:</strong></td>
						                              		<td colspan="3"> 
																<html:textarea property="enderecoSolicitante" readonly="true"
																	style="background-color:#EFEFEF; border:0;" cols="50" />
						                                 	</td>
						                            	</tr>

						                            	<tr> 
						                              		<td height="10"><strong>Ponto de Refer&ecirc;ncia:</strong></td>
						                              		<td colspan="3"> 
																<html:text property="pontoReferencia" readonly="true"
																	style="background-color:#EFEFEF; border:0;" size="40"
																	maxlength="40" />

						                                 	</td>
						                            	</tr>

									        			<tr>
									          				<td colspan="5" border="0">
							          							<hr noshade></hr>
									        				</td>
									        			</tr>
														<tr>
			                							<c:set var="count" value="0"/>

			  			                      		  	<logic:iterate id="solicitanteFone" 
			  			                      		  		name="ConsultarRegistroAtendimentoSolicitanteActionForm" 
			  			                      		  		property="colecaoSolicitanteFone" 
			  			                      		  		type="gcom.atendimentopublico.registroatendimento.SolicitanteFone" 
			  			                      		  		scope="session">
		
			  			                      		  		<c:set var="count" value="${count+1}"/>
															<tr>
									                        
							                        		<c:choose>
		
							                        			<c:when test="${count%2 == '1'}">
											        				<td height="10"><strong>Fone(s) do  Solicitante:</strong></td>
							                        			</c:when>
							                        			<c:otherwise>
											        				<td height="10"></td>							                        			</c:otherwise>
							                        		</c:choose>
									                        
									                        
									                        <td colspan="3">

								                        		<input type="text" 
								                        			value="<bean:write name="solicitanteFone" property="ddd" />" 
								                        			style="background-color:#EFEFEF; border:0;" size="2"
																	maxlength="2">

								                        		<input type="text" 
								                        			value="<bean:write name="solicitanteFone" property="fone" />" 
								                        			style="background-color:#EFEFEF; border:0;" size="9"
																	maxlength="9">

								                        		<input type="text" 
								                        			value="<bean:write name="solicitanteFone" property="ramal" />" 
								                        			style="background-color:#EFEFEF; border:0;" 
								                        			size="4"
																	maxlength="4">

								                        		<input type="text" 
								                        			value="<bean:write name="solicitanteFone" property="foneTipo.descricao" />" 
								                        			style="background-color:#EFEFEF; border:0;" size="15"
																	maxlength="15">
									                        </td>
								                      	</logic:iterate>	

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
			        <tr> 
			          <td>&nbsp;</td>
			          <td>&nbsp;</td>
			        </tr>
	        	</table>
	        	<table  width="100%" border="0">
			  		<tr> 
          				<td>&nbsp;</td>
          				<td>
          					<div align="right">
          						<strong>
          							<font color="#FF0000">
        								<input type="button" name="Submit223422" class="bottonRightCol" value="Voltar" onclick="javascript:consultarSolicitantePorRA();">        								
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