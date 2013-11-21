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
<%--<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="ParcelamentoDebitoActionForm"/>--%>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript">
<!-- Begin

function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {

   	var form = document.forms[0];

    if (tipoConsulta == 'imovel') {
      form.idImovelParcelamentosDebitos.value = codigoRegistro;
      form.matriculaImovelParcelamentosDebitos.value = descricaoRegistro;
      form.matriculaImovelParcelamentosDebitos.style.color = "#000000";
	  form.action = 'consultarImovelWizardAction.do?action=exibirConsultarImovelParcelamentosDebitosAction&indicadorNovo=OK'
	  form.submit();
    }
    
}

function limparForm(){
   	var form = document.forms[0];
	form.action = 'consultarImovelWizardAction.do?action=exibirConsultarImovelParcelamentosDebitosAction&limparForm=OK'
	form.submit();
}


function habilitaMatricula() {
	var form = document.forms[0];
	
	if (form.idImovelParcelamentosDebitos.value != null && form.matriculaImovelParcelamentosDebitos.value != null && 
		form.matriculaImovelParcelamentosDebitos.value != "" && form.matriculaImovelParcelamentosDebitos.value != "IMÓVEL INEXISTENTE"){
	
		form.idImovelParcelamentosDebitos.disabled = true;
	} else {
		form.idImovelParcelamentosDebitos.disabled = false;
	}
}

function pesquisarImovel() {
	var form = document.forms[0];
 	
 	if (form.idImovelParcelamentosDebitos.disabled ) {
 		alert("Para realizar uma pesquisa de imóvel é necessário apagar o imóvel atual.")
	} else {
		abrirPopup('exibirPesquisarImovelAction.do', 400, 800)
	}
}


 
//-->
</script>
</head>
<body leftmargin="5" topmargin="5" onload="javascript:habilitaMatricula();setarFoco('idImovelParcelamentosDebitos')">
<html:form action="/exibirConsultarImovelAction.do"
	name="ConsultarImovelActionForm"
	type="gcom.gui.cadastro.imovel.ConsultarImovelActionForm" method="post"
	onsubmit="return validateConsultarImovelActionForm(this);">

	<jsp:include
		page="/jsp/util/wizard/navegacao_abas_wizard_consulta.jsp?numeroPagina=9" />


<%@ include file="/jsp/util/cabecalho.jsp"%>
<%@ include file="/jsp/util/menu.jsp"%>


<table width="770" border="0" cellspacing="5" cellpadding="0">
	<tr>
		<td width="150" valign="top" class="leftcoltext">
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
		<p>&nbsp;</p>
		<table width="100%" border="0" align="center" cellpadding="0"
			cellspacing="0">
			<tr>
				<td width="11"><img border="0"
					src="<bean:message key="caminho.imagens"/>parahead_left.gif" /></td>
				<td class="parabg">&nbsp;</td>
				<td width="11"><img border="0"
					src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
			</tr>
		</table>
		<p>&nbsp;</p>

		<!-- Início do Corpo - Fernanda Paiva  07/02/2006  -->
		<table width="100%" border="0">
				<tr>
					<td colspan="4">
					<table width="100%" align="center" bgcolor="#99CCFF" border="0">
						<tr>
						    <td>
							    <table width="100%" align="center" bgcolor="#99CCFF" border="0">
								    <tr>
									    <td align="left" width="4%">
											<img border="0" width="25" height="25"
												src="<bean:message key="caminho.imagens"/>informacao.gif"
												onmouseover="this.T_BGCOLOR='whitesmoke';this.T_LEFT=true;return escape( '${ConsultarImovelActionForm.hint2}' ); "/>
									    </td>						    						
										<td align="center" width="96%"><strong>Dados do Imóvel<logic:present name="imovelExcluido" scope="request"><font color="#ff0000"> (Excluído)</font></logic:present></strong></td>
									</tr>
								</table>
							</td>
						</tr>
						<tr bgcolor="#cbe5fe">
							<td width="100%" align="center">
							<table width="100%" border="0">
								<tr>
									<td bordercolor="#000000" width="25%"><strong>Im&oacute;vel:<font
										color="#FF0000">*</font></strong></td>
									<td width="75%" colspan="3"><html:text
										property="idImovelParcelamentosDebitos" maxlength="9" size="9"
										onkeypress="validaEnterComMensagem(event, 'consultarImovelWizardAction.do?action=exibirConsultarImovelParcelamentosDebitosAction&indicadorNovo=OK','idImovelParcelamentosDebitos','Im&oacute;vel');return isCampoNumerico(event);"
										/> 
									<a
										href="javascript:pesquisarImovel();">
									<img width="23" height="21"
										src="<bean:message key="caminho.imagens"/>pesquisa.gif"
										border="0" title="Pesquisar Imóvel"/></a> <logic:present
										name="idImovelParcelamentosDebitosNaoEncontrado" scope="request">
										<html:text property="matriculaImovelParcelamentosDebitos" size="40"
											readonly="true"
											style="background-color:#EFEFEF; border:0; color: #ff0000" 
											title="Localidade.Setor.Quadra.Lote.Sublote"/>

									</logic:present> <logic:notPresent
										name="idImovelParcelamentosDebitosNaoEncontrado" scope="request">
										<logic:present name="valorMatriculaImovelParcelamentosDebitos"
											scope="request">
											<html:text property="matriculaImovelParcelamentosDebitos"
												size="40" readonly="true"
												style="background-color:#EFEFEF; border:0; color: #000000" 
												title="Localidade.Setor.Quadra.Lote.Sublote"/>
										</logic:present>
										<logic:notPresent name="valorMatriculaImovelParcelamentosDebitos"
											scope="request">
											<html:text property="matriculaImovelParcelamentosDebitos"
												size="40" readonly="true"
												style="background-color:#EFEFEF; border:0; color: #000000" 
												title="Localidade.Setor.Quadra.Lote.Sublote"/>
										</logic:notPresent>
									</logic:notPresent> <a href="javascript:limparForm();"> <img
										src="<bean:message key="caminho.imagens"/>limparcampo.gif"
										border="0" title="Apagar" /></a></td>

								</tr>
								<tr>
									<td height="10">
									<div class="style9"><strong>Situação de Água:</strong></div>
									</td>
									<td><html:text property="situacaoAguaParcelamentosDebitos"
										readonly="true"
										style="background-color:#EFEFEF; border:0; color: #000000"
										size="15" maxlength="15" /></td>
									<td width="90"><strong>Situação de Esgoto:</strong></td>
									<td width="120"><html:text
										property="situacaoEsgotoParcelamentosDebitos" readonly="true"
										style="background-color:#EFEFEF; border:0; color: #000000"
										size="15" maxlength="15" /></td>
								</tr>
							</table>
							</td>
						</tr>
					</table>
					</td>
				</tr>
			<tr>
				<td colspan="3" height="10"></td>
			</tr>
			<tr>
				<td colspan="3">
				<table width="100%" align="center" bgcolor="#90c7fc" border="0">
					<tr bordercolor="#79bbfd">
						<td bgcolor="#79bbfd" align="center"><strong>Quantidades de Parcelamentos / Reparcelamentos</strong></td>
					</tr>
				</table>
				<table width="100%" align="center" bgcolor="#90c7fc" border="0">
					<tr bordercolor="#90c7fc">
						<td align="center" width="30%" bgcolor="#90c7fc" ><strong>Parcelamentos</strong></td>
						<td align="center" width="32%" bgcolor="#90c7fc" ><strong>Reparcelamentos</strong></td>
						<td align="center" width="38%" bgcolor="#90c7fc" ><strong>Reparcelamentos Consecutivos</strong></td>
					</tr>
					<tr bordercolor="#90c7fc">
						<td align="center" width="30%" bgcolor="#FFFFFF">
							<span id="parcelamento"> 
								<logic:notEmpty name="ConsultarImovelActionForm" property="parcelamento" scope="session">
									<bean:write	name="ConsultarImovelActionForm" property="parcelamento"	scope="session" />
								</logic:notEmpty>
								<logic:empty name="ConsultarImovelActionForm" property="parcelamento" scope="session">
									0
								</logic:empty>
							</span>
						</td>
						<td align="center" width="32%" bgcolor="#FFFFFF">
							<span id="reparcelamento"> 
								<logic:notEmpty name="ConsultarImovelActionForm" property="reparcelamento" scope="session">
									<bean:write	name="ConsultarImovelActionForm" property="reparcelamento" scope="session" />
								</logic:notEmpty>
								<logic:empty name="ConsultarImovelActionForm" property="reparcelamento" scope="session">
									0
								</logic:empty>
							</span>
							</td>
						<td align="center" width="38%" bgcolor="#FFFFFF">
							<span id="reparcelamentoConsecutivo"> 
								<logic:notEmpty name="ConsultarImovelActionForm" property="reparcelamentoConsecutivo" scope="session">
									<bean:write	name="ConsultarImovelActionForm" property="reparcelamentoConsecutivo" scope="session" />
								</logic:notEmpty>
								<logic:empty name="ConsultarImovelActionForm" property="reparcelamentoConsecutivo" scope="session">
									0
								</logic:empty>
							</span>
						</td>
					</tr>
				</table>
				</td>
			</tr>
			<tr>
				<td colspan="3" height="10"></td>
			</tr>
			<tr>
				<td colspan="3">
				<table width="100%" align="center" bgcolor="#90c7fc" border="0">
					<tr bordercolor="#79bbfd">
						<td bgcolor="#79bbfd" align="center"><strong>Parcelamentos Efetuados para o Imóvel</strong></td>
					</tr>
				</table>
				<table width="100%" align="center" bgcolor="#90c7fc" border="0">
					<tr bordercolor="90c7fc">
						<td bgcolor="90c7fc" align="center"><strong>Data</strong></td>
						<td align="center" bgcolor="90c7fc"><strong>Hora</strong></td>
						<td align="center" bgcolor="90c7fc"><strong>D&eacute;bito Atualizado</strong></td>
						<td align="center" bgcolor="90c7fc"><strong>Desconto Concedido</strong></td>
						<td align="center" bgcolor="90c7fc"><strong>Valor da Entrada</strong></td>
						<td align="center" bgcolor="90c7fc"><strong>N&uacute;mero de Presta&ccedil;&otilde;es</strong></td>
						<td align="center" bgcolor="90c7fc"><strong>Valor da Presta&ccedil;&atilde;o</strong></td>
						<td align="center" bgcolor="90c7fc"><strong>Situa&ccedil;&atilde;o</strong></td>
					</tr>
					<%String cor = "#cbe5fe";%>	
					<logic:present name="colecaoParcelamento">
						<logic:iterate name="colecaoParcelamento"
							id="parcelamento">
					
						<%if (cor.equalsIgnoreCase("#cbe5fe")) {
								cor = "#FFFFFF";%>
							<tr bgcolor="#FFFFFF">
								<%} else {
								cor = "#cbe5fe";%>
							<tr bgcolor="#cbe5fe">
								<%}%>
							<td align="center">

								<a href="javascript:abrirPopup('exibirConsultarParcelamentoDebitoPopupAction.do?codigoImovel=<bean:define name="parcelamento"
								property="imovel" id="imovel" /><bean:write name="imovel"
								property="id" />&codigoParcelamento=<bean:write name="parcelamento" property="id" />', 400, 800);"><bean:write name="parcelamento" property="parcelamento" formatKey="date.format" /></a>
							</td>
							<td align="center"><bean:write name="parcelamento" property="parcelamento" formatKey="hour.format"/></td>
							<td align="right"><bean:write name="parcelamento" property="valorDebitoAtualizado"  format="0.00"/></td>
							<td align="right"><bean:write name="parcelamento" property="valorDesconto"  format="0.00"/></td>
							<td align="right"><bean:write name="parcelamento" property="valorEntrada"  format="0.00"/></td>
							<td align="right"><bean:write name="parcelamento" property="numeroPrestacoes"/></td>
							<td align="right"><bean:write name="parcelamento" property="valorPrestacao"  format="0.00"/></td>
							<td align="left"><bean:write name="parcelamento" property="parcelamentoSituacao.descricaoAbreviada" /></td>
						</tr>
						</logic:iterate>
					</logic:present>
				</table>
				</td>
			</tr>
			<tr>
			<%-- <td align="right" colspan="3">
				<input type="button" name="Button" class="bottonRightCol" value="Consultar" onClick="validarForm(document.forms[0]);" />
				<input name="Button" type="button" class="bottonRightCol" value="Fechar" onClick="javascript:fechar();">
			</td>--%>
			<td colspan="3">
			&nbsp;
			</td>
			</tr>
			<tr>
				<td align="left"></td>
				<td height="10">&nbsp;</td>
				<td>&nbsp;</td>
			</tr>
			<!-- Fim do Corpo - Fernanda Paiva 07/02/2006  -->
		</table>
		<p>&nbsp;</p>
		<table width="100%" border="0">
			<tr>
				<td colspan="2">
				<div align="right"><jsp:include
					page="/jsp/util/wizard/navegacao_botoes_wizard_consulta.jsp?numeroPagina=9" /></div>
				</td>
			</tr>
		</table>
		
		</td>
	</tr>
</table>
<%@ include file="/jsp/util/rodape.jsp"%>
<%@ include file="/jsp/util/tooltip.jsp"%>
</html:form>
</body>
</html:html>
