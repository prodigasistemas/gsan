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

<script language="JavaScript">
 

</script>
</head>
<body leftmargin="5" topmargin="5">
<html:form action="/removerBairroAction"
	name="InformarResumoSituacaoEspecialCobrancaActionForm"
	type="gcom.gui.gerencial.faturamento.InformarResumoSituacaoEspecialCobrancaActionForm"
	method="post">
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

			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_left.gif" /></td>
					<td class="parabg">Consultar Resumo de Situação Especial de
					Cobranca</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>

			<div style="width: 100%; height: 400; overflow: auto;">
			<logic:iterate name="colecaoRCSE" scope="session" id="RCSE">
				<p>&nbsp;</p>
				<p>&nbsp;</p>
				<table align="center" bgcolor="#99ccff" border="0" cellpadding="0"
					cellspacing="0" width="100%">
					<tr>

						<td colspan="5" height="20"><strong>Gerência - <bean:write
							name="RCSE" property="gerenciaRegionalDescricaoAbreviada" /> -
						GERÊNCIA REGIONAL <bean:write name="RCSE"
							property="gerenciaRegionalDescricao" /></strong></td>
					</tr>
				</table>

				<table align="center" border="0" cellpadding="0" cellspacing="0"
					width="100%">
					<tr>
						<td colspan="5" height="10"></td>
					</tr>
				</table>

				<logic:iterate name="RCSE"
					property="resumoCobrancaSituacaoEspecialConsultaUnidadeNegHelper"
					id="unidadeNegocio">

					<table align="center" border="0" cellpadding="0" cellspacing="0"
						width="100%">

						<tr>
							<td colspan="5" height="20"><strong>Unidade Negócio - <bean:write
								name="unidadeNegocio" property="idUnidadeNegocio" /> - <bean:write
								name="unidadeNegocio" property="unidadeNegocioDescricao" /></strong></td>

						</tr>

						<tr>
							<td><logic:iterate name="unidadeNegocio"
								property="resumoCobrancaSituacaoEspecialConsultaLocalidadeHelper"
								id="localidade">

								<table align="center" border="0" cellpadding="0" cellspacing="0"
									width="100%">

									<tr>
										<td colspan="5" height="20"><strong>Localidade - <bean:write
											name="localidade" property="idLocalidade" /> - <bean:write
											name="localidade" property="localidadeDescricao" /></strong></td>
									</tr>

									<logic:iterate name="localidade"
										property="resumoCobrancaSituacaoEspecialConsultaSetorComercialHelper"
										id="setorComercial">

										<table align="center" border="0" cellpadding="0"
											cellspacing="0" width="100%">

											<tr>
												<td colspan="5" height="20"><strong>Setor Comercial - <bean:write
													name="setorComercial" property="codigoSetorComercial" /> -
												<bean:write name="setorComercial"
													property="setorComercialDescricao" /></strong></td>
											</tr>
											<tr>
												<td colspan="5"><logic:iterate name="setorComercial"
													property="resumoCobrancaSituacaoEspecialConsultaSitTipoHelper"
													id="sitTipo">
													<table align="center" border="0" cellpadding="0"
														cellspacing="0" width="100%">

														<tr>
															<td colspan="4" height="20"><span
																style="font-size: 11px;"><strong><bean:write
																name="sitTipo" property="situacaoTipoDescricao" /> </strong></span></td>
														</tr>
														<tr>
															<td colspan="4">
															<table align="center" bgcolor="#99ccff" border="0"
																cellpadding="0" cellspacing="0" width="100%">

																<tr bgcolor="#99ccff">
																	<td align="center" height="25" width="152"><span
																		style="font-size: 11px;"><strong>Motivo</strong></span></td>
																	<td align="center" width="63"><span
																		style="font-size: 11px;"><strong>Início</strong></span></td>
																	<td align="center" width="56"><span
																		style="font-size: 11px;"><strong>Término</strong></span></td>
																	<td align="center" width="88"><span
																		style="font-size: 11px;"><strong>Qtd. Paralisada</strong></span></td>

																	<td align="center" width="96"><span
																		style="font-size: 11px;"><strong>Qtd. Ligações</strong></span></td>
																	<td align="center" width="49"><span
																		style="font-size: 11px;"><strong>Perc(%)</strong></span></td>
																	<td align="center" width="100"><span
																		style="font-size: 11px;"><strong>Fat. Estimado</strong></span></td>
																</tr>
															</table>
															</td>
														</tr>

														<tr>
															<td colspan="4"><%String cor = "#FFFFFF";%> <logic:iterate
																name="sitTipo"
																property="resumoCobrancaSituacaoEspecialConsultaMotivoHelper"
																id="motivo">
																<table bgcolor="#99ccff" width="100%">
																	<%if (cor.equalsIgnoreCase("#FFFFFF")) {
				cor = "#cbe5fe";%>
																	<tr bgcolor="#cbe5fe">
																		<%} else {
				cor = "#FFFFFF";%>
																	<tr bgcolor="#FFFFFF">
																		<%}%>
																		<td width="146"><bean:write name="motivo"
																			property="motivoDescricao" /></td>

																		<td width="60">
																		<div align="center">${motivo.formatarAnoMesParaMesAnoInicio}</div>
																		</td>
																		<td width="51">
																		<div align="center">${motivo.formatarAnoMesParaMesAnoFim}</div>
																		</td>

																		<td width="88">
																		<div align="center"><bean:write name="motivo"
																			property="qtParalisada" formatKey="number.format" /></div>
																		</td>
																		<td width="90">
																		<div align="center"><logic:equal name="motivo"
																			property="qtLigacoes" value="">0</logic:equal> <logic:notEqual
																			name="motivo" property="qtLigacoes" value="">
																			<bean:write name="motivo" property="qtLigacoes"
																				formatKey="number.format" />
																		</logic:notEqual></div>
																		</td>
																		<td width="46">
																		<div align="center"><bean:write name="motivo"
																			property="percentual" formatKey="money.format" /></div>
																		</td>
																		<td width="91">
																		<div align="center"><bean:write name="motivo"
																			property="valorFaturamentoEstimadoFormatado"
																			formatKey="money.format" /></div>
																		</td>
																	</tr>
																</table>
															</logic:iterate>
															<table bgcolor="#cbe5fe" cellpadding="0" cellspacing="0"
																width="100%">
																<tr>
																	<td height="4"></td>
																</tr>
															</table>

															<table bgcolor="#cbe5fe" border="0" cellpadding="0"
																cellspacing="0" width="100%">
																<tr>
																	<td height="20" width="150">&nbsp;</td>

																	<td align="left" bgcolor="#cbe5fe" width="126"><span
																		style="font-size: 11px;"><strong>Total da Situação</strong></span></td>
																	<td align="center" bgcolor="#99ccff" width="91"><strong><bean:write
																		name="sitTipo" property="totalSituacaoTipo"
																		formatKey="number.format" /></strong></td>
																	<td align="center" bgcolor="#99ccff" width="95">
																	<div align="center"><strong><bean:write name="sitTipo"
																		property="totalQtLigacoesSitTipo"
																		formatKey="number.format" /></strong></div>
																	</td>
																	<td align="center" bgcolor="#99ccff" width="49">
																	<div align="center"><strong><bean:write name="sitTipo"
																		property="totalPercentualSitTipo"
																		formatKey="money.format" /></strong></div>
																	</td>
																	<td align="center" bgcolor="#99ccff" width="95">
																	<div align="center"><strong><bean:write name="sitTipo"
																		property="totalFatEstimadoSitTipo"
																		formatKey="money.format" /></strong></div>
																	</td>
																</tr>
															</table>

															<table bgcolor="#cbe5fe" cellpadding="0" cellspacing="0"
																width="100%">
																<tr>
																	<td height="4"></td>
																</tr>

															</table>

															</td>

														</tr>

													</table>

												</logic:iterate>
												<table bgcolor="#cbe5fe" cellpadding="0" cellspacing="0"
													width="100%">

													<tr>
														<td height="4">&nbsp;</td>
													</tr>

												</table>

												<table bgcolor="#cbe5fe" border="0" cellpadding="0"
													cellspacing="0" width="100%">
													<tr>
														<td height="20" width="150">&nbsp;</td>

														<td align="left" bgcolor="#cbe5fe" width="126"><span
															style="font-size: 11px;"><strong>Total do Setor Comercial</strong></span></td>
														<td align="center" bgcolor="#99ccff" width="91"><strong><bean:write
															name="setorComercial" property="totalSetorComercial"
															formatKey="number.format" /></strong></td>
														<td align="center" bgcolor="#99ccff" width="95"><strong> <strong><bean:write
															name="setorComercial"
															property="totalQtLigacoesSetorComercial"
															formatKey="number.format" /></strong></strong></td>
														<td align="center" bgcolor="#99ccff" width="49"><strong> <strong><bean:write
															name="setorComercial"
															property="totalPercentualSetorComercial"
															formatKey="money.format" /></strong></strong></td>
														<td align="center" bgcolor="#99ccff" width="95"><strong> <strong><bean:write
															name="setorComercial"
															property="totalFatEstimadoSetorComercial"
															formatKey="money.format" /></strong></strong></td>
													</tr>


												</table>
												</td>
											</tr>
</table>
											</logic:iterate>
											<table bgcolor="#cbe5fe" cellpadding="0" cellspacing="0"
												width="100%">
												<tr>
													<td height="4">&nbsp;</td>
												</tr>

											</table>

											<table bgcolor="#cbe5fe" border="0" cellpadding="0"
												cellspacing="0" width="100%">
												<tr>
													<td height="20" width="150">&nbsp;</td>

													<td align="left" bgcolor="#cbe5fe" width="126"><span
														style="font-size: 11px;"><strong>Total da Localidade</strong></span></td>
													<td align="center" bgcolor="#99ccff" width="91"><strong><bean:write
														name="localidade" property="totalLocalidade"
														formatKey="number.format" /></strong></td>
													<td align="center" bgcolor="#99ccff" width="95"><strong> <strong><bean:write
														name="localidade" property="totalQtLigacoesLocalidade"
														formatKey="number.format" /></strong></strong></td>
													<td align="center" bgcolor="#99ccff" width="49"><strong> <strong><bean:write
														name="localidade" property="totalPercentualLocalidade"
														formatKey="money.format" /></strong></strong></td>
													<td align="center" bgcolor="#99ccff" width="95"><strong> <strong><bean:write
														name="localidade" property="totalFatEstimadoLocalidade"
														formatKey="money.format" /></strong></strong></td>
												</tr>
											</table>
									</logic:iterate>
									<table bgcolor="#cbe5fe" cellpadding="0" cellspacing="0"
										width="100%">

										<tr>
											<td height="4">&nbsp;</td>
										</tr>

									</table>

									<table bgcolor="#cbe5fe" border="0" cellpadding="0"
										cellspacing="0" width="100%">
										<tr>
											<td height="20" width="150">&nbsp;</td>

											<td align="left" bgcolor="#cbe5fe" width="126"><span
												style="font-size: 11px;"><strong>Total da Unidade Negócio</strong></span></td>
											<td align="center" bgcolor="#99ccff" width="91"><strong><bean:write
												name="unidadeNegocio" property="totalUnidadeNegocio"
												formatKey="number.format" /></strong></td>
											<td align="center" bgcolor="#99ccff" width="95"><strong> <strong><bean:write
												name="unidadeNegocio"
												property="totalQtLigacoesUnidadeNegocio"
												formatKey="number.format" /></strong></strong></td>
											<td align="center" bgcolor="#99ccff" width="49"><strong> <strong><bean:write
												name="unidadeNegocio"
												property="totalPercentualUnidadeNegocio"
												formatKey="money.format" /></strong></strong></td>
											<td align="center" bgcolor="#99ccff" width="95"><strong> <strong><bean:write
												name="unidadeNegocio"
												property="totalFatEstimadoUnidadeNegocio"
												formatKey="money.format" /></strong></strong></td>
										</tr>

									</table>
									</table>
									</logic:iterate>
									<table bgcolor="#cbe5fe" cellpadding="0" cellspacing="0"
										width="100%">

										<tr>
											<td height="4">&nbsp;</td>
										</tr>

									</table>
									<table bgcolor="#cbe5fe" border="0" cellpadding="0"
										cellspacing="0" width="100%">

										<tr>
											<td height="20" width="150">&nbsp;</td>

											<td align="left" bgcolor="#cbe5fe" width="126"><span
												style="font-size: 11px;"><strong>Total da Gerência</strong></span></td>
											<td align="center" bgcolor="#99ccff" width="91"><strong><bean:write
												name="RCSE" property="totalGerenciaRegional"
												formatKey="number.format" /></strong></td>
											<td align="center" bgcolor="#99ccff" width="95"><strong><bean:write
												name="RCSE" property="totalQtLigacoesGerencia"
												formatKey="number.format" /></strong></td>
											<td align="center" bgcolor="#99ccff" width="49"><strong><bean:write
												name="RCSE" property="totalPercentualGerencia"
												formatKey="money.format" /></strong></td>
											<td align="center" bgcolor="#99ccff" width="95"><strong><bean:write
												name="RCSE" property="totalFatEstimadoGerencia"
												formatKey="money.format" /></strong></td>
										</tr>

									</table>
									</logic:iterate>
									
									<table bgcolor="#cbe5fe" cellpadding="0" cellspacing="0"
										width="100%">

										<tr>
											<td height="4">&nbsp;</td>
										</tr>

									</table>
									<table bgcolor="#cbe5fe" border="0" cellpadding="0"
										cellspacing="0">

										<tr>
											<td height="20" width="150">&nbsp;</td>
											<td align="left" bgcolor="#cbe5fe" width="126"><span
												style="font-size: 11px;"><strong>Total Geral</strong></span></td>
											<td align="center" bgcolor="#99ccff" width="91"><strong><bean:write
												name="totalGeral" scope="session" formatKey="number.format" /></strong></td>
											<td align="center" bgcolor="#99ccff" width="95"><strong><bean:write
												name="totalQtLigacoesGeral" scope="session"
												formatKey="number.format" /></strong></td>
											<td align="center" bgcolor="#99ccff" width="49"><strong><bean:write
												name="totalPercentualGeral" scope="session"
												formatKey="money.format" /></strong></td>
											<td align="center" bgcolor="#99ccff" width="95"><strong><bean:write
												name="totalFatEstimadoGeral" scope="session"
												formatKey="money.format" /></strong></td>
										</tr>

									</table>
									</div>
									
								<table bgcolor="#cbe5fe" cellpadding="0" cellspacing="0"
									width="100%">

									<tr>
										<td height="4">&nbsp;</td>
									</tr>

								</table>

								<table width="100%">

									<tr>
										<td align="left" width="20%"><input name="Submit223"
											class="bottonRightCol" onclick="javascript:history.back();"
											value="Voltar Filtro" type="button"></td>
										<td align="right"><a href="javascript:toggleBox('demodiv',1);">
										<img border="0"
											src="<bean:message key="caminho.imagens"/>print.gif"
											title="Gerar Relatório das Situações Especiais de Faturamento" />
										</a></td>
									</tr>

								</table>
					</table>
	<jsp:include
		page="/jsp/relatorio/escolher_tipo_relatorio.jsp?relatorio=gerarRelatorioResumoFaturamentoSituacaoEspecialAction.do" />
	<%@ include file="/jsp/util/rodape.jsp"%>
</html:form>
</body>
</html:html>
