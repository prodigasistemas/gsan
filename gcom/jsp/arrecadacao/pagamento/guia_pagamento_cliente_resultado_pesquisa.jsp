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
		  <td class="parabg">Pesquisa de Guias de Pagamento do Cliente</td>
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
		            <strong>Dados do Cliente</strong>
		          </div>  
		        </td>
			  </tr>
			  <tr bgcolor="#cbe5fe">
				<td width="100%" align="center">
				  <table width="100%" border="0">
					<tr> 
					  <td height="10" width="150"><strong>Código do Cliente:</strong></td>
					  <td>					    					    
						<html:text name="PesquisarGuiaPagamentoActionForm" property="idCliente" size="9" readonly="true" style="background-color:#EFEFEF; border:0"/>
					  </td>
					</tr>
					<tr> 
					  <td height="10"><strong>Nome do Cliente:</strong></td>
					  <td>
						<html:text name="PesquisarGuiaPagamentoActionForm" property="nomeCliente" size="50" readonly="true" style="background-color:#EFEFEF; border:0"/>
					  </td>
					</tr>
					<tr> 
					  <td height="10"><strong>CPF/CNPJ:</strong></td>
					  <td>
						<html:text name="PesquisarGuiaPagamentoActionForm" property="cpf_cnpj" size="23" readonly="true" style="background-color:#EFEFEF; border:0"/>
					  </td>
					</tr>
					<tr> 
					  <td height="10"><strong>Profissão:</strong></td>
					  <td>
						<html:text name="PesquisarGuiaPagamentoActionForm" property="profissao" size="30" readonly="true" style="background-color:#EFEFEF; border:0"/>
					  </td>
					</tr>
					<tr> 
					  <td height="10"><strong>Ramo da Atividade:</strong></td>
					  <td>
						<html:text name="PesquisarGuiaPagamentoActionForm" property="ramoAtividade" size="20" readonly="true" style="background-color:#EFEFEF; border:0"/>
					  </td>
					</tr>
					<tr> 
          			  <td colspan="4">
          			    <table width="100%"  border="0" cellpadding="1" cellspacing="0" bgcolor="#99CCFF" bordercolor="#99CCFF">
              			  <tr> 
                			<td height="17" align="center"><strong>Endere&ccedil;o de Correspond&ecirc;ncia</strong></td>
              			  </tr>
            			</table>
            		  </td>
        			</tr>
        			<tr> 
         			  <td height="22" colspan="4"> 
            			<div align="left"> 
              			  <table width="100%"  border="0" cellpadding="1" cellspacing="0" bgcolor="#99CCFF" bordercolor="#99CCFF">
                			<tr bgcolor="#cbe5fe"> 
                  			  <td> 
                  			    <div align="center">
                  			      <bean:write name="PesquisarGuiaPagamentoActionForm" property="enderecoCliente"/>
								</div>
							  </td>
                			</tr>
              			  </table>
            			</div>
            		  </td>
        			</tr>
        			<tr> 
          			  <td><strong>Telefone para Contato:</strong></td>
          			  <td colspan="3">
          			    <input name="telefoneCliente" value="<bean:write name="PesquisarGuiaPagamentoActionForm" property="telefoneCliente"/>" type="text" disabled style="background-color:#EFEFEF; border:0" size="15" maxlength="10">
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
		  <td colspan="5"><strong>Guias de Pagamento do Cliente</strong></td>
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
						
						  <td width="20%">
						    <div>
						      <a href="javascript:enviarDadosCincoParametros('<bean:write name="guiaPagamento" property="id"/>', '<bean:write name="guiaPagamento" property="valorDebito"/>', '<bean:write name="guiaPagamento" property="debitoTipo.id"/>', '<bean:write name="guiaPagamento" property="debitoTipo.descricao"/>', 'guiaPagamento');">
								<bean:write name="guiaPagamento" property="debitoTipo.descricao"/>							    
							  </a>
							</div> 
						  </td>
						  
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
			<td height="24"><input type="button" class="bottonRightCol"	value="Voltar Pesquisa" onclick="window.location.href='<html:rewrite page="/exibirPesquisarGuiaPagamentoAction.do?idCliente=${requestScope.idCliente}"/>'" /></td>
		  </tr>
		</table>
		</pg:pager>
	  </td>
	</tr>
</table>

</body>

</html:html>
