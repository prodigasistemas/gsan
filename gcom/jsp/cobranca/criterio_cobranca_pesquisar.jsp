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
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="PesquisarCriterioCobrancaActionForm" />

<script language="JavaScript">
<!-- Begin
	var bCancel = false; 
   function validatePesquisa(form) {                                                                   
      if (bCancel) 
    	return true; 
      else 
	    return validateDate(form); 
   } 

	function DateValidations () {
      this.aa = new Array("dataInicio", "Período de Início de Vigência do Critério Inicial inválido.", new Function ("varName", "this.datePattern='dd/MM/yyyy';  return this[varName];"));
      this.ab = new Array("dataFim", "Período de Início de Vigência do Critério Final inválido.", new Function ("varName", "this.datePattern='dd/MM/yyyy';  return this[varName];"));
    }
	
	function validarForm(form){
		if (validatePesquisarCriterioCobrancaActionForm(form)){
			if(validatePesquisa(form)){
		   		submeterFormPadrao(form);
			}else{
		   		return false;				
			}
		}else{
			return false;			
		}
	}
	
-->
</script>

<script language="JavaScript">
	function replicaDados(campoOrigem, campoDestino){
		campoDestino.value = campoOrigem.value;
	}

</script>

</head>

<!-- onload="resizePageSemLink(765,530);" -->
<body leftmargin="0" topmargin="0" onload="resizePageSemLink(755,505);">
<html:form action="/pesquisarCriterioCobrancaAction"
	name="PesquisarCriterioCobrancaActionForm"
	type="gcom.gui.cobranca.PesquisarCriterioCobrancaActionForm"
	method="post"
	onsubmit="return validarForm(this);">

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
					<td class="parabg">Pesquisar Critério de Cobrança</td>
					<td width="11"><img src="imagens/parahead_right.gif"
						editor="Webstyle4"
						moduleid="Album Photos (Project)\toptab_page2_parahead_right.xws"
						border="0" /></td>

				</tr>
			</table>
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>
					<td colspan="3">Preencha o campo para pesquisar um crit&eacute;rio
					de cobran&ccedil;a:</td>
					<td align="right"><a href="javascript: abrirPopupHelp('/gsan/help/help.jsp?mapIDHelpSet=cobrancaCriterioPesquisar', 500, 700);"><span style="font-weight: bold"><font color="#3165CE">Ajuda</font></span></a></td>
				</tr>
			</table>
				
			<table width="100%" border="0">
				<tr>
					<td width="40%" height="0"><strong>Descri&ccedil;&atilde;o do
					Crit&eacute;rio de Cobran&ccedil;a:<font color="#FF0000"></font></strong></td>
					<td colspan="2"><html:text property="descricaoCriterio" size="30"
						maxlength="30"/></td>
				</tr>
				<tr>
					<td height="0"><strong>Per&iacute;odo de In&iacute;cio de
					Vig&ecirc;ncia do Crit&eacute;rio:</strong></td>

					<td colspan="2"><strong> <html:text property="dataInicio" size="10"
						maxlength="10" onkeyup="mascaraData(this, event); replicaDados(document.forms[0].dataInicio, document.forms[0].dataFim);"/><a
						href="javascript:abrirCalendarioReplicando('PesquisarCriterioCobrancaActionForm', 'dataInicio', 'dataFim')"><img border="0"
						src="<bean:message key="caminho.imagens"/>calendario.gif"
						width="20" border="0" align="absmiddle" alt="Exibir Calendário" /></a> </strong><strong> a <html:text property="dataFim" size="10"
						maxlength="10" onkeyup="mascaraData(this, event);"/><a
						href="javascript:abrirCalendario('PesquisarCriterioCobrancaActionForm', 'dataFim')"><img border="0"
						src="<bean:message key="caminho.imagens"/>calendario.gif"
						width="20" border="0" align="absmiddle" alt="Exibir Calendário" /></a></strong>
					dd/mm/aaaa</td>
				</tr>
				<tr>
					<td height="0"><strong>N&uacute;mero de Anos para Determinar Conta
					Antiga</strong><strong>:<font color="#FF0000"></font></strong></td>

					<td colspan="2"><strong><span class="style1"> </span> <span
						class="style1"> <html:text property="numeroAnos" size="2"
						maxlength="2"/> </span></strong></td>
				</tr>
				<tr>
					<td height="0" colspan="3">
					</td>
				</tr>
				<tr>
					<td height="0" colspan="2"><strong>Emiss&atilde;o da
					A&ccedil;&atilde;o para Im&oacute;vel com Situa&ccedil;&atilde;o
					Especial de Cobran&ccedil;a:</strong></td>

					<td height="0"><html:radio property="opcaoImovelSitEspecial"
						value="1"/>
						<strong>Sim</strong>
						<html:radio property="opcaoImovelSitEspecial" value="2"/>
							<strong>N&atilde;o</strong>
							<html:radio property="opcaoImovelSitEspecial" value="3"/>
								<strong>Todos</strong></td>
				</tr>
				<tr>
					<td height="0" colspan="2"><strong>Emiss&atilde;o da
					A&ccedil;&atilde;o para Im&oacute;vel com Situa&ccedil;&atilde;o de
					Cobran&ccedil;a:</strong></td>
					<td width="33%" height="0"><html:radio
						property="opcaoImovelSitCobranca" value="1"/>
						<strong>Sim</strong>
						<html:radio property="opcaoImovelSitCobranca" value="2"/>
							<strong>N&atilde;o</strong>
							<html:radio property="opcaoImovelSitCobranca" value="3"/>
								<strong>Todos</strong></td>
				</tr>
				<tr>
					<td height="0" colspan="2"><strong>Considerar Contas em
					Revis&atilde;o:</strong></td>

					<td width="33%" height="0"><html:radio property="opcaoContaRevisao"
						value="1"/>
						<strong>Sim</strong>
						<html:radio property="opcaoContaRevisao" value="2"/>
							<strong>N&atilde;o</strong>
							<html:radio property="opcaoContaRevisao" value="3"/>
								<strong>Todos</strong></td>
				</tr>
				<tr>
					<td height="0" colspan="2"><strong>Emiss&atilde;o da
					A&ccedil;&atilde;o para Im&oacute;vel com D&eacute;bito s&oacute;
					da Conta do M&ecirc;s:</strong></td>

					<td width="33%" height="0"><html:radio property="opcaoImovelDebito"
						value="1"/>
						<strong>Sim</strong>
						<html:radio property="opcaoImovelDebito" value="2"/>
							<strong>N&atilde;o</strong>
							<html:radio property="opcaoImovelDebito" value="3"/>
								<strong>Todos</strong></td>
				<tr>
					<td height="0" colspan="2"><strong>Emiss&atilde;o da
					A&ccedil;&atilde;o para Inquilino Com D&eacute;bito s&oacute; da
					Conta do M&ecirc;s Independente do Valor da Conta:<font
						color="#FF0000"></font></strong></td>

					<td width="33%" height="0"><html:radio
						property="opcaoInqDebitoConta" value="1"/>
						<strong>Sim</strong>
						<html:radio property="opcaoInqDebitoConta"
							value="2"/>
							<strong>N&atilde;o</strong>
							<html:radio property="opcaoInqDebitoConta"
								value="3"/>
								<strong>Todos</strong></td>
				</tr>
				<tr>
					<td height="0" colspan="2"><strong>Emiss&atilde;o da
					A&ccedil;&atilde;o para Im&oacute;vel com D&eacute;bito s&oacute;
					de Contas Antigas:</strong></td>

					<td width="33%" height="0"><html:radio
						property="opcaoInqDebitoContaAntiga" value="1"/>
						<strong>Sim</strong>
						<html:radio property="opcaoInqDebitoContaAntiga" value="2"/>
							<strong>N&atilde;o</strong>
							<html:radio property="opcaoInqDebitoContaAntiga" value="3"/>
								<strong>Todos</strong></td>
				</tr>
				<tr>
					<td height="0" colspan="3">&nbsp;</td>
				</tr>

				<tr>
					<td height="24">
					<logic:present name="caminhoRetornoTelaPesquisaCriterioCobranca">
					<input type="button" class="bottonRightCol" value="Voltar"
						onclick="redirecionarSubmit('${caminhoRetornoTelaPesquisaCriterioCobranca}.do')">
					 </logic:present>
					 
					 <logic:notPresent name="popup">
					<input type="button" name="Submit223"
						class="bottonRightCol" value="Fechar"
						onClick="javascript:window.close();">
					 </logic:notPresent>
					
					<input type="button" name="Submit2222" class="bottonRightCol"
						value="Limpar" onclick="window.location.href='/gsan/exibirPesquisarCriterioCobrancaAction.do?menu=sim';"></td>
					<td colspan="2">
					
					<div align="right"><html:submit property="Button" styleClass="bottonRightCol" value="Pesquisar"/>
					</div>
					</td>
				</tr>
				<tr>
					<td height="24">&nbsp;</td>
					<td colspan="2">&nbsp;</td>

				</tr>
			</table>
			<p>&nbsp;</p>
			</td>

		</tr>
	</table>
</html:form>
</body>
</html:html>
