<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>
<%@page import="gcom.cobranca.bean.CobrancaDocumentoHelper"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<head>
<html:html>
<%@ include file="/jsp/util/titulo.jsp"%>

<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">

<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js"></script>

</head>

	<logic:present name="ehPopup">
		<body leftmargin="5" topmargin="5" onload="resizePageSemLink(630, 600)">
	</logic:present>

	<logic:notPresent name="ehPopup">
		<body leftmargin="5" topmargin="5">
	</logic:notPresent>
<html:form action="/gerarRelatorioFiltrarDocumentoCobrancaAction" method="post" >
	<logic:notPresent name="ehPopup">

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
	
			<!--  %@ include file="/jsp/util/mensagens.jsp"%> -->
	
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
	
		</logic:notPresent>
		
		<logic:present name="ehPopup">
		
			<table width="600" border="0" cellspacing="5" cellpadding="0">
				<tr>	
					<td width="600" valign="top" class="centercoltext">
		
						<table height="100%">
							<tr>
								<td></td>
							</tr>
						</table>

		</logic:present>

		<table width="100%" border="0" align="center" cellpadding="0"
			cellspacing="0">
			<tr>
				<td width="11"><img border="0"
					src="<bean:message key="caminho.imagens"/>parahead_left.gif" /></td>
				<td class="parabg">Consultar Documentos de Cobrança</td>
				<td width="11"><img border="0"
					src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
			</tr>
		</table>



		<table width="590" border="0" align="center" cellpadding="0"
			cellspacing="0">
			<tr>
				<td colspan="4" height="23">
				<font color="#000000" style="font-size: 10px"
					face="Verdana, Arial, Helvetica, sans-serif">
				<strong>Documentos de Cobrança encontrados:</strong>
				</font>
				</td>
			</tr>

			<tr>
				<td bgcolor="#000000" height="2"></td>
			</tr>

			<tr>
				<td>
				<table width="590" bgcolor="#99CCFF">
					<tr bgcolor="#99CCFF">
						<td align="center" width="70"><FONT COLOR="#000000"><strong>Imóvel</strong></FONT></td>
						<td align="center" width="70"><FONT COLOR="#000000"><strong>DOC</strong></FONT></td>
						<td align="right" width="95"><FONT COLOR="#000000"><strong>Ação
						de Cobrança</strong></FONT></td>
						<td align="right" width="75"><FONT COLOR="#000000"><strong>Emissão</strong></FONT></td>
						<td align="right" width="85"><FONT COLOR="#000000"><strong>Forma
						Emissão</strong></FONT></td>
						<td align="right" width="90"><FONT COLOR="#000000"><strong>Tipo
						Doc.</strong></FONT></td>
						<td align="center" width="70"><FONT COLOR="#000000"><strong>Empresa</strong></FONT></td>
						<td align="center" width="70"><FONT COLOR="#000000"><strong>Vl.
						Doc.</strong></FONT></td>
						<td align="center" width="60"><FONT COLOR="#000000"><strong>Qtde.
						Itens</strong></FONT></td>
						<td align="center" width="30"><FONT COLOR="#000000"><strong>Sit. OS</strong></FONT></td>
					</tr>
				</table>
				</td>
			</tr>
			<tr>
				<td>

				<table width="590" bgcolor="#99CCFF">

					<%--Esquema de paginação--%>
					<pg:pager isOffset="true" index="half-full" maxIndexPages="10"
							export="currentPageNumber=pageNumber;pageOffset"
							maxPageItems="10" items="${sessionScope.totalRegistros}">
							<pg:param name="pg" />
							<pg:param name="q" />
						<logic:present name="colecaoDocumentoCobranca">
							<%int cont = 1;%>
							<logic:iterate name="colecaoDocumentoCobranca"
								id="cobrancaDocumentoHelper" type="CobrancaDocumentoHelper">
								<pg:item>
								<%	cont = cont + 1;
									if (cont % 2 == 0) {	%>
										<tr bgcolor="#FFFFFF">
								<%	} else {	%>
										<tr bgcolor="#cbe5fe">
								<%	}	%>
										
										<td width="70" align="center">
											<logic:empty name="cobrancaDocumentoHelper"
												property="cobrancaDocumento.imovel">
										   		&nbsp;
										 	</logic:empty>
											<logic:notEmpty name="cobrancaDocumentoHelper"
												property="cobrancaDocumento.imovel">
										   		<bean:write name="cobrancaDocumentoHelper"
												property="cobrancaDocumento.imovel.id"/>
										 	</logic:notEmpty>
										</td>

										<td width="70" align="center">

											<logic:present name="ehPopup">

												<logic:notEmpty name="caminhoRetornoTelaPesquisaDocumentoCobranca">
													<div align="center">
													  	<a href="javascript:enviarDadosParametros('<bean:write name="caminhoRetornoTelaPesquisaDocumentoCobranca"/>','<bean:write name="cobrancaDocumentoHelper" property="cobrancaDocumento.id"/>', '<bean:write name="cobrancaDocumentoHelper" property="cobrancaDocumento.documentoTipo.descricaoDocumentoTipo"/>', 'documentoCobranca');">
															${cobrancaDocumentoHelper.cobrancaDocumento.imovel.id}
														</a>
													</div>
												</logic:notEmpty>
					
												<logic:empty name="caminhoRetornoTelaPesquisaDocumentoCobranca">
													<a href="javascript:enviarDados('<bean:write name="cobrancaDocumentoHelper" property="cobrancaDocumento.id"/>', '<bean:write name="cobrancaDocumentoHelper" property="cobrancaDocumento.documentoTipo.descricaoDocumentoTipo"/>', 'documentoCobranca');">
														${cobrancaDocumentoHelper.cobrancaDocumento.imovel.id}
													</a>
												</logic:empty>

											</logic:present>

											<logic:notPresent name="ehPopup">
											   <html:link href="/gsan/exibirApresentarItensDocumentoCobrancaAction.do" 
													paramId="cobrancaDocumentosID" 
													paramProperty="cobrancaDocumento.id" 
													paramName="cobrancaDocumentoHelper" 
													title="<%="" + cobrancaDocumentoHelper.getCobrancaDocumento().getId()%>">
													${cobrancaDocumentoHelper.cobrancaDocumento.id}
											   </html:link>
											  
											  
											</logic:notPresent>
										</td>
										
										<td width="95" align="left">
											<logic:equal name="cobrancaDocumentoHelper"
												property="cobrancaDocumento.documentoEmissaoForma.id"
												value="<%= "" + gcom.cobranca.DocumentoEmissaoForma.CRONOGRAMA.intValue() %>">
											

												${cobrancaDocumentoHelper.cobrancaDocumento.cobrancaAcao.descricaoCobrancaAcao}

											</logic:equal> 
											
											<logic:equal name="cobrancaDocumentoHelper"
												property="cobrancaDocumento.documentoEmissaoForma.id"
												value="<%= "" + gcom.cobranca.DocumentoEmissaoForma.EVENTUAL.intValue() %>">
												
												${cobrancaDocumentoHelper.cobrancaDocumento.cobrancaAcao.descricaoCobrancaAcao}											
											</logic:equal> 
											
											<logic:equal name="cobrancaDocumentoHelper"
												property="cobrancaDocumento.documentoEmissaoForma.id"
												value="<%= "" + gcom.cobranca.DocumentoEmissaoForma.INDIVIDUAL.intValue() %>">
												
											</logic:equal>
										</td>
										
										<td width="75" align="center">
											<bean:write name="cobrancaDocumentoHelper"
												property="cobrancaDocumento.emissao"
												formatKey="datehour.format" />
										</td>
										
										<td width="85" align="left">
											<%=cobrancaDocumentoHelper.getCobrancaDocumento().getDocumentoEmissaoForma().getDescricaoDocumentoEmissaoForma().substring(0, 3) %>
										</td>
										
										<td width="90" align="center">
											${cobrancaDocumentoHelper.cobrancaDocumento.documentoTipo.descricaoDocumentoTipo}
										</td>
										
										<td width="70" align="center">
											 <logic:empty name="cobrancaDocumentoHelper"
												property="cobrancaDocumento.empresa">
										   		&nbsp;
										 	</logic:empty>
										 	<logic:notEmpty name="cobrancaDocumentoHelper"
												property="cobrancaDocumento.empresa">
										   		<bean:write name="cobrancaDocumentoHelper"
												property="cobrancaDocumento.empresa.descricaoAbreviada"/>
										 	</logic:notEmpty>
											
										</td>
										
										<td width="70" align="right">
											<bean:write
												name="cobrancaDocumentoHelper"
												property="cobrancaDocumento.valorDocumento"
												formatKey="money.format" />
										</td>
										
										<td width="60" align="center">
											${cobrancaDocumentoHelper.quantidadeItensCobrancaDocumento}
										</td>
										<td width="30" align="center">
										 <logic:notEmpty name="cobrancaDocumentoHelper" property="idOrdemServico">
										   ${cobrancaDocumentoHelper.situacaoOrdemServico}
										 </logic:notEmpty>
										 <logic:empty name="cobrancaDocumentoHelper" property="idOrdemServico">
										   &nbsp;
										 </logic:empty>
										</td>
									</tr>
								</pg:item>
							</logic:iterate>
						</logic:present>
				</table>

				</td>
			</tr>
			<tr bordercolor="#90c7fc">
				<td>
				<table width="100%">
					<tr>
						<td><input name="Button" type="button" class="bottonRightCol"
							value="Voltar Filtro"
							onclick="window.location.href='/gsan/exibirFiltrarDocumentosCobrancaAction.do'"
							align="left" style="width: 80px;"></td>
						<td valign="top">
												<div align="right"><a href="javascript:toggleBox('demodiv',1);">
												<img border="0"	src="<bean:message key="caminho.imagens"/>print.gif" title="Imprimir Documentos de Cobrança" /> </a></div>
											</td>
					</tr>
				</table>
				</td>
			</tr>
		</table>

		<table width="100%" border="0">
			<tr><td>
				<div align="center"><strong><%@ include
						file="/jsp/util/indice_pager_novo.jsp"%></strong></div>
					</td>
			</tr>
		</table>

		<br>
		</pg:pager></td>
	</tr>
</table>
<p>&nbsp;</p>
<jsp:include page="/jsp/relatorio/escolher_tipo_relatorio.jsp?relatorio=gerarRelatorioFiltrarDocumentoCobrancaAction.do" />
	<logic:notPresent name="ehPopup">
		<%@ include file="/jsp/util/rodape.jsp"%>
	</logic:notPresent>
</html:form>
</body>
</html:html>
