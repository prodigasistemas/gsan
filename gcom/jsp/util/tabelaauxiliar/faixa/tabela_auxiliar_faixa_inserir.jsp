<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/menus-taglib.tld" prefix="menu" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>

<head>
<title><bean:write name="nomeSistema" scope="session"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="TabelaAuxiliarFaixaActionForm"/>
</head>

<body leftmargin="5" topmargin="5">
<html:form
  action="/inserirTabelaAuxiliarFaixaAction"
  method="post"
  name="TabelaAuxiliarFaixaActionForm"
  type="gcom.gui.util.tabelaauxiliar.faixa.TabelaAuxiliarFaixaActionForm"
  onsubmit="return validateTabelaAuxiliarFaixaActionForm(this);"
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
          <td width="15%" height="0"><strong>Faixa Inicial<font color="#FF0000">*</font></strong></td>
            <td colspan="2"><input type="text" name="faixaInicial" size="<bean:write name="tamMaxCampoFaixaInicial" scope="request"/>" maxlength="<bean:write name="tamMaxCampoFaixaInicial" scope="request"/>">
          </td>
        </tr>
        <tr>
          <td width="15%" height="0"><strong>Faixa Final<font color="#FF0000">*</font></strong></td>
            <td colspan="2"><input type="text" name="faixaFinal" size="<bean:write name="tamMaxCampoFaixaFinal" scope="request"/>" maxlength="<bean:write name="tamMaxCampoFaixaFinal" scope="request"/>">
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
        
        <tr>
          <td height="0"><html:submit styleClass="bottonRightCol" value="Inserir"/></td>
          <td height="0">&nbsp;</td>
          <td><div align="right"></div></td>
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