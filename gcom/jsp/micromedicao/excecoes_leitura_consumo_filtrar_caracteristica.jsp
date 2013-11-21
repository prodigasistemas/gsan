<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<%@ page import="gcom.util.ConstantesSistema" isELIgnored="false"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">

<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js" ></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="LeituraConsumoActionForm" dynamicJavascript="false" />

<script>
<!-- Begin

     var bCancel = false;

    function validateLeituraConsumoActionForm(form) {
        if (bCancel)
      return true;
        else
       return testarCampoValorZero(document.LeituraConsumoActionForm.quantidadeEconomiaFiltro, 'Quantidade de Economias') 
       && validateCaracterEspecial(form)
       && validateLong(form)
       && validateRequired(form);
   }

    function caracteresespeciais () {
     this.aa = new Array("quantidadeEconomiaFiltro", "Quantidade de Economias possui caracteres especiais.", new Function ("varName", " return this[varName];"));
    }

    function IntegerValidations () {
     this.ab = new Array("quantidadeEconomiaFiltro", "Quantidade de Economias deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
    }
    
    function required () {
	 this.aj = new Array("idGrupoFaturamentoFiltro", "Informe Grupo de Faturamento.", new Function ("varName", " return this[varName];"));
	}

	function limparForm(){
		var form = document.forms[0];
		
		form.quantidadeEconomiaFiltro.value = "";
		form.perfilImovelFiltro.value = "-1";
		form.categoriaImovelFiltro.value = "-1";
		form.tipoMedicaoFiltro.value = "-1";
		form.tipoLigacaoFiltro.value = "-1";
		
	}
//End -->
</script>


<SCRIPT LANGUAGE="JavaScript">
<!--
function validarForm(form){
	// Confirma a validação do formulário
	if (validateLeituraConsumoActionForm(form)){
		redirecionarSubmit("/gsan/filtrarExcecoesLeiturasConsumosAction.do?nomeCaminhoMapping=efetuarAnaliseExcecoesLeiturasConsumos");
	}

}
//-->
</SCRIPT>

</head>

<body leftmargin="5" topmargin="5" onload="javascript:setarFoco('${requestScope.nomeCampo}');">


<html:form 
  name="LeituraConsumoActionForm"
  type="gcom.gui.micromedicao.LeituraConsumoActionForm"
  action="/filtrarExcecoesLeiturasConsumosWizardAction"
  method="post">
  
<jsp:include page="/jsp/util/wizard/navegacao_abas_wizard_filtro.jsp?numeroPagina=2"/>

<%@ include file="/jsp/util/cabecalho.jsp"%>
<%@ include file="/jsp/util/menu.jsp" %>

<table width="770" border="0" cellspacing="5" cellpadding="0">
	<input type="hidden" name="numeroPagina" value="2"/>
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

      <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_left.gif"/></td>
          <td class="parabg">Filtrar Exceções de Leituras e Consumos</td>
          <td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif"/></td>
        </tr>
      </table>
      <p>&nbsp;</p>
      <html:hidden property="idGrupoFaturamentoFiltro"/>
	<table width="100%" border="0" >
<tr>
<logic:present scope="application" name="urlHelp">
			<td align="right"><a href="javascript:abrirPopupHelp('${applicationScope.urlHelp}faturamentoLeituraConsumoExcecoesFiltrarAbaCaracteristica', 500, 700);"><span style="font-weight: bold"><font color="#3165CE">Ajuda</font></span></a></td>									
</logic:present>
<logic:notPresent scope="application" name="urlHelp">
			<td align="right"><span style="font-weight: bold"><font color=#696969><u>Ajuda</u></font></span></td>									
</logic:notPresent>
</tr>
</table>
      <table width="100%" border="0" >
        <tr>
            <td width="30%"><strong>Perfil do Imóvel:</strong></td>
            <td><html:select style="width: 230px;" multiple="mutiple" size="5" property="perfilImovelFiltro">
			 		<html:option value="-1">&nbsp;</html:option>
                	<html:options collection="imovelPerfis" labelProperty="descricao" property="id"/>
                </html:select>
            </td>
        </tr>
        <tr>
            <td><strong>Categoria do Imóvel:</strong></td>
            <td><html:select property="categoriaImovelFiltro">
			 		<html:option value="-1">&nbsp;</html:option>
                	<html:options collection="categorias" labelProperty="descricao" property="id"/>
                </html:select>
            </td>
        </tr>
        <tr>
            <td><strong>Quantidade de Economias:</strong></td>
            <td><html:text maxlength="4" 
		            	property="quantidadeEconomiaFiltro" 
		            	size="4" 
		            	tabindex="1"
		            	onkeypress="javascript:return isCampoNumerico(event);"/>
		    </td>
        </tr>
        <tr>
            <td><strong>Tipo de Medição:</strong></td>
            <td><html:select property="tipoMedicaoFiltro">
			 		<html:option value="-1">&nbsp;</html:option>
                	<html:options collection="medicaoTipos" labelProperty="descricao" property="id"/>
                </html:select>
            </td>
        </tr>
        <tr>
            <td><strong>Tipo de Ligação:</strong></td>
            <td><html:select property="tipoLigacaoFiltro">
			 		<html:option value="-1">&nbsp;</html:option>
                	<html:options collection="ligacaoTipos" labelProperty="descricao" property="id"/>
                </html:select>
            </td>
        </tr>
        	<tr>
                <td colspan="2"><jsp:include page="/jsp/util/wizard/navegacao_botoes_wizard_filtro.jsp?numeroPagina=2"/></td>
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