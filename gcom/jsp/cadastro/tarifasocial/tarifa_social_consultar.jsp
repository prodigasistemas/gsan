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
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script>
<!--

    function recuperarDadosPopup(codigoRegistro,descricaoRegistro,tipoConsulta){

       var form = document.InserirTarifaSocialActionForm;

       if (tipoConsulta == 'imovel') {
      		form.idImovel.value = codigoRegistro;
		    form.inscricaoImovel.value = descricaoRegistro;
            form.inscricaoImovel.style.color = "#000000";
            form.action = 'exibirConsultarTarifaSocialImovelAction.do'
            form.submit();
       }
       
       
    }
	
	function limparImovel(){
		var form = document.forms[0];
		
		if (!form.idImovel.readOnly) {
			form.idImovel.value = "";
			form.inscricaoImovel.value = "";
			endereco.style.display = "none";
			economias.style.display = "none";
			dadosTarifaSocial.style.display = "none";
		}
	}

function limparImovelTecla() {
	var form = document.forms[0];
	
	if (!form.idImovel.readOnly) {
		form.inscricaoImovel.value = "";
		endereco.style.display = "none";
		economias.style.display = "none";
		dadosTarifaSocial.style.display = "none";
	}
}

function verificarExibicaoEndereco() {

	var form = document.forms[0];
	
	if ((form.idImovel.value.length < 1 || form.inscricaoImovel.value.length < 1)) {
		endereco.style.display = "none";
		economias.style.display = "none";
		dadosTarifaSocial.style.display = "none";
	}
		
}

-->
</script>


</head>

<body leftmargin="5" topmargin="5"
	onload="setarFoco('${requestScope.nomeCampo}');verificarExibicaoEndereco();">

<html:form action="/exibirConsultarTarifaSocialAction"
	onsubmit="return validateManterTarifaSocialActionForm(this);"
	name="ConsultarTarifaSocialActionForm"
	type="gcom.gui.cadastro.tarifasocial.ConsultarTarifaSocialActionForm"
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
					<td class="parabg">Consultar Dados Tarifa Social</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<table width="100%" border="0" dwcopytype="CopyTableRow">
				<tr>

					<td colspan="2">Para consultar um imóvel na tarifa social, informe
					os dados abaixo:</td>
				</tr>

				<tr>
					<td width="80"><strong> Matricula: </strong></td>
					<td><html:text property="idImovel" size="9" maxlength="9"
						onkeyup="javascript:limparImovelTecla();"
						onkeypress="validaEnterComMensagem(event, 'exibirConsultarTarifaSocialAction.do', 
							'idImovel', 'Imóvel');" />
					<a
						href="javascript:abrirPopup('exibirPesquisarImovelAction.do', 400, 800);">
					<img width="23" height="21"
						src="<bean:message key='caminho.imagens'/>pesquisa.gif" border="0" /></a>
					<logic:present name="imovelNaoEncontrado">
						<html:text property="inscricaoImovel" size="40" maxlength="40"
							readonly="true"
							style="background-color:#EFEFEF; border:0; color: #ff0000" />
					</logic:present> <logic:notPresent name="imovelNaoEncontrado">
						<html:text property="inscricaoImovel" size="40" maxlength="40"
							readonly="true"
							style="background-color:#EFEFEF; border:0; color: #000000" />
					</logic:notPresent> <a href="javascript:limparImovel();"> <img
						src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar Imóvel" /> </a></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
				</tr>


				<tr>
					<td colspan="2">
					<table width="100%" bgcolor="#99CCFF" border="0">
						<tr bgcolor="#99CCFF">
							<td>
							<div align="center"><strong>Endere&ccedil;o</strong></div>
							</td>
						</tr>

						<logic:present name="imovel" scope="session">

							<logic:present name="matriculaInvalida" scope="request">

								<tr>
									<td bgcolor="#FFFFFF">
									<div align="center"><bean:write name="matriculaInvalida"
										scope="request" /></div>
									</td>
								</tr>

							</logic:present>

							<logic:notPresent name="matriculaInvalida" scope="request">
								<tr id="endereco">
									<td bgcolor="#FFFFFF"><logic:present name="imovel"
										scope="session">
										<div align="center"><bean:write name="imovel"
											property="enderecoFormatado" scope="session" /></div>
									</logic:present></td>
								</tr>
							</logic:notPresent>

						</logic:present>

						<logic:notPresent name="imovel" scope="session">

							<logic:present name="matriculaInvalida" scope="request">

								<tr>
									<td bgcolor="#cbe5fe">
									<div align="center"><bean:write name="matriculaInvalida"
										scope="request" /></div>
									</td>
								</tr>
							</logic:present>

						</logic:notPresent>

					</table>
					</td>
				</tr>

				<tr>
					<td width="80"><strong>Economias:</strong>
					<td id="economias"><logic:notPresent name="matriculaInvalida"
						scope="request">
						<logic:present name="quantEconomias" scope="session">
							<bean:write name="quantEconomias" scope="session" />
						</logic:present>
						<logic:notPresent name="quantEconomias" scope="session">
						&nbsp;
					</logic:notPresent>
					</logic:notPresent> <logic:present name="matriculaInvalida"
						scope="request">
						&nbsp;
				</logic:present></td>
				</tr>
				<logic:present name="colecaoTarifaSocialHelper">
					<tr id="dadosTarifaSocial">
						<td colspan="2"><%int count = 0;

			%> <logic:iterate id="tarifaSocialHelper"
							name="colecaoTarifaSocialHelper" type="TarifaSocialHelper">
							<table width="100%">

								<tr>
									<td height="82" colspan="2">
									<hr>
									<table width="100%" bgcolor="#99CCFF">
										<tr bordercolor="#FFFFFF" bgcolor="#0066FF">
											<td height="0" colspan="2" bgcolor="#99CCFF">
											<div align="center"><strong>Cliente Usuário</strong></div>
											</td>
											<td width="26%" height="0" bgcolor="#99CCFF">
											<div align="center"><strong>Complemento Endereço</strong></div>
											</td>
											<td height="0" bgcolor="#99CCFF">
											<div align="center"><strong>CPF</strong></div>
											</td>
										</tr>
										<tr bordercolor="#FFFFFF" bgcolor="#FFFFFF">
											<td colspan="2">
											<div align="left"><logic:present name="tarifaSocialHelper"
												property="clienteImovelEconomia">
											<strong>${tarifaSocialHelper.clienteImovelEconomia.cliente.nome}</strong>
											</logic:present> <logic:notPresent
												name="tarifaSocialHelper.clienteImovelEconomia">
											${tarifaSocialHelper.clienteImovel.cliente.nome}</logic:notPresent></div>
											</td>
											<td align="left"><logic:present name="tarifaSocialHelper"
												property="clienteImovelEconomia">
												<bean:write name="tarifaSocialHelper"
													property="clienteImovelEconomia.imovelEconomia.complementoEndereco" />
											</logic:present> <logic:notPresent name="tarifaSocialHelper"
												property="clienteImovelEconomia">
								&nbsp;
								</logic:notPresent></td>

											<td align="center"><logic:present name="tarifaSocialHelper"
												property="clienteImovelEconomia">
												<logic:present name="tarifaSocialHelper"
													property="clienteImovelEconomia.cliente.cpf">
													<bean:write name="tarifaSocialHelper"
														property="clienteImovelEconomia.cliente.cpfFormatado" />
												</logic:present>
												<logic:notPresent name="tarifaSocialHelper"
													property="clienteImovelEconomia.cliente.cpf">
													&nbsp;
												</logic:notPresent>
											</logic:present> <logic:notPresent name="tarifaSocialHelper"
												property="clienteImovelEconomia">
												<logic:present name="tarifaSocialHelper"
													property="clienteImovel.cliente.cpf">
													<bean:write name="tarifaSocialHelper"
														property="clienteImovel.cliente.cpfFormatado" />
												</logic:present>
												<logic:notPresent name="tarifaSocialHelper"
													property="clienteImovel.cliente.cpf">
															&nbsp;
																			</logic:notPresent>
											</logic:notPresent></td>
										</tr>

										<tr bordercolor="#FFFFFF" bgcolor="#0066FF">
											<td width="23%" height="0" bgcolor="#99CCFF">
											<div align="center"><strong>RG</strong></div>
											</td>
											<td width="20%" bgcolor="#99CCFF">
											<div align="center"><strong>Data Emiss&atilde;o</strong></div>
											</td>
											<td bgcolor="#99CCFF">
											<div align="center"><strong>&Oacute;rg&atilde;o Expedidor</strong></div>
											</td>
											<td bgcolor="#99CCFF">
											<div align="center"></div>
											<div align="center"><strong>UF</strong></div>
											</td>
										</tr>
										<tr bordercolor="#FFFFFF" bgcolor="#FFFFFF">
											<td align="center"><logic:present name="tarifaSocialHelper"
												property="clienteImovelEconomia">
												<logic:present name="tarifaSocialHelper"
													property="clienteImovelEconomia.cliente.rg">
													<bean:write name="tarifaSocialHelper"
														property="clienteImovelEconomia.cliente.rg" />
												</logic:present>
												<logic:notPresent name="tarifaSocialHelper"
													property="clienteImovelEconomia.cliente.rg">
														&nbsp;
												</logic:notPresent>
											</logic:present> <logic:notPresent name="tarifaSocialHelper"
												property="clienteImovelEconomia">
												<logic:present name="tarifaSocialHelper"
													property="clienteImovel.cliente.rg">
													<bean:write name="tarifaSocialHelper"
														property="clienteImovel.cliente.rg" />
												</logic:present>
												<logic:notPresent name="tarifaSocialHelper"
													property="clienteImovel.cliente.rg">
														&nbsp;
													</logic:notPresent>
											</logic:notPresent></td>

											<td align="left"><logic:present name="tarifaSocialHelper"
												property="clienteImovelEconomia">
												<logic:present name="tarifaSocialHelper"
													property="clienteImovelEconomia.cliente.dataEmissaoRg">
													<bean:write name="tarifaSocialHelper"
														property="clienteImovelEconomia.cliente.dataEmissaoRg"
														formatKey="date.format" />
												</logic:present>
												<logic:notPresent name="tarifaSocialHelper"
													property="clienteImovelEconomia.cliente.dataEmissaoRg">
										&nbsp;</logic:notPresent>
											</logic:present> <logic:notPresent name="tarifaSocialHelper"
												property="clienteImovelEconomia">
												<logic:present name="tarifaSocialHelper"
													property="clienteImovel.cliente.dataEmissaoRg">
													<bean:write name="tarifaSocialHelper"
														property="clienteImovel.cliente.dataEmissaoRg"
														formatKey="date.format" />
												</logic:present>
												<logic:notPresent name="tarifaSocialHelper"
													property="clienteImovel.cliente.dataEmissaoRg">
										&nbsp;</logic:notPresent>
											</logic:notPresent></td>

											<td>
											<div align="center"><logic:present name="tarifaSocialHelper"
												property="clienteImovelEconomia">
												<logic:present name="tarifaSocialHelper"
													property="clienteImovelEconomia.cliente.orgaoExpedidorRg">
													<bean:write name="tarifaSocialHelper"
														property="clienteImovelEconomia.cliente.orgaoExpedidorRg.descricaoAbreviada" />
												</logic:present>
												<logic:notPresent name="tarifaSocialHelper"
													property="clienteImovelEconomia.cliente.orgaoExpedidorRg">
									&nbsp;
								</logic:notPresent>
											</logic:present> <logic:notPresent name="tarifaSocialHelper"
												property="clienteImovelEconomia">
												<logic:present name="tarifaSocialHelper"
													property="clienteImovel.cliente.orgaoExpedidorRg">
													<bean:write name="tarifaSocialHelper"
														property="clienteImovel.cliente.orgaoExpedidorRg.descricaoAbreviada" />
												</logic:present>
												<logic:notPresent name="tarifaSocialHelper"
													property="clienteImovel.cliente.orgaoExpedidorRg">
									&nbsp;
								</logic:notPresent>
											</logic:notPresent></div>
											</td>

											<td>
											<div align="left"><logic:present name="tarifaSocialHelper"
												property="clienteImovelEconomia">
												<logic:present name="tarifaSocialHelper"
													property="clienteImovelEconomia.cliente.unidadeFederacao">
													<bean:write name="tarifaSocialHelper"
														property="clienteImovelEconomia.cliente.unidadeFederacao.sigla" />
												</logic:present>
												<logic:present name="tarifaSocialHelper"
													property="clienteImovelEconomia.cliente.unidadeFederacao">
										&nbsp;
									</logic:present>
											</logic:present> <logic:notPresent name="tarifaSocialHelper"
												property="clienteImovelEconomia">
												<logic:present name="tarifaSocialHelper"
													property="clienteImovel.cliente.unidadeFederacao">
													<bean:write name="tarifaSocialHelper"
														property="clienteImovel.cliente.unidadeFederacao.sigla" />
												</logic:present>
												<logic:present name="tarifaSocialHelper"
													property="clienteImovel.cliente.unidadeFederacao">
										&nbsp;
									</logic:present>
											</logic:notPresent></div>
											</td>
										</tr>
									</table>
									</td>
								</tr>
								<tr>
									<td height="44" colspan="2">

									<table width="100%" bgcolor="#99CCFF">
										<tr bordercolor="#FFFFFF" bgcolor="#E6EFFF">
											<td width="50%" bgcolor="#99CCFF">
											<div align="center"><strong>Contrato Companhia Elétrica</strong></div>
											</td>
											<td width="50%" bgcolor="#99CCFF">
											<div align="center"><strong>Consumo Médio</strong></div>
											</td>
										</tr>
										<tr bordercolor="#FFFFFF" bgcolor="#FFFFFF">

											<td align="center"><font color="#333333"><logic:present
												name="tarifaSocialHelper" property="clienteImovelEconomia">${tarifaSocialHelper.clienteImovelEconomia.imovelEconomia.numeroCelpe}&nbsp;</logic:present>
											<logic:notPresent name="tarifaSocialHelper"
												property="clienteImovelEconomia">${tarifaSocialHelper.clienteImovel.imovel.numeroCelpe}&nbsp;</logic:notPresent></font>
											</td>
											<td align="center"><font color="#333333">${tarifaSocialHelper.tarifaSocialDadoEconomia.consumoCelpe}&nbsp;</font>
											</td>
										</tr>
									</table>

									</td>
								</tr>

								<tr>
									<td height="44" colspan="2">

									<table width="100%" bgcolor="#99CCFF">
										<tr bordercolor="#FFFFFF" bgcolor="#E6EFFF">
											<td width="34%" bgcolor="#99CCFF">
											<div align="center"><strong>IPTU</strong></div>
											</td>
											<td width="33%" bgcolor="#99CCFF">
											<div align="center"><strong>Área Construída</strong></div>
											</td>
											<td width="33%" bgcolor="#99CCFF">
											<div align="center"><strong>Número Moradores</strong></div>
											</td>
										</tr>
										<tr bordercolor="#FFFFFF" bgcolor="#FFFFFF">

											<td align="center"><font color="#333333"><logic:present
												name="tarifaSocialHelper" property="clienteImovelEconomia">${tarifaSocialHelper.clienteImovelEconomia.imovelEconomia.numeroIptu}</logic:present>
											<logic:notPresent name="tarifaSocialHelper"
												property="clienteImovelEconomia">${tarifaSocialHelper.clienteImovel.imovel.numeroIptu}</logic:notPresent>&nbsp;</font>
											</td>
											<td align="center"><font color="#333333"><logic:present
												name="tarifaSocialHelper" property="clienteImovelEconomia">
												<logic:present name="tarifaSocialHelper"
													property="clienteImovelEconomia.imovelEconomia.areaConstruida">
													<bean:write name="tarifaSocialHelper"
														property="clienteImovelEconomia.imovelEconomia.areaConstruida"
														format="0.00" />
												</logic:present>
											</logic:present> <logic:notPresent name="tarifaSocialHelper"
												property="clienteImovelEconomia">
												<logic:present name="tarifaSocialHelper"
													property="clienteImovel.imovel.areaConstruida">
													<bean:write name="tarifaSocialHelper"
														property="clienteImovel.imovel.areaConstruida"
														format="0.00" />
												</logic:present>
											</logic:notPresent>&nbsp;</font></td>
											<td align="center"><font color="#333333"><logic:present
												name="tarifaSocialHelper" property="clienteImovelEconomia">${tarifaSocialHelper.clienteImovelEconomia.imovelEconomia.numeroMorador}</logic:present>
											<logic:notPresent name="tarifaSocialHelper"
												property="clienteImovelEconomia">${tarifaSocialHelper.clienteImovel.imovel.numeroMorador}</logic:notPresent>&nbsp;</font>
											</td>
										</tr>
									</table>

									</td>
								</tr>
								<tr>
									<td height="44" colspan="2">

									<table width="100%" bgcolor="#99CCFF">
										<tr bordercolor="#FFFFFF" bgcolor="#E6EFFF">

											<td width="23%" bgcolor="#99CCFF">
											<div align="center"><strong>Cart&atilde;o do Prog.Social</strong></div>
											</td>
											<td width="23%" bgcolor="#99CCFF">
											<div align="center"><strong>Tipo Cart&atilde;o Prog.Social</strong></div>
											</td>
											<td width="18%" bgcolor="#99CCFF">
											<div align="center"><strong>Data Validade</strong></div>
											</td>
											<td width="20%" bgcolor="#99CCFF">
											<div align="center"><strong>Número Parcelas</strong></div>
											</td>
										</tr>
										<tr bordercolor="#FFFFFF" bgcolor="#FFFFFF">

											<td align="center"><font color="#333333"><bean:write
												name="tarifaSocialHelper"
												property="tarifaSocialDadoEconomia.numeroCartaoProgramaSocial" /></font>
											</td>
											<td align="left"><logic:notEmpty name="tarifaSocialHelper"
												property="tarifaSocialDadoEconomia.tarifaSocialCartaoTipo">
												<font color="#333333">${tarifaSocialHelper.tarifaSocialDadoEconomia.tarifaSocialCartaoTipo.descricao}</font>
											</logic:notEmpty> <logic:empty name="tarifaSocialHelper"
												property="tarifaSocialDadoEconomia.tarifaSocialCartaoTipo">
	  								&nbsp;
								</logic:empty></td>
											<td>
											<div align="center"><font color="#333333"> <logic:notEmpty
												name="tarifaSocialHelper"
												property="tarifaSocialDadoEconomia.dataValidadeCartao">
												<bean:write name="tarifaSocialHelper"
													property="tarifaSocialDadoEconomia.dataValidadeCartao"
													formatKey="date.format" />
											</logic:notEmpty> </font></div>
											</td>
											<td align="center"><font color="#333333">${tarifaSocialHelper.tarifaSocialDadoEconomia.numeroMesesAdesao}</font>
											&nbsp;</td>

										</tr>
									</table>

									</td>
								</tr>
								<tr>
									<td height="44" colspan="2">

									<table width="100%" bgcolor="#99CCFF">
										<tr bordercolor="#FFFFFF" bgcolor="#E6EFFF">
											<td width="50%" bgcolor="#99CCFF">
											<div align="center"><strong>Renda Familiar</strong></div>
											</td>
											<td width="50%" bgcolor="#99CCFF">
											<div align="center"><strong>Tipo Renda Familiar</strong></div>
											</td>
										</tr>
										<tr bordercolor="#FFFFFF" bgcolor="#FFFFFF">

											<td>
											<div align="right"><font color="#333333"> <logic:notEmpty
												name="tarifaSocialHelper"
												property="tarifaSocialDadoEconomia.valorRendaFamiliar">
												<bean:write name="tarifaSocialHelper"
													property="tarifaSocialDadoEconomia.valorRendaFamiliar"
													format="0.00" />
											</logic:notEmpty> </font></div>
											</td>
											<td align="center"><logic:notEmpty name="tarifaSocialHelper"
												property="tarifaSocialDadoEconomia.rendaTipo">
												<font color="#333333">${tarifaSocialHelper.tarifaSocialDadoEconomia.rendaTipo.descricao}</font>
											</logic:notEmpty> <logic:empty name="tarifaSocialHelper"
												property="tarifaSocialDadoEconomia.rendaTipo">
                              		&nbsp;
								</logic:empty></td>
										</tr>
									</table>

									</td>
								</tr>
								<tr>
									<td height="44" colspan="2">

									<table width="100%" bgcolor="#99CCFF">
										<tr bordercolor="#FFFFFF" bgcolor="#E6EFFF">

											<td width="50%" bgcolor="#99CCFF">
											<div align="center"><strong>Data Implantação</strong></div>
											</td>
											<td width="50%" bgcolor="#99CCFF">
											<div align="center"><strong>Data Última Alteração</strong></div>
											</td>
										</tr>
										<tr bordercolor="#FFFFFF" bgcolor="#FFFFFF">

											<td align="center"><logic:notEmpty name="tarifaSocialHelper"
												property="tarifaSocialDadoEconomia.dataImplantacao">
												<font color="#333333"><bean:write name="tarifaSocialHelper"
													property="tarifaSocialDadoEconomia.dataImplantacao"
													format="dd/MM/yyyy" /></font>
											</logic:notEmpty>&nbsp;</td>
											<td align="center"><logic:notEmpty name="tarifaSocialHelper"
												property="tarifaSocialDadoEconomia.ultimaAlteracao">
												<font color="#333333"><bean:write name="tarifaSocialHelper"
													property="tarifaSocialDadoEconomia.ultimaAlteracao"
													format="dd/MM/yyyy" /></font>
											</logic:notEmpty>&nbsp;</td>
										</tr>
									</table>

									</td>
								</tr>
								<tr>
									<td height="44" colspan="2">

									<table width="100%" bgcolor="#99CCFF">
										<tr bordercolor="#FFFFFF" bgcolor="#E6EFFF">

											<td width="70%" bgcolor="#99CCFF">
											<div align="center"><strong>Motivo de Revisão</strong></div>
											</td>
											<td width="30%" bgcolor="#99CCFF">
											<div align="center"><strong>Data da Revisão</strong></div>
											</td>
										</tr>
										<tr bordercolor="#FFFFFF" bgcolor="#FFFFFF">

											<td align="left"><logic:present name="tarifaSocialHelper"
												property="tarifaSocialDadoEconomia.tarifaSocialRevisaoMotivo">
												<font color="#333333">${tarifaSocialHelper.tarifaSocialDadoEconomia.tarifaSocialRevisaoMotivo.id}
												-
												${tarifaSocialHelper.tarifaSocialDadoEconomia.tarifaSocialRevisaoMotivo.descricao}</font>
											</logic:present>&nbsp;</td>
											<td align="center"><logic:notEmpty name="tarifaSocialHelper"
												property="tarifaSocialDadoEconomia.dataRevisao">
												<font color="#333333"><bean:write name="tarifaSocialHelper"
													property="tarifaSocialDadoEconomia.dataRevisao"
													format="dd/MM/yyyy" /></font>
											</logic:notEmpty>&nbsp;</td>
										</tr>
									</table>

									</td>
								</tr>
								<tr>
									<td height="44" colspan="2">

									<table width="100%" bgcolor="#99CCFF">
										<tr bordercolor="#FFFFFF" bgcolor="#E6EFFF">

											<td width="70%" bgcolor="#99CCFF">
											<div align="center"><strong>Motivo de Exclusão</strong></div>
											</td>
											<td width="30%" bgcolor="#99CCFF">
											<div align="center"><strong>Data da Exclusão</strong></div>
											</td>
										</tr>
										<tr bordercolor="#FFFFFF" bgcolor="#FFFFFF">

											<td align="left"><logic:present name="tarifaSocialHelper"
												property="tarifaSocialDadoEconomia.tarifaSocialExclusaoMotivo">
												<font color="#333333">${tarifaSocialHelper.tarifaSocialDadoEconomia.tarifaSocialExclusaoMotivo.id}
												-
												${tarifaSocialHelper.tarifaSocialDadoEconomia.tarifaSocialExclusaoMotivo.descricao}</font>
											</logic:present>&nbsp;</td>
											<td align="center"><logic:notEmpty name="tarifaSocialHelper"
												property="tarifaSocialDadoEconomia.dataExclusao">
												<font color="#333333"><bean:write name="tarifaSocialHelper"
													property="tarifaSocialDadoEconomia.dataExclusao"
													format="dd/MM/yyyy" /></font>
											</logic:notEmpty>&nbsp;</td>
										</tr>
									</table>

									</td>
								</tr>

								<tr>
									<td height="44" colspan="2">

									<table width="100%" bgcolor="#99CCFF">
										<tr bordercolor="#FFFFFF" bgcolor="#E6EFFF">

											<td width="40%" bgcolor="#99CCFF">
											<div align="center"><strong>Quantidade de Recadastramentos</strong></div>
											</td>
											<td width="60%" bgcolor="#99CCFF">
											<div align="center"><strong>Data do Último Recadastramento</strong></div>
											</td>
										</tr>
										<tr bordercolor="#FFFFFF" bgcolor="#FFFFFF">

											<td align="center"><font color="#333333">${tarifaSocialHelper.tarifaSocialDadoEconomia.quantidadeRecadastramento}</font>&nbsp;
											</td>
											<td align="center"><logic:notEmpty name="tarifaSocialHelper"
												property="tarifaSocialDadoEconomia.dataRecadastramento">
												<font color="#333333"><bean:write name="tarifaSocialHelper"
													property="tarifaSocialDadoEconomia.dataRecadastramento"
													format="dd/MM/yyyy" /></font>
											</logic:notEmpty>&nbsp;</td>
										</tr>
									</table>

									</td>
								</tr>

								<%count = count + 1;

		%>
								</logic:iterate>
								<tr>
									<td>&nbsp;</td>
								</tr>
				</logic:present>
			</table>
			</td>
		</tr>
	</table>

	<p>&nbsp;</p>
	</td>
	</tr>
	</table>


	<%@ include file="/jsp/util/rodape.jsp"%>

</html:form>

</body>

</html:html>
