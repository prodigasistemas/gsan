<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>

<%@ page import="java.util.Collection,gcom.util.ConstantesSistema"%>
<%@page import="gcom.arrecadacao.banco.ContaBancaria"%>
<html:html>
<head>
<title>GSAN - Sistema Integrado de Gest&atilde;o de Servi&ccedil;os de Saneamento</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js">
	

</script>

</head>

<logic:present name="enviarDados" scope="request">
	<body leftmargin="5" topmargin="5"
		onload="javascript:enviarDadosCincoParametros('<bean:write name="idContaBancaria" scope="session"/>', '<bean:write name="idBanco" scope="request"/>', '<bean:write name="codigoAgencia" scope="request"/>', '<bean:write name="numeroConta" scope="request" />' ,'contaBancaria');window.close()">
</logic:present>

<logic:notPresent name="enviarDados" scope="request">
	<body leftmargin="5" topmargin="5"
		onload="javascript:resizePageSemLink(810, 405)">
</logic:notPresent>

<table width="770" border="0" cellspacing="5" cellpadding="0">
	<tr>
		<td width="770" valign="top" class="centercoltext">
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
				<td class="parabg">Pesquisa de Conta Bancária</td>
				<td width="11"><img border="0"
					src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
			</tr>
		</table>
		<p>&nbsp;</p>
		<table width="100%" border="0" bgcolor="#90c7fc">
			<tr align="left">
				<td bgcolor="#90c7fc" colspan="2">
				<div align="center"><strong>Banco</strong></div>
				</td>
				<td bgcolor="#90c7fc" colspan="2">
				<div align="center"><strong>Ag&ecirc;ncia</strong><strong></strong></div>
				</td>
				<td bgcolor="#90c7fc" rowspan="2">
				<div align="center"><strong>N&uacute;mero da Conta</strong></div>
				<div align="center"></div>
				</td>
			</tr>
			<tr align="left">
				<td bgcolor="#cbe5fe" width="15%">
				<div align="center"><strong>C&oacute;digo</strong></div>
				</td>
				<td bgcolor="#cbe5fe" width="25%">
				<div align="center"><strong>Nome</strong></div>
				</td>
				<td bgcolor="#cbe5fe" width="15%">
				<div align="center"><strong>C&oacute;digo</strong></div>
				</td>
				<td bgcolor="#cbe5fe" width="25%">
				<div align="center"><strong>Nome</strong></div>
				</td>
			</tr>
			<pg:pager isOffset="true" index="half-full" maxIndexPages="10"
				export="currentPageNumber=pageNumber;pageOffset"
				maxPageItems="10" items="${sessionScope.totalRegistros}">
				<pg:param name="pg" />
				<pg:param name="q" />
										<%int cont = 0;%>
										<logic:iterate name="collectionContaBancaria"
											id="contaBancaria" type="ContaBancaria">
											<pg:item>
												<%cont = cont + 1;
			if (cont % 2 == 0) {%>
												<tr bgcolor="#cbe5fe">
													<%} else {

			%>
												<tr bgcolor="#FFFFFF">
													<%}%>				

						<td>
						<div align="center"><bean:write name="contaBancaria"
							property="agencia.banco.id" /></div>
						</td>
						<td>
						<div align="left"><bean:write name="contaBancaria"
							property="agencia.banco.descricaoAbreviada" /></div>
						</td>
						<td>
						<div align="center"><bean:write name="contaBancaria"
							property="agencia.codigoAgencia" /></div>
						</td>
						<td>
						<div align="left"><bean:write name="contaBancaria"
							property="agencia.nomeAgencia" /></div>
						</td>
						<td>
						<div align="center"><logic:present name="tipoPesquisa">
							<logic:equal name="tipoPesquisa" value="avisoBancario">
								<a
									href="verificarExistenciaContaAction.do?idContaBancaria=<bean:write name="contaBancaria" property="id" />&idArrecadador=${sessionScope.idArrecadador}&idBanco=<bean:write name="contaBancaria" property="agencia.banco.id"/>&codigoAgencia=<bean:write name="contaBancaria" property="agencia.codigoAgencia"/>&numeroConta=<bean:write name="contaBancaria" property="numeroConta" />">

								<bean:write name="contaBancaria" property="numeroConta" /> </a>
							</logic:equal>
						</logic:present> <logic:notPresent name="tipoPesquisa">
							<logic:notEmpty name="caminhoRetornoTelaPesquisaContaBancaria">
								<a
									href="javascript:enviarDadosCincoParametrosCaminhoRetorno('<bean:write name="caminhoRetornoTelaPesquisaContaBancaria"/>', '<bean:write name="contaBancaria" property="id"/>', '<bean:write name="contaBancaria" property="agencia.banco.id"/>', '<bean:write name="contaBancaria" property="agencia.codigoAgencia"/>', '<bean:write name="contaBancaria" property="numeroConta" />' ,'contaBancaria');">
								<bean:write name="contaBancaria" property="numeroConta" /></a>

							</logic:notEmpty>
							<logic:empty name="caminhoRetornoTelaPesquisaContaBancaria">
								<a
									href="javascript:enviarDadosCincoParametros('<bean:write name="contaBancaria" property="id"/>', '<bean:write name="contaBancaria" property="agencia.banco.id"/>', '<bean:write name="contaBancaria" property="agencia.codigoAgencia"/>', '<bean:write name="contaBancaria" property="numeroConta" />' ,'contaBancaria');">
								<bean:write name="contaBancaria" property="numeroConta" /> </a></div>
						</logic:empty> </logic:notPresent></td>
					</tr>
					</pg:item>
				</logic:iterate>
		</table>

		<table width="100%" border="0">
			<tr>
				<td>
				<div align="center"><strong><%@ include
				file="/jsp/util/indice_pager_novo.jsp"%></strong></div>
				</td>
			</tr>
			<tr>
				<td height="24">
					<input type="button" class="bottonRightCol"	value="Voltar Pesquisa"	onclick="window.location.href='<html:rewrite page="/contaBancariaPesquisarAction.do"/>'" />
				</td>
			</tr>
		</table>
		</pg:pager><%-- Fim do esquema de paginação --%>
	</td>
	</tr>
</table>
</html:html>
