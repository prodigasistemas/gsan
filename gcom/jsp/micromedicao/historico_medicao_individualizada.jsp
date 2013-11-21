<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<head>
<html:html>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">

<%@ page import="gcom.util.ConstantesSistema"%>
<%@ page import="gcom.micromedicao.consumo.LigacaoTipo"%>

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false" formName="ConsultarHistoricoMedicaoIndividualizadaActionForm" />
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript">
<!-- Begin
function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {

    var form = document.forms[0];
    if (tipoConsulta == 'imovel') {
        form.codigoImovel.value = codigoRegistro;
        form.descricaoImovel.value = descricaoRegistro;
        form.descricaoImovel.style.color = "#000000";
    }
    redirecionarSubmit("exibirConsultarHistoricoMedicaoIndividualizadaAction.do?limparColecoes=OK");
}
 
function limparForm(){

 	var form = document.forms[0];
 	
 	for (var i=0;i < form.elements.length;i++){
		var elemento = form.elements[i];
		if (elemento.type == "text"){
			elemento.value = "";
		}
	}
	
	redirecionarSubmit("exibirConsultarHistoricoMedicaoIndividualizadaAction.do?limparForm=OK");
}
 
 
function consultar(){

	var form = document.forms[0];
	if (validateConsultarHistoricoMedicaoIndividualizadaActionForm(form) &&
	verificaAnoMesMensagemPersonalizada(form.mesAno,"Mês/Ano do Faturamento inválido.")){
	    form.action = 'exibirConsultarHistoricoMedicaoIndividualizadaAction.do'
    	form.submit();

	}
}


-->
</script>
</head>

<body leftmargin="5" topmargin="5">
<html:form
	action="/exibirConsultarHistoricoMedicaoIndividualizadaAction"
	name="ConsultarHistoricoMedicaoIndividualizadaActionForm"
	type="gcom.gui.micromedicao.ConsultarHistoricoMedicaoIndividualizadaActionForm"
	method="post"
	onsubmit="return validateConsultarHistoricoMedicaoIndividualizadaActionForm(this);">

	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>

	<table width="770" border="0" cellspacing="5" cellpadding="0">
		<tr>
			<td width="115" valign="top" class="leftcoltext">

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

			<td valign="top" class="centercoltext"><!--Início Tabela Reference a Páginação da Tela de Processo-->
				<table height="100%">
					<tr>
						<td></td>
					</tr>
				</table>

				<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
					<tr>
						<td width="11"><img border="0" src="imagens/parahead_left.gif" /></td>
						<td class="parabg">Consultar Hist&oacute;rico de Medi&ccedil;&atilde;o Individualizada</td>
						<td width="11" valign="top"><img border="0"	src="imagens/parahead_right.gif" /></td>
					</tr>
				</table>
			
				<p>&nbsp;</p>
				
				<table width="598" border="0">
					<tr>
						<td>Para consultar o hist&oacute;rico de medi&ccedil;&atilde;o individualizada, informe os dados abaixo:</td>
						<td align="right">
							<a href="javascript: abrirPopupHelp('/gsan/help/help.jsp?mapIDHelpSet=imovelConsultarHistoricoMedicaoIndividualizada', 500, 700);">
								<span style="font-weight: bold">
									<font color="#3165CE">Ajuda</font>
								</span>
							</a>
						</td>
					</tr>
				</table>
				
				<table width="598" border="0">
					<tr>
						<td class="style1"><strong>Matr&iacute;cula do Im&oacute;vel Condom&iacute;nio:</strong><strong><font color="#FF0000">*</font></strong></td>
						<td class="style1" colspan="2">
							<div align="left">
								<html:text property="codigoImovel" maxlength="9" size="9" onkeypress="validaEnter(event, 'exibirConsultarHistoricoMedicaoIndividualizadaAction.do?pesquisaImovel=OK', 'codigoImovel');" />
								<a href="javascript:abrirPopup('exibirPesquisarImovelAction.do?pesquisarImovelCondominio=OK', 400, 800);">
									<img width="23" height="21" src="<bean:message key='caminho.imagens'/>pesquisa.gif" border="0" />
								</a>
								
								<logic:present name="matriculaInexistente" scope="request">
									<html:text property="descricaoImovel" size="25" maxlength="25" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000" />
								</logic:present> 
								<logic:notPresent name="matriculaInexistente" scope="request">
									<html:text property="descricaoImovel" size="25" maxlength="25" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" />
								</logic:notPresent> 
								
								<img border="0"	src="<bean:message key='caminho.imagens'/>limparcampo.gif" onclick="limparForm();" style="cursor: hand;" /> &nbsp;
							</div>
						</td>
					</tr>
					<tr>
						<td width="220" class="style1"><strong>M&ecirc;s/Ano do	Faturamento:</strong><strong><font color="#FF0000">*</font></strong></td>
						<td width="310" class="style1">
							<html:text property="mesAno" size="10" maxlength="7" onkeyup="javascript:mascaraAnoMes(this, event);" /> mm/aaaa
						</td>
	
						<td width="65" align="left" class="style1">
							<gsan:controleAcessoBotao name="Button" value="Consultar" onclick="javascript:consultar();"	url="exibirConsultarHistoricoMedicaoIndividualizadaAction.do" />
						</td>
					</tr>
					
					
					<tr>
						<td><strong>Tipo de Ligação:</strong><strong><font color="#FF0000">*</font></strong></td>
						<td>
							<html:select property="idTipoLigacao">
								<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp; </html:option>
							
								<html:options collection="colecaoLigacaoTipo" labelProperty="descricao" property="id" />
							</html:select>
						</td>
					</tr>
					
					
					
				</table>

				<table width="590" border="0" bgcolor="#79bbfd" align="center" cellpadding="0" cellspacing="0">
					<tr bgcolor="#79bbfd" height="18">
						<td align="center" class="style11"><strong>Dados do Im&oacute;vel Condom&iacute;nio</strong></td>
					</tr>
				</table>
				
				
				<table width="590" border="0" align="center" cellpadding="0" cellspacing="0">
					<tr>
						<td height="22" class="style1">
							
							<table width="590" border="0" align="center" cellpadding="0" cellspacing="0">
								<tr>
									<td height="0">
										<table width="590" bgcolor="#90c7fc">
											<tr bgcolor="#90c7fc">
												<td width="90%">
													<div align="center" class="style7"><strong>Endere&ccedil;o do Im&oacute;vel</strong></div>
												</td>
											</tr>
											
											<logic:present name="dadosImovel" scope="session">
												<logic:iterate name="dadosImovel" id="imovel">
													<tr bgcolor="#FFFFFF">
														<td width="90%">
															<div align="center">
																<bean:write name="imovel" property="enderecoFormatado" />
															</div>
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
					
					<tr>
						<td></td>
					</tr>
					
					<tr>
						<td></td>
					</tr>
	
					<tr>
						<td height="31">
							<table width="590" bgcolor="#99CCFF">
								<tr bgcolor="#99CCFF">
									<td width="22%">
										<div align="center" class="style9"><strong>Situa&ccedil;&atilde;o de &Aacute;gua</strong></div>
									</td>
									<td width="30%">
										<div align="center" class="style9"><strong>Situa&ccedil;&atilde;o de Esgoto</strong></div>
									</td>
									<td width="30%">
										<div align="center"><strong>Tipo de Rateio</strong></div>
									</td>
									<td width="18%">
										<div align="center"><strong>Qtd. Imov. Vinc.</strong></div>
									</td>
								</tr>
								
								<logic:present name="dadosImovel" scope="session">
									<logic:iterate name="dadosImovel" id="imovel">
										<tr bgcolor="#FFFFFF">
											<td height="17">
												<div align="center">
													<logic:present name="imovel" property="ligacaoAguaSituacao">
														<bean:define name="imovel" property="ligacaoAguaSituacao" id="ligacaoAguaSituacao" />
														<bean:write name="ligacaoAguaSituacao" property="descricao" />
													</logic:present>
												</div>
											</td>
											
											<td height="17">
												<div align="center">
													<logic:present name="imovel" property="ligacaoEsgotoSituacao">
														<bean:define name="imovel" property="ligacaoEsgotoSituacao"	id="ligacaoEsgotoSituacao" />
														<bean:write name="ligacaoEsgotoSituacao" property="descricao" />
													</logic:present>
												</div>
											</td>
											
											<td height="17">
												<div align="center">
													<logic:present name="imovel" property="ligacaoAgua">
														<bean:define name="imovel" property="ligacaoAgua" id="ligacaoAgua" />
														<logic:present name="ligacaoAgua" property="hidrometroInstalacaoHistorico">
															<bean:define name="ligacaoAgua"	property="hidrometroInstalacaoHistorico" id="hidrometroInstalacaoHistorico" />
															<logic:present name="hidrometroInstalacaoHistorico"	property="rateioTipo">
																<bean:define name="hidrometroInstalacaoHistorico" property="rateioTipo" id="rateioTipo" />
																<bean:write name="rateioTipo" property="descricao" />
															</logic:present>
														</logic:present>
													</logic:present>
												</div>
											</td>
											
											
											
											
											<td height="17">
												<div align="center">
													<html:text readonly="true" style="background-color:#FFFFFF; border:0; text-align:center" property="quantidadeImoveisVinculados" />
												</div>
											</td>
											
										</tr>
									</logic:iterate>
								</logic:present>
		
							</table>
						</td>
					</tr>
				</table>

				<p>&nbsp;</p>
				<table width="590" border="0" bgcolor="#79bbfd" align="center" cellpadding="0" cellspacing="0">
					<tr bgcolor="#79bbfd" height="18">
						<td align="center" class="style11"><strong>Hist&oacute;rico de Medi&ccedil;&atilde;o Individualizada</strong></td>
					</tr>
				</table>


				<table width="590" border="0" align="center" cellpadding="0" cellspacing="0">
					<tr>
						<td height="22" class="style1">
							<table width="590" border="0" align="center" cellpadding="0" cellspacing="0">
								<tr>
									<td height="0">
		
										<table width="590" bgcolor="#90c7fc">
											<tr bgcolor="#90c7fc">
												<td width="11%">
													<div align="center"><strong>Matr&iacute;cula do Im&oacute;vel</strong></div>
												</td>
												<td width="37%">
													<div align="center"><strong>Nome do Cliente Usu&aacute;rio</strong></div>
												</td>
												<td width="9%">
													<div align="center"><strong>Tipo de Consumo</strong></div>
												</td>
												<td width="11%">
													<div align="center"><strong>Consumo Imóvel </strong></div>
												</td>
												<td width="11%">
													<div align="center"><strong>Consumo Rateado </strong></div>
												</td>
												
												<td width="11%">
													<div align="center"><strong>Consumo Cobrado </strong></div>
												</td>
													
												<%--<logic:equal name="ConsultarHistoricoMedicaoIndividualizadaActionForm" property="idTipoLigacao" value="<%=""+LigacaoTipo.LIGACAO_AGUA%>">
													<td width="11%">
														<div align="center"><strong>Cons.&Aacute;gua Cobrado </strong></div>
													</td>
												</logic:equal>
												
												<logic:equal name="ConsultarHistoricoMedicaoIndividualizadaActionForm" property="idTipoLigacao" value="<%=""+LigacaoTipo.LIGACAO_ESGOTO%>">
													<td width="10%">
														<div align="center"><strong>Consumo Esgoto</strong></div>
													</td>
												</logic:equal>
											</tr> --%>
			
											<%String cor = "#cbe5fe";%>
											<%String styleColor = "";%>
											<%int contador = 0;	%>
											
											<logic:present	name="colecaoConsultarHistoricoMedicaoIndividualizada" scope="session">
												<logic:iterate name="colecaoConsultarHistoricoMedicaoIndividualizada" id="consultarHistoricoMedicaoIndividualizadaHelper">
			
													<%if (contador == 0) {
													  if (cor.equalsIgnoreCase("#cbe5fe")) {
														cor = "#FFFFFF";%>
													<tr bgcolor="#FFFFFF">
														<%} else { 
															cor = "#cbe5fe";%>
													<tr bgcolor="#cbe5fe">
														<%}%>
														
														<td width="11%">
															<div align="center">
																<b><bean:write	name="consultarHistoricoMedicaoIndividualizadaHelper" property="matriculaImovel" /></b>
															</div>
														</td>
														
														<td width="37%">
															<div align="left">
																<b><bean:write name="consultarHistoricoMedicaoIndividualizadaHelper" property="nomeClienteUsuario" /></b>
															</div>
														</td>
														
														<td width="9%">
															<div align="center">
																<b><bean:write name="consultarHistoricoMedicaoIndividualizadaHelper" property="tipoConsumo" /></b>
															</div>
														</td>
														
														<td width="11%">
															<div align="center">
																<b><bean:write name="consultarHistoricoMedicaoIndividualizadaHelper" property="consumoImovel" /></b>
															</div>
														</td>

														<td width="11%">
															<div align="center">
																<b><bean:write name="consultarHistoricoMedicaoIndividualizadaHelper" property="consumoRateio" /></b>
															</div>
														</td>

														<logic:equal name="ConsultarHistoricoMedicaoIndividualizadaActionForm" property="idTipoLigacao" value="<%=""+LigacaoTipo.LIGACAO_AGUA%>">
															<td width="11%">
																<div align="center">
																	<b><bean:write name="consultarHistoricoMedicaoIndividualizadaHelper" property="consumoAguaFaturado" /></b>
																</div>
															</td>
														</logic:equal>
														
														<logic:equal name="ConsultarHistoricoMedicaoIndividualizadaActionForm" property="idTipoLigacao" value="<%=""+LigacaoTipo.LIGACAO_ESGOTO%>">
															<td width="10%">
																<div align="center">
																	<b><bean:write name="consultarHistoricoMedicaoIndividualizadaHelper" property="consumoEsgoto" /></b>
																</div>
															</td>
														</logic:equal>
													</tr>
													<%} else {
														styleColor = "";
													if (cor.equalsIgnoreCase("#FFFFFF")) {
														
													cor = "#cbe5fe";%>
													<tr bgcolor="#cbe5fe">
														
														<%} else {
														cor = "#FFFFFF";%>
													<tr bgcolor="#FFFFFF">
														<%}%>
														
														<logic:equal name="consultarHistoricoMedicaoIndividualizadaHelper" property="indicadorImovelAreaComum" value="<%=""+ConstantesSistema.INDICADOR_USO_ATIVO%>">
															<% styleColor = "color:#F00;"; %>
														</logic:equal>

														<td width="11%">
															<div align="center" style="<%=styleColor %>">
																<bean:write name="consultarHistoricoMedicaoIndividualizadaHelper" property="matriculaImovel" />
															</div>
														</td>
														
														<td width="37%">
															<div align="left" style="<%=styleColor %>">
																<bean:write	name="consultarHistoricoMedicaoIndividualizadaHelper" property="nomeClienteUsuario" />
															</div>
														</td>
														
														<td width="9%">
															<div align="center" style="<%=styleColor %>">
																<bean:write	name="consultarHistoricoMedicaoIndividualizadaHelper" property="tipoConsumo" />
															</div>
														</td>
														
														<td width="11%">
															<div align="center" style="<%=styleColor %>">
																<bean:write	name="consultarHistoricoMedicaoIndividualizadaHelper" property="consumoImovel" />
															</div>
														</td>
														
														<td width="11%">
															<div align="center" style="<%=styleColor %>">
																<bean:write	name="consultarHistoricoMedicaoIndividualizadaHelper" property="consumoRateio" />
															</div>
														</td>
														
														<logic:equal name="ConsultarHistoricoMedicaoIndividualizadaActionForm" property="idTipoLigacao" value="<%=""+LigacaoTipo.LIGACAO_AGUA%>">
															<td width="11%">
																<div align="center" style="<%=styleColor %>">
																	<bean:write	name="consultarHistoricoMedicaoIndividualizadaHelper" property="consumoAguaFaturado" />
																</div>
															</td>
														</logic:equal>
														
														<logic:equal name="ConsultarHistoricoMedicaoIndividualizadaActionForm" property="idTipoLigacao" value="<%=""+LigacaoTipo.LIGACAO_ESGOTO%>">
															<td width="10%">
																<div align="center" style="<%=styleColor %>">
																	<bean:write	name="consultarHistoricoMedicaoIndividualizadaHelper" property="consumoEsgoto" />
																</div>
															</td>
														</logic:equal>
														
													</tr>
			
													<%}
													contador = contador + 1;
													%>
													
												</logic:iterate>
											</logic:present>
										</table>
									</td>
								</tr>
							</table>

							<table border="0">
								<tr>
									<td align="left">
										<input type="button" name="limpar" value="Limpar" class="bottonRightCol" onclick="limparForm();"> 
										<input type="button" name="Button" class="bottonRightCol" value="Cancelar" onclick="window.location.href='/gsan/telaPrincipal.do'" />
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
<script language="JavaScript"> 
	document.forms[0].codigoImovel.focus();
</script>
</html:html>
