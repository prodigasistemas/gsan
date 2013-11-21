<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>

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
<html:javascript staticJavascript="false" formName="ClienteActionForm"
	dynamicJavascript="false" />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script>
<!--
var bCancel = false;

//******************************************************
// Autor: Ivan Sergio
// Data: 20/07/2009
// CRC2103
// Verifica se o Cliente foi inserido e retorna os dados
// para o action de imovel.
//******************************************************
if (<%=request.getParameter("concluir")%> != null) {
	if (<%=request.getParameter("concluir")%> == true) {
		opener.recuperarDadosAtualizarClientePopUp(
			'<%=session.getAttribute("codigoCliente")%>',
			'<%=session.getAttribute("nomeCliente")%>',
			'<%=session.getAttribute("cpfCnpjCliente")%>');
		window.close();
	}
}
//******************************************************

function validateClienteActionForm(form) {
	if (bCancel)
		return true;
	else
	    return validateCaracterEspecial(form) 
	    && validateRequired(form) 
	    && validateEmail(form)
	    && validateLong(form)
	    && validarDiaVencimento()
	    && validarVencimentoMesSeguinte()
	    && validarNomeExibidoConta();
}

function caracteresespeciais () {
	this.aa = new Array("nome", "Nome possui caracteres especiais.", new Function ("varName", " return this[varName];"));
	this.ab = new Array("nomeAbreviado", "Nome Abreviado possui caracteres especiais.", new Function ("varName", " return this[varName];"));
}

function required () {
	this.aa = new Array("nome", "Informe Nome.", new Function ("varName", " return this[varName];"));
	this.ab = new Array("tipoPessoa", "Informe Tipo do Cliente.", new Function ("varName", " return this[varName];"));
}

function email () {
	this.aa = new Array("email", "E-Mail inválido.", new Function ("varName", " return this[varName];"));
}

function IntegerValidations () {
	this.aa = new Array("diaVencimento", "Dia do Vencimento deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
}

function validarDiaVencimento(){
var form = document.forms[0];

	if( form.diaVencimento.value != null &&  form.diaVencimento.value != ''&&
		 (form.diaVencimento.value < 1 || form.diaVencimento.value > 31)){	
			alert('Dia do vencimento de ser entre 01 e 31.');
			form.diaVencimento.focus();
			return false;
	}else{
		return true;
	}
}

function validarVencimentoMesSeguinte() {
	var form = document.forms[0];
	
	if (form.diaVencimento.value != null &&  form.diaVencimento.value != '') {
		if (form.indicadorVencimentoMesSeguinte[0].checked == false && form.indicadorVencimentoMesSeguinte[1].checked == false) {
			alert("Informe se o vencimento é para o mês seguinte")
			return false;
		}
	}
	
	return true;
}

function validarDiaVencimentoTecla() {
	var form = document.forms[0];

	if (form.diaVencimento.value == null || form.diaVencimento.value == '') {
		form.indicadorVencimentoMesSeguinte[0].checked = false;
		form.indicadorVencimentoMesSeguinte[1].checked = false;
		form.indicadorVencimentoMesSeguinte[0].disabled = true;
		form.indicadorVencimentoMesSeguinte[1].disabled = true;
	} else {
		form.indicadorVencimentoMesSeguinte[0].disabled = false;
		form.indicadorVencimentoMesSeguinte[1].disabled = false;
	}
}

function validarNomeExibidoConta() {
	var form = document.forms[0];
	if (form.indicadorExibicaoNomeConta != undefined && form.indicadorExibicaoNomeConta != 'undefined' && form.indicadorExibicaoNomeConta.value != null &&  form.indicadorExibicaoNomeConta.value != '') {
		if (form.indicadorExibicaoNomeConta.value == '-1') {
			alert("Informe Nome a Ser Exibido na Conta:")
			return false;
		}
	}
	
	return true;
}

function verificarCampoDesabilitarCampos(desabilitarCampos){
	if(desabilitarCampos != null && desabilitarCampos == 'true'){
		disableAll();
	}
}

function disableAll(){
	var form = document.forms[0];
	var el = document.forms[0].elements;
	for(var i=0;i<el.length;i++){
		el[i].setAttribute('disabled',true);
	}

	var imgs = document.images;
	for(var i=0;i<imgs.length;i++){
		if(imgs[i].src.indexOf('imagens/avancar.gif') != -1 
			|| imgs[i].src.indexOf('/gsan/imagens/pesquisa_verde.gif') != -1){
			imgs[i].style.display = "none";
		}
	}

	

	form.indicadorUso[0].removeAttribute('disabled');
	form.indicadorUso[1].removeAttribute('disabled');

	var botaoVoltar = document.getElementById('botaoVoltar');
	var botaoDesfazer = document.getElementById('botaoDesfazer');
	var botaoCancelar = document.getElementById('botaoCancelar');
	var botaoConcluir = document.getElementById('botaoConcluir');
	botaoVoltar.removeAttribute('disabled');
	botaoDesfazer.removeAttribute('disabled');
	botaoCancelar.removeAttribute('disabled');
	botaoConcluir.removeAttribute('disabled');
	
}


-->
</script>
</head>
<body leftmargin="5" topmargin="5"
	onload="setarFoco('${requestScope.nomeCampo}');validarDiaVencimentoTecla(); verificarCampoDesabilitarCampos('${desabilitarCampos}');">
<div id="formDiv"><html:form action="/atualizarClienteWizardAction"
	method="post" onsubmit="return validateClienteActionForm(this);">

	<!-- CASO POPUP: Reposiciona as Abas -->
	<logic:equal name="POPUP" value="true" scope="session">
	<div id='Layer1PopUp' style='position:absolute; left:; top:-67px; width:300px; height:12px; z-index:1'>
	</logic:equal>
	
	<c:if  test="${desabilitarCampos == null ||  desabilitarCampos == false}" >
		<jsp:include page="/jsp/util/wizard/navegacao_abas_wizard_valida_avancar.jsp?numeroPagina=1" />
	</c:if>
	<logic:equal name="POPUP" value="true" scope="session">
	</div>
	</logic:equal>
	
	<!-- CASO POPUP: Retira o Cabecalho e o Menu -->
	<logic:equal name="POPUP" value="false" scope="session">
	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>
	</logic:equal>
	
	<input type="hidden" name="numeroPagina" value="1" />
	<table width="770" border="0" cellspacing="5" cellpadding="0">
		<tr>
			<!-- CASO POPUP: Retira a coluna de Informacoes do Usuario -->
			<logic:equal name="POPUP" value="false" scope="session">
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
			</logic:equal>
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
					<td class="parabg">Atualizar Cliente</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>
					<td>Para adicionar o nome e tipo do cliente, informe os dados
					abaixo:</td>
					<logic:present scope="application" name="urlHelp">
						<td align="right"><a href="javascript:abrirPopupHelp('${applicationScope.urlHelp}cadastroClienteAtualizarAbaNomeTipo', 500, 700);"><span style="font-weight: bold"><font color="#3165CE">Ajuda</font></span></a></td>									
					</logic:present>
					<logic:notPresent scope="application" name="urlHelp">
						<td align="right"><span style="font-weight: bold"><font color=#696969><u>Ajuda</u></font></span></td>									
					</logic:notPresent>
				</tr>
			</table>

			<table width="100%" border="0">
				<tr>
					<td><strong>Código:</strong></td>
					<td colspan="2"><html:hidden property="codigoCliente" /> <bean:write
						name="ClienteActionForm" property="codigoCliente" /></td>
				</tr>
				<logic:present name="indicadorNomeFantasia" scope="session">
			
					<tr>
						<td width="18%">
						<strong><bean:write name="descricao" scope="session"/><font color="#FF0000">*</font></strong>
						
						</td>
						<td width="82%">
						<html:text maxlength="50" property="nome" size="50" tabindex="1" />
							<!-- CASO POPUP: Retira o Pesquisar Cliente(Nao faz sentido no inserir) -->
							<logic:equal name="POPUP" value="false" scope="session">
							<c:if  test="${desabilitarCampos == null ||  desabilitarCampos == false}" >
								<a href="javascript:abrirPopup('exibirPesquisarClienteAction.do?limparForm=ok&indicadorUsoTodos=1&consultaCliente=sim', 400, 800);">
								<img width="23" height="21" border="0" title="Pesquisar Cliente" src="<bean:message key="caminho.imagens"/>pesquisa_verde.gif" /></a>
							</c:if>
							</logic:equal>
						</td>
					</tr>
					<tr>
						<td>
						<strong><bean:write name="descricaoAbreviada" scope="session"/></strong>
						</td>
						<td><html:text maxlength="40" property="nomeAbreviado" size="45"
							tabindex="2" /></td>
					</tr>
					
					<tr>
					<td><strong>Selecione o Nome a Ser Exibido na Conta:<font color="#FF0000">*</font></strong>
					</td>
					<td>
						<html:select property="indicadorExibicaoNomeConta" tabindex="5">
								<html:option  value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
								<html:option  value="1">Nome de Fantasia</html:option>
								<html:option  value="2">Nome na Receita Federal</html:option>
							
						</html:select>
					</td>
				</tr>
					
					
				</logic:present>
				
				<logic:notPresent name="indicadorNomeFantasia" scope="session">
						<tr>
							<td width="18%"><strong><bean:write name="descricao"
								scope="session" /><font color="#FF0000">*</font></strong></td>
							<td width="82%"><html:text maxlength="50" property="nome"
								size="50" tabindex="1" /> <!-- CASO POPUP: Retira o Pesquisar Cliente(Nao faz sentido no inserir) -->
							<logic:equal name="POPUP" value="false" scope="session">
								<a
									href="javascript:abrirPopup('exibirPesquisarClienteAction.do?limparForm=ok&indicadorUsoTodos=1&consultaCliente=sim', 400, 800);">
								<img width="23" height="21" border="0" title="Pesquisar Cliente"
									src="<bean:message key="caminho.imagens"/>pesquisa_verde.gif" /></a>
							</logic:equal></td>
						</tr>
						<tr>
							<td><strong><bean:write name="descricaoAbreviada"
								scope="session" /></strong></td>
							<td><html:text maxlength="40" property="nomeAbreviado"
								size="45" tabindex="2" /></td>
						</tr>
				</logic:notPresent>
				
				<tr>
					<td><strong>Tipo de Pessoa:<font color="#FF0000">*</font></strong>
					</td>
					<td><html:radio property="indicadorPessoaFisicaJuridica" value="1" tabindex="3" onchange="redirecionarSubmit('atualizarClienteWizardAction.do?destino=1&action=exibirAtualizarClienteNomeTipoAction');" />Física 
						<html:radio property="indicadorPessoaFisicaJuridica" value="2" tabindex="4" onchange="redirecionarSubmit('atualizarClienteWizardAction.do?destino=1&action=exibirAtualizarClienteNomeTipoAction');" />Jurídica </td>
				</tr>
				<tr>
					<td><strong>Tipo do Cliente:<font color="#FF0000">* </font></strong></td>
					<td><html:hidden property="tipoPessoaAntes" /> <html:select
						property="tipoPessoa" tabindex="5">
						<html:option
							value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
						<logic:present name="colecaoTipoPessoa">
						<html:options collection="colecaoTipoPessoa"
							labelProperty="descricaoComId" property="id" />
						</logic:present>
					</html:select></td>
				</tr>
				<tr>

					<td><strong> E-Mail: </strong></td>
					<td><html:text maxlength="40" property="email" size="40"
						tabindex="6" style="text-transform: none;" /></td>
				</tr>
				<logic:equal name="temPermissaoVisualizarDiaVencimentoContaCliente"
					value="true">
					<tr>
						<td><strong> Dia do Vencimento da Conta: </strong></td>
						<td><html:text maxlength="2" property="diaVencimento" size="2"
							tabindex="7" onkeyup="validarDiaVencimentoTecla();" /></td>
					</tr>
				</logic:equal>
				<logic:notEqual
					name="temPermissaoVisualizarDiaVencimentoContaCliente" value="true">
					<tr>
						<td><strong> Dia do Vencimento da Conta: </strong></td>
						<td><html:text maxlength="2" property="diaVencimento" size="2"
							tabindex="7" style="background-color:#EFEFEF; border:0; color: #000000" readonly="true" /></td>
					</tr>
				</logic:notEqual>
				<tr>
					<td><strong>Vencimento para Mês Seguinte?:</strong></td>
					<td>
					<html:radio property="indicadorVencimentoMesSeguinte"
						value="<%=ConstantesSistema.SIM.toString()%>" tabindex="8" />
					<strong>Sim</strong> <html:radio property="indicadorVencimentoMesSeguinte"
						value="<%=ConstantesSistema.NAO.toString()%>" tabindex="8" />
					<strong>Não</strong>
					</td>
				</tr>
				<tr>
					<td><strong>Indicador de Uso:</strong></td>
					<td align="right">
					<div align="left"><html:radio property="indicadorUso"
						value="<%=ConstantesSistema.INDICADOR_USO_ATIVO.toString()%>" />
					<strong>Ativo</strong> <html:radio property="indicadorUso"
						value="<%=ConstantesSistema.INDICADOR_USO_DESATIVO.toString()%>" />
					<strong>Inativo</strong></div>
					</td>
				</tr>
				
				
				<logic:equal name="temPermissaoAlterarAcrescimos" value="true">
				<tr>
					<td><strong>Acréscimos por impontualidade:<font color="#FF0000">*</font></strong></td>
					<td><html:radio property="indicadorAcrescimos" tabindex="2" value="1"><strong>Sim</strong></html:radio>
					<html:radio property="indicadorAcrescimos" tabindex="3" value="2" ><strong>Não</strong></html:radio>
					</td>
					<td>&nbsp;</td>
				</tr>
				</logic:equal>
				
				<logic:notEqual name="temPermissaoAlterarAcrescimos" value="true">
				<tr>
					<td><strong>Acréscimos por impontualidade:<font color="#FF0000">*</font></strong></td>
					<td><html:radio property="indicadorAcrescimos" tabindex="2" value="1" disabled="true"><strong>Sim</strong></html:radio>
					<html:radio property="indicadorAcrescimos" tabindex="3" value="2" disabled="true"><strong>Não</strong></html:radio>
					</td>
					<td>&nbsp;</td>
				</tr>
				
				
				</logic:notEqual>

				<tr>
					<td><strong>Permite Geração de Fatura Antecipada?:<font color="#FF0000">*</font></strong></td>
					<td align="right">
					<div align="left"><html:radio property="indicadorGeraFaturaAntecipada"
						value="<%=ConstantesSistema.SIM.toString()%>" />
					<strong>Sim</strong> <html:radio property="indicadorGeraFaturaAntecipada"
						value="<%=ConstantesSistema.NAO.toString()%>" />
					<strong>Não</strong></div>
					</td>
				</tr>
				
				<tr>
					<td><strong>Indicador de ação de cobrança:<font color="#FF0000">*</font></strong></td>
					<td align="right">
					<div align="left"><html:radio property="indicadorAcaoCobranca"
						value="<%=ConstantesSistema.SIM.toString()%>" />
					<strong>Sim</strong> <html:radio property="indicadorAcaoCobranca"
						value="<%=ConstantesSistema.NAO.toString()%>" />
					<strong>Não</strong></div>
					</td>
				</tr>

				<tr>
					<td><strong>Cliente bloqueado para negativação?</strong></td>
					
					<logic:present name="permissaoEspecial" scope="session">
						<td>
						<html:radio property="indicadorPermiteNegativacao"
							value="<%=ConstantesSistema.SIM.toString()%>" tabindex="9"/>
						<strong>Sim</strong> <html:radio property="indicadorPermiteNegativacao" 
							value="<%=ConstantesSistema.NAO.toString()%>" tabindex="9" />
						<strong>Não</strong>
						</td>
					</logic:present>
					
					<logic:notPresent name="permissaoEspecial" scope="session">
						<td>
						<html:radio property="indicadorPermiteNegativacao"
							value="<%=ConstantesSistema.SIM.toString()%>" tabindex="9" disabled="true"/>
						<strong>Sim</strong> <html:radio property="indicadorPermiteNegativacao"
							value="<%=ConstantesSistema.NAO.toString()%>" tabindex="9" />
						<strong>Não</strong>
						</td>
					</logic:notPresent>
				</tr>
				
				<tr>

					<td><strong> </strong></td>
					<td><strong> <font color="#FF0000"> * </font> </strong> Campo
					obrigat&oacute;rio</td>
				</tr>
				<tr>
					<td colspan="2">
					<div align="right">
						<jsp:include
						page="/jsp/util/wizard/navegacao_botoes_wizard_valida_avancar.jsp?numeroPagina=1" /></div>
					</td>
				</tr>

			</table>
			<p>&nbsp;</p>
			</td>
		</tr>
	</table>

	<!-- CASO POPUP: Retira o Rodape -->
	<logic:equal name="POPUP" value="false" scope="session">
	<%@ include file="/jsp/util/rodape.jsp"%>
	</logic:equal>
	<%@ include file="/jsp/util/tooltip.jsp"%>
</html:form></div>
</body>


<%@ include file="/jsp/util/telaespera.jsp"%>

<script>
document.getElementById('botaoConcluir').onclick = function() { botaoAvancarTelaEspera('/gsan/atualizarClienteWizardAction.do?concluir=true&action=atualizarClienteNomeTipoAction'); }

<logic:equal name="POPUP" value="true" scope="session">
//Altera o onclick do Cancelar caso seja chamado pelo popup
document.getElementById('botaoCancelar').onclick = function() { window.close(); }

//Altera o onclick do Desfazer caso seja chamado pelo popup
var botao = new String(document.getElementById('botaoDesfazer').onclick);
var acao = botao.substring( (botao.indexOf('"') + 1) , botao.lastIndexOf('"'));
acao = acao.replace('menu=sim', 'POPUP=true&desfazer=true');
document.getElementById('botaoDesfazer').onclick = function() { window.location.href = acao; }

document.getElementById('botaoVoltar').style.display = 'none';
</logic:equal>
</script>

</html:html>
