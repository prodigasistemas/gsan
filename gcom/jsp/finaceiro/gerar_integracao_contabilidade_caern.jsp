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
<html:javascript staticJavascript="false"  formName="GerarIntegracaoContabilidadeActionForm" />

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js" ></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js" ></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js"></script>

<script language="JavaScript">
<!-- Begin

    
function chamarGerar(){
  var form = document.forms[0];
	  if (validateGerarIntegracaoContabilidadeActionForm(form)) {
	  	form.submit();
	  }
}
-->
</script>
</head>

<body leftmargin="5" topmargin="5">
<html:form
  action="/gerarIntegracaoContabilidadeCaernAction.do"
  method="post"
  onsubmit="return validateGerarIntegracaoContabilidadeActionForm(this);"
  type="gcom.gui.financeiro.GerarIntegracaoContabilidadeActionForm"
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
      <table height="100%" border="0">
        <tr>
          <td></td>
        </tr>
      </table>
      
      <table width="100%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_left.gif"/></td>
          <td class="parabg">Gerar Integração para a Contabilidade</td>
          <td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif"/></td>
        </tr>
      </table>
      
      <p>&nbsp;</p>
	  
	  <table width="100%" border="0">
        <tr>
          <td width="100%" colspan=2>
	       	<table>
		      <tr>
		        <td width="505px">Para gerar a integração para a contabilidade, informe os dados abaixo:</td>
		      </tr>
		    </table>
          </td>
        </tr>
        
        <tr>
          <td><strong>Origem:</strong><font color="#FF0000">*</font></td>
          <td>
			<html:select property="idLacamentoOrigem">
 			  <html:option value="-1">&nbsp;</html:option>
		      <html:options collection="colecaoLancamentoOrigem" labelProperty="descricao" property="id"/>
		    </html:select>          	
          </td>
        </tr>
        
        <tr>
          <td><strong>Data Lançamento:</strong><font color="#FF0000">*</font></td>
          <td>
          	<html:text property="dataLancamento" size="10"  maxlength="10" onkeypress="javascript:mascaraData(this,event);return isCampoNumerico(event);"/>&nbsp;dd/mm/aaaa
          </td>
        </tr>
        
        <tr>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
        </tr>
        
        <tr>
          <td colspan="2" class="style1">
			<input type="button" name="Button" class="bottonRightCol" value="Desfazer" onclick="window.location.href='<html:rewrite page="/exibirGerarIntegracaoContabilidadeCaernAction.do?menu=sim"/>'">
			<input name="Submit23" class="bottonRightCol" value="Cancelar" type="button" onclick="window.location.href='/gsan/telaPrincipal.do'">
          </td>
          
          <td>
            <div align="right">
              <%--<gsan:controleAcessoBotao name="Button" value="Gerar" onclick="chamarGerar();" url="gerarIntegracaoContabilidadeCaernAction.do"/>--%>
               <input type="button" name="Button" class="bottonRightCol" value="Gerar" onClick="javascript:chamarGerar();"/> 
            </div>
          </td>
        </tr>
        <tr>
        	<td>
        		<p>&nbsp;</p>
        		<p>&nbsp;</p>
        		<p>&nbsp;</p>
        		<p>&nbsp;</p>
        		<p>&nbsp;</p>
        		<p>&nbsp;</p>
        		<p>&nbsp;</p>
        		<p>&nbsp;</p>
        		<p>&nbsp;</p>
        		<p>&nbsp;</p>
        		<p>&nbsp;</p>
        		<p>&nbsp;</p>
        		<p>&nbsp;</p>
        		<p>&nbsp;</p>
        		<p>&nbsp;</p>
        	</td>
        </tr>
      </table>      
	</td>
  </tr>
        
</table>

<%@ include file="/jsp/util/rodape.jsp"%>

</html:form>
</body>
</html:html>