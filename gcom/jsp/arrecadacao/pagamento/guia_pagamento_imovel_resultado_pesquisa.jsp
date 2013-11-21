<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>

<%@ page import="java.util.Collection,gcom.util.ConstantesSistema, gcom.util.Util, gcom.arrecadacao.pagamento.GuiaPagamento"%>
<html:html>
<head>
<title>GSAN - Sistema Integrado de Gest&atilde;o de Servi&ccedil;os de Saneamento</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<html:javascript formName="PesquisarGuiaPagamentoActionForm" dynamicJavascript="false" staticJavascript="true" />
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
		  <td class="parabg">Pesquisa de Guias de Pagamento do Imóvel</td>
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
		        <td>
		          <div align="center">
		            <strong>Dados do Imóvel</strong>
		          </div>  
		        </td>
			  </tr>
			  <tr bgcolor="#cbe5fe">
				<td width="100%" align="center">
				  <table width="100%" border="0">
				    <tr> 
					  <td height="10" width="150"><strong>Matrícula do Imóvel:</strong></td>
					  <td>					    					    
						<html:text name="PesquisarGuiaPagamentoActionForm" property="idImovel" size="9" readonly="true" style="background-color:#EFEFEF; border:0"/>
					  </td>
					</tr>
					<tr> 
					  <td height="10" width="150"><strong>Inscrição:</strong></td>
					  <td>					    					    
						<html:text name="PesquisarGuiaPagamentoActionForm" property="inscricaoImovel" size="25" readonly="true" style="background-color:#EFEFEF; border:0"/>
					  </td>
					</tr>
					<tr> 
					  <td height="10"><strong>Nome do Cliente Usuário:</strong></td>
					  <td>
						<html:text name="PesquisarGuiaPagamentoActionForm" property="nomeClienteUsuario" size="45" readonly="true" style="background-color:#EFEFEF; border:0"/>
					  </td>
					</tr>
					<tr> 
					  <td height="10"><strong>Situação de Água:</strong></td>
					  <td>
						<html:text name="PesquisarGuiaPagamentoActionForm" property="situacaoAguaImovel" size="45" readonly="true" style="background-color:#EFEFEF; border:0"/>
					  </td>
					</tr>
					<tr> 
					  <td height="10"><strong>Situação de Esgoto:</strong></td>
					  <td>
						<html:text name="PesquisarGuiaPagamentoActionForm" property="situacaoEsgotoImovel" size="45" readonly="true" style="background-color:#EFEFEF; border:0"/>
					  </td>
					</tr>
				  </table>
				</td>
			  </tr>
			</table>
		  </td>
        </tr>     
		<tr>
		  <td colspan="5"></td>
		</tr>
		<tr>
		  <td colspan="5"><strong>Guias de Pagamento do Imóvel</strong></td>
		</tr>
		<tr bordercolor="#000000">
		  <td width="20%" align="center" bgcolor="#90c7fc"><strong>Tipo do Débito</strong></td>
		  <td width="10%" align="center" bgcolor="#90c7fc"><strong>Prestação</strong></td>
		  <td width="10%" align="center" bgcolor="#90c7fc"><strong>Data Emissão</strong></td>
		  <td width="10%" align="center" bgcolor="#90c7fc"><strong>Data de Vencimento</strong></td>
		  <td width="12%" align="center" bgcolor="#90c7fc"><strong>Valor da Guia de Pagamento</strong></td>
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
				  <logic:present name="colecaoGuiasPagamento">
					<%int cont = 1;%>
					<logic:iterate name="colecaoGuiasPagamento" id="guiaPagamento" type="GuiaPagamento">
					  <pg:item>
						<%cont = cont + 1;
						if (cont % 2 == 0) {%>
						<tr bgcolor="#FFFFFF">
						<%} else {
						%>
						<tr bgcolor="#cbe5fe">
						<%}%>
						
							<logic:notEmpty name="caminhoRetornoTelaPesquisaGuiaPagamento">
								  <td width="20%">
								    <div>
								      <a href="javascript:enviarDadosCincoParametrosCaminhoRetorno('<bean:write name="caminhoRetornoTelaPesquisaGuiaPagamento"/>', '<bean:write name="guiaPagamento" property="id"/>', '<bean:write name="guiaPagamento" property="valorDebito"/>', '<bean:write name="guiaPagamento" property="debitoTipo.id"/>', '<bean:write name="guiaPagamento" property="debitoTipo.descricao"/>', 'guiaPagamento');">
										<bean:write name="guiaPagamento" property="debitoTipo.descricao"/>							    
									  </a>
									</div> 
								  </td>
							</logic:notEmpty>
							
							<logic:empty name="caminhoRetornoTelaPesquisaGuiaPagamento">
								  <td width="20%">
								    <div>
								      <a href="javascript:enviarDadosCincoParametros('<bean:write name="guiaPagamento" property="id"/>', '<bean:write name="guiaPagamento" property="valorDebito"/>', '<bean:write name="guiaPagamento" property="debitoTipo.id"/>', '<bean:write name="guiaPagamento" property="debitoTipo.descricao"/>', 'guiaPagamento');">
										<bean:write name="guiaPagamento" property="debitoTipo.descricao"/>							    
									  </a>
									</div> 
								  </td>
							</logic:empty>
						  
						  <td width="10%" align="center">
						  	<bean:write name="guiaPagamento" property="prestacaoFormatada"/>	
						  </td>		
						  
						  <td width="10%">
							<div align="center">
							  <logic:present name="guiaPagamento" property="dataEmissao">
							    <bean:write name="guiaPagamento" property="dataEmissao" formatKey="date.format"/>
							  </logic:present>
							  <logic:notPresent name="guiaPagamento" property="dataEmissao">
								&nbsp;
			  				  </logic:notPresent>	  
							</div>
						  </td>
						  
						  <td width="10%">
							<div align="center">
							  <logic:present name="guiaPagamento" property="dataVencimento">
							    <bean:write name="guiaPagamento" property="dataVencimento" formatKey="date.format"/>
							  </logic:present> 
							  <logic:notPresent name="guiaPagamento" property="dataVencimento">
								&nbsp;
			  				  </logic:notPresent>	   
							</div>
						  </td>
						  
						  <td width="12%">
							<div align="right">
							  <logic:present name="guiaPagamento" property="valorDebito">
							    <bean:write name="guiaPagamento" property="valorDebito" formatKey="money.format"/>
							  </logic:present> 
							  <logic:notPresent name="guiaPagamento" property="valorDebito">
								&nbsp;
			  				  </logic:notPresent> 
							</div>
						  </td>
						  
						  <td width="20%">
							<div>
							  <bean:write name="guiaPagamento" property="debitoCreditoSituacaoAtual.descricaoDebitoCreditoSituacao" />
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
			<td height="24"><input type="button" class="bottonRightCol"	value="Voltar Pesquisa" onclick="window.location.href='<html:rewrite page="/exibirPesquisarGuiaPagamentoAction.do?idImovel=${requestScope.idImovel}"/>'" /></td>
		  </tr>
		</table>
		</pg:pager>
	  </td>
	</tr>
</table>

</body>

</html:html>
