<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan"%>

<%@ page import="gcom.util.ConstantesSistema"%>


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
	formName="AtualizarAgenciaBancariaActionForm" />

<SCRIPT LANGUAGE="JavaScript">
<!--


function validarForm(formulario){

	if (validateAtualizarAgenciaBancariaActionForm(formulario)){
	
		var objCodigo = returnObject(formulario, "Codigo");
		var objNome = returnObject(formulario, "nome");
		var objBancoID = returnObject(formulario, "bancoID");
		var enderecoInformado = document.getElementById("botaoEndereco");
		var objTelefone = returnObject(formulario, "telefone");
		var objRamal = returnObject(formulario, "ramal");
		var objFax = returnObject(formulario, "fax");

	
		if (!testarCampoValorZero(objCodigo, "Código")){
			objCodigo.focus();
		}
		/* else if(!enderecoInformado.disabled){
			alert("Informe Endereço Localidade.");
			enderecoInformado.focus();
		} */
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
				formulario.action = "/gsan/atualizarAgenciaBancariaAction.do";
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
		}

		else {
			formulario.action = "/gsan/atualizarAgenciaBancariaAction.do";
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


function redirecionarSubmitAtualizar(idLocalidade) {
	urlRedirect = "/gsan/exibirAtualizarAgenciaBancariaAction.do?idRegistroInseridoAtualizar=" + idLocalidade;
	redirecionarSubmit(urlRedirect);
}

//-->
</SCRIPT>

</head>

<body leftmargin="5" topmargin="5"
	onload="document.forms[0].removerEndereco.value=''; setarFoco('${requestScope.nomeCampo}');">

<html:form action="/exibirAtualizarAgenciaBancariaAction" method="post">

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
					<td class="parabg">Atualizar Agência Bancária</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>

			<table width="100%" border="0">
				<tr>
					<td colspan="2">Para Atualizar uma Agência Bancária, informe os
					dados abaixo:</td>
					<!-- Banco -->
				<tr>
					<td><strong>Banco:<font color="#FF0000">*</font></strong></td>
					<td colspan="2" align="left"><html:select property="bancoID">
						<html:option value="-1">&nbsp;</html:option>
						<html:options collection="colecaoBanco" labelProperty="descricao"
							property="id" />
					</html:select></td>
				</tr>


				<tr>
					<td width="231"><strong>Código da Agência Bancária:<font
						color="#FF0000">*</font></strong></td>

					<td width="380"><html:text property="codigo" size="4" maxlength="4" /></td>
				</tr>

				<tr>
					<td><strong>Nome da Agência Bancária:<font color="#FF0000">*</font></strong></td>
					<td colspan="2"><html:text property="nome" size="50" maxlength="40"
						tabindex="2" /></td>
				</tr>

				<tr>
					<td><strong>Endere&ccedil;o:</strong></td>
					<td colspan="2" align="right"><logic:present name="colecaoEnderecos">
						<logic:empty name="colecaoEnderecos">
							<input type="button" class="bottonRightCol" value="Adicionar"
								tabindex="3" id="botaoEndereco"
								onclick="javascript:abrirPopup('exibirInserirEnderecoAction.do?tipoPesquisaEndereco=agencia&operacao=2', 560, 450);">
						</logic:empty>
						<logic:notEmpty name="colecaoEnderecos">
							<input type="button" class="bottonRightCol" value="Adicionar"
								tabindex="3" id="botaoEndereco"
								onclick="javascript:abrirPopup('exibirInserirEnderecoAction.do?tipoPesquisaEndereco=agencia&operacao=2', 560, 450);"
								disabled>
						</logic:notEmpty>
					</logic:present> <logic:notPresent name="colecaoEnderecos">
						<input type="button" class="bottonRightCol" value="Adicionar"
							tabindex="3" id="botaoEndereco"
							onclick="javascript:abrirPopup('exibirInserirEnderecoAction.do?tipoPesquisaEndereco=agencia&operacao=2', 560, 450);">
					</logic:notPresent></td>
				</tr>
				<tr>
					<td colspan="3" height="70" valign="top">
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
												href="javascript:abrirPopup('exibirInserirEnderecoAction.do?exibirEndereco=OK&tipoPesquisaEndereco=agencia&operacao=2', 570, 700)"><bean:write
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
					<td><strong>Telefone:<font color="#FF0000">*</font></strong></td>
					<td><html:text property="telefone" size="10" maxlength="8"
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
					<td></td>
					<td><font color="#FF0000">*</font>&nbsp;Campos obrigat&oacute;rios</td>
				</tr>

				<tr>
					<td colspan="2"><logic:present name="filtrar">
						<logic:present name="inserir">
							<input type="button" name="ButtonReset" class="bottonRightCol"
								value="Voltar"
								onClick="javascript:window.location.href='/gsan/exibirFiltrarAgenciaBancariaAction.do?menu=sim'">
						</logic:present>
						<logic:notPresent name="inserir">
							<input type="button" name="ButtonReset" class="bottonRightCol"
								value="Voltar"
								onClick="javascript:window.location.href='/gsan/exibirFiltrarAgenciaBancariaAction.do'">
						</logic:notPresent>
					</logic:present><logic:notPresent name="filtrar">
						<input type="button" name="ButtonReset" class="bottonRightCol"
							value="Voltar"
							onClick="javascript:window.location.href='/gsan/exibirManterAgenciaBancariaAction.do'">
					</logic:notPresent> <input name="Button" type="button"
						class="bottonRightCol" value="Desfazer" align="left"
						onclick="window.location.href='<html:rewrite page="/exibirAtualizarAgenciaBancariaAction.do?desfazer=S"/>'">
					<input type="button" name="Submit23" class="bottonRightCol"
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
		  --%> <gsan:controleAcessoBotao name="Botao" value="Atualizar"
						onclick="validarForm(document.forms[0]);"
						url="atualizarAgenciaBancariaAction.do" tabindex="13" /></td>
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

