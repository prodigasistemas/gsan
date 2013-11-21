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
		<td width="620" valign="top" class="centercoltext">
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
						<div align="center"><bean:write name="imovel"
							property="inscricaoFormatada" /><br>
						</div>
						</td>
						<td>
						<div align="center"><bean:write name="imovel" property="id" /></div>
						</td>
						<td>
						<div align="left"><bean:write name="imovel"
							property="ligacaoAguaSituacao.descricao" /></div>
						</td>
						<td>
						<div align="left"><bean:write name="imovel"
							property="ligacaoEsgotoSituacao.descricao" /></div>
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
						<table bgcolor="#99ccff" width="100%" class="fontePequena">

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
						<table align="center" bgcolor="#99ccff" width="100%"
							class="fontePequena">

							<!--corpo da segunda tabela-->
							<tr bgcolor="#ffffff">
								<td width="90%">
								<div align="center"><logic:present name="imovel"
									property="enderecoFormatado">
									<bean:write name="imovel" property="enderecoFormatado" />
								</logic:present>&nbsp;</div>
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
				<table width="100%" bgcolor="#90c7fc" class="fontePequena">
					<!--header da tabela interna -->
					<tr>
						<td bgcolor="#79bbfd" colspan="6" height="20">
						<div align="center"><strong>Relação dos Clientes Relacionados com
						o Imóvel</strong></div>
						</td>
					</tr>
					<tr>
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
					<logic:present name="collClienteImovel">
						<logic:iterate name="collClienteImovel" id="clienteImovel">
							<%if (cor.equalsIgnoreCase("#cbe5fe")) {
							cor = "#FFFFFF";%>
							<tr align="left" bgcolor="#FFFFFF" height="18">
								<%} else {
							cor = "#cbe5fe";%>
							<tr align="left" bgcolor="#cbe5fe" height="18">
								<%}%>
								<td height="17">
								<div align="center"><a
									href="ExibirClienteRelacaoClienteImovelAction.do?idCliente=<bean:write name="clienteImovel" property="cliente.id" />"><bean:write
									name="clienteImovel" property="cliente.id" /></a><br>
								</div>
								</td>
								<td>
								<div align="left"><bean:write name="clienteImovel"
									property="cliente.nome" /></div>
								</td>
								<td>
								<div align="left"><bean:write name="clienteImovel"
									property="clienteRelacaoTipo.descricao" /></div>
								</td>
								<td>
								<div align="center">
									<logic:present name="clienteImovel"	property="dataInicioRelacao">
										<bean:write name="clienteImovel" format="dd/MM/yyyy"
											property="dataInicioRelacao" />
									</logic:present>
									<logic:notPresent name="clienteImovel" property="dataInicioRelacao">
										&nbsp;
									</logic:notPresent>
								</div>
								
								</td>
								<td>
									<div align="center">
										<logic:present name="clienteImovel" property="dataFimRelacao">
											<bean:write name="clienteImovel" format="dd/MM/yyyy"
												property="dataFimRelacao" />
										</logic:present>
										<logic:notPresent name="clienteImovel" property="dataFimRelacao">
											&nbsp;
										</logic:notPresent>
									</div>
								</td>
								<td>
								<div align="left">
									<logic:present name="clienteImovel"
										property="clienteImovelFimRelacaoMotivo">
										<bean:write name="clienteImovel"
											property="clienteImovelFimRelacaoMotivo.descricao" />
									</logic:present>
									<logic:notPresent name="clienteImovel"
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

		<p>&nbsp;</p>
		<table border="0" width="100%" class="fontePequena">
			<%int count = 0;

				%>
			<logic:present name="collImovelSubCategoriaHelper">
				<logic:iterate name="collImovelSubCategoriaHelper"
					id="imovelSubCategoriaHelper">
					<tr>
						<td height="31">
						<table bgcolor="#90c7fc" width="100%" class="fontePequena">
							<%if (count == 0) {

						%>
							<tr>
								<td bgcolor="#79bbfd" colspan="3" height="20">
								<div align="center"><font color="#000000"><strong>Dados da(s)
								Economia(s) do Imóvel</strong></font></div>
								</td>
							</tr>
							<%}
						count++;

						%>
							<!--header da tabela interna -->
							<tr bordercolor="#FFFFFF" bgcolor="#90c7fc">
								<td bgcolor="#90c7fc" width="43%">
								<div align="center"><strong><font color="#000000">Categoria</font></strong></div>
								</td>

								<td bgcolor="#90c7fc" width="27%">
								<div align="center"><font color="#000000"><strong>SubCategoria</strong></font></div>
								</td>
								<td bgcolor="#90c7fc" width="30%">
								<div align="center"><font color="#000000"><strong>Quantidade de
								Economias</strong></font></div>
								</td>
							</tr>
							<tr bordercolor="#FFFFFF" bgcolor="#ffffff">
								<td height="17">
								<div align="left"><bean:write name="imovelSubCategoriaHelper"
									property="categoria" /></div>
								</td>
								<td>
								<div align="left"><bean:write name="imovelSubCategoriaHelper"
									property="subcategoria" /></div>
								</td>

								<td>
								<div align="center"><bean:write name="imovelSubCategoriaHelper"
									property="quantidadeEconomias" /></div>
								</td>
							</tr>
						</table>
						</td>
					</tr>
					<tr>
						<td height="44">
						<table bgcolor="#90c7fc" width="100%" class="fontePequena">
							<!--header da tabela interna -->
							<tr bgcolor="#90c7fc">
								<td width="20%">
								<div align="center"><font color="#000000"><strong>Complemento</strong></font></div>
								</td>

								<td width="9%">
								<div align="center"><font color="#000000"><strong>No.Moradores</strong></font></div>
								</td>
								<td width="14%">
								<div align="center"><font color="#000000"><strong>Ptos.Utilização</strong></font></div>
								</td>
								<td width="13%">
								<div align="center"><font color="#000000"><strong>IPTU</strong></font></div>
								</td>
								<td width="11%">
								<div align="center"><font color="#000000"><strong>Área
								Construída </strong></font></div>
								</td>
								<td width="33%">
								<div align="center"><font color="#000000"><strong>Cliente
								Usuário </strong></font></div>
								</td>

							</tr>
							<%cor = "#cbe5fe";%>
							<logic:present name="imovelSubCategoriaHelper"
								property="colecaoImovelEconomia">
								<logic:iterate name="imovelSubCategoriaHelper"
									property="colecaoImovelEconomia" id="clienteImovelEconomia">
									<%if (cor.equalsIgnoreCase("#cbe5fe")) {
									cor = "#FFFFFF";%>
									<tr align="left" bgcolor="#FFFFFF" height="18">
										<%} else {
									cor = "#cbe5fe";%>
									<tr align="left" bgcolor="#cbe5fe" height="18">
										<%}%>
										<td align="left">
										<div align="left">
										<logic:present name="clienteImovelEconomia"
											property="imovelEconomia.complementoEndereco">
											<logic:notEqual value="" name="clienteImovelEconomia"
												property="imovelEconomia.complementoEndereco">
												<bean:write name="clienteImovelEconomia"
													property="imovelEconomia.complementoEndereco" />
											</logic:notEqual>
											<logic:equal value="" name="clienteImovelEconomia"
												property="imovelEconomia.complementoEndereco">
                		-
                	</logic:equal>
										</logic:present> <logic:notPresent
											name="clienteImovelEconomia"
											property="imovelEconomia.complementoEndereco">
                	-
                </logic:notPresent> </div>
										</td>
										<td align="left">
										<div align="center"><font color="#333333"><logic:present
											name="clienteImovelEconomia"
											property="imovelEconomia.numeroMorador">
											<bean:write name="clienteImovelEconomia"
												property="imovelEconomia.numeroMorador" />
										</logic:present></font></div>
										</td>
										<td align="left">
										<div align="center"><font color="#333333"><logic:present
											name="clienteImovelEconomia"
											property="imovelEconomia.numeroPontosUtilizacao">
											<bean:write name="clienteImovelEconomia"
												property="imovelEconomia.numeroPontosUtilizacao" />
										</logic:present></font></div>
										</td>
										<td>
										<div align="center"><font color="#333333"><logic:present
											name="clienteImovelEconomia"
											property="imovelEconomia.numeroIptu">
											<bean:write name="clienteImovelEconomia"
												property="imovelEconomia.numeroIptu" />
										</logic:present></font></div>
										</td>
										<td>
										<div align="center"><font color="#333333"><logic:present
											name="clienteImovelEconomia"
											property="imovelEconomia.areaConstruidaFaixa">
											<bean:write name="clienteImovelEconomia"
												property="imovelEconomia.areaConstruidaFaixa.faixaCompleta" />
										</logic:present></font></div>
										</td>
										<td align="left">
										<div align="left"><logic:present name="clienteImovelEconomia"
											property="cliente.nome">
											<a
											href="ExibirImovelEconomiaRelacaoClienteImovelAction.do?idImovelEconomia=<bean:write name="clienteImovelEconomia" property="imovelEconomia.id" />">
											<bean:write name="clienteImovelEconomia"
												property="cliente.nome" />
											</a>
										</logic:present></div>
										</td>
									</tr>
								</logic:iterate>
							</logic:present>
						</table>
						</td>
					</tr>
				</logic:iterate>
			</logic:present>

		</table>
		<p>&nbsp;</p>
		<table width="100%">
			<tr>
				<td width="10%" align="left"><input name="adicionar" class="bottonRightCol"
					value="Voltar Filtro" type="button" onclick="javascript:filtrar();"></td>
				<td width="10%" align="left"><input name="adicionar" class="bottonRightCol"
					value="Cancelar" type="button" onclick="javascript:cancelar();"></td>
				<td align="right" valign="top">
					<a href="javascript:toggleBox('demodiv',1);"> 
                       	<img align="right" border="0" src="<bean:message key='caminho.imagens'/>print.gif"  title="Imprimir Clientes relacionados com Im&oacutevel; "/>
					</a>
				</td>														
			</tr>
		</table>
		<p>&nbsp;</p>
		</td>
	</tr>

</table>

	<jsp:include page="/jsp/relatorio/escolher_tipo_relatorio.jsp?relatorio=gerarRelatorioClientesRelacionadosImovelAction.do"/>
	<%@ include file="/jsp/util/rodape.jsp"%></form>
</body>
</html:html>
