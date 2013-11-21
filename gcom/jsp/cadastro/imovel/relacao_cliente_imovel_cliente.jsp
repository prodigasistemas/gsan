<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>

<%@ page
	import="java.util.Collection"%>

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

function filtrar() {

	document.forms[0].action = 'ExibirConsultarRelacaoClienteImovelAction.do';
	document.forms[0].submit();

}
function cancelar() {

	document.forms[0].action = 'telaPrincipal.do';
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
<form name="form1">
<%@ include file="/jsp/util/cabecalho.jsp"%> 
<%@ include file="/jsp/util/menu.jsp"%>

<table width="770" border="0" cellspacing="5" cellpadding="0">
	<tr>
		<td width="145" valign="top" class="leftcoltext">
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

				<td class="parabg">Consulta Imóveis Relacionados com o Cliente</td>
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
						<td bgcolor="#79bbfd" colspan="3" height="20">
						<div align="center"><strong>Dados do Cliente</strong></div>
						</td>
					</tr>
					<tr bordercolor="#FFFFFF" bgcolor="#90c7fc">
						<td width="24%">
						<div align="center"><strong>Nome do Cliente</strong></div>
						</td>
						<td width="30%">
						<div align="center"><strong> Profissão/Ramo de Atividade</strong></div>
						</td>
						<td width="14%">
						<div align="center"><strong>CPF/CNPJ</strong></div>
						</td>
					</tr>

					<tr bordercolor="#FFFFFF" bgcolor="#ffffff">
						<td height="17">
						<div align="left"><bean:write name="cliente" property="nome" /><br>
						</div>
						</td>
						<td>
						<div align="left">
						<logic:present name="cliente" property="profissao">
							<bean:write name="cliente" property="profissao.descricao" />
							<logic:present name="cliente" property="ramoAtividade"> /
							</logic:present>
						</logic:present>
						<logic:present name="cliente" property="ramoAtividade">
							 <bean:write name="cliente" property="ramoAtividade.descricao" />
						</logic:present></div>
						</td>
						<td>
						<div align="right"><logic:present name="cliente"
							property="cpfFormatado">
							<logic:notEqual name="cliente" property="cpfFormatado"
								value="null">
								<bean:write name="cliente" property="cpfFormatado" />
							</logic:notEqual>
						</logic:present> <logic:present name="cliente"
							property="cnpjFormatado">
							<logic:notEqual name="cliente" property="cnpjFormatado"
								value="null">
								<bean:write name="cliente" property="cnpjFormatado" />
							</logic:notEqual>
						</logic:present></div>
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
								<div align="center"><strong>Endereço de Correspondência do
								Cliente</strong></div>
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
								<div align="center">&nbsp;
								<logic:present name="enderecoCorrespondencia" property="enderecoFormatado" scope="request">
									<bean:write name="enderecoCorrespondencia" property="enderecoFormatado" scope="request"/>
								</logic:present>
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
				<table width="100%" bgcolor="#90c7fc" class="fontePequena">
					<!--header da tabela interna -->
					<tr>
						<td bgcolor="#79bbfd" colspan="6" height="20">
						<div align="center"><strong>Relação dos Imóveis Relacionados com o
						Cliente</strong></div>
						</td>
		
					</tr>
					<tr bordercolor="#FFFFFF">
						<td rowspan="2" bgcolor="#90c7fc" width="9%">
						<div align="center"><strong>Matrícula do Imóvel</strong></div>
						</td>
						<td rowspan="2" bgcolor="#90c7fc" width="25%">
						<div align="center"><strong>Inscrição do Imóvel</strong></div>
						</td>
						<td rowspan="2" bgcolor="#90c7fc" width="13%">
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
						<td bordercolor="#000000" bgcolor="#cbe5fe" width="12%">
						<div align="center"><strong>Início</strong></div>
						</td>
						<td bordercolor="#000000" bgcolor="#cbe5fe" width="12%">
						<div align="center"><strong>Término</strong></div>
						</td>
					</tr>
					<%String cor = "#cbe5fe";%>
					<bean:define id="coll" 
                     name="collClienteImovel" 
                     type="Collection"/>
					<logic:present name="collClienteImovel">
						
						<% Long qtdImoveis = (Long) session.getAttribute("qtdImoveis"); %>
							
						<logic:iterate name="collClienteImovel" id="consultarClienteRelacaoClienteImovelHelper">
							<%if (cor.equalsIgnoreCase("#cbe5fe")) {
								cor = "#FFFFFF";%>
									<tr align="left" bgcolor="#FFFFFF" height="18">
										<%} else {
								cor = "#cbe5fe";%>
									<tr align="left" bgcolor="#cbe5fe" height="18">
										<%}%>
								<td height="17">
								<div align="center"><a
									href="ExibirImovelRelacaoClienteImovelAction.do?idImovel=<bean:write name="consultarClienteRelacaoClienteImovelHelper" property="clienteImovel.imovel.id" />"><strong><bean:write
									name="consultarClienteRelacaoClienteImovelHelper" property="clienteImovel.imovel.id" /></strong></a><br>
								</div>
								</td>
								<td>
								<div align="center"><strong><bean:write name="consultarClienteRelacaoClienteImovelHelper"
									property="clienteImovel.imovel.inscricaoFormatada" /></strong></div>
								</td>
								<td>
								<div align="left"><strong><bean:write name="consultarClienteRelacaoClienteImovelHelper"
									property="clienteImovel.clienteRelacaoTipo.descricao" /></strong></div>
								</td>
								<td>
								<div align="center">
									<logic:present name="consultarClienteRelacaoClienteImovelHelper"
										property="clienteImovel.dataInicioRelacao">
										<strong>
										<bean:write name="consultarClienteRelacaoClienteImovelHelper" format="dd/MM/yyyy"
											property="clienteImovel.dataInicioRelacao" />
										</strong>	
									</logic:present>
									<logic:notPresent name="consultarClienteRelacaoClienteImovelHelper"
									property="clienteImovel.dataInicioRelacao">
									&nbsp;
									</logic:notPresent>
								</div>
								</td>
								<td>
								<div align="center">
									<logic:present name="consultarClienteRelacaoClienteImovelHelper"
										property="clienteImovel.dataFimRelacao">
										<strong>
										<bean:write name="consultarClienteRelacaoClienteImovelHelper" format="dd/MM/yyyy"
											property="clienteImovel.dataFimRelacao" />
										</strong>	
									</logic:present>
									<logic:notPresent name="consultarClienteRelacaoClienteImovelHelper"
										property="clienteImovel.dataFimRelacao">
										&nbsp;
									</logic:notPresent>
								</div>
								</td>
								<td>
								<div align="left">
								<logic:present name="consultarClienteRelacaoClienteImovelHelper"
									property="clienteImovel.clienteImovelFimRelacaoMotivo">
									<strong>
									<bean:write name="consultarClienteRelacaoClienteImovelHelper"
										property="clienteImovel.clienteImovelFimRelacaoMotivo.descricao" />
									</strong>	
								</logic:present>
								<logic:notPresent name="consultarClienteRelacaoClienteImovelHelper"
									property="clienteImovel.clienteImovelFimRelacaoMotivo">
									&nbsp;
								</logic:notPresent>
								</div>
								</td>
							</tr>
							<tr align="left" bgcolor=<%=cor%> height="18">
							<td colspan="6">
							  <bean:write name="consultarClienteRelacaoClienteImovelHelper" property="enderecoImovel" />
							</td>
							</tr>
							
						</logic:iterate>
						<tr>
			                 <td colspan="6"><strong>Quantidade de imóveis:&nbsp;<%=qtdImoveis%></strong>
			                 </td>
			            </tr>
					</logic:present>
				</table>
				</td>
			</tr>
		</table>
		<p>&nbsp;</p>
		<table border="0" width="100%" class="fontePequena">
			<tr>
				<td>
				<table width="100%" bgcolor="#90c7fc" class="fontePequena">
					<!--header da tabela interna -->
					<tr>
						<td bgcolor="#79bbfd" colspan="8" height="20">
						<div align="center"><strong>Relação das Economias Relacionadas com o
						Cliente</strong></div>
						</td>
					</tr>
					<tr>
						<td rowspan="2" bgcolor="#90c7fc" width="9%">
						<div align="center"><strong>Matrícula do Imóvel</strong></div>
						</td>
						<td rowspan="2" bgcolor="#90c7fc" width="9%">
						<div align="center"><strong>Categoria</strong></div>
						</td>
						<td rowspan="2" bgcolor="#90c7fc" width="14%">
						<div align="center"><strong>Subcategoria</strong></div>
						</td>

						<td rowspan="2" bgcolor="#90c7fc" width="10%">
						<div align="center"><strong>Complemento</strong></div>
						</td>
						<td rowspan="2" bgcolor="#90c7fc" width="9%">
						<div align="center"><strong>Tipo da Relação</strong></div>
						</td>
						<td colspan="2" bgcolor="#90c7fc">
						<div align="center"><strong> Data da Relação </strong></div>
						</td>
						<td rowspan="2" bgcolor="#90c7fc" width="26%">
						<div align="center"><strong>Motivo do Término da Relação</strong></div>
						</td>
					</tr>
					<tr bordercolor="#FFFFFF" bgcolor="#90c7fc">
						<td bordercolor="#000000" bgcolor="#cbe5fe" width="10%">
						<div align="center"><strong>Início</strong></div>
						</td>
						<td bordercolor="#000000" bgcolor="#cbe5fe" width="13%">
						<div align="center"><strong>Término</strong></div>
						</td>
					</tr>
					<%cor = "#cbe5fe";%>
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
								<td bordercolor="#90c7fc">
								<div align="center"><a
									href="ExibirImovelRelacaoClienteImovelAction.do?idImovel=<bean:write name="clienteImovelEconomia" property="imovelEconomia.imovelSubcategoria.comp_id.imovel.id" />"><bean:write
									name="clienteImovelEconomia"
									property="imovelEconomia.imovelSubcategoria.comp_id.imovel.id" /></a></div>
								</td>
								<td bordercolor="#90c7fc">
								<div align="left">
									<logic:present name="clienteImovelEconomia"
										property="imovelEconomia.imovelSubcategoria.comp_id.subcategoria.categoria">
										<bean:write name="clienteImovelEconomia"
											property="imovelEconomia.imovelSubcategoria.comp_id.subcategoria.categoria.descricao" />
									</logic:present>
									<logic:notPresent name="clienteImovelEconomia"
										property="imovelEconomia.imovelSubcategoria.comp_id.subcategoria.categoria">
										&nbsp;
									</logic:notPresent>
								</div>
								</td>
								<td bordercolor="#90c7fc">
									<div align="left">
										<logic:present name="clienteImovelEconomia" property="imovelEconomia.imovelSubcategoria.comp_id.subcategoria">
										<a	href="ExibirImovelEconomiaRelacaoClienteImovelAction.do?idImovelEconomia=<bean:write name="clienteImovelEconomia" property="imovelEconomia.id" />">
											<bean:write name="clienteImovelEconomia" property="imovelEconomia.imovelSubcategoria.comp_id.subcategoria.descricao" />
										</a>
										</logic:present>
										<logic:notPresent name="clienteImovelEconomia" property="imovelEconomia.imovelSubcategoria.comp_id.subcategoria">
										&nbsp;
										</logic:notPresent>
									</div>
								</td>
								<td bordercolor="#90c7fc">
								<div align="left">
								<logic:present name="clienteImovelEconomia"	property="imovelEconomia.complementoEndereco">
									<bean:write name="clienteImovelEconomia"
										property="imovelEconomia.complementoEndereco" />
								</logic:present>
								<logic:notPresent name="clienteImovelEconomia"	property="imovelEconomia.complementoEndereco">
									&nbsp;
								</logic:notPresent>
								</div>
								</td>
								<td bordercolor="#90c7fc">
								<div align="left">
								<logic:present name="clienteImovelEconomia"	property="clienteRelacaoTipo.descricao">
									<bean:write name="clienteImovelEconomia"
										property="clienteRelacaoTipo.descricao" />
								</logic:present>
								<logic:notPresent name="clienteImovelEconomia"	property="clienteRelacaoTipo.descricao">
									&nbsp;
								</logic:notPresent>
								</div>
								</td>
								<td>
								<div align="center"><logic:present name="clienteImovelEconomia"
									property="dataInicioRelacao">
									<bean:write name="clienteImovelEconomia" format="dd/MM/yyyy"
										property="dataInicioRelacao" />
								</logic:present>
								<logic:notPresent name="clienteImovelEconomia"
									property="dataInicioRelacao">
									&nbsp;
								</logic:notPresent>
								</div>
								</td>
								<td>
								<div align="center"><logic:present name="clienteImovelEconomia"
									property="dataFimRelacao">
									<bean:write name="clienteImovelEconomia" format="dd/MM/yyyy"
										property="dataFimRelacao" />
								</logic:present>
								<logic:notPresent name="clienteImovelEconomia"
									property="dataFimRelacao">
									&nbsp;
								</logic:notPresent>
								</div>
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
		<p>&nbsp;</p>
		<table width="100%" align="left">
			<tr>
				<td align="left" width="10%"><input name="adicionar" class="bottonRightCol"
					value="Voltar Filtro" type="button" onclick="javascript:filtrar();"></td>
				<td align="left" width="10%"><input name="adicionar" class="bottonRightCol"
					value="Cancelar" type="button" onclick="javascript:cancelar();"></td>
				<td align="right" valign="top">
					<a href="javascript:toggleBox('demodiv',1);">
                       	<img align="right" border="0" src="<bean:message key='caminho.imagens'/>print.gif"  title="Imprimir Im&oacute;veis relacionados com Cliente "/>
					</a>
				</td>														
			</tr>
		</table>
		</td>
	</tr>
</table>
	<jsp:include page="/jsp/relatorio/escolher_tipo_relatorio.jsp?relatorio=gerarRelatorioImoveisRelacionadosClienteAction.do"/>
	<%@ include file="/jsp/util/rodape.jsp"%>
</form>
</body>
</html:html>
