<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan"%>

<%@ page import="gcom.seguranca.transacao.TabelaColuna"%>

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
	src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false"
	formName="InserirTipoSolicitacaoEspecificacaoActionForm" />

<script language="JavaScript">

function validarForm(form){
	if (form.indicadorAtualizar.checked == true)
	    form.action="filtrarMotivoRetificacaoAction.do?atualizar=sim";
    else
	    form.action="filtrarMotivoRetificacaoAction.do";
    submeterFormPadrao(form);
}

function limpar(){
	var form = document.forms[0];

	form.descricao.value = '';
	form.numeroOcorrenciasNoAno.value = '';
	checkAtualizar(1);
	verificarValorAtualizar();
	setarPadrao();
}

function setarPadrao(){
	var form = document.forms[0];
	
	form.indicadorUso[0].checked = false;
	form.indicadorUso[1].checked = false;
	form.indicadorUso[2].checked = true;
	form.indicadorCompetenciaConsumo[0].checked = false;
	form.indicadorCompetenciaConsumo[1].checked = false;
	form.indicadorCompetenciaConsumo[2].checked = true;
}

function verificarValorAtualizar() {
	var form = document.forms[0];

	if (form.indicadorAtualizar.checked == true) {
		form.indicadorAtualizar.value = '1';
	} else {
		form.indicadorAtualizar.value = '';
	}
}

function checkAtualizar(valor) {

	var form = document.forms[0];
	
	if (valor == '1') {
		form.indicadorAtualizar.checked = true;
	} else {
		form.indicadorAtualizar.checked = false;
	}
	
}
</script>

</head>

<html:javascript staticJavascript="false"
	formName="FiltrarMotivoRetificacaoActionForm" />
	
<body leftmargin="5" topmargin="5"
	onload="setarPadrao();setarFoco('${requestScope.nomeCampo}');checkAtualizar('${sessionScope.atualizar}');verificarValorAtualizar();">

<html:form action="/filtrarMotivoRetificacaoAction"
	name="FiltrarMotivoRetificacaoActionForm"
	type="gcom.gui.faturamento.conta.FiltrarMotivoRetificacaoActionForm"
	method="post">

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
			<td width="600" valign="top" bgcolor="#003399" class="centercoltext">
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
					<td class="parabg">Filtrar Motivo de Retificação</td>

					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
				<tr>
					<td height="5" colspan="3"></td>
				</tr>
			</table>
			<table width="100%" border="0">
				<tr>
					<td colspan="2">Para filtrar um motivo de retificação, informe os dados abaixo:
					</td>
					<td width="100" align="left" colspan="2"><html:checkbox
						property="indicadorAtualizar" value="1"
						onchange="javascript:verificarValorAtualizar()"/><strong>Atualizar</strong></td>
				</tr>
				<tr>
					<td width="30%"><strong>Descrição:</strong></td>
					<td><html:text property="descricao" size="40" maxlength="35"
						tabindex="1" /></td>
				</tr>
				<tr>
					<td width="30%"><strong>Limite de reincidência em doze meses:</strong></td>
					<td><html:text property="numeroOcorrenciasNoAno" size="8" maxlength="3"
						tabindex="1" /></td>
				</tr>
				<tr>
					<td width="30%"><strong>Validar Competência de Consumo:<font
						color="#FF0000">*</font></strong></td>
					<td>
						<html:radio property="indicadorCompetenciaConsumo" value="1"
							tabindex="3" /> <strong>Sim</strong>&nbsp;
						<html:radio
							property="indicadorCompetenciaConsumo" value="2" tabindex="4" /> <strong>N&atilde;o</strong>&nbsp;
						<html:radio
							property="indicadorCompetenciaConsumo" value="3" tabindex="4" /> <strong>Todos</strong>
					</td>
				</tr>
				<tr>
					<td width="30%"><strong>Indicador de Uso</strong></td>
					<td>
						<html:radio property="indicadorUso" value="1"
							tabindex="3" /> <strong>Ativo</strong>&nbsp;
						<html:radio
							property="indicadorUso" value="2" tabindex="4" /> <strong>Inativo</strong>&nbsp;
						<html:radio
							property="indicadorUso" value="3" tabindex="4" /> <strong>Todos</strong>
					</td>
				</tr>
				<tr>
					<td colspan="2">&nbsp;</td>
				</tr>
			</table>
			
			<table width="100%" border="0">
				<tr>
					<td valign="top"><input name="button" type="button"
						class="bottonRightCol" value="Limpar" onclick="limpar();">&nbsp;<input
						type="button" name="ButtonCancelar" class="bottonRightCol"
						value="Cancelar"
						onClick="javascript:document.forms[0].target='';window.location.href='/gsan/telaPrincipal.do'"></td>
					<td valign="top">
					<div align="right"><input name="botaoFiltrar" type="button"
						class="bottonRightCol" value="Filtrar"
						onclick="validarForm(document.forms[0]);" tabindex="8"></div>
					</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td height="150" colspan="3">&nbsp;</td>				
				</tr>
			</table>
	</table>

	<%@ include file="/jsp/util/rodape.jsp"%>
</body>
</html:form>
</html:html>
