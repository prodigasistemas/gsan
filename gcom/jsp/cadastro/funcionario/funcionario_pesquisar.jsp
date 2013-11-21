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
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="PesquisarFuncionarioActionForm" />
<script>
	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
   var form = document.forms[0];

	   if (tipoConsulta == 'unidadeEmpresa') {
	      form.idUnidadeEmpresa.value = codigoRegistro;
	      form.descricaoUnidadeEmpresa.value = descricaoRegistro;
	    }
   }


	function limparUnidadeLotacao(form) {
    	form.idUnidadeEmpresa.value = "";
    	form.descricaoUnidadeEmpresa.value = "";
    	form.idUnidadeEmpresa.focus();
	}
	
	function limparDescricaoEmpresa(form) {
       	form.descricaoUnidadeEmpresa.value = "";
    }
	
	function validarForm(form){
	if (testarCampoValorZero(document.PesquisarFuncionarioActionForm.idUnidadeEmpresa, 'Unidade de Lotação')
	&& testarCampoValorZero(document.PesquisarFuncionarioActionForm.id, 'Matrícula')){
		if(validatePesquisarFuncionarioActionForm(form)){
    		form.submit();
		}
	}

	}

</script>

</head>

<body leftmargin="5" topmargin="5" onload="resizePageSemLink(600, 370);setarFoco('${requestScope.nomeCampo}');">

<html:form action="/retornarFuncionarioPesquisar"
	name="PesquisarFuncionarioActionForm"
	type="gcom.gui.cadastro.funcionario.PesquisarFuncionarioAction"
	method="post"
	onsubmit="return validatePesquisarFuncionarioActionForm(this);">

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
					<td class="parabg">Pesquisar Funcion&aacute;rio</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
	
      <p>&nbsp;</p>
      
      <table width="100%" border="0">
        <tr> 
          <td colspan="2">Preencha os campos para pesquisar um funcionário:</td>
          <logic:present scope="application" name="urlHelp">
				<td align="right"><a href="javascript:abrirPopupHelp('${applicationScope.urlHelp}cadastroFuncionarioPesquisar', 500, 700);"><span style="font-weight: bold"><font color="#3165CE">Ajuda</font></span></a></td>									
		  </logic:present>
		  <logic:notPresent scope="application" name="urlHelp">
				<td align="right"><span style="font-weight: bold"><font color=#696969><u>Ajuda</u></font></span></td>									
		  </logic:notPresent>
          
        </tr>
       </table>
        
       <table width="100%" border="0">
        <tr>
			<td width="15%"><strong>Matr&iacute;cula:</strong></td>
			<td><html:text maxlength="9" property="id"
				size="9" tabindex="1" onkeypress="return isCampoNumerico(event);"/>
			</td>
		</tr>
        
        <tr>
			<td><strong>Nome:</strong></td>
			<td><html:text maxlength="40" property="nome"
				size="40" tabindex="2" /></td>
		</tr>
		<tr>
					<td>&nbsp;</td>
					<td><html:radio property="tipoPesquisa" 
						tabindex="5"
						value="<%=ConstantesSistema.TIPO_PESQUISA_INICIAL.toString()%>" />
					Iniciando pelo texto <html:radio property="tipoPesquisa"
						tabindex="6"
						value="<%=ConstantesSistema.TIPO_PESQUISA_COMPLETA.toString()%>" />
					Contendo o texto</td>
					<td>&nbsp;</td>
		</tr>                  
        <tr> 
        <tr> 
          <td><strong>Unidade de Lota&ccedil;&atilde;o:</strong></td>
          <td><html:text maxlength="9" property="idUnidadeEmpresa" onkeyup="javascript:limparDescricaoEmpresa(document.PesquisarFuncionarioActionForm);"
				size="9" tabindex="3" onkeypress="javascript: validaEnter(event, 'exibirFuncionarioPesquisar.do?objetoConsulta=1', 'idUnidadeEmpresa');return isCampoNumerico(event);"/>
	
					<a
						href="javascript:redirecionarSubmit('exibirPesquisarUnidadeOrganizacionalAction.do?popup=exibirFuncionarioPesquisar&limpaForm=sim&caminhoRetornoTelaPesquisaUnidadeOrganizacional=exibirFuncionarioPesquisar');">
						<img width="23" height="21" border="0"
						src="<bean:message key="caminho.imagens"/>pesquisa.gif"
						title="Pesquisar Unidade de Lotação" /></a> 

					<logic:present 
						name="idUnidadeEmpresaNaoEncontrada">
						<logic:equal name="idUnidadeEmpresaNaoEncontrada" value="exception">
							<html:text property="descricaoUnidadeEmpresa" size="40"
								maxlength="40" readonly="true" 
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:equal>
						<logic:notEqual name="idUnidadeEmpresaNaoEncontrada"
							value="exception">
							<html:text property="descricaoUnidadeEmpresa" size="40"
								maxlength="40" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEqual>
					</logic:present> 
					<logic:notPresent
						name="idUnidadeEmpresaNaoEncontrada">
						<logic:empty name="PesquisarFuncionarioActionForm"
							property="idUnidadeEmpresa">
							<html:text property="descricaoUnidadeEmpresa" value="" size="40"
								maxlength="40" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:empty>
						<logic:notEmpty name="PesquisarFuncionarioActionForm"
							property="idUnidadeEmpresa">
							<html:text property="descricaoUnidadeEmpresa" size="40"
								maxlength="40" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEmpty>
					</logic:notPresent>
          
            <a href="javascript:limparUnidadeLotacao(document.PesquisarFuncionarioActionForm);">
			<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
			border="0" title="Apagar" /></a>
          </td>
        </tr>
        <tr> 
        
          <td>&nbsp;</td>
          <td colspan="3">&nbsp;</td>
        </tr>


		<tr>
			<td>
			
			<logic:present name="caminhoRetornoTelaPesquisaFuncionario">
				<input type="button" name="Button1"
				class="bottonRightCol" value="Voltar" 
				onclick="history.back();">
			</logic:present>

				<input name="Button2" type="button" class="bottonRightCol" value="Limpar" align="left" onclick="window.location.href='<html:rewrite page="/exibirFuncionarioPesquisar.do?desfazer=S"/>'" >
            </td>
            <td  align="right">
				<input type="button" name="Button3"
				class="bottonRightCol" value="Pesquisar" tabindex="4"
				onClick="validarForm(document.PesquisarFuncionarioActionForm)"/>
			</td>
		</tr>



      </table>

			<p>&nbsp;</p>
			<p>&nbsp;</p>
	</table>
</html:form>
</body>
</html:html>
