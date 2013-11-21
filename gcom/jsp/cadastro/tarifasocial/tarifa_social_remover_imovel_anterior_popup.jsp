
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>

<%@page import="gcom.util.ConstantesSistema" isELIgnored="false"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>

<head>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<html:javascript formName="RemoverTarifaSocialImovelAnteriorActionForm"
	staticJavascript="false" dynamicJavascript="false" />
<SCRIPT LANGUAGE="JavaScript">
<!--

function excluir(manter){
	var form = document.forms[0];
	if (form.motivoExclusao.value == "-1") {
		alert('Informe Motivo Exclusão');
	} else {
		if (confirm('Confirma remoção?')) {
			if (manter != null && manter != '') {
				form.action = 'removerTarifaSocialImovelAnteriorAction.do?manter=true';
			} 
			form.submit();
		}
	}
}

function fechar() {
	chamarReloadSemParametro();
	window.close();
}

//-->
</script>
</head>

<logic:present name="fecharPopup">
	<body leftmargin="0" topmargin="0" onload="javascript:fechar();">
</logic:present>

<logic:notPresent name="fecharPopup">
	<body leftmargin="0" topmargin="0">
</logic:notPresent>

<html:form action="/removerTarifaSocialImovelAnteriorAction"
	name="RemoverTarifaSocialImovelAnteriorActionForm"
	type="gcom.gui.cadastro.tarifasocial.RemoverTarifaSocialImovelAnteriorActionForm"
	method="post">
	<table width="500" border="0" cellpadding="0" cellspacing="5">
		<tr>
			<td width="500" valign="top" class="centercoltext">
			<table height="100%">
				<tr>
					<td></td>
				</tr>
			</table>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img src="imagens/parahead_left.gif"
						editor="Webstyle4"
						moduleid="Album Photos (Project)\toptab_page2_parahead_left.xws"
						border="0" /></td>
					<td class="parabg">Excluir Dados da Tarifa Social</td>
					<td width="11"><img src="imagens/parahead_right.gif"
						editor="Webstyle4"
						moduleid="Album Photos (Project)\toptab_page2_parahead_right.xws"
						border="0" /></td>
				</tr>
			</table>
			<table width="100%" border="0">
				<tr>
					<td>
					<table width="100%" border="0" dwcopytype="CopyTableRow">
						<tr>
							<td colspan="2"><strong>Cliente é usuário da Tarifa Social</strong></td>
						</tr>
						<tr>
							<td height="25"><strong>Matrícula do Imóvel:</strong></td>
							<td><html:text property="idImovel" size="10" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" /></td>
						</tr>
						<tr>
							<td height="25"><strong>Motivo Revisão:</strong></td>
							<td><html:text property="motivoRevisao" size="40" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" /></td>
						</tr>
						<tr>
							<td height="25"><strong>Motivo de Exclusão no Imóvel Anterior:</strong></td>
							<td><html:select property="motivoExclusao">
								<html:option
									value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
								<html:options collection="colecaoTarifaSocialExclusaoMotivo"
									labelProperty="descricao" property="id" />
							</html:select></td>
						</tr>
						<tr>
							<td height="25" colspan="3">
							<div align="right"><font color="#000000"><logic:present
								name="manter">
								<input name="Button2" type="button" class="bottonRightCol"
									value="Fechar" onClick="javascript:window.close();">
							</logic:present> <logic:notPresent name="manter">
								<input name="Button2" type="button" class="bottonRightCol"
									value="Voltar" onClick="javascript:window.history.back();">
							</logic:notPresent>&nbsp; <input name="Button" type="button"
								class="bottonRightCol" value="Confirmar"
								onClick="javascript:excluir('${requestScope.manter}');"></font></div>
							</td>
						</tr>
					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
</html:form>
</body>
</html>
