<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan" %>

<html:html>
<head>
 <%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">


<%-- Carrega javascripts da biblioteca --%>
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js"></script>

<script language="JavaScript">

	/* Valida Form */
	function validaForm() {
	    
	    var form = document.forms[0];
		
		form.action='atualizarProgramacaoAbastecimentoManutencaoAction.do';
		
		submeterFormPadrao(form);
	}
	
	/* Remove Componente da grid */	
	function remover(id,ehAbastecimento){
	    var form = document.forms[0];
	    
	    var remover;
	    if(ehAbastecimento){
	    	remover = 'removerAbastecimento'
	    }else{
	    	remover = 'removerManutencao'
	    }
	
		if (confirm ("Confirma remoção?")) {
        	form.action='exibirInformarProgramacaoAbastecimentoManutencaoAction.do?tipoOperacao=E&'+remover+'='+id;
	    	form.submit();
		}
	}
	
	function atualizar(indice,ehAbastecimento){
	    var form = document.forms[0];

	    var tipoProgramacao;

	    if(ehAbastecimento){
	    	tipoProgramacao = 'A'
	    }else{
	    	tipoProgramacao = 'M'
	    }
	    
	   	var param = 'tipoProgramacao='+tipoProgramacao+'&indice='+indice;
	    
		abrirPopup('exibirAtualizarProgramacaoAbastecimentoManutencaoAction.do?'+param);
	}
	
	function adicionar(ehAbastecimento){
	    var form = document.forms[0];
	    var tipoProgramacao;

	    if(ehAbastecimento){
	    	tipoProgramacao = 'A'
	    }else{
	    	tipoProgramacao = 'M'
	    }
	    
		abrirPopup('exibirInserirProgramacaoAbastecimentoManutencaoAction.do?tipoProgramacao='+tipoProgramacao);
	}
	
	function copiar(){
	    var form = document.forms[0];

		abrirPopup('exibirCopiarProgramacaoAbastecimentoManutencaoAction.do');
	}

	function desfazer(){
	    var form = document.forms[0];
	    
	    form.action='filtrarProgramacaoAbastecimentoManutencaoAction.do';
	    form.submit();
	}
	
	
</script>
</head>

<html:form action="/atualizarProgramacaoAbastecimentoManutencaoAction.do"
	name="InformarProgramacaoAbastecimentoManutencaoActionForm"
	type="gcom.gui.operacional.abastecimento.InformarProgramacaoAbastecimentoManutencaoActionForm"
	method="post">
	
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
			<td width="615" valign="top" class="centercoltext">
				<table height="100%">
					<tr>
						<td></td>
					</tr>
				</table>
				<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
					<tr>
						<td width="11">
							<img border="0" 
								src="<bean:message key="caminho.imagens"/>parahead_left.gif" />
						</td>
						
						<td class="parabg">
							Informar Programa&ccedil;&atilde;o de Abastecimento e Manuten&ccedil;&atilde;o
						</td>
						
						<td width="11">
							<img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif" />
						</td>
					</tr>
				</table>
				<p>&nbsp;</p>

				<table width="100%" border="0">
					<tr>
						<td height="31">
							<table width="100%" border="0" align="center">

								<tr>
									<td><strong>Mês e Ano de Referência:</strong></td>

									<td>
										<html:text property="mesAnoReferencia" 
											size="7"
											maxlength="7" 
											readonly="true"
											style="background-color:#EFEFEF; border:0;"/>
									</td>
								</tr>
				  	
							  	<tr> 
			           				<td colspan="4"><hr></td>
							  	</tr>
								

								<tr>
									<td><strong>Munic&iacute;pio:</strong></td>

									<td>
										<html:text property="municipio" 
											size="7"
											maxlength="7" 
											readonly="true"
											style="background-color:#EFEFEF; border:0;"/>

										<html:text property="nomeMunicipio" 
											size="40"
											maxlength="40" 
											readonly="true"
											style="background-color:#EFEFEF; border:0;"/>
									</td>
								</tr>

								<tr>
									<td><strong>Bairro:</strong></td>

									<td>
										<html:text property="bairro" 
											size="7"
											maxlength="7" 
											readonly="true"
											style="background-color:#EFEFEF; border:0;"/>

										<html:text property="nomeBairro" 
											size="40"
											maxlength="40" 
											readonly="true"
											style="background-color:#EFEFEF; border:0;"/>
									</td>
								</tr>

								<tr>
									<td><strong>&Aacute;rea de Bairro:</strong></td>

									<td>
										<html:text property="areaBairro" 
											size="7"
											maxlength="7" 
											readonly="true"
											style="background-color:#EFEFEF; border:0;"/>

										<html:text property="nomeAreaBairro" 
											size="40"
											maxlength="40" 
											readonly="true"
											style="background-color:#EFEFEF; border:0;"/>
									</td>
								</tr>

              					<tr> 
                					<td height="24" colspan="3">
                						<hr>
                					</td>
              					</tr>

								<tr>
									<td><strong>Sistema de Abastecimento:</strong></td>

									<td>
										<html:text property="sistemaAbastecimento" 
											size="7"
											maxlength="7" 
											readonly="true"
											style="background-color:#EFEFEF; border:0;"/>

										<html:text property="nomeSistemaAbastecimento" 
											size="40"
											maxlength="40" 
											readonly="true"
											style="background-color:#EFEFEF; border:0;"/>
									</td>
								</tr>

								<tr>
									<td><strong>Setor de Abastecimento:</strong></td>

									<td>
										<html:text property="setorAbastecimento" 
											size="7"
											maxlength="7" 
											readonly="true"
											style="background-color:#EFEFEF; border:0;"/>

										<html:text property="nomeSetorAbastecimento" 
											size="40"
											maxlength="40" 
											readonly="true"
											style="background-color:#EFEFEF; border:0;"/>
									</td>
								</tr>

								<tr>
									<td><strong>Zona de Abastecimento:</strong></td>

									<td>
										<html:text property="zonaAbastecimento" 
											size="7"
											maxlength="7" 
											readonly="true"
											style="background-color:#EFEFEF; border:0;"/>

										<html:text property="nomeZonaAbastecimento" 
											size="40"
											maxlength="40" 
											readonly="true"
											style="background-color:#EFEFEF; border:0;"/>
									</td>
								</tr>

								<tr>
									<td><strong>Distrito Operacional:</strong></td>

									<td>
										<html:text property="distritoOperacional" 
											size="7"
											maxlength="7" 
											readonly="true"
											style="background-color:#EFEFEF; border:0;"/>

										<html:text property="nomeDistritoOperacional" 
											size="40"
											maxlength="40" 
											readonly="true"
											style="background-color:#EFEFEF; border:0;"/>
									</td>
								</tr>
        						<tr>
                                  	<td colspan="3">
                                  		<p>&nbsp;</p>
                                  		<p>Para informar a programa&ccedil;&atilde;o de abastecimento e manuten&ccedil;&atilde;o, 
                                  		informe os dados abaixo:</p>
                                  		<p>&nbsp;</p>
                                  	</td>
                                </tr>
                                
              					<tr> 
                					<td>
                						<strong>
                							<font color="#000000">Programa&ccedil;&atilde;o de Abastecimento: </font>
                						</strong>                						
                					</td>
                					<td colspan="3" align="right">
                						<div align="right"> 
			                				<c:choose>
				                				<c:when test="${empty InformarProgramacaoAbastecimentoManutencaoActionForm.abastecimentoProgramacao}">
		                    						<input type="button" 
		                    							name="Submit24" 
		                    							class="bottonRightCol" 
		                    							value="Copiar" 
		                    						    onclick="javascript:copiar();">
					                    		</c:when>
												<c:otherwise>
		                    						<input type="button" 
		                    							name="Submit24" 
		                    							class="bottonRightCol" 
		                    							disabled="true"
		                    							value="Copiar">
												</c:otherwise>
											</c:choose>

                    						<input type="button" 
                    							name="Submit24" 
                    							class="bottonRightCol" 
                    							value="Adicionar" 
                    						    onclick="javascript:adicionar(true);">

                  						</div>
                  					</td>
              					</tr>
              					
              					<tr> 
                					<td width="100%" colspan="4">
                						<div align="center">
                							<strong>
                								<font color="#FF0000"></font>
                							</strong> 
                    						<table width="100%" align="center" bgcolor="#99CCFF">

	                      						<!--corpo da segunda tabela-->
						                      	<tr bordercolor="#FFFFFF" bgcolor="#79BBFD"> 
	                        						
	                        						<td width="09%">
	                        							<div align="center"><strong>Remover</strong></div>
	                        						</td>
	                        						
	                        						<td width="23%">
	                        							<div align="center"><strong>Data In&iacute;cio</strong></div>
							                      	</td>
	
	                        						<td width="22%">
	                        							<div align="center"><strong>Data Fim</strong></div>
							                      	</td>
							                        
							                        <td width="22%">
							                        	<div align="center"><strong>Hora In&iacute;cio</strong></div>
							                      	</td>
							                        
							                        <td width="24%">
							                        	<div align="center"><strong>Hora Fim</strong></div>
							                      	</td>
						                      	</tr>



						                      	<c:set var="count" value="0"/>
	  			                      		  	
	  			                      		  	<logic:iterate id="programacaoAbastecimento" 
	  			                      		  		name="InformarProgramacaoAbastecimentoManutencaoActionForm" 
	  			                      		  		property="abastecimentoProgramacao" 
	  			                      		  		type="gcom.operacional.abastecimento.AbastecimentoProgramacao" 
	  			                      		  		scope="session">
	
						                      	<tr>
	
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
														<a href="javascript:remover('${count}',true);">
							                        		<img src="<bean:message key='caminho.imagens'/>Error.gif" 
							                        			width="14" 
							                        			height="14" 
														 		style="cursor:hand;" 
														 		name="imagem"
																border="0"
														 		alt="Remover"></a>
														</div>
													</td>
	
							                        <td bordercolor="#90c7fc">
							                        	<div align="center">
														<a href="javascript:atualizar('${count}',true);">
	  														<fmt:formatDate value="${programacaoAbastecimento.dataInicio}" pattern="dd/MM/yyyy" />
				                        				</a>
							                        	</div>
							                        </td>
	
							                        <td bordercolor="#90c7fc">
							                        	<div align="center">
						                        		<c:if test="${programacaoAbastecimento.dataFim != null}">
						                        			<fmt:formatDate value="${programacaoAbastecimento.dataFim}" pattern="dd/MM/yyyy" />
						                        		</c:if>
							                        	</div>
							                        </td>
	
							                        <td bordercolor="#90c7fc">
							                        	<div align="center">
						                        		<c:if test="${programacaoAbastecimento.horaInicio != null}">
						                        			<fmt:formatDate value="${programacaoAbastecimento.horaInicio}" pattern="H:mm" />
						                        		</c:if>
							                        	</div>
							                        </td>
	
							                        <td bordercolor="#90c7fc">
							                        	<div align="center">
						                        		<c:if test="${programacaoAbastecimento.horaFim != null}">
							                        		<fmt:formatDate value="${programacaoAbastecimento.horaFim}" pattern="H:mm" />
						                        		</c:if>
							                        	</div>
							                        </td>
	
							                    </tr>
						                      		
						                      	</logic:iterate>	
				                    		</table>
                						</div>
                					</td>
              					</tr>
			  					
			  					<tr>
                        			<td colspan="3">&nbsp;</td>

              					</tr>
              					
              					<tr> 
                					<td>
                						<strong>
                							<font color="#000000">Programa&ccedil;&atilde;o de Manuten&ccedil;&atilde;o: </font>
                						</strong>                						
                					</td>
                					<td colspan="3" align="right">
                						<div align="right"> 
                    						<input type="button" 
                    							name="Submit24" 
                    							class="bottonRightCol" value="Adicionar" 
                    						    onclick="javascript:adicionar(false);">

                  						</div>
                  					</td>
              					</tr>
              					
              					<tr> 
                					<td width="100%" colspan="4">
                						<div align="center">
                							<strong>
                								<font color="#FF0000"></font>
                							</strong> 
                    						<table width="100%" align="center" bgcolor="#99CCFF">

	                      						<!--corpo da segunda tabela-->
						                      	<tr bordercolor="#FFFFFF" bgcolor="#79BBFD"> 
	                        						
	                        						<td width="09%">
	                        							<div align="center"><strong>Remover</strong></div>
	                        						</td>
	                        						
	                        						<td width="33%">
	                        							<div align="center"><strong>Descri&ccedil;&atilde;o</strong></div>
							                      	</td>

	                        						<td width="15%">
	                        							<div align="center"><strong>Situa&ccedil;&atilde;o</strong></div>
							                      	</td>

	                        						<td width="11%">
	                        							<div align="center"><strong>Data In&iacute;cio</strong></div>
							                      	</td>
	
	                        						<td width="11%">
	                        							<div align="center"><strong>Data Fim</strong></div>
							                      	</td>
							                        
							                        <td width="11%">
							                        	<div align="center"><strong>Hora In&iacute;cio</strong></div>
							                      	</td>
							                        
							                        <td width="11%">
							                        	<div align="center"><strong>Hora Fim</strong></div>
							                      	</td>
						                      	</tr>



						                      	<c:set var="count" value="0"/>
	  			                      		  	
	  			                      		  	<logic:iterate id="manutencaoProgr" 
	  			                      		  		name="InformarProgramacaoAbastecimentoManutencaoActionForm" 
	  			                      		  		property="manutencaoProgramacao" 
	  			                      		  		type="gcom.operacional.abastecimento.ManutencaoProgramacao" 
	  			                      		  		scope="session">
	
						                      	<tr>
	
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
														<a href="javascript:remover('${count}',false);">
							                        		<img src="<bean:message key='caminho.imagens'/>Error.gif" 
							                        			width="14" 
							                        			height="14" 
														 		style="cursor:hand;" 
														 		name="imagem"
																border="0"
														 		alt="Remover"></a>
														</div>
													</td>
	
							                        <td bordercolor="#90c7fc">
							                        	<div align="center">
	  														<a href="javascript:atualizar('${count}',false);">
						                        				<bean:write name="manutencaoProgr" property="descricao" />
					                        				</a>
							                        	</div>
							                        </td>

							                        <td bordercolor="#90c7fc">
							                        	<div align="center">
							                        		<c:if test="${manutencaoProgr.situacao != null}">
								                        		<c:choose>
								                        			<c:when test="${manutencaoProgr.situacao == '1'}">
								                        				Em Aberto
								                        			</c:when>
								                        			<c:otherwise>
								                        				Conclu&iacute;da
								                        			</c:otherwise>
								                        		</c:choose>
							                        		</c:if>
							                        	</div>
							                        </td>

							                        <td bordercolor="#90c7fc">
							                        	<div align="center">
						                        		<c:if test="${manutencaoProgr.dataInicio != null}">
															<fmt:formatDate value="${manutencaoProgr.dataInicio}" pattern="dd/MM/yyyy" />
						                        		</c:if>
							                        	</div>
							                        </td>

							                        <td bordercolor="#90c7fc">
							                        	<div align="center">
						                        		<c:if test="${manutencaoProgr.dataFim != null}">
							                        		<fmt:formatDate value="${manutencaoProgr.dataFim}" pattern="dd/MM/yyyy" />
						                        		</c:if>
							                        	</div>
							                        </td>

							                        <td bordercolor="#90c7fc">
							                        	<div align="center">
							                        		<c:if test="${manutencaoProgr.horaInicio != null}">
						                        				<fmt:formatDate value="${manutencaoProgr.horaInicio}" pattern="H:mm" />
							                        		</c:if>
							                        	</div>
							                        </td>
	
							                        <td bordercolor="#90c7fc">
							                        	<div align="center">
							                        		<c:if test="${manutencaoProgr.horaFim != null}">
						                        				<fmt:formatDate value="${manutencaoProgr.horaFim}" pattern="H:mm" />
							                        		</c:if>
							                        	</div>
							                        </td>
	
							                    </tr>
						                      		
						                      	</logic:iterate>	
				                    		</table>
                						</div>
                					</td>
              					</tr>
              			
              			
					            <tr> 
		                			<td colspan="4">
		                				<strong>
		                					<font color="#FF0000"></font>
		                				</strong> 
		                  				<div align="left"> 
		                    				<hr>
		                  				</div>
		                  			</td>
		              			</tr>

		              			<tr> 
		                			<td>
		                				<strong>
		                					<font color="#FF0000"></font>
		                				</strong>
		                			</td>
		              			</tr>

		              			<tr> 
		                			<td>
		                				<strong>
		                					<font color="#FF0000"> 
		                  						<input type="button" 
		                  							name="Submit22" 
		                  							class="bottonRightCol" 
		                  							value="Desfazer" 
		                  							onClick="javascript:desfazer();">
		                  							
		                  						<input type="button" 
		                  							name="Submit23" 
		                  							class="bottonRightCol" 
		                  							value="Cancelar" 
		                  							onClick="javascript:window.location.href='/gsan/telaPrincipal.do'">
		                  					</font>
		                  				</strong>
		                  			</td>
		                			<td colspan="3" align="right">
		
										<gsan:controleAcessoBotao name="Botao" 
											value="Concluir" 
											onclick="validaForm();" 
											url="atualizarProgramacaoAbastecimentoManutencaoAction.do"/>
		                					
		                			</td>
		              			</tr>
		            		</table>
	          			<p>&nbsp;</p>
	          		</tr>
	        	  </table>
	        	</td>
			</tr>
		</table>
	
	<!-- Rodapé -->
	<%@ include file="/jsp/util/rodape.jsp"%>
</html:form>
</html:html>