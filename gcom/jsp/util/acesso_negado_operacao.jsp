<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>

<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
</head>

<body leftmargin="5" topmargin="5">

<%@ include file="/jsp/util/cabecalho.jsp"%>

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
          <td class="parabg">Segurança</td>
          <td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
        </tr>
      </table>
	  <table width="100%" border="0">
        <tr>
          <td width="9%" align="center">
            <div align="right">
              <img src="<bean:message key="caminho.imagens"/>Error.gif"/>
            </div>
          </td>
          <td width="60%" align="center">
            <div align="left">
              <font>
                <strong>
                  Acesso a operação negado: <%=""+request.getAttribute("URL") %><br>
                </strong>
              </font>
            </div>
          </td>
	      <td width="*">&nbsp;</td>
        </tr>
        <tr>
	      <td width="6%" align="left">
            <strong><a href="javascript:history.back();">Voltar</a></strong>
          </td>
          <td align="center">&nbsp;</td>

      	  <td width="22%" align="right">
            &nbsp;
      	  </td>
        </tr>
      </table>
    </td>
  </tr>
</table>
<p>&nbsp;</p>
<p>&nbsp;</p>    
<%@ include file="/jsp/util/rodape.jsp"%>

</body>

</html:html>
