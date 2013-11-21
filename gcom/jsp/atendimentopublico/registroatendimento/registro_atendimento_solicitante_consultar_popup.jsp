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

<script language="JavaScript">

	function consultarSolicitante(id) {
		var form = document.forms[0];

		form.action = 'exibirConsultarRegistroAtendimentoSolicitanteDetalhadoAction.do?solicitanteId='+id;
		form.submit();
	}
	
	function validaRadio(){
		var form = document.forms[0];
		
		if(form.indicador.checked == false){
			form.indicador.checked = true;
		}
	}
</script>

</head>

<body leftmargin="0" topmargin="0"
	onload="window.focus();resizePageSemLink(730, 500);javascript:setarFoco('${requestScope.nomeCampo}');">

<html:form action="/exibirConsultarRegistroAtendimentoSolicitanteAction" 
	name="ConsultarRegistroAtendimentoSolicitanteActionForm"
	type="gcom.gui.atendimentopublico.registroatendimento.ConsultarRegistroAtendimentoSolicitanteActionForm"
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
						<td class="parabg">Consultar Solicitante(s) do Registro de Atendimento</td>
						<td width="11">
							<img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif" />
						</td>
					</tr>
				</table>
			
				<table width="100%">
					<tr>
						<td width="90%" colspan="2">&nbsp;</td>
						<logic:present scope="application" name="urlHelp">
						<td align="right"><a href="javascript:abrirPopupHelp('${applicationScope.urlHelp}atendimentoRegistroSolicitantesConsultar', 500, 700);"><span style="font-weight: bold"><font color="#3165CE">Ajuda</font></span></a></td>									
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
                         						<span class="style2"><b>Dados Gerais do Registro de Atendimento </b></span>
                         					</div>
                         				</td>
                        			</tr>

									<tr bgcolor="#cbe5fe">
										<td>
										<table border="0" width="100%">

			                            	<tr>
			                              		<td height="10"><strong>N&uacute;mero do RA:</strong></td>
			                              		
			                              		<td width="10%"> 
													<html:text property="numeroRA" 
														readonly="true"
														style="background-color:#EFEFEF; border:0;" 
														size="9"
														maxlength="9" />
			                                  	</td>
				                              	
				                              	<td><strong>Situa&ccedil;&atilde;o do RA:</strong>
				                              		<html:text property="situacaoRA" 
				                              			readonly="true"
														style="background-color:#EFEFEF; border:0;" 
														size="9"
														maxlength="9" />
				                              	</td>
			                            	</tr>

			                            	<tr> 
			                              		<td class="style3"><strong>Tipo de Solicita&ccedil;&atilde;o:</strong></td>
			                              		<td colspan="3">
													<html:text property="idTipoSolicitacao" 
														readonly="true"
														style="background-color:#EFEFEF; border:0;" 
														size="4"
														maxlength="4" />
	
													<html:text property="tipoSolicitacao" 
														readonly="true"
														style="background-color:#EFEFEF; border:0;" 
														size="40"
														maxlength="40" />
			                                	</td>
			                            	</tr>

			                            	<tr> 
			                              		<td height="10"><strong>Especifica&ccedil;&atilde;o:</strong></td>
			                              		<td colspan="3"> 
													<html:text property="idEspecificacao" 
														readonly="true"
														style="background-color:#EFEFEF; border:0;" 
														size="4"
														maxlength="4" />
	
													<html:text property="especificacao" 
														readonly="true"
														style="background-color:#EFEFEF; border:0;" 
														size="40"
														maxlength="40" />
			                                 	</td>
			                            	</tr>
	
				                            <tr> 
				                              	<td class="style3"><strong>Unidade Atual:</strong></td>
				                              	<td colspan="3">
													<html:text property="idUnidadeAtual" 
														readonly="true"
														style="background-color:#EFEFEF; border:0;" 
														size="4"
														maxlength="4" />
	
													<html:text property="unidadeAtual" 
														readonly="true"
														style="background-color:#EFEFEF; border:0;" 
														size="40"
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

					<!-- Solicitantes -->
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
	          						<td width="100%"><strong>Solicitantes:</strong></td>
	        					</tr>
		        				<tr> 
		          					<td><strong></strong>
		          						<div align="left"> 
		              						<table width="100%" align="center" bgcolor="#99CCFF">

		                						<!--corpo da segunda tabela-->
		                						<tr bordercolor="#FFFFFF" bgcolor="#79BBFD"> 
		                  							<td width="10%"><div align="center"><strong>Principal</strong></div></td>
		                  							<td width="15%"><div align="center"><strong>Protocolo</strong></div></td>
		                  							<td width="10%"><div align="center"><strong>Cliente</strong></div></td>
		                  							<td width="28%"><div align="center"><strong>Nome do Solicitante</strong></div></td>
	                        						<td width="10%"><div align="center"><strong>Unidade </strong></div></td>
		                  							<td width="27%"><div align="center">
		                  								<strong>Funcion&aacute;rio Respons&aacute;vel</strong></div>
		                  							</td>
		                						</tr>

			                					<c:set var="count" value="0"/>
	                      		  				<c:set var="checked" value="false"/>
	                      		  				
	  			                      		  	<logic:iterate id="solicitante" 
	  			                      		  		name="ConsultarRegistroAtendimentoSolicitanteActionForm" 
	  			                      		  		property="colecaoRegistroAtendimentoSolicitante" 
	  			                      		  		type="gcom.atendimentopublico.registroatendimento.RegistroAtendimentoSolicitante" 
	  			                      		  		scope="session">

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
					                        				<c:choose>
							                        			<c:when test="${solicitante.indicadorSolicitantePrincipal == '1' && checked == 'false'}">
			  			                      		  				<c:set var="checked" value="true"/>
																	<input type="radio" name="indicador" value="" disabled="true" checked>
							                        			</c:when>

							                        			<c:otherwise>
																	<input type="radio" name="indicador" value="" disabled="true">
							                        			</c:otherwise>
					                        				</c:choose>
							                        	</div>
							                        </td>
							                        
							                        <td bordercolor="#90c7fc">
							                        	<div align="center">
							                        	<logic:notEmpty name="solicitante" property="numeroProtocoloAtendimento">
						                        			<bean:write name="solicitante" property="numeroProtocoloAtendimento"/>
														</logic:notEmpty>
							                        	</div>
							                        </td>
							                        
							                        <td bordercolor="#90c7fc">
							                        	<div align="center">
							                        	<logic:notEmpty name="solicitante" property="cliente">
						                        			<bean:write name="solicitante" property="cliente.id"/>
														</logic:notEmpty>
							                        	</div>
							                        </td>
							                        
							                        <td bordercolor="#90c7fc">
							                        	<div align="center">

							                        		<a href="javascript:consultarSolicitante(<bean:write name="solicitante" property="ID"/>);">

							                        		<c:choose>
								                        		<c:when test="${solicitante.cliente == null}">
								                        			<bean:write name="solicitante" property="solicitante" />
								                        		</c:when>
								                        		<c:otherwise>
								                        			<bean:write name="solicitante" property="cliente.nome" />	
								                        		</c:otherwise>
								                        	</c:choose>

							                        		</a>
							                        	</div>
							                        </td>

							                        <td bordercolor="#90c7fc">
							                        	<div align="center">
							                        	<logic:notEmpty name="solicitante" property="unidadeOrganizacional">
					                        				<bean:write name="solicitante" property="unidadeOrganizacional.descricao" />
				                        				</logic:notEmpty>
							                        	</div>
							                        </td>

							                        <td bordercolor="#90c7fc">
							                        	<div align="left">
							                        	<logic:notEmpty name="solicitante" property="funcionario">
						                        			<bean:write name="solicitante" property="funcionario.nome" />
					                        			</logic:notEmpty>
							                        	</div>
							                        </td>
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
      								<input type="button" name="Submit223422" class="bottonRightCol" value="Fechar" onClick="window.close();">
       							</font>
       						</strong>
      					</div>
       				</td>
       			</tr>
        	</table>
			
			</td>
		</tr>
   		
	</table>
	<!-- Fim do Corpo - Rafael Pinto -->
</html:form>
</body>
</html:html>