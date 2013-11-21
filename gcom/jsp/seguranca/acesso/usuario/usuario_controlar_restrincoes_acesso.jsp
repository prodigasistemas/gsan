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
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="ControlarAcessoUsuarioActionForm" />

<script language="JavaScript">

function trocar() {
	document.forms[0].btnSalvar.disabled = false;
}

function salvar() {

	document.forms[0].action = 'controlarAcessosUsuarioWizardAction.do?action=exibirControlarRestrincoesAcessoUsuarioAction&botaoSalvar=1';
	document.forms[0].submit();
}

</script>

</head>

<body leftmargin="5" topmargin="5">

<html:form action="/controlarRestrincoesAcessoUsuarioAction"
	method="post"
	onsubmit="return validateControlarAcessoUsuarioActionForm(this);">

	<jsp:include
		page="/jsp/util/wizard/navegacao_abas_wizard_valida_avancar.jsp?numeroPagina=1" />

	<logic:present name="idFuncionalidade">
		<input type="hidden" name="codigoFuncionalidade"
			value="<bean:write name="idFuncionalidade" />">
	</logic:present>

	<logic:notPresent name="idFuncionalidade">
		<input type="hidden" name="codigoFuncionalidade" value="">
	</logic:notPresent>

	<input type="hidden" name="cadastrarOperacao" value="false">

	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>

	<table width="770" border="0" cellspacing="5" cellpadding="0">
		<tr>
			<td width="115" valign="top" class="leftcoltext">
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
					<td class="parabg">Controlar Acessos do Usuario - Restrições</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>

			<p>&nbsp;</p>

			<table border="0" width="100%">
				<tr>
					<td colspan="2">Para restringir o(s) acesso(s) do usuário,
					desmarque o(s) acesso(s) permitido(s):</td>
				</tr>
				<tr>
					<td><strong>Acessos do Usuário:</strong></td>
					<td><strong><html:text property="loginUsuario" readonly="true"
						style="background-color:#EFEFEF; border:0; color: #000000"
						size="10" maxlength="10" /> <html:text property="nomeUsuario"
						readonly="true"
						style="background-color:#EFEFEF; border:0; color: #000000"
						size="50" maxlength="50" /></strong></td>
				</tr>
				<tr>
					<td colspan="2">
					<table width="100%" border="0">

						<tr>
							<td valign="top" width="70">
							<table width="99%" border="1" bordercolor="#000000">

								<tr bordercolor="#90c7fc">
									<td><%--Inseri o menu(arvore de funcionalidades) na página--%>
									<bean:write name="arvoreFuncionalidades" scope="session"
										filter="false" /></td>
								</tr>
							</table>
							</td>

							<td valign="top" width="30">
							<table width="99%" border="1" cellpadding="1" cellspacing="0"
								bordercolor="#000000">

								<tr bordercolor="#90c7fc">
									<logic:notEmpty name="descricaoFuncionalidade">
										<td height="19" colspan="4" bgcolor="#90c7fc">Opera&ccedil;&otilde;es
										de <bean:write name="descricaoFuncionalidade" scope="request" /></td>
									</logic:notEmpty>
									<logic:empty name="descricaoFuncionalidade">
										<td height="19" colspan="4" bgcolor="#90c7fc">Opera&ccedil;&otilde;es:</td>
									</logic:empty>
								</tr>

								<%--Esquema de paginação das operações cadastradas--%>

								<logic:present name="operacoesMarcadas">
									<logic:iterate name="operacoesMarcadas" id="operacao">
										<tr bordercolor="#90c7fc">
											<td><strong> <input onchange="javascript:trocar()"
												type="checkbox" name="operacoes"
												value="<bean:write name="operacao" property="id"/>"
												checked="true"> </strong></td>

											<td><strong> <bean:write name="operacao" property="descricao" />
											</strong></td>
										</tr>
									</logic:iterate>
								</logic:present>
								<logic:present name="operacoesDesmarcadas">
									<logic:iterate name="operacoesDesmarcadas" id="operacao">
										<tr bordercolor="#90c7fc">
											<td><strong> <input onchange="javascript:trocar()"
												type="checkbox" name="operacoes"
												value="<bean:write name="operacao" property="id"/>"> </strong></td>
											<td><strong> <bean:write name="operacao" property="descricao" />
											</strong></td>
										</tr>
									</logic:iterate>
								</logic:present>
								<logic:present name="operacoesDesabilitadas">
									<logic:iterate name="operacoesDesabilitadas" id="operacao">
										<tr bordercolor="#90c7fc">
											<td><strong> <input onchange="javascript:trocar()"
												type="checkbox" name="operacoes"
												value="<bean:write name="operacao" property="id"/>"
												disabled="true"> </strong></td>
											<td><strong> <bean:write name="operacao" property="descricao" />
											</strong></td>
										</tr>
									</logic:iterate>
								</logic:present>

								<%--Fim do Esquema de paginação das operações cadastradas--%>

								<%--Esquema para exibir a menssagem de operações--%>
								<logic:notPresent name="operacoesMap">
									<logic:empty name="operacoesMap">
										<tr>
											<td>Click no link da funcionalidade para exibir as operações
											<bean:define id="naoTemOperacoes" value="1" /></td>
										</tr>
									</logic:empty>
								</logic:notPresent>

								<logic:notPresent name="naoTemOperacoes">
									<tr>
										<td colspan="4"><input name="btnSalvar" class="bottonRightCol"
											value="Salvar" disabled="disabled" type="button"
											onclick="javascritp:salvar();"></td>
									</tr>
								</logic:notPresent>
							</table>
							</td>
						</tr>
					</table>
					</td>
				</tr>
				<tr>
					<td colspan="2">
					<div align="right"><jsp:include
						page="/jsp/util/wizard/navegacao_botoes_wizard_valida_avancar.jsp?numeroPagina=1" /></div>
					</td>
				</tr>

			</table>
			</td>
		</tr>
	</table>
    <%@ include file="/jsp/util/tooltip.jsp"%>
	<%@ include file="/jsp/util/rodape.jsp"%>
</body>
</html:form>
</html:html>
