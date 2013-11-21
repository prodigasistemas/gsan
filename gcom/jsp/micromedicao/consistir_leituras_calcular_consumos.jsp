<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<html:html>
<head>
<title>Compesa - SGCQ</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js" ></script>



<script language="JavaScript">

function confirmacao(pergunta,caminhoAction) {
  if (confirm(pergunta)) {
    var form = document.forms[0];
    form.action = caminhoAction;
    form.submit();
  } else {
    return false;
  }
    return true;
}

</script>

</head>

<logic:notPresent name="confirmacao" scope="request">
<body leftmargin="5" topmargin="5">
</logic:notPresent>

<logic:present name="confirmacao" scope="request">
<body leftmargin="5" topmargin="5" onLoad="javascript:return confirmacao('Leituras ainda não registradas para o Grupo de Faturamento. Deseja prosseguir?','consistirLeiturasCalcularConsumosAction.do');" >
</logic:present>


<html:form action="/exibirConsistirLeiturasCalcularConsumosAction"
            name="ConsistirLeiturasCalcularConsumosActionForm"
            type="gcom.gui.micromedicao.ConsistirLeiturasCalcularConsumosActionForm"
            method="post">

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
    <td width="625" valign="top" class="centercoltext">
      <table height="100%">
        <tr>
          <td></td>
        </tr>
      </table>
      <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_left.gif"/></td>
          <td class="parabg">Consistir Leituras e Calcular Consumos</td>
          <td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif"/></td>
        </tr>
      </table>
      <p>&nbsp;</p>
            <table width="100%" border="0" dwcopytype="CopyTableRow">
                <tr>
                  <td colspan="2">Para consistir leituras e calcular consumos selecione o grupo de faturamento e clique em calcular.</td>
                </tr>
                <tr>
                  <td width="25%"><strong>Grupo de Faturamento:<font color="#FF0000">*</font></strong></td>
                  <td width="75%">
                    <html:select property="idFaturamentoGrupo">
                      <html:options collection="faturamentoGrupos" labelProperty="descricao" property="id"/>
                    </html:select> </td>
                </tr>

  <tr>
                  <td><strong></strong></td>
                  <td><strong><font color="#FF0000">*</font></strong> Campos obrigat&oacute;rios</td>
                </tr>
              </table>
		   <table width="100%"> <tr>
                  <td width="100%">
                    <table border="0" cellpadding="0" cellspacing="2">
                      <tr>
                        <td>
                              <html:button styleClass="bottonRightCol" value="Calcular" property="Button2" onclick="javascript:return redirecionarSubmit('exibirConsistirLeiturasCalcularConsumosAction.do?sim=sim');"/>
                         </td>
                      </tr>
                    </table>
            </table>
      <p>&nbsp;</p>
    </td>
  </tr>
</table>

<%@ include file="/jsp/util/rodape.jsp"%>

</body>
</html:form>
</html:html>





