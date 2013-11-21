<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan" %>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page import="gcom.cadastro.imovel.Imovel"%>

<html:html>
	<head>
		<%@ include file="/jsp/util/titulo.jsp"%>
		<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
		<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
		
		<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
		<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
		<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js"></script>
		
		<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
		<html:javascript staticJavascript="false"  formName="TransferirImovelLogradouroActionForm" dynamicJavascript="false" />
		
		<script language="JavaScript">
		
			//Marcar e Desmarcar todos os checkbox
			function facilitador(objeto) {
				if (objeto.value == "0"){
			  		objeto.value = "1";
			  		marcarTodos();
			 	}
			 	else{
			  		objeto.value = "0";
			  		desmarcarTodos();
			 	}
			}
			
			//Recebe os dados do Popup
			function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
				var form = document.TransferirImovelLogradouroActionForm;
				
				var logradouroTipo = document.getElementById('logradouroOrigemOuDestino').value;
				
				if (tipoConsulta == 'logradouro') {
					if(logradouroTipo == 'logradouroOrigem'){
						form.logradouroImovelOrigemFiltro.value = codigoRegistro;
						form.descricaoLogradouroImovelOrigemFiltro.value = descricaoRegistro;
						form.descricaoLogradouroImovelOrigemFiltro.style.color = "#000000";
						form.logradouroImovelOrigemFiltro.focus();
					} else if(logradouroTipo == 'logradouroDestino'){
						form.logradouroImovelDestinoFiltro.value = codigoRegistro;
						form.descricaoLogradouroImovelDestinoFiltro.value = descricaoRegistro;
						form.descricaoLogradouroImovelDestinoFiltro.style.color = "#000000";
						form.logradouroImovelDestinoFiltro.focus();
						form.action = "exibirTransferirImovelLogradouroAction.do";
						form.submit();
					}
			    }
			}
			
			function limparPesquisaLogradouroOrigem(){
				var form = document.TransferirImovelLogradouroActionForm;
			    form.logradouroImovelOrigemFiltro.value = "";
			    form.descricaoLogradouroImovelOrigemFiltro.value = "";
			    form.action = "pesquisarImovelLogradouroAction.do";
      			form.submit();
			}
			
			function limparPesquisaLogradouroDestino(){
				var form = document.TransferirImovelLogradouroActionForm;
			    form.logradouroImovelDestinoFiltro.value = "";
			    form.descricaoLogradouroImovelDestinoFiltro.value = "";
				form.action = "pesquisarImovelLogradouroAction.do";
      			form.submit();
			}
			 
			function validateInformarImovelTransferenciaLogradouroActionForm() {
				var form = document.TransferirImovelLogradouroActionForm;
				if(form.logradouroImovelOrigemFiltro.value == "") {
					alert("Informe o logradouro");
				}
				else {
					form.submit();
				}
			}
			
			//Validar a Pesquisa dos Imóveis
			function validarPesquisar(){
				var form = document.TransferirImovelLogradouroActionForm;
				if(form.logradouroImovelOrigemFiltro.value == "") {
					alert ("Informe o logradouro");
				}
				else{
					form.action = "pesquisarImovelLogradouroAction.do";
      				form.submit();
				}
			}
			
			function setLogradouroTipo(logradouroOrigemOuDestino) {
				document.getElementById('logradouroOrigemOuDestino').value = logradouroOrigemOuDestino;
			}
			
			//Validar a Transferência de Imóveis
			function validarTransferirImovel(){
				var mensagem = "Nenhum registro selecionado para transferência.";
				if(CheckboxNaoVazioMensagemGenerico(mensagem,document.TransferirImovelLogradouroActionForm.idsRegistro) && confirm('Confirma transferência ?')){
					var form = document.TransferirImovelLogradouroActionForm;
					form.action = "transferirImovelLogradouroAction.do";
				    form.submit();	
				}
			}
		
		</script>
	</head>
	<body leftmargin="5" topmargin="5"
		onload="javascript:setarFoco('logradouroImovelOrigemFiltro');">
	
		<input type="hidden" id="logradouroOrigemOuDestino" value="" />
		
		<html:form action="/exibirTransferirImovelLogradouroAction.do"
			name="TransferirImovelLogradouroActionForm"
			type="gcom.gui.cadastro.imovel.TransferirImovelLogradouroActionForm"
			method="post"
			onsubmit="return validateInformarImovelTransferenciaLogradouroActionForm(this);">
			
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
						<!--Início Tabela Reference a Páginação da Tela de Processo-->
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
							<td class="parabg">Transferir Imovéis Para Outros Logradouros</td>
							<td width="11"><img border="0"
								src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
						</tr>
						<table border="0" align="left">
							<tr>
								<td>&nbsp;</td>
							</tr>
							<tr>
								<td width="800">Preencher os Campos Para Efetuar a Transferência dos Imovéis do Logradouro</td>
							</tr>
							<tr>
								<td>&nbsp;</td>
							</tr>
						</table>
						<table border="0" width="100%">
							<tr>
								<td colspan="4">
								<table align="center" bgcolor="#99ccff" border="0" width="100%">
									<tr>
										<td align="left"><strong>Origem dos Imóveis Transferidos</strong></td>
									</tr>
								</table>
								<table align="left" border="0" width="100%">
									<tr bgcolor="#cbe5fe">
										<td align="center" width="100%">
										<table border="0" width="100%">
											<tr>
												<td bordercolor="#000000" width="80">
													<strong>Logradouro:<font color="#ff0000">*</font></strong>
												</td>
												<td>
													<html:text name="TransferirImovelLogradouroActionForm" property="logradouroImovelOrigemFiltro" size="9" maxlength="9"
														onkeypress="validaEnter(event, 'exibirTransferirImovelLogradouroAction.do', 'logradouroImovelOrigemFiltro'); return isCampoNumerico(event);"/>
													<a href="javascript:abrirPopup('/gsan/exibirPesquisarLogradouroAction.do', 465, 380);" onclick="javascript:setLogradouroTipo('logradouroOrigem');">
														<img src="<bean:message key='caminho.imagens'/>pesquisa.gif" width="23" height="21" border="0" alt="Pesquisar">
													</a>
													<logic:present name="idLogradouroNaoEncontrado">
														<logic:equal name="idLogradouroNaoEncontrado" value="true">
															<html:text property="descricaoLogradouroImovelOrigemFiltro" size="40" maxlength="30" readonly="true"
																style="background-color:#EFEFEF; border:0; color: #ff0000" />
														</logic:equal>
														<logic:notEqual name="idLogradouroNaoEncontrado" value="true">
															<html:text property="descricaoLogradouroImovelOrigemFiltro" size="40" maxlength="30" readonly="true"
																style="background-color:#EFEFEF; border:0; color: #000000" />
														</logic:notEqual>
													</logic:present>
													<logic:notPresent name="idLogradouroNaoEncontrado">
														<logic:empty name="TransferirImovelLogradouroActionForm" property="logradouroImovelOrigemFiltro">
															<html:text property="descricaoLogradouroImovelOrigemFiltro" size="40" maxlength="30" readonly="true"
																style="background-color:#EFEFEF; border:0; color: #ff0000" />
														</logic:empty>
														<logic:notEmpty name="TransferirImovelLogradouroActionForm" property="logradouroImovelOrigemFiltro">
															<html:text property="descricaoLogradouroImovelOrigemFiltro" size="40"	maxlength="30" readonly="true"
																style="background-color:#EFEFEF; border:0; color: #000000" />
														</logic:notEmpty>
													</logic:notPresent>
													<a href="javascript:limparPesquisaLogradouroOrigem();">
														<img src="<bean:message key="caminho.imagens"/>limparcampo.gif" border="0" title="Apagar"/>
													</a>
													<input  name="Submit22" class="bottonRightCol" value="Pesquisar" type="button" onclick="javascript:validarPesquisar();">
												</td>
											</tr>
										</table>
										</td>
									</tr>
									<tr>
										<td>
										<table border="0" width="90%">
											<tr>
												<hr style="width: 100%; height: 2px;" />
											</tr>
											<tr>
												<td align="left" width="400">Selecionar Imovéis para Transferência</td>
												<logic:present scope="application" name="urlHelp">
													<td align="right"><a href="javascript:abrirPopupHelp('${applicationScope.urlHelp}', 500, 700);"><span style="font-weight: bold"><font color="#3165CE">Ajuda</font></span></a></td>									
												</logic:present>
												<logic:notPresent scope="application" name="urlHelp">
													<td align="right"><span style="font-weight: bold"><font color=#696969><u>Ajuda</u></font></span></td>
												</logic:notPresent>
											</tr>
										</table>
										</td>
									</tr>
									<tr>
										<td>
										<table border="0" width="100%" class="fontePequena">
											<tr>
												<td height="31">
													<div align="left" style="max-height: 300px; overflow-y: scroll; ">
														<table width="100%" bgcolor="#90c7fc" class="fontePequena">
															<!--header da tabela interna -->
															<logic:present name="collImoveis">
																<thead>
																	<tr bordercolor="#FFFFFF" bgcolor="#79BBFD"> 
																		<td bgcolor="#90c7fc" width="2%">
																		<div align="center"><strong><u style="cursor: pointer;" onclick="javascript: facilitador(this);">Todos</u></strong></div>
																		</td>
																		<td bgcolor="#90c7fc" width="12%">
																			<div align="center"><strong>Matrícula</strong></div>
																		</td>
																		<td bgcolor="#90c7fc" width="86%">
																		<div align="center"><strong>Endereço Imóvel</strong></div>
																		</td>
																	</tr>
																</thead>
																<tbody>
																	<%int cont = 0;%>
																		<logic:iterate name="collImoveis" scope="session" id="imovel">
																				<%cont = cont + 1;
																				if (cont % 2 == 0) {%>
																				<tr bgcolor="#cbe5fe">
																				<%} else { %>
																				<tr bgcolor="#FFFFFF">
																					<%}%>
																					<td width="25">
																						<% 
																						String data = "";
																						String id = "";
																						
																						if(((Imovel)imovel).getUltimaAlteracao() != null){
																							data = new Long(((Imovel)imovel).getUltimaAlteracao().getTime()).toString();	 
																						 }	
																						 
																						 id = ((Imovel)imovel).getId().toString();
																						
																						%>
																						<div align="center" >
																							<input type="checkbox" name="idsRegistro"  value="<%=id + "-" + data%>" />
																						</div>
																					</td>
																					<td width="45">
																						<div >
																							<bean:write name="imovel" property="id" />
																						</div>
																					</td>
																					<td width="530">
																						<div >
																							<html:link page="/exibirAtualizarImovelAction.do" paramName="imovel" 
																								paramProperty="id" paramId="idRegistroTransferencia">
																								<bean:write name="imovel" property="enderecoFormatado" />
																							</html:link>
																						</div>
																					</td>
																				</tr>
																		</logic:iterate>
																</tbody>
															</logic:present>
														</table>
													</div>
												</td>
											</tr>
										</table>
										</td>	
									</tr>
									<tr>
										<table border="0" width="100%">
											<tr>
												<td colspan="4">
												<table align="center" bgcolor="#99ccff" border="0" width="100%">
													<tr>
														<hr style="width: 100%; height: 5px;" />
													</tr>
													<tr>
														<td align="left"><strong>Destino dos Imóveis Transferidos</strong></td>
													</tr>
												</table>
												</td>
											</tr>
											<tr bgcolor="#cbe5fe">
												<td align="center" width="100%">
												<table border="0" width="100%">
													<tr>
														<td bordercolor="#000000" width="80">
															<strong>Logradouro:<font color="#ff0000">*</font></strong>
														</td>
														<td>
															<html:text name="TransferirImovelLogradouroActionForm" property="logradouroImovelDestinoFiltro" size="9" maxlength="9"
																onkeypress="validaEnter(event, 'exibirTransferirImovelLogradouroAction.do', 'logradouroImovelDestinoFiltro'); return isCampoNumerico(event);"/>
															<a href="javascript:abrirPopup('/gsan/exibirPesquisarLogradouroAction.do', 465, 380);" onclick="javascript:setLogradouroTipo('logradouroDestino');">	
																<img src="<bean:message key='caminho.imagens'/>pesquisa.gif" width="23" 
																	height="21" border="0" alt="Pesquisar">
																</a>
																<logic:present name="idLogradouroNaoEncontrado">
																	<logic:equal name="idLogradouroNaoEncontrado" value="true">
																		<html:text property="descricaoLogradouroImovelDestinoFiltro" size="40" maxlength="30" readonly="true"
																			style="background-color:#EFEFEF; border:0; color: #ff0000" />
																	</logic:equal>
																	<logic:notEqual name="idLogradouroNaoEncontrado" value="true">
																		<html:text property="descricaoLogradouroImovelDestinoFiltro" size="40" maxlength="30" readonly="true"
																			style="background-color:#EFEFEF; border:0; color: #000000" />
																	</logic:notEqual>
																</logic:present>
																<logic:notPresent name="idLogradouroNaoEncontrado">
																	<logic:empty name="TransferirImovelLogradouroActionForm" property="logradouroImovelDestinoFiltro">
																		<html:text property="descricaoLogradouroImovelDestinoFiltro" size="40" maxlength="30" readonly="true"
																		style="background-color:#EFEFEF; border:0; color: #ff0000" />
																	</logic:empty>
																	<logic:notEmpty name="TransferirImovelLogradouroActionForm" property="logradouroImovelDestinoFiltro">
																		<html:text property="descricaoLogradouroImovelDestinoFiltro" size="40"	maxlength="30" readonly="true"
																		style="background-color:#EFEFEF; border:0; color: #000000" />
																	</logic:notEmpty>
																</logic:notPresent>
															<a href="javascript:limparPesquisaLogradouroDestino();">
																<img src="<bean:message key="caminho.imagens"/>limparcampo.gif" border="0" title="Apagar"/></a>
														</td>
													</tr>
													<tr>
														<td bordercolor="#000000" width="80">
															<strong>Bairro:</strong>
														</td>
														<td>
															<html:select name="TransferirImovelLogradouroActionForm" property="bairroImovelFiltro" tabindex="3">
																<html:option value="-1">&nbsp;</html:option>
																<logic:present name="colecaoBairros">
																	<html:options collection="colecaoBairros" labelProperty="nome" property="id" />
																</logic:present>
															</html:select>
														</td>
													</tr>
													<tr>
														<td bordercolor="#000000" width="80">
															<strong>CEP:</strong>
														</td>
														<td>
															<html:select name="TransferirImovelLogradouroActionForm" property="cepImovelFiltro" tabindex="3">
																<html:option value="-1">&nbsp;</html:option>
																<logic:present name="colecaoCep">
																	<html:options collection="colecaoCep" labelProperty="codigo" property="cepId" />
																</logic:present>
															</html:select>
														</td>
													</tr>
													<tr>
														<td>&nbsp;</td>
													</tr>
													<tr>
														<td align="left">&nbsp;
															<input name="Submit23" class="bottonRightCol" value="Cancelar" type="button" onclick="window.location.href='/gsan/telaPrincipal.do'">
														</td>
														<td align="right">&nbsp;
															<input name="Submit21" class="bottonRightCol" value="Transferir" type="button" onclick="javascript:validarTransferirImovel();">
														</td>
													</tr>
												</table>
												</td>
											</tr>
										</table>
									</tr>
								</table>
							</tr>
						</table>
					</table>
					</td>
				</tr>
			</table>
			<%@ include file="/jsp/util/rodape.jsp"%>
		</html:form>
	</body>
</html:html>
