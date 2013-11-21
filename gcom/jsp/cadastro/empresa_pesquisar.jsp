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

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="PesquisarActionForm"/>

<script>

 var bCancel = false;

    function validatePesquisarActionForm(form) {
        if (bCancel)
          return true;
        else
         return validateCaracterEspecial(form) && testarCampoValorZero(document.forms[0].idEmpresa, 'Código')
       && validateLong(form);
   }

    function caracteresespeciais () {
     this.ab = new Array("descricaoEmpresa", "Descrição possui caracteres especiais.", new Function ("varName", " return this[varName];"));
    }

    function IntegerValidations () {
     this.aa = new Array("idEmpresa", "Código deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
    }
    
    function limparForm(){
    	var form = document.forms[0];
    	
    	form.idEmpresa.value = "";
    	form.descricaoEmpresa.value = "";
    }
    function validarForm(form){
		form.submit();
    }
    
    
</script>
</head>

<body leftmargin="0" topmargin="0" onload="window.focus();resizePageSemLink(480, 305);setarFoco('${requestScope.nomeCampo}');">
<html:form
  action="/pesquisarEmpresaAction"
  method="post"
  onsubmit="return validatePesquisarActionForm(this)"
>
<table width="445" border="0" cellspacing="5" cellpadding="0">
  <tr>
    <td width="445" valign="top" class="centercoltext">
    <table height="100%">
        <tr>
          <td></td>
        </tr>
      </table>
      <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td width="11"><img  border="0" src="<bean:message key="caminho.imagens"/>parahead_left.gif" /></td>
          <td class="parabg">Pesquisar Empresa</td>
          <td width="11"><img  border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
        </tr>
      </table>
      <p>&nbsp;</p>
      <table width="100%" border="0">
        <tr>
          <td colspan="4">Preencha os campos para pesquisar uma empresa:</td>
        	<td align="right"><a href="javascript: abrirPopupHelp('/gsan/help/help.jsp?mapIDHelpSet=localidadePesquisar', 500, 700);"><span style="font-weight: bold"><font color="#3165CE">Ajuda</font></span></a></td>
    	</tr>
      </table>
    
      <table width="100%" border="0">
        <tr>
          <td width="14%"><strong>Código:</strong></td>
          <td width="86%" colspan="3"><html:text maxlength="3" property="idEmpresa" size="3" tabindex="1"/></td>
        </tr>
        <tr>
          <td height="0"><strong>Descricao:</strong></td>
          <td colspan="3"><html:text maxlength="30" property="descricaoEmpresa" size="35" tabindex="2"/>
          </td>
        </tr>
        <tr>
			<td>&nbsp;</td>
			<td><html:radio property="tipoPesquisa"
					value="<%=ConstantesSistema.TIPO_PESQUISA_INICIAL.toString()%>" />
				Iniciando pelo texto<html:radio property="tipoPesquisa"
					tabindex="5"
					value="<%=ConstantesSistema.TIPO_PESQUISA_COMPLETA.toString()%>" />
				Contendo o texto</td>
			</tr>
        <tr>
          <td>&nbsp;</td>
          <td colspan="3">&nbsp;</td>
        </tr>
        <tr>
          <td height="24" colspan="3" width="80%">
          	<INPUT TYPE="button" class="bottonRightCol" value="Limpar" onclick="document.forms[0].reset();limparForm();"/>
          	&nbsp;&nbsp;
          	<logic:present name="caminhoRetornoTelaPesquisaEmpresa">
          		<INPUT TYPE="button" class="bottonRightCol" value="Voltar" onclick="redirecionarSubmit('${caminhoRetornoTelaPesquisaLocalidade}.do')"/>
          	</logic:present>
          </td>
          <td align="right">
           <INPUT TYPE="submit" class="bottonRightCol" value="Pesquisar"/> </td>
        </tr>
      </table>
	</td>
  </tr>
</table>
</body>
</html:form>
</html:html>
