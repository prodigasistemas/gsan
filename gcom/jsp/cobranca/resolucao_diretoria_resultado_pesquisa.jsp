<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>

<%@ page import="java.util.Collection,gcom.util.ConstantesSistema"%>
<html:html>
<head>
<title>GSAN - Sistema Integrado de Gest&atilde;o de Servi&ccedil;os de Saneamento</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>

</head>

<body leftmargin="5" topmargin="5" onload="resizePageSemLink(790, 410);">
<table width="750" border="0" cellspacing="5" cellpadding="0">
	<tr>
		<td width="750" valign="top" class="centercoltext">
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
				<td class="parabg">Pesquisa de Resolução de Diretoria</td>

				<td width="11"><img border="0"
					src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
			</tr>
			<tr>
				<td height="5" colspan="3"></td>
			</tr>
		</table>


		<table width="100%" cellpadding="0" cellspacing="0" border="0" >
			<tr>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td colspan="4" bgcolor="#000000" height="2"></td>
			</tr>

			<tr bgcolor="#90c7fc">
				<td rowspan="2" width="15%" align="center" ><strong>Número da RD</strong></td>
				<td rowspan="2" width="55%" align="center" ><strong>Assunto da RD</strong></td>
				<td colspan="2" align="center" ><strong>Período de Vigência da RD</strong></td>
			</tr>
			<tr  bgcolor="#90c7fc">
			<!-- bgcolor="#cbe5fe" -->
				<td width="15%" align="center" ><strong>Início</strong></td>
  				<td width="15%" align="center" ><strong>Término</strong></td>
			</tr>
			
			<tr>
				<td colspan="4">
				<table width="100%" bgcolor="#99CCFF">

					<%--Esquema de paginação--%>
					<pg:pager maxIndexPages="10" export="currentPageNumber=pageNumber"
						index="center" maxPageItems="10">
						<pg:param name="pg" />
						<pg:param name="q" />
						<logic:present name="collectionResolucaoDiretoria">
							<%int cont = 0;%>
							<logic:iterate name="collectionResolucaoDiretoria" id="resolucaoDiretoria">
								<pg:item>
									<%cont = cont + 1;
									if (cont % 2 == 0) {%>
									<tr bgcolor="#FFFFFF">
										<%} else {

									%>
									<tr bgcolor="#cbe5fe">
										<%}%>
										
										
										
							<td width="15%" align="center">
																											
								<logic:notEmpty
									name="caminhoRetornoTelaPesquisaResolucaoDiretoria">
									<a
										href="javascript:enviarDadosParametros('<bean:write name="caminhoRetornoTelaPesquisaResolucaoDiretoria"/>','<bean:write name="resolucaoDiretoria" property="numeroResolucaoDiretoria"/>', '<bean:write name="resolucaoDiretoria" property="descricaoAssunto"/>', 'resolucaoDiretoria');">
									${resolucaoDiretoria.numeroResolucaoDiretoria} &nbsp;</a>
								</logic:notEmpty> 
								
								<logic:empty
									name="caminhoRetornoTelaPesquisaResolucaoDiretoria">
									<a
										href="javascript:enviarDados('<bean:write name="resolucaoDiretoria" property="numeroResolucaoDiretoria"/>', '<bean:write name="resolucaoDiretoria" property="descricaoAssunto"/>', 'resolucaoDiretoria');">
									${resolucaoDiretoria.numeroResolucaoDiretoria} &nbsp;</a>
								</logic:empty>
										
							</td>
							
							<td width="55%">${resolucaoDiretoria.descricaoAssunto} &nbsp;</td>
								
								
							<td width="15%" align="center">
								<div align="center"><bean:write name="resolucaoDiretoria"
										format="dd/MM/yyyy" property="dataVigenciaInicio" /></div>
							</td>
							
							<td width="15%" align="center">
								<div align="center"><bean:write name="resolucaoDiretoria"
										format="dd/MM/yyyy" property="dataVigenciaFim" /></div>
							</td>		
							
							</tr>
								</pg:item>
							</logic:iterate>
						</logic:present>
				</table>

				</td>
			</tr>

		</table>
		<%if (((Collection) session.getAttribute("collectionResolucaoDiretoria"))
					.size() == ConstantesSistema.NUMERO_MAXIMO_REGISTROS_PESQUISA) {%>
		<%@ include file="/jsp/util/limite_filtro.jsp"%> <%}%>

		<table width="100%" border="0">
			<tr>
				<td><strong><%@ include file="/jsp/util/indice_pager.jsp"%></strong>
				</td>
			</tr>
			<tr>
				<td height="24"><input type="button" class="bottonRightCol"
					value="Voltar Pesquisa"
					onclick="window.location.href='<html:rewrite page="/exibirPesquisarResolucaoDiretoriaAction.do"/>'" /></td>
			</tr>
		</table>
		</pg:pager></td>
	</tr>

</table>




</body>

</html:html>