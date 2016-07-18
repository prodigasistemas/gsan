<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan"%>

<%@ page import="gcom.batch.FuncionalidadeSituacao"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<head>
<html:html>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>

<script language="JavaScript">
	
function reiniciar(objeto){
	if (CheckboxNaoVazioMensagemGenerico('Nenhum registro selecionado para reinício do processamento batch.', objeto)){
		if (confirm ('Confirma reinício?')) {
			document.forms[0].action = '/gsan/reiniciarFuncionalidadeIniciadaAction.do';
			document.forms[0].submit();
		 }
	}
}

function atualizar() {
	document.forms[0].action = '/gsan/exibirConsultarDadosProcessoIniciadoAction.do';
	document.forms[0].submit();
}

function continuar(objeto){
	if (CheckboxNaoVazioMensagemGenerico('Nenhum registro selecionado para continuação do processamento batch.', objeto)){
		if (confirm ('Confirma continuação?')) {
			document.forms[0].action = '/gsan/continuarFuncionalidadeIniciadaAction.do';
			document.forms[0].submit();
		 }
	}
}

</script>
</head>

<body leftmargin="5" topmargin="5"
	onload="javascript:setarFoco('${requestScope.nomeCampo}');">
<html:form action="/reiniciarFuncionalidadeIniciadaAction"
	name="ManutencaoRegistroActionForm"
	type="gcom.gui.ManutencaoRegistroActionForm" method="post">

	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>

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
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_left.gif" /></td>
					<td class="parabg">Consultar Dados de Processos Iniciados</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>

				</tr>
			</table>
			<table width="100%" border="0">
				<tr>
					<td><strong>M&ecirc;s/Ano de Refer&ecirc;ncia:
					${requestScope.mesAnoReferencia}</strong></td>
					<td>
					<div align="left"><strong> Data: <bean:write name="dataCorrente"
						formatKey="date.format" /> - Hora: <bean:write
						name="dataCorrente" formatKey="hour.format" /> </strong></div>
					</td>

				</tr>
			</table>

			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>
					<td bgcolor="#6AB3FB">
					<div align="center" class="style11">
					<div align="left"><strong> Processo:
					${requestScope.processoIniciado.processo.descricaoProcesso}</strong></div>
					</div>
					</td>
				</tr>
				<tr>

					<td bgcolor="#E2F0FE">
					<div align="left" class="style9">
					<table width="100%">
						<tr bgcolor="#E2F0FE">
							<td width="34%" height="17" bgcolor="#E2F0FE">Hora de
							In&iacute;cio: <bean:write name="processoIniciado"
								property="dataHoraInicio" formatKey="hour.format"
								scope="request" /></td>
							<td width="31%">Hora de Conclus&atilde;o: <bean:write
								name="processoIniciado" property="dataHoraTermino"
								formatKey="hour.format" /></td>
							<td width="35%">${requestScope.processoIniciado.processoSituacao.descricao}</td>

						</tr>
					</table>
					</div>
					</td>

				</tr>
				<tr>
					<td height="31">
					<table width="100%" bgcolor="#99CCFF">
						<!--header da tabela interna -->
						<tr bordercolor="#FFFFFF" bgcolor="#99CCFF">
							<c:if
								test="${requestScope.processoIniciado.processoSituacao.id == requestScope.concluido || 
									requestScope.processoIniciado.processoSituacao.id == requestScope.concluidoComErro || 
									requestScope.processoIniciado.processoSituacao.id == requestScope.execucaoCancelada}">
								<td width="4%">
								<div align="center"></div>
								</td>
							</c:if>
							<td width="6%">
							<div align="center" class="style9"><strong>Seq.</strong></div>
							</td>
							<td width="34%">
							<div align="center" class="style9"><strong>Nome Funcionalidade</strong></div>
							</td>

							<td width="11%">
							<div align="center" class="style9"><strong>Hora In&iacute;cio </strong></div>
							</td>
							<td width="12%">
							<div align="center"><strong>Tempo em Execu&ccedil;&atilde;o</strong></div>
							</td>
							<td width="12%">
							<div align="center"><strong><strong>Hora de Conclus&atilde;o*</strong></strong></div>
							</td>
							<td width="25%">
							<div align="center" class="style9"><strong>SITUA&Ccedil;&Atilde;O</strong></div>
							</td>
							<td width="25%">
							<div align="center" class="style9"><strong>Erro</strong></div>
							</td>
							
							
						</tr>
						<%int cont = 0;%>
						<logic:iterate name="colecaoFuncionalidadeIniciada"
							id="funcionalidadeIniciada" scope="request">

							<%cont = cont + 1;
			if (cont % 2 == 0) {%>
							<tr bgcolor="#cbe5fe">
								<%} else {%>
							<tr bgcolor="#FFFFFF">
								<%}%>
								<c:if
									test="${requestScope.processoIniciado.processoSituacao.id == requestScope.concluido || 
									requestScope.processoIniciado.processoSituacao.id == requestScope.concluidoComErro || 
									requestScope.processoIniciado.processoSituacao.id == requestScope.execucaoCancelada}">
									<td>
									<div align="center"><logic:equal name="funcionalidadeIniciada"
										property="funcionalidadeSituacao.id"
										value="<%="" + FuncionalidadeSituacao.CONCLUIDA%>">
										<span class="style1"><strong> <html:checkbox
											property="idRegistrosRemocao"
											value="${funcionalidadeIniciada.id}" /></strong> </span>
									</logic:equal> <logic:equal name="funcionalidadeIniciada"
										property="funcionalidadeSituacao.id"
										value="<%="" + FuncionalidadeSituacao.CONCLUIDA_COM_ERRO%>">
										<span class="style1"><strong> <html:checkbox
											property="idRegistrosRemocao"
											value="${funcionalidadeIniciada.id}" /></strong> </span>
									</logic:equal> <logic:equal name="funcionalidadeIniciada"
										property="funcionalidadeSituacao.id"
										value="<%="" + FuncionalidadeSituacao.EXECUCAO_CANCELADA%>">
										<span class="style1"><strong> <html:checkbox
											property="idRegistrosRemocao"
											value="${funcionalidadeIniciada.id}" /></strong> </span>
									</logic:equal></div>
									</td>
								</c:if>
								<td>${funcionalidadeIniciada.processoFuncionalidade.sequencialExecucao}</td>
								<td><a
									href="javascript:abrirPopup('exibirConsultarUnidadeProcessamentoProcessoInseridoAction.do?idFuncionalidadeIniciada=${funcionalidadeIniciada.id}', 400, 500);">
								${funcionalidadeIniciada.processoFuncionalidade.funcionalidade.descricao}</a>
								</td>
								<td height="17">
								<div align="center"><bean:write name="funcionalidadeIniciada"
									property="dataHoraInicio" formatKey="hour.format" /><br>
								</div>
								</td>
								<td>
								<div align="center"><bean:write name="funcionalidadeIniciada"
									property="diferencaInicioTermino" /></div>
								</td>

								<td>
								<div align="center"><bean:write name="funcionalidadeIniciada"
									property="dataHoraTermino" formatKey="hour.format" /></div>
								</td>
								
								
								
								
								<td>
								<div align="center">
								<a href="javascript:abrirPopup('exibirConsultarDadosProcessoIniciadoAction.do?informacaoPopup=sim', 200, 800);">
								${funcionalidadeIniciada.funcionalidadeSituacao.descricaoOperacaoSituacao}
								</a>
								</div>
								</td>
								
								
								
								<td>
									<c:if test="${not empty funcionalidadeIniciada.descricaoExcecao}" var="existeErro">
										<div align="center"><input type="button" name="Log" value="Log" class="bottonRightCol" onclick="javascript:window.open('exibirExcecaoFuncionalidadeIniciadaAction.do?idFuncionalidadeIniciada=${funcionalidadeIniciada.id}','mywindow','width=700,height=700,toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=yes,copyhistory=no,resizable=yes');"/></div>
									</c:if>
									<c:if test="${not existeErro}">
										<div align="center">&nbsp;</div>
									</c:if>
								</td>
								
							</tr>

						</logic:iterate>



					</table>
					</td>
				</tr>
			</table>
			<table width="100%">
				<tr>
					<td>
						<input name="Button2" type="button" class="bottonRightCol" value="Voltar"
							onclick="window.location.href='<html:rewrite page="/filtrarProcessoAction.do"/>'">
						<input align="right" name="Button1" type="button" class="bottonRightCol" value="Atualizar" onclick="atualizar()">
					</td>
					<td colspan="2" align="right">
					<c:if
						test="${requestScope.processoIniciado.processoSituacao.id == requestScope.concluidoComErro || 
									requestScope.processoIniciado.processoSituacao.id == requestScope.execucaoCancelada}">
						<input type="button" value="Continuar Batch" name="botaoContinuar" class="bottonRightCol" onclick="javascript:continuar(document.forms[0].idRegistrosRemocao);" />			
									
					</c:if>
					<c:if
						test="${requestScope.processoIniciado.processoSituacao.id == requestScope.concluido || 
									requestScope.processoIniciado.processoSituacao.id == requestScope.concluidoComErro || 
									requestScope.processoIniciado.processoSituacao.id == requestScope.execucaoCancelada}">
						
						<gsan:controleAcessoBotao name="buttonReiniciar"
							value="Reiniciar Batch"
							onclick="javascript:reiniciar(document.forms[0].idRegistrosRemocao);"
							url="reiniciarFuncionalidadeIniciadaAction.do" /></c:if></td>
				</tr>
			</table>

			</td>
		</tr>
	</table>



	<%@ include file="/jsp/util/rodape.jsp"%>

</html:form>
</body>
</html:html>
