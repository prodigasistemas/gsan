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
    var retorno = true;
    	if(form.valorDebitoInicial.value.length > 0){
	
		  if(form.valorDebitoFinal.value.length < 1){
		    alert("Informe Valor do Débito Final");
		    form.valorDebitoFinal.focus();
		    return false;
		  }                                                              
        } 
        
       if(form.qtdContasInicial.value.length > 0){
	
		  if(form.qtdContasFinal.value.length < 1){
		    alert("Informe Quantidade de Contas Final");
		    form.qtdContasFinal.focus();
		    return false;
		  }                                                              
        } 
        
       if(form.referenciaFaturaInicial.value.length > 0){
	
		  if(form.referenciaFaturaFinal.value.length < 1){
		    alert("Informe Referência da Fatura Final");
		    form.referenciaFaturaFinal.focus();
		    return false;
		  }                                                              
        } 
        
      if(form.vencimentoInicial.value.length > 0){
	
		  if(form.vencimentoFinal.value.length < 1){
		    alert("Informe Vencimento Final");
		    form.vencimentoFinal.focus();
		    return false;
		  }                                                              
        } 
        return retorno;
    }

   function FloatValidations () {
     this.az = new Array("rendaInicial", "Intervalo de Renda Familiar Inicial deve somente conter números decimais positivos.", new Function ("varName", " return this[varName];"));
     this.ba = new Array("rendaFinal", "Intervalo de Renda Familiar Final deve somente conter números decimais positivos.", new Function ("varName", " return this[varName];"));     
	}     	
	
	function replicarValor(){
	    var form = document.ImovelOutrosCriteriosActionForm;
	    form.valorDebitoFinal.value = form.valorDebitoInicial.value;
	}
	
	function replicarQtdConta(){
	    var form = document.ImovelOutrosCriteriosActionForm;
	    form.qtdContasFinal.value = form.qtdContasInicial.value;
	}
	
	function replicarReferencia(){
	    var form = document.ImovelOutrosCriteriosActionForm;
	    form.referenciaFaturaFinal.value = form.referenciaFaturaInicial.value;
	}
	
    function replicarVencimento(){
	    var form = document.ImovelOutrosCriteriosActionForm;
	    form.vencimentoFinal.value = form.vencimentoInicial.value;
	}
	
	function verificarCheck(){
	  var form = document.ImovelOutrosCriteriosActionForm; 
	  if(form.todosTipoDebito.checked == true){
	 	marcarTodos();
	    desabilitarTodosTipoDebito();
	  }else{
	  	desmarcarTodos();
		habilitarTodosTipoDebito();
	  }
	}

  function desabilitarTodosTipoDebito(){

	for (var i=0;i < document.forms[0].tipoDebito.length;i++){
		var elemento = document.forms[0].tipoDebito[i];
		if (elemento.type == "checkbox"){
			elemento.disabled = true;
		}
	}
}

function habilitarTodosTipoDebito() {
	for (var i=0;i < document.forms[0].tipoDebito.length;i++){
		var elemento = document.forms[0].tipoDebito[i];
		if (elemento.type == "checkbox"){
			elemento.disabled = false;
		}
	}
}
-->
</script>

</head>

<body leftmargin="5" topmargin="5" onload="javascript:verificarCheck();">
<div id="formDiv">
<html:form
    action="/filtrarImovelOutrosCriteriosWizardAction"
	type="gcom.gui.cadastro.imovel.ImovelOutrosCriteriosActionForm"
	onsubmit="return validateImovelOutrosCriteriosActionForm(this);"
	name="ImovelOutrosCriteriosActionForm" method="post">

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
				<td colspan="3">Para filtrar o(s) im&oacute;vel(is) pelos
					dados de cobrança, informe os dados abaixo:</td>
			  </tr>
              <tr>
                <td colspan="3" height="24">
                <strong>Tipo de Débito:</strong></td>
              </tr>
              <tr>
                <td>
                	<html:checkbox  property="todosTipoDebito" value="1"
                	onclick="javascript:verificarCheck();"/>Todos               	                	
                </td>
                <td>
                  <html:multibox  property="tipoDebito" value="1"/>Débito a cobrar
                </td>
                <td>
                  <html:multibox  property="tipoDebito" value="2"/>Crédito a Realizar
                </td>                
			  </tr>
			  <tr>
                <td>
                  <html:multibox  property="tipoDebito" value="3"/>Conta              	
                </td>
                <td colspan="2">
                  <html:multibox  property="tipoDebito" value="4"/>Acréscimo                                  	
                </td>
			  </tr>
			  <tr>
                <td>
                	<html:multibox  property="tipoDebito" value="5"/>Guia de Pagamento                	
                </td>
                <td colspan="2">
					<html:multibox  property="tipoDebito" value="6"/>Parcelamento Fora da Conta                	                
                </td>
			  </tr>
			  <tr>
                <td height="24" colspan="2"></td>
              </tr>
             <!--<tr>
                <td height="24" colspan="2"><hr></td>
              </tr>-->
              
              <!--  Indicador Usado na Relação de Debitos -->
              <logic:present name="parametroGerarRelatorio">
              	<logic:equal name="parametroGerarRelatorio" value="GerarRelacaoDebito">
              		
	               <tr>
						<td><strong>Gerar Código de Barras?</strong></td>
						<td><html:radio property="indicadorCodigoBarra" tabindex="11" value="1" /><strong>Sim</strong>
						<html:radio property="indicadorCodigoBarra" tabindex="12" value="2" /><strong>Não</strong>
						</td>
					</tr>

              	</logic:equal>
              </logic:present>
				
              <tr>
                <td height="24"><strong>Valor do Débito:</strong></td>
                <td colspan="2"><html:text property="valorDebitoInicial" size="17" maxlength="17" onkeyup="formataValorMonetario(this, 13);replicarValor();"/>
                  a
                  <html:text property="valorDebitoFinal" size="17" maxlength="17"/>
              </tr>
              <tr>
                <td height="24"><strong>Quantidade de Contas:</strong></td>
                <td colspan="2"><html:text property="qtdContasInicial" size="4" maxlength="4" onkeyup="replicarQtdConta();"/>
                  a
                  <html:text property="qtdContasFinal" size="4" maxlength="4"/>
              </tr>   
               <tr>
                <td height="24"><strong>Referência da Fatura:</strong></td>
                <td colspan="2">
                <html:text property="referenciaFaturaInicial" size="7"  maxlength="7" onkeypress="javascript:mascaraAnoMes(this, event);" onkeyup="replicarReferencia();"/>
                  a
                  <html:text property="referenciaFaturaFinal" size="7" maxlength="7" onkeypress="javascript:mascaraAnoMes(this, event);"/> <font size="1">&nbsp; (mm/aaaa) </font></td>
              </tr> 
              <tr>
                <td height="24"><strong>Vencimento:</strong></td>
                <td colspan="2"><html:text property="vencimentoInicial" size="10" maxlength="10" onkeypress="limparValidadeCartao();" onkeyup="mascaraData(this, event);replicarVencimento();"
                	onfocus="replicarVencimento();"/>
                  <a href="javascript:abrirCalendario('ImovelOutrosCriteriosActionForm', 'vencimentoInicial');document.ImovelOutrosCriteriosActionForm.vencimentoInicial.focus();">
                    <img border="0" src="<bean:message key="caminho.imagens"/>calendario.gif" width="20" border="0" align="absmiddle" alt="Exibir Calendário"/></a>
                  a
                  <html:text property="vencimentoFinal" size="10" maxlength="10" onkeypress="mascaraData(this, event);" />
                  <a href="javascript:abrirCalendario('ImovelOutrosCriteriosActionForm', 'vencimentoFinal')">
                    <img border="0" src="<bean:message key="caminho.imagens"/>calendario.gif" width="20" border="0" align="absmiddle" alt="Exibir Calendário"/></a>
                  <font size="1">&nbsp; dd/mm/aaaa </font></td>
              </tr>       
              <tr>
                <td height="24"><strong>Quantidade de Imóveis:</strong></td>
                <td colspan="2"><html:text property="qtdImoveis" size="6" maxlength="4" onkeyup=""/>
              </tr> 
              <tr>
                <td height="24"><strong>Quantidade dos Maiores:</strong></td>
                <td colspan="2"><html:text property="qtdMaiores" size="6" maxlength="4" onkeyup=""/>
              </tr>    
              <tr>
                <td colspan="3"><div align="right"><jsp:include page="/jsp/util/wizard/navegacao_botoes_wizard_valida_avancar_valida_voltar.jsp?numeroPagina=6"/></div></td>
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
