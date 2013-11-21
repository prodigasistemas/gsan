<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<%@page import="gcom.util.ConstantesSistema"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>

<title>GSAN - Sistema Integrado de Gest&atilde;o de Servi&ccedil;os de Saneamento</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">

<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>

<html:javascript staticJavascript="false"  formName="PesquisarProcessoActionForm" />

<script>
	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
   var form = document.forms[0];

	   if (tipoConsulta == 'processo') {
	      form.idProcesso.value = codigoRegistro;
	      form.descricaoProcesso.value = descricaoRegistro;
	    }
   }

	
	function validarForm(form){
		if (validatePesquisarProcessoActionForm(form)){
    		form.submit();
    	}
	}


</script>

</head>

<body leftmargin="5" topmargin="5" onload="resizePageSemLink(600, 365);setarFoco('${requestScope.nomeCampo}');">

<html:form action="/pesquisarProcessoAction"
	name="PesquisarProcessoActionForm"
	type="gcom.gui.batch.PesquisarProcessoActionForm"
	method="post"
	onsubmit="return validatePesquisarProcessoActionForm(this);">

	<table width="550" border="0" cellspacing="5" cellpadding="0">
		<tr>
			<td width="520" valign="top" class="centercoltext">
			<table height="100%">
				<tr>
					<td></td>
				</tr>
				
			</table>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_left.gif" /></td>
					<td class="parabg">Pesquisar Processo</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
	
      <p>&nbsp;</p>
      
      <table width="100%" border="0">
        <tr> 
          <td colspan="2">Preencha os campos para pesquisar um Processo:</td>
          <td align="right"><a href="javascript: abrirPopupHelp('/gsan/help/help.jsp?mapIDHelpSet=processoPesquisar', 500, 700);"><span style="font-weight: bold"><font color="#3165CE">Ajuda</font></span></a></td>
        </tr>
       </table>
        
       <table width="100%" border="0">
        <tr>
			<td><strong>Descrição:</strong></td>
			<td><html:text maxlength="40" property="descricao"
				size="40" tabindex="1" />
			</td>
		</tr>
		<tr>
					<td>&nbsp;</td>
					<td><html:radio property="tipoPesquisa" tabindex="5"
						value="<%=ConstantesSistema.TIPO_PESQUISA_INICIAL.toString()%>" />
					Iniciando pelo texto <html:radio property="tipoPesquisa"
						tabindex="6"
						value="<%=ConstantesSistema.TIPO_PESQUISA_COMPLETA.toString()%>" />
					Contendo o texto</td>
					<td>&nbsp;</td>
				</tr>
         <tr>
			<td><strong>Descrição Abreviada:</strong></td>
			<td><html:text maxlength="10" property="descricaoAbreviada"
				size="10" tabindex="1" />
			</td>
		</tr>
        
        <tr>
			<td><strong>Tipo de Processo:</strong></td>
			<td><html:select property="idProcessoTipo"
						style="width: 150px;">
					<logic:present name="colecaoProcessoTipo" scope="request">
						<html:option value="-1">&nbsp;</html:option>
						<html:options collection="colecaoProcessoTipo"
							labelProperty="descricao" property="id" />
					</logic:present>
				</html:select>
			</td>
		</tr>
		<tr>
        <tr> 
        
          <td>&nbsp;</td>
          <td colspan="3">&nbsp;</td>
        </tr>

		<tr>
			<td>
			
			<logic:present name="caminhoRetornoTelaPesquisaProcesso">
				<input type="button" name="Button1"
				class="bottonRightCol" value="Voltar" 
				onclick="history.back();">
			</logic:present>

				<input name="Button2" type="button" class="bottonRightCol" value="Limpar" align="left" onclick="window.location.href='<html:rewrite page="/exibirPesquisarProcessoAction.do?menu=sim"/>'" >
            </td>
            <td  align="right">
				<input type="button" name="Button3"
				class="bottonRightCol" value="Pesquisar" tabindex="4"
				onClick="javascript:validarForm(document.forms[0]);"/>
			</td>
		</tr>

      </table>

			<p>&nbsp;</p>
			<p>&nbsp;</p>
	</table>
</html:form>
</body>
</html:html>
