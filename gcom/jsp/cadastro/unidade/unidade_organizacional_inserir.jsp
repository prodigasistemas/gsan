<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
<head>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">

<%@ page import="gcom.util.ConstantesSistema"%>
<%@page import="gcom.cadastro.geografico.Municipio"%>


<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="InserirUnidadeOrganizacionalActionForm" />

<script language="JavaScript">

	//Valida o form no validator.xml
	function validaForm(){
   		var form = document.forms[0];

    	if(validateInserirUnidadeOrganizacionalActionForm(form)){
    		if (validaDescricao()) {
	    		if(validaTodosRadioButton()) {
	    			form.action = 'inserirUnidadeOrganizacionalAction.do';
					submeterFormPadrao(form);
      			}
      		}
	  	}
    }
    
    function validaDescricao() {
    	var form = document.forms[0];
    	
    	if (form.codigoUnidadeTipo.value == 'G' && form.descricao.readOnly == true && form.gerenciaRegional.value == '-1') {
			alert('Informe Gerência Regional');
			return false;
    	} else if (form.codigoUnidadeTipo.value == 'L' && form.descricao.readOnly == true && form.idLocalidade.value == '') {
    		alert('Informe Localidade');
			return false;
    	} else if (form.codigoUnidadeTipo.value == 'U' && form.descricao.readOnly == true && form.unidadeNegocio.value == '-1') {
    		alert('Informe Unidade de Negocio');
    		return false;
    	} else {
    		if (form.descricao.value == '') {
    			alert('Informe Descrição');	
    			return false;
    		}
    	}
    	
    	return true;
    	
    }
    
    function validaTodosRadioButton() {
		var form = document.forms[0];		

		if (!form.unidadeEsgoto[0].checked && !form.unidadeEsgoto[1].checked) {
			alert("Informe Indicador de Unidade Esgoto.");
			return false;
		}		
		if (!form.unidadeAbreRegistro[0].checked && !form.unidadeAbreRegistro[1].checked) {
			alert("Informe Indicador de Abre Registro de Atendimento.");
			return false;
		}		

		if (!form.unidadeAceita[0].checked && !form.unidadeAceita[1].checked) {
			alert("Informe Indicador de Aceita Tramitação.");
			return false;
		}
		
		if (!form.unidadeCentralAtendimento[0].checked && !form.unidadeCentralAtendimento[1].checked) {
			alert("Informe Indicador de Central Atendimento.");
			return false;
		}

		if (!form.unidadeTarifaSocial[0].checked && !form.unidadeTarifaSocial[1].checked) {
			alert("Informe Indicador de Tarifa Social.");
			return false;
		}

		if(form.unidadeAbreRegistro[0].checked && form.meioSolicitacao.selectedIndex == 0){
			alert("Informe Meio de Solicitação.");		
			return false;
		}

		return true;
   	}
    

	function limparForm(limpaCodigo){

		var form = document.forms[0];

		form.idUnidadeCentralizadora.disabled=false;
		form.idUnidadeRepavimentadora.disabled=false;
		form.idEmpresa.disabled=false;
		form.idLocalidade.readOnly=false;
		form.sigla.readOnly=false;
		form.gerenciaRegional.disabled=false;
		form.unidadeNegocio.disabled=false;
		form.descricao.readOnly=false;
		form.meioSolicitacao.disabled=false;

		if(limpaCodigo != 'false'){
			form.unidadeTipo.selectedIndex = 0;
		}

		form.codigoUnidadeTipo.value = "";
		form.descricao.value = "";
		form.sigla.value = "";
		
		form.gerenciaRegional.selectedIndex = 0;
		form.meioSolicitacao.selectedIndex = 0;
		form.unidadeNegocio.selectedIndex = 0;

		form.unidadeEsgoto[0].checked = false;
		form.unidadeEsgoto[1].checked = false;
		
		form.unidadeAbreRegistro[0].checked = false;
		form.unidadeAbreRegistro[1].checked = false;

		form.unidadeAceita[0].checked = false;
		form.unidadeAceita[1].checked = false;
		
		form.unidadeCentralAtendimento[0].checked = false;
		form.unidadeCentralAtendimento[1].checked = false;

		form.unidadeTarifaSocial[0].checked = false;
		form.unidadeTarifaSocial[1].checked = false;
		
        form.idUnidadeCentralizadora.selectedIndex = 0;
        form.idEmpresa.selectedIndex = "";
        
		limparPesquisaLocalidade();
		limparUnidadeSuperior();
		
	   	form.action='exibirInserirUnidadeOrganizacionalAction.do?menu=sim';
	   	form.submit();
		
	}

	function limparPesquisaLocalidade() {

    	var form = document.forms[0];
    	form.idLocalidade.value = "";
    	form.descricaoLocalidade.value = ""; 	
		if(	form.codigoUnidadeTipo.value == 'L' || 
			form.codigoUnidadeTipo.value == 'G'){
			form.descricao.value = "";
   		}
    	
  	}

    function limparUnidadeSuperior() {
        var form = document.forms[0];

        form.idUnidadeSuperior.value = "";
    	form.descricaoUnidadeSuperior.value = "";
    }

	//Chama Popup
	function chamarPopup(url, tipo, objeto, codigoObjeto, altura, largura, msg,objetoRelacionado){
		if(objetoRelacionado.disabled != true && objetoRelacionado.readOnly != true){
			if (objeto == null || codigoObjeto == null){
				abrirPopup(url + "?" + "menu=sim&"+"tipo=" + tipo, altura, largura);
			}
			else{
				if (codigoObjeto.length < 1 || isNaN(codigoObjeto)){
					alert(msg);
				}
				else{
					abrirPopup(url + "?" +"menu=sim&"+ "tipo=" + tipo + "&" + objeto + "=" + codigoObjeto + "&caminhoRetornoTelaPesquisa=" + tipo, altura, largura);
				}
			}
		}
	}

	//Recupera Dados Popup
	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
		var form = document.forms[0];

	    if (tipoConsulta == 'localidade') {

	    	form.idLocalidade.value = codigoRegistro;
   	    	form.descricaoLocalidade.value = descricaoRegistro;
      		form.descricaoLocalidade.style.color = "#000000";

   			if(	form.codigoUnidadeTipo.value == 'L' || 
   				form.codigoUnidadeTipo.value == 'G'){
   				
   				form.descricao.value = descricaoRegistro;
				form.descricao.readOnly=true;
   			}

	    } else if (tipoConsulta == 'unidadeSuperior') {
	    	form.idUnidadeSuperior.value = codigoRegistro;
   	    	form.descricaoUnidadeSuperior.value = descricaoRegistro;
      		form.descricaoUnidadeSuperior.style.color = "#000000";
	    } else  if (tipoConsulta == 'municipio') {
		     
		      form.idMunicipio.value = codigoRegistro;
		      form.descricaoMunicipio.value = descricaoRegistro;
		      form.descricaoMunicipio.style.color = "#000000";
  		}
	}

    function validarUnidadeTipo(ehGerencia) {
    	var form = document.forms[0];

		if(ehGerencia != 'true'){

    		limparForm('false');

    		form.action = 'exibirInserirUnidadeOrganizacionalAction.do'
    		form.submit();

		}else if(form.codigoUnidadeTipo.value == 'G'){

    		form.action = 'exibirInserirUnidadeOrganizacionalAction.do'
    		form.submit();
		}
		else if(form.codigoUnidadeTipo.value == 'U'){

    		form.action = 'exibirInserirUnidadeOrganizacionalAction.do'
    		form.submit();
		} 
    }
    
    function verificaIndicadorAbreRegistro(){
       	var form = document.forms[0];
    	
		if(form.unidadeAbreRegistro[1].checked){
			form.meioSolicitacao.selectedIndex = -1;
    		form.meioSolicitacao.disabled = true;
		}else{
			
			if(form.meioSolicitacao.disabled){
	   			form.meioSolicitacao.disabled=false;
			}
		}
    	
    }
	
	//verifica se existe alguma restrição 
	//para exibição alguma campo no formulario
    function verificaForm() {
       	var form = document.forms[0];
    
    form.idMunicipio.disabled = true
	form.botaoAdicionarMunicipio.disabled = true;

		//localidade
		if(form.codigoUnidadeTipo.value == 'L'){
			
			form.sigla.readOnly=true;
			form.gerenciaRegional.disabled=true;
			form.unidadeNegocio.disabled=true;
			form.descricao.readOnly=true;
			form.idEmpresa.disabled=true;
			form.unidadeNegocio.disable=true;
			
		//unidade negocio
		}else if(form.codigoUnidadeTipo.value == 'U'){
			
			form.idLocalidade.readOnly=true;
			form.gerenciaRegional.disabled=true;
			form.descricao.readOnly=true;
			form.idEmpresa.disabled=true;
			form.sigla.readOnly=true;
			
		//gerencia regional
		}else if(form.codigoUnidadeTipo.value == 'G'){
			
			form.idLocalidade.readOnly=true;
			form.unidadeNegocio.disabled=true;
			form.descricao.readOnly=true;
			form.idEmpresa.disabled=true;
			form.sigla.readOnly=true;
			
		//centralizador
		}else if(form.codigoUnidadeTipo.value == 'C'){

			form.idLocalidade.readOnly=true;
			form.gerenciaRegional.disabled=true;
			form.unidadeNegocio.disabled=true;
			form.idUnidadeCentralizadora.disabled=true;
			form.idEmpresa.disabled=true;

		//terceirizada
		}else if(form.codigoUnidadeTipo.value == 'T'){
			form.unidadeNegocio.disabled=true;
			form.idLocalidade.readOnly=true;
			form.gerenciaRegional.disabled=true;
		} else if (form.codigoUnidadeTipo.value == 'R'){
			form.idLocalidade.readOnly = true;
			form.idLocalidade.disabled = true;
			form.gerenciaRegional.disabled = true;
			form.unidadeNegocio.disabled = true;
			form.idUnidadeRepavimentadora.disabled = true;
			form.botaoAdicionarMunicipio.disabled = false;
			form.idMunicipio.disabled = false;
			
		}

    }
    
    function pesquisarMunicipio(){
		var form = document.forms[0];
		if(form.idMunicipio.disabled == false){
			abrirPopup('exibirPesquisarMunicipioAction.do?indicadorUsoTodos=1', 400, 800);
		}
	}
	
	function limparMunicipio() {
		var form = document.forms[0];

		form.idMunicipio.value = "";
		form.descricaoMunicipio.value = "";

	}
	/* Adiciona componente da Colecao Municipio*/
	function incluirObjeto(){
		var form = document.forms[0];

		if ( form.idMunicipio.value != null && form.idMunicipio.value != "" ) {

			form.action = 'exibirInserirUnidadeOrganizacionalAction.do?acao=adicionar';
			form.submit();
		} else {
			alert("Informe o Município");
		}
	}
	
	/* Remove componente da Colecao Municipio*/
	function remover(obj){
		var form = document.forms[0];
		
		if (confirm('Confirma Remoção?')) {
			form.action = 'exibirInserirUnidadeOrganizacionalAction.do?acao=remover&id='+obj;
			form.submit();
		}
	}
    

</script>
</head>
<body leftmargin="5" topmargin="5" onload="javascript:verificaForm();verificaIndicadorAbreRegistro();">
<html:form action="/inserirUnidadeOrganizacionalAction.do"
	name="InserirUnidadeOrganizacionalActionForm"
	type="gcom.gui.cadastro.unidade.InserirUnidadeOrganizacionalActionForm"
	method="post">

	<html:hidden property="codigoUnidadeTipo" />

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
					<td class="parabg">Inserir Unidade Organizacional</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" />
					</td>
				</tr>
			</table>

			<p>&nbsp;</p>


			<!--Inicio da Tabela Unidade Organizacional -->
			<table width="100%" border="0">
				<tr>
					<td>
					<table width="100%" border="0" align="center">
						<tr>
							<td width="100%" height="10">Para inserir uma unidade
							organizacional, informe os dados gerais abaixo:</td>
						</tr>

						<tr bgcolor="#cbe5fe">
							<td align="center">
							<table width="100%" border="0" bgcolor="#99CCFF">
								<tr bgcolor="#99CCFF">
									<td colspan="2">
									<div align="center"><span class="style2"> Dados da Unidade
									Organizacional </span></div>
									</td>
								</tr>

								<tr bgcolor="#cbe5fe">
									<td>
									<table border="0" width="100%">

										<tr>
											<td><strong> Tipo da Unidade:</strong> <span class="style2">
											<strong> <font color="#FF0000">*</font> </strong> </span></td>

											<td><strong> <html:select property="unidadeTipo"
												style="width: 230px;"
												onchange="javascript:validarUnidadeTipo();">
												<html:option
													value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;
													</html:option>

												<logic:present name="colecaoUnidadeTipo" scope="session">
													<html:options collection="colecaoUnidadeTipo"
														labelProperty="descricao" property="id" />

												</logic:present>
											</html:select> </strong></td>
										</tr>
										<tr>
											<td><strong>Localidade:</strong></td>
											<td width="63%"><html:text property="idLocalidade"
												tabindex="11" size="4" maxlength="4"
												onkeypress="validaEnterComMensagem(event, 'exibirInserirUnidadeOrganizacionalAction.do?objetoConsulta=1', 'idLocalidade', 'Localidade');return isCampoNumerico(event);" />
											<a
												href="javascript:chamarPopup('exibirPesquisarLocalidadeAction.do', 'localidade', null, null, 275, 480, '',document.forms[0].idLocalidade);">
											<img src="<bean:message key="caminho.imagens"/>pesquisa.gif"
												border="0" width="23" height="21" title="Pesquisar Localidade"></a> <logic:present
												name="corLocalidade">
												<logic:equal name="corLocalidade" value="exception">
													<html:text property="descricaoLocalidade" size="35"
														readonly="true"
														style="background-color:#EFEFEF; border:0; color: #ff0000" />
												</logic:equal>
												<logic:notEqual name="corLocalidade" value="exception">
													<html:text property="descricaoLocalidade" size="35"
														readonly="true"
														style="background-color:#EFEFEF; border:0; color: #000000" />
												</logic:notEqual>
											</logic:present> <logic:notPresent name="corLocalidade">
												<logic:empty name="InserirUnidadeOrganizacionalActionForm"
													property="idLocalidade">
													<html:text property="descricaoLocalidade" size="35"
														value="" readonly="true"
														style="background-color:#EFEFEF; border:0; color: #ff0000" />
												</logic:empty>
												<logic:notEmpty
													name="InserirUnidadeOrganizacionalActionForm"
													property="idLocalidade">
													<html:text property="descricaoLocalidade" size="35"
														readonly="true"
														style="background-color:#EFEFEF; border:0; color: #000000" />
												</logic:notEmpty>
											</logic:notPresent> <a
												href="javascript:limparPesquisaLocalidade();"> <img
												src="<bean:message key="caminho.imagens"/>limparcampo.gif"
												border="0" title="Apagar" /></a></td>
										</tr>
										<tr>
											<td><strong> Ger&ecirc;ncia Regional:</strong></td>

											<td><strong> <html:select property="gerenciaRegional"
												style="width: 230px;"
												onchange="javascript:validarUnidadeTipo('true');">
												<html:option
													value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;
														</html:option>


												<logic:present name="colecaoGerenciaRegional"
													scope="session">
													<html:options collection="colecaoGerenciaRegional"
														labelProperty="nome" property="id" />
												</logic:present>

											</html:select> </strong></td>
										</tr>
										
										<tr>
											<td><strong>Unidade de Negocio:</strong></td>

											<td><strong>
												<html:select property="unidadeNegocio" style="width: 230px;" onchange="javascript:validarUnidadeTipo('true');">
													<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
													<logic:present name="colecaoUnidadeNegocio" scope="session">
														<html:options collection="colecaoUnidadeNegocio" labelProperty="nome" property="id" />
													</logic:present>
												</html:select>
											</strong></td>
										</tr>

										<tr>
											<td><strong>Descri&ccedil;&atilde;o:</strong> <span
												class="style2"> <strong> <font color="#FF0000">*</font> </strong>
											</span></td>

											<td><html:text maxlength="80" property="descricao"
												tabindex="1" size="40" /></td>
										</tr>

										<tr>
											<td><strong>Sigla:</strong></td>
											<td><html:text maxlength="5" property="sigla" tabindex="1"
												size="5" /></td>
										</tr>

										<tr>
											<td><strong>Empresa:</strong> <span class="style2"> <strong>
											<font color="#FF0000">*</font> </strong> </span></td>

											<td><strong> <html:select property="idEmpresa"
												style="width: 230px;">
												<html:option
													value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;
													</html:option>

												<logic:present name="colecaoEmpresa" scope="session">
													<html:options collection="colecaoEmpresa"
														labelProperty="descricao" property="id" />
												</logic:present>
											</html:select> </strong></td>

										</tr>
										<tr>
											<td><strong>Unidade Superior:</strong></td>
											<td width="63%"><html:text property="idUnidadeSuperior"
												tabindex="11" size="4" maxlength="4"
												onkeypress="validaEnterComMensagem(event, 'exibirInserirUnidadeOrganizacionalAction.do?objetoConsulta=2', 'idUnidadeSuperior', 'Unidade Superior');return isCampoNumerico(event);" />
											<a
												href="javascript:chamarPopup('exibirPesquisarUnidadeSuperiorAction.do', 'unidadeSuperior', null, null, 275, 480, '',document.forms[0].idUnidadeSuperior);">
											<img src="<bean:message key="caminho.imagens"/>pesquisa.gif"
												border="0" width="23" height="21" title="Pesquisar Unidade Superior"></a> <logic:present
												name="corUnidadeSuperior">
												<logic:equal name="corUnidadeSuperior" value="exception">
													<html:text property="descricaoUnidadeSuperior" size="35"
														readonly="true"
														style="background-color:#EFEFEF; border:0; color: #ff0000" />
												</logic:equal>
												<logic:notEqual name="corUnidadeSuperior" value="exception">
													<html:text property="descricaoUnidadeSuperior" size="35"
														readonly="true"
														style="background-color:#EFEFEF; border:0; color: #000000" />
												</logic:notEqual>
											</logic:present> <logic:notPresent name="corUnidadeSuperior">
												<logic:empty name="InserirUnidadeOrganizacionalActionForm"
													property="idUnidadeSuperior">
													<html:text property="descricaoUnidadeSuperior" size="35"
														value="" readonly="true"
														style="background-color:#EFEFEF; border:0; color: #ff0000" />
												</logic:empty>
												<logic:notEmpty
													name="InserirUnidadeOrganizacionalActionForm"
													property="idUnidadeSuperior">
													<html:text property="descricaoUnidadeSuperior" size="35"
														readonly="true"
														style="background-color:#EFEFEF; border:0; color: #000000" />
												</logic:notEmpty>
											</logic:notPresent> <a
												href="javascript:limparUnidadeSuperior();"> <img
												src="<bean:message key="caminho.imagens"/>limparcampo.gif"
												border="0" title="Apagar" /></a></td>
										</tr>

										<tr>
											<td><strong> Unidade Centralizadora:</strong></td>

											<td><strong> <html:select property="idUnidadeCentralizadora"
												style="width: 230px;">
												<html:option
													value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;
													</html:option>

												<logic:present name="colecaoUnidadeCentralizadora"
													scope="session">
													<html:options collection="colecaoUnidadeCentralizadora"
														labelProperty="descricao" property="id" />
												</logic:present>
											</html:select> </strong></td>
										</tr>
										
										
										<tr>
											<td><strong> Unidade Repavimentadora:</strong></td>

											<td><strong> <html:select property="idUnidadeRepavimentadora"
												style="width: 230px;">
												<html:option
													value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;
													</html:option>

												<logic:present name="colecaoUnidadeRepavimentadora"
													scope="session">
													<html:options collection="colecaoUnidadeRepavimentadora"
														labelProperty="descricao" property="id" />
												</logic:present>
											</html:select> </strong></td>
										</tr>
										
										

										<tr>
											<td><strong>Unidade de Esgoto?</strong> <span class="style2">
											<strong> <font color="#FF0000">*</font> </strong> </span></td>
											<td><html:radio property="unidadeEsgoto"
												value="<%=""+ConstantesSistema.SIM%>" /> <strong>Sim</strong>
											<html:radio property="unidadeEsgoto"
												value="<%=""+ConstantesSistema.NAO%>" /> <strong>N&atilde;o</strong>
											</td>
										</tr>

										<tr>
											<td><strong>Unidade Abre Registro de Atendimento?</strong> <span
												class="style2"> <strong> <font color="#FF0000">*</font> </strong>
											</span></td>
											<td><html:radio property="unidadeAbreRegistro"
												value="<%=""+ConstantesSistema.SIM%>"
												onclick="javascript:verificaIndicadorAbreRegistro();" /> <strong>Sim</strong>
											<html:radio property="unidadeAbreRegistro"
												value="<%=""+ConstantesSistema.NAO%>"
												onclick="javascript:verificaIndicadorAbreRegistro();" /> <strong>N&atilde;o</strong>
											</td>
										</tr>

										<tr>
											<td><strong>Unidade Aceita Tramita&ccedil;&atilde;o?</strong>
											<span class="style2"> <strong> <font color="#FF0000">*</font>
											</strong> </span></td>
											<td><html:radio property="unidadeAceita"
												value="<%=""+ConstantesSistema.SIM%>" /> <strong>Sim</strong>
											<html:radio property="unidadeAceita"
												value="<%=""+ConstantesSistema.NAO%>" /> <strong>N&atilde;o</strong>
											</td>
										</tr>

										<tr>
											<td><strong>Unidade Central Atendimento</strong> <span
												class="style2"> <strong> <font color="#FF0000">*</font> </strong>
											</span></td>
											<td><html:radio property="unidadeCentralAtendimento"
												value="<%=""+ConstantesSistema.SIM%>" /> <strong>Sim</strong>
											<html:radio property="unidadeCentralAtendimento"
												value="<%=""+ConstantesSistema.NAO%>" /> <strong>N&atilde;o</strong>
											</td>
										</tr>

										<tr>
											<td><strong>Unidade Tarifa Social</strong> <span
												class="style2"> <strong> <font color="#FF0000">*</font> </strong>
											</span></td>
											<td><html:radio property="unidadeTarifaSocial"
												value="<%=""+ConstantesSistema.SIM%>" /> <strong>Sim</strong>
											<html:radio property="unidadeTarifaSocial"
												value="<%=""+ConstantesSistema.NAO%>" /> <strong>N&atilde;o</strong>
											</td>
										</tr>


										<tr>
											<td><strong> Meio de Solicita&ccedil;&atilde;o Padr&atilde;o:
											<span class="style2"> <strong> <font color="#FF0000">*</font>
											</strong> </span> </strong></td>

											<td><strong> <html:select property="meioSolicitacao"
												style="width: 230px;">
												<html:option
													value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;
													</html:option>

												<logic:present name="colecaoMeioSolicitacao" scope="session">
													<html:options collection="colecaoMeioSolicitacao"
														labelProperty="descricao" property="id" />
												</logic:present>
											</html:select> </strong></td>
										</tr>
										
										<tr>
											<td height="24">
												<strong>Município:</strong>
											</td>
											<td colspan="2">
												<html:text  maxlength="4" 
															tabindex="7"
															property="idMunicipio" 
															size="4"
															onkeypress="javascript:validaEnter(event, 'exibirInserirUnidadeOrganizacionalAction.do?objetoConsulta=3', 'idMunicipio');return isCampoNumerico(event);" />
												<a href="javascript:pesquisarMunicipio();">
													<img width="23" height="21" border="0"
														src="<bean:message key="caminho.imagens"/>pesquisa.gif"
														title="Pesquisar Municipio" />
												</a> 
												<logic:present name="idMunicipio">
													<logic:equal name="idMunicipio" value="exception">
														<html:text  property="descricaoMunicipio" 
																	size="35" 
																	maxlength="35"
																	readonly="true"
																	style="background-color:#EFEFEF; border:0; color: #ff0000" />
													</logic:equal>
													<logic:notEqual name="idMunicipio" value="exception">
														<html:text  property="descricaoMunicipio" 
																	size="35" 
																	maxlength="35"
																	readonly="true"
																	style="background-color:#EFEFEF; border:0; color: #000000" />
													</logic:notEqual>
												</logic:present> 
												<logic:notPresent name="idMunicipio">
													<logic:empty name="InserirUnidadeOrganizacionalActionForm" property="idMunicipio">
														<html:text property="descricaoMunicipio" value="" 
															size="35"
															maxlength="35" readonly="true"
															style="background-color:#EFEFEF; border:0; color: #ff0000" />
													</logic:empty>
													<logic:notEmpty name="InserirUnidadeOrganizacionalActionForm" property="idMunicipio">
														<html:text  property="descricaoMunicipio" 
																	size="35" 
																	maxlength="35"
																	readonly="true"
																	style="background-color:#EFEFEF; border:0; color: #000000" />
													</logic:notEmpty>
												</logic:notPresent>
												<a href="javascript:limparMunicipio();"> 
													<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
														 border="0" 
														 title="Apagar" />
												</a>
											</td>
										</tr>
										
										<tr>
											<td><strong>Município(s):</strong></td>
											<td colspan="3" align="right">
													<input  type="button" class="bottonRightCol" 
															value="Adicionar"
															tabindex="25" 
															style="width: 80px; align: right;"
															onclick="javascript:incluirObjeto();" 
															name="botaoAdicionarMunicipio">
											</td>
										</tr>
										<tr>
											<td colspan="3">
											<table width="100%" cellpadding="0" cellspacing="0">
												<tr>
													<td>
													<table width="100%" bgcolor="#99CCFF">
														<tr bgcolor="#99CCFF">
															<td width="10%">
																<div align="left"><strong>Remover</strong></div>
															</td>
															<td width="20%">
																<div align="center"><strong>Código</strong></div>
															</td>
															<td width="70%">
																<div align="center"><strong>Descrição</strong></div>
															</td>
															
														</tr>
													</table>
													</td>
												</tr>
						
												<tr>
													<td>
											
													<%String cor = "#FFFFFF";%> 
													<logic:present name="colecaoMunicipioSelecionado" scope="session">
								
														<div style="width: 100%; height: 100; overflow: auto;">
								
														<table width="100%" cellpadding="0" cellspacing="0">
															<tr>
																<td><%cor = "#cbe5fe";%>
								
																<table width="100%" align="center" bgcolor="#99CCFF">
								
																	<logic:iterate name="colecaoMunicipioSelecionado" 
																		id="municipio"
																		type="Municipio">
																		<c:set var="count" value="${count+1}" />
																		<c:choose>
																			<c:when test="${count%2 == '1'}">
																				<tr bgcolor="#FFFFFF">
																			</c:when>
																			<c:otherwise>
																				<tr bgcolor="#cbe5fe">
																			</c:otherwise>
																		</c:choose>
								
																			<td align="center" width="10%">
																				<img
																				src="<bean:message key='caminho.imagens'/>Error.gif"
																				onclick="remover('${count}')"
																				title="Remover" style="cursor: hand;">
																				
																					
																			<td width="20%">
																				<div align="center">
																					<logic:present name="municipio" property="id">
																						<bean:write name="municipio" property="id" />
																					</logic:present> 
								
																					<logic:notPresent name="municipio" property="id">&nbsp;</logic:notPresent>
																				</div>
																			</td>
																			
																			<td width="70%">
																				<div align="center">
																					<logic:present name="municipio" property="nome">
																						<bean:write name="municipio" property="nome" />
																					</logic:present> 
																					<logic:notPresent name="municipio" property="nome">&nbsp;</logic:notPresent>
																				</div>
																			</td>
																			
																	</logic:iterate>
								
																</table>
								
																</td>
															</tr>
								
														</table>
								
														</div>
													</logic:present>
													
													</td>
												</tr>
											</table>
											
											</td>
										</tr>
									</table>
									</td>
								</tr>
							</table>
							</td>
						</tr>
						<tr>
							<td>
							<table width="100%">

								<tr>
									<td width="40%" align="left">
										<input  type="button"
												name="ButtonReset" class="bottonRightCol" value="Limpar"
												onClick="javascript:window.location.href='/gsan/exibirInserirUnidadeOrganizacionalAction.do?menu=sim'"> 
										<input type="button"
										name="ButtonCancelar" class="bottonRightCol" value="Cancelar"
										onClick="javascript:window.location.href='/gsan/telaPrincipal.do'">
									</td>

									<td align="right"><input name="Button" type="button"
										class="bottonRightCol" value="Inserir" onclick="validaForm();">
									</td>

								</tr>
							</table>
							</td>

						</tr>

					</table>
					</td>
				</tr>

			</table>

			</td>
		</tr>

	</table>
	<!-- Fim do Corpo - Rafael Pinto -->
	<%@ include file="/jsp/util/rodape.jsp"%>

</html:form>
</body>
</html:html>
