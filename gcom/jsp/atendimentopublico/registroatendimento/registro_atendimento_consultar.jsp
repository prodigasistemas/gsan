<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>

<%@ page import="gcom.gui.GcomAction"%>
<%@ page import="gcom.atendimentopublico.registroatendimento.RegistroAtendimentoAnexo"%>
<%@ page import="gcom.atendimentopublico.registroatendimento.RegistroAtendimentoConta"%>
<%@ page import="gcom.atendimentopublico.registroatendimento.RegistroAtendimentoPagamentoDuplicidade"%>
<%@ page import="java.math.BigDecimal,gcom.util.Util" %>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="gcom.atendimentopublico.bean.DadosRAReiteracaoHelper"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
<head>
<meta http-equiv="Expires" CONTENT="0">
<meta http-equiv="Cache-Control" CONTENT="no-store">
<meta http-equiv="Pragma" CONTENT="no-store">

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">

<%@ page import="gcom.util.ConstantesSistema"%>

<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>

<script language="JavaScript">
	
	function validaForm(){

		var form = document.forms[0];
		
		//Pendente
		if(form.codigoSituacao.value == 1){
		
			form.ButtonReativar.disabled=true;

		//Encerrado
		}else if(form.codigoSituacao.value == 2){

			form.ButtonAtualizar.disabled=true;
			form.ButtonReiterar.disabled=true;
			form.ButtonTramitar.disabled=true;
			form.ButtonEncerrar.disabled=true;
			form.ButtonOrdemSer.disabled=true;

		//Bloqueado
		}else{

			//form.ButtonAtualizar.disabled=true;
			form.ButtonReiterar.disabled=true;
			form.ButtonTramitar.disabled=true;
			form.ButtonOrdemSer.disabled=true;
			form.ButtonReativar.disabled=true;
			form.ButtonOrdens.disabled=true;
			form.ButtonSolicitantes.disabled=true;
			form.ButtonTramites.disabled=true;
		}
		
	}
	
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

	function consultarTramites() {
		var form = document.forms[0];
		abrirPopup('exibirConsultarRegistroAtendimentoTramiteAction.do?numeroRA='+form.numeroRAPesquisado.value, 550, 735);
	}

	function consultarSolicitantes() {
		var form = document.forms[0];
		abrirPopup('exibirConsultarRegistroAtendimentoSolicitanteAction.do?numeroRA='+form.numeroRAPesquisado.value, 550, 735);
	}

	function consultarDebitos() {
		var form = document.forms[0];

		//form.action = 'consultarDebitoAction.do?codigoImovel='+form.matriculaImovel.value;
		//form.submit();
		
		abrirPopup('consultarDebitoAction.do?ehPopup=true&codigoImovel='+form.matriculaImovel.value, 550, 735);
	}
	
	function consultarOS() {
		var form = document.forms[0];
		abrirPopup('exibirConsultarRegistroAtendimentoOSAction.do?numeroRA='+form.numeroRAPesquisado.value + '&botaoEncerraOs=1', 550, 735);
	}
	
	function gerarOS() {
		var form = document.forms[0];

		form.action = 'exibirGerarOrdemServicoAction.do?limparTela=sim&forward=exibirGerarOrdemServico&caminhoRetornoGerarOs=exibirConsultarRegistroAtendimentoAction.do&idRegistroAtendimento='+form.numeroRAPesquisado.value;
		form.submit();

	}
	
	function reiterarANTIGO(){
		var form = document.forms[0];
		form.action = 'reiterarRegistroAtendimentoAction.do?numeroRA='+form.numeroRAPesquisado.value;
		form.submit();
	}
	
	function reiterar(url,id){
		window.location.href=url+id;
	}
	

	function tramitar(url,id){
		window.location.href=url+id;
	}
	
	function encerrarRA(url,id){
		var form = document.forms[0];
		form.ButtonEncerrar.disabled = true;
		window.location.href=url+id;
	}
	
	function reativarRA(url,id){
		window.location.href=url+id;
	}
	function atualizarRA(url,id){
		var form = document.forms[0];
		form.ButtonAtualizar.disabled = true;
		window.location.href=url+id;
	}	
	
	function imprimir(viaCliente){
		var form = document.forms[0];
		if(viaCliente){
			toggleBoxCaminho('demodiv',1,'gerarRelatorioRegistroAtendimentoViaClienteConsultarAction.do');
		}else{
			toggleBoxCaminho('demodiv',1,'gerarRelatorioRegistroAtendimentoConsultarAction.do');
		}
	}
	
	
	function validarPesquisa(){
	
		var form = document.forms[0];
		
		if (validateConsultarRegistroAtendimentoActionForm(form)){
			form.action = "exibirConsultarRegistroAtendimentoAction.do?pesquisaUnitaria=OK"
			submeterFormPadrao(form);
		}
	}
	
	
	function raAnterior(){
 
 		var form = document.forms[0];
 		form.action = "exibirConsultarRegistroAtendimentoAction.do?raAnterior=1&numeroRA=" + form.numeroRAPesquisado.value;
 		submeterFormPadrao(form);
	}

	function proximoRA(){
 		var form = document.forms[0];
 		form.action = "exibirConsultarRegistroAtendimentoAction.do?proximoRA=1&numeroRA=" + form.numeroRAPesquisado.value;
 		submeterFormPadrao(form);
	}
	
	function visualizarArquivo(identificacao){
	
		var form = document.forms[0];
		form.target = "_new";
		form.action = "exibirConsultarRegistroAtendimentoAction.do?visualizar=" + identificacao;
	
		form.submit();
	}
	
</script>

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="ConsultarRegistroAtendimentoActionForm"/>

</head>

<body leftmargin="5" topmargin="5" onload="javascript:validaForm();setarFoco('${requestScope.nomeCampo}');">

<html:form action="/exibirConsultarRegistroAtendimentoAction.do"
	name="ConsultarRegistroAtendimentoActionForm"
	type="gcom.gui.atendimentopublico.registroatendimento.ConsultarRegistroAtendimentoActionForm"
	method="post">
	
	<html:hidden property="codigoSituacao"/>
	
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

			<td width="600" valign="top" class="centercoltext">

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
						<td class="parabg">Consultar Registro de Atendimento</td>
						<td width="11">
							<img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif" />
						</td>
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
                         					<span class="style2"><b>Pesquisar outro Registro de Atendimento</b></span>
                         				</div>
                         			</td>
                        		</tr>

								<tr bgcolor="#cbe5fe">
									<td>
									<table border="0" width="100%">

		                            	<tr>
		                              		<td height="10" width="22%"><strong>N&uacute;mero do RA:</strong></td>
		                              		
		                              		<td> 
												<html:text property="numeroRA" 
														size="9" 
														maxlength="9" 
														onkeypress="return isCampoNumerico(event);"/>&nbsp;
													<input type="button" class="bottonRightCol" value="Pesquisar"
													style="width: 80px" onclick="validarPesquisa();">
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
                	<td height="20"></td>
                </tr>
                
                <logic:notPresent name="naoHabilitarNavegacao" scope="session">
			      			
			    <tr>
					<td>						      			
									
						<table cellspacing="0" cellpadding="0" width="100%" border="0">
										
						<tr>
											
							<logic:notPresent name="desabilitaBotaoAnterior">
											
								<td align="right" width="83%"><img
									src="<bean:message key="caminho.imagens"/>voltar.gif"
									onclick="raAnterior();"></td>
								<td align="left" width="15%"><input type="button"
									name="Button" class="buttonAbaRodaPe" value="RA Anterior"
									onclick="raAnterior();" /></td>
											
							</logic:notPresent>
										
							<logic:notPresent name="desabilitaBotaoProximo">	
											
								<td align="right" width="97%"><input type="button"
									name="Button" class="buttonAbaRodaPe" value="Próximo RA"
									onclick="proximoRA();" /></td>
								<td align="right" width="3%"><img
									src="<bean:message key="caminho.imagens"/>avancar.gif"
									onclick="proximoRA()"></td>
										
							</logic:notPresent>
										
						</tr>
											
						</table>
								
					</td>
			    </tr>
			      			
			    </logic:notPresent>
			    
			    <tr>
                	<td height="20">
                	   <strong>Perfil do Imóvel:</strong>
                	   <html:text property="perfilImovel" readonly="true"
													style="background-color:#EFEFEF; border:0;" size="20"
													maxlength="9" />
                	</td>
                </tr>
            	
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
												<html:text property="numeroRAPesquisado" readonly="true"
													style="background-color:#EFEFEF; border:0;" size="9"
													maxlength="9" />
		                                  	</td>
			                              	
			                              	<td>
			                              		<strong>Situa&ccedil;&atilde;o do RA:</strong>
												<html:text property="situacaoRA" readonly="true"
													style="background-color:#EFEFEF; border:0;" size="15"
													maxlength="15" />

			                              	</td>
			                              	<td>
			                                </td>
		                            	</tr>



										<logic:present name="existeRaAssociado" scope="request">

			                            	<tr> 
			                              		<td height="10"><strong><bean:write name="ConsultarRegistroAtendimentoActionForm" property="descricaoRAAssociada"/></strong></td>
			                              		<td> 
													<html:text property="numeroRaAssociado" readonly="true"
														style="background-color:#EFEFEF; border:0;" size="9"
														maxlength="9" />
			                                	</td>
			                              		
			                              		<td>
			                              			<strong><bean:write name="ConsultarRegistroAtendimentoActionForm" property="descricaoSituacaoRAAssociada"/></strong>
			                              			<html:text property="situacaoRaAssociado" readonly="true"
														style="background-color:#EFEFEF; border:0;" size="15"
														maxlength="15" />
			                              		</td>

			                              		<td>
			                                	</td>
			                            	</tr>

										</logic:present>

										<tr> 
		                              		<td class="style3"><strong>Número Manual:</strong></td>
		                              		<td colspan="3">
												<html:text property="numeroRAManual" readonly="true"
													style="background-color:#EFEFEF; border:0;" size="12"
													maxlength="11" />
		                              		
		                                	</td>
		                            	</tr>
		                            	
		                            	<tr> 
		                              		<td class="style3"><strong>Tipo de Solicita&ccedil;&atilde;o:</strong></td>
		                              		<td colspan="3">
												<html:text property="idTipoSolicitacao" readonly="true"
													style="background-color:#EFEFEF; border:0;" size="4"
													maxlength="4" />
		                              		
												<html:text property="tipoSolicitacao" readonly="true"
													style="background-color:#EFEFEF; border:0;" size="50"
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
													style="background-color:#EFEFEF; border:0;" size="50"
													maxlength="50" />
		                                 	</td>
		                            	</tr>

			                            <tr>
			                              	<td height="10"><strong>Tipo de Atendimento:</strong></td>
											<td>
												<html:radio property="tipoAtendimento" value="<%=""+ConstantesSistema.SIM%>" disabled="true"/>
												<strong>on-line</strong>
											</td>
											<td>
												<html:radio property="tipoAtendimento" value="<%=""+ConstantesSistema.NAO%>" disabled="true"/>
												<strong>manual</strong>
											</td>
			                            </tr>

			                            <tr> 
			                              	<td width="31%" height="10"><strong>Data do Atendimento:</strong></td>
			                              	<td colspan="3"> 
												<html:text property="dataAtendimento" 
													readonly="true"
													style="background-color:#EFEFEF; border:0;" 
													size="10"
													maxlength="10" />
											</td>
			                            </tr>

			                            <tr> 
			                              	<td class="style3"><strong>Hora do Atendimento:</strong></td>
			                              	<td colspan="3">
												<html:text property="horaAtendimento" 
													readonly="true"
													style="background-color:#EFEFEF; border:0;" 
													size="5"
													maxlength="5" />
			                             	</td>
			                            </tr>

			                            <tr> 
		                              		<td height="10"><strong>Tempo de Espera para Atendimento:</strong></td>
			                              	<td colspan="3"> 
											<strong>
												<html:text property="tempoEsperaInicio" 
													readonly="true"
													style="background-color:#EFEFEF; border:0;" 
													size="5"
													maxlength="5" />
												
												&agrave;s 
											
												<html:text property="tempoEsperaTermino" 
													readonly="true"
													style="background-color:#EFEFEF; border:0;" 
													size="5"
													maxlength="5" />
			                              	</strong>
											</td>
			                            </tr>

			                            <tr> 
			                              	<td class="style3"><strong>Data Prevista:</strong></td>
			                              	<td colspan="3">
												<html:text property="dataPrevista" readonly="true"
													style="background-color:#EFEFEF; border:0;" size="10"
													maxlength="10" />
											
											</td>

			                            </tr>
			                            
			                            <tr>
									      	<td HEIGHT="30"><strong>Valor Sugerido:</strong></td>
									        <td>
												<html:text property="valorSugerido" size="11" maxlength="10" tabindex="12" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>
											</td>
 										</tr>

			                            <tr>
			                              	<td class="style3"><strong>Meio de Solicita&ccedil;&atilde;o:</strong></td>
			                              	<td colspan="3">
												<html:text property="idMeioSolicitacao" readonly="true"
													style="background-color:#EFEFEF; border:0;" size="4"
													maxlength="4" />

												<html:text property="meioSolicitacao" readonly="true"
													style="background-color:#EFEFEF; border:0;" size="50"
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

			                            <tr> 
			                              	<td class="style3"><strong>Usuário:</strong></td>
			                              	<td colspan="3">
												<html:text property="idUsuario" readonly="true"
													style="background-color:#EFEFEF; border:0;" size="4"
													maxlength="4" />

												<html:text property="usuario" readonly="true"
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
 										<tr> 
			                              	<td class="style3"><strong>Unidade Anterior:</strong></td>
			                              	<td colspan="3">
												<html:text property="idUnidadeAnterior" readonly="true"
													style="background-color:#EFEFEF; border:0;" size="4"
													maxlength="4" />

												<html:text property="unidadeAnterior" readonly="true"
													style="background-color:#EFEFEF; border:0;" size="40"
													maxlength="40" />
											</td>
			                            </tr>
			                            <tr> 
			                              	<td class="style3"><strong>Observa&ccedil;&atilde;o:</strong></td>
			                             	<td colspan="3">
												<html:textarea property="observacao" readonly="true"
													style="background-color:#EFEFEF; border:0;" cols="50"/>
			                              
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

				<!-- Dados do Local Ocorrencia -->

               	<tr bgcolor="#cbe5fe">
           			<td align="center">
       					<div id="layerHideLocal" style="display:block">

           					<table width="100%" border="0" bgcolor="#99CCFF">
	    						<tr bgcolor="#99CCFF">
                     				<td align="center">
                    					<span class="style2">
                     					<a href="javascript:extendeTabela('Local',true);"/>
                     						<b>Dados do Local da Ocorr&ecirc;ncia</b>
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
                     						<b>Dados do Local da Ocorr&ecirc;ncia</b>
                     					</a>
                    					</span>
	                     			</td>
	                    		</tr>
		
								<tr bgcolor="#cbe5fe">
									<td>
									<table border="0" width="100%">

	                              		<tr>
	                                		<td>
	                                			<strong>Matr&iacute;cula do Im&oacute;vel:</strong>
	                                		</td>
	                                		
	                                		<td> 
												<html:text property="matriculaImovel" 
													readonly="true"
													style="background-color:#EFEFEF; border:0;" 
													size="8"
													maxlength="8" />
				
												<html:text property="inscricaoImovel" 
													readonly="true"
													style="background-color:#EFEFEF; border:0;" 
													size="21"
													maxlength="21" />


												<c:choose>
	                								<c:when test="${ConsultarRegistroAtendimentoActionForm.matriculaImovel != null}">
								                		<input name="ButtonDebitos" 
								                			type="button" 
								                			class="bottonRightCol"  
								                			value="Consultar D&eacute;bitos" 
								                			onClick="javascript:consultarDebitos();">
								                	</c:when>
								                	<c:otherwise>
								                		<input name="ButtonDebitos" 
								                			type="button" 
								                			class="bottonRightCol"  
								                			value="Consultar D&eacute;bitos" 
								                			disabled="true"
								                			onClick="javascript:consultarDebitos();">
								                	</c:otherwise>
								                </c:choose>
											</td>
											
                              			</tr>
                              			<tr>
	                                		<td>
	                                			<strong>Rota:</strong>
	                                		</td>
	                                		
	                                		<td> 
												<html:text property="rota" 
													readonly="true"
													style="background-color:#EFEFEF; border:0;" 
													size="8"
													maxlength="8" />
											&nbsp;<strong>Sequencial Rota:</strong>&nbsp;
												<html:text property="sequencialRota" 
													readonly="true"
													style="background-color:#EFEFEF; border:0;" 
													size="8"
													maxlength="8" />
											</td>
											
                              			</tr>

	                              		<tr> 
	                                		<td height="10" colspan="4"> 
	                                		<div align="right"> 
	                                    	<hr>
	                                  		</div>
	                                  		<div align="right"> </div></td>
	                              		</tr>
										<tr bgcolor="#99CCFF">
			                     			<td align="center" colspan="6">
		                    					<span class="style2">
		                     						<b>Contas Associadas ao Registro de Atendimento</b>
		                    					</span>
			                     			</td>
			                    		</tr>
										<tr>
											<td colspan="6">
					                    		<table width="100%" cellpadding="0" cellspacing="0">
													<tr bgcolor="#99CCFF">
						                     			<td align="center" width="30%">
					                    					<span class="style2">
					                     						<b>Mes/Ano</b>
					                    					</span>
						                     			</td>
						                     			<td align="center" width="40%">
					                    					<span class="style2">
					                     						<b>Vencimento</b>
					                    					</span>
						                     			</td>
						                     			<td align="center" width="30%">
					                    					<span class="style2">
					                     						<b>Valor Total</b>
					                    					</span>
						                     			</td>
						                    		</tr>
						                    		<logic:present name="colecaoRAContas">
														<tr>
															<td colspan="3">
									
															<div style="width: 100%; height: 100%; overflow: auto;">
															<table width="100%" bgcolor="#99CCFF">
									
																<logic:iterate name="colecaoRAContas"
																	id="registroAtendimentoConta" type="RegistroAtendimentoConta">
																	
																	<tr>
																	<bean:define name="registroAtendimentoConta" property="conta" id="conta"/>

																	<c:set var="count2" value="${count2+1}" />
																	<c:choose>
																		<c:when test="${count2%2 == '1'}">
																			<tr bgcolor="#FFFFFF">
																		</c:when>
																		<c:otherwise>
																			<tr bgcolor="#cbe5fe">
																		</c:otherwise>
																	</c:choose>
																		<td align="center" width="30%">
																			<bean:write name="conta"
																				property="formatarAnoMesParaMesAno" />
																		</td>
																		<td align="center" width="40%">
																			<bean:write name="conta"
																				property="dataVencimentoConta" format="dd/MM/yyyy" />
																		</td>
																		<td align="center" width="30%">
																				<bean:define name="conta" property="valorTotalContaBigDecimal" id="valorTotalContaBigDecimal"/>
																				<%= Util.formatarMoedaReal((BigDecimal)valorTotalContaBigDecimal) %>
																		</td>
																	</tr>
									
																</logic:iterate>
							
															</table>
															</div>
															</td>
														</tr>
													</logic:present>
			                    				</table>
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
												style="background-color:#EFEFEF; border:0;" cols="50"/>
		                                  	</td>
		                            	</tr>
                            	
		                               	<tr>
		                                 	<td class="style3"><strong>Ponto de Refer&ecirc;ncia:</strong></td>
		                                 	
		                                 	<td>
												<html:text property="pontoReferencia" readonly="true"
													style="background-color:#EFEFEF; border:0;" size="60"
													maxlength="60" />
		                                 	</td>
		                               	</tr>
		                               	
		                               	<tr>
		                                 	<td class="style3"><strong>Coordenada Norte:</strong></td>
		                                 	<td>
												<html:text property="numeroCoordenadaNorte" readonly="true"
													style="background-color:#EFEFEF; border:0; text-align: right" size="12"
													maxlength="17" />
		                                 	</td>
		                               	</tr>
		                               	
		                               	<tr>
		                                 	<td class="style3"><strong>Coordenada Leste:</strong></td>
		                                 	<td>
												<html:text property="numeroCoordenadaLeste" readonly="true"
													style="background-color:#EFEFEF; border:0; text-align: right" size="12"
													maxlength="17" />
		                                 	</td>
		                               	</tr>

	                              		<tr> 
	                                		<td class="style3">
	                                			<strong>Munic&iacute;pio:</strong>
	                                		</td>
	                                		<td>
												<html:text property="idMunicipio" readonly="true"
													style="background-color:#EFEFEF; border:0;" size="4"
													maxlength="4" />
				
												<html:text property="municipio" readonly="true"
													style="background-color:#EFEFEF; border:0;" size="40"
													maxlength="40" />

											</td>
                              			</tr>

	                              		<tr> 
	                                		<td class="style3">
	                                			<strong>Bairro:</strong>
	                                		</td>
	                                		<td>
												<html:text property="idBairro" readonly="true"
													style="background-color:#EFEFEF; border:0;" size="4"
													maxlength="4" />
				
												<html:text property="bairro" readonly="true"
													style="background-color:#EFEFEF; border:0;" size="40"
													maxlength="40" />
											</td>
	                              		</tr>
	
	                              		<tr> 
	                                		<td class="style3">
	                                			<strong>&Aacute;rea do Bairro:</strong>
	                                		</td>
	                                		<td>
												<html:text property="idAreaBairro" readonly="true"
													style="background-color:#EFEFEF; border:0;" size="4"
													maxlength="4" />
				
												<html:text property="areaBairro" readonly="true"
													style="background-color:#EFEFEF; border:0;" size="40"
													maxlength="40" />

											</td>
                              			</tr>

	                              		<tr> 
	                                		<td height="10" colspan="4"> 
	                                		<div align="right"> 
	                                    		<hr>
	                                  		</div>
	                                  		<div align="right"> </div>
	                                  		</td>
	                              		</tr>
										
										<tr> 	
	                                		<td class="style3">
	                                			<strong>Localidade:</strong>
	                                		</td>
	                                		<td>
												<html:text property="idLocalidade" readonly="true"
													style="background-color:#EFEFEF; border:0;" size="4"
													maxlength="4" />
				
												<html:text property="localidade" readonly="true"
													style="background-color:#EFEFEF; border:0;" size="40"
													maxlength="40" />
											</td>
                              			</tr>
                            		
										<tr> 	
	                                		<td class="style3">
	                                			<strong>Setor Comercial:</strong>
	                                		</td>
	                                		<td colspan="3">
												<html:text property="idSetorComercial" readonly="true"
													style="background-color:#EFEFEF; border:0;" size="4"
													maxlength="4" />
													
												<html:text property="setorComercial" readonly="true"
													style="background-color:#EFEFEF; border:0;" size="40"
													maxlength="40" />
													
				
											</td>
	                              		</tr>
                              
										<tr> 	
	                                		<td class="style3">
	                                			<strong>Quadra:</strong>
	                                		</td>
	                                		<td colspan="3">
												<html:text property="idQuadra" readonly="true"
													style="background-color:#EFEFEF; border:0;" size="4"
													maxlength="4" />
				
											</td>
	                              		</tr>

										<tr> 	
	                                		<td class="style3">
	                                			<strong>Divis&atilde;o de Esgoto:</strong>
	                                		</td>
	                                		<td colspan="3">
												<html:text property="idDivisaoEsgoto" readonly="true"
													style="background-color:#EFEFEF; border:0;" size="4"
													maxlength="4" />
				
												<html:text property="divisaoEsgoto" readonly="true"
													style="background-color:#EFEFEF; border:0;" size="40"
													maxlength="40" />
											</td>
	                              		</tr>
                              
	                              		<tr>
	                                		<td height="10" colspan="4"> 
	                                			<div align="right">
	                                    			<hr>
	                                  			</div>
	                                  			<div align="right"> </div>
	                                  		</td>
	                              		</tr>
	                            		
	                              		<tr> 
		                                 	<td class="style3"><strong>Local da Ocorr&ecirc;ncia:</strong></td>
	    	                             	<td colspan="3">
												<html:text property="localOcorrencia" readonly="true"
												style="background-color:#EFEFEF; border:0;" size="50"
												maxlength="50" />
	                                
		                                 	</td>
	                              		</tr>
                              
		                               	<tr> 
	   	                              		<td class="style3"><strong>Pavimento da Rua:</strong></td>
	        	                          	<td>
												<html:text property="pavimentoRua" readonly="true"
												style="background-color:#EFEFEF; border:0;" size="20"
												maxlength="20" />
	
												<strong>Pavimento da Cal&ccedil;ada:</strong>
				
												<html:text property="pavimentoCalcada" readonly="true"
												style="background-color:#EFEFEF; border:0;" size="20"
												maxlength="20" />
	                                  		</td>
	                               		</tr>
	
		                               	<tr> 
		                               		<td height="10" colspan="4"> <div align="right"> 
		                                     <hr>
		                                   </div>
		                                   <div align="right"> </div></td>
		                               	</tr>

                              		<tr> 
                                		<td class="style3"><strong>Descri&ccedil;&atilde;o 
                                  		do Local da Ocorr&ecirc;ncia:</strong></td>

                                		<td colspan="3">
											<html:textarea property="descricaoLocalOcorrencia" readonly="true"
											style="background-color:#EFEFEF; border:0;" cols="50"/>
                                		</td>
                              		</tr>
									</table>
								</td>
							</tr>
							</table>
        				</div>
					</td>
				</tr>
				
				<!-- Dados do Solicitante -->
				<tr>
					<td>
           				<div id="layerHideSolicitante" style="display:block">
               				<table width="100%" border="0" bgcolor="#99CCFF">
		    					<tr bgcolor="#99CCFF">
                      				<td align="center">
                     					<span class="style2">
                      					<a href="javascript:extendeTabela('Solicitante',true);"/>
                      						<b>Dados do Solicitante</b>
                      					</a>
                     					</span>
                      				</td>
                     			</tr>
                    		</table>
           				</div>
                   				                        		
                        <div id="layerShowSolicitante" style="display:none">

	                    	<table width="100%" border="0" bgcolor="#99CCFF">

								<tr bgcolor="#99CCFF">
		                    		<td align="center">
                       					<span class="style2">
                         					<a href="javascript:extendeTabela('Solicitante',false);"/>
                         						<b>Dados do Solicitante</b>
                         					</a>
                       					</span>
                         			</td>
                        		</tr>

								<tr bgcolor="#cbe5fe">
									<td>
									<table border="0" width="100%">

										<tr>
											<td class="style3"><strong>Nº Protocolo:</strong></td>
											<td colspan="3"><html:text property="numeroProtocolo"
												readonly="true" style="background-color:#EFEFEF; border:0;"
												size="15" maxlength="14" /></td>
										</tr>
										<tr> 	
                                    		<td class="style3">
                                    			<strong>Cliente Solicitante:</strong>
                                    		</td>
                                    		<td colspan="3">
												<html:text property="idClienteSolicitante" readonly="true"
													style="background-color:#EFEFEF; border:0;" size="10"/>
	
												<html:text property="clienteSolicitante" readonly="true"
													style="background-color:#EFEFEF; border:0;" size="40"
													maxlength="40" />

											</td>
                                  		</tr>
		                                  		
										<tr> 	
                                    		<td class="style3">
                                    			<strong>Unidade Solicitante:</strong>
                                    		</td>
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
                                    		<td class="style3">
                                    			<strong>Funcion&aacute;rio Respons&aacute;vel:</strong>
                                    		</td>
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
                                    		<td class="style3">
                                    			<strong>Nome do Solicitante:</strong>
                                    		</td>
                                    		<td colspan="3">
												<html:text property="nomeSolicitante" readonly="true"
													style="background-color:#EFEFEF; border:0;" size="40"
													maxlength="40" />
											</td>
                                  		</tr>
                                  		
                                  		<tr> 
	                                  		<td height="10" colspan="4"> 
	                                  			<div align="right"> <hr>
	                                      		</div>
	                                      		<div align="right"> </div>
	                                      	</td>
	                                  	</tr>
                                  		
                                  		<c:if test="${habilitarCampoSatisfacaoEmail != null && habilitarCampoSatisfacaoEmail == true}">
									      <tr>
									      	<td>	
									      		<strong>Enviar email para pesquisa de satisfação:</strong>
									      	</td>
									      	<td>	
									      		<input disabled="disabled" onclick="selecionouEmailSatisfacaoSim();" type="radio" name="enviarEmailSatisfacao" <c:if test="${ConsultarRegistroAtendimentoActionForm.enviarEmailSatisfacao == 1 }">checked="checked"</c:if> value="1" /><strong> Sim </strong>
									      		<input disabled="disabled" onclick="selecionouEmailSatisfacaoNao();" type="radio" name="enviarEmailSatisfacao" <c:if test="${ConsultarRegistroAtendimentoActionForm.enviarEmailSatisfacao == 2 }">checked="checked"</c:if> value="2" /><strong> Não </strong>
									      	</td>
									      </tr>
									       <tr>
									      	<td><strong>Endereço de email:</strong></td>
									        <td>
								        		<input readonly="true" style="background-color:#EFEFEF; border:0;" type="text" value="${ConsultarRegistroAtendimentoActionForm.enderecoEmail }" name="enderecoEmail" size="40" maxlength="40"   />
											</td>
									      </tr>
		                                  	<tr> 
		                                  		<td height="10" colspan="4"> 
		                                  			<div align="right"> <hr>
		                                      		</div>
		                                      		<div align="right"> </div>
		                                      	</td>
		                                  	</tr>
								      </c:if>

		                                  		
	                                  	<tr> 
		                                	<td class="style3">
		                                		<strong><span class="style2">Endere&ccedil;o do Solicitante:</span></strong>
		                                	</td>
		                                    
		                                    <td>
												<html:textarea property="enderecoSolicitante" readonly="true"
													style="background-color:#EFEFEF; border:0;" cols="50" />
		                                    </td>
		                              	</tr>
		                              	
	                                  	<tr>
	                                    	<td class="style3"><strong>Ponto de Refer&ecirc;ncia:</strong></td>
	                                    	
	                                    	<td>
												<html:text property="pontoReferenciaSolicitante" readonly="true"
													style="background-color:#EFEFEF; border:0;" size="60"
													maxlength="60" />
	                                    	</td>
	                                  	</tr>

	                                  	<tr> 
	                                  		<td height="10" colspan="4"> 
	                                  			<div align="right"> <hr>
	                                      		</div>
	                                      		<div align="right"> </div>
	                                      	</td>
	                                  	</tr>
                                  		
	                                  	<tr>
	                                    	<td class="style3"><strong>Fone do Solicitante:</strong></td>
	                                    	
	                                    	<td>
												<html:text property="foneDDD" readonly="true"
													style="background-color:#EFEFEF; border:0;" size="60"
													maxlength="2" />
												<html:text property="fone" readonly="true"
													style="background-color:#EFEFEF; border:0;" size="60"
													maxlength="9" />
												<html:text property="foneRamal" readonly="true"
													style="background-color:#EFEFEF; border:0;" size="60"
													maxlength="4" />


	                                    	</td>
	                                  	</tr>
		                                  		
										</table>
									</td>
                            	</tr>
							</table>
       						
       					</div>
       				</td>
      			</tr>

				
				<!-- ANEXOS -->
				<tr>
					<td>
           				<div id="layerHideAnexos" style="display:block">
               				<table width="100%" border="0" bgcolor="#99CCFF">
		    					<tr bgcolor="#99CCFF">
                      				<td align="center">
                     					<span class="style2">
                      					<a href="javascript:extendeTabela('Anexos',true);"/>
                      						<b>Anexos</b>
                      					</a>
                     					</span>
                      				</td>
                     			</tr>
                    		</table>
           				</div>
                   				                        		
                        <div id="layerShowAnexos" style="display:none">

	                    	<table width="100%" border="0" bgcolor="#99CCFF">

								<tr bgcolor="#99CCFF">
		                    		<td align="center">
                       					<span class="style2">
                         					<a href="javascript:extendeTabela('Anexos',false);"/>
                         						<b>Anexos</b>
                         					</a>
                       					</span>
                         			</td>
                        		</tr>

								<tr bgcolor="#cbe5fe">
									<td>
									
										<table width="100%" cellpadding="0" cellspacing="0">
										<tr>
											<td>
				
												<div style="width: 100%; height: 100; overflow: auto;">
												<% int cont = 0;%>
													
												<table width="100%" align="center" bgcolor="#99CCFF">
				
													<logic:iterate name="colecaoRegistroAtendimentoAnexo" id="registroAtendimentoAnexo" type="RegistroAtendimentoAnexo">
														
														<%cont = cont + 1;
														
														if (cont % 2 == 0) {%>
															<tr bgcolor="#cbe5fe">
														<%} else {%>
															<tr bgcolor="#FFFFFF">
														<%}%>
				
																<td align="center" width="10%" valign="middle">
																		<a href="javascript:visualizarArquivo('<%="" + GcomAction.obterTimestampIdObjeto(registroAtendimentoAnexo) %>')" title="Visualizar Arquivo">
																		<IMG SRC="<bean:message key="caminho.imagens"/><%="" + registroAtendimentoAnexo.getNomeExtensaoDocumento() %>.gif" width="35" height="35" BORDER="0" ALT=""></a>	
																</td>
																<td width="90%">
																	
																	<% if (cont % 2 == 0) {%>
																		
																		<logic:present name="registroAtendimentoAnexo" property="descricaoDocumento">
																			
																			<TEXTAREA ROWS="2" COLS="55" style="cursor:hand; border:0px solid; background-color: #cbe5fe" readonly><bean:write name="registroAtendimentoAnexo" property="descricaoDocumento" /></TEXTAREA>
																			
																		</logic:present>
																						
																		<logic:notPresent name="registroAtendimentoAnexo" property="descricaoDocumento">
																			
																			<TEXTAREA ROWS="2" COLS="55" style="border:0px solid; background-color: #cbe5fe" readonly></TEXTAREA>
																			
																		</logic:notPresent>
																		
																	<%} else {%>
																		
																		<logic:present name="registroAtendimentoAnexo" property="descricaoDocumento">
																			
																			<TEXTAREA ROWS="2" COLS="55" style="cursor:hand; border:0px solid" readonly><bean:write name="registroAtendimentoAnexo" property="descricaoDocumento" /></TEXTAREA>
																			
																		</logic:present>
																						
																		<logic:notPresent name="registroAtendimentoAnexo" property="descricaoDocumento">
																			
																			<TEXTAREA ROWS="2" COLS="55" style="border:0px solid" readonly></TEXTAREA>
																			
																		</logic:notPresent>
																		
																	<%}%>
																	
																			
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
       						
       					</div>
       				</td>
      			</tr>
				
				
				<!-- Dados da Ultima Tramitação -->
							
							<tr>
								<td>
			           				<div id="layerHideTramitacao" style="display:block">
			               				<table width="100%" border="0" bgcolor="#99CCFF">
					    					<tr bgcolor="#99CCFF">
			                      				<td align="center">
			                     					<span class="style2">
			                      					<a href="javascript:extendeTabela('Tramitacao',true);"/>
			                      						<b>Dados da &Uacute;ltima Tramita&ccedil;&atilde;o</b>
			                      					</a>
			                     					</span>
			                      				</td>
			                     			</tr>
			                    		</table>
			           				</div>
                   				                        		
			                        <div id="layerShowTramitacao" style="display:none">
			
				                    	<table width="100%" border="0" bgcolor="#99CCFF">
			
											<tr bgcolor="#99CCFF">
					                    		<td align="center">
			                       					<span class="style2">
			                         					<a href="javascript:extendeTabela('Tramitacao',false);"/>
			                         						<b>Dados da &Uacute;ltima Tramita&ccedil;&atilde;o</b>
			                         					</a>
			                       					</span>
			                         			</td>
			                        		</tr>

											<tr bgcolor="#cbe5fe">
												<td>
												<table border="0" width="100%">
			
													<tr> 	
			                                    		<td class="style3">
			                                    			<strong>Unidade de Origem:</strong>
			                                    		</td>
			                                    		<td>
															<html:text property="idUnidadeOrigem" readonly="true"
																style="background-color:#EFEFEF; border:0;" size="4"
																maxlength="4" />
				
															<html:text property="unidadeOrigem" readonly="true"
																style="background-color:#EFEFEF; border:0;" size="40"
																maxlength="40" />
														</td>
			                                  		</tr>
			
													<tr> 	
			                                    		<td class="style3">
			                                    			<strong>Unidade Atual:</strong>
			                                    		</td>
			                                    		<td>
															<html:text property="idUnidadeAtualTramitacao" readonly="true"
																style="background-color:#EFEFEF; border:0;" size="4"
																maxlength="4" />
				
															<html:text property="unidadeAtualTramitacao" readonly="true"
																style="background-color:#EFEFEF; border:0;" size="40"
																maxlength="40" />
														</td>
			                                  		</tr>
			
													<tr> 	
			                                    		<td class="style3">
			                                    			<strong>Data do Tr&acirc;mite:</strong>
			                                    		</td>
			                                    		<td colspan="3">
															<html:text property="dataTramite" readonly="true"
																style="background-color:#EFEFEF; border:0;" size="10"
																maxlength="10" />
														</td>
			                                  		</tr>
			
													<tr> 	
			                                    		<td class="style3">
			                                    			<strong>Hora do Tr&acirc;mite:</strong>
			                                    		</td>
			                                    		<td colspan="3">
															<html:text property="horaTramite" 
																readonly="true"
																style="background-color:#EFEFEF; border:0;" 
																size="5"
																maxlength="5" />
														</td>
			                                  		</tr>
			
													<tr> 	
			                                    		<td class="style3">
			                                    			<strong>Usu&aacute;rio Respons&aacute;vel:</strong>
			                                    		</td>
			                                    		<td colspan="3">
															<html:text property="idUsuarioResponsavel" 
																readonly="true"
																style="background-color:#EFEFEF; border:0;" 
																size="4"
																maxlength="4" />
				
															<html:text property="usuarioResponsavel" 
																readonly="true"
																style="background-color:#EFEFEF; border:0;" 
																size="40"
																maxlength="40" />
														</td>
			                                  		</tr>
			
													<tr> 	
			                                    		<td class="style3">
			                                    			<strong>Parecer do Tr&acirc;mite:</strong>
			                                    		</td>
			                                    		<td colspan="3">
															<html:textarea property="parecerTramite" readonly="true"
																style="background-color:#EFEFEF; border:0;" cols="50" />
				
														</td>
			                                  		</tr>

												</table>
			       								</td>       						
			       							<tr>
										</table>
			   						</div>
			  					</td>
			      			</tr>
			      			
							<!-- Dados da Ultima Reiteração -->
							
							<tr>
								<td>
			           				<div id="layerHideReiteracao" style="display:block">
			               				<table width="100%" border="0" bgcolor="#99CCFF">
					    					<tr bgcolor="#99CCFF">
			                      				<td align="center">
			                     					<span class="style2">
			                      					<a href="javascript:extendeTabela('Reiteracao',true);"/>
			                      						<b>Dados de Reitera&ccedil;&atilde;o</b>
			                      					</a>
			                     					</span>
			                      				</td>
			                     			</tr>
			                    		</table>
			           				</div>
                   				                        		
			                        <div id="layerShowReiteracao" style="display:none">
			
				                    	<table width="100%" border="0" bgcolor="#99CCFF">
			
											<tr bgcolor="#99CCFF">
					                    		<td align="center">
			                       					<span class="style2">
			                         					<a href="javascript:extendeTabela('Reiteracao',false);"/>
			                         						<b>Dados de Reitera&ccedil;&atilde;o</b>
			                         					</a>
			                       					</span>
			                         			</td>
			                        		</tr>
			                        		
			                        		<tr bgcolor="#cbe5fe">
												
												<td>
												<table border="0" width="100%">


												<tr>
											         <td colspan="3" height="50" valign="top">
														<table width="100%" cellpadding="0" cellspacing="0">
														<tr>
															<td>
																<table width="100%" border="0" bgcolor="#99CCFF">
																<tr bgcolor="#99CCFF" height="18">
																	<td width="21%" align="center"><strong>Data-Hora</strong></td>
																	<td width="34%" align="center"><strong>Nome do Solicitante</strong></td>
																	<td width="12%" align="center"><strong>Cliente</strong></td>
																	<td width="12%" align="center"><strong>Unidade</strong></td>
																	<td width="21%" align="center"><strong>Fone</strong></td>
																</tr>
																</table>
															</td>
														</tr>
											
														<logic:present name="colecaoDadosReiteracao">
											
														<tr>
															<td>
																<table width="100%" align="center" bgcolor="#99CCFF">
																	<!--corpo da segunda tabela-->
											
																	<% String cor1 = "#cbe5fe";%>
											
																	<logic:iterate name="colecaoDadosReiteracao" id="helper" type="DadosRAReiteracaoHelper">
																	
																		<%	if (cor1.equalsIgnoreCase("#cbe5fe")){	
																			cor1 = "#FFFFFF";%>
																			<tr bgcolor="#FFFFFF" height="18">	
																		<%} else{	
																			cor1 = "#cbe5fe";%>
																			<tr bgcolor="#cbe5fe" height="18">		
																		<%}%>
																		<td width="21%" align="center">
																			<bean:write name="helper" property="dataReiteracaoFormatada"/>
																		</td>
																	
																		<td width="34%" align="left">
																			<div align="left">
																				<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
																					<a href="javascript:abrirPopup('exibirConsultarDadosReiteracaoRAAction.do?idRaReiteracao=<bean:define name="helper" property="raReiteracao" id="raReiteracao" /><bean:write name="raReiteracao" property="id" />', 350, 620);">
																					<bean:write name="helper" property="nomeSolicitante" /></a> 
																				</font>
																			</div>
																		</td>
																		
																		<td width="12%" align="center">
																			<bean:write name="helper" property="idClienteSolicitante"/>
																		</td>
																		
																		<td width="12%" align="center">
																			<bean:write name="helper" property="idUnidadeSolicitante"/>
																		</td>
																		
																		<td width="21%" align="center">
																			<bean:write name="helper" property="fonePrincipal"/>
																		</td>
																	</tr>
																</logic:iterate>
											
																</table>
														  	</td>
													</tr>
										
													</logic:present>
										
													</table>
											   </td>
										   	</tr>
					


												
												</table>
			       								</td>       						
			                        		</tr>
			                        		
										</table>
			   						</div>
			   						
			  					</td>
			      			</tr>
			      			
							<!-- Dados da Reativação -->
							
							<tr>
								<td>
			           				<div id="layerHideReativacao" style="display:block">
			               				<table width="100%" border="0" bgcolor="#99CCFF">
					    					<tr bgcolor="#99CCFF">
			                      				<td align="center">
			                     					<span class="style2">
			                      					<a href="javascript:extendeTabela('Reativacao',true);"/>
			                      						<b>Dados da Reativa&ccedil;&atilde;o</b>
			                      					</a>
			                     					</span>
			                      				</td>
			                     			</tr>
			                    		</table>
			           				</div>
                   				                        		
			                        <div id="layerShowReativacao" style="display:none">
			
				                    	<table width="100%" border="0" bgcolor="#99CCFF">
			
											<tr bgcolor="#99CCFF">
					                    		<td align="center">
			                       					<span class="style2">
			                         					<a href="javascript:extendeTabela('Reativacao',false);"/>
			                         						<b>Dados da Reativa&ccedil;&atilde;o</b>
			                         					</a>
			                       					</span>
			                         			</td>
			                        		</tr>
			                        		
			                        		<tr bgcolor="#cbe5fe">
												
												<td>
												<table border="0" width="100%">

													<tr>
			                                    		<td class="style3">
			                                    			<strong>N&uacute;mero do RA Atual:</strong>
			                                    		</td>

			                                    		<td>
															<html:text property="numeroRaAtual" 
																readonly="true"
																style="background-color:#EFEFEF; border:0;" 
																size="9"
																maxlength="9" />
														</td>
			                                  		</tr>

													<tr>
			                                    		<td class="style3">
			                                    			<strong>Situa&ccedil;&atilde;o do RA Atual:</strong>
			                                    		</td>

			                                    		<td>
															<html:text property="situacaoRaAtual" 
																readonly="true"
																style="background-color:#EFEFEF; border:0;" 
																size="15"
																maxlength="15" />
														</td>
			                                  		</tr>

													<tr>
			                                    		<td class="style3">
			                                    			<strong>Motivo da Reativa&ccedil;&atilde;o:</strong>
			                                    		</td>

			                                    		<td>
															<html:text property="idMotivoReativacao" readonly="true"
																style="background-color:#EFEFEF; border:0;" size="2"
																maxlength="2" />

															<html:text property="motivoReativacao" readonly="true"
																style="background-color:#EFEFEF; border:0;" size="40"
																maxlength="40" />

														</td>

			                                  		</tr>

													<tr>
			                                    		<td class="style3">
			                                    			<strong>Data da Reativa&ccedil;&atilde;o:</strong>
			                                    		</td>

			                                    		<td>
															<html:text property="dataReativacao" readonly="true"
																style="background-color:#EFEFEF; border:0;" size="10"
																maxlength="10" />
														</td>
			                                  		</tr>

													<tr>
			                                    		<td class="style3">
			                                    			<strong>Hora da Reativa&ccedil;&atilde;o:</strong>
			                                    		</td>

			                                    		<td>
															<html:text property="horaReativacao" 
																readonly="true"
																style="background-color:#EFEFEF; border:0;" 
																size="5"
																maxlength="5" />
														</td>
			                                  		</tr>

													<tr>
			                                    		<td class="style3">
			                                    			<strong>Data Prevista do RA Atual:</strong>
			                                    		</td>

			                                    		<td>
															<html:text property="dataPrevistaRaAtual" readonly="true"
																style="background-color:#EFEFEF; border:0;" size="10"
																maxlength="10" />
														</td>
			                                  		</tr>

													<tr>
			                                    		<td class="style3">
			                                    			<strong>Unidade da Reativa&ccedil;&atilde;o:</strong>
			                                    		</td>

			                                    		<td>
															<html:text property="idUnidadeReativacao" readonly="true"
																style="background-color:#EFEFEF; border:0;" size="4"
																maxlength="4" />

															<html:text property="unidadeReativacao" readonly="true"
																style="background-color:#EFEFEF; border:0;" size="40"
																maxlength="40" />

														</td>

			                                  		</tr>

													<tr>
			                                    		<td class="style3">
			                                    			<strong>Unidade do RA Atual:</strong>
			                                    		</td>

			                                    		<td>
															<html:text property="idUnidadeRaAtual" readonly="true"
																style="background-color:#EFEFEF; border:0;" size="4"
																maxlength="4" />

															<html:text property="unidadeRaAtual" readonly="true"
																style="background-color:#EFEFEF; border:0;" size="40"
																maxlength="40" />

														</td>

			                                  		</tr>

													<tr>
			                                    		<td class="style3">
			                                    			<strong>Observa&ccedil;&atilde;o:</strong>
			                                    		</td>

			                                    		<td>
															<html:textarea property="observacaoReativacao" readonly="true"
																style="background-color:#EFEFEF; border:0;" cols="40"/>
														</td>
			                                  		</tr>
												
												</table>
			       								</td>       						
			                        		</tr>
			                        		
										</table>
			   						</div>
			   						
			  					</td>
			      			</tr>			      			

							<!-- Dados do Encerramento -->
							
							<tr>
								<td>
			           				<div id="layerHideEncerramento" style="display:block">
			               				<table width="100%" border="0" bgcolor="#99CCFF">
					    					<tr bgcolor="#99CCFF">
			                      				<td align="center">
			                     					<span class="style2">
			                      					<a href="javascript:extendeTabela('Encerramento',true);"/>
			                      						<b>Dados do Encerramento</b>
			                      					</a>
			                     					</span>
			                      				</td>
			                     			</tr>
			                    		</table>
			           				</div>
                   				                        		
			                        <div id="layerShowEncerramento" style="display:none">
			
				                    	<table width="100%" border="0" bgcolor="#99CCFF">
			
											<tr bgcolor="#99CCFF">
					                    		<td align="center">
			                       					<span class="style2">
			                         					<a href="javascript:extendeTabela('Encerramento',false);"/>
			                         						<b>Dados do Encerramento</b>
			                         					</a>
			                       					</span>
			                         			</td>
			                        		</tr>
			                        		
			                        		<tr bgcolor="#cbe5fe">
												
												<td>
												<table border="0" width="100%">

													<tr>
			                                    		<td class="style3">
			                                    			<strong>Motivo do Encerramento:</strong>
			                                    		</td>

			                                    		<td>
															<html:text property="idMotivoEncerramento" readonly="true"
																style="background-color:#EFEFEF; border:0;" size="5"
																maxlength="5" />

															<html:text property="motivoEncerramento" readonly="true"
																style="background-color:#EFEFEF; border:0;" size="50"
																maxlength="50" />

														</td>

			                                  		</tr>

													<tr>
			                                    		<td class="style3">
			                                    			<strong>N&uacute;mero do RA de Refer&ecirc;ncia:</strong>
			                                    		</td>

			                                    		<td>
															<html:text property="numeroRaReferencia" readonly="true"
																style="background-color:#EFEFEF; border:0;" size="9"
																maxlength="9" />
														</td>
			                                  		</tr>

													<tr>
			                                    		<td class="style3">
			                                    			<strong>Situa&ccedil;&atilde;o do RA Refer&ecirc;ncia:</strong>
			                                    		</td>

			                                    		<td>
															<html:text property="situacaoRaReferencia" readonly="true"
																style="background-color:#EFEFEF; border:0;" size="15"
																maxlength="15" />
														</td>
			                                  		</tr>

													<tr>
			                                    		<td class="style3">
			                                    			<strong>Data da Encerramento:</strong>
			                                    		</td>

			                                    		<td>
															<html:text property="dataEncerramento" readonly="true"
																style="background-color:#EFEFEF; border:0;" size="10"
																maxlength="10" />
														</td>
			                                  		</tr>

													<tr>
			                                    		<td class="style3">
			                                    			<strong>Hora do Encerramento:</strong>
			                                    		</td>

			                                    		<td>
															<html:text property="horaEncerramento" 
																readonly="true"
																style="background-color:#EFEFEF; border:0;" 
																size="5"
																maxlength="5" />
														</td>
			                                  		</tr>

													<tr>
			                                    		<td class="style3">
			                                    			<strong>Data Prevista:</strong>
			                                    		</td>

			                                    		<td>
															<html:text property="dataPrevistaEncerramento" 
																readonly="true"
																style="background-color:#EFEFEF; border:0;" 
																size="10"
																maxlength="10" />
														</td>
			                                  		</tr>

													<tr>
			                                    		<td class="style3">
			                                    			<strong>Unidade do Encerramento:</strong>
			                                    		</td>

			                                    		<td>
															<html:text property="idUnidadeEncerramento" readonly="true"
																style="background-color:#EFEFEF; border:0;" size="4"
																maxlength="4" />

															<html:text property="unidadeEncerramento" readonly="true"
																style="background-color:#EFEFEF; border:0;" size="40"
																maxlength="40" />

														</td>

			                                  		</tr>

													<tr>
			                                    		<td class="style3">
			                                    			<strong>Usu&aacute;rio do Encerramento:</strong>
			                                    		</td>

			                                    		<td>
															<html:text property="idUsuarioEncerramento" readonly="true"
																style="background-color:#EFEFEF; border:0;" size="4"
																maxlength="4" />

															<html:text property="usuarioEncerramento" readonly="true"
																style="background-color:#EFEFEF; border:0;" size="40"
																maxlength="40" />

														</td>
			                                  		</tr>

													<tr>
			                                    		<td class="style3">
			                                    			<strong>Parecer do Encerramento:</strong>
			                                    		</td>

			                                    		<td>
															<html:textarea property="parecerEncerramento" readonly="true"
																style="background-color:#EFEFEF; border:0;" cols="40"/>
														</td>
			                                  		</tr>
			                                  		
			                                  		<tr>
			                                    		<td class="style3">
			                                    			<strong>Motivo da n&atilde;o cobran&ccedil;a:</strong>
			                                    		</td>

			                                    		<td>
															<html:text property="motivoNaoCobranca" 
																readonly="true"
																style="background-color:#EFEFEF; border:0;" 
																size="30"
																maxlength="30" />
														</td>
			                                  		</tr>
												
												</table>
			       								</td>       						
			                        		</tr>
			                        		
										</table>
			   						</div>
			   						
			  					</td>
			      			</tr>
			      			
			      			<!--Dados de Pagamentos em Duplicidade - Erivan Sousa-->
							<logic:present name="colecaoRAPagamentoDuplicidade">
							<tr>
								<td>
			           				<div id="layerHidePagamentosDuplicidade" style="display:block">
			               				<table width="100%" border="0" bgcolor="#99CCFF">
					    					<tr bgcolor="#99CCFF">
			                      				<td align="center">
			                     					<span class="style2">
			                      					<a href="javascript:extendeTabela('PagamentosDuplicidade',true);"/>
			                      						<b>Dados de Pagamentos em Duplicidade</b>
			                      					</a>
			                     					</span>
			                      				</td>
			                     			</tr>
			                    		</table>
			           				</div>
                   				                        		
			                        <div id="layerShowPagamentosDuplicidade" style="display:none">
			
				                    	<table width="100%" border="0" bgcolor="#99CCFF">
			
											<tr bgcolor="#99CCFF">
					                    		<td align="center">
			                       					<span class="style2">
			                         					<a href="javascript:extendeTabela('PagamentosDuplicidade',false);"/>
			                         						<b>Dados de Pagamentos em Duplicidade</b>
			                         					</a>
			                       					</span>
			                         			</td>
			                        		</tr>
			                        		
			                        		<tr bgcolor="#cbe5fe">
												
												<td>
												<table border="0" width="100%">

													<tr>
			                                    		<td bgcolor="#90c7fc" align="center" width="25%">
			                                    			<strong>Mês/Ano Pagamento</strong>
			                                    		</td>
			                                    		<td bgcolor="#90c7fc" align="center">
			                                    			<strong>Data do Pagamento</strong>
			                                    		</td>
			                                    		<td bgcolor="#90c7fc" align="center">
			                                    			<strong>Valor do Pagamento</strong>
			                                    		</td>
			                                    		<td bgcolor="#90c7fc" align="center">
			                                    			<strong>Devolvido?</strong>
			                                    		</td>			                                    					                                    		
			                                  		</tr>
													<%	BigDecimal valorTotalPagamentoDuplicidade = null;	%>
			                                  		<logic:present name="colecaoRAPagamentoDuplicidade">
														<%!	int cont2 = 0;	%>
														<logic:iterate name="colecaoRAPagamentoDuplicidade"
															id="rAPagamentoDuplicidade" 
															type="RegistroAtendimentoPagamentoDuplicidade">																	
															<%	cont2 = cont2 + 1;
																if (cont2 % 2 == 0) {	%>
																	<tr bgcolor="#cbe5fe">
															<%	} else {	%>																			
																	<tr bgcolor="#FFFFFF">
															<%	}	%>
																		
								                       		<%	if(valorTotalPagamentoDuplicidade == null){
								                         			valorTotalPagamentoDuplicidade = new BigDecimal(0.0);                       			
								                       			}
																if(rAPagamentoDuplicidade.getValorPagamento() != null){
									                       			valorTotalPagamentoDuplicidade = 
									                       				valorTotalPagamentoDuplicidade.add(rAPagamentoDuplicidade.getValorPagamento()); 
																}	%>
																		
																		<td align="center"><%=rAPagamentoDuplicidade.getFormatarAnoMesPagamentoParaMesAno()%></td>
																		<td align="center"><%=Util.formatarData(rAPagamentoDuplicidade.getDataPagamento())%></td>
																		<td align="right"><%=Util.formatarMoedaReal(rAPagamentoDuplicidade.getValorPagamento())%></td>
																		<td align="center"><%=rAPagamentoDuplicidade.getIndicadorPagamentoDevolvido() == 1?"SIM":"NÃO"%></td>
																	</tr>
														</logic:iterate>
														
														<% if (valorTotalPagamentoDuplicidade != null) {	%>
								                			<tr>
									                    		<td colspan="2">
										                    		<div align="left">
										                    		<strong>Valor Total:</strong>
										                    		</div>
									                    		</td>
								        		        		<td>
												                    <div align="right">
										   		              		<strong><%= Util.formatarMoedaReal(valorTotalPagamentoDuplicidade) %></strong>
												                    </div>
								                  				</td>
								                			</tr>
														<%	}	%>														
													</logic:present>
			                          
												</table>
			       								</td>       						
			                        		</tr>
			                        		
										</table>
			   						</div>
			   						
			  					</td>
			      			</tr>
			      			</logic:present>
			      			<!-- Final pagamentos em duplicidade-->
							<tr>
								<td>
								<bean:define name="ConsultarRegistroAtendimentoActionForm" property="numeroRAPesquisado" id="ra"/>
								<table width="100%">
									<tr>
			                			<td>
			                				<input name="ButtonAtualizar" 
			                					type="button" 
			                					class="bottonRightCol" 
			                					value="Atualizar" 
			                					onclick="javascript:atualizarRA('<html:rewrite page="/exibirAtualizarRegistroAtendimentoAction.do?definirDataPrevista=ok&idRegistroAtualizacao="/>', '<bean:write name="ConsultarRegistroAtendimentoActionForm" property="numeroRAPesquisado"/>')";/> 
			                  				
			                  				<input name="ButtonReiterar" 
			                  					type="button" 
			                  					class="bottonRightCol" 
			                  					value="Reiterar"
			                  					onclick="javascript:reiterar('<html:rewrite page="/exibirReiterarRegistroAtendimentoAction.do?resetarReiteracao=true&numeroRA="/>', '<bean:write name="ConsultarRegistroAtendimentoActionForm" property="numeroRAPesquisado"/>')";/>  
			                  				
			                  				<input name="ButtonTramitar" 
				                  				type="button" 
				                  				class="bottonRightCol"  
				                  				value="Tramitar" 
				                  				onClick="javascript:tramitar('<html:rewrite page="/exibirTramitarRegistroAtendimentoAction.do?resetarTramitacao=true&numeroRA="/>', '<bean:write name="ConsultarRegistroAtendimentoActionForm" property="numeroRAPesquisado"/>')";/> 
			                  				
			                  				<input name="ButtonEncerrar" 
			                  					type="button" 
			                  					class="bottonRightCol"  
			                  					value="Encerrar" 
			                  					onClick="javascript:encerrarRA('<html:rewrite page="/exibirEncerrarRegistroAtendimentoAction.do?resetarEncerramento=true&numeroRA="/>', '<bean:write name="ConsultarRegistroAtendimentoActionForm" property="numeroRAPesquisado"/>')";/> 
						                  	
						                  	<input name="ButtonReativar" 
						                  		type="button" 
						                  		class="bottonRightCol"  
						                  		value="Reativar" 
						                  		onClick="javascript:reativarRA('<html:rewrite page="/exibirReativarRegistroAtendimentoAction.do?resetarReativar=true&numeroRA="/>', '<bean:write name="ConsultarRegistroAtendimentoActionForm" property="numeroRAPesquisado"/>')";/>  
						                  	
						                  	<input name="ButtonImprimir" 
						                  		type="button" 
						                  		class="bottonRightCol" 
												value="Imprimir" 
												onClick="javascript:imprimir(false);" > 
						                  	
						                  	<input name="ButtonOrdemSer" 
						                  		type="button" 
						                  		class="bottonRightCol" 
						                  		value="Gerar O.S"
						                  		onClick="javascript:gerarOS();"> 
										</td>
									</tr>
					              	
					              	<tr>
					                	<td> 
					                		<input name="ButtonSolicitantes" 
					                			type="button" 
					                			class="bottonRightCol"  
					                			value="Consultar Solicitantes" 
					                			onClick="javascript:consultarSolicitantes();">
					                  		
					                  		<input name="ButtonTramites" 
					                  			type="button" 
					                  			class="bottonRightCol"  
					                  			value="Consultar Tr&acirc;mites" 
					                  			onClick="javascript:consultarTramites();">
					                  			
					                  		<input name="ButtonOrdens" 
					                  			type="button" 
					                  			class="bottonRightCol" 
					                  			value="Consultar O.S" 
					                  			onClick="javascript:consultarOS();">
					                  			
				                  			<input name="ButtonImprimirViaCliente" 
						                  		type="button" 
						                  		class="bottonRightCol" 
												value="Imprimir Via Cliente" 
												onClick="javascript:imprimir(true);" >
										</td>
					              	</tr>
					              	
					              	<tr>
						            	<td width="233">
						            	
			                  				<logic:notPresent name="naoHabilitarNavegacao" scope="session">
			                  					
			                  					<input type="button" 
			                  					name="ButtonReset" 
			                  					class="bottonRightCol"
												value="Voltar"
												onClick="window.location.href='filtrarRegistroAtendimentoAction.do'">
						              	
						              		</logic:notPresent>
						              		
						              		<logic:present name="naoHabilitarNavegacao" scope="session">
			                  					
			                  					<input type="button" 
			                  					name="ButtonReset" 
			                  					class="bottonRightCol"
												value="Voltar"
												onClick="window.location.href='exibirFiltrarRegistroAtendimentoAction.do'">
						              	
						              		</logic:present>
						              		
						              	</td>
						          	</tr>
									
								</table>
			
								</td>
			
							</tr>

						</table>
  					</td>
				</tr>
   		
	</table>
	<!-- Fim do Corpo - Rafael Pinto -->
	<jsp:include page="/jsp/relatorio/escolher_tipo_relatorio.jsp?relatorio=" />
	<%@ include file="/jsp/util/rodape.jsp"%>
</html:form>
</body>
</html:html>