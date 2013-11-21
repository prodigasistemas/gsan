<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<%@ page import="gcom.util.ConstantesSistema" isELIgnored="false"%>

<%@ page
	import="gcom.cadastro.cliente.ClienteRelacaoTipo,gcom.cadastro.cliente.ClienteImovelEconomia"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false"
	formName="EconomiaPopupActionForm" />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript"><!--
 Begin



function verificarAdicionar() {

	var form = document.forms[0];
	if (testarCampoValorZero(document.EconomiaPopupActionForm.idCliente, 'Cliente') && validateCaracterEspecial(form) && validateLong(form) ) {
		if(form.idCliente.value != ''){
			if (form.clienteRelacaoTipo.value != '-1'){
				if(form.dataInicioClienteImovelRelacao.value != ''){
					if(validateDate(form)){
						form.action='adicionarInformarEconomiaPopupAction.do';
						if (form.colecaoCliente != ""){
							form.submit();
						} else{
							alert ("Informe Cliente.")
						}
					}
				} else {
					alert("Informe Data Inicio Relação.");
				}
			}else{
				if (form.dataInicioClienteImovelRelacao.value == ''){
					alert("Informe Tipo Relação. \n Informe Data Inicio Relação.");
				}else{
					alert("Informe Tipo Relação.");
				}
			}
		}else{
			if (form.clienteRelacaoTipo.value == '-1'){
				if (form.dataInicioClienteImovelRelacao.value == ''){
					alert("Informe Código. \n Informe Tipo Relação. \n Informe Data Inicio Relação.");
				} else {
					alert("Informe Código. \n Informe Tipo Relação.");
				}
			} else{
				if (form.dataInicioClienteImovelRelacao.value == ''){
					alert("Informe Código. \n Informe Data Inicio Relação.");
				} else {
					alert("Informe Código.");
				}
			}
		} 
	}
}




function DateValidations () {
    this.aa = new Array("dataInicioClienteImovelRelacao", "Data de início relação não é uma data válida.", new Function ("varName", "this.datePattern='dd/MM/yyyy';  return this[varName];"));
}

function desabilitaCampoAreaConstruida(){
	
	var form = document.forms[0];
	 if(form.idAreaConstruidaFaixa.value == '-1' && form.areaConstruida.value == ''){
    	form.idAreaConstruidaFaixa.disabled = false;
      	form.areaConstruida.disabled = false;
    }else if(form.idAreaConstruidaFaixa.value != '-1' && form.areaConstruida.value == ''){
      	form.idAreaConstruidaFaixa.disabled = false;
      	form.areaConstruida.disabled = true;
    }else  if (form.areaConstruida.value != '' && form.idAreaConstruidaFaixa.value == '-1'){
    	form.idAreaConstruidaFaixa.value = '-1';
      	form.idAreaConstruidaFaixa.disabled = true;
		}
}

function limparPesquisaCliente() {
    var form = document.forms[0];

    form.idCliente.value = "";
    form.nomeCliente.value = "";
}


function validarAreaConstuida(){
	var form = document.forms[0];
	if(form.idAreaConstruidaFaixa.value == '-1' && form.areaConstruida.value == ''){
	alert('Informe Área Construída');
	}
}

function validarForm(form){

	if(testarCampoValorZero(document.EconomiaPopupActionForm.numeroPontosUtilizacao, 'Número Ponto Utilização') &&
	  testarCampoValorZero(document.EconomiaPopupActionForm.numeroMorador, 'Número Morador') &&
	  testarCampoValorZero(document.EconomiaPopupActionForm.numeroIptu, 'Número IPTU') &&
	  testarCampoValorZero(document.EconomiaPopupActionForm.numeroCelpe, 'Número Companhia de Energia') &&
	 // testarCampoValorZero(document.EconomiaPopupActionForm.areaConstruida, 'Área Contruída') &&
	  testarCampoValorZero(document.EconomiaPopupActionForm.idCliente, 'Cliente')){
		 
		if(validateEconomiaPopupActionForm(document.EconomiaPopupActionForm)){
			validarAreaConstuida();
	 		//converteVirgula(document.EconomiaPopupActionForm.areaConstruida);
			desabilitaCampos();
			submeterFormPadrao(form);
		}
	}
}


--></script>
</head>


<logic:equal name="testeInserir" value="false" scope="request">
	<body leftmargin="0" topmargin="0"
		onload="resizePageSemLink(640,600);javascript:setarFoco('${requestScope.nomeCampo}');desabilitaCampoAreaConstruida();">
</logic:equal>
<logic:equal name="testeInserir" value="true" scope="request">
	<body leftmargin="0" topmargin="0"
		onload="chamarReload('exibirInformarEconomiaAction.do?retornaDoPopup=1');window.close();">
</logic:equal>




<html:form action="/atualizarEconomiaPopupAction"
	name="EconomiaPopupActionForm"
	type="gcom.gui.cadastro.imovel.EconomiaPopupActionForm" method="post"
	onsubmit="return validateEconomiaPopupActionForm(this);">

	<html:hidden property="colecaoCliente" value="a" />
	<table width="600" border="0" cellpadding="0" cellspacing="5">
		<tr>
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
					<td class="parabg">Atualizar Economia</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<table width="100%" border="0">
				<tr>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td>Atualizar Economia:</td>
					<td align="right"><a
						href="javascript: abrirPopupHelp('/gsan/help/help.jsp?mapIDHelpSet=imovelInformarEconomia-Atualizar', 500, 700);"><span
						style="font-weight: bold"><font color="#3165CE">Ajuda</font></span></a></td>
				</tr>
			</table>
			<table width="100%" border="0">
				<tr>
					<td>
					<table width="100%" border="0">
						<tr>
							<td width="42%"><strong>Complemento Endere&ccedil;o:</strong></td>
							<td width="58%"><html:text maxlength="25" tabindex="1"
								property="complementoEndereco" size="25" /></td>

						</tr>

						<tr>
							<td><strong>N&uacute;mero de Pontos de Utiliza&ccedil;&atilde;o:</strong></td>
							<td><html:text maxlength="4" property="numeroPontosUtilizacao"
								tabindex="2" size="4" /></td>

						</tr>

						<tr>
							<td><strong>N&uacute;mero de Moradores<font color="#000000">:</font></strong></td>
							<td><html:text maxlength="4" property="numeroMorador" size="4"
								tabindex="3" /></td>

						</tr>

						<tr>
							<td><strong>N&uacute;mero de IPTU<font color="#000000">:</font></strong></td>

							<td><logic:present name="tarifaSocial" scope="session">
								<html:text maxlength="20" property="numeroIptu" size="20"
									tabindex="4" disabled="true" />
							</logic:present> <logic:notPresent name="tarifaSocial"
								scope="session">
								<html:text maxlength="20" property="numeroIptu" size="20"
									tabindex="4" />
							</logic:notPresent></td>
						</tr>

						<tr>
							<td><strong>N&uacute;mero Contrato Companhia de Energia:</strong></td>
							<td><logic:present name="tarifaSocial" scope="session">
								<html:text maxlength="10" property="numeroCelpe" size="10"
									tabindex="5" disabled="true" />
							</logic:present> <logic:notPresent name="tarifaSocial"
								scope="session">
								<html:text maxlength="10" property="numeroCelpe" size="10"
									tabindex="5" />
							</logic:notPresent></td>

						</tr>

						<tr>
							<td colspan="2">
							<table width="95%">
								<tr>
									<td width="30%"><strong>&Aacute;rea Constru&iacute;da:<font
										color="#FF0000">*</font></strong></td>
									<td width="20%"><logic:present name="tarifaSocial"
										scope="session">
										<html:text maxlength="10" style="text-align: right;"
											onkeyup="formataValorMonetario(this, 8); javascript:desabilitaCampoAreaConstruida();"
											property="areaConstruida" size="10" tabindex="6"
											disabled="true" />&nbsp;m<sup>2</sup></td>
									<td width="50%"><logic:empty name="EconomiaPopupActionForm"
										property="areaConstruida">
										<html:select property="idAreaConstruidaFaixa" disabled="true"
											tabindex="7"
											onchange="javascript:desabilitaCampoAreaConstruida();">
											<html:option value="-1">&nbsp;</html:option>
											<html:options collection="areasConstruidasFaixas"
												labelProperty="faixaCompleta" property="id" />
										</html:select>
									</logic:empty> </logic:present> <logic:notPresent
										name="tarifaSocial" scope="session">
										<html:text maxlength="10" property="areaConstruida" size="10"
											style="text-align: right;" tabindex="6"
											onkeyup="formataValorMonetario(this, 11); javascript:desabilitaCampoAreaConstruida();" />&nbsp;m<sup>2</sup></td>
									<td width="50%"><logic:empty name="EconomiaPopupActionForm"
										property="areaConstruida">
										<html:select property="idAreaConstruidaFaixa" tabindex="7"
											onchange="javascript:desabilitaCampoAreaConstruida();">
											<html:option value="-1">&nbsp;</html:option>
											<html:options collection="areasConstruidasFaixas"
												labelProperty="faixaCompleta" property="id" />
										</html:select>
									</logic:empty> <logic:notEmpty name="EconomiaPopupActionForm"
										property="areaConstruida">
										<html:select property="idAreaConstruidaFaixa" disabled="true"
											tabindex="7"
											onchange="javascript:desabilitaCampoAreaConstruida();">
											<html:option value="-1">&nbsp;</html:option>
											<html:options collection="areasConstruidasFaixas"
												labelProperty="faixaCompleta" property="id" />
										</html:select>
									</logic:notEmpty> </logic:notPresent></td>
								</tr>
							</table>
							<hr>
							<table width="100%" border="0">
								<tr>
									<td colspan="2">Para inserir o cliente, informe os dados
									abaixo:</td>
								</tr>



								<tr>
									<td width="13%"><strong>C&oacute;digo:<font color="#FF0000">*</font></strong></td>
									<td width="87%"><logic:present name="tarifaSocial"
										scope="session">
										<html:text maxlength="9" property="idCliente" size="9"
											tabindex="8" disabled="true" />
										<img width="23" height="21" border="0"
											src="<bean:message key="caminho.imagens"/>pesquisa.gif"
											title="Pesquisar Cliente" />
										<html:text maxlength="50" property="nomeCliente" tabindex="9"
											readonly="true"
											style="background-color:#EFEFEF; border:0; color: #ff0000"
											size="50" />
										<img
											src="<bean:message key="caminho.imagens"/>limparcampo.gif"
											border="0" title="Apagar" />
									</logic:present> <logic:notPresent name="tarifaSocial"
										scope="session">
										<html:text maxlength="9" property="idCliente" size="9"
											tabindex="8"
											onkeypress="validaEnter(event, 'exibirAtualizarEconomiaPopupAction.do?pesquisaEnter=1', 'idCliente');" />
										<a
											href="javascript:redirecionarSubmit('exibirPesquisarClienteParaEconomiaAction.do?caminhoRetornoTelaPesquisaCliente=exibirAtualizarEconomiaPopupAction');">
										<img width="23" height="21" border="0"
											src="<bean:message key="caminho.imagens"/>pesquisa.gif"
											title="Pesquisar Cliente" /></a>
										<logic:present name="codigoClienteNaoEncontrado"
											scope="request">
											<html:text maxlength="50" property="nomeCliente"
												readonly="true"
												style="background-color:#EFEFEF; border:0; color: #ff0000"
												size="50" />
										</logic:present>
										<logic:notPresent name="codigoClienteNaoEncontrado"
											scope="request">
											<html:text maxlength="50" property="nomeCliente"
												readonly="true"
												style="background-color:#EFEFEF; border:0; color: #000000"
												size="50" />
										</logic:notPresent>
										<a href="javascript:limparPesquisaCliente();"> <img
											src="<bean:message key="caminho.imagens"/>limparcampo.gif"
											border="0" title="Apagar" /></a>
									</logic:notPresent></td>
								</tr>
								<tr>
									<td><strong>Tipo Relação:</strong></td>
									<td><logic:present name="tarifaSocial" scope="session">
										<html:select property="clienteRelacaoTipo" disabled="true"
											tabindex="9">
											<html:option
												value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
											<html:options collection="clientesRelacoesTipos"
												labelProperty="descricao" property="id" />
										</html:select>
										<html:hidden property="textoSelecionadoEconomia" />
									</logic:present> <logic:notPresent name="tarifaSocial"
										scope="session">
										<html:select property="clienteRelacaoTipo" tabindex="9"
											onchange="document.EconomiaPopupActionForm.textoSelecionadoEconomia.value = this[this.selectedIndex].text;">
											<html:option
												value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
											<html:options collection="clientesRelacoesTipos"
												labelProperty="descricao" property="id" />
										</html:select>
										<html:hidden property="textoSelecionadoEconomia" />
									</logic:notPresent></td>
								</tr>
								<tr>
									<td><strong>Data Início Relação:<font color="#FF0000">*</font></strong></td>
									<td><logic:present name="tarifaSocial" scope="session">
										<html:text property="dataInicioClienteImovelRelacao"
											tabindex="10" size="10" maxlength="10" disabled="true" />
										<img border="0"
											src="<bean:message key="caminho.imagens"/>calendario.gif"
											width="20" border="0" align="absmiddle"
											alt="Exibir Calendário" />
										<font size="1"> dd/mm/aaaa </font>
									</logic:present> <logic:notPresent name="tarifaSocial"
										scope="session">
										<html:text property="dataInicioClienteImovelRelacao"
											tabindex="10" size="10" maxlength="10"
											onkeypress="mascaraData(this, event); " />
										<a
											href="javascript:abrirCalendario('EconomiaPopupActionForm', 'dataInicioClienteImovelRelacao')"><img
											border="0"
											src="<bean:message key="caminho.imagens"/>calendario.gif"
											width="20" border="0" align="absmiddle"
											alt="Exibir Calendário" /></a>
										<font size="1"> dd/mm/aaaa </font>
									</logic:notPresent></td>
								</tr>

								<tr>
									<td>&nbsp;</td>
									<td><strong><font color="#FF0000">*</font></strong> Campos
									obrigat&oacute;rios</td>
								</tr>
							</table>
							<table width="100%" border="0">
								<tr>
									<td width="183"><strong>Nome do Cliente </strong></td>
									<td width="432" align="right"><logic:present
										name="tarifaSocial" scope="session">
										<input name="Button" type="button" class="bottonRightCol"
											value="Adicionar" disabled="true" />
										<html:hidden property="botaoAdicionar" value="atualizar" />
									</logic:present> <logic:notPresent name="tarifaSocial"
										scope="session">
										<input name="Button" type="button" class="bottonRightCol"
											value="Adicionar" onclick="return verificarAdicionar();" />
										<html:hidden property="botaoAdicionar" value="atualizar" />
									</logic:notPresent></td>
								</tr>
								<tr>
									<td colspan="2">
									<table width="100%" cellpadding="0" cellspacing="0">
										<tr>
											<td height="0">
											<div style="width: 100%; height: 100%; overflow: auto;">
											<table width="100%" bgcolor="#99CCFF">
												<!--header da tabela interna -->
												<tr bordercolor="#FFFFFF" bgcolor="#79BBFD">
													<td width="10%">Remover</td>
													<td width="15%" align="center">Código</td>
													<td width="35%">
													<div align="center">Nome</div>
													</td>
													<td width="18%">Tipo</td>
													<td width="25%">CPF/CNPJ
													<div align="center"></div>
													</td>
												</tr>
											</table>
											</div>
											</td>
										</tr>
										<tr>
											<td>
											<div style="width: 100%; height: 100%; overflow: auto;">
											<table width="100%" align="center" bgcolor="#99CCFF">

												<%int cont = 1;
					boolean existeUsuario = false;%>
												<logic:iterate name="imovelEconomia"
													property="clienteImovelEconomias"
													id="clienteImovelEconomiaCadastrada">
													<logic:empty name="clienteImovelEconomiaCadastrada"
														property="dataFimRelacao">
														<%cont = cont + 1;
							if (cont % 2 == 0) {%>
														<tr bgcolor="#FFFFFF">
															<%} else {

							%>
														<tr bgcolor="#cbe5fe">
															<%}%>
															<td width="10%">
															<div align="center"><logic:present name="tarifaSocial"
																scope="session">
																<input type="checkbox" name="idRegistrosRemocao"
																	value="<%=""+((ClienteImovelEconomia)clienteImovelEconomiaCadastrada).getUltimaAlteracao().getTime()%>"
																	disabled />
															</logic:present> <logic:notPresent name="tarifaSocial"
																scope="session">
																<input type="checkbox" name="idRegistrosRemocao"
																	value="<%=""+((ClienteImovelEconomia)clienteImovelEconomiaCadastrada).getUltimaAlteracao().getTime()%>" />
															</logic:notPresent></div>
															</td>
															<td width="15%" align="center">
															<div><bean:write name="clienteImovelEconomiaCadastrada"
																property="cliente.id" /></div>
															</td>
															<td width="35%">
															<div><bean:write name="clienteImovelEconomiaCadastrada"
																property="cliente.nome" /></div>
															</td>
															<td width="18%"><logic:equal
																name="clienteImovelEconomiaCadastrada"
																property="clienteRelacaoTipo.id"
																value="<%=""+ClienteRelacaoTipo.USUARIO%>">
																<html:hidden property="idClienteImovelUsuario"
																	value="${clienteImovelEconomiaCadastrada.clienteRelacaoTipo.id}" />
																<%existeUsuario = true;%>
															</logic:equal> <bean:write
																name="clienteImovelEconomiaCadastrada"
																property="clienteRelacaoTipo.descricao" /></td>
															<td width="25%" align="right"><logic:notEmpty
																name="clienteImovelEconomiaCadastrada"
																property="cliente.cpf">
																<bean:write name="clienteImovelEconomiaCadastrada"
																	property="cliente.cpfFormatado" />
															</logic:notEmpty> <logic:notEmpty
																name="clienteImovelEconomiaCadastrada"
																property="cliente.cnpj">
																<bean:write name="clienteImovelEconomiaCadastrada"
																	property="cliente.cnpjFormatado" />
															</logic:notEmpty></td>

														</tr>
													</logic:empty>
												</logic:iterate>
												<logic:iterate name="colecaoClientesImoveisEconomia"
													id="clienteImovelEconomia">
													<%cont = cont + 1;
						if (cont % 2 == 0) {%>
													<tr bgcolor="#FFFFFF">
														<%} else {

						%>
													<tr bgcolor="#cbe5fe">
														<%}%>
														<td width="10%">
														<div align="center"><input type="checkbox"
															name="idRegistrosRemocao"
															value="<%=""+((ClienteImovelEconomia)clienteImovelEconomia).getUltimaAlteracao().getTime()%>" />

														</div>
														</td>
														<td width="15%" align="center">
														<div><bean:write name="clienteImovelEconomia"
															property="cliente.id" /></div>
														</td>
														<td width="35%">
														<div><bean:write name="clienteImovelEconomia"
															property="cliente.nome" /></div>
														</td>
														<td width="18%"><logic:equal name="clienteImovelEconomia"
															property="clienteRelacaoTipo.id"
															value="<%=""+ClienteRelacaoTipo.USUARIO%>">
															<html:hidden property="idClienteImovelUsuario"
																value="${clienteImovelEconomia.clienteRelacaoTipo.id}" />
															<%existeUsuario = true;%>
														</logic:equal> <bean:write name="clienteImovelEconomia"
															property="clienteRelacaoTipo.descricao" /></td>
														<td width="25%" align="right"><logic:notEmpty
															name="clienteImovelEconomia" property="cliente.cpf">
															<bean:write name="clienteImovelEconomia"
																property="cliente.cpfFormatado" />
														</logic:notEmpty> <logic:notEmpty
															name="clienteImovelEconomia" property="cliente.cnpj">
															<bean:write name="clienteImovelEconomia"
																property="cliente.cnpjFormatado" />
														</logic:notEmpty></td>

													</tr>
												</logic:iterate>

												<%if (!existeUsuario) {%>

												<input type="hidden" name="idClienteImovelUsuario" />
												<%}

				%>


											</table>
											</div>
											</td>
										</tr>
									</table>
									<html:button styleClass="bottonRightCol" value="Remover"
										property="botaoRemover"
										onclick="return CheckboxNaoVazio(document.forms[0].idRegistrosRemocao) && redirecionarSubmit('removerEconomiaPopupAction.do');" />
									</td>
								</tr>
							</table>
							</td>
						</tr>
					</table>
					</td>
				</tr>
				<tr>
					<td height="24">
					<div align="right"><input type="hidden" name="testeInserir"> <input
						name="Button" type="button" class="bottonRightCol" tabindex="11"
						value="Atualizar"
						onClick="document.forms[0].testeInserir.value='true'; validarForm(document.EconomiaPopupActionForm);">

					</div>
					</td>
				</tr>
			</table>
			<p>&nbsp;</p>
			</td>
		</tr>
	</table>
</html:form>
</body>
</html:html>
