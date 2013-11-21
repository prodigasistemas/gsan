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

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="PesquisarDebitoACobrarActionForm" />

<script language="JavaScript">
<!--
   	function limparForm(){
		var form = document.forms[0];
		
		form.referenciaDebitoInicial.value="";
		form.referenciaDebitoFinal.value="";
		form.dataGeracaoDebitoInicial.value="";
		form.dataGeracaoDebitoFinal.value="";
		form.idSituacaoDebitoACobrar.value="";
		form.idTipoDebito.value="";
		//form.idTipoDebitoSelecionados.value="";
		
		MoverTodosDadosSelectMenu2PARAMenu1('PesquisarDebitoACobrarActionForm', 'idTipoDebito', 'idTipoDebitoSelecionados');
		form.referenciaDebitoInicial.focus();
	}
		

    
  function validarForm(form){
	
	urlRedirect = "/gsan/pesquisarDebitoACobrarAction.do"
	
	  if(validatePesquisarDebitoACobrarActionForm(form)){
	  
	    enviarSelectMultiplo('PesquisarDebitoACobrarActionForm','idTipoDebitoSelecionados');
	    
	    if (verificaAnoMes(form.referenciaDebitoInicial)){
	  
	      if (verificaAnoMes(form.referenciaDebitoFinal)){
		    if (comparaData(form.dataGeracaoDebitoInicial.value, ">", form.dataGeracaoDebitoFinal.value )){
		      alert('Data final do período de geração é anterior à data inicial do período de geração');			
		    } else {
		      redirecionarSubmit(urlRedirect);
		    }
		  }else{
		    form.referenciaDebitoFinal.value = "";
		    form.referenciaDebitoFinal.focus();
		  }
	    }	
	  }
  }
  
  function replicarReferencia(){
    var form = document.forms[0];
	form.referenciaDebitoFinal.value = form.referenciaDebitoInicial.value;
  }
  
  function replicarCampo(fim,inicio) {
   	fim.value = inicio.value;
 }
  
 
-->
</script>

</head>

<body leftmargin="5" topmargin="5" onload="javascript:resizePageSemLink(805, 530);setarFoco('${requestScope.nomeCampo}');">

<html:form action="/pesquisarDebitoACobrarAction" 
           method="post"
		   onsubmit="return validatePesquisarDebitoACobrarActionForm(this);">
		   
<table width="770" border="0" cellspacing="5" cellpadding="0">
  <tr>
	<td width="770" valign="top" class="centercoltext">
	  <table height="100%">
		<tr>
		  <td></td>
		</tr>
	  </table>
	  <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
	    <tr>
	  	  <td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_left.gif" /></td>
		  <td class="parabg">Pesquisar Débitos a Cobrar do Imóvel</td>
	 	  <td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
	    </tr>
	  </table>
	  <p>&nbsp;</p>
	  <table width="100%" border="0">
	    <tr>
		  <td colspan="2">Preencha os campos para pesquisar débitos a cobrar do imóvel:</td>
		</tr>
		<html:hidden property="idImovel" value="${requestScope.idImovel}"/>		
		<tr> 
          <td width="30%"><strong>Período de Referência dos Débitos a Cobrar:</strong></td>
          <td>
            <html:text property="referenciaDebitoInicial"  size="7" maxlength="7" onkeyup="javascript:replicarReferencia();" onkeypress="javascript:mascaraAnoMes(this,event),replicarReferencia();"/>
            <strong>a</strong> 
            <html:text property="referenciaDebitoFinal"  size="7" maxlength="7" onkeypress="javascript:mascaraAnoMes(this,event);"/>(mm/aaaa) 
          </td>
        </tr>
        <tr> 
          <td><strong>Período de Geração dos Débitos a Cobrar:</strong></td>
          <td>
            <html:text maxlength="10" property="dataGeracaoDebitoInicial" size="10" onkeyup="javascript:mascaraData(this,event); replicarCampo(document.forms[0].dataGeracaoDebitoFinal,this);" onblur="replicarCampo(document.forms[0].dataGeracaoDebitoFinal,this);" onfocus="replicarCampo(document.forms[0].dataGeracaoDebitoFinal,this);"/>
            <img border="0" src="<bean:message key='caminho.imagens'/>calendario.gif" onclick="javascript:abrirCalendarioReplicando('PesquisarDebitoACobrarActionForm', 'dataGeracaoDebitoInicial','dataGeracaoDebitoFinal');"	width="20" border="0" align="middle" alt="Exibir Calendário" /><strong> a</strong> 
            <html:text maxlength="10" property="dataGeracaoDebitoFinal" size="10" onkeypress="javascript:mascaraData(this,event);"/>
            <img border="0" src="<bean:message key='caminho.imagens'/>calendario.gif" onclick="javascript:abrirCalendario('PesquisarDebitoACobrarActionForm', 'dataGeracaoDebitoFinal')"	width="20" border="0" align="middle" alt="Exibir Calendário" /> (dd/mm/aaaa) 
          </td>
        </tr>
        <tr> 
          <td><strong>Situação do Débito a Cobrar:</strong></td>
		  <td>
            <html:select property="idSituacaoDebitoACobrar" multiple="true">
              <html:option value="-1">&nbsp;</html:option> 
			  <html:options collection="colecaoSituacaoDebitoACobrar" labelProperty="descricaoDebitoCreditoSituacao" property="id"/>
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
					    <input type="button" class="bottonRightCol"	onclick="javascript:MoverTodosDadosSelectMenu1PARAMenu2('PesquisarDebitoACobrarActionForm', 'idTipoDebito', 'idTipoDebitoSelecionados');" value=" &gt;&gt; ">
					  </td>
					</tr>
					<tr>
					  <td align="center">
					    <input type="button" class="bottonRightCol" onclick="javascript:MoverDadosSelectMenu1PARAMenu2('PesquisarDebitoACobrarActionForm', 'idTipoDebito', 'idTipoDebitoSelecionados');" value=" &nbsp;&gt;  ">
					  </td>
					</tr>
					<tr>
					  <td align="center">
					    <input type="button" class="bottonRightCol"	onclick="javascript:MoverDadosSelectMenu2PARAMenu1('PesquisarDebitoACobrarActionForm', 'idTipoDebito', 'idTipoDebitoSelecionados');" value=" &nbsp;&lt;  ">
					  </td>
					</tr>
					<tr>
					  <td align="center">
					    <input type="button" class="bottonRightCol"	onclick="javascript:MoverTodosDadosSelectMenu2PARAMenu1('PesquisarDebitoACobrarActionForm', 'idTipoDebito', 'idTipoDebitoSelecionados');" value=" &lt;&lt; ">
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
	          	<INPUT TYPE="button" class="bottonRightCol" value="Limpar" onclick="javascript:limparForm();"/>
					&nbsp;&nbsp;
	          	<logic:present name="caminhoRetornoTelaPesquisaDebitoACobrar">
	          		<INPUT TYPE="button" class="bottonRightCol" value="Voltar" onclick="redirecionarSubmit('${caminhoRetornoTelaPesquisaDebitoACobrar}.do')"/>
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
