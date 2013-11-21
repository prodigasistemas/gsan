<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>

<%@page import="gcom.seguranca.ConsultaCdl"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<head>
<html:html>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript">


</script>

</head>

<body leftmargin="5" topmargin="5"
	onload="javascript:setarFoco('${requestScope.nomeCampo}');">
<html:form action="/removerConsultaCadastroCdlAction"
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

			<td valign="top" class="centercoltext">

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
					<td class="parabg">Pesquisa de Consultas realizadas ao cadastro da Receita Federal</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>

				</tr>
			</table>

			<table width="100%" border="0">
				<tr>
					<td><strong> Período de realização: <bean:write name="periodoAcessoInicial"
						formatKey="date.format" scope="request" /> à <bean:write
						name="periodoAcessoFinal" formatKey="date.format" scope="request" /> </strong>
					</td>

				</tr>
			</table>
			

			<p>&nbsp;</p>

			<table width="100%" align="center" cellpadding="0" cellspacing="0">

				<tr>
					<td colspan="5">
					<table width="100%" align="center" bgcolor="#90c7fc">
						<tr bordercolor="#000000">
							
							<td width="20%" bgcolor="#90c7fc">
							<div align="left"><strong>Nome do usuário</strong></div>
							</td>
							<td width="20%" bgcolor="#90c7fc">
							<div align="center"><strong>CPF Cliente</strong></div>
							</td>
							<td width="20%" bgcolor="#90c7fc">
							<div align="center"><strong>CNPJ Cliente</strong></div>
							</td>
							<td width="40%" bgcolor="#90c7fc">
							<div align="center"><strong>Nome do Cliente</strong></div>
							</td>

							<td width="10%" bgcolor="#90c7fc">
							<div align="left">
							<p><strong>Ação do Operador</strong></p>
							</div>
							
						</tr>
						<%int cont = 0;%>
						<pg:pager isOffset="true" index="half-full" maxIndexPages="10"
							export="currentPageNumber=pageNumber;pageOffset"
							maxPageItems="10" items="${sessionScope.totalRegistros}">
							<pg:param name="pg" />
							<pg:param name="q" />


							<logic:iterate name="colecaoConsultaCdl"
								id="consultaCdl" type="ConsultaCdl">
								<pg:item>

									<%cont = cont + 1;
								if (cont % 2 == 0) {%>
									<tr bgcolor="#cbe5fe">
										<%} else {

								%>
									<tr bgcolor="#FFFFFF">
										<%}%>
										
										<td>
										<div align="left"><html:link
											page="/exibirConsultaCadastroCdlInformacoesArmazenadasAction.do"
											paramName="consultaCdl" paramProperty="id"
											paramId="idRegistroAtualizacao"
											title="Usuário Login">
											
											<bean:write name="consultaCdl"
												property="loginUsuario"/>
										</html:link>
										
										</div>
										</td>
										
										
										<td>
										<div align="center"><bean:write name="consultaCdl"
											property="cpfCliente" /></div>
										</td>
										
										<td>
										<div align="center"><bean:write name="consultaCdl"
											property="cnpjCliente" /></div>
										</td>
										
										<td>
										<div align="center"><bean:write name="consultaCdl"
											property="nomeCliente" /></div>
										</td>
										
										<td>
											<% if(consultaCdl.getCodigoAcaoOperador().intValue() == 1){ %>
												<div align="center">Aceito</div>
											<% }else if(consultaCdl.getCodigoAcaoOperador().intValue() == 2){%>
												<div align="center">Rejeitado</div>
											<% }else{%>
												<div align="center">Dados Conferem</div>
											<%}%>
											
										</td>
										
									</tr>

								</pg:item>

							</logic:iterate>
					</table>
					</td>
				</tr>
			</table>

			<table width="100%" border="0">

				<tr>
				
					<td colspan="3" class="style1">
					<!--
					<input type="button"
						class="bottonRightCol" value="Remover"
						onclick="javascript:remover1(document.forms[0].idRegistrosRemocao);">
					-->
					<input name="voltar" type="button" class="bottonRightCol"
						value="Voltar Pesquisa"
						onclick="window.location.href='<html:rewrite page="/exibirFiltrarConsultaCadastroCdlAction.do"/>'">
				   </td>
				   <td valign="top">
									<div align="right"><a href="javascript:toggleBox('demodiv',1);">
									<img border="0"
										src="<bean:message key="caminho.imagens"/>print.gif"
										title="Imprimir Consulta(s)" /> </a></div>
					</td>

					<td width="116" class="style1">
					<div align="right"><%--<input name="iniciar" type="button"
						class="bottonRightCol" value="Iniciar">--%>&nbsp;</div>
					</td>
				</tr>
				<tr>
					<td width="330">&nbsp;</td>
					<td width="157" align="right">&nbsp;</td>
					<td colspan="2" align="right">&nbsp;</td>
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
		</pg:pager>
	</table>


	<jsp:include
		page="/jsp/relatorio/escolher_tipo_relatorio.jsp?relatorio=gerarRelatorioResultadoPesquisaConsultaCadastroCdlAction.do" />
	<%@ include file="/jsp/util/rodape.jsp"%>
	
</html:form>
</body>
</html:html>
