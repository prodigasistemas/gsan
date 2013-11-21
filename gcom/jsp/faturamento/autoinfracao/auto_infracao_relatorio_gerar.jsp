<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ include file="/jsp/util/telaespera.jsp"%>

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
	formName="GerarRelatorioAutoInfracaoActionForm" />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>

<script language="JavaScript">
<!-- Begin
	
	function replicaDados(campoOrigem, campoDestino){
		campoDestino.value = campoOrigem.value;
	}
	
	function limparFuncionario() {
		var form = document.forms[0];
		form.idFuncionario.value = "";
		form.nomeFuncionario.value = "";	
	}
	
	function limparFuncionarioTecla() {
		var form = document.forms[0];
		form.nomeFuncionario.value = "";
	}
	
	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {

    	var form = document.forms[0];
   
	    if ('funcionario' == tipoConsulta) {
		 	form.idFuncionario.value = codigoRegistro;
		 	form.nomeFuncionario.value = descricaoRegistro;
		 	form.nomeFuncionario.style.color = "#000000";
	 	}
    
    }
    
    function gerar() {
    	var form = document.forms[0];
    	
    	if(validateGerarRelatorioAutoInfracaoActionForm(form)) {
    		if (form.dataPagamentoInicio.value != "" && form.dataPagamentoFim.value == "") {
    			alert("Informe Data de Pagamento Final");
    		} else if (form.dataPagamentoFim.value != "" && form.dataPagamentoInicio.value == "") {
    			alert("Informe Data de Pagamento Inicial");    		
	    	} else {
	    		//submeterFormPadrao(form);
	    		botaoAvancarTelaEspera('/gsan/gerarRelatorioAutoInfracaoAction.do');
	    	}
    	}
    }

-->
</script>

</head>

<body leftmargin="5" topmargin="5"
	onload="javascript:setarFoco('${requestScope.nomeCampo}');">

<div id="formDiv"><html:form action="/gerarRelatorioAutoInfracaoAction"
	name="GerarRelatorioAutoInfracaoActionForm"
	type="gcom.gui.faturamento.autoinfracao.GerarRelatorioAutoInfracaoActionForm"
	method="post">

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
			<td width="600" valign="top" class="centercoltext">
			<table height="100%">
				<tr>
					<td></td>
				</tr>
			</table>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img border="0" src="imagens/parahead_left.gif" /></td>
					<td class="parabg">Gerar Relatório de Arrecadação de Serviços de
					Multas de Autos de Infração</td>
					<td width="11" valign="top"><img border="0"
						src="imagens/parahead_right.gif" /></td>
				</tr>
			</table>
			<!--Fim Tabela Reference a Páginação da Tela de Processo-->
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>
					<td colspan="2">Para gerar o relatório de multas de autos de
					infração, informe os dados abaixo:</td>
				</tr>
				<tr>
					<td width="20%"><strong>Unidade Negócio:</strong></td>
					<td><html:select property="idUnidadeNegocio" tabindex="1">
						<logic:notEmpty name="colecaoUnidadeNegocio">
							<option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</option>
							<html:options collection="colecaoUnidadeNegocio"
								labelProperty="nome" property="id" />
						</logic:notEmpty>
					</html:select></td>
				</tr>
				<tr>
					<td width="20%"><strong>Funcionário:</strong></td>
					<td><html:text maxlength="9" property="idFuncionario" size="9"
						onkeypress="javascript:return validaEnter(event, 'exibirGerarRelatorioAutoInfracaoAction.do', 'idFuncionario');"
						onkeyup="limparFuncionarioTecla();" /> <a
						href="javascript:chamarPopup('exibirFuncionarioPesquisar.do?limpaForm=S', 495, 300,document.forms[0].idFuncionario);"><img
						src="<bean:message key='caminho.imagens'/>pesquisa.gif" width="23"
						height="21" alt="Pesquisar" border="0"></a> 
						<logic:present name="funcionarioInexistente" scope="request">
						<html:text property="nomeFuncionario" size="40" readonly="true"
							style="background-color:#EFEFEF; border:0; color: #ff0000" />
					</logic:present> <logic:notPresent name="funcionarioInexistente" scope="request">
						<html:text property="nomeFuncionario" size="40" maxlength="40"
							readonly="true"
							style="border: 0pt none ; background-color: rgb(239, 239, 239);" />
					</logic:notPresent> <a href="javascript:limparFuncionario();"> <img
						border="0" src="imagens/limparcampo.gif" height="21" width="23"> </a></td>
				</tr>
				<tr>
					<td><strong>Período da Arrecadação:<font color="#FF0000">*</font></strong></td>
					<td><strong> <html:text maxlength="7"
						property="dataPagamentoInicio" size="7"
						onkeyup="mascaraAnoMes(this, event);  replicaDados(document.forms[0].dataPagamentoInicio, document.forms[0].dataPagamentoFim);" />

					a</strong> <html:text maxlength="7" property="dataPagamentoFim"
						size="7" onkeyup="mascaraAnoMes(this, event);" /> 
					(mm/aaaa)
				</td>
				</tr>
				<tr>
					<td><strong> <font color="#FF0000"></font></strong></td>
					<td align="right">
					<div align="left" colspan="2"><strong><font color="#FF0000">*</font></strong>

					Campos obrigat&oacute;rios</div>
					</td>
				</tr>
				
				<tr>
					<td colspan="2">
					<table width="100%">
						<tr>
							<td width="60%"><input type="button"
								name="ButtonReset" class="bottonRightCol" value="Limpar"
								onClick="javascript:window.location.href='/gsan/exibirGerarRelatorioAutoInfracaoAction.do?menu=sim'">
							<input type="button" name="ButtonCancelar" class="bottonRightCol"
								value="Cancelar"
								onClick="javascript:window.location.href='/gsan/telaPrincipal.do'">
							</td>
							<td align="right"><input type="button" name="Button"
								value="Gerar" onclick="javascript:gerar();"
								class="bottonRightCol" /></td>
						</tr>
					</table>
					</td>
				</tr>

			</table>
			</td>
		</tr>
	</table>

	<%@ include file="/jsp/util/rodape.jsp"%>

</html:form></div>
</body>
</html:html>
