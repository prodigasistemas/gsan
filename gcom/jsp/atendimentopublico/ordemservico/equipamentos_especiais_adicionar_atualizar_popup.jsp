<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<html:html>
<head>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">

<%-- Carrega validações do validator --%>
<%--html:javascript staticJavascript="false"  formName="AtualizarEquipamentosEspeciaisActionForm" /--%>
<%-- Carrega javascripts da biblioteca --%>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>
<%-- Novos Javascripts --%>
<script language="JavaScript">
<!--
	/* Valida Form */
	function validaForm() {
		var form = document.AtualizarEquipeActionForm;
		if (validaEquipamentosEspeciais()) {
			var lista = new Array();
			lista[0] = form.equipamentosEspeciasId.value;
			lista[1] = form.descricao.value;
			lista[2] = form.quantidade.value;

			if (validaEquipamentosEspeciais()) {
				window.opener.recuperarDadosPopup('', lista,
						'equipeEquipamentosEspeciais');
				window.close();
			}
		}
	}

	function validaEquipamentosEspeciais() {

		var form = document.AtualizarEquipeActionForm;

		if (form.equipamentosEspeciasId.value == ''
				|| form.equipamentosEspeciasId.value == '-1') {
			alert('Informe o Equipamento Especial.');
			return false
		} else if (form.quantidade.value == '') {
			alert('Informe quantidade.');
			return false
		}

		return true;
	}

	/* Limpa a descrição do equipemento */
	function limpaEquipamentosEspeciais() {
		var form = document.AtualizarEquipeActionForm;
		form.equipamentosEspeciasId.value = '-1';
	}

	/* Limpa Form */
	function limparForm() {
		var form = document.AtualizarEquipeActionForm;
		limpaEquipamentosEspeciais();
		limparQuantidade();

	}

	/* Limpa Quantidade */
	function limparQuantidade() {
		var form = document.AtualizarEquipeActionForm;
		form.quantidade.value = '';
	}

	/* Fecha Popup */
	function fechar() {
		limparForm();
		window.close();
	}
//-->
</script>
</head>

<body leftmargin="0" topmargin="0" onload="resizePageSemLink(600, 280)">


<html:form action="/atualizarEquipeAction.do"
	name="AtualizarEquipeActionForm"
	type="gcom.gui.atendimentopublico.ordemservico.AtualizarEquipeActionForm"
	method="post">
<input type="hidden" name="descricao">
	<table width="570" border="0" cellpadding="0" cellspacing="5">
		<tr>
			<td width="560" valign="top" class="centercoltext">
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
					<td class="parabg">Adicionar Equipamentos da Equipe</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" />
					</td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>
					<td colspan="5">Preencha os campos para inserir um Equipamento
					da equipe:</td>
				</tr>
				<tr>



					<td><strong>Equipamento Especial:</strong> <font
						color="#FF0000">*</font></td>

					<td colspan="2"><html:select property="equipamentosEspeciasId"
						style="width: 230px;">
						<logic:present name="colecaoEquipamentosEspeciais" scope="request">
							<html:option value="-1">&nbsp;</html:option>
							<html:options collection="colecaoEquipamentosEspeciais"
								labelProperty="descricao" property="id" />

						</logic:present>
					</html:select></td>

				</tr>
				<tr>
					<td height="24"><strong>Quantidade:</strong> <font
						color="#FF0000">*</font></td>
					<td colspan="4"><strong> <html:text onkeypress="return isCampoNumerico(event);"
						property="quantidade" size="4" maxlength="4"/> </strong></td>
				</tr>
				<tr>
					<td colspan="4"><strong> <font color="#FF0000">*</font>
					</strong> Campos obrigat&oacute;rios</td>
					</tr>
				
				<tr>
					<td height="27" colspan="5">
					<div align="right"><input name="Button" type="button"
						class="bottonRightCol" value="Inserir"
						onclick="javascript:validaForm();"> <input name="Button2"
						type="button" class="bottonRightCol" value="Fechar"
						onClick="javascript:fechar();"></div>
					</td>
				</tr>
			</table>
			<p>&nbsp;</p>
			</td>
		</tr>
	</table>
</html:form>
</body>
</html:html>