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
function validarFormLogradouro(){

	var formulario = document.forms[0];
	var objCodMunicipio = returnObject(formulario, "codigoMunicipio");
	var objNomMunicipio = returnObject(formulario, "nomeMunicipio");

	if (objCodMunicipio.value.length > 0 && !testarCampoValorZero(objCodMunicipio, "Município")){
		objCodMunicipio.focus();
	}
	else if(validateExibirPesquisarLogradouroActionForm(formulario)){
		formulario.submit();
		resizePageSemLink(485, 415);
		
	}
}

function limparMunicipio(){
	var form = document.forms[0];
	
	if(form.codigoMunicipio.disabled != true){
		form.codigoMunicipio.value = "";
		form.nomeMunicipio.value = "";
	}
}

function limparBairro(){
	var form = document.forms[0];
	
	if(form.codigoBairro.disabled != true){
		form.codigoBairro.value = "";
		form.nomeBairro.value = "";
	}
}

function limparCep(){
	var form = document.forms[0];
	
	form.cep.value = "";
	form.descricaoCep.value = "";
}

function limparForm(){
	var form = document.forms[0];
	
	form.titulo.value = "-1";
	form.tipoLogradouro.value = "-1";
	form.nome.value = "";
	form.nomePopular.value = "";
}
//-->
</SCRIPT>

</head>

<body leftmargin="0" topmargin="0" onload="resizePageSemLink(590, 520); setarFoco('${requestScope.nomeCampo}');">
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
          <td class="parabg">Pesquisar Logradouro</td>
          <td width="11"><img src="<bean:message key="caminho.imagens"/>parahead_right.gif" editor="Webstyle4" moduleid="Album Photos (Project)\toptab_page2_parahead_right.xws" border="0" /></td>
        </tr>
      </table>
      <p>&nbsp;</p>

      <html:form action="/pesquisarLogradouroAction" method="post">
      <table width="100%" border="0">
        <tr>
          <td colspan="4">Preencha os campos para pesquisar um logradouro:</td>
          <logic:present scope="application" name="urlHelp">
				<td align="right"><a href="javascript:abrirPopupHelp('${applicationScope.urlHelp}cadastroEnderecoLogradouroPesquisar', 500, 700);"><span style="font-weight: bold"><font color="#3165CE">Ajuda</font></span></a></td>									
		  </logic:present>
		  <logic:notPresent scope="application" name="urlHelp">
				<td align="right"><span style="font-weight: bold"><font color=#696969><u>Ajuda</u></font></span></td>									
		  </logic:notPresent>
          	
    	</tr>
      </table>
    
      <table width="100%" border="0">
        <tr>
          <td width="21%" height="24"><strong>Município:<font color="#FF0000">*</font></strong></td>
          <td colspan="3">
          
			          	<logic:present name="bloquearMunicipio">
						
							<html:text maxlength="4" property="codigoMunicipio" size="4" tabindex="1" disabled="true"/>
					
							<img width="23" height="21" border="0" src="<bean:message key='caminho.imagens'/>pesquisa.gif" title="Pesquisar Município" />
						
						</logic:present>
			          <logic:notPresent name="bloquearMunicipio">
          						<html:text property="codigoMunicipio" style="width: 50;" maxlength="4"
          							onkeypress="validaEnter(event, 'exibirPesquisarLogradouroAction.do?objetoConsulta=1','codigoMunicipio');"/>
		          				<a href="javascript:redirecionarSubmit('exibirPesquisarLogradouroAction.do?chamarPesquisa=exibirPesquisarMunicipio');">
        		                	 <img border="0" src="<bean:message key="caminho.imagens"/>pesquisa.gif" border="0"/></a>
        		      </logic:notPresent>

   		      	<logic:present name="codigoMunicipioNaoEncontrada" scope="request">
						<input type="text" name="nomeMunicipio" size="30" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000" value="<bean:message key="atencao.municipio.inexistente"/>"/>
                </logic:present>

                      <logic:notPresent name="codigoMunicipioNaoEncontrada" scope="request">
                        <html:text property="nomeMunicipio" size="30" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>
                      </logic:notPresent>
                      
                      <logic:notPresent name="bloquearMunicipio">
						<a href="javascript:limparMunicipio();limparBairro();"> <img
						src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar" /></a>
					  </logic:notPresent>
          
          </td>
          
        </tr>
        <tr>
			<td><strong>Bairro:</strong></td>
			<td colspan="3">
					
					<logic:present name="bloquearBairro">
						
							<html:text maxlength="4" property="codigoBairro" size="4" tabindex="1" disabled="true"/>
					
							<img width="23" height="21" border="0" src="<bean:message key='caminho.imagens'/>pesquisa.gif" title="Pesquisar Município" />
						
						</logic:present>
			          <logic:notPresent name="bloquearBairro">
			
							<html:text property="codigoBairro" style="width: 50;" maxlength="4"
		          					onkeypress="validaEnter(event, 'exibirPesquisarLogradouroAction.do?objetoConsulta=1','codigoBairro');"/>
		          			<a href="javascript:redirecionarSubmit('exibirPesquisarLogradouroAction.do?chamarPesquisa=exibirPesquisarBairro');">
		                         <img border="0" src="<bean:message key="caminho.imagens"/>pesquisa.gif" border="0"/></a>
		              </logic:notPresent>

   		      		<logic:present name="codigoBairroNaoEncontrada" scope="request">
						<input type="text" name="nomeBairro" size="30" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000" value="<bean:message key="atencao.bairro.inexistente"/>"/>
                    </logic:present>

                    <logic:notPresent name="codigoBairroNaoEncontrada" scope="request">
                        <html:text property="nomeBairro" size="30" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>
                    </logic:notPresent>
					<logic:notPresent name="bloquearBairro">
						<a href="javascript:limparBairro();"> <img
							src="<bean:message key="caminho.imagens"/>limparcampo.gif"
							border="0" title="Apagar" /></a>
					</logic:notPresent>
			</td>
        </tr>
        <tr>
          <td height="24"><strong>Tipo:</strong></td>
          <td colspan="3">
          <html:select property="tipoLogradouro" style="width: 200;">
			<html:option value="-1">&nbsp;</html:option>
          	<html:options collection="tiposLogradouro" labelProperty="descricao" property="id"/>
          </html:select></td>
        </tr>
        <tr>
          <td height="24"><strong>Título:</strong></td>
          <td colspan="3">
          <html:select property="titulo" style="width: 200;">
			<html:option value="-1">&nbsp;</html:option>
          	<html:options collection="titulosLogradouro" labelProperty="descricao" property="id"/>
          </html:select></td>
        </tr>
        <tr>
          <td width="21%" height="24"><strong>Nome:</strong></td>
          <td colspan="3"><html:text property="nome" style="width: 250;" maxlength="40"  size="50"/></td>
        </tr>
        <tr>
				<td>&nbsp;</td>
				<td valign="top"><html:radio property="tipoPesquisa"
						value="<%=ConstantesSistema.TIPO_PESQUISA_INICIAL.toString()%>" />
					Iniciando pelo texto<html:radio property="tipoPesquisa"
						tabindex="5"
						value="<%=ConstantesSistema.TIPO_PESQUISA_COMPLETA.toString()%>" />
					Contendo o texto</td>
				</tr>
        <tr>
          <td width="21%" height="24"><strong>Nome popular:</strong></td>
          <td colspan="3"><html:text property="nomePopular" style="width: 250;" maxlength="30"/></td>
        </tr>
        <tr>
				<td>&nbsp;</td>
				<td><html:radio property="tipoPesquisaPopular"
						value="<%=ConstantesSistema.TIPO_PESQUISA_INICIAL.toString()%>" />
					Iniciando pelo texto<html:radio property="tipoPesquisaPopular"
						tabindex="5"
						value="<%=ConstantesSistema.TIPO_PESQUISA_COMPLETA.toString()%>" />
					Contendo o texto</td>
				</tr>
        <tr>
          <td width="21%" height="24"><strong>Cep:</strong></td>
          <td colspan="3"><html:text property="cep" size="8" maxlength="8"
          					onkeypress="validaEnter(event, 'exibirPesquisarLogradouroAction.do?objetoConsulta=1','cep');"/>
          <a href="javascript:redirecionarSubmit('exibirPesquisarLogradouroAction.do?chamarPesquisa=exibirPesquisarCep');">
                         <img border="0" src="<bean:message key="caminho.imagens"/>pesquisa.gif" border="0"/></a>

   		      <logic:present name="cepNaoEncontrada" scope="request">
			<input type="text" name="descricaoCep" size="30" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000" value="<bean:message key="atencao.cep.inexistente"/>"/>
                      </logic:present>

                      <logic:notPresent name="cepNaoEncontrada" scope="request">
                        <html:text property="descricaoCep" size="30" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>
                      </logic:notPresent>
						<a href="javascript:limparCep();"> <img
						src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar" /></a>
          
          </td>
          
        </tr>
        <tr>
          <td height="24" colspan="4">&nbsp;</td>
        </tr>
        <tr>
		        <td height="24" colspan="3" width="80%">
		          	<INPUT TYPE="button" class="bottonRightCol" value="Limpar" onclick="limparForm();limparMunicipio();limparCep();limparBairro();"/>
        			  	&nbsp;&nbsp;
        		 
       			  	<logic:present name="caminhoRetornoTelaPesquisaLogradouro">
		          		<INPUT TYPE="button" class="bottonRightCol" value="Voltar" onclick="redirecionarSubmit('${caminhoRetornoTelaPesquisaLogradouro}.do?objetoConsulta=1')"/>
        		  	</logic:present>
        		  	</td>
					<td align="right">
					
					<input type="button" onclick="validarFormLogradouro(document.forms[0]);" class="bottonRightCol" value="Pesquisar"></td>
        </tr>
      </table>
      </html:form>
      <p>&nbsp;</p></td>
  </tr>
</table>
</body>
</html:html>

