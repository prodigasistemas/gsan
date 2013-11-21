<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>

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

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false" 
	formName="AtualizarTipoSolicitacaoEspecificacaoActionForm" />

<script language="JavaScript">

function validaRadioButton(nomeCampo,mensagem){
	var alerta = "";
	if(!nomeCampo[0].checked && !nomeCampo[1].checked){
		alerta = mensagem +" deve ser selecionado.";
	}
	return alerta;
}
function validaTodosRadioButton(){
	var form = document.forms[0];
	var mensagem = "";
	if(validaRadioButton(form.indicadorFaltaAgua,"Falta D'água") != ""){
			mensagem = mensagem + validaRadioButton(form.indicadorFaltaAgua," Falta D'água")+"\n";
	}
	if(validaRadioButton(form.indicadorTarifaSocial,"Tarifa Social") != ""){
			mensagem = mensagem + validaRadioButton(form.indicadorTarifaSocial,"Tarifa Social")+"\n";
	}
	if(mensagem != ""){
		alert(mensagem);
		return false;
	}else{
		return true;
	}
}

    

 
function validarForm(form){

if(validateAtualizarTipoSolicitacaoEspecificacaoActionForm(form)){
    document.forms[0].target='';
    form.action="atualizarTipoSolicitacaoEspecificacaoAction.do";
    
    submeterFormPadrao(form);
}
}

function desfazer(){
 form = document.forms[0];
 document.forms[0].target='';
 form.action='exibirAtualizarTipoSolicitacaoEspecificacaoAction.do?limpaSessao=1';
 form.submit();
}



function abrirPopupComSubmit(form,altura, largura){
 var height = window.screen.height - 160;
 var width = window.screen.width;
 var top = (height - altura)/2;
 var left = (width - largura)/2;
 window.open('', 'Pesquisar','top=' + top + ',left='+ left +',location=no,screenY=0,screenX=0,menubar=no,status=no,toolbar=no,scrollbars=yes,resizable=no,width=' + largura + ',height=' + altura);
 form.target='Pesquisar';
 form.action = 'recuperarDadosInserirTipoSolicitacaoEspecificacaoAction.do?retornarTela=exibirAtualizarTipoSolicitacaoEspecificacaoAction.do&limpaSessao=1';
                
 submeterFormPadrao(form);
}

	/* Remove Componente da grid */	
	function remover(id){
		var form = document.AtualizarTipoSolicitacaoEspecificacaoActionForm;
		var where_to= confirm("Confirmar remoção?");
		if (where_to== true) {
		    form.action='exibirAtualizarTipoSolicitacaoEspecificacaoAction.do?deleteComponente='+id;
		    form.submit();
 		}
	}

</script>

</head>

<body leftmargin="5" topmargin="5"
	onload="setarFoco('${requestScope.nomeCampo}');">

<html:form action="/atualizarTipoSolicitacaoEspecificacaoAction"
	name="AtualizarTipoSolicitacaoEspecificacaoActionForm"
	type="gcom.gui.atendimentopublico.registroatendimento.AtualizarTipoSolicitacaoEspecificacaoActionForm"
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
					<td class="parabg">Atualizar Tipo de Solicitação com Especificações</td>

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
					<p>Para atualizar um tipo de solicitação, informe os dados abaixo:</p>
					<p>&nbsp;</p>
					</td>
					<logic:present scope="application" name="urlHelp">
								<td align="right"><a href="javascript:abrirPopupHelp('${applicationScope.urlHelp}atendimentoRegistroTipoSolicitacaoEspecificacoesAtualizar', 500, 700);"><span style="font-weight: bold"><font color="#3165CE">Ajuda</font></span></a></td>									
					</logic:present>
					<logic:notPresent scope="application" name="urlHelp">
								<td align="right"><span style="font-weight: bold"><font color=#696969><u>Ajuda</u></font></span></td>									
					</logic:notPresent>
				</tr>

				<tr>
					<td><strong>Código do Tipo de Solicitação:</strong></td>
					<td><html:hidden property="idTipoSolicitacao" /> <bean:write
						name="AtualizarTipoSolicitacaoEspecificacaoActionForm"
						property="idTipoSolicitacao" /></td>
				</tr>

				<tr>
					<td width="40%"><strong>Descrição do Tipo de Solicitação:<font
						color="#FF0000">*</font><strong></td>
					<td><html:text property="descricao" size="40" maxlength="50"
						tabindex="1" /></td>
				</tr>
				<tr>
					<td><strong>Grupo de Solicitação do Tipo:<font color="#FF0000">*</font></strong></td>
					<td><html:select property="idgrupoTipoSolicitacao" tabindex="2">
						<html:option value="">&nbsp;</html:option>
						<html:options collection="colecaoSolicitacaoTipoGrupo"
							labelProperty="descricao" property="id" />
					</html:select></td>
				</tr>
				<tr>
					<td width="40%"><strong>Falta D'água:<font color="#FF0000">*</font></strong></td>
					<td><html:radio property="indicadorFaltaAgua" value="1"
						tabindex="3" /> <strong>Sim</strong>&nbsp; <html:radio
						property="indicadorFaltaAgua" value="2" tabindex="4" /> <strong>N&atilde;o</strong>
				</tr>
				<tr>
					<td width="40%"><strong>Tarifa Social:<font color="#FF0000">*</font></strong></td>
					<td><html:radio property="indicadorTarifaSocial" value="1"
						tabindex="5" /> <strong>Sim</strong>&nbsp; <html:radio
						property="indicadorTarifaSocial" value="2" tabindex="6" /> <strong>N&atilde;o</strong>
				</tr>
				<tr>
					<td width="40%"><strong>Indicador de uso:<font color="#FF0000">*</font></strong></td>
					<td><html:radio property="indicadorUso" value="1" tabindex="5" />
					<strong>Ativo</strong>&nbsp; <html:radio property="indicadorUso"
						value="2" tabindex="6" /> <strong>Inativo</strong>
				</tr>
				<tr>
					<td width="40%"><strong>Uso Sistema:<font color="#FF0000">*</font></strong></td>
					<td>
						<logic:present name="temPermissaoAlterarIndicadorUsoSistemaTipoSolicitacao">
							<html:radio property="indicadorUsoSistema" value="1" tabindex="5" />
							<strong>Sim</strong>&nbsp; 
							<html:radio	property="indicadorUsoSistema" value="2" tabindex="6" />
							<strong>N&atilde;o</strong>
						</logic:present> 
						<logic:notPresent name="temPermissaoAlterarIndicadorUsoSistemaTipoSolicitacao">
							<html:radio property="indicadorUsoSistema" value="1" disabled="true" />
							<strong>Sim</strong>&nbsp; 
							<html:radio property="indicadorUsoSistema" value="2" disabled="true" />
							<strong>N&atilde;o</strong>
							<!-- Hidden utilizado para recuperar o valor no action -->
							<html:hidden property="indicadorUsoSistema" />
						</logic:notPresent>
					</td>
				</tr>
				<tr>
					<td height="24" colspan="3">
					<hr>
					</td>
				</tr>
				<tr>
					<td colspan="2"><strong>Especificação do Tipo da Solicitação<font
						color="#FF0000">*</font></strong></td>
					<td align="right"><input type="button" name="Submit24"
						class="bottonRightCol" value="Adicionar"
						onClick="javascript:abrirPopupComSubmit(document.forms[0], 400, 800);"
						tabindex="7"></td>
				</tr>

				<tr>
					<td colspan="3">
					<table width="100%" bgcolor="#99CCFF cellpadding="
						0" cellspacing="0">

						<tr bordercolor="#000000">
							<td bgcolor="#90c7fc" align="center" width="15%">
							<div align="center"><strong>Remover</strong></div>
							</td>
							<td bgcolor="#90c7fc" width="80%"><strong>Descrição da
							Especificação</strong></td>
						</tr>

						<tr>
							<logic:present name="colecaoSolicitacaoTipoEspecificacao">
								<logic:iterate name="colecaoSolicitacaoTipoEspecificacao"
									id="solicitacaoTipoEspecificacao">
									<c:set var="count" value="${count+1}" />
									<c:choose>
										<c:when test="${count%2 == '1'}">
											<tr bgcolor="#FFFFFF">
										</c:when>
										<c:otherwise>
											<tr bgcolor="#cbe5fe">
										</c:otherwise>
									</c:choose>
									<td bordercolor="#90c7fc">
									<div align="center"><img
										src="<bean:message key='caminho.imagens'/>Error.gif"
										width="14" height="14" style="cursor:hand;" name="imagem"
										onclick="remover('${count}');" alt="Remover"></div>
									</td>

									<!-- <td bordercolor="#90c7fc">
										<div align="center">
											<bean:write name="solicitacaoTipoEspecificacao" property="id" />
										</div>
										</td> -->
									<td bordercolor="#90c7fc">
									<div align="left"><a
										href="javascript:document.forms[0].target='';
												abrirPopup('exibirAtualizarAdicionarSolicitacaoEspecificacaoAction.do?atualizar=sim&posicao=${count}&trocou=sim',710,440);">
									<bean:write name="solicitacaoTipoEspecificacao"
										property="descricao" /> </a></div>
									</td>
						</tr>
						</logic:iterate>
						</logic:present>

					</table>
				<tr>
					<td colspan="3">
					<hr>
					</td>
				</tr>
				<tr>
					<td colspan="2">&nbsp;</td>
				</tr>
			</table>
			<table width="100%" border="0">
				<tr>
					<td colspan="2"><font color="#FF0000"><logic:present name="manter"
						scope="session">
						<input type="button" name="ButtonReset" class="bottonRightCol"
							value="Voltar"
							onClick="javascript:window.location.href='/gsan/filtrarTipoSolicitacaoEspecificacaoAction.do'">
					</logic:present><logic:notPresent name="manter" scope="session">
						<input type="button" name="ButtonReset" class="bottonRightCol"
							value="Voltar"
							onClick="javascript:window.location.href='/gsan/exibirFiltrarTipoSolicitacaoEspecificacaoAction.do'">
					</logic:notPresent> <input type="button" name="ButtonReset"
						class="bottonRightCol" value="Desfazer"
						onClick="window.location.href='<html:rewrite page="/exibirAtualizarTipoSolicitacaoEspecificacaoAction.do?desfazer=S&idTipoSolicitacao=${requestScope.idTipoSolicitacao}"/>'">&nbsp;<input
						type="button" name="ButtonCancelar" class="bottonRightCol"
						value="Cancelar"
						onClick="javascript:document.forms[0].target='';window.location.href='/gsan/telaPrincipal.do'"></td>
					<td valign="top">
					<div align="right"><input name="botaoInserir" type="button"
						class="bottonRightCol" value="Atualizar"
						onclick="validarForm(document.forms[0]);" tabindex="8"></div>
					</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
				</tr>
			</table>
	</table>

	<%@ include file="/jsp/util/rodape.jsp"%>
</body>
</html:form>
</html:html>
