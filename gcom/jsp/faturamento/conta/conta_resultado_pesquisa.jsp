<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>

<%@ page import="java.util.Collection,gcom.util.ConstantesSistema, gcom.util.Util, gcom.faturamento.conta.Conta"%>
<html:html>
<head>
<title>GSAN - Sistema Integrado de Gest&atilde;o de Servi&ccedil;os de Saneamento</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="PesquisarContaActionForm" dynamicJavascript="false" />
</head>

<body leftmargin="5" topmargin="5">
<table width="750" border="0" cellspacing="5" cellpadding="0">
  <tr>
	<td width="750" valign="top" class="centercoltext">
	  <table height="100%">
		<tr>
		  <td></td>
		</tr>
	  </table>
	  <table width="100%" border="0" align="center" cellpadding="0"	cellspacing="0">
		<tr>
		  <td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_left.gif" /></td>
		  <td class="parabg">Pesquisa de Contas do Imóvel</td>
		  <td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
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
          <td colspan="8">
			<table width="100%" align="center" bgcolor="#99CCFF" border="0">
			  <tr>
				<td>Dados do Imóvel:</td>
			  </tr>
			  <tr bgcolor="#cbe5fe">
				<td width="100%" align="center">
				  <table width="100%" border="0">
				    <tr> 
					  <td height="10" width="150"><strong>Matrícula do Imóvel:</strong></td>
					  <td>					    					    
						<html:text name="PesquisarContaActionForm" property="idImovel" size="9" readonly="true" style="background-color:#EFEFEF; border:0"/>
					  </td>
					</tr>
					<tr> 
					  <td height="10" width="150"><strong>Inscrição:</strong></td>
					  <td>					    					    
						<html:text name="PesquisarContaActionForm" property="inscricaoImovel" size="25" readonly="true" style="background-color:#EFEFEF; border:0"/>
					  </td>
					</tr>
					<tr> 
					  <td height="10"><strong>Nome do Cliente Usuário:</strong></td>
					  <td>
						<html:text name="PesquisarContaActionForm" property="nomeClienteUsuario" size="45" readonly="true" style="background-color:#EFEFEF; border:0"/>
					  </td>
					</tr>
					<tr> 
					  <td height="10"><strong>Situação de Água:</strong></td>
					  <td>
						<html:text name="PesquisarContaActionForm" property="situacaoAguaImovel" size="45" readonly="true" style="background-color:#EFEFEF; border:0"/>
					  </td>
					</tr>
					<tr> 
					  <td height="10"><strong>Situação de Esgoto:</strong></td>
					  <td>
						<html:text name="PesquisarContaActionForm" property="situacaoEsgotoImovel" size="45" readonly="true" style="background-color:#EFEFEF; border:0"/>
					  </td>
					</tr>
				  </table>
				</td>
			  </tr>
			</table>
		  </td>
        </tr>     
		<tr>
		  <td colspan="8"></td>
		</tr>
		<tr>
		  <td colspan="8"><strong>Contas do Imóvel</strong></td>
		</tr>
		<tr bordercolor="#000000">
		  <td width="10%" align="center" bgcolor="#90c7fc"><strong>Mês/Ano</strong></td>
		  <td width="10%" align="center" bgcolor="#90c7fc"><strong>Vencimento</strong></td>
		  <td width="14%" align="center" bgcolor="#90c7fc"><strong>Valor da Conta</strong></td>
		  <td width="14%" align="center" bgcolor="#90c7fc"><strong>Consumo de Água</strong></td>
		  <td width="14%" align="center" bgcolor="#90c7fc"><strong>Esgoto Coletado</strong></td>
		  <td width="10%" align="center" bgcolor="#90c7fc"><strong>Validade</strong></td>
		  <td width="10%" align="center" bgcolor="#90c7fc"><strong>Data Emissão</strong></td>
		  <td width="20%" align="center" bgcolor="#90c7fc"><strong>Situação</strong></td>
		</tr>
		<tr>
		  <td colspan="8">
			<table width="100%" bgcolor="#99CCFF">

			  <%--Esquema de paginação--%>
			<pg:pager isOffset="true" index="half-full" maxIndexPages="10"
							export="currentPageNumber=pageNumber;pageOffset"
							maxPageItems="10" items="${sessionScope.totalRegistros}">
			    <pg:param name="pg" />
			    <pg:param name="q" />
				  <logic:present name="colecaoContasImovel">
					<%int cont = 1;%>
					<logic:iterate name="colecaoContasImovel" id="conta" type="Conta">
					  <pg:item>
						<%cont = cont + 1;
						if (cont % 2 == 0) {%>
						<tr bgcolor="#FFFFFF">
						<%} else {
						%>
						<tr bgcolor="#cbe5fe">
						<%}%>
						
							<logic:notEmpty name="caminhoRetornoTelaPesquisaConta">
								  <td width="10%">
								    <div align="center">
								      <a href="javascript:enviarDadosQuatroParametrosCaminhoRetorno('<bean:write name="caminhoRetornoTelaPesquisaConta" />', '<bean:write name="conta" property="id"/>', '<%="" + Util.formatarMesAnoReferencia(conta.getReferencia())%>', '<bean:write name="conta" property="valorTotalConta" formatKey="money.format"/>', 'conta');">
										<%="" + Util.formatarMesAnoReferencia(conta.getReferencia())%>							    
									  </a>
									</div>
								  </td>
							</logic:notEmpty>
							
							<logic:empty name="caminhoRetornoTelaPesquisaConta">
								  <td width="10%">
								    <div align="center">
								      <a href="javascript:enviarDadosQuatroParametros('<bean:write name="conta" property="id"/>', '<%="" + Util.formatarMesAnoReferencia(conta.getReferencia())%>', '<bean:write name="conta" property="valorTotalConta" formatKey="money.format"/>', 'conta');">
										<%="" + Util.formatarMesAnoReferencia(conta.getReferencia())%>							    
									  </a>
								      
								      <%--
								      <a href="javascript:enviarDados('<bean:write name="conta" property="id"/>', '<%="" + Util.formatarMesAnoReferencia(conta.getReferencia())%>', 'conta');">
									    <%="" + Util.formatarMesAnoReferencia(conta.getReferencia())%>
									  </a>
									  --%>
									  
									</div>
								  </td>
							</logic:empty>
						  
						  <td width="10%">
							<div align="center">
							  <bean:write name="conta"	property="dataVencimentoConta" formatKey="date.format"/>
							</div>
						  </td>
						  <td width="14%">
							<div align="right">
							  <bean:write name="conta" property="valorTotal" formatKey="money.format"/>
							</div>
						  </td>
						  <td width="14%">
							<div align="center">
							  <logic:present name="conta" property="consumoAgua">
                				<bean:write name="conta" property="consumoAgua" />
			  				  </logic:present>
			  				  <logic:notPresent name="conta" property="consumoAgua">
								&nbsp;
			  				  </logic:notPresent>								
							</div>
						  </td>
						  <td width="14%">
							<div align="center">
							  <logic:present name="conta" property="consumoEsgoto">
                				<bean:write name="conta"	property="consumoEsgoto" />
			  				  </logic:present>
			  				  <logic:notPresent name="conta" property="consumoEsgoto">
								&nbsp;
			  				  </logic:notPresent>								
							</div>
						  </td>
						  <td width="10%">
							<div align="center">
							  <logic:present name="conta" property="dataValidadeConta">
                				<bean:write name="conta" property="dataValidadeConta" formatKey="date.format"/>
			  				  </logic:present>
			  				  <logic:notPresent name="conta" property="dataValidadeConta">
								&nbsp;
			  				  </logic:notPresent>								
							</div>
						  </td>
						  <td width="10%">
							<div align="center">
							  <logic:present name="conta" property="dataEmissao">
							    <bean:write name="conta" property="dataEmissao" formatKey="date.format"/>
			  				  </logic:present>
			  				  <logic:notPresent name="conta" property="dataEmissao">
								&nbsp;
			  				  </logic:notPresent>								
							</div>
						  </td>
						  <td width="20%">
							<div>
							  <bean:write name="conta" property="debitoCreditoSituacaoAtual.descricaoDebitoCreditoSituacao" />
							</div>
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
				<div align="center"><strong><%@ include
					file="/jsp/util/indice_pager_novo.jsp"%></strong></div>
			</td>
		  </tr>
		  <tr>
			<td height="24"><input type="button" class="bottonRightCol"	value="Voltar Pesquisa" onclick="window.location.href='<html:rewrite page="/exibirPesquisarContaAction.do?idImovel=${requestScope.idImovel}"/>'" /></td>
		  </tr>
		</table>
		</pg:pager>
	  </td>
	</tr>
</table>

</body>

</html:html>
