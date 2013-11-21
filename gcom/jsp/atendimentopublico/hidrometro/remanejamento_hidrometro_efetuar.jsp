
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

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
	src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false"
	formName="EfetuarRemanejamentoHidrometroActionForm" />

<script language="JavaScript">
	
	function redirecionaSubmit(caminhoAction) {

   		var form = document.forms[0];
   		form.action = caminhoAction;
   		form.submit();

   		return true;

 	}
	
	//verifica se existe alguma restrição 
	//para exibição alguma campo no formulario
    function verificaForm() {

       	var form = document.forms[0];

		if(form.veioEncerrarOS.value == 'true'){
			form.idOrdemServico.disabled=true;
		}else{
			form.idOrdemServico.disabled=false;
		}       	
	}
	
	
	function validarForm(form){
	
		urlRedirect = "/gsan/efetuarRemanejamentoHidrometroAction.do"
	
		if(validateEfetuarRemanejamentoHidrometroActionForm(form)){
	    	redirecionaSubmit(urlRedirect);
		}
	}
	
	function validaCavalete(){
	
			var form = document.EfetuarRemanejamentoHidrometroActionForm;
    			if(form.tipoMedicao[0].checked == false &&	form.tipoMedicao[1].checked == false){
    			alert('Informe Cavalete');
    			}
	}
	
	function limparForm() {

		var form = document.EfetuarRemanejamentoHidrometroActionForm;

		form.idOrdemServico.value = "";
		form.nomeOrdemServico.value = "";
		form.matriculaImovel.value = "";
		form.inscricaoImovel.value = "";
		form.clienteUsuario.value = "";
		form.cpfCnpjCliente.value = "";
		form.situacaoLigacaoAgua.value = "";
		form.situacaoLigacaoEsgoto.value = "";
		form.hidrometro.value = "";  
		form.dataInstalacao.value = "";
		form.tipoMedicao[0].checked = false;
		form.tipoMedicao[1].checked = false;
		form.localInstalacao.value = "-1";
		form.tipoCavalete[0].checked = false;
		form.tipoCavalete[1].checked = false;
		form.protecao.value = "-1";
		form.percentualCobranca.value = "-1";
		if(form.motivoNaoCobranca != null){
		  form.motivoNaoCobranca.value = "-1";
		}
		form.quantidadeParcelas.value = "";
		form.idTipoDebito.value = "";
		form.descricaoTipoDebito.value = "";
		form.valorParcelas.value = "";
		form.valorDebito.value = "";
		form.percentualCobranca.disable = false;		
		form.quantidadeParcelas.disable = false;

	}
	
	function limparOrdemServico() {
	
		var form = document.EfetuarRemanejamentoHidrometroActionForm;
	
		form.idOrdemServico.value = "";
		form.nomeOrdemServico.value = "";
		form.matriculaImovel.value = "";
		form.inscricaoImovel.value = "";
		form.clienteUsuario.value = "";
		form.cpfCnpjCliente.value = "";
		form.situacaoLigacaoAgua.value = "";
		form.situacaoLigacaoEsgoto.value = "";
		form.hidrometro.value = "";  
		form.dataInstalacao.value = "";
		form.tipoMedicao[0].checked = false;
		form.tipoMedicao[1].checked = false;
		form.localInstalacao.value = "-1";
		form.tipoCavalete[0].checked = false;
		form.tipoCavalete[1].checked = false;
		form.protecao.value = "-1";
		form.percentualCobranca.value = "-1";
		if(form.motivoNaoCobranca != null){
		  form.motivoNaoCobranca.value = "-1";
		}
		form.quantidadeParcelas.value = "";
		form.idTipoDebito.value = "";
		form.descricaoTipoDebito.value = "";
		form.valorParcelas.value = "";
		form.valorDebito.value = "";
	
	}
	
	function limparOrdemServicoTecla() {
		var form = document.EfetuarRemanejamentoHidrometroActionForm;
		
		form.nomeOrdemServico.value = "";
		form.matriculaImovel.value = "";
		form.inscricaoImovel.value = "";
		form.clienteUsuario.value = "";
		form.cpfCnpjCliente.value = "";
		form.situacaoLigacaoAgua.value = "";
		form.situacaoLigacaoEsgoto.value = "";
		form.hidrometro.value = "";  
		form.dataInstalacao.value = "";
		form.tipoMedicao[0].checked = false;
		form.tipoMedicao[1].checked = false;
		form.localInstalacao.value = "-1";
		form.tipoCavalete[0].checked = false;
		form.tipoCavalete[1].checked = false;
		form.protecao.value = "-1";	
		form.percentualCobranca.value = "-1";
		if(form.motivoNaoCobranca != null){
		  form.motivoNaoCobranca.value = "-1";
		}
		form.quantidadeParcelas.value = "";
		form.idTipoDebito.value = "";
		form.descricaoTipoDebito.value = "";
		form.valorParcelas.value = "";
		form.valorDebito.value = "";

	}
	
	//Chama Popup
	function chamarPopup(url, tipo, objeto, codigoObjeto, altura, largura, msg,objetoRelacionado){
		if(objetoRelacionado.disabled != true){
			if (objeto == null || codigoObjeto == null){
				abrirPopup(url + "?" + "tipo=" + tipo, altura, largura);
			}
			else{
				if (codigoObjeto.length < 1 || isNaN(codigoObjeto)){
					alert(msg);
				}
				else{
					abrirPopup(url + "?" + "tipo=" + tipo + "&" + objeto + "=" + codigoObjeto + "&caminhoRetornoTelaPesquisaOrdemServico=" + tipo, altura, largura);
				}
			}
		}
	}

	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
   		var form = document.forms[0];
   
    	if (tipoConsulta == 'ordemServico') {
	    	form.idOrdemServico.value = codigoRegistro;
	      	form.action='exibirEfetuarRemanejamentoHidrometroAction.do';
	      	form.submit();
	    }
  	}

	function calcularValores(){
	
		var form = document.EfetuarRemanejamentoHidrometroActionForm;
	   	if (validateEfetuarRemanejamentoHidrometroActionForm(form) && validaDebito()){
	   	
	   		form.action='exibirEfetuarRemanejamentoHidrometroAction.do?calculaValores=S';
	      	form.submit();
		}
	}
	
	//verifica se o motivo da não cobraça naum foi informado
	//libera o campo quantidade de parcelas 
	function habilitarQtdParcelaButton(){
		var form = document.forms[0];
		if(form.motivoNaoCobranca.value == "-1"){	
			form.percentualCobranca.value = "-1"
			form.valorParcelas.value = "";
			form.percentualCobranca.disabled = false;
			form.quantidadeParcelas.disabled = false;
			form.valorParcelas.value = "";
			form.buttonCalcular.disabled = false;
		}else{
			form.percentualCobranca.value = "-1"
			form.percentualCobranca.disabled = true;
			form.quantidadeParcelas.disabled = true;
			form.quantidadeParcelas.value = "";
			form.valorParcelas.value = "";
			form.buttonCalcular.disabled = true;
		}
	}
</script>


</head>

<body leftmargin="5" topmargin="5"
	onload="javascript:verificaForm();setarFoco('${requestScope.nomeCampo}');">


<html:form action="/exibirEfetuarRemanejamentoHidrometroAction.do"
	name="EfetuarRemanejamentoHidrometroActionForm"
	type="gcom.gui.atendimentopublico.hidrometro.EfetuarRemanejamentoHidrometroActionForm"
	method="post">

	<html:hidden property="veioEncerrarOS" />

	<logic:notPresent name="semMenu">

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
				</logic:notPresent> <logic:present name="semMenu">
					<table width="550" border="0" cellspacing="5" cellpadding="0">
						<tr>
							<td width="615" valign="top" class="centercoltext">
							<table height="100%">
								<tr>
									<td></td>
								</tr>
							</table>
							</logic:present>
							<table width="100%" border="0" align="center" cellpadding="0"
								cellspacing="0">
								<tr>
									<td width="11"><img border="0"
										src="<bean:message key="caminho.imagens"/>parahead_left.gif" /></td>
									<td class="parabg">Efetuar Remanejamento de Hidrômetro</td>
									<td width="11"><img border="0"
										src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
								</tr>
							</table>
							<p>&nbsp;</p>
							<table width="100%" border="0">
								<tr>

									<td height="31">
									<table width="100%" border="0" align="center">
										<tr>
											<td height="10" colspan="2">Para efetuar o remanejamento do
											hidrômetro, informe os dados abaixo:.</td>
										</tr>
										<tr>
											<td height="10" colspan="2">
											<hr>
											</td>
										</tr>

										<tr>

											<td><strong>Ordem de Servi&ccedil;o:<font color="#FF0000">*</font>
											</strong></td>

											<td><html:text property="idOrdemServico" size="8"
												maxlength="9"
												onkeypress="validaEnterComMensagem(event, 'exibirEfetuarRemanejamentoHidrometroAction.do', 'idOrdemServico','Ordem de Serviço');"
												onkeyup="javascript:limparOrdemServicoTecla()" /> <a
												href="javascript:chamarPopup('exibirPesquisarOrdemServicoAction.do', 'ordemServico', null, null, 600, 500, '',document.forms[0].idOrdemServico);">
											<img width="23" height="21" border="0"
												src="<bean:message key="caminho.imagens"/>pesquisa.gif"
												title="Pesquisar OS" /></a> <logic:present
												name="OrdemServicoInexistente">
												<html:text property="nomeOrdemServico" readonly="true"
													style="background-color:#EFEFEF; border:0; color: #ff0000"
													size="45" maxlength="45" />
											</logic:present> <logic:notPresent
												name="OrdemServicoInexistente">
												<html:text property="nomeOrdemServico" readonly="true"
													style="background-color:#EFEFEF; border:0; color: #000000"
													size="45" maxlength="45" />
											</logic:notPresent> <a href="javascript:limparForm();"> <img
												border="0" title="Apagar"
												src="<bean:message key='caminho.imagens'/>limparcampo.gif" />
											</a></td>
										</tr>


										<tr bgcolor="#cbe5fe">
											<td align="center" colspan="2">
											<table width="100%" border="0" bgcolor="#99CCFF">
												<tr bgcolor="#99CCFF">
													<td height="18" colspan="2">
													<div align="center">Dados do Imóvel</div>
													</td>
												</tr>
												<tr bgcolor="#cbe5fe">

													<td>
													<table border="0" width="100%">
														<tr>
															<td width="38%" height="10"><strong>Matr&iacute;cula do
															Im&oacute;vel:</strong></td>
															<td><html:text property="matriculaImovel" readonly="true"
																style="background-color:#EFEFEF; border:0; color: #000000"
																size="15" maxlength="20" /> <html:text
																property="inscricaoImovel" readonly="true"
																style="background-color:#EFEFEF; border:0; color: #000000"
																size="21" maxlength="20" /></td>
														</tr>
														<tr>
															<td><strong> Cliente Usu&aacute;rio:</strong></td>
															<td><html:text property="clienteUsuario" readonly="true"
																style="background-color:#EFEFEF; border:0; color: #000000"
																size="40" maxlength="40" /></td>

														</tr>
														<tr>
															<td><strong>CPF ou CNPJ:</strong></td>
															<td><html:text property="cpfCnpjCliente" readonly="true"
																style="background-color:#EFEFEF; border:0; color: #000000"
																size="40" maxlength="40" /></td>


														</tr>
														<tr>

															<td><strong>Situa&ccedil;&atilde;o da
															Liga&ccedil;&atilde;o de &Aacute;gua:</strong></td>
															<td><html:text property="situacaoLigacaoAgua"
																readonly="true"
																style="background-color:#EFEFEF; border:0; color: #000000"
																size="40" maxlength="40" /></td>


														</tr>
														<tr>
															<td><strong>Situa&ccedil;&atilde;o da
															Liga&ccedil;&atilde;o de Esgoto:</strong></td>

															<td><html:text property="situacaoLigacaoEsgoto"
																readonly="true"
																style="background-color:#EFEFEF; border:0; color: #000000"
																size="40" maxlength="40" /></td>
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
											<td align="center" colspan="2">
											<table width="100%" border="0" bgcolor="#99CCFF">
												<tr bgcolor="#99CCFF">

													<td height="18" colspan="2">
													<div align="center">Dados do Remanejamento do
													Hidr&ograve;metro</div>
													</td>
												</tr>
												<tr bgcolor="#cbe5fe">
													<td>
													<table border="0" width="100%">
														<tr>

															<td width="38%"><strong>N&uacute;mero do
															Hidr&ocirc;metro:</strong></td>
															<td><strong><html:text property="hidrometro"
																readonly="true"
																style="background-color:#EFEFEF; border:0;" size="15"
																maxlength="15" /></strong></td>

														</tr>


														<tr>

															<td><strong>Data do Remanejamento:</strong></td>
															<td><strong><!-- Campo Data da Ligacao --> <html:text
																property="dataInstalacao" readonly="true"
																style="background-color:#EFEFEF; border:0;" size="10"
																maxlength="10" /></strong></td>
														</tr>


														<tr>
															<td><strong>Tipo de Medição:</strong></td>
															<td><html:radio property="tipoMedicao" disabled="true"
																value="1" /> <strong>LIGAÇÃO DE ÁGUA <html:radio
																property="tipoMedicao" disabled="true" value="2" />
															POÇO </strong></td>

														</tr>

														<tr>
															<td><strong>Local de Instalação:<font color="#FF0000">*</font></strong></td>
															<td><html:select property="localInstalacao">
																<html:option value="-1">&nbsp;</html:option>
																<html:options
																	collection="colecaoHidrometroLocalInstalacao"
																	labelProperty="descricao" property="id" />
															</html:select> <font size="1">&nbsp; </font></td>
														</tr>

														<tr>
															<td><strong>Proteção:<font color="#FF0000">*</font></strong></td>
															<td><html:select property="protecao">
																<html:option value="-1">&nbsp;</html:option>
																<html:options collection="colecaoHidrometroProtecao"
																	labelProperty="descricao" property="id" />
															</html:select> <font size="1">&nbsp; </font></td>
														</tr>

														<tr>
															<td><strong>Cavalete:<font color="#FF0000">*</font></strong></td>
															<td><strong> <html:radio property="tipoCavalete"
																value="1" /> <strong>COM <html:radio
																property="tipoCavalete" value="2" /> SEM</strong> </strong></td>

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

								<logic:notEmpty name="EfetuarRemanejamentoHidrometroActionForm"
									property="idTipoDebito">
									<tr>
										<td height="31">
										<table width="100%" border="0" align="center">
											<tr bgcolor="#cbe5fe">
												<td align="center" colspan="2">
												<table width="100%" border="0" bgcolor="#99CCFF">

													<tr bgcolor="#99CCFF">
														<td height="18" colspan="2">
														<div align="center">Dados da Geração do Débito</div>
														</td>
													</tr>
													<tr bgcolor="#cbe5fe">
														<td>
														<table border="0" width="100%">
															<tr>
																<td width="38%" height="10"><strong>Tipo de Débito:</strong></td>
																<td colspan="2"><strong> <!-- Campo Data da Ligacao -->
																<html:text property="idTipoDebito" readonly="true"
																	style="background-color:#EFEFEF; border:0;" size="5"
																	maxlength="5" /> <html:text
																	property="descricaoTipoDebito" readonly="true"
																	style="background-color:#EFEFEF; border:0;" size="30"
																	maxlength="30" /> </strong></td>
															</tr>
															
															<!-- Colocado por Raphael Rossiter em 20/04/2007 -->
															<logic:notEqual name="EfetuarRemanejamentoHidrometroActionForm" property="alteracaoValor" value="OK">
															<tr>
																<td><strong>Valor do Débito:</strong></td>
																<td colspan="2"><strong> <!--Campo Seleção Diametro de Ligação -->
																<html:text property="valorDebito" readonly="true"
																	style="background-color:#EFEFEF; border:0; text-align: right;"
																	size="15" maxlength="15" /></strong></td>
															</tr>
															</logic:notEqual>
															
															<logic:equal name="EfetuarRemanejamentoHidrometroActionForm" property="alteracaoValor" value="OK">
															<tr>
																<td><strong>Valor do Débito:</strong></td>
																<td colspan="2"><strong> <!--Campo Seleção Diametro de Ligação -->
																<html:text property="valorDebito" 
																	style="text-align: right;"
																	size="15" maxlength="15" /></strong></td>
															</tr>
															</logic:equal>
															
															<logic:present name="permissaoMotivoNaoCobranca">
																<tr>
																	<td class="style3"><strong>Motivo da Não Cobrança:</strong></td>

																	<td colspan="2"><strong> <!-- Campo Material Ligação -->
																	<html:select property="motivoNaoCobranca"
																		style="width: 230px;"
																		onchange="habilitarQtdParcelaButton();">
																		<html:option value="-1">&nbsp;</html:option>
																		<logic:present name="colecaoNaoCobranca">
																			<html:options collection="colecaoNaoCobranca"
																				labelProperty="descricao" property="id" />
																		</logic:present>
																	</html:select> </strong></td>
																</tr>
																<tr>
																	<td class="style3"><strong>Percentual de Cobrança:</strong></td>
																	<td colspan="2"><strong> <html:select
																		property="percentualCobranca" style="width: 70px;"
																		onchange="limpaValorParcela();calculaValorParcela();">
																		<html:option value="-1">&nbsp;</html:option>
																		<html:option value="100">100%</html:option>
																		<html:option value="70">70%</html:option>
																		<html:option value="50">50%</html:option>
																	</html:select> </strong></td>
																</tr>
																<tr>
																	<td class="style3"><strong>Quantidade de Parcelas:</strong></td>
																	<td colspan="2"><strong> <html:text
																		property="quantidadeParcelas" size="5" maxlength="5" /> </strong></td>
																</tr>
																<tr>
																	<td class="style3"><strong>Valor da Parcela:</strong></td>
																	<td colspan="2"><strong> <html:text
																		property="valorParcelas" readonly="true"
																		style="background-color:#EFEFEF; border:0;text-align: right;"
																		size="15" maxlength="15" /> </strong></td>
																		
																	<td colspan="4" align="right">
																		<input type="button" 
																			class="bottonRightCol" 
																			value="Calcular"
																			name="buttonCalcular" 
																			tabindex="10"
																			onclick="calcularValores();" 
																			style="width: 80px">
																	</td>
																	
																</tr>
															</logic:present>
															<logic:notPresent name="permissaoMotivoNaoCobranca">
																<tr>
																	<td class="style3"><strong>Percentual de Cobrança: <font
																		color="#FF0000">*</font></strong></td>
																	<td colspan="2"><strong> <html:select
																		property="percentualCobranca" style="width: 70px;">

																		<html:option value="100">100%</html:option>
																	</html:select> </strong></td>
																</tr>
																<tr>
																	<td class="style3"><strong>Quantidade de Parcelas:<font
																		color="#FF0000">*</font></strong></td>
																	<td colspan="2"><strong> <html:text
																		property="quantidadeParcelas" size="5" maxlength="5"
																		readonly="true" /> </strong></td>
																</tr>
																<tr>
																	<td class="style3"><strong>Valor da Parcela:</strong></td>
																	<td colspan="2"><html:text property="valorParcelas"
																		readonly="true"
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

										</table>
										</td>
									</tr>
								</logic:notEmpty>


								<tr>
									<td colspan="2">
									<table width="100%">
										<tr colspan="1" align="center">
											<td height="19"><strong> <font color="#FF0000"></font></strong></td>
											<td align="center">
											<div align="left"><strong><font color="#FF0000">*</font></strong>
											Campos obrigat&oacute;rios</div>
											</td>
										</tr>
										<tr>
											<td width="40%" align="left"><logic:present
												name="caminhoRetornoIntegracaoComercial">
												<INPUT TYPE="button" class="bottonRightCol" value="Voltar"
													onclick="redirecionarSubmit('${caminhoRetornoIntegracaoComercial}')" />
											</logic:present> <input type="button" name="ButtonReset"
												class="bottonRightCol" value="Desfazer"
												onClick="limparForm();"> <input type="button"
												name="ButtonCancelar" class="bottonRightCol"
												value="Cancelar"
												onClick="javascript:window.location.href='/gsan/telaPrincipal.do'">
											</td>
											<td align="right"><input name="Button" type="button"
												class="bottonRightCol" value="Efetuar"
												onClick="javascript:validarForm(document.forms[0]);"></td>
										</tr>
									</table>
									</td>

								</tr>
								<!--</tr></table></td>-->
							</table>
					</table>
					<!-- Fim do Corpo - Romulo Aurelio-->

					<logic:notPresent name="semMenu">
						<%@ include file="/jsp/util/rodape.jsp"%>
					</logic:notPresent>

					</html:form>

					<script language="JavaScript">

</script>
					</html:html>