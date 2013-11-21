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
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="PesquisarDistritoOperacionalActionForm" />
<script>
	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
   var form = document.forms[0];

	   if (tipoConsulta == 'unidadeEmpresa') {
	      form.idUnidadeEmpresa.value = codigoRegistro;
	      form.descricaoUnidadeEmpresa.value = descricaoRegistro;
	    }
   }

	
	function validarForm(form){
		if (validatePesquisarDistritoOperacionalActionForm(form)){
    		form.submit();
    	}
	}


</script>

</head>

<body leftmargin="5" topmargin="5" onload="resizePageSemLink(600, 360);setarFoco('${requestScope.nomeCampo}');">

<html:form action="/pesquisarDistritoOperacionalAction"
	name="PesquisarDistritoOperacionalActionForm"
	type="gcom.gui.operacional.PesquisarDistritoOperacionalActionForm"
	method="post"
	onsubmit="return validatePesquisarDistritoOperacionalActionForm(this);">

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
					<td class="parabg">Pesquisar Distrito Operacional</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
	
      <p>&nbsp;</p>
      
      <table width="100%" border="0">
        <tr> 
          <td colspan="2">Preencha os campos para pesquisar um Distrito Operacional:</td>
          <td align="right"><a href="javascript: abrirPopupHelp('/gsan/help/help.jsp?mapIDHelpSet=distritoOperacionalPesquisar', 500, 700);"><span style="font-weight: bold"><font color="#3165CE">Ajuda</font></span></a></td>
        </tr>
       </table>
        
       <table width="100%" border="0">
        <tr>
			<td><strong>Nome:</strong></td>
			<td><html:text maxlength="30" property="nomeDistritoOperacional"
				size="30" tabindex="1" />
			</td>
		</tr>
        
        <tr>
			<td><strong>Setor de Abastecimento:</strong></td>
			<td><html:select property="setorAbastecimento"
						style="width: 150px;">
					<logic:present name="colecaoSetorAbastecimento" scope="request">
						<html:option value="-1">&nbsp;</html:option>
						<html:options collection="colecaoSetorAbastecimento"
							labelProperty="descricao" property="id" />
					</logic:present>
				</html:select>
			</td>
		</tr>
                  
        <tr>
			<td><strong>Zona de Abastecimento:</strong></td>
			<td><html:select property="zonaAbastecimento"
						style="width: 150px;">
					<logic:present name="colecaoZonaAbastecimento" scope="request">
						<html:option value="-1">&nbsp;</html:option>
						<html:options collection="colecaoZonaAbastecimento"
							labelProperty="descricao" property="id" />
					</logic:present>
				</html:select>
			</td>
		</tr>
		<tr>
			<td><strong>Indicador de Uso:</strong></td>
			<td align="right">
			<div align="left"><html:radio property="indicadorUso" tabindex="4"
				value="<%=ConstantesSistema.INDICADOR_USO_ATIVO.toString()%>" />
			<strong>Ativo</strong> <html:radio property="indicadorUso"
				tabindex="5"
				value="<%=ConstantesSistema.INDICADOR_USO_DESATIVO.toString()%>" />
			<strong>Inativo</strong> <html:radio property="indicadorUso"
				tabindex="5" value="" /> <strong>Todos</strong></div>
			</td>
		</tr>
		
        <tr> 
        
          <td>&nbsp;</td>
          <td colspan="3">&nbsp;</td>
        </tr>

		<tr>
			<td>
			
			<logic:present name="caminhoRetornoTelaPesquisaDistritoOperacional">
				<input type="button" name="Button1"
				class="bottonRightCol" value="Voltar" 
				onclick="history.back();">
			</logic:present>

				<input name="Button2" type="button" class="bottonRightCol" value="Limpar" align="left" onclick="window.location.href='<html:rewrite page="/exibirPesquisarDistritoOperacionalAction.do?menu=sim"/>'" >
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
