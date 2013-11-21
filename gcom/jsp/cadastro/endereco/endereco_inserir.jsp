<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<%@ page import="gcom.util.ConstantesSistema" %>

<html:html>
<head>

<title>GSAN - Sistema Integrado de Gest&atilde;o de Servi&ccedil;os de Saneamento</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key='caminho.css'/>EstilosCompesa.css" type="text/css">

<script language="JavaScript" src="<bean:message key="caminho.js"/>endereco.js" ></script>

<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js" ></script>

<SCRIPT LANGUAGE="JavaScript">
<!--
function validarFormEndereco(formulario){

	var objCep = returnObject(formulario, "cep");
	var objLogradouro = returnObject(formulario, "logradouro");
	var objCepUnico = returnObject(formulario, "cepUnico");
	var objNumero = returnObject(formulario, "numero");

	// O processo inicia com o logradouro não obrigatório
	var logradouro = false;

	if (objCep.value.length < 1){
		logradouro = true;
	}
	else if (objCepUnico.value == "true"){
		logradouro = true;
	}
	

	if (objCep.value.length > 0 && !testarCampoValorZero(objCep, "CEP")){
		objCep.focus();
	}
	else if (logradouro == true && objLogradouro.value.length < 1){
		alert("O campo logradouro é obrigatório");
		objLogradouro.focus();
	}
	else if(!testarCampoValorZero(objLogradouro, "Logradouro")){
		objLogradouro.focus();
	}
	else if(objNumero.value.length < 1){
		alert("Informe o número do imóvel");
		objNumero.focus();
	}
	else if(!testarCampoValorZero(objNumero, "Número")){
		objNumero.focus();
	}
	else if(validateInserirEnderecoActionForm(formulario)){
		submeterFormPadrao(formulario);
	}
}
//-->
</SCRIPT>

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="InserirEnderecoActionForm"/>

</head>

<logic:equal name="flagReload" value="false">
  <logic:present name="objetoCep">
		<body leftmargin="0" topmargin="0" onload="resizePageSemLink(510, 560); setarFoco('${requestScope.nomeCampo}');">
  </logic:present>
  <logic:notPresent name="objetoCep">
		<body leftmargin="0" topmargin="0" onload="resizePageSemLink(510, 450); setarFoco('${requestScope.nomeCampo}');">
  </logic:notPresent>
</logic:equal>

<logic:equal name="flagReload" value="true">
	
	<logic:equal name="flagRedirect" value="cliente">
		<logic:equal name="flagOperacao" value="1">
			<body leftmargin="0" topmargin="0" onload="chamarReload('inserirClienteWizardAction.do?action=exibirInserirClienteEnderecoAction');resizePageSemLink(510, 450); document.InserirEnderecoActionForm.reset();setarFoco('${requestScope.nomeCampo}');">
		</logic:equal>
		<logic:equal name="flagOperacao" value="2">
			<body leftmargin="0" topmargin="0" onload="chamarReload('atualizarClienteWizardAction.do?action=exibirAtualizarClienteEnderecoAction');resizePageSemLink(510, 450); document.InserirEnderecoActionForm.reset();setarFoco('${requestScope.nomeCampo}');">
		</logic:equal>
	</logic:equal>

	<logic:equal name="flagRedirect" value="imovel">
		
		<logic:present name="fecharPopup">

			<logic:equal name="flagOperacao" value="1">
				<body leftmargin="0" topmargin="0" onload="chamarReload('inserirImovelWizardAction.do?action=exibirInserirImovelEnderecoAction');window.close();">
			</logic:equal>
			<logic:equal name="flagOperacao" value="2">
				<body leftmargin="0" topmargin="0" onload="chamarReload('atualizarImovelWizardAction.do?action=exibirAtualizarImovelEnderecoAction');window.close();;">
			</logic:equal>
			
			
		</logic:present>

		<logic:notPresent name="fecharPopup">
			
			<logic:equal name="flagOperacao" value="1">
				<body leftmargin="0" topmargin="0" onload="chamarReload('inserirImovelWizardAction.do?action=exibirInserirImovelEnderecoAction');resizePageSemLink(510, 450); document.InserirEnderecoActionForm.reset();setarFoco('${requestScope.nomeCampo}');">
			</logic:equal>
			<logic:equal name="flagOperacao" value="2">
				<body leftmargin="0" topmargin="0" onload="chamarReload('atualizarImovelWizardAction.do?action=exibirAtualizarImovelEnderecoAction');resizePageSemLink(510, 450); document.InserirEnderecoActionForm.reset();setarFoco('${requestScope.nomeCampo}');">
			</logic:equal>

		</logic:notPresent>
	
	</logic:equal>

	<logic:equal name="flagRedirect" value="localidade">

		<logic:present name="fecharPopup">
		
			<logic:equal name="flagOperacao" value="1">
				<body leftmargin="0" topmargin="0" onload="chamarSubmitEspecial('1');window.close();">
			</logic:equal>
			<logic:equal name="flagOperacao" value="2">
				<body leftmargin="0" topmargin="0" onload="chamarSubmitEspecial('2');window.close();">
			</logic:equal>

		</logic:present>

		<logic:notPresent name="fecharPopup">
		
			<logic:equal name="flagOperacao" value="1">
				<body leftmargin="0" topmargin="0" onload="chamarSubmitEspecial('1');resizePageSemLink(510, 450); document.InserirEnderecoActionForm.reset();setarFoco('${requestScope.nomeCampo}');">
			</logic:equal>
			<logic:equal name="flagOperacao" value="2">
				<body leftmargin="0" topmargin="0" onload="chamarSubmitEspecial('2');resizePageSemLink(510, 450); document.InserirEnderecoActionForm.reset();setarFoco('${requestScope.nomeCampo}');">
			</logic:equal>

		</logic:notPresent>
	
	</logic:equal>
	
	<logic:equal name="flagRedirect" value="gerenciaRegional">

		<logic:present name="fecharPopup">
		
			<logic:equal name="flagOperacao" value="1">
				<body leftmargin="0" topmargin="0" onload="chamarSubmitEspecial('1');window.close();">
			</logic:equal>
			<logic:equal name="flagOperacao" value="2">
				<body leftmargin="0" topmargin="0" onload="chamarSubmitEspecial('2');window.close();">
			</logic:equal>

		</logic:present>

		<logic:notPresent name="fecharPopup">
		
			<logic:equal name="flagOperacao" value="1">
				<body leftmargin="0" topmargin="0" onload="chamarSubmitEspecial('1');resizePageSemLink(510, 450); document.InserirEnderecoActionForm.reset();setarFoco('${requestScope.nomeCampo}');">
			</logic:equal>
			<logic:equal name="flagOperacao" value="2">
				<body leftmargin="0" topmargin="0" onload="chamarSubmitEspecial('2');resizePageSemLink(510, 450); document.InserirEnderecoActionForm.reset();setarFoco('${requestScope.nomeCampo}');">
			</logic:equal>

		</logic:notPresent>
	
	</logic:equal>
	
	<logic:equal name="flagRedirect" value="agencia">
		<logic:present name="fecharPopup">
			<logic:equal name="flagOperacao" value="1">
				<body leftmargin="0" topmargin="0" onload="chamarSubmitEspecial('1');window.close();">
			</logic:equal>
			<logic:equal name="flagOperacao" value="2">
				<body leftmargin="0" topmargin="0" onload="chamarSubmitEspecial('2');window.close();">
			</logic:equal>
		</logic:present>
		<logic:notPresent name="fecharPopup">
			<logic:equal name="flagOperacao" value="1">
				<body leftmargin="0" topmargin="0" onload="chamarSubmitEspecial('1');resizePageSemLink(700, 570); document.InserirEnderecoActionForm.reset();setarFoco('${requestScope.nomeCampo}');">
			</logic:equal>
			<logic:equal name="flagOperacao" value="2">
				<body leftmargin="0" topmargin="0" onload="chamarSubmitEspecial('2');resizePageSemLink(700, 570); document.InserirEnderecoActionForm.reset();setarFoco('${requestScope.nomeCampo}');">
			</logic:equal>
		</logic:notPresent>
	</logic:equal>

</logic:equal>


<html:form action="/inserirEnderecoAction" method="post">

<table width="470" border="0" cellpadding="0" cellspacing="5">
  <tr>
    <td width="470" valign="top" class="centercoltext"> <table height="100%">
        <tr>
          <td></td>
        </tr>
      </table>
      <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td width="11"><img src="<bean:message key='caminho.imagens'/>parahead_left.gif" editor="Webstyle4" moduleid="Album Photos (Project)\toptab_page2_parahead_left.xws" border="0" /></td>
          <td class="parabg">Informar Endere&ccedil;o</td>
          <td width="11"><img src="<bean:message key='caminho.imagens'/>parahead_right.gif" editor="Webstyle4" moduleid="Album Photos (Project)\toptab_page2_parahead_right.xws" border="0" /></td>
        </tr>
      </table>
      <p>&nbsp;</p>

      <table width="100%" border="0">
        <tr>
          <td>Preencha os campos para inserir um endere&ccedil;o:</td>
          <td align="right"><a href="javascript: abrirPopupHelp('/gsan/help/help.jsp?mapIDHelpSet=enderecoInformar', 500, 700);"><span style="font-weight: bold"><font color="#3165CE">Ajuda</font></span></a></td>												
        </tr>
		</table>
		<table width="100%" border="0">
		<logic:equal name="tipoPesquisaRetorno" value="cliente">
			<tr>
				<td width="21%" height="24"><strong>Tipo de Endere&ccedil;o:</strong></td>
				<td>
				<html:select property="tipo" style="width: 200px;">
					<html:options collection="colecaoTipo" labelProperty="descricao" property="id"/>
				</html:select>
				</td>
			</tr>
		</logic:equal>
        
		<tr>
          <td width="21%" height="24"><strong>CEP:</strong></td>
          <td>
			<table cellspacing="0" cellpadding="0" border="0" width="100%">
			<tr>
				<td width="80">
					<html:text maxlength="8" property="cep" 
					onkeypress="validaEnter(event, 'exibirInserirEnderecoAction.do', 'cep');" size="8"/>
				</td>
				<td width="70">
					<A HREF="http://www.correios.com.br/servicos/cep/cep_default.cfm" target="_new"><img src="<bean:message key='caminho.imagens'/>correios.jpg" border="0" title="Consulta de CEP"/></A>
				</td>
				<td>&nbsp;
					<logic:present name="cepNaoCadastrado">
						<span style="color:#ff0000"><bean:write name="cepNaoCadastrado"/></span>
				</logic:present>
				</td>
			</tr>
			</table>

			<html:hidden property="cepUnico"/>
		  </td>
        </tr>
      	<tr>
          <td colspan="2"> 
		  <table width="100%" bgcolor="99CCFF">
              
			  <logic:present name="municipio" property="nome">
			  
			  <tr>
                <td height="24" bgcolor="#E6F1FF"><strong>Munic&iacute;pio:</strong></td>
                <td colspan="3" bgcolor="#E6F1FF">
		
				<bean:write name="municipio" property="nome"/>
				
				</td>
              </tr>

			  </logic:present>
              
			  <logic:present name="objetoCep">

			  <tr>
                <td height="24"><strong>Logradouro:</strong></td>
                <td width="52%"><strong>Bairro:</strong></td>
              </tr>
              <tr>
                <td bgcolor="#E6F1FF">
					<logic:present name="objetoCep" property="logradouro">
						<bean:write name="objetoCep" property="logradouro"/>
					</logic:present>
				</td>
                <td bgcolor="#E6F1FF">
					<logic:present name="objetoCep" property="bairro">
						<bean:write name="objetoCep" property="bairro"/>
					</logic:present>
				</td>
              </tr>
              <tr>
                <td width="48%" height="24"><strong>Munic&iacute;pio:</strong></td>
                <td width="52%"><strong>Unidade de Federa&ccedil;&atilde;o:</strong></td>
              </tr>
              <tr>
                <td bgcolor="#E6F1FF">
					<logic:present name="objetoCep" property="municipio">
						<bean:write name="objetoCep" property="municipio"/>
					</logic:present>
				</td>
                <td bgcolor="#E6F1FF">
					<logic:present name="objetoCep" property="sigla">
						<bean:write name="objetoCep" property="sigla"/>
					</logic:present>
				</td>
              </tr>

			  </logic:present>

            </table>
			
		</td>
        </tr>
        <tr>
          <td height="24" colspan="2"><hr></td>
        </tr>
        <tr>
          <td height="24"><strong>Logradouro:</strong></td>
          <td>

			<html:text property="logradouro" size="9" maxlength="9" tabindex="1" onkeypress="validaEnter(event, 'exibirInserirEnderecoAction.do', 'logradouro');"/>
			<img src="<bean:message key='caminho.imagens'/>pesquisa.gif" width="23" height="21" border="0" style="cursor:hand;" onclick="redirecionarSubmit('/gsan/exibirPesquisarLogradouroAction.do?caminhoRetornoTelaPesquisaLogradouro=exibirInserirEnderecoAction', 465, 380);" alt="Pesquisar">

			<logic:present name="corRetorno">

				<logic:equal name="corRetorno" value="exception">
					<html:text property="logradouroDescricao" size="30" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000"/>
				</logic:equal>

				<logic:notEqual name="corRetorno" value="exception">
					<html:text property="logradouroDescricao" size="30" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>
				</logic:notEqual>

			</logic:present>

			<logic:notPresent name="corRetorno">

				<logic:empty name="InserirEnderecoActionForm" property="logradouro">
					<html:text property="logradouroDescricao" value="" size="30" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000"/>
				</logic:empty>
				<logic:notEmpty name="InserirEnderecoActionForm" property="logradouro">
					<html:text property="logradouroDescricao" size="30" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>
				</logic:notEmpty>
				

			</logic:notPresent>

			<img src="<bean:message key='caminho.imagens'/>limparcampo.gif" style="cursor:hand;" onclick="window.location.href='exibirInserirEnderecoAction.do?limparLogradouro=true';" alt="Apagar">

			</td>
        </tr>
        <tr>
          <td height="24" colspan="2"><hr></td>
        </tr>
        <tr>
          <td width="21%" height="24"><strong>Bairro:</strong></td>
          <td>

          <logic:present name="logradouroBairros">
			<html:select property="bairro" style="width: 200px;">
				<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
				<html:options collection="logradouroBairros" labelProperty="comp_id.bairro.nome" property="comp_id.bairro.id"/>
			</html:select>
		  </logic:present>

		  <logic:notPresent name="logradouroBairros">
				<html:select property="bairro" style="width: 200px;">
					<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
				</html:select>
		  </logic:notPresent>
          
		  </td>
        </tr>
        <tr>
          <td width="21%" height="24"><strong>Refer&ecirc;ncia:</strong></td>
          <td>
		<html:select property="enderecoReferencia" style="width: 200px;">
			<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
			<html:options collection="colecaoEnderecoReferencia" labelProperty="descricao" property="id"/>
		</html:select>
			</td>
        </tr>
        <tr>
          <td width="21%" height="24"><strong>N&uacute;mero do Im&oacute;vel:<font color="#FF0000">*</font></strong></td>
          <td><html:text maxlength="5" property="numero" size="5"/>
          </td>
        </tr>
        <tr>
          <td width="21%" height="24"><strong>Complemento:</strong></td>
          <td><html:text maxlength="25" property="complemento" size="25"/>
          </td>
        </tr>
        <tr>
          <td height="24">&nbsp;</td>
          <td><font color="#FF0000">*</font> Campo Obrigat&oacute;rio</td>
        </tr>
        <tr>
          <td height="27" colspan="2"> <div align="right">
              <input type="hidden" name="flagReload">
              <input type="button" class="bottonRightCol" value="Inserir" onclick="validarFormEndereco(document.forms[0]);" style="width: 70px;">
              <input type="button" class="bottonRightCol" value="Fechar" onClick="window.close();" style="width: 70px;">
            </div></td>
        </tr>
      </table>

      <p>&nbsp;</p></td>
  </tr>
</table>

</html:form>

</body>

</html:html>
