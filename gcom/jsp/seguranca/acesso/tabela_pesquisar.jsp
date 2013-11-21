<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ page import="gcom.util.Pagina, gcom.util.ConstantesSistema"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>

<title>GSAN - Sistema Integrado de Gest&atilde;o de Servi&ccedil;os de Saneamento</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="PesquisarTabelaActionForm" />
<script>
	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
   var form = document.forms[0];

	   if (tipoConsulta == 'unidadeEmpresa') {
	      form.idUnidadeEmpresa.value = codigoRegistro;
	      form.descricaoUnidadeEmpresa.value = descricaoRegistro;
	    }
   }

	
	function validarForm(form){
		if (validatePesquisarTabelaActionForm(form)){
    		form.submit();
    	}
	}
	
	function limparForm(){
	 var form = document.forms[0];
	
		form.nomeTabela.value="";
		form.descricao.value="";
	
	}


</script>

</head>

<body leftmargin="5" topmargin="5" onload="resizePageSemLink(600, 330);setarFoco('${requestScope.nomeCampo}');">

<html:form action="/pesquisarTabelaAction"
	name="PesquisarTabelaActionForm"
	type="gcom.gui.seguranca.acesso.PesquisarTabelaActionForm"
	method="post"
	onsubmit="return validatePesquisarTabelaActionForm(this);">

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
					<td class="parabg">Pesquisar Tabela</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
	
      <p>&nbsp;</p>
      
      <table width="100%" border="0">
        <tr> 
          <td colspan="2">Preencha os campos para pesquisar uma Tabela:</td>
          <td align="right"><a href="javascript: abrirPopupHelp('/gsan/help/help.jsp?mapIDHelpSet=tabelaPesquisar', 500, 700);"><span style="font-weight: bold"><font color="#3165CE">Ajuda</font></span></a></td>
        </tr>
       </table>
        
       <table width="100%" border="0">
        <tr>
			<td><strong>Nome da Tabela:</strong></td>
			<td><html:text maxlength="30" property="nomeTabela"
				size="30" tabindex="1" />
			</td>
		</tr>
        <tr>
			<td><strong>Descri&ccedil;&atilde;o:</strong></td>
			<td><html:text maxlength="30" property="descricao"
				size="30" tabindex="1" />
			</td>
		</tr>
        
        <tr> 
        
          <td>&nbsp;</td>
          <td colspan="3">&nbsp;</td>
        </tr>

		<tr>
			<td>
			

				<input type="button" name="Button1"
				class="bottonRightCol" value="Voltar" 
				onclick="history.back();">

				<input name="Button2" type="button" class="bottonRightCol" value="Limpar" align="left" onclick="document.forms[0].reset();limparForm();" >
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
