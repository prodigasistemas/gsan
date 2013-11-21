<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<%@page isELIgnored="false"%>
<%@ page import="gcom.cadastro.cliente.ClienteImovelEconomia"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="EconomiaPopupActionForm" />
<%@ page import="gcom.util.ConstantesSistema" %>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>

<script language="JavaScript">
<!-- Begin

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
    var form = document.EconomiaPopupActionForm;
    if (form.areaConstruida.value != ''){
      form.idAreaConstruidaFaixa.value = '-1';
      form.idAreaConstruidaFaixa.disabled = true;
    }else{
      form.idAreaConstruidaFaixa.disabled = false;
    }
  }


function limparPesquisaCliente() {
    var form = document.forms[0];

    form.idCliente.value = "";
    form.nomeCliente.value = "";
}
function validarForm(form){
var msg = '';

var idClienteImovelUsuario = document.getElementById(form.idClienteImovelUsuario);

	if(testarCampoValorZero(document.EconomiaPopupActionForm.numeroPontosUtilizacao, 'Número de Pontos de Utilização') &&
	  testarCampoValorZero(document.EconomiaPopupActionForm.numeroMorador, 'Número de Moradores') &&
	  testarCampoValorZero(document.EconomiaPopupActionForm.numeroIptu, 'Número de IPTU') &&
	  testarCampoValorZero(document.EconomiaPopupActionForm.numeroCelpe, 'Número Contrato Companhia de Energia') &&
	  testarCampoValorZeroDecimal(document.EconomiaPopupActionForm.areaConstruida, 'Área Construída') &&
	  testarCampoValorZero(document.EconomiaPopupActionForm.idCliente, 'Código')){
		
		if(document.EconomiaPopupActionForm.areaConstruida.value == "" &&
		 	document.EconomiaPopupActionForm.idAreaConstruidaFaixa.value == "-1"){
		 	msg = msg + 'Informe Área Construida. \n';
		}
		if (idClienteImovelUsuario == ""){
			msg = msg + 'Informe um Cliente do Tipo Usuário.' ;
		}
	
		if (msg == ''){
			if(validateEconomiaPopupActionForm(form)){
				  //converteVirgula(form.areaConstruida);
				  desabilitaCampos();
				  form.submit();
			}
		}else{
			alert(msg);
		}
	
		
	}
}

function fazerReload(contador,quantidadeEconomias){
   if(contador > quantidadeEconomias){
   chamarReload('exibirInformarEconomiaAction.do?retornaDoPopup=1');
   window.close();
   }else{
   chamarReload('exibirInformarEconomiaAction.do?retornaDoPopup=1');
   resizePageSemLink(640,600);
   setarFoco('${requestScope.nomeCampo}');
   }
}

-->
</script>
</head>

<logic:equal name="testeInserir" value="false" scope="request">
	<body leftmargin="0" topmargin="0"
		onload="resizePageSemLink(640,600);javascript:setarFoco('${requestScope.nomeCampo}');">
</logic:equal>
<logic:equal name="testeInserir" value="true" scope="request">
	<body leftmargin="0" topmargin="0"
		onload="javascript:fazerReload('${sessionScope.contIdentificadorTemp}','${sessionScope.imovelSubCategoria
                            .quantidadeEconomias}');">
</logic:equal>

<html:form action="/inserirEconomiaPopupAction"
	name="EconomiaPopupActionForm"
	type="gcom.gui.cadastro.imovel.EconomiaPopupActionForm" method="post"
	onsubmit="return validateEconomiaPopupActionForm(this);">

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
					<td class="parabg">Inserir Economias</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<table width="100%" border="0">
				<tr>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td>Preencha os campos para inserir as economias:</td>
					<td align="right"><a href="javascript: abrirPopupHelp('/gsan/help/help.jsp?mapIDHelpSet=imovelInformarEconomia-Inserir', 500, 700);"><span style="font-weight: bold"><font color="#3165CE">Ajuda</font></span></a></td>											
				</tr>
				</table>
				<table width="100%" border="0">
				<tr>
					<td colspan="2"><font size="1">Quantidade de imóvel(is) economia(s)
					por imóvel subcategoria:</font> <strong><bean:write
						name="contIdentificadorTemp" scope="session" />&nbsp;<font
						size="1">de</font>&nbsp;${sessionScope.imovelSubCategoria
                            .quantidadeEconomias}</strong></td>
				</tr>

				<tr>
					<td>
					<table width="100%" border="0">
						<tr>
							<td width="42%"><strong>Complemento Endere&ccedil;o:</strong></td>
							<td width="58%"><html:text maxlength="25"
								property="complementoEndereco" size="25" tabindex="1" /></td>

						</tr>
						<tr>
							<td><strong>N&uacute;mero de Pontos de Utiliza&ccedil;&atilde;o:</strong></td>
							<td><html:text maxlength="4" property="numeroPontosUtilizacao"
								size="4" tabindex="2" /></td>

						</tr>
						<tr>
							<td><strong>N&uacute;mero de Moradores<font color="#000000">:</font></strong></td>
							<td><html:text maxlength="4" property="numeroMorador" size="4"
								tabindex="3" /></td>

						</tr>
						<tr>
							<td><strong>N&uacute;mero de IPTU<font color="#000000">:</font></strong></td>
							<td><html:text maxlength="20" property="numeroIptu" size="20"
								tabindex="4" /></td>

						</tr>
						<tr>
							<td><strong>N&uacute;mero Contrato Companhia de Energia:</strong></td>
							<td><html:text maxlength="10" property="numeroCelpe" size="10"
								tabindex="5" /></td>

						</tr>
						<tr>
							<td colspan="2">
							<table width="95%">
								<tr>
									<td width="30%"><strong>&Aacute;rea Constru&iacute;da:<font
								color="#FF0000">*</font></strong></td>
									<td width="14%"><html:text maxlength="10" style="text-align: right;"
										property="areaConstruida" size="10" tabindex="6"
										onkeyup="javaScript:formataValorMonetario(this, 8);desabilitaCampoAreaConstruida();" /></td>
									<td width="56%"><logic:empty name="EconomiaPopupActionForm"
										property="areaConstruida">
										<html:select property="idAreaConstruidaFaixa" tabindex="7">
											<html:option value="-1">&nbsp;</html:option>
											<html:options collection="areasConstruidasFaixas"
												labelProperty="faixaCompleta" property="id" />
										</html:select>
									</logic:empty> <logic:notEmpty name="EconomiaPopupActionForm"
										property="areaConstruida">
										<html:select property="idAreaConstruidaFaixa" disabled="true"
											tabindex="7">
											<html:option value="-1">&nbsp;</html:option>
											<html:options collection="areasConstruidasFaixas"
												labelProperty="faixaCompleta" property="id" />
										</html:select>
									</logic:notEmpty></td>
								</tr>
							</table>
							<hr>
							<table width="100%" border="0">
								<tr>
									<td colspan="2">Para inserir o cliente, informe os dados
									abaixo:</td>
								</tr>



								<tr>
									<td width="12%"><strong>C&oacute;digo:<font color="#FF0000">*</font></strong></td>
									<td width="88%"><html:text maxlength="9" property="idCliente"
										size="9" tabindex="8"
										onkeypress="javascript:validaEnter(event, 'exibirInserirEconomiaPopupAction.do', 'idCliente');" />
									<html:hidden property="idClienteImovelUsuario" /> <a
										href="javascript:redirecionarSubmit('exibirPesquisarClienteParaEconomiaAction.do?caminhoRetornoTelaPesquisaCliente=exibirInserirEconomiaPopupAction');">
									<img width="23" height="21" border="0"
										src="<bean:message key="caminho.imagens"/>pesquisa.gif"
										title="Pesquisar Cliente" /></a> <logic:present
										name="codigoClienteNaoEncontrado" scope="request">
										<html:text maxlength="50" property="nomeCliente"
											readonly="true"
											style="background-color:#EFEFEF; border:0; color: #ff0000"
											size="50" />
									</logic:present> <logic:notPresent
										name="codigoClienteNaoEncontrado" scope="request">
										<html:text maxlength="50" property="nomeCliente"
											readonly="true"
											style="background-color:#EFEFEF; border:0; color: #000000"
											size="50" />
									</logic:notPresent> <a
										href="javascript:limparPesquisaCliente();"> <img
										src="<bean:message key="caminho.imagens"/>limparcampo.gif"
										border="0" title="Apagar" /></a></td>
								</tr>
								<tr>
									<td><strong>Tipo Relação:<font color="#FF0000">*</font></strong></td>
									<td><html:select property="clienteRelacaoTipo" tabindex="9"
										onchange="javascript:document.EconomiaPopupActionForm.textoSelecionadoEconomia.value = this[this.selectedIndex].text;">
										<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
										<html:options collection="clientesRelacoesTipos"
											labelProperty="descricao" property="id" />
									</html:select> <html:hidden property="textoSelecionadoEconomia" />
									</td>
								</tr>
								<tr>
									<td><strong>Data Início Relação:<font color="#FF0000">*</font></strong></td>
									<td><html:text property="dataInicioClienteImovelRelacao"
										tabindex="10" size="10" maxlength="10" onkeypress="mascaraData(this, event);"/> <a
										href="javascript:abrirCalendario('EconomiaPopupActionForm', 'dataInicioClienteImovelRelacao')">
									<img border="0"
										src="<bean:message key="caminho.imagens"/>calendario.gif"
										width="20" border="0" align="absmiddle"
										alt="Exibir Calendário" /></a> <font size="1">(dd/mm/aaaa) </font></td>
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
									<td width="432" align="right"><input name="Button"
										type="button" class="bottonRightCol" value="Adicionar"
										tabindex="11"
										onclick="javascript:return verificarAdicionar();" /> <html:hidden
										property="botaoAdicionar" value="inserir" /></td>
								</tr>
								<tr>
									<td colspan="2">
									<table width="100%" cellpadding="0" cellspacing="0">
										<tr>
											<td height="0">
											<div style="width: 100%; height: 100%; overflow: auto;">
											<table width="100%" bgcolor="#90c7fc">
												<!--header da tabela interna -->
												<tr bordercolor="#90c7fc" bgcolor="#90c7fc">
													<td width="10%"><strong>Remover</strong></td>
													<td width="50%">
													<div align="center"><strong>Nome</strong></div>
													</td>
													<td width="18%"><strong>Tipo</strong>
													<div align="center"></div>
													</td>
													<td width="25%"><strong>CPF/CNPJ</strong>
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

												<%int cont = 1;%>
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
														<td width="50%">
														<div><bean:write name="clienteImovelEconomia"
															property="cliente.nome" />
															</div>
														</td>
														<td width="18%"><bean:write name="clienteImovelEconomia"
															property="clienteRelacaoTipo.descricao" /></td>
														<td width="25%"><logic:notEmpty
															name="clienteImovelEconomia" property="cliente.cpf">
															<bean:write name="clienteImovelEconomia"
																property="cliente.cpf" />
														</logic:notEmpty> <logic:notEmpty
															name="clienteImovelEconomia" property="cliente.cnpj">
															<bean:write name="clienteImovelEconomia"
																property="cliente.cnpj" />
														</logic:notEmpty></td>

													</tr>
												</logic:iterate>
												<html:hidden property="colecaoCliente"/>
											</table>
											</div>
											</td>
										</tr>
									</table>
									<html:button styleClass="bottonRightCol" value="Remover"
										property="botaoRemover"
										onclick="javascript:return CheckboxNaoVazio(document.forms[0].idRegistrosRemocao) && redirecionarSubmit('removerEconomiaPopupAction.do');" />
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
					<div align="right"><input type="hidden" name="testeInserir"> 
					<input
						name="Button" type="button" class="bottonRightCol" value="Inserir"
						onClick="document.forms[0].testeInserir.value='true';validarForm(document.forms[0]);">
					<input name="Button2" type="button" class="bottonRightCol"
						value="Fechar" onClick="window.close();"></div>
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
