<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%@page import="gcom.util.ConstantesSistema" isELIgnored="false"%>

<html:html>

<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="PesquisarActionForm" dynamicJavascript="false" />

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript">
<!--

     var bCancel = false; 

    function validatePesquisarActionForm(form) {                                                                   
        if (bCancel) 
      return true; 
        else 
       return validateCaracterEspecial(form) && validateLong(form) && validateCnpj(form) && validateCpf(form) && validateDate(form) && validateEmail(form); 
   } 

function caracteresespeciais () {

     this.aa = new Array("idCliente", "Código do cliente possui caracteres especiais.", new Function ("varName", " return this[varName];"));
     this.ab = new Array("idClienteResponsavel", "Código do Cliente Responsável possui caracteres especiais.", new Function ("varName", " return this[varName];"));
     this.ac = new Array("nomeCliente", "Nome possui caracteres especiais.", new Function ("varName", " return this[varName];"));
     this.ad = new Array("cnpj", "CNPJ possui caracteres especiais.", new Function ("varName", " return this[varName];"));
     this.ae = new Array("cpf", "CPF possui caracteres especiais.", new Function ("varName", " return this[varName];"));
     this.af = new Array("rg", "RG possui caracteres especiais.", new Function ("varName", " return this[varName];"));
     this.ag = new Array("idLogradouro", "Código do Logradouro possui caracteres especiais.", new Function ("varName", " return this[varName];"));
     this.ah = new Array("idMunicipio", "Código Município possui caracteres especiais.", new Function ("varName", " return this[varName];"));
     this.ai = new Array("codigoBairro", "Código Bairro possui caracteres especiais.", new Function ("varName", " return this[varName];"));
     this.aj = new Array("cep", "CEP possui caracteres especiais.", new Function ("varName", " return this[varName];"));
     this.ak = new Array("idImovel", "Matrícula do Imóvel possui caracteres especiais.", new Function ("varName", " return this[varName];"));

    }

    function IntegerValidations () {
     this.ab = new Array("cnpj", "CNPJ deve somente conter dígitos.", new Function ("varName", " return this[varName];"));
     this.ac = new Array("cpf", "CPF deve somente conter dígitos.", new Function ("varName", " return this[varName];"));
     this.ad = new Array("rg", "RG deve somente conter dígitos.", new Function ("varName", " return this[varName];"));
     this.ae = new Array("idMunicipio", "Código Município deve somente conter dígitos.", new Function ("varName", " return this[varName];"));
     this.af = new Array("codigoBairro", "Código Bairro deve somente conter dígitos.", new Function ("varName", " return this[varName];"));
     this.ag = new Array("id", "Código do cliente deve somente conter dígitos.", new Function ("varName", " return this[varName];"));
     this.ah = new Array("cep", "CEP deve somente conter dígitos.", new Function ("varName", " return this[varName];"));
     this.ai = new Array("idCliente", "Código do Cliente deve somente conter dígitos.", new Function ("varName", " return this[varName];"));
     this.aj = new Array("idClienteResponsavel", "Código do Cliente Responsável deve somente conter dígitos.", new Function ("varName", " return this[varName];"));
     this.ak = new Array("idImovel", "Matrícula do Imóvel deve somente conter dígitos.", new Function ("varName", " return this[varName];"));
     this.ag = new Array("idLogradouro", "Código do Logradouro deve somente conter dígitos.", new Function ("varName", " return this[varName];"));
    }

    function cnpj () {
     this.aa = new Array("cnpj", "CNPJ inválido.", new Function ("varName", " return this[varName];"));
    }

    function cpf () {
     this.aa = new Array("cpf", "CPF inválido.", new Function ("varName", " return this[varName];"));
    }

    function DateValidations () {

      this.aa = new Array("dataEmissao", "Data de Expedição do RG está inválida.", new Function ("varName", "this.datePattern='dd/MM/yyyy';  return this[varName];"));
      this.ab = new Array("dataNascimento", "Data de Nascimento está inválida.", new Function ("varName", "this.datePattern='dd/MM/yyyy';  return this[varName];"));
     
    }
    
    function email () {
     this.aa = new Array("email", "Email inválido.", new Function ("varName", " return this[varName];"));
    }
       

    //Recebe os dados do(s) popup(s)
    function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {

      var form = document.PesquisarActionForm;

       if (tipoConsulta == 'municipio') {
        form.idMunicipio.value = codigoRegistro;
        form.descricaoMunicipioCliente.value = descricaoRegistro;
        form.descricaoMunicipioCliente.style.color = "#000000";
      }

       if (tipoConsulta == 'bairro') {
        form.codigoBairro.value = codigoRegistro;
        form.descricaoBairroCliente.value = descricaoRegistro;
        form.descricaoBairroCliente.style.color = "#000000";
      }
      
       if (tipoConsulta == 'logradouro') {
        form.idLogradouro.value = codigoRegistro;
        form.descricaoLogradouroCliente.value = descricaoRegistro;
        form.descricaoLogradouroCliente.style.color = "#000000";
      }
      
       if (tipoConsulta == 'imovel') {
        form.idImovel.value = codigoRegistro;
        form.inscricao.value = descricaoRegistro;
        form.action = 'exibirFiltrarClienteOutrosCriteriosAction.do'
        form.submit();
      }
      if (tipoConsulta == 'cep') {
      limparPesquisaCep();
      form.cep.value = codigoRegistro;
      form.descricaoCep.value = descricaoRegistro;
      form.descricaoCep.style.color = "#000000";
      form.cep.focus();
    }
      
       if (tipoConsulta == 'responsavelSuperior') {
        form.idClienteResponsavel.value = codigoRegistro;
        form.nomeClienteResponsavel.value = descricaoRegistro;
		form.nomeClienteResponsavel.style.color = "#000000";
      }          

    }


    function limparMunicipio() {
        var form = document.PesquisarActionForm;

        form.idMunicipio.value = "";
        form.descricaoMunicipioCliente.value = "";
        form.codigoBairro.value = "";
        form.descricaoBairroCliente.value = "";
        form.idLogradouro.value = "";
        form.descricaoLogradouroCliente.value = "";
        form.cep.value = "";
    }

    function limparBairro() {
        var form = document.PesquisarActionForm;

        form.codigoBairro.value = "";
        form.descricaoBairroCliente.value = "";
        form.idLogradouro.value = "";
        form.descricaoLogradouroCliente.value = "";
        form.cep.value = "";
        form.descricaoCep.value = "";
    }

    function limparLogradouro() {
        var form = document.PesquisarActionForm;

        form.idLogradouro.value = "";
        form.descricaoLogradouroCliente.value = "";
        form.cep.value = "";
        form.descricaoCep.value = "";
    }
 
    function limparCliente() {
        var form = document.PesquisarActionForm;

        form.idClienteResponsavel.value = "";
        form.nomeClienteResponsavel.value = "";
    }
    
    function limparImovel() {
        var form = document.PesquisarActionForm;

        form.idImovel.value = "";
        form.inscricao.value = "";
        form.target='';
        form.action = 'exibirFiltrarClienteOutrosCriteriosAction.do';
        form.submit();

    }
    
    function limparCep() {
        var form = document.PesquisarActionForm;

        form.cep.value = "";
        form.descricaoCep.value = "";
    }

    function verificarExclusaoMutua() {
	var form = document.PesquisarActionForm;

	if (form.cpf.value != "" && form.cnpj.value != "") {
		alert("CPF e CNPJ são exclusivos. Informe um ou outro.")
		return false;

	} else if (form.rg.value != "" && form.cnpj.value != "") {
		alert("RG e CNPJ são exclusivos. Informe um ou outro.")
		return false;

	}

	return true;
    }
    
    function submitForm(form) {
    
   		var msgDataNascimento = "Data de nacimento não pode ser superior à data corrente.";
   		var msgDataEmissao = "Data de expedição do RG não pode ser superior à data corrente.";
		var msgDataNascimentoDataEmissao = "Data de nascimento não pode ser superior à data de expedição do RG.";
		var DATA_ATUAL = document.getElementById("DATA_ATUAL").value;

		if(verificarExclusaoMutua() &&
	 		testarCampoValorZero(document.PesquisarActionForm.idCliente, 'Código do Cliente') &&
			testarCampoValorZero(document.PesquisarActionForm.nomeCliente, 'Nome do Cliente') &&
			testarCampoValorZero(document.PesquisarActionForm.rg, 'RG') &&
			testarCampoValorZero(document.PesquisarActionForm.idLogradouro, 'Código do Logradouro') &&
			testarCampoValorZero(document.PesquisarActionForm.idMunicipio, 'Código Município') &&
			testarCampoValorZero(document.PesquisarActionForm.codigoBairro, 'Código Bairro') &&
			testarCampoValorZero(document.PesquisarActionForm.cep, 'CEP') && 
			testarCampoValorZero(document.PesquisarActionForm.cpf, 'CPF') &&
			testarCampoValorZero(document.PesquisarActionForm.cnpj, 'CNPJ') &&
			testarCampoValorZero(document.PesquisarActionForm.idImovel, 'Matrícula do Imóvel')) {
            
    		if(validatePesquisarActionForm(form)) {
    		
    			var idMunicipio = trim(form.idMunicipio.value);
    			var idBairro = trim(form.codigoBairro.value);
    			
				if (comparaData(form.dataNascimento.value, ">", DATA_ATUAL)){
					alert(msgDataNascimento);
				}
				else if (comparaData(form.dataEmissao.value, ">", DATA_ATUAL)){
					alert(msgDataEmissao);
				}
				else if (comparaData(form.dataNascimento.value, ">", form.dataEmissao.value)){
					alert(msgDataNascimentoDataEmissao);
				} else if ((idMunicipio == null || idMunicipio == "") && (idBairro != null && idBairro != "")) {
					alert('Informe o município');					
				} else {
					 form.action = 'filtrarClienteOutrosCriteriosAction.do?indicadorTela=clienteOutrosCriterios'     
	    			 form.submit();
	    }
	}
    }
    }
-->
</script>


</head>

<body leftmargin="5" topmargin="5"
	onload="javascript:setarFoco('${requestScope.nomeCampo}');">

<html:form
	action="/filtrarClienteOutrosCriteriosAction?indicadorTela=clienteOutrosCriterios"
	method="post" onsubmit="return validatePesquisarActionForm(this);">

	<INPUT TYPE="hidden" ID="DATA_ATUAL" value="${requestScope.dataAtual}" />

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
					<td class="parabg">Filtrar Cliente Por Outros Crit&eacute;rios</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>

			<table width="100%" border="0">
				<tr>
					<td colspan="4">Para filtrar o(s) cliente(s), informe os dados
					abaixo:</td>
				</tr>
				<tr>
					<td><strong>C&oacute;digo do Cliente:</strong></td>
					<td colspan="3"><html:text maxlength="9" property="idCliente"
						size="9" /></td>
				</tr>
				<tr>
					<td><strong>Nome do Cliente:</strong></td>
					<td colspan="3"><html:text maxlength="50" property="nomeCliente"
						size="60" /></td>
				</tr>
				<tr>
					<td><strong>Nome Abreviado:</strong></td>
					<td colspan="3"><html:text maxlength="20"
						property="nomeAbreviadoCliente" size="25" /></td>
				</tr>
				<tr>
					<td><strong>Matr&iacute;cula do Im&oacute;vel:</strong></td>
					<td colspan="3"><html:text maxlength="9" property="idImovel"
						size="9"
						onkeypress="form.target='';validaEnter(event, 'exibirFiltrarClienteOutrosCriteriosAction.do', 'idImovel')" />
					<img width="23" height="21"
						src="<bean:message key="caminho.imagens"/>pesquisa.gif"
						style="cursor:hand;"
						onclick="abrirPopup('exibirPesquisarImovelAction.do', 400, 800);"
						alt="Pesquisar" /> <logic:present name="matriculaInexistente"
						scope="request">
						<html:text property="inscricao" size="40" maxlength="40"
							readonly="true"
							style="background-color:#EFEFEF; border:0; color: #ff0000" />
					</logic:present> <logic:notPresent name="matriculaInexistente"
						scope="request">
						<html:text property="inscricao" size="40" maxlength="40"
							readonly="true"
							style="background-color:#EFEFEF; border:0; color: #000000" />
					</logic:notPresent><a href="javascript:limparImovel();"><img
						src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar" /></a></td>
				</tr>
				<tr>
					<td colspan="4">
					<table width="100%" cellpadding="0" cellspacing="0">
						<tr>
							<td>
							<table width="100%" border="0" cellpadding="1" cellspacing="0"
								bgcolor="#99CCFF" bordercolor="#99CCFF">
								<!--header da tabela interna -->
								<tr>
									<td align="center"><strong>Endere&ccedil;o</strong></td>
								</tr>
							</table>
							</td>

						</tr>
						<tr>
							<td><logic:present name="imovel" scope="request">
								<table width="100%" align="center" bgcolor="#99CCFF">
									<tr bgcolor="#FFFFFF">
										<td>
										<div align="center"><span id="endereco"> <bean:write
											name="imovel" property="enderecoFormatado" scope="request" /></span></div>
										</td>
									</tr>

								</table>
							</logic:present></td>
						</tr>
					</table>
					</td>

				</tr>
				<tr>
					<td><strong>Tipo do Cliente:</strong></td>
					<td colspan="3"><html:select property="tipoCliente"
						onchange="form.target='';redirecionarSubmit('exibirFiltrarClienteOutrosCriteriosAction.do');">
						<html:option
							value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
						<html:options collection="clienteTipos" labelProperty="descricao"
							property="id" />
					</html:select></td>
				</tr>
				<tr>
					<td colspan="4">
					<hr>
					</td>
				</tr>
				<tr>
					<td><strong>Pessoa F&iacute;sica </strong></td>
					<td>&nbsp;</td>
					<td><strong>Pessoa Jur&iacute;dica</strong></td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td><strong>CPF: </strong></td>
					<td><logic:present name="pessoaJuridica" scope="request">
						<html:text maxlength="11" property="cpf" size="11" readonly="true" />
					</logic:present> <logic:notPresent name="pessoaJuridica"
						scope="request">
						<html:text maxlength="11" property="cpf" size="11" />
					</logic:notPresent></td>
					<td><strong>CNPJ:</strong></td>
					<td><logic:present name="pessoaFisica" scope="request">
						<html:text maxlength="14" property="cnpj" size="14"
							readonly="true" />
					</logic:present> <logic:notPresent name="pessoaFisica"
						scope="request">
						<html:text maxlength="14" property="cnpj" size="14" />
					</logic:notPresent></td>
				</tr>
				<tr>
					<td><strong>RG: </strong></td>
					<td><logic:present name="pessoaJuridica" scope="request">
						<html:text maxlength="9" property="rg" size="9" readonly="true" />
					</logic:present> <logic:notPresent name="pessoaJuridica"
						scope="request">
						<html:text maxlength="9" property="rg" size="9" />
					</logic:notPresent></td>
					<td><strong>Ramo de Atividade:</strong></td>
					<td><logic:present name="pessoaFisica" scope="request">
						<html:select property="ramoAtividade" disabled="true">
							<html:option
								value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
						</html:select>
					</logic:present> <logic:notPresent name="pessoaFisica"
						scope="request">
						<html:select property="ramoAtividade">
							<html:option
								value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
							<html:options collection="ramosAtividades"
								labelProperty="descricao" property="id" />
						</html:select>
					</logic:notPresent></td>
				</tr>
				<tr>
					<td><strong>Data de Emiss&atilde;o:</strong></td>
					<td><logic:present name="pessoaJuridica" scope="request">
						<html:text maxlength="10" property="dataEmissao" size="10"
							readonly="true" />
						<img border="0"
							src="<bean:message key='caminho.imagens'/>calendario.gif"
							width="20" border="0" align="middle" alt="Exibir Calendário" />
					</logic:present> <logic:notPresent name="pessoaJuridica"
						scope="request">
						<html:text maxlength="10" property="dataEmissao" size="10"
							onkeyup="mascaraData(this, event);" />
						<a
							href="javascript:abrirCalendario('PesquisarActionForm', 'dataEmissao')">
						<img border="0"
							src="<bean:message key='caminho.imagens'/>calendario.gif"
							width="20" border="0" align="middle" alt="Exibir Calendário" /></a>
					</logic:notPresent> dd/mm/aaaa</td>
					<td colspan="2">
					<hr>
					</td>
				</tr>
				<tr>
					<td><strong>&Oacute;rg&atilde;o Emissor: </strong></td>
					<td><logic:present name="pessoaJuridica" scope="request">
						<html:select property="orgaoEmissor" disabled="true">
							<html:option
								value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
						</html:select>
					</logic:present> <logic:notPresent name="pessoaJuridica"
						scope="request">
						<html:select property="orgaoEmissor">
							<html:option
								value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
							<html:options collection="orgaosExpeditores"
								labelProperty="descricaoAbreviada" property="id" />
						</html:select>
					</logic:notPresent></td>
					<td colspan="2"><strong>Cliente Respons&aacute;vel Superior</strong>
					</td>
				</tr>
				<tr>
					<td><strong>Data de Nascimento: </strong></td>
					<td><logic:present name="pessoaJuridica" scope="request">
						<html:text maxlength="10" property="dataNascimento" size="10"
							readonly="true" />
						<img border="0"
							src="<bean:message key='caminho.imagens'/>calendario.gif"
							width="20" border="0" align="middle" alt="Exibir Calendário" />
					</logic:present> <logic:notPresent name="pessoaJuridica"
						scope="request">
						<html:text maxlength="10" property="dataNascimento" size="10"
							onkeyup="mascaraData(this, event);" />
						<a
							href="javascript:abrirCalendario('PesquisarActionForm', 'dataNascimento')">
						<img border="0"
							src="<bean:message key='caminho.imagens'/>calendario.gif"
							width="20" border="0" align="middle" alt="Exibir Calendário" /></a>
					</logic:notPresent> dd/mm/aaaa</td>
					<td><strong>C&oacute;digo: </strong></td>
					<td><logic:present name="pessoaFisica" scope="request">
						<html:text maxlength="9" property="idClienteResponsavel" size="9"
							readonly="true" />
						<img width="23" height="21"
							src="<bean:message key="caminho.imagens"/>pesquisa.gif"
							alt="Pesquisar" />
						<a href="javascript:limparCliente();"> <img
							src="<bean:message key="caminho.imagens"/>limparcampo.gif"
							border="0" title="Apagar" /></a>
					</logic:present> <logic:notPresent name="pessoaFisica"
						scope="request">
						<html:text maxlength="9" property="idClienteResponsavel" size="9"
							onkeypress="javascript:form.target = ''; return validaEnter(event, 'exibirFiltrarClienteOutrosCriteriosAction.do', 'idClienteResponsavel');" />
						<img width="23" height="21"
							src="<bean:message key="caminho.imagens"/>pesquisa.gif"
							style="cursor:hand;"
							onclick="abrirPopup('exibirPesquisarResponsavelSuperiorAction.do', 400, 800);"
							alt="Pesquisar" />
						<a href="javascript:limparCliente();"> <img
							src="<bean:message key="caminho.imagens"/>limparcampo.gif"
							border="0" title="Apagar" /></a>
					</logic:notPresent></td>
				</tr>
				<tr>
					<td><strong>Profiss&atilde;o: </strong></td>
					<td><logic:present name="pessoaJuridica" scope="request">
						<html:select property="profissao" disabled="true" style="width:120">
							<html:option
								value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
						</html:select>
					</logic:present> <logic:notPresent name="pessoaJuridica"
						scope="request">
						<html:select property="profissao" style="width:120">
							<html:option
								value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
							<html:options collection="profissoes" labelProperty="descricao"
								property="id" />
						</html:select>
					</logic:notPresent></td>
					<td><strong>Nome:</strong></td>
					<td><logic:present name="clienteNaoEncontrado" scope="request">
						<html:text property="nomeClienteResponsavel" size="25"
							maxlength="25" readonly="true"
							style="background-color:#EFEFEF; border:0; color: #ff0000" />
					</logic:present> <logic:notPresent name="clienteNaoEncontrado"
						scope="request">
						<html:text property="nomeClienteResponsavel" size="25"
							maxlength="25" readonly="true"
							style="background-color:#EFEFEF; border:0; color: #000000" />
					</logic:notPresent></td>
				</tr>
				<tr>
					<td><strong>Sexo: </strong></td>
					<td><logic:present name="pessoaJuridica" scope="request">
						<html:select property="sexo" disabled="true">
							<html:option
								value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
						</html:select>
					</logic:present> <logic:notPresent name="pessoaJuridica"
						scope="request">
						<html:select property="sexo">
							<html:option
								value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
							<html:options collection="sexos" labelProperty="descricao"
								property="id" />
						</html:select>
					</logic:notPresent></td>
					<td colspan="2">&nbsp;</td>
				</tr>
				<tr>
					<td colspan="4">
					<hr>
					</td>
				</tr>
				<tr>
					<td><strong>E-mail:</strong></td>
					<td colspan="3"><strong> <html:text maxlength="40" property="email"
						size="50" /> </strong></td>
				</tr>
				<tr>
					<td><strong>Munic&iacute;pio:</strong></td>
					<td colspan="3"><strong> <html:text maxlength="4"
						property="idMunicipio" size="4"
						onkeypress="javascript:form.target = ''; return validaEnter(event, 'exibirFiltrarClienteOutrosCriteriosAction.do', 'idMunicipio');"
						onkeyup="limparBairro(); limparLogradouro();" /> <img width="23"
						height="21"
						src="<bean:message key="caminho.imagens"/>pesquisa.gif"
						style="cursor:hand;"
						onclick="abrirPopup('exibirPesquisarMunicipioAction.do', 400, 800); limparBairro(); limparLogradouro();"
						alt="Pesquisar" /> <logic:present name="municipioNaoEncontrado"
						scope="request">
						<html:text property="descricaoMunicipioCliente" size="40"
							maxlength="30" readonly="true"
							style="background-color:#EFEFEF; border:0; color: #ff0000" />
					</logic:present> <logic:notPresent name="municipioNaoEncontrado"
						scope="request">
						<html:text property="descricaoMunicipioCliente" size="40"
							maxlength="30" readonly="true"
							style="background-color:#EFEFEF; border:0; color: #000000" />
					</logic:notPresent> <a href="javascript:limparMunicipio();"> <img
						src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar" /></a> </strong></td>
				</tr>
				<tr>
					<td><strong>Bairro:</strong></td>
					<td colspan="3"><strong> <html:text maxlength="4"
						property="codigoBairro" size="4"
						onkeypress="javascript:form.target = ''; return validaEnterDependencia(event, 'exibirFiltrarClienteOutrosCriteriosAction.do', this, document.forms[0].idMunicipio.value,'Municipio');"
						onkeyup="limparLogradouro();" /> <img width="23" height="21"
						src="<bean:message key="caminho.imagens"/>pesquisa.gif"
						style="cursor:hand;"
						onclick="abrirPopupDependencia('exibirPesquisarBairroAction.do?idMunicipio='+document.forms[0].idMunicipio.value, document.forms[0].idMunicipio.value, 'o município antes de informar o bairro', 400, 800); limparLogradouro();"
						alt="Pesquisar" /> <logic:present name="bairroNaoEncontrado"
						scope="request">
						<html:text property="descricaoBairroCliente" size="40"
							maxlength="30" readonly="true"
							style="background-color:#EFEFEF; border:0; color: #ff0000" />
					</logic:present> <logic:notPresent name="bairroNaoEncontrado"
						scope="request">
						<html:text property="descricaoBairroCliente" size="40"
							maxlength="30" readonly="true"
							style="background-color:#EFEFEF; border:0; color: #000000" />
					</logic:notPresent> <a href="javascript:limparBairro();"> <img
						src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar" /></a> </strong></td>
				</tr>
				<tr>
					<td><strong>Logradouro:</strong></td>
					<td colspan="3"><strong> <html:text maxlength="9"
						property="idLogradouro" size="10" onkeyup="limparCep();"
						onkeypress="javascript:form.target = ''; return validaEnter(event, 'exibirFiltrarClienteOutrosCriteriosAction.do', 'idLogradouro');" />
					<a
						href="javascript:abrirPopup('exibirPesquisarLogradouroAction.do?codigoMunicipio='+document.forms[0].idMunicipio.value+'&codigoBairro='+document.forms[0].codigoBairro.value+'&indicadorUsoTodos=1&primeriaVez=1', 400, 800);"><img
						width="23" border="0" height="21"
						src="<bean:message key="caminho.imagens"/>pesquisa.gif"
						style="cursor:hand;" alt="Pesquisar" /></a> <logic:present
						name="logradouroNaoEncontrado" scope="request">
						<html:text property="descricaoLogradouroCliente" size="40"
							maxlength="30" readonly="true"
							style="background-color:#EFEFEF; border:0; color: #ff0000" />
					</logic:present> <logic:notPresent name="logradouroNaoEncontrado"
						scope="request">
						<html:text property="descricaoLogradouroCliente" size="40"
							maxlength="30" readonly="true"
							style="background-color:#EFEFEF; border:0; color: #000000" />
					</logic:notPresent> <a href="javascript:limparLogradouro();"> <img
						src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar" /></a> </strong></td>
				</tr>
				<tr>
					<td><strong>CEP:</strong></td>
					<td colspan="3"><html:text maxlength="8" property="cep" size="8"
						onkeypress="form.target='';validaEnter(event, 'exibirFiltrarClienteOutrosCriteriosAction.do', 'cep')" />
					<a
						href="javascript:abrirPopup('exibirPesquisarCepAction.do?&indicadorUsoTodos=1', 400, 800);">
					<img width="23" height="21" border="0" title="Pesquisar Cep"
						src="<bean:message key="caminho.imagens"/>pesquisa.gif" /></a> <logic:present
						name="cepInexistente" scope="request">
						<html:text property="descricaoCep" size="40" maxlength="30"
							readonly="true"
							style="background-color:#EFEFEF; border:0; color: #ff0000" />
					</logic:present> <logic:notPresent name="cepInexistente"
						scope="request">
						<html:text property="descricaoCep" size="40" maxlength="30"
							readonly="true"
							style="background-color:#EFEFEF; border:0; color: #000000" />
					</logic:notPresent><a href="javascript:limparCep();"> <img
						src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar" /></a></td>
				</tr>
				<tr>
					<td colspan="4">
					<hr>
					</td>
				</tr>
				<tr>
					<td><strong>Indicador de Uso:</strong></td>
					<td colspan="3"><strong> <label> <html:radio value=""
						property="indicadorUso" /> Todos</label> <label> <html:radio
						value="<%=ConstantesSistema.INDICADOR_USO_ATIVO.toString()%>"
						property="indicadorUso" /> Ativos</label> <label> <html:radio
						value="<%=ConstantesSistema.INDICADOR_USO_DESATIVO.toString()%>"
						property="indicadorUso" /> Inativos</label> </strong></td>
				</tr>
				<tr>
					<td colspan="4">&nbsp;</td>
				</tr>
				<tr>
					<td colspan="4" class="style1">
					<table>
						<tr>
							<td colspan="2" align="left"><input name="Limpar" class="bottonRightCol" value="Limpar"
								type="button"
								onclick="window.location.href='/gsan/exibirFiltrarClienteOutrosCriteriosAction.do?menu=sim';">
							</td>
							<td align="right" width="540">
							<gsan:controleAcessoBotao name="Button" value="Filtrar"
							  onclick="submitForm(document.forms[0]);" url="filtrarClienteOutrosCriteriosAction.do"/>
						<!-- 	<input type="button"
								class="bottonRightCol" value="Filtrar"
								onclick="submitForm(document.forms[0]);" /> --></td>
							<td>&nbsp;</td>
						</tr>
					</table>
					<p>&nbsp;</p>
					</td>
				</tr>
			</table>

			</td>
		</tr>
	</table>
	<%@ include file="/jsp/util/rodape.jsp"%>
</body>
</html:form>
</html:html>
