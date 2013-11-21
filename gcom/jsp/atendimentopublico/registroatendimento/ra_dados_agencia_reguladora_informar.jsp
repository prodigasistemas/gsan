<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ page import="gcom.atendimentopublico.registroatendimento.RaDadosAgenciaReguladoraFone"%>
<%@ page import="gcom.gui.GcomAction"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

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


<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/regras_validator.js">
</script>
<html:javascript staticJavascript="false"
	formName="InformarRaDadosAgenciaReguladoraActionForm" />

<script language="JavaScript">
	
   function validarForm() {
    var form = document.forms[0];
	  if(validateInformarRaDadosAgenciaReguladoraActionForm(form)){		
	  	       
		  submeterFormPadrao(form);   		  
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
   
   	/* PESQUISAR A RA */
	function validarRA(){
	
		var form = document.forms[0];
		
		if (form.numeroRA.value == ''){
			alert('Informe o número do RA.');
		}else{
			form.action = "exibirInformarRaDadosAgenciaReguladoraAction.do?pesquisarRa=OK";
			submeterFormPadrao(form);
		}
	}
	
	function removerFone(objetoRemocao){
  
  	redirecionarSubmit("removerRaDadosAgenciaReguladoraFonePopupAction.do?timestamp=" + objetoRemocao);
  	}
  	
  	function consultarTramites() {
		var form = document.forms[0];
		abrirPopup('exibirConsultarRegistroAtendimentoTramiteAction.do?numeroRA='+form.numeroRA.value, 550, 735);
	}
  
  	function limparForm(){
	
		var form = document.forms[0];
			window.location.href = "exibirInformarRaDadosAgenciaReguladoraAction.do?menu=sim";
	}
	
</script>
</head>

<body leftmargin="5" topmargin="5" onload="setarFoco('${requestScope.nomeCampo}');">

<html:form action="/informarRaDadosAgenciaReguladoraAction.do"
		  name="InformarRaDadosAgenciaReguladoraActionForm"
		type="gcom.gui.atendimentopublico.registroatendimento.InformarRaDadosAgenciaReguladoraActionForm"
		method="post"
		onsubmit="return validateInformarRaDadosAgenciaReguladoraActionForm(this);">

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

			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_left.gif" /></td>
					<td class="parabg">Informar Dados do RA pela Agência Reguladora</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>

			<p>&nbsp;</p>
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
									<div align="center"><span class="style2"><b>Pesquisar outro
									Registro de Atendimento</b></span></div>
									</td>
								</tr>

								<tr bgcolor="#cbe5fe">
									<td>
									<table border="0" width="100%">

										<tr>
											<td height="10" width="22%"><strong>N&uacute;mero do RA:<font colo*="#FF0000">*</font> </strong></td>

											<td>
												<html:text property="numeroRA" size="9" maxlength="9" 
													onkeyup="javascript:return validaEnter(event, 'exibirInformarRaDadosAgenciaReguladoraAction.do?pesquisarRa=OK', 'numeroRA');"/>
												<input type="button" class="bottonRightCol" value="Pesquisar"
													style="width: 80px" onclick="validarRA();"/>
													
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
					<td height="31">
					<table width="100%" border="0" align="center">
						<tr bgcolor="#cbe5fe">
							<td align="center">

							<div id="layerHideRegistroAtendimento" style="display:block">
							<table width="100%" border="0" bgcolor="#99CCFF">
								<tr bgcolor="#99CCFF">
									<td align="center"><span class="style2"> <a
										href="javascript:extendeTabela('RegistroAtendimento',true);" />
									<b> Dados do Registro de Atendimento</b> </a> </span></td>
								</tr>
							</table>
							</div>

							<div id="layerShowRegistroAtendimento" style="display:none">
							<table width="100%" border="0" bgcolor="#99CCFF">
								<tr bgcolor="#99CCFF">
									<td align="center"><span class="style2"> <a
										href="javascript:extendeTabela('RegistroAtendimento',false);" />
									<b>Dados do Registro de Atendimento</b> </a> </span></td>
								</tr>

								<tr bgcolor="#cbe5fe">
									<td>
									<table border="0" width="100%">

										<tr>
											<td colspan="2">
											<table width="100%">
												<tr>
													<td height="10" width="27%"><strong>N&uacute;mero do RA:</strong></td>
													<td><html:text property="numeroRADados" readonly="true"
														style="background-color:#EFEFEF; border:0;" size="9"
														maxlength="9" /><strong>Situação do RA:</strong> <html:text
														property="situacaoRA" readonly="true"
														style="background-color:#EFEFEF; border:0;" size="15"
														maxlength="15" /></td>
												</tr>
											</table>
											</td>
										</tr>
										<c:if
											test="${InformarRaDadosAgenciaReguladoraActionForm.numeroRaAssociado != null}">
											<tr>
												<td height="10"><strong>N&uacute;mero do RA Associado:</strong></td>
												<td><html:text property="numeroRaAssociado" readonly="true"
													style="background-color:#EFEFEF; border:0;" size="9"
													maxlength="9" /> <strong>Situa&ccedil;&atilde;o do RA
												Associado:</strong> <html:text
													property="situacaoRaAssociado" readonly="true"
													style="background-color:#EFEFEF; border:0;" size="12"
													maxlength="9" /></td>
											</tr>
										</c:if>

										<tr>
											<td class="style3"><strong>Tipo de Solicita&ccedil;&atilde;o:</strong></td>
											<td colspan="3"><html:text property="tipoSolicitacaoId"
												readonly="true" style="background-color:#EFEFEF; border:0;"
												size="4" maxlength="4" /> <html:text
												property="tipoSolicitacaoDescricao" readonly="true"
												style="background-color:#EFEFEF; border:0;" size="50"
												maxlength="50" /></td>
										</tr>

										<tr>
											<td height="10"><strong>Especifica&ccedil;&atilde;o:</strong></td>
											<td colspan="3"><html:text property="especificacaoId"
												readonly="true" style="background-color:#EFEFEF; border:0;"
												size="4" maxlength="4" /> <html:text
												property="especificacaoDescricao" readonly="true"
												style="background-color:#EFEFEF; border:0;" size="50"
												maxlength="50" /></td>
										</tr>

										<tr>
											<td class="style3"><strong>Meio de Solicita&ccedil;&atilde;o:</strong></td>
											<td colspan="3"><html:text
												property="meioSolicitacaoDescricao" readonly="true"
												style="background-color:#EFEFEF; border:0;" size="50"
												maxlength="50" /></td>
										</tr>

										<tr>
											<td><strong>Matr&iacute;cula do Im&oacute;vel:</strong></td>
											<td colspan="3"><html:text property="matriculaImovel"
												readonly="true" style="background-color:#EFEFEF; border:0;"
												size="8" maxlength="8" /> <html:text
												property="inscricaoImovel" readonly="true"
												style="background-color:#EFEFEF; border:0;" size="21"
												maxlength="21" /></td>
										</tr>

										<tr>
											<td width="31%" height="10"><strong>Data e Hora do
											Atendimento:</strong></td>
											<td colspan="3"><html:text property="dataAtendimento"
												readonly="true" style="background-color:#EFEFEF; border:0;"
												size="10" maxlength="10" /> <html:text
												property="horaAtendimento" readonly="true"
												style="background-color:#EFEFEF; border:0;" size="5"
												maxlength="5" /></td>
										</tr>

										<tr>
											<td class="style3"><strong>Data Prevista:</strong></td>
											<td colspan="3"><html:text property="dataPrevista"
												readonly="true" style="background-color:#EFEFEF; border:0;"
												size="10" maxlength="10" /></td>
										</tr>

										<tr>
											<td class="style3"><strong>Data de Encerramento:</strong></td>

											<td><html:text property="dataEncerramento" readonly="true"
												style="background-color:#EFEFEF; border:0;" size="10"
												maxlength="10" /></td>
										</tr>

										<tr>
											<td class="style3"><strong>Motivo do Encerramento:</strong></td>
											<td><html:text property="idMotivoEncerramento"
												readonly="true" style="background-color:#EFEFEF; border:0;"
												size="5" maxlength="5" /> <html:text
												property="motivoEncerramento" readonly="true"
												style="background-color:#EFEFEF; border:0;" size="50"
												maxlength="50" /></td>
										<tr>
											<td height="10" colspan="4">
											<div align="right">
											<hr>
											</div>
											<div align="right"></div>
											</td>
										</tr>

										<tr>
											<td class="style3"><strong>Cliente Solicitante:</strong></td>
											<td colspan="3"><html:text property="idClienteSolicitante"
												readonly="true" style="background-color:#EFEFEF; border:0;"
												size="4" maxlength="4" /> <html:text
												property="clienteSolicitante" readonly="true"
												style="background-color:#EFEFEF; border:0;" size="40"
												maxlength="40" /></td>
										</tr>

										<tr>
											<td class="style3"><strong>Unidade Solicitante:</strong></td>
											<td colspan="3"><html:text property="idUnidadeSolicitante"
												readonly="true" style="background-color:#EFEFEF; border:0;"
												size="4" maxlength="4" /> <html:text
												property="unidadeSolicitante" readonly="true"
												style="background-color:#EFEFEF; border:0;" size="40"
												maxlength="40" /></td>
										</tr>

										<tr>
											<td class="style3"><strong>Nome do Solicitante:</strong></td>
											<td colspan="3"><html:text property="nomeSolicitante"
												readonly="true" style="background-color:#EFEFEF; border:0;"
												size="40" maxlength="40" /></td>
										</tr>

										<tr>
											<td height="10" colspan="4">
											<div align="right">
											<hr>
											</div>
											<div align="right"></div>
											</td>
										</tr>

										<tr>
											<td class="style3"><strong><span class="style2">Endere&ccedil;o
											da Ocorr&ecirc;ncia:</span></strong></td>
											<td><html:textarea property="enderecoOcorrencia"
												readonly="true" style="background-color:#EFEFEF; border:0;"
												cols="50" /></td>
										</tr>

										<tr>
											<td class="style3"><strong>Ponto de Refer&ecirc;ncia:</strong></td>

											<td><html:text property="pontoReferencia" readonly="true"
												style="background-color:#EFEFEF; border:0;" size="60"
												maxlength="60" /></td>
										</tr>

										<tr>
											<td class="style3"><strong>Bairro:</strong></td>
											<td><html:text property="bairroId" readonly="true"
												style="background-color:#EFEFEF; border:0;" size="4"
												maxlength="4" /> <html:text property="bairroDescricao"
												readonly="true" style="background-color:#EFEFEF; border:0;"
												size="40" maxlength="40" /></td>
										</tr>

										<tr>
											<td class="style3"><strong>&Aacute;rea do Bairro:</strong></td>
											<td><html:text property="areaBairroId" readonly="true"
												style="background-color:#EFEFEF; border:0;" size="4"
												maxlength="4" /> <html:text property="areaBairroDescricao"
												readonly="true" style="background-color:#EFEFEF; border:0;"
												size="40" maxlength="40" /></td>
										</tr>

										<tr>
											<td class="style3"><strong>Local/Setor/Quadra:</strong></td>
											<td><html:text property="localidadeId" readonly="true"
												style="background-color:#EFEFEF; border:0;" size="4"
												maxlength="4" /> <strong>/</strong> <html:text
												property="setorComercialCodigo" readonly="true"
												style="background-color:#EFEFEF; border:0;" size="4"
												maxlength="4" /> <strong>/</strong> <html:text
												property="quadraNumero" readonly="true"
												style="background-color:#EFEFEF; border:0;" size="4"
												maxlength="4" /></td>
										</tr>

										<tr>
											<td class="style3"><strong>Divis&atilde;o de Esgoto:</strong>
											</td>
											<td colspan="3"><html:text property="divisaoEsgotoId"
												readonly="true" style="background-color:#EFEFEF; border:0;"
												size="4" maxlength="4" /> <html:text
												property="divisaoEsgotoDescricao" readonly="true"
												style="background-color:#EFEFEF; border:0;" size="40"
												maxlength="40" /></td>
										</tr>

										<tr>
											<td height="10" colspan="4">
											<div align="right">
											<hr>
											</div>
											<div align="right"></div>
											</td>
										</tr>


										<tr>
											<td class="style3"><strong>Unidade de Atendimento:</strong></td>
											<td colspan="3"><html:text property="unidadeAtendimentoId"
												readonly="true" style="background-color:#EFEFEF; border:0;"
												size="4" maxlength="4" /> <html:text
												property="unidadeAtendimentoDescricao" readonly="true"
												style="background-color:#EFEFEF; border:0;" size="40"
												maxlength="40" /></td>
										</tr>

										<tr>
											<td class="style3"><strong>Unidade Atual:</strong></td>
											<td colspan="3"><html:text property="unidadeAtualId"
												readonly="true" style="background-color:#EFEFEF; border:0;"
												size="4" maxlength="4" /> <html:text
												property="unidadeAtualDescricao" readonly="true"
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
					</table>
					</td>
				</tr>
				<tr>
					<td height="10" colspan="2">Para informar a reclamação do registro
					de atendimento, informe os dados abaixo:</td>
				</tr>
				<tr>
					<td height="31">
					<table width="100%" border="0" align="center">

						<tr bgcolor="#cbe5fe">

							<td align="center">
							<table width="100%" border="0" bgcolor="#99CCFF">

								<tr bgcolor="#99CCFF">
									<td height="18" colspan="2">
									<div align="center"><span class="style2"><b> Dados da
									Reclamação</b></span></div>
									</td>
								</tr>

								<tr bgcolor="#cbe5fe">
									<td>
									<table border="0" width="100%">
										<tr>
											<td height="10"><strong>Motivo da Reclamação:<font
												color="#FF0000">*</font></strong></td>
											<td width="70%" colspan="2"><html:select
												property="motivoReclamacao" tabindex="1"
												style="width:200px;">
												<html:option value="-1"> &nbsp; </html:option>
												<html:options collection="colecaoMotivoReclamacao"
													property="id" labelProperty="descricao" />
											</html:select></td>
										</tr>

										<tr>
											<td width="30%" class="style3"><strong>Número do Registro na
											Agência Reguladora:<font color="#FF0000">*</font></strong></td>
											<td width="70%" colspan="2"><strong><b><span class="style2">
											<html:text property="numeroRegistroAgenciaReguladora"
												size="22" maxlength="20" tabindex="2" /> </span></b></strong></td>
										</tr>

										<tr>
											<td width="30%" class="style3">
												<strong>Data de Previsão Original:<font color="#FF0000">*</font></strong></td>
											<td width="70%" colspan="2">
												<html:text property="dataPrevisaoOriginal" size="10" maxlength="10"
												tabindex="3" onkeyup="mascaraData(this,event)" 
												style="background-color:#EFEFEF; border:0; color: #000000;" readonly="true"/> dd/mm/aaaa</td>
										</tr>

										<tr>
											<td width="30%" class="style3"><strong>Data de Previsão
											Atual:<font color="#FF0000">*</font></strong></td>
											<td width="70%" colspan="2"><html:text
												property="dataPrevisaoAtual" size="10" maxlength="10"
												tabindex="4" onkeyup="mascaraData(this,event)" /> <a
												href="javascript:abrirCalendario('InformarRaDadosAgenciaReguladoraActionForm', 'dataPrevisaoAtual')">
											<img border="0"
												src="<bean:message key="caminho.imagens"/>calendario.gif"
												width="20" border="0" align="absmiddle"
												alt="Exibir Calendário" /></a> dd/mm/aaaa</td>
										</tr>

										<tr>
											<td td width="30%" class="style3"><strong>Reclamação:</strong></td>
											<td width="70%" colspan="2"><html:textarea
												property="reclamacao" tabindex="5" cols="45" rows="4"
												onkeyup=" validarTamanhoMaximoTextArea(this,400);" /></td>
										</tr>
										<tr>
											<td height="10" colspan="4">
											<div align="right">
											<hr>
											</div>
											<div align="right"></div>
											</td>
										</tr>
										
										<tr>
									         <td colspan="3">
									
												<table width="100%" border="0">
										      	<tr>
										      		<td><strong>Fones do Reclamante:</strong></td>
										      		<td align="right">
										      		
										      			<input type="button" class="bottonRightCol" value="Adicionar" tabindex="6" id="botaoFone" 
										      				onclick="abrirPopupComSubmit('exibirAdicionarRaDadosAgenciaReguladoraFonePopupAction.do?limparForm=1', 330, 630, 'Fone');">
										      		
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
									
												<logic:present name="collectionRaDadosAgenciaReguladoraFone">
									
												<tr>
													<td>
														<table width="100%" align="center" bgcolor="#99CCFF">
															<!--corpo da segunda tabela-->
									
															<% String cor = "#cbe5fe";%>
									
															<logic:iterate name="collectionRaDadosAgenciaReguladoraFone" id="raDadosAgenciaReguladoraFone" type="RaDadosAgenciaReguladoraFone">
															
																<%	if (cor.equalsIgnoreCase("#cbe5fe")){	
																	cor = "#FFFFFF";%>
																	<tr bgcolor="#FFFFFF" height="18">	
																<%} else{	
																	cor = "#cbe5fe";%>
																	<tr bgcolor="#cbe5fe" height="18">		
																<%}%>
															
																	<td width="10%" align="center">
																	
																				<a href="javascript:if(confirm('Confirma remoção?')){removerFone('<%="" + GcomAction.obterTimestampIdObjeto(raDadosAgenciaReguladoraFone) %>');}" alt="Remover"><img src="<bean:message key='caminho.imagens'/>Error.gif" width="14" height="14" border="0"></a>
																	</td>
																	
																	<td width="20%" align="center">
																		
																		<logic:equal name="raDadosAgenciaReguladoraFone" property="indicadoFonePadrao" value="1">
																			<input type="radio" name="clienteFoneSelected" value="<%="" + GcomAction.obterTimestampIdObjeto(raDadosAgenciaReguladoraFone) %>" checked/>
																		</logic:equal>
																		
																		<logic:notEqual name="raDadosAgenciaReguladoraFone" property="indicadoFonePadrao" value="1">
																			<input type="radio" name="clienteFoneSelected" value="<%="" + GcomAction.obterTimestampIdObjeto(raDadosAgenciaReguladoraFone) %>"/>
																		</logic:notEqual>
																		
																	</td>
																	
																	<td width="35%" align="left">
																		(<bean:write name="raDadosAgenciaReguladoraFone" property="ddd"/>)<bean:write name="raDadosAgenciaReguladoraFone" property="fone"/>
																	</td>
																	
																	<td width="35%" align="left">
																		<bean:write name="raDadosAgenciaReguladoraFone" property="foneTipo.descricao"/>
																	</td>
																</tr>
															</logic:iterate>
									
														</table>
												  	</td>
												</tr>
									
												</logic:present>
												<tr>
													<td width="500" colspan="2">
													<div align="center" ><font color="#FF0000">*</font> Campos obrigatórios</div>
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
					<table width="100%">
						<tr>
							<td width="40%" align="left">
								<input type="button" name="ButtonReset" class="bottonRightCol" value="Desfazer"
								onclick="javascript:limparForm();">

								<input type="button" name="ButtonCancelar" class="bottonRightCol"
									value="Cancelar" onClick="javascript:window.location.href='/gsan/telaPrincipal.do'">
							</td>
							
							<td align="right">
								<input name="ButtonTramites" type="button" class="bottonRightCol"  value="Consultar Trâmites" 
					                  onClick="javascript:consultarTramites();">

							<input type="button" name="Button" class="bottonRightCol"
									value="Inserir" onclick="javascript:validarForm();" tabindex="7" />
							</td>
						</tr>
					</table>
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
