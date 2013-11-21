<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan" %>
<%@page import="gcom.cadastro.cliente.ClienteTipo"%>
<%@page import="gcom.util.ConstantesSistema"%>

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
<script>
<!--
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

function remover(objeto){

	if (CheckboxNaoVazio(objeto)){
		if (confirm ("Confirma remoção?")) {
			document.forms[0].action = "/gsan/removerClienteAction.do"
			document.forms[0].submit();
		 }
	}
}
-->
</script>
</head>

<body leftmargin="5" topmargin="5">

<html:form action="/removerClienteAction" 
	name="ManutencaoRegistroActionForm"
	type="gcom.gui.ManutencaoRegistroActionForm" method="post"
	onsubmit="return CheckboxNaoVazio(document.ManutencaoRegistroActionForm.idRegistrosRemocao) && confirm('Confirma remoção?')">

<%@ include file="/jsp/util/cabecalho.jsp"%>
<%@ include file="/jsp/util/menu.jsp"%>

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
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_left.gif" /></td>
					<td class="parabg">Manter Cliente</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
				<tr>
					<td height="5" colspan="3"></td>
				</tr>
			</table>
	
			<table width="100%" cellpadding="0" cellspacing="0">
				<tr>
					<td colspan="4" height="23">
						<font color="#000000" style="font-size:10px" face="Verdana, Arial, Helvetica, sans-serif">
						<strong>Clientes Encontrados:</strong>
						</font>
					</td>
					<logic:present scope="application" name="urlHelp">
						<td align="right"><a href="javascript:abrirPopupHelp('${applicationScope.urlHelp}cadastroClienteManter', 500, 700);"><span style="font-weight: bold"><font color="#3165CE">Ajuda</font></span></a></td>									
					</logic:present>
					<logic:notPresent scope="application" name="urlHelp">
						<td align="right"><span style="font-weight: bold"><font color=#696969><u>Ajuda</u></font></span></td>									
					</logic:notPresent>
				</tr>
	  		</table>
			
	  		<table width="100%" cellpadding="0" cellspacing="0">
				<tr>
					<td colspan="4" bgcolor="#000000" height="2"></td>
				</tr>
				<tr>
					<td>
						<table width="100%" bgcolor="#99CCFF">
							<!--header da tabela interna -->
							<tr bgcolor="#99CCFF">
								<td width="49" align="center">
									<strong>
									<a onclick="this.focus();" id="0" href="javascript:facilitador(this);">Todos</a>
									</strong>
								</td>
								<td width="62" align="center">
									<strong>C&oacute;digo</strong>
								</td>
								<td width="347" align="center">
									<strong>Nome</strong>
								</td>
								<td width="165" align="center">
									<strong>CPF/RG - CNPJ</strong>
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td>
						<table width="100%" bgcolor="#99CCFF">
						<%--Esquema de paginação--%>
						<pg:pager isOffset="true" index="half-full" maxIndexPages="10"
							export="currentPageNumber=pageNumber;pageOffset"				
							maxPageItems="10" items="${sessionScope.totalRegistros}">
							<pg:param name="pg"/>
							<pg:param name="q"/>
							<logic:present name="clientes">
								<%int cont = 0;%>
								<logic:iterate name="clientes" id="cliente">
									<pg:item>
										<%cont = cont + 1;
										if (cont % 2 == 0) {%>
										<tr bgcolor="#cbe5fe">
										<%} else {%>
										<tr bgcolor="#FFFFFF">
										<%}%>
											<td width="46" align="center">
												<input type="checkbox" name="idRegistrosRemocao" value="<bean:write name="cliente" property="id"/>"/>
											</td>
											<td width="60" align="center">
												<logic:notEqual name="cliente" property="indicadorUso"
													value="<%=ConstantesSistema.INDICADOR_USO_DESATIVO.toString()%>">
													<bean:write name="cliente" property="id" />
												</logic:notEqual> 
												<logic:equal name="cliente"	property="indicadorUso"
													value="<%=ConstantesSistema.INDICADOR_USO_DESATIVO.toString()%>">
													<font color="#CC0000"><bean:write name="cliente" property="id" /></font>
												</logic:equal>
											</td>
											<td width="335">
												<logic:notEqual name="cliente" property="indicadorUso"
													value="<%=ConstantesSistema.INDICADOR_USO_DESATIVO.toString()%>">
													<html:link page="/exibirAtualizarClienteAction.do" paramName="cliente" 
														paramProperty="id" paramId="idRegistroAtualizacao">
														<bean:write name="cliente" property="nome"/>
													</html:link>
												</logic:notEqual>
												<logic:equal name="cliente" property="indicadorUso"
													value="<%=ConstantesSistema.INDICADOR_USO_DESATIVO.toString()%>">
													<html:link page="/exibirAtualizarClienteAction.do"
														paramName="cliente" paramProperty="id" paramId="idRegistroAtualizacao">
														<font color="#CC0000"><bean:write name="cliente" property="nome" /></font>
													</html:link>
												</logic:equal>
											</td>
											<td width="162" align="right">
												<logic:notEqual name="cliente" property="indicadorUso"
													value="<%=ConstantesSistema.INDICADOR_USO_DESATIVO.toString()%>">
													<logic:equal name="cliente" property="clienteTipo.indicadorPessoaFisicaJuridica"
														value="<%=ClienteTipo.INDICADOR_PESSOA_FISICA.toString()%>">
														<logic:notEmpty name="cliente" property="cpfFormatado">
															<logic:notEqual name="cliente" property="cpfFormatado" value="null">
																<bean:write name="cliente" property="cpfFormatado" />
															 </logic:notEqual>
														</logic:notEmpty>
														<logic:notEmpty name="cliente" property="rg">
															RG: <bean:write name="cliente" property="rg"/>
															<logic:notEmpty name="cliente" property="orgaoExpedidorRg">
																<bean:write name="cliente" property="orgaoExpedidorRg.descricao"/>
															</logic:notEmpty>
															<logic:notEmpty name="cliente" property="unidadeFederacao">
																/ <bean:write name="cliente" property="unidadeFederacao.sigla"/>
															</logic:notEmpty>
														</logic:notEmpty>
													</logic:equal>
													<logic:equal name="cliente"	property="clienteTipo.indicadorPessoaFisicaJuridica"
														value="<%=ClienteTipo.INDICADOR_PESSOA_JURIDICA.toString()%>">
														<bean:write name="cliente" property="cnpjFormatado" />
													</logic:equal>
												</logic:notEqual> 
												<logic:equal name="cliente" property="indicadorUso"
													value="<%=ConstantesSistema.INDICADOR_USO_DESATIVO.toString()%>">
													<font color="#CC0000">
													<logic:equal name="cliente"	property="clienteTipo.indicadorPessoaFisicaJuridica"
														value="<%=ClienteTipo.INDICADOR_PESSOA_FISICA.toString()%>">
														<bean:write name="cliente" property="cpfFormatado" />
														RG: <bean:write name="cliente" property="rg" />
														<logic:notEmpty name="cliente" property="orgaoExpedidorRg">
															<bean:write name="cliente" property="orgaoExpedidorRg.descricao"/>
														</logic:notEmpty>
														<logic:notEmpty name="cliente" property="unidadeFederacao">
															/ <bean:write name="cliente" property="unidadeFederacao.sigla"/>
														</logic:notEmpty>
													</logic:equal>
													<logic:equal name="cliente" property="clienteTipo.indicadorPessoaFisicaJuridica"
														value="<%=ClienteTipo.INDICADOR_PESSOA_JURIDICA.toString()%>">
														<bean:write name="cliente" property="cnpjFormatado" />
													</logic:equal>
													</font>
												</logic:equal>
											</td>
										</tr>
									</pg:item>
								</logic:iterate>
							</logic:present>
						<%-- Fim do esquema de paginação --%>
						</table>
						<table width="100%">
							<tr>
								<td>
								<gsan:controleAcessoBotao name="Button" value="Remover"
							  onclick="remover(idRegistrosRemocao);" url="removerClienteAction.do"/><!--
									<input type="button" class="bottonRightCol"
										value="Remover" name="removerCliente" onclick="remover(idRegistrosRemocao);"/>-->
									<input name="button" type="button" class="bottonRightCol" value="Voltar Filtro"
										onclick="window.location.href='<html:rewrite page="/exibirFiltrarClienteAction.do?botao=VoltarFiltro"/>'">
								</td>
								<td align="right" valign="top">
                                	<a href="javascript:toggleBox('demodiv',1);">
                                    	<img align="right" border="0" src="<bean:message key='caminho.imagens'/>print.gif"  title="Imprimir Clientes"/></a>
                                </td>
							</tr>
						</table>
						<table width="100%" border="0">
							<tr>
								<td align="center">
									<strong><%@ include file="/jsp/util/indice_pager_novo.jsp"%></strong>
								</td>
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
<jsp:include page="/jsp/relatorio/escolher_tipo_relatorio.jsp?relatorio=gerarRelatorioClienteManterAction.do"/>
<%@ include file="/jsp/util/rodape.jsp"%>

</body>
</html:form>
</html:html>