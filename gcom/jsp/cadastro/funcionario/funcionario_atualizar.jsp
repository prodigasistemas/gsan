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

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false" formName="AtualizarFuncionarioActionForm" />
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript">

	function validarForm(form){
	
		if(testarCampoValorZero(document.AtualizarFuncionarioActionForm.nome, 'Nome') && 
		testarCampoValorZero(document.AtualizarFuncionarioActionForm.matricula, 'Matrícula') && 
		testarCampoValorZero(document.AtualizarFuncionarioActionForm.empresa, 'Empresa') && 
		testarCampoValorZero(document.AtualizarFuncionarioActionForm.idUnidade, 'Unidade') &&
		validateDate(form) &&
		validarIdadeFuncionario() &&
		validateCpf(form)) {
	
			if(validateAtualizarFuncionarioActionForm(form)){
				submeterFormPadrao(form);
			}
		}
	}
	
	function limparUnidade() {
		var form = document.AtualizarFuncionarioActionForm;
		form.idUnidade.value = "";
		form.nomeUnidade.value = "";
	}

	/* Chama Popup */ 
	function chamarPopup(url, tipo, objeto, codigoObjeto, altura, largura, msg,objetoRelacionado){
		if(objetoRelacionado.disabled != true){
			if (objeto == null || codigoObjeto == null){
				abrirPopup(url + "&" + "tipo=" + tipo, altura, largura);
			} else{
				if (codigoObjeto.length < 1 || isNaN(codigoObjeto)){
					alert(msg);
				} else{
					abrirPopup(url + "&" + "tipo=" + tipo + "&" + objeto + "=" + codigoObjeto + "&caminhoRetornoTelaPesquisa=" + tipo, altura, largura);
				}
			}
		}
	}
	/* Recupera Dados Popup */	
	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
	    var form = document.forms[0];
	    
	    //form.action='exibirAtualizarFuncionarioAction.do';
	    if (tipoConsulta == 'unidadeOrganizacional') {
			form.idUnidade.value = codigoRegistro;
			form.nomeUnidade.value = descricaoRegistro;
			form.nomeUnidade.style.color = "#000000";
	   }
	    //submeterFormPadrao(form);
	}
	
	function DateValidations () {
		this.aa = new Array("dataNascimento", "Data de Nascimento inválida.", new Function ("varName", "this.datePattern='dd/MM/yyyy';  return this[varName];"));
	}
	
	function IntegerValidations () {
		this.aa = new Array("numeroCpf", "CPF deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
	}
	
	function cpf () {
		this.aa = new Array("numeroCpf", "CPF inválido.", new Function ("varName", " return this[varName];"));
	}
	
	function validarIdadeFuncionario(){
	
		var retorno = true;
		var form = document.forms[0];
		
		if (form.dataNascimento.value.length > 0){
			
			var idadeMinimaFuncionario = document.getElementById("IDADE_MINIMA_FUNCIONARIO").value;			
			
			var dataAtual = document.getElementById("DATA_ATUAL").value;
			var idadeFuncionario = anosEntreDatas(form.dataNascimento.value, dataAtual);
			
			if (parseInt(idadeFuncionario) < parseInt(idadeMinimaFuncionario)){
				
				alert("O funcionário terá que possuir, no mínimo, " + idadeMinimaFuncionario + " anos de idade");
				form.dataNascimento.focus();
				retorno = false;
			}
		}
		
		return retorno;
	}
</script>


</head>

<body leftmargin="5" topmargin="5"
	onload="javascript:setarFoco('${requestScope.nomeCampo}');">
	
<html:form action="/atualizarFuncionarioAction.do" 
	method="post"
	name="AtualizarFuncionarioActionForm"
	type="gcom.gui.cadastro.funcionario.AtualizarFuncionarioActionForm"
	onsubmit="return validateAtualizarFuncionarioActionForm(this);">
	
	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>
	
	<INPUT TYPE="hidden" ID="DATA_ATUAL" value="${requestScope.dataAtual}" />
	<INPUT TYPE="hidden" ID="IDADE_MINIMA_FUNCIONARIO" value="${requestScope.idadeMinimaFuncionario}" />

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

				<table width="100%" border="0" align="center" cellpadding="0"
					cellspacing="0">
					<tr>
						<td width="11"><img border="0" src="imagens/parahead_left.gif" /></td>
						<td class="parabg">Atualizar Funcion&aacute;rio</td>
						<td width="11" valign="top">
							<img border="0"
								src="imagens/parahead_right.gif" /></td>
					</tr>
	
				</table>
				
				<p>&nbsp;</p>
		
				<table width="100%" border="0">
					
					<tr>
						<td colspan="2">Para atualizar um funcion&aacute;rio, informe os dados
						abaixo:</td>
					</tr>
					
					<tr>
						<td><strong>Matrícula:<font color="#FF0000">*</font></strong></td>
						<td>
							<html:hidden property="idFuncionario" /> 
							<bean:write name="AtualizarFuncionarioActionForm" 
								property="matricula" /></td>
					</tr>
					
					<tr>
						<td><strong>Nome:<font color="#FF0000">*</font></strong></td>
						<td>
							<html:text property="nome" 
								size="60" 
								maxlength="70" /></td>
					</tr>
				
					<tr>
						<td height="31">
							<strong>CPF:<font color="#FF0000">*</font></strong>
						</td>
						<td>
							<html:text property="numeroCpf" 
								size="11" 
								maxlength="11" 
								tabindex="1" />
						</td>
					</tr>
				
					<tr>
						<td height="24">
							<strong>Data de Nascimento:</strong>
						</td>
						
						<td>
							<html:text property="dataNascimento" 
								size="10" 
								maxlength="10"
								tabindex="6" 
								onkeyup="javascript:mascaraData(this, event);"/>
								
								<a href="javascript:abrirCalendario('InserirFuncionarioActionForm', 'dataNascimento')">
									<img border="0" 
										src="<bean:message key="caminho.imagens"/>calendario.gif"
										width="20" 
										border="0" 
										align="absmiddle" 
										alt="Exibir Calendário" /></a>
								<font size="2">dd/mm/aaaa</font>
						</td>
					</tr>
					
					<tr>
						<td><strong>Cargo:<font color="#ff0000">*</font></strong></td>
						<td>
							<html:select property="funcionarioCargo">
								<option value="-1"></option>
								<html:options name="request" 
									collection="colecaoFuncionarioCargo"
									labelProperty="descricao" 
									property="id" />
							</html:select>
						</td>
					</tr>
					
					<tr>
						<td><strong>Empresa:<font color="#ff0000">*</font></strong></td>
						<td>
							<html:select property="empresa">
								<option value=""></option>
								<html:options name="request" 
									collection="colecaoEmpresa"
									labelProperty="descricao" 
									property="id" />
						</html:select></td>
					</tr>
					
					<tr>
						<td><strong>Unidade Organizacional: 
							<font color="#FF0000">*</font></strong>
						</td>
						<td colspan="2">
							<html:text maxlength="4" 
								property="idUnidade"
								size="4" 
								onkeyup="javascript:limparUnidadeTecla();" 
								onkeyup="javascript:verificaNumeroInteiro(this);" 
								onkeypress="javascript:validaEnterComMensagem(event, 'exibirAtualizarFuncionarioAction.do?enter=sim', 'idUnidade', 'Unidade Organizacional');" />
							<a href="javascript:abrirPopup('exibirPesquisarUnidadeOrganizacionalAction.do?limparForm=OK');">
								<img width="23" 
									height="21" 
									border="0"
									src="<bean:message key="caminho.imagens"/>pesquisa.gif"
									title="Pesquisar Unidade Organizacional" /></a> 
							
							<logic:present name="idUnidadeNaoEncontrado" scope="request">
								<html:text maxlength="30" 
									property="nomeUnidade" 
									readonly="true"
									style="background-color:#EFEFEF; border:0; color: #ff0000"
									size="40" />
							</logic:present> 
							
							<logic:notPresent name="idUnidadeNaoEncontrado" scope="request">
								<html:text maxlength="30" 
									property="nomeUnidade" 
									readonly="true"
									style="background-color:#EFEFEF; border:0; color: #000000"
									size="40" />
							</logic:notPresent> 
							
							<a href="javascript:limparUnidade();"> 
								<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
									border="0" 
									title="Apagar" /></a>
						</td>
					</tr>
					
					<tr>
						<td><strong> <font color="#FF0000"></font></strong></td>
						<td align="right">
							<div align="left"><strong><font color="#FF0000">*</font></strong>
							Campos obrigat&oacute;rios</div>
						</td>
					</tr>
				</table>
				
				<table width="100%" border="0">
					<tr>
						<td>
							<logic:present name="manter" scope="session">
								<input type="button" 
									name="ButtonReset" 
									class="bottonRightCol" 
									value="Voltar"
									onClick="javascript:window.location.href='/gsan/exibirManterFuncionarioAction.do'">
							</logic:present>
	
							<logic:notPresent name="manter" scope="session">
								<input type="button" 
									name="ButtonReset"  
									class="bottonRightCol" 
									value="Voltar"
									onClick="javascript:window.location.href='/gsan/exibirFiltrarFuncionarioAction.do'">
							</logic:notPresent> 
							
							<input name="Button" 
								type="button" 
								class="bottonRightCol"
								value="Desfazer" 
								align="left"
								onclick="window.location.href='<html:rewrite page="/exibirAtualizarFuncionarioAction.do?desfazer=S"/>'">
							
							<input name="Button" 
								type="button" 
								class="bottonRightCol"
								value="Cancelar" 
								align="left"
								onClick="javascript:window.location.href='/gsan/telaPrincipal.do'">
						</td>
							
						<td align="right">
							<input name="Button" 
								type="button"
								class="bottonRightCol" 
								value="Atualizar" 
								align="right"
								onClick="javascript:validarForm(document.forms[0]);">
						</td>
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