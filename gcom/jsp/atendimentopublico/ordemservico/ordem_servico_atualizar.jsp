<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/fmt.tld" prefix="fmt"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
<head>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="AtualizarOrdemServicoActionForm" />

<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js"></script>

<script language="JavaScript">
	/* Valida Form */
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

	    if (tipoConsulta == 'tipoServico') {
	      form.idServicoTipo.value = codigoRegistro;
	      form.descricaoServicoTipo.value = descricaoRegistro;
	      form.descricaoServicoTipo.style.color = "#000000";
	    }
	    if (tipoConsulta == 'ordemServico') {
	      form.idOrdemServicoReferencia.value = codigoRegistro;
	      form.descricaoOrdemServicoReferencia.value = descricaoRegistro;
	      form.descricaoOrdemServicoReferencia.style.color = "#000000";
	    } 
	    if (tipoConsulta == 'servicoTipoReferencia') {
	      form.idServicoTipoReferencia.value = codigoRegistro;
	      form.descricaoServicoTipoReferencia.value = descricaoRegistro;
	      form.descricaoServicoTipoReferencia.style.color = "#000000";
	    } 
	    
	}
	/* Consultar Trâmites */
	function consultarTramites() {
		var form = document.forms[0];
		abrirPopup('exibirConsultarRegistroAtendimentoTramiteAction.do?numeroRA='+form.numeroRA.value, 550, 735);
	}		
	
	function validarForm() {
		var form = document.forms[0];
		if(validateAtualizarOrdemServicoActionForm(form)){
			form.action = "/gsan/atualizarOrdemServicoAction.do";
    		form.submit();
		}
	}
	
	function reload() {
		var form = document.AtualizarOrdemServicoActionForm;
		form.action = "/gsan/exibirAtualizarOrdemServicoAction.do";
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
	    form.action = 'exibirAtualizarOrdemServicoAction.do?primeiraVez=sim';
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
	
	function consultarOS() {
		var form = document.forms[0];
		abrirPopup('exibirConsultarRegistroAtendimentoOSAction.do?numeroRA='+form.idRegistroAtendimento.value, 550, 735);
	}
	
	function limparCamposServicoTipo(){
	 var form = document.forms[0];
	 form.descricaoServicoTipo.value='';
	 if(form.idServicoTipoReferencia != null){
	  form.idServicoTipoReferencia.value = '';
	  form.descricaoServicoTipoReferencia.value = '';
	 }
	 if(form.idOrdemServicoReferencia != null){
	  form.idOrdemServicoReferencia.value = '';
	  form.descricaoOrdemServicoReferencia.value = '';
	 }
	}
</script>
</head>


<logic:present name="fecharPopup">
<body leftmargin="0" topmargin="0"
	onload="javascript:chamarReload('${sessionScope.retornoTela}');window.close();">
</logic:present>

<logic:notPresent name="fecharPopup">
<body leftmargin="5" topmargin="5">
</logic:notPresent>
<html:form action="/exibirAtualizarOrdemServicoAction" method="post">
	

	<logic:notPresent name="ehPopup">
	
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
	   </logic:notPresent>
	   <logic:present name="ehPopup">
		 <table width="600" border="0" cellspacing="5" cellpadding="0">
			<tr>
				<td width="615" valign="top" class="centercoltext">
				<table height="100%">
					<tr>
						<td></td>
					</tr>
				</table>
	   </logic:present>
		
				<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
					<tr>
						<td width="11">
							<img border="0" src="<bean:message key="caminho.imagens"/>parahead_left.gif" />
						</td>
						<td class="parabg">Atualizar Ordem de Serviço</td>
						<td width="11">
							<img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif" />
						</td>
					</tr>
				</table>
			
				<p>&nbsp;</p>

				<!--Inicio da Tabela Dados Gerais do Registro de Atendimento -->
            	<table width="100%" border="0">

				<%-- 

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

										<!-- Numero do RA -->

		                            	<tr>
		                              		<td height="10"><strong>N&uacute;mero do RA:</strong></td>
		                              		<td width="10%"> 
												<input type="text" name="numeroRA" readonly="true"
													style="background-color:#EFEFEF; border:0;" size="9"
													maxlength="9" 
													value="${registroAtendimentoHelper.registroAtendimento.id}" />
		                                  	</td>
		                            	</tr>
		                            	
		                            	<!-- RA Associado -->
		                            	
										<c:if test="${registroAtendimentoHelper.RAAssociado != null}">
			                            <tr> 
		                            		<td height="10"><strong>N&uacute;mero do RA Associado:</strong></td>
		                              		<td> 
												<input type="text" name="numeroRaAssociado" readonly="true"
													style="background-color:#EFEFEF; border:0;" size="9"
													maxlength="9"
													value="${registroAtendimentoHelper.RAAssociado.id}" />
		                              			<strong>Situa&ccedil;&atilde;o do RA Associado:</strong>
												<input type="text" name="situacaoRaAssociado" readonly="true"
													style="background-color:#EFEFEF; border:0;" size="12"
													maxlength="9" 
													value="${registroAtendimentoHelper.descricaoSituacaoRAAssociado}" />
		                                	</td>
			                            </tr>
										</c:if>
										
										<!-- Tipo de Solicitação -->
										
		                            	<tr> 
		                              		<td class="style3"><strong>Tipo de Solicita&ccedil;&atilde;o:</strong></td>
		                              		<td colspan="3">
												<input type="text" name="tipoSolicitacaoId" readonly="true"
													style="background-color:#EFEFEF; border:0;" size="4"
													maxlength="4" 
													value="${registroAtendimentoHelper.registroAtendimento.solicitacaoTipoEspecificacao.solicitacaoTipo.id}" />		                              		
												<input type="text" name="tipoSolicitacaoDescricao" readonly="true"
													style="background-color:#EFEFEF; border:0;" size="45"
													maxlength="50" 
													value="${registroAtendimentoHelper.registroAtendimento.solicitacaoTipoEspecificacao.solicitacaoTipo.descricao}" />
		                                	</td>
		                            	</tr>

										<!-- Especificação -->

		                            	<tr> 
		                              		<td height="10"><strong>Especifica&ccedil;&atilde;o:</strong></td>
		                              		<td colspan="3"> 
												<input type="text" name="especificacaoId" readonly="true"
													style="background-color:#EFEFEF; border:0;" size="4"
													maxlength="4" 
													value="${registroAtendimentoHelper.registroAtendimento.solicitacaoTipoEspecificacao.id}" />
												<input type="text" name="especificacaoDescricao" readonly="true"
													style="background-color:#EFEFEF; border:0;" size="45"
													maxlength="50" 
													value="${registroAtendimentoHelper.registroAtendimento.solicitacaoTipoEspecificacao.descricao}" />
		                                 	</td>
		                            	</tr>
		                            	
		                            	<!-- Meio de Solicitação -->

			                            <tr>
			                              	<td class="style3"><strong>Meio de Solicita&ccedil;&atilde;o:</strong></td>
			                              	<td colspan="3">
												<input type="text" name="meioSolicitacaoDescricao" readonly="true"
													style="background-color:#EFEFEF; border:0;" size="50"
													maxlength="50" 
													value="${registroAtendimentoHelper.registroAtendimento.meioSolicitacao.descricao}" />
											</td>
			                            </tr>

										<!-- Imóvel -->
	
	                              		<tr>
	                                		<td><strong>Matr&iacute;cula do Im&oacute;vel:</strong></td>
	                                		<td colspan="3"> 
												<input type="text" name="matriculaImovel" readonly="true"
													style="background-color:#EFEFEF; border:0;" size="8"
													maxlength="8"
													value="${registroAtendimentoHelper.registroAtendimento.imovel.id}" />
												<input type="text" name="inscricaoImovel" readonly="true"
													style="background-color:#EFEFEF; border:0;" size="21"
													maxlength="21" 
													value="${registroAtendimentoHelper.registroAtendimento.imovel.inscricaoFormatada}" />
											</td>
                              			</tr>

										<!-- Data e Hora -->

			                            <tr> 
			                              	<td width="31%" height="10"><strong>Data e Hora do Atendimento:</strong></td>
			                              	<td colspan="3"> 
												<input type="text" name="dataAtendimento" readonly="true"
													style="background-color:#EFEFEF; border:0;" size="10"
													maxlength="10" 
													value="
													<fmt:formatDate value="${registroAtendimentoHelper.registroAtendimento.registroAtendimento}" pattern="dd/MM/yyyy" />
													" />
												<input type="text" name="horaAtendimento" readonly="true"
													style="background-color:#EFEFEF; border:0;" size="5"
													maxlength="5" 
													value="
													<fmt:formatDate value="${registroAtendimentoHelper.registroAtendimento.registroAtendimento}" pattern="hh:mm" />
													" />
											</td>
			                            </tr>

										<!-- Data Prevista -->
										
			                            <tr> 
			                              	<td class="style3"><strong>Data Prevista:</strong></td>
			                              	<td colspan="3">
												<input type="text" name="dataPrevista" readonly="true"
													style="background-color:#EFEFEF; border:0;" size="10"
													maxlength="10" 
													value="
													<fmt:formatDate value="${registroAtendimentoHelper.registroAtendimento.dataPrevistaAtual}" pattern="dd/MM/yyyy" />
													" />
											</td>
			                            </tr>
			                            
			                            <!-- Data Encerramento -->
			                            
										<tr>
                                    		<td class="style3">
                                    			<strong>Data da Encerramento:</strong>
                                    		</td>

                                    		<td>
												<input type="text" name="dataEncerramento" readonly="true"
													style="background-color:#EFEFEF; border:0;" size="10"
													maxlength="10"
													value="
													<fmt:formatDate value="${registroAtendimentoHelper.registroAtendimento.dataEncerramento}" pattern="dd/MM/yyyy" />
													" />
											</td>
                                  		</tr>

										<!-- Motivo Encerramento -->

										<tr>
                                    		<td class="style3"><strong>Motivo do Encerramento:</strong></td>
                                    		<td>                                    		
												<input type="text" name="idMotivoEncerramento" readonly="true"
													style="background-color:#EFEFEF; border:0;" size="5"
													maxlength="5" 
													value="${registroAtendimentoHelper.registroAtendimento.atendimentoMotivoEncerramento.id}" />
												<input type="text" name="motivoEncerramento" readonly="true"
													style="background-color:#EFEFEF; border:0;" size="45"
													maxlength="50" 
													value="${registroAtendimentoHelper.registroAtendimento.atendimentoMotivoEncerramento.descricao}" />
											</td>

                                  		</tr>

	                              		<tr> 
	                                		<td height="10" colspan="4"> 
	                                		<div align="right"> 
	                                    	<hr>
	                                  		</div>
	                                  		<div align="right"> </div></td>
	                              		</tr>
			                            
			                            <!-- Cliente Solicitante -->
			                            
										<tr> 	
                                    		<td class="style3"><strong>Cliente Solicitante:</strong></td>
                                    		<td colspan="3">
												<input type="text" name="idClienteSolicitante" readonly="true"
													style="background-color:#EFEFEF; border:0;" size="4"
													maxlength="4" 
													value="${registroAtendimentoHelper.solicitante.cliente.id}" />

													<input type="text" name="clienteSolicitante" readonly="true"
														style="background-color:#EFEFEF; border:0;" size="40"
														maxlength="40" 
														value="${registroAtendimentoHelper.solicitante.cliente.nome}" />

											</td>
                                  		</tr>
                                  		
                                  		<!-- Unidade Solicitante -->

										<tr> 	
                                    		<td class="style3">
                                    			<strong>Unidade Solicitante:</strong>
                                    		</td>
                                    		<td colspan="3">
												<input type="text" name="idUnidadeSolicitante" readonly="true"
													style="background-color:#EFEFEF; border:0;" size="4"
													maxlength="4" 
													value="${registroAtendimentoHelper.solicitante.unidadeOrganizacional.id}" />
												<input type="text" name="unidadeSolicitante" readonly="true"
													style="background-color:#EFEFEF; border:0;" size="40"
													maxlength="40"
													value="${registroAtendimentoHelper.solicitante.unidadeOrganizacional.descricao}" />
											</td>
                                  		</tr>

										<!-- Nome do Solicitante -->

										<tr>
                                    		<td class="style3"><strong>Nome do Solicitante:</strong></td>
                                    		<td colspan="3">
												<input type="text" name="nomeSolicitante" readonly="true"
													style="background-color:#EFEFEF; border:0;" size="40"
													maxlength="40" 
													value="${registroAtendimentoHelper.solicitante.solicitante}" />
											</td>
                                  		</tr>

	                              		<tr> 
	                                		<td height="10" colspan="4"> 
	                                		<div align="right"> 
	                                    	<hr>
	                                  		</div>
	                                  		<div align="right"> </div></td>
	                              		</tr>
			                            
			                            <!-- Endereco -->
			                            
		                               	<tr> 
			                              	<td class="style3">
			                              		<strong><span class="style2">Endere&ccedil;o da Ocorr&ecirc;ncia:</span></strong>
			                              	</td>
		                                  
		                                  	<td>
												<textarea name="enderecoOcorrencia" readonly="true"
												style="background-color:#EFEFEF; border:0;" cols="43">
												${registroAtendimentoHelper.enderecoOcorrencia}
												</textarea>
		                                  	</td>
		                            	</tr>
		                            	
		                            	<!-- Ponto de Referência -->
                            	
		                               	<tr>
		                                 	<td class="style3"><strong>Ponto de Refer&ecirc;ncia:</strong></td>
		                                 	
		                                 	<td>
												<input type="text" name="pontoReferencia" readonly="true"
													style="background-color:#EFEFEF; border:0;" size="55"
													maxlength="60" 
													value="${registroAtendimentoHelper.registroAtendimento.pontoReferencia}" />
		                                 	</td>
		                               	</tr>

										<!-- Bairro -->

	                              		<tr> 
	                                		<td class="style3">
	                                			<strong>Bairro:</strong>
	                                		</td>
	                                		<td>
												<input type="text" name="bairroId" readonly="true"
													style="background-color:#EFEFEF; border:0;" size="4"
													maxlength="4" 
													value="${registroAtendimentoHelper.registroAtendimento.bairroArea.bairro.id}" />
				
												<input type="text" name="bairroDescricao" readonly="true"
													style="background-color:#EFEFEF; border:0;" size="40"
													maxlength="40" 
													value="${registroAtendimentoHelper.registroAtendimento.bairroArea.bairro.nome}" />
											</td>
	                              		</tr>
	                              		
	                              		<!-- Area -->
	
	                              		<tr> 
	                                		<td class="style3">
	                                			<strong>&Aacute;rea do Bairro:</strong>
	                                		</td>
	                                		<td>
												<input type="text" name="areaBairroId" readonly="true"
													style="background-color:#EFEFEF; border:0;" size="4"
													maxlength="4" 
													value="${registroAtendimentoHelper.registroAtendimento.bairroArea.id}" />
				
												<input type="text" name="areaBairroDescricao" readonly="true"
													style="background-color:#EFEFEF; border:0;" size="40"
													maxlength="40" 
													value="${registroAtendimentoHelper.registroAtendimento.bairroArea.nome}" />
											</td>
                              			</tr>
                              			
                              			<!-- Local/Setor/Quadra -->
                              			
										<tr> 	
	                                		<td class="style3">
	                                			<strong>Local/Setor/Quadra:</strong>
	                                		</td>
	                                		<td>
												<input type="text" name="localidadeId" readonly="true"
													style="background-color:#EFEFEF; border:0;" size="4"
													maxlength="4" 
													value="${registroAtendimentoHelper.registroAtendimento.localidade.id}" />
												<strong>/</strong>
												<input type="text" name="setorComercialId" readonly="true"
													style="background-color:#EFEFEF; border:0;" size="4"
													maxlength="4" 
													value="${registroAtendimentoHelper.registroAtendimento.setorComercial.id}" />
												<strong>/</strong>
												<input type="text" name="quadraId" readonly="true"
													style="background-color:#EFEFEF; border:0;" size="4"
													maxlength="4"
													value="${registroAtendimentoHelper.registroAtendimento.quadra.id}" />
											</td>
                              			</tr>

										<!-- Divisao de Esgoto -->

										<tr> 	
	                                		<td class="style3">
	                                			<strong>Divis&atilde;o de Esgoto:</strong>
	                                		</td>
	                                		<td colspan="3">
												<input type="text" name="divisaoEsgotoId" readonly="true"
													style="background-color:#EFEFEF; border:0;" size="4"
													maxlength="4"
													value="${registroAtendimentoHelper.registroAtendimento.divisaoEsgoto.id}" />
				
												<input type="text" name="divisaoEsgotoDescricao" readonly="true"
													style="background-color:#EFEFEF; border:0;" size="40"
													maxlength="40" 
													value="${registroAtendimentoHelper.registroAtendimento.divisaoEsgoto.descricao}" />
											</td>
	                              		</tr>

	                              		<tr> 
	                                		<td height="10" colspan="4"> 
	                                		<div align="right"> 
	                                    	<hr>
	                                  		</div>
	                                  		<div align="right"> </div></td>
	                              		</tr>
			                            
			                            <!-- Unidade de Atendimento -->
			                            
			                            <tr> 
			                              	<td class="style3"><strong>Unidade de Atendimento:</strong></td>
			                              	<td colspan="3">
												<input type="text" name="unidadeAtendimentoId" readonly="true"
													style="background-color:#EFEFEF; border:0;" size="4"
													maxlength="4" 
													value="${registroAtendimentoHelper.unidadeAtendimento.id}" />

												<input type="text" name="unidadeAtendimentoDescricao" readonly="true"
													style="background-color:#EFEFEF; border:0;" size="40"
													maxlength="40" 
													value="${registroAtendimentoHelper.unidadeAtendimento.descricao}" />
											</td>
			                            </tr>
			                            
			                            <!-- Unidade Atual -->

			                            <tr> 
			                              	<td class="style3"><strong>Unidade Atual:</strong></td>
			                              	<td colspan="3">
												<input type="text" name="unidadeAtualId" readonly="true"
													style="background-color:#EFEFEF; border:0;" size="4"
													maxlength="4"
													value="${registroAtendimentoHelper.unidadeAtual.id}" />

												<input type="text" name="unidadeAtualDescricao" readonly="true"
													style="background-color:#EFEFEF; border:0;" size="40"
													maxlength="40" 
													value="${registroAtendimentoHelper.unidadeAtual.descricao}" />
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
           		
           		--%>
           		
				<tr> 
                	<td height="10" colspan="2">Para atualizar uma ordem de serviço, informe os dados abaixo:</td>
                 </tr>
				<!-- Dados do Local Ocorrencia -->
				
				    <tr bgcolor="#cbe5fe">

						<td align="center">
						<table width="100%" border="0" bgcolor="#99CCFF">
	
							<tr bgcolor="#99CCFF">
								<td height="18" colspan="2">
								<div id="layerHideDadosGerais" style="display:block">
								<table width="100%" border="0" bgcolor="#99CCFF">
									<tr bgcolor="#99CCFF">
										<td align="center"><span class="style2"> <a
											href="javascript:extendeTabela('DadosGerais',true);" /> <b>Dados
										Gerais da Ordem de Serviço</b> </a> </span></td>
									</tr>
								</table>
								</div>
	
								<div id="layerShowDadosGerais" style="display:none">
	
								<table width="100%" border="0" bgcolor="#99CCFF">
	
									<tr bgcolor="#99CCFF">
										<td align="center"><span class="style2"> <a
											href="javascript:extendeTabela('DadosGerais',false);" /> <b>Dados
										Gerais da Ordem de Serviço</b> </a> </span></td>
									</tr>
	
	
	
									<tr bgcolor="#cbe5fe">
										<td>
										<table border="0" width="100%">
	
											<tr>
												<td height="10" width="30%"><strong>N&uacute;mero do OS:</strong></td>
	
												<td width="69%"><html:text property="numeroOS"
													readonly="true"
													style="background-color:#EFEFEF; border:0;" size="9"
													maxlength="9" /> &nbsp;&nbsp;&nbsp;&nbsp; <strong>Situa&ccedil;&atilde;o
												do OS:</strong> &nbsp;&nbsp; <html:text
													property="situacaoOS" readonly="true"
													style="background-color:#EFEFEF; border:0;" size="25"
													maxlength="35" /></td>
											</tr>
											<c:if
												test="${AtualizarOrdemServicoActionForm.numeroRA != null}">
												<tr>
													<td height="10" width="30%"><strong>N&uacute;mero do RA:</strong></td>
	
													<td width="69%"><html:text property="numeroRA"
														readonly="true"
														style="background-color:#EFEFEF; border:0;" size="9"
														maxlength="9" /> &nbsp;&nbsp;&nbsp;&nbsp; <strong>Situa&ccedil;&atilde;o
													do RA:</strong> &nbsp;&nbsp; <html:text
														property="situacaoRA" readonly="true"
														style="background-color:#EFEFEF; border:0;" size="25"
														maxlength="25" /></td>
												</tr>
											</c:if>
	
											<c:if
												test="${AtualizarOrdemServicoActionForm.numeroDocumentoCobranca != null}">
												<tr>
													<td width="30%"><strong>N&uacute;mero do Documento de
													Cobran&ccedil;a:</strong></td>
													<td width="69%"><html:text
														property="numeroDocumentoCobranca" readonly="true"
														style="background-color:#EFEFEF; border:0;" size="9"
														maxlength="9" /></td>
												</tr>
											</c:if>
	
											<tr>
												<td width="30%"><strong>Data da Gera&ccedil;&atilde;o:</strong></td>
												<td width="69%"><html:text property="dataGeracao"
													readonly="true"
													style="background-color:#EFEFEF; border:0;" size="10"
													maxlength="10" /></td>
											</tr>
	
											<tr>
												<td height="10" width="30%"><strong>Tipo do Servi&ccedil;o:</strong></td>
												<td width="69%"><html:text property="tipoServicoOSId"
													readonly="true"
													style="background-color:#EFEFEF; border:0;" size="4"
													maxlength="4" /> <html:text
													property="tipoServicoOSDescricao" readonly="true"
													style="background-color:#EFEFEF; border:0;" size="40"
													maxlength="40" /></td>
											</tr>
											<tr>
												<td height="10" width="30"><strong>Observa&ccedil;&atilde;o:</strong></td>
												<td width="69%"><strong> <html:textarea
													property="observacaoDadosGerais" readonly="true"
													style="background-color:#EFEFEF; border:0;" cols="40" />
												</strong></td>
											</tr>
	
											<tr>
												<td height="10" width="30%"><strong>Valor do Servi&ccedil;o
												Original:</strong></td>
	
												<td width="69%"><html:text property="valorServicoOriginal"
													readonly="true"
													style="background-color:#EFEFEF; border:0; text-align:right;"
													size="9" /> &nbsp;&nbsp;&nbsp;&nbsp; <strong>Valor do
												Servi&ccedil;o Atual:</strong> &nbsp;&nbsp; <html:text
													property="valorServicoAtual" readonly="true"
													style="background-color:#EFEFEF; border:0; text-align:right;"
													size="9" /></td>
											</tr>
	
											<tr>
												<td width="30%"><strong>Prioridade Original:</strong></td>
												<td width="69%"><html:text property="prioridadeOriginal"
													readonly="true"
													style="background-color:#EFEFEF; border:0;" size="48"
													maxlength="48" /></td>
											</tr>
	
											<tr>
												<td width="30%"><strong>Prioridade Atual:</strong></td>
												<td width="69%"><html:text property="prioridadeAtual"
													readonly="true"
													style="background-color:#EFEFEF; border:0;" size="48"
													maxlength="48" /></td>
											</tr>
	
	
											<tr>
												<td width="30%"><strong>Unidade da Gera&ccedil;&atilde;o da
												OS:</strong></td>
												<td width="69%"><html:text property="unidadeGeracaoId"
													readonly="true"
													style="background-color:#EFEFEF; border:0;" size="4"
													maxlength="4" /> <html:text
													property="unidadeGeracaoDescricao" readonly="true"
													style="background-color:#EFEFEF; border:0;" size="40"
													maxlength="40" /></td>
											</tr>
	
											<tr>
												<td width="30%"><strong>Usu&aacute;rio da
												Gera&ccedil;&atilde;o da OS:</strong></td>
												<td width="69%"><html:text property="usuarioGeracaoId"
													readonly="true"
													style="background-color:#EFEFEF; border:0;" size="4"
													maxlength="4" /> <html:text property="usuarioGeracaoNome"
													readonly="true"
													style="background-color:#EFEFEF; border:0;" size="40"
													maxlength="40" /></td>
											</tr>
	
											<tr>
												<td width="30%"><strong>Data da &Uacute;ltima
												Emiss&atilde;o:</strong></td>
												<td width="69%"><html:text property="dataUltimaEmissao"
													readonly="true"
													style="background-color:#EFEFEF; border:0;" size="9" /></td>
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
				
              		<tr bgcolor="#99CCFF"> 
                		<td height="18" colspan="2"><div align="center"><span class="style2"><b>Dados da Ordem de Servi&ccedil;o</b></span></div></td>
              		</tr>
              		<tr bgcolor="#cbe5fe"> 
                		<td> 
                			<table border="0" width="100%">
                			
                				<!-- Tipo de Serviço -->
                				
                				<c:choose>
	                				<c:when test="${empty colecaoServicosTipo}">
		                    			<tr> 
		                      				<td><span class="style3"><strong><span class="style2">Tipo de Servi&ccedil;o:<strong><strong><font color="#FF0000"></font><span class="style3"><strong><span class="style2"><strong><font color="#FF0000">*</font></strong></span></strong></span></strong></strong></span></strong></span></td>
		                      				<td colspan="3">
		                      					<strong><b><span class="style2"> 
		                        				<html:text property="idServicoTipo" 
														   size="7" 
														   maxlength="4" 
														   onkeyup="validaEnterComMensagem(event, 'exibirAtualizarOrdemServicoAction.do', 'idServicoTipo', 'Serviço Tipo');limparCamposServicoTipo();" />
		                        				<img src="imagens/pesquisa.gif" 
		                        					 width="23" 
		                        					 height="21"
                    					 			 style="cursor: hand;"
                 					    			 onclick="chamarPopup('exibirPesquisarTipoServicoAction.do?limpar=S', 'tipoServico', null, null, 300, 620, '','');" 
                 					    			 alt="Pesquisar">
                 					    		<logic:present name="idServicoTipoNaoEncontrado">
													<logic:equal name="idServicoTipoNaoEncontrado" value="exception">
													<html:text property="descricaoServicoTipo" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000" size="50" maxlength="50" />
													</logic:equal>
													<logic:notEqual name="idServicoTipoNaoEncontrado" value="exception">
														<html:text property="descricaoServicoTipo" size="50" maxlength="50" readonly="true"
															style="background-color:#EFEFEF; border:0; color: #000000" />
													</logic:notEqual>
												</logic:present> 
												<logic:notPresent name="idServicoTipoNaoEncontrado">
													<logic:empty name="AtualizarOrdemServicoActionForm" property="idServicoTipo">
														<html:text property="descricaoServicoTipo" value="" size="50" maxlength="50" readonly="true"
															style="background-color:#EFEFEF; border:0; color: #ff0000" />
													</logic:empty>
													<logic:notEmpty name="AtualizarOrdemServicoActionForm" property="idServicoTipo">
														<html:text property="descricaoServicoTipo" size="50" maxlength="50" readonly="true"
															style="background-color:#EFEFEF; border:0; color: #000000" />
													</logic:notEmpty>
												</logic:notPresent> 
		                        				
		                        				<a href="javascript:limpar('servicoTipo');">
		                        					<img src="imagens/limparcampo.gif" 
		                        						 width="23" 
		                        						 height="21"
		                        						 border="0"
	                  			 						 title="Apagar"> 
		                        				</a>
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
                    			
                    			<c:if test="${servicoTipo.servicoTipoReferencia != null}">

	                    			<c:choose>
	                    			
	                    				<c:when test="${servicoTipo.servicoTipoReferencia.indicadorExistenciaOsReferencia == '1'}">
	
			                    			<!-- Ordem de Serviço de Referência -->
	                    			
			                    			<tr> 
			                      				<td width="21%"><strong> <span class="style3"><strong>Ordem de Servi&ccedil;o de Refer&ecirc;ncia:<strong><strong><font color="#FF0000"> </font></strong></span></td>
			                      				<td colspan="3">
			                      					<strong> <b><span class="style2"> 
			                        				<html:text property="idOrdemServicoReferencia" 
			                        						   size="10" 
			                        						   maxlength="9" 
			                        						   onkeyup="validaEnterComMensagem(event, 'exibirAtualizarOrdemServicoAction.do', 'idOrdemServicoReferencia', 'Ordem de Serviço de Referência');" />
			                        				<img src="imagens/pesquisa.gif" 
			                        					 width="23" 
			                        					 height="21"
			                        					 style="cursor: hand;"
                       					    			 onclick="chamarPopup('exibirPesquisarOrdemServicoAction.do', 'ordemServicoReferencia', null, null, 300, 620, '','');document.forms[0].descricaoOrdemServicoReferencia.value='';" 
                       					    			 alt="Pesquisar">
                       					    		<logic:present name="idOrdemServicoReferenciaNaoEncontrado">
													 <logic:equal name="idOrdemServicoReferenciaNaoEncontrado" value="exception">
													    <html:text property="descricaoOrdemServicoReferencia" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000" size="50" maxlength="50" />
													 </logic:equal>
													<logic:notEqual name="idOrdemServicoReferenciaNaoEncontrado" value="exception">
														<html:text property="idOrdemServicoReferenciaNaoEncontrado" size="50" maxlength="50" readonly="true"
															style="background-color:#EFEFEF; border:0; color: #000000" />
													</logic:notEqual>
												   </logic:present> 
												   <logic:notPresent name="idOrdemServicoReferenciaNaoEncontrado">
													<logic:empty name="AtualizarOrdemServicoActionForm" property="idOrdemServicoReferencia">
														<html:text property="descricaoOrdemServicoReferencia" value="" size="50" maxlength="50" readonly="true"
															style="background-color:#EFEFEF; border:0; color: #ff0000" />
													</logic:empty>
													<logic:notEmpty name="AtualizarOrdemServicoActionForm" property="idOrdemServicoReferencia">
														<html:text property="descricaoOrdemServicoReferencia" size="50" maxlength="50" readonly="true"
															style="background-color:#EFEFEF; border:0; color: #000000" />
													</logic:notEmpty>
												  </logic:notPresent> 	  
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
							                        						   onkeyup="validaEnterComMensagem(event, 'exibirAtualizarOrdemServicoAction.do', 'idServicoTipoReferencia', 'Serviço Tipo de Referência');document.forms[0].descricaoServicoTipoReferencia.value='';" />
							                        				<img src="imagens/pesquisa.gif" 
							                        					 width="23" 
							                        					 height="21"
																		 style="cursor: hand;"
                       					    			 				 onclick="chamarPopup('exibirPesquisarTipoServicoAction.do', 'servicoTipoReferencia', null, null, 300, 620, '','');" 
                       					    			 				 alt="Pesquisar">
                       					    			 			<logic:present name="idServicoTipoReferenciaNaoEncontrado">
																		 <logic:equal name="idServicoTipoReferenciaNaoEncontrado" value="exception">
																		    <html:text property="descricaoServicoTipoReferencia" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000" size="50" maxlength="50" />
																		 </logic:equal>
																		<logic:notEqual name="idServicoTipoReferenciaNaoEncontrado" value="exception">
																			<html:text property="descricaoServicoTipoReferencia" size="50" maxlength="50" readonly="true"
																				style="background-color:#EFEFEF; border:0; color: #000000" />
																		</logic:notEqual>
																	   </logic:present> 
																	   <logic:notPresent name="idServicoTipoReferenciaNaoEncontrado">
																		<logic:empty name="AtualizarOrdemServicoActionForm" property="idServicoTipoReferencia">
																			<html:text property="descricaoServicoTipoReferencia" value="" size="50" maxlength="50" readonly="true"
																				style="background-color:#EFEFEF; border:0; color: #ff0000" />
																		</logic:empty>
																		<logic:notEmpty name="AtualizarOrdemServicoActionForm" property="idServicoTipoReferencia">
																			<html:text property="descricaoServicoTipoReferencia" size="50" maxlength="50" readonly="true"
																				style="background-color:#EFEFEF; border:0; color: #000000" />
																		</logic:notEmpty>
																	  </logic:notPresent> 
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
								
                    			<!-- Observação -->
                    			
                    			<tr> 
                      				<td class="style3"><strong>Observa&ccedil;&atilde;o:</strong></td>
                      				<td colspan="3"><strong><b><span class="style2"> <strong> 
                        				<html:textarea property="observacao" cols="50" rows="4" onkeyup=" validarTamanhoMaximoTextArea(this,200);"/>
                        				</strong> </span></b></strong>
                        			</td>
                    			</tr>
                    			
                    			<tr> 

	                    			<!-- Valor do Seviço Original -->

                      				<td class="style3"><strong>
                      				<!-- 
                      				Valor do Servi&ccedil;o Original:
                      				-->
                      				<strong><span class="style2"><strong><strong><span class="style3"><strong><span class="style2"><strong><font color="#FF0000"></font></strong></span></strong></span></strong></strong></span></strong></strong></td>
                      				<td width="26%">
                      					<strong><b><span class="style2"> 
                        				<span class="style3"><strong>
                        				<!-- 
                        				<html:text property="valorServicoOriginal" readonly="true" style="background-color:#EFEFEF; border:0" size="10" maxlength="8" />
                        				-->
                        				<html:hidden property="valorServicoOriginal" />
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
                    			
                      				<td><b>Prioridade do Tipo Servi&ccedil;o Original:</b><span class="style3"><strong><span class="style2"><strong><strong><span class="style3"><strong><span class="style2"></span></strong></span></strong></strong></span></strong></span></td>
                      				<td>
                      				
                      					<html:hidden property="idPrioridadeServicoOriginal" />

                      					<strong><b><span class="style2"> 
				                        <b><span class="style3"><strong>
				                        <html:text property="descricaoPrioridadeServicoOriginal" readonly="true" style="background-color:#EFEFEF; border:0" size="20" maxlength="20" />
				                        </strong></span></b>
				                        </span></b></strong>
				                    </td>
				                    
				                    <!-- Prioridade do Serviço Atual -->
				                    
                      				<td class="style3"><b>Prioridade do Servi&ccedil;o Atual:</b><strong><font color="#FF0000">*</font></strong></td>
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
              		
                    <tr> 
                      <td>                     
                    	<table width="100%">
                    		<tr>
                      		<td align="left"> 
	                      		<input name="ButtonVoltar" type="button"
									class="bottonRightCol" value="Voltar"
									onclick="javascript:redirecionarSubmit('${retornoTela}')">
                      			<input name="Submit223223" type="button" class="bottonRightCol" value="Desfazer" onClick="javascript:limparForm();"> 
                      			<logic:notPresent name="ehPopup">
                        		  <input name="Submit22322" type="button" class="bottonRightCol"  value="Cancelar" onClick="javascript:window.location.href='/gsan/telaPrincipal.do'"> 
                        		</logic:notPresent>
                      		</td>
                      		<td align="right"> 
                      		<div align="right">
                      			<input name="Submit223222" type="button" class="bottonRightCol"  onClick="javascript:validarForm();" value="Atualizar"> 
                        	</div>
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

</table>
<!-- Fim do Corpo -->
	<logic:notPresent name="ehPopup">
		<%@ include file="/jsp/util/rodape.jsp"%>
	</logic:notPresent>
</html:form>
</body>
</html:html>