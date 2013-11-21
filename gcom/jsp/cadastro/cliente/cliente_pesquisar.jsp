<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>


<%@ page import="gcom.util.ConstantesSistema"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
<head>
<title>GSAN - Sistema Integrado de Gest&atilde;o de Servi&ccedil;os de Saneamento</title>

<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="PesquisarActionForm"	dynamicJavascript="false" />

<script language="JavaScript">
var bCancel = false;

function validatePesquisarActionForm(form) {
	if (bCancel)
		return true;
	else
		return verificarExclusividade(document.PesquisarActionForm.cpf.value,document.PesquisarActionForm.rg.value,document.PesquisarActionForm.cnpj.value) 
		&& testarCampoValorZero(document.PesquisarActionForm.cpf, 'CPF') 
		&& testarCampoValorZero(document.PesquisarActionForm.rg, 'RG') 
		&& testarCampoValorZero(document.PesquisarActionForm.cnpj, 'CNPJ') 
		&& testarCampoValorZero(document.PesquisarActionForm.cepClienteEndereco, 'CEP') 
		&& testarCampoValorZero(document.PesquisarActionForm.idMunicipioCliente, 'Município') 
		&& testarCampoValorZero(document.PesquisarActionForm.codigoBairroCliente, 'Bairro') 
		&& testarCampoValorZero(document.PesquisarActionForm.idLogradouroCliente, 'Logradouro') 
		&& validateCaracterEspecial(form) && validateLong(form) && validateCnpj(form) 
		&& validateCpf(form);
}

function caracteresespeciais () {
	this.aa = new Array("nomeCliente", "Nome possui caracteres especiais.", new Function ("varName", " return this[varName];"));
	this.ab = new Array("cpf", "CPF possui caracteres especiais.", new Function ("varName", " return this[varName];"));
	this.ac = new Array("rg", "RG possui caracteres especiais.", new Function ("varName", " return this[varName];"));
	this.ad = new Array("cnpj", "CNPJ possui caracteres especiais.", new Function ("varName", " return this[varName];"));
	this.ae = new Array("cepClienteEndereco", "CEP possui caracteres especiais.", new Function ("varName", " return this[varName];"));
	this.af = new Array("idMunicipioCliente", "Município possui caracteres especiais.", new Function ("varName", " return this[varName];"));
	this.ad = new Array("codigoBairroCliente", "Bairro possui caracteres especiais.", new Function ("varName", " return this[varName];"));
	this.ah = new Array("idLogradouroCliente", "Logradouro possui caracteres especiais.", new Function ("varName", " return this[varName];"));
}

function IntegerValidations () {
	this.ab = new Array("cpf", "CPF deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
	this.ac = new Array("rg", "RG deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
	this.ad = new Array("cnpj", "CNPJ deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
	this.ae = new Array("cepClienteEndereco", "CEP deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
	this.af = new Array("idMunicipioCliente", "Município deve somente conter números positivoss.", new Function ("varName", " return this[varName];"));
	this.ag = new Array("codigoBairroCliente", "Bairro deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
	this.ah = new Array("idLogradouroCliente", "Logradouro deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
}

function cnpj () {
	this.aa = new Array("cnpj", "CNPJ inválido.", new Function ("varName", " return this[varName];"));
}

function cpf () {
	this.aa = new Array("cpf", "CPF inválido.", new Function ("varName", " return this[varName];"));
}

function verificarExclusividade(cpf,rg,cnpj){
	var retorno = true;
	if(cnpj != ''){
		if(cpf != ''){
			alert('CPF e CNPJ são exclusivos. Informe um ou outro.');
         	retorno = false;
       	}
        if(rg != ''){
        	alert('RG e CNPJ são exclusivos. Informe um ou outro.');
        	retorno = false;
       	}
    }
    return retorno;
}

//Recebe os dados do(s) popup(s)
function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
	var form = document.PesquisarActionForm;
	if (tipoConsulta == 'municipio') {
		form.idMunicipioCliente.value = codigoRegistro;
		form.descricaoMunicipioCliente.value = descricaoRegistro;
		form.codigoBairroCliente.value = "";
		form.descricaoBairroCliente.value = "";
		form.descricaoMunicipioCliente.style.color = "#000000";
	}
    if (tipoConsulta == 'bairro') {
		form.codigoBairroCliente.value = codigoRegistro;
		form.descricaoBairroCliente.value = descricaoRegistro;
		form.descricaoBairroCliente.style.color = "#000000";
    }
}

function limparPesquisaMunicipio() {
	var form = document.PesquisarActionForm;
    form.idMunicipioCliente.value = "";
    form.descricaoMunicipioClienTabelaAuxiliarIndicadorActionFormte.value = "";
}

function limparPesquisaBairro() {
    var form = document.PesquisarActionForm;
    form.codigoBairroCliente.value = "";
    form.descricaoBairroCliente.value = "";
}

function limparPesquisaLogradouro(){
    var form = document.PesquisarActionForm;
    form.idLogradouroCliente.value = "";
    form.nomeLogradouroCliente.value = "";
}

function validarForm(form){
	if(validatePesquisarActionForm(form)){
		//if(validatePesquisarActionForm(form) && performanceJSP()){
		//form.submit();
		submitForm(form);
	}
}


//Colocado por Raphael Rossiter em 01/02/2007
function performanceJSP(){

	form = document.forms[0];
	
	/**
	 * Objetos em dupla de acordo com as dependências que vc queira gerar
	 *
	 * EX: var camposDependencia = [[form.campo1, form.campo2],[form.campo1, form.campo3],[form.campo3, form.campo4]];
	 */
	var camposDependencia = [[form.idMunicipioCliente, form.codigoBairroCliente],[form.codigoBairroCliente,form.idMunicipioCliente]];
		
	/**
	 * Mensagens sincronizadas com as duplas informadas no objeto camposDependencia
	 *
	 * EX: var camposDependencia = [[form.campo1, form.campo2],[form.campo1, form.campo3],[form.campo3, form.campo4]];
	 *     var msgDependecia = ["nomeCampo2", "nomeCampo3", "nomeCampo4"];
	 */
	var msgDependecia = ["Bairro","Município"];
	
	/**
	 * Objetos que poderão já vir informados ou serem informados pelo usuário mas que não serão
	 * considerados como parâmetros informados (INDICADOR_USO).
	 *
	 * EX: var camposExclusao = [form.indicadorUso];
	 */
	var camposExclusao = [form.idTipoCliente, form.tipoPesquisa[0]];
	
		
	/**
	 * Caso o objeto camposDependencia seja informado, o objeto msgDependecia será obrigatório e visse-versa.
	 * Caso o objeto camposExclusao seja informado, o formulário será obrigatório e visse-versa.
	 */
	retorno = dependenciaPerformance(camposDependencia, msgDependecia, camposExclusao, form);
	
	
	return retorno;
}


function limparForm(){
	var form = document.PesquisarActionForm;
	    
	form.idTipoCliente.value = -1;
	form.nomeCliente.value = "";
	form.cpf.value = "";
	form.rg.value = "";
	form.cnpj.value = "";
	form.id.value = "";
	form.cepClienteEndereco.value = "";
	form.codigoBairroCliente.value = "";
	form.descricaoBairroCliente.value = "";
	form.idMunicipioCliente.value = "";
	form.descricaoMunicipioClienTabelaAuxiliarIndicadorActionFormte.value = "";
    form.idLogradouroCliente.value = "";
    form.nomeLogradouroCliente.value = "";
    form.tipoPesquisa[0].checked = true;
}

//Verifa parametro GET tipoConsulta
function verificaTipoConsulta(){
	var tipoConsulta = getURLParameter('tipoConsulta');
    var form = document.forms[0]; 
    if(tipoConsulta != null && tipoConsulta != ""){
		form.action = form.action + "?tipoConsulta=" + tipoConsulta;
    }

}
function getURLParameter( name )
{
  name = name.replace(/[\[]/,"\\\[").replace(/[\]]/,"\\\]");
  var regexS = "[\\?&]"+name+"=([^&#]*)";
  var regex = new RegExp( regexS );
  var results = regex.exec( window.location.href );
  if( results == null )
    return "";
  else
    return results[1];
}


</script>
</head>
<body leftmargin="5" topmargin="5"
	onload="window.focus();javascript:resizePageSemLink(800, 550);javascript:setarFoco('${requestScope.nomeCampo}'); verificaTipoConsulta();">

<div id="formDiv">
<html:form action="/pesquisarClienteAction" method="post"
	onsubmit="return validatePesquisarActionForm(this);">
	
	<table width="770" border="0" cellspacing="5" cellpadding="0">
		<tr>
			<td width="770" valign="top" class="centercoltext">
			<table height="100%">
				<tr>
					<td></td>
				</tr>
			</table>
			<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
				<tr>
					<td width="11">
						<img border="0" src="<bean:message key="caminho.imagens"/>parahead_left.gif" />
					</td>
					<td class="parabg">Pesquisar Cliente</td>
					<td width="11">
						<img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif" />
					</td>
				</tr>
			</table>
			<table width="100%" border="0">
				<tr>
					<td height="28">Preencha os campos para pesquisar um cliente:</td>
					<logic:present scope="application" name="urlHelp">
						<td align="right"><a href="javascript:abrirPopupHelp('${applicationScope.urlHelp}cadastroClientePesquisar', 500, 700);"><span style="font-weight: bold"><font color="#3165CE">Ajuda</font></span></a></td>									
					</logic:present>
					<logic:notPresent scope="application" name="urlHelp">
						<td align="right"><span style="font-weight: bold"><font color=#696969><u>Ajuda</u></font></span></td>									
					</logic:notPresent>	
    			</tr>
    		</table>
    
    		<table width="100%" border="0">
				<tr>
					<td width="18%" height="28"><strong>Tipo Cliente:</strong></td>
					<td width="82%">
						<html:select property="idTipoCliente" tabindex="1">
							<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
							<html:options collection="tiposClientes" labelProperty="descricao" property="id" />
						</html:select>	
					</td>
				</tr>
				<tr>
					<td height="28"><strong>Nome:</strong></td>
					<td>
						<html:text maxlength="50" property="nomeCliente" size="60" tabindex="2" />
					</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td align="top">
						<html:radio property="tipoPesquisa"
							value="<%=ConstantesSistema.TIPO_PESQUISA_INICIAL.toString()%>" />
						Iniciando pelo texto
						<html:radio property="tipoPesquisa"
							value="<%=ConstantesSistema.TIPO_PESQUISA_COMPLETA.toString()%>" />
						Contendo o texto
					</td>
				</tr>
				<tr>
					<td height="28"><strong>CPF:</strong></td>
					<td>
						<html:text maxlength="11" property="cpf" size="11" tabindex="3" />
					</td>
				</tr>
				<tr>
					<td height="28"><strong>RG:</strong></td>
					<td>
						<html:text maxlength="13" property="rg" size="14" tabindex="4" />
					</td>
				</tr>
				<tr>
					<td height="28"><strong>CNPJ:</strong></td>
					<td>
						<html:text maxlength="14" property="cnpj" size="14" tabindex="5" />
					</td>
				</tr>
				<tr>
					<td height="28"><strong>CEP:</strong></td>
					<td>
						<html:text maxlength="8" property="cepClienteEndereco" size="9" tabindex="6" />
					</td>
				</tr>
				<tr>
					<td><strong>Munic&iacute;pio:</strong></td>
					<td>
						<html:text maxlength="4" property="idMunicipioCliente" size="4" tabindex="7"
							onkeypress="javascript:validaEnterComMensagem(event, 'exibirPesquisarClienteAction.do?objetoConsulta=1', 'idMunicipioCliente', 'Município');" />
						<a href="javascript:redirecionarSubmit('exibirPesquisarMunicipioAction.do?caminhoRetornoTelaPesquisaMunicipio=exibirPesquisarClienteAction');">
							<img width="23" height="21" border="0" src="<bean:message key="caminho.imagens"/>pesquisa.gif"
								title="Pesquisar Municipio" /></a>
						<logic:present name="idMunicipioClienteNaoEncontrado">
							<logic:equal name="idMunicipioClienteNaoEncontrado" value="exception">
								<html:text property="descricaoMunicipioClienTabelaAuxiliarIndicadorActionFormte" size="40" maxlength="30" readonly="true"
									style="background-color:#EFEFEF; border:0; color: #ff0000" />
							</logic:equal>
							<logic:notEqual name="idMunicipioClienteNaoEncontrado" value="exception">
								<html:text property="descricaoMunicipioClienTabelaAuxiliarIndicadorActionFormte" size="40" maxlength="30" readonly="true"
									style="background-color:#EFEFEF; border:0; color: #000000" />
							</logic:notEqual>
						</logic:present> 
						<logic:notPresent name="idMunicipioClienteNaoEncontrado">
							<logic:empty name="PesquisarActionForm" property="idMunicipioCliente">
								<html:text property="descricaoMunicipioClienTabelaAuxiliarIndicadorActionFormte" value="" size="40" maxlength="30" readonly="true"
									style="background-color:#EFEFEF; border:0; color: #ff0000" />
							</logic:empty>
							<logic:notEmpty name="PesquisarActionForm" property="idMunicipioCliente">
								<html:text property="descricaoMunicipioClienTabelaAuxiliarIndicadorActionFormte" size="40" maxlength="30" readonly="true"
									style="background-color:#EFEFEF; border:0; color: #000000" />
							</logic:notEmpty>
						</logic:notPresent>
						<a href="javascript:limparPesquisaMunicipio();">
							<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
							border="0" title="Apagar" /></a>
					</td>
				</tr>
				<tr>
					<td><strong>Bairro:</strong></td>
					<td>
						<html:text maxlength="4" property="codigoBairroCliente" tabindex="8" size="4"
							onkeypress="javascript:validaEnterComMensagem(event, 'exibirPesquisarClienteAction.do?objetoConsulta=1', 'codigoBairroCliente', 'Bairro');" />
						<a href="javascript:redirecionarSubmit('exibirPesquisarBairroAction.do?caminhoRetornoTelaPesquisaBairro=exibirPesquisarClienteAction&idMunicipio='+document.forms[0].idMunicipioCliente.value);">
							<img width="23" height="21" border="0" src="<bean:message key="caminho.imagens"/>pesquisa.gif"
								title="Pesquisar Bairro" /></a>
						<logic:present name="codigoBairroClienteNaoEncontrado">
							<logic:equal name="codigoBairroClienteNaoEncontrado" value="exception">
								<html:text property="descricaoBairroCliente" size="40" maxlength="30" readonly="true"
									style="background-color:#EFEFEF; border:0; color: #ff0000" />
							</logic:equal>
							<logic:notEqual name="codigoBairroClienteNaoEncontrado" value="exception">
								<html:text property="descricaoBairroCliente" size="40" maxlength="30" readonly="true"
									style="background-color:#EFEFEF; border:0; color: #000000" />
							</logic:notEqual>
						</logic:present>
						<logic:notPresent name="codigoBairroClienteNaoEncontrado">
							<logic:empty name="PesquisarActionForm" property="codigoBairroCliente">
								<html:text property="descricaoBairroCliente" value="" size="40"	maxlength="30" readonly="true"
									style="background-color:#EFEFEF; border:0; color: #ff0000" />
							</logic:empty>
							<logic:notEmpty name="PesquisarActionForm" property="codigoBairroCliente">
								<html:text property="descricaoBairroCliente" size="40" maxlength="30" readonly="true"
									style="background-color:#EFEFEF; border:0; color: #000000" />
							</logic:notEmpty>
						</logic:notPresent> 
						<a href="javascript:limparPesquisaBairro();"> 
							<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
							border="0" title="Apagar" /></a>
					</td>
				</tr>
				<tr>
					<td width="18%"><strong>Logradouro:</strong></td>
					<td width="18%">
						<html:text property="idLogradouroCliente" size="9" maxlength="9" tabindex="9"
							onkeypress="validaEnterComMensagem(event, 'exibirPesquisarClienteAction.do?objetoConsulta=1', 'idLogradouroCliente', 'Logragouro');" />
						<a href="javascript:redirecionarSubmit('/gsan/exibirPesquisarLogradouroAction.do?caminhoRetornoTelaPesquisaLogradouro=exibirPesquisarClienteAction&
						codigoMunicipio='+document.forms[0].idMunicipioCliente.value+'&codigoBairro='+document.forms[0].codigoBairroCliente.value+'&indicadorUsoTodos=1&primeriaVez=1', 465, 380);"> 
							<img src="<bean:message key='caminho.imagens'/>pesquisa.gif" width="23" height="21" 
								border="0" style="cursor:hand;" alt="Pesquisar"></a>
						<logic:present name="idLogradouroNaoEncontrado">
							<logic:equal name="idLogradouroNaoEncontrado" value="exception">
								<html:text property="nomeLogradouroCliente" size="40" maxlength="30" readonly="true"
									style="background-color:#EFEFEF; border:0; color: #ff0000" />
							</logic:equal>
							<logic:notEqual name="idLogradouroNaoEncontrado" value="exception">
								<html:text property="nomeLogradouroCliente" size="40" maxlength="30" readonly="true"
									style="background-color:#EFEFEF; border:0; color: #000000" />
							</logic:notEqual>
						</logic:present>
						<logic:notPresent name="idLogradouroNaoEncontrado">
							<logic:empty name="PesquisarActionForm" property="idLogradouroCliente">
								<html:text property="nomeLogradouroCliente" value="" size="40" maxlength="30" readonly="true"
									style="background-color:#EFEFEF; border:0; color: #ff0000" />
							</logic:empty>
							<logic:notEmpty name="PesquisarActionForm" property="idLogradouroCliente">
								<html:text property="nomeLogradouroCliente" size="40" maxlength="30" readonly="true"
									style="background-color:#EFEFEF; border:0; color: #000000" />
							</logic:notEmpty>
						</logic:notPresent> 
						<a href="javascript:limparPesquisaLogradouro();"> 
							<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
								border="0" title="Apagar" /></a>
					</td>
				</tr>
				<tr>
					<td><strong>Esfera Poder:</strong></td>
					<td align="left"><html:select property="idEsferaPoder">
						<html:option value="-1">&nbsp;</html:option>
						<html:options collection="colecaoEsferaPoder"
							labelProperty="descricao" property="id" />
					</html:select></td>
				</tr>
				<tr>
					<td></td>
				</tr>
				<tr>
					<td height="24" colspan="3" width="80%">
						<input type="button" class="bottonRightCol" value="Limpar" onclick="javascript:limparForm();"/>&nbsp;
						<logic:present name="caminhoRetornoTelaPesquisaCliente" scope="session">
							<input type="button" class="bottonRightCol" value="Voltar" onclick="redirecionarSubmit('${caminhoRetornoTelaPesquisaCliente}.do')"/>
						</logic:present>
					</td>
					<td>
						
						<input type="button" name="Button" class="bottonRightCol" tabindex="10" value="Pesquisar"
							onClick="javascript:validarForm(document.forms[0])" />
					</td>
				</tr>
			</table>
			<p>&nbsp;</p>
			</td>
		</tr>
	</table>
</html:form>
</div>

</body>

<%@ include file="/jsp/util/telaespera.jsp"%>

</html:html>
