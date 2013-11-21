<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>

<title>GSAN - Sistema Integrado de Gest&atilde;o de Servi&ccedil;os de Saneamento</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false"
	formName="ImovelOutrosCriteriosActionForm" dynamicJavascript="false" />

<script>
<!-- Begin 

     var bCancel = false; 

    function validateImovelOutrosCriteriosActionForm(form) {                                                                   
        if (bCancel) 
      return true; 
        else 
       return validateCaracterEspecial(form) && validateInteger(form);
    } 

    function caracteresespeciais () { 
     this.aa = new Array("idCliente", "Cliente deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
     this.ab = new Array("idImovelCondominio", "Imóvel Condomínio deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
     this.ac = new Array("idImovelPrincipal", "Imóvel Principal deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
    } 

    function IntegerValidations () { 
     this.aa = new Array("idCliente", "Cliente deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
     this.ab = new Array("idImovelCondominio", "Imóvel Condomínio deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
     this.ac = new Array("idImovelPrincipal", "Imóvel Principal deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
    } 
-->    
</script>


<script language="JavaScript">

 var bCancel = false;


function validarForm(form){

  if(validatePesquisarActionForm(form)){
    form.submit();
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

function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {

	var form = document.ImovelOutrosCriteriosActionForm;

	if (tipoConsulta == 'cliente') {
      form.idCliente.value = codigoRegistro;	
	  form.nomeCliente.value = descricaoRegistro;
	  form.nomeCliente.style.color = "#000000";      

      }

	if (tipoConsulta == 'imovel') {
      form.idImovelCondominio.value = codigoRegistro;
	  form.inscricaoImovelCondominio.value = descricaoRegistro;
	  form.inscricaoImovelCondominio.style.color = "#000000";      
	}

	if(tipoConsulta == 'principal'){
		form.idImovelPrincipal.value = codigoRegistro;
	  form.inscricaoImovelPrincipal.value = descricaoRegistro;
	  form.inscricaoImovelPrincipal.style.color = "#000000";      

	}	

}

function limparCampos(tipo){
	var form = document.ImovelOutrosCriteriosActionForm;
	
	switch(tipo){
		case 1: 
			form.idCliente.value = "";
			form.nomeCliente.value = "";
			break;
		case 2: 
			form.idImovelCondominio.value = "";
			form.inscricaoImovelCondominio.value = "";
			break;			
		case 3:
			form.idImovelPrincipal.value = "";
			form.inscricaoImovelPrincipal.value = "";
			break;			
	}
}

function liberaTipoCliente(tipoRelatorio){
	var form = document.ImovelOutrosCriteriosActionForm;
	if(form.idCliente.value != ''){
		form.descricao.value = "-1";
		form.descricao.disabled = true;
	} else if (tipoRelatorio != "RelatorioCadastroConsumidoresInscricao" && tipoRelatorio != "RelatorioImoveisEndereco") {
		form.descricao.disabled = false;
		form.nomeCliente.value = "";
	}
}

function verificarSelecaoIndicadorCpfCnpj(aux){

	var form = document.ImovelOutrosCriteriosActionForm;
	
	if(parseInt(aux.value) == 1){
		form.cpfCnpj.value = '';
		form.cpfCnpj.disabled = false;
	}else{
		form.cpfCnpj.value = '';
		form.cpfCnpj.disabled = true;		
	}
	
}

</script>
</head>

<body leftmargin="5" topmargin="5"
	onload="javascript:setarFoco('${requestScope.nomeCampo}');javascript:verificarSelecaoIndicadorCpfCnpj('${requestScope.indicadorCpfCnpjInformado}');">
<div id="formDiv">
<html:form action="/filtrarImovelOutrosCriteriosWizardAction"
	name="ImovelOutrosCriteriosActionForm"
	type="gcom.gui.cadastro.imovel.ImovelOutrosCriteriosActionForm"
	method="post"
	onsubmit="return validateImovelOutrosCriteriosActionForm(this);">

	<jsp:include
		page="/jsp/util/wizard/navegacao_abas_wizard_valida_avancar_valida_voltar.jsp?numeroPagina=2" />

	
	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>

	<table width="770" border="0" cellspacing="5" cellpadding="0">
		<input type="hidden" name="numeroPagina" value="2" />
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
			<td width="645" valign="top" class="centercoltext">
			<table height="100%">
				<tr>
					<td></td>
				</tr>
			</table>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><html:img src="imagens/parahead_left.gif" border="0" /></td>
					<td class="parabg">Filtrar Imóvel</td>
					<td width="11" valign="top"><html:img
						src="imagens/parahead_right.gif" border="0" /></td>
				</tr>
			</table>
			<!--Fim Tabela Reference a Páginação da Tela de Processo-->
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>
					<td colspan="4">Para filtrar o(s) im&oacute;vel(is) pela
					rela&ccedil;&atilde;o com cliente(s) ou com im&oacute;vel(is),
					informe os dados abaixo:</td>
				</tr>
				<tr>
					<td width="158"><strong>Cliente:</strong></td>
					<td width="439" colspan="3" align="right">
					<div align="left"><strong> <logic:present
						name="parametroGerarRelatorio">
						<logic:equal name="parametroGerarRelatorio"
							value="RelatorioCadastroConsumidoresInscricao">
							<html:text maxlength="9" property="idCliente" size="9"
								onkeyup="liberaTipoCliente('${sessionScope.parametroGerarRelatorio}');"
								onkeypress="validaEnter(event, 'filtrarImovelOutrosCriteriosWizardAction.do?action=exibirClientesImoveisRelacionados&objetoConsulta=1&inscricaoTipo=origem', 'idCliente');return isCampoNumerico(event);"
								disabled="true" />
							<img width="23" height="21" border="0"
								src="<bean:message key="caminho.imagens"/>pesquisa.gif"
								title="Pesquisar" />
						</logic:equal>
						<logic:notEqual name="parametroGerarRelatorio"
							value="RelatorioCadastroConsumidoresInscricao">
							<logic:equal name="parametroGerarRelatorio"
								value="RelatorioImoveisEndereco">
								<html:text maxlength="9" property="idCliente" size="9"
									onkeyup="liberaTipoCliente('${sessionScope.parametroGerarRelatorio}');"
									onkeypress="validaEnter(event, 'filtrarImovelOutrosCriteriosWizardAction.do?action=exibirClientesImoveisRelacionados&objetoConsulta=1&inscricaoTipo=origem', 'idCliente');return isCampoNumerico(event);"
									disabled="true" />
								<img width="23" height="21" border="0"
									src="<bean:message key="caminho.imagens"/>pesquisa.gif"
									title="Pesquisar" />
							</logic:equal>
							<logic:notEqual name="parametroGerarRelatorio"
								value="RelatorioImoveisEndereco">
								<html:text maxlength="9" property="idCliente" size="9"
									onkeyup="liberaTipoCliente('${sessionScope.parametroGerarRelatorio}');"
									onkeypress="validaEnter(event, 'filtrarImovelOutrosCriteriosWizardAction.do?action=exibirClientesImoveisRelacionados&objetoConsulta=1&inscricaoTipo=origem', 'idCliente');return isCampoNumerico(event);" />
								<a
									href="javascript:chamarPopup('exibirPesquisarClienteAction.do', null, null, null, 275, 480, '');"><img
									width="23" height="21" border="0"
									src="<bean:message key="caminho.imagens"/>pesquisa.gif"
									title="Pesquisar" /></a>
							</logic:notEqual>
						</logic:notEqual>
					</logic:present> <logic:notPresent name="parametroGerarRelatorio">
						<html:text maxlength="9" property="idCliente" size="9"
							onkeyup="liberaTipoCliente('${sessionScope.parametroGerarRelatorio}');"
							onkeypress="validaEnter(event, 'filtrarImovelOutrosCriteriosWizardAction.do?action=exibirClientesImoveisRelacionados&objetoConsulta=1&inscricaoTipo=origem', 'idCliente');return isCampoNumerico(event);" />
						<a
							href="javascript:chamarPopup('exibirPesquisarClienteAction.do', null, null, null, 275, 480, '');"><img
							width="23" height="21" border="0"
							src="<bean:message key="caminho.imagens"/>pesquisa.gif"
							title="Pesquisar" /></a>
					</logic:notPresent> <logic:present name="corTipoCliente"
						scope="request">

						<logic:equal name="corTipoCliente" value="exception"
							scope="request">
							<html:text property="nomeCliente" size="40" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:equal>

						<logic:notEqual name="corTipoCliente" value="exception"
							scope="request">
							<html:text property="nomeCliente" size="40" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEqual>

					</logic:present> <a
						href="javascript:limparCampos(1);liberaTipoCliente('${sessionScope.parametroGerarRelatorio}');">
					<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar" /></a>
					</td>
				</tr>
				<tr>
					<td><strong>Tipo da Rela&ccedil;&atilde;o:</strong></td>
					<td colspan="3" align="right">
					<div align="left"><html:link href="usuario_pesquisar.htm">
					</html:link><strong> <html:select property="indicadorUso"
						style="width:200">
						<html:option value="-1">&nbsp;</html:option>
						<html:options collection="collectionsClienteRelacaoTipo"
							labelProperty="descricao" property="id"></html:options>
					</html:select> </strong></div>
					</td>
				</tr>
				<tr>
					<td><strong>Tipo de Cliente:</strong></td>
					<td colspan="3" align="right">
					<div align="left"><logic:present name="parametroGerarRelatorio">
						<logic:equal name="parametroGerarRelatorio"
							value="RelatorioCadastroConsumidoresInscricao">
							<html:select property="descricao" disabled="true" />
						</logic:equal>
						<logic:notEqual name="parametroGerarRelatorio"
							value="RelatorioCadastroConsumidoresInscricao">
							<logic:equal name="parametroGerarRelatorio"
								value="RelatorioImoveisEndereco">
								<html:select property="descricao" disabled="true" />
							</logic:equal>
							<logic:notEqual name="parametroGerarRelatorio"
								value="RelatorioImoveisEndereco">
								<html:select property="descricao">
									<html:option value="-1">&nbsp;</html:option>
									<html:options collection="collectionsClienteTipo"
										labelProperty="descricao" property="id" />
								</html:select>
							</logic:notEqual>
						</logic:notEqual>
					</logic:present> <logic:notPresent name="parametroGerarRelatorio">
						<html:select property="descricao">
							<html:option value="-1">&nbsp;</html:option>
							<html:options collection="collectionsClienteTipo"
								labelProperty="descricao" property="id" />
						</html:select>
					</logic:notPresent>
					</td>
				</tr>
				<tr>
						<td><strong>CPF/CNPJ:</strong></td>
						<td>
							<html:radio property="indicadorCpfCnpjInformado"
								value="1" onchange="verificarSelecaoIndicadorCpfCnpj(this);" />
							Informado
							<html:radio property="indicadorCpfCnpjInformado"
								value="2" onchange="verificarSelecaoIndicadorCpfCnpj(this);"/>
							Não Informado
							<html:radio property="indicadorCpfCnpjInformado"
								value="3" onchange="verificarSelecaoIndicadorCpfCnpj(this);"/>
							Todos
						</td>
				</tr>
				<tr>	
						<td>&nbsp;</td>
						<td>
							<html:text maxlength="14" property="cpfCnpj" size="15" 
								onkeypress="return isCampoNumerico(event);"/>						
						</td>
				</tr>
				<tr>
					<td colspan="4">
					<hr>
					</td>
				</tr>
				<tr>
					<td><strong>Im&oacute;vel Condom&iacute;nio:</strong></td>
					<td colspan="3" align="right">
					<div align="left"><strong><logic:present
						name="parametroGerarRelatorio">
						<logic:equal name="parametroGerarRelatorio"
							value="RelatorioCadastroConsumidoresInscricao">
							<html:text maxlength="9" property="idImovelCondominio" size="9"
								onkeypress="validaEnter(event, 'filtrarImovelOutrosCriteriosWizardAction.do?action=exibirClientesImoveisRelacionados&objetoConsulta=1&inscricaoTipo=origem', 'idImovelCondominio');return isCampoNumerico(event);"
								disabled="true" />
							<img width="23" height="21" border="0"
								src="<bean:message key="caminho.imagens"/>pesquisa.gif"
								title="Pesquisar" />
						</logic:equal>
						<logic:notEqual name="parametroGerarRelatorio"
							value="RelatorioCadastroConsumidoresInscricao">
							<logic:equal name="parametroGerarRelatorio"
								value="RelatorioImoveisEndereco">
								<html:text maxlength="9" property="idImovelCondominio" size="9"
									onkeypress="validaEnter(event, 'filtrarImovelOutrosCriteriosWizardAction.do?action=exibirClientesImoveisRelacionados&objetoConsulta=1&inscricaoTipo=origem', 'idImovelCondominio');return isCampoNumerico(event);"
									disabled="true" />
								<img width="23" height="21" border="0"
									src="<bean:message key="caminho.imagens"/>pesquisa.gif"
									title="Pesquisar" />
							</logic:equal>
							<logic:notEqual name="parametroGerarRelatorio"
								value="RelatorioImoveisEndereco">
								<html:text maxlength="9" property="idImovelCondominio" size="9"
									onkeypress="validaEnter(event, 'filtrarImovelOutrosCriteriosWizardAction.do?action=exibirClientesImoveisRelacionados&objetoConsulta=1&inscricaoTipo=origem', 'idImovelCondominio');return isCampoNumerico(event);" />
								<a
									href="javascript:chamarPopup('exibirPesquisarImovelAction.do?pesquisarImovelCondominio=OK', 'condominio', null, null, 275, 480, '');"><img
									width="23" height="21" border="0"
									src="<bean:message key="caminho.imagens"/>pesquisa.gif"
									title="Pesquisar" /></a>
							</logic:notEqual>
						</logic:notEqual>
					</logic:present> <logic:notPresent name="parametroGerarRelatorio">
						<html:text maxlength="9" property="idImovelCondominio" size="9"
							onkeypress="validaEnter(event, 'filtrarImovelOutrosCriteriosWizardAction.do?action=exibirClientesImoveisRelacionados&objetoConsulta=1&inscricaoTipo=origem', 'idImovelCondominio');return isCampoNumerico(event);" />
						<a
							href="javascript:chamarPopup('exibirPesquisarImovelAction.do?pesquisarImovelCondominio=OK', 'condominio', null, null, 275, 480, '');"><img
							width="23" height="21" border="0"
							src="<bean:message key="caminho.imagens"/>pesquisa.gif"
							title="Pesquisar" /></a>
					</logic:notPresent> <logic:present name="corTipoImovelCondominio"
						scope="request">

						<logic:equal name="corTipoImovelCondominio" value="exception"
							scope="request">
							<html:text property="inscricaoImovelCondominio" size="30"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:equal>

						<logic:notEqual name="corTipoImovelCondominio" value="exception"
							scope="request">
							<html:text property="inscricaoImovelCondominio" size="30"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEqual>

					</logic:present> <a href="javascript:limparCampos(2);"> <img
						src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar" /></a></strong></div>

					</td>
				</tr>
				<tr>
					<td><strong>Im&oacute;vel Principal:</strong></td>
					<td colspan="3" align="right">
					<div align="left"><strong> <logic:present
						name="parametroGerarRelatorio">
						<logic:equal name="parametroGerarRelatorio"
							value="RelatorioCadastroConsumidoresInscricao">
							<html:text maxlength="9" property="idImovelPrincipal" size="9"
								onkeypress="validaEnter(event, 'filtrarImovelOutrosCriteriosWizardAction.do?action=exibirClientesImoveisRelacionados&objetoConsulta=1&inscricaoTipo=origem', 'idImovelPrincipal');return isCampoNumerico(event);"
								disabled="true" />
							<img width="23" height="21" border="0"
								src="<bean:message key="caminho.imagens"/>pesquisa.gif"
								title="Pesquisar" />
						</logic:equal>
						<logic:notEqual name="parametroGerarRelatorio"
							value="RelatorioCadastroConsumidoresInscricao">
							<logic:equal name="parametroGerarRelatorio"
								value="RelatorioImoveisEndereco">
								<html:text maxlength="9" property="idImovelPrincipal" size="9"
									onkeypress="validaEnter(event, 'filtrarImovelOutrosCriteriosWizardAction.do?action=exibirClientesImoveisRelacionados&objetoConsulta=1&inscricaoTipo=origem', 'idImovelPrincipal');return isCampoNumerico(event);"
									disabled="true" />
								<img width="23" height="21" border="0"
									src="<bean:message key="caminho.imagens"/>pesquisa.gif"
									title="Pesquisar" />
							</logic:equal>
							<logic:notEqual name="parametroGerarRelatorio"
								value="RelatorioImoveisEndereco">
								<html:text maxlength="9" property="idImovelPrincipal" size="9"
									onkeypress="validaEnter(event, 'filtrarImovelOutrosCriteriosWizardAction.do?action=exibirClientesImoveisRelacionados&objetoConsulta=1&inscricaoTipo=origem', 'idImovelPrincipal');return isCampoNumerico(event);" />
								<a
									href="javascript:chamarPopup('exibirPesquisarImovelAction.do', 'principal', null, null, 275, 480, '');"><img
									width="23" height="21" border="0"
									src="<bean:message key="caminho.imagens"/>pesquisa.gif"
									title="Pesquisar" /></a>
							</logic:notEqual>
						</logic:notEqual>
					</logic:present> <logic:notPresent name="parametroGerarRelatorio">
						<html:text maxlength="9" property="idImovelPrincipal" size="9"
							onkeypress="validaEnter(event, 'filtrarImovelOutrosCriteriosWizardAction.do?action=exibirClientesImoveisRelacionados&objetoConsulta=1&inscricaoTipo=origem', 'idImovelPrincipal');return isCampoNumerico(event);" />
						<a
							href="javascript:chamarPopup('exibirPesquisarImovelAction.do', 'principal', null, null, 275, 480, '');"><img
							width="23" height="21" border="0"
							src="<bean:message key="caminho.imagens"/>pesquisa.gif"
							title="Pesquisar" /></a>
					</logic:notPresent> <logic:present name="corTipoImovelPrincipal"
						scope="request">

						<logic:equal name="corTipoImovelPrincipal" value="exception"
							scope="request">
							<html:text property="inscricaoImovelPrincipal" size="30"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:equal>

						<logic:notEqual name="corTipoImovelPrincipal" value="exception"
							scope="request">
							<html:text property="inscricaoImovelPrincipal" size="30"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEqual>

					</logic:present> <a href="javascript:limparCampos(3);"> <img
						src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar" /></a></strong></div>
					</td>
				</tr>
				<!--  				<tr>
					<td><strong>Nome Conta:</strong></td>
					<td><html:select property="idNomeConta" style="width:200">
						<html:option value="-1">&nbsp;</html:option>
					</html:select></td>
				</tr>
-->
				<tr>
					<td colspan="4">
					<hr>
					</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td colspan="3" align="right">&nbsp;</td>
				</tr>

				<tr>
					<td colspan="4">
					<div align="right"><jsp:include
						page="/jsp/util/wizard/navegacao_botoes_wizard_valida_avancar_valida_voltar.jsp?numeroPagina=2" />
					</div>
					</td>
				</tr>
			</table>
			<p>&nbsp;</p>
		</tr>

	</table>
	<%@ include file="/jsp/util/rodape.jsp"%>
</html:form>
	</div>
</body>

<%@ include file="/jsp/util/telaespera.jsp"%>

<script>
	liberaTipoCliente('${sessionScope.parametroGerarRelatorio}');
	document.getElementById('botaoConcluir').onclick = function() { botaoAvancarTelaEspera('/gsan/filtrarImovelOutrosCriteriosWizardAction.do?concluir=true&action=validarImovelOutrosCriterios'); }
</script>

</html:html>
