
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

<%@ page import="gcom.util.Util"%>
<%@ page import="gcom.util.ConstantesSistema"%>

<html:javascript formName="InserirQualidadeAguaActionForm"
	dynamicJavascript="false" staticJavascript="true" />

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>

<script>

	var bCancel = false;

	function validateInserirQualidadeAguaActionForm(form) {

		if (bCancel){
			return true;
		}else{
			
			return testarCampoValorZero(document.InserirQualidadeAguaActionForm.logradouro, 'Logradouro') && 
				validateCaracterEspecial(form) && 
				validateRequired(form) && 
				validateLong(form) ;
		}
	}
	
	function IntegerValidations () {
		this.aa = new Array("idLocalidade", "Localidade deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
		this.ac = new Array("idSetorComercial", "Unidade Organizacional deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
	}

	

	function caracteresespeciais () {
		this.aa = new Array("idLocalidade", "Localidade possui caracteres especiais.", new Function ("varName", " return this[varName];"));
		this.ab = new Array("idSetorComercial", "Setor Comercial possui caracteres especiais.", new Function ("varName", " return this[varName];"));
	}

	function required () {
		
		this.aa = new Array("referencia", "Informe Referência.", new Function ("varName", " return this[varName];"));
		
	}

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

    	validaPreenchimentoCampos();
  	}

	function limparPesquisaDescricaoLocalidade() {
    	var form = document.forms[0];
      	form.localidadeDescricao.value = "";
  	}

	function limparPesquisaLocalidade() {
    	var form = document.forms[0];

      	form.idLocalidade.value = "";
      	form.localidadeDescricao.value = "";

		if(form.idLocalidade.disabled!=true){
      		validaPreenchimentoCampos();
		}
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

		if(form.idSetorComercial.disabled!=true){
      		validaPreenchimentoCampos();
		}
	}

	function verificarInserirTodos(){
		var form = InserirQualidadeAguaActionForm;
       	
       	
       	form.action = 'exibirInserirQualidadeAguaAction.do';
       	form.submit();
       	
	}

	
	function verificarChecado(valor){
		form = document.forms[0];
		
	}

	function validaPreenchimentoCampos(){
		form = document.forms[0];

		if(form.idLocalidade.value != undefined
				&& form.idLocalidade.value!=''){
			form.sistemaAbastecimento.disabled = true;
			form.sistemaAbastecimento.value = '-1';
			
		}else if(form.idSetorComercial.value != undefined
				&& form.idSetorComercial.value!=''){
			
			form.sistemaAbastecimento.disabled = true;
			form.sistemaAbastecimento.value = '-1';
			
		}else if(form.fonteCaptacao.value != undefined
				&& form.fonteCaptacao.value!='-1'){
			
			form.sistemaAbastecimento.disabled = true;
			form.sistemaAbastecimento.value = '-1';
			
		}else{
			form.sistemaAbastecimento.disabled = false;
		}	
	}

	function validaSistemaAbastecimento(){
		form = document.forms[0];

		if(form.sistemaAbastecimento.value != undefined
				&& form.sistemaAbastecimento.value != '-1'){
			form.idLocalidade.disabled = true;
			form.idLocalidade.value = '';
			form.idSetorComercial.disabled = true;
			form.idSetorComercial.value = '';
			form.fonteCaptacao.disabled = true;
			form.fonteCaptacao.value = '-1';
		}else{
			form.idLocalidade.disabled = false;
			form.idSetorComercial.disabled = false;
			form.fonteCaptacao.disabled = false;
		}
		
	}

	function abrirPopupSemEstaDesabilitado(caminho, altura, largura) {
		form = document.forms[0];
		if(form.idLocalidade.disabled!=true){
			abrirPopup(caminho, altura, largura);
		}
	}

	function abrirPopupDependenciaSemEstaDesabilitado(url, idDependencia, nomeMSG, altura, largura){
		form = document.forms[0];
		if(form.idSetorComercial.disabled!=true){
			 abrirPopupDependencia(url, idDependencia, nomeMSG, altura, largura);
		}
	}

	function verificarCampos(tipo){

	if(tipo!=undefined){
		if(tipo=='localidade'){
			validaPreenchimentoCampos();
		}else if (tipo=='sistema'){
			validaSistemaAbastecimento();
		}		
	}	

	
	}
	
</script>
</head>

<body leftmargin="5" topmargin="5"
	onload="javascript:setarFoco('${requestScope.nomeCampo}');verificarCampos('${requestScope.validaCampos}')">

<html:form action="/inserirQualidadeAguaWizardAction" method="post"
	onsubmit="return validateInserirQualidadeAguaActionForm(this);">

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
					<td class="parabg">Inserir Qualidade da Água</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<table width="100%" border="0" dwcopytype="CopyTableRow">
				<tr>
					<td>Para adicionar a qualidade da água, informe os dados abaixo:
					<td align="right"></td>
				</tr>
			</table>

			<table width="100%" border="0">



				<logic:present name="inserirTodosAtivado" scope="request">

					<tr>
						<td><strong>Referência:<font color="#FF0000">*</font></strong></td>

						<td><html:text property="referencia" 
							size="7" 
							maxlength="7"
							onkeyup="mascaraAnoMes(this, event);"
							onkeypress="return isCampoNumerico(event);"
							readonly="true"
							style="background-color:#EFEFEF; border:0;" /> (mm/aaaa)</td>

						<td align="right"><html:checkbox property="incluirTodos" value="1"
							onclick="javascript:verificarInserirTodos();" /><!--<input type="checkbox"
							name="indicadorInserirTodos" value="1"
							onclick="javascript:verificarInserirTodos();" align="right" /> --><strong>Inserir
						Todos</strong></td>
					</tr>

					<tr>
						<td width="19%"><strong>Localidade:</strong></td>
						<td width="81%" height="24" colspan="2"><html:text
							property="idLocalidade" 
							maxlength="3" 
							size="3"
							onkeypress="javascript:limparPesquisaDescricaoLocalidade();limparPesquisaSetorComercial();
	                   		validaEnterComMensagem(event,'exibirInserirQualidadeAguaAction.do','idLocalidade','Localidade');
	                   		return isCampoNumerico(event);"
							disabled="true" 
							style="background-color:#EFEFEF; border:0;" /> <a
							href="javascript:abrirPopupSemEstaDesabilitado('exibirPesquisarLocalidadeAction.do?tipo=imovelLocalidade', 298, 480);">
						<img border="0"
							src="<bean:message key="caminho.imagens"/>pesquisa.gif"
							border="0" width="23" height="21" title="Pesquisar" /></a> <logic:present
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
							<logic:empty name="InserirQualidadeAguaActionForm"
								property="idLocalidade">
								<html:text property="localidadeDescricao" size="50"
									maxlength="50" readonly="true"
									style="background-color:#EFEFEF; border:0; color: #ff0000" />
							</logic:empty>
							<logic:notEmpty name="InserirQualidadeAguaActionForm"
								property="idLocalidade">
								<html:text property="localidadeDescricao" size="50"
									maxlength="50" readonly="true"
									style="background-color:#EFEFEF; border:0; color: #000000" />
							</logic:notEmpty>
						</logic:notPresent> <a
							href="javascript:limparPesquisaLocalidade();limparPesquisaSetorComercial();document.forms[0].idLocalidade.focus();">
						<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
							border="0" title="Apagar" /></a></td>
					</tr>

					<tr>
						<td><strong>Setor Comercial:</strong></td>
						<td height="24" colspan="2"><html:text maxlength="3"
							property="idSetorComercial" 
							size="3"
							onkeypress="javascript:alert('validaEnterDependenciaComMensagem');limparDescricaoSetorComercial();validaEnterDependenciaComMensagem(event,'exibirInserirQualidadeAguaAction.do', document.forms[0].idSetorComercial, document.forms[0].idLocalidade.value, 'Localidade', 'Setor Comercial');
							return isCampoNumerico(event);"
							disabled="true" 
							style="background-color:#EFEFEF; border:0;" /> <a
							href="javascript:abrirPopupDependenciaSemEstaDesabilitado('exibirPesquisarSetorComercialAction.do?idLocalidade='+document.forms[0].idLocalidade.value+'&tipo=SetorComercial',document.forms[0].idLocalidade.value,'Localidade', 298, 480);">
						<img border="0"
							src="<bean:message key="caminho.imagens"/>pesquisa.gif"
							border="0" /></a> <logic:present
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
							<logic:empty name="InserirQualidadeAguaActionForm"
								property="idSetorComercial">
								<html:text property="setorComercialDescricao" size="50"
									maxlength="50" readonly="true"
									style="background-color:#EFEFEF; border:0; color: #ff0000" />
							</logic:empty>
							<logic:notEmpty name="InserirQualidadeAguaActionForm"
								property="idSetorComercial">
								<html:text property="setorComercialDescricao" size="50"
									maxlength="50" readonly="true"
									style="background-color:#EFEFEF; border:0; color: #000000" />
							</logic:notEmpty>
						</logic:notPresent> <a
							href="javascript:limparPesquisaSetorComercial();document.forms[0].idSetorComercial.focus();">
						<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
							border="0" title="Apagar" /></a></td>
					</tr>

					<tr>
						<td><strong>Sistema de Abastecimento:</strong></td>

						<td colspan="2"><strong> <html:select
							property="sistemaAbastecimento" 
							style="width: 270px;"
							disabled="true">

							<html:option
								value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;
							</html:option>

							<logic:present name="colecaosistemaAbastecimento" scope="request">
								<html:options collection="colecaosistemaAbastecimento"
									labelProperty="descricao" property="id" />
							</logic:present>
						</html:select> </strong></td>

					</tr>

					<tr>
						<td><strong>Fonte de Captação:</strong></td>

						<td colspan="2"><strong> <html:select property="fonteCaptacao"
							style="width: 270px;" disabled="true">

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
							property="indiceMensalTurbidez" size="5" maxlength="20"
							readonly="true" style="background-color:#EFEFEF; border:0;" 
							onkeypress="return isCampoNumerico(event);"/></td>
						<td><strong>Padrão:</strong> <html:text property="padraoTurbidez"
							size="5" maxlength="20" readonly="true"
							style="background-color:#EFEFEF; border:0;" /></td>
					</tr>




					<tr>
						<td><strong>Cloro Residual:</strong></td>
						<td align="center"><strong>Índice mensal:</strong> <html:text
							property="indiceMensalCloroResidual" size="5" maxlength="20"
							readonly="true" style="background-color:#EFEFEF; border:0;" 
							onkeypress="return isCampoNumerico(event);"/></td>
						<td><strong>Padrão:</strong> <html:text
							property="padraoCloroResidual" size="5" maxlength="20"
							readonly="true" style="background-color:#EFEFEF; border:0;" /></td>
					</tr>

					<tr>
						<td><strong>PH:</strong></td>
						<td align="center"><strong>Índice mensal:</strong> <html:text
							property="indiceMensalPH" size="5" maxlength="20" readonly="true"
							style="background-color:#EFEFEF; border:0;" 
							onkeypress="return isCampoNumerico(event);"/></td>
						<td><strong>Padrão:</strong> <html:text property="padraoPH"
							size="5" maxlength="20" readonly="true"
							style="background-color:#EFEFEF; border:0;" /></td>
					</tr>

					<tr>
						<td><strong>Cor:</strong></td>
						<td align="center"><strong>Índice mensal:</strong> <html:text
							property="indiceMensalCor" size="5" maxlength="20"
							readonly="true" style="background-color:#EFEFEF; border:0;" 
							onkeypress="return isCampoNumerico(event);"/></td>
						<td><strong>Padrão:</strong> <html:text property="padraoCor"
							size="5" maxlength="20" readonly="true"
							style="background-color:#EFEFEF; border:0;" /></td>
					</tr>

					<tr>
						<td><strong>Flúor:</strong></td>
						<td align="center"><strong>Índice mensal:</strong> <html:text
							property="indiceMensalFluor" size="5" maxlength="20"
							readonly="true" style="background-color:#EFEFEF; border:0;"
							onkeypress="return isCampoNumerico(event);" /></td>
						<td><strong>Padrão:</strong> <html:text property="padraoFluor"
							size="5" maxlength="20" readonly="true"
							style="background-color:#EFEFEF; border:0;" /></td>
					</tr>
					
					<tr>
						<td><strong>EColi:</strong></td>
						<td align="center"><strong>Índice mensal:</strong> <html:text
							property="indiceMensalEColi" size="5" maxlength="20"
							readonly="true" style="background-color:#EFEFEF; border:0;"
							onkeypress="return isCampoNumerico(event);" /></td>
						<td><strong>Padrão:</strong> <html:text property="padraoEColi"
							size="5" maxlength="20" readonly="true"
							style="background-color:#EFEFEF; border:0;" /></td>
					</tr>

					<tr>
						<td><strong>Ferro:</strong></td>
						<td align="center"><strong>Índice mensal:</strong> <html:text
							property="indiceMensalFerro" size="5" maxlength="20"
							readonly="true" style="background-color:#EFEFEF; border:0;" 
							onkeypress="return isCampoNumerico(event);"/></td>
						<td><strong>Padrão:</strong> <html:text property="padraoFerro"
							size="5" maxlength="20" readonly="true"
							style="background-color:#EFEFEF; border:0;" /></td>
					</tr>

					<tr>
						<td><strong>Coliformes Totais:</strong></td>
						<td align="center"><strong>Índice mensal:</strong> <html:text
							property="indiceMensalColiformesTotais" size="5" maxlength="20"
							readonly="true" style="background-color:#EFEFEF; border:0;"
							onkeypress="return isCampoNumerico(event);" /></td>
						<td><strong>Padrão:</strong> <html:text
							property="padraoColiformesTotais" size="5" maxlength="20"
							readonly="true" style="background-color:#EFEFEF; border:0;" /></td>
					</tr>

					<tr>
						<td><strong>Coliformes Fecais:</strong></td>
						<td align="center"><strong>Índice mensal:</strong> <html:text
							property="indiceMensalColiformesFecais" size="5" maxlength="20"
							readonly="true" style="background-color:#EFEFEF; border:0;"
							onkeypress="return isCampoNumerico(event);" /></td>
						<td><strong>Padrão:</strong> <html:text
							property="padraoColiformesFecais" size="5" maxlength="20"
							readonly="true" style="background-color:#EFEFEF; border:0;" /></td>
					</tr>

					<tr>
						<td><strong>Nitrato:</strong></td>
						<td align="center"><strong>Índice mensal:</strong> <html:text
							property="indiceMensalNitrato" 
							size="5" 
							maxlength="20"
							readonly="true" 
							style="background-color:#EFEFEF; border:0;"
							onkeypress="return isCampoNumerico(event);" /></td>
						<td><strong>Padrão:</strong> <html:text property="padraoNitrato"
							size="5" 
							maxlength="20" 
							readonly="true"
							style="background-color:#EFEFEF; border:0;" /></td>
					</tr>


					<tr>
						<td><strong>Coliformes Termotolerantes:</strong></td>
						<td align="center"><strong>Índice mensal:</strong> <html:text
							property="indiceMensalColiformesTermotolerantes" size="5"
							maxlength="20" readonly="true"
							style="background-color:#EFEFEF; border:0;" 
							onkeypress="return isCampoNumerico(event);"/></td>
						<td><strong>Padrão:</strong> <html:text
							property="padraoColiformesTermotolerantes" size="5"
							maxlength="20" readonly="true"
							style="background-color:#EFEFEF; border:0;" /></td>
					</tr>

					<tr>
						<td><strong>Alcalinidade:</strong></td>
						<td align="center"><strong>Índice mensal:</strong> <html:text
							property="indiceMensalAlcalinidade" size="5" maxlength="20"
							readonly="true" style="background-color:#EFEFEF; border:0;"
							onkeypress="return isCampoNumerico(event);" /></td>
						<td><strong>Padrão:</strong> <html:text
							property="padraoAlcalinidade" size="5" maxlength="20"
							readonly="true" style="background-color:#EFEFEF; border:0;" /></td>
					</tr>

				</logic:present>

				<logic:notPresent name="inserirTodosAtivado" scope="request">

					<tr>
						<td><strong>Referência:<font color="#FF0000">*</font></strong></td>

						<td><html:text property="referencia" size="7" maxlength="7"
							tabindex="1"
							onkeyup="mascaraAnoMes(this, event);"
							onkeypress="return isCampoNumerico(event);" />
						(mm/aaaa)</td>

						<td align="right"><!--<input type="checkbox"
							name="indicadorInserirTodos" value="1"
							onclick="javascript:verificarInserirTodos();" align="right" /> --><html:checkbox
							property="incluirTodos" value="1"
							onclick="javascript:verificarInserirTodos();" /><strong>Inserir
						Todos</strong></td>
					</tr>
					
					<tr>
						<td><strong>Sistema de Abastecimento:</strong></td>

						<td colspan="2"><strong> <html:select
							property="sistemaAbastecimento" 
							style="width: 270px;"
							tabindex="2"
							onchange="validaSistemaAbastecimento();">

							<html:option
								value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;
							</html:option>

							<logic:present name="colecaoSistemaAbastecimento" scope="request">
								<html:options collection="colecaoSistemaAbastecimento"
									labelProperty="descricao" property="id" />
							</logic:present>
						</html:select> </strong></td>

					</tr>
					
					<tr>
						<td width="19%"><strong>Localidade:</strong></td>
						<td width="81%" height="24" colspan="2"><html:text
							property="idLocalidade" 
							maxlength="3" size="3" 
							tabindex="3"
							onkeypress="javascript:limparPesquisaDescricaoLocalidade();limparPesquisaSetorComercial();
	                   		validaEnterComMensagem(event,'exibirInserirQualidadeAguaAction.do','idLocalidade','Localidade');
							validaPreenchimentoCampos(); 
							return isCampoNumerico(event);"
							/> <a
							href="javascript:abrirPopupSemEstaDesabilitado('exibirPesquisarLocalidadeAction.do?tipo=imovelLocalidade', 298, 480);">
						<img border="0"
							src="<bean:message key="caminho.imagens"/>pesquisa.gif"
							border="0" width="23" height="21" title="Pesquisar" /></a> <logic:present
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
							<logic:empty name="InserirQualidadeAguaActionForm"
								property="idLocalidade">
								<html:text property="localidadeDescricao" size="50"
									maxlength="50" readonly="true"
									style="background-color:#EFEFEF; border:0; color: #ff0000" />
							</logic:empty>
							<logic:notEmpty name="InserirQualidadeAguaActionForm"
								property="idLocalidade">
								<html:text property="localidadeDescricao" size="50"
									maxlength="50" readonly="true"
									style="background-color:#EFEFEF; border:0; color: #000000" />
							</logic:notEmpty>
						</logic:notPresent> <a
							href="javascript:limparPesquisaLocalidade();limparPesquisaSetorComercial();document.forms[0].idLocalidade.focus();">
						<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
							border="0" title="Apagar" /></a></td>
					</tr>

					<tr>
						<td><strong>Setor Comercial:</strong></td>
						<td height="24" colspan="2"><html:text maxlength="3"
							property="idSetorComercial" size="3" 
							tabindex="4"
							onkeypress="javascript: limparDescricaoSetorComercial(); validaEnterDependenciaComMensagem(event,'exibirInserirQualidadeAguaAction.do', document.forms[0].idSetorComercial, document.forms[0].idLocalidade.value, 'Localidade', 'Setor Comercial');
							validaPreenchimentoCampos();
							return isCampoNumerico(event);"
							 /> <a
							href="javascript:abrirPopupDependenciaSemEstaDesabilitado('exibirPesquisarSetorComercialAction.do?idLocalidade='+document.forms[0].idLocalidade.value+'&tipo=SetorComercial',document.forms[0].idLocalidade.value,'Localidade',298, 480);">
						<img border="0"
							src="<bean:message key="caminho.imagens"/>pesquisa.gif"
							border="0" /></a> <logic:present
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
							<logic:empty name="InserirQualidadeAguaActionForm"
								property="idSetorComercial">
								<html:text property="setorComercialDescricao" size="50"
									maxlength="50" readonly="true"
									style="background-color:#EFEFEF; border:0; color: #ff0000" />
							</logic:empty>
							<logic:notEmpty name="InserirQualidadeAguaActionForm"
								property="idSetorComercial">
								<html:text property="setorComercialDescricao" size="50"
									maxlength="50" readonly="true"
									style="background-color:#EFEFEF; border:0; color: #000000" />
							</logic:notEmpty>
						</logic:notPresent> <a
							href="javascript:limparPesquisaSetorComercial();document.forms[0].idSetorComercial.focus();">
						<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
							border="0" title="Apagar" /></a></td>
					</tr>

					

					<tr>
						<td><strong>Fonte de Captação:</strong></td>

						<td colspan="2"><strong> <html:select property="fonteCaptacao"
							style="width: 270px;"
							tabindex="5"
							onchange="validaPreenchimentoCampos();">

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
							size="5" 
							maxlength="6"
							tabindex="6" 
							onkeyup="formataValorMonetario(this, 5)"
							onkeypress="return isCampoNumerico(event);" /></td>
						<td><strong>Padrão:</strong> <html:text property="padraoTurbidez"
							size="20" maxlength="20" tabindex="7" /></td>
					</tr>

					<tr>
						<td><strong>Cloro Residual:</strong></td>
						<td align="center"><strong>Índice mensal:</strong> <html:text
							property="indiceMensalCloroResidual" size="5" maxlength="6"
							tabindex="8" onkeyup="formataValorMonetario(this, 5)"
							onkeypress="return isCampoNumerico(event);" /></td>
						<td><strong>Padrão:</strong> <html:text
							property="padraoCloroResidual" size="20" maxlength="20"
							tabindex="9" /></td>
					</tr>

					<tr>
						<td><strong>PH:</strong></td>
						<td align="center"><strong>Índice mensal:</strong> <html:text
							property="indiceMensalPH" size="5" maxlength="6" tabindex="10"
							onkeyup="formataValorMonetario(this, 5)" 
							onkeypress="return isCampoNumerico(event);"/></td>
						<td><strong>Padrão:</strong> <html:text property="padraoPH"
							size="20" maxlength="20" tabindex="11" /></td>
					</tr>

					<tr>
						<td><strong>Cor:</strong></td>
						<td align="center"><strong>Índice mensal:</strong> <html:text
							property="indiceMensalCor" size="5" maxlength="6" tabindex="12"
							onkeyup="formataValorMonetario(this, 5)" 
							onkeypress="return isCampoNumerico(event);"/></td>
						<td><strong>Padrão:</strong> <html:text property="padraoCor"
							size="20" maxlength="20" tabindex="13" /></td>
					</tr>

					<tr>
						<td><strong>Flúor:</strong></td>
						<td align="center"><strong>Índice mensal:</strong> <html:text
							property="indiceMensalFluor" size="5" maxlength="6" tabindex="14"
							onkeyup="formataValorMonetario(this, 5)" 
							onkeypress="return isCampoNumerico(event);"/></td>
						<td><strong>Padrão:</strong> <html:text property="padraoFluor"
							size="20" maxlength="20" tabindex="15" /></td>
					</tr>
					
					<tr>
						<td><strong>EColi:</strong></td>
						<td align="center"><strong>Índice mensal:</strong> <html:text
							property="indiceMensalEColi" size="5" maxlength="6" tabindex="14"
							onkeyup="formataValorMonetario(this, 5)" 
							onkeypress="return isCampoNumerico(event);"/></td>
						<td><strong>Padrão:</strong> <html:text property="padraoEColi"
							size="20" maxlength="20" tabindex="15" /></td>
					</tr>

					<tr>
						<td><strong>Ferro:</strong></td>
						<td align="center"><strong>Índice mensal:</strong> <html:text
							property="indiceMensalFerro" size="5" maxlength="6" tabindex="16"
							onkeyup="formataValorMonetario(this, 5)" 
							onkeypress="return isCampoNumerico(event);"/></td>
						<td><strong>Padrão:</strong> <html:text property="padraoFerro"
							size="20" maxlength="20" tabindex="17" /></td>
					</tr>

					<tr>
						<td><strong>Coliformes Totais:</strong></td>
						<td align="center"><strong>Índice mensal:</strong> <html:text
							property="indiceMensalColiformesTotais" size="5" maxlength="6"
							tabindex="18" onkeyup="formataValorMonetario(this, 5)"
							onkeypress="return isCampoNumerico(event);" /></td>
						<td><strong>Padrão:</strong> <html:text
							property="padraoColiformesTotais" size="20" maxlength="20"
							tabindex="19" /></td>
					</tr>

					<tr>
						<td><strong>Coliformes Fecais:</strong></td>
						<td align="center"><strong>Índice mensal:</strong> <html:text
							property="indiceMensalColiformesFecais" size="5" maxlength="6"
							tabindex="20" onkeyup="formataValorMonetario(this, 5)" 
							onkeypress="return isCampoNumerico(event);"/></td>
						<td><strong>Padrão:</strong> <html:text
							property="padraoColiformesFecais" size="20" maxlength="20"
							tabindex="21" /></td>
					</tr>

					<tr>
						<td><strong>Nitrato:</strong></td>
						<td align="center"><strong>Índice mensal:</strong> <html:text
							property="indiceMensalNitrato" size="5" maxlength="6"
							tabindex="22" onkeyup="formataValorMonetario(this, 5)" 
							onkeypress="return isCampoNumerico(event);"/></td>
						<td><strong>Padrão:</strong> <html:text property="padraoNitrato"
							size="20" maxlength="20" tabindex="23" /></td>
					</tr>

					<tr>
						<td><strong>Coliformes Temotolerantes:</strong></td>
						<td align="center"><strong>Índice mensal:</strong> <html:text
							property="indiceMensalColiformesTermotolerantes" size="5"
							maxlength="6" tabindex="24"
							onkeyup="formataValorMonetario(this, 5)"
							onkeypress="return isCampoNumerico(event);"/></td>
						<td><strong>Padrão:</strong> <html:text
							property="padraoColiformesTermotolerantes" size="20"
							maxlength="20" tabindex="25" /></td>
					</tr>

					<tr>
						<td><strong>Alcalinidade:</strong></td>
						<td align="center"><strong>Índice mensal:</strong> <html:text
							property="indiceMensalAlcalinidade" size="5" maxlength="6"
							tabindex="26" onkeyup="formataValorMonetario(this, 5)" 
							onkeypress="return isCampoNumerico(event);"/></td>
						<td><strong>Padrão:</strong> <html:text
							property="padraoAlcalinidade" size="20" maxlength="20"
							tabindex="27" /></td>
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
