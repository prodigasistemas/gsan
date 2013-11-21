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

<%@ page import="gcom.util.ConstantesSistema"%>

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
	

	
<html:javascript staticJavascript="false"
	formName="TabelaAuxiliarAbreviadaTipoActionForm" />

</head>

<body leftmargin="5" topmargin="5">

<html:form action="/inserirTabelaAuxiliarAbreviadaTipoAction"
	name="TabelaAuxiliarTipoActionForm"
	type="gcom.gui.util.tabelaauxiliar.abreviadatipo.TabelaAuxiliarAbreviadaTipoActionForm"
	method="post"
	onsubmit="return validateTabelaAuxiliarAbreviadaTipoActionForm(this);">

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
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_left.gif" /></td>
					<td class="parabg">Inserir <bean:write name="titulo"
						scope="session" /></td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>
					<td colspan="2">Para inserir um(a) <%=((String) session.getAttribute("titulo")).toLowerCase()%>,
					informe os dados abaixo:</td>
				</tr>
				<tr>
					<td width="30%" height="0"><strong><bean:write name="descricao"
						scope="request" />: <font color="#FF0000">*</font></strong></td>
					<td colspan="2"><input type="text" name="descricao"
						size="20" maxlength="20">
					</td>
				</tr>
				<tr>
					<td width="30%" height="0"><strong><bean:write
						name="descricaoAbreviada" scope="request" />: <font
						color="#FF0000">*</font></strong></td>
					<td colspan="2"><input type="text" name="descricaoAbreviada"
						size="6" maxlength="6">
					</td>
				</tr>

				<tr>
					<td height="24"><strong>Sistema de Abastecimento<span
						class="style1">: </span><font color="#FF0000">*</font></strong></td>
					<td><html:select property="tipo" style="width: 230px;">

						<html:option
							value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;
							</html:option>

						<html:options collection="colecaoObject" labelProperty="descricao"
							property="id" />

					</html:select></td>
				</tr>


				<tr>
					<td height="24">&nbsp;</td>
					<td><strong><font color="#FF0000">*</font></strong> Campo
					obrigat&oacute;rio</td>
				</tr>
				<tr>
					
					<td colspan="2">
						<input type="button" 
							name="Button" 
							class="bottonRightCol"
							value="Limpar" 
							onClick="javascript:window.location.href='/gsan/exibirInserirTabelaAuxiliarAbreviadaTipoAction.do?tela=setorAbastecimento'"
							style="width: 80px" />&nbsp;
					&nbsp;
						<input type="button" 
								name="Button"
								class="bottonRightCol" 
								value="Cancelar" 
								onClick="javascript:window.location.href='/gsan/telaPrincipal.do'"
								style="width: 80px" />
					</td>
				
					<td>&nbsp;</td>
					<td height="0" align="right"><html:submit styleClass="bottonRightCol"
						value="Inserir" property="Button" /></td>
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
</html:form>
</body>
</html:html>
