<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<%@ page import="gcom.util.ConstantesSistema"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>

<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false"  formName="PesquisarColunaTabelaActionForm" />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript">

function validarForm(form){

	if(testarCampoValorZero(document.PesquisarColunaTabelaActionForm.nome, 'Nome') && 
	testarCampoValorZero(document.PesquisarColunaTabelaActionForm.codigo, 'Código') && 
	testarCampoValorZero(document.PesquisarColunaTabelaActionForm.idTabela, 'Tabela')) {

		if(validatePesquisarColunaTabelaActionForm(form)){
    		submeterFormPadrao(form);
		}
	}
}

function limparForm(form) {
	
		form.nome.value = "";
		form.codigo.value = "";
		form.idTabela.value = "";
		form.nomeTabela.value = "";  
		
	
	}
	
function limparCampoTabela(form){
		form.idTabela.value = "";
		form.nomeTabela.value = "";  
}	

function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {

    var form = document.forms[0];

   
    if (tipoConsulta == 'tabela') {
     	 form.nomeTabela.style.color = "#000000";
      	 form.idTabela.value = codigoRegistro;
     	 form.nomeTabela.value = descricaoRegistro;
	}
}

</script>


</head>

<body leftmargin="5" topmargin="5" onload="resizePageSemLink(590, 346);">
<html:form action="/pesquisarColunaTabelaAction" method="post"
	onsubmit="return validatePesquisarColunaTabelaActionForm(this);">
	<table width="452" border="0" cellspacing="5" cellpadding="0">
		<tr>
			<td width="452" valign="top" class="centercoltext">
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
					<td class="parabg">Pesquisar Coluna de Tabela</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>
					<td colspan="2">Preencha os campos para pesquisar uma coluna:</td>
				</tr>


				<tr>
					<td width="162"><strong>Código:</strong></td>

					<td><strong> <html:text property="codigo" size="8"
						maxlength="8" /> </strong></td>
				</tr>

				<tr>
					<td><strong>Nome:</strong></td>
					<td><strong> <html:text property="nome" size="60"
						maxlength="60" /> </strong></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td>
						<html:radio property="tipoPesquisa"
							value="<%=ConstantesSistema.TIPO_PESQUISA_INICIAL.toString()%>" />
						Iniciando pelo texto
						<html:radio property="tipoPesquisa"
							value="<%=ConstantesSistema.TIPO_PESQUISA_COMPLETA.toString()%>" />
						Contendo o texto
					</td>
				</tr>
				<tr>
					<td><strong>Tabela:</strong></td>
					<td colspan="3"><html:text property="idTabela" size="9"
						maxlength="9" onkeypress="javascript:verificaNumeroInteiro(this);"
						onkeypress="return validaEnter(event, 'exibirPesquisarColunaTabelaAction.do','idTabela');" />
					
						<!--  <a href="javascript:abrirPopupDeNome('exibirPesquisarTabelaAuxiliarAbreviadaAction.do?tela=tabela', 400, 800, 'pesquisarTabela', 'yes');">-->
						<!--  redirecionarSubmit('exibirPesquisarTabelaAuxiliarAbreviadaAction.do?tela=tabela');-->
						<a href="javascript:redirecionarSubmit('exibirPesquisarTabelaAuxiliarAbreviadaAction.do?tela=tabela&caminhoRetornoTelaPesquisa=exibirPesquisarColunaTabelaAction');">
					 <img width="23" height="21" border="0"
						src="<bean:message key="caminho.imagens"/>pesquisa.gif"
						title="Pesquisar Tabela" /></a>
					 <logic:present
						name="idTabelaNaoEncontrada">
						<logic:equal name="idTabelaNaoEncontrada" value="exception">
							<html:text property="nomeTabela" size="40" maxlength="40"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:equal>

						<logic:notEqual name="idTabelaNaoEncontrada"
							value="exception">
							<html:text property="descricaoTabela" size="40" maxlength="40"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEqual>
					</logic:present> <logic:notPresent
						name="idTabelaNaoEncontrada">
						<logic:empty name="PesquisarColunaTabelaActionForm"
							property="idTabela">
							<html:text property="nomeTabela" value="" size="40"
								maxlength="40" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:empty>
						<logic:notEmpty name="PesquisarColunaTabelaActionForm"
							property="idTabela">
							<html:text property="nomeTabela" size="40" maxlength="40"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEmpty>
					</logic:notPresent> <a
						href="javascript:limparCampoTabela(document.forms[0]);">
					<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar" /> </a></td>
				</tr>
				<tr>
					<td colspan="2">
					<input name="Button" type="button" class="bottonRightCol"
						value="Limpar" align="left"
						onclick="limparForm(document.forms[0]);">
					<logic:present name="caminhoRetornoTelaPesquisaColunaTabela">
	          			<INPUT TYPE="button" class="bottonRightCol" value="Voltar" onclick="redirecionarSubmit('${caminhoRetornoTelaPesquisaColunaTabela}.do')"/>
	          		</logic:present>
					</td>
					<td align="right" height="24"><input type="button" name="Button"
						class="bottonRightCol" value="Pesquisar"
						onClick="javascript:validarForm(document.forms[0])" /></td>
					<td>&nbsp;</td>
				</tr>

			</table>
			</td>
		</tr>



	</table>
	<p>&nbsp;</p>


</html:form>
</body>
</html:html>

