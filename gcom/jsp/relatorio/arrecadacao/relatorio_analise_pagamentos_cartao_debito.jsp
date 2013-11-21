<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan"%>

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
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<script language="JavaScript">

function limparUsuarioConfirmacao() {
	var form = document.forms[0];

	form.idUsuarioConfirmacao.value = "";
	form.descricaoUsuarioConfirmacao.value = "";	
}

function replicaDados(campoOrigem, campoDestino){
	campoDestino.value = campoOrigem.value;
}

function replicaDataConfirmacao() {

	  var form = document.forms[0];
	  form.dataConfirmacaoPagamentoFinal.value = form.dataConfirmacaoPagamentoInicial.value;
	  
}

function replicaDataConfirmacaoOperadora() {

	  var form = document.forms[0];
	  form.dataConfirmacaoOperadoraFinal.value = form.dataConfirmacaoOperadoraInicial.value;
}

function chamarPopup(url, tipo, objeto, codigoObjeto, altura, largura, msg, fieldName){

	var campo = document.forms['RelatorioAnalisePagamentoCartaoDebitoForm'].elements[fieldName]
		                                      		
	if(campo.disabled == false) {
	
		if (objeto == null || codigoObjeto == null){
			abrirPopup(url + "?" + "tipo=" + tipo, altura, largura);
		} else {
			if (codigoObjeto.length < 1 || isNaN(codigoObjeto)){
				alert(msg);
			} else {
				abrirPopup(url + "?" + "tipo=" + tipo + "&" + objeto + "=" + codigoObjeto + "&caminhoRetornoTelaPesquisa=" + tipo, altura, largura);
			}
		}
	
	}
}

function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {

	var form = document.forms[0];    

	if (tipoConsulta == 'usuario') {
		
		  form.idUsuarioConfirmacao.value = codigoRegistro;
		  form.descricaoUsuarioConfirmacao.value = descricaoRegistro;
		  form.descricaoUsuarioConfirmacao.style.color = "#000000";

    } 	
}

function limparCampos(){

  var form = document.forms[0];

  form.dataConfirmacaoPagamentoInicial.value = "";
  form.dataConfirmacaoPagamentoFinal.value = "";
  form.idUsuarioConfirmacao.value = "";
  form.descricaoUsuarioConfirmacao.value = "";
  form.dataConfirmacaoOperadoraInicial.value = "";
  form.dataConfirmacaoOperadoraFinal.value = "";

}  

function abrirCalendarioVerificandoBloqueio(formName, fieldName) {
	
	var campo = document.forms[formName].elements[fieldName]
	                                      		
	if(campo.disabled == false) {
	
	    nomeForm = formName;
	    nomeCampo = fieldName;
		centerpopup('./jsp/util/calendario.jsp','calendario',225,268);

	}

}

function abrirPopupDependenciaVerificandoBloqueio(url, idDependencia, nomeMSG, altura, largura, fieldName){

	var campo = document.forms['RelatorioAnalisePagamentoCartaoDebitoForm'].elements[fieldName]
		                                      		
	if(campo.disabled == false) {
	
		if (idDependencia.length < 1 || isNaN(idDependencia) || ((idDependencia * 1) == 0)){
			alert("Informe " + nomeMSG);
		}
		else{
			abrirPopup(url , altura, largura);
		}
	}
}

function validarGeracaoRelatorio(){

	var form = document.forms[0];
	
	if(validateRelatorioAnalisePagamentoCartaoDebitoForm(form)){
		javascript:botaoAvancarTelaEspera('/gsan/gerarRelatorioAnalisePagamentoCartaoDebitoAction.do');
	}
}

</script>	
</head>
<html:javascript staticJavascript="false" formName="RelatorioAnalisePagamentoCartaoDebitoForm" />
<body leftmargin="5" topmargin="5">
<div id="formDiv">
	<html:form action="/gerarRelatorioAnalisePagamentoCartaoDebitoAction.do"
		name="RelatorioAnalisePagamentoCartaoDebitoForm"
		type="gcom.gui.relatorio.arrecadacao.RelatorioAnalisePagamentoCartaoDebitoForm"
		method="post">
		
		<%@ include file="/jsp/util/cabecalho.jsp"%>
		<%@ include file="/jsp/util/menu.jsp" %>
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
		
		         <%@ include file="/jsp/util/mensagens.jsp"%>
		
		        <p align="left">&nbsp;</p>
		        <p align="left">&nbsp;</p>
		        <p align="left">&nbsp;</p>
		        <p align="left">&nbsp;</p>
		        <p align="left">&nbsp;</p>
		        <p align="left">&nbsp;</p>
		        <p align="left">&nbsp;</p>
		      </div></td>
		    <td width="625" valign="top" class="centercoltext">
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
					<td class="parabg">Filtrar Relatório Análise Pagamento Cartão Débito</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
	
			<!--Fim Tabela Reference a Páginação da Tela de Processo-->
	
			<p>&nbsp;</p>
	
			<table width="100%" border="0">
				<tr>
					<td colspan="4">Para filtrar, informe
					os dados abaixo:</td>
				</tr>
				
				<tr>
					<td width="25%"><strong>Período de Confirmação Pagamento:</strong>
						<strong><font color="#FF0000">*</font></strong>
					</td>
					<td><strong> <html:text property="dataConfirmacaoPagamentoInicial"
						size="10" maxlength="10" onkeypress="return isCampoNumerico(event);"
						onkeyup="mascaraData(this,event);replicaDataConfirmacao();" />
					<a
						href="javascript:abrirCalendarioVerificandoBloqueio('RelatorioAnalisePagamentoCartaoDebitoForm', 'dataConfirmacaoPagamentoInicial')">
					<img border="0"
						src="<bean:message key="caminho.imagens"/>calendario.gif"
						width="20" border="0" align="middle" alt="Exibir Calendário" /></a>
					a </strong> <html:text property="dataConfirmacaoPagamentoFinal" size="10"
						maxlength="10" onkeyup="mascaraData(this,event)" onkeypress="return isCampoNumerico(event);" /> <a
						href="javascript:abrirCalendarioVerificandoBloqueio('RelatorioAnalisePagamentoCartaoDebitoForm', 'dataConfirmacaoPagamentoFinal')">
					<img border="0"
						src="<bean:message key="caminho.imagens"/>calendario.gif"
						width="20" border="0" align="middle" alt="Exibir Calendário" /></a>
					(dd/mm/aaaa)</td>
				</tr>
				
				<tr>
					<td><strong>Usuário da Confirmação:</strong></td>
					<td><strong> <html:text property="idUsuarioConfirmacao" size="5"
						maxlength="3" onkeypress="return isCampoNumerico(event);"
						onkeyup="return validaEnterComMensagem(event, 'exibirGerarRelatorioAnalisePagamentoCartaoDebitoAction.do?pesquisarUsuarioConfirmacao=sim', 'idUsuarioConfirmacao', 'Usuário Confirmação');" />
					<a
						href="javascript:chamarPopup('exibirUsuarioPesquisar.do', 'usuarioConfirmacao', null, null, 400, 800, '','idUsuarioConfirmacao');">
					<img border="0" title="Pesquisar Usuário Responsável" src="imagens/pesquisa.gif" height="21" width="23" /></a>
	
					<logic:present name="usuarioConfirmacaoInexistente"
						scope="request">
						<html:text property="descricaoUsuarioConfirmacao"
							readonly="true"
							style="background-color:#EFEFEF; border:0; color: #ff0000"
							size="40" maxlength="40" />
					</logic:present> <logic:notPresent
						name="usuarioConfirmacaoInexistente" scope="request">
						<html:text property="descricaoUsuarioConfirmacao"
							readonly="true" style="background-color:#EFEFEF; border:0"
							size="40" maxlength="40" />
					</logic:notPresent> <a
						href="javascript:limparUsuarioConfirmacao();"> <img
						src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar" /> </a> </strong></td>
				</tr>
				
				<tr>
					<td><strong> Situação da Confirmação da Operadora:
							</strong></td>
							<td colspan="3" align="right">
							<div align="left">
								<label> 
								 	<input type="radio" 
									name="indicadorConfirmacaoOperadora" id="radio1" value="1"
									onclick="javascript:document.forms[0].indicadorConfirmacaoOperadora.value = 1;"/>
									Sim 
								</label> 
								<label> 
									<input type="radio" 
									name="indicadorConfirmacaoOperadora" id="radio2" value="2"
									onclick="javascript:document.forms[0].indicadorConfirmacaoOperadora.value = 2;"/>
									Não 
								</label>
								<label> 
									<input type="radio" 
									name="indicadorConfirmacaoOperadora" id="radio3" value="3"
									onclick="javascript:document.forms[0].indicadorConfirmacaoOperadora.value = 3;"/>
									Todos
								</label>
							</div>
					</td>		
				</tr>
				
				<tr>
					<td><strong>Período de Confirmação da Operadora:</strong></td>
					<td><strong> <html:text property="dataConfirmacaoOperadoraInicial"
						size="10" maxlength="10" onkeypress="return isCampoNumerico(event);"
						onkeyup="mascaraData(this,event);replicaDataConfirmacaoOperadora();" />
					<a
						href="javascript:abrirCalendarioVerificandoBloqueio('RelatorioAnalisePagamentoCartaoDebitoForm', 'dataConfirmacaoOperadoraInicial')">
					<img border="0"
						src="<bean:message key="caminho.imagens"/>calendario.gif"
						width="20" border="0" align="middle" alt="Exibir Calendário" /></a>
					a </strong> <html:text property="dataConfirmacaoOperadoraFinal" size="10"
						maxlength="10" onkeyup="mascaraData(this,event)" onkeypress="return isCampoNumerico(event);" /> <a
						href="javascript:abrirCalendarioVerificandoBloqueio('RelatorioAnalisePagamentoCartaoDebitoForm', 'dataConfirmacaoOperadoraFinal')">
					<img border="0"
						src="<bean:message key="caminho.imagens"/>calendario.gif"
						width="20" border="0" align="middle" alt="Exibir Calendário" /></a>
					(dd/mm/aaaa)</td>
				</tr>

				<tr>
					<td>&nbsp;</td>
					<td colspan="3" align="right">
					<div align="left"><strong> </strong></div>
					</td>
				</tr>
	
				<tr>
					<td>
					<div align="left">
					<input type="button" 
							name="limpar"
							class="bottonRightCol" value="Limpar" 
							onclick="javascript:limparCampos();">
					<input type="button" 
							name="Button" 		
							class="bottonRightCol"
							value="Cancelar" 
							align="left"
							onClick="javascript:window.location.href='/gsan/telaPrincipal.do'">
					</div>
					</td>
					<td>&nbsp;</td>
					<td>
					<div align="right">
					<input	type="button"  
							name="botaoConcluir"
							class="bottonRightCol" value="Gerar" 
							onclick="validarGeracaoRelatorio();"></div>
					</td>
				</tr>
			</table>
			<p>&nbsp;</p>
	        </td>
		  </tr>
		  <%@ include file="/jsp/util/rodape.jsp"%>
		</table>
</html:form>
</div>
<%@ include file="/jsp/util/telaespera.jsp"%>
</body>
</html:html>
