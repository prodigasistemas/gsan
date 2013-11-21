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

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="GerarOrdemServicoActionForm" />

<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js"></script>

<script language="JavaScript">

	function pesquisarTipoServico( event ){
	  var form = document.forms[0];
	  
	  form.forward.value = 'exibirGerarOrdemServico';
	  
  	  validaEnterComMensagem(event, 'exibirGerarOrdemServicoAction.do?idRegistroAtendimento='+form.idRegistroAtendimento.value, 'idServicoTipo', 'Serviço Tipo');
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

		if (tipoConsulta == 'ordemServico') {

		    form.idOrdemServicoReferencia.value = codigoRegistro;
		    form.descricaoOrdemServicoReferencia.value = descricaoRegistro;
		    form.descricaoOrdemServicoReferencia.style.color = "#000000";
	    }
	    
		if (tipoConsulta == 'servicoTipo') {
		    form.idServicoTipo.value = codigoRegistro;
		    form.descricaoServicoTipo.value = descricaoRegistro;
		    form.descricaoServicoTipo.style.color = "#000000";
		   // form.action = "/gsan/exibirGerarOrdemServicoAction.do";
		     form.action = "/gsan/exibirGerarOrdemServicoAction.do?forward=exibirGerarOrdemServico";
    		form.submit();  
	    }	    

	}
	
	function validarForm() {
		var form = document.forms[0];
		if(validateGerarOrdemServicoActionForm(form)){
		   if(form.idOrdemServicoReferencia != null && form.idOrdemServicoReferencia.value == ''){
		    if(form.indExistenciaOSRef.value == '1'){
		     alert("Informe Ordem de Serviço de Referência.");
		    }else{
		      if(form.idServicoTipoReferencia.value == ''){
		       alert("Informe Ordem de Serviço de Referência ou Tipo de Serviço de Referência.");
		      }else{
		       //form.action = "/gsan/gerarOrdemServicoAction.do";
    		   //form.submit(); 
    		   botaoAvancarTelaEspera('/gsan/gerarOrdemServicoAction.do');
    		  }
		    }
		   }else{
		    if(form.idOrdemServicoReferencia != null && form.idOrdemServicoReferencia.value !=''){
		       if(form.indExistenciaOSRef.value != '1' && form.idServicoTipoReferencia.value != ''){
		       alert("Informe Ordem de Serviço de Referência ou Tipo de Serviço de Referência.");
		       }else{
		        //form.action = "/gsan/gerarOrdemServicoAction.do";
    		    //form.submit();
    		    botaoAvancarTelaEspera('/gsan/gerarOrdemServicoAction.do');
		       }
		    }else{ 
			 //form.action = "/gsan/gerarOrdemServicoAction.do";
    		 //form.submit();
    		 botaoAvancarTelaEspera('/gsan/gerarOrdemServicoAction.do');
    	  }
		}
	  }
	}
	
	function reload() {
		var form = document.GerarOrdemServicoActionForm;
		form.action = "/gsan/exibirGerarOrdemServicoAction.do?forward=exibirGerarOrdemServico";
		form.submit();
	}  
	
	/* Limpa Form */	 
	function limparForm() {
		var form = document.forms[0];
		if (form.idServicoTipo != undefined) {
    		if(form.idServicoTipo.selectedIndex != undefined){
    			form.idServicoTipo.selectedIndex = 0;
    		}else{
    			form.idServicoTipo.value = "";
    		}
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

	    form.action = 'exibirGerarOrdemServicoAction.do?forward=exibirGerarOrdemServico';
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
		    form.valorServicoOriginal.value = "";
		    form.idPrioridadeServicoOriginal.value = "";
		    form.descricaoPrioridadeServicoOriginal.value = "";

		} else if (tipo == 'servicoTipoReferencia') {

	    	form.idServicoTipoReferencia.value = "";
		    form.descricaoServicoTipoReferencia.value = "";
		}
	}  
	
	function consultarOS() {
		var form = document.forms[0];
		abrirPopup('exibirConsultarRegistroAtendimentoOSAction.do?numeroRA='+form.idRegistroAtendimento.value, 550, 735);
	}
	
	function voltar(action){
		var form = document.forms[0];
			
	    form.action = action;
		form.submit();
	}
</script>
</head>

<body leftmargin="5" topmargin="5" >
<div id="formDiv">
<html:form action="/exibirGerarOrdemServicoAction" method="post">


	<html:hidden property="idRegistroAtendimento" />
	<html:hidden property="forward" />		
	
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
						<td class="parabg">Gerar Ordem de Serviço</td>
						<td width="11">
							<img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif" />
						</td>
					</tr>
				</table>
			
				<table width="100%">
					<tr>
						<td width="90%" colspan="2">&nbsp;</td>
						<logic:present scope="application" name="urlHelp">
						<td align="right"><a href="javascript:abrirPopupHelp('${applicationScope.urlHelp}atendimentoOrdemServicoGerar', 500, 700);"><span style="font-weight: bold"><font color="#3165CE">Ajuda</font></span></a></td>									
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

										<!-- Numero do RA -->

		                            	<tr>
		                              		<td height="10"><strong>N&uacute;mero do RA:</strong></td>
		                              		<td width="10%"> 
												<input type="text" 
													name="numeroRA" 
													readonly="true"
													style="background-color:#EFEFEF; border:0;" 
													size="9"
													maxlength="9" 
													value="${registroAtendimentoHelper.registroAtendimento.id}" />

		                              			<strong>Situa&ccedil;&atilde;o do RA:</strong>

												<input type="text" 
													name=situacaoRA 
													readonly="true"
													style="background-color:#EFEFEF; border:0;" 
													size="12"
													maxlength="9" 
													value="${registroAtendimentoHelper.descricaoSituacaoRA}" />

		                                  	</td>
		                                  	
		                            	</tr>
		                            	
		                            	<!-- RA Associado -->
		                            	
										<c:if test="${registroAtendimentoHelper.RAAssociado != null}">
			                            <tr> 
		                            		<td height="10"><strong>N&uacute;mero do RA Associado:</strong></td>
		                              		<td> 
												
												<input type="text" 
													name="numeroRaAssociado" 
													readonly="true"
													style="background-color:#EFEFEF; border:0;" 
													size="9"
													maxlength="9"
													value="${registroAtendimentoHelper.RAAssociado.id}" />
		                              			
		                              			<strong>Situa&ccedil;&atilde;o do RA Associado:</strong>
												
												<input type="text" 
													name="situacaoRaAssociado" 
													readonly="true"
													style="background-color:#EFEFEF; border:0;" 
													size="12"
													maxlength="9" 
													value="${registroAtendimentoHelper.descricaoSituacaoRAAssociado}" />
		                                	</td>
			                            </tr>
										</c:if>
										
										<!-- Tipo de Solicitação -->
										
		                            	<tr> 
		                              		<td class="style3">
		                              			<strong>Tipo de Solicita&ccedil;&atilde;o:</strong>
		                              		</td>
		                              		
		                              		<td colspan="3">
												
												<input type="text" 
													name="tipoSolicitacaoId" 
													readonly="true"
													style="background-color:#EFEFEF; border:0;" 
													size="4"
													maxlength="4" 
													value="${registroAtendimentoHelper.registroAtendimento.solicitacaoTipoEspecificacao.solicitacaoTipo.id}" />		                              		
												
												<input type="text" 
													name="tipoSolicitacaoDescricao" 
													readonly="true"
													style="background-color:#EFEFEF; border:0;" 
													size="45"
													maxlength="50" 
													value="${registroAtendimentoHelper.registroAtendimento.solicitacaoTipoEspecificacao.solicitacaoTipo.descricao}" />
		                                	</td>
		                            	</tr>

										<!-- Especificação -->

		                            	<tr> 
		                              		<td height="10"><strong>Especifica&ccedil;&atilde;o:</strong></td>
		                              		<td colspan="3"> 
												
												<input type="text" 
													name="especificacaoId" 
													readonly="true"
													style="background-color:#EFEFEF; border:0;" 
													size="4"
													maxlength="4" 
													value="${registroAtendimentoHelper.registroAtendimento.solicitacaoTipoEspecificacao.id}" />
												
												<input type="text" 
													name="especificacaoDescricao" 
													readonly="true"
													style="background-color:#EFEFEF; border:0;" 
													size="45"
													maxlength="50" 
													value="${registroAtendimentoHelper.registroAtendimento.solicitacaoTipoEspecificacao.descricao}" />
		                                 	</td>
		                            	</tr>
		                            	
		                            	<!-- Meio de Solicitação -->

			                            <tr>
			                              	<td class="style3"><strong>Meio de Solicita&ccedil;&atilde;o:</strong></td>
			                              	<td colspan="3">
												<input type="text" 
													name="meioSolicitacaoDescricao" 
													readonly="true"
													style="background-color:#EFEFEF; border:0;" 
													size="50"
													maxlength="50" 
													value="${registroAtendimentoHelper.registroAtendimento.meioSolicitacao.descricao}" />
											</td>
			                            </tr>

										<!-- Imóvel -->
	
	                              		<tr>
	                                		<td><strong>Matr&iacute;cula do Im&oacute;vel:</strong></td>
	                                		<td colspan="3"> 
												
												<input type="text" 
													name="matriculaImovel" 
													readonly="true"
													style="background-color:#EFEFEF; border:0;" 
													size="8"
													maxlength="8"
													value="${registroAtendimentoHelper.registroAtendimento.imovel.id}" />
												
												<input type="text" 
													name="inscricaoImovel" 
													readonly="true"
													style="background-color:#EFEFEF; border:0;" 
													size="21"
													maxlength="21" 
													value="${registroAtendimentoHelper.registroAtendimento.imovel.inscricaoFormatada}" />
											</td>
                              			</tr>

										<!-- Data e Hora -->

			                            <tr> 
			                              	<td width="31%" height="10"><strong>Data e Hora do Atendimento:</strong></td>
			                              	<td colspan="3"> 
												
												<input type="text" 
													name="dataAtendimento" 
													readonly="true"
													style="background-color:#EFEFEF; border:0;" 
													size="10"
													maxlength="10" 
													value="
													<fmt:formatDate value="${registroAtendimentoHelper.registroAtendimento.registroAtendimento}" pattern="dd/MM/yyyy" />
													" />
												
												<input type="text" 
													name="horaAtendimento" 
													readonly="true"
													style="background-color:#EFEFEF; border:0;" 
													size="5"
													maxlength="5" 
													value="
													<fmt:formatDate value="${registroAtendimentoHelper.registroAtendimento.registroAtendimento}" pattern="k:mm" />
													" />
											</td>
			                            </tr>

										<!-- Data Prevista -->
										
			                            <tr> 
			                              	<td class="style3"><strong>Data Prevista:</strong></td>
			                              	<td colspan="3">
												
												<input type="text" 
													name="dataPrevista" 
													readonly="true"
													style="background-color:#EFEFEF; border:0;" 
													size="10"
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
												<input type="text" 
													name="dataEncerramento" 
													readonly="true"
													style="background-color:#EFEFEF; border:0;" 
													size="10"
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
												
												<input type="text" 
													name="idMotivoEncerramento" 
													readonly="true"
													style="background-color:#EFEFEF; border:0;" 
													size="5"
													maxlength="5" 
													value="${registroAtendimentoHelper.registroAtendimento.atendimentoMotivoEncerramento.id}" />
												
												<input type="text" 
													name="motivoEncerramento" 
													readonly="true"
													style="background-color:#EFEFEF; border:0;" 
													size="45"
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
												
												<input type="text" 
													name="idClienteSolicitante" 
													readonly="true"
													style="background-color:#EFEFEF; border:0;" 
													size="4"
													maxlength="4" 
													value="${registroAtendimentoHelper.solicitante.cliente.id}" />

												<input type="text"
													name="clienteSolicitante"
													readonly="true"
													style="background-color:#EFEFEF; border:0;" 
													size="40"
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

												<input type="text" 
													name="idUnidadeSolicitante" 
													readonly="true"
													style="background-color:#EFEFEF; border:0;" 
													size="4"
													maxlength="4" 
													value="${registroAtendimentoHelper.solicitante.unidadeOrganizacional.id}" />

												<input type="text" 
													name="unidadeSolicitante" 
													readonly="true"
													style="background-color:#EFEFEF; border:0;" 
													size="40"
													maxlength="40"
													value="${registroAtendimentoHelper.solicitante.unidadeOrganizacional.descricao}" />
											</td>
                                  		</tr>

										<!-- Nome do Solicitante -->

										<tr>
                                    		<td class="style3"><strong>Nome do Solicitante:</strong></td>
                                    		<td colspan="3">
												<input type="text" 
													name="nomeSolicitante" 
													readonly="true"
													style="background-color:#EFEFEF; border:0;" 
													size="40"
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
												<textarea name="enderecoOcorrencia" 
													readonly="true"
													style="background-color:#EFEFEF; border:0;" 
													cols="43">
												${registroAtendimentoHelper.enderecoOcorrencia}
												</textarea>
		                                  	</td>
		                            	</tr>
		                            	
		                            	<!-- Ponto de Referência -->
                            	
		                               	<tr>
		                                 	<td class="style3"><strong>Ponto de Refer&ecirc;ncia:</strong></td>
		                                 	
		                                 	<td>
												<input type="text" 
													name="pontoReferencia" 
													readonly="true"
													style="background-color:#EFEFEF; border:0;" 
													size="55"
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
												<input type="text" 
													name="bairroId" 
													readonly="true"
													style="background-color:#EFEFEF; border:0;" 
													size="4"
													maxlength="4" 
													value="${registroAtendimentoHelper.registroAtendimento.bairroArea.bairro.id}" />
				
												<input type="text" 
													name="bairroDescricao" 
													readonly="true"
													style="background-color:#EFEFEF; border:0;" 
													size="40"
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
												<input type="text" 
													name="areaBairroId" 
													readonly="true"
													style="background-color:#EFEFEF; border:0;" 
													size="4"
													maxlength="4" 
													value="${registroAtendimentoHelper.registroAtendimento.bairroArea.id}" />
				
												<input type="text" 
													name="areaBairroDescricao" 
													readonly="true"
													style="background-color:#EFEFEF; border:0;" 
													size="40"
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
												<input type="text" 
													name="localidadeId" 
													readonly="true"
													style="background-color:#EFEFEF; border:0;" 
													size="4"
													maxlength="4" 
													value="${registroAtendimentoHelper.registroAtendimento.localidade.id}" />
												<strong>/</strong>
												
												<input type="text" 
													name="setorComercialId" 
													readonly="true"
													style="background-color:#EFEFEF; border:0;" 
													size="4"
													maxlength="4" 
													value="${registroAtendimentoHelper.registroAtendimento.setorComercial.id}" />
												<strong>/</strong>
												
												<input type="text" 
													name="quadraId" 
													readonly="true"
													style="background-color:#EFEFEF; border:0;" 
													size="4"
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
												<input type="text" 
													name="divisaoEsgotoId" 
													readonly="true"
													style="background-color:#EFEFEF; border:0;" 
													size="4"
													maxlength="4"
													value="${registroAtendimentoHelper.registroAtendimento.divisaoEsgoto.id}" />
				
												<input type="text" 
													name="divisaoEsgotoDescricao" 
													readonly="true"
													style="background-color:#EFEFEF; border:0;" 
													size="40"
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
												<input type="text" 
													name="unidadeAtendimentoId" 
													readonly="true"
													style="background-color:#EFEFEF; border:0;" 
													size="4"
													maxlength="4" 
													value="${registroAtendimentoHelper.unidadeAtendimento.id}" />

												<input type="text" 
													name="unidadeAtendimentoDescricao" 
													readonly="true"
													style="background-color:#EFEFEF; border:0;" 
													size="40"
													maxlength="40" 
													value="${registroAtendimentoHelper.unidadeAtendimento.descricao}" />
											</td>
			                            </tr>
			                            
			                            <!-- Unidade Atual -->

			                            <tr> 
			                              	<td class="style3"><strong>Unidade Atual:</strong></td>
			                              	<td colspan="3">
												<input type="text" 
													name="unidadeAtualId" 
													readonly="true"
													style="background-color:#EFEFEF; border:0;" 
													size="4"
													maxlength="4"
													value="${registroAtendimentoHelper.unidadeAtual.id}" />

												<input type="text" 
													name="unidadeAtualDescricao" 
													readonly="true"
													style="background-color:#EFEFEF; border:0;" 
													size="40"
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
				<tr> 
                	<td height="10" colspan="2">Para gerar uma ordem de serviço, informe os dados abaixo:</td>
                 </tr>
					
					<!-- Dados do Local Ocorrencia -->
              		<tr bgcolor="#99CCFF"> 
                		<td height="18" colspan="2">
                			<div align="center">
                				<span class="style2"><b>Dados da Ordem de Servi&ccedil;o</b></span>
                			</div>
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

		                        				<html:text property="idServicoTipo" 
														   size="7" 
														   maxlength="4" 
														   onkeyup="somente_numero(this);pesquisarTipoServico( event );" />

		                        				<a href="javascript:chamarPopup('exibirPesquisarTipoServicoAction.do', 'servicoTipo', null, null, 300, 620, '','');">

													<img width="23" 
														height="21" 
														border="0"
														src="<bean:message key="caminho.imagens"/>pesquisa.gif"
														title="Pesquisar Tipo de Serviço" /></a> 

												<logic:present name="idServicoTipoEncontrada" scope="request">
													
													<html:text property="descricaoServicoTipo" 
														size="42"
														maxlength="30" 
														readonly="true"
														style="background-color:#EFEFEF; border:0; color: #000000" />
												</logic:present> 
					
												<logic:notPresent name="idServicoTipoEncontrada" scope="request">
													
													<html:text property="descricaoServicoTipo" 
														size="42"
														maxlength="30" 
														readonly="true"
														style="background-color:#EFEFEF; border:0; color: red" />
														
												</logic:notPresent>


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
		                      					<strong>Tipo de Servi&ccedil;o:<font color="#FF0000">*</font></strong>
	                      					</td>

		                      				<td colspan="3">
		                      				<strong>
			                     				
			             						<html:select property="idServicoTipo" onchange="javascript:reload();">
													<html:option value="-1">&nbsp;</html:option>
													<html:options collection="colecaoServicosTipo" 
														labelProperty="descricao" 
														property="id" />
												</html:select> 
		                      				
		                      					</strong>
		                      				</td>
		                    			</tr>
									</c:otherwise>
								</c:choose>
								
								
							    <c:if test="${servicoTipo != null}">
									
									<c:if test="${servicoTipoReferencia != null}">
									 
									 <html:hidden property="indExistenciaOSRef" value="${servicoTipo.servicoTipoReferencia.indicadorExistenciaOsReferencia}"/>
		                    			<c:choose>
		                    			
		                    				<c:when test="${servicoTipo.servicoTipoReferencia.indicadorExistenciaOsReferencia == '1'}">
		
				                    			<!-- Ordem de Serviço de Referência -->
		                    			
				                    			<tr> 
				                      				<td width="21%">
				                      					<strong>Ordem de Servi&ccedil;o de Refer&ecirc;ncia:<font color="#FF0000">*</font></strong>
				                      				</td>
				                      				
				                      				<td colspan="3">
				                        				<html:text property="idOrdemServicoReferencia" 
				                        						   size="7" 
				                        						   maxlength="9" 
				                        						   onkeyup="validaEnterComMensagem(event, 'exibirGerarOrdemServicoAction.do', 'idOrdemServicoReferencia', 'Ordem de Serviço de Referência');" />
				                        				
				                        				<a href="javascript:chamarPopup('exibirPesquisarOrdemServicoAction.do', 'ordemServicoReferencia', null, null, 300, 620, '','');">
		
															<img width="23" 
																height="21" 
																border="0"
																src="<bean:message key="caminho.imagens"/>pesquisa.gif"
																title="Pesquisar Ordem de Serviço Referência" /></a> 
				                        				
				                        				
														<logic:present name="osReferenciaEncontrada" scope="request">
															
															<html:text property="descricaoOrdemServicoReferencia" 
																size="50"
																maxlength="50" 
																readonly="true"
																style="background-color:#EFEFEF; border:0; color: #000000" />
														</logic:present> 
							
														<logic:notPresent name="osReferenciaEncontrada" scope="request">
															
															<html:text property="descricaoOrdemServicoReferencia" 
																size="50"
																maxlength="50" 
																readonly="true"
																style="background-color:#EFEFEF; border:0; color: red" />
																
														</logic:notPresent>
				                        				
				                        				
				                        				<a href="javascript:limpar('ordemServicoReferencia');">
				                        					<img src="imagens/limparcampo.gif" 
				                        						 width="23" 
				                        						 height="21"
				                        						 border="0"
			                  			 						 title="Apagar"></a>
				                        			</td>
				                    			</tr>
		
											</c:when>
											
											<c:otherwise>
		                                          <tr> 
				                      				
				                      				<td width="21%">
				                      					<span class="style3">
				                      					<strong>Ordem de Servi&ccedil;o de Refer&ecirc;ncia:</strong>
				                      					</span>
				                      				</td>
				                      				
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
						                      					<strong><b><span class="style3">
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
						                        				</span></b></strong>
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
								                      					
								                      					<strong><b><span class="style3">
								                        				
								                        				<html:text property="idServicoTipoReferencia" 
								                        						   size="7" 
								                        						   maxlength="4" 
								                        						   onkeyup="validaEnterComMensagem(event, 'exibirGerarOrdemServicoAction.do', 'idServicoTipoReferencia', 'Serviço Tipo de Referência');" />
								                        				
																		<a href="javascript:chamarPopup('exibirPesquisarTipoServicoAction.do', 'servicoTipoReferencia', null, null, 300, 620, '','');">
																			<img width="23" 
																				height="21" 
																				border="0"
																				src="<bean:message key="caminho.imagens"/>pesquisa.gif"
																				title="Pesquisar Tipo de Serviço Referência" /></a> 
		
																		<logic:present name="servicoTipoReferenciaEncontrada" scope="request">
																			
																			<html:text property="descricaoServicoTipoReferencia" 
																				size="50"
																				maxlength="50" 
																				readonly="true"
																				style="background-color:#EFEFEF; border:0; color: #000000" />
																		</logic:present> 
											
																		<logic:notPresent name="servicoTipoReferenciaEncontrada" scope="request">
																			
																			<html:text property="descricaoServicoTipoReferencia" 
																				size="50"
																				maxlength="50" 
																				readonly="true"
																				style="background-color:#EFEFEF; border:0; color: red" />
																				
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
								 
								</c:if>
								
								
								
								<!-- Observação -->
                    			
                    			<tr> 
                      				<td class="style3"><strong>Observa&ccedil;&atilde;o:</strong></td>
                      				<td colspan="3"><strong><b><span class="style2"> <strong> 
                        				<html:textarea property="observacao" cols="50" rows="4"  
                        				onkeyup=" validarTamanhoMaximoTextArea(this,200);"/>
                        				</strong> </span></b></strong>
                        			</td>
                    			</tr>
                    			
                    			<tr> 

	                    			<!-- Valor do Seviço Original -->

                      				<td class="style3"><strong>Valor do Servi&ccedil;o Original:<strong><span class="style2"><strong><strong><span class="style3"><strong><span class="style2"><strong><font color="#FF0000">*</font></strong></span></strong></span></strong></strong></span></strong></strong></td>
                      				<td width="26%">
                      					<strong>
                        					
										<html:text property="valorServicoOriginal"
											readonly="true" 
											style="background-color:#EFEFEF; border:0; text-align:right;"
											size="10"/>
                        					
                        				</strong>
                        			</td>
                        			
                        			<!-- Valor do Serviço Atual -->
                        			
                      				<td width="17%"></td>
                      				<td width="36%">
                   						<html:hidden property="valorServicoAtual" />
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
				                    
                      				<td class="style3">
                      					<b>Prioridade do Servi&ccedil;o Atual:</b>
                      				</td>
                      				<td><strong>
                      				
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

                      			<input name="buttonDesfazer" 
                      				type="button" 
                      				class="bottonRightCol" 
                      				value="Desfazer" 
                      				onClick="javascript:limparForm();"> 

                        		<input name="Submit22322" 
                        			type="button" 
                        			class="bottonRightCol"  
                        			value="Cancelar" 
                        			onClick="javascript:window.location.href='/gsan/telaPrincipal.do'"> 
                        			

								<logic:notEmpty name="caminhoRetornoGerarOs">

									<input name="button" 
										type="button" 
										class="bottonRightCol" 
										value="Voltar" 
										onclick="javascript:voltar('<bean:write name="caminhoRetornoGerarOs"/>')" >

								</logic:notEmpty>
	
							<!--	<logic:empty name="caminhoRetornoTelaPesquisaRegistroAtendimento">
									<input name="button" 
										type="button" 
										class="bottonRightCol" 
										value="Voltar" 
										onclick="javascript:voltar('exibirConsultarRegistroAtendimentoAction.do?menu=sim&numeroRA=${registroAtendimentoHelper.registroAtendimento.id}')" >
								</logic:empty> -->

                        			
                      		</td>
                      		<td align="right"> 
                      		<div align="right">
                      			<input name="Submit223222" 
                      				type="button" 
                      				class="bottonRightCol"  
                      				onClick="javascript:validarForm();" 
                      				value="Gerar"> 
                        		
                        		<input name="Submit22323223" 
                        			type="button" 
                        			class="bottonRightCol" 
                        			onClick="javascript:consultarOS();" 
                        			value="Consultar Ordens de Servi&ccedil;o"> 
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
<!-- Fim do Corpo -->

	<%@ include file="/jsp/util/rodape.jsp"%>
</html:form>
</div>
<%@ include file="/jsp/util/telaespera.jsp"%>


</body>
</html:html>