<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>


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

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false" 
	formName="AtualizarConsumoMinimoLigacaoAguaActionForm" />

<script language="JavaScript">
	function validaForm() {
		var form = document.AtualizarConsumoMinimoLigacaoAguaActionForm;
		if (validateAtualizarConsumoMinimoLigacaoAguaActionForm(form)){
			if(form.consumoMinimoFixado.value.length > 0) {
		    	if (validarValorMinimo()) {
			   		submeterFormPadrao(form);
				}
			} else {
				submeterFormPadrao(form);
			}
		}
	}
	
	/* Valida Volume Mínimo */
	function validarValorMinimo () {
		var form = document.AtualizarConsumoMinimoLigacaoAguaActionForm;
		
		if (new Number(form.consumoMinimoFixado.value) < new Number(form.valorObtido.value)) {
			alert('Volume mínimo informado não deve ser menor que o valor mínimo calculado do imóvel '+form.valorObtido.value+' m³.');
			return false;
		}
		
		if (new Number(form.consumoMinimoFixado.value) == 1) {
			alert('Valor do Consumo Mínimo Inválido.');
			return false;
		}
		
		var respostaJanela;
		var modConsumoMinimo = parseInt(form.consumoMinimoFixado.value) % parseInt(form.qtdeEconomia.value);
		/* Testa se é multiplo */
		if (modConsumoMinimo > 0) {
			var novoConsumoMinimo = parseInt(form.consumoMinimoFixado.value) - modConsumoMinimo;
			var msg = 'Valor do volume mínimo deverá alterado para '+novoConsumoMinimo+' valor múltiplo de quantidade de economias '+form.qtdeEconomia.value+'.';
			respostaJanela=window.confirm(msg);
			if (respostaJanela == true) {
				form.consumoMinimoFixado.value = novoConsumoMinimo;
			} else {
				return false;
			}
		}
		return true;
	}
	
	function limparForm() {
		var form = document.AtualizarConsumoMinimoLigacaoAguaActionForm;
		if(!form.idOrdemServico.disabled){
		form.idOrdemServico.value = "";
		limparOS();
		}
	}
	
	function limparOS() {
		var form = document.AtualizarConsumoMinimoLigacaoAguaActionForm;
		
		form.nomeOrdemServico.value = "";
		form.matriculaImovel.value = "";
		form.inscricaoImovel.value = "";
		form.clienteUsuario.value = "";
		form.cpfCnpjCliente.value = "";
		form.situacaoLigacaoAgua.value = "";
		form.situacaoLigacaoEsgoto.value = "";
		form.categoriaImovel.value = "";		
		form.qtdeEconomia.value = "";
		form.consumoMinimoFixado.value = "";
	}
	
	//verifica se existe alguma restrição 
	//para exibição alguma campo no formulario
    function verificaForm() {

       	var form = document.AtualizarConsumoMinimoLigacaoAguaActionForm;

		if(form.veioEncerrarOS.value == 'true'){
			form.idOrdemServico.disabled=true;
		}else{
			form.idOrdemServico.disabled=false;
		}       	
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
					abrirPopup(url + "?" + "menu=sim&" + "tipo=" + tipo + "&" + objeto + "=" + codigoObjeto + "&caminhoRetornoTelaPesquisaOrdemServico=" + tipo, altura, largura);
				}
			}
		}
	}
	
</script>
</head>

<body leftmargin="5" topmargin="5" onload="javascript:setarFoco('${requestScope.nomeCampo}');verificaForm();">

<html:form
	action="/atualizarConsumoMinimoLigacaoAguaAction.do"
	name="AtualizarConsumoMinimoLigacaoAguaActionForm"
	type="gcom.gui.atendimentopublico.ligacaoagua.AtualizarConsumoMinimoLigacaoAguaActionForm"
	method="post">

	<html:hidden property="valorObtido"/>
	
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
			</logic:notPresent>
			<logic:present name="semMenu">
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
					<td class="parabg">Atualizar Consumo Mínimo da Ligação de Água</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>


			<!--Inicio da Tabela Corte Ligação Água -->
			<table width="100%" border="0">
				<tr>

					<td height="31">
					<table width="100%" border="0" align="center">
						<tr>
							<td height="10" colspan="2">Para atualização do consumo mínimo da ligação de água, informe os dados abaixo:</td>
						</tr>
						<tr>
							<td height="10" colspan="2">
							<hr>
							</td>
						</tr>

						<tr>
						<td height="10"><strong>Ordem de Servi&ccedil;o:<span
								class="style2"><strong><font color="#FF0000">*</font></strong></span></strong></td>
							<td><span class="style2">
							<!-- Campo Ordem de Serviço --> 
							<html:text property="idOrdemServico" size="9" maxlength="9"
   									   onkeypress="javascript:validaEnterComMensagem(event, 'exibirAtualizarConsumoMinimoLigacaoAguaAction.do?validaOS=true', 'idOrdemServico','Ordem de Serviço');"
   									   onkeyup="limparOS();"/>
							<img src="<bean:message key='caminho.imagens'/>pesquisa.gif" width="23"	height="21" 
									 style="cursor:hand;" name="imagem"	onclick="chamarPopup('exibirPesquisarOrdemServicoAction.do', 'ordemServico', null, null, 275, 480, '',document.forms[0].idOrdemServico);"
									 alt="Pesquisar">
							<logic:present name="osEncontrada" scope="session">
								<html:text property="nomeOrdemServico" readonly="true"
	   									   style="background-color:#EFEFEF; border:0; color: #000000" size="50"/>
							</logic:present> 

							<logic:notPresent name="osEncontrada" scope="session">
								<html:text property="nomeOrdemServico" readonly="true"
	   									   style="background-color:#EFEFEF; border:0; color: #ff0000" size="50"/>
							</logic:notPresent>   									 
							<a href="javascript:limparForm();">
								<img border="0" title="Apagar" src="<bean:message key='caminho.imagens'/>limparcampo.gif"/></a>
							</span></td>
						</tr>
						<tr bgcolor="#cbe5fe">
							<td align="center" colspan="2">
							<table width="100%" border="0" bgcolor="#99CCFF">
								<tr bgcolor="#99CCFF">
									<td height="18" colspan="2">
									<div align="center"><span class="style2"><strong>Dados do Imóvel </strong> </span></div>
									</td>
								</tr>
								<tr bgcolor="#cbe5fe">

									<td>
									<table border="0" width="100%">
										<tr>
											<td width="37%" height="10"><strong><span class="style2">Matr&iacute;cula
											do Im&oacute;vel:<strong></strong></span></strong></td>
											<td width="58%"><html:text property="matriculaImovel"
												readonly="true" style="background-color:#EFEFEF; border:0;"
												size="10" maxlength="20" /> <html:text
												property="inscricaoImovel" readonly="true"
												style="background-color:#EFEFEF; border:0;" size="20"
												maxlength="15" /></td>
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
												style="background-color:#EFEFEF; border:0;" size="20"
												maxlength="20" /></td>
										</tr>
										<tr>

											<td><strong>Situa&ccedil;&atilde;o da Liga&ccedil;&atilde;o
											de &Aacute;gua:</strong></td>
											<td><html:text property="situacaoLigacaoAgua" readonly="true"
												style="background-color:#EFEFEF; border:0;" size="20"
												maxlength="20" /></td>
										</tr>
										<tr>
											<td><strong>Situa&ccedil;&atilde;o da Liga&ccedil;&atilde;o
											de Esgoto:</strong></td>
											<td><html:text property="situacaoLigacaoEsgoto"
												readonly="true" style="background-color:#EFEFEF; border:0;"
												size="20" maxlength="40" /></td>
										</tr>
										
										<tr>
											<td><strong>Categoria do Imóvel:</strong></td>
											<td><html:text property="categoriaImovel" readonly="true"
												style="background-color:#EFEFEF; border:0;" size="40"
												maxlength="40" /></td>
										</tr>
										<tr>
											<td><strong>Quantidade de Economias:</strong></td>
											<td><html:text property="qtdeEconomia"
												readonly="true" style="background-color:#EFEFEF; border:0;"
												size="10" maxlength="3" /></td>
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
									<div align="center"><span class="style2"><strong>  Dados do Consumo Mínimo da Ligação de Água </strong></span></div>
									</td>
								</tr>
								<tr bgcolor="#cbe5fe">
									<td>
									<table border="0" width="100%">
										<tr>
											<td width="39%" height="10"><strong>Consumo Mínimo:</strong></td>
											<td colspan="2"><strong><b>
											<html:text property="consumoMinimoFixado" readonly="false" size="10" maxlength="6" /></b></strong><strong><b> m³</b></strong></td>
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
					<td colspan="2">
					<table width="100%">

						<tr>
							<td width="40%" align="left">
							<logic:present name="caminhoRetornoIntegracaoComercial">
								<INPUT TYPE="button" 
									class="bottonRightCol" 
									value="Voltar" 
									onclick="redirecionarSubmit('${caminhoRetornoIntegracaoComercial}')"/>
							</logic:present>
							<input type="button"
								name="ButtonReset" class="bottonRightCol" value="Desfazer"
								onClick="limparForm();"> <input type="button"
								name="ButtonCancelar" class="bottonRightCol" value="Cancelar"
								onClick="javascript:window.location.href='/gsan/telaPrincipal.do'">
							</td>
							<td align="right"><input name="Button" type="button"
								class="bottonRightCol" value="Atualizar" onclick="validaForm();">
							</td>
						</tr>
					</table>
					</td>

				</tr>
				<!--</tr></table></td>-->
			</table>
	</table>
	<logic:notPresent name="semMenu">
		<%@ include file="/jsp/util/rodape.jsp"%>
	</logic:notPresent>
</html:form>

</body>
</html:html>