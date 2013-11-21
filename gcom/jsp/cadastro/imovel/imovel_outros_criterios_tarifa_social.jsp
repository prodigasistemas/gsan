<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<%@ page import="gcom.util.ConstantesSistema" %>
<%@ page import="gcom.util.Pagina" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="ImovelOutrosCriteriosActionForm"
	dynamicJavascript="false" />
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js" ></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js" ></script>
<script language="JavaScript">
<!-- Begin 
     var bCancel = false; 

    function validateImovelOutrosCriteriosActionForm(form) {                                                                   
        if (bCancel) 
      return true; 
        else 
       return validateCaracterEspecial(form) && validateInteger(form) && validateDate(form)
                && validateDecimal(form)
				&& validarPeriodoImplantacao()
				&& validarPeriodoExclusao()
				&& validarPeriodoValidadeCartaoProgramaSocial()
				&& validarPeriodoRecadastramento()       
				&& validarIntervaloNumeroMesesAdesao()
				&& validarIntervaloRendaFamiliar()
				&& validarIntervaloConsumoCelpe()
				&& validarIntervaloNumeroRecadastramento();
   } 

    function caracteresespeciais () { 
     this.aa = new Array("mesInicioAdesao", "Intervalo de Número de Meses de Adesão Inicial deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
     this.ab = new Array("mesFimAdesao", "Intervalo de Número de Meses de Adesão Final deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
     this.ac = new Array("rendaInicial", "Intervalo de Renda Familiar Inicial deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
     this.ad = new Array("rendaFinal", "Intervalo de Renda Familiar Final deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
     this.ae = new Array("celpeInicial", "Intervalo de Consumo Companhia de Energia Inicial deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
     this.af = new Array("celpeFinal", "Intervalo de Consumo Companhia de Energia Inicial deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
     this.ag = new Array("recadastramentoNumeroInicial", "Intervalo de Número de Recadastramentos Inicial deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
     this.ah = new Array("recadastramentoNumeroFinal", "Intervalo de Número de Recadastramentos Final deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
    } 

    function IntegerValidations () {
     this.ai = new Array("mesInicioAdesao", "Intervalo de Número de Meses de Adesão Inicial deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
     this.aj = new Array("mesFimAdesao", "Intervalo de Número de Meses de Adesão Final deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
     this.am = new Array("celpeInicial", "Intervalo de Consumo Companhia de Energia Inicial deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
     this.an = new Array("celpeFinal", "Intervalo de Consumo Companhia de Energia Inicial deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
     this.ao = new Array("recadastramentoNumeroInicial", "Intervalo de Número de Recadastramentos Inicial deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
     this.ap = new Array("recadastramentoNumeroFinal", "Intervalo de Número de Recadastramentos Final deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
    } 

    function DateValidations () { 
     this.aq = new Array("dataInicioImplantacao", "Período de Implantação Inicial inválida.", new Function ("varName", "this.datePattern='dd/MM/yyyy';  return this[varName];"));
     this.ar = new Array("dataFimImplantacao", "Período de Implantação Final inválida.", new Function ("varName", "this.datePattern='dd/MM/yyyy';  return this[varName];"));
     this.as = new Array("dataFimValidadeCartao", "Período de Validade do Cartão do Programa Social Inicial inválida.", new Function ("varName", "this.datePattern='dd/MM/yyyy';  return this[varName];"));
     this.at = new Array("dataInicioValidadeCartao", "Período de Validade do Cartão do Programa Social Final inválida.", new Function ("varName", "this.datePattern='dd/MM/yyyy';  return this[varName];"));
     this.au = new Array("dataInicioRecadastramento", "Período de Recadastramento Inicial inválida.", new Function ("varName", "this.datePattern='dd/MM/yyyy';  return this[varName];"));
     this.av = new Array("dataFimRecadastramento", "Período de Recadastramento Final inválida.", new Function ("varName", "this.datePattern='dd/MM/yyyy';  return this[varName];"));
     this.ax = new Array("dataInicioExclusao", "Período da Exclusão Inicial inválida.", new Function ("varName", "this.datePattern='dd/MM/yyyy';  return this[varName];"));
     this.aw = new Array("dataFimExclusao", "Período da Exclusão Final inválida.", new Function ("varName", "this.datePattern='dd/MM/yyyy';  return this[varName];"));
    } 
    
    function FloatValidations () {
     this.az = new Array("rendaInicial", "Intervalo de Renda Familiar Inicial deve somente conter números decimais positivos.", new Function ("varName", " return this[varName];"));
     this.ba = new Array("rendaFinal", "Intervalo de Renda Familiar Final deve somente conter números decimais positivos.", new Function ("varName", " return this[varName];"));     
	}    
    
function validarPeriodoImplantacao(){
	if (comparaData(document.ImovelOutrosCriteriosActionForm.dataInicioImplantacao.value, ">",
	 document.ImovelOutrosCriteriosActionForm.dataFimImplantacao.value )){
		alert('Período de Implantação Inicial maior que o Período de Implantação Final');
		return false;
	}
	return true;
}    

function validarPeriodoExclusao(){
	if (comparaData(document.ImovelOutrosCriteriosActionForm.dataInicioExclusao.value, ">",
	 document.ImovelOutrosCriteriosActionForm.dataFimExclusao.value )){
		alert('Período da Exclusão Inicial maior que o Período da Exclusão Final');
		return false;
	}
	return true;
}    

function validarPeriodoValidadeCartaoProgramaSocial(){
	if (comparaData(document.ImovelOutrosCriteriosActionForm.dataInicioValidadeCartao.value, ">",
	 document.ImovelOutrosCriteriosActionForm.dataInicioRecadastramento.value )){
		alert('Período de Validade do Cartão do Programa Social Inicial maior que o Período de Validade do Cartão do Programa Social Final');
		return false;
	}
	return true;
}   

function validarPeriodoRecadastramento(){
	if (comparaData(document.ImovelOutrosCriteriosActionForm.dataInicioRecadastramento.value, ">",
	 document.ImovelOutrosCriteriosActionForm.dataFimRecadastramento.value )){
		alert('Período de Recadastramento Incial maior que o Período de Recadastramento Final');
		return false;
	}
	return true;
}   

function validarIntervaloNumeroMesesAdesao(){
	if(document.ImovelOutrosCriteriosActionForm.mesInicioAdesao.value <
	   document.ImovelOutrosCriteriosActionForm.mesFimAdesao.value){
		alert('Intervalo de Número de Meses de Adesão Inicial maior que o Intervalo de Número de Meses de Adesão Final');
		return false;			
	}
	return true;
}

function validarIntervaloRendaFamiliar(){
	if(document.ImovelOutrosCriteriosActionForm.rendaInicial.value <
	   document.ImovelOutrosCriteriosActionForm.rendaFinal.value){
		alert('Intervalo de Renda Familiar Inicial maior que o Intervalo de Renda Familiar Final');
		return false;			
	}
	return true;
}

function validarIntervaloConsumoCelpe(){
	if(document.ImovelOutrosCriteriosActionForm.celpeInicial.value <
	   document.ImovelOutrosCriteriosActionForm.celpeFinal.value){
		alert('Intervalo de Consumo Companhia de Energia Inicial maior que o Intervalo de Consumo Companhia de Energia Final');
		return false;			
	}
	return true;
}

function validarIntervaloNumeroRecadastramento(){
	if(document.ImovelOutrosCriteriosActionForm.recadastramentoNumeroInicial.value <
	   document.ImovelOutrosCriteriosActionForm.recadastramentoNumeroFinal.value){
		alert('Intervalo de Número de Recadastramentos Inicial maior que o Intervalo de Número de Recadastramentos Final');
		return false;			
	}
	return true;
}

    
function limparPeriodoImplantacao(){
	var form = document.ImovelOutrosCriteriosActionForm;
	if(form.dataInicioImplantacao.value == "")
		form.dataFimImplantacao.value = "";
}

function limparValidadeCartao(){
	var form = document.ImovelOutrosCriteriosActionForm;
	if(form.dataInicioValidadeCartao.value == "")
		form.dataFimValidadeCartao.value = "";
}

function limparPeriodoRecadastramento(){
	var form = document.ImovelOutrosCriteriosActionForm;
	if(form.dataInicioRecadastramento.value == "")
		form.dataFimRecadastramento.value = "";
}

function periodoImplantacao(){
		var form = document.ImovelOutrosCriteriosActionForm;
		form.dataFimImplantacao.value = form.dataInicioImplantacao.value;
	}
	
	function periodoExclusao(){
		var form = document.ImovelOutrosCriteriosActionForm;
		form.dataFimExclusao.value = form.dataInicioExclusao.value;
	}
	
	function mesesAdesao(){
		var form = document.ImovelOutrosCriteriosActionForm;
		form.mesFimAdesao.value = form.mesInicioAdesao.value;
	}
	
	function limparMesesadesao(){
		var form = document.ImovelOutrosCriteriosActionForm;
		form.mesInicioAdesao.value = "";
	}
	
	function validadeCartao(){
		var form = document.ImovelOutrosCriteriosActionForm;
		form.dataFimValidadeCartao.value = form.dataInicioValidadeCartao.value;
	}
	
	function validadePeriodoRecadastramento(){
		var form = document.ImovelOutrosCriteriosActionForm;
		form.dataFimRecadastramento.value = form.dataInicioRecadastramento.value;
	}
	
	function rendaFamiliar(){
		var form = document.ImovelOutrosCriteriosActionForm;
		
		form.rendaFinal.value = form.rendaInicial.value;
	}
	
	function consumoCelpe(){
		var form = document.ImovelOutrosCriteriosActionForm;
		
		form.celpeFinal.value = form.celpeInicial.value;
	}
	
	function numeroRecadastramento(){
		var form = document.ImovelOutrosCriteriosActionForm;
		
		form.recadastramentoNumeroFinal.value = form.recadastramentoNumeroInicial.value;
	}
	
-->
</script>

</head>

<body leftmargin="5" topmargin="5">
<div id="formDiv">
<html:form
    action="/filtrarImovelOutrosCriteriosWizardAction"
    method="post"
  	type="gcom.gui.cadastro.imovel.ImovelOutrosCriteriosActionForm"
  	name="ImovelOutrosCriteriosActionForm"

>

<jsp:include page="/jsp/util/wizard/navegacao_abas_wizard_valida_avancar_valida_voltar.jsp?numeroPagina=6"/>



<%@ include file="/jsp/util/cabecalho.jsp"%>
<%@ include file="/jsp/util/menu.jsp" %>

<table width="770" border="0" cellspacing="5" cellpadding="0">
<input type="hidden" name="numeroPagina" value="6"/>
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
          <td class="parabg">Filtrar Imóvel</td>
          <td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif"/></td>
        </tr>
      </table>
      <p>&nbsp;</p>
            <table width="100%" border="0" dwcopytype="CopyTableRow">
              <tr>
                <td width="18%" height="31">
                <strong>Indicador de Situação do Imóvel na Tarifa Social:</strong></td>
                <td width="82%">
                	<html:radio  property="indicadorImovelTarifaSocial" value="1"/><strong>Ativo</strong>
                	<html:radio  property="indicadorImovelTarifaSocial" value="2"/><strong>Excluído</strong>
                	<html:radio  property="indicadorImovelTarifaSocial" value="3"/><strong>Ativo ou Excluído</strong>
                </td>
              </tr>
              <tr>
                <td height="24" colspan="2"><hr></td>
              </tr>
              <tr>
                <td height="24"><strong>Período de Implantação:</strong></td>
                <td><html:text property="dataInicioImplantacao" size="10" maxlength="10" onkeypress="limparPeriodoImplantacao();"  onkeyup="mascaraData(this, event);periodoImplantacao();"
                	onfocus="periodoImplantacao();"/>
                  <a href="javascript:abrirCalendario('ImovelOutrosCriteriosActionForm', 'dataInicioImplantacao');document.ImovelOutrosCriteriosActionForm.dataInicioImplantacao.focus();">
                    <img border="0" src="<bean:message key="caminho.imagens"/>calendario.gif" width="20" border="0" align="absmiddle" alt="Exibir Calendário"/></a>
                  a
                  <html:text property="dataFimImplantacao" size="10" maxlength="10" onkeyup="mascaraData(this, event);"/>
                  <a href="javascript:abrirCalendario('ImovelOutrosCriteriosActionForm', 'dataFimImplantacao');">
                    <img border="0" src="<bean:message key="caminho.imagens"/>calendario.gif" width="20" border="0" align="absmiddle" alt="Exibir Calendário"/></a>
                  <font size="1">&nbsp; dd/mm/aaaa </font></td>
              </tr>
              <tr>
                <td height="24"><strong>Período da Exclusão:</strong></td>
                <td><html:text property="dataInicioExclusao" size="10" maxlength="10" onkeypress="limparPeriodoExclusao();" onkeyup="mascaraData(this, event);periodoExclusao();"
                	onfocus="periodoExclusao();"/>
                  <a href="javascript:abrirCalendario('ImovelOutrosCriteriosActionForm', 'dataInicioExclusao');document.ImovelOutrosCriteriosActionForm.dataInicioExclusao.focus();">
                    <img border="0" src="<bean:message key="caminho.imagens"/>calendario.gif" width="20" border="0" align="absmiddle" alt="Exibir Calendário"/></a>
                  a
                  <html:text property="dataFimExclusao" size="10" maxlength="10" onkeyup="mascaraData(this, event);"/>
                  <a href="javascript:abrirCalendario('ImovelOutrosCriteriosActionForm', 'dataFimExclusao')">
                    <img border="0" src="<bean:message key="caminho.imagens"/>calendario.gif" width="20" border="0" align="absmiddle" alt="Exibir Calendário"/></a>
                  <font size="1">&nbsp; dd/mm/aaaa </font></td>
              </tr>
              <tr>
                <td height="24"><strong>Motivo de Exclusão:</strong></td>
                <td>
					<html:select property="idMotivoExclusao">
						<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
	              		<html:options collection="colecaoExclusaoMotivo" labelProperty="descricao" property="id"/>
        	        </html:select>
				</td>
              </tr>
              <tr>
                <td height="24"><strong>Período de Validade do Cartão do Programa Social:</strong></td>
                <td><html:text property="dataInicioValidadeCartao" size="10" maxlength="10" onkeypress="limparValidadeCartao();" onkeyup="mascaraData(this, event);validadeCartao();"
                	onfocus="validadeCartao();"/>
                  <a href="javascript:abrirCalendario('ImovelOutrosCriteriosActionForm', 'dataInicioValidadeCartao');document.ImovelOutrosCriteriosActionForm.dataInicioValidadeCartao.focus();">
                    <img border="0" src="<bean:message key="caminho.imagens"/>calendario.gif" width="20" border="0" align="absmiddle" alt="Exibir Calendário"/></a>
                  a
                  <html:text property="dataFimValidadeCartao" size="10" maxlength="10" onkeypress="mascaraData(this, event);" />
                  <a href="javascript:abrirCalendario('ImovelOutrosCriteriosActionForm', 'dataFimValidadeCartao')">
                    <img border="0" src="<bean:message key="caminho.imagens"/>calendario.gif" width="20" border="0" align="absmiddle" alt="Exibir Calendário"/></a>
                  <font size="1">&nbsp; dd/mm/aaaa </font></td>
              </tr>
              
              <tr>
                <td height="24"><strong>Intervalo de Número de Meses de Adesão:</strong></td>
                <td>
                	<html:text property="mesInicioAdesao" size="2" maxlength="2" onkeypress="" onkeyup="mesesAdesao();"/>
                    a
                  	<html:text property="mesFimAdesao" size="2" maxlength="2"/>
                </td>
              </tr>              
              <tr>
                <td height="24"><strong>Tipo do Cartão:</strong></td>
                <td>
					<html:select property="tarifaSocialCartaoTipoId">
						<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
              			<html:options collection="colecaoCartaoTipo" labelProperty="descricaoAbreviada" property="id"/>
	                </html:select>
				</td>
              </tr>
              <tr>
                <td height="24"><strong>Tipo de Renda:</strong></td>
                <td>
					<html:select property="tarifaSocialRendaTipoId">
						<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
              			<html:options collection="colecaoRendaTipo" labelProperty="descricao" property="id"/>
	                </html:select>
				</td>
              </tr>
              <tr>
                <td height="24"><strong>Intervalo de Renda Familiar:</strong></td>
                <td><html:text property="rendaInicial" size="6" maxlength="6" onkeyup="rendaFamiliar();formataValorMonetario(this, 6);"/>
                  a
                  <html:text property="rendaFinal" size="7" maxlength="7"/>
              </tr>
              <tr>
                <td height="24"><strong>Intervalo de Consumo Companhia de Energia:</strong></td>
                <td><html:text property="celpeInicial" size="5" maxlength="5" onkeyup="consumoCelpe();"/>
                  a
                  <html:text property="celpeFinal" size="5" maxlength="5"/>
              </tr>
              <tr>
                <td height="24"><strong>Período de Recadastramento:</strong></td>
                <td><html:text property="dataInicioRecadastramento" size="10" maxlength="10" onkeypress="limparPeriodoRecadastramento();" onkeyup="mascaraData(this, event);validadePeriodoRecadastramento();"
                	onfocus="validadePeriodoRecadastramento();"/>
                  <a href="javascript:abrirCalendario('ImovelOutrosCriteriosActionForm', 'dataInicioRecadastramento');document.ImovelOutrosCriteriosActionForm.dataInicioRecadastramento.focus();">
                    <img border="0" src="<bean:message key="caminho.imagens"/>calendario.gif" width="20" border="0" align="absmiddle" alt="Exibir Calendário"/></a>
                  a
                  <html:text property="dataFimRecadastramento" size="10" maxlength="10" onkeypress="mascaraData(this, event);"/>
                  <a href="javascript:abrirCalendario('ImovelOutrosCriteriosActionForm', 'dataFimRecadastramento')">
                    <img border="0" src="<bean:message key="caminho.imagens"/>calendario.gif" width="20" border="0" align="absmiddle" alt="Exibir Calendário"/></a><font size="1">&nbsp; dd/mm/aaaa </font></td>
              </tr>
              <tr>
                <td height="24"><strong>Intervalo de Número de Recadastramentos:</strong></td>
                <td><html:text property="recadastramentoNumeroInicial" size="2" maxlength="2" onkeyup="numeroRecadastramento();"/>
                  	a
                  	<html:text property="recadastramentoNumeroFinal" size="2" maxlength="2"/>
              </tr>
              <tr>
                <td colspan="2"><div align="right"><jsp:include page="/jsp/util/wizard/navegacao_botoes_wizard_valida_avancar_valida_voltar.jsp?numeroPagina=6"/></div></td>
              </tr>

            </table>
      <p>&nbsp;</p>
    </td>
  </tr>
</table>

<%@ include file="/jsp/util/rodape.jsp"%>
<%@ include file="/jsp/util/tooltip.jsp" %>
</html:form>
</div>
</body>

<%@ include file="/jsp/util/telaespera.jsp"%>

<script>
document.getElementById('botaoConcluir').onclick = function() { botaoAvancarTelaEspera('/gsan/filtrarImovelOutrosCriteriosWizardAction.do?concluir=true&action=validarImovelOutrosCriterios'); }
</script>
</html:html>
