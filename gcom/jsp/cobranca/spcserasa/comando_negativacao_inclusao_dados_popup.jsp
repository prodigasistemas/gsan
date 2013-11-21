<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan" %>

<%@page import="gcom.cobranca.bean.DadosInclusoesComandoNegativacaoHelper"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">


<html:html>

<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>



<script language="JavaScript">


</script>

</head>

<body leftmargin="0" topmargin="0" onload="window.focus();">

<html:form action="/exibirInclusaoDadosComandoNegativacaoPopupAction" 
	name="InclusaoDadosComandoNegativacaoPopupActionForm" method="post"
	type="gcom.gui.cobranca.spcserasa.InclusaoDadosComandoNegativacaoPopupActionForm">


<table width="700" border="0" cellspacing="5" cellpadding="0">
	<tr>
		<td width="690" valign="top" class="centercoltext">
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
					<td class="parabg">Consultar Inclusões da Negativação </td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
				<tr>
					<td height="3" colspan="3"></td>
				</tr>
			</table>
			
			<table width="100%" cellpadding="0" cellspacing="0">
					
				<tr>
					<td>
						<strong>Negativador:</strong>
					</td>
					<td>
						<html:text property="negativador" size="50" maxlength="50" readonly="true"
						style="background-color:#EFEFEF; border:0; color: #000000;" />
						
					</td>
				</tr>
				<tr>
					<td height="10" colspan="4">
					<div align="right">
					<hr>
					</div>
					<div align="right"></div>
					</td>
				</tr>
				
				
				<tr>
					<td>
						<strong>Quantidade de Inclusões:</strong>
					</td>
					<td>
						<html:text property="qtdInclusoes"  size="5" maxlength="5" readonly="true" 
						style="background-color:#EFEFEF; border:0; color: #000000;" />
					</td>
				</tr>
				
				<tr>
					<td>
						<strong>Valor Total do Débito:</strong>
					</td>
					<td>
						<html:text property="valorTotalDebito"  size="16" maxlength="16" readonly="true" 
						style="background-color:#EFEFEF; border:0; color: #000000;" />
					</td>
				</tr>
				
				<tr>
					<td>
						<strong>Quantidade de Itens Incluídos:</strong>
					</td>
					<td>
						<html:text property="qtdItensIncluidos"  size="5" maxlength="5" readonly="true" 
						style="background-color:#EFEFEF; border:0; color: #000000;" />
					</td>
				</tr>
				
				<tr>
					<td height="10" colspan="4">
					<div align="right">
					<hr>
					</div>
					<div align="right"></div>
					</td>
				</tr>
				
	  		</table>
			
			<table width="100%" cellpadding="0" cellspacing="0">
				<tr>
					<td colspan="4" height="23">
						<font color="#000000" style="font-size:10px" face="Verdana, Arial, Helvetica, sans-serif">
						<strong>DADOS DAS INCLUSÕES</strong>
						</font>
					</td>
				</tr>
	  		</table>
	  		<table width="100%" cellpadding="0" cellspacing="0">
	  			
				<tr>
					<td colspan="4" bgcolor="#000000" height="1"></td>
				</tr>
				<tr>
					<td>
						<table width="100%" bgcolor="#99CCFF">
							<!--header da tabela interna -->
							<tr bgcolor="#99CCFF">
									<td width="13%" align="center">
										<strong>Imóvel</strong>
									</td>
									<td width="13%" align="center">
										<strong>CPF/CNPJ</strong>
									</td>
									<td width="11%" align="center">
										<strong>Valor do Débito</strong>
									</td>
									<td width="11%" align="center">
										<strong>Situação do Débito</strong>
									</td>
									<td width="11%" align="center">
										<strong>Data da Situação do Débito</strong>
									</td>
									<td width="9%" align="center">
										<strong>Inclusão Aceita</strong>
									</td>
									<td width="9%" align="center">
										<strong>Inclusão Corrigida</strong>
									</td>
									<td width="11%" align="center">
										<strong>Situação da Inclusao</strong>
									</td>
									<td width="10%" align="center">
										<strong>Usuário da Exclusão</strong>
									</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td>
						<table width="100%" bgcolor="#99CCFF">
						<%--Esquema de paginação--%>
						<pg:pager isOffset="true" index="half-full" maxIndexPages="10"
							export="currentPageNumber=pageNumber;pageOffset"				
							maxPageItems="10" items="${sessionScope.totalRegistrosSegundaPaginacao}">
							<pg:param name="pg"/>
							<pg:param name="q"/>
							<logic:present name="collectionDadosInclusoesComandoNegativacao">
								<%int cont = 0;%>
								<logic:iterate name="collectionDadosInclusoesComandoNegativacao" id="dadosInclusoesComandoNegativacao" type="DadosInclusoesComandoNegativacaoHelper">
									<pg:item>
										<%cont = cont + 1;
										if (cont % 2 == 0) {%>
										<tr bgcolor="#cbe5fe">
										<%} else {%>
										<tr bgcolor="#FFFFFF">
										<%}%>
											<td width="13%" align="center">
												<bean:write name="dadosInclusoesComandoNegativacao" property="idImovel"/>
											</td>
											<td width="13%" align="center">
												<bean:write name="dadosInclusoesComandoNegativacao" property="cpfCnpj"/>
											</td>
											<td width="11%" align="center">
												<bean:write name="dadosInclusoesComandoNegativacao" property="valorDebito"/>
											</td>
											<td width="11%" align="center">
												<bean:write name="dadosInclusoesComandoNegativacao" property="descricaoCobrancaSituacao"/>
											</td>
											<td width="11%" align="center">
												<bean:write name="dadosInclusoesComandoNegativacao" property="dataSituacaoDebito" formatKey="date.format"/>
											</td>
											<td width="9%" align="center">
												<bean:write name="dadosInclusoesComandoNegativacao" property="inclusaoAceita"/>
											</td>
											<td width="9%" align="center">
												<bean:write name="dadosInclusoesComandoNegativacao" property="inclusaoCorrigida"/>
											</td>
											<td width="11%" align="center">
												<bean:write name="dadosInclusoesComandoNegativacao" property="situacaoInclusao"/>
											</td>
											<td width="10%" align="center">
												<bean:write name="dadosInclusoesComandoNegativacao" property="nomeUsuario"/>
											</td>
										</tr>
									</pg:item>
								</logic:iterate>
							</logic:present>
						<%-- Fim do esquema de paginação --%>
						 
						</table>
						<table width="100%" border="0">
							<tr>
								<td align="center">
									<strong><%@ include file="/jsp/util/indice_pager_novo.jsp"%></strong>
								</td>
							</tr>
						</table>
						<table width="100%">
							<tr>
			            		<td >
		              				<div align="right">
	                  					<input name="ButtonFechar" type="button" class="bottonRightCol" value="Fechar" onclick="window.close();">
		              				</div>
			              		</td>
				          	</tr>
						</table>
						 
					</td>
				</tr>
			</table>		
		  </pg:pager>
		<p>&nbsp;</p>
		</td>
	</tr>
</table>

</body>
</html:form>
</html:html>