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

</head>

<body leftmargin="5" topmargin="5">

<html:form
	action="/filtrarTabelaAuxiliarTipoAction"
	method="post"
>

<%@ include file="/jsp/util/cabecalho.jsp"%>
<%@ include file="/jsp/util/menu.jsp" %>

<table width="770" border="0" cellspacing="5" cellpadding="0">
  <tr>
    <td width="150" valign="top" class="leftcoltext">
       	<%@ include file="/jsp/util/informacoes_usuario.jsp"%>
    </td>
    <td width="625" valign="top" class="centercoltext">
      <table>
        <tr>
          <td></td>
        </tr>
      </table>
      <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_left.gif" editor="Webstyle4"/></td>
          <td class="parabg">Filtro de <bean:write name="titulo" scope="session"/> </td>
          <td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif" editor="Webstyle4"/></td>
        </tr>
      </table>
      <p>&nbsp;</p>
  	  <table width="100%" border="0">
        <tr>
          <td colspan="2">Preencha os campos para pesquisar um(a) <%=((String) session.getAttribute("titulo")).toLowerCase()%>:</td>
        </tr>
        <tr>
          <td><strong>C&oacute;digo:</strong></td>
          <td><html:text property="id" maxlength="4" size="4" /><font size="1">&nbsp;(somente números)</font>
            <br><font color="red"><html:errors property="id"/></font>
          </td>
        </tr>
        <tr>
          <td><strong>Descri&ccedil;&atilde;o:</strong></td>
          <td><html:text property="descricao" maxlength="60" size="60"/>
	          <br><font color="red"><html:errors property="descricao" /></font>
          </td>
        </tr>
        <tr>
          <td><strong><%=((String) session.getAttribute("tituloTipo"))%>:</strong></td>
          <td>
            <html:select property="tipo">
              <html:option value="-1" >&nbsp;</html:option>
              <html:options collection="tipos" labelProperty="descricao" property="id"/>
            </html:select>
          </td>
        </tr>
        <tr>
          <td height="24">&nbsp;</td>
          <td>&nbsp;</td>
        </tr>
        <tr>
          <td height="0"><html:submit styleClass="bottonRightCol" value="Filtrar" property="Button"/></td>
          <td>
            <div align="right">
            </div>
          </td>
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
