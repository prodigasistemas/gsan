<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>

<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="AdicionarAreaBairroActionForm" />
<script language="JavaScript">


<!-- Begin

   function recuperarDados(codigoRegistro, descricaoRegistro, codigoAuxiliar,tipoConsulta) {
   var form = document.forms[0];

	   if (tipoConsulta == 'distritoOperacional') {
	      form.distritoOperacionalID.value = codigoRegistro;
		  form.distritoOperacionalNome.value = descricaoRegistro;
		  form.distritoOperacionalNome.style.color = "#000000";
		}
   }

	function limparDistritoOperacional() {
	var form = document.forms[0];
    	form.distritoOperacionalID.value = "";
    	form.distritoOperacionalDescricao.value = "";
	}


	function validarForm(form){
		if(validateAdicionarAreaBairroActionForm(form)){
    		form.submit();
		}
	}



function chamarSubmitComUrl(pagina){
	toUpperCase(opener.window.document.forms[0]);
	opener.window.document.forms[0].action = pagina; 
	opener.window.document.forms[0].submit();
}


-->
</script>

</head>


<logic:equal name="fechaPopup" value="false" scope="request">
	<body leftmargin="0" topmargin="0"
		onload="resizePageSemLink(600, 400);javascript:setarFoco('${requestScope.nomeCampo}');">
</logic:equal>

<logic:equal name="fechaPopup" value="true" scope="request">

	<logic:equal name="reloadPage" value="INSERIRBAIRRO">
		<body leftmargin="0" topmargin="0"
			onload="chamarReload('exibirInserirBairroAction.do?reloadPage=OK');window.close()">
	</logic:equal>

	<logic:equal name="reloadPage" value="ATUALIZARBAIRRO">
		<body leftmargin="0" topmargin="0"
			onload="chamarReload('exibirAtualizarBairroAction.do?reloadPage=OK');window.close()">
	</logic:equal>		
	
</logic:equal>


<html:form action="/inserirAreaBairroPopupAction"
	name="AdicionarAreaBairroActionForm"
	type="gcom.gui.cadastro.geografico.AdicionarAreaBairroActionForm"
	method="post"
	onsubmit="return validateAdicionarAreaBairroActionForm(this);">
	<table width="570" border="0" cellspacing="5" cellpadding="0">
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
					<td class="parabg">
					<logic:present name="atualizar">
						Atualizar Área de Bairro
					</logic:present>
					
					<logic:notPresent name="atualizar">
						Inserir Área de Bairro
					</logic:notPresent>
					</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<table width="100%" border="0">

				<tr>
					<td><strong>Município:</strong></td>
					<td><html:text property="municipioId" readonly="true"
						style="background-color:#EFEFEF; border:0" size="5" /> <html:text
						property="municipioDescricao" readonly="true"
						style="background-color:#EFEFEF; border:0" size="45" /></td>
				</tr>

				<tr>
					<td><strong>Bairro:</strong></td>
					<td><html:text property="bairroCodigo" readonly="true"
						style="background-color:#EFEFEF; border:0" size="5"/> <html:text
						property="bairroDescricao" readonly="true"
						style="background-color:#EFEFEF; border:0" size="45" /></td>
				</tr>
				<tr>
					<td>
					<p>&nbsp;</p>
					</td>
				</tr>
				
				
				
				
				
				<tr>
					<logic:present name="atualizar">
						<td colspan="4">Para atualizar uma área de bairro, informe os dados
						abaixo:</td>
					</logic:present>
					
					<logic:notPresent name="atualizar">
						<td colspan="4">Para inserir uma área de bairro, informe os dados
						abaixo:</td>
					</logic:notPresent>
				</tr>

				<tr>
					<td colspan="2">
					<table width="100%" align="center" border="0" bgcolor="#90c7fc">
						<tr>
							<td align="center"><strong>Área de Bairro</strong></td>
						</tr>
						<tr bgcolor="#cbe5fe">
							<td width="100%" align="left">
							<table width="100%" border="0">
								<tr>
									<td><strong>Nome da Área de Bairro:<font color="#FF0000">*</font></strong></td>
									<td><html:text property="areaBairroNome" size="40"
										maxlength="40" tabindex="1" /></td>
								</tr>

								<tr>
									<td><strong>Distrito Operacional:</strong></td>
									
									<td><html:select property="distritoOperacionalID" tabindex="2">
											<html:option value="-1">&nbsp;</html:option>
											<html:options collection="collectionDistritoOperacional"
												labelProperty="descricao" property="id" />
										</html:select> <font size="1">&nbsp; </font></td>
														
								</tr>

							</table>
							</td>
						</tr>
					</table>
					</td>
				</tr>


				<tr>
					<td><strong> <font color="#FF0000"></font></strong></td>
					<td align="right">
					<div align="left"><strong><font color="#FF0000">*</font></strong>
					Campos obrigat&oacute;rios</div>
					</td>
				</tr>

				<tr>
					<td height="0">&nbsp;</td>
				</tr>

				<tr>
					<td><input type="button" name="Button" class="bottonRightCol"
						value="Fechar" tabindex="4" onClick="window.close()" /></td>
					<td align="right">
					
						<logic:present name="atualizar">
							<input type="button" name="Button"
							class="bottonRightCol" value="Atualizar" tabindex="3"
							onClick="validarForm(document.forms[0]);" />
						</logic:present>
						
						<logic:notPresent name="atualizar">
							<input type="button" name="Button"
							class="bottonRightCol" value="Inserir" tabindex="3"
							onClick="validarForm(document.forms[0]);" />
						</logic:notPresent>
						
					</td>
				</tr>


			</table>
			<p>&nbsp;</p>
			<p>&nbsp;</p>
	</table>
</html:form>
</body>

</html:html>
