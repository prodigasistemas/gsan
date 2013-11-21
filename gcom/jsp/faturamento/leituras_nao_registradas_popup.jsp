<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>

<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="ExibirLeiturasNaoRegistradasActionForm" />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript">


</script>


</head>

<body leftmargin="5" topmargin="5" onload="resizePageSemLink(580, 330);" >
<html:form action="/exibirLeiturasNaoRegistradasAction" method="post">
	<table width="452" border="0" cellspacing="5" cellpadding="0">
		<tr>
			<td width="452" valign="top" class="centercoltext">
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
					<td class="parabg">Leituras Não Registradas</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<table width="100%" border="0">

				<tr>
					<td>
						<table width="100%" bgcolor="#99CCFF" border="0">
							<tr bordercolor="#000000">
								<td width="200" bgcolor="#90c7fc" align="center"><strong>Localidade</strong></td>
								<td width="180" bordercolor="#000000" bgcolor="#90c7fc"
									align="center">
									<div align="center"><strong>Setor Comercial</strong></div>
								</td>
								<td width="120" bgcolor="#90c7fc" align="center"><strong>C&oacute;digo da Rota</strong></td>
								<td width="100" bgcolor="#90c7fc" align="center"><strong>Quantidade de Im&oacute;veis</strong></td>
							</tr>
					</table>
					</td>
				</tr>
				
				<tr>
					<td>
						<table width="100%" bgcolor="#99CCFF">
							<pg:pager isOffset="true" index="half-full" maxIndexPages="10"
								export="currentPageNumber=pageNumber;pageOffset"
								maxPageItems="10" items="${sessionScope.totalRegistros}">
								<pg:param name="pg" />
								<pg:param name="q" />
								<logic:present name="colecaoLeiturasNaoRegistradas" scope="request">
									<%int cont = 0;%>
									<logic:iterate name="colecaoLeiturasNaoRegistradas" id="leiturasNaoRegistradasHelper"
										scope="request">
										<pg:item>
											<%cont = cont + 1;
												if (cont % 2 == 0) {%>
													<tr bgcolor="#cbe5fe">
												<%} else {%>
													<tr bgcolor="#FFFFFF">
												<%}%>
	
												<td width="16%" align="center">
													${leiturasNaoRegistradasHelper.localidade.descricao}
												</td>
												<td width="15%" align="center">
													${leiturasNaoRegistradasHelper.setorComercial.descricao}
												</td>
												<td width="10%" align="center">
													${leiturasNaoRegistradasHelper.codigoRota}
												</td>
												<td width="10%" align="center">
													${leiturasNaoRegistradasHelper.valorTotalImoveis}
												</td>
	
											</tr>
										</pg:item>
									</logic:iterate>
							</logic:present>
					</table>
					</td>
				</tr>




				<tr>
					<td colspan="2">
					
						<logic:present	name="caminhoRetornoTelaPesquisaFuncionalidade">
						<INPUT TYPE="button" class="bottonRightCol" value="Voltar"
						onclick="redirecionarSubmit('${caminhoRetornoTelaPesquisaFuncionalidade}.do')" />
						</logic:present>
						<logic:notPresent	name="caminhoRetornoTelaPesquisaFuncionalidade">
						<INPUT TYPE="button" class="bottonRightCol" value="Fechar"
						onclick="window.close();" />
						</logic:notPresent>
				</tr>
				

			</table>
			</td>
		</tr>
		
		
		
	</table>
	<p>&nbsp;</p>
	
	
</html:form>
</body>
</html:html>

