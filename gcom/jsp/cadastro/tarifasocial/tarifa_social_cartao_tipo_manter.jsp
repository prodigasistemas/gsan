<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan"%>

<%@ page import="gcom.util.ConstantesSistema,gcom.cadastro.tarifasocial.TarifaSocialCartaoTipo"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>

<head>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">

<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>

<script>

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
				document.forms[0].action = "/gsan/removerTarifaSocialCartaoTipoAction.do";
				document.forms[0].submit();
			}
		}
	}


</script>

</head>

<body leftmargin="5" topmargin="5">
<html:form action="/removerTarifaSocialCartaoTipoAction"
	name="ManutencaoRegistroActionForm"
	type="gcom.gui.ManutencaoRegistroActionForm" method="post">

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
					<td>
					
					</td>
				</tr>
			</table>
			
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_left.gif" /></td>
					<td class="parabg">Manter Tipo de Cartão da Tarifa Social</td>
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
						<font color="#000000"
							style="font-size:10px"
							face="Verdana, Arial, Helvetica, sans-serif">
							<strong>Tipos de Cartão da Tarifa Social encontrados:</strong>
						</font>
					</td>
					<logic:present scope="application" name="urlHelp">
						<td align="right"><a href="javascript:abrirPopupHelp('${applicationScope.urlHelp}cadastroTarifaSocialCartaoManter', 500, 700);"><span style="font-weight: bold"><font color="#3165CE">Ajuda</font></span></a></td>									
					</logic:present>
					<logic:notPresent scope="application" name="urlHelp">
						<td align="right"><span style="font-weight: bold"><font color=#696969><u>Ajuda</u></font></span></td>									
					</logic:notPresent>	
				</tr>

				<tr>
					<td colspan="5" bgcolor="#000000" height="2"></td>
				</tr>

				<tr bordercolor="#000000">
					<td width="7%" bgcolor="#90c7fc">
					
					<div align="center">
						<strong><a href="javascript:facilitador(this);" id="0">Todos</a></strong></div>
					</td>
					
					<td width="40%" bgcolor="#90c7fc" align="center"><strong>Descrição</strong></td>
					<td width="15%" bgcolor="#90c7fc" align="center"><strong>Descrição Abreviada</strong></td>
					<td width="26%" bgcolor="#90c7fc" align="center"><strong>Ind. Exis. Validade</strong></td>
					<td width="26%" bgcolor="#90c7fc" align="center"><strong>No. Máximo de Meses para Adesão</strong></td>
				</tr>

				<tr>
					<td colspan="5">

					<table width="100%" bgcolor="#99CCFF">

						<%--Esquema de paginação--%>
						<pg:pager isOffset="true" 
							index="half-full" 
							maxIndexPages="10"
							export="currentPageNumber=pageNumber;pageOffset"
							maxPageItems="10" 
							items="${sessionScope.totalRegistros}">
							
							<pg:param name="q" />
							
							<logic:present name="colecaoTarifaSocialCartaoTipo">
								<%int cont = 0;%>
								<logic:iterate name="colecaoTarifaSocialCartaoTipo"
									id="tarifaSocialCartaoTipo">
									<pg:item>
										<%cont = cont + 1;
										if (cont % 2 == 0) {%>
												<tr bgcolor="#cbe5fe">
										<%} else {
												
										%>
												<tr bgcolor="#FFFFFF">
											<%}%>
											<logic:equal name="tarifaSocialCartaoTipo"
												property="indicadorUso"
												value="<%=ConstantesSistema.INDICADOR_USO_DESATIVO.toString()%>">
												<td width="7%">
												<div align="center"><input type="checkbox"
													name="idRegistrosRemocao"
													value="<bean:write name="tarifaSocialCartaoTipo" property="id"/>" />
												</div>
												</td>
												<td width="40%">
													<html:link page="/exibirAtualizarTarifaSocialCartaoTipoAction.do"
														paramName="tarifaSocialCartaoTipo" 
														paramProperty="id"
														paramId="idRegistroAtualizacao">
														<font color="#CC0000"> 
															<bean:write name="tarifaSocialCartaoTipo" property="descricao" />
														</font>
													</html:link> 
												</td>
												
												<td width="15%"><font color="#CC0000"> <bean:write
													name="tarifaSocialCartaoTipo" property="descricaoAbreviada" />
												</font></td>
												<td width="26%"><logic:equal name="tarifaSocialCartaoTipo"
													property="indicadorValidade"
													value="<%=TarifaSocialCartaoTipo.INDICADOR_EXISTENCIA_VALIDADE_SIM.toString()%>">
													<font color="#CC0000"> SIM </font>
												</logic:equal> <logic:notEqual name="tarifaSocialCartaoTipo"
													property="indicadorValidade"
													value="<%=TarifaSocialCartaoTipo.INDICADOR_EXISTENCIA_VALIDADE_SIM.toString()%>">
													<font color="#CC0000"> NÃO </font>
												</logic:notEqual></td>
												<td width="26%" align="center"><font color="#CC0000"> <bean:write
													name="tarifaSocialCartaoTipo" property="numeroMesesAdesao" />
												</font></td>
											</logic:equal>
											<logic:notEqual name="tarifaSocialCartaoTipo"
												property="indicadorUso"
												value="<%=ConstantesSistema.INDICADOR_USO_DESATIVO.toString()%>">
												<td width="7%">
												<div align="center"><input type="checkbox"
													name="idRegistrosRemocao"
													value="<bean:write name="tarifaSocialCartaoTipo"   property="id"/>" />
												</div>
												</td>
												<td width="40%"><html:link
													page="/exibirAtualizarTarifaSocialCartaoTipoAction.do"
													paramName="tarifaSocialCartaoTipo" paramProperty="id"
													paramId="idRegistroAtualizacao">
													<bean:write name="tarifaSocialCartaoTipo"
														property="descricao" />
												</html:link></td>
												<td width="15%"><bean:write name="tarifaSocialCartaoTipo"
													property="descricaoAbreviada" /></td>
												<td width="26%"><logic:equal name="tarifaSocialCartaoTipo"
													property="indicadorValidade"
													value="<%=TarifaSocialCartaoTipo.INDICADOR_EXISTENCIA_VALIDADE_SIM.toString()%>">
													<%--<font color="#CC0000">
                                                <bean:write name="tarifaSocialCartaoTipo" property="indicadorValidade"/>
                                            </font> --%>
                                            SIM
                                        </logic:equal> <logic:notEqual
													name="tarifaSocialCartaoTipo" property="indicadorValidade"
													value="<%=TarifaSocialCartaoTipo.INDICADOR_EXISTENCIA_VALIDADE_SIM.toString()%>">
													<%--<font color="#CC0000">
                                              <bean:write name="tarifaSocialCartaoTipo" property="indicadorValidade"/>
                                             </font> --%>
                                             NÃO
                                         </logic:notEqual></td>
												<td width="26%" align="center"><bean:write name="tarifaSocialCartaoTipo"
													property="numeroMesesAdesao" /></td>
											</logic:notEqual>
										</tr>
									</pg:item>
								</logic:iterate>
							</logic:present>
					</table>
					
						<table width="100%">
							<tr>
								<td>

									<gsan:controleAcessoBotao name="Button" 
										value="Remover"
								  		onclick="javascript:remover(document.ManutencaoRegistroActionForm.idRegistrosRemocao);" 
								  		url="removerTarifaSocialCartaoTipoAction.do"/>
								</td>
								
								<td align="right" valign="top">
									<a href="javascript:toggleBox('demodiv',1);"> 
										<img align="right"
											border="0" 
											src="<bean:message key='caminho.imagens'/>print.gif"
											title="Imprimir Tipo de Cartão da Tarifa Social" /></a>
                                </td>
								
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
		page="/jsp/relatorio/escolher_tipo_relatorio.jsp?relatorio=gerarRelatorioTarifaSocialManterAction.do" />
	<%@ include file="/jsp/util/rodape.jsp"%>
</body>
</html:form>
</html:html>
