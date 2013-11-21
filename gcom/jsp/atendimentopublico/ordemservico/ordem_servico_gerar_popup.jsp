<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>

<title>GSAN - Sistema Integrado de Gest&atilde;o de Servi&ccedil;os de Saneamento</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="GerarOrdemServicoActionForm"/>

<script>

	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
  		var form = document.forms[0];
  		    		
    	if (tipoConsulta == 'atividade') {
	 	  	form.idAtividade.value = codigoRegistro;
	 	  	form.descricaoAtividade.value = descricaoRegistro; 
 		}else if (tipoConsulta == 'servicoTipo') {
	    	form.idServicoTipo.value = codigoRegistro;
		    form.descricaoServicoTipo.value = descricaoRegistro;
 		}
  	}
  	
	function validarForm(actionTipo) {
		var form = document.forms[0];
		if(validateGerarOrdemServicoActionForm(form)){
			if(form.idOrdemServicoReferencia != null && form.idOrdemServicoReferencia.value == ''){
		    	if(form.indExistenciaOSRef.value == '1'){
		     		alert("Informe Ordem de Serviço de Referência.");
		    	}else{
		      		if(form.idServicoTipoReferencia.value == ''){
		       			alert("Informe Ordem de Serviço de Referência ou Tipo de Serviço de Referência.");
		      		}else{
						if (actionTipo == 'true'){
		  					form.action = "/gsan/gerarOrdemServicoAction.do?veioAcompanhamentoRoteiro=true&numeroRa="+form.idRegistroAtendimento.value;
		  					form.submit();
		  				}else{
			    			form.action = "/gsan/gerarOrdemServicoAction.do";
	    		  			form.submit();
		  				}
	   		  		}
		    	}
			}else{
				if(form.idOrdemServicoReferencia != null && form.idOrdemServicoReferencia.value !=''){
		       		if(form.indExistenciaOSRef.value != '1' && form.idServicoTipoReferencia.value != ''){
		       			alert("Informe Ordem de Serviço de Referência ou Tipo de Serviço de Referência.");
		       		}else{
		    	   		if (actionTipo == 'true'){
			  				form.action = "/gsan/gerarOrdemServicoAction.do?veioAcompanhamentoRoteiro=true&numeroRa="+form.idRegistroAtendimento.value;
			  				form.submit();
			  			}else{
				    		form.action = "/gsan/gerarOrdemServicoAction.do";
		    		  		form.submit();
			  			}
		       		}
		    	}else{ 
			    	if (actionTipo == 'true'){
			  			form.action = "/gsan/gerarOrdemServicoAction.do?veioAcompanhamentoRoteiro=true&numeroRa="+form.idRegistroAtendimento.value;
			  			form.submit();
			  		}else{
				    	form.action = "/gsan/gerarOrdemServicoAction.do";
		    		  	form.submit();
			  		}
	   	  		}
			}
		}
	}
	
	function reload() {
		var form = document.GerarOrdemServicoActionForm;
		if (actionTipo == 'true'){
			form.action = "/gsan/gerarOrdemServicoAction.do?veioAcompanhamentoRoteiro=true&numeroRa="+form.idRegistroAtendimento.value;
  			form.submit();
		}else{
			form.action = "/gsan/exibirGerarOrdemServicoAction.do";
			form.submit();
		}
	}  

	/* Limpa Form */	 
	function limparForm() {
		var form = document.forms[0];
		if (form.idServicoTipo != undefined) {
    		form.idServicoTipo.value = "";
    	}
		if (form.descricaoServicoTipo != undefined) {
	    	form.descricaoServicoTipo.value = "";
	    }
	    if (form.idServicoTipoReferencia != undefined) {
	    	form.idServicoTipoReferencia.value = "";
	    }
	    if (form.descricaoServicoTipoReferencia != undefined) {	    
		    form.descricaoServicoTipoReferencia.value = "";	    
		}
	    if (form.idOrdemServicoReferencia != undefined) {	    		
    		form.idOrdemServicoReferencia.value = "";
    	}
	    if (form.descricaoOrdemServicoReferencia != undefined) {	    		    	
	    	form.descricaoOrdemServicoReferencia.value = "";
	    }
	    form.observacao.value = "";
	    form.valorServicoOriginal.value = "";
	    form.valorServicoAtual.value = "";
	    form.descricaoPrioridadeServicoOriginal.value = "";
	    form.idPrioridadeServicoAtual.selectedIndex = 0;
	    form.action = 'exibirGerarOrdemServicoAction.do';
		form.submit();
	}  
	
	function limpar(tipo) {
		var form = document.forms[0];
		if (tipo == 'ordemServicoReferencia') {
	    	form.idOrdemServicoReferencia.value = "";
		    form.descricaoOrdemServicoReferencia.value = "";
		} else if (tipo == 'servicoTipo') {
	    	form.idServicoTipo.value = "";
		    form.descricaoServicoTipo.value = "";
		} else if (tipo == 'servicoTipoReferencia') {
	    	form.idServicoTipoReferencia.value = "";
		    form.descricaoServicoTipoReferencia.value = "";
		}
	}  
	
	function chamarPopup(url, tipo, objeto, codigoObjeto, altura, largura, msg,objetoRelacionado) {
		if(objetoRelacionado.disabled != true) {
			if (objeto == null || codigoObjeto == null) {
				abrirPopup(url + "?" + "tipo=" + tipo, altura, largura);
			} else {
				if (codigoObjeto.length < 1 || isNaN(codigoObjeto)) {
					alert(msg);
				} else {
					abrirPopup(url + "?" + "tipo=" + tipo + "&" + objeto + "=" + codigoObjeto + "&caminhoRetornoTelaPesquisa=" + tipo, altura, largura);
				}
			}
		}
	}	 
</script>
</head>

<body leftmargin="5" topmargin="5" onload="window.focus();setarFoco('${requestScope.nomeCampo}');resizePageSemLink(750, 550);">
<html:form action="/exibirGerarOrdemServicoAction" method="post">

	<html:hidden property="idRegistroAtendimento" />
	<html:hidden property="forward" />
	
	<table width="730" border="0" cellspacing="5" cellpadding="0">
  		<tr width="100%"> 
    		<td valign="top" class="centercoltext" colspan="2"> 
    			<table width="100%" height="100%">
        			<tr> 
          				<td></td>
        			</tr>
      			</table>
      			<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
	        		<tr> 
	          			<td width="11"><img src="imagens/parahead_left.gif" border="0" /></td>
	          			<td class="parabg">Gerar Ordem de Servi&ccedil;o</td>
	          			<td width="11"><img src="imagens/parahead_right.gif" border="0" /></td>
	        		</tr>
      			</table>
      			<p>&nbsp;</p>
      			<table width="100%" border="0">
        			<tr> 
          				<td colspan="2">Para gerar uma ordem de servi&ccedil;o, informe os dados abaixo:.</td>
        			</tr>
        			<tr bgcolor="#cbe5fe"> 
          				<td align="center" colspan="2"> 
          					<table width="100%" border="0" bgcolor="#99CCFF">
	              				<tr bgcolor="#99CCFF"> 
	                				<td height="18" colspan="2"><div align="center"><span class="style2">Dados do Registro de Atendimento</span></div></td>
	              				</tr>
	              				<tr bgcolor="#cbe5fe"> 
	                				<td colspan="2"> 
	                					<table border="0" width="100%">
	                						
	                						<!-- Número do RA e Situação RA -->
	                    					
	                    					<tr> 
	                      						<td width="21%">
	                      							<strong>N&uacute;mero do RA:</strong>
	                      						</td>

	                      						<td width="8%">

	                      							<strong>
	                      							<font size="1">
							          				<html:text property="idRegistroAtendimento" 
							          					   	   size="6" 
							          					       maxlength="6" 
							          					       readonly="true" 
							          					       style="background-color:#EFEFEF; border:0" 
							          					       value="${registroAtendimento.id}" />
	                        						
	                      							</font>
	                      							</strong>
	                      						</td>
	                      						
	                      						<td width="14%"><strong>Situa&ccedil;&atilde;o do RA:</strong></td>
	                      						<td width="56%"><span class="style3"><strong>
	                      						
							          				<html:text property="situacaoRegistroAtendimento" 
							          					   	   size="50" 
							          					       maxlength="50" 
							          					       readonly="true" 
							          					       style="background-color:#EFEFEF; border:0" 
							          					       value="${registroAtendimento.descricaoSituacao}" />

	                      							</strong></span>
	                      						</td>
	                    					</tr>
	                    					
	                    					<!-- Tipo de Solicitação -->
	                    					
	                    					<tr> 
	                      						<td width="21%">
	                      							<strong>Tipo de Solicita&ccedil;&atilde;o:</strong>
	                      						</td>
	                      						
	                      						<td colspan="3"><span class="style3"><strong><font size="1">

							          				<html:text property="idSolicitacaoTipo" 
							          					   	   size="6" 
							          					       maxlength="6" 
							          					       readonly="true" 
							          					       style="background-color:#EFEFEF; border:0" 
							          					       value="${registroAtendimento.solicitacaoTipoEspecificacao.solicitacaoTipo.id}" />

							          				<html:text property="descricaoSolicitacaoTipo" 
							          					   	   size="67" 
							          					       maxlength="50" 
							          					       readonly="true" 
							          					       style="background-color:#EFEFEF; border:0" 
							          					       value="${registroAtendimento.solicitacaoTipoEspecificacao.solicitacaoTipo.descricao}" />

		                      						</font></strong></span> 
		                      					</td>
	                    					</tr>
	                    					
	                    					<!-- Especificação -->
	                    					
	                    					<tr>
	                      						<td class="style3"><strong>Especifica&ccedil;&atilde;o:</strong></td>
	                      						<td colspan="3"><span class="style3"><strong><font size="1">
	                      						
							          				<html:text property="idEspecificacao" 
							          					   	   size="6" 
							          					       maxlength="6" 
							          					       readonly="true" 
							          					       style="background-color:#EFEFEF; border:0" 
							          					       value="${registroAtendimento.solicitacaoTipoEspecificacao.id}" />

							          				<html:text property="descricaoEspecificacao" 
							          					   	   size="67" 
							          					       maxlength="50" 
							          					       readonly="true" 
							          					       style="background-color:#EFEFEF; border:0" 
							          					       value="${registroAtendimento.solicitacaoTipoEspecificacao.descricao}" />

	                      							</font></strong></span>
	                      						</td>
	                    					</tr>
	                    					
	                    					<!-- Unidade Atual -->
	                    					
	                    					<tr> 
	                      						<td class="style3"><b>Unidade Atual:</b></td>
	                      						<td colspan="3"> 
	                      							<span class="style2">
	                      								<strong><span class="style3"><strong><font size="1">
	                      							
							          				<html:text property="idUnidadeAtual" 
							          					   	   size="6" 
							          					       maxlength="6" 
							          					       readonly="true" 
							          					       style="background-color:#EFEFEF; border:0" 
							          					       value="${unidadeAtualRA.id}" />

							          				<html:text property="descricaoUnidadeAtual" 
							          					   	   size="67" 
							          					       maxlength="50" 
							          					       readonly="true" 
							          					       style="background-color:#EFEFEF; border:0" 
							          					       value="${unidadeAtualRA.descricao}" />

	                      							</font></strong></span> 
	                        						</strong></span> 
	                        					</td>
	                    					</tr>
	                  					</table>
	                  				</td>
	              				</tr>
	              			</table>
            			</td>
        			</tr>
        <tr bgcolor="#cbe5fe"> 
        	<td align="center" colspan="2"> 
        		<table width="100%" border="0" bgcolor="#99CCFF">
              		<tr bgcolor="#99CCFF"> 
                		<td height="18" colspan="2">
                			<div align="center"><span class="style2">Dados da Ordem de Servi&ccedil;o</span></div>
                		</td>
              		</tr>
              		<tr bgcolor="#cbe5fe"> 
                		<td> 
                			<table border="0" width="100%">
                			
                				<!-- Tipo de Serviço -->
                				
                				<c:choose>
	                				<c:when test="${empty colecaoServicosTipo}">
		                    			<tr> 
		                      				<td>
		                      					<strong>Tipo de Servi&ccedil;o:<font color="#FF0000">*</font></strong>
		                      				</td>

		                      				<td colspan="3">
		                      					<strong>
		                        				
		                        				<%if(request.getParameter("veioAcompanhamentoRoteiro") != null && request.getParameter("veioAcompanhamentoRoteiro").equals("true")){%>
									              	<html:text property="idServicoTipo" 
														   size="7"
														   maxlength="4" 
														   onkeyup="validaEnterComMensagem(event, 'exibirGerarOrdemServicoAction.do?veioAcompanhamentoRoteiro=true&numeroRA=${registroAtendimento.id}', 'idServicoTipo', 'Serviço Tipo');" /> 
									            <%}else{%>
													<html:text property="idServicoTipo" 
														   size="7"
														   maxlength="4" 
														   onkeyup="validaEnterComMensagem(event, 'exibirGerarOrdemServicoAction.do', 'idServicoTipo', 'Serviço Tipo');" />
												<%}%>
		                        				
		                        				<img src="imagens/pesquisa.gif" 
		                        					 width="23" 
		                        					 height="21"
                    					 			 style="cursor: hand;"
                 					    			 onclick="chamarPopup('exibirPesquisarTipoServicoAction.do', 'servicoTipo', null, null, 300, 620, '','');" 
                 					    			 alt="Pesquisar"> 
		                        				
		                        				<html:text property="descricaoServicoTipo" 
		                        					readonly="true" 
		                        					style="background-color:#EFEFEF; border:0" 
		                        					size="50" 
		                        					maxlength="50" />
		                        					
		                        				<a href="javascript:limpar('servicoTipo');">
		                        					<img src="imagens/limparcampo.gif" 
		                        						 width="23" 
		                        						 height="21"
		                        						 border="0"
	                  			 						 title="Apagar"></a>
		                        				</strong>
		                        			</td>
		                    			</tr>
		                    		</c:when>
									<c:otherwise>
		                    			<tr>
		                      				<td>
		                      					<span class="style3">
		                      					<strong>Tipo de Servi&ccedil;o:<font color="#FF0000">*</font></strong>
		                      					</span>
		                      				</td>
		                      				
		                      				<td colspan="3">
		                      					<strong>
			                     				<%if(request.getParameter("veioAcompanhamentoRoteiro") != null && request.getParameter("veioAcompanhamentoRoteiro").equals("true")){%>
									              	<html:select property="idServicoTipo" onchange="javascript:reload('true');">
														<html:option value="-1">&nbsp;</html:option>
														<html:options collection="colecaoServicosTipo" labelProperty="descricao" property="id" />
													</html:select> 
									            <%}else{%>
													<html:select property="idServicoTipo" onchange="javascript:reload('false');">
														<html:option value="-1">&nbsp;</html:option>
														<html:options collection="colecaoServicosTipo" labelProperty="descricao" property="id" />
													</html:select> 
												<%}%>
		                      					</strong>
		                      				</td>
		                    			</tr>
									</c:otherwise>
								</c:choose>
                    			
                    			<c:if test="${servicoTipo.servicoTipoReferencia != null}">
								  <html:hidden property="indExistenciaOSRef" value="${servicoTipo.servicoTipoReferencia.indicadorExistenciaOsReferencia}"/>
	                    			<c:choose>
	                    			
	                    				<c:when test="${servicoTipo.servicoTipoReferencia.indicadorExistenciaOsReferencia == '1'}">
	
			                    			<!-- Ordem de Serviço de Referência -->
	                    			
			                    			<tr> 
			                      				<td width="21%">
			                      					<strong>Ordem de Servi&ccedil;o de Refer&ecirc;ncia:<font color="#FF0000">*</font></strong>
												</td>
			                      				<td colspan="3">
			                      					<strong> 
			                      					<span class="style2"> 
			                        		
			                        				<html:text property="idOrdemServicoReferencia" 
			                        						   size="7" 
			                        						   maxlength="9" 
			                        						   onkeyup="validaEnterComMensagem(event, 'exibirGerarOrdemServicoAction.do', 'idOrdemServicoReferencia', 'Ordem de Serviço de Referência');" />
			                        		
			                        				<img src="imagens/pesquisa.gif" 
			                        					 width="23" 
			                        					 height="21"
			                        					 style="cursor: hand;"
                       					    			 onclick="chamarPopup('exibirPesquisarOrdemServicoAction.do', 'ordemServicoReferencia', null, null, 300, 620, '','');" 
                       					    			 alt="Pesquisar"> 
			                        		
			                        				<html:text property="descricaoOrdemServicoReferencia" 
			                        					readonly="true" 
			                        					style="background-color:#EFEFEF; border:0" 
			                        					size="50" 
			                        					maxlength="50" />
			                        					
			                        				<a href="javascript:limpar('ordemServicoReferencia');">
			                        					<img src="imagens/limparcampo.gif" 
			                        						 width="23" 
			                        						 height="21"
			                        						 border="0"
		                  			 						 title="Apagar"></a>
			                        				</span>
			                        				</strong> 
			                        			</td>
			                    			</tr>
	
										</c:when>
										
										<c:otherwise>
                                            <tr> 
			                      				<td width="21%"><strong> <span class="style3"><strong>Ordem de Servi&ccedil;o de Refer&ecirc;ncia:</strong></span></td>
			                      				<td colspan="3">
			                      					<strong> <b><span class="style2"> 
			                        				<html:text property="idOrdemServicoReferencia" 
			                        						   size="7" 
			                        						   maxlength="9" 
			                        						   onkeyup="validaEnterComMensagem(event, 'exibirGerarOrdemServicoAction.do', 'idOrdemServicoReferencia', 'Ordem de Serviço de Referência');" />
			                        				<img src="imagens/pesquisa.gif" 
			                        					 width="23" 
			                        					 height="21"
			                        					 style="cursor: hand;"
                       					    			 onclick="chamarPopup('exibirPesquisarOrdemServicoAction.do', 'ordemServicoReferencia', null, null, 300, 620, '','');" 
                       					    			 alt="Pesquisar"> 
			                        				<html:text property="descricaoOrdemServicoReferencia" readonly="true" style="background-color:#EFEFEF; border:0" size="50" maxlength="50" />
			                        				<a href="javascript:limpar('ordemServicoReferencia');">
			                        					<img src="imagens/limparcampo.gif" 
			                        						 width="23" 
			                        						 height="21"
			                        						 border="0"
		                  			 						 title="Apagar"> 
			                        				</a>
			                        				</span></b> <b> </b> </strong> 
			                        			</td>
			                    			</tr>
											<!-- Tipo de Serviço Ordem de Referência -->
										             
	                    					<c:choose>
	                    			
	                    						<c:when test="${not empty servicoTipo.servicoTipoReferencia.servicoTipo}">
										                    			
					                    			<tr>
					                      				<td class="style3">
					                      					<strong>Tipo de Servi&ccedil;o de Refer&ecirc;ncia:</strong>
					                      				</td>
					                      				
					                      				<td colspan="3">
					                      					<strong>
					                      					<span class="style3">
					                        				
					                        				<html:text property="idServicoTipoReferencia" 
					                        					readonly="true" 
					                        					style="background-color:#EFEFEF; border:0" 
					                        					size="7" 
					                        					maxlength="4" 
					                        					value="${servicoTipo.servicoTipoReferencia.servicoTipo.id}" />
					                        				
					                        				<html:text property="descricaoServicoTipoReferencia" 
					                        					readonly="true" 
					                        					style="background-color:#EFEFEF; border:0" 
					                        					size="50" 
					                        					maxlength="50" 
					                        					value="${servicoTipo.servicoTipoReferencia.servicoTipo.descricao}" />
					                        					
					                        				</span>
					                        				</strong>
					                        			</td>
					                    			</tr>
					                    			
					                    		</c:when>

												<c:otherwise>
												
													<c:choose>
												
						                				<c:when test="${empty colecaoServicosTipo}">
				                    			
							                    			<tr>
							                      				<td class="style3">
							                      					<strong>Tipo de Servi&ccedil;o de Refer&ecirc;ncia:</strong>
							                      				</td>

							                      				<td colspan="3">
							                      					<strong>
							                      					<span class="style3">
							                        				
							                        				<html:text property="idServicoTipoReferencia" 
							                        						   size="7" 
							                        						   maxlength="4" 
							                        						   onkeyup="validaEnterComMensagem(event, 'exibirGerarOrdemServicoAction.do', 'idServicoTipoReferencia', 'Serviço Tipo de Referência');" />
							                        				
							                        				<img src="imagens/pesquisa.gif" 
							                        					 width="23" 
							                        					 height="21"
																		 style="cursor: hand;"
                       					    			 				 onclick="chamarPopup('exibirPesquisarTipoServicoAction.do', 'servicoTipoReferencia', null, null, 300, 620, '','');" 
                       					    			 				 alt="Pesquisar"> 
							                        				
																	<logic:present name="idServicoTipoEncontrada" scope="request">
																		
																		<html:text property="descricaoServicoTipoReferencia" 
																			size="42"
																			maxlength="30" 
																			readonly="true"
																			style="background-color:#EFEFEF; border:0; color: #000000" />
																	</logic:present> 
										
																	<logic:notPresent name="idServicoTipoEncontrada" scope="request">
																		
																		<html:text property="descricaoServicoTipoReferencia" 
																			size="42"
																			maxlength="30" 
																			readonly="true"
																			style="background-color:#EFEFEF; border:0; color: red" />
																			
																	</logic:notPresent>
							                        				
							                        				<a href="javascript:limpar('servicoTipoReferencia');">
						                        					<img src="imagens/limparcampo.gif" 
						                        						 width="23" 
						                        						 height="21"
						                        						 border="0"
					                  			 						 title="Apagar"></a>
					                  			 					
					                  			 					</span>
					                  			 					</strong>
							                        			</td>
							                    			</tr>
							                    			
							                    		</c:when>
							                    		
							                    		<c:otherwise>
							                    		
							                    			<tr> 
							                      				<td class="style3">
							                      					<strong>Tipo de Servi&ccedil;o de Refer&ecirc;ncia:</strong>
							                      				</td>
							                      				
							                      				<td colspan="3">
				
								             						<html:select property="idServicoTipoReferencia">
																		<html:option value="-1">&nbsp;</html:option>
																		<html:options collection="colecaoServicosTipo" labelProperty="descricao" property="id" />
																	</html:select> 
						                      				
							                      				</td>
							                    			</tr>
							                    			
							                    		</c:otherwise>
							                    	
							                    	</c:choose>
					                    			
					                    		</c:otherwise>
			                    			
			                    			</c:choose>
			                    			
										</c:otherwise>	
										
									</c:choose>	                    			
									
								</c:if>
								
                    			<!-- Observação -->
                    			
                    			<tr> 
                      				<td class="style3">
                      					<strong>Observa&ccedil;&atilde;o:</strong>
                      				</td>
                      				
                      				<td colspan="3">
                      					<strong>
                        				<html:textarea property="observacao" 
                        					cols="50" 
                        					rows="4" onkeyup=" validarTamanhoMaximoTextArea(this,200);" />
                        				</strong>
                        			</td>
                    			</tr>
                    			
                    			<tr> 

	                    			<!-- Valor do Seviço Original -->

                      				<td class="style3">
                      					<strong>Valor do Servi&ccedil;o Original:<font color="#FF0000">*</font></strong>
                      				</td>
                      				
                      				<td width="26%">
                      					<strong>
                        				<html:text property="valorServicoOriginal" 
                        					readonly="true" 
                        					style="background-color:#EFEFEF; border:0" 
                        					size="10" 
                        					maxlength="8" />
                        				</strong>
                        			</td>
                        			
                        			<!-- Valor do Serviço Atual -->
                        			
                      				<td width="17%"></td>
                      				<td width="36%">
                      					<strong>
                   						<html:hidden property="valorServicoAtual" />
										</strong>
									</td>
                    			</tr>
                    			
                    			<tr> 
                    			
                    				<!-- Prioridade do Serviço Original -->
                    			
                      				<td>
                      					<b>Prioridade do Tipo Servi&ccedil;o Original:</b>
                      					<font color="#FF0000">*</font>
                      				</td>
                      				
                      				<td>
                      				
                      					<html:hidden property="idPrioridadeServicoOriginal" />

                      					<strong>
				                        <html:text property="descricaoPrioridadeServicoOriginal" 
				                        	readonly="true" 
				                        	style="background-color:#EFEFEF; border:0" 
				                        	size="20" 
				                        	maxlength="20" />
										</strong>
				                    </td>
				                    
				                    <!-- Prioridade do Serviço Atual -->
				                    
                      				<td class="style3"><b>Prioridade do Servi&ccedil;o Atual:</b></td>
                      				<td>
                      					<strong>
                      				
                    					<html:select property="idPrioridadeServicoAtual">

											<html:option value="-1">&nbsp;</html:option>
											<html:options collection="colecaoServicoTipoPrioridade" 
												labelProperty="descricao" 
												property="id" />

										</html:select> 
										
										</strong>
                      				</td>
                    			</tr>
                  			</table>
                  		</td>
              		</tr>
            	</table>
            </td>
        </tr>
        <tr> 
          	<td width="42%" height="24">
          		<input type="button" 
          			name="Submit222" 
          			class="bottonRightCol" 
          			value="Voltar" 
          			onClick="javascript:history.back();"> 
            	
            	<input name="Submit223223" 
            		type="button" 
            		class="bottonRightCol" 
            		onClick="javascript:limparForm()" 
            		value="Desfazer"> 
          	</td>
          	<td width="58%">
          		<div align="right"> 
	              	<%if(request.getParameter("veioAcompanhamentoRoteiro") != null && request.getParameter("veioAcompanhamentoRoteiro").equals("true")){%>
		              	<input name="Submit223222" 
		              		type="button" 
		              		class="bottonRightCol" 
		              		onClick="javascript:validarForm('true');" value="Gerar">
		            <%}else{%>
						<input name="Submit223222" 
		              		type="button" 
		              		class="bottonRightCol" 
		              		onClick="javascript:validarForm('false');" value="Gerar">
					<%}%>
	           </div>
           	</td>
        </tr>
        <tr> 
          	<td height="24">&nbsp;</td>
          	<td>&nbsp;</td>
        </tr>
  	</table>
  	
</body>
</html:form>
</html:html>
        