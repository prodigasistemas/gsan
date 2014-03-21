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
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js" ></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js" ></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>	Calendario.js"></script>
<html:javascript staticJavascript="false"  formName="AtualizarVencimentoFaturaClienteResponsavelActionForm"
dynamicJavascript="true"/>
<script language="JavaScript">
<!-- Begin
function validaMesAno(mesAno){
	if(mesAno.value != ""){
		return verificaAnoMes(mesAno);
	}else{
		return true;
	}
}
	
function limparForm(){
	var form = document.forms[0];
	
	form.mesAno.value = "";
}

function atualizar(){
	var form = document.forms[0];
	form.submit();
}

	-->
</script>
</head>

<body leftmargin="5" topmargin="5">
<html:form
  action="/atualizarVencimentoFaturaClienteResponsavelAction.do"
  method="post"
  name="AtualizarVencimentoFaturaClienteResponsavelActionForm"
  type="gcom.gui.faturamento.AtualizarVencimentoFaturaClienteResponsavelActionForm">

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

          <td class="parabg">Atualizar Faturas de Clientes Responsáveis</td>
          <td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif"/></td>
        </tr>
      </table>
      <p>&nbsp;</p>
	  <table width="100%" border="0">
        <tr>
          <td width="80%" colspan="2">
          <table>
          	<tr>
          		<td width="80%">Para alterar o vencimento das faturas do cliente responsável, informe os dados abaixo:</td>
          		<td align="right">
				</td>
          	</tr>
          </table>
          </td>
        </tr>
        
        <tr>
          <td width="30%"><strong>Data Vencimento:<font color="#FF0000">*</font></strong> </td>
          <td width="70%">
        	<html:text property="dataVencimento" 
						size="11" 
						maxlength="10" 
						tabindex="3" 
						onkeypress="mascaraData(this, event)"  
					 	value=""/>
			<a href="javascript:abrirCalendario('AtualizarVencimentoFaturaClienteResponsavelActionForm', 'dataVencimento')">
			<img border="0" 
				 width="16" 
				 height="15"
				 src="<bean:message key="caminho.imagens"/>calendario.gif"
				 width="20" 
				 border="0" 
				 align="absmiddle" 
				 alt="Exibir Calendário" /></a> (dd/mm/aaaa)
          
          </td>
        </tr>
        <tr>
          <td width="30%"><strong>Mês/Ano de Referência:<font color="#FF0000">*</font></strong></td>
          <td width="70%">
          	<html:text property="anoMesReferencia" size="7"  maxlength="7" onkeypress="javascript:mascaraAnoMes(this, event);"/>&nbsp;mm/aaaa
          </td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
        </tr>
        <tr>
         	<td align="left"><input type="button" name="Button" class="bottonRightCol"
						value="Limpar"
						onclick="window.location.href='<html:rewrite page="/exibirAtualizarVencimentoFaturaClienteResponsavel.do?menu=sim"/>'">
			</td>
          	<td align="right"><input type="button" name="Button" class="bottonRightCol"
					value="Atualizar" onclick="javascript:atualizar();" />
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