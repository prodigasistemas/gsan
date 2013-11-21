<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ page import="gcom.faturamento.debito.DebitoCreditoSituacao" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>

<title>GSAN - Sistema Integrado de Gest&atilde;o de Servi&ccedil;os de Saneamento</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js" ></script>

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="PesquisarContaActionForm" />

<script language="JavaScript">
<!--
    
  	function limparForm(){
		var form = document.forms[0];
		form.referenciaContaInicial.value="";
		form.referenciaContaFinal.value="";
		form.dataEmissaoContaInicial.value="";
		form.dataEmissaoContaFinal.value="";
		form.dataVencimentoContaInicial.value="";
		form.dataVencimentoContaFinal.value="";
		form.idSituacaoConta.value="";
		form.referenciaContaInicial.focus();
	}  
    
    
  function validarForm(form){
	
	urlRedirect = "/gsan/pesquisarContaAction.do"
	
	  if(validatePesquisarContaActionForm(form)){
	  
	    if (verificaAnoMes(form.referenciaContaInicial)){
	  
	      if (verificaAnoMes(form.referenciaContaFinal)){
	  		
	        if (comparaData(form.dataEmissaoContaInicial.value, ">", form.dataEmissaoContaFinal.value )){
		      alert('Data final do período de emissão é anterior à data inicial do período de emissão');
		    }
		    else if (comparaData(form.dataVencimentoContaInicial.value, ">", form.dataVencimentoContaFinal.value )){
		      alert('Data final do período de vencimento é anterior à data inicial do período de vencimento');			
		    } else {
		      redirecionarSubmit(urlRedirect);
		    }
		  }else{
		    form.referenciaContaFinal.value = "";
		    form.referenciaContaFinal.focus();
		  }
		  
		  
	    }	
	  }
  }
  
  function replicarReferencia(){
    var form = document.forms[0];
	form.referenciaContaFinal.value = form.referenciaContaInicial.value;
  }
  
  function replicarCampo(fim,inicio) {
   	fim.value = inicio.value;
 }
-->
</script>

</head>

<body leftmargin="5" topmargin="5" onload="javascript:resizePageSemLink(730, 450);setarFoco('${requestScope.nomeCampo}');">

<html:form action="/pesquisarContaAction" 
           method="post"
		   onsubmit="return validatePesquisarContaActionForm(this);">
		   
<table width="680" border="0" cellspacing="5" cellpadding="0">
  <tr>
	<td width="680" valign="top" class="centercoltext">
	  <table height="100%">
		<tr>
		  <td></td>
		</tr>
	  </table>
	  <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
	    <tr>
	  	  <td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_left.gif" /></td>
		  <td class="parabg">Pesquisar Contas do Imóvel</td>
	 	  <td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
	    </tr>
	  </table>
	  <p>&nbsp;</p>
	  <table width="100%" border="0">
	    <tr>
		  <td colspan="2">Preencha os campos para pesquisar contas do imóvel:</td>
		</tr>
		<html:hidden property="idImovel" value="${requestScope.idImovel}"/>		
		<tr> 
          <td width="40%"><strong>Per&iacute;odo de Refer&ecirc;ncia das Contas:</strong></td>
          <td>
            <html:text property="referenciaContaInicial"  size="7" maxlength="7" onkeyup="javascript:replicarReferencia();" onkeypress="javascript:mascaraAnoMes(this,event),replicarReferencia();"/>
            <strong>a</strong> 
            <html:text property="referenciaContaFinal"  size="7" maxlength="7" onkeypress="javascript:mascaraAnoMes(this,event);"/>(mm/aaaa) 
          </td>
        </tr>
        <tr> 
          <td><strong>Per&iacute;odo de Emiss&atilde;o das Contas:</strong></td>
          <td>
            <html:text maxlength="10" property="dataEmissaoContaInicial" size="10" onkeyup="javascript:mascaraData(this,event);replicarCampo(document.forms[0].dataEmissaoContaFinal,this);"  onfocus="replicarCampo(document.forms[0].dataEmissaoContaFinal,this);"/>
             <a href="javascript:abrirCalendarioReplicando('PesquisarContaActionForm', 'dataEmissaoContaInicial', 'dataEmissaoContaFinal');">
				<img border="0" src="<bean:message key="caminho.imagens"/>calendario.gif"
				width="20" border="0" align="absmiddle" alt="Exibir Calendário" /></a>
            <html:text maxlength="10" property="dataEmissaoContaFinal" size="10" onkeypress="javascript:mascaraData(this,event);"/>
             <a href="javascript:abrirCalendario('PesquisarContaActionForm', 'dataEmissaoContaFinal')">
				<img border="0" src="<bean:message key="caminho.imagens"/>calendario.gif"
				width="20" border="0" align="absmiddle" alt="Exibir Calendário" /></a>
          </td>
        </tr>
        <tr> 
          <td><strong>Per&iacute;odo de Vencimento das Contas:</strong></td>
          <td>
            <html:text maxlength="10" property="dataVencimentoContaInicial" size="10" onkeyup="javascript:mascaraData(this,event);replicarCampo(document.forms[0].dataVencimentoContaFinal,this);" onfocus="replicarCampo(document.forms[0].dataVencimentoContaFinal,this);"/>
            <a href="javascript:abrirCalendarioReplicando('PesquisarContaActionForm', 'dataVencimentoContaInicial', 'dataVencimentoContaFinal');">
				<img border="0" src="<bean:message key="caminho.imagens"/>calendario.gif"
				width="20" border="0" align="absmiddle" alt="Exibir Calendário" /></a>
            <html:text maxlength="10" property="dataVencimentoContaFinal" size="10" onkeypress="javascript:mascaraData(this,event);"/>
             <a href="javascript:abrirCalendario('PesquisarContaActionForm', 'dataVencimentoContaFinal')">
				<img border="0" src="<bean:message key="caminho.imagens"/>calendario.gif"
				width="20" border="0" align="absmiddle" alt="Exibir Calendário" /></a>
          </td>
        </tr>
        <tr> 
          <td><strong>Situa&ccedil;&atilde;o da Conta:</strong></td>
		  <td>
		  
		  <logic:present name="situacaoConta"> 
		  	<html:select property="idSituacaoConta" multiple="true">
              <html:option value="-1">&nbsp;</html:option> 
              <html:option value="DebitoCreditoSituacao.INCLUIDA">INCLUIDA</html:option> 
              <html:option value="DebitoCreditoSituacao.NORMAL">NORMAL</html:option> 
              <html:option value="DebitoCreditoSituacao.RETIFICADA">RETIFICADA</html:option> 
            </html:select>
		  
		  </logic:present>
		  
		  <logic:notPresent name="situacaoConta">
		 	 <html:select property="idSituacaoConta" multiple="true">
              <html:option value="-1">&nbsp;</html:option> 
			  <html:options collection="colecaoSituacaoConta" labelProperty="descricaoDebitoCreditoSituacao" property="id"/>
            </html:select>
		  </logic:notPresent>
		  
            
          </td>
        </tr>		
				
		<tr>
		  <td>&nbsp;</td>
		  <td>&nbsp;</td>
		</tr>
		
		<tr>
			<td colspan="2">
	          	<INPUT TYPE="button" class="bottonRightCol" value="Limpar" onclick="document.forms[0].reset();limparForm();"/>
					&nbsp;&nbsp;
	          	<logic:present name="caminhoRetornoTelaPesquisaConta">
	          		<INPUT TYPE="button" class="bottonRightCol" value="Voltar" onclick="redirecionarSubmit('${caminhoRetornoTelaPesquisaConta}.do')"/>
	          	</logic:present>
	         </td>
	         <td align="right">
		         <input type="button" name="Button" class="bottonRightCol" value="Pesquisar" onclick="javascript:validarForm(document.forms[0]);" />
			 </td>
		</tr>


		
	  </table>
	  <p>&nbsp;</p>
	</td>
  </tr>
</table>
</html:form>
</body>
</html:html>
