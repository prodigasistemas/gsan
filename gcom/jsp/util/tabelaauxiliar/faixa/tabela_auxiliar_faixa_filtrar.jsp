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
<link rel="stylesheet" href = "<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">

</head>

<body leftmargin="5" topmargin="5">
<html:form
  action="/filtrarTabelaAuxiliarFaixaAction"
  method="post"
>

<%@ include file="/jsp/util/cabecalho.jsp"%>
<%@ include file="/jsp/util/menu.jsp" %>

<table width="770" border="0" cellspacing="5" cellpadding="0">
  <tr>
    <td width="150" valign="top" class="leftcoltext">
      <div align="center">
       <%@ include file="/jsp/util/informacoes_usuario.jsp"%>
      </div></td>
    <td width="625" valign="top" class="centercoltext">
      <table>
        <tr>
          <td></td>
        </tr>
      </table>
      <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td width="11"><img src="<bean:message key="caminho.imagens"/>parahead_left.gif" border="0"/></td>
          <td class="parabg">Filtro de <bean:write name="titulo" scope="session"/></td>

          <td width="11"><img src="<bean:message key="caminho.imagens"/>parahead_right.gif" border="0"/></td>
        </tr>
      </table>
      <p>&nbsp;</p>
      <table width="100%" border="0">
        <tr>
          <td colspan="2">Preencha os campos para pesquisar um(a) <%=((String) session.getAttribute("titulo")).toLowerCase()%>:</td>
        </tr>
        <tr>
          <td><strong>C&oacute;digo:</strong></td>
          <td>
            <html:text property="id"/><font size="1">&nbsp;(somente números)</font>
	    <br><font color="red"><html:errors property="id"/></font>
          </td>
        </tr>
        <tr>
          <td width="19%" ><strong>Faixa Inicial:</strong></td>
          <td width="81%">
 <input type"text" name="faixaInicial" maxlength="<bean:write name="tamMaxCampoFaixaInicial" scope="session"/>" size="<bean:write name="tamMaxCampoFaixaInicial" scope="session"/>"/>

            <br><font color="red"><html:errors property="faixaInicial"/></font>
          </td>
        </tr>
        <tr>
          <td width="19%" ><strong>Faixa Final:</strong></td>
          <td width="81%">
 <input type"text" name="faixaFinal" maxlength="<bean:write name="tamMaxCampoFaixaFinal" scope="session"/>" size="<bean:write name="tamMaxCampoFaixaFinal" scope="session"/>"/>
            <br><font color="red"><html:errors property="faixaFinal"/></font>
          </td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
        </tr>
        <tr>
          <td ><html:submit styleClass="bottonRightCol" value="Filtrar" /></td>
          <td>&nbsp;</td>
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
