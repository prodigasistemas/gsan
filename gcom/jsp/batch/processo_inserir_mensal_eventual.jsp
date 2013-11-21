<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<%@ page import="gcom.util.ConstantesSistema"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<head>
<html:html>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="InserirProcessoMensalEventualActionForm" />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript">

function validarForm(form){
	var form = document.forms[0];
	if(testarCampoValorZero(document.InserirProcessoMensalEventualActionForm.idProcesso, 'Código do Processo') && testarCampoValorZero(document.InserirProcessoMensalEventualActionForm.idProcessoIniciadoPrecedente, 'Código do Processo Iniciado Precedente')){

		if(validateInserirProcessoMensalEventualActionForm(form) && validarHoraCompleta(form.horaAgendamento.value)){
			if (confirm('Confirma Início do Processo?')) {
			    //form.submit();
			    submitForm(form);
		    }
		}
	}
}

function limparProcessoIniciadoPrecedente() {
	var form = document.forms[0];
	
	form.idProcessoIniciadoPrecedente.value = "";
	form.descricaoProcessoIniciadoPrecedente.value = "";
}

function limparProcesso() {
	var form = document.forms[0];
	
	form.idProcesso.value = "";
    form.descricaoProcesso.value = "";

}
function limparPesquisarProcesso() {
		var form = document.forms[0];
	
		form.idProcesso.value = "";
	    form.descricaoProcesso.value = "";
	}
	
	 //Recebe os dados do(s) popup(s)
  	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {

    	var form = document.forms[0];

	    if (tipoConsulta == 'processo') {
      		limparPesquisarProcesso();
	      	
	      	form.idProcesso.value = codigoRegistro;
	      	form.descricaoProcesso.value = descricaoRegistro;
	      	form.descricaoProcesso.style.color = "#000000";
	      	form.idProcesso.focus();
	      	
	      	window.location = "/gsan/exibirInserirProcessoMensalEventualAction.do?idProcesso="+codigoRegistro;
	    }
	}

function verificarCampoAgendamento() {

/*
	var form = document.InserirProcessoMensalEventualActionForm;
	
	if (form.idSituacaoProcesso[form.idSituacaoProcesso.selectedIndex].value == 5 ){
		form.horaAgendamento.value = '';
		form.dataAgendamento.value = '';
		form.horaAgendamento.disabled = true;	
		form.dataAgendamento.disabled = true;	
	} else {

		form.horaAgendamento.disabled = false;	
		form.dataAgendamento.disabled = false;	
	}
*/
}




</script>
</head>

<body leftmargin="5" topmargin="5"
	onload="javascript:setarFoco('${requestScope.nomeCampo}');">
<div id="formDiv">
<html:form action="/inserirProcessoMensalEventualAction.do"
	name="InserirProcessoMensalEventualActionForm"
	type="gcom.gui.batch.InserirProcessoMensalEventualActionForm"
	method="post"
	onsubmit="return InserirProcessoMensalEventualActionForm(this);">

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

			<td valign="top" class="centercoltext">

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
					<td class="parabg">Inserir Processo Mensal ou Eventual</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>

				</tr>
			</table>

			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>
					<td colspan="3">Para iniciar um processo, informe os dados abaixo:</td>
				</tr>
				<tr>
				<td height="31"><strong>Processo:</strong></td>		
				<td colspan="3">
						<html:text property="idProcesso" 
							size="5"
							maxlength="5"
							onkeypress="javascript:return validaEnter(event, '/gsan/exibirInserirProcessoMensalEventualAction.do', 'idProcesso');" />

					<a href="javascript:abrirPopup('exibirPesquisarProcessoAction.do?indicadorUsoTodos=1', 250, 495);">
						<img width="23" 
							height="21" 
							border="0"
							src="<bean:message key="caminho.imagens"/>pesquisa.gif"
							title="Pesquisar Processo" /></a>
							 

					<logic:notPresent name="processoNaoEncontrada" scope="request">

						<html:text property="descricaoProcesso" 
							readonly="true"
							style="background-color:#EFEFEF; border:0; color: #000000"
							size="37" 
							maxlength="40" />
					</logic:notPresent> 
					
					<logic:present name="processoNaoEncontrada" scope="request">
						<html:text property="descricaoProcesso" 
							size="30" 
							maxlength="30"
							readonly="true"
							style="background-color:#EFEFEF; border:0; color: red" />
					</logic:present> 
					
					<a href="javascript:limparPesquisarProcesso();"> 
						<img border="0" 
							title="Apagar"
							src="<bean:message key='caminho.imagens'/>limparcampo.gif" /></a>
					</td>
				</tr>

<%--				<tr>
					<td height="0"><strong>Situa&ccedil;&atilde;o do Processo:<font
						color="#FF0000">*</font></strong></td>
					<td colspan="2"><html:select property="idSituacaoProcesso"
						onchange="verificarCampoAgendamento();" tabindex="2">
						<html:option
							value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
						<html:options collection="colecaoProcessoSituacao"
							labelProperty="descricao" property="id" />

					</html:select></td>
				</tr>
				
--%>				
				<tr>
					<td height="0"><strong>Data do Agendamento:<font color="#FF0000">*</font></strong></td>
					<td><html:text property="dataAgendamento" size="10" maxlength="10"
						tabindex="3" onkeyup="javascript:mascaraData(this, event);" /> <a
						href="javascript:abrirCalendario('InserirProcessoMensalEventualActionForm', 'dataAgendamento')">
					<img border="0"
						src="<bean:message key="caminho.imagens"/>calendario.gif"
						width="20" border="0" align="absmiddle" alt="Exibir Calendário" /></a>
					<font size="2">dd/mm/aaaa</font></td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td height="0"><strong>Hora do Agendamento:<font color="#FF0000">*</font></strong></td>
					<td><html:text property="horaAgendamento" size="8" maxlength="8"
						tabindex="4" onkeyup="mascaraHoraMinutoSegundo(this, event);" />
					(hh:mm:ss)</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td height="0"><strong>Processo Iniciado Precedente:</strong></td>
					<td width="53%"><html:text property="idProcessoIniciadoPrecedente"
						size="5" maxlength="4" tabindex="5"
						onkeypress="javascript:return validaEnter(event, '/gsan/exibirInserirProcessoMensalEventualAction.do', 'idProcessoIniciadoPrecedente');" />
					<img src="<bean:message key="caminho.imagens"/>pesquisa.gif"
						width="23" height="21"> <span class="style1"> <logic:present
						name="idProcessoIniciadoPrecedenteNaoEncontrado">

						<html:text property="descricaoProcessoIniciadoPrecedente"
							size="30" maxlength="30" readonly="true"
							style="background-color:#EFEFEF; border:0; color: red" />
					</logic:present> <logic:notPresent
						name="idProcessoIniciadoPrecedenteNaoEncontrado">

						<html:text property="descricaoProcessoIniciadoPrecedente"
							size="30" maxlength="30" readonly="true"
							style="background-color:#EFEFEF; border:0; color: #000000" />

					</logic:notPresent> <img
						src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0"
						onclick="javascript:limparProcessoIniciadoPrecedente();"> </span></td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td height="24" colspan="3">
					<hr>
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center" bgcolor="#99CCFF">
					<div align="center"><strong>Funcionalidade</strong></div>
					</td>
					<td width="27%" bgcolor="#99CCFF"><strong>Unidade de Processamento</strong></td>
				</tr>

				<logic:present name="colecaoProcessoFuncionalidade" scope="request">
					<logic:iterate name="colecaoProcessoFuncionalidade"
						id="processoFuncionalidade" scope="request">
						<tr>
							<td height="24" colspan="2"><bean:write
								name="processoFuncionalidade"
								property="funcionalidade.descricao" /></td>
							<td><bean:write name="processoFuncionalidade"
								property="unidadeProcessamento.descricaoUnidadeProcessamento" /></td>
						</tr>
					</logic:iterate>
				</logic:present>

				<tr>
					<td height="24" colspan="3">
					<hr>
					</td>
				</tr>
				<tr>
					<td height="24">&nbsp;</td>
					<td colspan="2"><strong><font color="#FF0000">*</font></strong>
					Campos obrigat&oacute;rios</td>
				</tr>
				<tr>
					<td colspan="2"><input type="button" name="Button"
						class="bottonRightCol" value="Desfazer"
						onClick="javascript:window.location.href='/gsan/exibirInserirProcessoMensalEventualAction.do'"
						style="width: 80px" /> &nbsp; <input type="button" name="Button"
						class="bottonRightCol" value="Cancelar"
						onClick="javascript:window.location.href='/gsan/telaPrincipal.do'"
						style="width: 80px" /></td>
					<td align="right"><input type="button" name="Button"
						class="bottonRightCol" value="Iniciar" tabindex="6"
						style="width: 80px"
						onClick="javascript:validarForm(document.forms[0]);" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>

			</td>
		</tr>
	</table>


	<%@ include file="/jsp/util/rodape.jsp"%>


</html:form>
</div>
</body>

<%@ include file="/jsp/util/telaespera.jsp"%>

<%--<script>
	document.forms[0].horaAgendamento.disabled = true;	
	document.forms[0].dataAgendamento.disabled = true;	
</script>--%>

</html:html>
