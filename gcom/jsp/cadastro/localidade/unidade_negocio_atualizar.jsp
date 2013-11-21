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

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false"
	formName="AtualizarUnidadeNegocioActionForm" />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>

<script language="JavaScript">

	function validarForm(){
		var form = document.forms[0];
		if (validateAtualizarUnidadeNegocioActionForm(form)){
				submeterFormPadrao(form);
			}	
	}

	
	function limparCliente() {
		var form = document.AtualizarUnidadeNegocioActionForm;
		form.idCliente.value = "";
		form.nomeCliente.value = "";
	
	}
	
	
	function limparGerenciaRegional() {
		var form = document.AtualizarUnidadeNegocioActionForm;
		form.idGerenciaRegional.value = "";
		form.nomeGerenciaRegional.value = "";
	
	}
	
	//Chama Popup
	function chamarPopup(url, tipo, objeto, codigoObjeto, altura, largura, msg,objetoRelacionado){
		if(objetoRelacionado.disabled != true){
			if (objeto == null || codigoObjeto == null){
				abrirPopup(url + "?" + "tipo=" + tipo, altura, largura);
			}
			else{
				if (codigoObjeto.length < 1 || isNaN(codigoObjeto)){
					alert(msg);
				}
				else{
					abrirPopup(url + "?" + "tipo=" + tipo + "&" + objeto + "=" + codigoObjeto + "&caminhoRetornoTelaPesquisa=" + tipo, altura, largura);
				}
			}
		}
	}

	//Recupera Dados Popup
	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
		var form = document.forms[0];
	    if (tipoConsulta == 'cliente') {
	    	form.idCliente.value = codigoRegistro;
	    	form.nomeCliente.value = descricaoRegistro;
	    }
	    if (tipoConsulta == 'gerenciaRegional') {
	    	form.idGerenciaRegional.value = codigoRegistro;
	    	form.nomeGerenciaRegional.value = descricaoRegistro;
	    }
	}
	
	
</script>

</head>

<body leftmargin="5" topmargin="5" onload="setarFoco('nome');">

<html:form action="/atualizarUnidadeNegocioAction.do"
	name="AtualizarUnidadeNegocioActionForm"
	type="gcom.gui.cadastro.localidade.AtualizarUnidadeNegocioActionForm"
	method="post"
	onsubmit="return validateAtualizarUnidadeNegocioActionForm(this);">

	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>


	<table width="770" border="0" cellspacing="5" cellpadding="0">

		<tr>
			<td width="150" valign="top" class="leftcoltext">
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
			<table>
				<tr>
					<td></td>

				</tr>
			</table>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img border="0" src="imagens/parahead_left.gif" /></td>
					<td class="parabg">Atualizar Unidade de Negócio</td>
					<td width="11" valign="top"><img border="0"
						src="imagens/parahead_right.gif" /></td>
				</tr>

			</table>
			<!--Fim Tabela Reference a Páginação da Tela de Processo-->

			<table width="100%" border="0">
				<tr>

					<td colspan="2">Para atualizar uma unidade de negócio, informe os
					dados abaixo:</td>
				</tr>
				<tr>
					<td><strong>C&oacute;digo:</strong></td>
					<td><html:hidden property="id" /> 
					<bean:write	name="AtualizarUnidadeNegocioActionForm" property="id" />
					</td>
				</tr>
				<tr>
					<td HEIGHT="30"><strong>Nome: <font color="#FF0000">*</font></strong></td>
					<td colspan="2"><html:text property="nome" maxlength="50" size="50" tabindex="1"
						onkeyup="limitTextArea(document.forms[0].descricao, 50, document.getElementById('utilizado'), document.getElementById('limite'));" />
					<br>
					</td>
				</tr>
				<tr>
					<td><strong>Nome Abreviado: <font color="#FF0000">*</font></strong></td>
					<td colspan="2"><span class="style2"> <html:text
						property="nomeAbreviado" size="4" maxlength="4" tabindex="2"/> </span></td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td><strong>CNPJ:</strong></td>
					<td colspan="2"><span class="style2"> <html:text
						property="numeroCnpj" size="14" maxlength="14" tabindex="3"/> </span></td>
					<td>&nbsp;</td>
				</tr>

				<tr>
					<td HEIGHT="30" WIDTH="120"><strong>Gerente da Unidade de Neg&oacute;cio:</strong></td>
					<td colspan="2"><html:text property="idCliente" size="10"
						maxlength="9" tabindex="4"
						onkeypress="validaEnterComMensagem(event, 'exibirAtualizarUnidadeNegocioAction.do', 'idCliente', 'Cliente');" />
					<a
						href="javascript:chamarPopup('exibirPesquisarClienteAction.do', 'cliente', null, null, 800, 490, '',document.forms[0].idCliente);">
					<img title="Pesquisar Gerente da Unidade de Negócio" src="<bean:message key='caminho.imagens'/>pesquisa.gif"
						width="23" height="21" alt="Pesquisar" border="0"></a>
					<logic:notPresent name="corCliente" scope="request">

						<html:text property="nomeCliente" readonly="true"
							style="background-color:#EFEFEF; border:0; color: #000000"
							size="37" maxlength="40" />

					</logic:notPresent> <logic:present name="corCliente"
						scope="request">

						<html:text property="nomeCliente" size="30" maxlength="30"
							readonly="true"
							style="background-color:#EFEFEF; border:0; color: red" />

					</logic:present> <a href="javascript:limparCliente();"> <img
						border="0" title="Apagar"
						src="<bean:message key='caminho.imagens'/>limparcampo.gif" /> </a>
					</td>
				</tr>

				<tr>
					<td HEIGHT="30" WIDTH="100"><strong>Gerencial Regional:</strong><font color="FF0000">*</font></td>
					<td colspan="2"><html:text property="idGerenciaRegional" size="10"
						maxlength="9" tabindex="5"
						onkeypress="validaEnterComMensagem(event, 'exibirAtualizarUnidadeNegocioAction.do', 'idGerenciaRegional', 'Gerencial Regional');" />
					<a
						href="javascript:chamarPopup('exibirPesquisarGerenciaRegionalAction.do', 'Gerência Regional', null, null, 800, 490, '',document.forms[0].idGerenciaRegional);">
					<img title="Pesquisar Gerencial Regional" src="<bean:message key='caminho.imagens'/>pesquisa.gif"
						width="23" height="21" alt="Pesquisar" border="0"></a> <logic:notPresent
						name="corGerenciaRegional" scope="request">

						<html:text property="nomeGerenciaRegional" readonly="true"
							style="background-color:#EFEFEF; border:0; color: #000000"
							size="37" maxlength="40" />

					</logic:notPresent> <logic:present name="corGerenciaRegional"
						scope="request">

						<html:text property="nomeGerenciaRegional" size="30"
							maxlength="30" readonly="true"
							style="background-color:#EFEFEF; border:0; color: red" />

					</logic:present> <a href="javascript:limparGerenciaRegional();"> <img
						border="0" title="Apagar"
						src="<bean:message key='caminho.imagens'/>limparcampo.gif" /> </a>
					</td>
				</tr>

				<tr>
					<td><strong>Indicador Uso:<font color="FF0000">*</font></strong></td>
					<td><html:radio property="indicadorUso" tabindex="6" value="1" /><strong>Ativo</strong>
					<html:radio property="indicadorUso" tabindex="7" value="2" /><strong>Inativo</strong>
					</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td align="left"><font color="#FF0000">*</font> Campo Obrigatório</td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="button" name="ButtonCancelar" class="bottonRightCol" value="Voltar"
						onClick="window.history.go(-1)">
						<input type="button" name="ButtonReset" class="bottonRightCol"
						value="Desfazer"
						onclick="window.location.href='/gsan/exibirAtualizarUnidadeNegocioAction.do?desfazer=S&reloadPage=1&id=<bean:write name="AtualizarUnidadeNegocioActionForm" property="id" />';">
						<input name="Button"
						type="button" class="bottonRightCol" value="Cancelar" align="left"
						onclick="window.location.href='/gsan/telaPrincipal.do'"></td>
					<td width="53" height="24" align="right"><input type="button"
						name="Button2" class="bottonRightCol" value="Atualizar"
						onClick="javascript:validarForm(document.forms[0])" /></td>
					<td width="12">&nbsp;</td>
				</tr>
			</table>
			<p>&nbsp;</p>
		</tr>
	</table>
	<tr>
		<td colspan="3"><%@ include file="/jsp/util/rodape.jsp"%>
	</tr>
</html:form>
</body>
</html:html>
