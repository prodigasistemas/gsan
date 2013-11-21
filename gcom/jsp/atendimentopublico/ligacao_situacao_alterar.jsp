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
	formName="AlterarSituacaoLigacaoActionForm" />
<script language="JavaScript">
	
	function redirecionaSubmit(caminhoAction) {

	   	var form = document.forms[0];
	   	form.action = caminhoAction;
	   	form.submit();
	   	return true;
 	}
	
	function validarForm(form){

		urlRedirect = "/gsan/alterarSituacaoLigacaoAction.do"
		var validou = 0;
		if(validateAlterarSituacaoLigacaoActionForm(form)){
			if(form.indicadorTipoLigacao[0].checked == false && form.indicadorTipoLigacao[1].checked == false && form.indicadorTipoLigacao[2].checked == false){
				alert('Informe Selecione a Ligação a Ser Removida');
				validou = 1;
			}else{
				if(form.indicadorTipoLigacao[0].checked == true && form.situacaoLigacaoAguaNova.value == '-1'){
				alert('Informe a Nova Situação da Ligacao de Água');
				validou = 1;
				}
				if(form.indicadorTipoLigacao[1].checked == true && form.situacaoLigacaoEsgotoNova.value == '-1'){
				alert('Informe a Nova Situação da Ligacao de Esgoto');
				validou = 1;
				}
				if(form.indicadorTipoLigacao[2].checked == true && (form.situacaoLigacaoAguaNova.value == '-1' || form.situacaoLigacaoEsgotoNova.value == '-1')){
					if (form.situacaoLigacaoAguaNova.value == '-1' && form.situacaoLigacaoEsgotoNova.value == '-1') {
						alert('Informe a Nova Situação da Ligacao de Água e Esgoto');

						validou = 1;
					} else if (form.situacaoLigacaoAguaNova.value == '-1') {
						alert('Informe a Nova Situação da Ligacao de Água');
				
						validou = 1;
					} else if (form.situacaoLigacaoEsgotoNova.value == '-1') {
						alert('Informe a Nova Situação da Ligacao de Esgoto');
				
						validou = 1;
					}

				}
	    	}
	    	if(validou == 0){
	    	redirecionaSubmit(urlRedirect);
	    	}
		}
	}
		
	function desabilitaCombos(){
		var form = document.forms[0];
		if(form.indicadorTipoLigacao[0].checked == true){
			form.situacaoLigacaoEsgotoNova.selectedIndex = 0;
			form.situacaoLigacaoEsgotoNova.disabled = true;
		}
		if(form.indicadorTipoLigacao[1].checked == true){
			form.situacaoLigacaoAguaNova.selectedIndex = 0;
			form.situacaoLigacaoAguaNova.disabled = true;
		}
		if(form.indicadorTipoLigacao[2].checked == true){
			form.situacaoLigacaoAguaNova.disabled = false;
			form.situacaoLigacaoEsgotoNova.disabled = false;
		}
	
	}
	
	function redicionaTipoLigacao(){
	
		urlRedirect = "/gsan/exibirAlterarSituacaoLigacaoAction.do?"
	
		desabilitaCombos();
	
		if(testarCampoValorZero(document.AlterarSituacaoLigacaoActionForm.idOrdemServico, 'Ordem de Serviço') ){
			redirecionaSubmit(urlRedirect);
	   	}
	}		
	
	function limparForm() {
		var form = document.AlterarSituacaoLigacaoActionForm;

		form.idOrdemServico.value = "";
		form.nomeOrdemServico.value = "";
		form.matriculaImovel.value = "";
		form.inscricaoImovel.value = "";
		form.clienteUsuario.value = "";
		form.cpfCnpjCliente.value = "";
		form.situacaoLigacaoAguaAtual.value = "";
		form.situacaoLigacaoEsgotoAtual.value = "";
		form.indicadorTipoLigacao[0].checked = false;
		form.indicadorTipoLigacao[1].checked = false;
		form.situacaoLigacaoAguaNova.selectedIndex = 0;
		form.situacaoLigacaoEsgotoNova.selectedIndex = 0;
	}
	
	function limparOrdemServico() {

		var form = document.AlterarSituacaoLigacaoActionForm;

		form.idOrdemServico.value = "";
		form.nomeOrdemServico.value = "";
		form.matriculaImovel.value = "";
		form.inscricaoImovel.value = "";
		form.clienteUsuario.value = "";
		form.cpfCnpjCliente.value = "";
		form.situacaoLigacaoAguaAtual.value = "";
		form.situacaoLigacaoEsgotoAtual.value = "";
		form.indicadorTipoLigacao[0].checked = false;
		form.indicadorTipoLigacao[1].checked = false;
		form.situacaoLigacaoAguaNova.selectedIndex = 0;
		form.situacaoLigacaoEsgotoNova.selectedIndex = 0;
	}
	
	function limparOrdemServicoTecla() {

		var form = document.AlterarSituacaoLigacaoActionForm;

		form.nomeOrdemServico.value = "";
		form.matriculaImovel.value = "";
		form.inscricaoImovel.value = "";
		form.clienteUsuario.value = "";
		form.cpfCnpjCliente.value = "";
		form.situacaoLigacaoAguaAtual.value = "";
		form.situacaoLigacaoEsgotoAtual.value = "";
		form.indicadorTipoLigacao[0].checked = false;
		form.indicadorTipoLigacao[1].checked = false;
		form.indicadorTipoLigacao[2].checked = false;
		form.situacaoLigacaoAguaNova.selectedIndex = 0;
		form.situacaoLigacaoEsgotoNova.selectedIndex = 0;
	}
	//Recupera Dados Popup
	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
		var form = document.forms[0];
	    if (tipoConsulta == 'ordemServico') {
	    	form.idOrdemServico.value = codigoRegistro;
	      	form.action='exibirAlterarSituacaoLigacaoAction.do';
	      	form.submit();
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
					abrirPopup(url + "?" + "tipo=" + tipo + "&" + objeto + "=" + codigoObjeto + "&caminhoRetornoTelaPesquisaOrdemServico=" + tipo, altura, largura);
				}
			}
		}
	}	 
</script>
</head>
<body leftmargin="5" topmargin="5"
	onload="javascript:desabilitaCombos();setarFoco('${requestScope.nomeCampo}');">
<html:form action="/exibirAlterarSituacaoLigacaoAction.do"
	name="AlterarSituacaoLigacaoActionForm"
	type="gcom.gui.atendimentopublico.AlterarSituacaoLigacaoActionForm"
	method="post">
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
			<table height="200%">
				<tr>
					<td></td>
				</tr>
			</table>

			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_left.gif" /></td>
					<td class="parabg">Alterar Situação da Ligação</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>
					<td height="31" colspan="4">
					<table width="100%" border="0" align="center">
						<tr>
							<td height="10" colspan="4">Para alterar os dados da ligação,
							informe abaixo:</td>
						</tr>
						<tr>
							<td height="10" colspan="4">
							<hr>
							</td>
						</tr>
						<tr>
							<td width="150"><strong>Ordem de Servi&ccedil;o:<font color="#FF0000">*</font>
							</strong></td>
							<td><html:text property="idOrdemServico" size="8"
								maxlength="9"
								onkeypress="validaEnterComMensagem(event, 'exibirAlterarSituacaoLigacaoAction.do', 'idOrdemServico','Ordem de Serviço');"
								onkeyup="javascript:limparOrdemServicoTecla()" /> <a
								href="javascript:chamarPopup('exibirPesquisarOrdemServicoAction.do', 'ordemServico', null, null, 600, 500, '',document.forms[0].idOrdemServico);">
							<img width="23" height="21" border="0"
								src="<bean:message key="caminho.imagens"/>pesquisa.gif"
								title="Pesquisar OS" /></a> <logic:present
								name="OrdemServicoInexistente">
								<html:text property="nomeOrdemServico" readonly="true"
									style="background-color:#EFEFEF; border:0; color: #ff0000"
									size="45" maxlength="45" />
							</logic:present> <logic:notPresent name="OrdemServicoInexistente">
								<html:text property="nomeOrdemServico" readonly="true"
									style="background-color:#EFEFEF; border:0; color: #000000"
									size="45" maxlength="45" />
							</logic:notPresent> <a href="javascript:limparForm();"> <img
								border="0" title="Apagar"
								src="<bean:message key='caminho.imagens'/>limparcampo.gif" /> </a></td>
						</tr>
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
											<td width="150" height="10"><strong>Matr&iacute;cula do
											Im&oacute;vel:</strong></td>
											<td><html:text
												property="matriculaImovel" readonly="true"
												style="background-color:#EFEFEF; border:0; color: #000000"
												size="15" maxlength="20" /> <html:text
												property="inscricaoImovel" readonly="true"
												style="background-color:#EFEFEF; border:0; color: #000000"
												size="21" maxlength="20" /></td>
										</tr>
										<tr>
											<td><strong> Cliente Usu&aacute;rio:</strong></td>
											<td><html:text property="clienteUsuario"
												readonly="true"
												style="background-color:#EFEFEF; border:0; color: #000000"
												size="40" maxlength="40" /></td>
										</tr>
										<tr>
											<td><strong>CPF ou CNPJ:</strong></td>
											<td><html:text property="cpfCnpjCliente"
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
					<td colspan="4"><strong>Ligação a ser Alterada:<font
						color="#FF0000">*</font></strong> <strong> <html:radio
						onchange="javascript:redicionaTipoLigacao();"
						property="indicadorTipoLigacao" value="1" />Água<html:radio
						onchange="javascript:redicionaTipoLigacao();"
						property="indicadorTipoLigacao" value="2" />Esgoto<html:radio
						onchange="javascript:redicionaTipoLigacao();"
						property="indicadorTipoLigacao" value="3" />Ambos</strong></td>
				</tr>

				<tr>
					<td height="31" colspan="2">
					<table width="100%" border="0" align="center">
						<tr bgcolor="#cbe5fe">
							<td align="center">
							<table width="100%" border="0" bgcolor="#99CCFF">
								<tr bgcolor="#99CCFF">
									<td height="18" colspan="2">
									<div align="center"><span class="style2">Ligação de Água</span></div>
									</td>
								</tr>
								<tr bgcolor="#cbe5fe">
									<td>
									<table border="0" width="100%">
										<tr>
											<td width="100"><strong>Situação Atual:</strong></td>
											<td><html:text property="situacaoLigacaoAguaAtual"
												readonly="true"
												style="background-color:#EFEFEF; border:0; color: #000000"
												size="15" maxlength="15" /></td>
										</tr>
										<tr>
											<td><strong>Nova Situação:<logic:notEmpty
												name="comboLigacaoAgua">
												<font color="#FF0000">*</font>
											</logic:notEmpty></strong></td>
											<td><html:select property="situacaoLigacaoAguaNova">
												<html:option value="-1">&nbsp;</html:option>
												<html:options collection="colecaoLigacaoAguaSituacao"
													labelProperty="descricao" property="id" />
											</html:select> <font size="1">&nbsp; </font></td>
										</tr>
									</table>
									</td>
									
								</tr>

							</table>
									
									<td>

					<table width="100%" border="0" align="center">

						<tr bgcolor="#cbe5fe">

							<td align="center" colspan="2">

							<table width="100%" border="0" bgcolor="#99CCFF">

								<tr bgcolor="#99CCFF">



									<td height="18" colspan="2">

									<div align="center"><span class="style2">Ligação de Esgoto</span></div>

									</td>

								</tr>

								<tr bgcolor="#cbe5fe">

									<td>

									<table border="0" width="100%">

										<tr>

											<td><strong>Situação Atual:</strong></td>

											<td><html:text property="situacaoLigacaoEsgotoAtual"

												readonly="true"

												style="background-color:#EFEFEF; border:0; color: #000000"

												size="15" maxlength="15" /></td>

										</tr>

										<tr>

											<td><strong>Nova Situação:<logic:notEmpty

												name="comboLigacaoEsgoto">

												<font color="#FF0000">*</font>

											</logic:notEmpty></strong></td>

											<td><html:select property="situacaoLigacaoEsgotoNova">

												<html:option value="-1">&nbsp;</html:option>

												<html:options collection="colecaoLigacaoEsgotoSituacao"

													labelProperty="descricao" property="id" />

											</html:select> <font size="1">&nbsp; </font></td>

										</tr>

									</table>

							</td>

						</tr>

					</table></td>
								</tr>

							</table>
							</td>
							
						</tr>
					</table>
					
					<!-- <td height="31"> --></td>
					

				</tr>
				
				<tr>

							<td height="19" width="150">&nbsp;</td>

							<td>

							<div align="left"><strong><font color="#FF0000">*</font></strong>

							Campos obrigat&oacute;rios</div>

							</td>

				</tr>
				<tr>
					<td colspan="2">
					<table width="100%">
						<tr>
							<td width="40%" align="left" colspan="2"><input type="button"
								name="ButtonReset" class="bottonRightCol" value="Desfazer"
								onClick="limparForm();"> <input type="button"
								name="ButtonCancelar" class="bottonRightCol" value="Cancelar"
								onClick="javascript:window.location.href='/gsan/telaPrincipal.do'">
							</td>

							<td align="right" colspan="2"><input name="Button" type="button"
								class="bottonRightCol" value="Alterar"
								onClick="javascript:validarForm(document.forms[0]);"></td>
						</tr>
					</table>
					</td>
				</tr>
			</table>
	</table>



	<!--</tr></table></td>-->
	<!-- Fim do Corpo - Romulo Aurelio-->
	<logic:notPresent name="semMenu">
		<%@ include file="/jsp/util/rodape.jsp"%>
	</logic:notPresent>
</html:form>
</body>

</html:html>

