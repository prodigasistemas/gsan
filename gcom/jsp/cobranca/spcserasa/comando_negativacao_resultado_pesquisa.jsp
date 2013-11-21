<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan" %>

<%@page import="gcom.cobranca.bean.ComandoNegativacaoHelper"%>
<%@ page import="gcom.util.ConstantesSistema" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">


<html:html>

<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>/validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script>

function chamarPaginaAnterior(){
		var form = ManutencaoRegistroActionForm;
		
		form.action = 'exibirPesquisarComandoNegativacaoAction.do?APAGAR=TRUE';
   		form.submit();
}

</script>
</head>

<body leftmargin="5" topmargin="5">

<html:form action="/exibirResultadoPesquisaComandoNegativacaoAction" 
		   name="ManutencaoRegistroActionForm"
		   type="gcom.gui.ManutencaoRegistroActionForm" method="post" >
<logic:present name="popup">
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


			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
</logic:present>
<logic:notPresent name="popup">
<table width="600" border="0" cellspacing="5" cellpadding="0">
	<tr>
		<td width="600" valign="top" class="centercoltext">
			<table height="100%">
				<tr>
					<td></td>
				</tr>
			</table>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
</logic:notPresent>

					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_left.gif" /></td>
					<td class="parabg">Resultado da Pesquisa de Comandos de Negativação Por Critério</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
				<tr>
					<td height="5" colspan="3"></td>
				</tr>
			</table>
			    
	  		<table  width="100%" border="0" align="center" cellpadding="0"
					cellspacing="0">
				<tr>
					<td>
						<table width="100%" bgcolor="#99CCFF">
							<!--header da tabela interna -->
							<tr bgcolor="#99CCFF">
								<td width="20%" align="center">
									<strong>Título</strong>
								</td>
								<td width="10%" align="center">
									<strong>Simulação</strong>
								</td>
								<td width="20%" align="center">
									<strong>Geração do Comando</strong>
								</td>
								<td width="20%" align="center">
									<strong>Execução do Comando</strong>
								</td>
								<td width="10%" align="center">
									<strong>Usuário Responsável</strong>
								</td>
								<td width="10%" align="center">
									<strong>Qtde. Inclusões</strong>
								</td>
								<td width="10%" align="center">
									<strong>Parâmentros do Comando</strong>
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
							maxPageItems="10" items="${sessionScope.totalRegistrosPrimeiraPaginacao}">
							<pg:param name="pg"/>
							<pg:param name="q"/>
							<logic:present name="collectionComandoNegativacao">
								<%int cont = 0;%>
								<logic:iterate name="collectionComandoNegativacao" id="comandoNegativacao" type="ComandoNegativacaoHelper">
									<pg:item>
										<%cont = cont + 1;
										if (cont % 2 == 0) {%>
										<tr bgcolor="#cbe5fe">
										<%} else {%>
										<tr bgcolor="#FFFFFF">
										<%}%>
											<logic:present name="popup" >
												<td width="18%">
													<div align="center">
															<bean:write name="comandoNegativacao" property="tituloComando"/> 
													</div>
												</td>
											</logic:present>
											<logic:notPresent name="popup" >
												<td width="18%">
													<div align="center">
														<a href="javascript:enviarDados('<bean:write name="comandoNegativacao" property="idComandoNegativacao"/>', '<bean:write name="comandoNegativacao" property="tituloComando"/>', 'comandoNegativacao');">
															<bean:write name="comandoNegativacao" property="tituloComando"/> 
														</a>
													</div>
												</td>
											</logic:notPresent>
											
											<td width="10%" align="center">
												<bean:write name="comandoNegativacao" property="indicadorComandoSimuladoString"/>
											</td>
											<td width="18%" align="center">
												<bean:write name="comandoNegativacao" property="geracaoComandoInicio" formatKey="date.format"/>&nbsp;
												<bean:write name="comandoNegativacao" property="geracaoComandoInicio" formatKey="hour.format"/>
											</td>
											<td width="18%" align="center">
												<bean:write name="comandoNegativacao" property="execucaoComandoInicio" formatKey="date.format"/>&nbsp;
												<bean:write name="comandoNegativacao" property="execucaoComandoInicio" formatKey="hour.format"/>
											</td>
											<td width="14%" align="center">
												<bean:write name="comandoNegativacao" property="nomeUsuarioResponsavel"/>
											</td>
											
											<logic:equal name="comandoNegativacao" property="indicadorQtdInclusoesString" value="<%=ConstantesSistema.NAO.toString()%>">
												<td width="9%" align="center">

													<a href="javascript:abrirPopup('/gsan/exibirInclusaoDadosComandoNegativacaoPopupAction.do?idComandoNegativacao=<%=""+ 
														comandoNegativacao.getIdComandoNegativacao()%>',530 , 700)">
														<bean:write name="comandoNegativacao" property="quantidadeInclusoes"/>
													</a>
												</td>	
											</logic:equal>
											
											<logic:notEqual name="comandoNegativacao" property="indicadorQtdInclusoesString" value="<%=ConstantesSistema.NAO.toString()%>">
												<td width="9%" align="center">
													<bean:write name="comandoNegativacao" property="quantidadeInclusoes"/>
												</td>	
											</logic:notEqual>
											
											<td width="20%" align="center">

												<a href="javascript:abrirPopup('/gsan/exibirConsultarParametrosComandoNegativacaoPopupAction.do?idComandoNegativacao=<%=""+ 
														comandoNegativacao.getIdComandoNegativacao()%>',500, 720)">Consultar</a>

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
									<input name="button" type="button" class="bottonRightCol" tabindex="1" value="Voltar Filtro"
										onclick="chamarPaginaAnterior();">
										
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
</body>
</html:form>
</html:html>