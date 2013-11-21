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
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="AtualizarMensagemContaActionForm" />

<script language="JavaScript">
 
 function validaForm(){
	var form = document.AtualizarMensagemContaActionForm;
	
	if (form.mensagemConta01.value == "") {
		alert('Informe Mensagem da Conta');
		form.mensagemConta.focus();
	} else {
		submeterFormPadrao(form);
	}
 }


  function habilitaMensagem2(){
	
	var form = document.AtualizarMensagemContaActionForm; 	
  	if (form.mensagemConta01.value != ""){
 	  form.mensagemConta02.disabled = false;
 	} else {
 	  form.mensagemConta02.value = "";
 	  form.mensagemConta02.disabled = true;
 	  form.mensagemConta03.value = "";
 	  form.mensagemConta03.disabled = true;  	
 	}
 }
 
  function habilitaMensagem3(){
	var form = document.AtualizarMensagemContaActionForm; 	
 	if (form.mensagemConta02.value != ""){
 	  form.mensagemConta03.disabled = false;
 	} else {
 	  form.mensagemConta03.value = "";
 	  form.mensagemConta03.disabled = true; 	
 	}
 }
 
 function verificaCamposHabilitadors(){
	var form = document.AtualizarMensagemContaActionForm; 
	
	if (form.mensagemConta01.value != ""){
	  form.mensagemConta02.disabled = false;
	} else {
	  form.mensagemConta02.disabled = true;
	}
	if (form.mensagemConta02.value != ""){
	  form.mensagemConta03.disabled = false;
	}else{
	  form.mensagemConta03.disabled = true;
	}
 }
 
</script>
</head>
<logic:present name="identificadorPesquisa" scope="request">
	<body leftmargin="5" topmargin="5">
</logic:present>
<logic:notPresent name="identificadorPesquisa" scope="request">
	<body leftmargin="5" topmargin="5"
		onload="verificaCamposHabilitadors(); javascript:setarFoco('mensagemConta01');">
</logic:notPresent>
<html:form action="/atualizarMensagemContaAction.do"
	name="AtualizarMensagemContaActionForm"
	type="gcom.gui.faturamento.conta.AtualizarMensagemContaActionForm"
	method="post" onsubmit="return validateAtualizarMensagemContaActionForm(this);">

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
			<td width="615" valign="top" class="centercoltext">
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
					<td class="parabg">Manter Mensagem da Conta</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<table border="0" width="100%">
				<tr>
					<td colspan="2">Para atualizar a mensagem da conta, informe os
					dados abaixo:</td>
					<logic:present scope="application" name="urlHelp">
						<td align="right"><a href="javascript:abrirPopupHelp('${applicationScope.urlHelp}faturamentoContaMensagemAtualizar', 500, 700);"><span style="font-weight: bold"><font color="#3165CE">Ajuda</font></span></a></td>									
					</logic:present>
					<logic:notPresent scope="application" name="urlHelp">
						<td align="right"><span style="font-weight: bold"><font color=#696969><u>Ajuda</u></font></span></td>									
					</logic:notPresent>
				</tr>
			</table>				
				<table border="0" width="100%">
				<tr>
					<td><strong>Referência do Faturamento:</strong></td>
						<td align="right">
					<div align="left"><strong> <html:text property="referenciaFaturamento"
						size="25" maxlength="25" readonly="true"
						style="background-color:#EFEFEF; border:0; color: #000000;" />
					</td>
				</tr>
				<tr>
					<td><strong>Grupo de Faturamento:</strong></td>
					<td align="right">
					<div align="left"><strong> <html:text property="grupoFaturamento"
						size="25" maxlength="25" readonly="true"
						style="background-color:#EFEFEF; border:0; color: #000000;" />
					</td>
				</tr>
				<tr>
					<td><strong>Gerência Regional:</strong></td>
					<td align="right">
					<div align="left"><strong> <html:text property="gerenciaRegional"
						size="25" maxlength="25" readonly="true"
						style="background-color:#EFEFEF; border:0; color: #000000;" />
					</td>

				</tr>
				<tr>
					<td><strong>Localidade:</strong></td>
					<td align="right">
					<div align="left"><html:text property="localidade" size="4"
						maxlength="4" readonly="true"
						style="background-color:#EFEFEF; border:0; color: #000000;" /> <html:text
						property="localidadeDescricao" size="30" maxlength="30"
						readonly="true"
						style="background-color:#EFEFEF; border:0; color: #000000;" />
				</tr>
				<tr>
					<td><strong>Setor Comercial:</strong></td>

					<td align="right">
					<div align="left"><html:text property="setorComercial" size="4"
						maxlength="4" readonly="true"
						style="background-color:#EFEFEF; border:0; color: #000000;" /> <html:text
						property="setorComercialDescricao" size="30" maxlength="30"
						readonly="true"
						style="background-color:#EFEFEF; border:0; color: #000000;" />
				</tr>
				<tr>
					<td><strong>Quadra:</strong></td>
					<td align="right">
					<div align="left"><strong> <html:text property="quadra"
						size="9" maxlength="9" readonly="true"
						style="background-color:#EFEFEF; border:0; color: #000000;" />
					</td>
				</tr>
				
				<tr>
					<td colspan="2"><hr></td>
					<td align="right">&nbsp;</td>
				</tr>

								<tr>
					<td><strong>Mensagem da Conta:<font color="#ff0000">*</font></strong></td>

					<td align="right">
					<div align="left"><html:text property="mensagemConta01"
						maxlength="100" size="45" onkeyup="habilitaMensagem2();" /></div>
					</td>
				</tr>
				<tr>
					<td><strong></strong></td>

					<td align="right">
					<div align="left"><html:text property="mensagemConta02"
						maxlength="100" size="45" onkeyup="habilitaMensagem3();"/></div>
					</td>
				</tr>
				<tr>
					<td><strong></strong></td>

					<td align="right">
					<div align="left"><html:text property="mensagemConta03"
						maxlength="100" size="45"/></div>
					</td>
				</tr>
				<tr>
					<td colspan="2"><hr></td>
					<td align="right">&nbsp;</td>
				</tr>

				<tr>
					<td><strong> <font color="#000000"></font></strong></td>
					<td align="right">
					<div align="left"><strong><font color="#FF0000">*</font></strong>
					Campos obrigatórios</div>
					</td>
				</tr>
				<tr> 
					<td><strong> <font color="#ff0000">
					<input name="Submit222"
						class="bottonRightCol" value="Voltar" type="button"
						onclick="javascript:history.back();">
					<input name="Submit22"
						class="bottonRightCol" value="Desfazer" type="button"
						onclick="window.location.href='/gsan/exibirAtualizarMensagemContaAction.do';">

					<input name="Submit23" class="bottonRightCol" value="Cancelar"
						type="button"
						onclick="window.location.href='/gsan/telaPrincipal.do'"> </font></strong></td>
					<td align="right"><input name="botao" class="bottonRightCol"
						value="Atualizar" onclick="validaForm();" type="button"></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<!-- Fim do Corpo - Roberta Costa --></td>
		</tr>
	</table>

	<%@ include file="/jsp/util/rodape.jsp"%>
</html:form>

<script language="JavaScript">
<!-- Begin
	verificaPreenchimentoLocalidade();
-->
</script>


</html:html>
