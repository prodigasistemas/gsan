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
	src="<bean:message key="caminho.js"/>/validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script>
<!--


-->
</script>
</head>

<body leftmargin="5" topmargin="5">
<form name="form1"><%@ include file="/jsp/util/cabecalho.jsp"%> <%@ include
	file="/jsp/util/menu.jsp"%>

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
		<table align="center" border="0" cellpadding="0" cellspacing="0"
			width="100%">
			<tbody>
				<tr>
					<td width="11"><img src="imagens/parahead_left.gif" border="0"></td>

					<td class="parabg">Consultar Posição do Faturamento</td>
					<td valign="top" width="11"><img src="imagens/parahead_right.gif"
						border="0"></td>
				</tr>
			</tbody>
		</table>
		<div style="overflow: auto; width: 100%; height: 400;">
		<table width="100%" border="0">
        <tr>
				<logic:present scope="application" name="urlHelp">
						<td align="right"><a href="javascript:abrirPopupHelp('${applicationScope.urlHelp}faturamentoPosicaoConsultar', 500, 700);"><span style="font-weight: bold"><font color="#3165CE">Ajuda</font></span></a></td>									
				</logic:present>
				<logic:notPresent scope="application" name="urlHelp">
						<td align="right"><span style="font-weight: bold"><font color=#696969><u>Ajuda</u></font></span></td>									
				</logic:notPresent>
        </tr>
        </table>
		<!--Fim Tabela Reference a Páginação da Tela de Processo-->
		<p>&nbsp;</p>

		<logic:iterate name="mapPagina" id="mapPagina" indexId="i">
			<table>
				<tbody style="overflow: auto">
					<tr>
						<td height="126" width="614">
							<table align="center" bgcolor="#79bbfd" width="100%">
								<tr bordercolor="#FFFFFF" bgcolor="#79bbfd">
									<td colspan="2" width="24%">
										<div align="left"><strong>Grupo de Faturamento: </strong></div>
									</td>
									<td width="21%"><input name="mesAno2"
											value="<bean:write name="mapPagina" property="key.descricao" />"
											size="10" maxlength="10" disabled="disabled"
											style="border: 0pt none ; background-color: rgb(239, 239, 239);"
											type="text"></td>
									<td colspan="2" width="25%" align="right"><strong>Mês de Faturamento:</strong></td>
									<td width="30%" align="left"><input
											value="<bean:write name="mesAno" scope="request" />"
											name="mesAno" size="7" maxlength="7" disabled="disabled"
											style="border: 0pt none ; background-color: rgb(239, 239, 239);"
											type="text"></td>
								</tr>
							</table>
							<table align="center" bgcolor="#90c7fc" width="100%">
								<tbody>
								<tr bordercolor="#FFFFFF" bgcolor="#90c7fc">
									<td width="120">
									<div align="center"><strong>Atividade</strong></div>
									</td>
									<td width="86">
									<div align="center"><strong>Predecessora</strong></div>
									</td>
									<td width="110">
									<div align="center"><strong>Obrigatória</strong></div>
									</td>
									<td width="">
									<div align="center"><strong>Data Prevista</strong></div>
									</td>
									<td width="90">
									<div align="center"><strong>Data Comando</strong></div>
									</td>
									<td width="85">
									<div align="center"><strong>Data Realizada</strong></div>
									</td>
								</tr>
								<%int cont = 0;%>

								<tr>
									<td colspan="6">
									<div style="overflow: auto; width: 100%; height: 100;">
									<table align="center" bgcolor="#90c7fc" width="100%">

										<logic:iterate name="mapPagina" property="value"
											id="faturamentoAtividadeCronograma" indexId="ii">



											<!--corpo da segunda tabela-->
											<%cont = cont + 1;
						if (cont % 2 == 0) {%>
											<tr bgcolor="#cbe5fe">
												<%} else {%>
											<tr bgcolor="#FFFFFF">
												<%}%>

												<td width="120">
													<logic:equal name="faturamentoAtividadeCronograma" property="faturamentoAtividade.descricao" value="REGISTRAR LEITURA">																	
														<div align="left">
															<a href="javascript:abrirPopup('exibirLeiturasNaoRegistradasAction.do?idFaturamento=
															<bean:write name="mapPagina" property="key.descricao" />&
															mesAno=<bean:write name="mesAno" scope="request" />',275,480);">
													</logic:equal>	
														<bean:write
															name="faturamentoAtividadeCronograma"
															property="faturamentoAtividade.descricao" />&nbsp;</a></div>
												</td>
												<td width="90">
												<div align="left"><logic:present
													name="faturamentoAtividadeCronograma"
													property="faturamentoAtividade.faturamentoAtividadePrecedente">
													<bean:write name="faturamentoAtividadeCronograma"
														property="faturamentoAtividade.faturamentoAtividadePrecedente.descricao" />
												</logic:present>&nbsp;</div>
												</td>
												<td width="110">
												<div align="left"><logic:equal
													name="faturamentoAtividadeCronograma"
													property="faturamentoAtividade.indicadorObrigatoriedadeAtividade"
													value="1"> 
                        	Sim
                        	</logic:equal> <logic:notEqual
													name="faturamentoAtividadeCronograma"
													property="faturamentoAtividade.indicadorObrigatoriedadeAtividade"
													value="1"> 
                        	Não
                        	</logic:notEqual></div>
												</td>
												<td width="90" valign="middle">
												<div align="center" title="${faturamentoAtividadeCronograma.faturamentoGrupoCronogramaMensal.usuario.nomeUsuario}"><logic:present
													name="faturamentoAtividadeCronograma"
													property="dataPrevista">
													<bean:write name="faturamentoAtividadeCronograma"
														format="dd/MM/yyyy" property="dataPrevista" />
												</logic:present></div>
												</td>
												<td width="88" valign="middle">
												<div align="center" title="${faturamentoAtividadeCronograma.usuario.nomeUsuario}"><logic:present
													name="faturamentoAtividadeCronograma" property="comando">
													<bean:write name="faturamentoAtividadeCronograma"
														format="dd/MM/yyyy" property="comando" /><br>
													<bean:write name="faturamentoAtividadeCronograma"
														formatKey="hour.format" property="comando" />
												</logic:present></div>
												</td>
												<td width="70" valign="middle">
												<div align="center"><logic:present
													name="faturamentoAtividadeCronograma"
													property="dataRealizacao">
													<bean:write name="faturamentoAtividadeCronograma"
														format="dd/MM/yyyy" property="dataRealizacao" /><br>
													<bean:write name="faturamentoAtividadeCronograma"
														formatKey="hour.format" property="dataRealizacao" />
														
												</logic:present></div>
												</td>
										</logic:iterate>
									</table>
									</div>

									</td>
								</tr>


							</tbody>
						</table>
						</td>
					</tr>
				</tbody>
			</table>
			<p>&nbsp;</p>
			<p>&nbsp;</p>
		</logic:iterate>
		<table width="100%" border="0">
				<tr>
					<td colspan="2">
						<div align="right"><a href="javascript:toggleBox('demodiv',1);"> <img
							border="0" src="<bean:message key="caminho.imagens"/>print.gif"
							title="Imprimir Posição do Faturamento" /> </a></div>
					</td>
				</tr>
		</table>
		</div>
		</td>
	</tr>
</table>

<jsp:include
	page="/jsp/relatorio/escolher_tipo_relatorio.jsp?relatorio=gerarRelatorioPosicaoFaturamentoAction.do" />
<%@ include file="/jsp/util/rodape.jsp"%>
</form>

</body>
</html:html>
