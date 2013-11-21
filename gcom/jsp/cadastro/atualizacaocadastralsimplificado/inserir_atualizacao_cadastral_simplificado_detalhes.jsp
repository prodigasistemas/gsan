<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>

<%@ page import="gcom.util.ConstantesSistema"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<head>

	<%@ include file="/jsp/util/titulo.jsp"%>
	<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
	<link rel="stylesheet"
		href="<bean:message key="caminho.css"/>EstilosCompesa.css"
		type="text/css">
	
	<script language="JavaScript"
		src="<bean:message key="caminho.js"/>util.js"></script>
	<script language="JavaScript"
		src="<bean:message key="caminho.js"/>Calendario.js"></script>
	
</head>

<body leftmargin="5" topmargin="5">

<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>

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
			<td width="625" valign="top" class="centercoltext">
			<table height="100%">
				<tr>
					<td></td>
				</tr>
			</table>

			<table>
				<tr>
					<td></td>

				</tr>
			</table>
			<table width="100%" border="0" align="center" cellpadding="0"
			cellspacing="0">
			<tr>
				<td width="11"><img border="0"
					src="<bean:message key="caminho.imagens"/>parahead_left.gif" /></td>
				<td class="parabg">Atualização Cadastral Simplificado - Detalhes</td>
				<td width="11" valign="top"><img border="0"
					src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
			</tr>
		</table>
		<table>
			<tr><td><strong>Nome:</strong></td><td><html:link title="Baixar o arquivo importado" page="/retornarAquivoTxtAtualizacaoCadastralSimplificadoAction.do" paramName="arquivo" paramProperty="id" paramId="id">${arquivo.nome}</html:link></td></tr>
			<tr><td><strong>Comentário:</strong></td><td>${arquivo.comentario}</td></tr>
			<tr><td><strong>Usuário:</strong></td><td>${arquivo.usuario.login}</td></tr>
			<tr><td><strong>Data:</strong></td><td><bean:write name="arquivo" property="ultimaAlteracao" formatKey="datehour.format" /></td></tr>
			<tr><td><strong>Total de imóveis:</strong></td><td>${arquivo.qtdeTotalImoveis}</td></tr>
			<tr><td><strong>Imóveis com hidrômetro:</strong></td><td>${arquivo.qtdeImoveisComHidrometro}</td></tr>
			<tr><td><strong>Imóveis sem hidrômetro:</strong></td><td>${arquivo.qtdeImoveisSemHidrometro}</td></tr>
			<tr><td><strong>Imóveis com hidrômetro que foram atualizados:</strong></td><td>${arquivo.qtdeImoveisComHidrometroAtualizados} (${arquivo.indiceAtualizacaoHidrometro}%)</td></tr>
			<tr><td><strong>Imóveis que tiveram suas economias atualizadas:</strong></td><td>${arquivo.qtdeImoveisComEconomiasAtualizados} (${arquivo.indiceAtualizacaoEconomias}%)</td></tr>
			<tr><td><strong>Imóveis que tiveram o número do medidor de energia atualizado:</strong></td><td>${arquivo.qtdeImoveisComMedidorEnergiaAtualizados} (${arquivo.indiceAtualizacaoMedidoresEnergia}%)</td></tr>
		</table>
		<p>&nbsp;</p>
		<logic:notEmpty name="criticas">
			<table width="100%" border="0" bgcolor="#90c7fc">
				<thead>
					<tr><th>Matrícula</th><th>Hidrômetro</th><th>Subcat/Econ</th><th>Procedimento</th><th>Crítica</th></tr>
				</thead>
				<tbody>
					<%String cor = "#cbe5fe";%>
					<logic:iterate id="critica" name="criticas">
						<logic:iterate id="linha" name="critica" property="linhas">
							<%if (cor.equalsIgnoreCase("#cbe5fe")) {
						cor = "#FFFFFF";%>
										<tr bgcolor="#FFFFFF">
										<%} else {
						cor = "#cbe5fe";%>
										<tr bgcolor="#cbe5fe">
											<%}%><td>${linha.imovel.id}</td><td>${linha.numeroMedidor}</td><td>${linha.economiasFormatadas}</td><td>${critica.tipo.descricao}</td><td>${critica.descricao}</td></tr>
						</logic:iterate>
					</logic:iterate>
				</tbody>
			</table>
		</logic:notEmpty>
		<table width="100%">
			<tr>
				<td align="center"><input name="Button" type="button"
					class="bottonRightCol" value="Voltar"
					onclick="window.location.href='<html:rewrite page="/exibirInserirAtualizacaoCadastralSimplificadoAction.do?menu=sim"/>'"></td>
			</tr>
		</table>
		<p>&nbsp;</p>
		<tr>
		<td colspan="3"><%@ include file="/jsp/util/rodape.jsp"%>
		</tr>
		</table>
</body>
