<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page import="gcom.util.ConstantesSistema"%>
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
	formName="ImovelOutrosCriteriosActionForm" dynamicJavascript="false" />

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>cadastro/imovel/imovel_outros_criterios.js"></script>

<script>
<!-- Begin 

function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {

	var form = document.ImovelOutrosCriteriosActionForm;

	if (tipoConsulta == 'localidadeOrigem') {
      form.localidadeOrigemID.value = codigoRegistro;
	  form.nomeLocalidadeOrigem.value = descricaoRegistro;
	  form.nomeLocalidadeOrigem.style.color = "#000000";
	  
	  form.localidadeDestinoID.value = codigoRegistro;
      form.nomeLocalidadeDestino.value = descricaoRegistro;
      form.nomeLocalidadeDestino.style.color = "#000000";
      form.setorComercialOrigemCD.focus();
	}

	if (tipoConsulta == 'localidadeDestino') {
      form.localidadeDestinoID.value = codigoRegistro;
      form.nomeLocalidadeDestino.value = descricaoRegistro;
   	  form.setorComercialDestinoCD.focus();
	 		  
	}
	
	if (tipoConsulta == 'setorComercialOrigem') {
      form.setorComercialOrigemCD.value = codigoRegistro;
      form.setorComercialOrigemID.value = idRegistro;
	  form.nomeSetorComercialOrigem.value = descricaoRegistro;
	  form.nomeSetorComercialOrigem.style.color = "#000000";
	  
	  form.setorComercialDestinoCD.value = codigoRegistro;
      form.setorComercialDestinoID.value = idRegistro;
	  form.nomeSetorComercialDestino.value = descricaoRegistro;
	  form.nomeSetorComercialDestino.style.color = "#000000";
	  form.quadraOrigemNM.focus();
	}

	if(tipoConsulta == 'municipio'){
		form.idMunicipio.value = codigoRegistro;
		form.nomeMunicipio.value = descricaoRegistro;
		form.nomeMunicipio.style.color = "#000000";
		form.idBairro.focus();
	}

	if(tipoConsulta == 'cep'){
		form.CEP.value = codigoRegistro;
		form.descricaoCep.value = descricaoRegistro;
		form.descricaoCep.style.color = "#000000";
 	    form.idLogradouro.focus();
	}

	
	if(tipoConsulta == 'bairro'){
		form.idBairro.value = codigoRegistro;
		form.nomeBairro.value = descricaoRegistro;
		form.nomeBairro.style.color = "#000000";
	    form.CEP.focus();
	}
	
	if(tipoConsulta == 'logradouro'){
		form.idLogradouro.value = codigoRegistro;
		form.nomeLogradouro.value = descricaoRegistro;
		form.nomeLogradouro.style.color = "#000000";
		form.idLogradouro.focus();
	}
	
}

/*
recupera os dados vindos pelo metodo enviarDadosQuatroParametros 
*/

function recuperarDadosQuatroParametros(idRegistro, descricaoRegistro, codigoRegistro, tipoConsulta) {

	var form = document.ImovelOutrosCriteriosActionForm;

	if (tipoConsulta == 'setorComercialOrigem') {
      form.setorComercialOrigemCD.value = codigoRegistro;
      form.setorComercialOrigemID.value = idRegistro;
	  form.nomeSetorComercialOrigem.value = descricaoRegistro;
	  form.nomeSetorComercialOrigem.style.color = "#000000";
	  
	  form.setorComercialDestinoCD.value = codigoRegistro;
      form.setorComercialDestinoID.value = idRegistro;
	  form.nomeSetorComercialDestino.value = descricaoRegistro;
	  form.nomeSetorComercialDestino.style.color = "#000000";
	  form.quadraOrigemNM.focus();
	}

	if (tipoConsulta == 'setorComercialDestino') {
      form.setorComercialDestinoCD.value = codigoRegistro;
      form.setorComercialDestinoID.value = idRegistro;
	  form.nomeSetorComercialDestino.value = descricaoRegistro;
	  form.nomeSetorComercialDestino.style.color = "#000000"; 
	  form.quadraDestinoNM.focus();
	}
	/**
	* 
	*form.submit(); 
	*/

}


     var bCancel = false; 

    function validateImovelOutrosCriteriosActionForm(form) {                                                                   
        if (bCancel) 
      return true; 
        else 
       return validateCaracterEspecial(form) && validateLong(form)
       && testarCampoValorZero(document.ImovelOutrosCriteriosActionForm.localidadeOrigemID, 'Localidade Inicial')
       && testarCampoValorZero(document.ImovelOutrosCriteriosActionForm.setorComercialOrigemCD, 'Setor Comercial Inicial')
       && testarCampoValorZero(document.ImovelOutrosCriteriosActionForm.quadraOrigemNM, 'Quadra Inicial')
       && testarCampoValorZero(document.ImovelOutrosCriteriosActionForm.loteOrigem, 'Lote Inicial')
       && testarCampoValorZero(document.ImovelOutrosCriteriosActionForm.localidadeDestinoID, 'Localidade Final')
       && testarCampoValorZero(document.ImovelOutrosCriteriosActionForm.setorComercialDestinoCD, 'Setor Comercial Final')
       && testarCampoValorZero(document.ImovelOutrosCriteriosActionForm.quadraDestinoNM, 'Quadra Final')
       && testarCampoValorZero(document.ImovelOutrosCriteriosActionForm.loteDestino, 'Lote Final')
       && testarCampoValorZero(document.ImovelOutrosCriteriosActionForm.idMunicipio, 'Município')
       && testarCampoValorZero(document.ImovelOutrosCriteriosActionForm.idBairro, 'Bairro')
       && testarCampoValorZero(document.ImovelOutrosCriteriosActionForm.idLogradouro, 'Logradouro')
       && testarCampoValorZero(document.ImovelOutrosCriteriosActionForm.CEP, 'CEP')
	   && validarLocalidadeDiferentes()
	   && validarSetoresComerciaisDiferentes()
	   && validarQuadrasDiferentes()
	   && validarLotesDiferentes()
	   && verificarSetoresComerciaisMenores()
	   && verificarLocalidadeMenores()    
	   && verificarQuadraMenores()
	   && verificarLoteMenores()
	   && validarRota(form);
	   //&& validarRota2();    
   } 

    function caracteresespeciais () { 
	     this.aa = new Array("localidadeOrigemID", "Localidade Inicial deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
	     this.ab = new Array("setorComercialOrigemCD", "Setor Comercial  Inicial deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
	     this.ac = new Array("quadraOrigemNM", "Quadra  Inicial deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
	     this.ad = new Array("loteOrigem", "Lote  Inicial deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
	     this.ae = new Array("localidadeDestinoID", "Localidade Final deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
	     this.af = new Array("setorComercialDestinoCD", "Setor Comercial Final deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
	     this.ag = new Array("quadraDestinoNM", "Quadra Final deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
	     this.ah = new Array("loteDestino", "Lote Final deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
	     this.ai = new Array("idMunicipio", "Município deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
	     this.aj = new Array("idBairro", "Bairro deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
		 this.al = new Array("CEP", "CEP deve somente conter números positivos.", new Function ("varName", " return this[varName];"));	 
		 this.am = new Array("idLogradouro", "Logradouro deve somente conter números positivos.", new Function ("varName", " return this[varName];"));	 
    } 

    function IntegerValidations () { 
	     this.an = new Array("localidadeOrigemID", "Localidade Inicial deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
	     this.ao = new Array("setorComercialOrigemCD", "Setor Comercial Inicial deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
	     this.ap = new Array("quadraOrigemNM", "Quadra Inicial deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
	     this.aq = new Array("loteOrigem", "Lote Inicial deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
	
	     this.ar = new Array("localidadeDestinoID", "Localidade Final deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
	     this.as = new Array("setorComercialDestinoCD", "Setor Comercial Final deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
	     this.at = new Array("quadraDestinoNM", "Quadra Final deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
	     this.au = new Array("loteDestino", "Lote Final deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
	
		 this.av = new Array("idMunicipio", "Município deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
	     this.ax = new Array("idBairro", "Bairro deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
		 this.ax = new Array("CEP", "CEP deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
		 this.aw = new Array("idLogradouro", "Logradouro deve somente conter números positivos.", new Function ("varName", " return this[varName];"));	 
    } 
    

 function limparForm(){
 	
 	var form = document.forms[0];
 }
 
 function validarBairro(){
 	
 	var form = document.forms[0];
 	
 	if (form.idMunicipio.value.length < 1) {
		form.idBairro.value = "";
 		alert('Informe Município');
 	}
 }
 
 //Sávio Luiz
 function replicarRota(){
    var form = document.forms[0];
	form.cdRotaFinal.value = form.cdRotaInicial.value;
  }
  
 //Sávio Luiz
 function replicarSequencialRota(){
    var form = document.forms[0];
	form.sequencialRotaFinal.value = form.sequencialRotaInicial.value;
  }
  
  function validarRota(form){

	var retorno = true;
	
	var sequencialRotaInicial = form.sequencialRotaInicial;
	var rotaInicial = form.cdRotaInicial;
	var rotaFinal = form.cdRotaFinal;
	var sequencialRotaFinal = form.sequencialRotaFinal;
	var localidadeOrigemID = form.localidadeOrigemID;
	
	if(rotaInicial.value.length > 0){
	
		if(localidadeOrigemID.value.length < 1){
		  alert("Informe Localidade Inicial");
		  localidadeOrigemID.focus();
		  return false;
		}
		
		if (rotaFinal.value.length < 1){
			alert("Informe Rota Final");
			rotaFinal.focus();
			return false;
		}
		if (sequencialRotaInicial.value.length > 0 && sequencialRotaFinal.value.length < 1){
			alert("Informe Seq. da Rota Final");
			sequencialRotaFinal.focus();
			return false;
		}
		if (sequencialRotaInicial.value.length < 1 && sequencialRotaFinal.value.length > 0){
			alert("Informe Seq. da Rota Inicial");
			sequencialRotaInicial.focus();
			return false;
		}
	}else{
	    if (rotaFinal.value.length > 0){
			alert("Informe Rota Inicial");
			rotaFinal.focus();
			return false;
		}
		if(sequencialRotaInicial.value.length > 0 || sequencialRotaFinal.value.length > 0){
		    alert("Informe Rota Inicial");
			rotaFinal.focus();
			return false;
		}
		if (sequencialRotaInicial.value.length > 0 && sequencialRotaFinal.value.length < 1){
			alert("Informe Seq. da Rota Final");
			sequencialRotaInicial.focus();
			return false;
		}
		if (sequencialRotaInicial.value.length < 1 && sequencialRotaFinal.value.length > 0){
			alert("Informe Seq. da Rota Inicial");
			sequencialRotaFinal.focus();
			return false;
		}
	}

	if(parseInt(rotaInicial.value) > parseInt(rotaFinal.value)){
		alert("O valor da Rota Final não pode ser menor que o valor da Rota Inicial.");
		rotaFinal.focus();
		return false;
	}
	
	if(parseInt(sequencialRotaInicial.value) > parseInt(sequencialRotaFinal.value)){
		alert("O valor Seq. da Rota Final não pode ser menor que o valor Seq. da Rota Inicial.");
		sequencialRotaFinal.focus();
		return false;
	}
	
	return retorno;
}

//Hugo Leonardo
function validarRota2(){
    var form = document.forms[0];
	var retorno = true;
	
	if (form.cdRotaInicial.value != ""){
		if (form.quadraOrigemNM.value ==""){
	  			alert("Informe o número da quadra.");
	  			retorno = false;
	   		}
	}
	
	return retorno;
}

    
-->    
</script>

</head>
<body leftmargin="5" topmargin="5"
	onload="setarFoco('${requestScope.nomeCampo}');desabilitaIntervaloDiferente(1);desabilitaIntervaloDiferente(2);desabilitaIntervaloDiferente(3);">
<div id="formDiv"><html:form
	action="/filtrarImovelOutrosCriteriosWizardAction"
	type="gcom.gui.cadastro.imovel.ImovelOutrosCriteriosActionForm"
	onsubmit="return validateImovelOutrosCriteriosActionForm(this);"
	name="ImovelOutrosCriteriosActionForm" method="post">

	<jsp:include
		page="/jsp/util/wizard/navegacao_abas_wizard_valida_avancar_valida_voltar.jsp?numeroPagina=1" />

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
					<td class="parabg">Filtrar Imóvel</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>

			<table width="100%" border="0">
				<tr>
					<td><strong>Gerência Regional:</strong></td>
					<td><!--<html:text property="idGerenciaRegional"> </html:text>--> <html:select
						property="idGerenciaRegional"
						name="ImovelOutrosCriteriosActionForm" tabindex="1">
						<html:option
							value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;
			            </html:option>
						<logic:iterate name="colecaoGerenciasRegionais"
							id="colecaoGerenciasRegionais">
							<html:option value="${colecaoGerenciasRegionais.id}">
								<bean:write name="colecaoGerenciasRegionais"
									property="nomeAbreviado" /> 
					           - <bean:write name="colecaoGerenciasRegionais"
									property="nome" />
							</html:option>
						</logic:iterate>


						<!-- 
						<logic:present scope="session" name="colecaoGerenciasRegionais">
						</logic:present>			         
						-->
					</html:select></td>
				</tr>
				<tr>
					<td><strong>Unidade Negócio:</strong></td>
					<td><!--<html:text property="idGerenciaRegional"> </html:text>--> <html:select
						property="unidadeNegocio" name="ImovelOutrosCriteriosActionForm"
						tabindex="1">
						<html:option
							value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;
			            </html:option>
						<logic:present name="colecaoUnidadeNegocio">
							<logic:iterate name="colecaoUnidadeNegocio"
								id="colecaoUnidadeNegocios">
								<html:option value="${colecaoUnidadeNegocios.id}">
									<bean:write name="colecaoUnidadeNegocios"
										property="nomeAbreviado" /> 
					           - <bean:write name="colecaoUnidadeNegocios"
										property="nome" />
								</html:option>
							</logic:iterate>
						</logic:present>
						<!-- 
						<logic:present scope="session" name="colecaoGerenciasRegionais">
						</logic:present>			         
						-->
					</html:select></td>
				</tr>


				<tr height="1">
					<td colspan="4">&nbsp;</td>
				</tr>

				<tr>
					<td colspan="4">
					<table width="100%" align="center" bgcolor="#99CCFF" border="0">
						<tr>
							<td colspan="2"><strong>Informe os dados da
							inscri&ccedil;&atilde;o inicial:</strong></td>
						</tr>
						<tr bgcolor="#cbe5fe">
							<td width="100%" align="center" colspan="2">

							<table width="100%" border="0">
								<tr bgcolor="#cbe5fe">
									<html:hidden property="inscricaoTipo" />
									<td width="20%"><strong>Localidade:</strong></td>
									<td><html:text tabindex="2" maxlength="3"
										property="localidadeOrigemID" size="5"
										onkeypress="form.target='';validaEnter(event, 'filtrarImovelOutrosCriteriosWizardAction.do?action=exibirFiltrarImovelOutrosCriteriosLocalizarImoveis&objetoConsulta=1&inscricaoTipo=origem', 'localidadeOrigemID');limparDestino(1);return isCampoNumerico(event);"
										onclick="validarLocalidade()"
										onblur="duplicarLocalidade();desabilitaIntervaloDiferente(1);" />
									<a
										href="javascript:chamarPopup('exibirPesquisarLocalidadeAction.do', 'origem', null, null, 275, 480, '');limparOrigem(1);"><img
										width="23" height="21" border="0"
										src="<bean:message key="caminho.imagens"/>pesquisa.gif"
										title="Pesquisar" /></a> <logic:present
										name="corLocalidadeOrigem">

										<logic:equal name="corLocalidadeOrigem" value="exception">
											<html:text property="nomeLocalidadeOrigem" size="45"
												readonly="true"
												style="background-color:#EFEFEF; border:0; color: #ff0000" />
										</logic:equal>

										<logic:notEqual name="corLocalidadeOrigem" value="exception">
											<html:text property="nomeLocalidadeOrigem" size="45"
												readonly="true"
												style="background-color:#EFEFEF; border:0; color: #000000" />
										</logic:notEqual>

									</logic:present> <logic:notPresent name="corLocalidadeOrigem">

										<logic:empty name="ImovelOutrosCriteriosActionForm"
											property="localidadeOrigemID">
											<html:text property="nomeLocalidadeOrigem" value="" size="45"
												readonly="true"
												style="background-color:#EFEFEF; border:0; color: #ff0000" />
										</logic:empty>
										<logic:notEmpty name="ImovelOutrosCriteriosActionForm"
											property="localidadeOrigemID">
											<html:text property="nomeLocalidadeOrigem" size="45"
												readonly="true"
												style="background-color:#EFEFEF; border:0; color: 	#000000" />
										</logic:notEmpty>
									</logic:notPresent> <a
										href="javascript:limparBorrachaOrigem(1);limparMsgQuadraInexistente();">
									<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
										border="0" title="Apagar" /></a></td>
								</tr>
								<tr>
									<td><strong>Setor Comercial:</strong></td>
									<td><html:text tabindex="3" maxlength="3"
										property="setorComercialOrigemCD" size="5"
										onkeyup="limparOrigem(2);"
										onkeypress="form.target='';limparDestino(2);validaEnterDependencia(event, 'filtrarImovelOutrosCriteriosWizardAction.do?action=exibirFiltrarImovelOutrosCriteriosLocalizarImoveis&objetoConsulta=2&inscricaoTipo=origem', document.forms[0].setorComercialOrigemCD, document.forms[0].localidadeOrigemID.value, 'Localidade Inicial.');return isCampoNumerico(event);"
										onblur="javascript:duplicarSetorComercial();desabilitaIntervaloDiferente(2);" />
									<a
										href="javascript:chamarPopup('exibirPesquisarSetorComercialAction.do', 'setorComercialOrigem','idLocalidade', document.ImovelOutrosCriteriosActionForm.localidadeOrigemID.value, 275, 480, 'Informe Localidade Inicial.');limparOrigem(2);"><img
										width="23" height="21" border="0"
										src="<bean:message key="caminho.imagens"/>pesquisa.gif"
										title="Pesquisar" /></a> <logic:present
										name="corSetorComercialOrigem">

										<logic:equal name="corSetorComercialOrigem" value="exception">
											<html:text property="nomeSetorComercialOrigem" size="45"
												readonly="true"
												style="background-color:#EFEFEF; border:0; color: #ff0000" />
										</logic:equal>

										<logic:notEqual name="corSetorComercialOrigem"
											value="exception">
											<html:text property="nomeSetorComercialOrigem" size="45"
												readonly="true"
												style="background-color:#EFEFEF; border:0; color: #000000" />
										</logic:notEqual>

									</logic:present> <logic:notPresent
										name="corSetorComercialOrigem">

										<logic:empty name="ImovelOutrosCriteriosActionForm"
											property="setorComercialOrigemCD">
											<html:text property="nomeSetorComercialOrigem" value=""
												size="45" readonly="true"
												style="background-color:#EFEFEF; border:0; color: #ff0000" />
										</logic:empty>
										<logic:notEmpty name="ImovelOutrosCriteriosActionForm"
											property="setorComercialOrigemCD">
											<html:text property="nomeSetorComercialOrigem" size="45"
												readonly="true"
												style="background-color:#EFEFEF; border:0; color: #000000" />
										</logic:notEmpty>

									</logic:notPresent> <a
										href="javascript:limparBorrachaOrigem(2);limparMsgQuadraInexistente();">
									<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
										border="0" title="Apagar" /></a> <html:hidden
										property="setorComercialOrigemID" /></td>
								</tr>
								<tr>
									<td width="183"><strong>Quadra:</strong></td>
									<td width="432"><html:text tabindex="4" maxlength="4"
										property="quadraOrigemNM" size="5"
										onkeypress="form.target='';limparOrigem(3);validaEnterDependencia(event, 'filtrarImovelOutrosCriteriosWizardAction.do?action=exibirFiltrarImovelOutrosCriteriosLocalizarImoveis&objetoConsulta=3&inscricaoTipo=origem', document.forms[0].quadraOrigemNM, document.forms[0].setorComercialOrigemCD.value, 'Setor Comercial Inicial.');return isCampoNumerico(event);"
										onblur="javascript:duplicarQuadra();desabilitaIntervaloDiferente(3);" />
									<logic:present name="corQuadraOrigem" scope="request">
										<span style="color:#ff0000" id="msgQuadraInicial"><bean:write
											scope="request" name="msgQuadraInicial" /></span>
									</logic:present> <logic:notPresent name="corQuadraOrigem"
										scope="request">
									</logic:notPresent> <html:hidden property="quadraOrigemID" /></td>
								</tr>
								<tr>
									<td><strong>Lote:</strong></td>
									<td><logic:present name="parametroGerarRelatorio">
										<logic:equal name="parametroGerarRelatorio"
											value="RelatorioCadastroConsumidoresInscricao">
											<html:text maxlength="4" property="loteOrigem" size="5"
												onkeypress="return isCampoNumerico(event);"
												onblur="javascript:duplicarLote();" tabindex="5" />
										</logic:equal>
										<logic:notEqual name="parametroGerarRelatorio"
											value="RelatorioCadastroConsumidoresInscricao">
											<logic:equal name="parametroGerarRelatorio"
												value="RelatorioImoveisEndereco">
												<html:text maxlength="4" property="loteOrigem" size="5"
													onkeypress="return isCampoNumerico(event);"
													onblur="javascript:duplicarLote();" tabindex="5" />
											</logic:equal>
											<logic:notEqual name="parametroGerarRelatorio"
												value="RelatorioImoveisEndereco">
												<html:text maxlength="4" property="loteOrigem" size="5"
													onkeypress="return isCampoNumerico(event);"
													onblur="javascript:duplicarLote();" tabindex="5" />
											</logic:notEqual>
										</logic:notEqual>
									</logic:present> <logic:notPresent
										name="parametroGerarRelatorio">
										<html:text maxlength="4" property="loteOrigem" size="5"
											onkeypress="return isCampoNumerico(event);"
											onblur="javascript:duplicarLote();" tabindex="5" />
									</logic:notPresent></td>
								</tr>
							</table>
							</td>
						</tr>
					</table>
					</td>
				</tr>
				<tr>
					<td colspan="4">&nbsp;</td>
				</tr>
				<tr>
					<td colspan="4">
					<table width="100%" align="center" bgcolor="#99CCFF" border="0">
						<tr>
							<td colspan="2"><strong>Informe os dados da
							inscri&ccedil;&atilde;o Final:</strong></td>
						</tr>
						<tr bgcolor="#cbe5fe">
							<td width="100%" align="center" colspan="2">

							<table width="100%" border="0">
								<tr bgcolor="#cbe5fe">
									<td width="20%"><strong>Localidade:</strong></td>
									<td><html:text maxlength="3" property="localidadeDestinoID"
										size="5" onkeyup="desabilitaIntervaloDiferente(1);"
										onkeypress="limparDestino(1);validaEnter(event,'filtrarImovelOutrosCriteriosWizardAction.do?action=exibirFiltrarImovelOutrosCriteriosLocalizarImoveis&objetoConsulta=1&inscricaoTipo=destino', 'localidadeDestinoID');return isCampoNumerico(event);"
										tabindex="6" /> <a
										href="javascript:chamarPopup('exibirPesquisarLocalidadeAction.do', 'destino', null, null, 275, 480, '');limparDestino(1);"><img
										width="23" height="21" border="0"
										src="<bean:message key="caminho.imagens"/>pesquisa.gif"
										title="Pesquisar" /></a> <logic:present
										name="corLocalidadeDestino">

										<logic:equal name="corLocalidadeDestino" value="exception">
											<html:text property="nomeLocalidadeDestino" size="45"
												readonly="true"
												style="background-color:#EFEFEF; border:0; color: #ff0000" />
										</logic:equal>

										<logic:notEqual name="corLocalidadeDestino" value="exception">
											<html:text property="nomeLocalidadeDestino" size="45"
												readonly="true"
												style="background-color:#EFEFEF; border:0; color: #000000" />
										</logic:notEqual>

									</logic:present> <logic:notPresent name="corLocalidadeDestino">

										<logic:empty name="ImovelOutrosCriteriosActionForm"
											property="localidadeDestinoID">
											<html:text property="nomeLocalidadeDestino" value=""
												size="45" readonly="true"
												style="background-color:#EFEFEF; border:0; color: #ff0000" />
										</logic:empty>
										<logic:notEmpty name="ImovelOutrosCriteriosActionForm"
											property="localidadeDestinoID">
											<html:text property="nomeLocalidadeDestino" size="45"
												readonly="true"
												style="background-color:#EFEFEF; border:0; color: 	#000000" />
										</logic:notEmpty>
									</logic:notPresent> <a
										href="javascript:limparBorrachaDestino(1);limparPesquisaQuadraFinal();">
									<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
										border="0" title="Apagar" /></a></td>
								</tr>
								<tr>
									<td><strong>Setor Comercial :</strong></td>
									<td><html:text maxlength="3" property="setorComercialDestinoCD"
										size="5"
										onkeyup="limparDestino(2);desabilitaIntervaloDiferente(2);"
										onkeypress="form.target='';validaEnterDependencia(event, 'filtrarImovelOutrosCriteriosWizardAction.do?action=exibirFiltrarImovelOutrosCriteriosLocalizarImoveis&objetoConsulta=2&inscricaoTipo=destino', document.forms[0].setorComercialDestinoCD, document.forms[0].localidadeDestinoID.value, 'Localidade Final.');return isCampoNumerico(event);"
										tabindex="7" /> <a
										href="javascript:chamarPopup('exibirPesquisarSetorComercialAction.do', 'setorComercialDestino', 'idLocalidade', document.ImovelOutrosCriteriosActionForm.localidadeDestinoID.value, 275, 480, 'Informe Localidade Final.');limparDestino(2);"><img
										width="23" height="21" border="0"
										src="<bean:message key="caminho.imagens"/>pesquisa.gif"
										title="Pesquisar" /></a> <logic:present
										name="corSetorComercialDestino">

										<logic:equal name="corSetorComercialDestino" value="exception">
											<html:text property="nomeSetorComercialDestino" size="45"
												readonly="true"
												style="background-color:#EFEFEF; border:0; color: #ff0000" />
										</logic:equal>

										<logic:notEqual name="corSetorComercialDestino"
											value="exception">
											<html:text property="nomeSetorComercialDestino" size="45"
												readonly="true"
												style="background-color:#EFEFEF; border:0; color: #000000" />
										</logic:notEqual>

									</logic:present> <logic:notPresent
										name="corSetorComercialDestino">

										<logic:empty name="ImovelOutrosCriteriosActionForm"
											property="setorComercialDestinoCD">
											<html:text property="nomeSetorComercialDestino" value=""
												size="45" readonly="true"
												style="background-color:#EFEFEF; border:0; color: #ff0000" />
										</logic:empty>
										<logic:notEmpty name="ImovelOutrosCriteriosActionForm"
											property="setorComercialDestinoCD">
											<html:text property="nomeSetorComercialDestino" size="45"
												readonly="true"
												style="background-color:#EFEFEF; border:0; color: #000000" />
										</logic:notEmpty>

									</logic:notPresent> <a
										href="javascript:limparBorrachaDestino(2);limparPesquisaQuadraFinal();">
									<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
										border="0" title="Apagar" /></a> <html:hidden
										property="setorComercialDestinoID" /></td>
								</tr>
								<tr>
									<td><strong>Quadra:</strong></td>
									<td><html:text maxlength="4" property="quadraDestinoNM"
										size="5" onkeyup="desabilitaIntervaloDiferente(3);"
										onkeypress="form.target='';limparDestino(3);validaEnterDependencia(event, 'filtrarImovelOutrosCriteriosWizardAction.do?action=exibirFiltrarImovelOutrosCriteriosLocalizarImoveis&objetoConsulta=3&inscricaoTipo=destino', document.forms[0].quadraDestinoNM, document.forms[0].setorComercialDestinoCD.value, 'Setor Comercial Final.');return isCampoNumerico(event);"
										tabindex="8" /> <logic:present name="corQuadraDestino"
										scope="request">
										<span style="color:#ff0000" id="msgQuadraFinal"><bean:write
											scope="request" name="msgQuadraFinal" /></span>
									</logic:present> <logic:notPresent name="corQuadraDestino"
										scope="request">
									</logic:notPresent> <html:hidden property="quadraDestinoID" /></td>
								</tr>
								<tr>
									<td><strong>Lote:</strong></td>
									<td><logic:present name="parametroGerarRelatorio">
										<logic:equal name="parametroGerarRelatorio"
											value="RelatorioCadastroConsumidoresInscricao">
											<html:text maxlength="4" property="loteDestino" size="5"
												tabindex="9" onkeypress="return isCampoNumerico(event);"/>
										</logic:equal>
										<logic:notEqual name="parametroGerarRelatorio"
											value="RelatorioCadastroConsumidoresInscricao">
											<logic:equal name="parametroGerarRelatorio"
												value="RelatorioImoveisEndereco">
												<html:text maxlength="4" property="loteDestino" size="5"
													tabindex="9" onkeypress="return isCampoNumerico(event);"/>
											</logic:equal>
											<logic:notEqual name="parametroGerarRelatorio"
												value="RelatorioImoveisEndereco">
												<html:text maxlength="4" property="loteDestino" size="5"
													tabindex="9" onkeypress="return isCampoNumerico(event);"/>
											</logic:notEqual>

										</logic:notEqual>
									</logic:present> <logic:notPresent
										name="parametroGerarRelatorio">
										<html:text maxlength="4" property="loteDestino" size="5"
											tabindex="9" />
									</logic:notPresent></td>
								</tr>
							</table>
							</td>
						</tr>
					</table>
					</td>
				</tr>
				<tr>
					<td colspan="4">
					<hr>
					</td>
				<tr>
				<tr>
					<td></td>
				</tr>
				<tr>
					<td></td>
				</tr>
				<tr>
					<td></td>
				</tr>
				<tr>
					<td></td>
				</tr>
				<tr>
					<td><strong>Municipio:</strong></td>
					<td>
						<logic:equal name="nomeEmpresa"	value="SAAE-JUAZEIRO">
							<html:text property="idMunicipio" size="5" maxlength="4"
									   onkeypress="javascript:form.target='';limparUltimosCampos(1);validaEnter(event, 'filtrarImovelOutrosCriteriosWizardAction.do?action=exibirFiltrarImovelOutrosCriteriosLocalizarImoveis&objetoConsulta=1', 'idMunicipio');return isCampoNumerico(event);"
									   tabindex="10" />
							<a href="javascript:chamarPopup('exibirPesquisarMunicipioAction.do', 'idMunicipio', null, document.ImovelOutrosCriteriosActionForm.idMunicipio.value, 250, 470, 'Informe Município.');">
								<img width="23" height="21" border="0"
									 src="<bean:message key="caminho.imagens"/>pesquisa.gif" title="Pesquisar"/></a>
							
							<logic:notEqual name="ImovelOutrosCriteriosActionForm" property="nomeMunicipio"
									 		value="Município inexistente">
									<html:text property="nomeMunicipio" size="45" readonly="true" 
											   style="background-color:#EFEFEF; border:0; color: #000000" 
									/>
							</logic:notEqual> 
							<logic:equal name="ImovelOutrosCriteriosActionForm" property="nomeMunicipio"
										 value="Município inexistente">
								<html:text property="nomeMunicipio" size="45" readonly="true"
										   style="background-color:#EFEFEF; border:0; color: #ff0000" 
								/>
							</logic:equal> 
						</logic:equal>
					
					<logic:notEqual name="nomeEmpresa" value="SAAE-JUAZEIRO">
						
						<logic:present name="parametroGerarRelatorio">
							<logic:equal name="parametroGerarRelatorio"
										 value="RelatorioImoveisEndereco">
								<html:text property="idMunicipio" size="5" maxlength="4"
										   onkeypress="javascript:form.target='';limparUltimosCampos(1);validaEnter(event, 'filtrarImovelOutrosCriteriosWizardAction.do?action=exibirFiltrarImovelOutrosCriteriosLocalizarImoveis&objetoConsulta=1', 'idMunicipio');return isCampoNumerico(event);"
										   tabindex="10" disabled="true" 
								/>
								<img width="23" height="21" border="0"
									src="<bean:message key="caminho.imagens"/>pesquisa.gif"
									title="Pesquisar" 
								/>
							</logic:equal>
							<logic:notEqual name="parametroGerarRelatorio"
								value="RelatorioImoveisEndereco">
								<html:text property="idMunicipio" size="5" maxlength="4"
									onkeypress="javascript:form.target='';limparUltimosCampos(1);validaEnter(event, 'filtrarImovelOutrosCriteriosWizardAction.do?action=exibirFiltrarImovelOutrosCriteriosLocalizarImoveis&objetoConsulta=1', 'idMunicipio');return isCampoNumerico(event);"
									tabindex="10" />
								<a href="javascript:chamarPopup('exibirPesquisarMunicipioAction.do', 'idMunicipio', null, document.ImovelOutrosCriteriosActionForm.idMunicipio.value, 250, 470, 'Informe Município.');">
									<img width="23" height="21" border="0"
										src="<bean:message key="caminho.imagens"/>pesquisa.gif"
										title="Pesquisar" /></a>
							</logic:notEqual>
						</logic:present> 
						<logic:notPresent name="parametroGerarRelatorio">
							<html:text property="idMunicipio" size="5" maxlength="4"
									   onkeypress="javascript:form.target='';limparUltimosCampos(1);validaEnter(event, 'filtrarImovelOutrosCriteriosWizardAction.do?action=exibirFiltrarImovelOutrosCriteriosLocalizarImoveis&objetoConsulta=1', 'idMunicipio');return isCampoNumerico(event);"
									   tabindex="10" 
							/>
							<a href="javascript:chamarPopup('exibirPesquisarMunicipioAction.do', 'idMunicipio', null, document.ImovelOutrosCriteriosActionForm.idMunicipio.value, 250, 470, 'Informe Município.');">
								<img width="23" height="21" border="0"
									src="<bean:message key="caminho.imagens"/>pesquisa.gif"
									title="Pesquisar" /></a>
						</logic:notPresent> 
						<logic:notEqual name="ImovelOutrosCriteriosActionForm" property="nomeMunicipio"
										value="Município inexistente">
							<html:text property="nomeMunicipio" size="45" readonly="true" 
								style="background-color:#EFEFEF; border:0; color: #000000" 
							/>
						</logic:notEqual> 
						<logic:equal name="ImovelOutrosCriteriosActionForm" property="nomeMunicipio"
									 value="Município inexistente">
							<html:text property="nomeMunicipio" size="45" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" 
							/>
						</logic:equal> 
					</logic:notEqual>
					<a href="javascript:limparMBCL(1);"> <img
						src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar" /></a></td>
				</tr>

				<tr>
					<td><strong>Bairro:</strong></td>
					<td>
					<logic:equal name="nomeEmpresa"	value="SAAE-JUAZEIRO">
						<html:text maxlength="3" property="idBairro" size="5"
								   onkeyup="validarBairro();"
								   onkeypress="form.target='';limparUltimosCampos(2);validaEnterDependencia(event, 'filtrarImovelOutrosCriteriosWizardAction.do?action=exibirFiltrarImovelOutrosCriteriosLocalizarImoveis&objetoConsulta=1', this, document.forms[0].idMunicipio.value, 'Município');return isCampoNumerico(event);"
								   tabindex="11" 
						/>
						<a href="javascript:chamarPopup('exibirPesquisarBairroAction.do', 'idBairro', 'idMunicipio', document.ImovelOutrosCriteriosActionForm.idMunicipio.value, 275, 480, 'Informe Município.');">
							<img width="23" height="21" border="0"
								src="<bean:message key="caminho.imagens"/>pesquisa.gif"
								title="Pesquisar" /></a>
						
						<logic:notEqual name="ImovelOutrosCriteriosActionForm" property="nomeBairro"
										value="Bairro inexistente">
							<html:text property="nomeBairro" size="45" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" 
							/>
						</logic:notEqual> 
						<logic:equal name="ImovelOutrosCriteriosActionForm" property="nomeBairro"
									 value="Bairro inexistente">
							<html:text property="nomeBairro" size="45" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" 
							/>
						</logic:equal> 
					</logic:equal>
					
					<logic:notEqual name="nomeEmpresa" value="SAAE-JUAZEIRO">
					<logic:present name="parametroGerarRelatorio">
						<logic:equal name="parametroGerarRelatorio"
							value="RelatorioImoveisEndereco">
							<html:text maxlength="3" property="idBairro" size="5"
								onkeyup="validarBairro();"
								onkeypress="form.target='';limparUltimosCampos(2);validaEnterDependencia(event, 'filtrarImovelOutrosCriteriosWizardAction.do?action=exibirFiltrarImovelOutrosCriteriosLocalizarImoveis&objetoConsulta=1', this, document.forms[0].idMunicipio.value, 'Município');return isCampoNumerico(event);"
								tabindex="11" disabled="true" />
							<img width="23" height="21" border="0"
								src="<bean:message key="caminho.imagens"/>pesquisa.gif"
								title="Pesquisar" />
						</logic:equal>
						<logic:notEqual name="parametroGerarRelatorio"
							value="RelatorioImoveisEndereco">
							<html:text maxlength="3" property="idBairro" size="5"
								onkeyup="validarBairro();"
								onkeypress="form.target='';limparUltimosCampos(2);validaEnterDependencia(event, 'filtrarImovelOutrosCriteriosWizardAction.do?action=exibirFiltrarImovelOutrosCriteriosLocalizarImoveis&objetoConsulta=1', this, document.forms[0].idMunicipio.value, 'Município');return isCampoNumerico(event);"
								tabindex="11" />
							<a href="javascript:chamarPopup('exibirPesquisarBairroAction.do', 'idBairro', 'idMunicipio', document.ImovelOutrosCriteriosActionForm.idMunicipio.value, 275, 480, 'Informe Município.');">
							<img width="23" height="21" border="0"
								src="<bean:message key="caminho.imagens"/>pesquisa.gif"
								title="Pesquisar" /></a>
						</logic:notEqual>
					</logic:present> <logic:notPresent name="parametroGerarRelatorio">
						<html:text maxlength="3" property="idBairro" size="5"
							onkeyup="validarBairro();"
							onkeypress="form.target='';limparUltimosCampos(2);validaEnterDependencia(event, 'filtrarImovelOutrosCriteriosWizardAction.do?action=exibirFiltrarImovelOutrosCriteriosLocalizarImoveis&objetoConsulta=1', this, document.forms[0].idMunicipio.value, 'Município');return isCampoNumerico(event);"
							tabindex="11" />
						<a href="javascript:chamarPopup('exibirPesquisarBairroAction.do', 'idBairro', 'idMunicipio', document.ImovelOutrosCriteriosActionForm.idMunicipio.value, 275, 480, 'Informe Município.');">
						<img width="23" height="21" border="0"
							src="<bean:message key="caminho.imagens"/>pesquisa.gif"
							title="Pesquisar" /></a>
					</logic:notPresent> <logic:notEqual
						name="ImovelOutrosCriteriosActionForm" property="nomeBairro"
						value="Bairro inexistente">
						<html:text property="nomeBairro" size="45" readonly="true"
							style="background-color:#EFEFEF; border:0; color: #000000" />
					</logic:notEqual> <logic:equal
						name="ImovelOutrosCriteriosActionForm" property="nomeBairro"
						value="Bairro inexistente">
						<html:text property="nomeBairro" size="45" readonly="true"
							style="background-color:#EFEFEF; border:0; color: #ff0000" />
					</logic:equal> 
					</logic:notEqual>
					
					<a href="javascript:limparMBCL(2);"> <img
						src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar" /></a></td>
				</tr>
						
				<tr>
					<td><strong>Logradouro:</strong></td>
					<td>
						<logic:equal name="nomeEmpresa"	value="SAAE-JUAZEIRO">
							<html:text property="idLogradouro" size="9"
								maxlength="9" tabindex="9"
								onkeypress="form.target='';limparUltimosCampos(3);validaEnterDependencia(event, 'filtrarImovelOutrosCriteriosWizardAction.do?action=exibirFiltrarImovelOutrosCriteriosLocalizarImoveis&objetoConsulta=1', this, document.forms[0].idMunicipio.value, 'município');return isCampoNumerico(event);" />
							<a href="javascript:chamarPopup('exibirPesquisarLogradouroAction.do', 'idLogradouro', null, document.ImovelOutrosCriteriosActionForm.idLogradouro.value, 275, 480, 'Informe Logradouro.');">
								<img src="<bean:message key='caminho.imagens'/>pesquisa.gif" width="23"
									height="21" border="0" style="cursor:hand;" alt="Pesquisar" title="Pesquisar"></a> 
							<logic:present 	name="Logradouro inexistente">
								<logic:equal name="ImovelOutrosCriteriosActionForm" property="nomeLogradouro"
											 value="Logradouro inexistente">
									<html:text property="nomeLogradouro" size="40" maxlength="30"
										readonly="true"
										style="background-color:#EFEFEF; border:0; color: #ff0000"  />
								</logic:equal>
								<logic:notEqual name="ImovelOutrosCriteriosActionForm" property="nomeLogradouro"
												value="Logradouro inexistente">
									<html:text property="nomeLogradouro" size="40" maxlength="30"
										readonly="true"
										style="background-color:#EFEFEF; border:0; color: #000000" />
								</logic:notEqual>
							</logic:present> 
							<logic:notPresent name="Logradouro inexistente">
								<logic:empty name="ImovelOutrosCriteriosActionForm"
									property="nomeLogradouro">
									<html:text property="nomeLogradouro" value="" size="40"
										maxlength="30" readonly="true"
										style="background-color:#EFEFEF; border:0; color: #ff0000" />
								</logic:empty>
								<logic:notEmpty name="ImovelOutrosCriteriosActionForm"
									property="nomeLogradouro">
									<html:text property="nomeLogradouro" size="40" maxlength="30"
										readonly="true"
										style="background-color:#EFEFEF; border:0; color: #000000" />
								</logic:notEmpty>
							</logic:notPresent>
						</logic:equal>
						
						<logic:notEqual name="nomeEmpresa" value="SAAE-JUAZEIRO">
						
							<logic:present name="parametroGerarRelatorio">
								<logic:equal name="parametroGerarRelatorio"
									value="RelatorioImoveisEndereco">
									<html:text maxlength="9" property="idLogradouro" size="9"
										onkeypress="form.target='';limparUltimosCampos(3);validaEnterDependencia(event, 'filtrarImovelOutrosCriteriosWizardAction.do?action=exibirFiltrarImovelOutrosCriteriosLocalizarImoveis&objetoConsulta=1', this, document.forms[0].idMunicipio.value, 'município');return isCampoNumerico(event);"
										tabindex="12" disabled="true" />
									<img width="23" height="21" border="0"
										src="<bean:message key="caminho.imagens"/>pesquisa.gif"
										title="Pesquisar" />
								</logic:equal>
								<logic:notEqual name="parametroGerarRelatorio"
									value="RelatorioImoveisEndereco">
									<html:text maxlength="9" property="idLogradouro" size="9"
										onkeypress="form.target='';limparUltimosCampos(3);validaEnterDependencia(event, 'filtrarImovelOutrosCriteriosWizardAction.do?action=exibirFiltrarImovelOutrosCriteriosLocalizarImoveis&objetoConsulta=1', this, document.forms[0].idMunicipio.value, 'município');return isCampoNumerico(event);"
										tabindex="12" />
									<a href="javascript:chamarPopup('exibirPesquisarLogradouroAction.do', 'idLogradouro', null, document.ImovelOutrosCriteriosActionForm.idLogradouro.value, 275, 480, 'Informe Logradouro.');">
									<img width="23" height="21" border="0"
										src="<bean:message key="caminho.imagens"/>pesquisa.gif"
										title="Pesquisar" /></a>
								</logic:notEqual>
							</logic:present> 
							<logic:notPresent name="parametroGerarRelatorio">
								<html:text maxlength="9" property="idLogradouro" size="9"
									onkeypress="form.target='';limparUltimosCampos(3);validaEnterDependencia(event, 'filtrarImovelOutrosCriteriosWizardAction.do?action=exibirFiltrarImovelOutrosCriteriosLocalizarImoveis&objetoConsulta=1', this, document.forms[0].idMunicipio.value, 'município');return isCampoNumerico(event);"
									tabindex="12" />
								<a href="javascript:chamarPopup('exibirPesquisarLogradouroAction.do', 'idLogradouro', null, document.ImovelOutrosCriteriosActionForm.idLogradouro.value, 275, 480, 'Informe Logradouro.');">
								<img width="23" height="21" border="0"
									src="<bean:message key="caminho.imagens"/>pesquisa.gif"
									title="Pesquisar" /></a>
							</logic:notPresent> 
							<logic:notEqual
								name="ImovelOutrosCriteriosActionForm" property="nomeLogradouro"
								value="Logradouro inexistente">
								<html:text property="nomeLogradouro" size="45" readonly="true"
									style="background-color:#EFEFEF; border:0; color: #000000" />
							</logic:notEqual> 
							<logic:equal
								name="ImovelOutrosCriteriosActionForm" property="nomeLogradouro"
								value="Logradouro inexistente">
								<html:text property="nomeLogradouro" size="45" readonly="true"
									style="background-color:#EFEFEF; border:0; color: #ff0000" />
							</logic:equal> 
					
						</logic:notEqual>
						
					<a href="javascript:limparMBCL(3);"> <img
						src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar" /></a></td>
				</tr>
				<tr>
					<td><strong>CEP:</strong></td>
					<td><logic:present name="parametroGerarRelatorio">
						<logic:equal name="parametroGerarRelatorio"
							value="RelatorioCadastroConsumidoresInscricao">
							<html:text maxlength="8" property="CEP" size="8" tabindex="13"
								onkeypress="javascript:limparUltimosCampos(4);validaEnter(event, 'filtrarImovelOutrosCriteriosWizardAction.do?action=exibirFiltrarImovelOutrosCriteriosLocalizarImoveis&objetoConsulta=1', 'CEP');return isCampoNumerico(event);" />
							<img width="23" height="21" border="0"
								src="<bean:message key="caminho.imagens"/>pesquisa.gif"
								title="Pesquisar" />
						</logic:equal>
						<logic:notEqual name="parametroGerarRelatorio"
							value="RelatorioCadastroConsumidoresInscricao">
							<logic:equal name="parametroGerarRelatorio"
								value="RelatorioImoveisEndereco">
								<html:text maxlength="8" property="CEP" size="8" tabindex="13"
									onkeypress="limparUltimosCampos(4);validaEnter(event, 'filtrarImovelOutrosCriteriosWizardAction.do?action=exibirFiltrarImovelOutrosCriteriosLocalizarImoveis&objetoConsulta=1', 'CEP');return isCampoNumerico(event);" />
								<a href="javascript:abrirPopup('exibirPesquisarCepAction.do?&indicadorUsoTodos=1', 400, 800);">
								<img width="23" height="21" border="0"
									src="<bean:message key="caminho.imagens" />pesquisa.gif"
									title="Pesquisar" /></a> 
							</logic:equal>
							<logic:notEqual name="parametroGerarRelatorio"
								value="RelatorioImoveisEndereco">
								<html:text maxlength="8" property="CEP" size="8" tabindex="13"
									onkeypress="limparUltimosCampos(4);validaEnter(event, 'filtrarImovelOutrosCriteriosWizardAction.do?action=exibirFiltrarImovelOutrosCriteriosLocalizarImoveis&objetoConsulta=1', 'CEP');return isCampoNumerico(event);" />
								<a
									href="javascript:abrirPopup('exibirPesquisarCepAction.do?&indicadorUsoTodos=1', 400, 800);">
								<img width="23" height="21" border="0"
									src="<bean:message key="caminho.imagens"/>pesquisa.gif"
									title="Pesquisar" /></a>
							</logic:notEqual>
						</logic:notEqual>
					</logic:present> <logic:notPresent name="parametroGerarRelatorio">
						<html:text maxlength="8" property="CEP" size="8" tabindex="13"
							onkeypress="limparUltimosCampos(4);validaEnter(event, 'filtrarImovelOutrosCriteriosWizardAction.do?action=exibirFiltrarImovelOutrosCriteriosLocalizarImoveis&objetoConsulta=1', 'CEP');return isCampoNumerico(event);" />
						<a
							href="javascript:abrirPopup('exibirPesquisarCepAction.do?&indicadorUsoTodos=1', 400, 800);">
						<img width="23" height="21" border="0"
							src="<bean:message key="caminho.imagens"/>pesquisa.gif"
							title="Pesquisar" /></a>
					</logic:notPresent> <logic:notEqual
						name="ImovelOutrosCriteriosActionForm" property="descricaoCep"
						value="CEP Inexistente">
						<html:text property="descricaoCep" size="45" readonly="true"
							style="background-color:#EFEFEF; border:0; color: #000000" />
					</logic:notEqual> <logic:equal
						name="ImovelOutrosCriteriosActionForm" property="descricaoCep"
						value="CEP Inexistente">
						<html:text property="descricaoCep" size="45" readonly="true"
							style="background-color:#EFEFEF; border:0; color: #ff0000" />
					</logic:equal> <a href="javascript:limparMBCL(4);"> <img
						src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar" /></a></td>
				</tr>

				<tr>
					<td colspan="4"></td>
				<tr>
				<tr>
					<td colspan="4">
					<table width="100%" align="center" bgcolor="#99CCFF" border="0">
						<tr>
							<td colspan="2"><strong>Informe os dados da Rota Inicial:</strong></td>
						</tr>
						<tr bgcolor="#cbe5fe">
							<td width="100%" align="center" colspan="2">

							<table width="100%" border="0">
								<tr bgcolor="#cbe5fe">
									<td height="10" width="20%"><strong>Rota:</strong></td>
									<td><html:text property="cdRotaInicial" maxlength="4" size="7"
										onkeypress="return isCampoNumerico(event);"
										onkeyup="replicarRota();"
										/></td>
								</tr>

								<tr bgcolor="#cbe5fe">
									<td height="10"><strong>Seq. da Rota:</strong></td>
									<td><html:text property="sequencialRotaInicial" maxlength="6"
										onkeypress="return isCampoNumerico(event);"
										size="7" onkeyup="replicarSequencialRota();" /></td>
								</tr>
							</table>
							</td>
						</tr>


					</table>
					</td>
				</tr>
				<tr>
					<td colspan="4">&nbsp;</td>
				</tr>
				<tr>
					<td colspan="4">
					<table width="100%" align="center" bgcolor="#99CCFF" border="0">
						<tr>
							<td colspan="2"><strong>Informe os dados da Rota Final:</strong></td>
						</tr>
						<tr bgcolor="#cbe5fe">
							<td width="100%" align="center" colspan="2">

							<table width="100%" border="0">

								<tr bgcolor="#cbe5fe">
									<td height="10" width="20%"><strong>Rota:</strong></td>
									<td><html:text property="cdRotaFinal" maxlength="4" size="7"
										onkeypress="return isCampoNumerico(event);"
										 /></td>
								</tr>

								<tr bgcolor="#cbe5fe">
									<td height="10"><strong>Seq. da Rota:</strong></td>
									<td><html:text property="sequencialRotaFinal" maxlength="6"
										onkeypress="return isCampoNumerico(event);"									
										size="7" /></td>
								</tr>
							</table>
							</td>
						</tr>
					</table>
				<tr>
					<td colspan="4">&nbsp;</td>
				<tr>
				<tr>
					<td colspan="4">
					<table width="100%" align="center" bgcolor="#99CCFF" border="0">
						<tr>
							<td colspan="2"><strong>Classificação Alternativa:</strong></td>
						</tr>
						<tr bgcolor="#cbe5fe">
							<td width="100%" align="center" colspan="2">
							<table width="100%" border="0">
								<tr>
									<td height="10" width="20%"><strong>Classificação:</strong></td>
									<td><html:select property="ordenacaoRelatorio">
										<html:option value="-1">&nbsp;</html:option>
										<html:option value="rota">ROTA</html:option>
									</html:select></td>
								</tr>
							</table>
							</td>
						</tr>
					</table>
					</td>
				</tr>

				<tr>
					<td colspan="4"></td>
				<tr>
				<tr>
					<td colspan="2">
					<div align="right"><jsp:include
						page="/jsp/util/wizard/navegacao_botoes_wizard_valida_avancar_valida_voltar.jsp?numeroPagina=1" /></div>
					</td>
				</tr>
			</table>

			<p>&nbsp;</p>
			</td>
		</tr>


	</table>

	<%@ include file="/jsp/util/rodape.jsp"%>

</html:form></div>
</body>


<%@ include file="/jsp/util/telaespera.jsp"%>

<script>
document.getElementById('botaoConcluir').onclick = function() { botaoAvancarTelaEspera('/gsan/filtrarImovelOutrosCriteriosWizardAction.do?concluir=true&action=validarImovelOutrosCriterios'); }
</script>
</html:html>
