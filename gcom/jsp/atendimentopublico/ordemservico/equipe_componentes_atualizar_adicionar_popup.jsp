<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<html:html>
<head>
<title>GSAN - Sistema Integrado de Gest&atilde;o de Servi&ccedil;os de Saneamento</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">

<%-- Carrega validações do validator --%>
<%--html:javascript staticJavascript="false"  formName="InserirEquipeActionForm" /--%>
<%-- Carrega javascripts da biblioteca --%>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>
<%-- Novos Javascripts --%>
<script language="JavaScript">
	/* Valida Form */
	function validaForm(tipoConsulta) {
		var form = document.forms[0];
		if(validaRadioButton()) {
			if(validaNomeComponente()) {
				var lista = new Array();
				lista[0] = form.idFuncionario.value;
				lista[1] = form.nomeComponente.value;
				/* Checa Radio Responsável */
				if (form.indicadorResponsavel[0].checked) {
					lista[2] = form.indicadorResponsavel[0].value;
				} else {
					lista[2] = form.indicadorResponsavel[1].value;
				}
				if (validaComponente()) {
					window.opener.recuperarDadosPopup('', lista, tipoConsulta);
					window.close();
				}
			}
   	    }
	}
	
	function validaComponente(){
		var form = document.forms[0];

		if (form.idFuncionario.value != '') {
			if (form.nomeFuncionario.value == '' || form.nomeFuncionario.value == 'Funcionário inexistente') {
				alert('Informe o Funcionário.');
				return false;
			}
		}
		return true;
	}
	
	/* Limpa Nome Componente  */
	function limpaNomeComponente() {
		var form = document.forms[0];
		form.nomeComponente.value = '';
	}
	/* Valida Nome Componente */
	function validaNomeComponente() {
		var form = document.forms[0];

		if (form.nomeComponente.value == '' && 
				form.idFuncionario.value == '') {
			alert("Informe o Nome do Componente da Equipe ou Funcionário.");		
			return false;
		}
		return true;		
	}
	/* Limpa Form */	 
	function limparForm() {
		var form = document.forms[0];
		limpaNomeComponente();
		limparFuncionario();
		form.indicadorResponsavel[0].checked = false;
		form.indicadorResponsavel[1].checked = false;		
	}
	/* Limpa Funcionário do Form */	 
	function limparFuncionario() {
		var form = document.forms[0];
		form.idFuncionario.value = '';
		form.nomeFuncionario.value = '';
	}
	
		/* Limpa Funcionário do Form */	 
	function limparFuncionarioTecla() {
		var form = document.forms[0];
		form.nomeFuncionario.value = '';
	}
	/* Chama Popup */ 
	function chamarPopup(url, tipo, objeto, codigoObjeto, altura, largura, msg,objetoRelacionado){
		if(objetoRelacionado.disabled != true){
			if (objeto == null || codigoObjeto == null){
				abrirPopup(url + "?" + "tipo=" + tipo, altura, largura);
			} else{
				if (codigoObjeto.length < 1 || isNaN(codigoObjeto)){
					alert(msg);
				} else{
					abrirPopup(url + "?" + "tipo=" + tipo + "&" + objeto + "=" + codigoObjeto + "&caminhoRetornoTelaPesquisa=" + tipo, altura, largura);
				}
			}
		}
	}
	/* Recupera Dados Popup */	
	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
	    var form = document.forms[0];
	    if (tipoConsulta == 'funcionario') {
			form.idFuncionario.value = codigoRegistro;
			form.nomeFuncionario.value = descricaoRegistro;
			form.nomeFuncionario.style.color = "#000000";
	    }
	}
	/* Valida Radio */
  	function validaRadioButton() {
		var form = document.forms[0];
		if (!form.indicadorResponsavel[0].checked
				&& !form.indicadorResponsavel[1].checked) {
			alert("Informe Indicador do Responsável.");		
			return false;
		}		
		return true;
   	}
   	/* Fecha Popup */
   	function fechar() {
   		limparForm();
   		window.close();
   	}
</script>
</head>

<body leftmargin="0" topmargin="0" onload="resizePageSemLink(600, 300)">
<html:form action="/inserirEquipeAction.do"
	name="AtualizarEquipeActionForm"
	type="gcom.gui.atendimentopublico.ordemservico.AtualizarEquipeActionForm"
	method="post">

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
					<td class="parabg">Adicionar Componentes da Equipe</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" />
					</td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>
					<td colspan="5">Preencha os campos para inserir um componente da
					equipe:</td>
				</tr>
				<tr>
					<td width="17%" height="24"><strong> Respons&aacute;vel: <font
						color="#FF0000">*</font> </strong></td>
					<td width="9%"><html:radio property="indicadorResponsavel"
						value="1" /> <strong>Sim</strong></td>
					<td width="74%" colspan="3"><html:radio
						property="indicadorResponsavel" value="2" /> <strong>N&atilde;o</strong>
					</td>
				</tr>
				<tr>
					<td height="24"><strong>Funcion&aacute;rio:</strong></td>
					<td colspan="4"><strong> <html:text property="idFuncionario"
						size="9" maxlength="9"
						onkeypress="javascript:validaEnterComMensagem(event, 'exibirAtualizarEquipeAction.do', 'idFuncionario','Funcionário');"
						onkeydown="javascript:limpaNomeComponente();limparFuncionarioTecla();" />
					<a href="javascript:redirecionarSubmit('exibirFuncionarioPesquisar.do?caminhoRetornoTelaPesquisaFuncionario=exibirAtualizarEquipeAction');"><img src="<bean:message key='caminho.imagens'/>pesquisa.gif"
						width="23" height="21" style="cursor:hand;" name="imagem" border="0"
						alt="Pesquisar"></a> <logic:present name="idFuncionarioNaoEncontrado">
						<html:text property="nomeFuncionario" readonly="true"
							style="background-color:#EFEFEF; border:0; color: #ff0000"
							size="30" maxlength="30" />
					</logic:present> <logic:notPresent
						name="idFuncionarioNaoEncontrado">
						<html:text property="nomeFuncionario" readonly="true"
							style="background-color:#EFEFEF; border:0; color: #000000"
							size="30" maxlength="30" />
					</logic:notPresent> <a href="javascript:limparFuncionario();"> <img
						border="0" title="Apagar"
						src="<bean:message key='caminho.imagens'/>limparcampo.gif" /></a>
					</strong></td>
				</tr>
				<tr>
					<td height="24"><strong>Nome do Componente:</strong> <font
						color="#FF0000">*</font></td>
					<td colspan="4"><strong> <html:text property="nomeComponente"
						size="40" maxlength="40"
						onkeydown="javascript:limparFuncionario();" /> </strong></td>
				</tr>
				<tr>
					<td height="24"><strong> </strong></td>
					<td colspan="4"><strong> <font color="#FF0000">*</font> </strong>
					Campos obrigat&oacute;rios</td>
				</tr>
				<tr>
					<td height="27" colspan="5">
					<div align="right"><logic:present name="popUpAtualizar">
						<input name="Button" type="button" class="bottonRightCol"
							value="Atualizar"
							onclick="javascript:validaForm('equipeComponenteAtualizar');">
					</logic:present> <logic:notPresent name="popUpAtualizar">
						<input name="Button" type="button" class="bottonRightCol"
							value="Inserir"
							onclick="javascript:validaForm('equipeComponente');">
					</logic:notPresent> <input name="Button2" type="button"
						class="bottonRightCol" value="Fechar"
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
