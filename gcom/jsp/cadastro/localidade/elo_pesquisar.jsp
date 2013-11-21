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


</head>

<body leftmargin="0" topmargin="0" onload="resizePageSemLink(495, 290);">
<html:form
  action="/pesquisarEloAction"
  method="post"
>
<table width="452" border="0" cellspacing="5" cellpadding="0">
  <tr>
    <td width="442" valign="top" class="centercoltext">
    <table height="100%">
        <tr>
          <td></td>
        </tr>
      </table>
      <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td width="11"><img  border="0" src="<bean:message key="caminho.imagens"/>parahead_left.gif" /></td>
          <td class="parabg">Pesquisar Elo</td>
          <td width="11"><img  border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
        </tr>
      </table>
      <p>&nbsp;</p>
      <table width="100%" border="0">
        <tr>
          <td colspan="4">Preencha os campos para pesquisar um elo:</td>
        </tr>
        <tr>
          <td height="0"><strong>Localidade Pólo:</strong></td>
          <td colspan="3"><html:text maxlength="30" property="descricao" size="30" tabindex="1"/>
          </td>
        </tr>
		<tr>
	   <td><strong>Ger&ecirc;ncia Regional:</strong></td>
		<td>
			<html:select property="idGerenciaRegional" style="width: 200px;" tabindex="2">
				<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
				<logic:iterate name="colecaoGerenciaRegional" id="colecaoGerenciaRegional">
					<html:option value="${colecaoGerenciaRegional.id}">
						<bean:write name="colecaoGerenciaRegional" property="nomeAbreviado"/> - <bean:write name="colecaoGerenciaRegional" property="nome"/>
					</html:option>
				</logic:iterate>
			</html:select>
		</td>
		</tr>
        <tr>
          <td>&nbsp;</td>
          <td colspan="3">&nbsp;</td>
        </tr>
        <tr>
          <td height="24" colspan="3" width="80%">
          	<logic:notPresent name="popup">
					<input type="button" name="Submit223"
						class="bottonRightCol" value="Fechar"
						onClick="javascript:window.close();">
			</logic:notPresent>
          	<INPUT TYPE="button" class="bottonRightCol" value="Limpar" onclick="document.forms[0].reset();"/>
          	&nbsp;&nbsp;
          	<logic:present name="caminhoRetornoTelaPesquisaElo">
          		<INPUT TYPE="button" class="bottonRightCol" value="Voltar" onclick="redirecionarSubmit('${caminhoRetornoTelaPesquisaElo}.do')"/>
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