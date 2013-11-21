<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>

<%@ include file="/jsp/util/telaespera.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%@ page import="gcom.gui.GcomAction"%>
<%@ page import="gcom.atendimentopublico.registroatendimento.RAReiteracaoFone"%>
<%@ page import="gcom.cadastro.cliente.ClienteEndereco" %>
<%@ page import="gcom.cadastro.cliente.ClienteFone" %>

<html:html>
<head>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>


<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="ReiterarRegistroAtendimentoActionForm" />

<script language="JavaScript">

	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
	
	  var form = document.forms[0];
	
	  if (tipoConsulta == 'cliente') {
	    form.idClienteSolicitante.value = codigoRegistro;
	    form.nomeSolicitante.value = descricaoRegistro;
	    form.nomeSolicitante.readonly = true;
	    
	    redirecionarSubmit('exibirReiterarRegistroAtendimentoAction.do?pesquisarCliente=S&cliente='+ codigoRegistro);
	  }
	  
	  if (tipoConsulta == 'unidadeOrganizacional') {
	    form.idUnidadeSolicitante.value = codigoRegistro;
	    form.nomeSolicitante.value = descricaoRegistro;
	    form.nomeSolicitante.readonly = true;
	    redirecionarSubmit('exibirReiterarRegistroAtendimentoAction.do?pesquisarUnidade=S&unidade='+ codigoRegistro);
	  }
	 
	}
	function consultarRA() {
		var form = document.forms[0];
		form.action = 'exibirConsultarRegistroAtendimentoAction.do?numeroRA='+form.numeroRA.value;
		form.submit();
	}
	function desfazer(){
	  var form = document.forms[0];
	  redirecionarSubmit('exibirReiterarRegistroAtendimentoAction.do?desfazer=S');
	}
	function limparDadosSolicitante(){
	  var form = document.forms[0];
	  redirecionarSubmit('exibirReiterarRegistroAtendimentoAction.do?limparDadosSolicitante=S');
	}
	
	function validaForm() {
	  var form = document.forms[0];
	 	if(validateReiterarRegistroAtendimentoActionForm(form)){	
	  
	  		botaoAvancarTelaEspera('reiterarRegistroAtendimentoAction.do');
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


	function controladorHabilitacaoCampos(){
	  
	  	var form = document.forms[0];
	  	
	  	if (form.idCliente.value.length > 0){
	  		form.idUnidadeSolicitante.readOnly = true;
			form.nomeSolicitante.readOnly = true;
	  	}
	  	else if (form.idUnidadeSolicitante.value.length > 0 
	  			 || form.idFuncionarioResponsavel.value.length > 0){
	  		form.idCliente.readOnly = true;
	  		form.nomeSolicitante.readOnly = true;
	  	}
	  	else if (form.nomeSolicitante.value.length > 0) {
	  		form.idCliente.readOnly = true;
	  		form.idUnidadeSolicitante.readOnly = true;
		    form.idFuncionarioResponsavel.readOnly = true;
	  	}
 	}

	function habilitaDadosSolicitante(nomeSolicitante){
	
		var form = document.forms[0];
		
		form.idClienteSolicitante.value = "";
		form.idUnidadeSolicitante.value = "";
		
		if (nomeSolicitante.value.length > 0){
			redirecionarSubmit('exibirReiterarRegistroAtendimentoAction.do?informadoNomeSolicitante=OK');
		}
	}
	
	function desabilitaDadosSolicitante(nomeSolicitante){
	
		var form = document.forms[0];
		
		form.idClienteSolicitante.value = "";
		form.idUnidadeSolicitante.value = "";
		
		if (nomeSolicitante.value.length < 1){
			redirecionarSubmit('exibirReiterarRegistroAtendimentoAction.do?limparNomeSolicitante=OK');
		}
	}
	
	function removerEndereco(){
  		redirecionarSubmit("exibirReiterarRegistroAtendimentoAction.do?removerEndereco=OK");
  	}
  
    function removerFone(objetoRemocao){
	  	redirecionarSubmit("exibirReiterarRegistroAtendimentoAction.do?removerFone=" + objetoRemocao);
	}

</script>
</head>

<body leftmargin="5" topmargin="5"
	onload="setarFoco('${requestScope.nomeCampo}');">
<div id="formDiv">
<html:form action="/reiterarRegistroAtendimentoAction.do"
	name="ReiterarRegistroAtendimentoActionForm"
	type="gcom.gui.atendimentopublico.registroatendimento.ReiterarRegistroAtendimentoActionForm"
	method="post"
	onsubmit="return validateReiterarRegistroAtendimentoActionForm(this);">

	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>

<input type="hidden" id="idClienteSolicitante">
<input type="hidden" id="idUnidadeSolicitante">
<INPUT TYPE="hidden" ID="enderecoPertenceCliente" value="${sessionScope.enderecoPertenceCliente}">

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

			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_left.gif" /></td>
					<td class="parabg">Reiterar Registro de Atendimento</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" />
					</td>
				</tr>
			</table>

			<table width="100%">
			
			    <tr>
		      		<td colspan="4" HEIGHT="40" align="center">
		      		<span style="font-size: 18px;font-weight: bold;">
		      		Nº Protocolo: ${sessionScope.protocoloAtendimento}</span></td>
		        </tr>
      
			
				<tr>
						<td width="90%" colspan="2">&nbsp;</td>
					<logic:present scope="application" name="urlHelp">
						<td align="right"><a href="javascript:abrirPopupHelp('${applicationScope.urlHelp}atendimentoRegistroReativar', 500, 700);"><span style="font-weight: bold"><font color="#3165CE">Ajuda</font></span></a></td>									
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

					

							<!-- <div id="layerShowRegistroAtendimento" style="display:none">-->
							<table width="100%" border="0" bgcolor="#99CCFF">
								<tr bgcolor="#99CCFF">
									<td align="center"><span class="style2"> 
									<b>Dados do Registro de Atendimento</b> </span></td>
									<!-- <a href="javascript:extendeTabela('RegistroAtendimento',false);" />
									<b>Dados do Registro de Atendimento</b> </a> </span></td>-->
								</tr>

								<tr bgcolor="#cbe5fe">
									<td>
									<table border="0" width="100%">

										<tr>
		                              		<td><strong>Número do RA:</strong></td>
		                              		<td> 
												<html:text property="numeroRA" readonly="true" size="9"
													maxlength="9" style="background-color:#EFEFEF; border:0;"/>
		                                  	</td>
		                                  	
                                    		<td>
                                    			<strong>Data Prevista:</strong>
                                    		</td>

                                    		<td>
												<html:text property="dataPrevista" readonly="true"
													style="background-color:#EFEFEF; border:0;" size="10" 
													maxlength="10" />
											</td>
										</tr>
										<tr> 
		                              		<td><strong>Tipo de Solicitação:</strong></td>
		                              		<td colspan="3">
												<html:text property="idTipoSolicitacao" readonly="true"
													style="background-color:#EFEFEF; border:0;" size="4"
													maxlength="4" />
		                              		
												<html:text property="descTipoSolicitacao" readonly="true"
													style="background-color:#EFEFEF; border:0;" size="50"
													maxlength="50" />
		                                	</td>
		                            	</tr>
		                            	
		                            	<tr> 
		                              		<td><strong>Especificação:</strong></td>
		                              		<td colspan="3"> 
												<html:text property="idEspecificacao" readonly="true"
													style="background-color:#EFEFEF; border:0;" size="4"
													maxlength="4" />

												<html:text property="descEspecificacao" readonly="true"
													style="background-color:#EFEFEF; border:0;" size="50"
													maxlength="50" />
		                                 	</td>
		                            	</tr>
		                            	
		                            	 <tr> 
			                              	<td><strong>Unidade Atual:</strong></td>
			                              	<td colspan="3">
												<html:text property="idUnidadeAtual" readonly="true"
													style="background-color:#EFEFEF; border:0;" size="4"
													maxlength="4" />

												<html:text property="descUnidadeAtual" readonly="true"
													style="background-color:#EFEFEF; border:0;" size="50"
													maxlength="40" />
											</td>
			                            </tr>
		                            	
									</table>
									</td>
								</tr>
							</table>
							<!--  </div>-->

							</td>
						</tr>
					</table>
					</td>
				</tr>
				<tr>
					<td height="10" colspan="2">Para reiterar o registro de	atendimento, informe os dados abaixo:</td>
				</tr>
				<tr>
					<td height="31">
					<table width="100%" border="0" align="center">

						<tr bgcolor="#cbe5fe">

							<td align="center">
							<table width="100%" border="0" bgcolor="#99CCFF">

								<tr bgcolor="#99CCFF">
									<td height="18" colspan="2">
									<div align="center"><span class="style2"><b>Dados da reiteração </b></span></div>
									</td>
								</tr>

								<tr bgcolor="#cbe5fe">
									<td>
									<table border="0" width="100%">
		                            	<tr>
		                              		<td><strong>Solicitante:<font color="#FF0000">*</font></strong></td>
		                              		
		                              		<td> 
		                              		
		                              			<logic:present name="desabilitarDadosSolicitanteNome">
									        		<html:text property="nomeSolicitante" size="50" maxlength="50" readonly="true"/>
												</logic:present>
												<logic:notPresent name="desabilitarDadosSolicitanteNome">
													<html:text property="nomeSolicitante" size="50" maxlength="50" onblur="habilitaDadosSolicitante(this);" onkeyup="desabilitaDadosSolicitante(this);"/>&nbsp;
												</logic:notPresent>
												
												<logic:present name="desabilitarDadosSolicitanteCliente">
									        		<input type="button" class="bottonRightCol" value="Cliente" disabled="true"
													style="width: 60px" onclick="javascript:abrirPopup('exibirPesquisarClienteAction.do', 475, 800);">&nbsp;
												</logic:present>
												<logic:notPresent name="desabilitarDadosSolicitanteCliente">
									        		<input type="button" class="bottonRightCol" value="Cliente"
													style="width: 60px" onclick="javascript:abrirPopup('exibirPesquisarClienteAction.do', 475, 800);">&nbsp;
												</logic:notPresent>
												
												<logic:present name="desabilitarDadosSolicitanteUnidade">
									        		<input type="button" class="bottonRightCol" value="Unidade" disabled="true"
													style="width: 60px" onclick="javascript:abrirPopup('exibirPesquisarUnidadeOrganizacionalAction.do', 410, 790);">
												</logic:present>
												<logic:notPresent name="desabilitarDadosSolicitanteUnidade">
									        		<input type="button" class="bottonRightCol" value="Unidade"
													style="width: 60px" onclick="javascript:abrirPopup('exibirPesquisarUnidadeOrganizacionalAction.do', 410, 790);">
												</logic:notPresent>
													
													
													<a	href="javascript:limparDadosSolicitante();">
													<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
													border="0" title="Apagar" /> </a>
		                                  	</td>
										</tr>
									</table>
									</td>
								</tr>

								<tr bgcolor="#cbe5fe">
									<td>
									<table border="0" width="100%">
<!-- 
										<tr>
											<td width="183"><strong> Endereço <font	color="red"> * </font> </strong></td>
											<td width="432" align="right">
												
										<logic:present name="colecaoEnderecos">
							 
											<logic:empty name="colecaoEnderecos">
												<input type="button" class="bottonRightCol" value="Adicionar" tabindex="4" id="botaoEndereco" onclick="abrirPopupComSubmit('exibirInserirEnderecoAction.do?tipoPesquisaEndereco=registroAtendimento&operacao=5&mostrarPerimetro=sim', 570, 700, 'Endereco');">
											</logic:empty>
											<logic:notEmpty name="colecaoEnderecos">
												<input type="button" class="bottonRightCol" value="Adicionar" tabindex="4" id="botaoEndereco" disabled>
											</logic:notEmpty>
									 
									 	</logic:present>
							
									 	<logic:notPresent name="colecaoEnderecos">
											<logic:present name="habilitarAlteracaoEndereco">
												<logic:equal name="habilitarAlteracaoEndereco" value="SIM">
													<input type="button" class="bottonRightCol" value="Adicionar" tabindex="4"  id="botaoEndereco" onclick="abrirPopupComSubmit('exibirInserirEnderecoAction.do?tipoPesquisaEndereco=registroAtendimento&operacao=5&mostrarPerimetro=sim', 570, 700, 'Endereco');">
											 	</logic:equal>
											 	<logic:notEqual name="habilitarAlteracaoEndereco" value="SIM">
											 		<input type="button" class="bottonRightCol" value="Adicionar" tabindex="4" id="botaoEndereco" disabled>
											 	</logic:notEqual>
											</logic:present>
										
											<logic:notPresent name="habilitarAlteracaoEndereco">
												<input type="button" class="bottonRightCol" value="Adicionar" tabindex="4"  id="botaoEndereco" onclick="abrirPopupComSubmit('exibirInserirEnderecoAction.do?tipoPesquisaEndereco=registroAtendimento&operacao=5&mostrarPerimetro=sim', 570, 700, 'Endereco');">
											</logic:notPresent>
									 	
									 	</logic:notPresent>

											</td>
										</tr>
											     	
								     	
								    <tr>
										<td colspan="2">
										<table width="100%" cellpadding="0" cellspacing="0">
											<tr>
												<td>
													<table width="100%" bgcolor="#90c7fc" border="0">
														<tr bgcolor="#90c7fc">
															<td width="9%" align="center"><strong>Remover</strong></td>
															<td width="23%" align="center"><strong>End. de Corresp.</strong></td>
															<td width="83%" align="center"><strong>Endere&ccedil;o</strong></td>
														</tr>
													</table>
												</td>
											</tr>
											<tr>
												<td>
												<table width="100%" bgcolor="#99CCFF">
													<logic:notPresent name="colecaoEnderecos">
														<input type="hidden" id="validarEndereco" value="0">
													</logic:notPresent>
													
													<logic:present name="colecaoEnderecos">
														<%int cont = 0;%>
														
														<input type="hidden" name="enderecoRemoverSelecao" value="" />
														<logic:iterate name="colecaoEnderecos" id="endereco" scope="session" type="ClienteEndereco">
										
															<%cont = cont + 1;
															if (cont % 2 == 0) {%>
															<tr bgcolor="#cbe5fe">
															<%} else {%>
															<tr bgcolor="#FFFFFF">
															<%}%>
															<td width="10%" align="center">
														
																<logic:present name="habilitarAlteracaoEnderecoSolicitante">
																	<logic:equal name="habilitarAlteracaoEnderecoSolicitante" value="SIM">
																		<a href="javascript:if(confirm('Confirma remoção?')){removerEndereco();}" alt="Remover"><img src="<bean:message key='caminho.imagens'/>Error.gif" width="14" height="14" border="0"></a>
													 				</logic:equal>
													 				<logic:notEqual name="habilitarAlteracaoEnderecoSolicitante" value="SIM">
													 					<img src="<bean:message key='caminho.imagens'/>Error.gif" width="14" height="14" border="0">
													 				</logic:notEqual>
																</logic:present>
												
																<logic:notPresent name="habilitarAlteracaoEnderecoSolicitante">
																	<a href="javascript:if(confirm('Confirma remoção?')){removerEndereco();}" alt="Remover"><img src="<bean:message key='caminho.imagens'/>Error.gif" width="14" height="14" border="0"></a>
																</logic:notPresent>
																
															</td>
													
															<td width="20%" align="center">
															<logic:equal name="endereco" property="indicadorEnderecoCorrespondencia" value="1">
																<input type="radio" name="clienteEnderecoSelected" value="<%="" + GcomAction.obterTimestampIdObjeto(endereco) %>" disabled="true" checked/>
															</logic:equal>
															
															<logic:notEqual name="endereco" property="indicadorEnderecoCorrespondencia" value="1">
																<input type="radio" name="clienteEnderecoSelected" value="<%="" + GcomAction.obterTimestampIdObjeto(endereco) %>" disabled="true"/>
															</logic:notEqual>
															</td>
															
															<td>
																<logic:equal name="habilitarAlteracaoEnderecoAbaSolicitante" value="SIM">
																	<a href="javascript:abrirPopup('exibirInserirEnderecoAction.do?exibirEndereco=OK&tipoPesquisaEndereco=registroAtendimento&operacao=1&mostrarPerimetro=sim', 570, 700)"><bean:write name="endereco" property="enderecoFormatadoAbreviado"/></a>
																</logic:equal>
																
																<logic:notEqual name="habilitarAlteracaoEnderecoAbaSolicitante" value="SIM">
																	<bean:write name="endereco" property="enderecoFormatadoAbreviado"/>
																</logic:notEqual>
															</td>
															</tr>
														</logic:iterate>
													</logic:present>
												</table>
												</td>
											</tr>
											
											
										</table>
										</td>
									</tr>

									<tr bgcolor="#cbe5fe">
										<td colspan="4">
										<table border="0" width="100%">
			                            	<tr>
			                              		<td><strong>Ponto de Referência:</strong></td>
			                              		
			                              		<td> 
													<html:text property="pontoReferencia" size="60" maxlength="60"/>&nbsp;
			                                  	</td>
											</tr>
										</table>
										</td>
									</tr>
									 -->
									 
									<tr>
								         <td colspan="3">
								
											<table width="100%" border="0">
									      	<tr>
									      		<td><strong>Fones do Solicitante:<font color="#FF0000">*</font></strong></td>
									      		<td align="right">
									      		<input type="button" class="bottonRightCol" value="Adicionar" tabindex="5" id="botaoFone" onclick="abrirPopupComSubmit('exibirAdicionarSolicitanteFoneAction.do?telaRetornoReiterar=exibirReiterarRegistroAtendimentoAction.do', 310, 600, 'Fone');">
									      		<!-- 
									      			<logic:present name="colecaoFonesSolicitante">
									      			
									      				<logic:present name="habilitarAlteracaoEnderecoSolicitante">
															<logic:equal name="habilitarAlteracaoEnderecoSolicitante" value="SIM">
																<input type="button" class="bottonRightCol" value="Adicionar" tabindex="5" id="botaoFone" onclick="abrirPopupComSubmit('exibirAdicionarSolicitanteFoneAction.do?telaRetornoReiterar=exibirReiterarRegistroAtendimentoAction.do', 310, 600, 'Fone');">
											 				</logic:equal>
											 				<logic:notEqual name="habilitarAlteracaoEnderecoSolicitante" value="SIM">
											 					<input type="button" class="bottonRightCol" value="Adicionar" tabindex="5" id="botaoFone" disabled="disabled">
											 				</logic:notEqual>
														</logic:present>
										
														<logic:notPresent name="habilitarAlteracaoEnderecoSolicitante">
															<input type="button" class="bottonRightCol" value="Adicionar" tabindex="5" id="botaoFone" onclick="abrirPopupComSubmit('exibirAdicionarSolicitanteFoneAction.do?telaRetornoReiterar=exibirReiterarRegistroAtendimentoAction.do', 310, 600, 'Fone');">
														</logic:notPresent>
									      			
										              
												 	</logic:present>
										
												 	<logic:notPresent name="colecaoFonesSolicitante">
													
														<logic:present name="habilitarAlteracaoEndereco">
															<logic:equal name="habilitarAlteracaoEndereco" value="SIM">
																<input type="button" class="bottonRightCol" value="Adicionar" tabindex="5"  id="botaoFone" onclick="abrirPopupComSubmit('exibirAdicionarSolicitanteFoneAction.do?telaRetornoReiterar=exibirReiterarRegistroAtendimentoAction.do', 310, 600, 'Fone');">
														 	</logic:equal>
														 	<logic:notEqual name="habilitarAlteracaoEndereco" value="SIM">
														 		<input type="button" class="bottonRightCol" value="Adicionar" tabindex="5" id="botaoFone" disabled>
														 	</logic:notEqual>
														</logic:present>
													
														<logic:notPresent name="habilitarAlteracaoEndereco">
															<input type="button" class="bottonRightCol" value="Adicionar" tabindex="5"  id="botaoFone" onclick="abrirPopupComSubmit('exibirAdicionarSolicitanteFoneAction.do?telaRetornoReiterar=exibirReiterarRegistroAtendimentoAction.do', 310, 600, 'Fone');">
														</logic:notPresent>
												 	
												 	</logic:notPresent>
									      		 -->
									      		</td>
									      	</tr>
										 	</table>
										 </td>
								     </tr>
								     <tr>
								         <td colspan="3" height="50" valign="top">
											<table width="100%" cellpadding="0" cellspacing="0">
											<tr>
												<td>
													<table width="100%" border="0" bgcolor="#99CCFF">
													<tr bgcolor="#99CCFF" height="18">
														<td width="10%" align="center"><strong>Remover</strong></td>
														<td width="20%" align="center"><strong>Principal</strong></td>
														<td width="35%" align="center"><strong>Telefone</strong></td>
														<td width="35%" align="center"><strong>Tipo</strong></td>
													</tr>
													</table>
												</td>
											</tr>
								
											<logic:present name="colecaoFonesSolicitante">
								
											<tr>
												<td>
													<table width="100%" align="center" bgcolor="#99CCFF">
														<!--corpo da segunda tabela-->
								
														<% String cor1 = "#cbe5fe";%>
								
														<logic:iterate name="colecaoFonesSolicitante" id="fone" type="ClienteFone">
														
															<%	if (cor1.equalsIgnoreCase("#cbe5fe")){	
																cor1 = "#FFFFFF";%>
																<tr bgcolor="#FFFFFF" height="18">	
															<%} else{	
																cor1 = "#cbe5fe";%>
																<tr bgcolor="#cbe5fe" height="18">		
															<%}%>
														
															<td width="10%" align="center">
															 <a href="javascript:if(confirm('Confirma remoção?')){removerFone('<%="" + GcomAction.obterTimestampIdObjeto(fone) %>');}" alt="Remover"><img src="<bean:message key='caminho.imagens'/>Error.gif" width="14" height="14" border="0"></a>
														<!-- 
														<logic:present name="habilitarAlteracaoEnderecoSolicitante">
															<logic:equal name="habilitarAlteracaoEnderecoSolicitante" value="SIM">
																<a href="javascript:if(confirm('Confirma remoção?')){removerFone('<%="" + GcomAction.obterTimestampIdObjeto(fone) %>');}" alt="Remover"><img src="<bean:message key='caminho.imagens'/>Error.gif" width="14" height="14" border="0"></a>
											 				</logic:equal>
											 				<logic:notEqual name="habilitarAlteracaoEnderecoSolicitante" value="SIM">
											 					<img src="<bean:message key='caminho.imagens'/>Error.gif" width="14" height="14" border="0">
											 				</logic:notEqual>
														</logic:present>
										
														<logic:notPresent name="habilitarAlteracaoEnderecoSolicitante">
															<a href="javascript:if(confirm('Confirma remoção?')){removerFone('<%="" + GcomAction.obterTimestampIdObjeto(fone) %>');}" alt="Remover"><img src="<bean:message key='caminho.imagens'/>Error.gif" width="14" height="14" border="0"></a>
														</logic:notPresent>
														 -->
													</td>
													
													<td width="20%" align="center">
														
														<logic:present name="habilitarAlteracaoEnderecoSolicitante">
															<logic:equal name="habilitarAlteracaoEnderecoSolicitante" value="SIM">
																<logic:equal name="fone" property="indicadorTelefonePadrao" value="1">
																	<input type="radio" name="clienteFoneSelected" value="<%="" + GcomAction.obterTimestampIdObjeto(fone) %>" checked/>
																</logic:equal>
																
																<logic:notEqual name="fone" property="indicadorTelefonePadrao" value="1">
																	<input type="radio" name="clienteFoneSelected" value="<%="" + GcomAction.obterTimestampIdObjeto(fone) %>"/>
																</logic:notEqual>
											 				</logic:equal>
											 				<logic:notEqual name="habilitarAlteracaoEnderecoSolicitante" value="SIM">
											 					<logic:equal name="fone" property="indicadorTelefonePadrao" value="1">
																	<input type="radio" name="clienteFoneSelected" disabled="disabled" value="<%="" + GcomAction.obterTimestampIdObjeto(fone) %>" checked/>
																</logic:equal>
																
																<logic:notEqual name="fone" property="indicadorTelefonePadrao" value="1">
																	<input type="radio" name="clienteFoneSelected" disabled="disabled" value="<%="" + GcomAction.obterTimestampIdObjeto(fone) %>"/>
																</logic:notEqual>
											 				</logic:notEqual>
														</logic:present>
										
														<logic:notPresent name="habilitarAlteracaoEnderecoSolicitante">
															<logic:equal name="fone" property="indicadorTelefonePadrao" value="1">
																<input type="radio" name="clienteFoneSelected" value="<%="" + GcomAction.obterTimestampIdObjeto(fone) %>" checked/>
															</logic:equal>
															
															<logic:notEqual name="fone" property="indicadorTelefonePadrao" value="1">
																<input type="radio" name="clienteFoneSelected" value="<%="" + GcomAction.obterTimestampIdObjeto(fone) %>"/>
															</logic:notEqual>
														</logic:notPresent>
														
														
														
													</td>
													
													<td width="35%" align="center">
														<bean:write name="fone" property="dddTelefone"/>
													</td>
													
													<td width="35%" align="center">
														<bean:write name="fone" property="foneTipo.descricao"/>
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
					
				
									
									<tr>
										<td><strong>Observação:</strong></td>
										<td>
											<html:textarea property="observacao" cols="50" rows="4" onkeyup="limitTextArea(document.forms[0].observacao, 200, document.getElementById('utilizado'), document.getElementById('limite'));"/><br>
											<strong><span id="utilizado">0</span>/<span id="limite">200</span></strong>				
										
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
					<td>

					<table width="100%">
						<tr>
							<td><input name="ButtonVoltar" type="button"
								class="bottonRightCol" value="Voltar" onclick="javascript:consultarRA();">
							<input name="ButtonDesfazer" type="button" class="bottonRightCol"
								value="Desfazer" onclick="javascript:desfazer();"> <input
								name="ButtonCancelar" type="button" class="bottonRightCol"
								value="Cancelar"
								onClick="javascript:window.location.href='/gsan/telaPrincipal.do'">
							</td>
							
							<td align="right"><input name="ButtonInserir" type="button"
								class="bottonRightCol" onClick="javascript:validaForm()"
								value="Reiterar"></td>
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
</body>
</html:html>
