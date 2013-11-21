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
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false"  formName="FiltrarGuiaPagamentoEmAtrasoActionForm" />
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js" ></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js" ></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript">
<!-- Begin

function chamarFiltrar(){
  var form = document.forms[0];
  if (validateFiltrarGuiaPagamentoEmAtrasoActionForm(form)) {
	  	form.submit();
  }
}
-->
</script>
</head>

<body leftmargin="5" topmargin="5">
<html:form
  action="/filtrarGuiaPagamentoEmAtrasoAction.do"
  name="FiltrarGuiaPagamentoEmAtrasoActionForm"
  type="gcom.gui.arrecadacao.pagamento.FiltrarGuiaPagamentoEmAtrasoActionForm" method="post"
  onsubmit="return validateFiltrarGuiaPagamentoEmAtrasoActionForm(this);"
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

          <td class="parabg">Filtrar Guia de Pagamento em Atraso</td>
          <td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif"/></td>
        </tr>
      </table>
      <p>&nbsp;</p>
	  <table width="100%" border="0">
        <tr>
        	<td width="100%" colspan=2>
	        	<table width="100%" border="0">
		          	<tr>
		          		<td>Para filtrar a(s) Guia(s) de pagamento, informe os dados abaixo:</td>
		          	</tr>
		          </table>
        	</td>
        </tr>
        <tr>
          <td width="10%"><strong>Financiamento:</strong> </td>
          <td width="90%"><html:select property="financiamentoTipoId">
          		<html:option value="-1">&nbsp;</html:option>
              <html:options collection="colecaoFinancimentoTipo" labelProperty="descricao" property="id"/>
             </html:select>
          </td>
        </tr>
        
        <tr>
          <td width="10%"><strong>Data inicial de vencimento:</strong><font color="#FF0000">*</font></td>
          <td width="90%">
          	<html:text property="vencimentoInicial" size="10" maxlength="10"
          		onkeyup="javascript:mascaraData(this, event);"/>
						<a href="javascript:abrirCalendario('FiltrarGuiaPagamentoEmAtrasoActionForm', 'vencimentoInicial')">
							<img border="0" src="<bean:message key="caminho.imagens"/>calendario.gif"
								width="20" border="0" align="absmiddle" alt="Exibir Calendário" /></a>
						<font size="2">dd/mm/aaaa</font>
          </td>
        </tr>
        <tr>
          <td width="10%"><strong>Data final de vencimento:</strong><font color="#FF0000">*</font></td>
          <td width="90%">
	          <html:text property="vencimentoFinal" size="10" maxlength="10"
    	      	onkeyup="javascript:mascaraData(this, event);"/>
						<a href="javascript:abrirCalendario('FiltrarGuiaPagamentoEmAtrasoActionForm', 'vencimentoFinal')">
							<img border="0" src="<bean:message key="caminho.imagens"/>calendario.gif"
								width="20" border="0" align="absmiddle" alt="Exibir Calendário" /></a>
						<font size="2">dd/mm/aaaa</font>
          </td>
        </tr>
        
        <tr>
          <td width="10%"><strong>Referência inicial contábil:</strong></td>
          <td width="90%">
          	<html:text property="referenciaInicialContabil" size="7"  maxlength="7" onkeypress="javascript:mascaraAnoMes(this, event);"/>
          	&nbsp;mm/aaaa
          </td>
        </tr>
        <tr>
          <td width="10%"><strong>Referência final contábil:</strong></td>
          <td width="90%">
          	<html:text property="referenciaFinalContabil" size="7"  maxlength="7" onkeypress="javascript:mascaraAnoMes(this, event);"/>
          	&nbsp;mm/aaaa
          </td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
        </tr>
        <tr>
			<td>&nbsp;</td>
			<td align="left"><font color="#FF0000">*</font> Campo Obrigatório</td>
		</tr>
        <tr>
          <td>
          	<input type="button" name="Button" class="bottonRightCol" value="Limpar" onclick="window.location.href='<html:rewrite page="/exibirFiltrarGuiaPagamentoEmAtrasoAction.do?menu=sim"/>'">
          </td>
          <td><div align="right">
          <gsan:controleAcessoBotao name="Button" value="Filtrar"
							  onclick="javascript:chamarFiltrar();" url="filtrarGuiaPagamentoEmAtrasoAction.do"/>
                   	<%--
          <input type="button" name="Button" class="bottonRightCol" value="Filtrar" onClick="javascript:chamarFiltrar();"/>--%></div></td>
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