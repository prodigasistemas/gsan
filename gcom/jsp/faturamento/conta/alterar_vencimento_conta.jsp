<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>


<html:html>
<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js" ></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js" ></script>

<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="AlterarVencimentoContaActionForm"/>

<SCRIPT LANGUAGE="JavaScript">
<!--

function validarForm(form){
	
	var msgDataVencimento = "Data de Vencimento anterior à data corrente.";
	var msgDataVencimento60 = "Data de vencimento posterior a data corrente mais 60 dias.";
	var msgDataVencimentoUltimoDiaMes = "Data de Vencimento foi alterada para o último dia do mês corrente.Confirma ?";
	var DATA_ATUAL = document.getElementById("DATA_ATUAL").value;
	var DATA_ATUAL_60 = document.getElementById("DATA_ATUAL_60").value;
	var ULTIMA_DATA_MES = document.getElementById("ULTIMA_DATA_MES").value;
	var INDICADOR_CALCULA_VENCIMENTO = document.getElementById("INDICADOR_CALCULA_VENCIMENTO").value;

	
	if (validateAlterarVencimentoContaActionForm(form)){


		if (comparaData(form.dataVencimento.value, "<", DATA_ATUAL )){
			if (confirm(msgDataVencimento)){
				if (confirm("Confirma alteração de vencimento ?")){

					submeterFormPadrao(form);
				}
			}
		}
		else if (comparaData(form.dataVencimento.value, ">", DATA_ATUAL_60 )){
			if (confirm(msgDataVencimento60)){
				if (confirm("Confirma alteração de vencimento ?")){

					submeterFormPadrao(form);
				}
			}
		}else  if(INDICADOR_CALCULA_VENCIMENTO != 2){ 
 			if (comparaData(form.dataVencimento.value, ">", ULTIMA_DATA_MES )){ 
 

  			 if (confirm(msgDataVencimentoUltimoDiaMes)){
                form.dataVencimento.value = ULTIMA_DATA_MES;  
   
                form.dataVencimento.focus();

            }else{          
               form.dataVencimento.focus();

           }        
      }
   
     submeterFormPadrao(form);

  }  
  else if (confirm("Confirma alteração de vencimento ?")){
    submeterFormPadrao(form);
  } 

}
		
	
}



//-->
</SCRIPT>

</head>

<logic:present name="reloadPage">
	<body leftmargin="0" topmargin="0" onload="chamarSubmitComUrl('exibirManterContaAction.do'); window.close();">
</logic:present>

<logic:notPresent name="reloadPage">
	<body leftmargin="0" topmargin="0" onload="resizePageSemLink(480, 220);">
</logic:notPresent>


<INPUT TYPE="hidden" ID="DATA_ATUAL" value="${requestScope.dataAtual}"/>
<INPUT TYPE="hidden" ID="DATA_ATUAL_60" value="${requestScope.dataAtual60}"/>
<INPUT TYPE="hidden" ID="ULTIMA_DATA_MES" value="${requestScope.ultimaDataMes}"/>
<INPUT TYPE="hidden" ID="INDICADOR_CALCULA_VENCIMENTO" value="${requestScope.indicadorCalculaVencimento}"/>


<html:form action="/alterarVencimentoContaAction" method="post">

<html:hidden property="contaSelected"/>

<table width="452" border="0" cellpadding="0" cellspacing="5">
  <tr>
    <td width="442" valign="top" class="centercoltext"> 
      <table height="100%">
        <tr>
          <td></td>
        </tr>
      </table>
      <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td width="11"><img src="<bean:message key="caminho.imagens"/>parahead_left.gif" editor="Webstyle4" moduleid="Album Photos (Project)\toptab_page2_parahead_left.xws" border="0" /></td>
          <td class="parabg">Alterar Vencimento da Conta</td>
          <td width="11"><img src="<bean:message key="caminho.imagens"/>parahead_right.gif" editor="Webstyle4" moduleid="Album Photos (Project)\toptab_page2_parahead_right.xws" border="0" /></td>
        </tr>
      </table> 
      <p>&nbsp;</p>

      <table width="100%" border="0">
        <tr>
          <td colspan="4">Informe o novo vencimento:</td>
		  <logic:present scope="application" name="urlHelp">
				<td align="right"><a href="javascript:abrirPopupHelp('${applicationScope.urlHelp}faturamentoContaVencimentoAlterar', 500, 700);"><span style="font-weight: bold"><font color="#3165CE">Ajuda</font></span></a></td>									
		  </logic:present>
		  <logic:notPresent scope="application" name="urlHelp">
				<td align="right"><span style="font-weight: bold"><font color=#696969><u>Ajuda</u></font></span></td>									
		  </logic:notPresent>
        </tr>
        </table>
      <table width="100%" border="0">
        <tr>
          <td width="150" height="20"><strong>Data de Vencimento<font
						color="#FF0000">*</font></strong></td>
          <td colspan="3">
          
          		<html:text property="dataVencimento" 
          				   size="11" 
          				   maxlength="10" 
          				   tabindex="4" 
          				   onkeyup="mascaraData(this, event);"
          				   onkeypress="return isCampoNumerico(event);"/>
				<a href="javascript:abrirCalendario('AlterarVencimentoContaActionForm', 'dataVencimento')">
                    <img border="0" src="<bean:message key='caminho.imagens'/>calendario.gif" width="20" border="0" align="absmiddle" alt="Exibir Calendário"/></a>&nbsp;dd/mm/aaaa          
          </td>
        </tr>
        <tr>
          <td colspan="4" height="5"></td>
        </tr>
        <tr>
          <td height="30" colspan="4"> 
          	<div align="right">
              <input type="button" onclick="validarForm(document.forms[0]);" class="bottonRightCol" value="Concluir" style="width: 70px;">&nbsp;
              <input type="button" onclick="window.close();" class="bottonRightCol" value="Fechar" style="width: 70px;">
			</div>
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
