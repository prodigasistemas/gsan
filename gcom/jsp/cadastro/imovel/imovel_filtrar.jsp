<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan"%>
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
	formName="FiltrarImovelActionForm" dynamicJavascript="false" />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>

<script>
<!-- Begin

     var bCancel = false;

    function validateFiltrarImovelActionForm(form) {
        if (bCancel)
      return true;
        else
       return testarCampoValorZero(document.FiltrarImovelActionForm.matriculaFiltro, 'Matrícula') &&
		      testarCampoValorZero(document.FiltrarImovelActionForm.idLocalidadeFiltro, 'Localidade')&&
	      testarCampoValorZero(document.FiltrarImovelActionForm.idLocalidadeFiltro, 'Setor Comercial')&&
	      testarCampoValorZero(document.FiltrarImovelActionForm.idQuadraFiltro, 'Quadra')&&
	      testarCampoValorZero(document.FiltrarImovelActionForm.idClienteFiltro, 'Cliente')&&
	      testarCampoValorZero(document.FiltrarImovelActionForm.cepFiltro, 'Cep')&&
	      testarCampoValorZero(document.FiltrarImovelActionForm.idMunicipioFiltro, 'Município') &&
	      testarCampoValorZero(document.FiltrarImovelActionForm.idBairroFiltro, 'Bairro')&&
   	      testarCampoValorZero(document.FiltrarImovelActionForm.idLogradouroFiltro, 'Logradouro')&&
          validateCaracterEspecial(form) && validateInteger(form) 
          && validateInteiroZeroPositivo(form)
          && validarBairroMunicipio() && validarNumeroImovel();
   }

    function caracteresespeciais () {
     this.aa = new Array("matriculaFiltro", "Matrícula deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
     this.ab = new Array("idLocalidadeFiltro", "Localidade deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
     this.ac = new Array("idSetorComercialFiltro", "Setor Comercial deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
     this.ad = new Array("idQuadraFiltro", "Quadra deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
     this.ae = new Array("loteFiltro", "Lote deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
///     this.af = new Array("subLoteFiltro", "SubLote deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
     this.ag = new Array("idClienteFiltro", "Cliente deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
     this.ah = new Array("cepFiltro", "Cep deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
     this.ai = new Array("idMunicipioFiltro", "Município deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
     this.aj = new Array("idBairroFiltro", "Bairro deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
     this.al = new Array("idLogradouroFiltro", "Logradouro deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
     this.am = new Array("numeroImovelInicialFiltro", "Número do Imóvel Inicial possui caracteres especiais.", new Function ("varName", " return this[varName];"));
     this.an = new Array("numeroImovelFinalFiltro", "Número do Imóvel Final possui caracteres especiais.", new Function ("varName", " return this[varName];"));
    }


    function IntegerValidations () {
     this.aa = new Array("matriculaFiltro", "Matrícula deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
     this.ab = new Array("idLocalidadeFiltro", "Localidade deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
     this.ac = new Array("idSetorComercialFiltro", "Setor Comercial deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
     this.ad = new Array("idQuadraFiltro", "Quadra deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
///     this.af = new Array("subLoteFiltro", "SubLote deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
     this.ag = new Array("idClienteFiltro", "Cliente deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
     this.ah = new Array("cepFiltro", "Cep deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
     this.ai = new Array("idMunicipioFiltro", "Municipio deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
     this.aj = new Array("idBairroFiltro", "Bairro deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
     this.al = new Array("idLogradouroFiltro", "Logradouro deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
     this.ao = new Array("numeroImovelInicialFiltro", "Número do Imóvel Inicial deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
     this.ap = new Array("numeroImovelFinalFiltro", "Número do Imóvel Final deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
    }
    
    function InteiroZeroPositivoValidations () {
     	this.am = new Array("subLoteFiltro", "SubLote deve somente conter números positivos ou zero.", new Function ("varName", " return this[varName];"));
        this.an = new Array("loteFiltro", "Lote deve somente conter números positivos ou zero.", new Function ("varName", " return this[varName];"));

    }
    
//End -->
</script>


<script>

function chamarFiltrar(){
  var form = document.forms[0];
  if (validateFiltrarImovelActionForm(form) /* && performanceJSP()*/) {
  	form.submit();
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
	var camposDependencia = [[form.idLocalidadeFiltro, form.idSetorComercialFiltro], [form.idMunicipioFiltro, form.idBairroFiltro]];
		
	/**
	 * Mensagens sincronizadas com as duplas informadas no objeto camposDependencia
	 *
	 * EX: var camposDependencia = [[form.campo1, form.campo2],[form.campo1, form.campo3],[form.campo3, form.campo4]];
	 *     var msgDependecia = ["nomeCampo2", "nomeCampo3", "nomeCampo4"];
	 */
	var msgDependecia = ["Setor Comercial", "Bairro"];
	
	/**
	 * Objetos que poderão já vir informados ou serem informados pelo usuário mas que não serão
	 * considerados como parâmetros informados (INDICADOR_USO).
	 *
	 * EX: var camposExclusao = [form.indicadorUso];
	 */
	var camposExclusao = [form.ultimoacesso, form.atualizarFiltro];
	
		
	/**
	 * Caso o objeto camposDependencia seja informado, o objeto msgDependecia será obrigatório e visse-versa.
	 * Caso o objeto camposExclusao seja informado, o formulário será obrigatório e visse-versa.
	 */
	retorno = dependenciaPerformance(camposDependencia, msgDependecia, camposExclusao, form);
	
	
	return retorno;
}

function validarBairroMunicipio(){
	var form = document.forms[0];

	if(form.idLocalidadeFiltro.value == "" && form.idSetorComercialFiltro.value  == ""){
		if(form.idBairroFiltro.value != "" && form.idMunicipioFiltro.value == ""){
			alert("Informe Município");
			form.idMunicipioFiltro.focus();
		}else{
			return true;
		}
	}else{
		return true;
	}


}

function validarNumeroImovel() {
	var form = document.forms[0];
	
	if ( (form.numeroImovelInicialFiltro.value != null && form.numeroImovelInicialFiltro.value != '') && (form.numeroImovelFinalFiltro.value == null || form.numeroImovelFinalFiltro.value == '') ) {
    	alert("Informe Número do Imóvel Final");
    	return false;
    } else if ( (form.numeroImovelFinalFiltro.value != null && form.numeroImovelFinalFiltro.value != '') && (form.numeroImovelInicialFiltro.value == null || form.numeroImovelInicialFiltro.value == '') ) {
    	alert("Informe Número do Imóvel Inicial");
    	return false;
    } else if ( (form.numeroImovelInicialFiltro.value != null && form.numeroImovelInicialFiltro.value != '') && (form.numeroImovelFinalFiltro.value != null && form.numeroImovelFinalFiltro.value != '') && (form.idLogradouroFiltro.value == null || form.idLogradouroFiltro.value == '') ) {
    	alert("Informe Logradouro");
    	return false;
    } else {
    	return true;
    }
}

function pesquisaEnter(tecla) {

  var form = document.forms[0];

  if (document.all) {
    var codigo = event.keyCode;
  } else {
    var codigo = tecla.which;
  }
  if(form.idLocalidadeFiltro.value != '' || form.idLocalidadeFiltro.value != ''
     || form.idQuadraFiltro.value != '' || form.idClienteFiltro.value != '' || form.idBairroFiltro.value != ''
     || form.idMunicipioFiltro.value != '' || form.cepFiltro.value != '' || form.idLogradouroFiltro.value != ''){
    if (codigo == 13) {
      form.action = "exibirFiltrarImovelAction.do";
      form.submit();
    } else {
      return true;
    }
  }
}

  //Recebe os dados do(s) popup(s)

  function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {

    var form = document.forms[0];

    if (tipoConsulta == 'localidade') {
      limparPesquisaLocalidade();
      form.idLocalidadeFiltro.value = codigoRegistro;
      form.localidadeDescricaoFiltro.value = descricaoRegistro;
      form.localidadeDescricaoFiltro.style.color = "#000000";
      form.idLocalidadeFiltro.focus();
    }

    if (tipoConsulta == 'cep') {
      limparPesquisaCep();
      form.cepFiltro.value = codigoRegistro;
      form.cepDescricaoFiltro.value = descricaoRegistro;
      form.cepDescricaoFiltro.style.color = "#000000";
      form.cepFiltro.focus();
    }


    if (tipoConsulta == 'quadra') {
      limparPesquisaQuadra();
      form.idQuadraFiltro.value = codigoRegistro;

      form.loteFiltro.focus();
    }

    if (tipoConsulta == 'setorComercial') {
      limparPesquisaSetorComercial();
      limparPesquisaBairro();      
      form.idSetorComercialFiltro.value = codigoRegistro;
      form.setorComercialDescricaoFiltro.value = descricaoRegistro;
      form.setorComercialDescricaoFiltro.style.color = "#000000";
      form.idMunicipioFiltro.disabled = false;
      form.idQuadraFiltro.focus();
    }

    if (tipoConsulta == 'cliente') {
      limparPesquisaCliente();
      form.idClienteFiltro.value = codigoRegistro;
      form.nomeClienteFiltro.value = descricaoRegistro;
      form.nomeClienteFiltro.style.color = "#000000";
      form.cepFiltro.focus();
    }

    if (tipoConsulta == 'bairro') {
      limparPesquisaBairro();
      form.idBairroFiltro.value = codigoRegistro;
      form.bairroFiltro.value = descricaoRegistro;
      form.bairroFiltro.style.color = "#000000";
      form.idLogradouroFiltro.focus();
    }

    if (tipoConsulta == 'municipio') {
      limparPesquisaMunicipio();
      limparPesquisaSetorComercial();      
      form.idMunicipioFiltro.value = codigoRegistro;
      form.municipioFiltro.value = descricaoRegistro;
      form.municipioFiltro.style.color = "#000000";
      form.idBairroFiltro.focus();
    }
    if (tipoConsulta == 'logradouro') {
      limparPesquisaLogradouro();
      form.idLogradouroFiltro.value = codigoRegistro;
      form.logradouroFiltro.value = descricaoRegistro;
      form.logradouroFiltro.style.color = "#000000";
      form.idLogradouroFiltro.focus();
    }
    
  }

function limparPesquisaLocalidade() {
    var form = document.forms[0];

      form.idLocalidadeFiltro.value = "";
      form.localidadeDescricaoFiltro.value = "";


  }

function limparPesquisaQuadra() {
    var form = document.forms[0];

      form.idQuadraFiltro.value = "";
	var msgQuadra = document.getElementById("msgQuadra");
	
	if (msgQuadra != null){
		msgQuadra.innerHTML = "";
	}


  }

function limparPesquisaSetorComercial() {
    var form = document.forms[0];

      form.idSetorComercialFiltro.value = "";
      form.setorComercialDescricaoFiltro.value = "";
      form.idQuadraFiltro.value = "";
      form.idMunicipioFiltro.disabled = false;
      form.idMunicipioFiltro.value = "";
      form.municipioFiltro.value = "";


  }

function limparPesquisaCliente() {
    var form = document.forms[0];

      form.idClienteFiltro.value = "";
      form.nomeClienteFiltro.value = "";


  }
function limparPesquisaCep() {
    var form = document.forms[0];

      form.cepFiltro.value = "";
      form.cepDescricaoFiltro.value = "";


  }

function limparPesquisaCepDescricao() {
    var form = document.forms[0];

      form.cepDescricaoFiltro.value = "";


  }

function limparPesquisaBairroDescricao(){
    var form = document.forms[0];

      form.bairroFiltro.value = "";

}

function limparPesquisaBairro() {
    var form = document.forms[0];

      form.idBairroFiltro.value = "";
      form.bairroFiltro.value = "";


  }

function limparPesquisaMunicipio() {
    var form = document.forms[0];

      form.idMunicipioFiltro.value = "";
      form.municipioFiltro.value = "";


  }

function limparPesquisaLogradouro(){
    var form = document.forms[0];

    form.idLogradouroFiltro.value = "";
    form.logradouroFiltro.value = "";

}

function limparPesquisaLogradouroDescricao(){
    var form = document.forms[0];

    form.logradouroFiltro.value = "";

}


function verificarSituacaoMunicipioPesquisarMunicipio(){
	var form = document.forms[0];
	if(form.idMunicipioFiltro.disabled == false){
    	limparPesquisaBairro();
		abrirPopup('exibirPesquisarMunicipioAction.do?indicadorUsoTodos=1', 400, 800);
	}

}

function verificarSituacaoMunicipioLimparMunicipio(){

	var form = document.forms[0];
	if(form.idMunicipioFiltro.disabled == false){
		limparPesquisaMunicipio();
		limparPesquisaBairro();
		limparPesquisaLogradouro();
		limparPesquisaCep();
	}

}

function habilitaMunicipio(){
	var form = document.forms[0];
	form.idMunicipioFiltro.disabled = false;

}


function verificarDigitosSetorComercial(){
	var form = document.forms[0];

    if (form.idSetorComercialFiltro.value.length > 0){
      form.idMunicipioFiltro.disabled = false;
    }else{
      form.idMunicipioFiltro.disabled = false;
    }
}

function valorExistenciaLocalidade(){
	
   var form = document.forms[0];
   if(form.idLocalidadeFiltro.value == ''){
   		form.idLocalidadeFiltro.value = "";
  		alert("Informe Localidade") ;
   		form.idLocalidadeFiltro.value = "";  		
   }
}  

function valorExistenciaMunicipio(){
	
   var form = document.forms[0];
   if(form.idMunicipioFiltro.value == ''){
///  		alert("Informe Município") ;
////  		form.idBairroFiltro.value = "";
   }
}  

function valorExistenciaSetorComercial(){
   var form = document.forms[0];
   if(form.idLocalidadeFiltro.value == ''){
   		form.idQuadraFiltro.value = "";
  		alert("Informe Setor Comercial") ;
   }
}  


function limparDescricaoQuadra(){
	var msgQuadra = document.getElementById("msgQuadra");
	
	if (msgQuadra != null){
		msgQuadra.innerHTML = "";
	}
}

function limparDescricaoLocalidade(){
    var form = document.forms[0];
    form.localidadeDescricaoFiltro.value = "";

}
function limparDescricaoSetorComercial(){
    var form = document.forms[0];
    form.setorComercialDescricaoFiltro.value = "";
    limparPesquisaMunicipio();
    verificarDigitosSetorComercial();


}

	function limparForm(form) {
	    form.matriculaFiltro.value = "";
		limparPesquisaLocalidade();
		limparPesquisaQuadra();
		limparPesquisaSetorComercial();
		limparPesquisaCliente();
		limparPesquisaBairro();
		limparPesquisaMunicipio();
		limparPesquisaLogradouro();	
		limparPesquisaCep();
	    form.loteFiltro.value = "";
    	form.subLoteFiltro.value = "";
	    form.cepFiltro.value = "";
	    form.numeroImovelInicialFiltro.value = "";
	    form.numeroImovelFinalFiltro.value = "";
	}

function valorCheckAtualizar(){
    var form = document.forms[0];
    if(form.atualizarFiltro.checked == true){
	    form.atualizarFiltro.value = "1";
    }else{
	    form.atualizarFiltro.value = "";    
    }
}


</script>
</head>

<body leftmargin="5" topmargin="5"
	onload="javascript:verificarDigitosSetorComercial();setarFoco('${requestScope.nomeCampo}');">

<html:form action="/filtrarImovelAction" method="post"
	onsubmit="return validateFiltrarImovelActionForm(this);">


	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>

	<table width="770" border="0" cellspacing="5" cellpadding="0">
		<tr>
			<td width="150" valign="top" class="leftcoltext">
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
			<td width="660" valign="top" class="centercoltext">
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
					<td class="parabg">Filtrar Imóvel</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>
					<td>Para filtrar o(s) im&oacute;vel(is), informe os dados abaixo:</td>
					<td align="right"><html:checkbox property="atualizarFiltro"
						value="1" onclick="javacript:valorCheckAtualizar();" /><strong>Atualizar</strong></td>
					<logic:present scope="application" name="urlHelp">
						<td align="right"><a href="javascript:abrirPopupHelp('${applicationScope.urlHelp}cadastroImovelFiltrar', 500, 700);"><span style="font-weight: bold"><font color="#3165CE">Ajuda</font></span></a></td>									
					</logic:present>
					<logic:notPresent scope="application" name="urlHelp">
						<td align="right"><span style="font-weight: bold"><font color=#696969><u>Ajuda</u></font></span></td>									
					</logic:notPresent>
				</tr>
			</table>
			<table width="100%" border="0">
				<tr>
					<td height="24"><strong>Matrícula:</strong></td>
					<td colspan="2">
						<html:text maxlength="10"
								property="matriculaFiltro" 
								size="10" 
								tabindex="1" 
								onkeypress="return isCampoNumerico(event);"/></td>
				</tr>
				<tr>
					<td width="19%"><strong>Localidade:</strong></td>
					<td colspan="2" width="81%" height="24"><html:text maxlength="3"
						tabindex="1" property="idLocalidadeFiltro" size="3"
						onkeypress="javascript:limparDescricaoLocalidade();limparPesquisaQuadra();limparPesquisaSetorComercial(); validaEnter(event, 'exibirFiltrarImovelAction.do', 'idLocalidadeFiltro'); return isCampoNumerico(event);" />
					<a
						href="javascript:abrirPopup('exibirPesquisarLocalidadeAction.do?tipo=imovelLocalidade&indicadorUsoTodos=1', 400, 800);limparPesquisaQuadra();limparPesquisaSetorComercial();">
					<img width="23" height="21" border="0"
						src="<bean:message key="caminho.imagens"/>pesquisa.gif"
						title="Pesquisar Localidade" /></a> <logic:present
						name="idLocalidadeNaoEncontrada">
						<logic:equal name="idLocalidadeNaoEncontrada" value="exception">
							<html:text property="localidadeDescricaoFiltro" size="40"
								maxlength="30" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:equal>
						<logic:notEqual name="idLocalidadeNaoEncontrada" value="exception">
							<html:text property="localidadeDescricaoFiltro" size="40"
								maxlength="30" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEqual>
					</logic:present> <logic:notPresent name="idLocalidadeNaoEncontrada">
						<logic:empty name="FiltrarImovelActionForm"
							property="idLocalidadeFiltro">
							<html:text property="localidadeDescricaoFiltro" value=""
								size="40" maxlength="30" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:empty>
						<logic:notEmpty name="FiltrarImovelActionForm"
							property="idLocalidadeFiltro">
							<html:text property="localidadeDescricaoFiltro" size="40"
								maxlength="30" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEmpty>

					</logic:notPresent> <a
						href="javascript:limparPesquisaLocalidade();limparPesquisaSetorComercial();limparPesquisaQuadra();">
					<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar" /></a></td>
				</tr>
				<tr>
					<td><strong>Setor Comercial:</strong></td>
					<td colspan="2" height="24"><html:text maxlength="3"
						property="idSetorComercialFiltro" tabindex="2" size="3"
						onkeypress="habilitaMunicipio();limparPesquisaBairro();limparPesquisaQuadra();validaEnterDependencia(event, 'exibirFiltrarImovelAction.do', this, document.forms[0].idLocalidadeFiltro.value, 'Localidade');return isCampoNumerico(event);"

						onkeyup="javascript:verificarDigitosSetorComercial();limparDescricaoSetorComercial();" /> <a
						href="javascript:abrirPopupDependencia('exibirPesquisarSetorComercialAction.do?idLocalidade='+document.forms[0].idLocalidadeFiltro.value+'&tipo=SetorComercial&indicadorUsoTodos=1',document.forms[0].idLocalidadeFiltro.value,'Localidade', 400, 800);limparPesquisaQuadra();">
					<img width="23" height="21" border="0"
						src="<bean:message key="caminho.imagens"/>pesquisa.gif"
						title="Pesquisar Setor Comercial" /></a> <logic:present
						name="idSetorComercialNaoEncontrada">
						<logic:equal name="idSetorComercialNaoEncontrada"
							value="exception">
							<html:text property="setorComercialDescricaoFiltro" size="40"
								maxlength="30" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:equal>
						<logic:notEqual name="idSetorComercialNaoEncontrada"
							value="exception">
							<html:text property="setorComercialDescricaoFiltro" size="40"
								maxlength="30" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEqual>
					</logic:present> <logic:notPresent
						name="idSetorComercialNaoEncontrada">
						<logic:empty name="FiltrarImovelActionForm"
							property="idSetorComercialFiltro">
							<html:text property="setorComercialDescricaoFiltro" value=""
								size="40" maxlength="30" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:empty>
						<logic:notEmpty name="FiltrarImovelActionForm"
							property="idSetorComercialFiltro">
							<html:text property="setorComercialDescricaoFiltro" size="40"
								maxlength="30" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEmpty>

					</logic:notPresent> <a
						href="javascript:limparPesquisaSetorComercial();limparPesquisaQuadra();">
					<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar" /></a></td>
				</tr>
				<tr>
					<td><strong>Quadra:</strong></td>
					<td colspan="2" height="24"><html:text maxlength="4"
						property="idQuadraFiltro" tabindex="3" size="3"
						
						onkeypress="javascript:limparDescricaoQuadra(); return isCampoNumerico(event);return validaEnterDependencia(event, 'exibirFiltrarImovelAction.do', this, document.forms[0].idLocalidadeFiltro.value, 'Setor Comercial');"   />
					<logic:present name="codigoQuadraNaoEncontrada" scope="request">
						<span style="color:#ff0000" id="msgQuadra"><bean:write
							scope="request" name="msgQuadra" /></span>
					</logic:present> <logic:notPresent name="codigoQuadraNaoEncontrada"
						scope="request">
					</logic:notPresent></td>
				</tr>
				<tr>
					<td height="24"><strong>Lote:</strong></td>
					<td colspan="2">
						<html:text maxlength="4" 
								property="loteFiltro"
								size="4" 
								tabindex="5" 
								onkeypress="return isCampoNumerico(event);"/></td>
				</tr>
				<tr>
					<td height="24"><strong>Sublote:</strong></td>
					<td colspan="2"><html:text maxlength="3" 
							property="subLoteFiltro"
							size="4" 
							tabindex="6" 
							onkeypress="return isCampoNumerico(event);"/></td>
				</tr>
				<tr>
					<td><strong>Cliente:</strong></td>
					<td colspan="2" height="24"><html:text maxlength="9"
						property="idClienteFiltro" tabindex="6" size="10"
						onkeypress="javascript:validaEnter(event, 'exibirFiltrarImovelAction.do', 'idClienteFiltro');return isCampoNumerico(event); " />
					<a
						href="javascript:abrirPopup('exibirPesquisarClienteAction.do?indicadorUsoTodos=1&limparForm=OK', 400, 800);">
					<img width="23" height="21" border="0"
						src="<bean:message key="caminho.imagens"/>pesquisa.gif"
						title="Pesquisar Cliente" /></a> <logic:present
						name="idClienteNaoEncontrado">
						<logic:equal name="idClienteNaoEncontrado" value="exception">
							<html:text property="nomeClienteFiltro" size="50" maxlength="50"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:equal>
						<logic:notEqual name="idClienteNaoEncontrado" value="exception">
							<html:text property="nomeClienteFiltro" size="50" maxlength="50"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEqual>
					</logic:present> <logic:notPresent name="idClienteNaoEncontrado">
						<logic:empty name="FiltrarImovelActionForm"
							property="idClienteFiltro">
							<html:text property="nomeClienteFiltro" value="" size="50"
								maxlength="50" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:empty>
						<logic:notEmpty name="FiltrarImovelActionForm"
							property="idClienteFiltro">
							<html:text property="nomeClienteFiltro" size="50" maxlength="50"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEmpty>

					</logic:notPresent> <a href="javascript:limparPesquisaCliente();">
					<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar" /></a></td>
				</tr>
				<tr>
					<td height="24"><strong>Município:</strong></td>
					<td colspan="2"><html:text maxlength="4" tabindex="7"
						property="idMunicipioFiltro" size="4"
						onkeypress="limparPesquisaBairro();limparPesquisaLogradouro();limparPesquisaCep();javascript:validaEnter(event, 'exibirFiltrarImovelAction.do', 'idMunicipioFiltro');return isCampoNumerico(event);" />
					<a
						href="javascript:verificarSituacaoMunicipioPesquisarMunicipio();">
					<img width="23" height="21" border="0"
						src="<bean:message key="caminho.imagens"/>pesquisa.gif"
						title="Pesquisar Municipio" /></a> <logic:present
						name="idMunicipioFiltroImovelNaoEncontrado">
						<logic:equal name="idMunicipioFiltroImovelNaoEncontrado"
							value="exception">
							<html:text property="municipioFiltro" size="40" maxlength="30"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:equal>
						<logic:notEqual name="idMunicipioFiltroImovelNaoEncontrado"
							value="exception">
							<html:text property="municipioFiltro" size="40" maxlength="30"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEqual>
					</logic:present> <logic:notPresent
						name="idMunicipioFiltroImovelNaoEncontrado">
						<logic:empty name="FiltrarImovelActionForm"
							property="idMunicipioFiltro">
							<html:text property="municipioFiltro" value="" size="40"
								maxlength="30" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:empty>
						<logic:notEmpty name="FiltrarImovelActionForm"
							property="idMunicipioFiltro">
							<html:text property="municipioFiltro" size="40" maxlength="30"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEmpty>
					</logic:notPresent> <a
						href="javascript:verificarSituacaoMunicipioLimparMunicipio();"> <img
						src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar" /></a></td>
				</tr>
				<tr>
					<td><strong>Bairro:</strong></td>
					<td height="24" colspan="2"><html:text maxlength="4"
						property="idBairroFiltro" size="4" tabindex="8"
						onkeypress="javascript:limparPesquisaBairroDescricao();limparPesquisaLogradouro();limparPesquisaCep();validaEnterDependencia(event, 'exibirFiltrarImovelAction.do', this, document.forms[0].idMunicipioFiltro.value, 'Município');return isCampoNumerico(event);return isCampoNumerico(event);" />
					<a
						href="javascript:abrirPopup('exibirPesquisarBairroAction.do?idMunicipio='+document.forms[0].idMunicipioFiltro.value+'&indicadorUsoTodos=1', 400, 800);">
					<img width="23" height="21" border="0"
						src="<bean:message key="caminho.imagens"/>pesquisa.gif"
						title="Pesquisar Bairro" /></a> <logic:present
						name="codigoBairroImovelNaoEncontrado">
						<logic:equal name="codigoBairroImovelNaoEncontrado"
							value="exception">
							<html:text property="bairroFiltro" size="40" maxlength="30"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:equal>
						<logic:notEqual name="codigoBairroImovelNaoEncontrado"
							value="exception">
							<html:text property="bairroFiltro" size="40" maxlength="30"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEqual>
					</logic:present> <logic:notPresent
						name="codigoBairroImovelNaoEncontrado">
						<logic:empty name="FiltrarImovelActionForm"
							property="idBairroFiltro">
							<html:text property="bairroFiltro" value="" size="40"
								maxlength="30" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:empty>
						<logic:notEmpty name="FiltrarImovelActionForm"
							property="idBairroFiltro">
							<html:text property="bairroFiltro" size="40" maxlength="30"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEmpty>
					</logic:notPresent> <a
						href="javascript:limparPesquisaBairro();limparPesquisaLogradouro();limparPesquisaCep();">
					<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar" /></a></td>
				</tr>
				<tr>
					<td height="24"><strong>Logradouro:</strong></td>
					<td colspan="2"><html:text property="idLogradouroFiltro" size="9"
						maxlength="9" tabindex="9"
						onkeypress="javascript:limparPesquisaLogradouroDescricao();limparPesquisaCep();validaEnter(event, 'exibirFiltrarImovelAction.do', 'idLogradouroFiltro');return isCampoNumerico(event);" />
					<a
						href="javascript:abrirPopup('exibirPesquisarLogradouroAction.do?codigoMunicipio='+document.forms[0].idMunicipioFiltro.value+'&codigoBairro='+document.forms[0].idBairroFiltro.value+'&indicadorUsoTodos=1&primeriaVez=1', 400, 800);"><img
						src="<bean:message key='caminho.imagens'/>pesquisa.gif" width="23"
						height="21" border="0" style="cursor:hand;" alt="Pesquisar"></a> <logic:present
						name="idLogradouroNaoEncontrado">
						<logic:equal name="idLogradouroNaoEncontrado" value="exception">
							<html:text property="logradouroFiltro" size="40" maxlength="30"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:equal>
						<logic:notEqual name="idLogradouroNaoEncontrado" value="exception">
							<html:text property="logradouroFiltro" size="40" maxlength="30"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEqual>
					</logic:present> <logic:notPresent name="idLogradouroNaoEncontrado">
						<logic:empty name="FiltrarImovelActionForm"
							property="logradouroFiltro">
							<html:text property="logradouroFiltro" value="" size="40"
								maxlength="30" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:empty>
						<logic:notEmpty name="FiltrarImovelActionForm"
							property="logradouroFiltro">
							<html:text property="logradouroFiltro" size="40" maxlength="30"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEmpty>
					</logic:notPresent> <a
						href="javascript:limparPesquisaLogradouro();limparPesquisaCep();">
					<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar" /></a></td>
				</tr>
				<tr>
					<td height="24"><strong>Número do Imóvel:</strong></td>
					<td colspan="2"><html:text maxlength="5" property="numeroImovelInicialFiltro"
											size="6" 
											tabindex="9" 
											onkeypress="return isCampoNumerico(event);"
											onkeyup="replicarCampo(form.numeroImovelFinalFiltro, form.numeroImovelInicialFiltro);" /> a <html:text maxlength="5" property="numeroImovelFinalFiltro" onkeypress="return isCampoNumerico(event);"
											size="6" tabindex="9" /></td>
				</tr>
				<tr>
					<td height="24"><strong>Cep:</strong></td>
					<td colspan="2"><html:text maxlength="8" property="cepFiltro"
						size="8" tabindex="10"
						onkeypress="javascript:limparPesquisaCepDescricao();validaEnter(event, 'exibirFiltrarImovelAction.do', 'cepFiltro');return isCampoNumerico(event);" />

					<a
						href="javascript:abrirPopup('exibirPesquisarCepAction.do?&indicadorUsoTodos=1&idMunicipioDefinido='+document.forms[0].idMunicipioFiltro.value , 400, 800);">
					<img width="23" height="21" border="0"
						src="<bean:message key="caminho.imagens"/>pesquisa.gif"
						title="Pesquisar Cep" /></a> <logic:present
						name="cepImovelNaoEncontrado">
						<logic:equal name="cepImovelNaoEncontrado" value="exception">
							<html:text property="cepDescricaoFiltro" size="40" maxlength="30"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:equal>
						<logic:notEqual name="cepImovelNaoEncontrado" value="exception">
							<html:text property="cepDescricaoFiltro" size="40" maxlength="30"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEqual>
					</logic:present> <logic:notPresent name="cepImovelNaoEncontrado">
						<logic:empty name="FiltrarImovelActionForm" property="cepFiltro">
							<html:text property="cepDescricaoFiltro" value="" size="40"
								maxlength="30" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:empty>
						<logic:notEmpty name="FiltrarImovelActionForm"
							property="cepFiltro">
							<html:text property="cepDescricaoFiltro" size="40" maxlength="30"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEmpty>
					</logic:notPresent> <a href="javascript:limparPesquisaCep();"> <img
						src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar" /></a></td>


				</tr>

				<tr>
					<td align="left"><input type="button" name="ButtonReset"
						class="bottonRightCol" value="Limpar"
						onclick="javascript:limparForm(document.forms[0]);"></td>
					<td colspan="2" height="24" align="right"><gsan:controleAcessoBotao
						name="Button" value="Filtrar"
						onclick="javascript:chamarFiltrar();" url="filtrarImovelAction.do" /><!--
					<input type="button" class="bottonRightCol"
						value="Filtrar" onclick="javascript:chamarFiltrar();" />--></td>
					<td></td>
				</tr>
				<tr>
					<td height="0" colspan="3">
					<div align="right"></div>
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
