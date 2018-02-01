<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ include file="/jsp/util/telaespera.jsp"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>

<%@ page import="gcom.cadastro.imovel.ImovelPerfil" %>
<%@ page import="gcom.cadastro.localidade.UnidadeNegocio" %>
<%@ page import="gcom.cadastro.localidade.GerenciaRegional" %>
<%@ page import="gcom.cobranca.CmdEmpresaCobrancaContaLigacaoAguaSituacao" %>
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
	src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<SCRIPT LANGUAGE="JavaScript">

function verificaPesquisa(flag){
	var form = document.forms[0];
	if(flag=='nao'){
		form.action = '/gsan/exibirConsultarContasComandoCobrancaPopupAction.do?pesquisa=sim&idComandoEmpresaCobrancaConta='+${requestScope.idComandoEmpresaCobrancaConta};
	    submitForm(form);
	}
}

</script>
</head>

<body leftmargin="5" topmargin="5" onload="verificaPesquisa('${requestScope.pesquisa}');resizePageSemLink(750, 570);">


<div id="formDiv"><html:form action="/exibirConsultarContasComandoCobrancaPopupAction"
	type="gcom.gui.cobranca.GerarArquivoTextoContasCobrancaEmpresaActionForm"
	name="GerarArquivoTextoContasCobrancaEmpresaActionForm" method="post">


	<table width="700" border="0" cellspacing="5" cellpadding="0">
		<tr>
			<td width="100%" valign="top" class="centercoltext">

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
					<td class="parabg">Comando de Contas em Cobrança por Empresa</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>

			<p>&nbsp;</p>

			<p>&nbsp;</p>

			<table width="100%" border="0">
				<tr>
					<td width="35%"><strong>Empresa:</strong></td>
					<td colspan="3"><html:text property="nomeEmpresa" size="60"
						maxlength="50" readonly="true"
						style="background-color:#EFEFEF; border:0; font-color: #000000" />
					</td>
				</tr>
				<tr>
					<td width="35%"><strong>Data de Execução do Comando:</strong></td>
					<td colspan="3"><html:text property="dataExecucaoComando" size="12"
						maxlength="10" readonly="true"
						style="background-color:#EFEFEF; border:0; font-color: #000000" />
					</td>
				</tr>
				<tr>
					<td width="35%"><strong>Período de Referência das Contas:</strong></td>
					<td colspan="3"><html:text
						property="periodoReferenciaContasInicial" size="7" maxlength="7"
						readonly="true"
						style="background-color:#EFEFEF; border:0; font-color: #000000" />
					a <html:text property="periodoReferenciaContasFinal" size="7"
						maxlength="7" readonly="true"
						style="background-color:#EFEFEF; border:0; font-color: #000000" />
					</td>
				</tr>
				<tr>
					<td width="35%"><strong>Período de Vencimento das Contas:</strong></td>
					<td colspan="3"><html:text
						property="periodoVencimentoContasInicial" size="12" maxlength="10"
						readonly="true"
						style="background-color:#EFEFEF; border:0; font-color: #000000" />
					a <html:text property="periodoVencimentoContasFinal" size="12"
						maxlength="10" readonly="true"
						style="background-color:#EFEFEF; border:0; font-color: #000000" />
					</td>
				</tr>
				<tr>
					<td width="35%"><strong>Intervalo de Valor das Contas:</strong></td>
					<td colspan="3"><html:text property="intervaloValorContasInicial"
						size="12" maxlength="10" readonly="true"
						style="background-color:#EFEFEF; border:0; font-color: #000000; text-align:right" />
					a <html:text property="intervaloValorContasFinal" size="12"
						maxlength="10" readonly="true"
						style="background-color:#EFEFEF; border:0; font-color: #000000; text-align:right" />
					</td>
				</tr>
				
				<tr>
					<td width="35%"><strong>Quantidade de Contas:</strong></td>
					<td colspan="3"><html:text property="qtdContasInicial"
						size="12" maxlength="10" readonly="true"
						style="background-color:#EFEFEF; border:0; font-color: #000000; text-align:right" />
					a <html:text property="qtdContasFinal" size="12"
						maxlength="10" readonly="true"
						style="background-color:#EFEFEF; border:0; font-color: #000000; text-align:right" />
					</td>
				</tr>
				
				<tr>
					<td width="35%"><strong>Quantidade de Dias de Vencimento:</strong></td>
					<td colspan="3"><html:text property="qtdDeDiasVencimento" size="12"
						maxlength="10" readonly="true"
						style="background-color:#EFEFEF; border:0; font-color: #000000" />
					</td>
				</tr>
				
				
				<tr>
					<td width="35%"><strong>Imóvel:</strong></td>
					<td colspan="3"><html:text property="idImovel" size="12"
						maxlength="10" readonly="true"
						style="background-color:#EFEFEF; border:0; font-color: #000000" />
					</td>
				</tr>
				<logic:notPresent name="colecaoImovelPerfil" scope="session">
					<tr>
						<td width="35%"><strong>Perfil do Imóvel:</strong></td>
						<td colspan="3">
						<html:text property="idImovelPerfil" size="10"
							maxlength="10" readonly="true"
							style="background-color:#EFEFEF; border:0; font-color: #000000" />
						<html:text property="dsImovelPerfil" size="50" maxlength="40"
							readonly="true"
							style="background-color:#EFEFEF; border:0; font-color: #000000" />
						</td>
					</tr>
				</logic:notPresent>
				<logic:present name="colecaoImovelPerfil" scope="session">
				
					<tr>
						<td colspan="4">
						<hr>
						</td>
					</tr>
					<logic:iterate name="colecaoImovelPerfil"
										id="imovelPerfil" type="ImovelPerfil">
						<tr>
							<c:set var="count" value="${count+1}" />
							<c:choose>
								<c:when test="${count == '1'}">
									<td width="35%"><strong>Perfil do Imóvel:</strong></td>
								</c:when>
								<c:otherwise>
									<td width="35%"><strong></strong></td>
								</c:otherwise>
							</c:choose>
											
							<td colspan="3">
							<input type="text" value="<bean:write name='imovelPerfil' property='id' />" size="10"
								maxlength="10" readonly="true"
								style="background-color:#EFEFEF; border:0; font-color: #000000" />
							<input type="text" value="<bean:write name='imovelPerfil' property='descricao' />" size="50" maxlength="40"
								readonly="true"
								style="background-color:#EFEFEF; border:0; font-color: #000000" />
							</td>
						</tr>
					</logic:iterate>
					
					<tr>
						<td colspan="4">
						<hr>
						</td>
					</tr>
					
				</logic:present>
				<tr>
					<td width="35%"><strong>Cliente:</strong></td>
					<td colspan="3"><html:text property="nomeCliente" size="60"
						maxlength="50" readonly="true"
						style="background-color:#EFEFEF; border:0; font-color: #000000" />
					</td>
				</tr>
				<logic:notPresent name="colecaoGerenciaRegional" scope="session">
					<tr>
						<td width="35%"><strong>Gerência Regional:</strong></td>
						<td colspan="3">
						<html:text property="idGerenciaRegional" size="10"
							maxlength="10" readonly="true"
							style="background-color:#EFEFEF; border:0; font-color: #000000" />
						<html:text property="nomeGerenciaRegional" size="50" maxlength="40"
							readonly="true"
							style="background-color:#EFEFEF; border:0; font-color: #000000" />
						</td>
					</tr>
				</logic:notPresent>
				<logic:present name="colecaoGerenciaRegional" scope="session">
				
					<tr>
						<td colspan="4">
						<hr>
						</td>
					</tr>
					<logic:iterate name="colecaoGerenciaRegional"
										id="gerenciaRegional" type="GerenciaRegional">
						<tr>
							<c:set var="count2" value="${count2+1}" />
							<c:choose>
								<c:when test="${count2 == '1'}">
									<td width="35%"><strong>Gerência Regional:</strong></td>
								</c:when>
								<c:otherwise>
									<td width="35%"><strong></strong></td>
								</c:otherwise>
							</c:choose>
											
							<td colspan="3">
							<input type="text" value="<bean:write name='gerenciaRegional' property='id' />" size="10"
								maxlength="10" readonly="true"
								style="background-color:#EFEFEF; border:0; font-color: #000000" />
							<input type="text" value="<bean:write name='gerenciaRegional' property='nome' />" size="50" maxlength="40"
								readonly="true"
								style="background-color:#EFEFEF; border:0; font-color: #000000" />
							</td>
						</tr>
					</logic:iterate>
					
					<tr>
						<td colspan="4">
						<hr>
						</td>
					</tr>
					
				</logic:present>
				<tr>
				<logic:notPresent name="colecaoUnidadeNegocio" scope="session">
					<tr>
						<td width="35%"><strong>Unidade de Negócio:</strong></td>
						<td colspan="3">
						<html:text property="idUnidadeNegocio" size="10"
							maxlength="10" readonly="true"
							style="background-color:#EFEFEF; border:0; font-color: #000000" />
						<html:text property="nomeUnidadeNegocio" size="50" maxlength="40"
							readonly="true"
							style="background-color:#EFEFEF; border:0; font-color: #000000" />
						</td>
					</tr>
				</logic:notPresent>
				<logic:present name="colecaoUnidadeNegocio" scope="session">
				
					<logic:iterate name="colecaoUnidadeNegocio"
										id="unidadeNegocio" type="UnidadeNegocio">
						<tr>
							<c:set var="count3" value="${count3+1}" />
							<c:choose>
								<c:when test="${count3 == '1'}">
									<td width="35%"><strong>Unidade de Negócio:</strong></td>
								</c:when>
								<c:otherwise>
									<td width="35%"><strong></strong></td>
								</c:otherwise>
							</c:choose>
											
							<td colspan="3">
							<input type="text" value="<bean:write name='unidadeNegocio' property='id' />" size="10"
								maxlength="10" readonly="true"
								style="background-color:#EFEFEF; border:0; font-color: #000000" />
							<input type="text" value="<bean:write name='unidadeNegocio' property='nome' />" size="50" maxlength="40"
								readonly="true"
								style="background-color:#EFEFEF; border:0; font-color: #000000" />
							</td>
						</tr>
					</logic:iterate>
					
					<tr>
						<td colspan="4">
						<hr>
						</td>
					</tr>
					
				</logic:present>
				<logic:notPresent name="colecaoLigacaoAguaSituacao" scope="session">
					<tr>
						<td width="35%"><strong>Situação da Ligação de Água:</strong></td>
						<td colspan="3">
						<html:text property="idLigacaoAguaSituacao" size="10"
							maxlength="10" readonly="true"
							style="background-color:#EFEFEF; border:0; font-color: #000000" />
						<html:text property="nomeLigacaoAguaSituacao" size="50" maxlength="40"
							readonly="true"
							style="background-color:#EFEFEF; border:0; font-color: #000000" />
						</td>
					</tr>
				</logic:notPresent>
				<logic:present name="colecaoLigacaoAguaSituacao" scope="session">
				
					<logic:iterate name="colecaoLigacaoAguaSituacao"
										id="ligacaoAgua" type="CmdEmpresaCobrancaContaLigacaoAguaSituacao">
						<tr>
							<c:set var="count3" value="${count3+1}" />
							<c:choose>
								<c:when test="${count3 == '1'}">
									<td width="35%"><strong>Situação da Ligação de Água:</strong></td>
								</c:when>
								<c:otherwise>
									<td width="35%"><strong></strong></td>
								</c:otherwise>
							</c:choose>
											
							<td colspan="3">
							<bean:define name="ligacaoAgua" property="ligacaoAguaSituacao" id="ligacaoAguaSituacao" />
							<input type="text" value="<bean:write name='ligacaoAguaSituacao' property='id' />" size="10"
								maxlength="10" readonly="true"
								style="background-color:#EFEFEF; border:0; font-color: #000000" />
							<input type="text" value="<bean:write name='ligacaoAguaSituacao' property='descricao' />" size="50" maxlength="40"
								readonly="true"
								style="background-color:#EFEFEF; border:0; font-color: #000000" />
							</td>
						</tr>
					</logic:iterate>
					
					<tr>
						<td colspan="4">
						<hr>
						</td>
					</tr>
					
				</logic:present>
				
				<tr>
					<td width="35%"><strong>Intervalo de Localização:</strong></td>
					<td colspan="3"><html:text property="intervaloLocalizacaoInicial"
						size="5" maxlength="4" readonly="true"
						style="background-color:#EFEFEF; border:0; font-color: #000000" />
					a <html:text property="intervaloLocalizacaoFinal" size="5"
						maxlength="4" readonly="true"
						style="background-color:#EFEFEF; border:0; font-color: #000000" />
					</td>
				</tr>
				<tr>
					<td width="35%"><strong>Intervalo de Setor Comercial:</strong></td>
					<td colspan="3"><html:text
						property="intervaloSetorComercialInicial" size="5" maxlength="4"
						readonly="true"
						style="background-color:#EFEFEF; border:0; font-color: #000000" />
					a <html:text property="intervaloSetorComercialFinal" size="5"
						maxlength="4" readonly="true"
						style="background-color:#EFEFEF; border:0; font-color: #000000" />
					</td>
				</tr>
				<tr>
					<td width="35%"><strong>Intervalo de Quadras:</strong></td>
					<td colspan="3"><html:text
						property="intervaloQuadraInicial" size="5" maxlength="4"
						readonly="true"
						style="background-color:#EFEFEF; border:0; font-color: #000000" />
					a <html:text property="intervaloQuadraFinal" size="5"
						maxlength="4" readonly="true"
						style="background-color:#EFEFEF; border:0; font-color: #000000" />
					</td>
				</tr>
				
				<tr><td colspan="2"><hr></td></tr>
				
				<tr>
					<td width="35%"><strong>Quantidade Total de Clientes Selecionados:</strong></td>
					<td colspan="3"><html:text property="qtdClientes"
						size="8" maxlength="8" readonly="true"
						style="background-color:#EFEFEF; border:0; font-color: #000000" />
					</td>
				</tr>
				<tr>
					<td width="35%"><strong>Quantidade Total de Contas Selecionadas:</strong></td>
					<td colspan="3"><html:text property="qtdContas"
						size="8" maxlength="8" readonly="true"
						style="background-color:#EFEFEF; border:0; font-color: #000000" />
					</td>
				</tr>
				<tr>
					<td width="35%"><strong>Valor Total de Contas Selecionadas:</strong></td>
					<td colspan="3"><html:text property="valorTotal"
						size="12" maxlength="12" readonly="true"
						style="background-color:#EFEFEF; border:0; font-color: #000000; text-align:right" />
					</td>
				</tr>

				<tr>
					<td>&nbsp;</td>
					<td colspan="1" align="right"><input type="button" name="fechar"
						class="bottonRightCol" value="Fechar" onClick="window.close();" /></td>
				</tr>
			</table>

			<table width="100%" border="0">
				<tr>
					<td align="left"></td>
				</tr>
			</table>


			<p>&nbsp;</p>
	</table>

</html:form></div>

</body>
</html:html>
