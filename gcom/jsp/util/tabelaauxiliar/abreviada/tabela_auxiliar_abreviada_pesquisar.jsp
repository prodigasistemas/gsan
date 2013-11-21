<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="gcom.gui.util.tabelaauxiliar.DadosTelaTabelaAuxiliar" isELIgnored="false" %>
<%@ page import="gcom.util.ConstantesSistema"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>
<title>GSAN - Sistema Integrado de Gest&atilde;o de Servi&ccedil;os de Saneamento</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false"  formName="PesquisarActionForm" dynamicJavascript="false" />
<script language="JavaScript">
window.onmousemove = resizePageSemLink(495, 300);

var bCancel = false; 

function validatePesquisarActionForm(form) {                                                                   
	if (bCancel) 
		return true; 	
	else 
		return validateCaracterEspecial(form) && validateLong(form); 
} 

function caracteresespeciais () { 
	this.aa = new Array("id", "Código possui caracteres especiais.", new Function ("varName", " return this[varName];"));
	this.ab = new Array("descricao", "Descrição possui caracteres especiais.", new Function ("varName", " return this[varName];"));
	this.ac = new Array("descricaoAbreviada", "Descrição Abreviada possui caracteres especiais.", new Function ("varName", " return this[varName];"));
} 

function IntegerValidations () { 
	this.aa = new Array("id", "Código deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
} 

function limparForm(){
 	var form = document.forms[0];
 	form.id.value = "";
 	form.descricao.value = "";
	form.descricaoAbreviada.value = "";

	form.tipoPesquisaDescricao[0].checked = true;
}


</script>
</head>

<body leftmargin="5" topmargin="5" onload="window.focus();resizePageSemLink(495, 335); setarFoco('${requestScope.nomeCampo}');">
<html:form
  action="/pesquisarTabelaAuxiliarAbreviadaAction"
  method="post"
  onsubmit="return validatePesquisarActionForm(this);"
>

<input type="hidden" name="tipoPesquisa" value="${requestScope.tipoPesquisa}"/>
<input type="hidden" name="caminhoRetorno" value="${requestScope.caminhoRetorno}"/>

<table width="452" border="0" cellspacing="5" cellpadding="0">
  <tr>
    <td width="452" valign="top" class="centercoltext"> <table height="100%">
        <tr>
          <td></td>
        </tr>
      </table>
      <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td width="11"><img  border="0" src="<bean:message key="caminho.imagens"/>parahead_left.gif" /></td>
          <td class="parabg">Pesquisar <bean:write name="dados" property="titulo" scope="session"/></td>
          <td width="11"><img  border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
        </tr>
      </table>
      <p>&nbsp;</p>
      <table width="100%" border="0">
        <tr>
          <td colspan="2">Preencha os campos para pesquisar um <%=(((DadosTelaTabelaAuxiliar) session.getAttribute("dados")).getTitulo()  ).toLowerCase()%>:</td>
          <td align="right"><a href="javascript: abrirPopupHelp('/gsan/help/help.jsp?mapIDHelpSet=<%=((DadosTelaTabelaAuxiliar) session.getAttribute("dados")).getNomeParametroFuncionalidade() %>', 500, 700);"><span style="font-weight: bold"><font color="#3165CE">Ajuda</font></span></a></td>
        </tr>
      </table>
      
      <table width="100%" border="0">
        <tr>
          <td width="30%"><strong>C&oacute;digo:</strong></td>
          <td width="70%"><html:text maxlength="4" property="id" size="4"/> </td>
        </tr>
        <tr>
          <td><strong>Descrição:</strong></td>
          <td><html:text maxlength="30" property="descricao" size="30"/>
          </td>
        </tr>
        <tr>
			<td></td>
			<td>
				<html:radio property="tipoPesquisaDescricao"
					value="<%=ConstantesSistema.TIPO_PESQUISA_INICIAL.toString()%>" />
			Iniciando pelo texto<html:radio property="tipoPesquisaDescricao"
				tabindex="5"
				value="<%=ConstantesSistema.TIPO_PESQUISA_COMPLETA.toString()%>" />
			Contendo o texto</td>
		</tr>
		<tr>
          <td><strong>Descrição Abreviada:</strong></td>
          <td><html:text maxlength="5" property="descricaoAbreviada" size="8"/></td>
        </tr>
     </table>
     
     <table width="100%" border="0">
    <tr>
		<td height="24">
			<input type="button" class="bottonRightCol" value="Limpar" onClick="javascript:limparForm();" style="width: 80px" />
			&nbsp;&nbsp;
			<logic:present name="caminhoRetorno">
				<INPUT TYPE="button" class="bottonRightCol" value="Voltar" onclick="redirecionarSubmit('${caminhoRetorno}.do')" style="width: 80px"/>
			</logic:present>
		</td>
			<td align="right">
				<html:submit styleClass="bottonRightCol" value="Pesquisar" style="width: 80px"/>
		</td>
	 </tr>
     </table>
      
      
     <p>&nbsp;</p></td>
  </tr>
</table>
</html:form>
</body>
</html:html>
