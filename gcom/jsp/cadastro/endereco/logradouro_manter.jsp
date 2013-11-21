<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan" %>
<%@ page import="gcom.util.Pagina,gcom.util.ConstantesSistema,java.util.Collection"%>
<%@ page import="gcom.atendimentopublico.ordemservico.OSProgramacaoCalibragem"%>
<%@ page import="gcom.cadastro.endereco.bean.ManterLogradouroHelper"%>

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
<script>
<!--
function facilitador(objeto){
	if (objeto.value == "0"){
		objeto.value = "1";
		marcarTodos();
	}
	else{
		objeto.value = "0";
		desmarcarTodos();
	}
}

function remover(objeto){

	if (CheckboxNaoVazio(objeto)){
		if (confirm ("Confirma remoção?")) {
			document.forms[0].action = "/gsan/removerLogradouroAction.do"
			document.forms[0].submit();
		 }
	}
}

function atualizar(objeto){
	if (CheckboxNaoVazioAtt(objeto)){
		if (confirm ("Confirma atualização?")) {
			document.forms[0].action = "/gsan/atualizarLogradouroGrauImportanciaAction.do"
			document.forms[0].submit();
		 }
	}
}
-->
</script>

</head>

<body leftmargin="5" topmargin="5">


<html:form action="/removerLogradouroAction"
	name="ManutencaoRegistroActionForm"
	type="gcom.gui.ManutencaoRegistroActionForm" method="post"
	onsubmit="return CheckboxNaoVazio(document.ManutencaoRegistroActionForm.idRegistrosRemocao) && confirm('Confirma remoção?')">
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
					<td class="parabg">
						<logic:present name="indicadorImportanciaLogradouro" >
							Manter Importância Logradouro
						</logic:present>
						<logic:notPresent name="indicadorImportanciaLogradouro">
							Manter Logradouro
						</logic:notPresent>
					</td>

					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
				<tr>
					<td height="5" colspan="3"></td>
				</tr>
			</table>

			<table width="100%" cellpadding="0" cellspacing="0">
				<tr>
					<td colspan="5" height="23"><font color="#000000"
						style="font-size:10px"
						face="Verdana, Arial, Helvetica, sans-serif"><strong>Logradouros
					Encontrados:</strong></font></td>
				</tr>
			</table>
			
			<table width="100%" cellpadding="0" cellspacing="0">
				<tr>
					<!--<td colspan="4" bgcolor="#3399FF"> -->
					<td colspan="5" bgcolor="#000000" height="2"></td>
				</tr>
				<tr bordercolor="#000000">
					<td width="6%" bgcolor="#90c7fc" height="20">
					<div align="center"><strong><a href="javascript:facilitador(this);">Todos</a></strong></div>
					</td>
					<td width="25%" align="center" bgcolor="#90c7fc"><strong>Nome Logradouro</strong></td>
					<td width="33%" align="center" bgcolor="#90c7fc"><strong>Bairro(s)</strong></td>
					<td width="19%" align="center" bgcolor="#90c7fc"><strong>Munic&iacute;pio</strong></td>
					<logic:present name="indicadorImportanciaLogradouro" >
						<td width="19%" align="center" bgcolor="#90c7fc"><strong>Grau de Importância</strong></td>
					</logic:present>
									
				</tr>

				<tr>
					<td colspan="5">
					<table width="100%" bgcolor="#99CCFF">


						<%--Esquema de paginação--%>
						<pg:pager isOffset="true" index="half-full" maxIndexPages="10"
							export="currentPageNumber=pageNumber;pageOffset"
							maxPageItems="10" items="${sessionScope.totalRegistros}">
							<pg:param name="pg" />
							<pg:param name="q" />
							<logic:present name="logradouros">
								<%int cont = 1;%>
								<logic:iterate name="logradouros" id="logradouro" type="ManterLogradouroHelper">
									<pg:item>
										<%cont = cont + 1;
			if (cont % 2 == 0) {%>
										<tr bgcolor="#FFFFFF">
											<%} else {

			%>
										<tr bgcolor="#cbe5fe">
											<%}%>
											<logic:equal name="logradouro" property="logradouro.indicadorUso"
												value="<%=ConstantesSistema.INDICADOR_USO_DESATIVO.toString()%>">

												<td width="6%">
												<div align="center"><input type="checkbox"
													name="idRegistrosRemocao"
													value="<bean:write name="logradouro" property="id"/>" /></div>
												</td>
												<logic:present name="indicadorImportanciaLogradouro" >
													<td width="25%"><font color="#CC0000"> 
														<bean:write name="logradouro" property="logradouro.descricaoFormatada" />
													</font></td>
												</logic:present>
												<logic:notPresent name="indicadorImportanciaLogradouro" >
													<td width="25%"><font color="#CC0000"> 
														<a href="exibirAtualizarLogradouroAction.do?manter=1&idRegistroAtualizacao=<bean:write name="logradouro" property="logradouro.id" />">
														<bean:write name="logradouro" property="logradouro.descricaoFormatada" />
													</a></font></td>
												</logic:notPresent>
												<td width="33%"><font color="#CC0000"> 
												<logic:empty
													name="logradouro" property="bairro">
                      								&nbsp;
                     							</logic:empty> <logic:notEmpty
													name="logradouro" property="bairro">
													<bean:write name="logradouro" property="bairro" />
												</logic:notEmpty> </font></td>

												<td width="19%"><font color="#CC0000"> <bean:write
													name="logradouro" property="logradouro.municipio.nome" /> </font></td>

											</logic:equal>

											<logic:notEqual name="logradouro" property="logradouro.indicadorUso"
												value="<%=ConstantesSistema.INDICADOR_USO_DESATIVO.toString()%>">

												<td width="6%">
												<div align="center"><input type="checkbox"
													name="idRegistrosRemocao"
													value="<bean:write name="logradouro" property="logradouro.id"/>" /></div>
												</td>
												<logic:present name="indicadorImportanciaLogradouro" >
													<td width="25%"><bean:write name="logradouro" property="logradouro.descricaoFormatada" />
													</td>
												</logic:present>
												<logic:notPresent name="indicadorImportanciaLogradouro">
													<td width="25%"><a href="exibirAtualizarLogradouroAction.do?manter=1&idRegistroAtualizacao=<bean:write name="logradouro" property="logradouro.id" />">
														<bean:write name="logradouro" property="logradouro.descricaoFormatada" />
													</a></td>
												</logic:notPresent>
												
												<td width="33%"><logic:empty name="logradouro"
													property="bairro">
                      								&nbsp;
                     							</logic:empty> <logic:notEmpty
													name="logradouro" property="bairro">
													<bean:write name="logradouro" property="bairro" />
												</logic:notEmpty></td>

												<td width="19%"><bean:write name="logradouro"
													property="logradouro.municipio.nome" /></td>
													
												<logic:present name="indicadorImportanciaLogradouro" >
													<td>	
														<!--<bean:write name="logradouro" property="grauImportancia" />	-->										
														<select name="grauImportancia_<%=""+ logradouro.getLogradouro().getId() %>">
															<option  value=""> </option>	
															<logic:iterate name="osProgramacaoCalibragem" id="calibragem" type="OSProgramacaoCalibragem">
																<logic:notEmpty name="logradouro" property="logradouro.programaCalibragem">
															
																<%
																	if (logradouro.getLogradouro().getProgramaCalibragem().getId()
																		.equals(calibragem.getId())){ %>
																		
																		<option  value="<%=""+ calibragem.getId() %>" selected="selected"><%=""
																		+ calibragem.getGrauImportancia()%> </option>													
																		
																<% 	}
																	else { %>
																
																		<option value="<%=""+ calibragem.getId() %>"><%=""
																		+ calibragem.getGrauImportancia()%> </option>												
																
																<% 	} %>
																
																</logic:notEmpty>
																<logic:empty name="logradouro" property="logradouro.programaCalibragem">
																
																	<option  value="<%=""+ calibragem.getId() %>"><%=""
																	+ calibragem.getGrauImportancia()%> </option>
																
																</logic:empty>
															
															
															</logic:iterate>

														</select>
													
													</td>
												</logic:present>
												
											</logic:notEqual>
										</tr>
									</pg:item>
								</logic:iterate>
							</logic:present>
					</table>
					<table width="100%" border="0">
						<tr>
							<td valign="top">
								<logic:present name="indicadorImportanciaLogradouro" >
									<input name="button" class="bottonRightCol" type="button" value="Atualizar" onclick="atualizar(idRegistrosRemocao);">
								</logic:present>
							<logic:notPresent name="indicadorImportanciaLogradouro" >
								<gsan:controleAcessoBotao name="Button" value="Remover"
								  onclick="remover(idRegistrosRemocao);" url="removerLogradouroAction.do"/>							
							</logic:notPresent>
					<!--
							<input type="button" class="bottonRightCol"
								value="Remover" name="removerLogradouro" onclick="remover(idRegistrosRemocao);"/>--> 
							<logic:notPresent name="indicadorImportanciaLogradouro">	
								<input name="button"
								type="button" class="bottonRightCol" value="Voltar Filtro"
								onclick="window.location.href='<html:rewrite page="/exibirFiltrarLogradouroAction.do"/>'"
								align="left" style="width: 80px;">
							</logic:notPresent>
							<logic:present name="indicadorImportanciaLogradouro" >
								<input name="button"
								type="button" class="bottonRightCol" value="Voltar Filtro"
								onclick="window.location.href='<html:rewrite page="/exibirFiltrarLogradouroAction.do?menu=sim&implog=sim"/>'"
								align="left" style="width: 80px;">
							</logic:present>
							</td>
							<td align="right" valign="top"><a
								href="javascript:toggleBox('demodiv',1);"> <img align="right"
								border="0" src="<bean:message key='caminho.imagens'/>print.gif"
								title="Imprimir Logradouros" /> </a></td>
						</tr>
					</table>
					</td>
				</tr>
			</table>
			<table width="100%" border="0">
				<tr>
					<td>
					<div align="center"><strong><%@ include
						file="/jsp/util/indice_pager_novo.jsp"%></strong></div>
					</td>
					</pg:pager>
					<%-- Fim do esquema de paginação --%>
				</tr>
			</table>
			<p>&nbsp;</p>
			</td>
		</tr>
	</table>
	<jsp:include
		page="/jsp/relatorio/escolher_tipo_relatorio.jsp?relatorio=gerarRelatorioLogradouroManterAction.do" />
	<%@ include file="/jsp/util/rodape.jsp"%>
</body>
</html:form>
</html:html>
