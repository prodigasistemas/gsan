<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan"%>

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
	src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
    <html:javascript staticJavascript="false"  formName="ConjuntoTramitacaoRaActionForm"/>		
<script language="JavaScript">

	function validarForm(form){
		if(validateConjuntoTramitacaoRaActionForm(form)){
			submeterFormPadrao(form);
		}
	}	
	
	function limparUnidadeDestino(){
		var form = document.forms[0];
		
      	form.idUnidadeDestino.value = '';
	    form.descricaoUnidadeDestino.value = '';		
	}
	
	function limparUnidadeResponsavel(){
		var form = document.forms[0];
		
      	form.idUsuarioResponsavel.value = '';
	    form.descricaoUsuarioResponsavel.value = '';		
	}
	
	function limparForm(){
	  var form = document.forms[0];
	
	  limparUnidadeDestino(); 
	  limparUnidadeResponsavel();
	  form.dataTramitacao.value = "";
	  form.horaTramitacao.value = "";
	  form.parecer.value = "";
	}
		
  	function textAreaMaxLength(maxlength){
		var form = document.forms[0];
		if(form.parecerTramite.value.length >= maxlength){
			window.event.keyCode = '';
		}
	}
		
</script>

</head>

<logic:notPresent name="fecharPopup">
	<body leftmargin="5" topmargin="5"
			onload="javascript:setarFoco('${requestScope.nomeCampo}');resizePageSemLink(650, 500);">
</logic:notPresent>
<logic:present name="fecharPopup">
	<body leftmargin="0" topmargin="0"
		onload="chamarReload('filtrarRegistroAtendimentoTramitacaoAction.do?retornaDoPopup=1');window.close()">
</logic:present>

<html:form action="/registroAtendimentoTramitacaoPopupAction.do"
	name="ConjuntoTramitacaoRaActionForm"
	type="gcom.gui.atendimentopublico.registroatendimento.ConjuntoTramitacaoRaActionForm"
	method="post"
	onsubmit="return validateConjuntoTramitacaoRaActionForm(this);">

	<table width="600" border="0" cellpadding="0" cellspacing="5">
		<tr>
			<td valign="top" class="centercoltext">
			<table height="100%">
				<tr>
					<td></td>
				</tr>
			</table>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img
						src="<bean:message key="caminho.imagens"/>parahead_left.gif"
						border="0" /></td>
					<td class="parabg">Tramite Conjunto de Registro de Atendimento</td>
					<td width="11" valign="top"><img
						src="<bean:message key="caminho.imagens"/>parahead_right.gif"
						border="0" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>
					<td>Para tramitar o conjunto de registro(s) de atendimento(s), informe os dados abaixo:</td>
				</tr>
				<tr bordercolor="#90c7fc" bgcolor="#90c7fc">
					<td align="center"><strong>Dados da Tramitação</strong></td>
				</tr>				
			</table>
			<table width="100%" border="0">
				<tr>		
					<td><strong>Unidade Destino:<font color="#FF0000">*</font></strong></td>
					<td width="66%"><html:text property="idUnidadeDestino"
						tabindex="11" size="4" maxlength="4"
						onkeypress="validaEnterComMensagem(event, 'exibirRegistroAtendimentoTramitacaoPopupAction.do?consultaUnidadeDestino=1', 'idUnidadeDestino', 'Unidade Destino');" />
					<a
						href="javascript:redirecionarSubmit('exibirPesquisarUnidadeOrganizacionalAction.do?caminhoRetornoTelaPesquisaUnidadeOrganizacional=exibirRegistroAtendimentoTramitacaoPopupAction', 410, 650);">
					<img src="<bean:message key="caminho.imagens"/>pesquisa.gif"
						border="0" width="23" height="21" title="Pesquisar"></a> 
					<logic:present name="corUnidadeDestino">
						<logic:equal name="corUnidadeDestino" value="exception">
							<html:text property="descricaoUnidadeDestino" size="45"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:equal>
						<logic:notEqual name="corUnidadeDestino" value="exception">
							<html:text property="descricaoUnidadeDestino" size="45"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEqual>
					</logic:present> 
					<logic:notPresent name="corUnidadeDestino">
						<logic:empty name="ConjuntoTramitacaoRaActionForm"
							property="idUnidadeDestino">
							<html:text property="descricaoUnidadeDestino" size="45" value=""
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:empty>
						<logic:notEmpty name="ConjuntoTramitacaoRaActionForm"
							property="idUnidadeDestino">
							<html:text property="descricaoUnidadeDestino" size="45"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEmpty>
					</logic:notPresent> <a href="javascript:limparUnidadeDestino();">
					<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar" /></a></td>
				</tr>
				<tr>
					<td><strong>Usuário Responsável:<font color="#FF0000">*</font></strong></td>
					<td width="66%"><html:text property="idUsuarioResponsavel"
						tabindex="11" size="9" maxlength="9"
						onkeypress="validaEnterComMensagem(event, 'exibirRegistroAtendimentoTramitacaoPopupAction.do?consultaUsuarioResponsavel=1', 'idUsuarioResponsavel', 'Usuário Responsável');" />
					<a
						href="javascript:redirecionarSubmit('exibirUsuarioPesquisar.do?caminhoRetornoTelaPesquisaUsuario=exibirRegistroAtendimentoTramitacaoPopupAction', 410, 650);">
					<img src="<bean:message key="caminho.imagens"/>pesquisa.gif"
						border="0" width="23" height="21" title="Pesquisar"></a> <logic:present
						name="corUsuario">
						<logic:equal name="corUsuario" value="exception">
							<html:text property="descricaoUsuarioResponsavel" size="45"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:equal>
						<logic:notEqual name="corUsuario" value="exception">
							<html:text property="descricaoUsuarioResponsavel" size="45"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEqual>
					</logic:present> <logic:notPresent name="corUsuario">
						<logic:empty name="ConjuntoTramitacaoRaActionForm"
							property="idUnidadeDestino">
							<html:text property="descricaoUsuarioResponsavel" size="45" value=""
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:empty>
						<logic:notEmpty name="ConjuntoTramitacaoRaActionForm"
							property="idUnidadeDestino">
							<html:text property="descricaoUsuarioResponsavel" size="45"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEmpty>
					</logic:notPresent> <a href="javascript:limparUnidadeResponsavel();">
					<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar" /></a></td>
				</tr>	
				<tr>
				  <td class="style3"><strong>Data da Tramitação:<font color="#FF0000">*</font></strong></td>
				  <td colspan="3"><html:text property="dataTramitacao"
					size="10" maxlength="10" onkeyup="mascaraData(this, event)"/> </td>
				</tr>
				<tr>
				  <td height="10"><strong>Hora da Tramitação:<font color="#FF0000">*</font></strong></td>
				  <td><html:text property="horaTramitacao" size="10"
						maxlength="5" tabindex="5" onkeyup="mascaraHora(this, event)" /><strong>&nbsp;(hh:mm)</strong>
				  </td>
				</tr>				
		        <tr>
					<td class="style3"><strong>Parecer:</strong></td>
					<td colspan="3"><html:textarea
						property="parecer" cols="50" rows="4"
						onkeypress="javascript:textAreaMaxLength(200);" /></td>
				 </tr>							
				<tr>
					<td>&nbsp;</td>
					<td colspan="6" align="right">&nbsp;</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td colspan="6">
					<div align="right"></div>
					</td>
				</tr>
				<tr>
					<td><input name="Button" type="button" class="bottonRightCol" value="Voltar" align="left" onclick="window.close();" >
                    	<input type="button" class="bottonRightCol" value="Desfazer"onClick="javascript:limparForm()">
                    </td>
					<td width="66%" align="right"><gsan:controleAcessoBotao
						name="Button" value="Inserir"
						onclick="javascript:validarForm(document.ConjuntoTramitacaoRaActionForm);" 
						url="filtrarRegistroAtendimentoTramitacaoAction.do" />
					</td>
				</tr>
			</table>
			<p>&nbsp;</p>
			</td>
		</tr>
	</table>
</html:form>
</body>
</html:html>
