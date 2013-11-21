<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<%@page import="gcom.cadastro.cliente.ClienteImovel" isELIgnored="false"%>
<%@page import="gcom.cadastro.cliente.ClienteImovelEconomia" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>

<head>

<title>GSAN - Sistema Integrado de Gest&atilde;o de Servi&ccedil;os de Saneamento 2</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false"
	formName="AtualizarDadosTarifaSocialClienteActionForm"
	dynamicJavascript="true" />

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>

<SCRIPT LANGUAGE="JavaScript">
<!--

function adicionarCliente(){

	var form = document.forms[0];
	
	if (validateAtualizarDadosTarifaSocialClienteActionForm(form)) {
		document.forms[0].action = 'exibirAtualizarDadosTarifaSocialClienteAction.do?adicionar=sim';
		document.forms[0].submit();
	}
	
}

function limparCliente(){
	document.forms[0].idCliente.value = '';
	document.forms[0].nomeCliente.value = '';
}


function voltar() {

	var form = document.forms[0];

	form.action = 'atualizarDadosTarifaSocialClienteAction.do';
	submeterFormPadrao(form);
}

function remover() {

	if (!CheckboxNaoVazio(document.forms[0].idRegistrosRemocao)) {
		//alert('Selecione, pelo menos, um cliente para remoção.')
		return;
	}
	
	if (document.forms[0].clienteImovelFimRelacaoMotivo.value == '') {
		document.forms[0].action = 'exibirRemoverClienteImovelTarifaSocialAction.do';
		document.forms[0].submit();
	}

//	if (document.forms[0].clienteImovelFimRelacaoMotivo.value == '') {
//		abrirPopupDeNome('AtualizarDadosTarifaSocialClienteAction.do?acao=mostraMotivo', 220, 410, 'motivo','yes');
//		return;
//	}
//	document.forms[0].acao.value = 'removerCliente';
//	document.forms[0].submit();
}


function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
    var form = document.forms[0];

    if (tipoConsulta == 'cliente') {
      document.forms[0].idCliente.value = "";
      document.forms[0].nomeCliente.value = "";
      
      document.forms[0].idCliente.value = codigoRegistro;
      document.forms[0].nomeCliente.value = descricaoRegistro;
    }
}

function fechar() {
	window.close();
}

function concluir() {
	var form = document.forms[0];

	form.action = 'atualizarDadosTarifaSocialClienteAction.do?concluir=sim';
	submeterFormPadrao(form);
}

</SCRIPT>
</head>

<body leftmargin="0" topmargin="0" onload="resizePageSemLink(700, 650);">

<html:form action="/atualizarDadosTarifaSocialClienteAction"
	name="AtualizarDadosTarifaSocialClienteActionForm"
	type="gcom.gui.cadastro.tarifasocial.AtualizarDadosTarifaSocialClienteActionForm"
	method="post">

	<%-- <jsp:include page="/jsp/util/wizard/navegacao_abas_wizard.jsp?numeroPagina=2"/> --%>

	<html:hidden property="acao" />
	<html:hidden property="clienteImovelFimRelacaoMotivo" value="" />
	<html:hidden property="dataFimRelacao" value="" />
	<html:hidden property="idImovelEconomia" />

	<table width="640" border="0" cellpadding="0" cellspacing="5">
		<tr>
			<td width="640" valign="top" class="centercoltext">
			<table height="100%">
				<tr>
					<td></td>
				</tr>
			</table>


			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img src="imagens/parahead_left.gif"
						editor="Webstyle4"
						moduleid="Album Photos (Project)\toptab_page2_parahead_left.xws"
						border="0" /></td>
					<td class="parabg">Atualizar Dados da Tarifa Social</td>
					<td width="11"><img src="imagens/parahead_right.gif"
						editor="Webstyle4"
						moduleid="Album Photos (Project)\toptab_page2_parahead_right.xws"
						border="0" /></td>
				</tr>
			</table>
			<table width="100%" border="0">



				<tr>
					<td colspan="2">&nbsp;</td>
				</tr>
				<tr>
					<td colspan="2">Para inserir o cliente, informe os dados abaixo:</td>
				</tr>
				<tr>
					<td width="20%"><strong>C&oacute;digo:<font color="#FF0000">*</font></strong></td>
					<td width="80%"><html:text property="idCliente" maxlength="10"
						size="11"
						onkeypress="javascript:validaEnter(event, 'exibirAtualizarDadosTarifaSocialClienteAction.do', 'idCliente');" />
					<a
						href="javascript:abrirPopupDeNome('exibirPesquisarClienteAction.do', 400, 800, 'Cliente','yes');">
					<img width="23" height="21"
						src="<bean:message key="caminho.imagens"/>pesquisa.gif" border="0" />
					</a> <logic:present name="codigoClienteNaoEncontrada"
						scope="request">
						<input type="text" maxlength="45" name="nomeCliente" size="45"
							readonly="true"
							style="background-color:#EFEFEF; border:0; color: #ff0000"
							value="<bean:message key="atencao.cliente.inexistente"/>" />
					</logic:present> <logic:notPresent
						name="codigoClienteNaoEncontrada" scope="request">
						<html:text property="nomeCliente" maxlength="45" size="45"
							style="background-color:#EFEFEF; border:0; font-color: #000000" />
					</logic:notPresent> <a href="javascript:limparCliente();"> <img
						src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar" /></a></td>
				</tr>
				<tr>
					<td><strong>Tipo:<font color="#FF0000">*</font></strong></td>
					<td><html:select property="clienteRelacaoTipo">
						<html:option value="-1">&nbsp;</html:option>
						<html:options collection="colecaoTipoRelacao"
							labelProperty="descricao" property="id" />
					</html:select></td>
				</tr>
				<tr>
					<td><strong>Data Início Relação:<font color="#FF0000">*</font></strong></td>
					<td><html:text onkeyup="mascaraData(this, event);"
						property="dataInicioRelacao" maxlength="10" size="11" /> <a
						href="javascript:abrirCalendario('AtualizarDadosTarifaSocialClienteActionForm', 'dataInicioRelacao')">
					<img border="0"
						src="<bean:message key='caminho.imagens'/>calendario.gif"
						width="20" border="0" align="middle" alt="Exibir Calendário" /> </a>
					</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td><strong><font color="#FF0000">*</font></strong> Campos
					obrigat&oacute;rios</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td>
					<div align="right"><input name="Button3" type="button"
						class="bottonRightCol" value="Adicionar"
						onClick="javascript:adicionarCliente();" /></div>
					</td>
				</tr>
			</table>
			<p>&nbsp;
			<table width="100%" bgcolor="#99ccff" width="100%">
				<tr bgcolor="#99ccff">
					<td align="center" width="49"><strong>Remover</strong></td>
					<td align="center" width="49"><strong>Código</strong></td>
					<td align="center" width="49"><strong>Nome</strong></td>
					<td align="center" width="49"><strong>Tipo</strong></td>
					<td align="center" width="49"><strong>Documento</strong></td>
				</tr>
				<%int cont = 0;%>
				<logic:present name="colecaoClienteImovel" scope="session">
					<logic:iterate indexId="i" name="colecaoClienteImovel"
						type="ClienteImovel" id="clienteImovel">
						<bean:define id="cliente" name="clienteImovel" property="cliente" />
						<bean:define id="clienteRelacaoTipo" name="clienteImovel"
							property="clienteRelacaoTipo" />
						<%cont = cont + 1;%>
						<%if (cont % 2 == 0) {%>
						<tr bgcolor="#cbe5fe">
							<%} else {%>
						<tr bgcolor="#FFFFFF">
							<%}%>
							<td>
							<div align="center"><input type="checkbox"
								name="posicaoParaRemover" value="<bean:write name="i"/>" /></div>
							</td>
							<td align="center">
							<div><bean:write name="cliente" property="id" />&nbsp;</div>
							</td>
							<td>
							<div><bean:write name="cliente" property="nome" />&nbsp;</div>
							</td>
							<td><bean:write name="clienteRelacaoTipo" property="descricao" />&nbsp;</td>
							<td><logic:present name="cliente" property="cpf">
								<bean:define name="cliente" property="cpf" id="cpf" />
          		CPF - <%=gcom.util.Util.formatarCPFApresentacao((cpf + ""))%>
							</logic:present> <logic:notPresent name="cliente" property="cpf">
								<logic:present name="cliente" property="rg">
									<bean:define name="cliente" property="unidadeFederacao.sigla"
										id="unidadeFederacao" />
									<bean:define name="cliente"
										property="orgaoExpedidorRg.descricaoAbreviada"
										id="orgaoExpedidorRg" />
									<bean:define name="cliente" property="rg" id="rg" />
	          		RG - <%=gcom.util.Util.formatarRGApresentacao((rg + ""),
							(orgaoExpedidorRg + ""), (unidadeFederacao + ""))%>
								</logic:present>
								<logic:notPresent name="cliente" property="rg">
									<logic:present name="cliente" property="cnpj">
										<bean:define name="cliente" property="cnpj" id="cnpj" />
								CNPJ - <%=gcom.util.Util.formatarCPFApresentacao((cnpj + ""))%>
									</logic:present>
								</logic:notPresent>
							</logic:notPresent></td>
						</tr>
					</logic:iterate>
				</logic:present>
				<logic:present name="colecaoClienteImovelEconomia" scope="session">
					<logic:iterate indexId="i" name="colecaoClienteImovelEconomia"
						type="ClienteImovelEconomia" id="clienteImovelEconomia">
						<bean:define id="cliente" name="clienteImovelEconomia" property="cliente" />
						<bean:define id="clienteRelacaoTipo" name="clienteImovelEconomia"
							property="clienteRelacaoTipo" />
						<%cont = cont + 1;%>
						<%if (cont % 2 == 0) {%>
						<tr bgcolor="#cbe5fe">
							<%} else {%>
						<tr bgcolor="#FFFFFF">
							<%}%>
							<td>
							<div align="center"><input type="checkbox"
								name="posicaoParaRemover" value="<bean:write name="i"/>" /></div>
							</td>
							<td align="center">
							<div><bean:write name="cliente" property="id" />&nbsp;</div>
							</td>
							<td>
							<div><bean:write name="cliente" property="nome" />&nbsp;</div>
							</td>
							<td><bean:write name="clienteRelacaoTipo" property="descricao" />&nbsp;</td>
							<td><logic:present name="cliente" property="cpf">
								<bean:define name="cliente" property="cpf" id="cpf" />
          		CPF - <%=gcom.util.Util.formatarCPFApresentacao((cpf + ""))%>
							</logic:present> <logic:notPresent name="cliente" property="cpf">
								<logic:present name="cliente" property="rg">
									<bean:define name="cliente" property="unidadeFederacao.sigla"
										id="unidadeFederacao" />
									<bean:define name="cliente"
										property="orgaoExpedidorRg.descricaoAbreviada"
										id="orgaoExpedidorRg" />
									<bean:define name="cliente" property="rg" id="rg" />
	          		RG - <%=gcom.util.Util.formatarRGApresentacao((rg + ""),
							(orgaoExpedidorRg + ""), (unidadeFederacao + ""))%>
								</logic:present>
								<logic:notPresent name="cliente" property="rg">
									<logic:present name="cliente" property="cnpj">
										<bean:define name="cliente" property="cnpj" id="cnpj" />
								CNPJ - <%=gcom.util.Util.formatarCPFApresentacao((cnpj + ""))%>
									</logic:present>
								</logic:notPresent>
							</logic:notPresent></td>
						</tr>
					</logic:iterate>
				</logic:present>
			</table>
			<p><input name="Button323" type="button" class="bottonRightCol"
				value="Remover" onClick="javascript:remover();" /></p>
			<table width="100%" border="0">
				<tr>
					<td>&nbsp;</td>
					<td>
					<div align="right"><input name="Button3222" type="button"
						class="bottonRightCol" value="Voltar"
						onClick="javascript:voltar();" /> <input name="Button322"
						type="button" class="bottonRightCol" value="Concluir"
						onClick="javascript:concluir();" /> <input name="Button3"
						type="button" class="bottonRightCol" value="Fechar"
						onClick="javascript:fechar();" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
</html:form>
</body>
</html:html>

