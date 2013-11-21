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
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>
	
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="PesquisarAvisoBancarioActionForm"/>

<script language="JavaScript">

 var bCancel = false;

    function validatePesquisarAvisoBancarioActionForm(form) {
        if (bCancel)
          return true;
        else
         return validateCaracterEspecial(form) 
         	&& testarCampoValorZero(document.forms[0].idArrecadador, 'Arrecadador')
	        && validateLong(form)
	        && validateDecimalZeroPositivo(form)
	        && validateDate(form)
	        && validateMesAno(form);
   }

	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
		var form = document.PesquisarAvisoBancarioActionForm;
		if (tipoConsulta == 'arrecadador') {
			form.arrecadadorNome.value = codigoRegistro; 
		}
		
	}

	function replicaDados(campoOrigem, campoDestino){
		campoDestino.value = campoOrigem.value;
		//campoOrigem.focus();
	}
	
	function limparContaBancaria() {
		var form = document.PesquisarAvisoBancarioActionForm;
		form.idConta.value = "";
		form.idBancoConta.value = "";
		form.idAgenciaConta.value = "";
		form.numeroConta.value = "";
	}

	
	function limparArrecadador() {
		var form = document.PesquisarAvisoBancarioActionForm;
		form.idArrecadador.value = "";
		form.arrecadadorNome.value = "";
	}
	
	function limparMovimento() {
	var form = document.PesquisarAvisoBancarioActionForm;
		form.idMovimento.value = "";
		form.codigoBanco.value = "";
		form.codigoRemessa.value = "";
		form.identificacaoServico.value = "";
		form.numeroSequencial.value = "";
   }
   
   function limparForm(){
    	var form = document.forms[0];
    	form.idArrecadador.value = "";
		form.arrecadadorNome.value = "";
		form.dataLancamentoInicio.value = "";
		form.dataLancamentoFim.value = "";
		form.tipoAviso.value = "";
		form.idConta.value = "";
		form.idBancoConta.value = "";
		form.idAgenciaConta.value = "";
		form.numeroConta.value = "";
		form.idMovimento.value = "";
		form.codigoBanco.value = "";
		form.codigoRemessa.value = "";
		form.identificacaoServico.value = "";
		form.numeroSequencial.value = "";
		form.periodoArrecadacaoInicio.value = "";
		form.periodoArrecadacaoFim.value = "";
		form.dataPrevisaoCreditoDebitoInicio.value = "";
		form.dataPrevisaoCreditoDebitoFim.value = "";
		/*form.intervaloValorPrevistoInicio.value = "";
		form.intervaloValorPrevistoFim.value = "";*/
		form.dataRealizacaoCreditoDebitoInicio.value = "";
		form.dataRealizacaoCreditoDebitoFim.value = "";
		form.intervaloValorRealizadoInicio.value = "";
		form.intervaloValorRealizadoFim.value = "";
		form.idArrecadador.focus();
    }
    
    function validarForm(form){
    /*var intervaloValorPrevistoInicioSemPonto = obterNumerosSemVirgulaEPonto(form.intervaloValorPrevistoInicio.value);
    var intervaloValorPrevistoFimSemPonto = obterNumerosSemVirgulaEPonto(form.intervaloValorPrevistoFim.value);*/
    var intervaloValorRealizadoInicioSemPonto = obterNumerosSemVirgulaEPonto(form.intervaloValorRealizadoInicio.value);
    var intervaloValorRealizadoFimSemPonto = obterNumerosSemVirgulaEPonto(form.intervaloValorRealizadoFim.value);
	if (validatePesquisarAvisoBancarioActionForm(form))
	{
		if ((form.dataLancamentoInicio.value != '' && form.dataLancamentoFim.value != '') && (comparaData(form.dataLancamentoInicio.value, ">", form.dataLancamentoFim.value)))
		{
			alert ("Data Final do Período de Lançamento do Aviso é anterior a Data Inicial do Período de Lançamento do Aviso.");
		}
		else if ((form.periodoArrecadacaoInicio.value != '' && form.periodoArrecadacaoFim.value != '') && (comparaMesAno(form.periodoArrecadacaoInicio.value, ">", form.periodoArrecadacaoFim.value)))
		{
			alert ("Mês/Ano Final do Período de Referência da Arrecadação é anterior ao Mês/Ano Inicial do Período de Referência da Arrecadação.");
		}
		else if ((form.dataPrevisaoCreditoDebitoInicio.value != '' && form.dataPrevisaoCreditoDebitoFim.value != '') && (comparaData(form.dataPrevisaoCreditoDebitoInicio.value, ">", form.dataPrevisaoCreditoDebitoFim.value)))
		{
			alert ("Data Final do Período de Previsão do Crédito/Débito é anterior a Data Inicial do Período de Previsão do Crédito/Débito.");
		}
		/*else if ((form.intervaloValorPrevistoInicio.value != '' && form.intervaloValorPrevistoFim.value != '') && (intervaloValorPrevistoInicioSemPonto > intervaloValorPrevistoFimSemPonto))
		{
			alert ("Valor Previsto Final do Intervalo é anterior ao Valor Previsto Inicial do Intervalo.");
		}*/
		else if ((form.dataRealizacaoCreditoDebitoInicio.value != '' && form.dataRealizacaoCreditoDebitoFim.value != '') && (comparaData(form.dataRealizacaoCreditoDebitoInicio.value, ">", form.dataRealizacaoCreditoDebitoFim.value)))
		{
			alert ("Data Final do Período de Realização do Crédito/Débito é anterior a Data Inicial do Período de Realização do Crédito/Débito.");
		}
		else if ((form.intervaloValorRealizadoInicio.value != '' && form.intervaloValorRealizadoFim.value != '') && (intervaloValorRealizadoInicioSemPonto >  intervaloValorRealizadoFimSemPonto))
		{
			alert ("Valor Realizado Final do Intervalo é anterior ao Valor Realizado Inicial do Intervalo.");
		}
		else
		{
			redirecionarSubmit('/gsan/pesquisarAvisoBancarioAction.do');
		}
	}
}
    

</script>

</head>

<body leftmargin="0" topmargin="0" onload="resizePageSemLink(765,545); setarFoco('${requestScope.nomeCampo}');">
<html:form action="/pesquisarAvisoBancarioAction"
	name="PesquisarAvisoBancarioActionForm"
	type="gcom.gui.arrecadacao.PesquisarAvisoBancarioActionForm"
	method="post"
	onsubmit="return validatePesquisarAvisoBancarioActionForm(this)">

	<table width="717" border="0" cellspacing="5" cellpadding="0">
		<tr>
			<td width="707" valign="top" class="centercoltext">
			<table height="100%">

				<tr>
					<td></td>
				</tr>
			</table>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img src="imagens/parahead_left.gif"
						editor="Webstyle4"
						moduleid="Album Photos (Project)\toptab_page2_parahead_left.xws"
						border="0" /></td>
					<td class="parabg">Pesquisar Aviso Banc&aacute;rio</td>
					<td width="11"><img src="imagens/parahead_right.gif"
						editor="Webstyle4"
						moduleid="Album Photos (Project)\toptab_page2_parahead_right.xws"
						border="0" /></td>

				</tr>
			</table>
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>
					<td colspan="4">Preencha os campos para pesquisar um aviso
					banc&aacute;rio:</td>
				</tr>
				<tr>
					<td width="36%"><strong>Arrecadador:</strong></td>

					<td width="61%" colspan="3">
					<input type="text" maxlength="3" name="idArrecadador" size="3" value="<bean:write name="PesquisarAvisoBancarioActionForm" property="idArrecadador" />"
						onkeypress="validaEnterComMensagem(event, 'exibirPesquisarAvisoBancarioAction.do?objetoConsulta=1', 'idArrecadador', 'Arrecadador');" />
						<a
							href="javascript:abrirPopup('exibirPesquisarArrecadadorAction.do?caminhoRetornoTelaPesquisaArrecadador=exibirPesquisarAvisoBancarioAction');">
						<img width="23" height="21" border="0"
							src="<bean:message key="caminho.imagens"/>pesquisa.gif"
							title="Pesquisar Arrecadador" /></a>
						<logic:present name="idArrecadadorNaoEncontrado">
							<logic:equal name="idArrecadadorNaoEncontrado" value="exception">
								<html:text property="arrecadadorNome" size="40"
									maxlength="30" readonly="true"
									style="background-color:#EFEFEF; border:0; color: #ff0000"/>
							</logic:equal>
							<logic:notEqual name="idArrecadadorNaoEncontrado"
								value="exception">
								<html:text property="arrecadadorNome" size="40"
									maxlength="30" readonly="true"
									style="background-color:#EFEFEF; border:0; color: #000000"/>							</logic:notEqual>
						</logic:present>
						<logic:notPresent name="idArrecadadorNaoEncontrado">
							<html:text property="arrecadadorNome" size="40"
									maxlength="30" readonly="true"
									style="background-color:#EFEFEF; border:0; color: #000000"/>
						</logic:notPresent>
						<a href="javascript:limparArrecadador();"> <img
						src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Limpar Arrecadador" /></a>
						</td>

				</tr>
				<tr>
					<td height="0"><strong>Per&iacute;odo de Lan&ccedil;amento do
					Aviso:</strong></td>

					<td colspan="3"><strong> <html:text property="dataLancamentoInicio"
						size="10" maxlength="10" onkeyup="mascaraData(this, event); replicaDados(document.forms[0].dataLancamentoInicio, document.forms[0].dataLancamentoFim);" onfocus="replicaDados(document.forms[0].dataLancamentoInicio, document.forms[0].dataLancamentoFim);" /> <a
						href="javascript:abrirCalendarioReplicando('PesquisarAvisoBancarioActionForm', 'dataLancamentoInicio','dataLancamentoFim');">
					<img border="0"
						src="<bean:message key="caminho.imagens"/>calendario.gif"
						width="20" border="0" align="absmiddle" alt="Exibir Calendário" /></a>
					a</strong> <html:text property="dataLancamentoFim" size="10"
						maxlength="10" onkeyup="mascaraData(this, event);" /> <a
						href="javascript:abrirCalendario('PesquisarAvisoBancarioActionForm', 'dataLancamentoFim')">
					<img border="0"
						src="<bean:message key="caminho.imagens"/>calendario.gif"
						width="20" border="0" align="absmiddle" alt="Exibir Calendário" /></a>
					dd/mm/aaaa</td>
				</tr>
				<tr>
					<td height="0"><strong>Tipo do Aviso:</strong></td>

					<td colspan="3"><span class="style1"> 
						<html:radio	property="tipoAviso" value="1" /> <strong>Crédito</strong> 
						<html:radio	property="tipoAviso" value="2" /><strong> Débito</strong>
						<html:radio	property="tipoAviso" value="" /><strong> Todos</strong></span>
					</td>
				</tr>
				<tr>
					<td height="0"><strong>Conta Banc&aacute;ria:</strong></td>

					<td colspan="3">
					<html:hidden property="idConta" />
					<html:text property="idBancoConta" size="3"
						maxlength="3"
						style="background-color:#EFEFEF; border:0; font-color: #000000"
						readonly="true" /> <html:text property="idAgenciaConta" size="9"
						maxlength="9"
						style="background-color:#EFEFEF; border:0; font-color: #000000"
						readonly="true" />
					<html:text property="numeroConta" size="20"
						maxlength="20"
						style="background-color:#EFEFEF; border:0; font-color: #000000"
						readonly="true" />
						<a href="javascript:redirecionarSubmit('contaBancariaPesquisarAction.do?caminhoRetornoTelaPesquisaContaBancaria=exibirPesquisarAvisoBancarioAction');">
						<img width="23" height="21" border="0"
							src="<bean:message key="caminho.imagens"/>pesquisa.gif"
							title="Pesquisar Conta Bancária" /></a>
						<a href="javascript:limparContaBancaria();">
							<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"	border="0" title="Limpar Conta Bancária" /></a>
					</td>
				</tr>
				<tr>
					<td height="0"><strong>Movimento:</strong></td>
					<td colspan="3">
					<html:hidden property="idMovimento" />
					<html:text property="codigoBanco" size="3"
						maxlength="3" disabled="true"
						style="background-color:#EFEFEF; border:0; color: #000000" /> <html:text
						property="codigoRemessa" size="3" maxlength="3"
						disabled="true"
						style="background-color:#EFEFEF; border:0; color: #000000" /> <html:text
						property="identificacaoServico" size="30" maxlength="30"
						disabled="true"
						style="background-color:#EFEFEF; border:0; color: #000000" /> <html:text
						property="numeroSequencial" size="9" maxlength="9"
						disabled="true"
						style="background-color:#EFEFEF; border:0; color: #000000" /> 
						<a href="javascript:redirecionarSubmit('exibirPesquisarMovimentoArrecadadorAction.do?caminhoRetornoTelaPesquisaMovimentoArrecadador=exibirPesquisarAvisoBancarioAction');">
							<img width="23" height="21" border="0" src="<bean:message key="caminho.imagens"/>pesquisa.gif" title="Pesquisar Movimento" /></a>
						<a href="javascript:limparMovimento();">
							<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"	border="0" title="Limpar Movimento" /></a>
					</td>
				</tr>
				<tr>
					<td height="0" colspan="4">
					<hr>
					</td>
				</tr>
				<tr>
					<td height="0"><strong>Per&iacute;odo de Refer&ecirc;ncia da
					Arrecada&ccedil;&atilde;o:</strong></td>
					<td colspan="3"><strong> <html:text maxlength="7"
						property="periodoArrecadacaoInicio" size="7"
						 onkeyup="mascaraAnoMes(this, event); replicaDados(document.forms[0].periodoArrecadacaoInicio, document.forms[0].periodoArrecadacaoFim);" onfocus="replicaDados(document.forms[0].periodoArrecadacaoInicio, document.forms[0].periodoArrecadacaoFim);" /> <strong> a</strong> <html:text
						maxlength="7" property="periodoArrecadacaoFim" size="7"
						onkeyup="mascaraAnoMes(this, event);" /> </strong> mm/aaaa</td>
				</tr>
				<tr>
					<td height="0" colspan="4">
					<hr>
					</td>
				</tr>
				<tr>
					<td height="0"><strong>Per&iacute;odo de Previs&atilde;o do
					Cr&eacute;dito/D&eacute;bito:</strong></td>

					<td colspan="3"><strong> <html:text
						property="dataPrevisaoCreditoDebitoInicio" size="10"
						maxlength="10" onkeyup="mascaraData(this, event); replicaDados(document.forms[0].dataPrevisaoCreditoDebitoInicio, document.forms[0].dataPrevisaoCreditoDebitoFim);" onfocus="replicaDados(document.forms[0].dataPrevisaoCreditoDebitoInicio, document.forms[0].dataPrevisaoCreditoDebitoFim);"/> <a
						href="javascript:abrirCalendarioReplicando('PesquisarAvisoBancarioActionForm', 'dataPrevisaoCreditoDebitoInicio', 'dataPrevisaoCreditoDebitoFim');">
					<img border="0"
						src="<bean:message key="caminho.imagens"/>calendario.gif"
						width="20" border="0" align="absmiddle" alt="Exibir Calendário" /></a>
					a</strong> <html:text property="dataPrevisaoCreditoDebitoFim"
						size="10" maxlength="10" onkeyup="mascaraData(this, event);"/> <a
						href="javascript:abrirCalendario('PesquisarAvisoBancarioActionForm', 'dataPrevisaoCreditoDebitoFim')">
					<img border="0"
						src="<bean:message key="caminho.imagens"/>calendario.gif"
						width="20" border="0" align="absmiddle" alt="Exibir Calendário" /></a>
					dd/mm/aaaa</td>
				</tr>
				<%-- <tr>
					<td height="0"><strong>Intervalo de Valor Previsto:</strong></td>

					<td colspan="3">
						<html:text	property="intervaloValorPrevistoInicio" size="13" maxlength="13" onkeyup="formataValorMonetario(this, 11); replicaDados(document.forms[0].intervaloValorPrevistoInicio, document.forms[0].intervaloValorPrevistoFim);" onfocus="replicaDados(document.forms[0].intervaloValorPrevistoInicio, document.forms[0].intervaloValorPrevistoFim);" style="text-align: right;"/>
						<strong> a</strong> 
						<html:text property="intervaloValorPrevistoFim"	size="13" maxlength="13" onkeyup="formataValorMonetario(this, 11)" style="text-align: right;"/>
					</td>
				</tr>--%>
				<tr>
					<td height="0" colspan="4">
					<hr>
					</td>
				</tr>

				<tr>
					<td height="0"><strong>Per&iacute;odo de Realiza&ccedil;&atilde;o
					do Cr&eacute;dito/D&eacute;bito:</strong></td>
					<td colspan="3"><strong> <html:text
						property="dataRealizacaoCreditoDebitoInicio" size="10"
						maxlength="10" onkeyup="mascaraData(this, event); replicaDados(document.forms[0].dataRealizacaoCreditoDebitoInicio, document.forms[0].dataRealizacaoCreditoDebitoFim);" onfocus="replicaDados(document.forms[0].dataRealizacaoCreditoDebitoInicio, document.forms[0].dataRealizacaoCreditoDebitoFim);"/> <a
						href="javascript:abrirCalendarioReplicando('PesquisarAvisoBancarioActionForm', 'dataRealizacaoCreditoDebitoInicio', 'dataRealizacaoCreditoDebitoFim');">
					<img border="0"
						src="<bean:message key="caminho.imagens"/>calendario.gif"
						width="20" border="0" align="absmiddle" alt="Exibir Calendário" /></a>
					a</strong> <html:text property="dataRealizacaoCreditoDebitoFim"
						size="10" maxlength="10" onkeyup="mascaraData(this, event);" /> <a
						href="javascript:abrirCalendario('PesquisarAvisoBancarioActionForm', 'dataRealizacaoCreditoDebitoFim')">
					<img border="0"
						src="<bean:message key="caminho.imagens"/>calendario.gif"
						width="20" border="0" align="absmiddle" alt="Exibir Calendário" /></a>
					dd/mm/aaaa</td>

				</tr>
				<tr>
					<td height="0"><strong>Intervalo de Valor Realizado:</strong></td>
					<td colspan="3">
						<html:text	property="intervaloValorRealizadoInicio" size="13" maxlength="13" onkeyup="formataValorMonetario(this, 11); replicaDados(document.forms[0].intervaloValorRealizadoInicio, document.forms[0].intervaloValorRealizadoFim);" onfocus="replicaDados(document.forms[0].intervaloValorRealizadoInicio, document.forms[0].intervaloValorRealizadoFim);" style="text-align: right;"/>
						<strong> a</strong> 
						<html:text property="intervaloValorRealizadoFim" size="13" maxlength="13" onkeyup="formataValorMonetario(this, 11)" style="text-align: right;"/>
					</td>
				</tr>

				<tr>
					<td>&nbsp;</td>
					<td colspan="3">&nbsp;</td>
				</tr>
				<tr>
					<td colspan="3">
						<INPUT TYPE="button" class="bottonRightCol" value="Limpar" onclick="document.forms[0].reset();limparForm();"/>
          					&nbsp;&nbsp;
			          	<logic:present name="caminhoRetornoTelaPesquisaAvisoBancario">
          					<INPUT TYPE="button" class="bottonRightCol" value="Voltar" onclick="redirecionarSubmit('${caminhoRetornoTelaPesquisaAvisoBancario}.do')"/>
			          	</logic:present>
					</td>
					<td align="right">
						<input type="button" name="Button" class="bottonRightCol" value="Pesquisar" onClick="javascript:validarForm(document.forms[0]);" />
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
