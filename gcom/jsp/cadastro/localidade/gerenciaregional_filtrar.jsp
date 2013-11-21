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

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false" 
	formName="FiltrarGerenciaRegionalActionForm"
	dynamicJavascript="true" />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript">

function validaForm(form){

	if(testarCampoValorZero(document.FiltrarGerenciaRegionalActionForm.gerenciaRegionalID, 'Código') &&
	testarCampoValorZero(document.FiltrarGerenciaRegionalActionForm.gerenciaRegionalNome, 'Nome') && 
	testarCampoValorZero(document.FiltrarGerenciaRegionalActionForm.gerenciaRegionalNomeAbre, 'Nome Abreviado')) {

		if(validateFiltrarGerenciaRegionalActionForm(form)){

    		submeterFormPadrao(form);
		}
	}

	function limparForm(form) {
		form.gerenciaRegionalID.value = "";
		form.gerenciaRegionalNome.value = "";
		form.gerenciaRegionalNomeAbre.value = "";
	    //form.indicadorUso.value = "";  	  	    
	}
}

function verificarChecado(valor){
	form = document.forms[0];
	if(valor == "1"){
	 	form.indicadorAtualizar.checked = true;
	 }else{
	 	form.indicadorAtualizar.checked = false;
	}
}


</script>


</head>

<body leftmargin="5" topmargin="5"
	onload="verificarChecado('${sessionScope.indicadorAtualizar}');">
<html:form action="/filtrarGerenciaRegionalAction"
	name="FiltrarGerenciaRegionalActionForm"
	type="gcom.gui.cadastro.localidade.FiltrarGerenciaRegionalActionForm"
	method="post"
	onsubmit="return validateFiltrarGerenciaRegionalActionForm(this);">

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
			<td width="625" valign="top" class="centercoltext">
			<table height="100%">
				<tr>
					<td></td>
				</tr>
			</table>


			<!--Início Tabela Reference a Páginação da Tela de Processo-->
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img border="0" src="imagens/parahead_left.gif" /></td>
					<td class="parabg">Filtrar Gerência Regional</td>
					<td width="11" valign="top"><img border="0"
						src="imagens/parahead_right.gif" /></td>
				</tr>

			</table>
			<!--Fim Tabela Reference a Páginação da Tela de Processo-->
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>
					<td width="100%" colspan=2>
					<table width="100%" border="0">
						<tr>
							<td>Para filtrar a gerência regional, informe os dados abaixo:</td>
							<td align="right"><input type="checkbox"
								name="indicadorAtualizar" value="1" /><strong>Atualizar</strong>
							
							</td>
							<td align="right"><a href="javascript: abrirPopupHelp('/gsan/help/help.jsp?mapIDHelpSet=gerenciaRegionalFiltrar', 500, 700);"><span style="font-weight: bold"><font color="#3165CE">Ajuda</font></span></a></td>
						</tr>
					</table>
					</td>
				</tr>
				
				<tr>
					<td width="145"><strong>Código:<font
						color="#FF0000"></font></strong></td>

					<td width="470"><html:text property="gerenciaRegionalID" size="2"
						maxlength="2" onkeypress="return isCampoNumerico(event);" /> </td>
				</tr>
				<tr>
					<td><strong>Nome:<font
						color="#FF0000"></font></strong></td>

					<td><strong> <html:text property="gerenciaRegionalNome" size="25"
						maxlength="25" /> </strong></td>
				</tr>
				
				<tr>
					<td>&nbsp;</td>
					<td><html:radio property="tipoPesquisa"
						value="<%=ConstantesSistema.TIPO_PESQUISA_INICIAL.toString()%>" />
					Iniciando pelo texto
						<html:radio property="tipoPesquisa" tabindex="5"
						value="<%=ConstantesSistema.TIPO_PESQUISA_COMPLETA.toString()%>" />
					Contendo o texto</td>
				</tr>
				
				<tr>
					<td height="30">
						<strong>CNPJ:</strong>
					</td>
					<td>
						<html:text property="cnpjGerenciaRegional" size="14" maxlength="14" 
						onkeypress="return isCampoNumerico(event);" />
					</td>
				</tr>
				
				<tr>
					<td><strong>Nome Abreviado:<font color="#FF0000"></font></strong></td>
					<td><strong> <html:text property="gerenciaRegionalNomeAbre" size="3"
						maxlength="3" /> </strong></td>
				</tr>
				<tr>
					<td><strong>Indicador de Uso:</strong></td>
					<td><strong> <html:radio property="indicadorUso"
						value="1" /> <strong>Ativos&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<html:radio property="indicadorUso" value="2" />
					Inativos&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<html:radio
						property="indicadorUso" value="3" /> Todos</strong> </strong></td>
				</tr>
				<tr>
					<td><input type="button" name="Submit22" class="bottonRightCol" value="Limpar"
						onClick="javascript:window.location.href='/gsan/exibirFiltrarGerenciaRegionalAction.do?menu=sim'">
						<!-- <input type="button" name="Button" class="bottonRightCol" value="Cancelar"
								onClick="window.location.href='/gsan/telaPrincipal.do'" /> -->
					</td>
					<td align="right">
						<input type="button" name="Submit2" class="bottonRightCol" value="Filtrar"
							onclick="validaForm(document.forms[0]);">
					</td>
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

