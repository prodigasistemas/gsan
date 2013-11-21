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
<html:javascript staticJavascript="true" formName="InserirCadastroEmailClienteActionForm" dynamicJavascript="false" />
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>

<script language="JavaScript">
	
	var bCancel = false; 
	
	function validateInserirCadastroEmailClienteActionForm(form) {                                                                   
		if (bCancel) 
	    	return true; 
	   	else 
	    	return validarRequerido(form) && validateCaracterEspecial(form) && validateLong(form) /*&& validateCpf(form) && validateCnpj(form)*/; 
	} 
	
	function caracteresespeciais () { 
		this.aa = new Array("matricula", "Matrícula possui caracteres especiais.", new Function ("varName", " return this[varName];"));
	    this.ab = new Array("cpfCnpjCliente", "CPF/CNPJ do Cliente possui caracteres especiais.", new Function ("varName", " return this[varName];"));
	    this.ac = new Array("cpfSolicitante", "CPF do Solicitante possui caracteres especiais.", new Function ("varName", " return this[varName];"));
	} 
	
	function IntegerValidations () { 
		this.aa = new Array("matricula", "Matrícula deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
	    this.ab = new Array("cpfCnpjCliente", "CPF/CNPJ do Cliente deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
	    this.ac = new Array("cpfSolicitante", "CPF do Solicitante deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
	} 
	
	/*function cpf () { 
		this.aa = new Array("cpf", "CPF inválido.", new Function ("varName", " return this[varName];"));
	} 
	
	function cnpj () { 
		this.aa = new Array("cnpj", "CNPJ inválido.", new Function ("varName", " return this[varName];"));
	}*/
		
	function validarRequerido(form){
			
		var retorno = true;
		var msg = "";
			
		if (form.matricula.value.length < 1){
			msg = "Informe Matrícula \n";
			form.matricula.focus();
		}
		
		if (form.nomeCliente.value.length < 1){
			
			if (msg.length > 0){
				msg = msg + "Informe o nome do Cliente \n";
			}
			else{
				msg = "Informe o nome do Cliente \n";
				form.nomeCliente.focus();
			}
		}
			
		if (form.cpfCnpjCliente.value.length < 1){
			
			if (msg.length > 0){
				msg = msg + "Informe o CPF/CNPJ do Cliente \n";
			}
			else{
				msg = "Informe o CPF/CNPJ do Cliente \n";
				form.cpfCnpjCliente.focus();
			}
		}
		
		if (form.email.value.length < 1 ){
			
			if (msg.length > 0){
				msg = msg + "Informe o email \n";
			}
			else{
				msg = "Informe o email \n";
				form.email.focus();
			}
		}
		
		if (form.nomeSolicitante.value.length < 1){
			
			if (msg.length > 0){
				msg = msg + "Informe o nome do Solicitante \n";
			}
			else{
				msg = "Informe o nome do Solicitante \n";
				form.nomeSolicitante.focus();
			}
		}
		
		if (form.cpfSolicitante.value.length < 1 ){
			
			if (msg.length > 0){
				msg = msg + "Informe o CPF do Solicitante \n";
			}
			else{
				msg = "Informe o CPF do Solicitante \n";
				form.cpfSolicitante.focus();
			}
		}
		
		if (msg.length > 0){
			alert(msg);
			retorno = false;
		}
		return retorno;
	}
	
	function limparMatricula(form){
	
		form.matricula.value = '';
		form.matricula.focus();
		
	}

	function limparForm(form){
	
		//limparMatricula(form);
		
		form.nomeCliente.value = "";
		form.nomeCliente.disabled = true;
		
		form.cpfCnpjCliente.value = "";
		form.cpfCnpjCliente.disabled = true;
		
		form.email.value = "";
		form.email.disabled = true;
		
		form.nomeSolicitante.value = "";
		form.nomeSolicitante.disabled = true;
		
		form.cpfSolicitante.value = "";
		form.cpfSolicitante.disabled = true;
		
		form.telefoneContato.value = "";
		form.telefoneContato.disabled = true;
		
		/*if ( document.getElementById('confirmarNomeCliente') != null ){
		
			document.getElementById('confirmarNomeCliente').disabled = false;
		
		}
		
		if ( document.getElementById('confirmarCpfCnpjCliente') != null ){
		
			document.getElementById('confirmarCpfCnpjCliente').disabled = false;
		
		}*/
		limparConfirmarNomeCliente();
		limparConfirmarCpfCnpjCliente();
		
	}

	function validarForm(form){
	
		if(validateInserirCadastroEmailClienteActionForm(form)){
    		
    		form.submit();
		}
	}
	
	function enviarConfirmacao(){
		
		var form = document.forms[0];
		var enviarEmailConfirmacao = document.getElementById("enviarConfirmacao").value;
		
		
		if (enviarEmailConfirmacao == "OK"){
			
			if (confirm("Confirma alteração do cadastro de email do cliente? ")) {
		
				alert('Você receberá um email para a confirmação do cadastro.');
				
				form.action = "/gsan/inserirCadastroEmailClienteAction.do?ok=sim&enviarEmail=sim";
				
				form.submit();
			}
		}
	}
	
	function verificarDadosCliente(){
			
		var form =  document.forms[0];
		
		form.action = "/gsan/exibirInserirCadastroEmailClienteAction.do?ok=sim";
		form.submit();
	
	}
	
	function validarNomeCliente(){
	
		var form =  document.forms[0];
		
		form.confirmarNomeCliente.value = "confirmado";
		
		document.getElementById('confirmarNomeClienteBotao').disabled = true;
	
	}
	
	function validarCpfCnpjCliente(){
	
		var form =  document.forms[0];
		
		form.confirmarCpfCnpjCliente.value = "confirmado";
		
		document.getElementById('confirmarCpfCnpjClienteBotao').disabled = true;
	
	}
	
	function limparMatricula(){
	
		var form =  document.forms[0];
		
		form.matricula.value = "";
	
	}
	
	function limparNomeCliente(){
	
		var form =  document.forms[0];
		
		form.nomeCliente.value = "";
	
	}
	
	function limparCpfCnpjCliente(){
	
		var form =  document.forms[0];
		
		form.cpfCnpjCliente.value = "";
	
	}
	
	function limparEmail(){
	
		var form =  document.forms[0];
		
		form.email.value = "";
	
	}
	
	function limparNomeSolicitante(){
	
		var form =  document.forms[0];
		
		form.nomeSolicitante.value = "";
	
	}
	
	function limparCpfSolicitante(){
	
		var form =  document.forms[0];
		
		form.cpfSolicitante.value = "";
	
	}
	
	function limparTelefoneContato(){
	
		var form =  document.forms[0];
		
		form.telefoneContato.value = "";
	
	}
	
	function desabilitarCampos(){
		//Desabilita os campos se a matricula do imovel nao for informada
		var form = document.forms[0];

		if ( form.desabilitaCampos.value != null
				&& form.desabilitaCampos.value == "1" ){
		
			form.nomeCliente.value = "";
			form.nomeCliente.disabled = true;
			
			form.cpfCnpjCliente.value = "";
			form.cpfCnpjCliente.disabled = true;
			
			form.email.value = "";
			form.email.disabled = true;
			
			form.nomeSolicitante.value = "";
			form.nomeSolicitante.disabled = true;
			
			form.cpfSolicitante.value = "";
			form.cpfSolicitante.disabled = true;
			
			form.telefoneContato.value = "";
			form.telefoneContato.disabled = true;
		
		}
	
	}
	
	function limparConfirmarNomeCliente(){
		
		var form = document.forms[0];
		
		form.confirmarNomeCliente.value = "";
		
		document.getElementById('confirmarNomeClienteBotao').disabled = false;
	
	}
	
	function limparConfirmarCpfCnpjCliente(){
		
		var form = document.forms[0];
		
		form.confirmarCpfCnpjCliente.value = "";
		
		document.getElementById('confirmarCpfCnpjClienteBotao').disabled = false;
	
	}

</SCRIPT>
</head>

<body leftmargin="5" topmargin="5"
	onload="setarFoco('${requestScope.nomeCampo}'); enviarConfirmacao(); desabilitarCampos();">

<html:form action="/inserirCadastroEmailClienteAction.do"
	name="InserirCadastroEmailClienteActionForm"
	type="gcom.gui.cadastro.InserirCadastroEmailClienteActionForm" method="post"
	onsubmit="return validateInserirCadastroEmailClienteActionForm(this);">
	
	<INPUT TYPE="hidden" ID="enviarConfirmacao" VALUE="${requestScope.enviarConfirmacao}"/>
	
	<html:hidden property="confirmarNomeCliente"/>
	<html:hidden property="confirmarCpfCnpjCliente"/>
	<html:hidden property="desabilitaCampos"/>
	
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
									<strong>Cadastro de Email do Cliente - Atendimento Internet</strong>
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
					
						<td colspan="8"><br>
						<br>
						Para cadastrar ou corrigir o email do cliente, informe os números da sua 
						<strong>MATRÍCULA</strong>, que se encontra na parte superior da sua conta de água:</td>

				</tr>
				
				<tr>
					<td colspan="8">&nbsp;</td>
				</tr>
				
				<tr>
					<td colspan="8">&nbsp;</td>
				</tr>
				
				<tr>
					<td width="15%" align="left">
						<strong>Matrícula:</strong>
						<font color="#FF0000">*</font>
					</td>
					
					<td>
						<html:text property="matricula" 
							size="9"
							maxlength="8" 
							onkeypress="validaEnter(event, 'exibirInserirCadastroEmailClienteAction.do?ok=sim', 'matricula');return isCampoNumerico(event);" 
							onkeyup="limparForm(document.forms[0]);"
							tabindex="1"/>
						<font size="1"> &nbsp; </font>
						<a href="javascript:limparMatricula();limparForm(document.forms[0]);"> <img
											src="<bean:message key="caminho.imagens"/>limparcampo.gif"
											border="0" title="Apagar" /></a>
						<input type="button" 
							name="Button"
							class="bottonRightCol" 
							value="OK"
							onClick="javascript:verificarDadosCliente()" />
					</td>
					<td>&nbsp;</td>
				</tr>
				
				<tr>	
					<td width="30%" align="left">
						<strong>Nome do Cliente:</strong>
						<font color="#FF0000">*</font>
					</td>
					
					<td>
						<html:text property="nomeCliente" 
							size="51"
							maxlength="50" 
							tabindex="2"
							onchange="javascript:limparConfirmarNomeCliente();"/>
						<font size="1"> &nbsp; </font>
						<a href="javascript:limparNomeCliente();"> <img
											src="<bean:message key="caminho.imagens"/>limparcampo.gif"
											border="0" title="Apagar" /></a>
						<input type="button" 
							id="confirmarNomeClienteBotao" 
							name="Button"
							class="bottonRightCol" 
							value="CONFIRMAR"
							onClick="javascript:validarNomeCliente();" />
						
						
					</td>
					<td>&nbsp;</td>
				</tr>
				
				<logic:notPresent name="pessoaFisicaJuridica" >
				
					<tr>	
					<td width="7%" align="left">
							<strong>CPF/CNPJ do Cliente:</strong>
							<font color="#FF0000">*</font>
					</td>
					
					<td>
							<html:text property="cpfCnpjCliente" 
								size="13" 
								maxlength="11" 
								tabindex="3" 
								onkeypress="return isCampoNumerico(event);"
								onchange="javascript:limparConfirmarCpfCnpjCliente();"/>
								
							<a href="javascript:limparCpfCnpjCliente();"> <img
									src="<bean:message key="caminho.imagens"/>limparcampo.gif"
									border="0" title="Apagar" /></a>
								
						<input type="button"
							id="confirmarCpfCnpjClienteBotao" 
							name="Button"
							class="bottonRightCol" 
							value="CONFIRMAR"
							onClick="javascript:validarCpfCnpjCliente();" />
						
					</td>
					<td>&nbsp;</td>
					</tr>
					
				</logic:notPresent>
				
				<logic:equal name="possuiDocumento" value = "true" >
				
					<logic:equal name="pessoaFisicaJuridica" value="pessoaFisica">
				
						<tr>	
						<td width="7%" align="left">
								<strong>CPF/CNPJ do Cliente:</strong>
								<font color="#FF0000">*</font>
						</td>
						
						<td>
								<html:text property="cpfCnpjCliente" 
									size="13" 
									maxlength="11" 
									tabindex="3" 
									onkeypress="return isCampoNumerico(event);"
									onchange="javascript:limparConfirmarCpfCnpjCliente();"
									readonly="true"
									style="background-color:#EFEFEF; border:0; color: gray"
								/>	
							
									
								<a href="javascript:limparCpfCnpjCliente();"> <img
										src="<bean:message key="caminho.imagens"/>limparcampo.gif"
										border="0" title="Apagar" /></a>
									
							<input type="button"
								id="confirmarCpfCnpjClienteBotao" 
								name="Button"
								class="bottonRightCol" 
								value="CONFIRMAR"
								disabled="true"
								onClick="javascript:validarCpfCnpjCliente();" />
							
						</td>
						<td>&nbsp;</td>
						</tr>
					
					</logic:equal>
					
					<logic:equal name="pessoaFisicaJuridica" value="pessoaJuridica">
					
						<tr>	
						<td width="7%" align="left">
								<strong>CPF/CNPJ do Cliente:</strong>
								<font color="#FF0000">*</font>
						</td>
						
						<td>
								<html:text property="cpfCnpjCliente" 
									size="15" 
									maxlength="14" 
									tabindex="3" 
									onkeypress="return isCampoNumerico(event);"
									onchange="javascript:limparConfirmarCpfCnpjCliente();"
									readonly="true"
									style="background-color:#EFEFEF; border:0; color: gray"/>
									
								<a href="javascript:limparCpfCnpjCliente();"> <img
										src="<bean:message key="caminho.imagens"/>limparcampo.gif"
										border="0" title="Apagar" /></a>
									
							<input type="button"
								id="confirmarCpfCnpjClienteBotao" 
								name="Button"
								class="bottonRightCol" 
								value="CONFIRMAR"
								disabled="true"
								onClick="javascript:validarCpfCnpjCliente();" />
							
						</td>
						<td>&nbsp;</td>
						</tr>
					
					</logic:equal>
					
				</logic:equal>
				
				<logic:equal name= "possuiDocumento" value = "false" >
				
					<logic:equal name="pessoaFisicaJuridica" value="pessoaFisica">
				
							<tr>	
							<td width="7%" align="left">
									<strong>CPF/CNPJ do Cliente:</strong>
									<font color="#FF0000">*</font>
							</td>
							
							<td>
									<html:text property="cpfCnpjCliente" 
										size="13" 
										maxlength="11" 
										tabindex="3" 
										onkeypress="return isCampoNumerico(event);"
										onchange="javascript:limparConfirmarCpfCnpjCliente();"
										readonly= "false" />
										
									<a href="javascript:limparCpfCnpjCliente();"> <img
											src="<bean:message key="caminho.imagens"/>limparcampo.gif"
											border="0" title="Apagar" /></a>
										
								<input type="button"
									id="confirmarCpfCnpjClienteBotao" 
									name="Button"
									class="bottonRightCol" 
									value="CONFIRMAR"
									disabled="false"
									onClick="javascript:validarCpfCnpjCliente();" />
								
							</td>
							<td>&nbsp;</td>
							</tr>
						
						</logic:equal>
						
						<logic:equal name="pessoaFisicaJuridica" value="pessoaJuridica">
						
							<tr>	
							<td width="7%" align="left">
									<strong>CPF/CNPJ do Cliente:</strong>
									<font color="#FF0000">*</font>
							</td>
							
							<td>
									<html:text property="cpfCnpjCliente" 
										size="15" 
										maxlength="14" 
										tabindex="3" 
										onkeypress="return isCampoNumerico(event);"
										onchange="javascript:limparConfirmarCpfCnpjCliente();"
										readonly = "false"/>
										
									<a href="javascript:limparCpfCnpjCliente();"> <img
											src="<bean:message key="caminho.imagens"/>limparcampo.gif"
											border="0" title="Apagar" /></a>
										
								<input type="button"
									id="confirmarCpfCnpjClienteBotao" 
									name="Button"
									class="bottonRightCol" 
									value="CONFIRMAR"
									disabled="false"
									onClick="javascript:validarCpfCnpjCliente();" />
								
							</td>
							<td>&nbsp;</td>
							</tr>
						
						</logic:equal>
					
				</logic:equal>
				
				<tr>
					<td width="30%" align="left">
						<strong>Email:</strong>
						<font color="#FF0000">*</font>
					</td>
					
					<td>
						<html:text property="email" 
							size="41"
							maxlength="40" 
							tabindex="4"/>
						<font size="1"> &nbsp; </font>
						<a href="javascript:limparEmail();"> <img
									src="<bean:message key="caminho.imagens"/>limparcampo.gif"
									border="0" title="Apagar" /></a>
					</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td width="30%" align="left">
						<strong>Nome do Solicitante:</strong>
						<font color="#FF0000">*</font>
					</td>
					
					<td>
						<html:text property="nomeSolicitante" 
							size="51"
							maxlength="50" 
							tabindex="5"/>
						<font size="1"> &nbsp; </font>
						<a href="javascript:limparNomeSolicitante();"> <img
									src="<bean:message key="caminho.imagens"/>limparcampo.gif"
									border="0" title="Apagar" /></a>
					</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td width="30%" align="left">
						<strong>CPF do Solicitante:</strong>
						<font color="#FF0000">*</font>
					</td>
					
					<td>
						<html:text property="cpfSolicitante" 
							size="15"
							maxlength="14" 
							tabindex="6"
							onkeypress="return isCampoNumerico(event);"/>
						<font size="1"> &nbsp; </font>
						<a href="javascript:limparCpfSolicitante();"> <img
									src="<bean:message key="caminho.imagens"/>limparcampo.gif"
									border="0" title="Apagar" /></a>
					</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td width="30%" align="left">
						<strong>Telefone para Contato:</strong>
					</td>
					
					<td>
						<html:text property="telefoneContato" 
							size="14"
							maxlength="13" 
							tabindex="7"
							onkeypress="return isCampoNumerico(event);"/>
						<font size="1"> &nbsp; </font>
						<a href="javascript:limparTelefoneContato();"> <img
									src="<bean:message key="caminho.imagens"/>limparcampo.gif"
									border="0" title="Apagar" /></a>
					</td>
					<td>&nbsp;</td>
					
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
					<td width="17%"></td>
					<td width="83%">
						<strong>
							Você receberá um email para a confirmação do cadastro.
						</strong>
					</td>
				</tr>
				
				<tr>
					<td>&nbsp;</td>
				</tr>
				
					<tr>
						<td colspan="2" align="right" height="24">
							<input type="button" 
								name="Button"
								class="bottonRightCol" 
								value="Enviar"
								onClick="javascript:validarForm(document.forms[0]);validarRequerido(document.forms[0]);emailValido(email); " />
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
