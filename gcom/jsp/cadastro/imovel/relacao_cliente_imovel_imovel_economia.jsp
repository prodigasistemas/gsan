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

function cancelar() {

	document.forms[0].action = 'telaPrincipal.do';
	document.forms[0].submit();

}

function filtrar() {

	document.forms[0].action = 'ExibirConsultarRelacaoClienteImovelAction.do';
	document.forms[0].submit();

}

-->
</script>

<style>
.fontePequena {
font-size: 11px;
face: Verdana, Arial, Helvetica, sans-serif;
}

</style>

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
			<tr>
				<td width="11"><img src="imagens/parahead_left.gif" border="0"></td>

				<td class="parabg">Consulta Clientes Relacionados com o Imóvel</td>
				<td valign="top" width="11"><img src="imagens/parahead_right.gif"
					border="0"></td>
			</tr>
		</table>
		<!--Fim Tabela Reference a Páginação da Tela de Processo-->
		<p>&nbsp;</p>
		<%-- Início --%>
		<table border="0" width="100%" class="fontePequena">
			<tr>
				<td height="31">
				<table bgcolor="#90c7fc" width="100%" class="fontePequena">
					<!--header da tabela interna -->
					<tr>
						<td bgcolor="#79bbfd" colspan="4" height="20">
						<div align="center"><strong>Dados do Imóvel</strong></div>
						</td>
					</tr>
					<tr bordercolor="#FFFFFF" bgcolor="#90c7fc">
						<td width="24%">
						<div align="center"><strong>Inscrição</strong></div>
						</td>

						<td width="17%">
						<div align="center"><strong>Matrícula Imóvel </strong></div>
						</td>
						<td width="27%">
						<div align="center"><strong> Situação de Água</strong></div>
						</td>
						<td width="32%">
						<div align="center"><strong>Situação de Esgoto</strong></div>
						</td>
					</tr>
					<tr bordercolor="#FFFFFF" bgcolor="#ffffff">
						<td height="17">
						<div align="center"><logic:present name="imovelEconomia"
							property="imovelSubcategoria.comp_id.imovel.inscricaoFormatada">
							<bean:write name="imovelEconomia"
								property="imovelSubcategoria.comp_id.imovel.inscricaoFormatada" />
						</logic:present><br>

						</div>
						</td>
						<td>
						<div align="center"><a
							href="ExibirImovelRelacaoClienteImovelAction.do?idImovel=<bean:write name="imovelEconomia" property="imovelSubcategoria.comp_id.imovel.id" />"><bean:write
							name="imovelEconomia"
							property="imovelSubcategoria.comp_id.imovel.id" /></a></div>
						</td>
						<td>
						<div align="center"><bean:write name="imovelEconomia"
							property="imovelSubcategoria.comp_id.imovel.ligacaoAguaSituacao.descricao" /></div>
						</td>
						<td>
						<div align="center"><bean:write name="imovelEconomia"
							property="imovelSubcategoria.comp_id.imovel.ligacaoEsgotoSituacao.descricao" /></div>
						</td>
					</tr>
				</table>
				</td>

			</tr>
			<tr>
				<td height="22">
				<table cellpadding="0" cellspacing="0" width="100%"
					class="fontePequena">
					<tr>
						<td height="0">
						<table bgcolor="#90c7fc" width="100%" class="fontePequena">
							<!--header da tabela interna -->
							<tr bordercolor="#FFFFFF" bgcolor="#90c7fc">
								<td bordercolor="#FFFFFF" bgcolor="#90c7fc" width="90%">
								<div align="center"><strong>Endereço do Imóvel</strong></div>
								</td>
							</tr>

						</table>
						</td>
					</tr>
					<tr>
						<td>
						<div style="overflow: auto; width: 100%; height: 100%;">
						<table align="center" bgcolor="#90c7fc" width="100%"
							class="fontePequena">
							<!--corpo da segunda tabela-->
							<tr bgcolor="#ffffff">
								<td width="90%">
								<div align="center"><logic:present name="imovelEconomia"
									property="imovelSubcategoria.comp_id.imovel.enderecoFormatado">
									<bean:write name="imovelEconomia"
										property="imovelSubcategoria.comp_id.imovel.enderecoFormatado" />
								</logic:present>
								<logic:notPresent name="imovelEconomia"
									property="imovelSubcategoria.comp_id.imovel.enderecoFormatado">
									&nbsp;
								</logic:notPresent>
								</div>
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
		<table border="0" width="100%" class="fontePequena">
			<tr>
				<td height="31">
				<table bgcolor="#90c7fc" width="100%" class="fontePequena">
					<!--header da tabela interna -->
					<tr>
						<td bgcolor="#79bbfd" colspan="7" height="20">
							<div align="center"><strong>Dados da Economia do Imóvel</strong></div>
						</td>
					</tr>
					<tr bordercolor="#FFFFFF" bgcolor="#90c7fc">
						<td width="24%">
						<div align="center"><strong><font color="#000000">Categoria</font></strong></div>
						</td>
						<td width="17%">
						<div align="center"><strong><font color="#000000">SubCategoria</font></strong></div>
						</td>
						<td width="27%">
						<div align="center"><strong> <font color="#333333">Complemento</font></strong></div>
						</td>

						<td width="16%">
						<div align="center"><strong><font color="#333333">No.Moradores</font></strong></div>
						</td>
						<td width="8%"><strong><font color="#333333">Ptos.Utilização</font></strong></td>
						<td width="8%">
						<div align="center"><strong>IPTU</strong></div>
						</td>
						<td width="16%">
						<div align="center"><strong>Área Construída</strong></div>
						</td>
					</tr>
					<tr bordercolor="#FFFFFF" bgcolor="#ffffff">
						<td height="17">
						<div align="center"><logic:present name="imovelEconomia"
							property="imovelSubcategoria.comp_id.subcategoria.categoria">
							<bean:write name="imovelEconomia"
								property="imovelSubcategoria.comp_id.subcategoria.categoria.descricao" />
						</logic:present><br>
						</div>
						</td>
						<td>
						<div align="center"><logic:present name="imovelEconomia"
							property="imovelSubcategoria.comp_id.subcategoria">
							<bean:write name="imovelEconomia"
								property="imovelSubcategoria.comp_id.subcategoria.descricao" />
						</logic:present></div>
						</td>
						<td>
						<div align="center"><logic:present name="imovelEconomia"
							property="complementoEndereco">
							<bean:write name="imovelEconomia" property="complementoEndereco" />
						</logic:present></div>
						</td>
						<td>
						<div align="center"><logic:present name="imovelEconomia"
							property="numeroMorador">
							<bean:write name="imovelEconomia" property="numeroMorador" />
						</logic:present></div>
						</td>
						<td>
						<div align="center"><logic:present name="imovelEconomia"
							property="numeroPontosUtilizacao">
							<bean:write name="imovelEconomia"
								property="numeroPontosUtilizacao" />
						</logic:present></div>
						</td>
						<td>
						<div align="center"><font color="#333333"><logic:present
							name="imovelEconomia" property="numeroIptu">
							<bean:write name="imovelEconomia" property="numeroIptu" />
						</logic:present></font></div>
						</td>
						<td>
						<div align="center"><logic:present name="imovelEconomia"
							property="areaConstruidaFaixa">
							<bean:write name="imovelEconomia"
								property="areaConstruidaFaixa.faixaCompleta" />
						</logic:present></div>
						</td>
					</tr>
				</table>
				</td>
			</tr>
		</table>
		<p>&nbsp;</p>
		<table border="0" width="100%" class="fontePequena">
			<tr>
				<td height="31">
				<table width="100%" bgcolor="#90c7fc" class="fontePequena">
					<!--header da tabela interna -->
					<tr>
						<td bgcolor="#79bbfd" colspan="6" height="20">
						<div align="center"><strong>Relação dos Clientes Relacionados com a
						Economia do Imóvel</strong></div>
						</td>
					</tr>
					<tr bordercolor="#FFFFFF">
						<td rowspan="2" bgcolor="#90c7fc" width="9%">
						<div align="center"><strong>Código do Cliente</strong></div>
						</td>
						<td rowspan="2" bgcolor="#90c7fc" width="32%">
						<div align="center"><strong>Nome do Cliente </strong></div>
						</td>

						<td rowspan="2" bgcolor="#90c7fc" width="9%">
						<div align="center"><strong>Tipo da Relação</strong></div>
						</td>
						<td colspan="2" bgcolor="#90c7fc">
						<div align="center"><strong> Data da Relação </strong></div>
						</td>
						<td rowspan="2" bgcolor="#90c7fc" width="29%">
						<div align="center"><strong>Motivo do Término da Relação</strong></div>
						</td>
					</tr>
					<tr bordercolor="#FFFFFF" bgcolor="#90c7fc">
						<td bordercolor="#000000" bgcolor="#cbe5fe" width="11%">
						<div align="center"><strong>Início</strong></div>
						</td>

						<td bordercolor="#000000" bgcolor="#cbe5fe" width="10%">
						<div align="center"><strong>Término</strong></div>
						</td>
					</tr>
					<%String cor = "#cbe5fe";%>
					<logic:present name="collClienteImovelEconomia">
						<logic:iterate name="collClienteImovelEconomia"
							id="clienteImovelEconomia">
							<%if (cor.equalsIgnoreCase("#cbe5fe")) {
								cor = "#FFFFFF";%>
									<tr align="left" bgcolor="#FFFFFF" height="18">
										<%} else {
								cor = "#cbe5fe";%>
									<tr align="left" bgcolor="#cbe5fe" height="18">
										<%}%>

								<td height="17">
								<div align="center"><a
									href="ExibirClienteRelacaoClienteImovelAction.do?idCliente=<bean:write name="clienteImovelEconomia" property="cliente.id" />"><bean:write
									name="clienteImovelEconomia" property="cliente.id" /></a><br>
								</div>
								</td>
								<td>
								<div align="left"><bean:write name="clienteImovelEconomia"
									property="cliente.nome" /></div>
								</td>
								<td>
								<div align="center"><bean:write name="clienteImovelEconomia"
									property="clienteRelacaoTipo.descricao" /></div>
								</td>
								<td>
								<div align="center"><logic:present name="clienteImovelEconomia"
									property="dataInicioRelacao">
									<bean:write name="clienteImovelEconomia" format="dd/MM/yyyy"
										property="dataInicioRelacao" />
								</logic:present></div>
								</td>
								<td>
								<div align="center"><logic:present name="clienteImovelEconomia"
									property="dataFimRelacao">
									<bean:write name="clienteImovelEconomia" format="dd/MM/yyyy"
										property="dataFimRelacao" />
								</logic:present></div>
								</td>
								<td>
								<div align="left"><logic:present name="clienteImovelEconomia"
									property="clienteImovelFimRelacaoMotivo">
									<bean:write name="clienteImovelEconomia"
										property="clienteImovelFimRelacaoMotivo.descricao" />
								</logic:present>
								<logic:notPresent name="clienteImovelEconomia"
									property="clienteImovelFimRelacaoMotivo">
									&nbsp;
								</logic:notPresent>
								</div>
								</td>
							</tr>
						</logic:iterate>
					</logic:present>
				</table>
				</td>
			</tr>
		</table>
		<table>
			<tr>
				<td colspan="2"><input name="adicionar" class="bottonRightCol"
					value="Voltar Filtro" type="button" onclick="javascript:filtrar();"></td>
				<td align="right"><input name="adicionar" class="bottonRightCol"
					value="Cancelar" type="button" onclick="javascript:cancelar();"></td>
			</tr>
		</table>
		<p>&nbsp;</p>
		</td>
	</tr>
</table>
<%-- Fim --%> <%@ include file="/jsp/util/rodape.jsp"%></form>
</body>
</html:html>
