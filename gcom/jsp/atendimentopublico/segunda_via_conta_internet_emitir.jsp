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

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="true" formName="EmitirSegundaViaContaInternetActionForm" dynamicJavascript="false" />
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>

<logic:equal name="indicadorDocumentoValido" value="1">
	<SCRIPT LANGUAGE="JavaScript">
	<!--
	var bCancel = false; 
	
	function validateEmitirSegundaViaContaInternetActionForm(form) {                                                                   
		if (bCancel) 
	    	return true; 
	   	else 
	    	return validarRequerido(form) && validateCaracterEspecial(form) && validateLong(form) && validateCpf(form) && validateCnpj(form); 
	} 
	
	function caracteresespeciais () { 
		this.aa = new Array("matricula", "Matrícula possui caracteres especiais.", new Function ("varName", " return this[varName];"));
	    this.ab = new Array("cpf", "CPF possui caracteres especiais.", new Function ("varName", " return this[varName];"));
	    this.ac = new Array("cnpj", "CNPJ possui caracteres especiais.", new Function ("varName", " return this[varName];"));
	} 
	
	function IntegerValidations () { 
		this.aa = new Array("matricula", "Matrícula deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
	    this.ab = new Array("cpf", "CPF deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
	    this.ac = new Array("cnpj", "CNPJ deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
	} 
	
	function cpf () { 
		this.aa = new Array("cpf", "CPF inválido.", new Function ("varName", " return this[varName];"));
	} 
	
	function cnpj () { 
		this.aa = new Array("cnpj", "CNPJ inválido.", new Function ("varName", " return this[varName];"));
	}
	    
	function desabilitaCNPJ(){
		var form = document.forms[0];
		if (form.cpf.value.length > 0){
			form.cnpj.value = "";
			form.cnpj.disabled = true;
		} else{
			form.cnpj.disabled = false;
		}
	}
		
	function desabilitaCPF(){
		var form = document.forms[0];
		if (form.cnpj.value.length > 0){
			form.cpf.value = "";
			form.cpf.disabled = true;
		} else{
			form.cpf.disabled = false;
		}
	}
		
	function validarRequerido(form){
			
		var retorno = true;
		var msg = "";
			
		if (form.matricula.value.length < 1){
			msg = "Informe Matrícula \n";
			form.matricula.focus();
		}
			
		if (form.cpf.value.length < 1 && form.cnpj.value.length < 1){
			
			if (msg.length > 0){
				msg = msg + "Informe Documento \n";
			}
			else{
				msg = "Informe Documento \n";
				form.cpf.focus();
			}
		}
			
		if (msg.length > 0){
			alert(msg);
			retorno = false;
		}
		return retorno;
	}
	//-->
	</SCRIPT>
	
</logic:equal>
					
<logic:notEqual name="indicadorDocumentoValido" value="1">

	<SCRIPT LANGUAGE="JavaScript">
	<!--
	var bCancel = false; 

    function validateEmitirSegundaViaContaInternetActionForm(form) {                                                                   
    	if (bCancel) 
      		return true; 
        else 
       		return validarRequerido(form) && validateCaracterEspecial(form) && validateLong(form); 
	} 
	
	function caracteresespeciais () { 
		this.aa = new Array("matricula", "Matrícula possui caracteres especiais.", new Function ("varName", " return this[varName];"));
	} 
	
	function IntegerValidations () { 
		this.aa = new Array("matricula", "Matrícula deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
	}
	    
	function validarRequerido(form){
			
		var retorno = true;
		var msg = "";
		
		if (form.matricula.value.length < 1){
			msg = "Informe Matrícula";
			form.matricula.focus();
		}
		
		if (msg.length > 0){
			alert(msg);
			retorno = false;
		}
		
		return retorno;
	}
	//-->
	</SCRIPT>
	
</logic:notEqual>

<SCRIPT LANGUAGE="JavaScript">
	<!--
	function limparMatricula(form){
		form.matricula.value = '';
		form.matricula.focus();
	}

	function limparDocumento(form){
	
		limparMatricula(form);
	
		form.cpf.value = "";
		form.cnpj.value = "";
		form.cpf.disabled = false;
		form.cnpj.disabled = false;
	}

	function validarForm(form){

		if(testarCampoValorZero(document.EmitirSegundaViaContaInternetActionForm.matricula, 'Matrícula')) {
	
			if(validateEmitirSegundaViaContaInternetActionForm(form)){
    		
    			form.submit();
			}
		}
	}
//-->
</SCRIPT>

</head>

<body leftmargin="5" topmargin="5"
	onload="setarFoco('${requestScope.nomeCampo}')">

<html:form action="${requestScope.nomeAction}" method="post"
	onsubmit="return validateEmitirSegundaViaContaInternetActionForm(this);">

	<%@ include file="/jsp/util/cabecalho.jsp"%>

	<table width="770" border="0" cellspacing="5" cellpadding="0">
		<tr>
			<td width="655" valign="top" class="centercoltext">
			<table height="100%">
				<tr>
					<td></td>
				</tr>
			</table>

			<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
				<tr>
					<td width="11">
						<img border="0" src="<bean:message key="caminho.imagens"/>parahead_left.gif" />
					</td>
					
					
						<td class="parabg">
							<font size="2">
								<logic:present name="ehEmail">
									<strong>Emissão da Conta - Atendimento Internet</strong>
								</logic:present>
								<logic:notPresent name="ehEmail">
									<strong>Emissão da 2ª Via de Conta - Atendimento Internet</strong>
								</logic:notPresent>
							</font>
						</td>

					
					<td width="11">
						<img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif" />
					</td>
				</tr>
			</table>

			<table width="100%" 
				height="34" 
				border="0" 
				align="center"
				cellpadding="0" 
				cellspacing="0">
				
				<tr>
					<td colspan="8">
						<br>
						<br>
						<br>
					
						<div align="center">
							<img border="0" src="<bean:message key="caminho.imagens"/>gsan.gif" />
						</div>
					</td>
				</tr>

				<tr>
					
					<logic:equal name="indicadorDocumentoValido" value="1">
					
						<td colspan="8"><br>
						<br>
						Informe os números da sua <strong>MATRÍCULA</strong>, que se
						encontra na parte superior da sua conta de água, e o seu documento de identificação 
						<strong>CPF</strong> ou <strong>CNPJ</strong>:</td>
					
					</logic:equal>
					
					<logic:notEqual name="indicadorDocumentoValido" value="1">
					
						<td colspan="8"><br>
						<br>
						Informe apenas os números da sua <strong>MATRÍCULA</strong>, que se
						encontra na parte superior da sua conta de água:</td>
					
					</logic:notEqual>


				</tr>

				<tr>
					<td colspan="8">&nbsp;</td>
				</tr>
				
				<tr>
					<td colspan="8">&nbsp;</td>
				</tr>
				
				<tr>
					<td width="15%" align="left">
						<strong>MATRÍCULA:</strong>
						<font color="#FF0000">*</font>
					</td>
					
					<td>
						<html:text property="matricula" 
							size="9"
							maxlength="9" 
							onkeypress="return isCampoNumerico(event);" 
							tabindex="1"/>
						<font size="1"> &nbsp; </font>
					</td>
					
					<logic:equal name="indicadorDocumentoValido" value="1">
					
						<td width="7%" align="left">
							<strong>CPF:</strong>
						</td>
						<td>
							<html:text property="cpf" 
								size="11" 
								maxlength="11" 
								tabindex="2" 
								onkeyup="desabilitaCNPJ();" 
								onkeypress="return isCampoNumerico(event);"/>
						</td>
							
						<td width="7%" align="left">
							<strong>CNPJ:</strong>
						</td>
						<td>
							<html:text property="cnpj" 
								size="14" 
								maxlength="14" 
								tabindex="3" 
								onkeyup="desabilitaCPF();" 
								onkeypress="return isCampoNumerico(event);"/>
						</td>
						
					</logic:equal>
					
					<logic:notEqual name="indicadorDocumentoValido" value="1">
						<td colspan="4">&nbsp;</td>
					</logic:notEqual>
				</tr>
			</table>
			
			<table width="100%" 
				height="34" 
				border="0" 
				align="center"
				cellpadding="0" 
				cellspacing="0">
				
				<tr>
					<td>&nbsp;</td>
				</tr>
				
				<tr>
					<td width="17%"></td>
					<td width="83%">
						<strong>
							<font color="#FF0000">*</font>
						</strong>
						Campos obrigat&oacute;rios
					</td>
				</tr>
				
				<tr>
					<td>&nbsp;</td>
				</tr>

				<tr>
					
					<logic:equal name="indicadorDocumentoValido" value="1">
					
						<td>
							<input name="Button" 
								type="button" 
								class="bottonRightCol"
								value="Desfazer" 
								align="left"
								onclick="javascript:limparDocumento(document.forms[0]);">
						</td>
						
					</logic:equal>
					
					<logic:notEqual name="indicadorDocumentoValido" value="1">
					
						<td>
							<input name="Button" 
								type="button" 
								class="bottonRightCol"
								value="Desfazer" 
								align="left"
								onclick="javascript:limparMatricula(document.forms[0]);">
						</td>
						
					</logic:notEqual>
					
					
					<logic:present name="veioMenu" scope="session">
					
						<td align="left">
							<input type="button" 
								name="ButtonCancelar"
								class="bottonRightCol" 
								value="Cancelar"
								onClick="javascript:window.location.href='/gsan/telaPrincipal.do'">
						</td>
					
					</logic:present>

					<td align="right" height="24">
						<input type="button" 
							name="Button"
							class="bottonRightCol" 
							value="Consultar"
							onClick="javascript:validarForm(document.forms[0])" />
					</td>
					<td>&nbsp;</td>
				</tr>
				
				<tr>
					<td>&nbsp;</td>
				</tr>
				
				<tr>
					<td>&nbsp;</td>
				</tr>

			</table>
			</td>
		</tr>
	</table>

</html:form>
<%@ include file="/jsp/util/rodape.jsp"%>
</body>
</html:html>
