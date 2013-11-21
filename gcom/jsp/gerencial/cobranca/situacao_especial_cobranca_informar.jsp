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
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="InformarResumoSituacaoEspecialCobrancaActionForm" />

<script language="JavaScript">
 
function pesquisarLocalidadeInicial() {
	var form = document.forms[0];
	form.tipoPesquisa.value = 'INICIAL';
	
	abrirPopup('exibirPesquisarLocalidadeAction.do?tipo=imovelLocalidade&indicadorUsoTodos=1', 400, 800);
}

function limparLocalidadeInicial() {
	var form = document.forms[0];
	
	form.idLocalidadeInicial.value = "";
	form.nomeLocalidadeInicial.value = "";
	form.idLocalidadeFinal.value = "";
	form.nomeLocalidadeFinal.value = "";
	form.codigoSetorComercialInicial.value = "";
	form.descricaoSetorComercialInicial.value = "";
	form.codigoSetorComercialFinal.value = "";
	form.descricaoSetorComercialFinal.value = "";
}

function limparLocalidadeInicialTecla() {
	var form = document.forms[0];
	
	form.nomeLocalidadeInicial.value = "";
	form.nomeLocalidadeFinal.value = "";
	form.codigoSetorComercialInicial.value = "";
	form.descricaoSetorComercialInicial.value = "";
	form.codigoSetorComercialFinal.value = "";
	form.descricaoSetorComercialFinal.value = "";
}

function pesquisarLocalidadeFinal() {
	var form = document.forms[0];
	form.tipoPesquisa.value = 'FINAL';
	
	abrirPopup('exibirPesquisarLocalidadeAction.do?tipo=imovelLocalidade&indicadorUsoTodos=1', 400, 800);
}

function limparLocalidadeFinal() {
	var form = document.forms[0];
	
	form.idLocalidadeFinal.value = "";
	form.nomeLocalidadeFinal.value = "";
	form.codigoSetorComercialInicial.value = "";
	form.descricaoSetorComercialInicial.value = "";
	form.codigoSetorComercialFinal.value = "";
	form.descricaoSetorComercialFinal.value = "";
}

function limparLocalidadeFinalTecla() {
	var form = document.forms[0];
	
	form.nomeLocalidadeFinal.value = "";
	form.codigoSetorComercialInicial.value = "";
	form.descricaoSetorComercialInicial.value = "";
	form.codigoSetorComercialFinal.value = "";
	form.descricaoSetorComercialFinal.value = "";
}

function pesquisarSetorComercialInicial() {
	var form = document.forms[0];
	form.tipoPesquisa.value = 'INICIAL';
	
	abrirPopupDependencia('exibirPesquisarSetorComercialAction.do?idLocalidade='+document.forms[0].idLocalidadeInicial.value+'&tipo=SetorComercial&indicadorUsoTodos=1',document.forms[0].idLocalidadeInicial.value,'Localidade', 400, 800);
}

function limparSetorComercialInicial() {
	var form = document.forms[0];
	
	form.codigoSetorComercialInicial.value = "";
	form.descricaoSetorComercialInicial.value = "";
	form.codigoSetorComercialFinal.value = "";
	form.descricaoSetorComercialFinal.value = "";
}

function limparSetorComercialInicialTecla() {
	var form = document.forms[0];
	
	form.descricaoSetorComercialInicial.value = "";
	form.descricaoSetorComercialFinal.value = "";
}

function pesquisarSetorComercialFinal() {
	var form = document.forms[0];
	form.tipoPesquisa.value = 'FINAL';
	
	abrirPopupDependencia('exibirPesquisarSetorComercialAction.do?idLocalidade='+document.forms[0].idLocalidadeFinal.value+'&tipo=SetorComercial&indicadorUsoTodos=1',document.forms[0].idLocalidadeFinal.value,'Localidade', 400, 800);
}

function limparSetorComercialFinal() {
	var form = document.forms[0];
	
	form.codigoSetorComercialFinal.value = "";
	form.descricaoSetorComercialFinal.value = "";
}

function limparSetorComercialFinalTecla() {
	var form = document.forms[0];
	
	form.descricaoSetorComercialFinal.value = "";
}




/* Recuperar Popup */

function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {

    var form = document.forms[0];

    if (tipoConsulta == 'localidade') {
    
    	if (form.tipoPesquisa.value == 'INICIAL') {
			form.idLocalidadeInicial.value = codigoRegistro;
			form.nomeLocalidadeInicial.value = descricaoRegistro;
			form.nomeLocalidadeInicial.style.color = "#000000";
			form.idLocalidadeFinal.value = codigoRegistro;
			form.nomeLocalidadeFinal.value = descricaoRegistro;
			form.nomeLocalidadeFinal.style.color = "#000000";
			form.idLocalidadeFinal.focus();
		} else if (form.tipoPesquisa.value == 'FINAL') {
			form.idLocalidadeFinal.value = codigoRegistro;
			form.nomeLocalidadeFinal.value = descricaoRegistro;
			form.nomeLocalidadeFinal.style.color = "#000000";
		}
    }

    if (tipoConsulta == 'setorComercial') {
    
    	if (form.tipoPesquisa.value == 'INICIAL') {
			form.codigoSetorComercialInicial.value = codigoRegistro;
			form.descricaoSetorComercialInicial.value = descricaoRegistro;
			form.descricaoSetorComercialInicial.style.color = "#000000";
			form.codigoSetorComercialFinal.value = codigoRegistro;
			form.descricaoSetorComercialFinal.value = descricaoRegistro;
			form.descricaoSetorComercialFinal.style.color = "#000000";
			form.codigoSetorComercialFinal.focus();
		} else if (form.tipoPesquisa.value == 'FINAL') {
			form.codigoSetorComercialFinal.value = codigoRegistro;
			form.descricaoSetorComercialFinal.value = descricaoRegistro;
			form.descricaoSetorComercialFinal.style.color = "#000000";
		}
	}
}

function replicaDados(campoOrigem, campoDestino){
	campoDestino.value = campoOrigem.value;
}

function validarForm() {
	var form = document.forms[0];
	
	if(validateInformarResumoSituacaoEspecialCobrancaActionForm(form)){			
		if (validarIntervalos() && validarSetorComercial() && validarRota()) {
			submeterFormPadrao(form);
		}
	}
}

function validarIntervalos() {
	var form = document.forms[0];
	
	if(form.idLocalidadeInicial.value.length > 0 && form.idLocalidadeFinal.value.length == 0) {
		alert('Informe Localidade Final');
		return false;
	} else if(form.idLocalidadeFinal.value.length > 0 && form.idLocalidadeInicial.value.length == 0) {
		alert('Informe Localidade Inicial');
		return false;
	} else if(form.codigoSetorComercialInicial.value.length > 0 && form.codigoSetorComercialFinal.value.length == 0) {
		alert('Informe Setor Comercial Final');
		return false;
	} else if(form.codigoSetorComercialFinal.value.length > 0 && form.codigoSetorComercialInicial.value.length == 0) {
		alert('Informe Setor Comercial Inicial');
		return false;
	} else if(form.codigoRotaInicial.value.length > 0 && form.codigoRotaFinal.value.length == 0) {
		alert('Informe Rota Final');
		return false;
	} else if(form.codigoRotaFinal.value.length > 0 && form.codigoRotaInicial.value.length == 0) {
		alert('Informe Rota Inicial');
		return false;
	}
	
	return true;
}

function validarSetorComercial() {
	var form = document.forms[0];
	
	if (form.codigoSetorComercialInicial.value.length > 0 && form.idLocalidadeInicial.value.length == 0) {
		alert('Para informar o Setor Comercial é necessário informar a Localidade');
		return false;
	}
	
	return true;
}

function validarRota() {
	var form = document.forms[0];
	
	if (form.codigoRotaInicial.value.length > 0 && form.codigoSetorComercialInicial.value.length == 0) {
		alert('Para informar a Rota é necessário informar o Setor Comercial');
		return false;
	}
	
	return true;
}

</script>
</head>
<body leftmargin="5" topmargin="5" onload="setarFoco('${requestScope.nomeCampo}');">
<html:form action="/informarResumoSituacaoEspecialCobrancaAction.do"
	name="InformarResumoSituacaoEspecialCobrancaActionForm"
	type="gcom.gui.gerencial.cobranca.InformarResumoSituacaoEspecialCobrancaActionForm"
	method="post">

	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>
	
	<html:hidden property="tipoPesquisa" />
	
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
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_left.gif" /></td>
					<td class="parabg">Consultar Resumo de Situação Especial de Cobranca</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<table border="0" width="100%">
				<tr>
					<td colspan="2">Para gerar o relatório ou a consulta, informe os dados abaixo:</td>
				</tr>
								<tr>
					<td><strong>Gerência Regional:</strong></td>
					<td><html:select property="idGerenciaRegional" tabindex="1">
						<html:option
							value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
						<html:options collection="colecaoGerenciaRegional"
							labelProperty="nome" property="id" />
					</html:select>
					</td>
				</tr>
				<tr>
					<td><strong>Unidade Negócio:</strong></td>
					<td><html:select property="idUnidadeNegocio" tabindex="2">
						<html:option
							value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
						<html:options collection="colecaoUnidadeNegocio"
							labelProperty="nome" property="id" />
					</html:select>
					</td>
				</tr>
				<tr>
					<td><strong>Localidade Inicial:</strong></td>
					<td><html:text maxlength="3"
						tabindex="3" property="idLocalidadeInicial" size="3"
						onkeyup="javascript:limparLocalidadeInicialTecla(); validaEnter(event, 'exibirInformarResumoSituacaoEspecialCobrancaAction.do', 'idLocalidadeInicial'); replicaDados(document.forms[0].idLocalidadeInicial, document.forms[0].idLocalidadeFinal);" />
					<a
						href="javascript:pesquisarLocalidadeInicial();">
					<img width="23" height="21" border="0"
						src="<bean:message key="caminho.imagens"/>pesquisa.gif"
						title="Pesquisar Localidade" /></a> 
						<logic:present name="localidadeInicialInexistente" scope="request">
							<html:text property="nomeLocalidadeInicial" size="40"
								maxlength="30" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:present> 
						<logic:notPresent name="localidadeInicialInexistente" scope="request">
							<html:text property="nomeLocalidadeInicial" size="40"
								maxlength="30" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
					</logic:notPresent> <a
						href="javascript:limparLocalidadeInicial();">
					<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar" /></a></td>
				</tr>
				<tr>
					<td><strong>Setor Comercial Inicial:</strong></td>
					<td colspan="2" height="24"><html:text maxlength="3"
						property="codigoSetorComercialInicial" tabindex="4" size="3"
						onkeyup="javascript:limparSetorComercialInicialTecla();replicaDados(document.forms[0].codigoSetorComercialInicial, document.forms[0].codigoSetorComercialFinal);return validaEnterDependencia(event, 'exibirInformarResumoSituacaoEspecialCobrancaAction.do', this, document.forms[0].idLocalidadeInicial.value, 'Localidade');" /> <a
						href="javascript:pesquisarSetorComercialInicial();">
					<img width="23" height="21" border="0"
						src="<bean:message key="caminho.imagens"/>pesquisa.gif"
						title="Pesquisar Setor Comercial" /></a> 
						<logic:present name="setorComercialInicialInexistente">
							<html:text property="descricaoSetorComercialInicial" size="40"
								maxlength="30" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
					</logic:present> 
					<logic:notPresent name="setorComercialInicialInexistente">
							<html:text property="descricaoSetorComercialInicial" size="40"
								maxlength="30" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
					</logic:notPresent> <a
						href="javascript:limparSetorComercialInicial();">
					<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar" /></a></td>
				</tr>
				<tr>
					<td><strong>Rota Inicial:</strong></td>
					<td><html:text property="codigoRotaInicial" maxlength="5" size="5" tabindex="5" onkeyup="replicaDados(document.forms[0].codigoRotaInicial, document.forms[0].codigoRotaFinal);"/></td>
				</tr>
				<tr>
					<td><strong>Localidade Final:</strong></td>
					<td><html:text maxlength="3"
						tabindex="6" property="idLocalidadeFinal" size="3"
						onkeyup="javascript:limparLocalidadeFinalTecla(); validaEnter(event, 'exibirInformarResumoSituacaoEspecialCobrancaAction.do', 'idLocalidadeFinal');" />
					<a
						href="javascript:pesquisarLocalidadeFinal();">
					<img width="23" height="21" border="0"
						src="<bean:message key="caminho.imagens"/>pesquisa.gif"
						title="Pesquisar Localidade" /></a> 
						<logic:present name="localidadeFinalInexistente" scope="request">
							<html:text property="nomeLocalidadeFinal" size="40"
								maxlength="30" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:present> 
						<logic:notPresent name="localidadeFinalInexistente" scope="request">
							<html:text property="nomeLocalidadeFinal" size="40"
								maxlength="30" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
					</logic:notPresent> <a
						href="javascript:limparLocalidadeFinal();">
					<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar" /></a></td>
				</tr>
				<tr>
					<td><strong>Setor Comercial Final:</strong></td>
					<td colspan="2" height="24"><html:text maxlength="3"
						property="codigoSetorComercialFinal" tabindex="7" size="3"
						onkeyup="javascript:limparSetorComercialFinalTecla();return validaEnterDependencia(event, 'exibirInformarResumoSituacaoEspecialCobrancaAction.do', this, document.forms[0].idLocalidadeFinal.value, 'Localidade');" /> <a
						href="javascript:pesquisarSetorComercialFinal();">
					<img width="23" height="21" border="0"
						src="<bean:message key="caminho.imagens"/>pesquisa.gif"
						title="Pesquisar Setor Comercial" /></a> 
						<logic:present name="setorComercialFinalInexistente">
							<html:text property="descricaoSetorComercialFinal" size="40"
								maxlength="30" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
					</logic:present> 
					<logic:notPresent name="setorComercialFinalInexistente">
							<html:text property="descricaoSetorComercialFinal" size="40"
								maxlength="30" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
					</logic:notPresent> <a
						href="javascript:limparSetorComercialFinal();">
					<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar" /></a></td>
				</tr>
				<tr>
					<td><strong>Rota Final:</strong></td>
					<td><html:text property="codigoRotaFinal" maxlength="5" size="5" tabindex="8"/></td>
				</tr>
				<tr>
					<td><strong>Situação:</strong></td>
					<td align="right">
					<div align="left"><html:select
						property="situacaoTipo" style="width: 400px;"
						multiple="mutiple" size="6">
						<logic:present name="colecaoCobSitTipo">
							<html:option value="" />
							<html:options collection="colecaoCobSitTipo"
								labelProperty="descricao" property="id" />
						</logic:present>
					</html:select></div>
					</td>
				</tr>
				<tr>
				<td>
				</td>
				</tr>
				<tr>
					<td><strong>Motivo:<font color="#ff0000"></font></strong></td>

					<td align="right">
					<div align="left"><html:select
						property="situacaoMotivo" style="width: 400px;"
						multiple="mutiple" size="6">
						<logic:present name="colecaoCobSitMotivo">
							<html:option value="" />
							<html:options collection="colecaoCobSitMotivo"
								labelProperty="descricao" property="id" />
						</logic:present>
					</html:select></div>
					</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td align="right">&nbsp;</td>
				</tr>
				
				<tr>
					<td><input name="Submit22"
						class="bottonRightCol" value="Limpar" type="button"
						onclick="window.location.href='/gsan/exibirInformarResumoSituacaoEspecialCobrancaAction.do?menu=sim';">
					</td>
					<td align="right" colspan="2"><input name="botao" class="bottonRightCol"
						value="Gerar Consulta/Relatório" onclick="validarForm();"
						type="button"></td>
				</tr>
			</table>
			<p>&nbsp;</p>
</td>
		</tr>
	</table>

	<%@ include file="/jsp/util/rodape.jsp"%>

</html:form>
</body>
</html:html>
