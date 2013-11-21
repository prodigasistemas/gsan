<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%@page import="gcom.util.ConstantesSistema"%>

<html:html>
<head>

<title>GSAN - Sistema Integrado de Gest&atilde;o de Servi&ccedil;os de Saneamento</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="PesquisarTipoDebitoActionForm" />

<script type="text/javascript" language="Javascript1.1">
<!--
 function validarForm(form){
  urlRedirect = "/gsan/pesquisarTipoDebitoAction.do"
  
  if(validatePesquisarTipoDebitoActionForm(form)){
    redirecionarSubmit(urlRedirect);
  }
}
  
 function replicarIntervalo(){
  var form = document.forms[0];
  form.intervaloValorLimiteFinal.value = form.intervaloValorLimiteInicial.value;
  }
  
  function limparForm(){
	var form = document.forms[0];
	form.idTipoDebito.value = "";
	form.descricao.value = "";
	form.idTipoFinanciamento.value = "-1";
	form.idItemLancamentoContabil.value = "-1";
	form.intervaloValorLimiteInicial.value = "";
	form.intervaloValorLimiteFinal.value = "";
}
-->
</script>

</head>

<body leftmargin="5" topmargin="5"
	onload="javascript:resizePageSemLink(605, 620);javascript:setarFoco('${requestScope.nomeCampo}');">

<html:form action="/pesquisarTipoDebitoAction" 
           method="post"
		   onsubmit="return validatePesquisarTipoDebitoActionForm(this);">
		   
<table width="570" border="0" cellspacing="5" cellpadding="0">
  <tr>
	<td width="570" valign="top" class="centercoltext">
	  <table height="100%">
		<tr>
		  <td></td>
		</tr>
	  </table>
	  <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
	    <tr>
	  	  <td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_left.gif" /></td>
		  <td class="parabg">Pesquisar Tipo de Débito</td>
	 	  <td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
	    </tr>
	  </table>
	  <p>&nbsp;</p>
	  <table width="100%" border="0">
	    <tr>
		  <td colspan="2">Preencha os campos para pesquisar um tipo de débito:</td>
		  <td align="right"><a href="javascript: abrirPopupHelp('/gsan/help/help.jsp?mapIDHelpSet=faturamentoDebitoCobrarTipoDebitoPesquisar', 500, 700);"><span style="font-weight: bold"><font color="#3165CE">Ajuda</font></span></a></td>
		</tr>
	  </table>
		
	  <table width="100%" border="0">
		<tr> 
          <td width="30%"><strong>Código:</strong></td>
          <td>
            <html:text property="idTipoDebito" size="4" maxlength="4"/>
          </td>
        </tr>
        <tr> 
          <td><strong>Descrição:</strong></td>
          <td>
            <html:text property="descricao" maxlength="30" size="30" />
          </td>
        </tr>
        <tr>
			<td>&nbsp;</td>
			<td align="top">
				<html:radio property="tipoPesquisa"
					value="<%=ConstantesSistema.TIPO_PESQUISA_INICIAL.toString()%>" />
					Iniciando pelo texto
				<html:radio property="tipoPesquisa"
					value="<%=ConstantesSistema.TIPO_PESQUISA_COMPLETA.toString()%>" />
						Contendo o texto
			</td>
		</tr>
        <tr> 
          <td><strong>Tipo de Financiamento:</strong></td>
		  <td>
            <html:select property="idTipoFinanciamento" size="8" multiple="true" style="width:250px">
              <html:option value="-1">&nbsp;</html:option> 
			  <html:options collection="colecaoFinanciamentoTipo" labelProperty="descricao" property="id"/>
            </html:select>
          </td>
        </tr>		

        <tr> 
          <td><strong>Item de Lançamento Contábil:</strong></td>
		  <td>
            <html:select property="idItemLancamentoContabil" size="8" multiple="true" style="width:250px">
              <html:option value="-1">&nbsp;</html:option> 
			  <html:options collection="colecaoLancamentoItemContabil" labelProperty="descricao" property="id"/>
            </html:select>
          </td>
        </tr>		
		
		<tr> 
          <td width="30%"><strong>Intervalo de Valor Limite:</strong></td>
          <td>
            <html:text property="intervaloValorLimiteInicial"  size="13" maxlength="13" onkeyup="javascript:formataValorMonetario(this, 13);replicarIntervalo();" />
            <strong>a</strong> 
            <html:text property="intervaloValorLimiteFinal"  size="13" maxlength="13" onkeyup="javascript:formataValorMonetario(this, 13);"/>
          </td>
        </tr>		
				
		<tr>
		  <td>&nbsp;</td>
		  <td>&nbsp;</td>
		</tr>

		<tr>
			<td height="24" colspan="3" width="80%">
	          	<INPUT TYPE="button" class="bottonRightCol" value="Limpar" onclick="javascript:limparForm();"/>
					&nbsp;&nbsp;
	          	<logic:present name="caminhoRetornoTelaPesquisaTipoDebito">
	          		<INPUT TYPE="button" class="bottonRightCol" value="Voltar" onclick="redirecionarSubmit('${caminhoRetornoTelaPesquisaTipoDebito}.do')"/>
	          	</logic:present>
	        </td>
	        <td align="right">
				<input type="button" name="Button" class="bottonRightCol" 
				value="Pesquisar" onclick="javascript:validarForm(document.forms[0]);" />
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
