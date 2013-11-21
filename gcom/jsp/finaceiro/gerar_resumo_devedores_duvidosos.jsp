<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan" %>

<%@ page import="gcom.util.Pagina, gcom.util.ConstantesSistema" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
<html:javascript staticJavascript="false"  formName="GerarResumoDevedoresDuvidososActionForm" />

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js" ></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js" ></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js"></script>

<script language="JavaScript">
<!-- Begin

    
function chamarGerar(){
  var form = document.forms[0];
	  if (validateGerarResumoDevedoresDuvidososActionForm(form)) {
	  	form.submit();
	  }
}
-->
</script>
</head>

<body leftmargin="5" topmargin="5">
<html:form
  action="/gerarResumoDevedoresDuvidososAction.do"
  method="post"
  onsubmit="return validateGerarResumoDevedoresDuvidososActionForm(this);"
  type="gcom.gui.financeiro.GerarResumoDevedoresDuvidososActionForm"
>

<%@ include file="/jsp/util/cabecalho.jsp"%>
<%@ include file="/jsp/util/menu.jsp" %>

<table width="770" border="0" cellspacing="5" cellpadding="0">
  <tr>
    <td width="130" valign="top" class="leftcoltext">
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
      </div>
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
          <td class="parabg">Gerar Resumo dos Devedores Duvidosos</td>
          <td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif"/></td>
        </tr>
      </table>
      
      <p>&nbsp;</p>
	  
	  <table width="100%" border="0">
        <tr>
          <td width="100%" colspan=2>
	       	<table>
		      <tr>
		        <td width="505px">Para gerar o resumo dos devedores duvidosos, informe os dados abaixo:</td>
		      </tr>
		    </table>
          </td>
        </tr>
        
        <tr>
          <td align="right"><strong>Referência Contábil:</strong><font color="#FF0000">*</font></td>
          <td>
            <div align="left">
          	  <html:text property="anoMesReferenciaContabil" size="7"  maxlength="7" onkeypress="javascript:mascaraAnoMes(this, event);"/>&nbsp;mm/aaaa
          	</div>
          </td>
        </tr>
        
        <tr>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
        </tr>
        
        <tr>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
        </tr>
        
        <tr>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
        </tr>
        
        <tr>
          <td colspan="2" class="style1">
			<input type="button" name="Button" class="bottonRightCol" value="Desfazer" onclick="window.location.href='<html:rewrite page="/exibirGerarResumoDevedoresDuvidososAction.do?menu=sim"/>'">
			<input name="Submit23" class="bottonRightCol" value="Cancelar" type="button" onclick="window.location.href='/gsan/telaPrincipal.do'">
          </td>
          
          <td>
            <div align="right">
              <gsan:controleAcessoBotao name="Button" value="Gerar" onclick="chamarGerar();" url="gerarResumoDevedoresDuvidososAction.do"/>
            </div>
          </td>
        </tr>
        
      </table>
      <p>&nbsp;</p>
	</td>
  </tr>
</table>

<%@ include file="/jsp/util/rodape.jsp"%>

</html:form>
</body>
</html:html>