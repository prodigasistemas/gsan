<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<%@ page import="gcom.util.Pagina, gcom.util.ConstantesSistema"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>

<title>GSAN - Sistema Integrado de Gest&atilde;o de Servi&ccedil;os de Saneamento</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js" ></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="PesquisarAtividadeActionForm"/>
<script>

 var bCancel = false;

	function validarForm(form){
	
	 if ( validarTipoMovimento()) {
	
		if(validatePesquisarNegativadorMovimentoActionForm(form)){
    		submeterFormPadrao(form);
		}
	 }	
		
	}

	/* Limpa Form */	 
	function limparForm() {
		var form = document.forms[0];
    	form.idNegativadorMovimento.value = "";
	  
	    form.action = 'exibirPesquisarNegativadorMovimentoAction.do';
		form.submit();
	}    
	
	
	
	
		 
</script>
</head>

<body leftmargin="0" topmargin="0" onload="window.focus();resizePageSemLink(500, 370);setarFoco('${requestScope.nomeCampo}');">
<html:form
  action="/pesquisarNegativadorMovimentoAction"
  method="post"
  onsubmit="return validatePesquisarNegativadorMovimentoActionForm(this);"
>
<table width="480" border="0" cellspacing="5" cellpadding="0">
  <tr>
    <td width="440" valign="top" class="centercoltext">
    <table height="100%">
        <tr>
          <td></td>
        </tr>
      </table>
    
  
      <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr> 
          <td width="11"><img src="imagens/parahead_left.gif" editor="Webstyle4" moduleid="Album Photos (Project)\toptab_page2_parahead_left.xws" border="0" /></td>
          <td class="parabg">Pesquisar Movimento do Negativador</td>
          <td width="11"><img src="imagens/parahead_right.gif" editor="Webstyle4" moduleid="Album Photos (Project)\toptab_page2_parahead_right.xws" border="0" /></td>
        </tr>
      </table>
      <p>&nbsp;</p>
      <table width="100%" border="0">
        <tr> 
          <td colspan="4">Preencha os campos para pesquisar um movimento:</td>
        </tr>
        <tr> 
          <td width="43%"><strong>Negativador:<strong><font color="#FF0000">*</font></strong></strong></td>
          <td width="57%" colspan="3">
          
          <logic:present name="colecaoNegativador">  
             <html:select property="idNegativador" tabindex="7">
				<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
				   <logic:present name="colecaoNegativador">
					 <html:options collection="colecaoNegativador" labelProperty="cliente.nome" property="id"/>
				    </logic:present>
				</html:select>
          </logic:present>    
           
          </td>
        </tr>
        <tr> 
          <td height="0"><strong>Tipo do Movimento:<strong><font color="#FF0000">*</font></strong></strong></td>
          <td colspan="3"> <strong> <span class="style1"><strong>             
            <html:radio property="codigoMovimento" value="1"/>           
            <strong>Inclus&atilde;o 
             <html:radio property="codigoMovimento" value="2"/>  
            Exclus&atilde;o<strong><strong>
             <html:radio property="codigoMovimento" value="3"/>  
            Todos
</strong></strong></strong></strong></span></strong></td>
        </tr>
        <tr> 
        <tr> 
          <td height="0"><strong>N&uacute;mero Sequencial do Arquivo (NSA):</strong></td>
          <td colspan="3"> <strong> 
          <html:text property="numeroSequencialEnvio" size="9" maxlength="9"/>          
           
            </strong> </td>
        </tr>
        <tr> 
          <td><strong>Per&iacute;odo de Processamento do Movimento:</strong></td>
          <td colspan="3"><strong> 
               <html:text property="dataProcessamentoInicial" size="10" maxlength="10"
						tabindex="8" onkeyup="mascaraData(this, event);" /> <a
						href="javascript:abrirCalendarioReplicando('PesquisarNegativadorMovimentoActionForm', 'dataProcessamentoInicial','dataProcessamentoFinal')">
					    <img border="0"
						src="<bean:message key="caminho.imagens"/>calendario.gif"
						width="20" border="0" align="absmiddle" alt="Exibir Calendário" /></a> a</strong> 
                <html:text property="dataProcessamentoFinal" size="10" maxlength="10"
						tabindex="8" onkeyup="mascaraData(this, event);" /> <a
						href="javascript:abrirCalendario('PesquisarNegativadorMovimentoActionForm', 'dataProcessamentoFinal')">
					    <img border="0"
						src="<bean:message key="caminho.imagens"/>calendario.gif"
						width="20" border="0" align="absmiddle" alt="Exibir Calendário" /></a> (dd/mm/aaaa)          </td>
        </tr>
        <tr> 
          <td>&nbsp;</td>
          <td colspan="3">&nbsp;</td>
        </tr>
        <tr> 
          <td height="24">
          <input type="submit" class="bottonRightCol" value="Pesquisar"/>
         <!--   <input name="Button" type="button" class="bottonRightCol" value="Pesquisar" onClick="javascript:window.open('negativador_movimento_resultado_pesquisa.htm', 'pesquisar','location=no,screenY=0,screenX=0,menubar=no,status=no,toolbar=no,scrollbars=no,resizable=no,width=660,height=590');">
         
         -->
         
         </td>
          <td colspan="3">&nbsp;</td>
        </tr>
      </table>
    
    

    
    
    
    
	</td>
  </tr>
</table>



      
     </table>


</body>
</html:form>
</html:html>
