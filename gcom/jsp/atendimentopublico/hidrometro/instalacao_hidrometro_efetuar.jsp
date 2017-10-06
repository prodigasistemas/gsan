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
	formName="EfetuarInstalacaoHidrometroActionForm" />

<script language="JavaScript">
  	function validaForm(){
    	var form = document.forms[0];
	  	
	  	if(validateEfetuarInstalacaoHidrometroActionForm(form) && validaDebito()){
			if(validaTodosRadioButton()){		     
		  		submeterFormPadrao(form);
		  			
   	    	}
   	  	}
  	}
  	
  	function validaRadioButton(nomeCampo,mensagem){
		
		var alerta = "";
		if(!nomeCampo[0].checked && !nomeCampo[1].checked){
			alerta = "Informe " + mensagem +".";
		}
		return alerta;
   	}
   
  	function validaTodosRadioButton(){
		
		var form = document.forms[0];
		var mensagem = "";
		
		if(validaRadioButton(form.tipoMedicao,"Tipo de Medição") != ""){
			mensagem = mensagem + validaRadioButton(form.tipoMedicao,"Tipo de Medição")+"\n";
		}
		
		if(validaRadioButton(form.situacaoCavalete,"Situação do Cavalete") != ""){
			mensagem = mensagem + validaRadioButton(form.situacaoCavalete,"Situação do Cavalete")+"\n";
		}
		
		if(mensagem != ""){
			alert(mensagem);
			return false;
		}else{
			return true;
		}
   	}
	 
	function limparForm() {		var form = document.forms[0];	    form.action='exibirEfetuarInstalacaoHidrometroAction.do?menu=sim';	    form.submit();
	}
	 
	 function limparDecricaoEfetuar() {
	
		var form = document.EfetuarInstalacaoHidrometroActionForm;
	
		form.nomeOrdemServico.value = "";
		form.matriculaImovel.value = "";
		form.inscricaoImovel.value = "";
		form.clienteUsuario.value = "";
		form.cpfCnpjCliente.value = "";
		form.situacaoLigacaoAgua.value = "";
		form.situacaoLigacaoEsgoto.value = "";
		form.dataInstalacao.value = "";	
	    if(form.idTipoDebito !=null){
	      form.idTipoDebito.value = "";
	      form.descricaoTipoDebito.value = "";
		  form.valorDebito.value = "";
		  if(form.motivoNaoCobranca != null){
		    form.motivoNaoCobranca.selectedIndex = 0;
		  }
		  form.percentualCobranca.selectedIndex = 0;
		  form.quantidadeParcelas.value = "";
		  form.valorParcelas.value = "";
	    }
	 }  
	  
	function limparNumeroHidrometro(){
		var form = document.EfetuarInstalacaoHidrometroActionForm;
	   	form.numeroHidrometro.value = "";
	}
	
	function limparUsuario(){
		var form = document.EfetuarInstalacaoHidrometroActionForm;
		
	   	form.idFuncionario.value = "";
	   	form.nomeUsuario.value = "";
	   	form.usuarioNaoEncontrado.value = "";
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
   
    	if (tipoConsulta == 'hidrometro') {

      		form.numeroHidrometro.value = codigoRegistro;
	      	form.action='exibirEfetuarInstalacaoHidrometroAction.do';
	      	form.submit();
      		
    	}else if (tipoConsulta == 'ordemServico') {

	    	form.idOrdemServico.value = codigoRegistro;
	      	form.action='exibirEfetuarInstalacaoHidrometroAction.do';
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
	
		var form = document.EfetuarInstalacaoHidrometroActionForm;
	   	if (validateEfetuarInstalacaoHidrometroActionForm(form) && validaDebito()){
	   	
	   		form.action='exibirEfetuarInstalacaoHidrometroAction.do?calculaValores=S';
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
	
	function pesquisarHidrometro() {
		var form = document.forms[0];
		
		if (form.idLocalArmazenagem.value != null && form.idLocalArmazenagem.value != "") {
			abrirPopup('exibirPesquisarHidrometroAction.do?limparForm=sim&idLocalArmazenagem=' + form.idLocalArmazenagem.value, 600, 640);
		} else {
			abrirPopup('exibirPesquisarHidrometroAction.do?limparForm=sim', 600, 640);
		}
	
	}
	

</script>

</head>

<body leftmargin="5" topmargin="5"
	onload="verificaForm();setarFoco('${requestScope.nomeCampo}');">

<html:form action="/efetuarInstalacaoHidrometroAction.do"
	name="EfetuarInstalacaoHidrometroActionForm"
	type="gcom.gui.atendimentopublico.hidrometro.EfetuarInstalacaoHidrometroActionForm"
	method="post">

	<html:hidden property="veioEncerrarOS" />
	<html:hidden property="qtdeMaxParcelas" />

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
									<td class="parabg">Efetuar Instalação de Hidrômetro</td>
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
											<td height="10" colspan="2">Para efetuar a instalação do
											hidrômetro, informe os dados abaixo:</td>
										</tr>
										<tr>											<html:hidden property="idLocalArmazenagem" />
											<td height="10"><strong>Ordem de Servi&ccedil;o:<strong><font
												color="#FF0000">*</font></strong></strong></td>
											<td><!-- Campo Ordem de Serviço --> <html:text
												property="idOrdemServico" size="12" maxlength="10"
												onkeypress="validaEnterComMensagem(event, 'exibirEfetuarInstalacaoHidrometroAction.do', 'idOrdemServico','Ordem de Serviço');return isCampoNumerico(event);"
												onkeyup="javascript:limparDecricaoEfetuar()" /> <a
												href="javascript:chamarPopup('exibirPesquisarOrdemServicoAction.do', 'ordemServico', null, null, 600, 500, '',document.forms[0].idOrdemServico);">
											<img src="/gsan/imagens/pesquisa.gif" alt="Pesquisar"
												border="0" height="21" width="23"></a> <logic:present
												name="ordemServicoEncontrado">
												<logic:equal name="ordemServicoEncontrado" value="exception">
													<html:text property="nomeOrdemServico" size="50"
														maxlength="40" readonly="true"
														style="background-color:#EFEFEF; border:0; color: #ff0000" />
												</logic:equal>
												<logic:notEqual name="ordemServicoEncontrado"
													value="exception">
													<html:text property="nomeOrdemServico" size="50"
														maxlength="40" readonly="true"
														style="background-color:#EFEFEF; border:0; color: #000000" />
												</logic:notEqual>
											</logic:present> <logic:notPresent
												name="ordemServicoEncontrado">
												<logic:empty name="EfetuarInstalacaoHidrometroActionForm"
													property="idOrdemServico">
													<html:text property="nomeOrdemServico" size="50"
														maxlength="40" readonly="true"
														style="background-color:#EFEFEF; border:0; color: #ff0000" />
												</logic:empty>
												<logic:notEmpty name="EfetuarInstalacaoHidrometroActionForm"
													property="idOrdemServico">
													<html:text property="nomeOrdemServico" size="50"
														maxlength="40" readonly="true"
														style="background-color:#EFEFEF; border:0; color: #000000" />
												</logic:notEmpty>
											</logic:notPresent> <a href="javascript:limparForm()"> <img
												border="0" title="Apagar"
												src="<bean:message key='caminho.imagens'/>limparcampo.gif" /></a></td>
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
															<td width="37%" height="10"><strong>Matr&iacute;cula do
															Im&oacute;vel:<strong></strong></strong></td>
															<td width="58%"><html:text property="matriculaImovel"
																readonly="true"
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
																size="50" maxlength="50" /></td>
														</tr>
														<tr>
															<td><strong>CPF ou CNPJ:</strong></td>
															<td><html:text property="cpfCnpjCliente" readonly="true"
																style="background-color:#EFEFEF; border:0; color: #000000"
																size="20" maxlength="20" /></td>
														</tr>
														<tr>
															<td><strong>Situa&ccedil;&atilde;o da
															Liga&ccedil;&atilde;o de &Aacute;gua:</strong></td>
															<td><html:text property="situacaoLigacaoAgua"
																readonly="true"
																style="background-color:#EFEFEF; border:0; color: #000000"
																size="20" maxlength="20" /></td>
														</tr>
														<tr>
															<td><strong>Situa&ccedil;&atilde;o da
															Liga&ccedil;&atilde;o de Esgoto:</strong></td>

															<td><html:text property="situacaoLigacaoEsgoto"
																readonly="true"
																style="background-color:#EFEFEF; border:0; color: #000000"
																size="20" maxlength="20" /></td>
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
													Instalação do Hidrômetro</span></div>
													</td>
												</tr>
												<tr bgcolor="#cbe5fe">
													<td>
													<table border="0" width="100%">
														<tr>
															<td width="37%" class="style3"><strong>N&uacute;mero do
															Hidr&ocirc;metro:<font color="#FF0000">*</font></strong></td>

															<td width="58%"><html:text property="numeroHidrometro"
																size="13" maxlength="11"
																onkeypress="validaEnterString(event, 'exibirEfetuarInstalacaoHidrometroAction.do', 'numeroHidrometro','Número do Hidrômetro');" />
															<a
																href="javascript:pesquisarHidrometro()">
															<img src="/gsan/imagens/pesquisa.gif" alt="Pesquisar"
																border="0" height="21" width="23"></a><a
																href="javascript:limparNumeroHidrometro()"> <img
																border="0" title="Apagar"
																src="<bean:message key='caminho.imagens'/>limparcampo.gif" /></a>
															</td>
														</tr>
														<tr>
															<td height="10"><strong>Data da Instalação:<font
																color="#FF0000">*</font></strong></td>
															<td colspan="2"><strong><b> <!-- Campo Data da Ligacao -->
															<html:text property="dataInstalacao" readonly="true"
																style="background-color:#EFEFEF; border:0;" size="10"
																maxlength="10" /> </b></strong></td>
														</tr>
														<tr>
															<td><strong>Tipo de Medição:<font color="#FF0000">*</font></strong></td>
															<td><strong> <html:radio property="tipoMedicao" value="1"
																disabled="true"/> <strong>LIGAÇÃO DE ÁGUA <html:radio
																property="tipoMedicao" value="2" disabled="true"/>
															POÇO</strong> </strong></td>
														</tr>
														<tr>
															<td><strong>Local de Instalação:<font color="#FF0000">*</font></strong></td>
															<td><html:select property="localInstalacao" tabindex="4">
																<html:option value="-1">&nbsp;</html:option>
																<html:options
																	collection="colecaoHidrometroLocalInstalacao"
																	labelProperty="descricao" property="id" />
															</html:select> <font size="1">&nbsp; </font></td>
														</tr>
														<tr>
															<td><strong>Proteção:<font color="#FF0000">*</font></strong></td>
															<td><html:select property="protecao" tabindex="4">
																<html:option value="-1">&nbsp;</html:option>
																<html:options collection="colecaoHidrometroProtecao"
																	labelProperty="descricao" property="id" />
															</html:select> <font size="1">&nbsp; </font></td>
														</tr>
														
														<tr>
															<td><strong>Troca de Proteção:</strong></td>
															<td><strong> 
																<html:radio property="indicadorTrocaProtecao" value="1"/><strong>SIM 
																<html:radio	property="indicadorTrocaProtecao" value="2"/>NÃO</strong>
															</strong></td>
														</tr>
														
														<tr>
															<td><strong>Troca de Registro:</strong></td>
															<td><strong> 
																<html:radio property="indicadorTrocaRegistro" value="1"/><strong>SIM 
																<html:radio	property="indicadorTrocaRegistro" value="2"/>NÃO</strong>
															</strong></td>
														</tr>
														
														<tr>
															<td class="style3"><strong>Leitura Instalação:<font color="#FF0000">*</font></strong></td>
															<td colspan="2"><strong><b><span class="style2"> <html:text
																property="leituraInstalacao" size="6" maxlength="6"
																onkeyup="" onkeydown="" onkeypress="return isCampoNumerico(event);" /> </span></b></strong></td>
														</tr>
														<tr>
															<td class="style3"><strong>Número do Selo:</strong></td>
															<td colspan="2"><strong><b><span class="style2"> <html:text
																property="numeroSelo" size="12" maxlength="12"
																onkeyup="" onkeydown="" onkeypress="" /> </span></b></strong></td>
														</tr>
														<tr>
															<td><strong>Cavalete:<font color="#FF0000">*</font></strong></td>
															<td><strong> <html:radio property="situacaoCavalete"
																value="1" /> <strong>COM <html:radio
																property="situacaoCavalete" value="2" /> SEM</strong> </strong></td>
														</tr>

														<tr>
															<td class="style3"><strong>N&uacute;mero do Lacre:</strong></td>
															<td colspan="2"><strong><b><span class="style2"> <html:text
																property="numeroLacre" size="10" maxlength="10"
																onkeyup="" onkeydown="" onkeypress="" /> </span></b></strong></td>
														</tr>
														
														<tr>
															<logic:present name="medicaoTipoPoco">
																<td><strong>Tipo do Poço:</strong></td>
	
																<td><html:select property="tipoPoco" tabindex="4">
	
																	<html:option value="-1">&nbsp;</html:option>
	
																	<html:options
	
																		collection="colecaoTipoPoco"
	
																		labelProperty="descricao" property="id" />
	
																</html:select> <font size="1">&nbsp; </font></td>
															</logic:present>
															
															<logic:notPresent name="medicaoTipoPoco">
																<td><strong>Tipo do Poço:</strong></td>
	
																<td><html:select property="tipoPoco" tabindex="4" disabled="true">
	
																	<html:option value="-1">&nbsp;</html:option>
	
																	<html:options
	
																		collection="colecaoTipoPoco"
	
																		labelProperty="descricao" property="id" />
	
																</html:select> <font size="1">&nbsp; </font></td>
															</logic:notPresent>
															
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

								<%-- Dados da Geração --%>
								<logic:notEmpty name="EfetuarInstalacaoHidrometroActionForm"
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
														Gera&ccedil;&atilde;o do D&eacute;bito</span></div>
														</td>
													</tr>
													<tr bgcolor="#cbe5fe">
														<td>
														<table border="0" width="100%">
															<tr>
																<td width="37%" class="style3"><strong> Tipo de
																D&eacute;bito: </strong></td>
																<td width="58%"><html:text property="idTipoDebito"
																	readonly="true"
																	style="background-color:#EFEFEF; border:0; color: #000000"
																	size="5" maxlength="5" /> <html:text
																	property="descricaoTipoDebito" readonly="true"
																	style="background-color:#EFEFEF; border:0; color: #000000"
																	size="35" maxlength="50" /></td>
															</tr>
															
															<!-- Colocado por Raphael Rossiter em 20/04/2007 -->
															<logic:notEqual name="EfetuarInstalacaoHidrometroActionForm" property="alteracaoValor" value="OK">
															<tr>
																<td height="10"><strong> Valor do D&eacute;bito: </strong>
																</td>
																<td><html:text property="valorDebito" readonly="true"
																	style="background-color:#EFEFEF; border:0; color: #000000; text-align: right;"
																	size="20" maxlength="20" /></td>
															</tr>
															</logic:notEqual>
															
															<logic:equal name="EfetuarInstalacaoHidrometroActionForm" property="alteracaoValor" value="OK">
															<tr>
																<td height="10"><strong> Valor do D&eacute;bito: </strong>
																</td>
																<td><html:text property="valorDebito" 
																	style="text-align: right;"
																	size="20" maxlength="20" /></td>
															</tr>
															</logic:equal>
															
															
															<logic:present name="permissaoMotivoNaoCobranca">
																<tr>
																	<td><strong> Motivo da N&atilde;o Cobran&ccedil;a: </strong>
																	</td>
																	<td><html:select property="motivoNaoCobranca"
																		tabindex="4" onchange="habilitarQtdParcelaButton();">
																		<html:option value="-1">&nbsp;</html:option>
																		<logic:present name="colecaoMotivoNaoCobranca"
																			scope="session">
																			<html:options collection="colecaoMotivoNaoCobranca"
																				labelProperty="descricao" property="id" />
																		</logic:present>
																	</html:select> <font size="1">&nbsp; </font></td>
																</tr>
																<tr>
																	<td class="style3"><strong>Percentual de Cobrança:<span
																		class="style2"><strong><strong><strong><font
																		color="#FF0000">*</font></strong></strong></strong></span></strong></td>
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
																	<td class="style3"><strong>Quantidade de Parcelas:<span
																		class="style2"><strong><strong><strong><font
																		color="#FF0000">*</font></strong></strong></strong></span></strong></td>
																	<td colspan="2"><strong> <html:text
																		property="quantidadeParcelas" size="6" maxlength="6" /> </strong></td>
																</tr>
																<tr>
																	<td class="style3"><strong> Valor da Parcela: </strong>
																	</td>
																	<td colspan="1"><strong> <b> <span class="style2"> <html:text
																		property="valorParcelas" size="10" maxlength="10"
																		readonly="true"
																		style="background-color:#EFEFEF; border:0; color: #000000; text-align: right;" />
																	</span> </b> </strong></td>
																	
																	
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

										<tr>
											<td width="40%" align="left">												<logic:present
												name="caminhoRetornoIntegracaoComercial">
												<INPUT TYPE="button" class="bottonRightCol" value="Voltar"
													onclick="redirecionarSubmit('${caminhoRetornoIntegracaoComercial}')" />
												</logic:present> 																							<input name="Button" type="button"												class="bottonRightCol" value="Limpar" align="left"												onclick="window.location.href='<html:rewrite page="/exibirEfetuarInstalacaoHidrometroAction.do?menu=sim"/>'">																																				<input type="button"
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
					<!-- Fim do Corpo-->

					<logic:notPresent name="semMenu">
						<%@ include file="/jsp/util/rodape.jsp"%>
					</logic:notPresent>

					</html:form>

					<script language="JavaScript">

</script>
					</html:html>