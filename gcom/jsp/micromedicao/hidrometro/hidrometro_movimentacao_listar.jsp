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
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>

</head>

<body leftmargin="5" topmargin="5">
<form>

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
		<td valign="top" bgcolor="#003399" class="centercoltext">
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
				<td class="parabg">Lista das Movimenta&ccedil;&otilde;es de
				Hidr&ocirc;metro</td>
				<td width="11"><img border="0"
					src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
			</tr>
			<tr>
				<td height="5" colspan="3"></td>
			</tr>
		</table>
		
		<table width="100%" border="0">
			<tr>
				<td height="23"><font color="#000000"
					style="font-size:10px" face="Verdana, Arial, Helvetica, sans-serif"><strong>Movimenta&ccedil;&otilde;es
				de Hidr&ocirc;metro:</strong></font></td>
				<td align="right"><a
					href="javascript: abrirPopupHelp('/gsan/help/help.jsp?mapIDHelpSet=hidrometroMovimentacaoConsultar-2-lista', 500, 700);"><span
					style="font-weight: bold"><font color="#3165CE">Ajuda</font></span></a></td>
			</tr>
			</table>
			<table width="100%" border="0">
			<tr>
				<td colspan="7" bgcolor="#000000" height="2"></td>
			</tr>
			<tr bordercolor="#90c7fc">
				<td colspan="7">
				<table width="100%" bgcolor="#99CCFF">
					<tr bgcolor="#90c7fc">
						<td rowspan="2" align="center"><strong>Data</strong></td>
						<td rowspan="2" align="center"><strong>Hora</strong></td>
						<td rowspan="2" align="center"><strong>Motivo</strong></td>
						<td colspan="2" align="center"><strong>Local Armazenagem </strong></td>
						<td rowspan="2" align="center"><strong>Hidr&ocirc;metros</strong></td>
					</tr>
					<tr bgcolor="#cbe5fe">
						<td align="center"><strong>Origem</strong></td>
						<td align="center"><strong>Destino</strong></td>
					</tr>
					<pg:pager isOffset="true" index="half-full" maxIndexPages="10"
						export="currentPageNumber=pageNumber;pageOffset" maxPageItems="10"
						items="${sessionScope.totalRegistros}">
						<pg:param name="pg" />
						<pg:param name="q" />
						<logic:present name="colecaoHidrometroMovimentacao">
							<%int cont = 0;%>
							<logic:iterate name="colecaoHidrometroMovimentacao"
								id="hidrometro">
								<pg:item>
									<%cont = cont + 1;
								if (cont % 2 == 0) {%>
									<tr bgcolor="#cbe5fe">
										<%} else {

								%>
									<tr bgcolor="#FFFFFF">
										<%}%>
										<td width="15%" align="center"><html:link
											page="/exibirMovimentacaoHidrometroAction.do"
											paramName="hidrometro" paramProperty="id"
											paramId="idRegistroAtualizacao">
											<bean:write name="hidrometro" property="data"
												formatKey="date.format" />
										</html:link></td>
										<td width="13%" align="center"><bean:write name="hidrometro"
											property="hora" formatKey="hourminute.format" /></td>
										<td width="14%"><bean:write name="hidrometro"
											property="hidrometroMotivoMovimentacao.descricao" /></td>
										<td width="21%"><bean:write name="hidrometro"
											property="hidrometroLocalArmazenagemOrigem.descricaoAbreviada" />
										</td>

										<td width="21%"><bean:write name="hidrometro"
											property="hidrometroLocalArmazenagemDestino.descricaoAbreviada" />
										</td>

										<td width="16%" align="center"><bean:write name="hidrometro"
											property="quantidade" /></td>

									</tr>
								</pg:item>
							</logic:iterate>

						</logic:present>
				</table>
				</td>
			</tr>
			<tr>
				<td colspan="7">
				<table width="100%" border="0">
					<tr>
						<td><input type="button" class="bottonRightCol"
							value="Voltar Filtro"
							onclick="window.location.href='<html:rewrite page="/exibirFiltrarMovimentacaoHidrometroAction.do"/>'"
							align="left" style="width: 80px;"></td>
					<td valign="top">
					<div align="right"><a href="javascript:toggleBox('demodiv',1);"> <img
						border="0" src="<bean:message key="caminho.imagens"/>print.gif"
						title="Imprimir Movimentações de Hidrometro" /> </a></div>
					</td>
					</tr>
				</table>
				<table width="100%" border="0">
					<tr>
						<td align="center"><strong><%@ include
							file="/jsp/util/indice_pager_novo.jsp"%></strong></td>
					</tr>
				</table>
				</td>
			</tr>
		</table>
		</pg:pager>
		<p>&nbsp;</p>
		</td>
	</tr>
</table>
<jsp:include
		page="/jsp/relatorio/escolher_tipo_relatorio.jsp?relatorio=gerarRelatorioMovimentacaoHidrometroAction.do" />
<%@ include file="/jsp/util/rodape.jsp"%>
</form>
</body>
</html:html>
