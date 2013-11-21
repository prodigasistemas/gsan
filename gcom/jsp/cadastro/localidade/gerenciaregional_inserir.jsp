<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan"%>

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
	src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false"
	formName="InserirGerenciaRegionalActionForm" />

<SCRIPT LANGUAGE="JavaScript">
<!--


function validarForm(formulario){

	if (validateInserirGerenciaRegionalActionForm(formulario)){
	
		var objCodigo = returnObject(formulario, "Codigo");
		var objNome = returnObject(formulario, "nome");
		var enderecoInformado = document.getElementById("botaoEndereco");
		var objTelefone = returnObject(formulario, "telefone");
		var objRamal = returnObject(formulario, "ramal");
		var objFax = returnObject(formulario, "fax");
		var objIndicadorUso = returnObject(formulario, "Indicador de Uso");
		//var cnpjGerenciaReg = formulario.cnpjGerenciaRegional.value;

	
		if (!testarCampoValorZero(objCodigo, "Código")){
			objCodigo.focus();
		}
		else if(objRamal.value.length > 0){
			if (!testarCampoValorZero(objRamal, "Ramal")){
				objRamal.focus();
			}
			else if(objTelefone.value.length < 1){
				alert("Informe Telefone.");
				objTelefone.focus();
			}
			else if (objTelefone.value.length > 0 && !testarCampoValorZero(objTelefone, "Telefone")){
				objTelefone.focus();
			}
			else if (objTelefone.value.length > 0 && objTelefone.value.length < 7){
				alert("Telefone deve conter no mínimo 7 dígitos");
				objTelefone.focus();
			}
			else if (objFax.value.length > 0 && !testarCampoValorZero(objFax, "Fax")){
				objFax.focus();
			}
			else if (objFax.value.length > 0 && objFax.value.length < 7){
				alert("Fax deve conter no mínimo 7 dígitos");
				objFax.focus();
			}

			else {
				formulario.action = "/gsan/inserirGerenciaRegionalAction.do";
				submeterFormPadrao(formulario);	
			}
		}
		else if (objTelefone.value.length > 0 && !testarCampoValorZero(objTelefone, "Telefone")){
			objTelefone.focus();
		}
		else if (objTelefone.value.length > 0 && objTelefone.value.length < 7){
			alert("Telefone deve conter no mínimo 7 dígitos");
			objTelefone.focus();
		}
		else if (objFax.value.length > 0 && !testarCampoValorZero(objFax, "Fax")){
			objFax.focus();
		}
		else if (objFax.value.length > 0 && objFax.value.length < 7){
			alert("Fax deve conter no mínimo 7 dígitos");
			objFax.focus();
//		}
//		else if (cnpjGerenciaReg != "" && cnpjGerenciaReg.length < 14){
///		alert("CNPJ Inválido");
//		cnpjGerenciaReg.focus();

		} else {
			formulario.action = "/gsan/inserirGerenciaRegionalAction.do";
			submeterFormPadrao(formulario);
		}
	}
	
}

function remover(){
	var form = document.forms[0];

	document.getElementById('limparCampos').value='1';
	form.removerEndereco.value = "1";
	form.submit();
}


function redirecionarSubmitAtualizar(idGerenciaRegional) {
	urlRedirect = "/gsan/exibirAtualizarGerenciaRegionalAction.do?idRegistroInseridoAtualizar=" + idGerenciaRegional;
	redirecionarSubmit(urlRedirect);
}

function limparCliente() {
	var form = document.InserirGerenciaRegionalActionForm;
	form.idCliente.value = "";
	form.nomeCliente.value = "";

}

//Recupera Dados Popup
function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
	var form = document.forms[0];
    if (tipoConsulta == 'cliente') {
    	form.idCliente.value = codigoRegistro;
    	form.nomeCliente.value = descricaoRegistro;
    }
}

	function pesquisarGerente(event) {
		validaEnterComMensagem(event, 'exibirInserirGerenciaRegionalAction.do?objetoConsulta=1&limparCampos=1', 'idCliente','Gerente da Gerência Regional');
	}

//-->
</SCRIPT>

</head>

<body leftmargin="5" topmargin="5"
	onload="document.forms[0].removerEndereco.value=''; setarFoco('${requestScope.nomeCampo}');">

<html:form action="/exibirInserirGerenciaRegionalAction" method="post">

	<INPUT TYPE="hidden" name="removerEndereco">
	<INPUT TYPE="hidden" name="limparCampos" id="limparCampos">


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

			<td width="625" valign="top" class="centercoltext">

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
					<td class="parabg">Inserir Gerência Regional</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>

			<table width="100%" border="0">
				<tr>
					<td colspan="2">Para adicionar uma Gerência Regional, informe os
					dados abaixo:</td>
					<!-- Banco -->
				<tr>
					<td><strong>Nome :<font color="#FF0000">*</font></strong></td>
					<td colspan="2"><html:text property="nome" size="25" maxlength="25"
						tabindex="1" /></td>
				</tr>

				<tr>
					<td><strong>Nome Abreviado:<font color="#FF0000">*</font></strong></td>
					<td colspan="2"><html:text property="nomeAbreviado" size="3"
						maxlength="3" tabindex="2" /></td>
				</tr>
				
				<tr>
					<td height="30">
						<strong>CNPJ:</strong>
					</td>
					<td>
						<html:text property="cnpjGerenciaRegional" size="14" maxlength="14" onkeypress="return isCampoNumerico(event);" />
					</td>
				</tr>



				<tr>
					<td><strong>Endere&ccedil;o:<font color="#FF0000">*</font></strong></td>
					<td align="right">
					
					<logic:present name="colecaoEnderecos">
						<logic:empty name="colecaoEnderecos">
							<input type="button" class="bottonRightCol" value="Adicionar"
								tabindex="3" id="botaoEndereco"
								onclick="javascript:abrirPopup('exibirInserirEnderecoAction.do?tipoPesquisaEndereco=gerenciaRegional&operacao=1', 570, 700);">
						</logic:empty>
						<logic:notEmpty name="colecaoEnderecos">
							<input type="button" class="bottonRightCol" value="Adicionar"
								tabindex="3" id="botaoEndereco"
								onclick="javascript:abrirPopup('exibirInserirEnderecoAction.do?tipoPesquisaEndereco=gerenciaRegional&operacao=1', 570, 700);"
								disabled>
						</logic:notEmpty>
					</logic:present> 
					
					<logic:notPresent name="colecaoEnderecos">
						<input type="button" class="bottonRightCol" value="Adicionar"
							tabindex="3" id="botaoEndereco"
							onclick="javascript:abrirPopup('exibirInserirEnderecoAction.do?tipoPesquisaEndereco=gerenciaRegional&operacao=1', 570, 700);">
					</logic:notPresent></td>
				</tr>
				<tr>
					<td colspan="2" height="70" valign="top">
					<table width="100%" cellpadding="0" cellspacing="0">
						<tr>
							<td>
							<table width="100%" border="0" bgcolor="#90c7fc">
								<tr bgcolor="#90c7fc" height="18">
									<td width="10%" align="center"><strong>Remover</strong></td>
									<td align="center"><strong>Endere&ccedil;o</strong></td>
								</tr>
							</table>
							</td>
						</tr>

						<logic:present name="colecaoEnderecos">

							<tr>
								<td>
								<table width="100%" align="center" bgcolor="#99CCFF">
									<!--corpo da segunda tabela-->

									<%String cor = "#cbe5fe";%>

									<logic:iterate name="colecaoEnderecos" id="endereco">

										<%if (cor.equalsIgnoreCase("#cbe5fe")) {
								cor = "#FFFFFF";%>
										<tr bgcolor="#FFFFFF" height="18">
											<%} else {
								cor = "#cbe5fe";%>
										<tr bgcolor="#cbe5fe" height="18">
											<%}%>

											<td width="10%" align="center"><img
												src="<bean:message key='caminho.imagens'/>Error.gif"
												width="14" height="14" style="cursor:hand;" alt="Remover"
												onclick="javascript:if(confirm('Confirma remoção?')){remover();}"></td>



											<td><a
												href="javascript:abrirPopup('exibirInserirEnderecoAction.do?exibirEndereco=OK&tipoPesquisaEndereco=gerenciaRegional&operacao=1', 570, 700)"><bean:write
												name="endereco" property="enderecoFormatado" /></a></td>
										</tr>
									</logic:iterate>

								</table>
								</td>
							</tr>

						</logic:present>

					</table>
					</td>
				</tr>
				<tr>
					<td><strong>Telefone:</strong></td>
					<td><html:text property="telefone" size="10" maxlength="9"
						tabindex="4" /></td>
				</tr>
				<tr>
					<td><strong>Ramal:</strong></td>
					<td><html:text property="ramal" size="5" maxlength="4" tabindex="5" /></td>
				</tr>
				<tr>
					<td><strong>Fax:</strong></td>
					<td><html:text property="fax" size="10" maxlength="9" tabindex="6" /></td>
				</tr>
				<tr>
					<td><strong>E-mail:</strong></td>
					<td><html:text property="email" size="50" maxlength="40"
						tabindex="7" /></td>
				</tr>

				<tr>
					<td height="30" width="130"><strong>Gerente da Gerência Regional:</strong></td>
					<td colspan="2"><html:text property="idCliente" size="10"
						maxlength="9" tabindex="8" 
						onkeypress="pesquisarGerente(event);return isCampoNumerico(event);" />
						<a href="javascript:abrirPopup('exibirPesquisarClienteAction.do', 'cliente', null, null, 800, 490, '',document.forms[0].idCliente);"
						title="Pesquisar Gerente" >
					<img src="<bean:message key='caminho.imagens'/>pesquisa.gif" width="23" height="21" alt="Pesquisar" border="0"></a> 
						
						<logic:notPresent name="corCliente" scope="request">

						<html:text property="nomeCliente" readonly="true"
							style="background-color:#EFEFEF; border:0; color: #000000"
							size="37" maxlength="40" />

					</logic:notPresent>

                     <logic:present name="corCliente" scope="request">

						<html:text property="nomeCliente" size="30" maxlength="30"
							readonly="true"
							style="background-color:#EFEFEF; border:0; color: red" />

					</logic:present> <a href="javascript:limparCliente();"> <img
						border="0" title="Apagar"
						src="<bean:message key='caminho.imagens'/>limparcampo.gif" /> </a>
					</td>
				</tr>


				<tr>
					<td></td>
					<td><font color="#FF0000">*</font>&nbsp;Campos obrigat&oacute;rios</td>
				</tr>

				<tr>
					<td>
						&nbsp;
					</td>
				</tr>
				<tr>
					<td colspan="2"><input name="Button" type="button" class="bottonRightCol"
						value="Desfazer" align="left"
						onclick="window.location.href='<html:rewrite page="/exibirInserirGerenciaRegionalAction.do?desfazer=S"/>'">
					<input type="button" name="ButtonCancelar" class="bottonRightCol"
						value="Cancelar"
						onClick="javascript:window.location.href='/gsan/telaPrincipal.do'"></td>
					<td align="right"><%--<INPUT type="button" class="bottonRightCol" value="Inserir" tabindex="13" style="width: 70px;" onclick="validarForm(document.forms[0]);">--%>
					<%-- 
		  	A taglib vai substituir o botão, as propriedades requeridas da tag são :
		  	1-name -> O nome do botão.
		  	2-value -> A descrição do botão. 
		  	3-onclick -> a função javascript que vai ser chamada no click do botão.
		  	4-url -> a url doaction da operação para verificar se o usário logado tem acesso a operação.
		  	
		  	As propriedades NÃO requeridas da tag são:
		  	1-tabindex -> mesma função do input
		  	2-align -> mesma função do input
		  --%> <gsan:controleAcessoBotao name="Botao" value="Inserir"
						onclick="validarForm(document.forms[0]);"
						url="inserirGerenciaRegionalAction.do" tabindex="13" /></td>
				</tr>

			</table>

			<p>&nbsp;</p>

			</td>
		</tr>
	</table>

	<%@ include file="/jsp/util/rodape.jsp"%>

</html:form>


</body>
</html:html>

