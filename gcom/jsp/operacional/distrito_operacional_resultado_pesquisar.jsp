<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>

<%@ page import="gcom.util.ConstantesSistema"%>
<html:html>
<head>
<title>GSAN - Sistema Integrado de Gest&atilde;o de Servi&ccedil;os de Saneamento</title>

<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="PesquisarActionForm" dynamicJavascript="false" />
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
				<td class="parabg">Pesquisar Distrito Operacional</td>
				<td width="11"><img border="0"
					src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
			</tr>
		</table>

		<p>&nbsp;</p>

		<table width="100%" border="0" bgcolor="#90c7fc">
			<tr align="left">
				<td width="15%" align="center"><strong>C&oacute;digo</strong></td>
				<td width="60%" align="center"><strong>Descri&ccedil;&atilde;o</strong></td>
				<td width="30%" align="center"><strong>Setor Abastecimento</strong></td>
				<td width="30%" align="center"><strong>Zona Abastecimento</strong></td>
			</tr>

			<%--Esquema de paginação--%>
			<pg:pager isOffset="true" index="half-full" maxIndexPages="10"
				export="currentPageNumber=pageNumber;pageOffset" maxPageItems="10"
				items="${sessionScope.totalRegistros}">
				<pg:param name="q" />
				<%int cont = 0;%>
				<logic:present name="colecaoDistrito" scope="session">
				<logic:iterate name="colecaoDistrito" id="distrito">
					<pg:item>
					<%	cont = cont + 1;
						if (cont % 2 == 0) {%>
						<tr bgcolor="#cbe5fe">
					<%	} else {	%>
						<tr bgcolor="#FFFFFF">
					<%	}	%>
					
							<td align="center">
							
								<bean:write name="distrito" property="id"/>
								
							</td>
							<td align="center">
								
								<logic:notEmpty name="caminhoRetornoTelaPesquisaQuadraFace">
									
									<a href="javascript:enviarDadosParametros('<bean:write name="caminhoRetornoTelaPesquisaQuadraFace"/>','<bean:write name="distrito" property="id"/>', '<bean:write name="distrito" property="descricao"/>', 'distrito');">
									<bean:write name="distrito" property="descricao"/></a>
														
								</logic:notEmpty>
			
								<logic:empty name="caminhoRetornoTelaPesquisaQuadraFace">
									
									<a href="javascript:enviarDados('<bean:write name="distrito" property="id"/>', '<bean:write name="distrito" property="descricao"/>', 'distrito');">
									<bean:write name="distrito" property="descricao"/>
								
								</logic:empty>
								
							
							</td>
							<td align="center">
							   <logic:notEmpty name="distrito" property="setorAbastecimento">
							    <bean:write name="distrito" property="setorAbastecimento.descricao"/>
							   </logic:notEmpty>
							   <logic:empty name="distrito" property="setorAbastecimento">
							     &nbsp;
							   </logic:empty>
							
							</td>
							
							<td align="center">
							<logic:notEmpty name="distrito" property="zonaAbastecimento">
							    <bean:write name="distrito" property="zonaAbastecimento.descricao"/>
							</logic:notEmpty>
							<logic:empty name="distrito" property="zonaAbastecimento">
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
					onclick="window.location.href='<html:rewrite page="/exibirPesquisarDistritoOperacionalAction.do?novaPesquisa=OK&objetoConsulta=1"/>'" /></td>
			</tr>
		</table>
		
		</td>
	</tr>
</table>

</body>
</html:html>
