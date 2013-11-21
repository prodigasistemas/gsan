<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>

<title>GSAN - Sistema Integrado de Gest&atilde;o de Servi&ccedil;os de Saneamento</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">

<%@ page import="gcom.util.ConstantesSistema"%>

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false" 
	formName="GerarRelatorioRegistroAtendimentoPorUnidadePorUsuarioActionForm" staticJavascript="false" dynamicJavascript="true"/>

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>

<script language="JavaScript">

	function reload() {
		var form = document.forms[0];
		
		if (form.solicitacaoTipo.selectedIndex == 0) {
			limparEspecificacao();
		}
		
		form.action = '/gsan/exibirGerarRelatorioRegistroAtendimentoPorUnidadePorUsuarioAction.do?onchange=true';
		form.submit();
	}

	function validarForm() {
		var form = document.forms[0];

		toggleBox('demodiv', 1);
		
		//if(validateGerarRelatorioResumoSolicitacoesRAPorUnidadeActionForm(form)){
			//if (validacaoForm()) {
				//
			//}
		//}
	}
	
	function validacaoForm() {
		var form = document.forms[0];
		
		var dataAtendimentoInicial = trim(form.dataAtendimentoInicial.value);
		var dataAtendimentoFinal = trim(form.dataAtendimentoFinal.value);
		
		if (dataAtendimentoInicial == null || dataAtendimentoInicial == '') {
    		alert('Informe Data Inicial do Período de Atendimento.');
    		return false;
    	}
		if (dataAtendimentoFinal == null || dataAtendimentoFinal == '') {
    		alert('Informe Data Final do Período de Atendimento');
       		return false;
    	}
    	
    	var unidade = trim(form.idUnidadeAtendimento.value);
    	
    	if (unidade == null || unidade == '') {
    	    alert('Informe Unidade de Atendimento.');
    	    return false;
    	}
    	
    	return true;
	}

	function chamarPopup(url, tipo, objeto, codigoObjeto, altura, largura, msg,objetoRelacionado){
		if(objetoRelacionado.disabled != true){
			if (objeto == null || codigoObjeto == null){
				abrirPopup(url + "?" + "tipo=" + tipo, altura, largura);
			} else{
				if (codigoObjeto.length < 1 || isNaN(codigoObjeto)){
					alert(msg);
				} else{
					abrirPopup(url + "?" + "tipo=" + tipo + "&" + objeto + "=" + codigoObjeto, altura, largura);
				}
			}
		}
	}
	
	function recuperarDadosPopup(codigoRegistro, nomeRegistro, tipoConsulta) {
		var form = document.forms[0];
		
		if (tipoConsulta == 'unidadeOrganizacional') {
			form.nomeUnidadeAtendimento.style.color = "#000000";
			form.idUnidadeAtendimento.value = codigoRegistro;
			form.nomeUnidadeAtendimento.value = nomeRegistro;
			
		}
	
	}
	
	
	
	function replicaDataAtendimento() {
		var form = document.forms[0];
		form.dataAtendimentoFinal.value = form.dataAtendimentoInicial.value;
	}
	
	
	function limparUnidadeAtendimento() {
		var form = document.forms[0];
		
		form.idUnidadeAtendimento.value = "";
		form.nomeUnidadeAtendimento.value = "";
	}
	
	
</script>

</head>

<body leftmargin="5" topmargin="5"
	onload="window.focus();javascript:setarFoco('${requestScope.nomeCampo}');" >

<html:form 
	action="/gerarRelatorioRegistroAtendimentoPorUnidadePorUsuarioAction" method="post"
	name="GerarRelatorioRegistroAtendimentoPorUnidadePorUsuarioActionForm"
	type="gcom.gui.atendimentopublico.registroatendimento.GerarRelatorioRegistroAtendimentoPorUnidadePorUsuarioActionForm">

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
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_left.gif" /></td>
					<td class="parabg">Filtrar Relatório Registros de Atendimento Por Unidade Por Usuário</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<table width="100%" border="0">

				<tr>
					<td colspan="2">Para selecionar registros de atendimento para geração do relatório, informe os dados abaixo:</td>
				</tr>
				
				<tr>
					<td><strong>Situação dos Registros de Atendimento<font color="#FF0000">*</font></strong>
					</td>
					<td style="width: 440px;">
					<html:radio property="situacaoRA" value="" /> <strong>Todos</strong>
					<html:radio property="situacaoRA" value="1" /> <strong>Pendentes</strong>
					<html:radio property="situacaoRA" value="2" /> <strong>Encerrados</strong>
					<html:radio property="situacaoRA" value="3" /> <strong>Sem Local de Ocorrência</strong>
					</td>
				</tr>
				
				<tr>
					<td height="10" colspan="3">
					<div align="right">
					<hr>
					</div>
					</td>
				</tr>

				<tr>
					<td><strong>Período de Atendimento:<font color="#FF0000">*</font></strong></td>

					<td colspan="6"><span class="style2"> <strong> <html:text
						property="dataAtendimentoInicial" size="11" maxlength="10"
						tabindex="3"
						onkeyup="mascaraData(this, event);replicaDataAtendimento();" /> <a
						href="javascript:abrirCalendarioReplicando('GerarRelatorioResumoSolicitacoesRAPorUnidadeActionForm', 'dataAtendimentoInicial','dataAtendimentoFinal');">
					<img border="0"
						src="<bean:message key='caminho.imagens'/>calendario.gif"
						width="16" height="15" border="0" alt="Exibir Calendário"
						tabindex="4" /></a> a <html:text
						property="dataAtendimentoFinal" size="11" maxlength="10"
						tabindex="3" onkeyup="mascaraData(this, event)" /> <a
						href="javascript:abrirCalendario('GerarRelatorioResumoSolicitacoesRAPorUnidadeActionForm', 'dataAtendimentoFinal');">
					<img border="0"
						src="<bean:message key='caminho.imagens'/>calendario.gif"
						width="16" height="15" border="0" alt="Exibir Calendário"
						tabindex="4" /></a> </strong>(dd/mm/aaaa)<strong> </strong> </span>
					</td>
				</tr>
				
				<tr>
					<td height="10" colspan="3">
					<div align="right">
					<hr>
					</div>
					</td>
				</tr>
				
				<tr>
					<td><strong>Unidade de Atendimento:</strong></td>
					<td>
						<html:text property="idUnidadeAtendimento" maxlength="4" size="4"
							onkeypress="limparUnidadeSuperior(); validaEnterComMensagem(event, 'exibirGerarRelatorioRegistroAtendimentoPorUnidadePorUsuarioAction.do?objetoConsulta=1','idUnidadeAtendimento','Unidade Organizacional');" />
						
						<a href="javascript: chamarPopup('exibirPesquisarUnidadeOrganizacionalAction.do', 'idUnidadeAtendimento', null, null, 275, 480, '', document.forms[0].idUnidadeAtendimento);">
						<img width="23" height="21" border="0" src="<bean:message key="caminho.imagens"/>pesquisa.gif" title="Pesquisar Unidade Organizacional" /></a> 
						
						<logic:present name="unidadeEncontrada" scope="session">
							<html:text property="nomeUnidadeAtendimento" size="40" maxlength="40" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:present>
						
						<logic:notPresent name="unidadeEncontrada" scope="session">
							<html:text property="nomeUnidadeAtendimento" size="40" maxlength="40" readonly="true" style="background-color:#EFEFEF; border:0; color: red" />
						</logic:notPresent>
						
						<a href="javascript:limparUnidade();">
							<img src="<bean:message key="caminho.imagens"/>limparcampo.gif" border="0" title="Apagar" /> 
						</a> 
					</td>
				</tr>
				
				<tr>
					<td height="10" colspan="3">
					<div align="right">
					<hr>
					</div>
					</td>
				</tr>
				
				<tr>
					<td>&nbsp;</td>
					<td align="right">
					<div align="left"><strong><font color="#FF0000">*</font></strong>
					Campos obrigat&oacute;rios</div>
					</td>
				</tr>
				<tr>
					<td height="24"><input type="button" class="bottonRightCol"
						value="Limpar"
						onclick="window.location.href='/gsan/exibirGerarRelatorioRegistroAtendimentoPorUnidadePorUsuarioAction.do?menu=sim';" />
					</td>

					<td align="right"><input type="button" name="Button"
						class="bottonRightCol" value="Filtrar"
						onClick="javascript:validarForm()" /></td>

				</tr>
			</table>
			<p>&nbsp;</p>
			</td>
		</tr>
	</table>
	<jsp:include
		page="/jsp/relatorio/escolher_tipo_relatorio.jsp?relatorio=gerarRelatorioRegistroAtendimentoPorUnidadePorUsuarioAction.do" />
	<%@ include file="/jsp/util/rodape.jsp"%>
</html:form>
</body>
</html:html>
