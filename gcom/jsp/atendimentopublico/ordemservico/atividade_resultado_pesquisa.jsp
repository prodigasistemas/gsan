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
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="PesquisarAtividadeActionForm" dynamicJavascript="false" />
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
				<td class="parabg">Pesquisar Atividade</td>

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
				<td width="30%" align="left"><strong>Descrição</strong></td>
				<td width="15%" align="left"><strong>Descrição Abreviada</strong></td>
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
					<logic:present name="atividades">
					<%int cont = 0;%>
			        <logic:iterate name="atividades" id="atividade">
						<pg:item>
							<%cont = cont + 1;
							if (cont % 2 == 0) {%>
								<tr bgcolor="#cbe5fe">								
							<%} else {	%>
								<tr bgcolor="#FFFFFF">
							<%}%>
					
									<td width="10%" align="center">
										<logic:notEmpty name="caminhoRetornoTelaPesquisaAtividade">
											<a href="javascript:enviarDadosParametros('<bean:write name="caminhoRetornoTelaPesquisaAtividade"/>', '<bean:write name="atividade" property="id"/>', '<bean:write name="atividade" property="descricao"/>', 'atividade');">
												<bean:write name="atividade" property="id" /> 
											</a>
										</logic:notEmpty> 
										<logic:empty
											name="caminhoRetornoTelaPesquisaAtividade">
										   <logic:notEmpty
											  name="caminhoRetornoAtividadeTipoServico">
												<a
												  href="javascript:enviarDadosParametrosFecharPopup('<bean:write name="caminhoRetornoAtividadeTipoServico"/>', '<bean:write name="atividade" property="id"/>', '<bean:write name="atividade" property="descricao"/>', 'atividade');">
												<bean:write name="atividade" property="id" /> </a>
										   </logic:notEmpty> 	
										   <logic:empty name="caminhoRetornoTelaPesquisaAtividade">
											<a href="javascript:enviarDados( '<bean:write name="atividade" property="id"/>', '<bean:write name="atividade" property="descricao"/>','atividade');">
												<bean:write name="atividade" property="id" />
											</a>
										   </logic:empty>	
										</logic:empty>								
									</td>

									<td width="30%" align="left">
										<bean:write name="atividade" property="descricao"/>
									</td>

									<td width="15%" align="left">
										<bean:write name="atividade" property="descricaoAbreviada" />
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
					onclick="window.location.href='/gsan/exibirPesquisarAtividadeAction.do'"/></td>
			</tr>
		</table>
		</pg:pager></td>
	</tr>

</table>

</body>

</html:html>
