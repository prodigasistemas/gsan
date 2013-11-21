<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/menus-taglib.tld" prefix="menu"%>
<%@ page
	import="gcom.gui.util.tabelaauxiliar.abreviada.DadosTelaTabelaAuxiliarAbreviada"
	isELIgnored="false"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>

<head>
<title>GSAN - Sistema Integrado de Gest&atilde;o de Servi&ccedil;os de Saneamento do Saneamento<!--<bean:write name="nomeSistema" scope="session"/>--></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false"
	formName="TabelaAuxiliarAbreviadaActionForm" />
</head>

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>

<script language="JavaScript">

function limpar(){
	var form = document.forms[0];
	
	form.descricao.value = '';
	form.descricaoAbreviada.value = '';

	
}

function validarForm(form){

		if(	testarCampoValorZero(document.TabelaAuxiliarAbreviadaActionForm.descricao, 'Descrição')) {

			if(validateTabelaAuxiliarAbreviadaActionForm(form)){
    			submeterFormPadrao(form);
			}
		}
	}

</script>




<body leftmargin="5" topmargin="5">
<html:form action="/inserirTabelaAuxiliarAbreviadaAction?tela=${requestScope.tela}" method="post"
	name="TabelaAuxiliarAbreviadaActionForm"
	type="gcom.gui.util.tabelaauxiliar.abreviada.TabelaAuxiliarAbreviadaActionForm"
	onsubmit="return validateTabelaAuxiliarAbreviadaActionForm(this);">

	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>

	<table width="770" border="0" cellspacing="5" cellpadding="0">
		<tr>
			<td width="150" valign="top" class="leftcoltext"><%@ include
				file="/jsp/util/informacoes_usuario.jsp"%></td>
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
					<td class="parabg">Inserir <%=(((DadosTelaTabelaAuxiliarAbreviada) session
									.getAttribute("dados")).getTitulo())%></td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>
					<td colspan="3">Para inserir um(a) <%=(((DadosTelaTabelaAuxiliarAbreviada) session
									.getAttribute("dados")).getTitulo())
									.toLowerCase()%>,
					informe os dados abaixo:</td>
				</tr>
				<tr>
					<td width="30%" height="0"><strong><bean:write name="descricao"
						scope="request" />:<font color="#FF0000"> *</font></strong></td>
					<td colspan="2"><input type="text" name="descricao"
						size="<bean:write name="tamMaxCampoDescricao" scope="request"/>"
						maxlength="<bean:write name="tamMaxCampoDescricao" scope="request"/>">
					</td>
				</tr>
				<tr>
					<td width="30%" height="0"><strong><bean:write
						name="descricaoAbreviada" scope="request" />:<font color="#FF0000"> *</font></strong></td>
					<td colspan="2"><input type="text" name="descricaoAbreviada"
						size="<bean:write name="tamMaxCampoDescricaoAbreviada" scope="request"/>"
						maxlength="<bean:write name="tamMaxCampoDescricaoAbreviada" scope="request"/>">
					</td>
				</tr>


				<tr>
					<td height="0">&nbsp;</td>
					<td colspan="2"><strong><font color="#FF0000">*</font></strong>
					Campos obrigat&oacute;rios</td>
				</tr>
				<tr>
					<td height="0">&nbsp;</td>
					<td colspan="2">&nbsp;</td>
				</tr>




				<tr>
					<td height="0" align="left"><input name="Button" type="button"
						class="bottonRightCol" value="Limpar" align="left"
						onclick="javascript:limpar();"> <!--<input name="Button" type="button" align="left" styleClass="bottonRightCol" value="Limpar" onclick="javascript:limpar();"/>--></td>
					<td height="0" align="right" colspan="2"><input type="button"
						name="Button" class="bottonRightCol" value="Inserir"
						onclick="javascript:validarForm(document.forms[0])" /></td>

					<td>
					<div align="right"></div>
					</td>
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
