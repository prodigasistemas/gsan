<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<%@ page isELIgnored="false"%>
<%@ page import="gcom.cadastro.tarifasocial.bean.TarifaSocialHelper"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>

<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
<html:javascript staticJavascript="false"
	formName="ManterTarifaSocialActionForm" dynamicJavascript="true" />

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>

<SCRIPT LANGUAGE="JavaScript">
function verificarExclusao(idTarifaSocial, count) {
	var form = document.forms[0];
	if (form.idRegistrosRemocao[count].checked) {
		
		form.idRegistrosRemocao[count].checked = false;
		abrirPopup('exibirRemoverTarifaSocialAction.do?idTarifaSocial=' + idTarifaSocial, 185, 440);
		
	} else {

		
		
		form.idRegistrosRemocao[count].checked = true;
		form.action = 'exibirManterTarifaSocialDadosMultiplasEconomiasAction.do?idTarifaSocial=' + idTarifaSocial;
		submeterFormPadrao(form);
		
	}
}

function abrirPopupExclusao() {
	abrirPopup('exibirRemoverTarifaSocialImovelAnteriorAction.do?manter=true', 205, 540);
}



	</SCRIPT>

</head>

<logic:present name="abrirPopupExclusao">
	<body leftmargin="5" topmargin="5" onload="javascript:abrirPopupExclusao();">
</logic:present>

<logic:notPresent name="abrirPopupExclusao">
	<body leftmargin="5" topmargin="5">
</logic:notPresent>

<html:form action="/manterTarifaSocialWizardAction" method="post">


	<jsp:include
		page="/jsp/util/wizard/navegacao_abas_wizard_valida_avancar.jsp?numeroPagina=2" />


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
			<td valign="top" class="centercoltext">

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
					<td class="parabg">Manter Tarifa Social</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>

			<table width="100%" border="0" dwcopytype="CopyTableRow">
				<tr>
					<td width="100%" height="17">
						<b>Rela&ccedil;&atilde;o de clientes usu&aacute;rios por economia</b>
					</td>
				</tr>
				<tr>
					<td height="200">
					<div style="width: 100%; height: 100%; overflow: auto;">
						<logic:present name="colecaoTarifaSocialHelper">
						<% int count = 0; %>
						<logic:iterate id="tarifaSocialHelper" name="colecaoTarifaSocialHelper" type="TarifaSocialHelper">
							<table width="100%">
								<tr>
									<td height="82">

									<table width="100%" bgcolor="#99CCFF">
										<tr bordercolor="#FFFFFF" bgcolor="#0066FF">
											<td height="0" colspan="2" bgcolor="#99CCFF">
												<div align="center">	
													<strong>Cliente Usuário</strong>
												</div>
											</td>
											<td width="26%" height="0" bgcolor="#99CCFF">
												<div align="center">
													<strong>Complemento Endereço</strong>
												</div>
											</td>
											<td height="0" bgcolor="#99CCFF">
												<div align="center">
													<strong>CPF</strong>
												</div>
											</td>
										</tr>
										<tr bordercolor="#FFFFFF" bgcolor="#FFFFFF">
											<td colspan="2">
												<div align="left">
													<logic:present  name="tarifaSocialHelper" 
																	property="tarifaSocialDadoEconomia.tarifaSocialExclusaoMotivo">${tarifaSocialHelper.clienteImovelEconomia.cliente.nome}
													</logic:present>
												
													<logic:notPresent name="tarifaSocialHelper"
																	  property="tarifaSocialDadoEconomia.tarifaSocialExclusaoMotivo">
														<logic:present name="pesquisaImovel" 
																	   scope="session">
															<a href="javascript:abrirPopup('exibirMotivoRevisaoTarifaSocialAction.do?idTarifaSocial=${tarifaSocialHelper.tarifaSocialDadoEconomia.id}', 190, 440);">${tarifaSocialHelper.clienteImovelEconomia.cliente.nome}</a>
														</logic:present>
														<logic:notPresent name="pesquisaImovel" scope="session">
															<a href="javascript:abrirPopup('exibirAtualizarDadosTarifaSocialAction.do?idTarifaSocial=${tarifaSocialHelper.tarifaSocialDadoEconomia.id}' , 700, 600);">${tarifaSocialHelper.clienteImovelEconomia.cliente.nome}</a>
														</logic:notPresent>
													</logic:notPresent>
												</div>
											</td>
											<td align="left">
												<logic:present  name="tarifaSocialHelper"
																property="clienteImovelEconomia.imovelEconomia">
													<bean:write name="tarifaSocialHelper"
																property="clienteImovelEconomia.imovelEconomia.complementoEndereco" />
												</logic:present> 
												<logic:notPresent name="tarifaSocialHelper"
																  property="clienteImovelEconomia.imovelEconomia">
								&nbsp;
												</logic:notPresent>
											</td>
											<td align="center">
												<logic:present  name="tarifaSocialHelper"
																property="clienteImovelEconomia.cliente.cpf">
													<bean:write name="tarifaSocialHelper"
																property="clienteImovelEconomia.cliente.cpfFormatado" />
												</logic:present> 
												<logic:present  name="clienteImovel"
																property="cliente.cpf">
										&nbsp;
												</logic:present>
											</td>
										</tr>
										
										<tr bordercolor="#FFFFFF" bgcolor="#0066FF">
											<td width="23%" height="0" bgcolor="#99CCFF">
												<div align="center">
													<strong>RG</strong>
												</div>
											</td>
											<td width="20%" bgcolor="#99CCFF">
												<div align="center">
													<strong>Data Emiss&atilde;o</strong>
												</div>
											</td>
											<td bgcolor="#99CCFF">
												<div align="center">
													<strong>&Oacute;rg&atilde;o Expedidor</strong>
												</div>
											</td>
											<td bgcolor="#99CCFF">
												<div align="center"></div>
												<div align="center">
													<strong>UF</strong>
												</div>
											</td>
										</tr>
										
										<tr bordercolor="#FFFFFF" bgcolor="#FFFFFF">
											<td align="right">
												<logic:present  name="tarifaSocialHelper"
																property="clienteImovelEconomia.cliente.rg">
													<bean:write name="tarifaSocialHelper"
																property="clienteImovelEconomia.cliente.rg" />
												</logic:present> 
												<logic:present  name="tarifaSocialHelper"
																property="clienteImovelEconomia.cliente.rg">
										&nbsp;
												</logic:present>
											</td>
											<td align="left">
												<logic:present  name="tarifaSocialHelper"
																property="clienteImovelEconomia.cliente.dataEmissaoRg">
													<bean:write name="tarifaSocialHelper"
																property="clienteImovelEconomia.cliente.dataEmissaoRg"
																formatKey="date.format" />
												</logic:present> 
												<logic:present  name="tarifaSocialHelper"
																property="clienteImovelEconomia.cliente.dataEmissaoRg">
													&nbsp;
												</logic:present>
											</td>
											<td>
												<div align="right">
													<logic:present name="tarifaSocialHelper"
																   property="clienteImovelEconomia.cliente.orgaoExpedidorRg">
														<bean:write name="tarifaSocialHelper"
																	property="clienteImovelEconomia.cliente.orgaoExpedidorRg.descricaoAbreviada" />
													</logic:present> 
													<logic:present name="tarifaSocialHelper"
														property="clienteImovelEconomia.cliente.orgaoExpedidorRg">
														&nbsp;
													</logic:present>
												</div>
											</td>
											<td>
												<div align="left"></div>
												<div align="left">
													<logic:present  name="tarifaSocialHelper"
																	property="clienteImovelEconomia.cliente.unidadeFederacao">
														<bean:write name="tarifaSocialHelper"
																	property="clienteImovelEconomia.cliente.unidadeFederacao.sigla" />
													</logic:present> 
													<logic:present  name="tarifaSocialHelper"
																	property="clienteImovelEconomia.cliente.unidadeFederacao">
												&nbsp;
													</logic:present>
												</div>
											</td>
										</tr>
									</table>
									</td>
								</tr>
								<tr>
									<td height="44">

									<table width="100%" bgcolor="#99CCFF">
										<tr bordercolor="#FFFFFF" bgcolor="#E6EFFF">

											<logic:present  name="tarifaSocialHelper"
															property="tarifaSocialDadoEconomia.id">
												<td width="9%" bgcolor="#99CCFF">
													<div align="center">
														<strong>Remover</strong>
													</div>
												</td>
											</logic:present>

											<td width="23%" bgcolor="#99CCFF">
												<div align="center">
													<strong>Cart&atilde;o do Prog.Social</strong>
												</div>
											</td>
											<td width="23%" bgcolor="#99CCFF">
												<div align="center">
													<strong>Tipo Cart&atilde;o Prog.Social</strong>
												</div>
											</td>
											<td width="15%" bgcolor="#99CCFF">
												<div align="center">
													<strong>Renda Familiar</strong>
												</div>
											</td>
											<td width="23%" bgcolor="#99CCFF">
												<div align="center">
													<strong>Tipo Renda Familiar</strong>
												</div>
											</td>
										</tr>
										<tr bordercolor="#FFFFFF" bgcolor="#FFFFFF">
											<logic:present  name="tarifaSocialHelper"
															property="tarifaSocialDadoEconomia.tarifaSocialExclusaoMotivo">
												<td align="center">
													<input  type="checkbox"
															name="idRegistrosRemocao"
															onclick="javascript:verificarExclusao('${tarifaSocialHelper.tarifaSocialDadoEconomia.id}', <%= count %>)"
															checked
															value="<bean:write name="tarifaSocialHelper" property="tarifaSocialDadoEconomia.id"/>" />
												</td>
											</logic:present>
											
											<logic:notPresent name="tarifaSocialHelper"
															  property="tarifaSocialDadoEconomia.tarifaSocialExclusaoMotivo">
												<td align="center">
													<input  type="checkbox"
															name="idRegistrosRemocao"
															onclick="javascript:verificarExclusao('${tarifaSocialHelper.tarifaSocialDadoEconomia.id}', <%= count %>)"
															value="<bean:write name="tarifaSocialHelper" property="tarifaSocialDadoEconomia.id"/>" />
												</td>
											</logic:notPresent>

											<td align="center">
												<font   color="#333333">
													<bean:write name="tarifaSocialHelper"
																property="tarifaSocialDadoEconomia.numeroCartaoProgramaSocial" />
												</font>
											</td>
											<td align="left">
												<logic:notEmpty name="tarifaSocialHelper"
																property="tarifaSocialDadoEconomia.tarifaSocialCartaoTipo">
													<font color="#333333">${tarifaSocialHelper.tarifaSocialDadoEconomia.tarifaSocialCartaoTipo.descricao}</font>
												</logic:notEmpty> 
												<logic:empty name="tarifaSocialHelper"
															 property="tarifaSocialDadoEconomia.tarifaSocialCartaoTipo">
					  								&nbsp;
												</logic:empty>
											</td>
											<td>
												<div align="right">
													<font color="#333333"> 
														<logic:notEmpty name="tarifaSocialHelper"
																		property="tarifaSocialDadoEconomia.valorRendaFamiliar">
															<bean:write name="tarifaSocialHelper"
																		property="tarifaSocialDadoEconomia.valorRendaFamiliar"
																		format="0.00" />
														</logic:notEmpty> 
													</font>
												</div>
											</td>
											<td align="center">
												<logic:notEmpty name="tarifaSocialHelper"
																property="tarifaSocialDadoEconomia.rendaTipo">
													<font color="#333333">${tarifaSocialHelper.tarifaSocialDadoEconomia.rendaTipo.descricao}</font>
												</logic:notEmpty> 
												<logic:empty name="tarifaSocialHelper"
															 property="tarifaSocialDadoEconomia.rendaTipo">
				                              		&nbsp;
												</logic:empty>
											</td>
										</tr>
									</table>

									</td>
									<% count = count + 1; %>
									</logic:iterate>

									</logic:present>
								<tr>
									<td>&nbsp;</td>
								</tr>
							</table></div>
					</td>
				</tr>
				<tr>
					<td align="left"><jsp:include
						page="/jsp/util/wizard/navegacao_botoes_wizard_valida_avancar.jsp?numeroPagina=2" />
					</td>
				</tr>
			</table>

			<p>&nbsp;</p>
			</td>
		</tr>
	</table>

	<%@ include file="/jsp/util/rodape.jsp"%>
</body>
</html:form>
</html:html>
