<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<%@page
	import="gcom.atendimentopublico.registroatendimento.RegistroAtendimento"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">

<!--

function fechar(){
		window.close();
-->

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false" 
	formName="ConsultarRegistroAtendimentoPendentesImovelActionForm" />



<script language="JavaScript">
	function redirecionaSubmit(caminhoAction) {
   	var form = document.forms[0];
   	form.action = caminhoAction;
   	form.submit();
   	return true;
 }
	function validarForm(form){
	urlRedirect = "/gsan/consultarRegistroAtendimentoPendentesImovelAction.do"
	if((document.ConsultarRegistroAtendimentoPendentesImovelActionForm.solicitacaoTipo, 'Especificação') && 
	testarCampoValorZero(document.ConsultarRegistroAtendimentoPendentesImovelActionForm.numeroRa, 'Numero do RA')&& 
	testarCampoValorZero(document.ConsultarRegistroAtendimentoPendentesImovelActionForm.matriculaImovel, 'Matrícula do imóvel')&&
	testarCampoValorZero(document.ConsultarRegistroAtendimentoPendentesImovelActionForm.inscricaoImovel, 'Inscrição do Imóvel')&&
	testarCampoValorZero(document.ConsultarRegistroAtendimentoPendentesImovelActionForm.clienteUsuario, 'Usuário')&&
	testarCampoValorZero(document.ConsultarRegistroAtendimentoPendentesImovelActionForm.solicitacaoTipoEspecificacao, 'Tipo de Solicitação')&&
	testarCampoValorZero(document.ConsultarRegistroAtendimentoPendentesImovelActionForm.situacaoLigacaoAgua, 'Situação da Ligacao Agua')&&	
	testarCampoValorZero(document.ConsultarRegistroAtendimentoPendentesImovelActionForm.dataRegistroAtendimento, 'Data do Atendimento')&&	
	testarCampoValorZero(document.ConsultarRegistroAtendimentoPendentesImovelActionForm.situacaoLigacaoEsgoto, 'Situação da Ligacao Esgoto')) {

		if(validateConsultarRegistroAtendimentoPendentesImovelActionForm(form)){
    		redirecionaSubmit(urlRedirect);
		}
		
	}
	
}

	function limparForm() {
		var form = document.ConsultarRegistroAtendimentoPendentesImovelActionForm;
		form.idOrdemServico.value = "";
		form.solicitacaoTipo.value = "";
		form.matriculaImovel.value = "";
		form.inscricaoImovel.value = "";
		form.clienteUsuario.value = "";
		form.solicitacaoTipoEspecificacao.value = "";
		form.situacaoLigacaoAgua.value = "";
		form.situacaoLigacaoEsgoto.value = "";
		form.dataRegistroAtendimento.value = "";
		form.numeroRa.value = "";
	 }
	 
	function limparOrdemServico() {
		var form = document.ConsultarRegistroAtendimentoPendentesImovelActionForm;
		form.idOrdemServico.value = "";
		form.solicitacaoTipo.value = "";
		form.matriculaImovel.value = "";
		form.inscricaoImovel.value = "";
		form.clienteUsuario.value = "";
		form.solicitacaoTipoEspecificacao.value = "";
		form.situacaoLigacaoAgua.value = "";
		form.situacaoLigacaoEsgoto.value = "";
		form.dataRegistroAtendimento.value = "";
		form.numeroRa.value = "";
	}
	
	function limparOrdemServicoTecla() {
		var form = document.ConsultarRegistroAtendimentoPendentesImovelActionForm;
		form.solicitacaoTipo.value = "";
		form.matriculaImovel.value = "";
		form.inscricaoImovel.value = "";
		form.clienteUsuario.value = "";
		form.solicitacaoTipoEspecificacao.value = "";
		form.situacaoLigacaoAgua.value = "";
		form.situacaoLigacaoEsgoto.value = "";
		form.dataRegistroAtendimento.value = "";
		form.numeroRa.value = "";
	}
	
</script>


</head>
<body leftmargin="5" topmargin="5" onload="resizePageSemLink(690, 400);">


<logic:present name="identificadorPesquisa" scope="request">
	<body leftmargin="5" topmargin="5">
</logic:present>

<logic:notPresent name="identificadorPesquisa" scope="request">
	<body leftmargin="5" topmargin="5"
		onload="javascript:setarFoco('${requestScope.nomeCampo}');">
</logic:notPresent>

<html:form
	action="/exibirConsultarRegistroAtendimentoPendentesImovelAction.do"
	name="ConsultarRegistroAtendimentoPendentesImovelActionForm"
	type="gcom.gui.atendimentopublico.registroatendimento.ConsultarRegistroAtendimentoPendentesImovelActionForm"
	method="post"
	onsubmit="return validateConsultarRegistroAtendimentoPendentesImovelActionForm(this);">

	<table width="635" border="0" cellspacing="5" cellpadding="0">
		<tr>
			<td width="635" valign="top" class="centercoltext">
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
					<td class="parabg">Registro Atendimento Pendentes para o Imóvel</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
						
				</tr>
			</table>
			<table width="100%">
					<tr>
						<td width="90%" colspan="2">&nbsp;</td>
						<logic:present scope="application" name="urlHelp">
						<td align="right"><a href="javascript:abrirPopupHelp('${applicationScope.urlHelp}atendimentoRegistroImovelConsultar', 500, 700);"><span style="font-weight: bold"><font color="#3165CE">Ajuda</font></span></a></td>									
						</logic:present>
						<logic:notPresent scope="application" name="urlHelp">
						<td align="right"><span style="font-weight: bold"><font color=#696969><u>Ajuda</u></font></span></td>									
						</logic:notPresent>
					</tr>
			</table>
			<table width="100%" border="0">
				<tr>
					<td height="31">
					<table width="100%" border="0" align="center">
						<tr>
							<td height="10" colspan="2"></td>
						</tr>
						<tr bgcolor="#cbe5fe">
							<td align="center" colspan="2">
							<table width="100%" border="0" bgcolor="#99CCFF">
								<tr bgcolor="#99CCFF">
									<td height="18" colspan="2">
									<div align="center"><strong>Dados do Imóvel</strong></div>
									</td>
								</tr>
								<tr bgcolor="#cbe5fe">
									<td>
									<table border="0" width="100%">
										<tr>
											<td width="37%" height="10"><strong>Matrícula do Imóvel:</strong></td>
											<td width="58%"><html:text property="matriculaImovel"
												readonly="true"
												style="background-color:#EFEFEF; border:0; color: #000000"
												size="15" maxlength="20" /></td>
										</tr>
										<tr>
											<td><strong>Inscrição do Imóvel:</strong></td>
											<td><html:text property="inscricaoImovel" readonly="true"
												style="background-color:#EFEFEF; border:0; color: #000000"
												size="40" maxlength="40" /></td>
										</tr>


										<tr>
											<td><strong>Situação da Ligação de Água:</strong></td>
											<td><html:text property="situacaoLigacaoAgua" readonly="true"
												style="background-color:#EFEFEF; border:0; color: #000000"
												size="40" maxlength="40" /></td>
										</tr>
										<tr>
											<td><strong>Situação da Ligação de Esgoto:</strong></td>

											<td><html:text property="situacaoLigacaoEsgoto"
												readonly="true"
												style="background-color:#EFEFEF; border:0; color: #000000"
												size="40" maxlength="40" /></td>
										</tr>
										<tr>
											<td colspan="2">
											
											<table width="100%" cellpadding="0" cellspacing="0">
												<tr>
													<td>
														<table width="100%" border="0" bgcolor="#90c7fc">
														<tr height="18">
															<td align="center"><strong>Endereço do Imóvel</strong></td>
														</tr>
														</table>
													</td>
												</tr>
												<tr>
													<td>
														<table width="100%" align="center" bgcolor="#99CCFF">
														<tr bgcolor="#FFFFFF" height="18">
															<td width="10%" align="center">
																<bean:write name="enderecoImovel"/>
															</td>
														</tr>
														</table>
												  	</td>
												</tr>
												</table>
											</td>
										</tr>
									</table>
									</td>
								</tr>
							</table>
							</td>

						</tr>
					</table>
					</td>
				</tr>
				<tr>
				</tr>
				<tr>
				</tr>
				<tr>
				</tr>
				<tr>
				</tr>

				<tr bgcolor="#cbe5fe">
					<td align="center" colspan="2">
					<table width="100%" border="0" bgcolor="#99CCFF">
						<tr bgcolor="#99CCFF">
							<td height="18" colspan="4" align="center"><strong>Dados Gerais
							do Registros de Atendimento</strong></td>
						</tr>
						<tr bgcolor="#cbe5fe">
						<tr>
						<tr bordercolor="#000000">
							<td width="16%" bgcolor="#90c7fc">
							<div align="center"><strong>Número do RA</strong></div>
							</td>

							<td width="20%" bgcolor="#90c7fc">
							<div align="center"><strong>Tipo da Solicitação</strong></div>
							</td>
							<td width="30%" bgcolor="#90c7fc">
							<div align="center"><strong>Especificação</strong></div>
							</td>
							<td width="20%" bgcolor="#90c7fc">
							<div align="center"><strong>Data de Atendimento</strong></div>
							</td>
							<td width="14%" bgcolor="#90c7fc">
							<div align="center"><strong>Situação</strong></div>
							</td>

						</tr>
						<logic:present
							name="colecaoConsultarImovelRegistroAtendimentoHelper">
							<%int cont = 0;%>
							<logic:iterate
								name="colecaoConsultarImovelRegistroAtendimentoHelper"
								id="consultarImovelRegistroAtendimentoHelper">
								<%cont = cont + 1;
			if (cont % 2 == 0) {%>
								<tr bgcolor="#FFFFFF">
									<%} else {

			%>
								<tr bgcolor="#cbe5fe">
									<%}%>
									<td width="16%" align="center"><a
										href="javascript:abrirPopup('exibirConsultarRegistroAtendimentoPopupAction.do?numeroRA='+${consultarImovelRegistroAtendimentoHelper.idRegistroAtendimento}, 400, 800);">
									${consultarImovelRegistroAtendimentoHelper.idRegistroAtendimento}</a></td>
									<td width="30%" align="center">${consultarImovelRegistroAtendimentoHelper.tipoSolicitacao}</td>
									<td width="33%" align="left">${consultarImovelRegistroAtendimentoHelper.especificacao}</td>
									<td width="21%" align="center">${consultarImovelRegistroAtendimentoHelper.dataAtendimento}</td>
									<td width="21%" align="center">${consultarImovelRegistroAtendimentoHelper.situacao}</td>
								</tr>
							</logic:iterate>
						</logic:present>
					</table>
				</tr>
				<tr>
					<td align="right"><input name="button" type="button" class="bottonRightCol"
						value="Fechar"
						onclick="window.close();"
						align="right"></td>

				</tr>
			</table>
			</td>
		</tr>
	</table>

	<!-- Fim do Corpo - Thiago Tenório-->
</html:form>
</body>
</html:html>
