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
	src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript">


function habilitaSituacaoUsuario(form){

	var form = document.forms[0];
		if (form.login.value.length > 0){
			form.usuarioSituacao.disabled = false;
			form.botao.disabled = false;
		}else{
			form.usuarioSituacao.disabled = true;
			form.botao.disabled = true;
			
		}

   }

function bloqueio(form) {
	form.usuarioSituacao.disabled = true;
	form.usuarioSituacao.selectedIndex = 0;
	form.botao.disabled = true;
}

function redirecionaSubmit(caminhoAction) {

   var form = document.forms[0];
   form.action = caminhoAction;
   form.submit();

   return true;

 }

function validarForm(form){

	urlRedirect = "/gsan/bloquearDesbloquearAcessoUsuarioAction.do"

	if(testarCampoValorZero(document.BloquearDesbloquearAcessoUsuarioActionForm.login, 'Login') && 
	   testarCampoValorZero(document.BloquearDesbloquearAcessoUsuarioActionForm.usuarioSituacao, 'Usuário Situação')) {

		if(validateBloquearDesbloquearAcessoUsuarioActionForm(form)){
    		redirecionaSubmit(urlRedirect);
		}
	}
}

</script>

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="BloquearDesbloquearAcessoUsuarioActionForm" />

</head>

<body leftmargin="5" topmargin="5"
	onload="javascript:setarFoco('${requestScope.nomeCampo}'); javascript:habilitaSituacaoUsuario(document.forms[0]);">

<html:form action="/exibirBloquearDesbloquearAcessoUsuarioAction" method="post">

	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>

	<table width="770" border="0" cellspacing="5" cellpadding="0">

		<tr>
			<td width="150" valign="top" class="leftcoltext">
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

			<!--Início Tabela Reference a Páginação da Tela de Processo-->
			<table>
				<tr>
					<td></td>

				</tr>
			</table>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img border="0" src="imagens/parahead_left.gif" /></td>
					<td class="parabg">Bloquear ou Desbloquear Acesso do Usuário</td>
					<td width="11" valign="top"><img border="0"
						src="imagens/parahead_right.gif" /></td>
				</tr>

			</table>
			<!--Fim Tabela Reference a Páginação da Tela de Processo-->
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>
					<td colspan="2">Para bloquear ou desbloquear o acesso do usuário ao
					sistema, informe os dados abaixo:</td>
				</tr>
				<tr>
					<td width="162"><strong>Login:<font color="#FF0000">*</font></strong></td>

					<td><strong><html:text property="login" size="10" maxlength="10"
						onkeyup="javascript:bloqueio(document.forms[0]);validaEnter(event, 'exibirBloquearDesbloquearAcessoUsuarioAction.do?objetoConsulta=1', 'login');"/>
					</strong></td>
				</tr>

				<tr>
					<td><strong>Situação do Usuário:<font color="#FF0000">*</font></strong></td>
					<td><html:select property="usuarioSituacao">
						<html:option value="-1">&nbsp;</html:option>
						<html:options collection="colecaoUsuarioSituacao"
							labelProperty="descricaoUsuarioSituacao" property="id" />
					</html:select> <font size="1">&nbsp; </font></td>
				</tr>
				
				<tr>
					<td height="19"><strong> <font color="#FF0000"></font></strong></td>
					<td align="right">
					<div align="left"><strong><font color="#FF0000">*</font></strong>
					Campos obrigat&oacute;rios</div>
					</td>
				</tr>
				<tr>
					<td colspan="2" align="right"><input name="botao" type="button"
						class="bottonRightCol" value="Bloquear/Desbloquear" align="right"
						onClick="javascript:validarForm(document.forms[0]);"></td>
				</tr>
			</table>
			<p>&nbsp;</p>
		</tr>
	</table>
	<tr>
		<td colspan="3"><%@ include file="/jsp/util/rodape.jsp"%>
	</tr>
</html:form>
</body>
</html:html>

