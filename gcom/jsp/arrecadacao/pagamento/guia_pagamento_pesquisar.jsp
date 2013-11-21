<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>

<title>GSAN - Sistema Integrado de Gest&atilde;o de Servi&ccedil;os de Saneamento</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js" ></script>

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="PesquisarGuiaPagamentoActionForm" />

<script language="JavaScript">
<!--


   	function limparForm(){
		var form = document.forms[0];

		form.idTipoDebito.value="";
		form.idSituacaoGuiaPagamento.value="";
		form.dataVencimentoGuiaPagamentoInicial.value="";
		form.dataVencimentoGuiaPagamentoFinal.value="";
		form.dataEmissaoGuiaPagamentoInicial.value="";
		form.dataEmissaoGuiaPagamentoFinal.value="";
		
		MoverTodosDadosSelectMenu2PARAMenu1('PesquisarGuiaPagamentoActionForm', 'idTipoDebito', 'idTipoDebitoSelecionados');
		form.dataEmissaoGuiaPagamentoInicial.focus();
	}

  function validarForm(form){
	
	urlRedirect = "/gsan/pesquisarGuiaPagamentoAction.do"
	
	  if(validatePesquisarGuiaPagamentoActionForm(form)){
	  
	  	 enviarSelectMultiplo('PesquisarGuiaPagamentoActionForm','idTipoDebitoSelecionados');
	  	
	    if (comparaData(form.dataEmissaoGuiaPagamentoInicial.value, ">", form.dataEmissaoGuiaPagamentoFinal.value )){
		  alert('Data final do período de emissão é anterior à data inicial do período de emissão');
		}
		else if (comparaData(form.dataVencimentoGuiaPagamentoInicial.value, ">", form.dataVencimentoGuiaPagamentoFinal.value )){
		  alert('Data final do período de vencimento é anterior à data inicial do período de vencimento');			
		} else {
		  redirecionarSubmit(urlRedirect);
		}
	  }
  }
  
    
 function replicarCampo(fim,inicio) {
   	fim.value = inicio.value;
 }
 -->
</script>

</head>

<body leftmargin="5" topmargin="5" onload="resizePageSemLink(790, 530);javascript:setarFoco('${requestScope.nomeCampo}');">
<html:form action="/pesquisarGuiaPagamentoAction" 
           method="post"
		   onsubmit="return validatePesquisarGuiaPagamentoActionForm(this);">
		   
<table width="750" border="0" cellspacing="5" cellpadding="0">
  <tr>
	<td width="750" valign="top" class="centercoltext">
	  <table height="100%">
		<tr>
		  <td></td>
		</tr>
	  </table>
	  <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
	    <tr>
	  	  <td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_left.gif" /></td>
		  <td class="parabg">Pesquisar Guias de Pagamento do Imóvel ou do Cliente</td>
	 	  <td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
	    </tr>
	  </table>
	  <p>&nbsp;</p>
	  <table width="100%" border="0">
	    <tr>
		  <td colspan="2">Preencha os campos para pesquisar guias de pagamento do imóvel ou do cliente:</td>
		</tr>
		<html:hidden property="idImovel" value="${requestScope.idImovel}"/>		
		<html:hidden property="idCliente" value="${requestScope.idCliente}"/>
		
        <tr> 
          <td><strong>Per&iacute;odo de Emiss&atilde;o das Guias:</strong></td>
          <td>
            <html:text maxlength="10" property="dataEmissaoGuiaPagamentoInicial" size="10" onkeyup="javascript:mascaraData(this,event); replicarCampo(document.forms[0].dataEmissaoGuiaPagamentoFinal,this);"  onfocus="replicarCampo(document.forms[0].dataEmissaoGuiaPagamentoFinal,this);"/>
            <img border="0" src="<bean:message key='caminho.imagens'/>calendario.gif" onclick="javascript:abrirCalendarioReplicando('PesquisarGuiaPagamentoActionForm', 'dataEmissaoGuiaPagamentoInicial', 'dataEmissaoGuiaPagamentoFinal');"	width="20" border="0" align="middle" alt="Exibir Calendário" /><strong> a</strong> 
            <html:text maxlength="10" property="dataEmissaoGuiaPagamentoFinal" size="10" onkeyup="javascript:mascaraData(this,event);"/>
            <img border="0" src="<bean:message key='caminho.imagens'/>calendario.gif" onclick="javascript:abrirCalendario('PesquisarGuiaPagamentoActionForm', 'dataEmissaoGuiaPagamentoFinal')"	width="20" border="0" align="middle" alt="Exibir Calendário" /> (dd/mm/aaaa) 
          </td>
        </tr>
        
        <tr> 
          <td><strong>Per&iacute;odo de Vencimento das Guias:</strong></td>
          <td>
            <html:text maxlength="10" property="dataVencimentoGuiaPagamentoInicial" size="10" onkeyup="javascript:mascaraData(this,event);replicarCampo(document.forms[0].dataVencimentoGuiaPagamentoFinal,this);"  onfocus="replicarCampo(document.forms[0].dataVencimentoGuiaPagamentoFinal,this);"/>
            <img border="0" src="<bean:message key='caminho.imagens'/>calendario.gif" onclick="javascript:abrirCalendarioReplicando('PesquisarGuiaPagamentoActionForm', 'dataVencimentoGuiaPagamentoInicial', 'dataVencimentoGuiaPagamentoFinal');" width="20" border="0" align="middle" alt="Exibir Calendário" /><strong> a</strong> 
            <html:text maxlength="10" property="dataVencimentoGuiaPagamentoFinal" size="10" onkeypress="javascript:mascaraData(this,event);"/>
            <img border="0" src="<bean:message key='caminho.imagens'/>calendario.gif" onclick="javascript:abrirCalendario('PesquisarGuiaPagamentoActionForm', 'dataVencimentoGuiaPagamentoFinal')"	width="20" border="0" align="middle" alt="Exibir Calendário" /> (dd/mm/aaaa) 
          </td>
        </tr>
        
        <tr> 
          <td><strong>Situa&ccedil;&atilde;o da Guia:</strong></td>
		  <td>
            <html:select property="idSituacaoGuiaPagamento" multiple="true">
              <html:option value="-1">&nbsp;</html:option> 
			  <html:options collection="colecaoSituacaoGuiaPagamento" labelProperty="descricaoDebitoCreditoSituacao" property="id"/>
            </html:select>
          </td>
        </tr>		

		<tr>
		  <td><strong>Tipo de Débito:</strong></td>
		  <td colspan="3">
			<table border=0 cellpadding=0 cellspacing=0 align="left">
			  <tr>
				<td align="center">
				  <div align="left"><strong>Disponíveis</strong></div>
				  <html:select property="idTipoDebito" size="6" multiple="true"	style="width:230px">
                    <html:options collection="colecaoTipoDebito" labelProperty="descricao" property="id"/>
                  </html:select>
				</td>
				<td align="center" width="5" valign="center"><br>
				  <table width="50" align="center">
					<tr>
					  <td align="center">
					    <input type="button" class="bottonRightCol"	onclick="javascript:MoverTodosDadosSelectMenu1PARAMenu2('PesquisarGuiaPagamentoActionForm', 'idTipoDebito', 'idTipoDebitoSelecionados');" value=" &gt;&gt; ">
					  </td>
					</tr>
					<tr>
					  <td align="center">
					    <input type="button" class="bottonRightCol" onclick="javascript:MoverDadosSelectMenu1PARAMenu2('PesquisarGuiaPagamentoActionForm', 'idTipoDebito', 'idTipoDebitoSelecionados');" value=" &nbsp;&gt;  ">
					  </td>
					</tr>
					<tr>
					  <td align="center">
					    <input type="button" class="bottonRightCol"	onclick="javascript:MoverDadosSelectMenu2PARAMenu1('PesquisarGuiaPagamentoActionForm', 'idTipoDebito', 'idTipoDebitoSelecionados');" value=" &nbsp;&lt;  ">
					  </td>
					</tr>
					<tr>
					  <td align="center">
					    <input type="button" class="bottonRightCol"	onclick="javascript:MoverTodosDadosSelectMenu2PARAMenu1('PesquisarGuiaPagamentoActionForm', 'idTipoDebito', 'idTipoDebitoSelecionados');" value=" &lt;&lt; ">
					  </td>
					</tr>
				  </table>
				</td>
				<td align="center">
				  <div align="left"><strong>Selecionados</strong></div>
				  <html:select property="idTipoDebitoSelecionados" size="6" multiple="true"	style="width:230px"></html:select>
				</td>
			  </tr>
			</table>
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
	          	<logic:present name="caminhoRetornoTelaPesquisaGuiaPagamento">
	          		<INPUT TYPE="button" class="bottonRightCol" value="Voltar" onclick="redirecionarSubmit('${caminhoRetornoTelaPesquisaGuiaPagamento}.do')"/>
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
