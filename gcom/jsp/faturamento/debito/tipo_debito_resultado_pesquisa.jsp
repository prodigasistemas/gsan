<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>

<%@ page import="java.util.Collection,gcom.util.ConstantesSistema, gcom.util.Util, gcom.faturamento.debito.DebitoACobrar"%>
<html:html>
<head>
<title>GSAN - Sistema Integrado de Gest&atilde;o de Servi&ccedil;os de Saneamento</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="PesquisarTipoDebitoActionForm" dynamicJavascript="false" />
</head>

<body leftmargin="5" topmargin="5"
	onload="javascript:resizePageSemLink(806, 425)">
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
				<td class="parabg">Pesquisa de Tipo de Débito</td>
				<td width="11"><img border="0"
					src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
			</tr>
		</table>
		<p>&nbsp;</p>
		<table width="100%" border="0" bgcolor="#90c7fc">
			<tr align="left" bgcolor="#90c7fc">
				  <td width="10%" align="center" bgcolor="#90c7fc"><strong>Código</strong></td>
				  <td width="20%" align="center" bgcolor="#90c7fc"><strong>Descrição</strong></td>
				  <td width="20%" align="center" bgcolor="#90c7fc"><strong>Tipo de Financiamento</strong></td>
				  <td width="20%" align="center" bgcolor="#90c7fc"><strong>Item de Lançamento Contábil</strong></td>
				  <td width="10%" align="center" bgcolor="#90c7fc"><strong>Valor Limite</strong></td>
			</tr>
			
			<%--Esquema de paginação--%>
			<pg:pager isOffset="true" index="half-full" maxIndexPages="10"
				export="currentPageNumber=pageNumber;pageOffset"
				maxPageItems="10" items="${sessionScope.totalRegistros}">
				<pg:param name="q" />

					<%int cont = 0;%>
			        <logic:iterate name="colecaoTipoDebitos" id="tipoDebito">
						<pg:item>
							<%cont = cont + 1;
							if (cont % 2 == 0) {%>
								<tr bgcolor="#cbe5fe">								
							<%} else {	%>
								<tr bgcolor="#FFFFFF">
							<%}%>
									 <td width="10%">
									   <div align="center">
										 <bean:write name="tipoDebito" property="id"/>							    
									   </div> 
									 </td>
									 <td width="20%">
									   <div align="left">
									    <logic:notPresent name="caminhoRetornoTelaPesquisaTipoDebito">
									      <a href="javascript:enviarDados('<bean:write name="tipoDebito" property="id"/>', '<bean:write name="tipoDebito" property="descricao"/>', 'tipoDebito');">
											<bean:write name="tipoDebito" property="descricao"/>							    
										  </a>
										</logic:notPresent>
										<logic:present name="caminhoRetornoTelaPesquisaTipoDebito">
											<a href="javascript:enviarDadosParametros('<bean:write name="caminhoRetornoTelaPesquisaTipoDebito"/>', '<bean:write name="tipoDebito" property="id"/>', '<bean:write name="tipoDebito" property="descricao"/>', 'tipoDebito');">
												<font color="#000000"> 
													<bean:write name="tipoDebito" property="descricao"/> 
												</font> 
											</a>
										</logic:present>
										</div> 
									  </td>
									  <td width="20%">
										<div align="left">
										  <bean:write name="tipoDebito"	property="financiamentoTipo.descricao"/>
										</div>
									  </td>
									  <td width="20%">
										<div align="left">
										  <bean:write name="tipoDebito" property="lancamentoItemContabil.descricao" />
										</div>
									  </td>
									  										  
										  <td width="10%">
											<div align="center">
											  <logic:present name="tipoDebito" property="valorLimite">
				                				<bean:write name="tipoDebito" property="valorLimite" formatKey="money.format"/>
							  				  </logic:present>
							  				  <logic:notPresent name="tipoDebito" property="valorLimite">
												&nbsp;
							  				  </logic:notPresent>								
											</div>
										  </td>
		
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
				</pg:pager>
				<%-- Fim do esquema de paginação --%>
			</tr>
			<tr>
				<td height="24"><input type="button" class="bottonRightCol"
					value="Voltar Pesquisa"
					onclick="window.location.href='<html:rewrite page="/exibirPesquisarTipoDebitoAction.do?novaPesquisa=1&objetoConsulta=1&voltarPesquisa=1"/>'" /></td>
			</tr>
		</table>
</table>
</body>
</html:html>
