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
	href="<bean:message key="caminho.css"/>

EstilosCompesa.css"
	type="text/css">
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="PesquisarActionForm"
	dynamicJavascript="false" />
	

<script type="text/javascript" language="Javascript1.1"> 

    var bCancel = false; 

    function validatePesquisarActionForm(form) {                                                                   
        if (bCancel) 
      return true; 
        else 
       return validateLong(form) && validateCaracterEspecial(form); 
   } 

    function caracteresespeciais () { 
     this.as = new Array("nomeBairro", "Nome Bairro possui caracteres especiais.", new Function ("varName", " return this[varName];"));
    } 

    function IntegerValidations () { 
     this.ai = new Array("idMunicipio", "Município deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
    }
    
    function validarForm(form){
    
    	var objMunicipio = returnObject(form, "idMunicipio");
		var voltarSituacao = false;
		
		if (objMunicipio.disabled){
			objMunicipio.disabled = false;
			voltarSituacao = true;
		}
		
		var objNomeBairro = returnObject(form, "nomeBairro");
		
		if (validatePesquisarActionForm(form)){
		
			if (objMunicipio.value.length > 0 &&
				!testarCampoValorZero(objMunicipio, "Município ")){
				objMunicipio.focus();
			}
			else{
				redirecionarSubmit('pesquisarBairroAction.do');
			} 
		}
		else if (voltarSituacao){
			objMunicipio.disabled = true;
		}
    } 
    
    
    function limparForm(form){
    	redirecionarSubmit('exibirPesquisarBairroAction.do?limparForm=OK');
    }
    
    
    function limparPesquisaMunicipio(form){
    
    	var objMunicipio = returnObject(form, "idMunicipio");
    	var objDescricaoMunicipio = returnObject(form, "descricaoMunicipio");
    	
    	objMunicipio.value = "";
    	objDescricaoMunicipio.value = "";
    	
    	objMunicipio.focus();
    }
    
    function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {

		var form = document.forms[0];

		if (tipoConsulta == 'municipio') {
    		form.idMunicipio.value = codigoRegistro;
      		form.descricaoMunicipio.value = descricaoRegistro;
      		form.descricaoMunicipio.style.color = "#000000";
      		form.nomeBairro.value = "";
    	}
    }	


</script>

</head>

<body leftmargin="5" topmargin="5"
	onload="resizePageSemLink(545, 340);javascript:setarFoco('${requestScope.nomeCampo}');">
	
<html:form action="/exibirPesquisarBairroAction" method="post">
	
	<table width="502" border="0" cellspacing="5" cellpadding="0">
		<tr>
			<td width="502" valign="top" class="centercoltext">
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
					<td class="parabg">Pesquisar Bairro</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>
					<td>Preencha os campos para pesquisar um bairro:</td>
					<logic:present scope="application" name="urlHelp">
						<td align="right"><a href="javascript:abrirPopupHelp('${applicationScope.urlHelp}cadastroEnderecoBairroPesquisar', 500, 700);"><span style="font-weight: bold"><font color="#3165CE">Ajuda</font></span></a></td>									
					</logic:present>
					<logic:notPresent scope="application" name="urlHelp">
						<td align="right"><span style="font-weight: bold"><font color=#696969><u>Ajuda</u></font></span></td>									
					</logic:notPresent>
    			</tr>
    		</table>
    
    		<table width="100%" border="0">
				<tr>
					<td width="80"><strong>Munic&iacute;pio:<font color="#FF0000">*</font></strong></td>
					<td colspan="2">
					
					<logic:empty name="PesquisarActionForm" property="idMunicipio">
						
						<html:text maxlength="4" property="idMunicipio" size="4" tabindex="1"
						onkeypress="return validaEnter(event, 'exibirPesquisarBairroAction.do?pesquisarMunicipio=OK', 'idMunicipio');" />
					
						<a href="javascript:redirecionarSubmit('exibirPesquisarMunicipioAction.do?caminhoRetornoTelaPesquisaMunicipio=exibirPesquisarBairroAction');">
						<img width="23" height="21" border="0"
						src="<bean:message key='caminho.imagens'/>pesquisa.gif" title="Pesquisar Município" /></a>
					
					</logic:empty>
		
					<logic:notEmpty name="PesquisarActionForm" property="idMunicipio">
						
						<logic:notPresent name="municipioRecebido">
						
							<html:text maxlength="4" property="idMunicipio" size="4" tabindex="1"
							onkeypress="return validaEnter(event, 'exibirPesquisarBairroAction.do?pesquisarMunicipio=OK', 'idMunicipio');" />
					
							<a href="javascript:abrirPopup('exibirPesquisarMunicipioAction.do');">
							<img width="23" height="21" border="0"
							src="<bean:message key='caminho.imagens'/>pesquisa.gif" title="Pesquisar Município" /></a>
						
						</logic:notPresent>
						
						<logic:present name="municipioRecebido">
						
							<html:text maxlength="4" property="idMunicipio" size="4" tabindex="1" disabled="true"/>
					
							<img width="23" height="21" border="0" src="<bean:message key='caminho.imagens'/>pesquisa.gif" title="Pesquisar Município" />
						
						</logic:present>
						
					</logic:notEmpty>
					
					<logic:present name="idMunicipioNaoEncontrado">
					
						<logic:equal name="idMunicipioNaoEncontrado" value="exception">
							<html:text property="descricaoMunicipio" size="40" maxlength="30"
									readonly="true"
											style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:equal>
						<logic:notEqual name="idMunicipioNaoEncontrado" value="exception">
								<html:text property="descricaoMunicipio" size="40" maxlength="30"
									readonly="true"
									style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEqual>
						
					</logic:present> 
					
					<logic:notPresent name="idMunicipioNaoEncontrado">
					
						<logic:empty name="PesquisarActionForm" property="idMunicipio">
							<html:text property="descricaoMunicipio" value="" size="40"
							maxlength="30" readonly="true"
							style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:empty>
						
						<logic:notEmpty name="PesquisarActionForm" property="idMunicipio">
							<html:text property="descricaoMunicipio" size="40" maxlength="30"
							readonly="true"
							style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEmpty>
		
					</logic:notPresent>
					
					<logic:empty name="PesquisarActionForm" property="idMunicipio">
						<a href="javascript:limparPesquisaMunicipio();"> <img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar" /></a>
					</logic:empty>
		
					<logic:notEmpty name="PesquisarActionForm" property="idMunicipio">
						
						<logic:notPresent name="municipioRecebido">
						
							<a href="javascript:limparPesquisaMunicipio(document.forms[0]);"> <img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
							border="0" title="Apagar" /></a>
						
						</logic:notPresent>
						
						<logic:present name="municipioRecebido">
						
							<img src="<bean:message key="caminho.imagens"/>limparcampo.gif" border="0"/>
						
						</logic:present>
						
					</logic:notEmpty>
								
					</td>
			  	</tr>
				<tr>
					<td><strong>Nome Bairro:</strong></td>
					<td colspan="2"><html:text maxlength="30" property="nomeBairro" tabindex="2"
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
					<td>&nbsp;</td>
					<td colspan="2">&nbsp;</td>
				</tr>
			</table>
			<table width="100%" border="0">
			<tr>
          		<td>
			      	<INPUT TYPE="button" class="bottonRightCol" value="Limpar" onclick="limparForm(document.forms[0]);" style="width: 70px;"/>
			       	&nbsp;&nbsp;
			       	<logic:present name="caminhoRetornoTelaPesquisaBairro">
			       		<INPUT TYPE="button" class="bottonRightCol" value="Voltar" onclick="redirecionarSubmit('${caminhoRetornoTelaPesquisaBairro}.do?objetoConsulta=1')"/>
			       	</logic:present>
          		</td>
          		<td align="right">
 
          		<INPUT TYPE="button" class="bottonRightCol" value="Pesquisar" onclick="validarForm(document.forms[0]);"/></td>
        	</tr>
        	</table>
			<p>&nbsp;</p>
			</td>
		</tr>
	</table>
</html:form>
</body>
</html:html>
