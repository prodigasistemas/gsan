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


<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false" 
	formName="ResultadoConsultarProgramacaoAbastecimentoManutencaoActionForm" />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>CalendarioProgramacaoAbastecimento.js"></script>

<script language="JavaScript">



function validarForm(form){  

	if(testarCampoValorZero(document.ResultadoConsultarProgramacaoAbastecimentoManutencaoActionForm.mes, 'mes') && 
	testarCampoValorZero(document.ResultadoConsultarProgramacaoAbastecimentoManutencaoActionForm.ano, 'ano') &&
	testarCampoValorZero(document.ResultadoConsultarProgramacaoAbastecimentoManutencaoActionForm.municipio,'Município') &&
	testarCampoValorZero(document.ResultadoConsultarProgramacaoAbastecimentoManutencaoActionForm.bairro, 'Bairro') && 
	testarCampoValorZero(document.ResultadoConsultarProgramacaoAbastecimentoManutencaoActionForm.areaBairro, 'Área de Bairro')) {

		if(validateResultadoConsultarProgramacaoAbastecimentoManutencaoActionForm(form)){
    		submeterFormPadrao(form);
		}
	}
}

</script>

<style>

.abastecimentoManutencao{
 Vertical-align: super;
 font-size: xx-small;
 color: #000000; 
}

</style>


</head>

<body leftmargin="5" topmargin="5"
	onload="javascript:resizePageSemLink(590, 600);">

<html:form action="/consultarProgramacaoAbastecimentoManutencaoAction"
	name="ResultadoConsultarProgramacaoAbastecimentoManutencaoActionForm"
	type="gcom.gui.atendimentopublico.registroatendimento.ResultadoConsultarProgramacaoAbastecimentoManutencaoActionForm"
	method="post"
	onsubmit="return validateResultadoConsultarProgramacaoAbastecimentoManutencaoActionForm(this);">
	<table width="550" border="0" cellspacing="5" cellpadding="0">
		<tr>
			<td width="550" valign="top" class="centercoltext">
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
					<td class="parabg">Consultar Períodos de Abastecimento e Manutenção</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>
					<td align="center" colspan="3"><strong>Dados do Sistema de
					Abastecimento</strong></td>
				</tr>
				<tr>
					<td></td>
				</tr>

				<tr>
					<td width="160" align="left"><strong>Sistema de Abastecimento:</strong></td>
					<td colspan="2"><html:text property="codigoSistemaAbastecimento"
						readonly="true" style="background-color:#EFEFEF; border:0;"
						size="4" maxlength="4" /> <html:text
						property="sistemaAbastecimento" readonly="true"
						style="background-color:#EFEFEF; border:0;" size="30"
						maxlength="30" /></td>

				</tr>

				<tr>
					<td width="160" align="left"><strong>Zona de Abastecimento:</strong></td>
					<td colspan="2"><html:text property="codigoZonaAbastecimento"
						readonly="true" style="background-color:#EFEFEF; border:0;"
						size="4" maxlength="4" /> <html:text property="zonaAbastecimento"
						readonly="true" style="background-color:#EFEFEF; border:0;"
						size="30" maxlength="30" /></td>

				</tr>

				<tr>
					<td width="160" align="left"><strong>Município:</strong></td>
					<td colspan="2"><html:text property="codigoMunicipio"
						readonly="true" style="background-color:#EFEFEF; border:0;"
						size="4" maxlength="4" /> <html:text property="municipio"
						readonly="true" style="background-color:#EFEFEF; border:0;"
						size="30" maxlength="30" /></td>

				</tr>


				<tr>
					<td width="160" align="left"><strong>Bairro:</strong></td>
					<td colspan="2"><html:text property="codigoBairro" readonly="true"
						style="background-color:#EFEFEF; border:0;" size="4" maxlength="4" />
					<html:text property="bairro" readonly="true"
						style="background-color:#EFEFEF; border:0;" size="30"
						maxlength="30" /></td>

				</tr>

				<tr>
					<td width="160" align="left"><strong>Área de Bairro:</strong></td>
					<td colspan="2"><html:text property="codigoAreaBairro"
						readonly="true" style="background-color:#EFEFEF; border:0;"
						size="4" maxlength="4" /> <html:text property="areaBairro"
						readonly="true" style="background-color:#EFEFEF; border:0;"
						size="30" maxlength="30" /></td>

				</tr>


				<tr>
					<td colspan="3" border="0">
				<tr>
					<td colspan="3">
					<hr noshade>
					</td>
				</tr>

			</table>
			<table width="100%" border="0">
				<tr>
					<td colspan="3" height="24" align="center"><strong>Abastecimento e
					Manutenção</strong></td>

				</tr>
				<tr>
					<td width="25%">&nbsp;</td>

					<td align="center" width="240"><html:hidden property="mes" /> <html:hidden
						property="ano" /> <SCRIPT TYPE="text/javascript"
						LANGUAGE="JavaScript"><!--
						document.write(CalendarProgramacaoAbastecimento(${requestScope.mes},${requestScope.ano},'${requestScope.situacoes}'));
						
					//--></SCRIPT></td>
					<td>&nbsp;</td>
				</tr>

				<tr>
					<td colspan="3" align="right"><input name="Button" type="button"
						class="bottonRightCol" value="Voltar" align="left"
						onclick="window.location.href='<html:rewrite page="/exibirConsultarProgramacaoAbastecimentoManutencaoAction.do"/>'">
				</tr>

			</table>
			</td>
		</tr>
	</table>

</html:form>
</body>
</html:html>

