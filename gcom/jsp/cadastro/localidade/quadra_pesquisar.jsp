<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<%@ page import="gcom.util.ConstantesSistema" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>
<title>GSAN - Sistema Integrado de Gest&atilde;o de Servi&ccedil;os de Saneamento</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="PesquisarActionForm" dynamicJavascript="false" />

<script>

 var bCancel = false;

    function validatePesquisarActionForm(form) {
        if (bCancel)
      return true;
        else
       return validateCaracterEspecial(form) && testarCampoValorZero(document.forms[0].numeroQuadra, 'Número quadra')
       && validateLong(form);
   }

    function caracteresespeciais () {
     this.ab = new Array("nomeBairro", "Bairro possui caracteres especiais.", new Function ("varName", " return this[varName];"));
    }

    function IntegerValidations () {
     this.aa = new Array("numeroQuadra", "Número quadra deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
    }
    
    function receberRota(codigoRota,destino) {
 	  var form = document.forms[0];
 	  
 	  form.idRota.value = codigoRota;
 	  form.action = 'exibirPesquisarQuadraAction.do';
 	  submeterFormPadrao(form);
	}
	
	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
	
		var form = document.PesquisarActionForm;
		if (tipoConsulta == 'rota') {
			form.idRota.value = codigoRegistro;
		
			form.action = 'exibirPesquisarQuadraAction.do';
			submeterFormPadrao(form);
		}
	}
	
	function limparRota() {
		var form = document.forms[0];
		
		form.idRota.value = '';
		form.codigoRota.value = '';
	}

</script>

</head>

<body leftmargin="0" topmargin="0" onload="resizePageSemLink(480, 280);setarFoco('${requestScope.nomeCampo}');">
<html:form
  action="/pesquisarQuadraAction"
  method="post"
  onsubmit="return validatePesquisarActionForm(this);"
>
<table width="452 " border="0" cellspacing="5" cellpadding="0">
  <tr>
    <td width="452" valign="top" class="centercoltext">
    <table height="100%">
        <tr>
          <td></td>
        </tr>
      </table>
      <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td width="11"><img  border="0" src="<bean:message key="caminho.imagens"/>parahead_left.gif" /></td>
          <td class="parabg">Pesquisar Quadra</td>
          <td width="11"><img  border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
        </tr>
      </table>
      <p>&nbsp;</p>
      <table width="100%" border="0">
        <tr>
          <td colspan="4">Preencha os campos para pesquisar uma quadra:</td>
        </tr>
        <tr>
          <td width="14%"><strong>Número Quadra:</strong></td>
          <td width="86%" colspan="3"><html:text maxlength="4" property="numeroQuadra" size="3" tabindex="1"/></td>
        </tr>
        <tr>
          <td height="0"><strong>Bairro:</strong></td>
          <td colspan="3"><html:text maxlength="30" property="nomeBairro" size="30" tabindex="2"/>
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
        <tr>
		<td height="24"><strong>Rota:</strong></td>
					<td>
					<html:hidden property="idRota"/>
					<html:text maxlength="4" size="4" property="codigoRota" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" />
					<a href="javascript:abrirPopup('exibirPesquisarInformarRotaLeituraAction.do?caminhoRetornoTelaPesquisa=exibirPesquisarQuadraAction&limparForm=sim', 400, 800);">
			<img border="0" src="<bean:message key="caminho.imagens"/>pesquisa.gif" border="0"/></a> 
			<a href="javascript:limparRota()"> <img
						src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar" /></a>
		</td>
	</tr>
        <tr>
          <td>&nbsp;</td>
          <td colspan="3">&nbsp;</td>
        </tr>
        <tr>
          <td height="24" colspan="3" width="80%">
          	<INPUT TYPE="button" class="bottonRightCol" value="Limpar" onclick="document.forms[0].reset();"/>
          	&nbsp;&nbsp;
          	<logic:present name="caminhoRetornoTelaPesquisaQuadra">
          		<INPUT TYPE="button" class="bottonRightCol" value="Voltar" onclick="redirecionarSubmit('${caminhoRetornoTelaPesquisaQuadra}.do')"/>
          	</logic:present>
          </td>
          <td align="right"><INPUT TYPE="submit" class="bottonRightCol" value="Pesquisar"/></td>
        </tr>
      </table>
      <p>&nbsp;</p></td>
  </tr>
</table>
</body>
</html:form>
</html:html>