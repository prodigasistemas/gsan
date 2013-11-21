<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ page import="gcom.util.ConstantesSistema" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>

<title>GSAN - Sistema Integrado de Gest&atilde;o de Servi&ccedil;os de Saneamento</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="PesquisarActionForm"
	dynamicJavascript="false" />

<script>
 var bCancel = false;

    function validatePesquisarActionForm(form) {
        if (bCancel)
      return true;
        else
       return validateCaracterEspecial(form);
   }

    function caracteresespeciais () {
     this.ab = new Array("nomeMunicipio", "Nome possui caracteres especiais.", new Function ("varName", " return this[varName];"));
    }

function validarForm(form){
	form.submit();
}

</script>
</head>

<body leftmargin="0" topmargin="0"
	onload="window.focus();resizePageSemLink(495, 420);javascript:setarFoco('${requestScope.nomeCampo}');">

<html:form action="/pesquisarMunicipioAction" method="post"
	onsubmit="return validatePesquisarActionForm(this);">
	<table width="452" border="0" cellspacing="5" cellpadding="0">
		<tr>
			<td width="452" valign="top" class="centercoltext">
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
					<td class="parabg">Pesquisar Munic&iacute;pio</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>
					<td colspan="2">Preencha os campos para pesquisar um
					munic&iacute;pio:</td>
					<logic:present scope="application" name="urlHelp">
						<td align="right"><a href="javascript:abrirPopupHelp('${applicationScope.urlHelp}cadastroGeograficoMunicipioPesquisar', 500, 700);"><span style="font-weight: bold"><font color="#3165CE">Ajuda</font></span></a></td>									
					</logic:present>
					<logic:notPresent scope="application" name="urlHelp">
						<td align="right"><span style="font-weight: bold"><font color=#696969><u>Ajuda</u></font></span></td>									
					</logic:notPresent>					
    			</tr>
      		</table>
    
      		<table width="100%" border="0">
				<tr>
					<td width="142"><strong>Nome:</strong></td>
					<td><html:text maxlength="30" property="nomeMunicipio" tabindex="1"
						size="30" /></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td colspan="3"><html:radio property="tipoPesquisa"
						value="<%=ConstantesSistema.TIPO_PESQUISA_INICIAL.toString()%>" />
						Iniciando pelo texto&nbsp;<html:radio property="tipoPesquisa"
							tabindex="5"
							value="<%=ConstantesSistema.TIPO_PESQUISA_COMPLETA.toString()%>" />
						Contendo o texto</td>
				</tr>
				<tr>
					<td width="142"><strong>Regi&atilde;o Desenvolvimento:</strong></td>
					<td><html:select property="idRegiaoDesenvolvimento" tabindex="2">
						<html:option value="-1">&nbsp;</html:option>
						<html:options collection="regiaoDesenvolvimentos"
							labelProperty="descricao" property="id" />
					</html:select></td>
				</tr>
				<tr>
					<td><strong>Regi&atilde;o:</strong></td>
					<td><html:select property="idRegiao" tabindex="3"
						onchange="pesquisaColecaoReload('exibirPesquisarMunicipioAction.do?objetoConsulta=1','idRegiao');">
						<html:option value="-1">&nbsp;</html:option>
						<html:options collection="regioes" labelProperty="nome"
							property="id" />
					</html:select></td>
				</tr>
				<tr>
					<td><strong>Microrregi&atilde;o:</strong></td>
					<td><html:select property="idMicrorregiao" tabindex="4">
						<html:option value="-1">&nbsp;</html:option>
						<html:options collection="microrregioes" labelProperty="nome"
							property="id" />
					</html:select></td>
				</tr>
				<tr>
					<td><strong>Unidade Federa&ccedil;&atilde;o:</strong></td>
					<td><html:select property="idUnidadeFederacao" tabindex="5">
						<html:option value="-1">&nbsp;</html:option>
						<html:options collection="unidadesFederacao"
							labelProperty="descricao" property="id" />
					</html:select></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td height="24" colspan="3" width="80%">
		          	<INPUT TYPE="button" class="bottonRightCol" value="Limpar" onclick="redirecionarSubmit('exibirPesquisarMunicipioAction.do?indicadorUsoTodos=1');"/>
        			  	&nbsp;&nbsp;
       			  	<logic:present name="caminhoRetornoTelaPesquisaMunicipio">
		          		<INPUT TYPE="button" class="bottonRightCol" value="Voltar" onclick="redirecionarSubmit('${caminhoRetornoTelaPesquisaMunicipio}.do?objetoConsulta=1&caminhoRetornoTelaPesquisaBairro=${caminho}');"/>
        		  	</logic:present>
        		  	</td>
					<td align="right">
					<html:submit styleClass="bottonRightCol" tabindex="6"
						value="Pesquisar" /></td>
					<td>&nbsp;</td>
				</tr>
			</table>
			<p>&nbsp;</p>
			</td>
		</tr>
	</table>
</html:form>
</body>
</html:html>
