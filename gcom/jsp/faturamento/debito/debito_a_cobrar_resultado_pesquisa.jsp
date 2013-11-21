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
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="PesquisarDebitoACobrarActionForm" dynamicJavascript="false" />
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
		  <td class="parabg">Pesquisar Débitos A Cobrar do Imóvel</td>
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
          <td colspan="11">
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
						<html:text name="PesquisarDebitoACobrarActionForm" property="idImovel" size="9" readonly="true" style="background-color:#EFEFEF; border:0"/>
					  </td>
					</tr>
					<tr>
					  <td height="10" width="150"><strong>Inscrição:</strong></td>
					  <td>					    					    
						<html:text name="PesquisarDebitoACobrarActionForm" property="inscricaoImovel" size="25" readonly="true" style="background-color:#EFEFEF; border:0"/>
					  </td>
					</tr>
					<tr> 
					  <td height="10"><strong>Nome do Cliente Usuário:</strong></td>
					  <td>
						<html:text name="PesquisarDebitoACobrarActionForm" property="nomeClienteUsuario" size="45" readonly="true" style="background-color:#EFEFEF; border:0"/>
					  </td>
					</tr>
					<tr> 
					  <td height="10"><strong>Situação de Água:</strong></td>
					  <td>
						<html:text name="PesquisarDebitoACobrarActionForm" property="situacaoAguaImovel" size="45" readonly="true" style="background-color:#EFEFEF; border:0"/>
					  </td>
					</tr>
					<tr> 
					  <td height="10"><strong>Situação de Esgoto:</strong></td>
					  <td>
						<html:text name="PesquisarDebitoACobrarActionForm" property="situacaoEsgotoImovel" size="45" readonly="true" style="background-color:#EFEFEF; border:0"/>
					  </td>
					</tr>
				  </table>
				</td>
			  </tr>
			</table>
		  </td>
        </tr>     
		<tr>
		  <td colspan="11"></td>
		</tr>
		
		<tr>
		  <td colspan="11"><strong>Débitos A Cobrar do Imóvel</strong></td>
		</tr>
		<tr bordercolor="#000000">
		  <td width="20%" align="center" bgcolor="#90c7fc" rowspan="2"><strong>Tipo do Débito</strong></td>
		  <td width="10%" align="right" bgcolor="#90c7fc" rowspan="2"><strong>Mês/Ano Referência</strong></td>
		  <td height="19" align="center" bgcolor="#90c7fc" colspan="2"><strong>Geração</strong></td>
		  <td width="10%" align="center" bgcolor="#90c7fc" rowspan="2"><strong>Valor Total do Débito</strong></td>
		  <td width="10%" align="center" bgcolor="#90c7fc" rowspan="2"><strong>Parcela</strong></td>
		  <td width="10%" align="center" bgcolor="#90c7fc" rowspan="2"><strong>Valor Restante a Ser Cobrado</strong></td>
		  <td width="15%" align="center" bgcolor="#90c7fc" rowspan="2"><strong>Situação do Débito</strong></td>
		</tr>
		<tr> 
          <td bgcolor="#90c7fc">
            <div align="center"><strong>Data</strong></div>
          </td>
          <td bgcolor="#90c7fc">
            <div align="center"><strong>Hora</strong></div>
          </td>
        </tr>  
        
                
		<tr>
		  <td colspan="11">
			<table width="100%" bgcolor="#99CCFF">

			  <%--Esquema de paginação--%>
			 <pg:pager isOffset="true" index="half-full" maxIndexPages="10"
							export="currentPageNumber=pageNumber;pageOffset"
							maxPageItems="10" items="${sessionScope.totalRegistros}">
			    <pg:param name="pg" />
			    <pg:param name="q" />
				  <logic:present name="colecaoDebitosACobrar">
					<%int cont = 1;%>
					<logic:iterate name="colecaoDebitosACobrar" id="debitoACobrar" type="DebitoACobrar">
					  <pg:item>
						<%cont = cont + 1;
						if (cont % 2 == 0) {%>
						<tr bgcolor="#FFFFFF">
						<%} else {
						%>
						<tr bgcolor="#cbe5fe">
						<%}%>
						
							<logic:notEmpty name="caminhoRetornoTelaPesquisaDebitoACobrar">
								  <td width="20%">
									   <div>
									      <a href="javascript:enviarDadosCincoParametrosCaminhoRetorno('<bean:write name="caminhoRetornoTelaPesquisaDebitoACobrar"/>', '<bean:write name="debitoACobrar" property="id"/>', '<bean:write name="debitoACobrar" property="valorDebito"/>', '<bean:write name="debitoACobrar" property="debitoTipo.id"/>', '<bean:write name="debitoACobrar" property="debitoTipo.descricao"/>', 'debitoACobrar');">
											<bean:write name="debitoACobrar" property="debitoTipo.descricao"/>							    
										  </a>
										</div> 
								  </td>
							</logic:notEmpty>
							
							<logic:empty name="caminhoRetornoTelaPesquisaDebitoACobrar">
								  <td width="20%">
									   <div>
									      <a href="javascript:enviarDadosCincoParametros('<bean:write name="debitoACobrar" property="id"/>', '<bean:write name="debitoACobrar" property="valorDebito"/>', '<bean:write name="debitoACobrar" property="debitoTipo.id"/>', '<bean:write name="debitoACobrar" property="debitoTipo.descricao"/>', 'debitoACobrar');">
											<bean:write name="debitoACobrar" property="debitoTipo.descricao"/>							    
										  </a>
										</div> 
								  </td>
							</logic:empty>
						 
						  <td width="10%">
						    <logic:present name="debitoACobrar" property="anoMesReferenciaDebito">
						      <div align="center">
						        <%="" + Util.formatarMesAnoReferencia(debitoACobrar.getAnoMesReferenciaDebito())%>
							  </div>
							</logic:present>
							<logic:notPresent name="debitoACobrar" property="anoMesReferenciaDebito">
								&nbsp;
			  				</logic:notPresent>		
						  </td>
						  
						  <td>
							<div align="center">
							  <bean:write name="debitoACobrar"	property="geracaoDebito" formatKey="date.format"/>
							</div>
						  </td>
						  <td>
							<div align="center">
							  <bean:write name="debitoACobrar" property="geracaoDebito" formatKey="hour.format"/>
							</div>
						  </td>
						  
						  <td width="10%">
							<div align="right">
							  <logic:present name="debitoACobrar" property="valorDebito">
                				<bean:write name="debitoACobrar" property="valorDebito" formatKey="money.format"/>
			  				  </logic:present>
			  				  <logic:notPresent name="debitoACobrar" property="valorDebito">
								&nbsp;
			  				  </logic:notPresent>								
							</div>
						  </td>
						  
						  <td width="10%">
							<div align="center">
							  <logic:present name="debitoACobrar" property="numeroPrestacaoCobradas">
                				<bean:write name="debitoACobrar" property="numeroPrestacaoCobradas" />/<bean:write name="debitoACobrar" property="numeroPrestacaoDebitoMenosBonus" /> 
			  				  </logic:present>
			  				  <logic:notPresent name="debitoACobrar" property="numeroPrestacaoCobradas">
								&nbsp;
			  				  </logic:notPresent>								
							</div>
						  </td>
						  
						  <td width="10%">
							<div align="right">
							  <logic:present name="debitoACobrar" property="valorTotalComBonus">
                				<bean:write name="debitoACobrar" property="valorTotalComBonus" formatKey="money.format"/>
			  				  </logic:present>
			  				  <logic:notPresent name="debitoACobrar" property="valorTotalComBonus">
								&nbsp;
			  				  </logic:notPresent>								
							</div>
						  </td>
						 
						  <td width="15%">
							<div>
							  <bean:write name="debitoACobrar" property="debitoCreditoSituacaoAtual.descricaoDebitoCreditoSituacao" />
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
			<td><strong><%@ include file="/jsp/util/indice_pager_novo.jsp"%></strong></td>
		  </tr>
		  <tr>
			<td height="24"><input type="button" class="bottonRightCol"	value="Voltar Pesquisa" onclick="window.location.href='<html:rewrite page="/exibirPesquisarDebitoACobrarAction.do?idImovel=${requestScope.idImovel}"/>'" /></td>
		  </tr>
		</table>
		</pg:pager>
	  </td>
	</tr>
</table>

</body>

</html:html>
