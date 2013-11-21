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
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="PesquisarAgenciaBancariaActionForm" dynamicJavascript="false" />
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
				<td class="parabg">Pesquisar Ag&ecirc;ncia Banc&aacute;ria</td>

				<td width="11"><img border="0"
					src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
			</tr>
			<tr>
				<td height="5" colspan="3"></td>
			</tr>
		</table>


		<table width="100%" cellpadding="0" cellspacing="0">
			<tr>
				<td></td>
			</tr>
			<tr>
				<!--<td colspan="4" bgcolor="#3399FF"> -->
				<td colspan="6"></td>
			</tr>

			<tr align="left" bgcolor="#90c7fc">
				<td width="10%" align="center"><strong>C&oacute;digo</strong></td>
				<td width="36%" align="center"><strong>Descri&ccedil;&atilde;o</strong></td>
				<td width="56%" align="center"><strong>Banco</strong></td>
			</tr>
			<tr>
				<td colspan="6">
				<table width="100%" bgcolor="#99CCFF">
					<%--Esquema de paginação--%>
					<pg:pager isOffset="true" index="half-full" maxIndexPages="10"
							  export="currentPageNumber=pageNumber;pageOffset"
				             maxPageItems="10" items="${sessionScope.totalRegistros}">
					<pg:param name="q" />
					<pg:param name="pg"/>
					<logic:present name="colecaoAgencia">
					<%int cont = 0;%>
			        <logic:iterate name="colecaoAgencia" id="agenciaBancaria">
						<pg:item>
					<%	cont = cont + 1;
						if (cont % 2 == 0) {%>
						<tr bgcolor="#cbe5fe">
					<%	} else {	%>
						<tr bgcolor="#FFFFFF">
					<%	}	%>
					
							<td align="center" width="60">
							
								<bean:write name="agenciaBancaria" property="codigoAgencia"/>
								
							</td>
							<td align="center" width="260">
								<a href="javascript:enviarDados('<bean:write name="agenciaBancaria" property="codigoAgencia"/>', '<bean:write name="agenciaBancaria" property="nomeAgencia"/>', 'agenciaBancaria');">
								<bean:write name="agenciaBancaria" property="nomeAgencia"/>
							
										<td align="center">
											<logic:notEmpty name="agenciaBancaria" property="banco">
											    <bean:write name="agenciaBancaria" property="banco.descricao"/>
											</logic:notEmpty>
											<logic:empty name="agenciaBancaria" property="banco">
											&nbsp;
											</logic:empty>
										</td>
									</tr>
								</pg:item>
							</logic:iterate>
						</logic:present>
				</table>

				</td>
			</tr>

		</table>
		<table width="100%" border="0">
			<tr>
				<td>
					<div align="center">
						<strong><%@ include
						file="/jsp/util/indice_pager_novo.jsp"%>
						</strong>
					</div>
				</td>
			</tr>
			<tr>
				<td height="24"><input type="button" class="bottonRightCol"
					value="Voltar Pesquisa"
					onclick="window.location.href='/gsan/exibirPesquisarAgenciaBancariaAction.do'"/></td>
			</tr>
		</table>
		</pg:pager></td>
	</tr>

</table>

</body>

</html:html>
