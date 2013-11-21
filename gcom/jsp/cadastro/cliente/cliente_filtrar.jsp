<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%@page import="gcom.util.ConstantesSistema"%>

<html:html>
<head>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="FiltrarClienteActionForm" dynamicJavascript="false"
	/>
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript">
<!--
var bCancel = false; 

    function validateFiltrarClienteActionForm(form) {                                                                   
        if (bCancel) 
      return true; 
        else 
       return validateCaracterEspecial(form) && validateLong(form) && validateCpf(form) && validateCnpj(form); 
   } 

    function caracteresespeciais () { 
     this.aa = new Array("cpfClienteFiltro", "CPF possui caracteres especiais.", new Function ("varName", " return this[varName];"));
     this.ab = new Array("rgClienteFiltro", "RG possui caracteres especiais.", new Function ("varName", " return this[varName];"));
     this.ac = new Array("cnpjClienteFiltro", "CNPJ possui caracteres especiais.", new Function ("varName", " return this[varName];"));
     this.ad = new Array("codigoClienteFiltro", "Código possui caracteres especiais.", new Function ("varName", " return this[varName];"));
     this.ae = new Array("nomeClienteFiltro", "Nome possui caracteres especiais.", new Function ("varName", " return this[varName];"));
     this.af = new Array("nomeMaeClienteFiltro", "Nome da Mãe possui caracteres especiais.", new Function ("varName", " return this[varName];"));     
     this.ag = new Array("cepClienteFiltro", "CEP possui caracteres especiais.", new Function ("varName", " return this[varName];"));
     this.ah = new Array("municipioClienteFiltro", "Município possui caracteres especiais.", new Function ("varName", " return this[varName];"));
     this.ai = new Array("bairroClienteFiltro", "Bairro possui caracteres especiais.", new Function ("varName", " return this[varName];"));
     this.aj = new Array("logradouroClienteFiltro", "Logradouro possui caracteres especiais.", new Function ("varName", " return this[varName];"));
    } 

    function cpf () { 
     this.aa = new Array("cpfClienteFiltro", "CPF inválido.", new Function ("varName", " return this[varName];"));
    } 

    function IntegerValidations () { 
     this.aa = new Array("cpfClienteFiltro", "CPF deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
     this.ac = new Array("cnpjClienteFiltro", "CNPJ deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
     this.ad = new Array("codigoClienteFiltro", "Código deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
     this.ae = new Array("cepClienteFiltro", "CEP deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
     this.af = new Array("municipioClienteFiltro", "Município deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
     this.ag = new Array("bairroClienteFiltro", "Bairro deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
     this.ah = new Array("logradouroClienteFiltro", "Logradouro deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
    } 

    function cnpj () { 
     this.aa = new Array("cnpjClienteFiltro", "CNPJ inválido.", new Function ("varName", " return this[varName];"));
    } 
    
//Recebe os dados do(s) popup(s)
function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
	var form = document.FiltrarClienteActionForm;

	if (tipoConsulta == 'municipio') {
		form.municipioClienteFiltro.value = codigoRegistro;
		form.descricaoMunicipioClienteFiltro.value = descricaoRegistro;
		form.descricaoMunicipioClienteFiltro.style.color = "#000000";
	}
	if (tipoConsulta == 'bairro') {
		form.bairroClienteFiltro.value = codigoRegistro;
		form.descricaoBairroClienteFiltro.value = descricaoRegistro;
		form.descricaoBairroClienteFiltro.style.color = "#000000";
	}
	if (tipoConsulta == 'logradouro') {
		form.logradouroClienteFiltro.value = codigoRegistro;
		form.descricaoLogradouroClienteFiltro.value = descricaoRegistro;
		form.descricaoLogradouroClienteFiltro.style.color = "#000000";
	}
	if (tipoConsulta == 'cep') {
      limparPesquisaCep();
      form.cepClienteFiltro.value = codigoRegistro;
      form.cepDescricaoClienteFiltro.value = descricaoRegistro;
      form.cepDescricaoClienteFiltro.style.color = "#000000";
      form.cepClienteFiltro.focus();
    }
}

function limparMunicipio() {
	var form = document.FiltrarClienteActionForm;
	form.municipioClienteFiltro.value = "";
	form.descricaoMunicipioClienteFiltro.value = "";
	form.bairroClienteFiltro.value = "";
	form.descricaoBairroClienteFiltro.value = "";
    form.logradouroClienteFiltro.value = "";
    form.descricaoLogradouroClienteFiltro.value = "";
    form.cepClienteFiltro.value = "";
    form.cepDescricaoClienteFiltro.value = "";
}

function limparBairro() {
	var form = document.FiltrarClienteActionForm;
	form.bairroClienteFiltro.value = "";
	form.descricaoBairroClienteFiltro.value = "";
    form.logradouroClienteFiltro.value = "";
    form.descricaoLogradouroClienteFiltro.value = "";
    form.cepClienteFiltro.value = "";
    form.cepDescricaoClienteFiltro.value = "";
}
function limparPesquisaLogradouro(){
	var form = document.FiltrarClienteActionForm;
    form.logradouroClienteFiltro.value = "";
    form.descricaoLogradouroClienteFiltro.value = "";
    form.cepClienteFiltro.value = "";
    form.cepDescricaoClienteFiltro.value = "";
}
function limparPesquisaCep() {
    var form = document.forms[0];
    form.cepClienteFiltro.value = "";
    form.cepDescricaoClienteFiltro.value = "";
}

function validarForm(form){
	if(validateFiltrarClienteActionForm(form)){
		if( verificarExclusaoMutua() ){
			if( validarBairroMunicipio() ){
				
				//if (performanceJSP()){
					form.submit();
				//}
				
			}	
		}	
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
	var camposDependencia = [[form.municipioClienteFiltro, form.bairroClienteFiltro]];
		
	/**
	 * Mensagens sincronizadas com as duplas informadas no objeto camposDependencia
	 *
	 * EX: var camposDependencia = [[form.campo1, form.campo2],[form.campo1, form.campo3],[form.campo3, form.campo4]];
	 *     var msgDependecia = ["nomeCampo2", "nomeCampo3", "nomeCampo4"];
	 */
	var msgDependecia = ["Bairro"];
	
	/**
	 * Objetos que poderão já vir informados ou serem informados pelo usuário mas que não serão
	 * considerados como parâmetros informados (INDICADOR_USO).
	 *
	 * EX: var camposExclusao = [form.indicadorUso];
	 */
	var camposExclusao = [form.ultimoacesso, form.atualizarFiltro, form.indicadorUsoClienteFiltro[0], form.tipoPesquisa[0], form.tipoPesquisaNomeMae[0]];
	
		
	/**
	 * Caso o objeto camposDependencia seja informado, o objeto msgDependecia será obrigatório e visse-versa.
	 * Caso o objeto camposExclusao seja informado, o formulário será obrigatório e visse-versa.
	 */
	retorno = dependenciaPerformance(camposDependencia, msgDependecia, camposExclusao, form);
	
	
	return retorno;
}



function verificarExclusaoMutua() {
	var form = document.FiltrarClienteActionForm;
	if(form.cpfClienteFiltro.value != "" && form.cnpjClienteFiltro.value != ""){
		alert("CPF e CNPJ são exclusivos. Informe um ou outro.")
		return false;
	} else if (form.rgClienteFiltro.value != "" && form.cnpjClienteFiltro.value != "") {
		alert("RG e CNPJ são exclusivos. Informe um ou outro.")
		return false;
	}
	return true;
}
   



function limparForm(){
	var form = document.FiltrarClienteActionForm;
	    
	form.cpfClienteFiltro.value = "";
	form.rgClienteFiltro.value = "";
	form.cnpjClienteFiltro.value = "";
	form.codigoClienteFiltro.value = "";
	form.nomeClienteFiltro.value = "";
    form.nomeMaeClienteFiltro.value = "";
	form.cepClienteFiltro.value = "";
	form.municipioClienteFiltro.value = "";
	form.descricaoMunicipioClienteFiltro.value = "";
	form.bairroClienteFiltro.value = "";
	form.descricaoBairroClienteFiltro.value = "";
    form.logradouroClienteFiltro.value = "";
    form.descricaoLogradouroClienteFiltro.value = "";
    form.tipoPesquisa[0].checked = true;
    form.cpfClienteFiltro.focus();
}

function validarBairroMunicipio(){
	var form = document.forms[0];
	if(form.bairroClienteFiltro.value != "" && form.municipioClienteFiltro.value == ""){
		alert("Informe Município");
		form.municipioClienteFiltro.focus();
	}else{
		return true;
	}
}

function valorCheckAtualizar(){
    var form = document.forms[0];
    if(form.atualizarFiltro.checked == true){
	    form.atualizarFiltro.value = "1";
    }else{
	    form.atualizarFiltro.value = "";    
    }
}

-->
</script>
</head>
<body leftmargin="5" topmargin="5"
	onload="setarFoco('${requestScope.nomeCampo}');">

<html:form action="/filtrarClienteAction" method="post"
	onsubmit="return validateFiltrarClienteActionForm(this);">

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
			<td width="620" valign="top" class="centercoltext">
			<table height="100%">
				<tr>
					<td></td>
				</tr>
			</table>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11">
						<img border="0" src="<bean:message key="caminho.imagens"/>parahead_left.gif" />
					</td>
					<td class="parabg">Filtrar Cliente</td>
					<td width="11">
						<img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif" />
					</td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<table bordercolor="#000000" width="100%" cellspacing="0">
				<tr>
					<td colspan="2">
						<p>Para filtrar um cliente no sistema, informe os dados abaixo:</p>
					</td>
					<td align="right">
						<html:checkbox name="FiltrarClienteActionForm" property="atualizarFiltro" value="1" onclick="javacript:valorCheckAtualizar();"/><strong>Atualizar</strong>
					</td>
					<logic:present scope="application" name="urlHelp">
						<td align="right"><a href="javascript:abrirPopupHelp('${applicationScope.urlHelp}cadastroClienteFiltrar', 500, 700);"><span style="font-weight: bold"><font color="#3165CE">Ajuda</font></span></a></td>									
					</logic:present>
					<logic:notPresent scope="application" name="urlHelp">
						<td align="right"><span style="font-weight: bold"><font color=#696969><u>Ajuda</u></font></span></td>									
					</logic:notPresent>
					</tr>
	        </table>
	        
	        <table bordercolor="#000000" width="100%" cellspacing="0">
				<tr>
					<td width="20%" height="30">
						<strong>CPF:</strong>
					</td>
					<td>
						<html:text name="FiltrarClienteActionForm" property="cpfClienteFiltro" size="11" maxlength="11" />
					</td>
				</tr>
				<tr>
					<td height="30">
						<strong>RG:</strong>
					</td>
					<td>
						<html:text name="FiltrarClienteActionForm" property="rgClienteFiltro" size="13" maxlength="13" />
					</td>
				</tr>
				<tr>
					<td height="30">
						<strong>CNPJ:</strong>
					</td>
					<td>
						<html:text name="FiltrarClienteActionForm" property="cnpjClienteFiltro" size="14" maxlength="14" />
					</td>
				</tr>
				<tr>
					<td height="30">
						<strong>Código:</strong>
					</td>
					<td>
						<html:text name="FiltrarClienteActionForm" property="codigoClienteFiltro" size="9" maxlength="9" />
					</td>
				</tr>
				<tr>
					<td height="30">
						<strong>Nome:</strong>
					</td>
					<td><html:text name="FiltrarClienteActionForm" property="nomeClienteFiltro" size="50" maxlength="50" /></td>
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
					<td height="30">
						<strong>Nome da Mãe:</strong>
					</td>
					<td><html:text name="FiltrarClienteActionForm" property="nomeMaeClienteFiltro" size="50" maxlength="50" /></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td align="top">
						<html:radio property="tipoPesquisaNomeMae"
							value="<%=ConstantesSistema.TIPO_PESQUISA_INICIAL.toString()%>" />
						Iniciando pelo texto
						<html:radio property="tipoPesquisaNomeMae"
							value="<%=ConstantesSistema.TIPO_PESQUISA_COMPLETA.toString()%>" />
						Contendo o texto
					</td>
				</tr>

				<tr>
					<td height="30">
						<strong>Munic&iacute;pio:</strong>
					</td>
					<td>
						<html:text name="FiltrarClienteActionForm" property="municipioClienteFiltro" size="4" maxlength="4"
							onkeypress="javascript:limparBairro();validaEnterComMensagem(event, 'exibirFiltrarClienteAction.do?limpar=false', 'municipioClienteFiltro', 'Município');" />
						<a href="javascript:abrirPopup('exibirPesquisarMunicipioAction.do?indicadorUsoTodos=1', 400, 800);">
							<img src="<bean:message key='caminho.imagens'/>pesquisa.gif" width="23" border="0"
								height="21" style="cursor:hand;" alt="Pesquisar"></a> 
						<logic:present name="codigoMunicipioNaoEncontrado">
							<logic:equal name="codigoMunicipioNaoEncontrado" value="exception">
								<html:text property="descricaoMunicipioClienteFiltro" size="40" maxlength="30" readonly="true"
									style="background-color:#EFEFEF; border:0; color: #ff0000" />
							</logic:equal>
							<logic:notEqual name="codigoMunicipioNaoEncontrado" value="exception">
								<html:text property="descricaoMunicipioClienteFiltro" size="40" maxlength="30" readonly="true"
									style="background-color:#EFEFEF; border:0; color: #000000" />
							</logic:notEqual>
						</logic:present> 
						<logic:notPresent name="codigoMunicipioNaoEncontrado">
							<logic:empty name="FiltrarClienteActionForm" property="municipioClienteFiltro">
								<html:text property="descricaoMunicipioClienteFiltro" value="" size="40" maxlength="30" readonly="true"
									style="background-color:#EFEFEF; border:0; color: #ff0000" />
							</logic:empty>
							<logic:notEmpty name="FiltrarClienteActionForm" property="municipioClienteFiltro">
								<html:text property="descricaoMunicipioClienteFiltro" value="" size="40" maxlength="30" readonly="true"
									style="background-color:#EFEFEF; border:0; color: #000000"/>
							</logic:notEmpty>
						</logic:notPresent> 
						<a href="javascript:limparMunicipio();">
							<img src="<bean:message key="caminho.imagens"/>limparcampo.gif" border="0" title="Apagar" /></a>
					</td>
				</tr>
				<tr>
					<td height="30">
						<strong>Bairro:</strong>
					</td>
					<td>
						<html:text name="FiltrarClienteActionForm" property="bairroClienteFiltro" size="4" maxlength="4"
							onkeypress="javascript:validaEnterComMensagem(event, 'exibirFiltrarClienteAction.do?limpar=false', 'bairroClienteFiltro', 'Bairro');" />
						<a href="javascript:abrirPopup('exibirPesquisarBairroAction.do?indicadorUsoTodos=1&idMunicipio='+document.forms[0].municipioClienteFiltro.value, 400, 800);">
							<img src="<bean:message key='caminho.imagens'/>pesquisa.gif" width="23" border="0"
								height="21" onclick= alt="Pesquisar"></a>
						<logic:present name="codigoBairroNaoEncontrado">
							<logic:equal name="codigoBairroNaoEncontrado" value="exception">
								<html:text property="descricaoBairroClienteFiltro" size="40" maxlength="30" readonly="true"
									style="background-color:#EFEFEF; border:0; color: #ff0000" />
							</logic:equal>
							<logic:notEqual name="codigoBairroNaoEncontrado" value="exception">
								<html:text property="descricaoBairroClienteFiltro" size="40" maxlength="30" readonly="true"
									style="background-color:#EFEFEF; border:0; color: #000000" />
							</logic:notEqual>
						</logic:present>
						<logic:notPresent name="codigoBairroNaoEncontrado">
							<logic:empty name="FiltrarClienteActionForm" property="bairroClienteFiltro">
								<html:text property="descricaoBairroClienteFiltro" value="" size="40"
									maxlength="30" readonly="true"
									style="background-color:#EFEFEF; border:0; color: #ff0000" />
							</logic:empty>
							<logic:notEmpty name="FiltrarClienteActionForm" property="bairroClienteFiltro">
								<html:text property="descricaoBairroClienteFiltro" value="" size="40"	maxlength="30" readonly="true"
									style="background-color:#EFEFEF; border:0; color: #000000" />
							</logic:notEmpty>
						</logic:notPresent>
						<a href="javascript:limparBairro();">
							<img src="<bean:message key="caminho.imagens"/>limparcampo.gif" border="0" title="Apagar" /></a>
					</td>
				</tr>
				<tr>
					<td height="30">
						<strong>Logradouro:</strong>
					</td>
					<td>
						<html:text name="FiltrarClienteActionForm" property="logradouroClienteFiltro" size="9" maxlength="9"
							onkeypress="validaEnterComMensagem(event, 'exibirFiltrarClienteAction.do?limpar=false', 'logradouroClienteFiltro', 'Logradouro');"/>
						<a href="javascript:abrirPopup('/gsan/exibirPesquisarLogradouroAction.do?codigoMunicipio='+document.forms[0].municipioClienteFiltro.value+'&codigoBairro='+document.forms[0].bairroClienteFiltro.value+'&indicadorUsoTodos=1&primeriaVez=1', 465, 380);">	
							<img src="<bean:message key='caminho.imagens'/>pesquisa.gif" width="23" 
								height="21" border="0" alt="Pesquisar"></a>
						<logic:present name="idLogradouroNaoEncontrado">
							<logic:equal name="idLogradouroNaoEncontrado" value="exception">
								<html:text property="descricaoLogradouroClienteFiltro" size="40" maxlength="30" readonly="true"
									style="background-color:#EFEFEF; border:0; color: #ff0000" />
							</logic:equal>
							<logic:notEqual name="idLogradouroNaoEncontrado" value="exception">
								<html:text property="descricaoLogradouroClienteFiltro" size="40" maxlength="30" readonly="true"
									style="background-color:#EFEFEF; border:0; color: #000000" />
							</logic:notEqual>
						</logic:present>
						<logic:notPresent name="idLogradouroNaoEncontrado">
							<logic:empty name="FiltrarClienteActionForm" property="logradouroClienteFiltro">
								<html:text property="descricaoLogradouroClienteFiltro" value="" size="40"	maxlength="30" readonly="true"
									style="background-color:#EFEFEF; border:0; color: #ff0000" />
							</logic:empty>
							<logic:notEmpty name="FiltrarClienteActionForm" property="logradouroClienteFiltro">
								<html:text property="descricaoLogradouroClienteFiltro" value="" size="40"	maxlength="30" readonly="true"
									style="background-color:#EFEFEF; border:0; color: #000000" />
							</logic:notEmpty>
						</logic:notPresent>
						<a href="javascript:limparPesquisaLogradouro();">
							<img src="<bean:message key="caminho.imagens"/>limparcampo.gif" border="0" title="Apagar"/></a>
					</td>
				</tr>
				<tr>
					<td height="30">
						<strong>CEP:</strong>
					</td>
					<td colspan="2">
						<html:text maxlength="8" property="cepClienteFiltro" size="8" tabindex="8" 
							onkeypress="javascript:validaEnter(event, 'exibirFiltrarClienteAction.do', 'cepClienteFiltro');"/>
						<a href="javascript:abrirPopup('exibirPesquisarCepAction.do?&indicadorUsoTodos=1&idMunicipioDefinido='+document.forms[0].municipioClienteFiltro.value, 400, 800);">
							<img width="23" height="21" border="0" title="Pesquisar Cep"
								src="<bean:message key="caminho.imagens"/>pesquisa.gif"/></a> 
						<logic:present name="cepImovelNaoEncontrado">
							<logic:equal name="cepImovelNaoEncontrado" value="exception">
								<html:text property="cepDescricaoClienteFiltro" size="40" maxlength="30" readonly="true"
									style="background-color:#EFEFEF; border:0; color: #ff0000" />
							</logic:equal>
							<logic:notEqual name="cepImovelNaoEncontrado" value="exception">
								<html:text property="cepDescricaoClienteFiltro" size="40" maxlength="30" readonly="true"
									style="background-color:#EFEFEF; border:0; color: #000000" />
							</logic:notEqual>
						</logic:present> 
						<logic:notPresent name="cepImovelNaoEncontrado">
							<logic:empty name="FiltrarClienteActionForm" property="cepClienteFiltro">
								<html:text property="cepDescricaoClienteFiltro" value="" size="40" maxlength="30" readonly="true"
									style="background-color:#EFEFEF; border:0; color: #ff0000" />
							</logic:empty>
							<logic:notEmpty name="FiltrarClienteActionForm" property="cepClienteFiltro">
								<html:text property="cepDescricaoClienteFiltro" size="40" maxlength="30" readonly="true"
									style="background-color:#EFEFEF; border:0; color: #000000" />
							</logic:notEmpty>
						</logic:notPresent>
						<a href="javascript:limparPesquisaCep();">
							<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
								border="0" title="Apagar" /></a>
					</td>
				</tr>
				<tr height="30">
					<td><strong>Esfera Poder:</strong></td>
					<td colspan="2" align="left"><html:select property="idEsferaPoder">
						<html:option value="-1">&nbsp;</html:option>
						<html:options collection="colecaoEsferaPoder"
							labelProperty="descricao" property="id" />
					</html:select></td>
				</tr>
				<tr height="30">
					<td>
						<strong>Indicador de Uso:</strong>
					</td>
					<td>
						<html:radio name="FiltrarClienteActionForm" property="indicadorUsoClienteFiltro"
							value="<%=ConstantesSistema.INDICADOR_USO_ATIVO.toString()%>" />
						<strong>Ativo</strong> 
						<html:radio name="FiltrarClienteActionForm" property="indicadorUsoClienteFiltro"
							value="<%=ConstantesSistema.INDICADOR_USO_DESATIVO.toString()%>" />
						<strong>Inativo</strong>
						<html:radio name="FiltrarClienteActionForm" property="indicadorUsoClienteFiltro" value=""/>
						<strong>Todos</strong>
					</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td colspan="3">
						<table width="100%" border="0">
							<tr>
								<td align="left" width="50%">
									<input type="button" name="ButtonReset" class="bottonRightCol"
										value="Limpar" onclick="javascript:limparForm();">
								</td>
								<td align="right">
								<gsan:controleAcessoBotao name="Button" value="Filtrar"
							  onclick="javascript:validarForm(document.forms[0]);" url="filtrarClienteAction.do"/><!--
									<input type="button" name="Button" class="bottonRightCol" value="Filtrar"
										onClick="javascript:validarForm(document.forms[0])"/>-->
								</td>
							</tr>	
						</table>
					</td>
				</tr>
			</table>
			<p>&nbsp;</p>
		</td>
	</tr>
</table>

<%@ include file="/jsp/util/rodape.jsp"%>

</body>
</html:form>
</html:html>