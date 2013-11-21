<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan"%>
<%@page import="gcom.util.ConstantesSistema"%>


<%@ page import="gcom.cobranca.NegativacaoCriterioCpfTipo"%>
<%@page import="gcom.cadastro.imovel.Subcategoria"%>
<%@page import="gcom.cadastro.imovel.ImovelPerfil"%>
<%@page import="gcom.cadastro.cliente.ClienteTipo"%>



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



<script language="JavaScript">

function chamarPaginaAnterior(){
		var form = ConsultarParametrosComandoNegativacaoActionForm;
		
		form.action = 'exibirResultadoPesquisaComandoNegativacaoAction.do?FECHAR=TRUE';
   		form.submit();
}

function extendeTabela(tabela,display){
		var form = document.forms[0];

		if(display){
 			eval('layerHide'+tabela).style.display = 'none';
 			eval('layerShow'+tabela).style.display = 'block';
		}else{
			eval('layerHide'+tabela).style.display = 'block';
 			eval('layerShow'+tabela).style.display = 'none';
		}
	}
</script>

</head>

<body leftmargin="0" topmargin="0" onload="window.focus();">

<html:form
	action="/exibirConsultarParametrosComandoNegativacaoPopupAction"
	name="ConsultarParametrosComandoNegativacaoActionForm" method="post"
	type="gcom.gui.cobranca.ConsultarParametrosComandoNegativacaoActionForm">


	<table width="695" border="0" cellspacing="5" cellpadding="0">
		<tr>
			<td width="695" valign="top" class="centercoltext">
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
					<td class="parabg">Consultar Parâmetros do Comando da Negativação</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
				<tr>
					<td height="5" colspan="3"></td>
				</tr>
			</table>

			<!-- DADOS GERAIS -->

			<table width="100%" cellpadding="0" cellspacing="0">
				<tr>
					<td height="31">
					<table width="100%" border="0" align="center">
						<tr bgcolor="#cbe5fe">
							<td align="center">

							<div id="layerHideDadosGerais" style="display:block">
							<table width="100%" border="0" bgcolor="#99CCFF">
								<tr bgcolor="#99CCFF">
									<td align="center"><span class="style2"> <a
										href="javascript:extendeTabela('DadosGerais',true);" /> <b>DADOS
									GERAIS</b> </a> </span></td>
								</tr>
							</table>
							</div>

							<div id="layerShowDadosGerais" style="display:none">
							<table width="100%" border="0" bgcolor="#99CCFF">
								<tr bgcolor="#99CCFF">
									<td align="center"><span class="style2"> <a
										href="javascript:extendeTabela('DadosGerais',false);" /> <b>DADOS
									GERAIS</b> </a> </span></td>
								</tr>

								<tr bgcolor="#cbe5fe">
									<td>
									<table border="0" width="100%">

										<tr>
											<td><strong>Negativador:</strong></td>
											<td><html:text property="negativador" size="50"
												maxlength="50" readonly="true"
												style="background-color:#EFEFEF; border:0; color: #000000;" />

											</td>
										</tr>
										<tr>
											<td><strong>Quantidade de Inclusões:</strong></td>
											<td><html:text property="qtdInclusoes" size="5" maxlength="5"
												readonly="true"
												style="background-color:#EFEFEF; border:0; color: #000000;" />
											</td>
										</tr>

										<tr>
											<td><strong>Valor Total do Débito:</strong></td>
											<td><html:text property="valorTotalDebito" size="16"
												maxlength="16" readonly="true"
												style="background-color:#EFEFEF; border:0; color: #000000;" />
											</td>
										</tr>
										<tr>
											<td><strong>Quantidade de Itens Incluídos:</strong></td>
											<td><html:text property="qtdItensIncluidos" size="5"
												maxlength="5" readonly="true"
												style="background-color:#EFEFEF; border:0; color: #000000;" />
											</td>
										</tr>

										<tr>
											<td height="10" colspan="4">
											<div align="right">
											<hr>
											</div>
											<div align="right"></div>
											</td>
										</tr>

										<tr>
											<td><strong>Título do Comando:</strong></td>
											<td><strong> <html:textarea property="tituloComando"
												cols="40" rows="5" readonly="true"
												style="background-color:#EFEFEF; border:0; color: #000000;"/> </strong></td>
										</tr>

										<tr>
											<td><strong>Descrição da Solicitação:</strong></td>
											<td><strong> <html:textarea property="descricaoSolicitacao"
												cols="40" rows="5" readonly="true"
												style="background-color:#EFEFEF; border:0; color: #000000;"/> </strong></td>
										</tr>

										<tr>
											<td><strong>Simular a Negativação:</strong></td>
											<td><html:radio property="indicadorSimularNegativacao"
												value="<%=ConstantesSistema.SIM.toString()%>" disabled="true"/><strong>Sim
											<html:radio property="indicadorSimularNegativacao"
												value="<%=ConstantesSistema.NAO.toString()%>" disabled="true"/>Não </strong>
											</td>
										</tr>

										<tr>
											<td><strong>Data Prevista para Execução:</strong></td>
											<td><html:text property="dataExecucao" size="10"
												maxlength="10" readonly="true"
												style="background-color:#EFEFEF; border:0; color: #000000;" />
											</td>
										</tr>

										<tr>
											<td><strong>Usuário Responsáviel:</strong></td>
											<td>
											  <table>
											    <tr>
											      <td><html:text property="idUsuarioResponsavel" size="6"
												   maxlength="6" readonly="true"
												   style="background-color:#EFEFEF; border:0; color: #000000;" />
											      </td>
											      <td><html:text property="usuarioResponsavel" size="50"
													maxlength="50" readonly="true"
													style="background-color:#EFEFEF; border:0; color: #000000;" />
												</td>
											    </tr>
											  </table>
											</td>
										</tr>

										<tr>
											<td><strong>Quantidade Máxima de Inclusões:</strong></td>
											<td><html:text property="qtdMaxInclusoes" size="4"
												maxlength="4" readonly="true"
												style="background-color:#EFEFEF; border:0; color: #000000;" />
											</td>
										</tr>

										<tr>
											<td height="10" colspan="4">
											<div align="right">
											<hr>
											</div>
											<div align="right"></div>
											</td>
										</tr>

										<tr>
											<td align=" left"><font color="#000000"
												style="font-size:10px"
												face="Verdana, Arial, Helvetica, sans-serif"> <strong>Titularidade
											do CPF/CNPJ da Negativação</strong> </font></td>
										</tr>
									</table>

									<table width="100%" cellpadding="0" cellspacing="0">
										<tr>
											<td colspan="4" bgcolor="#000000" height="1"></td>
										</tr>
										<tr>
											<td>
											<table width="100%" bgcolor="#99CCFF">
												<!--header da tabela interna -->
												<tr bgcolor="#99CCFF">
													<td width="40%" align="center"><strong>Titularidade do
													CPF/CNPJ da Negativação</strong></td>
													<td width="20%" align="center"><strong>Ordem</strong></td>
													<td width="20%" align="center"><strong>Coincidente</strong>
													</td>
												</tr>
											</table>
											</td>
										</tr>
										<tr>
											<td>
											<table width="100%" bgcolor="#99CCFF">
												<logic:present name="colecaoTitularidadeNegativacao">
													<%int cont = 0;%>
													<logic:iterate name="colecaoTitularidadeNegativacao"
														id="negativacaoCriterioCpfTipo"
														type="NegativacaoCriterioCpfTipo">

														<%cont = cont + 1;
															if (cont % 2 == 0) {%>
														<tr bgcolor="#cbe5fe">
															<%} else {%>
														<tr bgcolor="#FFFFFF">
															<%}%>
															<td width="40%" align="center"><bean:write
																name="negativacaoCriterioCpfTipo" property="cpfTipo.descricaoTipoCpf" />
															</td>
															<td width="20%" align="center"><bean:write
																name="negativacaoCriterioCpfTipo"
																property="numeroOrdemSelecao" /></td>
															<td width="20%" align="center"><logic:equal
																name="negativacaoCriterioCpfTipo"
																property="indicadorCoincidente"
																value="<%=ConstantesSistema.SIM.toString()%>">
																<input type="checkbox" name="negativacaoCriterioCpfTipo"
																	property="indicadorCoincidente" checked="true" disabled="true"/>
															</logic:equal> <logic:notEqual
																name="negativacaoCriterioCpfTipo"
																property="indicadorCoincidente"
																value="<%=ConstantesSistema.SIM.toString()%>">
																<input type="checkbox" name="negativacaoCriterioCpfTipo"
																	property="indicadorCoincidente" disabled="true"/>
															</logic:notEqual></td>

														</tr>

													</logic:iterate>
												</logic:present>
											</table>
											</td>
										</tr>
									</table>
									</td>
								</tr>
							</table>
							</div>

							</td>
						</tr>
					</table>
					</td>
				</tr>
			</table>




			<p>&nbsp;</p>

			<!-- DADOS DEBITO -->

			<table width="100%" cellpadding="0" cellspacing="0">
				<tr>
					<td height="31">
					<table width="100%" border="0" align="center">
						<tr bgcolor="#cbe5fe">
							<td align="center">

							<div id="layerHideDadosDebito" style="display:block">
							<table width="100%" border="0" bgcolor="#99CCFF">
								<tr bgcolor="#99CCFF">
									<td align="center"><span class="style2"> <a
										href="javascript:extendeTabela('DadosDebito',true);" /> <b>DADOS
									DO DÉBITO</b> </a> </span></td>
								</tr>
							</table>
							</div>

							<div id="layerShowDadosDebito" style="display:none">
							<table width="100%" border="0" bgcolor="#99CCFF">
								<tr bgcolor="#99CCFF">
									<td align="center"><span class="style2"> <a
										href="javascript:extendeTabela('DadosDebito',false);" /> <b>DADOS
									DO DÉBITO</b> </a> </span></td>
								</tr>

								<tr bgcolor="#cbe5fe">
									<td>
									<table border="0" width="100%">

										<tr>
											<td><strong>Negativador:</strong></td>
											<td><html:text property="negativador" size="50"
												maxlength="50" readonly="true"
												style="background-color:#EFEFEF; border:0; color: #000000;" />

											</td>
										</tr>

										<tr>
											<td height="10" colspan="4">
											<div align="right">
											<hr>
											</div>
											<div align="right"></div>
											</td>
										</tr>

										<tr>
											<td class="style3"><strong>Período de Referência do Débito:</strong>
											</td>
											<td colspan="3"><html:text property="referenciaInicial"
												maxlength="10" size="10" readonly="true"
												style="background-color:#EFEFEF; border:0;" /> a <html:text
												property="referenciaFinal" maxlength="10"
												style="background-color:#EFEFEF; border:0;" size="10"
												readonly="true" /></td>
										</tr>

										<tr>
											<td class="style3"><strong>Período de Vencimento do Débito:</strong>
											</td>
											<td colspan="3"><html:text property="vencimentoInicial"
												maxlength="10" size="10" readonly="true"
												style="background-color:#EFEFEF; border:0;" /> a <html:text
												property="vencimentoFinal" maxlength="10"
												style="background-color:#EFEFEF; border:0;" size="10"
												readonly="true" /></td>
										</tr>

										<tr>
											<td class="style3"><strong>Valor do Débito:</strong></td>
											<td colspan="3"><html:text property="valoMinimoDebito"
												maxlength="20" size="20" readonly="true"
												style="background-color:#EFEFEF; border:0;" /> a <html:text
												property="valoMaximoDebito" maxlength="20"
												style="background-color:#EFEFEF; border:0;" size="20"
												readonly="true" /></td>
										</tr>

										<tr>
											<td class="style3"><strong>Número de Contas:</strong></td>
											<td colspan="3"><html:text property="qtdMinimaContas"
												maxlength="10" size="10" readonly="true"
												style="background-color:#EFEFEF; border:0;" /> a <html:text
												property="qtdMaximaContas" maxlength="10"
												style="background-color:#EFEFEF; border:0;" size="10"
												readonly="true" /></td>
										</tr>

										<tr>
											<td><strong>Considerar Contas em Revisão:</strong></td>
											<td><html:radio property="indicadorContasRevisao"
												value="<%=ConstantesSistema.SIM.toString()%>" disabled="true"/><strong>Sim
											<html:radio property="indicadorContasRevisao"
												value="<%=ConstantesSistema.NAO.toString()%>" disabled="true"/>Não </strong>
											</td>
										</tr>

										<tr>
											<td><strong>Considerar Guias de Pagamento:</strong></td>
											<td><html:radio property="indicadorGuiaPagamento"
												value="<%=ConstantesSistema.SIM.toString()%>" disabled="true"/><strong>Sim
											<html:radio property="indicadorGuiaPagamento"
												value="<%=ConstantesSistema.NAO.toString()%>" disabled="true"/>Não </strong>
											</td>
										</tr>

										<tr>
											<td height="10" colspan="4">
											<div align="right">
											<hr>
											</div>
											<div align="right"></div>
											</td>
										</tr>

										<tr>
											<td><strong>Parcelamento em Atraso:</strong></td>
											<td><html:radio property="indicadorParcelamentoAtraso"
												value="<%=ConstantesSistema.SIM.toString()%>" disabled="true"/><strong>Sim
											<html:radio property="indicadorParcelamentoAtraso"
												value="<%=ConstantesSistema.NAO.toString()%>" disabled="true"/>Não </strong>
											</td>
										</tr>

										<tr>
											<td class="style3"><strong>Dias de Atraso de Parcelamento:</strong></td>
											<td colspan="3"><html:text
												property="numDiasAtrasoParcelamento" readonly="true"
												style="background-color:#EFEFEF; border:0;" size="5"
												maxlength="5" /></td>
										</tr>

										<tr>
											<td><strong>Recebeu Carta de Parcelamento em atraso:</strong></td>
											<td><html:radio property="indicadorRecCartaAtraso"
												value="<%=ConstantesSistema.SIM.toString()%>" disabled="true"/><strong>Recebeu
											<html:radio property="indicadorRecCartaAtraso"
												value="<%=ConstantesSistema.NAO.toString()%>" disabled="true"/>Não Recebeu
											</strong></td>
										</tr>

										<tr>
											<td class="style3"><strong>Dias em Atraso após Recebimento da
											Carta:</strong></td>
											<td colspan="3"><html:text
												property="numDiasAtrasoAposRecCarta" readonly="true"
												style="background-color:#EFEFEF; border:0;" size="5"
												maxlength="5" /></td>
										</tr>

									</table>
									</td>
								</tr>
							</table>
							</div>

							</td>
						</tr>
					</table>
					</td>
				</tr>
			</table>

			<p>&nbsp;</p>

			<!-- DADOS DO IMOVEL -->

			<table width="100%" cellpadding="0" cellspacing="0">
				<tr>
					<td height="31">
					<table width="100%" border="0" align="center">
						<tr bgcolor="#cbe5fe">
							<td align="center">

							<div id="layerHideDadosImovel" style="display:block">
							<table width="100%" border="0" bgcolor="#99CCFF">
								<tr bgcolor="#99CCFF">
									<td align="center"><span class="style2"> <a
										href="javascript:extendeTabela('DadosImovel',true);" /> <b>DADOS
									DO IMÓVEL</b> </a> </span></td>
								</tr>
							</table>
							</div>

							<div id="layerShowDadosImovel" style="display:none">
							<table width="100%" border="0" bgcolor="#99CCFF">
								<tr bgcolor="#99CCFF">
									<td align="center"><span class="style2"> <a
										href="javascript:extendeTabela('DadosImovel',false);" /> <b>DADOS
									DO IMÓVEL</b> </a> </span></td>
								</tr>

								<tr bgcolor="#cbe5fe">
									<td>
									<table border="0" width="100%">

										<tr>
											<td><strong>Negativador:</strong></td>
											<td><html:text property="negativador" size="50"
												maxlength="50" readonly="true"
												style="background-color:#EFEFEF; border:0; color: #000000;" />

											</td>
										</tr>

										<tr>
											<td height="10" colspan="4">
											<div align="right">
											<hr>
											</div>
											<div align="right"></div>
											</td>
										</tr>

										<tr>
											<td class="style3"><strong>Cliente:</strong></td>
											<td colspan="3"><html:text property="idCliente" size="4"
												maxlength="4" readonly="true"
												style="background-color:#EFEFEF; border:0;" /> <html:text
												property="nomeCliente" size="40" maxlength="40"
												style="background-color:#EFEFEF; border:0;" readonly="true" />
											</td>
										</tr>

										<tr>
											<td class="style3"><strong>Tipo de Relação com o Cliente:</strong>
											</td>
											<td colspan="3"><html:text property="tipoRelClie" size="30"
												maxlength="30" readonly="true"
												style="background-color:#EFEFEF; border:0;" /></td>
										</tr>

										<tr>
											<td height="10" colspan="4">
											<div align="right">
											<hr>
											</div>
											<div align="right"></div>
											</td>
										</tr>

										<tr>
											<td><strong>Imóvel com Sit. Especial de Cobrança:</strong></td>
											<td><html:radio property="indicadorEspecialCobranca"
												value="<%=ConstantesSistema.SIM.toString()%>" disabled="true"/><strong>Sim
											<html:radio property="indicadorEspecialCobranca"
												value="<%=ConstantesSistema.NAO.toString()%>" disabled="true"/>Não </strong>
											</td>
										</tr>

										<tr>
											<td><strong>Imóvel com Sit. de Cobrança:</strong></td>
											<td><html:radio property="indicadorSituacaoCobranca"
												value="<%=ConstantesSistema.SIM.toString()%>" disabled="true"/><strong>Sim
											<html:radio property="indicadorSituacaoCobranca"
												value="<%=ConstantesSistema.NAO.toString()%>" disabled="true"/>Não </strong>
											</td>
										</tr>

										<tr>
											<td height="10" colspan="4">
											<div align="right">
											<hr>
											</div>
											<div align="right"></div>
											</td>
										</tr>
									</table>

									<table width="100%" cellpadding="0" cellspacing="0">
										<tr>
											<td colspan="4" bgcolor="#000000" height="1"></td>
										</tr>
										<tr>
											<td>
											<table width="100%" bgcolor="#99CCFF">
												<!--header da tabela interna -->
												<tr bgcolor="#99CCFF">
													<td width="100%" align="center">
														<strong>Subcategoria</strong>
													</td>
													
												</tr>
											</table>
											</td>
										</tr>
										<tr>
											<td>
											<table width="100%" bgcolor="#99CCFF">
												<logic:present name="colecaoSubcategoria">
													<%int cont1 = 0;%>
													<logic:iterate name="colecaoSubcategoria"
														id="subcategoria" type="Subcategoria">

														<%cont1 = cont1 + 1;
															if (cont1 % 2 == 0) {%>
														<tr bgcolor="#cbe5fe">
															<%} else {%>
														<tr bgcolor="#FFFFFF">
															<%}%>
															<td width="40%" align="center">
																<bean:write name="subcategoria" property="descricao" />
															</td>
														</tr>

													</logic:iterate>
												</logic:present>
											</table>
											</td>
										</tr>
									</table>
								<table width="100%" border="0" bgcolor="#cbe5fe">
									<tr>
										<td>
											<p align="left">&nbsp;</p>
											<p align="left">&nbsp;</p>
										</td>
									</tr>
								</table>

								<table width="100%" cellpadding="0" cellspacing="0">
									<tr>
										<td colspan="4" bgcolor="#000000" height="1"></td>
									</tr>
									<tr>
										<td>
										<table width="100%" bgcolor="#99CCFF">
											<!--header da tabela interna -->
											<tr bgcolor="#99CCFF">
												<td width="100%" align="center">
													<strong>Perfil do Imóvel</strong>
												</td>
												
											</tr>
										</table>
										</td>
									</tr>
									<tr>
										<td>
										<table width="100%" bgcolor="#99CCFF">
											<logic:present name="colecaoPerfilImovel">
												<%int cont2 = 0;%>
												<logic:iterate name="colecaoPerfilImovel"
													id="perfilImovel"
													type="ImovelPerfil">

													<%cont2 = cont2 + 1;
														if (cont2 % 2 == 0) {%>
													<tr bgcolor="#cbe5fe">
														<%} else {%>
													<tr bgcolor="#FFFFFF">
														<%}%>
														<td width="100%" align="center">
															<bean:write name="perfilImovel" property="descricao" />
														</td>
													</tr>

												</logic:iterate>
											</logic:present>
										</table>
										</td>
									</tr>
								</table>

								<table width="100%" border="0" bgcolor="#cbe5fe">
									<tr>
										<td>
											<p align="left">&nbsp;</p>
											<p align="left">&nbsp;</p>
										</td>
									</tr>
								</table>

								<table width="100%" cellpadding="0" cellspacing="0">
									<tr>
										<td colspan="4" bgcolor="#000000" height="1"></td>
									</tr>
									<tr>
										<td>
										<table width="100%" bgcolor="#99CCFF">
											<!--header da tabela interna -->
											<tr bgcolor="#99CCFF">
												<td width="100%" align="center">
													<strong>Tipo de Cliente</strong>
												</td>
											</tr>
										</table>
										</td>
									</tr>
									<tr>
										<td>
										<table width="100%" bgcolor="#99CCFF">
											<logic:present name="colecaoTipoCliente">
												<%int cont3 = 0;%>
												<logic:iterate name="colecaoTipoCliente"
													id="tipoCliente"
													type="ClienteTipo">

													<%cont3 = cont3 + 1;
													if (cont3 % 2 == 0) {%>
													<tr bgcolor="#cbe5fe">
														<%} else {%>
													<tr bgcolor="#FFFFFF">
														<%}%>
														<td width="100%" align="center">
															<bean:write name="tipoCliente" property="descricao" />
														</td>
													</tr>

												</logic:iterate>
											</logic:present>
										</table>
										</td>
									</tr>
								</table>










								</td>
								</tr>
							</table>
							</div>
							</td>
						</tr>
					</table>
					</td>
				</tr>
			</table>

			<p>&nbsp;</p>

			<!-- DADOS DA LOCALIZACAO -->

			<table width="100%" cellpadding="0" cellspacing="0">
				<tr>
					<td height="31">
					<table width="100%" border="0" align="center">
						<tr bgcolor="#cbe5fe">
							<td align="center">

							<div id="layerHideDadosLocalizacao" style="display:block">
							<table width="100%" border="0" bgcolor="#99CCFF">
								<tr bgcolor="#99CCFF">
									<td align="center"><span class="style2"> <a
										href="javascript:extendeTabela('DadosLocalizacao',true);" />
									<b>DADOS DA LOCALIZAÇÃO</b> </a> </span></td>
								</tr>
							</table>
							</div>

							<div id="layerShowDadosLocalizacao" style="display:none">
							<table width="100%" border="0" bgcolor="#99CCFF">
								<tr bgcolor="#99CCFF">
									<td align="center"><span class="style2"> <a
										href="javascript:extendeTabela('DadosLocalizacao',false);" />
									<b>DADOS DA LOCALIZAÇÃO</b> </a> </span></td>
								</tr>

								<tr bgcolor="#cbe5fe">
									<td>
									<table border="0" width="100%">

										<tr>
											<td><strong>Negativador:</strong></td>
											<td><html:text property="negativador" size="50"
												maxlength="50" readonly="true"
												style="background-color:#EFEFEF; border:0; color: #000000;" />

											</td>
										</tr>

										<tr>
											<td height="10" colspan="4">
											<div align="right">
											<hr>
											</div>
											<div align="right"></div>
											</td>
										</tr>


										<tr>
											<td><strong>Grupo de Cobrança:</strong></td>
											<logic:present name="colecaoGrupoCobranca" scope="session">
												<td><html:select property="grupoCobranca" multiple="true">
													<html:options collection="colecaoGrupoCobranca"
														labelProperty="descricao" property="id"
														style="background-color:#EFEFEF; border:0;" />
												</html:select></td>
											</logic:present>
											<logic:notPresent name="colecaoGrupoCobranca" scope="session">
												<td><html:text maxlength="30" readonly="true"
													property="grupoCobranca"
													style="background-color:#EFEFEF; border:0;" size="30" /></td>
											</logic:notPresent>
										</tr>

										<tr>
											<td><strong>Gerência Regional:</strong></td>
											<logic:present name="colecaoGerenciaRegional" scope="session">
												<td><html:select property="gerenciaRegional" multiple="true">
													<html:options collection="colecaoGerenciaRegional"
														labelProperty="nome" property="id"
														style="background-color:#EFEFEF; border:0;" />
												</html:select></td>
											</logic:present>
											<logic:notPresent name="colecaoGerenciaRegional"
												scope="session">
												<td><html:text maxlength="30" readonly="true"
													property="gerenciaRegional"
													style="background-color:#EFEFEF; border:0;" size="30" /></td>
											</logic:notPresent>
										</tr>
										<tr>
											<td><strong>Unidade Negócio:</strong></td>
											<logic:present name="colecaoUnidadeNegocio" scope="session">
												<td><html:select property="unidadeNegocio" multiple="true">
													<html:options collection="colecaoUnidadeNegocio"
														labelProperty="nome" property="id"
														style="background-color:#EFEFEF; border:0;" />
												</html:select></td>
											</logic:present>
											<logic:notPresent name="colecaoUnidadeNegocio"
												scope="session">
												<td><html:text maxlength="30" readonly="true"
													property="unidadeNegocio"
													style="background-color:#EFEFEF; border:0;" size="30" /></td>
											</logic:notPresent>
										</tr>
										<tr>
											<td><strong>Localidade Pólo:</strong></td>
											<logic:present name="colecaoEloPolo" scope="session">
												<td><html:select property="eloPolo" multiple="true">
													<html:options collection="colecaoEloPolo"
														labelProperty="descricao" property="id"
														style="background-color:#EFEFEF; border:0;" />
												</html:select></td>
											</logic:present>
											<logic:notPresent name="colecaoEloPolo" scope="session">
												<td><html:text maxlength="30" readonly="true"
													property="eloPolo"
													style="background-color:#EFEFEF; border:0;" size="30" /></td>
											</logic:notPresent>
										</tr>

										<tr>
											<td height="10" colspan="4">
											<div align="right">
											<hr>
											</div>
											<div align="right"></div>
											</td>
										</tr>

										<tr>
											<td class="style3"><strong>Localidade:</strong></td>
											<td colspan="3"><html:text property="locInicial"
												maxlength="30" size="30" readonly="true"
												style="background-color:#EFEFEF; border:0;" /> a <html:text
												property="locFinal" maxlength="30"
												style="background-color:#EFEFEF; border:0;" size="30"
												readonly="true" /></td>
										</tr>

										<tr>
											<td class="style3"><strong>Setor Comercial:</strong></td>
											<td colspan="3"><html:text property="setComInicial"
												maxlength="30" size="30" readonly="true"
												style="background-color:#EFEFEF; border:0;" /> a <html:text
												property="setComFinal" maxlength="30"
												style="background-color:#EFEFEF; border:0;" size="30"
												readonly="true" /></td>
										</tr>

									</table>
									</td>
								</tr>
							</table>
							</div>

							</td>
						</tr>
					</table>
					</td>
				</tr>
			</table>

			<p>&nbsp;</p>

			<table width="100%" cellpadding="0" cellspacing="0">
				<tr>
					<logic:notPresent name="popup">
						<td height="17">
							<div align="left">
								<input name="Button" type="button"
									   class="bottonRightCol" value="Voltar"
							    	   onClick="javascript:history.back();">
						    </div>
						</td>					
					</logic:notPresent>
					<td>
					  <div align="right"><input name="ButtonFechar" type="button"
						  class="bottonRightCol" value="Fechar"
						  onclick="chamarPaginaAnterior();window.close();"></div>
					</td>									
				</tr>
			</table>
			<p>&nbsp;</p>
			</td>
		</tr>
	</table>
</body>
</html:form>
</html:html>
