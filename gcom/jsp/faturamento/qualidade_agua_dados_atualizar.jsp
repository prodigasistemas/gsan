<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
<head>
<%@ include file="/jsp/util/titulo.jsp"%>

<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
<%@ page import="gcom.util.ConstantesSistema"%>

<html:javascript formName="AtualizarQualidadeAguaActionForm"
	dynamicJavascript="false" staticJavascript="true" />

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>

<script>

	var bCancel = false;

	function validateAtualizarQualidadeAguaActionForm(form) {

		if (bCancel){
			return true;
		}else{
			
			return testarCampoValorZero(document.AtualizarQualidadeAguaActionForm.logradouro, 'Logradouro') && 
				validateCaracterEspecial(form) && 
				validateRequired(form) && 
				validateLong(form) ;
		}
	}
	
 
	
	function IntegerValidations () {
		this.aa = new Array("idLocalidade", "Localidade deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
		this.ac = new Array("idSetorComercial", "Unidade Organizacional deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
		//this.ad = new Array("presidente", "Presidente deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
		//this.ae = new Array("diretorComercial", "Diretor Comercial deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
		//this.af = new Array("numeroTelefoneAtendimento", "Telefone de Atendimento deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
	}

	

	function caracteresespeciais () {
		this.aa = new Array("idLocalidade", "Localidade possui caracteres especiais.", new Function ("varName", " return this[varName];"));
		this.ab = new Array("idSetorComercial", "Setor Comercial possui caracteres especiais.", new Function ("varName", " return this[varName];"));
		//this.ac = new Array("abreviaturaEmpresa", "Abreviatura da Empresa possui caracteres especiais.", new Function ("varName", " return this[varName];"));
		//this.ad = new Array("cnpj", "CNPJ possui caracteres especiais.", new Function ("varName", " return this[varName];"));
		//this.af = new Array("unidadeOrganizacionalPresidencia", "Unidade Organizacional possui caracteres especiais.", new Function ("varName", " return this[varName];"));
		//this.ag = new Array("presidente", "Presidente possui caracteres especiais.", new Function ("varName", " return this[varName];"));
		//this.ah = new Array("diretorComercial", "Diretor Comercial possui caracteres especiais.", new Function ("varName", " return this[varName];"));
		//this.ai = new Array("numeroTelefoneAtendimento", "Telefone de Atendimento possui caracteres especiais.", new Function ("varName", " return this[varName];"));
	}

	function required () {
		
		this.aa = new Array("referencia", "Informe Referência.", new Function ("varName", " return this[varName];"));
		
	}


   // function validateInformarParametrosSistemaActionForm(form) {
	//	var endereco = document.getElementById("validarEndereco").value;
		//var retorno = false;
//
	//	if (endereco == "1"){
	//	/	retorno = true;
	//	}/ else{
			//alert("Informe Endereço.");
		//}//

		//return retorno;
	//}
	
	
	//function validarForm(){

		//var form = document.forms[0];
		//form.action = 'inserirQualidadeAguaAction.do';
		
	//	if (form.referencia.readOnly == false) {
		
	//		if(validateInserirQualidadeAguaActionForm(form)){
	 //   			submeterFormPadrao(form);
	//		}
	//	} else {
		
	//		submeterFormPadrao(form);
//}
//}



	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {

    var form = document.forms[0];

    if (tipoConsulta == 'localidade') {
      limparPesquisaLocalidade();
      form.idLocalidade.value = codigoRegistro;
      form.localidadeDescricao.value = descricaoRegistro;
      form.localidadeDescricao.style.color = "#000000";
      form.idSetorComercial.focus();
    }

    	if (tipoConsulta == 'setorComercial') {
	      limparPesquisaSetorComercial();
	      form.idSetorComercial.value = codigoRegistro;
	      form.setorComercialDescricao.value = descricaoRegistro;
	      form.setorComercialDescricao.style.color = "#000000";
	      form.fonteCaptacao.focus();
   	 	}
  	}

	function limparPesquisaDescricaoLocalidade() {
    var form = document.forms[0];
      form.localidadeDescricao.value = "";
  	}

	function limparPesquisaLocalidade() {
    var form = document.forms[0];

      form.idLocalidade.value = "";
      form.localidadeDescricao.value = "";
  	}

	function limparDescricaoLocalidade(){
    var form = document.forms[0];
    form.localidadeDescricao.value = "";

	}

	function limparDescricaoSetorComercial(){
    var form = document.forms[0];
    form.setorComercialDescricao.value = "";


	}


	function limparPesquisaSetorComercial() {
    var form = document.forms[0];

      form.idSetorComercial.value = "";
      form.setorComercialDescricao.value = "";
	}

	//function verificarInserirTodos(){
	//	var form = AtualizarQualidadeAguaActionForm;
       	
    //   	if (form.indicadorInserirTodos.checked == true) {
    //   		form.indicadorInserirTodos.value = '1';
    //   	} else {
    //   		form.indicadorInserirTodos.value = '';
    //   	}
    //   	form.action = 'exibirAtualizarQualidadeAguaAction.do?indicadorInserirTodos='+form.indicadorInserirTodos.value;
    //   	form.submit();
       	
//	}

	function verificarAnoMesReferencia(){
		var form = AtualizarQualidadeAguaActionForm;
       	
       	form.action = 'exibirAtualizarQualidadeAguaAction.do';
       	form.submit();
       	
	}	
	
	//function verificarChecado(valor){
		//form = document.forms[0];
		//if(valor == "1"){
		 //	form.indicadorInserirTodos.checked = true;
		 //}else{
		 	//form.indicadorInserirTodos.checked = false;
		//}
	//}

	
</script>
</head>

<body leftmargin="5" topmargin="5"
	onload="javascript:setarFoco('${requestScope.nomeCampo}');">

<html:form action="/atualizarQualidadeAguaWizardAction" method="post"
	onsubmit="return validateAtualizarQualidadeAguaActionForm(this);">

	<jsp:include
		page="/jsp/util/wizard/navegacao_abas_wizard_valida_avancar.jsp?numeroPagina=1" />

	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>

	<input type="hidden" name="numeroPagina" value="1" />
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
			<td width="655" valign="top" class="centercoltext">
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
					<td class="parabg">Atualizar Qualidade da Água</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<table width="100%" border="0" dwcopytype="CopyTableRow">
				<tr>
					<td>Para atualizar a qualidade da água, informe os dados abaixo:
					<td align="right"></td>
				</tr>
			</table>

			<table width="100%" border="0">



				<tr>
					<td><strong>Referência:<font color="#FF0000">*</font></strong></td>

					<td><html:text property="referencia" size="7" maxlength="7"
						onkeyup="mascaraAnoMes(this, event);" readonly="true"
						style="background-color:#EFEFEF; border:0;" /> (mm/aaaa)</td>
				</tr>

				<tr>
					<td width="19%"><strong>Localidade:</strong></td>
					<td width="81%" height="24" colspan="2"><html:text
						property="idLocalidade" maxlength="3" size="3"
						onkeypress="javascript:limparPesquisaDescricaoLocalidade();limparPesquisaSetorComercial();
	                   		validaEnterComMensagem(event,'exibirInserirQualidadeAguaAction.do','idLocalidade','Localidade');"
						readonly="true" style="background-color:#EFEFEF; border:0;" /> <logic:present
						name="localidadeEncontrada">
						<logic:equal name="localidadeEncontrada" value="exception">
							<html:text property="localidadeDescricao" size="50"
								maxlength="50" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:equal>
						<logic:notEqual name="localidadeEncontrada" value="exception">
							<html:text property="localidadeDescricao" size="50"
								maxlength="50" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEqual>
					</logic:present> <logic:notPresent name="localidadeEncontrada">
						<logic:empty name="AtualizarQualidadeAguaActionForm"
							property="idLocalidade">
							<html:text property="localidadeDescricao" size="50"
								maxlength="50" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:empty>
						<logic:notEmpty name="AtualizarQualidadeAguaActionForm"
							property="idLocalidade">
							<html:text property="localidadeDescricao" size="50"
								maxlength="50" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEmpty>
					</logic:notPresent></td>
				</tr>




				<tr>
					<td><strong>Setor Comercial:</strong></td>
					<td height="24" colspan="2"><html:text maxlength="3"
						property="idSetorComercial" size="3"
						onkeypress="javascript:limparDescricaoSetorComercial();
                   		validaEnterDependenciaComMensagem(event,'exibirInserirQualidadeAguaAction.do', document.forms[0].idSetorComercial, document.forms[0].idLocalidade.value, 'Localidade', 'Setor Comercial');"
						readonly="true" style="background-color:#EFEFEF; border:0;" /> <logic:present
						name="codigoSetorComercialNaoEncontrada" scope="request">
						<input type="text" name="setorComercialDescricao" size="50"
							readonly="true" maxlength="50"
							style="background-color:#EFEFEF; border:0; color: #ff0000" />
					</logic:present> <logic:present
						name="codigoSetorComercialEncontrado">
						<logic:equal name="codigoSetorComercialEncontrado"
							value="exception">
							<html:text property="setorComercialDescricao" size="50"
								maxlength="50" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:equal>
						<logic:notEqual name="codigoSetorComercialEncontrado"
							value="exception">
							<html:text property="setorComercialDescricao" size="50"
								maxlength="50" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEqual>
					</logic:present> <logic:notPresent
						name="codigoSetorComercialEncontrado">
						<logic:empty name="AtualizarQualidadeAguaActionForm"
							property="idSetorComercial">
							<html:text property="setorComercialDescricao" size="50"
								maxlength="50" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:empty>
						<logic:notEmpty name="AtualizarQualidadeAguaActionForm"
							property="idSetorComercial">
							<html:text property="setorComercialDescricao" size="50"
								maxlength="50" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEmpty>
					</logic:notPresent></td>
				</tr>

				<tr>
					
					<td><strong>Sistema de Abastecimento:</strong></td>

					<td><html:text property="sistemaAbastecimento" size="30" maxlength="30"
						readonly="true"
						style="background-color:#EFEFEF; border:0;" />
					</td>
				</tr>

				<logic:present name="desabilitaCampos">
					<tr>
						<td><strong>Fonte de Captação:</strong></td>

						<td colspan="2"><strong> 
						<html:select property="fonteCaptacao"
							style="width: 270px;" 
							disabled="true">

							<html:option
								value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;
							</html:option>

							<logic:present name="colecaoFonteCaptacao" scope="request">
								<html:options collection="colecaoFonteCaptacao"
									labelProperty="descricao" property="id" />
							</logic:present>
						</html:select> </strong></td>

					</tr>

					<tr>
						<td><strong>Turbidez:</strong></td>
						<td align="center"><strong>Índice mensal:</strong> <html:text
							property="indiceMensalTurbidez" 
							size="6" 
							maxlength="20"
							readonly="true" 
							style="background-color:#EFEFEF; border:0;" 
							onkeypress="return isCampoNumerico(event);"/></td>
						<td><strong>Padrão:</strong> <html:text property="padraoTurbidez"
							size="20" maxlength="20" readonly="true"
							style="background-color:#EFEFEF; border:0;" /></td>
					</tr>




					<tr>
						<td><strong>Cloro Residual:</strong></td>
						<td align="center"><strong>Índice mensal:</strong> <html:text
							property="indiceMensalCloroResidual" size="6" maxlength="20"
							readonly="true" style="background-color:#EFEFEF; border:0;" 
							onkeypress="return isCampoNumerico(event);"/></td>
						<td><strong>Padrão:</strong> <html:text
							property="padraoCloroResidual" size="20" maxlength="20"
							readonly="true" style="background-color:#EFEFEF; border:0;" /></td>
					</tr>

					<tr>
						<td><strong>PH:</strong></td>
						<td align="center"><strong>Índice mensal:</strong> <html:text
							property="indiceMensalPH" size="6" maxlength="20" readonly="true"
							style="background-color:#EFEFEF; border:0;" 
							onkeypress="return isCampoNumerico(event);"/></td>
						<td><strong>Padrão:</strong> <html:text property="padraoPH"
							size="20" maxlength="20" readonly="true"
							style="background-color:#EFEFEF; border:0;" /></td>
					</tr>

					<tr>
						<td><strong>Cor:</strong></td>
						<td align="center"><strong>Índice mensal:</strong> <html:text
							property="indiceMensalCor" size="6" maxlength="20"
							readonly="true" style="background-color:#EFEFEF; border:0;" 
							onkeypress="return isCampoNumerico(event);"/></td>
						<td><strong>Padrão:</strong> <html:text property="padraoCor"
							size="20" maxlength="20" readonly="true"
							style="background-color:#EFEFEF; border:0;" /></td>
					</tr>

					<tr>
						<td><strong>Flúor:</strong></td>
						<td align="center"><strong>Índice mensal:</strong> <html:text
							property="indiceMensalFluor" size="6" maxlength="20"
							readonly="true" style="background-color:#EFEFEF; border:0;" 
							onkeypress="return isCampoNumerico(event);"/></td>
						<td><strong>Padrão:</strong> <html:text property="padraoFluor"
							size="20" maxlength="20" readonly="true"
							style="background-color:#EFEFEF; border:0;" /></td>
					</tr>
					
					<tr>
						<td><strong>EColi:</strong></td>
						<td align="center"><strong>Índice mensal:</strong> <html:text
							property="indiceMensalEColi" size="6" maxlength="20"
							readonly="true" style="background-color:#EFEFEF; border:0;" 
							onkeypress="return isCampoNumerico(event);"/></td>
						<td><strong>Padrão:</strong> <html:text property="padrao"
							size="20" maxlength="20" readonly="true"
							style="background-color:#EFEFEF; border:0;" /></td>
					</tr>

					<tr>
						<td><strong>Ferro:</strong></td>
						<td align="center"><strong>Índice mensal:</strong> <html:text
							property="indiceMensalFerro" size="6" maxlength="20"
							readonly="true" style="background-color:#EFEFEF; border:0;" 
							onkeypress="return isCampoNumerico(event);"/></td>
						<td><strong>Padrão:</strong> <html:text property="padraoFerro"
							size="20" maxlength="20" readonly="true"
							style="background-color:#EFEFEF; border:0;" /></td>
					</tr>

					<tr>
						<td><strong>Coliformes Totais:</strong></td>
						<td align="center"><strong>Índice mensal:</strong> <html:text
							property="indiceMensalColiformesTotais" size="6" maxlength="20"
							readonly="true" style="background-color:#EFEFEF; border:0;" 
							onkeypress="return isCampoNumerico(event);"/></td>
						<td><strong>Padrão:</strong> <html:text
							property="padraoColiformesTotais" size="20" maxlength="20"
							readonly="true" style="background-color:#EFEFEF; border:0;" /></td>
					</tr>

					<tr>
						<td><strong>Coliformes Fecais:</strong></td>
						<td align="center"><strong>Índice mensal:</strong> <html:text
							property="indiceMensalColiformesFecais" size="6" maxlength="20"
							readonly="true" style="background-color:#EFEFEF; border:0;" 
							onkeypress="return isCampoNumerico(event);"/></td>
						<td><strong>Padrão:</strong> <html:text
							property="padraoColiformesFecais" size="20" maxlength="20"
							readonly="true" style="background-color:#EFEFEF; border:0;" /></td>
					</tr>

					<tr>
						<td><strong>Nitrato:</strong></td>
						<td align="center"><strong>Índice mensal:</strong> <html:text
							property="indiceMensalNitrato" size="6" maxlength="20"
							readonly="true" style="background-color:#EFEFEF; border:0;" 
							onkeypress="return isCampoNumerico(event);"/></td>
						<td><strong>Padrão:</strong> <html:text property="padraoNitrato"
							size="20" maxlength="20" readonly="true"
							style="background-color:#EFEFEF; border:0;" /></td>
					</tr>

					<tr>
						<td><strong>Coliformes Termotolerantes:</strong></td>

						<td align="center"><strong>Índice mensal:</strong><html:text
							property="indiceMensalColiformesTermotolerantes" size="6"
							maxlength="6"
							onkeyup="formataValorMonetario(this, 5)" readonly="true"
							style="background-color:#EFEFEF; border:0;" 
							onkeypress="return isCampoNumerico(event);"/></td>
						<td><strong>Padrão:</strong> <html:text
							property="padraoColiformesTermotolerantes" size="20"
							maxlength="20" tabindex="21" readonly="true"
							style="background-color:#EFEFEF; border:0;" /></td>
					</tr>
					
					<tr>
						<td><strong>Alcalinidade:</strong></td>

						<td align="center"><strong>Índice mensal:</strong><html:text
							property="indiceMensalAlcalinidade" size="6"
							maxlength="6"
							onkeyup="formataValorMonetario(this, 5)" readonly="true"
							style="background-color:#EFEFEF; border:0;" 
							onkeypress="return isCampoNumerico(event);"/></td>
						<td><strong>Padrão:</strong> <html:text
							property="padraoAlcalinidade" size="20"
							maxlength="20" tabindex="21" readonly="true"
							style="background-color:#EFEFEF; border:0;" /></td>
					</tr>
					
					<tr>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
				</logic:present>

				<logic:notPresent name="desabilitaCampos">
					<tr>
						<td><strong>Fonte de Captação:</strong></td>

						<td colspan="2"><strong> <html:select property="fonteCaptacao"
							style="width: 270px;"
							tabindex="1"
							disabled="true">

							<html:option
								value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;
							</html:option>

							<logic:present name="colecaoFonteCaptacao" scope="request">
								<html:options collection="colecaoFonteCaptacao"
									labelProperty="descricao" property="id" />
							</logic:present>
						</html:select> </strong></td>

					</tr>

					<tr>
						<td><strong>Turbidez:</strong></td>
						<td align="center"><strong>Índice mensal:</strong> <html:text
							property="indiceMensalTurbidez" 
							size="6" 
							maxlength="6"
							tabindex="2"
							onkeyup="formataValorMonetario(this, 5)" 
							onkeypress="return isCampoNumerico(event);"/></td>
						<td><strong>Padrão:</strong> <html:text property="padraoTurbidez"
							size="20" 
							maxlength="20"
							tabindex="3" /></td>
					</tr>




					<tr>
						<td><strong>Cloro Residual:</strong></td>
						<td align="center"><strong>Índice mensal:</strong> <html:text
							property="indiceMensalCloroResidual" 
							size="6" 
							maxlength="6"
							tabindex="4"
							onkeyup="formataValorMonetario(this, 5)" 
							onkeypress="return isCampoNumerico(event);"/></td>
						<td><strong>Padrão:</strong> <html:text
							property="padraoCloroResidual" 
							size="20" 
							maxlength="20"
							tabindex="5" /></td>
					</tr>

					<tr>
						<td><strong>PH:</strong></td>
						<td align="center"><strong>Índice mensal:</strong> <html:text
							property="indiceMensalPH" 
							size="6" 
							maxlength="6"
							tabindex="6"
							onkeyup="formataValorMonetario(this, 5)" 
							onkeypress="return isCampoNumerico(event);"/></td>
						<td><strong>Padrão:</strong> <html:text property="padraoPH"
							size="20" 
							maxlength="20" 
							tabindex="7"/></td>
					</tr>

					<tr>
						<td><strong>Cor:</strong></td>
						<td align="center"><strong>Índice mensal:</strong> <html:text
							property="indiceMensalCor" 
							size="6" 
							maxlength="6"
							tabindex="8"
							onkeyup="formataValorMonetario(this, 5)" 
							onkeypress="return isCampoNumerico(event);"/></td>
						<td><strong>Padrão:</strong> <html:text property="padraoCor"
							size="20" 
							maxlength="20" 
							tabindex="9"/></td>
					</tr>

					<tr>
						<td><strong>Flúor:</strong></td>
						<td align="center"><strong>Índice mensal:</strong> <html:text
							property="indiceMensalFluor" 
							size="6" 
							maxlength="6"
							tabindex="10"
							onkeyup="formataValorMonetario(this, 5)" 
							onkeypress="return isCampoNumerico(event);"/></td>
						<td><strong>Padrão:</strong> <html:text property="padraoFluor"
							size="20" 
							maxlength="20" 
							tabindex="11"/></td>
					</tr>
					
					<tr>
						<td><strong>EColi:</strong></td>
						<td align="center"><strong>Índice mensal:</strong> <html:text
							property="indiceMensalEColi" 
							size="6" 
							maxlength="6"
							tabindex="10"
							onkeyup="formataValorMonetario(this, 5)" 
							onkeypress="return isCampoNumerico(event);"/></td>
						<td><strong>Padrão:</strong> <html:text property="padraoEColi"
							size="20" 
							maxlength="20" 
							tabindex="11"/></td>
					</tr>

					<tr>
						<td><strong>Ferro:</strong></td>
						<td align="center"><strong>Índice mensal:</strong> <html:text
							property="indiceMensalFerro" 
							size="6" 
							maxlength="6"
							tabindex="12"
							onkeyup="formataValorMonetario(this, 5)" 
							onkeypress="return isCampoNumerico(event);"/></td>
						<td><strong>Padrão:</strong> <html:text property="padraoFerro"
							size="20" 
							maxlength="20" 
							tabindex="13"/></td>
					</tr>

					<tr>
						<td><strong>Coliformes Totais:</strong></td>
						<td align="center"><strong>Índice mensal:</strong> <html:text
							property="indiceMensalColiformesTotais" 
							size="6" 
							maxlength="6"
							tabindex="14"
							onkeyup="formataValorMonetario(this, 5)" 
							onkeypress="return isCampoNumerico(event);"/></td>
						<td><strong>Padrão:</strong> <html:text
							property="padraoColiformesTotais" 
							size="20" 
							maxlength="20"
							tabindex="15" /></td>
					</tr>

					<tr>
						<td><strong>Coliformes Fecais:</strong></td>
						<td align="center"><strong>Índice mensal:</strong> <html:text
							property="indiceMensalColiformesFecais" 
							size="6" 
							maxlength="6"
							tabindex="16"
							onkeyup="formataValorMonetario(this, 5)" 
							onkeypress="return isCampoNumerico(event);"/></td>
						<td><strong>Padrão:</strong> <html:text
							property="padraoColiformesFecais" 
							size="20" 
							maxlength="20" 
							tabindex="16"/></td>
					</tr>

					<tr>
						<td><strong>Nitrato:</strong></td>
						<td align="center"><strong>Índice mensal:</strong> <html:text
							property="indiceMensalNitrato" 
							size="6" 
							maxlength="6"
							tabindex="17"
							onkeyup="formataValorMonetario(this, 5)" 
							onkeypress="return isCampoNumerico(event);"/></td>
						<td><strong>Padrão:</strong> <html:text property="padraoNitrato"
							size="20" 
							maxlength="20" 
							tabindex="18"/></td>
					</tr>

					<tr>
						<td><strong>Coliformes Termotolerantes:</strong></td>
						<td align="center"><strong>Índice mensal:</strong> <html:text
							property="indiceMensalColiformesTermotolerantes" size="6"
							maxlength="6" tabindex="19"
							onkeyup="formataValorMonetario(this, 5)" 
							onkeypress="return isCampoNumerico(event);"/></td>
						<td><strong>Padrão:</strong> <html:text
							property="padraoColiformesTermotolerantes" size="20"
							maxlength="20" tabindex="20" /></td>
					</tr>
					
					<tr>
						<td><strong>Alcalinidade:</strong></td>
						<td align="center"><strong>Índice mensal:</strong> <html:text
							property="indiceMensalAlcalinidade" size="6"
							maxlength="6" tabindex="21"
							onkeyup="formataValorMonetario(this, 5)" 
							onkeypress="return isCampoNumerico(event);"/></td>
						<td><strong>Padrão:</strong> <html:text
							property="padraoAlcalinidade" size="20"
							maxlength="20" tabindex="22" /></td>
					</tr>
					
				</logic:notPresent>



				<tr>
					<td></td>
					<td colspan="2"><strong><font color="#FF0000">*</font></strong>Campo
					obrigat&oacute;rio</td>
				</tr>

				<tr>
					<td colspan="3">
					<div align="right"><jsp:include
						page="/jsp/util/wizard/navegacao_botoes_wizard_valida_avancar.jsp?numeroPagina=1" />
					</div>
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
