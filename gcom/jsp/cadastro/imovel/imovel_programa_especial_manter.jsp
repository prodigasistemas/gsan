<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan"%>

<%@page import="gcom.cadastro.imovel.ImovelProgramaEspecial"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>

<head>

<%@ include file="/jsp/util/titulo.jsp"%>

<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript">

function facilitador(objeto){
	if (objeto.id == "0" || objeto.id == undefined){
		objeto.id = "1";
		marcarTodos();
	}
	else{
		objeto.id = "0";
		desmarcarTodos();
	}
}

function verficarSelecao(objeto){

	if (CheckboxNaoVazio(objeto)){
		if (confirm ("Confirma remoção?")) {
			document.forms[0].action = "/gsan/removerProjetoAction.do"
			document.forms[0].submit();
		 }
	}
 }

</script>

</head>

<body leftmargin="5" topmargin="5">

<html:form action="/exibirSuspenderImovelProgramaEspecialAction"
	name="ManutencaoRegistroActionForm"
	type="gcom.gui.ManutencaoRegistroActionForm" method="post"
	onsubmit="return CheckboxNaoVazio(document.ManutencaoRegistroActionForm.idRegistrosRemocao) && confirm('Confirma remoção?')">

	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>

	<table width="770" border="0" cellspacing="5" cellpadding="0">
		<tr>
			<td width="135" valign="top" class="leftcoltext">
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
			<td width="625" valign="top" bgcolor="#003399" class="centercoltext">
			<table height="100%">

				<tr>
					<td></td>
				</tr>
			</table>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img border="0" src="imagens/parahead_left.gif" /></td>
					<td class="parabg">Manter Imóvel em Programa Especial</td>
					<td width="11" valign="top"><img border="0"
						src="imagens/parahead_right.gif" /></td>
				</tr>
			</table>
			<!--Fim Tabela Reference a Páginação da Tela de Processo-->
			<p>&nbsp;</p>
			<table width="100%" align="center" cellpadding="0" cellspacing="0">
				<!--corpo da segunda tabela-->
				<tr>
					<td colspan="5" height="23"><font color="#000000"
						style="font-size:10px"
						face="Verdana, Arial, Helvetica, sans-serif"><strong>Imóveis
					Encontrados:</strong></font></td>
				</tr>
				<tr>
					<!--<td colspan="4" bgcolor="#3399FF"> -->
					<td colspan="5" bgcolor="#000000" height="2" valign="baseline"></td>
				</tr>
				<tr>
					<td>
					<table width="100%" align="center" bgcolor="#90c7fc">
						<tr bgcolor="#79bbfd">
							<td width="10%" bgcolor="#90c7fc">
							<div align="center"><strong>Matrícula</strong></div>
							</td>
							<td width="72%" bgcolor="#90c7fc">
							<div align="center"><strong>Observação dos Documentos</strong></div>
							</td>	
							<td width="6%" bgcolor="#90c7fc">
							<div align="center"><strong>Data de Apresentação dos Documentos</strong></div>
							</td>
							<td width="6%" bgcolor="#90c7fc">
							<div align="center"><strong>Mês/Ano de entrada</strong></div>
							</td>
							<td width="6%" bgcolor="#90c7fc">
							<div align="center"><strong>Mês/Ano de saída</strong></div>
							</td>
						</tr>
						<%--Esquema de paginação--%>
						<pg:pager isOffset="true" index="half-full" maxIndexPages="10"
							export="currentPageNumber=pageNumber;pageOffset"
							maxPageItems="10" items="${sessionScope.totalRegistros}">
							<pg:param name="pg" />
							<pg:param name="q" />
							<logic:present name="colecaoImoveisProgramasEspeciais">
								<%int cont = 0;%>
								<logic:iterate name="colecaoImoveisProgramasEspeciais" id="imovel" type="ImovelProgramaEspecial">
									<pg:item>
										<%cont = cont + 1;
			if (cont % 2 == 0) {%>
										<tr bgcolor="#cbe5fe">
											<%} else {

			%>
										<tr bgcolor="#FFFFFF">
											<%}%>
											<td width="10%">
											<html:link
												href="/gsan/exibirAtualizarImovelProgramaEspecialAction.do"
												paramId="imovelProgramaEspecialID" paramProperty="id" paramName="imovel"
												title="<%="" + imovel.getId()%>">
											<bean:write name="imovel" property="imovel.id" />
											</html:link>
											</td>
											<td width="78%" align="left">
												<bean:write name="imovel" property="descricaoDocumentos"/>							
											</td>
											<td width="6%" align="left">
											<bean:write name="imovel" property="dataApresentacaoDocumentos" format="dd/MM/yyyy" />
											</td>
											<td width="6%" align="left">
											<bean:write name="imovel" property="mesAnoInicio"/>
											</td>
											<td width="6%" align="left">
											<bean:write name="imovel" property="mesAnoSaida" />
											</td>
										</tr>
									</pg:item>
								</logic:iterate>
							</logic:present>
					</table>
					</td>
				</tr>
			</table>

			<table width="100%" border="0">
				<tr>
					<td colspan="2">
					<div align="center"><strong><%@ include
						file="/jsp/util/indice_pager_novo.jsp"%></strong></div>
					</td>
				</tr>
				</pg:pager>
				<tr>
					<td width="233">
						<font color="#FF0000"> <input type="button"
							name="buttonFiltro" class="bottonRightCol" value="Voltar Filtro"
							onClick="javascript:window.location.href='/gsan/exibirFiltrarImovelProgramaEspecialAction.do?menu=sim&atualizar=sim'">
						</font>
					</td>
					<td valign="top">
							<div align="right"><a
								href="javascript:toggleBox('demodiv',1);">
							<img border="0"
								src="<bean:message key="caminho.imagens"/>print.gif"
								title="Imprimir Relatório" /> </a></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
	<jsp:include page="/jsp/relatorio/escolher_tipo_relatorio.jsp?relatorio=gerarRelatorioManterImovelProgramaEspecialAction.do"/>
	<%@ include file="/jsp/util/rodape.jsp"%>
</html:form>
</body>
</html:html>
