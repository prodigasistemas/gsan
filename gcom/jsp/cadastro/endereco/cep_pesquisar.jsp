<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<%@ page import="gcom.cadastro.endereco.Cep" %>
<%@ page import="gcom.util.ConstantesSistema"%>


<html:html>
<head>

<title>GSAN - Sistema Integrado de Gest&atilde;o de Servi&ccedil;os de Saneamento</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key='caminho.css'/>EstilosCompesa.css" type="text/css">

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="PesquisarCepActionForm"/>

<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js" ></script>

<SCRIPT LANGUAGE="JavaScript">
<!--
function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
	var form = document.PesquisarActionForm;
	alert('ok');
	//alert(tipoConsulta);
	if (tipoConsulta == 'municipio') {
		form.idMunicipio.value = codigoRegistro;
		form.nomeMunicipio.value = descricaoRegistro;
	}
}

function limparPesquisaMunicipio(){

	var form = document.forms[0];

	form.idMunicipio.value = "";
	form.nomeMunicipio.value = "";

	form.idMunicipio.focus();
}

function limparForm(){

	var form = document.forms[0];

	form.idMunicipio.value = "";
	form.nomeMunicipio.value = "";
	form.nomeLogradouro.value = "";
	form.idCepInicial.value = "";
	form.idCepFinal.value = "";
}


function validarForm(form){

	var objMunicipio = returnObject(form, "idMunicipio");
	var voltarSituacao = false;
	
	if (objMunicipio.disabled){
		objMunicipio.disabled = false;
		voltarSituacao = true;
	}
	
	var objNomeLogradouro = returnObject(form, "nomeLogradouro");
	
	if (form.idCepInicial.value > form.idCepFinal.value) {
	alert('Cep final menor que o cep inicial');
	} 
	
	if (validatePesquisarCepActionForm(form)){
	
		if (objMunicipio.value.length > 0 &&
			!testarCampoValorZero(objMunicipio, "Município ")){
			objMunicipio.focus();
		}
		else{
			redirecionarSubmit('pesquisarCepAction.do');
		} 
	}
	else if (voltarSituacao){
		objMunicipio.disabled = true;
	}
}



function replicaCep() {
		var form = document.forms[0]; 
		form.idCepFinal.value = form.idCepInicial.value;
		
		}


//-->
</SCRIPT>

</head>


<BODY TOPMARGIN="5" LEFTMARGIN="5" onload="resizePageSemLink(600, 290); setarFoco('${requestScope.nomeCampo}');">


<html:form action="/exibirPesquisarCepAction" method="post">

<table width="550" border="0" cellpadding="0" cellspacing="5">
  <tr>
    <td width="540" valign="top" class="centercoltext"> <table height="100%">
        <tr>
          <td></td>
        </tr>
      </table>
      <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td width="11"><img src="<bean:message key='caminho.imagens'/>parahead_left.gif" editor="Webstyle4" moduleid="Album Photos (Project)\toptab_page2_parahead_left.xws" border="0" /></td>
          <td class="parabg">Pesquisar CEPs</td>
          <td width="11"><img src="<bean:message key='caminho.imagens'/>parahead_right.gif" editor="Webstyle4" moduleid="Album Photos (Project)\toptab_page2_parahead_right.xws" border="0" /></td>
        </tr>
      </table>
      <p>&nbsp;</p>

      <table width="100%" border="0">
      <tr>
		<td colspan="2">Preencha os campos para selecionar CEPs:</td>
      	<logic:present scope="application" name="urlHelp">
				<td align="right"><a href="javascript:abrirPopupHelp('${applicationScope.urlHelp}cadastroEnderecoCepPesquisar', 500, 700);"><span style="font-weight: bold"><font color="#3165CE">Ajuda</font></span></a></td>									
		</logic:present>
		<logic:notPresent scope="application" name="urlHelp">
				<td align="right"><span style="font-weight: bold"><font color=#696969><u>Ajuda</u></font></span></td>									
		</logic:notPresent>
      	
      </tr>
     </table>
    
    		
		
	  <!-- FORMULARIO --------------------------------------------------------------------------------------------------------------- -->
	  <table width="100%" border="0">
	  <tr>
			<td><strong>Munic&iacute;pio:<font color="#FF0000">*</font></strong></td>
			<td colspan="2">
				
				<logic:empty name="PesquisarCepActionForm" property="idMunicipio">
				
					<html:text maxlength="4" property="idMunicipio" size="4" tabindex="1"
					onkeypress="return validaEnter(event, 'exibirPesquisarCepAction.do?pesquisaMunicipio=true', 'idMunicipio');" />
			
					<a href="javascript:redirecionarSubmit('exibirPesquisarMunicipioAction.do?caminhoRetornoTelaPesquisaMunicipio=exibirPesquisarCepAction');">
					<img width="23" height="21" border="0"
					src="<bean:message key='caminho.imagens'/>pesquisa.gif" title="Pesquisar Município" /></a>
				
				</logic:empty>

				<logic:notEmpty name="PesquisarCepActionForm" property="idMunicipio">

					<logic:present name="municipioInformado" scope="session">
				
						<html:text maxlength="4" property="idMunicipio" size="4" tabindex="1" disabled="true"/>
			
						<img width="23" height="21" border="0" src="<bean:message key='caminho.imagens'/>pesquisa.gif" title="Pesquisar Município" />

					</logic:present>

					<logic:notPresent name="municipioInformado" scope="session">
				
						<html:text maxlength="4" property="idMunicipio" size="4" tabindex="1"
						onkeypress="return validaEnter(event, 'exibirPesquisarCepAction.do', 'idMunicipio');" />
			
						<a href="javascript:abrirPopup('exibirPesquisarMunicipioAction.do');">
						<img width="23" height="21" border="0"
						src="<bean:message key='caminho.imagens'/>pesquisa.gif" title="Pesquisar Município" /></a>

					</logic:notPresent>
				
				</logic:notEmpty>
			
				<logic:present name="idMunicipioNaoEncontrado">
			
					<logic:equal name="idMunicipioNaoEncontrado" value="exception">
						
						<html:text property="nomeMunicipio" size="40" maxlength="30"
						readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000" />
					
					</logic:equal>
			
					<logic:notEqual name="idMunicipioNaoEncontrado" value="exception">
			
						<html:text property="nomeMunicipio" size="40" maxlength="30"
						readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" />
			
					</logic:notEqual>
			
				</logic:present> 
			
				<logic:notPresent name="idMunicipioNaoEncontrado">
			
					<logic:empty name="PesquisarCepActionForm" property="idMunicipio">
						
						<html:text property="nomeMunicipio" value="" size="40"
						maxlength="30" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000" />
					
					</logic:empty>

					<logic:notEmpty name="PesquisarCepActionForm" property="idMunicipio">
				
						<html:text property="nomeMunicipio" size="40" maxlength="30" readonly="true"
						style="background-color:#EFEFEF; border:0; color: #000000" />
			
					</logic:notEmpty>

				</logic:notPresent>
			
				<logic:empty name="PesquisarCepActionForm" property="idMunicipio">
					
					<a href="javascript:limparPesquisaMunicipio();"> <img
					src="<bean:message key='caminho.imagens'/>limparcampo.gif"
					border="0" title="Apagar" /></a>
			
				</logic:empty>

				<logic:notEmpty name="PesquisarCepActionForm" property="idMunicipio">

					<logic:present name="municipioInformado" scope="session">
				
						<img src="<bean:message key='caminho.imagens'/>limparcampo.gif"
						border="0"/>

					</logic:present>

					<logic:notPresent name="municipioInformado" scope="session">
				
						<a href="javascript:limparPesquisaMunicipio();"> <img
						src="<bean:message key='caminho.imagens'/>limparcampo.gif"
						border="0" title="Apagar" /></a>

					</logic:notPresent>
			
				</logic:notEmpty>
				
			</td>
	  </tr>
	  <tr>
			<td width="15%"><strong>Logradouro:<font color="#FF0000"></font></strong></td>
			<td width="85%" colspan="2"><html:text maxlength="30" property="nomeLogradouro" size="30"
			tabindex="2" /></td>
	  </tr>
	    <tr>
			<td>&nbsp;</td>
			<td valign="top"><html:radio property="tipoPesquisaLogradouro"
					value="<%=ConstantesSistema.TIPO_PESQUISA_INICIAL.toString()%>" />
				Iniciando pelo texto<html:radio property="tipoPesquisaLogradouro"
					tabindex="5"
					value="<%=ConstantesSistema.TIPO_PESQUISA_COMPLETA.toString()%>" />
				Contendo o texto</td>
		</tr>
		
		<tr>
				<td><strong>CEP:</strong> </td>
				<td>
				<html:text property="idCepInicial" size="8"
				onkeyup="replicaCep();"
				onkeypress="return isCampoNumerico(event);"
					maxlength="8" value=""/>				
		
				<strong>até</strong>
				<html:text property="idCepFinal" size="8"
				onkeypress="return isCampoNumerico(event);"
					maxlength="8" value=""/>
				</td>
		</tr>		
	  <!-- FIM DO FORMULÁRIO -------------------------------------------------------------------------------------------------------- -->

      <tr>
      <td colspan="3">
	      <table width="100%">
    			<tr>     
    				<td width="70%">
			              <INPUT type="button" class="bottonRightCol" onclick="limparForm()" value="Limpar" tabindex="3" style="width: 70px;" name="botaoLimpar">
			              <logic:present name="caminhoRetornoTelaInformarEndereco">
			          		<INPUT TYPE="button" class="bottonRightCol" value="Voltar" onclick="redirecionarSubmit('${caminhoRetornoTelaInformarEndereco}.do?objetoConsulta=1')"/>
			          	</logic:present>
		          	</td>
		          	<td colspan="2" align="right">
		              <INPUT type="button" class="bottonRightCol" value="Pesquisar" tabindex="4" onclick="validarForm(document.forms[0]);" style="width: 70px;" name="botaoPesquisar">
		            </td>
	              </tr>
       		</table>
		 </td>
      </tr>
      </table>

      <p>&nbsp;</p></td>
  </tr>
</table>

</html:form>

</body>

</html:html>
