<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan" %>
<%@ page import="gcom.util.ConstantesSistema"%>


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

-->
</script>


</head>

<body leftmargin="5" topmargin="5">

<html:form action="/filtrarSituacaoEspecialFaturamentoAction" method="post"
	name="ManutencaoRegistroActionForm"
	type="gcom.gui.ManutencaoRegistroActionForm"
>

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
			<td width="602" valign="top" class="centercoltext">
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

					<logic:notPresent name="acao" scope="session">
						<td class="parabg">Manter Situação Especial de Faturamento</td>
					</logic:notPresent>

					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>

			<pg:pager isOffset="true" index="half-full" maxIndexPages="10"
			export="currentPageNumber=pageNumber;pageOffset"
			maxPageItems="10" items="${sessionScope.totalRegistros}">
				<pg:param name="pg" />
				<pg:param name="q" />

			<table width="100%" cellpadding="0" cellspacing="0">
				<tr>
					<td colspan="7" height="23"><font style="font-size: 10px;"
						color="#000000" face="Verdana, Arial, Helvetica, sans-serif"> <strong>Situação(ões) especial(is) de faturamento cadastrada(s):</strong> </font></td>
				</tr>

				<tr>
					<td colspan="7" bgcolor="#000000" height="2"></td>
				</tr>



				<tr>
					<td>
						<table width="100%" bgcolor="#90c7fc">

							<tr>
								<td width="25%">
									<div align="center">
										<strong>Situação Especial de Faturamento</strong>
									</div>
								</td>
								<td width="8%" align="center" bgcolor="#90c7fc"><strong>Imóvel</strong></td>
								<td width="6%" align="center" bgcolor="#90c7fc"><strong>Loc.</strong></td>
								<td width="6%" align="center" bgcolor="#90c7fc"><strong>Setor</strong></td>																					
								<td width="7%" align="center" bgcolor="#90c7fc"><strong>Quadra</strong></td>																					
								<td width="7%" align="center" bgcolor="#90c7fc"><strong>Lote</strong></td>
								<td width="6%" align="center" bgcolor="#90c7fc"><strong>Sub-Lote</strong></td>
								<td width="6%" align="center" bgcolor="#90c7fc"><strong>Rota</strong></td>
								<td width="8%" align="center" bgcolor="#90c7fc"><strong>Seq. Rota</strong></td>
								<td width="7%" align="center" bgcolor="#90c7fc"><strong>Cat.</strong></td>
								<td width="7%" align="center" bgcolor="#90c7fc"><strong>Cons.</strong></td>
								<td width="7%" align="center" bgcolor="#90c7fc"><strong>Qtd. Imov.</strong></td>
							</tr>

							<%--Esquema de paginação--%>

							<logic:present name="colecaoFaturamentoSituacaoComando">
								<% String cor = "#cbe5fe";%>

								<logic:iterate name="colecaoFaturamentoSituacaoComando" id="faturamentoSituacaoComando">
									<pg:item>
										<%	if (cor.equalsIgnoreCase("#cbe5fe")){	
											cor = "#FFFFFF";%>
											<tr bgcolor="#FFFFFF" height="18">	
										<%} else{	
											cor = "#cbe5fe";%>
											<tr bgcolor="#cbe5fe" height="18">		
										<%}%>
                            				<td width="25%" align="center">
	                            				<html:link page="/exibirAtualizarSituacaoEspecialFaturamentoAction.do"
	                                           		paramName="faturamentoSituacaoComando"
	                                          		paramProperty="id"
	                                           		paramId="idRegistroAtualizacao">
												<logic:present name="faturamentoSituacaoComando" property="faturamentoSituacaoTipo">	
	                              					 <bean:write name="faturamentoSituacaoComando" property="faturamentoSituacaoTipo.descricao"/>
												</logic:present>
												<logic:notPresent name="faturamentoSituacaoComando" property="faturamentoSituacaoTipo">	
	                              					 N&Atilde;O INFORMADO
												</logic:notPresent>
	                              				</html:link>
                            			    </td>

                            				<td width="7%" align="center">
												<logic:present name="faturamentoSituacaoComando" property="imovel">
 	                                  				<bean:write name="faturamentoSituacaoComando" property="imovel.matriculaFormatada"/>
												</logic:present>
                            				</td>
                            				
                           					 <td width="6%" align="center">
												<logic:present name="faturamentoSituacaoComando" property="localidadeInicial">
	                                   				<bean:write name="faturamentoSituacaoComando" property="localidadeInicial.id"/>
												</logic:present>
												<br/>
												<logic:present name="faturamentoSituacaoComando" property="localidadeFinal">
	                                   				<bean:write name="faturamentoSituacaoComando" property="localidadeFinal.id"/>
												</logic:present>
											</td>
											<td width="7%" align="center">
                                   				<bean:write name="faturamentoSituacaoComando" property="codigoSetorComercialInicial"/> <br/>
                                   				<bean:write name="faturamentoSituacaoComando" property="codigoSetorComercialFinal"/>
											</td>
											<td width="7%" align="center">
                                   				<bean:write name="faturamentoSituacaoComando" property="numeroQuadraInicial"/> <br/>
                                   				<bean:write name="faturamentoSituacaoComando" property="numeroQuadraFinal"/>
											</td>
											<td width="7%" align="center">
                                   				<bean:write name="faturamentoSituacaoComando" property="numeroLoteInicial"/> <br/>
                                   				<bean:write name="faturamentoSituacaoComando" property="numeroLoteFinal"/>
											</td>

											<td width="6%" align="center">
                                   				<bean:write name="faturamentoSituacaoComando" property="numeroSubLoteInicial"/> <br/>
                                   				<bean:write name="faturamentoSituacaoComando" property="numeroSubLoteFinal"/>
											</td>

											<td width="6%" align="center">
                                   				<bean:write name="faturamentoSituacaoComando" property="codigoRotaInicial"/> <br/>
                                   				<bean:write name="faturamentoSituacaoComando" property="codigoRotaFinal"/>
											</td>

											<td width="8%" align="center">
                                   				<bean:write name="faturamentoSituacaoComando" property="sequencialRotaInicial"/> <br/>
                                   				<bean:write name="faturamentoSituacaoComando" property="sequencialRotaFinal"/>
											</td>

											<td width="7%" align="center">
												<logic:present name="faturamentoSituacaoComando" property="categoria1">
                                   					<bean:write name="faturamentoSituacaoComando" property="categoria1.id"/> &brvbar;
													<logic:present name="faturamentoSituacaoComando" property="categoria2">
		                                   				<bean:write name="faturamentoSituacaoComando" property="categoria2.id"/> <br/>
														<logic:present name="faturamentoSituacaoComando" property="categoria3">
			                                   				<bean:write name="faturamentoSituacaoComando" property="categoria3.id"/> &brvbar;
															<logic:present name="faturamentoSituacaoComando" property="categoria4">
				                                   				<bean:write name="faturamentoSituacaoComando" property="categoria4.id"/>
															</logic:present>
														</logic:present>
													</logic:present>
												</logic:present>
											</td>

											<td width="7%" align="center">
                                   				<bean:write name="faturamentoSituacaoComando" property="indicadorConsumo"/>
											</td>

											<td width="7%" align="center">
                                   				<bean:write name="faturamentoSituacaoComando" property="quantidadeImoveis"/>
											</td>

										</tr>
									</pg:item>
								</logic:iterate>
							</logic:present>
					</table>
					</td>
				</tr>
			</table>

			<table width="100%">
				<tr>
					<td>
						 <input name="button" type="button"
							class="bottonRightCol" value="Voltar Filtro"
							onclick="window.location.href='<html:rewrite page="/exibirFiltrarSituacaoEspecialFaturamentoAction.do"/>'"
							align="left" style="width: 80px;"/>
					</td>
				</tr>
			</table>

			<table width="100%" border="0">
				<tr>
					<td>
					<div align="center"><strong><%@ include
						file="/jsp/util/indice_pager_novo.jsp"%></strong></div>
					</td>
				</tr>
			</table>
			
</pg:pager>
</td>
		</tr>		
	</table>
<!--  	<jsp:include page="/jsp/relatorio/escolher_tipo_relatorio.jsp?relatorio=gerarRelatorioSituacaoFaturamentoEspecialManterAction.do"/> -->
	<%@ include file="/jsp/util/rodape.jsp"%>
</html:form>
</body>
</html:html>