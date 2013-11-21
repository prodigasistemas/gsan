<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>

<html:html>
<head>
<title>GSAN - Sistema Integrado de Gest&atilde;o de Servi&ccedil;os de Saneamento</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
</head>

<body leftmargin="5" topmargin="5" onload="javascript:resizePageSemLink(810, 405)">

<table width="770" border="0" cellspacing="5" cellpadding="0">
	<tr>
		<td width="770" valign="top" class="centercoltext">
		<table height="100%">
			<tr>
				<td></td>
			</tr>
		</table>
		
		<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
			<tr>
				<td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_left.gif" /></td>
				<td class="parabg">Pesquisa de Arquivo Texto para Atualização Cadastral</td>
				<td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
			</tr>
		</table>
		<p>&nbsp;</p>
		<table width="100%" border="0" bgcolor="#90c7fc">
			<tbody>
			<tr align="left" bgcolor="#90c7fc">
				<td width="10%"  align="center"><strong>Código do Arquivo</strong></td>
				<td width="60%" align="center"><strong>Descrição</strong></td>
				<td width="30%" align="center"><strong>Situação da Transmissão</strong></td>			
			</tr>
			<%String cor = "#cbe5fe";%>
			<%--Esquema de paginação--%>
			<pg:pager isOffset="true" index="half-full" maxIndexPages="10"
				export="currentPageNumber=pageNumber;pageOffset"
				maxPageItems="10" items="${sessionScope.totalRegistros}">
				<pg:param name="pg" />
				<pg:param name="q" />

				<logic:iterate name="arquivosTextoAtualizacaoCadastral" id="arquivoTextoAtualizacaoCadastral">
					<pg:item>
						<%if (cor.equalsIgnoreCase("#cbe5fe")) {
					cor = "#FFFFFF";%>
									<tr bgcolor="#FFFFFF">
									<%} else {
					cor = "#cbe5fe";%>
									<tr bgcolor="#cbe5fe">
										<%}%>
									<td width="10%">
										<div align="left">
											<bean:write name="arquivoTextoAtualizacaoCadastral" property="id"/>
										</div>
												
									</td>
											
									<td width="60%">
										<a href="javascript:enviarDados('<bean:write name="arquivoTextoAtualizacaoCadastral" property="id"/>', '<bean:write name="arquivoTextoAtualizacaoCadastral" property="descricaoArquivo"/>', 'arquivoTextoAtualizacaoCadastral');">
											<bean:write name="arquivoTextoAtualizacaoCadastral" property="descricaoArquivo" />
										</a>
									</td>
									
									<td width="30%">
										<bean:write name="arquivoTextoAtualizacaoCadastral" property="situacaoTransmissaoLeitura.descricaoSituacao" />
									</td>														
						</tr>
					</pg:item>
				</logic:iterate>
				</tbody>
		</table>
		<table width="100%" border="0">
			<tr>
				<td>
				<div align="center"><strong><%@ include
				file="/jsp/util/indice_pager_novo.jsp"%></strong></div>
				</td>
			</tr>
			<tr>
				<td height="24"><input type="button" class="bottonRightCol"	value="Voltar Pesquisa"	onclick="window.location.href='<html:rewrite page="/exibirPesquisarArquivoTextoAtualizacaoCadastralAction.do?objetoConsulta=1"/>'" />
				</td>
			</tr>
		</table>
		</pg:pager> <%-- Fim do esquema de paginação --%></td>
	</tr>
</table>
</body>
</html:html>
