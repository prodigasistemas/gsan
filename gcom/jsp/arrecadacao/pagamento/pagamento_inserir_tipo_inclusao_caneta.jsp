<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/fmt.tld" prefix="fmt"%>
<fmt:bundle basename="gcom.properties.application" />
<%@page isELIgnored="false"%>
<%@ page import="gcom.arrecadacao.pagamento.bean.InserirPagamentoViaCanetaHelper"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>

<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="PagamentoActionForm" dynamicJavascript="false" />
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js" ></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js" ></script>
<script>
<!--

    var bCancel = false;

    function validatePagamentoActionForm(form) {
        if (bCancel)
      return true;
        else
       return validaRequiredCaneta() && validateLong(form) && validateInteiroZeroPositivo(form);
   }
   
   function InteiroZeroPositivoValidations() {
          this.ab = new Array("codigoBarraDigitadoDigitoVerificadorCampo1", "Dígito Verificador do Campo 1 do Código de Barras digitado deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
          this.ad = new Array("codigoBarraDigitadoDigitoVerificadorCampo2", "Dígito Verificador do Campo 2 do Código de Barras digitado deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
          this.af = new Array("codigoBarraDigitadoDigitoVerificadorCampo3", "Dígito Verificador do Campo 3 do Código de Barras digitado deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
          this.ah = new Array("codigoBarraDigitadoDigitoVerificadorCampo4", "Dígito Verificador do Campo 4 do Código de Barras digitado deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
   }
               
        
   
   function IntegerValidations () { 
       this.aa = new Array("codigoBarraDigitadoCampo1", "Campo 1 do Código de Barras digitado deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
       this.ac = new Array("codigoBarraDigitadoCampo2", "Campo 2 do Código de Barras digitado deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
       this.ae = new Array("codigoBarraDigitadoCampo3", "Campo 3 do Código de Barras digitado deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
       
    } 
    
-->
</script>

<script>
<!-- 
	function validaRequiredCaneta () {
		var form = document.forms[0];
		var msg = '';

		if(!form.tipoLeitura[0].checked && !form.tipoLeitura[1].checked){
			msg = 'Informe Dados do Código de Barras.';
		}
		if( msg != '' ){
			alert(msg);
			return false;
		}else{
			return true;
		}
	}

-->
</script>

<script>
<!--

	function validarCampo4(valorCampo){
	
		if (valorCampo.length > 0 && (isNaN(valorCampo) || valorCampo.indexOf(',') != -1 ||
			valorCampo.indexOf('.') != -1)){
			
			alert("Campo 4 do Código de Barras digitado deve somente conter números positivos.");
			return false;
		}else{
			return true;
		}
	}
	
	function removerCodigoDeBarra(urlRedirect){
 		redirecionarSubmit(urlRedirect);
 	}
 
	function adicionarCodigoDeBarra(){

	 	var form = document.forms[0];
	 	var tamanhoTotal = 
	 		form.codigoBarraDigitadoCampo1.value.length + 
	 		form.codigoBarraDigitadoDigitoVerificadorCampo1.value.length + 
	 		form.codigoBarraDigitadoCampo2.value.length + 
	 		form.codigoBarraDigitadoDigitoVerificadorCampo2.value.length + 
	 		form.codigoBarraDigitadoCampo3.value.length + 
	 		form.codigoBarraDigitadoDigitoVerificadorCampo3.value.length + 
	 		form.codigoBarraDigitadoCampo4.value.length + 
	 		form.codigoBarraDigitadoDigitoVerificadorCampo4.value.length;
	 	
	 	if(tamanhoTotal == 48){
	 	
	 		if (validarCampo4(form.codigoBarraDigitadoCampo4.value)){
		 		if(validatePagamentoActionForm(form)){
		 	    	form.action = "/gsan/inserirPagamentosWizardAction.do?destino=2&action=exibirInserirPagamentosTipoInclusaoCanetaAction";
		 	    	form.submit();
		 	  	}
	
	 		}
	 	}
	 }
 
 function adicionarCodigoDeBarraLeituraOtica(){
 	var form = document.forms[0];
 	var tamanhoTotal = form.codigoBarraPorLeituraOtica.value.length;

 	if(tamanhoTotal == 44){
 	  if(validatePagamentoActionForm(form)){
 	    form.action = "/gsan/inserirPagamentosWizardAction.do?destino=2&action=exibirInserirPagamentosTipoInclusaoCanetaAction";
 	    submitForm(form);
 	  }
 	}
 }
 
 function findRadioChecked(radioButtonCollection) {
    for (i=0; i < radioButtonCollection.length; i++){
      if (radioButtonCollection[i].checked == true) {
        return i;
      }
    }
  }

    
 function desabilitarCodigoBarra(){
 
	var form = document.forms[0];
	var radioChecked = findRadioChecked(form.tipoLeitura); 
 		
 		
 	if (radioChecked == 0 ){

 		form.codigoBarraDigitadoCampo1.disabled = false;
 		form.codigoBarraDigitadoDigitoVerificadorCampo1.disabled = false;
 		form.codigoBarraDigitadoCampo2.disabled = false;
 		form.codigoBarraDigitadoDigitoVerificadorCampo2.disabled = false;
 		form.codigoBarraDigitadoCampo3.disabled = false;
 		form.codigoBarraDigitadoDigitoVerificadorCampo3.disabled = false;
 		form.codigoBarraDigitadoCampo4.disabled = false;
 		form.codigoBarraDigitadoDigitoVerificadorCampo4.disabled = false;
 		
 		form.codigoBarraPorLeituraOtica.value = "";
 		form.codigoBarraPorLeituraOtica.disabled = true;
 		
 		form.codigoBarraDigitadoCampo1.focus();
 		
 	}
 	else if (radioChecked == 1 ){

 		form.codigoBarraDigitadoCampo1.disabled = true;
 		form.codigoBarraDigitadoDigitoVerificadorCampo1.disabled = true;
 		form.codigoBarraDigitadoCampo2.disabled = true;
 		form.codigoBarraDigitadoDigitoVerificadorCampo2.disabled = true;
 		form.codigoBarraDigitadoCampo3.disabled = true;
 		form.codigoBarraDigitadoDigitoVerificadorCampo3.disabled = true;
 		form.codigoBarraDigitadoCampo4.disabled = true;
 		form.codigoBarraDigitadoDigitoVerificadorCampo4.disabled = true;
 		
 		form.codigoBarraDigitadoCampo1.value = "";
 		form.codigoBarraDigitadoDigitoVerificadorCampo1.value = "";
 		form.codigoBarraDigitadoCampo2.value = "";
 		form.codigoBarraDigitadoDigitoVerificadorCampo2.value = "";
 		form.codigoBarraDigitadoCampo3.value = "";
 		form.codigoBarraDigitadoDigitoVerificadorCampo3.value = "";
 		form.codigoBarraDigitadoCampo4.value = "";
 		form.codigoBarraDigitadoDigitoVerificadorCampo4.value = "";
 		
 		form.codigoBarraPorLeituraOtica.disabled = false;
 		
 		form.codigoBarraPorLeituraOtica.focus();
 		
 	} else{
 	 	
 		form.codigoBarraDigitadoCampo1.disabled = true;
 		form.codigoBarraDigitadoDigitoVerificadorCampo1.disabled = true;
 		form.codigoBarraDigitadoCampo2.disabled = true;
 		form.codigoBarraDigitadoDigitoVerificadorCampo2.disabled = true;
 		form.codigoBarraDigitadoCampo3.disabled = true;
 		form.codigoBarraDigitadoDigitoVerificadorCampo3.disabled = true;
 		form.codigoBarraDigitadoCampo4.disabled = true;
 		form.codigoBarraDigitadoDigitoVerificadorCampo4.disabled = true;
 		
 		form.codigoBarraPorLeituraOtica.value = "";
 		form.codigoBarraPorLeituraOtica.disabled = true;
 		
 		form.codigoBarraDigitadoCampo1.value = "";
 		form.codigoBarraDigitadoDigitoVerificadorCampo1.value = "";
 		form.codigoBarraDigitadoCampo2.value = "";
 		form.codigoBarraDigitadoDigitoVerificadorCampo2.value = "";
 		form.codigoBarraDigitadoCampo3.value = "";
 		form.codigoBarraDigitadoDigitoVerificadorCampo3.value = "";
 		form.codigoBarraDigitadoCampo4.value = "";
 		form.codigoBarraDigitadoDigitoVerificadorCampo4.value = "";
 	}
 }
-->
</script>
</head>

<body leftmargin="5" topmargin="5" onload="javascript:desabilitarCodigoBarra();desabilitarCodigoBarra();">
<div id="formDiv">
<html:form
    action="/inserirPagamentosWizardAction"
    method="post"
    onsubmit="return validatePagamentoActionForm(this);"
>

<jsp:include page="/jsp/util/wizard/navegacao_abas_wizard_valida_avancar.jsp?numeroPagina=2"/>

<%@ include file="/jsp/util/cabecalho.jsp"%>
<%@ include file="/jsp/util/menu.jsp" %>
<input type="hidden" name="numeroPagina" value="2"/>
<table width="770" border="0" cellspacing="5" cellpadding="0">

  <tr>
    <td width="138" valign="top" class="leftcoltext">
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
          <td class="parabg">Inserir Pagamentos</td>
          <td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif"/></td>
        </tr>
      </table>
      <p>&nbsp;</p>
      <table width="100%" border="0" dwcopytype="CopyTableRow">
       	<tr>
          <td width="13%">
            <strong>Aviso Bancário:</strong>
          </td>
          <td colspan="3">
            <strong> 
			  <html:text property="codigoAgenteArrecadador" size="3" maxlength="3" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" />
			  <html:text property="dataLancamentoAviso" size="10" maxlength="10" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" />
			  <html:text property="numeroSequencialAviso" size="3" maxlength="3" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" />
			</strong>
		  </td>
        </tr>
        <tr>
          <td>
            <strong>Data do Pagamento:</strong>
          </td>
          <td>
            <html:text property="dataPagamento" size="10" maxlength="10" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>
          </td>
        </tr>
        <tr>
          <td>
            <strong>Forma de Arrecadação:</strong>
          </td>
          <td>
			<html:text property="descricaoFormaArrecadacao" size="60" maxlength="60" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>
          </td>
        </tr>
        <tr> 
          <td colspan="4"> Para inserir o pagamento, efetue a leitura via caneta ou digite os dados do c&oacute;digo de barras:</td>
        </tr>
        <tr> 
          <td colspan="4"><strong>Dados do C&oacute;digo de Barras</strong></td>
        </tr>
        <tr> 
          <td><html:radio onclick="javascript:desabilitarCodigoBarra();" property="tipoLeitura" value="digitacao"><strong>Por Digita&ccedil;&atilde;o:</strong></html:radio> </td>
          <td colspan="3"> 
            <strong> 
              <html:text property="codigoBarraDigitadoCampo1" disabled="true" tabindex="1" size="11" maxlength="11" onkeyup="javascript:autotab(this, document.PagamentoActionForm.codigoBarraDigitadoDigitoVerificadorCampo1);"/>
              <html:text property="codigoBarraDigitadoDigitoVerificadorCampo1" disabled="true" size="1" maxlength="1" onkeyup="autotab(this, document.PagamentoActionForm.codigoBarraDigitadoCampo2)"/>
              <html:text property="codigoBarraDigitadoCampo2"  size="11"  disabled="true" maxlength="11" onkeyup="autotab(this, document.PagamentoActionForm.codigoBarraDigitadoDigitoVerificadorCampo2)"/>
              <html:text property="codigoBarraDigitadoDigitoVerificadorCampo2"  disabled="true" size="1" maxlength="1" onkeyup="autotab(this, document.PagamentoActionForm.codigoBarraDigitadoCampo3)"/>

              <html:text property="codigoBarraDigitadoCampo3" disabled="true" size="11" maxlength="11" onkeyup="autotab(this, document.PagamentoActionForm.codigoBarraDigitadoDigitoVerificadorCampo3)"/>
              <html:text property="codigoBarraDigitadoDigitoVerificadorCampo3" disabled="true" size="1" maxlength="1" onkeyup="autotab(this, document.PagamentoActionForm.codigoBarraDigitadoCampo4)"/>
              <html:text property="codigoBarraDigitadoCampo4" disabled="true" size="11" maxlength="11" onkeyup="autotab(this, document.PagamentoActionForm.codigoBarraDigitadoDigitoVerificadorCampo4)"/>
              <html:text property="codigoBarraDigitadoDigitoVerificadorCampo4" disabled="true" size="1" maxlength="1" onkeyup="adicionarCodigoDeBarra()"/>
            </strong> 
          </td>
        </tr>
        <tr> 
          <td><html:radio onclick="javascript:desabilitarCodigoBarra();" property="tipoLeitura" value="optica"><strong>Por Leitura &Oacute;tica:</strong></html:radio> </td>
          <td colspan="3"> 
            <strong> 
               <html:text tabindex="0" property="codigoBarraPorLeituraOtica" size="44" maxlength="44" onkeyup="adicionarCodigoDeBarraLeituraOtica()" />
            </strong> 
          </td>
        </tr>
        <tr>
          <td>
            <strong>  </strong>
          </td>
          <td>
            <strong> <font color="#FF0000">*</font> </strong> Campo obrigat&oacute;rio
          </td>
        </tr>
        
        <tr> 
          <td colspan="3">
            <strong>
              <font color="#FF0000"> </font>
            </strong> 
          </td>
        </tr>
        <tr> 
          <td colspan="3">
            <table width="100%" bgcolor="#99CCFF">												
			  <tr bordercolor="#000000">
		        <td width="10%" align="center" bgcolor="#90c7fc"><strong>Remover</strong></td>
		        <td width="40%" align="center" bgcolor="#90c7fc"><strong>Código de Barra</strong></td>
		        <td width="10%" align="center" bgcolor="#90c7fc"><strong>Valor do Pagamento</strong></td>
		      </tr>
		      
		            <logic:present name="colecaoInserirPagamentoViaCanetaHelper">
					<%int cont = 1;%>
					<logic:iterate name="colecaoInserirPagamentoViaCanetaHelper" id="inserirPagamentoViaCanetaHelper" type="InserirPagamentoViaCanetaHelper">
						<%cont = cont + 1;
						if (cont % 2 == 0) {%>
						<tr bgcolor="#FFFFFF">
						<%} else {
						%>
						<tr bgcolor="#cbe5fe">
						<%}%>
						  <td width="10%">
						    <div align="center">
						      <img width="14" height="14" src="<bean:message key="caminho.imagens"/>Error.gif" title="Remover Documento" onclick="javascript:removerCodigoDeBarra('/gsan/inserirPagamentosWizardAction.do?destino=2&action=exibirInserirPagamentosTipoInclusaoCanetaAction&codigoBarraRemocao=<%="" + inserirPagamentoViaCanetaHelper.getCodigoBarra()%>');" style="cursor: hand;"/>
							</div>
						  </td>
						  
						  <td width="40%">
							<div align="center">
							  <bean:write name="inserirPagamentoViaCanetaHelper" property="codigoBarraFormatado"/>
							</div>
						  </td>
						  
						  <td width="10%">
							<div align="center">
							  <bean:write name="inserirPagamentoViaCanetaHelper" property="valorPagamento" formatKey="money.format"/>
							</div>
						  </td>
						</tr>
					</logic:iterate>
				  </logic:present> 
				   		        					              			              			              			              			              
            </table>
          </td>
        </tr>
        <tr>
          <td colspan="3"><div align="right"><jsp:include page="/jsp/util/wizard/navegacao_botoes_wizard_valida_avancar.jsp?numeroPagina=2"/></div></td>
        </tr>
      </table>
      <p>&nbsp;</p>
    </td>
  </tr>

</table>

<%@ include file="/jsp/util/rodape.jsp"%>

<script language="JavaScript">
<!--
desabilitarCodigoBarra();
//-->
</script>

</div>
</html:form>
</body>

<%@ include file="/jsp/util/telaespera.jsp"%>

</html:html>
