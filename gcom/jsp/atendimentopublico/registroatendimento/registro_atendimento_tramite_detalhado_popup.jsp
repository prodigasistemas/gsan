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

<script language="JavaScript">
	function consultarTramitePorRA(id) {
		var form = document.forms[0];
		form.action = 'exibirConsultarRegistroAtendimentoTramiteAction.do?numeroRA='+form.numeroRA.value;
		form.submit();
	}
</script>
</head>

<body leftmargin="5" topmargin="5">
<html:form action="/exibirConsultarRegistroAtendimentoTramiteDetalhadoAction.do"
	name="ConsultarRegistroAtendimentoTramiteActionForm"
	type="gcom.gui.atendimentopublico.registroatendimento.ConsultarRegistroAtendimentoTramiteActionForm"
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
						<td class="parabg">Tramite do Registro de Atendimento</td>
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
	                         							<span class="style2"><b>Dados do Registro de Atendimento </b></span>
	                         						</div>
	                         					</td>
	                        				</tr>
											<tr bgcolor="#cbe5fe">
												<td>
													<table border="0" width="100%">
			                            				<tr>
			                              					<td height="10"><strong>N&uacute;mero do RA:</strong></td>
						                              		<td width="10%"> 
																<html:text property="numeroRA" readonly="true"
																	style="background-color:#EFEFEF; border:0;" size="9"
																	maxlength="9" />
						                                  	</td>
				                              				<td width="13%"><strong>Situa&ccedil;&atilde;o do RA:</strong></td>
							                              	<td width="46%">
																<html:text property="situacaoRA" readonly="true"
																	style="background-color:#EFEFEF; border:0;" size="15"
																	maxlength="9" />
							                                </td>
						                            	</tr>
						                            	<tr> 
						                              		<td class="style3"><strong>Tipo de Solicita&ccedil;&atilde;o:</strong></td>
						                              		<td colspan="3">
																<html:text property="idTipoSolicitacao" readonly="true"
																	style="background-color:#EFEFEF; border:0;" size="4"
																	maxlength="4" />						                              		
																<html:text property="tipoSolicitacao" readonly="true"
																	style="background-color:#EFEFEF; border:0;" size="48"
																	maxlength="50" />
						                                	</td>
						                            	</tr>
						                            	<tr> 
						                              		<td height="10"><strong>Especifica&ccedil;&atilde;o:</strong></td>
						                              		<td colspan="3"> 
																<html:text property="idEspecificacao" readonly="true"
																	style="background-color:#EFEFEF; border:0;" size="4"
																	maxlength="4" />						                              		
																<html:text property="especificacao" readonly="true"
																	style="background-color:#EFEFEF; border:0;" size="48"
																	maxlength="50" />
						                                 	</td>
						                            	</tr>
							                            <tr> 
							                              	<td class="style3"><strong>Unidade de Atendimento:</strong></td>
							                              	<td colspan="3">
																<html:text property="idUnidadeAtendimento" readonly="true"
																	style="background-color:#EFEFEF; border:0;" size="4"
																	maxlength="4" />
				
																<html:text property="unidadeAtendimento" readonly="true"
																	style="background-color:#EFEFEF; border:0;" size="40"
																	maxlength="40" />
															</td>
							                            </tr>
													</table>
												</td>
											</tr>
										</table>
			               				<table width="100%">
							        		<tr> 
							          			<td colspan="1">
							          				<div align="right"><strong></strong></div>
							            			<div align="left"><strong></strong></div>
							            			<div align="center"></div>
							            		</td>
							        		</tr>
						        			<tr> 
						          				<td colspan="1" border="0">
				          							<hr noshade></hr>
						        				</td>
						        			</tr>		
			               				</table>
										<!-- Tramites -->
	                      				<table width="100%" border="0" bgcolor="#99CCFF">
						    				<tr bgcolor="#99CCFF">
	                         					<td height="18" colspan="2">
	                         						<div align="center">
	                         							<span class="style2"><b>Dados da Tramita&ccedil;&atilde;o </b></span>
	                         						</div>
	                         					</td>
	                        				</tr>
											<tr bgcolor="#cbe5fe">
												<td>
													<table border="0" width="100%">
			                            				<tr>
			                              					<td class="style3"><strong>Unidade Destino:</strong></td>
															<td colspan="3">
																<html:text property="unidadeDestinoId" readonly="true"
																	style="background-color:#EFEFEF; border:0;" size="4"
																	maxlength="4" />
				
																<html:text property="unidadeDestinoDescricao" readonly="true"
																	style="background-color:#EFEFEF; border:0;" size="40"
																	maxlength="40" />
															</td>						                            	</tr>
						                            	<tr> 
						                              		<td class="style3"><strong>Usu&aacute;rio Respons&aacute;vel:</strong></td>
						                              		<td colspan="3">
																<html:text property="usuarioResponsavelId" readonly="true"
																	style="background-color:#EFEFEF; border:0;" size="4"
																	maxlength="4" />
				
																<html:text property="usuarioResponsavelNome" readonly="true"
																	style="background-color:#EFEFEF; border:0;" size="40"
																	maxlength="40" />
						                                	</td>
						                            	</tr>
						                            	<tr> 
						                              		<td height="10"><strong>Usu&aacute;rio Registro:</strong></td>
						                              		<td colspan="3"> 
																<html:text property="usuarioRegistroId" readonly="true"
																	style="background-color:#EFEFEF; border:0;" size="4"
																	maxlength="4" />
				
																<html:text property="usuarioRegistroNome" readonly="true"
																	style="background-color:#EFEFEF; border:0;" size="40"
																	maxlength="40" />
						                                 	</td>
						                            	</tr>
						                            	<tr> 
						                              		<td class="style3"><strong>Data da Tramita&ccedil;&atilde;o:</strong></td>
						                              		<td colspan="3">
																<html:text property="dataTramitacao" readonly="true"
																	style="background-color:#EFEFEF; border:0;" size="48"
																	maxlength="50" />
						                                	</td>
						                            	</tr>
							                            <tr> 
						                              		<td class="style3"><strong>Hora da Tramita&ccedil;&atilde;o:</strong></td>
						                              		<td colspan="3">
																<html:text property="horaTramitacao" readonly="true"
																	style="background-color:#EFEFEF; border:0;" size="48"
																	maxlength="50"/>
						                                	</td>
							                            </tr>
							                            <tr> 
							                              	<td class="style3"><strong>Parecer:</strong></td>
							                              	<td colspan="3">
																<html:textarea property="parecer" cols="60" rows="6" readonly="true" style="background-color:#EFEFEF; border:0"/>
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
        								<input type="submit" name="Submit223422" class="bottonRightCol" value="Voltar" onclick="javascript:consultarTramitePorRA();">
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