<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ page import="gcom.util.ConstantesSistema"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
<head>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">

<%-- Carrega valida��es do validator --%>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="EncerrarRegistroAtendimentoActionForm"/>
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js"></script>

<script language="JavaScript">
	/* Valida Form */
	function validarForm() {
		var form = document.forms[0];
		if (validateEncerrarRegistroAtendimentoActionForm(form)) {
			if ((raReferenciaEnabled && 
				form.numeroRAReferenciaRetorno.value != '') || 
				!raReferenciaEnabled) {
				if (form.especificacaoIndicadorParecer.value != '1'  || 
					(form.especificacaoIndicadorParecer.value == '1'  && 
					form.parecerEncerramento.value != '')) {
					
					if (validaDebito()){
						botaoAvancarTelaEspera('/gsan/encerrarRegistroAtendimentoAction.do'); 
					}
					
				} else {
					alert('Informe o Parecer de Encerramento.');
				}
			} else {
				alert('Informe o N�mero de RA de Refer�ncia.');
			}
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
	/* Limpar N�mero RA de Refer�ncia */
	function limparNumeroRAReferencia() {
		var form = document.forms[0];
		
      	form.numeroRAReferencia.value = '';
	    form.numeroRAReferenciaRetorno.value = '';
	}
	
	/* Limpar Form */
	function limparForm() {
		var form = document.forms[0];
		
		limparNumeroRAReferencia();
      	form.motivoEncerramentoId.selectedIndex = 0;
      	if (form.usuarioRegistroUnidadeIndicadorCentralAtendimento.value == '1') {
	      	form.dataEncerramento.value = '';
		    form.horaEncerramento.value = '';
      	}
	    form.parecerEncerramento.value = '';
	    
	    form.action = 'exibirEncerrarRegistroAtendimentoAction.do';
		form.submit();
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

	    if (tipoConsulta == 'registroAtendimento') {
	      form.numeroRAReferencia.value = codigoRegistro;
	      form.numeroRAReferenciaRetorno.value = descricaoRegistro;
	      form.numeroRAReferenciaRetorno.style.color = '#000000';	
	    }
	}
	
	var raReferenciaEnabled = true;
	function habilitaRAReferencia() {
		var form = document.forms[0];

		if(form.indicadorDuplicidade.value == '1') {
			document.getElementById('imgRAReferencia').disabled = false;
			form.numeroRAReferencia.disabled = false;
			raReferenciaEnabled = true;
		} else {
			limparNumeroRAReferencia();
			document.getElementById('imgRAReferencia').disabled = true;
			form.numeroRAReferencia.disabled = true;
			raReferenciaEnabled = false;			
		}
	}

	function consultarRA(url,id){
		window.location.href=url+id;
	}
	
	function chamarForm(caminhoAction,objetoRelacionado){
		if(objetoRelacionado.disabled != true){
			var form = document.forms[0];
	   		form.action = caminhoAction;
	   		form.submit();
		}
	}
	
	function validarIndicadorDuplicidade(){
		var form = document.forms[0];
		
		form.action = 'exibirEncerrarRegistroAtendimentoAction.do';
		form.submit();
	}
	
	function habilitarQtdParcelaButton(){
		var form = document.forms[0];
		if(form.motivoNaoCobranca.value == "-1"){	
			form.percentualCobranca.value = "-1"
			form.valorParcelas.value = "";
			form.percentualCobranca.disabled = false;
			form.quantidadeParcelas.disabled = false;
			form.buttonCalcular.disabled = false;
		}else{
			form.percentualCobranca.value = "-1"
			form.quantidadeParcelas.value = "";
			form.valorParcelas.value = "";
			form.percentualCobranca.disabled = true;
			form.quantidadeParcelas.disabled = true;
			form.buttonCalcular.disabled = true;
		}
	}
	
	function calcularValores(){
	
		var form = document.forms[0];
	   	if (validaDebito()){
	   	
	   		form.action='exibirEncerrarRegistroAtendimentoAction.do?calculaValores=S';
	      	form.submit();
		}
	}
	
</script>
</head>

<body leftmargin="5" topmargin="5" onload="habilitaRAReferencia();">

<div id="formDiv">
<html:form action="/encerrarRegistroAtendimentoAction.do"
	name="EncerrarRegistroAtendimentoActionForm"
	type="gcom.gui.atendimentopublico.registroatendimento.EncerrarRegistroAtendimentoActionForm"
	method="post">
	
	<html:hidden property="especificacaoIndicadorParecer"/>
	<html:hidden property="usuarioRegistroUnidadeIndicadorCentralAtendimento"/>
	<html:hidden property="indicadorDuplicidade"/>
	
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
						<td class="parabg">Encerrar Registro de Atendimento</td>
						<td width="11">
							<img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif" />
						</td>
					</tr>
				</table>
			
				<table width="100%">
					<tr>
						<td width="90%" colspan="2">&nbsp;</td>
						<logic:present scope="application" name="urlHelp">
						<td align="right"><a href="javascript:abrirPopupHelp('${applicationScope.urlHelp}atendimentoRegistroEncerrar', 500, 700);"><span style="font-weight: bold"><font color="#3165CE">Ajuda</font></span></a></td>									
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
	                     						<b>Dados Gerais do Registro de Atendimento</b>
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
                     						<b>Dados Gerais do Registro de Atendimento</b>
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
													style="background-color:#EFEFEF; border:0;" size="9"
													maxlength="9" />
		                                  	</td>
		                            	</tr>
		                            	
										<c:if test="${EncerrarRegistroAtendimentoActionForm.numeroRaAssociado != null}">
			                            <tr> 
		                            		<td height="10"><strong>N&uacute;mero do RA Associado:</strong></td>
		                              		<td> 
												<html:text property="numeroRaAssociado" readonly="true"
													style="background-color:#EFEFEF; border:0;" size="9"
													maxlength="9" />
		                              			<strong>Situa&ccedil;&atilde;o do RA Associado:</strong>
												<html:text property="situacaoRaAssociado" readonly="true"
													style="background-color:#EFEFEF; border:0;" size="12"
													maxlength="9" />
		                                	</td>
			                            </tr>
										</c:if>
										
		                            	<tr> 
		                              		<td class="style3"><strong>Tipo de Solicita&ccedil;&atilde;o:</strong></td>
		                              		<td colspan="3">
												<html:text property="tipoSolicitacaoId" readonly="true"
													style="background-color:#EFEFEF; border:0;" size="4"
													maxlength="4" />		                              		
												<html:text property="tipoSolicitacaoDescricao" readonly="true"
													style="background-color:#EFEFEF; border:0;" size="45"
													maxlength="50" />
		                                	</td>
		                            	</tr>

		                            	<tr> 
		                              		<td height="10"><strong>Especifica&ccedil;&atilde;o:</strong></td>
		                              		<td colspan="3"> 
												<html:text property="especificacaoId" readonly="true"
													style="background-color:#EFEFEF; border:0;" size="4"
													maxlength="4" />		                              		
												<html:text property="especificacaoDescricao" readonly="true"
													style="background-color:#EFEFEF; border:0;" size="45"
													maxlength="50" />
		                                 	</td>
		                            	</tr>

			                            <tr>
			                              	<td class="style3"><strong>Meio de Solicita&ccedil;&atilde;o:</strong></td>
			                              	<td colspan="3">
												<html:text property="meioSolicitacaoDescricao" readonly="true"
													style="background-color:#EFEFEF; border:0;" size="50"
													maxlength="50" />
											</td>
			                            </tr>

	                              		<tr>
	                                		<td><strong>Matr&iacute;cula do Im&oacute;vel:</strong></td>
	                                		<td colspan="3"> 
												<html:text property="matriculaImovel" readonly="true"
													style="background-color:#EFEFEF; border:0;" size="8"
													maxlength="8" />
												<html:text property="inscricaoImovel" readonly="true"
													style="background-color:#EFEFEF; border:0;" size="21"
													maxlength="21" />
											</td>
                              			</tr>

			                            <tr> 
			                              	<td width="31%" height="10"><strong>Data e Hora do Atendimento:</strong></td>
			                              	<td colspan="3"> 
												<html:text property="dataAtendimento" readonly="true"
													style="background-color:#EFEFEF; border:0;" size="10"
													maxlength="10" />
												<html:text property="horaAtendimento" readonly="true"
													style="background-color:#EFEFEF; border:0;" size="5"
													maxlength="5" />
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
                                    		<td class="style3">
                                    			<strong>Data da Encerramento:</strong>
                                    		</td>

                                    		<td>
												<html:text property="dataEncerramentoRA" readonly="true"
													style="background-color:#EFEFEF; border:0;" size="10"
													maxlength="10" />
											</td>
                                  		</tr>

										<tr>
                                    		<td class="style3"><strong>Motivo do Encerramento:</strong></td>
                                    		<td>
												<html:text property="idMotivoEncerramento" readonly="true"
													style="background-color:#EFEFEF; border:0;" size="5"
													maxlength="5" />
												<html:text property="motivoEncerramento" readonly="true"
													style="background-color:#EFEFEF; border:0;" size="45"
													maxlength="50" />
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
													style="background-color:#EFEFEF; border:0;" size="4"
													maxlength="4" />
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
                                    		<td class="style3"><strong>Nome do Solicitante:</strong></td>
                                    		<td colspan="3">
												<html:text property="nomeSolicitante" readonly="true"
													style="background-color:#EFEFEF; border:0;" size="40"
													maxlength="40" />
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
													style="background-color:#EFEFEF; border:0;" size="55"
													maxlength="60" />
		                                 	</td>
		                               	</tr>

	                              		<tr> 
	                                		<td class="style3">
	                                			<strong>Bairro:</strong>
	                                		</td>
	                                		<td>
												<html:text property="bairroId" readonly="true"
													style="background-color:#EFEFEF; border:0;" size="4"
													maxlength="4" />
				
												<html:text property="bairroDescricao" readonly="true"
													style="background-color:#EFEFEF; border:0;" size="40"
													maxlength="40" />
											</td>
	                              		</tr>
	
	                              		<tr> 
	                                		<td class="style3">
	                                			<strong>&Aacute;rea do Bairro:</strong>
	                                		</td>
	                                		<td>
												<html:text property="areaBairroId" readonly="true"
													style="background-color:#EFEFEF; border:0;" size="4"
													maxlength="4" />
				
												<html:text property="areaBairroDescricao" readonly="true"
													style="background-color:#EFEFEF; border:0;" size="40"
													maxlength="40" />
											</td>
                              			</tr>
                              			
										<tr> 	
	                                		<td class="style3">
	                                			<strong>Local/Setor/Quadra:</strong>
	                                		</td>
	                                		<td>
												<html:text property="localidadeId" readonly="true"
													style="background-color:#EFEFEF; border:0;" size="4"
													maxlength="4" />
												<strong>/</strong>
												<html:text property="setorComercialId" readonly="true"
													style="background-color:#EFEFEF; border:0;" size="4"
													maxlength="4" />
												<strong>/</strong>
												<html:text property="quadraId" readonly="true"
													style="background-color:#EFEFEF; border:0;" size="4"
													maxlength="4" />
											</td>
                              			</tr>

										<tr> 	
	                                		<td class="style3">
	                                			<strong>Divis&atilde;o de Esgoto:</strong>
	                                		</td>
	                                		<td colspan="3">
												<html:text property="divisaoEsgotoId" readonly="true"
													style="background-color:#EFEFEF; border:0;" size="4"
													maxlength="4" />
				
												<html:text property="divisaoEsgotoDescricao" readonly="true"
													style="background-color:#EFEFEF; border:0;" size="40"
													maxlength="40" />
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
													style="background-color:#EFEFEF; border:0;" size="4"
													maxlength="4" />

												<html:text property="unidadeAtendimentoDescricao" readonly="true"
													style="background-color:#EFEFEF; border:0;" size="40"
													maxlength="40" />
											</td>
			                            </tr>

			                            <tr> 
			                              	<td class="style3"><strong>Unidade Atual:</strong></td>
			                              	<td colspan="3">
												<html:text property="unidadeAtualId" readonly="true"
													style="background-color:#EFEFEF; border:0;" size="4"
													maxlength="4" />

												<html:text property="unidadeAtualDescricao" readonly="true"
													style="background-color:#EFEFEF; border:0;" size="40"
													maxlength="40" />
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
                	<td height="10" colspan="2">Para encerrar o registro de atendimento, informe os dados abaixo:</td>
                 </tr>
				<!-- Dados do Local Ocorrencia -->
               	<tr bgcolor="#cbe5fe">
           			<td align="center">
                   		<table width="100%" border="0" bgcolor="#99CCFF">
	    					<tr bgcolor="#99CCFF">
                     			<td align="center">
                   					<span class="style2">
                    						<b>Dados do Encerramento do Registro de Atendimento</b>
                   					</span>
                     			</td>
                    		</tr>

							<tr bgcolor="#cbe5fe">
								<td>
									<table border="0" width="100%">
						                <tr> 
						                	<td class="style3">
						                		<strong>Motivo do Encerramento:</strong>
						                		<font color="#FF0000">*</font>
						                	</td>
						                	<td colspan="6">
						                		<span class="style2">
						                			<strong> 
														<html:select property="motivoEncerramentoId" style="width: 230px;" onchange="javascript:validarIndicadorDuplicidade();">
															<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
															<logic:present name="colecaoMotivoEncerramento" scope="session">
																<html:options collection="colecaoMotivoEncerramento" labelProperty="descricao" property="id" />
															</logic:present>
														</html:select>
						                  			</strong>
						                  		</span>
						                  	</td>
						                </tr>

										<tr> 	
                                    		<td class="style3">
                                    			<strong>N&uacute;mero do RA de Refer&ecirc;ncia:</strong>
                                    		</td>
                                    		<td>
                								<span class="style2">
                									<strong>
														<html:text property="numeroRAReferencia" size="9" maxlength="9"
							   									   onkeypress="javascript:validaEnterComMensagem(event, 'exibirEncerrarRegistroAtendimentoAction.do?validaNumeroRAReferencia=true', 'numeroRAReferencia','N�mero do RA de Refer�ncia');"/>
														<img src="<bean:message key='caminho.imagens'/>pesquisa.gif" width="23"	height="21" 
						 									 style="cursor:hand;" name="imagem"	onclick="javascript:chamarPopup('exibirPesquisarRegistroAtendimentoAction.do?resetarPesquisaRA=true', 'registroAtendimento', null, null, 275, 480, '', document.forms[0].numeroRAReferencia);"
						 									 alt="Pesquisar" id="imgRAReferencia">
														
														<logic:present name="raEncontrada" scope="session">
															<html:text property="numeroRAReferenciaRetorno" readonly="true"
								   									   style="background-color:#EFEFEF; border:0; color: #000000" size="40"/>
														</logic:present> 
														<logic:notPresent name="raEncontrada" scope="session">
															<html:text property="numeroRAReferenciaRetorno" readonly="true"
								   									   style="background-color:#EFEFEF; border:0; color: #ff0000" size="40"/>
														</logic:notPresent> 
														
														<a href="javascript:limparNumeroRAReferencia();">
															<img border="0" title="Apagar" src="<bean:message key='caminho.imagens'/>limparcampo.gif"/></a>
                  									</strong>
                  								</span>
											</td>
                                  		</tr>
                                 		
										<tr> 	
                                    		<td class="style3">
                                    			<strong>Data do Encerramento:</strong>
                                    			<font color="#FF0000">*</font>
                                    		</td>
                                    		<td colspan="3">
                                    			<span class="style2">
                                    				<strong>
                                    					<c:choose>
                                    						<c:when test="${EncerrarRegistroAtendimentoActionForm.usuarioRegistroUnidadeIndicadorCentralAtendimento == '1'}">
																<html:text property="dataEncerramento" size="11" maxlength="10" tabindex="3" onkeyup="mascaraData(this, event);"/>
																<a href="javascript:abrirCalendario('EncerrarRegistroAtendimentoActionForm', 'dataEncerramento');">
																<img border="0" src="<bean:message key='caminho.imagens'/>calendario.gif" width="16" height="15" border="0" alt="Exibir Calend�rio" tabindex="4"/></a>
			                       								<strong>(dd/mm/aaaa)</strong> 
                                    						</c:when>
                                    						<c:otherwise>
																<html:text property="dataEncerramento" readonly="true" style="background-color:#EFEFEF; border:0;" 
																		   size="10" maxlength="10" />
                                    						</c:otherwise>
                                    					</c:choose>
                  									</strong>
                  								</span>            		
											</td>
                                  		</tr>
                                  		
										<tr> 	
                                    		<td class="style3">
                                    			<strong>Hora do Encerramento:</strong>
                                    			<font color="#FF0000">*</font>
                                    		</td>
                                    		<td colspan="3">
                                    			<span class="style2">
                                    				<strong> 
                                    					<c:choose>
                                    						<c:when test="${EncerrarRegistroAtendimentoActionForm.usuarioRegistroUnidadeIndicadorCentralAtendimento == '1'}">
																<html:text property="horaEncerramento" size="6" maxlength="5" tabindex="3" onkeyup="mascaraHora(this, event);"/>
			                       								<strong>(hh:mm)</strong> 
                                    						</c:when>
                                    						<c:otherwise>
																<html:text property="horaEncerramento" readonly="true" style="background-color:#EFEFEF; border:0;" 
																		   size="6" maxlength="6" />
                                    						</c:otherwise>
                                    					</c:choose>
                  									</strong>
                  								</span>            		
											</td>
                                  		</tr>

										<tr> 	
                                    		<td class="style3">
                                    			<strong>Parecer do Encerramento:</strong>
                                    		</td>
                                    		<td colspan="3">
                                    			<span class="style2">
                                    				<strong>
    												
    												<html:textarea property="parecerEncerramento" 
    													cols="50" 
    													rows="8" 
    													onkeyup="limitTextArea(document.forms[0].parecerEncerramento, 600, document.getElementById('utilizado'), document.getElementById('limite'));"/><br>
													<strong>
													
													<span id="utilizado">0</span>/<span id="limite">600</span>
													
													</strong>
    												
    												</strong>
												</span>
											</td>
                                  		</tr>
								</table>
							</td>
                            </tr>
						</table>
       				</td>
      			</tr>
      			
      			<tr>
      				<td height="15"></td>
      			</tr>

				<logic:notEmpty name="EncerrarRegistroAtendimentoActionForm" property="idTipoDebito">

				<tr bgcolor="#cbe5fe">
           			<td align="center">
                   		
                   		<table width="100%" border="0" bgcolor="#99CCFF">
	    				<tr bgcolor="#99CCFF">
                     		<td align="center">
                   				<span class="style2">
                    					<b>Dados da Gera��o do D�bito</b>
                   				</span>
                     		</td>
                    	</tr>
                    		
                    	<tr bgcolor="#cbe5fe">
							<td>
								<table border="0" width="100%">
								<tr>
									<td height="10"><strong>Tipo de D�bito:</strong></td>

									<td colspan="2"><html:text property="idTipoDebito" readonly="true"
											style="background-color:#EFEFEF; border:0;" size="5"
											maxlength="5" /> 
											<html:text property="descricaoTipoDebito" readonly="true"
											style="background-color:#EFEFEF; border:0;" size="45"
											maxlength="45" /></td>
								</tr>
								
								<logic:notEqual name="EncerrarRegistroAtendimentoActionForm" property="alteracaoValor" value="OK">
															
								<tr>
									<td><strong>Valor do D�bito:</strong></td>
									<td colspan="2"><html:text
										property="valorDebito" readonly="true"
										style="background-color:#EFEFEF; border:0;text-align: right;"
										size="15" maxlength="15" /></td>
								</tr>
								
								</logic:notEqual>
															
								<logic:equal name="EncerrarRegistroAtendimentoActionForm" property="alteracaoValor" value="OK">
															
								<tr>
									<td><strong>Valor do D�bito:</strong></td>
									<td colspan="2"><html:text
										property="valorDebito" 
										style="text-align: right;"
										size="15" maxlength="15" onkeyup="formataValorMonetario(this, 11)"/></td>
								</tr>
								
								</logic:equal>							
								
								<logic:present name="permissaoMotivoNaoCobranca">
								
								<tr>
									<td><strong>Motivo da N�o Cobran�a:<font color="#FF0000">*</font></strong></td>
									<td colspan="2"><html:select property="motivoNaoCobranca" style="width: 230px;"
										onchange="habilitarQtdParcelaButton();">
										<html:option value="-1">&nbsp;</html:option>
										<logic:present name="colecaoServicoNaoCobrancaMotivo">
											<html:options collection="colecaoServicoNaoCobrancaMotivo" labelProperty="descricao" property="id" />
										</logic:present>
										</html:select></td>
								</tr>
								<tr>
									<td><strong>Percentual de Cobran�a:<font color="#FF0000">*</font></strong></td>
									<td colspan="2">
										<html:select property="percentualCobranca" style="width: 70px;"
										onchange="limpaValorParcela();calculaValorParcela();">
										<html:option value="-1">&nbsp;</html:option>
										<html:option value="100">100%</html:option>
										<html:option value="70">70%</html:option>
										<html:option value="50">50%</html:option>
										</html:select></td>
								</tr>
								<tr>
									<td><strong>Quantidade de Parcelas:<font color="#FF0000">*</font></strong></td>
									<td colspan="2">
										<html:text property="quantidadeParcelas" size="5" maxlength="5"/></td>
								</tr>
								<tr>
									<td><strong>Valor da Parcela:</strong></td>
									<td>
										<html:text property="valorParcelas" readonly="true"
										style="background-color:#EFEFEF; border:0;text-align: right;"
										size="15" maxlength="15" /></td>
									<td align="right">
										<input type="button" class="bottonRightCol" value="Calcular"
										name="buttonCalcular" onclick="calcularValores();" 
										style="width: 80px"></td>
								</tr>
								
								</logic:present>
								
								<logic:notPresent name="permissaoMotivoNaoCobranca">
								<tr>
									<td><strong>Percentual de Cobran�a: <font color="#FF0000">*</font></strong></td>
									<td colspan="2">
										<html:select property="percentualCobranca" style="width: 70px;">
										<html:option value="100">100%</html:option>
										</html:select></td>
								</tr>
								<tr>
									<td><strong>Quantidade de Parcelas:<font color="#FF0000">*</font></strong></td>
									<td colspan="2">
										<html:text property="quantidadeParcelas" size="5" maxlength="5"
										readonly="true" /></td>
								</tr>
								<tr>
									<td><strong>Valor da Parcela:</strong></td>
									<td colspan="2">
										<html:text property="valorParcelas"	readonly="true"
										style="background-color:#EFEFEF; border:0;text-align: right;"
										size="15" maxlength="15" /></td>
								</tr>
								
								</logic:notPresent>
								
                    		
                    			</table>
                    		</td>
                    	</tr>
                    	</table>
                    </td>
                </tr>
                
                
                </logic:notEmpty>


				<tr>
					<td>

					<table width="100%">
						<tr>
                			<td>
                			<logic:present name="inserirTarifaSocial" scope="session">
	                				<input name="ButtonVoltar"   
                					type="button" 
                					class="bottonRightCol" 
                					value="Voltar" onclick="javascript:window.location.href='/gsan/exibirInserirTarifaSocialAction.do?menu=sim';">
               				</logic:present>
               				<logic:notPresent name="inserirTarifaSocial" scope="session">
                				<input name="ButtonVoltar"   
                					type="button" 
                					class="bottonRightCol" 
                					value="Voltar" 
                					onClick="javascript:limparForm(); history.back();" />
                			</logic:notPresent>

								<input name="ButtonDesfazer" 
									type="button" 
									class="bottonRightCol" 
									value="Desfazer" 
									onclick="javascript:limparForm();">
								
								<input name="ButtonCancelar" 
									type="button" 
									class="bottonRightCol" 
									value="Cancelar" 
									onClick="javascript:window.location.href='/gsan/telaPrincipal.do'">
                      		</td>
							<td width="54%" align="right">
                        		<input name="botaoConcluir" 
                        			type="button" 
                        			class="bottonRightCol" 
                        			value="Encerrar" 
                        			onClick="javascript:validarForm();"> 
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
</div>

<%@ include file="/jsp/util/telaespera.jsp"%>


</body>
</html:html>