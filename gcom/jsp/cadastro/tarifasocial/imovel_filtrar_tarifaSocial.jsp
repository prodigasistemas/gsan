<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<%@ page import="gcom.util.ConstantesSistema" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>

<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="InserirImovelActionForm" dynamicJavascript="false" />
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js" ></script>

<script>
<!-- Begin

     var bCancel = false;

    function validateFiltrarImovelActionForm(form) {
        if (bCancel)
      return true;
        else
       return testarCampoValorZero(document.InserirImovelActionForm.idLocalidade, 'Localidade')&&
              testarCampoValorZero(document.InserirImovelActionForm.matricula, 'Matrícula')&&
	      testarCampoValorZero(document.InserirImovelActionForm.idSetorComercial, 'Setor Comercial')&&
	      testarCampoValorZero(document.InserirImovelActionForm.idQuadra, 'Quadra')&&
	      testarCampoValorZero(document.InserirImovelActionForm.idCliente, 'Cliente')&&
	      testarCampoValorZero(document.InserirImovelActionForm.cep, 'Cep')&&
	      testarCampoValorZero(document.InserirImovelActionForm.idBairro, 'Bairro')&&
	      testarCampoValorZero(document.InserirImovelActionForm.idMunicipio, 'Município')&&
              validateCaracterEspecial(form) && validateLong(form);
   }

    function caracteresespeciais () {
     this.aa = new Array("matricula", "Matrícula possui caracteres especiais.", new Function ("varName", " return this[varName];"));
     this.ab = new Array("idLocalidade", "Localidade possui caracteres especiais.", new Function ("varName", " return this[varName];"));
     this.ac = new Array("idSetorComercial", "Setor Comercial possui caracteres especiais.", new Function ("varName", " return this[varName];"));
     this.ad = new Array("idQuadra", "Quadra possui caracteres especiais.", new Function ("varName", " return this[varName];"));
     this.ae = new Array("lote", "Lote possui caracteres especiais.", new Function ("varName", " return this[varName];"));
     this.af = new Array("subLote", "SubLote possui caracteres especiais.", new Function ("varName", " return this[varName];"));
     this.ag = new Array("idCliente", "Cliente possui caracteres especiais.", new Function ("varName", " return this[varName];"));
     this.ah = new Array("cep", "Cep possui caracteres especiais.", new Function ("varName", " return this[varName];"));
     this.ai = new Array("idBairro", "Bairro possui caracteres especiais.", new Function ("varName", " return this[varName];"));
     this.aj = new Array("idMunicipio", "Município possui caracteres especiais.", new Function ("varName", " return this[varName];"));
     this.al = new Array("logradouro", "Logradouro possui caracteres especiais.", new Function ("varName", " return this[varName];"));
    }


    function IntegerValidations () {
     this.aa = new Array("matricula", "Matrícula deve somente conter dígitos.", new Function ("varName", " return this[varName];"));
     this.ab = new Array("idLocalidade", "Localidade deve somente conter dígitos.", new Function ("varName", " return this[varName];"));
     this.ac = new Array("idSetorComercial", "Setor Comercial deve somente conter dígitos.", new Function ("varName", " return this[varName];"));
     this.ad = new Array("idQuadra", "Quadra deve somente conter dígitos.", new Function ("varName", " return this[varName];"));
     this.ae = new Array("lote", "Lote deve somente conter dígitos.", new Function ("varName", " return this[varName];"));
     this.af = new Array("subLote", "SubLote deve somente conter dígitos.", new Function ("varName", " return this[varName];"));
     this.ag = new Array("idCliente", "Cliente deve somente conter dígitos.", new Function ("varName", " return this[varName];"));
     this.ah = new Array("cep", "Cep deve somente conter dígitos.", new Function ("varName", " return this[varName];"));
     this.ai = new Array("idBairro", "Bairro deve somente conter dígitos.", new Function ("varName", " return this[varName];"));
     this.aj = new Array("idMunicipio", "Municipio deve somente conter dígitos.", new Function ("varName", " return this[varName];"));
    }
//End -->
</script>


<script>

function chamarFiltrar(){
  var form = document.forms[0];
  if (validateFiltrarImovelActionForm(form)) {
  	form.submit();
  }
}

function pesquisaEnter(tecla) {

  var form = document.forms[0];

  if (document.all) {
    var codigo = event.keyCode;
  } else {
    var codigo = tecla.which;
  }
  if(form.idLocalidade.value != '' || form.idSetorComercial.value != ''
     || form.idQuadra.value != '' || form.idCliente.value != '' || form.idBairro.value != ''
     || form.idMunicipio.value != '' || form.cep.value != ''){
    if (codigo == 13) {
      form.action = "exibirFiltrarImovelDadosTarifaSocialAction.do";
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
      form.idLocalidade.value = codigoRegistro;
      form.localidadeDescricao.value = descricaoRegistro;
    }

    if (tipoConsulta == 'quadra') {
      limparPesquisaQuadra();
      form.idQuadra.value = codigoRegistro;
      form.quadraDescricao.value = descricaoRegistro;
    }

    if (tipoConsulta == 'setorComercial') {
      limparPesquisaSetorComercial();
      form.idSetorComercial.value = codigoRegistro;
      form.setorComercialDescricao.value = descricaoRegistro;
    }

    if (tipoConsulta == 'cliente') {
      limparPesquisaCliente();
      form.idCliente.value = codigoRegistro;
      form.nomeCliente.value = descricaoRegistro;
    }

    if (tipoConsulta == 'bairro') {
      limparPesquisaBairro();
      form.idBairro.value = codigoRegistro;
      form.bairro.value = descricaoRegistro;
    }

    if (tipoConsulta == 'municipio') {
      limparPesquisaMunicipio();
      form.idMunicipio.value = codigoRegistro;
      form.municipio.value = descricaoRegistro;
    }
  }

function limparPesquisaLocalidade() {
    var form = document.forms[0];

      form.idLocalidade.value = "";
      form.localidadeDescricao.value = "";


  }

function limparPesquisaQuadra() {
    var form = document.forms[0];

      form.idQuadra.value = "";
      form.quadraDescricao.value = "";


  }

function limparPesquisaSetorComercial() {
    var form = document.forms[0];

      form.idSetorComercial.value = "";
      form.setorComercialDescricao.value = "";


  }

function limparPesquisaCliente() {
    var form = document.forms[0];

      form.idCliente.value = "";
      form.nomeCliente.value = "";


  }

function limparPesquisaBairro() {
    var form = document.forms[0];

      form.idBairro.value = "";
      form.bairro.value = "";


  }

function limparPesquisaMunicipio() {
    var form = document.forms[0];

      form.idMunicipio.value = "";
      form.municipio.value = "";


  }

</script>
</head>

<body leftmargin="5" topmargin="5">
<div id="formDiv">
<html:form
    action="/filtrarImovelDadosTarifaSocialAction"
    method="post"
    onsubmit="return validateFiltrarImovelActionForm(this);"
>


<%@ include file="/jsp/util/cabecalho.jsp"%>
<%@ include file="/jsp/util/menu.jsp" %>

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
      </div></td>
    <td width="625" valign="top" class="centercoltext">
      <table height="100%">
        <tr>
          <td></td>
        </tr>
      </table>
      <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_left.gif"/></td>
          <td class="parabg">Filtrar Imóvel Tarifa Social</td>
          <td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif"/></td>
        </tr>
      </table>
      <p>&nbsp;</p>
            <table width="100%" border="0">
              <tr>
                <td colspan="2">Para filtrar o(s) im&oacute;vel(is), informe dados abaixo:</td>
              </tr>
              <tr>
                <td height="24"><strong>Matrícula:</strong></td>
                <td><html:text maxlength="8" property="matricula" size="8"/></td>
              </tr>
                 <tr>
		   <td width="19%"><strong>Localidade:</strong></td>
                   <td width="81%" height="24"><html:text maxlength="3" property="idLocalidade" size="3" onkeypress="javascript:limparPesquisaQuadra();limparPesquisaSetorComercial(); validaEnter(event, 'exibirFiltrarImovelAction.do', 'idLocalidade');"/>
                      <a href="javascript:abrirPopup('exibirPesquisarLocalidadeAction.do?tipo=imovelLocalidade&indicadorUsoTodos=1', 400, 800);limparPesquisaQuadra();limparPesquisaSetorComercial();">
                         <img width="23" height="21" src="<bean:message key="caminho.imagens"/>pesquisa.gif" border="0"/>
                      </a>

   		      <logic:present name="codigoLocalidadeNaoEncontrada" scope="request">
			<input type="text" name="localidadeDescricao" size="50" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000" value="<bean:message key="pesquisa.registro.inexistente"/>"/>
                      </logic:present>

                      <logic:notPresent name="codigoLocalidadeNaoEncontrada" scope="request">
                        <html:text property="localidadeDescricao" size="50" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>
                      </logic:notPresent>

  		      <a href="javascript:limparPesquisaLocalidade();limparPesquisaSetorComercial();limparPesquisaQuadra();"> <img src="<bean:message key="caminho.imagens"/>limparcampo.gif" border="0" title="Apagar"/></a>

                   </td>
                 </tr>
                  <tr>
                   <td><strong>Setor Comercial:</strong></td>
                   <td height="24"><html:text maxlength="3" property="idSetorComercial" size="3" onkeypress="javascript:limparPesquisaQuadra();return validaEnterDependencia(event, 'exibirFiltrarImovelAction.do', this, document.forms[0].idLocalidade.value, 'Localidade');"/>
                      <a href="javascript:abrirPopupDependencia('exibirPesquisarSetorComercialAction.do?idLocalidade='+document.forms[0].idLocalidade.value+'&tipo=SetorComercial&indicadorUsoTodos=1',document.forms[0].idLocalidade.value,'Localidade', 400, 800);limparPesquisaQuadra();">
			<img width="23" height="21" src="<bean:message key="caminho.imagens"/>pesquisa.gif" border="0"/>
                      </a>
   		      <logic:present name="codigoSetorComercialNaoEncontrada" scope="request">
			<input type="text" name="setorComercialDescricao" size="50" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000" value="<bean:message key="pesquisa.registro.inexistente"/>"/>
                      </logic:present>

                      <logic:notPresent name="codigoSetorComercialNaoEncontrada" scope="request">
                        <html:text property="setorComercialDescricao" size="50" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>
                      </logic:notPresent>

			<a href="javascript:limparPesquisaSetorComercial();limparPesquisaQuadra();"> <img src="<bean:message key="caminho.imagens"/>limparcampo.gif" border="0" title="Apagar"/></a>

                   </td>
                 </tr>
                 <tr>
                   <td><strong>Quadra:</strong></td>
                   <td height="24"><html:text maxlength="4" property="idQuadra" size="3" onkeypress="javascript:return validaEnterDependencia(event, 'exibirFiltrarImovelAction.do', this, document.forms[0].idSetorComercial.value, 'Setor Comercial');"/>
                      <a href="javascript:abrirPopupDependencia('exibirPesquisarQuadraAction.do?codigoSetorComercial='+document.forms[0].idSetorComercial.value+'&tipo=Quadra', document.forms[0].idSetorComercial.value, 'Setor Comercial',400, 800);">
                       <img width="23" height="21" src="<bean:message key="caminho.imagens"/>pesquisa.gif" border="0"/>
                      </a>

   		      <logic:present name="codigoQuadraNaoEncontrada" scope="request">
			<input type="text" name="quadraDescricao" size="50" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000" value="<bean:message key="pesquisa.registro.inexistente"/>"/>
                      </logic:present>

                      <logic:notPresent name="codigoQuadraNaoEncontrada" scope="request">
                        <html:text property="quadraDescricao" size="50" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>
                      </logic:notPresent>
			<a href="javascript:limparPesquisaQuadra();"> <img src="<bean:message key="caminho.imagens"/>limparcampo.gif" border="0" title="Apagar"/></a>
                   </td>
                 </tr>
              <tr>
                <td height="24"><strong>Lote:</strong></td>
                <td><html:text maxlength="4" property="lote" size="4" /></td>
              </tr>
              <tr>
                <td height="24"><strong>Sublote:</strong></td>
                <td><html:text maxlength="3" property="subLote" size="4"/></td>
              </tr>
              <tr>
                   <td><strong>Cliente:</strong></td>
                   <td height="24"><html:text maxlength="9" property="idCliente" size="9" onkeypress="javascript:return pesquisaEnter(event, 'exibirFiltrarImovelAction.do', 'idCliente');"/>
                      <a href="javascript:abrirPopup('exibirPesquisarClienteAction.do', 400, 800);">
                       <img width="23" height="21" src="<bean:message key="caminho.imagens"/>pesquisa.gif" border="0"/>
                      </a>
                        <logic:present name="codigoClienteNaoEncontrada" scope="request">
                           <input type="text" maxlength="50" name="nomeCliente" size="50" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000" value="<bean:message key="pesquisa.registro.inexistente"/>"/>
                        </logic:present>
                        <logic:notPresent name="codigoClienteNaoEncontrada" scope="request">
                           <html:text maxlength="50" property="nomeCliente" size="50" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>
                        </logic:notPresent>
			<a href="javascript:limparPesquisaCliente();"> <img src="<bean:message key="caminho.imagens"/>limparcampo.gif" border="0" title="Apagar"/></a>
                   </td>
              </tr>
              <tr>
                <td height="24"><strong>Cep:</strong></td>
                <td><html:text maxlength="8" property="cep" size="8"/></td>
              </tr>
              <tr>
                   <td><strong>Município:</strong></td>
                   <td height="24"><html:text maxlength="4" property="idMunicipio" size="4" onkeypress="limparPesquisaBairro();javascript:return pesquisaEnter(event, 'exibirFiltrarImovelAction.do', 'idMunicipio');"/>
                      <a href="javascript:abrirPopup('exibirPesquisarMunicipioAction.do', 400, 800);">
                       <img width="23" height="21" src="<bean:message key="caminho.imagens"/>pesquisa.gif" border="0"/>
                      </a>
                        <logic:present name="codigoMunicipioNaoEncontrada" scope="request">
                           <input type="text" maxlength="50" name="municipio" size="50" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000" value="<bean:message key="pesquisa.registro.inexistente"/>"/>
                        </logic:present>
                        <logic:notPresent name="codigoMunicipioNaoEncontrada" scope="request">
                           <html:text maxlength="50" property="municipio" size="50" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>
                        </logic:notPresent>
			<a href="javascript:limparPesquisaMunicipio();limparPesquisaBairro();"> <img src="<bean:message key="caminho.imagens"/>limparcampo.gif" border="0" title="Apagar"/></a>
                   </td>
              </tr>
              <tr>
                   <td><strong>Bairro:</strong></td>
                   <td height="24"><html:text maxlength="3" property="idBairro" size="3" onkeypress="javascript:return pesquisaEnter(event, 'exibirFiltrarImovelAction.do', 'idBairro');"/>
                      <a href="javascript:abrirPopup('exibirPesquisarBairroAction.do', 400, 800);">
                       <img width="23" height="21" src="<bean:message key="caminho.imagens"/>pesquisa.gif" border="0"/>
                      </a>
                        <logic:present name="codigoBairroNaoEncontrada" scope="request">
                           <input type="text" maxlength="50" name="bairro" size="50" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000" value="<bean:message key="pesquisa.registro.inexistente"/>"/>
                        </logic:present>
                        <logic:notPresent name="codigoBairroNaoEncontrada" scope="request">
                           <html:text maxlength="50" property="bairro" size="50" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>
                        </logic:notPresent>
			<a href="javascript:limparPesquisaBairro();"> <img src="<bean:message key="caminho.imagens"/>limparcampo.gif" border="0" title="Apagar"/></a>
                   </td>
              </tr>
              <tr>
                <td height="24"><strong>Logradouro:</strong></td>
                <td><html:text maxlength="30" property="logradouro" size="30" /></td>
              </tr>
              <tr>
                <td height="24"><input type="button" class="bottonRightCol" value="Filtrar" onclick="javascript:chamarFiltrar();"/></td>
                <td ></td>
              </tr>
              <tr>
                <td height="0" colspan="2">
                  <div align="right"> </div></td>
              </tr>
            </table>
      <p>&nbsp;</p>
    </td>
  </tr>
</table>

<%@ include file="/jsp/util/rodape.jsp"%>

</html:form>
</div>
</body>

<%@ include file="/jsp/util/telaespera.jsp"%>

<script>
document.getElementById('botaoConcluir').onclick = function() { botaoAvancarTelaEspera('/gsan/filtrarImovelOutrosCriteriosWizardAction.do?concluir=true&action=validarImovelOutrosCriterios'); }
</script>

</html:html>
