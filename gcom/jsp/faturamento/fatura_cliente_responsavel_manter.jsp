<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>

<head>

<title>GSAN - Sistema Integrado de Gest&atilde;o de Servi&ccedil;os de Saneamento</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>/validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script>
<!--
function facilitador(objeto){
	if (objeto.id == "0" || objeto.id == undefined){
		objeto.id = "1";
		marcarTodos();
	}
	else{
		objeto.id = "0";
		desmarcarTodos();
	}
}

function chamarRemover(){
	var	form = document.forms[0];
	
	if(CheckboxNaoVazio(document.forms[0].idRegistrosRemocao) && confirm('Confirma remo��o?')){
	
		form.action = 'exibirAtualizarFaturaClienteResponsavelAction.do';
		form.submit();
	}
}
-->
</script>
</head>

<body leftmargin="5" topmargin="5">

<html:form action="/atualizarFaturaClienteResponsavelAction"
	name="FiltrarFaturaClienteResponsavelActionForm"
	type="gcom.gui.faturamento.FiltrarFaturaClienteResponsavelActionForm" method="post">

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
			<td width="625" valign="top" bgcolor="#003399" class="centercoltext">
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
					<td class="parabg">Manter Fatura de Cliente Respons�vel</td>

					<td width="11"><img src="imagens/parahead_right.gif"
						editor="Webstyle4"
						moduleid="Album Photos (Project)\toptab_page2_parahead_right.xws"
						border="0" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>

			<table width="100%" align="center" cellpadding="0" cellspacing="0">
				<!--corpo da segunda tabela-->
				<tr>
					<td colspan="5" height="23"><font color="#000000"
						style="font-size:10px"
						face="Verdana, Arial, Helvetica, sans-serif"><strong>Faturas de clientes cadastradas:</strong></font></td>
				</tr>
				<tr>
					<!--<td colspan="4" bgcolor="#3399FF"> -->
					<td colspan="5" bgcolor="#000000" height="2" valign="baseline"></td>
				</tr>
				<tr>
					<td colspan="3"> <b>Valor total da fatura: ${FiltrarFaturaClienteResponsavelActionForm.valorFatura}</b></td>
				</tr>
				<tr>
					<td colspan="3"><b>Data de Vencimento da fatura: ${FiltrarFaturaClienteResponsavelActionForm.dataVencimentoFatura}</b></td>
				</tr>
				<tr>
					<td>
					<table width="100%" align="center" bgcolor="#90c7fc" border="0"
						cellpadding="0" cellspacing="0">
						<tr bgcolor="#cbe5fe">
							<td width="100%" align="center">
							<div style="width: 100%; height: 100%; overflow: auto;">
								<table width="100%" bgcolor="#99CCFF">
									<tr bordercolor="#000000" bgcolor="#90c7fc">
										<td bgcolor="#90c7fc">
										<div align="center"><strong><a
											href="javascript:facilitador(this);">Todos</a></strong></div>
										</td>
										<td bgcolor="#90c7fc">
										<div align="center"><strong>Im�vel</strong></div>
										</td>
	
										<td bgcolor="#90c7fc">
										<div align="center"><strong>Refer�ncia</strong></div>
										</td>
										<td bgcolor="#90c7fc">
										<div align="center"><strong>Vencimento</strong></div>
										</td>
										<td bgcolor="#90c7fc">
										<div align="center"><strong>Consumo</strong></div>
										</td>
										<td bgcolor="#90c7fc">
										<div align="center"><strong>Valor</strong></div>
										</td>
									</tr>
										<logic:present name="colecaoFaturaItem">
											<%int cont = 0;%>
											<logic:iterate name="colecaoFaturaItem" id="faturaItem"
												scope="session">
													<%cont = cont + 1;
													if (cont % 2 == 0) {%>
													<tr bgcolor="#cbe5fe">
														<%} else {%>
													<tr bgcolor="#FFFFFF">
														<%}%>
														<td width="7%">
														<div align="center"><html:checkbox
															property="idRegistrosRemocao" value="${faturaItem.imovel.id}" /></div>
														</td>
	
														<td width="12%" align="center">
															${faturaItem.imovel.matriculaFormatada}
														</td>
														<td width="15%">
															<logic:present name="faturaItem" property="contaGeral.conta">
																<div align="center">
																	<a href="javascript:abrirPopup('exibirConsultarContaAction.do?contaID=${faturaItem.contaGeral.conta.id}&tipoConsulta=conta', 600, 800);">
																		<bean:write name="faturaItem" property="contaGeral.conta.formatarAnoMesParaMesAno"/>
																	</a>																	
																</div>
															</logic:present>
															<logic:present name="faturaItem" property="contaGeral.contaHistorico">
																<div align="center">
																	<a href="javascript:abrirPopup('exibirConsultarContaAction.do?contaID=${faturaItem.contaGeral.conta.id}&tipoConsulta=conta', 600, 800);">
																		<bean:write name="faturaItem" property="contaGeral.contaHistorico.formatarAnoMesParaMesAno"/>
																	</a>		
																</div>
															</logic:present>
														</td>
														<td width="15%">
															<logic:present name="faturaItem" property="contaGeral.conta">
																<div align="center">
																<bean:write name="faturaItem" property="contaGeral.conta.dataVencimentoConta" formatKey="date.format"/></div>
															</logic:present>
															<logic:present name="faturaItem" property="contaGeral.contaHistorico">
																<div align="center">
																<bean:write name="faturaItem" property="contaGeral.contaHistorico.dataVencimentoConta" formatKey="date.format"/></div>
															</logic:present>
														</td>
														<td width="15%">
															<div align="center">${faturaItem.numeroConsumo}</div>
														</td>
														<td width="15%">
															<div align="center">${faturaItem.valorConta}</div>
														</td>
													</tr>
											</logic:iterate>
										</logic:present>
								</table>
							</div>
							</td>
						</tr>
					</table>
					<table width="100%" border="0">

						<tr>
							<td width="233"><font color="#FF0000"> 
								<input type="button" class="bottonRightCol"
									value="Remover" onclick="javascript:chamarRemover();"/></font> 								

								<input type="button" class="bottonRightCol" value="Adicionar Conta"
							 	 	onclick="javascript:abrirPopup('exibirAdicionarFaturaClienteResponsavelContaPopupAction.do?limparForm=1 ', 490, 800);">
							 	 
							 	<input type="button"
									name="buttonFiltro" class="bottonRightCol" value="Voltar Filtro"
									onClick="javascript:window.location.href='/gsan/exibirFiltrarFaturaClienteResponsavelAction.do?menu=sim'">
									
								<input type="button" class="bottonRightCol" value="Hist�rico de Atualiza��es"
							 	 	onclick="javascript:abrirPopup('exibirConsultarFaturaItemHistoricoPopupAction.do', 600, 800);">
									
								<html:submit styleClass="bottonRightCol"
									value="Concluir"/>
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
