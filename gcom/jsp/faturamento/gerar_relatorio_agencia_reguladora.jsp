<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan" %>

<%@page isELIgnored="false"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>

<script language="JavaScript">
  function validarForm(){
     var form = document.forms[0];
     form.submit();
  }
  function limparMunicipio() {
    var form = document.forms[0];
    form.action = 'filtrarRelatorioAgenciaReguladoraAction.do?limparForm=OK'
    form.submit();
    }
</script>

</head>
<body leftmargin="5" topmargin="5" onload="javascript:setarFoco('${requestScope.nomeCampo}');">

<html:form action="/gerarRelatorioAgenciaReguladoraAction.do"
  name="GerarRelatorioAgenciaReguladoraActionForm"
  type="gcom.gui.faturamento.GerarRelatorioAgenciaReguladoraActionForm"
  method="post">

<%@ include file="/jsp/util/cabecalho.jsp"%> 
<%@ include file="/jsp/util/menu.jsp"%>

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
    </div>
    </td>


    <td valign="top" class="centercoltext">
    <table>
      <tr>
        <td></td>
      </tr>
    </table>
    <table width="100%" border="0" align="center" cellpadding="0"
      cellspacing="0">
      <tr>
        <td width="11"><img border="0"
          src="<bean:message key="caminho.imagens"/>parahead_left.gif" /></td>
        <td class="parabg">Gerar Relatório Agência Reguladora</td>
        <td width="11" valign="top"><img border="0"
          src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
      </tr>
    </table>

    <p>&nbsp;</p>
    <table width="100%" border="0">

      <tr>
        <td colspan="2">Para gerar relatório para agência reguladora, informe os dados abaixo:</td>
      </tr>

      <tr>
        <td width="30%"><strong>Mês/Ano referência:<font color="#FF0000">*</font></strong></td>
            <td width="70%">
              <html:text property="mesAno" size="7"  maxlength="7" onkeypress="javascript:mascaraAnoMes(this, event);"/>
              &nbsp;mm/aaaa
          </td>
      </tr>
      
      <tr>
        <td><strong>Agência reguladora:<font color="#FF0000">*</font></strong></td>
        <td colspan="2" align="left">
          <html:select property="idAgenciaReguladora" tabindex="3" >
            <html:option value="-1">&nbsp;</html:option>
            <html:options collection="colecaoAgencias"
              labelProperty="nome" property="id" />
          </html:select>
        </td>
      </tr>
      
      <tr>
        <td><strong>Tipo relatório:<font color="#FF0000">*</font></strong></td>
        <td>
          <html:radio property="tipoRelatorio"
            value="1" onchange="verificarSelecaoIndicadorCpfCnpj(this);" />
          PDF
          <html:radio property="tipoRelatorio"
            value="2" onchange="verificarSelecaoIndicadorCpfCnpj(this);"/>
          XLS
        </td>
      </tr>
      
      <tr>
        <td>&nbsp;</td>
        <td align="left"><font color="#FF0000">*</font> Campo
        Obrigat&oacute;rio</td>
      </tr>
    </table>

    <table width="100%">
      <tr>
        <td align="left">
            
          <input type="button"
            name="ButtonCancelar" 
            class="bottonRightCol" 
            value="Cancelar"
            onClick="javascript:window.location.href='/gsan/telaPrincipal.do'">
        </td>
        
        <td align="right">
          <gsan:controleAcessoBotao name="Button" 
              value="Gerar" 
              onclick="javascript:validarForm();" 
              url="gerarRelatorioAgenciaReguladoraAction.do"/>
            
        </td>
      </tr>
    </table>
  </tr>


</table>
<%@ include file="/jsp/util/rodape.jsp"%></html:form>
</body>
</html:html>
