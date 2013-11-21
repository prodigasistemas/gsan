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

<%-- Carrega validações do validator --%>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="TramitarRegistroAtendimentoActionForm"/>
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js"></script>

<script language="JavaScript">
	/* Valida Form */
	function validarForm() {
		var form = document.forms[0];
		if (validateTramitarRegistroAtendimentoActionForm(form)) {
			submeterFormPadrao(form);
		}
	}
	function textAreaMaxLength(maxlength){
		var form = document.forms[0];
		if(form.parecerTramite.value.length >= maxlength){
			window.event.keyCode = '';
		}
	}
	/* Extende Table */
	function extendeTabela(tabela,display){
		var form = document.forms[0];

		if(display){
 			eval('layerHide'+tabela).style.display = 'none';
 			eval('layerShow'+tabela).style.display = 'block';
		}else{
			eval('layerHide'+tabela).style.display = 'block';
 			eval('layerShow'+tabela).style.display = 'none';
		}
	}
	/* Limpar Unidade Destino */
	function limparUnidadeDestino() {
		var form = document.forms[0];
		
      	form.unidadeDestinoId.value = '';
	    form.unidadeDestinoDescricao.value = '';
	}
	/* Limpar Usuário Responsável */
	function limparUsuarioResponsavel() {
		var form = document.forms[0];
		
      	form.usuarioResponsavelId.value = '';
	    form.usuarioResponsavelNome.value = '';
	}
	/* Limpar Form */
	function limparForm() {
		var form = document.forms[0];
		
		limparUnidadeDestino();
		limparUsuarioResponsavel();
      	form.dataTramite.value = '';
	    form.horaTramite.value = '';
	    form.parecerTramite.value = '';
	}
	/* Chama Popup */ 
	function chamarPopup(url, tipo, objeto, codigoObjeto, altura, largura, msg,objetoRelacionado){
		if(objetoRelacionado.disabled != true){
			if (objeto == null || codigoObjeto == null){
				abrirPopup(url + "?" + "tipo=" + tipo, altura, largura);
			} else{
				if (codigoObjeto.length < 1 || isNaN(codigoObjeto)){
					alert(msg);
				} else{
					abrirPopup(url + "?" + "tipo=" + tipo + "&" + objeto + "=" + codigoObjeto + "&caminhoRetornoTelaPesquisa=" + tipo, altura, largura);
				}
			}
		}
	}
	/* Recuperar Popup */
	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
	    var form = document.forms[0];

	    if (tipoConsulta == 'usuario') {
	      form.usuarioResponsavelId.value = codigoRegistro;
	      form.usuarioResponsavelNome.value = descricaoRegistro;
	      form.usuarioResponsavelNome.style.color = '#000000';	 
	    } else if (tipoConsulta == 'unidadeOrganizacional') {
		      form.unidadeDestinoId.value = codigoRegistro;
		      form.action = 'exibirTramitarRegistroAtendimentoAction.do?validaUnidadeDestino=true';
		      form.submit();
		      //form.unidadeDestinoDescricao.value = descricaoRegistro;
		      //form.unidadeDestinoDescricao.style.color = '#000000';
	    }
	}
	/* Consultar Trâmites */
	function consultarTramites() {
		var form = document.forms[0];
		abrirPopup('exibirConsultarRegistroAtendimentoTramiteAction.do?numeroRA='+form.numeroRA.value, 550, 735);
	}
	/* Consultar Registro de Atendimento */	
	function consultarRA() {
		var form = document.forms[0];
		form.action = 'exibirConsultarRegistroAtendimentoAction.do?numeroRA='+form.numeroRA.value;
		form.submit();
	}
	
	function exibirTelaPavimento(){
		var EXIBIR = document.getElementById("INDICADOR_PAVIMENTO").value;
	
		if (EXIBIR == "sim"){
			abrirPopup('exibirInserirDadosPavimentoOrdemServicoPopupAction.do', 300, 700);
		}	
	}
</script>
</head>

<body leftmargin="5" topmargin="5" onload="javascript:exibirTelaPavimento();">

<html:form action="/tramitarRegistroAtendimentoAction.do?primeiraVez=ok"
	name="TramitarRegistroAtendimentoActionForm"
	type="gcom.gui.atendimentopublico.registroatendimento.TramitarRegistroAtendimentoActionForm"
	method="post">
	
	<input type="hidden" id="INDICADOR_PAVIMENTO" value="${requestScope.indicadorPavimento}" />
	
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
							<img border="0" src="<bean:message key="caminho.imagens"/>parahead_left.gif" />
						</td>
						<td class="parabg">Tramitar Registro de Atendimento</td>
						<td width="11">
							<img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif" />
						</td>
					</tr>
				</table>
			
				<table width="100%">
					<tr>
						<td width="90%" colspan="2">&nbsp;</td>
						<logic:present scope="application" name="urlHelp">
						<td align="right"><a href="javascript:abrirPopupHelp('${applicationScope.urlHelp}atendimentoRegistroTramitar', 500, 700);"><span style="font-weight: bold"><font color="#3165CE">Ajuda</font></span></a></td>									
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
                      		
	     					<div id="layerHideLocal" style="display:block">
	           					<table width="100%" border="0" bgcolor="#99CCFF">
		    						<tr bgcolor="#99CCFF">
	                     				<td align="center">
	                    					<span class="style2">
	                     					<a href="javascript:extendeTabela('Local',true);"/>
	                     						<b>Dados do Registro de Atendimento</b>
	                     					</a>
	                    					</span>
	                     				</td>
	                    			</tr>
	                   			</table>
	           				</div>
							
							<div id="layerShowLocal" style="display:none">
							<table width="100%" border="0" bgcolor="#99CCFF">
		    					<tr bgcolor="#99CCFF">
	                     			<td align="center">
                    					<span class="style2">
                     					<a href="javascript:extendeTabela('Local',false);"/>
                     						<b>Dados do Registro de Atendimento</b>
                     					</a>
                    					</span>
	                     			</td>
	                    		</tr>

								<tr bgcolor="#cbe5fe">
									<td>
									<table border="0" width="100%">

		                            	<tr>
		                              		<td height="10"><strong>N&uacute;mero do RA:</strong></td>
		                              		
		                              		<td width="10%"> 
												<html:text property="numeroRA" readonly="true"
													style="background-color:#EFEFEF; border:0;" size="9"/>
													&nbsp
												<strong>Situa&ccedil;&atilde;o do RA:</strong>
												<html:text property="situacaoRA" readonly="true"
													style="background-color:#EFEFEF; border:0;" size="20"/>	
		                                  	</td>
		                            	</tr>
		                            	
										<c:if test="${TramitarRegistroAtendimentoActionForm.numeroRaAssociado != null}">
			                            <tr> 
		                            		<td height="10"><strong>N&uacute;mero do RA Associado:</strong></td>
		                              		<td> 
												<html:text property="numeroRaAssociado" readonly="true"
													style="background-color:#EFEFEF; border:0;" size="9"/>
													&nbsp
		                              			<strong>Situa&ccedil;&atilde;o do RA Associado:</strong>
												<html:text property="situacaoRaAssociado" readonly="true"
													style="background-color:#EFEFEF; border:0;" size="12"/>
		                                	</td>
			                            </tr>
										</c:if>
										
		                            	<tr> 
		                              		<td class="style3"><strong>Tipo de Solicita&ccedil;&atilde;o:</strong></td>
		                              		<td colspan="3">
												<html:text property="tipoSolicitacaoId" readonly="true"
													style="background-color:#EFEFEF; border:0;" size="4"/>		                              		
												<html:text property="tipoSolicitacaoDescricao" readonly="true"
													style="background-color:#EFEFEF; border:0;" size="45"/>
		                                	</td>
		                            	</tr>

		                            	<tr> 
		                              		<td height="10"><strong>Especifica&ccedil;&atilde;o:</strong></td>
		                              		<td colspan="3"> 
												<html:text property="especificacaoId" readonly="true"
													style="background-color:#EFEFEF; border:0;" size="4"/>		                              		
												<html:text property="especificacaoDescricao" readonly="true"
													style="background-color:#EFEFEF; border:0;" size="45"/>
		                                 	</td>
		                            	</tr>

			                            <tr>
			                              	<td class="style3"><strong>Meio de Solicita&ccedil;&atilde;o:</strong></td>
			                              	<td colspan="3">
												<html:text property="meioSolicitacaoDescricao" readonly="true"
													style="background-color:#EFEFEF; border:0;" size="50"/>
											</td>
			                            </tr>

	                              		<tr>
	                                		<td><strong>Matr&iacute;cula do Im&oacute;vel:</strong></td>
	                                		<td colspan="3"> 
												<html:text property="matriculaImovel" readonly="true"
													style="background-color:#EFEFEF; border:0;" size="8"/>
												<html:text property="inscricaoImovel" readonly="true"
													style="background-color:#EFEFEF; border:0;" size="21"/>
											</td>
                              			</tr>

			                            <tr> 
			                              	<td width="31%" height="10"><strong>Data e Hora do Atendimento:</strong></td>
			                              	<td colspan="3"> 
												<html:text property="dataAtendimento" readonly="true"
													style="background-color:#EFEFEF; border:0;" size="10"/>
												<html:text property="horaAtendimento" readonly="true"
													style="background-color:#EFEFEF; border:0;" size="5" maxlength="5" />
											</td>
			                            </tr>

			                            <tr> 
			                              	<td class="style3"><strong>Data Prevista:</strong></td>
			                              	<td colspan="3">
												<html:text property="dataPrevista" readonly="true"
													style="background-color:#EFEFEF; border:0;" size="10"/>
											
											</td>
			                            </tr>
			                            
										<tr>
                                    		<td class="style3">
                                    			<strong>Data da Encerramento:</strong>
                                    		</td>

                                    		<td>
												<html:text property="dataEncerramento" readonly="true"
													style="background-color:#EFEFEF; border:0;" size="10"/>
											</td>
                                  		</tr>

										<tr>
                                    		<td class="style3"><strong>Motivo do Encerramento:</strong></td>
                                    		<td>
												<html:text property="idMotivoEncerramento" readonly="true"
													style="background-color:#EFEFEF; border:0;" size="5"/>
												<html:text property="motivoEncerramento" readonly="true"
													style="background-color:#EFEFEF; border:0;" size="45"/>
											</td>

                                  		</tr>

	                              		<tr> 
	                                		<td height="10" colspan="4"> 
	                                		<div align="right"> 
	                                    	<hr>
	                                  		</div>
	                                  		<div align="right"> </div></td>
	                              		</tr>
			                            
										<tr> 	
                                    		<td class="style3"><strong>Cliente Solicitante:</strong></td>
                                    		<td colspan="3">
												<html:text property="idClienteSolicitante" readonly="true"
													style="background-color:#EFEFEF; border:0;" size="4"/>
													<html:text property="clienteSolicitante" readonly="true"
														style="background-color:#EFEFEF; border:0;" size="40"/>
											</td>
                                  		</tr>

										<tr> 	
                                    		<td class="style3">
                                    			<strong>Unidade Solicitante:</strong>
                                    		</td>
                                    		<td colspan="3">
												<html:text property="idUnidadeSolicitante" readonly="true"
													style="background-color:#EFEFEF; border:0;" size="4"/>
												<html:text property="unidadeSolicitante" readonly="true"
													style="background-color:#EFEFEF; border:0;" size="40"/>
											</td>
                                  		</tr>

										<tr>
                                    		<td class="style3"><strong>Nome do Solicitante:</strong></td>
                                    		<td colspan="3">
												<html:text property="nomeSolicitante" readonly="true"
													style="background-color:#EFEFEF; border:0;" size="40"/>
											</td>
                                  		</tr>

	                              		<tr> 
	                                		<td height="10" colspan="4"> 
	                                		<div align="right"> 
	                                    	<hr>
	                                  		</div>
	                                  		<div align="right"> </div></td>
	                              		</tr>
			                            
		                               	<tr> 
			                              	<td class="style3">
			                              		<strong><span class="style2">Endere&ccedil;o da Ocorr&ecirc;ncia:</span></strong>
			                              	</td>
		                                  
		                                  	<td>
												<html:textarea property="enderecoOcorrencia" readonly="true"
												style="background-color:#EFEFEF; border:0;" cols="43"/>
		                                  	</td>
		                            	</tr>
                            	
		                               	<tr>
		                                 	<td class="style3"><strong>Ponto de Refer&ecirc;ncia:</strong></td>
		                                 	
		                                 	<td>
												<html:text property="pontoReferencia" readonly="true"
													style="background-color:#EFEFEF; border:0;" size="55"/>
		                                 	</td>
		                               	</tr>

	                              		<tr> 
	                                		<td class="style3">
	                                			<strong>Bairro:</strong>
	                                		</td>
	                                		<td>
												<html:text property="bairroId" readonly="true"
													style="background-color:#EFEFEF; border:0;" size="4"/>
				
												<html:text property="bairroDescricao" readonly="true"
													style="background-color:#EFEFEF; border:0;" size="40"/>
											</td>
	                              		</tr>
	
	                              		<tr> 
	                                		<td class="style3">
	                                			<strong>&Aacute;rea do Bairro:</strong>
	                                		</td>
	                                		<td>
												<html:text property="areaBairroId" readonly="true"
													style="background-color:#EFEFEF; border:0;" size="4"/>
				
												<html:text property="areaBairroDescricao" readonly="true"
													style="background-color:#EFEFEF; border:0;" size="40"/>
											</td>
                              			</tr>
                              			
										<tr> 	
	                                		<td class="style3">
	                                			<strong>Local/Setor/Quadra:</strong>
	                                		</td>
	                                		<td>
												<html:text property="localidadeId" readonly="true"
													style="background-color:#EFEFEF; border:0;" size="4"/>
												<strong>/</strong>
												<html:text property="setorComercialId" readonly="true"
													style="background-color:#EFEFEF; border:0;" size="4"/>
												<strong>/</strong>
												<html:text property="quadraId" readonly="true"
													style="background-color:#EFEFEF; border:0;" size="4"/>
											</td>
                              			</tr>

										<tr> 	
	                                		<td class="style3">
	                                			<strong>Divis&atilde;o de Esgoto:</strong>
	                                		</td>
	                                		<td colspan="3">
												<html:text property="divisaoEsgotoId" readonly="true"
													style="background-color:#EFEFEF; border:0;" size="4"/>
				
												<html:text property="divisaoEsgotoDescricao" readonly="true"
													style="background-color:#EFEFEF; border:0;" size="40"/>
											</td>
	                              		</tr>

	                              		<tr> 
	                                		<td height="10" colspan="4"> 
	                                		<div align="right"> 
	                                    	<hr>
	                                  		</div>
	                                  		<div align="right"> </div></td>
	                              		</tr>
			                            
			                            
			                            <tr> 
			                              	<td class="style3"><strong>Unidade de Atendimento:</strong></td>
			                              	<td colspan="3">
												<html:text property="unidadeAtendimentoId" readonly="true"
													style="background-color:#EFEFEF; border:0;" size="4"/>

												<html:text property="unidadeAtendimentoDescricao" readonly="true"
													style="background-color:#EFEFEF; border:0;" size="40"/>
											</td>
			                            </tr>

			                            <tr> 
			                              	<td class="style3"><strong>Unidade Atual:</strong></td>
			                              	<td colspan="3">
												<html:text property="unidadeAtualId" readonly="true"
													style="background-color:#EFEFEF; border:0;" size="4"/>

												<html:text property="unidadeAtualDescricao" readonly="true"
													style="background-color:#EFEFEF; border:0;" size="40"/>
											</td>
			                            </tr>

									</table>
									</td>
								</tr>
							</table>
							</div>
							
							</td>
						</tr>	
               		</table>
			  		</td>
           		</tr>
				<tr> 
                	<td height="10" colspan="2">Para tramitar o registro de atendimento, informe os dados abaixo:</td>
                 </tr>
				<!-- Dados do Local Ocorrencia -->
               	<tr bgcolor="#cbe5fe">
           			<td align="center">
                   		<table width="100%" border="0" bgcolor="#99CCFF">
	    					<tr bgcolor="#99CCFF">
                     			<td align="center">
                   					<span class="style2">
                    						<b>Dados da Tramita&ccedil;&atilde;o</b>
                   					</span>
                     			</td>
                    		</tr>

							<tr bgcolor="#cbe5fe">
								<td>
									<table border="0" width="100%">

										<tr> 	
                                    		<td class="style3">
                                    			<strong>Unidade Destino:</strong>
                                    			<font color="#FF0000">*</font>
                                    		</td>
                                    		<td>
                								<span class="style2">
                									<strong>
														<html:text property="unidadeDestinoId" size="4" maxlength="4"
							   									   onkeypress="javascript:validaEnterComMensagem(event, 'exibirTramitarRegistroAtendimentoAction.do?validaUnidadeDestino=true', 'unidadeDestinoId','Unidade Destino');return isCampoNumerico(event);"/>
														<img src="<bean:message key='caminho.imagens'/>pesquisa.gif" width="23"	height="21" 
						 									 style="cursor: pointer;cursor:hand;" name="imagem"	onclick="chamarPopup('exibirPesquisarUnidadeOrganizacionalAction.do', 'unidadeOrganizacional', null, null, 275, 480, '',document.forms[0].unidadeAtendimentoId);"
						 									 alt="Pesquisar" title="Pesquisar Unidade Destino">
						 									 
														<logic:present name="unidadeDestinoEncontrada" scope="session">
															<html:text property="unidadeDestinoDescricao" readonly="true"
								   									   style="background-color:#EFEFEF; border:0; color: #000000" size="45"/>
														</logic:present> 
														<logic:notPresent name="unidadeDestinoEncontrada" scope="session">
															<html:text property="unidadeDestinoDescricao" readonly="true"
								   									   style="background-color:#EFEFEF; border:0; color: #ff0000" size="45"/>
														</logic:notPresent>
														
														<a href="javascript:limparUnidadeDestino();">
															<img border="0" title="Apagar" src="<bean:message key='caminho.imagens'/>limparcampo.gif" /></a>
                  									</strong>
                  								</span>
											</td>
                                  		</tr>
	                              	
										<tr> 	
                                    		<td class="style3">
                                    			<strong>Usu&aacute;rio Respons&aacute;vel:</strong>
                                    			<font color="#FF0000">*</font>
                                    		</td>
                                    		<td colspan="3">
                								<span class="style2">
                									<strong>
														<html:text property="usuarioResponsavelId" size="9" maxlength="9"
							   									   onkeypress="javascript:validaEnterComMensagem(event, 'exibirTramitarRegistroAtendimentoAction.do?validaUsuarioResponsavel=true', 'usuarioResponsavelId','Usuário Responsável');return isCampoNumerico(event);"/>
														<img src="<bean:message key='caminho.imagens'/>pesquisa.gif" width="23"	height="21" 
						 									 style="cursor: pointer;cursor:hand;" name="imagem"	onclick="chamarPopup('exibirUsuarioPesquisar.do', 'usuario', null, null, 275, 480, '',document.forms[0].usuarioResponsavelId);"
						 									 alt="Pesquisar" title="Pesquisar Usuário Responsável">

														<logic:present name="usuarioResponsavelEncontrada" scope="session">
															<html:text property="usuarioResponsavelNome" readonly="true"
								   									   style="background-color:#EFEFEF; border:0; color: #000000" size="45"/>
														</logic:present> 
														<logic:notPresent name="usuarioResponsavelEncontrada" scope="session">
															<html:text property="usuarioResponsavelNome" readonly="true"
								   									   style="background-color:#EFEFEF; border:0; color: #ff0000" size="45"/>
														</logic:notPresent>

														<a href="javascript:limparUsuarioResponsavel();">
															<img border="0" title="Apagar" src="<bean:message key='caminho.imagens'/>limparcampo.gif" /></a>
                  									</strong>
                  								</span>
											</td>
                                  		</tr>
                                 		
										<tr> 	
                                    		<td class="style3">
                                    			<strong>Data da Tramita&ccedil;&atilde;o:</strong>
                                    			<font color="#FF0000">*</font>
                                    		</td>
                                    		<td colspan="3">
                                    			<span class="style2">
                                    				<strong> 
														<html:text property="dataTramite" 
																   size="11" 
																   maxlength="10" 
																   tabindex="3" 
																   onkeypress="return isCampoNumerico(event);"
																   onkeyup="mascaraData(this, event);"/>
														<a href="javascript:abrirCalendario('TramitarRegistroAtendimentoActionForm', 'dataTramite');">
															<img border="0" 
																 src="<bean:message key='caminho.imagens'/>calendario.gif" 
																 width="16" 
																 height="15" 
																 border="0" 
																 alt="Exibir Calendário" 
																 tabindex="4"/>
														</a>
	                       								<strong>(dd/mm/aaaa)</strong> 
                  									</strong>
                  								</span>            		
											</td>
                                  		</tr>
                                  		
										<tr> 	
                                    		<td class="style3">
                                    			<strong>Hora da Tramita&ccedil;&atilde;o:</strong>
                                    			<font color="#FF0000">*</font>
                                    		</td>
                                    		<td colspan="3">
                                    			<span class="style2">
                                    				<strong> 
														<html:text property="horaTramite" 
																   size="6" 
																   maxlength="5" 
																   tabindex="3" 
																   onkeypress="return isCampoNumerico(event);"
																   onkeyup="mascaraHora(this, event);"/>
	                       								<strong>(hh:mm)</strong> 
                  									</strong>
                  								</span>            		
											</td>
                                  		</tr>

										<tr> 	
                                    		<td class="style3">
                                    			<strong>Parecer:</strong>
                                    		</td>
                                    		<td colspan="3">
                                    			<span class="style2">
                                    				<strong> 
														<html:textarea property="parecerTramite" cols="50" rows="5" onkeypress="javascript:textAreaMaxLength(200);"/>
													</strong>
												</span>
											</td>
                                  		</tr>

						       			<tr> 
						          			<td>
						          				<strong>
						          					<font color="#FF0000"></font>
						          				</strong>
						          			</td>
						          			<td colspan="3" align="right">
						          				<div align="left">
						          					<strong>
						          						<font color="#FF0000">*</font>
						          					</strong> 
						              				Campos obrigat&oacute;rios
						              			</div>
						              		</td>
						       			</tr>


								</table>
							</td>
                            </tr>
						</table>
       				</td>
      			</tr>



				<tr>
					<td>

					<table width="100%">
						<tr>
                			<td>
                				<logic:present name="inserirTarifaSocial" scope="session">
	                				<input name="ButtonVoltar"   type="button" class="bottonRightCol" value="Voltar" onclick="javascript:window.location.href='/gsan/exibirInserirTarifaSocialAction.do?menu=sim';">
                				</logic:present>
                				<logic:notPresent name="inserirTarifaSocial" scope="session">
	                				<input name="ButtonVoltar"   type="button" class="bottonRightCol" value="Voltar" onclick="javascript:consultarRA();">
                				</logic:notPresent>
								<input name="ButtonDesfazer" type="button" class="bottonRightCol" value="Desfazer" onclick="javascript:window.location.href='/gsan/exibirTramitarRegistroAtendimentoAction.do?desfazer=true'">
								<input name="ButtonCancelar" type="button" class="bottonRightCol" value="Cancelar" onClick="javascript:window.location.href='/gsan/telaPrincipal.do'">
                      		</td>
                      		<td width="54%" align="right">
		                  		<input name="ButtonTramites" type="button" class="bottonRightCol"  value="Consultar Tr&acirc;mites" onClick="javascript:consultarTramites();">
                        		<input name="ButtonInserir" type="button" class="bottonRightCol" value="Tramitar" onClick="javascript:validarForm();">
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

	<%@ include file="/jsp/util/rodape.jsp"%>
</html:form>
</body>
</html:html>