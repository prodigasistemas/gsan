<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan" %>
<%@ page
	import="gcom.util.Pagina,gcom.util.ConstantesSistema,java.util.Collection"%>
<%@ page import="gcom.atendimentopublico.ordemservico.ServicoTipo"%>	
<%@ page import="gcom.atendimentopublico.ordemservico.OSProgramacaoCalibragem"%>	


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

function validarForm(form){
	form.submit();
}

function atualizar(objeto){
	if (CheckboxNaoVazioAtt(objeto)){
		if (confirm ("Confirma atualização?")) {
			document.forms[0].action = "/gsan/atualizarTipoServicoGrauImportanciaAction.do"
			document.forms[0].submit();
		 }
	}
}
-->
</script>

</head>

<body leftmargin="5" topmargin="5">

<html:form action="/removerTipoServicoAction"
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
						<logic:notPresent name="indicadorGrauImportancia" >
							Manter Tipo de Servi&ccedil;o
						</logic:notPresent>
						<logic:present name="indicadorGrauImportancia" >
							Manter Import&acirc;ncia Tipo de Servi&ccedil;o
						</logic:present>
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
					<td colspan="6" height="23"><font color="#000000"
						style="font-size:10px"
						face="Verdana, Arial, Helvetica, sans-serif"><strong>Tipos de Servi&ccedil;o
					Encontrados:</strong></font></td>
					<td align="right"></td>
				</tr>
				</table>
			
	  			<table width="100%" cellpadding="0" cellspacing="0">
				<tr>
					<!--<td colspan="4" bgcolor="#3399FF"> -->
					<td colspan="8" bgcolor="#000000" height="2"></td>
				</tr>

				<tr bordercolor="#000000">

					<td width="7%" bgcolor="#90c7fc">
					<div align="center"><strong><a href="javascript:facilitador(this);">Todos</a></strong></div>
					</td>
					<td width="10%" align="center" bgcolor="#90c7fc"><strong>C&oacute;digo</strong></td>
					<td width="34%" align="center" bgcolor="#90c7fc"><strong>Descri&ccedil;&atilde;o</strong></td>
					<td width="10%" align="center" bgcolor="#90c7fc"><strong>C&oacute;d.Servi&ccedil;o Tipo</strong></td>
					<td width="8%" align="center" bgcolor="#90c7fc"><strong>Tempo M&eacute;dio</strong></td>
					<td width="22%" align="center" bgcolor="#90c7fc"><strong>Ind.Atualiza&ccedil;&atilde;o Comercial</strong></td>
				<logic:present name="indicadorGrauImportancia" >
					<td width="12%" align="center" bgcolor="#90c7fc"><strong>Grau de Import&acirc;ncia</strong></td>
				</logic:present>
				</tr>
				<tr>
					<td colspan="8">
					<table width="100%" bgcolor="#99CCFF">


						<%--Esquema de paginação--%>
						<pg:pager isOffset="true" index="half-full" maxIndexPages="10"
							export="currentPageNumber=pageNumber;pageOffset"
							maxPageItems="10" items="${sessionScope.totalRegistros}">
							<pg:param name="q" />
							<logic:present name="colecaoServicoTipo">
								<%int cont = 0;%>
								<logic:iterate name="colecaoServicoTipo" id="servicoTipo" type="ServicoTipo">
									<pg:item>
										<%cont = cont + 1;
									if (cont % 2 == 0) {%>
										<tr bgcolor="#cbe5fe">
											<%} else {

									%>
										<tr bgcolor="#FFFFFF">
											<%}%>
											<logic:equal name="servicoTipo" property="indicadorUso"
												value="<%=ConstantesSistema.INDICADOR_USO_DESATIVO.toString()%>">
												<td width="7%">
												
												<div align="center"><input type="checkbox"
													name="idRegistrosRemocao"
													value="<bean:write name="servicoTipo" property="id"/>" /></div>
												</td>
												
												<td width="10%" align="center">

												<div align="center"><bean:write name="servicoTipo"	property="id" /></div></td>
												
												<td width="34%"><a href="exibirAtualizarTipoServicoAction.do?pesquisa=S&manter=sim&idRegistroAtualizacao=
													<bean:write	name="servicoTipo" property="id" />">
													
												<bean:write name="servicoTipo" property="descricao" /></a></td>

												<td width="10%">
												<logic:equal name="servicoTipo" property="codigoServicoTipo" value="C">
													 Comercial 
												 </logic:equal>
					 							 <logic:equal name="servicoTipo" property="codigoServicoTipo" value="O">
													 Operacional
												 </logic:equal>
												 </td>

												<td width="8%">
													<div align="center"><bean:write name="servicoTipo" property="tempoMedioExecucao" /></div>
												</td>
												
												<td width="22%">
												  <logic:equal name="servicoTipo" property="indicadorAtualizaComercial" value="1">
												     Sim, no momento da execu&ccedil;&atilde;o
												  </logic:equal>
												  <logic:equal name="servicoTipo" property="indicadorAtualizaComercial" value="2">
												     Não Atualiza
												  </logic:equal>
												  <logic:equal name="servicoTipo" property="indicadorAtualizaComercial" value="3">
												     Sim, no momento posterior
												  </logic:equal>												  												  
													<!--<div align="center"><bean:write name="servicoTipo" property="indicadorAtualizaComercial" /></div>-->												 
												</td>
										</tr>
										</logic:equal>
										<logic:notEqual name="servicoTipo" property="indicadorUso"
											value="<%=ConstantesSistema.INDICADOR_USO_DESATIVO.toString()%>">
											<td width="7%">
											
											<div align="center"><input type="checkbox"
												name="idRegistrosRemocao"
												value="<bean:write name="servicoTipo" property="id"/>" /></div>
												
											</td>
											
											<td width="10%">
											
											<div align="center"><bean:write name="servicoTipo"
												property="id" /></div>
											</td>
											
											<td width="34%">
												<logic:notPresent name="indicadorGrauImportancia">
													<a href="exibirAtualizarTipoServicoAction.do?pesquisa=S&manter=sim&idRegistroAtualizacao=<bean:write
														name="servicoTipo" property="id" />">
													<bean:write name="servicoTipo" property="descricao" /></a>
												</logic:notPresent>
												<logic:present name="indicadorGrauImportancia">
													<bean:write name="servicoTipo" property="descricao" />
												</logic:present>
											</td>
											<td width="10%">
												<logic:equal name="servicoTipo" property="codigoServicoTipo" value="C">
													 Comercial 
												 </logic:equal>
					 							 <logic:equal name="servicoTipo" property="codigoServicoTipo" value="O">
													 Operacional
												 </logic:equal>
											</td>
											<td width="8%">
											<div align="center"><bean:write name="servicoTipo"
												property="tempoMedioExecucao" /></div>
											</td>
											
											<td width="22%">
												  <logic:equal name="servicoTipo" property="indicadorAtualizaComercial" value="1">
												     Sim, no momento da execu&ccedil;&atilde;o
												  </logic:equal>
												  <logic:equal name="servicoTipo" property="indicadorAtualizaComercial" value="2">
												     Não Atualiza
												  </logic:equal>
												  <logic:equal name="servicoTipo" property="indicadorAtualizaComercial" value="3">
												     Sim, no momento posterior
												  </logic:equal>	
													<!--<div align="center"><bean:write name="servicoTipo" property="indicadorAtualizaComercial" /></div>-->
											</td>
											<logic:present name="indicadorGrauImportancia" >											
												<td width="12%" align="center">											
													<select name="grauImportancia_${servicoTipo.id}">
													  <option  value=""> </option>	
														<logic:present name="osProgramacaoCalibragem" >													
															<logic:iterate name="osProgramacaoCalibragem" id="calibragem" type="OSProgramacaoCalibragem">
																<logic:notEmpty name="servicoTipo" property="programaCalibragem">
																<%
																	if (servicoTipo.getProgramaCalibragem().getId().equals(calibragem.getId())){ %>
																		<option  value="<%=""+ calibragem.getId() %>" selected="selected"><%=""
																		+ calibragem.getGrauImportancia()%> </option>													
																		
																<% 	}
																	else { %>
																
																		<option value="<%=""+ calibragem.getId() %>"><%=""
																		+ calibragem.getGrauImportancia()%> </option>												
																
																<% 	} %>
																
																</logic:notEmpty>
																<logic:empty name="servicoTipo" property="programaCalibragem">
																	
																		<option  value="<%=""+ calibragem.getId() %>"><%=""
																		+ calibragem.getGrauImportancia()%> </option>
																	
																</logic:empty>
															</logic:iterate>
														</logic:present>
													</select>
												</td>
											</logic:present>
										</logic:notEqual>
									</pg:item>
								</logic:iterate>
							</logic:present>
					</table>
					<table width="100%" border="0">
						<tr>
							<td valign="top">
						<logic:notPresent name="indicadorGrauImportancia" >
							<gsan:controleAcessoBotao name="Button" value="Remover"
							  onclick="javascript:validarForm(document.forms[0]);" url="removerTipoServicoAction.do"/>
						</logic:notPresent>
						<logic:present name="indicadorGrauImportancia" >
							<input name="button" class="bottonRightCol" type="button" value="Atualizar" onclick="atualizar(idRegistrosRemocao);">
						</logic:present>
					<!--
							<html:submit styleClass="bottonRightCol"
								value="Remover" property="Button" /> --> 
							<logic:present name="indicadorGrauImportancia" >
								<input name="button"
								type="button" class="bottonRightCol" value="Voltar Filtro"
								onclick="window.location.href='/gsan/exibirFiltrarTipoServicoAction.do?menu=sim&imp=sim'"
								align="left" style="width: 80px;">
							</logic:present>
							<logic:notPresent name="indicadorGrauImportancia" >
								<input name="button"
								type="button" class="bottonRightCol" value="Voltar Filtro"
								onclick="window.location.href='/gsan/exibirFiltrarTipoServicoAction.do'"
								align="left" style="width: 80px;">
							</logic:notPresent>
							</td>
							<td valign="top">
							<div align="right"><a href="javascript:toggleBox('demodiv',1);">
							<img border="0"
								src="<bean:message key="caminho.imagens"/>print.gif"
								title="Imprimir Tipos de Serviço" /> </a></div>
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
					</td>
				</tr>

			</table>
	</table>
	<jsp:include
		page="/jsp/relatorio/escolher_tipo_relatorio.jsp?relatorio=gerarRelatorioServicoTipoManterAction.do" />
	<%@ include file="/jsp/util/rodape.jsp"%>
</body>
</html:form>
</html:html>
