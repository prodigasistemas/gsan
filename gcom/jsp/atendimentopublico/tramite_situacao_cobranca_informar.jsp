<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>

<%@ page import="gcom.util.ConstantesSistema"%>
<%@ page import="gcom.atendimentopublico.EspecificacaoUnidadeCobranca"%>

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

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false"
	formName="InserirTipoSolicitacaoEspecificacaoActionForm" />

<script language="JavaScript">

function validaRadioButton(nomeCampo,mensagem){
	var alerta = "";
	if(!nomeCampo[0].checked && !nomeCampo[1].checked){
		alerta = mensagem +" deve ser selecionado.";
	}
	return alerta;
}

function validarForm(form){

	if(form.idSituacaoCobranca.value != null && form.idSituacaoCobranca.value != '' && form.idSituacaoCobranca.value != '-1'){
	    document.forms[0].target='';
	    form.action="informarTramiteSituacaoCobrancaAction.do";
	    submeterFormPadrao(form);
	}
}

function desfazer(){
	 form = document.forms[0];
	 form.action='exibirInformarTramiteSituacaoCobrancaAction.do?menu=sim';
	 form.submit();
}

function removerAssociacao(id){
	 form = document.forms[0];
	if (confirm('Confirma Remoção?')) {
		 form.action='exibirInformarTramiteSituacaoCobrancaAction.do?removerAssociacao='+id;
		 form.submit();
	 }
}

function recuperarAssociacoes(){
	 form = document.forms[0];
	 document.forms[0].target='';
	 form.action='exibirInformarTramiteSituacaoCobrancaAction.do?recuperarAssociacoes=SIM';
	 form.submit();
}

function abrirPopupAssociacao() {
	var form = document.forms[0];
	
	if(form.idSituacaoCobranca.value != null && form.idSituacaoCobranca.value != '' && form.idSituacaoCobranca.value != '-1'){
		abrirPopup('exibirInformarAssociacaoSituacaoCobrancaAction.do?abrirPopup=SIM&idSituacaoCobranca='+form.idSituacaoCobranca.value, 370, 570);
	} else {
		alert('Selecione a Situação de Cobrança.');
	}
}

</script>

</head>

<body leftmargin="5" topmargin="5"
	onload="setarFoco('${requestScope.nomeCampo}');">

<html:form action="/informarTramiteSituacaoCobrancaAction"
	name="InformarTramiteSituacaoCobrancaActionForm"
	type="gcom.gui.atendimentopublico.InformarTramiteSituacaoCobrancaActionForm"
	method="post">

	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>
	<table width="770" border="0" cellspacing="5" cellpadding="0">
		<tr>

			<td width="115" valign="top" class="leftcoltext">
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
			<td width="600" valign="top" bgcolor="#003399" class="centercoltext">
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
					<td class="parabg">Informar Tramite por Situação de Cobrança</td>

					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
				<tr>
					<td height="5" colspan="3"></td>
				</tr>
			</table>
			<table width="100%" border="0">
				<tr>
					<td colspan="2">
					<p>Para informar Tramite por Situação de Cobrança, informe os dados abaixo:</p>
					</td>
				</tr>
				<tr>
					<td width="30%"><strong>Situação de Cobrança:<font color="#FF0000">*</font></strong></td>
					<td><html:select property="idSituacaoCobranca" tabindex="3" onchange="recuperarAssociacoes();" >
						<logic:notEmpty name="colecaoCobrancaSituacao">
							<option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</option>
							<html:options collection="colecaoCobrancaSituacao"
								labelProperty="descricao" property="id" />
						</logic:notEmpty>
					</html:select></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>
					<td valign="top">
						<div align="right"><input name="botaoAssociar" type="button"
							class="bottonRightCol" value="Associar"
							onclick="abrirPopupAssociacao();" tabindex="8"></div>
					</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td height="62" colspan="3"/>					
				</tr>
			</table>
			<p>&nbsp;</p>
			<table width="100%" cellpadding="0" cellspacing="0">
			
					<tr bordercolor="#000000">
						<td bgcolor="#90c7fc" align="center" width="10%">
						<div align="center"><strong>Remover</strong></div>
						</td>
						<td bgcolor="#90c7fc" width="45%"><strong>Descrição da Especificação</strong></td>
						<td bgcolor="#90c7fc" width="45%"><strong>Nome da unidade</strong></td>
					</tr>
					<logic:present name="colecaoEspecificacaoUnidadeCobranca">
						<tr>
							<td colspan="3">
	
							<div style="width: 100%; height: 100%; overflow: auto;">
							<table width="100%" bgcolor="#99CCFF">
								<tr>
								<logic:iterate name="colecaoEspecificacaoUnidadeCobranca"
									id="especificacaoUnidadeCobranca" type="EspecificacaoUnidadeCobranca">
									<c:set var="count2" value="${count2+1}" />
									<c:choose>
										<c:when test="${count2%2 == '1'}">
											<tr bgcolor="#FFFFFF">
										</c:when>
										<c:otherwise>
											<tr bgcolor="#cbe5fe">
										</c:otherwise>
									</c:choose>
				
									<td width="10%">
										<div align="center"><font color="#333333"> <img width="14"
											height="14" border="0"
											src="<bean:message key="caminho.imagens"/>Error.gif"
										 	onclick="removerAssociacao('${count2}')" />
										</font></div>
									</td>
									<td width="45%">
			      				 		<bean:define name="especificacaoUnidadeCobranca" property="solicitacaoTipoEspecificacao" id="solicitacaoTipoEspecificacao" /> 
										<bean:write name="solicitacaoTipoEspecificacao"
											property="descricao" />
									</td>
									<td width="45%">
										<bean:define name="especificacaoUnidadeCobranca" property="unidadeOrganizacional" id="unidadeOrganizacional" /> 
										<bean:write name="unidadeOrganizacional"
											property="descricao" />
									</td>
								</logic:iterate>
								</tr>
	
							</table>
							</div>
							</td>
						</tr>
					</logic:present>
			</table>
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>
					<td valign="top"><input name="button" type="button"
						class="bottonRightCol" value="Desfazer" onclick="desfazer();">&nbsp;<input
						type="button" name="ButtonCancelar" class="bottonRightCol"
						value="Cancelar"
						onClick="javascript:document.forms[0].target='';window.location.href='/gsan/telaPrincipal.do'"></td>
					<td valign="top">
					<div align="right"><input name="botaoInserir" type="button"
						class="bottonRightCol" value="Informar"
						onclick="validarForm(document.forms[0]);" tabindex="8"></div>
					</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td height="62" colspan="3"/>					
				</tr>
			</table>
	</table>

	<%@ include file="/jsp/util/rodape.jsp"%>
</body>
</html:form>
</html:html>
