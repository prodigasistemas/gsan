<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
<head>
<meta http-equiv="Expires" CONTENT="0">
<meta http-equiv="Cache-Control" CONTENT="no-store">
<meta http-equiv="Pragma" CONTENT="no-store">

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">

<%--@ page import="gcom.util.ConstantesSistema"--%>

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>

<script language="JavaScript">
	
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
	
	function atualizar() {
		var form = document.forms[0];
		form.btnAtualizar.disabled = true;
		form.action = "exibirAtualizarOrdemServicoAction.do?primeiraVez=1&numeroOS=" + form.numeroOSPesquisada.value + "&retornoTela=exibirConsultarDadosOrdemServicoAction.do";
		form.submit();
	}
	function encerrar() {
		var form = document.forms[0];
		form.btnEncerrar.disabled = true;
		form.action = "exibirEncerrarOrdemServicoAction.do?numeroOS=" + form.numeroOSPesquisada.value + "&retornoTela=exibirConsultarDadosOrdemServicoAction.do?numeroOS=" + form.numeroOSPesquisada.value;
		form.submit();
	}
	
	function voltar(manterOS) {
		var form = document.forms[0];
		window.location.href='/gsan/'+manterOS;
	}
	
	function validarPesquisa(){
	
		var form = document.forms[0];
		
		if (validateConsultarDadosOrdemServicoActionForm(form)){
			submeterFormPadrao(form);
		}
	}
	
	function osAnterior(){
 
 		var form = document.forms[0];
 		form.action = "exibirConsultarDadosOrdemServicoAction.do?osAnterior=1&numeroOS=" + form.numeroOSPesquisada.value;
 		submeterFormPadrao(form);
	}

	function proximoOS(){
 		var form = document.forms[0];
 		form.action = "exibirConsultarDadosOrdemServicoAction.do?proximoOS=1&numeroOS=" + form.numeroOSPesquisada.value;
 		submeterFormPadrao(form);
	}
	
</script>

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false"
	formName="ConsultarDadosOrdemServicoActionForm" />

</head>

<body leftmargin="0" topmargin="0"
	onload="window.focus();setarFoco('${requestScope.nomeCampo}');">

<html:form action="/exibirConsultarDadosOrdemServicoAction"
	name="ConsultarDadosOrdemServicoActionForm"
	type="gcom.gui.atendimentopublico.ordemservico.ConsultarDadosOrdemServicoActionForm"
	method="post">

	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>

	<input type="hidden" name="numeroOS" value="${param.numeroOS}" />
	<input type="hidden" name="origem" value="${param.origem}" />

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

			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_left.gif" /></td>
					<td class="parabg">Consultar Ordem de Servi&ccedil;o</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" />
					</td>
				</tr>
			</table>

			<p>&nbsp;</p>


			<table width="100%" border="0">
				<tr>
					<td align="right"><a
						href="javascript: abrirPopupHelp('/gsan/help/help.jsp?mapIDHelpSet=atendimentoOrdemServicoConsultar', 500, 700);"><span
						style="font-weight: bold"><font color="#3165CE">Ajuda</font></span></a></td>
				</tr>
			</table>


			<!--Inicio da Tabela Dados Gerais da Ordem de Serviço -->
			<table width="100%" border="0">

				<tr>
					<td height="31">
					<table width="100%" border="0" align="center">

						<tr bgcolor="#cbe5fe">

							<td align="center">
							<table width="100%" border="0" bgcolor="#99CCFF">

								<tr bgcolor="#99CCFF">
									<td height="18" colspan="2">
									<div align="center"><span class="style2"><b>Pesquisar outra
									Ordem de Serviço</b></span></div>
									</td>
								</tr>

								<tr bgcolor="#cbe5fe">
									<td>
									<table border="0" width="100%">

										<tr>
											<td height="10" width="22%"><strong>Número da OS:</strong></td>

											<td><html:text property="numeroOSParametro" size="9"
												maxlength="9" 
												onkeypress="return isCampoNumerico(event);"/>&nbsp; <input type="button"
												class="bottonRightCol" value="Pesquisar" style="width: 80px"
												onclick="validarPesquisa();"></td>
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
									onclick="osAnterior();"></td>
								<td align="left" width="15%"><input type="button"
									name="Button" class="buttonAbaRodaPe" value="OS Anterior"
									onclick="osAnterior();" /></td>
											
							</logic:notPresent>
										
							<logic:notPresent name="desabilitaBotaoProximo">	
											
								<td align="right" width="97%"><input type="button"
									name="Button" class="buttonAbaRodaPe" value="Próxima OS"
									onclick="proximoOS();" /></td>
								<td align="right" width="3%"><img
									src="<bean:message key="caminho.imagens"/>avancar.gif"
									onclick="proximoOS()"></td>
										
							</logic:notPresent>
										
						</tr>
											
						</table>
								
					</td>
			    </tr>
			    
			    </logic:notPresent>

				<tr>
					<td height="31" colspan="2">
					<table width="100%" border="0" align="center">

						<tr bgcolor="#cbe5fe">

							<td align="center">
							<table width="100%" border="0" bgcolor="#99CCFF">

								<tr bgcolor="#99CCFF">
									<td height="18" colspan="2">
									<div align="center"><span class="style2"><b>Dados Gerais da
									Ordem de Servi&ccedil;o </b></span></div>
									</td>
								</tr>

								<tr bgcolor="#cbe5fe">
									<td>
									<table border="0" width="100%">

										<tr>
											<td height="10" width="33%"><strong>N&uacute;mero da OS:</strong></td>

											<td><html:text property="numeroOSPesquisada" readonly="true"
												style="background-color:#EFEFEF; border:0;" size="9"
												maxlength="9" /> &nbsp;&nbsp;&nbsp;&nbsp; <strong>Situa&ccedil;&atilde;o
											da OS:</strong> &nbsp;&nbsp; <html:text property="situacaoOS"
												readonly="true" style="background-color:#EFEFEF; border:0;"
												size="24" maxlength="25" /></td>
										</tr>
										<c:if
											test="${ConsultarDadosOrdemServicoActionForm.numeroRA != null}">
											<tr>
												<td height="10" width="33%"><strong>N&uacute;mero do RA:</strong></td>

												<td><html:text property="numeroRA" readonly="true"
													style="background-color:#EFEFEF; border:0;" size="9"
													maxlength="9" /> &nbsp;&nbsp;&nbsp;&nbsp; <strong>Situa&ccedil;&atilde;o
												do RA:</strong> &nbsp;&nbsp; <html:text
													property="situacaoRA" readonly="true"
													style="background-color:#EFEFEF; border:0;" size="24"
													maxlength="25" /></td>
											</tr>
										</c:if>

										<c:if
											test="${ConsultarDadosOrdemServicoActionForm.numeroDocumentoCobranca != null}">
											<tr>
												<td width="33%"><strong>N&uacute;mero do Documento de
												Cobran&ccedil;a:</strong></td>
												<td><html:text property="numeroDocumentoCobranca"
													readonly="true" style="background-color:#EFEFEF; border:0;"
													size="9" maxlength="9" /></td>
											</tr>
										</c:if>

										<tr>
											<td width="33%"><strong>Data da Gera&ccedil;&atilde;o:</strong></td>
											<td><html:text property="dataGeracao" readonly="true"
												style="background-color:#EFEFEF; border:0;" size="9"
												maxlength="10" /></td>
										</tr>
										<tr>
											<td height="10" width="33%"><strong>Tipo do Servi&ccedil;o:</strong></td>
											<td><html:text property="tipoServicoId" readonly="true"
												style="background-color:#EFEFEF; border:0;" size="4"
												maxlength="4" /> <html:text property="tipoServicoDescricao"
												readonly="true" style="background-color:#EFEFEF; border:0;"
												size="50" maxlength="50" /></td>
										</tr>
										<c:if
											test="${ConsultarDadosOrdemServicoActionForm.numeroOSReferencia != null}">
											<tr>
												<td width="33%"><strong>N&uacute;mero da OS de
												Refer&ecirc;ncia:</strong></td>
												<td><html:text property="numeroOSReferencia" readonly="true"
													style="background-color:#EFEFEF; border:0;" size="9"
													maxlength="9" /></td>
											</tr>
										</c:if>

										<c:if
											test="${ConsultarDadosOrdemServicoActionForm.tipoServicoReferenciaId != null}">
											<tr>
												<td height="10" width="33%"><strong>Tipo do Servi&ccedil;o
												de Refer&ecirc;ncia:</strong></td>
												<td><html:text property="tipoServicoReferenciaId"
													readonly="true" style="background-color:#EFEFEF; border:0;"
													size="4" maxlength="4" /> <html:text
													property="tipoServicoReferenciaDescricao" readonly="true"
													style="background-color:#EFEFEF; border:0;" size="50"
													maxlength="50" /></td>
											</tr>
										</c:if>

										<c:if
											test="${ConsultarDadosOrdemServicoActionForm.retornoOSReferida != null}">
											<tr>
												<td height="10" width="33%"><strong>Retorno da OS de
												Refer&ecirc;ncia:</strong></td>
												<td><html:text property="retornoOSReferida" readonly="true"
													style="background-color:#EFEFEF; border:0;" size="48"
													maxlength="48" /></td>
											</tr>
										</c:if>

										<tr>
											<td height="10" width="20"><strong>Observa&ccedil;&atilde;o:</strong></td>
											<td><strong> <html:textarea property="observacao"
												readonly="true" style="background-color:#EFEFEF; border:0;"
												cols="40" /> </strong></td>
										</tr>

										<tr>
											<td height="10" width="33%"><strong>Valor do Servi&ccedil;o
											Original:</strong></td>

											<td><html:text property="valorServicoOriginal"
												readonly="true"
												style="background-color:#EFEFEF; border:0; text-align:right;"
												size="9" maxlength="9" /> &nbsp;&nbsp;&nbsp;&nbsp; <strong>Valor
											do Servi&ccedil;o Atual:</strong> &nbsp;&nbsp; <html:text
												property="valorServicoAtual" readonly="true"
												style="background-color:#EFEFEF; border:0; text-align:right;"
												size="9" maxlength="9" /></td>
										</tr>

										<tr>
											<td width="33%"><strong>Prioridade Original:</strong></td>
											<td><html:text property="prioridadeOriginal" readonly="true"
												style="background-color:#EFEFEF; border:0;" size="48"
												maxlength="48" /></td>
										</tr>

										<tr>
											<td width="33%"><strong>Prioridade Atual:</strong></td>
											<td><html:text property="prioridadeAtual" readonly="true"
												style="background-color:#EFEFEF; border:0;" size="48"
												maxlength="48" /></td>
										</tr>


										<tr>
											<td width="33%"><strong>Unidade da Gera&ccedil;&atilde;o da
											OS:</strong></td>
											<td><html:text property="unidadeGeracaoId" readonly="true"
												style="background-color:#EFEFEF; border:0;" size="4"
												maxlength="4" /> <html:text
												property="unidadeGeracaoDescricao" readonly="true"
												style="background-color:#EFEFEF; border:0;" size="40"
												maxlength="40" /></td>
										</tr>

										<tr>
											<td width="33%"><strong>Usu&aacute;rio da
											Gera&ccedil;&atilde;o da OS:</strong></td>
											<td><html:text property="usuarioGeracaoId" readonly="true"
												style="background-color:#EFEFEF; border:0;" size="4"
												maxlength="4" /> <html:text property="usuarioGeracaoNome"
												readonly="true" style="background-color:#EFEFEF; border:0;"
												size="40" maxlength="40" /></td>
										</tr>
										<logic:present name="nomeProjeto" scope="request">
										<tr>
											<td width="33%"><strong>Projeto:</strong></td>
											<td> <html:text property="nomeProjeto"
												readonly="true" style="background-color:#EFEFEF; border:0;"
												size="55" maxlength="50" /></td>
										</tr>
										</logic:present>
										<tr>
											<td width="33%"><strong>Data da &Uacute;ltima Emiss&atilde;o:</strong></td>
											<td><html:text property="dataUltimaEmissao" readonly="true"
												style="background-color:#EFEFEF; border:0;" size="9" /></td>
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

				<!-- Dados da Programação -->

				<tr>
					<td>
					<div id="layerHideProgramacao" style="display:block">
					<table width="100%" border="0" bgcolor="#99CCFF">
						<tr bgcolor="#99CCFF">
							<td align="center"><span class="style2"> <a
								href="javascript:extendeTabela('Programacao',true);" /> <b>Dados
							da Programação</b> </a> </span></td>
						</tr>
					</table>
					</div>

					<div id="layerShowProgramacao" style="display:none">

					<table width="100%" border="0" bgcolor="#99CCFF">

						<tr bgcolor="#99CCFF">
							<td align="center"><span class="style2"> <a
								href="javascript:extendeTabela('Programacao',false);" /> <b>Dados
							da Programação</b> </a> </span></td>
						</tr>

						<tr bgcolor="#cbe5fe">

							<td>
							<table border="0" width="100%">

								<tr>
									<td width="33%" class="style3"><strong>Data da Programação:</strong></td>
									<td><html:text property="dataProgramacao" readonly="true"
										style="background-color:#EFEFEF; border:0;" size="10"
										maxlength="10" /></td>
								</tr>
								<tr>
									<td width="33%" class="style3"><strong>Equipe da Programação:</strong></td>
									<td><html:text property="equipeProgramacao" readonly="true"
										style="background-color:#EFEFEF; border:0;" size="30"
										maxlength="30" /></td>
								</tr>
							</table>
							</td>
						</tr>
					</table>
					</div>
					</td>
				</tr>
				<!-- Dados do Local Ocorrencia -->
				<tr bgcolor="#cbe5fe">
					<td align="center">
					<div id="layerHideLocal" style="display:block">

					<table width="100%" border="0" bgcolor="#99CCFF">
						<tr bgcolor="#99CCFF">
							<td align="center"><span class="style2"> <a
								href="javascript:extendeTabela('Local',true);" /> <b>Dados do
							Local da Ocorr&ecirc;ncia</b> </a> </span></td>
						</tr>
					</table>
					</div>

					<div id="layerShowLocal" style="display:none">

					<table width="100%" border="0" bgcolor="#99CCFF">
						<tr bgcolor="#99CCFF">
							<td align="center"><span class="style2"> <a
								href="javascript:extendeTabela('Local',false);" /> <b>Dados do
							Local da Ocorr&ecirc;ncia</b> </a> </span></td>
						</tr>

						<tr bgcolor="#cbe5fe">
							<td>
							<table border="0" width="100%">
								<tr>
									<td width="33%"><strong>Matr&iacute;cula do Im&oacute;vel:</strong></td>

									<td><html:text property="matriculaImovel" readonly="true"
										style="background-color:#EFEFEF; border:0;" size="8"
										maxlength="8" /> <html:text property="inscricaoImovel"
										readonly="true" style="background-color:#EFEFEF; border:0;"
										size="21" maxlength="21" /></td>

								</tr>
								<tr>
									<td width="33%"><strong>Rota:</strong></td>

									<td><html:text property="rota" readonly="true"
										style="background-color:#EFEFEF; border:0;" size="8"
										maxlength="8" /> &nbsp;<strong>Sequencial Rota:</strong>&nbsp;
									<html:text property="sequencialRota" readonly="true"
										style="background-color:#EFEFEF; border:0;" size="8"
										maxlength="8" /></td>

								</tr>
								<tr>
									<td width="33%" class="style3"><strong><span class="style2">Endere&ccedil;o
									da Ocorr&ecirc;ncia:</span></strong></td>

									<td><html:textarea property="enderecoOcorrencia"
										readonly="true" style="background-color:#EFEFEF; border:0;"
										cols="50" /></td>
								</tr>
							</table>
							</td>
						</tr>
					</table>
					</div>
					</td>
				</tr>
				<!-- Dados do Encerramento -->
				<c:if
					test="${ConsultarDadosOrdemServicoActionForm.situacaoOSId == '2'}">
					<tr>
						<td>
						<div id="layerHideEncerramento" style="display:block">
						<table width="100%" border="0" bgcolor="#99CCFF">
							<tr bgcolor="#99CCFF">
								<td align="center"><span class="style2"> <a
									href="javascript:extendeTabela('Encerramento',true);" /> <b>Dados
								do Encerramento da Ordem de Servi&ccedil;o</b> </a> </span></td>
							</tr>
						</table>
						</div>

						<div id="layerShowEncerramento" style="display:none">

						<table width="100%" border="0" bgcolor="#99CCFF">

							<tr bgcolor="#99CCFF">
								<td align="center"><span class="style2"> <a
									href="javascript:extendeTabela('Encerramento',false);" /> <b>Dados
								do Encerramento da Ordem de Servi&ccedil;o</b> </a> </span></td>
							</tr>

							<tr bgcolor="#cbe5fe">

								<td>
								<table border="0" width="100%">

									<tr>
										<td class="style3"><strong>Data do Encerramento:</strong></td>

										<td><html:text property="dataEncerramento" readonly="true"
											style="background-color:#EFEFEF; border:0;" size="19"
											maxlength="19" /></td>
									</tr>

									<c:if
										test="${ConsultarDadosOrdemServicoActionForm.parecerEncerramento != null}">
										<tr>
											<td class="style3"><strong>Parecer do Encerramento:</strong>
											</td>

											<td><html:textarea property="parecerEncerramento"
												readonly="true" style="background-color:#EFEFEF; border:0;"
												cols="40" /></td>
										</tr>
									</c:if>

									<c:if
										test="${ConsultarDadosOrdemServicoActionForm.areaPavimentacao != null}">
										<tr>
											<td class="style3"><strong>&Aacute;rea
											Pavimenta&ccedil;&atilde;o:</strong></td>

											<td><html:text property="areaPavimentacao" readonly="true"
												style="background-color:#EFEFEF; border:0; text-align:right;"
												size="9" maxlength="9" /> <strong>m&sup2;</strong></td>
										</tr>
									</c:if>


									<tr>
										<td class="style3"><strong>Comercial Atualizado:</strong></td>

										<td><html:text property="comercialAtualizado" readonly="true"
											style="background-color:#EFEFEF; border:0;" size="5"
											maxlength="5" /></td>
									</tr>

									<tr>
										<td class="style3"><strong>Servi&ccedil;o Cobrado:</strong></td>

										<td><html:text property="servicoCobrado" readonly="true"
											style="background-color:#EFEFEF; border:0;" size="5"
											maxlength="5" /></td>
									</tr>

									<c:if
										test="${ConsultarDadosOrdemServicoActionForm.motivoNaoCobranca != null}">
										<tr>
											<td class="style3"><strong>Motivo da N&atilde;o Cobrado:</strong>
											</td>

											<td><html:text property="motivoNaoCobranca" readonly="true"
												style="background-color:#EFEFEF; border:0;" size="55"
												maxlength="55" /></td>
										</tr>
									</c:if>

									<c:if
										test="${ConsultarDadosOrdemServicoActionForm.motivoNaoCobranca == null}">
										<tr>
											<td class="style3"><strong>Percentual da Cobran&ccedil;a:</strong>
											</td>
											<td><html:text property="percentualCobranca" readonly="true"
												style="background-color:#EFEFEF; border:0; text-align:right;"
												size="10" maxlength="10" /> <strong>%</strong></td>
										</tr>

										<tr>
											<td class="style3"><strong>Valor Cobrado:</strong></td>
											<td><html:text property="valorCobrado" readonly="true"
												style="background-color:#EFEFEF; border:0; text-align:right;"
												size="10" maxlength="10" /></td>
										</tr>
									</c:if>

									<tr>
										<td class="style3"><strong>Motivo do Encerramento:</strong></td>
										<td><html:text
											property="motivoEncerramento" readonly="true"
											style="background-color:#EFEFEF; border:0;" size="40"
											maxlength="40" /></td>

									</tr>

									<tr>
										<td class="style3"><strong>Unidade do Encerramento:</strong></td>

										<td><html:text property="unidadeEncerramentoId"
											readonly="true" style="background-color:#EFEFEF; border:0;"
											size="4" maxlength="4" /> <html:text
											property="unidadeEncerramentoDescricao" readonly="true"
											style="background-color:#EFEFEF; border:0;" size="40"
											maxlength="40" /></td>

									</tr>

									<tr>
										<td class="style3"><strong>Usu&aacute;rio do Encerramento:</strong>
										</td>
										<td><html:text property="usuarioEncerramentoId"
											readonly="true" style="background-color:#EFEFEF; border:0;"
											size="2" maxlength="2" /> <html:text
											property="usuarioEncerramentoNome" readonly="true"
											style="background-color:#EFEFEF; border:0;" size="40"
											maxlength="40" /></td>
									</tr>

								</table>
								</td>
							</tr>

						</table>
						</div>

						</td>
					</tr>
				</c:if>

				<!-- Dados das Atividades -->
				<c:choose>

					<c:when
						test="${not empty ConsultarDadosOrdemServicoActionForm.colecaoOSAtividade}">
						<tr>
							<td>
							<div id="layerHideAtividade" style="display:block">
							<table width="100%" border="0" bgcolor="#99CCFF">
								<tr bgcolor="#99CCFF">
									<td align="center"><span class="style2"> <a
										href="javascript:extendeTabela('Atividade',true);" /> <b>Dados
									das Atividades da Ordem de Servi&ccedil;o</b> </a> </span></td>
								</tr>
							</table>
							</div>

							<div id="layerShowAtividade" style="display:none">

							<table width="100%" border="0" bgcolor="#99CCFF">

								<tr bgcolor="#99CCFF">
									<td align="center"><span class="style2"> <a
										href="javascript:extendeTabela('Atividade',false);" /> <b>Dados
									das Atividades da Ordem de Servi&ccedil;o</b> </a> </span></td>
								</tr>

								<tr bgcolor="#cbe5fe">

									<td>
									<table width="100%" bgcolor="#99CCFF">
										<tr bordercolor="#90c7fc" bgcolor="#90c7fc">
											<td width="50%">
											<div align="center"><strong>Atividade</strong></div>
											</td>
											<td width="25%">
											<div align="center"><strong></strong></div>
											</td>
											<td width="25%">
											<div align="center"><strong></strong></div>
											</td>
										</tr>
										<tr>
											<c:set var="count" value="0" />
											<logic:iterate id="helper"
												name="ConsultarDadosOrdemServicoActionForm"
												property="colecaoOSAtividade"
												type="gcom.atendimentopublico.ordemservico.bean.ObterDadosAtividadeIdOSHelper"
												scope="session">

												<c:set var="count" value="${count+1}" />
												<c:choose>
													<c:when test="${count%2 == '1'}">
														<tr bgcolor="#FFFFFF">
													</c:when>
													<c:otherwise>
														<tr bgcolor="#cbe5fe">
													</c:otherwise>
												</c:choose>
												<td bordercolor="#90c7fc">
												<div align="center"><bean:write name="helper"
													property="atividade.descricao" /></div>
												</td>
												<td bordercolor="#90c7fc">
												<div align="center"><c:if test="${helper.periodo==true}">
													<a
														href="javascript:abrirPopup('/gsan/exibirDetalharPeriodoExecucaoEquipePopupAction.do?numeroOS=${helper.idOS}&atividadeId=${helper.atividade.id}', 550, 735);">
													<img title="Consultar Períodos/Equipes"
														src="<bean:message key='caminho.imagens'/>relogioTransparente.gif"
														border="0"></a>
												</c:if></div>
												</td>
												<td bordercolor="#90c7fc">
												<div align="center"><c:if test="${helper.material==true}">
													<a
														href="javascript:abrirPopup('/gsan/exibirDetalharMaterialUtilizadoPopupAction.do?numeroOS=${helper.idOS}&atividadeId=${helper.atividade.id}', 550, 735);">
													<img title="Consultar Materiais"
														src="<bean:message key='caminho.imagens'/>marteloTransparente3.gif"
														border="0"></a>
												</c:if></div>
												</td>
										</tr>
										</logic:iterate>
									</table>
									</td>
								</tr>

							</table>
							</div>

							</td>
						</tr>
					</c:when>

				</c:choose>
				
				<!-- Dados da Repavimentacao -->
				<c:if test="${ConsultarDadosOrdemServicoActionForm.unidadeRepavimentadoraId != null}">
				
					<tr bgcolor="#cbe5fe">
						<td align="center">
						<div id="layerHideRepavimentacao" style="display:block">
	
						<table width="100%" border="0" bgcolor="#99CCFF">
							<tr bgcolor="#99CCFF">
								<td align="center">
									<span class="style2"> 
									<a href="javascript:extendeTabela('Repavimentacao',true);" /> 
									<b>Dados de Repavimentação</b></a> 
									</span>
								</td>
							</tr>
						</table>
						
						</div>
	
						<div id="layerShowRepavimentacao" style="display:none">
	
						<table width="100%" border="0" bgcolor="#99CCFF">
							
							<tr bgcolor="#99CCFF">
								<td align="center">
									<span class="style2"> 
									<a href="javascript:extendeTabela('Repavimentacao',false);" /> 
									<b>Dados de Repavimentação</b></a>
									</span>
								</td>
							</tr>
	
							<tr bgcolor="#cbe5fe">
								<td>
								<table border="0" width="100%">
									<tr>
										<td>
											<strong>Unidade Repavimentadora:</strong>
										</td>
										
										<td colspan="3">
											<html:text property="unidadeRepavimentadoraId" 
												readonly="true"
												style="background-color:#EFEFEF; border:0;" 
												size="4"
												maxlength="4" /> 
											
											<html:text property="unidadeRepavimentadoraDescricao" 
												readonly="true"
												style="background-color:#EFEFEF; border:0;" size="40"
												maxlength="40" />
										</td>
									</tr>
											
									<tr>
										<td>
											<strong>Tipo Pavimento Rua:</strong>
										</td>
										
										<td>											
											<html:text property="tipoPavimentoRua" 
												readonly="true"
												style="background-color:#EFEFEF; border:0;" 
												size="15"
												maxlength="15" /> &nbsp;&nbsp;&nbsp;&nbsp; 
										</td>
										
										<td>
											<strong>Área Pavimento Rua:</strong>
										</td>
										
										<td>											
											<html:text property="areaPavimentoRua" 
												readonly="true"
												style="background-color:#EFEFEF; border:0;" 
												size="8"
												maxlength="8" /><strong>(m2)</strong>
										</td>
									</tr>
									
									
									<tr>
										<td>
											<strong>Tipo Pavimento Rua-Ret:</strong>
										</td>
	
										<td>
											<html:text property="tipoPavimentoRuaRet" 
												readonly="true"
												style="background-color:#EFEFEF; border:0;" 
												size="15"
												maxlength="15" />
										</td>
										
										<td>											
											<strong>Área Pavimento Rua-Ret:</strong>
										</td>
										
										<td>											
											<html:text property="areaPavimentoRuaRet" 
												readonly="true"
												style="background-color:#EFEFEF; border:0;" 
												size="8"
												maxlength="8" /><strong>(m2)</strong>
										</td>
									</tr>
									
									<tr>
										<td><strong>Tipo Pavimento Calçada:</strong></td>
	
										<td>
											<html:text property="tipoPavimentoCalcada" 
												readonly="true"
												style="background-color:#EFEFEF; border:0;" 
												size="15"
												maxlength="15" />
										</td>
										
										<td>											
											<strong>Área Pavimento Calçada:</strong>
										</td>
										
										<td>											
											<html:text property="areaPavimentoCalcada" 
												readonly="true"
												style="background-color:#EFEFEF; border:0;" 
												size="8"
												maxlength="8" /><strong>(m2)</strong>
										</td>
									</tr>
								
									<tr>
										<td><strong>Tipo Pavimento Calçada-Ret:</strong></td>
	
										<td>
											<html:text property="tipoPavimentoCalcadaRet" 
												readonly="true"
												style="background-color:#EFEFEF; border:0;" 
												size="15"
												maxlength="15" />
										</td>
										
										<td>											
											<strong>Área Pavimento Calçada-Ret:</strong>
										</td>
										
										<td>
											<html:text property="areaPavimentoCalcadaRet" 
												readonly="true"
												style="background-color:#EFEFEF; border:0;" 
												size="8"
												maxlength="8" /><strong>(m2)</strong>
										</td>
									</tr>									
									
									<tr>
										<td class="style3"><strong>Data Retorno:</strong></td>
										<td>
											<html:text property="dataRetornoRepavimentacao" 
												readonly="true"
												style="background-color:#EFEFEF; border:0;" 
												size="10"
												maxlength="10" />
										</td>
									</tr>
									
									<tr>
										<td colspan="1">
											<strong>Observação do Retorno:</strong>
										</td>
										
										<td colspan="3">
											<html:textarea property="observacaoRetornoRepavimentacao"
												readonly="true" 
												style="background-color:#EFEFEF; border:0;"
												cols="40" />
											
										</td>
									</tr>
									
									<tr>
										<td colspan="4"><p><HR><p><p></td>
								    </tr>
											
									<tr>
										<td><strong>Data Rejeição:</strong></td>
										<td>
											<html:text property="dataRejeicaoRepavimentacao" 
												readonly="true"
												style="background-color:#EFEFEF; border:0;" 
												size="10"
												maxlength="10" />
										</td>
									</tr>
									
									<tr>
										<td><strong>Motivo da Rejeição:</strong></td>
										<td>
											<html:text property="motivoRejeicaoRepavimentacao" 
												readonly="true"
												style="background-color:#EFEFEF; border:0;" 
												size="10"
												maxlength="10" />
										</td>
									</tr>
									
									<tr>
										<td colspan="1">
											<strong>Descrição da Rejeição:</strong>
										</td>
	
										<td colspan="3">
											<html:textarea property="descricaoRejeicaoRepavimentacao"
												readonly="true" 
												style="background-color:#EFEFEF; border:0;"
												cols="40" />
										</td>
									</tr>									
											
									<tr>
										<td colspan="4"><p><HR><p><p></td>
								    </tr>
									
									<tr>
										<td><strong>Situação do Aceite:</strong></td>
										<td>
											<html:text property="situacaoAceiteRepavimentacao" 
												readonly="true"
												style="background-color:#EFEFEF; border:0;" 
												size="10"
												maxlength="10" />
										</td>
									</tr>
									
									<tr>
										<td><strong>Data Aceite:</strong></td>
										<td>
											<html:text property="dataAceiteRepavimentacao" 
												readonly="true"
												style="background-color:#EFEFEF; border:0;" 
												size="10"
												maxlength="10" />
										</td>
									</tr>
									
									<tr>
										<td>
											<strong>Usuário do Aceite:</strong>
										</td>
										
										<td colspan="3">
											<html:text property="usuarioAceiteId" 
												readonly="true"
												style="background-color:#EFEFEF; border:0;" 
												size="15"
												maxlength="15" /> 
											
											<html:text property="usuarioAceiteNome" 
												readonly="true"
												style="background-color:#EFEFEF; border:0;" size="40"
												maxlength="40" />
										</td>
									</tr>
									
									<tr>
										<td colspan="1">
											<strong>Motivo do Aceite:</strong>
										</td>
	
										<td colspan="3">
											<html:textarea property="descricaoMotivoAceite"
												readonly="true" 
												style="background-color:#EFEFEF; border:0;"
												cols="40" />
										</td>
									</tr>									

											
								</table>
								</td>
							</tr>
							
							
						</table>
						</div>
						</td>
					</tr>
				</c:if>
				
				
				
				
				
				
				
				<tr>
					<td>
					<table width="100%">
						<tr>
							<td width="341"><strong> <font color="#FF0000"> <input
								type="button" name="Submit22342" class="bottonRightCol"
								value="Voltar"
								onclick="javascript:voltar('${caminhoRetornoOS}');"> </font> </strong>
							</td>
                            <c:if
								test="${caminhoRetornoOS != 'filtrarDocumentosCobrancaAction.do'}">
							<td width="272" align="right">
							<div align="right"><strong> <c:if
								test="${ConsultarDadosOrdemServicoActionForm.situacaoOSId != '2'}">
								<input name="btnAtualizar" type="button" class="bottonRightCol"
									value="Atualizar" onclick="javascript:atualizar();">
							</c:if> <c:if
								test="${ConsultarDadosOrdemServicoActionForm.situacaoOSId == '2'}">
								<input name="btnAtualizar" type="button" class="bottonRightCol"
									value="Atualizar" onclick="javascript:atualizar();"
									disabled="true">
							</c:if> <c:if
								test="${ConsultarDadosOrdemServicoActionForm.situacaoOSId != '2'
					                  							&& ConsultarDadosOrdemServicoActionForm.situacaoOSId != '4'}">
								<input name="btnEncerrar" type="button" class="bottonRightCol"
									value="Encerrar" onclick="javascript:encerrar();">

							</c:if> <c:if
								test="${ConsultarDadosOrdemServicoActionForm.situacaoOSId == '2'
					                  							|| ConsultarDadosOrdemServicoActionForm.situacaoOSId == '4'}">
								<input name="btnEncerrar" type="button" class="bottonRightCol"
									value="Encerrar" onclick="javascript:encerrar();"
									disabled="true">

							</c:if> <input name="btnImprimir" type="button"
								class="bottonRightCol" value="Imprimir"
								onclick="javascript:window.location.href='/gsan/gerarRelatorioOrdemServicoAction.do'">
							

							</strong></div>
							
							</td>
							</c:if>
						</tr>

					</table>

					</td>

				</tr>

			</table>
			</td>
		</tr>

	</table>
	<!-- Fim do Corpo - Leonardo Regis -->
	<%@ include file="/jsp/util/rodape.jsp"%>

</html:form>
</body>
</html:html>
