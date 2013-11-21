<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>

<html:html>
<head>
<title>GSAN - Sistema Integrado de Gest&atilde;o de Servi&ccedil;os de Saneamento</title>

<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="PesquisarTabelaActionForm" dynamicJavascript="false" />
<script language="JavaScript">
</script>

</head>

<body leftmargin="5" topmargin="5" onload="resizePageSemLink(600, 330);">

<table width="550" border="0" cellspacing="5" cellpadding="0">
	<tr>
		<td width="580" valign="top" class="centercoltext">

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
				<td class="parabg">Pesquisar Tabela</td>
				<td width="11"><img border="0"
					src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
			</tr>
		</table>

		<p>&nbsp;</p>

		<table width="100%" border="0" bgcolor="#90c7fc">
			<tr align="left">
				<td width="15%" align="center"><strong>C&oacute;digo</strong></td>
				<td width="60%" align="center"><strong>Descri&ccedil;&atilde;o</strong></td>
				<td width="30%" align="center"><strong>Nome da Tabela</strong></td>

			</tr>

			<%--Esquema de paginação--%>
			<pg:pager isOffset="true" index="half-full" maxIndexPages="10"
				export="currentPageNumber=pageNumber;pageOffset" maxPageItems="10"
				items="${sessionScope.totalRegistros}">
				<pg:param name="q" />
				<%int cont = 0;%>
				<logic:present name="tabelas" scope="session">
				<logic:iterate name="tabelas" id="tabela">
					<pg:item>
					<%	cont = cont + 1;
						if (cont % 2 == 0) {%>
						<tr bgcolor="#cbe5fe">
					<%	} else {	%>
						<tr bgcolor="#FFFFFF">
					<%	}	%>
					
							<td align="center">
								<bean:write name="tabela" property="id"/>
							</td>
							<logic:notEmpty name="caminhoRetornoTelaPesquisaTabela">
									<td width="10%" align="center">
									<a href="javascript:enviarDadosParametros('<bean:write name="caminhoRetornoTelaPesquisaTabela"/>','<bean:write name="tabela" property="id"/>', '<bean:write name="tabela" property="descricao"/>', 'tabela');">
										<bean:write name="tabela" property="descricao" />
									</a>
									</td>
							</logic:notEmpty>
	
							<logic:empty name="caminhoRetornoTelaPesquisaTabela">
									<td width="10%" align="center">
									<a href="javascript:enviarDados('<bean:write name="tabela" property="id"/>', '<bean:write name="tabela" property="id"/>', 'tabela');">
										<bean:write name="tabela" property="descricao" />
									</a>
									</td>
							</logic:empty>
							
							<td align="center">
							   <logic:notEmpty name="tabela" property="nomeTabela">
							    <bean:write name="tabela" property="nomeTabela"/>
							   </logic:notEmpty>
							   <logic:empty name="tabela" property="nomeTabela">
							     &nbsp;
							   </logic:empty>
							
							</td>
							
						</tr>
					</pg:item>
				</logic:iterate>
				</logic:present>
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
		
		<table width="100%" border="0">
			<tr>
				<td height="24"><input type="button" class="bottonRightCol"
					value="Voltar Pesquisa"
					onclick="window.location.href='<html:rewrite page="/exibirPesquisarTabelaAction.do?novaPesquisa=OK&objetoConsulta=1"/>'" /></td>
			</tr>
		</table>
		
		</td>
	</tr>
</table>

</body>
</html:html>
