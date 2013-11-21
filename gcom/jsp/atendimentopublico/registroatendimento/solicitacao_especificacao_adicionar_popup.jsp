<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
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

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false"
	formName="AdicionarSolicitacaoEspecificacaoActionForm" />

<script language="JavaScript">

    window.onmousemove = iniciarJsp;

	function validaRadioButton(nomeCampo,mensagem){
		var alerta = "";
	
		if(!nomeCampo[0].checked && !nomeCampo[1].checked){
			alerta = mensagem +" deve ser selecionado.";
		}
		return alerta;
	}

	function iniciarJsp(){
		var form = document.forms[0];
 
 		if(form.consultaDados.value == ''){
  			desabilitaCampos();
  			desabilitaCamposDebitoCredito();
  			desabilitaCamposMatriculaImovelOnClick();
 		}
	}
	
	function validaTodosRadioButton(){
	
		var form = document.forms[0];
		var mensagem = "";
	
		if(validaRadioButton(form.indicadorPavimentoCalcada," Pavimento Calçada Obrigatória?") != ""){
			mensagem = mensagem + validaRadioButton(form.indicadorPavimentoCalcada," Pavimento Calçada Obrigatória?")+"\n";
		}
	
		if(validaRadioButton(form.indicadorPavimentoRua,"Pavimento Rua Obrigatória?") != ""){
			mensagem = mensagem + validaRadioButton(form.indicadorPavimentoRua," Pavimento Rua Obrigatória?")+"\n";
		}
		
		if(validaRadioButton(form.indicadorLigacaoAgua,"Refere-se a Ligação de Água?") != ""){
			mensagem = mensagem + validaRadioButton(form.indicadorLigacaoAgua,"Refere-se a Ligação de Água?")+"\n";
		}
	
		if(validaRadioButton(form.indicadorCobrancaMaterial," Cobrança de Material?") != ""){
			mensagem = mensagem + validaRadioButton(form.indicadorCobrancaMaterial," Cobrança de Material?")+"\n";
		}
	
		if(validaRadioButton(form.indicadorParecerEncerramento," Parecer de Encerramento Obrigatório?") != ""){
			mensagem = mensagem + validaRadioButton(form.indicadorParecerEncerramento," Parecer de Encerramento Obrigatório?")+"\n";
		}
	
		if(validaRadioButton(form.indicadorGerarDebito,"Gera Débito?") != ""){
			mensagem = mensagem + validaRadioButton(form.indicadorGerarDebito,"Gera Débito?")+"\n";
		}
	
		if(validaRadioButton(form.indicadorGerarCredito,"Gera Crédito?") != ""){
			mensagem = mensagem + validaRadioButton(form.indicadorGerarCredito,"Gera Crédito?")+"\n";
		}
	
		if(validaRadioButton(form.indicadorCliente,"Cliente Obrigatório?") != ""){
			mensagem = mensagem + validaRadioButton(form.indicadorCliente,"Cliente Obrigatório?")+"\n";
		}
	
		if(validaRadioButton(form.indicadorVerificarDebito,"Existe Verificação de Débito?") != ""){
			mensagem = mensagem + validaRadioButton(form.indicadorVerificarDebito,"Cliente Obrigatório?")+"\n";
		}	
	
		if(validaRadioButton(form.indicadorMatriculaImovel,"Matrícula do Imóvel Obrigatória?") != ""){
			mensagem = mensagem + validaRadioButton(form.indicadorMatriculaImovel,"Matrícula do Imóvel Obrigatória?")+"\n";
		}

		if(validaRadioButton(form.indicadorUrgencia,"Indicador de Urgência Obrigatório?") != ""){
			mensagem = mensagem + validaRadioButton(form.indicadorUrgencia,"Indicador de Urgência Obrigatório?")+"\n";
		}		
	
		if(validaRadioButton(form.indicadorPermiteAlterarValor,"Alterar o valor do débito?") != ""){
			mensagem = mensagem + validaRadioButton(form.indicadorPermiteAlterarValor,"Alterar o valor do débito?")+"\n";
		}
		
		if(validaRadioButton(form.indicadorCobrarJuros,"Cobrar juros?") != ""){
			mensagem = mensagem + validaRadioButton(form.indicadorCobrarJuros,"Cobrar juros?")+"\n";
		}
		
		if(validaRadioButton(form.indicadorGeraOrdemServico,"Gera Ordem de Serviço?") != ""){
			mensagem = mensagem + validaRadioButton(form.indicadorGeraOrdemServico,"Gera Ordem de Serviço?")+"\n";
		}
		
		if(validaRadioButton(form.indicadorEncerramentoAutomatico,"Encerramento Automático?") != ""){
			mensagem = mensagem + validaRadioButton(form.indicadorEncerramentoAutomatico,"Encerramento Automático?")+"\n";
		}
		
		if(validaRadioButton(form.indicadorLojaVirtual, "Indicador Loja Virtual") != ""){
			mensagem = mensagem + validaRadioButton(form.indicadorLojaVirtual, "Indicador Loja Virtual")+"\n";	
		}
		
		if(validaRadioButton(form.indicadorInformarContaRA,"Informar conta no Registro de Atendimento") != ""){
			mensagem = mensagem + validaRadioButton(form.indicadorInformarContaRA,"Informar conta no Registro de Atendimento")+"\n";
		}
		
		if(mensagem != ""){
			alert(mensagem);
			return false;
		}
		else{
			return true;
		}
	}

    function validarForm(form){
		
		if(validateAdicionarSolicitacaoEspecificacaoActionForm(form) && validaTodosRadioButton()){
    		form.action="adicionarSolicitacaoEspecificacaoAction.do";
    		botaoDesabilita(form)
    		submeterFormPadrao(form);
		}
	}

	function desfazer(){
 
 		form = document.forms[0];
 		form.action='exibirAdicionarSolicitacaoEspecificacaoAction.do?limpaSessao=1';
 		form.submit();
	}

	function desabilitaCampos(){

		var form = document.forms[0];

		if(form.indicadorGeraOrdemServico[0].checked){
			form.idServicoOS.disabled = false;
			form.adicionarTipoServico.disabled = false;
		}
		else{
			form.idServicoOS.value = "";
			form.idServicoOS.disabled = true;
			form.adicionarTipoServico.disabled = true;
		}
	}

	function desabilitaCamposDebitoCredito(){

		var form = document.forms[0];
		//indicador igual a sim

		if(form.indicadorGerarDebito[0].checked){
			form.indicadorGerarCredito[0].disabled = true;
			form.indicadorGerarCredito[1].disabled = true;
			form.indicadorGerarCredito[1].checked = true;
		}
	}

	function desabilitaCamposDebitoCreditoOnClick(){

		var form = document.forms[0];
		
		//indicador igual a sim
		if(form.indicadorGerarDebito[0].checked){
			form.indicadorGerarCredito[0].disabled = true;
			form.indicadorGerarCredito[1].disabled = true;
			form.indicadorGerarCredito[1].checked = true;
		}
		else{
			form.indicadorGerarCredito[0].disabled = false;
			form.indicadorGerarCredito[1].disabled = false;
			form.indicadorGerarCredito[1].checked = false;
		}
	}

	
	function verificarCampoDesabilitado(campo,redirect,limparCampo){
 
 		if(!campo.disabled){
  			redirecionarSubmit(redirect);
  			limparCampo = ''; 
 		}
	}

	function limparPesquisaUnidadeTramitacao(){

		var form = document.forms[0];
		form.idUnidadeTramitacao.value= '';
		form.descricaoUnidadeTramitacao.value= '';
	}

	function limparPesquisaTipoServicoOS(){

		var form = document.forms[0];
		form.idServicoOS.value= '';
		form.descricaoServicoOS.value= '';
	}


	function limparPesquisaDebitoTipo(){

		var form = document.forms[0];
		form.idDebitoTipo.value= '';
		form.descricaoDebitoTipo.value= '';
	}


	function desabilitaCamposMatriculaImovelOnClick(){

		var form = document.forms[0];

		//indicador igual a sim
		if(form.indicadorMatriculaImovel[0].checked){
			form.idSituacaoImovel.disabled = false;
		}
		else{
			form.idSituacaoImovel.disabled = true;
			form.idSituacaoImovel.value = '';
		}
	}
	
	function statusEspecificacao(){

		var form = document.forms[0];
		
   		form.action="recuperarDadosInserirTipoSolicitacaoEspecificacaoAction.do?retornarTela=exibirInserirTipoSolicitacaoEspecificacaoAction.do";
   		submeterFormPadrao(form);
   	}
	

</script>

</head>

<logic:present name="consultaDados">
	<body leftmargin="5" topmargin="5"
		onload="javascript:resizePageSemLink(642, 700);">
</logic:present>

<logic:notPresent name="consultaDados">

	<logic:notPresent name="fecharPopup">
		<body leftmargin="5" topmargin="5"
			onload="javascript:resizePageSemLink(642, 700);setarFoco('${requestScope.nomeCampo}');desabilitaCampos();desabilitaCamposDebitoCredito();desabilitaCamposMatriculaImovelOnClick();">
	</logic:notPresent>

	<logic:present name="fecharPopup">
		<body leftmargin="0" topmargin="0"
			onload="chamarReload('${sessionScope.retornarTela}');window.close()">
	</logic:present>

</logic:notPresent>


<html:form action="/adicionarSolicitacaoEspecificacaoAction"
	name="AdicionarSolicitacaoEspecificacaoActionForm"
	type="gcom.gui.atendimentopublico.registroatendimento.AdicionarSolicitacaoEspecificacaoActionForm"
	method="post">
	<input type="hidden" name="consultaDados"
		value="${requestScope.consultaDados}">

	<table width="615" border="0" cellspacing="5" cellpadding="0">
		<tr>

			<td width="100%" valign="top" class="centercoltext">
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
					<td class="parabg">Adicionar Especificação da Solicitação</td>

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
					<p>Preencha os campos para inserir uma especificação da solicitação
					para o tipo de solicitação:</p>
					<p>&nbsp;</p>
					</td>
				</tr>
				<tr>
					<td width="33%"><strong>Descrição:<font color="#FF0000">*</font><strong></td>
					<td><logic:present name="consultaDados">
						<html:text property="descricaoSolicitacao" size="40"
							maxlength="50" tabindex="1" disabled="true" />
					</logic:present> <logic:notPresent name="consultaDados">
						<html:text property="descricaoSolicitacao" size="40"
							maxlength="50" tabindex="1" />
					</logic:notPresent></td>
				</tr>
				<tr>
					<td><strong> Prazo Previsão de Atendimento (em dias):</strong><strong><font
						color="#FF0000">*</font></strong></td>
					<td><logic:present name="consultaDados">
						<html:text property="prazoPrevisaoAtendimento" size="4"
							maxlength="4" tabindex="2" disabled="true" onkeypress="return isCampoNumerico(event);" />
					</logic:present> <logic:notPresent name="consultaDados">
						<html:text property="prazoPrevisaoAtendimento" size="4"
							maxlength="4" tabindex="2" onkeypress="return isCampoNumerico(event);" />
					</logic:notPresent></td>

				</tr>
				<tr>
					<td><strong> Pavimento Calçada Obrigatória?:<font color="#FF0000">*</font></strong></td>
					<td><logic:present name="consultaDados">
						<html:radio property="indicadorPavimentoCalcada" value="1"
							tabindex="3" disabled="true" />
						<strong>Sim</strong>&nbsp; <html:radio
							property="indicadorPavimentoCalcada" value="2" tabindex="4"
							disabled="true" />
						<strong>N&atilde;o</strong>
					</logic:present> <logic:notPresent name="consultaDados">
						<html:radio property="indicadorPavimentoCalcada" value="1"
							tabindex="3" />
						<strong>Sim</strong>&nbsp; <html:radio
							property="indicadorPavimentoCalcada" value="2" tabindex="4" />
						<strong>N&atilde;o</strong>
					</logic:notPresent></td>
				</tr>
				<tr>
					<td><strong> Pavimento Rua Obrigatória?:<font color="#FF0000"></font><font
						color="#FF0000">*</font></strong></td>
					<td><logic:present name="consultaDados">
						<html:radio property="indicadorPavimentoRua" value="1"
							tabindex="5" disabled="true" />
						<strong>Sim</strong>&nbsp; <html:radio
							property="indicadorPavimentoRua" value="2" tabindex="6"
							disabled="true" />
						<strong>N&atilde;o</strong>
					</logic:present> <logic:notPresent name="consultaDados">
						<html:radio property="indicadorPavimentoRua" value="1"
							tabindex="5" />
						<strong>Sim</strong>&nbsp; <html:radio
							property="indicadorPavimentoRua" value="2" tabindex="6" />
						<strong>N&atilde;o</strong>
					</logic:notPresent></td>
				</tr>
				<tr>
					<td><strong>Refere-se a Ligação de Água?:<font color="#FF0000"></font><font
						color="#FF0000">*</font></strong></td>
					<td><logic:present name="consultaDados">
						<html:radio property="indicadorLigacaoAgua" value="1" tabindex="5"
							disabled="true" />
						<strong>Sim</strong>&nbsp; <html:radio
							property="indicadorLigacaoAgua" value="2" tabindex="6"
							disabled="true" />
						<strong>N&atilde;o</strong>
					</logic:present> <logic:notPresent name="consultaDados">
						<html:radio property="indicadorLigacaoAgua" value="1" tabindex="5" />
						<strong>Sim</strong>&nbsp; <html:radio
							property="indicadorLigacaoAgua" value="2" tabindex="6" />
						<strong>N&atilde;o</strong>
					</logic:notPresent></td>
				</tr>
				<tr>
					<td><strong> Cobrança de Material?:<font color="#FF0000"></font><font
						color="#FF0000">*</font></strong></td>
					<td><logic:present name="consultaDados">
						<html:radio property="indicadorCobrancaMaterial" value="1"
							tabindex="7" disabled="true" />
						<strong>Sim</strong>&nbsp; <html:radio
							property="indicadorCobrancaMaterial" value="2" tabindex="8"
							disabled="true" />
						<strong>N&atilde;o</strong>
					</logic:present> <logic:notPresent name="consultaDados">
						<html:radio property="indicadorCobrancaMaterial" value="1"
							tabindex="7" />
						<strong>Sim</strong>&nbsp; <html:radio
							property="indicadorCobrancaMaterial" value="2" tabindex="8" />
						<strong>N&atilde;o</strong>
					</logic:notPresent></td>
				</tr>
				<tr>
					<td><strong>Parecer de Encerramento Obrigatório?:<font
						color="#FF0000"></font><font color="#FF0000">*</font></strong></td>
					<td><logic:present name="consultaDados">
						<html:radio property="indicadorParecerEncerramento" value="1"
							tabindex="9" disabled="true" />
						<strong>Sim</strong>&nbsp; <html:radio
							property="indicadorParecerEncerramento" value="2" tabindex="10"
							disabled="true" />
						<strong>N&atilde;o</strong>
					</logic:present> <logic:notPresent name="consultaDados">
						<html:radio property="indicadorParecerEncerramento" value="1"
							tabindex="9" />
						<strong>Sim</strong>&nbsp; <html:radio
							property="indicadorParecerEncerramento" value="2" tabindex="10" />
						<strong>N&atilde;o</strong>
					</logic:notPresent></td>
				</tr>
				<tr>
					<td><strong>Gera Débito?:<font color="#FF0000">*</font></strong></td>
					<td><logic:present name="consultaDados">
						<html:radio property="indicadorGerarDebito" value="1"
							tabindex="11" disabled="true" />
						<strong>Sim</strong>&nbsp; <html:radio
							property="indicadorGerarDebito" value="2" tabindex="12"
							disabled="true" />
						<strong>N&atilde;o</strong>
					</logic:present> <logic:notPresent name="consultaDados">
						<html:radio property="indicadorGerarDebito" value="1"
							tabindex="11" onclick="desabilitaCamposDebitoCreditoOnClick();" />
						<strong>Sim</strong>&nbsp; <html:radio
							property="indicadorGerarDebito" value="2" tabindex="12"
							onclick="desabilitaCamposDebitoCreditoOnClick();" />
						<strong>N&atilde;o</strong>
					</logic:notPresent></td>
				</tr>
				<tr>
					<td><strong>Gera Crédito?:<font color="#FF0000">*</font></strong></td>

					<td><logic:present name="consultaDados">
						<html:radio property="indicadorGerarCredito" value="1"
							tabindex="13" disabled="true" />
						<strong>Sim</strong>&nbsp;
					    <html:radio property="indicadorGerarCredito" value="2"
							tabindex="14" disabled="true" />
						<strong>N&atilde;o</strong>
					</logic:present> <logic:notPresent name="consultaDados">
						<html:radio property="indicadorGerarCredito" value="1"
							tabindex="13" />
						<strong>Sim</strong>&nbsp;
					    <html:radio property="indicadorGerarCredito" value="2"
							tabindex="14" />
						<strong>N&atilde;o</strong>
					</logic:notPresent></td>
				</tr>
				<tr>
					<td><strong>Cliente Obrigatório?:<font color="#FF0000">*</font></strong></td>

					<td><logic:present name="consultaDados">
						<html:radio property="indicadorCliente" value="1" tabindex="15"
							disabled="true" />
						<strong>Sim</strong>&nbsp; <html:radio property="indicadorCliente"
							value="2" tabindex="16" disabled="true" />
						<strong>N&atilde;o</strong>
					</logic:present> <logic:notPresent name="consultaDados">
						<html:radio property="indicadorCliente" value="1" tabindex="15" />
						<strong>Sim</strong>&nbsp; <html:radio property="indicadorCliente"
							value="2" tabindex="16" />
						<strong>N&atilde;o</strong>
					</logic:notPresent></td>
				</tr>
				
				<tr>
					<td><strong>Existe Verificação de Débito?:<font color="#FF0000">*</font></strong></td>

					<td><logic:present name="consultaDados">
						<html:radio property="indicadorVerificarDebito" value="1"
							tabindex="15" disabled="true" />
						<strong>Sim</strong>&nbsp; <html:radio
							property="indicadorVerificarDebito" value="2" tabindex="16"
							disabled="true" />
						<strong>N&atilde;o</strong>
					</logic:present> <logic:notPresent name="consultaDados">
						<html:radio property="indicadorVerificarDebito" value="1"
							tabindex="15" />
						<strong>Sim</strong>&nbsp; <html:radio
							property="indicadorVerificarDebito" value="2" tabindex="16" />
						<strong>N&atilde;o</strong>
					</logic:notPresent></td>
				</tr>
				<tr>
					<td><strong>Matrícula do Imóvel Obrigatória?:<font color="#FF0000">*</font></strong></td>

					<td><logic:present name="consultaDados">
						<html:radio property="indicadorMatriculaImovel" value="1"
							tabindex="17" disabled="true" />
						<strong>Sim</strong>&nbsp; <html:radio
							property="indicadorMatriculaImovel" value="2" tabindex="18"
							disabled="true" />
						<strong>N&atilde;o</strong>
					</logic:present> <logic:notPresent name="consultaDados">
						<html:radio property="indicadorMatriculaImovel" value="1"
							tabindex="17" onclick="desabilitaCamposMatriculaImovelOnClick();" />
						<strong>Sim</strong>&nbsp; <html:radio
							property="indicadorMatriculaImovel" value="2" tabindex="18"
							onclick="desabilitaCamposMatriculaImovelOnClick();" />
						<strong>N&atilde;o</strong>
					</logic:notPresent></td>
				</tr>
				
				<tr>
					<td><strong>Tipo com Urgência?:<font color="#FF0000">*</font></strong></td>

					<td><logic:present name="consultaDados">
						<html:radio property="indicadorUrgencia" value="1"
							tabindex="17" disabled="true" />
						<strong>Sim</strong>&nbsp; <html:radio
							property="indicadorUrgencia" value="2"
							disabled="true" />
						<strong>N&atilde;o</strong>
					</logic:present> <logic:notPresent name="consultaDados">
						<html:radio property="indicadorUrgencia" value="1"/>
						<strong>Sim</strong>&nbsp; <html:radio
							property="indicadorUrgencia" value="2"/>
						<strong>N&atilde;o</strong>
					</logic:notPresent></td>
				</tr>
				
				
				<tr>
					<td><strong>Situação do Imóvel?:</strong></td>
					<td><logic:present name="consultaDados">
						<html:select property="idSituacaoImovel" tabindex="19"
							disabled="true">
							<html:option value="">&nbsp;</html:option>
							<html:options collection="colecaoImovelSituacao"
								labelProperty="descricao" property="id" />
						</html:select>
					</logic:present> <logic:notPresent name="consultaDados">
						<html:select property="idSituacaoImovel" tabindex="19">
							<html:option value="">&nbsp;</html:option>
							<html:options collection="colecaoImovelSituacao"
								labelProperty="descricao" property="id" />
						</html:select>
					</logic:notPresent></td>
				<tr>
				<tr>
					<td><strong>Tipo de Débito:</strong></td>
					<td><html:text property="idDebitoTipo" size="4" maxlength="4"
						tabindex="20"
						onkeypress="validaEnterComMensagem(event, 'exibirAdicionarSolicitacaoEspecificacaoAction.do?objetoConsulta=1', 'idDebitoTipo', 'Tipo de Débito'); return isCampoNumerico(event);"
						onkeyup="document.forms[0].descricaoDebitoTipo.value = '';" /> <a
						href="javascript:javascript:verificarCampoDesabilitado(document.forms[0].idDebitoTipo,'recuperarDadosPesquisarAdicionarSolicitacaoEspecificacaoAction.do?caminhoRetornoTelaPesquisaTipoDebito=exibirAdicionarSolicitacaoEspecificacaoAction',document.forms[0].descricaoDebitoTipo);">
					<img src="<bean:message key='caminho.imagens'/>pesquisa.gif"
						width="23" height="21" title="Pesquisar" border="0"></a> <logic:present
						name="corDebitoTipo">

						<logic:equal name="corDebitoTipo" value="exception">
							<html:text property="descricaoDebitoTipo" size="40"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:equal>

						<logic:notEqual name="corDebitoTipo" value="exception">
							<html:text property="descricaoDebitoTipo" size="40"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEqual>

					</logic:present> <logic:notPresent name="corDebitoTipo">

						<logic:empty name="AdicionarSolicitacaoEspecificacaoActionForm"
							property="idDebitoTipo">
							<html:text property="descricaoDebitoTipo" value="" size="40"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:empty>
						<logic:notEmpty name="AdicionarSolicitacaoEspecificacaoActionForm"
							property="idDebitoTipo">
							<html:text property="descricaoDebitoTipo" size="40"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEmpty>

					</logic:notPresent> <a
						href="javascript:limparPesquisaDebitoTipo();"> <img
						src="<bean:message key='caminho.imagens'/>limparcampo.gif"
						title="Apagar" border="0"></a></td>

				</tr>

				<tr>
					<td><strong>Valor do Débito:</strong></td>
					<td><strong> <html:text property="valorDebito" size="14"
						maxlength="14" tabindex="21"
						onkeyup="formataValorMonetario(this, 14)"
						style="text-align:right;" /> </strong></td>
				</tr>

				<tr>
					<td><strong>Encerramento Automático?:<font color="#FF0000">*</font></strong></td>

					<td><html:radio property="indicadorEncerramentoAutomatico"
						value="1" tabindex="22" /> <strong>Sim</strong>&nbsp; <html:radio
						property="indicadorEncerramentoAutomatico" value="2" tabindex="23" />
					<strong>N&atilde;o</strong></td>
				</tr>
				
				<tr>
					<td><strong>Indicador de Loja Virtual:<font color="#FF0000">*</font></strong></td>
					
					<td>
						<html:radio property="indicadorLojaVirtual" value="1" tabindex="32" /><strong>Sim</strong>&nbsp;
						<html:radio property="indicadorLojaVirtual" value="2" tabindex="33" /><strong>N&atilde;o</strong>
					</td>
				</tr>
				
				<tr>
					<td colspan="2"><hr></td>
				</tr>				
				
				<tr>
					<td colspan="2"><strong>Incluir RA no Encerramento com Especificação abaixo:</strong></td>
				</tr>
				
				<tr>
					<td><strong>Tipo de solicitação:</strong></td>

					<td><html:select property="idTipoSolicitacao" onchange="javascript:statusEspecificacao();">
							<html:option value="">&nbsp;</html:option>
							<html:options collection="colecaoTipoSolicitacao"
								labelProperty="descricao" property="id" />
						</html:select>
					</td>
				</tr>
				
				<tr>
					<td><strong>Especificação:</strong></td>

					<td><html:select property="idEspecificacao">
							<html:option value="">&nbsp;</html:option>
							<html:options collection="colecaoEspecificacao"
								labelProperty="descricao" property="id" />
						</html:select>
					</td>
				</tr>
				
				<tr>
					<td colspan="2"><hr></td>
				</tr>			

				<tr>
					<td><strong>Alterar o valor do débito?:<font color="#FF0000">*</font></strong></td>

					<td><html:radio property="indicadorPermiteAlterarValor" value="1"
						tabindex="24" /> <strong>Sim</strong>&nbsp; <html:radio
						property="indicadorPermiteAlterarValor" value="2" tabindex="25" />
					<strong>N&atilde;o</strong></td>
				</tr>

				<tr>
					<td><strong>Cobrar juros?:<font color="#FF0000">*</font></strong></td>

					<td><html:radio property="indicadorCobrarJuros" value="1"
						tabindex="26" /> <strong>Sim</strong>&nbsp; <html:radio
						property="indicadorCobrarJuros" value="2" tabindex="27" /> <strong>N&atilde;o</strong>
					</td>
				</tr>


				<tr>
					<td><strong>Unidade Inicial de Tramitação:</strong></td>
					<td><logic:present name="consultaDados">
						<html:text maxlength="4" property="idUnidadeTramitacao" size="4"
							tabindex="28" disabled="true" onkeypress="return isCampoNumerico(event);"/>
						<img width="23" height="21" border="0"
							src="<bean:message key="caminho.imagens"/>pesquisa.gif"
							title="Pesquisar Unidade Inicial de Tramitação" />
						<html:text property="descricaoUnidadeTramitacao" size="40"
							maxlength="40" readonly="true"
							style="background-color:#EFEFEF; border:0; color: #000000" />
						<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
							border="0" title="Apagar" />
					</logic:present> <logic:notPresent name="consultaDados">
						<html:text maxlength="4" property="idUnidadeTramitacao" size="4"
							tabindex="20"
							onkeypress="javascript:validaEnterComMensagem(event, 'exibirAdicionarSolicitacaoEspecificacaoAction.do?objetoConsulta=1', 'idUnidadeTramitacao', 'Unidade Tramitação'); return isCampoNumerico(event);"
							onkeyup="document.forms[0].descricaoUnidadeTramitacao.value = '';" />
						<a
							href="javascript:verificarCampoDesabilitado(document.forms[0].idUnidadeTramitacao,'recuperarDadosPesquisarAdicionarSolicitacaoEspecificacaoAction.do?caminhoRetornoTelaPesquisaUnidadeOrganizacional=exibirAdicionarSolicitacaoEspecificacaoAction&tipoUnidade=unidadeAtual',document.forms[0].descricaoUnidadeTramitacao);">
						<img width="23" height="21" border="0"
							src="<bean:message key="caminho.imagens"/>pesquisa.gif"
							title="Pesquisar Unidade Inicial de Tramitação" /></a>
						<logic:present name="idUnidadeTramitacaoNaoEncontrado">
							<logic:equal name="idUnidadeTramitacaoNaoEncontrado"
								value="exception">
								<html:text property="descricaoUnidadeTramitacao" size="40"
									maxlength="40" readonly="true"
									style="background-color:#EFEFEF; border:0; color: #ff0000" />
							</logic:equal>
							<logic:notEqual name="idUnidadeTramitacaoNaoEncontrado"
								value="exception">
								<html:text property="descricaoUnidadeTramitacao" size="40"
									maxlength="40" readonly="true"
									style="background-color:#EFEFEF; border:0; color: #000000" />
							</logic:notEqual>
						</logic:present>
						<logic:notPresent name="idUnidadeTramitacaoNaoEncontrado">
							<logic:empty name="AdicionarSolicitacaoEspecificacaoActionForm"
								property="idUnidadeTramitacao">
								<html:text property="descricaoUnidadeTramitacao" value=""
									size="40" maxlength="40" readonly="true"
									style="background-color:#EFEFEF; border:0; color: #ff0000" />
							</logic:empty>
							<logic:notEmpty
								name="AdicionarSolicitacaoEspecificacaoActionForm"
								property="idUnidadeTramitacao">
								<html:text property="descricaoUnidadeTramitacao" size="40"
									maxlength="40" readonly="true"
									style="background-color:#EFEFEF; border:0; color: #000000" />
							</logic:notEmpty>
						</logic:notPresent>
						<a href="javascript:limparPesquisaUnidadeTramitacao();"> <img
							src="<bean:message key="caminho.imagens"/>limparcampo.gif"
							border="0" title="Apagar" /></a>
					</logic:notPresent></td>
				</tr>
				<tr>
					<td><strong>Gera Ordem de Serviço?:<font color="#FF0000">*</font></strong></td>

					<td><logic:present name="consultaDados">
						<html:radio property="indicadorGeraOrdemServico" value="1"
							tabindex="29" disabled="true" />
						<strong>Sim</strong>&nbsp;
					   <html:radio property="indicadorGeraOrdemServico" value="2"
							tabindex="30" disabled="true" />
						<strong>N&atilde;o</strong>
					</logic:present> <logic:notPresent name="consultaDados">
						<html:radio property="indicadorGeraOrdemServico" value="1"
							tabindex="29" onclick="desabilitaCampos();" />
						<strong>Sim</strong>&nbsp;
					   <html:radio property="indicadorGeraOrdemServico" value="2"
							tabindex="30" onclick="desabilitaCampos();" />
						<strong>N&atilde;o</strong>
					</logic:notPresent></td>
				</tr>

				<tr>
					<td><strong>Tipo do Serviço OS Obrigatória:</strong></td>
					<td><logic:present name="consultaDados">
						<html:text maxlength="4" property="idServicoOS" size="4"
							tabindex="31" disabled="true" onkeypress="return isCampoNumerico(event);"/>
						<img width="23" height="25" border="0"
							src="<bean:message key="caminho.imagens"/>pesquisa.gif"
							title="Pesquisar Serviço Tipo" />
						<html:text property="descricaoServicoOS" size="40" maxlength="40"
							readonly="true"
							style="background-color:#EFEFEF; border:0; color: #000000" />
						<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
							border="0" title="Apagar" />
					</logic:present> <logic:notPresent name="consultaDados">

						<html:text maxlength="4" property="idServicoOS" size="4"
							tabindex="25"
							onkeypress="javascript:validaEnterComMensagem(event, 'exibirAdicionarSolicitacaoEspecificacaoAction.do?objetoConsulta=1', 'idServicoOS', 'Tipo do Serviço OS Obrigatória'); return isCampoNumerico(event);"
							onkeyup="document.forms[0].descricaoServicoOS.value = '';" />
						<a
							href="javascript:verificarCampoDesabilitado(document.forms[0].idServicoOS,'recuperarDadosPesquisarAdicionarSolicitacaoEspecificacaoAction.do?caminhoRetornoTelaPesquisaTipoServico=exibirAdicionarSolicitacaoEspecificacaoAction&limpar=1',document.forms[0].descricaoServicoOS);">
						<img width="23" height="21" border="0"
							src="<bean:message key="caminho.imagens"/>pesquisa.gif"
							title="Pesquisar Tipo do Serviço OS Obrigatória" /></a>
						<logic:present name="idServicoOSNaoEncontrado">
							<logic:equal name="idServicoOSNaoEncontrado" value="exception">
								<html:text property="descricaoServicoOS" size="40"
									maxlength="40" readonly="true"
									style="background-color:#EFEFEF; border:0; color: #ff0000" />
							</logic:equal>
							<logic:notEqual name="idServicoOSNaoEncontrado" value="exception">
								<html:text property="descricaoServicoOS" size="40"
									maxlength="40" readonly="true"
									style="background-color:#EFEFEF; border:0; color: #000000" />
							</logic:notEqual>
						</logic:present>
						<logic:notPresent name="idServicoOSNaoEncontrado">
							<logic:empty name="AdicionarSolicitacaoEspecificacaoActionForm"
								property="idServicoOS">
								<html:text property="descricaoServicoOS" value="" size="40"
									maxlength="40" readonly="true"
									style="background-color:#EFEFEF; border:0; color: #ff0000" />
							</logic:empty>
							<logic:notEmpty
								name="AdicionarSolicitacaoEspecificacaoActionForm"
								property="idServicoOS">
								<html:text property="descricaoServicoOS" size="40"
									maxlength="40" readonly="true"
									style="background-color:#EFEFEF; border:0; color: #000000" />
							</logic:notEmpty>
						</logic:notPresent>
						<a href="javascript:limparPesquisaTipoServicoOS();"> <img
							src="<bean:message key="caminho.imagens"/>limparcampo.gif"
							border="0" title="Apagar" /></a>
					</logic:notPresent></td>
				</tr>

				<tr>
					<td colspan="2">
					<hr>
					</td>
				</tr>

				<tr>
					<td><strong>Tipo do Serviço OS Possíveis</strong></td>
					<td align="right"><logic:present name="consultaDados">
						<input type="button" name="adicionarTipoServico"
							class="bottonRightCol" value="Adicionar" disabled="true">
					</logic:present> <logic:notPresent name="consultaDados">
						<input type="button" name="adicionarTipoServico"
							class="bottonRightCol" value="Adicionar"
							onClick="javascript:redirecionarSubmit('exibirAdicionarSolicitacaoEspecificacaoTipoServicoAction.do?retornarTelaPopup=exibirAdicionarSolicitacaoEspecificacaoAction.do&limpaSessao=1');"
							tabindex="24">
					</logic:notPresent></td>
				</tr>

				<tr>
					<td colspan="2">
					<table width="100%" cellpadding="0" cellspacing="0">
						<tr bordercolor="#000000">
							<td bgcolor="#90c7fc" align="center" width="15%" height="20">
							<div align="center"><strong>Remover</strong></div>
							</td>
							<td bgcolor="#90c7fc" width="40%"><strong>Tipo de Serviço</strong></td>
							<td bgcolor="#90c7fc" width="40%"><strong>Ordem de Execução</strong></td>
						</tr>
						<logic:present name="colecaoEspecificacaoServicoTipo">
							<tr>
								<td colspan="3">

								<div style="width: 100%; height: 100%; overflow: auto;">
								<table width="100%" bgcolor="#99CCFF">

									<%int cont = 0;%>
									<logic:iterate name="colecaoEspecificacaoServicoTipo"
										id="especificacaoServicoTipo">
										<%cont = cont + 1;
							if (cont % 2 == 0) {%>
										<tr bgcolor="#cbe5fe">
											<%} else {

							%>
										<tr bgcolor="#FFFFFF">
											<%}%>
											<td width="15%">
											<div align="center"><font color="#333333"> <logic:present
												name="consultaDados">
												<img width="14" height="14" border="0"
													src="<bean:message key="caminho.imagens"/>Error.gif" />
											</logic:present> <logic:notPresent name="consultaDados">
												<img width="14" height="14" border="0"
													src="<bean:message key="caminho.imagens"/>Error.gif"
													onclick="javascript:if(confirm('Confirma remoção?')){redirecionarSubmit('removerSolicitacaoEspecificacaoAction.do?codigoSolicitacaoEspecificacao=<bean:write name="especificacaoServicoTipo" property="ultimaAlteracao.time"/>&tipoRetorno=inserir');}" />
											</logic:notPresent> </font></div>
											</td>
											<td width="40%"><bean:write name="especificacaoServicoTipo"
												property="servicoTipo.descricao" /></td>
											<td width="40%"><bean:write name="especificacaoServicoTipo"
												property="ordemExecucao" /></td>
										</tr>

									</logic:iterate>

								</table>
								</div>
								</td>
							</tr>
						</logic:present>
					</table>
					</td>
				</tr>
				<tr>
					<td colspan="2">
					<hr>
					</td>
				</tr>
				<tr>
					<td><strong>Informar conta no Registro de Atendimento:<font color="#FF0000">*</font></strong></td>

					<td><html:radio property="indicadorInformarContaRA" value="1"
						tabindex="26" /> <strong>Sim</strong>&nbsp; <html:radio
						property="indicadorInformarContaRA" value="2" tabindex="27" /> <strong>N&atilde;o</strong>
					</td>
				</tr>
				
				
				<tr>
					<td><strong>Informar Pagamento em Duplicidade no Registro de Atendimento:<font color="#FF0000">*</font></strong></td>

					<td><html:radio property="indicadorInformarPagamentoDP" value="1"
						tabindex="26" /> <strong>Sim</strong>&nbsp; <html:radio
						property="indicadorInformarPagamentoDP" value="2" tabindex="27" /> <strong>N&atilde;o</strong>
					</td>
				</tr>
				
				<tr>
					<td><strong>Alterar Vencimento Alternativo:<font color="#FF0000">*</font></strong></td>

					<td><html:radio property="indicadorAlterarVencimentoAlternativo" value="1"
						tabindex="26" /> <strong>Sim</strong>&nbsp; <html:radio
						property="indicadorAlterarVencimentoAlternativo" value="2" tabindex="27" /> <strong>N&atilde;o</strong>
					</td>
				</tr>
				
				<tr>
					<td colspan="2">
					<hr>
					</td>
				</tr>
				<tr>
					<td colspan="2">&nbsp;</td>
				</tr>
			</table>
			<table width="100%" border="0">
				<tr>
					<logic:present name="consultaDados">
						<td valign="top"><input name="button" type="button"
							class="bottonRightCol" value="Desfazer" disabled="true">&nbsp;<input
							type="button" name="ButtonCancelar" class="bottonRightCol"
							value="Fechar" disabled="true"></td>
						<td valign="top">
						<div align="right"><input name="botaoInserir" type="button"
							class="bottonRightCol" value="Inserir" disabled="true"
							tabindex="24"></div>
						</td>
					</logic:present>
					<logic:notPresent name="consultaDados">
						<td valign="top"><input name="button" type="button"
							class="bottonRightCol" value="Desfazer" onclick="desfazer();">&nbsp;<input
							type="button" name="ButtonCancelar" class="bottonRightCol"
							value="Fechar" onClick="javascript:window.close();"></td>
						<td valign="top">
						<div align="right"><input name="botaoInserir" type="button"
							class="bottonRightCol" value="Inserir"
							onclick="validarForm(document.forms[0]);" tabindex="24"></div>
						</td>
					</logic:notPresent>
				</tr>
				<tr>
					<td>&nbsp;</td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
	</body>
</html:form>
</html:html>
