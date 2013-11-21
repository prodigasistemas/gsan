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

<%@ page import="gcom.util.ConstantesSistema"%>

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false"
	formName="EfetuarLigacaoEsgotoActionForm" />

<script language="JavaScript">

	function validaForm() {
		var form = document.EfetuarLigacaoEsgotoActionForm;
		
		if(form.permissaoAlterarLigacaoEsgotosemRA.value == "true"){
		
			if (validateEfetuarLigacaoEsgotoActionForm(form) && validaDebito() && validaOSeImovel()){
				submeterFormPadrao(form);
			}
		} else {
			if (validateEfetuarLigacaoEsgotoActionForm(form) && validaDebito()){
					submeterFormPadrao(form);
				}
		
		}
	}
	
	function validaOSeImovel(){
		var form = document.EfetuarLigacaoEsgotoActionForm;
			if((form.idOrdemServico.value == "") && (form.idImovel.value == "")){
				alert('Informe Ordem de Serviço ou Imovel');
				return false;
			}else {
				return testarCampoValorInteiroComZero(form.idImovel,'Imovel');
			}
		
	}
	
	
	function limparForm() {

		var form = document.EfetuarLigacaoEsgotoActionForm;
		
		/*form.idImovel.value = "";
		form.idOrdemServico.value = "";
		form.nomeOrdemServico.value = "";
		form.matriculaImovel.value = "";
		form.inscricaoImovel.value = "";
		form.clienteUsuario.value = "";
		form.cpfCnpjCliente.value = "";
		form.situacaoLigacaoAgua.value = "";
		form.situacaoLigacaoEsgoto.value = "";
		form.dataLigacao.value = "";*/
		form.diametroLigacao.selectedIndex = 0;
		form.materialLigacao.selectedIndex = 0;
		form.perfilLigacao.selectedIndex = 0;
		form.percentualColeta.value = "";
		form.percentualEsgoto.value = "";
		if(form.idTipoDebito != null){
			form.idTipoDebito.value = "";
			form.descricaoTipoDebito.value = "";
			form.valorDebito.value = "";
		}
		if(form.motivoNaoCobranca != null){
		  form.motivoNaoCobranca.value = "-1";
		}
		if(form.percentualCobranca != null){
			form.percentualCobranca.value = "-1";
			form.quantidadeParcelas.value = "";
			form.valorParcelas.value = "";
		}
		form.idLigacaoOrigem.selectedIndex = 0;
		form.action = 'exibirEfetuarLigacaoEsgotoAction.do';
		form.submit();
	 }
	
	function limparDecricaoEfetuar() {

		var form = document.EfetuarLigacaoEsgotoActionForm;
		form.nomeOrdemServico.value = "";
		form.matriculaImovel.value = "";
		form.inscricaoImovel.value = "";
		form.clienteUsuario.value = "";
		form.cpfCnpjCliente.value = "";
		form.situacaoLigacaoAgua.value = "";
		form.situacaoLigacaoEsgoto.value = "";
		form.dataLigacao.value = "";
		form.diametroLigacao.selectedIndex = 0;
		form.materialLigacao.selectedIndex = 0;
		form.perfilLigacao.selectedIndex = 0;
		form.percentualColeta.value = "";
		form.percentualEsgoto.value = "";
		if(form.idTipoDebito != null){
			form.idTipoDebito.value = "";
			form.descricaoTipoDebito.value = "";
			form.valorDebito.value = "";
		}
		if(form.motivoNaoCobranca != null){
		  form.motivoNaoCobranca.value = "-1";
		}
		if(form.percentualCobranca != null){
			form.percentualCobranca.value = "-1";
			form.quantidadeParcelas.value = "";
			form.valorParcelas.value = "";
		}
		form.idLigacaoOrigem.selectedIndex = 0;
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
	      	form.action='exibirEfetuarLigacaoEsgotoAction.do';
	      	bloqueiaImovel();
	      	form.submit();
	    }
	    
	     if (tipoConsulta == 'imovel') {
	    	form.idImovel.value = codigoRegistro;
	      	form.action='exibirEfetuarLigacaoEsgotoAction.do';
	      	bloqueiaOS();
	      	form.submit();
	    }
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
	
	function calcularValores(){
	
		var form = document.EfetuarLigacaoEsgotoActionForm;
	   	if (validateEfetuarLigacaoEsgotoActionForm(form) && validaDebito()){
	   	
	   		form.action='exibirEfetuarLigacaoEsgotoAction.do?calculaValores=S';
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
	
	function bloqueiaOS(){
	
		var form = document.EfetuarLigacaoEsgotoActionForm;
		
		if(form.idImovel != null){
		
			if(form.idImovel.value != ""){
			
				form.idOrdemServico.value = "";
				form.idOrdemServico.style.color = "#000000";
				form.idOrdemServico.style.background = "#EFEFEF";
				form.idOrdemServico.disabled = true;
			
			}else{
			
				form.idOrdemServico.style.color = "#000000";
				form.idOrdemServico.style.background = "white";
				form.idOrdemServico.disabled = false;
			}
		}
	}
	
	function bloqueiaImovel(){
	
		var form = document.EfetuarLigacaoEsgotoActionForm;
		
		
		
			if(form.idOrdemServico.value != ""){
				if(form.idImovel != null){
					form.idImovel.value = "";
					form.idImovel.style.color = "#000000";
					form.idImovel.style.background = "#EFEFEF";
					form.idImovel.disabled = true;
				}
			}else{
				if(form.idImovel != null){
					form.idImovel.style.color = "#000000";
					form.idImovel.style.background = "white";
					form.idImovel.disabled = false;
					}
			}
	}
	
</script>
</head>
<body leftmargin="5" topmargin="5" onload="javascript:verificaForm();bloqueiaImovel();bloqueiaOS();">

<html:form action="/efetuarLigacaoEsgotoAction.do"
	name="EfetuarLigacaoEsgotoActionForm"
	type="gcom.gui.atendimentopublico.EfetuarLigacaoEsgotoActionForm"
	method="post">

	<html:hidden property="veioEncerrarOS" />
	<html:hidden property="permissaoAlterarLigacaoEsgotosemRA" />
	<html:hidden property="permissaoMotivoNaoCobranca" />
	
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
									<td class="parabg">Efetuar Ligação de Esgoto</td>
									<td width="11"><img border="0"
										src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
								</tr>
							</table>
							<p>&nbsp;</p>


							<!--Inicio da Tabela Ligação Esgoto -->
							<table width="100%" border="0">
								<tr>

									<td height="31">
									<table width="100%" border="0" align="center">
										<tr>
											<td height="10" colspan="2">Para efetuar a
											liga&ccedil;&atilde;o de esgoto, informe os dados abaixo:.</td>
										</tr>
										<tr>
											<td height="10" colspan="2">
											<hr>
											</td>
										</tr>
										<tr>
											<td height="10"><strong>Ordem de Servi&ccedil;o:<span
												class="style2"> <strong><font color="#FF0000">*</font></strong></span>
											</strong></td>

											<td><span class="style2"> <html:text
												property="idOrdemServico" size="8" maxlength="9"
												onkeypress="validaEnterComMensagem(event, 'exibirEfetuarLigacaoEsgotoAction.do', 'idOrdemServico','Ordem de Serviço');"
												onkeyup="javascript:limparDecricaoEfetuar();bloqueiaImovel();" /> <a
												href="javascript:chamarPopup('exibirPesquisarOrdemServicoAction.do', 'ordemServico', null, null, 600, 500, '',document.forms[0].idOrdemServico);">

											<img width="23" height="21" border="0"
												src="<bean:message key="caminho.imagens"/>pesquisa.gif"
												title="Pesquisar OS" /></a> <logic:present
												name="OrdemServioInexistente">
												<html:text property="nomeOrdemServico" readonly="true"
													style="background-color:#EFEFEF; border:0; color: #ff0000"
													size="37" maxlength="40" />
											</logic:present> <logic:notPresent
												name="OrdemServioInexistente">
												<html:text property="nomeOrdemServico" readonly="true"
													style="background-color:#EFEFEF; border:0; color: #000000"
													size="37" maxlength="40" />
											</logic:notPresent> <a href="javascript:limparForm();"> <img
												border="0" title="Apagar"
												src="<bean:message key='caminho.imagens'/>limparcampo.gif" />
											</a> </span></td>
										</tr>
										<logic:equal name="efetuarLigacaoEsgotoSemRA" value="true">
											<tr>
												<td HEIGHT="30" WIDTH="100"><strong>Imóvel:</strong></td>
												<td colspan="2"><html:text property="idImovel" size="10"
													maxlength="9" tabindex="1"
													onkeypress="validaEnterComMensagem(event, 'exibirEfetuarLigacaoEsgotoAction.do', 'idImovel', 'Matrícula do Imóvel');"
													onkeyup="document.forms[0].inscricaoImovel.value='';limparDecricaoEfetuar();bloqueiaOS();" />
												<a
													href="javascript:chamarPopup('exibirPesquisarImovelAction.do', 'imovel', null, null, 800, 490, '',document.forms[0].idImovel);">
												<img src="<bean:message key='caminho.imagens'/>pesquisa.gif"
													width="23" height="21" alt="Pesquisar" border="0"></a> 
													<a href="javascript:limparForm();"> <img
												border="0" title="Apagar"
												src="<bean:message key='caminho.imagens'/>limparcampo.gif" />
											</a>
										</logic:equal>

										<tr bgcolor="#cbe5fe">
											<td align="center" colspan="2">
											<table width="100%" border="0" bgcolor="#99CCFF">
												<tr bgcolor="#99CCFF">
													<td height="18" colspan="2">
													<div align="center"><span class="style2"> Dados do Imóvel </span></div>
													</td>
												</tr>
												<tr bgcolor="#cbe5fe">

													<td>
													<table border="0" width="100%">
														<tr>
															<td width="37%" height="10"><strong><span class="style2">Matr&iacute;cula
															do Im&oacute;vel:<strong></strong></span></strong></td>
															<td width="58%"><html:text property="matriculaImovel"
																readonly="true"
																style="background-color:#EFEFEF; border:0;" size="15"
																maxlength="20" /> <html:text property="inscricaoImovel"
																readonly="true"
																style="background-color:#EFEFEF; border:0;" size="21"
																maxlength="20" /></td>
														</tr>
														<tr>
															<td><strong> Cliente Usu&aacute;rio:</strong></td>
															<td><html:text property="clienteUsuario" readonly="true"
																style="background-color:#EFEFEF; border:0;" size="40"
																maxlength="40" /></td>

														</tr>
														<tr>
															<td><strong>CPF ou CNPJ:</strong></td>
															<td><html:text property="cpfCnpjCliente" readonly="true"
																style="background-color:#EFEFEF; border:0;" size="40"
																maxlength="40" /></td>
														</tr>
														<tr>

															<td><strong>Situa&ccedil;&atilde;o da
															Liga&ccedil;&atilde;o de &Aacute;gua:</strong></td>
															<td><html:text property="situacaoLigacaoAgua"
																readonly="true"
																style="background-color:#EFEFEF; border:0;" size="40"
																maxlength="40" /></td>
														</tr>
														<tr>
															<td><strong>Situa&ccedil;&atilde;o da
															Liga&ccedil;&atilde;o de Esgoto:</strong></td>

															<td><html:text property="situacaoLigacaoEsgoto"
																readonly="true"
																style="background-color:#EFEFEF; border:0;" size="40"
																maxlength="40" /></td>
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
													<div align="center"><span class="style2"> Dados da
													Liga&ccedil;&atilde;o </span></div>
													</td>
												</tr>
												<tr bgcolor="#cbe5fe">
													<td>
													<table border="0" width="100%">

														<logic:equal name="efetuarLigacaoEsgotoSemRA" value="true">
															<tr>
																<td width="42%" height="10"><strong>Data da
																Liga&ccedil;&atilde;o:<font color="#FF0000">*</font> </strong>
																</td>

																<td colspan="2"><strong><b> <html:text
																	property="dataLigacao"
																	onkeyup="mascaraData(this, event);" size="12"
																	maxlength="10" /> </b></strong></td>
															</tr>
														</logic:equal>
														<logic:notEqual name="efetuarLigacaoEsgotoSemRA"
															value="true">
															<tr>
																<td width="42%" height="10"><strong>Data da
																Liga&ccedil;&atilde;o:<font color="#FF0000">*</font> </strong>
																</td>

																<td colspan="2"><strong><b> <html:text
																	property="dataLigacao" readonly="true"
																	style="background-color:#EFEFEF; border:0;" size="20"
																	maxlength="20" /> </b></strong></td>
															</tr>
														</logic:notEqual>
														
														<tr>
															<td><strong> Diametro da Liga&ccedil;&atilde;o:<span
																class="style2"><strong><font color="#FF0000">*</font></strong></span></strong></td>
															<td colspan="2"><strong> <!--Campo Seleção Diametro de Ligação -->
															<html:select property="diametroLigacao"
																style="width: 230px;">
																<logic:present name="colecaoDiametroLigacao"
																	scope="session">
																	<html:option
																		value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
																	<html:options collection="colecaoDiametroLigacao"
																		labelProperty="descricao" property="id" />
																</logic:present>
															</html:select> </strong></td>
														</tr>
														<tr>
															<td class="style3"><strong>Material da
															Liga&ccedil;&atilde;o:<span class="style2"><strong><font
																color="#FF0000">*</font></strong></span></strong></td>
															<td colspan="2"><strong> <!-- Campo Material Ligação -->
															<html:select property="materialLigacao"
																style="width: 230px;">
																<logic:present name="colecaoMaterialLigacao"
																	scope="session">
																	<html:option
																		value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
																	<html:options collection="colecaoMaterialLigacao"
																		labelProperty="descricao" property="id" />
																</logic:present>
															</html:select> </strong></td>
														</tr>
														<tr>
															<td class="style3"><strong>Perfil da
															Liga&ccedil;&atilde;o:<span class="style2"><strong><strong><strong><font
																color="#FF0000">*</font></strong></strong></strong></span></strong></td>
															<td colspan="2"><strong> <html:select
																property="perfilLigacao"
																onchange="redirecionarSubmit('exibirEfetuarLigacaoEsgotoAction.do');"
																style="width: 230px;">
																<logic:present name="colecaoPerfilLigacao"
																	scope="session">
																	<html:option
																		value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
																	<html:options collection="colecaoPerfilLigacao"
																		labelProperty="descricao" property="id" />
																</logic:present>
															</html:select> </strong></td>
														</tr>
														<logic:equal name="alterarPercentualColetaEsgoto" value="true">
														<tr>
															<td class="style3"><b>Percentual de Coleta:<font
																color="#ff0000">*</font></b></td>

															<td colspan="2"><html:text property="percentualColeta"
																onkeyup="formataValorMonetario(this, 6);"
																style="text-align: right;" size="7" maxlength="6" /> %</td>
														</tr>
														</logic:equal>
														<logic:notEqual name="alterarPercentualColetaEsgoto" value="true">
														<tr>
															<td class="style3"><b>Percentual de Coleta:<font
																color="#ff0000">*</font></b></td>

															<td colspan="2"><html:text property="percentualColeta"
																onkeyup="formataValorMonetario(this, 6);" readonly="true"
																style="background-color:#EFEFEF; border:0;text-align: right;"
																size="7" maxlength="6" /> %</td>
														</tr>
														</logic:notEqual>
														<tr>
															<td class="style3"><b>Percentual de Esgoto:</b></td>

															<td colspan="2"><html:text property="percentualEsgoto"
																readonly="true"
																style="background-color:#EFEFEF; border:0;text-align: right;"
																size="7" maxlength="6" /> %</td>
														</tr>
														<tr>
															<td class="style3"><strong>Ligação Origem :</strong></td>

															<td colspan="2"><html:select property="idLigacaoOrigem"
																style="width: 230px;">

																<logic:present name="colecaoLigacaoOrigem"
																	scope="session">

																	<html:option value="">&nbsp;</html:option>

																	<html:options collection="colecaoLigacaoOrigem"
																		labelProperty="descricao" property="id" />

																</logic:present>
															</html:select></td>
														</tr>
														<tr>
															<td><strong>Com Caixa de Gordura?<font color="#FF0000">*</font></strong></td>
															<td><strong> <html:radio property="indicadorCaixaGordura"
																value="1" /> SIM<html:radio
																property="indicadorCaixaGordura" value="2" /> NÃO</strong></td>

														</tr>
														<tr>
															<td>
																<strong>Ligação:<font color="#FF0000">*</font></strong>
															</td>
															<td>
																<strong> 
																	<html:radio property="indicadorLigacao" value="2" /> Disponível
																	<html:radio property="indicadorLigacao" value="1" /> Efetivado
																</strong>
															</td>
														</tr>
														<tr>
															<td class="style3"><strong>Condi&ccedil;&atilde;o do
															Esgotamento:</strong></td>

															<td colspan="2"><html:select
																property="condicaoEsgotamento" style="width: 230px;">

																<html:option value="-1">&nbsp;</html:option>
																<logic:present name="colecaoLigacaoEsgotoEsgotamento">
																	<html:options
																		collection="colecaoLigacaoEsgotoEsgotamento"
																		labelProperty="descricao" property="id" />
																</logic:present>
															</html:select></td>
														</tr>
														<tr>
															<td class="style3"><strong>Situa&ccedil;&atilde;o da
															Caixa de Inspe&ccedil;&atilde;o:</strong></td>

															<td colspan="2"><html:select
																property="situacaoCaixaInspecao" style="width: 230px;">

																<html:option value="-1">&nbsp;</html:option>
																<logic:present name="colecaoSituacaoCaixaInspecao">
																	<html:options collection="colecaoSituacaoCaixaInspecao"
																		labelProperty="descricao" property="id" />
																</logic:present>
															</html:select></td>
														</tr>
														<tr>
															<td class="style3"><strong>Destino Dejetos:</strong></td>

															<td colspan="2"><html:select property="destinoDejetos"
																style="width: 230px;">

																<html:option value="-1">&nbsp;</html:option>
																<logic:present name="colecaoDestinoDejetos">
																	<html:options collection="colecaoDestinoDejetos"
																		labelProperty="descricao" property="id" />
																</logic:present>
															</html:select></td>
														</tr>
														<tr>
															<td class="style3"><strong>Destino de &Aacute;guas
															Pluviais:</strong></td>

															<td colspan="2"><html:select
																property="destinoAguasPluviais" style="width: 230px;">

																<html:option value="-1">&nbsp;</html:option>
																<logic:present name="colecaoDestinoAguasPluviais">
																	<html:options collection="colecaoDestinoAguasPluviais"
																		labelProperty="descricao" property="id" />
																</logic:present>
															</html:select></td>
														</tr>

													</table>
													</td>
												</tr>
											</table>
											</td>
										</tr>
										<logic:notEmpty name="EfetuarLigacaoEsgotoActionForm"
											property="idTipoDebito">
											<tr>
												<td height="31">
												<table width="100%" border="0" align="center">
													<tr bgcolor="#cbe5fe">
														<td align="center" colspan="2">
														<table width="100%" border="0" bgcolor="#99CCFF">

															<tr bgcolor="#99CCFF">
																<td height="18" colspan="2">
																<div align="center"><span class="style2"> Dados da
																Geração do Débito</span></div>
																</td>
															</tr>
															<tr bgcolor="#cbe5fe">
																<td>
																<table border="0" width="100%">
																	<tr>
																		<td width="42%" height="10"><strong>Tipo de Débito:<span
																			class="style2"></span><span class="style2"></span></strong></td>
																		<td colspan="2"><strong><b> <!-- Campo Data da Ligacao -->
																		<html:text property="idTipoDebito" readonly="true"
																			style="background-color:#EFEFEF; border:0;" size="5"
																			maxlength="5" /> <html:text
																			property="descricaoTipoDebito" readonly="true"
																			style="background-color:#EFEFEF; border:0;" size="30"
																			maxlength="30" /> </b></strong></td>
																	</tr>

																	<!-- Colocado por Raphael Rossiter em 19/04/2007 -->

																	<logic:notEqual name="EfetuarLigacaoEsgotoActionForm"
																		property="alteracaoValor" value="OK">
																		<tr>
																			<td><strong>Valor do Débito:</strong></td>
																			<td colspan="2"><html:text property="valorDebito"
																				readonly="true"
																				style="background-color:#EFEFEF; border:0;text-align: right;"
																				size="15" maxlength="15" /></td>
																		</tr>
																	</logic:notEqual>

																	<logic:equal name="EfetuarLigacaoEsgotoActionForm"
																		property="alteracaoValor" value="OK">
																		<tr>
																			<td><strong>Valor do Débito:</strong></td>
																			<td colspan="2"><html:text property="valorDebito"
																				style="text-align: right;" size="15" maxlength="15" 
																				onkeyup="formataValorMonetario(this, 11);" />
																			</td>
																		</tr>
																	</logic:equal>

																	<logic:present name="permissaoMotivoNaoCobranca">
																		<tr>
																			<td class="style3"><strong>Motivo da Não Cobrança:</strong></td>

																			<td colspan="2"><html:select
																				property="motivoNaoCobranca" style="width: 230px;"
																				onchange="habilitarQtdParcelaButton();">

																				<html:option value="-1">&nbsp;</html:option>
																				<logic:present name="colecaoNaoCobranca">
																					<html:options collection="colecaoNaoCobranca"
																						labelProperty="descricao" property="id" />
																				</logic:present>
																			</html:select></td>
																		</tr>

																		<tr>
																			<td class="style3"><strong>Percentual de Cobrança:</strong></td>
																			<td colspan="2"><html:select
																				property="percentualCobranca" style="width: 70px;">

																				<html:option value="-1">&nbsp;</html:option>
																				<html:option value="100">100%</html:option>
																				<html:option value="70">70%</html:option>
																				<html:option value="50">50%</html:option>
																			</html:select></td>
																		</tr>

																		<tr>
																			<td class="style3"><strong>Quantidade de Parcelas:</strong></td>
																			<td colspan="2"><html:text
																				property="quantidadeParcelas" size="5" maxlength="5" />
																			</td>
																		</tr>

																		<tr>
																			<td class="style3"><strong>Valor da Parcela:</strong></td>
																			<td colspan="2"><html:text property="valorParcelas"
																				readonly="true"
																				style="background-color:#EFEFEF; border:0;text-align: right;"
																				size="15" maxlength="15" /></td>

																			<td colspan="4" align="right"><input type="button"
																				class="bottonRightCol" value="Calcular"
																				name="buttonCalcular" tabindex="10"
																				onclick="calcularValores();" style="width: 80px"></td>
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
									</table>
									</td>
								</tr>
							</table>
							<table width="100%">
								<tr>
									<td colspan="2">
									<table width="100%">

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
												onclick="validaForm();"></td>
										</tr>
									</table>
									</td>

								</tr>
								<!--</tr></table></td>-->
							</table>
					</table>
					<!-- Fim do Corpo - Leandro Cavalcanti -->

					<logic:notPresent name="semMenu">
						<%@ include file="/jsp/util/rodape.jsp"%>
					</logic:notPresent>

					</html:form>
</body>
</html:html>
