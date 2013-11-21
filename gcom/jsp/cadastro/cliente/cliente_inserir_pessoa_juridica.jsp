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
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
<script language="Javascript">
<!--
var bCancel = false;

function validateClienteActionForm(form) {
	if (bCancel)
		return true;
	else
		return testarCampoValorZero(document.ClienteActionForm.cnpj, 'CNPJ') 
			&& testarCampoValorZero(document.ClienteActionForm.codigoClienteResponsavel, 'Código do Cliente Responsável Superior') 
			&& validateCaracterEspecial(form) 
			&& validateLong(form) 
			&& validateCnpj(form);
			//&& validateRequired(form) 
}

/*function required () {
	this.ab = new Array("cnpj", "Informe CNPJ.", new Function ("varName", " return this[varName];"));
}*/

function IntegerValidations () {
	this.ac = new Array("cnpj", "CNPJ deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
	this.ad = new Array("codigoClienteResponsavel", "Código do Cliente Responsável Superior deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
}

function cnpj () {
	this.aa = new Array("cnpj", "CNPJ inválido.", new Function ("varName", " return this[varName];"));
}

function caracteresespeciais () {
	this.aa = new Array("cnpj", "CNPJ possui caracteres especiais.", new Function ("varName", " return this[varName];"));
	this.ab = new Array("codigoClienteResponsavel", "Código do Cliente Responsável Superior possui caracteres especiais.", new Function ("varName", " return this[varName];"));
}


//Recebe os dados do(s) popup(s)
function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
	var form = document.ClienteActionForm;
	if (tipoConsulta == 'responsavelSuperior') {
		form.codigoClienteResponsavel.value = codigoRegistro;
		form.nomeClienteResponsavel.value = descricaoRegistro;
		form.nomeClienteResponsavel.style.color = "#000000";
	    setarFoco('codigoClienteResponsavel');
  	}
}

function limparClienteResponsavel() {
	var form = document.ClienteActionForm;
	form.codigoClienteResponsavel.value = "";
	form.nomeClienteResponsavel.value = "";
}
-->
</script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="ClienteActionForm" dynamicJavascript="false"
	/>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>
</head>
<body leftmargin="5" topmargin="5"
	onload="setarFoco('${requestScope.nomeCampo}');">
	
<div id="formDiv">
<html:form action="/inserirClienteWizardAction" method="post">

	<!-- CASO POPUP: Reposiciona as Abas -->
	<logic:equal name="POPUP" value="true" scope="session">
	<div id='Layer1PopUp' style='position:absolute; left:; top:-67px; width:300px; height:12px; z-index:1'>
	</logic:equal>
	<jsp:include page="/jsp/util/wizard/navegacao_abas_wizard_valida_avancar.jsp?numeroPagina=2" />
	<logic:equal name="POPUP" value="true" scope="session">
	</div>
	</logic:equal>
	
	<!-- CASO POPUP: Retira o Cabecalho e o Menu -->
	<logic:equal name="POPUP" value="false" scope="session">
	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>
	</logic:equal>
	
	<input type="hidden" name="numeroPagina" value="2" />
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
					<td class="parabg">Inserir Cliente</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<table width="100%" border="0" dwcopytype="CopyTableRow">
				<tr>
					<td>Para adicionar um cliente pessoa jur&iacute;dica,
					informe os dados abaixo:</td>
					<logic:present scope="application" name="urlHelp">
						<td align="right"><a href="javascript:abrirPopupHelp('${applicationScope.urlHelp}cadastroClienteInserirAbaPessoa', 500, 700);"><span style="font-weight: bold"><font color="#3165CE">Ajuda</font></span></a></td>									
					</logic:present>
					<logic:notPresent scope="application" name="urlHelp">
						<td align="right"><span style="font-weight: bold"><font color=#696969><u>Ajuda</u></font></span></td>									
					</logic:notPresent>
				</tr>
    		</table>
    
    		<table width="100%" border="0">
				<tr>
					<td width="13%"><strong> CNPJ: </strong>
					</td>
					<td width="87%"><html:text property="cnpj" size="14" maxlength="14" tabindex="1" onkeypress="return isCampoNumerico(event);"/>
					<font size="1"> &nbsp; </font></td>
				</tr>
				<tr>
					<td><strong> Ramo de Atividade: </strong></td>
					<td>
						<html:select property="idRamoAtividade" tabindex="2">
							<html:option value="<%= "" + ConstantesSistema.NUMERO_NAO_INFORMADO %>">&nbsp;</html:option>
							<html:options collection="ramoAtividades" labelProperty="descricaoComId" property="id" />
						</html:select>
					</td>
				</tr>
				<tr>
					<td colspan="2"><hr></td>
				</tr>
				<tr>
					<td colspan="2">
						<strong> Cliente Respons&aacute;vel Superior </strong>
					</td>
				</tr>
				<tr>
					<td><strong> C&oacute;digo : </strong></td>
					<td>
						<html:text maxlength="9" property="codigoClienteResponsavel" size="9" tabindex="3"
							onkeypress="javascript:validaEnter(event, '/gsan/inserirClienteWizardAction.do?destino=2&action=exibirInserirClientePessoaAction', 'codigoClienteResponsavel');return isCampoNumerico(event);" />
						<a href="javascript:abrirPopup('exibirPesquisarResponsavelSuperiorAction.do', 400, 800);">
							<img src="<bean:message key="caminho.imagens"/>pesquisa.gif" border="0"
								width="23" height="21" title="Pesquisar Cliente"></a>
						<logic:present name="codigoClienteNaoEncontrado">
							<logic:equal name="codigoClienteNaoEncontrado" value="exception">
								<html:text property="nomeClienteResponsavel" size="50" maxlength="50" readonly="true"
									style="background-color:#EFEFEF; border:0; color: #ff0000" />
							</logic:equal>
							<logic:notEqual name="codigoClienteNaoEncontrado" value="exception">
								<html:text property="nomeClienteResponsavel" size="50" maxlength="50" readonly="true"
									style="background-color:#EFEFEF; border:0; color: #000000" />
							</logic:notEqual>
						</logic:present>
						<logic:notPresent name="codigoClienteNaoEncontrado">
							<logic:empty name="ClienteActionForm" property="codigoClienteResponsavel">
								<html:text property="nomeClienteResponsavel" value="" size="50"	maxlength="50" readonly="true"
									style="background-color:#EFEFEF; border:0; color: #ff0000" />
							</logic:empty>
							<logic:notEmpty name="ClienteActionForm" property="codigoClienteResponsavel">
								<html:text property="nomeClienteResponsavel" size="50" maxlength="50" readonly="true"
									style="background-color:#EFEFEF; border:0; color: #000000" />
							</logic:notEmpty>
						</logic:notPresent>
						<a href="javascript:limparClienteResponsavel();">
							<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
								border="0" title="Apagar" /></a>
					</td>
				<tr>
					<td colspan="2">
					<hr>
					</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td align="left"><font color="red"> * </font> Campo Obrigatório</td>
				</tr>
				<tr>
					<td colspan="2">
					<div align="right"><jsp:include
						page="/jsp/util/wizard/navegacao_botoes_wizard_valida_avancar.jsp?numeroPagina=2" />
					</div>
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
</html:form>
</div>
</body>

<%@ include file="/jsp/util/telaespera.jsp"%>

<script>
document.getElementById('botaoConcluir').onclick = function() { botaoAvancarTelaEspera('/gsan/inserirClienteWizardAction.do?concluir=true&action=inserirClientePessoaAction'); }

<logic:equal name="POPUP" value="true" scope="session">
//Altera o onclick do Cancelar caso seja chamado pelo popup
document.getElementById('botaoCancelar').onclick = function() { window.close(); }

//Altera o onclick do Desfazer caso seja chamado pelo popup
var botao = new String(document.getElementById('botaoDesfazer').onclick);
var acao = botao.substring( (botao.indexOf('"') + 1) , botao.lastIndexOf('"'));
acao = acao.replace('menu=sim', 'POPUP=true&desfazer=true');
document.getElementById('botaoDesfazer').onclick = function() { window.location.href = acao; }
</logic:equal>
</script>

</html:html>
