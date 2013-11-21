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


<script language="JavaScript">

function validarForm(form){

		if(	testarCampoValorZero(document.TabelaAuxiliarIndicadorActionForm.descricao, 'Descrição')) {

			if(validateTabelaAuxiliarIndicadorActionForm(form)){
    			submeterFormPadrao(form);
			}
		}
	}
	

</script>



<html:javascript staticJavascript="false"
	formName="TabelaAuxiliarIndicadorActionForm" />
</head>

<body leftmargin="5" topmargin="5">

<html:form action="/atualizarTabelaAuxiliarIndicadorAction?tela=${requestScope.tela}"
	name="TabelaAuxiliarIndicadorActionForm"
	type="gcom.gui.util.tabelaauxiliar.indicador.TabelaAuxiliarIndicadorActionForm"
	method="post"
	onsubmit="return validateTabelaAuxiliarIndicadorActionForm(this);">

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
					<td><strong><bean:write name="tabelaAuxiliarIndicador"
						property="id" /></strong></td>
				</tr>
				<tr>
					<td width="30%" height="24"><strong><bean:write name="descricao"
						scope="session" />:<font color="#FF0000">*</font></strong></td>
					<td width="70%"><input type"text" name="descricao"
						value="<bean:write name="tabelaAuxiliarIndicador" property="descricao" scope="session"/>"
						maxlength="<bean:write name="tamMaxCampoDescricao" scope="session"/>"
						size="<bean:write name="tamMaxCampoDescricao" scope="session"/>" />
					</td>
				</tr>


				<logic:equal name="indicadorBaixaRenda" scope="session" value="sim">
					<tr>
						<td><strong>Indicador de Baixa Renda:<font color="#FF0000">*</font></strong></td>
						<td><strong> <input type="radio" name="indicadorBaixaRenda"
							value="1" checked /> <strong>Sim <input type="radio"
							name="indicadorBaixaRenda" value="2" /> Não</strong> </strong></td>
					</tr>
				</logic:equal>
				<logic:equal name="indicadorBaixaRenda" scope="session" value="nao">
					<tr>
						<td><strong>Indicador de Baixa Renda:<font color="#FF0000">*</font></strong></td>
						<td><strong> <input type="radio" name="indicadorBaixaRenda"
							value="1" /> <strong>Sim <input type="radio"
							name="indicadorBaixaRenda" value="2" checked /> N&atilde;o</strong>
						</strong></td>
					</tr>
				</logic:equal>

				<logic:equal name="indicadorUso" scope="session" value="sim">
					<tr>
						<td><strong>Indicador de Uso:<font color="#FF0000">*</font></strong></td>
						<td><strong> <input type="radio" name="indicadorUso" value="1"
							checked /> <strong>Ativo <input type="radio" name="indicadorUso"
							value="2" /> Inativo</strong> </strong></td>
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
					<td><logic:present name="manter" scope="session">

						<input type="button" name="ButtonReset" class="bottonRightCol"
							value="Voltar"
							onClick="javascript:window.location.href='/gsan/exibirManterTabelaAuxiliarIndicadorAction.do?tela=${requestScope.tela}'">

					</logic:present> <logic:notPresent name="manter" scope="session">
						<input type="button" name="ButtonReset" class="bottonRightCol"
							value="Voltar"
							onClick="javascript:window.location.href='/gsan/exibirFiltrarTabelaAuxiliarIndicadorAction.do?tela=${requestScope.tela}'">
					</logic:notPresent> <input type="button" name="ButtonReset"
						class="bottonRightCol" value="Desfazer"
						onClick="window.location.href='<html:rewrite page="/exibirAtualizarTabelaAuxiliarIndicadorAction.do?tela=${requestScope.tela}&desfazer=S"/>'">
					</td>
					<td height="0" align="right"><input type="button" name="Button"
						class="bottonRightCol" value="Atualizar" onclick="javascript:validarForm(document.forms[0])"/></td>
					<div align="right"></div>
				</tr>

				<tr>
					<td height="0">&nbsp;</td>
					<td colspan="2">&nbsp;</td>
				</tr>
				<tr>
					<td height="0">&nbsp;</td>
					<td colspan="2">&nbsp;</td>
				</tr>
				<tr>
					<td height="0">&nbsp;</td>
					<td colspan="2">&nbsp;</td>
				</tr>
				<tr>
					<td height="0">&nbsp;</td>
					<td colspan="2">&nbsp;</td>
				</tr>
				<tr>
					<td height="0">&nbsp;</td>
					<td colspan="2">&nbsp;</td>
				</tr>
				<tr>
					<td height="0">&nbsp;</td>
					<td colspan="2">&nbsp;</td>
				</tr>
				<tr>
					<td height="0">&nbsp;</td>
					<td colspan="2">&nbsp;</td>
				</tr>
				<tr>
					<td height="0">&nbsp;</td>
					<td colspan="2">&nbsp;</td>
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

