<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>




<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>

<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="PesquisarTipoServicoReferenciaActionForm"
	dynamicJavascript="true" />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript">

function validarForm(form){

	if(testarCampoValorZero(document.PesquisarTipoServicoReferenciaActionForm.descricao, 'Descrição') && 
	testarCampoValorZero(document.PesquisarTipoServicoReferenciaActionForm.descricaoAbreviada, 'Descricao Abreviada') &&
	testarCampoValorZero(document.PesquisarTipoServicoReferenciaActionForm.idTipoServico, 'Tipo de Serviço')) {

		if(validatePesquisarTipoServicoReferenciaActionForm(form)){

    		submeterFormPadrao(form);
		}
	}

function limparForm(form) {
	
		form.descricaoAbreviada.value = "";
		form.descricao.value = "";
		form.indicadorExistenciaOsReferencia.value = "";
	    form.descricaoTipoServico.value = "";
	    form.tipoServico.value = "";  
	    form.idTipoServico.value = "";  	    
		
		
	
	}
}

	function limpaTipoServicoTecla() {

		var form = document.PesquisarTipoServicoReferenciaActionForm;


	form.descricaoTipoServico.value = "";

	
		}
		
		function limpaTipoServico() {
		
		var form = document.PesquisarTipoServicoReferenciaActionForm;
		
	
	form.descricaoTipoServico.value = "";
	form.idTipoServico.value = "";
	}


</script>


</head>

<body leftmargin="5" topmargin="5" onload="resizePageSemLink(755, 355);">

<html:form action="/pesquisarTipoServicoReferenciaAction" method="post"
	name="PesquisarTipoServicoReferenciaActionForm"
	type="gcom.gui.atendimentopublico.ordemservico.PesquisarTipoServicoReferenciaActionForm">


	<table width="720" border="0" cellspacing="5" cellpadding="0">

		<tr>

			<td width="625" valign="top" class="centercoltext">
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
					<td class="parabg">Pesquisar Tipo de Serviço de Referência</td>
					<td width="11" valign="top"><img border="0"
						src="imagens/parahead_right.gif" /></td>
				</tr>

			</table>
			<!--Fim Tabela Reference a Páginação da Tela de Processo-->
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>

					<td colspan="2">Preencha os campos para pesquisar um tipo de
					serviço de referência:</td>
				</tr>
				<tr>
					<td width="162"><strong>Descri&ccedil;&atilde;o:<font
						color="#FF0000"></font></strong></td>

					<td><strong> <html:text property="descricao" size="50"
						maxlength="50" /> </strong></td>
				</tr>
				<tr>
					<td><strong>Descri&ccedil;&atilde;o Abreviada:<font color="#FF0000"></font></strong></td>
					<td><strong> <html:text property="descricaoAbreviada" size="5"
						maxlength="5" /> </strong></td>
				</tr>
				<tr>
					<td width="40%"><strong>Indicador de existência da OS de
					Referência:<font color="#FF0000">*</font></strong></td>
					<td width="60%"><strong> <html:radio
						property="indicadorExistenciaOsReferencia" value="1" /> <strong>Sim
					<html:radio property="indicadorExistenciaOsReferencia" value="2" />
					Não <html:radio property="indicadorExistenciaOsReferencia" value="" />
					Todas</strong> </strong></td>
				</tr>
				<tr>
					<td height="30"><strong>Tipo de Servi&ccedil;o:</strong></td>
					<td colspan="4"><html:text property="idTipoServico" size="4"
						maxlength="4"
						onkeyup="javascript:limpaTipoServicoTecla();validaEnter(event, 'exibirPesquisarTipoServicoReferenciaAction.do?enter=sim', 'idTipoServico')" />
					<a
						href="javascript:abrirPopup('exibirPesquisarTipoServicoAction.do?caminhoRetornoTelaPesquisaTipoServico=exibirPesquisarTipoServicoReferenciaAction', 400, 800);">
					<img src="/gsan/imagens/pesquisa.gif" alt="Pesquisar" border="0"
						height="21" width="23"></a><logic:present
						name="idTipoServicoNaoEncontrado">
						<html:text property="descricaoTipoServico" size="50"
							maxlength="45" readonly="true"
							style="background-color:#EFEFEF; border:0; color: #ff0000" />
					</logic:present><logic:notPresent name="idTipoServicoNaoEncontrado">
						<html:text property="descricaoTipoServico" size="50"
							maxlength="45" readonly="true"
							style="background-color:#EFEFEF; border:0; font-color: #000000" />
					</logic:notPresent> <a href="javascript:limpaTipoServico()"> <img
						src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" onclick="Apagar" /></a></td>
				</tr>


				<tr>

					<td colspan="2"><logic:present
						name="caminhoRetornoTelaPesquisaTipoReferencia">
						<INPUT TYPE="button" class="bottonRightCol" value="Voltar"
							onclick="redirecionarSubmit('${caminhoRetornoTelaPesquisaTipoReferencia}.do')" />
					</logic:present> <input name="Button" type="button"
						class="bottonRightCol" value="Limpar" align="left"
						onclick="window.location.href='<html:rewrite page="/exibirPesquisarTipoServicoReferenciaAction.do"/>'">
					</td>
					<td align="right" height="24"><input name="Button" type="button"
						class="bottonRightCol" value="Pesquisar"
						onClick="javascript:validarForm(document.forms[0])" /></td>
					<td>&nbsp;</td>
				</tr>
			</table>
			<p>&nbsp;</p>
		</tr>
		<tr>

		</tr>
	</table>
</html:form>
</body>
</html:html>

