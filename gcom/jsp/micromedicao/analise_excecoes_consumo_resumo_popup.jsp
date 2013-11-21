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
</script>
</head>

<body leftmargin="5" topmargin="5">
<table width="635" border="0" cellspacing="5" cellpadding="0">
  <tr> 
    <td width="625" valign="top" class="centercoltext"> <table height="100%">

        <tr> 
          <td></td>
        </tr>
      </table>
      <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr> 
          <td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_left.gif" /></td>
          <td class="parabg">Consultar Cliente e Im&oacute;vel</td>

          <td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>

        </tr>
      </table>
      <p>&nbsp;</p>
      <table width="100%" border="0">
        <tr> 
          <td><table width="100%" bgcolor="#99CCFF" border="0">
              <tr bgcolor="#79BBFD"> 
                <td>
                  <div align="center"><strong>Cliente</strong></div>
                </td>
                <td>
    	              <div align="center"><strong>Tipo</strong></div>
                </td>
              </tr>
              <tr> 
                <td bgcolor="#ffffff"> <div align="center">${nomeCliente}</div></td>
                <td bgcolor="#ffffff"> <div align="center">Usuário</div></td>
              </tr>
            </table></td>
        </tr>

        <tr> 
          <td colspan="4"><table width="100%" bgcolor="#99CCFF" border="0">
              <tr bgcolor="#79BBFD"> 
                <td><strong>
                  <div align="center">Endere&ccedil;o</div>
                  </strong></td>
              </tr>
              <tr> 
                <td bgcolor="#ffffff"> <div align="center">${imovelEndereco}</div></td>

              </tr>
            </table></td>
        </tr>
        <tr> 
          <td>&nbsp;</td>
        </tr>
        <tr> 
          <td align="right"><input name="Button" type="button" class="bottonRightCol" align="right" value="Fechar" onClick="window.close();"></td>

        </tr>
      </table>

      <p>&nbsp;</p></td>
  </tr>
</table>
</body>
</html:html>
