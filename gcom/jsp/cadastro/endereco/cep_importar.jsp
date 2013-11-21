<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
<script language="JavaScript">
<!-- Begin

function validarForm(form){
	form.submit();
}

-->
</script>

</head>
<body leftmargin="5" topmargin="5">

<form action="/gsan/importarCepAction.do"
           method="post"
           enctype="multipart/form-data"
>

<%@ include file="/jsp/util/cabecalho.jsp"%>
<%@ include file="/jsp/util/menu.jsp" %>

<table width="770" border="0" cellspacing="5" cellpadding="0">
  <tr>
    <td width="115" valign="top" class="leftcoltext">
      <div align="center">

        <p align="left">&nbsp;</p>
        <p align="left">&nbsp;</p>
        <p align="left">&nbsp;</p>

       <%@ include file="/jsp/util/informacoes_usuario.jsp"%>

        <p align="left">&nbsp;</p>
        <p align="left">&nbsp;</p>
        <p align="left">&nbsp;</p>
        <p align="left">&nbsp;</p>
        <p align="left">&nbsp;</p>
        <p align="left">&nbsp;</p>
        <p align="left">&nbsp;</p>
        <p align="left">&nbsp;</p>
        <p align="left">&nbsp;</p>
        <p align="left">&nbsp;</p>

       <%@ include file="/jsp/util/mensagens.jsp"%>

        <p align="left">&nbsp;</p>
        <p align="left">&nbsp;</p>
        <p align="left">&nbsp;</p>
        <p align="left">&nbsp;</p>
        <p align="left">&nbsp;</p>
        <p align="left">&nbsp;</p>
        <p align="left">&nbsp;</p>
      </div></td>

          <td valign="top" class="centercoltext">
            <table>
              <tr>
                <td></td>
              </tr>
            </table>
            <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
              <tr>
                <td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_left.gif"/></td>
                <td class="parabg"> Importar CEP dos Correios</td>
                <td width="11" valign="top"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif"/></td>
              </tr>
            </table>

            <p>&nbsp;</p>
            <table width="100%" border="0" >
              <tr>
                  <td colspan="2">Para importar CEP dos correios, informe o arquivo:</td>
                </tr>
                <tr>
                  <td><strong> Arquivo:<font color="#FF0000">*</font></strong>
                  </td>
                  <td><input type="file" style="textbox" name="uploadPicture" size="50"/>
                  </td>
                </tr>
                <tr>
                  <td>&nbsp;</td>
                  <td align="left"><font color="#FF0000">*</font> Campo Obrigat&oacute;rio
                  </td>
                </tr>
            </table>
            <gsan:controleAcessoBotao name="Button" value="Importar"
							  onclick="javascript:validarForm(document.forms[0]);" url="importarCepAction.do"/>
					<!--
            <html:submit  styleClass="bottonRightCol" value="Importar"/>
-->
            <table>
              <tr>
                <td width="770" align="right">&nbsp;</td>
                <td>&nbsp;</td>
              </tr>
            </table>
        </tr>


</table>
<%@ include file="/jsp/util/rodape.jsp"%>

</form>
</body>
</html:html>
