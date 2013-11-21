<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan" %>

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

function atualizaIdRegistroConsulta(teste){
	form = document.forms[0];
	myString = new String(teste);
	splitString = myString.split(";");
	form.idRAConsulta.value  = splitString[0];
	form.idImovelSelecionado.value  = splitString[1];
}

function consultar(){

	form = document.forms[0];
	
	if(form.idRAConsulta != 'undefined'){
		document.forms[0].action = '<html:rewrite page="/exibirEfetuarDevolucaoValoresPagosDuplicidadeAction.do"/>';
		document.forms[0].submit();
	}
	
}

-->
</script>


<style>
teste {
	font-size: 5px;
}
</style>
</head>

<body leftmargin="5" topmargin="5">

<html:form action="/exibirManterRegistroAtendimentoDevolucaoValoresAction" method="post">
	<html:hidden property="idImovelSelecionado" />

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


						<td class="parabg">Devolução de Pagamentos em Duplicidade</td>

					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td colspan="9" height="23"><font style="font-size: 10px;"
						color="#000000" face="Verdana, Arial, Helvetica, sans-serif"> <strong>Selecione 1(um) Registro de Atendimento:</strong></font></td>
				</tr>
				<tr>
					<td colspan="9" bgcolor="#000000" height="2"></td>
				</tr>
			</table>

			<table width="100%" bgcolor="#90c7fc">

				<%--Esquema de paginação--%>
				<pg:pager isOffset="true" index="half-full" maxIndexPages="10" export="currentPageNumber=pageNumber;pageOffset" maxPageItems="10" items="${sessionScope.totalRegistros}">
					<pg:param name="pg" />
					<pg:param name="q" />
					<%int cont = 0;%>
					<tr bordercolor="#000000">
						  
						<td bgcolor="#90c7fc" width="10%">
						<div align="center"><strong>Marcar</strong></div>
						</td>
						<td bgcolor="#90c7fc" width="15%">
						<div align="center"><strong>RA</strong></div>
						</td>
						<td bgcolor="#90c7fc" width="15%">
						<div align="center"><strong>Matrícula</strong></div>
						</td>
						<td bgcolor="#90c7fc" width="60%">
						<div align="center"><strong>Cliente</strong></div>
						</td>
						
					</tr>


					<logic:present name="colecaoRegistroAtendimento">
						<logic:iterate name="colecaoRegistroAtendimento" id="registroAtendimento"
							indexId="i">
							<pg:item>
								<%cont = cont + 1;
	if (cont % 2 == 0) {%>
								<tr bgcolor="#cbe5fe">
									<%} else {

	%>
								<tr bgcolor="#FFFFFF">
									<%}%>

									<logic:notPresent name="acao" scope="session">
										<td align="center"><input type="radio" name="idRAConsulta" property="idRAConsulta"
											value="<bean:write name="registroAtendimento" property="numeroRA"/>" 
											onclick="atualizaIdRegistroConsulta(${registroAtendimento.numeroRA} + ';' + ${registroAtendimento.idImovel});" ></td>
									</logic:notPresent>

									<td>
										<div align="center">
										
										<a  title="Consultar Dados do Registro de Atendimento" 
		                        			href="javascript:abrirPopup('exibirConsultarRegistroAtendimentoPopupAction.do?numeroRA=${registroAtendimento.numeroRA}', 400, 600 );">
											
											<bean:write name="registroAtendimento" property="numeroRA" />&nbsp;
										</a>
										</div>
									</td>
									
									<td>
									<div align="center"><bean:write name="registroAtendimento" property="idImovel" />&nbsp;</div>
									</td>
									
									<td>
									<div align="left"><bean:write name="registroAtendimento" property="nomeClienteUsuario" />&nbsp;</div>
									</td>

								</tr>
							</pg:item>
						</logic:iterate>
					</logic:present>
			</table>


			<table width="100%">
				<tr>
					<td valign="top">
					
					<input class="bottonRightCol" value="Consultar" type="button"
								onclick="javascript:consultar();">
						
					<input name="Submit222"
								class="bottonRightCol" value="Voltar Filtro" type="button"
								onclick="window.location.href='/gsan/exibirFiltrarRegistroAtendimentoDevolucaoValoresAction.do';">
						
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

</body>
</html:form>
</html:html>
