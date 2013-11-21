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
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="TabelaAuxiliarTipoActionForm"/>

</head>

<body leftmargin="5" topmargin="5">

<html:form
	action="/atualizarTabelaAuxiliarTipoAction"
	name="TabelaAuxiliarTipoActionForm"
	type="gcom.gui.util.tabelaauxiliar.tipo.TabelaAuxiliarTipoActionForm"
	method="post"
	onsubmit="return validateTabelaAuxiliarTipoActionForm(this);"
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
          <td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_left.gif"/></td>
          <td class="parabg">Atualizar  <bean:write name="titulo" scope="session"/> </td>
          <td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif"/></td>
        </tr>
      </table>
      <p>&nbsp;</p>
   	  <table width="100%" border="0">
        <tr>
          <td colspan="2">Para atualizar um(a) <%=((String) session.getAttribute("titulo")).toLowerCase()%>, informe os dados abaixo:</td>
        </tr>
        <tr>
         <td ><strong>Código:</strong></td>
         <td ><bean:write name="tabelaAuxiliarTipo" property="id"/></td>
        </tr>
         <td><strong>Descri&ccedil;&atilde;o<span class="style1">:</span><font color="#FF0000">*</font></strong></td>
         <td >
            <input type="text" name="descricao" value="<bean:write name="tabelaAuxiliarTipo" property="descricao" scope="session"/>" size="<bean:write name="tamMaxCampoDescricao" scope="request"/>" maxlength="<bean:write name="tamMaxCampoDescricao" scope="request"/>"/>
         </td>
        </tr>
        <tr>
          <td><strong><%=((String) session.getAttribute("tituloTipo"))%>:</strong></td>
          <td>
           <html:select  name="tabelaAuxiliarTipo" property="tipo" value="<%=((Integer)request.getAttribute("tipo")).toString()%>">
             <html:options collection="colecaoTipos" labelProperty="descricao" property="id"/>
           </html:select>
         </td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td><strong><font color="#FF0000">*</font></strong> Campo obrigat&oacute;rio</td>
        </tr>
        <tr>
          <td><html:submit styleClass="bottonRightCol" value="Atualizar" property="Button"/></td>
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