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

<script language="JavaScript">
function habilitaComboAnormalidade(tipoAnormalidade){
	var form = document.LeituraConsumoActionForm;
	if(tipoAnormalidade.value == "-1"){
		form.anormalidadeLeituraInformadaFiltro.disabled = true;
		form.anormalidadeLeituraFaturadaFiltro.disabled = true;
		form.anormalidadeConsumoFiltro.disabled = true;

		form.anormalidadeLeituraInformadaFiltro.value = "-1";
		form.anormalidadeLeituraFaturadaFiltro.value = "-1";
		form.anormalidadeConsumoFiltro.value = "-1";
	}else
	if(tipoAnormalidade.value == "<%=""+ConstantesSistema.ANORMALIDADE_LEITURA_INFORMADA%>"){
		form.anormalidadeLeituraInformadaFiltro.disabled = false;
		form.anormalidadeLeituraFaturadaFiltro.disabled = true;
		form.anormalidadeConsumoFiltro.disabled = true;	
		
		form.anormalidadeLeituraFaturadaFiltro.value = "-1";
		form.anormalidadeConsumoFiltro.value = "-1";
	}else
	if(tipoAnormalidade.value == "<%=""+ConstantesSistema.ANORMALIDADE_LEITURA_FATURADA%>"){
		form.anormalidadeLeituraInformadaFiltro.disabled = true;
		form.anormalidadeLeituraFaturadaFiltro.disabled = false;
		form.anormalidadeConsumoFiltro.disabled = true;	

		form.anormalidadeLeituraInformadaFiltro.value = "-1";
		form.anormalidadeConsumoFiltro.value = "-1";
	}else
	if(tipoAnormalidade.value == "<%=""+ConstantesSistema.ANORMALIDADE_CONSUMO%>"){
		form.anormalidadeLeituraInformadaFiltro.disabled = true;
		form.anormalidadeLeituraFaturadaFiltro.disabled = true;
		form.anormalidadeConsumoFiltro.disabled = false;	

		form.anormalidadeLeituraInformadaFiltro.value = "-1";
		form.anormalidadeLeituraFaturadaFiltro.value = "-1";
	}
}
</script>

<script>
<!-- Begin

     var bCancel = false;

    function validateLeituraConsumoActionForm(form) {
        if (bCancel)
      return true;
        else
       return validateCaracterEspecial(form)
       && validateInteiroZeroPositivo(form)
       && validateRequired(form);
   }

    function caracteresespeciais () {
     this.aa = new Array("consumoFaturadoInicialFiltro", "Consumo Faturado Inicial possui caracteres especiais.", new Function ("varName", " return this[varName];"));
     this.ab = new Array("consumoFaturadoFinalFiltro", "Consumo Faturado Final possui caracteres especiais.", new Function ("varName", " return this[varName];"));
     this.ac = new Array("consumoMedidoInicialFiltro", "Consumo Medido Inicial possui caracteres especiais.", new Function ("varName", " return this[varName];"));
     this.ac = new Array("consumoMedidoFinalFiltro", "Consumo Medido Final possui caracteres especiais.", new Function ("varName", " return this[varName];"));
     this.ae = new Array("consumoMedioInicialFiltro", "Consumo Médio Inicial possui caracteres especiais.", new Function ("varName", " return this[varName];"));
     this.ae = new Array("consumoMedioFinalFiltro", "Consumo Médio Final possui caracteres especiais.", new Function ("varName", " return this[varName];"));
    }

    function InteiroZeroPositivoValidations () {
     this.am = new Array("consumoFaturadoInicialFiltro", "Consumo Faturado Inicial deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
     this.an = new Array("consumoFaturadoFinalFiltro", "Consumo Faturado Final deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
     this.ao = new Array("consumoMedidoInicialFiltro", "Consumo Medido Inicial deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
     this.ap = new Array("consumoMedidoFinalFiltro", "Consumo Medido Final deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
     this.ao = new Array("consumoMedioInicialFiltro", "Consumo Médio Inicial deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
     this.ao = new Array("consumoMedioFinalFiltro", "Consumo Médio Final deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
    }
    
    function required () {
	 this.aj = new Array("idGrupoFaturamentoFiltro", "Informe Grupo de Faturamento.", new Function ("varName", " return this[varName];"));
	}
    
    function limparForm(){
    	var form = document.forms[0];
    	
    	form.consumoFaturadoInicialFiltro.value = "";
    	form.consumoFaturadoFinalFiltro.value = "";
    	form.consumoMedidoInicialFiltro.value = "";
    	form.consumoMedidoFinalFiltro.value = "";
    	form.consumoMedioInicialFiltro.value = "";
    	form.consumoMedioFinalFiltro.value = "";
    	form.anormalidadeLeituraFaturadaFiltro.value = "-1";
    	form.anormalidadeConsumoFiltro.value = "-1";
    	form.anormalidadeLeituraInformadaFiltro.value = "-1";
    	form.leituraSituacaoAtualFiltro.value = "-1";
    	form.valorAguaEsgotoInicialFiltro.value = "";
    	form.valorAguaEsgotoFinalFiltro.value = "";
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

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="LeituraConsumoActionForm" dynamicJavascript="false" />

</head>

<body leftmargin="5" topmargin="5" onload="javascript:setarFoco('${requestScope.nomeCampo}');">


<html:form 
  name="LeituraConsumoActionForm"
  type="gcom.gui.micromedicao.LeituraConsumoActionForm"
  action="/filtrarExcecoesLeiturasConsumosWizardAction"
  method="post">
  
<jsp:include page="/jsp/util/wizard/navegacao_abas_wizard_filtro.jsp?numeroPagina=3"/>


<%@ include file="/jsp/util/cabecalho.jsp"%>
<%@ include file="/jsp/util/menu.jsp" %>

<table width="770" border="0" cellspacing="5" cellpadding="0">
	
  <tr>
    <td width="130" valign="top" class="leftcoltext">
    
    	<input type="hidden" name="numeroPagina" value="3"/>
      
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
			<td align="right"><a href="javascript:abrirPopupHelp('${applicationScope.urlHelp}faturamentoLeituraConsumoExcecoesFiltrarAbaLigacoesConsumo', 500, 700);"><span style="font-weight: bold"><font color="#3165CE">Ajuda</font></span></a></td>									
</logic:present>
<logic:notPresent scope="application" name="urlHelp">
			<td align="right"><span style="font-weight: bold"><font color=#696969><u>Ajuda</u></font></span></td>									
</logic:notPresent>
</tr>
</table>
      <table width="100%" border="0" >
        <tr>
            <td><strong>Anormalidade de Leitura Informada:</strong></td>
            <td><html:select property="anormalidadeLeituraInformadaFiltro" style="width: 250px;" 
										multiple="mutiple" size="4">
			 		<html:option value="-1">&nbsp;</html:option>
                	<html:options collection="leituraAnormalidades" labelProperty="descricao" property="id"/>
                </html:select>
            </td>
        </tr>
        <tr>
            <td><strong>Anormalidade de Leitura Faturada:</strong></td>
            <td><html:select property="anormalidadeLeituraFaturadaFiltro" style="width: 250px;" 
										multiple="mutiple" size="4">
			 		<html:option value="-1">&nbsp;</html:option>
                	<html:options collection="leituraAnormalidades" labelProperty="descricao" property="id"/>
                </html:select>
            </td>
        </tr>
        <tr>
            <td><strong>Anormalidade de Consumo:</strong></td>
            <td><html:select property="anormalidadeConsumoFiltro" style="width: 250px;" 
										multiple="mutiple" size="4">
			 		<html:option value="-1">&nbsp;</html:option>
                	<html:options collection="consumoAnormalidades" labelProperty="descricao" property="id"/>
                </html:select>
            </td>
        </tr>
        <tr>
            <td><strong>Consumo Faturado:</strong></td>
            <td><html:text maxlength="6" property="consumoFaturadoInicialFiltro" size="6" tabindex="8"
			            onkeypress="return isCampoNumerico(event);"/> a 
            	<html:text maxlength="6" property="consumoFaturadoFinalFiltro" size="6" tabindex="8"
		            	onkeypress="return isCampoNumerico(event);"/>
            </td>
        </tr>
        <tr>
            <td><strong>Consumo Medido:</strong></td>
            <td><html:text maxlength="6" property="consumoMedidoInicialFiltro" size="6" tabindex="8"
			            onkeypress="return isCampoNumerico(event);"/> a 
            	<html:text maxlength="6" property="consumoMedidoFinalFiltro" size="6" tabindex="8"
			            	onkeypress="return isCampoNumerico(event);"/>
            </td>
        </tr>
        <tr>
            <td><strong>Consumo Médio:</strong></td>
            <td><html:text maxlength="6" property="consumoMedioInicialFiltro" size="6" tabindex="8"
		            onkeypress="return isCampoNumerico(event);"/> a 
	            <html:text maxlength="6" 
			            property="consumoMedioFinalFiltro"
			            size="6" 
			            tabindex="8"
			            onkeypress="return isCampoNumerico(event);"/>
            </td>
        </tr>
        
        <tr>
            <td><strong>Valor (água+esgoto):</strong></td>
            <td><html:text maxlength="6" property="valorAguaEsgotoInicialFiltro" size="6" tabindex="8"
		            onkeypress="return isCampoNumerico(event);"/> a 
	            <html:text maxlength="6" 
			            property="valorAguaEsgotoFinalFiltro"
			            size="6" 
			            tabindex="8"
			            onkeypress="return isCampoNumerico(event);"/>
            </td>
        </tr>
        
        <tr>
            <td><strong>Situação da leitura atual:</strong></td>
            <td><html:select property="leituraSituacaoAtualFiltro">
			 		<html:option value="-1">&nbsp;</html:option>
                	<html:options collection="colecaoLeituraSituacaoAtual" labelProperty="descricao" property="id"/>
                </html:select>
            </td>
        </tr>        
	        <tr>
                <td colspan="2">
                	<div align="right">
                		<jsp:include page="/jsp/util/wizard/navegacao_botoes_wizard_filtro.jsp?numeroPagina=3"/>
                	</div>
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