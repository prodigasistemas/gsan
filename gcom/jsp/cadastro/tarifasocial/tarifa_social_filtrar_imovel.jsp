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
<html:javascript formName="ManterTarifaSocialActionForm"
	dynamicJavascript="false" staticJavascript="true" />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script>
<!--

    var bCancel = false;

    function validateManterTarifaSocialActionForm(form) {
        if (bCancel)
      return true;
        else
       if (validateCaracterEspecial(form) && validateLong(form) 
       && testarCampoValorZero(form.registroAtendimento, "Registro de Atendimento") 
       && testarCampoValorZero(form.idImovel, "Matrícula")) {
       		if (form.registroAtendimento.value == "" && form.idImovel.value == "") {
       			alert('Informe Registro de Atendimento ou Imóvel');
       			return false;
       		} else if (form.registroAtendimento.value != "" && form.nomeRegistroAtendimento.value == "") {
       			alert('Pesquise Registro de Atendimento');
       			return false;
       		} else if (form.idImovel.value != "" && form.inscricaoImovel.value == "") {
       			alert('Pesquise Imóvel');   
       			return false;
       		} else {
       			return true;
       		}
       }
   }

    function caracteresespeciais () {
     this.aa = new Array("registroAtendimento", " Registro de Atendimento deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
     this.ab = new Array("idImovel", " Imóvel deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
    }

    function IntegerValidations () { 
     this.aa = new Array("registroAtendimento", "Registro de Atendimento deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
     this.aa = new Array("idImovel", "Imóvel deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
    }

    function recuperarDadosPopup(codigoRegistro,descricaoRegistro,tipoConsulta){

       var form = document.forms[0];

       if (tipoConsulta == 'registroAtendimento') {
      		form.registroAtendimento.value = codigoRegistro;
            form.action = 'manterTarifaSocialWizardAction.do?action=exibirManterTarifaSocialImovelAction'
            form.submit();
       } else if (tipoConsulta == 'imovel') {
      		form.idImovel.value = codigoRegistro;
            form.action = 'manterTarifaSocialWizardAction.do?action=exibirManterTarifaSocialImovelAction'
            form.submit();
       }
       
       
    }
    
	function limparRegistroAtendimento(){
		var form = document.forms[0];
		form.registroAtendimento.value = "";
		form.nomeRegistroAtendimento.value = "";
		form.idImovel.readOnly = false;
		
		if (!form.registroAtendimento.readOnly) {
			form.idImovel.value = "";
			form.inscricaoImovel.value = "";
			endereco.style.display = "none";
			economias.style.display = "none";
		}
		
	}
	
	function limparImovel(){
		var form = document.forms[0];
		form.registroAtendimento.readOnly = false;
		
		if (!form.idImovel.readOnly) {
			form.idImovel.value = "";
			form.inscricaoImovel.value = "";
			endereco.style.display = "none";
			economias.style.display = "none";
		}
	}

function pesquisarRegistroAtendimento(){

	var form = document.forms[0];
	
	if (!form.registroAtendimento.readOnly) {	
		abrirPopup('exibirPesquisarRegistroAtendimentoAction.do', 400, 800);
	}
	
}

function pesquisarImovel(){
	
	var form = document.forms[0];

	if (!form.idImovel.readOnly) {
		abrirPopup('exibirPesquisarImovelAction.do', 400, 800);
	}
	
}

function limparRegistroAtendimentoTecla() {
	var form = document.forms[0];
	form.nomeRegistroAtendimento.value = "";
	form.idImovel.value = "";
	form.inscricaoImovel.value = "";
	endereco.style.display = "none";
	economias.style.display = "none";
	
	if (form.registroAtendimento.value.length < 1) {
		form.idImovel.readOnly = true;
	} else {
		form.idImovel.readOnly = false;
	}
}

function limparImovelTecla() {
	var form = document.forms[0];
	
	if (!form.idImovel.readOnly) {
	
		form.nomeRegistroAtendimento.value = "";
		form.inscricaoImovel.value = "";
		endereco.style.display = "none";
		economias.style.display = "none";
	
		if (form.idImovel.value.length < 1) {
			form.registroAtendimento.readOnly = true;
			form.registroAtendimento.value = "";
		} else {
			form.registroAtendimento.readOnly = false;
		}
	}
}

function bloquearCampos() {
	
	var form = document.forms[0];
	
	if (form.registroAtendimento.value != null && form.registroAtendimento.value != "") {
		form.idImovel.readOnly = true;
	} else if (form.idImovel.value != null && form.idImovel.value != "") {
		form.registroAtendimento.readOnly = true;
	}
	
}

function verificarExibicaoEndereco() {

	var form = document.forms[0];
	
	if ((form.registroAtendimento.value.length < 1 || form.nomeRegistroAtendimento.value.length < 1)
	&& (form.idImovel.value.length < 1 || form.inscricaoImovel.value.length < 1)) {
		endereco.style.display = "none";
		economias.style.display = "none";
	}
		
}

-->
</script>


</head>

<body leftmargin="5" topmargin="5"
	onload="setarFoco('${requestScope.nomeCampo}');bloquearCampos();verificarExibicaoEndereco();">

<html:form action="/manterTarifaSocialWizardAction"
	onsubmit="return validateManterTarifaSocialActionForm(this);"
	name="ManterTarifaSocialActionForm"
	type="gcom.gui.cadastro.tarifasocial.ManterTarifaSocialActionForm"
	method="post">


	<jsp:include
		page="/jsp/util/wizard/navegacao_abas_wizard_valida_avancar.jsp?numeroPagina=1" />


	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>

	<table width="770" border="0" cellspacing="5" cellpadding="0">
		<tr>
			<td width="130" valign="top" class="leftcoltext">
			<div align="center">
			<p align="left">&nbsp;</p>
			<p align="left">&nbsp;</p>
			<p align="left">&nbsp;</p>

			<%@ include file="/jsp/util/informacoes_usuario.jsp"%>

			<p align="left">&nbsp;</p>
			<p align="left">&nbsp;</p>
			<p align="left">&nbsp;</p>
			<p align="left">&nbsp;</p>
			<p align="left">&nbsp;</p>
			<p align="left">&nbsp;</p>
			<p align="left">&nbsp;</p>
			<p align="left">&nbsp;</p>
			<p align="left">&nbsp;</p>
			<p align="left">&nbsp;</p>
			<p align="left">&nbsp;</p>
			<p align="left">&nbsp;</p>

			<%@ include file="/jsp/util/mensagens.jsp"%>

			<p align="left">&nbsp;</p>
			<p align="left">&nbsp;</p>
			<p align="left">&nbsp;</p>
			<p align="left">&nbsp;</p>
			<p align="left">&nbsp;</p>
			<p align="left">&nbsp;</p>
			<p align="left">&nbsp;</p>
			</div>
			</td>
			<td width="625" valign="top" class="centercoltext">

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
					<td class="parabg">Manter Dados Tarifa Social</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<table width="100%" border="0" dwcopytype="CopyTableRow">
				<tr>

					<td colspan="2">Para manter um imóvel na tarifa social, informe os
					dados abaixo:</td>
				</tr>
				<tr>
					<td width="80"><strong>Registro de Atendimento:<font
						color="#FF0000">*</font><font color="#FF0000"></font></strong></td>
					<td colspan="3" align="right">
					<div align="left"><html:text property="registroAtendimento"
						maxlength="9" size="9" tabindex="1"
						onkeyup="javascript:limparRegistroAtendimentoTecla();" onblur="bloquearCampos();"
						onkeypress="validaEnterComMensagem(event, 'manterTarifaSocialWizardAction.do?action=exibirManterTarifaSocialImovelAction', 
							'registroAtendimento','Registro de Atendimento');" />
					<a href="javascript:pesquisarRegistroAtendimento();"> <img
						width="23" height="21"
						src="<bean:message key='caminho.imagens'/>pesquisa.gif" border="0" /></a>
					<logic:present name="corRegistroAtendimento">
						<logic:equal name="corRegistroAtendimento" value="exception">
							<html:text property="nomeRegistroAtendimento" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000"
								size="40" maxlength="40" />
						</logic:equal>
						<logic:notEqual name="corRegistroAtendimento" value="exception">
							<html:text property="nomeRegistroAtendimento" size="40"
								maxlength="40" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEqual>
					</logic:present> <logic:notPresent name="corRegistroAtendimento">
						<logic:empty name="ManterTarifaSocialActionForm"
							property="registroAtendimento">
							<html:text property="nomeRegistroAtendimento" size="40"
								maxlength="40" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:empty>
						<logic:notEmpty name="ManterTarifaSocialActionForm"
							property="registroAtendimento">
							<html:text property="nomeRegistroAtendimento" size="40"
								maxlength="40" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEmpty>
					</logic:notPresent> <a
						href="javascript:limparRegistroAtendimento();"> <img
						src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar Registro de Atendimento" /> </a></div>
					</td>
				</tr>


				<tr>
					<td width="80"><strong> Matricula: </strong></td>
					<td><html:text property="idImovel" size="9" maxlength="9" onkeyup="javascript:limparImovelTecla();" onblur="bloquearCampos();"
							onkeypress="validaEnterComMensagem(event, 'manterTarifaSocialWizardAction.do?action=exibirManterTarifaSocialImovelAction', 
							'idImovel', 'Imóvel');"/> <a
						href="javascript:pesquisarImovel();"> <img width="23" height="21"
						src="<bean:message key='caminho.imagens'/>pesquisa.gif" border="0" /></a>
					<logic:present name="imovelNaoEncontrado">
						<html:text property="inscricaoImovel" size="40" maxlength="40"
							readonly="true" 
							style="background-color:#EFEFEF; border:0; color: #ff0000" />
					</logic:present> <logic:notPresent name="imovelNaoEncontrado">
						<html:text property="inscricaoImovel" size="40" maxlength="40"
							readonly="true"
							style="background-color:#EFEFEF; border:0; color: #000000" />
					</logic:notPresent> <a
						href="javascript:limparImovel();"> <img
						src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar Imóvel" /> </a></td>
				</tr>
				<tr>

					<td>&nbsp;</td>
					<td><strong> <font color="#FF0000"> * </font> </strong> Campo
					obrigat&oacute;rio</td>
				</tr>


				<tr>
					<td colspan="2">
					<table width="100%" bgcolor="#99CCFF" border="0">
						<tr bgcolor="#99CCFF">
							<td>
							<div align="center"><strong>Endere&ccedil;o</strong></div>
							</td>
						</tr>

						<logic:present name="clienteImovel" scope="session">

							<logic:present name="matriculaInvalida" scope="request">

								<tr>
									<td bgcolor="#FFFFFF">
									<div align="center"><bean:write name="matriculaInvalida"
										scope="request" /></div>
									</td>
								</tr>

							</logic:present>

							<logic:notPresent name="matriculaInvalida" scope="request">
								<tr id="endereco">
									<td bgcolor="#FFFFFF"><logic:present name="clienteImovel"
										scope="session">
										<div align="center"><bean:write name="clienteImovel"
											property="imovel.enderecoFormatado" scope="session" /></div>
									</logic:present></td>
								</tr>
							</logic:notPresent>

						</logic:present>

						<logic:notPresent name="clienteImovel" scope="session">

							<logic:present name="matriculaInvalida" scope="request">

								<tr>
									<td bgcolor="#cbe5fe">
									<div align="center"><bean:write name="matriculaInvalida"
										scope="request" /></div>
									</td>
								</tr>
							</logic:present>

						</logic:notPresent>

					</table>
					</td>
				</tr>

				<tr>
					<td width="80"><strong>Economias:</strong>
					<td id="economias"><logic:notPresent name="matriculaInvalida"
						scope="request">
						<logic:present name="quantEconomias" scope="session">
							<bean:write name="quantEconomias" scope="session" />
						</logic:present>
						<logic:notPresent name="quantEconomias" scope="session">
						&nbsp;
					</logic:notPresent>
					</logic:notPresent> <logic:present name="matriculaInvalida"
						scope="request">
						&nbsp;
				</logic:present></td>
				</tr>

				<tr>
					<td colspan="2">
					<div align="right"><jsp:include
						page="/jsp/util/wizard/navegacao_botoes_wizard_valida_avancar.jsp?numeroPagina=1" />
					</div>
					</td>
				</tr>

			</table>

			<p>&nbsp;</p>
			</td>
		</tr>
	</table>


	<%@ include file="/jsp/util/rodape.jsp"%>
	
</html:form>

</body>

</html:html>
