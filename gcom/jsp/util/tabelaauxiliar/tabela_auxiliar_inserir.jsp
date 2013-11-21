<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/menus-taglib.tld" prefix="menu" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>

<head>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="TabelaAuxiliarActionForm"/>
<script language="JavaScript">

function limpar(){
	var form = document.forms[0];
	form.descricao.value = '';
}

</script>

</head>

<body leftmargin="5" topmargin="5">
<html:form
  action="/inserirTabelaAuxiliarAction?tela=${requestScope.tela}"
  method="post"
  name="TabelaAuxiliarActionForm"
  type="gcom.gui.util.tabelaauxiliar.TabelaAuxiliarActionForm"
  onsubmit="return validateTabelaAuxiliarActionForm(this);"
>

<%@ include file="/jsp/util/cabecalho.jsp"%>
<%@ include file="/jsp/util/menu.jsp" %>

<table width="770" border="0" cellspacing="5" cellpadding="0">
  <tr>
    <td width="150" valign="top" class="leftcoltext">
      <%@ include file="/jsp/util/informacoes_usuario.jsp"%>
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
          <td class="parabg">Inserir <bean:write name="titulo" scope="session"/> </td>
          <td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif"/></td>
        </tr>
      </table>
      <p>&nbsp;</p>
  	  <table width="100%" border="0">
        <tr>
          <td colspan="3">Para inserir um(a) <%=((String) session.getAttribute("titulo")).toLowerCase()%>, informe os dados abaixo:</td>
        </tr>
        <tr>
          <td width="25%" height="0"><strong><bean:write name="descricao" scope="request"/>:<font color="#FF0000">*</font></strong></td>
            <td colspan="2"><input type="text" name="descricao" size="<bean:write name="tamanhoMaximoCampo" scope="request"/>" maxlength="<bean:write name="tamanhoMaximoCampo" scope="request"/>">
          </td>
        </tr>
        <tr>
          <td height="0">&nbsp;</td>
          <td colspan="2"><strong><font color="#FF0000">*</font></strong> Campos obrigat&oacute;rios</td>
        </tr>
        <tr>
          <td height="0">&nbsp;</td>
          <td colspan="2">&nbsp;</td>
        </tr>
        <tr>
		<td height="0" align="left">
			<input name="Button" type="button"
				class="bottonRightCol" value="Limpar" align="left"
				onclick="javascript:limpar();">	
			<input type="button" name="ButtonCancelar" class="bottonRightCol"
				value="Cancelar"
				onClick="javascript:window.location.href='/gsan/telaPrincipal.do'">
		</td>						
          <td height="0" align="right"><html:submit styleClass="bottonRightCol" value="Inserir" /></td>
          <td><div align="right"></div></td>
        </tr>
         <tr>
          <td height="0">&nbsp;</td>
          <td colspan="2">&nbsp;</td>
        </tr>
         <tr>
          <td height="0">&nbsp;</td>
          <td colspan="2">&nbsp;</td>
        </tr>
         <tr>
          <td height="0">&nbsp;</td>
          <td colspan="2">&nbsp;</td>
        </tr>
         <tr>
          <td height="0">&nbsp;</td>
          <td colspan="2">&nbsp;</td>
        </tr>
         <tr>
          <td height="0">&nbsp;</td>
          <td colspan="2">&nbsp;</td>
        </tr> <tr>
          <td height="0">&nbsp;</td>
          <td colspan="2">&nbsp;</td>
        </tr>
         <tr>
          <td height="0">&nbsp;</td>
          <td colspan="2">&nbsp;</td>
        </tr>
         <tr>
          <td height="0">&nbsp;</td>
          <td colspan="2">&nbsp;</td>
        </tr>
         <tr>
          <td height="0">&nbsp;</td>
          <td colspan="2">&nbsp;</td>
        </tr>
         <tr>
          <td height="0">&nbsp;</td>
          <td colspan="2">&nbsp;</td>
        </tr>
      </table>
      <p>&nbsp;</p>
    </td>
  </tr>
</table>
<%@ include file="/jsp/util/rodape.jsp"%>

</body>
</html:form>
</html:html>