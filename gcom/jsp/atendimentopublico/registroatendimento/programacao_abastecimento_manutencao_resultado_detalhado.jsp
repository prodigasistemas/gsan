 <%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<%@ page import="gcom.util.Util"%>
<%@ page import="gcom.operacional.abastecimento.AbastecimentoProgramacao"%>
<%@ page import="gcom.operacional.abastecimento.ManutencaoProgramacao"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>

<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">


<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false" 
	formName="ResultadoDetalhadoProgramacaoAbastecimentoManutencaoActionForm" />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>

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


</head>

<body leftmargin="5" topmargin="5"
	onload="javascript:resizePageSemLink(565, 692);">

<html:form
	action="/exibirResultadoDetalhadoProgramacaoAbastecimentoManutencaoAction"
	name="ResultadoDetalhadoProgramacaoAbastecimentoManutencaoActionForm"
	type="gcom.gui.atendimentopublico.registroatendimento.ResultadoDetalhadoProgramacaoAbastecimentoManutencaoActionForm"
	method="post"
	onsubmit="return validateResultadoDetalhadoProgramacaoAbastecimentoManutencaoActionForm(this);">
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
					<td class="parabg">Programação de Abastecimento e Manutenção</td>
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
					<td width="160" align="left"><strong>Data da Referência:</strong></td>
					<td colspan="2"><html:text property="dataReferencia"
						readonly="true" style="background-color:#EFEFEF; border:0;"
						size="10" maxlength="10" /></td>

				</tr>
			</table>
			<table width="100%" border="0">
				<tr>
					<td colspan="3" height="24" align="center"><strong>Abastecimento</strong></td>

				</tr>
				<tr>
					<td>
					<table width="100%" align="center" bgcolor="#90c7fc">
						<tr bgcolor="#90c7fc">
							<td align="center"><strong>Hora Início</strong></td>
							<td align="center"><strong>Hora Fim</strong></td>
						</tr>

						<%int cont = 1;%>
						<logic:iterate name="colecaoProgramacaoAbastecimento"
							id="abastecimentoProgramacao" type="AbastecimentoProgramacao">
							<pg:item>
								<% cont = cont + 1;
			if (cont % 2 == 0) {%>
								<tr bgcolor="#FFFFFF">
									<%} else {%>
								<tr bgcolor="#cbe5fe">
									<%}%>

									<td width="50%">
									<div align="center"><%=Util.formatarHoraSemData(abastecimentoProgramacao
							.getHoraInicio())%></div>
									</td>
									<td width="50%">
									<div align="center"><bean:write name="abastecimentoProgramacao"
										property="horaFim" formatKey="hour.format" /></div>
									</td>
								</tr>
							</pg:item>
						</logic:iterate>
					</table>
					</td>
				</tr>
			</table>
			<table width="100%" border="0">
				<tr>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td colspan="4" height="24" align="center"><strong>Manutenção</strong></td>
				</tr>
				<tr bgcolor="#79bbfd">
					<td colspan="4">
					<table width="100%" align="center" bgcolor="#90c7fc">
						<tr bgcolor="#90c7fc">
							<td align="center"><strong>Hora Início</strong></td>
							<td align="center"><strong>Hora Fim</strong></td>
							<td align="center"><strong>Situação</strong></td>
							<td align="center"><strong>Descrição</strong></td>
						</tr>

						<%int cont1 = 1;%>
						<logic:iterate name="colecaoProgramacaoManutencao"
							id="manutencaoProgramacao" type="ManutencaoProgramacao">
							<pg:item>
								<%cont1 = cont1 + 1;
			if (cont1 % 2 == 0) {%>
								<tr bgcolor="#FFFFFF">
									<%} else {%>
								<tr bgcolor="#cbe5fe">
									<%}%>
									<td width="15%">
									<div align="center"><%=Util.formatarHoraSemData(manutencaoProgramacao
							.getHoraInicio())%></div>
									</td>
									<td width="15%">
									<div align="center"><bean:write name="manutencaoProgramacao"
										property="horaFim" formatKey="hour.format" /></div>
									</td>

									<td width="20%">
									<div align="center"><logic:equal name="manutencaoProgramacao"
										property="situacao" value="1">
											EM ABERTO
											</logic:equal> <logic:equal name="manutencaoProgramacao"
										property="situacao" value="2">
											CONCLUÍDA
											</logic:equal></div>
									</td>

									<td width="50%">
									<div align="center"><bean:write name="manutencaoProgramacao"
										property="descricao" /></div>

									</td>
								</tr>
							</pg:item>
						</logic:iterate>
					</table>
					</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
				</tr>


				<tr>
					<td></td>
					<td></td>
					<td></td>
					<td>
					<div align="right"><input name="Button" type="button"
						class="bottonRightCol" value="Voltar"
						onclick="window.location.href='<html:rewrite page="/consultarProgramacaoAbastecimentoManutencaoAction.do"/>'"></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
</html:form>
</body>
</html:html>

