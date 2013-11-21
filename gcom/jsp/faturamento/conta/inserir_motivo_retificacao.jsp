<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>

<%@ page import="gcom.seguranca.transacao.TabelaColuna"%>

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

function validarForm(form){

	if(validateInserirMotivoRetificacaoActionForm(form)){
	    form.action="inserirMotivoRetificacaoAction.do";
	    submeterFormPadrao(form);
	}
}

function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
    var form = document.forms[0];

    if (tipoConsulta == 'TabelaColuna') {
    	form.descColuna.style.color = "#000000";
  		form.idColuna.value = descricaoRegistro;
   		form.descColuna.value = descricaoRegistro;
	}
	
	form.action = 'exibirInserirMotivoRetificacaoAction.do';
	form.submit();
} 


function limparCampo() {

	var form = document.forms[0];
	
	form.idColuna.value = "";
	form.descColuna.value = "";

}

function adicionar() {
	
	var form = document.forms[0];
	
	if (form.idColuna.value == '') {
		alert('Informe o Campo.');
	} else {
		form.action = 'exibirInserirMotivoRetificacaoAction.do?acao=adicionar';
		form.submit();
	}
	
}
function limpar(){
	var form = document.forms[0];
	
	form.action = 'exibirInserirMotivoRetificacaoAction.do?acao=limpar';
	form.submit();
}

function remover(obj){
	var form = document.forms[0];
	
	if (confirm('Confirma Remoção?')) {

		form.action = 'exibirInserirMotivoRetificacaoAction.do?acao=remover&id='+obj;
		form.submit();
	}
}
</script>

</head>

<html:javascript staticJavascript="false"
	formName="InserirMotivoRetificacaoActionForm" />
	
<body leftmargin="5" topmargin="5"
	onload="setarFoco('${requestScope.nomeCampo}');">

<html:form action="/inserirMotivoRetificacaoAction"
	name="InserirMotivoRetificacaoActionForm"
	type="gcom.gui.faturamento.conta.InserirMotivoRetificacaoActionForm"
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
					<td class="parabg">Inserir Motivo de Retificação</td>

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
					<p>Para adicionar um motivo de retificação, informe os dados abaixo:</p>
					</td>
					<logic:present scope="application" name="urlHelp">
					<td align="right"><a href="javascript:abrirPopupHelp('${applicationScope.urlHelp}atendimentoRegistroTipoSolicitacaoEspecificacoesInserir', 500, 700);"><span style="font-weight: bold"><font color="#3165CE">Ajuda</font></span></a></td>									
					</logic:present>
					<logic:notPresent scope="application" name="urlHelp">
					<td align="right"><span style="font-weight: bold"><font color=#696969><u>Ajuda</u></font></span></td>									
					</logic:notPresent>
				</tr>
				<tr>
					<td width="30%"><strong>Descrição:<font
						color="#FF0000">*</font></strong></td>
					<td><html:text property="descricao" size="40" maxlength="35"
						tabindex="1" /></td>
				</tr>
				<tr>
					<td width="30%"><strong>Limite de reincidência em doze meses:</strong></td>
					<td><html:text property="numeroOcorrenciasNoAno" size="8" maxlength="3"
						tabindex="1" /></td>
				</tr>
				<tr>
					<td width="30%"><strong>Validar Competência de Consumo?<font
						color="#FF0000">*</font></strong></td>
					<td><html:radio property="indicadorCompetenciaConsumo" value="1"
						tabindex="3" /> <strong>Sim</strong>&nbsp; <html:radio
						property="indicadorCompetenciaConsumo" value="2" tabindex="4" /> <strong>N&atilde;o</strong>
				</tr>
				<tr>
					<td colspan="2">&nbsp;</td>
				</tr>
			</table>
				
			<table width="100%" border="0">
				<tr>
					<td width="15%">
						<strong>Campo:</strong>
					</td>					
					<td colspan="5">
						<html:text property="idColuna" style="text-transform: none;"
							size="40" maxlength="100" tabindex="6"
							onkeypress="pesquisaEnterSemUpperCase(event, 'exibirInserirMotivoRetificacaoAction.do', 'idColuna');" />
						
						<a href="javascript:abrirPopup('exibirPesquisarColunaTabelaAction.do?podeRetificarContaAction=SIM', 346, 590);">
							<img width="23" height="21" border="0" 
								src="<gsan:i18n key="caminho.imagens"/>pesquisa.gif" title="Pesquisar Coluna"/></a> 
					</td>
				</tr>
				<tr>
					<td>&nbsp;&nbsp;&nbsp;</td>
					<td colspan="3">
						
						<logic:present name="colunaEncontrada" scope="session">
							<html:text property="descColuna" readonly="true"
								style="background-color:#EFEFEF; border:0" size="40"
								maxlength="40" />
						</logic:present>
						
						<logic:notPresent name="colunaEncontrada" scope="session">
							<html:text property="descColuna" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000"
								size="40" maxlength="40" />
						</logic:notPresent>
										
						<a href="javascript:limparCampo();">
							<img src="<gsan:i18n key="caminho.imagens"/>limparcampo.gif"
								border="0" title="Limpar Coluna" /> 
						</a>
					</td> 
					<td>&nbsp;&nbsp;&nbsp;</td>
					<td>
						<input type="button" name="Submit24"
							class="bottonRightCol" value="Adicionar"
							onClick="javascript:adicionar();"
							tabindex="7">
					</td>
				</tr>
				<tr>
					<td colspan="6">
					<table width="100%" cellpadding="0" cellspacing="0">
						<tr bordercolor="#000000">
							<td bgcolor="#90c7fc" align="center" width="15%">
							<div align="center"><strong>Remover</strong></div>
							</td>
							<td bgcolor="#90c7fc" width="80%"><strong>Campo(s) permitido(s)
							para retificação</strong></td>
						</tr>
						<logic:present name="colecaoCampos">
							<tr>
								<td colspan="3">

								<div style="width: 100%; height: 100%; overflow: auto;">
								<table width="100%" bgcolor="#99CCFF">

									<logic:iterate name="colecaoCampos"
										id="campo" type="TabelaColuna">
										<c:set var="count2" value="${count2+1}" />
										<c:choose>
											<c:when test="${count2%2 == '1'}">
												<tr bgcolor="#FFFFFF">
											</c:when>
											<c:otherwise>
												<tr bgcolor="#cbe5fe">
											</c:otherwise>
										</c:choose>
											<td width="15%">
											<div align="center"><font color="#333333"> <img width="14"
												height="14" border="0"
												src="<bean:message key="caminho.imagens"/>Error.gif"
											 	onclick="remover('${count2}')" />
											</font></div>
											</td>
											<td width="80%">
												<bean:write name="campo"
													property="descricaoColuna" /></td>
										</tr>

									</logic:iterate>

										<tr bgcolor="#FFFFFF">
											<td width="15%">
												<div align="center" ></div>
											</td>
											<td width="80%" height="17">
											</td>
										</tr>
								</table>
								</div>
								</td>
							</tr>
						</logic:present>
					</table>
					</td>
				</tr>
				
			</table>
			<table width="100%" border="0">
				<tr>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td width="500" colspan="2" align="center">
						<div align="center"><font color="#FF0000">*</font> Campos obrigatórios </div>
					</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td valign="top"><input name="button" type="button"
						class="bottonRightCol" value="Limpar" onclick="limpar();">&nbsp;<input
						type="button" name="ButtonCancelar" class="bottonRightCol"
						value="Cancelar"
						onClick="javascript:document.forms[0].target='';window.location.href='/gsan/telaPrincipal.do'"></td>
					<td valign="top">
					<div align="right"><input name="botaoInserir" type="button"
						class="bottonRightCol" value="Inserir"
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
