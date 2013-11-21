<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/menus-taglib.tld" prefix="menu"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
<head>

<title><bean:write name="nomeSistema" scope="session" /></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<html:javascript staticJavascript="false"
	formName="TabelaAuxiliarFaixaInteiroActionForm" />
</head>

<body leftmargin="5" topmargin="5">

<html:form action="/atualizarTabelaAuxiliarFaixaInteiroAction?tela=${requestScope.tela}"
	name="TabelaAuxiliarFaixaInteiroActionForm"
	type="gcom.gui.util.tabelaauxiliar.faixa.TabelaAuxiliarFaixaInteiroActionForm"
	method="post"
	onsubmit="return validateTabelaAuxiliarFaixaInteiroActionForm(this);">

	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>

	<table width="770" border="0" cellspacing="5" cellpadding="0">
		<tr>
			<td width="150" valign="top" class="leftcoltext">
			<div align="center"><%@ include
				file="/jsp/util/informacoes_usuario.jsp"%> <%@ include
				file="/jsp/util/mensagens.jsp"%></div>
			</td>
			<td width="625" valign="top" class="centercoltext">
			<table>
				<tr>
					<td></td>
				</tr>
			</table>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_left.gif" /></td>
					<td class="parabg">Atualizar <bean:write name="titulo"
						scope="session" /></td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>
					<td colspan="2">Para atualizar o(a) <%=((String) session.getAttribute("titulo"))
									.toLowerCase()%>, preencha os campos:</td>
				</tr>
				<tr>
					<td height="24"><strong>C&oacute;digo:</strong></td>
					<td><strong><bean:write name="tabelaAuxiliarFaixaInteiro"
						property="id" /></strong></td>
				</tr>
				<tr>
					<td width="30%" height="24"><strong>Menor Faixa:<font
						color="#FF0000">*</font></strong></td>
					<td width="85%"><input type"text" name="menorFaixa"
						value="<bean:write name="tabelaAuxiliarFaixaInteiro" property="menorFaixa" scope="session" />"
						maxlength="<bean:write name="tamMaxCampoMenorFaixa" scope="session"/>"
						size="<bean:write name="tamMaxCampoMenorFaixa" scope="session"/>"
						 /></td>
				</tr>
				<tr>
					<td width="30%" height="24"><strong>Maior Faixa:<font
						color="#FF0000">*</font></strong></td>
					<td width="85%"><input type"text" name="maiorFaixa"
						value="<bean:write name="tabelaAuxiliarFaixaInteiro" property="maiorFaixa" scope="session" />"
						
						maxlength="<bean:write name="tamMaxCampoMaiorFaixa" scope="session"/>"
						size="<bean:write name="tamMaxCampoMaiorFaixa" scope="session"/>"
						 /></td>
				</tr>
				<logic:equal name="indicadorUso" scope="session" value="sim">
					<tr>
						<td><strong>Indicador de Uso:<font color="#FF0000">*</font></strong></td>
						<td><strong> <input type="radio" name="indicadorUso" value="1"
							checked /> <strong>Sim <input type="radio" name="indicadorUso"
							value="2" /> N&atilde;o</strong> </strong></td>
					</tr>
				</logic:equal>
				<logic:equal name="indicadorUso" scope="session" value="nao">
					<tr>
						<td><strong>Indicador de Uso:<font color="#FF0000">*</font></strong></td>
						<td><strong> <input type="radio" name="indicadorUso" value="1" />
						<strong>Sim <input type="radio" name="indicadorUso" value="2"
							checked /> N&atilde;o</strong> </strong></td>
					</tr>
				</logic:equal>


				<td height="24">&nbsp;</td>
				<td><strong><font color="#FF0000">*</font></strong> Campo
				obrigat&oacute;rio</td>

				<tr>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
				</tr>
				
				<tr>
					<td><logic:present name="manter" scope="session">

						<input type="button" name="ButtonReset" class="bottonRightCol"
							value="Voltar"
							onClick="javascript:window.location.href='/gsan/exibirManterTabelaAuxiliarFaixaInteiroAction.do?tela=${requestScope.tela}'">

					</logic:present> <logic:notPresent name="manter" scope="session">
						<input type="button" name="ButtonReset" class="bottonRightCol"
							value="Voltar"
							onClick="javascript:window.location.href='/gsan/exibirFiltrarTabelaAuxiliarFaixaInteiroAction.do?tela=${requestScope.tela}'">
					</logic:notPresent> <input type="button" name="ButtonReset"
						class="bottonRightCol" value="Desfazer"
						onClick="window.location.href='<html:rewrite page="/exibirAtualizarTabelaAuxiliarFaixaInteiroAction.do??tela=${requestScope.tela}&desfazer=S"/>'">

					</td>
					<td height="0" align="right"><html:submit
						styleClass="bottonRightCol" value="Atualizar" property="Button" /></td>
					<div align="right"></div>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
				</tr>

			</table>
			<p>&nbsp;</p>
			</td>
		</tr>
	</table>

	<%@ include file="/jsp/util/rodape.jsp"%>
</body>
</html:form>
</html:html>

