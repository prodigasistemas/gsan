<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>




<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@page import="gcom.util.ConstantesSistema" isELIgnored="false"%>
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
	formName="FiltrarAgenciaBancariaActionForm" dynamicJavascript="true" />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript">

function validaForm(form){

	if(testarCampoValorZero(document.FiltrarAgenciaBancariaActionForm.bancoID, 'Banco') &&
	testarCampoValorZero(document.FiltrarAgenciaBancariaActionForm.codigo, 'Código') && 
	testarCampoValorZero(document.FiltrarAgenciaBancariaActionForm.nome, 'Nome da Agência')) {

		if(validateFiltrarAgenciaBancariaActionForm(form)){
			var atualizar = form.atualizar.value;
			form.action = 'filtrarAgenciaBancariaAction.do?atualizar=' + atualizar;
    		submeterFormPadrao(form);
		}
	}

function limparForm(form) {
		form.bancoID.value = "";
		form.codigo.value = "";
		form.nome.value = "";
		
	}
}

	function verificarValorAtualizar() {
	
		var form = document.forms[0];
	
		if (form.atualizar.checked == true) {
			form.atualizar.value = '1';
		} else {
			form.atualizar.value = '';
		}
		
	}
	
	function checkAtualizar(valor) {
	
		var form = document.forms[0];
		
		if (valor == '1') {
			form.atualizar.checked = true;
		} else {
			form.atualizar.checked = false;
		}
		
	}


</script>


</head>

<body leftmargin="5" topmargin="5"
	onload="verificarChecado('${sessionScope.indicadorAtualizar}'); setaFocus();checkAtualizar('${sessionScope.atualizar}');verificarValorAtualizar();">
<html:form action="/filtrarAgenciaBancariaAction"
	name="FiltrarAgenciaBancariaActionForm"
	type="gcom.gui.arrecadacao.banco.FiltrarAgenciaBancariaActionForm"
	method="post"
	onsubmit="return validateFiltrarAgenciaBancariaActionForm(this);">

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
					<td class="parabg">Filtrar Agência Bancária</td>
					<td width="11" valign="top"><img border="0"
						src="imagens/parahead_right.gif" /></td>
				</tr>

			</table>
			<!--Fim Tabela Reference a Páginação da Tela de Processo-->
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>
					<td height="10" colspan="3">Para filtrar a agência bancária,
					informe os dados abaixo:</td>
					<td align="right"><html:checkbox property="atualizar" value="1"
						onclick="javascript:verificarValorAtualizar()" /><strong>Atualizar</strong></td>
				<tr>
					<td width="150"><strong>Banco:</strong></td>
					<td colspan="2" align="left"><html:select property="bancoID"
						tabindex="1">
						<html:option value="-1">&nbsp;</html:option>
						<html:options collection="colecaoBanco" labelProperty="descricao"
							property="id" />
					</html:select></td>
				</tr>

				<tr>
					<td width="150"><strong>Código da Agência:</strong></td>
					<td colspan="2"><html:text property="codigo" size="5" maxlength="5"
						tabindex="2" /></td>
				</tr>


				<tr>
					<td width="150"><strong>Nome da Agência:</strong></td>
					<td colspan="2"><html:text property="nome" size="50" maxlength="40"
						tabindex="3" /></td>
				</tr>

				<tr>
					<td>&nbsp;</td>
					<td><html:radio property="tipoPesquisa"
						value="<%=ConstantesSistema.TIPO_PESQUISA_INICIAL.toString()%>" />
					Iniciando pelo texto<html:radio property="tipoPesquisa"
						tabindex="5"
						value="<%=ConstantesSistema.TIPO_PESQUISA_COMPLETA.toString()%>" />
					Contendo o texto</td>
				</tr>

				<tr>
					<td></td>
				</tr>
				<tr>
					<td width="150"><strong> <font color="#FF0000"> <input type="button"
						name="Submit22" class="bottonRightCol" value="Limpar"
						onClick="javascript:window.location.href='/gsan/exibirFiltrarAgenciaBancariaAction.do?menu=sim'"><!-- <input type="button"
								name="Submit23" class="bottonRightCol" value="Cancelar"
								onClick="javascript:window.location.href='/gsan/telaPrincipal.do'"> -->
					</font> </strong></td>
					<td colspan="3" align="right"><input type="button" name="Submit2"
						class="bottonRightCol" value="Filtrar"
						onclick="validaForm(document.forms[0]);"></td>
				</tr>
			</table>
			<p>&nbsp;</p>
		</tr>
		<!-- Rodapé -->
		<%@ include file="/jsp/util/rodape.jsp"%>
	</table>
	<p>&nbsp;</p>

	<tr>

	</tr>

</html:form>
</body>
</html:html>

