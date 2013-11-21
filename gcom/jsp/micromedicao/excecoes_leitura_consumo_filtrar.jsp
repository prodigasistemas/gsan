<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<%@ page import="gcom.util.ConstantesSistema" isELIgnored="false"%>

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

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="LeituraConsumoActionForm"
	dynamicJavascript="false" />

<script language="JavaScript">
<!-- Begin

     var bCancel = false;

    function validateLeituraConsumoActionForm(form) {
        if (bCancel)
      return true;
        else
       return testarCampoValorZero(document.LeituraConsumoActionForm.imovelFiltro, 'Mátricula do Imóvel') 
       && testarCampoValorZero(document.LeituraConsumoActionForm.imovelCondominioFiltro, 'Mat. do Imóvel Condomínio')
       && testarCampoValorZero(document.LeituraConsumoActionForm.localidadeFiltro, 'Localidade') 
       && testarCampoValorZero(document.LeituraConsumoActionForm.setorComercialFiltro, 'Setor Comercial')
       && testarCampoValorZero(document.LeituraConsumoActionForm.quadraInicialFiltro, 'Quadra Inicial')
       && testarCampoValorZero(document.LeituraConsumoActionForm.quadraFinalFiltro, 'Quadra Final')
       && testarCampoValorZero(document.LeituraConsumoActionForm.rotaFiltro, 'Rota')
       && validateCaracterEspecial(form)
       && validateLong(form) && validaQuadra() && validateRequired(form) && verificaAnoMes(form.mesAno);
   }

    function caracteresespeciais () {
     this.ae = new Array("imovelFiltro", "Mátricula do Imóvel possui caracteres especiais.", new Function ("varName", " return this[varName];"));
     this.af = new Array("imovelCondominioFiltro", "Mat. do Imóvel Condomínio possui caracteres especiais.", new Function ("varName", " return this[varName];"));
     this.aa = new Array("localidadeFiltro", "Localidade possui caracteres especiais.", new Function ("varName", " return this[varName];"));
     this.ab = new Array("setorComercialFiltro", "Setor Comercial possui caracteres especiais.", new Function ("varName", " return this[varName];"));
     this.ac = new Array("quadraInicialFiltro", "Quadra Inicial possui caracteres especiais.", new Function ("varName", " return this[varName];"));
     this.ad = new Array("quadraFinalFiltro", "Quadra Final possui caracteres especiais.", new Function ("varName", " return this[varName];"));
     this.ag = new Array("rotaFiltro", "Rota possui caracteres especiais.", new Function ("varName", " return this[varName];"));
    }

    function IntegerValidations () {
     this.aj = new Array("imovelFiltro", "Mátricula do Imóvel deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
     this.al = new Array("imovelCondominioFiltro", "Mat. do Imóvel Condomínio deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
     this.ae = new Array("localidadeFiltro", "Localidade deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
     this.ag = new Array("setorComercialFiltro", "Setor Comercial deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
     this.ah = new Array("quadraInicialFiltro", "Quadra Inicial deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
     this.ai = new Array("quadraFinalFiltro", "Quadra Final deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
     this.ad = new Array("rotaFiltro", "Rota deve conter apenas números.", new Function ("varName", " return this[varName];"));
    }
    
    function required () {
	 this.aj = new Array("idGrupoFaturamentoFiltro", "Informe Grupo de Faturamento.", new Function ("varName", " return this[varName];"));
	 this.al = new Array("mesAno", "Informe Mês/Ano.", new Function ("varName", " return this[varName];"));
	}

 function validaQuadra(){
 	var form = document.forms[0];
 	if(form.setorComercialFiltro.value == "" && (form.quadraInicialFiltro.value != "" || form.quadraFinalFiltro.value != "")){
 		alert("Informe Setor Comercial.");
 		return false;
 	}else{
 		
	 	if((form.quadraInicialFiltro.value * 1) > (form.quadraFinalFiltro.value * 1)){
	 		alert("A Quadra Inicial deve ser menor ou igual à Quadra Final.");
	 		return false;
	 	}else{
	 		if(form.quadraInicialFiltro.value == "" && form.quadraFinalFiltro.value != ""){
	 			alert("Informe Quadra Inicial.");
	 			return false;
	 		}else if(form.quadraInicialFiltro.value != "" && form.quadraFinalFiltro.value == ""){
	 			alert("Informe Quadra Final.");
	 			return false;
	 		}else{
	 			return true;
	 		}
	 	}
 	}
 }
//End -->
</script>


<SCRIPT LANGUAGE="JavaScript">
<!--
function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {

	var form = document.LeituraConsumoActionForm;
	
	if (tipoConsulta == 'localidade') {
      form.localidadeFiltro.value = codigoRegistro;
	  form.nomeLocalidade.value = descricaoRegistro;
	  form.nomeLocalidade.style.color = "#000000";
	}
	
	if (tipoConsulta == 'setorComercial') {
      form.setorComercialFiltro.value = codigoRegistro;
	  form.setorComercialNome.value = descricaoRegistro;
	  form.setorComercialNome.style.color = "#000000";
	}
	
	if (tipoConsulta == 'quadraInicial') {
      form.quadraInicialFiltro.value = codigoRegistro;
	  form.quadraInicialID.value = idRegistro;
	  form.quadraFinalFiltro.value = codigoRegistro;
	  form.quadraFinalID.value = idRegistro;
    }

	if (tipoConsulta == 'quadraFinal') {
      form.quadraFinalFiltro.value = codigoRegistro;
	  form.quadraFinalID.value = idRegistro;
	}

	if (tipoConsulta == 'imovel') {
      form.imovelFiltro.value = codigoRegistro;
      form.action = "exibirFiltrarExcecoesLeiturasConsumosAction.do";
      submeterFormPadrao(form);
    }
    
    if (tipoConsulta == 'imovelCondominio') {
      form.imovelCondominioFiltro.value = codigoRegistro;
      form.action = "exibirFiltrarExcecoesLeiturasConsumosAction.do";
      submeterFormPadrao(form);
    }
    
    if (tipoConsulta == 'rota') {
      form.rotaFiltro.value = codigoRegistro;
      form.action = "exibirFiltrarExcecoesLeiturasConsumosAction.do";
      submeterFormPadrao(form);    
    }
    
    if (tipoConsulta == 'usuario') {
		form.idUsuarioAlteracao.value = codigoRegistro;
		form.loginUsuarioAlteracao.value = "";
		form.nomeUsuarioAlteracao.value = "";
		form.action= 'exibirFiltrarExcecoesLeiturasConsumosAction.do?objetoConsulta=5&inscricaoTipo=origem';
		form.submit();
    }
}

function recuperarDadosQuatroParametros(idRegistro, descricaoRegistro, codigoRegistro, tipoConsulta) {

	var form = document.LeituraConsumoActionForm;
	
	if (tipoConsulta == 'quadraOrigem') {
      form.quadraInicialFiltro.value = codigoRegistro;
	  form.quadraInicialID.value = idRegistro;
	  form.quadraInicialNome.value = descricaoRegistro;
	  form.quadraInicialNome.style.color = "#000000";
	  form.quadraFinalFiltro.value = codigoRegistro;
	  form.quadraFinalID.value = idRegistro;
	  form.quadraFinalNome.value = descricaoRegistro;
	  form.quadraFinalNome.style.color = "#000000";
    }

	if (tipoConsulta == 'quadraDestino') {
      form.quadraFinalFiltro.value = codigoRegistro;
	  form.quadraFinalID.value = idRegistro;
	  form.quadraFinalNome.value = descricaoRegistro;
	  form.quadraFinalNome.style.color = "#000000";
	}
}

function limparPesquisaLocalidade() {
    var form = document.forms[0];

      form.localidadeFiltro.value = "";
      form.nomeLocalidade.value = "";
      form.localidadeFiltro.focus();

  }

function limparPesquisaQuadra() {
    var form = document.forms[0];

      form.quadraInicialFiltro.value = "";
      form.quadraFinalFiltro.value = "";
      form.quadraInicialFiltro.focus();
  }

function limparPesquisaQuadraInicial() {
    var form = document.forms[0];

      form.quadraInicialFiltro.value = "";
      form.quadraFinalFiltro.value = "";
      form.quadraInicialFiltro.focus();
  }
  
function limparPesquisaQuadraFinal() {
    var form = document.forms[0];

      form.quadraFinalFiltro.value = "";
      form.quadraFinalFiltro.focus();
  }  
  
function limparPesquisaRota() {
    var form = document.forms[0];

      form.rotaFiltro.value = "";
      form.rotaFiltro.focus();
  }    

function limparPesquisaSetorComercial() {
    var form = document.forms[0];

      form.setorComercialFiltro.value = "";
      form.setorComercialNome.value = "";
      form.setorComercialNome.focus();

  }
  
  function limparPesquisaLocalidadeSemFocus() {
    var form = document.forms[0];

      form.localidadeFiltro.value = "";
      form.nomeLocalidade.value = "";

  }

function limparPesquisaQuadraSemFocus() {
    var form = document.forms[0];

      form.quadraInicialFiltro.value = "";
      form.quadraFinalFiltro.value = "";
  }

function limparPesquisaQuadraInicialSemFocus() {
    var form = document.forms[0];

      form.quadraInicialFiltro.value = "";
      form.quadraFinalFiltro.value = "";
  }
  
function limparPesquisaQuadraFinalSemFocus() {
    var form = document.forms[0];

      form.quadraFinalFiltro.value = "";
  }  

function limparPesquisaSetorComercialSemFocus() {
    var form = document.forms[0];

      form.setorComercialFiltro.value = "";
      form.setorComercialNome.value = "";
  }
  
  function limparUsuario() {
  
    var form = document.forms[0];

	form.idUsuarioAlteracao.value = "";
	form.loginUsuarioAlteracao.value = "";
    form.nomeUsuarioAlteracao.value = "";
  }
  
  function limparUsuarioTecla() {
	var form = document.forms[0];

	form.idUsuarioAlteracao.value = "";
    form.nomeUsuarioAlteracao.value = "";
  }
  
 function limparForm(){
 	
 	var form = document.forms[0];
 	
 	limparPesquisaSetorComercial();
 	limparPesquisaQuadraFinal();
 	limparPesquisaQuadraInicial();
 	limparPesquisaRota();
 	limparPesquisaLocalidade();
 	limparPesquisaLocalidade();
	limparPesquisaImovelPrincipal();
	limparPesquisaImovelCondominio();
	form.idGrupoFaturamentoFiltro.value = "";
	form.idEmpresaFiltro.value = "-1";
	form.idUsuarioAlteracao.value = "";
	form.loginUsuarioAlteracao.value = "";
	form.nomeUsuarioAlteracao.value = "";
	form.indicadorAnalisado[2].checked = true;
	form.indicadorImovelCondominioFiltro[2].checked = true;
	form.indicadorDebitoAutomatico[2].checked = true;
 }

function validarForm(form){
	// Campos relacionados
	var localidade = form.localidadeFiltro;
	var setorComercial = form.setorComercialFiltro;
	var quadraInicial = form.quadraInicialFiltro;
	var quadraFinal = form.quadraFinalFiltro;

	if(form.mesAno.value != ""){
		 if (verificaAnoMes(form.mesAno)){
		 	alert("Mês Ano inválido.");
		 	return false;
		 }
	}else	
	//Origem
	if (localidade.value.length > 0 && !testarCampoValorZero(localidade, "O código da localidade")){
		localidade.focus();
	}else if (setorComercial.value.length > 0 && 
			  !testarCampoValorZero(setorComercial, "O código do setor comercial")){
		setorComercial.focus();
	}else if (quadraInicial.value.length > 0 && !testarCampoValorZero(quadraInicial, "O número da quadra inicial")){
		quadraInicial.focus();
	}else if (quadraFinal.value.length > 0 && !testarCampoValorZero(quadraFinal, "O número da quadra Final")){
		quadraFinal.focus();
	}
	
	// Confirma a validação do formulário
	if (validateLeituraConsumoActionForm(form)){
		form.action = 'filtrarExcecoesLeiturasConsumosAction.do?nomeCaminhoMapping=efetuarAnaliseExcecoesLeiturasConsumos';
		form.submit();
//		redirecionarSubmit("/gsan/filtrarExcecoesLeiturasConsumosAction.do?nomeCaminhoMapping=efetuarAnaliseExcecoesLeiturasConsumos");
	}

}

function chamarPopup(url, tipo, objeto, codigoObjeto, altura, largura, msg){

	if (objeto == null || codigoObjeto == null){
		abrirPopup(url + "?" + "tipo=" + tipo, altura, largura);
	}
	else{
		if (codigoObjeto.length < 1 || isNaN(codigoObjeto)){
			alert(msg);
		}
		else{
			abrirPopup(url + "?" + "tipo=" + tipo + "&" + objeto + "=" + codigoObjeto + "&caminhoRetornoTelaPesquisa=" + tipo, altura, largura);
		}
	}
}

function limparPesquisaImovelPrincipal() {
    var form = document.forms[0];

      form.imovelFiltro.value = "";
      form.imovelMatriculaFiltro.value = "";
habilitaDesabilitaImovel();

  }
  
  function limparPesquisaImovelCondominio() {
    var form = document.forms[0];

      form.imovelCondominioFiltro.value = "";
      form.imovelMatriculaCondominioFiltro.value = "";
      habilitaDesabilitaImovel();


  }
  
  function habilitaDesabilitaImovel(){
  var form = document.forms[0];
  if(form.imovelFiltro.value !=''){
   form.imovelCondominioFiltro.disabled = true;
   form.imovelCondominioFiltro.value == '';
   form.imovelMatriculaCondominioFiltro.value =='';
  }else{
  form.imovelCondominioFiltro.disabled = false;
  }
   if(form.imovelCondominioFiltro.value !=''){
    form.imovelFiltro.disabled = true;
    form.imovelFiltro.value == '';
    form.imovelMatriculaFiltro.value =='';
   }else{
   form.imovelFiltro.disabled = false;
   }
}

function verificarCampoDesabilitado(campoHabilitado,caminho,height,width){
if(!campoHabilitado){
javascript:abrirPopup(caminho,height,width);
}

}

//-->
</SCRIPT>

</head>

<body leftmargin="5" topmargin="5"
	onload="javascript:setarFoco('${requestScope.nomeCampo}');habilitaDesabilitaImovel();">


<html:form name="LeituraConsumoActionForm"
	type="gcom.gui.micromedicao.LeituraConsumoActionForm"
	action="/filtrarExcecoesLeiturasConsumosWizardAction" method="post"
	onsubmit="return validateLeituraConsumoActionForm(this);">

	<jsp:include
		page="/jsp/util/wizard/navegacao_abas_wizard_filtro.jsp?numeroPagina=1" />


	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>

	<table width="770" border="0" cellspacing="5" cellpadding="0">
		<input type="hidden" name="numeroPagina" value="1" />
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
					<td class="parabg">Filtrar Exceções de Leituras e Consumos</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			
			<table width="100%" border="0" >
			<tr>
			<logic:present scope="application" name="urlHelp">
						<td align="right"><a href="javascript:abrirPopupHelp('${applicationScope.urlHelp}faturamentoLeituraConsumoExcecoesFiltrarAbaLocalidade', 500, 700);"><span style="font-weight: bold"><font color="#3165CE">Ajuda</font></span></a></td>									
			</logic:present>
			<logic:notPresent scope="application" name="urlHelp">
						<td align="right"><span style="font-weight: bold"><font color=#696969><u>Ajuda</u></font></span></td>									
			</logic:notPresent>
			</tr>
			</table>

			<table width="100%" border="0">
				<tr>
					<td><strong>&nbsp;</strong></td>
					<td><html:radio property="tipoApresentacao" value="1"/>
						    Análise 
						    <html:radio property="tipoApresentacao" value="2" />Gerar Relatório Análise
						    <html:radio property="tipoApresentacao" value="3" />Gerar Aviso Anormalidade
					</td>
				</tr>
				
				<!-- ano mes - pedido por Aryed data: 12/02/2008 -->
				
				<tr>
					<td><strong>Mês/Ano:<font color="#FF0000">*</font></strong></td>
					<td>
						<html:text property="mesAno" size="7"  maxlength="7" onkeypress="javascript:mascaraAnoMes(this, event);return isCampoNumerico(event); "/>&nbsp;mm/aaaa
					</td>
				</tr>
				
				<tr>
					<td><strong>Matrícula do Imóvel:</strong></td>
					<td><html:text maxlength="9" property="imovelFiltro" size="9"
						tabindex="1"
						onkeypress="javascript:validaEnterComMensagem(event, 'exibirFiltrarExcecoesLeiturasConsumosAction.do', 'imovelFiltro', 'Mátricula do Imóvel');return isCampoNumerico(event);"
						onkeyup="document.forms[0].imovelMatriculaFiltro.value='';habilitaDesabilitaImovel();" /> <a
						href="javascript:verificarCampoDesabilitado(document.forms[0].imovelFiltro.disabled,'exibirPesquisarImovelAction.do?tipo=imovel', 400, 800);"><img
						width="23" height="21"
						src="<bean:message key="caminho.imagens"/>pesquisa.gif" border="0" title="Pesquisar Matrícula do Imóvel"/></a>
					<logic:present name="codigoImovelNaoEncontrada" scope="request">
						<html:text property="imovelMatriculaFiltro" size="50"
							readonly="true"
							style="background-color:#EFEFEF; border:0; color: #ff0000" />
					</logic:present> <logic:notPresent name="codigoImovelNaoEncontrada"
						scope="request">
						<logic:empty name="LeituraConsumoActionForm"
							property="imovelFiltro">
							<html:text property="imovelMatriculaFiltro" size="50"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:empty>
						<logic:notEmpty name="LeituraConsumoActionForm"
							property="imovelFiltro">
							<html:text property="imovelMatriculaFiltro" size="50"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEmpty>
					</logic:notPresent> <a
						href="javascript:limparPesquisaImovelPrincipal();"> <img
						src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar" /></a></td>
				</tr>
				<tr>
					<td><strong>Mat. do Imóvel Condomínio:</strong></td>
					<td><html:text maxlength="9" property="imovelCondominioFiltro"
						size="9" tabindex="2"
						onkeypress="javascript:validaEnterComMensagem(event, 'exibirFiltrarExcecoesLeiturasConsumosAction.do', 'imovelCondominioFiltro', 'Mat. do Imóvel Condomínio:');return isCampoNumerico(event);"
						onkeyup="document.forms[0].imovelMatriculaCondominioFiltro.value='';habilitaDesabilitaImovel();" />
					<a
						href="javascript:verificarCampoDesabilitado(document.forms[0].imovelCondominioFiltro.disabled,'exibirPesquisarImovelAction.do?tipo=imovelCondominio', 400, 800);"><img
						width="23" height="21"
						src="<bean:message key="caminho.imagens"/>pesquisa.gif" border="0" title="Pesquisar Matrícula do Imóvel Condomínio" /></a>
					<logic:present name="codigoImovelCondominioNaoEncontrada"
						scope="request">
						<html:text property="imovelMatriculaCondominioFiltro" size="50"
							readonly="true"
							style="background-color:#EFEFEF; border:0; color: #ff0000" />
					</logic:present> <logic:notPresent
						name="codigoImovelCondominioNaoEncontrada" scope="request">
						<logic:empty name="LeituraConsumoActionForm"
							property="imovelCondominioFiltro">
							<html:text property="imovelMatriculaCondominioFiltro" size="50"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:empty>
						<logic:notEmpty name="LeituraConsumoActionForm"
							property="imovelCondominioFiltro">
							<html:text property="imovelMatriculaCondominioFiltro" size="50"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEmpty>
					</logic:notPresent> <a
						href="javascript:limparPesquisaImovelCondominio();"> <img
						src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar" /></a></td>
				</tr>
				<tr>
					<td><strong>Grupo de Faturamento:<font color="#FF0000">*</font></strong></td>
					<td><html:select property="idGrupoFaturamentoFiltro" tabindex="3">
						<html:option value="">&nbsp;</html:option>
						<html:options collection="colecaoFaturamentoGrupoDescricaoDiaVencimento"
							labelProperty="descricao"  property="id" />
					</html:select></td>
				</tr>
				<tr>
					<td><strong>Empresa:</strong></td>
					<td><html:select property="idEmpresaFiltro" tabindex="4">
						<html:option value="-1">&nbsp;</html:option>
						<html:options collection="colecaoEmpresa"
							labelProperty="descricao" property="id" />
					</html:select></td>
				</tr>
				<tr>
					<td width="19%"><strong>Localidade:</strong></td>
					<td width="81%" height="24"><html:text maxlength="3"
						property="localidadeFiltro" size="3" tabindex="5"
						onkeypress="javascript:limparPesquisaQuadraSemFocus();limparPesquisaSetorComercialSemFocus(); validaEnter(event, 'exibirFiltrarExcecoesLeiturasConsumosAction.do?objetoConsulta=1&inscricaoTipo=origem', 'localidadeFiltro');return isCampoNumerico(event);"
						onkeyup="document.forms[0].nomeLocalidade.value='';" /> <a
						href="javascript:abrirPopup('exibirPesquisarLocalidadeAction.do?tipo=imovelLocalidade', 400, 800);limparPesquisaQuadraSemFocus();limparPesquisaSetorComercialSemFocus();">
					<img width="23" height="21"
						src="<bean:message key="caminho.imagens"/>pesquisa.gif" border="0" title="Pesquisar Localidade"/></a>

					<logic:present name="codigoLocalidadeNaoEncontrada" scope="request">
						<html:text property="nomeLocalidade" size="50" readonly="true"
							style="background-color:#EFEFEF; border:0; color: #ff0000"
							value="Localidade Inexistente" />
					</logic:present> <logic:notPresent
						name="codigoLocalidadeNaoEncontrada" scope="request">
						<logic:empty name="LeituraConsumoActionForm"
							property="localidadeFiltro">
							<html:text property="nomeLocalidade" size="50"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:empty>
						<logic:notEmpty name="LeituraConsumoActionForm"
							property="localidadeFiltro">
							<html:text property="nomeLocalidade" size="50"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEmpty>
					</logic:notPresent> <a
						href="javascript:limparPesquisaQuadra();limparPesquisaSetorComercial();limparPesquisaLocalidade();">
					<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar" /></a></td>
				</tr>
				<tr>
					<td><strong>Setor Comercial:</strong></td>
					<td height="24"><html:text maxlength="3"
						property="setorComercialFiltro" size="3" tabindex="6"
						onkeypress="javascript:limparPesquisaQuadraSemFocus();validaEnterDependencia(event, 'exibirFiltrarExcecoesLeiturasConsumosAction.do?objetoConsulta=2&inscricaoTipo=origem', this, document.forms[0].localidadeFiltro.value, 'Localidade');return isCampoNumerico(event);"
						onkeyup="document.forms[0].setorComercialNome.value='';" /> <a
						href="javascript:abrirPopupDependencia('exibirPesquisarSetorComercialAction.do?idLocalidade='+document.forms[0].localidadeFiltro.value+'&tipo=SetorComercial',document.forms[0].localidadeFiltro.value,'Localidade', 400, 800);limparPesquisaQuadraSemFocus();">
					<img width="23" height="21"
						src="<bean:message key="caminho.imagens"/>pesquisa.gif" border="0" title="Pesquisar Setor Comercial" /></a>
					<logic:present name="codigoSetorComercialNaoEncontrada"
						scope="request">
						<input type="text" name="setorComercialNome" size="50"
							readonly="true"
							style="background-color:#EFEFEF; border:0; color: #ff0000"
							value="Setor Comercial Inexistente" />
					</logic:present> <logic:notPresent
						name="codigoSetorComercialNaoEncontrada" scope="request">
						<logic:empty name="LeituraConsumoActionForm"
							property="setorComercialFiltro">
							<html:text property="setorComercialNome" size="50"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:empty>
						<logic:notEmpty name="LeituraConsumoActionForm"
							property="setorComercialFiltro">
							<html:text property="setorComercialNome" size="50"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEmpty>
					</logic:notPresent> <a
						href="javascript:limparPesquisaQuadra();limparPesquisaSetorComercial();">
					<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar" /></a></td>
				</tr>
				<tr>
					<td><strong>Quadra Inicial:</strong></td>
					<td height="24"><html:text maxlength="4"
						property="quadraInicialFiltro" size="4" tabindex="7"
						onkeypress="javascript:return isCampoNumerico(event);return validaEnterDependencia(event, 'exibirFiltrarExcecoesLeiturasConsumosAction.do?objetoConsulta=3&inscricaoTipo=origem', this, document.forms[0].setorComercialFiltro.value, 'Setor Comercial');"
						onkeyup="document.getElementById('quadraInicial').innerHTML = '';document.forms[0].quadraFinalFiltro.value=document.forms[0].quadraInicialFiltro.value" /> 
						<font color="#ff0000" size="2px"><span id="quadraInicial"> <bean:write name="LeituraConsumoActionForm"
							property="quadraInicialMensagem" /> </span></font>
						<html:hidden property="quadraInicialID" /></td>
				</tr>
				<tr>
					<td><strong>Quadra Final:</strong></td>
					<td height="24"><html:text maxlength="4"
						property="quadraFinalFiltro" size="4" tabindex="8"
						onkeypress="javascript:return isCampoNumerico(event);return validaEnterDependencia(event, 'exibirFiltrarExcecoesLeiturasConsumosAction.do?objetoConsulta=3&inscricaoTipo=Destino', this, document.forms[0].setorComercialFiltro.value, 'setorComercialFiltro');"
						onkeyup="document.getElementById('quadraFinal').innerHTML = '';" />
						<font color="#ff0000" size="2px"><span id="quadraFinal"> <bean:write name="LeituraConsumoActionForm"
							property="quadraFinalMensagem" /> </span></font>
					<html:hidden property="quadraFinalID" /></td>
				</tr>
				<tr>
					<td><strong>Rota:</strong></td>
					<td height="24"><html:text maxlength="6"
						property="rotaFiltro" size="6" tabindex="8"
						onkeypress="javascript:return isCampoNumerico(event);return validaEnterDependencia(event, 'exibirFiltrarExcecoesLeiturasConsumosAction.do?objetoConsulta=4', this, document.forms[0].rotaFiltro.value, 'rotaFiltro');"/>
						<font color="#ff0000" size="2px"><span id="quadraFinal"> <bean:write name="LeituraConsumoActionForm"
							property="rotaMensagem" /> </span></font></td>
				</tr>				
				<tr>
					<td><strong>Usuário Alteração:</strong></td>
					<td>
					<html:hidden property="idUsuarioAlteracao" />
					<html:text maxlength="9" property="loginUsuarioAlteracao" size="9"
						tabindex="9" style="text-transform: none;"
						onkeypress="javascript:validaEnterStringSemUpperCase(event, 'exibirFiltrarExcecoesLeiturasConsumosAction.do?objetoConsulta=5&inscricaoTipo=origem', 'loginUsuarioAlteracao');return isCampoNumerico(event);"
						onkeyup="limparUsuarioTecla();" /> <a
						href="javascript:abrirPopup('exibirUsuarioPesquisar.do?limpaForm=sim', 250, 495);"><img
						width="23" height="21"
						src="<bean:message key="caminho.imagens"/>pesquisa.gif" border="0" title="Pesquisar Usuário Alteração"/></a>
					<logic:present name="codigoUsuarioNaoEncontrado" scope="request">
						<html:text property="nomeUsuarioAlteracao" size="50"
							readonly="true"
							style="background-color:#EFEFEF; border:0; color: #ff0000" />
					</logic:present> <logic:notPresent name="codigoUsuarioNaoEncontrado"
						scope="request">
						<logic:empty name="LeituraConsumoActionForm"
							property="loginUsuarioAlteracao">
							<html:text property="nomeUsuarioAlteracao" size="50"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:empty>
						<logic:notEmpty name="LeituraConsumoActionForm"
							property="loginUsuarioAlteracao">
							<html:text property="nomeUsuarioAlteracao" size="50"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEmpty>
					</logic:notPresent> <a
						href="javascript:limparUsuario();"> <img
						src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar" /></a></td>
				</tr>
				<tr>
					<td><strong>Ind. de Imóvel Condomínio:</strong></td>
					<td><html:radio property="indicadorImovelCondominioFiltro"
						value="S" tabindex="10" />Sim <html:radio
						property="indicadorImovelCondominioFiltro" value="N" tabindex="11" />Não
					<html:radio property="indicadorImovelCondominioFiltro"
						value="" tabindex="12" />Todos</td>
				</tr>
				<tr>
					<td><strong>Ind. de Débito Automático:</strong></td>
					<td><html:radio property="indicadorDebitoAutomatico"
						value="S" tabindex="13" />Sim <html:radio
						property="indicadorDebitoAutomatico" value="N" tabindex="14" />Não
					<html:radio property="indicadorDebitoAutomatico"
						value="" tabindex="15" />Todos</td>
				</tr>
				<tr>
					<td><strong>Ind. Analisado:</strong></td>
					<td><html:radio property="indicadorAnalisado"
						value="<%=""+ConstantesSistema.INDICADOR_USO_ATIVO%>" tabindex="16" />Sim <html:radio
						property="indicadorAnalisado" value="<%=""+ConstantesSistema.INDICADOR_USO_DESATIVO%>" tabindex="17" />Não
					<html:radio property="indicadorAnalisado"
						value="" tabindex="18" />Todos</td>
				</tr>
				<tr>
					<td colspan="2">
					<div align="right"><jsp:include
						page="/jsp/util/wizard/navegacao_botoes_wizard_filtro.jsp?numeroPagina=1" /></div>
					</td>
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
