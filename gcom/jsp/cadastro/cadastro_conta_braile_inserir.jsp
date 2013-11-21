<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<%@ page import="gcom.util.ConstantesSistema"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
<head>
<%@ include file="/jsp/util/titulo.jsp"%>

<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="true"
	formName="InserirCadastroContaBraileActionForm"
	dynamicJavascript="false" />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>

<script language="JavaScript">
	
	var bCancel = false; 
	
	function validateInserirCadastroContaBraileActionForm(form) {                                                                   
		if (bCancel) 
	    	return true; 
	   	else 
	    	return validarRequerido(form) && validateCaracterEspecial(form) && validateLong(form) /*&& validateCpf(form) && validateCnpj(form)*/; 
	} 
	
	function caracteresespeciais () { 
		this.aa = new Array("matricula", "Matrícula possui caracteres especiais.", new Function ("varName", " return this[varName];"));
	    this.ab = new Array("cpfCnpjCliente", "CPF/CNPJ do Cliente possui caracteres especiais.", new Function ("varName", " return this[varName];"));
	    this.ac = new Array("cpfSolicitante", "CPF do Solicitante possui caracteres especiais.", new Function ("varName", " return this[varName];"));
	    this.ad = new Array("rg", "RG possui caracteres especiais.", new Function ("varName", " return this[varName];"));
	} 
	
	function IntegerValidations () { 
		this.aa = new Array("matricula", "Matrícula deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
	    this.ab = new Array("cpfCnpjCliente", "CPF/CNPJ do Cliente deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
	    this.ac = new Array("cpfSolicitante", "CPF do Solicitante deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
	} 
	
	function DateValidations () {
		this.aa = new Array("dataExpedicao", "Data de Expedição inválida.", new Function ("varName", "this.datePattern='dd/MM/yyyy';  return this[varName];"));
	}

	function validarRequerido(form){
			
		var retorno = true;
		var msg = "";
			
		if (form.matricula.value.length < 1){
			msg = "Informe Matrícula \n";
			form.matricula.focus();
		}
		
		if (form.nomeCliente.value.length < 1){
			
			if (msg.length > 0){
				msg = msg + "Informe o Nome do Cliente \n";
			}
			else{
				msg = "Informe o Nome do Cliente \n";
				form.nomeCliente.focus();
			}
		}
		/*	
		if (form.cpfCnpjCliente.value.length < 1){
			
			if (msg.length > 0){
				msg = msg + "Informe o CPF/CNPJ do Cliente \n";
			}
			else{
				msg = "Informe o CPF/CNPJ do Cliente \n";
				form.cpfCnpjCliente.focus();
			}
		}
		*/
		
		if (form.email.value.length < 1 ){
			
			if (msg.length > 0){
				msg = msg + "Informe o E-mail \n";
			}
			else{
				msg = "Informe o E-mail \n";
				form.email.focus();
			}
		}
		
		if (form.nomeSolicitante.value.length < 1){
			
			if (msg.length > 0){
				msg = msg + "Informe o Nome do Solicitante \n";
			}
			else{
				msg = "Informe o Nome do Solicitante \n";
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
		
		if (form.rg.value.length < 1 ){
			
			if (msg.length > 0){
				msg = msg + "Informe o Registro Geral \n";
			}
			else{
				msg = "Informe o Registro Geral \n";
				form.rg.focus();
			}
		}
		
		if(form.orgaoExpeditor.value == '-1'){
			
			if (msg.length > 0){
				msg = msg + "Informe o Órgão Expeditor \n";
			}
			else{
				msg = "Informe o Órgão Expeditor \n";
				form.orgaoExpeditor.focus();
			}
		}
		
		if(form.unidadeFederacao.value == '-1'){
			
			if (msg.length > 0){
				msg = msg + "Informe a Unidade Federação \n";
			}
			else{
				msg = "Informe a Unidade Federação \n";
				form.unidadeFederacao.focus();
			}
		}
		
		if (form.dataExpedicao.value.length < 10 ){
			
			if (msg.length > 0){
				msg = msg + "Informe a Data de Expedição \n";
			}
			else{
				msg = "Informe a Data de Expedição \n";
				form.dataExpedicao.focus();
			}
		}
		
		if (form.telefoneContato.value.length < 1){
			
			if (msg.length > 0){
				msg = msg + "Informe o Telefone de Contato \n";
			}
			else{
				msg = "Informe o Telefone de Contato \n";
				form.telefoneContato.focus();
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
		
		form.rg.value = "";
		form.rg.disabled = true;
		
		form.orgaoExpeditor.value = "-1";
		form.orgaoExpeditor.disabled = true;
		
		form.unidadeFederacao.value = "-1";
		form.unidadeFederacao.disabled = true;
		
		form.dataExpedicao.value = "";
		form.dataExpedicao.disabled = true;
		
	}

	function validarForm(form){
	
		if(validateInserirCadastroContaBraileActionForm(form)){
    		
    		form.submit();
		}
	}
	
	function verificarDadosCliente(){
			
		var form =  document.forms[0];
		
		form.action = "/gsan/exibirInserirCadastroContaBraileAction.do?ok=sim";
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
	
	function limparRg(){
	
		var form = document.forms[0];
		
		form.rg.value = "";
	}
	
	function limparOrgaoExpeditor(){
	
		var form = document.forms[0];
		
		form.orgaoExpeditor.value = "";
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
			
			form.rg.value = "";
			form.rg.disabled = true;
			
			form.orgaoExpeditor.value = "-1";
			form.orgaoExpeditor.disabled = true;
			
			form.unidadeFederacao.value = "-1";
			form.unidadeFederacao.disabled = true;
			
			form.dataExpedicao.value = "";
			form.dataExpedicao.disabled = true;
		}
	}

</SCRIPT>
</head>

<body leftmargin="5" topmargin="5"
	onload="setarFoco('${requestScope.nomeCampo}'); desabilitarCampos();">

<html:form action="/inserirCadastroContaBraileAction.do"
	name="InserirCadastroContaBraileActionForm"
	type="gcom.gui.cadastro.InserirCadastroContaBraileActionForm"
	method="post"
	onsubmit="return validateInserirCadastroContaBraileActionForm(this);">

	<html:hidden property="confirmarNomeCliente" />
	<html:hidden property="confirmarCpfCnpjCliente" />
	<html:hidden property="desabilitaCampos" />

	<table width="770" border="0" cellspacing="5" cellpadding="0">
		<tr>
			<td width="655" valign="top" class="centercoltext">
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


					<td class="parabg"><font size="2"> <strong>Solicitação da Conta em BRAILE</strong> </font></td>


					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" />
					</td>
				</tr>
			</table>

			<table width="100%" height="34" border="0" align="center"
				cellpadding="0" cellspacing="0">

				<tr>
					<td colspan="8"><br>
					<br>
					<br>

					<div align="center"><img border="0"
						src="<bean:message key="caminho.imagens"/>gsan.gif" /></div>
					</td>
				</tr>

				<tr>
					<td colspan="8"><br>
					<br>
					Para solicitar a emissão da conta em <strong>BRAILE</strong>,
					informe os números da sua <strong>MATRÍCULA</strong>, que se
					encontra na parte superior da sua conta de água:</td>
				</tr>

				<tr>
					<td colspan="8">&nbsp;</td>
				</tr>

				<tr>
					<td colspan="8">&nbsp;</td>
				</tr>

				<tr>
					<td width="15%" align="left"><strong>Matrícula:</strong> <font
						color="#FF0000">*</font></td>

					<td><html:text property="matricula" size="9" maxlength="8"
						onkeypress="validaEnter(event, 'exibirInserirCadastroContaBraileAction.do?ok=sim', 'matricula');return isCampoNumerico(event);"
						onkeyup="limparForm(document.forms[0]);" tabindex="1" /> <font
						size="1"> &nbsp; </font> <a
						href="javascript:limparMatricula();limparForm(document.forms[0]);">
					<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar" /></a> <input type="button"
						name="Button" class="bottonRightCol" value="OK"
						onClick="javascript:verificarDadosCliente();" /></td>
					<td>&nbsp;</td>
				</tr>

				<tr>
					<td width="30%" align="left" style="margin-top: 5px;"><strong>Nome do Cliente:</strong> <font
						color="#FF0000">*</font></td>

					<td><html:text property="nomeCliente" size="51" maxlength="50"
						tabindex="2" readonly="true" 
						style="background-color:#EFEFEF; border:0; color: #000000; margin-top: 5px;" /></td>
					<td>&nbsp;</td>
				</tr>

				<tr>
					<td width="7%" align="left" style="margin-top: 5px;"><strong>CPF/CNPJ do Cliente:</strong>
					<font color="#FF0000"></font></td>

					<td><html:text property="cpfCnpjCliente" size="13" maxlength="11"
						tabindex="3" readonly="true"
						style="background-color:#EFEFEF; border:0; color: #000000; margin-top: 5px;"/></td>
					<td>&nbsp;</td>
				</tr>

				<tr>
					<td width="30%" align="left"><strong>Email:</strong> <font
						color="#FF0000">*</font></td>

					<td><html:text property="email" size="41" maxlength="40"
						tabindex="4" style="text-transform: none;" /> <font size="1">
					&nbsp; </font> <a href="javascript:limparEmail();"> <img
						src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar" /></a></td>
					<td>&nbsp;</td>
				</tr>

				<tr>
					<td width="30%" align="left"><strong>Nome do Solicitante:</strong>
					<font color="#FF0000">*</font></td>

					<td><html:text property="nomeSolicitante" size="51" maxlength="50"
						tabindex="5" /> <font size="1"> &nbsp; </font> <a
						href="javascript:limparNomeSolicitante();"> <img
						src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar" /></a></td>
					<td>&nbsp;</td>
				</tr>

				<tr>
					<td width="30%" align="left"><strong>CPF do Solicitante:</strong> <font
						color="#FF0000">*</font></td>

					<td><html:text property="cpfSolicitante" size="15" maxlength="14"
						tabindex="6" onkeypress="return isCampoNumerico(event);" /> <font
						size="1"> &nbsp; </font> <a
						href="javascript:limparCpfSolicitante();"> <img
						src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar" /></a></td>
					<td>&nbsp;</td>
				</tr>

				<tr>
					<td width="30%" align="left"><strong>Registro Geral:</strong> <font
						color="#FF0000">*</font></td>

					<td><html:text property="rg" size="13" maxlength="13" tabindex="7"
						onkeypress="return isCampoNumerico(event);" /> <font size="1">
					&nbsp; </font> <a href="javascript:limparRg();"> <img
						src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar" /></a></td>
					<td>&nbsp;</td>
				</tr>

				<tr>
					<td width="30%" align="left" style="margin-top: 5px;"><strong>&Oacute;rg&atilde;o Expeditor:</strong>
					<font color="#FF0000">*</font></td>
					<td><html:select property="orgaoExpeditor" tabindex="8" style="margin-top: 5px;">
						<html:option
							value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
						<html:options collection="orgaosExpedidores"
							labelProperty="descricaoAbreviada" property="id" />
					</html:select></td>
					<td>&nbsp;</td>
				</tr>

				<tr>
					<td height="24" style="margin-top: 5px;"><strong>Unidade Federação:</strong><font color="#FF0000">*</font></td>
					<td><html:select property="unidadeFederacao" tabindex="9" style="margin-top: 5px;">
						<html:option
							value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
						<html:options collection="unidadesFederacao" labelProperty="sigla"
							property="id" />
					</html:select></td>
				</tr>

				<tr>
					<td height="24" style="margin-top: 5px;"><strong>Data de Expedição:</strong><font color="#FF0000">*</font></td>
					<td><html:text property="dataExpedicao" size="10" maxlength="10" style="margin-top: 5px;"
						tabindex="10" onkeyup="javascript:mascaraData(this, event);" /> <a
						href="javascript:abrirCalendario('InserirCadastroContaBraileActionForm', 'dataExpedicao')">
					<img border="0"
						src="<bean:message key="caminho.imagens"/>calendario.gif"
						width="20" border="0" align="absmiddle" alt="Exibir Calendário" /></a>
					<font size="2">dd/mm/aaaa</font></td>
				</tr>

				<tr>
					<td width="30%" align="left"><strong>Telefone para Contato:</strong><font color="#FF0000">*</font>
					</td>

					<td><html:text property="telefoneContato" size="14" maxlength="13"
						tabindex="11" onkeypress="return isCampoNumerico(event);" /> <font
						size="1"> &nbsp; </font> <a
						href="javascript:limparTelefoneContato();"> <img
						src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar" /></a></td>
					<td>&nbsp;</td>

				</tr>
			</table>

			<table width="100%" height="34" border="0" align="center"
				cellpadding="0" cellspacing="0">

				<tr>
					<td>&nbsp;</td>
				</tr>

				<tr>
					<td width="17%"></td>
					<td width="83%"><strong> <font color="#FF0000">*</font> </strong>
					Campos obrigat&oacute;rios</td>
				</tr>

				<tr>
					<td>&nbsp;</td>
				</tr>

				<tr>
					<td colspan="2" align="right" height="24"><input type="button"
						name="Button" class="bottonRightCol" value="Enviar"
						onClick="javascript:validarForm(document.forms[0]);emailValido(email); " />
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
