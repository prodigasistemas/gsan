<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>

<%@ page import="gcom.util.Pagina, gcom.util.ConstantesSistema"%>
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
 		}	
  	}	

	function validarForm() {
		var form = document.forms[0];
		if(validateGerarOrdemServicoActionForm(form)){
		   if(form.idOrdemServicoReferencia != null && form.idOrdemServicoReferencia.value == ''){
		    if(form.indExistenciaOSRef != null){
		     if(form.indExistenciaOSRef.value == '1'){
		      alert("Informe Ordem de Serviço de Referência.");
		     }else{
		      if(form.idServicoTipoReferencia.value == ''){
		       alert("Informe Ordem de Serviço de Referência ou Tipo de Serviço de Referência.");
		      }else{
		       form.action = "/gsan/exibirGerarOrdemServicoInserirRAAction.do?close=true";
    		   form.submit();
    		  }
		     }
		    }else{
		     form.action = "/gsan/exibirGerarOrdemServicoInserirRAAction.do?close=true";
    		 form.submit();
		    }
		   }else{
		    if(form.idOrdemServicoReferencia != null && form.idOrdemServicoReferencia.value !=''){
		      if(form.indExistenciaOSRef != null){
		       if(form.indExistenciaOSRef.value != '1' && form.idServicoTipoReferencia.value != ''){
		       alert("Informe Ordem de Serviço de Referência ou Tipo de Serviço de Referência.");
		       }else{
		        form.action = "/gsan/exibirGerarOrdemServicoInserirRAAction.do?close=true";
    		    form.submit();
		       }
		      }else{
		       form.action = "/gsan/exibirGerarOrdemServicoInserirRAAction.do?close=true";
    		   form.submit();
		      } 
		    }else{ 
			 form.action = "/gsan/exibirGerarOrdemServicoInserirRAAction.do?close=true";
    		 form.submit();
    	  }
		}
	  }
	}
	
	function whenOnload() {
		if (${requestScope.close != null}) {
		    if(${requestScope.close == 'TRUE'}){
			 window.close(); 
			}else{
			 redirecionarSubmit('${requestScope.close}');
			}
		}
	}
	
	function reload() {
		var form = document.GerarOrdemServicoActionForm;
		form.action = "/gsan/exibirGerarOrdemServicoInserirRAAction.do";
		form.submit();
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
	    form.action = 'exibirGerarOrdemServicoInserirRAAction.do';
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

<body leftmargin="5" topmargin="5" onload="window.focus();setarFoco('${requestScope.nomeCampo}');whenOnload();resizePageSemLink(760, 450);">
<html:form action="/exibirGerarOrdemServicoInserirRAAction" method="post">

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
                		<td height="18" colspan="2"><div align="center"><span class="style2">Dados da Ordem de Servi&ccedil;o</span></div></td>
              		</tr>
              		<tr bgcolor="#cbe5fe"> 
                		<td colspan="2"> 
                			<table border="0" width="100%">
                			
                				<!-- Tipo de Serviço -->
                				<logic:present name="solicitacaoEspecificacaoOperacionalAgua">
                				 <tr>
                				  <td><strong>Tipo de Servi&ccedil;o:<font color="#FF0000">*</font></strong></td>
                				  <td colspan="3">
                				   <html:text property="idServicoTipo" readonly="true" size="7" maxlength="4" style="background-color:#EFEFEF; border:0"/>
                				   <html:text property="descricaoServicoTipo" readonly="true" style="background-color:#EFEFEF; border:0" size="50" maxlength="50" />
                				  </td>
                				 </tr>
                				 <tr>
                				  <td><strong>Tipo de Servi&ccedil;o de Refer&ecirc;ncia:<font color="#FF0000">*</font></strong></td>
                				  <td colspan="3">
                				   <html:text property="idServicoTipoReferencia" readonly="true" size="7" maxlength="4" style="background-color:#EFEFEF; border:0"/>
                				   <html:text property="descricaoServicoTipoReferencia" readonly="true" style="background-color:#EFEFEF; border:0" size="50" maxlength="50" />
                				  </td>
                				 </tr>  
                				</logic:present>
                				<logic:notPresent name="solicitacaoEspecificacaoOperacionalAgua">
                				
                				
                				<c:choose>
	                				<c:when test="${empty colecaoServicosTipo}">
		                    			<tr> 
		                      				<td><span class="style3"><strong><span class="style2">Tipo de Servi&ccedil;o:<strong><strong><font color="#FF0000"></font><span class="style3"><strong><span class="style2"><strong><font color="#FF0000">*</font></strong></span></strong></span></strong></strong></span></strong></span></td>
		                      				<td colspan="3">
		                      					<strong><b><span class="style2"> 
		                        				<html:text property="idServicoTipo" 
														   size="7" 
														   maxlength="4" 
														   readonly="true"
														   style="background-color:#EFEFEF; border:0" />&nbsp;
														   <!-- 
														   onkeyup="validaEnterComMensagem(event, 'exibirGerarOrdemServicoInserirRAAction.do', 'idServicoTipo', 'Serviço Tipo');" />
														   -->
												<!-- 
		                        				<img src="imagens/pesquisa.gif" 
		                        					 width="23" 
		                        					 height="21"
                    					 			 style="cursor: hand;"
                 					    			 onclick="chamarPopup('exibirPesquisarTipoServicoAction.do', 'servicoTipo', null, null, 300, 620, '','');" 
                 					    			 alt="Pesquisar"> 
                 					    		 -->
		                        				<html:text property="descricaoServicoTipo" readonly="true" style="background-color:#EFEFEF; border:0" size="50" maxlength="50" />
		                        				<!-- 
		                        				<a href="javascript:limpar('servicoTipo');">
		                        					<img src="imagens/limparcampo.gif" 
		                        						 width="23" 
		                        						 height="21"
		                        						 border="0"
	                  			 						 title="Apagar"> 
		                        				</a>
		                        				-->
		                        				</b></strong>
		                        			</td>
		                    			</tr>
		                    		</c:when>
									<c:otherwise>
		                    			<tr>
		                      				<td><span class="style3"><strong>Tipo de Servi&ccedil;o:<strong><strong><font color="#FF0000"></font><strong><strong><font color="#FF0000">*</font></strong></strong></strong></strong></strong></span></td>
		                      				<td colspan="3"><strong><b><span class="style3">
			                     				
			             						<html:select property="idServicoTipo" onchange="javascript:reload();">
													<html:option value="-1">&nbsp;</html:option>
													<html:options collection="colecaoServicosTipo" labelProperty="descricao" property="id" />
												</html:select> 
		                      				
		                      					</strong>
		                      				</td>
		                    			</tr>
									</c:otherwise>
								</c:choose>
								<logic:present name="veioEncerrarOS">
								         <tr> 
			                      				<td width="21%"><strong> <span class="style3"><strong>Ordem de Servi&ccedil;o de Refer&ecirc;ncia:</strong></span></td>
			                      				<td colspan="3">
			                      					<strong> <b><span class="style2"> 
			                        				<html:text property="idOrdemServicoReferencia" 
			                        						   size="7" 
			                        						   maxlength="9" readonly="true" style="background-color:#EFEFEF; border:0"/>&nbsp;
			                        				<html:text property="descricaoOrdemServicoReferencia" readonly="true" style="background-color:#EFEFEF; border:0" size="50" maxlength="50" />
			                        				</span></b> <b> </b> </strong> 
			                        			</td>
			                    			</tr>
								</logic:present>
                    			<logic:notPresent name="veioEncerrarOS">
                    			 <c:if test="${servicoTipo.servicoTipoReferencia != null}">
                                   <html:hidden property="indExistenciaOSRef" value="${servicoTipo.servicoTipoReferencia.indicadorExistenciaOsReferencia}"/>
	                    			<c:choose>
	                    			
	                    				<c:when test="${servicoTipo.servicoTipoReferencia.indicadorExistenciaOsReferencia == '1'}">
	
			                    			<!-- Ordem de Serviço de Referência -->
	                    			
			                    			<tr> 
			                      				<td width="21%"><strong> <span class="style3"><strong>Ordem de Servi&ccedil;o de Refer&ecirc;ncia:<font color="#FF0000">*</font></strong></span></td>
			                      				<td colspan="3">
			                      					<strong> <b><span class="style2"> 
			                        				<html:text property="idOrdemServicoReferencia" 
			                        						   size="7" 
			                        						   maxlength="9" 
			                        						   onkeyup="validaEnterComMensagem(event, 'exibirGerarOrdemServicoInserirRAAction.do', 'idOrdemServicoReferencia', 'Ordem de Serviço de Referência');" />
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
	
										</c:when>
										
										<c:otherwise>
											<tr> 
			                      				<td width="21%"><strong> <span class="style3"><strong>Ordem de Servi&ccedil;o de Refer&ecirc;ncia:</strong></span></td>
			                      				<td colspan="3">
			                      					<strong> <b><span class="style2"> 
			                        				<html:text property="idOrdemServicoReferencia" 
			                        						   size="7" 
			                        						   maxlength="9" 
			                        						   onkeyup="validaEnterComMensagem(event, 'exibirGerarOrdemServicoInserirRAAction.do', 'idOrdemServicoReferencia', 'Ordem de Serviço de Referência');" />
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
					                      				<td class="style3"><strong>Tipo de Servi&ccedil;o de Refer&ecirc;ncia:<strong><strong><font color="#FF0000"> </font></strong></strong></strong></td>
					                      				<td colspan="3">
					                      					<strong><b><span class="style3">
					                        				<html:text property="idServicoTipoReferencia" readonly="true" style="background-color:#EFEFEF; border:0" size="7" maxlength="4" value="${servicoTipo.servicoTipoReferencia.servicoTipo.id}" />
					                        				<html:text property="descricaoServicoTipoReferencia" readonly="true" style="background-color:#EFEFEF; border:0" size="50" maxlength="50" value="${servicoTipo.servicoTipoReferencia.servicoTipo.descricao}" />
					                        				</span></b></strong>
					                        			</td>
					                    			</tr>
					                    			
					                    		</c:when>

												<c:otherwise>
												
													<c:choose>
												
						                				<c:when test="${empty colecaoServicosTipo}">
				                    			
							                    			<tr>
							                      				<td class="style3"><strong>Tipo de Servi&ccedil;o de Refer&ecirc;ncia:<strong><strong><font color="#FF0000"> </font></strong></strong></strong></td>
							                      				<td colspan="3">
							                      					<strong><b><span class="style3">
							                        				<html:text property="idServicoTipoReferencia" 
							                        						   size="7" 
							                        						   maxlength="4" 
							                        						   onkeyup="validaEnterComMensagem(event, 'exibirGerarOrdemServicoInserirRAAction.do', 'idServicoTipoReferencia', 'Serviço Tipo de Referência');" />
							                        				<img src="imagens/pesquisa.gif" 
							                        					 width="23" 
							                        					 height="21"
																		 style="cursor: hand;"
                       					    			 				 onclick="chamarPopup('exibirPesquisarTipoServicoAction.do', 'servicoTipoReferencia', null, null, 300, 620, '','');" 
                       					    			 				 alt="Pesquisar"> 
							                        				<html:text property="descricaoServicoTipoReferencia" readonly="true" style="background-color:#EFEFEF; border:0" size="50" maxlength="50" />
							                        				<a href="javascript:limpar('servicoTipoReferencia');">
						                        					<img src="imagens/limparcampo.gif" 
						                        						 width="23" 
						                        						 height="21"
						                        						 border="0"
					                  			 						 title="Apagar"> 
			                        								</a></span></b></strong>
							                        			</td>
							                    			</tr>
							                    			
							                    		</c:when>
							                    		
							                    		<c:otherwise>
							                    		
							                    			<tr> 
							                      				<td class="style3"><strong><strong>Tipo de Servi&ccedil;o de Refer&ecirc;ncia:<strong><strong><font color="#FF0000"> </font></strong></strong></strong></strong></td>
							                      				<td colspan="3"><span class="style3">
				
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
								 </logic:notPresent> 
								</logic:notPresent>
								
                    			<!-- Observação -->
                    			
                    			<tr> 
                      				<td class="style3"><strong>Observa&ccedil;&atilde;o:</strong></td>
                      				<td colspan="3"><strong><b><span class="style2"> <strong> 
                        				<html:textarea property="observacao" cols="50" rows="4" />
                        				</strong> </span></b></strong>
                        			</td>
                    			</tr>
                    			
                    			<tr> 

	                    			<!-- Valor do Seviço Original -->

                      				<td class="style3"><strong>
                      				Valor do Servi&ccedil;o Original:<strong><span class="style2"><strong><strong><span class="style3"><strong><span class="style2"><strong><font color="#FF0000">*</font></strong></span></strong></span></strong></strong></span></strong></strong>
                      				</td>
                      				<td width="26%">
                      					<strong><b><span class="style2"> 
                        				<span class="style3"><strong>
                        				<html:text property="valorServicoOriginal" readonly="true" style="background-color:#EFEFEF; border:0" size="10" maxlength="8" />
                        				</strong></span></span></b></strong>
                        			</td>
                        			
                        			<!-- Valor do Serviço Atual -->
                        			
                      				<td width="17%"><span class="style3"><strong>
                      				<!-- 
                      				Valor do Servi&ccedil;o Atual:
                      				-->
                      				</strong></span></td>
                      				<td width="36%"><strong><b><span class="style3">
                      					<!-- 
                   						<html:text property="valorServicoAtual" size="16" maxlength="16" />
                   						 -->
                   						 <html:hidden property="valorServicoAtual" />
										</span></b></strong>
									</td>
                    			</tr>
                    			
                    			<tr> 
                    			
                    				<!-- Prioridade do Serviço Original -->
                    			
                      				<td><b>Prioridade do Tipo Servi&ccedil;o Original:</b><span class="style3"><strong><span class="style2"><strong><strong><span class="style3"><strong><span class="style2"><strong><font color="#FF0000">*</font></strong></span></strong></span></strong></strong></span></strong></span></td>
                      				<td>
                      				
                      					<html:hidden property="idPrioridadeServicoOriginal" />

                      					<strong><b><span class="style2"> 
				                        <b><span class="style3"><strong>
				                        <html:text property="descricaoPrioridadeServicoOriginal" readonly="true" style="background-color:#EFEFEF; border:0" size="20" maxlength="20" />
				                        </strong></span></b>
				                        </span></b></strong>
				                    </td>
				                    
				                    <!-- Prioridade do Serviço Atual -->
				                    
                      				<td class="style3"><b>Prioridade do Servi&ccedil;o Atual:</b></td>
                      				<td><strong><b><span class="style3">
                      				
                    					<html:select property="idPrioridadeServicoAtual">
											<html:option value="-1">&nbsp;</html:option>
											<html:options collection="colecaoServicoTipoPrioridade" labelProperty="descricao" property="id" />
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
          		<!-- 
          		<input type="button" name="Submit222" class="bottonRightCol" value="Voltar" onClick="javascript:window.location.href='comando_acao_cobranca_consultar_comando_eventual.htm'"> 
            	<input name="Submit223223" type="button" class="bottonRightCol" onClick="javascript:limparForm()" value="Desfazer"> 
            	 -->
          	</td>
          	<td width="58%"><div align="right"> 
              	<input name="Submit223222" type="button" class="bottonRightCol" onClick="javascript:validarForm();" value="Continuar"></div>
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
