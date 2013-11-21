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
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="ClienteActionForm" dynamicJavascript="false"
	/>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript">
<!--

function verificarGrupoRG(){
	var form = document.ClienteActionForm;
	var rg = form.rg.value;
	var dataEmissao = form.dataEmissao.value;
	var orgaoExpedidor = form.idOrgaoExpedidor[form.idOrgaoExpedidor.selectedIndex].value;
	var estado = form.idUnidadeFederacao[form.idUnidadeFederacao.selectedIndex].value;
	
	//Faz a verificação do grupo do RG, onde se um campo for preenchido, todos terão que ser preenchidos
	if (rg == '' && orgaoExpedidor == '-1' && estado == '-1') {
		return true;
	}else{
		if (rg != '' && orgaoExpedidor != '-1' && estado != '-1') {
   			return true;
        } else {
			alert('O preenchimento dos campos RG, Órgão Expedidor e Estado é obrigatório, caso algum deles seja informado.');
			return false;
	    }
	}
}

var bCancel = false;

function validateClienteActionForm(form) {
	if (bCancel)
		return true;
	else{
		return validateRequired(form)
			&& validateCaracterEspecial(form) 
			&& validateLong(form) 
			&& validateDate(form)
			&& validateCpf(form) 
			&& testarCampoValorZero(form.rg, 'RG')
			&& verificarGrupoRG()};
}

function required () {
	var permissao = document.getElementById("permissaoCpf");
	this.ab = new Array("idPessoaSexo", "Informe Sexo.", new Function ("varName", " return this[varName];"));
	
	if(permissao.value == "false"){
		this.ac = new Array("cpf", "Informe o Cpf.", new Function ("varName", " return this[varName];"));
	}
}

function caracteresespeciais () {
	this.aa = new Array("cpf", "CPF possui caracteres especiais.", new Function ("varName", " return this[varName];"));
	this.ab = new Array("rg", "RG possui caracteres especiais.", new Function ("varName", " return this[varName];"));
	this.ac = new Array("nomeMae", "Nome da Mãe possui caracteres especiais.", new Function ("varName", " return this[varName];"));	
}

function cpf () {
	this.aa = new Array("cpf", "CPF inválido.", new Function ("varName", " return this[varName];"));
}

function IntegerValidations () {
	this.aa = new Array("cpf", "CPF deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
	//this.ab = new Array("rg", "RG deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
}

function DateValidations () {
	this.aa = new Array("dataEmissao", "Data de Emissão inválida.", new Function ("varName", "this.datePattern='dd/MM/yyyy';  return this[varName];"));
	this.ab = new Array("dataNascimento", "Data de Nascimento inválida.", new Function ("varName", "this.datePattern='dd/MM/yyyy';  return this[varName];"));
}
-->
</script>
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
	
	<input type="hidden" id="permissaoCpf" value="${requestScope.temPermissaoParaIncluirClienteSemCpf}" />
	
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
					<td>
						Para adicionar um cliente pessoa f&iacute;sica, informe os dados abaixo:
					</td>
					<logic:present scope="application" name="urlHelp">
						<td align="right"><a href="javascript:abrirPopupHelp('${applicationScope.urlHelp}cadastroClienteInserirAbaPessoa', 500, 700);"><span style="font-weight: bold"><font color="#3165CE">Ajuda</font></span></a></td>									
					</logic:present>
					<logic:notPresent scope="application" name="urlHelp">
						<td align="right"><span style="font-weight: bold"><font color=#696969><u>Ajuda</u></font></span></td>									
					</logic:notPresent>	
				</tr>
    		</table>
    
    		<table width="100%" border="0">
				
				<logic:equal name="temPermissaoParaIncluirClienteSemCpf" value="${false}" scope="request">
					<tr>
						<td width="18%" height="31">
							<strong>CPF:<font color="#FF0000">*</font></strong>
						</td>
						<td width="82%">
							<html:text property="cpf" size="11" maxlength="11" tabindex="1" onkeypress="return isCampoNumerico(event);" />
						</td>
					</tr>
				</logic:equal>
				<logic:notEqual name="temPermissaoParaIncluirClienteSemCpf" value="${false}" scope="request">
					<tr>
						<td width="18%" height="31">
							<strong>CPF:<font color="#FF0000"></font></strong>
						</td>
						<td width="82%">
							<html:text property="cpf" size="11" maxlength="11" tabindex="1" onkeypress="return isCampoNumerico(event);" />
						</td>
					</tr>
				</logic:notEqual>
				
				
				<tr>
					<td height="24" colspan="2">
					<hr>
					</td>
				</tr>
				<tr>
					<td height="24"><strong>RG:</strong></td>
					<td><html:text property="rg" size="13" maxlength="13" tabindex="2" onkeypress="return isCampoNumerico(event);" /></td>
				</tr>
				<tr>
					<td height="24"><strong>Data de Emiss&atilde;o:</strong></td>
					<td>
						<html:text property="dataEmissao" size="10" maxlength="10"
							tabindex="3" onkeyup="javascript:mascaraData(this, event);" onkeypress="return isCampoNumerico(event);"/>
						<a href="javascript:abrirCalendario('ClienteActionForm', 'dataEmissao')">
							<img border="0" src="<bean:message key="caminho.imagens"/>calendario.gif"
								width="20" border="0" align="absmiddle" alt="Exibir Calendário" /></a>
						<font size="2">dd/mm/aaaa</font>
					</td>
				</tr>
				<tr>
					<td height="24"><strong>&Oacute;rg&atilde;o Expedidor:</strong></td>
					<td>
						<html:select property="idOrgaoExpedidor" tabindex="4">
							<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
							<html:options collection="orgaosExpedidores"
								labelProperty="descricaoAbreviada" property="id" />
						</html:select>
					</td>
				</tr>
				<tr>
					<td height="24"><strong>Estado:</strong></td>
					<td><html:select property="idUnidadeFederacao" tabindex="5">
						<html:option
							value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
						<html:options collection="unidadesFederacao" labelProperty="sigla"
							property="id" />
					</html:select></td>
				</tr>
				<tr>
					<td height="24" colspan="2">
					<hr>
					</td>
				</tr>
				<tr>
					<td height="24"><strong>Data de Nascimento:</strong></td>
					<td>
						<html:text property="dataNascimento" size="10" maxlength="10"
							tabindex="6" onkeyup="javascript:mascaraData(this, event);" onkeypress="return isCampoNumerico(event);"/>
						<a href="javascript:abrirCalendario('ClienteActionForm', 'dataNascimento')">
							<img border="0" src="<bean:message key="caminho.imagens"/>calendario.gif"
								width="20" border="0" align="absmiddle" alt="Exibir Calendário" /></a>
						<font size="2">dd/mm/aaaa</font>
					</td>
				</tr>
				<tr>
					<td height="24"><strong>Profiss&atilde;o:</strong></td>
					<td><html:select property="idProfissao" tabindex="7">
						<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
						<html:options collection="profissoes" labelProperty="descricaoComId" property="id" />
					</html:select></td>
				</tr>
				<tr>
					<td height="24"><strong>Sexo:<font color="#FF0000">*</font></strong></td>
					<td>
						<html:select property="idPessoaSexo" tabindex="8">
							<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
							<html:options collection="pessoasSexos" labelProperty="descricaoComId" property="id" />
						</html:select>
					</td>
				</tr>
				<tr>
					<td height="24"><strong>Nome da Mãe:</strong></td>
					<td><html:text property="nomeMae" size="50" maxlength="50" tabindex="9" /></td>
				</tr>

				<tr>
					<td height="24"><strong></strong></td>
					<td>
						<strong><font color="#FF0000">*</font></strong> Campo obrigat&oacute;rio
					</td>
				</tr>
				<tr>
					<td colspan="2">
					<div align="right"><jsp:include
						page="/jsp/util/wizard/navegacao_botoes_wizard_valida_avancar.jsp?numeroPagina=2" /></div>
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
