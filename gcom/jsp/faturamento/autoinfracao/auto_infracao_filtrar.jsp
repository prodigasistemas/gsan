<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<%@page import="gcom.util.ConstantesSistema"%>

<html:html>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<head>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false"
	formName="FiltrarAutoInfracaoActionForm" dynamicJavascript="true" />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript">

	function validarForm(){
			var form = document.forms[0];	

			if(validateFiltrarAutoInfracaoActionForm(form)){    		
		  			submeterFormPadrao(form);
			}
	}

	function limparForm() {
		var form = document.FiltrarAutoInfracaoActionForm;
		form.idImovel.value = "";
		form.descricaoImovel.value = "";
		form.idFuncionario.value = "";
		form.descricaoFuncionario.value = "";
		form.idFiscalizacaoSituacao.value = "-1";
		form.idAutoInfracaoSituacao.value = "-1";
		form.dataEmissaoInicial.value = "";
		form.dataEmissaoFinal.value = "";
		form.dataInicioRecursoInicial.value = "";
		form.dataInicioRecursoFinal.value = "";
		form.dataTerminoRecursoInicial.value = "";
		form.dataTerminoRecursoFinal.value = "";
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
	    if (tipoConsulta == 'imovel') {
	    	form.idImovel.value = codigoRegistro;
	      	form.action='exibirFiltrarAutoInfracaoAction.do';
	      	form.submit();
	    }
	    if (tipoConsulta == 'funcionario') {
	    	form.idFuncionario.value = codigoRegistro;
	      	form.action='exibirFiltrarAutoInfracaoAction.do';
	      	form.submit();
	    }
	}
	
		function limparImovel() {
		var form = document.forms[0];
		form.idImovel.value = "";
		form.descricaoImovel.value = "";
	
	}
	
	
	function limparFuncionario() {
		var form = document.forms[0];
		form.idFuncionario.value = "";
		form.descricaoFuncionario.value = "";
	
	}
	
	function duplicaDataEmissao(form) {
		form.dataEmissaoFinal.value = form.dataEmissaoInicial.value;
	}
	function duplicaDataInicioRecurso(form) {
		form.dataInicioRecursoFinal.value = form.dataInicioRecursoInicial.value;
	}
	function duplicaDataTerminoRecurso(form) {
		form.dataTerminoRecursoFinal.value = form.dataTerminoRecursoInicial.value;
	}
</script>

</head>

<body leftmargin="5" topmargin="5">
<html:form action="/filtrarAutoInfracaoAction"
	name="FiltrarAutoInfracaoActionForm"
	type="gcom.gui.faturamento.autoinfracao.FiltrarAutoInfracaoActionForm"
	method="post"
	onsubmit="return validateFiltrarAutoInfracaoActionForm(this);">


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
					<td class="parabg">Filtrar Autos de Infração</td>
					<td width="11" valign="top"><img border="0"
						src="imagens/parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>

					<td colspan="2">Para filtrar o(s) auto(s) de infração, informe o
					dado abaixo:</td>
					<td width="100" align="right" colspan="2"><html:checkbox
						property="atualizar" value="1" /> <strong>Atualizar</strong></td>
				</tr>

				<tr>
					<td width="120"><strong>Matrícula do Imóvel:</strong></td>
					<td colspan="2"><html:text property="idImovel" size="9"
						maxlength="9"
						onkeyup="validaEnterComMensagem(event, 'exibirFiltrarAutoInfracaoAction.do', 'idImovel');" />
					<a
						href="javascript:abrirPopup('exibirPesquisarImovelAction.do?limpaForm=S', 495, 300);"><img
						width="23" height="21"
						src="<bean:message key="caminho.imagens"/>pesquisa.gif"
						style="border:0;" alt="Pesquisar" /></a><logic:present
						name="matriculaInexistente" scope="request">
						<html:text property="descricaoImovel" size="35" maxlength="35"
							readonly="true"
							style="background-color:#EFEFEF; border:0; color: #ff0000" />
					</logic:present> <logic:notPresent name="matriculaInexistente"
						scope="request">
						<html:text property="descricaoImovel" size="35" maxlength="35"
							readonly="true"
							style="background-color:#EFEFEF; border:0; color: #000000" />
					</logic:notPresent> <a href="javascript:limparImovel();"><img
						src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar" /></a></td>
				</tr>


				<tr>
					<td width="120"><strong>Funcionário do Imóvel:</strong></td>
					<td colspan="2"><html:text property="idFuncionario" size="9"
						maxlength="9"
						onkeyup="validaEnterComMensagem(event, 'exibirFiltrarAutoInfracaoAction.do', 'idFuncionario');" />
					<a
						href="javascript:abrirPopup('exibirFuncionarioPesquisar.do?menu=sim', 495, 300);"><img
						width="23" height="21"
						src="<bean:message key="caminho.imagens"/>pesquisa.gif"
						style="border:0;" alt="Pesquisar" /></a><logic:present
						name="funcionarioInexistente" scope="request">
						<html:text property="descricaoFuncionario" size="35" maxlength="35"
							readonly="true"
							style="background-color:#EFEFEF; border:0; color: #ff0000" />
					</logic:present> <logic:notPresent name="funcionarioInexistente"
						scope="request">
						<html:text property="descricaoFuncionario" size="35" maxlength="35"
							readonly="true"
							style="background-color:#EFEFEF; border:0; color: #000000" />
					</logic:notPresent> <a href="javascript:limparFuncionario();"><img
						src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar" /></a></td>
				</tr>

				<tr>
					<td width="120"><strong>Irregularidade Constatada:</strong></td>
					<td colspan="2"><html:select property="idFiscalizacaoSituacao">
						<html:option value="-1">&nbsp;</html:option>
						<html:options collection="colecaoFiscalizacaoSituacao"
							labelProperty="descricaoFiscalizacaoSituacao" property="id" />
					</html:select> <font size="1">&nbsp; </font></td>
				</tr>

				<tr>
					<td width="120"><strong>Situação do Auto:</strong></td>
					<td colspan="2"><html:select property="idAutoInfracaoSituacao">
						<html:option value="-1">&nbsp;</html:option>
						<html:options collection="colecaoAutoInfracaoSituacao"
							labelProperty="descricao" property="id" />
					</html:select> <font size="1">&nbsp; </font></td>
				</tr>


				<tr>
					<td width="120"><strong>Data de Emissão:</strong></td>

					<td colspan="2"><strong> <html:text maxlength="10"
						property="dataEmissaoInicial" size="10"
						onkeyup="mascaraData(this, event); duplicaDataEmissao(document.forms[0]);"
						onfocus="duplicaDataEmissao(document.forms[0]);" /> <a
						href="javascript:abrirCalendarioReplicando('FiltrarAutoInfracaoActionForm', 'dataEmissaoInicial', 'dataEmissaoFinal');">
					<img border="0"
						src="<bean:message key="caminho.imagens"/>calendario.gif"
						width="20" border="0" align="absmiddle" alt="Exibir Calendário" /></a>
					a</strong> <html:text maxlength="10" property="dataEmissaoFinal"
						size="10" onkeyup="mascaraData(this, event);" /> <a
						href="javascript:abrirCalendario('FiltrarAutoInfracaoActionForm', 'dataEmissaoFinal');">
					<img border="0"
						src="<bean:message key="caminho.imagens"/>calendario.gif"
						width="20" border="0" align="absmiddle" alt="Exibir Calendário" /></a>
					dd/mm/aaaa</td>
				</tr>

				<tr>
					<td width="120"><strong>Data de Início do Recurso:</strong></td>

					<td colspan="2"><strong> <html:text maxlength="10"
						property="dataInicioRecursoInicial" size="10"
						onkeyup="mascaraData(this, event); duplicaDataInicioRecurso(document.forms[0]);"
						onfocus="duplicaDataInicioRecurso(document.forms[0]);" /> <a
						href="javascript:abrirCalendarioReplicando('FiltrarAutoInfracaoActionForm', 'dataInicioRecursoInicial', 'dataInicioRecursoFinal');">
					<img border="0"
						src="<bean:message key="caminho.imagens"/>calendario.gif"
						width="20" border="0" align="absmiddle" alt="Exibir Calendário" /></a>
					a</strong> <html:text maxlength="10"
						property="dataInicioRecursoFinal" size="10"
						onkeyup="mascaraData(this, event);" /> <a
						href="javascript:abrirCalendario('FiltrarAutoInfracaoActionForm', 'dataInicioRecursoFinal');">
					<img border="0"
						src="<bean:message key="caminho.imagens"/>calendario.gif"
						width="20" border="0" align="absmiddle" alt="Exibir Calendário" /></a>
					dd/mm/aaaa</td>
				</tr>

				<tr>
					<td width="120"><strong>Data de Término do Recurso:</strong></td>

					<td colspan="2"><strong> <html:text maxlength="10"
						property="dataTerminoRecursoInicial" size="10"
						onkeyup="mascaraData(this, event); duplicaDataTerminoRecurso(document.forms[0]);"
						onfocus="duplicaDataTerminoRecurso(document.forms[0]);" /> <a
						href="javascript:abrirCalendarioReplicando('FiltrarAutoInfracaoActionForm', 'dataTerminoRecursoInicial', 'dataTerminoRecursoFinal');">
					<img border="0"
						src="<bean:message key="caminho.imagens"/>calendario.gif"
						width="20" border="0" align="absmiddle" alt="Exibir Calendário" /></a>
					a</strong> <html:text maxlength="10"
						property="dataTerminoRecursoFinal" size="10"
						onkeyup="mascaraData(this, event);" /> <a
						href="javascript:abrirCalendario('FiltrarAutoInfracaoActionForm', 'dataTerminoRecursoFinal');">
					<img border="0"
						src="<bean:message key="caminho.imagens"/>calendario.gif"
						width="20" border="0" align="absmiddle" alt="Exibir Calendário" /></a>
					dd/mm/aaaa</td>
				</tr>



				<tr>
					<td><input name="Button" type="button" class="bottonRightCol"
						value="Limpar" align="left"
						onclick="window.location.href='/gsan/exibirFiltrarAutoInfracaoAction.do?menu=sim'"
						tabindex="8"></td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td width="65" align="right"><input name="Button2" type="button"
						class="bottonRightCol" value="Filtrar" align="right"
						onClick="javascript:validarForm(document.forms[0]);" tabindex="9" /></td>
				</tr>

			</table>
			</td>
		</tr>
	</table>

	<%@ include file="/jsp/util/rodape.jsp"%>
</html:form>
</body>
</html:html>
