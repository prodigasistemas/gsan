<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan"%>

<%@ page import="gcom.util.Util"%>
<%@ page import="java.util.Date"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>

<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false" formName="ComandarEncerramentoRegistroAtendimentoActionForm" />
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript">
<!-- Begin

function limparUsuarioTecla(){
	var form = document.forms[0];	
	form.nomeUsuario.value = "";
}

function limparUsuario(){
	var form = document.forms[0];
	form.idUsuario.value = "";
	form.nomeUsuario.value = "";
}

function limparUnidadeAtendimento() {
	var form = document.forms[0];
	form.idUnidadeAtendimento.value = "";
	form.nomeUnidadeAtendimento.value = "";	
}

function limparUnidadeAtendimentoTecla() {
	var form = document.forms[0];
	form.nomeUnidadeAtendimento.value = "";	
}

function limparUnidadeAtual() {
	var form = document.forms[0];
	form.idUnidadeAtual.value = "";
	form.nomeUnidadeAtual.value = "";
	form.idUnidadeSuperior.disabled = false;
}

function limparUnidadeAtualTecla() {
	var form = document.forms[0];
	form.nomeUnidadeAtual.value = "";	
	
	if(form.idUnidadeAtual.value.length > 0) {
		form.idUnidadeSuperior.disabled = true;
		form.idUnidadeSuperior.value = "";
	} else {	
		form.idUnidadeSuperior.disabled = false;
		form.idUnidadeAtual.value = "";
	}
	
}


function limparUnidadeSuperior() {
	var form = document.forms[0];
	form.idUnidadeSuperior.value = "";
	form.nomeUnidadeSuperior.value = "";
	form.idUnidadeAtual.disabled = false;
}

function limparUnidadeSuperiorTecla() {
	var form = document.forms[0];
	form.nomeUnidadeSuperior.value = "";	
	
	if(form.idUnidadeSuperior.value.length > 0) {
		form.idUnidadeAtual.disabled = true;
		form.idUnidadeAtual.value = "";
	} else {	
		form.idUnidadeAtual.disabled = false;
		form.idUnidadeSuperior.value = "";
	}
}

function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {

    var form = document.forms[0];
   
    if (tipoConsulta == 'usuario') {
		form.idUsuario.value = codigoRegistro;
		form.nomeUsuario.value = descricaoRegistro;
		form.nomeUsuario.style.color = "#000000";
		form.idUnidadeAtendimento.focus();
    }
   
    if (tipoConsulta == 'unidadeOrganizacional') {
     
	    if (form.tipoPesquisa.value == 'atendimento') {
    		form.idUnidadeAtendimento.value = codigoRegistro;
	    	form.nomeUnidadeAtendimento.value = descricaoRegistro;
	    	form.nomeUnidadeAtendimento.style.color = "#000000";
    		form.idUnidadeAtual.focus();
	    } else if (form.tipoPesquisa.value == 'atual') {
			form.idUnidadeAtual.value = codigoRegistro;
	    	form.nomeUnidadeAtual.value = descricaoRegistro;
	    	form.nomeUnidadeAtual.style.color = "#000000";
	    	form.idUnidadeSuperior.disabled = true;
	    	form.idUnidadeSuperior.value = "";
    	}
    
    }
    
    if (tipoConsulta == 'unidadeSuperior') {
     
   		form.idUnidadeSuperior.value = codigoRegistro;
    	form.nomeUnidadeSuperior.value = descricaoRegistro;
    	form.nomeUnidadeSuperior.style.color = "#000000";
   		form.idUnidadeAtual.disabled = true;
   		form.idUnidadeAtual.value = "";
    
    }
    
}

function replicaDados(campoOrigem, campoDestino){
	campoDestino.value = campoOrigem.value;
}
	
function chamarPesquisaUnidadeAtendimento() {

	document.forms[0].tipoPesquisa.value = 'atendimento';
	abrirPopup('exibirPesquisarUnidadeOrganizacionalAction.do', 400, 800);
	
}

function chamarPesquisaUnidadeAtual() {

	document.forms[0].tipoPesquisa.value = 'atual';

	if (document.forms[0].idUnidadeAtual.disabled == false) {
		abrirPopup('exibirPesquisarUnidadeOrganizacionalAction.do', 400, 800);
	}
}

function chamarPesquisaUnidadeSuperior() {
	
	if (document.forms[0].idUnidadeSuperior.disabled == false) {
		abrirPopup('exibirPesquisarUnidadeSuperiorAction.do', 400, 800);
	}
}

function controle(form) {

	if (form.idUnidadeAtual.value.length > 0) {
		form.idUnidadeSuperior.disabled = true;
		form.idUnidadeSuperior.value = "";
	} else if (form.idUnidadeSuperior.value.length > 0) {
		form.idUnidadeAtual.disabled = true;
		form.idUnidadeAtual.value = "";
	}
	
}


function habilitarUnidadeAtual(form) {

	if (!form.idUnidadeSuperior.disabled) {
		form.idUnidadeSuperior.disabled = true;
		form.idUnidadeSuperior.value = "";
	} 
}


function habilitarUnidadeSuperior(form) {

	if (!form.idUnidadeAtual.disabled) {
		form.idUnidadeAtual.disabled = true;
		form.idUnidadeAtual.value = "";
	} 
}

function validarForm(form){
	
	if(validateComandarEncerramentoRegistroAtendimentoActionForm(form)){
		if(comparaData(form.dataAtendimentoInicial.value, ">", form.dataAtendimentoFinal.value)) {
			alert('Data Final do Período é anterior à Data Inicial do Período');
		} else{
			if(comparaData(form.dataAtendimentoFinal.value, ">", form.dataAtual.value)) {
				alert('Data Final do Período é posterior à Data Atual');
			}else{
	    		submeterFormPadrao(form);
	    	}
	    }
	}
}

-->
</script>


</head>

<body leftmargin="5" topmargin="5" onload="javascript:setarFoco('${requestScope.nomeCampo}'); controle(document.forms[0]);">

<html:form action="/comandarEncerramentoRegistroAtendimentoAction"
	method="post" 
	name="ComandarEncerramentoRegistroAtendimentoActionForm"
	type="gcom.gui.atendimentopublico.registroatendimento.ComandarEncerramentoRegistroAtendimentoActionForm"
	onsubmit="return validateComandarEncerramentoRegistroAtendimentoActionForm(this);">

	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>

	<input type="hidden" name="tipoPesquisa" />
	<input type="hidden" name="dataAtual" value="${requestScope.dataAtual}"/>

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
			<table height="100%">
				<tr>
					<td></td>
				</tr>
			</table>

			<!--Início Tabela Reference a Páginação da Tela de Processo-->
			<table>
				<tr>
					<td></td>

				</tr>
			</table>
			
			<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
				<tr>
					<td width="11"><img border="0" src="imagens/parahead_left.gif" /></td>
					<td class="parabg">Comandar Encerramento de Registros de Atendimento</td>
					<td width="11" valign="top"><img border="0" src="imagens/parahead_right.gif" /></td>
				</tr>
			</table>			
			<!--Fim Tabela Reference a Páginação da Tela de Processo-->
			<p>&nbsp;</p>
			
			<table width="100%" border="0">
				<tr>
					<td colspan="2">Para comandar o encerramento de registros de atendimento, informe os dados abaixo:</td>
				</tr>
				
				<%-- Periodo de atendimento --%>
				<tr>
					<td width="200"><strong>Período de Atendimento:<font color="#FF0000">*</font></strong></td>
					<td colspan="2">
						<strong> 
							<%--<html:text maxlength="10" property="dataAtendimentoInicial" size="10" tabindex="1" onkeyup="mascaraData(this, event); replicaDados(document.forms[0].dataAtendimentoInicial, document.forms[0].dataAtendimentoFinal);" onfocus="replicaDados(document.forms[0].dataAtendimentoInicial, document.forms[0].dataAtendimentoFinal);" /> --%>
							<html:text maxlength="10" property="dataAtendimentoInicial" size="10" tabindex="1" onkeyup="mascaraData(this, event);" />
							<a href="javascript:abrirCalendarioReplicando('ComandarEncerramentoRegistroAtendimentoActionForm', 'dataAtendimentoInicial', 'dataAtendimentoFinal'); document.forms[0].motivoEncerramento.focus();">
								<img border="0"	src="<bean:message key="caminho.imagens"/>calendario.gif" width="20" border="0" align="absmiddle" alt="Exibir Calendário" />
							</a>
							a
						</strong> 
							<html:text maxlength="10" property="dataAtendimentoFinal" size="10" tabindex="2" onkeyup="mascaraData(this, event);" /> 
							<a href="javascript:abrirCalendario('ComandarEncerramentoRegistroAtendimentoActionForm', 'dataAtendimentoFinal');">
								<img border="0"	src="<bean:message key="caminho.imagens"/>calendario.gif" width="20" border="0" align="absmiddle" alt="Exibir Calendário" />
							</a>
							dd/mm/aaaa
					</td>
				</tr>
				<%-- Fim Periodo de atendimento --%>
				
				<%-- Motivo Encerramento --%>
				<tr>
					<td width="200"><strong>Motivo do Encerramento:<font color="#FF0000">*</font></strong></td>
					<td colspan="3">
						<html:select property="motivoEncerramento" tabindex="3" style="width:200px;">
							<html:option value="-1"> &nbsp; </html:option>
							<html:options collection="colecaoMotivoEncerramento" property="id" labelProperty="descricao" />
						</html:select>
					</td>
				</tr>
				<%-- Fim Motivo do Encerramento --%>

				<%-- Usuário Responsável --%>
				<tr>
					<td width="26%"><strong>Usuário Responsável:<font color="#FF0000">*</font></strong></td>
					<td width="74%">
						<html:text property="idUsuario" size="10" maxlength="9" tabindex="4" onkeyup="limparUsuarioTecla();" onkeypress="validaEnterComMensagem(event,'exibirComandarEncerramentoRegistroAtendimentoAction.do','idUsuario','Usuário Responsável');" />
						<a href="javascript:abrirPopup('exibirUsuarioPesquisar.do?indicadorUsoTodos=1');">
							<img border="0" src="imagens/pesquisa.gif" height="21" width="23">
						</a>

						<logic:present name="usuarioInexistente" scope="request">
							<html:text property="nomeUsuario" readonly="true" size="40"	maxlength="40" style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:present> 
						<logic:notPresent name="usuarioInexistente"	scope="request">
							<html:text property="nomeUsuario" readonly="true" maxlength="40" style="background-color:#EFEFEF; border:0" size="40" />
						</logic:notPresent> 
						
						<a href="javascript:limparUsuario();"> 
							<img border="0" src="imagens/limparcampo.gif" height="21" width="23"> 
						</a>
					</td>
				</tr>
				<%-- Fim Usuário Responsável --%>				
				
				<tr>
					<td height="18" colspan="4">
						<hr>
					</td>
				</tr>
				
				<%-- Unidade de Atendimento --%>
				<tr>
					<td width="200"><strong>Unidade de Atendimento:</strong></td>
					<td colspan="3">
						<strong> 
							<html:text property="idUnidadeAtendimento" size="5" maxlength="4" tabindex="5" onkeyup="limparUnidadeAtendimentoTecla();" onkeypress="validaEnterComMensagem(event, 'exibirComandarEncerramentoRegistroAtendimentoAction.do', 'idUnidadeAtendimento', 'Unidade de Atendimento');" />

							<a href="javascript:chamarPesquisaUnidadeAtendimento();">
								<img width="23" height="21" border="0" src="<bean:message key="caminho.imagens"/>pesquisa.gif" title="Pesquisar" /> 
							</a> 
							<logic:present name="unidadeAtendimentoInexistente" scope="request">
								<html:text property="nomeUnidadeAtendimento" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000" size="40" maxlength="40" />
							</logic:present> 
							<logic:notPresent name="unidadeAtendimentoInexistente" scope="request">
								<html:text property="nomeUnidadeAtendimento" readonly="true" style="background-color:#EFEFEF; border:0" size="40" maxlength="40" />
							</logic:notPresent> 
							<a href="javascript:limparUnidadeAtendimento();"> 
								<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"	border="0" title="Apagar" /> 
							</a> 
						</strong>
					</td>
				</tr>
				<%-- Fim Unidade de Atendimento --%>
				
				<%-- Unidade Atual --%>
				<tr>
					<td width="200"><strong>Unidade Atual:</strong></td>
					<td colspan="3">
						<strong> 
							<html:text property="idUnidadeAtual" size="5" maxlength="4" tabindex="6" onclick="habilitarUnidadeAtual(document.forms[0]);" onkeypress="validaEnterComMensagem(event, 'exibirComandarEncerramentoRegistroAtendimentoAction.do', 'idUnidadeAtual', 'Unidade Atual');" />

							<a href="javascript:chamarPesquisaUnidadeAtual();">
								<img width="23"	height="21" border="0" src="<bean:message key="caminho.imagens"/>pesquisa.gif" title="Pesquisar" /> 
							</a> 
							
							<logic:present name="unidadeAtualInexistente" scope="request">
								<html:text property="nomeUnidadeAtual" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000" size="40" maxlength="40" />
							</logic:present> 
							<logic:notPresent name="unidadeAtualInexistente" scope="request">
								<html:text property="nomeUnidadeAtual" readonly="true" style="background-color:#EFEFEF; border:0" size="40" maxlength="40" />
							</logic:notPresent> 
							
							<a href="javascript:limparUnidadeAtual();"> 
								<img src="<bean:message key="caminho.imagens"/>limparcampo.gif" border="0" title="Apagar" /> 
							</a> 
						</strong>
					</td>
				</tr>
				<%-- Fim Unidade Atual --%>
				
				<%-- Unidade Superior --%>
				<tr>
					<td width="200"><strong>Unidade Superior:</strong></td>
					<td colspan="3">
						<strong> 
							<html:text property="idUnidadeSuperior"	size="5" maxlength="4" tabindex="6" onclick="habilitarUnidadeSuperior(document.forms[0]);"	onkeypress="validaEnterComMensagem(event, 'exibirComandarEncerramentoRegistroAtendimentoAction.do', 'idUnidadeSuperior', 'Unidade Superior');" />

							<a href="javascript:abrirPopup('exibirPesquisarUnidadeSuperiorAction.do');">
								<img width="23" height="21" border="0" src="<bean:message key="caminho.imagens"/>pesquisa.gif" title="Pesquisar" /> 
							</a> 
							
							<logic:present name="unidadeSuperiorInexistente" scope="request">
								<html:text property="nomeUnidadeSuperior" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000" size="40" maxlength="40" />
							</logic:present> 
							<logic:notPresent name="unidadeSuperiorInexistente" scope="request">
								<html:text property="nomeUnidadeSuperior" readonly="true" style="background-color:#EFEFEF; border:0" size="40" maxlength="40" />
							</logic:notPresent> 
							
							<a href="javascript:limparUnidadeSuperior();">
								<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"	border="0" title="Apagar" /> 
							</a> 
						</strong>
					</td>
				</tr>
				<%-- Fim Unidade Superior --%>
				<tr>
           			<td width="200"><strong>Especificações:</strong></td>
            		<td><html:select style="width: 350px;" multiple="mutiple" size="10" property="idsSolicitacaoTipoEspecificcacoes">
			 		<html:option value="-1">&nbsp;</html:option>
                	<html:options collection="colecaoSolicitacaoTipoEspecificacao" labelProperty="descricao" property="id"/>
                </html:select>
            </td>
        </tr>
				<tr>
					<td height="18" colspan="4">
					<hr>
					</td>
				</tr>
				
				<tr>
					<td height="19"><strong> <font color="#FF0000"></font></strong></td>
					<td align="right">
						<div align="left">
							<strong><font color="#FF0000">*</font></strong>Campos obrigat&oacute;rios
						</div>
					</td>
				</tr>

				<tr>
					<td>
						<input name="Button" type="button" class="bottonRightCol" value="Limpar" align="left" onclick="window.location.href='/gsan/exibirComandarEncerramentoRegistroAtendimentoAction.do?menu=sim'" tabindex="17">
					</td>
					<td align="right" colspan="2">
						<input name="Button" type="button" class="bottonRightCol" value="Comandar" align="left" tabindex="16" onclick="validarForm(document.forms[0]);">
					</td>
					<td align="right"></td>
				</tr>
			</table>
			<p>&nbsp;</p>
		</tr>
	</table>
	<tr>
		<td colspan="3"><%@ include file="/jsp/util/rodape.jsp"%>
	</tr>
</html:form>
</body>
</html:html>

