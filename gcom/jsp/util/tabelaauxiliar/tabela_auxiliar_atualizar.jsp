<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/menus-taglib.tld" prefix="menu"%>
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
	formName="TabelaAuxiliarActionForm" />
</head>

<body leftmargin="5" topmargin="5">

<html:form action="/atualizarTabelaAuxiliarAction?tela=${requestScope.tela}"
	name="TabelaAuxiliarActionForm"
	type="gcom.gui.util.tabelaauxiliar.TabelaAuxiliarActionForm"
	method="post" onsubmit="return validateTabelaAuxiliarActionForm(this);">

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
					<td colspan="2">Para atualizar o(a) <%=((String) session.getAttribute("titulo")).toLowerCase()%>,
					preencha a descrição:</td>
				</tr>
				<tr>
					<td height="24"><strong>C&oacute;digo:</strong></td>
					<td><strong><bean:write name="tabelaAuxiliar" property="id" /></strong></td>
				</tr>
				<tr>
					<td width="15%" height="24"><strong><bean:write name="descricao"
						scope="session" />:<font color="#FF0000">*</font></strong></td>
					<td width="85%"><input type"text" name="descricao"
						value="<bean:write name="tabelaAuxiliar" property="descricao" scope="session"/>"
						maxlength="<bean:write name="tamMaxCampoDescricao" scope="session"/>"
						size="<bean:write name="tamMaxCampoDescricao" scope="session"/>" /></td>
				</tr>

				<logic:equal name="indicadorUso" scope="session" value="sim"> 
					<tr>
						<td><strong>Indicador de Uso:<font color="#FF0000">*</font></strong></td>
						<td>
							<input type="radio" name="indicadorUso" value="1" checked/><strong>Sim
							<input type="radio" name="indicadorUso" value="2" /> N&atilde;o</strong>
						</td>
					</tr>
				</logic:equal>
				
				<logic:equal name="indicadorUso" scope="session" value="nao"> 
					<tr>
						<td><strong>Indicador de Uso:<font color="#FF0000">*</font></strong></td>
						<td>
							<input type="radio" name="indicadorUso" value="1" /> <strong>Sim
							<input type="radio" name="indicadorUso" value="2" checked/> N&atilde;o</strong>
						</td>
					</tr>
				</logic:equal>

				<tr>
					<td height="24">&nbsp;</td>
					<td><strong><font color="#FF0000">*</font></strong> Campo
					obrigat&oacute;rio</td>
				</tr>
				<tr>
					<td width="40%" align="left" colspan="2"><input type="button"
						name="ButtonCancelar" class="bottonRightCol" value="Voltar"
						onClick="window.history.go(-1)"> <input type="button"
						name="ButtonReset" class="bottonRightCol" value="Desfazer"
						onClick="(document.forms[0]).reset()"> <input type="button"
						name="ButtonCancelar" class="bottonRightCol" value="Cancelar"
						onClick="javascript:window.location.href='/gsan/telaPrincipal.do'">
					</td>
					<td height="0" align="right"><html:submit
						styleClass="bottonRightCol" value="Atualizar" property="Button" /></td>
					<td>
					<div align="right"></div>
					</td>
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

