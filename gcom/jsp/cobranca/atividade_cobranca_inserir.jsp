<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"><head>
<%@ include file="/jsp/util/titulo.jsp"%> 
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">

<html:html>


<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js">

</script>
<html:javascript staticJavascript="false"  formName="InserirAtividadeCobrancaActionForm" />

<script language="JavaScript">
	function limpaProcesso(){
	 	var form = document.InserirAtividadeCobrancaActionForm;
	
	 	form.idProcessoAssociado.value = "";
	 	form.descricaoProcessoAssociado.value = "";
	 	form.idProcessoAssociado.focus();
	 
	 }
	function validaForm(){
		var form = document.InserirAtividadeCobrancaActionForm;
		if (form.quantidadeDiasExecucao.value != ''
			&& (form.quantidadeDiasExecucao.value > 30 || form.quantidadeDiasExecucao.value < 1)) {
			alert('Quantidade de dias está inválido.');	
		} else if (validateInserirAtividadeCobrancaActionForm(form)){
			if (validaTodosRadioButton()){
				submeterFormPadrao(form);
			}
		}
	}
	
	function validaRadioButton(nomeCampo,mensagem){
		var alerta = "";
		if(!nomeCampo[0].checked && !nomeCampo[1].checked){
			alerta =  "Informe " + mensagem +".";
		}
		return alerta;
	}
	function popupIndisponivel(){
		alert ("Esta opção está indisponível.");
	}
	
	function validaTodosRadioButton(){
		var form = document.InserirAtividadeCobrancaActionForm;
		var mensagem = "";
		
		
		if(validaRadioButton(form.opcaoCompoeCronograda,"Opção Compõe Cronograma") != ""){
				mensagem = mensagem + validaRadioButton(form.opcaoCompoeCronograda,"Opção Compõe Cronograma")+"\n";
		}
		
		if(validaRadioButton(form.opcaoAtividadeObrigatoria,"Opção Atividade Obrigatória") != ""){
			mensagem = mensagem + validaRadioButton(form.opcaoAtividadeObrigatoria,"Opção Atividade Obrigatória")+"\n";
		}
		
		if(validaRadioButton(form.opcaoPodeSerComandada,"Opção Pode ser Comandada") != ""){
			mensagem = mensagem + validaRadioButton(form.opcaoPodeSerComandada,"Opção Pode ser Comandada")+"\n";
		}
				
		if(validaRadioButton(form.opcaoPodeSerExecutada,"Opção Pode ser Executada") != ""){
			mensagem = mensagem + validaRadioButton(form.opcaoPodeSerExecutada,"Opção Pode ser Executada")+"\n";
		}
	 		 	
		if(mensagem != ""){
			alert(mensagem);
			return false;
		}else{
			return true;
		}
	}
	
	function recuperarDadosPopup(codigo, descricao, tipoConsulta) {

		var form = document.forms[0];
		if (tipoConsulta == 'processo') {
		     form.idProcessoAssociado.value = codigo;
		 	 form.descricaoProcessoAssociado.value = descricao;
	  	     form.descricaoProcessoAssociado.style.color = "#000000";
		}
	}
	
</script>
</head>
<logic:present name="identificadorPesquisa" scope="request">
	<body leftmargin="5" topmargin="5">
</logic:present>
<logic:notPresent name="identificadorPesquisa" scope="request">
	<body leftmargin="5" topmargin="5"
		onload="javascript:setarFoco('${requestScope.idProcessoAssociado}');">
</logic:notPresent>
<html:form action="/inserirAtividadeCobrancaAction.do"
	name="InserirAtividadeCobrancaActionForm"
	type="gcom.gui.cobranca.InserirAtividadeCobrancaActionForm"
	method="post" onsubmit="return validateInserirAtividadeCobrancaActionForm(this);">

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
					<td class="parabg">Inserir Atividade de Cobrança</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<table border="0" width="100%">
				<tr>
					<td>Para adicionar a atividade de cobrança, informe os
					dados abaixo:</td>
					<td align="right"><a href="javascript: abrirPopupHelp('/gsan/help/help.jsp?mapIDHelpSet=faturamentoContaMensagemInserir', 500, 700);"><span style="font-weight: bold"><font color="#3165CE"></font></span></a></td>																	        	
				</tr>
			</table>
			<table border="0" width="100%">	
				<tr>
					<td><strong>Descrição da Atividade de Cobrança:<font color="#ff0000">*</font></strong></td>
					<td align="right">
					<div align="left"><html:text property="descricaoAtividadeCobranca"
						size="40" maxlength="40" onkeyup="" tabindex="1"/></div>
					</td>
				</tr>
				<tr>
					<td><strong>Atividade Predecessora:</strong></td>
					<td align="right">
					<div align="left"><strong> <html:select property="idAtividadePredecessora"
						style="width: 150px;" onchange="" tabindex="2">
						<logic:present name="colecaoAtividadePredecessora">
							<html:option value="">&nbsp;</html:option>
							<html:options collection="colecaoAtividadePredecessora"
								labelProperty="descricaoCobrancaAtividade" property="id" />
						</logic:present>
					</html:select> </strong></div>
					</td>
				</tr>
				<tr>
					<td><strong>Ordem no Cronograma:<font color="#ff0000">*</font></strong></td>

					<td align="right">
					<div align="left"><html:text property="ordemCronograma"
						maxlength="3" size="3" onkeyup="" tabindex="3"/></div>
					</td>
				</tr>
				<tr>
					<td><strong>Processo Associado:<font color="#ff0000">*</font></strong></td>
					<td align="right">
					<div align="left"><html:text property="idProcessoAssociado" size="3"
						maxlength="3" onkeyup=""
						onkeydown="" tabindex="4"
						onkeypress="validaEnter(event, 'exibirInserirAtividadeCobrancaAction.do', 'idProcessoAssociado');" />
					
					<a href="javascript:abrirPopup('exibirPesquisarProcessoAction.do?indicadorUsoTodos=1', 250, 495);"> <img
						src="imagens/pesquisa.gif" style="" height="21" width="23" title="Pesquisar Processo" alt="Pesquisar Processo"
						border="0"></a>
						<logic:present name="processoInexistente"
						scope="request">
						<html:text property="descricaoProcessoAssociado" size="40" maxlength="40"
							readonly="true"
							style="background-color:#EFEFEF; border:0; color: #ff0000;" />
						</logic:present>		
					<!-- <a href="javascript:abrirPopup('exibirPesquisarProcessoAction.do?indicadorUsoTodos=1', 250, 495);">
						<img width="23" 
							height="21" 
							border="0"
							src="<bean:message key="caminho.imagens"/>pesquisa.gif"
							title="Pesquisar Processo" /></a> -->
					
					
					
					<logic:notPresent name="processoInexistente"
						scope="request">
						<html:text property="descricaoProcessoAssociado" size="40" maxlength="40"
							readonly="true"
							style="background-color:#EFEFEF; border:0; color: #000000;" />
					</logic:notPresent> 
					
					<a href="javascript:limpaProcesso();"><img src="imagens/limparcampo.gif" border="0" height="21"
						width="23" title="Apagar">
					</a></div>
					</td>
				</tr>
				<tr>
					<td colspan="2"><hr></td>
				</tr>
				<tr> 
                  <td><strong>Compõe Cronograma:<font color="#FF0000">*</font></strong></td>
                  <td><html:radio property="opcaoCompoeCronograda" value="1" tabindex="5"/> 
                      <strong>Sim </strong><html:radio property="opcaoCompoeCronograda" value="2" tabindex="6"/> 
                      <strong>N&atilde;o</strong></td>
                 </tr>
				<tr> 
                  <td><strong>Atividade Obrigatória:<font color="#FF0000">*</font></strong></td>
                  <td><html:radio property="opcaoAtividadeObrigatoria" value="1" tabindex="7"/> 
                      <strong>Sim </strong><html:radio property="opcaoAtividadeObrigatoria" value="2" tabindex="8"/> 
                      <strong>N&atilde;o</strong></td>
                 </tr>
				<tr> 
                  <td><strong>Pode ser Comandada:<font color="#FF0000">*</font></strong></td>
                  <td><html:radio property="opcaoPodeSerComandada" value="1" tabindex="9"/> 
                      <strong>Sim </strong><html:radio property="opcaoPodeSerComandada" value="2" tabindex="10"/> 
                      <strong>N&atilde;o</strong></td>
                 </tr>
				<tr> 
                  <td><strong>Pode ser Executada:<font color="#FF0000">*</font></strong></td>
                  <td><html:radio property="opcaoPodeSerExecutada" value="1" tabindex="11"/> 
                      <strong>Sim </strong><html:radio property="opcaoPodeSerExecutada" value="2" tabindex="12"/> 
                      <strong>N&atilde;o</strong></td>
                 </tr>
				<tr>
					<td colspan="2"><hr></td>
				</tr>
				
				<tr>
					<td><strong>Quantidade de dias para execução:</strong></td>
						<td align="right">
						<div align="left"><html:text property="quantidadeDiasExecucao"
							maxlength="3" size="3" onkeyup="" tabindex="3" onkeypress="return isCampoNumerico(event);"/></div>
					</td>
				</tr>
				
				<tr>
					<td><strong>Ação associada à atividade:</strong></td>
						<td align="right">
						<div align="left"><strong> <html:select property="cobrancaAcao"
							style="width: 150px;" onchange="" tabindex="2">
							<logic:present name="colecaoCobrancaAcao">
								<html:option value="">&nbsp;</html:option>
								<html:options collection="colecaoCobrancaAcao"
									labelProperty="descricaoCobrancaAcao" property="id" />
							</logic:present>
						</html:select> </strong></div>
					</td>
				</tr>
				<tr>
					<td colspan="2"><hr></td>
				</tr>

				<tr>
					<td><strong> <font color="#ff0000"></font></strong></td>
					<td align="right">
					<div align="left"><strong><font color="#ff0000">*</font></strong>
					Campos obrigatórios</div>
					</td>
				</tr>
				<tr>
					<td><strong> <font color="#ff0000"> <input name="Submit22"
						class="bottonRightCol" value="Limpar" type="button" tabindex="14"
						onclick="window.location.href='/gsan/exibirInserirAtividadeCobrancaAction.do?menu=sim';">

					<input name="Submit23" class="bottonRightCol" value="Cancelar"
						type="button" tabindex="15"
						onclick="window.location.href='/gsan/telaPrincipal.do'"> </font></strong></td>
					<td align="right"><input name="botao" class="bottonRightCol"
						value="Inserir" onclick="validaForm();" type="button" tabindex="13"></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<!-- Fim do Corpo - Tiago Moreno --></td>
		</tr>
	</table>

	<%@ include file="/jsp/util/rodape.jsp"%>

</html:form>
</body>
</html:html>
