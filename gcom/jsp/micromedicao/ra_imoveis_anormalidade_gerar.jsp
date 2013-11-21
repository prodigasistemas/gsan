<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page import="gcom.util.ConstantesSistema"%>

<html:html>
<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<meta name="generator" content="Web page layout created by Xara Webstyle 4 - http://www.xara.com/webstyle/" />

<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js" ></script>

<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="ExibirPesquisarLogradouroActionForm"/>

<SCRIPT LANGUAGE="JavaScript">
<!--
function validarForm(){

	var form = document.forms[0];
	
	if (form.solicitacaoTipo.value < 0){
		alert('Informe Tipo de Solicitação');
	} else if (form.solicitacaoTipoEspecificacao.value < 0) {
		alert('Informe Especificação');
	} else {
		confirm('Os dados selecionados para geração do relatório serão perdidos. Confirma?');
		form.action = 'exibirPesquisarGeracaoRAImoveisAnormalidadeAction.do?reload=sim';
		form.submit();
	}

}


function limparForm(){
	var form = document.forms[0];
	
	form.idSolicitacaoTipo.value = "-1";
	form.idSolicitacaoTipoEspecificacao.value = "-1";
}

	function carregarEspecificacao(){
		
		var form = document.forms[0];
		
		if (form.solicitacaoTipo.value > 0){
			redirecionarSubmit('exibirPesquisarGeracaoRAImoveisAnormalidadeAction.do');
		}
	}
	
	function chamarReload(){
		chamarSubmitComUrlSemUpperCase('/gsan/gerarRAImoveisAnormalidadeAction.do');
	}
//-->
</SCRIPT>

</head>

<logic:present name="reload" scope="request">
<body leftmargin="5" topmargin="5"
onload="resizePageSemLink(590, 280);chamarReload();window.close();">
</logic:present>

<logic:notPresent name="reload">
<body leftmargin="0" topmargin="0" onload="resizePageSemLink(590, 280); setarFoco('${requestScope.nomeCampo}');">
</logic:notPresent>

<table width="552" border="0" cellpadding="0" cellspacing="5">
  <tr>
    <td width="542" valign="top" class="centercoltext"> <table height="100%">
        <tr>
          <td></td>
        </tr>
      </table>
      <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td width="11"><img src="<bean:message key="caminho.imagens"/>parahead_left.gif" editor="Webstyle4" moduleid="Album Photos (Project)\toptab_page2_parahead_left.xws" border="0" /></td>
          <td class="parabg">Gerar OS de Fiscalização</td>
          <td width="11"><img src="<bean:message key="caminho.imagens"/>parahead_right.gif" editor="Webstyle4" moduleid="Album Photos (Project)\toptab_page2_parahead_right.xws" border="0" /></td>
        </tr>
      </table>
      <p>&nbsp;</p>

      <html:form action="/gerarRAImoveisAnormalidadeAction" method="post">
      <table width="100%" border="0">
        <tr>
          <td colspan="4">Preencha os campos para gerar as ordem de serviço de fiscalização:</td>
        <td align="right"></td>
    	</tr>
      </table>
    
      <table width="100%" border="0">
        <tr>
        <td HEIGHT="30"><strong>Tipo de Solicitação:<font color="#FF0000">*</font></strong></td>
        <td>
			<html:select property="solicitacaoTipo" style="width: 350px;font-size:11px;" tabindex="10" onchange="carregarEspecificacao()">
				<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
				<html:options collection="colecaoSolicitacaoTipo" labelProperty="descricao" property="id"/>
				</html:select>
		</td>
      </tr>
      <tr>
        <td HEIGHT="30"><strong>Especificação:<font color="#FF0000">*</font></strong></td>
        <td>
				<html:select property="solicitacaoTipoEspecificacao" style="width: 350px;font-size:11px;" tabindex="11">
					<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
					<logic:present name="colecaoSolicitacaoTipoEspecificacao">
						<html:options collection="colecaoSolicitacaoTipoEspecificacao" labelProperty="descricao" property="id"/>
					</logic:present>
				</html:select>
		</td>
      </tr>
      <tr>
					<td>&nbsp;</td>
					<td align="right">
					<div align="left"><strong><font color="#FF0000">*</font></strong>
					Campos obrigat&oacute;rios</div>
					</td>



				</tr>
        <tr>
		        <td height="24" colspan="3" width="80%">
		          	<INPUT TYPE="button" class="bottonRightCol" value="Limpar" onclick="limparForm();"/>
        			  	&nbsp;&nbsp;
	       			<INPUT TYPE="button" class="bottonRightCol" value="Fechar" onclick="window.close();"/>
        		  	</td>
					<td align="right">
					
					<input type="button" onclick="validarForm();" class="bottonRightCol" value="Gerar"></td>
        </tr>
      </table>
      </html:form>
      <p>&nbsp;</p></td>
  </tr>
</table>
</body>
</html:html>

