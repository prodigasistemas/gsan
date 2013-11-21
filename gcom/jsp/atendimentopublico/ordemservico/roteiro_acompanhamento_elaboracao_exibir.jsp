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
	formName="ExibirCalendarioElaboracaoAcompanhamentoRoteiroActionForm" />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>CalendarioElaboracaoAcompanhamento.js"></script>

<script language="JavaScript">



function validarForm(form){  

	if(testarCampoValorZero(document.ExibirCalendarioElaboracaoAcompanhamentoRoteiroActionForm.mes, 'mes') && 
	testarCampoValorZero(document.ExibirCalendarioElaboracaoAcompanhamentoRoteiroActionForm.ano, 'ano') &&
	testarCampoValorZero(document.ExibirCalendarioElaboracaoAcompanhamentoRoteiroActionForm.municipio,'Município') &&
	testarCampoValorZero(document.ExibirCalendarioElaboracaoAcompanhamentoRoteiroActionForm.bairro, 'Bairro') && 
	testarCampoValorZero(document.ExibirCalendarioElaboracaoAcompanhamentoRoteiroActionForm.areaBairro, 'Área de Bairro')) {

		if(validateExibirCalendarioElaboracaoAcompanhamentoRoteiroActionForm(form)){
    		submeterFormPadrao(form);
		}
	}
}

</script>

<style>

.elaboracaoAcompanhamento{
 Vertical-align: super;
 font-size: xx-small;
 color: #000000; 
}

</style>


</head>

<body leftmargin="5" topmargin="5"
	onload="javascript:setarFoco('${requestScope.nomeCampo}');">

<html:form
	action="/exibirCalendarioElaboracaoAcompanhamentoRoteiroAction"
	name="ExibirCalendarioElaboracaoAcompanhamentoRoteiroActionForm"
	type="gcom.gui.atendimentopublico.ordemservico.ExibirCalendarioElaboracaoAcompanhamentoRoteiroActionForm"
	method="post"
	onsubmit="return validateExibirCalendarioElaboracaoAcompanhamentoRoteiroActionForm(this);">
	
	<logic:present name="elaboracao">
		<INPUT TYPE="hidden" NAME="tipoCalendario" VALUE="elaboracao"/>
	</logic:present>
	
	<logic:present name="acompanhamento">
		<INPUT TYPE="hidden" NAME="tipoCalendario" VALUE="acompanhamento"/>
	</logic:present>
	

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
			<td width="625" valign="top" class="centercoltext">
			<table height="100%">
				<tr>
					<td></td>
				</tr>
			</table>
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
					<td class="parabg"><html:hidden property="cabecalho" /> <bean:write
						name="ExibirCalendarioElaboracaoAcompanhamentoRoteiroActionForm"
						property="cabecalho" /> do Roteiro</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>
					<td>
					<table width="100%" border="0">
						<tr>
							<td align="left" colspan="3">Para <html:hidden property="titulo" />
							<bean:write
								name="ExibirCalendarioElaboracaoAcompanhamentoRoteiroActionForm"
								property="titulo" /> do roteiro, escolha o dia específico:</td>
						</tr>
						<tr>
							<td></td>
						</tr>
					</table>
					<table width="100%" border="0" align="center">
						<tr bgcolor="#cbe5fe">
							<td align="center" colspan="3">
						<tr bgcolor="#99CCFF">

							<td height="18" colspan="3">
							<div align="center"><strong><html:hidden property="cabecalho" />
							<bean:write
								name="ExibirCalendarioElaboracaoAcompanhamentoRoteiroActionForm"
								property="cabecalho" /> do Roteiro</strong></div>
							</td>
						</tr>

						<tr>
							<td width="25%">&nbsp;</td>

							<td align="center" width="240"><html:hidden property="mes" /> <html:hidden
								property="ano" /><SCRIPT TYPE="text/javascript"
								LANGUAGE="JavaScript"><!--
						document.write(CalendarElaboracaoAcompanhamento(${requestScope.mes},${requestScope.ano},'${requestScope.situacoes}'));
						
					//--></SCRIPT></td>
							<td>&nbsp;</td>
						</tr>
						<tr>
							<td colspan="3"><strong> <font color="#000000"></font></strong>
							<div align="left">
							<hr>
							</div>
							</td>
						</tr>
						<tr>
							<td colspan="3" align="left"><input name="Button" type="button"
								class="bottonRightCol" value="Cancelar" align="left"
								onclick="javascript:window.location.href='/gsan/telaPrincipal.do'">
						</tr>

						<tr>
							<td colspan="3"><strong> <font color="#000000"></font></strong>
							<div align="left">
							<hr>
							</div>
							</td>
						</tr>

					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
	<%@ include file="/jsp/util/rodape.jsp"%>
</html:form>
</body>
</html:html>
