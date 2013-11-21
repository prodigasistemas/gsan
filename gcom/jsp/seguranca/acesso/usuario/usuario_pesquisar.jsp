<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
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
	src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false"
	formName="PesquisarUsuarioActionForm" />

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript">

<!-- Begin

   function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
   var form = document.forms[0];

	   if (tipoConsulta == 'funcionario') {
	      form.matriculaFuncionario.value = codigoRegistro;
	      form.nomeFuncionario.value = descricaoRegistro;
	    }
   }


	function validarForm(form){

		if(validatePesquisarUsuarioActionForm(form)){
    		form.submit();
		}
	}

	function limparPesquisaFuncionario(form) {
    	form.matriculaFuncionario.value = "";
    	form.nomeFuncionario.value = "";
    	form.login.value = "";
    	form.matriculaFuncionario.focus();
	}

	function limparPesquisaFuncionarioNome() {
   		var form = document.forms[0];
		
		form.nomeFuncionario.value = "";
	}
	
	function limparUnidadeOrganizacional() {
	
		var form = document.forms[0];
		
		if (form.idUnidadeOrganizacional.readOnly == false) {
    		form.idUnidadeOrganizacional.value = "";
	    	form.nomeUnidadeOrganizacional.value = "";
    		form.idUnidadeOrganizacional.focus();
    	}
	}
	
	function limparUnidadeOrganizacionalTecla() {
	
		var form = document.forms[0];
		
    	form.nomeUnidadeOrganizacional.value = "";
	}

-->
</script>

</head>

<body leftmargin="5" topmargin="5" onload="resizePageSemLink(610, 460);">

<html:form action="/retornarUsuarioPesquisar"
	name="PesquisarUsuarioActionForm"
	type="gcom.gui.seguranca.acesso.usuario.PesquisarUsuarioActionForm"
	method="post"
	onsubmit="return validatePesquisarUsuarioActionForm(this);">
	<table width="580" border="0" cellspacing="5" cellpadding="0">
		<tr>
			<td width="520" valign="top" class="centercoltext">
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
					<td class="parabg">Pesquisar Usuário</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>
					<td colspan="4">Preencha os campos para pesquisar um usu&aacute;rio
					do sistema:</td>
					<td align="right"><a
						href="javascript: abrirPopupHelp('/gsan/help/help.jsp?mapIDHelpSet=usuarioPesquisar', 500, 700);"><span
						style="font-weight: bold"><font color="#3165CE">Ajuda</font></span></a></td>
				</tr>
			</table>

			<table width="100%" border="0">
				<tr>
					<td>
					<p>&nbsp;</p>
					</td>
				</tr>
				<tr>
					<td><strong>Tipo de Usu&aacute;rio:</strong></td>
					<td colspan="3"><html:select property="tipoUsuario">
						<html:option value="-1">&nbsp;</html:option>
						<html:options collection="collectionUsuarioTipo"
							labelProperty="descricao" property="id" />
					</html:select> <font size="1">&nbsp; </font></td>
				</tr>
				<tr>
					<td height="0"><strong>Nome:</strong></td>
					<td colspan="3"><html:text property="nome" size="50" maxlength="50"
						tabindex="2" /></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td colspan="3"><html:radio property="tipoPesquisa"
						value="<%=ConstantesSistema.TIPO_PESQUISA_INICIAL.toString()%>" />
					Iniciando pelo texto&nbsp;<html:radio property="tipoPesquisa"
						tabindex="5"
						value="<%=ConstantesSistema.TIPO_PESQUISA_COMPLETA.toString()%>" />
					Contendo o texto</td>
				</tr>
				<tr>
					<td><strong>Funcion&aacute;rio:</strong></td>
					<td colspan="3"><html:text property="matriculaFuncionario" size="9"
						maxlength="9" tabindex="3"
						onkeyup="limparPesquisaFuncionarioNome();"
						onkeypress="javascript:return validaEnter(event, 'exibirUsuarioPesquisar.do?objetoConsulta=1', 'matriculaFuncionario');" />

					<a
						onclick="javascript:redirecionarSubmit('exibirFuncionarioPesquisar.do?caminhoRetornoTelaPesquisaFuncionario=exibirUsuarioPesquisar');">
					<img width="23" height="21" border="0"
						src="<bean:message key="caminho.imagens"/>pesquisa.gif"
						title="Pesquisar Funcionário" /> </a> <logic:present
						name="idFuncionarioNaoEncontrado">
						<logic:equal name="idFuncionarioNaoEncontrado" value="exception">
							<html:text property="nomeFuncionario" size="40" maxlength="40"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:equal>
						<logic:notEqual name="idFuncionarioNaoEncontrado"
							value="exception">
							<html:text property="nomeFuncionario" size="40" maxlength="40"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEqual>
					</logic:present> <logic:notPresent
						name="idFuncionarioNaoEncontrado">
						<logic:empty name="PesquisarUsuarioActionForm"
							property="matriculaFuncionario">
							<html:text property="nomeFuncionario" value="" size="40"
								maxlength="40" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:empty>
						<logic:notEmpty name="PesquisarUsuarioActionForm"
							property="matriculaFuncionario">
							<html:text property="nomeFuncionario" size="40" maxlength="40"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEmpty>
					</logic:notPresent> <a
						href="javascript:limparPesquisaFuncionario(document.PesquisarUsuarioActionForm);">
					<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar" /></a>
				</tr>

				<tr>
					<td height="0"><strong>Login:</strong></td>
					<td colspan="3"><html:text property="login" size="12"
						maxlength="11" style="text-transform: none;" /></td>
				</tr>

				<tr>
					<td><strong>Unidade Organizacional:</strong></td>
					<td><logic:present name="bloquearUnidadeOrganizacional"
						scope="session">
						<html:text property="idUnidadeOrganizacional" size="4"
							maxlength="4" readonly="true"/>
						<img src="<bean:message key='caminho.imagens'/>pesquisa.gif"
							width="23" height="21" style="cursor:hand;" name="imagem"
							alt="Pesquisar">
					</logic:present> <logic:notPresent
						name="bloquearUnidadeOrganizacional" scope="session">
						<html:text property="idUnidadeOrganizacional" size="4"
							maxlength="4" onkeyup="limparUnidadeOrganizacionalTecla();"
							onkeypress="javascript:validaEnter(event, 'exibirUsuarioPesquisar.do?objetoConsulta=2', 'idUnidadeOrganizacional');" />

						<img src="<bean:message key='caminho.imagens'/>pesquisa.gif"
							width="23" height="21" style="cursor:hand;" name="imagem"
							onclick="redirecionarSubmit('exibirPesquisarUnidadeOrganizacionalAction.do?caminhoRetornoTelaPesquisaUnidadeOrganizacional=exibirUsuarioPesquisar');"
							alt="Pesquisar">
					</logic:notPresent> <logic:present
						name="unidadeOrganizacionalInexistente">
						<html:text property="nomeUnidadeOrganizacional" readonly="true"
							style="background-color:#EFEFEF; border:0; color: #ff0000"
							size="40" maxlength="40" />
					</logic:present> <logic:notPresent
						name="unidadeOrganizacionalInexistente">
						<html:text property="nomeUnidadeOrganizacional" readonly="true"
							style="background-color:#EFEFEF; border:0; color: #000000"
							size="40" maxlength="40" />
					</logic:notPresent> <a
						href="javascript:limparUnidadeOrganizacional();"> <img border="0"
						title="Apagar"
						src="<bean:message key='caminho.imagens'/>limparcampo.gif" /></a>
					</strong></span></td>
				</tr>

				<tr>
					<td height="0">&nbsp;</td>
					<td colspan="3">&nbsp;</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td colspan="3">&nbsp;</td>
				</tr>


				<tr>
					
					<td>
					<!--<logic:present name="caminhoRetornoTelaPesquisaUsuario">
						<input type="button" name="Button" class="bottonRightCol"
							value="Voltar" onclick="history.back();">
					</logic:present> -->
					<input name="Button" type="button"
						class="bottonRightCol" value="Limpar" align="left"
						onclick="window.location.href='<html:rewrite page="/exibirUsuarioPesquisar.do?desfazer=S"/>'">
					</td>

					<td align="right"><input type="button" name="Button"
						class="bottonRightCol" value="Pesquisar" tabindex="4"
						onClick="validarForm(document.forms[0])" /></td>
				</tr>

			</table>
			<p>&nbsp;</p>
			<p>&nbsp;</p>
	</table>
</html:form>
</body>
</html:html>
